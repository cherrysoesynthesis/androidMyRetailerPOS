package com.dcs.myretailer.app.CardPayment;

public class CardPaymentDataDetails {
    Integer INTEGER;
    String uuid;
    String billno;
    String card_label;
    String card_number;
    String merchant_id;
    String terminal_id;
    String invoice_number;
    String Date;
    Integer DateTime;

    public CardPaymentDataDetails(Integer INTEGER, String uuid, String billno, String card_label, String card_number, String merchant_id, String terminal_id, String invoice_number, String date, Integer dateTime) {
        this.INTEGER = INTEGER;
        this.uuid = uuid;
        this.billno = billno;
        this.card_label = card_label;
        this.card_number = card_number;
        this.merchant_id = merchant_id;
        this.terminal_id = terminal_id;
        this.invoice_number = invoice_number;
        Date = date;
        DateTime = dateTime;
    }

    public Integer getINTEGER() {
        return INTEGER;
    }

    public void setINTEGER(Integer INTEGER) {
        this.INTEGER = INTEGER;
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
