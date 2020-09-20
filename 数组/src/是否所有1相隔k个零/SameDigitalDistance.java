package 是否所有1相隔k个零;
/**
 * FileName: SameDigitalDistance.java
 * 类的详细说明
 * 一个由若干 0 和 1 组成的数组 nums 以及整数 k。
 * 如果所有 1 都至少相隔 k 个元素，则返回 True ；否则，返回 False 。
 *
 * @label Array
 * @author &#x8c2f;&#x535a;
 * @Date 2020.5.6
 * @version 1.00
 */

import Array公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SameDigitalDistance {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个仅包含0和1的数组，例如：[1,0,0,1,0,1]");
        while ((line = in.readLine()) != null) {
            int[] nums = new PublicMethod().stringToIntegerArray(line);
            System.out.println("输入两个1相隔的距离k，例如：2");
            int k = Integer.parseInt(in.readLine());
            boolean ret = new SolutionCopy().kLengthApart(nums, k);
            String out = String.valueOf(ret);
            System.out.println(out);
        }
    }
}

class Solution {

    public boolean kLengthApart(int[] nums, int k) {
        //两个1之间的距离
        int distance = k;
        //遍历整个数组如果当前值是1，就判断与前一个1之间的距离
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                if (distance < k)
                    return false;
                distance = 0;
            } else {
                distance++;
            }
        }
        return true;
    }
}

class SolutionCopy {
    public boolean kLengthApart(int[] nums, int k) {
        int pre = 0x80000fff, next = 0;
        for (; next < nums.length; next++) {
            if (nums[next] == 1) {
                if (next - pre <= k)
                    return false;
                pre = next;
            }
        }
        return true;
    }
}