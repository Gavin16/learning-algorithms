package interviewQuestions;

/**
 * @ClassName: GasBusStartPosition
 * @CopyRight: wufangmin
 * @Author: wufangmin
 * @Date: 2019/11/18 14:34
 * @Version: 1.0
 *
 * @Description: 加油车对工作区域加油
 * gas = [1,2,3,4,5]
 * cost = [3,4,5,1,2]
 * 返回 3
 *
 */
public class GasBusStartPosition {

    public static void main(String[] args) {
        int[] gas = {1,2,3,4,5};
        int[] cost = {3,4,5,1,9};

        System.out.println(circlyAddGas(gas,cost));
    }


    /**
     * 若加油车能够循环一圈则返回起始位置，否则返回 -1
     * @param gas
     * @param cost
     * @return
     */
    static int circlyAddGas(int[] gas, int[] cost){
        int len = gas.length;
        for(int i = 0 ; i < len; i++){
            int sum = 0,j = i;
            do{
                sum += gas[j];
                sum = sum - cost[j];
                if(sum < 0){
                    j++;
                    break;
                }
                j = (j+1) % len;
            }while (j != i);
            if(j == i) return i;
        }
        return -1;
    }

}
