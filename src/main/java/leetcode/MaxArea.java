package leetcode;

/**
 * @ClassName: MaxArea
 * @Description:
 * 《11. 盛最多水的容器》 标签：数组，双指针
 *  给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，
 *  垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 *
 * 示例:
 *
 * 输入: [1,8,6,2,5,4,8,3,7]
 * 输出: 49
 *
 * @Author: wufangmin
 * @Date: 2019/12/27 14:48
 * @Version:
 */
public class MaxArea {


    public static void main(String[] args) {
        int[] stems = {1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(stems));
    }

    /**  */
    static int maxArea(int[] height) {
        if(height == null || height.length < 2) return 0;

        int head = 0,tail = height.length-1;
        int max = 0;
        while(head < tail){
            int h = height[head] < height[tail] ? height[head] : height[tail];
            int area =  h*(tail-head);
            if(area > max){
                max = area;
            }else{
                if(height[head] < height[tail]){
                    head++;
                }else{
                    tail--;
                }
            }
        }
        return max;
    }
}
