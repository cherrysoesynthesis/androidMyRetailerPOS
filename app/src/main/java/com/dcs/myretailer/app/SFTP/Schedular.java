package com.dcs.myretailer.app.SFTP;

public class Schedular {
    Integer id;
    String uuid;
    String filePath;
    String fileName;
    String setTime;
    String status;
    String salesdt;
    String salesdtstr;
    long dt;

    public Schedular() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSetTime() {
        return setTime;
    }

    public void setSetTime(String setTime) {
        this.setTime = setTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSalesdt() {
        return salesdt;
    }

    public void setSalesdt(String salesdt) {
        this.salesdt = salesdt;
    }

    public String getSalesdtstr() {
        return salesdtstr;
    }

    public void setSalesdtstr(String salesdtstr) {
        this.salesdtstr = salesdtstr;
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }
}
