package GeekTimeCourse;

/**
 * @Class: Course15
 * @Author: Minsky
 * @Date: 2019/11/23 10:26
 * @Version: v1.0
 * @Description: 二分查找
 *
 *
 *
 */
public class Course15 {

    public static void main(String[]args){
        System.out.println(getNumberSqrt(17));
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
}
