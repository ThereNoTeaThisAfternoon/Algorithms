package 旋转数组;

import Array公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * FileName: RotateArray.java
 * 类的详细说明
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2021.1.8 - 下午 8:30
 * @label Array
 */
public class RotateArray {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个整型数组nums：[1,2,3,4,5,6,7]");
        while ((line = in.readLine()) != null) {
            int[] nums = new PublicMethod().stringToIntegerArray(line);
            System.out.println("请输入一个整数k：3");
            int k = Integer.parseInt(in.readLine());
            new SolutionCopy().rotate(nums, k);
            System.out.println(Arrays.toString(nums));
        }
    }
}

/**
 * Enum
 */
class Solution {

    public void rotate(int[] nums, int k) {
        int len = nums.length;
        k = k % len;
        while (k-- > 0) {
            int lastNumber = nums[len - 1];
            System.arraycopy(nums, 0, nums, 1, len - 2 + 1);
            nums[0] = lastNumber;
        }
    }
}

/**
 * 使用额外的数组
 */
class SolutionCopy {

    public void rotate(int[] nums, int k) {
        int len = nums.length;
        int[] newArr = new int[len];
        for (int i = 0; i < len; ++i) {
            newArr[(i + k) % len] = nums[i];
        }
        System.arraycopy(newArr, 0, nums, 0, len);
    }
}

/**
 * 数组翻转
 * nums = "----->-->"; k =3
 * result = "-->----->";
 * <p>
 * reverse "----->-->" we can get "<--<-----"
 * reverse "<--" we can get "--><-----"
 * reverse "<-----" we can get "-->----->"
 */
class SolutionCopy2 {

    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            nums[start] = nums[start] + nums[end] - (nums[end] = nums[start]);
            start++;
            end--;
        }
    }
}