package com.dcs.myretailer.app.Model;

public class MercatusReceiptData {
    String memberStatus;
    String payment_type;
    String payment_amount;
    String Card_Label;
    String Card_Number;
    String Invoice_Number;
    String voucher_number;
    String voucher_amount;

    public MercatusReceiptData() {
    }

    public String getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(String memberStatus) {
        this.memberStatus = memberStatus;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public String getPayment_amount() {
        return payment_amount;
    }

    public void setPayment_amount(String payment_amount) {
        this.payment_amount = payment_amount;
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

    public String getVoucher_number() {
        return voucher_number;
    }

    public void setVoucher_number(String voucher_number) {
        this.voucher_number = voucher_number;
    }

    public String getVoucher_amount() {
        return voucher_amount;
    }

    public void setVoucher_amount(String voucher_amount) {
        this.voucher_amount = voucher_amount;
    }
}
