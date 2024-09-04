package com.yin.algorithm;

/**
 * 713. 乘积小于 K 的子数组
 * 中等
 * 相关标签
 * 相关企业
 * 提示
 * 给你一个整数数组 nums 和一个整数 k ，请你返回子数组内所有元素的乘积严格小于 k 的连续子数组的数目。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [10,5,2,6], k = 100
 * 输出：8
 * 解释：8 个乘积小于 100 的子数组分别为：[10]、[5]、[2]、[6]、[10,5]、[5,2]、[2,6]、[5,2,6]。
 * 需要注意的是 [10,5,2] 并不是乘积小于 100 的子数组。
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,3], k = 0
 * 输出：0
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= nums.length <= 3 * 104
 * 1 <= nums[i] <= 1000
 * 0 <= k <= 106
 */
public class Lc713 {
    public static void main(String[] args) {
        System.out.println(new Solution713().numSubarrayProductLessThanKPrefer(new int[]{10, 5, 2, 6}, 100));
    }
}

class Solution713 {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        // 从左端点开始
        int left = 0;
        int res = 0;
        while (left < nums.length) {
            // 定义乘积
            int mult = 1;
            for (int i = left; i < nums.length; i++) {
                mult *= nums[i];
                if (mult >= k) break;
                res++;
            }
            left++;
        }
        return res;
    }

    public int numSubarrayProductLessThanKPrefer(int[] nums, int k) {
        // 从左端点开始
        if (k <= 1) return 0;
        int left = 0;
        int ans = 0;
        int prod = 1;
        for (int right = left; right < nums.length; right++) {
            prod *= nums[right];
            while (prod >= k) {
                prod /= nums[left++];
            }
            ans += right - left + 1;
        }
        return ans;
    }
}
