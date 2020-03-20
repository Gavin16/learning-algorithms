package leetCode;

/**
 *
 * 《836. 矩形重叠》
 *
 * 矩形以列表 [x1, y1, x2, y2] 的形式表示，其中 (x1, y1) 为左下角的坐标，(x2, y2) 是右上角的坐标。
 *
 * 如果相交的面积为正，则称两矩形重叠。需要明确的是，只在角或边接触的两个矩形不构成重叠。
 *
 * 给出两个矩形，判断它们是否重叠并返回结果。
 *
 * 示例 1：
 *
 * 输入：rec1 = [0,0,2,2], rec2 = [1,1,3,3]
 * 输出：true
 * 示例 2：
 *
 * 输入：rec1 = [0,0,1,1], rec2 = [1,0,2,1]
 * 输出：false
 *
 */
public class IsRectangleOverlap {


    public static void main(String[] args) {
        String str = "DabccccAdD";
        IsRectangleOverlap obj = new IsRectangleOverlap();
        System.out.println(obj.longestPalindrome(str));

        System.out.println('z' - '0');
    }

    /**
     *  判断方式：
     */
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        return false;
    }

    public int longestPalindrome(String s) {
        int[] chars = new int[75];

        for(char ch : s.toCharArray()){
            chars[ch - '0']++;
        }

        int maxLen =  0 , oddNum = 0;
        for(int n : chars){
            maxLen += n;
            if((n & 1) == 1){
                oddNum += 1;
            }
        }
        return  oddNum > 0 ? maxLen - oddNum + 1 : maxLen;
    }
}
