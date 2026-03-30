package com.leetcode.Medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 208. 实现 Trie (前缀树)
 * <p>
 * Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补全和拼写检查。
 * <p>
 * 请你实现 Trie 类：
 * <p>
 * Trie() 初始化前缀树对象。
 * void insert(String word) 向前缀树中插入字符串 word 。
 * boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
 * boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入
 * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
 * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
 * 输出
 * [null, null, true, false, true, null, true]
 * <p>
 * 解释
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // 返回 True
 * trie.search("app");     // 返回 False
 * trie.startsWith("app"); // 返回 True
 * trie.insert("app");
 * trie.search("app");     // 返回 True
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= word.length, prefix.length <= 2000
 * word 和 prefix 仅由小写英文字母组成
 * insert、search 和 startsWith 调用次数 总计 不超过 3 * 104 次
 */
public class M208_Trie {

    public static void main(String[] args) {
        // 解法一测试
        System.out.println("--- 解法一：HashMap + 递归 ---");
        M208_Trie trie = new M208_Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));   // 返回 True
        System.out.println(trie.search("app"));     // 返回 False
        System.out.println(trie.startsWith("app")); // 返回 True
        trie.insert("app");
        System.out.println(trie.search("app"));     // 返回 True

        // 解法二测试
        System.out.println("--- 解法二：数组 + 迭代 ---");
        ArrayTrie trie2 = new ArrayTrie();
        trie2.insert("apple");
        System.out.println(trie2.search("apple"));   // 返回 True
        System.out.println(trie2.search("app"));     // 返回 False
        System.out.println(trie2.startsWith("app")); // 返回 True
        trie2.insert("app");
        System.out.println(trie2.search("app"));     // 返回 True
    }

    // ==================== 解法一：HashMap + 递归 ====================

    public char c;                                    // 当前节点代表的字符
    public Map<Character, M208_Trie> map = new HashMap<>();  // 子节点映射：字符 -> 子 Trie 节点
    public boolean flag = false;                      // 是否是某个完整单词的结尾

    /** 根节点构造，不代表任何字符 */
    public M208_Trie() {
    }

    /** 子节点构造，记录当前节点对应的字符 */
    public M208_Trie(char c) {
        this.c = c;
    }

    public void insert(String word) {
        insert(word, 0);
    }

    /**
     * 递归插入：用 index 指针逐字符往下走
     * - index 走到末尾 → 标记当前节点为单词结尾
     * - 否则取 word[index]，有子节点就递归进去，没有就新建
     */
    public void insert(String word, int index) {
        if (word.length() == index) {
            flag = true;  // 到达单词末尾，标记 isEnd
            return;
        }
        char cc = word.charAt(index);
        if (map.containsKey(cc)) {
            map.get(cc).insert(word, index + 1);      // 子节点已存在，继续往下走
        } else {
            M208_Trie t = new M208_Trie(cc);          // 创建新子节点
            map.put(cc, t);
            t.insert(word, index + 1);                // 从新节点继续递归
        }
    }

    public boolean search(String word) {
        return search(word, 0);
    }

    /**
     * 递归查找完整单词
     * - index 到末尾 → 返回 flag（必须是完整单词的结尾）
     * - 否则检查子节点是否存在并递归
     */
    public boolean search(String word, int index) {
        if (word.length() == index) {
            return flag;  // 到末尾了，看这个位置是不是一个完整单词
        }
        char cc = word.charAt(index);
        return map.containsKey(cc) && map.get(cc).search(word, index + 1);
    }

    public boolean startsWith(String prefix) {
        return startsWith(prefix, 0);
    }

    /**
     * 递归查找前缀
     * - index 到末尾 → 直接返回 true（只要路径走得通就行，不需要 flag）
     * - 与 search 的唯一区别：终止时不检查 isEnd
     */
    public boolean startsWith(String prefix, int index) {
        if (prefix.length() == index) return true;  // 前缀匹配完毕，无需检查 flag
        char cc = prefix.charAt(index);
        return map.containsKey(cc) && map.get(cc).startsWith(prefix, index + 1);
    }

    // ==================== 解法二：数组 + 迭代 ====================
    static class ArrayTrie {
        private ArrayTrie[] children = new ArrayTrie[26];  // 26 个子节点槽位，下标 = c - 'a'
        private boolean isEnd = false;                      // 是否是某个完整单词的结尾

        /**
         * 迭代插入：用 node 指针从根节点逐字符往下走
         * - 子节点已存在 → 直接走过去
         * - 子节点不存在 → 新建后再走过去
         * - 走完所有字符后标记 isEnd
         */
        public void insert(String word) {
            int i = 0;
            if (word.isEmpty()) return;
            ArrayTrie node = this;              // 从根节点出发
            while (i < word.length()) {
                int x = word.charAt(i) - 'a';   // 字符映射到 0~25 的下标
                ArrayTrie child;
                if (node.children[x] != null) {
                    child = node.children[x];    // 路径已存在，直接复用
                } else {
                    child = new ArrayTrie();     // 路径不存在，新建节点
                    node.children[x] = child;
                }
                node = child;                    // 指针下移
                i++;
            }
            node.isEnd = true; // 最后一个字符，标记单词结尾
        }

        /** 查找完整单词：走到底 + 检查 isEnd */
        public boolean search(String word) {
            ArrayTrie node = searchPrefix(word);
            return node != null && node.isEnd;   // 路径存在 且 是完整单词
        }

        /** 查找前缀：走到底即可，不检查 isEnd */
        public boolean startsWith(String prefix) {
            return searchPrefix(prefix) != null;  // 只要路径走得通就行
        }

        /**
         * 公共辅助方法：沿着字符路径走到底
         * - 走得通 → 返回终点节点
         * - 走不通 → 返回 null
         * search 和 startsWith 都复用这个方法，区别仅在于最后是否检查 isEnd
         */
        private ArrayTrie searchPrefix(String prefix) {
            ArrayTrie node = this;               // 从根节点出发
            for (char c : prefix.toCharArray()) {
                int x = c - 'a';                 // 字符映射到下标
                if(node.children[x] == null) {
                    return null;                 // 路径断了，匹配失败
                } else {
                    node = node.children[x];     // 继续往下走
                }
            }
            return node;                         // 路径走完，返回终点节点
        }
    }

}
