package datastruct;

/**
 * @ClassName: Heap
 * @Description: 数组实现堆
 * @Author: wufangmin
 * @Date: 2019/12/12 11:25
 * @Version: 1.0
 */
public class Heap {

    private int[] data;
    private int cap;
    private int count;

    public Heap(int cap){
        this.data = new int[cap];
        this.cap = cap;
        this.count = 0;
    }

    /** 对插入元素做从下至上的堆化 */
    public int insertIntoHeap(int num){
        if(count == cap) return -1;
        data[count] = num;
        int ret = count;
        // 堆化
        int i = count++;
        while(i/2 >= 0 && data[i/2] < data[i]){
            swap(data,i/2,i);
            i = i/2;
        }
        return ret;
    }


    /**
     * 删除并返回堆顶元素
     * @return
     */
    public int deleteTop(){
        // 删除堆顶元素
        if(count == 0){
            System.out.println("堆中没有元素!!");
            return Integer.MIN_VALUE;
        }
        count--;
        int ret = data[count];
        swap(0,count);
        heapify(count,0);
        return ret;
    }

    private void swap(int a, int b){
        swap(data,a,b);
    }

    /** 交换数组中两个指定下表的元素 */
    private void swap(int[] data, int k, int l) {
        int temp = data[k];
        data[k] = data[l];
        data[l] = temp;
    }

    /**
     * 堆中某元素在指定范围(n) 内的堆化
     * @param n
     * @param k
     */
    private void heapify(int n, int k){
        int maxPos = k;
        while(true){
            if((2*k + 1) < n && data[k] < data[2*k+1])
                maxPos = 2*k + 1;
            if((2*k + 2) < n && data[maxPos] < data[2*k + 2] )
                maxPos = 2*k + 2;
            if(maxPos == k) break;
            swap(data,k,maxPos);
            k = maxPos;
        }
    }

    public int[] getData() {
        return data;
    }

    public int getCap() {
        return cap;
    }

    public int getCount() {
        return count;
    }
}
