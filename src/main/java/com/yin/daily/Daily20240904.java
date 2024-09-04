package com.yin.daily;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 2860. 让所有学生保持开心的分组方法数
 * 中等
 * 相关标签
 * 相关企业
 * 提示
 * 给你一个下标从 0 开始、长度为 n 的整数数组 nums ，其中 n 是班级中学生的总数。班主任希望能够在让所有学生保持开心的情况下选出一组学生：
 * <p>
 * 如果能够满足下述两个条件之一，则认为第 i 位学生将会保持开心：
 * <p>
 * 这位学生被选中，并且被选中的学生人数 严格大于 nums[i] 。
 * 这位学生没有被选中，并且被选中的学生人数 严格小于 nums[i] 。
 * 返回能够满足让所有学生保持开心的分组方法的数目。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,1]
 * 输出：2
 * 解释：
 * 有两种可行的方法：
 * 班主任没有选中学生。
 * 班主任选中所有学生形成一组。
 * 如果班主任仅选中一个学生来完成分组，那么两个学生都无法保持开心。因此，仅存在两种可行的方法。
 * 示例 2：
 * <p>
 * 输入：nums = [6,0,3,3,6,7,2,7]
 * 输出：3
 * 解释：
 * 存在三种可行的方法：
 * 班主任选中下标为 1 的学生形成一组。
 * 班主任选中下标为 1、2、3、6 的学生形成一组。
 * 班主任选中所有学生形成一组。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 105
 * 0 <= nums[i] < nums.length
 */
public class Daily20240904 {
    public static void main(String[] args) {
        int[] nums = {6, 0, 3, 3, 6, 7, 2, 7};
        System.out.println(new Solution20240904().countWays(Arrays.stream(nums).boxed().collect(Collectors.toList())));
    }
}

class Solution20240904 {
    // 超时
    public int countWaysOutTime(List<Integer> nums) {
        nums = nums.stream().sorted().collect(Collectors.toList());
        int n = nums.size(), pick = 0, res = 0; // 从一个也不选开始
        while (pick <= n) {
            boolean valid = true; // 当前方案是否合理
            int picking = 0;
            for (Integer num : nums) {
                if (pick > num) { // 选中的条件
                    picking++;
                    if (picking > pick) { // 指标已经超出，方案不满足
                        valid = false;
                        break;
                    }
                }
                if (pick == num) { // 一定不开心
                    valid = false;
                    break;
                }
                if (pick < num) { // 由于排序，接下来一定也不会选中
                    break;
                }
            }
            res += valid && picking == pick ? 1 : 0; // 满足条件
            pick++; // 挑选指标加一
        }
        return res;
    }

    /**
     * 0 <= nums[i] < nums.length 这个条件保证全选一定满足条件 被选中的学生人数 严格大于 nums[i]
     * 排序后如果 num.get(i) 选，则 [0, i - 1] 一定要选
     * 反之 num.get(i) 不选，则 [i, n - 1] 一定不选
     */
    public int countWays(List<Integer> nums) {
        nums = nums.stream().sorted().collect(Collectors.toList());
        int res = 0;
        res += nums.get(0) > 0 ? 1 : 0; // 全不选，满足 nums.get(0) > 0
        int pick = 1, n = nums.size();
        while (pick <= n - 1) {
            res += nums.get(pick - 1) < pick && nums.get(pick) > pick ? 1 : 0;
            pick++;
        }
        return ++res;
    }
}
