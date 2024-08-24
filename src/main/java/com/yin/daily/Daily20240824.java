package com.yin.daily;

import java.util.Map;

/**
 * 3146. 两个字符串的排列差
 * 简单
 * 相关标签
 * 相关企业
 * 提示
 * 给你两个字符串 s 和 t，每个字符串中的字符都不重复，且 t 是 s 的一个排列。
 * <p>
 * 排列差 定义为 s 和 t 中每个字符在两个字符串中位置的绝对差值之和。
 * <p>
 * 返回 s 和 t 之间的 排列差 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "abc", t = "bac"
 * <p>
 * 输出：2
 * <p>
 * 解释：
 * <p>
 * 对于 s = "abc" 和 t = "bac"，排列差是：
 * <p>
 * "a" 在 s 中的位置与在 t 中的位置之差的绝对值。
 * "b" 在 s 中的位置与在 t 中的位置之差的绝对值。
 * "c" 在 s 中的位置与在 t 中的位置之差的绝对值。
 * 即，s 和 t 的排列差等于 |0 - 1| + |2 - 2| + |1 - 0| = 2。
 * <p>
 * 示例 2：
 * <p>
 * 输入：s = "abcde", t = "edbac"
 * <p>
 * 输出：12
 * <p>
 * 解释： s 和 t 的排列差等于 |0 - 3| + |1 - 2| + |2 - 4| + |3 - 1| + |4 - 0| = 12。
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 26
 * 每个字符在 s 中最多出现一次。
 * t 是 s 的一个排列。
 * s 仅由小写英文字母组成
 */
public class Daily20240824 {
    public static void main(String[] args) {
        String s = "abc";
        String t = "bac";
        Solution20240824 solution = new Solution20240824();
        System.out.println(solution.findPermutationDifference(s, t));
    }
}

class Solution20240824 {
    public int findPermutationDifference(String s, String t) {
        int[] array = new int[26];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char si = s.charAt(i);
            char ti = t.charAt(i);
            // si 用来加
            array[si - 'a'] += i;
            // ti 用来减
            array[ti - 'a'] -= i;
        }
        int ans = 0;
        for (int i : array) {
            ans = i < 0 ? ans - i : ans + i;
        }
        return ans;
    }
}
