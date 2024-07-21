package com.yin.daily;

/**
 * 1186. 删除一次得到子数组最大和
 * 中等
 * 相关标签
 * 相关企业
 * 提示
 * 给你一个整数数组，返回它的某个 非空 子数组（连续元素）在执行一次可选的删除操作后，所能得到的最大元素总和。换句话说，你可以从原数组中选出一个子数组，并可以决定要不要从中删除一个元素（只能删一次哦），（删除后）子数组中至少应当有一个元素，然后该子数组（剩下）的元素总和是所有子数组之中最大的。
 *
 * 注意，删除一个元素后，子数组 不能为空。
 *
 *
 *
 * 示例 1：
 *
 * 输入：arr = [1,-2,0,3]
 * 输出：4
 * 解释：我们可以选出 [1, -2, 0, 3]，然后删掉 -2，这样得到 [1, 0, 3]，和最大。
 * 示例 2：
 *
 * 输入：arr = [1,-2,-2,3]
 * 输出：3
 * 解释：我们直接选出 [3]，这就是最大和。
 * 示例 3：
 *
 * 输入：arr = [-1,-1,-1,-1]
 * 输出：-1
 * 解释：最后得到的子数组不能为空，所以我们不能选择 [-1] 并从中删去 -1 来得到 0。
 *      我们应该直接选择 [-1]，或者选择 [-1, -1] 再从中删去一个 -1。
 *
 *
 * 提示：
 *
 * 1 <= arr.length <= 105
 * -104 <= arr[i] <= 104
 */

public class Daily20240721 {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 1, -2, -5, -2};

        System.out.println(new Solution1186().maximumSum(arr));
    }
}

/**
 * dp[i][0]
 * 1. dp[i][0] = dp[i - 1][0] + arr[i]
 * 2. 丢掉i位以前：0 + arr[i]
 * dp[i][1]
 * 1. 之前已经删除过：dp[i - 1][1] + arr[i]
 * 2. 之前没删除过：dp[i-1][0]
 */
class Solution1186 {
    public int maximumSum(int[] arr) {
//        int n = arr.length;
//        int[][] dp = new int[n][2];
//        dp[0][0] = arr[0];
//        dp[0][1] = 0;
//        int max = arr[0];
//        for (int i = 1; i < n; i++) {
//            dp[i][0] = Math.max(dp[i - 1][0], 0) + arr[i];
//            dp[i][1] = Math.max(dp[i - 1][1] + arr[i], dp[i - 1][0]);
//            max = Math.max(max, Math.max(dp[i][0], dp[i][1]));
//        }
//        return max;
        /**
         * 优化思路
         * 1. 只关注i位上一个位置的结果，可以用两个变量来代替，而不是使用二维数组
         */
        int dp0 = arr[0], dp1 = 0, max = arr[0], pre = 0;
        for (int i = 1; i < arr.length; i++) {
            pre = dp0;
            dp0 = Math.max(pre, 0) + arr[i];
            dp1 = Math.max(pre, dp1 + arr[i]);
            max = Math.max(max, Math.max(dp0, dp1));
        }
        return max;
    }
}
