package problems;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * 京东白条综合年化利率计算
 * 月利率: 1%
 * 分12月返还
 *
 * 以实际欠款金额 加权月份， 统计全年12个月的金额.月 ，以统计结果/12 代表实际借一年的金额数
 *
 */
public class JDBaiTiaoInterestRate {

    // 银行年化利率
    private static BigDecimal bankYearRate = new BigDecimal("0.04");


    /**
     * 版本1：存在问题: 利息也是分期返还的，假如累积总还款利息是 a 元，那么该 a 元是要大于实际还款总利息的 a 元
     * 版本2：增加实际还款利息的迭代计算，累积计算每个月利息等到一年后再还时加上利息的利息得到
     */
    public static void main(String[] args) {
        BigDecimal totalRentAmt = new BigDecimal("12000");
        BigDecimal monthRefund = totalRentAmt.divide(new BigDecimal("12"),2, RoundingMode.HALF_UP);

        //版本1： 表面利息
        BigDecimal monthlyInterest = totalRentAmt.multiply(new BigDecimal("0.01"));
        BigDecimal totalInterest  = monthlyInterest.multiply(new BigDecimal("12"));
        //版本2： 实际利息
        BigDecimal realInterest = calculateRealInterest(monthlyInterest);



        BigDecimal floatRentLeftAmt = new BigDecimal("12000");
        BigDecimal amtMulMonth = BigDecimal.ZERO;
        for(int i = 0 ; i < 12 ; i++){
            amtMulMonth = amtMulMonth.add(floatRentLeftAmt);
            floatRentLeftAmt = floatRentLeftAmt.subtract(monthRefund);
        }
        // 实际金额*月  数量
        System.out.println(amtMulMonth);
        // 这一年的实际借款金额: 借原金额数的钱分期还等价于借这个金额的钱借一整年
        BigDecimal realAmt = amtMulMonth.divide(new BigDecimal("12"),2,RoundingMode.HALF_UP);
        System.out.println(realAmt);

        // 实际利率 = 实际利息/实际借款金额
        BigDecimal realRate1 = totalInterest.divide(realAmt,4,RoundingMode.HALF_UP);
        BigDecimal realRate2 = realInterest.divide(realAmt,4,RoundingMode.HALF_UP);
        System.out.println("实际年化利率1: " + realRate1);
        System.out.println("实际年化利率2: " + realRate2);
    }

    // 计算实际利息，以年化利率 4% 计算
    private static BigDecimal calculateRealInterest(BigDecimal monthlyInterest) {
        BigDecimal bankMonthRate = bankYearRate.divide(new BigDecimal("12"), 6, RoundingMode.HALF_UP);
        BigDecimal realInterest = BigDecimal.ZERO;

        for(int i = 0 ; i < 12 ; i++){
            BigDecimal currInterestInter = bankMonthRate.multiply(new BigDecimal(i)).multiply(monthlyInterest);
            realInterest = realInterest.add(monthlyInterest).add(currInterestInter);
        }
        return realInterest;
    }

}


