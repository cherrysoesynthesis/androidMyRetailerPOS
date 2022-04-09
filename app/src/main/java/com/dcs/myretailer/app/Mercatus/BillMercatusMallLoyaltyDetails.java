package com.dcs.myretailer.app.Mercatus;

public class BillMercatusMallLoyaltyDetails {
    Integer ID;
    String BillNo;
    String status;
    String transaction_id;
    String transaction_amount;
    Integer dt;

    public BillMercatusMallLoyaltyDetails(Integer ID, String billNo, String status, String transaction_id, String transaction_amount, Integer dt) {
        this.ID = ID;
        BillNo = billNo;
        this.status = status;
        this.transaction_id = transaction_id;
        this.transaction_amount = transaction_amount;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getTransaction_amount() {
        return transaction_amount;
    }

    public void setTransaction_amount(String transaction_amount) {
        this.transaction_amount = transaction_amount;
    }

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }
}
