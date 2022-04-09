package com.dcs.myretailer.app.Cashier.SalesDelievery;

public class SalesDelivery {
    String SalesDelivery_ID;
    String Type;
     String CoyCode;
    String CompanyID;
    String CompanyAddr;
    String CompanyTel;
    String CompanyFax;
    String RecipientName;
    String RecipientAddr;
     String RecipientPostCode;
     String RecipientAttn;
     String RecipientTel;
     String RecipientFax;
    String OrderStatus;
     String IDRef;
    String TrackingNumber;
    String Date;
    String SN_Ref;
    String INVID;
    String INVRef;
    String INVDate;
    String GstRate;
    String BalSubTotal;
    String BalTax;
    String TotalDiscount;
    String BalTotal;
    String BalPayable;
    String OutStandingBal;
    String LocalBalSubTotal;
    String LocalTax;
    String LocalTotalDiscount;
    String LocalTotal;
    String LocalBalPayable;
    String LocalOutStandingBal;
    String DepositAmount;

    public SalesDelivery(String salesDelivery_ID, String type, String coyCode, String companyID, String companyAddr, String companyTel, String companyFax, String recipientName, String recipientAddr, String recipientPostCode, String recipientAttn, String recipientTel, String recipientFax, String orderStatus, String IDRef, String trackingNumber, String date, String SN_Ref, String INVID, String INVRef, String INVDate, String gstRate, String balSubTotal, String balTax, String totalDiscount, String balTotal, String balPayable, String outStandingBal, String localBalSubTotal, String localTax, String localTotalDiscount, String localTotal, String localBalPayable, String localOutStandingBal, String depositAmount) {
        SalesDelivery_ID = salesDelivery_ID;
        Type = type;
        CoyCode = coyCode;
        CompanyID = companyID;
        CompanyAddr = companyAddr;
        CompanyTel = companyTel;
        CompanyFax = companyFax;
        RecipientName = recipientName;
        RecipientAddr = recipientAddr;
        RecipientPostCode = recipientPostCode;
        RecipientAttn = recipientAttn;
        RecipientTel = recipientTel;
        RecipientFax = recipientFax;
        OrderStatus = orderStatus;
        this.IDRef = IDRef;
        TrackingNumber = trackingNumber;
        Date = date;
        this.SN_Ref = SN_Ref;
        this.INVID = INVID;
        this.INVRef = INVRef;
        this.INVDate = INVDate;
        GstRate = gstRate;
        BalSubTotal = balSubTotal;
        BalTax = balTax;
        TotalDiscount = totalDiscount;
        BalTotal = balTotal;
        BalPayable = balPayable;
        OutStandingBal = outStandingBal;
        LocalBalSubTotal = localBalSubTotal;
        LocalTax = localTax;
        LocalTotalDiscount = localTotalDiscount;
        LocalTotal = localTotal;
        LocalBalPayable = localBalPayable;
        LocalOutStandingBal = localOutStandingBal;
        DepositAmount = depositAmount;
    }

    public String getSalesDelivery_ID() {
        return SalesDelivery_ID;
    }

    public void setSalesDelivery_ID(String salesDelivery_ID) {
        SalesDelivery_ID = salesDelivery_ID;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getCoyCode() {
        return CoyCode;
    }

    public void setCoyCode(String coyCode) {
        CoyCode = coyCode;
    }

    public String getCompanyID() {
        return CompanyID;
    }

    public void setCompanyID(String companyID) {
        CompanyID = companyID;
    }

    public String getCompanyAddr() {
        return CompanyAddr;
    }

    public void setCompanyAddr(String companyAddr) {
        CompanyAddr = companyAddr;
    }

    public String getCompanyTel() {
        return CompanyTel;
    }

    public void setCompanyTel(String companyTel) {
        CompanyTel = companyTel;
    }

    public String getCompanyFax() {
        return CompanyFax;
    }

    public void setCompanyFax(String companyFax) {
        CompanyFax = companyFax;
    }

    public String getRecipientName() {
        return RecipientName;
    }

    public void setRecipientName(String recipientName) {
        RecipientName = recipientName;
    }

    public String getRecipientAddr() {
        return RecipientAddr;
    }

    public void setRecipientAddr(String recipientAddr) {
        RecipientAddr = recipientAddr;
    }

    public String getRecipientPostCode() {
        return RecipientPostCode;
    }

    public void setRecipientPostCode(String recipientPostCode) {
        RecipientPostCode = recipientPostCode;
    }

    public String getRecipientAttn() {
        return RecipientAttn;
    }

    public void setRecipientAttn(String recipientAttn) {
        RecipientAttn = recipientAttn;
    }

    public String getRecipientTel() {
        return RecipientTel;
    }

    public void setRecipientTel(String recipientTel) {
        RecipientTel = recipientTel;
    }

    public String getRecipientFax() {
        return RecipientFax;
    }

    public void setRecipientFax(String recipientFax) {
        RecipientFax = recipientFax;
    }

    public String getOrderStatus() {
        return OrderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        OrderStatus = orderStatus;
    }

    public String getIDRef() {
        return IDRef;
    }

    public void setIDRef(String IDRef) {
        this.IDRef = IDRef;
    }

    public String getTrackingNumber() {
        return TrackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        TrackingNumber = trackingNumber;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getSN_Ref() {
        return SN_Ref;
    }

    public void setSN_Ref(String SN_Ref) {
        this.SN_Ref = SN_Ref;
    }

    public String getINVID() {
        return INVID;
    }

    public void setINVID(String INVID) {
        this.INVID = INVID;
    }

    public String getINVRef() {
        return INVRef;
    }

    public void setINVRef(String INVRef) {
        this.INVRef = INVRef;
    }

    public String getINVDate() {
        return INVDate;
    }

    public void setINVDate(String INVDate) {
        this.INVDate = INVDate;
    }

    public String getGstRate() {
        return GstRate;
    }

    public void setGstRate(String gstRate) {
        GstRate = gstRate;
    }

    public String getBalSubTotal() {
        return BalSubTotal;
    }

    public void setBalSubTotal(String balSubTotal) {
        BalSubTotal = balSubTotal;
    }

    public String getBalTax() {
        return BalTax;
    }

    public void setBalTax(String balTax) {
        BalTax = balTax;
    }

    public String getTotalDiscount() {
        return TotalDiscount;
    }

    public void setTotalDiscount(String totalDiscount) {
        TotalDiscount = totalDiscount;
    }

    public String getBalTotal() {
        return BalTotal;
    }

    public void setBalTotal(String balTotal) {
        BalTotal = balTotal;
    }

    public String getBalPayable() {
        return BalPayable;
    }

    public void setBalPayable(String balPayable) {
        BalPayable = balPayable;
    }

    public String getOutStandingBal() {
        return OutStandingBal;
    }

    public void setOutStandingBal(String outStandingBal) {
        OutStandingBal = outStandingBal;
    }

    public String getLocalBalSubTotal() {
        return LocalBalSubTotal;
    }

    public void setLocalBalSubTotal(String localBalSubTotal) {
        LocalBalSubTotal = localBalSubTotal;
    }

    public String getLocalTax() {
        return LocalTax;
    }

    public void setLocalTax(String localTax) {
        LocalTax = localTax;
    }

    public String getLocalTotalDiscount() {
        return LocalTotalDiscount;
    }

    public void setLocalTotalDiscount(String localTotalDiscount) {
        LocalTotalDiscount = localTotalDiscount;
    }

    public String getLocalTotal() {
        return LocalTotal;
    }

    public void setLocalTotal(String localTotal) {
        LocalTotal = localTotal;
    }

    public String getLocalBalPayable() {
        return LocalBalPayable;
    }

    public void setLocalBalPayable(String localBalPayable) {
        LocalBalPayable = localBalPayable;
    }

    public String getLocalOutStandingBal() {
        return LocalOutStandingBal;
    }

    public void setLocalOutStandingBal(String localOutStandingBal) {
        LocalOutStandingBal = localOutStandingBal;
    }

    public String getDepositAmount() {
        return DepositAmount;
    }

    public void setDepositAmount(String depositAmount) {
        DepositAmount = depositAmount;
    }
}
