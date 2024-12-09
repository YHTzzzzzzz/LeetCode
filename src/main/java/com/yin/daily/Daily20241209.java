package com.yin.daily;

import java.util.List;

// 1812. 判断国际象棋棋盘中一个格子的颜色
public class Daily20241209 {
    public static void main(String[] args) {
        Solution20241209 solution = new Solution20241209();
        System.out.println(solution.squareIsWhite("a1"));
        System.out.println(solution.example());
    }
}

class Solution20241209 {
    public boolean squareIsWhite(String coordinates) {
        char[] charArray = coordinates.toCharArray();
        // 第一个字符是 a-h 判断该字符到 a 的距离
        int dis = charArray[0] - 'a';
        dis += charArray[1] - '0';
        return dis % 2 == 0;
    }

    // 找规律 第一个字符和第二个字符 ASCII 奇偶性不同就白色
    public boolean squareIsWhitePrefer(String s) {
        return s.charAt(0) % 2 != s.charAt(1) % 2;
    }

    public List<Integer> example() {
        // a1 is black
        String point = "a1";
        int first = (int) point.charAt(0); // 97
        int second = (int) point.charAt(1); // 49
        return List.of(first, second);
    }
}
