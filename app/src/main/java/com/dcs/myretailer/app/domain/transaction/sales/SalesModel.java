package com.dcs.myretailer.app.domain.transaction.sales;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SalesModel {
    @PrimaryKey(autoGenerate = true)
    Integer id;
    @ColumnInfo(name = "bill_id")
    Integer bill_id;
    @ColumnInfo(name = "bill_no")
    String bill_no;
    @ColumnInfo(name = "uuid")
    String uuid;
    @ColumnInfo(name = "total_qty")
    Integer total_qty;
    @ColumnInfo(name = "total_amount")
    Double total_amount;
    @ColumnInfo(name = "change_amount")
    Double change_amount;
    @ColumnInfo(name = "payment_type_id")
    Integer payment_type_id;
    @ColumnInfo(name = "payment_type_name")
    String payment_type_name;
    @ColumnInfo(name = "payment_type_amount")
    Double payment_type_amount;
    @ColumnInfo(name = "total_items_discount")
    Double total_items_discount;
    @ColumnInfo(name = "total_bill_discount")
    Double total_bill_discount;
    @ColumnInfo(name = "service_charges")
    Double service_charges;
    @ColumnInfo(name = "total_nett_sales")
    Double total_nett_sales;
    @ColumnInfo(name = "total_taxes")
    Double total_taxes;
    @ColumnInfo(name = "cashier_name")
    String cashier_name;
    @ColumnInfo(name = "sales_datetime")
    String sales_datetime;
    @ColumnInfo(name = "bill_hour")
    String bill_hour;
    @ColumnInfo(name = "status")
    String status;
    @ColumnInfo(name = "discount_id")
    Integer discount_id;
    @ColumnInfo(name = "discount_name")
    String discount_name;
    @ColumnInfo(name = "discount_type")
    String discount_type;
    @ColumnInfo(name = "discount_type_name")
    String discount_type_name;
    @ColumnInfo(name = "discount_value")
    Double discount_value;
    @ColumnInfo(name = "is_z")
    String is_z;
    @ColumnInfo(name = "round_adj")
    Double round_adj;
    @ColumnInfo(name = "sales_delivery_id")
    Integer sales_delivery_id;
    @ColumnInfo(name = "collected_or_delivery")
    String collected_or_delivery;
    @ColumnInfo(name = "reference_bill_no")
    String reference_bill_no;
    @ColumnInfo(name = "reference_sales_id")
    String reference_sales_id;
    @ColumnInfo(name = "ewallet_status")
    String ewallet_status;
    @ColumnInfo(name = "dt")
    Long dt;

    public SalesModel(Integer id, Integer bill_id, String bill_no, String uuid, Integer total_qty, Double total_amount, Double change_amount, Integer payment_type_id, String payment_type_name, Double payment_type_amount, Double total_items_discount, Double total_bill_discount, Double service_charges, Double total_nett_sales, Double total_taxes, String cashier_name, String sales_datetime, String bill_hour, String status, Integer discount_id, String discount_name, String discount_type, String discount_type_name, Double discount_value, String is_z, Double round_adj, Integer sales_delivery_id, String collected_or_delivery, String reference_bill_no, String reference_sales_id, String ewallet_status, Long dt) {
        this.id = id;
        this.bill_id = bill_id;
        this.bill_no = bill_no;
        this.uuid = uuid;
        this.total_qty = total_qty;
        this.total_amount = total_amount;
        this.change_amount = change_amount;
        this.payment_type_id = payment_type_id;
        this.payment_type_name = payment_type_name;
        this.payment_type_amount = payment_type_amount;
        this.total_items_discount = total_items_discount;
        this.total_bill_discount = total_bill_discount;
        this.service_charges = service_charges;
        this.total_nett_sales = total_nett_sales;
        this.total_taxes = total_taxes;
        this.cashier_name = cashier_name;
        this.sales_datetime = sales_datetime;
        this.bill_hour = bill_hour;
        this.status = status;
        this.discount_id = discount_id;
        this.discount_name = discount_name;
        this.discount_type = discount_type;
        this.discount_type_name = discount_type_name;
        this.discount_value = discount_value;
        this.is_z = is_z;
        this.round_adj = round_adj;
        this.sales_delivery_id = sales_delivery_id;
        this.collected_or_delivery = collected_or_delivery;
        this.reference_bill_no = reference_bill_no;
        this.reference_sales_id = reference_sales_id;
        this.ewallet_status = ewallet_status;
        this.dt = dt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBill_id() {
        return bill_id;
    }

    public void setBill_id(Integer bill_id) {
        this.bill_id = bill_id;
    }

    public String getBill_no() {
        return bill_no;
    }

    public void setBill_no(String bill_no) {
        this.bill_no = bill_no;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getTotal_qty() {
        return total_qty;
    }

    public void setTotal_qty(Integer total_qty) {
        this.total_qty = total_qty;
    }

    public Double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(Double total_amount) {
        this.total_amount = total_amount;
    }

    public Double getChange_amount() {
        return change_amount;
    }

    public void setChange_amount(Double change_amount) {
        this.change_amount = change_amount;
    }

    public Integer getPayment_type_id() {
        return payment_type_id;
    }

    public void setPayment_type_id(Integer payment_type_id) {
        this.payment_type_id = payment_type_id;
    }

    public String getPayment_type_name() {
        return payment_type_name;
    }

    public void setPayment_type_name(String payment_type_name) {
        this.payment_type_name = payment_type_name;
    }

    public Double getPayment_type_amount() {
        return payment_type_amount;
    }

    public void setPayment_type_amount(Double payment_type_amount) {
        this.payment_type_amount = payment_type_amount;
    }

    public Double getTotal_items_discount() {
        return total_items_discount;
    }

    public void setTotal_items_discount(Double total_items_discount) {
        this.total_items_discount = total_items_discount;
    }

    public Double getTotal_bill_discount() {
        return total_bill_discount;
    }

    public void setTotal_bill_discount(Double total_bill_discount) {
        this.total_bill_discount = total_bill_discount;
    }

    public Double getService_charges() {
        return service_charges;
    }

    public void setService_charges(Double service_charges) {
        this.service_charges = service_charges;
    }

    public Double getTotal_nett_sales() {
        return total_nett_sales;
    }

    public void setTotal_nett_sales(Double total_nett_sales) {
        this.total_nett_sales = total_nett_sales;
    }

    public Double getTotal_taxes() {
        return total_taxes;
    }

    public void setTotal_taxes(Double total_taxes) {
        this.total_taxes = total_taxes;
    }

    public String getCashier_name() {
        return cashier_name;
    }

    public void setCashier_name(String cashier_name) {
        this.cashier_name = cashier_name;
    }

    public String getSales_datetime() {
        return sales_datetime;
    }

    public void setSales_datetime(String sales_datetime) {
        this.sales_datetime = sales_datetime;
    }

    public String getBill_hour() {
        return bill_hour;
    }

    public void setBill_hour(String bill_hour) {
        this.bill_hour = bill_hour;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getDiscount_id() {
        return discount_id;
    }

    public void setDiscount_id(Integer discount_id) {
        this.discount_id = discount_id;
    }

    public String getDiscount_name() {
        return discount_name;
    }

    public void setDiscount_name(String discount_name) {
        this.discount_name = discount_name;
    }

    public String getDiscount_type() {
        return discount_type;
    }

    public void setDiscount_type(String discount_type) {
        this.discount_type = discount_type;
    }

    public String getDiscount_type_name() {
        return discount_type_name;
    }

    public void setDiscount_type_name(String discount_type_name) {
        this.discount_type_name = discount_type_name;
    }

    public Double getDiscount_value() {
        return discount_value;
    }

    public void setDiscount_value(Double discount_value) {
        this.discount_value = discount_value;
    }

    public String getIs_z() {
        return is_z;
    }

    public void setIs_z(String is_z) {
        this.is_z = is_z;
    }

    public Double getRound_adj() {
        return round_adj;
    }

    public void setRound_adj(Double round_adj) {
        this.round_adj = round_adj;
    }

    public Integer getSales_delivery_id() {
        return sales_delivery_id;
    }

    public void setSales_delivery_id(Integer sales_delivery_id) {
        this.sales_delivery_id = sales_delivery_id;
    }

    public String getCollected_or_delivery() {
        return collected_or_delivery;
    }

    public void setCollected_or_delivery(String collected_or_delivery) {
        this.collected_or_delivery = collected_or_delivery;
    }

    public String getReference_bill_no() {
        return reference_bill_no;
    }

    public void setReference_bill_no(String reference_bill_no) {
        this.reference_bill_no = reference_bill_no;
    }

    public String getReference_sales_id() {
        return reference_sales_id;
    }

    public void setReference_sales_id(String reference_sales_id) {
        this.reference_sales_id = reference_sales_id;
    }

    public String getEwallet_status() {
        return ewallet_status;
    }

    public void setEwallet_status(String ewallet_status) {
        this.ewallet_status = ewallet_status;
    }

    public Long getDt() {
        return dt;
    }

    public void setDt(Long dt) {
        this.dt = dt;
    }
}
