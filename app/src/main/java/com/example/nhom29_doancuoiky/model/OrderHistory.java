package com.example.nhom29_doancuoiky.model;

import java.io.Serializable;

public class OrderHistory implements Serializable {
    Integer id;
    String orderDate;
    Float totalMoney;

    public OrderHistory() {
    }

    public OrderHistory(Integer id, String orderDate, Float totalMoney) {
        this.id = id;
        this.orderDate = orderDate;
        this.totalMoney = totalMoney;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Float getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Float totalMoney) {
        this.totalMoney = totalMoney;
    }
}
