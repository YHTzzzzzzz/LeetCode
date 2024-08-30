package com.yin.daily;

import java.util.HashMap;
import java.util.Map;

/**
 * 3153. 所有数对中数位不同之和
 * 中等
 * 相关标签
 * 相关企业
 * 提示
 * 你有一个数组 nums ，它只包含 正 整数，所有正整数的数位长度都 相同 。
 * <p>
 * 两个整数的 数位不同 指的是两个整数 相同 位置上不同数字的数目。
 * <p>
 * 请你返回 nums 中 所有 整数对里，数位不同之和。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [13,23,12]
 * <p>
 * 输出：4
 * <p>
 * 解释：
 * 计算过程如下：
 * - 13 和 23 的数位不同为 1 。
 * - 13 和 12 的数位不同为 1 。
 * - 23 和 12 的数位不同为 2 。
 * 所以所有整数数对的数位不同之和为 1 + 1 + 2 = 4 。
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [10,10,10,10]
 * <p>
 * 输出：0
 * <p>
 * 解释：
 * 数组中所有整数都相同，所以所有整数数对的数位不同之和为 0 。
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * 2 <= nums.length <= 105
 * 1 <= nums[i] < 109
 * nums 中的整数都有相同的数位长度。
 */
public class Daily20240830 {
    public static void main(String[] args) {
        Solution20240830 solution20240830 = new Solution20240830();
        long l = solution20240830.sumDigitDifferences(new int[]{13, 23, 12});
        System.out.println(l);
    }
}

/**
 * 假设你有 5 个数字，其中数字及其出现次数如下：
 * <p>
 * 数字 A 出现 2 次
 * 数字 B 出现 3 次
 * 数字 C 出现 1 次
 * 首先，找出不同的数字对：
 * <p>
 * A 和 B
 * A 和 C
 * B 和 C
 * 然后计算每对数字组合的总数：
 * 选择 A 和 B 的组合数：
 * 2×3=6
 * 选择 A 和 C 的组合数：
 * 2×1=2
 * 选择 B 和 C 的组合数：
 * 3×1=3
 * 总的组合数为：
 * 6+2+3=11
 * 所以，从这些数字中取出两个不同的元素一共有 11 种组合。
 */
class Solution20240830 {
    public long sumDigitDifferences(int[] nums) {
        // 遍历 nums
        Map<Integer, int[]> map = new HashMap<>();
        for (int num : nums) {
            int count = 1;
            while (num != 0) {
                int i = num % 10;
                int[] freq = map.getOrDefault(count, new int[10]);
                freq[i]++;
                map.put(count, freq);
                num /= 10;
                count++;
            }
        }
        long res = 0;
        for (Map.Entry<Integer, int[]> entry : map.entrySet()) {
            int[] v = entry.getValue();
            for (int i = 0; i < v.length; i++) {
                if (v[i] != 0) {
                    for (int j = i + 1; j < v.length; j++) {
                        res += (long) v[i] * v[j];
                    }
                }
            }
        }
        return res;
    }
}
