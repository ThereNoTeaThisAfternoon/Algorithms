package 二叉搜索树的最小绝对差;

import Tree公共方法.PublicMethod;
import Tree公共方法.TreeNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * FileName: MinimumAbsoluteDifferenceInBST.java
 * 类的详细说明
 * 给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。
 * 树中至少有 2 个节点。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.10.12 - 下午 10:08
 * @label Tree Depth-firstSearch
 */
public class MinimumAbsoluteDifferenceInBST {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;

        System.out.println("请输入一颗二叉查找树：[100,68,145,null,77,111,150,73,null,110]");
        while ((line = in.readLine()) != null) {
            // 层序遍历生成二叉查找树
            TreeNode root = new PublicMethod().stringToBinaryTreeNode(line);
            int result = new Solution().getMinimumDifference(root);
            System.out.println("二叉搜索树的最小绝对差为：" + result);
        }
    }
}

class Solution {

    private int min = Integer.MAX_VALUE;
    private TreeNode pre;

    public int getMinimumDifference(TreeNode root) {
        if (root.left != null) {
            getMinimumDifference(root.left);
        }
        if (pre != null) {
            min = Math.min(min, root.val - pre.val);
        }
        pre = root;
        if (root.right != null) {
            getMinimumDifference(root.right);
        }
        return min;
    }
}