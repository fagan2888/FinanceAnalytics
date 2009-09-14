/**
 * Copyright (C) 2009 - 2009 by OpenGamma Inc.
 *
 * Please see distribution for license.
 */
package com.opengamma.engine.view;

import com.opengamma.engine.position.PortfolioNode;

/**
 * 
 *
 * @author kirk
 */
public interface View {

  PortfolioNode getPositionRoot();
  
  ViewComputationResultModel getMostRecentResult();
}
