package com.yin.algorithm;

/**
 * 给你一个下标从 1 开始的整数数组 numbers ，该数组已按 非递减顺序排列  ，请你从数组中找出满足相加之和等于目标数 target 的两个数。如果设这两个数分别是 numbers[index1] 和 numbers[index2] ，则 1 <= index1 < index2 <= numbers.length 。
 *
 * 以长度为 2 的整数数组 [index1, index2] 的形式返回这两个整数的下标 index1 和 index2。
 *
 * 你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的元素。
 *
 * 你所设计的解决方案必须只使用常量级的额外空间。
 *
 *
 * 示例 1：
 *
 * 输入：numbers = [2,7,11,15], target = 9
 * 输出：[1,2]
 * 解释：2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。返回 [1, 2] 。
 *
 * 提示：
 * 2 <= numbers.length <= 3 * 104
 * -1000 <= numbers[i] <= 1000
 * numbers 按 非递减顺序 排列
 * -1000 <= target <= 1000
 * 仅存在一个有效答案
 */
public class Lc167 {
    public static void main(String[] args) {

        Solution167 solution167 = new Solution167();
        int[] numbers = {2, 7, 11, 15};
        int target = 9;
        int[] ints = solution167.twoSum(numbers, target);
        System.out.println(ints[0] + " " + ints[1]);
    }
}

/**
 * 暴力做法：
 * 两层 for 循环解决
 * 时间复杂度 O(n^2)
 * 优化：
 * 首尾数字相加 > target 说明中间与末尾相加一定大于 target, 可以排除 右端点
 * 首尾数字相加 < target 说明中间与首位相加一定小于 target, 可以排除 左端点
 * 时间复杂度 O(n)
 */
class Solution167 {
    int[] ret;
    public int[] twoSum(int[] numbers, int target) {
        ret = new int[2];
        check(0, numbers.length - 1, numbers, target);
        return ret;
    }

    void check(int left, int right, int[] numbers, int target) {
        if (numbers[left] + numbers[right] == target) {
            ret[0] = left + 1;
            ret[1] = right + 1;
            return;
        }
        if (numbers[left] + numbers[right] > target) {
            check(left, right - 1, numbers, target);
        } else {
            check(left + 1, right, numbers, target);
        }
    }

    // 简化写法
    public int[] twoSumSimple(int[] numbers, int target) {
        int left = 0, right = numbers.length - 1;
        while(left < right) {
            // 题目保证答案一定存在，可以替换为 while(true)
            int s = numbers[left] + numbers[right];
            if (s == target) {
                break;
            } else if (s > target) {
                right--;
            } else {
                left++;
            }
        }
        return new int[] {left + 1, right + 1};
    }
}