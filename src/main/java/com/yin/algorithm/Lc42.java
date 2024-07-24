package com.yin.algorithm;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 */
public class Lc42 {
    public static void main(String[] args) {

        System.out.println(new Solution42().trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
    }
}

class Solution42 {
    /**
     * 坐标轴 x轴上每个位置可以看做是一个木桶，木桶左边是最大前缀，右边是最大后缀
     * 每个位置的存水量是当前位置 Math.min(最大前缀， 最大后缀) - 当前高度
     * 时间复杂度 O(n)
     * 空间复杂度 O(n)
     */
    public int trap(int[] height) {
        // 取一遍最大前缀
        int n = height.length;
        int[] preMax = new int[n];
        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, height[i]);
            preMax[i] = max;
        }
        // 取最大后缀
        int[] sufMax = new int[n];
        max = 0;
        for (int i = n -1; i >= 0; i--) {
            max = Math.max(max, height[i]);
            sufMax[i] = max;
        }
        int ans = 0;

        // 计算存水量
        for (int i = 0; i < n; i++) {
            int add = Math.min(preMax[i], sufMax[i]) - height[i];
            ans += Math.max(add, 0);
        }
        return ans;
    }

    /**
     * 寻找空间复杂度优化点
     */
    public int trapPrefer(int[] height) {
        int preMax = 0, sufMax = 0, ans = 0, left = 0, right = height.length - 1;
        while (left < right) {
            preMax = Math.max(preMax, height[left]);
            sufMax = Math.max(sufMax, height[right]);

            ans += preMax < sufMax ? preMax - height[left++] : sufMax - height[right--];
        }
        return ans;
    }
}
