package com.example.nhom29_doancuoiky.response;

import java.io.Serializable;

public class CartApiResponse implements Serializable {
    private Long id;
    private Integer quantity;
    private Float totalMoney;
    //productsize
    private String productSizeName;
    private String sizeCode;
    //product
    private String linkImgProd;
    private String nameProd;
    private Long productId;

    private String userName;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getSizeCode() {
        return sizeCode;
    }

    public void setSizeCode(String sizeCode) {
        this.sizeCode = sizeCode;
    }

    public String getNameProd() {
        return nameProd;
    }

    public void setNameProd(String nameProd) {
        this.nameProd = nameProd;
    }

    public String getLinkImgProd() {
        return linkImgProd;
    }

    public void setLinkImgProd(String linkImgProd) {
        this.linkImgProd = linkImgProd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getProductSizeName() {
        return productSizeName;
    }

    public void setProductSizeName(String productSizeName) {
        this.productSizeName = productSizeName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
