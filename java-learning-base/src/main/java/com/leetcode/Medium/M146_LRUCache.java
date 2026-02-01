package com.leetcode.Medium;

import java.util.HashMap;

public class M146_LRUCache {

    public static void main(String[] args) {
        M146_LRUCache lRUCache = new M146_LRUCache(2);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        System.out.println(lRUCache.get(1));    // 返回 1
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        System.out.println(lRUCache.get(2));    // 返回 -1 (未找到)
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        System.out.println(lRUCache.get(1));    // 返回 -1 (未找到)
        System.out.println(lRUCache.get(3));    // 返回 3
        System.out.println(lRUCache.get(4));    // 返回 4
    }

    private final int capacity;
    private int size;
    private DLinkedNode head, tail;
    private HashMap<Integer, DLinkedNode> map; // 使用哈希表快速找到节点

    public M146_LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>(capacity, 1.0f);
        size = 0;
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            DLinkedNode node = map.get(key);
            moveToHead(node);
            return node.value; // 返回找到的值
        }
        return -1; // 如果关键字不存在，返回 -1
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            // 如果关键字已存在，更新值并移动到头部
            DLinkedNode node = map.get(key);
            node.value = value;
            moveToHead(node);
            return;
        }
        // 如果关键字不存在，检查容量
        if (size >= capacity) {
            // 如果容量已满，移除尾部节点
            map.remove(tail.prev.key);
            removeTail();
            size--;
        }
        size++;
        DLinkedNode node = new DLinkedNode(key, value);
        addToHead(node);
        map.put(key, node);
    }

    private static class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;

        private DLinkedNode() {}

        private DLinkedNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private void addToHead(DLinkedNode node) {
        node.next = this.head.next;
        node.prev = this.head;
        node.next.prev = node;
        this.head.next = node;
    }

    private void removeNode(DLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(DLinkedNode node) {
        if (node != tail && node != head) {
            removeNode(node);
            addToHead(node);
        }
    }

    private void removeTail() {
        if (tail.prev != head) {
            tail.prev.prev.next = tail;
            tail.prev.next = null;
            tail.prev = tail.prev.prev;
        }
    }

}
