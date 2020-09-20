package 平衡二叉树;
/**
 * FileName: BalanceBinaryTree.java
 * 类的详细说明
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 *
 * @label Tree Depth-firstSearch
 * @author &#x8c2f;&#x535a;
 * @Date 2020.8.17
 * @version 1.00
 */

import Tree公共方法.PublicMethod;
import Tree公共方法.TreeNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BalanceBinaryTree {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个BinaryTree：[3,9,20,null,null,15,7]");
        while ((line = in.readLine()) != null) {
            TreeNode root = new PublicMethod().stringToBinaryTreeNode(line);
            boolean result = new Solution().isBalanced(root);
            String out = result ? "是" : "不是";
            System.out.printf("该树%s平衡二叉树", out);
        }
    }
}

class Solution {
    public boolean isBalanced(TreeNode root) {
        return height(root) >= 0;
    }

    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int lh = height(root.left), lr = height(root.right);
        if (lh >= 0 && lr >= 0 && Math.abs(lr - lh) <= 1) {
            return Math.max(lh, lr) + 1;
        } else {
            return -1;
        }
    }
}