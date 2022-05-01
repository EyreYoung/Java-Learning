package com.leetcode.Medium;

//这里有 n 个航班，它们分别从 1 到 n 进行编号。
//
// 有一份航班预订表 bookings ，表中第 i 条预订记录 bookings[i] = [firsti, lasti, seatsi] 意味着在从
//firsti 到 lasti （包含 firsti 和 lasti ）的 每个航班 上预订了 seatsi 个座位。
//
// 请你返回一个长度为 n 的数组 answer，里面的元素是每个航班预定的座位总数。
//
//
//
// 示例 1：
//
//
//输入：bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
//输出：[10,55,45,25,25]
//解释：
//航班编号        1   2   3   4   5
//预订记录 1 ：   10  10
//预订记录 2 ：       20  20
//预订记录 3 ：       25  25  25  25
//总座位数：      10  55  45  25  25
//因此，answer = [10,55,45,25,25]
//
//
// 示例 2：
//
//
//输入：bookings = [[1,2,10],[2,2,15]], n = 2
//输出：[10,25]
//解释：
//航班编号        1   2
//预订记录 1 ：   10  10
//预订记录 2 ：       15
//总座位数：      10  25
//因此，answer = [10,25]
//
//
//
//
// 提示：
//
//
// 1 <= n <= 2 * 10⁴
// 1 <= bookings.length <= 2 * 10⁴
// bookings[i].length == 3
// 1 <= firsti <= lasti <= n
// 1 <= seatsi <= 10⁴
//
// Related Topics 数组 前缀和 👍 342 👎 0

import java.util.Arrays;

/**
 * 航班预定统计 同M370区间加法
 */
public class M1109_CorpFlightBookings {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(corpFlightBookings(new int[][]{{1, 2, 10}, {2, 3, 20}, {2, 5, 25}}, 5)));
    }

    public static int[] corpFlightBookings(int[][] bookings, int n) {
        int[] a = new int[n];
        int left, right, num;
        for (int[] booking : bookings) {
            left = booking[0] - 1;
            right = booking[1] - 1;
            num = booking[2];
            a[left] += num;
            if (right < n - 1)
                a[right + 1] -= num;
        }
        int[] recovery = new int[n];
        recovery[0] = a[0];
        for (int i = 1; i < n; i++) {
            recovery[i] = recovery[i - 1] + a[i];
        }
        return recovery;
    }

}
