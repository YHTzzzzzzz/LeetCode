package com.yin.algorithm;

import java.util.Arrays;
import java.util.logging.Level;

/**
 * 611. 有效三角形的个数
 * 中等
 * 相关标签
 * 相关企业
 * 给定一个包含非负整数的数组 nums ，返回其中可以组成三角形三条边的三元组个数。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [2,2,3,4]
 * 输出: 3
 * 解释:有效的组合是:
 * 2,3,4 (使用第一个 2)
 * 2,3,4 (使用第二个 2)
 * 2,2,3
 * 示例 2:
 *
 * 输入: nums = [4,2,3,4]
 * 输出: 4
 *
 *
 * 提示:
 *
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 1000
 */
public class Lc611 {
    public static void main(String[] args) {

        Solution611 solution611 = new Solution611();
        System.out.println(solution611.triangleNumberPrefer(new int[]{2, 2, 3, 4}));
    }
}

/**
* 组成三角形的条件是两边之和 > 第三边
*/
class Solution611 {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int ans = 0, n = nums.length;
        for (int i = n - 1; i >= 0; i--) {
            int max = nums[i];
            for (int j = 0; j < i; j++) {
                int min = nums[j];
                int k = j + 1;
                while (k < i) {
                    int mid = nums[k];
                    if (min + mid > max) {
                        ans++;
                    }
                    k++;
                }
            }
        }
        return ans;
    }

    public int triangleNumberPrefer(int[] nums) {
        Arrays.sort(nums);
        int ans = 0, n = nums.length;
        for (int i = 2; i < n; i++) {
            int left = 0, right = i - 1;
            while (left < right) {
                if (nums[left] + nums[right] > nums[i]) {
                    ans += right - left;
                    right--;
                } else {
                    left++;
                }
            }
        }
        return ans;
    }
}
