package leetcode;


import java.util.*;

/**
 * 《LeetCode 442. 数组中重复的数据》
 *  给定一个整数数组 a，其中1 ≤ a[i] ≤ n （n为数组长度）, 其中有些元素出现两次而其他元素出现一次。
 *
 * 找到所有出现两次的元素。
 * 你可以不用到任何额外空间并在O(n)时间复杂度内解决这个问题吗？
 *
 * 示例：
 * 输入:
 * [4,3,2,7,8,2,3,1]
 *
 * 输出:
 * [2,3]
 *
 */
public class FindDuplicates {

    public static void main(String[] args) {
        // 10是初始大小，0.75是装载因子，true是表示按照访问时间排序,不指定则代表按照插入顺序排序
        HashMap<Integer, Integer> m = new LinkedHashMap<>(10, 0.75f);
        m.put(3, 11);
        m.put(1, 12);
        m.put(5, 23);
        m.put(2, 22);

        m.put(3, 26);
        m.get(5);

        for (Map.Entry e : m.entrySet()) {
            System.out.println(e.getKey());
        }
    }

//    public static void main(String[] args) {
//        int[] nums = {4,3,2,7,8,2,3,1};
//        System.out.println(findDuplicates(nums));
//    }

    /**
     * 利用好前提条件： 给定一个整数数组 a，其中1 ≤ a[i] ≤ n （n为数组长度）
     * 做法： 遍历数组时,对每一个元素值 - 1所在的位置作标记(取反操作),当发现某个元素值-1 所在位置的元素为 负时,则可认为该元素曾经出现过
     * @param nums
     * @return
     */
    static public List<Integer> findDuplicates(int[] nums) {
        List<Integer> list = new ArrayList<>();

        for(int i = 0 ; i < nums.length ; i++){
            if(nums[Math.abs(nums[i]) - 1] < 0){
                list.add(Math.abs(nums[i]));
            }else{
                nums[Math.abs(nums[i]) - 1] *= -1;
            }
        }
        return list;
    }


    /**
     *
     * 《 LeetCode 287. 寻找重复数》
     * 大小为 n+1 的数组，元素大小在[1:n] 之间，只有一个元素且可能会重复出现多次，找出该重复的元素
     * 不能更改原数组
     * 只能使用额外的O(1) 的空间
     * 时间复杂度小于O(n^2)
     * 数组中只有一个重复的数字，但它可能不止重复出现一次
     *
     * 高票题解：
     * 快慢指针，一个时间复杂度为O(N)的算法。
     * 其一，对于链表问题，使用快慢指针可以判断是否有环。
     * 其二，本题可以使用数组配合下标，抽象成链表问题。但是难点是要定位环的入口位置。
     * 举个例子：nums = [2,5, 9 ,6,9,3,8, 9 ,7,1]，构造成链表就是：2->[9]->1->5->3->6->8->7->[9]，也就是在[9]处循环。
     * 其三，快慢指针问题，会在环内的[9]->1->5->3->6->8->7->[9]任何一个节点追上，不一定是在[9]处相碰，事实上会在7处碰上。
     * 其四，必须另起一个for循环定位环入口位置[9]。这里需要数学证明。
     *
     * 对“其四”简单说明一下，既然快慢指针在环内的某处已经相碰了。那么，第二个for循环遍历时，res指针还是在不停的绕环走，但是必定和i指针在环入口处相碰。
     *
     *
     * @param nums
     * @return
     */
    static public int findDuplicate(int[] nums){
        return -1;
    }
}
