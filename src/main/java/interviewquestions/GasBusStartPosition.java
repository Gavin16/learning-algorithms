package interviewquestions;

/**
 * @ClassName: GasBusStartPosition
 * @CopyRight: wufangmin
 * @Author: wufangmin
 * @Date: 2019/11/18 14:34
 * @Version: 1.0
 *
 * @Description: 加油车对工作区域加油
 *
 * 字节跳动面试题：
 * 办公场所有 N 个工作区，加油车可以围着工作区转圈，加油车GasBus 可以在每一个区域加油，同时去往下一个区域需要也会耗油
 *
 * 加油和耗油对应的数组定义如下数组
 * gas = [1,2,3,4,5]
 * cost = [3,4,5,1,2]
 *
 * 若加油车能够循环一圈则返回起始位置，否则返回 -1
 *
 * 例如上例的情况返回 3
 *
 */
public class GasBusStartPosition {

    public static void main(String[] args) {
        int[] gas = {4,5,1,2,3};
        int[] cost = {3,4,3,1,2};

        System.out.println(circlyAddGas1(gas,cost));
    }

    /**
     * 和上一版本几乎一样; 时间复杂度 O(n^2)
     */
    static int circlyAddGas1(int[]gas, int[]cost){
        int len = gas.length;
        for(int i = 0 ; i < len; i++){
            int j = i , sum = 0;
            do{
                sum += gas[j];
                sum -= cost[j];
                if(sum >=0 ){
                    j = (++j) % len;
                }else{
                    // 若 sum < 0 退出当层循环
                    break;
                }
            }while(j != i);

            if(sum >= 0 && j == i) return i;
        }
        return -1;
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
