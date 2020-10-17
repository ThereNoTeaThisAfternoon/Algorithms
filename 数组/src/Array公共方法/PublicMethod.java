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
}

