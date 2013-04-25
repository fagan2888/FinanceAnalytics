/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.timeseries.date.localdate;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

import org.threeten.bp.LocalDate;

import com.opengamma.timeseries.date.DateDoubleTimeSeries;

/**
 * Builder implementation for {@code ImmutableLocalDateDoubleTimeSeries}.
 */
final class ImmutableLocalDateDoubleTimeSeriesBuilder
    implements LocalDateDoubleTimeSeriesBuilder {

  /**
   * The current size.
   */
  private int _size;
  /**
   * The times.
   */
  private int[] _times = new int[128];
  /**
   * The values.
   */
  private double[] _values = new double[128];

  /**
   * Creates an instance.
   */
  ImmutableLocalDateDoubleTimeSeriesBuilder() {
  }

  //-------------------------------------------------------------------------
  private void ensureCapacity(int newSize) {
    if (newSize > _times.length) {
      newSize = Math.max(newSize + 8, (_size * 3) / 2);
      _times = Arrays.copyOf(_times, newSize);
      _values = Arrays.copyOf(_values, _size * 2);
    }
  }

  private static int convertToInt(LocalDate date) {
    return LocalDateToIntConverter.convertToInt(date);
  }

  private static LocalDate convertFromInt(int date) {
    return LocalDateToIntConverter.convertToLocalDate(date);
  }

  //-------------------------------------------------------------------------
  @Override
  public int size() {
    return _size;
  }

  @Override
  public LocalDateDoubleEntryIterator iterator() {
    return new LocalDateDoubleEntryIterator() {
      private int _index = -1;

      @Override
      public boolean hasNext() {
        return (_index + 1) < size();
      }

      @Override
      public Entry<LocalDate, Double> next() {
        if (hasNext() == false) {
          throw new NoSuchElementException("No more elements in the iteration");
        }
        _index++;
        int date = _times[_index];
        double value = _values[_index];
        return makeMapEntry(convertFromInt(date), value);
      }

      private Entry<LocalDate, Double> makeMapEntry(LocalDate key, Double value) {
        return new SimpleImmutableEntry<>(key, value);
      }

      @Override
      public int nextTimeFast() {
        if (hasNext() == false) {
          throw new NoSuchElementException("No more elements in the iteration");
        }
        _index++;
        return _times[_index];
      }

      @Override
      public LocalDate nextTime() {
        return convertFromInt(nextTimeFast());
      }

      @Override
      public int currentTimeFast() {
        if (_index < 0) {
          throw new IllegalStateException("Iterator has not yet been started");
        }
        return _times[_index];
      }

      @Override
      public LocalDate currentTime() {
        return convertFromInt(currentTimeFast());
      }

      @Override
      public Double currentValue() {
        if (_index < 0) {
          throw new IllegalStateException("Iterator has not yet been started");
        }
        return _values[_index];
      }

      @Override
      public double currentValueFast() {
        if (_index < 0) {
          throw new IllegalStateException("Iterator has not yet been started");
        }
        return _values[_index];
      }

      @Override
      public int currentIndex() {
        return _index;
      }

      @Override
      public void remove() {
        if (_index < 0) {
          throw new IllegalStateException("Iterator has not yet been started");
        }
        if (_index < _size) {
          System.arraycopy(_times, _index + 1, _times, _index, _size - _index);
          System.arraycopy(_values, _index + 1, _values, _index, _size - _index);
        }
        _size--;
        _index--;
      }
    };
  }

  //-------------------------------------------------------------------------
  @Override
  public LocalDateDoubleTimeSeriesBuilder put(LocalDate time, double value) {
    return put(convertToInt(time), value);
  }

  @Override
  public LocalDateDoubleTimeSeriesBuilder put(int time, double value) {
    int search = Arrays.binarySearch(_times, 0, _size, time);
    if (search >= 0) {
      _values[search] = value;
    } else {
      ensureCapacity(_size + 1);
      int pos = -(search + 1);
      System.arraycopy(_times, pos, _times, pos + 1, _size - pos);
      System.arraycopy(_values, pos, _values, pos + 1, _size - pos);
      _times[pos] = time;
      _values[pos] = value;
      _size++;
    }
    return this;
  }

  @Override
  public LocalDateDoubleTimeSeriesBuilder putAll(LocalDate[] times, double[] values) {
    if (times.length != values.length) {
      throw new IllegalArgumentException("Arrays are of different sizes: " + times.length + ", " + values.length);
    }
    ensureCapacity(_size + times.length);
    for (int i = 0; i < times.length; i++) {
      put(times[i], values[i]);
    }
    return this;
  }

  @Override
  public LocalDateDoubleTimeSeriesBuilder putAll(int[] times, double[] values) {
    if (times.length != values.length) {
      throw new IllegalArgumentException("Arrays are of different sizes: " + times.length + ", " + values.length);
    }
    ensureCapacity(_size + times.length);
    for (int i = 0; i < times.length; i++) {
      put(times[i], values[i]);
    }
    return this;
  }

  //-------------------------------------------------------------------------
  @Override
  public LocalDateDoubleTimeSeriesBuilder putAll(DateDoubleTimeSeries<?> timeSeries) {
    return putAll(timeSeries, 0, timeSeries.size());
  }

  @Override
  public LocalDateDoubleTimeSeriesBuilder putAll(DateDoubleTimeSeries<?> timeSeries, int startPos, int endPos) {
    if (startPos < 0 || startPos > timeSeries.size()) {
      throw new IndexOutOfBoundsException("Invalid start index: " + startPos);
    }
    if (endPos < 0 || endPos > timeSeries.size()) {
      throw new IndexOutOfBoundsException("Invalid end index: " + endPos);
    }
    if (startPos > endPos) {
      throw new IndexOutOfBoundsException("End index not be less than start index");
    }
    if (startPos == endPos) {
      return this;
    }
    int sizeToAdd = endPos - startPos;
    ensureCapacity(_size + sizeToAdd);
    if (_size == 0) {
      System.arraycopy(timeSeries.timesArrayFast(), startPos, _times, 0, sizeToAdd);
      System.arraycopy(timeSeries.valuesArrayFast(), startPos, _values, 0, sizeToAdd);
      _size = sizeToAdd;
    } else {
      for (int i = startPos; i < endPos; i++) {
        put(timeSeries.getTimeAtIndexFast(i), timeSeries.getValueAtIndexFast(i));
      }
    }
    return this;
  }

  @Override
  public LocalDateDoubleTimeSeriesBuilder putAll(Map<LocalDate, Double> timeSeriesMap) {
    if (timeSeriesMap.size() == 0) {
      return this;
    }
    ensureCapacity(_size + timeSeriesMap.size());
    for (Entry<LocalDate, Double> entry : timeSeriesMap.entrySet()) {
      put(entry.getKey(), entry.getValue());
    }
    return this;
  }

  //-------------------------------------------------------------------------
  @Override
  public LocalDateDoubleTimeSeriesBuilder clear() {
    _size = 0;
    return this;
  }

  //-------------------------------------------------------------------------
  @Override
  public ImmutableLocalDateDoubleTimeSeries build() {
    if (_size == 0) {
      return ImmutableLocalDateDoubleTimeSeries.EMPTY_SERIES;
    }
    return new ImmutableLocalDateDoubleTimeSeries(Arrays.copyOf(_times, _size), Arrays.copyOf(_values, _size));
  }

  //-------------------------------------------------------------------------
  @Override
  public String toString() {
    return "Builder[size=" + _size + "]";
  }

}
