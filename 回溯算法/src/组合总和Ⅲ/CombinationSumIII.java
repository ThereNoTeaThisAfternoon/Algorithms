package 组合总和Ⅲ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * FileName: CombinationSumIII.java
 * 类的详细说明
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。 
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @Date 2020.9.11
 * @label Backtracking
 */
public class CombinationSumIII {

    public static void main(String[] args) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try (in) {
            String line;
            System.out.println("请输入k：3");
            while ((line = in.readLine()) != null) {
                int k = Integer.parseInt(line);
                System.out.println("请输入n：9");
                int n = Integer.parseInt(in.readLine());
                List<List<Integer>> result = new Solution().combinationSum3(k, n);

                System.out.println("[");
                for (List<Integer> list : result) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("[");
                    for (Integer value : list) {
                        sb.append(value).append(", ");
                    }
                    System.out.println(sb.substring(0, sb.length() - 2) + "]");
                }
                System.out.println("]");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Solution {

    List<List<Integer>> lists = new ArrayList<>();
    List<Integer> list = new ArrayList<>();

    public List<List<Integer>> combinationSum3(int k, int n) {
        backtrack(k, n, 1);
        return lists;
    }

    private void backtrack(int k, int n, int index) {
        if (n == 0 && k == 0) {
            lists.add(new ArrayList<>(list));
            return;
        }
        for (int i = index; i <= 9; ++i) {
            if (n < index || k <= 0) {
                break;
            }
            list.add(i);
            backtrack(k - 1, n - i, i + 1);
            list.remove(list.size() - 1);
        }
    }
}