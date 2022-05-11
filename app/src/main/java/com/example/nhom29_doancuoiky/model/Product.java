package com.example.nhom29_doancuoiky.model;

import java.io.Serializable;

public class Product implements Serializable {
    int id,viewcount,category_id;
    float discount,price;
    String description,name,image;

    public Product() {
    }

    public Product(int id, int viewcount, int category_id, float discount, float price, String description, String name, String image) {
        this.id = id;
        this.viewcount = viewcount;
        this.category_id = category_id;
        this.discount = discount;
        this.price = price;
        this.description = description;
        this.name = name;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getViewcount() {
        return viewcount;
    }

    public void setViewcount(int viewcount) {
        this.viewcount = viewcount;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

