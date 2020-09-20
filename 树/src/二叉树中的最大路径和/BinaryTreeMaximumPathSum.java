package 二叉树中的最大路径和;
/**
 * FileName: BinaryTreeMaximumPathSum.java
 * 类的详细说明
 * 给定一个非空二叉树，返回其最大路径和。
 * 本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
 *
 * @label Tree Depth-firstSearch
 * @author &#x8c2f;&#x535a;
 * @Date 2020.6.21
 * @version 1.00
 */

import Tree公共方法.PublicMethod;
import Tree公共方法.TreeNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class BinaryTreeMaximumPathSum {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个树：[-10,9,20,null,null,15,7]");
        while ((line = in.readLine()) != null) {
            TreeNode root = new PublicMethod().stringToBinaryTreeNode(line);
            int result = new Solution().maxPathSum(root);
            System.out.println("树的最大路径和为：" + result);
        }
    }
}

class Solution {
    private int ret = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        /**
         对于任意一个节点, 如果最大和路径包含该节点, 那么只可能是两种情况:
         1. 其左右子树中所构成的和路径值较大的那个加上该节点的值后向父节点回溯构成最大路径
         2. 左右子树都在最大路径中, 加上该节点的值构成了最终的最大路径
         **/
        getMax(root);
        return ret;
    }

    private int getMax(TreeNode root) {
        if (root == null)
            return 0;
        int left = Math.max(0, getMax(root.left));// 如果子树路径和为负则应当置0表示最大路径不包含子树
        int right = Math.max(0, getMax(root.right));
        ret = Math.max(ret, root.val + left + right); // 判断在该节点包含左右子树的路径和是否大于当前最大路径和
        return Math.max(left, right) + root.val;
    }
}