package com.dcs.myretailer.app;

public class Refund {
    String billRefund;
    String quantity;
    String AMTNett;
    String AMTSurcharge;

    public Refund() {
    }

    public String getBillRefund() {
        return billRefund;
    }

    public void setBillRefund(String billRefund) {
        this.billRefund = billRefund;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getAMTNett() {
        return AMTNett;
    }

    public void setAMTNett(String AMTNett) {
        this.AMTNett = AMTNett;
    }

    public String getAMTSurcharge() {
        return AMTSurcharge;
    }

    public void setAMTSurcharge(String AMTSurcharge) {
        this.AMTSurcharge = AMTSurcharge;
    }
}
