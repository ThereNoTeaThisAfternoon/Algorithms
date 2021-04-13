package 二叉搜索树节点最小距离;

import Tree公共方法.PublicMethod;
import Tree公共方法.TreeNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * FileName: MinimumDistanceBetweenBSTNodes.java
 * 类的详细说明
 * 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
 * <p>
 * 树中节点数目在范围 [2, 100] 内
 * 0 <= Node.val <= 105
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2021.4.13 - 下午 7:57
 * @label Tree Depth-firstSearch Recursion
 */
public class MinimumDistanceBetweenBSTNodes {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一棵二叉搜索树：[4,2,6,1,3]");
        while ((line = in.readLine()) != null) {
            TreeNode root = new PublicMethod().stringToBinaryTreeNode(line);
            int result = new Solution().minDiffInBST(root);
            System.out.println(result);
        }
    }
}

class Solution {

    private TreeNode pre = null; // 记录前一节点
    private int ans = Integer.MAX_VALUE; // 最小差值

    public int minDiffInBST(TreeNode root) {
        infixOrder(root);
        return ans;
    }

    private void infixOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        infixOrder(root.left);
        if (pre != null) {
            ans = Math.min(ans, root.val - pre.val);
        }
        pre = root;
        infixOrder(root.right);
    }
}
