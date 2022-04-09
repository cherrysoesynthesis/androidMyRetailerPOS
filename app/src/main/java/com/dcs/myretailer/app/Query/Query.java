package com.dcs.myretailer.app.Query;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dcs.myretailer.app.APIActivity;
import com.dcs.myretailer.app.Allocator;
import com.dcs.myretailer.app.BOC.BillBOC;
import com.dcs.myretailer.app.BillList.BillListModel;
import com.dcs.myretailer.app.CashLayoutActivity;
import com.dcs.myretailer.app.Cashier.BillDetails;
import com.dcs.myretailer.app.Cashier.DeclarationConf;
import com.dcs.myretailer.app.Cashier.EditProductSheetFragment;
import com.dcs.myretailer.app.Cashier.MainActivity;
import com.dcs.myretailer.app.Cashier.ProductMainPageFragment;
import com.dcs.myretailer.app.Cashier.RecyclerViewAdapter;
import com.dcs.myretailer.app.Cashier.SalesDelievery.SalesDelivery;
import com.dcs.myretailer.app.Cashier.SalesDelievery.SalesDeliveryItem;
import com.dcs.myretailer.app.Category.CategoryModel;
import com.dcs.myretailer.app.CheckOutActivity;
import com.dcs.myretailer.app.Checkout.PaymentCashSuccesActivity;
import com.dcs.myretailer.app.Checkout.PaymentTypesCheckoutAdapter;
import com.dcs.myretailer.app.DaggerIngenicoComponent;
import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.DetailsBillProduct;
import com.dcs.myretailer.app.DeviceHelper;
import com.dcs.myretailer.app.DialogBox;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.IngenicoModule;
import com.dcs.myretailer.app.Jeripay.BillJeripay;
import com.dcs.myretailer.app.Jeripay.BillJeripayDetails;
import com.dcs.myretailer.app.Mercatus.BillMercatus;
import com.dcs.myretailer.app.Mercatus.BillMercatusDetails;
import com.dcs.myretailer.app.Mercatus.BillMercatusMallLoyaltyDetails;
import com.dcs.myretailer.app.Mercatus.BillMercatusPayment;
import com.dcs.myretailer.app.Mercatus.BillMercatusVouchers;
import com.dcs.myretailer.app.Model.DeviceData;
import com.dcs.myretailer.app.Model.Discount;
import com.dcs.myretailer.app.ProductData;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.RemarkMainActivity;
import com.dcs.myretailer.app.Report.ReportActivity;
import com.dcs.myretailer.app.Setting.AddNewProductActivity;
import com.dcs.myretailer.app.Setting.ButtonStyle;
import com.dcs.myretailer.app.Setting.DiscountClass;
import com.dcs.myretailer.app.Setting.MapButton;
import com.dcs.myretailer.app.Setting.MapViewer;
import com.dcs.myretailer.app.Setting.StrTextConst;
import com.dcs.myretailer.app.Setting.SyncActivity;
import com.dcs.myretailer.app.Stock.StockAdjustment;
import com.dcs.myretailer.app.Stock.StockAgent;
import com.dcs.myretailer.app.Stock.StockIn;
import com.dcs.myretailer.app.Stock.StockOut;
import com.dcs.myretailer.app.Stock.StockTake;
import com.dcs.myretailer.app.Tbllicense;
import com.dcs.myretailer.app.TransactionDetailsActivity;
import com.dcs.myretailer.app.UserAccess;
import com.dcs.myretailer.app.ZClose.ZClose;
import com.imin.printerlib.IminPrintUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;

import cn.pedant.SweetAlert.SweetAlertDialog;

//import com.dcs.myretailer.app.Cashier.RecyclerViewNoImageAdapter;

public class Query {
    private static SimpleDateFormat sdf;
    public static String GetTokenStatusResult = "";
    public static String GetTokenResult = "";
    public static String FindShopperResult = "";
    public static String voucher_value = "";
    public static String voucher_no = "";
    public static String voucher_status = "";
    public static String voucher_record_id = "";
    public static String voucher_expiry_date = "";
    public static String anynomousvoucher = "";
    public static int qrval = 0;
//    public static double taxItemAmount = 0.0;
    public static String memberewalletsuccess = "";
    public static String anonymousmessage = "";
    public static String anynomousvoucheruccess = "";
    public static String anynomousvoucherermsg = "";
    public static String Shopper_id = "";
    public static String tokresult = "";
    public static String qrresult = "";
    public static int voucherLen = 0;
    public static String dir;
    public static double typevalue = 0.0;
    public static String taxType = "";
    public static double totalvoucherval = 0.0;
    public static ArrayList<Voucher> voucherArrList = new ArrayList<Voucher>();
    static final List<String> recArr = new ArrayList<String>();
    static final List<String> recVoycherArr = new ArrayList<String>();
    public static final List<String> ewalletrecArr = new ArrayList<String>();
    public static final List<String> ewalletVoucherValArr = new ArrayList<String>();
    public static RequestQueue queue_qrcode;
    public static final int MY_SOCKET_TIMEOUT_MS = 30000;
    public static Boolean chk_pos_qr_code_ = false;
    public static String qrcodestring = "";

    public static String stillpendingbillno = "";
    public static ArrayList<String> arrStillpendingbillno = new ArrayList<String>();
    public static ArrayList<String> arrStillpendingbilllist = new ArrayList<String>();

    //static String query = " WHERE STATUS = 'SALES' AND isZ IS NULL ";
     //static String query = " WHERE STATUS = 'SALES' ";
     static String query = " WHERE (STATUS = 'SALES'   OR STATUS = 'REFUND') ";
     static String query_details_bill = " WHERE (CANCEL = 'SALES'   OR CANCEL = 'REFUND') ";
     static String query_cancel = " WHERE (STATUS = 'CANCEL'   OR STATUS = 'VOID') ";
     static String groupdtformat = " group by strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch') ";

    public static String EditFragmentOpenPrice = "0";
    public static String EditFragmentRemarks = "0";
   // public static String EditFragmentID = "0";
    //DetailsBillProduct
    public static Cursor SearchBillProductByBillNo(String billNo,String GroupByStatus){
        String sql = "0";
        if (GroupByStatus.equals(Constraints.GroupBy)){
            sql = " SELECT (ProductQty),(ProductPrice),ProductQty,(ItemDiscountAmount) " +
                    "FROM DetailsBillProduct Where ProductQty != -1 AND BillNo = '" + billNo + "' ";
                    //"group by BillNo";
        }else {
            sql = " SELECT (ProductQty),(ProductPrice),ProductName,intTableNo,vchQueueNo,Cancel " +
                    "FROM DetailsBillProduct " +
                    "Where BillNo = '" + billNo + "' ";
//            sql = " SELECT ProductQty,ProductPrice,ProductName,intTableNo,vchQueueNo,Cancel " +
//                    "FROM DetailsBillProduct " +
//                    "Where BillNo = '" + billNo + "' ";
        }
//        String sql = "0";
//        if (GroupByStatus.equals(Constraints.GroupBy)){
//            sql = " SELECT SUM(ProductQty),SUM(ProductPrice),ProductQty,SUM(ItemDiscountAmount) " +
//                    "FROM DetailsBillProduct Where ProductQty != -1 AND BillNo = '" + billNo + "' "+
//                    "group by BillNo";
//        }else {
//            sql = " SELECT SUM(ProductQty),SUM(ProductPrice),ProductName,intTableNo,vchQueueNo,Cancel " +
//                    "FROM DetailsBillProduct " +
//                    "Where BillNo = '" + billNo + "' ";
////            sql = " SELECT ProductQty,ProductPrice,ProductName,intTableNo,vchQueueNo,Cancel " +
////                    "FROM DetailsBillProduct " +
////                    "Where BillNo = '" + billNo + "' ";
//        }
        Log.i("dsfsdfdsf___","Sdfsdf____"+sql);
        return DBFunc.Query(sql, false);
    }
//    //Possys
//    public static Cursor GetDataFromPossys(){
//        return  DBFunc.Query("SELECT retails_id,company_code,company_url,terminal_id " +
//                "FROM POSSys", true);
//    }
    //Bill
    public static Cursor GetDataFromBill(){
        return  DBFunc.Query("SELECT BillNo FROM Bill " +
                "where CloseDateTime IS NULL " +
                "order by BillNo DESC", false);
    }
    public static void SaveOnlineBillHold(String TransID, String CompanyID, String RetailerID, String TransNo, String TotalDue,
                                          String TotalGST, String TotalDisc, String TransDate, String CreateTime, String TotalQty,
                                          String CashierID, String MemberID, String isNewCust, String SalesPersonID, String CommID,
                                          String CommPerc, String ReceiptOrderStatus, String vchQueueNo, String intTableNo,
                                          String MacAddress, String TerminalID, String PendingSync, String LastUser, String LastUpdate,
                                          String LockUpdate, String perc, String LockStatus, String RecordStatus, String RecordUpdate,
                                          String QueueStatus){
        String saveHoldBillSQL = "INSERT INTO OnlineBill_HOLD (TransID, CompanyID, RetailerID, TransNo, TotalDue ," +
                "TotalGST,TotalDisc,TransDate,CreateTime,TotalQty ," +
                "CashierID,MemberID,isNewCust,SalesPersonID,CommID ," +
                "CommPerc,ReceiptOrderStatus,vchQueueNo,intTableNo,MacAddress ," +
                "TerminalID,PendingSync,LastUser,LastUpdate,LockUser ," +
                "LockUpdate,LockStatus,RecordStatus,RecordUpdate,QueueStatus ,DateTime) VALUES " +
                "( '"+TransID+"', '"+CompanyID+"', '"+RetailerID+"', '"+TransNo+"',"+
                "'"+TotalDue+"', '"+TotalGST+"', '"+TotalDisc+"', '"+TransDate+"',"+
                "'"+CreateTime+"', '"+TotalQty+"', '"+CashierID+"', '"+MemberID+"', '"+isNewCust+"',"+
                "'"+SalesPersonID+"', '"+CommID+"', '"+CommPerc+"', '"+ReceiptOrderStatus+"', '"+vchQueueNo+"',"+
                "'"+intTableNo+"',"+
                "'"+MacAddress+"',"+
                "'"+TerminalID+"',"+
                "'"+PendingSync+"',"+
                "'"+LastUser+"',"+
                "'"+LastUpdate+"',"+
                "'"+LockUpdate+"',"+
                "'"+CommPerc+"',"+
                "'"+LockStatus+"',"+
                "'"+RecordStatus+"',"+
                "'"+RecordUpdate+"',"+
                "'"+QueueStatus+"',"+
                "" + System.currentTimeMillis() + ")";

        DBFunc.ExecQuery(saveHoldBillSQL, false);
    }
    public static Integer getSalesIDByBillNo(String billNo){
        Integer sales_id = 0;
        String sql = "SELECT ID FROM SALES Where BillNo = '"+ billNo +"' ";

        Cursor c = DBFunc.Query(sql, false);
        if (c != null) {
            sales_id = 0;
            if (c.moveToNext()) {
                if (!c.isNull(0)) {

                    sales_id = c.getInt(0);
                }
            }
            c.close();
        }
        return  sales_id;
    }
    public static String getSalesStatusBySaleID(Integer ID){
        String Status = "0";
        String sql = "SELECT STATUS FROM SALES Where ID = "+ ID +" ";

        Cursor c = DBFunc.Query(sql, false);
        if (c != null) {
            Status = "0";
            if (c.moveToNext()) {
                if (!c.isNull(0)) {

                    Status = c.getString(0);
                }
            }
            c.close();
        }
        return  Status;
    }
//    public static void SavePLUQuick(String Name,String Name2,String Price,String PCode,Bitmap Pbitmap,String editQuickName,
//                                  String editQuickPrice,String catId,String catName,Context context){
    public static void SavePLUQuick(String Name,String Name2,String Price,String PCode,String str_img,
                                    String str_imgItemId, String str_imgUrl, String str_imgType,String str_imgfileName,
                                    String base64imgValue,
                                    String editQuickName,
                                  String editQuickPrice,String catId,String catName,Context context){
//        String img = "0";
//        if (Pbitmap == null){
//            img = "0";
//        }else {
//            Uri getUrlImage = GetUrlForImageBitMap(context, Pbitmap);
//            //img = String.valueOf(BitMapToString(Pbitmap));
//            img = String.valueOf(getUrlImage);
//        }

        String uniqueId  = UUID.randomUUID().toString();

        Query.SavePLU(Name,uniqueId,Price,PCode,str_img,
                str_imgItemId,str_imgUrl,str_imgType,str_imgfileName,base64imgValue,editQuickName,
                editQuickPrice,"0","0",
                Integer.parseInt(catId),catName,0,"0","0","Other");
    }

    //public static void saveCategory(String name, String str_img,Context context){
    public static void saveCategory(CategoryModel ctmodel){
        String sql = "INSERT INTO Category (UUID,Name,Code,Image,DateTime) " +
                "VALUES ('" + DBFunc.PurifyString(ctmodel.getUUID()) + "'," +
                "'" + DBFunc.PurifyString(ctmodel.getName()) + "'," +
                "'" + DBFunc.PurifyString(ctmodel.getCode()) + "'," +
                "'" + DBFunc.PurifyString(ctmodel.getImage()) + "'," +
                System.currentTimeMillis() + ")";

//        CREATE TABLE "Category" (
//            `ID`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
//            `UUID`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
//            `Name`	TEXT,
//            `Code`	TEXT,
//            `Image`	TEXT,
//            `DateTime`	INTEGER NOT NULL
//)

        DBFunc.ExecQuery(sql, true);
    }
    //content://media/external/images/media/74
    ///storage/emulated/0/Pictures Title image/jpeg

    //content://media/external/images/media/16
    //content://media/external/images/media/59
    public static Uri GetUrlForImageBitMap(Context mContext, Bitmap bitmap) {
        Date currentTime;
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        //inImage.compress(Bitmap.CompressFormat.PNG, 100, bytes);
        //inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(mContext.getContentResolver(), bitmap, "Title", null);
        String path1 = MediaStore.Images.Media.insertImage(mContext.getContentResolver(), bitmap, "ThisIsImageTitleString" + " - " + (currentTime = Calendar.getInstance().getTime()), null);

        //return Uri.parse(path);
        return Uri.parse(path1);
    }
    public static Cursor getStaff(String name,String pin,String status){
        String sql = "0";
        if (status.equals("ALL")) {
            sql = "SELECT name,pin,commission,permission_group_id," +
                    "permission_group_name,DateTime " +
                    "FROM Staff";

        }else {
            sql = "SELECT name,pin,commission,permission_group_id," +
                    "permission_group_name,DateTime " +
                    "FROM Staff where name = '"+ name +"' AND " +
                    "pin = '"+pin+ "'";

        }


        Cursor c = DBFunc.Query(sql,true);
        return c;
    }
    public static void saveStaff(String name,String pin,String commission,String perId,String perName){
        String sql = "INSERT INTO Staff (name,pin,commission,permission_group_id," +
                "permission_group_name,DateTime) " +
                "VALUES ('" + DBFunc.PurifyString(name) + "'," +
                "'" + DBFunc.PurifyString(String.valueOf(pin)) + "','" +
                DBFunc.PurifyString(commission) + "','" +
                DBFunc.PurifyString(perId) + "','" +
                DBFunc.PurifyString(perName) + "'," +
                "" + System.currentTimeMillis() + ")";

        DBFunc.ExecQuery(sql, true);
        //return DBFunc.Query(sql, true);

    }
    public static void SavePermissionGroup(String name) {

        String sql = " SELECT name " +
                "FROM PermissionGroup Where name = '" + name + "' ";

        Cursor c = DBFunc.Query(sql,true);
        if (c != null){
           if (c.getCount() == 0){
               sql = "INSERT INTO PermissionGroup (name,DateTime) " +
                        "VALUES ('" + DBFunc.PurifyString(name) + "'," +
                        "" + System.currentTimeMillis() + ")";

                DBFunc.ExecQuery(sql, true);
                //DBFunc.ExecQuery(sql, true);
            }
            c.close();
        }
    }
    public static Cursor findCategory(String name) {
        String sql = " SELECT ProductCategoryID,ProductCategoryName " +
                "FROM PLU Where Name = '" + name + "' ";

        return DBFunc.Query(sql,true);

    }
    public static void ErrorDialog (Context c,String msg){
        new AlertDialog.Builder(c, R.style.AlertDialogStyle)
                .setMessage(msg)
                .setCancelable(false)
                .setNegativeButton(Constraints.OK, null)
                .show();
        return;
    }

    public static String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        byte[] bitmapdata = bos.toByteArray();
//        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG,50, baos);
//        byte [] b=baos.toByteArray();
        String temp= Base64.encodeToString(bitmapdata, Base64.DEFAULT);
        return temp;
    }
    public static String GetOptions(Integer num){
        String option = "";
        String chkk = "0";
        try {

            Cursor config_values_pro_item = DBFunc.Query("SELECT option FROM POSSys", true);
            if (config_values_pro_item != null) {
                while (config_values_pro_item.moveToNext()) {
                    option = config_values_pro_item.getString(0);
                }

                if (option.length() > 0) {
                    if (option.charAt(num) == '1') {
                        chkk = "1";
                    }else {
                        chkk = "0";
                    }
                }else {
                    chkk = "0";
                }
            }
        }catch (ArrayIndexOutOfBoundsException e){
            chkk = "0";
        } catch (Exception e){
            chkk = "0";
        }
        return chkk;
    }

//    public static void RefundBill(String BillNo,Integer totalQty, Double subTotal, Double totalamount, Double paymentAmount, Double grossSales,
//                                  Double totalItemDisount, Double totalBillDisount, Double grossTotal, Double serviceCharges,
//                                  Double totalNettSales, Double totalTaxes, String salesDateTime, String billHour, Integer sales_id,
//                                  Double TotalDiscountValue) {

        public static void RefundBill(Integer sales_id,String status,String BillNo) {

            String dateFormat3 = CheckOutActivity.dateFormat55.format(new Date()).toString();
            String dateFormat4 = CheckOutActivity.dateFormat555.format(new Date()).toString();

            String old_bId = findBillIDByBillNo(BillNo);

            RefundSaveBill(old_bId);

            Integer lstbillID = Query.findLatestID("BillNo","Bill",false);
            String refubdBillNo = billNo(lstbillID);

            String bId = findBillIDByBillNo(refubdBillNo);

            RefundBillList(BillNo,refubdBillNo,System.currentTimeMillis());

            RefundDetailsBillProduct(BillNo,refubdBillNo,dateFormat3,bId,System.currentTimeMillis());

//            RefundPLUModi(BillNo,refubdBillNo,dateFormat3,bId,System.currentTimeMillis());

            RefundBillPayment(BillNo,refubdBillNo);

            RefundSalesAndItems(BillNo,refubdBillNo,sales_id,dateFormat3,dateFormat4,bId,System.currentTimeMillis(),"Current");
    }

    private static void RefundPLUModi(String billNo, String refubdBillNo, String dateFormat3, String bId, String oldbilldetailsPID,
                                      Integer latestbilldetailsPID,long currentTimeMillis) {
        String sql = "SELECT PLU_UUID,ItemID,ItemName,ModiName,ModiPrice,Qty,OpenPriceStatus," +
                "Remarks,BillID FROM PLUModi WHERE BillNo = '"+billNo+"' AND  DetailsBillProductID = '"+oldbilldetailsPID+"' ";
        Log.i("Sdfdsfdsf___","sql___"+sql);
        Cursor c = DBFunc.Query(sql, false);
        if (c != null) {
            while (c.moveToNext()) {

                String uuid = c.getString(0);
                Integer modiID = c.getInt(1);
                String name = c.getString(2);
                String modiName = c.getString(3);
                String modiPrice = c.getString(4);
                Integer qty = c.getInt(5);
                Integer openPrice = c.getInt(6);
                String remarks = c.getString(7);
                Integer billID = c.getInt(8);

                Query.SavePLUModi(uuid,modiID,name,modiName,modiPrice,qty,String.valueOf(openPrice),
                        remarks,refubdBillNo,String.valueOf(billID),String.valueOf(latestbilldetailsPID));

            }
            c.close();
        }
    }

    private static void RefundSaveBill(String old_bId) {
        String sql = "SELECT OpenDateTime, Cashier, CashierID, BalNo, " +
                "VchQueueNo,IntTableNo,ReceiptOrderStatus,TransID, " +
                "OnlineOrderBill " +
                "FROM Bill " +
                "where BillNo = " + old_bId + "";
//                "where BillNo = '" + old_bId + "'";

        Cursor c = DBFunc.Query(sql, false);
        if (c != null) {
            if (c.moveToNext()) {
                long opendt = c.getLong(0);
                String casherName = c.getString(1);
                String casherID = c.getString(2);
                String BalNo = c.getString(3);
                String VchQueueNo = c.getString(4);
                String intTableNo = c.getString(5);
                String receiptOrderStatus = c.getString(6);
                String transID = c.getString(7);
                String offonStatus = c.getString(8);

                sql = "INSERT INTO Bill (OpenDateTime, Cashier, CashierID, BalNo, " +
                        "VchQueueNo,IntTableNo,ReceiptOrderStatus,TransID, " +
                        "OnlineOrderBill) VALUES " +
                        "(" + opendt + ",'" + casherName + "', " +
                        casherID + ", '"+BalNo+"' , " +
                        "'"+ VchQueueNo +"', '"+intTableNo+"','"+receiptOrderStatus+"','"+transID+"',"+
                        "'"+offonStatus+"' )";

                DBFunc.ExecQuery(sql, false);


                updateBillNo(offonStatus);
            }
        }
    }

//    private static void RefundSalesAndItems(String BillNo, String refubdBillNo,Integer sales_id,String dateFormat3,String dateFormat4,
//                                            String bId,long dt,String str_Amend) {
//        Integer TotalQty = 0;
//        Integer ID = 0;
//        Integer PaymentTypeID = 0;
////            String BillNo = "";
//        String CashierName = "";
//        String SalesDateTime = "";
//        String BillHour = "";
//        String STATUS = "";
//        String DiscountName = "";
//        String DiscountType = "";
//        String DiscountTypeName = "";
//        String PaymentTypeName = "";
//        Double SubTotal = 0.0;
//        Double Totalamount = 0.0;
//        Double Changeamount = 0.0;
//        Double PaymentAmount = 0.0;
//        Double GrossSales = 0.0;
//        Double TotalItemDisount = 0.0;
//        Double TotalBillDisount = 0.0;
//        Double GrossTotal = 0.0;
//        Double ServiceCharges = 0.0;
//        Double TotalNettSales = 0.0;
//        Double TotalTaxes = 0.0;
//        Double RoundAdj = 0.0;
//        Double DiscountID = 0.0;
//        Double DiscountValue = 0.0;
//
//        String sql = "SELECT ID,BillNo,TotalQty,SubTotal,Totalamount,Changeamount,PaymentTypeID,PaymentTypeName,PaymentAmount, " +
//                "GrossSales,TotalItemDisount,TotalBillDisount,GrossTotal,ServiceCharges,TotalNettSales,TotalTaxes," +
//                "CashierName,SalesDateTime,BillHour,STATUS, " +
//                "RoundAdj,DiscountID,DiscountName,DiscountType,DiscountTypeName,DiscountValue,BillID " +
//                "FROM Sales " +
//                "where ID = " + sales_id;
//        Log.i("__sql", sql);
//        Cursor c = DBFunc.Query(sql, false);
//        if (c != null) {
//            if (c.moveToNext()) {
//                ID = c.getInt(0);
//                BillNo =  c.getString(1);
//
//                if (c.getInt(2) < 0){
//                    TotalQty = c.getInt(2);
//                    SubTotal = c.getDouble(3);
//                    Totalamount = c.getDouble(4);
//                    Changeamount = c.getDouble(5);
//                    PaymentTypeID = c.getInt(6);
//                    PaymentTypeName = c.getString(7);
//                    PaymentAmount = c.getDouble(8);
//                    GrossSales = c.getDouble(9);
//                    TotalItemDisount = c.getDouble(10);
//                    TotalBillDisount = c.getDouble(11);
//                    GrossTotal = c.getDouble(12);
//                    ServiceCharges = c.getDouble(13);
//                    TotalNettSales = c.getDouble(14);
//                    TotalTaxes = c.getDouble(15);
//                    CashierName = c.getString(16);
////                        SalesDateTime =  c.getString(17);
////                        BillHour =  c.getString(18);
////                        STATUS =  c.getString(19);
//                    RoundAdj = c.getDouble(20);
//                    DiscountID = c.getDouble(21);
//                    DiscountName = c.getString(22);
//                    DiscountType = c.getString(23);
//                    DiscountTypeName = c.getString(24);
//                    DiscountValue = c.getDouble(25);
//                }else {
//                    TotalQty = (-1) * c.getInt(2);
//                    SubTotal = (-1) * c.getDouble(3);
//                    Totalamount = (-1) * c.getDouble(4);
//                    Changeamount = (-1) * c.getDouble(5);
//                    PaymentTypeID = c.getInt(6);
//                    PaymentTypeName = c.getString(7);
//                    PaymentAmount = (-1) * c.getDouble(8);
//                    GrossSales = (-1) * c.getDouble(9);
//                    TotalItemDisount = (-1) * c.getDouble(10);
//                    TotalBillDisount = (-1) * c.getDouble(11);
//                    GrossTotal = (-1) * c.getDouble(12);
//                    ServiceCharges = (-1) * c.getDouble(13);
//                    TotalNettSales = (-1) * c.getDouble(14);
//                    TotalTaxes = (-1) * c.getDouble(15);
//                    CashierName = c.getString(16);
////                        SalesDateTime =  c.getString(17);
////                        BillHour =  c.getString(18);
////                        STATUS =  c.getString(19);
//                    RoundAdj = (-1) * c.getDouble(20);
//                    DiscountID = c.getDouble(21);
//                    DiscountName = c.getString(22);
//                    DiscountType = c.getString(23);
//                    DiscountTypeName = c.getString(24);
//                    DiscountValue = (-1) * c.getDouble(25);
//                }
//
//                String s = "Select ID FROM Sales WHERE BillNo = '" +refubdBillNo + "'";
//                Cursor cc = DBFunc.Query(s,false);
//                if (cc != null) {
//                    if (cc.getCount() == 0) {
//                        //REFUND
//                        String uniqueId  = UUID.randomUUID().toString();
////                        sql = "INSERT INTO Sales (UUID,BillNo,TotalQty,SubTotal,Totalamount,Changeamount,PaymentTypeID,PaymentTypeName,PaymentAmount," +
////                                "GrossSales,TotalItemDisount,TotalBillDisount,GrossTotal,ServiceCharges,TotalNettSales,TotalTaxes,CashierName,SalesDateTime,BillHour,STATUS," +
////                                "RoundAdj,DiscountID,DiscountName,DiscountType,DiscountTypeName,DiscountValue," +
////                                "ReferenceBillNo,ReferenceSalesID,BillID,IsZ,DateTime) VALUES (";
//                        if (str_Amend.equals("Amend")){
//                            sql = "INSERT INTO Sales (UUID,BillNo,TotalQty,SubTotal,Totalamount,Changeamount,PaymentTypeID,PaymentTypeName,PaymentAmount," +
//                                    "GrossSales,TotalItemDisount,TotalBillDisount,GrossTotal,ServiceCharges,TotalNettSales,TotalTaxes,CashierName,SalesDateTime,BillHour,STATUS," +
//                                    "RoundAdj,DiscountID,DiscountName,DiscountType,DiscountTypeName,DiscountValue," +
//                                    "ReferenceBillNo,ReferenceSalesID,BillID,IsZ,DateTime) VALUES (";
//                        }else {
//                            sql = "INSERT INTO Sales (UUID,BillNo,TotalQty,SubTotal,Totalamount,Changeamount,PaymentTypeID,PaymentTypeName,PaymentAmount," +
//                                    "GrossSales,TotalItemDisount,TotalBillDisount,GrossTotal,ServiceCharges,TotalNettSales,TotalTaxes,CashierName,SalesDateTime,BillHour,STATUS," +
//                                    "RoundAdj,DiscountID,DiscountName,DiscountType,DiscountTypeName,DiscountValue," +
//                                    "ReferenceBillNo,ReferenceSalesID,BillID,DateTime) VALUES (";
//                        }
//                        sql +=  "'" + uniqueId + "', ";
//                        sql +=  "'" + refubdBillNo + "', ";
//                        sql += "'" + DBFunc.PurifyString(String.valueOf(TotalQty)) + "', ";
//                        sql += "'" + String.format("%.2f", Double.valueOf(SubTotal)) + "', ";
//                        sql += "'" + String.format("%.2f", Double.valueOf(Totalamount)) + "', ";
//                        sql += "'" + String.format("%.2f", Double.valueOf(Changeamount)) + "', ";
//                        sql += "'" + DBFunc.PurifyString(String.valueOf(PaymentTypeID)) + "', ";
//                        sql += "'" + DBFunc.PurifyString(PaymentTypeName) + "', ";
//                        sql += "'" + String.format("%.2f", Double.valueOf(PaymentAmount)) + "', ";
//                        sql += "'" + String.format("%.2f", Double.valueOf(GrossSales)) + "', ";
//                        sql += "'" + String.format("%.2f", Double.valueOf(TotalItemDisount)) + "', ";
//                        sql += "'" + String.format("%.2f", Double.valueOf(TotalBillDisount)) + "', ";
//                        sql += "'" + String.format("%.2f", Double.valueOf(GrossTotal)) + "', ";
//                        sql += "'" + String.format("%.2f", Double.valueOf(ServiceCharges)) + "', ";
//                        sql += "'" + String.format("%.2f", Double.valueOf(TotalNettSales))  + "', ";
//                        sql += "'" + String.format("%.2f", Double.valueOf(TotalTaxes)) + "', ";
//                        sql += "'" + DBFunc.PurifyString(String.valueOf(CashierName)) + "', ";
//                        sql += "'" + dateFormat3 + "', ";
//                        sql += "'" + dateFormat4 + "', ";
//                        sql += "'REFUND', ";
//                        sql += "'" + RoundAdj + "', ";
//                        sql += "'" + DiscountID + "', ";
//                        sql += "'" + DiscountName + "', ";
//                        sql += "'" + DiscountType + "', ";
//                        sql += "'" + DiscountTypeName + "', ";
//                        sql += "'" + DiscountValue + "', ";
//                        sql += "'" + BillNo + "', ";
//                        sql += "'" + ID + "', ";
//                        sql += "'" + bId + "', ";
//                        if (str_Amend.equals("Amend")){
//                            sql += "'Z', ";
//                        }
//                        sql += dt + ")";
//
//                        DBFunc.ExecQuery(sql, false);
//                    }
//                    cc.close();
//                }
//
//                Integer sale_id = Query.findLatestID("ID","Sales",false);
//                sql = "SELECT PluId,PluName,CategoryId,CategoryName,Qty,Price " +
//                        "FROM SalesItem " +
//                        "where ID = " + ID;
//                Log.i("__sql", sql);
//                c = DBFunc.Query(sql, false);
//                if (c != null) {
//                    while (c.moveToNext()) {
//
//                        String sql_chk_plu_existing = "Select PluId from SalesItem WHERE BillNo = '" + BillNo +
//                                "' AND PluId = " + c.getInt(0);
//                        Log.i("Df__","dfdf__"+sql_chk_plu_existing);
//                        Cursor curpluexisting = DBFunc.Query(sql_chk_plu_existing, false);
//                        if (curpluexisting != null) {
//                            if (curpluexisting.getCount() == 0) {
//
//                                sql = "INSERT INTO SalesItem (SalesID,BillNo,PluId,PluName,CategoryId,CategoryName,Qty,Price,DateTime) VALUES (";
//                                sql += sale_id + ", ";
//                                sql += "'" + DBFunc.PurifyString(refubdBillNo) + "', ";
//                                sql += "'" + DBFunc.PurifyString(String.valueOf(c.getString(0))) + "', ";
//                                sql += "'" + DBFunc.PurifyString(String.valueOf(c.getString(1))) + "', ";
//                                sql += "'" + DBFunc.PurifyString(String.valueOf(c.getString(2))) + "', ";
//                                sql += "'" + DBFunc.PurifyString(String.valueOf(c.getString(3))) + "', ";
//                                String sql_chk_qty_count = " SELECT couni(ProductQty),SUM(ProductPrice) FROM Details_BillProduct " +
//                                        " Where BillNo = '" + BillNo + "' AND ProductID = '"+c.getInt(0) +"'" +
//                                        " AND Cancel = 'REFUND'";
//                                Log.i("DfdfsCount___","sql_chk_qty_count___"+sql_chk_qty_count);
//                                Integer cc_qty = 0;
//                                Cursor cursql_chk_qty_count = DBFunc.Query(sql_chk_qty_count, false);
//                                if (cursql_chk_qty_count != null) {
//                                    cc_qty = 0;
//                                    while (cursql_chk_qty_count.moveToNext()){
//                                        cc_qty += cursql_chk_qty_count.getInt(0);
//                                    }
//                                }
//
//
//                                //sql += "'" + DBFunc.PurifyString(String.valueOf(CheckOutActivity.sldQtyArr.get(i))) + "', ";
//                                sql += "'" + DBFunc.PurifyString(String.valueOf(cc_qty)) + "', ";
//                                sql += "'" + DBFunc.PurifyString(String.valueOf(c.getString(5))) + "', ";
//                                sql += dt + ")";
//                                Log.i("DFdf___","DQL"+sql);
//                                DBFunc.ExecQuery(sql, false);
//                            }
//                            curpluexisting.close();
//                        }
//
//                    }
//                    c.close();
//                }
//            }
//            c.close();
//        }
//    }

//    public static void RefundSalesAndItems1(String refubdBillNo,Integer sales_id,String dateFormat3,String dateFormat4,
//                                            String bId,long dt,String str_Amend) {
//        Integer TotalQty = 0;
//        Integer ID = 0;
//        Integer PaymentTypeID = 0;
////            String BillNo = "";
//        String CashierName = "";
//        String SalesDateTime = "";
//        String BillHour = "";
//        String STATUS = "";
//        String DiscountName = "";
//        String DiscountType = "";
//        String DiscountTypeName = "";
//        String PaymentTypeName = "";
//        Double SubTotal = 0.0;
//        Double Totalamount = 0.0;
//        Double Changeamount = 0.0;
//        Double PaymentAmount = 0.0;
//        Double GrossSales = 0.0;
//        Double TotalItemDisount = 0.0;
//        Double TotalBillDisount = 0.0;
//        Double GrossTotal = 0.0;
//        Double ServiceCharges = 0.0;
//        Double TotalNettSales = 0.0;
//        Double TotalTaxes = 0.0;
//        Double RoundAdj = 0.0;
//        Double DiscountID = 0.0;
//        Double DiscountValue = 0.0;
//
//        String sql = "SELECT ID,BillNo,TotalQty,SubTotal,Totalamount,Changeamount,PaymentTypeID,PaymentTypeName,PaymentAmount, " +
//                "GrossSales,TotalItemDisount,TotalBillDisount,GrossTotal,ServiceCharges,TotalNettSales,TotalTaxes," +
//                "CashierName,SalesDateTime,BillHour,STATUS, " +
//                "RoundAdj,DiscountID,DiscountName,DiscountType,DiscountTypeName,DiscountValue,BillID " +
//                "FROM Sales " +
//                "where BillNo = '00000555' " ;
////                "where ID = " + sales_id;
//        Log.i("__sql", sql);
//        Cursor c = DBFunc.Query(sql, false);
//        if (c != null) {
//            if (c.moveToNext()) {
//                ID = c.getInt(0);
////                BillNo =  c.getString(1);
//
//                if (c.getInt(2) < 0){
//                    TotalQty = c.getInt(2);
//                    SubTotal = c.getDouble(3);
//                    Totalamount = c.getDouble(4);
//                    Changeamount = c.getDouble(5);
//                    PaymentTypeID = c.getInt(6);
//                    PaymentTypeName = c.getString(7);
//                    PaymentAmount = c.getDouble(8);
//                    GrossSales = c.getDouble(9);
//                    TotalItemDisount = c.getDouble(10);
//                    TotalBillDisount = c.getDouble(11);
//                    GrossTotal = c.getDouble(12);
//                    ServiceCharges = c.getDouble(13);
//                    TotalNettSales = c.getDouble(14);
//                    TotalTaxes = c.getDouble(15);
//                    CashierName = c.getString(16);
////                        SalesDateTime =  c.getString(17);
////                        BillHour =  c.getString(18);
////                        STATUS =  c.getString(19);
//                    RoundAdj = c.getDouble(20);
//                    DiscountID = c.getDouble(21);
//                    DiscountName = c.getString(22);
//                    DiscountType = c.getString(23);
//                    DiscountTypeName = c.getString(24);
//                    DiscountValue = c.getDouble(25);
//                }else {
//                    TotalQty = (-1) * c.getInt(2);
//                    SubTotal = (-1) * c.getDouble(3);
//                    Totalamount = (-1) * c.getDouble(4);
//                    Changeamount = (-1) * c.getDouble(5);
//                    PaymentTypeID = c.getInt(6);
//                    PaymentTypeName = c.getString(7);
//                    PaymentAmount = (-1) * c.getDouble(8);
//                    GrossSales = (-1) * c.getDouble(9);
//                    TotalItemDisount = (-1) * c.getDouble(10);
//                    TotalBillDisount = (-1) * c.getDouble(11);
//                    GrossTotal = (-1) * c.getDouble(12);
//                    ServiceCharges = (-1) * c.getDouble(13);
//                    TotalNettSales = (-1) * c.getDouble(14);
//                    TotalTaxes = (-1) * c.getDouble(15);
//                    CashierName = c.getString(16);
////                        SalesDateTime =  c.getString(17);
////                        BillHour =  c.getString(18);
////                        STATUS =  c.getString(19);
//                    RoundAdj = (-1) * c.getDouble(20);
//                    DiscountID = c.getDouble(21);
//                    DiscountName = c.getString(22);
//                    DiscountType = c.getString(23);
//                    DiscountTypeName = c.getString(24);
//                    DiscountValue = (-1) * c.getDouble(25);
//                }
//
////                String s = "Select ID FROM Sales WHERE BillNo = '" +refubdBillNo + "'" +
////                        " AND ReferenceBillNo = '00000555'";
////                Log.i("DSDFF___","DFF___"+s);
////                Cursor cc = DBFunc.Query(s,false);
////                if (cc != null) {
////                    if (cc.getCount() == 0) {
//                        //REFUND
//                        String uniqueId  = UUID.randomUUID().toString();
////                    sql = "INSERT INTO Sales (UUID,BillNo,TotalQty,SubTotal,Totalamount,Changeamount,PaymentTypeID,PaymentTypeName,PaymentAmount," +
////                            "GrossSales,TotalItemDisount,TotalBillDisount,GrossTotal,ServiceCharges,TotalNettSales,TotalTaxes,CashierName,SalesDateTime,BillHour,STATUS," +
////                            "RoundAdj,DiscountID,DiscountName,DiscountType,DiscountTypeName,DiscountValue," +
////                            "ReferenceBillNo,ReferenceSalesID,BillID,IsZ,DateTime) VALUES (";
//                        if (str_Amend.equals("Amend")){
//                            sql = "INSERT INTO Sales (UUID,BillNo,TotalQty,SubTotal,Totalamount,Changeamount,PaymentTypeID,PaymentTypeName,PaymentAmount," +
//                                    "GrossSales,TotalItemDisount,TotalBillDisount,GrossTotal,ServiceCharges,TotalNettSales,TotalTaxes,CashierName,SalesDateTime,BillHour,STATUS," +
//                                    "RoundAdj,DiscountID,DiscountName,DiscountType,DiscountTypeName,DiscountValue," +
//                                    "ReferenceBillNo,ReferenceSalesID,BillID,IsZ,DateTime) VALUES (";
//                        }else {
//                            sql = "INSERT INTO Sales (UUID,BillNo,TotalQty,SubTotal,Totalamount,Changeamount,PaymentTypeID,PaymentTypeName,PaymentAmount," +
//                                    "GrossSales,TotalItemDisount,TotalBillDisount,GrossTotal,ServiceCharges,TotalNettSales,TotalTaxes,CashierName,SalesDateTime,BillHour,STATUS," +
//                                    "RoundAdj,DiscountID,DiscountName,DiscountType,DiscountTypeName,DiscountValue," +
//                                    "ReferenceBillNo,ReferenceSalesID,BillID,DateTime) VALUES (";
//                        }
//                        sql +=  "'" + uniqueId + "', ";
//                        sql +=  "'" + refubdBillNo + "', ";
//                        sql += "'" + DBFunc.PurifyString(String.valueOf(TotalQty)) + "', ";
//                        sql += "'" + String.format("%.2f", Double.valueOf(SubTotal)) + "', ";
//                        sql += "'" + String.format("%.2f", Double.valueOf(Totalamount)) + "', ";
//                        sql += "'" + String.format("%.2f", Double.valueOf(Changeamount)) + "', ";
//                        sql += "'" + DBFunc.PurifyString(String.valueOf(PaymentTypeID)) + "', ";
//                        sql += "'" + DBFunc.PurifyString(PaymentTypeName) + "', ";
//                        sql += "'" + String.format("%.2f", Double.valueOf(PaymentAmount)) + "', ";
//                        sql += "'" + String.format("%.2f", Double.valueOf(GrossSales)) + "', ";
//                        sql += "'" + String.format("%.2f", Double.valueOf(TotalItemDisount)) + "', ";
//                        sql += "'" + String.format("%.2f", Double.valueOf(TotalBillDisount)) + "', ";
//                        sql += "'" + String.format("%.2f", Double.valueOf(GrossTotal)) + "', ";
//                        sql += "'" + String.format("%.2f", Double.valueOf(ServiceCharges)) + "', ";
//                        sql += "'" + String.format("%.2f", Double.valueOf(TotalNettSales))  + "', ";
//                        sql += "'" + String.format("%.2f", Double.valueOf(TotalTaxes)) + "', ";
//                        sql += "'" + DBFunc.PurifyString(String.valueOf(CashierName)) + "', ";
//                        sql += "'" + dateFormat3 + "', ";
//                        sql += "'" + dateFormat4 + "', ";
//                        sql += "'REFUND', ";
//                        sql += "'" + RoundAdj + "', ";
//                        sql += "'" + DiscountID + "', ";
//                        sql += "'" + DiscountName + "', ";
//                        sql += "'" + DiscountType + "', ";
//                        sql += "'" + DiscountTypeName + "', ";
//                        sql += "'" + DiscountValue + "', ";
//                        sql += "'00000555', ";
//                        sql += "'" + ID + "', ";
//                        sql += "'" + bId + "', ";
//                        if (str_Amend.equals("Amend")){
//                            sql += "'Z', ";
//                        }
//                        sql += dt + ")";
//
//                        DBFunc.ExecQuery(sql, false);
////                    }
////                    cc.close();
////                }
//
//                Integer sale_id = Query.findLatestID("ID","Sales",false);
//                sql = "SELECT PluId,PluName,CategoryId,CategoryName,Qty,Price " +
//                        "FROM SalesItem " +
//                        "where BillNo = '00000555' " ;
////                        "where SalesID = " + ID;
//                Log.i("__sql", sql);
//                c = DBFunc.Query(sql, false);
//                if (c != null) {
//                    while (c.moveToNext()) {
////                        //String sql_chk_plu_existing = "Select PluId from SalesItem WHERE BillNo = '" + BillNo +
////                        String sql_chk_plu_existing = "Select PluId from SalesItem WHERE BillNo = '" + refubdBillNo +
////                                "' AND PluId = " + c.getInt(0);
////                        Log.i("Df__","dfdf__"+sql_chk_plu_existing);
////                        Cursor curpluexisting = DBFunc.Query(sql_chk_plu_existing, false);
////                        if (curpluexisting != null) {
////                            if (curpluexisting.getCount() == 0) {
//
//                                sql = "INSERT INTO SalesItem (SalesID,BillNo,PluId,PluName,CategoryId,CategoryName,Qty,Price,DateTime) VALUES (";
//                                sql += sale_id + ", ";
//                                sql += "'" + DBFunc.PurifyString(refubdBillNo) + "', ";
//                                sql += "'" + DBFunc.PurifyString(String.valueOf(c.getString(0))) + "', ";
//                                sql += "'" + DBFunc.PurifyString(String.valueOf(c.getString(1))) + "', ";
//                                sql += "'" + DBFunc.PurifyString(String.valueOf(c.getString(2))) + "', ";
//                                sql += "'" + DBFunc.PurifyString(String.valueOf(c.getString(3))) + "', ";
////                            String sql_chk_qty_count = " SELECT count(ProductQty),SUM(ProductPrice) FROM Details_BillProduct " +
////                                    " Where BillNo = '" + BillNo + "' AND ProductID = '"+c.getInt(0) +"'" +
////                                    " AND Cancel = 'REFUND'";
////                            Log.i("DfdfsCount___","sql_chk_qty_count___"+sql_chk_qty_count);
////                            Integer cc_qty = 0;
////                            Cursor cursql_chk_qty_count = DBFunc.Query(sql_chk_qty_count, false);
////                            if (cursql_chk_qty_count != null) {
////                                cc_qty = 0;
////                                while (cursql_chk_qty_count.moveToNext()){
////                                    cc_qty += cursql_chk_qty_count.getInt(0);
////                                }
////                            }
//
//
//                                //sql += "'" + DBFunc.PurifyString(String.valueOf(CheckOutActivity.sldQtyArr.get(i))) + "', ";
//                                sql += "'" + DBFunc.PurifyString(String.valueOf((-1) * c.getInt(4))) + "', ";
//                                sql += "'" + DBFunc.PurifyString(String.valueOf(((-1) * c.getDouble(5)))) + "', ";
//                                sql += dt + ")";
//
//                                DBFunc.ExecQuery(sql, false);
////                            }
////                            curpluexisting.close();
////                        }
//
//                    }
//                    c.close();
//                }
//            }
//            c.close();
//        }
//    }

     private static void RefundSalesAndItems(String BillNo, String refubdBillNo,Integer sales_id,String dateFormat3,String dateFormat4,
                                            String bId,long dt,String str_Amend) {
        Integer TotalQty = 0;
        Integer ID = 0;
        Integer PaymentTypeID = 0;
//            String BillNo = "";
        String CashierName = "";
        String SalesDateTime = "";
        String BillHour = "";
        String STATUS = "";
        String DiscountName = "";
        String DiscountType = "";
        String DiscountTypeName = "";
        String PaymentTypeName = "";
        Double SubTotal = 0.0;
        Double Totalamount = 0.0;
        Double Changeamount = 0.0;
        Double PaymentAmount = 0.0;
        Double GrossSales = 0.0;
        Double TotalItemDisount = 0.0;
        Double TotalBillDisount = 0.0;
        Double GrossTotal = 0.0;
        Double ServiceCharges = 0.0;
        Double TotalNettSales = 0.0;
        Double TotalTaxes = 0.0;
        Double RoundAdj = 0.0;
        Double DiscountID = 0.0;
        Double DiscountValue = 0.0;
        String ReceiptRemarks = "";

        String sql = "SELECT ID,BillNo,TotalQty,SubTotal,Totalamount,Changeamount,PaymentTypeID,PaymentTypeName,PaymentAmount, " +
                "GrossSales,TotalItemDisount,TotalBillDisount,GrossTotal,ServiceCharges,TotalNettSales,TotalTaxes," +
                "CashierName,SalesDateTime,BillHour,STATUS, " +
                "RoundAdj,DiscountID,DiscountName,DiscountType,DiscountTypeName,DiscountValue,BillID,ReceiptRemarks " +
                "FROM Sales " +
//                "where BillNo = '" + refubdBillNo+"'";
                "where ID = " + sales_id;
        Log.i("__sql", sql);
        Cursor c = DBFunc.Query(sql, false);
        if (c != null) {
            if (c.moveToNext()) {
                ID = c.getInt(0);
                //BillNo =  c.getString(1);

                if (c.getInt(2) < 0){
                    TotalQty = c.getInt(2);
                    SubTotal = c.getDouble(3);
                    Totalamount = c.getDouble(4);
                    Changeamount = c.getDouble(5);
                    PaymentTypeID = c.getInt(6);
                    PaymentTypeName = c.getString(7);
                    PaymentAmount = c.getDouble(8);
                    GrossSales = c.getDouble(9);
                    TotalItemDisount = c.getDouble(10);
                    TotalBillDisount = c.getDouble(11);
                    GrossTotal = c.getDouble(12);
                    ServiceCharges = c.getDouble(13);
                    TotalNettSales = c.getDouble(14);
                    TotalTaxes = c.getDouble(15);
                    CashierName = c.getString(16);
//                        SalesDateTime =  c.getString(17);
//                        BillHour =  c.getString(18);
//                        STATUS =  c.getString(19);
                    RoundAdj = c.getDouble(20);
                    DiscountID = c.getDouble(21);
                    DiscountName = c.getString(22);
                    DiscountType = c.getString(23);
                    DiscountTypeName = c.getString(24);
                    DiscountValue = c.getDouble(25);
                    ReceiptRemarks = c.getString(27);
                }else {
                    TotalQty = (-1) * c.getInt(2);
                    SubTotal = (-1) * c.getDouble(3);
                    Totalamount = (-1) * c.getDouble(4);
                    Changeamount = (-1) * c.getDouble(5);
                    PaymentTypeID = c.getInt(6);
                    PaymentTypeName = c.getString(7);
                    PaymentAmount = (-1) * c.getDouble(8);
                    GrossSales = (-1) * c.getDouble(9);
                    TotalItemDisount = (-1) * c.getDouble(10);
                    TotalBillDisount = (-1) * c.getDouble(11);
                    GrossTotal = (-1) * c.getDouble(12);
                    ServiceCharges = (-1) * c.getDouble(13);
                    TotalNettSales = (-1) * c.getDouble(14);
                    TotalTaxes = (-1) * c.getDouble(15);
                    CashierName = c.getString(16);
//                        SalesDateTime =  c.getString(17);
//                        BillHour =  c.getString(18);
//                        STATUS =  c.getString(19);
                    RoundAdj = (-1) * c.getDouble(20);
                    DiscountID = c.getDouble(21);
                    DiscountName = c.getString(22);
                    DiscountType = c.getString(23);
                    DiscountTypeName = c.getString(24);
                    DiscountValue = (-1) * c.getDouble(25);
                    ReceiptRemarks = c.getString(27);
                }

                String s = "Select ID FROM Sales WHERE BillNo = '" +refubdBillNo + "'" +
                        " AND ReferenceBillNo = '"+BillNo+"'";

                String sf = "Select ID,BillNo FROM Sales WHERE BillNo = '" +BillNo + "' ";

                Integer iddd = 0;
                String idbno = "0";
                Cursor ccsf = DBFunc.Query(sf,false);
                if (ccsf != null) {
                    if (ccsf.moveToNext()){
                        iddd = ccsf.getInt(0);
                        idbno = ccsf.getString(1);
                    }
                    ccsf.close();
                }

                Cursor ss = DBFunc.Query(s,false);
                if (ss != null){
                    if (ss.getCount() == 0){

//                    if (cc.getCount() == 0) {
                        //REFUND
                        String uniqueId  = UUID.randomUUID().toString();
//                    sql = "INSERT INTO Sales (UUID,BillNo,TotalQty,SubTotal,Totalamount,Changeamount,PaymentTypeID,PaymentTypeName,PaymentAmount," +
//                            "GrossSales,TotalItemDisount,TotalBillDisount,GrossTotal,ServiceCharges,TotalNettSales,TotalTaxes,CashierName,SalesDateTime,BillHour,STATUS," +
//                            "RoundAdj,DiscountID,DiscountName,DiscountType,DiscountTypeName,DiscountValue," +
//                            "ReferenceBillNo,ReferenceSalesID,BillID,IsZ,DateTime) VALUES (";
                        if (str_Amend.equals("Amend")){
                            sql = "INSERT INTO Sales (UUID,BillNo,TotalQty,SubTotal,Totalamount,Changeamount,PaymentTypeID,PaymentTypeName,PaymentAmount," +
                                    "GrossSales,TotalItemDisount,TotalBillDisount,GrossTotal,ServiceCharges,TotalNettSales,TotalTaxes,CashierName,SalesDateTime,BillHour,STATUS," +
                                    "RoundAdj,DiscountID,DiscountName,DiscountType,DiscountTypeName,DiscountValue," +
                                    "ReferenceBillNo,ReferenceSalesID,BillID,IsZ,ReceiptRemarks,DateTime) VALUES (";
                        }else {
                            sql = "INSERT INTO Sales (UUID,BillNo,TotalQty,SubTotal,Totalamount,Changeamount,PaymentTypeID,PaymentTypeName,PaymentAmount," +
                                    "GrossSales,TotalItemDisount,TotalBillDisount,GrossTotal,ServiceCharges,TotalNettSales,TotalTaxes,CashierName,SalesDateTime,BillHour,STATUS," +
                                    "RoundAdj,DiscountID,DiscountName,DiscountType,DiscountTypeName,DiscountValue," +
                                    "ReferenceBillNo,ReferenceSalesID,BillID,ReceiptRemarks,DateTime) VALUES (";
                        }
                        sql +=  "'" + uniqueId + "', ";
                        sql +=  "'" + refubdBillNo + "', ";
                        sql += "'" + DBFunc.PurifyString(String.valueOf(TotalQty)) + "', ";
                        sql += "'" + String.format("%.2f", Double.valueOf(SubTotal)) + "', ";
                        sql += "'" + String.format("%.2f", Double.valueOf(Totalamount)) + "', ";
                        sql += "'" + String.format("%.2f", Double.valueOf(Changeamount)) + "', ";
                        sql += "'" + DBFunc.PurifyString(String.valueOf(PaymentTypeID)) + "', ";
                        sql += "'" + DBFunc.PurifyString(PaymentTypeName) + "', ";
                        sql += "'" + String.format("%.2f", Double.valueOf(PaymentAmount)) + "', ";
                        sql += "'" + String.format("%.2f", Double.valueOf(GrossSales)) + "', ";
                        sql += "'" + String.format("%.2f", Double.valueOf(TotalItemDisount)) + "', ";
                        sql += "'" + String.format("%.2f", Double.valueOf(TotalBillDisount)) + "', ";
                        sql += "'" + String.format("%.2f", Double.valueOf(GrossTotal)) + "', ";
                        sql += "'" + String.format("%.2f", Double.valueOf(ServiceCharges)) + "', ";
                        sql += "'" + String.format("%.2f", Double.valueOf(TotalNettSales))  + "', ";
                        sql += "'" + String.format("%.2f", Double.valueOf(TotalTaxes)) + "', ";
                        sql += "'" + DBFunc.PurifyString(String.valueOf(CashierName)) + "', ";
                        sql += "'" + dateFormat3 + "', ";
                        sql += "'" + dateFormat4 + "', ";
                        sql += "'REFUND', ";
                        sql += "'" + RoundAdj + "', ";
                        sql += "'" + DiscountID + "', ";
                        sql += "'" + DiscountName + "', ";
                        sql += "'" + DiscountType + "', ";
                        sql += "'" + DiscountTypeName + "', ";
                        sql += "'" + DiscountValue + "', ";

                        sql += "'" + idbno + "', ";
                        sql += "'" + iddd + "', ";
//                        sql += "'" + BillNo + "', ";
//                        sql += "'" + ID + "', ";
                        sql += "'" + bId + "', ";
                        if (str_Amend.equals("Amend")){
                            sql += "'Z', ";
                        }
                        sql += "'" + DBFunc.PurifyString(ReceiptRemarks) + "', ";
                        sql += dt + ")";

                        DBFunc.ExecQuery(sql, false);
                    }
                    ss.close();
                }
//                    }
//                    cc.close();
//                }

                Integer sale_id = Query.findLatestID("ID","Sales",false);
                sql = "SELECT PluId,PluName,CategoryId,CategoryName,Qty,Price " +
                        "FROM SalesItem " +
                        "where SalesID = " + ID;
                Log.i("__sql", sql);
                c = DBFunc.Query(sql, false);
                if (c != null) {
                    while (c.moveToNext()) {
                        //String sql_chk_plu_existing = "Select PluId from SalesItem WHERE BillNo = '" + BillNo +
                        String sql_chk_plu_existing = "Select PluId from SalesItem WHERE BillNo = '" + refubdBillNo +
                                "' AND PluId = " + c.getInt(0);

                        Cursor curpluexisting = DBFunc.Query(sql_chk_plu_existing, false);
                        if (curpluexisting != null) {
                            if (curpluexisting.getCount() == 0) {

                                sql = "INSERT INTO SalesItem (SalesID,BillNo,PluId,PluName,CategoryId,CategoryName,Qty,Price,DateTime) VALUES (";
                                sql += sale_id + ", ";
                                sql += "'" + DBFunc.PurifyString(refubdBillNo) + "', ";
                                sql += "'" + DBFunc.PurifyString(String.valueOf(c.getString(0))) + "', ";
                                sql += "'" + DBFunc.PurifyString(String.valueOf(c.getString(1))) + "', ";
                                sql += "'" + DBFunc.PurifyString(String.valueOf(c.getString(2))) + "', ";
                                sql += "'" + DBFunc.PurifyString(String.valueOf(c.getString(3))) + "', ";
//                            String sql_chk_qty_count = " SELECT count(ProductQty),SUM(ProductPrice) FROM Details_BillProduct " +
//                                    " Where BillNo = '" + BillNo + "' AND ProductID = '"+c.getInt(0) +"'" +
//                                    " AND Cancel = 'REFUND'";
//                            Log.i("DfdfsCount___","sql_chk_qty_count___"+sql_chk_qty_count);
//                            Integer cc_qty = 0;
//                            Cursor cursql_chk_qty_count = DBFunc.Query(sql_chk_qty_count, false);
//                            if (cursql_chk_qty_count != null) {
//                                cc_qty = 0;
//                                while (cursql_chk_qty_count.moveToNext()){
//                                    cc_qty += cursql_chk_qty_count.getInt(0);
//                                }
//                            }


                                //sql += "'" + DBFunc.PurifyString(String.valueOf(CheckOutActivity.sldQtyArr.get(i))) + "', ";
                                sql += "'" + DBFunc.PurifyString(String.valueOf((-1) * c.getInt(4))) + "', ";
                                sql += "'" + DBFunc.PurifyString(String.valueOf(((-1) * c.getDouble(5)))) + "', ";
                                sql += dt + ")";

                                DBFunc.ExecQuery(sql, false);
                            }
                            curpluexisting.close();
                        }

                    }
                    c.close();
                }
            }
            c.close();
        }
    }

    private static void RefundDetailsBillProduct(String BillNo, String refubdBillNo,String dateFormat3,String bId,long dt) {
        String detailsBillProductsql = "SELECT OnlineOrderBill,ProductQty,ProductName,ProductPrice,BillDetailsID, " +
                "ItemDiscountAmount,ProductID,CategoryID,CategoryName,vchQueueNo,intTableNo,Cancel, " +
                "TaxID,TaxType,TaxName,TaxAmount,DiscountID,DiscountName,DiscountType,DiscountTypeName,DiscountValue," +
                "AfterDiscountAmount,OpenPriceStatus,BeforeOpenPrice,ID,Remarks " +
                "FROM DetailsBillProduct " +
                "where BillNo = '" + BillNo + "'";
        Log.i("__sql", detailsBillProductsql);

        Cursor c = DBFunc.Query(detailsBillProductsql, false);
        if (c != null) {
            while (c.moveToNext()) {

                String OnlineOrderBill = c.getString(0);
                String ProductQty = c.getString(1);
                String ProductName = c.getString(2);
                String ProductPrice = c.getString(3);
                String BillDetailsID = c.getString(4);
                String ItemDiscountAmount = c.getString(5);
                String ProductID = c.getString(6);
                String CategoryID = c.getString(7);
                String CategoryName = c.getString(8);
                String vchQueueNo = c.getString(9);
                String intTableNo = c.getString(10);
                //String Cancel = c.getString(11);
                //String Cancel = "VOID";
                String Cancel = "REFUND";
                String TaxID = c.getString(12);
                String TaxType = c.getString(13);
                String TaxName = c.getString(14);
                String TaxAmount = c.getString(15);
                String DisID = c.getString(16);
                String DisName = c.getString(17);
                String DisType = c.getString(18);
                String DisTypeName = c.getString(19);
                String DisValue = c.getString(20);
                String AfterDisAmount = c.getString(21);
                String OpenPriceStatus = c.getString(22);
                String BeforeOpenPrice = c.getString(23);
                String oldDetailsBillPID = c.getString(24);
                String remarks = c.getString(25);

                DetailsBillProduct dbprod = new DetailsBillProduct();

                dbprod.setuUID(UUID.randomUUID().toString());
                dbprod.setBillNo(refubdBillNo);
                dbprod.setOnlineOrderBill(OnlineOrderBill);
                dbprod.setProductQty(ProductQty);
                dbprod.setProductName(ProductName);
                dbprod.setProductPrice(ProductPrice);
                dbprod.setBillDetailsID(BillDetailsID);
                dbprod.setBillDateTime(dateFormat3);
                dbprod.setItemDiscountAmount(ItemDiscountAmount);
                dbprod.setProductID(ProductID);
                dbprod.setCategoryID(CategoryID);
                dbprod.setCategoryName(CategoryName);
                dbprod.setVchQueueNo(vchQueueNo);
                dbprod.setIntTableNo(intTableNo);
                dbprod.setCancel(Cancel);
                dbprod.setBillID(bId);
                dbprod.setTaxID(TaxID);
                dbprod.setTaxType(TaxType);
                dbprod.setTaxName(TaxName);
                dbprod.setTaxAmount(TaxAmount);
                dbprod.setDiscountID(DisID);
                dbprod.setDiscountName(DisName);
                dbprod.setDiscountType(DisType);
                dbprod.setDiscountTypeName(DisTypeName);
                dbprod.setDiscountValue(DisValue);
                dbprod.setAfterDiscountAmount(AfterDisAmount);
                dbprod.setOpenPriceStatus(OpenPriceStatus);
                dbprod.setBeforeOpenPrice(BeforeOpenPrice);
                dbprod.setBillID(bId);
                dbprod.setRemarks(remarks);
                dbprod.setDt(dt);


                try {

                    Query.SaveDetailsBillProductByObjVal("QuerySavedb1280",dbprod);


                    Integer latestbilldetailsPID = Query.findLatestID("ID","DetailsBillProduct",false);

                    RefundPLUModi(BillNo,refubdBillNo,dateFormat3,bId,oldDetailsBillPID,latestbilldetailsPID,System.currentTimeMillis());



                }catch (Exception e){
                    DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                            System.currentTimeMillis(), DBFunc.PurifyString("Err-Query-RefundExcep" +  e.getMessage()));
                }

            }
            c.close();
        }
    }



    private static void SaveDetailsBillProductByObjVal(String querySavedb, DetailsBillProduct dbprod) {

        try {

            String sql = "INSERT INTO DetailsBillProduct (UUID,BillNo,OnlineOrderBill,ProductQty,ProductName,ProductPrice,BillDetailsID,BillDateTime," +
                    "ItemDiscountAmount,ProductID,CategoryID,CategoryName,vchQueueNo,intTableNo,Cancel," +
                    "BillID,TaxID,TaxType,TaxName,TaxAmount,DiscountID,DiscountName,DiscountType,DiscountTypeName," +
                    "DiscountValue,AfterDiscountAmount,OpenPriceStatus,BeforeOpenPrice,BillID,Remarks,DateTime) VALUES (";
            sql += "'" + dbprod.getuUID() + "', ";

            sql += "'" + dbprod.getBillNo() + "', ";
            sql += "'" + dbprod.getOnlineOrderBill() + "', ";
            sql += "'" + dbprod.getProductQty() + "', ";
            sql += "'" + dbprod.getProductName() + "', ";
            sql += "'" + String.format("%.2f", Double.valueOf(dbprod.getProductPrice())) + "', ";
            sql += "'" + dbprod.getBillDetailsID() + "', ";
            sql += "'" + dbprod.getBillDateTime() + "', ";
            sql += "'" + String.format("%.2f", Double.valueOf(dbprod.getItemDiscountAmount())) + "', ";
            sql += "'" + dbprod.getProductID() + "', ";
            sql += "'" + dbprod.getCategoryID() + "', ";
            sql += "'" + dbprod.getCategoryName() + "', ";
            sql += "'" + dbprod.getVchQueueNo() + "', ";
            sql += "'" + dbprod.getIntTableNo() + "', ";
            sql += "'" + dbprod.getCancel() + "', ";
            sql += "'" + dbprod.getBillID() + "', ";
            sql += "'" + dbprod.getTaxID() + "', ";
            sql += "'" + dbprod.getTaxType() + "', ";
            sql += "'" + dbprod.getTaxName() + "', ";
            sql += "'" + String.format("%.2f", Double.valueOf(dbprod.getTaxAmount())) + "', ";
            sql += "'" + dbprod.getDiscountID() + "', ";
            sql += "'" + dbprod.getDiscountName() + "', ";
            sql += "'" + dbprod.getDiscountType() + "', ";
            sql += "'" + dbprod.getDiscountTypeName() + "', ";
            sql += "'" + String.format("%.2f", Double.valueOf(dbprod.getDiscountValue())) + "', ";
            sql += "'" + String.format("%.2f", Double.valueOf(dbprod.getAfterDiscountAmount())) + "', ";
            sql += "'" + dbprod.getOpenPriceStatus() + "', ";
            ;
            sql += "'" + String.format("%.2f", Double.valueOf(dbprod.getBeforeOpenPrice())) + "', ";
            ;
            sql += "'" + dbprod.getBillID() + "', ";

            if (dbprod.getRemarks() != null) {
                if (dbprod.getRemarks().equals("null") || dbprod.getRemarks().equals("")) {
                    sql += "'', ";
                }else {
                    sql += "'" + dbprod.getRemarks() + "', ";
                }
            }else {
                sql += "'" + dbprod.getRemarks() + "', ";
            }
            sql += dbprod.getDt() + ")";


            DBFunc.ExecQuery(sql, false);

            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                    System.currentTimeMillis(), DBFunc.PurifyString("Saved-Query-Refund-"+querySavedb + "-"+sql));

        } catch (Exception e){
            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                    System.currentTimeMillis(), DBFunc.PurifyString("Err-Query-Refund-"+querySavedb + "-"+e.getMessage()));
        }
    }

    private static void RefundBillList(String BillNo,String refubdBillNo,long dt) {
        String Date = GetDateFormart55();
        String billListsql = "SELECT TotalItems,TableNo,QueueNo,OnlineOrderBill,TotalAmount " +
                "FROM BillList " +
                "where BillNo = '" + BillNo+"'";
        Log.i("__sqlGetDateFormart55", billListsql);
        Cursor c = DBFunc.Query(billListsql, false);
        if (c != null) {
            if (c.moveToNext()) {

                Integer totalItm = c.getInt(0);
                String tableNo = c.getString(1);
                String queueNo = c.getString(2);
                String onlineorderBill = c.getString(3);
                String totalamt = c.getString(4);


                String sql = "INSERT INTO BillList (BillID,BillNo,STATUS,TotalItems," +
                        "Date,TableNo,QueueNo,OnlineOrderBill,TotalAmount,DateTime) " +
                        "VALUES ('" + DBFunc.PurifyString(findBillIDByBillNo(refubdBillNo)) + "'," +
                        "'" + DBFunc.PurifyString(String.valueOf(refubdBillNo)) + "','" +
                        //DBFunc.PurifyString(blist.getSTATUS()) + "','" +
                        DBFunc.PurifyString("REFUND") + "','" +
                        DBFunc.PurifyString(totalItm.toString()) + "','" +
                        DBFunc.PurifyString(Date) + "','" +
                        DBFunc.PurifyString(tableNo) + "','" +
                        DBFunc.PurifyString(queueNo) + "','" +
                        DBFunc.PurifyString(onlineorderBill) + "','" +
                        DBFunc.PurifyString(totalamt) + "'," +
                        "" + dt + ")";

                DBFunc.ExecQuery(sql, false);
            }
            c.close();
        }
    }

//    private static void RefundBillPayment(String BillNo,String refubdBillNo) {
//        String billPaymentsql = "SELECT BillNo,PaymentID,Name,Amount,QRCode,ChangeAmount,SaleSync " +
//                "FROM BillPayment " +
//                "where BillNo = " + BillNo;
//        Log.i("__sql", billPaymentsql);
//        Cursor c = DBFunc.Query(billPaymentsql, false);
//        if (c != null) {
//            while (c.moveToNext()) {
//
//                //String billPaymentBillNo = c.getString(0);
//                String billPaymentPaymentID = c.getString(1);
//                String billPaymentPaymentName = c.getString(2);
//                Double billPaymentPaymentAmount = c.getDouble(3);
//                String billPaymentQRCode = c.getString(4);
//                String billPaymentChangeAmount = c.getString(5);
//                String billPaymentSaleSync = c.getString(6);
//
//                String sql = "INSERT INTO BillPayment (BillNo,PaymentID,Name,Amount,PaymentDateTime,QRCode,ChangeAmount,SaleSync) VALUES (";
//                //sql += BillNo + ", ";
//                sql += "'"+refubdBillNo+"'" + ", ";
//                sql += billPaymentPaymentID + ", ";
//                sql += "'" + DBFunc.PurifyString(DBFunc.PurifyString(billPaymentPaymentName)) + "', ";
//                sql += Query.DoubleAmountFormat(Double.valueOf(billPaymentPaymentAmount)) + ", ";
//                sql += System.currentTimeMillis() + ", ";
//                sql += "'" + billPaymentQRCode + "', ";
//                sql += "'" + Query.DoubleAmountFormat(Double.valueOf(billPaymentChangeAmount)) + "', ";
//                sql += "'"+billPaymentSaleSync+"')";
//                Log.i("Sf_SaveBillPayment", sql);
//                DBFunc.ExecQuery(sql, false);
//
////                    Query.SaveBillPayment(billPaymentBillNo,billPaymentPaymentID,billPaymentPaymentName,billPaymentPaymentAmount,
////                            billPaymentQRCode,billPaymentChangeAmount);
//            }
//            c.close();
//        }
//    }

    private static void RefundBillPayment(String BillNo,String refubdBillNo) {
        String billPaymentsql = "SELECT BillNo,PaymentID,Name,Amount,QRCode,ChangeAmount,SaleSync," +
                "EwalletStatus,EwalletPaymentType,EwalletIssueBanker,PaymentRemarks  " +
                "FROM BillPayment " +
                "where BillNo = '" + BillNo +"'";
        Log.i("__sql", billPaymentsql);
        Cursor c = DBFunc.Query(billPaymentsql, false);
        if (c != null) {
            while (c.moveToNext()) {

                //String billPaymentBillNo = c.getString(0);
                String billPaymentPaymentID = c.getString(1);
                String billPaymentPaymentName = c.getString(2);
                Double billPaymentPaymentAmount = (-1) * c.getDouble(3);
                String billPaymentQRCode = c.getString(4);
                String billPaymentChangeAmount = String.valueOf((-1) *  c.getDouble(5));
                String billPaymentSaleSync = c.getString(6);
                String billEwalletStatus = c.getString(7);
                String billEwalletPaymentType = c.getString(8);
                String billEwalletIssueBanker = c.getString(9);
                String billPaymentRemarks = c.getString(10);

                String bidsqls = "SELECT BillNo " +
                        "FROM BillPayment WHERE BillNo = '"+refubdBillNo+"' " +
                        "AND PaymentID = '"+billPaymentPaymentID+"'";
                Log.i("__sql", bidsqls);
                Cursor cdds = DBFunc.Query(bidsqls, false);
                if (cdds != null) {
                    if (cdds.getCount() == 0) {
                        //REFUND
                        String sql = "INSERT INTO BillPayment (BillNo,PaymentID,Name,Amount,PaymentDateTime,QRCode,ChangeAmount,STATUS,SaleSync,PaymentRemarks," +
                                "EwalletStatus,EwalletPaymentType,EwalletIssueBanker) VALUES (";
                        //sql += BillNo + ", ";
                        sql += "'"+refubdBillNo+"'" + ", ";
                        sql += billPaymentPaymentID + ", ";
                        sql += "'" + DBFunc.PurifyString(DBFunc.PurifyString(billPaymentPaymentName)) + "', ";
                        sql += Query.DoubleAmountFormat(Double.valueOf(billPaymentPaymentAmount)) + ", ";
                        sql += System.currentTimeMillis() + ", ";
                        sql += "'" + billPaymentQRCode + "', ";
                        sql += "'" + Query.DoubleAmountFormat(Double.valueOf(billPaymentChangeAmount)) + "', ";
                        sql += "'REFUND',";
                        sql += "'"+billPaymentSaleSync+"',";
                        sql += "'"+billPaymentRemarks+"',";
                        sql += "'"+billEwalletStatus+"',";
                        sql += "'"+billEwalletPaymentType+"',";
                        sql += "'"+billEwalletIssueBanker+"')";
                        Log.i("Sf_SaveBillPayment", sql);
                        DBFunc.ExecQuery(sql, false);

                    }
                    cdds.close();
                }

//                    Query.SaveBillPayment(billPaymentBillNo,billPaymentPaymentID,billPaymentPaymentName,billPaymentPaymentAmount,
//                            billPaymentQRCode,billPaymentChangeAmount);
            }
            c.close();
        }
    }

    public static void updateBillReceipt(String strreceiptdatetime, Integer totalItems, Double subTotal, Double total,
                                         Double paymentAmount, Double change, Integer sales_id) {
        String query = "UPDATE BillReceipt SET ";
        query += "ReceiptNoDateTime = '"+ strreceiptdatetime +"', ";
        query += "TotalItems = '"+ String.valueOf(totalItems)+"', ";
        query += "SubTotal = '"+ String.valueOf(subTotal)+"', ";
        query += "Total = '"+ String.valueOf(total)+"', ";
        query += "PaymentAmount = '"+ String.valueOf(paymentAmount)+"', ";
        query += "Change = '"+ String.valueOf(change)+"', ";
        query += "DateTime = " + System.currentTimeMillis();
        query += " WHERE SalesID = "+sales_id+"";

        DBFunc.ExecQuery(query, false);

    }

//    public static Boolean CheckXReport(String start, String end) {
////        Boolean flag = false;
////        Cursor c = DBFunc.Query("SELECT BillNo FROM Bill where CloseDateTime IS NULL AND strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch') BETWEEN '"+ start +"' AND '"+ end +"'" +
////                "order by BillNo DESC ", false);
////        if (c != null){
////            if (c.moveToNext()){
////                flag =
////            }
////            c.close();
////        }
////        return flag);
//
//    }

    public static Cursor GeneratePaymentSummary(long starttime, long endtime, String report) {
        //String query =  " WHERE (SALES.STATUS = 'REFUND') ";
        String query =  " ";
        String groupdtformat_ = " group by strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch')," +
                ",BillNo,PaymentID ";

        String previousReportCheck_ = "";
        if (report.equals("Report")){
            previousReportCheck_ = " WHERE DateTime BETWEEN "+starttime+ " AND "+endtime+" ";
//            previousReportCheck_ = " AND DateTime BETWEEN "+starttime+ " AND "+endtime+" ";
        }else {
//            previousReportCheck_ = " AND strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch') " +
            previousReportCheck_ = " WHERE strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch') " +
                    "BETWEEN '"+ ReportActivity.start +"' AND '"+ ReportActivity.end +"' ";
        }
//        AND SALES.DateTime BETWEEN 1632326400000 AND 1632412799999" +
////                "group by strftime('%m/%d/%Y', DetailsBillProduct.DateTime / 1000, 'unixepoch') ,DetailsBillProduct.ProductID,DetailsBillProduct.OpenPriceStatus," +
//                "DetailsBillProduct.Remarks  order by DetailsBillProduct.BillNo DESC"
        String sql = "";
        if (report.equals("Report")){
            //String previousReportCheck =  GetPreviousReportSQLCheck(report,starttime,endtime);
            sql = GetDetailsBillPaymentSummary() +
//            sql = GetSalesDataFromSalesTable() +
                    query +
                    previousReportCheck_ ;

//            sql += groupdtformat + " ,BillNo  order by BillNo DESC ";
            sql += groupdtformat_ + " order by " +
                    "BillNo DESC ";



        } else {

            String billnoall = "";
            if (String.valueOf(ReportActivity.previousReport).length() > 4){

                //String previousReportCheck =  GetPreviousReportSQLCheck(report,starttime,endtime);

                billnoall = GetBillNoAll();


                sql = GetDetailsBillPaymentSummary() +
                        query +
                        " AND BillID IN ("+billnoall+") " +
                        previousReportCheck_ ;

                sql += groupdtformat_ +
                        " order by BillNo DESC ";

                ////                " AND  ( Cancel = 'SALES' || Cancel = 'CANCEL' ) " +
//                "group by ProductID,OpenPriceStatus,Remarks";


                return DBFunc.Query(sql, false);

            }else {
                //String str_report_query = GetPreviousReportSQLCheck(report,starttime,endtime);

                SimpleDateFormat sdf = new SimpleDateFormat(Constraints.dateYMD, Locale.US);
                final Calendar myCalendar2 = Calendar.getInstance();

                String sql_dt = "select strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch')" +
                        " from SALES where isZ IS NULL order by DateTime ASC";
                //" from Sales where isZ IS NULL order by DateTime ASC";

                String isnulldatestr = "";
                Cursor sql_dt_c = DBFunc.Query(sql_dt,false);
                if (sql_dt_c != null) {
                    if (sql_dt_c.moveToNext()) {
                        isnulldatestr = sql_dt_c.getString(0);
                        //Log.i("DFs__","sisnulldatestr_____"+isnulldatestr+"_____"+today);
                    }
                    sql_dt_c.close();
                }


                if (ReportActivity.previous_report_shift_name.toUpperCase().equals("NOW")){
//                    previousReportCheck_ += "AND IsZ IS NULL " ;
                    previousReportCheck_ += " WHERE IsZ IS NULL " ;
                    Log.i("sql",sql_dt);
                }
                sql = GetDetailsBillPaymentSummary() +
                        query +
                        previousReportCheck_ +
                        groupdtformat_ ;
                if (!report.equals("Report")){
                    //sql += ",BillNo  " ;
//                    sql += ",BillNo  " ;
//                    sql += " ,DetailsBillProduct.ProductID,DetailsBillProduct.OpenPriceStatus,DetailsBillProduct.Remarks ";
                }

                sql += " order by BillNo DESC ";


            }
        }
        Log.i("Sdfherer","herrrrsqlreceiptnosummary_"+sql);
        return DBFunc.Query(sql, false);
    }

    public static Cursor DetailsBillProductReportReceiptNoSummary(long starttime, long endtime, String report) {
        //String query =  " WHERE (SALES.STATUS = 'REFUND') ";
        String query =  " ";
        String groupdtformat_ = " group by strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch')," +
                " BillNo ";

        String previousReportCheck_ = "";
        if (report.equals("Report")){
            previousReportCheck_ = " WHERE DateTime BETWEEN "+starttime+ " AND "+endtime+" ";
//            previousReportCheck_ = " AND DateTime BETWEEN "+starttime+ " AND "+endtime+" ";
        }else {
//            previousReportCheck_ = " AND strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch') " +
            previousReportCheck_ = " WHERE strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch') " +
                    "BETWEEN '"+ ReportActivity.start +"' AND '"+ ReportActivity.end +"' ";
        }
//        AND SALES.DateTime BETWEEN 1632326400000 AND 1632412799999" +
////                "group by strftime('%m/%d/%Y', DetailsBillProduct.DateTime / 1000, 'unixepoch') ,DetailsBillProduct.ProductID,DetailsBillProduct.OpenPriceStatus," +
//                "DetailsBillProduct.Remarks  order by DetailsBillProduct.BillNo DESC"
        String sql = "";
        if (report.equals("Report")){
            //String previousReportCheck =  GetPreviousReportSQLCheck(report,starttime,endtime);
            sql = GetDetailsBillProductReceiptSummary() +
//            sql = GetSalesDataFromSalesTable() +
                    query +
                    previousReportCheck_ ;

//            sql += groupdtformat + " ,BillNo  order by BillNo DESC ";
            sql += groupdtformat_ + " order by " +
                    "BillNo DESC ";



        } else {

            String billnoall = "";
            if (String.valueOf(ReportActivity.previousReport).length() > 4){

                //String previousReportCheck =  GetPreviousReportSQLCheck(report,starttime,endtime);

                billnoall = GetBillNoAll();


                sql = GetDetailsBillProductReceiptSummary() +
                        query +
                        " AND BillID IN ("+billnoall+") " +
                        previousReportCheck_ ;

                sql += groupdtformat_ +
                        " order by BillNo DESC ";

                ////                " AND  ( Cancel = 'SALES' || Cancel = 'CANCEL' ) " +
//                "group by ProductID,OpenPriceStatus,Remarks";


                return DBFunc.Query(sql, false);

            }else {
                //String str_report_query = GetPreviousReportSQLCheck(report,starttime,endtime);

                SimpleDateFormat sdf = new SimpleDateFormat(Constraints.dateYMD, Locale.US);
                final Calendar myCalendar2 = Calendar.getInstance();

                String sql_dt = "select strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch')" +
                        " from SALES where isZ IS NULL order by DateTime ASC";
                //" from Sales where isZ IS NULL order by DateTime ASC";

                String isnulldatestr = "";
                Cursor sql_dt_c = DBFunc.Query(sql_dt,false);
                if (sql_dt_c != null) {
                    if (sql_dt_c.moveToNext()) {
                        isnulldatestr = sql_dt_c.getString(0);
                        //Log.i("DFs__","sisnulldatestr_____"+isnulldatestr+"_____"+today);
                    }
                    sql_dt_c.close();
                }


                if (ReportActivity.previous_report_shift_name.toUpperCase().equals("NOW")){
//                    previousReportCheck_ += "AND IsZ IS NULL " ;
                    previousReportCheck_ += " WHERE IsZ IS NULL " ;
                    Log.i("sql",sql_dt);
                }
                sql = GetDetailsBillProductReceiptSummary() +
                        query +
                        previousReportCheck_ +
                        groupdtformat_ ;
                if (!report.equals("Report")){
                    //sql += ",BillNo  " ;
//                    sql += ",BillNo  " ;
//                    sql += " ,DetailsBillProduct.ProductID,DetailsBillProduct.OpenPriceStatus,DetailsBillProduct.Remarks ";
                }

                sql += " order by BillNo DESC ";


            }
        }
        Log.i("Sdfherer","herrrrsqlreceiptnosummary_"+sql);
        return DBFunc.Query(sql, false);
    }

    public static Cursor DetailsBillProductReportRefund(long starttime, long endtime, String report) {
        String query =  " WHERE (SALES.STATUS = 'REFUND') ";
        String groupdtformat_ = " group by strftime('"+Constraints.sqldateformat+"', SALES.DateTime / 1000, 'unixepoch') ";

        String previousReportCheck_ = "";
        if (report.equals("Report")){
            previousReportCheck_ = " AND SALES.DateTime BETWEEN "+starttime+ " AND "+endtime+" ";
        }else {
            previousReportCheck_ = " AND strftime('"+Constraints.sqldateformat+"', SALES.DateTime / 1000, 'unixepoch') BETWEEN '"+ ReportActivity.start +"' AND '"+ ReportActivity.end +"' ";
        }
//        AND SALES.DateTime BETWEEN 1632326400000 AND 1632412799999" +
////                "group by strftime('%m/%d/%Y', DetailsBillProduct.DateTime / 1000, 'unixepoch') ,DetailsBillProduct.ProductID,DetailsBillProduct.OpenPriceStatus," +
//                "DetailsBillProduct.Remarks  order by DetailsBillProduct.BillNo DESC"
        String sql = "";
        if (report.equals("Report")){
            //String previousReportCheck =  GetPreviousReportSQLCheck(report,starttime,endtime);
            sql = GetDetailsBillProduct() +
//            sql = GetSalesDataFromSalesTable() +
                    query +
                    previousReportCheck_ ;

//            sql += groupdtformat + " ,BillNo  order by BillNo DESC ";
            sql += groupdtformat_ + " ,DetailsBillProduct.ProductID,DetailsBillProduct.OpenPriceStatus,DetailsBillProduct.Remarks  order by " +
                    "DetailsBillProduct.BillNo DESC ";



        } else {

            String billnoall = "";
            if (String.valueOf(ReportActivity.previousReport).length() > 4){

                //String previousReportCheck =  GetPreviousReportSQLCheck(report,starttime,endtime);

                billnoall = GetBillNoAll();


                sql = GetDetailsBillProduct() +
                        query +
                        " AND DetailsBillProduct.BillID IN ("+billnoall+") " +
                        previousReportCheck_ ;

                sql += groupdtformat_ + " ,DetailsBillProduct.ProductID,DetailsBillProduct.OpenPriceStatus,DetailsBillProduct.Remarks " +
                        " order by DetailsBillProduct.BillNo DESC ";

                ////                " AND  ( Cancel = 'SALES' || Cancel = 'CANCEL' ) " +
//                "group by ProductID,OpenPriceStatus,Remarks";


                return DBFunc.Query(sql, false);

            }else {
                //String str_report_query = GetPreviousReportSQLCheck(report,starttime,endtime);

                SimpleDateFormat sdf = new SimpleDateFormat(Constraints.dateYMD, Locale.US);
                final Calendar myCalendar2 = Calendar.getInstance();

                String sql_dt = "select strftime('"+Constraints.sqldateformat+"', SALES.DateTime / 1000, 'unixepoch')" +
                        " from DetailsBillProduct where DetailsBillProduct.isZ IS NULL order by SALES.DateTime ASC";
                //" from Sales where isZ IS NULL order by DateTime ASC";

                String isnulldatestr = "";
                Cursor sql_dt_c = DBFunc.Query(sql_dt,false);
                if (sql_dt_c != null) {
                    if (sql_dt_c.moveToNext()) {
                        isnulldatestr = sql_dt_c.getString(0);
                        //Log.i("DFs__","sisnulldatestr_____"+isnulldatestr+"_____"+today);
                    }
                    sql_dt_c.close();
                }


                if (ReportActivity.previous_report_shift_name.toUpperCase().equals("NOW")){
                    previousReportCheck_ += "AND DetailsBillProduct.IsZ IS NULL " ;
                    Log.i("sql",sql_dt);
                }
                sql = GetDetailsBillProduct() +
                        query +
                        previousReportCheck_ +
                        groupdtformat_ ;
                if (!report.equals("Report")){
                    //sql += ",BillNo  " ;
//                    sql += ",BillNo  " ;
                    sql += " ,DetailsBillProduct.ProductID,DetailsBillProduct.OpenPriceStatus,DetailsBillProduct.Remarks ";
                }

                sql += " order by DetailsBillProduct.BillNo DESC ";


            }
        }
        Log.i("Sdfherer","herrrrsqlrefund_"+sql);
        return DBFunc.Query(sql, false);
    }

    public static Cursor DetailsBillProductReport(long starttime, long endtime, String report) {
        String query =  " WHERE (SALES.STATUS = 'SALES' OR SALES.STATUS = 'REFUND') ";
        String groupdtformat_ = " group by strftime('"+Constraints.sqldateformat+"', SALES.DateTime / 1000, 'unixepoch') ";

        String previousReportCheck_ = "";
        if (report.equals("Report")){
            previousReportCheck_ = " AND SALES.DateTime BETWEEN "+starttime+ " AND "+endtime+" ";
        }else {
            previousReportCheck_ = " AND strftime('"+Constraints.sqldateformat+"', SALES.DateTime / 1000, 'unixepoch') BETWEEN '"+ ReportActivity.start +"' AND '"+ ReportActivity.end +"' ";
        }
//        AND SALES.DateTime BETWEEN 1632326400000 AND 1632412799999" +
////                "group by strftime('%m/%d/%Y', DetailsBillProduct.DateTime / 1000, 'unixepoch') ,DetailsBillProduct.ProductID,DetailsBillProduct.OpenPriceStatus," +
//                "DetailsBillProduct.Remarks  order by DetailsBillProduct.BillNo DESC"
        String sql = "";
        if (report.equals("Report")){
            //String previousReportCheck =  GetPreviousReportSQLCheck(report,starttime,endtime);
            sql = GetDetailsBillProduct() +
//            sql = GetSalesDataFromSalesTable() +
                    query +
                    previousReportCheck_ ;

//            sql += groupdtformat + " ,BillNo  order by BillNo DESC ";
            sql += groupdtformat_ + " ,DetailsBillProduct.ProductID,DetailsBillProduct.OpenPriceStatus,DetailsBillProduct.Remarks  order by " +
                    "DetailsBillProduct.BillNo DESC ";



        } else {

            String billnoall = "";
            if (String.valueOf(ReportActivity.previousReport).length() > 4){

                //String previousReportCheck =  GetPreviousReportSQLCheck(report,starttime,endtime);

                billnoall = GetBillNoAll();


                sql = GetDetailsBillProduct() +
                        query +
                        " AND DetailsBillProduct.BillID IN ("+billnoall+") " +
                        previousReportCheck_ ;

                sql += groupdtformat_ + " ,DetailsBillProduct.ProductID,DetailsBillProduct.OpenPriceStatus,DetailsBillProduct.Remarks " +
                        " order by DetailsBillProduct.BillNo DESC ";

                ////                " AND  ( Cancel = 'SALES' || Cancel = 'CANCEL' ) " +
//                "group by ProductID,OpenPriceStatus,Remarks";


                return DBFunc.Query(sql, false);

            }else {
                //String str_report_query = GetPreviousReportSQLCheck(report,starttime,endtime);

                SimpleDateFormat sdf = new SimpleDateFormat(Constraints.dateYMD, Locale.US);
                final Calendar myCalendar2 = Calendar.getInstance();

                String sql_dt = "select strftime('"+Constraints.sqldateformat+"', SALES.DateTime / 1000, 'unixepoch')" +
                        " from DetailsBillProduct where DetailsBillProduct.isZ IS NULL order by SALES.DateTime ASC";
                        //" from Sales where isZ IS NULL order by DateTime ASC";

                String isnulldatestr = "";
                Cursor sql_dt_c = DBFunc.Query(sql_dt,false);
                if (sql_dt_c != null) {
                    if (sql_dt_c.moveToNext()) {
                        isnulldatestr = sql_dt_c.getString(0);
                        //Log.i("DFs__","sisnulldatestr_____"+isnulldatestr+"_____"+today);
                    }
                    sql_dt_c.close();
                }


                if (ReportActivity.previous_report_shift_name.toUpperCase().equals("NOW")){
                    previousReportCheck_ += "AND DetailsBillProduct.IsZ IS NULL " ;
                    Log.i("sql",sql_dt);
                }
                sql = GetDetailsBillProduct() +
                        query +
                        previousReportCheck_ +
                        groupdtformat_ ;
                if (!report.equals("Report")){
                    //sql += ",BillNo  " ;
//                    sql += ",BillNo  " ;
                    sql += " ,DetailsBillProduct.ProductID,DetailsBillProduct.OpenPriceStatus,DetailsBillProduct.Remarks ";
                }

                sql += " order by DetailsBillProduct.BillNo DESC ";


            }
        }
        Log.i("Sdfherer","herrrrsql_"+sql);
        return DBFunc.Query(sql, false);
    }

    public static Cursor XZDataReportSales(long starttime, long endtime, String report) {
        String sql = "";
        if (report.equals("Report")){
            String previousReportCheck =  GetPreviousReportSQLCheck(report,starttime,endtime);
            sql = GetSalesDataFromSalesTable() +
                    query +
                    previousReportCheck ;

            sql += groupdtformat + " ,BillNo  order by BillNo DESC ";



        } else {

            String billnoall = "";
            if (String.valueOf(ReportActivity.previousReport).length() > 4){

                String previousReportCheck =  GetPreviousReportSQLCheck(report,starttime,endtime);

                billnoall = GetBillNoAll();

                sql = GetSalesDataFromSalesTable() +
                        query +
                        " AND BillID IN ("+billnoall+") " +
                        previousReportCheck ;

                sql += groupdtformat + " ,BillNo  order by BillNo DESC ";


                return DBFunc.Query(sql, false);

            }else {
                String str_report_query = GetPreviousReportSQLCheck(report,starttime,endtime);

                SimpleDateFormat sdf = new SimpleDateFormat(Constraints.dateYMD, Locale.US);
                final Calendar myCalendar2 = Calendar.getInstance();

                String sql_dt = "select strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch')" +
                        " from Sales where isZ IS NULL order by DateTime ASC";

                String isnulldatestr = "";
                Cursor sql_dt_c = DBFunc.Query(sql_dt,false);
                if (sql_dt_c != null) {
                    if (sql_dt_c.moveToNext()) {
                        isnulldatestr = sql_dt_c.getString(0);
                        //Log.i("DFs__","sisnulldatestr_____"+isnulldatestr+"_____"+today);
                    }
                    sql_dt_c.close();
                }


                if (ReportActivity.previous_report_shift_name.toUpperCase().equals("NOW")){
                    str_report_query += "AND IsZ IS NULL " ;
                    Log.i("sql",sql_dt);
                }
                sql = GetSalesDataFromSalesTable() +
                        query +
                        str_report_query +
                        groupdtformat ;
                if (!report.equals("Report")){
                    sql += ",BillNo  " ;
                }

                sql += " order by BillNo DESC ";


            }
        }
        Log.i("sql____","sqlsql__"+sql);
        return DBFunc.Query(sql, false);
    }

    public static Cursor XZDataReportSalesTax(long starttime, long endtime, String report) {
        String sql = "";
        if (report.equals("Report")){
            String previousReportCheck =  GetPreviousReportSQLCheck(report,starttime,endtime);
            sql = GetSalesDataFromSalesTable() +
                    query +
                    previousReportCheck ;

            sql += groupdtformat + " ,BillNo  order by BillNo DESC ";



        } else {

            String billnoall = "";
            if (String.valueOf(ReportActivity.previousReport).length() > 4){

                String previousReportCheck =  GetPreviousReportSQLCheck(report,starttime,endtime);

                billnoall = GetBillNoAll();

                sql = GetSalesDataFromSalesTable() +
                        query +
                        " AND BillID IN ("+billnoall+") " +
                        previousReportCheck ;

                sql += groupdtformat + " ,BillNo  order by BillNo DESC ";


                return DBFunc.Query(sql, false);

            }else {
                String str_report_query = GetPreviousReportSQLCheck(report,starttime,endtime);

                SimpleDateFormat sdf = new SimpleDateFormat(Constraints.dateYMD, Locale.US);
                final Calendar myCalendar2 = Calendar.getInstance();

                String sql_dt = "select strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch')" +
                        " from Sales where isZ IS NULL order by DateTime ASC";

                String isnulldatestr = "";
                Cursor sql_dt_c = DBFunc.Query(sql_dt,false);
                if (sql_dt_c != null) {
                    if (sql_dt_c.moveToNext()) {
                        isnulldatestr = sql_dt_c.getString(0);
                        //Log.i("DFs__","sisnulldatestr_____"+isnulldatestr+"_____"+today);
                    }
                    sql_dt_c.close();
                }


                if (ReportActivity.previous_report_shift_name.toUpperCase().equals("NOW")){
                    str_report_query += "AND IsZ IS NULL " ;
                    Log.i("sql",sql_dt);
                }
                sql = GetSalesDataFromSalesTable() +
                        query +
                        str_report_query +
                        groupdtformat ;
                if (!report.equals("Report")){
                    sql += ",BillNo  " ;
                }

                sql += " order by BillNo DESC ";


            }
        }

        return DBFunc.Query(sql, false);
    }

    private static String GetDetailsBillProduct() {
//        return "SELECT SUM(TotalQty),SUM(TotalNettSales),SUM(GrossTotal),SUM(TotalBillDisount)," +
//                "SUM(TotalTaxes),SUM(RoundAdj)," +
//                "SUM(ServiceCharges),count(ID),SUM(TotalItemDisount),ReferenceBillNo,BillNo" +
//                " FROM Sales ";

        return "SELECT SUM(DetailsBillProduct.ProductQty),DetailsBillProduct.ProductName," +
                "SUM(DetailsBillProduct.ProductPrice)," +
                "DetailsBillProduct.Remarks,DetailsBillProduct.BillNo," +
                "SUM(DetailsBillProduct.ProductQty*DetailsBillProduct.ItemDiscountAmount)," +
                " DetailsBillProduct.ProductID,DetailsBillProduct.CategoryID,DetailsBillProduct.CategoryName,DetailsBillProduct.TaxID," +
                "DetailsBillProduct.TaxName," +
                "SUM(DetailsBillProduct.TaxAmount),DetailsBillProduct.DiscountName,DetailsBillProduct.DiscountTypeName,DetailsBillProduct.DiscountValue," +
                "DetailsBillProduct.ProductPrice,DetailsBillProduct.ID,DetailsBillProduct.OpenPriceStatus,DetailsBillProduct.TaxType,DetailsBillProduct.ProductQty,SALES.STATUS" +
                " FROM DetailsBillProduct inner join SALES ON DetailsBillProduct.BillNo = SALES.BillNo" ;
//        return "SELECT SUM(DetailsBillProduct.ProductQty),DetailsBillProduct.ProductName," +
//                "DetailsBillProduct.ProductPrice,DetailsBillProduct.Remarks,DetailsBillProduct.BillNo,SUM(DetailsBillProduct.ItemDiscountAmount)," +
//                " DetailsBillProduct.ProductID,DetailsBillProduct.CategoryID,DetailsBillProduct.CategoryName,DetailsBillProduct.TaxID," +
//                "DetailsBillProduct.TaxName," +
//                "SUM(DetailsBillProduct.TaxAmount),DetailsBillProduct.DiscountName,DetailsBillProduct.DiscountTypeName,DetailsBillProduct.DiscountValue," +
//                "DetailsBillProduct.ProductPrice,DetailsBillProduct.ID,DetailsBillProduct.OpenPriceStatus,DetailsBillProduct.TaxType,DetailsBillProduct.ProductQty,SALES.STATUS" +
//                " FROM DetailsBillProduct inner join SALES ON DetailsBillProduct.BillNo = SALES.BillNo" ;
//                "WHERE (SALES.STATUS = 'SALES')  AND SALES.DateTime BETWEEN 1632326400000 AND 1632412799999" +
//                "group by strftime('%m/%d/%Y', DetailsBillProduct.DateTime / 1000, 'unixepoch') ,DetailsBillProduct.ProductID,DetailsBillProduct.OpenPriceStatus," +
//                "DetailsBillProduct.Remarks  order by DetailsBillProduct.BillNo DESC" ;


//        return  "SELECT SUM(ProductQty),ProductName,ProductPrice,Remarks,BillNo," +
//                "SUM(ItemDiscountAmount),ProductID,CategoryID,CategoryName,TaxID,TaxName,SUM(TaxAmount)," +
//                "DiscountName,DiscountTypeName,DiscountValue,ProductPrice,ID,OpenPriceStatus,TaxType,ProductQty  " +
//                "FROM DetailsBillProduct " ;
//        return  "SELECT SUM(ProductQty),ProductName,SUM(ProductPrice),Remarks,BillNo," +
//                "SUM(ItemDiscountAmount),ProductID,CategoryID,CategoryName,TaxID,TaxName,SUM(TaxAmount)," +
//                "DiscountName,DiscountTypeName,DiscountValue,ProductPrice,ID,OpenPriceStatus,TaxType,ProductQty  " +
//                "FROM DetailsBillProduct " ;
//                "Where BillNo = '"+ BillNo +"' " +
////                " AND  ( Cancel = 'SALES' || Cancel = 'CANCEL' ) " +
//                "group by ProductID,OpenPriceStatus,Remarks";
    }
    private static String GetDetailsBillPaymentSummary() {
        return "SELECT strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch')," +
                "BillNo,Name,STATUS,Amount,ChangeAmount" +
                " FROM BillPayment " ;
    }

    private static String GetDetailsBillProductReceiptSummary() {
//        return "SELECT SUM(TotalQty),SUM(TotalNettSales),SUM(GrossTotal),SUM(TotalBillDisount)," +
//                "SUM(TotalTaxes),SUM(RoundAdj)," +
//                "SUM(ServiceCharges),count(ID),SUM(TotalItemDisount),ReferenceBillNo,BillNo" +
//                " FROM Sales ";
//        "Date           ",
//                "       Receipt No",
//                "       STATUS",
//                "       TotalQty",
//                "       Total Nett",
//                "       TotalTax (Exclusive) ",
//                "       TotalTax (Inclusive) ",
//                "       Payment Type ",
//                "       Invoice No ",};
//        return "SELECT strftime('"+Constraints.sqldateformat+"', SALES.DateTime / 1000, 'unixepoch')," +
//                "DetailsBillProduct.BillNo,SALES.STATUS,SUM(DetailsBillProduct.ProductQty)," +
//                "SUM(DetailsBillProduct.ProductPrice),SUM(SALES.TotalTaxes) " +
//                " FROM DetailsBillProduct inner join SALES ON DetailsBillProduct.BillNo = SALES.BillNo " ;
        return "SELECT strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch')," +
                "BillNo,STATUS,SUM(TotalQty)," +
                "SUM(TotalNettSales),SUM(TotalTaxes) " +
                " FROM SALES " ;
    }

    private static String GetSalesDataFromSalesTable() {
        return "SELECT SUM(TotalQty),SUM(TotalNettSales),SUM(GrossTotal),SUM(TotalBillDisount)," +
                "SUM(TotalTaxes),SUM(RoundAdj)," +
                "SUM(ServiceCharges),count(ID),SUM(TotalItemDisount),ReferenceBillNo,BillNo" +
                " FROM Sales ";
    }

    public static String XZDataReportSalesShow(Double totalNettSales, Integer billCancelTotalQty, Integer totalQty,
                                               Double nettSales, Double grossSales, Double taxTotal, Double totalBillDisount,
                                               Double sales, Double billCancel, Integer cancelTotalQty, Double serviceChargesSales, Double roundingAdjSales) {
        String str = "\n" + Constraints.LINE;
        str += "\n" + "*TOTAL SALES*";
        str += "\n"  + Constraints.DOTLINE;
        str += "\n" + "Bill Paid"+ spaceCount("Bill Paid",String.valueOf(totalNettSales))  ;
        str += "\n" + "Bill Cancel"+ spaceCount("Bill Cancel",String.valueOf(billCancelTotalQty));
        str += "\n" + "Qty Sold"+ spaceCount1("Qty Sold",String.valueOf(billCancelTotalQty));;
        str += "\n" + Constraints.AMTNett+ spaceCount(Constraints.AMTNett,String.valueOf(nettSales));
//        str += "\n" + ENUM.AMTGross+ spaceCount(ENUM.AMTGross,String.valueOf(grossSales));
        str += "\n" + Constraints.TaxTotal+ spaceCount(Constraints.TaxTotal,String.valueOf(taxTotal));
        str += "\n" + Constraints.AMTDiscount+ spaceCount(Constraints.AMTDiscount,String.valueOf(totalBillDisount));
        str += "\n" + Constraints.AMTSurcharge+ spaceCount(Constraints.AMTSurcharge,String.valueOf(serviceChargesSales));
        str += "\n" + Constraints.RoundAdj+ spaceCount(Constraints.RoundAdj,String.valueOf(roundingAdjSales));
        str += "\n" + "AMT Collected"+ spaceCount("AMT Collected",String.valueOf(sales));
        str += "\n" + "Qty Cancel"+ spaceCount("Qty Cancel",String.valueOf(billCancel));
        str += "\n" + "AMT Cancel"+ spaceCount("AMT Cancel",String.valueOf(cancelTotalQty));
        return str;
    }

    private static String spaceCount1(String name, String value) {
        String strvalName = "";
        String spe = "";
        Integer count = 0;
        String valuee = "";
        if (value != null){
            count = (name.length()+value.length());
            valuee = value;
        }else{
            count = name.length() + "0".length();
            valuee = "0";
        }
        for (int i = 0; i < 32 - count; i++) {
            spe += " ";
        }
        strvalName +=  spe + valuee;
        return strvalName;
    }

    public static String XZDataReportPaymentShow(Integer paymentTypeCount, String paymentTypeAmount, String PaymentTypeName) {
        String str = "\n" + Constraints.LINE;
        str += "\n" + "*Payment Count*";
        str += "\n" + Constraints.DOTLINE;
        //str += "\n" + "Count"+ spaceCount1("Count",String.valueOf(paymentTypeCount));
        //str += "\n" + "Amount"+ spaceCount("Amount",String.valueOf(paymentTypeAmount));
        //str += "\n" + PaymentTypeName + spaceCount(PaymentTypeName,String.valueOf(paymentTypeAmount));
        //str += "\n" + PaymentTypeName + paymentTypeAmount;
//        str += "\n" + PaymentTypeName+ spaceCount1(PaymentTypeName,String.valueOf(paymentTypeAmount));
        str += "\n" + paymentTypeAmount;
        //str += "\n" + paymentTypeAmount+ spaceCount1(paymentTypeAmount,String.valueOf(paymentTypeAmount));
        return str;
    }
    public static String XZDataReportDiscountShow(String dicountName, String dicountAmount) {
        String str = "\n" + Constraints.LINE;
        str += "\n" + "*Discount / Surcharge*";
        str += "\n" + Constraints.DOTLINE;
//        str += "\n" + dicountName+ spaceCount1(dicountName,String.valueOf(dicountAmount));
        str += "\n" + dicountName;
        //str += "\n" + dicountAmount+ spaceCount1(dicountName,String.valueOf(dicountAmount));
       //str += "\n" + "Amount"+ spaceCount("Amount",String.valueOf(dicountAmount));
//        str += "\n" + dicountAmount;
        return str;
    }

    public static String spaceNameCount(String name){
        String strvalName = "";
        String spe = "";
        for (int i = 0; i < 32 - name.length(); i++) {
            spe += " ";
        }
        strvalName +=  name + spe ;
        return strvalName;
    }
    public static String spaceCount(String name,String value){
        String strvalName = "";
        String spe = "";
        Integer count = 0;
        String valuee = "";
        if (value != null){
            count = (name.length()+("$"+String.format("%.2f", Double.valueOf(value))).length());
            valuee = "$"+ String.format("%.2f", Double.valueOf(value));
        }else{
            count = name.length() + "$0.00".length();
            valuee = "$0.00";
        }
        for (int i = 0; i < 32 - count; i++) {
            spe += " ";
        }
        strvalName +=  spe + valuee;
        return strvalName;
    }

    public static String spaceCount2(String name,String value){
        String strvalName = "";
        String spe = "";
        Integer count = 0;
        String valuee = "";
        if (value != null){
            count = (name.length()+(value).length());
            valuee =  value;
        }else{
            count = name.length() + "$0.00".length();
            valuee = "$0.00";
        }
        for (int i = 0; i < 32 - count; i++) {
            spe += " ";
        }
        strvalName +=  spe + valuee;
        return strvalName;
    }

    public static String XZDataReportCategoryShow(Integer totalQtyCategory, Double totalNettSalesCategory, String taxTotalCategory, Double totalBillDisountTaxCategory,
                                                  Integer totalQtyCategoryCancel, Double totalNettSalesCategory1) {
        String str = "\n" + Constraints.LINE;
        str += "\n" + "*Category*";
        str += "\n"  + Constraints.DOTLINE;
        str += "\n" + "Qty Sold"+ spaceCount1("Qty Sold",String.valueOf(totalQtyCategory));
        str += "\n" + "AMT Nett"+ spaceCount("AMT Nett",String.valueOf(totalNettSalesCategory));
        str += "\n" + "Amount"+ spaceCount("Amount",String.valueOf(totalNettSalesCategory));
        str += "\n" + "Tax"+ spaceCount("Tax",String.valueOf(taxTotalCategory));
        str += "\n" + "Dic Amt"+ spaceCount("Dic Amt",String.valueOf(totalBillDisountTaxCategory));
        str += "\n" + "Surcharge"+ spaceCount("Surcharge","0.0");
        str += "\n" + "Qty Cancel"+ spaceCount("Qty Cancel",String.valueOf(totalQtyCategoryCancel));
        str += "\n" + "AMT Nett Cancel"+ spaceCount("AMT Nett Cancel",String.valueOf(totalNettSalesCategory1));
        return str;
    }

    public static String XZDataReportProductShow(Integer totalQtyProduct, Double totalNettSalesProduct, String taxTotalProduct,
                                                 Double totalBillDisountTaxProduct, Double totalNettSalesProductCancel,
                                                 Double totalNettSalesProduct1,
                                                 Double totalProductServiceCharges) {
        String str = "\n" + Constraints.LINE;
        str += "\n" + "*Product SALES*";
        str += "\n" + Constraints.DOTLINE;
        str += "\n" + "Qty Sold"+ spaceCount1("Qty Sold",String.valueOf(totalQtyProduct));
        str += "\n" + "AMT Nett"+ spaceCount("AMT Nett",String.valueOf(totalNettSalesProduct));
        str += "\n" + "Amount"+ spaceCount("Amount",String.valueOf(totalNettSalesProduct));
        str += "\n" + "Tax"+ spaceCount("Tax",taxTotalProduct);
        str += "\n" + "Dic Amt"+ spaceCount("Dic Amt",String.valueOf(totalBillDisountTaxProduct));
        str += "\n" + "Surcharge"+ spaceCount("Surcharge",String.valueOf(totalProductServiceCharges)) ;
        str += "\n" + "AMT Cancel"+ spaceCount("AMT Cancel",String.valueOf(totalNettSalesProductCancel));
        str += "\n" + "AMT Nett"+ spaceCount("AMT Nett",String.valueOf(totalNettSalesProduct1));
        str += "\n" + "Amount"+ spaceCount("Amount",String.valueOf(totalNettSalesProduct1));
        return str;
    }

    public static String XZDataReportTaxShow(String totalTaxesCount, String totalTaxesAmount) {
        String str = "\n" + Constraints.LINE;
        str += "\n" + "*Tax Sales*";
        str += "\n" + Constraints.DOTLINE;
        //str += "\n" + "Count"+ spaceCount1("Count",totalTaxesCount);
        str += "\n" + "TotalTax"+ spaceCount("TotalTax",totalTaxesAmount);
        return str;
    }

    public static String XZDataReportCancellationShow(Double billCancel, Integer billCancelTotalQty, String billTax, Double totalBillDisountTax) {
        String str = "\n" + Constraints.LINE;
        str += "\n" + "*Cancellation*";
        str += "\n" + Constraints.DOTLINE;
        str += "\n" + "Bill Cancelled"+ spaceCount("Bill Cancelled",String.valueOf(billCancel));
        str += "\n" + "Quantity"+ spaceCount1("Quantity",String.valueOf(billCancelTotalQty));
        str += "\n" + "AMT Nett"+ spaceCount("AMT Nett",String.valueOf(billCancel));;
        str += "\n" + "Amount"+ spaceCount("Amount",String.valueOf(billCancel));
        str += "\n" + "Tax"+ spaceCount("Tax",billTax);
        str += "\n" + "Disc Amt"+ spaceCount("Disc Amt",String.valueOf(totalBillDisountTax));
        str += "\n" + "Surcharge"+ spaceCount("Surcharge","0");
        str += "\n" + "Bill Cancel"+ spaceCount("Bill Cancel",String.valueOf(billCancel));
        str += "\n" + "AMT Nett Cancel"+ spaceCount("AMT Nett Cancel",String.valueOf(billCancel));;
        str += "\n" + "AMT Cancel"+ spaceCount("AMT Cancel",String.valueOf(billCancel));
        str += "\n" + "Tax Cancel"+ spaceCount("Tax Cancel",billTax);
        return str;
    }

    public static String XZDataReportReforeInfoShow() {
        String str = "\n" + Constraints.LINE;
        str += "\n" + "*Refer Info Sales*";
        str += "\n" +"\n" + Constraints.DOTLINE;
        str += "\n" + "Bill Paid";
        str += "\n" + "Qty Sold";
        str += "\n" + "AMT Nett";
        str += "\n" + "Amount";
        str += "\n" + "Tax";
        str += "\n" + "Disc Amt";
        str += "\n" + "Surcharge";
        str += "\n" + "Bill Cancel";
        str += "\n" + "AMT Nett Cancel";
        str += "\n" + "AMT Cancel";
        str += "\n" + "Tax Cancel";
        str += "\n" + "====================================";
        return str;
    }

    public static Cursor XZDataReportPayment(long start, long end, String report) {
        String sql = "";
        if (String.valueOf(ReportActivity.previousReport).length() > 4){
            String billnoall = GetBillNoAll();

            sql = "select BillPayment.Name,sum(BillPayment.Amount)," +
                    "BillPayment.STATUS,BillPayment.isZ,BillPayment.BillNo,BillPayment.EwalletStatus," +
                    "BillPayment.EwalletPaymentType,BillPayment.EwalletIssueBanker,BillPayment.PaymentRemarks from BillPayment " ;
            sql += "left join Sales on Sales.BillNo = BillPayment.BillNo" ;
            sql += " WHERE (Sales.STATUS = 'SALES'   OR Sales.STATUS = 'REFUND') " ;
            sql += " AND Sales.BillID IN ("+billnoall+") " ;
            sql += " and strftime('"+Constraints.sqldateformat+"', BillPayment.PaymentDateTime / 1000, 'unixepoch') " +
                    "BETWEEN '" + ReportActivity.start + "' and '" + ReportActivity.end + "' " +
                    " group by BillPayment.PaymentID,BillPayment.EwalletIssueBanker," +
                    "BillPayment.EwalletStatus,BillPayment.EwalletPaymentType";

        }else {
            sql = "select Name,sum(Amount),STATUS,isZ,BillNo,EwalletStatus,EwalletPaymentType,EwalletIssueBanker,BillPayment.PaymentRemarks " +
                    "from BillPayment " +
                    "WHERE (STATUS = 'SALES'   OR STATUS = 'REFUND')  " ;
            if (ReportActivity.previous_report_shift_name.equals("Now")){
                //sql += "AND isZ != 'Z' ";
                sql += "AND (isZ != 'Z'  OR  isZ IS NULL ) ";
            }
            sql += " and strftime('"+Constraints.sqldateformat+"', PaymentDateTime / 1000, 'unixepoch') " +
                    "BETWEEN '" + ReportActivity.start + "' and '" + ReportActivity.end + "' " +
                    " group by PaymentID,EwalletIssueBanker,EwalletStatus,EwalletPaymentType,BillPayment.PaymentRemarks";
        }
        Log.i("sql___","sql_____"+sql);
        return DBFunc.Query(sql, false);
    }

    public static Cursor XZDataReportPaymentEwallet(long start, long end, String report) {
        String sql = "";
        if (String.valueOf(ReportActivity.previousReport).length() > 4){

            String billnoall = GetBillNoAll();

            sql = "select BillPayment.Name,sum(BillPayment.Amount)," +
                    "BillPayment.STATUS,BillPayment.isZ,BillPayment.BillNo,BillPayment.EwalletPaymentType from BillPayment " ;
            sql += "left join Sales on Sales.BillNo = BillPayment.BillNo" ;
            sql += " WHERE (Sales.STATUS = 'SALES'   OR Sales.STATUS = 'REFUND') " ;
            sql += " AND Sales.BillID IN ("+billnoall+") " ;
            sql += " and strftime('"+Constraints.sqldateformat+"', BillPayment.PaymentDateTime / 1000, 'unixepoch') " +
                    "BETWEEN '" + ReportActivity.start + "' and '" + ReportActivity.end + "' " +
                    "and  (EwalletStatus IS NOT NULL OR EwalletStatus = 1) " +
                    " group by BillPayment.Name";


        }else {
            sql = "select Name,sum(Amount),STATUS,isZ,BillNo,EwalletPaymentType " +
                    "from BillPayment " +
                    "WHERE (STATUS = 'SALES'   OR STATUS = 'REFUND')  " ;
            if (ReportActivity.previous_report_shift_name.equals("Now")){
                sql += "AND isZ != 'Z' ";
            }
            sql += " and strftime('"+Constraints.sqldateformat+"',  PaymentDateTime / 1000, 'unixepoch') " +
                    "BETWEEN '" + ReportActivity.start + "' and '" + ReportActivity.end + "' " +
                    "and  (EwalletStatus IS NOT NULL OR EwalletStatus = 1) " +
                    " group by Name";
//                    " group by Name,BillNo";
        }

        return DBFunc.Query(sql, false);
    }

    private static String GetBillNoAll() {
        String billnoall = "";
        String query = " WHERE (STATUS = 'SALES'   OR STATUS = 'REFUND') ";
        String cpSql = "SELECT ID,AllBillNo,ClosingTime FROM ZClosing " +
                "WHERE  ClosingTime = '"+ReportActivity.previousReport+"' ";
//                    "WHERE strftime('"+Constraints.sqldateformat+"', ClosingTime / 1000, 'unixepoch') = '"+ReportActivity.previousReport+"' ";

        Cursor cpc = DBFunc.Query(cpSql, false);
        if (cpc != null) {
            if (cpc.moveToNext()) {
                billnoall = cpc.getString(1);
            }
            cpc.close();
        }
        return billnoall;
    }

    public static Cursor XZDataReportDiscount(String start, String end, ArrayList<Integer> ClosingPeriodBillIDArr) {
        String sql = "";
        if (String.valueOf(ReportActivity.previousReport).length() > 4){
            String billnoall = GetBillNoAll();

            sql = "SELECT SUM(TotalItemDisount) FROM Sales " +
                    query;
            sql +=  " AND BillID IN ("+billnoall+") " ;
            sql += " and strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch') " +
                    "BETWEEN '" + ReportActivity.start + "' and '" + ReportActivity.end + "' ";
        }else {
            sql = "SELECT SUM(TotalItemDisount) FROM Sales " +
                    query;
            if (ReportActivity.previous_report_shift_name.equals("Now")) {
                sql += "  AND isZ IS NULL ";
            }
            sql += " and strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch') " +
                    " BETWEEN '" + ReportActivity.start + "' and '" + ReportActivity.end + "'";
        }
        Log.i("SDFSDFggg____","_sqlsql__"+sql);
        return DBFunc.Query(sql, false);
    }

    public static Cursor XZDataReportDiscountBill(String start, String end, ArrayList<Integer> ClosingPeriodBillIDArr) {
        String sql = "";
        if (String.valueOf(ReportActivity.previousReport).length() > 4){
            String billnoall = GetBillNoAll();

            sql = "SELECT SUM(TotalBillDisount) FROM Sales " +
                    query;
            sql +=  " AND BillID IN ("+billnoall+") " ;
            sql += " and strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch') " +
                    "BETWEEN '" + ReportActivity.start + "' and '" + ReportActivity.end + "' ";
        }else {
            sql = "SELECT SUM(TotalBillDisount) FROM Sales " +
                    query;
            sql += " and strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch') " +
                    " BETWEEN '" + ReportActivity.start + "' and '" + ReportActivity.end + "'";
            if (ReportActivity.previous_report_shift_name.equals("Now")) {
                sql += "  AND isZ IS NULL ";
            }
        }
        return DBFunc.Query(sql, false);
    }

    public static Cursor XZDataReportCancelDiscount(String start, String end, ArrayList<Integer> ClosingPeriodBillIDArr) {
        String sql = "";
        if (String.valueOf(ReportActivity.previousReport).length() > 4){
            String billnoall = GetBillNoAll();

            sql = "SELECT SUM(TotalItemDisount) FROM Sales " +
                    query_cancel;
            sql +=  " AND BillID IN ("+billnoall+") " ;
            sql += " and strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch') " +
                    "BETWEEN '" + ReportActivity.start + "' and '" + ReportActivity.end + "' ";
        }else {
            sql = "SELECT SUM(TotalItemDisount) FROM Sales " +
                    query_cancel;
            if (ReportActivity.previous_report_shift_name.equals("Now")) {
                sql += "  AND isZ IS NULL ";
            }
            sql += " and strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch') " +
                    " BETWEEN '" + ReportActivity.start + "' and '" + ReportActivity.end + "'";
        }
        return DBFunc.Query(sql, false);
    }

    public static Cursor XZDataReportCancelDiscountBill(String start, String end, ArrayList<Integer> ClosingPeriodBillIDArr) {
        String sql = "";
        if (String.valueOf(ReportActivity.previousReport).length() > 4){
            String billnoall = GetBillNoAll();

            sql = "SELECT SUM(TotalBillDisount) FROM Sales " +
                    query_cancel;
            sql +=  " AND BillID IN ("+billnoall+") " ;
            sql += " and strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch') " +
                    "BETWEEN '" + ReportActivity.start + "' and '" + ReportActivity.end + "' ";
        }else {
            sql = "SELECT SUM(TotalBillDisount) FROM Sales " +
                    query_cancel;
            sql += " and strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch') " +
                    " BETWEEN '" + ReportActivity.start + "' and '" + ReportActivity.end + "'";
            if (ReportActivity.previous_report_shift_name.equals("Now")) {
                sql += "  AND isZ IS NULL ";
            }
        }
        return DBFunc.Query(sql, false);
    }

    public static Cursor XZDataReportTax(String start, String end, ArrayList<Integer> ClosingPeriodBillIDArr) {

        String sql = "SELECT SUM(TotalTaxes) FROM Sales " +  query + "  AND isZ IS NULL";
                //"group by TotalTaxes order by TotalTaxes DESC ";
//                "group by SalesItem.CategoryId order by SalesItem.CategoryId DESC ";

        return DBFunc.Query(sql, false);
    }

    public static Cursor XZDataReportProduct(long starttime, long endtime, String report) {
        String sql = "";
        String str_report_query = "";

        if (report.equals("Report")){
            str_report_query = " AND Sales.DateTime BETWEEN "+starttime + " AND "+endtime+" ";
//            str_report_query = " AND strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch') " +
//                    "BETWEEN '"+ActivityGenReport.selected_from_date+"'"+ " AND '"+ActivityGenReport.selected_to_date+"'";
            sql = " SELECT DetailsBillProduct.ProductID,DetailsBillProduct.ProductName,SUM(Sales.TotalQty), "+
                    "SUM(Sales.TotalNettSales),SUM(Sales.TotalTaxes),SUM(Sales.TotalBillDisount),SUM(Sales.ServiceCharges) "+
                    "FROM Sales "+
                    "INNER JOIN DetailsBillProduct on DetailsBillProduct.BillNo = Sales.BillNo "+
                    "WHERE Sales.STATUS = 'SALES' "+
                    str_report_query +
                    "Group By DetailsBillProduct.ProductID ";
        }else {
            String billnoall = "";
            if (String.valueOf(ReportActivity.previousReport).length() > 4){
                String cpSql = GetBillNoAll();
                sql = "SELECT SUM(TotalQty),SUM(TotalNettSales),SUM(Totalamount),SUM(GrossTotal),SUM(TotalBillDisount)," +
                        "SUM(TotalTaxes),SUM(ServiceCharges)" +
                        "FROM Sales " +
                        query +
                        " AND BillID IN ("+billnoall+") " +
                        " and strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch') BETWEEN '" + ReportActivity.start + "' and '" + ReportActivity.end + "'" +
                        " Group By BillNo";


                return DBFunc.Query(sql, false);

            }else {
                sql = "SELECT SUM(TotalQty),SUM(TotalNettSales),SUM(Totalamount),SUM(GrossTotal),SUM(TotalBillDisount)," +
                        "SUM(TotalTaxes),SUM(ServiceCharges) " +
                        "FROM Sales " +
                        query;

                //String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(Constraints.dateYMD, Locale.US);
                final Calendar myCalendar2 = Calendar.getInstance();
                String todayd = sdf.format(myCalendar2.getTime());

                if (ReportActivity.previous_report_shift_name.equals("Now")){
                    sql += "AND Sales.isZ IS NULL ";
                }
                sql += " and strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch') BETWEEN '" + ReportActivity.start + "' and '" + ReportActivity.end + "'" +
                        " Group By BillNo";
            }
        }
        Log.i("sql______","sql__product___"+sql);
        return DBFunc.Query(sql, false);
//        String sql = "";
//        String str_report_query = "";
//
//        if (report.equals("Report")){
//            str_report_query = " AND Sales.DateTime BETWEEN "+starttime + " AND "+endtime+" ";
////            str_report_query = " AND strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch') " +
////                    "BETWEEN '"+ActivityGenReport.selected_from_date+"'"+ " AND '"+ActivityGenReport.selected_to_date+"'";
//            sql = " SELECT DetailsBillProduct.ProductID,DetailsBillProduct.ProductName,SUM(Sales.TotalQty), "+
//                    "SUM(Sales.TotalNettSales),SUM(Sales.TotalTaxes),SUM(Sales.TotalBillDisount),SUM(Sales.ServiceCharges) "+
//                    "FROM Sales "+
//                    "INNER JOIN DetailsBillProduct on DetailsBillProduct.BillNo = Sales.BillNo "+
//                    "WHERE Sales.STATUS = 'SALES' "+
//                    str_report_query +
//                    "Group By DetailsBillProduct.ProductID ";
//        }else {
//            String billnoall = "";
//            if (String.valueOf(ReportActivity.previousReport).length() > 4){
//                String cpSql = GetBillNoAll();
//                sql = "SELECT SUM(TotalQty),SUM(TotalNettSales),SUM(Totalamount),SUM(GrossTotal),SUM(TotalBillDisount)," +
//                        "SUM(TotalTaxes),SUM(ServiceCharges) " +
//                        "FROM Sales " +
//                        query +
//                        " AND BillID IN ("+billnoall+") " +
//                        " and strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch') BETWEEN '" + ReportActivity.start + "' and '" + ReportActivity.end + "'" +
//                        " Group By BillNo";
//
//
//                return DBFunc.Query(sql, false);
//
//            }else {
//                sql = "SELECT SUM(TotalQty),SUM(TotalNettSales),SUM(Totalamount),SUM(GrossTotal),SUM(TotalBillDisount)," +
//                        "SUM(TotalTaxes),SUM(ServiceCharges) " +
//                        "FROM Sales " +
//                        query;
//
//                //String myFormat = "MM/dd/yy"; //In which you need put here
//                SimpleDateFormat sdf = new SimpleDateFormat(Constraints.dateYMD, Locale.US);
//                final Calendar myCalendar2 = Calendar.getInstance();
//                String todayd = sdf.format(myCalendar2.getTime());
////                if (todayd.equals(ReportActivity.start)) {
//                if (ReportActivity.previous_report_shift_name.equals("Now")){
//                    sql += "AND Sales.isZ IS NULL ";
//                }
//                sql += " and strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch') BETWEEN '" + ReportActivity.start + "' and '" + ReportActivity.end + "'" +
//                        " Group By BillNo";
//            }
//        }
//        Log.i("sql______","sql__product___"+sql);
//        return DBFunc.Query(sql, false);
    }

    public static Cursor XZDataReportCategory(String start, String end,ArrayList<Integer> ClosingPeriodBillIDArr) {
        String sql = "";
        String billnoall = "";
        String checkReferencenobill = "SELECT ReferenceBillNo FROM SALES WHERE ReferenceBillNo IS NOT NULL";
        Cursor ccheckReferencenobill = DBFunc.Query(checkReferencenobill,false);
        String sqlrefbillno = "";
        Integer count = 0;
        String ReferenceBillNo = "";
        if (ccheckReferencenobill != null){
            while (ccheckReferencenobill.moveToNext()){
                ReferenceBillNo += "'"+ccheckReferencenobill.getString(0)+"'";
                if (!ccheckReferencenobill.isLast()) {
                    ReferenceBillNo += ",";
                }

            }
            ccheckReferencenobill.close();
        }
        Log.i("sdfdsf___","ReferenceBillNo_____"+ReferenceBillNo);
        if (String.valueOf(ReportActivity.previousReport).length() > 4) {
            billnoall = GetBillNoAll();
            sql = "select SUM(DetailsBillProduct.ProductQty) , " +
                    "SUM((DetailsBillProduct.ProductPrice - (DetailsBillProduct.ProductQty * DetailsBillProduct.ItemDiscountAmount)))" +
                    ",Sales.STATUS AS SalesStatus," +
                    "Sales.BillNo " +
//                    "case when Sales.STATUS = 'SALES' THEN 'SALES' ELSE 'CANCEL' END AS  SalesStatus " +
                    " from DetailsBillProduct " +
                    " left join Sales on Sales.BillNo = DetailsBillProduct.BillNo " +
                    " where (Sales.STATUS = 'SALES') " +
                    " AND Sales.BillID IN (" + billnoall + ") " +
                    " AND DetailsBillProduct.ProductQty != '0'" +
                    " AND DetailsBillProduct.BillNo NOT IN ( "+ReferenceBillNo+" ) "+
                    " and strftime('" + Constraints.sqldateformat + "', Sales.DateTime / 1000, 'unixepoch') BETWEEN '" + ReportActivity.start + "' and '" +
                    ReportActivity.end + "'";

            sql += " group by DetailsBillProduct.CategoryID";
//            sql += " group by DetailsBillProduct.CategoryID,sales.STATUS";

            Log.i("Dsfdsf____", sql + "__sql");
            // return DBFunc.Query(sql, false);

        } else {
            sql = "select SUM(DetailsBillProduct.ProductQty) , " +
                    "SUM((DetailsBillProduct.ProductPrice - (DetailsBillProduct.ProductQty * DetailsBillProduct.ItemDiscountAmount)))" +
                    ",Sales.STATUS AS SalesStatus," +
                    "Sales.BillNo " +
//                    "case when Sales.STATUS = 'SALES' THEN 'SALES' ELSE 'CANCEL' END AS  SalesStatus " +
                    " from DetailsBillProduct " +
                    " left join Sales on Sales.BillNo = DetailsBillProduct.BillNo " +
                    " where (Sales.STATUS = 'SALES' ) ";

            //String myFormat = "MM/dd/yy"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(Constraints.dateYMD, Locale.US);
            final Calendar myCalendar2 = Calendar.getInstance();

            if (ReportActivity.previous_report_shift_name.toUpperCase().equals("NOW")) {
                sql += "AND Sales.isZ IS NULL ";
            }
            //sql += "AND DetailsBillProduct.CategoryId > 0 " +
            sql +=  " AND DetailsBillProduct.ProductQty != '0'" +
                    " AND DetailsBillProduct.BillNo NOT IN ( "+ReferenceBillNo+" ) "+
                    " and strftime('" + Constraints.sqldateformat + "', Sales.DateTime / 1000, 'unixepoch') " +
                    "BETWEEN '" + ReportActivity.start + "' and '" +
                    ReportActivity.end + "'";
            sql += " group by DetailsBillProduct.CategoryID";
        }
        Log.i("Dsfdsf__fffff__cattt", sql + "__sql");
        return DBFunc.Query(sql, false);
    }

    public static Cursor XZDataReportCancel(String start, String end, ArrayList<Integer> ClosingPeriodBillIDArr) {
        String sqll = "select sum(DetailsBillProduct.ProductQty)," +
//                "SUM((DetailsBillProduct.ProductQty * DetailsBillProduct.ProductPrice) - DetailsBillProduct.ItemDiscountAmount)," +
                "SUM((DetailsBillProduct.ProductQty * DetailsBillProduct.ProductPrice))," +
                "DetailsBillProduct.CANCEL,DetailsBillProduct.CategoryName " +
                "from DetailsBillProduct " +
                "left join Sales on Sales.BillNo = DetailsBillProduct.BillNo "+
                "where (DetailsBillProduct.CANCEL = 'CANCEL' OR DetailsBillProduct.CANCEL = 'VOID')  " +
                //"AND DetailsBillProduct.CategoryId > 0  " +
                " AND DetailsBillProduct.ProductQty != '0' and strftime('"+Constraints.sqldateformat+"', Sales.DateTime / 1000, 'unixepoch')  " +
                "  BETWEEN '" + ReportActivity.start + "' and '" + ReportActivity.end + "'" ;

        if (ReportActivity.previous_report_shift_name.equals("Now")){
            sqll += "AND Sales.IsZ IS NULL " ;
            Log.i("sql",sqll);
        }

        sqll += " group by DetailsBillProduct.CategoryID";
        return DBFunc.Query(sqll, false);
    }


    public static Cursor XZDataReportProductRefund(long starttime, long endtime,String report) {
        String sql = "";
        if (String.valueOf(ReportActivity.previousReport).length() > 4){
            String billnoall = "";
            String cpSql = "SELECT ID,AllBillNo,ClosingTime FROM ZClosing " +
                    "WHERE  ClosingTime = '"+ReportActivity.previousReport+"' ";
//                    "WHERE strftime('%Y-%m-%d', ClosingTime / 1000, 'unixepoch') = '"+ReportActivity.previousReport+"' ";

            Cursor cpc = DBFunc.Query(cpSql, false);
            if (cpc != null) {
                if (cpc.moveToNext()) {
                    billnoall = cpc.getString(1);
                }
                cpc.close();
            }

            Log.i("DSF__","billnoall___"+billnoall);
            //" group by BillPayment.Name";

            sql = "SELECT count(ID),BillNo,SUM(TotalQty),SUM(SubTotal),SUM(Totalamount)," +
                    "PaymentTypeID,SUM(PaymentAmount),strftime('%Y-%m-%d %H:%M:%S', DateTime / 1000, 'unixepoch'),SUM(GrossSales),SUM(TotalItemDisount)," +
                    "SUM(TotalBillDisount),SUM(GrossTotal),SUM(ServiceCharges),SUM(TotalNettSales),SUM(TotalTaxes)," +
                    "SalesDateTime FROM Sales " +
                    " WHERE (STATUS = 'REFUND')  " ;
            sql += " AND BillID IN ("+billnoall+") " ;
            sql += " and strftime('"+Constraints.sqldateformat+"',  DateTime / 1000, 'unixepoch') " +
                    "BETWEEN '" + ReportActivity.start + "' and '" + ReportActivity.end + "' " ;
            sql +=  " group by strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch')";


        }else {
            String str_report_query = "";
            if (report.equals("Report")){
                str_report_query = " AND DateTime BETWEEN "+starttime + " AND "+endtime+" ";
                //            str_report_query = " AND strftime('%Y-%m-%d', DateTime / 1000, 'unixepoch') BETWEEN strftime('%Y-%m-%d', "+starttime+" / 1000, 'unixepoch')"+ " AND strftime('%Y-%m-%d', "+endtime+" / 1000, 'unixepoch')";
                //             str_report_query = " AND strftime('%Y-%m-%d', DateTime / 1000, 'unixepoch') " +
                //                     "BETWEEN '"+ActivityGenReport.selected_from_date+"'"+ " AND '"+ActivityGenReport.selected_to_date+"'";
            }else {
                if (ReportActivity.previous_report_shift_name.equals("Now")) {
                    str_report_query = " AND isZ IS NULL ";
                }
                str_report_query += " and strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch') " +
                        "BETWEEN '"+ReportActivity.start+"' and '"+ReportActivity.end+"'";
            }
            sql = "SELECT count(ID),BillNo,SUM(TotalQty),SUM(SubTotal),SUM(Totalamount)," +
                    "PaymentTypeID,SUM(PaymentAmount),strftime('%Y-%m-%d %H:%M:%S', DateTime / 1000, 'unixepoch'),SUM(GrossSales),SUM(TotalItemDisount)," +
                    "SUM(TotalBillDisount),SUM(GrossTotal),SUM(ServiceCharges),SUM(TotalNettSales),SUM(TotalTaxes)," +
                    "SalesDateTime FROM Sales " +
                    " WHERE (STATUS = 'REFUND')  " +
                    //        " WHERE STATUS != 'SALES'  " +
                    //        " WHERE STATUS = 'CANCEL'  " +
                    str_report_query +
                    " group by strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch')";
        }

        return DBFunc.Query(sql, false);
    }

    public static Cursor XZDataReportProductCancel(long starttime, long endtime,String report) {
        String sql = "";
        if (report.equals("Report")) {
            String previousReportCheck = GetPreviousReportSQLCheck(report, starttime, endtime);

            sql = "SELECT count(ID),BillNo,SUM(TotalQty),SUM(SubTotal),SUM(Totalamount)," +
                    "PaymentTypeID,SUM(PaymentAmount),strftime('%Y-%m-%d %H:%M:%S', DateTime / 1000, 'unixepoch'),SUM(GrossSales),SUM(TotalItemDisount)," +
                    "SUM(TotalBillDisount),SUM(GrossTotal),SUM(ServiceCharges),SUM(TotalNettSales),SUM(TotalTaxes)," +
                    "SalesDateTime FROM Sales " +
                    " WHERE (STATUS = 'CANCEL' OR STATUS = 'VOID')  ";
            sql += previousReportCheck;
            sql += groupdtformat;
        } else {
            if (String.valueOf(ReportActivity.previousReport).length() > 4) {
                String billnoall = GetBillNoAll();

                String previousReportCheck = GetPreviousReportSQLCheck(report, starttime, endtime);

                sql = "SELECT count(ID),BillNo,SUM(TotalQty),SUM(SubTotal),SUM(Totalamount)," +
                        "PaymentTypeID,SUM(PaymentAmount),strftime('%Y-%m-%d %H:%M:%S', DateTime / 1000, 'unixepoch'),SUM(GrossSales),SUM(TotalItemDisount)," +
                        "SUM(TotalBillDisount),SUM(GrossTotal),SUM(ServiceCharges),SUM(TotalNettSales),SUM(TotalTaxes)," +
                        "SalesDateTime FROM Sales " +
                        " WHERE (STATUS = 'CANCEL' OR STATUS = 'VOID')  ";
                sql += " AND BillID IN (" + billnoall + ") ";
                sql += previousReportCheck;
                sql += groupdtformat;
            } else {
                String str_report_query = "";
                if (report.equals("Report")) {
                    str_report_query = " AND DateTime BETWEEN " + starttime + " AND " + endtime + " ";
                } else {
                    if (ReportActivity.previous_report_shift_name.equals("Now")) {
                        str_report_query = " AND isZ IS NULL ";
                    }
                    str_report_query += " and strftime('" + Constraints.sqldateformat + "', DateTime / 1000, 'unixepoch') " +
                            "BETWEEN '" + ReportActivity.start + "' and '" + ReportActivity.end + "'";
                }
                sql = "SELECT count(ID),BillNo,SUM(TotalQty),SUM(SubTotal),SUM(Totalamount)," +
                        "PaymentTypeID,SUM(PaymentAmount),strftime('%Y-%m-%d %H:%M:%S', DateTime / 1000, 'unixepoch'),SUM(GrossSales),SUM(TotalItemDisount)," +
                        "SUM(TotalBillDisount),SUM(GrossTotal),SUM(ServiceCharges),SUM(TotalNettSales),SUM(TotalTaxes)," +
                        "SalesDateTime FROM Sales " +
                        " WHERE (STATUS = 'CANCEL' OR STATUS = 'VOID')  " +
                        str_report_query +
                        groupdtformat;
            }
        }

        return DBFunc.Query(sql, false);
    }

    private static String GetPreviousReportSQLCheck(String report, long starttime, long endtime) {
        String previousReportCheck = "";
        if (report.equals("Report")){
            previousReportCheck = " AND DateTime BETWEEN "+starttime+ " AND "+endtime+" ";
        }else {
            previousReportCheck = " AND strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch') BETWEEN '"+ ReportActivity.start +"' AND '"+ ReportActivity.end +"' ";
        }
        return previousReportCheck;
    }

    public static String GetOnlineOrderBillStatus(String BillNo) {
        String sql = "SELECT OnlineOrderBill FROM Bill WHERE TransNo = '" + BillNo + "'";

        String OnlineOrderBill = "OFF";

        Cursor c = DBFunc.Query(sql, false);
        if (c != null) {
            if (c.moveToNext()) {
                OnlineOrderBill = c.getString(0);
            }
            c.close();
        }
        return OnlineOrderBill;
    }

    public static String ISO_8601_formatted_date1() {
        sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String nowAsISO1 = sdf.format(new Date());
        TimeZone tz = TimeZone.getTimeZone("UTC");
        Date date1 = null;
        try {
            date1 = sdf.parse(nowAsISO1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);

        return String.valueOf(calendar.getTimeInMillis());
    }

    public static String getImageUrl(Context context, ImageView img_choose_folder) {
        String str_img = "0";

        //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            if (SampleActivity.terminal_type.equals("NewLand")){
//
//            }else {
                BitmapDrawable drawable = null;
                Bitmap bitmap = null;

                if (img_choose_folder.getDrawable() == null){
                    str_img = "0";
                }else{
                    drawable = (BitmapDrawable) img_choose_folder.getDrawable();
                    bitmap = drawable.getBitmap();

                    Uri getUrlImage = GetUrlForImageBitMap(context, bitmap);
                    str_img = String.valueOf(getUrlImage);

                    Log.i("DSFDFS___","str_imgtest__"+str_img);
                }
//            }
        //}
        return str_img;
    }

    public static String SearchProductUUID(String productID) {
        String str_uuid = "0";
        String sql = " SELECT UUID " +
                "FROM PLU " +
                "Where ID = "+productID;
        Cursor c = DBFunc.Query(sql, true);
        if (c != null){
            if (c.moveToNext()){
                str_uuid = c.getString(0);
            }
            c.close();
        }
        return str_uuid;
    }

    public static List<ProductData> getProductAll(String searchV) {
        List<ProductData> lstProductData = new ArrayList<ProductData>();
        //String chk_hide_img = Query.GetOptions(20);
            String sql = "SELECT Name,Price,Code,Image,ID,ProductCategoryID," +
                    "ProductCategoryName,UUID,AllowOpenPrice,AllowRemarks FROM PLU ";

            if (searchV != null && searchV.length() > 0) {
                sql += " WHERE Name LIKE '%" + searchV.toUpperCase() + "%'";
                sql += " OR Code = '" + searchV.toUpperCase() + "'";
                sql += " OR Price = '" + searchV.toUpperCase() + "'";
            }
        Log.i("searchValue___","searchValue__"+sql);
            Cursor c = DBFunc.Query(sql, true);
            if (c != null) {
                while (c.moveToNext()) {
                    if (!c.isNull(4)) {
                        lstProductData.add(new ProductData(c.getString(0),c.getString(1),"","",
                                c.getString(3),c.getString(4),c.getString(5),c.getString(6)
                                ,c.getString(7),c.getString(8),c.getString(9)));
                    }
                }
                c.close();
            }
            return lstProductData;
    }

    public static HashMap<String,String> GetProductByID(String strbillNo) {
        HashMap<String,String> hashValues = new HashMap<String, String>();
//    public static void GetProductByID(String strbillNo) {
        //String sql = "Select TotalItems,totalNettAmount FROM BillDetails Where BillNo = '" + MainActivity.strbillNo + "' ";
        String sql = " SELECT SUM(ProductQty),SUM(ProductPrice)," +
                "ProductName,ItemDiscountAmount,ProductID,ProductQty FROM " +
                "DetailsBillProduct Where ProductQty != -1 AND BillNo = '" + strbillNo + "' "+
                " AND Cancel = 'SALES' group by ProductID";

               // " AND Cancel = 'SALES' group by ProductID,OpenPriceStatus";
        Cursor c = DBFunc.Query(sql, false);
        if (c != null) {
            while (c.moveToNext()) {

                if (c.getInt(5) != -1){

                    //if (c.moveToNext()) {
                    if (!c.isNull(0)) {
                        String productID = c.getString(4);
                        String str_uuid = SearchProductUUID(productID);

                        hashValues.put(productID,c.getString(0));
                    }
                }
            }
            c.close();
        }
        return hashValues;
    }

    public static void CheckDateUpdate() {
//        String sql = "SELECT DateTime,SalesDateTime,BillNo,ID FROM Sales WHERE strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch') " +
//                "BETWEEN '2020-12-01' AND '2020-12-20' ";
//        Log.i("DFdfd___sql","SQL___"+sql);
//        Cursor c = DBFunc.Query(sql, false);
//        if (c != null) {
//            while (c.moveToNext()) {
//
//                Integer dt = c.getInt(0);
//                String sdt = c.getString(1);
//                String billno = c.getString(2);
//                String saleID = c.getString(3);
//
//                String saleitemsql = "SELECT ID FROM SalesItem WHERE SalesID = "+saleID+" ";
//                Log.i("DFdfd___sql","saleitemsql___"+saleitemsql);
//                Cursor saleitemc = DBFunc.Query(saleitemsql, false);
//                if (saleitemc != null) {
//                    while (saleitemc.moveToNext()) {
//                        String query = "UPDATE SalesItem SET ";
//                        query += "BillNo = '"+billno+"' ";
//                        query += "WHERE ID = "+saleitemc.getInt(0)+" ";
//
//                        Log.i("DFdfd___sql","query___"+query);
//                        DBFunc.ExecQuery(query, false);
//                    }
//                    saleitemc.close();
//                }
//
////                String billsql = "SELECT CheckDate FROM BillList WHERE BillNo = '"+billno+"' AND CheckDate IS NULL ";
////                Log.i("DFdfd___sql","billsql___"+billsql);
////                Cursor billc = DBFunc.Query(billsql, false);
////                if (billc != null) {
////                    while (billc.moveToNext()) {
////
////
////                        String query = "UPDATE BillList SET ";
////                        query += "CheckDate = '1', ";
////                        query += "Date = '"+sdt+"', ";
////                        query += "DateTime = " + dt + " ";
////                        query += "WHERE BillNo = '"+billno+"' ";
////
////                        Log.i("DFdfd___sql","query___"+query);
////                        DBFunc.ExecQuery(query, false);
////                    }
////                    billc.close();
////                }
//            }
//            c.close();
//        }
////        sql = "SELECT DateTime,BillNo,SalesDate FROM SyncSales WHERE strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch') " +
////                "BETWEEN '2020-12-01' AND '2020-12-20' ";
////        Log.i("DFdfd___sql","SQL___"+sql);
////        c = DBFunc.Query(sql, false);
////        if (c != null) {
////            while (c.moveToNext()) {
////
////                long dttt = c.getLong(0) + 28800;
////
////
////                String dtttt = Query.findfieldNameById("strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch')",
////                        "BillNo", c.getString(1) , "Sales", false);
////                Log.i("Dfdfdf___dtttt","dtttt____"+dtttt);
////
////
////                DateFormat dateFormat56 = new SimpleDateFormat("hh:mm:ss");
////                Date resultdate = new Date(dttt);
////                String b_date = dtttt + " " +dateFormat56.format(resultdate);
////
////
////
////                String query = "UPDATE SyncSales SET ";
////                query += "SalesDate = '"+b_date+"', ";
////                query += "DateTime = " + c.getString(0) + " ";
////                query += "WHERE BillNo = '"+c.getString(1)+"' ";
////
////                Log.i("DFdfd___sql","query___"+query);
////                DBFunc.ExecQuery(query, false);
////
////            }
////            c.close();
////        }

    }

    public static void CancelSales(Context context,String BillNo,Integer sales_id) {
        String query = "UPDATE Sales SET ";
        query += "STATUS = '"+ Constraints.CANCEL +"', ";
        query += "DateTime = " + System.currentTimeMillis();
        query += " WHERE ID = "+sales_id+"";

        DBFunc.ExecQuery(query, false);

        Query.UpdateBillPayment(BillNo);

        String Date = Query.GetDateFormart55();
        Query.UpdateStatusBillList(BillNo, Constraints.CANCEL,Date,System.currentTimeMillis());
    }
    @SuppressLint("ClickableViewAccessibility")
    public static void ShowMapLayout(final Context context, int mapID, final String StrBillNo) {
        //final ProgressDialog progressDialog = new ProgressDialog(context);
//        progressDialog.setMessage(str);
        //progressDialog.show();


        final Dialog dlg_map = new Dialog(context);
        //dlg_map.setCancelable(false);
        //dlg_map.setCanceledOnTouchOutside(false);
        dlg_map.setCancelable(true);
        dlg_map.setCanceledOnTouchOutside(true);
        final MapViewer mapviewer = new MapViewer(context);
        dlg_map.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dlg_map.addContentView(mapviewer, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        dlg_map.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mapviewer.setOnSelectedButtonListener(new MapViewer.OnSelectedButton() {
            @Override
            public void SelectedButton(int buttonPos, MapButton button) {

                try {

                    String str_tbl = " ";
                    if (!(button.getObjExt().equals("0") || button.getObjExt().isEmpty())) {
                        if (button.getObjExt().toUpperCase().equals("CANCEL")){
                            str_tbl = "  BalNo = '0'";
                        }else {
                            str_tbl = "  BalNo = '" + button.getObjExt() + "'";
                        }
                    }

                    String sql = "UPDATE Bill SET " + str_tbl + " WHERE BillNo = '" + StrBillNo + "'";

                    DBFunc.ExecQuery(sql, false);

                    if (button.getObjExt().toUpperCase().equals("CANCEL")){
                        MainActivity.tbl_name = "0";
                    }else {
                        MainActivity.tbl_name = button.getObjExt();
                    }

                    Intent i = new Intent(context,MainActivity.class);
                    i.putExtra("name", "ShowMapLayout");
                    context.startActivity(i);
                    ((Activity)context).finish();

                    MainActivity.St = "1";
                    //progressDialog.dismiss();
                        dlg_map.dismiss();
                }catch (IllegalFormatException e){
                    MainActivity.St = "1";
                }catch (RuntimeException e){
                    MainActivity.St = "1";
                }
            }
//
        });
        dlg_map.show();
        //dlg_map.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dlg_map.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        // }

        mapviewer.ButtonList().clear();
        mapviewer.setBGColor(Color.WHITE);
        mapviewer.setBGImage(null);

        String imgbase64 = null;
        Cursor c = DBFunc.Query("SELECT name,image,bgcolor FROM MapLayout WHERE id = " + mapID, true);
        if (c != null) {
            if (c.getCount() == 0) {
                Query.ErrorDialog(context, StrTextConst.GetText(StrTextConst.TextType.POS, 304));

                c.close();
                dlg_map.dismiss();
                return;
            }

        c.moveToNext();
        String mapname = c.getString(0);//map name, for dialog title, currently not in use!
        Bitmap mapimg = null;

        imgbase64 = c.getString(1);
        if (imgbase64 != null) {
            try {
                byte[] b = Base64.decode(imgbase64, Base64.DEFAULT);
                mapimg = (BitmapFactory.decodeByteArray(b, 0, b.length));
            } catch (IllegalArgumentException e) {
            }
        }

        mapviewer.setBGImage(mapimg);
        mapviewer.setBGColor(c.getInt(2));
        c.close();

        }

        c = DBFunc.Query("SELECT func,name,x,y,width,height,style,bgcolor,fgcolor,fontsize,img FROM MapButtons WHERE map_id = " + mapID + " ORDER BY id ASC", true);
        while (c.moveToNext()) {
            MapButton mapbtn = new MapButton();
            String[] func = c.getString(0).split(":", 3);
            try {
                if (func.length >= 2) {
                    int Type = Integer.parseInt(func[0].trim());
                    int ObjID = Integer.parseInt(func[1].trim());
                    mapbtn.setFuncType(Type);
                    mapbtn.setObjID(ObjID);
                    if (func.length == 3) {
                        if (Type == 0 && ObjID == 19) {
                            if (!func[2].trim().isEmpty()) {
                                mapbtn.setObjExt(func[2].trim());
                            }
                        }
                    }
                }
            } catch (NumberFormatException e) {
            }

            mapbtn.setName(c.getString(1));
            mapbtn.setText(mapbtn.getName());

            float val = c.getFloat(2);
            if (val < 0)
                val = 0;
            mapbtn.setX(val);

            val = c.getFloat(3);
            if (val < 0)
                val = 0;
            mapbtn.setY(val);

            val = c.getFloat(4);
            if (val + mapbtn.getX() > 100) {
                val = 100 - mapbtn.getX();
            }
            mapbtn.setWidth(val);

            val = c.getFloat(5);
            if (val + mapbtn.getY() > 100) {
                val = 100 - mapbtn.getY();
            }
            mapbtn.setHeight(val);

            switch (c.getInt(6)) {
                case 0:
                    mapbtn.setButtonStyle(ButtonStyle.NORMAL);
                    break;
                case 1:
                    mapbtn.setButtonStyle(ButtonStyle.IMAGE_TEXT);
                    break;
                case 2:
                    mapbtn.setButtonStyle(ButtonStyle.IMAGE);
                    break;
                case 3:
                    mapbtn.setButtonStyle(ButtonStyle.IMAGE_TEXT_BORDERLESS);
                    break;
            }
            mapbtn.setBGColor(c.getInt(7));
            mapbtn.setFGColor(c.getInt(8));

            val = c.getFloat(9);
            if (val < 10)
                val = 10;
            if (val > 40)
                val = 40;
            mapbtn.setTextSize(val);

            imgbase64 = c.getString(10);
            try {
                if (imgbase64 != null) {
                    byte[] b = Base64.decode(imgbase64, Base64.DEFAULT);
                    mapbtn.setImage(BitmapFactory.decodeByteArray(b, 0, b.length));
                } else {
                    mapbtn.setImage(null);
                }
            } catch (IllegalArgumentException e) {
                mapbtn.setImage(null);
            }
            mapviewer.ButtonList().add(mapbtn);
        }

        c.close();

        final Thread refreshThread = new Thread() {
            public void run() {
                while (true) {
                    try {
                        for (MapButton btn : mapviewer.ButtonList()) {
                            if (btn.getFuncType() == 0 && btn.getObjID() == 19) {
                                if (btn.getObjExt() != null) {
                                    if (!btn.getObjExt().isEmpty()) {
//                                        String sql = "SELECT OpenDateTime FROM Bill WHERE Cancel = 0 AND " +
//                                                " CloseDateTime IS NULL AND BalNo = '" +
//                                                 btn.getObjExt() + "' ORDER BY BillNo ASC LIMIT 1";
//                                        String sql = "SELECT OpenDateTime FROM Bill " +
//                                                " WHERE CloseDateTime IS NULL " +
//                                                  " ORDER BY BillNo ASC LIMIT 1";
                                        String sql ="SELECT OpenDateTime FROM Bill WHERE (Cancel = 0 or Cancel IS NULL) AND CloseDateTime IS NULL " +
                                                "AND BalNo = '" + DBFunc.PurifyString(btn.getObjExt()) + "' ORDER BY BillNo ASC LIMIT 1";

                                        Cursor c = DBFunc.Query(sql, false);
                                        String billname = "";
                                        if (c != null) {
                                            if (c.moveToNext()) {
                                                // Calendar openTime = Calendar.getInstance().;
                                                // openTime.setTimeInMillis(c.getLong(1));
                                                long curTime = System.currentTimeMillis();
                                                long openTime = c.getLong(0);
                                                long interval = curTime - openTime;
                                                billname += "\n" + String.format("%02d:%02d:%02d", (interval / 1000 / 60 / 60), (interval / 1000 / 60) % 60, (interval / 1000) % 60) + "\n";
                                            }
                                            c.close();
                                        }

                                        btn.setText(btn.getName() + billname);
                                    }
                                }
                            }
                        }
                        mapviewer.postInvalidate();
                        sleep(1000);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }

        };

        refreshThread.start();

        dlg_map.setOnDismissListener(new DialogInterface.OnDismissListener() {

            @Override
            public void onDismiss(DialogInterface dialog) {
                refreshThread.interrupt();

            }

        });

    }
//
//
//    private static class LongOperation extends AsyncTask<Context, MapButton, String>{
//
//        @Override
//        protected void onPostExecute(Void result) {
//            //Toast.makeText(c, "DOne", Toast.LENGTH_SHORT).show();
//        }
//
//        @Override
//        protected String doInBackground(Context... contexts) {
//            Context context = contexts[0];
//            return null;
//        }
//
//        @Override
//        protected void onPreExecute() {}
//
//        public void execute(Context context, MapButton button, String strBillNo) {
//        }
//    }



    public static void CreateNewBill(String status,String transNo) {
        if (transNo.length() > 0) {
            Cursor c = DBFunc.Query("SELECT * FROM Bill WHERE TransNo = '" + transNo + "'", false);
            if (c != null) {
                if (c.getCount() == 0) {
                    Query.SaveBill("","","","",transNo,status);
                }
                c.close();
            }
        }else {
            //Query.SaveBill("","","","",transNo,status);
        }
    }

    public static String GetBalNo(String billNo) {
        String BalNo = "0";
        Cursor c = DBFunc.Query("SELECT BalNo FROM Bill where CloseDateTime IS NULL AND BillNo = '"+billNo+"' order by BillNo DESC ", false);
        //Cursor c = DBFunc.Query("SELECT BalNo FROM Bill where CloseDateTime IS NULL  order by BillNo DESC ", false);

        if (c != null) {
            //while (c.moveToNext()) {
            if (c.moveToNext()) {
                if (!c.isNull(0)) {
                    BalNo = c.getString(0);
                }
            }
            c.close();
        }
        return BalNo;
    }
    public static void saveBillPromo(String billNo,String productID,String itemID,String promoItemID,
                                     String promoTypeID,String promoTypeName,String promoValue){
        DBFunc.ExecQuery("DELETE FROM BillPromo WHERE BillNo = '"+billNo+"'",false);
        String sql = "INSERT INTO BillPromo (BillNo,ProductID,ItemID,PromoItemID," +
                "PromoTypeID,PromoTypeName,PromoValue,DateTime) " +
                "VALUES ('" + DBFunc.PurifyString(billNo) + "'," +
                "'" + DBFunc.PurifyString(productID) + "'," +
                "'" + DBFunc.PurifyString(itemID) + "'," +
                "'" + DBFunc.PurifyString(promoItemID) + "'," +
                "'" + DBFunc.PurifyString(promoTypeID) + "'," +
                "'" + DBFunc.PurifyString(promoTypeName) + "'," +
                "'" + DBFunc.PurifyString(promoValue) + "'," +
                System.currentTimeMillis() + ")";

        DBFunc.ExecQuery(sql, false);
    }

    public static String GetPromotionPriceByBillNo(String billNo,String productID) {

//        "INSERT INTO BillPromo (BillNo,ProductID,ItemID,PromoItemID," +
//                "PromoTypeID,PromoTypeName,PromoValue,DateTime) " +
        String promoPrice = "0";
        String str = "";
        if (!(productID.equals("0")|| productID.isEmpty() || productID != null)){
            str = " , ItemID = '"+productID+"'";
        }
        String sql = "SELECT PromoValue FROM BillPromo Where BillNo = '"+ billNo +"' "+str;

        Cursor c = DBFunc.Query(sql, false);
        if (c != null) {
            if (c.moveToNext()) {
                if (!c.isNull(0)) {
                    promoPrice = c.getString(0);
                }
            }
            c.close();
        }
        return  promoPrice;
    }
    public static ProgressDialog showProgressDialog(Context context,String str){
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(str);
        progressDialog.show();
        return progressDialog;
    }
    public static void SavePromoType(String Promo_Type_ID,String Promo_TypeCode){
        DBFunc.ExecQuery("INSERT INTO Promotype (Promo_TypeID,Promo_TypeCode) " +
                "VALUES ('"+DBFunc.PurifyString(Promo_Type_ID)+"',"+
                "'"+DBFunc.PurifyString(Promo_TypeCode).toLowerCase().trim()+"')", true);
    }

    public static void GetPromotionInformation() {
//        String sql = " SELECT ProductCategoryID,ProductCategoryName " +
//                "FROM PLU Where Name = '" + name + "' ";
//        Log.i("Dfdf_Permisp_",sql);
//        return DBFunc.Query(sql,true);
    }
    public static String validate_space(int start, int val, String valName, String status) {
        String strvalName= "";
        if (valName.length() > val) {
            strvalName= valName.substring(start, val) + " ";
        } else {
            String spe = "";
            for (int i = 0; i < val - valName.length(); i++) {
                spe += " ";
            }
            if (status.equals("Price")){
                strvalName =  spe + valName;
            }else{
                strvalName = valName + spe;
            }
        }
        return  strvalName;
    }
    public static String GetOptionsFromPayment(String optionName,String PaymentID){
        String optionValue = "0";
//        String sql = "SELECT ID, Name, switchSTATUS, disallowEmptyCash, linktoPaymentApp, " +
//                "integratetoShoptima from Payment";
        String sql = "SELECT "+optionName +
                "  from Payment WHERE ID  = "+Integer.parseInt(PaymentID);
        Cursor c = DBFunc.Query(sql, false);
        if (c != null) {
            if (c.moveToNext()) {
                if (!c.isNull(0)) {
                    optionValue = c.getString(0);
                }
            }
            c.close();
        }
        return optionValue;
    }
    public static void VolleyGetToken(final Context context, final int qrValue, final String result) {
        RequestQueue queue = Volley.newRequestQueue(context);
        //String url = "http://" + shoptima_url;
        String url = MainActivity.shoptima_url;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response code
                        String xmlString = response;
                        Document xmlparse = null;
                        Document parse = APIActivity.XMLParseFunction(xmlString, xmlparse);
                        for (int i = 0; i < parse.getElementsByTagName("Status").getLength(); i++) {
                            GetTokenStatusResult = (parse.getElementsByTagName("Status").getLength() > 0)
                                    ? parse.getElementsByTagName("Status").item(i).getTextContent() : " ";
                        }

                        if (GetTokenStatusResult.toUpperCase().equals("SUCCESS")) {
                            for (int i = 0; i < parse.getElementsByTagName("Token").getLength(); i++) {
                                GetTokenResult = (parse.getElementsByTagName("Token").getLength() > 0)
                                        ? parse.getElementsByTagName("Token").item(i).getTextContent() : " ";
                            }
                        }
                        if (!GetTokenResult.equals("")) {
                            if (qrValue == DeclarationConf.QR_REQUEST) {
                                volleyFindShopper(context,GetTokenResult, result, DeclarationConf.QR_REQUEST);
                            } else {
                                volleyFindShopperForAnonymous(context,GetTokenResult, result, DeclarationConf.QR_REQUEST);
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                        // As of f605da3 the following should work
                NetworkResponse response = error.networkResponse;
                if (error instanceof ServerError && response != null) {
                    try {
                        String res = new String(response.data,
                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));

                        // Now you can use any deserializer to make sense of data
//                                JSONObject obj = new JSONObject(res);
                    } catch (UnsupportedEncodingException e1) {
                        // Couldn't properly decode data to string
                        e1.printStackTrace();
                        //Log.i("e1",e1.getMessage());
                    }
                }
            }
        }) {
            @Override
            public Map<String, String> getParams() {
                return null;
            }

            @Override
            public byte[] getBody() {
                String temp = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                        "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
                        "  <soap12:Body>\n" +
                        "    <GetToken xmlns=\"http://tempuri.org/\">\n" +
                        "      <UserID>" + MainActivity.shoptima_user_id + "</UserID>\n" +
                        "      <Password>" + MainActivity.shoptima_password + "</Password>\n" +
                        "    </GetToken>\n" +
                        "  </soap12:Body>\n" +
                        "</soap12:Envelope>";
                //Log.i("APP", "request String: " + temp);
                byte[] b = temp.getBytes(Charset.forName("UTF-8"));

                return b;
            }

            @Override
            public String getBodyContentType() {
                return "text/xml;charset=utf-8";
            }
        };
        queue.add(stringRequest);
    }

    private static void volleyFindShopperForAnonymous(final Context context, final String tokenresult, final String result, final int qrValue) {
        RequestQueue queue = Volley.newRequestQueue(context);
        //String url = "http://" + shoptima_url;
        String url = MainActivity.shoptima_url;
        final String finalResult = result;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // response code
                        String xmlString = response;

                        Document xmlparse = null;
                        Document parse = APIActivity.XMLParseFunction(xmlString, xmlparse);
                        for (int ii = 0; ii < parse.getElementsByTagName("shopper_id").getLength(); ii++) {
                            Shopper_id = (parse.getElementsByTagName("shopper_id").getLength() > 0)
                                    ? parse.getElementsByTagName("shopper_id").item(ii).getTextContent() : " ";
                        }
                        for (int iii = 0; iii < parse.getElementsByTagName("error_message").getLength(); iii++) {

                            anonymousmessage = (parse.getElementsByTagName("error_message").getLength() > 0)
                                    ? parse.getElementsByTagName("error_message").item(iii).getTextContent() : " ";

                        }
//                        if (!Shopper_id.equals("")){
                        volleyCheckAnonymousVoucher(context,GetTokenResult, result);
//                        }else{
//                            DialogBox dlg2 = new DialogBox(ActivityPosCashier.this);
//                            dlg2.setDialogIconType(IconType.INFORMATION);
//                            dlg2.setTitle("EESFA"+anonymousmessage);
//                            dlg2.setMessage(anonymousmessage);
//                            dlg2.setButton1OnClickListener(StrTextConst.GetText(TextType.GENERAL, 0), null);
//                            dlg2.show();
//                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                        // As of f605da3 the following should work
                NetworkResponse response = error.networkResponse;
                if (error instanceof ServerError && response != null) {
                    try {
                        String res = new String(response.data,
                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));

                        //Log.i("res:",res);
                        // Now you can use any deserializer to make sense of data
//                                JSONObject obj = new JSONObject(res);
                    } catch (UnsupportedEncodingException e1) {
                        // Couldn't properly decode data to string
                        e1.printStackTrace();
                        //Log.i("e1",e1.getMessage());
                    }
                }
            }
        }) {
            @Override
            public Map<String, String> getParams() {
                return null;
            }

            @Override
            public byte[] getBody() {
                String encodedURL = null;

                try {
                    encodedURL = URLEncoder.encode(finalResult, "UTF-8");

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                String temp = "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
                        "  <soap12:Body>\n" +
                        "    <FindShopper xmlns=\"http://tempuri.org/\">\n" +
                        "      <Token>" + tokenresult + "</Token>\n" +
                        "      <QRCode>" + encodedURL + "</QRCode>\n" +
//						"      <QRCode>"+""+"</QRCode>\n" +
                        "      <NRIC></NRIC>\n" +
                        "      <MachineID>" + MainActivity.shoptima_machine_id + "</MachineID>\n" +
                        "      <Mallcode>" + MainActivity.shoptima_mall_code + "</Mallcode>\n" +
                        "    </FindShopper>\n" +
                        "  </soap12:Body>\n" +
                        "</soap12:Envelope>";
                //Log.i("APPshp", "request String: " + temp);
                byte[] b = temp.getBytes(Charset.forName("UTF-8"));

                return b;
            }

            @Override
            public String getBodyContentType() {
                return "text/xml;charset=utf-8";
            }
        };
        queue.add(stringRequest);
    }

    private static void volleyFindShopper(final Context context, final String tokenresult, String result, final int qrValue) {
        RequestQueue queue = Volley.newRequestQueue(context);
        //String url = "http://" + shoptima_url;
        String url = MainActivity.shoptima_url;
        final String finalResult = result;
//        final String finalNric = nric;
//		final String finalStr = str;

//		final String finalResult2 = finalResult1;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        // response code
                        String xmlString = response;
                        Document xmlparse = null;
                        Document parse = APIActivity.XMLParseFunction(xmlString, xmlparse);


                        for (int ii = 0; ii < parse.getElementsByTagName("message").getLength(); ii++) {

                            memberewalletsuccess = (parse.getElementsByTagName("message").getLength() > 0)
                                    ? parse.getElementsByTagName("message").item(ii).getTextContent() : " ";

                        }

//						if (qrValue == QR_REQUEST) {
//                        DialogBox dlg2 = new DialogBox(ActivityPosCashier.this);
//                        dlg2.setDialogIconType(IconType.INFORMATION);
//                        dlg2.setTitle(memberewalletsuccess.toUpperCase());
//                        dlg2.setMessage(memberewalletsuccess.toUpperCase());
//                        dlg2.setButton1OnClickListener(StrTextConst.GetText(TextType.GENERAL, 0), null);
//                        dlg2.show();

                        if (memberewalletsuccess.toUpperCase().equals("FAILED")) {
                            DialogBox dlg2 = new DialogBox(context);
                            dlg2.setDialogIconType(DialogBox.IconType.INFORMATION);
                            dlg2.setTitle(memberewalletsuccess.toUpperCase());
                            dlg2.setMessage("Checking Wallet Unsuccess ( " + memberewalletsuccess.toUpperCase() + " )");
                            dlg2.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                            dlg2.show();
                        } else {


                            DialogBox dlg2 = new DialogBox(context);
                            dlg2.setDialogIconType(DialogBox.IconType.INFORMATION);
                            dlg2.setTitle(memberewalletsuccess.toUpperCase());
                            dlg2.setMessage("Checking Wallet Success");
//                            dlg2.setMessage(anynomousvoucheruccess.toUpperCase());
                            dlg2.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                            dlg2.show();

//                            DialogBox dlg2 = new DialogBox(ActivityPosCashier.this);
//                            dlg2.setDialogIconType(IconType.INFORMATION);
//                            dlg2.setTitle(anynomousvoucheruccess.toUpperCase());
//                            dlg2.setMessage(anynomousvoucherermsg);
//                            dlg2.setButton1OnClickListener(StrTextConst.GetText(TextType.GENERAL, 0), null);
//                            dlg2.show();

                            //UpdateItemList();
                        }

//						}else{
//							DialogBox dlg2 = new DialogBox(ActivityPosCashier.this);
//							dlg2.setDialogIconType(IconType.INFORMATION);
//							dlg2.setTitle("FAILED");
//							dlg2.setMessage("FAILED");
//							dlg2.setButton1OnClickListener(StrTextConst.GetText(TextType.GENERAL, 0), null);
//							dlg2.show();
//
//						}

//						if (finalResult2.equals("wallet")) {
//							for (int ii = 0; ii < parse.getElementsByTagName("shopper_id").getLength(); ii++) {
//
//								Shopper_id = (parse.getElementsByTagName("shopper_id").getLength() > 0)
//										? parse.getElementsByTagName("shopper_id").item(ii).getTextContent() : " ";
//
//							}
//						}

//						if (qrValue == QR_VOUCHER_REQUEST) {
                        for (int ii = 0; ii < parse.getElementsByTagName("shopper_id").getLength(); ii++) {

                            Shopper_id = (parse.getElementsByTagName("shopper_id").getLength() > 0)
                                    ? parse.getElementsByTagName("shopper_id").item(ii).getTextContent() : " ";

                        }
                        voucherLen = parse.getElementsByTagName("Voucher").getLength();
                        voucherArrList.clear();
                        for (int i = 0; i < parse.getElementsByTagName("Voucher").getLength(); i++) {
                            FindShopperResult = (parse.getElementsByTagName("Voucher").getLength() > 0)
                                    ? parse.getElementsByTagName("Voucher").item(i).getTextContent() : " ";
                            voucher_value = (parse.getElementsByTagName("voucher_value").getLength() > 0)
                                    ? parse.getElementsByTagName("voucher_value").item(i).getTextContent() : " ";
                            voucher_no = (parse.getElementsByTagName("voucher_no").getLength() > 0)
                                    ? parse.getElementsByTagName("voucher_no").item(i).getTextContent() : " ";
                            voucher_status = (parse.getElementsByTagName("voucher_status").getLength() > 0)
                                    ? parse.getElementsByTagName("voucher_status").item(i).getTextContent() : " ";
                            voucher_record_id = (parse.getElementsByTagName("voucher_record_id").getLength() > 0)
                                    ? parse.getElementsByTagName("voucher_record_id").item(i).getTextContent() : " ";
                            voucher_expiry_date = (parse.getElementsByTagName("voucher_expiry_date").getLength() > 0)
                                    ? parse.getElementsByTagName("voucher_expiry_date").item(i).getTextContent() : " ";
                            Voucher v = new Voucher();
                            v.setVoucher_no(voucher_no);
                            v.setVoucher_value(voucher_value);
                            v.setVoucher_record_id(voucher_record_id);
                            v.setVoucher_expiry_value(voucher_expiry_date);
                            v.setVoucher_status(voucher_status);
                            voucherArrList.add(v);
                        }
                        tokresult = tokenresult;
                        qrresult = finalResult;
                        qrval = qrValue;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                        // As of f605da3 the following should work
                NetworkResponse response = error.networkResponse;
                if (error instanceof ServerError && response != null) {
                    try {
                        String res = new String(response.data,
                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));


                        // Now you can use any deserializer to make sense of data
//                                JSONObject obj = new JSONObject(res);
                    } catch (UnsupportedEncodingException e1) {
                        // Couldn't properly decode data to string
                        e1.printStackTrace();
                        //Log.i("e1",e1.getMessage());
                    }
                }
            }
        }) {
            @Override
            public Map<String, String> getParams() {
                return null;
            }

            @Override
            public byte[] getBody() {
                String temp = "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
                        "  <soap12:Body>\n" +
                        "    <FindShopper xmlns=\"http://tempuri.org/\">\n" +
                        "      <Token>" + tokenresult + "</Token>\n" +
                        "      <QRCode>" + finalResult + "</QRCode>\n" +
//						"      <QRCode>"+""+"</QRCode>\n" +
                        "      <NRIC></NRIC>\n" +
                        "      <MachineID>" + MainActivity.shoptima_machine_id + "</MachineID>\n" +
                        "      <Mallcode>" + MainActivity.shoptima_mall_code + "</Mallcode>\n" +
                        "    </FindShopper>\n" +
                        "  </soap12:Body>\n" +
                        "</soap12:Envelope>";
                //Log.i("APPshp", "request String: " + temp);
                byte[] b = temp.getBytes(Charset.forName("UTF-8"));

                return b;
            }

            @Override
            public String getBodyContentType() {
                return "text/xml;charset=utf-8";
            }
        };
        queue.add(stringRequest);
    }

//    private void VoucherFunction(ArrayList<Voucher> v, final String tokenresult, final String result, final int qrval, String Shopper_id, String machineid, String mallcode, String voucher_record_id) {
//
//
//        //				//create dialog
//        final Dialog dialog = new Dialog(ActivityPosCashier.this);
//        //set layout custom
//        dialog.setContentView(R.layout.activity_voucher_list);
//        final RecyclerView rvcaddy = (RecyclerView) dialog.findViewById(R.id.rvcaddy);
//
////		ArrayList<Voucher> data = new ArrayList<Voucher>();
////		data.add(v);
//        VoucherAdapter adapter = new VoucherAdapter(v, this, R.layout.activity_voucher_list, tokenresult, result, qrval, Shopper_id, machineid, mallcode, voucher_record_id);
//        // Add some item here to show the list.
//        rvcaddy.setAdapter(adapter);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//        rvcaddy.setLayoutManager(mLayoutManager);
//
//        Button dialogButton = (Button) dialog.findViewById(R.id.btn_use);
//        Button dialogButtonCancel = (Button) dialog.findViewById(R.id.btn_cancel);
//        dialogButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//                if (qrval == DeclarationConf.QR_REQUEST) {
//                    totalvoucherval = VoucherAdapter.totvoucherval;
//                    UpdateItemList();
//                    etnr(static_option, static_paymentID, static_payname);
//                } else {
//                    totalvoucherval = VoucherAdapter.totvoucherval;
//                    UpdateItemList();
//                    etnr(static_option, static_paymentID, static_payname);
//                }
//            }
//        });
//        dialogButtonCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//        dialog.setTitle("Voucher List");
//        dialog.show();
//    }
//
    private static void volleyCheckAnonymousVoucher(final Context context, final String tokenresult, final String result) {
        RequestQueue queue = Volley.newRequestQueue(context);
        //String url = "http://" + shoptima_url;
        String url = MainActivity.shoptima_url;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        // response code
                        String xmlString = response;

                        Document xmlparse = null;
                        Document parse = APIActivity.XMLParseFunction(xmlString, xmlparse);

                        for (int ii = 0; ii < parse.getElementsByTagName("shopper_id").getLength(); ii++) {
                            Shopper_id = (parse.getElementsByTagName("shopper_id").getLength() > 0)
                                    ? parse.getElementsByTagName("shopper_id").item(ii).getTextContent() : " ";
                        }
                        for (int ii = 0; ii < parse.getElementsByTagName("message").getLength(); ii++) {
                            anynomousvoucheruccess = (parse.getElementsByTagName("message").getLength() > 0)
                                    ? parse.getElementsByTagName("message").item(ii).getTextContent() : " ";
                        }
                        for (int ii = 0; ii < parse.getElementsByTagName("error_message").getLength(); ii++) {
                            anynomousvoucherermsg = (parse.getElementsByTagName("error_message").getLength() > 0)
                                    ? parse.getElementsByTagName("error_message").item(ii).getTextContent() : " ";
                        }
                        recArr.clear();
                        for (int i = 0; i < parse.getElementsByTagName("Voucher").getLength(); i++) {
                            anynomousvoucher = (parse.getElementsByTagName("voucher_value").getLength() > 0)
                                    ? parse.getElementsByTagName("voucher_value").item(i).getTextContent() : " ";
                            recArr.add(result);
                            recVoycherArr.add(anynomousvoucher);
                            totalvoucherval += Double.valueOf(anynomousvoucher);
                        }
                        if (recArr.size() < 1) {
                            DialogBox dlg2 = new DialogBox(context);
                            dlg2.setDialogIconType(DialogBox.IconType.INFORMATION);
                            dlg2.setTitle(anynomousvoucheruccess.toUpperCase());
                            dlg2.setMessage("Checking Voucher Unsuccess ( " + anynomousvoucherermsg.toUpperCase() + " )");
                            dlg2.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                            dlg2.show();
                        } else {
                            DialogBox dlg2 = new DialogBox(context);
                            dlg2.setDialogIconType(DialogBox.IconType.INFORMATION);
                            dlg2.setTitle(anynomousvoucheruccess.toUpperCase());
                            dlg2.setMessage("Checking Voucher Success");
//                            dlg2.setMessage(anynomousvoucheruccess.toUpperCase());
                            dlg2.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                            dlg2.show();
                            //UpdateItemList();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                        // As of f605da3 the following should work
                NetworkResponse response = error.networkResponse;
                if (error instanceof ServerError && response != null) {
                    try {
                        String res = new String(response.data,
                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                    } catch (UnsupportedEncodingException e1) {
                        // Couldn't properly decode data to string
                        e1.printStackTrace();
                        //Log.i("e1",e1.getMessage());
                    }
                }
            }
        }) {
            @Override
            public Map<String, String> getParams() {
                return null;
            }

            @Override
            public byte[] getBody() {
                String encodedURL = null;

                try {
                    encodedURL = URLEncoder.encode(result, "UTF-8");

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                String temp = "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
                        "  <soap12:Body>\n" +
                        "    <CheckAnonymousVoucher xmlns=\"http://tempuri.org/\">\n" +
                        "      <Token>" + tokenresult + "</Token>\n" +
                        "      <machine_id>" + MainActivity.shoptima_machine_id + "</machine_id>\n" +
                        "      <mall_code>" + MainActivity.shoptima_mall_code + "</mall_code>\n" +
                        "      <voucher_qr>" + encodedURL + "</voucher_qr>\n" +
                        "    </CheckAnonymousVoucher>\n" +
                        "  </soap12:Body>\n" +
                        "</soap12:Envelope>";

                byte[] b = temp.getBytes(Charset.forName("UTF-8"));

                return b;
            }

            @Override
            public String getBodyContentType() {
                return "text/xml;charset=utf-8";
            }
        };
        queue.add(stringRequest);
    }
//
//    private void volleyUseAnonymousVoucher(final String tokenresult, final String result, final String paymenttype, List<String> recArr, List<String> recVoycherArr) {
//        String str = "";
//        int anonymousVoucherVal = 0;
//        for (int i = 0; i < recArr.size(); i++) {
//            //str += "        <string>"+result+"</string>\n" ;
//            str += "        <Voucher_qr>" + recArr.get(i) + "</Voucher_qr>\n";
//        }
//        for (int ii = 0; ii < recVoycherArr.size(); ii++) {
//            //str += "        <string>"+result+"</string>\n" ;
//            anonymousVoucherVal += Integer.parseInt(recVoycherArr.get(ii));
//        }
//
//        RequestQueue queue = Volley.newRequestQueue(this);
//        //String url = "http://" + shoptima_url;
//        String url = shoptima_url;
//        final String finalStr = str;
//        final int finalAnonymousVoucherVal = anonymousVoucherVal;
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
//                new Response.Listener<String>() {
//
//                    @Override
//                    public void onResponse(String response) {
//                        // response code
//                        String xmlString = response;
//                        Log.i("successusevoucher", xmlString);
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
////                        // As of f605da3 the following should work
//                NetworkResponse response = error.networkResponse;
//                if (error instanceof ServerError && response != null) {
//                    try {
//                        String res = new String(response.data,
//                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));
//                    } catch (UnsupportedEncodingException e1) {
//                        // Couldn't properly decode data to string
//                        e1.printStackTrace();
//                        //Log.i("e1",e1.getMessage());
//                    }
//                }
//            }
//        }) {
//            @Override
//            public Map<String, String> getParams() {
//                return null;
//            }
//
//            @Override
//            public byte[] getBody() {
////				String temp = "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
////						"  <soap12:Body>\n" +
////						"    <UseAnonymousVouchers xmlns=\"http://tempuri.org/\">\n" +
////						"      <Token>"+tokenresult+"</Token>\n" +
////						"      <Payment_value>"+ finalAnonymousVoucherVal +"</Payment_value>\n" +
////						"      <Vouchers>\n" +
////								finalStr +
////						"      </Vouchers>\n" +
////						"      <Shopper_id>"+"2"+"</Shopper_id>\n" +
////						"      <Machine_id>"+shoptima_machine_id+"</Machine_id>\n" +
////						"      <Mall_cod>"+shoptima_mall_code+"</Mall_cod>\n" +
////						"    </UseAnonymousVouchers>\n" +
////						"  </soap12:Body>\n" +
////						"</soap12:Envelope>";
//                String temp = "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
//                        "  <soap:Body>\n" +
//                        "    <UseAnonymousVouchers xmlns=\"http://tempuri.org/\">\n" +
//                        "      <Token>" + tokenresult + "</Token>\n" +
//                        "      <payment_value>" + finalAnonymousVoucherVal + "</payment_value>\n" +
//                        "      <vouchers>\n" +
//                        finalStr +
//                        "      </vouchers>\n" +
//                        "      <shopper_id>" + Shopper_id + "</shopper_id>\n" +
//                        "      <machine_id>" + shoptima_machine_id + "</machine_id>\n" +
//                        "      <mall_code>" + shoptima_mall_code + "</mall_code>\n" +
//                        "    </UseAnonymousVouchers>\n" +
//                        "  </soap:Body>\n" +
//                        "</soap:Envelope>";
//                Log.i("APPTesting", "request String: " + temp);
//                byte[] b = temp.getBytes(Charset.forName("UTF-8"));
//
//                return b;
//            }
//
//            @Override
//            public String getBodyContentType() {
//                return "text/xml;charset=utf-8";
//            }
//        };
//        queue.add(stringRequest);
//    }
//
//    private void volleyCheckWalletVoucher(final String tokenresult) {
//        RequestQueue queue = Volley.newRequestQueue(this);
//        //String url = "http://" + shoptima_url;
//        String url = shoptima_url;
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
//                new Response.Listener<String>() {
//
//                    @Override
//                    public void onResponse(String response) {
//                        // response code
//                        String xmlString = response;
//
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
////                        // As of f605da3 the following should work
//                NetworkResponse response = error.networkResponse;
//                if (error instanceof ServerError && response != null) {
//                    try {
//                        String res = new String(response.data,
//                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));
//                    } catch (UnsupportedEncodingException e1) {
//                        // Couldn't properly decode data to string
//                        e1.printStackTrace();
//                        //Log.i("e1",e1.getMessage());
//                    }
//                }
//            }
//        }) {
//            @Override
//            public Map<String, String> getParams() {
//                return null;
//            }
//
//            @Override
//            public byte[] getBody() {
//                String temp = "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
//                        "  <soap12:Body>\n" +
//                        "    <CheckWalletVoucher xmlns=\"http://tempuri.org/\">\n" +
//                        "      <Token>" + tokenresult + "</Token>\n" +
//                        "      <shopper_id>" + Shopper_id + "</shopper_id>\n" +
//                        "      <machine_id></machine_id>\n" +
//                        "      <mall_code>string</mall_code>\n" +
//                        "      <voucher_record_id>string</voucher_record_id>\n" +
//                        "    </CheckWalletVoucher>\n" +
//                        "  </soap12:Body>\n" +
//                        "</soap12:Envelope>";
//                //Log.i("APP", "request String: " + temp);
//                byte[] b = temp.getBytes(Charset.forName("UTF-8"));
//
//                return b;
//            }
//
//            @Override
//            public String getBodyContentType() {
//                return "text/xml;charset=utf-8";
//            }
//        };
//        queue.add(stringRequest);
//    }
//
//    private void volleyUseWalletVoucher(final String tokenresult, final String paymenttype, List<String> ewalletrecArr, List<String> ewalletVoucherValArr) {
//        Log.i("VRid", String.valueOf(ewalletrecArr));
//        String str_Voucher_record_id = "";
//        int str_Voucher_value = 0;
//        for (int i = 0; i < ewalletrecArr.size(); i++) {
//            //str_Voucher_record_id += "        <Voucher_record_id>"+ewalletrecArr.get(i)+"</Voucher_record_id>\n" ;
//            str_Voucher_record_id += "        <string>" + ewalletrecArr.get(i) + "</string>\n";
//        }
//
//        for (int ii = 0; ii < ewalletVoucherValArr.size(); ii++) {
//            //str_Voucher_record_id += "        <Voucher_record_id>"+ewalletrecArr.get(i)+"</Voucher_record_id>\n" ;
//            str_Voucher_value += Integer.parseInt(ewalletVoucherValArr.get(ii));
//        }
//
//        Log.i("str_Voucher_valuee", String.valueOf(str_Voucher_value));
//
//        RequestQueue queue = Volley.newRequestQueue(this);
//        //String url = "http://" + shoptima_url;
//        String url = shoptima_url;
//        final String finalStr = str_Voucher_record_id;
//        final String finalStr_Voucher_record_id = str_Voucher_record_id;
//        final String finalStr_Voucher_record_id1 = str_Voucher_record_id;
//        final int finalStr_Voucher_value = str_Voucher_value;
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
//                new Response.Listener<String>() {
//
//                    @Override
//                    public void onResponse(String response) {
//                        // response code
//                        String xmlString = response;
//                        Log.i("ressdspownse", response);
//                        ActivityPosCashier.ewalletrecArr.clear();
//                        ActivityPosCashier.ewalletVoucherValArr.clear();
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
////                        // As of f605da3 the following should work
//                NetworkResponse response = error.networkResponse;
//                if (error instanceof ServerError && response != null) {
//                    try {
//                        String res = new String(response.data,
//                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));
//                    } catch (UnsupportedEncodingException e1) {
//                        // Couldn't properly decode data to string
//                        e1.printStackTrace();
//                        //Log.i("e1",e1.getMessage());
//                    }
//                }
//            }
//        }) {
//            @Override
//            public Map<String, String> getParams() {
//                return null;
//            }
//
//            @Override
//            public byte[] getBody() {
////				String temp = "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
////						"  <soap12:Body>\n" +
////						"    <UseWalletEvouchers xmlns=\"http://tempuri.org/\">\n" +
////						"      <Token>"+tokenresult+"</Token>\n" +
////						"      <payment_value>"+paymenttype+"</payment_value>\n" +
////						"      <wallet_vouchers>\n" +
////								finalStr_Voucher_record_id +
////						"      </wallet_vouchers>\n" +
////						"      <shopper_id>"+Shopper_id+"</shopper_id>\n" +
////						"      <machine_id>"+shoptima_machine_id+"</machine_id>\n" +
////						"      <mall_code>"+shoptima_mall_code+"</mall_code>\n" +
////						"    </UseWalletEvouchers>\n" +
////						"  </soap12:Body>\n" +
////						"</soap12:Envelope>";
//                String temp = "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
//                        "  <soap12:Body>\n" +
//                        "    <UseWalletEvouchers xmlns=\"http://tempuri.org/\">\n" +
//                        "      <Token>" + tokenresult + "</Token>\n" +
//                        "      <payment_value>" + finalStr_Voucher_value + "</payment_value>\n" +
//                        "      <wallet_vouchers>\n" +
//                        finalStr_Voucher_record_id1 +
//                        "      </wallet_vouchers>\n" +
//                        "      <shopper_id>" + Shopper_id + "</shopper_id>\n" +
//                        "      <machine_id>" + shoptima_machine_id + "</machine_id>\n" +
//                        "      <mall_code>" + shoptima_mall_code + "</mall_code>\n" +
//                        "    </UseWalletEvouchers>\n" +
//                        "  </soap12:Body>\n" +
//                        "</soap12:Envelope>";
//                Log.i("APPsdsdsds1", "drequest String: " + temp);
//                byte[] b = temp.getBytes(Charset.forName("UTF-8"));
//
//                return b;
//            }
//
//            @Override
//            public String getBodyContentType() {
//                return "text/xml;charset=utf-8";
//            }
//        };
//        queue.add(stringRequest);
//    }

//    private void loadBreadModuleList(Context context,final String encoded) {
//        final String JSON_URL = "https://bakery-demo.viscovery.com/demokit_bread_api/DemokitBreadModule";
//        RequestQueue requestQueue = Volley.newRequestQueue(context);
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, JSON_URL,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        //Toast.makeText(ActivityPosCashier.this,response,Toast.LENGTH_LONG).show();
//                        try {
//                            JSONObject jsonObj = new JSONObject(response);
//                            String jsonObjData = jsonObj.getString("data");
//                            JSONArray jsonArr = new JSONArray(jsonObjData);
//                            for (int i = 0; i < jsonArr.length(); i++) {
//                                JSONObject jsonArrObj = jsonArr.getJSONObject(i);
//                                String jsonArrProductID = jsonArrObj.getString("product_id"); //plu's code
//
//                                String product_name = "";
//                                double product_price = 0.0;
//                                String product_code = "";
//                                Cursor c = DBFunc.Query("SELECT Name,Price,Option,Code,Condi_Seq FROM PLU WHERE Code = \"" + jsonArrProductID + "\"", true);
//                                if (c == null) {// no item found
////                                    DialogBox dlg1 = new DialogBox(CurrentActivity);
////                                    dlg1.setTitle(StrTextConst.GetText(TextType.GENERAL, 18));
////                                    dlg1.setDialogIconType(IconType.ERROR);
////                                    dlg1.setMessage("Please assign BarCode!");
////                                    dlg1.setButton1OnClickListener(StrTextConst.GetText(TextType.GENERAL, 0), null);
////                                    dlg1.show();
//                                    return;
//                                }
//                                if (!c.moveToNext()) {// no item found
//                                    return;
//                                }
//
//                                product_name = c.getString(0);
//                                product_price = c.getDouble(1);
//                                product_code = c.getString(3);
//
//
//                                c.close();
//
//                                int PLUID = FindPLUCode(jsonArrProductID);
//                                int deptID = 0;
//                                String deptName = "";
//                                c = DBFunc.Query("SELECT DeptID FROM PLU WHERE ID = " + PLUID, true);
//                                if (c != null) {
//                                    if (c.moveToNext()) {
//                                        if (!c.isNull(0)) {
//                                            deptID = c.getInt(0);
//                                        }
//                                    }
//                                    c.close();
//                                }
//
//                                if (deptID != -1) {
//                                    //c = DBFunc.Query("SELECT Name FROM Dept WHERE ID = " + deptID, true);
//                                    c = DBFunc.Query(Query.select_query_dept + deptID, true);
//                                    if (c == null) {
//                                        deptID = -1;
//                                    } else {
//                                        if (c.moveToNext()) {
//                                            deptName = c.getString(0);
//                                        } else {
//                                            deptID = -1;
//                                        }
//                                        c.close();
//                                    }
//                                }
//
//                                String sql = "INSERT INTO BillPLU (BillNo,PLUID,Name,Amount,Qty,DateTime,DeptID,DeptName,PLUCode) VALUES (";
//                                Log.i("SQL_test_4",sql);
//                                sql += BillID + ", ";
//                                sql += PLUID + ", ";
//                                sql += "'" + DBFunc.PurifyString(product_name) + "', ";
//                                sql += product_price + ", ";
//                                sql += 1 + ", ";
//
//                                sql += System.currentTimeMillis() + ", ";
//                                sql += deptID + ", ";
//                                sql += "'" + DBFunc.PurifyString(deptName) + "', ";
//
//                                sql += "'" + DBFunc.PurifyString(jsonArrProductID) + "')";
//
//
//                                DBFunc.ExecQuery(sql, false);
//
//                                UpdateItemList();
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
////						{
////							"code":"0001",
////								"data":[
////							{"bounding_box":{"h":0.31941837072372437,
////									"w":0.4411758482456207,
////									"x":0.20887407660484314,
////									"y":0.3347308039665222},
////
////								"name":"Almond Milk Bread",
////
////									"price":"1.23",
////									"product_id":"2151",
////									"score":"0.9961762428283691",
////
////									"thumbnail":"/static/thumbnails/demokit_bread/2151.jpg",
////									"unique_id":"2151"
////							},
////
////							{
////								"bounding_box":{
////								"h":0.2826899439096451,
////										"w":0.33693574368953705,
////										"x":0.004469648003578186,
////										"y":0.194680318236351
////							},
////								"name":"Bun",
////									"price":"1.05",
////									"product_id":"2152",
////									"score":"0.9999454021453857",
////									"thumbnail":"/static/thumbnails/demokit_bread/2152.jpg",
////									"unique_id":"2152"
////							},
////							{"bounding_box":
////								{"h":0.3811715543270111,
////										"w":0.4596669673919678,
////										"x":0.5403330326080322,
////										"y":0.4372307360172272
////								},
////								"name":"Croissant",
////									"price":"1.97",
////									"product_id":"2150",
////									"score":"0.9980931878089905",
////									"thumbnail":"/static/thumbnails/demokit_bread/2150.jpg",
////									"unique_id":"2150"},
////							{"bounding_box":
////								{"h":0.28488659858703613,
////										"w":0.667922705411911,
////										"x":0.001860290765762329,
////										"y":0.6375641226768494
////								},
////								"name":"Sesame Butter Bread",
////									"price":"1.75",
////									"product_id":"2153",
////									"score":"0.9738147854804993",
////									"thumbnail":"/static/thumbnails/demokit_bread/2153.jpg",
////									"unique_id":"2153"},
////							{"bounding_box":
////								{"h":0.3478495180606842,
////										"w":0.7618629634380341,
////										"x":0.23813703656196594,
////										"y":0.05072030425071716
////								},
////								"name":"Almond Batard","price":"1.94","product_id":"2154","score":"0.9993119239807129",
////									"thumbnail":"/static/thumbnails/demokit_bread/2154.jpg","unique_id":"2154"}],
////							"message":"Success","pass_by":null}
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(ActivityPosCashier.this, error.toString(), Toast.LENGTH_LONG).show();
//                    }
//                }) {
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("image", encoded);
//                return params;
//            }
//
//        };
//        requestQueue.add(stringRequest);
//    }

    public static String getStrInfoForQRCodeOnReceipt(String receipt_no, String paymentType, double _val_amount, double amount){
        String strinfo = "";
        String chk_qr_code_on_receipt = Query.GetOptions(18);
        if (chk_qr_code_on_receipt.equals("1")) {

            Double Amt = 0.0;
            String Mall_ID = MainActivity.qr_code_shoptima_mall_code;
            String Machine_ID = MainActivity.qr_code_shoptima_machine_id;
            String Receipt_No = receipt_no;

            String GTO_Amount = String.valueOf(_val_amount);
            String Payment_Type = "0";
            String brand = MainActivity.qr_code_shoptima_brand;
            String outlet = MainActivity.qr_code_shoptima_outlet;
            String GTO2 = "";
            String Payment_Type_2 = "";
            String GTO3 = "";
            String Payment_Type_3 = "";
            String GST = "";
            String Discount = "";
            String strQRCodeDate = "";
            String strQRCodeTime = "";
            String PAX = "";
            String Txn_Type = "";
            String Brand = "";
            String Outlet = "";
            String Stall = "";
            String Setting = "";
            String Item_List = "";
            String Category = "";
            if (paymentType.toUpperCase().equals("CASH")){
                Payment_Type = "1";
                Amt = _val_amount;
            }else if (paymentType.toUpperCase().equals("VISA")){
                Payment_Type = "2";
                Amt = amount;
            }else if (paymentType.toUpperCase().equals("MASTER")){
                Payment_Type = "3";
                Amt = amount;
            }else if (paymentType.toUpperCase().equals("AMEX")){
                Payment_Type = "4";
                Amt = amount;
            }else if (paymentType.toUpperCase().equals("NETS")){
                Payment_Type = "5";
                Amt = amount;
            }else if (paymentType.toUpperCase().equals("EASYLINK")){
                Payment_Type = "6";
                Amt = amount;
            }else if (paymentType.toUpperCase().equals("EVOUCHER") || paymentType.toUpperCase().equals("EWALLET")){
                Payment_Type = "7";
                Amt = amount;
            }else if (paymentType.toUpperCase().equals("ANONYMOUS VOUCHER")){
                Payment_Type = "8";
                Amt = amount;
            }else if (paymentType.toUpperCase().equals("OTHER VOUCHER")){
                Payment_Type = "9";
                Amt = amount;
            }else if (paymentType.toUpperCase().equals("OTHER PAYMENT TYPES")){
                Payment_Type = "10";
                Amt = amount;
            }else{
                Payment_Type = "0";
                Amt = amount;
            }
//        String strinfo = "-m "+Mall_ID+"-p "+Machine_ID+
//                "- r "+Receipt_No+"–g1 "+GTO_Amount+
//                "–p1 "+Payment_Type+"–g2 "+GTO2+"–p2 "+
//                Payment_Type_2+ "–g3  "+GTO3+"–p3 "+Payment_Type_3+
//                "-g "+GST+"–ds "+Discount+"–d "+strQRCodeDate+"-t "+strQRCodeTime+"-x "+PAX+"-y "+Txn_Type+
//                "-b "+Brand+"-o "+Outlet+"-s "+Stall+"-e "+Setting
//                +"–i "+Item_List+"c "+Category;
            String datePattern = "dd-MMM-yyyy";
            String datePattern1 = "DD-MMM-yyyy";
            Date today;
            String dateOutput;
            String dateOutput1;
            SimpleDateFormat simpleDateFormat;
            simpleDateFormat = new SimpleDateFormat(datePattern);
            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(datePattern1);
            today = new Date();
            dateOutput = simpleDateFormat.format(today);
            dateOutput1 = simpleDateFormat1.format(today);

            //saleDate = String.valueOf(new java.sql.Timestamp(utilDate.getTime()));
            //dateOutput = simpleDateFormat.format(saleDate);
            //Log.d("saleDate","saleDate_"+saleDate);
            //Log.d("dateOutput","dateOutput_"+dateOutput);

            SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss");
            String time = sdf2.format(today);

            //String strinfo = "-m "+Mall_ID+" -p "+Machine_ID+" -r "+Receipt_No+" -g1 "+GTO_Amount+" -p1 "+Payment_Type+" -d 16-Dec-2019 -t 15:34:02 -y 1 -b Keisuke -o Parkway";
//        String strinfo = "-m "+Mall_ID+" -p "+Machine_ID+" -r "+Receipt_No+" -g1 "+GTO_Amount+" -p1 "+
//                Payment_Type+" -d "+dateOutput+" -t "+time+" -y 1 -b "+brand+" -o "+outlet;
            //String strinfo = "-m "+Mall_ID+" -p "+Machine_ID+" -r "+Receipt_No+" -g1 "+GTO_Amount+" -p1 "+Payment_Type+" -d 16-Dec-2019 -t 15:34:02 -y 1 -b Keisuke -o Parkway";
//        String strinfo = "-m \""+Mall_ID+"\" -p \""+Machine_ID+"\" -r \""+Receipt_No+"\" -g1 "+GTO_Amount+" -p1 "+
//                Payment_Type+" -d \""+dateOutput+"\" -t \""+time+"\" -y 1 -b \""+brand+"\" -o \""+outlet+"\"";
            strinfo = "-m \""+Mall_ID+"\" -p \""+Machine_ID+"\" -r \""+Receipt_No+"\" -g1 "+Amt+" -p1 "+
                    Payment_Type+" -d \""+dateOutput+"\" -t \""+time+"\" -y 1 -b \""+brand+"\" -o \""+outlet+"\"";
            Log.d("strinfo","strinfo_"+strinfo);
        }
        return strinfo;

//        Cursor config_values_pro_item = null;
//        config_values_pro_item = DBFunc.Query("SELECT qrcode_shoptima_url,qrcode_shoptima_user_id,qrcode_qrcode_shoptima_password,qrcode_shoptima_machine_id,qrcode_shoptima_mall_code FROM POSSys", true);
//        if (config_values_pro_item != null) {
//            while (config_values_pro_item.moveToNext()) {
//                Mall_ID = config_values_pro_item.getString(0);
//                Machine_ID = config_values_pro_item.getString(1);
//                //url = config_values_pro_item.getString(2);
//            }
//        }
    }

    public static String GetQRCodeFromBillPayment(String BillNo) {
        String qr_str_info = "";
        String sql = "SELECT QRCode FROM BillPayment WHERE BillNo = '" + BillNo + "'";
        Cursor c = DBFunc.Query(sql, false);
        if (c != null) {
            if (c.moveToNext()) {
                qr_str_info = c.getString(0);
            }
            c.close();
        }
        return qr_str_info;
    }
    public static String GetStatus(String BillNo) {
        String status_str_info = "";
        String sql = "SELECT STATUS FROM Sales WHERE BillNo = '" + BillNo + "'";

        Cursor c = DBFunc.Query(sql, false);
        if (c != null) {
            if (c.moveToNext()) {
                status_str_info = c.getString(0);
            }
            c.close();
        }
        return status_str_info;
    }

//    public static int SeqTax(){
//        Integer tax_Seq = 0;
//        Cursor plutax = DBFunc.Query("SELECT ID,Name,Acronym,Rate,Type,Seq FROM Tax ", true);
//        String tax_acronym = "";
//        if (plutax != null) {
//            tax_Seq = 0;
//            while (plutax.moveToNext()) {
//                tax_Seq = plutax.getInt(4);
//            }
//        }
//        return tax_Seq;
//    }
    public static void XReportClosingPeriod(){

        //long closetime = System.currentTimeMillis();
//
//        DBFunc.ExecQuery("INSERT INTO ClosingPeriod(BillStart,BillEnd,ClosingTime,PrintedCount) VALUES(" + closetime + ",1)", false);

//        SELECT BillStart,* from ClosingPeriod Order By ClosingTime DESC last id => BillStart
//
//        BillStart + 1 , bill closing not null last bill no => Billend  (1 - 12)
//
//
//        13 - 26

        String sql = "SELECT BillEnd FROM ClosingPeriod Order by ID DESC";
        Cursor Cursor_ClosingPeriod = DBFunc.Query(sql,false);
        Integer BillStartNo = 0;
        if (Cursor_ClosingPeriod != null){
            if (Cursor_ClosingPeriod.moveToNext()){
                BillStartNo = Cursor_ClosingPeriod.getInt(0) + 1;
                SaveClosingPeriod(BillStartNo);
            }else {
                SaveClosingPeriod(1);
            }
            Cursor_ClosingPeriod.close();
        }else {
           SaveClosingPeriod(1);
        }
    }

    private static void SaveClosingPeriod(Integer billStartNo) {
        String sql = "select BillNo from Bill where CloseDatetime is not null order by BillNo desc";
        Cursor Cursor_Bill = DBFunc.Query(sql,false);
        Integer BillLastNo = 0;
        if (Cursor_Bill != null){
            if (Cursor_Bill.moveToNext()){
                BillLastNo = Cursor_Bill.getInt(0);
                sql = "INSERT INTO ClosingPeriod(BillStart,BillEnd,ClosingTime,PrintedCount) " +
                        " VALUES( " + billStartNo+ " ," + BillLastNo + " , " + System.currentTimeMillis() + ",1)";

                DBFunc.ExecQuery(sql, false);

            }
            Cursor_Bill.close();
        }
    }

    public static void BillPLUTax(Integer bill_plu_id, Integer tax_id, String taxName, String acronym, Double rate, Integer type,
                                  Integer seq, Double total_tax){

        try {

            DBFunc.ExecQuery("DELETE FROM BillPLUTax WHERE BillPLU_idx = "+bill_plu_id,false);

            String sql = "INSERT INTO BillPLUTax (BillPLU_idx,TaxID,Name,Acronym,Rate,Type,Amount,Seq) VALUES (";
            sql += bill_plu_id + ",";
            sql += tax_id + ",";
            sql += "'" + DBFunc.PurifyString(taxName) + "',";
            sql += "'" + DBFunc.PurifyString(acronym) + "',";
            sql += rate + ",";
            sql += type + ",";
            sql += String.format("%.2f", Double.valueOf(total_tax)) + ",";
            sql += seq + ")";
            DBFunc.ExecQuery(sql, false);
        }catch (Exception e){

        }
    }
    public static int findLatestID(String colname,String tablename,Boolean flag) {
        int latestID = 0;
        Cursor c = DBFunc.Query("SELECT MAX("+colname+") FROM "+tablename, flag);
        if (c != null) {
            if (c.moveToNext()) {
                if (!c.isNull(0)) {
                    latestID = c.getInt(0);
                }
            }
            c.close();
        }
        return latestID;
    }

    public static String findfieldNameById(String fieldName,String IdName, String Id,String tbName, Boolean flag) {
        String fName = "";
        String sql = "SELECT "+fieldName+" FROM "+tbName+ " WHERE "+IdName +"= '" +Id +"'";
        Log.i("query___","sql__"+sql);
        Cursor c = DBFunc.Query(sql, flag);
        if (c != null) {
            if (c.moveToNext()) {
                if (!c.isNull(0)) {
                    fName = c.getString(0);
                }
            }
            c.close();
        }
        return fName;
    }

    public static void DeleteForUpdate(String strbillNo,String productName,String productID,String EditOpenPrice) {
        DBFunc.ExecQuery("DELETE FROM BillDetails WHERE BillNo = '"+ strbillNo + "' AND ProductName = '"+productName+"'", false);
        //DBFunc.ExecQuery("DELETE FROM BillDetails WHERE BillNo = '"+ MainActivity.strbillNo + "'", false);
        String query_sql_ = "";
        if (EditOpenPrice != null && EditOpenPrice.length() > 1) {
            query_sql_ = " AND OpenPriceStatus = '" + EditOpenPrice + "'";
        }
        String sql_details_product = "DELETE FROM DetailsBillProduct WHERE BillNo = '" + strbillNo + "' " +
                "AND ProductID = '" + productID + "' " + query_sql_;
        DBFunc.ExecQuery(sql_details_product, false);

        String sql = "SELECT idx FROM BillPLU WHERE BillNo = '"+ strbillNo + "' AND PLUID = '"+productID+"'";
        Cursor Cursor_BillPLUTax = DBFunc.Query(sql,false);
        if (Cursor_BillPLUTax != null){
            if (Cursor_BillPLUTax.moveToNext()){
                DBFunc.ExecQuery("DELETE FROM BillPLUTax WHERE BillPLU_idx = "+ Cursor_BillPLUTax.getInt(0) , false);
            }
            Cursor_BillPLUTax.close();
        }

        DBFunc.ExecQuery("DELETE FROM BillPLU WHERE BillNo = '"+ strbillNo + "' AND PLUID = '"+productID+"'", false);

    }

    public static Cursor calculateTaxAmountAllForPLU(String billNo) {
//        String sql = "SELECT BillPLUTax.Seq,BillPLUTax.Name,BillPLUTax.Amount " +
//                "  FROM BillPLUTax  " +
//                "  inner join BillPLU on BillPLU.idx = BillPLUTax.BillPLU_idx " +
//                "  where BillPLU.BillNo = '"+billNo+"' " ;
        String sql = "select BillPLUTax.Seq,BillPLUTax.Name,BillPLUTax.Amount,DetailsBillProduct.ProductPrice,DetailsBillProduct.ItemDiscountAmount from Details_BillProduct"
        +" inner join BillPLU on BillPLU.BillNo = DetailsBillProduct.BillNo"
        +" inner join BillPLUTax on BillPLUTax.idx = BillPLUTax.BillPLU_idx"
        +" where DetailsBillProduct.BillNo = '"+billNo+"'"
        +" group By DetailsBillProduct.ProductID";

        return DBFunc.Query(sql,false);
    }

    public static void SaveBillPLU(int billID, String strbillNo, String pluID, String pluName, String pluPrice, int qty, String str_Cancel,String modifier) {
//        String sql = "INSERT INTO BillPLU (BillID,BillNo,PLUID,Name,Amount,Qty,DateTime,DeptID,DeptName,PLUCode) VALUES (";
        String sql = "INSERT INTO BillPLU (BillID,BillNo,PLUID,Name,Amount,Qty,DateTime,DeptID,DeptName,PLUCode,Cancel,Modifiers) VALUES (";
        sql += billID + ", ";
        sql += "'" + strbillNo + "', ";
        sql += Integer.parseInt(pluID) + ", ";
        sql += "'" + DBFunc.PurifyString(pluName) + "', ";
        sql += "'" + String.valueOf(pluPrice) + "', ";
        sql += qty + ", ";
        sql += System.currentTimeMillis() + ", ";
        sql += 0 + ", ";
        sql += "'" + DBFunc.PurifyString("") + "', ";
        sql += "'" + DBFunc.PurifyString("") + "',";
        sql += "'" + DBFunc.PurifyString(str_Cancel) + "',";
        sql += "'" + modifier + "')";

        DBFunc.ExecQuery(sql, false);

    }

    public static String ItemStatus(String Itemstatus) {
        String str_Cancel = "";
        if (Itemstatus.equals(Constraints.SALES)){
            str_Cancel = Constraints.SALES;
        }else if (Itemstatus.equals(Constraints.CANCEL)){
            str_Cancel = Constraints.CANCEL;
        }else{
            str_Cancel = Constraints.VOID;
        }
        return str_Cancel;
    }

    public static String GetModifier(ArrayList<Integer> modifier_ID) {
        String listString = "";
        for (int j = 0 ; j < modifier_ID.size() ; j ++){
            listString += modifier_ID.get(j) + ":";
        }
        return listString;
    }

    //public static String SubmitSales(String finalCompany_code, JSONObject jsonObject) {
    public static String SubmitSales(String finalCompany_code, JSONArray jsonArr, JSONObject jsonObj) {
        String temp = "0";
        String chk_hide_img = Query.GetOptions(22);
        if (chk_hide_img.equals("1")){
            temp = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                    "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                    "  <soap:Body>\n" +
                    "    <submitSales xmlns=\"http://tempuri.org/\">\n" +
                    "      <companyCode>"+ finalCompany_code +"</companyCode>\n" +
                    "      <json>"+jsonArr+"</json>\n" +
                    "    </submitSales>\n" +
                    "  </soap:Body>\n" +
                    "</soap:Envelope>";
        }else {
            temp = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                    "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                    "  <soap:Body>\n" +
                    "    <submitAdroindSales xmlns=\"http://tempuri.org/\">\n" +
                    "      <companyCode>" + finalCompany_code + "</companyCode>\n" +
                    "      <json>" + jsonObj + "</json>\n" +
                    "    </submitAdroindSales>\n" +
                    "  </soap:Body>\n" +
                    "</soap:Envelope>";
        }
        //Log.i("temp___","temp___"+temp);
        return temp;
    }
    public static String SubmitStockTransfer(String finalCompany_code, JSONArray jsonArr) {
        String temp = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "  <soap:Body>\n" +
                "    <submitStockTransfer xmlns=\"http://tempuri.org/\">\n" +
                "      <companyCode>"+finalCompany_code+"</companyCode>\n" +
                "      <json>"+jsonArr+"</json>\n" +
                "    </submitStockTransfer>\n" +
                "  </soap:Body>\n" +
                "</soap:Envelope>";

        Log.i("DFDF___","DFTTT"+temp);
        return temp;
    }
    public static String SubmitStockAdjust(String finalCompany_code, JSONArray jsonArr) {
        String temp = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "  <soap:Body>\n" +
                "    <submitStockAdjust xmlns=\"http://tempuri.org/\">\n" +
                "      <companyCode>"+finalCompany_code+"</companyCode>\n" +
                "      <json>"+jsonArr+"</json>\n" +
                "    </submitStockAdjust>\n" +
                "  </soap:Body>\n" +
                "</soap:Envelope>";


        return temp;
    }
    public static String SubmitStockTake(String finalCompany_code, JSONArray jsonArr) {
        String temp = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "  <soap:Body>\n" +
                "    <submitStockTake xmlns=\"http://tempuri.org/\">\n" +
                "      <companyCode>"+finalCompany_code+"</companyCode>\n" +
                "      <json>"+jsonArr+"</json>\n" +
                "    </submitStockTake>\n" +
                "  </soap:Body>\n" +
                "</soap:Envelope>";


        return temp;
    }
    public static String CreateDiscount(String finalCompany_code, JSONArray jsonObject) {
        String temp = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
                "  <soap12:Body>\n" +
                "    <CreateDiscount xmlns=\"http://tempuri.org/\">\n" +
                "      <companyCode>"+ finalCompany_code +"</companyCode>\n" +
                "      <json>"+jsonObject+"</json>\n" +
                "    </CreateDiscount>\n" +
                "  </soap12:Body>\n" +
                "</soap12:Envelope>";
        return temp;
    }
    public static String GetUsersList(String finalCompany_code) {
        String temp = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "  <soap:Body>\n" +
                "    <GetUsersList xmlns=\"http://tempuri.org/\">\n" +
                "      <companyCode>"+finalCompany_code+"</companyCode>\n" +
                "    </GetUsersList>\n" +
                "  </soap:Body>\n" +
                "</soap:Envelope>";
        return temp;
    }
    public static String GetDiscount(String finalCompany_code) {
        String temp = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
                "  <soap12:Body>\n" +
                "    <GetDiscountList xmlns=\"http://tempuri.org/\">\n" +
                "      <companyCode>"+ finalCompany_code +"</companyCode>\n" +
                "    </GetDiscountList>\n" +
                "  </soap12:Body>\n" +
                "</soap12:Envelope>";
        return temp;
    }
    public static String GetUsersPermission(String finalCompany_code,String sl_id,String userid) {
        String temp = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "  <soap:Body>\n" +
                "    <GetUsersPermission xmlns=\"http://tempuri.org/\">\n" +
                "      <companyCode>"+finalCompany_code+"</companyCode>\n" +
                "      <sl_id>string</sl_id>\n" +
                "      <userid>string</userid>\n" +
                "    </GetUsersPermission>\n" +
                "  </soap:Body>\n" +
                "</soap:Envelope>";
        return temp;
    }
    public static String ImageUpload(String finalCompany_code,String base64ImgStr,String imgFileName,
                                     String imgType,String isDepartment,String isItem,
                                     String itemDepartment,String itemID,String imgeUrl) {
        String temp = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "  <soap:Body>\n" +
                "    <ImageUpload xmlns=\"http://tempuri.org/\">\n" +
                "      <companyCode>"+finalCompany_code+"</companyCode>\n" +
                "      <Base64ImgStr>"+base64ImgStr+"</Base64ImgStr>\n" +
                "      <ImgFileName>"+imgFileName+"</ImgFileName>\n" +
                "      <ImgType>"+imgType+"</ImgType>\n" +
                "      <isDepartment>"+isDepartment+"</isDepartment>\n" +
                "      <isItem>"+isItem+"</isItem>\n" +
                "      <ItemDepartment>"+itemDepartment+"</ItemDepartment>\n" +
                "      <ItemID>"+itemID+"</ItemID>\n" +
                "      <ImgeUrl>"+imgeUrl+"</ImgeUrl>\n" +
                "    </ImageUpload>\n" +
                "  </soap:Body>\n" +
                "</soap:Envelope>";
        return temp;
    }

//    public static void GetPrintQRCodeOnReceipt(Context context) {
//        //Bitmap[] bitmapQRCode = new Bitmap[1];
//        //Bitmap bitmapQRCode = null;
//        String  status_str_info = Query.GetStatus(MainActivity.strbillNo);
//        Log.i("TAGGG___","status_str_info___"+status_str_info+"__"+ENUM.SALES.toUpperCase());
//        if (status_str_info.toUpperCase().equals(ENUM.SALES.toUpperCase())) {
//            String str_info = Query.GetQRCodeFromBillPayment(MainActivity.strbillNo);
//            Log.i("str_info_d", String.valueOf(str_info));
//            //bitmapQRCode = GenerateQRCodeString(context, str_info);
//            GenerateQRCodeString(context, str_info);
//        }
//        //Log.i("TAGGG___","status_str_info__d_"+bitmapQRCode);
//        //return bitmapQRCode;
//    }
//    public static Bitmap bitmapQRCode = null;
//    private static void GenerateQRCodeString(final Context context, final String strInfo) {
////        final SweetAlertDialog pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
////        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
////        pDialog.setTitleText("Loading ...");
//        //pDialog.setCancelable(true);
////        pDialog.show();
//        //final Bitmap[] bitmapQRCode = new Bitmap[1];
//
//        queue_qrcode = Volley.newRequestQueue(context);
//        Log.i("First_TESTING","First_TESTING");
////        RequestQueue queue = Volley.newRequestQueue(this);
//        //String url = "http://" + qr_code_shoptima_url;
//        String url = MainActivity.qr_code_shoptima_url;
//        Log.i("qr_code_shopturl_dd",url);
//        //String url = "http://" + "llposmgr.ddns.net:8085/Service.asmx";
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        //pDialog.dismiss();
//                        // response code
//                        String xmlString = response;
//                        Log.i("GenerateQRCodeString_", response);
////                        <?xml version="1.0" encoding="utf-8"?><soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema"><soap:Body><GenerateQRCodeStringResponse xmlns="http://tempuri.org/"><GenerateQRCodeStringResult><xs:schema id="NewDataSet" xmlns="" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:msdata="urn:schemas-microsoft-com:xml-msdata"><xs:element name="NewDataSet" msdata:IsDataSet="true" msdata:MainDataTable="ReturnTable" msdata:UseCurrentLocale="true"><xs:complexType><xs:choice minOccurs="0" maxOccurs="unbounded"><xs:element name="ReturnTable"><xs:complexType><xs:sequence><xs:element name="STATUS" type="xs:string" minOccurs="0" /><xs:element name="ERRORCODE" type="xs:string" minOccurs="0" /><xs:element name="QRCODE" type="xs:string" minOccurs="0" /></xs:sequence></xs:complexType></xs:element></xs:choice></xs:complexType></xs:element></xs:schema><diffgr:diffgram xmlns:msdata="urn:schemas-microsoft-com:xml-msdata" xmlns:diffgr="urn:schemas-microsoft-com:xml-diffgram-v1"><DocumentElement xmlns=""><ReturnTable diffgr:id="ReturnTable1" msdata:rowOrder="0" diffgr:hasChanges="inserted"><STATUS>SUCCESS</STATUS><ERRORCODE>0</ERRORCODE><QRCODE>dcs:ZMiWiadjOZm90Rszvn5+SDCYtZ060vKEq+XRWhfgR3Jd6Lujro5gl1iKxsSKQuoOHUUjpInfZ6HmrecQ5LLuOgRvmPm750E4F1td66GcmRS7I16DCd4SRCEMWexHpdxNP5T89iNNIVFXrq8RP1sbdQ==</QRCODE></ReturnTable></DocumentElement></diffgr:diffgram></GenerateQRCodeStringResult></GenerateQRCodeStringResponse></soap:Body></soap:Envelope>
////
//                        Document xmlparse = null;
//                        Document parse = APIActivity.XMLParseFunction(xmlString, xmlparse);
//                        String status = "";
//                        //String qrcodestring = "";
//                        String dcsQrCodeString = "";
//                        for (int ii = 0; ii < parse.getElementsByTagName("STATUS").getLength(); ii++) {
//                            status = (parse.getElementsByTagName("STATUS").getLength() > 0)
//                                    ? parse.getElementsByTagName("STATUS").item(ii).getTextContent() : " ";
//                        }
//                        for (int iii = 0; iii < parse.getElementsByTagName("QRCODE").getLength(); iii++) {
//                            dcsQrCodeString = (parse.getElementsByTagName("QRCODE").getLength() > 0)
//                                    ? parse.getElementsByTagName("QRCODE").item(iii).getTextContent() : " ";
//                        }
//
//                        //qrcodestring = dcsQrCodeString.split(":")[1];
//                        qrcodestring = dcsQrCodeString;
//                        //qrcodestring = "dcs:ZMiWiadjOZm90Rszvn5+SG7Ep2MsGAfnfx8xoCE1e3rA4mtgaoy0BfjbFEHlqY5dRAZzgiBdN8zO+uJulLTQ3flsQ0wozp2FOyNmX2pCABM1QJIlTooayanA5NhSv3nQboljX7a7KnjoRCF/zOXccA==";
//                        Log.i("qrcodestring_","qrcodestring_"+qrcodestring);
//                        //bitmap_qr_shoptima = convertQRCodeImage(context,qrcodestring);
//                        //convertQRCodeImage(dcsQrCodeString);
//                        //bitmapQRCode[0] = convertQRCodeImage(context, qrcodestring);
//                        //bitmapQRCode = convertQRCodeImage(context, qrcodestring);
//                        convertQRCodeImage(context, qrcodestring);
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.i("First_TESTING","First_error");
////                        // As of f605da3 the following should work
//                NetworkResponse response = error.networkResponse;
//                Log.i("errorres:1",error.getMessage());
//                Log.i("errorres:", String.valueOf(error));
//                if (error instanceof ServerError && response != null) {
//                    try {
//                        String res = new String(response.data,
//                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));
//
//                        Log.i("res:",res);
//                        // Now you can use any deserializer to make sense of data
////                                JSONObject obj = new JSONObject(res);
//                    } catch (UnsupportedEncodingException e1) {
//                        e1.printStackTrace();
//                    }
//                }
//            }
//        }) {
//            @Override
//            public Map<String, String> getParams() {
//                return null;
//            }
//            @Override
//            public byte[] getBody() {
//                String encodedURL = null;
//                String temp = "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
//                        "  <soap12:Body>\n" +
//                        "    <GenerateQRCodeString xmlns=\"http://tempuri.org/\">\n" +
//                        "      <UserID>"+MainActivity.qr_code_shoptima_user_id+"</UserID>\n" +
//                        "      <Password>"+MainActivity.qr_code_shoptima_password+"</Password>\n" +
//                        "      <strInfo>"+strInfo+"</strInfo>\n" +
//                        "    </GenerateQRCodeString>\n" +
//                        "  </soap12:Body>\n" +
//                        "</soap12:Envelope>";
//                Log.i("APPshp", "request__String: " + temp);
//                byte[] b = temp.getBytes(Charset.forName("UTF-8"));
//                //pDialog.show();
//                return b;
//            }
//
//            @Override
//            public String getBodyContentType() {
//                return "text/xml;charset=utf-8";
//            }
//        };
//        queue_qrcode.add(stringRequest);
//        //return bitmapQRCode;
//    }
//
//    //    private void convertQRCodeImage(String dcsQrCodeString, Bitmap qrcode_bitmap, int billID) {
//    private static void convertQRCodeImage(Context context,String dcsQrCodeString) {
//        Bitmap bitmap_qr_shoptima = null;
//        try {
//            //this.qrcode_bitmap = TextToImageEncode(dcsQrCodeString);
//            bitmap_qr_shoptima = TextToImageEncode(context,dcsQrCodeString);
//            CashLayoutActivity.bitmap_qr_shoptima = bitmap_qr_shoptima;
//            TransactionDetailsActivity.bitmap_qr_shoptima = bitmap_qr_shoptima;
//            //qrcode_bitmap = TextToImageEncode(dcsQrCodeString);
//        } catch (WriterException e) {
//            e.printStackTrace();
//            Log.i("qrcode_bitmap_","e_error_"+e);
//        }
//        Log.i("_sd_","sd_"+dcsQrCodeString.length());
//        Log.i("_sd_","sdbitmap_qr_shoptima_"+bitmap_qr_shoptima);
//        Log.i("_sd_","CashBmap_qr_shoptima_"+CashLayoutActivity.bitmap_qr_shoptima);
//        //return bitmap_qr_shoptima;
//    }
//    public static Bitmap TextToImageEncode(Context context,String Value) throws WriterException {
//        BitMatrix bitMatrix;
//        try {
//            bitMatrix = new MultiFormatWriter().encode(
//                    Value,
//                    DATA_MATRIX.QR_CODE,
//                    500, 500, null
//            );
//            Log.i("ssdsd","bitMatrix"+bitMatrix);
//        } catch (IllegalArgumentException Illegalargumentexception) {
//            Log.i("ssd","Illegalargumentexception"+Illegalargumentexception);
//            return null;
//        }
//        Log.d("bitMatrix","bitMatrix"+bitMatrix);
//        int bitMatrixWidth = bitMatrix.getWidth();
//
//        int bitMatrixHeight = bitMatrix.getHeight();
//
//        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];
//
//        for (int y = 0; y < bitMatrixHeight; y++) {
//            int offset = y * bitMatrixWidth;
//
//            for (int x = 0; x < bitMatrixWidth; x++) {
//
//                pixels[offset + x] = bitMatrix.get(x, y) ?
//                        context.getResources().getColor(R.color.cardview_dark_background):context.getResources().getColor(R.color.cardview_light_background);
//            }
//        }
//        Log.d("bitMatrixWidth","bitMatrixWidth"+bitMatrixWidth);
//        Log.d("bitMatrixHeight","bitMatrixHeight"+bitMatrixHeight);
//        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);
//        Log.d("bitmap","bitmapqqq"+bitmap);
//        bitmap.setPixels(pixels, 0, 500, 0, 0, bitMatrixWidth, bitMatrixHeight);
//        Log.d("bitmap","bitmapqqq"+bitmap);
//
//        Log.d("Bitmap_","bitmap_"+bitmap);
//        return bitmap;
//    }

    public static Cursor getPossy() {
        String sql = "SELECT name,receipt_printer,receipt_count,hotkey_col,hotkey_row,bal_mode," +
                "bal_title,bill_fontsize,option,maplayout,refer_info1_name," +
                "refer_info2_name,refer_info3_name,roundtype,shoptima_url,shoptima_user_id," +
                "shoptima_password,shoptima_machine_id,shoptima_mall_code,qrcode_shoptima_url,qrcode_shoptima_user_id," +
                "qrcode_shoptima_password,qrcode_shoptima_machine_id,qrcode_shoptima_mall_code,qrcode_shoptima_brand_code,qrcode_shoptima_outlet_code" +
                " FROM POSSys LIMIT 1";
        return DBFunc.Query(sql,true);
    }

    public static Integer getBillID(String billNo) {
        Integer id = 0;
        Cursor CursorbillID = DBFunc.Query("SELECT BillNo FROM Bill WHERE TransNo = '"+billNo+"'",false);
        if (CursorbillID != null) {
            if (CursorbillID.moveToNext()) {
                id = CursorbillID.getInt(0);
            }
            CursorbillID.close();
        }
        return id;
    }
//    public static void SaveDetailsBillProduct(){
//        String sql = "INSERT INTO Details_BillProduct (BillNo,OnlineOrderBill,ProductQty,ProductName,ProductPrice,BillDetailsID," +
//                "BillDateTime,vchQueueNo,intTableNo,Cancel,CategoryID,CategoryName,ItemDiscountAmount,ProductID,DateTime) VALUES (";
//        sql += "'" + BillNo + "', ";
//        sql += "'" + OnlineOrderBill + "', ";
//        sql += "'" + ChkOut_sldQtyArr.get(i) + "', ";
//        sql += "'" + ChkOut_sldNameArr.get(i) + "', ";
//        sql += "'" + after_dis_amt.toString() + "', ";
//        sql += "'" + bill_details_id + "', ";
//        sql += "'" + dateFormat3 + "', ";
//        Log.i("dfdf___", String.valueOf(ChkOut_intTableNo.size()));
//        Log.i("dfdf__ddd_", String.valueOf(ChkOut_intTableNo));
//        if (ChkOut_vchQueueNo.size() > 0) {
//            Log.i("DfdvchQueueNo__", String.valueOf(ChkOut_vchQueueNo));
//            if (ChkOut_vchQueueNo.get(i) == null) {
//                sql += "'0', ";
//            } else {
//                sql += "'" + ChkOut_vchQueueNo.get(i) + "', ";
//            }
//        } else {
//            sql += "'0', ";
//        }
//        if (ChkOut_intTableNo.size() > 0) {
//            if (ChkOut_intTableNo.get(i) == null) {
//                sql += "'0', ";
//            } else {
//                sql += "'" + ChkOut_intTableNo.get(i) + "', ";
//            }
//        } else {
//            sql += "'0', ";
//        }
//        sql += "'" + str_Cancel + "', ";
//        sql += "'" + c_cate_catID + "', ";
//        sql += "'" + c_cate_catName + "', ";
//        sql += "'" + ChkOut_sltBillDisArr.get(i) + "', ";
//        sql += "'" + ChkOut_sldIDArr.get(i) + "', ";
//        sql += System.currentTimeMillis() + ")";
//        Log.i("Sf_taiProduct", sql);
//        DBFunc.ExecQuery(sql, false);
//    }

    public static Cursor GetItemTax(Integer id){
        String sql = "Select Amount,Type from BillPLUTax where BillPLU_idx = "+id+" ";
        Cursor c = DBFunc.Query(sql,false);
        return c;
    }

    public static JSONArray GetItemItemSyncSales(String bill_no,String sale_staus) {
        JSONArray item_sales_details_array = new JSONArray();
        try {
            String sql = "SELECT ProductName,ProductQty,ProductPrice,ProductID,ItemDiscountAmount,ProductName," +
                    "DiscountID,DiscountName,Remarks " +
                    "FROM DetailsBillProduct " +
                    "where BillNo = '"+bill_no+"' Group by ProductID,OpenPriceStatus,Remarks";

            Log.i("FdfdSQL","__DFDF"+sql);

            Cursor cursorDBP = DBFunc.Query(sql, false);
            if (cursorDBP != null) {
//                if (cursorDBP.getCount() == 0){
//
//                    item_sales_details_array = GetSaleItemEmptyFun();
//
//                }else {

                    Double originalAmount = 0.0;
                    Double Amount = 0.0;
                    String Qty = "";
                    String nameStr = "";
                    String supBarCodeNameStr = "";
                    String billPluId = "";
                    String billPluName = "";
                    Double itemdisAmt = 0.0;
                    Double ItemTaxExclusive = 0.0;
                    Double afterDiscountAmount = 0.0;
                    Integer DiscountID = 0;
                    String DiscountName = "";
                    String ItemRemarks = "";

                    while (cursorDBP.moveToNext()) {
                        nameStr = cursorDBP.getString(0);
                        Qty = cursorDBP.getString(1);
                        originalAmount = cursorDBP.getDouble(2)  / cursorDBP.getDouble(1);
                        Log.i("DF__","originalAmountntff_j_"+originalAmount);
                        Log.i("DF__","Amount__Qty_"+cursorDBP.getDouble(1)+"___"+cursorDBP.getDouble(2));
                       // Amount = cursorDBP.getDouble(1) * cursorDBP.getDouble(2);
                        Amount =   cursorDBP.getDouble(2);
                        Log.i("DF__","Amount__Amountff_j_"+Amount);
                        billPluId = cursorDBP.getString(3);
                        billPluName = cursorDBP.getString(5);
                        DiscountID = cursorDBP.getInt(6);
                        DiscountName = cursorDBP.getString(7);
                        ItemRemarks = cursorDBP.getString(8);

                        String sql_for_code = "SELECT Code " +
                                "FROM PLU " +
                                " WHERE Name = '"+nameStr+"' ";
                        Cursor Cursor_for_code = DBFunc.Query(sql_for_code,true);
                        if (Cursor_for_code != null) {
                            if (Cursor_for_code.moveToNext()) {
                                supBarCodeNameStr = Cursor_for_code.getString(0).toLowerCase();
                            }else {
                                supBarCodeNameStr = "";
                            }
                            Cursor_for_code.close();
                        }else {
                            supBarCodeNameStr = "";
                        }

                        Log.i("FdfdSQL","__DFDF"+billPluId);
                        //String UUID = GetUUID(Integer.parseInt(billPluId));
                        String UUID = GetUUIDByName(billPluName);

                        //itemdisAmt = cursorDBP.getDouble(4);
                        itemdisAmt = cursorDBP.getDouble(4) * cursorDBP.getDouble(1);
                        if (itemdisAmt > 0.0){
                            itemdisAmt = itemdisAmt;
                        }else {
                            itemdisAmt = 0.0;
                        }
                        afterDiscountAmount = Amount - itemdisAmt;
                        JSONObject item_sales_details = new JSONObject();
                        //item_sales_details.put(DeclarationConf.submitSalesItemID, billPluId);

                        Log.i("Dfdf___","billPluId___"+billPluId+"__"+UUID+"__"+bill_no);
                        item_sales_details.put(DeclarationConf.submitSalesItemID, UUID);
                        //item_sales_details.put(DeclarationConf.SupBarCode, nameStr);
                        item_sales_details.put(DeclarationConf.SupBarCode, supBarCodeNameStr);

                        double d_qty = Query.getStatusValue(sale_staus,Double.valueOf(Qty)); //Void or Not Status Checking

                        Cursor CursorBillItemTax = Query.GetItemTax(Integer.parseInt(billPluId));
                        if (CursorBillItemTax != null) {
                            if (CursorBillItemTax.moveToNext()){
                                //- (total price /  (1 + (7/100))
                                Double taxamount = CursorBillItemTax.getDouble(0);
                                String taxtype = CursorBillItemTax.getString(1);
                                if (taxtype.equals("1")) {
                                    //ItemTaxExclusive = afterDiscountAmount - (Amount / (1f + (taxamount / 100)));
                                }else {
                                    ItemTaxExclusive = afterDiscountAmount * (taxamount/100);
                                }
                            }
                            CursorBillItemTax.close();
                        }
                        ItemTaxExclusive = Query.getStatusValue(sale_staus,ItemTaxExclusive); //Void or Not Status Checking

                        String statusSales = "";
                        String chkBillNoStatus = "SELECT STATUS FROM SALES WHERE BillNo = '"+bill_no+"'";

                        Cursor CursorChkBillNoStatus = DBFunc.Query(chkBillNoStatus,false);
                        if (CursorChkBillNoStatus != null){
                            if (CursorChkBillNoStatus.moveToNext()){
                                statusSales = CursorChkBillNoStatus.getString(0).toUpperCase();
                            }
                            CursorChkBillNoStatus.close();
                        }

                        if (statusSales.equals("REFUND")){
                            item_sales_details.put(DeclarationConf.submitSalesItemQty, ((-1) * d_qty));
                        }else {
                            item_sales_details.put(DeclarationConf.submitSalesItemQty, d_qty);
                        }
                        item_sales_details.put(DeclarationConf.submitSalesItemUOMDesc, "PCS");
                        item_sales_details.put(DeclarationConf.submitSalesItemPrice, Double.valueOf(originalAmount));

                        String chk_hide_img = Query.GetOptions(22);
                        if (chk_hide_img.equals("1")) {

                            String UUID_DISC = Query.findfieldNameById("UUID", "Name", DiscountName , "DISC", true);

                            item_sales_details.put("DiscID1", UUID_DISC);
                            item_sales_details.put("DiscID2", "0");
                            item_sales_details.put("DiscID3", "0");
                        }
                        if (statusSales.equals("REFUND")) {
                            item_sales_details.put(DeclarationConf.submitSalesItemDisc, Double.valueOf(String.format("%.2f", (-1) * itemdisAmt)));
                        }else {
                            item_sales_details.put(DeclarationConf.submitSalesItemDisc, Double.valueOf(String.format("%.2f", itemdisAmt)));
                        }
                        if (statusSales.equals("REFUND")) {
                            item_sales_details.put(DeclarationConf.submitSalesItemTax, Double.valueOf(String.format("%.2f", (-1) * ItemTaxExclusive)));
                        }else {
                            item_sales_details.put(DeclarationConf.submitSalesItemTax, Double.valueOf(String.format("%.2f", ItemTaxExclusive)));
                        }

                        Cursor CursorPromo = Query.GetPromo(bill_no,billPluId);
                        //PLUID,PromoName,PromoValue from PLUPromo
                        if (CursorPromo != null) {
                            if (CursorPromo.moveToNext()) {
                                String PromoName = CursorPromo.getString(1);
                                String PromoValue = CursorPromo.getString(2);
                                String PromoTypeID = CursorPromo.getString(3);
                                String PromoTypeCode = Query.GetPromoTypeCode(PromoTypeID);
                                String PromoItemID = CursorPromo.getString(4);
                                String PromoID = CursorPromo.getString(5);

                                item_sales_details.put(DeclarationConf.submitSalesItemPromoID, PromoID);
                                item_sales_details.put(DeclarationConf.submitSalesItemPromoType, PromoTypeID);
                                if (statusSales.equals("REFUND")) {
                                    item_sales_details.put(DeclarationConf.submitSalesItemPromoDisc, String.format("%.2f", (-1) * Double.valueOf(PromoValue)));
                                }else {
                                    item_sales_details.put(DeclarationConf.submitSalesItemPromoDisc, String.format("%.2f", Double.valueOf(PromoValue)));
                                }
                                item_sales_details.put(DeclarationConf.submitSalesItemPromoTypeCode, PromoTypeCode);

                                DeclarationConf.sync_submitSalesItemPromoID = PromoID;
                                DeclarationConf.sync_submitSalesItemPromoType = PromoTypeID;
                                DeclarationConf.sync_submitSalesItemPromoDisc = String.format("%.2f", Double.valueOf(PromoValue));
                                DeclarationConf.sync_submitSalesItemPromoTypeCode = PromoTypeCode;
                            }else {
                                item_sales_details.put(DeclarationConf.submitSalesItemPromoID, 0);
                                item_sales_details.put(DeclarationConf.submitSalesItemPromoType, 0);
                                item_sales_details.put(DeclarationConf.submitSalesItemPromoDisc, 0);
                                item_sales_details.put(DeclarationConf.submitSalesItemPromoTypeCode, 0);

                                DeclarationConf.sync_submitSalesItemPromoID = "0";
                                DeclarationConf.sync_submitSalesItemPromoType = "0";
                                DeclarationConf.sync_submitSalesItemPromoDisc = "0";
                                DeclarationConf.sync_submitSalesItemPromoTypeCode = "0";
                            }
                        }else {
                            item_sales_details.put(DeclarationConf.submitSalesItemPromoID, 0);
                            item_sales_details.put(DeclarationConf.submitSalesItemPromoType, 0);
                            item_sales_details.put(DeclarationConf.submitSalesItemPromoDisc, 0);
                            item_sales_details.put(DeclarationConf.submitSalesItemPromoTypeCode, 0);

                            DeclarationConf.sync_submitSalesItemPromoID = "0";
                            DeclarationConf.sync_submitSalesItemPromoType = "0";
                            DeclarationConf.sync_submitSalesItemPromoDisc = "0";
                            DeclarationConf.sync_submitSalesItemPromoTypeCode = "0";

                        }


                        //double d_amt = Query.getStatusValue(sale_staus,Amount);
                        double d_amt = Query.getStatusValue(sale_staus,afterDiscountAmount);
                        if (statusSales.equals("REFUND")) {
                            item_sales_details.put(DeclarationConf.submitSalesItemTotal, (-1) * d_amt);
                        }else {
                            item_sales_details.put(DeclarationConf.submitSalesItemTotal, d_amt);
                        }

                        item_sales_details.put(DeclarationConf.submitSalesItemRemarks, ItemRemarks);

                        DeclarationConf.sync_submitSalesItemTotal = String.valueOf(d_amt);

                        item_sales_details_array.put(item_sales_details);


                        DeclarationConf.sync_submitSalesItemID = billPluId;
                        DeclarationConf.sync_SupBarCode = nameStr;
                        DeclarationConf.sync_submitSalesItemQty = String.valueOf(d_qty);
                        DeclarationConf.sync_submitSalesItemUOMDesc = String.valueOf("PCS");
                        DeclarationConf.sync_submitSalesItemPrice = String.valueOf(Amount);

                        DeclarationConf.sync_submitSalesItemDisc = String.format("%.2f", itemdisAmt);
                        DeclarationConf.sync_submitSalesItemTax = String.format("%.2f", ItemTaxExclusive);
//                DeclarationConf.sync_submitSalesItemPromoID = "0";
//                DeclarationConf.sync_submitSalesItemPromoType = "0";
//                DeclarationConf.sync_submitSalesItemPromoDisc = "0";
//                DeclarationConf.sync_submitSalesItemPromoTypeCode = "0";
//                DeclarationConf.sync_submitSalesItemTotal = String.valueOf(d_amt);

                       SyncSaleItemsSaveFun();
                    }
               // }
                cursorDBP.close();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return item_sales_details_array;
    }

    private static void SyncSaleItemsSaveFun() {
        Integer sync_sales_id = findLatestID("ID","SyncSales",false);
        Integer id_ = 0;

        String search_S_SItm_sql = "SELECT ID  FROM SyncSalesItem  WHERE SyncSalesID = '"+ sync_sales_id +"' ";
        Cursor config_option = DBFunc.Query(search_S_SItm_sql, false);
        if (config_option != null){
            while (config_option.moveToNext()) {
                id_ = config_option.getInt(0);
            }

            if (id_ > 0){
                //updateSyncSales(id_);
            }else {
                saveSyncSalesItem();
            }
            config_option.close();
        }
    }

    public static JSONArray GetSaleItemEmptyFun() {
        JSONArray item_sales_details_array = new JSONArray();
        JSONObject item_sales_details = new JSONObject();
        try {
            item_sales_details.put(DeclarationConf.submitSalesItemID, "0");
            item_sales_details.put(DeclarationConf.SupBarCode, "");
            item_sales_details.put(DeclarationConf.submitSalesItemQty, 0.0);
            item_sales_details.put(DeclarationConf.submitSalesItemUOMDesc, "");
            item_sales_details.put(DeclarationConf.submitSalesItemPrice, 0.0);
            item_sales_details.put("DiscID1", "0");
            item_sales_details.put("DiscID2", "0");
            item_sales_details.put("DiscID3", "0");
            item_sales_details.put(DeclarationConf.submitSalesItemDisc, 0.0);
            item_sales_details.put(DeclarationConf.submitSalesItemTax, 0.0);
            item_sales_details.put(DeclarationConf.submitSalesItemPromoID, 0);
            item_sales_details.put(DeclarationConf.submitSalesItemPromoType, 0);
            item_sales_details.put(DeclarationConf.submitSalesItemPromoDisc, 0);
            item_sales_details.put(DeclarationConf.submitSalesItemPromoTypeCode, 0);

            DeclarationConf.sync_submitSalesItemPromoID = "0";
            DeclarationConf.sync_submitSalesItemPromoType = "0";
            DeclarationConf.sync_submitSalesItemPromoDisc = "0";
            DeclarationConf.sync_submitSalesItemPromoTypeCode = "0";

            item_sales_details.put(DeclarationConf.submitSalesItemTotal, 0.0);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        DeclarationConf.sync_submitSalesItemTotal = "0";

        item_sales_details_array.put(item_sales_details);


        DeclarationConf.sync_submitSalesItemID = "0";
        DeclarationConf.sync_SupBarCode = "";
        DeclarationConf.sync_submitSalesItemQty = "0";
        DeclarationConf.sync_submitSalesItemUOMDesc = "";
        DeclarationConf.sync_submitSalesItemPrice = "0";

        DeclarationConf.sync_submitSalesItemDisc = "0";
        DeclarationConf.sync_submitSalesItemTax = "0";

        saveSyncSalesItem();

        return item_sales_details_array;
    }

    public static void saveSyncSalesItem() {
        Integer sync_sales_id = Query.findLatestID("ID","SyncSales",false);
        DBFunc.ExecQuery("INSERT INTO SyncSalesItem " +
                "(ItemID,SupBarCode,ItemQty,ItemUOMDesc,ItemPrice," +
                "ItemDisc,ItemTax,PromoID,PromoType,PromoDisc," +
                "PromoTypeCode,ItemTotal,SyncSalesID,DateTime) " +
                "VALUES ("
                + "'"+DBFunc.PurifyString(DeclarationConf.sync_submitSalesItemID)+"',"
                + "'"+DBFunc.PurifyString(DeclarationConf.sync_SupBarCode)+"',"
                + "'"+DBFunc.PurifyString(DeclarationConf.sync_submitSalesItemQty)+"',"
                + "'"+DBFunc.PurifyString(DeclarationConf.sync_submitSalesItemUOMDesc)+"',"
                + "'"+DBFunc.PurifyString(DeclarationConf.sync_submitSalesItemPrice)+"',"
                + "'"+DBFunc.PurifyString(DeclarationConf.sync_submitSalesItemDisc)+"',"
                + "'"+DBFunc.PurifyString(DeclarationConf.sync_submitSalesItemTax)+"',"
                + "'"+DBFunc.PurifyString(DeclarationConf.sync_submitSalesItemPromoID)+"',"
                + "'"+DBFunc.PurifyString(DeclarationConf.sync_submitSalesItemPromoType)+"',"
                + "'"+DBFunc.PurifyString(DeclarationConf.sync_submitSalesItemPromoDisc)+"',"
                + "'"+DBFunc.PurifyString(DeclarationConf.sync_submitSalesItemPromoTypeCode)+"',"
                + "'"+DBFunc.PurifyString(DeclarationConf.sync_submitSalesItemTotal)+"',"
                + "'"+DBFunc.PurifyString(String.valueOf(sync_sales_id))+"',"
                + System.currentTimeMillis()  +")", false);

        //DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth, System.currentTimeMillis(), "Inventory -> Add -> ItemID: "+ItemID);

        //saveSyncSalesPayment();
    }

    public static Double getStatusValue(String sale_staus, Double valueOriginal) {
        Double value = 0.0;
        if (sale_staus.equals("VOID")) {
            value = (-1) * Double.valueOf(valueOriginal);
        } else {
            value = Double.valueOf(valueOriginal);

        }
        return value;
    }

//    public static JSONArray GetItemPaymentSyncSales(String bill_payment_type,String __volley_pay_type,String sale_staus,String amtt,String __volley_changeamt) {
//        JSONArray sales_multiple_payement = new JSONArray();
//        JSONObject sales_payment_details = new JSONObject();
//        JSONArray sales_payement_details_array =   new JSONArray();
//                String option_val = "";
//        String option_id = "";
//        try {
//            if (bill_payment_type.length() > 0) {
//                option_val = bill_payment_type.toUpperCase();
//            } else {
//                option_val = __volley_pay_type.toUpperCase();
//            }
//
//            String s_paymentsql = "SELECT ID FROM payment WHERE UPPER(Name) = '" + option_val + "'";
//
//            Cursor config_option = DBFunc.Query(s_paymentsql, true);
//            assert config_option != null;
//            while (config_option.moveToNext()) {
//                option_id = config_option.getString(0);
//            }
//
//            sales_payment_details.put("PaymentID", option_id);
//            sales_payment_details.put("strPayment", bill_payment_type.toUpperCase());
//            if (bill_payment_type.length() > 0) {
//                sales_payment_details.put("strPayment", bill_payment_type.toUpperCase());
//            } else {
//                sales_payment_details.put("strPayment", __volley_pay_type.toUpperCase());
//            }
//            double d_amtt = Query.getStatusValue(sale_staus,Double.valueOf(amtt));
//            sales_payment_details.put("SalesPayTtl", d_amtt);
//
//            double chgamt = 0.0;
//            if (Double.valueOf(__volley_changeamt) < 0.0) {
//                chgamt = (-1) * Double.valueOf(__volley_changeamt);
//            }
//            double paytotal = Double.valueOf(amtt) - chgamt;
//            double d_paytotal = 0.0;
//            double d_chgamt = Query.getStatusValue(sale_staus,Double.valueOf(paytotal));
//            sales_payment_details.put("SalesBalTtl", d_paytotal);
//            sales_payment_details.put("ChangeAmount", d_chgamt);
//            sales_payement_details_array.put(sales_payment_details);
//            sales_multiple_payement.put(sales_payment_details);
//
//            //jsonObject.put("SalesPayments", sales_multiple_payement);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return sales_multiple_payement;
//    }
//
//    public static String GetReceiptNoSaleSync(String bill_no) {
//        //String sql_ = "SELECT strftime('%Y%m%d', CloseDateTime / 1000, 'unixepoch') FROM Bill WHERE BillNo = '" + bill_no + "'  and Cancel = 0  ";
//        String sql_ = "SELECT strftime('%Y%m%d', CloseDateTime / 1000, 'unixepoch') FROM Bill WHERE BillNo = '" + bill_no + "' ";
//
//        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
//        Date date = new Date();
//        String yyyymmdd = dateFormat.format(date);
//        Cursor config_option_bill = DBFunc.Query(sql_, false);
//        assert config_option_bill != null;
//        while (config_option_bill.moveToNext()) {
//        }
//
//        return yyyymmdd + bill_no;
//    }
//
//    public static Double GetTaxTotalSyncSales(String amtt, String sale_staus, String billno) {
//        int tax_Seq = 0;
//        Double tax_Amount = 0.0;
//        //double tax_ttl = (Double.valueOf(amtt) / 100) * 7;
//        double tax_rate = 0.00;
//        Double tax_ttl = 0.00;
//        //Cursor c = DBFunc.Query("SELECT Rate FROM Tax ORDER BY Seq ASC", true);
//        Cursor CursorTax = Query.calculateTaxAmountAllForPLU(billno);
//
//        if (CursorTax != null) {
//            tax_ttl = 0.00;
//            while (CursorTax.moveToNext()) {
//                tax_Seq = CursorTax.getInt(0);
//                tax_Amount = CursorTax.getDouble(2);
//                if (tax_Seq == 1) {
//                    //tax_ttl = (Double.valueOf(amtt) - ((Double.valueOf(amtt) / (1f + (tax_Amount / 100f)))));
//                }else {
//                    tax_ttl = Double.valueOf(amtt) * (tax_Amount/100f);
//                }
//            }
//            CursorTax.close();
//        }
//        if (tax_ttl > 0.0) {
//            tax_ttl = tax_ttl;
//        }else {
//            tax_ttl = 0.0;
//        }
//        Double d_tax_ttl = getStatusValue(sale_staus,tax_ttl);
//        return d_tax_ttl;
//    }

//    public static String StringRequestQueue(RequestQueue queue, String url, final String tagName, final String temp,
//                                            final String billNo, final String statusSyncStatus, final String zCloseStatus,
//                                            final String linkStr) {
//
//        final String[] Result = {""};
//        final String[] returnValue = {""};
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                        String xmlString = response;
//
//                        Log.i("REsponse___","xmlString__"+xmlString);
//
//                        //progressDialog.dismiss();
////                        success();
//                        //updatePayment(finalCompany_code1, finalOption_id,bill_payment_type.toUpperCase(), finalUrl);
//                        Document xmlparse = null;
//                        Document parse = APIActivity.XMLParseFunction(xmlString, xmlparse);
//
//                        for (int i = 0; i < parse.getElementsByTagName(tagName).getLength(); i++) {
//                                Result[0] = (parse.getElementsByTagName(tagName).getLength() > 0)
//                                        ? parse.getElementsByTagName(tagName).item(i).getTextContent() : " ";
//
//                            Log.i("REsponse___","Success__"+Result[0]);
//
//
//                            if (linkStr.equals("SubmitSales") && !(Result[0].equals("Success"))) {
//                                String Status = "";
//                                String TransNo = "";
//                                try {
//                                    JSONObject JObj = new JSONObject(Result[0]);
//                                    if (JObj.has("Status")) {
//                                        Status = JObj.getString("Status");
//                                    }
//                                    if (JObj.has("TransNo")) {
//                                        TransNo = JObj.getString("TransNo");
//                                    }
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//                                returnValue[0] = Status;
//
//                                Log.i("Query_","Status_____"+Status);
//
//                                if (Status.equals("Success")){
//                                    if (TransNo != null && TransNo.length() > 0) {
//
//                                        UpdateSyncSalesStatusAftSendData(TransNo, statusSyncStatus, zCloseStatus);
//
//                                    }
//                                }
//
//                            }else {
//
//                                if (Result[0].equals("Success")){
//
//                                    returnValue[0] = Result[0]; //Return String value Success
//                                }else {
//                                    Log.i("Dfdf___","linkStr___"+linkStr);
//                                    if (linkStr.equals("GetDiscountList")){
//
//                                        String getDisResult = "";
//                                        for (int dis_i=0; dis_i < parse.getElementsByTagName("GetDiscountListResponse").getLength(); dis_i++) {
//                                            getDisResult = (parse.getElementsByTagName("GetDiscountListResponse").getLength() > 0)
//                                                    ? parse.getElementsByTagName("GetDiscountListResponse").item(dis_i).getTextContent() : " ";
//                                        }
//
//                                        DiscountFun(getDisResult,returnValue);
//
//                                    }else if (linkStr.equals("GetUsersList")){
//                                        String getDisResult = "";
//                                        for (int dis_i=0; dis_i < parse.getElementsByTagName("GetUsersListResponse").getLength(); dis_i++) {
//                                            getDisResult = (parse.getElementsByTagName("GetUsersListResponse").getLength() > 0)
//                                                    ? parse.getElementsByTagName("GetUsersListResponse").item(dis_i).getTextContent() : " ";
//                                        }
//                                        GetUserListFun(getDisResult,returnValue);
//                                    }else if (linkStr.equals("GetUsersPermission")){
//                                        String getDisResult = "";
//                                        for (int dis_i=0; dis_i < parse.getElementsByTagName("GetUsersPermissionResponse").getLength(); dis_i++) {
//                                            getDisResult = (parse.getElementsByTagName("GetUsersPermissionResponse").getLength() > 0)
//                                                    ? parse.getElementsByTagName("GetUsersPermissionResponse").item(dis_i).getTextContent() : " ";
//                                        }
//                                        GetUsersPermissionListFun(getDisResult,returnValue);
//                                    }
//                                }
//                            }
//
//
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
////                        // As of f605da3 the following should work
//                //progressDialog.dismiss();
//                NetworkResponse response = error.networkResponse;
//                if (error instanceof ServerError && response != null) {
//                    try {
//                        String res = new String(response.data,
//                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));
//
//                        // Now you can use any deserializer to make sense of data
////                                JSONObject obj = new JSONObject(res);
//                    } catch (UnsupportedEncodingException e1) {
//                        // Couldn't properly decode data to string
//                        e1.printStackTrace();
//                    }
//                }
//            }
//        }) {
//            @Override
//            public Map<String, String> getParams() {
//                return null;
//            }
//
//            @Override
//            public byte[] getBody() {
//                //Log.i("APP", "request String: " + temp);
//                //progressDialog = Query.showProgressDialog(SyncActivity.this, ENUM.Sync);
//                byte[] b = temp.getBytes(Charset.forName("UTF-8"));
//
//                return b;
//            }
//
//            @Override
//            public String getBodyContentType() {
//                return "text/xml;charset=utf-8";
//            }
//        };
//        queue.add(stringRequest);
//        return Result[0];
//    }

    public static String StringRequestQueue(Context context,RequestQueue queue, String url, final String tagName, final String temp,
                                            final String billNo, final String statusSyncStatus, final String zCloseStatus,
                                            final String linkStr) {

        Log.i("url___","url__"+url);
        Log.i("url___","url__"+billNo);
        Log.i("url___","url__"+statusSyncStatus);

        final String[] Result = {""};
        final String[] returnValue = {""};
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        String xmlString = response;

                        Log.i("REsponse___","xmlString__"+xmlString);

                        //progressDialog.dismiss();
//                        success();
                        //updatePayment(finalCompany_code1, finalOption_id,bill_payment_type.toUpperCase(), finalUrl);
                        Document xmlparse = null;
                        Document parse = APIActivity.XMLParseFunction(xmlString, xmlparse);

                        for (int i = 0; i < parse.getElementsByTagName(tagName).getLength(); i++) {
                            Result[0] = (parse.getElementsByTagName(tagName).getLength() > 0)
                                    ? parse.getElementsByTagName(tagName).item(i).getTextContent() : " ";

                            Log.i("REsponse___","linkStr__"+linkStr);
                            Log.i("REsponse___","Success__"+Result[0]);//_Error: Duplicate sales record.


                            if (linkStr.equals("SubmitSales") && !(Result[0].equals("Success"))) {
                                String Status = "";
                                String TransNo = "";
                                try {
                                    JSONObject JObj = new JSONObject(Result[0]);
                                    if (JObj.has("Status")) {
                                        Status = JObj.getString("Status");
                                    }
                                    if (JObj.has("TransNo")) {
                                        TransNo = JObj.getString("TransNo");
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                returnValue[0] = Status;

                                Log.i("Query_","Status_____"+Status);

                                if (Status.equals("Success")){
                                    if (TransNo != null && TransNo.length() > 0) {

                                        UpdateSyncSalesStatusAftSendData(TransNo, statusSyncStatus, zCloseStatus);

                                    }
                                }

                            }else if (linkStr.equals("ValidateLicenseKey")) {
                                // ValidateLicenseKey
                                String getValidateLicenseKeyResult = "";
                                for (int lk_i=0; lk_i < parse.getElementsByTagName("ValidateLicenseKeyResponse").getLength(); lk_i++) {
                                    getValidateLicenseKeyResult = (parse.getElementsByTagName("ValidateLicenseKeyResponse").getLength() > 0)
                                            ? parse.getElementsByTagName("ValidateLicenseKeyResponse").item(lk_i).getTextContent() : " ";
                                }

                                JSONObject obj = null;
                                try {
                                    obj = new JSONObject(getValidateLicenseKeyResult);
                                    String list_tbllicense = obj.getString("tbllicense");
                                    JSONArray mJsonArray_list_tbllicense = new JSONArray(list_tbllicense);

                                    for (int disarr_i = 0; disarr_i < mJsonArray_list_tbllicense.length(); disarr_i++) {
                                        JSONObject list_discount_arr_Values = mJsonArray_list_tbllicense.getJSONObject(disarr_i);
                                        String CHECKSUM = list_discount_arr_Values.getString("CHECKSUM");
                                        String MacAddress = list_discount_arr_Values.getString("MacAddress");
                                        String LicenseType = list_discount_arr_Values.getString("LicenseType");
                                        Integer NoOfDay = list_discount_arr_Values.getInt("NoOfDay");


                                        Tbllicense licensekey = new Tbllicense();
                                        licensekey.setChecksum(CHECKSUM);
                                        licensekey.setMacAddress(MacAddress);
                                        licensekey.setLicenseType(LicenseType);
                                        licensekey.setNoOfDay(NoOfDay);
                                        Query.SaveLicenseKey(licensekey);

                                        String typeKey = billNo;
                                        String GetLicenseKey = Query.GetLicenseKey(typeKey,"Query");

                                        Log.i("__","GetLicenseKeyset___"+GetLicenseKey+"__"+typeKey);

                                         if (GetLicenseKey.equals(typeKey)){
                                            Intent icontext = new Intent(context, RemarkMainActivity.class);
                                            icontext.putExtra("licenseKey",GetLicenseKey);
                                            context.startActivity(icontext);

                                        } else {
                                             Query.SweetAlertWarningYesOnly(context,"License Key Invalid.","OK");
                                         }
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }else if (linkStr.equals("UpdateTransZReadResponse")) {

                                try {
                                    JSONObject resultZUpdateJson = new JSONObject(Result[0]);
                                    String responseStatus = resultZUpdateJson.getString("Status");
                                    String responseTransNo = resultZUpdateJson.getString("TransNo");

                                    //zCloseStatus means UUID
                                    Query.updateZReadByResponseValue(zCloseStatus,responseStatus,responseTransNo);

                                } catch (Exception e){

                                }
//                                {"Status":"Success","TransNo":"02536c00-4ce1-11ec-adbe-00090faa0001"}
//                                {"Status":"Success","TransNo":"163119c1-2075-48b5-8afc-ac2f4489e1cf"}
                                Log.i("SDfsfd____","DSFDSF_Result[0]zero__"+Result[0]);
                            }else {

                                if (Result[0].equals("Success")){

                                    returnValue[0] = Result[0]; //Return String value Success

                                    SyncActivity.updateSyncSales(0, "SUCCESS",billNo);

                                }else {

                                    if (linkStr.equals("GetDiscountList")){

                                        String getDisResult = "";
                                        for (int dis_i=0; dis_i < parse.getElementsByTagName("GetDiscountListResponse").getLength(); dis_i++) {
                                            getDisResult = (parse.getElementsByTagName("GetDiscountListResponse").getLength() > 0)
                                                    ? parse.getElementsByTagName("GetDiscountListResponse").item(dis_i).getTextContent() : " ";
                                        }

                                        DiscountFun(getDisResult,returnValue);

                                    }else if (linkStr.equals("GetUsersList")){
                                        String getDisResult = "";
                                        for (int dis_i=0; dis_i < parse.getElementsByTagName("GetUsersListResponse").getLength(); dis_i++) {
                                            getDisResult = (parse.getElementsByTagName("GetUsersListResponse").getLength() > 0)
                                                    ? parse.getElementsByTagName("GetUsersListResponse").item(dis_i).getTextContent() : " ";
                                        }
                                        GetUserListFun(getDisResult,returnValue);
                                    }else if (linkStr.equals("GetUsersPermission")){
                                        String getDisResult = "";
                                        for (int dis_i=0; dis_i < parse.getElementsByTagName("GetUsersPermissionResponse").getLength(); dis_i++) {
                                            getDisResult = (parse.getElementsByTagName("GetUsersPermissionResponse").getLength() > 0)
                                                    ? parse.getElementsByTagName("GetUsersPermissionResponse").item(dis_i).getTextContent() : " ";
                                        }
                                        GetUsersPermissionListFun(getDisResult,returnValue);
                                    }
                                }
                            }


                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                        // As of f605da3 the following should work
                //progressDialog.dismiss();
                NetworkResponse response = error.networkResponse;
                Log.i("errornetresp__","resp__"+response);
                Log.i("errornetresp__","resp__"+error);
                if (error instanceof ServerError && response != null) {
                    try {
                        String res = new String(response.data,
                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                        Log.i("errornetresp__","res__"+res);
                        // Now you can use any deserializer to make sense of data
//                                JSONObject obj = new JSONObject(res);
                    } catch (UnsupportedEncodingException e1) {
                        // Couldn't properly decode data to string
                        e1.printStackTrace();
                    }
                }
                Log.i("eeeeeeeeeeee","eeeeeeeeeeee_3"+"FAIL"+billNo);
                SyncActivity.updateSyncSales(0, "FAIL",billNo);
            }
        }) {
            @Override
            public Map<String, String> getParams() {
                return null;
            }

            @Override
            public byte[] getBody() {
                //Log.i("APP", "request String: " + temp);
                //progressDialog = Query.showProgressDialog(SyncActivity.this, ENUM.Sync);

                Log.i("APP", "request____String___ttt" + temp);
                byte[] b = temp.getBytes(Charset.forName("UTF-8"));

                return b;
            }

            @Override
            public String getBodyContentType() {
                return "text/xml;charset=utf-8";
            }
        };
        queue.add(stringRequest);
        return Result[0];
    }

    public static void GetUsersPermissionListFun(String getDisResult, String[] returnValue) {
//        "securityaccessright": [ { "id": "080d32fe-f8f4-11e7-95c5-68f7282584d2",
//                "userid": "105e4bf0-f8f5-11e7-95c5-68f7282584d2",
//                "slid": "7c98eca2-f996-11e7-8a97-68f7282584d2", "sl_code": "", "accessaction": "enable" }
    }

    public static void GetUserListFun(String getDisResult, String[] returnValue) {

//        { "users": [ { "ID": "06e8fe2d-f5a8-4b97-ac4c-5eb28cd4b351", "user": "", "password": "123456",
//                "email": "1984656@qq.com", "email_alt": "", "hph_countrycode": "", "hph_areacode": "", "hph": "",
//                "tel_countrycode": "", "tel_areacode": "", "tel": "", "fax_countrycode": "", "fax_areacode": "",
//                "fax": "", "org": "", "title": null, "userlevel": 0, "usergroup": "0", "accesslevel": 0, "accessgroup": "0",
//                "uniqueid": "", "UsersDOB": "0001-01-01T00:00:00", "UsersFood": "", "UsersSalutation": "0",
//                "UsersFirstName": "Kobe", "UsersMiddleName": "", "UsersLastName": "", "UsersMetaName": "",
//                "OtherLanguage": "", "UsersCompanyID": "0", "UsersClassificationID": "0", "UsersDesignationID": "0",
//                "UsersDepartmentID": "0", "UsersStartDate": "0001-01-01T00:00:00", "UsersEndDate": "0001-01-01T00:00:00",
//                "UsersSalary": 0.00, "UsersSexID": 0, "UsersRaceID": "0",
//                "UsersCitizenship": "777fb669-f8ef-11e7-95c5-68f7282584d2", "UsersReligonID": "0", "UsersMaritalStatusID": "0",
//                "UsersSpouse": "", "UsersSpouseContact": "", "UsersNOK": "", "UsersNOKContact": "", "UsersAddress1": "",
//                "UsersAddress2": "", "UsersAddress3": "", "UsersPostcode": "",
//                "UsersCountryID": "777fb669-f8ef-11e7-95c5-68f7282584d2", "UsersNotes": "", "UsersLeaveAnnual": 21,
//                "UsersLeaveMarriage": 21, "UsersLeaveMedical": 14, "UsersLeaveCompassionate": 21, "UsersLeaveProcreative": 7,
//                "UsersLeaveMilitary": 14, "UsersLeaveUnpaid": 14, "UsersCommision": "N", "CommissionPerc": 0.00,
//                "CommissionTypeID": "0", "DefaultRetailID": 0, "SupplierID": "0", "MemberID": "0", "Display": "Y",
//                "TokenCode": "", "vchPreferLanguage": "en", "PendingSync": "N", "SPBI01": 0, "SPBI02": 0, "SPBI03": 0,
//                "SPBI04": 0, "SPBI05": 0, "SPD01": 0.000000, "SPD02": 0.000000, "SPD03": 0.000000, "SPD04": 0.000000,
//                "SPD05": 0.000000, "SPI01": 0, "SPI02": 0, "SPI03": 0, "SPI04": 0, "SPI05": 0, "SPC01": "N", "SPC02": "N",
//                "SPC03": "N", "SPC04": "N", "SPC05": "N", "SPV01": "", "SPV02": "", "SPV03": "", "SPV04": "", "SPV05": "",
//                "SPT01": null, "SPT02": null, "SPT03": null, "SPT04": null, "SPT05": null, "SDT01": null, "SDT02": null,
//                "SDT03": null, "SDT04": null, "SDT05": null, "LastUser": "Y", "LastUpdate": "0001-01-01T00:00:00",
//                "LockUser": "", "LockUpdate": "0001-01-01T00:00:00", "LockStatus": "0",
//                "RecordStatus": "READY", "RecordUpdate": "0001-01-01T00:00:00", "QueueStatus": "READY" }

    }

    public static void DiscountFun(String getDisResult, String[] returnValue) {

//                                    { "list_discount_sales": [ { "ID": "e8262679-f8f1-11e7-95c5-68f7282584d2",
//                                            "Nick": "OPENDISC$", "VALUE": "DISC $", "ButtonName": "DISC $",
//                                            "PrintOnReceipt": "DISC $", "FULL": "", "ItemDisc": "Y", "GroupDisc": "Y",
//                                            "DiscType": "A", "DiscAmount": 0.00, "OpenDisc": "Y" },
//                                        { "ID": "e8262c62-f8f1-11e7-95c5-68f7282584d2", "Nick": "OPENDISC%",
//                                                "VALUE": "DISC %", "ButtonName": "DISC %", "PrintOnReceipt": "DISC %",
//                                                "FULL": "", "ItemDisc": "Y", "GroupDisc": "Y", "DiscType": "P",
//                                                "DiscAmount": 0.00, "OpenDisc": "Y" },
//                                        { "ID": "e8263d8f-f8f1-11e7-95c5-68f7282584d2", "Nick": "20%DISC",
//                                                "VALUE": "20%DISC", "ButtonName": "20% DISC", "PrintOnReceipt": "20% DISC", "FULL": "",
//                                                "ItemDisc": "Y", "GroupDisc": "Y", "DiscType": "P", "DiscAmount": 20.00, "OpenDisc": "N" },
//                                        { "ID": "e8264d36-f8f1-11e7-95c5-68f7282584d2", "Nick": "FOC", "VALUE": "FOC", "ButtonName": "FOC",
//                                                "PrintOnReceipt": "FOC", "FULL": "FOC", "ItemDisc": "Y", "GroupDisc": "N", "DiscType": "P",
//                                                "DiscAmount": 100.00, "OpenDisc": "N" } ] }\

        if (getDisResult != null && getDisResult.length() > 0) {
            JSONObject obj = null;
            try {
                obj = new JSONObject(getDisResult);
                String list_discount_sales = obj.getString("list_discount_sales");
                JSONArray mJsonArray_list_discount_sales = new JSONArray(list_discount_sales);

                for (int disarr_i = 0; disarr_i < mJsonArray_list_discount_sales.length(); disarr_i++) {
                    JSONObject list_discount_arr_Values = mJsonArray_list_discount_sales.getJSONObject(disarr_i);
//                                                 [ { "ID": "0606dd44-a6ea-496a-bce7-8fd5854fe60a", "Nick": "", "VALUE": "", "ButtonName": "",
//                                                        "PrintOnReceipt": "", "FULL": "0",
//                                                        "ItemDisc": "Y", "GroupDisc": "Y", "DiscType": "P", "DiscAmount": 25.00, "OpenDisc": "N" },
                    String obj_uuid = list_discount_arr_Values.getString("ID");
                    String obj_Nick = list_discount_arr_Values.getString("Nick");
                    String obj_VALUE = list_discount_arr_Values.getString("VALUE");
                    String obj_ButtonName = list_discount_arr_Values.getString("ButtonName");
                    String obj_PrintOnReceipt = list_discount_arr_Values.getString("PrintOnReceipt");
                    String obj_FULL = list_discount_arr_Values.getString("FULL");
                    String obj_ItemDisc = list_discount_arr_Values.getString("ItemDisc");
                    String obj_GroupDisc = list_discount_arr_Values.getString("GroupDisc");
                    String obj_DiscType = list_discount_arr_Values.getString("DiscType");
                    String obj_DiscAmount = list_discount_arr_Values.getString("DiscAmount");
                    String obj_OpenDisc = list_discount_arr_Values.getString("OpenDisc");

                    String distypeStr = "";
                    if (obj_DiscType.equals("A")) {
                        distypeStr = "$ Dollar Value";
                    }else {
                        distypeStr = "% Percentage";
                    }


                    String sss = "SELECT UUID FROM Disc WHERE UUID = '"+obj_uuid+"' ";

                    Cursor csss = DBFunc.Query(sss,true);
                    if (csss != null) {

                        if (csss.getCount() == 0){

                            String ssName = "SELECT Name FROM Disc WHERE Name = '"+obj_Nick+"'";
                            Cursor CursorDisName = DBFunc.Query(ssName,true);

                            if (CursorDisName != null){
                                if (CursorDisName.getCount() == 0){
                                    String sql = "INSERT INTO Disc (UUID,Name,Value,Option,Seq,PromoTypeID,Type,DiscountType,BillDiscount,AmountDiscount," +
                                            "ServiceChargeDiscount,DiscountDate,ItemDisc,GroupDisc,OpenDisc,DateTime) " +
                                            "VALUES ('" + obj_uuid + "','" +
                                            obj_Nick + "','" +
                                            obj_DiscAmount + "'," +
                                            "000" + ",'" +
                                            "1" + "','" +
                                            "0" + "','" +
                                            "0" + "','" +

                                            distypeStr + "','" +
                                            "0" + "','" +
                                            "0" + "','" +
                                            "0" + "','" +
                                            "0" + "','" +
                                            obj_ItemDisc + "','" +
                                            obj_GroupDisc + "','" +
                                            obj_OpenDisc + "'," +
                                            System.currentTimeMillis() + ")";

                                    DBFunc.ExecQuery(sql, true);
                                }
                                CursorDisName.close();
                            }

                        }
                        csss.close();
                    }

                    //GetAndSaveDiscount();
                    returnValue[0] = "Success"; //Return String value Success
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    public static void UpdateSyncSalesStatusAftSendData(String transNo,String statusSyncStatus,String zCloseStatus) {

//                                    <string xmlns="http://tempuri.org/">
//                                            {"Status":"Success",
//                                            "TransNo":"2020-12-0600000021V,2020-12-0600000021,2020-12-0600000027V,2020-12-0600000027," +
//                                            "2020-12-0600000042V,2020-12-0600000042,2020-12-0600000046V,2020-12-0600000046"}</string>

        //After the changes arrayjson one by one => response get one by one so changed
        //{"Status":"Success","TransNo":"2020120500000022"}

        String updateSql = "";
        //String billNo = Arrays.toString(new String[]{transNo});
        String billNo = transNo;
        Log.i("dfdfs___","transNo____"+transNo);
       if (billNo != null && billNo.length() > 0){
           updateSql = "SELECT ID,SalesStatus  FROM SyncSales ";
           if (billNo.contains("V")){
               String voidBillNo = billNo.split("V")[0];
               //updateSql += " WHERE BillNo = '" + voidBillNo + "' AND SalesStatus != 'SALES'";
               updateSql += " WHERE BillNo = '" + voidBillNo + "' ";
           }else {
               //updateSql += " WHERE BillNo = '" + billNo + "' AND SalesStatus = 'SALES'";
               updateSql += " WHERE BillNo = '" + billNo + "' ";
           }
       }
        Cursor CursorSyncSalesUpdate = DBFunc.Query(updateSql, false);
        Integer id_ = 0;
        if (CursorSyncSalesUpdate != null) {
            if (CursorSyncSalesUpdate.moveToNext()) {
                id_ = CursorSyncSalesUpdate.getInt(0);
            }
            CursorSyncSalesUpdate.close();
        }

        //id_ = findLatestID("ID","SyncSales",false);
        //SyncActivity.updateSyncSales(id_, statusSyncStatus);
        SyncActivity.updateSyncSales(id_, "SUCCESS",billNo);

        if (zCloseStatus.equals("Z")) {
            Query.UpdateIsZInSyncSales();
        }
    }

    //    private static void GetAndSaveDiscount(Discount dis) {
//        String sql = "INSERT INTO Disc (Name,Value,Option,Seq,PromoTypeID,Type,DiscountType,BillDiscount,AmountDiscount," +
//                "ServiceChargeDiscount,DiscountDate,DateTime) " +
//                "VALUES ('" + DBFunc.PurifyString(dis.getName()) + "','" +
//                dis.getValue() + "','" +
//                dis.getOption() + "'," +
//                dis.getSeq() + ",'" +
//                "1" + "','" +
//                dis.getType() + "','" +
//                dis.getDiscountType() + "','" +
//                dis.getBillDiscount() + "','" +
//                dis.getAmountDiscount() + "','" +
//                dis.getServiceChargeDiscount() + "','" +
//                dis.getDiscountDate() + "'," +
//                System.currentTimeMillis() + ")";
//        Log.i("__sql",sql);
//        DBFunc.ExecQuery(sql, true);
//    }
    public static Cursor GetDiscount() {

        String cnulluuidsql = "SELECT ID FROM Disc WHERE UUID IS NULL" ;
        String uuid = "0";
        Integer id = 0;
        Cursor cnulluuid = DBFunc.Query(cnulluuidsql,true);
        if (cnulluuid != null){
            while (cnulluuid.moveToNext()){
                id = cnulluuid.getInt(0);
                uuid = UUID.randomUUID().toString();
                String str_update_str = "UPDATE DISC SET UUID =  '"+uuid+"' WHERE ID = " +id;
                DBFunc.ExecQuery(str_update_str, true);
            }
            cnulluuid.close();
        }



        String sql = "SELECT UUID,Name,Value,DiscountType FROM Disc " ;
        Log.i("__sql",sql);

        Discount dis = null;

        Cursor cz = DBFunc.Query(sql,true);


        return cz;
    }

//    public static void UpdateIsZInSyncSales() {
//        String str_update_str = "UPDATE SyncSales SET IsZ =  'Z' WHERE  ( IsZ  IS NULL OR IsZ = '' ) ";
//        //String str_update_str = "UPDATE SyncSales SET IsZ =  'Z' WHERE ( IsZ  IS NULL || IsZ = '' ) ";
//        Log.i("str_update_str___","str_update_str____"+str_update_str);
//        DBFunc.ExecQuery(str_update_str, false);
//    }

    public static Cursor getTaxByName(String name) {
        Cursor c = DBFunc.Query("SELECT ID,Name,Type FROM Tax WHERE Name = '"+name+"' ", true);
        return c;
    }

    public static void saveDetailsBillProduct_AssignValue(String statusv,Integer billId,String billNo, String onlineOrderBill, Integer productQty,
                                                          String productName, String productPrice,
                                                            String itemDiscount, String productID,
                                                          String vchQueueNo, String intTableNo, String str_Cancel,
                                                          String slddisID, String slddisName, String slddisTypeName, String slddisType,
                                                          String DiscountValue,Integer EditProductSheetFragment_ID,String openPrice,
                                                          String strRemarks) {

        Cursor c_cate = Query.findCategory(productName);
        String c_cate_catID = "0";
        String c_cate_catName = "0";
        String taxName = "0";
        String taxID = "0";
        if (c_cate != null){
            if (c_cate.moveToNext()){
                c_cate_catID = c_cate.getString(0);
                c_cate_catName = c_cate.getString(1);
            }else {
                c_cate_catID = "0";
                c_cate_catName = "0";
            }
            c_cate.close();
        }else {
            c_cate_catID = "0";
            c_cate_catName = "0";
        }

        if (itemDiscount == null) {
            itemDiscount = "0";
        }
        if (productPrice == null) {
                productPrice = "0";
        }

        if (productPrice != null && productPrice.length() == 0){
            productPrice = "0";
        }
        if (itemDiscount != null && itemDiscount.length() == 0){
            itemDiscount = "0";
        }
        Double after_dis_amt = 0.0;
        try {

            after_dis_amt = Double.valueOf(productPrice) - Double.valueOf(itemDiscount);
        } catch (Exception e){
            after_dis_amt = 0.0;
        }

        String beforeOpenPrice = "0";

        Query.SaveDetailsBillProduct(billNo,onlineOrderBill,productQty,productName,productPrice,itemDiscount,productID,
                c_cate_catID,c_cate_catName,vchQueueNo,intTableNo,str_Cancel,billId,taxID,taxName,slddisID,
                slddisName,slddisType,slddisTypeName,DiscountValue,after_dis_amt,openPrice,beforeOpenPrice,
                EditProductSheetFragment_ID,strRemarks);


//        Integer billId,String billNo, String onlineOrderBill, Integer productQty, String productName
        //xAndMatchPromoFun;
        Integer totalQty = 0;

        //String sql_for_promo = "SELECT SUM(ProductQty) " +
        String sql_for_promo = "SELECT ProductQty " +
                "FROM DetailsBillProduct Where BillNo = '" + billNo + "' AND Cancel = 'SALES' AND ProductID = '"+productID+"' " +
                "group by BillNo order by BillNo DESC";

        Cursor Cursor_DBP_ForPromo = DBFunc.Query(sql_for_promo,false);
        if (Cursor_DBP_ForPromo != null) {
            if (Cursor_DBP_ForPromo.moveToNext()) {
                totalQty = Cursor_DBP_ForPromo.getInt(0);
            }
            Cursor_DBP_ForPromo.close();
        }
        CalculatePromoMixAndMatch(productName,totalQty,Double.valueOf(productPrice),Double.valueOf(productQty),
                String.valueOf(billId),
                billNo,productID,productName);

    }
    public static void SaveDetailsBillProduct(String billNo, String onlineOrderBill, Integer productQty, String productName, String productPrice,
                                              String itemDiscount, String productID, String c_cate_catID, String c_cate_catName,
                                              String vchQueueNo, String intTableNo, String str_Cancel, Integer billId, String taxID,
                                              String taxName, String slddisID, String slddisName,
                                              String slddisType, String slddisTypeName, String discountValue, Double after_dis_amt,
                                              String openPriceStatus, String beforeOpenPrice,Integer EditProductSheetFragment_ID,
                                              String strRemarks) {
        String dateFormat3 = CheckOutActivity.dateFormat55.format(new Date()).toString();


//        String itemRemarksPLU = "";
//        String searchItemRemarksPLU = "SELECT Remarks from PLU WHERE ID = " +productID;
//        Log.i("search__","_searchItemRemarksPLU__"+searchItemRemarksPLU);
//        Cursor csearchItemRemarksPLU = DBFunc.Query(searchItemRemarksPLU,true);
//        if (csearchItemRemarksPLU != null) {
//            if (csearchItemRemarksPLU.moveToNext()){
//                itemRemarksPLU = csearchItemRemarksPLU.getString(0);
//            }
//            csearchItemRemarksPLU.close();
//        }
//        Log.i("search__","_itemRemarksPLU__"+itemRemarksPLU);
        Double after_diss_amt = 0.0;

        if (productPrice == null) {
            productPrice = "0";
        }
        if (itemDiscount == null) {
            productPrice = "0";
        }

        try {
            after_diss_amt = Double.valueOf(productPrice) - Double.valueOf(itemDiscount);
        } catch (Exception e){
            after_diss_amt = 0.0;
        }
        String taxItemAmount = "0";

        String existingBillNo = "";
        String sql = "Select BillNo,SUM(ProductQty) from DetailsBillProduct ";


        if (EditProductSheetFragment.FragmentVar.equals("CheckoutItem")) {
           // sql +=  " WHERE ID = " + EditProductSheetFragment.ID ;
            sql +=  " WHERE ID = " + EditProductSheetFragment_ID ;
        } else {
            sql +=  " Where BillNo = '"+billNo+"' " +
                    "AND ProductId = '"+productID+"' " ;
            sql += Query.CheckingNullOrNot(openPriceStatus,"OpenPriceStatus");
        }
        sql += " group by BillNo,ProductID,OpenPriceStatus,Remarks";

        Cursor c = DBFunc.Query(sql,false);
        if (c != null){
            if (c.getCount() == 0){
                existingBillNo ="";
//                productQty = 0;
            }else {
                if (c.moveToNext()) {
                    existingBillNo = c.getString(0);
//                    productQty = c.getInt(1);
                }
            }
            c.close();
        }
        if (productQty > 0) {
            Double pr = 0.0;
            String openPriceStatusStr = "0";
            String sqlOp = "SELECT AllowOpenPrice FROM PLU WHERE ID = " + productID;
            Cursor cOp = DBFunc.Query(sqlOp, true);
            if (cOp != null) {
                if (cOp.moveToNext()) {
                    openPriceStatusStr = cOp.getString(0);
                }
                cOp.close();
            }
            if (openPriceStatusStr != null && openPriceStatusStr.equals("1")) {
                pr = Double.valueOf(productQty) * Double.valueOf(productPrice);
//                pr = Double.valueOf(productPrice);
            } else {
                String searchPPrice = "SELECT Price From PLU WHERE ID = " + productID;

                c = DBFunc.Query(searchPPrice, true);
                if (c != null) {
                    if (c.moveToNext()) {
                        pr = Double.valueOf(productQty) * c.getDouble(0);
//                        pr = c.getDouble(0);
                    }
                    c.close();
                }
            }

            if (existingBillNo != null && existingBillNo.length() > 3) {
                String val = "0";
                String val2 = "0";
                String val3 = "0";
                if (taxItemAmount.toString().length() > 0) {
                    val = String.format("%.2f", Double.valueOf(taxItemAmount));
                } else {
                    val = "0";
                }
                if (discountValue != null && discountValue.length() > 0 && !slddisID.equals("0")) {
                    //val2 =  String.format("%.2f", Double.valueOf(productQty) * Double.valueOf(discountValue));
                    val2 =  String.format("%.2f", Double.valueOf(discountValue));
                } else {
                    val2 = "0";
                }
                if (after_dis_amt != null && after_dis_amt.toString().length() > 0) {
                    val3 = String.format("%.2f", Double.valueOf(after_dis_amt));
                } else {
                    val3 = "0";
                }

                String str_update_str = "UPDATE DetailsBillProduct SET " +
                        "BillNo =  '" + billNo + "', " +
                        "OnlineOrderBill =  '" + onlineOrderBill + "', " +
                        "ProductQty =  '" + productQty + "', " +
                        "ProductName =  '" + productName + "', " +
                        "ProductPrice =  '" + String.format("%.2f", pr) + "', " +
                        "BillDateTime =  '" + dateFormat3 + "', " +
                        "ItemDiscountAmount =  '" + String.format("%.2f", Double.valueOf(itemDiscount)) + "', " +
                        "ProductID =  '" + productID + "', " +
                        "CategoryID =  '" + c_cate_catID + "', " +
                        "CategoryName =  '" + c_cate_catName + "', " +
                        "vchQueueNo =  '" + vchQueueNo.replace("'", "") + "', " +
                        "intTableNo =  '" + intTableNo.replace("'", "") + "', " +
                        "Cancel =  '" + str_Cancel + "', " +
                        "BillID =  '" + billId + "', " +
                        "TaxID =  '" + taxID + "', " +
                        "TaxType =  '" + str_Cancel + "', " +
                        "TaxName =  '" + taxType + "', " +
                        "TaxAmount =  '" + val + "', " +
                        "DiscountID =  '" + slddisID + "', " +
                        "DiscountName =  '" + slddisName + "', " +
                        "DiscountType =  '" + slddisType + "', " +
                        "DiscountTypeName =  '" + slddisTypeName + "', " +
                        "DiscountValue =  '" + val2 + "', " +
                        "AfterDiscountAmount =  '" + val3 + "', " +
                        "OpenPriceStatus =  '" + openPriceStatus + "', " +
                        "BeforeOpenPrice =  '" + String.format("%.2f",  Double.valueOf(beforeOpenPrice)) + "', " +
                        "DateTime =  " + System.currentTimeMillis() + " " ;
                        //"WHERE ID = " + EditProductSheetFragment.ID ;

                if (EditProductSheetFragment.FragmentVar.equals("CheckoutItem")) {
                    //str_update_str +=  " WHERE ID = " + EditProductSheetFragment.ID ;
                    str_update_str +=  " WHERE ID = " + EditProductSheetFragment_ID ;
                } else {
                    str_update_str +=  " Where BillNo = '"+billNo+"' " +
                            "AND ProductId = '"+productID+"' "  +
                            "AND OpenPriceStatus = '"+openPriceStatus+"' " ;
                }
//                        "WHERE BillNo = '" + billNo + "'  AND ProductId = '"+productID+"' " ;


                Log.i("DFD___", "DFDF_updateee_" + str_update_str);
                DBFunc.ExecQuery(str_update_str, false);

                DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                        System.currentTimeMillis(), DBFunc.PurifyString("Query -UpdatedDetailsBillProd-" + str_update_str));

                String productUUID = TransactionDetailsActivity.ValidAndGetValue("UUID", "PLU", "ID", productID, true);

                //Query.updatePriceDetailsBillProduct(productUUID,billNo,openPriceStatus,"edit",pr,EditProductSheetFragment.ID);
                Query.updatePriceDetailsBillProduct(productUUID,billNo,openPriceStatus,"edit",pr,String.valueOf(EditProductSheetFragment_ID));
            } else {
                String uniqueId = UUID.randomUUID().toString();
                sql = "INSERT INTO DetailsBillProduct (UUID,BillNo,OnlineOrderBill,ProductQty,ProductName,ProductPrice,BillDetailsID,BillDateTime," +
                        "ItemDiscountAmount,ProductID,CategoryID,CategoryName,vchQueueNo,intTableNo,Cancel,Remarks," +
                        "BillID,TaxID,TaxType,TaxName,TaxAmount,DiscountID,DiscountName,DiscountType,DiscountTypeName,DiscountValue," +
                        "AfterDiscountAmount,OpenPriceStatus,BeforeOpenPrice,DateTime) VALUES (";
//                Remarks,
                sql += "'" + uniqueId + "', ";
                sql += "'" + billNo + "', ";
                sql += "'" + onlineOrderBill + "', ";
                sql += "'" + productQty + "', ";
//        sql += "'" + productID + "', ";
                sql += "'" + productName + "', ";
                sql += "'" + String.format("%.2f", pr) + "', ";
                sql += "'" + 0 + "', ";
                sql += "'" + dateFormat3 + "', ";
                sql += "'" + String.format("%.2f",  Double.valueOf(itemDiscount)) + "', ";
                sql += "'" + productID + "', ";
                productID = "0";
                sql += "'" + c_cate_catID + "', ";
                sql += "'" + c_cate_catName + "', ";
                sql += "'" + vchQueueNo.replace("'", "") + "', ";
                sql += "'" + intTableNo.replace("'", "") + "', ";
                sql += "'" + str_Cancel + "', ";
                sql += "'" + strRemarks + "', ";
                sql += "'" + billId + "', ";
                sql += "'" + taxID + "', ";
                sql += "'" + taxType + "', ";
                sql += "'" + taxName + "', ";
                if (taxItemAmount.toString().length() > 0) {
                    sql += "'" + String.format("%.2f", Double.valueOf(taxItemAmount)) + "', ";
                } else {
                    sql += "'0', ";
                }
                sql += "'" + slddisID + "', ";
                sql += "'" + slddisName + "', ";
                sql += "'" + slddisType + "', ";
                sql += "'" + slddisTypeName + "', ";
                if (discountValue.length() > 0) {
                    sql += "'" + String.format("%.2f",   Double.valueOf(discountValue)) + "', ";
                } else {
                    sql += "'0', ";
                }
                if (after_dis_amt.toString().length() > 0) {
                    sql += "'" + String.format("%.2f",  Double.valueOf(after_dis_amt)) + "', ";
                } else {
                    sql += "'0', ";
                }
                sql += "'" + openPriceStatus + "', ";

                sql += "'" + String.format("%.2f",   Double.valueOf(beforeOpenPrice)) + "', ";

//                sql += "'" + itemRemarksPLU + "', ";

                sql += System.currentTimeMillis() + ")";

                Log.i("DFD___", "DFDF__" + sql);
                DBFunc.ExecQuery(sql, false);

                DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                        System.currentTimeMillis(), DBFunc.PurifyString("Query -SavedDetailsBillProd-" + sql));
            }


            String bId = findBillIDByBillNo(billNo);
            String str_update_str = "UPDATE DetailsBillProduct SET BillID =  '" + bId + "' WHERE BillNo = '" + billNo + "' ";
            DBFunc.ExecQuery(str_update_str, false);


            //String BillPLU_idx = String.valueOf(productID);
            Integer product_taxID = SearchProductTaxIDByProductID(productID);
            //Integer ttaxID = GetTaxById(product_taxID);
            Integer ttaxID = 0;
            String ttaxName = "0";
            String ttaxAcronym = "0";
            String ttaxRate = "0";
            Integer ttaxType = 0;
            Integer ttaxSeq = 0;
            String sql__ = "SELECT ID,Name,Acronym,Rate,Type,Seq FROM Tax WHERE ID = " + product_taxID;
            Cursor Cursor_Tax = DBFunc.Query(sql__, true);
            if (Cursor_Tax != null) {
                if (Cursor_Tax.moveToNext()) {
                    ttaxID = Cursor_Tax.getInt(0);
                    ttaxName = Cursor_Tax.getString(1);
                    ttaxAcronym = Cursor_Tax.getString(2);
                    ttaxRate = Cursor_Tax.getString(3);
                    ttaxType = Cursor_Tax.getInt(3);
                    ttaxSeq = Cursor_Tax.getInt(4);
                }
                Cursor_Tax.close();
            }
            double ttotal_tax = 0.0;
            if (ttaxType.equals("1")) {
                ttotal_tax = 0.0;
                ttotal_tax = Query.calculateInclusive(after_diss_amt, Integer.parseInt(ttaxRate));
                //String str_inclusive =  taxName.toUpperCase() + "(" + taxRate+"%)" +" SGD "+"$" + String.format("%.2f", amt_inclusive) + " Incl";
                //str += "\n" + str_inclusive;
            } else {
                ttotal_tax = 0.0;
                ttotal_tax = calculateExclusive(after_diss_amt, Integer.parseInt(ttaxRate));
            }

            Query.BillPLUTax(Integer.valueOf(productID), ttaxID, ttaxName, ttaxAcronym, Double.valueOf(ttaxRate), ttaxType, ttaxSeq, ttotal_tax);
            Query.DeleteProductQty(billNo);


            String sqlll = "SELECT BillNo FROM BillPayment WHERE BillNo = '" + billNo + "' ";
            Cursor csqlll = DBFunc.Query(sqlll, false);
            if (csqlll != null) {
                if (csqlll.getCount() == 0) {

                    String BillID = findBillIDByBillNo(billNo);
                    String STATUS = "PENDING";
//        String fieldName,String IdName, String Id,String tbName, Boolean flag
                    Integer TotalItems = 0;
                    String TableNo = "0";
                    String QueueNo = "0";
                    double TotalAmount = 0.0;
                    String OnlineOrderBill = "0";
//                    String sqql = "SELECT SUM(ProductQty), SUM(ProductPrice),OnlineOrderBill,vchQueueNo,intTableNo,Cancel,SUM(ItemDiscountAmount) " +
//                            " FROM DetailsBillProduct Where BillNo = '" + billNo + "' " +
//                            " group by BillNo order by BillNo DESC";
                    String sqql = "SELECT (ProductQty), (ProductPrice),OnlineOrderBill,vchQueueNo,intTableNo,Cancel,(ItemDiscountAmount) " +
                            " FROM DetailsBillProduct Where BillNo = '" + billNo + "' " +
                            " group by BillNo order by BillNo DESC";
                    Cursor Cursor_DBP = DBFunc.Query(sqql, false);
                    if (Cursor_DBP != null) {
                        TotalItems = 0;
                        TotalAmount = 0.0;
                        OnlineOrderBill = "0";
                        while (Cursor_DBP.moveToNext()) {
//                        if (Cursor_DBP.moveToNext()) {
                            TotalItems += Cursor_DBP.getInt(0);
                            //Double exact_price = Cursor_DBP.getDouble(1) - Cursor_DBP.getDouble(6);
                            Double exact_price = Cursor_DBP.getDouble(1) - (Cursor_DBP.getDouble(0) * Cursor_DBP.getDouble(6));
                            TotalAmount += exact_price;
                            OnlineOrderBill = Cursor_DBP.getString(2);
                            QueueNo = Cursor_DBP.getString(3);
                            TableNo = Cursor_DBP.getString(4);
//                        STATUS = Cursor_DBP.getString(5);
                        }
                        Cursor_DBP.close();
                    }
                    DateFormat dateFormat55 = new SimpleDateFormat("dd MMM yyyy  hh:mm aa");
                    String Date = dateFormat55.format(new Date()).toString();
                    BillListModel blist = new BillListModel(BillID, billNo, STATUS, String.valueOf(TotalItems), Date,
                            TableNo, QueueNo, OnlineOrderBill, String.valueOf(TotalAmount), 0);
                    Log.i("Dfd_____STATUSdd", "STATUSdd___" + STATUS);

                    UpdateBillList(blist, billNo);
                }
                csqlll.close();
            }

            //String productUUID = TransactionDetailsActivity.ValidAndGetValue("UUID", "PLU", "ID", productID, true);

            // Query.updatePriceDetailsBillProduct(productUUID,billNo,openPriceStatus);
            String productUUID = TransactionDetailsActivity.ValidAndGetValue("UUID", "PLU", "ID", productID, true);

            //Query.updatePriceDetailsBillProduct(productUUID,billNo,openPriceStatus,"edit",pr,EditProductSheetFragment.ID);
            Query.updatePriceDetailsBillProduct(productUUID,billNo,openPriceStatus,"edit",pr,String.valueOf(EditProductSheetFragment_ID));

            EditProductSheetFragment.modiID.clear();
            EditProductSheetFragment.modiName.clear();
            EditProductSheetFragment.modiPrice.clear();
            EditProductSheetFragment.UnSelectedItem.clear();
            EditProductSheetFragment.EditFragmentRemarks = "";
        }
    }
//    public static void SaveDetailsBillProduct(String billNo, String onlineOrderBill, Integer productQty, String productName, String productPrice,
//                                              String itemDiscount, String productID, String c_cate_catID, String c_cate_catName,
//                                              String vchQueueNo, String intTableNo, String str_Cancel, Integer billId, String taxID, String taxName,
//                                              String slddisID, String slddisName,
//                                              String slddisType, String slddisTypeName, String discountValue, Double after_dis_amt,
//                                              String openPriceStatus, String beforeOpenPrice) {
//        String dateFormat3 = CheckOutActivity.dateFormat55.format(new Date()).toString();
//
//
////        String itemRemarksPLU = "";
////        String searchItemRemarksPLU = "SELECT Remarks from PLU WHERE ID = " +productID;
////        Log.i("search__","_searchItemRemarksPLU__"+searchItemRemarksPLU);
////        Cursor csearchItemRemarksPLU = DBFunc.Query(searchItemRemarksPLU,true);
////        if (csearchItemRemarksPLU != null) {
////            if (csearchItemRemarksPLU.moveToNext()){
////                itemRemarksPLU = csearchItemRemarksPLU.getString(0);
////            }
////            csearchItemRemarksPLU.close();
////        }
////        Log.i("search__","_itemRemarksPLU__"+itemRemarksPLU);
//         Double after_diss_amt = 0.0;
//
//         if (productPrice == null) {
//             productPrice = "0";
//         }
//         if (itemDiscount == null) {
//             productPrice = "0";
//         }
//
//        try {
//            after_diss_amt = Double.valueOf(productPrice) - Double.valueOf(itemDiscount);
//        } catch (Exception e){
//            after_diss_amt = 0.0;
//        }
//        String taxItemAmount = "0";
//
////        productQty = RecyclerViewAdapter.latestQty;
//
//
////        productQty = 0;
//        String existingBillNo = "";
//////        String sql = "SELECT BillNo FROM DetailsBillProduct WHERE BillNo = '"+billNo+"' LIMIT 1";
//        String sql = "Select BillNo,SUM(ProductQty) from DetailsBillProduct ";
////                "Where BillNo = '"+billNo+"' " +
////                "AND ProductId = '"+productID+"' " +
////                "AND OpenPriceStatus = '"+openPriceStatus+"' " ;
//
//        if (EditProductSheetFragment.FragmentVar.equals("CheckoutItem")) {
//            sql +=  " WHERE ID = " + EditProductSheetFragment.ID ;
////            //if (EditFragmentRemarks != null && EditFragmentRemarks.trim().equals("")) {
////            if (EditFragmentRemarks != null && EditFragmentRemarks.trim().length() > 0 && !(EditFragmentRemarks.equals("0"))
////                    && !(EditFragmentRemarks.trim() == null)
////                    && !(EditFragmentRemarks.trim().equals("null"))) {
//////                         deletesql += " AND Remarks IS NULL ";
////
////                sql += " AND Remarks = '" + EditFragmentRemarks + "' ";
////            } else {
////                sql += " AND ( Remarks IS NULL OR Remarks = '' OR Remarks = 'null' ) ";
////                //                        if (FragmentVar.equals("CheckoutItem")) {
////                //                        }
////                //                            " AND Remarks = '"+EditFragmentRemarks+"' ";
////            }
//        } else {
//            sql +=  " Where BillNo = '"+billNo+"' " +
//                "AND ProductId = '"+productID+"' " +
//                "AND OpenPriceStatus = '"+openPriceStatus+"' " ;
//        }
//            sql += " group by BillNo,ProductID,OpenPriceStatus,Remarks";
////        Where BillNo = '00000017' AND ProductId = '1' AND OpenPriceStatus = '_71' group by BillNo,ProductID,OpenPriceStatus
////        Where BillNo = '00000017' AND ProductId = '1' AND OpenPriceStatus = '_72' group by BillNo,ProductID,OpenPriceStatus
//
//        Cursor c = DBFunc.Query(sql,false);
//        if (c != null){
//            if (c.getCount() == 0){
//                existingBillNo ="";
////                productQty = 0;
//            }else {
//                if (c.moveToNext()) {
//                    existingBillNo = c.getString(0);
////                    productQty = c.getInt(1);
//                }
//            }
//            c.close();
//        }
//        Log.i("productQty___","productQtysavesales_____"+productQty);
//        if (productQty > 0) {
//            Double pr = 0.0;
//            String openPriceStatusStr = "0";
//            String sqlOp = "SELECT AllowOpenPrice FROM PLU WHERE ID = " + productID;
//            Cursor cOp = DBFunc.Query(sqlOp, true);
//            if (cOp != null) {
//                if (cOp.moveToNext()) {
//                    openPriceStatusStr = cOp.getString(0);
//                }
//                cOp.close();
//            }
//            if (openPriceStatusStr != null && openPriceStatusStr.equals("1")) {
//                pr = Double.valueOf(productQty) * Double.valueOf(productPrice);
////                pr = Double.valueOf(productPrice);
//            } else {
//                String searchPPrice = "SELECT Price From PLU WHERE ID = " + productID;
//
//                c = DBFunc.Query(searchPPrice, true);
//                if (c != null) {
//                    if (c.moveToNext()) {
//                        pr = Double.valueOf(productQty) * c.getDouble(0);
////                        pr = c.getDouble(0);
//                    }
//                    c.close();
//                }
//            }
//
//            if (existingBillNo != null && existingBillNo.length() > 3) {
//                String val = "0";
//                String val2 = "0";
//                String val3 = "0";
//                if (taxItemAmount.toString().length() > 0) {
//                    val = String.format("%.2f", Double.valueOf(taxItemAmount));
//                } else {
//                    val = "0";
//                }
//                if (discountValue != null && discountValue.length() > 0 && !slddisID.equals("0")) {
//                    //val2 =  String.format("%.2f", Double.valueOf(productQty) * Double.valueOf(discountValue));
//                    val2 =  String.format("%.2f", Double.valueOf(discountValue));
//                } else {
//                    val2 = "0";
//                }
//                if (after_dis_amt != null && after_dis_amt.toString().length() > 0) {
//                    val3 = String.format("%.2f", Double.valueOf(after_dis_amt));
//                } else {
//                    val3 = "0";
//                }
//
//                String str_update_str = "UPDATE DetailsBillProduct SET " +
//                        "BillNo =  '" + billNo + "', " +
//                        "OnlineOrderBill =  '" + onlineOrderBill + "', " +
//                        "ProductQty =  '" + productQty + "', " +
//                        "ProductName =  '" + productName + "', " +
//                        "ProductPrice =  '" + String.format("%.2f", pr) + "', " +
//                        "BillDateTime =  '" + dateFormat3 + "', " +
//                        "ItemDiscountAmount =  '" + String.format("%.2f", Double.valueOf(itemDiscount)) + "', " +
//                        "ProductID =  '" + productID + "', " +
//                        "CategoryID =  '" + c_cate_catID + "', " +
//                        "CategoryName =  '" + c_cate_catName + "', " +
//                        "vchQueueNo =  '" + vchQueueNo.replace("'", "") + "', " +
//                        "intTableNo =  '" + intTableNo.replace("'", "") + "', " +
//                        "Cancel =  '" + str_Cancel + "', " +
//                        "BillID =  '" + billId + "', " +
//                        "TaxID =  '" + taxID + "', " +
//                        "TaxType =  '" + str_Cancel + "', " +
//                        "TaxName =  '" + taxType + "', " +
//                        "TaxAmount =  '" + val + "', " +
//                        "DiscountID =  '" + slddisID + "', " +
//                        "DiscountName =  '" + slddisName + "', " +
//                        "DiscountType =  '" + slddisType + "', " +
//                        "DiscountTypeName =  '" + slddisTypeName + "', " +
//                        "DiscountValue =  '" + val2 + "', " +
//                        "AfterDiscountAmount =  '" + val3 + "', " +
//                        "OpenPriceStatus =  '" + openPriceStatus + "', " +
//                        "BeforeOpenPrice =  '" + String.format("%.2f",  Double.valueOf(beforeOpenPrice)) + "', " +
//                        "DateTime =  " + System.currentTimeMillis() + " " ;
//                if (EditProductSheetFragment.ID != null && Integer.parseInt(EditProductSheetFragment.ID) > 0){
//                    str_update_str += "WHERE ID = " + EditProductSheetFragment.ID ;
//
//                }else {
//                    str_update_str += " WHERE BillNo = '" + billNo + "' " +
//                " AND ProductID = '" + productID + "' ";
//
//                }
//
////                        "WHERE BillNo = '" + billNo + "'  AND ProductId = '"+productID+"' " ;
//
//
//
//                Log.i("EnPrice___","EditFragmentOpenPriceeeeq111______"+EditFragmentOpenPrice);
//                Log.i("EnPrice___","EditFragmentRemarksq111______"+EditFragmentRemarks);
//
////                if (EditFragmentOpenPrice != null && EditFragmentOpenPrice.trim().length() > 0
////                        && !(EditFragmentOpenPrice.equals("0"))
////                        && !(EditFragmentOpenPrice.trim() == null)
////                        && !(EditFragmentOpenPrice.trim().equals("null"))) {
////                    str_update_str += " AND OpenPriceStatus = '"+openPriceStatus+"'" ;
////                    EditFragmentOpenPrice = "0";
////                } else {
////                    str_update_str += " AND ( OpenPriceStatus IS NULL OR OpenPriceStatus = '' OR OpenPriceStatus = 'null' OR OpenPriceStatus = '0' ) ";
////                    EditFragmentOpenPrice = "0";
////                }
////
////                if (EditFragmentRemarks != null && EditFragmentRemarks.trim().length() > 0 && !(EditFragmentRemarks.equals("0"))
////                        && !(EditFragmentRemarks.trim() == null)
////                        && !(EditFragmentRemarks.trim().equals("null"))) {
//////                         deletesql += " AND Remarks IS NULL ";
////                    str_update_str += " AND Remarks = '"+EditFragmentRemarks+"'" ;
////                    EditFragmentRemarks = "0";
////                } else {
////                    str_update_str += " AND ( Remarks IS NULL OR Remarks = '' OR Remarks = 'null' ) ";
////                    EditFragmentRemarks = "0";
////                    }
////
//////                updateee_UPDATE DetailsBillProduct SET BillNo =  'FSTF00000006', OnlineOrderBill =  'OFF', ProductQty =  '3', ProductName =  'TESTING SERVICE – ART', ProductPrice =  '15.00', BillDateTime =  '21 Sep 2021  09:44 PM', ItemDiscountAmount =  '0.00', ProductID =  '8', CategoryID =  '4', CategoryName =  'DEFAULT', vchQueueNo =  '0, ', intTableNo =  '0, ', Cancel =  'SALES', BillID =  '6', TaxID =  '0', TaxType =  'SALES', TaxName =  '', TaxAmount =  '0.00', DiscountID =  '0', DiscountName =  '0', DiscountType =  '0', DiscountTypeName =  '0', DiscountValue =  '0', AfterDiscountAmount =  '15.00', OpenPriceStatus =  '0', BeforeOpenPrice =  '0.00', DateTime =  1632231880761 WHERE BillNo = 'FSTF00000006'  AND ProductId = '8'  AND ( OpenPriceStatus IS NULL OR OpenPriceStatus = '' OR OpenPriceStatus = 'null' OR OpenPriceStatus = '0' )  AND Remarks = 'BB'
//////                AND Remarks = '"+remarks+"'
//                Log.i("DFD___", "DFDF_updateee_" + str_update_str);
//                DBFunc.ExecQuery(str_update_str, false);
//
//                String productUUID = TransactionDetailsActivity.ValidAndGetValue("UUID", "PLU", "ID", productID, true);
//
//                Query.updatePriceDetailsBillProduct(productUUID,billNo,openPriceStatus,"edit",pr,EditProductSheetFragment.ID);
//            } else {
//                String uniqueId = UUID.randomUUID().toString();
//                sql = "INSERT INTO DetailsBillProduct (UUID,BillNo,OnlineOrderBill,ProductQty,ProductName,ProductPrice,BillDetailsID,BillDateTime," +
//                        "ItemDiscountAmount,ProductID,CategoryID,CategoryName,vchQueueNo,intTableNo,Cancel," +
//                        "BillID,TaxID,TaxType,TaxName,TaxAmount,DiscountID,DiscountName,DiscountType,DiscountTypeName,DiscountValue," +
//                        "AfterDiscountAmount,OpenPriceStatus,BeforeOpenPrice,DateTime) VALUES (";
////                Remarks,
//                sql += "'" + uniqueId + "', ";
//                sql += "'" + billNo + "', ";
//                sql += "'" + onlineOrderBill + "', ";
//                sql += "'" + productQty + "', ";
////        sql += "'" + productID + "', ";
//                sql += "'" + productName + "', ";
//                sql += "'" + String.format("%.2f", pr) + "', ";
//                sql += "'" + 0 + "', ";
//                sql += "'" + dateFormat3 + "', ";
//                sql += "'" + String.format("%.2f",  Double.valueOf(itemDiscount)) + "', ";
//                sql += "'" + productID + "', ";
//                productID = "0";
//                sql += "'" + c_cate_catID + "', ";
//                sql += "'" + c_cate_catName + "', ";
//                sql += "'" + vchQueueNo.replace("'", "") + "', ";
//                sql += "'" + intTableNo.replace("'", "") + "', ";
//                sql += "'" + str_Cancel + "', ";
//                sql += "'" + billId + "', ";
//                sql += "'" + taxID + "', ";
//                sql += "'" + taxType + "', ";
//                sql += "'" + taxName + "', ";
//                if (taxItemAmount.toString().length() > 0) {
//                    sql += "'" + String.format("%.2f", Double.valueOf(taxItemAmount)) + "', ";
//                } else {
//                    sql += "'0', ";
//                }
//                sql += "'" + slddisID + "', ";
//                sql += "'" + slddisName + "', ";
//                sql += "'" + slddisType + "', ";
//                sql += "'" + slddisTypeName + "', ";
//                if (discountValue.length() > 0) {
//                    sql += "'" + String.format("%.2f",   Double.valueOf(discountValue)) + "', ";
//                } else {
//                    sql += "'0', ";
//                }
//                if (after_dis_amt.toString().length() > 0) {
//                    sql += "'" + String.format("%.2f",  Double.valueOf(after_dis_amt)) + "', ";
//                } else {
//                    sql += "'0', ";
//                }
//                sql += "'" + openPriceStatus + "', ";
//
//                sql += "'" + String.format("%.2f",   Double.valueOf(beforeOpenPrice)) + "', ";
//
////                sql += "'" + itemRemarksPLU + "', ";
//
//                sql += System.currentTimeMillis() + ")";
//
//                Log.i("DFD___", "DFDF__" + sql);
//                DBFunc.ExecQuery(sql, false);
//            }
//
//
//            String bId = findBillIDByBillNo(billNo);
//            String str_update_str = "UPDATE DetailsBillProduct SET BillID =  '" + bId + "' WHERE BillNo = '" + billNo + "' ";
//            DBFunc.ExecQuery(str_update_str, false);
//
//
//            //String BillPLU_idx = String.valueOf(productID);
//            Integer product_taxID = SearchProductTaxIDByProductID(productID);
//            //Integer ttaxID = GetTaxById(product_taxID);
//            Integer ttaxID = 0;
//            String ttaxName = "0";
//            String ttaxAcronym = "0";
//            String ttaxRate = "0";
//            Integer ttaxType = 0;
//            Integer ttaxSeq = 0;
//            String sql__ = "SELECT ID,Name,Acronym,Rate,Type,Seq FROM Tax WHERE ID = " + product_taxID;
//            Cursor Cursor_Tax = DBFunc.Query(sql__, true);
//            if (Cursor_Tax != null) {
//                if (Cursor_Tax.moveToNext()) {
//                    ttaxID = Cursor_Tax.getInt(0);
//                    ttaxName = Cursor_Tax.getString(1);
//                    ttaxAcronym = Cursor_Tax.getString(2);
//                    ttaxRate = Cursor_Tax.getString(3);
//                    ttaxType = Cursor_Tax.getInt(3);
//                    ttaxSeq = Cursor_Tax.getInt(4);
//                }
//                Cursor_Tax.close();
//            }
//            double ttotal_tax = 0.0;
//            if (ttaxType.equals("1")) {
//                ttotal_tax = 0.0;
//                ttotal_tax = Query.calculateInclusive(after_diss_amt, Integer.parseInt(ttaxRate));
//                //String str_inclusive =  taxName.toUpperCase() + "(" + taxRate+"%)" +" SGD "+"$" + String.format("%.2f", amt_inclusive) + " Incl";
//                //str += "\n" + str_inclusive;
//            } else {
//                ttotal_tax = 0.0;
//                ttotal_tax = calculateExclusive(after_diss_amt, Integer.parseInt(ttaxRate));
//            }
//
//            Query.BillPLUTax(Integer.valueOf(productID), ttaxID, ttaxName, ttaxAcronym, Double.valueOf(ttaxRate), ttaxType, ttaxSeq, ttotal_tax);
//            Query.DeleteProductQty(billNo);
//
//
//            String sqlll = "SELECT BillNo FROM BillPayment WHERE BillNo = '" + billNo + "' ";
//            Cursor csqlll = DBFunc.Query(sqlll, false);
//            if (csqlll != null) {
//                if (csqlll.getCount() == 0) {
//
//                    String BillID = findBillIDByBillNo(billNo);
//                    String STATUS = "PENDING";
////        String fieldName,String IdName, String Id,String tbName, Boolean flag
//                    String TotalItems = "0";
//                    String TableNo = "0";
//                    String QueueNo = "0";
//                    String TotalAmount = "0";
//                    String OnlineOrderBill = "0";
//                    String sqql = "SELECT SUM(ProductQty), SUM(ProductPrice),OnlineOrderBill,vchQueueNo,intTableNo,Cancel,SUM(ItemDiscountAmount) " +
//                            " FROM DetailsBillProduct Where BillNo = '" + billNo + "' " +
//                            " group by BillNo order by BillNo DESC";
//                    Cursor Cursor_DBP = DBFunc.Query(sqql, false);
//                    if (Cursor_DBP != null) {
//                        if (Cursor_DBP.moveToNext()) {
//                            TotalItems = Cursor_DBP.getString(0);
//                            Double exact_price = Cursor_DBP.getDouble(1) - Cursor_DBP.getDouble(6);
//                            TotalAmount = String.valueOf(exact_price);
//                            OnlineOrderBill = Cursor_DBP.getString(2);
//                            QueueNo = Cursor_DBP.getString(3);
//                            TableNo = Cursor_DBP.getString(4);
////                        STATUS = Cursor_DBP.getString(5);
//                        }
//                        Cursor_DBP.close();
//                    }
//                    DateFormat dateFormat55 = new SimpleDateFormat("dd MMM yyyy  hh:mm aa");
//                    String Date = dateFormat55.format(new Date()).toString();
//                    BillListModel blist = new BillListModel(BillID, billNo, STATUS, TotalItems, Date, TableNo, QueueNo, OnlineOrderBill, TotalAmount, 0);
//                    Log.i("Dfd_____STATUSdd", "STATUSdd___" + STATUS);
//
//                    UpdateBillList(blist, billNo);
//                }
//                csqlll.close();
//            }
//
//            //String productUUID = TransactionDetailsActivity.ValidAndGetValue("UUID", "PLU", "ID", productID, true);
//
//           // Query.updatePriceDetailsBillProduct(productUUID,billNo,openPriceStatus);
//            String productUUID = TransactionDetailsActivity.ValidAndGetValue("UUID", "PLU", "ID", productID, true);
//
//            Query.updatePriceDetailsBillProduct(productUUID,billNo,openPriceStatus,"edit",pr,EditProductSheetFragment.ID);
//
//            EditProductSheetFragment.modiID.clear();
//            EditProductSheetFragment.modiName.clear();
//            EditProductSheetFragment.modiPrice.clear();
//            EditProductSheetFragment.UnSelectedItem.clear();
//            EditProductSheetFragment.EditFragmentRemarks = "";
//        }
//    }

    public static Cursor GetTaxById(Integer taxID) {
//        Integer tax_ID = 0;
//        String sql = "SELECT Type FROM Tax WHERE ID = "+product_taxID;
//        Cursor Cursor_Tax = DBFunc.Query(sql,true);
//        if (Cursor_Tax != null){
//            if (Cursor_Tax.moveToNext()){
//                tax_ID = Cursor_Tax.getInt(0);
//            }
//            Cursor_Tax.close();
//        }
//        return tax_ID;
        String str_tax = "Select Type,Rate from Tax Where ID = " + taxID;

        return  DBFunc.Query(str_tax,true);
    }

    private static Integer SearchProductTaxIDByProductID(String productID) {
        Integer tax_ID = 0;
        String sql = "SELECT ProductTaxID FROM PLU WHERE ID = "+productID;
        Cursor Cursor_Tax = DBFunc.Query(sql,true);
        if (Cursor_Tax != null){
            if (Cursor_Tax.moveToNext()){
                tax_ID = Cursor_Tax.getInt(0);
            }
            Cursor_Tax.close();
        }
        return tax_ID;
    }

    public static String GetValue(int s_Size, String i_Index) {
        String str_value = "0";
        if (s_Size > 0) {
            if (i_Index == null) {
                str_value = "'0', ";
            } else {
                str_value = "'" + i_Index + "', ";
            }
        }else {
            str_value = "'0', ";
        }
        return str_value;
    }

    public static void ZClose(Context context) {
        String sqlisZ1 = "Select BillNo,UUID from Sales WHERE isZ IS NULL Order By BillNo ASC";
        Cursor sqlSalesZClose = DBFunc.Query(sqlisZ1,false);
        String billNoAllStr = "";
        String billNoAllStr1 = "";
        String billNoAllStr2 = "";
        String opendtStr = "";
        String firstBillNoZClose = "";
        String firstUUIDZClose = "";
        String lastBillNoZClose = "";
        String lastUUIDZClose = "";
        if (sqlSalesZClose != null){
            //String jerifood_tax = Query.GetOptions(24);
//            if (sqlSalesZClose.moveToNext()) {
            while (sqlSalesZClose.moveToNext()) {
                String sqlisZ = "Select BillID,BillNo from BillList WHERE BillNo = '" + sqlSalesZClose.getString(0) + "' ";
                Cursor sqlBillZClose = DBFunc.Query(sqlisZ, false);
                if (sqlBillZClose != null) {
                    billNoAllStr = "";
                    billNoAllStr1 = "";
                    billNoAllStr2 = "";
                    while (sqlBillZClose.moveToNext()) {

                        Log.i("SDFDFSD_____","sqlisZC___"+sqlBillZClose.getString(0)+"__"+sqlBillZClose.getString(1)+"__"+sqlSalesZClose.getString(0));

                        billNoAllStr += sqlBillZClose.getString(0);
                        billNoAllStr1 += "'" + sqlBillZClose.getString(0) + "'";
                        billNoAllStr2 += "'" + sqlBillZClose.getString(1) + "'";
                        //if (sqlBillZClose.isFirst()) {
                        if (sqlSalesZClose.isFirst()) {
                            opendtStr = sqlBillZClose.getString(0);
                            firstBillNoZClose = sqlSalesZClose.getString(0);
                            firstUUIDZClose = sqlSalesZClose.getString(1);
                        }
                        Log.i("firstBillNoZClose_","firstBillNoZClose__"+firstBillNoZClose);
                        Log.i("firstBillNoZClose_","firstUUIDZClose__"+firstUUIDZClose);
                        if (!sqlSalesZClose.isLast()) {
                        //if (!sqlBillZClose.isLast()) {
                            billNoAllStr += ",";
                            billNoAllStr1 += ",";
                            billNoAllStr2 += ",";
                        }else {
                            lastBillNoZClose = sqlSalesZClose.getString(0);
                            lastUUIDZClose = sqlSalesZClose.getString(1);

                            Log.i("firstBillNoZClose_","lastBillNoZClose__"+lastBillNoZClose);
                            Log.i("firstBillNoZClose_","lastUUIDZClose__"+lastUUIDZClose);
                        }

                    }
                    sqlBillZClose.close();
                }
            }
            sqlSalesZClose.close();
        }
        //String sqlisBZ = "Select OpenDateTime from Bill WHERE TransNo = '"+opendtStr+"'";
//        String sqlisBZ = "Select DateTime from Sales WHERE BillNo = '"+opendtStr+"'";
        String sqlisBZ = "Select DateTime from Sales WHERE BillNo = '"+firstBillNoZClose+"'";

        Cursor sqlisZBC = DBFunc.Query(sqlisBZ,false);
        long opendtInt = 0;
        if (sqlisZBC != null){
            if(sqlisZBC.moveToNext()){
                opendtInt = sqlisZBC.getLong(0);

            }
            sqlisZBC.close();
        }

        if (billNoAllStr != null && billNoAllStr.length() > 0) {
            String zclosesql = "INSERT INTO ZClosing (AllBillNo,PrintedCount,OpeningTime,ClosingTime,DateTime) " +
                    "VALUES ('" + billNoAllStr + "'," +
                    1 + "," +
                    opendtInt + "," +
                    System.currentTimeMillis() + "," +
                    System.currentTimeMillis() + ")";

            DBFunc.ExecQuery(zclosesql, false);

        }

        String retailID = "";
        String terminalID = "";
        Cursor Cursor_Possys = Query.GetURLAndCodeFromPossys();
        if (Cursor_Possys != null) {
            while (Cursor_Possys.moveToNext()) {
                retailID  = Cursor_Possys.getString(0);
                terminalID = Cursor_Possys.getString(6);
            }
            Cursor_Possys.close();
        }
        Log.i("billNoAllStr2__","billNoAllStr2___"+billNoAllStr2);
        Log.i("billNoAllStr2__","opendtInt___"+opendtInt);
        ZClose zc = new ZClose();
        zc.setAllBillNo(billNoAllStr2);
        zc.setOpeningTime(opendtInt);
        zc.setClosingTime(System.currentTimeMillis());
        zc.setPrintedCount(1);
        String uuid = UUID.randomUUID().toString();
        zc.setUUID(uuid);
        zc.setRetailID(retailID);
        zc.setTransIDFrom(firstUUIDZClose);
        zc.setTransIDTo(lastUUIDZClose);
        zc.setTransNoFrom(firstBillNoZClose);
        zc.setTransNoTo(lastBillNoZClose);

        String transDate = new SimpleDateFormat(Constraints.dateYMD_Sync).format(new Date());
        String transZReadDate = new SimpleDateFormat(Constraints.dateYMD_YMDHMS).format(new Date());
        zc.setTransDate(transDate);

        Integer zcloseidchk = Query.findLatestID("ID", "ZClose", false);
        String zreadno = Constraints.Zeroes;
        if (zcloseidchk > 0) {
            Integer latest_zreadidchk = Query.findLatestID("ID", "ZClose", false);

            int reduce = zreadno.length() - String.valueOf(latest_zreadidchk).length();
            String loopval = "";
           for (int reducei = 0 ; reducei < reduce ; reducei ++) {
               loopval += "0";
           }
            zreadno =  loopval + latest_zreadidchk;

        }
        zc.setZReadNo(Constraints.ZReadNoFormat + zreadno);
        zc.setZReadDate(transZReadDate);
        zc.setZReadUser("");
        zc.setTerminalID(terminalID);
        zc.setPendingSync("");
        zc.setLastUser("");
        zc.setLockUser("");
        zc.setLockUpdate(transZReadDate);
        zc.setLockStatus("");
        zc.setRecordStatus("");
        zc.setRecordUpdate(transZReadDate);
        zc.setQueueStatus("");
        zc.setDt(System.currentTimeMillis());

        Query.saveZClose(context,zc);

        DBFunc.ExecQuery("UPDATE Sales SET isZ = 'Z'  WHERE isZ IS NULL",false);

//        DBFunc.ExecQuery("UPDATE Sales SET isZ = 'Z' ",false);
//        Query.UpdateBillPayment();

        Integer billID = Query.findLatestID("BillNo","Bill",false);

        String BNo = Query.findBillNoByBillID(billID);

        Boolean bno = false;
        try {


            if (BNo.equals(MainActivity.strbillNo)) {
                bno = true;
            } else {
                bno = false;
            }
        }catch (NullPointerException e){
            bno = false;
        }
        Boolean checkBillPending = Query.CheckBillListStatus(bno,BNo);

//        if (checkBillPending == true) {
        if (checkBillPending) {
            DBFunc.ExecQuery("UPDATE BillList SET isZ = 'Z' WHERE BillNo != '"+BNo+"' AND isZ IS NULL",false);
            DBFunc.ExecQuery("UPDATE BillList SET isZ = 'Z' WHERE STATUS = 'REFUND'",false);
        }else {
            DBFunc.ExecQuery("UPDATE BillList SET isZ = 'Z' WHERE isZ IS NULL",false);
            DBFunc.ExecQuery("UPDATE BillList SET isZ = 'Z' WHERE STATUS = 'REFUND'",false);
        }

        String sql = "UPDATE Sales SET isZ = 'Z' WHERE BillNo IN ("+billNoAllStr2+")";

        DBFunc.ExecQuery(sql,false);

        //String sql_update_billpayment = "UPDATE BillPayment SET isZ = 'Z' WHERE BillNo IN ("+billNoAllStr2+")";
        String sql_update_billpayment = "UPDATE BillPayment SET isZ = 'Z' WHERE isZ IS NULL";

        DBFunc.ExecQuery(sql_update_billpayment,false);
        String sql_update_billpayment1 = "UPDATE BillPayment SET isZ = 'Z' WHERE isZ = 'null'";

        DBFunc.ExecQuery(sql_update_billpayment1,false);

        DBFunc.ExecQuery("UPDATE Sales SET isZ = 'Z' WHERE isZ IS NULL",false);

        Query.UpdateBillPayment(BNo);

        try {

            SyncActivity.passwordResendSalesDialog(context,"",0, "0", "0",
                    0, "0", "0","Z");
        }catch (java.lang.RuntimeException e){
            Log.i("Error","er_repotyz"+e.getMessage());
        } catch (Exception e){
            Log.i("Error","er_repotyzzz"+e.getMessage());
        }

//        ReportActivity.binding.pagerReport.setCurrentItem(3);
//        ReportActivity.binding.pagerReport.setCurrentItem(3);
//        ReportActivity.binding.pagerReport.setCurrentItem(3);
        Log.i("checkingZClose___","checkingZClose__ddd__"+Constraints.ZClose);
//        ReportXFragment.updateReportFragmentXFun(Constraints.ZClose);

//        ReportActivity.updateMediaButtons();
//        ArrayList<String> reportProductNameArrt = new ArrayList<String>();
//        //binding.ReportProductListView.removeAllViewsInLayout();
//        ReportXFragment.binding.ReportProductListView.removeAllViewsInLayout();
//        ReportXAdapter customAdapter = new ReportXAdapter(context, reportProductNameArrt, reportProductNameArrt, reportProductNameArrt);
////            binding.ReportProductListView.removeAllViews(); // java.lang.UnsupportedOperationException: removeAllViews() is not supported in AdapterView
////            binding.ReportProductListView.refreshDrawableState();
//        ReportXFragment.binding.ReportProductListView.setAdapter(customAdapter);
////            customAdapter.notifyDataSetInvalidated();
//        customAdapter.notifyDataSetChanged();
    }

    private static String getStatusfromBillList(String bNo) {
        String status = "0";
        String sql = "SELECT STATUS FROM BillList WHERE BillNo = '"+bNo+"' ";
        Cursor c = DBFunc.Query(sql,false);
        if (c != null){
            if (c.moveToNext()){
                status = c.getString(0);
            }
            c.close();
        }
        return status;
    }

    public static String getBillNofromDetailsBillProduct(String bNo) {
        String status = "";
        //String sql = "SELECT Cancel FROM Details_BillProduct WHERE BillNo = '"+bNo+"' ";
        String sql = "SELECT Cancel FROM DetailsBillProduct WHERE BillNo = '"+bNo+"' LIMIT 1";
        Cursor c = DBFunc.Query(sql,false);
        if (c != null){
            if (c.moveToNext()){
            //while (c.moveToNext()){
                status = c.getString(0);
            }
            c.close();
        }
        return status;
    }

    public static Bitmap GetLogPrint() {
        Bitmap bitmap__ = null;
        String sql = "SELECT Logo,ImageStatus FROM ReceiptEditor";

        Cursor Cursor_ReceiptHeader = DBFunc.Query(sql,true);
        if (Cursor_ReceiptHeader != null){
            if (Cursor_ReceiptHeader.moveToNext()){
                //tmap__ = Cursor_ReceiptHeader.getString(0);

                if (Cursor_ReceiptHeader.getInt(1) == 1) {
                    try {

                        byte[] decodedString = Base64.decode(Cursor_ReceiptHeader.getString(0), Base64.DEFAULT);
                        bitmap__ = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                        toGrayscale(bitmap__);

                    } catch (NullPointerException e) {
                        Log.e("DNullPointerException", e.getMessage());
                    }
                }else {
                    bitmap__ = null;
                }
            }
            Cursor_ReceiptHeader.close();
        }
        return bitmap__;
    }

    public static Bitmap toGrayscale(Bitmap srcImage) {

        Bitmap bmpGrayscale = Bitmap.createBitmap(srcImage.getWidth(), srcImage.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bmpGrayscale);
        Paint paint = new Paint();

        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        paint.setColorFilter(new ColorMatrixColorFilter(cm));
        canvas.drawBitmap(srcImage, 0, 0, paint);

        return bmpGrayscale;
    }

    public static Cursor GetPaymentInfoAll() {
        String sql = "SELECT ID, Name, switchSTATUS, disallowEmptyCash, linktoPaymentApp, integratetoShoptima , " +
                "RoundingActivate, FullAmount,Ezlink" +
                " from Payment " ;
//                "oundingActivate from Payment LIMIT 3";

        return  DBFunc.Query(sql, true);
    }

    public static String GetRoundActivateInfo(String paymentID) {
        String roundActivateStr = "0";
        String sql = "SELECT RoundingActivate FROM Payment WHERE ID = "+Integer.parseInt(paymentID);
        Log.i("Dsf___","sql___"+sql);
        Cursor c = DBFunc.Query(sql,true);
        if (c != null){
            if (c.moveToNext()){
                roundActivateStr = c.getString(0);
            }
            c.close();
        }else {
            roundActivateStr = "0";
        }
        return roundActivateStr;
    }

    public static String DoubleAmountFormat(Double amt) {
        return String.format("%.2f", Double.valueOf(amt));
    }

    public static ArrayList<String> GetSalesArr(Cursor Cursor_SalesAddBillNo){
        ArrayList<String> billSalesArrList = new ArrayList<String>();
        if (Cursor_SalesAddBillNo != null){
            billSalesArrList.clear();
            //lstBillDetails.clear();
            while (Cursor_SalesAddBillNo.moveToNext()){
                String billNo = Cursor_SalesAddBillNo.getString(0);
                billSalesArrList.add(billNo);

            }
            Cursor_SalesAddBillNo.close();
        }
        return billSalesArrList;
    }

    public static List<BillDetails> GetBillListData(ArrayList<String> billSalesArrList,String queryStr) {
        List<BillDetails> lstBillDetails = new ArrayList<BillDetails>();
        String sql = "SELECT BillNo,(ProductQty)," +
                "(ProductPrice),BillDateTime,ID,(ItemDiscountAmount),TaxAmount,TaxID,ProductQty " +
                "FROM DetailsBillProduct Where OnlineOrderBill = 'OFF' " +
                queryStr +
                "group by BillNo order by BillNo DESC";
//        String sql = "SELECT BillNo,SUM(ProductQty)," +
//                "SUM(ProductPrice),BillDateTime,ID,SUM(ItemDiscountAmount),TaxAmount,TaxID,ProductQty " +
//                "FROM DetailsBillProduct Where OnlineOrderBill = 'OFF' " +
//                queryStr +
//                "group by BillNo order by BillNo DESC";

        Cursor c = DBFunc.Query(sql, false);
        if (c != null) {
            lstBillDetails.clear();
            while (c.moveToNext()) {
                if (!c.isNull(0)) {

                    if (!billSalesArrList.contains(c.getString(0))) {

                        //Double exact_price = c.getDouble(2) - c.getDouble(5);
                        Double exact_price = c.getDouble(2) - (c.getDouble(1) * c.getDouble(5));

                        String s_status = "0";
                        Integer sale_id = Query.getSalesIDByBillNo(c.getString(0));
                        if (sale_id > 0) {
                            s_status = Query.getSalesStatusBySaleID(sale_id);
                        } else {
                            s_status = "0";
                        }
                        Double taxAmt = c.getDouble(6);
                        Integer taxID = c.getInt(7);
                        String str_tax = "Select Type from Tax Where ID = " + taxID;
                        Cursor CursortaxObj = DBFunc.Query(str_tax, true);

                       if (CursortaxObj != null) {
                            if (CursortaxObj.moveToNext()) {
                                //sldCTaxType.add(CursortaxObj.getString(0));
                                if (CursortaxObj.getString(0).equals("1")) {
                                    exact_price = exact_price;
                                } else {
                                    exact_price = exact_price + taxAmt;
                                }
                            }
                            CursortaxObj.close();
                        } else {
                            exact_price = exact_price;
                        }

                        String str_billdis = "Select TotalBillDisount,ServiceCharges from Sales Where BillNo = '" + c.getString(0) + "'";
                        Cursor CursorBillDisObj = DBFunc.Query(str_billdis, false);
                        if (CursorBillDisObj != null) {
                            if (CursorBillDisObj.moveToNext()) {
                                //sldCTaxType.add(CursortaxObj.getString(0));
                                exact_price = exact_price - CursorBillDisObj.getDouble(0);
                                exact_price = exact_price + CursorBillDisObj.getDouble(1);

                            }
                            CursorBillDisObj.close();
                        } else {
                            exact_price = exact_price;
                        }

                        if (c.getInt(8) == -1){
                            lstBillDetails.add(new BillDetails(c.getString(0),
                                    0,
                                    String.valueOf(exact_price), c.getString(3), c.getInt(4), s_status, "0", "0"));
                        }else {
                            lstBillDetails.add(new BillDetails(c.getString(0),
                                    c.getInt(1),
                                    String.valueOf(exact_price), c.getString(3), c.getInt(4), s_status, "0", "0"));
                        }
                    }
                }
            }
            // billNo,  totalItems,  totalAmount,  dateTime,  ID
            c.close();
        }
        return lstBillDetails;
    }

    public static Cursor GetSales(String queryStr) {
        String sql = "SELECT Sales.BillNo,Sales.STATUS,Sales.TotalQty," +
                "Sales.TotalNettSales,Sales.SalesDateTime,Sales.ID" +
                " FROM Sales " +
                " INNER JOIN DetailsBillProduct on DetailsBillProduct.BillNo = Sales.BillNo " +
                queryStr + "  AND DetailsBillProduct.OnlineOrderBill = 'OFF' " +
                " group by Sales.BillNo order by Sales.BillNo DESC ";

        return  DBFunc.Query(sql,false);
    }

    public static String getCancelByBillNo(String billNo){
        String status = "0";
        //String sql = "SELECT OnlineOrderBill FROM Bill Where BillNo = '"+ billNo +"' ";
        String sql = "SELECT Cancel FROM DetailsBillProduct Where BillNo = '"+ billNo +"' ";

        Cursor c = DBFunc.Query(sql, false);
        if (c != null) {
            status = "0";
            if (c.moveToNext()) {
                if (!c.isNull(0)) {
                    status = c.getString(0);
                }
            }
            c.close();
        }
        return status;
    }

    public static List<BillDetails> GetBillListDataSales(List<BillDetails> lstBillDetails, Cursor Cursor_Sales) {
        //List<BillDetails> lstBillDetails = new ArrayList<BillDetails>();
        if (Cursor_Sales != null){
            //billSalesArrList.clear();
            String billNo = "";
            String saleStatus = "";
            Integer saleQty = 0;
            String saleNettAmt = "";
            String saleDateTime = "";
            Integer saleID = 0;
            //lstBillDetails.clear();
            while (Cursor_Sales.moveToNext()){
                billNo = Cursor_Sales.getString(0);
                saleStatus = Cursor_Sales.getString(1);
                saleQty = Cursor_Sales.getInt(2);
                saleNettAmt = Cursor_Sales.getString(3);
                saleDateTime = Cursor_Sales.getString(4);
                saleID = Cursor_Sales.getInt(5);
                //billSalesArrList.add(billNo);
                lstBillDetails.add(new BillDetails
                        (billNo,saleQty,saleNettAmt,saleDateTime,saleID,saleStatus,"0","0"));
            }
            Cursor_Sales.close();
        }
        return lstBillDetails;
    }

    public static Cursor SearchDiscountFromDetailsBillProductBy(String strbillNo,String productID) {
//       String sql = " SELECT DiscountID,DiscountName,DiscountType,DiscountTypeName,DiscountValue,ItemDiscountAmount," +
//               "DiscountTypeName " +
//                "FROM DetailsBillProduct Where BillNo = '" + strbillNo + "' AND Cancel = 'SALES' " +
//               "AND ProductID = '"+productID+"' " +
//               "order by ID desc ";

                String sql = " SELECT DiscountID,DiscountName,DiscountType,DiscountTypeName,DiscountValue,ItemDiscountAmount," +
                "DiscountTypeName " +
                "FROM DetailsBillProduct Where BillNo = '" + strbillNo + "' AND Cancel = 'SALES' " +
                "AND ProductID = '"+productID+"' " +
                "order by ID desc ";

       return DBFunc.Query(sql,false);

//        DiscountID,DiscountName,DiscountType,DiscountTypeName,DiscountValue
    }

    public static Cursor GetTax() {
        String sql = "SELECT Tax.Rate, Tax.Type, Tax.Name " +
                "  FROM Tax " +
                "  inner join TaxType on TaxType.ID = Tax.Type Where ServiceCharges = 0 ";
//        if (taxID > 0){
//            sql += " WHERE Tax.ID = "+taxID;
//        }
        Log.i("TAX__","tax___"+sql);
        return DBFunc.Query(sql,true);

    }
    public static Cursor GetServiceCharges() {
        String sql = "SELECT Tax.Rate, Tax.Type, Tax.Name " +
                "  FROM Tax " +
                "  inner join TaxType on TaxType.ID = Tax.Type Where ServiceCharges = 1 ";
        return DBFunc.Query(sql,true);

    }

    public static double CalculationTaxFunction(double total_amt, LinearLayout inc_tax_layout,
                                              TextView inc_taxname, LinearLayout tax_layout, TextView taxname, TextView tax_value) {
        double amt_tax = 0.0;
        double amt_inclusive = 0.0;
        double amt_exclusive = 0.0;
        Integer taxRate = 0;
        String str_inclusive = "0";
        String str_exclusive = "0";
        String str_exc_taxname = "0";
        String str_inc_taxname = "";

//        Cursor Cursor_serv_charges = Query.GetServiceCharges();
//        if (Cursor_serv_charges == null){
            Cursor Cursor_tax = Query.GetTax();
            if (Cursor_tax != null){
                if (Cursor_tax.moveToNext()){
                    taxRate = Cursor_tax.getInt(0);
                    Integer taxType = Cursor_tax.getInt(1);
                    str_inc_taxname = Cursor_tax.getString(2);
                    str_exc_taxname = Cursor_tax.getString(2);
                    Log.i("DFD___","taxType__ddd_"+taxType);
                    //1=> None , 2=>Inclusive , 3=>Exclusive
                    if (taxType == 2){
                        amt_inclusive = 0.0;
                        amt_inclusive = calculateInclusive(total_amt,taxRate);
                        amt_exclusive = 0.0;
                    }else if (taxType == 3) {
                        amt_exclusive = 0.0;
                        amt_exclusive = calculateExclusive(total_amt,taxRate);
                        amt_inclusive = 0.0;
                    }
                }
                Cursor_tax.close();
            }
            if (amt_inclusive != 0.0){
                //amt_tax = amt_inclusive;
                //str_inclusive =  str_inc_taxname.toUpperCase() + " SGD "+"$" + String.format("%.2f", amt_inclusive) + " Incl";
                //str_inclusive =  str_inc_taxname.toUpperCase() + "(" + taxRate +"%)" +" SGD "+"$" + String.format("%.2f", amt_inclusive) + " Incl";
                str_inclusive =  str_inc_taxname.toUpperCase() + "(" + taxRate +"%)" +"$" + String.format("%.2f", amt_inclusive) + " Incl";
                inc_tax_layout.setVisibility(View.VISIBLE);
                tax_layout.setVisibility(View.GONE);
                inc_taxname.setText(str_inclusive);
            }
            if (amt_exclusive != 0.0){
                inc_tax_layout.setVisibility(View.GONE);
                amt_tax = amt_exclusive;
                str_exclusive = "$" + String.format("%.2f", Double.valueOf(amt_exclusive));
                tax_layout.setVisibility(View.VISIBLE);
                str_exc_taxname = str_exc_taxname.toUpperCase()+ "(" + taxRate+"%)";
                taxname.setText(str_exc_taxname);
                //taxname.setTextSize(MainActivity.billFontSize);
                tax_value.setVisibility(View.VISIBLE);
                tax_value.setText(str_exclusive);
            }
//            Cursor_serv_charges.close();
//        }
        return amt_tax;
    }


    public static double calculateInclusive(double amount, Integer taxRate) {
        double taxRatePercentage = taxRate / 100f;//7 / 100 = 0.07
        taxRatePercentage = 1.00 + taxRatePercentage; // 1.07
//        1.01 -(1.01 /1.07)
        amount = amount - (amount / taxRatePercentage);
        return amount;
    }
    public static double calculateExclusive(double amount, Integer taxRate) {
        double taxRatePercentage = taxRate / 100f;//7 / 100 = 0.07
//        = 7/100
        amount = amount * taxRatePercentage; //100 * 7/100
        return amount;
    }

    public static double CalculationServiceCharges(LinearLayout service_charges_layout, TextView service_chargesname, Double sub_total, TextView service_charges_value) {
        Double service_charges = 0.0;
        Integer taxRate = 0;
        Cursor Cursor_tax = Query.GetServiceCharges();
        if (Cursor_tax != null){
            if (Cursor_tax.moveToNext()){
                taxRate = Cursor_tax.getInt(0);
            }
            Cursor_tax.close();
        }
        //service_charges = (sub_total * (10 / 100f));
        service_charges = (sub_total * (taxRate / 100f));
        if (service_charges > 0.0) {
            service_charges_layout.setVisibility(View.VISIBLE);

            String str_service_charges = Query.GetServiceChargesNameAndPercentage();
            service_chargesname.setText(str_service_charges);
            //service_chargesname.setTextSize(MainActivity.billFontSize);
            service_charges_value.setVisibility(View.VISIBLE);
            service_charges_value.setText("$" + String.format("%.2f", service_charges));
            //service_charges_value.setTextSize(MainActivity.billFontSize);

        }
        return service_charges;
    }

    public static double CalculationBillDiscountAmount(String status, Double sub_total, String str_percentage_value, TextView txt_discount_checkout, String disAmountValue) {
        double discount_amount = 0.0;
        if (status.equals("TabFragmentPercentage")){

            //Double dis_amt = (Double.valueOf(TabFragmentPercentage.str_percentage_value) * Double.valueOf(sub_total)) / 100d;

            if (str_percentage_value != null && str_percentage_value.length() > 0) {
                Double dis_amt = (Double.valueOf(str_percentage_value) * Double.valueOf(sub_total)) / 100d;
                //Double new_amt = Double.valueOf(sub_total) - dis_amt;
                discount_amount = dis_amt;
                //btn_add_discount_checkout.setVisibility(View.GONE);
//                txt_discount_checkout.setVisibility(View.VISIBLE);
//                //txt_discount_checkout.setText("$"+String.valueOf(String.format("%.2f", discount_amount)));
//                txt_discount_checkout.setText("$" + String.valueOf(String.format("%.2f", dis_amt)));
                CheckOutActivity.discount_amount = discount_amount;
                Query.ShowBillDiscountAtCheckoutPage();
                //txt_discount_checkout.setTextSize(MainActivity.billFontSize);
            }
        }else if (status.equals("TabFragmentAmount")){

//            if (strPercentageValue != null && str_percentage_value.length() > 0) {
            if (disAmountValue != null && disAmountValue.length() > 0) {
//                Double dis_amt = Double.valueOf(strPercentageValue);

//                Double new_amt = Double.valueOf(sub_total) - dis_amt;

//                discount_amount = new_amt;
                discount_amount = Double.valueOf(disAmountValue);
//                txt_discount_checkout.setVisibility(View.VISIBLE);
//                txt_discount_checkout.setText("$" + String.valueOf(String.format("%.2f", discount_amount)));
                CheckOutActivity.discount_amount = discount_amount;
                Query.ShowBillDiscountAtCheckoutPage();
            }
        }else{
            txt_discount_checkout.setVisibility(View.GONE);
        }
        return discount_amount;
    }

    public static Cursor GetSalesForSync(String bill_no) {
//        String sql = "SELECT Details_BillProduct.ProductID,Details_BillProduct.ProductName,SUM(Sales.TotalQty), "+
//                "SUM(Sales.TotalNettSales),SUM(Sales.TotalTaxes),SUM(Sales.TotalBillDisount),SUM(Sales.ServiceCharges)," +
//                "Sales.STATUS,SUM(RoundAdj),strftime('%Y-%m-%d %H:%M:%S', Sales.DateTime / 1000, 'unixepoch') "+
//                "FROM Sales "+
//                "INNER JOIN Details_BillProduct on Details_BillProduct.BillNo = Sales.BillNo " +
//                "WHERE Sales.BillNo = '"+bill_no+"' ";
        String sql = "SELECT DetailsBillProduct.ProductID,DetailsBillProduct.ProductName,count(Sales.TotalQty),(Sales.TotalNettSales)," +
                "(Sales.TotalTaxes),(Sales.TotalBillDisount),(Sales.ServiceCharges),"+
                " Sales.STATUS,(RoundAdj),Sales.DateTime "+
                " FROM Sales INNER JOIN DetailsBillProduct on DetailsBillProduct.BillNo = Sales.BillNo " +
                "WHERE Sales.BillNo = '"+bill_no+"' group by ProductID ";
        Log.i("DFD___","sqlsale___"+sql);
        return DBFunc.Query(sql,false);
    }

    public static Cursor GetBillPaymentAmountDetails(String billNo) {
        String sql = "SELECT Name,Amount,ChangeAmount,PaymentID,EwalletIssueBanker,EwalletPaymentType," +
                "EwalletStatus,PaymentRemarks FROM BillPayment " +
                "where BillNo = '"+billNo+"' group by PaymentID,EwalletPaymentType order by BillNo ASC ";
                //"where BillNo = '"+billNo+"' group by PaymentID order by BillNo DESC ";
        //"where BillNo = '"+BillNo+"' order by BillNo DESC ";

        return DBFunc.Query(sql,false);
    }

    public static String GetUUID(Integer product_id) {
        String uuid = "0";
        String uuidsql = " SELECT UUID " +
                "FROM PLU WHERE ID = "+ product_id;

        Cursor c = DBFunc.Query(uuidsql, true);
        if (c!=null){
            if (c.moveToNext()){
                uuid = c.getString(0);
            }
            c.close();
        }

        return uuid;
    }
    public static String GetUUIDByName(String product_name) {
        String uuid = "0";
        String uuidsql = " SELECT UUID " +
                "FROM PLU WHERE Name = '"+ product_name+"'";

        Cursor c = DBFunc.Query(uuidsql, true);
        if (c!=null){
            if (c.moveToNext()){
                uuid = c.getString(0);
            }
            c.close();
        }

        return uuid;
    }

//    public static Integer GetTotalQty(Integer product_id,String billNo) {
//        Integer totalQty = 0;
//        String sqll =  "SELECT count(ProductQty),ProductName,(ProductPrice),BillNo,ItemDiscountAmount,ProductID,CategoryID,CategoryName," +
//                "TaxID,TaxName,(TaxAmount),DiscountName,DiscountTypeName,DiscountValue, " +
//                "ProductPrice,ID  " +
//                "FROM Details_BillProduct Where BillNo = '"+billNo+"' AND  " +
//                "ProductID = '"+product_id+"' " +
//                "AND OpenPriceStatus = '"+ EditProductSheetFragment.EditFragmentOpenPrice +
//                "' AND Cancel = 'SALES' Group By ProductID,OpenPriceStatus" ;
//        Log.i("___sqll",sqll);
//        Cursor cc = DBFunc.Query(sqll,false);
//        //int ccount = 0;
//        if (cc != null) {
//            //while (cc.moveToNext()) {
//            if (cc.moveToNext()) {
//                //ccount++;
//                totalQty = cc.getInt(0);
//            }
//            cc.close();
//        }
//        Log.i("df__","totalQty__"+totalQty);
//        return totalQty;
//    }

    public static String PromotionRequesttemp(String company_code) {
        String temp = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
                "  <soap12:Body>\n" +
                "    <getPromotion xmlns=\"http://tempuri.org/\">\n" +
                "      <companyCode>"+company_code+"</companyCode>\n" +
                "    </getPromotion>\n" +
                "  </soap12:Body>\n" +
                "</soap12:Envelope>";
        return temp;
    }

    public static Cursor GetPLU(Integer product_id) {
        String sql = "";
        sql = "SELECT Name,Price,Code,Image,ID,ProductCategoryID,ProductCategoryName,UUID,AllowOpenPrice,AllowRemarks FROM PLU " ;
        if (product_id == -1) {
            sql += " WHERE Name LIKE '%"+ ProductMainPageFragment.str_newText.toUpperCase()+"%'";
            sql += " OR Code = '"+ ProductMainPageFragment.str_newText.toUpperCase()+"'";
            sql += " OR Price = '"+ ProductMainPageFragment.str_newText.toUpperCase()+"'";
        } else if (product_id > 0) {
            sql += " where ProductCategoryID = " + product_id;
        }

        return DBFunc.Query(sql,true);
    }

    public static List<ProductData> GetBillDetailsProduct(Cursor c, List<ProductData> lstProductData) {
        String title = c.getString(0);
        String price = c.getString(1);
        String category = c.getString(5);
        String description = "";
        String thumbnail = c.getString(3);
        String productID = c.getString(4);
        String productCategoryID = c.getString(5);
        String productCategoryName = c.getString(6);
        String UUID = c.getString(7);
        String openPrice = c.getString(8);
        String remarks = c.getString(9);
        if ( openPrice == null){
            openPrice = "0";
        }
        if ( remarks == null){
            remarks = "0";
        }

        if (c.getInt(4) > 0) {
            lstProductData.add(
                    new ProductData(
                            title, price, category, description, thumbnail,
                            productID, productCategoryID, productCategoryName, UUID, openPrice,remarks));
        }
        return lstProductData;
    }

    public static void ClearRecyclerViewValues() {
        RecyclerViewAdapter.count = 0;
        RecyclerViewAdapter.sltPriceTotal = 0.0;
        RecyclerViewAdapter.countSelectedArr = 0;
        RecyclerViewAdapter.sldNameArr.clear();
        RecyclerViewAdapter.sldQtyArr.clear();
        RecyclerViewAdapter.sldIDArr.clear();
        RecyclerViewAdapter.sltPriceTotal = 0.0;
        RecyclerViewAdapter.str_sltPriceTotal = "0";
        //sltPriceTotalArr.add(Integer.parseInt(selectedPrice))
        RecyclerViewAdapter.sltPriceTotalArr.clear();
        //RecyclerViewAdapter.chksldQtyArr.clear();
        RecyclerViewAdapter.sldCategoryIDArr.clear();
        RecyclerViewAdapter.sldCategoryNameArr.clear();
        RecyclerViewAdapter.sltBillDisArr.clear();
        RecyclerViewAdapter.count_totalItems = 0;
        RecyclerViewAdapter.count_selected_total = 0;
        RecyclerViewAdapter.sltProductIDArr.clear();
        RecyclerViewAdapter.sltCategoryIDArr.clear();
        RecyclerViewAdapter.sltCategoryNameArr.clear();
        RecyclerViewAdapter.slddisID.clear();
        RecyclerViewAdapter.slddisName.clear();
        RecyclerViewAdapter.slddisTypeName.clear();
        RecyclerViewAdapter.slddisType.clear();
        RecyclerViewAdapter.slddisValue.clear();
    }

    public static void SavePLU(String pluName, String uniqueId, String pluPrice,String pluCode, String str_img,
                               String str_imgItemId, String str_imgUrl, String str_imgType,String str_imgfileName,String base64imgValue,
                               String str_checked_name_quick_edit, String str_checked_price_quick_edit,
                               String str_checked_open_price_edit, String allowremarks,
                               Integer category_IDD, String category_Namee, Integer taxID, String taxName, String OnHandQty,
                               String status) {
        if (str_checked_name_quick_edit != null && str_checked_name_quick_edit.length() > 0){
            str_checked_name_quick_edit = str_checked_name_quick_edit;
        }else {
            str_checked_name_quick_edit = "0";
        }
        if (str_checked_price_quick_edit != null && str_checked_price_quick_edit.length() > 0){
            str_checked_price_quick_edit = str_checked_price_quick_edit;
        }else {
            str_checked_price_quick_edit = "0";
        }
        if (str_checked_open_price_edit != null && str_checked_open_price_edit.length() > 0){
            str_checked_open_price_edit = str_checked_open_price_edit;
        }else {
            str_checked_open_price_edit = "0";
        }
        try {
            if (pluPrice.length() == 0){
                pluPrice = "0";
            }
            Log.i("OnHandQty__","OnHandQty_ddd13");
//            	`ImageItemID`	TEXT,
//	`ImageUrl`	TEXT,
//	`ImageType`	TEXT,
//	`ImageFileName`	TEXT,
            String sql = "INSERT INTO PLU (Name,UUID,Name2,DeptID,Price,Option,Code,Image,base64imgValue,ImageItemID,ImageUrl,ImageType,ImageFileName,ProductVariant,ProductModifiers," +
                    "AllowNameQuickEdit,AllowPriceQuickEdit,AllowOpenPrice,AllowRemarks,ProductCategoryID,ProductCategoryName,ProductTaxID," +
                    "ProductTaxName,OnHandQty,DateTime,Condi_Seq) " +
                    "VALUES ('" + DBFunc.PurifyString(pluName) + "'," +
                    "'" + uniqueId + "'," +
                    //"'"+base64imgValue+"'," +
                    "''," +
                    "" + 0 + "," +
                    pluPrice + ",'" +
                    000000 + "','" + DBFunc.PurifyString(pluCode) + "'," +
//                    "'" + DBFunc.PurifyString(String.valueOf(BitMapToString(bitmap))) + "'," +
                    "'" + DBFunc.PurifyString(String.valueOf(str_img)) + "'," +
                    "'" + DBFunc.PurifyString(String.valueOf(base64imgValue)) + "'," +
                    "'" + DBFunc.PurifyString(String.valueOf(str_imgItemId)) + "'," +
                    "'" + DBFunc.PurifyString(String.valueOf(str_imgUrl)) + "'," +
                    "'" + DBFunc.PurifyString(String.valueOf(str_imgType)) + "'," +
                    "'" + DBFunc.PurifyString(String.valueOf(str_imgfileName.replace(".",""))) + "'," +
                    "''," +
                    "''," +
//                    "" + Integer.parseInt(str_checked_name_quick_edit) + "," +
//                    "" + Integer.parseInt(str_checked_price_quick_edit) + "," +
//                    "" + Integer.parseInt(str_checked_open_price_edit) + "," +
                    "" + Integer.parseInt(str_checked_name_quick_edit) + "," +
                    "" + Integer.parseInt(str_checked_price_quick_edit) + "," +
                    "" + Integer.parseInt(str_checked_open_price_edit) + "," +

                    "" + Integer.parseInt(allowremarks) + "," +
                    "'" + category_IDD + "'," +
                    "'" + category_Namee + "'," +
                    "'" + taxID + "'," +
                    "'" + taxName + "'," +
                    "'" + OnHandQty + "'," +
                    "" + System.currentTimeMillis() + "," +
                    000 + ")";
            Log.i("Dfsf___","sql__"+sql);
            DBFunc.ExecQuery(sql, true);

            Integer lstpluid = findLatestID("ID", "PLU", true);

            Integer QtySold = 0;
            Integer QtyAdjustment = 0;
            Integer QtyReturn = 0;
            Integer QtyBalance = 0;
            Integer QtyActual = 0;
            Integer PLUID = lstpluid;
            //String DateTime = "strftime('"+Constraints.sqldateformat+"', "+System.currentTimeMillis()+" / 1000, 'unixepoch')";

//            Date resultdate = new Date();
//            String DateTime = CheckOutActivity.dateFormat55.format(resultdate);
//            String DateTime = GetDateFormart55();
//
//            StockAgent stockAgent = new StockAgent(QtySold,QtyAdjustment,QtyReturn,QtyBalance,QtyActual,PLUID,DateTime);
//            Query.SaveStockAgent(stockAgent);

            String searchPLUStockAgent = "SELECT PLUID FROM StockAgent WHERE PLUID = "+PLUID;
            Cursor csearchPLUStockAgent = DBFunc.Query(searchPLUStockAgent,true);
            if (csearchPLUStockAgent != null) {
                if (csearchPLUStockAgent.getCount() == 0){
                    //String stockAgentDateTime = CheckOutActivity.dateFormat55.format(new Date());
                    String DateTime = GetDateFormart55();

                    StockAgent stockAgent = new StockAgent(QtySold,QtyAdjustment,QtyReturn,QtyBalance,QtyActual,PLUID,DateTime);
                    Query.SaveStockAgent(stockAgent);

                } else {
                    Query.UpdateStockAgent(PLUID,QtyActual);
                }
                csearchPLUStockAgent.close();
            }


            if (status.equals("Sync")){

                Query.UpdateStockAgent(lstpluid,Integer.parseInt(OnHandQty));

                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                final Calendar myCalendar = Calendar.getInstance();

                StockIn stockIn = new StockIn(lstpluid, Integer.parseInt(OnHandQty) , "0000SI", sdf.format(myCalendar.getTime()));
                Query.SaveStockIn(stockIn);
            }

        }catch (SQLiteException e){
            //Log.i("ERror","Error");
        }
    }
    public static void SaveModifier(String name,String price) {
        //DBFunc.ExecQuery("INSERT INTO Bill (OpenDateTime, Cashier, CashierID, BalNo) VALUES (" + System.currentTimeMillis() + ",'" + DBFunc.PurifyString(Allocator.cashierName) + "', " + Allocator.cashierID + ", '" + balno + "')", false);

        try {
            String sql = "SELECT ID FROM Product_Modifier WHERE Name = '" + name + "'";
            Log.i("sql__ddddd", sql);
            Cursor c = DBFunc.Query(sql, true);
            if (c != null) {
                if (c.getCount() == 0) {
                    sql = "INSERT INTO Product_Modifier (Name,Price,DateTime) " +
                            "VALUES ('" + DBFunc.PurifyString(name) + "'," +
                            price + "," +
                            System.currentTimeMillis() + ")";
                    Log.i("sql__",sql);
                    DBFunc.ExecQuery(sql, true);
                }
                c.close();
            }
        }catch (Exception e){
            Log.i("Error",e.toString());
        }
    }

    public static Cursor GetURLAndCodeFromPossys() {
        String sql = "SELECT retails_id,company_code,company_url," +
                "download_retails_id,download_company_code,download_company_url,terminal_id,storeno," +
                "server_image_upload_url,download_edit_eunoia_storeId,download_edit_eunoia_partnerId," +
                "download_edit_eunoia_url,download_edit_eunoia_partnerEmail,download_userlogin,download_userpassword FROM POSSys";
        return  DBFunc.Query(sql, true);
    }
    public static void writeToFile(String data,Context context,String fileName) {
//        File file = new File(context.getFilesDir(), "REport");
//        if (!file.exists()) {
//            file.mkdir();
//        }
//        try {
//            File gpxfile = new File(file, fileName);
//            FileWriter writer = new FileWriter(gpxfile);
//            writer.append(data);
//            writer.flush();
//            writer.close();
//            Toast.makeText(context, "Saved your text", Toast.LENGTH_LONG).show();
//        } catch (Exception e) { }
////        try {
////            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
////                    context.openFileOutput(fileName+".txt", Context.MODE_PRIVATE));
////            outputStreamWriter.write(data);
////            outputStreamWriter.close();
////        }
////        catch (IOException e) {
////            Log.e("Exception", "File write failed: " + e.toString());
////        }
//        FileWriter fWriter;
//        File sdCardFile = new File(Environment.getExternalStorageDirectory() + "/"+fileName+".txt");
//        Log.i("TAG", "Pathhhhh"+sdCardFile.getPath()); //<-- check the log to make sure the path is correct.
//        try{
//            fWriter = new FileWriter(sdCardFile, true);
//            fWriter.write(data);
//            fWriter.flush();
//            fWriter.close();
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        String FolerName = sdf3.format(myCalendar2.getTime()).toString();
//        File direct = new File("/storage/emulated/0/"+FolerName);
    }

    public static void AA(String itemname,String itemprice,String qty,String itemTotalAmount) {
        Integer totQty = 0;
        String mixmatproqty = "0";
        String mixmatproamt = "0";

        String PromoID = "";
        String PromoName = "";
        String promoType = "";

        Integer overtot = 0;
        Integer ot_totQty_ = 0;
        double reduceprice = 0.0;

        String inventoryItemID = Query.GetInventoryID(itemname);
        String promoItemPromoID = Query.GetPromoItemID(inventoryItemID);

        if (promoItemPromoID.length() > 0) {
            promoType = Query.GetPromoType(promoItemPromoID,PromoID,PromoName);

            String promoTypeCode ="";
            if (promoType.length() > 0) {
               promoTypeCode = Query.GetPromoTypeCode(promoType);
            }

            Query.MixAndMatchInfo(promoType,mixmatproqty,mixmatproamt);

            if (promoTypeCode.equals("mm")) {
                if (Integer.parseInt(itemprice) >= Integer.parseInt(mixmatproqty)) {
                    //totQty = 0;
                    totQty += Integer.parseInt(itemprice);

                } else {
                    totQty += Integer.parseInt(itemprice);
                }

            }
        }

        Log.i("___", String.valueOf(PromoID.length()));
        if (PromoID.length() > 0) {
            //if (totQty >= Integer.parseInt(mixmatproqty) && Integer.parseInt(mixmatproamt) > 0) {
            if (Double.parseDouble(String.valueOf(totQty)) >= Double.parseDouble(mixmatproqty)
                    && Double.parseDouble(mixmatproamt) > 0.0) {

                int modulus = totQty % Integer.parseInt(mixmatproqty); // 12 % 3 = 0 =>modulus  // 13 % 3 = 1

                int getPromoQty = totQty / Integer.parseInt(mixmatproqty);// 12 / 3 = 4 // 13 / 3 = 4.3

                int totQty_ = getPromoQty * Integer.parseInt(mixmatproqty);  // 4 *3 = 12 //  4.3 * 3  = 12.9

                if (modulus == 0) {
                    overtot = 0;
                } else {
                    overtot = totQty - totQty_; // 13 - 12.9 = 0.1
                    ot_totQty_ = totQty_;
                    double rv = Double.valueOf(itemTotalAmount) / Double.valueOf(qty); // 100 /
                    reduceprice = overtot * rv;
                }
                //overtot = totQty - totQty_;
                //int totamt_ = 0;
                double totamt_ = 0.0;
                if (overtot > 0) {
                    int realtotqtyq = totQty_ / Integer.parseInt(mixmatproqty);
                    //int totQty_q = realtotqtyq * Integer.parseInt(mixmatproqty);
                    //totamt_ = realtotqtyq * Integer.parseInt(mixmatproamt);//1*
                    totamt_ = Double.parseDouble(String.valueOf(realtotqtyq)) * Double.parseDouble(mixmatproamt);//1*
                } else {
                    totamt_ = Double.parseDouble(String.valueOf(getPromoQty)) * Double.parseDouble(mixmatproamt);
                }
                //price = price - totamt_;
                //totQtyaa = totamt_;

                //needqtyamt = totQty_ * Double.valueOf(plu.getString(2)); // 3 * 0.8 = 2.4
                //needqtyamt = totQty_ * Double.valueOf((Double) (item[3])); // 3 * 0.8 = 2.4
                //needqtyamt = Double.valueOf((Double) (item[3])); // 3 * 0.8 = 2.4
                double needqtyamt = Double.valueOf(itemTotalAmount); // 3 * 0.8 = 2.4

                if (overtot > 0) {
                    //needqtyamt = ot_totQty_ * Double.valueOf((Double) (item[3])); // 3 * 0.8 = 2.4
                    //needqtyamt = reduceprice; // 3 * 0.8 = 2.4
                    double rv1 = Double.valueOf(needqtyamt) / Double.valueOf(qty);
                    double reduceprice1 = totQty_ * rv1;
                    needqtyamt = Double.valueOf(reduceprice1); // 3 * 0.8 = 2.4
                } else {
                    //needqtyamt = totQty_ * Double.valueOf((Double) (item[3])); // 3 * 0.8 = 2.4
                    double rv1 = Double.valueOf(needqtyamt) / Double.valueOf(qty);
                    needqtyamt = totQty_ * rv1; // 3 * 0.8 = 2.4
                }
                Double val = Double.valueOf(needqtyamt) - Double.valueOf(totamt_);

                double eachItemPromoValue = val / Integer.parseInt(mixmatproqty);

                double dneedqtyamt = needqtyamt - totamt_; // 2.4 - 2 = 0.4
                double eachProAmt = dneedqtyamt / totQty_; // 0.4 / 3 = 0.1333
                double eachProAmttot = eachProAmt * Double.valueOf(itemprice);
                double originalpromotot = dneedqtyamt - eachProAmt;
                double promo_amt = Double.valueOf(mixmatproamt);
                double totalsalesamt = needqtyamt;
                double allitemdis = promo_amt / totalsalesamt;

                double eachitmNotDis = Double.valueOf(itemTotalAmount);

                double eachitmtotalamt = eachitmNotDis * allitemdis;
                double eachitmdiss = eachitmNotDis - eachitmtotalamt;
                double tamt = needqtyamt - totamt_;
                double aa = tamt;
//							double tamt = needqtyamt - totamt_;
//							double aa = eachitmNotDis - needqtyamt;

                if (eachProAmt > 0) {
                    String Promo_Name_ = "  **" + PromoName;
                    Double dpv = (-1) * Double.valueOf(aa);
                    String pv = String.format("%.2f", dpv);

                    double Promo_Value = Double.valueOf(pv);
                }
            }
        }
    }

    public static void CalculatePromoMixAndMatch(String itemname,Integer totalQty,double itemprice_base,double qty,
                          String BillID, String BillNo, String PLUID, String PLUName){
//        idx,Name,Amount,Qty,PLUID,CondiPLUID
//        idx => [0]
//        Name => [1]
//        Amount => [2]
//        Qty => [3]
//        PLUID => [4]
//        CondiPLUID => [5]
//        data.getString(1) => [0] ,  Name
//        data.getInt(3)  => [1], =>Qty item[2]
//        itemprice => [2] =>Amount item[3]

//        data.getString(1), [0]
//        data.getInt(3), [1]
//        itemprice, [2]
//        itemprice_base [3]


        //idx,Name,Amount,Qty,
        //data.getString(1), data.getInt(3), itemprice,
        //item[0], (idx)     item[1],(Amount)   item[2]
        String _inv_item_id = "";
        Integer totQty = 0;

        //Cursor c = DBFunc.Query("SELECT Name,Price,Option,Code,Condi_Seq FROM PLU WHERE Name = '" + itemname + "'", true);

        String to_chk_promo_item = itemname;

        //Cursor config_values_inv = DBFunc.Query("SELECT ItemID FROM Inventory WHERE ItemDescp = \"" + to_chk_promo_item + "\"", true);
        Cursor c = DBFunc.Query("SELECT UUID,Name,Price,Option,Code,Condi_Seq FROM PLU WHERE Name = '" + itemname + "'", true);
        assert c != null;
        while (c.moveToNext()) {
            _inv_item_id = c.getString(0);
        }

        String proItem_PromoID = "";
        String proItem_ItemID = "";
        String proItem_ItemQty = "";
        String proItem_ItemUOM = "";
        String proItem_ItemPrice = "";
        //Cursor config_values_pro_item = DBFunc.Query("SELECT PromoID,ItemID,Item_Qty,ItemUOM,ItemPrice FROM promo_item WHERE ItemUOM = '" + to_chk_promo_item + "'", true);
        Cursor config_values_pro_item = null;
        config_values_pro_item = DBFunc.Query("SELECT PromoID,ItemID,Item_Qty,ItemUOM,ItemPrice " +
                "FROM Promo_Item WHERE ItemID = \"" + _inv_item_id + "\"", true);
        //assert config_values_pro_item != null;
        String tst = "tst";
        if (config_values_pro_item != null) {
            while (config_values_pro_item.moveToNext()) {
                proItem_PromoID = config_values_pro_item.getString(0);
                tst = "";

                proItem_ItemID = config_values_pro_item.getString(1);


                proItem_ItemQty = config_values_pro_item.getString(2);
                proItem_ItemUOM = config_values_pro_item.getString(3);
                proItem_ItemPrice = config_values_pro_item.getString(4);
            }
        } else {
            proItem_PromoID = "";
        }


        String proN = "";
        String Promo_Name = "";
        String Promo_Promo_Type = "";
        String mixmatproqty = "";
        String mixmatproamt = "";
        String mixmatID = "";
        if (proItem_PromoID.length() > 0) {
           // String nowAsISO1 = sdf.format(new Date());
           // String now_date = ISO_8601_formatted_date1(nowAsISO1);
            String now_date = ISO_8601_formatted_date1();
            Cursor config_pro_obj = null;
            config_pro_obj = DBFunc.Query("SELECT Promo_Name,Promo_DateFrom,Promo_DateTo,Promo_Type " +
                    "FROM Promo WHERE PromoID = \"" + proItem_PromoID + "\"", true);
            String SubmitSaleObject = "0";
            String SubmitSaleObjectfrom = "0";
            String SubmitSaleObjectto = "0";
            List<String> list = new ArrayList<String>();
            if (config_pro_obj != null) {
                while (config_pro_obj.moveToNext()) {
                    proN = config_pro_obj.getString(0);
                    Promo_Name = config_pro_obj.getString(0);



                    SubmitSaleObject = config_pro_obj.getString(0);
                    SubmitSaleObjectfrom = config_pro_obj.getString(1);
                    SubmitSaleObjectto = config_pro_obj.getString(2);
                    String fromDAte = "";
                    String toDAte = "";
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
                        SimpleDateFormat sdf2 = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
                        fromDAte = sdf2.format(sdf.parse(SubmitSaleObjectfrom));
                        toDAte = sdf2.format(sdf.parse(SubmitSaleObjectto));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Date date11 = null;
                    Date date12 = null;
                    try {
                        date11 = sdf.parse(fromDAte);
                        date12 = sdf.parse(toDAte);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (Long.parseLong(now_date) >= Long.parseLong(String.valueOf(date11.getTime()))) {
                        if (Long.parseLong(String.valueOf(date12.getTime())) >= Long.parseLong(now_date)) {
                            Promo_Promo_Type = config_pro_obj.getString(3);
                            list.add(SubmitSaleObject + "__1__" + SubmitSaleObjectfrom + "__2__" + SubmitSaleObjectto);
                        } else {
                            Promo_Promo_Type = "";
                        }
                    } else {
                        Promo_Promo_Type = "";

                    }
                }
            } else {
                Promo_Promo_Type = "";
            }

            String Promo_TypeCode = "";
            if (Promo_Promo_Type.length() > 0) {
                Cursor config_pro_type_obj = null;
                config_pro_type_obj = DBFunc.Query("SELECT Promo_TypeCode " +
                        "FROM Promotype WHERE Promo_TypeID = '" + Promo_Promo_Type + "'", true);
                        //"FROM promo_type WHERE Promo_TypeID = '" + Promo_Promo_Type + "'", true);

                if (config_pro_type_obj != null) {
                    while (config_pro_type_obj.moveToNext()) {
                        Promo_TypeCode = config_pro_type_obj.getString(0);
                    }
                } else {
                    Promo_TypeCode = "";
                }


            }
            Cursor config_promo_mixmatch_obj = null;
            //config_promo_mixmatch_obj = DBFunc.Query("SELECT PromoID,PromoMixID,Promo_Qty,Promo_Amount,Promo_MemberAmount FROM promo_mixmatch WHERE PromoID = '" + str_pro_itm_proID + "'", true);
            String sqql = "SELECT PromoID,PromoMixID,Promo_Qty,Promo_Amount,Promo_MemberAmount,ID " +
                    "FROM Promo_Mixmatch WHERE PromoID = '" + Promo_Promo_Type + "'";

            config_promo_mixmatch_obj = DBFunc.Query(sqql, true);
                    //"FROM Promo_Mixmatch WHERE PromoID = \"" + proItem_PromoID + "\"", true);

            assert config_promo_mixmatch_obj != null;
            while (config_promo_mixmatch_obj.moveToNext()) {
                //mixmatproid = config_promo_mixmatch_obj.getString(0);
                //mixmatpromixid = config_promo_mixmatch_obj.getString(1);
                mixmatproqty = config_promo_mixmatch_obj.getString(2);
                mixmatproamt = config_promo_mixmatch_obj.getString(3);
                mixmatID = config_promo_mixmatch_obj.getString(5);
                //mixmatpromemamt = config_promo_mixmatch_obj.getString(4);
            }


            if (Promo_TypeCode.equals("mm")) {
               //if (Integer.parseInt(String.valueOf(((Integer) item[1]))) >= Integer.parseInt(mixmatproqty)) {
               // if (Integer.parseInt(String.valueOf(((Integer) item[1]))) >= Integer.parseInt(mixmatproqty)) {

                if (totalQty >= Integer.parseInt(mixmatproqty)) {
//							Log.i("AAA", String.valueOf(totQty)+"\n"+"BBBB"+Integer.parseInt(String.valueOf(plu.getInt(3))));
//							c = totQty;
                    totQty = 0;
                    //totQty += Integer.parseInt(String.valueOf(((Integer) item[1])));
                    //totQty += totQty;
                    totQty += totalQty;
//							c = c - Integer.parseInt(String.valueOf(plu.getInt(3)));
//							Log.i("before", String.valueOf(c));
                } else {
//							Log.i("after", String.valueOf(c));
//							totQty += c;
//							Log.i("aftera", String.valueOf(totQty));
                    //totQty += Integer.parseInt(String.valueOf(((Integer) item[1])));
                    //totQty += totQty;
                    totQty += totalQty;
//							Log.i("afterb", String.valueOf(totQty));
                }
            }
        }

        String _Promo_Name = "";
        String _Promo_Value = "";

        Integer overtot = 0;
        Integer ot_totQty_ = 0;
        Double reduceprice = 0.0;
        //Double needqtyamt = 0.0;
        Double itembaseamt = 0.0;
        Double eachItemPromoValue = 0.0;

        if (proItem_PromoID.length() > 0 && mixmatproqty != null && mixmatproqty.length() > 0) {
            //if (totQty >= Integer.parseInt(mixmatproqty) && Integer.parseInt(mixmatproamt) > 0) {


            if (Double.parseDouble(String.valueOf(totQty)) >= Double.parseDouble(mixmatproqty)
                    && Double.parseDouble(mixmatproamt) > 0.0) {

                int modulus = totQty % Integer.parseInt(mixmatproqty);


                int realtotqty = totQty / Integer.parseInt(mixmatproqty);

                int totQty_ = realtotqty * Integer.parseInt(mixmatproqty);


                if (modulus == 0) {
                    overtot = 0;
                } else {
                    overtot = totQty - totQty_;
                    ot_totQty_ = totQty_;
                    //double rv = Double.valueOf((Double) (item[3]) / Double.valueOf(qty));
                    double rv = Double.valueOf(itemprice_base / Double.valueOf(qty));

                    reduceprice = overtot * rv;

                }
                // 3 / 2 =
                //overtot = totQty - totQty_;
                //int totamt_ = 0;
                double totamt_ = 0.0;
                if (overtot > 0) {
                    int realtotqtyq = totQty_ / Integer.parseInt(mixmatproqty);

                    //int totQty_q = realtotqtyq * Integer.parseInt(mixmatproqty);
                    //totamt_ = realtotqtyq * Integer.parseInt(mixmatproamt);//1*
                    totamt_ = Double.parseDouble(String.valueOf(realtotqtyq)) * Double.parseDouble(mixmatproamt);//1*

                } else {
                    totamt_ = Double.parseDouble(String.valueOf(realtotqty)) * Double.parseDouble(mixmatproamt);

                }
                //price = price - totamt_;
                //totQtyaa = totamt_;

                //needqtyamt = totQty_ * Double.valueOf(plu.getString(2)); // 3 * 0.8 = 2.4
                //needqtyamt = totQty_ * Double.valueOf((Double) (item[3])); // 3 * 0.8 = 2.4
                //needqtyamt = Double.valueOf((Double) (item[3])); // 3 * 0.8 = 2.4
                //needqtyamt = Double.valueOf(itemprice_base); // 3 * 0.8 = 2.4
                itembaseamt = Double.valueOf(itemprice_base); // 3 * 0.8 = 2.4


//                if (overtot > 0) {
//                    //needqtyamt = ot_totQty_ * Double.valueOf((Double) (item[3])); // 3 * 0.8 = 2.4
//                    //needqtyamt = reduceprice; // 3 * 0.8 = 2.4
//                    //double rv1 = Double.valueOf((Double) (item[3]) / Double.valueOf(qty));
//                    double rv1 = Double.valueOf(itemprice_base/ Double.valueOf(qty));
//                    double reduceprice1 = totQty_ * rv1;
////                    //needqtyamt = Double.valueOf(reduceprice1); // 3 * 0.8 = 2.4
////                    itembaseamt = Double.valueOf(reduceprice1); // 3 * 0.8 = 2.4
//                   //itembaseamt = itembaseamt * qty;
//                    itembaseamt = itembaseamt * totalQty;
//                } else {
////                    //needqtyamt = totQty_ * Double.valueOf((Double) (item[3])); // 3 * 0.8 = 2.4
////                    //double rv1 = Double.valueOf((Double) (item[3]) / Double.valueOf(qty));
////                    double rv1 = Double.valueOf(itemprice_base / Double.valueOf(qty));
////                    //needqtyamt = totQty_ * rv1; // 3 * 0.8 = 2.4
////                    itembaseamt = totQty_ * rv1; // 3 * 0.8 = 2.4
//                    //itembaseamt = itembaseamt * qty;
//                    itembaseamt = itembaseamt * totalQty;
//                }
                if (overtot > 0) {
                    //needqtyamt = ot_totQty_ * Double.valueOf((Double) (item[3])); // 3 * 0.8 = 2.4
                    //needqtyamt = reduceprice; // 3 * 0.8 = 2.4
                   // double rv1 = Double.valueOf(itembaseamt / Double.valueOf(totalQty));
                    //double reduceprice1 = totQty_ * rv1;
                    double reduceprice1 = totQty_ * itembaseamt;
                    itembaseamt = Double.valueOf(reduceprice1); // 3 * 0.8 = 2.4
                } else {
                    //needqtyamt = totQty_ * Double.valueOf((Double) (item[3])); // 3 * 0.8 = 2.4
                    //double rv1 = Double.valueOf(itembaseamt / Double.valueOf(totalQty));
                    //itembaseamt = totQty_ * rv1; // 3 * 0.8 = 2.4
                    itembaseamt = totQty_ * itembaseamt; // 3 * 0.8 = 2.4
                }

                //Double val = Double.valueOf(needqtyamt) - Double.valueOf(totamt_);
                Double val = Double.valueOf(itembaseamt) - Double.valueOf(totamt_);

                eachItemPromoValue = val / Integer.parseInt(mixmatproqty);

                //double dneedqtyamt = needqtyamt - totamt_; // 2.4 - 2 = 0.4
                double dneedqtyamt = itembaseamt - totamt_; // 2.4 - 2 = 0.4

                double eachProAmt = dneedqtyamt / totQty_; // 0.4 / 3 = 0.1333
                //double eachProAmttot = eachProAmt * (Integer) item[1];
                double eachProAmttot = eachProAmt * qty;
                double originalpromotot = dneedqtyamt - eachProAmt;
                double promo_amt = Double.valueOf(mixmatproamt);
               // double totalsalesamt = needqtyamt;
               double totalsalesamt = itembaseamt;

                double allitemdis = promo_amt / totalsalesamt;
                //double eachitmNotDis = Double.valueOf((Double) (item[3]));
                double eachitmNotDis = itemprice_base;
                double eachitmtotalamt = eachitmNotDis * allitemdis;

                double eachitmdiss = eachitmNotDis - eachitmtotalamt;

                //double tamt = needqtyamt - totamt_;
                double tamt = totalsalesamt - totamt_;

                double aa = tamt;

                if (eachProAmt > 0) {
                    String Promo_Name_ = "  **" + Promo_Name;
                    Double dpv = (-1) * Double.valueOf(aa);
                    String pv = String.format("%.2f", dpv);
                    double Promo_Value = Double.valueOf(pv);

                    SavePLUPromo(BillID,BillNo,proItem_PromoID,proItem_ItemID,
                            Promo_Promo_Type,mixmatID,"0",Promo_Name_,
                            String.valueOf(Promo_Value),PLUID,PLUName);
                }
            }
        }
    }

//    CREATE TABLE "PLUPromo" (
//            `ID`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
//            `BillID`	TEXT,
//            `BillNo`	TEXT,
//            `PromoID`	TEXT,
//            `PromoItemID`	TEXT,
//            `PromoTypeID`	TEXT,
//            `PromoMixmatchID`	TEXT,
//            `PromoQty`	TEXT,
//            `PromoName`	TEXT,
//            `PromoValue`	TEXT,
//            `PLUID`	TEXT,
//            `PLUName`	TEXT,
//            `DateTime`	INTEGER NOT NULL
//)

    public static void SavePLUPromo(String BillID,String BillNo,String PromoID,String PromoItemID,
                                    String PromoTypeID,String PromoMixmatchID,String PromoQty,String PromoName,
                                    String PromoValue,String PLUID,String PLUName){

        DBFunc.ExecQuery("DELETE From PLUPromo WHERE BillNo = '"+BillNo+"' AND PLUID = '"+PLUID+"'",false);

        String sql = "INSERT INTO PLUPromo (BillID,BillNo,PromoID,PromoItemID,PromoTypeID," +
                "PromoMixmatchID,PromoQty,PromoName,PromoValue,PLUID,PLUName,DateTime) VALUES (";
        sql += "'" + BillID + "', ";
        sql += "'" + BillNo + "', ";
        sql += "'" + PromoID + "', ";
        sql += "'" + PromoItemID + "', ";
        sql += "'" + PromoTypeID + "', ";
        sql += "'" + PromoMixmatchID + "', ";
        sql += "'" + PromoQty + "', ";
        sql += "'" + PromoName + "', ";
        sql += "'" + PromoValue + "', ";
        sql += "'" + PLUID + "', ";
        sql += "'" + PLUName + "', ";
        sql +=  System.currentTimeMillis() + ")";
        Log.i("TAG_","Promo_sql__"+sql);
        DBFunc.ExecQuery(sql, false);
    }



////                    int __str__Promo_Name = 0;
////                    if (Promo_Name_.length() > 23) {
////                        __str__Promo_Name = 1;
////                    } else {
////                        __str__Promo_Name = 23 - Promo_Name_.length();
////                    }
////                    _Promo_Name = String.format("%" + 1 + "s%-" + __str__Promo_Name + "s", "   ", Promo_Name_);
////                    int __str__Promo_Value = 0;
////                    if (String.valueOf(Promo_Value).length() > 10) {
////                        __str__Promo_Value = 1;
////                    } else {
////                        __str__Promo_Value = 10 - String.valueOf(Promo_Value).length();
////                    }
////                    _Promo_Value = String.format("%" + __str__Promo_Value + "s%-" + 1 + "s", "   ",
////                            String.format("%.2f", Promo_Value));
//    totQty = 0;
    private static void MixAndMatchInfo(String promoType,String mixmatproqty,String mixmatproamt) {
        Cursor config_promo_mixmatch_obj = null;
        //config_promo_mixmatch_obj = DBFunc.Query("SELECT PromoID,PromoMixID,Promo_Qty,Promo_Amount,Promo_MemberAmount FROM promo_mixmatch WHERE PromoID = '" + str_pro_itm_proID + "'", true);
        config_promo_mixmatch_obj = DBFunc.Query("SELECT PromoID,PromoMixID,Promo_Qty,Promo_Amount,Promo_MemberAmount " +
                "FROM Promo_Mixmatch WHERE PromoID = \"" + promoType + "\"", true);

        assert config_promo_mixmatch_obj != null;
        while (config_promo_mixmatch_obj.moveToNext()) {
            //mixmatproid = config_promo_mixmatch_obj.getString(0);
            //mixmatpromixid = config_promo_mixmatch_obj.getString(1);
            mixmatproqty = config_promo_mixmatch_obj.getString(2);
            mixmatproamt = config_promo_mixmatch_obj.getString(3);
            //mixmatpromemamt = config_promo_mixmatch_obj.getString(4);
        }
    }

    private static String GetPromoTypeCode(String promoType) {
        String promoTypeCode = "";
        Cursor Cursor_PromoType = DBFunc.Query("SELECT Promo_TypeCode " +
                "FROM Promotype WHERE Promo_TypeID = '" + promoType + "'", true);

        if (Cursor_PromoType != null) {
            while (Cursor_PromoType.moveToNext()) {
                promoTypeCode = Cursor_PromoType.getString(0);
            }
            Cursor_PromoType.close();
        } else {
            promoTypeCode = "";
        }
        return promoTypeCode;
    }

    private static String GetPromoType(String promoItemPromoID,String PromoID,String PromoName) {
        String promoType = "";
        String now_date = ISO_8601_formatted_date1();
        Cursor Cursor_Promo = DBFunc.Query("SELECT PromoID,Promo_Name,Promo_DateFrom,Promo_DateTo,Promo_Type " +
                "FROM Promo WHERE PromoID = \"" + promoItemPromoID + "\"", true);
        String SubmitSaleObject = "0";
        String SubmitSaleObjectfrom = "0";
        String SubmitSaleObjectto = "0";
        //List<String> list = new ArrayList<String>();
        if (Cursor_Promo != null) {
            while (Cursor_Promo.moveToNext()) {
                PromoID = Cursor_Promo.getString(0);
                PromoName = Cursor_Promo.getString(1);
                // SubmitSaleObject = Cursor_Promo.getString(0);
                SubmitSaleObjectfrom = Cursor_Promo.getString(2);
                SubmitSaleObjectto = Cursor_Promo.getString(3);

                String fromDAte = "";
                String toDAte = "";
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
                    SimpleDateFormat sdf2 = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
                    fromDAte = sdf2.format(sdf.parse(SubmitSaleObjectfrom));
                    toDAte = sdf2.format(sdf.parse(SubmitSaleObjectto));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Date date11 = null;
                Date date12 = null;
                try {
                    date11 = sdf.parse(fromDAte);
                    date12 = sdf.parse(toDAte);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (Long.parseLong(now_date) >= Long.parseLong(String.valueOf(date11.getTime()))) {
                    if (Long.parseLong(String.valueOf(date12.getTime())) >= Long.parseLong(now_date)) {
                        promoType = Cursor_Promo.getString(4);
                        //list.add(SubmitSaleObject + "__1__" + SubmitSaleObjectfrom + "__2__" + SubmitSaleObjectto);
                    } else {
                        promoType = "";
                    }
                } else {
                    promoType = "";

                }
            }
        } else {
            promoType = "";
        }
        return promoType;
    }

    private static String GetPromoItemID(String inventoryItemID) {
        String promoItemPromoID = "";

        Cursor Cursor_PromoItem = DBFunc.Query("SELECT PromoID,ItemID,Item_Qty,ItemUOM,ItemPrice " +
                "FROM Promo_Item WHERE ItemID = '" + inventoryItemID + "'", true);

        if (Cursor_PromoItem != null) {
            if (Cursor_PromoItem.moveToNext()) {
                promoItemPromoID = Cursor_PromoItem.getString(0);
            }
            Cursor_PromoItem.close();
        }
        return promoItemPromoID;
    }

    private static String GetInventoryID(String to_chk_promo_item) {
        String inventoryItemID ="0";
        Cursor Cursor_Inventory = DBFunc.Query("SELECT ItemID FROM Inventory " +
                "WHERE ItemDescp = \"" + to_chk_promo_item + "\"", true);

        if (Cursor_Inventory != null) {
            while (Cursor_Inventory.moveToNext()) {
                inventoryItemID = Cursor_Inventory.getString(0);
            }
            Cursor_Inventory.close();
        }
        return inventoryItemID;
    }

    public static void SavePayment(String paymentName, String disallowEmptyCash, String linktoPaymentApp, String integratetoShoptima,
                                   String roundingActivate, String amount, String thirdPartyPaymentID, String switchSTATUS,
                                   String fullAmount, String remarks, String ezlink) {

        try {
            String sql = "INSERT INTO Payment (Name,DisallowEmptyCash,LinktoPaymentApp,IntegratetoShoptima,RoundingActivate,Amount," +
                    "Option,ThirdPartyPaymentID,SwitchSTATUS,FullAmount,Remarks,Ezlink,DateTime) " +
                    "VALUES ('" + paymentName + "','" +
                    disallowEmptyCash + "','" +
                    linktoPaymentApp + "','" +
                    integratetoShoptima + "','" +
                    roundingActivate + "'," +
                    amount + ",'" +
                    //Option + "','" +
                    "0000000" + "','" +
                    thirdPartyPaymentID + "','" +
                    switchSTATUS + "','" +
                    fullAmount + "','" +
                    remarks + "','" +
                    ezlink + "'," +
                    System.currentTimeMillis() + ")";

            DBFunc.ExecQuery(sql, true);

            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                    System.currentTimeMillis(), DBFunc.PurifyString("Saved-Query-SavePayment-"+sql));
        } catch (Exception e){
            String str = "Name"+"-"+paymentName+",";
            str += "DisallowEmptyCash"+"-"+disallowEmptyCash+",";
            str += "LinktoPaymentApp"+"-"+linktoPaymentApp+",";
            str += "IntegratetoShoptima"+"-"+integratetoShoptima+",";
            str += "RoundingActivate"+"-"+roundingActivate+",";
            str += "Amount"+"-"+amount+",";
            str += "ThirdPartyPaymentID"+"-"+thirdPartyPaymentID+",";
            str += "SwitchSTATUS"+"-"+switchSTATUS+",";
            str += "FullAmount"+"-"+fullAmount+",";
            str += "Remarks"+"-"+remarks+",";
            str += "Ezlink"+"-"+ezlink+",";
//          DisallowEmptyCash,LinktoPaymentApp,IntegratetoShoptima,RoundingActivate,Amount," +
//                  "Option,ThirdPartyPaymentID,SwitchSTATUS,FullAmount,Remarks,Ezlink,DateTime) " +
//                  "VALUES ('" + paymentName + "','" +
//                  disallowEmptyCash + "','" +
//                  linktoPaymentApp + "','" +
//                  integratetoShoptima + "','" +
//                  roundingActivate + "'," +
//                  amount + ",'" +
//                  //Option + "','" +
//                  "0000000" + "','" +
//                  thirdPartyPaymentID + "','" +
//                  switchSTATUS + "','" +
//                  fullAmount + "','" +
//                  remarks + "','" +
//                  ezlink + "'," +
//                  System.currentTimeMillis() + ")";
            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                    System.currentTimeMillis(), DBFunc.PurifyString("Err-Query-SavePayment-"+str+"\n"+"-"+e.getMessage()));
        }
    }

//    public static void SaveReportSettings(String str_chk_sales, String str_chk_category, String str_chk_product_sales, String str_chk_payment, String str_chk_discount, String str_chk_tax, String str_chk_cancellation, String str_chk_refer_info) {
//        String sql = "INSERT INTO ReportSettings (Sales,Category,ProductSales,Payment,Discount,Tax,Cancellation,ReferInfoSales,DateTime) " +
//                "VALUES (" +
//                "'" + str_chk_sales + "'," +
//                "'" + str_chk_category + "'," +
//                "'" + str_chk_product_sales + "'," +
//                "'" + str_chk_payment + "'," +
//                "'" + str_chk_discount + "'," +
//                "'" + str_chk_tax + "'," +
//                "'" + str_chk_cancellation + "'," +
//                "'" + str_chk_refer_info + "'," +
//                System.currentTimeMillis() + ")";
//        Log.i("__sql",sql);
//        DBFunc.ExecQuery(sql, true);
//    }
public static void SaveReportSettings(String str_chk_sales, String str_chk_category, String str_chk_product_sales,
                                      String str_chk_payment, String str_chk_discount, String str_chk_tax,
                                      String str_chk_cancellation, String str_chk_refer_info, String str_chk_refund) {
    String sql = "INSERT INTO ReportSettings (Sales,Category,ProductSales,Payment,Discount,Tax,Cancellation,ReferInfoSales,Refund,DateTime) " +
            "VALUES (" +
            "'" + str_chk_sales + "'," +
            "'" + str_chk_category + "'," +
            "'" + str_chk_product_sales + "'," +
            "'" + str_chk_payment + "'," +
            "'" + str_chk_discount + "'," +
            "'" + str_chk_tax + "'," +
            "'" + str_chk_cancellation + "'," +
            "'" + str_chk_refer_info + "'," +
            "'" + str_chk_refund + "'," +
            System.currentTimeMillis() + ")";
    Log.i("__sql",sql);
    DBFunc.ExecQuery(sql, true);
}

    public static Cursor GetPromo(String billNo,String PLUID) {
        String sql = "Select PLUID,PromoName,PromoValue,PromoTypeID,PromoItemID,PromoID from PLUPromo Where BillNo = '"+billNo+"' and PLUID = '"+PLUID+"'";
        //Log.i("TAGGG_","sql__"+sql);
       return DBFunc.Query(sql,false);
    }

    public static Double GetPromoTotal(String billNo) {
        Double PromoTotalValue = 0.0;
        String str_promo_ = "Select SUM(PromoValue) from PLUPromo where BillNo = '"+billNo+"' ";
        Log.i("TAGG","str_promo___"+str_promo_);
        Cursor Cursor_Promo = DBFunc.Query(str_promo_,false);
        if (Cursor_Promo != null){
            if (Cursor_Promo.moveToNext()){
                PromoTotalValue = Cursor_Promo.getDouble(0);
            }
            Cursor_Promo.close();
        }
        return PromoTotalValue;
    }

//    public static void SaveSales(String billNo, Integer totalQty, Double sub_total, Double total_sales, String changeamount,
//                                 String paymentID, String paymentName, String payment_amount, Double gross_sales, Double itemDiscountAmount,
//                                 Double billdiscount, Double service_charges, Double total_nett_sales, Double amt_exclusive,
//                                 String cashier_name, String roundAmt,
//                                 Double bill_amt,Double amount,Double total_discount,
//                                 Double PromoTotalValue,String BillNo,String Changeamount) {
//
//        String dateFormat3 = CheckOutActivity.dateFormat55.format(new Date()).toString();
//        String dateFormat4 = CheckOutActivity.dateFormat555.format(new Date()).toString();
//        String billDiscountID = "";
//        String billDiscountName = "";
//        String billDiscountType = "";
//        String billDiscountTypeName = "";
//        String billDiscountValue = "";
//
//        if (CheckOutActivity.billDisID != null &&  CheckOutActivity.billDisID.length() > 0){
//            billDiscountID = CheckOutActivity.billDisID;
//            billDiscountName = CheckOutActivity.billDisName;
//            billDiscountType = CheckOutActivity.billDisType;
//            billDiscountTypeName = CheckOutActivity.billDisTypeName;
//            billDiscountValue = CheckOutActivity.billDisValue;
//        }else {
//            billDiscountID = CheckOutActivity.billDisIDAmt;
//            billDiscountName = CheckOutActivity.billDisNameAmt;
//            billDiscountType = CheckOutActivity.billDisTypeAmt;
//            billDiscountTypeName = CheckOutActivity.billDisTypeNameAmt;
//            billDiscountValue = CheckOutActivity.billDisValueAmt;
//        }
//
//        String s = "Select ID FROM Sales WHERE BillNo = '" +billNo + "'";
//        Cursor cc = DBFunc.Query(s,false);
//        if (cc != null) {
//            if (cc.getCount() == 0) {
//
//                String sql = "INSERT INTO Sales (BillNo,TotalQty,SubTotal,Totalamount,Changeamount,PaymentTypeID,PaymentTypeName,PaymentAmount," +
//                        "GrossSales,TotalItemDisount,TotalBillDisount,GrossTotal,ServiceCharges,TotalNettSales,TotalTaxes,CashierName,SalesDateTime,BillHour,STATUS," +
//                        "RoundAdj,DiscountID,DiscountName,DiscountType,DiscountTypeName,DiscountValue,DateTime) VALUES (";
//                sql +=  "'" + billNo + "', ";
//                sql += "'" + DBFunc.PurifyString(String.valueOf(totalQty)) + "', ";
//                sql += "'" + String.format("%.2f", Double.valueOf(sub_total)) + "', ";
//                sql += "'" + String.format("%.2f", Double.valueOf(total_sales)) + "', ";
//                sql += "'" + String.format("%.2f", Double.valueOf(changeamount)) + "', ";
//                sql += "'" + DBFunc.PurifyString(String.valueOf(paymentID)) + "', ";
//                sql += "'" + DBFunc.PurifyString(paymentName) + "', ";
//                sql += "'" + String.format("%.2f", Double.valueOf(payment_amount)) + "', ";
//                sql += "'" + String.format("%.2f", Double.valueOf(gross_sales)) + "', ";
//                sql += "'" + String.format("%.2f", Double.valueOf(itemDiscountAmount)) + "', ";
//                sql += "'" + String.format("%.2f", Double.valueOf(billdiscount)) + "', ";
//                sql += "'" + String.format("%.2f", Double.valueOf(gross_sales)) + "', ";
//                sql += "'" + String.format("%.2f", Double.valueOf(service_charges)) + "', ";
//                sql += "'" + String.format("%.2f", Double.valueOf(total_nett_sales))  + "', ";
//                sql += "'" + String.format("%.2f", Double.valueOf(amt_exclusive)) + "', ";
//                sql += "'" + DBFunc.PurifyString(String.valueOf(cashier_name)) + "', ";
//                sql += "'" + dateFormat3 + "', ";
//                sql += "'" + dateFormat4 + "', ";
//                sql += "'SALES', ";
//                sql += "'" + roundAmt + "', ";
//                sql += "'" + billDiscountID + "', ";
//                sql += "'" + billDiscountName + "', ";
//                sql += "'" + billDiscountType + "', ";
//                sql += "'" + billDiscountTypeName + "', ";
//                sql += "'" + billDiscountValue + "', ";
////        sql += "'"+billIDDD+"', ";
//                sql += System.currentTimeMillis() + ")";
//
//                DBFunc.ExecQuery(sql, false);
//
//                CheckOutActivity.billDisID = "0";
//                CheckOutActivity.billDisName = "";
//                CheckOutActivity.billDisType = "";
//                CheckOutActivity.billDisTypeName = "";
//                CheckOutActivity.billDisValue = "";
//
//                CheckOutActivity.billDisIDAmt = "0";
//                CheckOutActivity.billDisNameAmt = "";
//                CheckOutActivity.billDisTypeAmt = "";
//                CheckOutActivity.billDisTypeNameAmt = "";
//                CheckOutActivity.billDisValueAmt = "";
//
//
//                SaveBillPaymentIntoDBFun(bill_amt,amount,total_discount,amt_exclusive,PromoTotalValue,BillNo,
//                        paymentID,paymentName,Changeamount);
//
//            }
//        }
//
//
//        Query.updateBillIDByBillNoIntoSales(billNo);
//
//        Query.findOnHandQtyByBillNo(billNo);
////        HashMap<Integer,Integer> val = Query.findOnHandQtyByBillNo(billNo);
////        for (Map.Entry<Integer, Integer> set : val.entrySet()) {
////            Integer pluid = set.getKey();
////            Integer onHandQty = set.getValue();
////            Query.UpdateOnHandQtyByPLUID(pluid,onHandQty);
////        }
//        Query.DeleteProductQty();
//        Query.UpdateBillPayment();
//
//
//        String BillID = findBillIDByBillNo(billNo);
////        String STATUS = "PENDING";
////        String fieldName,String IdName, String Id,String tbName, Boolean flag
//        String TotalItems = "0";
//        String TableNo = "0";
//        String QueueNo = "0";
//        String TotalAmount = "0";
//        String OnlineOrderBill = "0";
//        String sqll = "SELECT Sales.BillNo,Sales.STATUS,Sales.TotalQty," +
//                "Sales.TotalNettSales,Sales.SalesDateTime,Sales.ID,Details_BillProduct.OnlineOrderBill," +
//                "vchQueueNo,intTableNo " +
//                " FROM Sales " +
//                " INNER JOIN Details_BillProduct on Details_BillProduct.BillNo = Sales.BillNo " +
//                " group by Sales.BillNo order by Sales.BillNo DESC ";
//        Log.i("__ddsql",sqll);
//        Cursor Cursor_DBP = DBFunc.Query(sqll,false);
//        if (Cursor_DBP != null){
//            if (Cursor_DBP.moveToNext()){
//                TotalItems = Cursor_DBP.getString(2);
//                TotalAmount = Cursor_DBP.getString(3);
//                OnlineOrderBill = Cursor_DBP.getString(6);
//                QueueNo = Cursor_DBP.getString(7);
//                TableNo = Cursor_DBP.getString(8);
//                //STATUS = Cursor_DBP.getString(1);
//                //STATUS = "PENDING";
//            }
//            Cursor_DBP.close();
//        }
//        DateFormat dateFormat55 = new SimpleDateFormat("dd MMM yyyy  hh:mm aa");
//        String Date = dateFormat55.format(new Date()).toString();
//        BillListModel blist = new BillListModel(BillID,billNo,"S",TotalItems,Date,TableNo,QueueNo,OnlineOrderBill,TotalAmount,0);
////        Log.i("Dfd_____STATUS","STATUS___"+STATUS);
//        UpdateBillList(blist,billNo);
//        Query.UpdateStatusBillList(billNo,ENUM.SALES.toUpperCase());
//        Query.UpdateIsClosedBillList(billNo);
//
//        String query = "UPDATE BillList SET ";
//        query += "CheckDate = '1', ";
//        query += "Date = '"+dateFormat3+"', ";
//        query += "DateTime = " + System.currentTimeMillis() + " ";
//        query += "WHERE BillNo = '"+billNo+"' ";
//
//        Log.i("DFdfd___sql","query___"+query);
//        DBFunc.ExecQuery(query, false);
//
//
//
////        if (billDiscountTypeName != null && billDiscountTypeName.length() > 1) {
////            if (billDiscountValue != null && Double.parseDouble(billDiscountValue) > 0.0) {
////                CalculateAndSaveGroupDiscountAmount(billNo, billDiscountTypeName, Double.parseDouble(billDiscountValue));
////            }
////        }
//    }

    public static void SaveSales(String billNo, Integer totalQty, Double sub_total, Double total_sales, String changeamount,
                                 String paymentID, String paymentName, String payment_amount, Double gross_sales, Double itemDiscountAmount,
                                 Double billdiscount, Double service_charges, Double total_nett_sales, Double amt_exclusive,
                                 String cashier_name, String roundAmt,
                                 Double bill_amt,Double amount,Double total_discount,
                                 Double PromoTotalValue,String BillNo,String Changeamount,Context context,
                                 String strCardOrCash,String card_label) {

        String dateFormat3 = CheckOutActivity.dateFormat55.format(new Date()).toString();
        String dateFormat4 = CheckOutActivity.dateFormat555.format(new Date()).toString();
        String billDiscountID = "";
        String billDiscountName = "";
        String billDiscountType = "";
        String billDiscountTypeName = "";
        String billDiscountValue = "";


        if (CheckOutActivity.billDisID != null &&  CheckOutActivity.billDisID.length() > 0){
            billDiscountID = CheckOutActivity.billDisID;
            billDiscountName = CheckOutActivity.billDisName;
            billDiscountType = CheckOutActivity.billDisType;
            billDiscountTypeName = CheckOutActivity.billDisTypeName;
            billDiscountValue = CheckOutActivity.billDisValue;
        }else {
            billDiscountID = CheckOutActivity.billDisIDAmt;
            billDiscountName = CheckOutActivity.billDisNameAmt;
            billDiscountType = CheckOutActivity.billDisTypeAmt;
            billDiscountTypeName = CheckOutActivity.billDisTypeNameAmt;
            billDiscountValue = CheckOutActivity.billDisValueAmt;
        }

        String s = "Select ID FROM Sales WHERE BillNo = '" +billNo + "'";
        Cursor cc = DBFunc.Query(s,false);
        if (cc != null) {
            if (cc.getCount() == 0) {
                //SALES
                String uniqueId  = UUID.randomUUID().toString();

                String BillID = findBillIDByBillNo(billNo);
                itemDiscountAmount = Query.GetItemDiscountAmount(billNo);

//                sub_total = Query.GetSubTotalValueFromDetailsBillProduct(BillNo);

                try {

                    //SavedSales
                    String sql = "INSERT INTO Sales (UUID,BillNo,BillID,TotalQty,SubTotal,Totalamount,Changeamount,PaymentTypeID,PaymentTypeName,PaymentAmount," +
                            "GrossSales,TotalItemDisount,TotalBillDisount,GrossTotal,ServiceCharges,TotalNettSales,TotalTaxes,CashierName,SalesDateTime,BillHour,STATUS," +
                            "RoundAdj,DiscountID,DiscountName,DiscountType,DiscountTypeName,DiscountValue,EwalletStatus,ReceiptRemarks,DateTime) VALUES (";
                    sql += "'" + uniqueId + "', ";
                    sql += "'" + billNo + "', ";
                    sql += "'" + BillID + "', ";
                    sql += "'" + DBFunc.PurifyString(String.valueOf(totalQty)) + "', ";
                    sql += "'" + String.format("%.2f", Double.valueOf(sub_total)) + "', ";
                    sql += "'" + String.format("%.2f", Double.valueOf(total_sales)) + "', ";
                    sql += "'" + String.format("%.2f", Double.valueOf(changeamount)) + "', ";
                    sql += "'" + DBFunc.PurifyString(String.valueOf(paymentID)) + "', ";
                    sql += "'" + DBFunc.PurifyString(paymentName) + "', ";
                    sql += "'" + String.format("%.2f", Double.valueOf(payment_amount)) + "', ";
                    sql += "'" + String.format("%.2f", Double.valueOf(gross_sales)) + "', ";
                    sql += "'" + String.format("%.2f", Double.valueOf(itemDiscountAmount)) + "', ";
                    sql += "'" + String.format("%.2f", Double.valueOf(billdiscount)) + "', ";
                    sql += "'" + String.format("%.2f", Double.valueOf(gross_sales)) + "', ";
                    sql += "'" + String.format("%.2f", Double.valueOf(service_charges)) + "', ";
                    sql += "'" + String.format("%.2f", Double.valueOf(total_nett_sales)) + "', ";
                    sql += "'" + String.format("%.2f", Double.valueOf(amt_exclusive)) + "', ";
                    sql += "'" + DBFunc.PurifyString(String.valueOf(cashier_name)) + "', ";
                    sql += "'" + dateFormat3 + "', ";
                    sql += "'" + dateFormat4 + "', ";
                    sql += "'SALES', ";
                    sql += "'" + roundAmt + "', ";
                    sql += "'" + billDiscountID + "', ";
                    sql += "'" + billDiscountName + "', ";
                    sql += "'" + billDiscountType + "', ";
                    sql += "'" + billDiscountTypeName + "', ";
                    sql += "'" + billDiscountValue + "', ";
                    if (strCardOrCash.equals("card")) {
                        sql += 1 + ", ";
                    } else {
                        sql += 0 + ", ";
                    }
//        sql += "'"+billIDDD+"', ";
                    sql += "'" + PaymentTypesCheckoutAdapter.payment_remarks + "', ";
                    sql += System.currentTimeMillis() + ")";

                    Log.i("DFDF___", "sql_savedsalesss_" + sql);
                    DBFunc.ExecQuery(sql, false);


                    DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                            System.currentTimeMillis(), DBFunc.PurifyString("Query -SavedSales-" + sql));

                    PaymentTypesCheckoutAdapter.payment_remarks = "";

                    Integer sale_id = Query.findLatestID("ID","Sales",false);

                    CashLayoutActivity.SaveSalesItem(BillNo,sub_total,totalQty,sale_id,Changeamount,paymentName,payment_amount,context,System.currentTimeMillis());



                    SaveBillPaymentIntoDBFun(context,bill_amt,amount,total_discount,amt_exclusive,PromoTotalValue,BillNo,
                            paymentID,paymentName,Changeamount,total_nett_sales,strCardOrCash,card_label,sale_id);

                } catch (Exception e){
                    DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                            System.currentTimeMillis(), DBFunc.PurifyString("Query -Err-SavedSales-" + e.getMessage()));
                }



            }
        }

//        String BillID = findBillIDByBillNo(billNo);
//        String sql = "UPDATE Sales SET BillID = '"+BillID+"' WHERE BillNo = '" + billNo + "'";
//        DBFunc.ExecQuery(sql, false);
//
////        Query.updateBillIDByBillNoIntoSales(billNo);
//
//        Query.findOnHandQtyByBillNo(billNo);
//
//        Query.DeleteProductQty();
//        Query.UpdateBillPayment();
//
//
////        String BillID = findBillIDByBillNo(billNo);
//
//        String TotalItems = "0";
//        String TableNo = "0";
//        String QueueNo = "0";
//        String TotalAmount = "0";
//        String OnlineOrderBill = "0";
//        String sqll = "SELECT Sales.BillNo,Sales.STATUS,Sales.TotalQty," +
//                "Sales.TotalNettSales,Sales.SalesDateTime,Sales.ID,Details_BillProduct.OnlineOrderBill," +
//                "vchQueueNo,intTableNo " +
//                " FROM Sales " +
//                " INNER JOIN Details_BillProduct on Details_BillProduct.BillNo = Sales.BillNo " +
//                " WHERE Sales.BillNo = '"+BillNo+"' " +
//                " group by Sales.BillNo order by Sales.BillNo DESC ";
//        Log.i("__ddsql",sqll);
//        Cursor Cursor_DBP = DBFunc.Query(sqll,false);
//        if (Cursor_DBP != null){
//            if (Cursor_DBP.moveToNext()){
//                TotalItems = Cursor_DBP.getString(2);
//                TotalAmount = Cursor_DBP.getString(3);
//                OnlineOrderBill = Cursor_DBP.getString(6);
//                QueueNo = Cursor_DBP.getString(7);
//                TableNo = Cursor_DBP.getString(8);
//                //STATUS = Cursor_DBP.getString(1);
//                //STATUS = "PENDING";
//            }
//            Cursor_DBP.close();
//        }
//        DateFormat dateFormat55 = new SimpleDateFormat("dd MMM yyyy  hh:mm aa");
//        String Date = dateFormat55.format(new Date()).toString();
//        BillListModel blist = new BillListModel(BillID,billNo,"S",TotalItems,Date,TableNo,QueueNo,OnlineOrderBill,TotalAmount,0);
////        Log.i("Dfd_____STATUS","STATUS___"+STATUS);
//        UpdateBillList(blist,billNo);
//        Query.UpdateStatusBillList(billNo,ENUM.SALES.toUpperCase());
//        Query.UpdateIsClosedBillList(billNo);
    }

    public static void SaveBillPaymentIntoDBFun(Context context,Double bill_amtt,Double amount,Double total_discount,Double amt_exclusive,
                                                 Double PromoTotalValue,String BillNo,String paymentID,
                                                 String paymentName,String Changeamount,Double total_nett_sales,
                                                String strCardOrCash,String card_label,Integer sale_id) {

        Double actual_amt = total_nett_sales;

        CashLayoutActivity.change_due_amt = 0.0;

        if (CheckOutActivity.CashValueArr.size() > 0){
//            Log.i("Sizzze","Sizzz___"+CheckOutActivity.CashValueArr.size());
            for (int i = 0 ; i < CheckOutActivity.CashValueArr.size(); i++){
                //bill_amt = amount - CashLayoutActivity.CashValue;
                //bill_amt = amount - CheckOutActivity.CashValueArr.get(i);
                actual_amt -= CheckOutActivity.CashValueArr.get(i);

                String str_info = "";
                String printrqr = Query.GetOptions(18);
                if (printrqr.equals("1")) {

                    str_info = Query.getStrInfoForQRCodeOnReceipt(BillNo, CheckOutActivity.CashValuePaymentNameArr.get(i),
                            CheckOutActivity.CashValueArr.get(i),
                            CheckOutActivity.CashValueArr.get(i));
                } else {
                    str_info = "";
                }


                String sql = "INSERT INTO BillPayment (BillNo,EwalletStatus,EwalletIssueBanker,EwalletPaymentType,PaymentRemarks,PaymentID,Name," +
                        "Amount,PaymentDateTime,QRCode," +
                        "ChangeAmount,SaleSync) VALUES (";
                //sql += BillNo + ", ";
                sql += "'"+BillNo+"'" + ", ";

                sql +=  0 + ", ";
                sql +=  "''" + ", ";
                sql +=  "''" + ", ";
//                sql += CheckOutActivity.CashValuePaymentRemarksArr.get(i) + ", ";
                sql += "'" + CheckOutActivity.CashValuePaymentRemarksArr.get(i) + "' , ";
                sql += CheckOutActivity.CashValuePaymentIDArr.get(i) + ", ";
                sql += "'" + CheckOutActivity.CashValuePaymentNameArr.get(i) + "', ";
                sql += Query.DoubleAmountFormat(CheckOutActivity.CashValueArr.get(i)) + ", ";
                sql += System.currentTimeMillis() + ", ";
                sql += "'" + str_info + "', ";
                sql += "'" + Query.DoubleAmountFormat(Double.valueOf("0")) + "', ";
//        sql += "'" + change_due_amt + "', ";
                sql += "'0')";
                Log.i("hggghggh", sql);
                DBFunc.ExecQuery(sql, false);

            }
        }else {
            actual_amt = total_nett_sales;
        }


        String getRoundActivateStatus = Query.GetRoundActivateInfo(paymentID);

        if (getRoundActivateStatus.equals("1")) {
            //bill_amt = bill_amt + CheckOutActivity.staticRound;
            //actual_amt = actual_amt + CheckOutActivity.staticRound;
        }
        String str_info = "";
        String printrqr = Query.GetOptions(18);
        if (printrqr.equals("1")) {
            str_info = Query.getStrInfoForQRCodeOnReceipt(BillNo, paymentName, actual_amt, actual_amt);
        } else {
            str_info = "";
        }

        Query.SaveBillPayment(context,BillNo,paymentID,paymentName,actual_amt,str_info,Changeamount,strCardOrCash,card_label,sale_id);
    }

//    private static void CalculateAndSaveGroupDiscountAmount(String billNo, String billDiscountTypeName, Double billDiscountValue) {
//        List<String> disArr = new ArrayList<String>();
//        HashMap<String,String> hashValues = new HashMap<String, String>();
//        Double totaldisArr = 0.0;
//        String sql = "SELECT BillNo,ProductQty,ProductID,ProductName,ProductPrice,ID FROM DetailsBillProduct " +
//                " WHERE BillNo = '" + billNo + "'  GROUP BY ProductQty ";
//        Cursor c = DBFunc.Query(sql, false);
//        String billno = "";
//        String productName = "";
//        String productPrice = "";
//        Integer qty = 0;
//        Integer productID = 0;
//        if (c != null) {
//            hashValues.clear();
//            disArr.clear();
//            totaldisArr = 0.0;
//            while (c.moveToNext()) {
//
//                billno = c.getString(0);
//                qty = c.getInt(1);
//                productID = c.getInt(2);
//                productName = c.getString(3);
//                productPrice = c.getString(4);
//
//                disArr.add(productPrice);
//                totaldisArr += c.getDouble(4);
//
//                hashValues.put(c.getString(5),productPrice);
//
//
////                if (billDiscountValue.equals("$ Dollar Value")) {
////
////                    if (!c.isLast()) {
////
////                    } else {
////
////                    }
////
////                }else {
////                    if (!c.isLast()) {
////
////                    } else {
////
////                    }
////                }
//            }
//            c.close();
//        }
//        // [50+100+50]
//        Double value = 0.0;
//
//        List<Double> ItmdisValueArr = new ArrayList<Double>();
//        HashMap<String,String> hashValuesItem = new HashMap<String, String>();
//
//        for (String i : hashValues.keySet()) {
//
//            Log.i("DFDF___","DI"+i);
//            if (billDiscountTypeName.equals("$ Dollar Value")) {
//                Log.i("DFDF___","i.lastIndexOf(i)"+i.lastIndexOf(i));
//                Log.i("DFDF___","i.lastIndexOf__"+disArr.size());
//                if (i.lastIndexOf(i)== disArr.size()) { //if last one
//
//                } else { // if not last one
//                    value = Double.parseDouble(disArr.get(i.indexOf(i))) / totaldisArr * billDiscountValue;
//                    ItmdisValueArr.add(value);
//
//                    //hashValuesItem.put(hashValues.get();
//                }
//            } else {
//
//                if (i.lastIndexOf(i)== disArr.size()) { //if last one
//
//                } else { // if not last one
//                    value = Double.parseDouble(disArr.get(i.indexOf(i))) / totaldisArr * billDiscountValue;
//                    ItmdisValueArr.add(value);
//                }
//            }
//        }
////        for (int i = 1; i <= disArr.size(); i++) {
////
////            if (billDiscountTypeName.equals("$ Dollar Value")) {
////
////                if (i == disArr.size()) { //if last one
////
////                } else { // if not last one
////                    value = Double.parseDouble(disArr.get(i)) / totaldisArr * billDiscountValue;
////                    ItmdisValueArr.add(value);
////                }
////            } else {
////
////                if (i == disArr.size()) { //if last one
////
////                } else { // if not last one
////
////                }
////            }
////
////        }
//
//
//    }

    public static void RunBillPayment() {
//        String sql = "UPDATE BillPayment SET STATUS = '"+status+"' AND isZ = '"+isZ+"' WHERE BillNo = '"+billNo+"' ";
//        Log.i("__sql","sql_UPDATE__"+sql);
//        DBFunc.ExecQuery(sql,true);


        //String sql = "SELECT BillNo FROM BillPayment WHERE isZ IS NULL ";
        String sql = "SELECT BillNo FROM BillPayment";

        Cursor c = DBFunc.Query(sql,false);
        if ( c != null) {
            while (c.moveToNext()) {
                String billPaymentbillNo = c.getString(0);

                String sql_sales = "SELECT STATUS,isZ,SalesDateTime FROM Sales WHERE BillNo = '"+ billPaymentbillNo +"' ";
                Cursor Cursor = DBFunc.Query(sql_sales,false);
                if (Cursor != null){
                    while (Cursor.moveToNext()){
                        String sstatus = Cursor.getString(0);
                        String sisZZ = Cursor.getString(1);
                        String dt = Cursor.getString(2);
//                        if (sisZZ.equals("null")){
//                            sisZZ = "";
//                        }
                        String updatesql = "UPDATE BillPayment SET STATUS = '"+sstatus+"' , " +
                                "isZ = '"+sisZZ+"' " +
                                "WHERE BillNo = '"+billPaymentbillNo+"' ";

                        DBFunc.ExecQuery(updatesql,false);
                    }
                    Cursor.close();
                }
            }
            c.close();
        }

        //String sql = "SELECT BillNo FROM BillPayment WHERE isZ IS NULL ";
        sql = "SELECT BillID,BillNo,ID FROM Sales WHERE STATUS = 'REFUND'";

        c = DBFunc.Query(sql,false);
        if ( c != null) {
            while (c.moveToNext()) {
                Integer billid = c.getInt(0);
                String billno = c.getString(1);
                String salidId = c.getString(2);

                String refunded_salesno = findBillNoByBillID(billid);


                String updatesql = "UPDATE Sales SET ReferenceBillNo = '"+refunded_salesno+"'," +
                        "ReferenceSalesID = '"+salidId+"' " +
                        "WHERE BillNo = '"+billno+"' ";

                DBFunc.ExecQuery(updatesql,false);

            }
            c.close();
        }


         sql = "SELECT BillNo FROM BillPayment WHERE (EWalletStatus = 0 || EWalletStatus IS NULL)";

         c = DBFunc.Query(sql,false);
        if ( c != null) {
            while (c.moveToNext()) {
                String billno = c.getString(0);

                String updatesql = "UPDATE BillPayment SET EwalletPaymentType = '' , " +
                        "EwalletIssueBanker = '' , " +
                        "EWalletStatus = 0 " +
                        "WHERE BillNo = '"+billno+"' ";
                Log.i("FG___","updatesql___"+updatesql);
                DBFunc.ExecQuery(updatesql,false);
            }
            c.close();
        }



    }

    public static void UpdateBillPayment(String billno) {
//        String sql = "UPDATE BillPayment SET STATUS = '"+status+"' AND isZ = '"+isZ+"' WHERE BillNo = '"+billNo+"' ";
//        Log.i("__sql","sql_UPDATE__"+sql);
//        DBFunc.ExecQuery(sql,true);


//        //String sql = "SELECT BillNo FROM BillPayment WHERE isZ IS NULL ";
//        String sql = "SELECT BillNo FROM BillPayment ";
//
//        Cursor c = DBFunc.Query(sql,false);
//        if ( c != null) {
//            while (c.moveToNext()) {
//                String billPaymentbillNo = c.getString(0);

//                String sql_sales = "SELECT STATUS,isZ FROM Sales WHERE BillNo = '"+ billPaymentbillNo +"' ";
                String sql_sales = "SELECT STATUS,isZ FROM Sales WHERE BillNo = '"+ billno +"' ";
                Cursor Cursor = DBFunc.Query(sql_sales,false);
                if (Cursor != null){
                    while (Cursor.moveToNext()){
                        String sstatus = Cursor.getString(0);
                        String sisZZ = Cursor.getString(1);
//                        if (sisZZ.equals("null")){
//                            sisZZ = "";
//                        }
//                        String updatesql = "UPDATE BillPayment SET STATUS = '"+sstatus+"' , isZ = '"+sisZZ+"' WHERE BillNo = '"+billPaymentbillNo+"' ";
                        String updatesql = "UPDATE BillPayment SET STATUS = '"+sstatus+"' , isZ = '"+sisZZ+"' WHERE BillNo = '"+billno+"' ";

                        DBFunc.ExecQuery(updatesql,false);
                    }
                    Cursor.close();
                }
//            }
//            c.close();
//        }
    }

    public static void UpdateOnHandQtyByPLUID(Integer pluID, Integer onHandQty) {
        String sql = "UPDATE PLU SET OnHandQty = '"+onHandQty+"' WHERE ID = "+pluID+" ";
        Log.i("__sql","sql_UPDATE_dd_"+sql);
        DBFunc.ExecQuery(sql,true);
    }

    //private static HashMap<Integer,Integer> findOnHandQtyByBillNo(String billNo) {
    public static void findOnHandQtyByBillNo(String billNo) {
        //HashMap<Integer,Integer> val = new HashMap<Integer, Integer>();
        //Integer onHandQty = 0;
        String sql = "SELECT SUM(ProductQty),ProductID FROM DetailsBillProduct WHERE BillNo = '"+billNo+"' " +
                "GROUP BY ProductID";
        //String sql = "SELECT Count(ProductQty),ProductQty FROM Details_BillProduct GROUP BY ProductQty ";
//        String sql = "SELECT Count(ProductQty),ProductQty FROM Details_BillProduct ";

        Cursor c = DBFunc.Query(sql,false);
        if ( c != null){
            while (c.moveToNext()){
                Integer plu_id = c.getInt(1);
                //Integer stockInQty_Sales = c.getInt(0);
                Integer soldQty = c.getInt(0);

                String stockInQty = Query.SearchStockInValueByPLUID(plu_id,"Qty");
//                if (Integer.parseInt(stockInQty) > 0){
                    //String variantQty = SearchStockAdjValueByPLUID(plu_id);
                    //Integer old_onHandQty = Integer.parseInt(stockInQty) + Integer.parseInt(variantQty);
                    //Integer old_onHandQty = Integer.parseInt(stockInQty) - soldQty;
                    Integer old_onHandQty = findOnHandQtyByPLUID(plu_id);
                    //Integer new_onHandQty = old_onHandQty - soldQty;
                    //Integer new_onHandQty = Integer.parseInt(stockInQty) - soldQty;
                    //Integer onHandQty = old_onHandQty - new_onHandQty;

                    Integer onHandQty = old_onHandQty - soldQty;

                    //Integer onHandQty = old_onHandQty - stockInQty_Sales;
                    //val.put(stockInQty_Sales,onHandQty);

//                   Integer PLUID;
//                   Integer Qty;
//                   Integer VarianceQty;
//                   String DateTime;
//                   String TransNo;
                    //Integer total_variantQty = Integer.parseInt(variantQty) - stockInQty_Sales;
                    //total_variantQty = (-1)* total_variantQty;
                    //Log.i("Dfd___sql","sql__variantQty__"+variantQty);
                    //StockAdjustment stockAdjustment = new StockAdjustment(plu_id,Integer.parseInt(stockInQty),total_variantQty,"11","00000");
                    //Query.SaveStockAdjustment(stockAdjustment);


                    UpdateOnHandQtyByPLUID(plu_id,onHandQty);

                    Integer stockAgentQtySold = soldQty;
                    Integer stockAgentQtyAdjustment = 0;
                    Integer stockAgentQtyReturn = 0;
                    Integer stockAgentQtyBalance = 0;
                    Integer stockAgentQtyActual = Query.findOnHandQtyByPLUID(plu_id);;
                    Integer stockAgentPLUID = plu_id;
                    //String stockAgentDateTime = "11";

//                    //String stockAgentDateTime = CheckOutActivity.dateFormat55.format(new Date());
//                    String stockAgentDateTime = GetDateFormart55();
//
//                    StockAgent stockAgent = new StockAgent(stockAgentQtySold,stockAgentQtyAdjustment,stockAgentQtyReturn,stockAgentQtyBalance,stockAgentQtyActual,stockAgentPLUID,stockAgentDateTime);
//                    Query.SaveStockAgent(stockAgent);
                String searchPLUStockAgent = "SELECT PLUID FROM StockAgent WHERE PLUID = "+plu_id;
                Cursor csearchPLUStockAgent = DBFunc.Query(searchPLUStockAgent,true);
                if (csearchPLUStockAgent != null) {
                    if (csearchPLUStockAgent.getCount() == 0){
                        //String stockAgentDateTime = CheckOutActivity.dateFormat55.format(new Date());
                        String stockAgentDateTime = GetDateFormart55();

                        StockAgent stockAgent = new StockAgent(stockAgentQtySold,stockAgentQtyAdjustment,stockAgentQtyReturn,
                                stockAgentQtyBalance,stockAgentQtyActual,stockAgentPLUID,stockAgentDateTime);
                        Query.SaveStockAgent(stockAgent);

                    } else {
                        Query.UpdateStockAgent(plu_id,stockAgentQtyActual);
                    }
                    csearchPLUStockAgent.close();
                }

//                }
                //c.close();
            }
            c.close();
        }

        //Log.i("Dfd___sql","sql__val__"+val);
        //return val;
    }

    public static Integer findSalesIDBySalesNo(String billNo) {
        Integer sale_id = 0;
        String sql_sales = "SELECT ID FROM Sales WHERE BillNo = '"+ billNo +"' ";
        Cursor Cursor = DBFunc.Query(sql_sales,false);
        if (Cursor != null){
            if (Cursor.moveToNext()){
                sale_id = Cursor.getInt(0);
            }
            Cursor.close();
        }
        return sale_id;
    }
    public static Bitmap bitmap_qr_shoptima = null;
    static Bitmap bitmap__ = null;
    public static void SaveBillPayment(final Context context, final String billNo, String paymentID, String paymentName, Double paymentValue,
                                       String str_info, String changeAmt, String strCardOrCash, String card_label, final Integer sale_id) {

        if (paymentValue == null){
            paymentValue = 0.0;
        }
        try {
            String sql = "INSERT INTO BillPayment (BillNo,EwalletStatus,EwalletIssueBanker,EwalletPaymentType,PaymentID,Name,Amount,PaymentDateTime,QRCode,ChangeAmount,SaleSync) VALUES (";
            //sql += BillNo + ", ";
            sql += "'" + billNo + "'" + ", ";
            if (strCardOrCash.equals("card")) {
                sql += 1 + ", ";
            } else {
                sql += 0 + ", ";
            }

            if (card_label != null && card_label.length() > 5) {
                try {
                    sql += "'" + card_label.split("~")[0] + "',";
                    //sql += "'" + card_label.split("~")[1] + "',";
                } catch (ArrayIndexOutOfBoundsException e) {
                    sql += "'',";
                    //sql += "'',";
                }
            } else {
                sql += "''" + ", ";
                //sql += "''" + ", ";
            }
            if (card_label != null && card_label.length() > 5) {
                try {
                    //sql += "'" + card_label.split("~")[0] + "',";
                    sql += "'" + card_label.split("~")[1] + "',";
                } catch (ArrayIndexOutOfBoundsException e) {
                    //sql += "'',";
                    sql += "'',";
                }
            } else {
                //sql += "''" + ", ";
                sql += "''" + ", ";
            }
            sql += paymentID + ", ";
            sql += "'" + DBFunc.PurifyString(DBFunc.PurifyString(paymentName)) + "', ";
            sql += Query.DoubleAmountFormat(Double.valueOf(paymentValue)) + ", ";
            sql += System.currentTimeMillis() + ", ";
            sql += "'" + str_info + "', ";
            sql += "'" + Query.DoubleAmountFormat(Double.valueOf(changeAmt)) + "', ";
//        sql += "'" + change_due_amt + "', ";
            sql += "'0')";

            DBFunc.ExecQuery(sql, false);

            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                    System.currentTimeMillis(), DBFunc.PurifyString("Savedd-Query-SaveBillPayment" +  sql));

        } catch (Exception e){
            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                    System.currentTimeMillis(), DBFunc.PurifyString("Err-Query-SaveBillPayment" +   e.getMessage() +
                            ",BillNo-"+billNo + ",strCardOrCash-"+strCardOrCash+
                            ",card_label-"+card_label +
                            ",paymentID-"+paymentID+
                            ",paymentName-"+paymentName+
                            ",paymentValue-"+paymentValue+
                            ",changeAmt-"+changeAmt));
        }
//        DBFunc.ExecQuery(sql, false);

//
//        String BillID = findBillIDByBillNo(billNo);
//        sql = "UPDATE Sales SET BillID = '"+BillID+"' WHERE BillNo = '" + billNo + "'";
//        DBFunc.ExecQuery(sql, false);

//        Query.updateBillIDByBillNoIntoSales(billNo);

//        Query.findOnHandQtyByBillNo(billNo);

//        Query.DeleteProductQty(billNo);
//        Query.UpdateBillPayment(billNo);
////
//
////        String BillID = findBillIDByBillNo(billNo);
//
//        String TotalItems = "0";
//        String TableNo = "0";
//        String QueueNo = "0";
//        String TotalAmount = "0";
//        String OnlineOrderBill = "0";
//        String sqll = "SELECT Sales.BillNo,Sales.STATUS,Sales.TotalQty," +
//                "Sales.TotalNettSales,Sales.SalesDateTime,Sales.ID,Details_BillProduct.OnlineOrderBill," +
//                "vchQueueNo,intTableNo " +
//                " FROM Sales " +
//                " INNER JOIN Details_BillProduct on Details_BillProduct.BillNo = Sales.BillNo " +
//                " WHERE Sales.BillNo = '"+billNo+"' " +
//                " group by Sales.BillNo order by Sales.BillNo DESC ";
//        Log.i("__ddsql",sqll);
//        Cursor Cursor_DBP = DBFunc.Query(sqll,false);
//        if (Cursor_DBP != null){
//            if (Cursor_DBP.moveToNext()){
//                TotalItems = Cursor_DBP.getString(2);
//                TotalAmount = Cursor_DBP.getString(3);
//                OnlineOrderBill = Cursor_DBP.getString(6);
//                QueueNo = Cursor_DBP.getString(7);
//                TableNo = Cursor_DBP.getString(8);
//                //STATUS = Cursor_DBP.getString(1);
//                //STATUS = "PENDING";
//            }
//            Cursor_DBP.close();
//        }
//        DateFormat dateFormat55 = new SimpleDateFormat("dd MMM yyyy  hh:mm aa");
//        String Date = dateFormat55.format(new Date()).toString();
//        BillListModel blist = new BillListModel(BillID,billNo,"S",TotalItems,Date,TableNo,QueueNo,OnlineOrderBill,TotalAmount,0);
////        Log.i("Dfd_____STATUS","STATUS___"+STATUS);
//        UpdateBillList(blist,billNo);
//        Query.UpdateStatusBillList(billNo,ENUM.SALES.toUpperCase());
//        Query.UpdateIsClosedBillList(billNo);
//
//
//
//        CheckOutActivity.billDisID = "0";
//        CheckOutActivity.billDisName = "";
//        CheckOutActivity.billDisType = "";
//        CheckOutActivity.billDisTypeName = "";
//        CheckOutActivity.billDisValue = "";
//
//        CheckOutActivity.billDisIDAmt = "0";
//        CheckOutActivity.billDisNameAmt = "";
//        CheckOutActivity.billDisTypeAmt = "";
//        CheckOutActivity.billDisTypeNameAmt = "";
//        CheckOutActivity.billDisValueAmt = "";
//
//        CheckOutActivity.CashValuePaymentNameArr.clear();
//        CheckOutActivity.CashValuePaymentIDArr.clear();
//        CheckOutActivity.CashValueArr.clear();
//
//        CashLayoutActivity.change_due_amt = 0.0;
//
//
////        //Receipt Print Function
////        final String chk_qr_code_on_receipt = Query.GetOptions(18);
////
////
////        new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
////                .setTitleText(ENUM.Print)
////                .setConfirmText(ENUM.YES)
////                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
////                    @Override
////                    public void onClick(SweetAlertDialog sDialog) {
////                        sDialog.dismissWithAnimation();
////
////                        if (sale_id > 0) {
////
////                            bitmap_qr_shoptima = null;
////                            if (chk_qr_code_on_receipt.equals("1")) {
////                                //bitmap_qr_shoptima = Query.GetPrintQRCodeOnReceipt(context);
////                                //Query.GetPrintQRCodeOnReceipt(context);
////                                CashLayoutActivity.GetPrintQRCodeOnReceipt(context,billNo);
////
////                            }else {
////
////                                bitmap__ = null;
////                                bitmap__ = Query.GetLogPrint();
////
////                                Query.CheckEmulatorOrNot(context, sale_id, billNo,ENUM.SALES,ENUM.SALES,bitmap__,bitmap_qr_shoptima);
////
////                                CheckOutActivity.str_percentage_value = "0";
////                                CheckOutActivity.discount_amount = 0.0;
////                            }
////
////
////                        }
////                    }
////                })
////                .setCancelButton(ENUM.No, new SweetAlertDialog.OnSweetClickListener() {
////                    @Override
////                    public void onClick(SweetAlertDialog sDialog) {
////                        sDialog.dismissWithAnimation();
////
////                        Toast.makeText(context, ENUM.SALES + " Successfully.", Toast.LENGTH_SHORT).show();
////                        Intent intent = new Intent(context, TransactionDetailsActivity.class);
////                        intent.putExtra("BillNo", billNo);
////                        context.startActivity(intent);
////                    }
////                })
////                .show();


//        //Sync to Backend Function
//        Cursor config_values_pro_item = null;
//        String url = "";
//        config_values_pro_item = DBFunc.Query("SELECT retails_id,company_code,company_url,option FROM POSSys", true);
//        if (config_values_pro_item != null) {
//            while (config_values_pro_item.moveToNext()) {
//                url = config_values_pro_item.getString(2);
//            }
//        }
//        if (url != null) {
//            Log.i("Successs___","SSSSSS"+url);
//            SyncActivity.ResyncOrNormal(context,CheckOutActivity.BillNo,"","Normal","");
//        }

    }
//    public static void SaveBillPayment(final Context context, final String billNo, String paymentID, String paymentName, Double paymentValue,
//                                       String str_info, String changeAmt, String strCardOrCash, String card_label, final Integer sale_id) {
//        String sql = "INSERT INTO BillPayment (BillNo,EwalletStatus,EwalletIssueBanker,EwalletPaymentType,PaymentID,Name,Amount,PaymentDateTime,QRCode,ChangeAmount,SaleSync) VALUES (";
//        //sql += BillNo + ", ";
//        sql += "'"+billNo+"'" + ", ";
//        if (strCardOrCash.equals("card")){
//            sql +=  1 + ", ";
//        }else {
//            sql +=  0 + ", ";
//        }
//        if (card_label != null && card_label.length() > 5){
//            try {
//                sql += "'"+card_label.split("~")[0]+"'," ;
//                sql += "'"+card_label.split("~")[1]+"'," ;
//            }catch (ArrayIndexOutOfBoundsException e){
//                sql += "''," ;
//                sql += "''," ;
//            }
//        }else {
//            sql +=  "''" + ", ";
//            sql +=  "''" + ", ";
//        }
//        sql += paymentID + ", ";
//        sql += "'" + DBFunc.PurifyString(DBFunc.PurifyString(paymentName)) + "', ";
//        sql += Query.DoubleAmountFormat(Double.valueOf(paymentValue)) + ", ";
//        sql += System.currentTimeMillis() + ", ";
//        sql += "'" + str_info + "', ";
//        sql += "'" + Query.DoubleAmountFormat(Double.valueOf(changeAmt)) + "', ";
////        sql += "'" + change_due_amt + "', ";
//        sql += "'0')";
//        Log.i("Sf_SaveBillPayment", sql);
//        DBFunc.ExecQuery(sql, false);
////        DBFunc.ExecQuery(sql, false);
//
////
////        String BillID = findBillIDByBillNo(billNo);
////        sql = "UPDATE Sales SET BillID = '"+BillID+"' WHERE BillNo = '" + billNo + "'";
////        DBFunc.ExecQuery(sql, false);
//
////        Query.updateBillIDByBillNoIntoSales(billNo);
//
////        Query.findOnHandQtyByBillNo(billNo);
//
////        Query.DeleteProductQty(billNo);
////        Query.UpdateBillPayment(billNo);
//////
////
//////        String BillID = findBillIDByBillNo(billNo);
////
////        String TotalItems = "0";
////        String TableNo = "0";
////        String QueueNo = "0";
////        String TotalAmount = "0";
////        String OnlineOrderBill = "0";
////        String sqll = "SELECT Sales.BillNo,Sales.STATUS,Sales.TotalQty," +
////                "Sales.TotalNettSales,Sales.SalesDateTime,Sales.ID,Details_BillProduct.OnlineOrderBill," +
////                "vchQueueNo,intTableNo " +
////                " FROM Sales " +
////                " INNER JOIN Details_BillProduct on Details_BillProduct.BillNo = Sales.BillNo " +
////                " WHERE Sales.BillNo = '"+billNo+"' " +
////                " group by Sales.BillNo order by Sales.BillNo DESC ";
////        Log.i("__ddsql",sqll);
////        Cursor Cursor_DBP = DBFunc.Query(sqll,false);
////        if (Cursor_DBP != null){
////            if (Cursor_DBP.moveToNext()){
////                TotalItems = Cursor_DBP.getString(2);
////                TotalAmount = Cursor_DBP.getString(3);
////                OnlineOrderBill = Cursor_DBP.getString(6);
////                QueueNo = Cursor_DBP.getString(7);
////                TableNo = Cursor_DBP.getString(8);
////                //STATUS = Cursor_DBP.getString(1);
////                //STATUS = "PENDING";
////            }
////            Cursor_DBP.close();
////        }
////        DateFormat dateFormat55 = new SimpleDateFormat("dd MMM yyyy  hh:mm aa");
////        String Date = dateFormat55.format(new Date()).toString();
////        BillListModel blist = new BillListModel(BillID,billNo,"S",TotalItems,Date,TableNo,QueueNo,OnlineOrderBill,TotalAmount,0);
//////        Log.i("Dfd_____STATUS","STATUS___"+STATUS);
////        UpdateBillList(blist,billNo);
////        Query.UpdateStatusBillList(billNo,ENUM.SALES.toUpperCase());
////        Query.UpdateIsClosedBillList(billNo);
////
////
////
////        CheckOutActivity.billDisID = "0";
////        CheckOutActivity.billDisName = "";
////        CheckOutActivity.billDisType = "";
////        CheckOutActivity.billDisTypeName = "";
////        CheckOutActivity.billDisValue = "";
////
////        CheckOutActivity.billDisIDAmt = "0";
////        CheckOutActivity.billDisNameAmt = "";
////        CheckOutActivity.billDisTypeAmt = "";
////        CheckOutActivity.billDisTypeNameAmt = "";
////        CheckOutActivity.billDisValueAmt = "";
////
////        CheckOutActivity.CashValuePaymentNameArr.clear();
////        CheckOutActivity.CashValuePaymentIDArr.clear();
////        CheckOutActivity.CashValueArr.clear();
////
////        CashLayoutActivity.change_due_amt = 0.0;
////
////
//////        //Receipt Print Function
//////        final String chk_qr_code_on_receipt = Query.GetOptions(18);
//////
//////
//////        new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
//////                .setTitleText(ENUM.Print)
//////                .setConfirmText(ENUM.YES)
//////                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//////                    @Override
//////                    public void onClick(SweetAlertDialog sDialog) {
//////                        sDialog.dismissWithAnimation();
//////
//////                        if (sale_id > 0) {
//////
//////                            bitmap_qr_shoptima = null;
//////                            if (chk_qr_code_on_receipt.equals("1")) {
//////                                //bitmap_qr_shoptima = Query.GetPrintQRCodeOnReceipt(context);
//////                                //Query.GetPrintQRCodeOnReceipt(context);
//////                                CashLayoutActivity.GetPrintQRCodeOnReceipt(context,billNo);
//////
//////                            }else {
//////
//////                                bitmap__ = null;
//////                                bitmap__ = Query.GetLogPrint();
//////
//////                                Query.CheckEmulatorOrNot(context, sale_id, billNo,ENUM.SALES,ENUM.SALES,bitmap__,bitmap_qr_shoptima);
//////
//////                                CheckOutActivity.str_percentage_value = "0";
//////                                CheckOutActivity.discount_amount = 0.0;
//////                            }
//////
//////
//////                        }
//////                    }
//////                })
//////                .setCancelButton(ENUM.No, new SweetAlertDialog.OnSweetClickListener() {
//////                    @Override
//////                    public void onClick(SweetAlertDialog sDialog) {
//////                        sDialog.dismissWithAnimation();
//////
//////                        Toast.makeText(context, ENUM.SALES + " Successfully.", Toast.LENGTH_SHORT).show();
//////                        Intent intent = new Intent(context, TransactionDetailsActivity.class);
//////                        intent.putExtra("BillNo", billNo);
//////                        context.startActivity(intent);
//////                    }
//////                })
//////                .show();
//
//
////        //Sync to Backend Function
////        Cursor config_values_pro_item = null;
////        String url = "";
////        config_values_pro_item = DBFunc.Query("SELECT retails_id,company_code,company_url,option FROM POSSys", true);
////        if (config_values_pro_item != null) {
////            while (config_values_pro_item.moveToNext()) {
////                url = config_values_pro_item.getString(2);
////            }
////        }
////        if (url != null) {
////            Log.i("Successs___","SSSSSS"+url);
////            SyncActivity.ResyncOrNormal(context,CheckOutActivity.BillNo,"","Normal","");
////        }
//
//    }

    public static void CheckEmulatorOrNot(Context context,Integer sale_id,String billNo,String status,String redirectStatus,
                                          Bitmap bitmap__,Bitmap bitmap_qr_shoptima) {

        Log.i("exception___","eee__"+"CheckEmulatorOrNot");
        if (!Query.isEmulator()) {

            try {

                CashLayoutActivity.PrintReceiptFun(context, sale_id, billNo,status,bitmap__,bitmap_qr_shoptima);
            }catch (Exception e){

                Log.i("exception___","eee__"+e.getMessage());

            }
        }else {
            Intent i = new Intent(context, TransactionDetailsActivity.class);
            if (redirectStatus.toUpperCase().equals(Constraints.SALES.toUpperCase())){

                i = new Intent(context, PaymentCashSuccesActivity.class); // If Emulator And Status is SALES
            }else {
                i = new Intent(context, TransactionDetailsActivity.class);
            }
            context.startActivity(i);
            ((Activity)context).finish();
        }
    }

    public static Integer CheckServiceCharges() {
        int Service_Charges = 0;
        String sql = "SELECT ServiceCharges from Tax WHERE ServiceCharges = 1";
        Cursor Cursor_Tax = DBFunc.Query(sql,true);
        if (Cursor_Tax != null){
            if (Cursor_Tax.moveToNext()){
                Service_Charges = Cursor_Tax.getInt(0);
            }
            Cursor_Tax.close();
        }
        return Service_Charges;
    }

    public static String GetServiceChargesNameAndPercentage() {
        Integer taxRate = 0;
        String str_service_charges_name = "";
        Cursor Cursor_tax = Query.GetServiceCharges();
        if (Cursor_tax != null){
            if (Cursor_tax.moveToNext()){
                taxRate = Cursor_tax.getInt(0);
                str_service_charges_name = Cursor_tax.getString(2);
            }
            Cursor_tax.close();
        }
        return str_service_charges_name.toUpperCase()+"("+taxRate+"%)";
    }

    public static void ShowBillDiscountAtCheckoutPage() {
        CheckOutActivity.binding.checkoutOrderSummary.txtItemDiscountCheckout.setVisibility(View.VISIBLE);
        String  disName = "";
        if (CheckOutActivity.billDisID != null &&  CheckOutActivity.billDisID.length() > 0){
            disName = "\n" + CheckOutActivity.billDisName + "("+CheckOutActivity.billDisValue+"%)";
        }else {
            disName = "\n" + CheckOutActivity.billDisNameAmt + "($"+CheckOutActivity.billDisValueAmt+")";
        }

        CheckOutActivity.binding.checkoutOrderSummary.billDiscountName.setText("Bill Discount"+disName);
        CheckOutActivity.binding.checkoutOrderSummary.txtDiscountCheckout.setText("-"+"$"+String.valueOf(String.format("%.2f", CheckOutActivity.discount_amount)));
    }

    private static Integer productTaxID(int productID) {
        Integer productTaxID = 0;
        String sql = "SELECT ProductTaxID FROM PLU WHERE ID = "+productID;
        Cursor Cursor_PLU = DBFunc.Query(sql,true);
        if (Cursor_PLU != null){
            if (Cursor_PLU.moveToNext()){
                productTaxID = Cursor_PLU.getInt(0);
            }
            Cursor_PLU.close();
        }
        return productTaxID;
    }

    public static Double findDetailsBillProductTaxValueByBillNo(String billNo,String status) {
        Double taxAmount = 0.0;
        String query = "";
        if (status.equals("All")){
            query = " ";
        }else {
            query = " AND TaxType = '2' ";
        }
        String sql = "SELECT SUM(TaxAmount) FROM DetailsBillProduct WHERE BillNo = '"+billNo+"' " +
                "AND Cancel = 'SALES' " + query;
        Cursor Cursor_DetailsBillProduct = DBFunc.Query(sql,false);
        if (Cursor_DetailsBillProduct != null){
            taxAmount = 0.0;
             while (Cursor_DetailsBillProduct.moveToNext()){
                 taxAmount += Cursor_DetailsBillProduct.getDouble(0);
            }
            Cursor_DetailsBillProduct.close();
        }
        return taxAmount;
    }

    public static Double GetExclusiveTaxAmounByBillNo(String billNo) {
        Double tax_amt = 0.0;
        String sql = "SELECT TaxAmount FROM DetailsBillProduct WHERE BillNo = '"+billNo+"' AND Cancel = 'SALES' AND TaxType = '2' ";
        //Log.i("TAGGG__","sql_"+sql);
        Cursor Cursor_DetailBP = DBFunc.Query(sql,false);
        if (Cursor_DetailBP != null){
            tax_amt = 0.0;
            while (Cursor_DetailBP.moveToNext()){
                tax_amt += Cursor_DetailBP.getDouble(0);
            }
            Cursor_DetailBP.close();
        }
        //Log.i("TAGGG__","sqltax_amt_"+tax_amt);
        return tax_amt;
    }

    public static String getBillDetailsBillProductByBillNo(String billNo) {
        String bill_details_ID = "";

        String sql = "Select ID FROM DetailsBillProduct Where BillNo = '"+billNo+"' ";
        Log.i("___sql","__sql__"+sql);
        Cursor c = DBFunc.Query(sql, false);
        if (c != null) {
            if (c.moveToNext()) {
                bill_details_ID = c.getString(0);
            }
            c.close();
        }
        return bill_details_ID;
    }

    public static String ShowAmtMinusCorrectly(Double amt) {
        String st_sv_ch = "";
        if (amt < 0.0){
            amt = (-1) * amt;
            st_sv_ch = "-" + "$" + String.format("%.2f", amt);
        }else {
            st_sv_ch = "$" + String.format("%.2f", amt);
        }
        return st_sv_ch;
    }

    public static Cursor SearchOnlinePaymentBill(String str_newText) {
        String sql = "SELECT Sales.BillNo,Sales.TotalQty," +
                "Sales.TotalNettSales,Sales.SalesDateTime,Sales.ID," +
                "Bill.vchQueueNo,Bill.intTableNo,Sales.STATUS,Sales.BillID " +
                "FROM Sales INNER JOIN Bill ON Bill.TransNo = Sales.BillNo " +
                "Where Bill.OnlineOrderBill = 'ON' ";
        if (str_newText.length() > 0){
            sql += " AND Sales.BillNo LIKE '%" + str_newText.toUpperCase() + "%' OR " +
                    " Bill.vchQueueNo LIKE '%" + str_newText.toUpperCase() + "%' OR " +
                    " Bill.intTableNo LIKE '%" + str_newText.toUpperCase() + "%' OR " +
                    " Sales.STATUS = '"+str_newText+"' " ;
        }
        sql += "group by Sales.BillNo order by Sales.BillNo DESC";
        return DBFunc.Query(sql, false);
    }

    public static String  SearchProductIDByUUID(String ittemID_item_payment) {
        String productID = "";
        String product_id_sql = "SELECT ID FROM PLU WHERE UUID = '"+ittemID_item_payment+"'";
        Cursor c = DBFunc.Query(product_id_sql,true);
        if (c != null){
            if (c.moveToNext()){
                productID = c.getString(0);
            }
            c.close();
        }
        return productID;
    }

    public static Integer GetQtyByBillNo(String billNo) {
        Integer Qty = 0;
        String product_id_sql = "SELECT SUM(ProductQty) FROM DetailsBillProduct WHERE BillNo = '"+billNo+"'";
        Cursor c = DBFunc.Query(product_id_sql,false);
        if (c != null){
            while (c.moveToNext()){
                Qty = c.getInt(0);
            }
            c.close();
        }
        return Qty;
    }

    public static Integer GetSalesByBillNo(String billNo) {
        Integer sale_id = 0;
        String bill_no_sql = "SELECT ID FROM Sales WHERE BillNo = '"+billNo+"'";
        Cursor c = DBFunc.Query(bill_no_sql,false);
        if (c != null){
            if (c.moveToNext()){
                sale_id = c.getInt(0);
            }
            c.close();
        }
        return sale_id;
    }

    public static void SaveSalesDelivery(SalesDelivery sd){
        DBFunc.ExecQuery("DELETE FROM SalesDelivery WHERE SalesDelivery_ID = '"+sd.getSalesDelivery_ID()+"'",false);
        String save_sql = "INSERT INTO SalesDelivery (SalesDelivery_ID, Type, CoyCode, CompanyID, CompanyAddr ," +
                "CompanyTel,CompanyFax,RecipientName,RecipientAddr,RecipientPostCode ," +
                "RecipientAttn,RecipientTel,RecipientFax,OrderStatus,IDRef ," +
                "TrackingNumber,Date,SN_Ref,INVID,INVRef ," +
                "INVDate,GstRate,BalSubTotal,BalTax,TotalDiscount ," +
                "BalTotal,BalPayable,OutStandingBal,LocalBalSubTotal,LocalTax ," +
                "LocalTotalDiscount,LocalTotal,LocalBalPayable,LocalOutStandingBal,DepositAmount,DateTime) VALUES " +
                "( '"+sd.getSalesDelivery_ID()+"', '"+sd.getType()+"', '"+sd.getCoyCode()+"', '"+sd.getCompanyID()+"',"+
                "'"+sd.getCompanyAddr()+"', '"+sd.getCompanyTel()+"', '"+sd.getCompanyFax()+"', '"+sd.getRecipientName()+"',"+
                "'"+sd.getRecipientAddr()+"', '"+sd.getRecipientPostCode()+"', '"+sd.getRecipientAttn()+"', '"+sd.getRecipientTel()+"', '"+sd.getRecipientFax()+"',"+
                "'"+sd.getOrderStatus()+"', '"+sd.getIDRef()+"', '"+sd.getTrackingNumber()+"', '"+sd.getDate()+"', '"+sd.getSN_Ref()+"',"+
                "'"+sd.getINVID()+"',"+
                "'"+sd.getINVRef()+"',"+
                "'"+sd.getINVDate()+"',"+
                "'"+sd.getGstRate()+"',"+
                "'"+sd.getBalSubTotal()+"',"+
                "'"+sd.getBalTax()+"',"+
                "'"+sd.getTotalDiscount()+"',"+
                "'"+sd.getBalTotal()+"',"+
                "'"+sd.getBalPayable()+"',"+
                "'"+sd.getOutStandingBal()+"',"+
                "'"+sd.getLocalBalSubTotal()+"',"+
                "'"+sd.getLocalTax()+"',"+
                "'"+sd.getLocalTotalDiscount()+"',"+
                "'"+sd.getLocalTotal()+"',"+
                "'"+sd.getLocalBalPayable()+"',"+
                "'"+sd.getLocalOutStandingBal()+"',"+
                "'"+sd.getDepositAmount()+"',"+
                "" + System.currentTimeMillis() + ")";
        DBFunc.ExecQuery(save_sql,false);
    }

    public static void SaveSalesDeliveryItem(SalesDeliveryItem sdi){
        DBFunc.ExecQuery("DELETE FROM SalesDeliveryItem WHERE SalesDeliveryItem_ID = '"+sdi.getSalesDeliveryItem_ID()+"'",false);

        String save_sql = "INSERT INTO SalesDeliveryItem (SalesDeliveryItem_ID, KeyCol, ItemID, SupBarItem, SupBarItemID ," +
                "ItemRemark,PriceLevel,ActualQty,ActualSOQty,Currency ," +
                "ExchRate,GST,GSTType,GSTRate,ItemPrice ," +
                "ItemCost,ItemDiscAmt,ItemUnit,ItemUnitID,ItemBaseUnit ," +
                "ItemBaseUnitID,ItemSubTotal,ItemGST,TotalDisc,Total ," +
                "LocalItemPrice,LocalItemDiscAmt,LocalTotalDisc,LocalItemSubTotal,LocalItemGST ," +
                "LocalTotal,ItemFOC,ExpireDate,DateTime) VALUES " +
                "( '"+sdi.getSalesDeliveryItem_ID()+"', '"+sdi.getKeyCol()+"', '"+sdi.getItemID()+"', '"+sdi.getSupBarItem()+"',"+
                "'"+sdi.getSupBarItemID()+"', '"+sdi.getItemRemark()+"', '"+sdi.getPriceLevel()+"', '"+sdi.getActualQty()+"',"+
                "'"+sdi.getActualSOQty()+"', '"+sdi.getCurrency()+"', '"+sdi.getExchRate()+"', '"+sdi.getGST()+"', '"+sdi.getGSTType()+"',"+
                "'"+sdi.getGSTRate()+"', '"+sdi.getItemPrice()+"', '"+sdi.getItemCost()+"', '"+sdi.getItemDiscAmt()+"', '"+sdi.getItemUnit()+"',"+
                "'"+sdi.getItemUnitID()+"',"+
                "'"+sdi.getItemBaseUnit()+"',"+
                "'"+sdi.getItemBaseUnitID()+"',"+
                "'"+sdi.getItemSubTotal()+"',"+
                "'"+sdi.getItemGST()+"',"+
                "'"+sdi.getTotalDisc()+"',"+
                "'"+sdi.getTotal()+"',"+
                "'"+sdi.getLocalItemPrice()+"',"+
                "'"+sdi.getLocalItemDiscAmt()+"',"+
                "'"+sdi.getLocalTotalDisc()+"',"+
                "'"+sdi.getLocalItemSubTotal()+"',"+
                "'"+sdi.getLocalItemGST()+"',"+
                "'"+sdi.getLocalTotal()+"',"+
                "'"+sdi.getItemFOC()+"',"+
                "'"+sdi.getExchRate()+"',"+
                "" + System.currentTimeMillis() + ")";
        DBFunc.ExecQuery(save_sql,false);
    }

    public static String SearchSalesDelivery(String psd_salesDelivery_id) {
        String  saleDeliveryID = "0";
        String sql = "SELECT ID FROM SalesDelivery WHERE SalesDelivery_ID = '"+psd_salesDelivery_id+"' ";
        Cursor c = DBFunc.Query(sql,false);
        if (c != null){
            if (c.moveToNext()){
                saleDeliveryID = c.getString(0);
            }
            c.close();
        }
        return saleDeliveryID;
    }

    public static String GetSalesDelivery(String billNo) {
        String text = "";
        String sql = "SELECT SalesDelivery.RecipientName,SalesDelivery.RecipientTel,SalesDelivery.IDRef,SalesDelivery.Date " +
                " FROM SALES " +
                " INNER JOIN SalesDelivery ON SALES.SalesDeliveryID = SalesDelivery.ID "+
                " INNER JOIN Bill ON SALES.BillNo = Bill.TransNo " +
                " WHERE Bill.OnlineOrderBill = 'ON' AND Sales.BillNo = '"+billNo+"' ";
        Cursor c = DBFunc.Query(sql,false);
        if (c != null){
            if (c.moveToNext()){
               String RecipientName = c.getString(0);
               String RecipientTel = c.getString(1);
               String IDRef = c.getString(2);
               String Date = c.getString(3);
                text = "<font color='#a9aaad'>Status #</font>"+"Delivery";
                if (RecipientName.length() > 0) {
                    text += "<br/><font color='#a9aaad'>Recipient Name #</font>" + RecipientName;
                }
                if (RecipientTel.length() > 0) {
                    text += "<br/><font color='#a9aaad'>Recipient Tel #</font>" + RecipientTel;
                }
                if (IDRef.length() > 0) {
                    text += "<br/><font color='#a9aaad'>ID Ref #</font>" + IDRef;
                }
                if (Date.length() > 0) {
                    text += "<br/><font color='#a9aaad'>Delivery Date #</font>" + Date;
                }
            }else {

                String c_bill_sql = "SELECT OnlineOrderBill From Bill Where TransNo = '"+billNo+"' AND OnlineOrderBill = 'ON' ";
                Cursor c_bill = DBFunc.Query(c_bill_sql,false);
                if (c_bill != null){
                    if (c_bill.moveToNext()) {
                        text = "<font color='#a9aaad'>Status #</font>" + "Collected";
                    }
                    c_bill.close();
                }
            }
            c.close();
        }
        return text;
    }

    public static Integer GetOnlineSalesByBillNo(String billNo) {
        Integer sale_id = 0;
         String sql = "SELECT ID FROM SALES WHERE SalesDeliveryID IS NOT NULL";
         Cursor c = DBFunc.Query(sql,false);
         if (c !=null){
             if (c.moveToNext()){
                 sale_id = c.getInt(0);
             }
             c.close();
         }
         return sale_id;
    }

    public static void UpdateCollectedOrDeliveryByBillNo(String status, String billNo) {
        String sql = "UPDATE SALES SET CollectedOrDelivery = '"+status+"' WHERE BillNo = '"+billNo+"'";
        DBFunc.ExecQuery(sql,false);
    }

    public static String GetCollectedOrDeliveryStatusByBillNo(String billNo) {
        String status = "";
        String sql = "SELECT CollectedOrDelivery FROM SALES WHERE BillNo = '"+billNo+"' ";
        Cursor c = DBFunc.Query(sql,false);
        if (c!=null){
            if (c.moveToNext()){
                status = c.getString(0);
            }
            c.close();
        }
        return status;
    }

//    public static String SaveImageToServerFolder() {
//        String server = "ftp://49.128.60.174/";
//        int port = 21;
//        String user = "8000713";
//        String pass = "8000713";
//        FTPClient ftpClient = new FTPClient();
//        try {
//
//                ftpClient.connect(server, port);
//                ftpClient.login(user, pass);
//                ftpClient.enterLocalPassiveMode();
//
//                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
//
//                // APPROACH #1: uploads first file using an InputStream
//                File firstLocalFile = new File("D:/Test/Projects.zip");
//
//                String firstRemoteFile = "Projects.zip";
//                InputStream inputStream = new FileInputStream(firstLocalFile);
//
//                System.out.println("Start uploading first file");
//                boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
//                inputStream.close();
//                if (done) {
//                    System.out.println("The first file is uploaded successfully.");
//                }
//
//                // APPROACH #2: uploads second file using an OutputStream
//                File secondLocalFile = new File("E:/Test/Report.doc");
//                String secondRemoteFile = "test/Report.doc";
//                inputStream = new FileInputStream(secondLocalFile);
//
//                System.out.println("Start uploading second file");
//                OutputStream outputStream = ftpClient.storeFileStream(secondRemoteFile);
//                byte[] bytesIn = new byte[4096];
//                int read = 0;
//
//                while ((read = inputStream.read(bytesIn)) != -1) {
//                    outputStream.write(bytesIn, 0, read);
//                }
//                inputStream.close();
//                outputStream.close();
//
//                boolean completed = ftpClient.completePendingCommand();
//                if (completed) {
//                    System.out.println("The second file is uploaded successfully.");
//                }
//
//            } catch (IOException ex) {
//                System.out.println("Error: " + ex.getMessage());
//                ex.printStackTrace();
//            } finally {
//                try {
//                    if (ftpClient.isConnected()) {
//                        ftpClient.logout();
//                        ftpClient.disconnect();
//                    }
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                }
//            }
//        }
//    }


//    public static String SaveImageToServerFolder(){
//        String folderPath = Server.MapPath("~/ImagesFolder/");  //Create a Folder in your Root directory on your solution.
//        string fileName = "IMageName.jpg";
//        string imagePath = folderPath + fileName;
//
//        string base64StringData = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAlgAAADmCAYAAAA..........."; // Your base 64 string data
//        string cleandata = base64StringData.Replace("data:image/png;base64,", "");
//        byte[] data = System.Convert.FromBase64String(cleandata);
//        MemoryStream ms = new MemoryStream(data);
//        System.Drawing.Image img = System.Drawing.Image.FromStream(ms);
//        img.Save(imagePath, System.Drawing.Imaging.ImageFormat.Jpeg);
//    }

//    void FTPSUpload(Map<Long, File> filereport, boolean implicit, boolean protpmode){
//        FTPSClient ftpClient = null;
//        //Boolean stop = true;
//
//        for(long batchID : filereport.keySet()){
//
//            //if(stop)return;
//            try{
//                ftpClient = new FTPSClient(implicit);
//                ftpClient.setConnectTimeout(5000);
//                ftpClient.connect(ftpadd,ftpport);
//
//                if(ftpClient.login(ftpuser, ftppass)){
//                    //ftpClient.enterLocalActiveMode();
//                    if(ftpactivemode){
//                        ftpClient.enterLocalActiveMode();
//                    }else{
//                        ftpClient.enterLocalPassiveMode();
//                    }
//                    ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
//                    if(protpmode){
//                        ftpClient.execPROT("P");
//                    }else{
//                        ftpClient.execPROT("C");
//                    }
//                    if(ftpClient.changeWorkingDirectory(ftppath)){
//
//                        //Log.e("BATCH ID", batchID+">>>>>>>>>>>>>>>"+filereport.get(batchID));
//                        boolean success = false;
//                        String errcode = "";
//                        File filename = filereport.get(batchID);
//                        for(int retry=0;retry<3;retry++){
//                            if(stop)return;
//                            try{
//                                Log.i("GRIBReport FTP", "Report Uploading > "+filename);
//                                if(filename.isDirectory()){
//                                    Log.i("GRIBReport FTP", "Create Dir > "+filename.getName());
//                                    ftpClient.mkd(filename.getName());
//                                    //ftpClient.changeWorkingDirectory(filename.getName());
//                                    File[] files = filename.listFiles();
//                                    success = true;
//                                    for(File f:files){
//                                        if(stop)return;
//                                        Log.i("GRIBReport FTP", "Report Uploading > "+f.getAbsolutePath());
//                                        InputStream is = new FileInputStream(f);
//
//
//                                        Calendar cal = Calendar.getInstance();
//                                        cal.setTimeZone(TimeZone.getTimeZone("GMT+8"));
//                                        cal.get(Calendar.HOUR_OF_DAY);
//                                        cal.get(Calendar.MINUTE);
//                                        //success = ftpClient.storeFile("/"+filename.getName()+"/"+dateFormatFile.format(cal.getTime())+"/"+f.getName(), is);
//
//                                        //success = ftpClient.storeFile("/"+filename.getName()+"/a/"+f.getName(), is);
//
//                                        success = ftpClient.storeFile("/"+filename.getName()+"/"+f.getName(), is);
//                                        if(!success){//error happens on FTP
//                                            break;
//                                        }
//                                        //sftpchan.put(f.getAbsolutePath(), f.getName());
//                                    }
//
//                                }else{
//                                    InputStream is = new FileInputStream(filename);
//                                    success = ftpClient.storeFile(filename.getName(), is);
//                                    is.close();
//                                }
//
//                                if(success){
//
//                                    DBFunc.ExecQuery("UPDATE batch_info SET status = 1, errmsg = '', time = "+Calendar.getInstance().getTimeInMillis()+" WHERE id = "+batchID, true);
//                                    Log.i("GRIBReport FTP", "Report Uploaded > "+filename);
//                                    break;
//                                }else{
//                                    String errmsg = ftpClient.getReplyString();
//                                    errcode = Base64.encodeToString(errmsg.getBytes(), Base64.DEFAULT);;
//                                    Log.e("GRIBReport FTP Upload",errmsg);
//                                }
//
//                                //if success break;
//                                //success = true;
//
//                            }catch(IOException e){
//
//                                String errmsg = e.getMessage()+"\r\n";
//                                for(StackTraceElement ste : e.getStackTrace()){
//                                    errmsg += ste.toString()+"\r\n";
//                                }
//                                errcode = Base64.encodeToString(errmsg.getBytes(), Base64.DEFAULT);
//                                Log.w("GRIBReport FTP", "Failed Upload > "+filename);
//                                Log.e("GRIBReport FTP", e.toString());
//                                Log.w("GRIBReport FTP", "Retrying "+(retry+1)+"/3");
//
//                            }
//                        }
//                        if(!success){
//                            DBFunc.ExecQuery("UPDATE batch_info SET status = -1, errmsg = '"+DBFunc.PurifyString(errcode)+"', time = "+Calendar.getInstance().getTimeInMillis()+" WHERE id = "+batchID, true);
//                        }
//                    }else{
//                        String errmsg = "FTP Path doesn't exist!";
//
//                        String errcode = Base64.encodeToString(errmsg.getBytes(), Base64.DEFAULT);
//                        DBFunc.ExecQuery("UPDATE batch_info SET status = -1, errmsg = '"+DBFunc.PurifyString(errcode)+"', time = "+Calendar.getInstance().getTimeInMillis()+" WHERE id = "+batchID, true);
//
//                    }
//                }else{
//                    String errmsg = "FTP Username/Password is wrong!";
//
//                    String errcode = Base64.encodeToString(errmsg.getBytes(), Base64.DEFAULT);
//                    DBFunc.ExecQuery("UPDATE batch_info SET status = -1, errmsg = '"+DBFunc.PurifyString(errcode)+"', time = "+Calendar.getInstance().getTimeInMillis()+" WHERE id = "+batchID, true);
//                }
//
//
//                if(ftpClient.isConnected()){
//                    ftpClient.logout();
//                    ftpClient.disconnect();
//                }
//            }catch(Exception e){
//
//                String errmsg = e.getMessage()+"\r\n";
//                for(StackTraceElement ste : e.getStackTrace()){
//                    errmsg += ste.toString()+"\r\n";
//                }
//                errmsg = errmsg + "---test---" + f_t;
//                String errcode = Base64.encodeToString(errmsg.getBytes(), Base64.DEFAULT);
//                DBFunc.ExecQuery("UPDATE batch_info SET status = -1, errmsg = '"+DBFunc.PurifyString(errcode)+"', time = "+Calendar.getInstance().getTimeInMillis()+" WHERE id = "+batchID, true);
//            }
//            try{
//                if(ftpClient!=null){
//                    if(ftpClient.isConnected()){
//                        ftpClient.logout();
//                        ftpClient.disconnect();
//                    }
//                }
//            }catch(Exception e){}
//        }
//
//
//    }

    public static void SaveStockIn(StockIn stockIn){
//        '"+stockIn.getDateTime()+"',
        String sql = "INSERT INTO StockIn (PLUID,Qty,DateTime) VALUES (" +
                stockIn.getPLUID()+", "+stockIn.getQty()+", "+System.currentTimeMillis()+")";
        Log.i("Stock","SaveStockIn_"+sql);
        //DBFunc.Query(sql,true);
        DBFunc.ExecQuery(sql,true);

    }

    public static void SaveStockOut(StockOut stockOut){
        String sql = "INSERT INTO StockOut (PLUID,Qty,DateTime) VALUES (" +
                stockOut.getPLUID()+", "+stockOut.getQty()+", "+stockOut.getDateTime()+","+System.currentTimeMillis()+")";
        Log.i("Stock","SaveStockOut_"+sql);
        DBFunc.ExecQuery(sql,true);

    }


    public static void SaveStockTake(StockTake stockTake){
        String sql = "INSERT INTO StockTake (SystemQty,VarianceQty,Qty,TransNo,PLUID,DateTime) VALUES (" +
                stockTake.getSystemQty()+", "+stockTake.getVarianceQty()+", "+stockTake.getQty()+","+stockTake.getTransNo()+","+stockTake.getPLUID()+","+System.currentTimeMillis()+")";
        Log.i("Stock","SaveStockTake_"+sql);
        DBFunc.ExecQuery(sql,true);

    }

    public static void SaveStockAdjustment(StockAdjustment stockAdj){
//        String sql = "INSERT INTO StockAdjustment (PLUID,Qty,VarianceQty,TransNo,DateTime) VALUES (" +
//                stockAdj.getPLUID()+", "+stockAdj.getQty()+", "+stockAdj.getVarianceQty()+","+stockAdj.getTransNo()+","+System.currentTimeMillis()+")";
//        Log.i("Stock","SaveStockAdjustment_"+sql);
//        DBFunc.ExecQuery(sql,true);

//        Integer PLUID, Integer qty, Integer varianceQty, String dateTime,
//                String transNo, String UUID, String IDRef, String stkAdj_Type,
//                String stkAdjDate, String stkAdj_Remark, String stkAdj_DRemark, String transStatus
        String sql = "INSERT INTO StockAdjustment (PLUID,Qty,VarianceQty,DateTime,TransNo," +
                "UUID,IDRef,stkAdj_Type,stkAdjDate,stkAdj_Remark,stkAdj_DRemark," +
                "transStatus) VALUES (" +
                stockAdj.getPLUID()+"" +
                ","+stockAdj.getQty()+
                ", "+stockAdj.getVarianceQty()+
                ","+System.currentTimeMillis() +
                ",'"+stockAdj.getTransNo()+ "'" +
                ",'"+stockAdj.getUUID()+ "'" +
                ",'"+stockAdj.getIDRef()+ "'" +
                ",'"+stockAdj.getStkAdj_Type()+ "'" +
                ",'"+stockAdj.getStkAdjDate()+ "'" +
                ",'"+stockAdj.getStkAdj_Remark()+ "'" +
                ",'"+stockAdj.getStkAdj_DRemark()+ "'" +
                ",'"+stockAdj.getTransStatus()+ "'" +
                ")";
        Log.i("Stock","SaveStockAdjustment_"+sql);
        DBFunc.ExecQuery(sql,true);

    }

    public static void SaveStockAgent(StockAgent stockAgent){
       // String sql = "INSERT INTO StockAgent (QtySold,QtyAdjustment,QtyReturn,QtyBalance,QtyActual,PLUID,DateTime) VALUES (" +
        String sql = "INSERT INTO StockAgent (QtyAdjustment,QtyReturn,QtyBalance,QtyActual,PLUID,DateTime) VALUES (" +
                //stockAgent.getQtySold()+", "+stockAgent.getQtyAdjustment()+", "+stockAgent.getQtyReturn()+","+
                stockAgent.getQtyAdjustment()+", "+stockAgent.getQtyReturn()+","+
                stockAgent.getQtyBalance()+","+stockAgent.getQtyActual()+"," + stockAgent.getPLUID() +", "+System.currentTimeMillis()+")";
        Log.i("Stock","SaveStockAdjustment_"+sql);
        DBFunc.ExecQuery(sql,true);
    }

    public static void SaveReceiptEditor(Context applicationContext,Bitmap bitmap,String header, String footer,Integer print_status, Integer img_status) {
        String sql = "INSERT INTO ReceiptEditor (Logo,HeaderValue,FooterValue,Options,ImageStatus,DateTime) VALUES (";
        if (bitmap == null){
            bitmap = BitmapFactory.decodeResource(applicationContext.getResources(), R.drawable.default_no_image);
        }
        sql += "'" + DBFunc.PurifyString(AddNewProductActivity.BitMapToString(bitmap)) + "',";
        sql +=  "'" +header + "', ";
        sql +=  "'" +footer + "', ";
        sql += print_status + ", ";
        sql += img_status + ", ";
        sql += System.currentTimeMillis() + ")";
        Log.i("Sf__SaveReceiptEditor",sql);
        DBFunc.ExecQuery(sql, true);
    }

    public static void DeleteImageLogoReceiptEditor(Context applicationContext,String tableName, String fieldName,String IdQuery) {
        Bitmap bitmap = BitmapFactory.decodeResource(applicationContext.getResources(), R.drawable.default_no_image);
        String sql = "UPDATE "+tableName+" SET "+fieldName+" = '"+ DBFunc.PurifyString(AddNewProductActivity.BitMapToString(bitmap)) + "' "+IdQuery;
        DBFunc.ExecQuery(sql,true);
    }

    public static String SearchStockInValueByPLUID(int id,String fieldName) {
        String qty = "0";
        String sql = "Select "+fieldName+" FROM StockIn WHERE PLUID = "+ id + " ORDER BY ID DESC";
        Cursor c = DBFunc.Query(sql,true);
        if (c != null){
            if (c.moveToNext()){
                qty = c.getString(0);
            }
            c.close();
        }
        return qty;
    }

    public static String SearchStockAdjValueByPLUID(int id,String fieldName) {
        String qty = "0";
        String sql = "Select "+fieldName+" FROM StockAdjustment WHERE PLUID = "+ id + " ORDER BY ID DESC";
        Cursor c = DBFunc.Query(sql,true);
        if (c != null){
            if (c.moveToNext()){
                qty = c.getString(0);
                Log.i("df__qty","qty___"+qty);
            }
            c.close();
        }
        return qty;
    }

    public static Integer GetOnHandQtyByPLU(Integer product_id) {
        Integer Qty = 0;
        //String sql = "Select OnHandQty FROM PLU WHERE ID = "+ product_id;
        String sql = "Select OnHandQty FROM PLU WHERE ID = "+ product_id;
        Cursor c = DBFunc.Query(sql,true);
        if (c != null){
            if (c.moveToNext()){
                //Qty = c.getInt(0) + c.getInt(1);
                Qty = c.getInt(0) ;
                Log.i("df__qty","qty__Qtyyyy_"+Qty);
            }
            c.close();
        }
        return Qty;
    }

    public static List<StockAgent> getStockAgent() {
        List<StockAgent> stockAgent = new ArrayList<StockAgent>();
        String sql = "Select ID,QtyAdjustment,QtyReturn,QtyBalance,QtyActual,PLUID FROM StockAgent " +
                "GROUP BY PLUID ORDER BY PLUID ASC";

        Log.i("dfdfd___","sql__stockAgent____"+sql);
        Cursor c = DBFunc.Query(sql,true);
        if (c != null){
            while (c.moveToNext()){
                Integer qtySold = c.getInt(1);
                Integer qtyAdjustment = c.getInt(1);
                Integer qtyReturn = c.getInt(2);
                Integer qtyBalance = c.getInt(3);
                Integer qtyActual = c.getInt(4);
                Integer PLUID = c.getInt(5);
                String dateTime = c.getString(0);

                String s = "Select ID FROM PLU WHERE ID = " +PLUID;
                Cursor cc = DBFunc.Query(s,true);
                if (cc != null) {
                    if (cc.getCount() == 0) {
                        DBFunc.ExecQuery("DELETE FROM StockAgent WHERE PLUID = '"+PLUID+"'", true);
                    }
                    cc.close();
                }
                StockAgent stock_agent = new StockAgent(qtySold,qtyAdjustment,qtyReturn,qtyBalance,qtyActual,PLUID,dateTime);
                stockAgent.add(stock_agent);
               // Log.i("dfdfd___", String.valueOf(stockAgent));
            }
            c.close();
        }
        return stockAgent;
    }


    public static Integer findOnHandQtyByPLUID(Integer plu_id) {
        Integer onHandqty =  0;
        String sql = "Select OnHandQty FROM PLU WHERE ID = "+ plu_id + " ORDER BY ID DESC";
        Cursor c = DBFunc.Query(sql,true);
        if (c != null){
            if (c.moveToNext()){
                onHandqty = c.getInt(0);
            }
            c.close();
        }
        return onHandqty;
    }

    public static void UpdateStockAgent(Integer plu_ID,Integer actualQty) {



//        Integer QtySold;
//        Integer QtyAdjustment;
//        Integer QtyReturn;
//        Integer QtyBalance;
//        Integer QtyActual;
//        Integer PLUID;
//        String DateTime;
//        String sql = "Select QtySold,QtyAdjustment,QtyReturn,QtyBalance,QtyActual,PLUID " +
//                "FROM StockAgent " +
//                "WHERE PLUID = "+ plu_ID + " " +
//                "ORDER BY ID DESC";
//        Cursor c = DBFunc.Query(sql,true);
//        if (c != null){
//            if (c.moveToNext()){
//                QtySold = c.getInt(0);
//                QtyAdjustment = c.getInt(1);
//                QtyReturn = c.getInt(2);
//                QtyBalance = c.getInt(3);
//                QtyActual = c.getInt(4);
//                PLUID = c.getInt(5);
//            }
//            c.close();
//        }

        String query = "UPDATE StockAgent SET ";
        query += " QtyActual = "+ actualQty +" ,";
        query += " DateTime = '" + System.currentTimeMillis() +"' ";
        query += " WHERE PLUID = "+plu_ID+"";

        DBFunc.ExecQuery(query, true);
        Log.i("sdd__sales_br_id", String.valueOf(query));
    }

    public static void CreateNewBillAndDetailsBillProduct() {

        Query.SaveBill("","","","","","OFF");

        Integer billID__ = Query.findLatestID("BillNo","Bill",false);
//                    String vchQueueNo, String intTableNo, String str_Cancel, Integer billId, String taxID, String taxName, String slddisID,
//                    String slddisName, String slddisType, String slddisTypeName, String discountValue, Double after_dis_amt, String openPriceStatus,
//                    String beforeOpenPrice
        Integer billID_str = Query.findLatestID("BillNo","Bill",false);
        //billID_str = billID_str +1;


        //String strbillNo = MainActivity.billNo(billID_str);
        String strbillNo = Query.findBillNoByBillID(billID_str);
        MainActivity.strbillNo = strbillNo;

//        Query.SaveDetailsBillProduct(strbillNo,"OFF",-1,"0","0",
//                0,"0","-1","0","0",
//                "0","0","SALES",billID__,"0","","",
//                "","","","",0.00,"","0");
//        String sql = "insert into Details_BillProduct (BillNo,OnlineOrderBill,ProductQty,ProductID,Cancel,BillID,DateTime) " +
//                "VALUES (" +
//                " '"+ strbillNo+"' ," +
//                "'OFF'," +
//                -1 +
//                ",'-1'," +
//                "'SALES',"+
//                billID__+","+
//                System.currentTimeMillis()+
//                ")";
        //DBFunc.ExecQuery(sql,false);
//        String sql = "INSERT INTO Details_BillProduct (BillNo,OnlineOrderBill,ProductQty,ProductName,ProductPrice,BillDetailsID,BillDateTime," +
//                "ItemDiscountAmount,ProductID,CategoryID,CategoryName,vchQueueNo,intTableNo,Cancel," +
//                "BillID,TaxID,TaxType,TaxName,TaxAmount,DiscountID,DiscountName,DiscountType,DiscountTypeName,DiscountValue,AfterDiscountAmount,OpenPriceStatus,BeforeOpenPrice,DateTime) VALUES (";
//        sql += "'" + billNo + "', ";
//        sql += "'" + onlineOrderBill + "', ";
    }

    public static void DeleteProductQty(String billNo){
        DBFunc.ExecQuery("delete from DetailsBillProduct  where ProductQty = '-1' " +
                " AND BillNo = '"+billNo+"' ",false);
    }

    public static void SaveBillList(String OnlineOrderBill){

        String BillID = String.valueOf(Query.findLatestID("BillNo","Bill",false));

//        Integer offlineBillNo = 0;
//        String  onlineOrderBill = "0";
//        String BillNo = "";
//        String sql = "SELECT OnlineOrderBill FROM Bill where "+
//                " BillNo = "+BillID+" Order By TransNo DESC";
////        String sql = "SELECT OfflineBillNo FROM Bill where OnlineOrderBill = 'OFF' " +
////                " AND BillNo = "+BillID+" Order By TransNo DESC";
//
//        Cursor c = DBFunc.Query(sql,false);
//        if (c != null){
//            if (c.moveToNext()){
//                onlineOrderBill = c.getString(0);
//            }
//            c.close();
//        }
//
//        if (onlineOrderBill.equals("OFF")){
//
//            sql = "SELECT OfflineBillNo FROM BillList where OnlineOrderBill = 'OFF' " +
//                " AND BillID = '"+BillID+"' Order By BillNo DESC";
//
//            c = DBFunc.Query(sql,false);
//            if (c != null){
//                if (c.moveToNext()){
//                    offlineBillNo = c.getInt(0) + 1;
//                    BillNo = findBillNoByBillID(offlineBillNo);
//                }
//                c.close();
//            }
//        }else {

        String BillNo = findBillNoByBillID(Integer.parseInt(BillID));
//        }

        //String STATUS = "SALES";
        String STATUS = "PENDING";
        String TotalItems = "0";
        //DateFormat dateFormat55 = new SimpleDateFormat("dd MMM yyyy  hh:mm aa");
        //String Date = dateFormat55.format(new Date()).toString();

        String Date = GetDateFormart55();


        String TableNo = "0";
        String QueueNo = "0";
        String TotalAmount = "0";
        BillListModel blist = new BillListModel(BillID,BillNo,STATUS,TotalItems,Date,TableNo,QueueNo,OnlineOrderBill,TotalAmount,0);

        String sql = "INSERT INTO BillList (BillID,BillNo,STATUS,TotalItems," +
                "Date,TableNo,QueueNo,OnlineOrderBill,TotalAmount,DateTime) " +
                "VALUES ('" + DBFunc.PurifyString(blist.getBillID()) + "'," +
                "'" + DBFunc.PurifyString(String.valueOf(blist.getBillNo())) + "','" +
                //DBFunc.PurifyString(blist.getSTATUS()) + "','" +
                DBFunc.PurifyString(STATUS) + "','" +
                DBFunc.PurifyString(blist.getTotalItems()) + "','" +
                DBFunc.PurifyString(blist.getDate()) + "','" +
                DBFunc.PurifyString(blist.getTableNo()) + "','" +
                DBFunc.PurifyString(blist.getQueueNo()) + "','" +
                DBFunc.PurifyString(blist.getOnlineOrderBill()) + "','" +
                DBFunc.PurifyString(blist.getTotalAmount()) + "'," +
                "" + System.currentTimeMillis() + ")";

        DBFunc.ExecQuery(sql, false);


        //UpdateOnlineOrderBillInBillList();
    }

    public static String GetDateFormart55() {
        long yourmilliseconds = System.currentTimeMillis();
        DateFormat dateFormat55 = new SimpleDateFormat("dd MMM yyyy  hh:mm aa");
        Date resultdate = new Date(yourmilliseconds);
        String Date = dateFormat55.format(resultdate);
        return Date;
    }

    private static void UpdateOfflineBillNoInBillList() {

        Integer latestID = findLatestID("BillNo","Bill",false);

        String sql = "SELECT OfflineBillNo FROM Bill where OnlineOrderBill = 'OFF' " +
                " AND BillNo = "+latestID+" Order By BillNo DESC";

        Cursor c = DBFunc.Query(sql,false);
        if (c != null){
            if (c.moveToNext()){
                Integer offlineBillNo = c.getInt(0) + 1;

                sql = "UPDATE Bill SET " +
                        "OfflineBillNo = '"+offlineBillNo+"' " +
                        "WHERE BillNo = " + latestID + "";
                Log.i("Dfdfd___","onlsql_OfflineBillNo_"+sql);
                DBFunc.ExecQuery(sql, false);
            }
            c.close();
        }

//        Integer latestID = findLatestID("ID","BillList",false);
//
//        String sql = "SELECT OfflineBillNo FROM BillList where OnlineOrderBill = 'OFF' " +
//                " AND ID = "+latestID+" Order By BillNo DESC";
//
//        Cursor c = DBFunc.Query(sql,false);
//        if (c != null){
//            if (c.moveToNext()){
//                Integer offlineBillNo = c.getInt(0) + 1;
//
//                sql = "UPDATE BillList SET " +
//                        "OfflineBillNo = '"+offlineBillNo+"' " +
//                        "WHERE ID = " + latestID + "";
//                Log.i("Dfdfd___","onlsql_OfflineBillNo_"+sql);
//                DBFunc.ExecQuery(sql, false);
//            }
//            c.close();
//        }
    }

    public static void UpdateOnlineOrderBillInBillList() {
        String billNo = "";
        String strBill1 = "SELECT BillNo FROM BillList WHERE OnlineOrderBill IS NULL OR OnlineOrderBill = '0' ";
        Cursor c = DBFunc.Query(strBill1,false);
        if (c != null) {
            while (c.moveToNext()) {

                String strBill = "SELECT TransNo,OnlineOrderBill FROM Bill " +
                        "WHERE TransNo = '"+c.getString(0)+"'";

                Cursor c1 = DBFunc.Query(strBill,false);
                if (c1 != null){
                    while (c1.moveToNext()){

                        billNo = c1.getString(0);

                        String sql = "UPDATE BillList SET " +
                                "OnlineOrderBill = '"+c1.getString(1)+"' " +
                                "WHERE BillNo = '" + billNo + "'";
                        Log.i("Dfdfd___","onlsql_ddd__"+sql);
                        DBFunc.ExecQuery(sql, false);
                    }
                    c1.close();
                }
            }
            c.close();
        }
    }


    public static void UpdateBillList(BillListModel blmlist,String billNo){
        String sql = "UPDATE BillList SET " +
                "BillID = '"+blmlist.getBillID()+"', " +
                "BillNo = '"+blmlist.getBillNo()+"', " +
                "TotalItems = '"+blmlist.getTotalItems()+"', " +
                //"Date = '"+CheckOutActivity.dateFormat55.format(new Date()).toString()+"', " +
                "TableNo = '"+blmlist.getTableNo()+"', " +
                "QueueNo = '"+blmlist.getQueueNo()+"', " +
                "OnlineOrderBill = '"+blmlist.getOnlineOrderBill()+"', " +
                "TotalAmount = '"+blmlist.getTotalAmount()+"' " +
                //"DateTime = "+System.currentTimeMillis()+" " +
                "WHERE BillNo = '" + billNo + "'";
        Log.i("DF____","sql___"+sql);
        DBFunc.ExecQuery(sql, false);

        UpdateOnlineOrderBillInBillList();
    }

    public static List<BillListModel> getBillList(String offonStatus,String searchStatusQuery){
        String str = "";
        if (offonStatus.equals("ON")){
            str = "AND IsClosed IS NULL ";
        }
        List<BillListModel> blmlist = new ArrayList<BillListModel>();
        String sql_for_bill_list = "SELECT BillID,BillNo,STATUS,TotalItems,Date,TableNo,QueueNo,OnlineOrderBill,TotalAmount,DateTime " +
                "FROM BillList " +
                " WHERE OnlineOrderBill = '"+offonStatus+"' " + str +
                searchStatusQuery +
                "Order By BillNo DESC";

        Cursor Cursor_for_bill_list = DBFunc.Query(sql_for_bill_list,false);
        if (Cursor_for_bill_list != null) {
            while (Cursor_for_bill_list.moveToNext()) {
                String BillID = Cursor_for_bill_list.getString(0);
                String BillNo = Cursor_for_bill_list.getString(1);
                String STATUS = Cursor_for_bill_list.getString(2);
                String TotalItems = Cursor_for_bill_list.getString(3);
                String Date = Cursor_for_bill_list.getString(4);
                String TableNo = Cursor_for_bill_list.getString(5);
                String QueueNo = Cursor_for_bill_list.getString(6);
                String OnlineOrderBill = Cursor_for_bill_list.getString(7);
                String TotalAmount = Cursor_for_bill_list.getString(8);
                Integer DateTime = Cursor_for_bill_list.getInt(9);
//                String dt = CheckOutActivity.dateFormat55.format(DateTime);

//                long yourmilliseconds = DateTime;
//                //SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");
//                Date resultdate = new Date(yourmilliseconds);
//                String dt = CheckOutActivity.dateFormat55.format(resultdate);
//
//                Log.i("Dfdf____","dfdf_fddtt__"+dt);

                if (OnlineOrderBill.equals("ON")){
                    OnlineOrderBill = "HOLD";
                }
                blmlist.add(new BillListModel(BillID,BillNo,STATUS,TotalItems,Date,TableNo,QueueNo,OnlineOrderBill,TotalAmount,DateTime));
            }
            Cursor_for_bill_list.close();
        }
        return blmlist;
    }


    public static void SaveBill(String vchQueueNo,String intTableNo,String receiptOrderStatus,String transID,String transNo,String offonStatus) {
        String sql = "INSERT INTO Bill (OpenDateTime, Cashier, CashierID, BalNo, " +
                "VchQueueNo,IntTableNo,ReceiptOrderStatus,TransID, " +
                "OnlineOrderBill,TransNo) VALUES " +
                "(" + System.currentTimeMillis() + ",'" + Allocator.cashierName+ "', " +
                Allocator.cashierID + ", '0' , " +
                "'"+ vchQueueNo +"', '"+intTableNo+"','"+receiptOrderStatus+"','"+transID+"',"+
                "'"+offonStatus+"','" + transNo + "')";

        DBFunc.ExecQuery(sql, false);




        updateBillNo("OFF");


        //UpdateOfflineBillNoInBillList();

        //updateBillNo();

        SaveBillList(offonStatus);
    }

    public static void updateBillNo(String status) {
        String BillID = String.valueOf(Query.findLatestID("BillNo","Bill",false));

        String BillNo = "";
        if (status.equals("OFF")){

//            String sql = "SELECT OfflineBillNo FROM Bill where " +
//                    " BillNo !=  "+BillID+" Order By BillNo DESC";

//            String sql = "SELECT OfflineBillNo FROM Bill where " +
//                    " BillNo !=  "+BillID+" Order By BillNo DESC";

            String sql = "SELECT OfflineBillNo FROM Bill where OnlineOrderBill = '"+status+"' " +
                    " AND  BillNo !=  "+BillID+" Order By BillNo DESC";

            Log.i("Dfd___","___sql"+sql);
            Cursor c = DBFunc.Query(sql,false);
            Integer offlineBillNo = 1;
            if (c != null) {
                if (c.moveToNext()) {
                    offlineBillNo = c.getInt(0) + 1;
                }
                c.close();
            }

            BillNo = billNo(offlineBillNo);

            String updateSql = "UPDATE Bill SET TransNo = '"+BillNo+"' , OfflineBillNo = '"+offlineBillNo + "' WHERE BillNo = " + BillID + " AND OnlineOrderBill = '"+status+"' " ;

//           UPDATE Bill SET TransNo = '00000001' WHERE BillNo = 2 AND OnlineOrderBill = 'OFF' AND OfflineBillNo = '1'

            DBFunc.ExecQuery(updateSql, false);

        }else if (status.equals("ON")) {
//            BillNo = MainActivity.billNo(Integer.parseInt(BillID));
//            String updateSql = "UPDATE Bill SET TransNo = '"+BillNo+"' WHERE BillNo = " + BillID + " AND OnlineOrderBill = '"+status+"'";
//
//            Log.i("updateSql__","updateSql___"+updateSql);
//
//            DBFunc.ExecQuery(updateSql, false);
        }
    }

    private static Integer findLatestTransNo() {
        Integer TransNoIntVal = 0;
        String sql = "SELECT TransNo FROM Bill where OnlineOrderBill = 'OFF' Order By TransNo DESC";

        Cursor c = DBFunc.Query(sql,false);
        if (c != null){
            if (c.moveToNext()){
                TransNoIntVal = c.getInt(0);
            }
            c.close();
        }

        return TransNoIntVal;
    }

    public static void updateBillIDByBillNoIntoSales(String billNo) {
        String billID = findBillIDByBillNo(billNo);
        String sql = "UPDATE Sales SET BillID = '"+billID+"' WHERE BillNo = '" + billNo + "'";
        DBFunc.ExecQuery(sql, false);
    }

    public static String findBillIDByBillNo(String billNo) {
        String billID = "0";
        String sql_for_bill = "SELECT BillNo " +
                "FROM Bill " +
                " WHERE TransNo = '"+billNo+"' ";
        Cursor Cursor_for_bill = DBFunc.Query(sql_for_bill,false);
        if (Cursor_for_bill != null) {
            if (Cursor_for_bill.moveToNext()) {
                billID = Cursor_for_bill.getString(0);
            }
        }
        return billID;
    }
    public static String findBillNoByBillID(Integer billID) {
        String billNo = "0";
        String sql_for_bill = "SELECT TransNo " +
                "FROM Bill " +
                " WHERE BillNo = "+billID+" ";
        Cursor Cursor_for_bill = DBFunc.Query(sql_for_bill,false);
        if (Cursor_for_bill != null) {
            if (Cursor_for_bill.moveToNext()) {
                billNo = Cursor_for_bill.getString(0);
            }
        }
        return billNo;
    }

    public static void UpdateStatusBillList(String billNo, String status, String dateString,long dtl) {
        String sql = "UPDATE BillList SET " +
                "STATUS = '"+status+"', " +
                "Date = '"+dateString+"', " +
                "Datetime = "+dtl+" " +
                "WHERE BillNo = '" + billNo + "'";
        DBFunc.ExecQuery(sql, false);
    }


    public static void UpdateIsClosedBillList(String billNo) {
        String sql = "UPDATE BillList SET " +
                 "IsClosed  = 'Closed' " +
                "WHERE BillNo = '" + billNo + "'";
        DBFunc.ExecQuery(sql, false);
    }

    public static Boolean CheckBillListStatus(boolean bno,String BNo) {
        Boolean checkBillPending = true; // Can Z Close
        String checkSQLPendingBill = "SELECT BillNo FROM BillList " +
                "WHERE STATUS = 'PENDING' " +
                "AND IsClosed IS NULL " +
                "AND IsZ IS NULL";
        Cursor CursorPendingBill = DBFunc.Query(checkSQLPendingBill, false);
        if (CursorPendingBill != null) {
            arrStillpendingbillno.clear();
            arrStillpendingbilllist.clear();
            while (CursorPendingBill.moveToNext()) {
                String pendingBillNo = CursorPendingBill.getString(0);
                String checkBillNo = getBillNofromDetailsBillProduct(pendingBillNo);
                if (checkBillNo.length() > 0) {
                    arrStillpendingbillno.add(pendingBillNo);
                }
                arrStillpendingbilllist.add(pendingBillNo);
            }
            CursorPendingBill.close();
        }

//        for (int i = 0; i < arrStillpendingbillno.size(); i++) {
//            String stillpendingbillno1 = arrStillpendingbillno.get(i);
//            if (i == arrStillpendingbillno.size() - 2) {
//                stillpendingbillno += stillpendingbillno1 + ",";
//            }
//        }
        Log.i("DFFS___","DFD___"+arrStillpendingbillno.size()+"___"+
                arrStillpendingbilllist.size());
        if (arrStillpendingbillno.size() > 0) {
            checkBillPending = false;
            stillpendingbillno = arrStillpendingbillno.toString();
        }else if (arrStillpendingbilllist.size() > 1) {
            checkBillPending = false;
            for (int i = 0; i < arrStillpendingbilllist.size(); i++) {
                arrStillpendingbilllist.remove(arrStillpendingbilllist.size() - 1);
            }
            stillpendingbillno = arrStillpendingbilllist.toString();
        }
        Log.i("__checkBillPending__","checkBillPending_"+checkBillPending+"_"+arrStillpendingbillno);
        return checkBillPending;
    }


////        Boolean checkBillPending = true; // Can Z Close
////        String query = "";
////        //String pending_status_check_bill_no = "";
////        Log.i("bno___","bno1____"+bno);
////        if (bno == true) { // if last bill is mainactivity's bill
////            String checkBillNo = getBillNofromDetailsBillProduct(BNo);//check last bill have data or not
////
////            Log.i("checkBillNo___","checkBillNo1____"+checkBillNo);
////            if (checkBillNo.length() > 0){  // if have , can't z close , so return false
////                checkBillPending = false;
////
////                stillpendingbillno = BNo;
////            }else {   // if not (not have data, so continue check pending status in billlist)
////                String status = getStatusfromBillList(BNo);
////                Log.i("status___","status1____"+status);
////                if (status.toUpperCase().equals("PENDING")) { // if pending status, can ignore (so return true)
////                    //pending_status_check_bill_no = " AND BillNo != '" + BNo + "' "; // Pending last billno
////                    checkBillPending = true;  // Can Z Close
////
////                    query = "AND BillNo != '"+BNo+"' ";
////
////                }else {
////                    checkBillPending = false; //if not pending , return false (can't Z close)
////
////                    stillpendingbillno = BNo;
////                }
////            }
////        }else { // if bill no is not last bill
////            String status = getStatusfromBillList(BNo);
////            Log.i("status___","status2____"+status);
////            if (status.toUpperCase().equals("PENDING")) { // if pending status, can't Zclose
////                checkBillPending = false;  // can't Zclose
////                stillpendingbillno = BNo;
////            }else {
//////                String salesql = "SELECT ReferenceBillNo " +
//////                        "FROM Sales " +
//////                        "where BillNo = '"+BNo+"' " ;
//////                Log.i("__sql", salesql);
//////
//////                Cursor salec = DBFunc.Query(salesql,false);
//////                if (salec != null) {
//////                    if (salec.getCount() == 0) {
//////                        checkBillPending = true; //if not pending , return false (can't Z close)
//////                    }else {
//////                        checkBillPending = false;  // can Zclose
//////                    }
//////                    salec.close();
//////                }else {
////                    checkBillPending = true; //if not pending , return false (can't Z close)
//////                }
////            }
//
//            Log.i("checkBillPending___","checkBillPending2____"+checkBillPending);
//        }
//    public static Boolean CheckBillListStatus(String  query) {
//        Boolean canZCloseflag = true;
//        Log.i("checkBillPending___","checkBillPending3____"+checkBillPending);
//        if (checkBillPending == true) { // Fist Stage Can Z close
//
//            //String sql = "SELECT STATUS FROM BillList WHERE IsClosed IS NULL AND IsZ IS NULL AND TotalItems != '0'";
//            String sql = "SELECT STATUS,BillNo FROM BillList WHERE IsClosed IS NULL AND IsZ IS NULL " + query;
//            Log.i("sql555___","sql4444____"+sql);
//            Cursor c = DBFunc.Query(sql, false);
//            if (c != null) {
//                while (c.moveToNext()) {
//                    //if (c.getString(0).toUpperCase().equals("PENDING") && b == false) {
////                if (c.getString(0).toUpperCase().equals("PENDING") && b == false) {
//                    Log.i("ghghtyt___","454545____"+c.getString(0).toUpperCase());
//                    if (c.getString(0).toUpperCase().equals("PENDING")) {
//                        String salesql = "SELECT ReferenceBillNo " +
//                                "FROM Sales " +
//                                "where BillNo = '"+c.getString(1)+"' " ;
//                        Log.i("__sql", salesql);
//
//                        Cursor salec = DBFunc.Query(salesql,false);
//                        if (salec != null) {
//                            if (salec.getCount() == 0) {
//                                stillpendingbillno = c.getString(1);
//                                canZCloseflag = false;
//                                return canZCloseflag;
//                            }
//                            salec.close();
//                        }
//                    }
//                    Log.i("canZCloseflag___","canZCloseflag1____"+canZCloseflag);
//                }
//                c.close();
//            }
//        }else { // First Stage can't allow Z Close
//            canZCloseflag = false;
//        }
//        Log.i("canZCloseflag___","canZCloseflag2____"+canZCloseflag);
//        return canZCloseflag;
//    }

    public static Integer CheckLength(String str) {
        Integer i = 0;
        if (str.length() == 0){
            i = 0;
        }else {
            i = str.length();
        }
        return i;
    }

    public static void ResponseCodeSuccessFun(final Context context, final String billNo,
                                              String paymentID, String paymentName,
                                              String card_label) {
        //InsertPaymentIntoDB(_paymentID, _payname, _val_amount);
        //CashLayoutActivity.SaveBillPayment(paymentID,paymentName,String.valueOf(change),String.valueOf(paymentAmount),CashLayoutActivity.context);
        Double str_sub_total = 0.0;
        Double str_amount = 0.00;
        Integer str_totalQty = 0;
        Double str_ItemDiscountAmount = 0.00;

        String sql = " SELECT BillNo,(ProductQty),(ProductPrice),ProductName," +
                "(ItemDiscountAmount),BillDateTime,ID FROM DetailsBillProduct " +
                " Where BillNo = '"+ billNo +"' "+
                "   group by ProductID,OpenPriceStatus";
//
//        String sql = " SELECT BillNo,SUM(ProductQty),SUM(ProductPrice),ProductName,SUM(ItemDiscountAmount),BillDateTime,ID FROM DetailsBillProduct " +
//                " Where BillNo = '"+ billNo +"' "+
//                "   group by ProductID,OpenPriceStatus";

        Cursor c = DBFunc.Query(sql, false);
        if (c != null) {
            str_sub_total = 0.0;
            str_amount = 0.00;
            str_totalQty = 0;
            str_ItemDiscountAmount = 0.00;
            while (c.moveToNext()) {
                if (!c.isNull(0)) {
//                    str_sub_total += c.getDouble(2) - c.getDouble(4);
//                    str_amount += c.getDouble(2);
//                    str_totalQty += c.getInt(1);
//                    str_ItemDiscountAmount += c.getDouble(4);
                    //Double totalamt = c.getInt(1) * (c.getDouble(2) - c.getDouble(4));
                    Double totalamt =  c.getDouble(2) - (c.getInt(1) * c.getDouble(4));
                    str_sub_total += totalamt;
                    str_amount += totalamt;
                    str_totalQty += c.getInt(1);
                    str_ItemDiscountAmount += c.getInt(1) * c.getDouble(4);
                }
            }
            c.close();
        }

        Log.i("dsfdsf__totalamt","totalastr_amount___"+str_amount);
        CashLayoutActivity.SaveBillPayment(billNo,str_sub_total,str_totalQty,
                str_amount,str_ItemDiscountAmount,paymentID,paymentName,"0.00",
                String.valueOf(str_amount),
                context,"card",card_label);


        String chk_print_receipt_paper = "0";
        try {
            chk_print_receipt_paper = Query.GetOptions(23);
        }catch (StringIndexOutOfBoundsException e){
            chk_print_receipt_paper = "0";
        }finally {
            chk_print_receipt_paper = chk_print_receipt_paper;
        }
        final Integer sale_id = Query.findLatestID("ID","Sales",false);
        Log.i("DSFchk_receipt_print_","chk_receipt_print___"+CheckOutActivity.BillNo+"__"+sale_id+"__"+chk_print_receipt_paper);
        if (chk_print_receipt_paper.equals("1")) {


            new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText(Constraints.Print)
                    .setConfirmText(Constraints.YES)
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismissWithAnimation();

                            if (sale_id > 0) {

                                String terminaltype_check = Query.GetDeviceData(Constraints.TERMINAL_TYPE);
                                Integer receiptCount = 1;

                                if (terminaltype_check.toUpperCase().equals(Constraints.IMIN.toUpperCase())) {
                                    receiptCount = MainActivity.receiptCount;
                                }

                                for (int printcount = 0; printcount < receiptCount; printcount++) {
                                    CashLayoutActivity.PrintFormatFun(context,sale_id,billNo, Constraints.SALES,null,null);
                                }
//                                CashLayoutActivity.PrintFormatFun(context,sale_id,billNo, Constraints.SALES,null,null);

                            }
                            Toast.makeText(context, Constraints.SALES + " Successfully.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, PaymentCashSuccesActivity.class);
                            context.startActivity(intent);

                        }
                    })
                    .setCancelButton(Constraints.No, new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismissWithAnimation();

                            Toast.makeText(context, Constraints.SALES + " Successfully.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, PaymentCashSuccesActivity.class);
                            context.startActivity(intent);
                        }
                    })
                    .show();

        }else {
            Toast.makeText(context, Constraints.SALES + " Successfully.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, PaymentCashSuccesActivity.class);
            context.startActivity(intent);
        }


        CashLayoutActivity.UpdateFunationfun(CheckOutActivity.BillNo);

        //final Integer sale_id = Query.findLatestID("ID","Sales",false);
//        CashLayoutActivity.PrintFormatFun(sale_id,billNo,ENUM.SALES,null,null);

//        Intent i = new Intent(context, PaymentCashSuccesActivity.class);
//        context.startActivity(i);
//        ((Activity)context).finish();

    }

    public static String GetBankNameFun() {
        String bankName = "";
        Cursor Cursor_BankName = DBFunc.Query("SELECT * FROM "+ Constraints.ConfigPaymentHost +" Where activate = 1 ", true);
        if (Cursor_BankName != null) {
            if (Cursor_BankName.moveToNext()) {
                bankName = Cursor_BankName.getString(3);
            }
            Cursor_BankName.close();
        }
        return bankName;

    }

    public static void SweetAlertWarning(Context context,String str,String yheader,String ydescription,String nheader,String ndescription){
            if (str.equals("Y")){
                SweetAlertWarningYesOnly(context,yheader,ydescription);
            }else if (str.equals("N")){
                SweetAlertWarningNoOnly(context,nheader,ndescription);
            }else {
                SweetAlertWarningAll(context,yheader,ydescription,nheader,ndescription);
            }

    }

    public static void SweetAlertWarningYesOnly(Context context, String header, String description) {
        new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                //.setTitleText("Cancelled Bill")
                .setContentText(header)
                .setConfirmText(description)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                    }
                })
                .show();
    }

    public static void SweetAlertWarningNoOnly(Context context, String header, String description) {
        new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                //.setTitleText("Cancelled Bill")
                .setContentText(header)
                .setCancelButton(description, new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                    }
                })
                .show();
    }


    public static void SweetAlertWarningAll(Context context, String yheader, String ydescription, String nheader, String ndescription) {

        final SweetAlertDialog syncDialog =  new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                //.setTitleText("Cancelled Bill")
                .setContentText(yheader)
                .setConfirmText(ydescription)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                    }
                })
                .setCancelButton(nheader, new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                    }
                });
        syncDialog.show();
        syncDialog.setCancelable(false);
    }

//    public static void RunFrist() {
//        Integer TotalQty = 0;
//        Integer ID = 0;
//        Integer PaymentTypeID = 0;
//        String BillNo = "";
//        String CashierName = "";
//        String SalesDateTime = "";
//        String BillHour = "";
//        String STATUS = "";
//        String DiscountName = "";
//        String DiscountType = "";
//        String DiscountTypeName = "";
//        String PaymentTypeName = "";
//        long dt = 0;
//        Double SubTotal = 0.0;
//        Double Totalamount = 0.0;
//        Double Changeamount = 0.0;
//        Double PaymentAmount = 0.0;
//        Double GrossSales = 0.0;
//        Double TotalItemDisount = 0.0;
//        Double TotalBillDisount = 0.0;
//        Double GrossTotal = 0.0;
//        Double ServiceCharges = 0.0;
//        Double TotalNettSales = 0.0;
//        Double TotalTaxes = 0.0;
//        Double RoundAdj = 0.0;
//        Double DiscountID = 0.0;
//        Double DiscountValue = 0.0;
//
//        String sql = "SELECT ID,BillNo,TotalQty,SubTotal,Totalamount,Changeamount,PaymentTypeID,PaymentTypeName,PaymentAmount, " +
//                "GrossSales,TotalItemDisount,TotalBillDisount,GrossTotal,ServiceCharges,TotalNettSales,TotalTaxes, " +
//                "CashierName,SalesDateTime,BillHour,STATUS, " +
//                "RoundAdj,DiscountID,DiscountName,DiscountType,DiscountTypeName,DiscountValue,BillID,DateTime " +
//                "FROM Sales Where BillNo IN ('00000042','00000046'," +
//                "'00000047','00000048','00000050','00000153','00000168'," +
//                "'00000169','00000181','00000201')";
//        Log.i("sql___","sql__"+sql);
//        Cursor c = DBFunc.Query(sql,false);
//        if (c != null) {
//            while (c.moveToNext()) {
//
//                        ID = c.getInt(0);
//                        BillNo =  c.getString(1);
////                        TotalQty = (-1) * c.getInt(2);
////                        SubTotal = (-1) * c.getDouble(3);
////                        Totalamount = (-1) * c.getDouble(4);
////                        Changeamount = (-1) * c.getDouble(5);
////                        PaymentTypeID= c.getInt(6);
////                        PaymentTypeName =  c.getString(7);
////                        PaymentAmount = (-1) * c.getDouble(8);
////                        GrossSales = (-1) * c.getDouble(9);
////                        TotalItemDisount = (-1) * c.getDouble(10);
////                        TotalBillDisount = (-1) * c.getDouble(11);
////                        GrossTotal = (-1) * c.getDouble(12);
////                        ServiceCharges = (-1) * c.getDouble(13);
////                        TotalNettSales = (-1) * c.getDouble(14);
////                        TotalTaxes = (-1) * c.getDouble(15);
////                        CashierName =  c.getString(16);
//                        SalesDateTime =  c.getString(17);
//                        BillHour =  c.getString(18);
////        //                        STATUS =  c.getString(19);
////                        RoundAdj = (-1) * c.getDouble(20);
////                        DiscountID = (-1) * c.getDouble(21);
////                        DiscountName =  c.getString(22);
////                        DiscountType =  c.getString(23);
////                        DiscountTypeName =  c.getString(24);
////                        DiscountValue = (-1) * c.getDouble(25);
////                        BillID = c.getDouble(26);
//                        dt = c.getLong(27);
//
//                        String old_bId = findBillIDByBillNo(BillNo);
////
//
//                        //REFUND HardCode
//                        RefundSaveBill(old_bId);
////
//                        Integer lstbillID = Query.findLatestID("BillNo","Bill",false);
//                        String refubdBillNo = billNo(lstbillID);
////
//                        String bId = findBillIDByBillNo(refubdBillNo);
////
//                        RefundBillList(BillNo,refubdBillNo,dt);
//
//                        RefundDetailsBillProduct(BillNo,refubdBillNo,SalesDateTime,bId,dt);
//
//                        RefundBillPayment(BillNo,refubdBillNo);
//
//                        Integer sales_id = ID;
//                        RefundSalesAndItems(BillNo,refubdBillNo,sales_id,SalesDateTime,BillHour,bId,dt,"Amend");
//
//            }
//            c.close();
//        }
//
//
//
////        String sql = "SELECT BillNo,TransNo FROM Bill";
////        Cursor c = DBFunc.Query(sql,false);
////        Integer offlineBillNo = 0;
////        if (c != null){
////            while (c.moveToNext()){
////
////
////                Integer billID = c.getInt(0);
////                String transNo = billNo(billID);
////                offlineBillNo ++;
////
////
//////                String sqlb = "SELECT TransNo FROM Bill WHERE BillNo = "+billID+"";
//////                Cursor csqlb= DBFunc.Query(sqlb,false);
//////                if (csqlb != null) {
//////                    if (csqlb.getCount() == 0){
////
////                        String updateSql = "UPDATE Bill SET TransNo = '"+transNo+"' , OfflineBillNo = '"+offlineBillNo + "' WHERE BillNo = " + billID + " AND OnlineOrderBill = 'OFF' " ;
////                        DBFunc.ExecQuery(updateSql,false);
////
//////                    }
//////                    csqlb.close();
//////                }
////
////            }
////            c.close();
////        }
//    }

    public static void RunThird () {
        String salesql = "SELECT ID,BillNo " +
                "FROM Sales " +
                "where UUID IS NULL " ;
        Log.i("__sql", salesql);
        long sitmdt =  0;
        Cursor salec = DBFunc.Query(salesql,false);
        if (salec != null) {
            while (salec.moveToNext()) {
                String uniqueId  = UUID.randomUUID().toString();
                String query = "UPDATE Sales SET ";
                        query += "UUID = '"+uniqueId+"' ";
                        query += "WHERE ID = "+salec.getInt(0)+" ";

                        Log.i("DFdfd___sql","query___"+query);
                        DBFunc.ExecQuery(query, false);
            }
            salec.close();
        }

        String siql = "SELECT STATUS,BillNo,SalesID FROM SalesItem WHERE STATUS IS NULL";
        Log.i("__sql", siql);
        Cursor siqlc = DBFunc.Query(siql,false);
        if (siqlc != null) {
            while (siqlc.moveToNext()) {
                String sql = "SELECT STATUS,ReferenceBillNo,BillNo FROM SALES WHERE ID = '"+siqlc.getInt(2)+"'";
                Log.i("__sql", sql);
                Cursor sqlc = DBFunc.Query(sql,false);
                if (sqlc != null) {
                    while (sqlc.moveToNext()) {

                        String query = "UPDATE SalesItem SET ";
                        query += "STATUS = '"+sqlc.getString(0)+"', ";
                        query += "ReferenceBillNo = '"+sqlc.getString(1)+"',  ";
                        query += "BillNo = '"+sqlc.getString(2)+"'  ";
                        query += "WHERE SalesID = '"+siqlc.getInt(2)+"'";

                        Log.i("DFdfd___sql","query___"+query);
                        DBFunc.ExecQuery(query, false);
                    }
                    sqlc.close();
                }
            }
            siqlc.close();
        }



////        String sql = "SELECT ID  FROM PLU";
////        Cursor c = DBFunc.Query(sql,true);
////        Integer offlineBillNo = 0;
////        if (c != null) {
////            while (c.moveToNext()) {
////
////                String pluid = c.getString(0);
////
////
////                //String stockAgentDateTime = CheckOutActivity.dateFormat55.format(new Date());
////                String stockAgentDateTime = GetDateFormart55();
////
////                StockAgent stockAgent = new StockAgent(0,0,0,
////                        0,0,Integer.parseInt(pluid),stockAgentDateTime);
////                Query.SaveStockAgent(stockAgent);
////            }
////            c.close();
////        }
//        String sql = "SELECT BillDateTime,BillNo FROM Details_BillProduct";
//        Cursor c = DBFunc.Query(sql,false);
//        Integer offlineBillNo = 0;
//        if (c != null) {
//            while (c.moveToNext()) {
//                DBFunc.ExecQuery("UPDATE BillList SET Date = '"+c.getString(0)+"' WHERE BillNo = '"+c.getString(1)+"'", false);
//            }
//            c.close();
//        }
//
////        String sql = "SELECT BillNo FROM Details_BillProduct ";
////        Cursor c = DBFunc.Query(sql,false);
////        Integer offlineBillNo = 0;
////        if (c != null) {
////            while (c.moveToNext()) {
////
////                //String billID = c.getString(0);
////                String billNo = c.getString(0);
////
////                String bid = findBillIDByBillNo(billNo);
////                String bno = billNo(Integer.parseInt(bid));
////                //if (Integer.parseInt(billID) > 99) {
////
////                DBFunc.ExecQuery("UPDATE Details_BillProduct SET  BillNo = '"+bno+"'", false);
////               // }
////            }
////        }

    }

    public static void RunFourth() {

        String bidsql = "SELECT BillNo,ReferenceBillNo,IsZ,ID,DateTime " +
                "FROM Sales WHERE STATUS = 'REFUND'";
        Log.i("__sql", bidsql);
        Cursor cdd = DBFunc.Query(bidsql, false);
        if (cdd != null) {
            while (cdd.moveToNext()) {

                RefundBillPayment(cdd.getString(1), cdd.getString(0));

                String updatesql = "UPDATE BillPayment SET isZ = '"+ cdd.getString(2)+"' WHERE BillNo = '"+cdd.getString(0)+"' ";

                DBFunc.ExecQuery(updatesql,false);



                Integer sale_id = cdd.getInt(3);
                String sql = "SELECT PluId,PluName,CategoryId,CategoryName,Qty,Price " +
                        "FROM SalesItem " +
                        "where BillNo = '" + cdd.getString(1) + "'";
                Log.i("__sql", sql);
                Cursor c = DBFunc.Query(sql, false);
                if (c != null) {
                    while (c.moveToNext()) {
                        //String sql_chk_plu_existing = "Select PluId from SalesItem WHERE BillNo = '" + BillNo +
                        String sql_chk_plu_existing = "Select PluId from SalesItem WHERE BillNo = '" + cdd.getString(0) +
                                "' AND PluId = " + c.getInt(0);
                        Log.i("Df__","dfdf__"+sql_chk_plu_existing);
                        Cursor curpluexisting = DBFunc.Query(sql_chk_plu_existing, false);
                        if (curpluexisting != null) {
                            if (curpluexisting.getCount() == 0) {

                                sql = "INSERT INTO SalesItem (SalesID,BillNo,PluId,PluName,CategoryId,CategoryName,Qty,Price,DateTime) VALUES (";
                                sql += sale_id + ", ";
                                sql += "'" + DBFunc.PurifyString(cdd.getString(0)) + "', ";
                                sql += "'" + DBFunc.PurifyString(String.valueOf(c.getString(0))) + "', ";
                                sql += "'" + DBFunc.PurifyString(String.valueOf(c.getString(1))) + "', ";
                                sql += "'" + DBFunc.PurifyString(String.valueOf(c.getString(2))) + "', ";
                                sql += "'" + DBFunc.PurifyString(String.valueOf(c.getString(3))) + "', ";
                                sql += "'" + DBFunc.PurifyString(String.valueOf((-1) * c.getInt(4))) + "', ";
                                sql += "'" + DBFunc.PurifyString(String.valueOf(((-1) * c.getDouble(5)))) + "', ";
                                sql += cdd.getLong(4) + ")";

                                DBFunc.ExecQuery(sql, false);
                            }
                            curpluexisting.close();
                        }

                    }
                    c.close();
                }

            }
            cdd.close();
        }

//        String billPaymentsql = "SELECT STATUS,Amount,idx,BillNo " +
//                "FROM BillPayment ";
//        Log.i("__sql", billPaymentsql);
//        Cursor c = DBFunc.Query(billPaymentsql, false);
//        if (c != null) {
//            while (c.moveToNext()) {
//
//                if (c.getString(0).toUpperCase().equals("CANCEL")  ){
//
//                    String bidsql = "SELECT STATUS " +
//                            "FROM Sales WHERE BillNo = '"+c.getString(3)+"'";
//                    Log.i("__sql", bidsql);
//                    Cursor cdd = DBFunc.Query(bidsql, false);
//                    if (cdd != null) {
//                        if (cdd.moveToNext()) {
//
//                            if (c.getDouble(1) > 0.0) {
//                                Double v = (-1) * c.getDouble(1);
//
//                                if (cdd.getString(0).toUpperCase().equals("REFUND")  ){
//
//                                    String query = "UPDATE BillPayment SET ";
//                                    query += " Amount = '" + v + "', ";
//                                    query += " STATUS = '" + cdd.getString(0) + "' ";
//                                    query += " WHERE idx = " + c.getInt(2) + " ";
//
//                                    Log.i("DFdfd___sql", "query___" + query);
//                                    DBFunc.ExecQuery(query, false);
//                                }
//                            }
//                        }
//                        cdd.close();
//                    }
//                }
//            }
//            c.close();
//        }

    }
//    public static void RunSecond() {
//        Integer TotalQty = 0;
//        Integer ID = 0;
//        Integer PaymentTypeID = 0;
//        String BillNo = "";
//        String CashierName = "";
//        String SalesDateTime = "";
//        String BillHour = "";
//        String STATUS = "";
//        String DiscountName = "";
//        String DiscountType = "";
//        String DiscountTypeName = "";
//        String PaymentTypeName = "";
//        long dt = 0;
//        Double SubTotal = 0.0;
//        Double Totalamount = 0.0;
//        Double Changeamount = 0.0;
//        Double PaymentAmount = 0.0;
//        Double GrossSales = 0.0;
//        Double TotalItemDisount = 0.0;
//        Double TotalBillDisount = 0.0;
//        Double GrossTotal = 0.0;
//        Double ServiceCharges = 0.0;
//        Double TotalNettSales = 0.0;
//        Double TotalTaxes = 0.0;
//        Double RoundAdj = 0.0;
//        Integer DiscountID = 0;
//        Double DiscountValue = 0.0;
//
//        String sql = "SELECT ID,BillNo,TotalQty,SubTotal,Totalamount,Changeamount,PaymentTypeID,PaymentTypeName,PaymentAmount, " +
//                "GrossSales,TotalItemDisount,TotalBillDisount,GrossTotal,ServiceCharges,TotalNettSales,TotalTaxes, " +
//                "CashierName,SalesDateTime,BillHour,STATUS, " +
//                "RoundAdj,DiscountID,DiscountName,DiscountType,DiscountTypeName,DiscountValue,BillID,DateTime " +
//                "FROM Sales Where BillNo IN ('00000042','00000046'," +
//                "'00000047','00000048','00000050','00000153','00000168'," +
//                "'00000169','00000181','00000201')";
//        Log.i("sql___","sql__"+sql);
//        Cursor c = DBFunc.Query(sql,false);
//        if (c != null){
//            while (c.moveToNext()){
//                Log.i("Billno__","billno___"+c.getString(1));
//                ID = c.getInt(0);
//                BillNo = c.getString(1);
////
//                if (c.getInt(2) > 0.0){ // +3 > 0.0
//                    TotalQty = c.getInt(2); // 3
//                }else {
//                    TotalQty = (-1) * c.getInt(2); // (-1) * -3 = +3
//                }
//                SubTotal = CheckMinusPlusValue(c.getDouble(3));
//                Totalamount = CheckMinusPlusValue(c.getDouble(4));
//                Changeamount = CheckMinusPlusValue(c.getDouble(5));
//                PaymentTypeID = c.getInt(6);
//                PaymentTypeName = c.getString(7);
//                PaymentAmount = CheckMinusPlusValue(c.getDouble(8));
//                GrossSales = CheckMinusPlusValue(c.getDouble(9));
//                TotalItemDisount = CheckMinusPlusValue(c.getDouble(10));
//                TotalBillDisount = CheckMinusPlusValue(c.getDouble(11));
//                GrossTotal = CheckMinusPlusValue(c.getDouble(12));
//                ServiceCharges = CheckMinusPlusValue(c.getDouble(13));
//                TotalNettSales = CheckMinusPlusValue(c.getDouble(14));
//                TotalTaxes = CheckMinusPlusValue(c.getDouble(15));
//                CashierName = c.getString(16);
////                        SalesDateTime =  c.getString(17);
////                        BillHour =  c.getString(18);
////                        STATUS =  c.getString(19);
//                RoundAdj = CheckMinusPlusValue(c.getDouble(20));
//                DiscountID = c.getInt(21);
//                DiscountName = c.getString(22);
//                DiscountType = c.getString(23);
//                DiscountTypeName = c.getString(24);
//                DiscountValue = CheckMinusPlusValue(c.getDouble(25));;
////
////
//                String query = "UPDATE Sales SET ";
//                query += "TotalQty = '"+ String.valueOf(TotalQty)+"', ";
//                query += "SubTotal = '"+ String.valueOf(SubTotal)+"', ";
//                query += "Totalamount = '"+ String.valueOf(Totalamount)+"', ";
//                query += "Changeamount = '"+ String.valueOf(Changeamount)+"', ";
//                query += "PaymentTypeID = '"+ String.valueOf(PaymentTypeID)+"', ";
//                query += "PaymentTypeName = '"+ String.valueOf(PaymentTypeName)+"', ";
//                query += "PaymentAmount = '"+ String.valueOf(PaymentAmount)+"', ";
//                query += "GrossSales = '"+ String.valueOf(GrossSales)+"', ";
//                query += "TotalItemDisount = '"+ String.valueOf(TotalItemDisount)+"', ";
//                query += "TotalBillDisount = '"+ String.valueOf(TotalBillDisount)+"', ";
//                query += "GrossTotal = '"+ String.valueOf(GrossTotal)+"', ";
//                query += "ServiceCharges = '"+ String.valueOf(ServiceCharges)+"', ";
//                query += "TotalNettSales = '"+ String.valueOf(TotalNettSales)+"', ";
//                query += "TotalTaxes = '"+ String.valueOf(TotalTaxes)+"', ";
//                query += "CashierName = '"+ String.valueOf(CashierName)+"', ";
//
////                String saleitmsql = "SELECT DateTime " +
////                        "FROM SalesItem " +
////                        "where ID = " + ID;
//                String saleitmsql = "SELECT strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch'),DateTime " +
//                        "FROM SalesItem " +
//                        "where BillNo = '"+BillNo+"'" ;
//                Log.i("__sql", saleitmsql);
//                long sitmdt =  0;
//                Cursor saleitmc = DBFunc.Query(saleitmsql,false);
//                if (saleitmc != null) {
//                    if (saleitmc.moveToNext()) {
//                        sitmdt = saleitmc.getLong(1);
//                        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd",Locale.US);
//                        try {
//                            Date date = (Date)formatter.parse(saleitmc.getString(0));
//
//                            String dateFormat3 = CheckOutActivity.dateFormat55.format(date).toString();
//                            String dateFormat4 = CheckOutActivity.dateFormat555.format(date).toString();
//
//                            Log.i("Df____dateFormat3",BillNo+"__"+"dateFormat3__"+dateFormat3+"dateFormat4__"+dateFormat4);
////
//                            query += "SalesDateTime = '"+ dateFormat3 +"', ";
////
//                            query += "BillHour = '" + dateFormat4 + "', ";
//
//                            query += "DateTime = " + sitmdt +",";
//                        } catch (ParseException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//
////
//                query += "STATUS = '"+ ENUM.SALES +"', ";
//
//
//                query += "RoundAdj = '"+ RoundAdj +"', ";
//
//                query += "DiscountID = '"+ DiscountID +"', ";
//                query += "DiscountName = '"+ DiscountName +"', ";
//                query += "DiscountType = '"+ DiscountType +"', ";
//                query += "DiscountTypeName = '"+ DiscountTypeName +"', ";
//                query += "DiscountValue = '"+ DiscountValue +"' ";
//                query += " WHERE ID = "+ID+"";
//
//
//                DBFunc.ExecQuery(query, false);
////
////
////                Query.UpdateBillPayment();
//                String updatebpsql = "SELECT BillNo FROM BillPayment WHERE BillNo = '"+BillNo+"'";
//
//                Cursor updatebpc = DBFunc.Query(updatebpsql,false);
//                if ( updatebpc != null) {
//                    while (updatebpc.moveToNext()) {
//                        String billPaymentbillNo = updatebpc.getString(0);
//
//                        String sql_sales = "SELECT STATUS,isZ FROM Sales WHERE BillNo = '"+ billPaymentbillNo +"' ";
//                        Cursor Cursor = DBFunc.Query(sql_sales,false);
//                        if (Cursor != null){
//                            while (Cursor.moveToNext()){
//                                String sstatus = Cursor.getString(0);
//                                String sisZZ = Cursor.getString(1);
////                        if (sisZZ.equals("null")){
////                            sisZZ = "";
////                        }
//                                String updatesql = "UPDATE BillPayment SET STATUS = '"+sstatus+"' , isZ = '"+sisZZ+"' WHERE BillNo = '"+billPaymentbillNo+"' ";
//
//                                DBFunc.ExecQuery(updatesql,false);
//                            }
//                            Cursor.close();
//                        }
//                    }
//                    updatebpc.close();
//                }
//
//
//
//                Log.i("Df____query","query__"+query);
////
//                Query.UpdateStatusBillList(BillNo, ENUM.SALES);
//
//            }
//            c.close();
//        }
//
////        String sql = "SELECT BillNo,TransNo FROM Bill";
////        Cursor c = DBFunc.Query(sql,false);
////        Integer offlineBillNo = 0;
////        if (c != null){
////            while (c.moveToNext()){
////
////                String billID = c.getString(0);
////                String billNo = c.getString(1);
////
////               // if (Integer.parseInt(billID) > 99) {
////
////                    Log.i("Dfd__billNo","billNo__"+billNo);
////
////                    String STATUS = "";
////                    String totalitm = "";
////                    //String Date = GetDateFormart55();
////                    //dt
////
////
////                    String tblNo = "";
////                    String queueNo = "";
////                    String totalAmt = "0";
////
////                    String sqlsales = "SELECT STATUS,TotalQty,TotalNettSales FROM Sales WHERE BillNo = '"+billNo+"'";
////                    Cursor csales = DBFunc.Query(sqlsales,false);
////                    if (csales != null) {
////                        if (csales.moveToNext()){
////                            STATUS = csales.getString(0);
////                            totalitm = csales.getString(1);
////                            totalAmt = csales.getString(2);
////                        }
////                        csales.close();
////                    }
////                    Integer dt = 0;
////                    if (STATUS.length() == 0) {
////                        String sqldetailbillproduct = " SELECT CANCEL,count(ProductQty),SUM(ProductPrice),ProductQty,SUM(ItemDiscountAmount),DateTime " +
////                                "FROM Details_BillProduct Where ProductQty != -1 AND BillNo = '" + billNo + "' "+
////                                "group by BillNo ";
////                        Cursor cdetailbillproduct = DBFunc.Query(sqldetailbillproduct,false);
////                        if (cdetailbillproduct != null) {
////                            if (cdetailbillproduct.moveToNext()){
////                                STATUS = cdetailbillproduct.getString(0);
////                                totalitm = cdetailbillproduct.getString(1);
////                                Double realamt = cdetailbillproduct.getDouble(2) - cdetailbillproduct.getDouble(4);
////                                totalAmt = String.valueOf(realamt);
////                                dt = cdetailbillproduct.getInt(5);
////                            }
////                            cdetailbillproduct.close();
////                        }
////                    }
////
////                    long yourmilliseconds = dt;
////                    DateFormat dateFormat55 = new SimpleDateFormat("dd MMM yyyy  hh:mm aa");
////                    Date resultdate = new Date(yourmilliseconds);
////                    String Date = dateFormat55.format(resultdate);
////
//////                String sqlpayment = "SELECT STATUS,TotalQty FROM Sales WHERE BillNo = '"+billNo+"'";
//////                Cursor csqlpayment = DBFunc.Query(sqlsales,false);
//////                if (csqlpayment != null) {
//////                    if (csqlpayment.moveToNext()){
//////                        //STATUS = csqlpayment.getString(0);
//////                        //totalitm = csqlpayment.getString(1);
//////                        //totalAmt
//////                    }
//////                    csqlpayment.close();
//////                }
////
////                    String chkexisting = "SELECT ID FROM BillList WHERE BillNo = '"+billNo+"' ";
////                    Cursor cccc = DBFunc.Query(chkexisting,false);
////                    if (cccc != null) {
////                        if (cccc.getCount() == 0) {
////                            if (STATUS.toUpperCase().equals(ENUM.REFUND.toUpperCase())){
////                                Double ttitm = (-1) * Double.valueOf(totalitm);
////                                totalitm = String.valueOf(ttitm);
////
////                                Double ttotalAmt = (-1) * Double.valueOf(totalAmt);
////                                totalAmt = String.valueOf(ttotalAmt);
////                            }
////
////                            String sql_billlist = "INSERT INTO BillList (BillID,BillNo,STATUS,TotalItems," +
////                                    "Date,TableNo,QueueNo,OnlineOrderBill,TotalAmount,DateTime) " +
////                                    "VALUES ('" + DBFunc.PurifyString(billID) + "'," +
////                                    "'" + DBFunc.PurifyString(billNo) + "','" +
////                                    //DBFunc.PurifyString(blist.getSTATUS()) + "','" +
////                                    DBFunc.PurifyString(STATUS) + "','" +
////                                    DBFunc.PurifyString(totalitm) + "','" +
////                                    DBFunc.PurifyString(Date) + "','" +
////                                    DBFunc.PurifyString(tblNo) + "','" +
////                                    DBFunc.PurifyString(queueNo) + "','" +
////                                    DBFunc.PurifyString("OFF") + "','" +
////                                    DBFunc.PurifyString(totalAmt) + "'," +
////                                    "" + dt + ")";
////                            Log.i("SaveBillListByHardCode_","sql_billlist__"+sql_billlist);
////                            DBFunc.ExecQuery(sql_billlist, false);
////                        }else {
////                            DBFunc.ExecQuery("DELETE FROM BillList WHERE BillNo = '"+billNo+"'",false);
////
////                            if (STATUS.toUpperCase().equals(ENUM.REFUND.toUpperCase())){
////                                Double ttitm = (-1) * Double.valueOf(totalitm);
////                                totalitm = String.valueOf(ttitm);
////
////                                Double ttotalAmt = (-1) * Double.valueOf(totalAmt);
////                                totalAmt = String.valueOf(ttotalAmt);
////                            }
////
////                            String sql_billlist = "INSERT INTO BillList (BillID,BillNo,STATUS,TotalItems," +
////                                    "Date,TableNo,QueueNo,OnlineOrderBill,TotalAmount,DateTime) " +
////                                    "VALUES ('" + DBFunc.PurifyString(billID) + "'," +
////                                    "'" + DBFunc.PurifyString(billNo) + "','" +
////                                    //DBFunc.PurifyString(blist.getSTATUS()) + "','" +
////                                    DBFunc.PurifyString(STATUS) + "','" +
////                                    DBFunc.PurifyString(totalitm) + "','" +
////                                    DBFunc.PurifyString(Date) + "','" +
////                                    DBFunc.PurifyString(tblNo) + "','" +
////                                    DBFunc.PurifyString(queueNo) + "','" +
////                                    DBFunc.PurifyString("OFF") + "','" +
////                                    DBFunc.PurifyString(totalAmt) + "'," +
////                                    "" + dt + ")";
////                            Log.i("SaveBillListByHardCode_","sql_billlist__"+sql_billlist);
////                            DBFunc.ExecQuery(sql_billlist, false);
////                        }
////                        cccc.close();
////                    }
////                //}
////            }
////            c.close();
////        }
//    }

//    public static void RunFrist() {
//        Integer TotalQty = 0;
//        Integer ID = 0;
//        Integer PaymentTypeID = 0;
//        String BillNo = "";
//        String CashierName = "";
//        String SalesDateTime = "";
//        String BillHour = "";
//        String STATUS = "";
//        String DiscountName = "";
//        String DiscountType = "";
//        String DiscountTypeName = "";
//        String PaymentTypeName = "";
//        long dt = 0;
//        Double SubTotal = 0.0;
//        Double Totalamount = 0.0;
//        Double Changeamount = 0.0;
//        Double PaymentAmount = 0.0;
//        Double GrossSales = 0.0;
//        Double TotalItemDisount = 0.0;
//        Double TotalBillDisount = 0.0;
//        Double GrossTotal = 0.0;
//        Double ServiceCharges = 0.0;
//        Double TotalNettSales = 0.0;
//        Double TotalTaxes = 0.0;
//        Double RoundAdj = 0.0;
//        Double DiscountID = 0.0;
//        Double DiscountValue = 0.0;
//
//        String sql = "SELECT ID,BillNo,TotalQty,SubTotal,Totalamount,Changeamount,PaymentTypeID,PaymentTypeName,PaymentAmount, " +
//        "GrossSales,TotalItemDisount,TotalBillDisount,GrossTotal,ServiceCharges,TotalNettSales,TotalTaxes, " +
//        "CashierName,SalesDateTime,BillHour,STATUS, " +
//        "RoundAdj,DiscountID,DiscountName,DiscountType,DiscountTypeName,DiscountValue,BillID,DateTime " +
//        "FROM Sales Where STATUS = 'REFUND' ";
//
//        Log.i("sql___","sql__"+sql);
//        Cursor c = DBFunc.Query(sql,false);
//        if (c != null) {
//            while (c.moveToNext()) {
//
//                ID = c.getInt(0);
//                BillNo =  c.getString(1);
//
//                String sqql = "SELECT BillNo FROM Sales WHERE ReferenceBillNo = '"+BillNo+"'";
//                Cursor csqql = DBFunc.Query(sqql,false);
//                if (csqql != null) {
//                    if (csqql.getCount() == 0) {
//
//                        SalesDateTime =  c.getString(17);
//                        dt = c.getLong(27);
//
//                        String old_bId = findBillIDByBillNo(BillNo);//532
////
//
//                        //REFUND HardCode
//                        RefundSaveBill(old_bId);
////
//                        Integer lstbillID = Query.findLatestID("BillNo","Bill",false);
//                        String refubdBillNo = billNo(lstbillID);
////
//                        String bId = findBillIDByBillNo(refubdBillNo);
////
//                        RefundBillList(BillNo,refubdBillNo,dt);
//
//                        RefundDetailsBillProduct(BillNo,refubdBillNo,SalesDateTime,bId,dt);
//
//                         //script
//                        //RefundBillPayment(BillNo,refubdBillNo);
//                        String billPaymentsql = "SELECT BillNo,PaymentID,Name,Amount,QRCode,ChangeAmount,SaleSync,PaymentRemarks " +
//                                "FROM BillPayment " +
//                                "where BillNo = '" + BillNo +"'";
//                        Log.i("__sql", billPaymentsql);
//                        Cursor cd = DBFunc.Query(billPaymentsql, false);
//                        if (cd != null) {
//                            while (cd.moveToNext()) {
//
//                                //String billPaymentBillNo = c.getString(0);
//                                String billPaymentPaymentID = cd.getString(1);
//                                String billPaymentPaymentName = cd.getString(2);
//                                Double billPaymentPaymentAmount = (-1) * cd.getDouble(3);
//                                String billPaymentQRCode = cd.getString(4);
//                                String billPaymentChangeAmount = String.valueOf((-1) *  cd.getDouble(5));
//                                String billPaymentSaleSync = cd.getString(6);
//                                String billPaymentRemarks = cd.getString(7);
//
//                                String bidsqls = "SELECT BillNo " +
//                                        "FROM BillPayment WHERE BillNo = '"+refubdBillNo+"' " +
//                                        "AND PaymentID = '"+billPaymentPaymentID+"'";
//                                Log.i("__sql", bidsqls);
//                                Cursor cdds = DBFunc.Query(bidsqls, false);
//                                if (cdds != null) {
//                                    if (cdds.getCount() == 0) {
//                                        //REFUND
//                                        sql = "INSERT INTO BillPayment (BillNo,PaymentID,Name,Amount,PaymentDateTime,QRCode,PaymentRemarks,ChangeAmount,STATUS,SaleSync) VALUES (";
//                                        //sql += BillNo + ", ";
//                                        sql += "'"+refubdBillNo+"'" + ", ";
//                                        sql += billPaymentPaymentID + ", ";
//                                        sql += "'" + DBFunc.PurifyString(DBFunc.PurifyString(billPaymentPaymentName)) + "', ";
//                                        sql += Query.DoubleAmountFormat(Double.valueOf(billPaymentPaymentAmount)) + ", ";
//                                        sql += dt + ", ";
//                                        sql += "'" + billPaymentQRCode + "', ";
//                                        sql += "'" + billPaymentRemarks + "', ";
//                                        sql += "'" + Query.DoubleAmountFormat(Double.valueOf(billPaymentChangeAmount)) + "', ";
//                                        sql += "'REFUND',";
//                                        sql += "'"+billPaymentSaleSync+"')";
//                                        Log.i("Sf_SaveBillPayment", sql);
//                                        DBFunc.ExecQuery(sql, false);
//
//                                    }
//                                    cdds.close();
//                                }
//
////                    Query.SaveBillPayment(billPaymentBillNo,billPaymentPaymentID,billPaymentPaymentName,billPaymentPaymentAmount,
////                            billPaymentQRCode,billPaymentChangeAmount);
//                            }
//                            cd.close();
//                        }
//
//                        Integer sales_id = ID;
//                        RefundSalesAndItems(BillNo,refubdBillNo,sales_id,SalesDateTime,BillHour,bId,dt,"Amend");
//                    }
//                    csqql.close();
//                }
//
//            }
//            c.close();
//        }
//
//    }
//public static void RunSecond() {
//    Integer TotalQty = 0;
//    Integer ID = 0;
//    Integer PaymentTypeID = 0;
//    String BillNo = "";
//    String CashierName = "";
//    String SalesDateTime = "";
//    String BillHour = "";
//    String STATUS = "";
//    String DiscountName = "";
//    String DiscountType = "";
//    String DiscountTypeName = "";
//    String PaymentTypeName = "";
//    long dt = 0;
//    Double SubTotal = 0.0;
//    Double Totalamount = 0.0;
//    Double Changeamount = 0.0;
//    Double PaymentAmount = 0.0;
//    Double GrossSales = 0.0;
//    Double TotalItemDisount = 0.0;
//    Double TotalBillDisount = 0.0;
//    Double GrossTotal = 0.0;
//    Double ServiceCharges = 0.0;
//    Double TotalNettSales = 0.0;
//    Double TotalTaxes = 0.0;
//    Double RoundAdj = 0.0;
//    Integer DiscountID = 0;
//    Double DiscountValue = 0.0;
//
////    String sql = "SELECT ID,BillNo,TotalQty,SubTotal,Totalamount,Changeamount,PaymentTypeID,PaymentTypeName,PaymentAmount, " +
////            "GrossSales,TotalItemDisount,TotalBillDisount,GrossTotal,ServiceCharges,TotalNettSales,TotalTaxes, " +
////            "CashierName,SalesDateTime,BillHour,STATUS, " +
////            "RoundAdj,DiscountID,DiscountName,DiscountType,DiscountTypeName,DiscountValue,BillID,DateTime " +
////            "FROM Sales Where BillNo IN ('00000555')";
//    String sql = "SELECT ID,BillNo,TotalQty,SubTotal,Totalamount,Changeamount,PaymentTypeID,PaymentTypeName,PaymentAmount, " +
//            "GrossSales,TotalItemDisount,TotalBillDisount,GrossTotal,ServiceCharges,TotalNettSales,TotalTaxes, " +
//            "CashierName,SalesDateTime,BillHour,STATUS, " +
//            "RoundAdj,DiscountID,DiscountName,DiscountType,DiscountTypeName,DiscountValue,BillID,DateTime " +
//            "FROM Sales Where STATUS = 'REFUND' AND ReferenceBillNo IS NULL";
//    Log.i("sql___","sql__"+sql);
//    Cursor c = DBFunc.Query(sql,false);
//    if (c != null){
//        while (c.moveToNext()){
//            Log.i("Billno__","billno___"+c.getString(1));
//            ID = c.getInt(0);
//            BillNo = c.getString(1);
////
//            if (c.getInt(2) > 0.0){ // +3 > 0.0
//                TotalQty = c.getInt(2); // 3
//            }else {
//                TotalQty = (-1) * c.getInt(2); // (-1) * -3 = +3
//            }
//            SubTotal = CheckMinusPlusValue(c.getDouble(3));
//            Totalamount = CheckMinusPlusValue(c.getDouble(4));
//            Changeamount = CheckMinusPlusValue(c.getDouble(5));
//            PaymentTypeID = c.getInt(6);
//            PaymentTypeName = c.getString(7);
//            PaymentAmount = CheckMinusPlusValue(c.getDouble(8));
//            GrossSales = CheckMinusPlusValue(c.getDouble(9));
//            TotalItemDisount = CheckMinusPlusValue(c.getDouble(10));
//            TotalBillDisount = CheckMinusPlusValue(c.getDouble(11));
//            GrossTotal = CheckMinusPlusValue(c.getDouble(12));
//            ServiceCharges = CheckMinusPlusValue(c.getDouble(13));
//            TotalNettSales = CheckMinusPlusValue(c.getDouble(14));
//            TotalTaxes = CheckMinusPlusValue(c.getDouble(15));
//            CashierName = c.getString(16);
////                        SalesDateTime =  c.getString(17);
////                        BillHour =  c.getString(18);
////                        STATUS =  c.getString(19);
//            RoundAdj = CheckMinusPlusValue(c.getDouble(20));
//            DiscountID = c.getInt(21);
//            DiscountName = c.getString(22);
//            DiscountType = c.getString(23);
//            DiscountTypeName = c.getString(24);
//            DiscountValue = CheckMinusPlusValue(c.getDouble(25));;
////
////
//            String query = "UPDATE Sales SET ";
//            query += "TotalQty = '"+ String.valueOf(TotalQty)+"', ";
//            query += "SubTotal = '"+ String.valueOf(SubTotal)+"', ";
//            query += "Totalamount = '"+ String.valueOf(Totalamount)+"', ";
//            query += "Changeamount = '"+ String.valueOf(Changeamount)+"', ";
//            query += "PaymentTypeID = '"+ String.valueOf(PaymentTypeID)+"', ";
//            query += "PaymentTypeName = '"+ String.valueOf(PaymentTypeName)+"', ";
//            query += "PaymentAmount = '"+ String.valueOf(PaymentAmount)+"', ";
//            query += "GrossSales = '"+ String.valueOf(GrossSales)+"', ";
//            query += "TotalItemDisount = '"+ String.valueOf(TotalItemDisount)+"', ";
//            query += "TotalBillDisount = '"+ String.valueOf(TotalBillDisount)+"', ";
//            query += "GrossTotal = '"+ String.valueOf(GrossTotal)+"', ";
//            query += "ServiceCharges = '"+ String.valueOf(ServiceCharges)+"', ";
//            query += "TotalNettSales = '"+ String.valueOf(TotalNettSales)+"', ";
//            query += "TotalTaxes = '"+ String.valueOf(TotalTaxes)+"', ";
//            query += "CashierName = '"+ String.valueOf(CashierName)+"', ";
//            query += "ReferenceSalesID = '0', ";
//            query += "STATUS = 'SALES', ";
//
////                String saleitmsql = "SELECT DateTime " +
////                        "FROM SalesItem " +
////                        "where ID = " + ID;
//            String saleitmsql = "SELECT strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch'),DateTime " +
//                    "FROM SalesItem " +
//                    "where BillNo = '"+BillNo+"'" ;
//            Log.i("__sql", saleitmsql);
//            long sitmdt =  0;
//            Cursor saleitmc = DBFunc.Query(saleitmsql,false);
//            if (saleitmc != null) {
//                if (saleitmc.moveToNext()) {
//                    sitmdt = saleitmc.getLong(1);
//                    DateFormat formatter = new SimpleDateFormat(Constraints.dateYMD,Locale.US);
//                    try {
//                        Date date = (Date)formatter.parse(saleitmc.getString(0));
//
//                        String dateFormat3 = CheckOutActivity.dateFormat55.format(date).toString();
//                        String dateFormat4 = CheckOutActivity.dateFormat555.format(date).toString();
//
//                        Log.i("Df____dateFormat3",BillNo+"__"+"dateFormat3__"+dateFormat3+"dateFormat4__"+dateFormat4);
////
//                        query += "SalesDateTime = '"+ dateFormat3 +"', ";
////
//                        query += "BillHour = '" + dateFormat4 + "', ";
//
//                        query += "DateTime = " + sitmdt +",";
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
////
//            query += "STATUS = '"+ Constraints.SALES +"', ";
//
//
//            query += "RoundAdj = '"+ RoundAdj +"', ";
//
//            query += "DiscountID = '"+ DiscountID +"', ";
//            query += "DiscountName = '"+ DiscountName +"', ";
//            query += "DiscountType = '"+ DiscountType +"', ";
//            query += "DiscountTypeName = '"+ DiscountTypeName +"', ";
//            query += "DiscountValue = '"+ DiscountValue +"' ";
//            query += " WHERE ID = "+ID+"";
//
//
//            DBFunc.ExecQuery(query, false);
////
////
////                Query.UpdateBillPayment();
//            String updatebpsql = "SELECT BillNo FROM BillPayment WHERE BillNo = '"+BillNo+"'";
//
//            Cursor updatebpc = DBFunc.Query(updatebpsql,false);
//            if ( updatebpc != null) {
//                while (updatebpc.moveToNext()) {
//                    String billPaymentbillNo = updatebpc.getString(0);
//
//                    String sql_sales = "SELECT STATUS,isZ FROM Sales WHERE BillNo = '"+ billPaymentbillNo +"' ";
//                    Cursor Cursor = DBFunc.Query(sql_sales,false);
//                    if (Cursor != null){
//                        while (Cursor.moveToNext()){
//                            String sstatus = Cursor.getString(0);
//                            String sisZZ = Cursor.getString(1);
////                        if (sisZZ.equals("null")){
////                            sisZZ = "";
////                        }
//                            Log.i("DFSFD____","sstatus___"+sstatus+"__"+sisZZ);
//                            String updatesql = "UPDATE BillPayment SET STATUS = '"+sstatus+"' , isZ = '"+sisZZ+"' WHERE BillNo = '"+billPaymentbillNo+"' ";
//                            Log.i("DFSFD____","updatesql_"+updatesql);
//                            DBFunc.ExecQuery(updatesql,false);
//                        }
//                        Cursor.close();
//                    }
//                }
//                updatebpc.close();
//            }
//
//
//
//            Log.i("Df____query","query__"+query);
////
//            Query.UpdateStatusBillList(BillNo, Constraints.SALES);
//
//        }
//        c.close();
//    }
//
////        String sql = "SELECT BillNo,TransNo FROM Bill";
////        Cursor c = DBFunc.Query(sql,false);
////        Integer offlineBillNo = 0;
////        if (c != null){
////            while (c.moveToNext()){
////
////                String billID = c.getString(0);
////                String billNo = c.getString(1);
////
////               // if (Integer.parseInt(billID) > 99) {
////
////                    Log.i("Dfd__billNo","billNo__"+billNo);
////
////                    String STATUS = "";
////                    String totalitm = "";
////                    //String Date = GetDateFormart55();
////                    //dt
////
////
////                    String tblNo = "";
////                    String queueNo = "";
////                    String totalAmt = "0";
////
////                    String sqlsales = "SELECT STATUS,TotalQty,TotalNettSales FROM Sales WHERE BillNo = '"+billNo+"'";
////                    Cursor csales = DBFunc.Query(sqlsales,false);
////                    if (csales != null) {
////                        if (csales.moveToNext()){
////                            STATUS = csales.getString(0);
////                            totalitm = csales.getString(1);
////                            totalAmt = csales.getString(2);
////                        }
////                        csales.close();
////                    }
////                    Integer dt = 0;
////                    if (STATUS.length() == 0) {
////                        String sqldetailbillproduct = " SELECT CANCEL,count(ProductQty),SUM(ProductPrice),ProductQty,SUM(ItemDiscountAmount),DateTime " +
////                                "FROM Details_BillProduct Where ProductQty != -1 AND BillNo = '" + billNo + "' "+
////                                "group by BillNo ";
////                        Cursor cdetailbillproduct = DBFunc.Query(sqldetailbillproduct,false);
////                        if (cdetailbillproduct != null) {
////                            if (cdetailbillproduct.moveToNext()){
////                                STATUS = cdetailbillproduct.getString(0);
////                                totalitm = cdetailbillproduct.getString(1);
////                                Double realamt = cdetailbillproduct.getDouble(2) - cdetailbillproduct.getDouble(4);
////                                totalAmt = String.valueOf(realamt);
////                                dt = cdetailbillproduct.getInt(5);
////                            }
////                            cdetailbillproduct.close();
////                        }
////                    }
////
////                    long yourmilliseconds = dt;
////                    DateFormat dateFormat55 = new SimpleDateFormat("dd MMM yyyy  hh:mm aa");
////                    Date resultdate = new Date(yourmilliseconds);
////                    String Date = dateFormat55.format(resultdate);
////
//////                String sqlpayment = "SELECT STATUS,TotalQty FROM Sales WHERE BillNo = '"+billNo+"'";
//////                Cursor csqlpayment = DBFunc.Query(sqlsales,false);
//////                if (csqlpayment != null) {
//////                    if (csqlpayment.moveToNext()){
//////                        //STATUS = csqlpayment.getString(0);
//////                        //totalitm = csqlpayment.getString(1);
//////                        //totalAmt
//////                    }
//////                    csqlpayment.close();
//////                }
////
////                    String chkexisting = "SELECT ID FROM BillList WHERE BillNo = '"+billNo+"' ";
////                    Cursor cccc = DBFunc.Query(chkexisting,false);
////                    if (cccc != null) {
////                        if (cccc.getCount() == 0) {
////                            if (STATUS.toUpperCase().equals(ENUM.REFUND.toUpperCase())){
////                                Double ttitm = (-1) * Double.valueOf(totalitm);
////                                totalitm = String.valueOf(ttitm);
////
////                                Double ttotalAmt = (-1) * Double.valueOf(totalAmt);
////                                totalAmt = String.valueOf(ttotalAmt);
////                            }
////
////                            String sql_billlist = "INSERT INTO BillList (BillID,BillNo,STATUS,TotalItems," +
////                                    "Date,TableNo,QueueNo,OnlineOrderBill,TotalAmount,DateTime) " +
////                                    "VALUES ('" + DBFunc.PurifyString(billID) + "'," +
////                                    "'" + DBFunc.PurifyString(billNo) + "','" +
////                                    //DBFunc.PurifyString(blist.getSTATUS()) + "','" +
////                                    DBFunc.PurifyString(STATUS) + "','" +
////                                    DBFunc.PurifyString(totalitm) + "','" +
////                                    DBFunc.PurifyString(Date) + "','" +
////                                    DBFunc.PurifyString(tblNo) + "','" +
////                                    DBFunc.PurifyString(queueNo) + "','" +
////                                    DBFunc.PurifyString("OFF") + "','" +
////                                    DBFunc.PurifyString(totalAmt) + "'," +
////                                    "" + dt + ")";
////                            Log.i("SaveBillListByHardCode_","sql_billlist__"+sql_billlist);
////                            DBFunc.ExecQuery(sql_billlist, false);
////                        }else {
////                            DBFunc.ExecQuery("DELETE FROM BillList WHERE BillNo = '"+billNo+"'",false);
////
////                            if (STATUS.toUpperCase().equals(ENUM.REFUND.toUpperCase())){
////                                Double ttitm = (-1) * Double.valueOf(totalitm);
////                                totalitm = String.valueOf(ttitm);
////
////                                Double ttotalAmt = (-1) * Double.valueOf(totalAmt);
////                                totalAmt = String.valueOf(ttotalAmt);
////                            }
////
////                            String sql_billlist = "INSERT INTO BillList (BillID,BillNo,STATUS,TotalItems," +
////                                    "Date,TableNo,QueueNo,OnlineOrderBill,TotalAmount,DateTime) " +
////                                    "VALUES ('" + DBFunc.PurifyString(billID) + "'," +
////                                    "'" + DBFunc.PurifyString(billNo) + "','" +
////                                    //DBFunc.PurifyString(blist.getSTATUS()) + "','" +
////                                    DBFunc.PurifyString(STATUS) + "','" +
////                                    DBFunc.PurifyString(totalitm) + "','" +
////                                    DBFunc.PurifyString(Date) + "','" +
////                                    DBFunc.PurifyString(tblNo) + "','" +
////                                    DBFunc.PurifyString(queueNo) + "','" +
////                                    DBFunc.PurifyString("OFF") + "','" +
////                                    DBFunc.PurifyString(totalAmt) + "'," +
////                                    "" + dt + ")";
////                            Log.i("SaveBillListByHardCode_","sql_billlist__"+sql_billlist);
////                            DBFunc.ExecQuery(sql_billlist, false);
////                        }
////                        cccc.close();
////                    }
////                //}
////            }
////            c.close();
////        }
//}

    private static Double CheckMinusPlusValue(Double value) { //+3 //-3
        Double v = 0.0;
        if (value >= 0.0){ // +3 > 0.0
            v = value; // 3
        }else {
            v = (-1) * value; // (-1) * -3 = +3
        }
        if (String.valueOf(v).equals("-0.0")){
            v = 0.0;
        }
        return v; // -3
    }

    public static Cursor GetdetailsBillProductByBillNo(String billNo) {
        String sql = "SELECT (ProductQty),ProductName,ProductPrice,(ItemDiscountAmount),DiscountName,DiscountTypeName,DiscountValue," +
                "TaxID,TaxName,(TaxAmount),ProductID,(ProductPrice),ProductQty,OpenPriceStatus,Remarks,ID " +
                "FROM DetailsBillProduct Where billNo = '"+ billNo +"' group by ProductID,OpenPriceStatus,Remarks";
//        String sql = "SELECT SUM(ProductQty),ProductName,ProductPrice,SUM(ItemDiscountAmount),DiscountName,DiscountTypeName,DiscountValue," +
//                "TaxID,TaxName,SUM(TaxAmount),ProductID,SUM(ProductPrice),ProductQty,OpenPriceStatus,Remarks,ID " +
//                "FROM DetailsBillProduct Where billNo = '"+ billNo +"' group by ProductID,OpenPriceStatus,Remarks";
        //Log.i("_sql__",sql);
        return  DBFunc.Query(sql, false);
    }

    public static String billNo(int BillID) {
        String bill_no = "";
        //if (String.valueOf(BillID).length() == 1 ) { // 1,2,3,4, 9
        if (BillID <= 9) {
            bill_no = "0000000" + BillID;
        } else if (BillID <= 99) {
        //}else if (String.valueOf(BillID).length() == 2 ) { // 10,99
            bill_no = "000000" + BillID;
        //}else  if (String.valueOf(BillID).length() == 3 ) { // 100
        } else if (BillID <= 999) {
            bill_no = "00000" + BillID;
        //} else if (BillID > 999) {
        //}else  if (String.valueOf(BillID).length() == 4 ) { // 1000
        } else if (BillID <= 9999) {
            bill_no = "0000" + BillID;
       // }else  if (String.valueOf(BillID).length() == 5 ) { // 1000
        } else if (BillID <= 99999) {
            bill_no = "000" + BillID;
        } else {
            bill_no = "00" + BillID;
        }

        String receiptnoreference = "";
        String sql = "SELECT receipt_no_reference FROM POSSys ";
        Log.i("query___","sql__"+sql);
        Cursor c = DBFunc.Query(sql, true);
        if (c != null) {
            if (c.moveToNext()) {
                if (!c.isNull(0)) {
                    receiptnoreference = c.getString(0);
                }
            }
            c.close();
        }
        String bno = "";
        Log.i("receiptnoreference___","receiptnoreference_____"+receiptnoreference);
        if (receiptnoreference != null && receiptnoreference.trim().length() > 0) {
            bno = receiptnoreference+bill_no;
        }else {
            bno = bill_no;
        }
        Log.i("r__","receibno_____"+bno);
        return bno;
    }

//    public static void UpdatPLU(String ID,String pluName,String pluPrice,String str_img,String pluCode,
//                                    String str_checked_open_price_edit,String cateid,String catename,
//                                    String taxID,String taxName) {
//        String query = "UPDATE PLU SET ";
//        query += "Name = '"+DBFunc.PurifyString(pluName)+"', ";
//        query += "DeptID = "+0000+", ";
//        query += "Price = "+pluPrice+", ";
//        query += "Image = "+ "'" + DBFunc.PurifyString(str_img) + "'," ;
//        query += "Option = '"+""+"', ";
//        query += "Code = '"+DBFunc.PurifyString(pluCode)+"', ";
////        query += "ProductTaxID = '"+taxID+"', ";
////        query += "ProductTaxName = '"+tax_Name+"', ";
//        query += "AllowOpenPrice = "+ str_checked_open_price_edit+", ";
//
//        query += "ProductCategoryID = '" + cateid + "', ";
//        query += "ProductCategoryName = '"+catename+"', ";
//        query += "ProductTaxID = '" + taxID + "', ";
//        query += "ProductTaxName = '"+ taxName +"', ";
//        query += "Condi_Seq = "+00000+", ";
//        query += "DateTime = "+System.currentTimeMillis()+" ";
//        query += "WHERE ID = "+Integer.parseInt(ID);
//
//        Log.i("Dfdfquery___","query__"+query);
//        DBFunc.ExecQuery(query, true);
//    }

    public static boolean appInstalledOrNot(Context context,String uri) {
        //public static boolean appInstalledOrNot(Context context, String uri) {
            PackageManager pm = context.getPackageManager();
            List<PackageInfo> packageInfoList = pm.getInstalledPackages(PackageManager.GET_ACTIVITIES);
            if (packageInfoList != null) {
                for (PackageInfo packageInfo : packageInfoList) {
                    String packageName = packageInfo.packageName;
                    if (packageName != null && packageName.equals(uri)) {
                        return true;
                    }
                }
            }
            return false;
        //}
    }

    public static boolean isEmulator() {
        return Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")
                || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
                || "google_sdk".equals(Build.PRODUCT);
    }

    public static void SaveBillMercatus(BillMercatus bmer) {
        String existingValue = Query.findfieldNameById("BillNo", "BillNo", bmer.getBillNo() , "BillMercatus", false);
        if (existingValue.length() > 1) {
            DBFunc.ExecQuery("DELETE From BillMercatus WHERE BillNo = '"+bmer.getBillNo()+"'",false);
        }
        String sql = "INSERT INTO BillMercatus (BillNo,transaction_id,billmercatuspaymentID,billmercatusvouchersID," +
                "billmercatusmallloyaltydetailsID,IsMember,Date,DateTime) VALUES ('" +
                bmer.getBillNo()+"', '"+bmer.getTransaction_id()+"','"+
                bmer.getBillmercatuspaymentID()+"','"+bmer.getBillmercatusvouchersID()+"','" +
                bmer.getBillmercatusmallloyaltydetailsID() +"', '" +
                bmer.getIsMember() +"', '" +
                GetDateFormart55() +"', "
                +System.currentTimeMillis()+")";
        Log.i("Stock","BillMercatus_"+sql);
        DBFunc.ExecQuery(sql,false);
    }
    public static void SaveBillMercatusDetails(BillMercatusDetails bmerdetails) {
        String existingValue = Query.findfieldNameById("BillNo", "BillNo", bmerdetails.getBillNo() , "BillMercatusDetails", false);
        if (existingValue.length() > 1) {
            DBFunc.ExecQuery("DELETE From BillMercatusDetails WHERE BillNo = '"+bmerdetails.getBillNo()+"'",false);
        }
        String sql = "INSERT INTO BillMercatusDetails (BillNo,card_label,card_number,merchant_id,terminal_id,invoice_number," +
                "DateTime) VALUES ('" +
                bmerdetails.getBillNo()+"', '"+bmerdetails.getCard_label()+"','"+
                bmerdetails.getCard_number()+"','"+bmerdetails.getMerchant_id()+"','" +
                bmerdetails.getTerminal_id() +"', '" +
                bmerdetails.getInvoice_number() +"', " +
                +System.currentTimeMillis()+")";
        Log.i("Stock","SaveStockAdjustment_"+sql);
        DBFunc.ExecQuery(sql,false);
    }
    public static void SaveBillMercatusPayment(BillMercatusPayment bmerpayment) {
        String existingValue = Query.findfieldNameById("BillNo", "BillNo", bmerpayment.getBillNo() , "BillMercatusVouchers", false);
        if (existingValue.length() > 1) {
            DBFunc.ExecQuery("DELETE From BillMercatusVouchers WHERE BillNo = '"+bmerpayment.getBillNo()+"'",false);
        }
        String sql = "INSERT INTO BillMercatusPayment (BillNo,payment_type,payment_id,amount,billmercatusdetailsID," +
                "DateTime) VALUES ('" +
                bmerpayment.getBillNo()+"', '"+bmerpayment.getPayment_type()+"','"+
                bmerpayment.getPayment_id()+"','"+bmerpayment.getAmount()+"','" +
                bmerpayment.getBillmercatusdetailsID() +"', " +
                +System.currentTimeMillis()+")";
        Log.i("Stock","SaveStockAdjustment_"+sql);
        DBFunc.ExecQuery(sql,false);
    }
    public static void SaveBillMercatusVouchers(BillMercatusVouchers bmervouchers) {
        String existingValue = Query.findfieldNameById("BillNo", "BillNo", bmervouchers.getBillNo() , "BillMercatusVouchers", false);
        if (existingValue.length() > 1) {
            DBFunc.ExecQuery("DELETE From BillMercatusVouchers WHERE BillNo = '"+bmervouchers.getBillNo()+"'",false);
        }
        String sql = "INSERT INTO BillMercatusVouchers (BillNo,voucher_number,voucher_value," +
                "DateTime) VALUES ('" +
                bmervouchers.getBillNo()+"', '"+bmervouchers.getVoucher_number()+"','"+
                bmervouchers.getVoucher_value()+"',"+
                +System.currentTimeMillis()+")";
        Log.i("Stock","SaveStockAdjustment_"+sql);
        DBFunc.ExecQuery(sql,false);
    }
    public static void SaveBillMercatusMallLoyaltyDetails(BillMercatusMallLoyaltyDetails bmerloyaltydetails) {
        String existingValue = Query.findfieldNameById("BillNo", "BillNo", bmerloyaltydetails.getBillNo() , "BillMercatusMallLoyaltyDetails", false);
        if (existingValue.length() > 1) {
            DBFunc.ExecQuery("DELETE From BillMercatusMallLoyaltyDetails WHERE BillNo = '"+bmerloyaltydetails.getBillNo()+"'",false);
        }
        String sql = "INSERT INTO BillMercatusMallLoyaltyDetails (BillNo,status,transaction_id,transaction_amount," +
                "DateTime) VALUES ('" +
                bmerloyaltydetails.getBillNo()+"', '"+bmerloyaltydetails.getStatus()+"','"+
                bmerloyaltydetails.getTransaction_id()+"','"+
                bmerloyaltydetails.getTransaction_amount()+"',"+
                +System.currentTimeMillis()+")";
        Log.i("Stock","SaveStockAdjustment_"+sql);
        DBFunc.ExecQuery(sql,false);
    }
    public static void SaveBillJeripay(BillJeripay bjeripay) {
        String existingValue = Query.findfieldNameById("BillNo", "BillNo", bjeripay.getBillNo() , "BillJeripay", false);
        if (existingValue.length() > 1) {
            DBFunc.ExecQuery("DELETE From BillJeripay WHERE BillNo = '"+bjeripay.getBillNo()+"'",false);
        }
        String sql = "INSERT INTO BillJeripay (BillNo,uuid,status,acquirer,acquirer_id,acquirer_payment_id," +
                "amount,billjeripaydetailsID,IsCardPayment," +
                "Date,DateTime) VALUES ('" +
                bjeripay.getBillNo()+"', '"+bjeripay.getUuid()+"','"+
                bjeripay.getStatus()+"', '"+bjeripay.getAcquirer()+"','"+
                bjeripay.getAcquirer_id()+"','"+
                bjeripay.getAcquirer_payment_id()+"','"+
                bjeripay.getAmount()+"','"+
                bjeripay.getBilljeripaydetailsID()+"','"+
                bjeripay.getIsCardPayment()+"','"+
                GetDateFormart55()+"',"+
                +System.currentTimeMillis()+")";
        Log.i("Stock","SaveBillJeripay_"+sql);
        DBFunc.ExecQuery(sql,false);
    }
    public static void SaveBillJeripayDetails(BillJeripayDetails bjeripaydetails) {
        String existingValue = Query.findfieldNameById("BillNo", "BillNo", bjeripaydetails.getBillNo() , "BillJeripayDetails", false);
        if (existingValue.length() > 1) {
            DBFunc.ExecQuery("DELETE From BillJeripayDetails WHERE BillNo = '"+bjeripaydetails.getBillNo()+"'",false);
        }
        String sql = "INSERT INTO BillJeripayDetails (BillNo,card_label,card_number,merchant_id,terminal_id,invoice_number," +
                "DateTime) VALUES ('" +
                bjeripaydetails.getBillNo()+"', '"+bjeripaydetails.getCard_label()+"','"+
                bjeripaydetails.getCard_number()+"', '"+bjeripaydetails.getMerchant_id()+"','"+
                bjeripaydetails.getTerminal_id()+"','"+
                bjeripaydetails.getInvoice_number()+"',"+
                +System.currentTimeMillis()+")";
        Log.i("Stock","SaveStockAdjustment_"+sql);
        DBFunc.ExecQuery(sql,false);
    }

    public static Cursor GetBillMercatus(String billNo) {
        String sql = "SELECT IsMember FROM BillMercatus WHERE BillNo = '"+billNo+"' ";
        return DBFunc.Query(sql,false);
    }
    public static Cursor GetMercatusVoucher(String billNo) {
        String sql = "SELECT voucher_number,voucher_value FROM BillMercatusVouchers WHERE BillNo = '"+billNo+"' ";
        return DBFunc.Query(sql,false);
    }

    public static Cursor GetMercatusPayment(String billNo) {
        String sql = "SELECT payment_type,amount FROM BillMercatusPayment WHERE BillNo = '"+billNo+"' ";
        return DBFunc.Query(sql,false);
    }

    public static Cursor GetMercatusDetails(String billNo) {
        String sql = "SELECT card_label,card_number,invoice_number FROM BillMercatusDetails WHERE BillNo = '"+billNo+"' ";
        return DBFunc.Query(sql,false);
    }

    public static Cursor GetMercatusMallLoyaltyDetails(String billNo) {
        String sql = "SELECT status,transaction_id,transaction_amount FROM BillMercatusMallLoyaltyDetails WHERE BillNo = '"+billNo+"' ";
        return DBFunc.Query(sql,false);
    }
    public static Cursor GetJeripayDetails(String billNo) {
        String sql = "SELECT card_label,card_number,invoice_number," +
                "merchant_id,terminal_id " +
                "FROM BillJeripayDetails WHERE BillNo = '"+billNo+"' ";
        return DBFunc.Query(sql,false);
    }
    public static Cursor GetBillJeripay(String billNo) {
        String sql = "SELECT status,acquirer,acquirer_payment_id,amount,IsCardPayment FROM BillJeripay WHERE BillNo = '"+billNo+"' ";
        return DBFunc.Query(sql,false);
    }

    public static void RunFour() {
        String updatesql = "UPDATE Sales SET IsZ = 'Z' " +
                "where STATUS = 'REFUND' " ;
        Log.i("__sql", updatesql);
        long sitmdt =  0;
        DBFunc.ExecQuery(updatesql,false);
    }

    public static void RunFifth() {
//        String searchDuplicateSql = "SELECT BillNo,ReferenceBillNo FROM Sales WHERE ReferenceBillNo IS NOT NULL";
//        Cursor c = DBFunc.Query(searchDuplicateSql,false);
//        if (c != null) {
//            while (c.moveToNext()){
//                //270
//                //300
//                String billnoexisiting = c.getString(0);
//                String billrefexisiting = c.getString(1);
//                String aa = "SELECT ID FROM Sales WHERE BillNo = '"+billnoexisiting+"'" +
//                        " AND ReferenceBillNo = '"+billrefexisiting+"'";
//                Cursor caa = DBFunc.Query(aa,false);
//                if (caa != null){
//                    while (caa.moveToNext()){
////                        Integer idd = 0;
////                        if (caa.isFirst()) {
////                            idd = caa.getInt(0);
//                        }
//                        if (idd > 0){
                            String dddd = "DELETE FROM Sales WHERE BillNo = '00000300' AND ReferenceBillNo = '00000042'";
                            DBFunc.ExecQuery(dddd,false);
                            String dddd1 = "DELETE FROM Sales WHERE BillNo = '00000301' AND ReferenceBillNo = '00000046'";
                            DBFunc.ExecQuery(dddd1,false);
                            String dddd2 = "DELETE FROM Sales WHERE BillNo = '00000302' AND ReferenceBillNo = '00000047'";
                            DBFunc.ExecQuery(dddd2,false);
                            String dddd3 = "DELETE FROM Sales WHERE BillNo = '00000303' AND ReferenceBillNo = '00000048'";
                            DBFunc.ExecQuery(dddd3,false);
                            String dddd4 = "DELETE FROM Sales WHERE BillNo = '00000304' AND ReferenceBillNo = '00000050'";
                            DBFunc.ExecQuery(dddd4,false);
                            String dddd5 = "DELETE FROM Sales WHERE BillNo = '00000305' AND ReferenceBillNo = '00000153'";
                            DBFunc.ExecQuery(dddd5,false);
                            String dddd6 = "DELETE FROM Sales WHERE BillNo = '00000306' AND ReferenceBillNo = '00000168'";
                            DBFunc.ExecQuery(dddd6,false);
                            String dddd7 = "DELETE FROM Sales WHERE BillNo = '00000307' AND ReferenceBillNo = '00000169'";
                            DBFunc.ExecQuery(dddd7,false);
//                        }
//                    }
//                    caa.close();
//                }
//            }
//            c.close();
//        }
    }

    public static void DeletDiscountEmpty() {
        String sql = "SELECT ID,Name,DiscountType FROM DISC";
        Cursor c = DBFunc.Query(sql,true);
        if ( c != null) {
            while (c.moveToNext()){
                if (c.getString(1) == null || c.getString(1).length() == 0 || c.getString(2).equals("0")) {
                    String deleteSQL = "DELETE FROM DISC WHERE ID = " + c.getInt(0);
                    DBFunc.ExecQuery(deleteSQL, true);
                }
            }
            c.close();
        }
    }

    public static void DeleteBillPaymentDuplicate() {
        String sql = "select BillNo,count(BillNo),idx,count(PaymentID),PaymentID from BillPayment " +
                "Group By BillNo,PaymentID " +
//                "Group By BillNo " +
                "order by idx ASC";
        Cursor c = DBFunc.Query(sql,false);
//        ArrayList<Integer> arrInt = new ArrayList<Integer>();
        if (c != null){
//            arrInt.clear();
            while (c.moveToNext()) {
                Integer countBillNo = c.getInt(1) ;
                Integer countPaymentID = c.getInt(3) ;
                Integer paymentID = c.getInt(4) ;

                if ( countBillNo > 1 && countPaymentID > 1) { // 2 && 2

                    //if (arrInt.size() > 0 && arrInt.contains(paymentID)){
//                    if (arrInt.size() > 1 && arrInt.contains(paymentID)){

                        String deleteSQL = "DELETE FROM BillPayment WHERE idx = " + c.getInt(2);

                        DBFunc.ExecQuery(deleteSQL, false);

//                        arrInt.remove(paymentID);
//                    }else {
//                        arrInt.add(paymentID);
//                    }
                }else {
//                    arrInt.clear();
                }
            }
            c.close();
        }

        String sqlsales = "select count(BillNo),ID from Sales " +
                "Group By BillNo " +
                "order by ID ASC";
        Cursor csales = DBFunc.Query(sqlsales,false);
        if (csales != null){
            while (csales.moveToNext()) {
                if (csales.getInt(0) > 1) {
                    String deleteSQL = "DELETE FROM Sales WHERE ID = " + csales.getInt(1);
                    DBFunc.ExecQuery(deleteSQL, false);
                }
            }
            csales.close();
        }
    }

    public static void DeleteBillPaymentDuplicateDefault() {
        String sql = "select BillNo,count(PaymentID),idx from BillPayment " +
                "Group By BillNo " +
                "order by idx ASC";
        Cursor c = DBFunc.Query(sql,false);
        if (c != null){
            while (c.moveToNext()) {

                String bno = c.getString(0);
                Integer paymentCount = c.getInt(1);
                Integer idx = c.getInt(2);

                if (paymentCount > 1){

                    String deleteSQL = "DELETE FROM BillPayment " +
                            "WHERE idx = " +  idx;
                    Log.i("Billpayment___",deleteSQL);
                    DBFunc.ExecQuery(deleteSQL, false);

                }

            }
            c.close();
        }
    }

    public static void DelteDiscountNameValueZero() {
        String sql = "select TotalBillDisount,ID from Sales " +
                "where DiscountTypeName != ''" +
                "order by ID ASC";
        Cursor c = DBFunc.Query(sql,false);
        if (c != null){
            while (c.moveToNext()) {
                if (c.getDouble(0) == 0.0) {
                    String deleteSQL = "update sales set " +
                            "DiscountID = '0'," +
                            "DiscountName = ''," +
                            "DiscountType = ''," +
                            "DiscountTypeName = ''," +
                            "DiscountValue = '' " +
                            "Where ID = "+c.getInt(1);
                    DBFunc.ExecQuery(deleteSQL, false);
                }
            }
            c.close();
        }
    }

    public static Cursor XZDataReportCancelItemDiscount(String start, String end, ArrayList<Integer> closingPeriodBillIDArr) {
        Cursor c = null;
        String sql = "";
        if (String.valueOf(ReportActivity.previousReport).length() > 4){
            String billnoall = GetBillNoAll();

            String sqll = "select sum(DetailsBillProduct.ProductQty),sum(DetailsBillProduct.ItemDiscountAmount)," +
                    "      Sales.STATUS,sales.BillNo " +
                    "      from DetailsBillProduct " +
                    "      inner join Sales on Sales.BillNo = DetailsBillProduct.BillNo " +
                    "      where (Sales.STATUS = 'SALES'  )" +
                    "      AND strftime('"+Constraints.sqldateformat+"', Sales.DateTime / 1000, 'unixepoch')  " +
                    "      BETWEEN '" + ReportActivity.start + "' and '" + ReportActivity.end + "' " +
                    "      and DetailsBillProduct.DiscountID > 0  ";
                    //"      and DetailsBillProduct.DiscountID > 0 AND DetailsBillProduct.CategoryID > 0 ";

            sqll += " AND Sales.BillID IN ("+billnoall+") " ;

            sqll += "  Group By Sales.STATUS";

            Log.i("SQLLL___", "sqllsqlll___" + sqll);

            c = DBFunc.Query(sqll, false);
        }else {
            String sqllll = "select BillNo from DetailsBillProduct " +
                    "where CategoryId IS NOT NULL and CategoryId > 0 ";
            Cursor ccc = DBFunc.Query(sqllll, false);
            if (ccc != null) {
                if (ccc.getCount() > 0) {
                    Log.i("Hello__", "____" + ReportActivity.previous_report_shift_name);
                    String sqll = "select sum(DetailsBillProduct.ProductQty),sum(DetailsBillProduct.ItemDiscountAmount)," +
                            "      Sales.STATUS,sales.BillNo " +
                            "      from DetailsBillProduct " +
                            "      inner join Sales on Sales.BillNo = DetailsBillProduct.BillNo " +
                            "      where (Sales.STATUS = 'SALES'  )" +
                            "      AND strftime('"+Constraints.sqldateformat+"', Sales.DateTime / 1000, 'unixepoch')  " +
                            "      BETWEEN '" + ReportActivity.start + "' and '" + ReportActivity.end + "' " +
                            "      and DetailsBillProduct.DiscountID > 0  ";
                            //"      and DetailsBillProduct.DiscountID > 0 AND DetailsBillProduct.CategoryID > 0 ";

                    if (ReportActivity.previous_report_shift_name.equals("Now")) {
                        sqll += " AND Sales.IsZ IS NULL ";
                        Log.i("sql", sqll);
                    }

                    sqll += "      Group By Sales.STATUS";
                    Log.i("SQLLL___", "sqllsqlll___" + sqll);
                    c = DBFunc.Query(sqll, false);
                }
                ccc.close();
            } else {
                c = null;
            }
        }
        return c;
    }

    public static Cursor XZDataReportCancelTotalBillDiscount(String start, String end, ArrayList<Integer> closingPeriodBillIDArr) {
        Cursor c = null;
        String sqllll = "select BillNo from DetailsBillProduct " +
                "where CategoryId IS NOT NULL and CategoryId > 0 ";
        Cursor ccc = DBFunc.Query(sqllll,false);
        if (ccc != null){
            if(ccc.getCount() > 0){
//
//                String sqll = "select sum(TotalQty),sum(TotalBillDisount),BillNo from Sales " +
//                        "   where (STATUS = 'SALES') AND strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch') " +
//                        "   BETWEEN '" + ReportActivity.start + "' and '" + ReportActivity.end + "'" +
//                        "   and DiscountID > 0 AND DetailsBillProduct.CategoryID > 0 " ;
//
//                if (ReportActivity.previous_report_shift_name.equals("Now")){
//                    sqll += " AND IsZ IS NULL " ;
//                    Log.i("sql",sqll);
//                }

//                String sqll = "select sum(Sales.TotalQty),sum(Sales.TotalBillDisount),Sales.BillNo from Sales " +
//                        "left join DetailsBillProduct on DetailsBillProduct.BillNo = Sales.BillNo " +
//                        "   where (Sales.STATUS = 'SALES') AND strftime('"+Constraints.sqldateformat+"', Sales.DateTime / 1000, 'unixepoch') " +
//                        "   BETWEEN '" + ReportActivity.start + "' and '" + ReportActivity.end + "'" +
//                        "   and Sales.DiscountID > 0 AND DetailsBillProduct.CategoryID > 0 " ;
                String sqll = "select (Sales.TotalQty),(Sales.TotalBillDisount),Sales.BillNo from Sales " +
                        "left join DetailsBillProduct on DetailsBillProduct.BillNo = Sales.BillNo " +
                        "   where (Sales.STATUS = 'SALES') AND strftime('"+Constraints.sqldateformat+"', Sales.DateTime / 1000, 'unixepoch') " +
                        "   BETWEEN '" + ReportActivity.start + "' and '" + ReportActivity.end + "'" +
                        "   and Sales.DiscountID > 0  " ;
                        //"   and Sales.DiscountID > 0 AND DetailsBillProduct.CategoryID > 0 " ;

                if (ReportActivity.previous_report_shift_name.equals("Now")){
                    sqll += " AND Sales.IsZ IS NULL " ;
                    Log.i("sql",sqll);
                }

                sqll += " Group By Sales.BillNo";
                Log.i("SQLLL___","sqllsqlll___"+sqll);
                c = DBFunc.Query(sqll, false);
            }
            ccc.close();
        }else {
             c = null;
        }
        return c;
    }

//    public static void BillListCreateForNotHaveInBillist() {
//
////        String BillID = "555";
////        String BillNo = "00000555";
////        String STATUS = "REFUND";
////        String TotalItems = "2";
////        String Date = "22 Jan 2021  08:02 PM";
////        String TotalAmount = "19.8";
////        long DateTime = 1611316943095L;
//        String sqqq = "Select BillNo From BillList WHERE BillNo = '00000555'";
//        Log.i("SDFDFDSFD","sqqq____"+sqqq);
//        Cursor cc  = DBFunc.Query(sqqq,false);
//        if (cc != null) {
//
//                if (cc.getCount() == 0) {
//                    String sql = "INSERT INTO BillList (BillID,BillNo,STATUS,TotalItems," +
//                            "Date,OnlineOrderBill,TotalAmount,TableNo,IsZ,IsClosed,QueueNo,DateTime) " +
//                            "VALUES ('" + "555" + "'," +
//                            "'" + "00000555" + "','" +
//                            "REFUND" + "','" +
//                            "2" + "','" +
//                            "22 Jan 2021  08:02 PM" + "','" +
//                            "OFF" + "','" +
//                            "19.8" + "'," +
//                            "'0, '," +
//                            "'Z'," +
//                            "'Closed'," +
//                            "'0'," +
//                            1611316943095L + ")";
//
//                    DBFunc.ExecQuery(sql, false);
//             }
//            cc.close();
//        }
//
//         sqqq = "Select BillNo From Bill WHERE BillNo = "+555;
//        Log.i("SDFDFDSFD","sqqq____"+sqqq);
//        cc  = DBFunc.Query(sqqq,false);
//        if (cc != null) {
//
//                if (cc.getCount() == 0) {
//
//                    String sql = "INSERT INTO Bill (OpenDateTime,BillNo,CloseDateTime,TransNo, " +
//                            "OnlineOrderBill) VALUES " +
//                            "(" + 1611316943095L + ",555," +
//                            1611316943095L +
//                            ",'00000555'," +
//                            "'" + "OFF" + "')";
//                    Log.i("DFdf___", "DQL" + sql);
//                    DBFunc.ExecQuery(sql, false);
//
//                }
//
//            cc.close();
//        }
//
//        sqqq = "Select BillNo From DetailsBillProduct WHERE BillNo = '00000555'";
//        Log.i("SDFDFDSFD","sqqq____"+sqqq);
//        cc  = DBFunc.Query(sqqq,false);
//        if (cc != null) {
//
//                if (cc.getCount() == 0) {
//                    String sql = "INSERT INTO DetailsBillProduct (BillNo,OnlineOrderBill,ProductQty,ProductName,ProductPrice,BillDateTime," +
//                            "ProductID,Cancel," +
//                            "BillID,AfterDiscountAmount,OpenPriceStatus,BeforeOpenPrice,DateTime) VALUES (";
//                    sql += " '00000555', ";
//                    sql += "'" + "OFF" + "', ";
//                    sql += "'" + "13" + "', ";
//                    sql += "'" + "9.90" + "', ";
//                    sql += "'" + String.format("%.2f", Double.valueOf("9.90")) + "', ";
//                    sql += "'" + "22 Jan 2021  08:02 PM" + "', ";
//                    sql += "'" + "13" + "', ";
//                    sql += "'" + "REFUND" + "', ";
//                    sql += "'" + "555" + "', ";
//                    sql += "'" + String.format("%.2f", Double.valueOf("9.90")) + "', ";
//                    sql += "'" + "0" + "', ";
//                    ;
//                    sql += "'" + String.format("%.2f", Double.valueOf("0.00")) + "', ";
//                    ;
//                    sql += 1611316943095L + ")";
//                    Log.i("DFdf___", "DQL" + sql);
//
//                    DBFunc.ExecQuery(sql, false);
//
//                    DBFunc.ExecQuery(sql, false);
//                }
//
//            cc.close();
//        }
//
//
//        sqqq = "Select BillNo From SalesItem WHERE BillNo = '00000555'";
//        Log.i("SDFDFDSFD","sqqq____"+sqqq);
//        cc  = DBFunc.Query(sqqq,false);
//        if (cc != null) {
//
//                if (cc.getCount() == 0) {
//
//
//                    String sql = "INSERT INTO SalesItem (SalesID,BillNo,PluId,PluName,CategoryId,CategoryName,Qty,Price,DateTime) VALUES (";
//                    sql += 532 + ", ";
//                    sql += "'00000555', ";
//                    sql += "'" + DBFunc.PurifyString(String.valueOf("13")) + "', ";
//                    sql += "'" + DBFunc.PurifyString(String.valueOf("9.90")) + "', ";
//                    sql += "'" + DBFunc.PurifyString(String.valueOf("0")) + "', ";
//                    sql += "'" + DBFunc.PurifyString(String.valueOf("0")) + "', ";
//                    sql += "'" + DBFunc.PurifyString(String.valueOf("2")) + "', ";
//                    sql += "'" + DBFunc.PurifyString(String.valueOf("9.90")) + "', ";
//                    sql += 1611316943095L + ")";
//                    Log.i("DFdf___", "DQL" + sql);
//                    DBFunc.ExecQuery(sql, false);
//                }
//
//            cc.close();
//        }
//
//    }

//    public static void RunHangeProblem() {
//        String BillNo = "";
//        String ProductQty = "";
//        String ProductName = "";
//        String vchQueueNo = "";
//        String intTableNo = "";
//        String ProductID = "";
//        String CategoryID = "";
//        String CategoryName = "";
//        String ProductPrice = "";
//        String BillDetailsID = "";
//        String OnlineOrderBill = "";
//        String Cancel = "";
//        String ItemDiscountAmount = "";
//        String BillDateTime = "";
//        String BillID = "";
//        String TaxID = "";
//        String TaxType = "";
//        String TaxAmount = "";
//        String TaxName = "";
//        String DiscountID = "";
//        String DiscountName = "";
//        String DiscountType = "";
//        String DiscountTypeName = "";
//        String DiscountValue = "";
//        String AfterDiscountAmount = "";
//        String OpenPriceStatus = "";
//        String BeforeOpenPrice = "";
//        Integer DateTime = 0;
//        String uniqueId = "";
//        String query = "SELECT BillNo,SUM(ProductQty),ProductName,vchQueueNo,intTableNo,ProductID,CategoryID,CategoryName,\n" +
//                "            ProductPrice,BillDetailsID,OnlineOrderBill,Cancel,ItemDiscountAmount,BillDateTime,BillID,\n" +
//                "            TaxID,TaxType,TaxAmount,TaxName,DiscountID,DiscountName,DiscountType,DiscountTypeName,DiscountValue\n" +
//                "        ,AfterDiscountAmount,OpenPriceStatus,BeforeOpenPrice,DateTime " +
//                "FROM Details_BillProduct " +
//                "group by BillNo,ProductID,OpenPriceStatus";
//
//        Cursor c = DBFunc.Query(query,false);
//
//        if (c != null){
//            while (c.moveToNext()) {
//                 uniqueId  = UUID.randomUUID().toString();
//                 BillNo = c.getString(0);
//                 ProductQty = c.getString(1);
//                 ProductName = c.getString(2);
//                 vchQueueNo = c.getString(3);
//                 intTableNo = c.getString(4);
//                 ProductID = c.getString(5);
//                 CategoryID = c.getString(6);
//                CategoryName = c.getString(7);
//                ProductPrice = c.getString(8);
//                BillDetailsID = c.getString(9);
//                OnlineOrderBill = c.getString(10);
//                Cancel = c.getString(11);
//                ItemDiscountAmount = c.getString(12);
//                BillDateTime = c.getString(13);
//                BillID = c.getString(14);
//                TaxID = c.getString(15);
//                TaxType = c.getString(16);
//                TaxAmount = c.getString(0);
//                TaxName = c.getString(17);
//                DiscountID = c.getString(18);
//                DiscountName = c.getString(19);
//                DiscountType = c.getString(20);
//                DiscountTypeName = c.getString(21);
//                DiscountValue = c.getString(22);
//                AfterDiscountAmount = c.getString(23);
//                OpenPriceStatus = c.getString(24);
//                BeforeOpenPrice = c.getString(25);
//                DateTime = c.getInt(26);
//
//                String sql = "INSERT INTO DetailsBillProduct (UUID,BillNo,ProductQty,ProductName,vchQueueNo,intTableNo,ProductID,CategoryID,CategoryName, " +
//                        " ProductPrice,BillDetailsID,OnlineOrderBill,Cancel,ItemDiscountAmount,BillDateTime,BillID, " +
//                        " TaxID,TaxType,TaxAmount,TaxName,DiscountID,DiscountName,DiscountType,DiscountTypeName,DiscountValue " +
//                        "  ,AfterDiscountAmount,OpenPriceStatus,BeforeOpenPrice,DateTime) VALUES (";
//                sql += "'" + uniqueId + "', ";
//                sql += "'" + BillNo + "', ";
//                sql += "'" + ProductQty + "', ";
//                sql += "'" + ProductName + "', ";
//                sql += "'" + vchQueueNo + "', ";
//                sql += "'" + intTableNo + "', ";
//                sql += "'" + ProductID + "', ";
//                sql += "'" + CategoryID + "', ";
//                sql += "'" + CategoryName + "', ";
//                sql += "'" + ProductPrice + "', ";
//                sql += "'" + BillDetailsID + "', ";
//                sql += "'" + OnlineOrderBill + "', ";
//                sql += "'" + Cancel + "', ";
//                sql += "'" + ItemDiscountAmount + "', ";
//                sql += "'" + BillDateTime + "', ";
//                sql += "'" + BillID + "', ";
//                sql += "'" + TaxID + "', ";
//                sql += "'" + TaxType + "', ";
//                sql += "'" + TaxAmount + "', ";
//                sql += "'" + TaxName + "', ";
//                sql += "'" + DiscountID + "', ";
//                sql += "'" + DiscountName + "', ";
//                sql += "'" + DiscountType + "', ";
//                sql += "'" + DiscountTypeName + "', ";
//                sql += "'" + DiscountValue + "', ";
//                sql += "'" + AfterDiscountAmount + "', ";
//                sql += "'" + OpenPriceStatus + "', ";
//                sql += "'" + BeforeOpenPrice + "', ";
//                sql += DateTime + ")";
//
//                DBFunc.ExecQuery(sql, false);
//
//            }
//            c.close();
//        }
//
//
//    }

    public static Integer GetQtyByBillNoAndProductId(String billNo,Integer productID) {
        Integer qty = 0;
        String sss = "SELECT SUM(ProductQty) FROM DetailsBillProduct " +
                " WHERE BillNo = '"+billNo+"' " +
                " AND ProductID = '"+productID+"' " +
                " GROUP BY ProductID" ;
        Log.i("DFFD___","sss_"+sss);
        Cursor csss = DBFunc.Query(sss,false);
        if (csss != null) {
            while (csss.moveToNext()){
                qty = csss.getInt(0);
            }
            csss.close();
        }
        if (qty != null && qty > 0){
            qty = qty + 1;
        }else {
            qty = 1;
        }
        return qty;
    }

    public static void RunThree() {
        String queray = "Select TotalQty,TotalNettSales,SalesDateTime,STATUS,BillNo from Sales ";
        Cursor ccc = DBFunc.Query(queray,false);
        Double ddPrice = 0.0;
        Integer ddQty = 0;
        String ddDt = "";
        String ddstatus = "";
        String ddbno = "";
        if (ccc != null) {
            while (ccc.moveToNext()){
                ddQty = ccc.getInt(0);
                ddPrice = ccc.getDouble(1);
                ddDt = ccc.getString(2);
                ddstatus = ccc.getString(3);
                ddbno = ccc.getString(4);
            }
            ccc.close();
        }
        String query = "UPDATE BillList SET ";
        query += "Date = '"+ddDt+"', ";
        query += "TotalAmount = '"+ddPrice+"', ";
        query += "TotalItems = '"+ddQty+"', ";
        query += "STATUS = '"+ddstatus+"' ";
        query += "WHERE BillNo = '"+ddbno+"' ";

        DBFunc.ExecQuery(query,false);
    }

    public static void SaveEwallet(JSONObject res_data,String BillNo) {
        String uuid = "";
        String status = "";
        String acquirer = "";
        String acquirer_id = "";
        String acquirer_payment_id = "";
        String amount = "";
        String response_detail = "";
        String isCardPayment = "";
        String merchant_id = "";
        String terminal_id = "";
        String invoice_number = "";
        try {

//            {"additional_printing_flag":"",
//                    "approval_code":"154738",
//                    "batch_number":"000659",
//                    "card_holder_name":"TAN SOO KWEI              ",
//                    "card_label":"VISA","card_number":"XXXXXXXXXXXX9310",
//                    "card_type":"V","command_identifier":"",
//                    "coupons_vouchers":"",
//                    "custom_data_2":"",
//                    "custom_data_3":"",
//                    "date_time":"20210226154738",
//                    "destination_package_name":"",
//                    "ecr_unique_trace_number":"",
//                    "employee_id":"","emv_data":"0080008000E800A0000000031010  VISA CREDIT                     ",
//                    "entry_mode":"C","expiry_date":"XXXX","external_device_invoice":"",
//                    "host_label":"BOC","host_type":"B","invoice_number":"000008",
//                    "merchant_id":"104767011000016","original_trans_type":"000009",
//                    "response_code":"00","retrieval_reference_number":"210226154738",
//                    "source_package_name":"","terminal_id":"76002460","transaction_amount":"000000049800",
//                    "transaction_info":"","transaction_type":"R200"}
            uuid = res_data.getString("uuid");
            status = res_data.getString("status");
            acquirer = res_data.getString("acquirer");
            acquirer_id = res_data.getString("acquirer_id");
            acquirer_payment_id = res_data.getString("acquirer_payment_id");
            amount = res_data.getString("amount");
            response_detail = res_data.getString("details");
            String card_label = "";
            String card_number = "";
            if (status.toUpperCase().equals("SUCCESS")) {

//                                _{"uuid":"b8cc85ac65f94bbe8ac46cab5a3e7ec2","status":"SUCCESS","acquirer":"GrabPay",
//                                    "acquirer_id":"GRAB_PAY","acquirer_payment_id":"cdcaad384faa48ac9e084dc4f7b2646a","amount":0.1,"details":""}

                if (response_detail != null && response_detail.length() > 3) {
                    JSONObject response_detailOBj = new JSONObject(response_detail);
                    card_label = response_detailOBj.getString("card_label");
                    card_number = response_detailOBj.getString("card_number");
                    if (response_detail.length() > 10) {
                        merchant_id = response_detailOBj.getString("merchant_id");
                        terminal_id = response_detailOBj.getString("terminal_id");
                        invoice_number = response_detailOBj.getString("invoice_number");
                        isCardPayment = "1";
                    } else {
                        isCardPayment = "0";
                    }
                }
                if (acquirer.toUpperCase().equals("GrabPay".toUpperCase())) {
                    //"OCBC~VISA"
                    card_label = acquirer_id + "~" + acquirer;
                }

//                                Integer ID, String billNo, String card_label, String card_number, String merchant_id, String terminal_id, String invoice_number, Integer dt
                BillJeripayDetails jeripaydetail = new BillJeripayDetails(0, BillNo, card_label, card_number, merchant_id, terminal_id, invoice_number, 0);
                Query.SaveBillJeripayDetails(jeripaydetail);

//                                Integer ID, String billNo, String uuid, String status, String acquirer, String acquirer_id,
//                                String acquirer_payment_id, String amount, String billjeripaydetailsID,
//                                String isCardPayment, String dt, Integer dtTime
                Integer billjeripaydetailsID = Query.findLatestID("ID", "BillJeripayDetails", false);
                BillJeripay bill_jeripay = new BillJeripay(0, BillNo, uuid, status, acquirer, acquirer_id, acquirer_payment_id, amount,
                        String.valueOf(billjeripaydetailsID), isCardPayment, Query.GetDateFormart55(), 0);
                Query.SaveBillJeripay(bill_jeripay);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void saveBillBOC(BillBOC billBOC) {

//        Integer ID, String billNo, String uuid, String STATUS, String additional_printing_flag,
//                String approval_code, String batch_number, String card_holder_name, String card_label,
//                String card_number, String card_type, String command_identifier, String coupons_vouchers,
//                String custom_data_, String custom_data_3, String date_time, String destination_package_name,
//                String ecr_unique_trace_number, String employee_id, String emv_data, String entry_mode,
//                String expiry_date, String external_device_invoice, String host_label, String host_type,
//                String invoice_number, String merchant_id, String original_trans_type, String response_code,
//                String retrieval_reference_number, String source_package_name, String terminal_id,
//                String transaction_amount, String transaction_info, String transaction_type, Integer dateTime

        String saveBillBOC = "INSERT INTO BillBOC (billNo,UUID,STATUS,additional_printing_flag,approval_code," +
                "batch_number,card_holder_name,card_label,card_number,card_type,command_identifier," +
                "coupons_vouchers,custom_data_2,custom_data_3,date_time,destination_package_name," +
                "ecr_unique_trace_number,employee_id,emv_data,entry_mode,expiry_date," +
                "external_device_invoice,host_label,host_type,invoice_number,external_device_invoice," +
                "host_label,host_type,invoice_number,merchant_id,original_trans_type," +
                "response_code,retrieval_reference_number,source_package_name,terminal_id,transaction_amount," +
                "transaction_info,transaction_type," +
                "DateTime) " +
                "VALUES ('" + DBFunc.PurifyString(billBOC.getBillNo()) + "'," +
                "'" + DBFunc.PurifyString(billBOC.getUuid()) + "'," +
                "'" + DBFunc.PurifyString(billBOC.getSTATUS()) + "'," +
                "'" + DBFunc.PurifyString(billBOC.getAdditional_printing_flag()) + "'," +
                "'" + DBFunc.PurifyString(billBOC.getApproval_code()) + "'," +
                "'" + DBFunc.PurifyString(billBOC.getBatch_number()) + "'," +
                "'" + DBFunc.PurifyString(billBOC.getCard_holder_name()) + "'," +
                "'" + DBFunc.PurifyString(billBOC.getCard_label()) + "'," +
                "'" + DBFunc.PurifyString(billBOC.getCard_number()) + "'," +
                "'" + DBFunc.PurifyString(billBOC.getCard_type()) + "'," +
                "'" + DBFunc.PurifyString(billBOC.getCommand_identifier()) + "'," +
                "'" + DBFunc.PurifyString(billBOC.getCoupons_vouchers()) + "'," +
                "'" + DBFunc.PurifyString(billBOC.getCustom_data_()) + "'," +
                "'" + DBFunc.PurifyString(billBOC.getCustom_data_3()) + "'," +
                "'" + DBFunc.PurifyString(billBOC.getDate_time()) + "'," +
                "'" + DBFunc.PurifyString(billBOC.getDestination_package_name()) + "'," +
                "'" + DBFunc.PurifyString(billBOC.getEcr_unique_trace_number()) + "'," +
                "'" + DBFunc.PurifyString(billBOC.getEmployee_id()) + "'," +
                "'" + DBFunc.PurifyString(billBOC.getEmv_data()) + "'," +
                "'" + DBFunc.PurifyString(billBOC.getEntry_mode()) + "'," +
                "'" + DBFunc.PurifyString(billBOC.getExpiry_date()) + "'," +
                "'" + DBFunc.PurifyString(billBOC.getExternal_device_invoice()) + "'," +
                "'" + DBFunc.PurifyString(billBOC.getHost_label()) + "'," +
                "'" + DBFunc.PurifyString(billBOC.getHost_type()) + "'," +
                "'" + DBFunc.PurifyString(billBOC.getInvoice_number()) + "'," +
                "'" + DBFunc.PurifyString(billBOC.getMerchant_id()) + "'," +
                "'" + DBFunc.PurifyString(billBOC.getOriginal_trans_type()) + "'," +
                "'" + DBFunc.PurifyString(billBOC.getHost_type()) + "'," +
                "'" + DBFunc.PurifyString(billBOC.getInvoice_number()) + "'," +
                "'" + DBFunc.PurifyString(billBOC.getMerchant_id()) + "'," +
                "'" + DBFunc.PurifyString(billBOC.getOriginal_trans_type()) + "'," +
                "'" + DBFunc.PurifyString(billBOC.getResponse_code()) + "'," +
                "'" + DBFunc.PurifyString(billBOC.getRetrieval_reference_number()) + "'," +
                "'" + DBFunc.PurifyString(billBOC.getSource_package_name()) + "'," +
                "'" + DBFunc.PurifyString(billBOC.getTerminal_id()) + "'," +
                "'" + DBFunc.PurifyString(billBOC.getTransaction_amount()) + "'," +
                "'" + DBFunc.PurifyString(billBOC.getTransaction_info()) + "'," +
                "'" + DBFunc.PurifyString(billBOC.getTransaction_type()) + "'," +
                System.currentTimeMillis() + ")";

        Log.i("SAVE___","saveBillBOC____"+saveBillBOC);
        DBFunc.ExecQuery(saveBillBOC,false);
    }

    public static Cursor getBillBOC(String billNo){
//        String card_label = jeripayDetails.getString(0);
//        String merchant_id = jeripayDetails.getString(3);
//        String terminal_id = jeripayDetails.getString(4);
//        String card_number = jeripayDetails.getString(1);
//        String invoice_number = jeripayDetails.getString(2);
        Cursor c = null;
        String sql = "";
        if (billNo != null && billNo.length() > 2) {
            //0000008000E800A0000000041010  MasterCard
            sql = "SELECT entry_mode,merchant_id,terminal_id,card_number,invoice_number,host_label,card_label From BillBOC WHERE BillNo = '"+billNo+"'";
        }else {
            sql = "SELECT entry_mode,merchant_id,terminal_id,card_number,invoice_number,host_label,card_label From BillBOC";
        }
        c = DBFunc.Query(sql, false);
        return c;
    }

    public static void ChkOnlineOrderAndUpdateCloseDtBill(String billNo, String tbl_name) {
        String chkOnlineOrderBill = "SELECT OnlineOrderBill FROM Bill WHERE TransNo = '"+billNo+"'";
        Cursor CursorchkOnlineOrderBill = DBFunc.Query(chkOnlineOrderBill,false);
        String chkOOB = "";
        if (CursorchkOnlineOrderBill != null) {
            if (CursorchkOnlineOrderBill.moveToNext()){
                chkOOB = CursorchkOnlineOrderBill.getString(0);
            }
            CursorchkOnlineOrderBill.close();
        }

        Log.i("Billtype___","Billtype__a"+billNo+"__"+chkOOB);
        if (chkOOB.equals("OFF")) { // OFF for Bill / ON for Online Bill // B for Bill / OB for Online Bill

            //BillTypeFunction(BillNo,CheckOutActivity.tbl_name);
//            CashLayoutActivity.BillTypeFunction(billNo,CheckOutActivity.tbl_name);
            CashLayoutActivity.BillTypeFunction(billNo,tbl_name);

        }
    }

    public static String GetImageURLForPLU(String uuid) {
        String url_name = "";
        String searchUUId = "SELECT ImageUrl,ImageFileName,ImageType FROM PLU WHERE UUID = '"+uuid+"'";

        Cursor c = DBFunc.Query(searchUUId,true);
        if (c != null) {
            if (c.moveToNext()) {
                Log.i("DTET","DFDFD___"+c.getString(0) + "___" + c.getString(0).length());
//                if (c.getString(0) != null && c.getString(0).length() > 2) {
                if (c.getString(0) != null && c.getString(0).length() > 5) {
                    url_name = "http://" + c.getString(0) + c.getString(1) + "." + c.getString(2);
                }
            }
            c.close();
        }
        Log.i("DTET","DFDFD___url_name"+url_name);
        return url_name;
    }

    public static void SaveDeviceData(DeviceData deviceData) {
        String delete = "DELETE FROM DeviceData";
        DBFunc.ExecQuery(delete,true);
        String checkDeviceData = "SELECT ID FROM DeviceData ";
        Cursor c = DBFunc.Query(checkDeviceData,true);
        if (c != null){
            if (c.getCount() == 0){
                String saveDeviceData = "INSERT INTO DeviceData (MODEL,BOARD,BRAND,DEVICE,DISPLAY,PRODUCT,TERMINAL_TYPE,LICENSE_KEY) " +
                        "VALUES ('" + DBFunc.PurifyString(deviceData.getMODEL()) + "'," +
                        "'" + DBFunc.PurifyString(deviceData.getBOARD()) + "'," +
                        "'" + DBFunc.PurifyString(deviceData.getBRAND()) + "'," +
                        "'" + DBFunc.PurifyString(deviceData.getDEVICE()) + "'," +
                        "'" + DBFunc.PurifyString(deviceData.getDISPLAY()) + "'," +
                        "'" + DBFunc.PurifyString(deviceData.getPRODUCT()) + "',"+
                        "'" + DBFunc.PurifyString(deviceData.getTERMINAL_TYPE()) + "',"+
                        "'" + DBFunc.PurifyString(deviceData.getLicenseKey()) + "')";
                Log.i("saveDeviceData___","saveDeviceData___"+saveDeviceData);
                DBFunc.ExecQuery(saveDeviceData,true);
            }
            c.close();
        }
    }
    public static String GetDeviceData(String chkStatus) {
        String retVal = "0";
        String checkDeviceData = "SELECT MODEL,BOARD,BRAND,DEVICE,DISPLAY,PRODUCT,TERMINAL_TYPE FROM DeviceData ";
        Cursor c = DBFunc.Query(checkDeviceData,true);
        if (c != null){
            if (c.moveToNext()){
                String model = c.getString(0);
                String board = c.getString(1);
                String brand = c.getString(2);
                String device = c.getString(3);
                String display = c.getString(4);
                String product = c.getString(5);
                String terminal_type = c.getString(6);
                if (chkStatus.equals(Constraints.MODEL)){
                    retVal = model;
                } else if (chkStatus.equals(Constraints.BOARD)){
                    retVal = board;
                } else if (chkStatus.equals(Constraints.BRAND)){
                    retVal = brand;
                } else if (chkStatus.equals(Constraints.DEVICE)){
                    retVal = device;
                } else if (chkStatus.equals(Constraints.DISPLAY)){
                    retVal = display;
                } else if (chkStatus.equals(Constraints.PRODUCT)){
                    retVal = product;
                } else if (chkStatus.equals(Constraints.TERMINAL_TYPE)){
                    retVal = terminal_type.toUpperCase();
                }
            }
            c.close();
        }
        return retVal;
    }

    public static Cursor getStaffData() {
        String sql = " SELECT name FROM Staff";
        Cursor c = DBFunc.Query(sql,true);
        if (c != null) {
            if (c.getCount() == 0) {
                Query.saveStaff("Admin","1111","0","1","ADMIN");

            }
            c.close();
        }

        sql = "SELECT name,pin,commission,permission_group_id," +
                "permission_group_name,DateTime " +
                "FROM Staff";

        return DBFunc.Query(sql,true);
    }

    public static void RegisterForIngenico(Context context) {
        String terminalTypeVal = Query.GetDeviceData(Constraints.TERMINAL_TYPE);
        if (terminalTypeVal.equals(Constraints.INGENICO)) {

            DaggerIngenicoComponent.builder()
                    .ingenicoModule(new IngenicoModule(context))
                    .build();
            register(context,true);
        }
    }


    private static void register(Context c,boolean useEpayModule) {
        try {
            DeviceHelper.me().register(useEpayModule);
//            registerEnabled(false);
        } catch (IllegalStateException e) {
            toast(c,"register fail: " + e.getMessage());
        }
        Log.i("DEVICE_CHECKED","TAGS_"+"Register_status");
    }

    public static void toast(Context c,String message) {
        Toast.makeText(c, message, Toast.LENGTH_SHORT).show();
    }

    public static List<DiscountClass> getDiscountAll(String status) {
        List<DiscountClass> discounts = new ArrayList<DiscountClass>();;
        String query = " ";
        if (status.equals("DiscountAmount")){
            query += "Where DiscountType = '$ Dollar Value' ";
        } else if (status.equals("DiscountPercentage")) {
            query += "Where DiscountType = '% Percentage' ";
        } else if (status.equals("DiscountAll")) {
            query += " ";
        }
        String sql = "SELECT ID, Name, Value, Option, Seq, DiscountType , Type, OpenDiscountStatus FROM Disc " +
                    query + " ORDER BY Seq";

        Cursor c = DBFunc.Query(sql, true);
        if(c!=null){
            discounts.clear();
            while(c.moveToNext()){
                discounts.add(new DiscountClass(c.getInt(0),c.getString(1),
                        c.getString(2),c.getString(3),c.getInt(4),
                        c.getString(5),c.getString(6),
                        c.getString(7)));
            }
            c.close();
        }
        return discounts;
    }

    public static void SavePLUModi(String uuid,Integer itemID,String itemName,String modiName,String modiPrice,Integer qty,
                                   String openPrice,String remarks,String billNo,
                                   String billid,String billDetailsPID) {
        String sql = "INSERT INTO PLUModi (PLU_UUID,ItemID,ItemName,ModiName,ModiPrice,Qty,OpenPriceStatus," +
                "Remarks,DetailsBillProductID,BillNo,BillID) VALUES (";
        sql += "'" + uuid + "', ";
        sql += "'" + itemID+ "', ";
        sql += "'"+itemName+"',";
        sql += "'" + modiName + "',";
        sql += "'" + modiPrice + "',";
        sql += "'" + qty + "',";
        sql += "'" + openPrice + "',";
        sql += "'" + remarks + "',";
        sql += "'" + billDetailsPID + "',";
        sql += "'" + billNo + "',";
        sql += "'"+billid+"')";

        DBFunc.ExecQuery(sql, false);

        Log.i("Sdfsdfds___","sdfsdfsd___"+sql);

        DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                System.currentTimeMillis(), DBFunc.PurifyString("Saved-Query -PLUModi-" + sql));


       Query.updatePriceDetailsBillProduct(uuid,billNo,openPrice,"add",0.0,"0");
    }

    public static void updatePriceDetailsBillProduct(String uuid, String billNo, String openPrice,String status,
                                                     Double priceofupdate,String detailsBillID) {
        String sqlModiPrice = " SELECT SUM(ModiPrice) " +
                "FROM PLUModi " +
                "Where PLU_UUID = '"+ uuid +"' " +
                "and BillNo = '"+ billNo +"' " +
                "and OpenPriceStatus = '"+openPrice+"' " ;

        Cursor cModiPrice = DBFunc.Query(sqlModiPrice, false);
        double modiPriceCal = 0.0;
        if (cModiPrice != null) {
            if (cModiPrice.moveToNext()) {
                modiPriceCal = cModiPrice.getDouble(0);
            }
            cModiPrice.close();
        }


        String productUUID = TransactionDetailsActivity.ValidAndGetValue("ID", "PLU", "UUID", uuid, true);

        String billDettailsPrice = " SELECT SUM(ProductPrice),SUM(ProductQty) " +
                "FROM DetailsBillProduct " +
                "WHERE ID = " + detailsBillID;
//                "WHERE BillNo = '" + billNo + "'  AND ProductId = '"+productUUID+"' " +
//                "AND OpenPriceStatus = '"+openPrice+"' " ;

        double cbillDettailsPriceCal = 0.0;
        Cursor cbillDettailsPrice = DBFunc.Query(billDettailsPrice,false);
        if (cbillDettailsPrice != null) {
            if (cbillDettailsPrice.moveToNext()) {
                cbillDettailsPriceCal = cbillDettailsPrice.getDouble(0);
                modiPriceCal = modiPriceCal * cbillDettailsPrice.getDouble(1);
            }
            cModiPrice.close();
        }
        double val = 0.0;
        if (status.equals("add")) {
            if (modiPriceCal > 0.0 || modiPriceCal == 0.0) {
                val = cbillDettailsPriceCal + modiPriceCal;
            }
        } else {
            val = priceofupdate + modiPriceCal;
        }


        try {
            String update =  "UPDATE DetailsBillProduct SET " +
                    "ProductPrice =  '" + String.format("%.2f", val) + "' " +
                    "WHERE ID = " + detailsBillID + " " +
                            " AND BillNo = '" + billNo + "'  AND ProductId = '"+productUUID+"' " +
                            "AND OpenPriceStatus = '"+openPrice+"' " ;


            DBFunc.ExecQuery(update,false);

            Log.i("dsfsd____","sf_update__"+update);

            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                    System.currentTimeMillis(), DBFunc.PurifyString("Updated-" +   update));

        }catch (Exception e){
            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                    System.currentTimeMillis(), DBFunc.PurifyString("Error-Query-updatePriceDetailsBillProduct- " +   e.getMessage()));
        }

       // EditProductSheetFragment.ID = "0";
    }

    public static Integer GetLineSpaceCount(String val,Integer count,Integer pluscount) {
        Integer linespacecount = count;
        if (val.toUpperCase().equals(Constraints.IMIN)){
            linespacecount = count + pluscount;
        }
        return linespacecount;
    }

//    public static void PrintingValueSetForIMIN(IminPrintUtils mIminPrintUtils, String colTextFirst, String colTextSecond, String colTextThird,
//                                               Integer colWidthFirst, Integer colWidthSecond, Integer colWidthThird,
//                                               Integer colAlignFirst, Integer colAlignSecond, Integer colAlignThird,
//                                               Integer sizeFirst, Integer sizeSecond, Integer sizeThird) {
//        mIminPrintUtils.printColumnsText(new String[]{colTextFirst,colTextSecond,colTextThird} ,
//                new int[]{colWidthFirst,colWidthSecond,colWidthThird} ,
//                new int[]{colAlignFirst,colAlignSecond,colAlignThird} ,new int[]{sizeFirst,sizeSecond,sizeThird} );
//
//        //lumnsText(String[] colTextArr, int[] colWidthArr, int[] colAlign, int[] size)
//    }

    public static void PrintingValueSetForIMIN(IminPrintUtils mIminPrintUtils, String colTextFirst, String colTextSecond, String colTextThird,
                                                    int[] ints, int[] ints1, int[] ints2) {
        try {
            mIminPrintUtils.printColumnsText(new String[]{colTextFirst, colTextSecond, colTextThird},
                    ints,
                    ints1, ints2);
        } catch (NullPointerException e){

           // if (CashLayoutActivity.terminalTypeVal.toUpperCase().equals(Constraints.IMIN.toUpperCase())) {
                DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                        System.currentTimeMillis(), DBFunc.PurifyString("Err-PrintingValueSetForIMIN-NullPointerException-"
                                + e.getMessage()));
           // }
        }catch (Exception e){
           // if (CashLayoutActivity.terminalTypeVal.toUpperCase().equals(Constraints.IMIN.toUpperCase())) {
                DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                        System.currentTimeMillis(), DBFunc.PurifyString("Err-PrintingValueSetForIMIN-Exception-"
                                + e.getMessage()));
            //}
        }
    }

    public static void PrintingValueSetForIMINReport(IminPrintUtils mIminPrintUtils, String colText1, String colText2) {
        try {
            if (colText2.equals(Constraints.HEADER)){
                mIminPrintUtils.printColumnsText(new String[]{colText1, "", ""},
                        new int[]{2, 0, 0},
                        new int[]{0, 1, 2}, new int[]{22, 22, 22});
            }else {
                mIminPrintUtils.printColumnsText(new String[]{colText1, "", colText2},
                        new int[]{2, 0, 1},
                        new int[]{0, 1, 2}, new int[]{22, 22, 22});
            }
        } catch (NullPointerException e){
           // if (ReportActivity.terminalTypeVal.toUpperCase().equals(Constraints.IMIN.toUpperCase())) {
                DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                        System.currentTimeMillis(), DBFunc.PurifyString("Err-ReportActivity-PrintingValueSetForIMINReport-"
                                + e.getMessage()+"-"+mIminPrintUtils));
           // }
        }catch (Exception e){
           // if (ReportActivity.terminalTypeVal.toUpperCase().equals(Constraints.IMIN.toUpperCase())) {
                DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                        System.currentTimeMillis(), DBFunc.PurifyString("Err-ReportActivity-PrintingValueSetForIMINReport-"
                                + e.getMessage()+"-"+mIminPrintUtils));
           // }
        }
    }
//    public static Integer GetLine50SpaceCount(String val) {
//        Integer linespacecount = 50;
//        if (val.toUpperCase().equals(Constraints.IMIN)){
//            linespacecount = 65;
//        }
//        return linespacecount;
//    }
//    public static Integer GetLine68SpaceCount(String val) {
//        Integer linespacecount = 68;
//        if (val.toUpperCase().equals(Constraints.IMIN)){
//            linespacecount = 83;
//        }
//        return linespacecount;
//    }
//    public static Integer GetNameLineSpaceCount(String val,Integer count) {
//        Integer linespacecount = count;
//        if (val.toUpperCase().equals(Constraints.IMIN)){
//            linespacecount = count + 15;
//        }
//        return linespacecount;
//    }
//    public static Integer GetNameLine21SpaceCount(String val) {
//        Integer linespacecount = 21;
//        if (val.toUpperCase().equals(Constraints.IMIN)){
//            linespacecount = 36;
//        }
//        return linespacecount;
//    }

    public static void UpdatStockAgentByStatus(String BillNo) {
        //String findbno = "Select ProductID FROM DetailsBillProduct WHERE BillNo = '"+BillNo+"'";
        String findbno = "Select ProductID,SUM(ProductQty) FROM DetailsBillProduct WHERE BillNo = '"+BillNo+"' " +
                "Group By ProductID";

        Cursor ccfindbno = DBFunc.Query(findbno,false);
        if (ccfindbno != null) {
            while (ccfindbno.moveToNext()){
                String dbpProductID = ccfindbno.getString(0);
                Integer dbpProductQty = ccfindbno.getInt(1);


                String findplustockagent = "Select QtyActual FROM StockAgent WHERE PLUID = '"+dbpProductID+"' Order By ID DESC";

                Cursor ccfindplustockagent = DBFunc.Query(findplustockagent,true);
                if (ccfindplustockagent != null) {
                    if (ccfindplustockagent.moveToNext()){
                        Integer QtyActual = ccfindplustockagent.getInt(0);
                        //QtyActual = QtyActual + 1;
                        QtyActual = QtyActual + dbpProductQty;

                        try {
                            String usql = "UPDATE StockAgent SET QtyActual = '"+QtyActual+"' WHERE PLUID = '" + dbpProductID + "'";

                            DBFunc.ExecQuery(usql, true);
                        }catch (Exception e){
                            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                                    System.currentTimeMillis(), DBFunc.PurifyString("Error-Query-UpdatStockAgentByStatus- " +   e.getMessage()));
                        }

                        Query.UpdateOnHandQtyByPLUID(Integer.parseInt(dbpProductID),QtyActual);
                    }
                    ccfindplustockagent.close();
                }
            }
            ccfindbno.close();
        }
    }

    public static void UpdateBillListForZClose(String billNo) {
        String bidsql = "SELECT SalesDateTime,BillNo,TotalQty,TotalNettSales,STATUS " +
                "FROM Sales WHERE BillNo = '"+billNo+"'";

        Cursor cdd = DBFunc.Query(bidsql, false);
        if (cdd != null) {
            if (cdd.moveToNext()) {
                try {
                    String updatesql = "UPDATE BillList SET Date = '" + cdd.getString(0) + "', " +
                            "IsClosed  = 'Closed'," +
                            "TotalItems  = '"+cdd.getInt(2)+"'," +
                            "TotalAmount  = '"+cdd.getDouble(3)+"'," +
                            "STATUS = '"+cdd.getString(4)+"' " +
                            "WHERE BillNo = '" + billNo + "' ";

                    DBFunc.ExecQuery(updatesql, false);

                    DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                            System.currentTimeMillis(), DBFunc.PurifyString("Update-Query-UpdateBillListForZClose- "+updatesql));
                }catch (Exception e){
                    DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                            System.currentTimeMillis(), DBFunc.PurifyString("Error-Query-UpdateBillListForZClose- " +   e.getMessage()));
                }
            }
        }
    }

    public static void UpdateIsZInSyncSales() {
        try {
            String str_update_str = "UPDATE SyncSales SET IsZ =  'Z' WHERE  ( IsZ  IS NULL OR IsZ = '' ) ";
            //String str_update_str = "UPDATE SyncSales SET IsZ =  'Z' WHERE ( IsZ  IS NULL || IsZ = '' ) ";

            DBFunc.ExecQuery(str_update_str, false);
        }catch (Exception e){
            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                    System.currentTimeMillis(), DBFunc.PurifyString("Error-Query-UpdateIsZInSyncSales- " +   e.getMessage()));
        }
    }

    public static void UpdateDetailsBillProductStatusByBillNo(String billNo,String status,long dtl) {
        try {
            String query_details_billproduct = "UPDATE DetailsBillProduct SET ";
            query_details_billproduct += "Cancel = '"+status+"', ";
            query_details_billproduct += "DateTime = "+dtl+" ";
            query_details_billproduct += "WHERE BillNo = '"+billNo+"'";

            DBFunc.ExecQuery(query_details_billproduct, false);

            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                    System.currentTimeMillis(), DBFunc.PurifyString("Update-Query-UpdateDetailsBillProductStatusByBillNo- " +  query_details_billproduct));

        }catch (Exception e){
            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                    System.currentTimeMillis(), DBFunc.PurifyString("Error-Query-UpdateDetailsBillProductStatusByBillNo- " +   e.getMessage()));
        }
    }

    public static void UpdateSalesStatusByBillNo(String billNo, String status) {
        try {
            String query = "UPDATE Sales SET ";
            query += "STATUS = '"+status+"' ";
            query += "WHERE BillNo = '"+billNo+"'";

            DBFunc.ExecQuery(query, false);


            String sql = "UPDATE Bill SET CloseDateTime = " + System.currentTimeMillis()  + " WHERE TransNo = " + billNo;

            DBFunc.ExecQuery(sql, false);

            DBFunc.ExecQuery(query, false);


            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                    System.currentTimeMillis(), DBFunc.PurifyString("Update-Query-UPDATEBill- " +   sql));

            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                    System.currentTimeMillis(), DBFunc.PurifyString("Update-Query-UpdateSalesStatusByBillNo- " +   query));

        }catch (Exception e){
            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                    System.currentTimeMillis(), DBFunc.PurifyString("Error-Query-UpdateSalesStatusByBillNo- " +   e.getMessage()));
        }
    }

    public static void DonothaveUserAccess(Context appContext) {
        try {
            new SweetAlertDialog(appContext, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText(Constraints.DonothaveUserAccess)
                    .setConfirmText(Constraints.OK)
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismissWithAnimation();
                        }
                    })
                    .show();
        } catch (Exception e){
            Log.i("errr__","erer__"+e.getMessage());
            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                    System.currentTimeMillis(), DBFunc.PurifyString("Error_Query -> DonothaveUserAccess: " +e.getMessage()));
        }
    }

    public static void SaveUserAccess(UserAccess userAccess) {
        String searchUserAccess = "SELECT SLCode FROM UserAccess WHERE SLCode = '"+userAccess.getUserAccessSLCode()+"' " +
                " AND User = '"+userAccess.getUserAccessUser()+"' AND Password = '"+userAccess.getUserAccessPassword()+"' ";
        Cursor cSearchUserAccess = DBFunc.Query(searchUserAccess,true);
        if (cSearchUserAccess != null) {
            if (cSearchUserAccess.getCount() == 0){
                try {
                    String userAccessSave = "INSERT INTO UserAccess (SLCode,SLName,User,Password,accessable,DateTime) " +
                            "VALUES ('" + DBFunc.PurifyString(userAccess.getUserAccessSLCode()) + "'," +
                            "'" + DBFunc.PurifyString(userAccess.getUserAccessSLName()) + "'," +
                            "'" + DBFunc.PurifyString(userAccess.getUserAccessUser()) + "'," +
                            "'" + DBFunc.PurifyString(userAccess.getUserAccessPassword()) + "'," +
                            "'" + DBFunc.PurifyString(userAccess.getUserAccessaccessable()) + "'," +
                            System.currentTimeMillis() + ")";

                    DBFunc.ExecQuery(userAccessSave, true);

                    DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                            System.currentTimeMillis(), DBFunc.PurifyString("Saved-SaveUserAccess- " + userAccessSave));

                }catch (Exception e){
                    DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                            System.currentTimeMillis(), DBFunc.PurifyString("Error-Query-SaveUserAccess- " +   e.getMessage()));
                }
            }
            cSearchUserAccess.close();
        }
    }

    public static String SearchUserAccess(String slcode,String user,String password) {
        String accessable = "0";
        String searchUserAccess = "SELECT accessable FROM UserAccess WHERE SLCode = '"+slcode+"' " +
                " AND User = '"+user+"' AND Password = '"+password+"' ";

        Cursor cSearchUserAccess = DBFunc.Query(searchUserAccess,true);
        if (cSearchUserAccess != null) {
            if (cSearchUserAccess.moveToNext()){
                accessable = cSearchUserAccess.getString(0);
            }
            cSearchUserAccess.close();
        }
        if (accessable == null || accessable.equals("")){
            accessable = "0";
        }

        return accessable;
    }

    public static String CheckingNullOrNot(String value,String colName) {
        String sql = "";
        if (value != null && value.trim().length() > 0 && !(value.equals("0"))
                && !(value.trim() == null)
                && !(value.trim().equals("null"))) {
            sql += " AND "+colName+" = '" + value + "'";
        } else {
            sql += " AND ( "+colName+" IS NULL OR "+colName+" = '' OR "+colName+" = 'null'  OR "+colName+" = '0' ) ";
//                            " AND Remarks = '"+EditFragmentRemarks+"' ";
        }
        return sql;
    }

    public static double GetSubTotalValueFromDetailsBillProduct(String billNo) {
        Double subtotal = 0.0;
//        String sql = " SELECT SUM(ProductPrice),SUM(ItemDiscountAmount),SUM(ProductQty) " +
//                "FROM DetailsBillProduct " +
//                "Where BillNo = '"+ billNo +"' " +
//////                " AND  ( Cancel = 'SALES' || Cancel = 'CANCEL' ) " +
//                "group by ProductID,OpenPriceStatus,Remarks";
        String sql = " SELECT (ProductPrice),(ItemDiscountAmount),(ProductQty) " +
                "FROM DetailsBillProduct " +
                "Where BillNo = '"+ billNo +"' " +
////                " AND  ( Cancel = 'SALES' || Cancel = 'CANCEL' ) " +
                "group by ProductID,OpenPriceStatus,Remarks";
        Log.i("sql____","sql___sBillProduct___"+sql);
        Cursor c = DBFunc.Query(sql, false);
        if (c != null) {
            subtotal = 0.0;
            while (c.moveToNext()){
                Log.i("sql____","sql___sBillProdusubtotal___"+c.getDouble(0)+"-"+c.getDouble(1));
                subtotal += c.getDouble(0) - (c.getDouble(1) * c.getDouble(2));
            }
            c.close();
        }
        Log.i("sql____","sql___sBillProdusubtotal___"+subtotal);
        return subtotal;
    }

    public static double GetItemDiscountAmount(String billNo) {
        Double ItemDiscountAmount = 0.0;
        String sqlIDA = " SELECT ItemDiscountAmount,ProductQty" +
                " FROM DetailsBillProduct " +
                " Where BillNo = '"+ billNo +"' ";
        Log.i("_sql__",sqlIDA);
        Cursor csqlIDA = DBFunc.Query(sqlIDA, false);
        if (csqlIDA != null){
            ItemDiscountAmount = 0.0;
            while (csqlIDA.moveToNext()){
                ItemDiscountAmount += csqlIDA.getDouble(0) * csqlIDA.getDouble(1);
            }
            csqlIDA.close();
        }
        return ItemDiscountAmount;
    }

    public static void UpdatePLUModi(Context context,String popupProductID,String billNo,String strRemarks
            ,String openPrice,String DetailsBillProductID) {
//        String splu = "Select UUID FROM PLU WHERE ID = " +popupProductID;
//        Cursor ccsplu = DBFunc.Query(splu,true);
//        String uuidd = "";
//        if (ccsplu != null) {
//            uuidd = "";
//            if (ccsplu.moveToNext()) {
//                uuidd = ccsplu.getString(0);
//            }
//            ccsplu.close();
//        }
//        String sqlunselected = "Update PLUModi SET Remarks = '"+strRemarks+"' " +
//                " WHERE BillNo = '" + billNo + "' " +
//                "AND PLU_UUID = '" + uuidd + "' " +
//                    " AND OpenPriceStatus = '"+openPrice+"' " ;
//       // sqlunselected += Query.CheckingNullOrNot(openPrice,"OpenPriceStatus");
//
//        DBFunc.ExecQuery(sqlunselected, false);
//
//        Log.i("sdf___sqlunselected","_____"+sqlunselected);
//
//        DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
//                System.currentTimeMillis(), DBFunc.PurifyString("Update-EditProductSheetFragment -PLUModi-" +
//                        sqlunselected));


        String sqlunselected = "Update PLUModi SET Remarks = '"+strRemarks+"' " +
                " WHERE BillNo = '"+billNo+"' AND DetailsBillProductID = '" + DetailsBillProductID + "' " ;
        DBFunc.ExecQuery(sqlunselected, false);

        Log.i("sdf___sqlunselected","_____"+sqlunselected);

        DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                System.currentTimeMillis(), DBFunc.PurifyString("Update-EditProductSheetFragment -PLUModi-" +
                        sqlunselected));

        try {
            CheckOutActivity.updateCheckoutAdapter(context);
        } catch (Exception e){
            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                    System.currentTimeMillis(), DBFunc.PurifyString("Err-updateCheckoutAdapter-" + e.getMessage()));
        }
//        String str_update_str = "UPDATE DetailsBillProduct SET " +
//                "Remarks =  '" + strRemarks + "', " +
//                "DateTime =  " + System.currentTimeMillis() + " " ;
//        str_update_str +=  " Where BillNo = '"+billNo+"' " +
//                "AND ProductId = '"+popupProductID+"' ";
//        str_update_str += Query.CheckingNullOrNot(openPrice,"OpenPriceStatus");
//
//        DBFunc.ExecQuery(str_update_str, false);
//        DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
//                System.currentTimeMillis(), DBFunc.PurifyString("Update-EditProductSheetFragment -Remarks-" +
//                        str_update_str));

    }

    public static void UpdateDetailsBillProduct(String chkAction,String status,String EditFragmentOpenPrice,String strRemarks,
                                                String billNo,String popupProductID,String ID) {
        Log.i("productId_","EditFragmentOpenPrice_____"+EditFragmentOpenPrice);
        Log.i("productId_","EditFID_____"+ID);
        try {


//            String updateRemarksDetatilsBill = "UPDATE DetailsBillProduct " +
//                    "SET Remarks = '"+strRemarks+"' " +
//                    "WHERE BillNo = '" + billNo + "' " +
//                    " AND ProductID = '"+popupProductID+"' AND OpenPriceStatus = '"+EditFragmentOpenPrice+"' ";
//            Log.i("update___","updupdateRemarksDetatilsBill__2_"+updateRemarksDetatilsBill);
//            DBFunc.ExecQuery(updateRemarksDetatilsBill, false);

            String updateRemarksDetatilsBill = "";

            if (ID != null && Integer.parseInt(ID) > 0) {
                 updateRemarksDetatilsBill = "UPDATE DetailsBillProduct " +
                        "SET Remarks = '" + strRemarks + "' " +
                        "WHERE ID = " + ID + " ";

                Log.i("chkExistingRemarks_____", "updateRemarksDetatilsBill___" + updateRemarksDetatilsBill);

                DBFunc.ExecQuery(updateRemarksDetatilsBill, false);

            } else {
                updateRemarksDetatilsBill = "UPDATE DetailsBillProduct " +
                        "SET Remarks = '" + strRemarks + "' ";
                updateRemarksDetatilsBill += "WHERE BillNo = '" + billNo + "' " +
                        " AND ProductID = '" + popupProductID + "' ";
                if (status.equals("openPrice")) {
                    //if (EditFragmentOpenPrice.equals("0")) {
                    updateRemarksDetatilsBill += "AND OpenPriceStatus = '" + EditFragmentOpenPrice + "' ";
                    //}
                }
            }
//            if (EditFragmentOpenPrice.equals("0")) {
//                    updateRemarksDetatilsBill += "AND ( OpenPriceStatus = '" + EditFragmentOpenPrice + "' OR " +
//                            "OpenPriceStatus IS NULL OR  OpenPriceStatus = '' )";
//                }
            Log.i("update___", "updupdateRemarksDetatilsBill__1_" + updateRemarksDetatilsBill);
            DBFunc.ExecQuery(updateRemarksDetatilsBill, false);

            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                    System.currentTimeMillis(), DBFunc.PurifyString("Updated-DeilstBP-" + chkAction + "-" +updateRemarksDetatilsBill));

        } catch (Exception e){
            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                    System.currentTimeMillis(), DBFunc.PurifyString("Err-DeilstBP-" + chkAction + "-" +e.getMessage()));
        }
    }

    public static Integer CategoryCheck() {
        String categoryCheck = "SELECT ID FROM Category WHERE ID IS NOT NULL";
        Cursor cursor = DBFunc.Query(categoryCheck,true);
        Integer id = 0;
        if (cursor != null) {
            if (cursor.moveToNext()){
                id = cursor.getInt(0);
            }
            cursor.close();
        }
        return id;
    }

    public static void UpdateNaNItemBillDiscount() {
        String updateItemDiscountAmt = "Update DetailsBillProduct SET ItemDiscountAmount = 0.00 " +
                "WHERE ( ItemDiscountAmount IS NULL OR ItemDiscountAmount = 'null' OR ItemDiscountAmount = 'NaN' ) ";
        DBFunc.ExecQuery(updateItemDiscountAmt,false);
    }

    private static void SaveLicenseKey(Tbllicense licensekey) {
        //String delete = "DELETE FROM LicenseKey";
        ///DBFunc.ExecQuery(delete,true);
        String checkDeviceData = "SELECT ID FROM LicenseKey ";
        Cursor c = DBFunc.Query(checkDeviceData,true);
        if (c != null){
            if (c.getCount() == 0){
                String saveDeviceData = "INSERT INTO LicenseKey (CHECKSUM,MacAddress,LicenseType,NoOfDay,DateTime) " +
                        "VALUES ('" + DBFunc.PurifyString(licensekey.getChecksum()) + "'," +
                        "'" + DBFunc.PurifyString(licensekey.getMacAddress()) + "'," +
                        "'" + DBFunc.PurifyString(licensekey.getLicenseType()) + "'," +
                        "'" + licensekey.getNoOfDay() + "'," +
                        " " + System.currentTimeMillis() + ")";
                Log.i("savelicensekey___","saveDeviceData___"+saveDeviceData);
                DBFunc.ExecQuery(saveDeviceData,true);
            } else {
                String updatequery = "UPDATE LicenseKey SET ";
                updatequery += "CHECKSUM = '"+ DBFunc.PurifyString(licensekey.getChecksum()) +"', ";
                updatequery += "MacAddress = '"+ DBFunc.PurifyString(licensekey.getMacAddress())+"', ";
                updatequery += "LicenseType = '"+ DBFunc.PurifyString(licensekey.getLicenseType())+"', ";
                updatequery += "NoOfDay = '"+ licensekey.getNoOfDay()+"', ";
                updatequery += "DateTime = " + System.currentTimeMillis();

                Log.i("savelicensekey___","saveDeviceData___"+updatequery);
                DBFunc.ExecQuery(updatequery, true);
            }
            c.close();
        }
    }

    public static String GetLicenseKey(String licensekey,String settingV) {
        String retVal = "0";
        String checkLicensekey = "SELECT MacAddress FROM LicenseKey ";
//        if (!settingV.equals("Settings")){
//            checkLicensekey += " WHERE MacAddress = '"+licensekey+"'";
//        }
        Log.i("checkLicensekey___","checkLicensekey____"+checkLicensekey);
        Cursor c = DBFunc.Query(checkLicensekey,true);
        if (c != null){
            if (c.moveToNext()){
                String macAddress = c.getString(0);
                //if (licensekey.equals(macAddress)){
                    //retVal = "1";
                    retVal = macAddress;
                //}else {
                    //retVal = "0";
                    //retVal = "0";
                //}
            }
            c.close();
        }
        return retVal;
    }
    public static String ValidateLicenseKey(String lKey) {
        String temp = "0";
        temp = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
                "  <soap12:Body>\n" +
                "    <ValidateLicenseKey xmlns=\"http://tempuri.org/\">\n" +
                "      <lKey>"+lKey+"</lKey>\n" +
                "    </ValidateLicenseKey>\n" +
                "  </soap12:Body>\n" +
                "</soap12:Envelope>";

        Log.i("temp___","temp___"+temp);
        return temp;
    }

    public static String GetSQLForBillPendingQty() {

//        Cursor c = DBFunc.Query("SELECT BillNo FROM Bill order by CloseDateTime DESC ", false);
//        String sql = "SELECT Bill.BillNo,BillList.STATUS FROM Bill " +
//                "inner join BillList on BillList.BillID = Bill.BillNo " +
//                "where Bill.CloseDateTime IS NULL AND BillList.STATUS = 'PENDING'  order by BillList.BillNo DESC LIMIT 1";
//        String sql = "SELECT Bill.BillNo,BillList.STATUS,BillList.BillNo FROM Bill " +
//                "inner join BillList on BillList.BillID = Bill.BillNo " +
//                "where BillList.TotalItems = '0' AND BillList.STATUS = 'PENDING'  order by BillList.BillNo DESC LIMIT 1";

//		return DBFunc.Query("SELECT BillNo FROM Bill where CloseDateTime IS NULL order by BillNo DESC ", false);
//		String sql = "SELECT Bill.BillNo,BillList.STATUS FROM Bill " +
//		"inner join BillList on BillList.BillID = Bill.BillNo " +
//		"where Bill.CloseDateTime IS NULL AND BillList.STATUS = 'PENDING'  order by BillList.BillNo DESC LIMIT 1";

        String sql = "SELECT Bill.BillNo,BillList.STATUS FROM Bill " +
                "inner join BillList on BillList.BillID = Bill.BillNo " +
                "where BillList.TotalItems = '0' AND BillList.STATUS = 'PENDING'  order by BillList.BillNo DESC LIMIT 1";
        return sql;
    }

    public static void saveZClose(Context context, ZClose zclosObj) {
        try {
            String sql = "INSERT INTO ZClose (AllBillNo,OpeningTime,ClosingTime,PrintedCount,UUID," +
                    "RetailID,TransIDFrom,TransIDTo,TransNoFrom,TransNoTo,TransDate,ZReadNo,ZReadDate," +
                    "ZReadUser,TerminalID,PendingSync,LastUser,LastUpdate,LockUser,LockUpdate,LockStatus," +
                    "RecordStatus,RecordUpdate,QueueStatus,DateTime) " +
                    "VALUES ('" + DBFunc.PurifyString(zclosObj.getAllBillNo()) + "'," +
                    "" + zclosObj.getOpeningTime() + "," +
                    "" + zclosObj.getClosingTime() + "," +
                    "" + zclosObj.getPrintedCount() + "," +
                    "'" + DBFunc.PurifyString(zclosObj.getUUID()) + "'," +
                    "" + zclosObj.getRetailID() + "," +
                    "'" + zclosObj.getTransIDFrom() + "'," +
                    "'" + zclosObj.getTransIDTo() + "'," +
                    "'" + zclosObj.getTransNoFrom() + "'," +
                    "'" + zclosObj.getTransNoTo() + "'," +
                    "'" + zclosObj.getTransDate() + "'," +
                    "'" + zclosObj.getZReadNo() + "'," +
                    "'" + zclosObj.getZReadDate() + "'," +
                    "'" + zclosObj.getZReadUser() + "'," +
                    "'" + zclosObj.getTerminalID() + "'," +
                    "'" + zclosObj.getPendingSync() + "'," +
                    "'" + zclosObj.getLastUser() + "'," +
                    "'" + zclosObj.getLastUpdate() + "'," +
                    "'" + zclosObj.getLockUser() + "'," +
                    "'" + zclosObj.getLockUpdate() + "'," +
                    "'" + zclosObj.getLockStatus() + "'," +
                    "'" + zclosObj.getRecordStatus() + "'," +
                    "'" + zclosObj.getRecordUpdate() + "'," +
                    "'" + zclosObj.getQueueStatus() + "'," +
                    System.currentTimeMillis() + ")";


            Log.i("SAVEZCLOSESQL__", "SQLL_" + sql);


            DBFunc.ExecQuery(sql, false);


            getZClose(context,zclosObj.getUUID(),"resync");
        } catch (Exception e){
            Log.i("SAVEZCLOSESQL__", "SQLL_Err_" + e.getMessage());
        }
    }

    public static ZClose getZClose(Context context,String uuid , String status) {
        ZClose zc = new ZClose();

        String sql = "SELECT AllBillNo,OpeningTime,ClosingTime,PrintedCount,UUID," +
                "RetailID,TransIDFrom,TransIDTo,TransNoFrom,TransNoTo,TransDate,ZReadNo,ZReadDate," +
                "ZReadUser,TerminalID,PendingSync,LastUser,LastUpdate,LockUser,LockUpdate,LockStatus," +
                "RecordStatus,RecordUpdate,QueueStatus,DateTime FROM ZClose WHERE UUID = '"+uuid+"' ";
        Log.i("SAVEZCLOSESQL__","SQLL_"+sql);

        Cursor c = DBFunc.Query(sql, false);

        if (c != null) {
            if (c.moveToNext()) {
                String AllBillNo = c.getString(0);
                long OpeningTime = c.getLong(1);
                long ClosingTime = c.getLong(2);
                int PrintedCount = c.getInt(3);
                String UUID = c.getString(4);
                String retailID = c.getString(5);
                String TransIDFrom = c.getString(6);
                String TransIDTo = c.getString(7);

                zc.setAllBillNo(AllBillNo);
                zc.setOpeningTime(OpeningTime);
                zc.setClosingTime(ClosingTime);
                zc.setPrintedCount(PrintedCount);
                zc.setUUID(UUID);
                zc.setRetailID(retailID);
                zc.setTransIDFrom(TransIDFrom);
                zc.setTransIDTo(TransIDTo);
                zc.setTransNoFrom(c.getString(8));
                zc.setTransNoTo(c.getString(9));
                zc.setTransDate(c.getString(10));
                zc.setZReadNo(c.getString(11));
                zc.setZReadDate(c.getString(12));
                zc.setZReadUser(c.getString(13));
                zc.setTerminalID(c.getString(14));
                zc.setPendingSync(c.getString(15));
                zc.setLastUser(c.getString(16));
                zc.setLastUpdate(c.getString(17));
                zc.setLockUser(c.getString(18));
                zc.setLockUpdate(c.getString(19));
                zc.setLockStatus(c.getString(20));
                zc.setRecordStatus(c.getString(21));
                zc.setRecordUpdate(c.getString(22));
                zc.setQueueStatus(c.getString(23));
                zc.setDt(System.currentTimeMillis());
            }
            c.close();
        }

        if (!status.equals("show")) {
            SyncActivity.UpdateTransZRead(context, zc);
        }
        return zc;
    }
    public static String UpdateTransZReadSOAP(String finalCompany_code, JSONObject jsonObj) {
        String temp = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
                "  <soap12:Body>\n" +
                "    <UpdateTransZRead xmlns=\"http://tempuri.org/\">\n" +
                "      <companyCode>"+ finalCompany_code +"</companyCode>\n" +
                "      <TransZReadJson>"+jsonObj+"</TransZReadJson>\n" +
                "    </UpdateTransZRead>\n" +
                "  </soap12:Body>\n" +
                "</soap12:Envelope>";

        Log.i("jsonArrObj___","jsonArtemp_"+temp);
        return temp;
    }


    public static void updateZReadByJsonSendingValue(String uuid, JSONObject jsonArrObj) {
        String updateSQL = "UPDATE ZClose SET " +
                "sendJson = '"+jsonArrObj+"' ," +
                "RecordUpdate = '"+System.currentTimeMillis()+"' " +
                "Where UUID = '"+uuid +"' ";
        Log.i("sdfs__","updateSQL__"+updateSQL);
        DBFunc.ExecQuery(updateSQL, false);
    }

    private static void updateZReadByResponseValue(String uuid, String responseStatus, String responseTransNo) {

        String updateSQL = "UPDATE ZClose SET " +
                "responseStatus = '"+responseStatus+"' ," +
                "responseTransNo = '"+responseTransNo+"' " +
                "Where UUID = '"+uuid +"' ";
        Log.i("sdfs__","updateSQL__"+updateSQL);
        DBFunc.ExecQuery(updateSQL, false);
    }

    public static String changeLongtoDateFormat(long longdtval) {
        long now = longdtval;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(now);

        SimpleDateFormat sdf = new SimpleDateFormat(Constraints.dateYMD_YMDHMS, Locale.US);
        final Calendar myCalendar2 = Calendar.getInstance();
        String todayd = sdf.format(calendar.getTime());
        return  todayd;
    }

    public static int screenSize(Context contex,String status){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) contex.getSystemService(Context.WINDOW_SERVICE);
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
        final int deviceWidth = displayMetrics.widthPixels;
        final int deviceHeight = displayMetrics.heightPixels;
        if (status.equals("W")){
            return deviceWidth;
        }else {
            return deviceHeight;
        }
    }
}

