package 验证二叉搜索树;
/**
 * FileName: ValidateBinarySearchTree.java
 * 类的详细说明
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * 假设一个二叉搜索树具有如下特征：
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 *
 * @label Tree DepthFirstSearch
 * @author &#x8c2f;&#x535a;
 * @Date 2020.5.5
 * @version 1.00
 */

import Tree公共方法.PublicMethod;
import Tree公共方法.TreeNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class ValidateBinarySearchTree {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            TreeNode root = new PublicMethod().stringToBinaryTreeNode(line);
            boolean ans = new SolutionCopy1().isValidBST(root);
            String out = Boolean.toString(ans);
            System.out.println(out);
        }
    }
}

class Solution {
    /**
     * 如果该二叉树的左子树不为空，则左子树上所有节点的值均小于它的根节点的值；
     * 若它的右子树不空，则右子树上所有节点的值均大于它的根节点的值；它的左右子树也为二叉搜索树。
     */
    //DFS
    public boolean isValidBST(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean validate(TreeNode node, long min, long max) {
        if (node == null)
            return true;
        if (node.val <= min || node.val >= max)
            return false;
        return validate(node.left, min, node.val) && validate(node.right, node.val, max);
    }
}

class SolutionCopy1 {
    //中序遍历遍历为升序
    public boolean isValidBST(TreeNode root) {
        Deque<TreeNode> deque = new ArrayDeque<>();
        double pre = -Double.MAX_VALUE;

        while (!deque.isEmpty() || root != null) {
            while (root != null) {
                deque.push(root);
                root = root.left;
            }
            root = deque.pop();
            //如果中序遍历得到的节点值小于等于前一个pre，说明不是二叉搜索树
            if (root.val <= pre) return false;
            pre = root.val;
            root = root.right;
        }
        return true;
    }
}

class SolutionCopy2 {
    LinkedList<TreeNode> stack = new LinkedList<>();
    LinkedList<Integer> lowers = new LinkedList<>();
    LinkedList<Integer> uppers = new LinkedList<>();

    //广度优先遍历，迭代
    public boolean isValidBST(TreeNode root) {
        Integer lower = 0, upper = 0, val;
        update(root, lower, upper);

        while (!stack.isEmpty()) {
            root = stack.poll();
            lower = lowers.poll();
            upper = uppers.poll();
            if (root == null) continue;
            val = root.val;
            if (lower != null && val <= lower) return false;
            if (upper != null && val >= upper) return false;
            update(root.right, val, upper);
            update(root.left, lower, val);
        }
        return true;
    }

    //将当前节点和对应的上下界存入对应的队列中
    private void update(TreeNode node, Integer lower, Integer upper) {
        stack.add(node);
        lowers.add(lower);
        uppers.add(upper);
    }
}