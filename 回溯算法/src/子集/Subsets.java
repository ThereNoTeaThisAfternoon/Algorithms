package 子集;

import 公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * FileName: Subsets.java
 * 类的详细说明
 * Given a set of distinct integers, nums, return all possible subsets (the power set).
 * Note: The solution set must not contain duplicate subsets.
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.9.21 - 上午 9:12
 * @label Array Backtracking BitManipulation
 */
public class Subsets {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个整型数组：[1,2,3]");
        while ((line = in.readLine()) != null) {
            int[] nums = PublicMethod.stringToIntegerArray(line);
            List<List<Integer>> result = new Solution().subsets(nums);
            for (List<Integer> list : result) {
                System.out.println(list);
            }
        }
    }
}

/**
 * 枚举
 */
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>());
        for (int num : nums) {
            int size = ans.size();
            for (int j = 0; j < size; j++) {
                List<Integer> tmp = new ArrayList<>(ans.get(j));
                tmp.add(num);
                ans.add(tmp);
            }
        }
        return ans;
    }
}

/**
 * DFS
 */
class SolutionCopy {
    List<Integer> t = new ArrayList<>();
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        dfs(0, nums);
        return ans;
    }

    public void dfs(int cur, int[] nums) {
        if (cur == nums.length) {
            ans.add(new ArrayList<>(t));
            return;
        }
        t.add(nums[cur]);
        dfs(cur + 1, nums);
        t.remove(t.size() - 1);
        dfs(cur + 1, nums);
    }
}

/**
 * BitManipulation
 */
class SolutionCopy2 {
    List<Integer> t = new ArrayList<>();
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        for (int mask = 0; mask < (1 << n); ++mask) {
            t.clear();
            for (int i = 0; i < n; ++i) {
                if ((mask & (1 << i)) != 0) {
                    t.add(nums[i]);
                }
            }
            ans.add(new ArrayList<>(t));
        }
        return ans;
    }
}