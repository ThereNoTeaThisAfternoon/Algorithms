package 长按键入;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * FileName: LongPressedName.java
 * 类的详细说明
 * 你的朋友正在使用键盘输入他的名字 name。偶尔，在键入字符 c 时，按键可能会被长按，而字符可能被输入 1 次或多次。
 * 你将会检查键盘输入的字符 typed。如果它对应的可能是你的朋友的名字（其中一些字符可能被长按），那么就返回 True。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.10.21 - 上午 8:13
 * @label TwoPointers String
 */
public class LongPressedName {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String name;
        System.out.println("请输入字符串name：alex");
        while ((name = in.readLine()) != null) {
            System.out.println("请输入一个字符串typed：aaleex");
            String typed = in.readLine();
            boolean result = new Solution().isLongPressedName(name, typed);
            System.out.println("匹配" + (result ? "成功" : "失败"));
        }
    }
}

class Solution {

    public boolean isLongPressedName(String name, String typed) {
        int i = 0, j = 0;
        while (j < typed.length()) {
            if (i < name.length() && name.charAt(i) == typed.charAt(j)) {// 两个字符相同，指针后移
                i++;
                j++;
            } else if (j > 0 && typed.charAt(j - 1) == typed.charAt(j)) {// 两串匹配位字符不同，当前字符与本串的前一个字符匹配
                j++;
            } else {
                return false;
            }
        }
        return i == name.length();
    }
}