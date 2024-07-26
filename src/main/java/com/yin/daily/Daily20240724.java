package com.yin.daily;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 给你一个下标从 0 开始的整数数组 nums ，表示一些石块的初始位置。再给你两个长度 相等 下标从 0 开始的整数数组 moveFrom 和 moveTo 。
 *
 * 在 moveFrom.length 次操作内，你可以改变石块的位置。在第 i 次操作中，你将位置在 moveFrom[i] 的所有石块移到位置 moveTo[i] 。
 *
 * 完成这些操作后，请你按升序返回所有 有 石块的位置。
 *
 * 注意：
 *
 * 如果一个位置至少有一个石块，我们称这个位置 有 石块。
 * 一个位置可能会有多个石块。
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,6,7,8], moveFrom = [1,7,2], moveTo = [2,9,5]
 * 输出：[5,6,8,9]
 * 解释：一开始，石块在位置 1,6,7,8 。
 * 第 i = 0 步操作中，我们将位置 1 处的石块移到位置 2 处，位置 2,6,7,8 有石块。
 * 第 i = 1 步操作中，我们将位置 7 处的石块移到位置 9 处，位置 2,6,8,9 有石块。
 * 第 i = 2 步操作中，我们将位置 2 处的石块移到位置 5 处，位置 5,6,8,9 有石块。
 * 最后，至少有一个石块的位置为 [5,6,8,9] 。
 * 示例 2：
 *
 * 输入：nums = [1,1,3,3], moveFrom = [1,3], moveTo = [2,2]
 * 输出：[2]
 * 解释：一开始，石块在位置 [1,1,3,3] 。
 * 第 i = 0 步操作中，我们将位置 1 处的石块移到位置 2 处，有石块的位置为 [2,2,3,3] 。
 * 第 i = 1 步操作中，我们将位置 3 处的石块移到位置 2 处，有石块的位置为 [2,2,2,2] 。
 * 由于 2 是唯一有石块的位置，我们返回 [2] 。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * 1 <= moveFrom.length <= 105
 * moveFrom.length == moveTo.length
 * 1 <= nums[i], moveFrom[i], moveTo[i] <= 109
 * 测试数据保证在进行第 i 步操作时，moveFrom[i] 处至少有一个石块。
 */
public class Daily20240724 {
    public static void main(String[] args) {

        Solution20240724 solution = new Solution20240724();
//        System.out.println(solution.relocateMarbles(new int[]{1,6,7,8}, new int[]{1,7,2}, new int[]{2,9,5}));
//        System.out.println(solution.relocateMarbles(new int[]{1,1,3,3}, new int[]{1,3}, new int[]{2,2}));
        System.out.println(solution.relocateMarbles(new int[]{3, 4}, new int[]{4,3,1,2,2,3,2,4,1}, new int[]{3,1,2,2,3,2,4,1,1}));
    }
}

class Solution20240724 {
    public List<Integer> relocateMarblesMyself(int[] nums, int[] moveFrom, int[] moveTo) {
        HashMap<Integer, Integer> store = new HashMap<>();
        for (int num : nums) {
            store.put(num, store.getOrDefault(num, 0) + 1);
        }

        int n = moveFrom.length;
        for (int i = 0; i < n; i++) {
            int from = moveFrom[i];
            int to = moveTo[i];
            if (from == to) {
                continue;
            }
            store.put(to, store.getOrDefault(to, 0) + store.get(from));
            store.put(from, 0);
        }

        return store.entrySet().stream().filter(e -> e.getValue() > 0).map(Map.Entry::getKey).sorted().collect(Collectors.toList());
    }

    public List<Integer> relocateMarbles(int[] nums, int[] moveFrom, int[] moveTo) {
        HashSet<Integer> ans = new HashSet<>(nums.length);

        for (int num : nums) {
            ans.add(num);
        }

        for (int i = 0; i < moveFrom.length; i++) {
            ans.remove(moveFrom[i]);
            ans.add(moveTo[i]);
        }

        return ans.stream().sorted().collect(Collectors.toList());
    }
}
