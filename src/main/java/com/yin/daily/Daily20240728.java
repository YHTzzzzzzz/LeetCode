package com.yin.daily;

import java.util.ArrayList;
import java.util.List;

/**
 * 699. 掉落的方块
 * 困难
 * 相关标签
 * 相关企业
 * 提示
 * 在二维平面上的 x 轴上，放置着一些方块。
 *
 * 给你一个二维整数数组 positions ，其中 positions[i] = [lefti, sideLengthi] 表示：第 i 个方块边长为 sideLengthi ，其左侧边与 x 轴上坐标点 lefti 对齐。
 *
 * 每个方块都从一个比目前所有的落地方块更高的高度掉落而下。方块沿 y 轴负方向下落，直到着陆到 另一个正方形的顶边 或者是 x 轴上 。一个方块仅仅是擦过另一个方块的左侧边或右侧边不算着陆。一旦着陆，它就会固定在原地，无法移动。
 *
 * 在每个方块掉落后，你必须记录目前所有已经落稳的 方块堆叠的最高高度 。
 *
 * 返回一个整数数组 ans ，其中 ans[i] 表示在第 i 块方块掉落后堆叠的最高高度。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：positions = [[1,2],[2,3],[6,1]]
 * 输出：[2,5,5]
 * 解释：
 * 第 1 个方块掉落后，最高的堆叠由方块 1 组成，堆叠的最高高度为 2 。
 * 第 2 个方块掉落后，最高的堆叠由方块 1 和 2 组成，堆叠的最高高度为 5 。
 * 第 3 个方块掉落后，最高的堆叠仍然由方块 1 和 2 组成，堆叠的最高高度为 5 。
 * 因此，返回 [2, 5, 5] 作为答案。
 * 示例 2：
 *
 * 输入：positions = [[100,100],[200,100]]
 * 输出：[100,100]
 * 解释：
 * 第 1 个方块掉落后，最高的堆叠由方块 1 组成，堆叠的最高高度为 100 。
 * 第 2 个方块掉落后，最高的堆叠可以由方块 1 组成也可以由方块 2 组成，堆叠的最高高度为 100 。
 * 因此，返回 [100, 100] 作为答案。
 * 注意，方块 2 擦过方块 1 的右侧边，但不会算作在方块 1 上着陆。
 *
 *
 * 提示：
 *
 * 1 <= positions.length <= 1000
 * 1 <= lefti <= 108
 * 1 <= sideLengthi <= 106
 */
public class Daily20240728 {
}

class Solution20240728 {
    public List<Integer> fallingSquares(int[][] positions) {
        int n = positions.length;
        List<Integer> heights = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            int left1 = positions[i][0], right1 = positions[i][0] + positions[i][1] - 1;
            int height = positions[i][1];
            for (int j = 0; j < i; j++) {
                int left2 = positions[j][0], right2 = positions[j][0] + positions[j][1] - 1;
                if (right1 >= left2 && right2 >= left1) {
                    height = Math.max(height, heights.get(j) + positions[i][1]);
                }
            }
            heights.add(height);
        }
        for (int i = 1; i < n; i++) {
            heights.set(i, Math.max(heights.get(i), heights.get(i - 1)));
        }
        return heights;
    }
}
