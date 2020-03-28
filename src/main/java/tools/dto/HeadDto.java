package tools.dto;

import java.util.Date;
import java.util.List;

public class HeadDto {
    private String arrivedOrderNo;
    private String detailId;

    private String expectedArriveTime;
    private String supplier;

    private String supplierName;
    private String operator;
    private String sourceType;
    private String arrivedOrg;
    private String arrivedDepotCode;

    private List<DetailDto> detailsItem;

    public String getArrivedOrderNo() {
        return arrivedOrderNo;
    }

    public void setArrivedOrderNo(String arrivedOrderNo) {
        this.arrivedOrderNo = arrivedOrderNo;
    }

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    public String getExpectedArriveTime() {
        return expectedArriveTime;
    }

    public void setExpectedArriveTime(String expectedArriveTime) {
        this.expectedArriveTime = expectedArriveTime;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getArrivedOrg() {
        return arrivedOrg;
    }

    public void setArrivedOrg(String arrivedOrg) {
        this.arrivedOrg = arrivedOrg;
    }

    public String getArrivedDepotCode() {
        return arrivedDepotCode;
    }

    public void setArrivedDepotCode(String arrivedDepotCode) {
        this.arrivedDepotCode = arrivedDepotCode;
    }

    public List<DetailDto> getDetailsItem() {
        return detailsItem;
    }

    public void setDetailsItem(List<DetailDto> detailsItem) {
        this.detailsItem = detailsItem;
    }
}
