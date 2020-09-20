package 盛最多水的容器;
/**
 * FileName: ContainerWithMostWater.java
 * 类的详细使用说明
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 说明：不能倾斜容器，且 n 的值至少为 2。
 *
 * @label Array TwoPointers(双指针)
 * @author &#x8c2f;&#x535a;
 * @Date 2020.4.18
 * @version 1.00
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ContainerWithMostWater {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] height = stringToIntegerArray(line);
            int result = new Solution().maxArea(height);
            String out = String.valueOf(result);
            System.out.println(out);
        }
    }

    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) return new int[0];
        String[] part = input.split(",");
        int[] output = new int[part.length];
        int index = 0;
        for (String temp : part) {
            output[index++] = Integer.parseInt(temp);
        }
        return output;
    }
}

class Solution {
    /**
     * MethodName: maxArea
     * 暴力破解
     * Area = Max(min(height[i], height[j]) * (j-i)) {其中0 <= i < j < height.length}
     * 执行用时 : 427 ms
     * 内存消耗 : 40.1 MB
     *
     * @param height array
     * @return int 容器所能容纳最多的水
     */
    public int maxArea(int[] height) {
        if (!(null != height)) {
            return 0;
        } else {
            if (height.length == 0) {
                return 0;
            }
        }
        int res = 0;
        for (int i = 0; i < height.length - 1; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int width = j - i;
                int h = Math.min(height[i], height[j]);
                int area = width * h;
                if (area > res) {
                    res = area;
                }
            }
        }
        return res;
    }
}

class SolutionCopy1 {
    /**
     * Two Pointers
     * 执行用时 : 3 ms
     * 内存消耗 : 40.3 MB
     */
    public int maxArea(int[] height) {
        int max = 0;
        for (int i = 0, j = height.length - 1; i < j; ) {
            int minHeight = height[i] < height[j] ? height[i++] : height[j--];
            max = Math.max(max, (j - i + 1) * minHeight);
        }
        return max;
    }
}

class SolutionCopy2 {
    /**
     * Two Pointers
     * 执行用时 : 4 ms
     * 内存消耗 : 40.2 MB
     */
    public int maxArea(int[] a){
        int ans=0,left=0,right=a.length-1;
        while(left<right){
            int area = (right-left)*Math.min(a[left],a[right]);
            ans = Math.max(ans,area);
            if(a[left]<a[right]) left++;
            else right++;
        }
        return ans;
    }
}