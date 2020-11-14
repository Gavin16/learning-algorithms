package leetcode;


/**
 *  每日一题
 *  《面试题 10.01. 合并排序的数组》
 *
 *  给定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。 编写一个方法，将 B 合并入 A 并排序。
 *
 * 初始化 A 和 B 的元素数量分别为 m 和 n。
 *
 * 示例:
 *
 * 输入:
 * A = [1,2,3,0,0,0], m = 3
 * B = [2,5,6],       n = 3
 *
 * 输出: [1,2,2,3,5,6]
 */
public class Merge {

    public static void main(String[] args) {
        int[] A = {1,2,3,0,0,0};
        int[] B = {2,5,6};
        merge(A,3,B,3);
        for(int n : A){
            System.out.print(n + " ");
        }
    }


    public static void merge(int[] A, int m, int[] B, int n) {
        int lenA = A.length;
        for(int i = 1 ; i <= m ; i++){
            A[lenA-i] = A[m-i];
        }
        // 合并数据到数组A
        int j = lenA - m , k = 0 , i = 0;
        while(j != lenA && k != n){
            if(A[j] <= B[k]){
                A[i++] = A[j++];
            }else{
                A[i++] = B[k++];
            }
        }
        // 存在剩余数据
        if(j == lenA){
            while(k != n){
                A[i++] = B[k++];
            }
        }
        if(k == n){
            while(j != lenA){
                A[i++] = A[j++];
            }
        }
    }
}
