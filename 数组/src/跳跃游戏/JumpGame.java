package 跳跃游戏;
/**
 * FileName: JumpGame.java
 * 类的详细说明
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个位置。
 *
 * @label Array Greedy(贪婪)
 * @author &#x8c2f;&#x535a;
 * @Date 2020.4.17
 * @version 1.00
 */

import Array公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JumpGame {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = br.readLine()) != null) {
            int[] nums = new PublicMethod().stringToIntegerArray(line);
            boolean result = new SolutionCopy2().canJump(nums);
            String out = booleanToString(result);
            System.out.println(out);
        }
    }

    private static String booleanToString(boolean input) {
        return input ? "True" : "False";
    }
}

class Solution {
    /**
     * MethodName: canJump
     * 如果所有元素都不为0，一定能跳到最后；
     * 从后往前遍历，如果遇到nums[i] = 0，就找i前面的元素j，使得nums[j] > i - j。如果找不到，则不可能跳跃到num[i+1]，返回false。
     *
     * @param nums array
     * @return boolean
     */
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        int temp = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] >= temp) {
                temp = 1;
            } else {
                temp++;
            }
            if (i == 0 && temp > 1) {
                return false;
            }
        }
        return true;
    }
}

class SolutionCopy1 {
    //倒着遍历
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        int pos = nums.length - 1;//表示每次循环需要到达的位置
        for (int i = pos - 1; i >= 0; i--) {
            if (nums[i] >= pos - i) {
                pos = i;
            }
        }
        return pos == 0;
    }
}

class SolutionCopy2 {
    //正向遍历 Greedy
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        int temp = 0;
        for (int i = 0; i <= nums.length - 2; i++) {
            if (nums[i] > 0 && nums[i] + i > temp) {
                temp = nums[i] + i;
            }
            if (temp == i && nums[i] == 0) return false;
            if (temp >= nums.length - 1) return true;
        }
        return temp >= nums.length - 1;
    }
}