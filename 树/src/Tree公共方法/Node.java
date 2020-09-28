package Tree公共方法;

/**
 * FileName: Node.java
 * 类的详细说明
 * javaBean
 * 包含三个节点，一个左节点，一个右节点，一个指向下一个右侧节点的指针
 */
public class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}
