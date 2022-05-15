package com.example.nhom29_doancuoiky.model;

import com.example.nhom29_doancuoiky.response.ProductSizeApiResponse;

import java.io.Serializable;

public class Cart implements Serializable {
    private String source_img;
    private String name;
    private Integer amount;
    private String size;
    private Integer price;
    private Integer totalprice;
    private ProductSizeApiResponse productSizeApiResponse;

    public ProductSizeApiResponse getProductSizeApiResponse() {
        return productSizeApiResponse;
    }

    public void setProductSizeApiResponse(ProductSizeApiResponse productSizeApiResponse) {
        this.productSizeApiResponse = productSizeApiResponse;
    }

    public Integer getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Integer totalprice) {
        this.totalprice = totalprice;
    }


    public Cart(String source_img, String name, Integer amount, String size, Integer price) {
        this.source_img = source_img;
        this.name = name;
        this.amount = amount;
        this.size = size;
        this.price = price;
        this.totalprice=price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSource_img() {
        return source_img;
    }

    public void setSource_img(String source_img) {
        this.source_img = source_img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
