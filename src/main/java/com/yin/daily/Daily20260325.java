package com.yin.daily;

/**
 * 给定一个由正整数组成的 m × n 矩阵 grid，
 * 判断是否可以通过一条水平分割线或垂直分割线将矩阵分成两部分，
 * 需要满足以下条件：
 * <p>
 * 1. 分割后的两个部分都必须是非空区域
 * 2. 两个部分中所有元素的总和相等
 * <p>
 * 如果存在满足条件的分割方式，返回 true；
 * 否则返回 false。
 * <p>
 * 说明：
 * - 水平分割：在某一行的上下之间进行切割
 * - 垂直分割：在某一列的左右之间进行切割
 * <p>
 * 示例：
 * grid =
 * [
 * [1, 2],
 * [3, 0]
 * ]
 * <p>
 * 可以在第一行和第二行之间进行水平分割：
 * 上半部分和为：1 + 2 = 3
 * 下半部分和为：3 + 0 = 3
 * <p>
 * 返回 true
 */
public class Daily20260325 {
}

class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        long total = 0;
        for (int[] m : grid) {
            for (int n : m) {
                total += n;
            }
        }

        // 如果能分割必须满足 total % 2 == 0
        if (total % 2 != 0) return false;

        long target = total / 2;

        int line = grid.length, row = grid[0].length;

        // 水平分割
        long sum = 0;
        for (int i = 0; i < line - 1; i++) {
            for (int item : grid[i]) {
                sum += item;
            }
            if (sum == target) return true;
            else if (sum > target) break;
        }

        // 垂直分割
        long colSum = 0;
        for (int j = 0; j < row - 1; j++) {
            for (int[] ints : grid) {
                colSum += ints[j];
            }
            if (colSum == target) return true;
            else if (colSum > target) break;
        }

        return false;
    }
}
