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

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PACKAGE;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Identifies values that should be considered when writing tests that require inputs of type {@link
 * Enum}.
 *
 * @since Diffblue Cover 2024.06.01
 */
@Retention(RUNTIME)
@Repeatable(InTestsUseEnums.Repeatable.class)
public @interface InTestsUseEnums {

  /** Collects multiple {@link InTestsUseEnums} annotations. */
  @Retention(RUNTIME)
  @Target({PACKAGE, TYPE, METHOD, PARAMETER})
  @interface Repeatable {

    /**
     * @return the repeated {@link InTestsUseEnums} annotations.
     */
    InTestsUseEnums[] value();
  }

  /**
   * @return {@link String} values that should be tried as names of the annotated enum.
   */
  String[] value() default {};
}