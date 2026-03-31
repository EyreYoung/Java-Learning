package com.leetcode.Medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 253. Meeting Rooms II
 * <p>
 * 给定一个会议时间区间数组 intervals，其中 intervals[i] = [starti, endi]，表示第 i 个会议的开始和结束时间。
 * <p>
 * 返回安排所有会议所需的最少会议室数量。
 * <p>
 * 如果两个会议时间重叠，就不能放在同一个会议室里。
 * <p>
 * 示例 1：
 * <p>
 * 输入: intervals = [[0,30],[5,10],[15,20]]
 * 输出: 2
 * 示例 2：
 * <p>
 * 输入: intervals = [[7,10],[2,4]]
 * 输出: 1
 * 返回值：
 * <p>
 * 一个整数，表示最少需要几个会议室
 */
public class M253_MinMeetingRooms {

    public static void main(String[] args) {
        System.out.println(minMeetingRooms(new int[][]{{0,30},{5,10},{15,20}})); // 2
        System.out.println(minMeetingRooms(new int[][]{{7,10},{2,4}})); // 1

        System.out.println(minMeetingRooms2(new int[][]{{0,30},{5,10},{15,20}})); // 2
        System.out.println(minMeetingRooms2(new int[][]{{7,10},{2,4}})); // 1
    }

    /**
     * 方法一：小根堆
     * intervals[i][0] 是开始时间，intervals[i][1] 是结束时间
     * 返回安排完所有会议所需的最少会议室数量
     */
    public static int minMeetingRooms(int[][] intervals) {
        // 按开始时间排序，保证会议按进入时间依次处理
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        // 小根堆 q 存当前正在使用的会议室结束时间，堆顶是最早结束的会议
        PriorityQueue<Integer> q = new PriorityQueue<>();
        // max 记录遍历过程中堆的最大大小，也就是最多同时占用了几个会议室
        int max = 0;
        for (int[] interval : intervals) {
            // interval[0] 是当前会议开始时间，q.peek() 是最早结束的会议结束时间
            // 如果当前会议开始时，最早结束的会议已经结束，就能复用那个房间
            if (!q.isEmpty() && interval[0] >= q.peek()) {
                q.poll();
            }
            // 把当前会议的结束时间放进堆，表示它现在占用了一个会议室
            q.offer(interval[1]);
            // 堆的最大大小，就是同一时刻最多需要的会议室数量
            max = Math.max(max, q.size());
        }
        return max;
    }

    /**
     * 方法二：开始时间数组 + 结束时间数组 + 双指针
     * in 指向下一个要开始的会议，out 指向当前最早结束的会议
     * rooms 表示总共需要开过多少个会议室
     */
    public static int minMeetingRooms2(int[][] intervals) {
        int[] start = new int[intervals.length], end = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }
        // start 按开始时间排序，end 按结束时间排序
        // 这样就能只比较“下一个开始”和“当前最早结束”
        Arrays.sort(start);
        Arrays.sort(end);
        int in = 0, out = 0;
        int rooms = 0;
        for (; in < intervals.length; in++) {
            // start[in] < end[out]：新会议开始时，还没有房间空出来，只能新开房间
            if (start[in] < end[out]) {
                rooms++;
            } else {
                // 否则说明最早结束的会议已经结束，可以直接复用那个房间
                out++;
            }
        }
        // rooms 记录的就是整个过程中最少需要准备的会议室数量
        return rooms;
    }

}
