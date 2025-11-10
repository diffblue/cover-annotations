/*
 * Copyright 2025 Diffblue Limited.
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
package com.diffblue.cover.annotations.experimental;

import static java.lang.annotation.ElementType.PACKAGE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * This annotation is used to specify that a test requires the usage
 * of a specific Spring context during testing. It allows the XML file locations of the
 * Spring context to be specified.
 *
 * @deprecated This annotation is experimental and may change or be removed in future releases. Its
 *     use is discouraged except for internal experimental purposes.
 */
@Retention(RUNTIME)
@Target({PACKAGE, TYPE})
@Deprecated
public @interface InTestsUseSpringContext {

  /**
   * Specifies the locations of XML configuration files for the Spring context
   * required by a test.
   */
  String[] locations();
}
