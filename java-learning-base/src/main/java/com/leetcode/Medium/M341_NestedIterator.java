package com.leetcode.Medium;

import java.util.*;

/**
 * @author slowdive
 * @summary 扁平化嵌套列表迭代器
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/3/3
 */

//给你一个嵌套的整数列表 nestedList 。每个元素要么是一个整数，要么是一个列表；该列表的元素也可能是整数或者是其他列表。请你实现一个迭代器将其扁平化
//，使之能够遍历这个列表中的所有整数。
//
// 实现扁平迭代器类 NestedIterator ：
//
//
// NestedIterator(List<NestedInteger> nestedList) 用嵌套列表 nestedList 初始化迭代器。
// int next() 返回嵌套列表的下一个整数。
// boolean hasNext() 如果仍然存在待迭代的整数，返回 true ；否则，返回 false 。
//
//
// 你的代码将会用下述伪代码检测：
//
//
//initialize iterator with nestedList
//res = []
//while iterator.hasNext()
//    append iterator.next() to the end of res
//return res
//
// 如果 res 与预期的扁平化列表匹配，那么你的代码将会被判为正确。
//
//
//
// 示例 1：
//
//
//输入：nestedList = [[1,1],2,[1,1]]
//输出：[1,1,2,1,1]
//解释：通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,1,2,1,1]。
//
// 示例 2：
//
//
//输入：nestedList = [1,[4,[6]]]
//输出：[1,4,6]
//解释：通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,4,6]。
//
//
//
//
// 提示：
//
//
// 1 <= nestedList.length <= 500
// 嵌套列表中的整数值在范围 [-10⁶, 10⁶] 内
//
// Related Topics 栈 树 深度优先搜索 设计 队列 迭代器 👍 418 👎 0

public class M341_NestedIterator {

    public static void main(String[] args) {
        List<NestedInteger> nestedInteger = Collections.singletonList(
                new NestedInteger(null,
                        Arrays.asList(new NestedInteger(1, null),
                                new NestedInteger(2, null),
                                new NestedInteger(null,
                                        Arrays.asList(new NestedInteger(3, null),
                                                new NestedInteger(4, null))),
                                new NestedInteger(5, null))));
        NestedIterator iterator = new NestedIterator(nestedInteger);
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public static class NestedIterator implements Iterator<Integer> {

        private Queue<Integer> queue = new LinkedList<>();

        public NestedIterator(List<NestedInteger> nestedList) {
            find(nestedList);
        }

        void find(List<NestedInteger> nestedList) {
            nestedList.forEach(i -> {
                if (i.isInteger()) {
                    queue.add(i.getInteger());
                } else {
                    find(i.getList());
                }
            });
        }

        @Override
        public Integer next() {
            return hasNext() ? queue.poll() : null;
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }
    }

    public static class NestedInteger {
        public Integer i;
        public List<NestedInteger> is;

        public boolean isInteger() {
            return is == null;
        }

        public NestedInteger(Integer i, List<NestedInteger> is) {
            this.i = i;
            this.is = is;
        }

        public Integer getInteger() {
            return i;
        }

        public List<NestedInteger> getList() {
            return is;
        }
    }

}
