package com.dcs.myretailer.app.Checkout;

public class CheckOut {
    private Integer Checkout_TotalQty;
    private String Checkout_Name;
    private String Checkout_TotalAmount ;
    private Integer Checkout_SubTotal ;

    public CheckOut(Integer checkout_TotalQty, String checkout_Name, String checkout_TotalAmount, Integer checkout_SubTotal) {
        Checkout_TotalQty = checkout_TotalQty;
        Checkout_Name = checkout_Name;
        Checkout_TotalAmount = checkout_TotalAmount;
        Checkout_SubTotal = checkout_SubTotal;
    }

    public Integer getCheckout_TotalQty() {
        return Checkout_TotalQty;
    }

    public void setCheckout_TotalQty(Integer checkout_TotalQty) {
        Checkout_TotalQty = checkout_TotalQty;
    }

    public String getCheckout_Name() {
        return Checkout_Name;
    }

    public void setCheckout_Name(String checkout_Name) {
        Checkout_Name = checkout_Name;
    }

    public String getCheckout_TotalAmount() {
        return Checkout_TotalAmount;
    }

    public void setCheckout_TotalAmount(String checkout_TotalAmount) {
        Checkout_TotalAmount = checkout_TotalAmount;
    }

    public Integer getCheckout_SubTotal() {
        return Checkout_SubTotal;
    }

    public void setCheckout_SubTotal(Integer checkout_SubTotal) {
        Checkout_SubTotal = checkout_SubTotal;
    }
}
