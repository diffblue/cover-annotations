/*
 * Copyright 2024 Diffblue Limited.
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
import static java.lang.annotation.RetentionPolicy.CLASS;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Indicates whether tests of the annotated item should mock the classes identified by {@link
 * #value()}. Without annotation then mocking is assumed to be {@link MockDecision#ALLOWED}, but
 * with annotation then the decision defaults to {@link MockDecision#RECOMMENDED}. The decision can
 * be overridden with an explicit {@link #decision()} value.
 */
@Retention(CLASS)
@Target({PACKAGE, TYPE, METHOD})
@Repeatable(InTestsMock.Repeatable.class)
public @interface InTestsMock {

  /** Collects multiple {@link InTestsMock} annotations. */
  @Retention(CLASS)
  @Target({PACKAGE, TYPE, METHOD})
  @interface Repeatable {

    /**
     * @return the repeated {@link InTestsMock} annotations.
     */
    InTestsMock[] value();
  }

  /**
   * @return the classes to mock (or not).
   */
  Class<?>[] value();

  /**
   * @return the mocking decision to apply.
   */
  MockDecision decision() default RECOMMENDED;
}
