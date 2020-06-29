package tools.dto;

public class ResponseToTrade {
    private String invoiceNo;
    private String vendorCode;
    private String orderArrDetailId;
    private String orderSeqno;
    private String goodsCode;
    private String goodsOrigin;
    private String dutyOrgCode;
    private Integer status;


    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public String getOrderArrDetailId() {
        return orderArrDetailId;
    }

    public void setOrderArrDetailId(String orderArrDetailId) {
        this.orderArrDetailId = orderArrDetailId;
    }

    public String getOrderSeqno() {
        return orderSeqno;
    }

    public void setOrderSeqno(String orderSeqno) {
        this.orderSeqno = orderSeqno;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsOrigin() {
        return goodsOrigin;
    }

    public void setGoodsOrigin(String goodsOrigin) {
        this.goodsOrigin = goodsOrigin;
    }

    public String getDutyOrgCode() {
        return dutyOrgCode;
    }

    public void setDutyOrgCode(String dutyOrgCode) {
        this.dutyOrgCode = dutyOrgCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
