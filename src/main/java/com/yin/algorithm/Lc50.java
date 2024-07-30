package com.yin.algorithm;

/**
 * 50. Pow(x, n)
 * 已解答
 * 中等
 * 相关标签
 * 相关企业
 * 实现 pow(x, n) ，即计算 x 的整数 n 次幂函数（即，xn ）。
 *
 *
 *
 * 示例 1：
 *
 * 输入：x = 2.00000, n = 10
 * 输出：1024.00000
 * 示例 2：
 *
 * 输入：x = 2.10000, n = 3
 * 输出：9.26100
 * 示例 3：
 *
 * 输入：x = 2.00000, n = -2
 * 输出：0.25000
 * 解释：2-2 = 1/22 = 1/4 = 0.25
 *
 *
 * 提示：
 *
 * -100.0 < x < 100.0
 * -231 <= n <= 231-1
 * n 是一个整数
 * 要么 x 不为零，要么 n > 0 。
 * -104 <= xn <= 104
 */
public class Lc50 {
    public static void main(String[] args) {

        Solution50 solution50 = new Solution50();
        System.out.println(solution50.myPowQuickMi(2.00000, -2));
        System.out.println(solution50.myPow(2.00000, -2));
        System.out.println(solution50.myPowQuickMi(2.00000, -2147483648));
        System.out.println(solution50.myPow(2.00000, -2147483648));
    }
}

class Solution50 {
    // 搞笑一下
    public double myPow(double x, int n) {
        return Math.pow(x, n);
    }

    public double myPowQuickMi(double x, int n) {
        double ans = 1;
        while (n != 0) {
            if ((n & 1) == 1) {
                if (n < 0) {
                    ans *= 1/x;
                } else  ans *= x;
            }
            x *= x;
            n >>= 1;
        }
        return ans;
    }
}
