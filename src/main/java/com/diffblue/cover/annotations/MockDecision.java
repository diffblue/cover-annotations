/*
 * Copyright 2024 Diffblue Limited. All Rights Reserved.
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

public enum MockDecision {
  /** Mocking is required, other instantiations are not permitted. */
  REQUIRED,

  /**
   * Mocking is recommended and should be used in preference to instantiation, but instantiations
   * are still permitted.
   */
  RECOMMENDED,

  /** No preference with regard to mocking, instantations may be mocked or not. */
  ALLOWED,

  /** Mocking is forbidden; instantiation must be done without mocking. */
  FORBIDDEN,
}
