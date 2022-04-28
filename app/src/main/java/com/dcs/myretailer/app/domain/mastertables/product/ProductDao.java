package com.dcs.myretailer.app.domain.mastertables.product;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


import java.util.List;

@Dao
public interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void SaveProduct(ProductModel productModel);

    @Update
    void UpdateProduct(ProductModel productModel);

    @Delete
    void DeleteProduct(ProductModel productModel);

    @Query("SELECT * FROM product")
    <List> ProductModel getProduct();

//    @Query("SELECT MAX(id) FROM product")
//    Integer getLatestProductID();

    @Query("Select * from product")
    List<ProductModel> getLatestProductID();
}
