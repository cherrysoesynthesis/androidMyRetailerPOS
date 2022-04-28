package com.dcs.myretailer.app.Setting;

public class PaymentTypes {
   String Name;
   Integer STATUS;

    public PaymentTypes(String name, Integer STATUS) {
        Name = name;
        this.STATUS = STATUS;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(Integer STATUS) {
        this.STATUS = STATUS;
    }
}
