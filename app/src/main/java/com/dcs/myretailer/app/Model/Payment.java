package com.dcs.myretailer.app.Model;

public class Payment {
    public String PaymentTypeName;
    String PaymentTypeAmount;

    public Payment() {
    }

    public String getPaymentTypeName() {
        return PaymentTypeName;
    }

    public void setPaymentTypeName(String paymentTypeName) {
        PaymentTypeName = paymentTypeName;
    }

    public String getPaymentTypeAmount() {
        return PaymentTypeAmount;
    }

    public void setPaymentTypeAmount(String paymentTypeAmount) {
        PaymentTypeAmount = paymentTypeAmount;
    }
}
