/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.sesame.engine;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Map;

import javax.inject.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableMap;
import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.component.tool.ToolContextUtils;
import com.opengamma.core.config.ConfigSource;
import com.opengamma.core.convention.ConventionSource;
import com.opengamma.core.exchange.ExchangeSource;
import com.opengamma.core.historicaltimeseries.HistoricalTimeSeriesSource;
import com.opengamma.core.holiday.HolidaySource;
import com.opengamma.core.marketdatasnapshot.MarketDataSnapshotSource;
import com.opengamma.core.organization.OrganizationSource;
import com.opengamma.core.position.PositionSource;
import com.opengamma.core.region.RegionSource;
import com.opengamma.core.security.SecuritySource;
import com.opengamma.financial.convention.ConventionBundleSource;
import com.opengamma.financial.tool.ToolContext;
import com.opengamma.sesame.graph.Provides;

/**
 * Loads components using {@link ToolContext} configuration and puts them in a map.
 * This isn't a long-term solution but will do for the time being.
 */
public final class ComponentMap {

  private static final Logger s_logger = LoggerFactory.getLogger(ComponentMap.class);

  public static final ComponentMap EMPTY = new ComponentMap(Collections.<Class<?>, Object>emptyMap());
  private final ImmutableMap<Class<?>, Object> _components;

  private ComponentMap(Map<Class<?>, Object> components) {
    _components = ImmutableMap.copyOf(components);
  }

  /**
   * @param location Location of the component config, can be a classpath: or file: resource or the URL or a remote
   * server
   * @return The available components, keyed by type.
   */
  public static ComponentMap loadComponents(String location) {
    ImmutableMap.Builder<Class<?>, Object> builder = ImmutableMap.builder();
    s_logger.info("Loading components from {}", location);
    ToolContext toolContext = ToolContextUtils.getToolContext(location, ToolContext.class);

    builder.put(ConfigSource.class, toolContext.getConfigSource());
    builder.put(ConventionBundleSource.class, toolContext.getConventionBundleSource());
    builder.put(ConventionSource.class, toolContext.getConventionSource());
    builder.put(ExchangeSource.class, toolContext.getExchangeSource());
    builder.put(HolidaySource.class, toolContext.getHolidaySource());
    builder.put(OrganizationSource.class, toolContext.getOrganizationSource());
    builder.put(PositionSource.class, toolContext.getPositionSource());
    builder.put(RegionSource.class, toolContext.getRegionSource());
    builder.put(SecuritySource.class, toolContext.getSecuritySource());
    builder.put(HistoricalTimeSeriesSource.class, toolContext.getHistoricalTimeSeriesSource());
    builder.put(MarketDataSnapshotSource.class, toolContext.getMarketDataSnapshotSource());

    return new ComponentMap(builder.build());
  }

  public Object getComponent(Class<?> type) {
    Object component = _components.get(type);
    // TODO move this to a helper
    if (component instanceof Provider) {
      Method getMethod;
      try {
        getMethod = component.getClass().getMethod("get", new Class[0]);
      } catch (NoSuchMethodException e) {
        // won't happen, we know it's a provider
        throw new OpenGammaRuntimeException("Unexpected exception", e);
      }
      if (getMethod.getAnnotation(Provides.class) != null) {
        return ((Provider) component).get();
      } else {
        return component;
      }
    } else {
      return component;
    }
  }

  public ComponentMap with(Map<Class<?>, Object> components) {
    ImmutableMap.Builder<Class<?>, Object> builder = ImmutableMap.builder();
    return new ComponentMap(builder.putAll(_components).putAll(components).build());
  }

  public static ComponentMap of(Map<Class<?>, Object> components) {
    return new ComponentMap(ImmutableMap.copyOf(components));
  }
}
