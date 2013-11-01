/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.master.convention;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.master.AbstractHistoryResult;
import com.opengamma.util.PublicSPI;

/**
 * Result providing the history of a convention.
 * <p>
 * The returned documents may be a mixture of versions and corrections.
 * The document instant fields are used to identify which are which.
 * See {@link ConventionHistoryRequest} for more details.
 */
@PublicSPI
@BeanDefinition
public class ConventionHistoryResult extends AbstractHistoryResult<ConventionDocument> {

  /**
   * Creates an instance.
   */
  public ConventionHistoryResult() {
  }

  /**
   * Creates an instance from a collection of documents.
   * 
   * @param coll  the collection of documents to add, not null
   */
  public ConventionHistoryResult(Collection<ConventionDocument> coll) {
    super(coll);
  }

  //-------------------------------------------------------------------------
  /**
   * Gets the returned conventions from within the documents.
   * 
   * @return the conventions, not null
   */
  public List<ManageableConvention> getConventions() {
    List<ManageableConvention> result = new ArrayList<ManageableConvention>();
    if (getDocuments() != null) {
      for (ConventionDocument doc : getDocuments()) {
        result.add(doc.getConvention());
      }
    }
    return result;
  }

  /**
   * Gets the first convention, or null if no documents.
   * 
   * @return the first convention, null if none
   */
  public ManageableConvention getFirstConvention() {
    return getDocuments().size() > 0 ? getDocuments().get(0).getConvention() : null;
  }

  /**
   * Gets the single result expected from a query.
   * <p>
   * This throws an exception if more than 1 result is actually available.
   * Thus, this method implies an assumption about uniqueness of the queried convention.
   * 
   * @return the matching convention, not null
   * @throws IllegalStateException if no convention was found
   */
  public ManageableConvention getSingleConvention() {
    if (getDocuments().size() != 1) {
      throw new OpenGammaRuntimeException("Expecting zero or single resulting match, and was " + getDocuments().size());
    } else {
      return getDocuments().get(0).getConvention();
    }
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code ConventionHistoryResult}.
   * @return the meta-bean, not null
   */
  public static ConventionHistoryResult.Meta meta() {
    return ConventionHistoryResult.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(ConventionHistoryResult.Meta.INSTANCE);
  }

  @Override
  public ConventionHistoryResult.Meta metaBean() {
    return ConventionHistoryResult.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  @Override
  public ConventionHistoryResult clone() {
    return (ConventionHistoryResult) super.clone();
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
    buf.append("ConventionHistoryResult{");
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
   * The meta-bean for {@code ConventionHistoryResult}.
   */
  public static class Meta extends AbstractHistoryResult.Meta<ConventionDocument> {
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
    public BeanBuilder<? extends ConventionHistoryResult> builder() {
      return new DirectBeanBuilder<ConventionHistoryResult>(new ConventionHistoryResult());
    }

    @Override
    public Class<? extends ConventionHistoryResult> beanType() {
      return ConventionHistoryResult.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
