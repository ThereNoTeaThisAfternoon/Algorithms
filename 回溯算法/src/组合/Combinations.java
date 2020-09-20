package 组合;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * FileName: Combinations.java
 * 类的详细说明
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @Date 2020.9.8
 * @label Recursive BackTracking
 */
public class Combinations {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入两个整数 n 和 k: 4 2");
        while ((line = in.readLine()) != null) {
            int k = Integer.parseInt(in.readLine());
            List<List<Integer>> result = new Solution().combine(Integer.parseInt(line), k);
            if (result.size() == 0) {
                System.out.println("Sorry the list is empty");
            } else {
                for (List<Integer> list : result) {
                    System.out.println(list);
                }
            }
        }
    }
}

class Solution {

    private List<List<Integer>> combinations;
    private List<Integer> temp;

    public List<List<Integer>> combine(int n, int k) {
        combinations = new ArrayList<>();
        if (n < 1 || k == 0 || n < k) {
            return combinations;
        }
        temp = new ArrayList<>();
        recursive(n, k, 1);
        return combinations;
    }

    private void recursive(int n, int k, int cur) {
        if (k == 0) {
            combinations.add(new ArrayList<>(temp));
            return;
        }
        for (int i = cur; i <= n; ++i) {
            if (n - i < k - 1) {
                break;
            }
            temp.add(i);
            recursive(n, k - 1, i + 1);
            temp.remove(temp.size() - 1);
        }
    }
}