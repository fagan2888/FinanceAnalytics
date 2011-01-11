/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.master.portfolio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.joda.beans.BeanDefinition;
import org.joda.beans.MetaProperty;

import com.opengamma.master.AbstractSearchResult;

/**
 * Result from searching for portfolio trees.
 * <p>
 * The returned documents will match the search criteria.
 * See {@link PortfolioSearchRequest} for more details.
 * <p>
 * This returns multiple instances of the tree excluding positions, which may be a large response.
 * The depth parameter in the request allows the size of the result to be controlled.
 */
@BeanDefinition
public class PortfolioSearchResult extends AbstractSearchResult<PortfolioDocument> {

  /**
   * Creates an instance.
   */
  public PortfolioSearchResult() {
  }

  //-------------------------------------------------------------------------
  /**
   * Gets the returned portfolios from within the documents.
   * 
   * @return the portfolios, not null
   */
  public List<ManageablePortfolio> getPortfolios() {
    List<ManageablePortfolio> result = new ArrayList<ManageablePortfolio>();
    if (getDocuments() != null) {
      for (PortfolioDocument doc : getDocuments()) {
        result.add(doc.getPortfolio());
      }
    }
    return result;
  }

  /**
   * Gets the first portfolio, or null if no documents.
   * 
   * @return the first portfolio, null if none
   */
  public ManageablePortfolio getFirstPortfolio() {
    return getDocuments().size() > 0 ? getDocuments().get(0).getPortfolio() : null;
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code PortfolioSearchResult}.
   * @return the meta-bean, not null
   */
  @SuppressWarnings("unchecked")
  public static PortfolioSearchResult.Meta meta() {
    return PortfolioSearchResult.Meta.INSTANCE;
  }

  @Override
  public PortfolioSearchResult.Meta metaBean() {
    return PortfolioSearchResult.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName) {
    switch (propertyName.hashCode()) {
    }
    return super.propertyGet(propertyName);
  }

  @Override
  protected void propertySet(String propertyName, Object newValue) {
    switch (propertyName.hashCode()) {
    }
    super.propertySet(propertyName, newValue);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code PortfolioSearchResult}.
   */
  public static class Meta extends AbstractSearchResult.Meta<PortfolioDocument> {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<Object>> _map;

    @SuppressWarnings({"unchecked", "rawtypes" })
    protected Meta() {
      LinkedHashMap temp = new LinkedHashMap(super.metaPropertyMap());
      _map = Collections.unmodifiableMap(temp);
    }

    @Override
    public PortfolioSearchResult createBean() {
      return new PortfolioSearchResult();
    }

    @Override
    public Class<? extends PortfolioSearchResult> beanType() {
      return PortfolioSearchResult.class;
    }

    @Override
    public Map<String, MetaProperty<Object>> metaPropertyMap() {
      return _map;
    }

    //-----------------------------------------------------------------------
  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
