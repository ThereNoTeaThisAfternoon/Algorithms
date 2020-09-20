package 二叉树的最大深度;
/**
 * FileName: MaximumDepthOfBinaryTree.java
 * 类的详细说明
 * 给定一个二叉树，找出其最大深度。
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * @label Tree Depth-firstSearch Breadth-firstSearch
 * @author &#x8c2f;&#x535a;
 * @Date 2020.6.29
 * @version 1.00
 */

import Tree公共方法.PublicMethod;
import Tree公共方法.TreeNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class MaximumDepthOfBinaryTree {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个二叉树：[3,9,20,null,null,15,7]");
        while ((line = in.readLine()) != null) {
            TreeNode root = new PublicMethod().stringToBinaryTreeNode(line);
            int result = new SolutionCopy2().maxDepth(root);
            System.out.println("二叉树的最大深度为：" + result);
        }
    }
}

class Solution {
    //DFS+分治
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}

class SolutionCopy1 {
    //DFS
    int maxLevel = 0;

    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        dfs(root, 1);
        return maxLevel;
    }

    private void dfs(TreeNode root, int level) {
        if (root == null)
            return;
        if (level > maxLevel)
            maxLevel = level;
        dfs(root.left, level + 1);
        dfs(root.right, level + 1);
    }
}

class SolutionCopy2 {
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        int maxLevel = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            maxLevel++;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
        }
        return maxLevel;
    }
}

