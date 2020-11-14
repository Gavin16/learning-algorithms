package geektime;

import datastruct.Heap;
import utils.ArrayUtil;
import utils.HeapUtil;

/**
 * @ClassName: Course28
 * @Description: 堆的实现及应用
 *
 * 一般的认为具有以下特性的二叉树，是一个堆结构：
 *  (1) 二叉树中的每一个节点大于等于(或小于等于)它的左右子节点
 *  (2) 二叉树是完全二叉树
 *
 * 由于完全二叉树可以很方便的采用数组实现,因此这里堆的实现与应用都采用数组(下表从0开始的数组)
 *
 * @Author: wufangmin
 * @Date: 2019/12/12 10:03
 * @Version: 1.0
 */
public class Course28 {

    public static void main(String[] args) {
        int[] data = {3,2,5,7,6,9,8,4};
        HeapUtil.buildHeap(data);
        ArrayUtil.showArray(data);

        HeapUtil.heapSort(data);
        ArrayUtil.showArray(data);

        Heap heap = new Heap(10);
        heap.insertIntoHeap(11);
        heap.insertIntoHeap(3);
        heap.insertIntoHeap(13);
        heap.insertIntoHeap(4);
        heap.insertIntoHeap(7);
        heap.insertIntoHeap(9);
        heap.insertIntoHeap(8);

        HeapUtil.printHeap(heap);
        heap.deleteTop();
        HeapUtil.printHeap(heap);
    }


}
