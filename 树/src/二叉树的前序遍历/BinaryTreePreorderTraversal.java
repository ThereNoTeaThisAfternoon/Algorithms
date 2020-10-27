package 二叉树的前序遍历;

import Tree公共方法.PublicMethod;
import Tree公共方法.TreeNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * FileName: BinaryTreePreorderTraversal.java
 * 类的详细说明
 * Given the root of a binary tree, return the preorder traversal of its nodes' values.
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.10.27 - 下午 9:58
 * @label Tree Depth-firstSearch Stack Morris
 */
public class BinaryTreePreorderTraversal {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一颗二叉树：[4,2,5,1,3]");
        while ((line = in.readLine()) != null) {
            TreeNode root = new PublicMethod().stringToBinaryTreeNode(line);
            List<Integer> result = new SolutionCopy().preorderTraversal(root);
            for (Integer i : result) {
                System.out.print(i + "\t");
            }
        }
    }
}

/**
 * Depth-firstSearch
 */
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, res);
        return res;
    }

    private void dfs(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        dfs(root.left, res);
        dfs(root.right, res);
    }
}

/**
 * Iterator
 */
class SolutionCopy {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.offer(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            res.add(node.val);
            if (node.right != null) {
                stack.offer(node.right);
            }
            if (node.left != null) {
                stack.offer(node.left);
            }
        }
        return res;
    }
}

/**
 * 利用树的大量空闲指针，实现空间开销的极限缩减
 *
 * 新建临时节点，令该节点为 root；
 *
 * 如果当前节点的左子节点为空，将当前节点加入答案，并遍历当前节点的右子节点；
 *
 * 如果当前节点的左子节点不为空，在当前节点的左子树中找到当前节点在中序遍历下的前驱节点：
 *
 * 如果前驱节点的右子节点为空，将前驱节点的右子节点设置为当前节点。然后将当前节点加入答案，并将前驱节点的右子节点更新为当前节点。
 *
 * 如果前驱节点的右子节点为当前节点，将它的右子节点重新设为空。当前节点更新为当前节点的右子节点。
 *
 * 重复步骤 2 和步骤 3，直到遍历结束。
 */
class SolutionCopy2 {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        TreeNode p1 = root, p2;

        while (p1 != null) {
            p2 = p1.left;
            if (p2 != null) {
                while (p2.right != null && p2.right != p1) {
                    p2 = p2.right;
                }
                if (p2.right == null) {
                    res.add(p1.val);
                    p2.right = p1;
                    p1 = p1.left;
                    continue;
                } else {
                    p2.right = null;
                }
            } else {
                res.add(p1.val);
            }
            p1 = p1.right;
        }
        return res;
    }
}
