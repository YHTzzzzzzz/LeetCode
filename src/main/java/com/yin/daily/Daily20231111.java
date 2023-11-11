package com.yin.daily;

//光棍节快乐
public class Daily20231111 {
    public static void main(String[] args) {
        int[] row = {3, 1, 0, 2};
        Solution20231111 solution = new Solution20231111();
        int i = solution.minSwapsCouples(row);
        System.out.println(i);
    }
}

class Solution20231111 {
    int[] fa;
    public int minSwapsCouples(int[] row) {
        int n = row.length, m = n / 2;
        fa = new int[m];
        for (int i = 0; i < m; i++) {
            fa[i] = i;
        }
        for (int i = 0; i < n; i+=2) {
            union(row[i] / 2, row[i+1] / 2);
        }
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            if (i == find(i)) {
                cnt++;
            }
        }
        return m - cnt;
    }
    void union(int a, int b) {
        fa[find(a)] = fa[find(b)];
    }
    int find(int x) {
        if (fa[x] != x) {
            fa[x] = find(fa[x]);
        }
        return fa[x];
    }
}
