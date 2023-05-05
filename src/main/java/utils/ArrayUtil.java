package utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

/**
 * @Class: ArrayUtil
 * @Description:
 * @Author: Minsky
 * @Date: 2019/7/28 8:46
 * @Version: v1.0
 */
public class ArrayUtil {



    /**
     * 产生指定长度的随机数组, 随机值最大值为maxVal
     * @param n
     * @param maxVal
     * @return
     */
    public static int[] randValueArray(int n, int maxVal){
        int[] rands = new int[n];
        Random random = new Random();

        for(int i = 0; i < n ; i++){
            rands[i] = random.nextInt(maxVal);
        }
        return rands;
    }

    /**
     * 产生指定长度的随机数组, 随机值最大值不超过传参 n
     * @param n
     * @return 数值有可能重复
     */
    public static int[] randValueArray(int n){
        int[] rands = new int[n];
        Random random = new Random();

        for(int i = 0; i < n ; i++){
            rands[i] = random.nextInt(n);
        }
        return rands;
    }


    /**
     * 打印array中所有的元素
     * @param arr
     */
    public static void showArray(int[] arr){
        if(null == arr){
            System.out.println("null");
            return;
        }
        int len = arr.length;
        showArray(arr,len);
    }

    /**
     * 打印array中指定个数的元素
     */
    public static void showArray(int[] arr,int n){
        int len = n;
        for(int i = 0 ; i < len ; i++){
            if(i == len-1){
                System.out.print(arr[i]);
            }else{
                System.out.print(arr[i] + ",");
            }
        }
        System.out.println("");
    }


    /**
     * 统计数组中值为n的元素重复出现的次数
     * @param arr
     * @param n
     * @return
     */
    public static int countRepeatTimes(int[] arr, int n) {
        // 先二分查找找到该数值在数组中的位置
        int pos = Arrays.binarySearch(arr, n);
        if(pos < 0) return pos;

        // 再从该位置出发，分别向数组前后两端做搜索 直到结束
        int down = pos, up = pos + 1 , cnt = 0;
        while(down >= 0 || up < arr.length){
            if(down >= 0 && arr[down] == n) {
                cnt++;
            }

            if(up < arr.length && arr[up] == n){
                cnt++;
            }

            down-- ;
            up++;
        }

        return cnt;
    }

}
