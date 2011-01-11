/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.model.equity;

import java.util.Set;

import com.opengamma.core.position.PortfolioNode;
import com.opengamma.core.position.Position;
import com.opengamma.core.position.Trade;
import com.opengamma.core.position.impl.PositionAccumulator;
import com.opengamma.engine.ComputationTarget;
import com.opengamma.engine.ComputationTargetType;
import com.opengamma.engine.function.FunctionCompilationContext;
import com.opengamma.financial.analytics.model.pnl.AbstractPortfolioPnLFunction;
import com.opengamma.financial.security.equity.EquitySecurity;

/**
 * 
 */
public class PortfolioEquityPnLFunction extends AbstractPortfolioPnLFunction {
  
  @Override
  public boolean canApplyTo(FunctionCompilationContext context, ComputationTarget target) {
    if (target.getType() == ComputationTargetType.PORTFOLIO_NODE) {
      final PortfolioNode node = target.getPortfolioNode();
      final Set<Position> allPositions = PositionAccumulator.getAccumulatedPositions(node);
      for (Position position : allPositions) {
        if (position.getSecurity() instanceof EquitySecurity) {
          for (Trade trade : position.getTrades()) {
            if (!(trade.getSecurity() instanceof EquitySecurity)) {
              return false;
            }
          }
          return true;
        } 
      }
    }
    return false;
  }
  
  @Override
  public String getShortName() {
    return "PortfolioDailyEquityPnL";
  }
  
}
