package com.dcs.myretailer.app.domain.transaction.sales;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

@Dao
public interface SalesModelDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void SaveSales(SalesModel salesModel);
}
