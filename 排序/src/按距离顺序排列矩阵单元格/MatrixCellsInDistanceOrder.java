package 按距离顺序排列矩阵单元格;

import Sort公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

/**
 * FileName: MatrixCellsInDistanceOrder.java
 * 类的详细说明
 * 给出 R 行 C 列的矩阵，其中的单元格的整数坐标为 (r, c)，满足 0 <= r < R 且 0 <= c < C。
 * 另外，我们在该矩阵中给出了一个坐标为 (r0, c0) 的单元格。
 * 返回矩阵中的所有单元格的坐标，并按到 (r0, c0) 的距离从最小到最大的顺序排，
 * 其中，两单元格(r1, c1) 和 (r2, c2) 之间的距离是曼哈顿距离，|r1 - r2| + |c1 - c2|。（可以按任何满足此条件的顺序返回答案。）
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.11.17 - 下午 3:04
 * @label Sort
 */
public class MatrixCellsInDistanceOrder {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入矩阵R行和C列及矩阵中的一个坐标(r0,c0)：2 3 1 2");
        while ((line = in.readLine()) != null) {
            int R = Integer.parseInt(line.substring(0, 1));
            int C = Integer.parseInt(line.substring(2, 3));
            int r0 = Integer.parseInt(line.substring(4, 5));
            int c0 = Integer.parseInt(line.substring(6));
            int[][] result = new SolutionCopy2().allCellsDistOrder(R, C, r0, c0);
            String output = PublicMethod.Integer2DArrayToString(result);
            System.out.println(output);
        }
    }
}

/**
 * 首先存储矩阵内所有的点，然后将其按照哈曼顿距离直接排序。
 */
class Solution {

    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        int[][] array = new int[R * C][];
        // 存储矩阵中所有单元格的坐标
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                array[r * C + c] = new int[]{r, c};
            }
        }
        // 按到(r0,c0)的曼哈顿距离递增排序
        Arrays.sort(array, Comparator.comparingInt(o -> (Math.abs(r0 - o[0]) + Math.abs(c0 - o[1]))));
        return array;
    }
}

/**
 * StreamAPI
 */
class SolutionCopy {

    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        return IntStream.range(0, R).boxed()
                .flatMap((i) -> IntStream.range(0, C).mapToObj((j) -> new int[]{i, j}))
                .sorted(Comparator.comparingInt((o) -> (Math.abs(r0 - o[0]) + Math.abs(c0 - o[1]))))
                .toArray(int[][]::new);
    }

    public int[][] allCellsDistOrder(int R, int C, int r0, int c0, boolean button) {
        return IntStream
                .range(0, R * C)
                .mapToObj((i) -> new int[]{i / C, i % C})
                .sorted((Comparator.comparingInt(o -> (Math.abs(r0 - o[0]) + Math.abs(c0 - o[1])))))
                .toArray(int[][]::new);
    }
}

/**
 * BucketSort
 */
class SolutionCopy2 {

    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        // 根据桶数，创建桶
        int maxDist = Math.max(r0, R - 1 - r0) + Math.max(c0, C - 1 - c0);
        List<List<int[]>> buckets = new ArrayList<>();
        for (int i = 0; i <= maxDist; i++) {
            buckets.add(new ArrayList<>());
        }
        // 按曼哈顿距离桶排序
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                buckets.get(Math.abs(r0 - i) + Math.abs(c0 - j)).add(new int[]{i, j});
            }
        }
        //依次从桶中取出数组
        int[][] ret = new int[R * C][];
        int index = 0;
        for (List<int[]> bucket : buckets) {
            for (int[] arr : bucket) {
                ret[index++] = arr;
            }
        }
        return ret;
    }

}