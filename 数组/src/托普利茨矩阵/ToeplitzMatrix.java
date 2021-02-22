package 托普利茨矩阵;

import Array公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * FileName: ToeplitzMatrix.java
 * 类的详细说明
 * 给你一个 m x n 的矩阵 matrix 。如果这个矩阵是托普利茨矩阵，返回 true ；否则，返回 false 。
 * 如果矩阵上每一条由左上到右下的对角线上的元素都相同，那么这个矩阵是 托普利茨矩阵 。
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 20
 * 0 <= matrix[i][j] <= 99
 * <p>
 * 进阶：
 * 如果矩阵存储在磁盘上，并且内存有限，以至于一次最多只能将矩阵的一行加载到内存中，该怎么办？
 * 如果矩阵太大，以至于一次只能将不完整的一行加载到内存中，该怎么办？
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2021.2.22 - 下午 9:54
 * @label
 */
public class ToeplitzMatrix {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个整型二维数组matrix：[[1,2,3,4],[5,1,2,3],[9,5,1,2]]");
        while ((line = in.readLine()) != null) {
            int[][] matrix = PublicMethod.stringTo2DIntegerArray(line);
            boolean result = new SolutionCopy().isToeplitzMatrix(matrix);
            System.out.println("该矩阵" + (result ? "" : "不") + "是托普利茨矩阵");
        }
    }
}

class Solution {

    public boolean isToeplitzMatrix(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[row - 1].length;
        // 列
        for (int j = col - 1; j >= 0; j--) {
            int x = j;
            for (int y = 0; x + 1 < col && y + 1 < row; y++) {
                if (matrix[y][x] != matrix[y + 1][++x]) {
                    return false;
                }
            }
        }
        // 行
        for (int i = 1; i < row; ++i) {
            int y = i;
            for (int x = 0; y + 1 < row && x + 1 < col; ++x) {
                if (matrix[y][x] != matrix[++y][x + 1]) {
                    return false;
                }
            }
        }
        return true;
    }
}

class SolutionCopy {

    public boolean isToeplitzMatrix(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[row - 1].length;
        for (int i = 0; i < row - 1; ++i) {
            for (int j = 0; j < col - 1; ++j) {
                if (matrix[i][j] != matrix[i + 1][j + 1]) {
                    return false;
                }
            }
        }
        return true;
    }
}