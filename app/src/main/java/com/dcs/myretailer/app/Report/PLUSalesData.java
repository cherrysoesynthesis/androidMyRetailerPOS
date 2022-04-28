package com.dcs.myretailer.app.Report;

public class PLUSalesData {
    //pluid, name, unitprice, paidqty, paidamountnotax, paidamounttax, paidtax, cancelqty, cancelamount, deptID, deptName
    public int pluid = 0;
    public String name = "";
    public double unitprice =0;
    //public double paidqty = 0;
    public int paidqty = 0;
    public double paidamountnotax = 0;
    public double paidamounttax = 0;
    public double paidtax = 0;
    public double cancelqty = 0;
    public double cancelamtnotax = 0;
    public double cancelamttax = 0;
    public double canceltax = 0;
    public double status = 0;

    public double discamount = 0;
    public double surchgamt = 0;


    public int deptid = -1;
    public String deptname = "";

    public PLUSalesData(int pluid, String name, double unitprice, int deptid, String deptname){
        this.pluid = pluid;
        this.name = name;
        this.unitprice = unitprice;
        this.deptid = deptid;
        this.deptname = deptname;
    }


}
