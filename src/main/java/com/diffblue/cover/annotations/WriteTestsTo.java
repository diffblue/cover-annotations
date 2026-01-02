/*
 * Copyright 2025-2026 Diffblue Limited.
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

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Directs Diffblue Cover to write tests for the annotated source class into the specified test
 * class file. This annotation allows users to explicitly specify the target test class when it
 * should not match the configured or default --class-name-template.
 *
 * <p>This annotation can only be applied at the class level.
 *
 * <p>The specified test class name must be alphanumeric. The resulting test class will be created
 * in the test folder, under the same package structure as the source class.
 *
 * <p>Example usage:
 *
 * <pre>{@code
 * package com.example.myapp;
 *
 * @WriteTestsTo("CustomTestClassName")
 * public class SourceClass {
 *     // class implementation
 * }
 * // Tests will be written to: src/test/java/com/example/myapp/CustomTestClassName.java
 * }</pre>
 *
 * @since Diffblue Cover 1.9.0
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface WriteTestsTo {

  /**
   * @return the alphanumeric name of the test class file where tests for the annotated source class
   *     should be written. The resulting test class will be placed in the test folder under the
   *     same package structure as the source class.
   */
  String value();
}
