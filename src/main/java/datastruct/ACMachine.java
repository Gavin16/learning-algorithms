package datastruct;


import java.util.*;

/**
 *
 * AC自动机实现多模式匹配
 *
 * (1) 字符串多模式匹配考虑空格' ' 开始的96个字符
 *
 */
public class ACMachine {


    private static final int MAX_CHAR_NUM = 96;

    public static void main(String[] args) {
        HashSet<String> set = new HashSet<>();
        set.add("fuck");
        set.add("damn");
        set.add("bitch");
        ACMachine acMachine = new ACMachine(set);
        String testStr = "Hey,fuck you! you god damn bitch,leave that girl alone.";
        List<String> match = acMachine.match(testStr.toCharArray());
        System.out.println("AC 自动机多模匹配后结果:" + match);
        int[][] sections =  mergeOverlap(match);
        String s = replaceCharacters(testStr, sections);

        System.out.println("替换敏感词后的字符串:" + s);
    }

    private static String replaceCharacters(String testStr, int[][] sections) {
        StringBuilder stringBuilder = new StringBuilder(testStr);
        for(int[] wordIds : sections){
            StringBuilder sb1 = new StringBuilder();
            for(int i=wordIds[0] ; i <= wordIds[1] ; i++){
                sb1.append("*");
            }
            stringBuilder.replace(wordIds[0],wordIds[1]+1,sb1.toString());
        }
        return stringBuilder.toString();
    }

    /**
     * 区间重叠默认只取第一个
     *
     * @param match
     * @return
     */
    private static int[][] mergeOverlap(List<String> match) {
        int N = match.size();
        int[][] res = new int[N][2];
        for(int k = 0 ; k < match.size() ; k++){
            String pair = match.get(k);
            String[] split = pair.split("-");
            res[k][0] = Integer.parseInt(split[0]);
            res[k][1] = Integer.parseInt(split[1]);
        }
        if(N == 1) return res;
        // 以第一个元素按从小到大顺序排序
        Arrays.sort(res,((o1, o2) -> o1[0] - o2[0]));
        List<int[]> resList = new ArrayList<>();
        resList.add(res[0]);
        int preRight = res[0][1];
        int curr = 1;
        for(int i = 1; i < res.length ; i++){
            int[] row = res[i];
            if(row[0] >= preRight){
                preRight = row[1];
                res[curr++] = row;
            }
        }
        return Arrays.copyOf(res,curr);
    }

    private AcNode root;

    public ACMachine(HashSet<String> templateStrs){
        //  根节点不存储任何信息用 '/' 标识
        root = new AcNode('/');
        for(String str : templateStrs){
            if(str.length() > 0){
                insert(str.toCharArray());
            }
        }
        // 构建完Trie树后增加失败指针
        buildFailurePointer();

    }


    /** 插入单个模式串 */
    public void insert(char[] text) {
        AcNode p = root;
        for (int i = 0; i < text.length; ++i) {
            int index = text[i] - ' ';
            if (p.children[index] == null) {
                AcNode newNode = new AcNode(text[i]);
                p.children[index] = newNode;
            }
            p = p.children[index];
        }
        p.isEndingChar = true;
        p.length = text.length;
    }

    /**
     * 构建Trie树的失败指针
     */
    private void buildFailurePointer() {
        Queue<AcNode> queue = new LinkedList<>();
        root.fail = null;
        queue.add(root);
        while (!queue.isEmpty()) {
            AcNode p = queue.remove();
            // 按照队列添加顺序，每次遍历单个节点下所有子节点
            for (int i = 0; i < MAX_CHAR_NUM; ++i) {
                AcNode pc = p.children[i];
                if (pc == null) continue;
                if (p == root) {
                    pc.fail = root;
                } else {
                    AcNode q = p.fail;
                    // 根据失败指针链由下往上搜索，找出Trie树中当前子节点所有可能的失败指针，若无则指向root
                    while (q != null) {
                        // pc的字符是否和qc的字符相同,相同则记录失败指针
                        AcNode qc = q.children[pc.data - ' '];
                        if (qc != null) {
                            pc.fail = qc;
                            // 当前子节点确定失败指针,则处理下一个子节点
                            break;
                        }
                        q = q.fail;
                    }
                    if (q == null) {
                        pc.fail = root;
                    }
                }
                queue.add(pc);
            }
        }
    }


    /**
     *
     * 匹配方式:
     * 多模匹配在发现目标主串与某个模式串不匹配之后,立即通过失败指针在Trie 树种去寻找下一个具有最长前缀的模式串
     * 并与模式串做匹配 直到失败指针指向root
     * @param text
     */
    public List<String> match(char[] text) { // text是主串
        int n = text.length;
        AcNode p = root;
        List<String> segList = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            int idx = text[i] - ' ';
            while (p.children[idx] == null && p != root) {
                p = p.fail; // 失败指针发挥作用的地方
            }
            p = p.children[idx];
            if (p == null) p = root; // 如果没有匹配的，从root开始重新匹配(后续逻辑将被跳过)
            AcNode tmp = p;

            while (tmp != root) { // 打印出可以匹配的模式串
                if (tmp.isEndingChar == true) {
                    int pos = i-tmp.length+1;
                    segList.add(pos + "-" + (pos + tmp.length - 1));
                }
                tmp = tmp.fail;
            }
        }
        return segList;
    }



    class AcNode {
        /** 当前节点字符存储内容 */
        public char data;
        /**  字符集只包含100个字符 */
        public AcNode[] children = new AcNode[MAX_CHAR_NUM];
        /** 结尾字符为true */
        public boolean isEndingChar = false;
        /** 当isEndingChar=true时，记录模式串长度 */
        public int length = -1;
        /** 失败指针 */
        public AcNode fail;
        public AcNode(char data) {
            this.data = data;
        }
    }
}
