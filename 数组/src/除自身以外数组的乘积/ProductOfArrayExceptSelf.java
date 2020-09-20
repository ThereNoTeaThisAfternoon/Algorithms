package 除自身以外数组的乘积;
/**
 * FileName: ProductOfArrayExceptSelf.java
 * 类的详细说明
 * 给一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，
 * 其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
 *
 * @label Array
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @Date 2020.6.4
 */

import Array公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ProductOfArrayExceptSelf {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个数组所有值在32位范围内的整型数组：[1,0,-1]");
        while ((line = in.readLine()) != null) {
            int[] nums = new PublicMethod().stringToIntegerArray(line);
            int[] result = new Solution().productExceptSelf(nums);
            System.out.println(Arrays.toString(result));
        }
    }
}

class Solution {
    //左右累乘，巧妙记录每个元素的左右乘积，时间复杂度O(n),空间复杂度0(1)。
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int left = 1, right = 1;//left从左边累乘，right从右边累乘。
        int[] output = new int[len];

        for (int i = 0; i < len; i++)
            output[i] = 1;
        for (int i = 0; i < len; i++) {
            //乘以左边结果
            output[i] *= left;
            left *= nums[i];
            //乘以右边结果
            output[len - 1 - i] *= right;
            right *= nums[len - 1 - i];
        }//最终得到每个元素其左右乘积进行相乘得到结果。
        return output;
    }
}