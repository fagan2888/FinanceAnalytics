/**
 * Copyright (C) 2009 - 2009 by OpenGamma Inc.
 *
 * Please see distribution for license.
 */
package com.opengamma.engine.function;

import java.util.Set;

import com.opengamma.engine.value.NewComputedValue;

/**
 * 
 *
 * @author kirk
 */
public interface NewPrimitiveFunctionInvoker extends FunctionInvoker {
  
  Set<NewComputedValue> execute(
      FunctionExecutionContext executionContext,
      NewFunctionInputs inputs,
      Object target);

}
