package com.dcs.myretailer.app.Model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
//import androidx.databinding.library.baseAdapters.BR;


public class StockTake extends BaseObservable {
    Integer SystemQty;
    Integer VarianceQty;
    Integer Qty;
    String TransNo; // STT00001
    Integer PLUID;
    String DateTime;

    public StockTake(Integer systemQty, Integer varianceQty, Integer qty, String transNo, Integer PLUID,String dateTime) {
        SystemQty = systemQty;
        VarianceQty = varianceQty;
        Qty = qty;
        TransNo = transNo;
        PLUID = PLUID;
        DateTime = dateTime;
    }
    @Bindable
    public Integer getSystemQty() {
        return SystemQty;
    }

    public void setSystemQty(Integer systemQty) {
        SystemQty = systemQty;
//        notifyPropertyChanged(BR.systemQty);
    }
    @Bindable
    public Integer getVarianceQty() {
        return VarianceQty;
    }

    public void setVarianceQty(Integer varianceQty) {
        VarianceQty = varianceQty;
//        notifyPropertyChanged(BR.varianceQty);
    }
    @Bindable
    public Integer getQty() {
        return Qty;
    }

    public void setQty(Integer qty) {
        Qty = qty;
//        notifyPropertyChanged(BR.qty);
    }
    @Bindable
    public String getTransNo() {
        return TransNo;
    }

    public void setTransNo(String transNo) {
        TransNo = transNo;
//        notifyPropertyChanged(BR.transNo);
    }
    @Bindable
    public Integer getPLUID() {
        return PLUID;
    }

    public void setPLUID(Integer PLUID) {
        this.PLUID = PLUID;
//        notifyPropertyChanged(BR.pLUID);
    }

    @Bindable
    public String getDateTime() {
        return DateTime;
    }

    public void setDateTime(String dateTime) {
        DateTime = dateTime;
//        notifyPropertyChanged(BR.dateTime);
    }
}
