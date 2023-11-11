package com.yin.daily;

public class Daily20231107 {
    public static void main(String[] args) {
        String[] words = {"hey","aeo","mu","ooo","artro"};
        Solution20231107 solution = new Solution20231107();
        int i = solution.vowelStrings(words, 1, 4);
        System.out.println(i);
    }
}

class Solution20231107 {
    public int vowelStrings(String[] words, int left, int right) {
        int cnt = 0;
        for (int i = left; i <= right; i++) {
            char end = words[i].charAt(words[i].length() - 1);
            char start = words[i].charAt(0);
            if (vowelChar(end) && vowelChar(start)) {
                cnt++;
            }
        }
        return cnt;
    }
    boolean vowelChar(char c) {
//        return c - 'a' == 0 || c - 'e' == 0 || c - 'i' == 0 || c - 'o' == 0 || c - 'u' == 0;
        return "aeiou".indexOf(c) != -1;
    }
}
