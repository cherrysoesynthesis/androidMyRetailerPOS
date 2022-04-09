package com.dcs.myretailer.app.Jeripay;

public class BillJeripay {
    Integer ID;
    String BillNo;
    String uuid;
    String status;
    String acquirer;
    String acquirer_id;
    String acquirer_payment_id;
    String amount;
    String billjeripaydetailsID;
    String isCardPayment;
    String dt;
    Integer dtTime;

    public BillJeripay(Integer ID, String billNo, String uuid, String status, String acquirer, String acquirer_id, String acquirer_payment_id, String amount, String billjeripaydetailsID, String isCardPayment, String dt, Integer dtTime) {
        this.ID = ID;
        BillNo = billNo;
        this.uuid = uuid;
        this.status = status;
        this.acquirer = acquirer;
        this.acquirer_id = acquirer_id;
        this.acquirer_payment_id = acquirer_payment_id;
        this.amount = amount;
        this.billjeripaydetailsID = billjeripaydetailsID;
        this.isCardPayment = isCardPayment;
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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAcquirer() {
        return acquirer;
    }

    public void setAcquirer(String acquirer) {
        this.acquirer = acquirer;
    }

    public String getAcquirer_id() {
        return acquirer_id;
    }

    public void setAcquirer_id(String acquirer_id) {
        this.acquirer_id = acquirer_id;
    }

    public String getAcquirer_payment_id() {
        return acquirer_payment_id;
    }

    public void setAcquirer_payment_id(String acquirer_payment_id) {
        this.acquirer_payment_id = acquirer_payment_id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBilljeripaydetailsID() {
        return billjeripaydetailsID;
    }

    public void setBilljeripaydetailsID(String billjeripaydetailsID) {
        this.billjeripaydetailsID = billjeripaydetailsID;
    }

    public String getIsCardPayment() {
        return isCardPayment;
    }

    public void setIsCardPayment(String isCardPayment) {
        this.isCardPayment = isCardPayment;
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
