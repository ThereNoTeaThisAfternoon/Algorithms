package 解数独;

/**
 * FileName: SudokuSolver.java
 * 类的详细说明
 * 编写一个程序，通过已填充的空格来解决数独问题。
 * 一个数独的解法需遵循如下规则：
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * 空白格用 '.' 表示。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @Date 2020.9.15 - 下午 8:07
 * @label Backtracking
 */
public class SudokuSolver {
    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},

                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},

                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        Solution solution = new Solution();
        System.out.println("------------solve Before------------");
        printBoard(board);
        solution.solveSudoku(board);
        System.out.println("------------solve After------------");
        printBoard(board);
    }

    private static void printBoard(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}

class Solution {

    private char[] nums = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public void solveSudoku(char[][] board) {
        if (board == null || board.length < 1 || board[0].length < 1) {
            return;
        }
        dfs(board, 0, 0);

    }

    private boolean dfs(char[][] board, int y, int x) {
        if (x >= board[0].length) {//一行找完，进入下一行
            return dfs(board, y + 1, 0);
        }
        if (y >= board.length) {//行列都找完，结束
            return true;
        }
        if (board[y][x] != '.') {//没有数字，寻找下一个格子
            return dfs(board, y, x + 1);
        }
        for (char target : nums) {//升序遍历每一个可放入元素
            if (check(board, y, x, target)) {//如果当前元素可用，放入board中
                board[y][x] = target;
                if (dfs(board, y, x + 1)) {//下一个可填，也就是从最后一个格子依次返回的都是true
                    return true;
                } else {//回溯
                    board[y][x] = '.';
                }
            }
        }
        return false;
    }

    /**
     * 判断当前位置(y,x)是否可以填入该数字
     *
     * @param y      对应数组第几行
     * @param x      对应数组第几列
     * @param target 数字 1-9
     */
    private boolean check(char[][] board, int y, int x, char target) {
        for (int i = 0; i < board.length; i++) {
            if (target == board[y][i] || target == board[i][x]) {//判断横竖
                return false;
            }
        }
        for (int i = y / 3 * 3; i < (y / 3 + 1) * 3; ++i) {//从target所在九宫格左上角，依次遍历
            for (int j = x / 3 * 3; j < (x / 3 + 1) * 3; ++j) {
                if (board[i][j] == target) {
                    return false;
                }
            }
        }
        return true;
    }
}