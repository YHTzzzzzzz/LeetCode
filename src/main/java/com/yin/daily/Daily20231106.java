package com.yin.daily;

import java.util.Arrays;
import java.util.Comparator;

public class Daily20231106 {
    public static void main(String[] args) {
        String[] words = new String[] {"a", "ab", "abc", "d", "cd", "bcd", "abcd"};
        Solution20231106 solution20231106 = new Solution20231106();
        solution20231106.maxProduct(words);
    }
}

class Solution20231106 {
    public int maxProduct(String[] words) {
        int length = words.length;
        int[] masks = new int[length];
        for (int i = 0; i < length; i++) {
            String word = words[i];
            int wordLength = word.length();
            for (int j = 0; j < wordLength; j++) {
                masks[i] |= 1 << (word.charAt(j) - 'a');
            }
        }
        int maxProd = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if ((masks[i] & masks[j]) == 0) {
                    maxProd = Math.max(maxProd, words[i].length() * words[j].length());
                }
            }
        }
        return maxProd;
    }
}
