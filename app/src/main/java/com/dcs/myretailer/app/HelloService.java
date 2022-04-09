//package com.dcs.myretailer.app;
//
//import android.app.Service;
//import android.content.Intent;
//import android.database.Cursor;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.drawable.BitmapDrawable;
//import android.os.IBinder;
//import android.util.Log;
//
//import com.dcs.myretailer.app.Cashier.RecyclerViewAdapter;
//import com.dcs.myretailer.app.Database.DBFunc;
//import com.dcs.myretailer.app.Query.Query;
//import com.dcs.myretailer.app.Setting.AddNewProductActivity;
//
//import java.util.ArrayList;
//import java.util.Date;
//
//public class HelloService extends Service {
//
//    private static final String TAG = "HelloService";
//    public static boolean isRunning  = false;
//    String ItemName = "0";
//    public static String name = "";
//    String ItemQty = "0";
//    String ItemPrice = "0";
//    String IttemID = "0";
//    String ParentItemID_ADDON = "0";
//    String LastUpdate = "0";
//    String TransNo = "0";
//    String CreateTime = "0";
//    String CashierID = "0";
//    String vchQueueNo = "0";
//    String intTableNo = "0";
//    public static String status = "1";
//    public static String St = "0";
//
//    @Override
//    public IBinder onBind(Intent arg0) {
//        return null;
//    }
//
//
//    @Override
//    public void onCreate() {
//        isRunning = true;
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                /*Here I want to do my task forever (reading from database & generate notifications)*/
//                Hold();
//            }
//        }).start();
//    }
//
//    ArrayList<Integer> MID = new ArrayList<Integer>();
//    private void Hold() {
//        Double total_price = 0.0;
//        Cursor config_values_pro_item = DBFunc.Query("SELECT ItemName,ItemID,ItemPrice,TransNo,ItemQty,ParentItemID_ADDON" +
//                " FROM OnlineBill_HOLD_ITEMS", false);
//
//        if (config_values_pro_item != null) {
//            while (config_values_pro_item.moveToNext()) {
//                ItemName = config_values_pro_item.getString(0);
//                IttemID = config_values_pro_item.getString(1);
//                ItemPrice = config_values_pro_item.getString(2);
//                TransNo = config_values_pro_item.getString(3);
//                ItemQty = config_values_pro_item.getString(4);
//                ParentItemID_ADDON = config_values_pro_item.getString(5);
//
//                Integer ID = 0;
//                String ModifiersID = "0";
//                if (ParentItemID_ADDON.length() > 0){
//                    Cursor c = DBFunc.Query("SELECT ID " +
//                            " FROM PLU where UUID = '"+IttemID+"'", true);
//
//                    if (c != null) {
//                        if (c.moveToNext()) {
//                            ID = c.getInt(0);
//                        }
//                        c.close();
//                    }
//
//                    c = DBFunc.Query("SELECT Modifiers " +
//                            " FROM BillPLU where PLUID = '"+ID+"'", false);
//
//                    if (c != null) {
//                        if (c.moveToNext()) {
//                            ModifiersID = c.getString(0);
//                        }
//                        c.close();
//                    }
//                    Integer modiID = 0;
//                    c = DBFunc.Query("SELECT ID FROM Product_Modifier Where Name = '"+ItemName+"'",true);
//                    if (c != null) {
//                        if (c.moveToNext()) {
//                            if (!c.isNull(0)) {
//                                modiID = c.getInt(0);
//                            }
//                        }
//                        c.close();
//                    }
//                    String str_modiID = ModifiersID + ":" +modiID;
//                    String sql = "SELECT BillNo FROM Bill WHERE TransNo = '" + TransNo + "'";
//
//                    Integer Bill__ID = 0;
//                    c = DBFunc.Query(sql, false);
//                    if (c != null) {
//                        if (c.moveToNext()) {
//                            Bill__ID = c.getInt(0);
//                        }
//                        c.close();
//                    }
//                    sql = "UPDATE BillPLU SET Modifiers = '" + str_modiID + "' WHERE PLUID = " + ID + " and BillNo = "+Bill__ID;
//
//                    DBFunc.ExecQuery(sql, false);
//                }
//                Cursor c = DBFunc.Query("SELECT vchQueueNo,CreateTime,LastUpdate,CashierID,intTableNo " +
//                        " FROM OnlineBill_HOLD where TransNo = '"+TransNo+"'", false);
//
//                if (c != null) {
//                    if (c.moveToNext()) {
//                        vchQueueNo = c.getString(0);
//                        CreateTime = c.getString(1);
//                        LastUpdate = c.getString(2);
//                        CashierID = c.getString(3);
//                        intTableNo = c.getString(4);
//                    }
//                    c.close();
//                }else {
//                    vchQueueNo = "0";
//                    CreateTime = "0";
//                    LastUpdate = "0";
//                    CashierID = "0";
//                    intTableNo = "0";
//                }
//                if (ItemQty.equals("0")) {
//                    AddNewProductActivity.SaveModifier(ItemName,ItemPrice);
//
////                    Integer modiID = 0;
////                    c = DBFunc.Query("SELECT ID FROM ProductModifier Where Name = '"+TransNo+"'",true);
////                    if (c != null) {
////                        if (c.moveToNext()) {
////                            if (!c.isNull(0)) {
////                                modiID = c.getInt(0);
////                            }
////                        }
////                        c.close();
////
////                        Integer BID = 0;
////                        c = DBFunc.Query("SELECT BillNo FROM Bill Where TransNo = '" + TransNo + "'", false);
////                        if (c != null) {
////                            if (c.moveToNext()) {
////                                if (!c.isNull(0)) {
////                                    BID = c.getInt(0);
////                                }
////                            }
////                            c.close();
////                        } else {
////                            BID = 0;
////                        }
////                        if (BID > 0) {
////                            String sql = "UPDATE BillPLU SET Modifiers = '" + modiID + "' WHERE BillNo = " + BID;
////                            Log.i("__sql", sql);
////                            DBFunc.ExecQuery(sql, false);
////                        }
////                    }
//////                    c = DBFunc.Query("SELECT BillNo FROM BillPLU Where TransNo = '"+TransNo+"'",false);
//////                    if (c != null) {
//////                        if (c.moveToNext()) {
//////                            if (!c.isNull(0)) {
//////                                BID = c.getInt(0);
//////                            }
//////                        }
//////                        c.close();
//////                    }else {
//////                        BID = 0;
//////                    }
//////                    c = DBFunc.Query("SELECT BillNo FROM BillPLU Where BillNo = '"+BID+"'",false);
//////                    if (c != null) {
//////                        if (c.moveToNext()) {
//////                            if (!c.isNull(0)) {
//////                                BID = c.getInt(0);
//////                                String sql = "UPDATE BillPLU SET Modifiers = '"+ qty +"' WHERE BillNo = '"+ MainActivity.strbillNo + "' and " +
//////                                        " ProductName = '"+ txt_header_.getText().toString() +"' ";
//////                                Log.i("__sql",sql);
//////                                DBFunc.ExecQuery(sql, false);
//////                            }
//////                        }
//////                        c.close();
//////                    }else {
//////                        BID = 0;
//////                    }
//////
//
//
//                }else {
//                    String sql = "SELECT BillNo FROM Bill WHERE TransNo = '" + TransNo + "'";
//
//                    c = DBFunc.Query(sql, false);
//                    if (c != null) {
//                        if (c.getCount() == 0) {
//
//                            int latestID = 0;
//                            c = DBFunc.Query("SELECT MAX(BillNo) FROM Bill", false);
//                            if (c != null) {
//                                if (c.moveToNext()) {
//                                    if (!c.isNull(0)) {
//                                        latestID = c.getInt(0);
//                                    }
//                                }
//                                c.close();
//                            }
////                            DBFunc.ExecQuery("INSERT INTO Bill (OpenDateTime, Cashier, CashierID, BalNo, OnlineOrderBill,TransNo) VALUES " +
////                                    "(" + System.currentTimeMillis() + ",'" + CashierID + "', " +
////                                    Allocator.cashierID + ", '" + latestID + 1 + "','ON','" + TransNo + "')", false);
//
//                            Query.CreateNewBill("ON",TransNo);
//                        }
//                        c.close();
//                    }
////                    BitmapDrawable drawable = null;
////
////                    Bitmap icon = BitmapFactory.decodeResource(this.getResources(),
////                            R.drawable.black_photo);
////                    drawable = new BitmapDrawable(this.getResources(), icon);
////                    Bitmap bitmap = drawable.getBitmap();
//                    Bitmap bitmap = null;
//                    sql = "SELECT ID FROM PLU WHERE UUID = '" + IttemID + "'";
//
//                    c = DBFunc.Query(sql, true);
//                    if (c != null) {
//                        if (c.getCount() == 0) {
//                            sql = "INSERT INTO PLU (Name,UUID,Name2,DeptID,Price,Option,Code,Image,ProductVariant,ProductModifiers," +
//                                    "AllowNameQuickEdit,AllowPriceQuickEdit,ProductCategoryID,ProductCategoryName,DateTime,Condi_Seq) " +
//                                    "VALUES ('" + DBFunc.PurifyString(ItemName) + "'," +
//                                    "'" + IttemID + "'," +
//                                    "''," +
//                                    "" + 0 + "," +
//                                    ItemPrice + ",'" +
//                                    00000 + "','" + DBFunc.PurifyString(vchQueueNo) + "'," +
//                                    "'" + DBFunc.PurifyString(String.valueOf(AddNewProductActivity.BitMapToString(bitmap))) + "'," +
//                                    "'" + DBFunc.PurifyString("0") + "'," +
//                                    "'" + DBFunc.PurifyString("0") + "'," +
//                                    "" + Integer.parseInt("0") + "," +
//                                    "" + Integer.parseInt("0") + "," +
//                                    "'0'," +
//                                    "'0'," +
//                                    "" + System.currentTimeMillis() + "," +
//                                    000 + ")";
//                            DBFunc.ExecQuery(sql, true);
//                        }
//                        c.close();
//                        sql = "SELECT ID FROM PLU WHERE UUID = '" + IttemID + "'";
//
//                        c = DBFunc.Query(sql, true);
//                        if (c != null) {
//                            if (c.moveToNext()){
//                                total_price += Double.valueOf(ItemPrice);
//                                RecyclerViewAdapter.totalItems = 1;
//                                RecyclerViewAdapter.countSelectedArr = 1;
//                                RecyclerViewAdapter.sldNameArr.add(ItemName);
//                                RecyclerViewAdapter.sltPriceTotalArr.add(ItemPrice);
//                                RecyclerViewAdapter.sldQtyArr.add(Integer.parseInt(ItemQty));
//                                RecyclerViewAdapter.sltPriceTotalArr.add(String.valueOf(total_price));
//                                RecyclerViewAdapter.sltBillDisArr.add("0");
//                                RecyclerViewAdapter.sldIDArr.add(c.getString(0));
//                                RecyclerViewAdapter.sltCategoryIDArr.add("0");
//                                RecyclerViewAdapter.sltCategoryNameArr.add("0");
//                                RecyclerViewAdapter.vchQueueNo.clear();
//                                RecyclerViewAdapter.intTableNo.clear();
//
//                                RecyclerViewAdapter.vchQueueNo.add(vchQueueNo);
//
//                                RecyclerViewAdapter.intTableNo.add(intTableNo);
//                            }
//                        }
//                    }
//
//                    if (RecyclerViewAdapter.sldIDArr.size() > 0) {
//                        Integer totalItems = RecyclerViewAdapter.totalItems;
//                        ArrayList<String> sldIDArr = RecyclerViewAdapter.sldIDArr;
//                        ArrayList<Integer> sldQtyArr = RecyclerViewAdapter.sldQtyArr;
//                        ArrayList<String> sldNameArr = RecyclerViewAdapter.sldNameArr;
//                        ArrayList<String> sltPriceTotalArr = RecyclerViewAdapter.sltPriceTotalArr;
//                        ArrayList<String> sltBillDisArr = RecyclerViewAdapter.sltBillDisArr;
//                        ArrayList<String> sltCategoryIDArr = RecyclerViewAdapter.sltCategoryIDArr;
//                        ArrayList<String> sltCategoryNameArr = RecyclerViewAdapter.sltCategoryNameArr;
//                        ArrayList<String> vchQueueNo = RecyclerViewAdapter.vchQueueNo;
//                        ArrayList<String> intTableNo = RecyclerViewAdapter.intTableNo;
//                        Integer countSelectedArr = RecyclerViewAdapter.countSelectedArr;
//                        String BillNo = TransNo;
//                        Integer qty = 1;
//                        String dateFormat3 = CheckOutActivity.dateFormat55.format(new Date()).toString();
//                        String paymenttype = "Cash";
//                        String status = "SALES";
//                        String Itemstatus = "SALES";
//                        Double sub_total = 0.0;
//                        Double amount = 0.0;
//                        sql = "SELECT ID FROM Product_Modifier WHERE Name = '" + ItemName + "'";
//
//                        c = DBFunc.Query(sql, true);
//                        if (c != null) {
//                            MID.clear();
//                            while (c.moveToNext()) {
//                                MID.add(c.getInt(0));
//                            }
//                        }
//                        //ArrayList<Integer> Modifier_ID =  ButtonAdapter.ID;
//
//                        Integer billIDDD = Query.getBillID(BillNo);
////                        CheckOutActivity.SaveBill(billIDDD,BillNo, sldNameArr, sldQtyArr, sltPriceTotalArr, sltBillDisArr, sltCategoryNameArr,
////                                qty, totalItems, sldIDArr, sltCategoryIDArr, countSelectedArr, dateFormat3, status,
////                                sub_total, amount, paymenttype,MID,vchQueueNo,intTableNo,Itemstatus);
//                    }
//                }
//
//            }
//            config_values_pro_item.close();
////            String sql = "SELECT idx FROM BillPLU WHERE Name = '" + ItemName + "'";
////            Log.i("sql__ddd", sql);
////            Cursor c = DBFunc.Query(sql, false);
////            if (c != null) {
////                if (c.getCount() == 0) {
////                    if (RecyclerViewAdapter.sldIDArr.size() > 0) {
////                        Integer totalItems = RecyclerViewAdapter.totalItems;
////                        ArrayList<String> sldIDArr = RecyclerViewAdapter.sldIDArr;
////                        ArrayList<Integer> sldQtyArr = RecyclerViewAdapter.sldQtyArr;
////                        ArrayList<String> sldNameArr = RecyclerViewAdapter.sldNameArr;
////                        ArrayList<String> sltPriceTotalArr = RecyclerViewAdapter.sltPriceTotalArr;
////                        ArrayList<String> sltBillDisArr = RecyclerViewAdapter.sltBillDisArr;
////                        ArrayList<String> sltCategoryIDArr = RecyclerViewAdapter.sltCategoryIDArr;
////                        ArrayList<String> sltCategoryNameArr = RecyclerViewAdapter.sltCategoryNameArr;
////                        Integer countSelectedArr = RecyclerViewAdapter.countSelectedArr;
////                        String BillNo = TransNo;
////                        Integer qty = 1;
////                        String dateFormat3 = CheckOutActivity.dateFormat55.format(new Date()).toString();
////                        String paymenttype = "Cash";
////                        String status = "SALES";
////                        Double sub_total = 0.0;
////                        Double amount = 0.0;
////                        sql = "SELECT ID FROM ProductModifier WHERE Name = '" + ItemName + "'";
////                        Log.i("sql__ddd", sql);
////                        c = DBFunc.Query(sql, true);
////                        if (c != null) {
////                            MID.clear();
////                            while (c.moveToNext()) {
////                                MID.add(c.getInt(0));
////                            }
////                        }
////                        //ArrayList<Integer> Modifier_ID =  ButtonAdapter.ID;
////                        CheckOutActivity.SaveBill(BillNo, sldNameArr, sldQtyArr, sltPriceTotalArr, sltBillDisArr, sltCategoryNameArr,
////                                qty, totalItems, sldIDArr, sltCategoryIDArr, countSelectedArr, dateFormat3, status,
////                                sub_total, amount, paymenttype,MID);
////                    }
////                }
////            }
//
//        }
//    }
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        return Service.START_STICKY;
//    }
//
//    @Override
//    public void onDestroy() {
//        isRunning = false;
//    }
//
//}