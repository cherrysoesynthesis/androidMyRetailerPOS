package com.dcs.myretailer.app;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class ModifierData extends BaseObservable {
    Integer ID;
    String Name;
    String Price;
    Long Dtime;

    public ModifierData(Integer ID, String name, String price, Long dtime) {
        this.ID = ID;
        Name = name;
        Price = price;
        Dtime = dtime;
    }
    @Bindable
    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
        notifyPropertyChanged(BR.iD);
    }

    @Bindable
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
        notifyPropertyChanged(BR.price);
    }

    @Bindable
    public Long getDtime() {
        return Dtime;
    }

    public void setDtime(Long dtime) {
        Dtime = dtime;
        notifyPropertyChanged(BR.dtime);
    }
}
