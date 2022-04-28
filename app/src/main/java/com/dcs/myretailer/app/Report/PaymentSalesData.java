package com.dcs.myretailer.app.Report;

public class PaymentSalesData {
    public int paymentid = 0;
    public String name = "";
    public int paycount = 0;
    public double payamount = 0d;
    public int cancelcount = 0;
    public double cancelamount = 0d;

    public PaymentSalesData(int paymentid, String name){
        this.paymentid = paymentid;
        this.name = name;
    }


}

