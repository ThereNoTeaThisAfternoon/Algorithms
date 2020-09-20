package 不同的二叉搜索树Ⅱ;
/**
 * FileName: UniqueBinarySearchTree.java
 * 类的详细说明
 * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。
 *
 * @label Tree DynamicProgramming
 * @author &#x8c2f;&#x535a;
 * @Date 2020.7.21
 * @version 1.00
 */

import Tree公共方法.PublicMethod;
import Tree公共方法.TreeNode;
import com.sun.source.tree.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UniqueBinarySearchTreeTwo {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个整数n");
        while ((line = in.readLine()) != null) {
            List<TreeNode> result = new Solution().generateTrees(Integer.parseInt(line));
            List<String> out = new ArrayList<>();
            String str = "";
            for (TreeNode root : result) {
                str = new PublicMethod().integerTreeString(root);
                out.add("[" + str.substring(0, str.length() - 2) + "]");
            }
            out.forEach(System.out::println);
        }
    }
}

class Solution {
    public List<TreeNode> generateTrees(int n) {
        if (n == 0)
            return new LinkedList<>();
        return generateTrees(1, n);
    }

    private List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> allTrees = new LinkedList<>();
        if (start > end) {
            allTrees.add(null);
            return allTrees;
        }
        //枚举可行根节点
        for (int i = start; i <= end; i++) {
            //列举所有可行的左子树合集
            List<TreeNode> leftTrees = generateTrees(start, i - 1);
            //列举所有可行的右子树合集
            List<TreeNode> rightTrees = generateTrees(i + 1, end);
            //从左子树合集中选出一颗左子树，从右子树合集中选出一颗右子树，拼接到根节点
            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode curTree = new TreeNode(i);
                    curTree.left = left;
                    curTree.right = right;
                    allTrees.add(curTree);
                }
            }
        }
        return allTrees;
    }
}