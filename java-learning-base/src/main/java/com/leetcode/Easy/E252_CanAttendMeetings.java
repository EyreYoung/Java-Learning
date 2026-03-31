package com.leetcode.Easy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 252. Meeting Rooms 会议室
 * <p>
 * 题目内容：
 * 给定一个会议时间区间数组 intervals，其中 intervals[i] = [starti, endi]，表示第 i 个会议的开始和结束时间。
 * <p>
 * 判断一个人是否能够参加所有会议。
 * <p>
 * 如果某两个会议时间重叠，这个人就不能同时参加，返回 false；如果所有会议都不重叠，返回 true。
 * <p>
 * 示例 1：
 * <p>
 * 输入: intervals = [[0,30],[5,10],[15,20]]
 * 输出: false
 * 示例 2：
 * <p>
 * 输入: intervals = [[7,10],[2,4]]
 * 输出: true
 * 返回值：
 * <p>
 * true：能参加所有会议
 * false：不能参加所有会议
 */
public class E252_CanAttendMeetings {

    public static void main(String[] args) {
        System.out.println(canAttendMeetings(new int[][]{{0,30},{5,10},{15,20}}));
        System.out.println(canAttendMeetings(new int[][]{{7,10},{2,4}}));
    }

    public static boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= intervals[i - 1][1]) {
                return false;
            }
        }
        return true;
    }

}
