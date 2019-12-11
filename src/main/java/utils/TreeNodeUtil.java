package utils;

import dataStruct.BSTNode;
import dataStruct.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * @ClassName: TreeNodeUtil
 * @Description: 二叉树测试工具类
 * @Author: wufangmin
 * @Date: 2019/12/5 12:12
 * @Version: 1.0
 */
public class TreeNodeUtil {


    public static void main(String[] args) {
        int[] arr = {4,3,5,2,7,6,8};
        BSTNode bstNode = genBinSearchTreeFromArray(arr);
        TreeNode max = findMaxValInBinSearchTree(bstNode);
        preOrderPrint(bstNode);
        System.out.println();
        TreeNode treeNode = deleteFromBinSearchTree(bstNode, 4);
        preOrderPrint(treeNode);
        System.out.println("------------");
        BFSPrint(treeNode);
    }



    /**
     * 产生指定元素个数的随机 BinTree
     */
    public static TreeNode genRandomBinTree(int n){
        if(n <= 0 ) return null;
        // 生成随机值数组,随机值范围 [0,2n-1)
        int[] randVals = ArrayUtil.randValueArray(n,2*n);
        TreeNode root = null, temp;
        Random random = new Random();
        for(int i = 0; i < n ; i++){
            //插入第一个元素
            if(root == null){
                root = new TreeNode(randVals[i]);
            }else{
                // root之后每一个元素插入的位置通过 random 随机产生
                temp = root;
                TreeNode node = null;
                while(node == null){
                    int sw = random.nextInt(2);
                    if(sw > 0){
                        if(temp.right == null){
                            node = new TreeNode(randVals[i]);
                            temp.right = node;
                        }else{
                            temp = temp.right;
                        }
                    }else{
                        if(temp.left == null){
                            node = new TreeNode(randVals[i]);
                            temp.left = node;
                        }else{
                            temp = temp.left;
                        }
                    }
                }
            }
        }
        return root;
    }


    /**
     *  随机生成制定长度的二叉搜索树
     */
    public static BSTNode genRandomBinSearchTree(int n){
        int[] randVals = ArrayUtil.randValueArray(n,2*n);
        return genBinSearchTreeFromArray(randVals);
    }


    /**
     * 从数组生成二叉搜索树: 读取数组所有元素生成二叉搜索树
     */
    public static BSTNode genBinSearchTreeFromArray(int[] arr){
        if(null == arr || arr.length == 0) return null;

        BSTNode root = new BSTNode(arr[0]);
        for(int i = 1; i < arr.length ; i++){
            insertBinSearchTree(root,arr[i]);
        }

        return root;
    }

    /**
     * 向二叉搜索树插入
     */
    public static void insertBinSearchTree(BSTNode root, int val){
        if(root == null) return ;

        TreeNode node = null, tmp = root;
        while(node == null){
            if(val > tmp.val){
                if(tmp.right == null){
                    node = new BSTNode(val);
                    tmp.right = node;
                }
                tmp = tmp.right;
            }else{
                if(tmp.left == null){
                    node = new BSTNode(val);
                    tmp.left = node;
                }
                tmp = tmp.left;
            }
        }
    }

    /**
     * 二叉搜索树中查询值为 val 的节点
     */
    public static TreeNode findInBinSearchTree(BSTNode root, int val){
        if(root == null) return null;
        TreeNode tmp = root;
        while(tmp != null){
            if(tmp.val == val) return tmp;
            else if(tmp.val > val){
                tmp = tmp.left;
            }else{
                tmp = tmp.right;
            }
        }
        return null;
    }

    /**
     * 找出最小节点
     */
    public static TreeNode findMinValInBinSearchTree(BSTNode root){
        TreeNode tmp = root;
        if(null != tmp){
            while(tmp.left != null){
                tmp = tmp.left;
            }
        }
        return tmp;
    }

    /**
     * 找出最大节点
     */
    public static TreeNode findMaxValInBinSearchTree(BSTNode root){
        TreeNode tmp = root;
        if(null != tmp){
            while(tmp.right != null){
                tmp = tmp.right;
            }
        }
        return tmp;
    }

    /**
     * 删除搜索树中val为给定值的节点
     */
    public static TreeNode deleteFromBinSearchTree(TreeNode root, int val){
        // 找出值为val的节点
        if(null == root) return null;
        TreeNode p = null, curr = root, pp = p;
        while(curr != null){
            if(curr.val > val){
                pp = curr;
                curr = curr.left;
            }else if(curr.val < val){
                pp = curr;
                curr = curr.right;
            }else{
                p = curr;
                break;
            }
        }
        if(p == null) return null; //未找到值为 null 的元素

        // 判断节点下子节点个数,若左右子节点都不为null则删除节点后用右子树最小节点替换当前节点,并删除右子树最小节点
        if(p.left != null && p.right != null){
            TreeNode rcp = p,rc = p.right;
            while(rc.left != null){
                rcp = rc;
                rc = rc.left;
            }
            p.val = rc.val;
            // 将删除目标节点转化为删除右子树最小节点
            pp = rcp;
            p = rc;
        }

        // 若左右子树有一个为null或者两个都为null
        TreeNode node;
        if(p.left != null){
            node = p.left;
        }else if (p.right != null){
            node = p.right;
        }else{
            node = null;
        }

        // 要删除的就是根结点，且不同时存在左子树或右子树
        if(pp == null){
            pp = node;
            return pp;
        } else if(pp.left == p){
            pp.left = node;
        }else{
            pp.right = node;
        }
        return root;
    }


    /**
     * 从数组生成完全二叉树
     */
    public static TreeNode genTreeNodeFromArray(int[] arr){
        int[] vals = offsetOnePosition(arr);
        return initTreeNode(vals,1);
    }

    /**
     *  递归生成二叉树
     */
    private static TreeNode initTreeNode(int[] vals, int i) {
        if(i >= vals.length) return null;

        TreeNode root = new TreeNode(vals[i]);

        if(2*i < vals.length){
            root.left = initTreeNode(vals,2*i);
        }
        if((2*i + 1) < vals.length){
            root.right = initTreeNode(vals,2*i + 1);
        }

        return root;
    }

    /**
     * 数组元素向后偏移一个槽位
     */
    private static int[] offsetOnePosition(int[] arr) {
        int[] res = new int[arr.length + 1];
        for(int i = 0 ; i < arr.length ; i++){
            res[i+1] = arr[i];
        }
        return res;
    }

    /** 前序遍历二叉树 */
    public static void preOrderPrint(TreeNode root){
        if(root == null) return;
        print(root);
        if(root.left != null){
            preOrderPrint(root.left);
        }
        if(root.right != null){
            preOrderPrint(root.right);
        }
    }
    /** 中序遍历二叉树 */
    public static void midOrderPrint(TreeNode root){
        if(root == null) return ;

        if(root.left != null){
            midOrderPrint(root.left);
        }
        print(root);
        if(root.right != null){
            midOrderPrint(root.right);
        }
    }
    /** 后序遍历二叉树 */
    public static void backOrderPrint(TreeNode root){
        if(root == null) return;

        if(root.left != null){
            backOrderPrint(root.left);
        }
        if(root.right != null){
            backOrderPrint(root.right);
        }
        print(root);
    }

    /**
     * 树的广度优先遍历
     * 遍历方式：从根节点开始逐层遍历
     */
    public static void BFSPrint(TreeNode root){
        if(null == root) return ;
        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);
        TreeNode current;
        while(!queue.isEmpty()){
            current = queue.poll();
            print(current);

            if(null != current.left)
                queue.offer(current.left);
            if(null != current.right)
                queue.offer(current.right);
        }
    }

    /**
     *  为便于分析
     *  打印输出二叉树图形
     *  形如：
     *         A
     *        / \
     *       B   C
     *      /\   /\
     *     D  E F  G
     *
     * 循环遍历实现
     */
    public static void printTreeGraph(TreeNode root){

    }

    private static void print(TreeNode node){
        System.out.print(node.val + " ");
    }
}
