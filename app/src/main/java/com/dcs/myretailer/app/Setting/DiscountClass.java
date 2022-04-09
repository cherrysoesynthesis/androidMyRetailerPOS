package com.dcs.myretailer.app.Setting;

public class DiscountClass {
    Integer Discount_ID;
    String Discount_Name;
    String Discount_Value;
    String Discount_Options;
    Integer Discount_Seq;
    String DiscountType;
    String Type;
    String OpenDiscountStatus;

    public DiscountClass(Integer discount_ID, String discount_Name, String discount_Value, String discount_Options, Integer discount_Seq, String discountType, String type, String openDiscountStatus) {
        Discount_ID = discount_ID;
        Discount_Name = discount_Name;
        Discount_Value = discount_Value;
        Discount_Options = discount_Options;
        Discount_Seq = discount_Seq;
        DiscountType = discountType;
        Type = type;
        OpenDiscountStatus = openDiscountStatus;
    }

    public Integer getDiscount_ID() {
        return Discount_ID;
    }

    public void setDiscount_ID(Integer discount_ID) {
        Discount_ID = discount_ID;
    }

    public String getDiscount_Name() {
        return Discount_Name;
    }

    public void setDiscount_Name(String discount_Name) {
        Discount_Name = discount_Name;
    }

    public String getDiscount_Value() {
        return Discount_Value;
    }

    public void setDiscount_Value(String discount_Value) {
        Discount_Value = discount_Value;
    }

    public String getDiscount_Options() {
        return Discount_Options;
    }

    public void setDiscount_Options(String discount_Options) {
        Discount_Options = discount_Options;
    }

    public Integer getDiscount_Seq() {
        return Discount_Seq;
    }

    public void setDiscount_Seq(Integer discount_Seq) {
        Discount_Seq = discount_Seq;
    }

    public String getDiscountType() {
        return DiscountType;
    }

    public void setDiscountType(String discountType) {
        DiscountType = discountType;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getOpenDiscountStatus() {
        return OpenDiscountStatus;
    }

    public void setOpenDiscountStatus(String openDiscountStatus) {
        OpenDiscountStatus = openDiscountStatus;
    }
}
