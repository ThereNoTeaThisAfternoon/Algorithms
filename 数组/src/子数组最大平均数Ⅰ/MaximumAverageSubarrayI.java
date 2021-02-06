package 子数组最大平均数Ⅰ;

import Array公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * FileName: MaximumAverageSubarray.java
 * 类的详细说明
 * 给定 n 个整数，找出平均数最大且长度为 k 的连续子数组，并输出该最大平均数。
 * <p>
 * 1 <= k <= n <= 30,000。
 * 所给数据范围 [-10,000，10,000]。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2021.2.4 - 下午 7:25
 * @label Array
 */
public class MaximumAverageSubarrayI {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个整数数组nums：[1,12,-5,-6,50,3]");
        while ((line = in.readLine()) != null) {
            int[] nums = new PublicMethod().stringToIntegerArray(line);
            System.out.println("请输入最大长度k：4");
            int k = Integer.parseInt(in.readLine());
            double result = new SolutionCopy().findMaxAverage(nums, k);
            System.out.println("最大平均长度为：" + result);
        }
    }
}

class Solution {

    public double findMaxAverage(int[] nums, int k) {
        double res = -666666666;
        int l = 0, r = 0;
        int sum = 0;
        while (r < nums.length) {
            sum += nums[r++];
            if (r - l == k) {
                res = Math.max(res, sum * 1.0 / k);
                sum -= nums[l++];
            }
        }
        return res;
    }
}

class SolutionCopy {

    public double findMaxAverage(int[] nums, int k) {
        int sum = 0;
        for (int i = 0; i < k; ++i) {
            sum += nums[i];
        }
        int maxSum = sum;
        for (int i = k; i < nums.length; ++i) {
            sum = sum - nums[i - k] + nums[i];
            maxSum = Math.max(maxSum, sum);
        }
        return 1.0 * maxSum / k;
    }
}