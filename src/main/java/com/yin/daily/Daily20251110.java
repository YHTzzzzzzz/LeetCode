package com.yin.daily;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 每次操作选一个区间 [i, j]，把其中的 最小非负整数（即该子数组中最小的正数）设为0，并保持其他元素不变。
 * 我们要最小化操作次数。
 */
public class Daily20251110 {
}

class Solution20251110{
    public int minOperations(int[] nums) {
        Deque<Integer> stack = new ArrayDeque<>();
        int res = 0;
        for (int num : nums) {
            while (!stack.isEmpty() && stack.peek() > num) {
                stack.pop();
            }
            if (num == 0) continue;
            if (stack.isEmpty() || stack.peek() < num) {
                res++;
                stack.push(num);
            }
        }
        return res;
    }
}
