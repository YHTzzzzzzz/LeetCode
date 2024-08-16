package com.yin.algorithm;

/**
 * 45. 跳跃游戏 II
 * 中等
 * 相关标签
 * 相关企业
 * 给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
 * <p>
 * 每个元素 nums[i] 表示从索引 i 向前跳转的最大长度。换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处:
 * <p>
 * 0 <= j <= nums[i]
 * i + j < n
 * 返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 * 从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * 示例 2:
 * <p>
 * 输入: nums = [2,3,0,1,4]
 * 输出: 2
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= nums.length <= 104
 * 0 <= nums[i] <= 1000
 * 题目保证可以到达 nums[n-1]
 */
public class Lc45 {
    public static void main(String[] args) {
        int[] nums = {3,4,3,2,5,4,3};
        System.out.println(new Solution45().jump(nums));
    }
}

class Solution45 {
    public int jump(int[] nums) {
        return dfs(0, nums);
    }

    private int dfs(int i, int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 0;
        }
        if (i > n - 1) {
            return 1;
        }
        if (nums[i] >= n - i - 1) { // 如果当前值大于等于剩余长度，则直接返回
            return 1;
        }
        int index = 0, max = 0, res = 1;
        for (int j = i; j <= i + nums[i] && j < n -1; j++) {
            if (max < nums[j] + j) {
                max = nums[j] + j;
                index = j;
            }
        }
        res += dfs(index, nums);
        return res;
    }
}
