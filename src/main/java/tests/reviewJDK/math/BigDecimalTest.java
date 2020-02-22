package tests.reviewJDK.math;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @ClassName: BigDecimalTest
 * @CopyRight: wufangmin
 * @Description: BigDecimal 常用功能测试
 * @Author: wufangmin
 * @Date: 2020/1/8 15:10
 * @Version: 1.0
 */
public class BigDecimalTest {

    public static void main(String[] args) {

        // BigDecimal 中 使用MathContext



        // 设置精度后返回一个新的 BigDecimal对象; HALF_UP 和 HALF_DOWN 的是针对精度scale 之后所有的值来取舍的
        // 譬如精度为小数点后两位，则若第三位开始所有后面的值正好等于 0.005 则 HALF_DOWN 舍去该0.005 ; 而 HALF_UP 则让该0.005 提升为0.01
        BigDecimal decimal = new BigDecimal("12.15500");
        System.out.println(decimal.setScale(2,RoundingMode.HALF_DOWN));
        System.out.println(decimal.setScale(2,RoundingMode.HALF_UP));
        System.out.println(decimal);

        // 对于负数, BigDecimal 对象设置精度
        BigDecimal negDec = new BigDecimal(-123.789);
        BigDecimal negScaled = negDec.setScale(2,RoundingMode.HALF_UP);
        System.out.println(negScaled);


        // BigDecimal 构造方法使用 float 或者 double 传参容易丢失精度
        // 因此这里使用 String 类型传参
        BigDecimal decim1 = new BigDecimal("0");
        System.out.println(decim1);
        BigDecimal decim2 = new BigDecimal("21.1");

        System.out.println(decim1.add(decim2));
        BigDecimal decim3 = decim1.add(decim2);

        // 精确到小数点后三位,设置后需要保存
        decim3 = decim3.setScale(3, RoundingMode.HALF_UP);
        System.out.println(decim3);
    }
}
