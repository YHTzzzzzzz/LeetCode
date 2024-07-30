package com.yin.daily;

import java.util.ArrayList;
import java.util.List;

/**
 * 2961. 双模幂运算
 * 中等
 * 相关标签
 * 相关企业
 * 给你一个下标从 0 开始的二维数组 variables ，其中 variables[i] = [ai, bi, ci, mi]，以及一个整数 target 。
 *
 * 如果满足以下公式，则下标 i 是 好下标：
 *
 * 0 <= i < variables.length
 * ((aibi % 10)ci) % mi == target
 * 返回一个由 好下标 组成的数组，顺序不限 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：variables = [[2,3,3,10],[3,3,3,1],[6,1,1,4]], target = 2
 * 输出：[0,2]
 * 解释：对于 variables 数组中的每个下标 i ：
 * 1) 对于下标 0 ，variables[0] = [2,3,3,10] ，(23 % 10)3 % 10 = 2 。
 * 2) 对于下标 1 ，variables[1] = [3,3,3,1] ，(33 % 10)3 % 1 = 0 。
 * 3) 对于下标 2 ，variables[2] = [6,1,1,4] ，(61 % 10)1 % 4 = 2 。
 * 因此，返回 [0,2] 作为答案。
 * 示例 2：
 *
 * 输入：variables = [[39,3,1000,1000]], target = 17
 * 输出：[]
 * 解释：对于 variables 数组中的每个下标 i ：
 * 1) 对于下标 0 ，variables[0] = [39,3,1000,1000] ，(393 % 10)1000 % 1000 = 1 。
 * 因此，返回 [] 作为答案。
 *
 *
 * 提示：
 *
 * 1 <= variables.length <= 100
 * variables[i] == [ai, bi, ci, mi]
 * 1 <= ai, bi, ci, mi <= 103
 * 0 <= target <= 103
 */
public class Daily20240730 {
    public static void main(String[] args) {
        int[][] variables = {{31,12,21,24},{2,3,3,10},{3,3,3,1},{6,1,1,4}};
        Solution20240730 solution20240730 = new Solution20240730();
        List<Integer> integers = solution20240730.getGoodIndices(variables, 1);
        System.out.println(integers);
    }
}

class Solution20240730 {
    public List<Integer> getGoodIndicesError(int[][] variables, int target) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < variables.length; i++) {
            int a = variables[i][0];
            int b = variables[i][1];
            int c = variables[i][2];
            int m = variables[i][3];

            // 例子中的 {31,12,21,24} 遇到问题，Math.pow(31, 12) 使用科学计数法导致精度丢失
            if (Math.pow((Math.pow(a, b) % 10), c) % m == target) {
                ans.add(i);
            }
        }

        return ans;
    }


    public List<Integer> getGoodIndices(int[][] variables, int target) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < variables.length; i++) {
            int a = variables[i][0];
            int b = variables[i][1];
            int c = variables[i][2];
            int m = variables[i][3];

            if (pow(pow(a, b, 10), c, m) == target) {
                ans.add(i);
            }
        }

        return ans;
    }

    // 快速幂
    private int pow(int x, int n, int mod) {
        // 初始化结果， 因为任何数的0次幂都是1
        int res = 1;
        // 循环核心
        while (n > 0) {
            // 判断 n 是否为奇数，n = 2m + 1 x^n = x * x^2m， res = res * x % mod
            if ((n & 1) == 1) {
                res = res * x % mod;
            }
            // 上面 x^n = x * x^2m的 x 已经处理， 这里处理2m
            x = x * x % mod;
            // n -> m
            n >>= 1;
        }
        return res;
    }
}
