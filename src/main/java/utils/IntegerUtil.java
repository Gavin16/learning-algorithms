package utils;

import java.util.Arrays;
import java.util.List;

public class IntegerUtil {

    /**
     *  int 数组转 Integer数组
     */
    public static Integer[] toIntegerArray(int[] nums){
        Integer[] data = new Integer[nums.length];
        for(int i = 0 ; i < data.length ; i++){
            data[i] = nums[i];
        }
        return data;
    }

    /**
     * int 数组转 Integer 列表
     */
    public static List<Integer> toIntegerList(int[] nums){
        return Arrays.asList(toIntegerArray(nums));
    }
}
