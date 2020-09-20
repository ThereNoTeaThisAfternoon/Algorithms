package Dynamic公共方法;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class PublicMethod {
    private static final Set<Character> CHARS = Set.of('0', '1');

    //String -> IntegerArray
    public int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.equals(""))
            return new int[0];

        String[] strings = input.split(",");
        int[] output = new int[strings.length];
        int index = 0;
        for (String string : strings) {
            output[index++] = Integer.parseInt(string);
        }
        return output;
    }

    //String -> IntegerArray
    public int[][] stringTo2DIntegerArray(String input) {
        char[][] ans = stringToCharacterArray(input);
        int row = ans.length;
        int col = ans[ans.length - 1].length;
        int[][] out = new int[row][col];
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                out[i][j] = ans[i][j];
            }
        }
        return out;
    }

    //String -> CharacterArray
    //[[0,1,0],[1,1,0],[1,1,0]] 01矩阵
    //产生行列可以不同的矩阵
    public char[][] stringToCharacterArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        int rows = 0;
        int cols;
        int allNum = 0;
        for (int i = 0; i < input.length(); i++) {
            if (']' == input.charAt(i))
                rows++;
            if (CHARS.contains(input.charAt(i)))
                allNum++;
        }
        cols = allNum / rows;
        char[][] output = new char[rows][cols];

        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                while (!CHARS.contains(input.charAt(index))) {
                    index++;
                }
                output[i][j] = input.charAt(index++);
            }
        }
        return output;
    }

    //String -> StringArray
    //["A..","AAA","..."]
    public String[] stringToStringArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0)
            return new String[0];
        StringBuilder sB = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) != '"')
                sB.append(input.charAt(i));
        }
        return sB.toString().split(",");
    }

    public List<String> stringToStringList(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0)
            return new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (char ch : input.toCharArray())
            if (Character.isLetter(ch) || ch == ',')
                sb.append(ch);
        return new ArrayList<>(Arrays.asList(sb.toString().split(",")));
    }
}
