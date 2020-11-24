package 用最少数量箭引爆气球;


import Greedy公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

/**
 * FileName: MinimumNumberOfArrowsToBurstBalloons.java
 * 类的详细说明
 * 在二维空间中有许多球形的气球。对于每个气球，提供的输入是水平方向上，气球直径的开始和结束坐标。
 * 由于它是水平的，所以纵坐标并不重要，因此只要知道开始和结束的横坐标就足够了。开始坐标总是小于结束坐标。
 * 一支弓箭可以沿着 x 轴从不同点完全垂直地射出。在坐标 x 处射出一支箭，
 * 若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足  xstart ≤ x ≤ xend，则该气球会被引爆。
 * 可以射出的弓箭的数量没有限制。 弓箭一旦被射出之后，可以无限地前进。我们想找到使得所有气球全部被引爆，所需的弓箭的最小数量。
 * 给你一个数组 points ，其中 points [i] = [xstart,xend] ，返回引爆所有气球所必须射出的最小弓箭数。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.11.23 - 下午 10:07
 * @label GreedyAlgorithm Sort
 */
public class MinimumNumberOfArrowsToBurstBalloons {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个二维数组区间points：[[10,16],[2,8],[1,6],[7,12]]");
        while ((line = in.readLine()) != null) {
            int[][] points = PublicMethod.stringTo2DIntegerArray(line);
            int result = new Solution().findMinArrowShots(points);
            System.out.println("引爆所有气球最小弓箭数为：" + result);
        }
    }
}

class Solution {
    public int findMinArrowShots(int[][] points) {
        if (points.length < 1) {
            return 0;
        }
        int count = 1;
        Arrays.sort(points, Comparator.comparingInt(a -> a[1]));
        int axis = points[0][1];
        for (int i = 1; i < points.length; i++) {
            if (axis < points[i][0]) {
                axis = points[i][1];
                count++;
            }
        }
        return count;
    }

}