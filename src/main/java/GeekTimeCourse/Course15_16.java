package GeekTimeCourse;

import utils.ArrayUtil;

import java.util.Arrays;

/**
 * @Class: Course15_16
 * @Author: Minsky
 * @Date: 2019/11/23 10:26
 * @Version: v1.0
 * @Description: 二分查找
 *
 *
 *
 */
public class Course15_16 {

    public static void main(String[]args){
        int[] arr = ArrayUtil.randValueArray(15);
        ArrayUtil.showArray(arr);
        Arrays.sort(arr);
        ArrayUtil.showArray(arr);

        System.out.println(lastEqualValue(arr,3));
    }


    /**
     * 求一个数的平方根，精确到小数点后6位
     *
     * 类似的立方根，多少次方根都能很快求出来
     */
    static float getNumberSqrt(int num){

        float high = (float)num;
        float low = 0.0f;

        while(low < high){
            float mid = low + (high - low)/2;
            float curr = mid * mid;
            if((curr - num) > 1e-6){
                high = mid;
            }else if((curr - num) < -1e-6){
               low = mid;
            }else{
                return mid;
            }
        }

        return -1;
    }



/********************************* course16 **********************************/
    /**
     * 查找第一个值等于给定值的元素: 返回元素的下标
     *
     */
    static int firstEqualValue(int[] arr , int n){
        int high = arr.length - 1;
        int low = 0,mid = 0;

        while(low <= high){
            mid = low + (high - low)/2;

            if(arr[mid] < n){
                low = mid + 1;
            }else if(arr[mid] > n){
                high = mid - 1;
            }else{
                break;
            }
        }

        if(low > high){
            return -1;
        }else{
            for(int i = mid ; i > 0 ; i--){
                if(arr[i] != n){
                    mid = i + 1;
                    break;
                }
            }
        }
        return mid;
    }

    /**
     *  查找最后一个值等于给定值的元素: 一次循环完成
     */
    static int lastEqualValue(int[] arr, int n){

        int len = arr.length, high = len - 1;
        int low = 0;

        while(low <= high){
            int mid = low + (high - low)/2;
            if(n < arr[mid]){
                high = mid - 1;
            }else if(n > arr[mid]){
                low = mid + 1;
            }else{
                if(mid == len-1 || arr[mid+1] != n){
                    return mid;
                }else{
                    low = mid + 1;
                }
            }
        }

        return -1;
    }

    /**`
     * 查找第一个大于等于给定值的元素
     */
    static int firstLargerValue(int[] arr, int n){

        return -1;
    }

    /**
     * 查找最后一个小于等于给定值的元素
     */
    static int lastSmallerValue(int[] arr,int n){
        return -1;
    }

}
