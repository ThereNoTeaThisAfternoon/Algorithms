package 柱状图中最大的矩形;
/**
 * FileName: LargestRectangleInHistogram.java
 * 类的详细说明
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *
 * @label Array Stack
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @Date 2020.5.30
 */

import Array公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class LargestRectangleInHistogram {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个整型数组：[2,1,5,6,2,3]");
        while ((line = in.readLine()) != null) {
            int[] heights = new PublicMethod().stringToIntegerArray(line);
            int result = new SolutionCopy().largestRectangleArea(heights);
            String out = String.valueOf(result);
            System.out.println("最大矩形区域为：" + out);
        }
    }
}

class Solution {
    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        if (len < 2)
            return len == 0 ? 0 : heights[0];
        int maxArea = 0;
        for (int i = 0; i < len; i++) {
            int left = i, right = i;
            for (int j = i - 1; j >= 0; j--) {
                if (heights[j] > heights[i])
                    left--;
                else
                    break;
            }
            for (int k = i + 1; k < len; k++) {
                if (heights[k] > heights[i])
                    right++;
                else
                    break;
            }
            maxArea = Math.max(maxArea, (right - left + 1) * heights[i]);
        }
        return maxArea;
    }
}

class SolutionCopy {
    //单调栈 + 常数优化
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(right, n);

        Deque<Integer> mono_stack = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            while (!mono_stack.isEmpty() && heights[mono_stack.peek()] >= heights[i]) {
                right[mono_stack.peek()] = i;
                mono_stack.pop();
            }
            left[i] = (mono_stack.isEmpty() ? -1 : mono_stack.peek());
            mono_stack.push(i);
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
        }
        return ans;
    }
}