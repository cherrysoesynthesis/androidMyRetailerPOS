package com.dcs.myretailer.app.Mercatus;

public class BillMercatus {
    Integer ID;
    String BillNo;
    String transaction_id;
    String billmercatuspaymentID;
    String billmercatusvouchersID;
    String billmercatusmallloyaltydetailsID;
    String IsMember;
    String dt;
    Integer dtTime;

    public BillMercatus(Integer ID, String billNo, String transaction_id, String billmercatuspaymentID, String billmercatusvouchersID, String billmercatusmallloyaltydetailsID, String isMember, String dt, Integer dtTime) {
        this.ID = ID;
        BillNo = billNo;
        this.transaction_id = transaction_id;
        this.billmercatuspaymentID = billmercatuspaymentID;
        this.billmercatusvouchersID = billmercatusvouchersID;
        this.billmercatusmallloyaltydetailsID = billmercatusmallloyaltydetailsID;
        IsMember = isMember;
        this.dt = dt;
        this.dtTime = dtTime;
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

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getBillmercatuspaymentID() {
        return billmercatuspaymentID;
    }

    public void setBillmercatuspaymentID(String billmercatuspaymentID) {
        this.billmercatuspaymentID = billmercatuspaymentID;
    }

    public String getBillmercatusvouchersID() {
        return billmercatusvouchersID;
    }

    public void setBillmercatusvouchersID(String billmercatusvouchersID) {
        this.billmercatusvouchersID = billmercatusvouchersID;
    }

    public String getBillmercatusmallloyaltydetailsID() {
        return billmercatusmallloyaltydetailsID;
    }

    public void setBillmercatusmallloyaltydetailsID(String billmercatusmallloyaltydetailsID) {
        this.billmercatusmallloyaltydetailsID = billmercatusmallloyaltydetailsID;
    }

    public String getIsMember() {
        return IsMember;
    }

    public void setIsMember(String isMember) {
        IsMember = isMember;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public Integer getDtTime() {
        return dtTime;
    }

    public void setDtTime(Integer dtTime) {
        this.dtTime = dtTime;
    }
}
