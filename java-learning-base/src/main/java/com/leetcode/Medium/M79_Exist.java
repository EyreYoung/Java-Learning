package com.leetcode.Medium;

/**
 * 79. 单词搜索
 * <p>
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 * <p>
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：board = [['A','B','C','E'],['S','F','C','S'],['A','D','E','E']], word = "ABCCED"
 * 输出：true
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：board = [['A','B','C','E'],['S','F','C','S'],['A','D','E','E']], word = "SEE"
 * 输出：true
 * <p>
 * 示例 3：
 * <p>
 * <p>
 * 输入：board = [['A','B','C','E'],['S','F','C','S'],['A','D','E','E']], word = "ABCB"
 * 输出：false
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == board.length
 * n = board[i].length
 * 1 <= m, n <= 6
 * 1 <= word.length <= 15
 * board 和 word 仅由大小写英文字母组成
 * <p>
 * <p>
 * 进阶：你可以使用搜索剪枝的技术来优化解决方案，使其在 board 更大的情况下可以更快解决问题？
 */
public class M79_Exist {

    public static void main(String[] args) {
        System.out.println(exist(new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}}, "ABCCED")); // true
        System.out.println(exist(new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}}, "SEE")); // true
        System.out.println(exist(new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}}, "ABCB")); // false
        System.out.println(exist(new char[][]{{'a','a','a','a'},{'a','a','a','a'},{'a','a','a','a'}}, "aaaaaaaaaaaaa")); // false
    }

    public static boolean exist(char[][] board, String word) {
        // used[i][j] 表示当前这条搜索路径里，这个格子是否已经被使用过。
        boolean[][] used = new boolean[board.length][board[0].length];
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                // 以每个格子作为起点，尝试能不能搜出目标单词。
                if(dfs(board, used, i, j, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean dfs(char[][] board, boolean[][] used, int x, int y, String word, int index) {
        // 同一个格子在一条路径里不能重复使用。
        if(used[x][y]) return false;
        if(board[x][y] == word.charAt(index)) {
            // 当前字符已经匹配到单词最后一位，说明整条路径成立。
            if(index == word.length() - 1) return true;
            // 准备去匹配下一个字符。
            index++;
            // 先标记当前格子已使用，避免后续搜索又绕回来。
            used[x][y] = true;
            boolean b = false;
            // 按上、下、左、右四个方向继续搜索。
            if(x > 0) {
                b = dfs(board, used, x - 1, y, word, index);
                if(b) return true;
            }
            if(x < board.length - 1) {
                b = dfs(board, used, x + 1, y, word, index);
                if(b) return true;
            }
            if(y > 0) {
                b = dfs(board, used, x, y - 1, word, index);
                if(b) return true;
            }
            if(y < board[0].length - 1) {
                b = dfs(board, used, x, y + 1, word, index);
                if(b) return true;
            }
            // 四个方向都走不通，回溯时撤销当前格子的使用状态。
            used[x][y] = false;
        }
        return false;
    }

}
