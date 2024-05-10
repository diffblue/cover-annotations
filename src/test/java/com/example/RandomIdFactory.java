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

import com.diffblue.cover.annotations.InTestsMock;
import java.util.Random;

// When testing this class, then it is ALLOWED to mock Random
@InTestsMock(Random.class)
public class RandomIdFactory {

  private final Random random;

  public RandomIdFactory(Random random) {
    this.random = random;
  }

  public long nextId() {
    return random.nextLong();
  }
}
