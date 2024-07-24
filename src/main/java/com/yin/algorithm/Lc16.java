package com.yin.algorithm;

import java.util.Arrays;

/**
 * 16. 最接近的三数之和
 * 中等
 * 相关标签
 * 相关企业
 * 给你一个长度为 n 的整数数组 nums 和 一个目标值 target。请你从 nums 中选出三个整数，使它们的和与 target 最接近。
 *
 * 返回这三个数的和。
 *
 * 假定每组输入只存在恰好一个解。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 * 示例 2：
 *
 * 输入：nums = [0,0,0], target = 1
 * 输出：0
 *
 *
 * 提示：
 *
 * 3 <= nums.length <= 1000
 * -1000 <= nums[i] <= 1000
 * -104 <= target <= 104
 */
public class Lc16 {
    public static void main(String[] args) {

        System.out.println(new Solution16().threeSumClosest(new int[]{4,0,5,-5,3,3,0,-4,-5}, -2));
    }
}

class Solution16 {
    public int threeSumClosest(int[] nums, int target) {
        // 排序
        Arrays.sort(nums);
        int left = 0, right = nums.length - 1, abs = Integer.MAX_VALUE, ans = 0;
        while (left + 1 < right) {
            int sum = nums[left] + nums[left + 1] + nums[right];
            int abs1 = Math.abs(sum - target);
            if (abs1 < abs) {
                ans = sum;
                abs = abs1;
            }
            if (sum == target) {
                return sum;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return ans;
    }
}
