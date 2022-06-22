package com.dcs.myretailer.app.domain.mastertables.mastertables.POSSYS;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface PosConfigurationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void SavePossy(PosConfigurationModel posConfigurationModel);

    @Update
    void UpdatePossy(PosConfigurationModel posConfigurationModel);

    @Delete
    void DeletePossy(PosConfigurationModel posConfigurationModel);

    @Query("SELECT * FROM possys")
    <List>PosConfigurationModel getPossy();
}
