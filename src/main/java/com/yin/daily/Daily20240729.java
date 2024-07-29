package com.yin.daily;

import java.util.Stack;

/**
 * 682. 棒球比赛
 * 简单
 * 相关标签
 * 相关企业
 * 你现在是一场采用特殊赛制棒球比赛的记录员。这场比赛由若干回合组成，过去几回合的得分可能会影响以后几回合的得分。
 *
 * 比赛开始时，记录是空白的。你会得到一个记录操作的字符串列表 ops，其中 ops[i] 是你需要记录的第 i 项操作，ops 遵循下述规则：
 *
 * 整数 x - 表示本回合新获得分数 x
 * "+" - 表示本回合新获得的得分是前两次得分的总和。题目数据保证记录此操作时前面总是存在两个有效的分数。
 * "D" - 表示本回合新获得的得分是前一次得分的两倍。题目数据保证记录此操作时前面总是存在一个有效的分数。
 * "C" - 表示前一次得分无效，将其从记录中移除。题目数据保证记录此操作时前面总是存在一个有效的分数。
 * 请你返回记录中所有得分的总和。
 *
 *
 *
 * 示例 1：
 *
 * 输入：ops = ["5","2","C","D","+"]
 * 输出：30
 * 解释：
 * "5" - 记录加 5 ，记录现在是 [5]
 * "2" - 记录加 2 ，记录现在是 [5, 2]
 * "C" - 使前一次得分的记录无效并将其移除，记录现在是 [5].
 * "D" - 记录加 2 * 5 = 10 ，记录现在是 [5, 10].
 * "+" - 记录加 5 + 10 = 15 ，记录现在是 [5, 10, 15].
 * 所有得分的总和 5 + 10 + 15 = 30
 * 示例 2：
 *
 * 输入：ops = ["5","-2","4","C","D","9","+","+"]
 * 输出：27
 * 解释：
 * "5" - 记录加 5 ，记录现在是 [5]
 * "-2" - 记录加 -2 ，记录现在是 [5, -2]
 * "4" - 记录加 4 ，记录现在是 [5, -2, 4]
 * "C" - 使前一次得分的记录无效并将其移除，记录现在是 [5, -2]
 * "D" - 记录加 2 * -2 = -4 ，记录现在是 [5, -2, -4]
 * "9" - 记录加 9 ，记录现在是 [5, -2, -4, 9]
 * "+" - 记录加 -4 + 9 = 5 ，记录现在是 [5, -2, -4, 9, 5]
 * "+" - 记录加 9 + 5 = 14 ，记录现在是 [5, -2, -4, 9, 5, 14]
 * 所有得分的总和 5 + -2 + -4 + 9 + 5 + 14 = 27
 * 示例 3：
 *
 * 输入：ops = ["1"]
 * 输出：1
 *
 *
 * 提示：
 *
 * 1 <= ops.length <= 1000
 * ops[i] 为 "C"、"D"、"+"，或者一个表示整数的字符串。整数范围是 [-3 * 104, 3 * 104]
 * 对于 "+" 操作，题目数据保证记录此操作时前面总是存在两个有效的分数
 * 对于 "C" 和 "D" 操作，题目数据保证记录此操作时前面总是存在一个有效的分数
 */
public class Daily20240729 {
    public static void main(String[] args) {

        Solution20240729 solution20240729 = new Solution20240729();
//        System.out.println(solution20240729.calPointsPrefer(new String[]{"5","2","C","D","+"}));

        System.out.println(solution20240729.calPointsPrefer(new String[]{"5","-2","4","C","D","9","+","+"}));
    }
}

class Solution20240729 {
    public int calPoints(String[] operations) {
        // 定义得分堆栈
        Stack<Integer> score = new Stack<>();

        // 遍历操作循环
        for (String op : operations) {
            if ("+".equals(op)) {
                Integer last = score.pop();
                Integer last2 = score.pop();
                score.push(last2);
                score.push(last);
                score.push(last + last2);
            } else if ("D".equals(op)) {
                Integer last = score.pop();
                score.push(last);
                score.push(last * 2);
            } else if ("C".equals(op)) {
                score.pop();
            } else {
                score.push(Integer.parseInt(op));
            }
        }

        int sum = 0;
        for (Integer i : score) {
            sum += i;
        }
        return sum;
    }

    public int calPointsPrefer(String[] operations) {
        // 定义得分数组
        int[] score = new int[operations.length];
        int index = 0, sum = 0, cur = 0; // 定义数组当前下标 & 得分总和


        // 遍历操作循环
        for (String op : operations) {
            if ("+".equals(op)) {
                cur = score[index - 1] + score[index - 2];
                score[index++] = cur;
            } else if ("D".equals(op)) {
                cur = score[index - 1] * 2;
                score[index++] = cur;
            } else if ("C".equals(op)) {
                cur = -1 * score[--index];
            } else {
                cur = Integer.parseInt(op);
                score[index++] = cur;
            }
            sum += cur;
        }
        return sum;
    }
}
