package com.example.nhom29_doancuoiky.model;


import java.io.Serializable;

public class User implements Serializable {
    Integer id;
    String email, phone, name, password;

    public User() {
    }

    public User(Integer id, String email, String phone, String name, String password) {
        this.id = id;
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

