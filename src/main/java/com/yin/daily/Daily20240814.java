package com.yin.daily;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 3152. 特殊数组 II
 * 中等
 * 相关标签
 * 相关企业
 * 提示
 * 如果数组的每一对相邻元素都是两个奇偶性不同的数字，则该数组被认为是一个 特殊数组 。
 *
 * 你有一个整数数组 nums 和一个二维整数矩阵 queries，对于 queries[i] = [fromi, toi]，请你帮助你检查
 * 子数组
 *  nums[fromi..toi] 是不是一个 特殊数组 。
 *
 * 返回布尔数组 answer，如果 nums[fromi..toi] 是特殊数组，则 answer[i] 为 true ，否则，answer[i] 为 false 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [3,4,1,2,6], queries = [[0,4]]
 *
 * 输出：[false]
 *
 * 解释：
 *
 * 子数组是 [3,4,1,2,6]。2 和 6 都是偶数。
 *
 * 示例 2：
 *
 * 输入：nums = [4,3,1,6], queries = [[0,2],[2,3]]
 *
 * 输出：[false,true]
 *
 * 解释：
 *
 * 子数组是 [4,3,1]。3 和 1 都是奇数。因此这个查询的答案是 false。
 * 子数组是 [1,6]。只有一对：(1,6)，且包含了奇偶性不同的数字。因此这个查询的答案是 true。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 * 1 <= queries.length <= 105
 * queries[i].length == 2
 * 0 <= queries[i][0] <= queries[i][1] <= nums.length - 1
 */
public class Daily20240814 {
    public static void main(String[] args) {
        int[] nums = {3, 4, 1, 1, 2, 5};
        int[][] queries = {{0, 4}};
        Solution20240814 solution20240814 = new Solution20240814();
        boolean[] res = solution20240814.isArraySpecial(nums, queries);
        for (boolean b : res) {
            System.out.println(b);
        }
    }
}

class Solution20240814 {
    /**
     * 暴力解法 超时
     */
    public boolean[] isArraySpecialBL(int[] nums, int[][] queries) {
        // 遍历nums获取其中子数组的下标
        Map<Integer, Integer> map = new HashMap<>();

        int from = 0;
        for (int to = 1; to < nums.length; to++) {
            if (nums[to-1] % 2 == nums[to] % 2) {
                map.put(from, to - 1);
                from = to;
            }
            if (to == nums.length - 1) {
                map.put(from, to);
            }
        }
        boolean[] res = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            if (queries[i][0] == queries[i][1]) {
                res[i] = true;
                continue;
            }
            int finalI = i;
            map.forEach((k, v) -> {
                if (k <= queries[finalI][0] && v >= queries[finalI][1]) {
                    res[finalI] = true;
                }
            });
        }
        return res;
    }
    
    /**
     * 前缀和
     */
    public boolean[] isArraySpecial(int[] nums, int[][] queries) {
        int[] s = new int[nums.length];
        for (int i = 1; i < nums.length; i++) {
            s[i] = s[i - 1] + (nums[i] % 2 == nums[i - 1] % 2 ? 1 : 0); // 前缀和
        }
        boolean[] res = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            res[i] = s[queries[i][0]] == s[queries[i][1]];
        }
        return res;
    }
}