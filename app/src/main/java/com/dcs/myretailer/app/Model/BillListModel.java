package com.dcs.myretailer.app.Model;

public class BillListModel {
    String BillID;
    String BillNo;
    String STATUS;
    String TotalItems;
    String Date;
    String TableNo;
    String QueueNo;
    String OnlineOrderBill;
    String TotalAmount;
    Integer DateTime;

    public BillListModel(String billID, String billNo, String STATUS, String totalItems, String date, String tableNo, String queueNo, String onlineOrderBill, String totalAmount, Integer dateTime) {
        BillID = billID;
        BillNo = billNo;
        this.STATUS = STATUS;
        TotalItems = totalItems;
        Date = date;
        TableNo = tableNo;
        QueueNo = queueNo;
        OnlineOrderBill = onlineOrderBill;
        TotalAmount = totalAmount;
        DateTime = dateTime;
    }

    public String getBillID() {
        return BillID;
    }

    public void setBillID(String billID) {
        BillID = billID;
    }

    public String getBillNo() {
        return BillNo;
    }

    public void setBillNo(String billNo) {
        BillNo = billNo;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getTotalItems() {
        return TotalItems;
    }

    public void setTotalItems(String totalItems) {
        TotalItems = totalItems;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTableNo() {
        return TableNo;
    }

    public void setTableNo(String tableNo) {
        TableNo = tableNo;
    }

    public String getQueueNo() {
        return QueueNo;
    }

    public void setQueueNo(String queueNo) {
        QueueNo = queueNo;
    }

    public String getOnlineOrderBill() {
        return OnlineOrderBill;
    }

    public void setOnlineOrderBill(String onlineOrderBill) {
        OnlineOrderBill = onlineOrderBill;
    }

    public String getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        TotalAmount = totalAmount;
    }

    public Integer getDateTime() {
        return DateTime;
    }

    public void setDateTime(Integer dateTime) {
        DateTime = dateTime;
    }
}
