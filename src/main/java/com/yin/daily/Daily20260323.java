package com.yin.daily;

/**
 * 给定一个 m x n 的整数矩阵 grid，你从左上角 (0,0) 出发，
 * 每次只能向右或向下移动，最终到达右下角 (m-1, n-1)。
 * <p>
 * 每条路径的“路径乘积”定义为：路径上所有经过元素的乘积。
 * <p>
 * 请在所有可能路径中，找出“乘积最大且非负”的路径，并返回该乘积对 (1e9 + 7) 取模后的结果。
 * <p>
 * 注意：
 * 1. 如果所有路径的乘积都为负数，则返回 -1。
 * 2. 取模操作必须在“找到最大乘积之后”再进行。
 * <p>
 * 关键点：
 * - 由于 grid 中可能包含负数，路径乘积会因为负负得正而变大
 * - 因此每个位置需要同时维护：
 * 当前路径的“最大乘积”和“最小乘积”
 * <p>
 * 状态定义：
 * dpMax[i][j]：到达 (i,j) 的最大乘积
 * dpMin[i][j]：到达 (i,j) 的最小乘积
 * <p>
 * 状态转移：
 * 来自上方或左方：
 * candidates = {
 * dpMax * grid[i][j],
 * dpMin * grid[i][j]
 * }
 * <p>
 * 时间复杂度：O(m * n)
 * 空间复杂度：O(m * n)（可优化为 O(n)）
 */
public class Daily20260323 {
}

class Solution20260323 {
    public int maxProductPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        long[][] maxGrid = new long[m][n];
        long[][] minGrid = new long[m][n];

        maxGrid[0][0] = grid[0][0];
        minGrid[0][0] = grid[0][0];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;

                long curr = grid[i][j];

                if (i == 0) {
                    maxGrid[i][j] = maxGrid[i][j - 1] * curr;
                    minGrid[i][j] = minGrid[i][j - 1] * curr;
                } else if (j == 0) {
                    maxGrid[i][j] = maxGrid[i - 1][j] * curr;
                    minGrid[i][j] = minGrid[i - 1][j] * curr;
                } else {
                    long a = maxGrid[i - 1][j] * curr;
                    long b = minGrid[i - 1][j] * curr;
                    long c = maxGrid[i][j - 1] * curr;
                    long d = minGrid[i][j - 1] * curr;

                    maxGrid[i][j] = Math.max(Math.max(a, b), Math.max(c, d));
                    minGrid[i][j] = Math.min(Math.min(a, b), Math.min(c, d));
                }
            }
        }

        long res = maxGrid[m - 1][n - 1];
        if (res < 0) return -1;
        return (int) (res % 1_000_000_007);
    }
}
