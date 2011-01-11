/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.masterdb.exchange;

import static org.junit.Assert.assertEquals;

import java.util.TimeZone;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opengamma.DataNotFoundException;
import com.opengamma.id.UniqueIdentifier;
import com.opengamma.master.exchange.ExchangeDocument;

/**
 * Tests QueryExchangeDbExchangeMasterWorker.
 */
public class QueryExchangeDbExchangeMasterWorkerGetTest extends AbstractDbExchangeMasterWorkerTest {
  // superclass sets up dummy database

  private static final Logger s_logger = LoggerFactory.getLogger(QueryExchangeDbExchangeMasterWorkerGetTest.class);

  public QueryExchangeDbExchangeMasterWorkerGetTest(String databaseType, String databaseVersion) {
    super(databaseType, databaseVersion);
    s_logger.info("running testcases for {}", databaseType);
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
  }

  //-------------------------------------------------------------------------
  @Test(expected = IllegalArgumentException.class)
  public void test_getExchange_nullUID() {
    _exgMaster.get(null);
  }

  @Test(expected = DataNotFoundException.class)
  public void test_getExchange_versioned_notFound() {
    UniqueIdentifier uid = UniqueIdentifier.of("DbExg", "0", "0");
    _exgMaster.get(uid);
  }

  @Test
  public void test_getExchange_versioned_oneExchangeDate() {
    UniqueIdentifier uid = UniqueIdentifier.of("DbExg", "101", "0");
    ExchangeDocument test = _exgMaster.get(uid);
    assert101(test);
  }

  @Test
  public void test_getExchange_versioned_twoExchangeDates() {
    UniqueIdentifier uid = UniqueIdentifier.of("DbExg", "102", "0");
    ExchangeDocument test = _exgMaster.get(uid);
    assert102(test);
  }

  @Test
  public void test_getExchange_versioned_notLatest() {
    UniqueIdentifier uid = UniqueIdentifier.of("DbExg", "201", "0");
    ExchangeDocument test = _exgMaster.get(uid);
    assert201(test);
  }

  @Test
  public void test_getExchange_versioned_latest() {
    UniqueIdentifier uid = UniqueIdentifier.of("DbExg", "201", "1");
    ExchangeDocument test = _exgMaster.get(uid);
    assert202(test);
  }

  //-------------------------------------------------------------------------
  @Test(expected = DataNotFoundException.class)
  public void test_getExchange_unversioned_notFound() {
    UniqueIdentifier uid = UniqueIdentifier.of("DbExg", "0");
    _exgMaster.get(uid);
  }

  @Test
  public void test_getExchange_unversioned() {
    UniqueIdentifier oid = UniqueIdentifier.of("DbExg", "201");
    ExchangeDocument test = _exgMaster.get(oid);
    assert202(test);
  }

  //-------------------------------------------------------------------------
  @Test
  public void test_toString() {
    assertEquals(_exgMaster.getClass().getSimpleName() + "[DbExg]", _exgMaster.toString());
  }

}
