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
     * String:"[[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]" -> int[][]
     */
    public int[][] stringTo2DIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0][];
        }
        int n = 0;
        for (char ch : input.toCharArray())
            if (ch == ']')
                n++;
        int[][] out = new int[n][n];
        int i = 0;
        StringBuilder str = new StringBuilder();
        for (int a = 0; a < n; a++) {
            for (int b = 0; b < n; b++) {
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
}

