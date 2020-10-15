package 填充每个节点的下一个右侧节点指针;

import Tree公共方法.Node;
import Tree公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * FileName: PopulatingNextRightPointersInEachNode.java
 * 类的详细说明
 * 给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。
 * 如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * 初始状态下，所有 next 指针都被设置为 NULL。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.10.15 - 下午 1:52
 * @label Tree Breadth-firstSearch Depth-firstSearch
 */
public class PopulatingNextRightPointersInEachNode {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个完全二叉树：[1,2,3,4,5,6,7]");
        while ((line = in.readLine()) != null) {
            Node root = new PublicMethod().stringToBinaryTreeNode(line, null);
            Node result = new SolutionCopy2().connect(root);
            System.out.println("这玩意自己调试着看答案吧。。。" + result);
        }
    }
}

/**
 * Breadth Iterator
 */
class Solution {
    //使用队列完成层次遍历
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        // 初始化队列同时将第一层节点加入队列中，即根节点
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        // 外层的 while 循环迭代的是层数
        while (!queue.isEmpty()) {
            // 记录当前队列大小
            int size = queue.size();
            // 遍历该层所有节点
            while (size-- > 0) {
                // 从队首取出节点
                Node node = queue.remove();
                // 连接
                if (size - 1 > 0) {
                    node.next = queue.peek();
                }
                // 拓展下一层节点
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        // 返回根节点
        return root;
    }
}

/**
 * Breadth Iterator
 */
class SolutionCopy {
    // 使用常数个空间完成层次遍历
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        // 从根节点开始
        Node leftMost = root;
        // 遍历每层
        while (leftMost.left != null) {
            // 遍历这一层节点组织成的链表，为下一层的节点更新 next 指针
            Node cur = leftMost;
            while (cur != null) {
                // 左子节点连接右子节点
                cur.left.next = cur.right;
                // 如果当前节点有 next节点，右子节点连接 next的左子节点
                if (cur.next != null) {
                    cur.right.next = cur.next.left;
                }
                // 指针向后移动
                cur = cur.next;
            }
            // 去下一层的最左的节点
            leftMost = leftMost.left;
        }
        return root;
    }
}

/**
 * Recursive，前序遍历，中序，后序没啥区别
 */
class SolutionCopy1 {
    public Node connect(Node root) {
        if (root == null || root.left == null || root.right == null) {
            return root;
        }
        // 左子节点连接右子节点
        root.left.next = root.right;
        // 如果当前节点有next节点，右子节点连接 next的左子节点
        if (root.next != null) {
            root.right.next = root.next.left;
        }
        connect(root.left);
        connect(root.right);
        return root;
    }
}

class SolutionCopy2 {
    public Node connect(Node root) {
        dfs(root, null);
        return root;
    }

    private void dfs(Node cur, Node next) {
        if (cur == null) {
            return;
        }
        cur.next = next;
        // 左子节点连接右子节点
        dfs(cur.left, cur.right);
        // 如果当前节点有next节点，右子节点连接 next的左子节点
        dfs(cur.right, cur.next == null ? null : cur.next.left);
    }
}