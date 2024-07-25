package com.yin.daily;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 2844. 生成特殊数字的最少操作
 * 中等
 * 相关标签
 * 相关企业
 * 提示
 * 给你一个下标从 0 开始的字符串 num ，表示一个非负整数。
 *
 * 在一次操作中，您可以选择 num 的任意一位数字并将其删除。请注意，如果你删除 num 中的所有数字，则 num 变为 0。
 *
 * 返回最少需要多少次操作可以使 num 变成特殊数字。
 *
 * 如果整数 x 能被 25 整除，则该整数 x 被认为是特殊数字。
 *
 *
 *
 *
 *
 * 示例 1：
 *
 * 输入：num = "2245047"
 * 输出：2
 * 解释：删除数字 num[5] 和 num[6] ，得到数字 "22450" ，可以被 25 整除。
 * 可以证明要使数字变成特殊数字，最少需要删除 2 位数字。
 * 示例 2：
 *
 * 输入：num = "2908305"
 * 输出：3
 * 解释：删除 num[3]、num[4] 和 num[6] ，得到数字 "2900" ，可以被 25 整除。
 * 可以证明要使数字变成特殊数字，最少需要删除 3 位数字。
 * 示例 3：
 *
 * 输入：num = "10"
 * 输出：1
 * 解释：删除 num[0] ，得到数字 "0" ，可以被 25 整除。
 * 可以证明要使数字变成特殊数字，最少需要删除 1 位数字。
 *
 *
 * 提示
 *
 * 1 <= num.length <= 100
 * num 仅由数字 '0' 到 '9' 组成
 * num 不含任何前导零
 */
public class Daily20240725 {
    public static void main(String[] args) {
        System.out.println(new Solution20240725().minimumOperations("2245047"));
    }
}

/**
 * 要被25整除，那么一定是00, 25, 50, 75结尾，有个特殊情况是0
 */
class Solution20240725 {
    public int minimumOperations(String num) {
        char end = '0';
        char[] charArray = num.toCharArray();
        // p0表示以0结尾删除的元素个数，p5表示以5结尾删除的元素个数
        int p0 = 0, p5 = 0;
        boolean zeroFlag = false, fiveFlag = false, zeroFinish = false, fiveFinish = false;
        for (int i = charArray.length - 1; i >= 0; i--) {
            char c = charArray[i];
            int abs = c - end;
            // 以 0 结尾未结束
            if (!zeroFinish) {
                if (!zeroFlag) {
                    if (abs == 0) {
                        zeroFlag = true;
                    } else {
                        p0++;
                    }
                } else {
                    // 寻找 0 或 5
                    if (abs == 0 || abs == 5) {
                        zeroFinish = true;
                    } else {
                        p0++;
                    }
                }
            }

            // 以 5 结尾未结束
            if (!fiveFinish) {
                if (!fiveFlag) {
                    if (abs == 5) {
                        fiveFlag = true;
                    } else {
                        p5++;
                    }
                } else {
                    // 寻找 2 或 7
                    if (abs == 2 || abs == 7) {
                        fiveFinish = true;
                    } else {
                        p5++;
                    }
                }
            }
        }
        if (zeroFinish && fiveFinish) {
            return Math.min(p0, p5);
        } else if (zeroFinish) {
            return p0;
        } else if (fiveFinish) {
            return p5;
        } else {
            if (zeroFlag) {
                return num.length() - 1;
            } else {
                return num.length();
            }
        }
    }
    
    /**
    * 思考复杂了，简化下
    */
    public int minimumOperations2(String num) {
        int n = num.length();
        boolean found0 = false;
        boolean found5 = false;
        for (int i = n - 1; i >= 0; i--) {
            char c = num.charAt(i);
            if (found0 && (c == '0' || c == '5') ||
                    found5 && (c == '2' || c == '7')) {
                return n - i - 2;
            }
            if (c == '0') {
                found0 = true;
            } else if (c == '5') {
                found5 = true;
            }
        }
        return found0 ? n - 1 : n;
    }
}
