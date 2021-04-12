package 最大数;

import Sort公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * FileName: LargestNumber.java
 * 类的详细说明
 * 给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
 * 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 * <p>
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 109
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2021.4.12 - 下午 8:39
 * @label Sort
 */
public class LargestNumber {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个非负整型数组nums：[3,30,34,5,9]");
        while ((line = in.readLine()) != null) {
            int[] nums = PublicMethod.stringToIntegerArray(line);
            long start = System.currentTimeMillis();
            String result = new SolutionCopy().largestNumber(nums);
            long end = System.currentTimeMillis();
            System.out.println((end - start) + " : " + result);
        }
    }
}

class Solution {

    public String largestNumber(int[] nums) {
        int len = nums.length;
        StringBuilder sb = new StringBuilder();
        //数字数组->字符数组  转化
        String[] strings = new String[len];
        for (int i = 0; i < len; ++i) {
            strings[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(strings, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));
        //字符数组->字符串 转化
        for (String string : strings) {
            sb.append(string);
        }
        // 特殊情况 若干个零
        return sb.charAt(0) == '0' ? "0" : sb.toString();
    }
}

class SolutionCopy {
    public String largestNumber(int[] nums) {
        int n = nums.length;
        // 转换成包装类型，以便传入 Comparator 对象（此处为 lambda 表达式）
        Integer[] numsArr = new Integer[n];
        for (int i = 0; i < n; i++) {
            numsArr[i] = nums[i];
        }

        Arrays.sort(numsArr, (x, y) -> {
            long sx = 10, sy = 10;
            while (sx <= x) {
                sx *= 10;
            }
            while (sy <= y) {
                sy *= 10;
            }
            return (int) (-sy * x - y + sx * y + x);
        });

        if (numsArr[0] == 0) {
            return "0";
        }
        StringBuilder ret = new StringBuilder();
        for (int num : numsArr) {
            ret.append(num);
        }
        return ret.toString();
    }
}
