package 翻转字符串里的单词;

/**
 * FileName: ReverseWordsInAString.java
 * 类的详细说明
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 *
 * @label String
 * @author &#x8c2f;&#x535a;
 * @Date 2020.4.10
 * @version 1.00
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ReverseWordsInAString {
    public static String stringToString(String input) {
        if (input == null) {
            return "null";
        }
        return input;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            String s = stringToString(line);

            String out = (new SolutionCopy2().reverseWords(s));

            System.out.print(out);
        }
    }
}
class Solution{
    /**
     * MethodName: reverseWords
     * 导入char数组然后翻转数组，翻转每一个单词，去除多余空格
     *
     * @param s string 待翻转的字符串
     * @return 翻转后的string
     * @throws
     */
    public String reverseWords(String s) {
        if(s == null) return null;
        char[] s_arr = s.toCharArray();
        int n = s_arr.length;
        //翻转数组
        reverse(s_arr,0,n - 1);
        System.out.println(new String(s_arr));
        //翻转每个单词
        reverseWords(s_arr, n);
        System.out.println(new String(s_arr));
        //去除多余空格
        return clean_space(s_arr,n);
    }
    //逆序操作
    private void reverse(char[] s_arr,int i,int j){
        while(i<j){
            char temp = s_arr[i];
            s_arr[i++] = s_arr[j];
            s_arr[j--] = temp;
        }
    }
    //翻转每一个单词
    private void reverseWords(char[] s_arr,int n){
        int i = 0;
        int j = 0;
        while(j<n){
            //找到当前word第一个字母
            while(i<n && s_arr[i] == ' ')i++;
            j = i;
            //找到当前word最后一个字母
            while(j<n && s_arr[j] != ' ')j++;
            reverse(s_arr,i,j-1);
            i = j;
        }
    }
    //清除多余的空格
    private String clean_space(char[] s_arr,int n){
        int i = 0;
        int j = 0;
        while(j<n){
            while(j<n && s_arr[j] == ' ') j++;
            while(j<n && s_arr[j] != ' ') s_arr[i++] = s_arr[j++];
            while(j<n && s_arr[j] == ' ') j++;
            if(j<n) s_arr[i++] = ' ';
        }
        return new String(s_arr).substring(0,i);
    }
}
class SolutionCopy1 {
    public StringBuilder trimSpaces(String s) {
        int left = 0, right = s.length() - 1;
        // 去掉字符串开头的空白字符
        while (left <= right && s.charAt(left) == ' ') ++left;

        // 去掉字符串末尾的空白字符
        while (left <= right && s.charAt(right) == ' ') --right;

        // 将字符串间多余的空白字符去除
        StringBuilder sb = new StringBuilder();
        while (left <= right) {
            char c = s.charAt(left);

            if (c != ' ') sb.append(c);
            else if (sb.charAt(sb.length() - 1) != ' ') sb.append(c);

            ++left;
        }
        return sb;
    }

    public void reverse(StringBuilder sb, int left, int right) {
        while (left < right) {
            char tmp = sb.charAt(left);
            sb.setCharAt(left++, sb.charAt(right));
            sb.setCharAt(right--, tmp);
        }
    }

    public void reverseEachWord(StringBuilder sb) {
        int n = sb.length();
        int start = 0, end = 0;

        while (start < n) {
            // 循环至单词的末尾
            while (end < n && sb.charAt(end) != ' ') ++end;
            // 翻转单词
            reverse(sb, start, end - 1);
            // 更新start，去找下一个单词
            start = end + 1;
            ++end;
        }
    }

    public String reverseWords(String s) {
        StringBuilder sb = trimSpaces(s);

        // 翻转字符串
        reverse(sb, 0, sb.length() - 1);

        // 翻转每个单词
        reverseEachWord(sb);

        return sb.toString();
    }
}
class SolutionCopy2 {
    public String reverseWords(String s) {
        // 除去开头和末尾的空白字符
        s = s.trim();
        // 正则匹配连续的空白字符作为分隔符分割
        List<String> wordList = Arrays.asList(s.split("\\s+"));
        Collections.reverse(wordList);
        return String.join(" ", wordList);
    }
}