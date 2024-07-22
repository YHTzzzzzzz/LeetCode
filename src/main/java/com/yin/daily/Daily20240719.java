package com.yin.daily;

/**
 * 3096. 得到更多分数的最少关卡数目
 * 中等
 * 相关标签
 * 相关企业
 * 提示
 * 给你一个长度为 n 的二进制数组 possible 。
 *
 * Alice 和 Bob 正在玩一个有 n 个关卡的游戏，游戏中有一些关卡是 困难 模式，其他的关卡是 简单 模式。如果 possible[i] == 0 ，那么第 i 个关卡是 困难 模式。一个玩家通过一个简单模式的关卡可以获得 1 分，通过困难模式的关卡将失去 1 分。
 *
 * 游戏的一开始，Alice 将从第 0 级开始 按顺序 完成一些关卡，然后 Bob 会完成剩下的所有关卡。
 *
 * 假设两名玩家都采取最优策略，目的是 最大化 自己的得分，Alice 想知道自己 最少 需要完成多少个关卡，才能获得比 Bob 更多的分数。
 *
 * 请你返回 Alice 获得比 Bob 更多的分数所需要完成的 最少 关卡数目，如果 无法 达成，那么返回 -1 。
 *
 * 注意，每个玩家都至少需要完成 1 个关卡。
 *
 *
 *
 * 示例 1：
 *
 * 输入：possible = [1,0,1,0]
 *
 * 输出：1
 *
 * 解释：
 *
 * 我们来看一下 Alice 可以完成的关卡数目：
 *
 * 如果 Alice 只完成关卡 0 ，Bob 完成剩下的所有关卡，那么 Alice 获得 1 分，Bob 获得 -1 + 1 - 1 = -1 分。
 * 如果 Alice 完成到关卡 1 ，Bob 完成剩下的所有关卡，那么 Alice 获得 1 - 1 = 0 分，Bob 获得 1 - 1 = 0 分。
 * 如果 Alice 完成到关卡 2 ，Bob 完成剩下的所有关卡，那么 Alice 获得 1 - 1 + 1 = 1 分，Bob 获得 -1 分。
 * Alice 需要完成至少一个关卡获得更多的分数。
 *
 * 示例 2：
 *
 * 输入：possible = [1,1,1,1,1]
 *
 * 输出：3
 *
 * 解释：
 *
 * 我们来看一下 Alice 可以完成的关卡数目：
 *
 * 如果 Alice 只完成关卡 0 ，Bob 完成剩下的所有关卡，那么 Alice 获得 1 分，Bob 获得 4 分。
 * 如果 Alice 完成到关卡 1 ，Bob 完成剩下的所有关卡，那么 Alice 获得 2 分，Bob 获得 3 分。
 * 如果 Alice 完成到关卡 2 ，Bob 完成剩下的所有关卡，那么 Alice 获得 3 分，Bob 获得 2 分。
 * 如果 Alice 完成到关卡 3 ，Bob 完成剩下的所有关卡，那么 Alice 获得 4 分，Bob 获得 1 分。
 * Alice 需要完成至少三个关卡获得更多的分数。
 *
 * 示例 3：
 *
 * 输入：possible = [0,0]
 *
 * 输出：-1
 *
 * 解释：
 *
 * 两名玩家只能各完成 1 个关卡，Alice 完成关卡 0 得到 -1 分，Bob 完成关卡 1 得到 -1 分。两名玩家得分相同，所以 Alice 无法得到更多分数。
 *
 *
 *
 * 提示：
 *
 * 2 <= n == possible.length <= 105
 * possible[i] 要么是 0 要么是 1 。
 */
public class Daily20240719 {
    public static void main(String[] args) {
        int[] possible = new int[]{0, 0, 1};
        Solution20240719 solution = new Solution20240719();
        int i = solution.minimumLevels(possible);
        System.out.println(i);
    }
}

class Solution20240719 {
    /**
     * 第一次提交，超时啦！
     */
//    public int minimumLevels(int[] possible) {
//
//        int min = -1;
//
//        for (int i = 1; i < possible.length; i++) {
//            int aliceCount = 0, bobCount = 0;
//            // 计算到i时, alice的分数
//            for (int j = 0; j < i; j++) {
//                if (possible[j] == 1) {
//                    aliceCount++;
//                } else {
//                    aliceCount--;
//                }
//            }
//            for (int j = possible.length - 1; j >= i; j--) {
//                if (possible[j] == 1) {
//                    bobCount++;
//                } else {
//                    bobCount--;
//                }
//            }
//            if (aliceCount > bobCount) {
//                return i;
//            }
//        }
//
//        return min;
//    }

    /**
     * 从题解获取一点思路
     * 1. 计算总和
     * 2. 遍历计算alice得分
     * 3. bob得分为总分 - alice得分
     */
    public int minimumLevels(int[] possible) {
        int sum = 0;
        for (int i : possible) {
            sum += i == 0 ? -1 : 1;
        }

        int alice = 0;
        for (int i = 0; i < possible.length - 1; i++) {
            int temp = possible[i] == 0 ? -1 : 1;
            alice += temp;
            sum -= temp;

            if (sum < alice) {
                return i + 1;
            }
        }
        return -1;
    }

    /**
     * 从官方题解中获取优化策略
     * 正确题解满足表达式
     * sum - alice < alice 即 2 * alice > sum
     */
    public int minimumLevelsOptimize(int[] possible) {
        int sum = 0;
        for (int i : possible) {
            sum += i == 0 ? -1 : 1;
        }

        int alice = 0;
        for (int i = 0; i < possible.length - 1; i++) {
            alice += possible[i] == 0 ? -1 : 1;

//            if (sum < 2 * alice) {
//                return i+1;
//            }
            // 再优化一下
            if (sum < alice << 1) {
                return i + 1;
            }
        }
        return -1;
    }

    /**
     * 继续优化
     * 计算alice可以把1视作2，把0视作-2，这样无需计算 * 2
     * s = cnt1 - cnt0 = cnt1 - (n - cnt1) = 2 * cnt1 - n
     */
    public int minimumLevelsPerfect(int[] possible) {
        int sum = 0, n = possible.length;
        for (int i : possible) {
            sum += i;
        }
        sum = 2 * sum - n;

        int alice = 0;
        for (int i = 0; i < possible.length - 1; i++) {
//            alice += possible[i] == 0 ? -2 : 2;
            alice += (possible[i] * 4) - 2;

            if (sum < alice) {
                return i + 1;
            }
        }
        return -1;
    }
}