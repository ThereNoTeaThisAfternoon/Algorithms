package 每日温度;
/**
 * FileName: DailyTemperatures.java
 * 类的详细说明
 * 根据每日 气温 列表，请重新生成一个列表，对应位置的输出是需要再等待多久温度才会升高超过该日的天数。
 * 如果之后都不会升高，请在该位置用 0 来代替。
 *
 * @label Stack HashTable
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @Date 2020.6.11
 */

import Stack公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class DailyTemperatures {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入气温列表：[73,74,75,71,69,72,76,73]");
        while ((line = in.readLine()) != null) {
            int[] T = PublicMethod.stringToIntegerArray(line);
            int[] result = new Solution().dailyTemperatures(T);
            String out = Arrays.toString(result);
            System.out.println("温度升高到超过该日的天数分别为" + out);
        }
    }
}

class Solution {
    public int[] dailyTemperatures(int[] T) {
        int[] res = new int[T.length];
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < T.length; i++) {
            while (!stack.isEmpty() && T[i] > T[stack.peek()]) {
                res[stack.peek()] = i - stack.pop();
            }
            stack.push(i);
        }
        return res;
    }
}

class SolutionCopy {
    //枚举法
    public int[] dailyTemperatures(int[] T) {
        int len = T.length;
        if (len < 2)
            return len == 0 ? new int[0] : new int[]{1};
        int[] ans = new int[len];
        int index = 0;
        for (int i = 0; i < len - 1; i++) {
            int temp = T[i];
            for (int j = i + 1; j < len; j++) {
                if (temp < T[j]) {
                    ans[index++] = j - i;
                    break;
                }
                if (j == len - 1)
                    index++;
            }
        }
        return ans;
    }
}