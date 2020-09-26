package 从中序与后序遍历序列构造二叉树;

import Tree公共方法.PublicMethod;
import Tree公共方法.TreeNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * FileName: ConstructBinaryTreeFromInorderAndPostOrderTraversal.java
 * 类的详细说明
 * Given inorder and postOrder traversal of a tree , construct the bianry tree
 * 注意: 你可以假设树中没有重复的元素。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.9.26 - 上午 9:06
 * @label Tree Depth-firstSearch Array
 */
public class ConstructBinaryTreeFromInorderAndPostOrderTraversal {

    private static PublicMethod method = new PublicMethod();

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入inorder = [9,3,15,20,7]");
        while ((line = in.readLine()) != null) {
            int[] inorder = method.stringToIntegerArray(line);
            System.out.println("请输入postOrder = [9,15,7,20,3]");
            line = in.readLine();
            int[] postOrder = method.stringToIntegerArray(line);
            TreeNode result = new Solution().buildTree(inorder, postOrder);
            String string = method.integerTreeToString(result);
            System.out.println("树的层序遍历为：" + string);
        }
    }
}

class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return helper(inorder, postorder, postorder.length - 1, 0, inorder.length - 1);
    }

    public TreeNode helper(int[] inorder, int[] postorder, int postEnd, int inStart, int inEnd) {
        if (inStart > inEnd) {
            return null;
        }

        int currentVal = postorder[postEnd];
        TreeNode current = new TreeNode(currentVal);

        int inIndex = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == currentVal) {
                inIndex = i;
            }
        }
        TreeNode left = helper(inorder, postorder, postEnd - (inEnd - inIndex) - 1, inStart, inIndex - 1);
        TreeNode right = helper(inorder, postorder, postEnd - 1, inIndex + 1, inEnd);
        current.left = left;
        current.right = right;
        return current;
    }
}