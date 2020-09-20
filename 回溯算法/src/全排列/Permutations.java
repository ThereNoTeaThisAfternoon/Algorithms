package 全排列;
/**
 * FileName: Permutations.java
 * 类的详细说明
 * 给定一个 没有重复 数字 的序列，返回其所有可能的全排列。
 *
 * @label Backtracking
 * @author &#x8c2f;&#x535a;
 * @Date 2020.4.25
 * @version 1.00
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Permutations {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] nums = stringToIntegerArray(line);
            List<List<Integer>> result = new SolutionCopy().permute(nums);
            String out = int2dListToString(result);
            System.out.println(out);
        }
    }

    private static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0)
            return new int[0];
        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        int index = 0;
        for (String part : parts) {
            output[index++] = Integer.parseInt(part);
        }
        return output;
    }

    private static String int2dListToString(List<List<Integer>> nums) {
        StringBuilder sb = new StringBuilder("[\n");
        for (List<Integer> list : nums) {
            sb.append(integerArrayListToString(list));
            sb.append(",\n");
        }
        String s = sb.toString();
        s = s.substring(0,s.length()-1)+"\n]";

        return s;
    }

    private static String integerArrayListToString(List<Integer> nums) {
        int len = nums.size();
        if (len == 0) return "[]";

        String result = "";
        for (int index = 0; index < len; index++) {
            Integer number = nums.get(index);
            result += number + ", ";
        }

        return "[" + result.substring(0, result.length() - 2) + "]";
    }
}

class Solution {
    //DFS backtrack
    /**
     * @param nums intArray
     * @return 2dArrayList
     */
    public List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0)
            return res;
        Deque<Integer> path = new ArrayDeque<>();
        boolean[] used = new boolean[len];
        dfs(nums, len, 0, path, used, res);
        return res;
    }

    /**
     *
     * @param nums intArray
     * @param len intArray的长度
     * @param depth int 递归到第几层
     * @param path intStack 所选数的栈
     * @param used booleanArray
     * @param res result
     */
    private void dfs(int[] nums, int len, int depth, Deque<Integer> path, boolean[] used, List<List<Integer>> res) {
        if (depth == len) {
            res.add(new ArrayList<>(path));//将数组栈添加到列表中
            return;
        }
        for(int index=0;index<len;index++){//
            if(used[index])
                continue;
            path.addLast(nums[index]);
            used[index] = true;
            dfs(nums,len,depth+1,path,used,res);
            path.removeLast();
            used[index] = false;
        }
    }
}
class SolutionCopy{
    //backtrack DFS
    public List<List<Integer>> permute(int[] nums){
        List<List<Integer>> res = new ArrayList<>();
        ArrayList<Integer> output = new ArrayList<>();
        for(int num:nums)
            output.add(num);
        backtrack(0,nums.length,output,res);
        return res;
    }

    private void backtrack(int depth, int len, ArrayList<Integer> output, List<List<Integer>> res) {
        if(depth == len){
            res.add(new ArrayList<>(output));
        }
        for(int i=depth;i<len;i++){
            Collections.swap(output,depth,i);//动态维护数组
            backtrack(depth+1,len,output,res);//继续递归填下一个数
            Collections.swap(output,depth,i);//撤销操作
        }
    }
}