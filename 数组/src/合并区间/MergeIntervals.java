package 合并区间;
/**
 * FileName: MergeIntervals.java
 * 类的详细说明
 * 给出一个区间的集合，请合并所有重叠的区间。
 * example: [[1,3],[2,6],[8,10],[15,18]]
 *
 * @label Array Sort
 * @author &#x8c2f;&#x535a;
 * @Date 2020.4.16
 * @version 1.00
 */

import java.util.Arrays;
import java.util.Scanner;

public class MergeIntervals {
    public static void main(String[] args) {
        int[][] intervals = new int[4][2];
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入八个数表示一个二维四列数组");
        for (int i = 0; i < intervals.length; i++) {
            for (int j = 0; j < intervals[0].length; j++) {
                if (scanner.hasNextInt()) {
                    intervals[i][j] = scanner.nextInt();
                }
            }
        }
        int[][] ret = new Solution().merge(intervals);
        String out = int2dArrayToString(ret);
        System.out.print(out);
    }

    public static String int2dArrayToString(int[][] array) {
        if (array == null) {
            return "null";
        }
        if (array.length == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        for (int[] item : array) {
            sb.append(Arrays.toString(item));
            sb.append(",");
        }
        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }
}

class Solution {
    /**
     * MethodName: merge
     * 类方法详细说明
     * 区间[a,b],[c,d] 在a<b && c<d 情况下，只有满足 a<d && b>c 区间才会相交
     *
     * @param intervals 二维区间数组
     * @return 合并后的区间
     */
    public int[][] merge(int[][] intervals) {
        int len = intervals.length;
        if (len < 2) return intervals;
        int counter = 0;//合并次数
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                //区间[a,b] ,[c,d]
                //必须满足此条件区间才会相交 a<d && c<b
                if ((intervals[i][0] <= intervals[j][1]) && (intervals[i][1] >= intervals[j][0])) {
                    //取a,c中小值
                    intervals[j][0] = Math.min(intervals[i][0], intervals[j][0]);
                    //取b,d中大值
                    intervals[j][1] = Math.max(intervals[i][1], intervals[j][1]);
                    //清空前者
                    intervals[i] = null;
                    counter++;
                    break;
                }
            }
        }
        int[][] res = new int[len - counter][2];
        int role = 0;
        for (int[] temp : intervals) {
            if (temp != null) res[role++] = temp;
        }
        return res;
    }
}