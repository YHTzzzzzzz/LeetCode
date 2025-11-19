package com.yin.daily;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Daily20251119 {
}

class Solution20251119 {
    public int findFinalValue(int[] nums, int original) {
        Arrays.sort(nums);
        for (int num : nums) {
            if (original == num) {
                original = original << 1;
            } else if (original < num) break;
        }
        return original;
    }
}
