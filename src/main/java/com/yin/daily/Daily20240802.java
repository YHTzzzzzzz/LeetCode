package com.yin.daily;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 3128. 直角三角形
 * 中等
 * 相关标签
 * 相关企业
 * 提示
 * 给你一个二维 boolean 矩阵 grid 。
 *
 * 请你返回使用 grid 中的 3 个元素可以构建的 直角三角形 数目，且满足 3 个元素值 都 为 1 。
 *
 * 注意：
 *
 * 如果 grid 中 3 个元素满足：一个元素与另一个元素在 同一行，同时与第三个元素在 同一列 ，那么这 3 个元素称为一个 直角三角形 。这 3 个元素互相之间不需要相邻。
 *
 *
 * 示例 1：
 *
 * 0	1	0
 * 0	1	1
 * 0	1	0
 * 0	1	0
 * 0	1	1
 * 0	1	0
 * 输入：grid = [[0,1,0],[0,1,1],[0,1,0]]
 *
 * 输出：2
 *
 * 解释：
 *
 * 有 2 个直角三角形。
 *
 * 示例 2：
 *
 * 1	0	0	0
 * 0	1	0	1
 * 1	0	0	0
 * 输入：grid = [[1,0,0,0],[0,1,0,1],[1,0,0,0]]
 *
 * 输出：0
 *
 * 解释：
 *
 * 没有直角三角形。
 *
 * 示例 3：
 *
 * 1	0	1
 * 1	0	0
 * 1	0	0
 * 1	0	1
 * 1	0	0
 * 1	0	0
 * 输入：grid = [[1,0,1],[1,0,0],[1,0,0]]
 *
 * 输出：2
 *
 * 解释：
 *
 * 有两个直角三角形。
 *
 *
 *
 * 提示：
 *
 * 1 <= grid.length <= 1000
 * 1 <= grid[i].length <= 1000
 * 0 <= grid[i][j] <= 1
 */
public class Daily20240802 {
    public static void main(String[] args) {
        int[][] grid = {{0,1,0},{0,1,1},{0,1,0}};
        System.out.println(new Solution20240802().numberOfRightTrianglesPrefer(grid));
//        grid = new int[][]{{1,0,0,0},{0,1,0,1},{1,0,0,0}};
//        System.out.println(new Solution20240802().numberOfRightTriangles(grid));
//        grid = new int[][]{{1,0,1},{1,0,0},{1,0,0}};
//        System.out.println(new Solution20240802().numberOfRightTriangles(grid));
//        grid = new int[][]{{0}, {1}};
//        System.out.println(new Solution20240802().numberOfRightTriangles(grid));
    }
}

/**
 * 从左往右遍历，该元素为1时，看其下方和右方是否存在1
 */
class Solution20240802 {
    public long numberOfRightTriangles(int[][] grid) {
        // 暴力解法优化
        Map<Integer, Long> cntR = new HashMap<>(grid.length);
        Map<Integer, Long> cntC = new HashMap<>(grid.length);
        long ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    long rowCnt = 0, colCnt = 0;
                    // 检索 i 行有多少个 1
                    if (cntR.containsKey(i)) {
                        rowCnt = cntR.get(i);
                    } else {
                        rowCnt = cntOnes(grid, i, true);
                        cntR.put(i, rowCnt);
                    }
                    if (cntC.containsKey(j)) {
                        colCnt = cntC.get(j);
                    } else {
                        colCnt = cntOnes(grid, j, false);
                        cntC.put(j, colCnt);
                    }
                    ans += (rowCnt - 1) * (colCnt - 1);
                }
            }
        }
        return ans;
    }

    private long cntOnes(int[][] grid, int i, boolean isRow) {
        long ans = 0;
        if (isRow) {
            int n = grid[i].length;
            while (n > 0) {
                if (grid[i][n - 1] == 1) {
                    ans++;
                }
                n--;
            }
        } else {
            int n = grid.length;
            while (n > 0) {
                if (grid[n - 1][i] == 1) {
                    ans++;
                }
                n--;
            }
        }
        return ans;
    }

    // 灵茶山艾府的解法
    public long numberOfRightTrianglesPrefer(int[][] grid) {
        int n = grid[0].length;
        int[] colSum = new int[n];
        Arrays.fill(colSum, -1); // 提前减一
        for (int[] row : grid) {
            for (int j = 0; j < n; j++) {
                colSum[j] += row[j];
            }
        }

        long ans = 0;
        for (int[] row : grid) {
            int rowSum = -1; // 提前减一
            for (int x : row) {
                rowSum += x;
            }
            for (int j = 0; j < row.length; j++) {
                if (row[j] == 1) {
                    ans += rowSum * colSum[j];
                }
            }
        }
        return ans;
    }
}
