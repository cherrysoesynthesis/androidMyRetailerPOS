package com.dcs.myretailer.app;

import java.util.ArrayList;

public class ViewModel {

    Integer id;
    String qty;
    String name;
    //    int image;
    String price;

    public ViewModel(Integer id, String qty, String name, String price) {
        this.id = id;
        this.qty = qty;
        this.name = name;
        this.price = price;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
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
}