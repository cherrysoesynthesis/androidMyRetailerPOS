package com.dcs.myretailer.app.Model;

public class BillDiscount {
    Integer ID;
    String uUID;
    String nick;
    String name;
    String type;
    String discountType;
    String discountDate;
    Integer value;
    String option;
    String promoTypeID;
    String billDiscount;
    String amountDiscount;
    String serviceChargeDiscount;
    Integer seq;
    Integer dt;

    public BillDiscount(Integer ID, String uUID, String nick, String name, String type, String discountType, String discountDate, Integer value, String option, String promoTypeID, String billDiscount, String amountDiscount, String serviceChargeDiscount, Integer seq, Integer dt) {
        this.ID = ID;
        this.uUID = uUID;
        this.nick = nick;
        this.name = name;
        this.type = type;
        this.discountType = discountType;
        this.discountDate = discountDate;
        this.value = value;
        this.option = option;
        this.promoTypeID = promoTypeID;
        this.billDiscount = billDiscount;
        this.amountDiscount = amountDiscount;
        this.serviceChargeDiscount = serviceChargeDiscount;
        this.seq = seq;
        this.dt = dt;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getuUID() {
        return uUID;
    }

    public void setuUID(String uUID) {
        this.uUID = uUID;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public String getDiscountDate() {
        return discountDate;
    }

    public void setDiscountDate(String discountDate) {
        this.discountDate = discountDate;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getPromoTypeID() {
        return promoTypeID;
    }

    public void setPromoTypeID(String promoTypeID) {
        this.promoTypeID = promoTypeID;
    }

    public String getBillDiscount() {
        return billDiscount;
    }

    public void setBillDiscount(String billDiscount) {
        this.billDiscount = billDiscount;
    }

    public String getAmountDiscount() {
        return amountDiscount;
    }

    public void setAmountDiscount(String amountDiscount) {
        this.amountDiscount = amountDiscount;
    }

    public String getServiceChargeDiscount() {
        return serviceChargeDiscount;
    }

    public void setServiceChargeDiscount(String serviceChargeDiscount) {
        this.serviceChargeDiscount = serviceChargeDiscount;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }
}
