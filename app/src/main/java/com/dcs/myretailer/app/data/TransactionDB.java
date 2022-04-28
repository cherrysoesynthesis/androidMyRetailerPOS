package com.dcs.myretailer.app.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.dcs.myretailer.app.domain.transaction.sales.SalesModel;

@Database(
        entities = {SalesModel.class},
        version = 1,
        exportSchema = false
)
public abstract class TransactionDB extends RoomDatabase {
    private static final String DB_NAME = "transaction_roomdb";
    protected static TransactionDB instance;

    public static synchronized TransactionDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    TransactionDB.class,
                    DB_NAME
            )
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}
