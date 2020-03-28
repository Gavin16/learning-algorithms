package tools.dto;

import java.math.BigDecimal;

public class DetailDto {
    private String detailId;
    private String goodsCode;
    private String goodsSpec;

    private String purOrderNo;
    private String arrivedOrg;

    private String arrivedDepotCode;
    private double arrivedItem;
    private double arrivedNumber;
    private double price;
    private String uom;


    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsSpec() {
        return goodsSpec;
    }

    public void setGoodsSpec(String goodsSpec) {
        this.goodsSpec = goodsSpec;
    }

    public String getPurOrderNo() {
        return purOrderNo;
    }

    public void setPurOrderNo(String purOrderNo) {
        this.purOrderNo = purOrderNo;
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

    public double getArrivedItem() {
        return arrivedItem;
    }

    public void setArrivedItem(double arrivedItem) {
        this.arrivedItem = arrivedItem;
    }

    public double getArrivedNumber() {
        return arrivedNumber;
    }

    public void setArrivedNumber(double arrivedNumber) {
        this.arrivedNumber = arrivedNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }
}
