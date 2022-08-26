package temp;

/**
 * @author slowdive
 * @summary
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/8/18
 */

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Description
 * You are given coins of different denominations and a total amount of money amount.
 * Write a function to compute the fewest number of coins that you need to make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 *
 *
 * You may assume that you have an infinite number of each kind of coin.
 * It is guaranteed that the num of money will not exceed 10000.
 * And the num of coins wii not exceed 500，The denomination of each coin will not exceed 100
 *
 * Example
 * Example1
 *
 * Input:
 * [1, 2, 5]
 * 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 *
 * Example2
 *
 * Input:
 * [2]
 * 3
 * Output: -1
 */

public class temp {
    public static void main(String[] args) {
        System.out.println(cal(new int[]{1, 2, 5, 10}, 11));
    }

    private static int ret;

    public static int cal(int[] coins, int target) {
        ret = -1;
        List<Integer> temp = new ArrayList<>();
        dfs(coins, 0, target, temp);
        return ret;
    }

    static void dfs(int[] coins, int start, int target, List<Integer> temp) {
        if (target == 0) {
            if (ret == -1) {
                ret = temp.size();
            } else {
                ret = Math.min(ret, temp.size());
            }
        } else if (target < 0) {
            return;
        }
        for (int i = start; i < coins.length; i++) {
            if (target - coins[i] >= 0) {
                temp.add(coins[i]);
                dfs(coins, start, target - coins[i], temp);
                temp.remove(temp.size() - 1);
            }
        }
    }
}
