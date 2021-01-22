package 数组形式的整数加法;

import Array公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * FileName: AddToArrayFormOfInteger.java
 * 类的详细说明
 * 对于非负整数 X 而言，X 的数组形式是每位数字按从左到右的顺序形成的数组。
 * 例如，如果 X = 1231，那么其数组形式为 [1,2,3,1]。
 * 给定非负整数 X 的数组形式 A，返回整数 X+K 的数组形式。
 * <p>
 * 1 <= A.length <= 10000
 * 0 <= A[i] <= 9
 * 0 <= K <= 10000
 * 如果 A.length > 1，那么 A[0] != 0
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2021.1.22 - 下午 7:36
 * @label Array
 */
public class AddToArrayFormOfInteger {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个整型数组A：[1,2,0,0]");
        while ((line = in.readLine()) != null) {
            int[] A = new PublicMethod().stringToIntegerArray(line);
            System.out.println("请输入整数K：34");
            int K = Integer.parseInt(in.readLine());
            List<Integer> result = new SolutionCopy().addToArrayForm(A, K);
            System.out.println(Arrays.toString(result.toArray()));
        }
    }
}

/**
 * 将整个加数 K 加入数组表示的数的最低位。
 */
class Solution {

    public List<Integer> addToArrayForm(int[] A, int K) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        int i = A.length - 1;
        while (i >= 0 || K > 0) {
            if (i >= 0) {
                K += A[i];
            }
            linkedList.addFirst(K % 10);
            K /= 10;
            i--;
        }
        return linkedList;
    }
}

/**
 * 逐位将数字加在一起，从低位到高位依次计算。
 * 任何时候，若加法的结果大于等于 10，把进位的 1 加入到下一位的计算中。
 */
class SolutionCopy {

    public List<Integer> addToArrayForm(int[] A, int K) {
        List<Integer> list = new ArrayList<>();
        for (int i = A.length - 1; i >= 0; --i) {
            int sum = A[i] + K % 10;
            K /= 10;
            if (sum >= 10) {
                sum -= 10;
                K += 1;
            }
            list.add(sum);
        }
        while (K > 0) {
            list.add(K % 10);
            K /= 10;
        }
        Collections.reverse(list);
        return list;
    }
}