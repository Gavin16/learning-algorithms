package demos;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Demo1
 * @CopyRight: 百果科技
 * @Description:
 * @Author: wufangmin
 * @Date: 2019/12/12 16:16
 * @Version:
 */
public class Demo1 {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        list.add(9);
        list.add(2);
        list.add(4);

        // 浅拷贝
        List<Integer> list1 = new ArrayList<>(list);

        for(int i = 0 ; i < list.size() ;i++){
            System.out.println(list.get(i) == list1.get(i));
        }
    }
}
