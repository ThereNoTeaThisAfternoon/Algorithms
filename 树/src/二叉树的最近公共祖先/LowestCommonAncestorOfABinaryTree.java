package 二叉树的最近公共祖先;
/**
 * FileName: LowestCommonAncestorOfABinaryTree.java
 * 类的详细说明
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * 对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，
 * 满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）
 *
 * @label Tree LowestCommonAncestor(LCA最低共同祖先)
 * @author &#x8c2f;&#x535a;
 * @Date 2020.5.10
 * @version 1.00
 */

import Tree公共方法.PublicMethod;
import Tree公共方法.TreeNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LowestCommonAncestorOfABinaryTree {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个列表表示a binary tree：[3,5,1,6,2,0,8,null,null,7,4]");
        while ((line = in.readLine()) != null) {
            TreeNode root = new PublicMethod().stringToBinaryTreeNode(line);
            System.out.println("请输入这个二叉树的一个子节点值：5");
            line = in.readLine();
            TreeNode p = new TreeNode(Integer.parseInt(line));
            System.out.println("请输入这个二叉树的另一个子节点值：1");
            line = in.readLine();
            TreeNode q = new TreeNode(Integer.parseInt(line));
            TreeNode ret = new SolutionCopy().lowestCommonAncestor(root, p, q);
            System.out.println("该最近公共祖先为：" + ret.val);
        }
    }
}

class Solution {
    //递归
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q)
            return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null)
            return root;
        return left != null ? left : right;
    }
}

class SolutionCopy {
    //存储父节点
    Map<Integer, TreeNode> parent = new HashMap<>();//key = 非空子节点的值，val = 父节点
    Set<Integer> visited = new HashSet<>();//p节点 -> root节点路径上的父节点的值

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root);
        while (p != null) {
            visited.add(p.val);
            p = parent.get(p.val);
        }
        while (q != null) {
            if (visited.contains(q.val))
                return q;
            q = parent.get(q.val);
        }
        return null;
    }

    private void dfs(TreeNode root) {
        if (root.left != null) {
            parent.put(root.left.val, root);
            dfs(root.left);
        }
        if (root.right != null) {
            parent.put(root.right.val, root);
            dfs(root.right);
        }
    }
}
