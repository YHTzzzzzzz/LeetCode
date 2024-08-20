package com.yin.daily;

import java.util.HashMap;
import java.util.Map;

/**
 * 3154. 到达第 K 级台阶的方案数
 * 困难
 * 相关标签
 * 相关企业
 * 提示
 * 给你有一个 非负 整数 k 。有一个无限长度的台阶，最低 一层编号为 0 。
 * <p>
 * Alice 有一个整数 jump ，一开始值为 0 。Alice 从台阶 1 开始，可以使用 任意 次操作，目标是到达第 k 级台阶。假设 Alice 位于台阶 i ，一次 操作 中，Alice 可以：
 * <p>
 * 向下走一级到 i - 1 ，但该操作 不能 连续使用，如果在台阶第 0 级也不能使用。
 * 向上走到台阶 i + 2jump 处，然后 jump 变为 jump + 1 。
 * 请你返回 Alice 到达台阶 k 处的总方案数。
 * <p>
 * 注意，Alice 可能到达台阶 k 处后，通过一些操作重新回到台阶 k 处，这视为不同的方案。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：k = 0
 * <p>
 * 输出：2
 * <p>
 * 解释：
 * <p>
 * 2 种到达台阶 0 的方案为：
 * <p>
 * Alice 从台阶 1 开始。
 * 执行第一种操作，从台阶 1 向下走到台阶 0 。
 * Alice 从台阶 1 开始。
 * 执行第一种操作，从台阶 1 向下走到台阶 0 。
 * 执行第二种操作，向上走 20 级台阶到台阶 1 。
 * 执行第一种操作，从台阶 1 向下走到台阶 0 。
 * 示例 2：
 * <p>
 * 输入：k = 1
 * <p>
 * 输出：4
 * <p>
 * 解释：
 * <p>
 * 4 种到达台阶 1 的方案为：
 * <p>
 * Alice 从台阶 1 开始，已经到达台阶 1 。
 * Alice 从台阶 1 开始。
 * 执行第一种操作，从台阶 1 向下走到台阶 0 。
 * 执行第二种操作，向上走 20 级台阶到台阶 1 。
 * Alice 从台阶 1 开始。
 * 执行第二种操作，向上走 20 级台阶到台阶 2 。
 * 执行第一种操作，向下走 1 级台阶到台阶 1 。
 * Alice 从台阶 1 开始。
 * 执行第一种操作，从台阶 1 向下走到台阶 0 。
 * 执行第二种操作，向上走 20 级台阶到台阶 1 。
 * 执行第一种操作，向下走 1 级台阶到台阶 0 。
 * 执行第二种操作，向上走 21 级台阶到台阶 2 。
 * 执行第一种操作，向下走 1 级台阶到台阶 1 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= k <= 109
 */
public class Daily20240820 {
    public static void main(String[] args) {
        Solution20240820 solution = new Solution20240820();
        System.out.println(solution.waysToReachStair(5));
    }
}

class Solution20240820 {
    public int waysToReachStair(int k) {
        Map<Long, Integer> memo = new HashMap<>();
        return dfs(1, 0, 0, k, memo);
    }

    /**
     * i：当前台阶
     * j：当前jump
     * preDown：下一步是否向下走
     * k：目标台阶
     */
    int dfs(int i, int j, int preDown, int k, Map<Long, Integer> memo) {
        // 递归边界
        if (i > k + 1) { // 只能往下走一格
            return 0;
        }
        // 压缩数据
        long key = ((long) i << 32) | ((long) j << 1) | preDown;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        int ans = i == k ? 1 : 0;
        ans += dfs(i + (1 << j), j + 1, 0, k, memo); // 向上走
        if (preDown == 0 && i > 0) {
            ans += dfs(i - 1, j, 1, k, memo); // 向下走
        }
        memo.put(key, ans);
        return ans;
    }
}
