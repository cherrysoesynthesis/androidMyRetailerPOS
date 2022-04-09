package com.dcs.myretailer.app.Stock;


import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

//import com.dcs.myretailer.app.BR;


public class StockIn extends BaseObservable {
    Integer PLUID;
    Integer Qty;
    String DateTime;
    String TransNo;


    public StockIn(Integer PLUID, Integer qty, String transNo, String dateTime) {
        this.PLUID = PLUID;
        Qty = qty;
        TransNo = transNo;
        DateTime = dateTime;
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
    public Integer getQty() {
        return Qty;
    }

    public void setQty(Integer qty) {
        Qty = qty;
//        notifyPropertyChanged(BR.qty);
    }
    @Bindable
    public String getDateTime() {
        return DateTime;
    }

    public void setDateTime(String dateTime) {
        DateTime = dateTime;
//        notifyPropertyChanged(BR.dateTime);
    }
    @Bindable
    public String getTransNo() {
        return TransNo;
    }

    public void setTransNo(String transNo) {
        TransNo = transNo;
//        notifyPropertyChanged(BR.transNo);
    }
}
