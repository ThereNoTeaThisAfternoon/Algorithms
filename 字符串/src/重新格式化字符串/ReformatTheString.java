package 重新格式化字符串;
/**
 * FileName: ReformatTheString.java
 * 类的详细说明
 * 将含有数字和小写字母的混合字符串，重新格式化为任意相邻字符类型不同串 "1a2b3c4"
 * 如果不能格式化，返回一个空串
 *
 * @label String
 * @author &#x8c2f;&#x535a;
 * @Date 2020.4.19
 * @version 1.00
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class ReformatTheString {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            String ret = new SolutionCopy1().reformat(line);
            System.out.println(ret);
        }
    }
}

class Solution {
    public String reformat(String s) {
        StringBuilder sb = new StringBuilder();
        StringBuilder param = new StringBuilder(s);
        while (0 < param.length() - 1) {
            int end = param.length() - 1;
            //find pair(digiter,lowcase letter)
            while (end > 0 && Math.abs(param.charAt(end) - param.charAt(0)) < 40) {
                end--;
            }
            //no solution
            if (end == 0) return "";
            //pair(digiter,lowcase letter)
            char ch1, ch2;
            if (param.charAt(end) > param.charAt(0)) {
                ch1 = param.charAt(0);
                ch2 = param.charAt(end);
            } else {
                ch1 = param.charAt(end);
                ch2 = param.charAt(0);
            }
            //pair
            sb.append(ch1);
            sb.append(ch2);
            //update the length of param
            param.deleteCharAt(end);
            param.deleteCharAt(0);
        }
        //param has only one element,how to replace it
        if (param.length() == 1) {
            if (isDigiter(param.charAt(0))) {
                sb.append(param.charAt(0));
            } else {
                sb.insert(0, param.charAt(0));
            }
        }
        return sb.toString();
    }

    boolean isDigiter(char ch) {
        if (ch >= 0 && ch <= 9) {
            return true;
        }
        return false;
    }

}

class SolutionCopy1 {
    /**
     * 1.先把字母和数字分开，分别放入stack中
     * 2.要拼接成功，必须两个相减绝对值小于1，否则返回"";
     * 3.拼接过程中是先放字母还是数字，取决于判断两个stack大小（谁大谁先放，我做了判断，让大的保持先遍历）
     *
     * @param s String
     * @return reformatString
     */
    public String reformat(String s) {
        Stack<Character> stackAZ = new Stack();
        Stack<Character> stack09 = new Stack();
        Stack<Character> temp = new Stack();
        String result = "";
        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9') {
                stack09.add(c);
            } else {
                stackAZ.add(c);
            }
        }
        if (Math.abs(stack09.size() - stackAZ.size()) > 1) {
            return result;
        }
        if (stack09.size() > stackAZ.size()) {
            temp = stack09;
            stack09 = stackAZ;
            stackAZ = temp;
        }
        for (stackAZ.size(); stackAZ.size() > 0; ) {
            result = result + String.valueOf(stackAZ.pop());
            if (stack09.empty()) {
                continue;
            } else {
                result = result + String.valueOf(stack09.pop());
            }
        }
        return result;
    }
}