package algorithmsContest.charpt4;

/**
 * @className: BinaryIndexTree
 * @description:
 *
 * 树状数组定义
 *
 * @version: 1.0
 * @author: minsky
 * @date: 2023/5/28
 */
public class BinaryIndexTree {


    public static void main(String[] args) {

        BinaryIndexTree instance = new BinaryIndexTree();
        System.out.println(instance.lowBit(6));
        int[] a = {0,4,5,6,7,8,9,10,11,12,13};

        for(int i = 1; i <= 10; i++){
            instance.update(i, a[i]);
        }
        System.out.println(instance.sum(8) - instance.sum(4));
        instance.update(5, 100);
        System.out.println(instance.sum(8) - instance.sum(4));
        System.out.println(instance.tree[5]);
        System.out.println(instance.tree[6]);
        System.out.println(instance.tree[7]);
        System.out.println(instance.tree[8]);
    }

    int N = 1000;
    int[] tree = new int[N];
    void update(int x, int d){
        while(x < N){
            tree[x] += d;
            x += lowBit(x);
        }
    }
    int lowBit(int x){
        return x & (-x);
    }

    int sum(int x){
        int ans = 0;
        while(x > 0){
            ans += tree[x];
            x -= lowBit(x);
        }
        return ans;
    }


}
