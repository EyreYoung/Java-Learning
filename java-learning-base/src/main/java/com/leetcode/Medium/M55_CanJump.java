package com.leetcode.Medium;

/**
 * 55. 跳跃游戏
 * <p>
 * 给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * <p>
 * 判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,3,1,1,4]
 * 输出：true
 * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 * 示例 2：
 * <p>
 * 输入：nums = [3,2,1,0,4]
 * 输出：false
 * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 104
 * 0 <= nums[i] <= 105
 */
public class M55_CanJump {

    public static void main(String[] args) {
        M55_CanJump solution = new M55_CanJump();
        System.out.println(solution.canJump(new int[]{2,3,1,1,4})); // true
        System.out.println(solution.canJump(new int[]{3,2,1,0,4})); // false
    }

    public boolean canJump(int[] nums) {
        // max 记录目前为止，依靠前面的跳跃积累能够达到的最远下标
        int max = 0;
        
        for (int i = 0; i < nums.length; i++) {
            // 前提条件：你必须首先能够到达当前的位置 i (即 i <= max)
            if (i <= max) {
                // 利用当前位置提供的弹跳能力 (nums[i]) 加上当前位置的起点 (i)
                // 去刷新我们的“最远可达范围界限”
                max = Math.max(max, i + nums[i]);
                
                // 性能优化：如果在中途发现界限已经够到了最后一位，直接判定通关
                if (max >= nums.length - 1) return true;
            } else {
                // i > max 的情况：当你目前遍历的位置，超出了你所能跳到的极限
                // 说明遇到了无法跨越的鸿沟（比如前面一连串都是0），注定过不去了
                return false;
            }
        }
        
        // 兜底返回通关。比如数组只有一个元素 [0] 时，上面的 max >= nums.length - 1 满足后早早就返回 true了
        return true;
    }

}
