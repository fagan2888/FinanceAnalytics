/**
 * Copyright (C) 2009 - 2009 by OpenGamma Inc.
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.security.option;

import org.fudgemsg.FudgeFieldContainer;
import org.fudgemsg.MutableFudgeFieldContainer;
import org.fudgemsg.mapping.FudgeDeserializationContext;
import org.fudgemsg.mapping.FudgeSerializationContext;

import com.opengamma.financial.Currency;
import com.opengamma.id.Identifier;
import com.opengamma.util.time.Expiry;

/**
 * An equity option security.
 */
public abstract class EquityOptionSecurity extends ExchangeTradedOptionSecurity {

  /**
   * The security type.
   */
  public static final String EQUITY_OPTION_TYPE = "EQUITY_OPTION";

  // TODO: jim 23-Sep-2009 -- Add support for regions/countries

  /**
   * Creates the security.
   * @param optionType
   * @param strike
   * @param expiry
   * @param underlyingIdentifier
   * @param currency
   * @param exchange
   */
  public EquityOptionSecurity(final OptionType optionType, final double strike, final Expiry expiry,
      final Identifier underlyingIdentifier, final Currency currency, final double pointValue, final String exchange) {
    super(EQUITY_OPTION_TYPE, optionType, strike, expiry, underlyingIdentifier, currency, pointValue, exchange);
  }

  //-------------------------------------------------------------------------
  public abstract <T> T accept(EquityOptionSecurityVisitor<T> visitor);

  @Override
  public final <T> T accept(final ExchangeTradedOptionSecurityVisitor<T> visitor) {
    return accept((EquityOptionSecurityVisitor<T>) visitor);
  }

  protected void toFudgeMsg(final FudgeSerializationContext context, final MutableFudgeFieldContainer message) {
    super.toFudgeMsg(context, message);
    // No additional fields
  }

  protected void fromFudgeMsgImpl(final FudgeDeserializationContext context, final FudgeFieldContainer message) {
    super.fromFudgeMsgImpl(context, message);
    // No additional fields
  }

}
