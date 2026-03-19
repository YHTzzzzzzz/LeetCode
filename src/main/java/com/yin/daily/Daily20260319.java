package com.yin.daily;

public class Daily20260319 {
    public static void main(String[] args) {
        char[][] grid = {
                {'X', 'Y', '.' },
                {'Y', '.', '.' }
        };
        Solution20260319 s = new Solution20260319();
        int count = s.numberOfSubmatrices(grid);
        System.out.println(count);
    }
}

class Solution20260319 {
    public int numberOfSubmatrices(char[][] grid) {
        int m = grid.length, n = grid[0].length, count = 0;

        int[][] nums = new int[m][n], cntX = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int val = grid[i][j] == 'X' ? 1 : (grid[i][j] == 'Y' ? -1 : 0);
                int x = grid[i][j] == 'X' ? 1 : 0;

                nums[i][j] = val;
                cntX[i][j] = x;
                if (i > 0) {
                    nums[i][j] += nums[i - 1][j];
                    cntX[i][j] += cntX[i - 1][j];
                }
                if (j > 0) {
                    nums[i][j] += nums[i][j - 1];
                    cntX[i][j] += cntX[i][j - 1];
                }
                if (i > 0 && j > 0) {
                    nums[i][j] -= nums[i - 1][j - 1];
                    cntX[i][j] -= cntX[i - 1][j - 1];
                }
                if (nums[i][j] == 0 && cntX[i][j] > 0) count++;
            }
        }
//        System.out.println(Arrays.deepToString(nums));
        return count;
    }
}
