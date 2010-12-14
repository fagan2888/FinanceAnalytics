/**
 * Copyright (C) 2009 - 2010 by OpenGamma Inc.
 * 
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.ircurve;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.time.Instant;
import javax.time.InstantProvider;

import com.opengamma.DataNotFoundException;
import com.opengamma.core.common.Currency;
import com.opengamma.id.UniqueIdentifier;
import com.opengamma.master.NotifyingMaster;
import com.opengamma.master.VersionedSource;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.tuple.Pair;

/**
 * An in-memory master for yield curve definitions, backed by a hash-map.
 */
public class InMemoryInterpolatedYieldCurveDefinitionMaster implements InterpolatedYieldCurveDefinitionMaster, InterpolatedYieldCurveDefinitionSource, VersionedSource, NotifyingMaster {
  
  /**
   * Default scheme used for identifiers created.
   */
  public static final String DEFAULT_SCHEME = "InMemoryInterpolatedYieldCurveDefinition";

  private final Map<Pair<Currency, String>, TreeMap<Instant, YieldCurveDefinition>> _definitions = new HashMap<Pair<Currency, String>, TreeMap<Instant, YieldCurveDefinition>>();
  private final Set<MasterChangeListener> _listeners = new HashSet<MasterChangeListener>();

  private String _identifierScheme;
  private Instant _sourceVersionAsOfInstant;

  public InMemoryInterpolatedYieldCurveDefinitionMaster() {
    setIdentifierScheme(DEFAULT_SCHEME);
  }

  public void setIdentifierScheme(final String identifierScheme) {
    ArgumentChecker.notNull(identifierScheme, "identifierScheme");
    _identifierScheme = identifierScheme;
  }

  public String getIdentifierScheme() {
    return _identifierScheme;
  }

  // InterpolatedYieldCurveDefinitionSource

  /**
   * Gets a yield curve definition for a currency and name.
   * @param currency  the currency, not null
   * @param name  the name, not null
   * @return the definition, null if not found
   */
  @Override
  public synchronized YieldCurveDefinition getDefinition(Currency currency, String name) {
    ArgumentChecker.notNull(currency, "currency");
    ArgumentChecker.notNull(name, "name");
    final TreeMap<Instant, YieldCurveDefinition> definitions = _definitions.get(Pair.of(currency, name));
    if (definitions == null) {
      return null;
    }
    if (_sourceVersionAsOfInstant == null) {
      return definitions.lastEntry().getValue();
    } else {
      return definitions.floorEntry(_sourceVersionAsOfInstant).getValue();
    }
  }

  /**
   * Gets a yield curve definition for a currency, name and version.
   * @param currency  the currency, not null
   * @param name  the name, not null
   * @param version  the version instant, not null
   * @return the definition, null if not found
   */
  @Override
  public YieldCurveDefinition getDefinition(Currency currency, String name, InstantProvider version) {
    throw new UnsupportedOperationException();
  }

  // VersionedSource

  @Override
  public synchronized void setVersionAsOfInstant(final InstantProvider versionAsOfInstantProvider) {
    if (versionAsOfInstantProvider != null) {
      _sourceVersionAsOfInstant = Instant.of(versionAsOfInstantProvider);
    } else {
      _sourceVersionAsOfInstant = null;
    }
  }

  // InterpolatedYieldCurveDefinitionMaster

  @Override
  public synchronized YieldCurveDefinitionDocument add(YieldCurveDefinitionDocument document) {
    ArgumentChecker.notNull(document, "document");
    ArgumentChecker.notNull(document.getYieldCurveDefinition(), "document.yieldCurveDefinition");
    final Currency currency = document.getYieldCurveDefinition().getCurrency();
    final String name = document.getYieldCurveDefinition().getName();
    final Pair<Currency, String> key = Pair.of(currency, name);
    if (_definitions.containsKey(key)) {
      throw new IllegalArgumentException("Duplicate definition");
    }
    final TreeMap<Instant, YieldCurveDefinition> value = new TreeMap<Instant, YieldCurveDefinition>();
    value.put(Instant.now(), document.getYieldCurveDefinition());
    _definitions.put(key, value);
    document.setUniqueId(UniqueIdentifier.of(getIdentifierScheme(), currency.getISOCode() + "_" + name));
    fireChangeEvent();
    return document;
  }

  @Override
  public synchronized YieldCurveDefinitionDocument addOrUpdate(YieldCurveDefinitionDocument document) {
    ArgumentChecker.notNull(document, "document");
    ArgumentChecker.notNull(document.getYieldCurveDefinition(), "document.yieldCurveDefinition");
    final Currency currency = document.getYieldCurveDefinition().getCurrency();
    final String name = document.getYieldCurveDefinition().getName();
    final Pair<Currency, String> key = Pair.of(currency, name);
    TreeMap<Instant, YieldCurveDefinition> value = _definitions.get(key);
    if (value != null) {
      if (_sourceVersionAsOfInstant != null) {
        // Don't need to keep the old values before the one needed by "versionAsOfInstant"
        final Instant oldestNeeded = value.floorKey(_sourceVersionAsOfInstant);
        value.headMap(oldestNeeded).clear();
      } else {
        // Don't need any old values
        value.clear();
      }
      value.put(Instant.now(), document.getYieldCurveDefinition());
    } else {
      value = new TreeMap<Instant, YieldCurveDefinition>();
      value.put(Instant.now(), document.getYieldCurveDefinition());
      _definitions.put(key, value);
    }
    document.setUniqueId(UniqueIdentifier.of(getIdentifierScheme(), currency.getISOCode() + "_" + name));
    fireChangeEvent();
    return document;
  }

  @Override
  public YieldCurveDefinitionDocument correct(YieldCurveDefinitionDocument document) {
    throw new UnsupportedOperationException();
  }

  @Override
  public synchronized YieldCurveDefinitionDocument get(UniqueIdentifier uid) {
    ArgumentChecker.notNull(uid, "uid");
    if (!uid.isLatest()) {
      throw new IllegalArgumentException("Only latest version supported by '" + getIdentifierScheme() + "'");
    }
    if (!getIdentifierScheme().equals(uid.getScheme())) {
      throw new DataNotFoundException("Scheme '" + uid.getScheme() + "' not valid for '" + getIdentifierScheme() + "'");
    }
    final int i = uid.getValue().indexOf('_');
    if (i <= 0) {
      throw new DataNotFoundException("Identifier '" + uid.getValue() + "' not valid for '" + getIdentifierScheme() + "'");
    }
    final String iso = uid.getValue().substring(0, i);
    final String name = uid.getValue().substring(i + 1);
    final Currency currency;
    try {
      currency = Currency.getInstance(iso);
    } catch (IllegalArgumentException e) {
      throw new DataNotFoundException("Identifier '" + uid.getValue() + "' not valid for '" + getIdentifierScheme() + "'", e);
    }
    final TreeMap<Instant, YieldCurveDefinition> definitions = _definitions.get(Pair.of(currency, name));
    if (definitions == null) {
      throw new DataNotFoundException("Curve definition not found");
    }
    final YieldCurveDefinition definition = definitions.lastEntry().getValue();
    if (definition == null) {
      throw new DataNotFoundException("Curve definition not found");
    }
    return new YieldCurveDefinitionDocument(uid, definition);
  }

  @Override
  public synchronized void remove(UniqueIdentifier uid) {
    ArgumentChecker.notNull(uid, "uid");
    if (!uid.isLatest()) {
      throw new IllegalArgumentException("Only latest version supported by '" + getIdentifierScheme() + "'");
    }
    if (!getIdentifierScheme().equals(uid.getScheme())) {
      throw new DataNotFoundException("Scheme '" + uid.getScheme() + "' not valid for '" + getIdentifierScheme() + "'");
    }
    final int i = uid.getValue().indexOf('_');
    if (i <= 0) {
      throw new DataNotFoundException("Identifier '" + uid.getValue() + "' not valid for '" + getIdentifierScheme() + "'");
    }
    final String iso = uid.getValue().substring(0, i);
    final String name = uid.getValue().substring(i + 1);
    final Currency currency;
    try {
      currency = Currency.getInstance(iso);
    } catch (IllegalArgumentException e) {
      throw new DataNotFoundException("Identifier '" + uid.getValue() + "' not valid for '" + getIdentifierScheme() + "'", e);
    }
    final Pair<Currency, String> key = Pair.of(currency, name);
    if (_sourceVersionAsOfInstant != null) {
      final TreeMap<Instant, YieldCurveDefinition> value = _definitions.get(key);
      if (value == null) {
        throw new DataNotFoundException("Curve definition not found");
      }
      // Don't need to keep the old values before the one needed by "versionAsOfInstant"
      final Instant oldestNeeded = value.floorKey(_sourceVersionAsOfInstant);
      value.headMap(oldestNeeded).clear();
      // Store a null to indicate the delete
      value.put(Instant.now(), null);
    } else {
      if (_definitions.remove(key) == null) {
        throw new DataNotFoundException("Curve definition not found");
      }
    }
    fireChangeEvent();
  }

  @Override
  public synchronized YieldCurveDefinitionDocument update(YieldCurveDefinitionDocument document) {
    ArgumentChecker.notNull(document, "document");
    ArgumentChecker.notNull(document.getYieldCurveDefinition(), "document.yieldCurveDefinition");
    final Currency currency = document.getYieldCurveDefinition().getCurrency();
    final String name = document.getYieldCurveDefinition().getName();
    final UniqueIdentifier uid = UniqueIdentifier.of(getIdentifierScheme(), currency.getISOCode() + "_" + name);
    if (!uid.equals(document.getUniqueId())) {
      throw new IllegalArgumentException("Invalid unique identifier");
    }
    final Pair<Currency, String> key = Pair.of(currency, name);
    final TreeMap<Instant, YieldCurveDefinition> value = _definitions.get(key);
    if (value == null) {
      throw new DataNotFoundException("UID '" + uid + "' not found");
    }
    if (_sourceVersionAsOfInstant != null) {
      // Don't need to keep the old values before the one needed by "versionAsOfInstant"
      final Instant oldestNeeded = value.floorKey(_sourceVersionAsOfInstant);
      value.headMap(oldestNeeded).clear();
    } else {
      // Don't need any old values
      value.clear();
    }
    value.put(Instant.now(), document.getYieldCurveDefinition());
    document.setUniqueId(uid);
    fireChangeEvent();
    return document;
  }

  private synchronized void fireChangeEvent() {
    // TODO temporary hack until listeners are done properly
    final Instant now = Instant.now();
    for (final MasterChangeListener listener : _listeners) {
      final Thread async = new Thread() {
        @Override
        public void run() {
          listener.onMasterChanged(now);
        }
      };
      async.start();
    }
  }

  @Override
  public synchronized void addOnChangeListener(MasterChangeListener listener) {
    ArgumentChecker.notNull(listener, "listener");
    // TODO temporary hack until listeners are done properly
    _listeners.add(listener);
  }

  @Override
  public synchronized void removeOnChangeListener(MasterChangeListener listener) {
    ArgumentChecker.notNull(listener, "listener");
    // TODO temporary hack until listeners are done properly
    _listeners.remove(listener);
  }

}
