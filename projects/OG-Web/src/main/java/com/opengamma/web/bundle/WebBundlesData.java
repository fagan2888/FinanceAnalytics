/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.web.bundle;

import java.util.Map;

import javax.ws.rs.core.HttpHeaders;

import org.joda.beans.Bean;
import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.web.WebPerRequestData;

/**
 * Data class for web-based bundles.
 */
@BeanDefinition
public class WebBundlesData extends WebPerRequestData {

  /**
   * The bundle manager factory.
   */
  @PropertyDefinition
  private BundleManagerFactory _bundleManagerFactory;
  /**
   * The bundle manager.
   */
  @PropertyDefinition
  private BundleManager _bundleManager;
  /**
   * The development bundle manager.
   * This manager contains bundles that have been adjusted for development.
   */
  @PropertyDefinition
  private BundleManager _devBundleManager;
  /**
   * The bundle compressor.
   */
  @PropertyDefinition
  private BundleCompressor _compressor;
  /**
   * The deployment mode.
   */
  @PropertyDefinition
  private DeployMode _mode;
  /**
   * The style tag.
   */
  @PropertyDefinition
  private StyleTag _styleTag;
  /**
   * The script tag.
   */
  @PropertyDefinition
  private ScriptTag _scriptTag;
  /**
   * HttpHeaders information.
   */
  @PropertyDefinition
  private HttpHeaders _httpHeaders;
  /**
   * Creates an instance.
   */
  public WebBundlesData() {
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code WebBundlesData}.
   * @return the meta-bean, not null
   */
  public static WebBundlesData.Meta meta() {
    return WebBundlesData.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(WebBundlesData.Meta.INSTANCE);
  }

  @Override
  public WebBundlesData.Meta metaBean() {
    return WebBundlesData.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the bundle manager factory.
   * @return the value of the property
   */
  public BundleManagerFactory getBundleManagerFactory() {
    return _bundleManagerFactory;
  }

  /**
   * Sets the bundle manager factory.
   * @param bundleManagerFactory  the new value of the property
   */
  public void setBundleManagerFactory(BundleManagerFactory bundleManagerFactory) {
    this._bundleManagerFactory = bundleManagerFactory;
  }

  /**
   * Gets the the {@code bundleManagerFactory} property.
   * @return the property, not null
   */
  public final Property<BundleManagerFactory> bundleManagerFactory() {
    return metaBean().bundleManagerFactory().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the bundle manager.
   * @return the value of the property
   */
  public BundleManager getBundleManager() {
    return _bundleManager;
  }

  /**
   * Sets the bundle manager.
   * @param bundleManager  the new value of the property
   */
  public void setBundleManager(BundleManager bundleManager) {
    this._bundleManager = bundleManager;
  }

  /**
   * Gets the the {@code bundleManager} property.
   * @return the property, not null
   */
  public final Property<BundleManager> bundleManager() {
    return metaBean().bundleManager().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the development bundle manager.
   * This manager contains bundles that have been adjusted for development.
   * @return the value of the property
   */
  public BundleManager getDevBundleManager() {
    return _devBundleManager;
  }

  /**
   * Sets the development bundle manager.
   * This manager contains bundles that have been adjusted for development.
   * @param devBundleManager  the new value of the property
   */
  public void setDevBundleManager(BundleManager devBundleManager) {
    this._devBundleManager = devBundleManager;
  }

  /**
   * Gets the the {@code devBundleManager} property.
   * This manager contains bundles that have been adjusted for development.
   * @return the property, not null
   */
  public final Property<BundleManager> devBundleManager() {
    return metaBean().devBundleManager().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the bundle compressor.
   * @return the value of the property
   */
  public BundleCompressor getCompressor() {
    return _compressor;
  }

  /**
   * Sets the bundle compressor.
   * @param compressor  the new value of the property
   */
  public void setCompressor(BundleCompressor compressor) {
    this._compressor = compressor;
  }

  /**
   * Gets the the {@code compressor} property.
   * @return the property, not null
   */
  public final Property<BundleCompressor> compressor() {
    return metaBean().compressor().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the deployment mode.
   * @return the value of the property
   */
  public DeployMode getMode() {
    return _mode;
  }

  /**
   * Sets the deployment mode.
   * @param mode  the new value of the property
   */
  public void setMode(DeployMode mode) {
    this._mode = mode;
  }

  /**
   * Gets the the {@code mode} property.
   * @return the property, not null
   */
  public final Property<DeployMode> mode() {
    return metaBean().mode().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the style tag.
   * @return the value of the property
   */
  public StyleTag getStyleTag() {
    return _styleTag;
  }

  /**
   * Sets the style tag.
   * @param styleTag  the new value of the property
   */
  public void setStyleTag(StyleTag styleTag) {
    this._styleTag = styleTag;
  }

  /**
   * Gets the the {@code styleTag} property.
   * @return the property, not null
   */
  public final Property<StyleTag> styleTag() {
    return metaBean().styleTag().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the script tag.
   * @return the value of the property
   */
  public ScriptTag getScriptTag() {
    return _scriptTag;
  }

  /**
   * Sets the script tag.
   * @param scriptTag  the new value of the property
   */
  public void setScriptTag(ScriptTag scriptTag) {
    this._scriptTag = scriptTag;
  }

  /**
   * Gets the the {@code scriptTag} property.
   * @return the property, not null
   */
  public final Property<ScriptTag> scriptTag() {
    return metaBean().scriptTag().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets httpHeaders information.
   * @return the value of the property
   */
  public HttpHeaders getHttpHeaders() {
    return _httpHeaders;
  }

  /**
   * Sets httpHeaders information.
   * @param httpHeaders  the new value of the property
   */
  public void setHttpHeaders(HttpHeaders httpHeaders) {
    this._httpHeaders = httpHeaders;
  }

  /**
   * Gets the the {@code httpHeaders} property.
   * @return the property, not null
   */
  public final Property<HttpHeaders> httpHeaders() {
    return metaBean().httpHeaders().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public WebBundlesData clone() {
    return JodaBeanUtils.cloneAlways(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      WebBundlesData other = (WebBundlesData) obj;
      return JodaBeanUtils.equal(getBundleManagerFactory(), other.getBundleManagerFactory()) &&
          JodaBeanUtils.equal(getBundleManager(), other.getBundleManager()) &&
          JodaBeanUtils.equal(getDevBundleManager(), other.getDevBundleManager()) &&
          JodaBeanUtils.equal(getCompressor(), other.getCompressor()) &&
          JodaBeanUtils.equal(getMode(), other.getMode()) &&
          JodaBeanUtils.equal(getStyleTag(), other.getStyleTag()) &&
          JodaBeanUtils.equal(getScriptTag(), other.getScriptTag()) &&
          JodaBeanUtils.equal(getHttpHeaders(), other.getHttpHeaders()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash += hash * 31 + JodaBeanUtils.hashCode(getBundleManagerFactory());
    hash += hash * 31 + JodaBeanUtils.hashCode(getBundleManager());
    hash += hash * 31 + JodaBeanUtils.hashCode(getDevBundleManager());
    hash += hash * 31 + JodaBeanUtils.hashCode(getCompressor());
    hash += hash * 31 + JodaBeanUtils.hashCode(getMode());
    hash += hash * 31 + JodaBeanUtils.hashCode(getStyleTag());
    hash += hash * 31 + JodaBeanUtils.hashCode(getScriptTag());
    hash += hash * 31 + JodaBeanUtils.hashCode(getHttpHeaders());
    return hash ^ super.hashCode();
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(288);
    buf.append("WebBundlesData{");
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
    buf.append("bundleManagerFactory").append('=').append(JodaBeanUtils.toString(getBundleManagerFactory())).append(',').append(' ');
    buf.append("bundleManager").append('=').append(JodaBeanUtils.toString(getBundleManager())).append(',').append(' ');
    buf.append("devBundleManager").append('=').append(JodaBeanUtils.toString(getDevBundleManager())).append(',').append(' ');
    buf.append("compressor").append('=').append(JodaBeanUtils.toString(getCompressor())).append(',').append(' ');
    buf.append("mode").append('=').append(JodaBeanUtils.toString(getMode())).append(',').append(' ');
    buf.append("styleTag").append('=').append(JodaBeanUtils.toString(getStyleTag())).append(',').append(' ');
    buf.append("scriptTag").append('=').append(JodaBeanUtils.toString(getScriptTag())).append(',').append(' ');
    buf.append("httpHeaders").append('=').append(JodaBeanUtils.toString(getHttpHeaders())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code WebBundlesData}.
   */
  public static class Meta extends WebPerRequestData.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code bundleManagerFactory} property.
     */
    private final MetaProperty<BundleManagerFactory> _bundleManagerFactory = DirectMetaProperty.ofReadWrite(
        this, "bundleManagerFactory", WebBundlesData.class, BundleManagerFactory.class);
    /**
     * The meta-property for the {@code bundleManager} property.
     */
    private final MetaProperty<BundleManager> _bundleManager = DirectMetaProperty.ofReadWrite(
        this, "bundleManager", WebBundlesData.class, BundleManager.class);
    /**
     * The meta-property for the {@code devBundleManager} property.
     */
    private final MetaProperty<BundleManager> _devBundleManager = DirectMetaProperty.ofReadWrite(
        this, "devBundleManager", WebBundlesData.class, BundleManager.class);
    /**
     * The meta-property for the {@code compressor} property.
     */
    private final MetaProperty<BundleCompressor> _compressor = DirectMetaProperty.ofReadWrite(
        this, "compressor", WebBundlesData.class, BundleCompressor.class);
    /**
     * The meta-property for the {@code mode} property.
     */
    private final MetaProperty<DeployMode> _mode = DirectMetaProperty.ofReadWrite(
        this, "mode", WebBundlesData.class, DeployMode.class);
    /**
     * The meta-property for the {@code styleTag} property.
     */
    private final MetaProperty<StyleTag> _styleTag = DirectMetaProperty.ofReadWrite(
        this, "styleTag", WebBundlesData.class, StyleTag.class);
    /**
     * The meta-property for the {@code scriptTag} property.
     */
    private final MetaProperty<ScriptTag> _scriptTag = DirectMetaProperty.ofReadWrite(
        this, "scriptTag", WebBundlesData.class, ScriptTag.class);
    /**
     * The meta-property for the {@code httpHeaders} property.
     */
    private final MetaProperty<HttpHeaders> _httpHeaders = DirectMetaProperty.ofReadWrite(
        this, "httpHeaders", WebBundlesData.class, HttpHeaders.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "bundleManagerFactory",
        "bundleManager",
        "devBundleManager",
        "compressor",
        "mode",
        "styleTag",
        "scriptTag",
        "httpHeaders");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 1984241087:  // bundleManagerFactory
          return _bundleManagerFactory;
        case 1459962059:  // bundleManager
          return _bundleManager;
        case 862647990:  // devBundleManager
          return _devBundleManager;
        case -369448763:  // compressor
          return _compressor;
        case 3357091:  // mode
          return _mode;
        case 1997897769:  // styleTag
          return _styleTag;
        case 249937615:  // scriptTag
          return _scriptTag;
        case 1649792478:  // httpHeaders
          return _httpHeaders;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends WebBundlesData> builder() {
      return new DirectBeanBuilder<WebBundlesData>(new WebBundlesData());
    }

    @Override
    public Class<? extends WebBundlesData> beanType() {
      return WebBundlesData.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code bundleManagerFactory} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<BundleManagerFactory> bundleManagerFactory() {
      return _bundleManagerFactory;
    }

    /**
     * The meta-property for the {@code bundleManager} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<BundleManager> bundleManager() {
      return _bundleManager;
    }

    /**
     * The meta-property for the {@code devBundleManager} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<BundleManager> devBundleManager() {
      return _devBundleManager;
    }

    /**
     * The meta-property for the {@code compressor} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<BundleCompressor> compressor() {
      return _compressor;
    }

    /**
     * The meta-property for the {@code mode} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<DeployMode> mode() {
      return _mode;
    }

    /**
     * The meta-property for the {@code styleTag} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<StyleTag> styleTag() {
      return _styleTag;
    }

    /**
     * The meta-property for the {@code scriptTag} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ScriptTag> scriptTag() {
      return _scriptTag;
    }

    /**
     * The meta-property for the {@code httpHeaders} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<HttpHeaders> httpHeaders() {
      return _httpHeaders;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 1984241087:  // bundleManagerFactory
          return ((WebBundlesData) bean).getBundleManagerFactory();
        case 1459962059:  // bundleManager
          return ((WebBundlesData) bean).getBundleManager();
        case 862647990:  // devBundleManager
          return ((WebBundlesData) bean).getDevBundleManager();
        case -369448763:  // compressor
          return ((WebBundlesData) bean).getCompressor();
        case 3357091:  // mode
          return ((WebBundlesData) bean).getMode();
        case 1997897769:  // styleTag
          return ((WebBundlesData) bean).getStyleTag();
        case 249937615:  // scriptTag
          return ((WebBundlesData) bean).getScriptTag();
        case 1649792478:  // httpHeaders
          return ((WebBundlesData) bean).getHttpHeaders();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 1984241087:  // bundleManagerFactory
          ((WebBundlesData) bean).setBundleManagerFactory((BundleManagerFactory) newValue);
          return;
        case 1459962059:  // bundleManager
          ((WebBundlesData) bean).setBundleManager((BundleManager) newValue);
          return;
        case 862647990:  // devBundleManager
          ((WebBundlesData) bean).setDevBundleManager((BundleManager) newValue);
          return;
        case -369448763:  // compressor
          ((WebBundlesData) bean).setCompressor((BundleCompressor) newValue);
          return;
        case 3357091:  // mode
          ((WebBundlesData) bean).setMode((DeployMode) newValue);
          return;
        case 1997897769:  // styleTag
          ((WebBundlesData) bean).setStyleTag((StyleTag) newValue);
          return;
        case 249937615:  // scriptTag
          ((WebBundlesData) bean).setScriptTag((ScriptTag) newValue);
          return;
        case 1649792478:  // httpHeaders
          ((WebBundlesData) bean).setHttpHeaders((HttpHeaders) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
