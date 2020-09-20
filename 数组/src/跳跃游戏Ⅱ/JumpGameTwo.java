package 跳跃游戏Ⅱ;
/**
 * FileName: JumpGameⅡ.java
 * 类的详细说明
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 你的目标是使用 最少的跳跃次数 到达数组的最后一个位置。
 *
 * @label Array GreedyAlgorithm
 * @author &#x8c2f;&#x535a;
 * @Date 2020.5.4
 * @version 1.00
 */

import Array公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JumpGameTwo {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个非负整数数组例如:[2,3,1,1,4]");
        while ((line = in.readLine()) != null) {
            int[] nums = new PublicMethod().stringToIntegerArray(line);
            int ans = new SolutionCopy().jump(nums);
            String out = String.valueOf(ans);
            System.out.println("最少跳跃次数为-->>" + out);
        }
    }
}

class Solution {
    //反向查找出发位置
    //复杂度O(N^2)O(1)
    public int jump(int[] nums) {
        int position = nums.length - 1;
        int steps = 0;
        while (position > 0) {
            for (int i = 0; i < position; i++) {
                if (nums[i] + i >= position) {
                    position = i;
                    steps++;
                    break;
                }
            }
        }
        return steps;
    }
}

class SolutionCopy {
    //正向查找可到达的最大位置
    //复杂度O(N)O(1)
    public int jump(int[] nums) {
        int len = nums.length;
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        for (int i = 0; i < len - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);//定位
            if (i == end) {
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }
}