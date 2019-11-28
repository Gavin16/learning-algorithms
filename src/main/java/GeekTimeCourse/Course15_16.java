package GeekTimeCourse;


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
//        int[] arr = ArrayUtil.randValueArray(15);
//        ArrayUtil.showArray(arr);
//        Arrays.sort(arr);
//        ArrayUtil.showArray(arr);
        int[] arr = {0,2,2,3,5,6,6,6,7,8,8,10,11,12};
        System.out.println(lastSmallerValue(arr,6));
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

        int len = arr.length;
        int high = len - 1, low = 0;

        while(low <= high){
            int mid = low + ((high - low) >> 1);
            if( n > arr[mid]){
                low = mid + 1;
            }else{
                if(mid == 0 || arr[mid - 1] < n){
                    return mid;
                }else{
                    high = mid - 1;
                }
            }
        }

        return -1;
    }

    /**
     * 查找最后一个小于等于给定值的元素
     */
    static int lastSmallerValue(int[] arr,int n){
        int len = arr.length - 1;
        int high = len, low = 0;

        while(low <= high){
            int mid = low + ((high - low) >> 1);
            if(n < arr[mid]){
                high = mid - 1;
            }else{
                if(mid == len || arr[mid + 1] > n){
                    return mid;
                }else{
                    low = mid + 1;
                }
            }
        }
        return -1;
    }


    /**
     * 循环有序数组的二分查找
     * 《LeetCode 33. 搜索旋转排序数组》
     *
     *  假设按照升序排序的数组在预先未知的某个点上进行了旋转。
     *
     * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
     *
     * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
     *
     * 你可以假设数组中不存在重复的元素。
     *
     * 你的算法时间复杂度必须是 O(log n) 级别。
     *
     * 示例 1:
     * 输入: nums = [4,5,6,7,0,1,2], target = 0
     * 输出: 4
     */
    static int periodicSortedBinSearch(int[] nums, int target){
        return -1;
    }

}
