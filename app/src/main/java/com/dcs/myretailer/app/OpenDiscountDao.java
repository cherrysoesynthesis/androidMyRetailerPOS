package com.dcs.myretailer.app;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface OpenDiscountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void SaveOpenDiscount(OpenDiscountModel openDiscountModel);

    @Update
    void UpdateOpenDiscount(OpenDiscountModel openDiscountModel);

    @Delete
    void DeleteOpenDiscount(OpenDiscountModel openDiscountModel);

    @Query("SELECT * FROM open_discount")
    List<OpenDiscountModel> GetOpenDiscount();

}
