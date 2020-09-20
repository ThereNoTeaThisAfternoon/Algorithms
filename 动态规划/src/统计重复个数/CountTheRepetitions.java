package 统计重复个数;
/**
 * FileName: CountTheRepetitions.java
 * 类的详细说明
 * input -> "acb" \n 400 \n "ab" \n 10 求串二出现次数n
 * 可以删除串一的字符使条件满足
 *
 * @label DynamicProgramming
 * @author &#x8c2f;&#x535a;
 * @Date 2020.4.19
 * @version 1.00
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class CountTheRepetitions {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            String s1 = stringToString(line);

            line = in.readLine();
            int n1 = Integer.parseInt(line);

            line = in.readLine();
            String s2 = stringToString(line);

            line = in.readLine();
            int n2 = Integer.parseInt(line);

            int ret = new Solution().getMaxRepetitions(s1, n1, s2, n2);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }

    public static String stringToString(String input) {
        if (input == null) {
            return "null";
        }
        return input;
    }
}

class Solution {
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        if (n1 == 0) return 0;
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        int l1 = s1.length();
        int l2 = s2.length();
        int couts1 = 0;//经历多少s1
        int couts2 = 0;//经历多少s2
        int p = 0;//当前在s2的位置
        Map<Integer, int[]> mp = new HashMap<>();//记录每一次s1扫描结束后当前的状态，寻找循环
        while (couts1 < n1) {
            for (int i = 0; i < l1; i++) {
                if (c1[i] == c2[p]) {//往前
                    p++;
                    if (p == l2) {//s2扫描结束从头开始循环
                        p = 0;
                        couts2++;
                    }
                }
            }
            couts1++;
            if (!mp.containsKey(p)) {
                mp.put(p, new int[]{couts1, couts2});//记录当前状态

            } else {//出现了循环 这次结束后p的位置和以前某一次一样，就是循环
                int[] last = mp.get(p);
                int circle1 = couts1 - last[0];
                int circle2 = couts2 - last[1];
                couts2 += circle2 * ((n1 - couts1) / circle1);
                couts1 = couts1 + ((n1 - couts1) / circle1) * circle1;//更新新他们
            }
        }
        return couts2 / n2;
    }
}