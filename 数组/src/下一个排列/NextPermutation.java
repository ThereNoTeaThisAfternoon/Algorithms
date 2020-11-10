package 下一个排列;

import Array公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * FileName: NextPermutation.java
 * 类的详细说明
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * 必须原地修改，只允许使用额外常数空间。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.11.10 - 下午 2:55
 * @label Array
 */
public class NextPermutation {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个整型数组：[1,2,3,8,5,7,6,4]");
        while ((line = in.readLine()) != null) {
            int[] nums = new PublicMethod().stringToIntegerArray(line);
            new Solution().nextPermutation(nums);
            System.out.println(Arrays.toString(nums));
        }
    }
}

/**
 * 下一个数比当前数大，增幅尽可能小
 * 从后往前，将一个尽可能小的大数与前面的小数交换
 */
class Solution {

    public void nextPermutation(int[] nums) {
        int len = nums.length;
        for (int i = len - 1; i > 0; --i) {
            // 找到待交换的小数
            if (nums[i] > nums[i - 1]) {
                Arrays.sort(nums, i, len);
                for (int j = i; j < len; j++) {
                    // 找到尽可能小的大数
                    if (nums[i - 1] > nums[j]) {
                        int temp = nums[i - 1];
                        nums[i - 1] = nums[j];
                        nums[j] = temp;
                        return;
                    }
                }
            }
        }
        Arrays.sort(nums);
    }
}