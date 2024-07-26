package com.yin.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 18. 四数之和
 * 给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]] （若两个四元组元素一一对应，则认为两个四元组重复）：
 *
 * 0 <= a, b, c, d < n
 * a、b、c 和 d 互不相同
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * 你可以按 任意顺序 返回答案 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,0,-1,0,-2,2], target = 0
 * 输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * 示例 2：
 *
 * 输入：nums = [2,2,2,2,2], target = 8
 * 输出：[[2,2,2,2]]
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 200
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 */
public class Lc18 {
    public static void main(String[] args) {

        Solution18 solution18 = new Solution18();
        System.out.println(solution18.fourSum(new int[]{2,2,2,2,2}, 8));
    }
}

class Solution18 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        if (n < 4) {
            return ans;
        }
        // 先排序
        Arrays.sort(nums);
        // 预留其他三个元素位置
        for (int i = 0; i < n - 3; i++) {
            long x = nums[i];
            if (i > 0 && nums[i - 1] == x) continue;
            if (x + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) break;
            if (x + nums[n - 3] + nums[n - 2] + nums[n - 1] < target) continue;
            for (int j = i + 1; j < n - 2; j++) {
                long y = nums[j];
                if (j > i + 1 && nums[j - 1] == y) continue;
                if (x + y + nums[j + 1] + nums[j + 2] > target) break;
                if (x + y + nums[n - 2] + nums[n - 1] < target) continue;
                int left = j + 1, right = n - 1;
                while (left < right) {
                    long sum = x + y + nums[left] + nums[right];
                    if (sum > target) right--;
                    else if (sum < target) left++;
                    else {
                        ans.add(List.of((int) x, (int) y, nums[left], nums[right]));
                        for(left++; left < right && nums[left] == nums[left - 1]; left++);
                        for(right--; left < right && nums[right] == nums[right + 1]; right--);
                    }
                }
            }
        }
        return ans;
    }
}
