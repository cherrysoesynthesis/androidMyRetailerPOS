//package com.dcs.myretailer.app.data;
//
//import android.content.Context;
//
//import androidx.room.Database;
//import androidx.room.Room;
//import androidx.room.RoomDatabase;
//
//import com.dcs.myretailer.app.OpenDiscountModel;
//import com.dcs.myretailer.app.domain.mastertables.mastertables.POSSYS.PosConfigurationDao;
//import com.dcs.myretailer.app.domain.mastertables.mastertables.POSSYS.PosConfigurationModel;
//import com.dcs.myretailer.app.domain.mastertables.mastertables.plukitchen.PLUKPrinterDao;
//import com.dcs.myretailer.app.domain.mastertables.mastertables.tax.TaxDao;
//import com.dcs.myretailer.app.domain.mastertables.mastertables.tax.TaxModel;
//import com.dcs.myretailer.app.domain.mastertables.plukitchen.PLUKPrinterModel;
//import com.dcs.myretailer.app.domain.mastertables.product.ProductDao;
//import com.dcs.myretailer.app.domain.mastertables.product.ProductModel;
//
//@Database(
//        entities = {OpenDiscountModel.class,PosConfigurationModel.class,
//                ProductModel.class, PLUKPrinterModel.class, TaxModel.class},
//        version = 1,
//        exportSchema = false
//)
//public abstract class MasterDB extends RoomDatabase {
//    private static final String DB_NAME = "master_roomdb";
//    protected static MasterDB instance;
//
//    public static synchronized MasterDB getInstance(Context context) {
//        if (instance == null) {
//            instance = Room.databaseBuilder(
//                    context.getApplicationContext(),
//                    MasterDB.class,
//                    DB_NAME
//            )
//                    .fallbackToDestructiveMigration()
//                    .build();
//        }
//        return instance;
//    }
//
//    public abstract ProductDao productDao();
//    public abstract PosConfigurationDao posConfigurationDao();
//    public abstract PLUKPrinterDao plukPrinterDao();
//    public abstract TaxDao taxDao();
//}
