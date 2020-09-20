package 罗马数字转整数;
/**
 * FileName: RomanToInteger.java
 *
 * @label Math
 * @author &#x8c2f;&#x535a;
 * @Date 2020.4.23
 * @version 1.00
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int ret = new Solution().romanToInteger(line);
            String out = String.valueOf(ret);
            System.out.println(out);
        }
    }
}

class Solution {
    /**
     * 建立一个HashMap来映射符号和值，然后对字符串从右到左，
     * 如果当前字符代表的值不小于其右边，就加上该值；
     * 否则就减去该值。以此类推到最左边的数，最终得到的结果即是答案
     */
    private static Map<Character, Integer> roman_dict = new HashMap<>();
    static {
        int[] nums = {1, 5, 10, 50, 100, 500, 1000};
        char[] romans = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};
        for (int i = 0; i < nums.length; i++) {
            roman_dict.put(romans[i], nums[i]);
        }
    }
    public int romanToInteger(String s) {
        int ans = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (i==s.length()-1){
                ans += roman_dict.get(s.charAt(i));
                continue;
            }
            if(roman_dict.get(s.charAt(i))<roman_dict.get(s.charAt(i+1))){
                ans -= roman_dict.get(s.charAt(i));
            }else{
                ans+= roman_dict.get(s.charAt(i));
            }
        }

        return ans;
    }
}

class SolutionCopy {
    public int romanToInt(String s) {
        int res = 0;
        int lastNum = 100000;    //max

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int num = partRomanToInt(c);
            res += num;
            if (num > lastNum) {
                res -= 2 * lastNum;
            }
            lastNum = num;
        }
        return res;
    }

    private int partRomanToInt(char c) {
        int num = 0;
        switch (c) {
            case 'I':
                num = 1;
                break;
            case 'V':
                num = 5;
                break;
            case 'X':
                num = 10;
                break;
            case 'L':
                num = 50;
                break;
            case 'C':
                num = 100;
                break;
            case 'D':
                num = 500;
                break;
            case 'M':
                num = 1000;
                break;
            default:
                num = 0;
                break;
        }
        return num;
    }
}