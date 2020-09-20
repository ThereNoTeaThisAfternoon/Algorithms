package 转变数组后最接近目标值的数组和;
/**
 * FileName: SumOfMutatedArrayClosestToTarget.java
 * 类的详细说明
 * 给一个整数数组 arr 和一个目标值 target ，请返回一个整数 value ，
 * 使得将数组中所有大于 value 的值变成 value 后，数组的和最接近  target （最接近表示两者之差的绝对值最小）。
 * 如果有多种使得和最接近 target 的方案，请返回这些整数中的最小值。
 * 请注意，答案不一定是 arr 中的数字。
 *
 * @label BinarySearch Array
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @Date 2020.6.14
 */

import Binary公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SumOfMutatedArrayClosestToTarget {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个整型数组：[4,9,3]");
        while ((line = in.readLine()) != null) {
            int[] arr = PublicMethod.stringToIntegerArray(line);
            System.out.println("请输入一个目标值：10");
            int target = Integer.parseInt(in.readLine());
            int result = new Solution().findBestValue(arr, target);
            String out = String.valueOf(result);
            System.out.println("转变值为" + out);
        }
    }
}

class Solution {
    //
    public int findBestValue(int[] arr, int target) {
        Arrays.sort(arr);
        int sum = 0;
        int len = arr.length;
        int n = arr.length;
        for (int value : arr) {
            if ((target - sum) / n < value) {
                int k = (target - sum) / n;
                if (Math.abs(sum + k * n - target) <= Math.abs(sum + (k + 1) * n - target))
                    return k;
                return k + 1;
            }
            sum += value;
            n--;
        }
        return arr[len - 1];
    }
}