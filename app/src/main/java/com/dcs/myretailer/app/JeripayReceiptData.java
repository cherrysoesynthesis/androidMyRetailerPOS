package com.dcs.myretailer.app;

public class JeripayReceiptData {
    String status;
    String Acquirer;
    String Acquirer_PaymentID;
    String Amount;
    String Card_Label;
    String Card_Number;
    String Invoice_Number;

    public JeripayReceiptData() {
    }

    public String getCard_Label() {
        return Card_Label;
    }

    public void setCard_Label(String card_Label) {
        Card_Label = card_Label;
    }

    public String getCard_Number() {
        return Card_Number;
    }

    public void setCard_Number(String card_Number) {
        Card_Number = card_Number;
    }

    public String getInvoice_Number() {
        return Invoice_Number;
    }

    public void setInvoice_Number(String invoice_Number) {
        Invoice_Number = invoice_Number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAcquirer() {
        return Acquirer;
    }

    public void setAcquirer(String acquirer) {
        Acquirer = acquirer;
    }

    public String getAcquirer_PaymentID() {
        return Acquirer_PaymentID;
    }

    public void setAcquirer_PaymentID(String acquirer_PaymentID) {
        Acquirer_PaymentID = acquirer_PaymentID;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }
}
