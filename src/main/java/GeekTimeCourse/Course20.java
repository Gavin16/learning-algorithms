package GeekTimeCourse;

/**
 * @ClassName: Course20
 * @CopyRight: wufangmin
 * @Description: HashCode 计算
 * @Author: wufangmin
 * @Date: 2019/12/3 14:16
 * @Version: 1.0
 */
public class Course20 {

    public static void main(String[] args) {
        // hashcode 可能为负数
        int h = "dfshbf".hashCode();
        int hashCode = h ^ (h >>> 16);
        System.out.println(hashCode);
        // 通过位与计算hash 还有一个好处在于无需判断hashCode的正负,也即无需求绝对值
        System.out.println(hashCode & 15);
        System.out.println(hashCode % 16);

        // 测试两个DTO是否相同
//        TestDTO dto1 = new TestDTO("TestDTO","1.0","测试重写equals&HashCode方法");
//        TestDTO dto2 = new TestDTO("TestDTO","1.0","测试重写equals&HashCode方法");

        TestDTO d1 = new TestDTO();
        TestDTO d2 = new TestDTO();

        // 即使TestDTO 未定义hashCode 方法，仍然可以将native 的hashCode方法
        System.out.println(d1.hashCode());
    }

}
