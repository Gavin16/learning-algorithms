package leetCode;

/**
 * @Class: HappyNumber
 * @Description:
 *
 * 《leetCode 202》
 *
 * 编写一个算法来判断一个数是不是“快乐数”。
 *
 * 一个“快乐数”定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，
 * 也可能是无限循环但始终变不到 1。如果可以变为 1，那么这个数就是快乐数。
 *
 *
 *
 * 心得:  ① 当对数组做无穷列举时，判断数组中是否包含"环"可以使用快慢指针法;
 *       ② 如果需要在while循环外做一次循环中的操作，可以使用do-while替换while循环
 *
 * @Author: Minsky
 * @Date: 2019/10/1 18:16
 * @Version: v1.0
 */
public class HappyNumber {

    public static void main(String[]args){
        System.out.println(isHappy(3));
    }


    /**
     * 使用快慢指针实现
     * @param n
     * @return
     */
    public static boolean isHappy(int n) {
        int slow = n;
        int fast = n;
        do{
            slow = squareSum(slow);
            fast = squareSum(fast);
            fast = squareSum(fast);
        }while(fast != slow);

        if(fast == 1)
            return true;
        return false;
    }

    // 计算输入各位的平方和
    private static int squareSum(int temp) {
        int sum = 0, n = 0;
        while(temp != 0){
            n = temp % 10;
            temp =temp / 10;
            sum += n*n;
        }
        return sum;
    }


}
