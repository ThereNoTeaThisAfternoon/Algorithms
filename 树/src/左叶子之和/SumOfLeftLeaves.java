package 左叶子之和;

import Tree公共方法.PublicMethod;
import Tree公共方法.TreeNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * FileName:
 * 类的详细说明
 * Find the sum of left leaves in a given binary tree
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.9.20 - 下午 8:01
 */
public class SumOfLeftLeaves {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一颗二叉树：[3,9,20,null,null,15,7]");
        while ((line = in.readLine()) != null) {
            TreeNode root = new PublicMethod().stringToBinaryTreeNode(line);
            int result = new SolutionCopy().sumOfLeftLeaves(root);
            System.out.println("所有左叶子的值之和为：" + result);
        }
    }
}

/**
 * DFS
 */
class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        return root == null ? 0 : sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right)
                + (root.left != null && root.left.left == null && root.left.right == null ? root.left.val : 0);
    }
}

/**
 * BFS
 */
class SolutionCopy {
    public int sumOfLeftLeaves(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int sum = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                if (isLeave(node.left)) {
                    sum += node.left.val;
                } else {
                    queue.offer(node.left);
                }
            }
            if (node.right != null && !isLeave(node.right)) {
                queue.offer(node.right);
            }
        }
        return sum;
    }

    private boolean isLeave(TreeNode node) {
        return node.left == null && node.right == null;
    }
}