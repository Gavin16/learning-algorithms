package leetcode.oneMoreHundred;

/**
 * @className: Day048
 * @description:
 *
 * 队列应用
 *《622. 设计循环队列》
 * 设计你的循环队列实现。 循环队列是一种线性数据结构，其操作表现基于 FIFO（先进先出）原则并且队尾被连接在队首之后以形成一个循环。它也被称为“环形缓冲器”。
 * 循环队列的一个好处是我们可以利用这个队列之前用过的空间。在一个普通队列里，一旦一个队列满了，我们就不能插入下一个元素，即使在队列前面仍有空间。但是使用循环队列，我们能使用这些空间去存储新的值。
 * 你的实现应该支持如下操作：
 *
 * MyCircularQueue(k): 构造器，设置队列长度为 k 。
 * Front: 从队首获取元素。如果队列为空，返回 -1 。
 * Rear: 获取队尾元素。如果队列为空，返回 -1 。
 * enQueue(value): 向循环队列插入一个元素。如果成功插入则返回真。
 * deQueue(): 从循环队列中删除一个元素。如果成功删除则返回真。
 * isEmpty(): 检查循环队列是否为空。
 * isFull(): 检查循环队列是否已满。
 *
 *
 * 示例：
 *
 * MyCircularQueue circularQueue = new MyCircularQueue(3); // 设置长度为 3
 * circularQueue.enQueue(1);  // 返回 true
 * circularQueue.enQueue(2);  // 返回 true
 * circularQueue.enQueue(3);  // 返回 true
 * circularQueue.enQueue(4);  // 返回 false，队列已满
 * circularQueue.Rear();  // 返回 3
 * circularQueue.isFull();  // 返回 true
 * circularQueue.deQueue();  // 返回 true
 * circularQueue.enQueue(4);  // 返回 true
 * circularQueue.Rear();  // 返回 4
 *
 * @version: 1.0
 * @author: minsky
 * @date: 2022/5/9
 */
public class Day048 {


    public static void main(String[] args) {
        MyCircularQueue circularQueue = new MyCircularQueue(3); // 设置长度为 3
        System.out.println(circularQueue.enQueue(1));
        System.out.println(circularQueue.enQueue(2));
        System.out.println(circularQueue.enQueue(3));
        System.out.println(circularQueue.enQueue(4));
        System.out.println(circularQueue.Rear());
        System.out.println(circularQueue.isFull());
        System.out.println(circularQueue.deQueue());
        System.out.println(circularQueue.enQueue(4));
        System.out.println(circularQueue.Rear());
    }

    // 队列为空时 head == tail
    // 队列非空时 tail 指向下一个为空的元素
    static class MyCircularQueue {

        private int[] items;

        private int size;

        private int cnt;

        private int head;

        private int tail;

        public MyCircularQueue(int k) {
            items = new int[k+1];
            this.size = k;
            head = 0;
            tail = 0;
            cnt = 0;
        }

        public boolean enQueue(int value) {
            if((tail + 1) % (size + 1) == head) return false;
            // 数据接入队尾
            items[tail] = value;
            tail = (tail + 1) % (size + 1);
            cnt++;
            return true;
        }

        public boolean deQueue() {
            if(head == tail) return false;
            head = (head + 1) % (size + 1);
            cnt--;
            return true;
        }

        public int Front() {
            if(isEmpty()) return -1;
            return items[head];
        }

        public int Rear() {
            if(isEmpty()) return -1;
            int rearPos = (tail + size ) % (size + 1);
            return items[rearPos];
        }

        public boolean isEmpty() {
            return tail == head;
        }

        public boolean isFull() {
            // 循环队列默认最后元素无法使用
            return cnt == size;
        }
    }

}
