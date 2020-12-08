package leetcode.oneMoreHundred;

/**
 *
 * AC自动机实现多模式匹配
 *
 */
public class ACMachine {

    /**  */
    private AcNode root = new AcNode('/');

    public void insert(char[] text) {
        AcNode p = root;
        for (int i = 0; i < text.length; ++i) {
            int index = text[i] - 'a';
            if (p.children[index] == null) {
                AcNode newNode = new AcNode(text[i]);
                p.children[index] = newNode;
            }
            p = p.children[index];
        }
        p.isEndingChar = true;
    }





    class AcNode {
        public char data;
        public AcNode[] children = new AcNode[26]; // 字符集只包含a~z这26个字符
        public boolean isEndingChar = false; // 结尾字符为true
        public int length = -1; // 当isEndingChar=true时，记录模式串长度
        public AcNode fail; // 失败指针
        public AcNode(char data) {
            this.data = data;
        }
    }
}
