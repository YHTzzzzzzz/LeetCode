package com.yin.algorithm;

/**
 *70. 爬楼梯
 * 简单
 * 相关标签
 * 相关企业
 * 提示
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：2
 * 解释：有两种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶
 * 2. 2 阶
 * 示例 2：
 *
 * 输入：n = 3
 * 输出：3
 * 解释：有三种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶 + 1 阶
 * 2. 1 阶 + 2 阶
 * 3. 2 阶 + 1 阶
 *
 *
 * 提示：
 *
 * 1 <= n <= 45
 */
public class Lc70 {
    public static void main(String[] args) {
        Solution70 solution70 = new Solution70();
        System.out.println(solution70.climbStairsMemo(45) == solution70.climbStairs(45));
    }
}

/**
 * 动态规划题：
 * 1. 定义dp(n) 表示爬到第n阶楼梯的方法数
 * 2. 状态转移方程：dp(n) = dp(n-1) + dp(n-2)
 */
class Solution70 {
    /**
     * 不出意外的超时了 n = 45
     */
    public int climbStairsTimeOut(int n) {
        // 边界条件
        if(n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return climbStairsTimeOut(n -1) + climbStairsTimeOut(n - 2);
    }

    /**
     * 记忆优化
     */
    public int climbStairsMemo(int n) {
        int[] memo = new int[n + 1];
        return dfs(n , memo);
    }

    private int dfs(int n, int[] memo) {
        // 边界条件
        if(n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        if (memo[n] != 0) { // 之前计算过
            return memo[n];
        }
        return memo[n] = dfs(n - 1, memo) + dfs(n - 2, memo); // 记忆优化
    }

    public int climbStairs(int n) {
        if (n <= 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int pre1 = 2, pre2 = 1, curr = 0;
        for (int i = 3; i < n + 1; i++) {
            curr = pre1 + pre2;
            pre2 = pre1;
            pre1 = curr;
        }
        return curr;
    }
}
