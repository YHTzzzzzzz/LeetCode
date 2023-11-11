package com.yin.daily;

import java.awt.*;
import java.util.Arrays;

public class Daily20231110 {
    public static void main(String[] args) {
        Solution20231110 solution = new Solution20231110();
        int[] spells = {5, 1, 3};
        int[] potions = {1, 2, 3, 4, 5};
        int[] successfulPairs = solution.successfulPairs(spells, potions, 7L);
        for (int successfulPair : successfulPairs) {
            System.out.printf(successfulPair + "\t");
        }
    }
}

class Solution20231110 {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        //sort potions
        Arrays.sort(potions);
        int length = spells.length;
        int[] arrays = new int[length];
        for (int i = 0; i < length; i++) {
            // 二分法比较
            int spell = spells[i];
            int left = 0, right = potions.length - 1;
            double cur = success * 1.0 / spell;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (potions[mid] >= cur) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            if (spell * 1L * potions[right] >= success) {
                arrays[i] = potions.length - right;
            }
        }
        return arrays;
    }
}
