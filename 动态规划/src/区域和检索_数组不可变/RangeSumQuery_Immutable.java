package 区域和检索_数组不可变;

import Dynamic公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * FileName: RangeSumQuery_Immutable.java
 * 类的详细说明
 * 给定一个整数数组  nums，求出数组从索引 i 到 j（i ≤ j）范围内元素的总和，包含 i、j 两点。
 * 实现 NumArray 类：
 * NumArray(int[] nums) 使用数组 nums 初始化对象
 * int sumRange(int i, int j) 返回数组 nums 从索引 i 到 j（i ≤ j）范围内元素的总和，
 * 包含 i、j 两点（也就是 sum(nums[i], nums[i + 1], ... , nums[j])）
 * <p>
 * 0 <= nums.length <= 104
 * -105 <= nums[i] <= 105
 * 0 <= i <= j < nums.length
 * 最多调用 104 次 sumRange 方法
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2021.3.1 - 下午 7:02
 * @label DynamicProgramming
 */
public class RangeSumQuery_Immutable {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个字符串数组表示一些方法functions：[\"NumArray\",\"sumRange\",\"sumRange\",\"sumRange\"]");
        while ((line = in.readLine()) != null) {
            String[] functions = new PublicMethod().stringToStringArray(line);
            NumArray obj = null;
            System.out.println("请输入一个二维整型数组表示数组对象和参数：[-2,0,3,-5,2,-1]");
            int[] nums = new PublicMethod().stringToIntegerArray(in.readLine());
            System.out.println("请输入一些操作options：[[0,2],[2,5],[0,5]]");
            int[][] options = PublicMethod.stringTo2DIntegerArray(in.readLine());

            String[] results = new String[functions.length];
            for (int i = 0; i < functions.length; ++i) {
                switch (functions[i]) {
                    case "NumArray":
                        obj = new NumArray(nums);
                        results[i] = null;
                        break;
                    case "sumRange":
                        if (obj != null) {
                            int result = obj.sumRange(options[i - 1][0], options[i - 1][1]);
                            results[i] = Integer.toString(result);
                        }
                        break;
                }
            }
            System.out.println(Arrays.toString(results));
        }
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */
class NumArray {

    private int[] sums;

    public NumArray(int[] nums) {
        sums = new int[nums.length + 1];
        for(int i = 1; i <= nums.length; ++i){
            sums[i] += sums[i - 1] + nums[i - 1];
        }
    }

    public int sumRange(int i, int j) {
        return sums[j + 1] - sums[i];
    }
}