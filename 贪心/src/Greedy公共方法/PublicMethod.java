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

    /**
     * String:[[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]] -> int[][]
     */
    public static int[][] stringTo2DIntegerArray(String input) {
        input = input.trim(); // 去除两旁多余空格
        input = input.substring(1, input.length() - 1); // 去除两旁括号
        if ("".equals(input)) {
            return new int[0][];
        }
        // 获取行数、列数
        int rows = 0, cols = 1;
        boolean SWITCH = true;
        for (char word : input.toCharArray()) {
            if (SWITCH && word == ',') {
                cols++;
            } else if (word == ']') {
                SWITCH = false;
                rows++;
            }
        }
        int[][] output = new int[rows][cols];
        int index = 0;
        for (int[] row : output) {
            for (int i = 0; i < row.length; i++) {
                StringBuilder sb = new StringBuilder();
                // 跳过非数字字符
                while (index < input.length() && !Character.isDigit(input.charAt(index))) {
                    index++;
                }
                // 为负数加上'-'
                if (input.charAt(index - 1) == '-') {
                    sb.append('-');
                }
                // 获取数字 10 111
                while (index < input.length() && Character.isDigit(input.charAt(index))) {
                    sb.append(input.charAt(index++));
                }
                row[i] = Integer.parseInt(sb.toString());
            }
        }
        return output;
    }

    /**
     * int[][]:[[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]] -> String
     */
    public static String integer2DArrayToString(int[][] input) {
        StringBuilder output = new StringBuilder("[");
        for (int[] arr : input) {
            StringBuilder sb = new StringBuilder("[");
            for (int elem : arr) {
                sb.append(elem).append(",");
            }
            output.append(sb.substring(0, sb.length() - 1)).append("],");
        }
        return output.substring(0, output.length() - 2) + "]";
    }
}
