package 路径总和Ⅱ;

import Tree公共方法.PublicMethod;
import Tree公共方法.TreeNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * FileName: PathSumII.java
 * 类的详细说明
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 * Note: A leaf is a node with no children
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.9.26 - 下午 8:29
 * @label Tree Depth-firstSearch
 */
public class PathSumII {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一颗二叉树：[5,4,8,11,null,13,4,7,2,null,null,5,1]");
        while ((line = in.readLine()) != null) {
            TreeNode root = new PublicMethod().stringToBinaryTreeNode(line);
            System.out.println("请输入表示root-to-leaf的路径和：22");
            int sum = Integer.parseInt(in.readLine());
            List<List<Integer>> result = new Solution().pathSum(root, sum);
            result.forEach(System.out::println);
        }
    }
}

class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> ans = new ArrayList<>();
        if (root.val == sum && root.left == null && root.right == null) {
            List<Integer> list = new ArrayList<>();
            list.add(root.val);
            ans.add(list);
            return ans;
        }
        List<List<Integer>> left = pathSum(root.left, sum - root.val);
        List<List<Integer>> right = pathSum(root.right, sum - root.val);
        for (List<Integer> list : left) {
            list.add(root.val);
            ans.add(list);
        }
        for (List<Integer> list : right) {
            list.add(root.val);
            ans.add(list);
        }
        return ans;
    }
}