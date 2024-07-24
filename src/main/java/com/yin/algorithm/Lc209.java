package com.yin.algorithm;

/**
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 *
 * 找出该数组中满足其总和 *大于等于* target 的长度最小的
 * 子数组
 *  [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 * 示例 2：
 *
 * 输入：target = 4, nums = [1,4,4]
 * 输出：1
 * 示例 3：
 *
 * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
 * 输出：0
 *
 * 提示：
 *
 * 1 <= target <= 10^9
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 */
public class Lc209 {
    public static void main(String[] args) {

        System.out.println(new Solution209().minSubArrayLen(11, new int[]{1,2,3,4,5}));
    }
}

class Solution209 {
    /**
     * 暴力解法
     * 超出时间限制
     */
    public int minSubArrayLenBL(int target, int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            // 以 i 为左端点
            int sum = nums[i];
            if (sum >= target) {
                return 1;
            }
            for (int j = i + 1; j < nums.length; j++) {
                sum += nums[j];
                if (sum >= target) {
                    min = Math.min(min, j - i + 1);
                    break;
                }
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int left = 0, sum = 0, ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            while (sum >= target) {
                ans = Math.min(ans, i - left + 1);
                sum -= nums[left++];
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}