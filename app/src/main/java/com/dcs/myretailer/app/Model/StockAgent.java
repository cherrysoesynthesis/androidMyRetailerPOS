package com.dcs.myretailer.app.Model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
//import androidx.databinding.library.baseAdapters.BR;


public class StockAgent extends BaseObservable {
    Integer QtySold;
    Integer QtyAdjustment;
    Integer QtyReturn;
    Integer QtyBalance;
    Integer QtyActual;
    Integer PLUID;
    String DateTime;

    public StockAgent(Integer qtySold, Integer qtyAdjustment, Integer qtyReturn, Integer qtyBalance, Integer qtyActual, Integer PLUID, String dateTime) {
        QtySold = qtySold;
        QtyAdjustment = qtyAdjustment;
        QtyReturn = qtyReturn;
        QtyBalance = qtyBalance;
        QtyActual = qtyActual;
        this.PLUID = PLUID;
        DateTime = dateTime;
    }
    @Bindable
    public Integer getQtySold() {
        return QtySold;
    }

    public void setQtySold(Integer qtySold) {
        QtySold = qtySold;
//        notifyPropertyChanged(BR.qtySold);
    }
    @Bindable
    public Integer getQtyAdjustment() {
        return QtyAdjustment;
    }

    public void setQtyAdjustment(Integer qtyAdjustment) {
        QtyAdjustment = qtyAdjustment;
//        notifyPropertyChanged(BR.qtyAdjustment);
    }
    @Bindable
    public Integer getQtyReturn() {
        return QtyReturn;
    }

    public void setQtyReturn(Integer qtyReturn) {
        QtyReturn = qtyReturn;
//        notifyPropertyChanged(BR.qtyReturn);
    }
    @Bindable
    public Integer getQtyBalance() {
        return QtyBalance;
    }

    public void setQtyBalance(Integer qtyBalance) {
        QtyBalance = qtyBalance;
//        notifyPropertyChanged(BR.qtyBalance);
    }
    @Bindable
    public Integer getQtyActual() {
        return QtyActual;
    }

    public void setQtyActual(Integer qtyActual) {
        QtyActual = qtyActual;
//        notifyPropertyChanged(BR.qtyActual);
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
