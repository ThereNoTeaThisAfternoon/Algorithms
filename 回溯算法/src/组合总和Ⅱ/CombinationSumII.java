package 组合总和Ⅱ;

import 公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * FileName: CombinationSumII.java
 * 类的详细说明
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的每个数字在每个组合中只能使用一次。
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。 
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @Date 2020.9.10
 * @label Array Backtracking
 */
public class CombinationSumII {
    public static void main(String[] args) throws IOException {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            System.out.println("请输入一个一维整型数组：[2，3，5]");
            while ((line = in.readLine()) != null) {
                int[] candidates = PublicMethod.stringToIntegerArray(line);
                System.out.println("请输入目标值target：8");
                int target = Integer.parseInt(in.readLine());
                List<List<Integer>> result = new Solution().combinationSum2(candidates, target);
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
        }
    }
}

class Solution {

    List<List<Integer>> paths = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        backtrack(candidates, target, 0);
        return paths;
    }

    private void backtrack(int[] candidates, int target, int index) {
        if (target == 0) {
            paths.add(new ArrayList<>(path));
            return;
        }
        for (int i = index; i < candidates.length; ++i) {
            if (candidates[i] <= target) {
                if (index < i && candidates[i] == candidates[i - 1]) {
                    continue;
                }
                path.add(candidates[i]);
                backtrack(candidates, target - candidates[i], i + 1);
                path.remove(path.size() - 1);
            }
        }
    }
}