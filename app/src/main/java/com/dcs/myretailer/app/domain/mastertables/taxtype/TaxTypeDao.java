package com.dcs.myretailer.app.domain.mastertables.mastertables.taxtype;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

@Dao
public interface TaxTypeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void SaveTaxType(com.dcs.myretailer.app.domain.mastertables.mastertables.taxtype.TaxTypeModel taxTypeModel);
}
