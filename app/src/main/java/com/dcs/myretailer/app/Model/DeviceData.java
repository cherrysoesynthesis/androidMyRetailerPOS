package com.dcs.myretailer.app.Model;

public class DeviceData {
//    String MODEL = android.os.Build.MODEL; // N910
//    String BOARD = android.os.Build.BOARD; // msm8909
//    String BRAND = android.os.Build.BRAND; // qcom
//    String DEVICE = android.os.Build.DEVICE; // msm8909
//    String DISPLAY = android.os.Build.DISPLAY; // NewLand_N910-48f87c9f7a
//
//    String PRODUCT = android.os.Build.PRODUCT; // msm8909

    Integer ID = 0;
    String MODEL = "";
    String BOARD = "";
    String BRAND = "";
    String DEVICE = "";
    String DISPLAY = "";
    String PRODUCT = "";
    String TERMINAL_TYPE = "";
    String LicenseKey = "";

    public DeviceData(Integer ID, String MODEL, String BOARD, String BRAND, String DEVICE,
                      String DISPLAY, String PRODUCT, String TERMINAL_TYPE, String LicenseKey) {
        this.ID = ID;
        this.MODEL = MODEL;
        this.BOARD = BOARD;
        this.BRAND = BRAND;
        this.DEVICE = DEVICE;
        this.DISPLAY = DISPLAY;
        this.PRODUCT = PRODUCT;
        this.TERMINAL_TYPE = TERMINAL_TYPE;
        this.LicenseKey = LicenseKey;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getMODEL() {
        return MODEL;
    }

    public void setMODEL(String MODEL) {
        this.MODEL = MODEL;
    }

    public String getBOARD() {
        return BOARD;
    }

    public void setBOARD(String BOARD) {
        this.BOARD = BOARD;
    }

    public String getBRAND() {
        return BRAND;
    }

    public void setBRAND(String BRAND) {
        this.BRAND = BRAND;
    }

    public String getDEVICE() {
        return DEVICE;
    }

    public void setDEVICE(String DEVICE) {
        this.DEVICE = DEVICE;
    }

    public String getDISPLAY() {
        return DISPLAY;
    }

    public void setDISPLAY(String DISPLAY) {
        this.DISPLAY = DISPLAY;
    }

    public String getPRODUCT() {
        return PRODUCT;
    }

    public void setPRODUCT(String PRODUCT) {
        this.PRODUCT = PRODUCT;
    }

    public String getTERMINAL_TYPE() {
        return TERMINAL_TYPE;
    }

    public void setTERMINAL_TYPE(String TERMINAL_TYPE) {
        this.TERMINAL_TYPE = TERMINAL_TYPE;
    }

    public String getLicenseKey() {
        return LicenseKey;
    }

    public void setLicenseKey(String licenseKey) {
        LicenseKey = licenseKey;
    }
}
