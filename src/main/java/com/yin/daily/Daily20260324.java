package com.yin.daily;

/*
给定一个下标从 0 开始、大小为 n * m 的二维整数矩阵 grid，
定义一个同样大小为 n * m 的二维矩阵 p。

如果满足以下条件，则称 p 为 grid 的“乘积矩阵”：
对于每个元素 p[i][j]，其值等于 grid 中除 grid[i][j] 之外所有元素的乘积，
并且结果需要对 12345 取模。

请返回矩阵 p。
*/
public class Daily20260324 {
}

class Solution20260324 {
    public int[][] constructProductMatrix(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int mod = 12345;

        int[][] ans = new int[m][n];

        // 先全部设为1
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans[i][j] = 1;
            }
        }

        // 前缀乘积（从左上到右下）
        int prefix = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans[i][j] = prefix;
                prefix = (int)((long)prefix * grid[i][j] % mod);
            }
        }

        // 后缀乘积（从右下到左上）
        int suffix = 1;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                ans[i][j] = (int)((long)ans[i][j] * suffix % mod);
                suffix = (int)((long)suffix * grid[i][j] % mod);
            }
        }

        return ans;
    }
}

