package GeekTimeCourse;

/**
 * 使用递归树估计算法时间复杂度
 */
public class Course27 {

    public static void main(String[] args) {
        int[] arr = {1,2,3};
        printPenetration(arr,3,3);
    }

    /**
     * 递归打印数组的全排列
     */
    static void printPenetration(int[] arr , int len, int fi){
        if(1 == fi){
            for(int i = 0; i < len ; i++){
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }else{
            for(int j = 0 ; j < fi ; j++){
                int temp = arr[j];
                arr[j] = arr[fi -1];
                arr[fi - 1] = temp;
                printPenetration(arr, len , fi - 1);

                temp = arr[j];
                arr[j] = arr[fi -1];
                arr[fi - 1] = temp;
            }
        }

    }
}
