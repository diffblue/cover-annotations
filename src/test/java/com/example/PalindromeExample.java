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

import com.diffblue.cover.annotations.InTestsUseLongs;
import com.diffblue.cover.annotations.InTestsUseStrings;

public class PalindromeExample {
  @InTestsUseLongs({1L, 123454321L, 12345L})
  public boolean isPalindrome(long value) {
    return isPalindrome(Long.toString(value));
  }

  @InTestsUseStrings({"Madam I'm Adam", "Nurses run", "Clearly not a palindrome!"})
  public boolean isPalindrome(String text) {
    int left = 0, right = text.length() - 1;
    while (left < right) {
      final char leftChar = text.charAt(left);
      if (!Character.isLetterOrDigit(leftChar)) {
        ++left;
        continue;
      }
      final char rightChar = text.charAt(right);
      if (!Character.isLetterOrDigit(rightChar)) {
        --right;
        continue;
      }
      if (Character.toLowerCase(leftChar) == Character.toLowerCase(rightChar)) {
        ++left;
        --right;
        continue;
      }
      return false;
    }
    return true;
  }
}
