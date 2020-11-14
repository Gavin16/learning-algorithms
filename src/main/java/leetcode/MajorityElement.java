package leetcode;

/**
 * @Class: MajorityElement
 * @Description:
 *
 * 《GeekTime -- practice day01》
 * 《169. 多数元素》
 *
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 * 示例 1:
 *
 * 输入: [3,2,3]
 * 输出: 3
 * 示例 2:
 *
 * 输入: [2,2,1,1,1,2,2]
 * 输出: 2
 *
 *
 * @Author: Minsky
 * @Date: 2020/2/1 17:00
 * @Version: v1.0
 */
public class MajorityElement {


    public static void main(String[] args) {
        int[] nums = {2,2,1,1,1,2,2};
        System.out.println(majorityElement(nums));
    }


    public static int majorityElement(int[] nums) {
        int i,val,cnt;
        for(i = 0, val = nums[0],cnt = 0 ; i < nums.length ; i++){
            if(cnt == 0){
                if(val != nums[i]) val = nums[i];
                cnt++;
            }else{
                if(val != nums[i])cnt--;
                else cnt++;
            }
        }
        return val;
    }
}
