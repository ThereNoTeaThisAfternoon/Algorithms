package 删除有序数组中的重复项;

import Array公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * FileName: RemoveDuplicatesFromSortedArray.java
 * 类的详细说明
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 * <p>
 * 0 <= nums.length <= 3 * 104
 * -104 <= nums[i] <= 104
 * nums 已按升序排列
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2021.4.18 - 上午 9:43
 * @label Array TwoPointers
 */
public class RemoveDuplicatesFromSortedArray {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个待删除的整型数组nums：[1,2,2,3,3,3,4,5,5,6]");
        while ((line = in.readLine()) != null) {
            int[] nums = new PublicMethod().stringToIntegerArray(line);
            int result = new SolutionCopy().removeDuplicates(nums);
            System.out.println(Arrays.toString(Arrays.copyOfRange(nums, 0, result)));
        }
    }
}

class Solution {

    public int removeDuplicates(int[] nums) {
        int i = 0; // 数组中非重复元素个数
        for (int num : nums) {
            // i<1初始化删除后数组，之后每次比较，与当前删除后数组的最后一个值进行比较
            if (i < 1 || nums[i - 1] < num) {
                nums[i++] = num;
            }
        }
        return i;
    }
}

class SolutionCopy {

    public int removeDuplicates(int[] nums) {
        int len = nums.length;
        if (len < 1) {
            return 0;
        }
        int i = 1, j = 1;
        while (j < len) {
            if (nums[j - 1] != nums[j]) {
                nums[i++] = nums[j];
            }
            j++;
        }
        return i;
    }
}