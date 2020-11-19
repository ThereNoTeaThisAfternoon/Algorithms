package 移动零;

import Pointer公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * FileName: MoveZeroes.java
 * 类的详细说明
 * Given an array nums, write a function to move all 0's to the end of it
 * while maintaining the relative order of the non-zero elements.
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.11.19 - 下午 2:11
 * @label TwoPointers Array
 */
public class MoveZeroes {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个整型数组nums：[0,1,0,3,12]");
        while ((line = in.readLine()) != null) {
            int[] nums = PublicMethod.stringToIntegerArray(line);
            new SolutionCopy2().moveZeroes(nums);
            System.out.println(Arrays.toString(nums));
        }
    }
}

class Solution {

    public void moveZeroes(int[] nums) {
        // 将非零值依序插入，剩下补零
        if (nums == null || nums.length < 2) {
            return;
        }
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[index++] = nums[i];
            }
        }
        while (index < nums.length) {
            nums[index++] = 0;
        }
    }
}

class SolutionCopy {
    public void moveZeroes(int[] nums) {
        // 从数组尾部开始，把0往后移
        int len;
        if (nums == null || (len = nums.length) < 2) {
            return;
        }
        for (int i = len - 1, j = i; i >= 0; --i) {
            if (nums[i] != 0) {
                continue;
            }
            int k = i;
            while (k < j) {
                nums[k] = nums[k] + nums[k + 1] - (nums[k + 1] = nums[k]);
                k++;
            }
            j--;
        }
    }
}

class SolutionCopy2 {

    public void moveZeroes(int[] nums) {
        // 把非零值往前移
        int len;
        if (nums == null || (len = nums.length) < 2) {
            return;
        }
        int l = 0, r = 0;
        while (r < len) {
            if (nums[r] != 0) {
                nums[l] = nums[r] + nums[l] - (nums[r] = nums[l]);
                l++;
            }
            r++;
        }
    }
}