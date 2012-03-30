/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.web;

import java.util.LinkedHashMap;
import java.util.Map;

import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.bbg.BloombergSecuritySource;
import com.opengamma.bbg.ReferenceDataProvider;
import com.opengamma.component.ComponentRepository;
import com.opengamma.component.factory.AbstractComponentFactory;
import com.opengamma.core.historicaltimeseries.HistoricalTimeSeriesSource;
import com.opengamma.integration.copier.portfolio.web.PortfolioLoaderResource;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesMaster;
import com.opengamma.master.portfolio.PortfolioMaster;
import com.opengamma.master.position.PositionMaster;
import com.opengamma.master.security.SecurityMaster;

/**
 * Factory for registering REST components from the OG-Integration project that can't be configured with the other
 * REST resources because they're not available to the OG-Web project component factories.
 */
@BeanDefinition
public class IntegrationWebComponentFactory extends AbstractComponentFactory {

  @PropertyDefinition(validate = "notNull")
  private BloombergSecuritySource _bloombergSecuritySource;
  @PropertyDefinition(validate = "notNull")
  private HistoricalTimeSeriesMaster _historicalTimeSeriesMaster;
  @PropertyDefinition(validate = "notNull")
  private HistoricalTimeSeriesSource _bloombergHistoricalTimeSeriesSource;
  @PropertyDefinition(validate = "notNull")
  private ReferenceDataProvider _bloombergReferenceDataProvider;
  @PropertyDefinition(validate = "notNull")
  private PortfolioMaster _portfolioMaster;
  @PropertyDefinition(validate = "notNull")
  private PositionMaster _positionMaster;
  @PropertyDefinition(validate = "notNull")
  private SecurityMaster _securityMaster;

    @Override
  public void init(ComponentRepository repo, LinkedHashMap<String, String> configuration) throws Exception {
      repo.getRestComponents().publishResource(new PortfolioLoaderResource(getHistoricalTimeSeriesMaster(),
                                                                           getBloombergHistoricalTimeSeriesSource(),
                                                                           getBloombergReferenceDataProvider(),
                                                                           getBloombergSecuritySource(),
                                                                           getPortfolioMaster(),
                                                                           getPositionMaster(),
                                                                           getSecurityMaster()));
  }
  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code IntegrationWebComponentFactory}.
   * @return the meta-bean, not null
   */
  public static IntegrationWebComponentFactory.Meta meta() {
    return IntegrationWebComponentFactory.Meta.INSTANCE;
  }
  static {
    JodaBeanUtils.registerMetaBean(IntegrationWebComponentFactory.Meta.INSTANCE);
  }

  @Override
  public IntegrationWebComponentFactory.Meta metaBean() {
    return IntegrationWebComponentFactory.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -437041514:  // bloombergSecuritySource
        return getBloombergSecuritySource();
      case 173967376:  // historicalTimeSeriesMaster
        return getHistoricalTimeSeriesMaster();
      case 1652351076:  // bloombergHistoricalTimeSeriesSource
        return getBloombergHistoricalTimeSeriesSource();
      case -245204181:  // bloombergReferenceDataProvider
        return getBloombergReferenceDataProvider();
      case -772274742:  // portfolioMaster
        return getPortfolioMaster();
      case -1840419605:  // positionMaster
        return getPositionMaster();
      case -887218750:  // securityMaster
        return getSecurityMaster();
    }
    return super.propertyGet(propertyName, quiet);
  }

  @Override
  protected void propertySet(String propertyName, Object newValue, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -437041514:  // bloombergSecuritySource
        setBloombergSecuritySource((BloombergSecuritySource) newValue);
        return;
      case 173967376:  // historicalTimeSeriesMaster
        setHistoricalTimeSeriesMaster((HistoricalTimeSeriesMaster) newValue);
        return;
      case 1652351076:  // bloombergHistoricalTimeSeriesSource
        setBloombergHistoricalTimeSeriesSource((HistoricalTimeSeriesSource) newValue);
        return;
      case -245204181:  // bloombergReferenceDataProvider
        setBloombergReferenceDataProvider((ReferenceDataProvider) newValue);
        return;
      case -772274742:  // portfolioMaster
        setPortfolioMaster((PortfolioMaster) newValue);
        return;
      case -1840419605:  // positionMaster
        setPositionMaster((PositionMaster) newValue);
        return;
      case -887218750:  // securityMaster
        setSecurityMaster((SecurityMaster) newValue);
        return;
    }
    super.propertySet(propertyName, newValue, quiet);
  }

  @Override
  protected void validate() {
    JodaBeanUtils.notNull(_bloombergSecuritySource, "bloombergSecuritySource");
    JodaBeanUtils.notNull(_historicalTimeSeriesMaster, "historicalTimeSeriesMaster");
    JodaBeanUtils.notNull(_bloombergHistoricalTimeSeriesSource, "bloombergHistoricalTimeSeriesSource");
    JodaBeanUtils.notNull(_bloombergReferenceDataProvider, "bloombergReferenceDataProvider");
    JodaBeanUtils.notNull(_portfolioMaster, "portfolioMaster");
    JodaBeanUtils.notNull(_positionMaster, "positionMaster");
    JodaBeanUtils.notNull(_securityMaster, "securityMaster");
    super.validate();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      IntegrationWebComponentFactory other = (IntegrationWebComponentFactory) obj;
      return JodaBeanUtils.equal(getBloombergSecuritySource(), other.getBloombergSecuritySource()) &&
          JodaBeanUtils.equal(getHistoricalTimeSeriesMaster(), other.getHistoricalTimeSeriesMaster()) &&
          JodaBeanUtils.equal(getBloombergHistoricalTimeSeriesSource(), other.getBloombergHistoricalTimeSeriesSource()) &&
          JodaBeanUtils.equal(getBloombergReferenceDataProvider(), other.getBloombergReferenceDataProvider()) &&
          JodaBeanUtils.equal(getPortfolioMaster(), other.getPortfolioMaster()) &&
          JodaBeanUtils.equal(getPositionMaster(), other.getPositionMaster()) &&
          JodaBeanUtils.equal(getSecurityMaster(), other.getSecurityMaster()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash += hash * 31 + JodaBeanUtils.hashCode(getBloombergSecuritySource());
    hash += hash * 31 + JodaBeanUtils.hashCode(getHistoricalTimeSeriesMaster());
    hash += hash * 31 + JodaBeanUtils.hashCode(getBloombergHistoricalTimeSeriesSource());
    hash += hash * 31 + JodaBeanUtils.hashCode(getBloombergReferenceDataProvider());
    hash += hash * 31 + JodaBeanUtils.hashCode(getPortfolioMaster());
    hash += hash * 31 + JodaBeanUtils.hashCode(getPositionMaster());
    hash += hash * 31 + JodaBeanUtils.hashCode(getSecurityMaster());
    return hash ^ super.hashCode();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the bloombergSecuritySource.
   * @return the value of the property, not null
   */
  public BloombergSecuritySource getBloombergSecuritySource() {
    return _bloombergSecuritySource;
  }

  /**
   * Sets the bloombergSecuritySource.
   * @param bloombergSecuritySource  the new value of the property, not null
   */
  public void setBloombergSecuritySource(BloombergSecuritySource bloombergSecuritySource) {
    JodaBeanUtils.notNull(bloombergSecuritySource, "bloombergSecuritySource");
    this._bloombergSecuritySource = bloombergSecuritySource;
  }

  /**
   * Gets the the {@code bloombergSecuritySource} property.
   * @return the property, not null
   */
  public final Property<BloombergSecuritySource> bloombergSecuritySource() {
    return metaBean().bloombergSecuritySource().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the historicalTimeSeriesMaster.
   * @return the value of the property, not null
   */
  public HistoricalTimeSeriesMaster getHistoricalTimeSeriesMaster() {
    return _historicalTimeSeriesMaster;
  }

  /**
   * Sets the historicalTimeSeriesMaster.
   * @param historicalTimeSeriesMaster  the new value of the property, not null
   */
  public void setHistoricalTimeSeriesMaster(HistoricalTimeSeriesMaster historicalTimeSeriesMaster) {
    JodaBeanUtils.notNull(historicalTimeSeriesMaster, "historicalTimeSeriesMaster");
    this._historicalTimeSeriesMaster = historicalTimeSeriesMaster;
  }

  /**
   * Gets the the {@code historicalTimeSeriesMaster} property.
   * @return the property, not null
   */
  public final Property<HistoricalTimeSeriesMaster> historicalTimeSeriesMaster() {
    return metaBean().historicalTimeSeriesMaster().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the bloombergHistoricalTimeSeriesSource.
   * @return the value of the property, not null
   */
  public HistoricalTimeSeriesSource getBloombergHistoricalTimeSeriesSource() {
    return _bloombergHistoricalTimeSeriesSource;
  }

  /**
   * Sets the bloombergHistoricalTimeSeriesSource.
   * @param bloombergHistoricalTimeSeriesSource  the new value of the property, not null
   */
  public void setBloombergHistoricalTimeSeriesSource(HistoricalTimeSeriesSource bloombergHistoricalTimeSeriesSource) {
    JodaBeanUtils.notNull(bloombergHistoricalTimeSeriesSource, "bloombergHistoricalTimeSeriesSource");
    this._bloombergHistoricalTimeSeriesSource = bloombergHistoricalTimeSeriesSource;
  }

  /**
   * Gets the the {@code bloombergHistoricalTimeSeriesSource} property.
   * @return the property, not null
   */
  public final Property<HistoricalTimeSeriesSource> bloombergHistoricalTimeSeriesSource() {
    return metaBean().bloombergHistoricalTimeSeriesSource().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the bloombergReferenceDataProvider.
   * @return the value of the property, not null
   */
  public ReferenceDataProvider getBloombergReferenceDataProvider() {
    return _bloombergReferenceDataProvider;
  }

  /**
   * Sets the bloombergReferenceDataProvider.
   * @param bloombergReferenceDataProvider  the new value of the property, not null
   */
  public void setBloombergReferenceDataProvider(ReferenceDataProvider bloombergReferenceDataProvider) {
    JodaBeanUtils.notNull(bloombergReferenceDataProvider, "bloombergReferenceDataProvider");
    this._bloombergReferenceDataProvider = bloombergReferenceDataProvider;
  }

  /**
   * Gets the the {@code bloombergReferenceDataProvider} property.
   * @return the property, not null
   */
  public final Property<ReferenceDataProvider> bloombergReferenceDataProvider() {
    return metaBean().bloombergReferenceDataProvider().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the portfolioMaster.
   * @return the value of the property, not null
   */
  public PortfolioMaster getPortfolioMaster() {
    return _portfolioMaster;
  }

  /**
   * Sets the portfolioMaster.
   * @param portfolioMaster  the new value of the property, not null
   */
  public void setPortfolioMaster(PortfolioMaster portfolioMaster) {
    JodaBeanUtils.notNull(portfolioMaster, "portfolioMaster");
    this._portfolioMaster = portfolioMaster;
  }

  /**
   * Gets the the {@code portfolioMaster} property.
   * @return the property, not null
   */
  public final Property<PortfolioMaster> portfolioMaster() {
    return metaBean().portfolioMaster().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the positionMaster.
   * @return the value of the property, not null
   */
  public PositionMaster getPositionMaster() {
    return _positionMaster;
  }

  /**
   * Sets the positionMaster.
   * @param positionMaster  the new value of the property, not null
   */
  public void setPositionMaster(PositionMaster positionMaster) {
    JodaBeanUtils.notNull(positionMaster, "positionMaster");
    this._positionMaster = positionMaster;
  }

  /**
   * Gets the the {@code positionMaster} property.
   * @return the property, not null
   */
  public final Property<PositionMaster> positionMaster() {
    return metaBean().positionMaster().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the securityMaster.
   * @return the value of the property, not null
   */
  public SecurityMaster getSecurityMaster() {
    return _securityMaster;
  }

  /**
   * Sets the securityMaster.
   * @param securityMaster  the new value of the property, not null
   */
  public void setSecurityMaster(SecurityMaster securityMaster) {
    JodaBeanUtils.notNull(securityMaster, "securityMaster");
    this._securityMaster = securityMaster;
  }

  /**
   * Gets the the {@code securityMaster} property.
   * @return the property, not null
   */
  public final Property<SecurityMaster> securityMaster() {
    return metaBean().securityMaster().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code IntegrationWebComponentFactory}.
   */
  public static class Meta extends AbstractComponentFactory.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code bloombergSecuritySource} property.
     */
    private final MetaProperty<BloombergSecuritySource> _bloombergSecuritySource = DirectMetaProperty.ofReadWrite(
        this, "bloombergSecuritySource", IntegrationWebComponentFactory.class, BloombergSecuritySource.class);
    /**
     * The meta-property for the {@code historicalTimeSeriesMaster} property.
     */
    private final MetaProperty<HistoricalTimeSeriesMaster> _historicalTimeSeriesMaster = DirectMetaProperty.ofReadWrite(
        this, "historicalTimeSeriesMaster", IntegrationWebComponentFactory.class, HistoricalTimeSeriesMaster.class);
    /**
     * The meta-property for the {@code bloombergHistoricalTimeSeriesSource} property.
     */
    private final MetaProperty<HistoricalTimeSeriesSource> _bloombergHistoricalTimeSeriesSource = DirectMetaProperty.ofReadWrite(
        this, "bloombergHistoricalTimeSeriesSource", IntegrationWebComponentFactory.class, HistoricalTimeSeriesSource.class);
    /**
     * The meta-property for the {@code bloombergReferenceDataProvider} property.
     */
    private final MetaProperty<ReferenceDataProvider> _bloombergReferenceDataProvider = DirectMetaProperty.ofReadWrite(
        this, "bloombergReferenceDataProvider", IntegrationWebComponentFactory.class, ReferenceDataProvider.class);
    /**
     * The meta-property for the {@code portfolioMaster} property.
     */
    private final MetaProperty<PortfolioMaster> _portfolioMaster = DirectMetaProperty.ofReadWrite(
        this, "portfolioMaster", IntegrationWebComponentFactory.class, PortfolioMaster.class);
    /**
     * The meta-property for the {@code positionMaster} property.
     */
    private final MetaProperty<PositionMaster> _positionMaster = DirectMetaProperty.ofReadWrite(
        this, "positionMaster", IntegrationWebComponentFactory.class, PositionMaster.class);
    /**
     * The meta-property for the {@code securityMaster} property.
     */
    private final MetaProperty<SecurityMaster> _securityMaster = DirectMetaProperty.ofReadWrite(
        this, "securityMaster", IntegrationWebComponentFactory.class, SecurityMaster.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
      this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "bloombergSecuritySource",
        "historicalTimeSeriesMaster",
        "bloombergHistoricalTimeSeriesSource",
        "bloombergReferenceDataProvider",
        "portfolioMaster",
        "positionMaster",
        "securityMaster");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -437041514:  // bloombergSecuritySource
          return _bloombergSecuritySource;
        case 173967376:  // historicalTimeSeriesMaster
          return _historicalTimeSeriesMaster;
        case 1652351076:  // bloombergHistoricalTimeSeriesSource
          return _bloombergHistoricalTimeSeriesSource;
        case -245204181:  // bloombergReferenceDataProvider
          return _bloombergReferenceDataProvider;
        case -772274742:  // portfolioMaster
          return _portfolioMaster;
        case -1840419605:  // positionMaster
          return _positionMaster;
        case -887218750:  // securityMaster
          return _securityMaster;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends IntegrationWebComponentFactory> builder() {
      return new DirectBeanBuilder<IntegrationWebComponentFactory>(new IntegrationWebComponentFactory());
    }

    @Override
    public Class<? extends IntegrationWebComponentFactory> beanType() {
      return IntegrationWebComponentFactory.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code bloombergSecuritySource} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<BloombergSecuritySource> bloombergSecuritySource() {
      return _bloombergSecuritySource;
    }

    /**
     * The meta-property for the {@code historicalTimeSeriesMaster} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<HistoricalTimeSeriesMaster> historicalTimeSeriesMaster() {
      return _historicalTimeSeriesMaster;
    }

    /**
     * The meta-property for the {@code bloombergHistoricalTimeSeriesSource} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<HistoricalTimeSeriesSource> bloombergHistoricalTimeSeriesSource() {
      return _bloombergHistoricalTimeSeriesSource;
    }

    /**
     * The meta-property for the {@code bloombergReferenceDataProvider} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ReferenceDataProvider> bloombergReferenceDataProvider() {
      return _bloombergReferenceDataProvider;
    }

    /**
     * The meta-property for the {@code portfolioMaster} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<PortfolioMaster> portfolioMaster() {
      return _portfolioMaster;
    }

    /**
     * The meta-property for the {@code positionMaster} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<PositionMaster> positionMaster() {
      return _positionMaster;
    }

    /**
     * The meta-property for the {@code securityMaster} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<SecurityMaster> securityMaster() {
      return _securityMaster;
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
