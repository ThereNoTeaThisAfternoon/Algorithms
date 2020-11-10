package Sort公共方法;


import java.util.Arrays;

public class PublicMethod {

    /**
     * String:[0,1,2,3,4,5,6,7,8] -> int[]
     */
    public static int[] stringToIntegerArray(String input) {
        input = input.trim(); // 去除两旁多余空格
        input = input.substring(1, input.length() - 1); // 去除两旁括号
        if (input.equals("")) {
            return new int[0];
        }
        String[] parts = input.split(","); // regular expression
        int[] output = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            output[i] = Integer.parseInt(parts[i]);
        }
        return output;
    }

    /**
     * String:[[3,3],[5,-1],[-2,4]] -> int[][]
     */
    public static int[][] stringTo2DIntegerArray(String input) {
        input = input.trim(); // 去除两旁多余空格
        input = input.substring(1, input.length() - 1); // 去除两旁括号
        if ("".equals(input)) {
            return new int[0][];
        }
        // 获取行数，列数
        int rows = 0;
        int cols = 1;
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
        for (int i = 0; i < output.length; i++) {
            for (int j = 0; j < output[0].length; j++) {
                StringBuilder sb = new StringBuilder();
                // 跳过非数字字符
                while (index < input.length() && !Character.isDigit(input.charAt(index))) {
                    index++;
                }
                if (input.charAt(index - 1) == '-') {
                    sb.append("-");
                }
                // 获取该数字 10 111
                while (index < input.length() && Character.isDigit(input.charAt(index))) {
                    sb.append(input.charAt(index++));
                }
                output[i][j] = Integer.parseInt(sb.toString());
            }
        }
        return output;
    }

    public static String Integer2DArrayToString(int[][] input) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int[] arr : input) {
            sb.append(Arrays.toString(arr)).append(", ");
        }
        return sb.substring(0, sb.length() - 2) + "]";
    }
}
