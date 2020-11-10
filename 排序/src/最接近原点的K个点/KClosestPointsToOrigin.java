package 最接近原点的K个点;

import Sort公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * FileName: KClosestPointsToOrigin.java
 * 类的详细说明
 * 我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。
 * （这里，平面上两点之间的距离是欧几里德距离。）
 * 你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.11.9 - 下午 3:32
 * @label Sort Heap
 */
public class KClosestPointsToOrigin {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个由平面上的点组成的列表points：[[3,3],[5,-1],[-2,4]]");
        while ((line = in.readLine()) != null) {
            int[][] points = PublicMethod.stringTo2DIntegerArray(line);
            System.out.println("请输入要获取距离远点最近的点的个数K：2");
            int K = Integer.parseInt(in.readLine());
            int[][] result = new SolutionCopy().kClosest(points, K);
            String out = PublicMethod.Integer2DArrayToString(result);
            System.out.println(out);
        }
    }
}

/**
 * Sort，将每个点到原点的欧几里得距离的平方从小到大排序后，取出前K个即可
 */
class Solution {

    public int[][] kClosest(int[][] points, int K) {
        Arrays.sort(points, Comparator.comparingInt(point -> (point[0] * point[0] + point[1] * point[1])));
        return Arrays.copyOf(points, K);
    }

}

/**
 * Enum
 */
class SolutionCopy {

    public int[][] kClosest(int[][] points, int K) {
        int[][] res = new int[K][2];
        int[] square = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            square[i] = points[i][0] * points[i][0] + points[i][1] * points[i][1];
        }

        for (int k = 0; k < K; k++) {
            int index = 0;
            int minSquare = square[0];
            for (int i = 1; i < square.length; i++) {
                if (square[i] < minSquare) {
                    minSquare = square[i];
                    index = i;
                }
            }
            res[k][0] = points[index][0];
            res[k][1] = points[index][1];
            square[index] = Integer.MAX_VALUE;
        }
        return res;
    }
}

class SolutionCopy2 {
    public int[][] kClosest(int[][] points, int K) {
        // 默认是小根堆，实现大根堆需要重写一下比较器。
        PriorityQueue<int[]> pq = new PriorityQueue<>((p1, p2) -> p2[0] * p2[0] + p2[1] * p2[1] - p1[0] * p1[0] - p1[1] * p1[1]);
        for (int[] point : points) {
            if (pq.size() < K) { // 如果堆中不足 K 个，直接将当前 point 加入即可
                pq.offer(point);
                // 否则，判断当前点的距离是否小于堆中的最大距离，若是，则将堆中最大距离poll出，将当前点加入堆中。
            } else if (pq.comparator().compare(point, pq.peek()) > 0) {
                pq.poll();
                pq.offer(point);
            }
        }

        // 返回堆中的元素
        int[][] res = new int[pq.size()][2];
        int idx = 0;
        for (int[] point : pq) {
            res[idx++] = point;
        }
        return res;
    }
}
