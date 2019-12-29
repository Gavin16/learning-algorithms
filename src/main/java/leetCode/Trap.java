package leetCode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * 《42. 接雨水》
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 *
 * 示例:
 *
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 *
 */
public class Trap {

    static int trap(int[] height) {
        if(null == height || height.length <=2) return 0;
        // 查分求出height中所有的极大值
        int[] diff = new int[height.length - 1];
        for(int i = 1 ; i < height.length ; i++){
            diff[i-1] = height[i] - height[i-1];
        }
        // 遍历差分数组，找出所有极大值
        List<Integer> ids = new ArrayList<>();
        if(diff[0] < 0) ids.add(0);
        for(int j = 0; j < diff.length-1 ; j++){
            if(diff[j] > 0 && diff[j+1] < 0) ids.add(j+1);
        }
        if(diff[diff.length-1] > 0) ids.add(diff.length);



        return 0;
    }

    /** 递归计算 */
    static int calculateAmount(int[]nums , int l, int r){
        int amount = 0;
        int j = l;
        for(int i = l+1; i < r ; i++){
            if(nums[i] > nums[j]){
                amount += calculateAmount(nums,j,i);
                j = i;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] nums = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap(nums));

        List<Integer> list = new ArrayList<>();
    }
}
