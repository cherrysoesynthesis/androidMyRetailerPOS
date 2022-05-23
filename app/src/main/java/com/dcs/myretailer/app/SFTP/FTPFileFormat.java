package com.dcs.myretailer.app.SFTP;

public class FTPFileFormat {
    String id;
    String uuid;
    String fileFormatTypeNo;
    String fileGenerateCount;
    String fileGenerateCountString;
    String fileName;
    String FTPSyncUUID;
    String ZReadNo;
    String BillNo;
    long DateTime;

    public FTPFileFormat() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFileFormatTypeNo() {
        return fileFormatTypeNo;
    }

    public void setFileFormatTypeNo(String fileFormatTypeNo) {
        this.fileFormatTypeNo = fileFormatTypeNo;
    }

    public String getFileGenerateCount() {
        return fileGenerateCount;
    }

    public void setFileGenerateCount(String fileGenerateCount) {
        this.fileGenerateCount = fileGenerateCount;
    }

    public String getFileGenerateCountString() {
        return fileGenerateCountString;
    }

    public void setFileGenerateCountString(String fileGenerateCountString) {
        this.fileGenerateCountString = fileGenerateCountString;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFTPSyncUUID() {
        return FTPSyncUUID;
    }

    public void setFTPSyncUUID(String FTPSyncUUID) {
        this.FTPSyncUUID = FTPSyncUUID;
    }

    public String getZReadNo() {
        return ZReadNo;
    }

    public void setZReadNo(String ZReadNo) {
        this.ZReadNo = ZReadNo;
    }

    public String getBillNo() {
        return BillNo;
    }

    public void setBillNo(String billNo) {
        BillNo = billNo;
    }

    public long getDateTime() {
        return DateTime;
    }

    public void setDateTime(long dateTime) {
        DateTime = dateTime;
    }
}