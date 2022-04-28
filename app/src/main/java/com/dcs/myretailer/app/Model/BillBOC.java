package com.dcs.myretailer.app.Model;

public class BillBOC {
    	Integer ID;
        String BillNo;
        String uuid;
        String STATUS;
        String additional_printing_flag;
        String approval_code;
        String batch_number;
        String card_holder_name;
        String card_label;
        String card_number;
        String card_type;
        String command_identifier;
        String coupons_vouchers;
        String custom_data_;
        String custom_data_3;
        String date_time;
        String destination_package_name;
        String ecr_unique_trace_number;
        String employee_id;
        String emv_data;
        String entry_mode;
        String expiry_date;
        String external_device_invoice;
        String host_label;
        String host_type;
        String invoice_number;
        String merchant_id;
        String original_trans_type;
        String response_code;
        String retrieval_reference_number;
        String source_package_name;
        String terminal_id;
        String transaction_amount;
        String transaction_info;
        String transaction_type;
        long dt;

    public BillBOC(Integer ID, String billNo, String uuid, String STATUS, String additional_printing_flag,
                   String approval_code, String batch_number, String card_holder_name, String card_label,
                   String card_number, String card_type, String command_identifier, String coupons_vouchers,
                   String custom_data_, String custom_data_3, String date_time, String destination_package_name,
                   String ecr_unique_trace_number, String employee_id, String emv_data, String entry_mode,
                   String expiry_date, String external_device_invoice, String host_label, String host_type,
                   String invoice_number, String merchant_id, String original_trans_type, String response_code,
                   String retrieval_reference_number, String source_package_name, String terminal_id,
                   String transaction_amount, String transaction_info, String transaction_type, long dt) {

        this.ID = ID;
        BillNo = billNo;
        this.uuid = uuid;
        this.STATUS = STATUS;
        this.additional_printing_flag = additional_printing_flag;
        this.approval_code = approval_code;
        this.batch_number = batch_number;
        this.card_holder_name = card_holder_name;
        this.card_label = card_label;
        this.card_number = card_number;
        this.card_type = card_type;
        this.command_identifier = command_identifier;
        this.coupons_vouchers = coupons_vouchers;
        this.custom_data_ = custom_data_;
        this.custom_data_3 = custom_data_3;
        this.date_time = date_time;
        this.destination_package_name = destination_package_name;
        this.ecr_unique_trace_number = ecr_unique_trace_number;
        this.employee_id = employee_id;
        this.emv_data = emv_data;
        this.entry_mode = entry_mode;
        this.expiry_date = expiry_date;
        this.external_device_invoice = external_device_invoice;
        this.host_label = host_label;
        this.host_type = host_type;
        this.invoice_number = invoice_number;
        this.merchant_id = merchant_id;
        this.original_trans_type = original_trans_type;
        this.response_code = response_code;
        this.retrieval_reference_number = retrieval_reference_number;
        this.source_package_name = source_package_name;
        this.terminal_id = terminal_id;
        this.transaction_amount = transaction_amount;
        this.transaction_info = transaction_info;
        this.transaction_type = transaction_type;
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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
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

    public String getCard_holder_name() {
        return card_holder_name;
    }

    public void setCard_holder_name(String card_holder_name) {
        this.card_holder_name = card_holder_name;
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

    public String getCoupons_vouchers() {
        return coupons_vouchers;
    }

    public void setCoupons_vouchers(String coupons_vouchers) {
        this.coupons_vouchers = coupons_vouchers;
    }

    public String getCustom_data_() {
        return custom_data_;
    }

    public void setCustom_data_(String custom_data_) {
        this.custom_data_ = custom_data_;
    }

    public String getCustom_data_3() {
        return custom_data_3;
    }

    public void setCustom_data_3(String custom_data_3) {
        this.custom_data_3 = custom_data_3;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public String getDestination_package_name() {
        return destination_package_name;
    }

    public void setDestination_package_name(String destination_package_name) {
        this.destination_package_name = destination_package_name;
    }

    public String getEcr_unique_trace_number() {
        return ecr_unique_trace_number;
    }

    public void setEcr_unique_trace_number(String ecr_unique_trace_number) {
        this.ecr_unique_trace_number = ecr_unique_trace_number;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmv_data() {
        return emv_data;
    }

    public void setEmv_data(String emv_data) {
        this.emv_data = emv_data;
    }

    public String getEntry_mode() {
        return entry_mode;
    }

    public void setEntry_mode(String entry_mode) {
        this.entry_mode = entry_mode;
    }

    public String getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(String expiry_date) {
        this.expiry_date = expiry_date;
    }

    public String getExternal_device_invoice() {
        return external_device_invoice;
    }

    public void setExternal_device_invoice(String external_device_invoice) {
        this.external_device_invoice = external_device_invoice;
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

    public String getSource_package_name() {
        return source_package_name;
    }

    public void setSource_package_name(String source_package_name) {
        this.source_package_name = source_package_name;
    }

    public String getTerminal_id() {
        return terminal_id;
    }

    public void setTerminal_id(String terminal_id) {
        this.terminal_id = terminal_id;
    }

    public String getTransaction_amount() {
        return transaction_amount;
    }

    public void setTransaction_amount(String transaction_amount) {
        this.transaction_amount = transaction_amount;
    }

    public String getTransaction_info() {
        return transaction_info;
    }

    public void setTransaction_info(String transaction_info) {
        this.transaction_info = transaction_info;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public Long getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }
}
