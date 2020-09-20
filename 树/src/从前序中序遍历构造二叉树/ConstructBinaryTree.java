package 从前序中序遍历构造二叉树;
/**
 * FileName: ConstructBinaryTree.java
 * 类的详细说明
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 * 注意: 假设树中没有重复的元素。
 *
 * @label Tree Depth-firstSearch Array
 * @author &#x8c2f;&#x535a;
 * @Date 2020.5.22
 * @version 1.00
 */

import Tree公共方法.PublicMethod;
import Tree公共方法.TreeNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ConstructBinaryTree {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] preorder = new PublicMethod().stringToIntegerArray(line);
            line = in.readLine();
            int[] inorder = new PublicMethod().stringToIntegerArray(line);
            TreeNode ret = new Solution().buildTree(preorder, inorder);
            String out = new PublicMethod().integerTreeToString(ret);
            System.out.println("树的层序遍历为：" + out);
        }
    }
}

class Solution {
    //DFS
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0)
            return null;
        TreeNode root = new TreeNode(preorder[0]);
        for (int i = 0; i < preorder.length; i++) {
            if (preorder[0] == inorder[i]) {
                root.left = buildTree(Arrays.copyOfRange(preorder, 1, i + 1), Arrays.copyOfRange(inorder, 0, i));
                root.right = buildTree(Arrays.copyOfRange(preorder, i + 1, preorder.length), Arrays.copyOfRange(inorder, i + 1, inorder.length));
            }
        }
        return root;
    }
}
