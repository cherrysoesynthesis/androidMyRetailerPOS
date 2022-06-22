package com.dcs.myretailer.app;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tbllicense {

    @SerializedName("CHECKSUM")
    @Expose
    private String checksum;
    @SerializedName("MacAddress")
    @Expose
    private String macAddress;
    @SerializedName("LicenseType")
    @Expose
    private String licenseType;
    @SerializedName("NoOfDay")
    @Expose
    private Integer noOfDay;
    @SerializedName("KeyLicense")
    @Expose
    private Integer KeyLicense;

    public Tbllicense() {
    }

    public Integer getKeyLicense() {
        return KeyLicense;
    }

    public void setKeyLicense(Integer keyLicense) {
        KeyLicense = keyLicense;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
    }

    public Integer getNoOfDay() {
        return noOfDay;
    }

    public void setNoOfDay(Integer noOfDay) {
        this.noOfDay = noOfDay;
    }

}