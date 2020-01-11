package leetCode;

import java.util.PriorityQueue;

/**
 *
 */
public class FindKthLargest {

    public static void main(String[] args) {
        int[] test = {3,2,3,1,2,4,5,5,6};
        int[] test1 = {1,2};

        findKthLargest(test1,2);
    }

    static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue(k);
        for(int i = 0 ; i < k ; i++){
            queue.add(nums[i]);
        }

        int max = queue.peek();
        for(int j = k ; j < nums.length ; j++){
            if(nums[j] > max){
                queue.remove();
                queue.offer(nums[j]);
                max = queue.peek();
            }
        }
        return queue.peek();
    }
}
