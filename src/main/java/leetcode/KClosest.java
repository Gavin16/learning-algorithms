package leetcode;

import java.util.*;

/**
 * @Class: KClosest
 * @Description:
 *
 *  <<LeetCode 973 -- 最接近原点的 K 个点 >>
 *
 * 我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。
 *
 * （这里，平面上两点之间的距离是欧几里德距离。）
 *
 * 你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：points = [[1,3],[-2,2]], K = 1
 * 输出：[[-2,2]]
 * 解释：
 * (1, 3) 和原点之间的距离为 sqrt(10)，
 * (-2, 2) 和原点之间的距离为 sqrt(8)，
 * 由于 sqrt(8) < sqrt(10)，(-2, 2) 离原点更近。
 * 我们只需要距离原点最近的 K = 1 个点，所以答案就是 [[-2,2]]。
 *
 *
 * @Author: Minsky
 * @Date: 2019/7/31 19:39
 * @Version: v1.0
 */
public class KClosest {

    public static void main(String[]args){
        int[][] arr = new int[][]{{3,3},{5,-1},{-2,4}};
        int[][] res = kClosest(arr, 2);
    }

    /**
     * 方法一： 使用优先队列实现
     * @param points
     * @param K
     * @return
     */
    public static int[][] kClosest(int[][] points, int K) {

        if(points.length < K) return null;

        // 这里注意int compare(T o1,T o2) 方法的使用
        // 当compare结果为-1时, 默认会将o2 放在 o1后面，这样如果想实现o1,o2的升序排序只需要 返回 val(o1) - val(o2)
        // 反之实现的是  o1,o2的降序排序
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>((p1,p2)->{
            return -(p1[0]*p1[0] + p1[1]*p1[1] - p2[0]*p2[0] - p2[1]*p2[1]);
        });

        // 优先队列装入传参中的前K个元素, 此时队列相当于是一个大顶堆
        for(int i = 0 ; i < K ; i++){
            queue.offer(points[i]);
        }

        // points 中剩下的元素与 优先队列的堆顶比较,若比堆顶小则该元素与堆顶 互换
        for(int j = K ; j < points.length ; j++){
            int[] root = queue.peek();
            int[] ele = points[j];
            // 若元素比堆顶小,则移除堆顶,并将该元素加入堆
            if(root[0]*root[0] + root[1]*root[1] > ele[0]*ele[0] + ele[1]*ele[1]){
                queue.poll();
                queue.offer(ele);
            }
        }

        int[][] res = new int[K][2];
        for(int k = 0 ; k < K ; k++){
            res[k] = queue.poll();
        }

        return res;
    }

    /**
     * 对二维数组升序排序，返回前k个元素
     */
    public static int[][] kClosest1(int[][] points, int K) {
        List<int[]> list = Arrays.asList(points);
        Collections.sort(list, Comparator.comparingInt(o -> (o[0] * o[0] + o[1] * o[1])));

        int[][] res = new int[K][2];
        for(int j = 0 ; j < K ; j++){
            res[j] = list.get(j);;
        }
        return res;
    }

}
