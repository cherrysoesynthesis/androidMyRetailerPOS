package com.dcs.myretailer.app.domain.mastertables.mastertables.tax;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

@Dao
public interface TaxDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void SaveTax(TaxModel taxModel);
}
