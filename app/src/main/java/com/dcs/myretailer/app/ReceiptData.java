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
class BillPaymentValue {
    String BillPaymentValueDetailsName;
    String BillPaymentValueDetailsAmount;
    String BillPaymentValueDetailsRemarks;

    public BillPaymentValue(){
    }

    public String getBillPaymentValueDetailsName() {
        return BillPaymentValueDetailsName;
    }

    public void setBillPaymentValueDetailsName(String billPaymentValueDetailsName) {
        BillPaymentValueDetailsName = billPaymentValueDetailsName;
    }

    public String getBillPaymentValueDetailsAmount() {
        return BillPaymentValueDetailsAmount;
    }

    public void setBillPaymentValueDetailsAmount(String billPaymentValueDetailsAmount) {
        BillPaymentValueDetailsAmount = billPaymentValueDetailsAmount;
    }

    public String getBillPaymentValueDetailsRemarks() {
        return BillPaymentValueDetailsRemarks;
    }

    public void setBillPaymentValueDetailsRemarks(String billPaymentValueDetailsRemarks) {
        BillPaymentValueDetailsRemarks = billPaymentValueDetailsRemarks;
    }
}

class OrderDetails {
    String OderDetailsName;
    String OderDetailsQty;
    String OderDetailPrice;
    String OderDetailDisName;
    String OderDetailDisPrice;

    public OrderDetails() {
    }

    public String getOderDetailsQty() {
        return OderDetailsQty;
    }

    public void setOderDetailsQty(String oderDetailsQty) {
        OderDetailsQty = oderDetailsQty;
    }

    public String getOderDetailPrice() {
        return OderDetailPrice;
    }

    public void setOderDetailPrice(String oderDetailPrice) {
        OderDetailPrice = oderDetailPrice;
    }

    public String getOderDetailDisName() {
        return OderDetailDisName;
    }

    public void setOderDetailDisName(String oderDetailDisName) {
        OderDetailDisName = oderDetailDisName;
    }

    public String getOderDetailDisPrice() {
        return OderDetailDisPrice;
    }

    public void setOderDetailDisPrice(String oderDetailDisPrice) {
        OderDetailDisPrice = oderDetailDisPrice;
    }
}
class MercatusReceiptData {
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

class JeripayReceiptData {
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
