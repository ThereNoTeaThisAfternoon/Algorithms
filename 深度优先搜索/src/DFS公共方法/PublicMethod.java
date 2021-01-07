package DFS公共方法;


/**
 * FileName: PublicMethod.java
 * 类的详细说明
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2021.1.7 - 下午 8:00
 * @label
 */
public class PublicMethod {

    /**
     * String:"[[1,5,9],[10,11,13],[12,13,15]]" -> int[][]
     */
    public static int[][] stringTo2DIntegerArray(String input) {
        input = input.trim(); // 去除两旁多余空格
        input = input.substring(0, input.length() - 2); // 去除左右括号
        if ("".equals(input)) {
            return new int[0][];
        }
        // 获取行列数
        int rows = 0, cols = 1;
        boolean SWITCH = true;
        for (char ch : input.toCharArray()) {
            if (SWITCH && ch == ',') {
                cols++;
            } else if (ch == ']') {
                rows++;
                SWITCH = false;
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

}
