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
    public int threeSumClosestError(int[] nums, int target) {
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

    public int threeSumClosest(int[] nums, int target) {
        // 排序
        Arrays.sort(nums);
        int abs = Integer.MAX_VALUE, ans = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            int j = i + 1, k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                int abs1 = Math.abs(sum - target);
                if (abs1 < abs) {
                    ans = sum;
                    abs = abs1;
                }
                if (sum == target) {
                    return sum;
                } else if (sum < target) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        return ans;
    }

    // 贴一段灵茶山艾府的代码，有几个优化点
    public int threeSumClosestPrefer(int[] nums, int target) {
        Arrays.sort(nums);
        int ans = 0, n = nums.length;
        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i < n - 2; i++) {
            int x = nums[i];
            if (i > 0 && x == nums[i - 1]) {
                continue; // 优化三
            }

            // 优化一
            int s = x + nums[i + 1] + nums[i + 2];
            if (s > target) { // 后面无论怎么选，选出的三个数的和不会比 s 还小
                if (s - target < minDiff) {
                    ans = s; // 由于下面直接 break，这里无需更新 minDiff
                }
                break;
            }

            // 优化二
            s = x + nums[n - 2] + nums[n - 1];
            if (s < target) { // x 加上后面任意两个数都不超过 s，所以下面的双指针就不需要跑了
                if (target - s < minDiff) {
                    minDiff = target - s;
                    ans = s;
                }
                continue;
            }

            // 双指针
            int j = i + 1, k = n - 1;
            while (j < k) {
                s = x + nums[j] + nums[k];
                if (s == target) {
                    return target;
                }
                if (s > target) {
                    if (s - target < minDiff) { // s 与 target 更近
                        minDiff = s - target;
                        ans = s;
                    }
                    k--;
                } else { // s < target
                    if (target - s < minDiff) { // s 与 target 更近
                        minDiff = target - s;
                        ans = s;
                    }
                    j++;
                }
            }
        }
        return ans;
    }
}
