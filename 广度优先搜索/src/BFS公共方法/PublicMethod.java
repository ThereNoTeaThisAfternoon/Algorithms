package BFS公共方法;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * FileName:
 * 类的详细说明
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.11.5 - 下午 9:31
 * @label
 */
public class PublicMethod {

    /**
     * String：[hot,dot,dog,lot,log,cog] -> List<String>
     */
    public static List<String> stringToStringList(String input) {
        input = input.trim(); // 去除两旁多余空格
        input = input.substring(1, input.length() - 1); // 去掉左右括号
        return new ArrayList<>(Arrays.asList(input.split(","))); // 切割后的字符串数组转列表
    }

}
