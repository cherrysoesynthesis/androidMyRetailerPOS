//package com.dcs.myretailer.app;
//
//import android.content.Context;
//
//import androidx.room.Database;
//import androidx.room.Room;
//import androidx.room.RoomDatabase;
//
//import com.dcs.myretailer.app.domain.mastertables.mastertables.POSSYS.PosConfigurationDao;
//import com.dcs.myretailer.app.domain.mastertables.mastertables.POSSYS.PosConfigurationModel;
//import com.dcs.myretailer.app.domain.mastertables.mastertables.plukitchen.PLUKPrinterDao;
//import com.dcs.myretailer.app.domain.mastertables.product.ProductDao;
//import com.dcs.myretailer.app.domain.mastertables.product.ProductModel;
//
//@Database(
//        entities = {OpenDiscountModel.class,
//                PosConfigurationModel.class, ProductModel.class, PLUKPrinterModel.class},
//        version = 1,
//        exportSchema = false
//)public abstract class OpenDiscountDatabase extends RoomDatabase {
//
//    private static final String DB_NAME = "opendiscount_roomdb";
//    protected static OpenDiscountDatabase instance;
//
//    public static synchronized OpenDiscountDatabase getInstance(Context context) {
//        if (instance == null) {
//            instance = Room.databaseBuilder(
//                    context.getApplicationContext(),
//                    OpenDiscountDatabase.class,
//                    DB_NAME
//                )
//                    .fallbackToDestructiveMigration()
//                    .build();
//        }
//        return instance;
//    }
//
//    public abstract OpenDiscountDao openDiscountDao();
//    public abstract ProductDao productDao();
//    public abstract PosConfigurationDao posConfigurationDao();
//    public abstract PLUKPrinterDao plukPrinterDao();
//}
