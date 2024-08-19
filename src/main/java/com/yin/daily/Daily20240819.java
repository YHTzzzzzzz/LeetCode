package com.yin.daily;

import java.util.Arrays;

/**
 * 552. 学生出勤记录 II
 * 困难
 * 相关标签
 * 相关企业
 * 可以用字符串表示一个学生的出勤记录，其中的每个字符用来标记当天的出勤情况（缺勤、迟到、到场）。记录中只含下面三种字符：
 * 'A'：Absent，缺勤
 * 'L'：Late，迟到
 * 'P'：Present，到场
 * 如果学生能够 同时 满足下面两个条件，则可以获得出勤奖励：
 * <p>
 * 按 总出勤 计，学生缺勤（'A'）严格 少于两天。
 * 学生 不会 存在 连续 3 天或 连续 3 天以上的迟到（'L'）记录。
 * 给你一个整数 n ，表示出勤记录的长度（次数）。请你返回记录长度为 n 时，可能获得出勤奖励的记录情况 数量 。答案可能很大，所以返回对 109 + 7 取余 的结果。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 2
 * 输出：8
 * 解释：
 * 有 8 种长度为 2 的记录将被视为可奖励：
 * "PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL"
 * 只有"AA"不会被视为可奖励，因为缺勤次数为 2 次（需要少于 2 次）。
 * 示例 2：
 * <p>
 * 输入：n = 1
 * 输出：3
 * 示例 3：
 * <p>
 * 输入：n = 10101
 * 输出：183236316
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 105
 */
public class Daily20240819 {
    public static void main(String[] args) {
        Solution20240819 solution20240819 = new Solution20240819();
        System.out.println(solution20240819.checkRecord(10101));
    }
}

class Solution20240819 {
    int mod = (int) 1e9 + 7;
    int[][][] cache;

    public int checkRecord(int n) {
        cache = new int[n + 1][2][3];
        for (int[][] its : cache) {
            for (int[] anInt : its) {
                Arrays.fill(anInt, -1);
            }
        }
        return dfs(n, 0, 0);
    }

    /**
     * u 还需要填入的字符个数
     * acnt 已经出现的A的个数
     * lcnt 已经连续出现的L的个数
     */
    int dfs(int u, int acnt, int lcnt) {
        if (acnt >= 2) return 0;
        if (lcnt >= 3) return 0;
        if (u == 0) return 1;
        if (cache[u][acnt][lcnt] != -1) return cache[u][acnt][lcnt];
        int ans = dfs(u - 1, acnt + 1, 0) % mod; // A
        ans = (ans + dfs(u - 1, acnt, lcnt + 1)) % mod; // L
        ans = (ans + dfs(u - 1, acnt, 0)) % mod;
        cache[u][acnt][lcnt] = ans;
        return ans;
    }
}
