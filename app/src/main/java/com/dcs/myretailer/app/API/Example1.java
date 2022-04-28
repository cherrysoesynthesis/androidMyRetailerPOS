package com.dcs.myretailer.app.API;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Example1 {

    @SerializedName("list_paymentmethods")
    @Expose
    private List<ListPaymentmethod> listPaymentmethods = null;

    public List<ListPaymentmethod> getListPaymentmethods() {
        return listPaymentmethods;
    }

    public void setListPaymentmethods(List<ListPaymentmethod> listPaymentmethods) {
        this.listPaymentmethods = listPaymentmethods;
    }

}