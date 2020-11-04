package Array公共方法;

import java.util.List;

public class PublicMethod {
    //Strings -> Array
    //[100,4,200,1,3,2]
    public int[] stringToIntegerArray(String input) {
        input = input.trim();//除去两旁多余的空格
        input = input.substring(1, input.length() - 1);//除掉左右括号
        if (input.length() == 0)
            return new int[0];
        String[] strings = input.split(",");//regularExpression
        int[] output = new int[strings.length];
        int index = 0;
        for (String string : strings) {//string[] -->> int[]
            output[index++] = Integer.parseInt(string);
        }
        return output;
    }

    //StringList -> String
    public String stringListToString(List<String> input) {
        StringBuilder output = new StringBuilder();
        for (String string : input) {
            output.append(string).append(", ");
        }
        return "[" + output.substring(0, output.length() - 2) + "]";
    }

    /**
     * String:"[[1,5,9],[10,11,13],[12,13,15]]" -> int[][]
     */
    public static int[][] stringTo2DIntegerArray(String input) {
        input = input.trim(); // 去除两旁多余空格
        input = input.substring(1, input.length() - 1); // 去掉左右括号
        if (input.length() == 0) {
            return new int[0][];
        }
        // 获取行数
        int rows = 0;
        for (char ch : input.toCharArray()) {
            if (ch == ']') {
                rows++;
            }
        }
        // 获取列数
        int cols = 1;
        for (char ch : input.toCharArray()) {
            if (ch == ',') {
                cols++;
            } else if (ch == ']') {
                break;
            }
        }
        int[][] out = new int[rows][cols];
        int i = 0;
        StringBuilder str = new StringBuilder();
        for (int a = 0; a < rows; a++) {
            for (int b = 0; b < cols; b++) {
                while (i < input.length() && !Character.isDigit(input.charAt(i)))
                    i++;
                while (i < input.length() && Character.isDigit(input.charAt(i))) {
                    str.append(input.charAt(i++));
                }
                out[a][b] = Integer.parseInt(str.toString());
                str = new StringBuilder();
            }
        }
        return out;
    }

    /**
     * int[][]:{{1,2},{1,2}} -> String
     */
    public static String integer2DArrayToString(int[][] input) {
        StringBuilder output = new StringBuilder("[");
        for (int[] arr : input) {
            StringBuilder sb = new StringBuilder("[");
            for (int elem : arr) {
                sb.append(elem).append(",");
            }
            output.append(sb.substring(0, sb.length() - 1)).append("], ");
        }
        return output.substring(0, output.length() - 2) + "]";
    }
}

