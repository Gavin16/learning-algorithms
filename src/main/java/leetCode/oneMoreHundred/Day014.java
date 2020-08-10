package leetCode.oneMoreHundred;

/**
 * @Date： 2020年8月10日
 * ==============================================================================
 * 96. 不同的二叉搜索树
 * ==============================================================================
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 *
 * 示例:
 *
 * 输入: 3
 * 输出: 5
 * 解释:
 * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 * ==============================================================================
 *
 */
public class Day014 {


    public static void main(String[] args) {
        System.out.println(numTrees(1));
        System.out.println(numTrees(2));
        System.out.println(numTrees(3));
        System.out.println(numTrees(4));
        System.out.println(numTrees(5));
    }

    /**
     * @Title: 96. 不同的二叉搜索树
     * @Version: 动态规划实现
     * 对于输入n得到的序列(1,2,3,4,...,n)考虑使用不同的元素作为二叉搜索树的根节点, 需要满足根节点左边的元素
     * 要小于根节点右边的元素,因此若选择 i(i >= 1) 作为根节点，那么左子树对应的元素为 1,2,..,i-1 共 i - 1 个
     * 相应的右子树的元素个数为 i+1,i+2,...,n 共 n-i 个;
     *
     * 考虑以上的规律,设 f(i) 为以i作为根节点时对应可以构建的搜索二叉树的个数,T(n) 代表 输入n 对应的不相同的二叉搜索树的个数，则
     * f(i) = T(i-1)*T(n-i)
     * 同时也有
     * T(n) = Σf(i) = ΣT(i-1)*T(n-i) (i = 1,2,3,..,n)
     * 因此对于输入n,其对应的不同二叉搜索树的个数满足以下 转移方程
     * T(n) = ΣT(i-1)*T(n-i) (i = 1,2,3,..,n)
     *
     * 对于n = 0 , 1 的情况，T(0) = 1, T(1) = 1
     *
     * @param n
     * @return
     */
    public static int numTrees(int n) {
        if(n <= 1){return 1;}
        int[] T = new int[n+1];
        T[0] = 1;T[1] = 1;

        for(int i = 2; i <= n ; i++){
            int curr = 0;
            for(int k = 1; k <= i ; k++){
                curr += T[k-1]*T[i-k];
            }
            T[i] = curr;
        }
        return T[n];
    }
}
