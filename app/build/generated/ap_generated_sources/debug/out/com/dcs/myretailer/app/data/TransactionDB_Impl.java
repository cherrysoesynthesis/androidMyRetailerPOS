package com.dcs.myretailer.app.data;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class TransactionDB_Impl extends TransactionDB {
  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `SalesModel` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `bill_id` INTEGER, `bill_no` TEXT, `uuid` TEXT, `total_qty` INTEGER, `total_amount` REAL, `change_amount` REAL, `payment_type_id` INTEGER, `payment_type_name` TEXT, `payment_type_amount` REAL, `total_items_discount` REAL, `total_bill_discount` REAL, `service_charges` REAL, `total_nett_sales` REAL, `total_taxes` REAL, `cashier_name` TEXT, `sales_datetime` TEXT, `bill_hour` TEXT, `status` TEXT, `discount_id` INTEGER, `discount_name` TEXT, `discount_type` TEXT, `discount_type_name` TEXT, `discount_value` REAL, `is_z` TEXT, `round_adj` REAL, `sales_delivery_id` INTEGER, `collected_or_delivery` TEXT, `reference_bill_no` TEXT, `reference_sales_id` TEXT, `ewallet_status` TEXT, `dt` INTEGER)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'c337ef90b50aedd3fd14af6656d0a483')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `SalesModel`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsSalesModel = new HashMap<String, TableInfo.Column>(32);
        _columnsSalesModel.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSalesModel.put("bill_id", new TableInfo.Column("bill_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSalesModel.put("bill_no", new TableInfo.Column("bill_no", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSalesModel.put("uuid", new TableInfo.Column("uuid", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSalesModel.put("total_qty", new TableInfo.Column("total_qty", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSalesModel.put("total_amount", new TableInfo.Column("total_amount", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSalesModel.put("change_amount", new TableInfo.Column("change_amount", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSalesModel.put("payment_type_id", new TableInfo.Column("payment_type_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSalesModel.put("payment_type_name", new TableInfo.Column("payment_type_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSalesModel.put("payment_type_amount", new TableInfo.Column("payment_type_amount", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSalesModel.put("total_items_discount", new TableInfo.Column("total_items_discount", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSalesModel.put("total_bill_discount", new TableInfo.Column("total_bill_discount", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSalesModel.put("service_charges", new TableInfo.Column("service_charges", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSalesModel.put("total_nett_sales", new TableInfo.Column("total_nett_sales", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSalesModel.put("total_taxes", new TableInfo.Column("total_taxes", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSalesModel.put("cashier_name", new TableInfo.Column("cashier_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSalesModel.put("sales_datetime", new TableInfo.Column("sales_datetime", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSalesModel.put("bill_hour", new TableInfo.Column("bill_hour", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSalesModel.put("status", new TableInfo.Column("status", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSalesModel.put("discount_id", new TableInfo.Column("discount_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSalesModel.put("discount_name", new TableInfo.Column("discount_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSalesModel.put("discount_type", new TableInfo.Column("discount_type", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSalesModel.put("discount_type_name", new TableInfo.Column("discount_type_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSalesModel.put("discount_value", new TableInfo.Column("discount_value", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSalesModel.put("is_z", new TableInfo.Column("is_z", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSalesModel.put("round_adj", new TableInfo.Column("round_adj", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSalesModel.put("sales_delivery_id", new TableInfo.Column("sales_delivery_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSalesModel.put("collected_or_delivery", new TableInfo.Column("collected_or_delivery", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSalesModel.put("reference_bill_no", new TableInfo.Column("reference_bill_no", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSalesModel.put("reference_sales_id", new TableInfo.Column("reference_sales_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSalesModel.put("ewallet_status", new TableInfo.Column("ewallet_status", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSalesModel.put("dt", new TableInfo.Column("dt", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSalesModel = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSalesModel = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSalesModel = new TableInfo("SalesModel", _columnsSalesModel, _foreignKeysSalesModel, _indicesSalesModel);
        final TableInfo _existingSalesModel = TableInfo.read(_db, "SalesModel");
        if (! _infoSalesModel.equals(_existingSalesModel)) {
          return new RoomOpenHelper.ValidationResult(false, "SalesModel(com.dcs.myretailer.app.domain.transaction.sales.SalesModel).\n"
                  + " Expected:\n" + _infoSalesModel + "\n"
                  + " Found:\n" + _existingSalesModel);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "c337ef90b50aedd3fd14af6656d0a483", "cded940739bf71d0691f09645671e721");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "SalesModel");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `SalesModel`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }
}
