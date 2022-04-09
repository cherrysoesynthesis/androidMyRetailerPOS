package com.dcs.myretailer.app.Checkout;

public class PaymentTypes {
    String PaymentID;
    String PaymentName;
    String SwitchSTATUS;
    String DisallowEmptyCash;
    String LinktoPaymentApp;
    String IntegratetoShoptima;
    String RoundingActivate;
    String FullAmount;
    String EzLink;

    public PaymentTypes(String paymentID, String paymentName, String switchSTATUS, String disallowEmptyCash,
                        String linktoPaymentApp, String integratetoShoptima, String roundingActivate,
                        String fullAmount, String ezLink) {
        PaymentID = paymentID;
        PaymentName = paymentName;
        SwitchSTATUS = switchSTATUS;
        DisallowEmptyCash = disallowEmptyCash;
        LinktoPaymentApp = linktoPaymentApp;
        IntegratetoShoptima = integratetoShoptima;
        RoundingActivate = roundingActivate;
        FullAmount = fullAmount;
        EzLink = ezLink;
    }

    public String getPaymentID() {
        return PaymentID;
    }

    public void setPaymentID(String paymentID) {
        PaymentID = paymentID;
    }

    public String getPaymentName() {
        return PaymentName;
    }

    public void setPaymentName(String paymentName) {
        PaymentName = paymentName;
    }

    public String getSwitchSTATUS() {
        return SwitchSTATUS;
    }

    public void setSwitchSTATUS(String switchSTATUS) {
        SwitchSTATUS = switchSTATUS;
    }

    public String getDisallowEmptyCash() {
        return DisallowEmptyCash;
    }

    public void setDisallowEmptyCash(String disallowEmptyCash) {
        DisallowEmptyCash = disallowEmptyCash;
    }

    public String getLinktoPaymentApp() {
        return LinktoPaymentApp;
    }

    public void setLinktoPaymentApp(String linktoPaymentApp) {
        LinktoPaymentApp = linktoPaymentApp;
    }

    public String getIntegratetoShoptima() {
        return IntegratetoShoptima;
    }

    public void setIntegratetoShoptima(String integratetoShoptima) {
        IntegratetoShoptima = integratetoShoptima;
    }

    public String getRoundingActivate() {
        return RoundingActivate;
    }

    public void setRoundingActivate(String roundingActivate) {
        RoundingActivate = roundingActivate;
    }

    public String getFullAmount() {
        return FullAmount;
    }

    public void setFullAmount(String fullAmount) {
        FullAmount = fullAmount;
    }

    public String getEzLink() {
        return EzLink;
    }

    public void setEzLink(String ezLink) {
        EzLink = ezLink;
    }
}
