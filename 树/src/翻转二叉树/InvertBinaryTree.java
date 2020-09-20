package 翻转二叉树;

import Tree公共方法.PublicMethod;
import Tree公共方法.TreeNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * FileName: InvertBinaryTree.java
 * 类的详细说明
 * 翻转一颗二叉树
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @Date 2020.9.16 - 下午 10:32
 * @label Tree DFS(前中后序遍历) BFS
 */
public class InvertBinaryTree {
    public static void main(String... args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一颗二叉树：[4,2,6,1,3,5,7]");
        while ((line = in.readLine()) != null) {
            TreeNode root = new PublicMethod().stringToBinaryTreeNode(line);
            TreeNode result = new SolutionCopy2().invertTree(root);
            String out = new PublicMethod().integerTreeToInorderString(result);
            System.out.println(out.substring(0, out.length() - 1));
        }
    }
}

/**
 * 利用前序遍历
 */
class Solution {
    //先序遍历--自顶向下交换
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        //保存右子树
        TreeNode rightTree = root.right;
        //交换左右子树位置
        root.right = invertTree(root.left);
        root.left = invertTree(rightTree);
        return root;
    }
}

/**
 * 利用中序遍历
 */
class SolutionCopy {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        invertTree(root.left); // 递归找到左节点
        TreeNode rightNode = root.right; // 保存右节点
        root.right = root.left;
        root.left = rightNode;
        // 递归找到右节点 继续交换 : 因为此时左右节点已经交换了,所以此时的右节点为root.left
        invertTree(root.left);
        return root;
    }
}

/**
 * 利用后序遍历
 */
class SolutionCopy2 {
    public TreeNode invertTree(TreeNode root) {
        // 后序遍历-- 从下向上交换
        if (root == null) return null;
        TreeNode leftNode = invertTree(root.left);
        TreeNode rightNode = invertTree(root.right);
        root.right = leftNode;
        root.left = rightNode;
        return root;
    }
}

/**
 * 利用层次遍历
 */
class SolutionCopy3 {
    public TreeNode invertTree(TreeNode root) {
        // 层次遍历--直接左右交换即可
        if (root == null) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode rightTree = node.right;
            node.right = node.left;
            node.left = rightTree;
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return root;
    }
}