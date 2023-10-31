package com.yin.daily;

public class Daily20231030 {

}

class Solution20231030 {
    public int hIndex(int[] citations) {
        int n = citations.length;
        int start = 0, end = n - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (citations[mid] >= n - mid) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return n - start;
    }
}
