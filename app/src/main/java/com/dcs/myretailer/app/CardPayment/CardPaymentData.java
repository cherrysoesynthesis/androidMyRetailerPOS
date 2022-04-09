package com.dcs.myretailer.app.CardPayment;

public class CardPaymentData {
    Integer ID;
    String uuid;
    String billno;
    String status;
    String acquirer;
    String acquirer_id;
    String acquirer_payment_id;
    String amount;
    String cardPaymentDataDetailsID;
    String Date;
    Integer DateTime;


    public CardPaymentData(Integer ID, String uuid, String billno, String status, String acquirer, String acquirer_id, String acquirer_payment_id, String amount, String cardPaymentDataDetailsID, String date, Integer dateTime) {
        this.ID = ID;
        this.uuid = uuid;
        this.billno = billno;
        this.status = status;
        this.acquirer = acquirer;
        this.acquirer_id = acquirer_id;
        this.acquirer_payment_id = acquirer_payment_id;
        this.amount = amount;
        this.cardPaymentDataDetailsID = cardPaymentDataDetailsID;
        Date = date;
        DateTime = dateTime;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getBillno() {
        return billno;
    }

    public void setBillno(String billno) {
        this.billno = billno;
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

    public String getCardPaymentDataDetailsID() {
        return cardPaymentDataDetailsID;
    }

    public void setCardPaymentDataDetailsID(String cardPaymentDataDetailsID) {
        this.cardPaymentDataDetailsID = cardPaymentDataDetailsID;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public Integer getDateTime() {
        return DateTime;
    }

    public void setDateTime(Integer dateTime) {
        DateTime = dateTime;
    }
}
