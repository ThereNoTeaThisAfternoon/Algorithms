package 最接近的三数之和;
/**
 * FileName: ThreeSumClosest.java
 * 类的详细说明
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。
 * 找出 nums 中的三个整数，使得它们的和与 target 最接近。
 * 返回这三个数的和。假定每组输入只存在唯一答案。
 * 3 <= nums.length <= 10^3
 * -10^3 <= nums[i] <= 10^3
 * -10^4 <= target <= 10^4
 *
 * @label Arrays TwoPointer
 * @author &#x8c2f;&#x535a;
 * @Date 2020.6.25
 * @version 1.00
 */

import Array公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ThreeSumClosest {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个整型数组：[-5,10,4,7,3,2]");
        while ((line = in.readLine()) != null) {
            int[] nums = new PublicMethod().stringToIntegerArray(line);
            System.out.println("请输入一个目标值target：5");
            int target = Integer.parseInt(in.readLine());
            int result = new SolutionCopy().threeSumClosest(nums, target);
            System.out.println("最接近目标值target的三数之和为：" + result);
        }
    }
}

class Solution {
    //sort twoPointer
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int min = nums[0] + nums[1] + nums[2];

        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1, right = nums.length - 1;
            while (left < right) {//三个数之和与target的差的绝对值最小  abs(sum-target) -> min; return sum
                int threeSum = nums[i] + nums[left] + nums[right];
                if (Math.abs(threeSum - target) < Math.abs(min - target))
                    min = threeSum;
                if (threeSum > target)//三数之和大于目标值，右指针左移
                    right--;
                else if (threeSum < target)//三数之和小于目标值，左指针右移
                    left++;
                else
                    return target;//等于，直接返回
            }
        }
        return min;
    }
}

class SolutionCopy {
    //枚举法
    public int threeSumClosest(int[] nums, int target) {
        int ans = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    int threeSum = nums[i] + nums[j] + nums[k];
                    if (Math.abs(threeSum - target) < Math.abs(ans - target))
                        ans = threeSum;
                    if (ans == target)
                        return target;
                }
            }
        }
        return ans;
    }
}