package com.dcs.myretailer.app.Model;

public class CategorySales {
    String QtySold;
    String AMTNett;

    public CategorySales() {
    }

    public String getQtySold() {
        return QtySold;
    }

    public void setQtySold(String qtySold) {
        QtySold = qtySold;
    }

    public String getAMTNett() {
        return AMTNett;
    }

    public void setAMTNett(String AMTNett) {
        this.AMTNett = AMTNett;
    }
}
