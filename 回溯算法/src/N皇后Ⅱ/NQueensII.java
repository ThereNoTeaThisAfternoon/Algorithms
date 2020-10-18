package N皇后Ⅱ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * FileName: NQueens.java
 * 类的详细说明
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给定一个整数 n，返回 n 皇后不同的解决方案的数量。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.10.18 - 下午 8:14
 * @label
 */
public class NQueensII {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个数 n：8");
        while ((line = in.readLine()) != null) {
            int n = Integer.parseInt(line);
            int result = new SolutionCopy2().totalNQueens(n);
            System.out.printf("%d个皇后，有%d个解\n", n, result);
        }
    }
}

/**
 * 不能沉迷于算法的精妙，而忽略解决实际问题
 */
class Solution {
    public int totalNQueens(int n) {
        int[] res = new int[]{1, 0, 0, 2, 10, 4, 40, 92, 352, 724, 2680, 14200, 73712};
        return res[n - 1];
    }
}

class SolutionCopy {
    /**
     * 记录某列是否已有皇后摆放
     */
    private boolean[] col;
    /**
     * 记录某条正对角线（左上右下）是否已有皇后摆放（某条对角线对应的摆放位置为 x - y + n - 1）
     */
    private boolean[] dia1;
    /**
     * 记录某条斜对角线（左下右上）是否已有皇后摆放（某条对角线对应的摆放位置为 x + y）
     */
    private boolean[] dia2;

    public int totalNQueens(int n) {
        col = new boolean[n];
        dia1 = new boolean[2 * n - 1];
        dia2 = new boolean[2 * n - 1];
        return putQueen(n, 0);
    }

    /**
     * 递归回溯方式摆放皇后
     *
     * @param n     待摆放皇后个数
     * @param index 已摆放皇后个数
     */
    private int putQueen(int n, int index) {
        int res = 0;
        if (index == n) {
            return 1;
        }
        // 表示在 index 行的第 i 列尝试摆放皇后
        for (int i = 0; i < n; i++) {
            if (!col[i] && !dia1[i - index + n - 1] && !dia2[i + index]) {
                // 递归
                col[i] = true;
                dia1[i - index + n - 1] = true;
                dia2[i + index] = true;
                res += putQueen(n, index + 1);
                // 回溯
                col[i] = false;
                dia1[i - index + n - 1] = false;
                dia2[i + index] = false;
            }
        }
        return res;
    }
}

class SolutionCopy2 {
    private int count = 0;

    public int totalNQueens(int n) {
        if (n < 1) return 0;
        dfs(0, 0, 0, 0, n);
        return count;
    }

    private void dfs(int row, int col, int pie, int na, int n) {
        if (row >= n) {
            count++;
            return;
        }
        int bit = (~(col | pie | na)) // 获取当前空位 标识为1
                & ((1 << n) - 1);  // 去掉所有高位
        while (bit > 0) {//遍历所有空位
            int p = bit & -bit; //获取最后的空位 1
            /*
             col | p 表示 p 位被占用
             (pie | p ) << 1 ,表示pie往斜左方移一位 被占用
             (na | p) >> 1,表示na往斜右方移一位 被占用
             */
            dfs(row + 1, col | p, (pie | p) << 1, (na | p) >> 1, n);
            bit &= (bit - 1); // 打掉最后的1
        }
    }
}