package 被围绕的区域;
/**
 * FileName: SurroundedRegions.java
 * 类的详细说明
 * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 * 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。
 * 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 *
 * @label Depth-firstSearch
 * @author &#x8c2f;&#x535a;
 * @Date 2020.8.11
 * @version 1.00
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SurroundedRegions {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个给定的二维矩阵：XXXX XOOX XXOX XXOX");
        while ((line = in.readLine()) != null) {
            char[][] chars = stringToChar2dArray(line);
            new Solution().solve(chars);
            for (char[] item : chars) {
                System.out.println(Arrays.toString(item));
            }
        }
    }

    public static char[][] stringToChar2dArray(String input) {
        if (input.length() == 0)
            return new char[0][];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char temp = input.charAt(i);
            if (temp == 'X' || temp == 'O') {
                sb.append(temp);
            }
        }
        int len = (int) Math.sqrt(sb.length());
        int step = 0;
        char[][] output = new char[len][len];

        for (int i = 0; i < output.length; i++) {
            for (int j = 0; j < output[0].length; j++) {
                if (step < sb.length()) {
                    output[i][j] = sb.charAt(step++);
                }
            }
        }
        return output;
    }
}

class Solution {
    private int row, col;

    public void solve(char[][] board) {
        if (board == null || board.length == 0)
            return;
        row = board.length;
        col = board[row - 1].length;
        //与边界相邻的O全部置为'-'
        for (int i = 0; i < row; i++) {
            dfs(board, i, 0);
            dfs(board, i, col - 1);
        }
        for (int j = 0; j < col; j++) {
            dfs(board, 0, j);
            dfs(board, row - 1, j);
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O')
                    board[i][j] = 'X';
                if (board[i][j] == '-')
                    board[i][j] = 'O';
            }
        }
    }

    private void dfs(char[][] board, int i, int j) {
        if (i < 0 || j < 0 || i >= row || j >= col || board[i][j] != 'O')
            return;
        board[i][j] = '-';
        dfs(board, i - 1, j);
        dfs(board, i, j + 1);
        dfs(board, i + 1, j);
        dfs(board, i, j - 1);
    }
}