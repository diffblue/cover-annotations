/*
 * Copyright 2024-2025 Diffblue Limited.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.diffblue.cover.annotations;

import static com.diffblue.cover.annotations.MockDecision.RECOMMENDED;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PACKAGE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.diffblue.cover.annotations.exceptions.NoException;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Indicates whether tests of the annotated item should mock the classes identified by {@link
 * #value()}. Without annotation then mocking is assumed to be {@link MockDecision#ALLOWED}, but
 * with annotation then the decision defaults to {@link MockDecision#RECOMMENDED}. The decision can
 * be overridden with an explicit {@link #decision()} value.
 *
 * @since Diffblue Cover 2024.04.02
 */
@Retention(RUNTIME)
@Target({PACKAGE, TYPE, METHOD})
@Repeatable(InTestsMock.Repeatable.class)
public @interface InTestsMock {

  /** Collects multiple {@link InTestsMock} annotations. */
  @Retention(RUNTIME)
  @Target({PACKAGE, TYPE, METHOD})
  @interface Repeatable {

    /** @return the repeated {@link InTestsMock} annotations. */
    InTestsMock[] value();
  }

  /** @return the classes to mock (or not). */
  Class<?>[] value();

  /** @return the mocking decision to apply. */
  MockDecision decision() default RECOMMENDED;

  /** @return name of method to mock */
  String method() default "";

  /** @return boolean value or values to return from the {@link #method()} */
  boolean[] booleanReturnValues() default {};

  /** @return byte value or values to return from the {@link #method()} */
  byte[] byteReturnValues() default {};

  /** @return char value or values to return from the {@link #method()} */
  char[] charReturnValues() default {};

  /** @return float value or values to return from the {@link #method()} */
  float[] floatReturnValues() default {};

  /** @return double value or values to return from the {@link #method()} */
  double[] doubleReturnValues() default {};

  /** @return int value or values to return from the {@link #method()} */
  int[] intReturnValues() default {};

  /** @return long value or values to return from the {@link #method()} */
  long[] longReturnValues() default {};

  /** @return short value or values to return from the {@link #method()} */
  short[] shortReturnValues() default {};

  /** @return String value or values to return from the {@link #method()} */
  String[] stringReturnValues() default {};

  /**
   * @return name of the factory method used to create the object returned by the mocked {@link
   *     #method()}
   */
  String returnValueFactory() default "";

  /**
   * @return exception type to throw when the mocked {@link #method()} is called. Defaults to {@link
   *     NoException} to indicate no exception should be thrown.
   */
  Class<? extends Throwable> throwException() default NoException.class;

  /**
   * @return fully qualified name of the factory method used to create the exception to throw when
   *     the mocked {@link #method()} is called.
   */
  String throwExceptionFactory() default "";
}
