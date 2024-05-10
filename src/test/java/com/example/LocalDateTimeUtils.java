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

import com.diffblue.cover.annotations.InTestsMockStatic;
import com.diffblue.cover.annotations.MockDecision;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

public class LocalDateTimeUtils {

  // When testing this method, then it is RECOMMENDED to statically mock LocalDate and LocalTime,
  // but never ZoneId
  @InTestsMockStatic(
      value = {LocalDate.class, LocalTime.class},
      decision = MockDecision.RECOMMENDED)
  @InTestsMockStatic(value = ZoneId.class, decision = MockDecision.FORBIDDEN)
  public LocalDateTime parse(String date, String time) {
    return LocalDateTime.of(LocalDate.parse(date), LocalTime.parse(time));
  }
}
