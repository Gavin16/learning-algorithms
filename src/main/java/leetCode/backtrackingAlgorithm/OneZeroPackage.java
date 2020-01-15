package leetCode.backtrackingAlgorithm;

/**
 *  0-1 背包问题（回溯解法）
 *
 *  回溯算法的基本思想是: 在深度优先搜索(递归)的每一个关口(即做或不做)的地方分出两个分支做递归调用，
 *  那么在递归的"归"的过程可以经历所有的做或不做的组合,每个组合中对要解决的问题做判断，最后拿到的就是问题的解
 */
public class OneZeroPackage {


    private static int maxW = Integer.MIN_VALUE;

    /**
     * @param id 当前到装到物品的位置
     * @param w  当前背包重量
     * @param items 所有物品列表
     * @param cap  背包的容量
     * @param n    可装包物品总数量
     */
    private static void addPack(int id, int w, int[] items ,int cap, int n){
        if(w == cap || id == n){

            return;
        }


    }
}
