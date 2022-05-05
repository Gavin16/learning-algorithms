package leetcode;

/**
 * 有 n 个网络节点，标记为1到 n
 *
 * 给你一个列表times，表示信号经过 有向 边的传递时间。times[i] = (ui, vi, wi)，其中ui是源节点，vi是目标节点， wi是一个信号从源节点传递到目标节点的时间。
 * 现在，从某个节点K发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回-1 。
 *
 * 输入：times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
 * 输出：2
 * 示例 2：
 *
 * 输入：times = [[1,2,1]], n = 2, k = 1
 * 输出：1
 * 示例 3：
 *
 * 输入：times = [[1,2,1]], n = 2, k = 2
 * 输出：-1
 * 
 * 提示：
 *
 * 1 <= k <= n <= 100
 * 1 <= times.length <= 6000
 * times[i].length == 3
 * 1 <= ui, vi <= n
 * ui != vi
 * 0 <= wi <= 100
 * 所有 (ui, vi) 对都 互不相同（即，不含重复边）
 *
 */
public class NetworkDelayTime {

    int[] nodes;
    int allRecTime = 0;

    public static void main(String[] args) {

//        int[][] times = {{2,1,1},{2,3,1},{3,4,1}};
//
        NetworkDelayTime instance = new NetworkDelayTime();
//        int res = instance.networkDelayTime(times, 4, 2);
//        System.out.println(res);

        int[][] times = new int[][]{{1, 2, 1}};
        System.out.println(instance.networkDelayTime(times, 2, 2));
    }

    /**
     * 有向图，从指定节点进行深度优先搜索，统计所有访问过的节点
     *
     */
    public int networkDelayTime(int[][] times, int n, int k) {
        // 访问标记,默认为0,访问过后改为1
        nodes = new int[n];
        dfsNetwork(times, n, k);
        for(int i : nodes){
            if(i == 0){
                return -1;
            }
        }
        return allRecTime;
    }

    /**
     * 深度优先搜索网络,遍历网络所有节点
     */
    private void dfsNetwork(int[][] times, int n, int k){
        int currTime = 0;
        for(int i = 0 ; i < times.length; i++){
            if(times[i][0] == k && nodes[k-1] == 0){
                if(times[i][2] > currTime){
                    currTime = times[i][2];
                }
                dfsNetwork(times, n, times[i][1]);
            }
        }
        nodes[k-1] = 1;
        allRecTime += currTime;
    }

}
