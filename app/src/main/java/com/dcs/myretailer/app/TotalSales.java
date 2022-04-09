package com.dcs.myretailer.app;

public class TotalSales {
    String BillPaid;
    String BillCancel;
    String BillRefund;
    String QtySold;
    String AMTNett;
    String TaxTotal;
    String AMTDiscount;
    String AMTSurcharge;
    String RoundAdj;
    String QtyCancel;
    String QtyRefund;

    public TotalSales() {
    }

    public String getBillPaid() {
        return BillPaid;
    }

    public void setBillPaid(String billPaid) {
        BillPaid = billPaid;
    }

    public String getBillCancel() {
        return BillCancel;
    }

    public void setBillCancel(String billCancel) {
        BillCancel = billCancel;
    }

    public String getBillRefund() {
        return BillRefund;
    }

    public void setBillRefund(String billRefund) {
        BillRefund = billRefund;
    }

    public String getQtySold() {
        return QtySold;
    }

    public void setQtySold(String qtySold) {
        QtySold = qtySold;
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

    public String getAMTDiscount() {
        return AMTDiscount;
    }

    public void setAMTDiscount(String AMTDiscount) {
        this.AMTDiscount = AMTDiscount;
    }

    public String getAMTSurcharge() {
        return AMTSurcharge;
    }

    public void setAMTSurcharge(String AMTSurcharge) {
        this.AMTSurcharge = AMTSurcharge;
    }

    public String getRoundAdj() {
        return RoundAdj;
    }

    public void setRoundAdj(String roundAdj) {
        RoundAdj = roundAdj;
    }

    public String getQtyCancel() {
        return QtyCancel;
    }

    public void setQtyCancel(String qtyCancel) {
        QtyCancel = qtyCancel;
    }

    public String getQtyRefund() {
        return QtyRefund;
    }

    public void setQtyRefund(String qtyRefund) {
        QtyRefund = qtyRefund;
    }
}
