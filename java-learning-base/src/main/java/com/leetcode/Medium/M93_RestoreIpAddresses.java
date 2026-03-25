package com.leetcode.Medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 93. 复原 IP 地址
 * <p>
 * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 * <p>
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
 * 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。你 不能 重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "25525511135"
 * 输出：["255.255.11.135","255.255.111.35"]
 * 示例 2：
 * <p>
 * 输入：s = "0000"
 * 输出：["0.0.0.0"]
 * 示例 3：
 * <p>
 * 输入：s = "101023"
 * 输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 20
 * s 仅由数字组成
 */
public class M93_RestoreIpAddresses {

    public static void main(String[] args) {
        System.out.println(restoreIpAddresses("101023"));
        ret.clear();
        System.out.println(restoreIpAddresses("25525511135"));
    }

    public static List<String> ret = new ArrayList<>();

    public static List<String> restoreIpAddresses(String s) {
        restore(s, 0, new ArrayList<>());
        return ret;
    }

    // start: 当前截取起点，pre: 已选的 IP 段
    public static void restore(String s, int start, List<String> pre) {
        // 选满 4 段了
        if (pre.size() == 4) {
            if (start == s.length()) {       // 字符串也刚好用完，合法结果
                ret.add(String.join(".", pre));
            }
            return; // 不管用没用完都要 return，4 段就是上限
        }
        // 每段尝试截 1~3 个字符
        for (int i = 1; i <= 3; i++) {
            if (start + i > s.length()) break;  // 剩余字符不够了
            String sub = s.substring(start, start + i);
            // 合法条件：单字符直接合法；多字符要求无前导零且值 < 256
            if (i == 1 || (!sub.startsWith("0") && Integer.parseInt(sub) < 256)) {
                pre.add(sub);
                restore(s, start + i, pre);
                pre.remove(pre.size() - 1);  // 回溯，撤销选择
            }
        }
    }
}
