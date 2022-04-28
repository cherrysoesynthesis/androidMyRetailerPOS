package com.dcs.myretailer.app.Report;

public class Sales {
    Integer SalesID;
    String BillNo;
    String GrossSales;
    String TotalItemizedDiscount;
    String TotalBillDiscount;
    String GrossTotal;
    String ServiceCharge;
    String Taxes;
    String TotalNettSales;

    public Sales(String billNo) {
        BillNo = billNo;
    }

    //    public Sales(Integer salesID, String billNo, String grossSales, String totalItemizedDiscount, String totalBillDiscount, String grossTotal, String serviceCharge, String taxes, String totalNettSales) {
//        SalesID = salesID;
//        BillNo = billNo;
//        GrossSales = grossSales;
//        TotalItemizedDiscount = totalItemizedDiscount;
//        TotalBillDiscount = totalBillDiscount;
//        GrossTotal = grossTotal;
//        ServiceCharge = serviceCharge;
//        Taxes = taxes;
//        TotalNettSales = totalNettSales;
//    }

    public Integer getSalesID() {
        return SalesID;
    }

    public void setSalesID(Integer salesID) {
        SalesID = salesID;
    }

    public String getBillNo() {
        return BillNo;
    }

    public void setBillNo(String billNo) {
        BillNo = billNo;
    }

    public String getGrossSales() {
        return GrossSales;
    }

    public void setGrossSales(String grossSales) {
        GrossSales = grossSales;
    }

    public String getTotalItemizedDiscount() {
        return TotalItemizedDiscount;
    }

    public void setTotalItemizedDiscount(String totalItemizedDiscount) {
        TotalItemizedDiscount = totalItemizedDiscount;
    }

    public String getTotalBillDiscount() {
        return TotalBillDiscount;
    }

    public void setTotalBillDiscount(String totalBillDiscount) {
        TotalBillDiscount = totalBillDiscount;
    }

    public String getGrossTotal() {
        return GrossTotal;
    }

    public void setGrossTotal(String grossTotal) {
        GrossTotal = grossTotal;
    }

    public String getServiceCharge() {
        return ServiceCharge;
    }

    public void setServiceCharge(String serviceCharge) {
        ServiceCharge = serviceCharge;
    }

    public String getTaxes() {
        return Taxes;
    }

    public void setTaxes(String taxes) {
        Taxes = taxes;
    }

    public String getTotalNettSales() {
        return TotalNettSales;
    }

    public void setTotalNettSales(String totalNettSales) {
        TotalNettSales = totalNettSales;
    }
}
