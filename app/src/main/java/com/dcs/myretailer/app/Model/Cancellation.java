package com.dcs.myretailer.app.Model;

public class Cancellation {
    String quantity;
    String AMTNett;
    String TaxTotal;
    String AMTSurcharge;

    public Cancellation() {
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

    public String getTaxTotal() {
        return TaxTotal;
    }

    public void setTaxTotal(String taxTotal) {
        TaxTotal = taxTotal;
    }

    public String getAMTSurcharge() {
        return AMTSurcharge;
    }

    public void setAMTSurcharge(String AMTSurcharge) {
        this.AMTSurcharge = AMTSurcharge;
    }
}
