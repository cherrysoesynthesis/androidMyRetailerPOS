package com.dcs.myretailer.app.Report;

public class ReferSalesData {

    //referid, name, paidqty, paidamountnotax, paidamounttax, paidtax, cancelqty, cancelamount
    public int referID = 0;
    public String name = "";

    public int paidbill = 0;
    public double paidqty = 0;
    public double paidamountnotax = 0;
    public double paidamounttax = 0;
    public double paidtax = 0;
    public double paidround = 0;

    public int cancelbill = 0;
    public double cancelqty = 0;
    public double cancelamtnotax = 0;
    public double cancelamttax = 0;
    public double canceltax = 0;

    public double discamount = 0;
    public double surchgamt = 0;


    public ReferSalesData(int referid, String name){
        this.referID = referid;
        this.name = name;
    }



}
