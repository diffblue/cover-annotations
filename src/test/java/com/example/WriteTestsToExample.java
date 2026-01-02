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
package com.example;

import com.diffblue.cover.annotations.WriteTestsTo;

/**
 * Example demonstrating the use of the {@link WriteTestsTo} annotation.
 *
 * <p>This class uses the @WriteTestsTo annotation to direct Diffblue Cover to write tests into a
 * custom test class named "CustomTestClass" rather than following the default naming template.
 *
 * <p>The resulting test class will be created at: src/test/java/com/example/CustomTestClass.java
 */
@WriteTestsTo("CustomTestClass")
public class WriteTestsToExample {

  public static String greet(String name) {
    if (name == null || name.isEmpty()) {
      return "Hello, World!";
    }
    return "Hello, " + name + "!";
  }

  public static int add(int a, int b) {
    return a + b;
  }
}
