package com.yin.daily;

import java.util.HashMap;
import java.util.Map;

/**
 * 3117. 划分数组得到最小的值之和
 * 困难
 * 相关标签
 * 相关企业
 * 提示
 * 给你两个数组 nums 和 andValues，长度分别为 n 和 m。
 * <p>
 * 数组的 值 等于该数组的 最后一个 元素。
 * <p>
 * 你需要将 nums 划分为 m 个 不相交的连续
 * 子数组
 * ，对于第 ith 个子数组 [li, ri]，子数组元素的按位 AND 运算结果等于 andValues[i]，换句话说，对所有的 1 <= i <= m，nums[li] & nums[li + 1] & ... & nums[ri] == andValues[i] ，其中 & 表示按位 AND 运算符。
 * <p>
 * 返回将 nums 划分为 m 个子数组所能得到的可能的 最小 子数组 值 之和。如果无法完成这样的划分，则返回 -1 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入： nums = [1,4,3,3,2], andValues = [0,3,3,2]
 * <p>
 * 输出： 12
 * <p>
 * 解释：
 * <p>
 * 唯一可能的划分方法为：
 * <p>
 * [1,4] 因为 1 & 4 == 0
 * [3] 因为单元素子数组的按位 AND 结果就是该元素本身
 * [3] 因为单元素子数组的按位 AND 结果就是该元素本身
 * [2] 因为单元素子数组的按位 AND 结果就是该元素本身
 * 这些子数组的值之和为 4 + 3 + 3 + 2 = 12
 * <p>
 * 示例 2：
 * <p>
 * 输入： nums = [2,3,5,7,7,7,5], andValues = [0,7,5]
 * <p>
 * 输出： 17
 * <p>
 * 解释：
 * <p>
 * 划分 nums 的三种方式为：
 * <p>
 * [[2,3,5],[7,7,7],[5]] 其中子数组的值之和为 5 + 7 + 5 = 17
 * [[2,3,5,7],[7,7],[5]] 其中子数组的值之和为 7 + 7 + 5 = 19
 * [[2,3,5,7,7],[7],[5]] 其中子数组的值之和为 7 + 7 + 5 = 19
 * 子数组值之和的最小可能值为 17
 * <p>
 * 示例 3：
 * <p>
 * 输入： nums = [1,2,3,4], andValues = [2]
 * <p>
 * 输出： -1
 * <p>
 * 解释：
 * <p>
 * 整个数组 nums 的按位 AND 结果为 0。由于无法将 nums 划分为单个子数组使得元素的按位 AND 结果为 2，因此返回 -1。
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n == nums.length <= 104
 * 1 <= m == andValues.length <= min(n, 10)
 * 1 <= nums[i] < 105
 * 0 <= andValues[j] < 105
 */
public class Daily20240816 {
    public static void main(String[] args) {
        int[] nums = {1, 4, 3, 3, 2};
        int[] andValues = {0, 3, 3, 2};
        Solution20240816 solution20240816 = new Solution20240816();
        System.out.println(solution20240816.minimumValueSum(nums, andValues));
    }
}

class Solution20240816 {
    public int minimumValueSum(int[] nums, int[] andValues) {
        Map<Long, Integer> memo = new HashMap<>();
        int ans = dfs(0, 0, -1, nums, andValues, memo);
        return ans < Integer.MAX_VALUE / 2 ? ans : -1;
    }

    private int dfs(int i, int j, int and, int[] nums, int[] andValues, Map<Long, Integer> memo) {
        int n = nums.length;
        int m = andValues.length;
        if (n - i < m - j) { // 剩余元素不足
            return Integer.MAX_VALUE / 2; // 防止下面 + nums[i]溢出
        }
        if (j == m) { // 分成了m段
            return i == n ? 0 : Integer.MAX_VALUE / 2;
        }
        and &= nums[i];
        // 三个参数压缩成一个long
        long mask = (long) i << 36 | (long) j << 32 | and;
        if (memo.containsKey(mask)) { //之前计算过
            return memo.get(mask);
        }
        int res = dfs(i + 1, j, and, nums, andValues, memo); // 不划分
        if (and == andValues[j]) { // 划分，nums[i]是这一段最后一个数
            res = Math.min(res, dfs(i + 1, j + 1, -1, nums, andValues, memo) + nums[i]);
        }
        memo.put(mask, res);
        return res;
    }
}
