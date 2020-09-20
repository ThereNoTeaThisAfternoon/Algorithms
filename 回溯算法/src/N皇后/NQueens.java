package N皇后;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * FileName: NQueens.java
 * 类的详细说明
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @Date 2020.9.3
 * @label Backtracking
 */
public class NQueens {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个n表示n个皇后：" + 8);
        while ((line = in.readLine()) != null) {
            List<List<String>> result = new Solution().solveNQueens(Integer.parseInt(line));
            if (result.size() != 0) {
                for (List<String> list : result) {
                    for (String s : list) {
                        System.out.println("[" + s + "]");
                    }
                }
            } else {
                System.out.println("Sorry Don't have this List");
            }
        }
    }

}

class Solution {

    public List<List<String>> solveNQueens(int n) {
        char[][] chess = new char[n][n];
        for (int i = 0; i < chess.length; i++) {
            for (int j = 0; j < chess.length; j++) {
                chess[i][j] = '.';
            }
        }
        List<List<String>> res = new ArrayList<>();
        solve(res, chess, 0);
        return res;
    }

    private void solve(List<List<String>> res, char[][] chess, int row) {
        if (row == chess.length) {
            res.add(construct(chess));
            return;
        }
        for (int col = 0; col < chess.length; col++) {
            //判断当前行是否可放皇后
            if (valid(chess, row, col)) {
                //为当前位置放置皇后
                chess[row][col] = 'Q';
                //递归到下一行
                solve(res, chess, row + 1);
                //回溯
                chess[row][col] = '.';
            }
        }
    }

    private boolean valid(char[][] chess, int row, int col) {
        //判断当前列上是否有皇后
        for (int i = row; i >= 0; --i) {
            if (chess[i][col] == 'Q')
                return false;
        }
        //判断当前坐标的右上角有没有皇后
        for (int i = row - 1, j = col + 1; i >= 0 && j < chess.length; i--, j++) {
            if (chess[i][j] == 'Q') {
                return false;
            }
        }
        //判断当前坐标的左上角有没有皇后
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (chess[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }

    //将数组转换成为list
    private List<String> construct(char[][] chess) {
        List<String> path = new ArrayList<>();
        for (char[] chars : chess) {
            path.add(new String(chars));
        }
        return path;
    }
}