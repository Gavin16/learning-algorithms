package leetCode;

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

    /**
     *  通过求出极大值，计算极大值之间围成的区域来计算容量: 行不通！！
     */
    static int trap(int[] height) {
        if(null == height || height.length <=2) return 0;
        // 差分找出极大值所在的位置
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

        // 只有一个极大值，不能盛水
        if(ids.size() < 2) return 0;


        int totalCap = 0, l = 0;
        // 遍历极大值求各盛水区域的容量, 这里l,k 都指的
        for(int k = 1; k < ids.size(); k++){
            if(height[ids.get(k)] < height[ids.get(l)]){
                // 右侧极大值比左侧的小，需要判断右侧还有没有比左侧极大值大的
                // 若没有则计算当前 l,k 围成区域的容量; 若有则计算左侧到右侧大于左侧的极大值围成区域的容量
                int bigger = findBigThanLeft(height,ids,k,l);
                if(bigger > 0){
                    totalCap += calculateCap(height,ids.get(l),ids.get(bigger));
                    l = bigger;
                    k = bigger;
                }else{
                    totalCap += calculateCap(height,ids.get(l),ids.get(k));
                    l = k;
                }
            }else{
                totalCap += calculateCap(height,ids.get(l),ids.get(k));
                l = k;
            }

        }
        return totalCap;
    }

    /**  */
    private static int findBigThanLeft(int[] height, List<Integer> ids, int k,int l) {
        for(int i = k ; i < ids.size(); i++){
            if(height[ids.get(i)] > height[ids.get(l)]) return i;
        }
        return -1;
    }


    /**
     * 计算小范围内的容量
     */
    static int calculateCap(int[] nums , int l, int r){

        int width = r - l -1, minH = Math.min(nums[l],nums[r]);
        int cap = width * minH;
        for(int i = l+1; i < r ;i++){
            cap -= nums[i];
        }

        return cap;
    }


    /**
     * 双指针遍历找出所有的有效的小区域
     *
     * 遍历判断可以盛水的依据：
     *
     */
    static int trap2(int[] height) {
        int l = 0 , r = height.length - 1;
        int i = l , cap = 0 ;
        // lDec , rDec 用来判断是否凹陷过，
        boolean lDec = false;
        while(i <= r){
            if(height[i] < height[l]){
                // 之前是否递减过，未递减过则设置标记
                if(!lDec){
                    // 判断i之后是否还有大于 height[i]的,若没有则 l 从 i开始继续
                    if(!hasBiggerThanCurr(height,i)){
                        l = i;
                    }else{
                        lDec = true;
                    }
                }else{
                    // 凹陷过且当前值仍然小于左侧的值
                    // 需要再次判断右侧是否有大于当前值的，若没有则计算当前l到i的容量
                    if(!hasBiggerThanCurr(height,i)){
                        cap += calculateCap(height,l,i);
                        lDec = false;
                        l = i;
                    }
                }
            }else{
                if(lDec){
                    cap += calculateCap(height,l,i);
                    lDec = false;
                }
                l = i;
            }
            i++;
        }

        return cap;
    }

    private static boolean hasBiggerThanCurr(int[] height, int i) {
        for(int j = i; j < height.length ; j++){
            if(height[j] > height[i]) return true;
        }
        return false;
    }


    public static void main(String[] args) {
        int[] nums = {0,1,0,2,1,0,1,3,2,1,2,1};
        int[] nums1 = {4,2,3};
        int[] nums2 = {2,6,3,8,2,7,2,5,0};
        System.out.println(trap2(nums));
    }
}
