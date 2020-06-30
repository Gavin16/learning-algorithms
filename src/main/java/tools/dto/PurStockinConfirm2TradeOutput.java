package tools.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName: PurStockinConfirm2TradeOutput
 * @CopyRight: 百果科技
 * @Description:
 * @Author: wufangmin
 * @Date: 2020/4/17 上午 11:30
 * @Version:
 */
@Data
public class PurStockinConfirm2TradeOutput {

    private String balanceOrgCode;

    private String balanceOrgDesc;

    private String orgCode;

    private String orgDesc;

    private String remark;

    private String shipDate;

    private String shipNo;

    private String vendorCode;

    private String vendorName;

    private BigDecimal shipAmt;

    private String isInvoiced;

    private BigDecimal strikeAmt;

    private String purchaseType;
}
