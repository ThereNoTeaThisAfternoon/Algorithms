package HashTable公共方法;

import java.util.List;

/**
 * FileName:
 * 类的详细说明
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.10.28 - 下午 10:45
 * @label
 */
public class PublicMethod {

    /**
     * String:"[1,1,2,3,1]" -> Array:{1,1,2,3,1}
     */
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();//除去两旁多余的空格
        input = input.substring(1, input.length() - 1);//除掉左右括号
        if (input.length() == 0) {
            return new int[0];
        }
        String[] strings = input.split(",");//regularExpression
        int[] output = new int[strings.length];
        int index = 0;
        for (String string : strings) {//string[] -->> int[]
            output[index++] = Integer.parseInt(string);
        }
        return output;
    }
}
