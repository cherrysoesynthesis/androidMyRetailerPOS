package com.dcs.myretailer.app.KitchenPrinter;

public class KitchenPrinterModel {
    Integer ID;
    String OpenDeviceName;
    String OpenDeviceMacAddress;
    String ConnectionType;
    String PrinterModel;
    String PrinterName;
    String dt;

    public KitchenPrinterModel() {
        this.ID = ID;
        this.OpenDeviceName = OpenDeviceName;
        this.OpenDeviceMacAddress = OpenDeviceMacAddress;
        this.ConnectionType = ConnectionType;
        this.PrinterModel = PrinterModel;
        this.PrinterName = PrinterName;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getOpenDeviceName() {
        return OpenDeviceName;
    }

    public void setOpenDeviceName(String openDeviceName) {
        OpenDeviceName = openDeviceName;
    }

    public String getOpenDeviceMacAddress() {
        return OpenDeviceMacAddress;
    }

    public void setOpenDeviceMacAddress(String openDeviceMacAddress) {
        OpenDeviceMacAddress = openDeviceMacAddress;
    }

    public String getConnectionType() {
        return ConnectionType;
    }

    public void setConnectionType(String connectionType) {
        ConnectionType = connectionType;
    }

    public String getPrinterModel() {
        return PrinterModel;
    }

    public void setPrinterModel(String printerModel) {
        PrinterModel = printerModel;
    }

    public String getPrinterName() {
        return PrinterName;
    }

    public void setPrinterName(String printerName) {
        PrinterName = printerName;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }
}
