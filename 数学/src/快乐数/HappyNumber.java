package 快乐数;
/**
 * FileName: HappyNumber.java
 * 类的详细说明
 * 判断输入的数字是不是快乐数
 * 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，
 * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
 * 如果 可以变为  1，那么这个数就是快乐数。
 *
 * @label Math HashTable
 * @author &#x8c2f;&#x535a;
 * @Date 2020.4.11
 * @version 1.00
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class HappyNumber {
    public static String booleanToString(boolean input) {
        return input ? "True" : "False";
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int n = Integer.parseInt(line);

            boolean ret = new SolutionCopy1().isHappy(n);

            String out = booleanToString(ret);

            System.out.print(out);
        }
    }
}

class Solution {
    /**
     * MethodName: isHappy
     * 类方法详细使用说明
     * 递归
     *
     * @param n int 待判断的数
     * @return boolean
     */
    public boolean isHappy(int n) {
        if (n == 4) return false;
        int result = 0;
        while (n > 0) {
            result += Math.pow(n % 10, 2);
            n = n / 10;
        }
        return result == 1 ? true : isHappy(result);

    }
}
class SolutionCopy1{
    //HashSet
    Set<Integer> set = new HashSet<>();
    public boolean isHappy(int n) {
        while(n!=1&& !set.contains(n)){//n不为1，且集合不包含n
            set.add(n);
            n = getNext(n);
        }
        return n == 1;
    }

    private int getNext(int n) {
        int res = 0;
        while(n>0){
            res += Math.pow(n%10,2);
            n /= 10;
        }
        return res;
    }
}

class SolutionCopy2 {
    /**
     * 如果给定的数字最后会一直循环重复，那么快的指针（值）一定会追上慢的指针（值），也就是
     * 两者一定会相等。如果没有循环重复，那么最后快慢指针也会相等，且都等于1。
     */
    //快慢指针
    public boolean isHappy(int n) {
        int fast = n;
        int slow = n;
        do {
            slow = squareSum(slow);
            fast = squareSum(fast);
            fast = squareSum(fast);
        } while (slow != fast);
        if (fast == 1)
            return true;
        else return false;
    }

    private int squareSum(int m) {
        int squaresum = 0;
        while (m != 0) {
            squaresum += (m % 10) * (m % 10);
            m /= 10;
        }
        return squaresum;
    }
}