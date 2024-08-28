package com.yin.daily;

/**
 * 3144. 分割字符频率相等的最少子字符串
 * 中等
 * 相关标签
 * 相关企业
 * 提示
 * 给你一个字符串 s ，你需要将它分割成一个或者更多的 平衡 子字符串。比方说，s == "ababcc" 那么 ("abab", "c", "c") ，("ab", "abc", "c") 和 ("ababcc") 都是合法分割，但是 ("a", "bab", "cc") ，("aba", "bc", "c") 和 ("ab", "abcc") 不是，不平衡的子字符串用粗体表示。
 * <p>
 * 请你返回 s 最少 能分割成多少个平衡子字符串。
 * <p>
 * 注意：一个 平衡 字符串指的是字符串中所有字符出现的次数都相同。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "fabccddg"
 * <p>
 * 输出：3
 * <p>
 * 解释：
 * <p>
 * 我们可以将 s 分割成 3 个子字符串：("fab, "ccdd", "g") 或者 ("fabc", "cd", "dg") 。
 * <p>
 * 示例 2：
 * <p>
 * 输入：s = "abababaccddb"
 * <p>
 * 输出：2
 * <p>
 * 解释：
 * <p>
 * 我们可以将 s 分割成 2 个子字符串：("abab", "abaccddb") 。
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 1000
 * s 只包含小写英文字母。
 */
public class Daily20240828 {
    public static void main(String[] args) {
        Solution20240828 solution = new Solution20240828();
        System.out.println(solution.minimumSubstringsInPartition("fabccddg"));
//        System.out.println(solution.minimumSubstringsInPartition("abababaccddb"));
    }
}

class Solution20240828 {
    public int minimumSubstringsInPartition(String S) {
        char[] s = S.toCharArray();
        int n = s.length;
        int[] memo = new int[n];
        return dfs(n - 1, s, memo);
    }

    private int dfs(int i, char[] s, int[] memo) {
        if (i < 0) {
            return 0;
        }
        if (memo[i] > 0) { // 之前计算过
            return memo[i];
        }
        int res = Integer.MAX_VALUE;
        int[] cnt = new int[26];
        int k = 0, maxCnt = 0;
        for (int j = i; j >= 0; j--) {
            k += cnt[s[j] - 'a']++ == 0 ? 1 : 0;
            maxCnt = Math.max(maxCnt, cnt[s[j] - 'a']);
            if (i - j + 1 == k * maxCnt) {
                res = Math.min(res, dfs(j - 1, s, memo) + 1);
            }
        }
        memo[i] = res; // 记忆化
       return res;
    }
}
