package 无重复字符串的最长子窜;
/**
 * FileName: LongestSubstringWithoutRepeatingCharacters.java
 * 类的详细说明
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * @label String HashSet TwoPointer SlidingWindow
 * @author &#x8c2f;&#x535a;
 * @Date 2020.4.28
 * @version 1.00
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            String s = stringToString(line);
            int res = new SolutionCopy3().lengthOfLongestSubstring(s);
            String out = Integer.toString(res);
            System.out.println(out);
        }
    }

    private static String stringToString(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        return input;
    }
}

class Solution {
    //暴力破解
    //复杂度 O(N^3)O(min(N,set.size()))
    //找出所有子串，返回满足要求子串的长度两两比较，保留最长值。
    public int lengthOfLongestSubstring(String s) {
        int len = s.length();
        if (len == 0)
            return 0;
        int ans = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j <= len; j++) {
                if (allUnique(s, i, j))
                    ans = Math.max(ans, j - i);
            }
        }
        return ans;
    }

    private boolean allUnique(String s, int start, int end) {
        Set<Character> set = new HashSet<>();
        while (start < end) {
            if (set.contains(s.charAt(start)))
                return false;
            set.add(s.charAt(start++));
        }
        return true;
    }
}
class SolutionCopy1{
    //滑动窗口
    //复杂度 O(N)O(min(N,set.size()))
    //从左到右每次找到当前无重复子串，保留最长子串长度
    public int lengthOfLongestSubstring(String s) {
        int len = s.length();
        if(s==null ||len==0)
            return 0;
        int ans=0,left=0,right=0;
        Set<Character> set = new HashSet<>();
        while(left<len && right<len){
            if(!set.contains(s.charAt(right))){
                set.add(s.charAt(right++));
                ans = Math.max(ans,right-left);
            }else {
                set.remove(s.charAt(left++));
            }
        }
        return ans;
    }
}
class SolutionCopy2{
    // 优化滑动窗口(HashMap)
    public int lengthOfLongestSubstring(String s) {
        int len = s.length(),ans = 0;
        Map<Character,Integer> map = new HashMap<>();//current index of character
        //try to extend the range[i,j]
        for(int i=0,j=0;j<len;j++){
            if(map.containsKey(s.charAt(j))){
                i = Math.max(map.get(s.charAt(j)),i);
            }
            ans = Math.max(ans,j-i+1);
            map.put(s.charAt(j),j+1);
        }
        return ans;
    }
}
class SolutionCopy3{
    /**
     * int [26] 用于字母 ‘a’ - ‘z’ 或 ‘A’ - ‘Z’
     * int [128] 用于ASCII码
     * int [256] 用于扩展ASCII码
     */
    //字符集假设
    public int lengthOfLongestSubstring(String s) {
        int len = s.length(),ans = 0;
        if(len < 2)
            return len;
        int[] index = new int[128];//current index of character
        //try to extend the range[i,j]
        for(int i=0,j=0;j<len;j++){
            i = Math.max(index[s.charAt(j)],i);//当子串出现重复字符，i++
            ans = Math.max(ans,j-i+1);//获取最长无重复子串
            index[s.charAt(j)] = j+1;//存储当前字符出现在字符串s中最新的位置+1
        }
        return ans;
    }
}