package com.dcs.myretailer.app;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class ProductData extends BaseObservable {

    private String Title;
    private String Price;
    private String Category ;
    private String Description ;
    //private Bitmap Thumbnail ;
    private String Thumbnail ;
    private String ProductID ;
    private String ProductCategoryID ;
    private String ProductCategoryName ;
    private String OpenPrice ;
    private String Remarks ;
    private String UUID ;

    public ProductData(String title, String price, String category, String description, String thumbnail, String productID, String productCategoryID,
                       String productCategoryName, String uUID, String openPrice, String remarks) {
        this.Title = title;
        this.Price = price;
        this.Category = category;
        this.Description = description;
        this.Thumbnail = thumbnail;
        this.ProductID = productID;
        this.ProductCategoryID = productCategoryID;
        this.ProductCategoryName = productCategoryName;
        this.UUID = uUID;
        this.OpenPrice = openPrice;
        this.Remarks = remarks;
    }
    @Bindable
    public String getTitle() {
        return Title;
    }
    @Bindable
    public String getPrice() {
        return Price;
    }
    @Bindable
    public String getCategory() {
        return Category;
    }
    @Bindable
    public String getDescription() {
        return Description;
    }
    @Bindable
    public String getThumbnail() {
        return Thumbnail;
    }


    public void setTitle(String title) {
        Title = title;
        notifyPropertyChanged(BR.title);
    }

    public void setPrice(String price) {
        Price = price;
        notifyPropertyChanged(BR.price);
    }

    public void setCategory(String category) {
        Category = category;
        notifyPropertyChanged(BR.category);
    }

    public void setDescription(String description) {
        Description = description;
        notifyPropertyChanged(BR.description);
    }

    public void setThumbnail(String thumbnail) {
        Thumbnail = thumbnail;
        notifyPropertyChanged(BR.thumbnail);
    }
    @Bindable
    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String productID) {
        ProductID = productID;
        notifyPropertyChanged(BR.productID);
    }
    @Bindable
    public String getProductCategoryID() {
        return ProductCategoryID;
    }

    public void setProductCategoryID(String productCategoryID) {
        ProductCategoryID = productCategoryID;
        notifyPropertyChanged(BR.productCategoryID);
    }
    @Bindable
    public String getProductCategoryName() {
        return ProductCategoryName;
    }

    public void setProductCategoryName(String productCategoryName) {
        ProductCategoryName = productCategoryName;
        notifyPropertyChanged(BR.productCategoryName);
    }
    @Bindable
    public String getOpenPrice() {
        return OpenPrice;
    }

    public void setOpenPrice(String openPrice) {
        OpenPrice = openPrice;
        notifyPropertyChanged(BR.openPrice);
    }
    @Bindable
    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
        notifyPropertyChanged(BR.remarks);
    }
    @Bindable
    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
        notifyPropertyChanged(BR.uUID);
    }
}