package tests.reviewJDK.math;

import java.math.BigDecimal;
import java.math.MathContext;
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



        // 设置精度后返回一个新的 BigDecimal对象
        BigDecimal decimal = new BigDecimal(12.1542);
        System.out.println(decimal.setScale(2,RoundingMode.HALF_UP));
        System.out.println(decimal);

        // 对于负数, BigDecimal 对象设置精度
        BigDecimal negDec = new BigDecimal(-123.789);
        BigDecimal negScaled = negDec.setScale(2,RoundingMode.HALF_UP);
        System.out.println(negScaled);
    }
}
