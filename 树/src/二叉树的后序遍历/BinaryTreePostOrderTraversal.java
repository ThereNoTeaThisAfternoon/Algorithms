package 二叉树的后序遍历;

import Tree公共方法.PublicMethod;
import Tree公共方法.TreeNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * FileName: BinaryTreePostOrderTraversal.java
 * 类的详细说明
 * 二叉树的后序遍历
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.9.29 - 下午 2:27
 * @label Tree Depth-firstSearch Iteration
 */
public class BinaryTreePostOrderTraversal {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个二叉树：[1,null,2,3]");
        while ((line = in.readLine()) != null) {
            TreeNode root = new PublicMethod().stringToBinaryTreeNode(line);
            List<Integer> result = new SolutionCopy2().postOrderTraversal(root);
            String string = new PublicMethod().integerArrayListToString(result, result.size());
            System.out.println("二叉树的后序遍历为：" + string);
        }
    }
}

class Solution {
    public List<Integer> postOrderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        dfs(root, list);
        return list;
    }

    private void dfs(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        dfs(root.left, list);//向左递归
        dfs(root.right, list);//向右递归
        list.add(root.val);
    }
}

class SolutionCopy1 {

    public List<Integer> postOrderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        List<Integer> left = postOrderTraversal(root.left);
        List<Integer> right = postOrderTraversal(root.right);
        list.addAll(left);
        list.addAll(right);
        list.add(root.val);
        return list;
    }
}

/**
 * 迭代遍历，使用栈代替虚拟机栈完成递归功能
 */
class SolutionCopy2 {

    public List<Integer> postOrderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root, p = null;

        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {//向左
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.peek();//获取当前栈顶节点
                if (cur.right == null || cur.right == p) {
                    list.add(cur.val);
                    stack.pop();
                    p = cur;
                    cur = null;
                } else {//向右
                    cur = cur.right;
                }
            }
        }
        return list;
    }
}

/**
 * 迭代，从右往左进行，依次次向队列头部添加
 */
class SolutionCopy3 {
    public List<Integer> postOrderTraversal(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> ans = new LinkedList<>();
        if (null == root) return ans;
        stack.addFirst(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.removeFirst();
            ans.addFirst(node.val);
            if (null != node.left) {
                stack.addFirst(node.left);
            }
            if (null != node.right) {
                stack.addFirst(node.right);
            }
        }
        return ans;
    }
}