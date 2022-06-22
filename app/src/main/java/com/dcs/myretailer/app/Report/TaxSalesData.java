package com.dcs.myretailer.app.Report;

public class TaxSalesData {
    public int taxID = 0;
    public String name = "";
    public double rate = 0;
    public int type = 0;
    public int paidcount = 0;
    public double paidamt = 0;
    public double paidtaxed = 0;
    public int cancelcount = 0;
    public double cancelamt = 0;
    public double canceltaxed = 0;

    public TaxSalesData(int taxID, String name,double rate, int type){
        this.taxID = taxID;
        this.name = name;
        this.rate = rate;
        this.type = type;
    }
}
