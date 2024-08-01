package com.yin.daily;

import java.util.Arrays;

/**
 * LCP 40. 心算挑战
 * 简单
 * 相关标签
 * 相关企业
 * 「力扣挑战赛」心算项目的挑战比赛中，要求选手从 N 张卡牌中选出 cnt 张卡牌，若这 cnt 张卡牌数字总和为偶数，则选手成绩「有效」且得分为 cnt 张卡牌数字总和。 给定数组 cards 和 cnt，其中 cards[i] 表示第 i 张卡牌上的数字。 请帮参赛选手计算最大的有效得分。若不存在获取有效得分的卡牌方案，则返回 0。
 *
 * 示例 1：
 *
 * 输入：cards = [1,2,8,9], cnt = 3
 *
 * 输出：18
 *
 * 解释：选择数字为 1、8、9 的这三张卡牌，此时可获得最大的有效得分 1+8+9=18。
 *
 * 示例 2：
 *
 * 输入：cards = [3,3,1], cnt = 1
 *
 * 输出：0
 *
 * 解释：不存在获取有效得分的卡牌方案。
 *
 * 提示：
 *
 * 1 <= cnt <= cards.length <= 10^5
 * 1 <= cards[i] <= 1000
 */
public class Daily20240801 {
    public static void main(String[] args) {
        int[] cards = {1};
        int cnt = 1;
        System.out.println(new Solution20240801().maxmiumScore(cards, cnt));
    }
}

/**
* 简单来说就是从cards数组中，选出cnt个数字满足相加为偶数，返回最大的值
*/
class Solution20240801 {
    public int maxmiumScoreSelf(int[] cards, int cnt) {
        int n = cards.length;
        // 排序
        Arrays.sort(cards);
        int ans = 0, mixOdd = -1, mixEven = -1, cycle = cnt;
        while (cycle > 0) {
            int curr = cards[n - cycle];
            boolean isOdd = curr % 2 > 0;
            if (mixOdd == -1 && isOdd) {
                mixOdd = curr;
            }
            if (mixEven == -1 && !isOdd) {
                mixEven = curr;
            }
            ans += curr;
            cycle--;
        }
        if (ans % 2 == 0) {
            return ans;
        }
        // 从剩下数组中选出最大的奇数和最大的偶数
        int maxOdd = -1, maxEven = -1;
        for (int i = cards.length - 1 - cnt; i >= 0; i--) {
            if (maxOdd != -1 && maxEven != -1) {
                break;
            }
            boolean isOdd = cards[i] % 2 > 0;
            if (maxOdd == -1 && isOdd) {
                maxOdd = cards[i];
            }
            if (maxEven == -1 && !isOdd) {
                maxEven = cards[i];
            }
        }
        /**
         * 用剩下数组中最大的偶数替换ans中最小的奇数
         * 用剩下数组中最大的奇数替换ans中最小的偶数
         */
        int res1 = 0, res2 = 0;
        if (mixOdd != -1 && maxEven != -1) {
            res1 = ans - mixOdd + maxEven;
        }
        if (maxOdd != -1 && mixEven != -1) {
            res2 = ans - mixEven + maxOdd;
        }
        return Math.max(res1, res2);
    }

    // 优化让逻辑稍微清晰一点
    private int replaceSum(int[] cards, int cnt, int s, int x) {
        for (int i = cards.length - cnt - 1; i >= 0; i--) {
            if (cards[i] % 2 != x % 2) {
                return s - x + cards[i];
            }
        }
        return 0;
    }

    public int maxmiumScore(int[] cards, int cnt) {
        Arrays.sort(cards);
        int n = cards.length;
        int s = 0;
        for (int i = n - cnt; i < n; i++) {
            s += cards[i];
        }
        if (s % 2 == 0) {
            return s;
        }

        int x = cards[n - cnt];
        int ans = replaceSum(cards, cnt, s, x);
        for (int i = n - cnt + 1; i < n; i++) {
            if (cards[i] % 2 != x % 2) {
                ans = Math.max(ans, replaceSum(cards, cnt, s, cards[i]));
                break;
            }
        }
        return ans;
    }
}
