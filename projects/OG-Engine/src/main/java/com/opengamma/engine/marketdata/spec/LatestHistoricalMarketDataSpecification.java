/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.engine.marketdata.spec;

import java.util.Map;
import java.util.NoSuchElementException;

import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

/**
 * 
 */
@BeanDefinition(hierarchy = "immutable")
public class LatestHistoricalMarketDataSpecification extends HistoricalMarketDataSpecification implements MarketDataSpecification {
  
  private static final long serialVersionUID = 1L;

  /**
   * Creates an instance, using the default time series resolver key
   */
  public LatestHistoricalMarketDataSpecification() {
  }
  
  /**
   * Creates an instance, specifying the time series resolver key
   * 
   * @param timeSeriesResolverKey the time series resolver key, not null
   */
  public LatestHistoricalMarketDataSpecification(String timeSeriesResolverKey) {
    super(timeSeriesResolverKey);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code LatestHistoricalMarketDataSpecification}.
   * @return the meta-bean, not null
   */
  public static LatestHistoricalMarketDataSpecification.Meta meta() {
    return LatestHistoricalMarketDataSpecification.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(LatestHistoricalMarketDataSpecification.Meta.INSTANCE);
  }

  /**
   * Returns a builder used to create an instance of the bean.
   * @return the builder, not null
   */
  public static LatestHistoricalMarketDataSpecification.Builder builder() {
    return new LatestHistoricalMarketDataSpecification.Builder();
  }

  /**
   * Restricted constructor.
   * @param builder  the builder to copy from, not null
   */
  protected LatestHistoricalMarketDataSpecification(LatestHistoricalMarketDataSpecification.Builder builder) {
    super(builder);
  }

  @Override
  public LatestHistoricalMarketDataSpecification.Meta metaBean() {
    return LatestHistoricalMarketDataSpecification.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  @Override
  public LatestHistoricalMarketDataSpecification clone() {
    return this;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      return super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    return hash ^ super.hashCode();
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(32);
    buf.append("LatestHistoricalMarketDataSpecification{");
    int len = buf.length();
    toString(buf);
    if (buf.length() > len) {
      buf.setLength(buf.length() - 2);
    }
    buf.append('}');
    return buf.toString();
  }

  @Override
  protected void toString(StringBuilder buf) {
    super.toString(buf);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code LatestHistoricalMarketDataSpecification}.
   */
  public static class Meta extends HistoricalMarketDataSpecification.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap());

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    public LatestHistoricalMarketDataSpecification.Builder builder() {
      return new LatestHistoricalMarketDataSpecification.Builder();
    }

    @Override
    public Class<? extends LatestHistoricalMarketDataSpecification> beanType() {
      return LatestHistoricalMarketDataSpecification.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
  }

  //-----------------------------------------------------------------------
  /**
   * The bean-builder for {@code LatestHistoricalMarketDataSpecification}.
   */
  public static class Builder extends HistoricalMarketDataSpecification.Builder {

    /**
     * Restricted constructor.
     */
    protected Builder() {
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      throw new NoSuchElementException("Unknown property: " + propertyName);
    }

    @Override
    public Builder set(String propertyName, Object newValue) {
      throw new NoSuchElementException("Unknown property: " + propertyName);
    }

    @Override
    public Builder set(MetaProperty<?> property, Object value) {
      super.set(property, value);
      return this;
    }

    @Override
    public Builder setString(String propertyName, String value) {
      setString(meta().metaProperty(propertyName), value);
      return this;
    }

    @Override
    public Builder setString(MetaProperty<?> property, String value) {
      super.set(property, value);
      return this;
    }

    @Override
    public Builder setAll(Map<String, ? extends Object> propertyValueMap) {
      super.setAll(propertyValueMap);
      return this;
    }

    @Override
    public LatestHistoricalMarketDataSpecification build() {
      return new LatestHistoricalMarketDataSpecification(this);
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(32);
      buf.append("LatestHistoricalMarketDataSpecification.Builder{");
      int len = buf.length();
      toString(buf);
      if (buf.length() > len) {
        buf.setLength(buf.length() - 2);
      }
      buf.append('}');
      return buf.toString();
    }

    @Override
    protected void toString(StringBuilder buf) {
      super.toString(buf);
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
