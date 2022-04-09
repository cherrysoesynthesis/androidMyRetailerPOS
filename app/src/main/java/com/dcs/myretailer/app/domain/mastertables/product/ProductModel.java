package com.dcs.myretailer.app.domain.mastertables.product;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "product")
public class ProductModel {
    @PrimaryKey(autoGenerate = true)
    Integer id;

    String name;

    String uUID;

    String name2;

    String kitchen_Printer;

    String secondaryName;

    Integer deptID;

    Double price;

    String option;

    String code;

    String image;

    String imageItemID;

    String imageUrl;

    String imageType;

    String imageFileName;

    String base64imgValue;

    String onHandQty;

    String productVariant;

    String productModifiers;

    String productCategoryID;

    String productCategoryName;

    String productTaxID;

    String productTaxName;

    Integer allowNameQuickEdit;

    Integer allowPriceQuickEdit;

    Integer allowOpenPrice;

    Integer receiptPrinter;

    Integer condi_Seq;
    Long dateTime;

    public ProductModel(String name, String uUID, String name2, String kitchen_Printer, String secondaryName, Integer deptID, Double price, String option, String code, String image, String imageItemID, String imageUrl, String imageType, String imageFileName, String base64imgValue, String onHandQty, String productVariant, String productModifiers, String productCategoryID, String productCategoryName, String productTaxID, String productTaxName, Integer allowNameQuickEdit, Integer allowPriceQuickEdit, Integer allowOpenPrice, Integer receiptPrinter, Integer condi_Seq, Long dateTime) {
        this.name = name;
        this.uUID = uUID;
        this.name2 = name2;
        this.kitchen_Printer = kitchen_Printer;
        this.secondaryName = secondaryName;
        this.deptID = deptID;
        this.price = price;
        this.option = option;
        this.code = code;
        this.image = image;
        this.imageItemID = imageItemID;
        this.imageUrl = imageUrl;
        this.imageType = imageType;
        this.imageFileName = imageFileName;
        this.base64imgValue = base64imgValue;
        this.onHandQty = onHandQty;
        this.productVariant = productVariant;
        this.productModifiers = productModifiers;
        this.productCategoryID = productCategoryID;
        this.productCategoryName = productCategoryName;
        this.productTaxID = productTaxID;
        this.productTaxName = productTaxName;
        this.allowNameQuickEdit = allowNameQuickEdit;
        this.allowPriceQuickEdit = allowPriceQuickEdit;
        this.allowOpenPrice = allowOpenPrice;
        this.receiptPrinter = receiptPrinter;
        this.condi_Seq = condi_Seq;
        this.dateTime = dateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getuUID() {
        return uUID;
    }

    public void setuUID(String uUID) {
        this.uUID = uUID;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getKitchen_Printer() {
        return kitchen_Printer;
    }

    public void setKitchen_Printer(String kitchen_Printer) {
        this.kitchen_Printer = kitchen_Printer;
    }

    public String getSecondaryName() {
        return secondaryName;
    }

    public void setSecondaryName(String secondaryName) {
        this.secondaryName = secondaryName;
    }

    public Integer getDeptID() {
        return deptID;
    }

    public void setDeptID(Integer deptID) {
        this.deptID = deptID;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageItemID() {
        return imageItemID;
    }

    public void setImageItemID(String imageItemID) {
        this.imageItemID = imageItemID;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public String getBase64imgValue() {
        return base64imgValue;
    }

    public void setBase64imgValue(String base64imgValue) {
        this.base64imgValue = base64imgValue;
    }

    public String getOnHandQty() {
        return onHandQty;
    }

    public void setOnHandQty(String onHandQty) {
        this.onHandQty = onHandQty;
    }

    public String getProductVariant() {
        return productVariant;
    }

    public void setProductVariant(String productVariant) {
        this.productVariant = productVariant;
    }

    public String getProductModifiers() {
        return productModifiers;
    }

    public void setProductModifiers(String productModifiers) {
        this.productModifiers = productModifiers;
    }

    public String getProductCategoryID() {
        return productCategoryID;
    }

    public void setProductCategoryID(String productCategoryID) {
        this.productCategoryID = productCategoryID;
    }

    public String getProductCategoryName() {
        return productCategoryName;
    }

    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }

    public String getProductTaxID() {
        return productTaxID;
    }

    public void setProductTaxID(String productTaxID) {
        this.productTaxID = productTaxID;
    }

    public String getProductTaxName() {
        return productTaxName;
    }

    public void setProductTaxName(String productTaxName) {
        this.productTaxName = productTaxName;
    }

    public Integer getAllowNameQuickEdit() {
        return allowNameQuickEdit;
    }

    public void setAllowNameQuickEdit(Integer allowNameQuickEdit) {
        this.allowNameQuickEdit = allowNameQuickEdit;
    }

    public Integer getAllowPriceQuickEdit() {
        return allowPriceQuickEdit;
    }

    public void setAllowPriceQuickEdit(Integer allowPriceQuickEdit) {
        this.allowPriceQuickEdit = allowPriceQuickEdit;
    }

    public Integer getAllowOpenPrice() {
        return allowOpenPrice;
    }

    public void setAllowOpenPrice(Integer allowOpenPrice) {
        this.allowOpenPrice = allowOpenPrice;
    }

    public Integer getReceiptPrinter() {
        return receiptPrinter;
    }

    public void setReceiptPrinter(Integer receiptPrinter) {
        this.receiptPrinter = receiptPrinter;
    }

    public Integer getCondi_Seq() {
        return condi_Seq;
    }

    public void setCondi_Seq(Integer condi_Seq) {
        this.condi_Seq = condi_Seq;
    }

    public Long getDateTime() {
        return dateTime;
    }

    public void setDateTime(Long dateTime) {
        this.dateTime = dateTime;
    }
}
//
//@Entity(tableName = "product")
//public class ProductModel {
//    @PrimaryKey(autoGenerate = true)
//    Integer id;
//
//    @ColumnInfo(name = "Name")
//    String Name;
//
//    @ColumnInfo(name = "uuid")
//    String UUID;
//
//    @ColumnInfo(name = "name2")
//    String Name2;
//
//    @ColumnInfo(name = "kitchen_printer")
//    String Kitchen_Printer;
//
//    @ColumnInfo(name = "secondary_name")
//    String SecondaryName;
//
//    @ColumnInfo(name = "dept_id")
//    Integer DeptID;
//
//    @ColumnInfo(name = "price")
//    Double Price;
//
//    @ColumnInfo(name = "option")
//    String Option;
//
//    @ColumnInfo(name = "code")
//    String Code;
//
//    @ColumnInfo(name = "image")
//    String Image;
//
//    @ColumnInfo(name = "image_item_id")
//    String ImageItemID;
//
//    @ColumnInfo(name = "image_url")
//    String ImageUrl;
//
//    @ColumnInfo(name = "image_type")
//    String ImageType;
//
//    @ColumnInfo(name = "image_file_name")
//    String ImageFileName;
//
//    @ColumnInfo(name = "base64_img_value")
//    String base64imgValue;
//
//    @ColumnInfo(name = "onhandqty")
//    String OnHandQty;
//
//    @ColumnInfo(name = "product_variant")
//    String ProductVariant;
//
//    @ColumnInfo(name = "product_modifier")
//    String ProductModifiers;
//
//    @ColumnInfo(name = "product_category_id")
//    String ProductCategoryID;
//
//    @ColumnInfo(name = "product_category_name")
//    String ProductCategoryName;
//
//    @ColumnInfo(name = "product_tax_id")
//    String ProductTaxID;
//
//    @ColumnInfo(name = "product_tax_name")
//    String ProductTaxName;
//
//    @ColumnInfo(name = "allow_name_quick_edit")
//    Integer AllowNameQuickEdit;
//
//    @ColumnInfo(name = "allow_price_quick_edit")
//    Integer AllowPriceQuickEdit;
//
//    @ColumnInfo(name = "allow_open_price")
//    Integer AllowOpenPrice;
//
//    @ColumnInfo(name = "receipt_printer")
//    Integer ReceiptPrinter;
//
//    @ColumnInfo(name = "condi_sql")
//    Integer Condi_Seq;
//
//    @ColumnInfo(name = "datetime")
//    Long DateTime;
//
//    public ProductModel(Integer id, String name, String UUID, String name2, String kitchen_Printer, String secondaryName, Integer deptID, Double price, String option, String code, String image, String imageItemID, String imageUrl, String imageType, String imageFileName, String base64imgValue, String onHandQty, String productVariant, String productModifiers, String productCategoryID, String productCategoryName, String productTaxID, String productTaxName, Integer allowNameQuickEdit, Integer allowPriceQuickEdit, Integer allowOpenPrice, Integer receiptPrinter, Integer condi_Seq, Long dateTime) {
//        this.id = id;
//        this.Name = name;
//        this.UUID = UUID;
//        this.Name2 = name2;
//        this.Kitchen_Printer = kitchen_Printer;
//        this.SecondaryName = secondaryName;
//        this.DeptID = deptID;
//        this.Price = price;
//        this.Option = option;
//        this.Code = code;
//        this.Image = image;
//        this.ImageItemID = imageItemID;
//        this.ImageUrl = imageUrl;
//        this.ImageType = imageType;
//        this.ImageFileName = imageFileName;
//        this.base64imgValue = base64imgValue;
//        this.OnHandQty = onHandQty;
//        this.ProductVariant = productVariant;
//        this.ProductModifiers = productModifiers;
//        this.ProductCategoryID = productCategoryID;
//        this.ProductCategoryName = productCategoryName;
//        this.ProductTaxID = productTaxID;
//        this.ProductTaxName = productTaxName;
//        this.AllowNameQuickEdit = allowNameQuickEdit;
//        this.AllowPriceQuickEdit = allowPriceQuickEdit;
//        this.AllowOpenPrice = allowOpenPrice;
//        this.ReceiptPrinter = receiptPrinter;
//        this.Condi_Seq = condi_Seq;
//        this.DateTime = dateTime;
//    }
//}
