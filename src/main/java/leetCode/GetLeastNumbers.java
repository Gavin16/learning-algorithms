package leetCode;

import utils.ArrayUtil;

import java.util.PriorityQueue;

/**
 * 《面试题40. 最小的k个数》
 *
 *
 */
public class GetLeastNumbers {


    public static void main(String[] args) {
        int[] arr = {4,5,1,6,2,7,3,8};
        int[] leastNumbers = new GetLeastNumbers().getLeastNumbers(arr, 4);
        ArrayUtil.showArray(leastNumbers);
    }

    /**
     *  优先队列解法
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        if(k == 0) return new int[0];
        if(k == arr.length) return arr;

        PriorityQueue<Integer> queue = new PriorityQueue<>(k,(o1,o2)-> o2 - o1);
        for(int j = 0 ; j < arr.length ; j++){
            if(j < k){
                queue.add(arr[j]);
            }else{
                int first = queue.peek();
                if(arr[j] < first){
                    queue.remove();
                    queue.add(arr[j]);
                }
            }
        }

        int[] res = new int[k];
        for(int i = 0 ; i <  k ; i++)
            res[i] = queue.poll();
        return res;
    }


    /**
     * 使用枢轴  TODO
     */
    public int[] getLeastNumbers1(int[]arr , int k){
        return null;
    }
}
