package algorithmsContest.charpt3;

import utils.ArrayUtil;

import java.util.*;

/**
 * @className: DFSBasic
 * @description:
 *
 * 练习题
 * LeetCode 210
 * LeetCode 133
 *
 *
 *
 *
 *
 *
 *
 * ------------------------------------------------------------------------------------------------------------
 * 210. 课程表 II
 *
 * 现在你总共有 numCourses 门课需要选，记为 0 到 numCourses - 1。给你一个数组 prerequisites ，
 * 其中 prerequisites[i] = [ai, bi] ，表示在选修课程 ai 前 必须 先选修 bi 。
 *
 * 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示：[0,1] 。
 * 返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 任意一种 就可以了。如果不可能完成所有课程，返回 一个空数组 。
 *
 * 示例 1：
 * 输入：numCourses = 2, prerequisites = [[1,0]]
 * 输出：[0,1]
 * 解释：总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
 * 示例 2：
 *
 * 输入：numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 * 输出：[0,2,1,3]
 * 解释：总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
 * 因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。
 * 示例 3：
 *
 * 输入：numCourses = 1, prerequisites = []
 * 输出：[0]
 *
 * 提示：
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= numCourses * (numCourses - 1)
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * ai != bi
 * 所有[ai, bi] 互不相同
 * ------------------------------------------------------------------------------------------------------------
 * 133. 克隆图
 *
 * 给你无向 连通 图中一个节点的引用，请你返回该图的 深拷贝（克隆）。
 * 图中的每个节点都包含它的值 val（int） 和其邻居的列表（list[Node]）。
 *
 * class Node {
 *     public int val;
 *     public List<Node> neighbors;
 * }
 *  
 * 测试用例格式：
 * 简单起见，每个节点的值都和它的索引相同。
 * 例如，第一个节点值为 1（val = 1），第二个节点值为 2（val = 2），以此类推。该图在测试用例中使用邻接列表表示。
 * 邻接列表 是用于表示有限图的无序列表的集合。每个列表都描述了图中节点的邻居集。
 * 给定节点将始终是图中的第一个节点（值为 1）。你必须将 给定节点的拷贝 作为对克隆图的引用返回。
 *
 *
 * 提示：
 * 节点数不超过 100 。
 * 每个节点值 Node.val 都是唯一的，1 <= Node.val <= 100。
 * 无向图是一个简单图，这意味着图中没有重复的边，也没有自环。
 * 由于图是无向的，如果节点 p 是节点 q 的邻居，那么节点 q 也必须是节点 p 的邻居。
 * 图是连通图，你可以从给定节点访问到所有节点。
 * ------------------------------------------------------------------------------------------------------------
 * 127. 单词接龙
 * 字典 wordList 中从单词 beginWord 和 endWord 的 转换序列 是一个按下述规格形成的序列 beginWord -> s1 -> s2 -> ... -> sk：
 *
 * 每一对相邻的单词只差一个字母。
 * 对于 1 <= i <= k 时，每个 si 都在 wordList 中。注意， beginWord 不需要在 wordList 中。
 * sk == endWord
 * 给你两个单词 beginWord 和 endWord 和一个字典 wordList ，返回 从 beginWord 到 endWord 的 最短转换序列 中的 单词数目 。如果不存在这样的转换序列，返回 0 。
 *  
 * 示例 1：
 *
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * 输出：5
 * 解释：一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。
 * 示例 2：
 *
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * 输出：0
 * 解释：endWord "cog" 不在字典中，所以无法进行转换。
 *  
 *
 * 提示：
 * 1 <= beginWord.length <= 10
 * endWord.length == beginWord.length
 * 1 <= wordList.length <= 5000
 * wordList[i].length == beginWord.length
 * beginWord、endWord 和 wordList[i] 由小写英文字母组成
 * beginWord != endWord
 * wordList 中的所有字符串 互不相同
 *
 * ------------------------------------------------------------------------------------------------------------
 * 51. N 皇后
 *
 * 按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 *
 * 输入：n = 4
 * 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * 解释：如上图所示，4 皇后问题存在两个不同的解法。
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：[["Q"]]
 *
 * 提示：
 *
 * 1 <= n <= 9
 * ------------------------------------------------------------------------------------------------------------
 *
 * @version: 1.0
 * @author: minsky
 * @date: 2023/4/9
 */
public class DFS_BFS_Basic {

    public static void main(String[] args) {
        DFS_BFS_Basic instance = new DFS_BFS_Basic();
        // 210 课程表II
        int numCourse = 7;
        int[][] prerequisites = {{4,0},{5,0},{0, 1},{2,1},{2,0},{6,5},{5,3},{4,5}};

        int[] dfsRes = instance.findOrder1(numCourse, prerequisites);
        ArrayUtil.showArray(dfsRes);

//        int[] bfsRes = instance.findOrder2(numCourse, prerequisites);
//        ArrayUtil.showArray(bfsRes);


        // 133 克隆图
        Node node = instance.buildGraphNode();
        Node cloneNode = instance.cloneGraph(node);

        // 127 单词接龙
        String beginWord = "hit", endWord = "cog";
        List<String> wordList = new ArrayList<>();
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("dog");
        wordList.add("lot");
        wordList.add("log");
        wordList.add("cog");

        int steps = instance.ladderLength(beginWord, endWord, wordList);
        System.out.println(steps);

        // 51 N皇后问题
        List<List<String>> lists = instance.solveNQueens(1);
        for(List<String> list : lists){
            System.out.println(list);
        }
    }

    private Node buildGraphNode() {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        List<Node> n1 = new ArrayList<>();
        List<Node> n2 = new ArrayList<>();
        List<Node> n3 = new ArrayList<>();
        List<Node> n4 = new ArrayList<>();

        n1.add(node2);
        n1.add(node4);
        node1.neighbors = n1;

        n2.add(node1);
        n2.add(node3);
        node2.neighbors = n2;

        n3.add(node2);
        n3.add(node4);
        node3.neighbors = n3;

        n4.add(node1);
        n4.add(node3);
        node4.neighbors = n4;

        return node1;
    }


    // DFS实现
    // 若图中存在环,则结束搜索
    private boolean valid = true;
    // visit 记录已经遍历过的点
    private byte[] visit;
    private int[] result;
    int index;

    public int[] findOrder1(int numCourses, int[][] prerequisites) {
        if(numCourses <= 0) return new int[0];
        result = new int[numCourses];
        if(prerequisites.length == 0){
            for(int i = 0; i < numCourses; i++){
                result[i] = i;
            }
            return result;
        }
        visit = new byte[numCourses];
        result = new int[numCourses];
        index = numCourses - 1;
        // adjList[i] 代表依赖课程i的所有课程的集合
        HashSet<Integer> [] adjList = new HashSet[numCourses];
        for(int i = 0; i< numCourses; i++){
            adjList[i] = new HashSet<>();
        }
        // 将所有被依赖的课程加入adjList
        for(int[] pre: prerequisites){
            adjList[pre[1]].add(pre[0]);
        }
        for(int j = 0; j < numCourses; j++){
            if(visit[j] == 0){
                dfs(j, adjList);
            }
        }
        if(!valid){ return new int[0]; }
        return result;
    }

    private void dfs(int i,HashSet<Integer> [] adjList){
        visit[i] = 1;
        for(int k : adjList[i]){
            // 继续深度搜索依赖课程k 的课程
            if(visit[k] == 0){
                dfs(k, adjList);
                if(!valid){ return; }
            } else if(visit[k] == 1){
                valid = false;
                return;
            }
        }
        visit[i] = 2;
        result[index--] = i;
    }

    /**
     * BFS实现
     */
    public int[] findOrder2(int numCourses, int[][] prerequisites) {
        if(numCourses <= 0) return new int[0];
        // 使用邻接表表示有向图
        HashSet<Integer>[] adj = new HashSet[numCourses];
        for(int i = 0; i < numCourses; i++){
            adj[i] = new HashSet<>();
        }
        int[] inDegree = new int[numCourses];
        // [1, 0] 表示为 0 -> 1; 意思图中存在节点0 指向节点1的边
        for(int[] p : prerequisites){
            adj[p[1]].add(p[0]);
            inDegree[p[0]]++;
        }
        // 入度为0的节点可以作为初始节点
        Queue<Integer> queue = new LinkedList<>();
        for(int j = 0; j < numCourses; j++){
            if(inDegree[j] == 0) queue.offer(j);
        }
        int[] res = new int[numCourses];
        int count = 0;
        while(!queue.isEmpty()){
            Integer poll = queue.poll();
            res[count] = poll;
            count++;
            HashSet<Integer> successors = adj[poll];
            for(Integer nextCourse : successors){
                // 若有向图中存在环,则进入环的入口时,inDegree[]将不为0
                inDegree[nextCourse]--;
                if(inDegree[nextCourse] == 0){
                    queue.offer(nextCourse);
                }
            }
        }
        if(count == numCourses){
            return res;
        }
        return new int[0];
    }


    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }


     Map<Integer, Node> memo;
    /**
     * 克隆图
     * @param node
     * @return
     */
    public Node cloneGraph(Node node) {
        if(null == node) return null;
        else if(node.neighbors.size() == 0){
            return new Node(node.val);
        }
        memo = new HashMap<>();
        return dfsClone(node);
    }

    /**
     *
     * @param node
     * @return
     */
    public Node dfsClone(Node node){
        Node ans = new Node(node.val);
        memo.put(node.val, ans);
        List<Node> neighbors = node.neighbors;
        for(Node neighbor : neighbors){
            if(!memo.containsKey(neighbor.val)){
                ans.neighbors.add(dfsClone(neighbor));
            }else{
                ans.neighbors.add(memo.get(neighbor.val));
            }
        }
        return ans;
    }


    /**
     * 127.单词接龙
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        if(!set.contains(endWord)){ return 0; }
        // 构建图邻接表
        Map<String,List<String>> adjList = new HashMap<>();
        for(int i = 0; i < wordList.size(); i++){
            boolean adjacent = isAdjacent(beginWord, wordList.get(i));
            if(adjacent){
                List<String> beginList = adjList.getOrDefault(beginWord, new ArrayList<>());
                beginList.add(wordList.get(i));
                adjList.put(beginWord, beginList);
            }
            for(int j = i + 1; j < wordList.size(); j++){
                String first = wordList.get(i);
                String second = wordList.get(j);
                if(isAdjacent(first, second)){
                    List<String> l1 = adjList.getOrDefault(first, new ArrayList<>());
                    l1.add(second);
                    adjList.put(first, l1);
                    List<String> l2 = adjList.getOrDefault(second, new ArrayList<>());
                    l2.add(first);
                    adjList.put(second, l2);
                }
            }
        }
        // 遍历搜索邻接表
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        int count = 1;
        while(!queue.isEmpty()){
            // 取出当前所有临近单词
            int currSize = queue.size();
            List<String> nextLayer = new ArrayList<>();
            for(int i = 0; i < currSize; i++){
                String poll = queue.poll();
                set.remove(poll);

                if(poll.equals(endWord)) return count;
                List<String> list = adjList.getOrDefault(poll, new ArrayList<>());
                nextLayer.addAll(list);
            }

            for(String next : nextLayer){
                if(set.contains(next)){
                    queue.offer(next);
                }
            }
            count++;
        }
        return 0;
    }

    private boolean isAdjacent(String first, String second) {
        int cnt = 0;
        for(int k = 0; k < first.length(); k++){
            if(first.charAt(k) != second.charAt(k)){
                cnt++;
            }
        }
        return cnt == 1 ? true : false;
    }



    /**
     * N 皇后问题
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new ArrayList<>();
        char[][] board = new char[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                board[i][j] = '.';
            }
        }
        dfsSolve(board, 0, n, ans);
        return ans;
    }

    private void dfsSolve(char[][] board, int row, int n, List<List<String>> ans) {
        if(row == n){
            List<String> solution = new ArrayList<>();
            for(char[] chars : board){
                solution.add(String.valueOf(chars));
            }
            ans.add(solution);
            return;
        }

        for(int col = 0; col < n; col++){
            if(!isSafe(board, row, col, n)){
                continue;
            }
            board[row][col] = 'Q';
            dfsSolve(board, row + 1, n, ans);
            board[row][col] = '.';
        }
    }

    /**
     * 列，对角线方向判断
     * @param board
     * @param row
     * @param col
     * @param n
     * @return
     */
    private boolean isSafe(char[][]board, int row, int col, int n){
        for(int r = 0; r < row; r++){
            if(board[r][col] == 'Q') return false;
        }
        // 左上角对角线方向
        for(int r = row - 1, c = col - 1;  r >= 0 && c >= 0; r--,c--){
            if(board[r][c] == 'Q') return false;
        }
        // 右上角对角线方向
        for(int r = row - 1, c = col + 1; r >= 0 && c < n; r--, c++){
            if(board[r][c] == 'Q') return false;
        }
        return true;
    }


}
