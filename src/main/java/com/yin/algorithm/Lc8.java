package com.yin.algorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 8. 字符串转换整数 (atoi)
 * 中等
 * 相关标签
 * 相关企业
 * 请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数。
 *
 * 函数 myAtoi(string s) 的算法如下：
 *
 * 空格：读入字符串并丢弃无用的前导空格（" "）
 * 符号：检查下一个字符（假设还未到字符末尾）为 '-' 还是 '+'。如果两者都不存在，则假定结果为正。
 * 转换：通过跳过前置零来读取该整数，直到遇到非数字字符或到达字符串的结尾。如果没有读取数字，则结果为0。
 * 舍入：如果整数数超过 32 位有符号整数范围 [−231,  231 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231 的整数应该被舍入为 −231 ，大于 231 − 1 的整数应该被舍入为 231 − 1 。
 * 返回整数作为最终结果。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "42"
 *
 * 输出：42
 *
 * 解释：加粗的字符串为已经读入的字符，插入符号是当前读取的字符。
 *
 * 带下划线线的字符是所读的内容，插入符号是当前读入位置。
 * 第 1 步："42"（当前没有读入字符，因为没有前导空格）
 *          ^
 * 第 2 步："42"（当前没有读入字符，因为这里不存在 '-' 或者 '+'）
 *          ^
 * 第 3 步："42"（读入 "42"）
 *            ^
 * 示例 2：
 *
 * 输入：s = " -042"
 *
 * 输出：-42
 *
 * 解释：
 *
 * 第 1 步："   -042"（读入前导空格，但忽视掉）
 *             ^
 * 第 2 步："   -042"（读入 '-' 字符，所以结果应该是负数）
 *              ^
 * 第 3 步："   -042"（读入 "042"，在结果中忽略前导零）
 *                ^
 * 示例 3：
 *
 * 输入：s = "1337c0d3"
 *
 * 输出：1337
 *
 * 解释：
 *
 * 第 1 步："1337c0d3"（当前没有读入字符，因为没有前导空格）
 *          ^
 * 第 2 步："1337c0d3"（当前没有读入字符，因为这里不存在 '-' 或者 '+'）
 *          ^
 * 第 3 步："1337c0d3"（读入 "1337"；由于下一个字符不是一个数字，所以读入停止）
 *              ^
 * 示例 4：
 *
 * 输入：s = "0-1"
 *
 * 输出：0
 *
 * 解释：
 *
 * 第 1 步："0-1" (当前没有读入字符，因为没有前导空格)
 *          ^
 * 第 2 步："0-1" (当前没有读入字符，因为这里不存在 '-' 或者 '+')
 *          ^
 * 第 3 步："0-1" (读入 "0"；由于下一个字符不是一个数字，所以读入停止)
 *           ^
 * 示例 5：
 *
 * 输入：s = "words and 987"
 *
 * 输出：0
 *
 * 解释：
 *
 * 读取在第一个非数字字符“w”处停止。
 *
 *
 *
 * 提示：
 *
 * 0 <= s.length <= 200
 * s 由英文字母（大写和小写）、数字（0-9）、' '、'+'、'-' 和 '.' 组成
 */
public class Lc8 {
    public static void main(String[] args) {
        System.out.println(new Solution8().myAtoiPrefer("   -042546789461"));
    }
}

class Solution8 {
//    public int myAtoi(String s) {
//        // char[i] - zero <= 9 就是数字
//        int max = Integer.MAX_VALUE, min = Integer.MIN_VALUE, maxLen = 10;
//        StringBuilder sb = new StringBuilder();
//        Set<Character> set = new HashSet<>(Set.of('+', '-', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'));
//        boolean start = false, zeroSkip = false;
//        char[] charArray = s.toCharArray();
//        for (char c : charArray) {
//            if (maxLen == -1) {
//                break;
//            }
//            if (start) {
//                if (set.contains(c)) {
//                    if (!zeroSkip) {
//                        if (c == '0') {
//                            continue;
//                        } else {
//                            zeroSkip = true;
//                        }
//                    }
//                    sb.append(c);
//                    maxLen--;
//                } else {
//                    break;
//                }
//            } else {
//                if (c == ' ') {
//                } else if (set.contains(c)) {
//                    if (c != '+') {
//                        sb.append(c);
//                        maxLen--;
//                    }
//                    if (c == '-') {
//                        maxLen++;
//                    }
//                    start = true;
//                    set.remove('+');
//                    set.remove('-');
//                } else break;
//            }
//        }
//
//        if (maxLen == -1) {
//            if (sb.indexOf("-") == 0) {
//                return min;
//            } else {
//                return max;
//            }
//        } else if (maxLen == 0) {
//            if (sb.toString().compareTo(Integer.toString(max)) > 0) {
//                return max;
//            } else if (sb.indexOf("-") == 0 && sb.toString().compareTo(Integer.toString(min)) > 0) {
//                return min;
//            }
//        }
//        if (sb.length() == 0 || (sb.length() == 1 && sb.indexOf("-") == 0)) {
//            return 0;
//        } else {
//            return Integer.parseInt(sb.toString());
//        }
//    }
    /**
     * 自己处理过程中测试用例覆盖不周到
     * 无奈尝试题解
     * 新的概念（自动机）
     */
    public int myAtoi(String str) {
        Automaton automaton = new Automaton();
        int length = str.length();
        for (int i = 0; i < length; ++i) {
            automaton.get(str.charAt(i));
        }
        return (int) (automaton.sign * automaton.ans);
    }
    
    /**
     * 不用自动机，更清晰的处理
     */
    public int myAtoiPrefer(String s) {
        char[] c = s.trim().toCharArray();
        if (c.length == 0) return 0;
        int res = 0, bndry = Integer.MAX_VALUE / 10;
        int i = 1, sign = 1;
        if (c[0] == '-') sign = -1;
        else if (c[0] != '+') i = 0;
        for (int j = i; j < c.length; j++) {
            if (c[j] < '0' || c[j] > '9') break;
            if (res > bndry || res == bndry && c[j] > '7') return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            res = res * 10 + (c[j] - '0');
        }
        return sign * res;
    }
}

/**
* 定义自动机
*/
class Automaton {
    public int sign = 1;
    public long ans = 0;
    private String state = "start";
    private Map<String, String[]> table = new HashMap<>(){{
        put("start", new String[]{"start", "signed", "in_number", "end"});
        put("signed", new String[]{"end", "end", "in_number", "end"});
        put("in_number", new String[]{"end", "end", "in_number", "end"});
        put("end", new String[]{"end", "end", "end", "end"});
    }};

    public void get(char c) {
        state = table.get(state)[get_col(c)];
        if ("in_number".equals(state)) {
            ans = ans * 10 + c - '0';
            ans = sign == 1 ? Math.min(ans, (long) Integer.MAX_VALUE) : Math.min(ans, -(long) Integer.MIN_VALUE);
        } else if ("signed".equals(state)) {
            sign = c == '+' ? 1 : -1;
        }
    }

    private int get_col(char c) {
        if (c == ' ') {
            return 0;
        }
        if (c == '+' || c == '-') {
            return 1;
        }
        if (Character.isDigit(c)) {
            return 2;
        }
        return 3;
    }
}