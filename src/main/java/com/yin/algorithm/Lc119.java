package com.yin.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 119. 杨辉三角 II
 * 简单
 * 相关标签
 * 相关企业
 * 给定一个非负索引 rowIndex，返回「杨辉三角」的第 rowIndex 行。
 *
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 *
 * 示例 1:
 *
 * 输入: rowIndex = 3
 * 输出: [1,3,3,1]
 * 示例 2:
 *
 * 输入: rowIndex = 0
 * 输出: [1]
 * 示例 3:
 *
 * 输入: rowIndex = 1
 * 输出: [1,1]
 *
 *
 * 提示:
 *
 * 0 <= rowIndex <= 33
 */
public class Lc119 {
    public static void main(String[] args) {
        Solution119 solution119 = new Solution119();
        System.out.println(solution119.getRow(2));
    }
}

class Solution119 {
    // 先不优化
    public List<Integer> getRow(int rowIndex) {
//        List<Integer> ans = List.of(1);
//        for (int i = 1; i <= rowIndex; i++) {
//            List<Integer> newAns = new ArrayList<>(i + 1);
//            newAns.add(1);
//            for (int j = 1; j < i; j++) {
//                newAns.add(ans.get(j - 1) + ans.get(j));
//            }
//            newAns.add(1);
//            ans = newAns;
//        }
//        return ans;
        List<Integer> dp = new ArrayList<>();
        dp.add(1);
        for(int i=1;i<=rowIndex;i++) {
            dp.add((int)((long)dp.get(i-1)*(rowIndex-i+1)/i));
        }
        return dp;
    }
}
