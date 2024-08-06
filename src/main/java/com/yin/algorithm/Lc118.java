package com.yin.algorithm;

import java.util.*;

/**
 * 118. 杨辉三角
 * 简单
 * 相关标签
 * 相关企业
 * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
 *
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 *
 *
 *
 *
 *
 * 示例 1:
 *
 * 输入: numRows = 5
 * 输出: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 * 示例 2:
 *
 * 输入: numRows = 1
 * 输出: [[1]]
 *
 *
 * 提示:
 *
 * 1 <= numRows <= 30
 */
public class Lc118 {
}

class Solution118 {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>(numRows);
        ans.add(List.of(1));
        for (int i = 2; i <= numRows; i++) {
            List<Integer> list = new ArrayList<>(i);
            List<Integer> pre = ans.get(i - 2);
            list.add(1);
            for (int j = 1; j < i - 1; j++) {
                list.add(pre.get(j - 1) + pre.get(j));
            }
            list.add(1);
            ans.add(list);
        }
        return ans;
    }
}
