package leetCode.depthFirstSearch;

import java.util.ArrayList;
import java.util.List;

/**
 * 《46. 全排列》
 *
 * 给定一个没有重复数字的序列，返回其所有可能的全排列。
 *
 * 示例:
 *
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 *
 */
public class Permute {

    public static void main(String[] args) {

    }


    static List<List<Integer>> list = new ArrayList<>();

    /**
     *  返回数组data的所有全排列
     */
    static List<List<Integer>> listPermute(int[] data){
        addDfsPermute(data,data.length-1,data.length-1);
        return list;
    }

    static void addDfsPermute(int[] data,int k , int n){
        if(k == 0){
            List<Integer> row = new ArrayList<>();
            for(int i = 0; i <= n ; i++){
                row.add(data[i]);
            }
            list.add(row);
        }else{
            for(int j = k ; j >= 0  ; j--){
                swap(data,j,k);
                addDfsPermute(data,k-1,n);
                swap(data,k,j);
            }
        }
    }

    static void swap(int[] data, int k, int l){
        int temp = data[k];
        data[k] = data[l];
        data[l] = temp;
    }
}
