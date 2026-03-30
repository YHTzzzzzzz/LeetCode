package com.yin.daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 给定两个字符串 s1 和 s2，它们长度相同为 n，并且只包含小写英文字母。
 * <p>
 * 你可以对任意一个字符串执行以下操作任意次：
 * - 选择两个下标 i 和 j，满足：
 * 1. i < j
 * 2. (j - i) 为偶数
 * - 然后交换该字符串中下标 i 和 j 位置的字符
 * <p>
 * 如果通过若干次操作，可以使字符串 s1 和字符串 s2 完全相等，
 * 则返回 true；否则返回 false。
 * <p>
 * 注意：
 * - 操作可以对 s1 或 s2 任意一个字符串执行
 * - 操作可以执行任意多次
 */
public class Daily20260330 {
}

class Solution20260330 {
    public boolean checkStrings(String s1, String s2) {
        List<Character> s1Even = new ArrayList<>(), s1Odd = new ArrayList<>(), s2Even = new ArrayList<>(), s2Odd = new ArrayList<>();
        for (int i = 0; i < s1.length(); i++) {
            if (i % 2 == 0) {
                s1Even.add(s1.charAt(i));
                s2Even.add(s2.charAt(i));
            } else {
                s1Odd.add(s1.charAt(i));
                s2Odd.add(s2.charAt(i));
            }
        }
        Collections.sort(s1Even);
        Collections.sort(s1Odd);
        Collections.sort(s2Even);
        Collections.sort(s2Odd);
        return s1Even.equals(s2Even) && s1Odd.equals(s2Odd);
    }

    // 优化 使用两个计数数组 int[26] s1出现的++ s2出现的--
}