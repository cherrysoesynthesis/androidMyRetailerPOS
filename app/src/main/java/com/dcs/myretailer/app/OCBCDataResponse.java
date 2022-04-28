package com.dcs.myretailer.app;

public class OCBCDataResponse {
    String uuid = "";
    String billno = "";
    String additional_printing_flag = "";
    String approval_code = "";
    String batch_number = "";
    String card_label = "";
    String card_number = "";
    String card_type = "";
    String command_identifier = "";
    String custom_data_2 = "";
    String date_time = "";
    String host_label = "";
    String host_type = "";
    String invoice_number = "";
    String merchant_id = "";
    String original_trans_type = "";
    String response_code = "";
    String retrieval_reference_number = "";
    String terminal_id = "";
    String transaction_type = "";

    public OCBCDataResponse(String uuid, String billno, String additional_printing_flag, String approval_code, String batch_number, String card_label, String card_number, String card_type, String command_identifier, String custom_data_2, String date_time, String host_label, String host_type, String invoice_number, String merchant_id, String original_trans_type, String response_code, String retrieval_reference_number, String terminal_id, String transaction_type) {
        this.uuid = uuid;
        this.billno = billno;
        this.additional_printing_flag = additional_printing_flag;
        this.approval_code = approval_code;
        this.batch_number = batch_number;
        this.card_label = card_label;
        this.card_number = card_number;
        this.card_type = card_type;
        this.command_identifier = command_identifier;
        this.custom_data_2 = custom_data_2;
        this.date_time = date_time;
        this.host_label = host_label;
        this.host_type = host_type;
        this.invoice_number = invoice_number;
        this.merchant_id = merchant_id;
        this.original_trans_type = original_trans_type;
        this.response_code = response_code;
        this.retrieval_reference_number = retrieval_reference_number;
        this.terminal_id = terminal_id;
        this.transaction_type = transaction_type;
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

    public String getAdditional_printing_flag() {
        return additional_printing_flag;
    }

    public void setAdditional_printing_flag(String additional_printing_flag) {
        this.additional_printing_flag = additional_printing_flag;
    }

    public String getApproval_code() {
        return approval_code;
    }

    public void setApproval_code(String approval_code) {
        this.approval_code = approval_code;
    }

    public String getBatch_number() {
        return batch_number;
    }

    public void setBatch_number(String batch_number) {
        this.batch_number = batch_number;
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

    public String getCard_type() {
        return card_type;
    }

    public void setCard_type(String card_type) {
        this.card_type = card_type;
    }

    public String getCommand_identifier() {
        return command_identifier;
    }

    public void setCommand_identifier(String command_identifier) {
        this.command_identifier = command_identifier;
    }

    public String getCustom_data_2() {
        return custom_data_2;
    }

    public void setCustom_data_2(String custom_data_2) {
        this.custom_data_2 = custom_data_2;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public String getHost_label() {
        return host_label;
    }

    public void setHost_label(String host_label) {
        this.host_label = host_label;
    }

    public String getHost_type() {
        return host_type;
    }

    public void setHost_type(String host_type) {
        this.host_type = host_type;
    }

    public String getInvoice_number() {
        return invoice_number;
    }

    public void setInvoice_number(String invoice_number) {
        this.invoice_number = invoice_number;
    }

    public String getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(String merchant_id) {
        this.merchant_id = merchant_id;
    }

    public String getOriginal_trans_type() {
        return original_trans_type;
    }

    public void setOriginal_trans_type(String original_trans_type) {
        this.original_trans_type = original_trans_type;
    }

    public String getResponse_code() {
        return response_code;
    }

    public void setResponse_code(String response_code) {
        this.response_code = response_code;
    }

    public String getRetrieval_reference_number() {
        return retrieval_reference_number;
    }

    public void setRetrieval_reference_number(String retrieval_reference_number) {
        this.retrieval_reference_number = retrieval_reference_number;
    }

    public String getTerminal_id() {
        return terminal_id;
    }

    public void setTerminal_id(String terminal_id) {
        this.terminal_id = terminal_id;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }
}
