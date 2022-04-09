package com.dcs.myretailer.app.Setting;

class TaxType {
    Integer ID;
    String Name;
    Integer TaxID;

    public TaxType(Integer ID, String name, Integer taxID) {
        this.ID = ID;
        Name = name;
        TaxID = taxID;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getTaxID() {
        return TaxID;
    }

    public void setTaxID(Integer taxID) {
        TaxID = taxID;
    }
}
