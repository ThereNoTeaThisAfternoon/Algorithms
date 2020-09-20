package 逐步求和得到正数最小值;
/**
 * FileName: MinStartValue.java
 * 类的详细说明
 * 给定一个整数数组 nums 。可以选定任意的 正数 startValue 作为初始值。
 * 需要从左到右遍历 nums 数组，并将 startValue 依次累加上 nums 数组中的值。
 * 在确保累加和始终大于等于 1 的前提下，选出一个最小的 正数 作为 startValue 。
 *
 * @label Array
 * @author &#x8c2f;&#x535a;
 * @Date 2020.4.19
 * @version 1.00
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MinStartValue {
    public static void main(String[] args)throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] nums = stringToIntegerArray(line);

            int ret = new Solution().minStartValue(nums);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }

    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for (int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }
}
class Solution{
    public int minStartValue(int[] nums){
        int ret=1,sum=0;
        for(int num:nums){
            sum += num;
            if(sum<0) ret = Math.max(ret,1-sum);
        }
        return ret;
    }
}