package 比较含退格的字符串;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * FileName: BackspaceStringCompare.java
 * 类的详细说明
 * 给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符。
 * 注意：如果对空文本输入退格字符，文本继续为空。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.10.19 - 下午 8:00
 * @label String Stack TwoPointers
 */
public class BackspaceStringCompare {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个字符串S：ab#c");
        while ((line = in.readLine()) != null) {
            String S = line;
            System.out.println("请输入一个字符串T：ad#c");
            String T = in.readLine();
            boolean result = new Solution().backspaceCompare(S, T);
            System.out.println("两个字符串" + (result ? "相等" : "不相等"));
        }
    }
}

/**
 * 从后向前比较比较每一个有效字符
 */
class Solution {
    public boolean backspaceCompare(String S, String T) {
        char[] s = S == null ? new char[]{} : S.toCharArray();
        char[] t = T == null ? new char[]{} : T.toCharArray();
        // 获取第一个有效字符位置
        int i = next(s, s.length), j = next(t, t.length);
        while (i >= 0 && j >= 0) {
            if (s[i] != t[j]) {
                return false;
            }
            i = next(s, i);
            j = next(t, j);
        }
        return i == -1 && j == -1;
    }

    private int next(char[] chars, int cur) {
        int count = 1;
        while (count > 0 && cur > 0) {
            if (chars[--cur] == '#') {
                count++;
            } else {
                count--;
            }

        }
        return count > 0 ? -1 : cur;
    }
}

/**
 * 将给定的字符串中的退格符和应当被删除的字符都去除，还原给定字符串的一般形式。然后直接比较两字符串是否相等
 * 如果它是退格符，那么我们将栈顶弹出
 * 如果它是普通字符，那么我们将其压入栈中
 */
class SolutionCopy {
    public boolean backspaceCompare(String S, String T) {
        return build(S).equals(build(T));
    }

    public String build(String str) {
        StringBuilder ret = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; ++i) {
            char ch = str.charAt(i);
            if (ch != '#') {
                ret.append(ch);
            } else {
                if (ret.length() > 0) {
                    ret.deleteCharAt(ret.length() - 1);
                }
            }
        }
        return ret.toString();
    }
}

