package temp;

import java.util.Arrays;

/**
 * @author slowdive
 * @summary 快速排序 快排
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/8/17
 */
public class solution {

    public static void main(String[] args) {
        String s = "sa";
        System.out.println(s.intern());

        int[] nums = new int[]{3, 1, 4, 7, 2, 9, 4};
        quickSort(nums, 0 , nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }

    static void quickSort (int[] nums, int left, int right) {
        if (left >= right) return;
        int i = left, j = right;
        // 把基准值拿出来，比较后移动的位置就空着，不用交换，最后再放基准值
        int tmp = nums[i];
        while (i < j) {
            // 从右向左找比tmp小的数
            while (i < j && nums[j] > tmp) {
                j--;
            }
            // 把右边比基准值小的数放到左边去
            // 记住！因为值放到左边了，所以要移动一下左指针
            // 右指针不用动，后续会把左边比较大的放过来
            if (i < j) {
                nums[i] = nums[j];
                i++;
            }
            // 从左往右找
            while (i < j && nums[i] < tmp) {
                i++;
            }
            if (i < j) {
                nums[j] = nums[i];
                j--;
            }
        }
        // 此时 i=j，这个位置就是基准值最终应该在数组中的位置
        nums[i] = tmp;
        // 左边排一遍
        quickSort(nums, left, i - 1);
        // 右边排一遍
        quickSort(nums, i + 1, right);
    }
}
