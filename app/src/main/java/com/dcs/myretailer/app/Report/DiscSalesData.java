package com.dcs.myretailer.app.Report;

public class DiscSalesData {
    public int discID = -1;

    public String name = "";
    public double rate = 0;
    public boolean[] option = new boolean[0];
    public double amount = 0;
    public int count = 0;

    public DiscSalesData(int discID, String name, double rate, boolean[] option){
        this.discID = discID;
        this.name = name;
        this.rate = rate;
        this.option = option;
    }

}
