package com.yin.daily;

import java.util.Arrays;

/**
 * 3142. 判断矩阵是否满足条件
 * 已解答
 * 简单
 * 相关标签
 * 相关企业
 * 提示
 * 给你一个大小为 m x n 的二维矩阵 grid 。你需要判断每一个格子 grid[i][j] 是否满足：
 * <p>
 * 如果它下面的格子存在，那么它需要等于它下面的格子，也就是 grid[i][j] == grid[i + 1][j] 。
 * 如果它右边的格子存在，那么它需要不等于它右边的格子，也就是 grid[i][j] != grid[i][j + 1] 。
 * 如果 所有 格子都满足以上条件，那么返回 true ，否则返回 false 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：grid = [[1,0,2],[1,0,2]]
 * <p>
 * 输出：true
 * <p>
 * 解释：
 * <p>
 * <p>
 * <p>
 * 网格图中所有格子都符合条件。
 * <p>
 * 示例 2：
 * <p>
 * 输入：grid = [[1,1,1],[0,0,0]]
 * <p>
 * 输出：false
 * <p>
 * 解释：
 * <p>
 * <p>
 * <p>
 * 同一行中的格子值都相等。
 * <p>
 * 示例 3：
 * <p>
 * 输入：grid = [[1],[2],[3]]
 * <p>
 * 输出：false
 * <p>
 * 解释：
 * <p>
 * <p>
 * <p>
 * 同一列中的格子值不相等。
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n, m <= 10
 * 0 <= grid[i][j] <= 9
 */
public class Daily20240829 {
}

class Solution20240829 {
    public boolean satisfiesConditions(int[][] grid) {
        int[] ints = grid[0];
        int pre = ints[0];
        for (int i = 1; i < ints.length; i++) {
            if (pre == ints[i]) {
                return false;
            }
            pre = ints[i];
        }
        for (int i = 1; i < grid.length; i++) {
            if (!Arrays.equals(grid[i], ints))
                return false;
        }
        return true;
    }
}
