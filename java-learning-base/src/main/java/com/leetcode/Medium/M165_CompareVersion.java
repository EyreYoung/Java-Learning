package com.leetcode.Medium;

/**
 * 165.比较版本号
 * <p>
 * 给你两个 版本号字符串 version1 和 version2 ，请你比较它们。版本号由被点 '.' 分开的修订号组成。修订号的值 是它 转换为整数 并忽略前导零。
 * <p>
 * 比较版本号时，请按 从左到右的顺序 依次比较它们的修订号。如果其中一个版本字符串的修订号较少，则将缺失的修订号视为 0。
 * <p>
 * 返回规则如下：
 * <p>
 * 如果 version1 < version2 返回 -1，
 * 如果 version1 > version2 返回 1，
 * 除此之外返回 0。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：version1 = "1.2", version2 = "1.10"
 * <p>
 * 输出：-1
 * <p>
 * 解释：
 * <p>
 * version1 的第二个修订号为 "2"，version2 的第二个修订号为 "10"：2 < 10，所以 version1 < version2。
 * <p>
 * 示例 2：
 * <p>
 * 输入：version1 = "1.01", version2 = "1.001"
 * <p>
 * 输出：0
 * <p>
 * 解释：
 * <p>
 * 忽略前导零，"01" 和 "001" 都代表相同的整数 "1"。
 * <p>
 * 示例 3：
 * <p>
 * 输入：version1 = "1.0", version2 = "1.0.0.0"
 * <p>
 * 输出：0
 * <p>
 * 解释：
 * <p>
 * version1 有更少的修订号，每个缺失的修订号按 "0" 处理。
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= version1.length, version2.length <= 500
 * version1 和 version2 仅包含数字和 '.'
 * version1 和 version2 都是 有效版本号
 * version1 和 version2 的所有修订号都可以存储在 32 位整数 中
 */

public class M165_CompareVersion {

    public static void main(String[] args) {
        System.out.println(compareVersion("1.2", "1.10"));
        System.out.println(compareVersion("1.01", "1.001"));
        System.out.println(compareVersion("1.0.0", "1.0"));
        System.out.println(compareVersion("1.0.11", "1.0.09"));
    }

    public static int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        int len = Math.max(v1.length, v2.length);
        for (int i = 0; i < len; i++) {
            int num1, num2;
            if(i >= v1.length) {
                num1 = 0;
            } else {
                num1 = Integer.parseInt(v1[i]);
            }
            if (i >= v2.length) {
                num2 = 0;
            } else {
                num2 = Integer.parseInt(v2[i]);
            }
            if (num1 < num2) return -1;
            else if (num1 > num2) return 1;
        }
        return 0;
    }

}
