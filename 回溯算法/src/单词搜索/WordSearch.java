package 单词搜索;

import 公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * FileName: WordSearch.java
 * 类的详细说明
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
 * 同一个单元格内的字母不允许被重复使用。
 *
 * @author &#x8c2f;&#x535a;
 * @verson 1.00
 * @Date 2020.9.13
 * @label Array Backtrack
 */
public class WordSearch {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try (in) {
            String line;
            System.out.println("请输入一个字符数组：[[A,B,C,E],[S,F,C,S],[A,D,E,E]]");
            while ((line = in.readLine()) != null) {
                char[][] board = PublicMethod.stringTo2DCharacterArray(line);
                System.out.println("请输入一个待匹配的字符串：ABCCED");
                String word = in.readLine();
                boolean result = new Solution().exist(board, word);
                System.out.println("匹配" + (result ? "成功" : "失败"));
            }
        }
    }
}

class Solution {

    public boolean exist(char[][] board, String word) {
        if (word.length() == 0) {
            return false;
        }
        char[] parts = word.toCharArray();
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; ++j) {
                if (exist(board, parts, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean exist(char[][] board, char[] parts, int i, int j, int index) {
        if (index >= parts.length) {
            return true;
        }
        if (i < 0 || i >= board.length || j < 0 || j >= board[i].length || board[i][j] != parts[index]) {
            return false;
        }
        board[i][j] += 111;
        boolean result = exist(board, parts, i, j + 1, index + 1)
                || exist(board, parts, i + 1, j, index + 1)
                || exist(board, parts, i, j - 1, index + 1)
                || exist(board, parts, i - 1, j, index + 1);
        board[i][j] -= 111;
        return result;
    }
}