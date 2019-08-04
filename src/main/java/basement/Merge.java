package basement;

import utils.ArrayUtil;

/**
 * @Class: MergeTwoArray
 * @Description:
 * @Author: Minsky
 * @Date: 2019/7/29 13:25
 * @Version: v1.0
 *
 * 归并排序：
 *
 */
public class Merge {

    private static int[] ak;


    public static void main(String[]args){
        int[] arr = ArrayUtil.randValueArray(20);
        sort(arr, 0,arr.length - 1);
        ArrayUtil.showArray(arr);
    }

    public static void sort(int[] a, int lo, int hi){
        ak = new int[a.length];
        if(lo >= hi) return;
        int mid = lo + (hi - lo)/2 ;
        sort(a,lo, mid);
        sort(a,mid+1, hi);
        merge(a,lo,mid, hi);
    }

    /**
     *
     * @param arr
     * @param lo
     * @param mid
     * @param hi
     */
    public static void merge(int [] arr , int lo , int mid ,int hi){
        int i = lo , j = mid + 1;
        int[] ak = new int[arr.length];

        for(int k = lo ; k <= hi; k++)
            ak[k] = arr[k];

        for(int k = lo ; k <= hi ; k++){
            if(i > mid) arr[k] = ak[j++];
            else if(j > hi) arr[k] = ak[i++];
            else if(ak[j] < ak[i]) arr[k] = ak[j++];
            else arr[k] = ak[i++];
        }
    }
}
