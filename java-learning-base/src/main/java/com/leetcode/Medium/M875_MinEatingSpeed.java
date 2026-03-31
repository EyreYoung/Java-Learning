package com.leetcode.Medium;

/**
 * 875. 爱吃香蕉的珂珂
 * <p>
 * 珂珂喜欢吃香蕉。这里有 n 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 h 小时后回来。
 * <p>
 * 珂珂可以决定她吃香蕉的速度 k （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 k 根。如果这堆香蕉少于 k 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。
 * <p>
 * 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
 * <p>
 * 返回她可以在 h 小时内吃掉所有香蕉的最小速度 k（k 为整数）。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：piles = [3,6,7,11], h = 8
 * 输出：4
 * <p>
 * 示例 2：
 * <p>
 * 输入：piles = [30,11,23,4,20], h = 5
 * 输出：30
 * <p>
 * 示例 3：
 * <p>
 * 输入：piles = [30,11,23,4,20], h = 6
 * 输出：23
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= piles.length <= 104
 * piles.length <= h <= 109
 * 1 <= piles[i] <= 109
 */
public class M875_MinEatingSpeed {

    public static void main(String[] args) {
        M875_MinEatingSpeed solution = new M875_MinEatingSpeed();
        System.out.println(solution.minEatingSpeed(new int[]{3, 6, 7, 11}, 8)); // 4
        System.out.println(solution.minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 5)); // 30
        System.out.println(solution.minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 6)); // 23
    }

    /**
     * 思路：二分答案
     * 速度 k 和"能否在 h 小时内吃完"之间有单调性：
     *   k 越大 → 花的时间越少 → 越容易满足 ≤ h（ok 为 true）
     *   k 越小 → 花的时间越多 → 越不容易满足（ok 为 false）
     * 所以答案空间 [1, max(piles)] 可以被分成两段：
     *   [1 ... ans-1] → 全部 false（太慢，吃不完）
     *   [ans ... max]  → 全部 true（够快，吃得完）
     * 我们要找的就是第一个 true 的位置，即最小可行速度
     */
    public int minEatingSpeed(int[] piles, int h) {
        // 右边界：速度最大就是最大那堆的数量，每堆最多 1 小时搞定
        int max = piles[0];
        for (int i = 1; i < piles.length; i++) {
            max = Math.max(max, piles[i]);
        }

        // 二分搜索：在 [1, max] 上找最小的满足条件的速度
        // 用 l < r（不带等号），因为 r = k 不排除 k 本身
        // 如果用 l <= r，当 l == r 时 k = l，ok 为 true 则 r = k 不变，死循环
        int l = 1, r = max, k;
        while (l < r) {
            k = (l + r) / 2;
            if (ok(piles, h, k)) {
                // 速度 k 能吃完，但可能还能更慢 → 答案在 [l, k]
                // r = k（不是 k-1），因为 k 本身可能就是答案，不能排除
                r = k;
            } else {
                // 速度 k 吃不完，必须更快 → 答案在 [k+1, r]
                // l = k+1（不是 k），因为 k 已经确认不行，必须排除
                // 而且如果写 l = k，当 l 和 r 相邻时 k = l，l 不变会死循环
                l = k + 1;
            }
        }
        // 循环结束时 l == r，二者收敛到了同一个点，就是最小可行速度
        // 不能返回 k：k 是最后一轮的试探值，如果最后走了 else 分支
        // k 还停留在旧值，而 l 已经通过 k+1 前进到真正答案了
        return l;
    }

    /**
     * 验证函数：以速度 k 吃完所有堆，需要的总时间是否 ≤ h
     */
    public boolean ok(int[] piles, int h, int k) {
        int ret = 0, x;
        for (int i = 0; i < piles.length; i++) {
            x = piles[i];
            // 吃完一堆需要 ⌈x/k⌉ 小时（向上取整）
            // 整数向上取整技巧：⌈x/k⌉ = (x + k - 1) / k，避免浮点运算
            ret += (x + k - 1) / k;
        }
        return ret <= h;
    }

}
