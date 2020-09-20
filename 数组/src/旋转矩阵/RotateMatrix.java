package 旋转矩阵;
/**
 * FileName: RotateMatrix.java
 * 类的详细使用说明
 *
 * @label Array
 * @author &#x8c2f;&#x535a;
 * @Date 2020.4.20
 * @version 1.00
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RotateMatrix {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[][] matrix = stringToInteger2dArray(line);
            new Solution().rotate(matrix);
            for (int[] temps : matrix) {
                for (int temp : temps) {
                    System.out.print(temp + " ");
                }
                System.out.println();
            }
        }
    }

    public static int[][] stringToInteger2dArray(String input) {
        if (input.length() == 0 || input == null) return new int[0][];
        int len = (int) Math.sqrt(input.length());
        int step = 0;
        int[][] output = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (step < input.length()) {
                    output[i][j] = Integer.parseInt(input.charAt(step++)+"");
                }
            }
        }
        return output;
    }
}

class Solution {
    public void rotate(int[][] matrix) {
        //先以对角线（左下-右上）为轴翻转，然后以行中点进行翻转
        int len = matrix.length;
        int[][] temp = new int[len][len];
        for (int i = 0; i < len; i++) {
            int var = len - i - 1;
            for (int j = 0; j < len; j++) {
                temp[j][var] = matrix[i][j];
            }
        }
        for (int a = 0; a < len; a++) {
            for (int b = 0; b < len; b++) {
                matrix[a][b] = temp[a][b];
            }
        }
    }
}

class SolutionCopy {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // 先以对角线（左上-右下）为轴进行翻转
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
        // 再对每一行以中点进行翻转
        int mid = n >> 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < mid; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][n - 1 - j];
                matrix[i][n - 1 - j] = tmp;
            }
        }
    }
}