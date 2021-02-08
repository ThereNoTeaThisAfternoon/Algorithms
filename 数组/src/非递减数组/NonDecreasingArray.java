package 非递减数组;

import Array公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * FileName: NonDecreasingArray.java
 * 类的详细说明
 * 给你一个长度为 n 的整数数组，请你判断在 最多 改变 1 个元素的情况下，该数组能否变成一个非递减数列。
 * 我们是这样定义一个非递减数列的： 对于数组中所有的 i (0 <= i <= n-2)，总满足 nums[i] <= nums[i + 1]。
 * <p>
 * 1 <= n <= 10 ^ 4
 * - 10 ^ 5 <= nums[i] <= 10 ^ 5
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2021.2.7 - 下午 8:52
 * @label Array
 */
public class NonDecreasingArray {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个整型数组nums：[5,7,1,8]");
        while ((line = in.readLine()) != null) {
            int[] nums = new PublicMethod().stringToIntegerArray(line);
            boolean result = new Solution().checkPossibility(nums);
            System.out.println((result ? "" : "不") + "能变成非递减数列");
        }
    }
}

class Solution {

    public boolean checkPossibility(int[] nums) {
        int cnt = 0;
        for (int i = 0; i < nums.length - 1; ++i) {
            if (nums[i] > nums[i + 1]) {
                cnt++;
                if (cnt > 1) {
                    return false;
                }
                if (i > 0 && nums[i - 1] > nums[i + 1]) {
                    nums[i + 1] = nums[i];
                }
            }
        }
        return true;
    }
}

