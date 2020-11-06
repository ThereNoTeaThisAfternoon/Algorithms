package 根据数字二进制下1的数目排序;

import Sort公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * FileName: SortIntegersByTheNumberOf1Bits.java
 * 类的详细说明
 * 给你一个整数数组 arr 。请你将数组中的元素按照其二进制表示中数字 1 的数目升序排序。
 * 如果存在多个数字二进制中 1 的数目相同，则必须将它们按照数值大小升序排列。
 * 请你返回排序后的数组。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.11.6 - 上午 11:21
 * @label Sort BitManipulation
 */
public class SortIntegersByTheNumberOf1Bits {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个待排序的整型数组：[1024,512,256,128,64,32,16,8,4,2,1]");
        while ((line = in.readLine()) != null) {
            int[] arr = PublicMethod.stringToIntegerArray(line);
            int[] result = new SolutionCopy().sortByBits(arr);
            System.out.println(Arrays.toString(result));
        }
    }
}

class Solution {
    // 为数组种的每个数增加比特为1的个数的权重，重排序，去除权
    public int[] sortByBits(int[] arr) {
        int[] map = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            map[i] = Integer.bitCount(arr[i]) * 1000000 + arr[i];
        }
        Arrays.sort(map);
        for (int i = 0; i < map.length; i++) {
            map[i] = map[i] % 1000000;
        }
        return map;
    }
}

class SolutionCopy {

    public int[] sortByBits(int[] arr) {
        Integer[] nums = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            nums[i] = arr[i];
        }
        Arrays.sort(nums, (num1, num2) -> {
            int bitCount1 = bitCount(num1);
            int bitCount2 = bitCount(num2);
            // 相同按原数，不同按位数
            return bitCount1 == bitCount2 ? num1 - num2 : bitCount1 - bitCount2;
        });
        for (int i = 0; i < nums.length; i++) {
            arr[i] = nums[i];
        }
        return arr;
    }

    /**
     * 111 & 110 -> 110
     * 110 & 101 -> 100
     * 100 & 011 -> 000
     */
    private int bitCount(int i) {
        int cnt = 0;
        while (i > 0) {
            i &= (i - 1);
            cnt++;
        }
        return cnt;
    }
}