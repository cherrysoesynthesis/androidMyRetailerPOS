package com.dcs.myretailer.app;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "open_discount")
public class OpenDiscountModel {
    @PrimaryKey(autoGenerate = true)
    Integer id;
    @ColumnInfo(name = "uuid")
    String uuid;
    @ColumnInfo(name = "open_discount_name")
    String openDiscountName;

    @ColumnInfo(name = "open_discount_value")
    String openDiscountValue;

    @ColumnInfo(name = "open_discount_type")
    String openDiscountType;


    @ColumnInfo(name = "created_datetime")
    String createdDt;

    public OpenDiscountModel(Integer id, String uuid, String openDiscountName, String openDiscountValue, String openDiscountType, String createdDt) {
        this.id = id;
        this.uuid = uuid;
        this.openDiscountName = openDiscountName;
        this.openDiscountValue = openDiscountValue;
        this.openDiscountType = openDiscountType;
        this.createdDt = createdDt;
    }
}
