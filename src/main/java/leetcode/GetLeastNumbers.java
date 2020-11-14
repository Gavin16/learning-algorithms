package leetcode;

import utils.ArrayUtil;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

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

        //
        Integer[] prod = {48553,48554,48555,48556,48557,48558,48559,48560,48561,48562,48563,48564,48565,48566,48567,48568,48569,48570,48571,48572,48573,48574,48575,48576,48577,48578,48579,48523,48525,48527,48528,48529,48531,
                48532,48533,48534,48536,48537,48538,48539,48540,48541,48542,48543,48544,48545,48546,48547,48548,48549,48550,48551};

        Set<Integer> set = new HashSet<>();
        set.addAll(Arrays.asList(prod));
        System.out.println(set.size());

        // 计算推送批次号
        LocalDateTime now = LocalDateTime.now();
        System.out.println(new Date().getTime());
        System.out.println(now.toInstant(ZoneOffset.of("+8")).toEpochMilli());

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
