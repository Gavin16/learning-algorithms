package basement;

import utils.ArrayUtil;

/**
 * @Class: Quick
 * @Description: 快速排序的常规实现
 * @Author: Minsky
 * @Date: 2019/7/29 13:24
 * @Version: v1.0
 *
 * 快速排序的几点思考：
 *  (1) 快速排序的实现主要依赖 partition方法的实现，其中partition方法实现看着简单实则有以下几点需要注意
 *         1.首尾端在对元素做遍历时在同一层循环里既要遍历比较完了之后还可能需要做交换，因此千万不要写成 i++,j-- 而应该保证发现元素大于枢轴
 *           或者小于枢轴时的下标与 交换时的下标相同
 *         2. 由于涉及到两个指针对数组做遍历，且遍历时需要与一个相同的枢轴做比较，因此(内层)循环的终止条件需要明确，容易想到的终止条件是前面的
 *           指针与后面的指针相遇时停止循环，但其实除非第一次做遍历(中间没有做过元素交换) 否者在条件成立时
 *         3. 结束外层循环时,需要保证指针 j的位置就是枢轴需要与之交换的位置，这一点可以通过内部的两层循环以及交换执行的先后顺序来保证：
 *              分三种情况讨论：
 *                  ① 元素是倒序有序的，这时内层的第一个循环将一直遍历直到最后一个元素 条件 i == hi 成立，退出；然后第二层循环第一个条件就不成立
 *                          这时候发现 i==j 外层循环将要终止，这时恰恰枢轴是最大的元素，j是最后一个元素，因此交换元素j 和枢轴的位置得到结果
 *                  ② 元素是正序有序的，这时内层第一个循环套件不成立，内层第一个循环将一直执行 直到 j == lo , 完了后发现 j < i, 退出第二层循环
 *                          这时j的位置就是枢轴的位置，因此在外层循环结束后 执行的是枢轴自己和自己交换，这也是符合算法要求的
 *                  ③ 元素是无序的， 无序则意味着内层的两个循环一定会在前后的指针相遇前发生元素的交换，这里的相遇是分别通过与枢轴的比较发生的
 *                          这时内存的两个循环每一个都不会走完全程(完整的数组)而是在 i 找到的一个比枢轴大的元素后 j 找到了该元素的前一个元素，
 *                          j的这个元素比枢轴小，而i的这个元素比枢轴大，这个时候正好满足i = j + 1, 且j右边的元素都比枢轴大，i左边的元素都比枢轴小，
 *                          显然枢轴应该被交换到j的这个位置
 *         4. 除非对以上有深刻认识, 否者仍难写出准确的快速排序
 *
 *
 */
public class Quick {

    public static void main(String[]args){
        int[] rands = ArrayUtil.randValueArray(11);
        ArrayUtil.showArray(rands);

        sort(rands);
        ArrayUtil.showArray(rands);
    }

    public static void sort(int[] arr) {
        sort(arr, 0 ,arr.length-1);
    }

    public static void sort(int[] arr,int start , int end){
        if(start >= end) return ;

        int p = partition(arr,start,end);

        sort(arr,0,p-1);
        sort(arr,p+1,end);
    }

    /**
     * 以第一个元素为枢轴，找出该元素的在数组 arr 中 lo 到 hi 的范围内应该被放在arr中的下标 j
     * @param arr
     * @param lo
     * @param hi
     * @return 枢轴的目标下标
     */
    static int partition(int[]arr , int lo, int hi){

        int i = lo;
        int j = hi + 1;
        int pv = arr[lo];

        while(true){
            while(arr[++i] < pv){
                // 仅第一次遍历时条件才可能成立,如果第二次最多遍历到 i==j 时while的条件就不成立了, 因为做过一次交换将较大元素放到了该处
                if(i == hi) break;
            }
            // 当前面的循环由于 i==j 而结束时，此处循环执行判断之后也会立刻结束(因为执行条件不再成立)，最终 j = i -1 ; 这时外层循环也
            // 结束，exchange(arr,lo,j) 交换时正好将枢轴放到第一个大于它的值的前面
            while(arr[--j] > pv){
                if(j == lo) break;
            }

            if(i >= j) break;

            // 前面大于枢轴的元素 与 后面小于枢轴的元素交换
            exchange(arr,i,j);
        }

        exchange(arr,lo,j);
        return j;
    }


    private static void exchange(int[] a, int l , int r){
        int temp = a[l];
        a[l] = a[r];
        a[r] = temp;
    }
}
