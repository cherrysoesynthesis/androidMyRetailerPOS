package com.dcs.myretailer.app.Mercatus;

public class BillMercatusPayment {
    Integer ID;
    String BillNo;
    String payment_type;
    String payment_id;
    String amount;
    String billmercatusdetailsID;
    Integer dt;

    public BillMercatusPayment(Integer ID, String billNo, String payment_type, String payment_id, String amount, String billmercatusdetailsID, Integer dt) {
        this.ID = ID;
        BillNo = billNo;
        this.payment_type = payment_type;
        this.payment_id = payment_id;
        this.amount = amount;
        this.billmercatusdetailsID = billmercatusdetailsID;
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

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public String getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(String payment_id) {
        this.payment_id = payment_id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBillmercatusdetailsID() {
        return billmercatusdetailsID;
    }

    public void setBillmercatusdetailsID(String billmercatusdetailsID) {
        this.billmercatusdetailsID = billmercatusdetailsID;
    }

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }
}
