package interviewQuestions.swordToOffer;

/**
 * @Class: Question4
 * @Description:
 *    <<剑指offer -- 面试题 4>>
 *   在一个每一行从左到右递增，每一列从上到下递增的数组中查找某个元素，若存在则返回true,不存在则返回false
 *
 * @Author: Minsky
 * @Date: 2019/7/29 12:24
 * @Version: v1.0
 */
public class Question4 {

    public static void main(String[]args){

        int[][] arr = new int[][]{{1,2,8,9},{2,3,9,12},{4,7,10,13},{6,8,11,15}};

        System.out.println(twoDimensionArraySearch(arr,17));
    }

    /**
     * 二维数组查找
     * @param arr
     * @param target
     * @return
     */
    public static boolean twoDimensionArraySearch(int[][]arr , int target){
        // 列数 len 和 行数 high
        int len = arr[0].length;
        int high = arr.length -1;
        int i = 0 , j = len - 1;

        while(j >=0 && i <= high){

            if(arr[i][j] > target){
                j--;
            }else if(arr[i][j] < target){
                i++;
            }else{
                return true;
            }
        }
        return false;
    }
}
