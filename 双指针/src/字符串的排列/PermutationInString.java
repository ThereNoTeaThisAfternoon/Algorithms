package 字符串的排列;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * FileName: PermutationInString.java
 * 类的详细说明
 * Give two strings s1 and s2, write a function to return true if s2 contains the permutation of s1.
 * In other words, one of the first string's permutations is the substring of the second string.
 * <p>
 * The input strings only contain lower case letters.
 * The length of both given strings is in range [1, 10,000].
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2021.2.10 - 下午 1:31
 * @label
 */
public class PermutationInString {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("please input string s1：ab");
        while ((line = in.readLine()) != null) {
            System.out.println("please input another string s2：eidboaoo");
            boolean result = new Solution().checkInclusion(line, in.readLine());
            System.out.println("the string s2 " + (result ? "contains" : "doesn't contain") + " the permutation of s1");
        }
    }
}

class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int n = s2.length();
        int[] dict = new int[26];
        int[] freq = new int[26];
        int size = 0;
        for(char c : s1.toCharArray()) {
            if(dict[c - 'a'] == 0) size++;
            dict[c - 'a']++;
        }
        int match = 0;
        int left = 0, right = 0;
        while(right < n) {
            char rc = s2.charAt(right);
            freq[rc - 'a']++;
            if(freq[rc - 'a'] == dict[rc - 'a']) match++;
            while(size == match) {
                if(right - left + 1 == s1.length()) return true;
                char lc = s2.charAt(left);
                freq[lc - 'a']--;
                if(freq[lc - 'a'] < dict[lc - 'a']) match--;
                left++;
            }
            right++;
        }
        return false;
    }
}