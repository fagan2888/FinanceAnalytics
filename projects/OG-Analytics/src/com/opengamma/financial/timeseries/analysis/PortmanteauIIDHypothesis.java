/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.financial.timeseries.analysis;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opengamma.math.function.Function1D;
import com.opengamma.math.statistics.distribution.ChiSquareDistribution;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.timeseries.DoubleTimeSeries;

/**
 * 
 */
public class PortmanteauIIDHypothesis extends IIDHypothesis {
  private static final Logger s_logger = LoggerFactory.getLogger(PortmanteauIIDHypothesis.class);
  private final Function1D<DoubleTimeSeries<?>, double[]> _calculator = new AutocorrelationFunctionCalculator();
  private final double _criticalValue;
  private final int _h;

  public PortmanteauIIDHypothesis(final double level, final int maxLag) {
    if (!ArgumentChecker.isInRangeExcludingLow(0, 1, level)) {
      throw new IllegalArgumentException("Level must be between 0 and 1");
    }
    if (maxLag == 0) {
      throw new IllegalArgumentException("Lag cannot be zero");
    }
    if (maxLag < 0) {
      s_logger.warn("Maximum lag was less than zero; using absolute value");
    }
    _h = Math.abs(maxLag);
    _criticalValue = new ChiSquareDistribution(_h).getInverseCDF(1 - level);
  }

  @Override
  public boolean testIID(final DoubleTimeSeries<?> x) {
    Validate.notNull(x, "x");
    if (x.size() < _h) {
      throw new IllegalArgumentException("Time series must have at least " + _h + " points");
    }
    final double[] autocorrelation = _calculator.evaluate(x.toFastLongDoubleTimeSeries());
    double q = 0;
    for (int i = 1; i < _h; i++) {
      q += autocorrelation[i] * autocorrelation[i];
    }
    q *= x.size();
    return q < _criticalValue;
  }

}
