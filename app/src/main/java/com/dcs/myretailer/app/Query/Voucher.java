package com.dcs.myretailer.app.Query;

public class Voucher {
    private String voucher_record_id;
    private String voucher_no;
    private String voucher_value;
    private String voucher_status;
    private String voucher_expiry_value;

    public String getVoucher_record_id() {
        return voucher_record_id;
    }

    public void setVoucher_record_id(String voucher_record_id) {
        this.voucher_record_id = voucher_record_id;
    }

    public String getVoucher_no() {
        return voucher_no;
    }

    public void setVoucher_no(String voucher_no) {
        this.voucher_no = voucher_no;
    }

    public String getVoucher_value() {
        return voucher_value;
    }

    public void setVoucher_value(String voucher_value) {
        this.voucher_value = voucher_value;
    }

    public String getVoucher_status() {
        return voucher_status;
    }

    public void setVoucher_status(String voucher_status) {
        this.voucher_status = voucher_status;
    }

    public String getVoucher_expiry_value() {
        return voucher_expiry_value;
    }

    public void setVoucher_expiry_value(String voucher_expiry_value) {
        this.voucher_expiry_value = voucher_expiry_value;
    }
}
