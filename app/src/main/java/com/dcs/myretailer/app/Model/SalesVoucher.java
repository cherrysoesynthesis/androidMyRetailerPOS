package com.dcs.myretailer.app.Model;

public class SalesVoucher {
    public static Integer ID;
    public static String uuid;
    public static String billno;
    public static String voucherNo;
    public static String Date;
    public static Integer DateTime;

    public SalesVoucher() {
    }

    public static Integer getID() {
        return ID;
    }

    public static void setID(Integer ID) {
        SalesVoucher.ID = ID;
    }

    public static String getUuid() {
        return uuid;
    }

    public static void setUuid(String uuid) {
        SalesVoucher.uuid = uuid;
    }

    public static String getBillno() {
        return billno;
    }

    public static void setBillno(String billno) {
        SalesVoucher.billno = billno;
    }

    public static String getVoucherNo() {
        return voucherNo;
    }

    public static void setVoucherNo(String voucherNo) {
        SalesVoucher.voucherNo = voucherNo;
    }

    public static String getDate() {
        return Date;
    }

    public static void setDate(String date) {
        Date = date;
    }

    public static Integer getDateTime() {
        return DateTime;
    }

    public static void setDateTime(Integer dateTime) {
        DateTime = dateTime;
    }
}
