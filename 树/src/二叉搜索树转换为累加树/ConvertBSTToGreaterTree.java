package 二叉搜索树转换为累加树;

import Tree公共方法.PublicMethod;
import Tree公共方法.TreeNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * FileName: ConvertBSTToGreaterTree.java
 * 类的详细说明
 * 给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，
 * 使得每个节点的值是原来的节点值加上所有大于它的节点值之和
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.9.21 - 下午 8:51
 * @label Tree
 */
public class ConvertBSTToGreaterTree {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一颗BST树：[2,1,3]");
        while ((line = in.readLine()) != null) {
            TreeNode root = new PublicMethod().stringToBinaryTreeNode(line);
            TreeNode result = new Solution().convertBST(root);
            String string = new PublicMethod().integerTreeString(root);
            System.out.println(string);
        }
    }
}

/**
 * 反序中序遍历该二叉搜索树，记录过程中的节点值之和，
 * 并不断更新当前遍历到的节点的节点值，即可得到题目要求的累加树。
 */
class Solution {
    int num;

    public TreeNode convertBST(TreeNode root) {
        if (root != null) {
            //遍历所有右树
            convertBST(root.right);
            //处理节点
            root.val = root.val + num;
            num = root.val;
            //遍历所有左树
            convertBST(root.left);
        }
        return root;
    }
}

/**
 * Morris 遍历的核心思想是利用树的大量空闲指针，实现空间开销的极限缩减。其反序中序遍历规则总结如下：
 * 1、如果当前节点的右子节点为空，处理当前节点，并遍历当前节点的左子节点；
 * 2、如果当前节点的右子节点不为空，找到当前节点右子树的最左节点（该节点为当前节点中序遍历的前驱节点）；
 * * * 如果最左节点的左指针为空，将最左节点的左指针指向当前节点，遍历当前节点的右子节点；
 * * * 如果最左节点的左指针不为空，将最左节点的左指针重新置为空（恢复树的原状），处理当前节点，并将当前节点置为其左节点；
 * 重复步骤 1 和步骤 2，直到遍历结束。
 */
class SolutionCopy {
    public TreeNode convertBST(TreeNode root) {
        int sum = 0;
        TreeNode node = root;

        while (node != null) {
            if (node.right == null) {
                sum += node.val;
                node.val = sum;
                node = node.left;
            } else {
                TreeNode succ = getSuccessor(node);
                if (succ.left == null) {
                    succ.left = node;
                    node = node.right;
                } else {
                    succ.left = null;
                    sum += node.val;
                    node.val = sum;
                    node = node.left;
                }
            }
        }

        return root;
    }

    public TreeNode getSuccessor(TreeNode node) {
        TreeNode succ = node.right;
        while (succ.left != null && succ.left != node) {
            succ = succ.left;
        }
        return succ;
    }
}

