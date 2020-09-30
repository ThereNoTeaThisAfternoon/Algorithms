package 二叉搜索树的插入操作;

import Tree公共方法.PublicMethod;
import Tree公共方法.TreeNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * FileName: InsertIntoABST.java
 * 类的详细说明
 * 给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。
 * 返回插入后二叉搜索树的根节点。 输入数据保证，新值和原始二叉搜索树中的任意节点值都不同。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.9.30 - 下午 3:20
 * @label Tree Recursive Iteration
 */
public class InsertIntoABST {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一颗二叉搜索树：[4,2,7,1,3]");
        while ((line = in.readLine()) != null) {
            TreeNode root = new PublicMethod().stringToBinaryTreeNode(line);
            System.out.println("请输入待插入的节点值：5");
            int val = Integer.parseInt(in.readLine());
            TreeNode result = new SolutionCopy().insertIntoBST(root, val);
            String out = new PublicMethod().integerTreeToString(result);
            System.out.println(out);
        }
    }
}

/**
 * 递归
 */
class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (root.val > val) {//向左走
            root.left = insertIntoBST(root.left, val);
        } else {//向右走
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }
}

/**
 * Iteration
 */
class SolutionCopy {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode target = new TreeNode(val);
        if (root == null) {
            return target;
        }
        TreeNode parent = null, cur = root;
        while (cur != null) {
            parent = cur;
            cur = cur.val > val ? cur.left : cur.right;
        }
        if (parent.val > target.val) {
            parent.left = target;
        } else {
            parent.right = target;
        }
        return root;
    }
}
