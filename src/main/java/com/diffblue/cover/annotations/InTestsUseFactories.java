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

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Identifies factories that should be considered when writing tests for the annotated method.
 *
 * @since Diffblue Cover 2024.10.01
 */
@Retention(RUNTIME)
@Target(METHOD)
@Repeatable(InTestsUseFactories.Repeatable.class)
public @interface InTestsUseFactories {

  /** Collects multiple {@link InTestsUseFactories} annotations. */
  @Retention(RUNTIME)
  @Target(METHOD)
  @interface Repeatable {

    /** @return the repeated {@link InTestsUseFactories} annotations. */
    InTestsUseFactories[] value();
  }

  /** @return The name of the class containing the factory methods. */
  String className();

  /** @return The method names to use for factory methods. */
  String[] methodNames();
}
