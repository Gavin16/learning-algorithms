package leetCode;

/**
 * @Class: MyCircularDeque
 * @Description:
 *
 * 《GeekTime -- practice day02》
 * 641. 设计循环双端队列
 * 设计实现双端队列。
 * 你的实现需要支持以下操作：
 *
 * MyCircularDeque(k)：构造函数,双端队列的大小为k。
 * insertFront()：将一个元素添加到双端队列头部。 如果操作成功返回 true。
 * insertLast()：将一个元素添加到双端队列尾部。如果操作成功返回 true。
 * deleteFront()：从双端队列头部删除一个元素。 如果操作成功返回 true。
 * deleteLast()：从双端队列尾部删除一个元素。如果操作成功返回 true。
 * getFront()：从双端队列头部获得一个元素。如果双端队列为空，返回 -1。
 * getRear()：获得双端队列的最后一个元素。 如果双端队列为空，返回 -1。
 * isEmpty()：检查双端队列是否为空。
 * isFull()：检查双端队列是否满了。
 * 示例：
 *
 * MyCircularDeque circularDeque = new MycircularDeque(3); // 设置容量大小为3
 * circularDeque.insertLast(1);			        // 返回 true
 * circularDeque.insertLast(2);			        // 返回 true
 * circularDeque.insertFront(3);			        // 返回 true
 * circularDeque.insertFront(4);			        // 已经满了，返回 false
 * circularDeque.getRear();  				// 返回 2
 * circularDeque.isFull();				        // 返回 true
 * circularDeque.deleteLast();			        // 返回 true
 * circularDeque.insertFront(4);			        // 返回 true
 * circularDeque.getFront();				// 返回 4
 *  
 *  
 *
 * 提示：
 *
 * 所有值的范围为 [1, 1000]
 * 操作次数的范围为 [1, 1000]
 * 请不要使用内置的双端队列库。
 *
 *
 *
 * @Author: Minsky
 * @Date: 2020/2/2 21:39
 * @Version: v1.0
 */
public class MyCircularDeque {


    public static void main(String[] args) {
        MyCircularDeque deque = new MyCircularDeque(5);

        deque.insertFront(7);
        deque.insertLast(0);
        int front = deque.getFront();
        boolean b = deque.insertLast(3);
        deque.getFront();
        deque.insertFront(9);
        deque.getRear();
        deque.getFront();
        deque.getFront();
        deque.deleteLast();
        deque.getRear();
    }

    // 方案一  使用数组+首尾双指针实现

    private int[] nums;

    // 用首不用尾， 往头部插入 nums[head--] 往尾部插入 nums[++tail]
    private int head = 0;
    private int tail = 0;

    private int cap;
    private int size = 0;


    /** Initialize your data structure here. Set the size of the deque to be k. */
    public MyCircularDeque(int k) {
        if(k > 0)
            nums = new int[k];
        else
            nums = new int[4];
        cap = nums.length;
    }

    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if(size == cap) return false;
        if(head < 0){
            // 数据向后移位一个，由于size < cap ,因此tail 肯定小于 size
            for(int i = tail ; i >=0; i--){
                nums[i+1] = nums[i];
            }
            nums[0] = value;
            tail++;
        }else{
            nums[head--] = value;
        }
        size ++;
        return true;
    }

    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if(size == cap) return false;
        if(tail == cap-1){
            // 若队尾已经到数组末尾，则将队列所有元素移动到数组最前端
            if(head > -1){
                for(int k = head + 1; k <= tail ; k++){
                    nums[k - head - 1] = nums[k];
                }
                head = -1;
                tail = tail - head - 1;
            }

        }
        nums[++tail] = value;
        size++;
        return true;
    }

    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if(size == 0) return false;
        head++;size--;
        return true;
    }

    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if(size == 0) return false;
        tail--;size--;
        return true;
    }

    /** Get the front item from the deque. */
    public int getFront() {
        if(size == 0) return -1;
        return nums[head+1];
    }

    /** Get the last item from the deque. */
    public int getRear() {
        if(size == 0) return -1;
        return nums[tail-1];
    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return size == 0 ? true : false;
    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return size == cap ? true : false;
    }
}
