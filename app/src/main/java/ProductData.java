import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.dcs.myretailer.app.BR;


public class ProductData extends BaseObservable {
    Integer ID;
    String Name;
    String UUID;
    String Name2;
    String DeptID;
    String Price;
    String Option;
    String Code;
    String Image;
    String base64imgValue;
    String ImageItemID;
    String ImageUrl;
    String ImageType;
    String ImageFileName;
    String ProductVariant;
    String ProductModifiers;
    String AllowNameQuickEdit;
    String AllowPriceQuickEdit;
    String AllowOpenPrice;
    String ProductCategoryID;
    String ProductCategoryName;
    String ProductTaxID;
    String ProductTaxName;
    String OnHandQty;
    String Dt;
    String Condi_Seq;

    public ProductData(Integer ID, String name, String UUID, String name2, String deptID, String price, String option, String code, String image, String base64imgValue, String imageItemID, String imageUrl, String imageType, String imageFileName, String productVariant, String productModifiers, String allowNameQuickEdit, String allowPriceQuickEdit, String allowOpenPrice, String productCategoryID, String productCategoryName, String productTaxID, String productTaxName, String onHandQty, String dt, String condi_Seq) {
        this.ID = ID;
        Name = name;
        this.UUID = UUID;
        Name2 = name2;
        DeptID = deptID;
        Price = price;
        Option = option;
        Code = code;
        Image = image;
        this.base64imgValue = base64imgValue;
        ImageItemID = imageItemID;
        ImageUrl = imageUrl;
        ImageType = imageType;
        ImageFileName = imageFileName;
        ProductVariant = productVariant;
        ProductModifiers = productModifiers;
        AllowNameQuickEdit = allowNameQuickEdit;
        AllowPriceQuickEdit = allowPriceQuickEdit;
        AllowOpenPrice = allowOpenPrice;
        ProductCategoryID = productCategoryID;
        ProductCategoryName = productCategoryName;
        ProductTaxID = productTaxID;
        ProductTaxName = productTaxName;
        OnHandQty = onHandQty;
        Dt = dt;
        Condi_Seq = condi_Seq;
    }

    @Bindable
    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
        notifyPropertyChanged(BR.iD);
    }

    @Bindable
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
        notifyPropertyChanged(BR.uUID);
    }

    @Bindable
    public String getName2() {
        return Name2;
    }

    public void setName2(String name2) {
        Name2 = name2;
        notifyPropertyChanged(BR.name2);
    }

    @Bindable
    public String getDeptID() {
        return DeptID;
    }

    public void setDeptID(String deptID) {
        DeptID = deptID;
        notifyPropertyChanged(BR.deptID);
    }

    @Bindable
    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
        notifyPropertyChanged(BR.price);
    }

    @Bindable
    public String getOption() {
        return Option;
    }

    public void setOption(String option) {
        Option = option;
        notifyPropertyChanged(BR.option);
    }

    @Bindable
    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
        notifyPropertyChanged(BR.code);
    }

    @Bindable
    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
        notifyPropertyChanged(BR.image);
    }

    @Bindable
    public String getBase64imgValue() {
        return base64imgValue;
    }

    public void setBase64imgValue(String base64imgValue) {
        this.base64imgValue = base64imgValue;
        notifyPropertyChanged(BR.base64imgValue);
    }

    @Bindable
    public String getImageItemID() {
        return ImageItemID;
    }

    public void setImageItemID(String imageItemID) {
        ImageItemID = imageItemID;
        notifyPropertyChanged(BR.imageItemID);
    }

    @Bindable
    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
        notifyPropertyChanged(BR.imageUrl);
    }

    @Bindable
    public String getImageType() {
        return ImageType;
    }

    public void setImageType(String imageType) {
        ImageType = imageType;
        notifyPropertyChanged(BR.imageType);
    }

    @Bindable
    public String getImageFileName() {
        return ImageFileName;
    }

    public void setImageFileName(String imageFileName) {
        ImageFileName = imageFileName;
        notifyPropertyChanged(BR.imageFileName);
    }

    @Bindable
    public String getProductVariant() {
        return ProductVariant;
    }

    public void setProductVariant(String productVariant) {
        ProductVariant = productVariant;
        notifyPropertyChanged(BR.productVariant);
    }

    @Bindable
    public String getProductModifiers() {
        return ProductModifiers;
    }

    public void setProductModifiers(String productModifiers) {
        ProductModifiers = productModifiers;
        notifyPropertyChanged(BR.productModifiers);
    }

    @Bindable
    public String getAllowNameQuickEdit() {
        return AllowNameQuickEdit;
    }

    public void setAllowNameQuickEdit(String allowNameQuickEdit) {
        AllowNameQuickEdit = allowNameQuickEdit;
        notifyPropertyChanged(BR.allowNameQuickEdit);
    }

    @Bindable
    public String getAllowPriceQuickEdit() {
        return AllowPriceQuickEdit;
    }

    public void setAllowPriceQuickEdit(String allowPriceQuickEdit) {
        AllowPriceQuickEdit = allowPriceQuickEdit;
        notifyPropertyChanged(BR.allowPriceQuickEdit);
    }

    @Bindable
    public String getAllowOpenPrice() {
        return AllowOpenPrice;
    }

    public void setAllowOpenPrice(String allowOpenPrice) {
        AllowOpenPrice = allowOpenPrice;
        notifyPropertyChanged(BR.allowOpenPrice);
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
    public String getProductTaxID() {
        return ProductTaxID;
    }

    public void setProductTaxID(String productTaxID) {
        ProductTaxID = productTaxID;
        notifyPropertyChanged(BR.productTaxID);
    }

    @Bindable
    public String getProductTaxName() {
        return ProductTaxName;
    }

    public void setProductTaxName(String productTaxName) {
        ProductTaxName = productTaxName;
        notifyPropertyChanged(BR.productTaxName);
    }

    @Bindable
    public String getOnHandQty() {
        return OnHandQty;
    }

    public void setOnHandQty(String onHandQty) {
        OnHandQty = onHandQty;
        notifyPropertyChanged(BR.onHandQty);
    }

    @Bindable
    public String getDt() {
        return Dt;
    }

    public void setDt(String dt) {
        Dt = dt;
        notifyPropertyChanged(BR.dt);
    }

    @Bindable
    public String getCondi_Seq() {
        return Condi_Seq;
    }

    public void setCondi_Seq(String condi_Seq) {
        Condi_Seq = condi_Seq;
        notifyPropertyChanged(BR.condi_Seq);

    }
}
