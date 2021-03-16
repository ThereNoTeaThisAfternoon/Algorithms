package 螺旋矩阵II;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * FileName: SpiralMatrixII.java
 * 类的详细说明
 * 给你一个正整数 n ，生成一个包含 1 到 n^2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 * <p>
 * 1 <= n <= 20
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2021.3.16 - 下午 8:50
 * @label Array
 */
public class SpiralMatrixII {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个正整数n：4");
        while ((line = in.readLine()) != null) {
            int n = Integer.parseInt(line);
            int[][] result = new Solution().generateMatrix(n);
            System.out.println(Arrays.toString(Arrays.stream(result).map(Arrays::toString).toArray()));
        }
    }
}

class Solution {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int[] direct = {-1, 1, 1, -1}; // 上 右 下 左
        int a = 0, b = 0;
        int count = n * n;
        int index = 1;
        while (index <= count) {
            while (b < n && matrix[a][b] == 0) {
                matrix[a][b] = index;
                b++;
                index++;
            }
            a += direct[2];
            b += direct[3];
            while (a < n && matrix[a][b] == 0) {
                matrix[a][b] = index;
                a++;
                index++;
            }
            a += direct[0];
            b += direct[3];
            while (b >= 0 && matrix[a][b] == 0) {
                matrix[a][b] = index;
                b--;
                index++;
            }
            a += direct[0];
            b += direct[1];
            while (a >= 0 && matrix[a][b] == 0) {
                matrix[a][b] = index;
                a--;
                index++;
            }
            a += direct[2];
            b += direct[1];
        }
        return matrix;
    }
}

class SolutionCopy {
    public int[][] generateMatrix(int n) {
        int l = 0, r = n - 1, t = 0, b = n - 1;
        int[][] mat = new int[n][n];
        int num = 1, tar = n * n;
        while (num <= tar) {
            for (int i = l; i <= r; i++) mat[t][i] = num++; // left to right.
            t++;
            for (int i = t; i <= b; i++) mat[i][r] = num++; // top to bottom.
            r--;
            for (int i = r; i >= l; i--) mat[b][i] = num++; // right to left.
            b--;
            for (int i = b; i >= t; i--) mat[i][l] = num++; // bottom to top.
            l++;
        }
        return mat;
    }
}

class SolutionCopy2 {
    public int[][] generateMatrix(int n) {
        int[][] arr = new int[n][n];
        int c = 1, j = 0;
        while (c <= n * n) {
            for (int i = j; i < n - j; i++) {
                arr[j][i] = c++;
            }
            for (int i = j + 1; i < n - j; i++) {
                arr[i][n - j - 1] = c++;
            }
            for (int i = n - j - 2; i >= j; i--) {
                arr[n - j - 1][i] = c++;
            }
            for (int i = n - j - 2; i > j; i--) {
                arr[i][j] = c++;
            }
            j++;
        }
        return arr;
    }
}