package com.dcs.myretailer.app.Model;

public class BillJeripayDetails {
    Integer ID;
    String BillNo;
    String card_label;
    String card_number;
    String merchant_id;
    String terminal_id;
    String invoice_number;
    Integer dt;

    public BillJeripayDetails(Integer ID, String billNo, String card_label, String card_number, String merchant_id, String terminal_id, String invoice_number, Integer dt) {
        this.ID = ID;
        BillNo = billNo;
        this.card_label = card_label;
        this.card_number = card_number;
        this.merchant_id = merchant_id;
        this.terminal_id = terminal_id;
        this.invoice_number = invoice_number;
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

    public String getCard_label() {
        return card_label;
    }

    public void setCard_label(String card_label) {
        this.card_label = card_label;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public String getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(String merchant_id) {
        this.merchant_id = merchant_id;
    }

    public String getTerminal_id() {
        return terminal_id;
    }

    public void setTerminal_id(String terminal_id) {
        this.terminal_id = terminal_id;
    }

    public String getInvoice_number() {
        return invoice_number;
    }

    public void setInvoice_number(String invoice_number) {
        this.invoice_number = invoice_number;
    }

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }
}
