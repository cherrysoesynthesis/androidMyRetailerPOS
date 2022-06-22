package com.dcs.myretailer.app.Setting;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DBConfig  extends SQLiteOpenHelper {
    public DBConfig(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
        super(context, "myretailerapi.db", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE SUBMIT_SALES( ID INTEGER PRIMARY KEY AUTOINCREMENT, SubmitSaleObject TEXT );");
        sqLiteDatabase.execSQL("CREATE TABLE Cx( ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " CxName TEXT ," +
                " PrinterReceipt TEXT ," +
                " CashDrawer TEXT ," +
                " CustomerDisplay TEXT ," +
                " BarcodeScanner TEXT )" +
                " ;");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void deleteCx() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM Cx"); //delete all rows in a table
        db.close();
    }

    public void saveCx(String CxName,String PrinterReceipt,String CashDrawer,String CustomerDisplay,String BarcodeScanner){
        ContentValues contentValues = new ContentValues();
        contentValues.put("CxName", CxName);
        contentValues.put("PrinterReceipt", PrinterReceipt);
        contentValues.put("CashDrawer", CashDrawer);
        contentValues.put("CustomerDisplay", CustomerDisplay);
        contentValues.put("BarcodeScanner", BarcodeScanner);
        this.getWritableDatabase().insertOrThrow("Cx","",contentValues);
    }

    public List<String> getCx(){
        Cursor config_values = null;
        config_values = this.getReadableDatabase().rawQuery("SELECT CxName,PrinterReceipt,CashDrawer,CustomerDisplay,BarcodeScanner FROM Cx", null);
        String CxName = "0";
        String PrinterReceipt = "0";
        String CashDrawer = "0";
        String CustomerDisplay = "0";
        String BarcodeScanner = "0";
        List<String> list = new ArrayList<String>();
        while (config_values.moveToNext()) {
            CxName = config_values.getString(0);
            PrinterReceipt = config_values.getString(1);
            CashDrawer = config_values.getString(2);
            CustomerDisplay = config_values.getString(3);
            BarcodeScanner = config_values.getString(4);
            list.add(CxName+"__1__"+PrinterReceipt+"__2__"+CashDrawer+"__3__"+CustomerDisplay+"__4__"+BarcodeScanner);
        }
        return list;
    }


    public void deleteVoucher() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM SUBMIT_SALES"); //delete all rows in a table
        db.close();
    }

    public void saveSubmitSales(JSONObject submitSaleObject){
        ContentValues contentValues = new ContentValues();
        contentValues.put("SubmitSaleObject", String.valueOf(submitSaleObject));
        this.getWritableDatabase().insertOrThrow("SUBMIT_SALES","",contentValues);
    }

    public List<String> getSubmitSales(){
        Cursor config_values = null;
        config_values = this.getReadableDatabase().rawQuery("SELECT SubmitSaleObject FROM SUBMIT_SALES", null);
        String SubmitSaleObject = "0";
        List<String> list = new ArrayList<String>();
        while (config_values.moveToNext()) {
            SubmitSaleObject = config_values.getString(0);
            list.add(SubmitSaleObject);
        }
        return list;
    }
}
