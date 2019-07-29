package swordToOffer;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Class: Question3
 * @Description:
 *      <<剑指offer -- 面试题 3>>
 *      在长度为 n 的数组中存着 0 ~ n-1 的值，其中包含一个或者多个重复的值，找出中间的某一个重复值
 *
 *      可以有三种方法来解决该问题：
 *      (1) 先对数组排序，然后搜索有序数组找出重复值
 *      (2) 遍历数组 并使用map 保存每一元素，保存前判断map中是否已经含有该元素，若已有则该元素时重复值
 *      (3) 考虑没有重复元素的情况下，每个元素的下标都可以找到与之相等的元素,因此可以通过交换值不等于下标的元素到对应下标
 *          对数组遍历时，可以找到某个元素交换时和被交换元素相等，该元素的值就是重复值
 * @Author: Minsky
 * @Date: 2019/7/29 11:44
 * @Version: v1.0
 */
public class Question3 {


    public static void main(String[]args){

        int[] arr = new int[]{2,3,1,0,2,5};

        System.out.println(solution1(arr));
        System.out.println(solution2(arr));
        System.out.println(solution3(arr));
    }

    /**
     * 先排序，再在有序数组中搜索重复元素
     * @param arr
     * @return
     */
    public static int solution1(int[] arr){
        Arrays.sort(arr);

        // 搜索元素, 要求数组长度至少不小于2
        int i = 0 , j = i + 1;
        for(; j < arr.length ; i++, j++){
            if(arr[i] == arr[j]){
                break;
            }
        }
        return arr[i];
    }

    /**
     * 使用map 保存遍历过的元素，出现重复时可以立即发现
     * @param arr
     * @return 若包含重复元素, 则返回第一个找到的重复值，否则返回第一个元素
     */
    public static  int solution2(int[] arr){
        Map<Integer,Integer> map = new HashMap<>();

        int res = arr[0];
        for(int i = 0 ; i < arr.length ; i++){
            if(map.containsKey(arr[i])){
                res = arr[i];
                break;
            }else{
                map.put(arr[i],i);
            }
        }

        return res;
    }


    /**
     * 采用(基于下标与元素值相等的原则)交换元素的方式找到重复元素
     * @param arr
     * @return 返回重复元素，若不存在重复元素则返回第一个元素
     */
    public static int solution3(int[] arr){

        int cur = arr[0];
        for(int i = 0 ; i < arr.length ; i++){

            // 将元素i交换到 与arr[i] 值相等的地方
            while(arr[i] != i){
                cur = arr[i];
                if(arr[cur] == cur){
                    // 找到相同重复元素
                    break;
                }else{
                    // 交换元素
                    int temp = cur;
                    arr[i] = arr[cur];
                    arr[cur] = temp;
                }
            }
        }

        return cur;
    }


}
