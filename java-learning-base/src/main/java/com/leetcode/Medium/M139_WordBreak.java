package com.leetcode.Medium;

import com.google.common.collect.Lists;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 139. 单词拆分
 * <p>
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。如果可以利用字典中出现的一个或多个单词拼接出 s 则返回 true。
 * <p>
 * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * <p>
 * 输出: true
 * <p>
 * 解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
 * <p>
 * 示例 2：
 * <p>
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * <p>
 * 输出: true
 * <p>
 * 解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。
 * 注意，你可以重复使用字典中的单词。
 * <p>
 * 示例 3：
 * <p>
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * <p>
 * 输出: false
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 300
 * <p>
 * 1 <= wordDict.length <= 1000
 * <p>
 * 1 <= wordDict[i].length <= 20
 * <p>
 * s 和 wordDict[i] 仅由小写英文字母组成
 * <p>
 * wordDict 中的所有字符串 互不相同
 */
public class M139_WordBreak {

    public static void main(String[] args) {
        M139_WordBreak solution = new M139_WordBreak();
        System.out.println(solution.wordBreak("leetcode", Lists.newArrayList("leet", "code")));
        System.out.println(solution.wordBreak("applepenapple", Lists.newArrayList("apple", "pen")));
    }

    /**
     * 动态规划
     * <p>
     * dp[i] 表示 s 的前 i 个字符（s[0..i-1]）能否被字典中的单词拼出
     * <p>
     * 转移：枚举字典中每个单词 w，如果 s 的末尾恰好匹配 w，
     * 且去掉 w 后的前缀 dp[i - w.length()] 也为 true，则 dp[i] = true
     * <p>
     * 这里选择「枚举单词」而非「枚举分割点 j」，
     * 当字典较小时更高效，避免了 O(n) 次 substring + HashSet 查找
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        // 放入 HashSet 方便 O(1) 查找（本解法中实际直接遍历 set）
        Set<String> set = new HashSet<>(wordDict);

        // dp[i]: s 的前 i 个字符能否被拆分，多开一位，dp[0] 代表空串
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true; // 空串视为可拆分（base case）

        for (int i = 1; i <= s.length(); i++) {
            // 尝试每个字典单词作为「最后一个拼接的单词」
            for (String w : set) {
                int w_len = w.length();
                // 三个条件：
                // 1. i >= w_len：当前位置够长，能容纳这个单词
                // 2. dp[i - w_len]：去掉该单词后的前缀可拆分
                // 3. s[i-w_len, i) == w：末尾确实匹配该单词
                if (i >= w_len && dp[i - w_len] && s.substring(i - w_len, i).equals(w)) {
                    dp[i] = true;
                    break; // 找到一种拆法即可，无需继续尝试其他单词
                }
            }
        }
        return dp[s.length()];
    }

}
