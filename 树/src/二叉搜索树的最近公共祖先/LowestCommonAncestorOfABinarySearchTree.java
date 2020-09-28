package 二叉搜索树的最近公共祖先;

import Tree公共方法.PublicMethod;
import Tree公共方法.TreeNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * FileName: LowestCommonAncestorOfABinarySearchTree.java
 * 类的详细说明
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.9.28 - 上午 8:44
 * @label Tree Depth-firstSearch
 */
public class LowestCommonAncestorOfABinarySearchTree {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个二叉搜索树：[6,2,8,0,4,7,9,null,null,3,5]");
        while ((line = in.readLine()) != null) {
            TreeNode root = new PublicMethod().stringToBinaryTreeNode(line);
            System.out.println("请输入一个节点：2");
            TreeNode p = new TreeNode(Integer.parseInt(in.readLine()));
            System.out.println("请输入另一个节点：8");
            TreeNode q = new TreeNode(Integer.parseInt(in.readLine()));
            TreeNode result = new Solution().lowestCommonAncestor(root, p, q);
            System.out.println("该二叉树的最近公共祖先为：" + result.val);
        }
    }
}

class Solution {
    private TreeNode ans;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        lca(root, p, q);
        return ans;
    }

    private void lca(TreeNode root, TreeNode p, TreeNode q) {
        if ((root.val - p.val) * (root.val - q.val) <= 0) {
            ans = root;
        } else if (root.val > p.val && root.val > q.val) {
            lca(root.left, p, q);
        } else {
            lca(root.right, p, q);
        }
    }
}