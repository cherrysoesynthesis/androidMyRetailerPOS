package com.dcs.myretailer.app;

public class BillPaymentValue {
    String BillPaymentValueDetailsName;
    String BillPaymentValueDetailsAmount;
    String BillPaymentValueDetailsRemarks;

    public BillPaymentValue() {
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
