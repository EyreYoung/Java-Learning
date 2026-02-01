package com.leetcode.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class M56_Merge {

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}})));
    }

    public static int[][] merge(int[][] intervals) {
        sort(intervals);
        int i = 0;
        List<int[]> ret = new ArrayList<>();
        int start = intervals[0][0], end = intervals[0][1];
        while (i < intervals.length - 1) {
            i++;
            if (end >= intervals[i][0]) {
                if (end < intervals[i][1]) {
                    end = intervals[i][1];
                }
            } else {
                ret.add(new int[]{start, end});
                start = intervals[i][0];
                end = intervals[i][1];
            }
        }
        ret.add(new int[]{start, end});
        return ret.toArray(new int[ret.size()][]);
    }

    public static void sort(int[][] intervals) {
        int[] x;
        for (int i = 0; i < intervals.length; i++) {
            for (int j = 0; j < intervals.length - i - 1; j++) {
                if (intervals[j][0] > intervals[j + 1][0]) {
                    x = intervals[j];
                    intervals[j] = intervals[j + 1];
                    intervals[j + 1] = x;
                }
            }
        }
    }

}
