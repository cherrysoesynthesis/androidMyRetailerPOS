package com.dcs.myretailer.app.Cashier;

public class BillDetails {
    private String BillNo;
    private Integer TotalItems;
    private String TotalAmount;
    private String DateTime;
    private Integer ID;
    private String STATUS;
    private String vchQueueNo;
    private String intTableNo;
    public BillDetails(String billNo, Integer totalItems, String totalAmount, String dateTime, Integer ID,String STATUS,String vchQueueNo,String intTableNo) {
        this.BillNo = billNo;
        this.TotalItems = totalItems;
        this.TotalAmount = totalAmount;
        this.DateTime = dateTime;
        this.ID = ID;
        this.STATUS = STATUS;
        this.vchQueueNo = vchQueueNo;
        this.intTableNo = intTableNo;
    }

    public String getBillNo() {
        return BillNo;
    }

    public void setBillNo(String billNo) {
        BillNo = billNo;
    }

    public Integer getTotalItems() {
        return TotalItems;
    }

    public void setTotalItems(Integer totalItems) {
        TotalItems = totalItems;
    }

    public String getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        TotalAmount = totalAmount;
    }

    public String getDateTime() {
        return DateTime;
    }

    public void setDateTime(String dateTime) {
        DateTime = dateTime;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getVchQueueNo() {
        return vchQueueNo;
    }

    public void setVchQueueNo(String vchQueueNo) {
        this.vchQueueNo = vchQueueNo;
    }

    public String getIntTableNo() {
        return intTableNo;
    }

    public void setIntTableNo(String intTableNo) {
        this.intTableNo = intTableNo;
    }
}
