package leetcode.oneMoreHundred;

import java.util.*;

/**
 * 116. 填充每个节点的下一个右侧节点指针
 * 给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * 初始状态下，所有 next 指针都被设置为 NULL。
 *
 * 进阶：
 * 你只能使用常量级额外空间。
 * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 *
 *
 * 117. 填充每个节点的下一个右侧节点指针 II
 *
 * 给定一个二叉树
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * 初始状态下，所有 next 指针都被设置为 NULL。
 *
 * 进阶：
 * 你只能使用常量级额外空间。
 * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 *
 */
public class Day040 {

    static class Node {
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


    public static void main(String[] args) {
        Node n1 = new Node(4,null,null,null);
        Node n2 = new Node(5,null,null,null);
//        Node n3 = new Node(6,null,null,null);
        Node n4 = new Node(7,null,null,null);

        Node nn1 = new Node(2,n1,n2,null);
        Node nn2 = new Node(3,null,n4,null);

        Node root = new Node(1,nn1,nn2,null);

        // [2,1,3,0,7,9,1,2,null,1,0,null,null,8,8,null,null,null,null,7]


        Node node = connect3(root);
        System.out.println(node);
    }


    /**
     * 使用队列保存每一层节点
     *
     */
    public static Node connect1(Node root) {
        if(root == null) return null;
        Deque<Node> queue = new LinkedList<>();
        Node curr = root;
        queue.add(curr);
        List<Node> nodeList = new ArrayList<>();
        while(!queue.isEmpty()){
            nodeList.clear();
            while(!queue.isEmpty()){
                nodeList.add(queue.remove());
            }
            // 连接该层节点
            Node node = nodeList.get(0);
            for(int i = 1 ; i < nodeList.size() ; i++){
                node.next = nodeList.get(i);
                node = nodeList.get(i);
            }
            node.next = null;
            // 添加该层节点的子节点
            for(Node n : nodeList){
                if(n.left != null && n.right != null){
                    queue.add(n.left);
                    queue.add(n.right);
                }
            }
        }
        return root;
    }



    /**
     * 递归解法
     * 深度优先搜索
     */
    public static Node connect(Node root) {
        if(null == root) return root;
        Node res = dfsConnect(root);
        return res;
    }

    private static Node dfsConnect(Node root) {
        if(null == root) return root;
        if(root.left != null && root.right != null){
            root.left.next = root.right;
            if(root.next != null){
                root.right.next = root.next.left;
            }
        }
        dfsConnect(root.left);
        dfsConnect(root.right);
        return root;
    }


    /**
     * 117 对于任意二叉树 填充下一个右侧节点
     */
    public static Node connect2(Node root) {
        if(root == null) return root;
        Deque<Node> deque = new LinkedList<>();

        deque.add(root);
        while(!deque.isEmpty()){
            List<Node> levelNodes = new ArrayList<>();
            while(!deque.isEmpty()) levelNodes.add(deque.remove());

            if(levelNodes.size() == 0) continue;
            Node first = levelNodes.get(0);
            for(int i = 1 ; i < levelNodes.size(); i++){
                Node node = levelNodes.get(i);
                first.next = node;
                first = node;
            }
            first.next = null;

            for(Node node : levelNodes){
                if(null != node){
                    if(node.left != null) deque.add(node.left);
                    if(node.right != null) deque.add(node.right);
                }
            }
        }
        return root;
    }


    /**
     * 任意二叉树，设置同层级节点连接
     * 深度优先搜索
     */
    private static Node connect3(Node root) {
        if (root == null) return root;
        if(root.left != null){
            root.left.next = root.right != null ? root.right : getFirstChildInNext(root.next);
        }
        if(root.right != null){
            root.right.next = getFirstChildInNext(root.next);
        }
        connect3(root.right);
        connect3(root.left);
        return root;
    }

    /**
     * 获取 next 链表开始的第一个有效子节点，若无返回 null
     */
    private static Node getFirstChildInNext(Node next) {
        Node curr = next;
        while(curr != null){
            if(curr.left != null) return curr.left;
            else if(curr.right != null) return curr.right;
            else curr = curr.next;
        }
        return null;
    }
}
