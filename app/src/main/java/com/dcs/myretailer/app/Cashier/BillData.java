package com.dcs.myretailer.app.Cashier;

public class BillData {
    String tableName;
    String occupiedName;
    String paxName;
    String tableName1;
    String occupiedName1;
    String paxName1;

    public BillData(String tableName, String occupiedName, String paxName, String tableName1, String occupiedName1, String paxName1) {
        this.tableName = tableName;
        this.occupiedName = occupiedName;
        this.paxName = paxName;
        this.tableName1 = tableName1;
        this.occupiedName1 = occupiedName1;
        this.paxName1 = paxName1;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getOccupiedName() {
        return occupiedName;
    }

    public void setOccupiedName(String occupiedName) {
        this.occupiedName = occupiedName;
    }

    public String getPaxName() {
        return paxName;
    }

    public void setPaxName(String paxName) {
        this.paxName = paxName;
    }

    public String getTableName1() {
        return tableName1;
    }

    public void setTableName1(String tableName1) {
        this.tableName1 = tableName1;
    }

    public String getOccupiedName2() {
        return occupiedName1;
    }

    public void setOccupiedName2(String occupiedName2) {
        this.occupiedName1 = occupiedName2;
    }

    public String getPaxName3() {
        return paxName1;
    }

    public void setPaxName3(String paxName3) {
        this.paxName1 = paxName3;
    }
}
