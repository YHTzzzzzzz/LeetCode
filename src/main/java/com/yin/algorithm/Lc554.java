package com.yin.algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
题意说明：

给定一堵由多行砖块组成的墙，每一行由若干块砖组成，每块砖的宽度不同，但每一行的总宽度相同。

现在需要画一条从顶部到底部的垂直直线，使得这条线“穿过的砖块数量最少”。

规则说明：
1. 如果直线刚好经过砖与砖之间的边界（缝隙），则不算穿过砖块。
2. 不能选择最左侧或最右侧边界画线（否则不会穿过任何砖，题目不允许）。

问题转化：
- 对于每一行，计算从左到右的“前缀和”（表示砖的边界位置），但不包含最后一个边界（最右侧）。
- 统计每个边界位置在所有行中出现的次数。
- 找到出现次数最多的边界位置，说明这条竖线经过最多的“缝”，从而穿过最少的砖。

最终结果：
最少穿过的砖块数 = 墙的总行数 - 某个位置的最大边界出现次数
*/
public class Lc554 {
}

class Solution554 {
    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> map = new HashMap<>();
        for (List<Integer> arr : wall) {
            int p = 0;

            // 最后一块砖不能算（避免最右边界）
            for (int i = 0; i < arr.size() - 1; i++) {
                p += arr.get(i);   // 直接跳到“缝的位置”
                map.put(p, map.getOrDefault(p, 0) + 1);
            }
        }
        // 找最多的缝
        int max = 0;
        for (Integer count : map.values()) {
            max = Math.max(max, count);
        }

        return wall.size() - max;
    }
}
