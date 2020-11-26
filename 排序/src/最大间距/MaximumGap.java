package 最大间距;

import Sort公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * FileName: MaximumGap.java
 * 类的详细说明
 * 给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。
 * 如果数组元素个数小于 2，则返回 0。
 * |--你可以假设数组中所有元素都是非负整数，且数值在 32 位有符号整数范围内。
 * |--请尝试在线性时间复杂度和空间复杂度的条件下解决此问题。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.11.26 - 下午 2:00
 * @label Sort
 */
public class MaximumGap {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个整型数组nums：[3,6,9,1]");
        while ((line = in.readLine()) != null) {
            int[] nums = PublicMethod.stringToIntegerArray(line);
            int result = new Solution().maximumGap(nums);
            System.out.println("排序后最大差值为：" + result);
        }
    }
}

/**
 * enum
 */
class Solution {

    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int maxGap = 0;
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            maxGap = Math.max(maxGap, nums[i] - nums[i - 1]);
        }
        return maxGap;
    }
}