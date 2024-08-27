package com.yin.daily;

import java.util.HashMap;
import java.util.Map;

/**
 * 3134. 找出唯一性数组的中位数
 * 困难
 * 相关标签
 * 相关企业
 * 提示
 * 给你一个整数数组 nums 。数组 nums 的 唯一性数组 是一个按元素从小到大排序的数组，包含了 nums 的所有非空
 * 子数组
 * 中不同元素的个数。
 * <p>
 * 换句话说，这是由所有 0 <= i <= j < nums.length 的 distinct(nums[i..j]) 组成的递增数组。
 * <p>
 * 其中，distinct(nums[i..j]) 表示从下标 i 到下标 j 的子数组中不同元素的数量。
 * <p>
 * 返回 nums 唯一性数组 的 中位数 。
 * <p>
 * 注意，数组的 中位数 定义为有序数组的中间元素。如果有两个中间元素，则取值较小的那个。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3]
 * <p>
 * 输出：1
 * <p>
 * 解释：
 * <p>
 * nums 的唯一性数组为 [distinct(nums[0..0]), distinct(nums[1..1]), distinct(nums[2..2]), distinct(nums[0..1]), distinct(nums[1..2]), distinct(nums[0..2])]，即 [1, 1, 1, 2, 2, 3] 。唯一性数组的中位数为 1 ，因此答案是 1 。
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [3,4,3,4,5]
 * <p>
 * 输出：2
 * <p>
 * 解释：
 * <p>
 * nums 的唯一性数组为 [1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3] 。唯一性数组的中位数为 2 ，因此答案是 2 。
 * <p>
 * 示例 3：
 * <p>
 * 输入：nums = [4,3,5,4]
 * <p>
 * 输出：2
 * <p>
 * 解释：
 * <p>
 * nums 的唯一性数组为 [1, 1, 1, 1, 2, 2, 2, 3, 3, 3] 。唯一性数组的中位数为 2 ，因此答案是 2 。
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 */
public class Daily20240827 {
    public static void main(String[] args) {
    Solution20240827 solution20240827 = new Solution20240827();
        System.out.println(solution20240827.medianOfUniquenessArray(new int[]{1, 2, 3}));
    }
}

class Solution20240827 {
    public int medianOfUniquenessArray(int[] nums) {
        int n = nums.length;
        // 非空子数列个数，中位数所在位置
        long k = ((long) n * (n + 1) / 2 + 1) / 2;
        int left = 0, right = n;
        while (left + 1 < right) {
            int mid = (left + right) / 2;
            if (check(nums, mid, k)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }

    private boolean check(int[] nums, int mid, long k) {
        // 存放元素出现的频次
        Map<Integer, Integer> freq = new HashMap<>();
        long cnt = 0;
        int l = 0;
        for (int r = 0; r < nums.length; r++) {
            freq.merge(nums[r], 1, Integer::sum); // 移入右端点
            while (freq.size() > mid) {
                // 移掉左端点
                int out = nums[l++];
                if (freq.merge(out, -1, Integer::sum) == 0) {
                    freq.remove(out);
                }
            }
            cnt += r - l + 1;
            if (cnt >= k) {
                return true;
            }
        }
        return false;
    }
}
