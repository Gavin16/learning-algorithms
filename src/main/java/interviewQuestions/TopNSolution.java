package interviewQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * @Class: TopNSolution
 * @Description: Partition 及小顶堆 两种方式实现找出数组中的最大的前N 个元素
 * @Author: Minsky
 * @Date: 2019/7/23 16:45
 * @Version: v1.0
 */
public class TopNSolution {

    public static void main(String[]args){

        System.out.println("----------使用partition方式----------");
        int[] testArr = randomIntArr(30);
        show(testArr);
        copyAndSort(testArr);

        System.out.println("----------使用小顶堆----------");
        int[] testArr1 = randomIntArr(20);
        show(testArr1);

    }


    /**
     * 找出arr中前N大的元素，采用小顶堆实现
     * @param arr
     * @param n
     * @return
     */
    public int[] findTopN2(int[] arr, int n){
        return findTopN1(arr,n);
    }


    /**
     * 找出数组中最小的 n 个数
     * @param arr
     * @param n
     * @return
     */
    public static ArrayList<Integer> findButtomN(int[] arr, int n){
        if(null == arr || arr.length == 0 || n > arr.length || n < 1) return null;

        int start = 0;
        int end = arr.length - 1;

        int id = partition(arr , start , end);
        int target = n-1;

        while(id != target){
            if(id < target){
                id = partition(arr, id + 1, end);
            }else{
                id = partition(arr,start, id - 1);
            }
        }


        ArrayList<Integer> intList = new ArrayList<>();
        for(int i = 0 ; i < n ; i++){
            intList.add(new Integer(arr[i]));
        }

        return intList;
    }

    /**
     * 找出arr中前N大的元素: 采用快速排序的partition方式实现
     * @param arr
     * @param n
     * @return int[]
     */
    public static int[] findTopN1(int[] arr, int n){
        if(null == arr || arr.length == 0 || n > arr.length || n < 1) return null;

        int last = arr.length - 1;

        int id = partition(arr , 0 , last);
        int targetId = arr.length - n;

        // 前n大的元素出现在arr的后面
        while(id != targetId){
            if( id < targetId){
                id = partition(arr , id + 1 , last);
            }else{
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
            // 这里 i,j 的操作由于在while中判断后还需要做交换处理
            // 因此 需要保证while中的 ++/-- 操作发生在使用之前
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

    /**
     * 这里less 指的是小于等于
     * @param a
     * @param b
     * @return
     */
    private static boolean less(int a , int b){
        return a <= b ? true : false;
    }

    private static void exchange(int[] a, int l , int r){
        int temp = a[l];
        a[l] = a[r];
        a[r] = temp;
    }

    private static void copyAndSort(int[] org){
        int len = org.length;
        int[] copy = new int[len];

        for(int i = 0 ; i < len; i++){
            copy[i] = org[i];
        }

        Arrays.sort(copy, 0, len);

        show(copy);
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
