package leetcode;


import java.util.ArrayList;
import java.util.List;

/**
 *
 * 《207. 课程表》
 *
 * 你这个学期必须选修 numCourses 门课程，记为0到numCourses - 1 。
 *
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组prerequisites 给出，其中prerequisites[i] = [ai, bi] ，表示如果要学习课程ai 则 必须 先学习课程 bi 。
 *
 * 例如，先修课程对[0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 *
 * 
 *
 * 示例 1：
 *
 * 输入：numCourses = 2, prerequisites = [[1,0]]
 * 输出：true
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
 * 示例 2：
 *
 * 输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
 * 输出：false
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要先完成课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
 * 
 *
 * 提示：
 *
 * 1 <= numCourses <= 105
 * 0 <= prerequisites.length <= 5000
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * prerequisites[i] 中的所有课程对 互不相同
 *
 */
public class CanFinish {

    List<List<Integer>> edges;
    int[] visited;
    boolean valid = true;


    public static void main(String[] args) {
        CanFinish canFinish = new CanFinish();
        int[][] courses = {{0,1},{0,2},{2,3},{3,4}};
        System.out.println(canFinish.canFinish(5, courses));
    }


    /**
     *  将课程视为图节点，课程间的依赖关系视为图的边，可以构造出课程间的有向图
     *  只要有向图是有向无环图，那么课程就一定能够被修完: 题设隐含了要修完所有课程的要求
     *
     *  采用深度优先搜索，若发现深度优先搜索路径上出现重复节点，则判断图中存在有相环
     *
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 构造并初始化邻接表
        edges = new ArrayList<>();
        for(int i = 0 ; i < numCourses ; i++){
            edges.add(new ArrayList<>());
        }
        visited = new int[numCourses];
        for(int[] pair : prerequisites){
            edges.get(pair[1]).add(pair[0]);
        }
        // 深度优先搜索
        for(int i = 0 ; i < numCourses && valid ; i++){
            if(visited[i] == 0){
                dfs(i);
            }
        }
        return valid;
    }

    /**
     * 深度优先搜索，判断邻接表中各节点是否出现了环
     *
     * 若出现环则判断无法修完所有课程
     */
    public void dfs(int u) {
        visited[u] = 1;
        for(int v : edges.get(u)){
            if(visited[v] == 0){
                dfs(v);
                if(!valid){
                    return;
                }
            }else if(visited[v] == 1){
                valid = false;
                return;
            }
        }
        visited[u] = 2;
    }

}
