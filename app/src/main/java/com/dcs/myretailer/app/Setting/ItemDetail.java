package com.dcs.myretailer.app.Setting;

public class ItemDetail {
    private String itemID;
    private String itemQty;
    private String supBarCode;
    private String itemUOMDesc;
    private String itemPrice;
    private String itemDisc;
    private String itemTax;
    private String itemTotal;
    private String itemplu;
    private String itemproID;
    private String itemproTypeID;
    private String itemproName;

    public ItemDetail() {
        super();
    }

    public ItemDetail(String itemID, String sup_barcode, String itemQty, String itemUOMDesc, String itemPrice, String itemDisc, String itemTax, String itemTotal, String itemplu, String itemproID, String itemproTypeID, String itemproName) {
        this.itemID = itemID;
        this.supBarCode = sup_barcode;
        this.itemQty = itemQty;
        this.itemUOMDesc = itemUOMDesc;
        this.itemPrice = itemPrice;
        this.itemDisc = itemDisc;
        this.itemTax = itemTax;
        this.itemTotal = itemTotal;
        this.itemplu = itemplu;
        this.itemproID = itemproID;
        this.itemproTypeID = itemproTypeID;
        this.itemproName = itemproName;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemQty() {
        return itemQty;
    }

    public void setItemQty(String itemQty) {
        this.itemQty = itemQty;
    }

    public String getItemUOMDesc() {
        return itemUOMDesc;
    }

    public void setItemUOMDesc(String itemUOMDesc) {
        this.itemUOMDesc = itemUOMDesc;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemDisc() {
        return itemDisc;
    }

    public void setItemDisc(String itemDisc) {
        this.itemDisc = itemDisc;
    }

    public String getItemTax() {
        return itemTax;
    }

    public void setItemTax(String itemTax) {
        this.itemTax = itemTax;
    }

    public String getItemTotal() {
        return itemTotal;
    }

    public void setItemTotal(String itemTotal) {
        this.itemTotal = itemTotal;
    }

    public String getItemplu() {
        return itemplu;
    }

    public void setItemplu(String itemplu) {
        this.itemplu = itemplu;
    }

    public String getItemproID() {
        return itemproID;
    }

    public void setItemproID(String itemproID) {
        this.itemproID = itemproID;
    }

    public String getItemproTypeID() {
        return itemproTypeID;
    }

    public void setItemproTypeID(String itemproTypeID) {
        this.itemproTypeID = itemproTypeID;
    }

    public String getItemproName() {
        return itemproName;
    }

    public void setItemproName(String itemproName) {
        this.itemproName = itemproName;
    }

    public String getSupBarCode() {
        return supBarCode;
    }

    public void setSupBarCode(String supBarCode) {
        this.supBarCode = supBarCode;
    }
}

