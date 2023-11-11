package com.yin.daily;

public class Daily20231108 {
    public static void main(String[] args) {
        String s = "01000111";
        Solution2609 solution = new Solution2609();
        int cnt = solution.findTheLongestBalancedSubstring(s);
        System.out.println(cnt);
    }
}

class Solution2609 {
    public int findTheLongestBalancedSubstring(String s) {
        int maxSize = 0;
        int idx = 0;
        int n = s.length();
        while (idx < n) {
            int cntZero = 0;
            int cntOne = 0;
            while (idx < n && s.charAt(idx) == '0') {
                cntZero++;
                idx++;
            }
            while (idx < n && s.charAt(idx) == '1') {
                cntOne++;
                idx++;
            }
            maxSize = Math.max(maxSize, Math.min(cntZero, cntOne) * 2);
        }
        return maxSize;
    }
}
