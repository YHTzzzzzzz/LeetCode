package com.yin.daily;

import java.util.Arrays;

public class Daily20260318 {
    public static void main(String[] args) {
        Solution20260318 s = new Solution20260318();
        int[][] grid = {{7, 6, 3}, {6, 6, 1}};
        int k = 18;
        int countSubmatrices = s.countSubmatrices(grid, k);
        System.out.println(Arrays.deepToString(grid));
        System.out.printf("符合结果的个数：%d%n", countSubmatrices);
    }
}


class Solution20260318 {
    public int countSubmatrices(int[][] grid, int k) {
        int n = grid.length, m = grid[0].length, count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i > 0) grid[i][j] += grid[i - 1][j];
                if (j > 0) grid[i][j] += grid[i][j - 1];
                if (i > 0 && j > 0) grid[i][j] -= grid[i - 1][j - 1];
                if (grid[i][j] <= k) count++;
            }
        }
        return count;
    }
}
