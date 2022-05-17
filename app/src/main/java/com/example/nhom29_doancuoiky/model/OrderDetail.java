package com.example.nhom29_doancuoiky.model;

import java.io.Serializable;

public class OrderDetail implements Serializable {
    Integer id, amount;
    String name, price, image;

    public OrderDetail() {
    }

    public OrderDetail(Integer id, String name, Integer amount, String price, String image) {
        this.id = id;
        this.amount = amount;
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
