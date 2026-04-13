package com.leetcode.Easy;

/**
 * 268. 丢失的数字
 * <p>
 * 给定一个包含 [0, n] 中 n 个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [3,0,1]
 * <p>
 * 输出：2
 * <p>
 * 解释：n = 3，因为有 3 个数字，所以所有的数字都在范围 [0,3] 内。2 是丢失的数字，因为它没有出现在 nums 中。
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [0,1]
 * <p>
 * 输出：2
 * <p>
 * 解释：n = 2，因为有 2 个数字，所以所有的数字都在范围 [0,2] 内。2 是丢失的数字，因为它没有出现在 nums 中。
 * <p>
 * 示例 3：
 * <p>
 * 输入：nums = [9,6,4,2,3,5,7,0,1]
 * <p>
 * 输出：8
 * <p>
 * 解释：n = 9，因为有 9 个数字，所以所有的数字都在范围 [0,9] 内。8 是丢失的数字，因为它没有出现在 nums 中。
 * <p>
 * 提示：
 * <p>
 * n == nums.length
 * 1 <= n <= 104
 * 0 <= nums[i] <= n
 * nums 中的所有数字都 独一无二
 * <p>
 * <p>
 * 进阶：你能否实现线性时间复杂度、仅使用额外常数空间的算法解决此问题?
 */
public class E268_MissingNumber {

    public static void main(String[] args) {
        System.out.println(missingNumber(new int[]{3,0,1})); // 2
        System.out.println(missingNumber(new int[]{0,1})); // 2
        System.out.println(missingNumber(new int[]{9,6,4,2,3,5,7,0,1})); // 8
    }

    public static int missingNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            // 目标是把值 x 放到下标 x 的位置上。
            // 但如果 nums[i] == nums.length，说明这个数没有对应下标，先跳过。
            // 如果 nums[i] == i，说明当前位置已经放对了，也不需要处理。
            if (nums[i] != nums.length && nums[i] != i) {
                // 把当前数字 nums[i] 交换到它应该去的位置 nums[nums[i]]。
                int temp = nums[nums[i]];
                nums[nums[i]] = nums[i];
                nums[i] = temp;
                // 交换后当前位置来了一个新数字，需要重新检查一次。
                i--;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            // 第一处 nums[i] != i 的位置，就是缺失的数字 i。
            if (nums[i] != i) {
                return i;
            }
        }
        // 如果 0 ~ n-1 都在正确位置上，缺失的就是 n。
        return nums.length;
    }

}
