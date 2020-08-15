package leetCode.oneMoreHundred;


import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2020年8月13日
 * ==============================================================================
 * 46. 全排列
 * ==============================================================================
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
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
 * ==============================================================================
 * 93. 复原IP地址
 * ==============================================================================
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 *
 * 有效的 IP 地址正好由四个整数（每个整数位于 0 到 255 之间组成），整数之间用 '.' 分隔。
 *  
 *
 * 示例:
 *
 * 输入: "25525511135"
 * 输出: ["255.255.11.135", "255.255.111.35"]
 * ==============================================================================
 */
public class Day016 {

    public static void main(String[] args) {
        int[] nums = {};
        List<List<Integer>> permute = permute(nums);
        for(List<Integer> list : permute){
            for(Integer n : list){
                System.out.print(n + " ");
            }
            System.out.println();
        }


        // IP地址还原
        String ipAddr = "25525511135";
        List<String> strings = restoreIpAddresses(ipAddr);
        for(String str : strings){
            System.out.println(str);
        }

    }


    /**
     * @Title: 46. 全排列
     * @Version: 回溯算法实现
     * 假设输入num 长度为 N,则全排列的所有组合的情况为:
     * f(n) = {第一个数为num[0]|f(n-1)} U {第一个数为num[1] | f(n-1)} U ... U {第一个数为num[n-1] | f(n-1)}
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        dfsAddPermute(result,nums,len,0);
        return result;
    }

    /**
     * 深度优先遍历
     * @param nums
     * @param len
     * @param k
     * @return
     */
    private static void dfsAddPermute(List<List<Integer>> result, int[] nums, int len, int k) {
        if(k == len-1){
            List<Integer> list = new ArrayList<>();
            for(int n : nums){
                list.add(n);
            }
            result.add(list);
        }
        for(int i = k ; i < len ; i++){
            swap(nums, i, k);
            dfsAddPermute(result,nums,len,k+1);
            swap(nums, i, k);
        }
    }

    private static void swap(int[]nums , int i , int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    /**
     * @Title: 93. 复原IP地址
     * 定义dfs(s,i) 来深度优先搜索
     * 其中 s 代表ip地址的段数, i 代表第s段地址起始位置的下标
     * 那么在搜索时, 遍历的去寻找第s段地址的结束位置 j,然后判断以i,j 下标为首尾地址的数据段是否
     * 符合IP地址定义的范围,若符合则添加到列表中，若不符合则终止循环(因为再找下去i到j段内的数值只会越来越大)
     *
     *
     * @param s
     * @return
     */
    static int MAX_LEN= 4;
    static String[] segs = new String[4];
    static List<String> result = new ArrayList<>();
    public static List<String> restoreIpAddresses(String s) {
        dfs(s,0,0);
        return result;
    }

    private static void dfs(String s,int ipSegNum, int idStart){
        // idStart 等于 s.length() 已经成功将4段IP添加并且已经没有剩余的元素了
        if(ipSegNum == MAX_LEN){
            if(idStart == s.length()){
                String join = String.join(".", segs);
                result.add(join);
            }
            return;
        }else if(idStart == s.length()){
            return;
        }

        if(s.charAt(idStart) == '0'){
            segs[ipSegNum] = "0";
            dfs(s,ipSegNum+1,idStart+1);
        }

        int value = 0 ;
        for(int idEnd = idStart ; idEnd < s.length() ; idEnd++){
            value = value*10 + (s.charAt(idEnd) - '0');
            if(value > 0 && value <= 255){
                segs[ipSegNum] = String.valueOf(value);
                dfs(s,ipSegNum+1,idEnd+1);
            }else{
                break;
            }
        }
    }

}
