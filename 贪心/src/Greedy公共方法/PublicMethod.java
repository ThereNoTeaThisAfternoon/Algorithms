package Greedy公共方法;

/**
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.10.23 - 下午 3:28
 * @label staticMethod
 */
public class PublicMethod {

    /**
     * String:"[7,1,5,3,6,4]" -> int[]
     */
    public static int[] stringToIntegerArray(String input) {
        input = input.trim(); // 去除两旁多余空格
        input = input.substring(1, input.length() - 1); // 去除两旁括号
        if ("".equals(input)) {
            return new int[0];
        }
        String[] nums = input.split(",");
        int[] output = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            output[i] = Integer.parseInt(nums[i]);
        }
        return output;
    }
}
