package com.dcs.myretailer.app;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class ReceiptData {
    String receiptHeader;
    String receiptFooter;
    String statusCancelBill;
    String statusVoidBill;
    String statusRefundBill;
    String statusReprintBill;
    String QueueNo;
    String TableNo;
    String OrderStatus;
    String InvoiceNo;
    String ReceiptNoDateTime;
    String ReceiptNo;
    String DeliveryText;
    ArrayList<OrderDetails> orderDetails;
    String totalItems;
    String subTotal;
    String billDiscount;
    String billDiscountNameValue;
    String billTotal;
    String BillPayment;
    String BillPaymentValue;
    ArrayList<BillPaymentValue> billPaymentDetailsValue;
    String billChangeAmount;
    String closedSales;
    String totalLine;
    String taxInclusive;
    String taxExclusiveName;
    String taxExclusiveValue;
    String RoundAdj;
    String ServiceChargesName;
    String ServiceChargesValue;
    JeripayReceiptData jeripayInfo;
    MercatusReceiptData mercatusInfo;
    Bitmap ReceiptNoBarCode;
    Bitmap Shoptima;
    Bitmap ScaledBitmap;

    public ReceiptData() {
    }

    public Bitmap getReceiptNoBarCode() {
        return ReceiptNoBarCode;
    }

    public void setReceiptNoBarCode(Bitmap receiptNoBarCode) {
        ReceiptNoBarCode = receiptNoBarCode;
    }

    public Bitmap getShoptima() {
        return Shoptima;
    }

    public void setShoptima(Bitmap shoptima) {
        Shoptima = shoptima;
    }

    public Bitmap getScaledBitmap() {
        return ScaledBitmap;
    }

    public void setScaledBitmap(Bitmap scaledBitmap) {
        ScaledBitmap = scaledBitmap;
    }

    public String getBillDiscountNameValue() {
        return billDiscountNameValue;
    }

    public void setBillDiscountNameValue(String billDiscountNameValue) {
        this.billDiscountNameValue = billDiscountNameValue;
    }

    public String getServiceChargesName() {
        return ServiceChargesName;
    }

    public void setServiceChargesName(String serviceChargesName) {
        ServiceChargesName = serviceChargesName;
    }

    public String getServiceChargesValue() {
        return ServiceChargesValue;
    }

    public void setServiceChargesValue(String serviceChargesValue) {
        ServiceChargesValue = serviceChargesValue;
    }

    public String getRoundAdj() {
        return RoundAdj;
    }

    public void setRoundAdj(String roundAdj) {
        RoundAdj = roundAdj;
    }

    public String getTaxInclusive() {
        return taxInclusive;
    }

    public void setTaxInclusive(String taxInclusive) {
        this.taxInclusive = taxInclusive;
    }

    public String getTaxExclusiveName() {
        return taxExclusiveName;
    }

    public void setTaxExclusiveName(String taxExclusiveName) {
        this.taxExclusiveName = taxExclusiveName;
    }

    public String getTaxExclusiveValue() {
        return taxExclusiveValue;
    }

    public void setTaxExclusiveValue(String taxExclusiveValue) {
        this.taxExclusiveValue = taxExclusiveValue;
    }

    public MercatusReceiptData getMercatusInfo() {
        return mercatusInfo;
    }

    public void setMercatusInfo(MercatusReceiptData mercatusInfo) {
        this.mercatusInfo = mercatusInfo;
    }

    public JeripayReceiptData getJeripayInfo() {
        return jeripayInfo;
    }

    public void setJeripayInfo(JeripayReceiptData jeripayInfo) {
        this.jeripayInfo = jeripayInfo;
    }

    public String getReceiptHeader() {
        return receiptHeader;
    }

    public void setReceiptHeader(String receiptHeader) {
        this.receiptHeader = receiptHeader;
    }

    public String getReceiptFooter() {
        return receiptFooter;
    }

    public void setReceiptFooter(String receiptFooter) {
        this.receiptFooter = receiptFooter;
    }

    public String getStatusCancelBill() {
        return statusCancelBill;
    }

    public void setStatusCancelBill(String statusCancelBill) {
        this.statusCancelBill = statusCancelBill;
    }

    public String getStatusVoidBill() {
        return statusVoidBill;
    }

    public void setStatusVoidBill(String statusVoidBill) {
        this.statusVoidBill = statusVoidBill;
    }

    public String getStatusRefundBill() {
        return statusRefundBill;
    }

    public void setStatusRefundBill(String statusRefundBill) {
        this.statusRefundBill = statusRefundBill;
    }

    public String getStatusReprintBill() {
        return statusReprintBill;
    }

    public void setStatusReprintBill(String statusReprintBill) {
        this.statusReprintBill = statusReprintBill;
    }

    public String getQueueNo() {
        return QueueNo;
    }

    public void setQueueNo(String queueNo) {
        QueueNo = queueNo;
    }

    public String getTableNo() {
        return TableNo;
    }

    public void setTableNo(String tableNo) {
        TableNo = tableNo;
    }

    public String getOrderStatus() {
        return OrderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        OrderStatus = orderStatus;
    }

    public String getInvoiceNo() {
        return InvoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        InvoiceNo = invoiceNo;
    }

    public String getReceiptNoDateTime() {
        return ReceiptNoDateTime;
    }

    public void setReceiptNoDateTime(String receiptNoDateTime) {
        ReceiptNoDateTime = receiptNoDateTime;
    }

    public String getReceiptNo() {
        return ReceiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        ReceiptNo = receiptNo;
    }

    public String getDeliveryText() {
        return DeliveryText;
    }

    public void setDeliveryText(String deliveryText) {
        DeliveryText = deliveryText;
    }

    public ArrayList<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(ArrayList<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public String getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(String totalItems) {
        this.totalItems = totalItems;
    }

    public String getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }

    public String getBillDiscount() {
        return billDiscount;
    }

    public void setBillDiscount(String billDiscount) {
        this.billDiscount = billDiscount;
    }

    public String getBillTotal() {
        return billTotal;
    }

    public void setBillTotal(String billTotal) {
        this.billTotal = billTotal;
    }

    public String getBillPayment() {
        return BillPayment;
    }

    public void setBillPayment(String billPayment) {
        BillPayment = billPayment;
    }



    public String getBillChangeAmount() {
        return billChangeAmount;
    }

    public void setBillChangeAmount(String billChangeAmount) {
        this.billChangeAmount = billChangeAmount;
    }

    public String getClosedSales() {
        return closedSales;
    }

    public void setClosedSales(String closedSales) {
        this.closedSales = closedSales;
    }

    public String getTotalLine() {
        return totalLine;
    }

    public void setTotalLine(String totalLine) {
        this.totalLine = totalLine;
    }

    public String getBillPaymentValue() {
        return BillPaymentValue;
    }

    public void setBillPaymentValue(String billPaymentValue) {
        BillPaymentValue = billPaymentValue;
    }

    public ArrayList<com.dcs.myretailer.app.BillPaymentValue> getBillPaymentDetailsValue() {
        return billPaymentDetailsValue;
    }

    public void setBillPaymentDetailsValue(ArrayList<com.dcs.myretailer.app.BillPaymentValue> billPaymentDetailsValue) {
        this.billPaymentDetailsValue = billPaymentDetailsValue;
    }

}

