package com.yin.daily;

import java.text.MessageFormat;

/**
 * 3127. 构造相同颜色的正方形
 * 简单
 * 相关标签
 * 相关企业
 * 提示
 * 给你一个二维 3 x 3 的矩阵 grid ，每个格子都是一个字符，要么是 'B' ，要么是 'W' 。字符 'W' 表示白色，字符 'B' 表示黑色。
 * <p>
 * 你的任务是改变 至多一个 格子的颜色，使得矩阵中存在一个 2 x 2 颜色完全相同的正方形。
 * <p>
 * 如果可以得到一个相同颜色的 2 x 2 正方形，那么返回 true ，否则返回 false 。
 * 示例 1：
 * 输入：grid = [["B","W","B"],["B","W","W"],["B","W","B"]]
 * 输出：true
 * 解释：
 * 修改 grid[0][2] 的颜色，可以满足要求。
 * 示例 2：
 * 输入：grid = [["B","W","B"],["W","B","W"],["B","W","B"]]
 * 输出：false
 * 解释：
 * 只改变一个格子颜色无法满足要求。
 * 示例 3：
 * 输入：grid = [["B","W","B"],["B","W","W"],["B","W","W"]]
 * 输出：true
 * 解释：
 * grid 已经包含一个 2 x 2 颜色相同的正方形了。
 * 提示：
 * grid.length == 3
 * grid[i].length == 3
 * grid[i][j] 要么是 'W' ，要么是 'B' 。
 */
public class Daily20240831 {
    public static void main(String[] args) {
        Solution20240831 solution20240831 = new Solution20240831();
        System.out.println(solution20240831.canMakeSquare(new char[][]{
                {'B', 'W', 'B'},
                {'W', 'B', 'W'},
                {'B', 'W', 'B'}
        }));
    }
}

class Solution20240831 {
    public boolean canMakeSquare(char[][] grid) {
        for (int i = 0; i < grid.length - 1; i++) {
            int index = 0;
            while (index < grid.length - 1) {
                int w = 0, b = 0;
                if (grid[i][index] == 'W') w++;
                else b++;
                if (grid[i+1][index] == 'W') w++;
                else b++;
                if (grid[i][index+1] == 'W') w++;
                else b++;
                if (grid[i+1][index+1] == 'W') w++;
                else b++;
                if (w >= 3 || b >= 3) {
//                    System.out.println(MessageFormat.format("第{0}行，第{1}列开头的方块符合要求", i + 1, index + 1));
                    return true;
                }
                index++;
            }
        }
        return false;
    }
}
