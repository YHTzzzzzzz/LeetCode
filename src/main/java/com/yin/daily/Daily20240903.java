package com.yin.daily;

import javax.crypto.spec.PSource;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 2708. 一个小组的最大实力值
 * 中等
 * 相关标签
 * 相关企业
 * 提示
 * 给你一个下标从 0 开始的整数数组 nums ，它表示一个班级中所有学生在一次考试中的成绩。老师想选出一部分同学组成一个 非空 小组，且这个小组的 实力值 最大，如果这个小组里的学生下标为 i0, i1, i2, ... , ik ，那么这个小组的实力值定义为 nums[i0] * nums[i1] * nums[i2] * ... * nums[ik​] 。
 * <p>
 * 请你返回老师创建的小组能得到的最大实力值为多少。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [3,-1,-5,2,5,-9]
 * 输出：1350
 * 解释：一种构成最大实力值小组的方案是选择下标为 [0,2,3,4,5] 的学生。实力值为 3 * (-5) * 2 * 5 * (-9) = 1350 ，这是可以得到的最大实力值。
 * 示例 2：
 * <p>
 * 输入：nums = [-4,-5,-4]
 * 输出：20
 * 解释：选择下标为 [0, 1] 的学生。得到的实力值为 20 。我们没法得到更大的实力值。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 13
 * -9 <= nums[i] <= 9
 */
public class Daily20240903 {
    public static void main(String[] args) {
        Solution20240903 solution20240903 = new Solution20240903();
//        System.out.println(solution20240903.maxStrength(new int[]{3, -1, -5, 2, 5, -9}));
        System.out.println(solution20240903.maxStrength(new int[]{8, 6, 0, 5, -4, -8, -4, 9, -1, 6, -4, 8, -5}));
    }
}

class Solution20240903 {

    public long maxStrength(int[] nums) {
        // 将数组分类 负数 正数 zero
        int n = nums.length;
        List<Integer> negative = new ArrayList<>();
        List<Integer> positive = new ArrayList<>();
        int maxNegative = Integer.MIN_VALUE;
        boolean hasZero = false;
        for (int num : nums) {
            if (num < 0) {
                maxNegative = Math.max(maxNegative, num);
                negative.add(num);
            } else if (num == 0) {
                hasZero = true;
            } else {
                positive.add(num);
            }
        }
        long res = 1;
        if (positive.isEmpty()) {
            if (negative.size() <= 1) {
                return hasZero ? 0 : negative.get(0);
            }
        } else {
            for (Integer i : positive) {
                res *= i;
            }
        }
        if (negative.size() > 1) {
            for (Integer i : negative) {
                res *= i;
            }
        }
        if (res < 0) {
            res /= maxNegative;
        }
        return res;
    }

    /**
     * 不选 nums[i] 乘积就是 【0， i - 1】
     * 选 nums[i] 考虑
     * 1. nums[i] 作为最大值
     * 2. nums[i] 为正数 与前面的最大乘积相乘
     * 3. nums[i] 为负数 与前面的最小乘积相乘
     * <p>
     * mn = min(mn, x, mn * x, mx * x)
     * mx = max(mx, x, mx * x, mn * x)
     */
    public long maxStrength2(int[] nums) {
        int n = nums.length;
        long mn = nums[0], mx = mn;
        for (int i = 1; i < nums.length; i++) {
            long x = nums[i];
            long temp = mn;
            mn = Math.min(Math.min(mn, x), Math.min(mn * x, mx * x));
            mx = Math.max(Math.max(mx, x), Math.max(temp * x, mx * x));
        }
        return mx;
    }
}
