package com.dcs.myretailer.app.API;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListPaymentmethod {

    @SerializedName("ID")
    @Expose
    private String id;
    @SerializedName("PaymentCode")
    @Expose
    private String paymentCode;
    @SerializedName("Full")
    @Expose
    private String full;
    @SerializedName("Display")
    @Expose
    private String display;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    public String getFull() {
        return full;
    }

    public void setFull(String full) {
        this.full = full;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

}