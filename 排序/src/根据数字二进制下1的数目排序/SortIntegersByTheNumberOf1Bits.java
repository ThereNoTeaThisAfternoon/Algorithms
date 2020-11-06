package 根据数字二进制下1的数目排序;

import Sort公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

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
            int[] result = new SolutionCopy2().sortByBits(arr);
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
    // 将数组中的元素按照其二进制表示中数字 1 的数目升序排序
    public int[] sortByBits(int[] arr) {
        Integer[] nums = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            nums[i] = arr[i];
        }
        Arrays.sort(nums, (num1, num2) -> {
            int bitCount1 = bitCount(num1);
            int bitCount2 = bitCount(num2);
            // 相同按数值，不同按位数
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

/**
 * bit[i] 为数字 i 二进制表示下数字 1 的个数，则可以列出递推式：
 * bit[i] = bit[i>>1] + (i & 1)
 */
class SolutionCopy2 {

    public int[] sortByBits(int[] arr) {
        List<Integer> list = new ArrayList<>();
        for (int i : arr) {
            list.add(i);
        }
        int[] bit = new int[10001];
        for (int i = 1; i < bit.length; i++) {
            bit[i] = bit[i >> 1] + i & 1;
        }
        list.sort((o1, o2) -> {
            // 相同按数值，不同按位数
            return bit[o1] == bit[o2] ? o1 - o2 : bit[o1] - bit[o2];
        });
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

}
