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

package com.example;

import com.diffblue.cover.annotations.InTestsMockConstruction;

public class ConstructionExample {

  static class ClassToMock {}

  // When testing this method, then it is RECOMMENDED to mock construction of ClassToMock
  @InTestsMockConstruction(ConstructionExample.ClassToMock.class)
  public static String exampleMethodUsingConstructor() {
    return new ClassToMock().toString();
  }
}
