package com.dcs.myretailer.app.Mercatus;

public class BillMercatusVouchers {
    Integer ID;
    String BillNo;
    String voucher_number;
    String voucher_value;
    Integer dt;

    public BillMercatusVouchers(Integer ID, String billNo, String voucher_number, String voucher_value, Integer dt) {
        this.ID = ID;
        BillNo = billNo;
        this.voucher_number = voucher_number;
        this.voucher_value = voucher_value;
        this.dt = dt;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getBillNo() {
        return BillNo;
    }

    public void setBillNo(String billNo) {
        BillNo = billNo;
    }

    public String getVoucher_number() {
        return voucher_number;
    }

    public void setVoucher_number(String voucher_number) {
        this.voucher_number = voucher_number;
    }

    public String getVoucher_value() {
        return voucher_value;
    }

    public void setVoucher_value(String voucher_value) {
        this.voucher_value = voucher_value;
    }

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }
}
