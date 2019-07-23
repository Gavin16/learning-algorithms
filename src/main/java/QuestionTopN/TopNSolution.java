package QuestionTopN;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

/**
 * @Class: TopNSolution
 * @Description: Partition及小顶堆 两种方式实现找出数组中的最大的前N 个元素
 * @Author: Minsky
 * @Date: 2019/7/23 16:45
 * @Version: v1.0
 */
public class TopNSolution {


    public static void main(String[]args){
        int[] test100 = randomIntArr(30);
        show(test100);
        int[] topN = findTopN(test100, 9);
        // 结果
        show(topN);
    }


    /**
     * 找出arr中前N大的元素
     * @param arr
     * @param n
     * @return int[]
     */
    public static int[] findTopN(int[] arr, int n){
        if(null == arr || arr.length == 0 || n > arr.length || n < 1) return null;

        int last = arr.length - 1;
        int id = partition(arr , 0 , last);
        int targetId = last -n;
        // 前 n 大的元素出现在arr的后面
        while(id != targetId){
            if( id < targetId){
                id = partition(arr , id + 1 , last);
            }

            if(id > targetId){
                id = partition(arr , 0 , id - 1);
            }
        }

        // 循环结束 代表 id == targetId
        int[] topN = new int[n];
        for(int i = 0; i < n ; i++ ){
            topN[i] = arr[id++];
        }

        return topN;
    }


    /**
     * 获取以数组首元素为枢轴的 该枢轴下标id
     * @param a
     * @param lo
     * @param hi
     * @return
     */
    private static int partition(int[] a, int lo , int hi){
        // 取第一个元素作为枢轴
        int pivot = a[lo];
        int i = lo, j = hi + 1;

        while(true){
            while(less(a[++i] , pivot)){
                if (i == hi) break;
            }

            while(less(pivot,a[--j])){
                if(j == lo) break;
            }

            // 判断是否已经找出所有需要交换的元素
            if(i >= j) break;

            // 还有元素未遍历，则先交换元素
            exchange(a , i ,j);
        }
        // i == j ，a[i]元素左右已经满足 左边元素比枢轴小，右边元素比枢轴大
        exchange(a,lo,j);

        return j;
    }

    private static boolean less(int a , int b){
        return a < b ? true : false;
    }

    private static void exchange(int[] a, int l , int r){
        int temp = a[l];
        a[l] = a[r];
        a[r] = temp;
    }


    private static void show(int[]arr){
        for(int n : arr){
            System.out.print(n + " ");
        }
        System.out.println();
    }

    /**
     * 生成指定个数的 随机数组 , 数组元素大小在 0 - n 区间分布
     * @param n
     * @return
     */
    private static int[] randomIntArr(int n){
        Random random = new Random();
        int[] arr = new int[n];

        for (int i = 0 ; i < n  ; i++){
            arr[i] = random.nextInt(n);
        }

        return arr;
    }
}
