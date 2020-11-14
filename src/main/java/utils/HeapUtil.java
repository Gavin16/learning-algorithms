package utils;

import datastruct.Heap;

/**
 * @ClassName: HeapUtil
 * @Description: 堆工具类
 * @Author: wufangmin
 * @Date: 2019/12/12 11:28
 * @Version: 1.0
 */
public class HeapUtil {


    /**
     * 对堆使用堆排序
     * @param data
     * @param n
     */
    public static void heapSort(int[] data, int n){
        buildHeap(data,n);
        for(int i = n - 1; i >= 0 ; i--){
            swap(data,0,i);
            // 每次对根节点做从上至下的堆化
            heapify(data,i,0);
        }
    }


    /**
     * 普通数组使用堆排序
     * @param data
     */
    public static void heapSort(int[] data){
        int len = data.length;
        buildHeap(data,len);
        for(int i = len - 1; i >= 0 ; i--){
            swap(data,0,i);
            // 每次对根节点做从上至下的堆化
            heapify(data,i,0);
        }
    }


    /** 打印堆 */
    public static void printHeap(Heap heap){
        int len = heap.getCount();
        int[] data = heap.getData();
        ArrayUtil.showArray(data,len);
    }

    /** 堆排序实现 */
    public static void heapSort(Heap heap){
        heapSort(heap.getData(),heap.getCount());
    }


    public static void buildHeap(int[]data){
        buildHeap(data,data.length);
    }

    /**
     * 将输入无序数组堆化
     *
     * @param data
     * @param n 数组长度
     */
    public static void buildHeap(int[]data,int n){
        if(null == data || n <= 1) return;
        // 数组中 n/2 - n 的元素为叶子节点，由于堆化采用从上至下方式堆化
        // 故只对非叶子节点做判断
        for(int i = (n-1)/2 ; i >= 0 ; i --){
            heapify(data,n,i);
        }
    }

    private static void heapify(int[] data, int n, int k){
        int maxPos = k;
        while(true){
            if((2*k + 1) < n && data[k] < data[2*k+1])
                maxPos = 2*k + 1;
            if((2*k + 2) < n && data[maxPos] < data[2*k + 2] )
                maxPos = 2*k + 2;
            if(maxPos == k) break;
            swap(data,k,maxPos);
            k = maxPos;
        }
    }


    /** 交换数组中两个指定下表的元素 */
    private static void swap(int[] data, int k, int l) {
        int temp = data[k];
        data[k] = data[l];
        data[l] = temp;
    }
}
