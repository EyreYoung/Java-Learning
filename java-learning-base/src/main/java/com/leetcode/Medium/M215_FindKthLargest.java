package com.leetcode.Medium;


/**
 * 215. 数组中的第K个最大元素
 * <p>
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * <p>
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,1,5,6,4], k = 2
 * 输出: 5
 * 示例 2:
 * <p>
 * 输入: [3,2,3,1,2,4,5,5,6], k = 4
 * 输出: 4
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= k <= nums.length <= 105
 * -104 <= nums[i] <= 104
 */
public class M215_FindKthLargest {

    public static void main(String[] args) {
        System.out.println(findKthLargest(new int[]{3,2,1,5,6,4}, 2)); // 2
        System.out.println(findKthLargest(new int[]{3,2,3,1,2,4,5,5,6}, 4)); // 4
    }

    // 手写小顶堆，堆大小固定为 k，堆顶就是第 K 大
    public static int findKthLargest(int[] nums, int k) {
        int[] heap = new int[k];

        // 第一阶段：用前 k 个元素建堆，逐个插入并上浮
        for (int i = 0; i < k; i++) {
            heap[i] = nums[i];
            // 上浮：新元素和父节点比，比父小就交换，维持小顶堆
            int x = i;
            while (x > 0) {
                int parent = (x - 1) / 2;
                if (heap[x] < heap[parent]) {
                    swap(heap, x, parent);
                    x = parent;
                } else {
                    break;
                }
            }
        }

        // 第二阶段：遍历剩余元素，比堆顶大就替换并下沉
        for (int i = k; i < nums.length; i++) {
            if (nums[i] > heap[0]) { // 比堆顶大，有资格踢掉当前最小的
                heap[0] = nums[i];   // 替换堆顶
                // 下沉：堆顶和较小的子节点比，比子大就交换，维持小顶堆
                int x = 0;
                while (2 * x + 1 < k) {
                    // 选左右孩子中更小的那个
                    int child = (2 * x + 2) < k && heap[2 * x + 1] > heap[2 * x + 2] ? 2 * x + 2 : 2 * x + 1;
                    if (heap[x] > heap[child]) {
                        swap(heap, x, child);
                        x = child;
                    } else {
                        break; // 已经比两个孩子都小，堆恢复
                    }
                }
            }
        }

        return heap[0]; // 堆顶 = 第 K 大
    }

    private static void swap(int[] heap, int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

}
