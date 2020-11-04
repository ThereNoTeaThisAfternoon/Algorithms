package 插入区间;

import Array公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * FileName: InsertInterval.java
 * 类的详细说明
 * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.11.4 - 下午 5:35
 * @label Array Sort
 */
public class InsertInterval {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个无重叠的 ，按照区间起始端点排序的区间列表：[[1,3],[6,9]]");
        while ((line = in.readLine()) != null) {
            int[][] intervals = PublicMethod.stringTo2DIntegerArray(line);
            int[] newInterval = new PublicMethod().stringToIntegerArray(in.readLine());
            int[][] result = new Solution().insert(intervals, newInterval);
            String out = PublicMethod.integer2DArrayToString(result);
            System.out.println(out);
        }
    }
}

/**
 * 插入 排序 合并
 */
class Solution {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> list = new ArrayList<>(Arrays.asList(intervals));
        list.add(newInterval);
        list.sort(Comparator.comparingInt(o -> o[0]));
        List<int[]> res = new ArrayList<>();
        int i = 0;
        while (i < list.size()) {
            int l = list.get(i)[0];
            int r = list.get(i)[1];
            while (i + 1 < list.size() && r >= list.get(i + 1)[0]) {
                r = Math.max(r, list.get(i + 1)[1]);
                i++;
            }
            res.add(new int[]{l, r});
            i++;
        }
        return res.toArray(new int[res.size()][2]);
    }

}
