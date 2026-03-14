package com.leetcode.Medium;


import java.util.*;

/**
 * 347. 前 K 个高频元素
 * <p>
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,1,1,2,2,3], k = 2
 * <p>
 * 输出：[1,2]
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [1], k = 1
 * <p>
 * 输出：[1]
 * <p>
 * 示例 3：
 * <p>
 * 输入：nums = [1,2,1,2,1,2,3,1,3,2], k = 2
 * <p>
 * 输出：[1,2]
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * k 的取值范围是 [1, 数组中不相同的元素的个数]
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的
 * <p>
 * <p>
 * 进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。
 */
public class M347_TopKFrequent {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2)));
        System.out.println(Arrays.toString(topKFrequent(new int[]{1, 2, 1, 2, 1, 2, 3, 1, 3, 2}, 2)));
        System.out.println(Arrays.toString(topKFrequent(new int[]{1}, 1)));
    }

    public static int[] topKFrequent(int[] nums, int k) {
        List<Integer>[] bucket = new List[nums.length + 1];
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int freq = entry.getValue();
            if(bucket[freq] == null) bucket[freq] = new ArrayList<>();
            bucket[freq].add(entry.getKey());
        }
        List<Integer> ret = new ArrayList<>();
        for (int i = nums.length; i > 0 ; i--) {
            if (bucket[i] != null) {
                for (Integer j : bucket[i]) {
                    ret.add(j);
                    k--;
                    if (k == 0) break;
                }
                if (k == 0) break;
            }
        }
        return ret.stream().mapToInt(Integer::intValue).toArray();
    }

}
