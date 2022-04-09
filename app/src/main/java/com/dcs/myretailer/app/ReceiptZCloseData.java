package com.dcs.myretailer.app;

import java.util.List;

public class ReceiptZCloseData {
    TotalSales totalSales;
    ProductSales productSales;
    CategorySales categorySales;
    List<Payment> payment;
    Discount discount;
    Refund refund;
    Cancellation cancellation;
    String ZCloseStatus;
    String ZClosedt;

    public ReceiptZCloseData() {
    }

    public String getZClosedt() {
        return ZClosedt;
    }

    public void setZClosedt(String ZClosedt) {
        this.ZClosedt = ZClosedt;
    }

    public String getZCloseStatus() {
        return ZCloseStatus;
    }

    public void setZCloseStatus(String ZCloseStatus) {
        this.ZCloseStatus = ZCloseStatus;
    }

    public TotalSales getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(TotalSales totalSales) {
        this.totalSales = totalSales;
    }

    public ProductSales getProductSales() {
        return productSales;
    }

    public void setProductSales(ProductSales productSales) {
        this.productSales = productSales;
    }

    public CategorySales getCategorySales() {
        return categorySales;
    }

    public void setCategorySales(CategorySales categorySales) {
        this.categorySales = categorySales;
    }

    public List<Payment> getPayment() {
        return payment;
    }

    public void setPayment(List<Payment> payment) {
        this.payment = payment;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public Refund getRefund() {
        return refund;
    }

    public void setRefund(Refund refund) {
        this.refund = refund;
    }

    public Cancellation getCancellation() {
        return cancellation;
    }

    public void setCancellation(Cancellation cancellation) {
        this.cancellation = cancellation;
    }
}

