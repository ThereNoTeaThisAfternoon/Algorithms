package 五重叠区间;

import Greedy公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

/**
 * FileName: NonOverlappingIntervals.java
 * 类的详细说明
 * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
 * 注意:
 * |--可以认为区间的终点总是大于它的起点。
 * |--区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.12.31 - 下午 8:19
 * @label Greedy
 */
public class NonOverlappingIntervals {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个二维数组intervals： [[1,2],[2,3],[3,4],[1,3]]");
        while ((line = in.readLine()) != null) {
            int[][] intervals = PublicMethod.stringTo2DIntegerArray(line);
            int result = new Solution().eraseOverlapIntervals(intervals);
            System.out.println("需要移除的区间最小数量为：" + result);
        }
    }
}

/**
 * 贪心策略，先计算最多能组成的不重叠区间个数，然后用区间总个数减去不重叠区间的个数。
 * 在每次选择中，选择的区间结尾越小，留给后面的区间的空间越大，那么后面能够选择的区间个数也就越大。
 * 按区间的结尾进行升序排序，每次选择结尾最小，并且和前一个区间不重叠的区间。
 * 在对数组进行排序的时候也可以使用 lambda 表示式来创建 Comparator ，不过算法运行时间会比较长点。
 */
class Solution {

    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0])); // 升序排序
        int count = 0; // 最多能组成不重叠区间个数
        int end = intervals[0][1];
        for (int[] interval : intervals) {
            if (interval[0] < end) {
                continue;
            }
            end = interval[1];
            count++;
        }
        return intervals.length - count;
    }
}

/**
 * DynamicProgramming
 */
class SolutionCopy {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }

        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];
            }
        });

        int n = intervals.length;
        int[] f = new int[n];
        Arrays.fill(f, 1);
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (intervals[j][1] <= intervals[i][0]) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
        }
        return n - Arrays.stream(f).max().getAsInt();
    }
}
