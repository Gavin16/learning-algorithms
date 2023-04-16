package algorithmsContest.charpt2.practice.submit;

import org.apache.commons.compress.utils.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @className: P1462
 * @description:
 * 通往奥格瑞玛的道路
 * 问题描述:
 * 给定无向图,包含n个点, m条双向边，每个点有点权fi, 每条边有边权ci。求起点1到终点N的所有
 * 可能路径中，在总边权不超过给定的b的前提下，所经过的路径中最大点权(这条路径上点权最大的那个点)的最小值。
 *
 * 输入：第1行输入3个整数n,m,b; 后面n行中，每行输入1个整数fi; 再后面m行中，每行输入3个整数 ai,bi,ci 表示ai 和 bi
 * 之间有一条边，从ai到bi或从bi 到ai,会损失ci的边权。1 < ai, bi < n.
 *
 * 输出: 一个整数，表示答案
 * 数据范围: n <= 10000, m <= 50000, fi,ci,b <= 10^9.
 *
 * 本题将二分法和最短路径算法结合
 * 对点权fi进行二分, 用Dijkstra 算法求最短路径，检验总边权是否小于b
 * (1) 对点权二分, 题目的要求是: 从1到N有很多路径, 其中的一条可行路径Pi, 它有一个点的点权最大，记为Fi;
 *     在所有可行路径中，找到那条有最小的F的路径，输出F.
 * 解题方案是先对所有点的fi 排序，然后用二分法，找符合要求的最小的fi。二分次数 log2(fi) = log2(10^9) < 30
 *
 * (2) 检查某个fi时，删除所有点权大于fi的点，在剩下的点中，求1到N的最短路径， 看总边权是否小于b ,如果满足，这个fi
 * 是合适的(如果最短路径的边权都大于b,那么其它路径的总边权就更大,肯定不符合要求)。 一次Dijkstra 算法求最短路径
 * 的复杂度为O(m*log2(n))
 *
 *
 *
 * @version: 1.0
 * @author: minsky
 * @date: 2023/3/17
 */
public class P1462 {

    public static void main(String[] args) {
        // TODO Dijkstra算法之后补充
    }

    /**
     * 用邻接矩阵表示图, 矩阵中对应(i,j)位置的值为边权
     * nws 数组存储了图中节点的点权值
     * @param graph
     * @param nws
     * @param startPos
     * @return 最短路径(边权和最小)所经过的点
     */
    public Integer dijkstraSearchMaxF(int[][] graph, int[]nws, int startPos){
        int len = graph.length;
        int[] result = new int[len];
        for(int i=0; i < len; i++){
            result[i] = -1;
        }
        result[startPos] = 0;

        int[] notFound = new int[len];
        for(int k = 0; k < len; k++){
            notFound[k] = graph[startPos][k];
        }
        // 初始位置无需搜索,因此记为 -1
        notFound[startPos] = -1;
        List<Integer> pathNodes = Lists.newArrayList();
        pathNodes.add(0);
        for(int i=0; i < len; i++){
            int min = Integer.MAX_VALUE;
            int minId = 0;
            // 找出notFound 中距离最小的
            for(int j = 0; j < len; j++){
                if(notFound[j] > 0 && notFound[j] < min){
                    min = notFound[j];
                    minId = j;
                }
            }
            result[minId] = min;
            notFound[minId] = -1;
            // 通过notFound中距离最小的节点
            // 判断是否满足边权和小于原有值
            // 若是则更新notFound中其它的节点的距离(边权和)
            for(int k = 0; k < len; k++){
                if(graph[minId][k] > 0 && result[k] == -1){
                    int newDist = result[minId] + graph[minId][k];
                    if(newDist < notFound[k] || notFound[k] == -1){
                        notFound[k] = newDist;
                        if(k == len-1){
                            pathNodes.add(minId);
                        }
                    }
                }
            }
        }
        pathNodes.add(len-1);
        // 遍历最短路径，求出点权和
        int maxF = Integer.MIN_VALUE;
        for(Integer id : pathNodes){
            if(nws[id] > maxF) maxF = nws[id];
        }
        return maxF;
    }


}
