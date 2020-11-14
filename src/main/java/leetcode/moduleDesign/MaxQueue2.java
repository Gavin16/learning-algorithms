package leetcode.moduleDesign;

import java.util.LinkedList;

/**
 * MaxQueue 优化版本： 使用LinkedList保存最大值，原理基于如下：
 *
 * 每次向queue中添加元素val时，都会将val该元素保存到比它大的元素后，这中间如果有比它小的元素，可以直接删除这些元素，因为这些被删除
 * 元素在pop 出队列之前，这个新添加的val将一直是优先于他们的最大值候选，当他们被pop之后，也就无需再用到他们
 *
 */
public class MaxQueue2 {

    private LinkedList<Integer> queue;

    private LinkedList<Integer> max;

    public MaxQueue2() {
        queue = new LinkedList<>();
        max = new LinkedList<>();
    }

    public int max_value() {
        if(queue.isEmpty()) return -1;
        Integer first = max.peekFirst();
        return first == null ? -1 : first;
    }

    public void push_back(int value) {
        queue.add(value);
        while(max.size() > 0 && max.peekLast() < value){
            max.removeLast();
        }
        max.add(value);
    }

    public int pop_front() {
        if(queue.isEmpty()) return -1;
        Integer poll = queue.removeFirst();

        if(poll.intValue() == max.peekFirst()){
            max.removeFirst();
        }
        return poll;
    }
}
