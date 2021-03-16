package 螺旋矩阵;

import Array公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * FileName: SpiralMatrix.java
 * 类的详细说明
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 10
 * -100 <= matrix[i][j] <= 100
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2021.3.16 - 下午 8:33
 * @label Array
 */
public class SpiralMatrix {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个二维整型矩阵matrix：[[1,2,3,4],[5,6,7,8],[9,10,11,12]]");
        while ((line = in.readLine()) != null) {
            int[][] matrix = PublicMethod.stringTo2DIntegerArray(line);
            List<Integer> list = new SolutionCopy().spiralOrder(matrix);
            System.out.println(Arrays.toString(list.toArray()));
        }
    }
}

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int wall_up = 0, wall_left = 0;
        int wall_right = matrix[0].length;
        int wall_down = matrix.length;

        int i = 0, j = 0;

        int count = wall_down * wall_right;
        int index = 0;
        while (index < count) {
            while (j < wall_right) {
                res.add(matrix[i][j++]);
                index++;
            }
            i++;
            j--;
            wall_up++;
            if (index == count) {
                break;
            }
            while (i < wall_down) {
                res.add(matrix[i++][j]);
                index++;
            }
            j--;
            i--;
            wall_right--;
            if (index == count) {
                break;
            }
            while (j >= wall_left) {
                res.add(matrix[i][j--]);
                index++;
            }
            i--;
            j++;
            wall_down--;
            if (index == count) {
                break;
            }
            while (i >= wall_up) {
                res.add(matrix[i--][j]);
                index++;
            }
            j++;
            i++;
            wall_left++;
            if (index == count) {
                break;
            }
        }
        return res;
    }
}

class SolutionCopy {

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix.length == 0 || matrix[0].length == 0) {
            return res;
        }
        int m = matrix[0].length, n = matrix[0].length;
        // 确定上下左右四条边的位置
        int up = 0, down = m - 1, left = 0, right = n - 1;
        while (true) {
            for (int i = left; i <= right; i++) {
                res.add(matrix[up][i]);
            }
            if (++up > down) break;
            for (int i = up; i <= down; i++) {
                res.add(matrix[i][right]);
            }
            if (--right < left) break;
            for (int i = right; i >= left; i--) {
                res.add(matrix[down][i]);
            }
            if (--down < up) break;
            for (int i = down; i >= up; i--) {
                res.add(matrix[i][left]);
            }
            if (++left > right) break;
        }
        return res;
    }
}

class SolutionCopy2 {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> order = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return order;
        }
        int rows = matrix.length, columns = matrix[0].length;
        boolean[][] visited = new boolean[rows][columns];
        int total = rows * columns;
        int row = 0, column = 0;
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int directionIndex = 0;
        for (int i = 0; i < total; i++) {
            order.add(matrix[row][column]);
            visited[row][column] = true;
            int nextRow = row + directions[directionIndex][0], nextColumn = column + directions[directionIndex][1];
            if (nextRow < 0 || nextRow >= rows || nextColumn < 0 || nextColumn >= columns || visited[nextRow][nextColumn]) {
                directionIndex = (directionIndex + 1) % 4;
            }
            row += directions[directionIndex][0];
            column += directions[directionIndex][1];
        }
        return order;
    }
}
