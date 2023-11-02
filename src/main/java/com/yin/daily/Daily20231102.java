package com.yin.daily;

public class Daily20231102 {
    public static void main(String[] args) {
//        String rings = "B0B6G0R6R0R6G9";
//        Solution solution = new Solution();
//        solution.countPoints(rings);
        int a = 10;    // in binary: 1010
        int b = 15;    // in binary: 1111
        System.out.println(a &= b);
    }
}

class Solution20231102 {
    char R = 'R';
    char G = 'G';
    char B = 'B';
    public int countPoints(String rings) {
        char[] chars = rings.toCharArray();
        int[] barPole = new int[10];
        int count = 0;
        for (int i = 0; i < chars.length; i+=2) {
            int index = chars[i + 1] - '0';
            int idx = -1;
            if (R == chars[i]) {
                idx = 0;
            } else if (G == chars[i]) {
                idx = 1;
            } else {
                idx = 2;
            }
            barPole[index] |= 1 << idx;
        }
        for (int i = 0; i < 10; i++) {
            count += barPole[i] == (1 << 8) - 1 ? 1 : 0;
        }
        return count;
    }
}
