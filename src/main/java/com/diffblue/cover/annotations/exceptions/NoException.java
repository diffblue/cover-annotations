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
package com.diffblue.cover.annotations.exceptions;

/**
 * Marker class used to indicate that no exception should be thrown by default in {@link
 * com.diffblue.cover.annotations.InTestsMock#throwException()}.
 */
public final class NoException extends Throwable {
  private NoException() {
    // This class should not be instantiated.
  }
}
