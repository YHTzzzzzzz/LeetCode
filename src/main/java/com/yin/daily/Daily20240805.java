package com.yin.daily;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 600. 不含连续1的非负整数
 * 困难
 * 相关标签
 * 相关企业
 * 给定一个正整数 n ，请你统计在 [0, n] 范围的非负整数中，有多少个整数的二进制表示中不存在 连续的 1 。
 *
 *
 *
 * 示例 1:
 *
 * 输入: n = 5
 * 输出: 5
 * 解释:
 * 下面列出范围在 [0, 5] 的非负整数与其对应的二进制表示：
 * 0 : 0
 * 1 : 1
 * 2 : 10
 * 3 : 11
 * 4 : 100
 * 5 : 101
 * 其中，只有整数 3 违反规则（有两个连续的 1 ），其他 5 个满足规则。
 * 示例 2:
 *
 * 输入: n = 1
 * 输出: 2
 * 示例 3:
 *
 * 输入: n = 2
 * 输出: 3
 *
 *
 * 提示:
 *
 * 1 <= n <= 109
 */
public class Daily20240805 {
    public static void main(String[] args) {
        Solution20240805 solution20240805 = new Solution20240805();
        System.out.println(solution20240805.findIntegers(5));
    }
}

class Solution20240805 {
    /**
     * 不出意外的暴力解法肯定是超时的,10^9次方
     */
    public int findIntegersBL(int n) {
//        Set<String> filter = new HashSet<>();
        int ans = 0;
        for (int i = 0; i <= n; i++) {
            if (!suit(i)) {
                ans++;
            }
        }
        return ans;
    }

    private boolean suit(int n) {
        // int 转 二进制
        String binaryString = Integer.toBinaryString(n);
        return binaryString.contains("11");
    }

    char s[];
    int dp[][];

    public int findIntegers(int n) {
        s = Integer.toBinaryString(n).toCharArray();
        var m = s.length;
        dp = new int[m][2];
        for (var i = 0; i < m; i++) Arrays.fill(dp[i], -1);
        return f(0, false, true);
    }

    int f(int i, boolean pre1, boolean isLimit) {
        if (i == s.length) return 1;
        if (!isLimit && dp[i][pre1 ? 1 : 0] >= 0) return dp[i][pre1 ? 1 : 0];
        var up = isLimit ? s[i] - '0' : 1;
        var res = f(i + 1, false, isLimit && up == 0); // 填 0
        if (!pre1 && up == 1) res += f(i + 1, true, isLimit); // 填 1
        if (!isLimit) dp[i][pre1 ? 1 : 0] = res;
        return res;
    }

    /**
     * 记录一种二叉树解法
     */
    public int findIntegersTree(int n) {
        // 预处理第 i 层满二叉树的路径数量
        int[] dp = new int[31];
        dp[0] = dp[1] = 1;
        for (int i = 2; i < 31; ++i) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        // pre 记录上一层的根节点值，res 记录最终路径数
        int pre = 0, res = 0;
        for (int i = 29; i >= 0; --i) {
            int val = 1 << i;
            // if 语句判断 当前子树是否有右子树
            if ((n & val) != 0) {
                // 有右子树
                n -= val;
                res += dp[i + 1]; // 先将左子树（满二叉树）的路径加到结果中

                // 处理右子树
                if (pre == 1) {
                    // 上一层为 1，之后要处理的右子树根节点肯定也为 1
                    // 此时连续两个 1，不满足题意，直接退出
                    break;
                }
                // 标记当前根节点为 1
                pre = 1;
            } else {
                // 无右子树，此时不能保证左子树是否为满二叉树，所以要在下一层再继续判断
                pre = 0;
            }

            if (i == 0) {
                ++res;
            }
        }

        return res;
    }
}
