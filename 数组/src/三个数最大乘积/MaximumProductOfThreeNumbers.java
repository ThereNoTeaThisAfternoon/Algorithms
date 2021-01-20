package 三个数最大乘积;

import Array公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * FileName: MaximumProductOfThreeNumbers.java
 * 类的详细说明
 * 给定一个整型数组，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
 * <p>
 * 给定的整型数组长度范围是[3,104]，数组中所有的元素范围是[-1000, 1000]。
 * 输入的数组中任意三个数的乘积不会超出32位有符号整数的范围。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2021.1.20 - 下午 9:02
 * @label Array Math
 */
public class MaximumProductOfThreeNumbers {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个整型数组nums：[-10,-9,0,1]");
        while ((line = in.readLine()) != null) {
            int[] nums = new PublicMethod().stringToIntegerArray(line);
            int result = new Solution().maximumProduct(nums);
            System.out.println("最大乘积为：" + result);
        }
    }
}

// 实际上只要求出数组中最大的三个数以及最小的两个数，因此我们可以不用排序，用线性扫描直接得出这五个数。
class Solution {
    public int maximumProduct(int[] nums) {
        // 最小的和第二小的
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        // 最大的、第二大的和第三大的
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
        for (int i : nums) {
            if (i < min1) {
                min2 = min1;
                min1 = i;
            } else if (i < min2) {
                min2 = i;
            }
            if (i > max1) {
                max3 = max2;
                max2 = max1;
                max1 = i;
            } else if (i > max2) {
                max3 = max2;
                max2 = i;
            } else if (i > max3) {
                max3 = i;
            }
        }
        return Math.max(min1 * min2 * max1, max1 * max2 * max3);
    }
}

// 排序 最大乘积为最大三个数的乘积 或 最小两个数与最大数的乘积
class SolutionCopy1 {
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        return Math.max(nums[0] * nums[1] * nums[len - 1], nums[len - 3] * nums[len - 2] * nums[len - 1]);
    }
}


// 枚举所有组合情况 超时
class SolutionCopy2 {
    public int maximumProduct(int[] nums) {
        int maxNum = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length - 2; ++i) {
            for (int j = i + 1; j < nums.length - 1; ++j) {
                for (int k = j + 1; k < nums.length; ++k) {
                    maxNum = Math.max(nums[i] * nums[j] * nums[k], maxNum);
                }
            }
        }
        return maxNum;
    }
}
