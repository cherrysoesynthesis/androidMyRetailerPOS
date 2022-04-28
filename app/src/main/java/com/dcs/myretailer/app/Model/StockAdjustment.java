package com.dcs.myretailer.app.Model;

import androidx.databinding.BaseObservable;

public class StockAdjustment extends BaseObservable {
    Integer PLUID;
    Integer Qty;
    Integer VarianceQty;
    long DateTime;
    String TransNo;
    String UUID;
    String IDRef;
    String StkAdj_Type;
    String StkAdjDate;
    String StkAdj_Remark;
    String StkAdj_DRemark;
    String TransStatus;

    public StockAdjustment(Integer PLUID, Integer qty, Integer varianceQty, long dateTime,
                           String transNo, String UUID, String IDRef, String stkAdj_Type,
                           String stkAdjDate, String stkAdj_Remark, String stkAdj_DRemark, String transStatus) {
        this.PLUID = PLUID;
        Qty = qty;
        VarianceQty = varianceQty;
        DateTime = dateTime;
        TransNo = transNo;
        this.UUID = UUID;
        this.IDRef = IDRef;
        StkAdj_Type = stkAdj_Type;
        StkAdjDate = stkAdjDate;
        StkAdj_Remark = stkAdj_Remark;
        StkAdj_DRemark = stkAdj_DRemark;
        TransStatus = transStatus;
    }

    public Integer getPLUID() {
        return PLUID;
    }

    public void setPLUID(Integer PLUID) {
        this.PLUID = PLUID;
    }

    public Integer getQty() {
        return Qty;
    }

    public void setQty(Integer qty) {
        Qty = qty;
    }

    public Integer getVarianceQty() {
        return VarianceQty;
    }

    public void setVarianceQty(Integer varianceQty) {
        VarianceQty = varianceQty;
    }

    public long getDateTime() {
        return DateTime;
    }

    public void setDateTime(long dateTime) {
        DateTime = dateTime;
    }

    public String getTransNo() {
        return TransNo;
    }

    public void setTransNo(String transNo) {
        TransNo = transNo;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getIDRef() {
        return IDRef;
    }

    public void setIDRef(String IDRef) {
        this.IDRef = IDRef;
    }

    public String getStkAdj_Type() {
        return StkAdj_Type;
    }

    public void setStkAdj_Type(String stkAdj_Type) {
        StkAdj_Type = stkAdj_Type;
    }

    public String getStkAdjDate() {
        return StkAdjDate;
    }

    public void setStkAdjDate(String stkAdjDate) {
        StkAdjDate = stkAdjDate;
    }

    public String getStkAdj_Remark() {
        return StkAdj_Remark;
    }

    public void setStkAdj_Remark(String stkAdj_Remark) {
        StkAdj_Remark = stkAdj_Remark;
    }

    public String getStkAdj_DRemark() {
        return StkAdj_DRemark;
    }

    public void setStkAdj_DRemark(String stkAdj_DRemark) {
        StkAdj_DRemark = stkAdj_DRemark;
    }

    public String getTransStatus() {
        return TransStatus;
    }

    public void setTransStatus(String transStatus) {
        TransStatus = transStatus;
    }

    //    public StockAdjustment(Integer PLUID, Integer qty, Integer varianceQty, String dateTime, String transNo) {
//        this.PLUID = PLUID;
//        Qty = qty;
//        VarianceQty = varianceQty;
//        DateTime = dateTime;
//        TransNo = transNo;
//    }
//    @Bindable
//    public Integer getPLUID() {
//        return PLUID;
//    }
//
//    public void setPLUID(Integer PLUID) {
//        this.PLUID = PLUID;
//        notifyPropertyChanged(BR.pLUID);
//    }
//    @Bindable
//    public Integer getQty() {
//        return Qty;
//    }
//
//    public void setQty(Integer qty) {
//        Qty = qty;
//        notifyPropertyChanged(BR.qty);
//    }
//    @Bindable
//    public Integer getVarianceQty() {
//        return VarianceQty;
//    }
//
//    public void setVarianceQty(Integer varianceQty) {
//        VarianceQty = varianceQty;
//        notifyPropertyChanged(BR.varianceQty);
//    }
//    @Bindable
//    public String getDateTime() {
//        return DateTime;
//    }
//
//    public void setDateTime(String dateTime) {
//        DateTime = dateTime;
//        notifyPropertyChanged(BR.dateTime);
//    }
//    @Bindable
//    public String getTransNo() {
//        return TransNo;
//    }
//
//    public void setTransNo(String transNo) {
//        TransNo = transNo;
//        notifyPropertyChanged(BR.transNo);
//    }
}
