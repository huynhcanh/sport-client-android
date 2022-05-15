package com.example.nhom29_doancuoiky.response;

import java.io.Serializable;

public class OrderDetailApiResponse implements Serializable {

    private String nameProd;
    private Integer quantity;
    private Float totalMoney;
    private String linkImgProd;

    public String getNameProd() {
        return nameProd;
    }

    public void setNameProd(String nameProd) {
        this.nameProd = nameProd;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Float totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getLinkImgProd() {
        return linkImgProd;
    }

    public void setLinkImgProd(String linkImgProd) {
        this.linkImgProd = linkImgProd;
    }
}
