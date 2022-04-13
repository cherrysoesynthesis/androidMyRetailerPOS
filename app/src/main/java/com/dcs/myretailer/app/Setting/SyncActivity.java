package com.dcs.myretailer.app.Setting;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

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
import com.dcs.myretailer.app.Cashier.DeclarationConf;
import com.dcs.myretailer.app.Category.CategoryModel;
import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.DialogBox;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.RemarkMainActivity;
import com.dcs.myretailer.app.TransactionDetailsActivity;
import com.dcs.myretailer.app.UserAccess;
import com.dcs.myretailer.app.ZClose.ZClose;
import com.dcs.myretailer.app.databinding.ActivitySyncBinding;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class SyncActivity extends AppCompatActivity implements View.OnClickListener {
    public static String syncRetailID = " ";
    public static String syncUrl = " ";
    public static String syncCompanyCode = " ";
    public static String download_retail_ID = " ";
    public static String download_url = " ";
//    public static String download_userlogin = " ";
//    public static String download_userpassword = " ";
    public static String terminal_id = " ";
    public static String store_no = " ";
    public static String download_company_code = " ";
    public static String getPromotionResponse;
    String getItemSetMenuResponse  = "";
    String PromoID = "";
    String PromoName = "";
    String Promo_Type_ID = "";
    String Promo_TypeCode = "";
    String Promo_DateFrom = "";
    String Promo_DateTo = "";
    String PromoItems = "";
    String promoItemID  = "";
    String promoItemUOM = "";
    String promoItem_UnitID = "";
    String promoItemPrice = "";
    String promoItemMemberPrice = "";
    String promoItemUnit = "";
    String promoItem_Qty = "";
    String promoItem_Amt = "";
    String promoItem_Percentage = "";
    String promoItem_MemberAmt = "";
    String promoItem_MemberPerc = "";
    String promoItem_Qty2 = "";
    String promoItem_Amt2 = "";
    String promoItem_Percentage2 = "";
    String promoItem_MemberAmt2 = "";
    String promoItem_MemberPerc2 = "";
    String promoItem_Qty3 = "";
    String promoItem_Amt3 = "";
    String promoItem_Percentage3 = "";
    String promoItem_MemberAmt3 = "";
    String promoItem_MemberPerc3 = "";
    String promoItem_Qty4 = "";
    String promoItem_Amt4 = "";
    String promoItem_Percentage4 = "";
    String promoItem_MemberAmt4 = "";
    String promoItem_MemberPerc4 = "";
    String promoItem_Qty5 = "";
    String promoItem_Amt5 = "";
    String promoItem_Percentage5 = "";
    String promoItem_MemberAmt5 = "";
    String promoItem_MemberPerc5 = "";
    String promoItem_Qty6 = "";
    String promoItem_Amt6 = "";
    String promoItem_Percentage6 = "";
    String promoItem_MemberAmt6 = "";
    String promoItem_MemberPerc6 = "";
    String promoItem_Qty7 = "";
    String promoItem_Amt7 = "";
    String promoItem_Percentage7 = "";
    String promoItem_MemberAmt7 = "";
    String promoItem_MemberPerc7 = "";
    String promoItem_Qty8 = "";
    String promoItem_Amt8 = "";
    String promoItem_Percentage8 = "";
    String promoItem_MemberAmt8 = "";
    String promoItem_MemberPerc8 = "";
    String promoItem_Qty9 = "";
    String promoItem_Amt9 = "";
    String promoItem_Percentage9 = "";
    String promoItem_MemberAmt9 = "";
    String promoItem_MemberPerc9 = "";
    String promoItem_Qty10 = "";
    String promoItem_Amt10 = "";
    String promoItem_Percentage10 = "";
    String promoItem_MemberAmt10 = "";
    String promoItem_MemberPerc10 = "";
    String promoPromoMixMatch = "";
    String getInventoryResult = "";
    static String getUserAccessResult = "";
    static String GetAllUserAccessRightResult = "";
    String getCategoryResult = "";
    static String getSyncResult = "";
    String ItemID = "";
    String ItemSKU = "";
    String ItemImage = "";
    String ItemDescp = "";
    String SupBarCode = "";
    String UOM = "";
    Double Price = 0.0;
    String OnHandQty = "";
    String Department = "";
    String Category = "";
    String OpenPrice = "";
    String ItemRemark = "";
    String option = "";
    String GST = "";
    String GSTCode = "";
    String GSTRate = "";
    String GSTIncExc = "";
    String str_dept = "";
    String str_set_menu = "";
    public static String __volley_submit_sale_memberID = " ";
    public static double _tot_a = 0.0;
    public static int _paymentID = 0;
    public static ArrayList<ItemDetail> volleyItemStringList1 = new ArrayList<ItemDetail>();
    public static ArrayList<ItemDetail> volleyItemStringList = new ArrayList<ItemDetail>();

    SyncAdapter syncAdapter;
    String[] syncList = {"Download Department", "Download Inventory", "Download Promotion", "Resend Sale", "Resync Payment Type"};

    static ProgressDialog progressDialog;
    Context context;
    ActivitySyncBinding binding = null;

    public static String eunoia_storeId = "632";
    public static String eunoia_partnerId = "WEEBO";
    public static String eunoia_url = "http://jerifoodeunoiastage.jeripay.com/api";
    public static String eunoia_partnerEmail = "test@weebo.com";
    public static String eunoia_partnerSecret = "s34b78u6nw23ds1357b56e34";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_sync);
        context = SyncActivity.this;

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        setTitle("Sync Management");

        myToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_close_white));
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        GetSyncDataInformationAll();

        binding.syncId.setOnClickListener(this);
        binding.downloadDepartment.setOnClickListener(this);
        binding.downloadInventory.setOnClickListener(this);
        binding.downloadUserAccess.setOnClickListener(this);
        binding.downloadPromotion.setOnClickListener(this);
        binding.resendSale.setOnClickListener(this);
        binding.syncDiscount.setOnClickListener(this);
        binding.syncPaymentType.setOnClickListener(this);
        binding.getDiscountlist.setOnClickListener(this);
        binding.getDiscountlist.setOnClickListener(this);
        binding.syncStockadjustment.setOnClickListener(this);
        binding.getEunoiaMenu.setOnClickListener(this);
        binding.getEunoiaProducts.setOnClickListener(this);
        binding.deleteLog.setOnClickListener(this);

        syncAdapter = new SyncAdapter(SyncActivity.this, syncList);
        binding.syncList.setAdapter(syncAdapter);

        String device = Query.GetDeviceData(Constraints.DEVICE);
        if (device.equals("M2-Max")) {
            LinearLayout.LayoutParams linearOverAllParams = new LinearLayout.LayoutParams(750,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            linearOverAllParams.leftMargin = 10;
            binding.layOverAll.setLayoutParams(linearOverAllParams);
        }

        binding.syncList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view, int position, long id) {

                Toast.makeText(getApplicationContext(),
                        "You click on position:"+position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void GetSyncDataInformationAll() {
        Cursor Cursor_Possys = Query.GetURLAndCodeFromPossys();
        if (Cursor_Possys != null) {
            while (Cursor_Possys.moveToNext()) {
                syncRetailID = Cursor_Possys.getString(0);
                syncCompanyCode = Cursor_Possys.getString(1);
                syncUrl = Cursor_Possys.getString(2);
                download_retail_ID = Cursor_Possys.getString(3);
                download_company_code = Cursor_Possys.getString(4);
                download_url = Cursor_Possys.getString(5);
                terminal_id = Cursor_Possys.getString(6);
                store_no = Cursor_Possys.getString(7);
                eunoia_storeId = Cursor_Possys.getString(9);
                eunoia_partnerId = Cursor_Possys.getString(10);
                eunoia_url = Cursor_Possys.getString(11);
                eunoia_partnerEmail= Cursor_Possys.getString(12);
//                download_userlogin = Cursor_Possys.getString(13);
//                download_userpassword = Cursor_Possys.getString(14);
            }
            Cursor_Possys.close();
        }
    }

//    private void SyncSales(final String str,String tempVar) {
public static  void SyncSales(Context context,RequestQueue queue1, String url, final String tagName, final String tempVar,
                            final String billNo, final String statusSyncStatus, final String zCloseStatus,
                            final String linkStr) {
//    queue,syncUrl,"submitStockAdjustResult",temp,
        Cursor Cursor_Possys = Query.GetURLAndCodeFromPossys();
        if (Cursor_Possys != null) {
            while (Cursor_Possys.moveToNext()) {
                syncRetailID = Cursor_Possys.getString(0);
                syncCompanyCode = Cursor_Possys.getString(1);
                syncUrl = Cursor_Possys.getString(2);
                download_retail_ID = Cursor_Possys.getString(3);
                download_company_code = Cursor_Possys.getString(4);
                download_url = Cursor_Possys.getString(5);
            }
            Cursor_Possys.close();
        }
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, syncUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //progressDialog.dismiss();
                        // response code
                        String xmlString = response;

                        //Log.i("inv_xmlString",xmlString);
                        Document xmlparse = null;

                        Document parse = APIActivity.XMLParseFunction(xmlString, xmlparse);

//                        for (int i = 0; i < parse.getElementsByTagName(tagName).getLength(); i++) {
//                            getSyncResult = (parse.getElementsByTagName(tagName).getLength() > 0)
//                                    ? parse.getElementsByTagName(tagName).item(i).getTextContent() : " ";

                        for (int i = 0; i < parse.getElementsByTagName("updatePaymentMethodResponse").getLength(); i++) {
                            getSyncResult = (parse.getElementsByTagName("updatePaymentMethodResponse").getLength() > 0)
                                    ? parse.getElementsByTagName("updatePaymentMethodResponse").item(i).getTextContent() : " ";




                            if (linkStr.equals("SubmitSales") && !(getSyncResult.equals("Success"))) {
                                String Status = "";
                                String TransNo = "";
                                try {
                                    JSONObject JObj = new JSONObject(getSyncResult);
                                    if (JObj.has("Status")) {
                                        Status = JObj.getString("Status");
                                    }
                                    if (JObj.has("TransNo")) {
                                        TransNo = JObj.getString("TransNo");
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                getSyncResult = Status;



                                if (Status.equals("Success")) {
                                    if (TransNo != null && TransNo.length() > 0) {

                                        Query.UpdateSyncSalesStatusAftSendData(TransNo, statusSyncStatus, zCloseStatus);

                                    }
                                }

                            } else {

                                if (statusSyncStatus.equals("Success")) {

//                                    returnValue[0] = Result[0]; //Return String value Success

                                    updateSyncSales(0, "SUCCESS", billNo);

                                } else {

                                }
                            }
//                        for (int i=0;i< parse.getElementsByTagName("getInventoryResult").getLength();i++) {
//                            getInventoryResult = (parse.getElementsByTagName("getInventoryResult").getLength() > 0)
//                                    ? parse.getElementsByTagName("getInventoryResult").item(i).getTextContent() : " ";
//                        }
//                        for (int i=0;i< parse.getElementsByTagName("GetCategoryMenuResponse").getLength();i++) {
//                            getCategoryResult = (parse.getElementsByTagName("GetCategoryMenuResponse").getLength() > 0)
//                                    ? parse.getElementsByTagName("GetCategoryMenuResponse").item(i).getTextContent() : " ";
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

//                        // As of f605da3 the following should work
//                progressDialog.dismiss();
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
        })
        {
            @Override
            public Map<String, String> getParams(){
                return null;
            }
            @Override
            public byte[] getBody(){
                String temp = tempVar;


                byte[] b = temp.getBytes(Charset.forName("UTF-8"));

                return b;
            }
            @Override
            public String getBodyContentType()
            {
                return "text/xml;charset=utf-8";
            }
        };
        queue.add(stringRequest);
//        progressDialog = Query.showProgressDialog(SyncActivity.this, Constraints.Downaloding);
    }

    private void volleyGetCategory(final String str) {
        Cursor Cursor_Possys = Query.GetURLAndCodeFromPossys();
        if (Cursor_Possys != null) {
            while (Cursor_Possys.moveToNext()) {
                syncRetailID = Cursor_Possys.getString(0);
                syncCompanyCode = Cursor_Possys.getString(1);
                syncUrl = Cursor_Possys.getString(2);
                download_retail_ID = Cursor_Possys.getString(3);
                download_company_code = Cursor_Possys.getString(4);
                download_url = Cursor_Possys.getString(5);
            }
            Cursor_Possys.close();
        }
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, download_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //progressDialog.dismiss();
                        // response code
                        String xmlString = response;
                        //Log.i("inv_xmlString",xmlString);
                        Document xmlparse  = null;
                        Document parse = APIActivity.XMLParseFunction(xmlString, xmlparse);
//                        for (int i=0;i< parse.getElementsByTagName("getInventoryResult").getLength();i++) {
//                            getInventoryResult = (parse.getElementsByTagName("getInventoryResult").getLength() > 0)
//                                    ? parse.getElementsByTagName("getInventoryResult").item(i).getTextContent() : " ";
//                        }
                        for (int i=0;i< parse.getElementsByTagName("GetCategoryMenuResponse").getLength();i++) {
                            getCategoryResult = (parse.getElementsByTagName("GetCategoryMenuResponse").getLength() > 0)
                                    ? parse.getElementsByTagName("GetCategoryMenuResponse").item(i).getTextContent() : " ";
                        }

                        //displayPopupDialogQRScan(getInventoryResult,getInventoryResult);

                        try {


                            JSONObject obj = new JSONObject(getCategoryResult);
                            String inv_obj = obj.getString("categories");
                            JSONArray mJsonArray = new JSONArray(inv_obj);

                            JSONObject categoryObject = null;
                            DBFunc.ExecQuery("DELETE FROM Category", true);

                            for (int i =0; i < mJsonArray.length(); i++) {
                                categoryObject = mJsonArray.getJSONObject(i);

                                String uuid = categoryObject.getString("CategoryID");
                                String categoryName = categoryObject.getString("CategoryName");
                                String categoryCode = categoryObject.getString("CategoryCode");
                                String categoryImage = categoryObject.getString("CategoryImage");

//                                 [ { "CategoryID": "fca99d56-2f21-11eb-be45-00155d01ca02", "CategoryCode": "ALICE MARTHA",
//                                        "CategoryName": "ALICE MARTHA", "CategoryImage": null, "OtherLanguage": "" },
//                                Integer ID, String UUID, String name, String code, String image, Integer dateTime
                                CategoryModel ctM = new CategoryModel(0,uuid,categoryName,categoryCode,categoryImage,0);
                                Query.saveCategory(ctM);
                            }

                            progressDialog.dismiss();

                            success(context);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

//                        // As of f605da3 the following should work
                progressDialog.dismiss();
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
        })
        {
            @Override
            public Map<String, String> getParams(){
                return null;
            }
            @Override
            public byte[] getBody(){
                String temp = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                        "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
                        "  <soap12:Body>\n" +
                        "    <GetCategoryMenu xmlns=\"http://tempuri.org/\">\n" +
                        "      <companyCode>"+download_company_code+"</companyCode>\n" +
                        "    </GetCategoryMenu>\n" +
                        "  </soap12:Body>\n" +
                        "</soap12:Envelope>";

                //Log.i("APP", "request String: " + temp);
                byte[] b = temp.getBytes(Charset.forName("UTF-8"));

                return b;
            }
            @Override
            public String getBodyContentType()
            {
                return "text/xml;charset=utf-8";
            }
        };
        queue.add(stringRequest);
        progressDialog = Query.showProgressDialog(SyncActivity.this, Constraints.Downaloding);
    }
    private void volleySyncDiscount(Context context,final String str) {
        volleyUpdatePayment(context);

        if (syncCompanyCode != null && syncCompanyCode.length() > 0) {
            RequestQueue queue = Volley.newRequestQueue(context);


            final String finalCompany_code = syncCompanyCode;

            String disCreateID = "0";
            String disCreateNick = "0";
            String disCreateValue = "0";
            String disCreateButtonName = "0";
            String disCreatePrintOnReceipt = "0";
            String disCreateFull = "";
            String disCreateItemDisc = "Y";
            String disCreateGroupDisc = "Y";
            String disCreateDiscType = "0";
            String disCreateDiscAmount = "0";
            String disCreateOpenDisc = "N";
            String disCreateDisplay = "Y";
            JSONArray mJsonArray = new JSONArray();
            try {
//            A = amount
//            P = Percentage

                Cursor cdis = Query.GetDiscount();
                if (cdis != null) {
                    while (cdis.moveToNext()) {
                        final JSONObject jsonObject = new JSONObject();
//                Integer ID, String uUID, String nick, String name, String type, String discountType,
//                        String discountDate, Integer value, String option, String promoTypeID,
//                        String billDiscount, String amountDiscount, String serviceChargeDiscount,
//                        Integer seq, Integer dt
                        String disuuid = cdis.getString(0);
                        String disname = cdis.getString(1);
                        Double disuvalue = cdis.getDouble(2);
                        String distype = cdis.getString(3);
//                    Discount dis = new Discount(0,disuuid,disname,"","","","",disuvalue,"",
//                            "","","","",0,0);

                        jsonObject.put("ID", disuuid);
                        jsonObject.put("Nick", disname);
                        jsonObject.put("Value", disname);
                        jsonObject.put("ButtonName", disname);
                        jsonObject.put("PrintOnReceipt", disname);
                        jsonObject.put("Full", disCreateFull);
                        jsonObject.put("ItemDisc", disCreateItemDisc);
                        jsonObject.put("GroupDisc", disCreateGroupDisc);

                        if (distype != null && distype.length() > 0) {
                            if (distype.equals("$ Dollar Value")) {
                                disCreateDiscType = "A";
                            } else {
                                disCreateDiscType = "P";
                            }
                            jsonObject.put("DiscType", disCreateDiscType);

                        } else {
                            jsonObject.put("DiscType", disCreateDiscType);
                        }

                        if (disuvalue != null && Double.valueOf(disuvalue) != 0.0) {
                            jsonObject.put("DiscAmount", disuvalue);
                        } else {
                            jsonObject.put("DiscAmount", "0.00");
                        }

                        jsonObject.put("OpenDisc", disCreateOpenDisc);
                        jsonObject.put("Display", disCreateDisplay);

                        mJsonArray.put(jsonObject);
                    }
                    cdis.close();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            String temp = Query.CreateDiscount(finalCompany_code, mJsonArray);
            //Log.i("Dfdf__temp","temp___"+temp);
            String createDiscountResult = Query.StringRequestQueue(context,queue, syncUrl, "CreateDiscountResult", temp, "", "", "", "CreateDiscount");
            //Log.i("createDiscountResultp","createDiscountResult__"+createDiscountResult);
            if (createDiscountResult.equals("Success")) {
                success(SyncActivity.this);
            }
        }
    }
//    private void volleyGetUsersList(final String str) {
//        volleyUpdatePayment(context);
//
//        RequestQueue queue = Volley.newRequestQueue(context);
//
//        final String finalCompany_code = syncCompanyCode;
//
//        String temp = Query.GetUsersList(finalCompany_code);
//
//        String getUsersList = Query.StringRequestQueue(queue,syncUrl,"GetUsersListResult",
//                temp,"","","","GetUsersList");
//        if (getUsersList.equals("Success")){
//            success(SyncActivity.this);
//        }
//    }
//    private void GetUsersPermissionList(final String str) {
//        volleyUpdatePayment(context);
//
//        RequestQueue queue = Volley.newRequestQueue(context);
//
//        final String finalCompany_code = syncCompanyCode;
//
//        String sl_id = "";
//        String userid = "";
//        String temp = Query.GetUsersPermission(finalCompany_code,sl_id, userid);
//        String GetUsersPermission = Query.StringRequestQueue(queue,syncUrl,"GetUsersPermissionResult",
//                temp,"","","","GetUsersPermission");
//        if (GetUsersPermission.equals("Success")){
//            success(SyncActivity.this);
//        }
//    }
    public static void SyncImageUpload(Context context,String base64ImgStr,String imgFileName,String imgType,String itemID,String imgeUrl) {
        GetSyncDataInformationAll();
        volleyUpdatePayment(context);
        if (syncCompanyCode != null && syncCompanyCode.length() > 0) {
            RequestQueue queue = Volley.newRequestQueue(context);

            final String finalCompany_code = syncCompanyCode;

            String isDepartment = "N";
            String isItem = "Y";
            String itemDepartment = "";

            String temp = Query.ImageUpload(finalCompany_code, base64ImgStr, imgFileName, imgType, isDepartment,
                    isItem, itemDepartment, itemID, imgeUrl);

            String ImageUploadResult = Query.StringRequestQueue(context,queue, syncUrl, "ImageUploadResult",
                    temp, "", "", "", "ImageUploadResult");
            if (ImageUploadResult.equals("Success")) {
                success(context);
            }
        }
    }
    private void volleyGetDiscount(Context context,final String str) {
        volleyUpdatePayment(context);
        GetSyncDataInformationAll();
        RequestQueue queue = Volley.newRequestQueue(context);

        final String finalCompany_code = syncCompanyCode;

        String temp = Query.GetDiscount(finalCompany_code);

        String getDiscountResult = Query.StringRequestQueue(context,queue,syncUrl,"GetDiscountListResult",temp,"","","","GetDiscountList");
        if (getDiscountResult.equals("Success")){
            success(SyncActivity.this);
        }
    }

    private void GetAllUserAccessRightDownloadDialog(Context context) {
        Cursor Cursor_Possys = Query.GetURLAndCodeFromPossys();
        if (Cursor_Possys != null) {
            while (Cursor_Possys.moveToNext()) {
                syncRetailID = Cursor_Possys.getString(0);
                syncCompanyCode = Cursor_Possys.getString(1);
                syncUrl = Cursor_Possys.getString(2);
                download_retail_ID = Cursor_Possys.getString(3);
                download_company_code = Cursor_Possys.getString(4);
                download_url = Cursor_Possys.getString(5);
//                download_userlogin = Cursor_Possys.getString(13);
//                download_userpassword = Cursor_Possys.getString(14);
            }
            Cursor_Possys.close();
        }

        //final String[] blnaccess = {"0"};
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, download_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //progressDialog.dismiss();
                        // response code
                        String xmlString = response;


                        Document xmlparse  = null;
                        Document parse = APIActivity.XMLParseFunction(xmlString, xmlparse);
                        for (int i=0;i< parse.getElementsByTagName("GetAllUserAccessRightResponse").getLength();i++) {
                            GetAllUserAccessRightResult = (parse.getElementsByTagName("GetAllUserAccessRightResponse").getLength() > 0)
                                    ? parse.getElementsByTagName("GetAllUserAccessRightResponse").item(i).getTextContent() : " ";
                        }

                        //displayPopupDialogQRScan(getInventoryResult,getInventoryResult);

                        try {

                            //JSONObject obj = new JSONObject(getUserAccessResult);
                            //String inv_obj = obj.getString("users");
                            JSONArray mJsonArray = new JSONArray(GetAllUserAccessRightResult);

                            JSONObject inventoryObject = null;

                            String userlogin = "";
                            String userpass = "";
                            String SLCode = "";
                            String SLName = "";
                            String accessable_ = "";

//                            RemarkMainActivity.SLNameArr.clear();
//                            RemarkMainActivity.SLAccessableArr.clear();

                            //RemarkMainActivity.hashValuesSLCode.clear();
                            String deleteExistingUserAccess = "DELETE FROM UserAccess";

                            DBFunc.ExecQuery(deleteExistingUserAccess,true);

                            for (int i =0; i < mJsonArray.length(); i++){
                                inventoryObject = mJsonArray.getJSONObject(i);

//                                {"userlogin":"001","userpass":"001001","slcode":"M0022","slname":"Z-Reports","accessable":1},
                                userlogin = inventoryObject.getString("userlogin");
                                userpass = inventoryObject.getString("userpass");
                                SLCode = inventoryObject.getString("slcode");
                                SLName = inventoryObject.getString("slname");
                                accessable_ = inventoryObject.getString("accessable");

                                UserAccess userAccess = new UserAccess(0,userlogin,userpass,SLCode,SLName,accessable_,0);
                                Query.SaveUserAccess(userAccess);

                            }

                            progressDialog.dismiss();

                            success(context);

                            RemarkMainActivity.userid = "";
                            RemarkMainActivity.userpassword = "";

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

//                        // As of f605da3 the following should work
                progressDialog.dismiss();
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
        })
        {
            @Override
            public Map<String, String> getParams(){
                return null;
            }
            @Override
            public byte[] getBody(){
                String temp = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                        "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
                        "  <soap12:Body>\n" +
                        "    <GetAllUserAccessRight xmlns=\"http://tempuri.org/\">\n" +
                        "      <companyCode>"+download_company_code+"</companyCode>\n" +
                        "    </GetAllUserAccessRight>\n" +
                        "  </soap12:Body>\n" +
                        "</soap12:Envelope>";
                Log.i("APP", "request String: " + temp);
                byte[] b = temp.getBytes(Charset.forName("UTF-8"));

                return b;
            }
            @Override
            public String getBodyContentType()
            {
                return "text/xml;charset=utf-8";
            }
        };
        queue.add(stringRequest);
        progressDialog = Query.showProgressDialog(context, Constraints.Downaloding);
    }

    private void UserAccessDownloadDialog(Context context) {
        binding.container.setAlpha(0.4F);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View popupView = inflater.inflate(R.layout.activity_user_access_pop_up, null);

        final PopupWindow popupWindow = new PopupWindow(popupView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        LinearLayout lvclose = (LinearLayout) popupView.findViewById(R.id.linear_close) ;


        EditText pop_up_window_user = (EditText) popupView.findViewById(R.id.pop_up_window_user) ;
        EditText pop_up_window_password = (EditText) popupView.findViewById(R.id.pop_up_window_password) ;

        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.update();
//        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
//        popupWindow.showAsDropDown(popupView, 0, 0);
        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);

        Button btn_add_pop_up_window_download = (Button) popupView.findViewById(R.id.btn_add_pop_up_window_download);
        btn_add_pop_up_window_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                popupWindow.dismiss();

                volleyCheckPermission(context,pop_up_window_user.getText().toString(),pop_up_window_password.getText().toString());

                binding.container.setAlpha(1);

            }
        });
        //Button btn_cancel_pop_up_window_itemremarks = (Button) popupView.findViewById(R.id.btn_cancel_pop_up_window_itemremarks) ;
        Button btn_cancel_pop_up_window_cancel = (Button) popupView.findViewById(R.id.btn_cancel_pop_up_window_cancel) ;
        btn_cancel_pop_up_window_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                binding.container.setAlpha(1);
            }
        });
        lvclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                //onResume();
                binding.container.setAlpha(1);
            }
        });
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Log.i("dsfsdf___","___onDismiss");
                //Do Something here
                binding.container.setAlpha(1);
            }
        });
    }

    private void volleyGetInventory(final String str) {
        Cursor Cursor_Possys = Query.GetURLAndCodeFromPossys();
        if (Cursor_Possys != null) {
            while (Cursor_Possys.moveToNext()) {
                syncRetailID = Cursor_Possys.getString(0);
                syncCompanyCode = Cursor_Possys.getString(1);
                syncUrl = Cursor_Possys.getString(2);
                download_retail_ID = Cursor_Possys.getString(3);
                download_company_code = Cursor_Possys.getString(4);
                download_url = Cursor_Possys.getString(5);
            }
            Cursor_Possys.close();
        }

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, download_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //progressDialog.dismiss();
                        // response code
                        String xmlString = response;
                        Log.i("Sdf___","response___"+response);
                        Document xmlparse  = null;
                        Document parse = APIActivity.XMLParseFunction(xmlString, xmlparse);
//                        for (int i=0;i< parse.getElementsByTagName("getInventoryResult").getLength();i++) {
//                            getInventoryResult = (parse.getElementsByTagName("getInventoryResult").getLength() > 0)
//                                    ? parse.getElementsByTagName("getInventoryResult").item(i).getTextContent() : " ";
//                        }
                        for (int i=0;i< parse.getElementsByTagName("getInventoryResponse").getLength();i++) {
                            getInventoryResult = (parse.getElementsByTagName("getInventoryResponse").getLength() > 0)
                                    ? parse.getElementsByTagName("getInventoryResponse").item(i).getTextContent() : " ";
                        }

                        //displayPopupDialogQRScan(getInventoryResult,getInventoryResult);

                        try {


                            JSONObject obj = new JSONObject(getInventoryResult);
                            String inv_obj = obj.getString("inventory");
                            Log.i("Sdf___","reinv_obj___"+inv_obj);
                            JSONArray mJsonArray = new JSONArray(inv_obj);

                            JSONObject inventoryObject = null;
                            //Log.i("mJsonArrayLength:", String.valueOf(mJsonArray.length()));
                            //DBFunc.ExecQuery("DELETE FROM Inventory", true);
                            DBFunc.ExecQuery("DELETE FROM Inventory", true);
                            DBFunc.ExecQuery("DELETE FROM PLU", true);
                            DBFunc.ExecQuery("DELETE FROM StockAgent", true);
                            DBFunc.ExecQuery("DELETE FROM StockAdjustment", true);
                            DBFunc.ExecQuery("DELETE FROM StockIn", true);
                            DBFunc.ExecQuery("DELETE FROM StockOut", true);
                            DBFunc.ExecQuery("DELETE FROM StockTake", true);

                            for (int i =0; i < mJsonArray.length(); i++){
                                inventoryObject = mJsonArray.getJSONObject(i);

                                ItemID = inventoryObject.getString("ItemID");
                                ItemSKU = inventoryObject.getString("ItemSKU");
                                ItemImage = inventoryObject.getString("ItemImage");
                                ItemDescp = inventoryObject.getString("ItemDescp");
                                SupBarCode = inventoryObject.getString("SupBarCode");
                                UOM = inventoryObject.getString("UOM");
                                Price = inventoryObject.getDouble("Price");
                                OnHandQty = inventoryObject.getString("OnHandQty");

                                if (inventoryObject.has("Department")) {
                                    Department = inventoryObject.getString("Department");
                                }else{
                                    Department = "";
                                }
                                String cat_uuid = "0";
                                String cat_name = "";
                                if (inventoryObject.has("Category")) {
                                    Category = inventoryObject.getString("Category");
                                    try {
                                        cat_uuid = Query.findfieldNameById("ID", "UUID", Category , "Category", true);
                                        cat_name = Query.findfieldNameById("Name", "ID", cat_uuid , "Category", true);

                                    }catch (NullPointerException e){
                                        cat_uuid = "0";
                                        cat_name = "";
                                    }
                                }else{
                                    Category = "";
                                }
                                if (inventoryObject.has("ItemRemark")) {
                                    ItemRemark = inventoryObject.getString("ItemRemark");
                                }else{
                                    ItemRemark = "";
                                }
                                if (inventoryObject.has("OpenPrice")) {
                                    OpenPrice = inventoryObject.getString("OpenPrice");
                                }else{
                                    OpenPrice = "";
                                }
                                if (inventoryObject.has("ItemOpenPrice")) {
                                    OpenPrice = inventoryObject.getString("ItemOpenPrice");
                                }else{
                                    OpenPrice = "";
                                }

                                if (OpenPrice.equals("N")){
                                    option = "0000";
                                }else{
                                    option = "0100";
                                }

                                if (inventoryObject.has("GST")) {
                                    GST = inventoryObject.getString("GST");
                                }else{
                                    GST = "";
                                }
                                if (inventoryObject.has("GSTCode")) {
                                    GSTCode = inventoryObject.getString("GSTCode");
                                }else{
                                    GSTCode = "";
                                }
                                if (inventoryObject.has("GSTRate")) {
                                    GSTRate = inventoryObject.getString("GSTRate");
                                }else{
                                    GSTRate = "";
                                }
                                if (inventoryObject.has("GSTIncExc")) {
                                    GSTIncExc = inventoryObject.getString("GSTIncExc");
                                }else{
                                    GSTIncExc = "";
                                }

                                String image = "";
                                if (ItemImage != null && ItemImage.length() > 0) {
                                    image = DBFunc.PurifyString("http://" + ItemImage);
                                }else {
                                    image = "";
                                }
//                                try {

                                String itmRemark_edit = "0";
                                if (ItemRemark !=null && Integer.parseInt(ItemRemark) == 1){
                                    itmRemark_edit = "1";
                                }else {
                                    itmRemark_edit = "0";
                                }

                                String openPrice_edit = "0";
                                if (OpenPrice !=null && Integer.parseInt(OpenPrice) == 1){
                                    openPrice_edit = "1";
                                }else {
                                    openPrice_edit = "0";
                                }

                                Integer catid = 0;
                                if (cat_uuid !=null && Integer.parseInt(cat_uuid) > 0){
                                    catid = Integer.parseInt(cat_uuid);
                                }else {
                                    catid = 0;
                                }

                                Double pprice = 0.0;
                                if (Price !=null && Double.valueOf(Price) > 0.0){
                                    pprice = Price;
                                }else {
                                    pprice = 0.0;
                                }
//                                    {"ItemID":"c97cde40-33ed-11eb-8b1f-00155d01ca02","ItemSKU":"16A517E8",
//                                            "ItemDescp":"Yony - Light Gray","SupBarCode":"16A517E8","UOM":"PCS","Price":57.15,
//                                            "OnHandQty":0,"ItemOtherLanguage":"","ItemImage":"",
//                                            "Department":"fcabd143-2f21-11eb-be45-00155d01ca02",
//                                            "Category":"fca99d56-2f21-11eb-be45-00155d01ca02"}
//
//                                    {"ItemID":"c97efc2e-33ed-11eb-8b1f-00155d01ca02","ItemSKU":"E92025CF",
//                                            "ItemDescp":"Character Wappen  Gwi-Yeop","SupBarCode":"E92025CF","UOM":"PCS","Price":29.4,
//                                            "OnHandQty":0,"ItemOtherLanguage":"","ItemImage":""}



                                try {
//                                    Query.SavePLU(ItemDescp, ItemID, String.valueOf(pprice), ItemSKU, image, "0",
//                                            "", "", "", "", "",
//                                            "0", openPrice_edit,"0",
//                                            catid, cat_name, 0, "0", OnHandQty,"Sync");
                                    Query.SavePLU(ItemDescp, ItemID, String.valueOf(pprice), ItemSKU, image, "0",
                                            "", "", "", "", "",
                                            "0", openPrice_edit,itmRemark_edit,
                                            catid, cat_name, 0, "0", OnHandQty,"Sync");
                                }catch (NumberFormatException e) {
                                    Log.i("Error__","ErrorSmile__"+e.getMessage());
                                }
//                                }catch (NumberFormatException e){
//                                    Log.i("Error__","ErrorSmile__"+e.getMessage());
//                                }



                            }

                            progressDialog.dismiss();

                            success(context);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

//                        // As of f605da3 the following should work
                progressDialog.dismiss();
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
        })
        {
            @Override
            public Map<String, String> getParams(){
                return null;
            }
            @Override
            public byte[] getBody(){
                String temp = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                        "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
                        "  <soap12:Body>\n" +
                        "    <getInventory xmlns=\"http://tempuri.org/\">\n" +
                        "      <companyCode>"+download_company_code+"</companyCode>\n" +
                        "      <retailID>"+download_retail_ID+"</retailID>\n" +
                        "    </getInventory>\n" +
                        "  </soap12:Body>\n" +
                        "</soap12:Envelope>";
                Log.i("APP", "request String: " + temp);
                byte[] b = temp.getBytes(Charset.forName("UTF-8"));

                return b;
            }
            @Override
            public String getBodyContentType()
            {
                return "text/xml;charset=utf-8";
            }
        };
        queue.add(stringRequest);
        progressDialog = Query.showProgressDialog(SyncActivity.this, Constraints.Downaloding);
    }

//    public static String volleyCheckPermission(Context context,String status,String accessable,
//                                               String syncRetailID,
//                                               String syncCompanyCode,String syncUrl,String download_retail_ID,String download_company_code,
//                                               String download_url,String download_userlogin,String download_userpassword) {
    public static void volleyCheckPermission(Context context,String user,String password) {
//    public static String volleyCheckPermission(Context context,String status,String accessablee) {
//    public static void volleyCheckPermission(Context context,String status,String accessable) {

        Cursor Cursor_Possys = Query.GetURLAndCodeFromPossys();
        if (Cursor_Possys != null) {
            while (Cursor_Possys.moveToNext()) {
                syncRetailID = Cursor_Possys.getString(0);
                syncCompanyCode = Cursor_Possys.getString(1);
                syncUrl = Cursor_Possys.getString(2);
                download_retail_ID = Cursor_Possys.getString(3);
                download_company_code = Cursor_Possys.getString(4);
                download_url = Cursor_Possys.getString(5);
//                download_userlogin = Cursor_Possys.getString(13);
//                download_userpassword = Cursor_Possys.getString(14);
            }
            Cursor_Possys.close();
        }

        //final String[] blnaccess = {"0"};
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, download_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //progressDialog.dismiss();
                        // response code
                        String xmlString = response;


                        Document xmlparse  = null;
                        Document parse = APIActivity.XMLParseFunction(xmlString, xmlparse);
                        for (int i=0;i< parse.getElementsByTagName("GetUserAccessResponse").getLength();i++) {
                            getUserAccessResult = (parse.getElementsByTagName("GetUserAccessResponse").getLength() > 0)
                                    ? parse.getElementsByTagName("GetUserAccessResponse").item(i).getTextContent() : " ";
                        }

                        //displayPopupDialogQRScan(getInventoryResult,getInventoryResult);

                        try {

                            JSONObject obj = new JSONObject(getUserAccessResult);
                            String inv_obj = obj.getString("users");
                            JSONArray mJsonArray = new JSONArray(inv_obj);

                            JSONObject inventoryObject = null;

                            String SLCode = "";
                            String SLName = "";
                            String accessable_ = "";

//                            RemarkMainActivity.SLNameArr.clear();
//                            RemarkMainActivity.SLAccessableArr.clear();

                            //RemarkMainActivity.hashValuesSLCode.clear();
                            String deleteExistingUserAccess = "DELETE FROM UserAccess";

                            DBFunc.ExecQuery(deleteExistingUserAccess,true);

                            for (int i =0; i < mJsonArray.length(); i++){
                                inventoryObject = mJsonArray.getJSONObject(i);

                                SLCode = inventoryObject.getString("SLCode");
                                SLName = inventoryObject.getString("SLName");
                                accessable_ = inventoryObject.getString("accessable");

                                UserAccess userAccess = new UserAccess(0,user,password,SLCode,SLName,accessable_,0);
                                Query.SaveUserAccess(userAccess);

                            }

                            progressDialog.dismiss();

                            success(context);

                            RemarkMainActivity.userid = "";
                            RemarkMainActivity.userpassword = "";

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

//                        // As of f605da3 the following should work
                progressDialog.dismiss();
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
        })
        {
            @Override
            public Map<String, String> getParams(){
                return null;
            }
            @Override
            public byte[] getBody(){
                String temp = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                        "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
                        "  <soap12:Body>\n" +
                        "    <GetUserAccess xmlns=\"http://tempuri.org/\">\n" +
                        "      <companyCode>"+download_company_code+"</companyCode>\n" +
                        "      <UserLogin>"+user+"</UserLogin>\n" +
                        "      <UserPassword>"+password+"</UserPassword>\n" +
                        "    </GetUserAccess>\n" +
                        "  </soap12:Body>\n" +
                        "</soap12:Envelope>";
                Log.i("APP", "request String: " + temp);
                byte[] b = temp.getBytes(Charset.forName("UTF-8"));

                return b;
            }
            @Override
            public String getBodyContentType()
            {
                return "text/xml;charset=utf-8";
            }
        };
        queue.add(stringRequest);
        progressDialog = Query.showProgressDialog(context, Constraints.Downaloding);

//        return blnaccess[0];
    }

    public static void success(final Context context) {
//        final DialogBox dlg1 = new DialogBox(ActivityGetDataFromMyRetailer.this);
//        dlg1.setTitle("Success");
//        dlg1.setMessage("Success");
//        dlg1.show();
        try {
            ((Activity) context).runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    DialogBox dlg1 = new DialogBox(context);
                    dlg1.setTitle("Success");
                    dlg1.setDialogIconType(DialogBox.IconType.INFORMATION);
                    dlg1.setMessage("Success");
                    dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                    dlg1.show();
                }
            });
        } catch (Exception e){

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void warning(String str) {
        DialogBox dlg1 = new DialogBox(SyncActivity.this);
        dlg1.setTitle("Require "+str);
        dlg1.setDialogIconType(DialogBox.IconType.INFORMATION);
        dlg1.setMessage("Require "+str);
        dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
        dlg1.show();
    }
    private void volleyGetPromotion() {
        Cursor Cursor_Possys = Query.GetURLAndCodeFromPossys();
        if (Cursor_Possys != null) {
            while (Cursor_Possys.moveToNext()) {
                syncRetailID = Cursor_Possys.getString(0);
                syncCompanyCode = Cursor_Possys.getString(1);
                syncUrl = Cursor_Possys.getString(2);
                download_retail_ID = Cursor_Possys.getString(3);
                download_company_code = Cursor_Possys.getString(4);
                download_url = Cursor_Possys.getString(5);
            }
            Cursor_Possys.close();
        }
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, download_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        success(context);
                        // response code
                        String xmlString = response;
                        Document xmlparse  = null;
                        Document parse = APIActivity.XMLParseFunction(xmlString, xmlparse);
                        for (int i=0;i< parse.getElementsByTagName("getPromotionResponse").getLength();i++) {
                            getPromotionResponse = (parse.getElementsByTagName("getPromotionResponse").getLength() > 0)
                                    ? parse.getElementsByTagName("getPromotionResponse").item(i).getTextContent() : " ";
                        }

                        JSONArray mJsonArray = null;
                        try {
                            mJsonArray = new JSONArray(getPromotionResponse);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (mJsonArray == null)
                            return;

                        DBFunc.ExecQuery("DELETE FROM promo", true);
                        DBFunc.ExecQuery("DELETE FROM Promotype", true);
                        DBFunc.ExecQuery("DELETE FROM promo_Item", true);

                        JSONObject promoObject = null;
                        JSONObject promoItemObject = null;
                        JSONObject promoMixMatchObject = null;

                        for (int i=0; i < mJsonArray.length(); i++){
                            try {
                                promoObject = mJsonArray.getJSONObject(i);

                                PromoID = promoObject.getString("PromoID");
                                PromoName = promoObject.getString("PromoName");
                                Promo_Type_ID = promoObject.getString("Promo_Type_ID");
                                Promo_TypeCode = promoObject.getString("Promo_TypeCode");
                                Promo_DateFrom = promoObject.getString("Promo_DateFrom");
                                Promo_DateTo = promoObject.getString("Promo_DateTo");

                                DBFunc.ExecQuery("INSERT INTO promo (PromoID,Promo_Name,Promo_Type,Promo_DateFrom,Promo_DateTo) " +
                                        "VALUES ('"+DBFunc.PurifyString(PromoID)+"','"+DBFunc.PurifyString(PromoName)+"', "
                                        +"'"+DBFunc.PurifyString(Promo_Type_ID).toLowerCase().trim()+"', "
                                        +"'"+DBFunc.PurifyString(Promo_DateFrom).toLowerCase().trim()+"', "
                                        +"'"+DBFunc.PurifyString(Promo_DateTo).toLowerCase().trim()+"')", true);

                                DBFunc.ExecQuery("INSERT INTO Promotype (Promo_TypeID,Promo_TypeCode) " +
                                        "VALUES ('"+DBFunc.PurifyString(Promo_Type_ID)+"',"+
                                        "'"+DBFunc.PurifyString(Promo_TypeCode).toLowerCase().trim()+"')", true);

                                PromoItems = promoObject.getString("PromoItems");
                                JSONArray jsonArrayPromoItems = new JSONArray(PromoItems);

                                for (int j=0; j < jsonArrayPromoItems.length(); j++){
                                    promoItemObject = jsonArrayPromoItems.getJSONObject(j);

                                    promoItemID = promoItemObject.getString("ItemID");
                                    promoItemUOM = promoItemObject.getString("ItemUOM");
                                    promoItem_UnitID = promoItemObject.getString("Item_UnitID");
                                    promoItemPrice = promoItemObject.getString("ItemPrice");
                                    promoItemMemberPrice = promoItemObject.getString("ItemMemberPrice");
                                    promoItemUnit = promoItemObject.getString("ItemUnit");
                                    promoItem_Qty = promoItemObject.getString("Item_Qty");
                                    promoItem_Amt = promoItemObject.getString("Item_Amt");
                                    promoItem_Percentage = promoItemObject.getString("Item_Percentage");
                                    promoItem_MemberAmt = promoItemObject.getString("Item_MemberAmt");
                                    promoItem_MemberPerc = promoItemObject.getString("Item_MemberPerc");
                                    promoItem_Qty2 = promoItemObject.getString("Item_Qty2");
                                    promoItem_Amt2 = promoItemObject.getString("Item_Amt2");
                                    promoItem_Percentage2 = promoItemObject.getString("Item_Percentage2");
                                    promoItem_MemberAmt2 = promoItemObject.getString("Item_MemberAmt2");
                                    promoItem_MemberPerc2 = promoItemObject.getString("Item_MemberPerc2");
                                    promoItem_Qty3 = promoItemObject.getString("Item_Qty3");
                                    promoItem_Amt3 = promoItemObject.getString("Item_Amt3");
                                    promoItem_Percentage3 = promoItemObject.getString("Item_Percentage3");
                                    promoItem_MemberAmt3 = promoItemObject.getString("Item_MemberAmt3");
                                    promoItem_MemberPerc3 = promoItemObject.getString("Item_MemberPerc3");
                                    promoItem_Qty4 = promoItemObject.getString("Item_Qty4");
                                    promoItem_Amt4 = promoItemObject.getString("Item_Amt4");
                                    promoItem_Percentage4 = promoItemObject.getString("Item_Percentage4");
                                    promoItem_MemberAmt4 = promoItemObject.getString("Item_MemberAmt4");
                                    promoItem_MemberPerc4 = promoItemObject.getString("Item_MemberPerc4");
                                    promoItem_Qty5 = promoItemObject.getString("Item_Qty5");
                                    promoItem_Amt5 = promoItemObject.getString("Item_Amt5");
                                    promoItem_Percentage5 = promoItemObject.getString("Item_Percentage5");
                                    promoItem_MemberAmt5 = promoItemObject.getString("Item_MemberAmt5");
                                    promoItem_MemberPerc5 = promoItemObject.getString("Item_MemberPerc5");
                                    promoItem_Qty6 = promoItemObject.getString("Item_Qty6");
                                    promoItem_Amt6 = promoItemObject.getString("Item_Amt6");
                                    promoItem_Percentage6 = promoItemObject.getString("Item_Percentage6");
                                    promoItem_MemberAmt6 = promoItemObject.getString("Item_MemberAmt6");
                                    promoItem_MemberPerc6 = promoItemObject.getString("Item_MemberPerc6");
                                    promoItem_Qty7 = promoItemObject.getString("Item_Qty7");
                                    promoItem_Amt7 = promoItemObject.getString("Item_Amt7");
                                    promoItem_Percentage7 = promoItemObject.getString("Item_Percentage7");
                                    promoItem_MemberAmt7 = promoItemObject.getString("Item_MemberAmt7");
                                    promoItem_MemberPerc7 = promoItemObject.getString("Item_MemberPerc7");
                                    promoItem_Qty8 = promoItemObject.getString("Item_Qty8");
                                    promoItem_Amt8 = promoItemObject.getString("Item_Amt8");
                                    promoItem_Percentage8 = promoItemObject.getString("Item_Percentage8");
                                    promoItem_MemberAmt8 = promoItemObject.getString("Item_MemberAmt8");
                                    promoItem_MemberPerc8 = promoItemObject.getString("Item_MemberPerc8");
                                    promoItem_Qty9 = promoItemObject.getString("Item_Qty9");
                                    promoItem_Amt9 = promoItemObject.getString("Item_Amt9");
                                    promoItem_Percentage9 = promoItemObject.getString("Item_Percentage9");
                                    promoItem_MemberAmt9 = promoItemObject.getString("Item_MemberAmt9");
                                    promoItem_MemberPerc9 = promoItemObject.getString("Item_MemberPerc9");
                                    promoItem_Qty10 = promoItemObject.getString("Item_Qty10");
                                    promoItem_Amt10 = promoItemObject.getString("Item_Amt10");
                                    promoItem_Percentage10 = promoItemObject.getString("Item_Percentage10");
                                    promoItem_MemberAmt10 = promoItemObject.getString("Item_MemberAmt10");
                                    promoItem_MemberPerc10 = promoItemObject.getString("Item_MemberPerc10");

                                    String insertql = "INSERT INTO promo_item (PromoID,ItemID,ItemUOM,Item_UnitID,ItemPrice," +
                                            "ItemMemberPrice,ItemUnit,ItemMemberPrice,ItemUnit,Item_Qty," +
                                            "Item_Amt,Item_Percentage,Item_MemberAmt,Item_MemberPerc,Item_Qty2," +
                                            "Item_Amt2,Item_Percentage2,Item_MemberAmt2,Item_MemberPerc2," +
                                            "Item_Qty3,Item_Amt3,Item_Percentage3,Item_MemberAmt3,Item_MemberPerc3," +
                                            "Item_Qty4,Item_Amt4,Item_Percentage4,Item_MemberAmt4,Item_MemberPerc4," +
                                            "Item_Qty5,Item_Amt5,Item_Percentage5,Item_MemberAmt5,Item_MemberPerc5," +
                                            "Item_Qty6,Item_Amt6,Item_Percentage6,Item_MemberAmt6,Item_MemberPerc6," +
                                            "Item_Qty7,Item_Amt7,Item_Percentage7,Item_MemberAmt7,Item_MemberPerc7," +
                                            "Item_Qty8,Item_Percentage8,Item_MemberAmt8,Item_MemberPerc8,Item_Qty9," +
                                            "Item_Amt9,Item_Percentage9,Item_MemberAmt9,Item_MemberPerc9,Item_Qty10," +
                                            "Item_Amt10,Item_Percentage10,Item_MemberAmt10,Item_MemberPerc10) " +
                                            "VALUES (" +
                                            "'"+DBFunc.PurifyString(PromoID)+"',"+
                                            "'"+DBFunc.PurifyString(promoItemID)+"',"+
                                            "'"+DBFunc.PurifyString(promoItemUOM)+"',"+
                                            "'"+DBFunc.PurifyString(promoItem_UnitID)+"',"+
                                            "'"+DBFunc.PurifyString(promoItemPrice)+"',"+
                                            "'"+DBFunc.PurifyString(promoItemMemberPrice)+"',"+
                                            "'"+DBFunc.PurifyString(promoItemUnit)+"',"+
                                            "'"+DBFunc.PurifyString(promoItemMemberPrice)+"',"+
                                            "'"+DBFunc.PurifyString(promoItemUnit)+"',"+
                                            "'"+DBFunc.PurifyString(promoItem_Qty)+"',"+
                                            "'"+DBFunc.PurifyString(promoItem_Amt)+"',"+
                                            "'"+DBFunc.PurifyString(promoItem_Percentage)+"',"+
                                            "'"+DBFunc.PurifyString(promoItem_MemberAmt)+"',"+
                                            "'"+DBFunc.PurifyString(promoItem_MemberPerc)+"',"+
                                            "'"+DBFunc.PurifyString(promoItem_Qty2)+"',"+
                                            "'"+DBFunc.PurifyString(promoItem_Amt2)+"',"+
                                            "'"+DBFunc.PurifyString(promoItem_Percentage2)+"',"+
                                            "'"+DBFunc.PurifyString(promoItem_MemberAmt2)+"',"+
                                            "'"+DBFunc.PurifyString(promoItem_MemberPerc2)+"',"+
                                            "'"+DBFunc.PurifyString(promoItem_Qty3)+"',"+
                                            "'"+DBFunc.PurifyString(promoItem_Amt3)+"',"+
                                            "'"+DBFunc.PurifyString(promoItem_Percentage3)+"',"+
                                            "'"+DBFunc.PurifyString(promoItem_MemberAmt3)+"',"+
                                            "'"+DBFunc.PurifyString(promoItem_MemberPerc3)+"',"+
                                            "'"+DBFunc.PurifyString(promoItem_Qty4)+"',"+
                                            "'"+DBFunc.PurifyString(promoItem_Amt4)+"',"+
                                            "'"+DBFunc.PurifyString(promoItem_Percentage4)+"',"+
                                            "'"+DBFunc.PurifyString(promoItem_MemberAmt4)+"',"+
                                            "'"+DBFunc.PurifyString(promoItem_MemberPerc4)+"',"+
                                            "'"+DBFunc.PurifyString(promoItem_Qty5)+"',"+
                                            "'"+DBFunc.PurifyString(promoItem_Amt5)+"',"+
                                            "'"+DBFunc.PurifyString(promoItem_Percentage5)+"',"+
                                            "'"+DBFunc.PurifyString(promoItem_MemberAmt5)+"',"+
                                            "'"+DBFunc.PurifyString(promoItem_MemberPerc5)+"',"+
                                            "'"+DBFunc.PurifyString(promoItem_Qty6)+"',"+
                                            "'"+DBFunc.PurifyString(promoItem_Amt6)+"',"+
                                            "'"+DBFunc.PurifyString(promoItem_Percentage6)+"',"+
                                            "'"+DBFunc.PurifyString(promoItem_MemberAmt6)+"',"+
                                            "'"+DBFunc.PurifyString(promoItem_MemberPerc6)+"',"+
                                            "'"+DBFunc.PurifyString(promoItem_Qty7)+"',"+
                                            "'"+DBFunc.PurifyString(promoItem_Amt7)+"',"+
                                            "'"+DBFunc.PurifyString(promoItem_Percentage7)+"',"+
                                            "'"+DBFunc.PurifyString(promoItem_MemberAmt7)+"',"+
                                            "'"+DBFunc.PurifyString(promoItem_MemberPerc7)+"',"+
                                            "'"+DBFunc.PurifyString(promoItem_Qty8)+"',"+
                                            "'"+DBFunc.PurifyString(promoItem_Percentage8)+"',"+
                                            "'"+DBFunc.PurifyString(promoItem_MemberAmt8)+"',"+
                                            "'"+DBFunc.PurifyString(promoItem_MemberPerc8)+"',"+
                                            "'"+DBFunc.PurifyString(promoItem_Qty9)+"',"+
                                            "'"+DBFunc.PurifyString(promoItem_Amt9)+"',"+
                                            "'"+DBFunc.PurifyString(promoItem_Percentage9)+"',"+
                                            "'"+DBFunc.PurifyString(promoItem_MemberAmt9)+"',"+
                                            "'"+DBFunc.PurifyString(promoItem_MemberPerc9)+"',"+
                                            "'"+DBFunc.PurifyString(promoItem_Qty10)+"',"+
                                            "'"+DBFunc.PurifyString(promoItem_Amt10)+"',"+
                                            "'"+DBFunc.PurifyString(promoItem_Percentage10)+"',"+
                                            "'"+DBFunc.PurifyString(promoItem_MemberAmt10)+"',"+
                                            "'"+DBFunc.PurifyString(promoItem_MemberPerc10)+"')";

                                    DBFunc.ExecQuery(insertql, true);
                                }
                                promoPromoMixMatch = promoObject.getString("PromoMixMatch");
                                JSONArray jsonArrayPromoMixMatch = new JSONArray(promoPromoMixMatch);
                                String Promo_MixID = "";
                                String Promo_Qty = "";
                                String Promo_Amount = "";
                                String Promo_MemberAmount = "";
                                for (int k=0; k < jsonArrayPromoMixMatch.length(); k++) {
                                    promoMixMatchObject = jsonArrayPromoMixMatch.getJSONObject(k);

                                    Promo_MixID = promoMixMatchObject.getString("Promo_MixID");
                                    Promo_Qty = promoMixMatchObject.getString("Promo_Qty");
                                    Promo_Amount = promoMixMatchObject.getString("Promo_Amount");
                                    Promo_MemberAmount = promoMixMatchObject.getString("Promo_MemberAmount");

                                    DBFunc.ExecQuery("INSERT INTO promo_mixmatch (PromoID,PromoMixID,Promo_Qty,Promo_Amount,Promo_MemberAmount) " +
                                            "VALUES ('"+DBFunc.PurifyString(Promo_Type_ID)+"',"+
                                            "'"+DBFunc.PurifyString(Promo_MixID).toLowerCase().trim()+"'," +
                                            "'"+DBFunc.PurifyString(Promo_Qty).toLowerCase().trim()+"'," +
                                            "'"+DBFunc.PurifyString(Promo_Amount).toLowerCase().trim()+"'," +
                                            "'"+DBFunc.PurifyString(Promo_MemberAmount).toLowerCase().trim()+"'" +
                                            " )", true);

                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        Cursor config_values_pt = DBFunc.Query("SELECT ID FROM Promotype ", true);
                        assert config_values_pt != null;
                        String promo_type_id = "";
                        while (config_values_pt.moveToNext()) {
                            promo_type_id = config_values_pt.getString(0);
                        }
                        DBFunc.ExecQuery("DELETE FROM Disc WHERE NAME = 'MM' ", true);
                        Cursor config_values_inv = DBFunc.Query("SELECT ID FROM Disc ", true);
                        assert config_values_inv != null;
                        Integer seq_id = 0;
                        while (config_values_inv.moveToNext()) {
                            seq_id = Integer.parseInt(config_values_inv.getString(0));
                        }

                        seq_id = seq_id + 1;
                        DBFunc.ExecQuery("INSERT INTO Disc(Name,Value,Option,Seq,PromoTypeID) VALUES('"+Promo_TypeCode+"', "+"0"+", '"+"0000"+"', "+seq_id+",'"+promo_type_id+"')", true);

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

                        //Log.i("dfsfres___res",res);
                        // Now you can use any deserializer to make sense of data
//                                JSONObject obj = new JSONObject(res);
                    } catch (UnsupportedEncodingException e1) {
                        // Couldn't properly decode data to string
                        e1.printStackTrace();
                        //Log.i("e1",e1.getMessage());
                    }
                }
            }
        })
        {
            @Override
            public Map<String, String> getParams(){
                return null;
            }

            @Override
            public byte[] getBody(){

                String temp = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                        "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
                        "  <soap12:Body>\n" +
                        "    <getPromotion xmlns=\"http://tempuri.org/\">\n" +
                        "      <companyCode>"+download_company_code+"</companyCode>\n" +
                        "    </getPromotion>\n" +
                        "  </soap12:Body>\n" +
                        "</soap12:Envelope>";
                //Log.i("APPsss", "request String: " + temp);
                byte[] b = temp.getBytes(Charset.forName("UTF-8"));

                return b;
            }
            @Override
            public String getBodyContentType()
            {
                return "text/xml;charset=utf-8";
            }
        };
        queue.add(stringRequest);



    }

    private void SavePromoItem(JSONObject promoItemObject) {
        try {
            promoItemID = promoItemObject.getString("ItemID");

            promoItemUOM = promoItemObject.getString("ItemUOM");
            promoItem_UnitID = promoItemObject.getString("Item_UnitID");
            promoItemPrice = promoItemObject.getString("ItemPrice");
            promoItemMemberPrice = promoItemObject.getString("ItemMemberPrice");
            promoItemUnit = promoItemObject.getString("ItemUnit");
            promoItem_Qty = promoItemObject.getString("Item_Qty");
            promoItem_Amt = promoItemObject.getString("Item_Amt");
            promoItem_Percentage = promoItemObject.getString("Item_Percentage");
            promoItem_MemberAmt = promoItemObject.getString("Item_MemberAmt");
            promoItem_MemberPerc = promoItemObject.getString("Item_MemberPerc");
            promoItem_Qty2 = promoItemObject.getString("Item_Qty2");
            promoItem_Amt2 = promoItemObject.getString("Item_Amt2");
            promoItem_Percentage2 = promoItemObject.getString("Item_Percentage2");
            promoItem_MemberAmt2 = promoItemObject.getString("Item_MemberAmt2");
            promoItem_MemberPerc2 = promoItemObject.getString("Item_MemberPerc2");
            promoItem_Qty3 = promoItemObject.getString("Item_Qty3");
            promoItem_Amt3 = promoItemObject.getString("Item_Amt3");
            promoItem_Percentage3 = promoItemObject.getString("Item_Percentage3");
            promoItem_MemberAmt3 = promoItemObject.getString("Item_MemberAmt3");
            promoItem_MemberPerc3 = promoItemObject.getString("Item_MemberPerc3");
            promoItem_Qty4 = promoItemObject.getString("Item_Qty4");
            promoItem_Amt4 = promoItemObject.getString("Item_Amt4");
            promoItem_Percentage4 = promoItemObject.getString("Item_Percentage4");
            promoItem_MemberAmt4 = promoItemObject.getString("Item_MemberAmt4");
            promoItem_MemberPerc4 = promoItemObject.getString("Item_MemberPerc4");
            promoItem_Qty5 = promoItemObject.getString("Item_Qty5");
            promoItem_Amt5 = promoItemObject.getString("Item_Amt5");
            promoItem_Percentage5 = promoItemObject.getString("Item_Percentage5");
            promoItem_MemberAmt5 = promoItemObject.getString("Item_MemberAmt5");
            promoItem_MemberPerc5 = promoItemObject.getString("Item_MemberPerc5");
            promoItem_Qty6 = promoItemObject.getString("Item_Qty6");
            promoItem_Amt6 = promoItemObject.getString("Item_Amt6");
            promoItem_Percentage6 = promoItemObject.getString("Item_Percentage6");
            promoItem_MemberAmt6 = promoItemObject.getString("Item_MemberAmt6");
            promoItem_MemberPerc6 = promoItemObject.getString("Item_MemberPerc6");
            promoItem_Qty7 = promoItemObject.getString("Item_Qty7");
            promoItem_Amt7 = promoItemObject.getString("Item_Amt7");
            promoItem_Percentage7 = promoItemObject.getString("Item_Percentage7");
            promoItem_MemberAmt7 = promoItemObject.getString("Item_MemberAmt7");
            promoItem_MemberPerc7 = promoItemObject.getString("Item_MemberPerc7");
            promoItem_Qty8 = promoItemObject.getString("Item_Qty8");
            promoItem_Amt8 = promoItemObject.getString("Item_Amt8");
            promoItem_Percentage8 = promoItemObject.getString("Item_Percentage8");
            promoItem_MemberAmt8 = promoItemObject.getString("Item_MemberAmt8");
            promoItem_MemberPerc8 = promoItemObject.getString("Item_MemberPerc8");
            promoItem_Qty9 = promoItemObject.getString("Item_Qty9");
            promoItem_Amt9 = promoItemObject.getString("Item_Amt9");
            promoItem_Percentage9 = promoItemObject.getString("Item_Percentage9");
            promoItem_MemberAmt9 = promoItemObject.getString("Item_MemberAmt9");
            promoItem_MemberPerc9 = promoItemObject.getString("Item_MemberPerc9");
            promoItem_Qty10 = promoItemObject.getString("Item_Qty10");
            promoItem_Amt10 = promoItemObject.getString("Item_Amt10");
            promoItem_Percentage10 = promoItemObject.getString("Item_Percentage10");
            promoItem_MemberAmt10 = promoItemObject.getString("Item_MemberAmt10");
            promoItem_MemberPerc10 = promoItemObject.getString("Item_MemberPerc10");


            DBFunc.ExecQuery("INSERT INTO Promo_Item (PromoID,ItemID,ItemUOM,Item_UnitID,ItemPrice," +
                    "ItemMemberPrice,ItemUnit,ItemMemberPrice,ItemUnit,Item_Qty," +
                    "Item_Amt,Item_Percentage,Item_MemberAmt,Item_MemberPerc,Item_Qty2," +
                    "Item_Amt2,Item_Percentage2,Item_MemberAmt2,Item_MemberPerc2," +
                    "Item_Qty3,Item_Amt3,Item_Percentage3,Item_MemberAmt3,Item_MemberPerc3," +
                    "Item_Qty4,Item_Amt4,Item_Percentage4,Item_MemberAmt4,Item_MemberPerc4," +
                    "Item_Qty5,Item_Amt5,Item_Percentage5,Item_MemberAmt5,Item_MemberPerc5," +
                    "Item_Qty6,Item_Amt6,Item_Percentage6,Item_MemberAmt6,Item_MemberPerc6," +
                    "Item_Qty7,Item_Amt7,Item_Percentage7,Item_MemberAmt7,Item_MemberPerc7," +
                    "Item_Qty8,Item_Percentage8,Item_MemberAmt8,Item_MemberPerc8,Item_Qty9," +
                    "Item_Amt9,Item_Percentage9,Item_MemberAmt9,Item_MemberPerc9,Item_Qty10," +
                    "Item_Amt10,Item_Percentage10,Item_MemberAmt10,Item_MemberPerc10) " +
                    "VALUES (" +
                    "'"+DBFunc.PurifyString(PromoID)+"',"+
                    "'"+DBFunc.PurifyString(promoItemID)+"',"+
                    "'"+DBFunc.PurifyString(promoItemUOM)+"',"+
                    "'"+DBFunc.PurifyString(promoItem_UnitID)+"',"+
                    "'"+DBFunc.PurifyString(promoItemPrice)+"',"+
                    "'"+DBFunc.PurifyString(promoItemMemberPrice)+"',"+
                    "'"+DBFunc.PurifyString(promoItemUnit)+"',"+
                    "'"+DBFunc.PurifyString(promoItemMemberPrice)+"',"+
                    "'"+DBFunc.PurifyString(promoItemUnit)+"',"+
                    "'"+DBFunc.PurifyString(promoItem_Qty)+"',"+
                    "'"+DBFunc.PurifyString(promoItem_Amt)+"',"+
                    "'"+DBFunc.PurifyString(promoItem_Percentage)+"',"+
                    "'"+DBFunc.PurifyString(promoItem_MemberAmt)+"',"+
                    "'"+DBFunc.PurifyString(promoItem_MemberPerc)+"',"+
                    "'"+DBFunc.PurifyString(promoItem_Qty2)+"',"+
                    "'"+DBFunc.PurifyString(promoItem_Amt2)+"',"+
                    "'"+DBFunc.PurifyString(promoItem_Percentage2)+"',"+
                    "'"+DBFunc.PurifyString(promoItem_MemberAmt2)+"',"+
                    "'"+DBFunc.PurifyString(promoItem_MemberPerc2)+"',"+
                    "'"+DBFunc.PurifyString(promoItem_Qty3)+"',"+
                    "'"+DBFunc.PurifyString(promoItem_Amt3)+"',"+
                    "'"+DBFunc.PurifyString(promoItem_Percentage3)+"',"+
                    "'"+DBFunc.PurifyString(promoItem_MemberAmt3)+"',"+
                    "'"+DBFunc.PurifyString(promoItem_MemberPerc3)+"',"+
                    "'"+DBFunc.PurifyString(promoItem_Qty4)+"',"+
                    "'"+DBFunc.PurifyString(promoItem_Amt4)+"',"+
                    "'"+DBFunc.PurifyString(promoItem_Percentage4)+"',"+
                    "'"+DBFunc.PurifyString(promoItem_MemberAmt4)+"',"+
                    "'"+DBFunc.PurifyString(promoItem_MemberPerc4)+"',"+
                    "'"+DBFunc.PurifyString(promoItem_Qty5)+"',"+
                    "'"+DBFunc.PurifyString(promoItem_Amt5)+"',"+
                    "'"+DBFunc.PurifyString(promoItem_Percentage5)+"',"+
                    "'"+DBFunc.PurifyString(promoItem_MemberAmt5)+"',"+
                    "'"+DBFunc.PurifyString(promoItem_MemberPerc5)+"',"+
                    "'"+DBFunc.PurifyString(promoItem_Qty6)+"',"+
                    "'"+DBFunc.PurifyString(promoItem_Amt6)+"',"+
                    "'"+DBFunc.PurifyString(promoItem_Percentage6)+"',"+
                    "'"+DBFunc.PurifyString(promoItem_MemberAmt6)+"',"+
                    "'"+DBFunc.PurifyString(promoItem_MemberPerc6)+"',"+
                    "'"+DBFunc.PurifyString(promoItem_Qty7)+"',"+
                    "'"+DBFunc.PurifyString(promoItem_Amt7)+"',"+
                    "'"+DBFunc.PurifyString(promoItem_Percentage7)+"',"+
                    "'"+DBFunc.PurifyString(promoItem_MemberAmt7)+"',"+
                    "'"+DBFunc.PurifyString(promoItem_MemberPerc7)+"',"+
                    "'"+DBFunc.PurifyString(promoItem_Qty8)+"',"+
                    "'"+DBFunc.PurifyString(promoItem_Percentage8)+"',"+
                    "'"+DBFunc.PurifyString(promoItem_MemberAmt8)+"',"+
                    "'"+DBFunc.PurifyString(promoItem_MemberPerc8)+"',"+
                    "'"+DBFunc.PurifyString(promoItem_Qty9)+"',"+
                    "'"+DBFunc.PurifyString(promoItem_Amt9)+"',"+
                    "'"+DBFunc.PurifyString(promoItem_Percentage9)+"',"+
                    "'"+DBFunc.PurifyString(promoItem_MemberAmt9)+"',"+
                    "'"+DBFunc.PurifyString(promoItem_MemberPerc9)+"',"+
                    "'"+DBFunc.PurifyString(promoItem_Qty10)+"',"+
                    "'"+DBFunc.PurifyString(promoItem_Amt10)+"',"+
                    "'"+DBFunc.PurifyString(promoItem_Percentage10)+"',"+
                    "'"+DBFunc.PurifyString(promoItem_MemberAmt10)+"',"+
                    "'"+DBFunc.PurifyString(promoItem_MemberPerc10)+"')", true);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


//    //Cursor config_promo_item = null;
//    private void volleyPLU(String str) {
//        Cursor config_values = null;
//        config_values = DBFunc.Query("SELECT Promo_Name,Promo_DateFrom,Promo_DateTo,PromoID FROM Promo" , true);
//        String SubmitSaleObject = "0";
//        String SubmitSaleObjectfrom = "0";
//        String SubmitSaleObjectto = "0";
//        String SubmitSaleObjectproID = "0";
//        List<String> list = new ArrayList<String>();
//        assert config_values != null;
//        while (config_values.moveToNext()) {
//            SubmitSaleObject = config_values.getString(0);
//            SubmitSaleObjectfrom = config_values.getString(1);
//            SubmitSaleObjectto = config_values.getString(2);
//            SubmitSaleObjectproID = config_values.getString(3);
//            list.add(SubmitSaleObject+"__1__"+SubmitSaleObjectfrom+"__2__"+SubmitSaleObjectto+"__3__"+SubmitSaleObjectproID);
//        }
//
//        Cursor test = null;
//        test = DBFunc.Query("SELECT Department,UOM FROM Inventory", true);
//        if (test != null) {
//            while (test.moveToNext()) {
//                Log.i("TEStttinv",test.getString(0)+"___"+str+"_"+test.getString(1));
//            }
//        }
//
//        Cursor testdept = null;
//        testdept = DBFunc.Query("SELECT * FROM Dept", true);
//        if (testdept != null) {
//            while (testdept.moveToNext()) {
//                Log.i("TEStttdept",testdept.getString(0)+"___"+testdept.getString(1)+"___"+str);
//            }
//        }
//
//
//
//
//        Cursor config_promo_item = null;
//        //config_promo_item = DBFunc.Query("SELECT ItemUOM,ItemPrice FROM promo_item" , true);
//        config_promo_item = DBFunc.Query("SELECT ItemID,ItemSKU,ItemDescp,SupBarCode,UOM,Price,OnHandQty,Department,Option FROM Inventory" , true);
//
//        String db_inv_ItemID = "0";
//        String db_inv_ItemSKU = "0";
//        String db_inv_ItemDescp = "0";
//        String db_inv_SupBarCode = "0";
//        String db_inv_Price = "0";
//        String db_inv_UOM = "0";
//        String db_inv_OnHandQty = "0";
//        String db_inv_dept = "0";
//        String db_inv_option = "0";
//        assert config_promo_item != null;
//
//        DBFunc.ExecQuery("DELETE FROM PLU", true);
//
//        while (config_promo_item.moveToNext()) {
//            db_inv_ItemID = config_promo_item.getString(0);
//            db_inv_ItemSKU = config_promo_item.getString(1);
//            db_inv_ItemDescp = config_promo_item.getString(2);
//            db_inv_SupBarCode = config_promo_item.getString(3);
//            db_inv_UOM = config_promo_item.getString(4);
//            db_inv_Price = config_promo_item.getString(5);
//            db_inv_OnHandQty = config_promo_item.getString(6);
//            db_inv_dept = config_promo_item.getString(7);
//
//            db_inv_option = config_promo_item.getString(8);
//            Log.i("sdsdb_inv_dept",db_inv_dept);
//            if (db_inv_dept.equals("")){
//                db_inv_dept = "226";
//            }
//            Log.i("ddb_inv_dept",db_inv_dept);
//            String db_deptname = "";
//            if (db_inv_dept.length() > 0) {
//
//                Cursor c_department = DBFunc.Query("SELECT Name FROM Dept WHERE ID = '" + Integer.parseInt(db_inv_dept) + "' ", true);
//                if (c_department == null) {// no item found
//                    db_deptname = "";
//                } else if (!c_department.moveToNext()) {// no item found
//                    db_deptname = "";
//                } else {
//                    db_deptname = c_department.getString(0);
//                }
//
//            }
//            String db_condi_seq = "0";
//            Cursor c_condi_seq = DBFunc.Query("SELECT ID FROM Condi_Seq WHERE Name = '"+db_deptname.toUpperCase()+"' ", true);
//            if (c_condi_seq == null) {// no item found
//                db_condi_seq = "0";
//            }else if (!c_condi_seq.moveToNext()) {// no item found
//                db_condi_seq = "0";
//            }else{
//                db_condi_seq = c_condi_seq.getString(0);
//            }
//
//            //DBFunc.ExecQuery("INSERT INTO PLU (Name,DeptID,Price,Code,Option,Condi_Seq) VALUES ('"+DBFunc.PurifyString(db_inv_ItemDescp)+"','"+Integer.parseInt(db_inv_dept)+"','"+DBFunc.PurifyString(db_inv_Price)+"','"+DBFunc.PurifyString(db_inv_ItemSKU)+"','"+db_inv_option+"',"+Integer.parseInt(db_condi_seq)+")", true);
//
//            // Log.i("db_condi_seqs",DBFunc.PurifyString(db_condi_seq));
//
//            String sql = "SELECT ID FROM PLU ORDER BY ID DESC LIMIT 1";
//
//            Cursor c_pluid = DBFunc.Query(sql, true);
//            if (c_pluid == null) {// DB Error
//
//            }
//            int pluid = 0;
//
//            if (c_pluid.moveToNext()) {
//                pluid = c_pluid.getInt(0);
//                Log.i("sdsd_pluid", String.valueOf(pluid));
//            }
//            pluid = pluid + 1;
//            c_pluid.close();
//            Log.i("sdsad__pluid", String.valueOf(pluid));
//
////            ID,
////                    Name,
////                    DeptID,
////                    Price,
////                    Option,
////                    Code,
////                    Condi_Seq
//            String uniqueId  = UUID.randomUUID().toString();
//            String sql_insert = "INSERT INTO PLU (ID,UUID,Name,DeptID,Price,Code,Option,Condi_Seq) " +
//                    "VALUES ("+pluid+",'"+uniqueId+"','"+DBFunc.PurifyString(db_inv_ItemDescp)+"','"+Integer.parseInt(db_inv_dept)+"','"+DBFunc.PurifyString(db_inv_Price)+"','"+DBFunc.PurifyString(db_inv_ItemSKU)+"','"+db_inv_option+"','')";
//            Log.i("sdsad__sql_insert", String.valueOf(sql_insert));
//            DBFunc.ExecQuery(sql_insert, true);
//
//        }
//
//        Cursor config_promo_type = null;
//        config_promo_type = DBFunc.Query("SELECT Promo_TypeCode FROM Promotype" , true);
//        String db_promo_type_ = "0";
//        List<String> list_promo_type = new ArrayList<String>();
//        assert config_promo_type != null;
//        while (config_promo_type.moveToNext()) {
//            db_promo_type_ = config_promo_type.getString(0);
//            list_promo_type.add(db_promo_type_);
//        }
//        if (!str.equals("check")) {
//            success(context);
//        }
//    }

//    private void volleySetMenu(String str) {
//
//        DBFunc.ExecQuery("DELETE FROM PLU", true);
//
//
//        RequestQueue queue = Volley.newRequestQueue(this);
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        // response code
//
//
//                        String xmlString = response;
//                        Document xmlparse  = null;
//                        Document parse = ActivityMyRetailerAPI.XMLParseFunction(xmlString, xmlparse);
//                        for (int i=0;i< parse.getElementsByTagName("getItemSetMenuResponse").getLength();i++) {
//                            getItemSetMenuResponse = (parse.getElementsByTagName("getItemSetMenuResponse").getLength() > 0)
//                                    ? parse.getElementsByTagName("getItemSetMenuResponse").item(i).getTextContent() : " ";
//                        }
//                        try {
//
//                            JSONObject obj = new JSONObject(getItemSetMenuResponse);
//                            String itmSetMenu = obj.getString("ItemSetMenu");
//                            JSONArray mJsonArray = new JSONArray(itmSetMenu);
//                            if (mJsonArray == null)
//                                return;
//
//                            DBFunc.ExecQuery("DELETE FROM Inventory", true);
//                            DBFunc.ExecQuery("DELETE FROM Condi_Seq", true);
//
//                            JSONObject deptObject = null;
//                            for (int i=0; i < mJsonArray.length(); i++){
//                                deptObject = mJsonArray.getJSONObject(i);
//                                ID = deptObject.getString("ID");
//                                Value = deptObject.getString("Value");
//                                Nick = deptObject.getString("Nick");
//
//                                DBFunc.ExecQuery("INSERT INTO Condi_Seq(Name) VALUES('"+DBFunc.PurifyString(Value)+"')", true);
//                                DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth, System.currentTimeMillis(), "Condi_Seq -> Add -> Name: "+Value);
//
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                        //success();
//                        //volleyPLU("");
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
//
//                        //Log.i("ServerError",res);
//                        // Now you can use any deserializer to make sense of data
////                                JSONObject obj = new JSONObject(res);
//                    } catch (UnsupportedEncodingException e1) {
//                        // Couldn't properly decode data to string
//                        e1.printStackTrace();
//                        //Log.i("e1",e1.getMessage());
//                    }
//                }
//            }
//        })
//        {
//            @Override
//            public Map<String, String> getParams(){
//                return null;
//            }
//            @Override
//            public byte[] getBody(){
//                String temp = "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
//                        "  <soap12:Body>\n" +
//                        "    <getItemSetMenu xmlns=\"http://tempuri.org/\">\n" +
//                        "      <companyCode>"+company_code+"</companyCode>\n" +
//                        "    </getItemSetMenu>\n" +
//                        "  </soap12:Body>\n" +
//                        "</soap12:Envelope>";
//                //Log.i("APP", "request String: " + temp);
//                byte[] b = temp.getBytes(Charset.forName("UTF-8"));
//
//                return b;
//            }
//            @Override
//            public String getBodyContentType()
//            {
//                return "text/xml;charset=utf-8";
//            }
//        };
//        queue.add(stringRequest);
//
//    }

    public static void passwordResendSalesDialog(Context context,final String str, final int year, final String month, final String dayOfMonth,
                                                 final int to_year, final String to_month, final String to_dayOfMonth,String zCloseStatus) {

        ProgressDialog pd = new ProgressDialog(context);
        new AsyncTaskSync(context,zCloseStatus).execute(zCloseStatus,dayOfMonth,month,to_dayOfMonth,to_month,year,to_year,pd);


    }

    private static class AsyncTaskSync extends AsyncTask<Object, ImageView, Void> {
        Context mcontext;
        String chkzCloseStatusForDialog = "";
        public AsyncTaskSync(Context context,String strzCloseStatus) {
            mcontext = context;
            chkzCloseStatusForDialog = strzCloseStatus;
        }

        ProgressDialog pd = null;

        protected Void doInBackground(Object... params) {
            String zCloseStatus = (String) params[0];
//            Integer dayOfMonth = (Integer) params[1];
//            Integer month = (Integer) params[2];
//            Integer to_dayOfMonth = (Integer) params[3];
//            Integer to_month = (Integer) params[4];

            String dayOfMonth = (String) params[1];
            String month = (String) params[2];
            String to_dayOfMonth = (String) params[3];
            String to_month = (String) params[4];
            Integer year = (Integer) params[5];
            Integer to_year = (Integer) params[6];

            //pd = (ProgressDialog) params [7];

            //pd = new ProgressDialog(mcontext);
            //pd = Query.showProgressDialog(mcontext, "Syncing....");

            AsyncTaskSyncFun(mcontext, zCloseStatus, dayOfMonth, month, to_dayOfMonth, to_month, year, to_year);

            return null;
        }

        //        private final Handler handler = new Handler();
//        private Runnable yourRunnable;
        @Override
        protected void onPostExecute(Void result) {
//
//            try{
//
//
//                 //(mcontext);
//
//
//
//                Thread.sleep(10000);
//            }
//            catch(Exception e){
//                e.printStackTrace();
//            }


//
//            this.handler.removeCallbacks(this.yourRunnable);
        }

        @Override
        protected void onPreExecute() {

            if (!(chkzCloseStatusForDialog.equals("FailResync") || chkzCloseStatusForDialog.equals(""))) {
                if (!chkzCloseStatusForDialog.equals("Z")) {

                    final ProgressDialog progressDialog = ProgressDialog.show(mcontext, "Please wait...",
                            "Syncing data ...", true);

                    new Thread() {
                        public void run() {
                            try {
                                // Do some work here
                                sleep(10000);
                            } catch (Exception e) {
                            }
                            // start next intent
                            new Thread() {
                                public void run() {
                                    // Dismiss the Dialog
                                    progressDialog.dismiss();

                                    Intent mainp = new Intent(mcontext, SyncActivity.class);
                                    mcontext.startActivity(mainp);
                                    //((Activity)mcontext).finish();
//                            // start selected activity
//                            if ( startingIntent != null) context.startActivity(startingIntent);
                                }
                            }.start();
                        }
                    }.start();
                }
            }
        }
    }
    //public static String Sale_ReceiptNo = "";
    public static void AsyncTaskSyncFun(Context context, String zCloseStatus, String dayOfMonth, String month, String to_dayOfMonth,
                                        String to_month, Integer year, Integer to_year) {
        String strQuery = "";

        if (zCloseStatus.equals("Z")) {
            //strQuery = " WHERE IsZ IS NULL";


            String sSql = "SELECT BillNo FROM SyncSales WHERE (SyncStatus = 'FAIL' OR SyncStatus = '' OR SyncStatus IS NULL )";
            Cursor CursorSyncSalesUpdate = DBFunc.Query(sSql, false);
            Integer id_ = 0;
            String billNoAllStr = "";
            if (CursorSyncSalesUpdate != null) {
                billNoAllStr = "";
                while (CursorSyncSalesUpdate.moveToNext()) {
                    billNoAllStr += "'"+CursorSyncSalesUpdate.getString(0)+"'";
                    if (!CursorSyncSalesUpdate.isLast()) {
                        billNoAllStr += ",";
                    }
                }
                CursorSyncSalesUpdate.close();
            }
            //strQuery = " WHERE BillNo IN ('"+billNoAllStr+"')";
            strQuery = " WHERE BillNo IN ("+billNoAllStr+")";
        } else if (zCloseStatus.equals("FailResync")) {
            //strQuery = " WHERE SyncStatus = 'FAIL' ";
            strQuery = " ";
        } else {
            String fromdate = year + "-" + month + "-" + dayOfMonth;
            String todate = to_year + "-" + to_month + "-" + to_dayOfMonth;

            //strQuery = " WHERE strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch') BETWEEN '" + fromdate + "' and '" + todate + "'";
            strQuery = " WHERE strftime('"+Constraints.sqldateformat_sync+"', DateTime / 1000, 'unixepoch') BETWEEN '" + fromdate + "' and '" + todate + "'";
        }

        ResyncOrNormal(context,"",strQuery,"Resync",zCloseStatus);

    }

    public static void ResyncOrNormal(Context context,String billno,String strQuery,String statusResynOrNormal,String zCloseStatus) {

//        if (syncUrl != null && syncUrl.length() > 10 ) {
            try {

//                volleyUpdatePayment(context);

                GetSyncDataInformationAll();

                JSONArray jarr = new JSONArray();

                RequestQueue queue = Volley.newRequestQueue(context);
//                final String finalCompany_code = syncCompanyCode;

//                Log.i("sql___","sql____"+"third_"+finalCompany_code);

                if (statusResynOrNormal.equals("Normal")) {
                    strQuery = " WHERE BillNo = '" + billno + "'";
                }

                String sql = "SELECT BillNo,strftime('%Y-%m-%d %H:%M:%S', " +
                        "DateTime / 1000, 'unixepoch'), STATUS, TotalNettSales," +
                        "strftime('%Y%m%d', DateTime / 1000, 'unixepoch') " +
                        "FROM Sales  " +
                        strQuery + " ORDER BY BillNo ASC";

                Cursor billData = DBFunc.Query(sql, false);

                if (billData != null) {

                    while (billData.moveToNext()) {
                        String b_bill_no = billData.getString(0);
                        String b_date = billData.getString(1);
                        String Sale_status = billData.getString(2);
                        //String receipt_no = billData.getString(4) + b_bill_no;
                        String receipt_no = b_bill_no;


                        String temp = "";
                        jarr.put(GetJSONArrValueForResyncSales(receipt_no, b_bill_no));
                        String chk_hide_img = Query.GetOptions(22);


                        Cursor Cursor_Possys = Query.GetURLAndCodeFromPossys();
                        if (Cursor_Possys != null) {
                            while (Cursor_Possys.moveToNext()) {
                               // syncRetailID = Cursor_Possys.getString(0);
                                syncCompanyCode = Cursor_Possys.getString(1);
                                syncUrl = Cursor_Possys.getString(2);
                                //download_retail_ID = Cursor_Possys.getString(3);
                               // download_company_code = Cursor_Possys.getString(4);
                               // download_url = Cursor_Possys.getString(5);
                            }
                            Cursor_Possys.close();
                        }

                        if (chk_hide_img.equals("1")) {
                            temp = Query.SubmitSales(syncCompanyCode, jarr, null);
                        } else {
                            JSONObject jsonval = GetJSONArrValueForResyncSales(receipt_no, b_bill_no);
                            temp = Query.SubmitSales(syncCompanyCode, null, jsonval);
                        }
                       // Log.i("temp__","temp___"+temp);
                        String chkSubmitSalesOrNot = Query.GetOptions(22);
                        String result = "";
                        if (chkSubmitSalesOrNot.equals("1")) {
//                            result = "submitSalesResult";
                            result = "submitSalesResponse";
                        } else {
                            result = "submitAdroindSalesResult";
                        }
                        DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                                System.currentTimeMillis(), DBFunc.PurifyString("SyncActivity-SubmitSales " +"queue-"+queue+"," +"syncUrl-"+syncUrl+"," +"result-"+result+","
                                        +"temp-"+temp+"," +"b_bill_no-"+b_bill_no+","+"zCloseStatus-"+zCloseStatus));
                        //String submitSalesResult = Query.StringRequestQueue(queue, syncUrl, result, temp, "", zCloseStatus, "", "SubmitSales");
                        String submitSalesResult = Query.StringRequestQueue(context,queue, syncUrl, result, temp, b_bill_no, zCloseStatus, "", "SubmitSales");
//                        SyncSales(context,queue, syncUrl, result, temp, b_bill_no, zCloseStatus, "", "SubmitSales");

                    }
                    billData.close();
    /*
            RequestQueue queue = Volley.newRequestQueue(context);

            final String finalCompany_code = syncCompanyCode;

            String temp = Query.SubmitSales(finalCompany_code,jarr);

            String chk_hide_img = Query.GetOptions(22);
            String resul = "";
            if (chk_hide_img.equals("1")){
                resul = "submitSalesResult";
            }else {
                resul = "submitAdroindSalesResult";
            }

            // String submitSalesResult = Query.StringRequestQueue(queue,syncUrl,"submitAdroindSalesResult",jarr,bill_no,statusSyncStatus,zCloseStatus,"SubmitSales");
            String submitSalesResult = Query.StringRequestQueue(queue,syncUrl,resul,temp,"",zCloseStatus,"","SubmitSales");
    */
                }
            } catch (java.lang.RuntimeException e) {
                Log.e("RuntimeException__", "err__" + e.getMessage());
                DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                        System.currentTimeMillis(), DBFunc.PurifyString("SyncActivity-SubmitSales " +"RuntimeException-"+e.getMessage()));
            } catch (Exception e) {
                Log.e("Exception_", "err__" + e.getMessage());
                DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                        System.currentTimeMillis(), DBFunc.PurifyString("SyncActivity-SubmitSales " +"Exception-"+e.getMessage()));
            }
//        }
    }

    private static JSONObject GetJSONArrValueForResyncSales(String receiptno, String bill_no) {

        GetSyncDataInformationAll();

        final JSONObject jsonObject = new JSONObject();
        String b_date = "";
        String statusSyncStatus = "";
        double statusSyncRoundAdj = 0.0;
        double statusSyncTotalTax = 0.0;
        double statusSyncTotalNettAmt = 0.0;
        try {
            Cursor Cursor_SaleForSync = Query.GetSalesForSync(bill_no);
            if (Cursor_SaleForSync != null){
                if (Cursor_SaleForSync.moveToNext()){
                    statusSyncStatus = Cursor_SaleForSync.getString(7);
                    statusSyncRoundAdj = Cursor_SaleForSync.getDouble(8);
                    statusSyncTotalTax = Cursor_SaleForSync.getDouble(4);
                    statusSyncTotalNettAmt = Cursor_SaleForSync.getDouble(3);

                    long dttt = Cursor_SaleForSync.getLong(9);

                    String dtttt = Query.findfieldNameById("strftime('"+Constraints.sqldateformat_sync+"', DateTime / 1000, 'unixepoch')",
                            "BillNo", bill_no , "Sales", false);

                    DateFormat dateFormat56 = new SimpleDateFormat("HH:mm:ss");
                    Date resultdate = new Date(dttt);
                    b_date = dtttt + " " +dateFormat56.format(resultdate);
                }
                Cursor_SaleForSync.close();
            }

//            if (!statusSyncStatus.equals(ENUM.SALES)) {
//                statusSyncStatus = ENUM.VOID;
//            }

            jsonObject.put(DeclarationConf.submitSalesTransNo, receiptno); // ReceiptNo
            jsonObject.put(DeclarationConf.submitSalesRetailID, syncRetailID); // RetailID

            DateZeroFun(b_date,bill_no);

            jsonObject.put(DeclarationConf.submitSalesSalesDate, b_date); //Date

            if (!statusSyncStatus.equals(Constraints.SALES)) {
//                String chk_hide_img = Query.GetOptions(22);
//                if (chk_hide_img.equals("1")) {
//                    if (!statusSyncStatus.toUpperCase().equals(ENUM.REFUND.toUpperCase())) {
//                        jsonObject.put(DeclarationConf.submitSalesSalesStatus, ENUM.REFUND.toUpperCase()); //Status
//                    } else {
//                        jsonObject.put(DeclarationConf.submitSalesSalesStatus, ENUM.VOID); //Status
//                    }
//                }else {
//
//                }

                if (statusSyncStatus.toUpperCase().equals(Constraints.REFUND.toUpperCase())) {
                    jsonObject.put(DeclarationConf.submitSalesSalesStatus, Constraints.REFUND); //Status
                }else {
                    jsonObject.put(DeclarationConf.submitSalesSalesStatus, Constraints.VOID); //Status
                }
            }else {
                jsonObject.put(DeclarationConf.submitSalesSalesStatus, Constraints.SALES); //Status
            }
            jsonObject.put(DeclarationConf.submitSalesMemberID, __volley_submit_sale_memberID);

//            if (Double.valueOf(statusSyncTotalTax) == 0.0 ) {
//                statusSyncTotalTax = GetExclusiveTaxValues(statusSyncTotalNettAmt);
//            }
            String[] hwid = GetExclusiveTaxValues(statusSyncTotalNettAmt);
            double inclTaxValue = 0.0;
            double SalesTaxRate = 0.0;
            String SalesTaxType = "";

            for (int s1 = 0 ; s1 < 1 ; s1 ++){


                inclTaxValue = Double.parseDouble(hwid[0]);
                SalesTaxRate = Double.parseDouble(String.format("%.2f", Double.parseDouble(hwid[1])));
                SalesTaxType = hwid[2];


            }
            if (Double.valueOf(statusSyncTotalTax) == 0.0 ) {
                statusSyncTotalTax = inclTaxValue;
            }
            if (Double.valueOf(statusSyncTotalTax) == 0.0 ){
                statusSyncTotalTax = 0.0;
            }


            if (statusSyncStatus.equals("VOID")){
                statusSyncTotalTax = (-1) * statusSyncTotalTax;
            }
            jsonObject.put(DeclarationConf.submitSalesSalesTaxTtl, String.format("%.2f", statusSyncTotalTax)); //TotalTax
            jsonObject.put(DeclarationConf.submitSalesTaxType, SalesTaxType); //SalesTaxType
            jsonObject.put(DeclarationConf.submitSalesTaxRate, SalesTaxRate); //SalesTaxRate
            jsonObject.put(DeclarationConf.submitSalesSalesRounding, String.format("%.2f", statusSyncRoundAdj)); //RoundAdj
            //GetDiscount
            getDiscount(bill_no,statusSyncStatus,statusSyncTotalNettAmt,jsonObject);

            asignToSyncSalesTable(bill_no,receiptno,b_date,statusSyncStatus,statusSyncRoundAdj,statusSyncTotalTax,statusSyncTotalNettAmt);

            //ItemSales
            GetItemSales(bill_no,statusSyncStatus,jsonObject);

            //ItemPayment
            JSONArray sales_payement_details_array =   new JSONArray();
            sales_payement_details_array = GetItemPayment(bill_no,jsonObject,statusSyncStatus);


            jsonObject.put("SalesPayments", sales_payement_details_array);

//            jsonObject.put("SaleVoucher", "");

            Cursor cMV = Query.GetMercatusVoucher(bill_no);
            if (cMV != null) {
                if (cMV.getCount() == 0){
                    jsonObject.put("SaleVoucher", "");
                }
                while (cMV.moveToNext()) {
                    String voucher_number = cMV.getString(0);
                    String voucher_amount = cMV.getString(1);

                    jsonObject.put("SaleVoucher", voucher_number);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        String syncSalesStr = "SELECT ID,SalesStatus FROM SyncSales WHERE BillNo = '" + bill_no + "' ";

        Cursor config_option = DBFunc.Query(syncSalesStr, false);
        int id_ = 0;
        String syncStatus_ = "";
        if (config_option != null){
            if (config_option.moveToNext()) {
                id_ = config_option.getInt(0);
                syncStatus_ = config_option.getString(1);
            }
            config_option.close();
        }


        if (id_ > 0){
            if (!syncStatus_.equals("VOID")){

                if (!"Resync".equals("Resync")) {

                    SaveSyncSales();
                }

                id_ = Query.findLatestID("ID","SyncSales",false);

                //updateSyncSales(id_, statusSyncStatus);
            }else {

                //updateSyncSales(id_,statusSyncStatus);
            }
        }else {
            SaveSyncSales();
        }
        Log.i("jsonobj__","jsonObject__"+jsonObject);
        return jsonObject;
    }

//    private static double GetExclusiveTaxValues(double totalNettSales) {
//        double TotalTaxSales = 0.0;
//        Cursor Cursor_tax = Query.GetTax();
//        if (Cursor_tax != null){
//            if (Cursor_tax.moveToNext()){
//                String taxRate = Cursor_tax.getString(0);
//                String taxType = Cursor_tax.getString(1);
//                String taxName = Cursor_tax.getString(2);
//                if (taxType.equals("2")){
//                    double amt_inclusive = Query.calculateInclusive(Double.valueOf(totalNettSales),Integer.parseInt(taxRate));
//                    TotalTaxSales = amt_inclusive;
////                    if (amt_inclusive != 0.0) {
////                        //String str_amt_inclusive = Query.ShowAmtMinusCorrectly(Double.valueOf(String.valueOf(amt_inclusive)));
////                        //String str_inclusive = taxName.toUpperCase() + "(" + taxRate + "%)" + " SGD " + str_amt_inclusive + " Incl";
////                        TotalTaxSales += Double.parseDouble(amt_inclusive);
////                    }
//                }
////                else {
////                    if (TotalTaxes != 0.0) {
////                        String str_exclusive = Query.ShowAmtMinusCorrectly(Double.valueOf(String.valueOf(TotalTaxes)));
////                        //String str_exclusive = String.format("%.2f", Double.valueOf(TotalTaxes));
////                        //String TaxExcul = validate_space(0, 21, taxName.toUpperCase() + "(" + taxRate + "%)" + " $", "Qty");
////                        String TaxExcul = validate_space(0, 21, taxName.toUpperCase() + "(" + taxRate + "%)" + "  ", "Qty");
////                        str += "\n" + TaxExcul + validate_space(0, 11, str_exclusive, "Price");
////                    }
////                }
//            }
//            Cursor_tax.close();
//        }
//        return TotalTaxSales;
//    }


    private static String[] GetExclusiveTaxValues(double totalNettSales) {
        double TotalTaxSales = 0.0;
        String taxRate = "";
        String taxTypeStatus = "E";
        Cursor Cursor_tax = Query.GetTax();
        if (Cursor_tax != null){
            if (Cursor_tax.moveToNext()){
                taxRate = Cursor_tax.getString(0);
                String taxType = Cursor_tax.getString(1);
                String taxName = Cursor_tax.getString(2);
                if (taxType.equals("2")){
                    taxTypeStatus = "I";
                    double amt_inclusive = Query.calculateInclusive(Double.valueOf(totalNettSales),Integer.parseInt(taxRate));
                    TotalTaxSales = amt_inclusive;
                }
            }
            Cursor_tax.close();
        }
        return new String[]{String.valueOf(TotalTaxSales), taxRate,taxTypeStatus };
    }

    private static void getDiscount(String bill_no,String statusSyncStatus,Double statusSyncTotalNettAmt,JSONObject jsonObject) {
        Double totalbilldisValue = 0.0;
        Double disValue = 0.0;
        Double totalDisamt = 0.0;
        String DiscountTypeName = "";
        Integer DiscountID = 0;
        String DiscountName = "";
        try {

            //jsonObject.put("SalesTotalAmount", String.format("%.2f", Double.valueOf(saleTotalAmount)));

            if (statusSyncStatus.equals("VOID")){
                if (statusSyncTotalNettAmt != null && Double.valueOf(statusSyncTotalNettAmt) > 0.0) {
                    jsonObject.put("SalesTotalAmount", String.format("%.2f", ((-1) * Double.valueOf(statusSyncTotalNettAmt))));
                }else {
                    jsonObject.put("SalesTotalAmount", String.format("%.2f", Double.valueOf(statusSyncTotalNettAmt)));
                }
            }else {
                jsonObject.put("SalesTotalAmount", String.format("%.2f", Double.valueOf(statusSyncTotalNettAmt)));
            }

//        String chk_hide_img = Query.GetOptions(22);
//        if (chk_hide_img.equals("1")) {
            String q_uery = "SELECT DiscountValue,TotalBillDisount,DiscountTypeName,DiscountID,DiscountName," +
                    "TotalBillDisount FROM Sales WHERE BillNo = '" + bill_no + "' ";


            Cursor c_ursor = DBFunc.Query(q_uery,false);
            if (c_ursor != null) {
                if (c_ursor.moveToNext()){
                    disValue = c_ursor.getDouble(0);
                    totalDisamt = c_ursor.getDouble(1);
                    DiscountTypeName = c_ursor.getString(2);
                    DiscountID = c_ursor.getInt(3);
                    DiscountName = c_ursor.getString(4);
                    totalbilldisValue = c_ursor.getDouble(5);


                    String UUID_DISC = Query.findfieldNameById("UUID", "Name", DiscountName , "DISC", true);
                    // saleTotalAmount = Query.getStatusValue(statusSyncStatus, statusSyncTotalNettAmt);



                    //disValue = Query.getStatusValue(statusSyncStatus,disValue);
                    //totalDisamt = Query.getStatusValue(statusSyncStatus,totalDisamt);
                    //            }

//                    //jsonObject.put("SalesTotalDiscount", String.format("%.2f", Double.valueOf(totalDisamt)));
//                    if (statusSyncStatus.equals("VOID")){
//                        if (totalbilldisValue != null && Double.valueOf(totalbilldisValue) > 0.0) {
//                            jsonObject.put("SalesTotalDiscount", String.format("%.2f", ((-1) * Double.valueOf(totalbilldisValue))));
//                        }else {
//                            jsonObject.put("SalesTotalDiscount", String.format("%.2f", Double.valueOf(totalbilldisValue)));
//                        }
//                    }else {
//                        jsonObject.put("SalesTotalDiscount", String.format("%.2f", Double.valueOf(totalbilldisValue)));
//                    }
                    //jsonObject.put("SalesTotalDiscount", String.format("%.2f", Double.valueOf(totalDisamt)));
                    if (statusSyncStatus.equals("SALES")){
                        if (totalbilldisValue != null && Double.valueOf(totalbilldisValue) > 0.0) {
                            jsonObject.put("SalesTotalDiscount", String.format("%.2f", ((-1) * Double.valueOf(totalbilldisValue))));
                        }else {
                            jsonObject.put("SalesTotalDiscount", String.format("%.2f", Double.valueOf(totalbilldisValue)));
                        }
                    }else {
                        if (totalbilldisValue != null && Double.valueOf(totalbilldisValue) < 0.0) {
                            jsonObject.put("SalesTotalDiscount", String.format("%.2f", ((-1) * Double.valueOf(totalbilldisValue))));
                        }else {
                            jsonObject.put("SalesTotalDiscount", String.format("%.2f", Double.valueOf(totalbilldisValue)));
                        }
                    }



                    if (UUID_DISC != null && UUID_DISC.length() > 0) {
                        jsonObject.put("DiscID1", UUID_DISC);
                    }else {
                        jsonObject.put("DiscID1", UUID_DISC);
                    }
                    jsonObject.put("DiscID2", "0");
                    jsonObject.put("DiscID3", "0");



                    String salesql = "SELECT ReferenceBillNo " +
                            "FROM Sales " +
                            "where BillNo = '"+bill_no+"' " ;


                    Cursor salec = DBFunc.Query(salesql,false);
                    if (salec != null) {
                        if (salec.moveToNext()) {
                            String referenceBillNo = salec.getString(0);
                            String salersql = "SELECT UUID,BillNo,STATUS,strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch')," +
                                    "strftime('%Y-%m-%d %H:%M:%S', DateTime / 1000, 'unixepoch') " +
                                    "FROM Sales " +
                                    "where BillNo = '"+referenceBillNo+"' " ;
                           ;
                            Cursor salerc = DBFunc.Query(salersql,false);
                            if (salerc != null) {
                                if (salerc.moveToNext()) {
                                    String uuid = salerc.getString(0);
                                    String billno = salerc.getString(1);
                                    String status = salerc.getString(2);
                                    String dt = salerc.getString(3);
                                    String dtm = salerc.getString(4);


                                    jsonObject.put("ContraSalesID", uuid);
                                    jsonObject.put("ContraSalesNo", billno);
                                    jsonObject.put("ContraSalesDate", dt);
                                    jsonObject.put("ContraCreateTime", dtm);
                                    jsonObject.put("ContraSalesStatus", status.toUpperCase());
                                }
                                salerc.close();
                            }
                        }
                        salec.close();
                    }



                    if (DiscountTypeName.equals("$ Dollar Value")){
                        jsonObject.put("SalesDiscPerc", "0.00");
                        disValue = Query.getStatusValue(statusSyncStatus,disValue);
                        jsonObject.put("SalesDiscAmt", String.format("%.2f", Double.valueOf(disValue)));
                    }else {
                        disValue = Query.getStatusValue(statusSyncStatus,disValue);
                        jsonObject.put("SalesDiscPerc", String.format("%.2f", Double.valueOf(disValue)));
                        jsonObject.put("SalesDiscAmt", "0.00");
                    }

                }else {
                    jsonObject.put("SalesTotalDiscount", "0.00");
                    jsonObject.put("DiscID1", "");
                    jsonObject.put("DiscID2", "0");
                    jsonObject.put("DiscID3", "0");
                    jsonObject.put("SalesDiscPerc", "0.00");
                    jsonObject.put("SalesDiscAmt", "0.00");
                }
                c_ursor.close();
            }
            else {
                jsonObject.put("SalesTotalDiscount", "0.00");
                jsonObject.put("DiscID1", "");
                jsonObject.put("DiscID2", "0");
                jsonObject.put("DiscID3", "0");
                jsonObject.put("SalesDiscPerc", "0.00");
                jsonObject.put("SalesDiscAmt", "0.00");
            }

//        }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static JSONArray GetItemPayment(String bill_no,JSONObject jsonObject,String statusSyncStatus) {
        JSONArray sales_payement_details_array =  new JSONArray();
        String billPaymentName = "";
        Double billPaymentAmount = 0.0;
        Double billNettAmount = 0.0;
        Double billChangeAmount = 0.0;
        Integer billPaymentID = 0;
        String billPaymentRemarks = "";
        Cursor Cursor_BillPayment = Query.GetBillPaymentAmountDetails(bill_no);
        if (Cursor_BillPayment != null) {
            if (Cursor_BillPayment.getCount()  == 0) {

                JSONObject sales_payment_details = new JSONObject();
                try {
                    sales_payment_details.put("PaymentID", 0);
                    sales_payment_details.put("strPayment", " ");
                    sales_payment_details.put("SalesPayTtl", "0");
                    sales_payment_details.put("SalesBalTtl", "0");
                    sales_payment_details.put("ChangeAmount", "0");
                    sales_payment_details.put("PaymentRemarks", "");

                    JeripayDataEmpty(sales_payment_details);


//

                    DeclarationConf.sync_PaymentID = "0";
                    DeclarationConf.sync_strPayment = "";
                    DeclarationConf.sync_SalesPayTtl = "0";
                    DeclarationConf.sync_SalesBalTtl = "0";
                    DeclarationConf.sync_ChangeAmount = "0";

                    saveSyncSalesPayment();

                    sales_payement_details_array.put(sales_payment_details);
                    jsonObject.put("SalesPayments", sales_payement_details_array);
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            }else {

                billPaymentName = "";
                billPaymentAmount = 0.0;
                billNettAmount = 0.0;
                billChangeAmount = 0.0;
                billPaymentID = 0;
                billPaymentRemarks = "";
                while (Cursor_BillPayment.moveToNext()) {
                    JSONObject sales_payment_details = new JSONObject();

                    billPaymentName = Cursor_BillPayment.getString(0);
                    billPaymentAmount = Cursor_BillPayment.getDouble(1) + Cursor_BillPayment.getDouble(2);
                    billNettAmount = Cursor_BillPayment.getDouble(1);
                    billChangeAmount = Cursor_BillPayment.getDouble(2);
                    billPaymentID = Cursor_BillPayment.getInt(3);
                    billPaymentRemarks = Cursor_BillPayment.getString(7);

                    if (billPaymentRemarks == null || billPaymentRemarks.equals("null") || billPaymentRemarks.equals("")) {
                        billPaymentRemarks = "";
                    }

                    //double d_amtt = Query.getStatusValue(sale_staus,Double.valueOf(amtt));

                    try {
                        Integer len_billPaymentID = Query.CheckLength(String.valueOf(billPaymentID));
                        if (len_billPaymentID == 0 ){
                            billPaymentID = 0;
                        }
                        sales_payment_details.put("PaymentID", billPaymentID);

                        Integer len_billPaymentName = Query.CheckLength(billPaymentName);
                        if (len_billPaymentName == 0 ){
                            billPaymentName = " ";
                        }
                        sales_payment_details.put("strPayment", billPaymentName.toUpperCase());

                        Integer len_billPaymentAmount = Query.CheckLength(String.valueOf(billPaymentAmount));
                        if (len_billPaymentAmount == 0 ){
                            billPaymentAmount = 0.0;
                        }
                        //billPaymentAmount = Query.getStatusValue(statusSyncStatus,billPaymentAmount);

                        if (statusSyncStatus.equals("VOID")){

                            if (billPaymentAmount != null && Double.valueOf(billPaymentAmount) > 0.0) {

                                sales_payment_details.put("SalesPayTtl", String.format("%.2f", ((-1) *  billPaymentAmount)));
                            }else {

                                sales_payment_details.put("SalesPayTtl", String.format("%.2f", billPaymentAmount));
                            }
                        }else {
                            sales_payment_details.put("SalesPayTtl", String.format("%.2f", billPaymentAmount));
                        }


                        Integer len_billNettAmount = Query.CheckLength(String.valueOf(billNettAmount));
                        if (len_billNettAmount == 0 ){
                            billNettAmount = 0.0;
                        }
                        //billNettAmount = Query.getStatusValue(statusSyncStatus,billNettAmount);
                        if (statusSyncStatus.equals("VOID")){
                            if (billNettAmount != null && Double.valueOf(billNettAmount) > 0.0) {
                                sales_payment_details.put("SalesBalTtl", String.format("%.2f", ((-1) *  billNettAmount)));
                            }else {
                                sales_payment_details.put("SalesBalTtl", String.format("%.2f", billNettAmount));
                            }
                        }else {
                            sales_payment_details.put("SalesBalTtl", String.format("%.2f", billNettAmount));
                        }

                        Integer len_billChangeAmount = Query.CheckLength(String.valueOf(billChangeAmount));
                        if (len_billChangeAmount == 0 ){
                            billChangeAmount = 0.0;
                        }
                        billChangeAmount = Query.getStatusValue(statusSyncStatus,billChangeAmount);
                        sales_payment_details.put("ChangeAmount", String.format("%.2f", billChangeAmount));

                        sales_payment_details.put("PaymentRemarks", billPaymentRemarks);


                        JeripayData(sales_payment_details,bill_no);

                        MercatusData(sales_payment_details,bill_no);

                        BOCData(sales_payment_details,bill_no);

                        sales_payement_details_array.put(sales_payment_details);

                        DeclarationConf.sync_PaymentID = String.valueOf(billPaymentID);
                        DeclarationConf.sync_strPayment = billPaymentName.toUpperCase();
                        DeclarationConf.sync_SalesPayTtl = String.valueOf(billPaymentAmount);
                        DeclarationConf.sync_SalesBalTtl = String.valueOf(billNettAmount);
                        DeclarationConf.sync_ChangeAmount = String.valueOf(billChangeAmount);

                        Integer sync_sales_id = Query.findLatestID("ID","SyncSales",false);
                        String syncSalesStr = "SELECT ID  FROM SyncSalesPayments  WHERE SyncSalesID = '"+ sync_sales_id +"' ";


                        Cursor config_option = DBFunc.Query(syncSalesStr, false);
                        assert config_option != null;
                        Integer id_ = 0;
                        while (config_option.moveToNext()) {
                            id_ = config_option.getInt(0);
                        }

                        if (id_ > 0){

                        }else {
                            saveSyncSalesPayment();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            Cursor_BillPayment.close();
        }
        return sales_payement_details_array;
    }

    private static void BOCData(JSONObject sales_payment_details, String bill_no) {
        try {
            //OtherPayments
            Cursor jeripayDetails = Query.getBillBOC(bill_no);
            if (jeripayDetails != null) {
                while (jeripayDetails.moveToNext()) {
//                    String acquirer_id = jeripayDetails.getString(3);
                    String entry_mode = jeripayDetails.getString(0);
                    String merchant_id = jeripayDetails.getString(1);
                    String terminal_id = jeripayDetails.getString(2);
                    String card_number = jeripayDetails.getString(3);
                    String invoice_number = jeripayDetails.getString(4);
                    String host_label = jeripayDetails.getString(5);
                    String card_label = jeripayDetails.getString(6);

                    sales_payment_details.put("TipsAmount", "0");
                    sales_payment_details.put("PaymentReference", "");
                    sales_payment_details.put("PaymentStatus", "");
                    sales_payment_details.put("OthersPaymentRef", card_label.toUpperCase());
//                    try {
////                        0000008000E800A0000000041010  MasterCard
//                        sales_payment_details.put("OthersPaymentRef", entry_mode.split("  ")[1]);
//                    }catch (ArrayIndexOutOfBoundsException e){
//                        sales_payment_details.put("OthersPaymentRef", "");
//                    }
                    sales_payment_details.put("PaymentCardNo", card_number);
                    sales_payment_details.put("TID", terminal_id);
                    sales_payment_details.put("MerchantID", merchant_id);
                    sales_payment_details.put("PaymentInvoiceNo", invoice_number);
                    sales_payment_details.put("PaymentApprovalCode", "");
//                                jsonObject.put("SaleVoucher", "");

//                    try {
////                        0000008000E800A0000000041010  MasterCard
                    sales_payment_details.put("OthersPayment", card_label.toUpperCase());
//                        sales_payment_details.put("OthersPayment", entry_mode.split("  ")[1]);
//                    }catch (ArrayIndexOutOfBoundsException e){
//                        sales_payment_details.put("OthersPayment", "");
//                    }
                    sales_payment_details.put("Issuer_bank", host_label.toUpperCase());
                    sales_payment_details.put("Issuer_country", "");
                }
            } else {
//                jsonObject.put("paymentID", "");
//                jsonObject.put("strPayment", "");
//                jsonObject.put("SalesPayTtl", "");
//                jsonObject.put("SalesBalTtl", "");
//                jsonObject.put("ChangeAmount", "");
                sales_payment_details.put("TipsAmount", "0");
                sales_payment_details.put("PaymentReference", "");
                sales_payment_details.put("PaymentStatus", "");
                sales_payment_details.put("OthersPaymentRef", "");
                sales_payment_details.put("PaymentCardNo", "");
                sales_payment_details.put("TID", "");
                sales_payment_details.put("MerchantID", "");
                sales_payment_details.put("PaymentInvoiceNo", "");
                sales_payment_details.put("PaymentApprovalCode", "");
//                            jsonObject.put("SaleVoucher", "");
                sales_payment_details.put("OthersPayment", "");
                sales_payment_details.put("Issuer_bank", "");
                sales_payment_details.put("Issuer_country", "");
            }
//                    }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static void MercatusData(JSONObject sales_payment_details, String bill_no) {
        try {
//            String chk_hide_img = Query.GetOptions(22);
//                    if (chk_hide_img.equals("1")) {
            //OtherPayments
            Cursor jeripayDetails = Query.GetMercatusDetails(bill_no);
            if (jeripayDetails != null) {
                if (jeripayDetails.getCount() == 0) {

//                    jsonObject.put("paymentID", "");
//                    jsonObject.put("strPayment", "");
//                    jsonObject.put("SalesPayTtl", "");
//                    jsonObject.put("SalesBalTtl", "");
//                    jsonObject.put("ChangeAmount", "");

                    sales_payment_details.put("TipsAmount", "0");

                    sales_payment_details.put("PaymentReference", "");
                    sales_payment_details.put("PaymentStatus", "");
                    sales_payment_details.put("OthersPaymentRef", "");
                    sales_payment_details.put("PaymentCardNo", "");
                    sales_payment_details.put("TID", "");
                    sales_payment_details.put("MerchantID", "");
                    sales_payment_details.put("PaymentInvoiceNo", "");
                    sales_payment_details.put("PaymentApprovalCode", "");
//                                jsonObject.put("SaleVoucher", "");
                    sales_payment_details.put("OthersPayment", "");
                    sales_payment_details.put("Issuer_bank", "");
                    sales_payment_details.put("Issuer_country", "");
                }
                while (jeripayDetails.moveToNext()) {
//                    String acquirer_id = jeripayDetails.getString(3);
                    String card_label = jeripayDetails.getString(0);
                    String merchant_id = jeripayDetails.getString(3);
                    String terminal_id = jeripayDetails.getString(4);
                    String card_number = jeripayDetails.getString(1);
                    String invoice_number = jeripayDetails.getString(2);
//                    "card_label":"OCBC~VISA"

//                    jsonObject.put("paymentID", "");
//                    jsonObject.put("strPayment", "");
//                    jsonObject.put("SalesPayTtl", "");
//                    jsonObject.put("SalesBalTtl", "");
//                    jsonObject.put("ChangeAmount", "");
                    sales_payment_details.put("TipsAmount", "0");
                    sales_payment_details.put("PaymentReference", "");
                    sales_payment_details.put("PaymentStatus", "");
                    sales_payment_details.put("OthersPaymentRef", "");
                    sales_payment_details.put("PaymentCardNo", card_number);
                    sales_payment_details.put("TID", terminal_id);
                    sales_payment_details.put("MerchantID", merchant_id);
                    sales_payment_details.put("PaymentInvoiceNo", invoice_number);
                    sales_payment_details.put("PaymentApprovalCode", "");
//                                jsonObject.put("SaleVoucher", "");
                    try {
                        sales_payment_details.put("OthersPayment", card_label.split("~")[1]);
                        sales_payment_details.put("Issuer_bank", card_label.split("~")[0]);
                    }catch (ArrayIndexOutOfBoundsException e){
                        sales_payment_details.put("OthersPayment", "");
                        sales_payment_details.put("Issuer_bank", "");
                    }
                    sales_payment_details.put("Issuer_country", "");
                }
            } else {
//                jsonObject.put("paymentID", "");
//                jsonObject.put("strPayment", "");
//                jsonObject.put("SalesPayTtl", "");
//                jsonObject.put("SalesBalTtl", "");
//                jsonObject.put("ChangeAmount", "");
                sales_payment_details.put("TipsAmount", "0");
                sales_payment_details.put("PaymentReference", "");
                sales_payment_details.put("PaymentStatus", "");
                sales_payment_details.put("OthersPaymentRef", "");
                sales_payment_details.put("PaymentCardNo", "");
                sales_payment_details.put("TID", "");
                sales_payment_details.put("MerchantID", "");
                sales_payment_details.put("PaymentInvoiceNo", "");
                sales_payment_details.put("PaymentApprovalCode", "");
//                            jsonObject.put("SaleVoucher", "");
                sales_payment_details.put("OthersPayment", "");
                sales_payment_details.put("Issuer_bank", "");
                sales_payment_details.put("Issuer_country", "");
            }
//                    }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static void JeripayDataEmpty(JSONObject sales_payment_details) {
        try {
            sales_payment_details.put("TipsAmount", "0");
            sales_payment_details.put("PaymentReference", "");
            sales_payment_details.put("PaymentStatus", "");
            sales_payment_details.put("OthersPaymentRef", "");
            sales_payment_details.put("PaymentCardNo", "");
            sales_payment_details.put("TID", "");
            sales_payment_details.put("MerchantID", "");
            sales_payment_details.put("PaymentInvoiceNo", "");
            sales_payment_details.put("PaymentApprovalCode", "");
//                            jsonObject.put("SaleVoucher", "");
            sales_payment_details.put("OthersPayment", "");
            sales_payment_details.put("Issuer_bank", "");
            sales_payment_details.put("Issuer_country", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static void JeripayData(JSONObject sales_payment_details, String bill_no) {
        try {
            String chk_hide_img = Query.GetOptions(22);
//                    if (chk_hide_img.equals("1")) {
            //OtherPayments
            Cursor jeripayDetails = Query.GetJeripayDetails(bill_no);
            if (jeripayDetails != null) {
//            if (jeripayDetails.getCount() == 0) {
//
//////                    jsonObject.put("paymentID", "");
//////                    jsonObject.put("strPayment", "");
//////                    jsonObject.put("SalesPayTtl", "");
//////                    jsonObject.put("SalesBalTtl", "");
//////                    jsonObject.put("ChangeAmount", "");
////
////               sales_payment_details.put("TipsAmount", "0");
////
////                sales_payment_details.put("PaymentReference", "");
////                sales_payment_details.put("PaymentStatus", "");
////                sales_payment_details.put("OthersPaymentRef", "");
////                sales_payment_details.put("PaymentCardNo", "");
////                sales_payment_details.put("TID", "");
////                sales_payment_details.put("MerchantID", "");
////                sales_payment_details.put("PaymentInvoiceNo", "");
////                sales_payment_details.put("PaymentApprovalCode", "");
//////                                jsonObject.put("SaleVoucher", "");
////                sales_payment_details.put("OthersPayment", "");
////                sales_payment_details.put("Issuer_bank", "");
////                sales_payment_details.put("Issuer_country", "");
//            }
                while (jeripayDetails.moveToNext()) {
//                    String acquirer_id = jeripayDetails.getString(3);
                    String card_label = jeripayDetails.getString(0);
                    String merchant_id = jeripayDetails.getString(3);
                    String terminal_id = jeripayDetails.getString(4);
                    String card_number = jeripayDetails.getString(1);
                    String invoice_number = jeripayDetails.getString(2);
//                    "card_label":"OCBC~VISA"

//                    jsonObject.put("paymentID", "");
//                    jsonObject.put("strPayment", "");
//                    jsonObject.put("SalesPayTtl", "");
//                    jsonObject.put("SalesBalTtl", "");
//                    jsonObject.put("ChangeAmount", "");
                    sales_payment_details.put("TipsAmount", "0");
                    sales_payment_details.put("PaymentReference", "");
                    sales_payment_details.put("PaymentStatus", "");
                    sales_payment_details.put("OthersPaymentRef", "");
                    sales_payment_details.put("PaymentCardNo", card_number);
                    sales_payment_details.put("TID", terminal_id);
                    sales_payment_details.put("MerchantID", merchant_id);
                    sales_payment_details.put("PaymentInvoiceNo", invoice_number);
                    sales_payment_details.put("PaymentApprovalCode", "");
//                                jsonObject.put("SaleVoucher", "");
                    try {
                        sales_payment_details.put("OthersPayment", card_label.split("~")[1]);
                        sales_payment_details.put("Issuer_bank", card_label.split("~")[0]);
                    }catch (ArrayIndexOutOfBoundsException e){
                        sales_payment_details.put("OthersPayment", "");
                        sales_payment_details.put("Issuer_bank", "");
                    }

                    sales_payment_details.put("Issuer_country", "");
                }
            } else {
//                jsonObject.put("paymentID", "");
//                jsonObject.put("strPayment", "");
//                jsonObject.put("SalesPayTtl", "");
//                jsonObject.put("SalesBalTtl", "");
//                jsonObject.put("ChangeAmount", "");
                sales_payment_details.put("TipsAmount", "0");
                sales_payment_details.put("PaymentReference", "");
                sales_payment_details.put("PaymentStatus", "");
                sales_payment_details.put("OthersPaymentRef", "");
                sales_payment_details.put("PaymentCardNo", "");
                sales_payment_details.put("TID", "");
                sales_payment_details.put("MerchantID", "");
                sales_payment_details.put("PaymentInvoiceNo", "");
                sales_payment_details.put("PaymentApprovalCode", "");
//                            jsonObject.put("SaleVoucher", "");
                sales_payment_details.put("OthersPayment", "");
                sales_payment_details.put("Issuer_bank", "");
                sales_payment_details.put("Issuer_country", "");
            }
//                    }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static void GetItemSales(String bill_no,String statusSyncStatus,JSONObject jsonObject) {
        try {
            JSONArray item_sales_details_array = Query.GetItemItemSyncSales(bill_no,statusSyncStatus);
            if (item_sales_details_array.length() > 0) {
                jsonObject.put(DeclarationConf.submitSalesItemSales, item_sales_details_array);
            }else {
                item_sales_details_array = Query.GetSaleItemEmptyFun();
                jsonObject.put(DeclarationConf.submitSalesItemSales, item_sales_details_array);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static void asignToSyncSalesTable(String bill_no,String receiptno,String b_date,String statusSyncStatus,
                                              Double statusSyncRoundAdj, Double statusSyncTotalTax, Double statusSyncTotalNettAmt) {
        //Assign Values To SyncSales
        DeclarationConf.Sync_billNo = String.valueOf(bill_no);
        DeclarationConf.Sync_TransNo = receiptno;
        DeclarationConf.Sync_RetailID = syncRetailID;
        DeclarationConf.Sync_SalesDate = b_date;
        DeclarationConf.Sync_SalesStatus = statusSyncStatus;
        DeclarationConf.Sync_MemberID = __volley_submit_sale_memberID;
        DeclarationConf.Sync_SalesRounding = String.valueOf(statusSyncRoundAdj);
        DeclarationConf.Sync_SalesTaxTtl = String.valueOf(statusSyncTotalTax);
        DeclarationConf.Sync_SalesTotalAmount = String.valueOf(statusSyncTotalNettAmt);
        // DeclarationConf.Sync_ItemSalesID = "1";
        //DeclarationConf.Sync_SalesPaymentsID = "1";
        DeclarationConf.SyncStatus = "FAIL";
    }

    private static void DateZeroFun(String b_date, String bill_no) {
        if (b_date.length() == 0) {

            long dttt = 0;
            String sql = "SELECT DateTime,strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch') " +
                    "FROM Sales WHERE BillNo = '" +bill_no +"'";
            Cursor c = DBFunc.Query(sql, false);
            if (c != null) {
                if (c.moveToNext()) {
                    //dttt = c.getLong(0) + 3600000;
                    //dttt = c.getLong(0) + 28800;
                    dttt = c.getLong(0);

                    //DateFormat dateFormat55 = new SimpleDateFormat("yyyy-MM-dd hh:mm aa");
                    //DateFormat dateFormat55 = new SimpleDateFormat("hh:mm aa");
                    DateFormat dateFormat55 = new SimpleDateFormat("HH:mm aa");

                    //b_date = Query.findfieldNameById("strftime('%Y-%m-%d %H:%M:%S', DateTime / 1000, 'unixepoch')", "BillNo", bill_no , "BillList", false);

                    //Date resultdate = new Date(dttt);
                    String dtttt = Query.findfieldNameById("strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch')",
                            "BillNo", bill_no , "Sales", false);


                    b_date =  dtttt+ " " + dateFormat55.format(dttt);
                }
                c.close();
            }


        }
    }

//    private static class AsyncTaskVolleySubmitSales extends AsyncTask<Object, ImageView, Void> {
//        Context mcontext;
//        ProgressDialog progressDialog;
//        public AsyncTaskVolleySubmitSales(Context context) {
//            mcontext = context;
//        }
//
//        @Override
//        protected Void doInBackground(Object... params) {
////            String zCloseStatus = (String) params [0];
////            Integer dayOfMonth = (Integer) params [1];
////            Integer month = (Integer) params [2];
////            Integer to_dayOfMonth = (Integer) params [3];
////            Integer to_month = (Integer) params [4];
////            Integer year = (Integer) params [5];
////            Integer to_year = (Integer) params [6];
//            String bill_no = (String) params [0];
//            String receipt_no = (String) params [1];
//            Double amount = (Double) params [2];
//            String resync_status = (String) params [3];
//            String sale_status = (String) params [4];
//            String zCloseStatus = (String) params [5];
////            progressDialog = (ProgressDialog) params [5];
//
//
////            progressDialog = Query.showProgressDialog(mcontext, "Syncing...");
//
////            AsyncTaskSyncFun(mcontext,zCloseStatus,dayOfMonth,month,to_dayOfMonth,to_month,year,to_year);
//            //AsyncTaskVolleySubmitSalesFun(mcontext,bill_no,amount,resync_status,sale_status,zCloseStatus,progressDialog);
//            AsyncTaskVolleySubmitSalesFun(mcontext,bill_no,receipt_no,amount,resync_status,sale_status,zCloseStatus,null);
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void result) {
//            //success(mcontext);
//
//            //progressDialog.dismiss();
//
////            Intent mainp = new Intent(mcontext, SyncActivity.class);
////            mcontext.startActivity(mainp);
////            ((Activity)mcontext).finish();
//
////            if (!zCloseStatus.equals("FailResync")) {
////                success(context);
////            }
////            Intent mainp = new Intent(mcontext, SyncActivity.class);
////            mcontext.startActivity(mainp);
////            ((Activity)mcontext).finish();
//
//        }
//
//        @Override
//        protected void onPreExecute() {
//
//        }
//
//
//    }
//
//    public static String asasaa = "";
//    public static void AsyncTaskVolleySubmitSalesFun(Context context, String bill_no, String receiptno,
//                                                     Double amount, String resync_status,
//                                                     String sale_status, String zCloseStatus,
//                                                     ProgressDialog progressDialog) {
//        volleyUpdatePayment(context);
//
//        RequestQueue queue = Volley.newRequestQueue(context);
//        //final String receipt_no =  Query.GetReceiptNoSaleSync(bill_no);
//        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
//        Date date = new Date();
//
//        String receipt_no = "";
////        Log.i("Dfdf____","resync_status___"+resync_status+"__"+
////                Sale_ReceiptNo);
//        if (resync_status.equals("Resync")){
//            //receipt_no = bill_no;
//            receipt_no = receiptno;
//
//        }else {
//            String yyyymmdd = dateFormat.format(date);
//            receipt_no = yyyymmdd + bill_no;
//        }
//        Log.i("Dfdf____","receipt_123receiptno___"+resync_status+"__"+receipt_no);
//        final JSONObject jsonObject = new JSONObject();
//        String b_date = "";
//        String statusSyncStatus = "";
//        Double statusSyncRoundAdj = 0.0;
//        Double statusSyncTotalTax = 0.0;
//        Double statusSyncTotalNettAmt = 0.0;
//        try {
////            java.util.Date utilDate = new java.util.Date();
////            Calendar cal = Calendar.getInstance();
////            cal.setTime(utilDate);
////            cal.set(Calendar.MILLISECOND, 0);
//            Cursor Cursor_SaleForSync = Query.GetSalesForSync(bill_no);
//            if (Cursor_SaleForSync != null){
//                if (Cursor_SaleForSync.moveToNext()){
//                    statusSyncStatus = Cursor_SaleForSync.getString(7);
//                    statusSyncRoundAdj = Cursor_SaleForSync.getDouble(8);
//                    statusSyncTotalTax = Cursor_SaleForSync.getDouble(4);
//                    statusSyncTotalNettAmt = Cursor_SaleForSync.getDouble(3);
//
//                    //long dttt = Cursor_SaleForSync.getLong(9) + 28800;
//                    long dttt = Cursor_SaleForSync.getLong(9);
//
//                    DateFormat dateFormat55 = new SimpleDateFormat("YYYY MM DD  hh:mm aa");
//                    //Date resultdate = new Date(dttt);
//                    //b_date = dateFormat55.format(dttt);
//                    //b_date = Cursor_SaleForSync.getString(9);
//                    //b_date = Cursor_SaleForSync.getString(9);
//
//                    String dtttt = Query.findfieldNameById("strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch')",
//                            "BillNo", bill_no , "Sales", false);
//
//
//
//                    //DateFormat dateFormat56 = new SimpleDateFormat("hh:mm:ss");
//                    DateFormat dateFormat56 = new SimpleDateFormat("HH:mm:ss");
//                    Date resultdate = new Date(dttt);
//                    b_date = dtttt + " " +dateFormat56.format(resultdate);
//                }
//                Cursor_SaleForSync.close();
//            }
//
//            if (resync_status.equals("Resync")){
//                statusSyncStatus = sale_status;
//            }
//
//            if (!statusSyncStatus.equals(ENUM.SALES)) {
//                statusSyncStatus = ENUM.VOID;
//            }
//
//            jsonObject.put(DeclarationConf.submitSalesTransNo, receipt_no); // ReceiptNo
//            jsonObject.put(DeclarationConf.submitSalesRetailID, syncRetailID); // RetailID
//            //jsonObject.put(DeclarationConf.submitSalesSalesDate, String.valueOf(new java.sql.Timestamp(utilDate.getTime())));
//
//
//            if (b_date.length() == 0) {
//
//                long dttt = 0;
//                String sql = "SELECT DateTime,strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch') " +
//                        "FROM Sales WHERE BillNo = '" +bill_no +"'";
//                Cursor c = DBFunc.Query(sql, false);
//                if (c != null) {
//                    if (c.moveToNext()) {
//                        //dttt = c.getLong(0) + 3600000;
//                        //dttt = c.getLong(0) + 28800;
//                        dttt = c.getLong(0);
//                        Log.i("Dfdfd___","dttt__"+dttt);
//                        //DateFormat dateFormat55 = new SimpleDateFormat("yyyy-MM-dd hh:mm aa");
//                        //DateFormat dateFormat55 = new SimpleDateFormat("hh:mm aa");
//                        DateFormat dateFormat55 = new SimpleDateFormat("HH:mm aa");
//
//                        //b_date = Query.findfieldNameById("strftime('%Y-%m-%d %H:%M:%S', DateTime / 1000, 'unixepoch')", "BillNo", bill_no , "BillList", false);
//
//                        //Date resultdate = new Date(dttt);
//                        String dtttt = Query.findfieldNameById("strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch')",
//                                "BillNo", bill_no , "Sales", false);
//                        Log.i("Dfdfdf___dtttt","dtttt____"+dtttt);
//
//                        b_date =  dtttt+ " " + dateFormat55.format(dttt);
//                    }
//                    c.close();
//                }
//
//
//            }
//
//
//            //            }Cursor c = DBFunc.Query("SELECT "+fieldName+" FROM "+tbName+ " WHERE "+IdName +"= '" +Id +"'", flag);
//            jsonObject.put(DeclarationConf.submitSalesSalesDate, b_date); //Date
//            jsonObject.put(DeclarationConf.submitSalesSalesStatus, statusSyncStatus); //Status
//            jsonObject.put(DeclarationConf.submitSalesMemberID, __volley_submit_sale_memberID);
//
//            //Double d_tax_ttl = Query.GetTaxTotalSyncSales(amtt,sale_staus,bill_no);
//            //statusSyncTotalTax = Query.getStatusValue(statusSyncStatus,statusSyncTotalTax);
//            if (Double.valueOf(statusSyncTotalTax) == 0.0 ){
//                statusSyncTotalTax = 0.0;
//            }
//
//            if (resync_status.equals("Resync") && statusSyncStatus.equals("VOID")){
//                statusSyncTotalTax = (-1) * statusSyncTotalTax;
//            }else {
//                statusSyncTotalTax = Query.getStatusValue(statusSyncStatus,statusSyncTotalTax);
//            }
//            jsonObject.put(DeclarationConf.submitSalesSalesTaxTtl, String.format("%.2f", statusSyncTotalTax)); //TotalTax
//            jsonObject.put(DeclarationConf.submitSalesSalesRounding, String.format("%.2f", statusSyncRoundAdj)); //RoundAdj
//
//            //double saleTotalAmount = Query.getStatusValue(statusSyncStatus,statusSyncTotalNettAmt);
//            double saleTotalAmount = 0.0;
//
//            Log.i("Df___","resync_status__"+resync_status);
//            if (resync_status.equals("Resync")) {
//                saleTotalAmount = amount;
//                Log.i("Df___","resync_status1__"+resync_status);
//            }else {
//                if (statusSyncStatus.equals("VOID")){
//                Log.i("DFDF__","statusSyncTotalNettAmt___"+statusSyncTotalNettAmt);
//                    if (statusSyncTotalNettAmt > 0.0) {
//                        saleTotalAmount = Query.getStatusValue(statusSyncStatus, statusSyncTotalNettAmt);
//                    }else {
//                        saleTotalAmount = statusSyncTotalNettAmt;
//                    }
//                    Log.i("DFDF__","ssaleTotalAmount___"+saleTotalAmount);
//                }else {
//                    saleTotalAmount = statusSyncTotalNettAmt;
//                }
//                Log.i("Df___","resync_status2__"+resync_status);
//            }
//////            Log.i("DFDF__","statusSyncStatus___"+statusSyncStatus);
//////            if (statusSyncStatus.equals("VOID")){
////                Log.i("DFDF__","__statusSyncTotalNettAmt___"+bill_no+"___"+amount+"___"+statusSyncTotalNettAmt);
////                if (statusSyncStatus.equals("SALES")){
////                    if (statusSyncTotalNettAmt > 0.0) {
////                        saleTotalAmount = Query.getStatusValue(statusSyncStatus, statusSyncTotalNettAmt);
////                        //saleTotalAmount = statusSyncTotalNettAmt;
////                    }else {
////                        saleTotalAmount = statusSyncTotalNettAmt;
////                        //saleTotalAmount = (-1) *  statusSyncTotalNettAmt;
////                    }
////                }else {
////                    saleTotalAmount = statusSyncTotalNettAmt;
////                }
////
////                Log.i("DFDF__","ssaleTotalAmount___"+saleTotalAmount);
////            }else {
////                saleTotalAmount = statusSyncTotalNettAmt;
////
////            }
////            Log.i("DFDF__","statusSyncStatus___"+statusSyncStatus);
////            if (statusSyncStatus.equals("VOID")){
////                Log.i("DFDF__","statusSyncTotalNettAmt___"+statusSyncTotalNettAmt);
////                if (statusSyncTotalNettAmt > 0.0) {
////                    saleTotalAmount = Query.getStatusValue(statusSyncStatus, statusSyncTotalNettAmt);
////                }else {
////                    saleTotalAmount = statusSyncTotalNettAmt;
////                }
////                Log.i("DFDF__","ssaleTotalAmount___"+saleTotalAmount);
////            }else {
////                saleTotalAmount = statusSyncTotalNettAmt;
////            }
//           // saleTotalAmount = Query.getStatusValue(statusSyncStatus,statusSyncTotalNettAmt);
//            //saleTotalAmount = statusSyncTotalNettAmt;
//            Double disValue = 0.0;
//            Double totalDisamt = 0.0;
//            String DiscountTypeName = "";
//            Integer DiscountID = 0;
//            String DiscountName = "";
//            String q_uery = "SELECT DiscountValue,TotalBillDisount,DiscountTypeName,DiscountID,DiscountName FROM Sales WHERE BillNo = '"+bill_no+"' ";
//            Cursor c_ursor = DBFunc.Query(q_uery,false);
//            if (c_ursor != null) {
//                if (c_ursor.moveToNext()){
//                    disValue = c_ursor.getDouble(0);
//                    totalDisamt = c_ursor.getDouble(1);
//                    DiscountTypeName = c_ursor.getString(2);
//                    DiscountID = c_ursor.getInt(3);
//                    DiscountName = c_ursor.getString(4);
//                }
//                c_ursor.close();
//            }
//            String UUID_DISC = Query.findfieldNameById("UUID", "Name", DiscountName , "DISC", true);
//
//            jsonObject.put("SalesTotalAmount", String.format("%.2f", Double.valueOf(saleTotalAmount)));
////            if (Double.valueOf(totalDisamt) == 0.0 ){
////                totalDisamt = 0.0;
////            }
//
//
//            if (resync_status.equals("Resync") && statusSyncStatus.equals("VOID")){
//
//                disValue = (-1) * disValue;
//                //totalDisamt = (-1) * totalDisamt;
//            }else {
//
//                disValue = Query.getStatusValue(statusSyncStatus,disValue);
//                //totalDisamt = Query.getStatusValue(statusSyncStatus,totalDisamt);
//            }
//            Log.i("Dfd__","_totalDisamt_"+totalDisamt+"__"+resync_status+"__"+statusSyncStatus);
//            //jsonObject.put("SalesTotalDiscount", String.format("%.2f", Double.valueOf(totalDisamt)));
//            jsonObject.put("SalesTotalDiscount", String.format("%.2f", Double.valueOf(disValue)));
//            if (UUID_DISC != null && UUID_DISC.length() > 0) {
//                jsonObject.put("DiscID1", UUID_DISC);
//            }else {
//                jsonObject.put("DiscID1", UUID_DISC);
//            }
//            jsonObject.put("DiscID2", "0");
//            jsonObject.put("DiscID3", "0");
//            if (DiscountTypeName.equals("$ Dollar Value")){
//                jsonObject.put("SalesDiscPerc", "0.00");
//                jsonObject.put("SalesDiscAmt", String.format("%.2f", Double.valueOf(disValue)));
//            }else {
//                jsonObject.put("SalesDiscPerc", String.format("%.2f", Double.valueOf(disValue)));
//                jsonObject.put("SalesDiscAmt", "0.00");
//            }
//            //Assign Values To SyncSales
//            DeclarationConf.Sync_billNo = String.valueOf(bill_no);
//            DeclarationConf.Sync_TransNo = receipt_no;
//            DeclarationConf.Sync_RetailID = syncRetailID;
//            DeclarationConf.Sync_SalesDate = b_date;
//            DeclarationConf.Sync_SalesStatus = statusSyncStatus;
//            DeclarationConf.Sync_MemberID = __volley_submit_sale_memberID;
//            DeclarationConf.Sync_SalesRounding = String.valueOf(statusSyncRoundAdj);
//            DeclarationConf.Sync_SalesTaxTtl = String.valueOf(statusSyncTotalTax);
//            DeclarationConf.Sync_SalesTotalAmount = String.valueOf(statusSyncTotalNettAmt);
//            // DeclarationConf.Sync_ItemSalesID = "1";
//            //DeclarationConf.Sync_SalesPaymentsID = "1";
//            DeclarationConf.SyncStatus = "FAIL";
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        //ItemSales
//        try {
//            JSONArray item_sales_details_array = Query.GetItemItemSyncSales(bill_no,statusSyncStatus);
//            if (item_sales_details_array.length() > 0) {
//                jsonObject.put(DeclarationConf.submitSalesItemSales, item_sales_details_array);
//            }else {
//                item_sales_details_array = Query.GetSaleItemEmptyFun();
//                jsonObject.put(DeclarationConf.submitSalesItemSales, item_sales_details_array);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        //ItemPayment
//
//        JSONArray sales_payement_details_array =   new JSONArray();
//        String billPaymentName = "";
//        Double billPaymentAmount = 0.0;
//        Double billNettAmount = 0.0;
//        Double billChangeAmount = 0.0;
//        Integer billPaymentID = 0;
//        Cursor Cursor_BillPayment = Query.GetBillPaymentAmountDetails(bill_no);
//        if (Cursor_BillPayment != null) {
//            if (Cursor_BillPayment.getCount()  == 0) {
//
//                JSONObject sales_payment_details = new JSONObject();
//                try {
//                    sales_payment_details.put("PaymentID", 0);
//                    sales_payment_details.put("strPayment", " ");
//                    sales_payment_details.put("SalesPayTtl", "0");
//                    sales_payment_details.put("SalesBalTtl", "0");
//                    sales_payment_details.put("ChangeAmount", "0");
//
//                    DeclarationConf.sync_PaymentID = "0";
//                    DeclarationConf.sync_strPayment = "";
//                    DeclarationConf.sync_SalesPayTtl = "0";
//                    DeclarationConf.sync_SalesBalTtl = "0";
//                    DeclarationConf.sync_ChangeAmount = "0";
//
//                    saveSyncSalesPayment();
//
//                    sales_payement_details_array.put(sales_payment_details);
//                    jsonObject.put("SalesPayments", sales_payement_details_array);
//                } catch (JSONException ex) {
//                    ex.printStackTrace();
//                }
//            }else {
//
//                billPaymentName = "";
//                billPaymentAmount = 0.0;
//                billNettAmount = 0.0;
//                billChangeAmount = 0.0;
//                billPaymentID = 0;
//                while (Cursor_BillPayment.moveToNext()) {
//                    JSONObject sales_payment_details = new JSONObject();
//
//                    billPaymentName = Cursor_BillPayment.getString(0);
//                    billPaymentAmount = Cursor_BillPayment.getDouble(1) + Cursor_BillPayment.getDouble(2);
//                    billNettAmount = Cursor_BillPayment.getDouble(1);
//                    billChangeAmount = Cursor_BillPayment.getDouble(2);
//                    billPaymentID = Cursor_BillPayment.getInt(3);
//
//                    //double d_amtt = Query.getStatusValue(sale_staus,Double.valueOf(amtt));
//
//                    try {
//                        Integer len_billPaymentID = Query.CheckLength(String.valueOf(billPaymentID));
//                        if (len_billPaymentID == 0 ){
//                            billPaymentID = 0;
//                        }
//                        sales_payment_details.put("PaymentID", billPaymentID);
//
//                        Integer len_billPaymentName = Query.CheckLength(billPaymentName);
//                        if (len_billPaymentName == 0 ){
//                            billPaymentName = " ";
//                        }
//                        sales_payment_details.put("strPayment", billPaymentName.toUpperCase());
//
//                        Integer len_billPaymentAmount = Query.CheckLength(String.valueOf(billPaymentAmount));
//                        if (len_billPaymentAmount == 0 ){
//                            billPaymentAmount = 0.0;
//                        }
//                        billPaymentAmount = Query.getStatusValue(statusSyncStatus,billPaymentAmount);
//                        sales_payment_details.put("SalesPayTtl", String.format("%.2f", billPaymentAmount));
//
//                        Integer len_billNettAmount = Query.CheckLength(String.valueOf(billNettAmount));
//                        if (len_billNettAmount == 0 ){
//                            billNettAmount = 0.0;
//                        }
//                        billNettAmount = Query.getStatusValue(statusSyncStatus,billNettAmount);
//                        sales_payment_details.put("SalesBalTtl", String.format("%.2f", billNettAmount));
//
//                        Integer len_billChangeAmount = Query.CheckLength(String.valueOf(billChangeAmount));
//                        if (len_billChangeAmount == 0 ){
//                            billChangeAmount = 0.0;
//                        }
//                        billChangeAmount = Query.getStatusValue(statusSyncStatus,billChangeAmount);
//                        sales_payment_details.put("ChangeAmount", String.format("%.2f", billChangeAmount));
//
//                        sales_payement_details_array.put(sales_payment_details);
//
//                        DeclarationConf.sync_PaymentID = String.valueOf(billPaymentID);
//                        DeclarationConf.sync_strPayment = billPaymentName.toUpperCase();
//                        DeclarationConf.sync_SalesPayTtl = String.valueOf(billPaymentAmount);
//                        DeclarationConf.sync_SalesBalTtl = String.valueOf(billNettAmount);
//                        DeclarationConf.sync_ChangeAmount = String.valueOf(billChangeAmount);
//
//                        Integer sync_sales_id = Query.findLatestID("ID","SyncSales",false);
//                        String syncSalesStr = "SELECT ID  FROM SyncSalesPayments  WHERE SyncSalesID = '"+ sync_sales_id +"' ";
//
//                        Log.i("__cancel", syncSalesStr);
//                        Cursor config_option = DBFunc.Query(syncSalesStr, false);
//                        assert config_option != null;
//                        Integer id_ = 0;
//                        while (config_option.moveToNext()) {
//                            id_ = config_option.getInt(0);
//                        }
//
//                        if (id_ > 0){
//
//                        }else {
//                            saveSyncSalesPayment();
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//            Cursor_BillPayment.close();
//        }
//        //JSONArray sales_payement_details_array = Query.GetItemPaymentSyncSales(bill_payment_type,__volley_pay_type,sale_staus,amtt,__volley_changeamt);
//        //JSONArray sales_payement_details_array = Query.GetItemPaymentSyncSales(bill_payment_type,__volley_pay_type,sale_staus,amtt,__volley_changeamt);
//        try {
//            jsonObject.put("SalesPayments", sales_payement_details_array);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        double amt = amount + multiple_amount;
////            if (Double.valueOf(String.format("%.2f", amt)) < cost) {
////                multiple_amount = Double.valueOf(String.format("%.2f", amt));
////                return;
////            }
//        //asasaa +=  String.valueOf(jsonObject);
//        Log.i("_jsonObjectssss", "\n"+String.valueOf(jsonObject));
//        Date newdt = new Date();
//        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        //generateNoteOnSD(context,fmt.format(newdt.getTime()),jsonObject.toString());
//
////        final String finalCompany_code = syncCompanyCode;
////
////        String temp = Query.SubmitSales(finalCompany_code,jsonObject);
//
//
//////        String paramValue = "param\\with\\backslash";
//////        String yourURLStr = "http://host.com?param=" + java.net.URLEncoder.encode(paramValue, "UTF-8");
////        URL url = null;
////        try {
//////            url = new java.net.URL(syncUrl);
////            url = new URL(syncUrl);
////        } catch (MalformedURLException e) {
////            e.printStackTrace();
////        }
////        Log.i("Df___","url_url__"+url);
//
////        URL url = new URL(builtUri.toString());
//        //String submitSalesResult = Query.StringRequestQueue(queue,syncUrl,"submitAdroindSalesResult",temp,bill_no,statusSyncStatus,zCloseStatus,"SubmitSales");
//        //String submitSalesResult = Query.StringRequestQueue(queue, String.valueOf(url),"submitSalesResult",temp,bill_no,statusSyncStatus,zCloseStatus);
//
//        //String cancel = "SELECT ID  FROM SyncSales  WHERE BillID = '" + bill_no + "' ";
//        //20200701000000633
//        String cancel = "SELECT ID,SalesStatus  FROM SyncSales  WHERE BillNo = '" + bill_no + "' ";
//        Log.i("__cancel", cancel);
//        Cursor config_option = DBFunc.Query(cancel, false);
//        Integer id_ = 0;
//        String sstatus_ = "";
//        if (config_option != null){
//            if (config_option.moveToNext()) {
//                id_ = config_option.getInt(0);
//                sstatus_ = config_option.getString(1);
//            }
//            config_option.close();
//        }
//
//
//        if (id_ > 0){
//            if (!sstatus_.equals("VOID")){
//                if (!resync_status.equals("Resync")) {
//                    SaveSyncSales();
//                }
//                id_ = Query.findLatestID("ID","SyncSales",false);
//                updateSyncSales(id_, statusSyncStatus);
//                //updateSyncSales(bill_no, statusSyncStatus);
//            }else {
//                updateSyncSales(id_,statusSyncStatus);
//                // updateSyncSales(bill_no, statusSyncStatus);
//            }
//        }else {
//            SaveSyncSales();
//        }
//    }


    public static void generateNoteOnSD(Context context, String sFileName, String sBody) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd HH:mm:ss");
        Date now = new Date();
        String fileName = formatter.format(now) + ".txt";//like 2016_01_12.txt

        String FolerName = formatter.format(now.getTime()).toString();
        File direct = new File("/storage/emulated/0/"+FolerName);

        if (direct.exists()){
            try {

                FileUtils.deleteDirectory(direct);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (direct.mkdir()) {

            try {
                FileWriter writer = new FileWriter(direct,true);
                writer.append(sBody+"\n\n");
                writer.flush();
                writer.close();

                DBFunc.SaveDBToDisk(direct.getAbsolutePath(), FolerName);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else {
            Log.i("file_"+FolerName,"Error");
        }


    }
//    public void passwordDialog(final String str) {
//
//        LayoutInflater li = LayoutInflater.from(SyncActivity.this);
//        View promptsView = li.inflate(R.layout.searchprompt, null);
//        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SyncActivity.this,R.style.AlertDialogStyle);
//        alertDialogBuilder.setView(promptsView);
//
//        final EditText userInput = (EditText) promptsView
//                .findViewById(R.id.editTextDialogUserInput);
//
//
//        // set dialog message
//        alertDialogBuilder
//                .setCancelable(false)
//                .setNegativeButton("OK",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog,int id) {
//                                /** DO THE METHOD HERE WHEN PROCEED IS CLICKED*/
//                                String user_text = (userInput.getText()).toString();
//
//                                /** CHECK FOR USER'S INPUT **/
//                                if (user_text.equals("dcsSyn"))
//                                {
//                                    if (str.equals("department")){
//
//                                        Cursor con_val_plu = null;
//                                        String str_plu = "";
//                                        con_val_plu = DBFunc.Query("SELECT * FROM PLU", true);
//                                        if (con_val_plu != null) {
//                                            while (con_val_plu.moveToNext()) {
//                                                str_plu = con_val_plu.getString(0);
//                                            }
//                                        }
//                                        if (str_plu.length() > 0) {
//                                            volleyGetItemDepartment("check");
//                                            volleyGetInventory("check");
//                                            volleyGetPromotion("check");
//                                        }else{
//                                            volleyGetItemDepartment("");
//                                        }
//                                    }else  if (str.equals("inventory")){
//                                        Log.i("DSFSAF",str);
//                                        volleyGetInventory("");
//                                    }else  if (str.equals("promotion")){
//                                        volleyGetPromotion("");
//                                    }else  if (str.equals("payment_type")){
//                                        volleyUpdatePayment("");
//                                    }
////                                    else  if (str.equals("setmenu")){
////                                        Cursor con_val_inv = null;
////                                        String str_inv = "";
////                                        con_val_inv = DBFunc.Query("SELECT * FROM Inventory", true);
////                                        if (con_val_inv != null) {
////                                            while (con_val_inv.moveToNext()) {
////                                                str_inv = con_val_inv.getString(0);
////                                            }
////                                        }
////                                        if (str_inv.length() > 0) {
////                                            volleySetMenu("check");
////                                            volleyGetInventory("check");
////
////                                        }else{
////                                            volleySetMenu("");
////                                            volleyGetInventory("");
////
////                                        }
////                                    }else{
////
////                                    }
////                                    Log.d(user_text, "HELLO THIS IS THE MESSAGE CAUGHT :)");
////                                    Search_Tips(user_text);
//
//                                }
//                                else{
//                                    Log.d(user_text,"string is empty");
//                                    String message = "The password you have entered is incorrect." + " \n \n" + "Please try again!";
//                                    AlertDialog.Builder builder = new AlertDialog.Builder(SyncActivity.this,R.style.AlertDialogStyle);
//                                    builder.setTitle("Error");
//                                    builder.setMessage(message);
//                                    builder.setPositiveButton("Cancel", null);
//                                    builder.setNegativeButton("Retry", new DialogInterface.OnClickListener() {
//                                        @Override
//                                        public void onClick(DialogInterface dialog, int id) {
//                                            passwordDialog(str);
//                                        }
//                                    });
//                                    builder.create().show();
//
//                                }
//                            }
//                        })
//                .setPositiveButton("Cancel",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog,int id) {
//                                dialog.dismiss();
//                            }
//
//                        }
//
//                );
//
//        // create alert dialog
//        AlertDialog alertDialog = alertDialogBuilder.create();
//
//        // show it
//        alertDialog.show();
//
//    }

    public static void volleyUpdatePayment(Context context) {
        GetSyncDataInformationAll();
        if (syncCompanyCode != null && syncCompanyCode.length() > 0) {
            RequestQueue queue = Volley.newRequestQueue(context);
            JSONObject jsonObject = new JSONObject();
            final JSONObject jsonObjectC = new JSONObject();
            final String finalCompany_code = syncCompanyCode;

            Cursor config_values_pt = DBFunc.Query("SELECT ID,Name FROM Payment ", true);
            assert config_values_pt != null;
            int id = 0;
            String name = "";
            JSONArray sales_payement_details_array = new JSONArray();
            try {
                while (config_values_pt.moveToNext()) {
                    jsonObject = new JSONObject();
                    JSONObject sales_payment_details = new JSONObject();

                    id = config_values_pt.getInt(0);
                    name = config_values_pt.getString(1);
                    //Log.i("dsfdsf__name__",name);

                    sales_payment_details.put("ID", id);
                    sales_payment_details.put("Name", name);
                    sales_payment_details.put("Full", name);
                    sales_payment_details.put("Display", "Y");

                    sales_payement_details_array.put(sales_payment_details);
                    //Log.i("d_saletails_array", String.valueOf(sales_payement_details_array));
                    jsonObject.put("PaymentMethods", sales_payement_details_array);

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.i("ds___","jsonObject__"+jsonObject.toString());
            // Log.i("d_saleswwwent_details", String.valueOf(sales_payment_details));
            //Log.i("d_sales_paywwwils_array", String.valueOf(sales_payement_details_array));

            final JSONObject finalJsonObject = jsonObject;
            StringRequest stringRequest = new StringRequest(Request.Method.POST, syncUrl,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //sales_multiple_payement = new JSONArray();
                            // response code
                            String xmlString = response;

                            try {

                                //progressDialog.dismiss();
                            } catch (Exception e){

                            }
                            success(context);
                            //Log.i("JsonOBjRes", xmlString);
                            Document xmlparse = null;
//                        Document parse = XMLParseFunction(xmlString, xmlparse);
//                        for (int i = 0; i < parse.getElementsByTagName("submitSalesResult").getLength(); i++) {
//                            submitSalesResult = (parse.getElementsByTagName("submitSalesResult").getLength() > 0)
//                                    ? parse.getElementsByTagName("submitSalesResult").item(i).getTextContent() : " ";
//                        }
//                        DBFunc.ExecQuery("UPDATE BillPayment SET SaleSync = 1 WHERE BillNo = " + BillID, false);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    try {
                        //progressDialog.dismiss();
                    } catch (Exception e){

                    }
                    // Log.i("JsonOBjerror", String.valueOf(error));
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
                            "    <updatePaymentMethod xmlns=\"http://tempuri.org/\">\n" +
                            "      <companyCode>" + finalCompany_code + "</companyCode>\n" +
                            "      <json>" + finalJsonObject + "</json>\n" +
                            "    </updatePaymentMethod>\n" +
                            "  </soap12:Body>\n" +
                            "</soap12:Envelope>";
                    Log.i("temp___","temp___"+temp);
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
        //progressDialog = Query.showProgressDialog(context, Constraints.Downaloding);
    }

    public void getDept() {

        Cursor c_department = DBFunc.Query("SELECT ID FROM Dept ", true);
        if (c_department == null) {// no item found
            str_dept = "";
        } else if (!c_department.moveToNext()) {// no item found
            str_dept = "";
        } else {
            str_dept = c_department.getString(0);
        }

    }

    public void getSetMenu() {

        Cursor c_setmenu = DBFunc.Query("SELECT ID FROM Condi_Seq ", true);
        if (c_setmenu == null) {// no item found
            str_set_menu= "";
        } else if (!c_setmenu.moveToNext()) {// no item found
            str_set_menu = "";
        } else {
            str_set_menu = c_setmenu.getString(0);
        }

    }

//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Toast.makeText(this, "Clicked"+position, Toast.LENGTH_SHORT).show();
//        if (position == 0){
//            passwordDialog("department");
//        }else  if (position == 1){
//            getDept();
//            getSetMenu();
////                if (str_dept.length() == 0) {
////                    warning("Department");
////                }else{
//            passwordDialog("inventory");
//            //}
//        }else  if (position == 2){
//            getDept();
//            getSetMenu();
//            if (str_dept.length() == 0) {
//                warning("Department");
//            }else{
//                passwordDialog("promotion");
//            }
////                else if (str_set_menu.length() == 0){
////                    warning("Set Menu");
////                }
//        }else  if (position == 3){
//            resendSales();
//        }else{
//            passwordDialog("payment_type");
//        }
//        //passwordDialog("setmenu");
//    }

//    public void volleySubmitSales(double amount, double cost, final String bill_payment_type,String retail_ID,String company_code,String url,
//                                  String sale_status) {
//        //final String receipt_no = _bill_no;
//        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
//        Date date = new Date();
//        Log.i("__dateat(date)",dateFormat.format(date));
//        final String receipt_no = dateFormat.format(date) + bill_no;
//        Log.i("__rece_no",receipt_no);
//        Log.i("__bill_no",bill_no);
//        double chgamt = 0.0;
//        if (Double.valueOf(__volley_changeamt) < 0.0) {
//            chgamt = (-1) * Double.valueOf(__volley_changeamt);
//        }
//        double paytotal = Double.valueOf(_tot_a) - chgamt;
//        RequestQueue queue = Volley.newRequestQueue(this);
//        //RequestQueue queue = Volley.newRequestQueue(this, new HurlStack(null, getSocketFactory()));
//        final JSONObject jsonObject = new JSONObject();
//        int s_v = 1;
//        try {
//            java.util.Date utilDate = new java.util.Date();
//            Calendar cal = Calendar.getInstance();
//            cal.setTime(utilDate);
//            cal.set(Calendar.MILLISECOND, 0);
//            jsonObject.put(DeclarationConf.submitSalesTransNo, receipt_no);
//
//            //url = "http://" + url;
//            url = url;
//            Log.i("URLLLLL",url);
//            jsonObject.put(DeclarationConf.submitSalesRetailID, retail_ID);
//            jsonObject.put(DeclarationConf.submitSalesSalesDate, String.valueOf(new java.sql.Timestamp(utilDate.getTime())));
//            jsonObject.put(DeclarationConf.submitSalesSalesStatus, sale_status);
//            jsonObject.put(DeclarationConf.submitSalesMemberID, __volley_submit_sale_memberID);
//            Log.i("dfs__sale_staus",sale_status);
//            if (sale_status.equals("VOID")){
//                s_v = (-1);
//            }else{
//                s_v = 1;
//            }
//            Double tax_amttt = 0.0;
//            if (Double.valueOf(__volley_tax_rate) > 0.0) {
//                tax_amttt = s_v * Double.valueOf(__volley_tax_ttl);
//            }else{
//                tax_amttt = 0.0;
//            }
//            jsonObject.put(DeclarationConf.submitSalesSalesTaxTtl, tax_amttt);
//            jsonObject.put(DeclarationConf.submitSalesSalesRounding, Double.valueOf(__volley_round));
//            jsonObject.put("SalesTotalAmount", s_v * Double.valueOf(paytotal));
//            Log.i("dsf__BillID", String.valueOf(BillID));
//            Integer bId = BillID - 1 ;
//            Log.i("dsf__", String.valueOf(bId));
//            DeclarationConf.Sync_billID = String.valueOf(bId);
//            DeclarationConf.Sync_TransNo = receipt_no;
//            DeclarationConf.Sync_RetailID = retail_ID;
//            DeclarationConf.Sync_SalesDate = String.valueOf(new java.sql.Timestamp(utilDate.getTime()));
//            DeclarationConf.Sync_SalesStatus = sale_status;
//            DeclarationConf.Sync_MemberID = __volley_submit_sale_memberID;
//            DeclarationConf.Sync_SalesRounding = __volley_round;
//            DeclarationConf.Sync_SalesTaxTtl = String.valueOf(tax_amttt);
//            DeclarationConf.Sync_SalesTotalAmount = String.valueOf(s_v * Double.valueOf(paytotal));
//            DeclarationConf.Sync_ItemSalesID = "1";
//            DeclarationConf.Sync_SalesPaymentsID = "1";
//            DeclarationConf.SyncStatus = "FAIL";
//
//            String sql = "";
//            if (!sale_status.equals("SALES")){
//                sql = "SELECT ID,SalesTotalAmount,SalesTaxTtl,SalesRounding " +
//                        "FROM SyncSales WHERE billID = " + BillID;
//                Log.i("__sql_Salesss",sql);
//                Cursor c = DBFunc.Query(sql, false);
//                Integer findIdByStatus = 0;
//                Double SalesTotalAmount = 0.0;
//                Double SalesTaxTtl = 0.0;
//                Double SalesRounding = 0.0;
//                Integer neg_one = -1;
//                if (c !=null) {
//                    if (c.moveToNext()) {
//                        findIdByStatus =  c.getInt(0);
//                        SalesTotalAmount = neg_one * c.getDouble(1);
//                        SalesTaxTtl = neg_one * c.getDouble(2);
//                        SalesRounding = neg_one * c.getDouble(3);
//                    }
//                    c.close();
//                }
//                sql = "UPDATE SyncSales SET " +
//                        "SalesTotalAmount = '" + SalesTotalAmount + "'," +
//                        "SalesTaxTtl = '" + SalesTaxTtl + "'," +
//                        "SyncStatus = '" + "Void".toUpperCase() + "'," +
//                        "SalesRounding = '" + SalesRounding + "'," +
//                        "DateTime = " + System.currentTimeMillis() + " " +
//                        " WHERE ID = " + findIdByStatus;
//
//
//                Log.i("__up_sqls_Salesss",sql);
//                DBFunc.ExecQuery(sql, false);
//
//
//                sql = "SELECT ID,ItemTotal,ItemPrice,ItemQty,ItemTax " +
//                        "FROM SyncSalesItem WHERE SyncSalesID = " + findIdByStatus;
//                Log.i("_sql_Salesss1",sql);
//                c = DBFunc.Query(sql, false);
//                Integer findItemIdByStatus = 0;
//                Double ItemTotal = 0.0;
//                Double ItemPrice = 0.0;
//                Double ItemQty = 0.0;
//                Double ItemTax = 0.0;
//                if (c !=null) {
//                    while (c.moveToNext()) {
//                        findItemIdByStatus = c.getInt(0);
//                        ItemTotal = neg_one * c.getDouble(1);
//                        ItemPrice = neg_one * c.getDouble(2);
//                        ItemQty = neg_one * c.getDouble(3);
//                        ItemTax = neg_one * c.getDouble(4);
//
//                        sql = "UPDATE SyncSalesItem SET " +
//                                "ItemTotal = '" + ItemTotal + "'," +
//                                "ItemPrice = '" + ItemPrice + "'," +
//                                "ItemQty = '" + ItemQty + "'," +
//                                "ItemTax = '" + ItemTax + "'," +
//                                "DateTime = " + System.currentTimeMillis() + " " +
//                                " WHERE ID = " + findItemIdByStatus;
//                        DBFunc.ExecQuery(sql, false);
//                        Log.i("_upd_sql_Salesss1",sql);
//                    }
//                    c.close();
//                }
////                sql = "UPDATE SyncSalesItem SET " +
////                        "ItemTotal = '" + ItemTotal + "'," +
////                        "ItemPrice = '" + ItemPrice + "'," +
////                        "ItemQty = '" + ItemQty + "'," +
////                        "ItemTax = '" + ItemTax + "'," +
////                        "DateTime = " + System.currentTimeMillis() + " " +
////                        " WHERE ID = " + findItemIdByStatus;
////                DBFunc.ExecQuery(sql, false);
////                Log.i("_upd_sql_Salesss1",sql);
//
//                sql = "SELECT ID,SalesPayTtl,SalesBalTtl,ChangeAmount " +
//                        "FROM SyncSalesPayments WHERE SyncSalesID = " + findIdByStatus;
//                Log.i("_sql_Salesss2",sql);
//                c = DBFunc.Query(sql, false);
//                Integer findPaymentIdByStatus = 0;
//                Double SalesPayTtl = 0.0;
//                Double SalesBalTtl = 0.0;
//                Double ChangeAmount = 0.0;
//                if (c !=null) {
//                    while (c.moveToNext()) {
//                        findPaymentIdByStatus =  c.getInt(0);
//                        SalesPayTtl = neg_one * c.getDouble(1);
//                        SalesBalTtl = neg_one * c.getDouble(2);
//                        ChangeAmount = neg_one * c.getDouble(3);
//
//                        sql = "UPDATE SyncSalesPayments SET " +
//                                "SalesPayTtl = '" + SalesPayTtl + "'," +
//                                "SalesBalTtl = '" + SalesBalTtl + "'," +
//                                "ChangeAmount = '" + ChangeAmount + "'," +
//                                "DateTime = " + System.currentTimeMillis() + " " +
//                                "WHERE ID = " + findPaymentIdByStatus;
//                        Log.i("_upd__sql_Salesss2",sql);
//                        DBFunc.ExecQuery(sql, false);
//                    }
//                    c.close();
//                }
////                sql = "UPDATE SyncSalesPayments SET " +
////                        "SalesPayTtl = '" + SalesPayTtl + "'," +
////                        "SalesBalTtl = '" + SalesBalTtl + "'," +
////                        "ChangeAmount = '" + ChangeAmount + "'," +
////                        "DateTime = " + System.currentTimeMillis() + " " +
////                        "WHERE ID = " + findPaymentIdByStatus;
////                Log.i("_upd__sql_Salesss2",sql);
////                DBFunc.ExecQuery(sql, false);
//            }else {
//                SaveSyncSales();
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        //double realtotal = Double.valueOf(_total_price);
//        //double realtotal = Double.valueOf(_total_price) - Double.valueOf(__volley_changeamt);
//
//        JSONArray item_sales_details_array = new JSONArray();
//        try {
//            Object[] mvolleyItemStringArray = volleyItemStringList.toArray();
//            String dta_itemID = "0.0";
//            String dta_supBarcode = "0.0";
//            String dta_itemQty = "0.0";
//            String dta_itemUOMDesc = "0.0";
//            String dta_itemPrice = "0.0";
//            String dta_itemDisc = "0.0";
//            String dta_itemTax = "0.0";
//            String dta_itemTotal = "0.0";
//            String dta_itemproID = "0.0";
//            String dta_itemproType = "0.0";
//            String dta_itemproCode = "0.0";
//            String dta = "";
//            Log.i("ARRLenn", String.valueOf(mvolleyItemStringArray.length));
//            assert mvolleyItemStringArray != null;
//            for (int i = 0; i < mvolleyItemStringArray.length; i++) {
//                JSONObject item_sales_details = new JSONObject();
//                ItemDetail _dta = (ItemDetail) mvolleyItemStringArray[i];
//                dta_itemID = _dta.getItemID();
//                dta_itemQty = _dta.getItemQty();
//                dta_itemUOMDesc = _dta.getItemUOMDesc();
//                dta_itemPrice = _dta.getItemPrice();
//                dta_itemDisc = _dta.getItemDisc();
//                dta_itemTax = _dta.getItemTax();
//                dta_itemTotal = _dta.getItemTotal();
//                dta_itemproID = _dta.getItemproID();
//
//                dta_itemproType = _dta.getItemproTypeID();
//                dta_itemproCode = _dta.getItemproName();
//
//                item_sales_details.put(DeclarationConf.submitSalesItemID, dta_itemID);
//                item_sales_details.put(DeclarationConf.submitSalesItemQty, dta_itemQty);
//                //item_sales_details.put(DeclarationConf.submitSalesItemUOMDesc, dta_itemUOMDesc.toUpperCase());
//                //item_sales_details.put(DeclarationConf.submitSalesItemUOMDesc, "PCS1");
//                item_sales_details.put(DeclarationConf.submitSalesItemUOMDesc, "PCS");
//                item_sales_details.put(DeclarationConf.submitSalesItemPrice, dta_itemPrice);
//
//                item_sales_details.put(DeclarationConf.submitSalesItemDisc, 0.0);
//                item_sales_details.put(DeclarationConf.submitSalesItemTax, String.valueOf(dta_itemTax));
//
//
//                item_sales_details.put(DeclarationConf.submitSalesItemPromoID, String.valueOf(dta_itemproID));
//                item_sales_details.put(DeclarationConf.submitSalesItemPromoType, String.valueOf(dta_itemproType));
//                double d_submitSalesItemDiscval = Double.valueOf(dta_itemDisc);
//                String submitSalesItemDiscval = String.format("%.2f", d_submitSalesItemDiscval);
//                item_sales_details.put(DeclarationConf.submitSalesItemPromoDisc, submitSalesItemDiscval);
//                item_sales_details.put(DeclarationConf.submitSalesItemPromoTypeCode, String.valueOf(dta_itemproCode));
//                double d_submitSalesItemTotalval = Double.valueOf(dta_itemTotal);
//                String submitSalesItemTotalval = String.format("%.2f", d_submitSalesItemTotalval);
//                item_sales_details.put(DeclarationConf.submitSalesItemTotal, submitSalesItemTotalval);
//                item_sales_details_array.put(item_sales_details);
//            }
//            Object[] dvolleyItemStringArray = volleyItemStringList1.toArray();
//            Log.i("dvolleyItemStringArray", String.valueOf(dvolleyItemStringArray.length));
//            assert dvolleyItemStringArray != null;
//            for (int i = 0; i < dvolleyItemStringArray.length; i++) {
//                JSONObject item_sales_details = new JSONObject();
//                ItemDetail _dta = (ItemDetail) dvolleyItemStringArray[i];
//                dta_itemID = _dta.getItemID();
//                dta_supBarcode = _dta.getSupBarCode();
//                Log.i("d_dta_supBarcode",dta_supBarcode);
//                dta_itemQty = _dta.getItemQty();
//                dta_itemUOMDesc = _dta.getItemUOMDesc();
//                dta_itemPrice = _dta.getItemPrice();
//                dta_itemDisc = _dta.getItemDisc();
//                dta_itemTax = _dta.getItemTax();
//                dta_itemTotal = _dta.getItemTotal();
//                dta_itemproID = _dta.getItemproID();
//                dta_itemproType = _dta.getItemproTypeID();
//                dta_itemproCode = _dta.getItemproName();
//
//                item_sales_details.put(DeclarationConf.submitSalesItemID, dta_itemID);
//                item_sales_details.put(DeclarationConf.SupBarCode, dta_supBarcode);
//                item_sales_details.put(DeclarationConf.submitSalesItemQty, s_v * Double.valueOf(dta_itemQty));
//                //item_sales_details.put(DeclarationConf.submitSalesItemUOMDesc, dta_itemUOMDesc.toUpperCase());
//                item_sales_details.put(DeclarationConf.submitSalesItemUOMDesc, "PCS");
//                item_sales_details.put(DeclarationConf.submitSalesItemPrice, s_v * Double.valueOf(dta_itemPrice));
////				String submitSalesItemDiscval = String.format("%.2f", Double.valueOf(itm_dis));
//                item_sales_details.put(DeclarationConf.submitSalesItemDisc, 0.00);
//                item_sales_details.put(DeclarationConf.submitSalesItemTax, s_v * Double.valueOf(dta_itemTax));
//
//
//                //"PromoType":"0","PromoDisc":"0","PromoTypeCode":"0"
//                if (Integer.parseInt(dta_itemproID) == 0) {
//                    dta_itemproID = "0";
//                } else {
//                    dta_itemproID = dta_itemproID;
//                }
//                if (Integer.parseInt(dta_itemproType) == 0) {
//                    dta_itemproType = " ";
//                } else {
//                    dta_itemproType = dta_itemproType;
//                }
//                if (Integer.parseInt(dta_itemproCode) == 0) {
//                    dta_itemproCode = " ";
//                } else {
//                    dta_itemproCode = dta_itemproCode;
//                }
//                item_sales_details.put(DeclarationConf.submitSalesItemPromoID, 0.0);
//                item_sales_details.put(DeclarationConf.submitSalesItemPromoType, 0.0);
//                item_sales_details.put(DeclarationConf.submitSalesItemPromoDisc, 0.0);
//                item_sales_details.put(DeclarationConf.submitSalesItemPromoTypeCode, 0.0);
//
//                double d_submitSalesItemTotalval = Double.valueOf(dta_itemTotal);
//                String submitSalesItemTotalval = String.format("%.2f", d_submitSalesItemTotalval);
//                item_sales_details.put(DeclarationConf.submitSalesItemTotal, s_v * Double.valueOf(submitSalesItemTotalval));
//                item_sales_details_array.put(item_sales_details);
//
//
//
//                DeclarationConf.sync_submitSalesItemID = dta_itemID;
//                DeclarationConf.sync_SupBarCode = dta_supBarcode;
//                DeclarationConf.sync_submitSalesItemQty = String.valueOf(s_v * Double.valueOf(dta_itemQty));
//                DeclarationConf.sync_submitSalesItemUOMDesc = String.valueOf("PCS");
//                DeclarationConf.sync_submitSalesItemPrice = String.valueOf(s_v * Double.valueOf(dta_itemPrice));
//                DeclarationConf.sync_submitSalesItemDisc = "0";
//                DeclarationConf.sync_submitSalesItemTax = String.valueOf(s_v * Double.valueOf(dta_itemTax));
//                DeclarationConf.sync_submitSalesItemPromoID = "0";
//                DeclarationConf.sync_submitSalesItemPromoType = "0";
//                DeclarationConf.sync_submitSalesItemPromoDisc = "0";
//                DeclarationConf.sync_submitSalesItemPromoTypeCode = "0";
//                DeclarationConf.sync_submitSalesItemTotal = String.valueOf(s_v * Double.valueOf(submitSalesItemTotalval));
//
//                if (sale_status.equals("SALES")) {
//                    SyncActivity.saveSyncSalesItem();
//                }
//            }
//            jsonObject.put(DeclarationConf.submitSalesItemSales, item_sales_details_array);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        JSONObject sales_payment_details = new JSONObject();
//        JSONArray sales_payement_details_array = new JSONArray();
//        String option_val = "";
//        String option_id = "";
//        try {
//            if (bill_payment_type.length() > 0) {
//                option_val = bill_payment_type.toUpperCase();
//            }else{
//                option_val = __volley_pay_type.toUpperCase();
//            }
//            Cursor config_option = DBFunc.Query("SELECT ID FROM payment WHERE UPPER(Name) = '" + option_val +"'", true);
//            assert config_option != null;
//            while (config_option.moveToNext()) {
//                option_id = config_option.getString(0);
//            }
//
//            sales_payment_details.put("PaymentID", option_id);
//            //sales_payment_details.put("strPayment", bill_payment_type.toUpperCase());
//            String str_payment_typee = "0";
//            if (bill_payment_type.length() > 0) {
//                sales_payment_details.put("strPayment", bill_payment_type.toUpperCase());
//                str_payment_typee = bill_payment_type.toUpperCase();
//            } else {
//                str_payment_typee = __volley_pay_type.toUpperCase();
//            }
//            sales_payment_details.put("strPayment", str_payment_typee.toUpperCase());
//            //sales_payment_details.put("SalesPayTtl", String.format("%.2f", Double.valueOf(_tot_a)));
//            sales_payment_details.put("SalesPayTtl", s_v * Double.valueOf(_tot_a));
//
////            double chgamt = 0.0;
////            if (Double.valueOf(__volley_changeamt) < 0.0) {
////                chgamt = (-1) * Double.valueOf(__volley_changeamt);
////            }
////            double paytotal = Double.valueOf(_tot_a) - chgamt;
//
//            //sales_payment_details.put("SalesBalTtl", String.format("%.2f", Double.valueOf(paytotal)));
//            sales_payment_details.put("SalesBalTtl", s_v * Double.valueOf(paytotal));
//
//            //sales_payment_details.put("ChangeAmount", String.format("%.2f", Double.valueOf(chgamt)));
//            if (sale_status.equals("VOID")){
//                chgamt = 0.0;
//            }else {
//                chgamt = Double.valueOf(chgamt);
//            }
//            sales_payment_details.put("ChangeAmount", s_v * chgamt);
//
//            DeclarationConf.sync_PaymentID = option_id;
//            DeclarationConf.sync_strPayment = str_payment_typee.toUpperCase();
//            DeclarationConf.sync_SalesPayTtl = String.valueOf(s_v * Double.valueOf(_tot_a));
//            DeclarationConf.sync_SalesBalTtl = String.valueOf(s_v * Double.valueOf(paytotal));
//            DeclarationConf.sync_ChangeAmount = String.valueOf(s_v * chgamt);
//
//            sales_payement_details_array.put(sales_payment_details);
//            sales_multiple_payement.put(sales_payment_details);
//            jsonObject.put("SalesPayments",sales_payement_details_array);
//            //jsonObject.put("SalesPayments", sales_multiple_payement);
//
//            if (sale_status.equals("SALES")) {
//                SyncActivity.saveSyncSalesPayment();
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        Log.i("_jsonObjectaa", String.valueOf(jsonObject));
//        //double amt = amount + multiple_amount;
//
////        if (Double.valueOf(String.format("%.2f", amt)) < cost) {
////            multiple_amount = Double.valueOf(String.format("%.2f", amt));
////            return;
////        }
//
//        final String finalCompany_code = company_code;
//        final String finalCompany_code1 = company_code;
//        final String finalOption_id = option_id;
//        //final String finalOption_id = option_id;
//        final String finalUrl = url;
//        Log.i("_finalUrl", String.valueOf(finalUrl));
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        sales_multiple_payement = new JSONArray();
//                        // response code
//                        String xmlString = response;
//                        //{"PaymentMethods":[{"ID":10,"Name":"CASH","Full":"CASH","Display":"Y"}]}
//                        //updatePayment(finalCompany_code1, finalOption_id,bill_payment_type.toUpperCase(), finalUrl);
//                        //Log.i("ddd_error", xmlString);
//                        Document xmlparse = null;
//                        Document parse = XMLParseFunction(xmlString, xmlparse);
//                        //DBFunc.ExecQuery("UPDATE SyncSales SET SyncStatus = 'SUCCESS' WHERE billID = '" + (BillID - 1)+"'", false);
//                        DBFunc.ExecQuery("UPDATE SyncSales SET SyncStatus = 'SUCCESS',DateTime = "+System.currentTimeMillis()+" WHERE billID = '" + (BillID - 1) +"'", false);
//
////                        if (parse.getElementsByTagName("submitSalesResult") != null) {
////                            for (int i = 0; i < parse.getElementsByTagName("submitSalesResult").getLength(); i++) {
////                                submitSalesResult = (parse.getElementsByTagName("submitSalesResult").getLength() > 0)
////                                        ? parse.getElementsByTagName("submitSalesResult").item(i).getTextContent() : " ";
////                            }
//                        DBFunc.ExecQuery("UPDATE BillPayment SET SaleSync = 1 WHERE BillNo = " + BillID, false);
//                        //}
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.i("Sdsdserror", String.valueOf(error));
////                        // As of f605da3 the following should work
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
//                String temp = SyncSalesData(finalCompany_code,jsonObject);
////                String temp = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
////                        "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
////                        "  <soap:Body>\n" +
////                        "    <submitSales xmlns=\"http://tempuri.org/\">\n" +
////                        "      <companyCode>" + finalCompany_code + "</companyCode>\n" +
////                        "      <json>" + jsonObject + "</json>\n" +
////                        "    </submitSales>\n" +
////                        "  </soap:Body>\n" +
////                        "</soap:Envelope>";
//////                String temp = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
//////                        "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
//////                        "  <soap:Body>\n" +
//////                        "    <submitAdroindSales xmlns=\"http://tempuri.org/\">\n" +
//////                        "      <companyCode>" + finalCompany_code + "</companyCode>\n" +
//////                        "      <json>" + jsonObject + "</json>\n" +
//////                        "    </submitAdroindSales>\n" +
//////                        "  </soap:Body>\n" +
//////                        "</soap:Envelope>";
//                Log.i("APP", "request_String: " + temp);
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
//        Log.i("_finalUrl", String.valueOf(stringRequest));
//    }

    public static void SaveSyncSales(){
        String chkstr = "SELECT BillNo FROM SyncSales WHERE BillNo = '"+DeclarationConf.Sync_billNo+"' " +
                "AND SalesStatus = '"+DeclarationConf.Sync_SalesStatus+"' ";
        Cursor cchkstr = DBFunc.Query(chkstr,false);
        if (cchkstr != null){
            if (cchkstr.getCount() == 0){

                String sql = "INSERT INTO SyncSales " +
                        "(BillNo,TransNo,RetailID,SalesDate,SalesStatus,MemberID," +
                        "SalesRounding," +
                        "SalesTaxTtl,SalesTotalAmount," +
                        "SyncStatus,DateTime) " +
                        "VALUES ("
                        + "'"+DBFunc.PurifyString(DeclarationConf.Sync_billNo)+"',"
                        + "'"+DBFunc.PurifyString(DeclarationConf.Sync_TransNo)+"',"
                        + "'"+DBFunc.PurifyString(DeclarationConf.Sync_RetailID)+"',"
                        + "'"+DBFunc.PurifyString(DeclarationConf.Sync_SalesDate)+"',"
                        + "'"+DBFunc.PurifyString(DeclarationConf.Sync_SalesStatus)+"',"
                        + "'"+DBFunc.PurifyString(DeclarationConf.Sync_MemberID)+"',"
                        + "'"+DBFunc.PurifyString(DeclarationConf.Sync_SalesRounding)+"',"
                        + "'"+DBFunc.PurifyString(DeclarationConf.Sync_SalesTaxTtl)+"',"
                        + "'"+DBFunc.PurifyString(DeclarationConf.Sync_SalesTotalAmount)+"',"
                        + "'"+DBFunc.PurifyString(DeclarationConf.SyncStatus)+"',"
                        + System.currentTimeMillis()  +")";

                DBFunc.ExecQuery(sql, false);
            }
            cchkstr.close();
        }
        //DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth, System.currentTimeMillis(), "Inventory -> Add -> ItemID: "+ItemID);

        //saveSyncSalesItem();
//        String ItemID,String SupBarCode,String ItemQty,String ItemUOMDesc,String ItemPrice,
//                String ItemDisc,String ItemTax,String PromoID,String PromoType,String PromoDisc,
//                String PromoTypeCode,String ItemTotal,String PaymentID,String strPayment,String SalesPayTtl,
//                String SalesBalTtl,String ChangeAmount
    }

    //public static void updateSyncSales(Integer id_, String statusSyncStatus) {
    public static void updateSyncSales(Integer id_, String statusSyncStatus,String billNo) {
//        //DBFunc.ExecQuery("UPDATE SyncSales SET SyncStatus = 'SUCCESS' WHERE ID = " + id_, false);
//       // String sql = "UPDATE SyncSales SET SyncStatus = 'SUCCESS'," +
//        String  sql = "UPDATE SyncSales SET SyncStatus = '" + statusSyncStatus + "'," +
////                "SalesStatus = '"+statusSyncStatus+"', " +
//                "DateTime = " + System.currentTimeMillis() ;
        String  sql = "UPDATE SyncSales SET SyncStatus = '" + statusSyncStatus + "'," +
//                "SalesStatus = '"+statusSyncStatus+"', " +
                "DateTime = " + System.currentTimeMillis() ;
//        if (statusSyncStatus.toUpperCase().equals("SUCCESS")) {
//            sql += " WHERE ID = " + id_ + " ";
//        } else {
            sql += " WHERE BillNo = '" + billNo + "' ";
//        }

        DBFunc.ExecQuery(sql, false);
//                "DateTime = "+System.currentTimeMillis()+" WHERE BillNo = '" + bill_no + "' ", false);
//                "DateTime = "+System.currentTimeMillis()+" WHERE ID = " + id_, false);

    }

    public static void saveSyncSalesPayment() {
        Integer sync_sales_id = Query.findLatestID("ID","SyncSales",false);
        //Integer sync_sales_id = Query.findLatestID("ID","SyncSales",false);
        String sql = "INSERT INTO SyncSalesPayments " +
                "(PaymentID,strPayment,SalesPayTtl,SalesBalTtl,ChangeAmount,SyncSalesID,DateTime) " +
                "VALUES ("
                + "'"+DBFunc.PurifyString(DeclarationConf.sync_PaymentID)+"',"
                + "'"+DBFunc.PurifyString(DeclarationConf.sync_strPayment)+"',"
                + "'"+DBFunc.PurifyString(DeclarationConf.sync_SalesPayTtl)+"',"
                + "'"+DBFunc.PurifyString(DeclarationConf.sync_SalesBalTtl)+"',"
                + "'"+DBFunc.PurifyString(DeclarationConf.sync_ChangeAmount)+"',"
                + "'"+DBFunc.PurifyString(String.valueOf(sync_sales_id))+"',"
                + System.currentTimeMillis()  +")";

        DBFunc.ExecQuery(sql, false);

        //DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth, System.currentTimeMillis(), "Inventory -> Add -> ItemID: "+ItemID);
    }

//    private void resendSales() {
//        final Dialog dlgdate = new Dialog(SyncActivity.this);
//        dlgdate.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        LinearLayout lay = new LinearLayout(dlgdate.getContext());
//        lay.setOrientation(LinearLayout.VERTICAL);
//        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        dlgdate.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        dlgdate.addContentView(lay, lp);
//
//        final Calendar calfrom = Calendar.getInstance();
//        final Calendar calto = Calendar.getInstance();
//        calfrom.set(Calendar.HOUR_OF_DAY, 0);
//        calfrom.set(Calendar.MINUTE, 0);
//        calfrom.set(Calendar.SECOND, 0);
//        calfrom.set(Calendar.MILLISECOND, 0);
//
//        final DatePicker dp = new DatePicker(dlgdate.getContext());
//        dp.setCalendarViewShown(false);
//        dp.updateDate(calfrom.get(Calendar.YEAR), calfrom.get(Calendar.MONTH), calfrom.get(Calendar.DATE));
//        lay.addView(dp);
//
//        LinearLayout lay3 = new LinearLayout(dlgdate.getContext());
//        lay3.setOrientation(LinearLayout.HORIZONTAL);
//        lay.addView(lay3);
//
//        Button btn1 = new Button(dlgdate.getContext());
//        btn1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,1f));
//        btn1.setText(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 110));
//        btn1.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//
//                calfrom.set(dp.getYear(), dp.getMonth(), dp.getDayOfMonth(),0,0,0);
//                calfrom.set(Calendar.MILLISECOND, 0);
//                Log.i("AAA__","AAA_"+dp.getYear());
//                Log.i("AAA__","AAA1_"+dp.getMonth());
//                Log.i("AAA__","AAA2_"+dp.getDayOfMonth());
//                dlgdate.dismiss();
//                //passwordResendSalesDialog("Resend Sale",dp.getYear(), dp.getMonth(), dp.getDayOfMonth());
////                        btn_fromdate.setText(fmt.format(calfrom.getTime()));
////                        if(calfrom.after(calto)){
////                            calto.set(dp.getYear(), dp.getMonth(),dp.getDayOfMonth(),23,59,59);
////                            calto.set(Calendar.MILLISECOND, 999);
////                            btn_todate.setText(fmt.format(calto.getTime()));
////                        }
//                //dlgdate.dismiss();
//
//
//
//
//                final Dialog dlgdate = new Dialog(SyncActivity.this);
//                dlgdate.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                LinearLayout lay = new LinearLayout(dlgdate.getContext());
//                lay.setOrientation(LinearLayout.VERTICAL);
//                ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//                dlgdate.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//                dlgdate.addContentView(lay, lp);
//
//                final Calendar calto = Calendar.getInstance();
//                calto.set(Calendar.HOUR_OF_DAY, 0);
//                calto.set(Calendar.MINUTE, 0);
//                calto.set(Calendar.SECOND, 0);
//                calto.set(Calendar.MILLISECOND, 0);
//
//
//                final DatePicker to_dp = new DatePicker(dlgdate.getContext());
//                to_dp.setCalendarViewShown(false);
//                to_dp.updateDate(calto.get(Calendar.YEAR), calto.get(Calendar.MONTH), calto.get(Calendar.DATE));
//                lay.addView(to_dp);
//
//                LinearLayout lay3 = new LinearLayout(dlgdate.getContext());
//                lay3.setOrientation(LinearLayout.HORIZONTAL);
//                lay.addView(lay3);
//
//                Button btn1 = new Button(dlgdate.getContext());
//                btn1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,1f));
//                btn1.setText(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 110));
//                btn1.setOnClickListener(new View.OnClickListener(){
//                    @Override
//                    public void onClick(View v) {
//
//                        calto.set(to_dp.getYear(), to_dp.getMonth(), to_dp.getDayOfMonth(),0,0,0);
//
//                        Log.i("AAA__","AAAs_"+to_dp.getYear());
//                        Log.i("AAA__","AAA1s_"+to_dp.getMonth());
//                        Log.i("AAA__","AAA2s_"+to_dp.getDayOfMonth());
//
//
//                        calto.set(Calendar.MILLISECOND, 0);
//
//                        dlgdate.dismiss();
//                        passwordResendSalesDialog(SyncActivity.this,"Resend Sale",dp.getYear(), dp.getMonth(), dp.getDayOfMonth(),
//                                to_dp.getYear(), to_dp.getMonth(), to_dp.getDayOfMonth(),"Normal");
////                        btn_fromdate.setText(fmt.format(calfrom.getTime()));
////                        if(calfrom.after(calto)){
////                            calto.set(dp.getYear(), dp.getMonth(),dp.getDayOfMonth(),23,59,59);
////                            calto.set(Calendar.MILLISECOND, 999);
////                            btn_todate.setText(fmt.format(calto.getTime()));
////                        }
//                        //dlgdate.dismiss();
//
//
//                    }
//                });
//                lay3.addView(btn1);
//
//
////                Button btn2 = new Button(dlgdate.getContext());
////                btn2.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,1f));
////                btn2.setText(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 111));
////                btn2.setOnClickListener(new View.OnClickListener(){
////                    @Override
////                    public void onClick(View v) {
////                        Calendar cal = Calendar.getInstance();
////                        dp.updateDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
////                    }
////                });
////                lay3.addView(btn2);
//
//                Button btn3 = new Button(dlgdate.getContext());
//                btn3.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,1f));
//                btn3.setText(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 13));
//                btn3.setOnClickListener(new View.OnClickListener(){
//                    @Override
//                    public void onClick(View v) {
//                        dlgdate.dismiss();
//                    }
//                });
//                lay3.addView(btn3);
//                dlgdate.show();
//            }
//        });
//        lay3.addView(btn1);
//
//
////                Button btn2 = new Button(dlgdate.getContext());
////                btn2.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,1f));
////                btn2.setText(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 111));
////                btn2.setOnClickListener(new View.OnClickListener(){
////                    @Override
////                    public void onClick(View v) {
////                        Calendar cal = Calendar.getInstance();
////                        dp.updateDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
////                    }
////                });
////                lay3.addView(btn2);
//
//        Button btn3 = new Button(dlgdate.getContext());
//        btn3.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,1f));
//        btn3.setText(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 13));
//        btn3.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                dlgdate.dismiss();
//            }
//        });
//        lay3.addView(btn3);
//        dlgdate.show();
//    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static byte[] getHash(String password) {
        MessageDigest digest=null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        digest.reset();
        return digest.digest(password.getBytes(StandardCharsets.UTF_8));
        //byte[] postData = urlParameters.getBytes(Charset.forName("UTF-8"));

//        byte[] hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));
    }

    public static void sendPost(String urlValue,String partnerEmailValue,String storeId,String partnerId,
                                String partnerSecret,String status) {

        Log.i("sendPost","sendPost");
        Thread thread = new Thread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void run() {
                try {
                    URL url = new URL(urlValue);
                    Log.i("sendPost_","sendPosturl"+url);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json");
                    //conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    //conn.setRequestProperty("Accept","application/json");
                    conn.setRequestProperty("partnerEmail",partnerEmailValue);
                    //String authHeaderValue = "Basic " + new String(encodedAuth);
                    //String authHeaderValue = "Basic ";

                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("storeId", storeId);
                    jsonParam.put("partnerId",partnerId);



                    Log.i("JSON VALUE","sendPostjsonParam"+jsonParam);

//                        String plainText = "this is my plain text";
//                        String key = "simplekey";
//                        String iv = "1234123412341234";
//
//                        CryptLib cryptLib = new CryptLib();
//
//                        String encryptedString = cryptLib.encryptSimple(plainText, key, iv);
//                        System.out.println("encryptedString " + encryptedString);
//
//                        String decryptedString = cryptLib.decryptSimple(encryptedString, key, iv);
//                        System.out.println("decryptedString " + decryptedString);

////                        function generateHMACSignature(partnerSecret, requestBody) {
//                            //console.log("requestBody", requestBody);
                    //hashedPayload = CryptoJS.enc.Base64.stringify(CryptoJS.SHA256(requestBody));

                    //Step 1 . Hash request body using SHA256.
                    //Step 2 : Base64 encode the string generated in step 1.
                    byte[] hashedPayload = getHash(jsonParam.toString());
                    //String partnerSecret = "s34b78u6nw23ds1357b56e34";

//                            Step 3: Generate HmacSHA256 signature of the string generated in Step2 using partnerSecret provided.
                    //Step4 : Base64 encode the string generated in step 3

                    //String authHeaderValue = generateHashWithHmac256(Arrays.toString(hashedPayload),partnerSecret);

                    String authHeaderValue = JeripaySignature.getSign(jsonParam.toString(),partnerSecret);
//                            String authHeaderValue = JeripaySignature.getSign(
//                                    "{\"storeId\":\"632\", \"partnerId\":\"WEEBO\"}","s34b78u6nw23ds1357b56e34");
                    Boolean chkValuu = JeripaySignature.verify(authHeaderValue,jsonParam.toString(),partnerSecret);
                    Log.i("__authHeaderValue","__authHeaderValue_"+authHeaderValue);
                    Log.i("__authHeaderValue","chkValuu_"+chkValuu);


                    //Fu7PtY5Hfkr/NJHE8Mp9egXMP1CdmoPHrd/oTZp6+gM=
                    //Dy5wHW7eu266cQLpOSGMfvdmGnr2IxmaEXaGx7WRD/Y=



                    //String authHeaderValue = JeripaySignature.hmac256(jsonParam.toString(),partnerSecret);
                    //String authHeaderValue = JeripaySignature.hmac256(jsonParam.toString(),partnerSecret);

//
//                            var hmacDigest = CryptoJS.enc.Base64.stringify(CryptoJS.HmacSHA256(hashedPayload, partnerSecret));
//                            var authHeader = encodeURIComponent(hmacDigest);
                    //console.log("signature", authHeader)
                    //return authHeader;
////                        }
////
////The step below is important. Extra space and other special chars need to be removed
//                        reqBody = JSON.stringify(JSON.parse(request['data']))
//                        Step 1 . Hash request body using SHA256.
//                        Step 2 : Base64 encode the string generated in step 1.
//                        Step 3: Generate HmacSHA256 signature of the string generated in Step2 using partnerSecret provided.
//                        Step4 : Base64 encode the string generated in step 3
                    //FAAvQuFGCkR01plluNnQfpMDtVlfxnUNPVcWfYROWg8=

                    //Dy5wHW7eu266cQLpOSGMfvdmGnr2IxmaEXaGx7WRD/Y=
                    final String encodedURL = URLEncoder.encode(authHeaderValue, "UTF-8");
                    //final String encodedURL = "Dy5wHW7eu266cQLpOSGMfvdmGnr2IxmaEXaGx7WRD%2FY%3D";
                    //final String encodedURL = authHeaderValue;
                    Log.i("encodedURL__","encodedURL_"+encodedURL);
                    conn.setRequestProperty("Authorization",encodedURL);
                    conn.setDoOutput(true);
                    conn.setDoInput(true);


//                        {
//                            "storeId":"632",
//                                "partnerId":"WEEBO"
//                        }

                    Log.i("JSON", jsonParam.toString());
                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
                    os.writeBytes(jsonParam.toString());

                    os.flush();
                    os.close();

                    Log.i("STATUS____", String.valueOf(conn.getResponseCode()));
                    Log.i("MSG_____" , conn.getResponseMessage());

//                        BufferedReader br = null;
//                        if (100 <= conn.getResponseCode() && conn.getResponseCode() <= 399) {
//                            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//                            Log.i("VAlue__br","brr__"+br);
//                        } else {
//                            br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
//                            Log.i("VAlue__br","brr__err"+br);
//                        }

                    BufferedReader br = null;
                    if (conn.getResponseCode() == 200) {
                        br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        String strCurrentLine;
                        while ((strCurrentLine = br.readLine()) != null) {
                            //System.out.println(strCurrentLine);
                            Log.i("VAlue__br","strCurrentLine__"+strCurrentLine);
                            if (status.equals("products")) {
                                //{"success":true,
                                // "products":[{"id":"13191","name":"Beef","available":true},
                                // {"id":"13288","name":"Beef burger","available":true},
                                // {"id":"13259","name":"Coca Cola","available":true},
                                // {"id":"13008","name":"Coffee","available":true},
                                // {"id":"13192","name":"Coffee","available":true},
                                // {"id":"13009","name":"chicken bu","available":true},
                                // {"id":"13010","name":"chocolate ice cream edited","available":true}]}

                                JSONObject jsonprodutobj = new JSONObject(strCurrentLine);
                                String chkstatus_str = jsonprodutobj.getString("success");
                                Boolean chkstatus_b =Boolean.valueOf(chkstatus_str);
                                if (chkstatus_b){
                                    String products_str = jsonprodutobj.getString("products");
                                    JSONArray jsonarrproducts_str = new JSONArray(products_str);
                                    JSONObject productsObject = null;
                                    for (int i =0; i < jsonarrproducts_str.length(); i++) {
                                        productsObject = jsonarrproducts_str.getJSONObject(i);
                                        Log.i("productsObject__", String.valueOf(productsObject));
                                        String id = productsObject.getString("id");
                                        String name = productsObject.getString("name");
                                        String available = productsObject.getString("available");
                                    }
                                }


                            } else if (status.equals("menu")) {
                                //{"success":true,
                                // "products":[{"id":"13191","name":"Beef","available":true},
                                // {"id":"13288","name":"Beef burger","available":true},
                                // {"id":"13259","name":"Coca Cola","available":true},
                                // {"id":"13008","name":"Coffee","available":true},
                                // {"id":"13192","name":"Coffee","available":true},
                                // {"id":"13009","name":"chicken bu","available":true},
                                // {"id":"13010","name":"chocolate ice cream edited","available":true}]}

                                JSONObject jsonprodutobj = new JSONObject(strCurrentLine);
                                String chkstatus_str = jsonprodutobj.getString("success");
                                Boolean chkstatus_b =Boolean.valueOf(chkstatus_str);
                                if (chkstatus_b){
                                    String products_str = jsonprodutobj.getString("products");
                                    String categories_str = jsonprodutobj.getString("categories");
                                    JSONArray jsonarrproducts_str = new JSONArray(products_str);
                                    JSONArray jsonarrcategories_str = new JSONArray(categories_str);
                                    JSONObject productsObject = null;
                                    JSONObject categoriesObject = null;
                                    JSONObject variantsObject = null;
                                    JSONObject modifiersObject = null;
                                    JSONObject modifiersArrObject = null;
                                    for (int i =0; i < jsonarrcategories_str.length(); i++) {
                                        categoriesObject = jsonarrcategories_str.getJSONObject(i);
                                        Log.i("productsObject__", String.valueOf(productsObject));
                                        String id = categoriesObject.getString("id");
                                        String name = categoriesObject.getString("name");


                                        String chkExisting = TransactionDetailsActivity.ValidAndGetValue("Name", "Category", "Name", name, true);
                                        if (chkExisting.equals("0")) {
                                            String uniqueId = UUID.randomUUID().toString();
                                            CategoryModel ctM = new CategoryModel(0, uniqueId, name, id, "", 0);
                                            Query.saveCategory(ctM);
                                        }
                                    }
                                    for (int i =0; i < jsonarrproducts_str.length(); i++) {
                                        productsObject = jsonarrproducts_str.getJSONObject(i);
                                        Log.i("productsObject__", String.valueOf(productsObject));
                                        String id = productsObject.getString("id");
                                        String name = productsObject.getString("name");
                                        String price = productsObject.getString("price");
                                        String categories = productsObject.getString("categories");
                                        JSONArray jobjArr = new JSONArray(categories);
                                        String categoryId = jobjArr.getString(0);
                                        //String available = productsObject.getString("available");


                                        String chkExisting = TransactionDetailsActivity.ValidAndGetValue("NAME", "PLU", "NAME", name, true);
                                        if (chkExisting.equals("0")) {
                                            String uniqueId = UUID.randomUUID().toString();

                                            Query.SavePLU(name, uniqueId, price, id, "", "", "", "",
                                                    "", "", "0",
                                                    "0", "0","0" ,Integer.parseInt(categoryId), "",
                                                    0, "", "0", "Other");

                                        }

                                        Integer lstpluid = Query.findLatestID("ID","PLU",true);
                                        Integer onhandQtyLstpluid= Query.findOnHandQtyByPLUID(lstpluid);

                                        Query.UpdateStockAgent(lstpluid,onhandQtyLstpluid);



                                        String variants = productsObject.getString("variants");
                                        JSONArray variantsjArr = new JSONArray(variants);

                                        for (int j =0; j < variantsjArr.length(); j++) {
                                            variantsObject = variantsjArr.getJSONObject(j);
                                            Log.i("DinventoryObject__", String.valueOf(variantsObject));
                                            String modifierGroups = variantsObject.getString("modifierGroups");
                                            JSONArray modifierGroupsjArr = new JSONArray(modifierGroups);
                                            for (int k =0; k< modifierGroupsjArr.length(); k++) {
                                                modifiersObject = modifierGroupsjArr.getJSONObject(k);
                                                String modifiersStrVal = modifiersObject.getString("modifiers");
                                                JSONArray modifiersjArr = new JSONArray(modifiersStrVal);
                                                for (int h =0; h< modifiersjArr.length(); h++) {
                                                    try {

                                                        modifiersArrObject = modifiersjArr.getJSONObject(h);
                                                        String id_modi = modifiersArrObject.getString("id");
                                                        String name_modi = modifiersArrObject.getString("name");
                                                        String price_modi = "0";
                                                        try {
                                                            price_modi = modifiersArrObject.getString("price");
                                                        }catch (JSONException e){
                                                            price_modi = "0";
                                                        }

                                                        chkExisting = TransactionDetailsActivity.ValidAndGetValue("Name", "Product_Modifier", "Name", name, true);
                                                        if (chkExisting.equals("0")) {
                                                            Query.SaveModifier(name_modi, price_modi);
                                                        }
                                                    }catch (Exception e){

                                                    }

                                                }

                                            }
                                        }
                                    }
                                }


                            }
                        }
                    } else {
                        br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                        String strCurrentLine;
                        while ((strCurrentLine = br.readLine()) != null) {
                            //System.out.println(strCurrentLine);

                            Log.i("VAlue__br","strCurrentLine__err"+strCurrentLine);
                        }
                    }

                    conn.disconnect();




                } catch (Exception e) {
                    e.printStackTrace();
                    Log.i("Exception_noted_","err__"+e.getMessage());
                }
            }
        });

        thread.start();
    }

    @Override
    public void onClick(View v) {
        //Toast.makeText(this, "DSFDSF", Toast.LENGTH_SHORT).show();
        switch (v.getId()){
            case R.id.get_eunoia_menu:

                String menuURL = eunoia_url + "/menu";
                //String menuURL = "http://jerifoodeunoiastage.jeripay.com/api" + "/menu";
                Log.i("eunoia___","menuURL_"+menuURL);
                Log.i("eunoia___","eunoia_partnerEmail_"+eunoia_partnerEmail);
                Log.i("eunoia___","eunoia_storeId_"+eunoia_storeId);
                Log.i("eunoia___","eunoia_partnerId_"+eunoia_partnerId);
                Log.i("eunoia___","eunoia_partnerSecret_"+eunoia_partnerSecret);
                sendPost(menuURL,eunoia_partnerEmail,eunoia_storeId,eunoia_partnerId,eunoia_partnerSecret,"menu");
                break;
            case R.id.get_eunoia_products:
                String productURL = eunoia_url + "/products";
                sendPost(productURL,eunoia_partnerEmail,eunoia_storeId,eunoia_partnerId,eunoia_partnerSecret,"products");
                break;
            case R.id.sync_stockadjustment:
                //passwordDialog("department");
                StockAdjustSynFun(context);
                break;
            case R.id.sync_discount:
                //passwordDialog("department");
                volleySyncDiscount(context,"");
                break;
            case R.id.get_discountlist:
                //passwordDialog("department");
                volleyGetDiscount(context,"");
                break;
            case R.id.download_department:
                //passwordDialog("department");
                volleyGetCategory("");
                break;
            case R.id.download_inventory:
//                getDept();
//                getSetMenu();
//                if (str_dept.length() == 0) {
//                    warning("Department");
//                }else{
                //passwordDialog("inventory");
                volleyGetInventory("");
                //}
//                else if (str_set_menu.length() == 0){
//                    warning("Set Menu");
//                }
                break;
            case R.id.download_user_access:
                //UserAccessDownloadDialog(this);
                GetAllUserAccessRightDownloadDialog(this);
                break;
            case R.id.download_promotion:
//                getDept();
//                getSetMenu();
//                if (str_dept.length() == 0) {
//                    warning("Department");
//                }else{
                //passwordDialog("promotion");
                volleyGetPromotion();
//                }
                break;
            case R.id.resend_sale:

                SynFromDateDialogFun();

//                final Dialog dlgdate = new Dialog(SyncActivity.this);
//                dlgdate.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                LinearLayout lay = new LinearLayout(dlgdate.getContext());
//                lay.setOrientation(LinearLayout.VERTICAL);
//                ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//                dlgdate.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//                dlgdate.addContentView(lay, lp);
//
//                final Calendar calfrom = Calendar.getInstance();
//                final Calendar calto = Calendar.getInstance();
//                calfrom.set(Calendar.HOUR_OF_DAY, 0);
//                calfrom.set(Calendar.MINUTE, 0);
//                calfrom.set(Calendar.SECOND, 0);
//                calfrom.set(Calendar.MILLISECOND, 0);
//
//                final DatePicker dp = new DatePicker(dlgdate.getContext());
//                dp.setCalendarViewShown(false);
//                dp.updateDate(calfrom.get(Calendar.YEAR), calfrom.get(Calendar.MONTH), calfrom.get(Calendar.DATE));
//                lay.addView(dp);
//
//                LinearLayout lay3 = new LinearLayout(dlgdate.getContext());
//                lay3.setOrientation(LinearLayout.HORIZONTAL);
//                lay.addView(lay3);
//
//                Button btn1 = new Button(dlgdate.getContext());
//                btn1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,1f));
//                btn1.setText(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 110));
//                btn1.setOnClickListener(new View.OnClickListener(){
//                    @Override
//                    public void onClick(View v) {
//
//                        calfrom.set(dp.getYear(), dp.getMonth(), dp.getDayOfMonth(),0,0,0);
//                        calfrom.set(Calendar.MILLISECOND, 0);
//                        dlgdate.dismiss();
//                        final Dialog dlgdate = new Dialog(SyncActivity.this);
//                        dlgdate.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                        LinearLayout lay = new LinearLayout(dlgdate.getContext());
//                        lay.setOrientation(LinearLayout.VERTICAL);
//                        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//                        dlgdate.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//                        dlgdate.addContentView(lay, lp);
//
//                        final Calendar calto = Calendar.getInstance();
//                        calto.set(Calendar.HOUR_OF_DAY, 0);
//                        calto.set(Calendar.MINUTE, 0);
//                        calto.set(Calendar.SECOND, 0);
//                        calto.set(Calendar.MILLISECOND, 0);
//
//                        final DatePicker to_dp = new DatePicker(dlgdate.getContext());
//                        to_dp.setCalendarViewShown(false);
//                        to_dp.updateDate(calto.get(Calendar.YEAR), calto.get(Calendar.MONTH), calto.get(Calendar.DATE));
//                        lay.addView(to_dp);
//
//                        LinearLayout lay3 = new LinearLayout(dlgdate.getContext());
//                        lay3.setOrientation(LinearLayout.HORIZONTAL);
//                        lay.addView(lay3);
//
//                        Button btn1 = new Button(dlgdate.getContext());
//                        btn1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,1f));
//                        btn1.setText(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 110));
//                        btn1.setOnClickListener(new View.OnClickListener(){
//                            @Override
//                            public void onClick(View v) {
//
//                                calto.set(to_dp.getYear(), to_dp.getMonth(), to_dp.getDayOfMonth(),0,0,0);
//
//                                calto.set(Calendar.MILLISECOND, 0);
//
//                                dlgdate.dismiss();
//                                passwordResendSalesDialog(SyncActivity.this,"Resend Sale",dp.getYear(), dp.getMonth(), dp.getDayOfMonth(),
//                                        to_dp.getYear(), to_dp.getMonth(), to_dp.getDayOfMonth(),"Normal");
//
//                            }
//                        });
//                        lay3.addView(btn1);
//
//                        Button btn3 = new Button(dlgdate.getContext());
//                        btn3.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,1f));
//                        btn3.setText(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 13));
//                        btn3.setOnClickListener(new View.OnClickListener(){
//                            @Override
//                            public void onClick(View v) {
//                                dlgdate.dismiss();
//                            }
//                        });
//                        lay3.addView(btn3);
//                        dlgdate.show();
//                    }
//                });
//                lay3.addView(btn1);
//
//                Button btn3 = new Button(dlgdate.getContext());
//                btn3.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,1f));
//                btn3.setText(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 13));
//                btn3.setOnClickListener(new View.OnClickListener(){
//                    @Override
//                    public void onClick(View v) {
//                        dlgdate.dismiss();
//                    }
//                });
//                lay3.addView(btn3);
//                dlgdate.show();
                break;
            case R.id.sync_payment_type:
                //passwordDialog("payment_type");
                //volleyUpdatePayment("");
                volleyUpdatePayment(SyncActivity.this);
                break;
//            passwordDialog("setmenu");
            case R.id.delete_log:
                DeleteLog();
                break;
//            passwordDialog("setmenu");
        }
    }

    private void DeleteLog() {

        LayoutInflater li = LayoutInflater.from(SyncActivity.this);
        View promptsView = li.inflate(R.layout.searchprompt, null);
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SyncActivity.this,R.style.AlertDialogStyle);
        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.editTextDialogUserInput);


        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setNegativeButton(Constraints.OK,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                /** DO THE METHOD HERE WHEN PROCEED IS CLICKED*/
                                String user_text = (userInput.getText()).toString();

                                /** CHECK FOR USER'S INPUT **/
                                if (user_text.equals(Constraints.deleteLogPassword)){

                                    try {

                                        String sqlDeleteLog = "DELETE FROM UserLog";
                                        DBFunc.ExecQuery(sqlDeleteLog,false);

                                        DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                                                System.currentTimeMillis(), DBFunc.PurifyString("Saved-UserLog- " + sqlDeleteLog));


                                        Toast.makeText(getApplicationContext(),
                                                "Delete Logs Successfully!", Toast.LENGTH_SHORT).show();
                                    }catch (Exception e){
                                        DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                                                System.currentTimeMillis(), DBFunc.PurifyString("Err-UserLog- " + e.getMessage()));
                                    }



                                    dialog.dismiss();
                                }
                                else{
                                    Log.d(user_text,"string is empty");
                                    String message = "The password you have entered is incorrect." + " \n \n" + "Please try again!";
                                    AlertDialog.Builder builder = new AlertDialog.Builder(SyncActivity.this,R.style.AlertDialogStyle);
                                    builder.setTitle("Error");
                                    builder.setMessage(message);
                                    builder.setPositiveButton("Cancel", null);
                                    builder.setNegativeButton("Retry", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int id) {
                                            DeleteLog();
                                        }
                                    });
                                    builder.create().show();

                                }
                            }
                        })
                .setPositiveButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.dismiss();
                            }

                        }

                );

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();




    }

    private void SynFromDateDialogFun() {
        Calendar mcurrentDate=Calendar.getInstance();
        int mYear = mcurrentDate.get(Calendar.YEAR);
        int mMonth = mcurrentDate.get(Calendar.MONTH);
        int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
        int datePickerThemeResId = 0;
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            datePickerThemeResId = R.style.my_dialog_theme;
        }
//                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), AlertDialog.THEME_HOLO_DARK,
//                        new DatePickerDialog.OnDateSetListener() {
        //DatePickerDialog mDatePicker = new DatePickerDialog(getContext(),AlertDialog.THEME_HOLO_DARK,new DatePickerDialog.OnDateSetListener() {
        DatePickerDialog mDatePicker = new DatePickerDialog(SyncActivity.this, datePickerThemeResId, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                // TODO Auto-generated method stub
                /*      Your code   to get date and time    */
                String month = "";
                Integer monthh = selectedmonth + 1;
                if (monthh < 10){
                    month = "0" + String.valueOf(monthh);
                }else{
                    month = String.valueOf(monthh);
                }
                String day = "";
                Integer dayy = selectedday;
                if (dayy < 10){
                    day = "0" + String.valueOf(dayy);
                }else{
                    day = String.valueOf(dayy);
                }
                String selected_from_date = String.valueOf(selectedyear) + "-" + month + "-" + day;

                SynToDateDialogFun(selectedyear,month,day);
            }
        },mYear, mMonth, mDay);
        mDatePicker.show();
    }
    private void SynToDateDialogFun(final int fselectedyear, final String fmonth, final String fday) {
        Calendar mcurrentDate=Calendar.getInstance();
        int mYear = mcurrentDate.get(Calendar.YEAR);
        int mMonth = mcurrentDate.get(Calendar.MONTH);
        int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
        int datePickerThemeResId = 0;
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            datePickerThemeResId = R.style.my_dialog_theme;
        }
//                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), AlertDialog.THEME_HOLO_DARK,
//                        new DatePickerDialog.OnDateSetListener() {
        //DatePickerDialog mDatePicker = new DatePickerDialog(getContext(),AlertDialog.THEME_HOLO_DARK,new DatePickerDialog.OnDateSetListener() {
        DatePickerDialog mDatePicker = new DatePickerDialog(SyncActivity.this, datePickerThemeResId, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                // TODO Auto-generated method stub
                /*      Your code   to get date and time    */
                String month = "";
                Integer monthh = selectedmonth + 1;
                if (monthh < 10){
                    month = "0" + String.valueOf(monthh);
                }else{
                    month = String.valueOf(monthh);
                }
                String day = "";
                Integer dayy = selectedday;
                if (dayy < 10){
                    day = "0" + String.valueOf(dayy);
                }else{
                    day = String.valueOf(dayy);
                }
                String selected_from_date = String.valueOf(selectedyear) + "-" + month + "-" + day;

                passwordResendSalesDialog(SyncActivity.this,"Resend Sale",fselectedyear, fmonth, fday,
                        selectedyear, month, day,"Normal");
            }
        },mYear, mMonth, mDay);
        mDatePicker.show();
    }

//    public static void StockTakeSynFun(Context context){
//        volleyUpdatePayment(context);
//
//        GetSyncDataInformationAll();
//
//        JSONArray jarr = new JSONArray();
//
//        RequestQueue queue = Volley.newRequestQueue(context);
//        final String finalCompany_code = syncCompanyCode;
////        //stockadj json example
////[{"RetailerID":"2","IDRef":"STKTAKE/100023/2020","StkTakeDate":"2020-12-31 12:12:33",
////                "RemarkID":"1","StkTake_Remark":"TEST TAKE REMARK","StkAdjID":"","PostedDate":"2020-12-31",
////                "bitStockTakeByBatch":"Y","bitIncludeZeroQty":"Y","CreateTime":"2020-12-31 12:12:33","RecordStatus":"CLOSED",
////                "ItemTake":[{"serialNo":1,"ItemID":"c97ebdf3-33ed-11eb-8b1f-00155d01ca02","SystemQty":1,"CountQty":1,
////                "VarianceQty":0,"StkTake_DRemark":"test adj remark for item","UOM":"PCS","UnitID":"1"}]}]
//        JSONArray stockObjArr = new JSONArray();
//        JSONObject stockObj = new JSONObject();
//        String retailerID = "0";
//        String IDRef = "0";
//        String StkTakeDate = "0";
//        String RemarkID = "0";
//        String StkTake_Remark = "0";
//        String StkAdjID = "0";
//        String PostedDate = "0";
//        String bitStockTakeByBatch = "0";
//        String bitIncludeZeroQty = "0";
//        String CreateTime = "0";
//        String RecordStatus = "0";
//        JSONObject ItemTakeObj = new JSONObject();
//        String serialNo = "0";
//        String ItemID = "0";
//        String SystemQty = "0";
//        String VarianceQty = "0";
//        String StkTake_DRemark = "0";
//        String UOM = "0";
//        String UnitID = "0";
//        try {
//            stockObj.put("RetailerID",retailerID);
//            stockObj.put("IDRef",IDRef);
//            stockObj.put("StkTakeDate",StkTakeDate);
//            stockObj.put("RemarkID",RemarkID);
//            stockObj.put("StkTake_Remark",StkTake_Remark);
//            stockObj.put("StkAdjID",StkAdjID);
//            stockObj.put("PostedDate",PostedDate);
//            stockObj.put("bitStockTakeByBatch",bitStockTakeByBatch);
//            stockObj.put("bitIncludeZeroQty",bitIncludeZeroQty);
//            stockObj.put("CreateTime",CreateTime);
//            stockObj.put("RecordStatus",RecordStatus);
//
////            "ItemTake":[{"serialNo":1,"ItemID":"c97ebdf3-33ed-11eb-8b1f-00155d01ca02","SystemQty":1,"CountQty":1,
////                "VarianceQty":0,"StkTake_DRemark":"test adj remark for item","UOM":"PCS","UnitID":"1"}]}]
//            ItemTakeObj.put("serialNo",serialNo);
//            ItemTakeObj.put("ItemID",ItemID);
//            ItemTakeObj.put("SystemQty",SystemQty);
//            ItemTakeObj.put("VarianceQty",VarianceQty);
//            ItemTakeObj.put("StkTake_DRemark",StkTake_DRemark);
//            ItemTakeObj.put("UOM",UOM);
//            ItemTakeObj.put("UnitID",UnitID);
//
//            stockObj.put("ItemTake",ItemTakeObj);
//
//            stockObjArr.put(stockObj);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        String temp = Query.SubmitStockTransfer(finalCompany_code,stockObjArr);
//        String submitStockTransferResult = Query.StringRequestQueue(queue,syncUrl,"submitStockTransferResult",temp,"","","","SubmitStockTransfer");
//
//    }
    public static void StockAdjustSynFun(Context context) {
        volleyUpdatePayment(context);

        GetSyncDataInformationAll();

        RequestQueue queue = Volley.newRequestQueue(context);
        final String finalCompany_code = syncCompanyCode;
//       //stockadj json example
//[{"RetailerID":"2","IDRef":"STKADJ/100023/2020","StkAdj_Type":1,"StkAdjDate":"2020-12-31 12:12:33",
//"StkAdj_Remark":"TEST ADJ REMARK","CreateTime":"2020-12-31 12:12:33","RecordStatus":"CLOSED",
//"ItemAdjust":[{"ItemID":"c97ebdf3-33ed-11eb-8b1f-00155d01ca02","Qty":1,"ItemUOMDesc":"PCS",
//"StkAdj_DRemark":"test adj remark for item","StkAdj_Type":1,"RetailerID":"1","TransStatus":"FINISH"}]}]
        JSONArray stockObjArr = new JSONArray();
        JSONArray stockItemObjArr = new JSONArray();
        JSONObject stockObj = new JSONObject();
        String retailerID = "0";
        String terminalID = "0";
        String yyyy = "0";
        String storeno = "0";
        String IDRef = "0";
        String StkAdjDate = "0";
        String StkAdj_Remark = "0";
        String CreateTime = "0";
        String RecordStatus = "0";
        JSONObject ItemAdjustObj = new JSONObject();
        String ItemID = "0";
        String Qty = "0";
        String ItemUOMDesc = "0";
        String StkAdj_DRemark = "0";
        String StkAdj_Type = "0";
        String RetailerID = "0";
        String TransStatus = "0";

        String sqlStockAdj = "SELECT UUID,IDRef,StkAdj_Type,StkAdjDate,StkAdj_Remark,StkAdj_DRemark," +
                "TransStatus,VarianceQty," +
                "Qty,TransNo,PLUID,DateTime,strftime('%Y', DateTime / 1000, 'unixepoch') From StockAdjustment";
        Log.i("sqlStockAdj___", "sqlStockAdj_" + sqlStockAdj);

        Integer nextno = 0;
        Cursor Ctotalcount = DBFunc.Query(sqlStockAdj,true);
        if (Ctotalcount != null) {

            while (Ctotalcount.moveToNext()) {
                nextno = Ctotalcount.getCount();
            }
            Ctotalcount.close();
        }
        Cursor cc = DBFunc.Query(sqlStockAdj, true);
        if (cc != null){
            if (cc.moveToNext()) {

                retailerID = syncRetailID;
                storeno = store_no;
                terminalID = terminal_id;;
                yyyy = cc.getString(12);
                //IDRef = cc.getString(1).replace("\\","");
                //Log.i("DFDFDFDFDF__","DFDFAA"+cc.getString(1));
                //Log.i("DFDFDFDFDF__","DFDFAA1"+cc.getString(9));
                //IDRef = "STKADJ/"+cc.getString(9)+"/"+cc.getString(12);
//                IDRef = "STKADJ/"+cc.getString(1).contains("\\");
//                if (cc.getString(1).contains("\\")){
//                    IDRef = IDRef.replace("\\","AA");
//                }
                //STKADJ/3/2021_181  //_181  //storeno + terminalid
                String YStoreTerminalID = yyyy+"_"+storeno+terminalID;
                //IDRef = "STKADJ"+'/'+nextno+'/'+YStoreTerminalID;
//                "STKADJ\/2\/2021_31"
//                IDRef = "STKADJ".concat("/")+nextno;
//                IDRef = IDRef.concat("/")+YStoreTerminalID;
//                Log.i("DFDF____ewrdd","IDRef___"+IDRef.replace("/\\","DF"));
                IDRef = "STKADJ".concat(Constraints.StockAdjSymbmol)+nextno;
                IDRef = IDRef.concat(Constraints.StockAdjSymbmol)+YStoreTerminalID;

                //IDRef = IDRef.replace("_","/");

                StkAdjDate = cc.getString(3);
                StkAdj_Remark = cc.getString(4);
                StkAdj_Type = cc.getString(2);
                CreateTime = cc.getString(3);
                RecordStatus = "CLOSED";
                try {
                    stockObj.put("RetailerID", retailerID);
                    //stockObj.put("IDRef", "STKADJ"+'/'+nextno+'/'+YStoreTerminalID);
                    stockObj.put("IDRef",IDRef );
                    stockObj.put("StkAdj_Type",StkAdj_Type);
                    stockObj.put("StkAdjDate", StkAdjDate);
                    stockObj.put("StkAdj_Remark", StkAdj_Remark);
                    stockObj.put("CreateTime", CreateTime);
                    stockObj.put("RecordStatus", RecordStatus);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                cc.close();
            }
        }
        Cursor c = DBFunc.Query(sqlStockAdj,true);
        if (c != null){

            while (c.moveToNext()){
                ItemID = c.getString(0);
                Qty = c.getString(8);
                ItemUOMDesc = "PCS";
                StkAdj_DRemark = c.getString(5);
                StkAdj_Type = c.getString(2);
                TransStatus = c.getString(6);

                try {
                    //"ItemAdjust":[{"ItemID":"c97ebdf3-33ed-11eb-8b1f-00155d01ca02","Qty":1,"ItemUOMDesc":"PCS",
//"StkAdj_DRemark":"test adj remark for item","StkAdj_Type":1,"RetailerID":"1","TransStatus":"FINISH"}]}]
                    ItemAdjustObj.put("ItemID",ItemID);
                    ItemAdjustObj.put("Qty",Qty);
                    ItemAdjustObj.put("ItemUOMDesc",ItemUOMDesc);
                    ItemAdjustObj.put("StkAdj_DRemark",StkAdj_DRemark);
                    ItemAdjustObj.put("StkAdj_Type",StkAdj_Type);
                    ItemAdjustObj.put("RetailerID",RetailerID);
                    ItemAdjustObj.put("TransStatus",TransStatus);

                    stockItemObjArr.put(ItemAdjustObj);
                    stockObj.put("ItemAdjust",stockItemObjArr);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            stockObjArr.put(stockObj);
            c.close();
        }
        String temp = Query.SubmitStockAdjust(finalCompany_code,stockObjArr);
        String submitStockAdjustResult = Query.StringRequestQueue(context,queue,syncUrl,"submitStockAdjustResult",temp,"","","","SubmitStockAdjust");
    }
//    public static void StockTransferSynFun(Context context){
//        volleyUpdatePayment(context);
//
//        GetSyncDataInformationAll();
//
//        JSONArray jarr = new JSONArray();
//
//        RequestQueue queue = Volley.newRequestQueue(context);
//        final String finalCompany_code = syncCompanyCode;
////stocktransfer json example
////[{"FromRetailerID":"2","ToRetailerID":"1","IDRef":"STF00001","StkTransDate":"2020-12-24 12:12:33",
////                "StkTrans_Remark":"TEST REMARK","LastUser1stConfirm":"superuser","LastUpdate1stConfirm":"2020-12-24 12:12:33",
////                "RecordStatus1stConfirm":"CONFIRMED","CreateTime":"2020-12-24 12:12:33",
////                "ItemTransfer":[{"ItemID":"c97ebdf3-33ed-11eb-8b1f-00155d01ca02","Qty":1,"RcvdQty":1,"ItemUOMDesc":"PCS",
////                "StkTrans_DRemark":"test remark for item","FromRetailerID":"2","ToRetailerID":"1"}]}]
//
//        JSONArray stockObjArr = new JSONArray();
//        JSONObject stockObj = new JSONObject();
//        String FromRetailerID = "0";
//        String ToRetailerID = "0";
//        String IDRef = "0";
//        String StkTransDate = "0";
//        String StkTrans_Remark = "0";
//        String LastUser1stConfirm = "0";
//        String LastUpdate1stConfirm = "0";
//        String RecordStatus1stConfirm = "0";
//        String CreateTime = "0";
//        JSONObject ItemTransferObj = new JSONObject();
//        String ItemID = "0";
//        String Qty = "0";
//        String ItemUOMDesc = "0";
//        String StkTrans_DRemark = "0";
//        try {
//            stockObj.put("FromRetailerID",FromRetailerID);
//            stockObj.put("ToRetailerID",ToRetailerID);
//            stockObj.put("IDRef",IDRef);
//            stockObj.put("StkTransDate",StkTransDate);
//            stockObj.put("StkTrans_Remark",StkTrans_Remark);
//            stockObj.put("LastUser1stConfirm",LastUser1stConfirm);
//            stockObj.put("LastUpdate1stConfirm",LastUpdate1stConfirm);
//            stockObj.put("RecordStatus1stConfirm",RecordStatus1stConfirm);
//            stockObj.put("CreateTime",CreateTime);
//
////    "ItemTransfer":[{"ItemID":"c97ebdf3-33ed-11eb-8b1f-00155d01ca02","Qty":1,"RcvdQty":1,"ItemUOMDesc":"PCS",
//////                "StkTrans_DRemark":"test remark for item","FromRetailerID":"2","ToRetailerID":"1"}]}]
//            ItemTransferObj.put("ItemID",ItemID);
//            ItemTransferObj.put("Qty",Qty);
//            ItemTransferObj.put("ItemUOMDesc",ItemUOMDesc);
//            ItemTransferObj.put("StkTrans_DRemark",StkTrans_DRemark);
//            ItemTransferObj.put("FromRetailerID",FromRetailerID);
//            ItemTransferObj.put("ToRetailerID",ToRetailerID);
//
//            stockObj.put("ItemTransfer",ItemTransferObj);
//
//            stockObjArr.put(stockObj);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        String temp = Query.SubmitStockTake(finalCompany_code,stockObjArr);
//        String SubmitStockTakeResult = Query.StringRequestQueue(queue,syncUrl,"submitStockTakeResult",temp,"","","","SubmitStockTake");
//    }

    public static void CheckLicenseKey(Context context,String typedKey,String terminalKey) {
        GetSyncDataInformationAll();
        RequestQueue queue = Volley.newRequestQueue(context);
        String temp = Query.ValidateLicenseKey(typedKey);
        String CheckLicenseKeyResult = Query.StringRequestQueue(context,queue, download_url, "ValidateLicenseKeyResult", temp, terminalKey,
                "", "", "ValidateLicenseKey");



    }

    public static void UpdateTransZRead(Context context, ZClose zClose) {
        Cursor Cursor_Possys = Query.GetURLAndCodeFromPossys();
        if (Cursor_Possys != null) {
            while (Cursor_Possys.moveToNext()) {
                // syncRetailID = Cursor_Possys.getString(0);
                syncCompanyCode = Cursor_Possys.getString(1);
                syncUrl = Cursor_Possys.getString(2);
                //download_retail_ID = Cursor_Possys.getString(3);
                // download_company_code = Cursor_Possys.getString(4);
                // download_url = Cursor_Possys.getString(5);
            }
            Cursor_Possys.close();
        }

        JSONObject jsonArrObj = new JSONObject();
        RequestQueue queue = Volley.newRequestQueue(context);
        String result = "UpdateTransZReadResponse";
        JSONObject jsonObject = new JSONObject();
        UUID UpdateTransZReadID = UUID.randomUUID();
        try {
            jsonObject.put("ID",zClose.getUUID());
            jsonObject.put("RetailID",zClose.getRetailID());
            jsonObject.put("TransIDFrom",zClose.getTransIDFrom());
            jsonObject.put("TransIDTo",zClose.getTransIDTo());
            jsonObject.put("TransNoFrom",zClose.getTransNoFrom());
            jsonObject.put("TransNoTo",zClose.getTransNoTo());
            jsonObject.put("TransDate",zClose.getTransDate());
            jsonObject.put("ZReadNo",zClose.getZReadNo());
            jsonObject.put("ZReadDate",zClose.getZReadDate());
            jsonObject.put("ZReadUser",zClose.getZReadUser());
            jsonObject.put("TerminalID", zClose.getTerminalID());
            jsonObject.put("PendingSync", zClose.getPendingSync());
            jsonObject.put("SPBI01", "0");
            jsonObject.put("SPBI02", "0");
            jsonObject.put("SPBI03", "0");
            jsonObject.put("SPBI04", "0");
            jsonObject.put("SPBI05", "0");
            jsonObject.put("SPD01", "0.000000");
            jsonObject.put("SPD02", "0.000000");
            jsonObject.put("SPD03", "0.000000");
            jsonObject.put("SPD04", "0.000000");
            jsonObject.put("SPD05", "0.000000");
            jsonObject.put("SPI01", "0");
            jsonObject.put("SPI02", "0");
            jsonObject.put("SPI03", "0");
            jsonObject.put("SPI04", "0");
            jsonObject.put("SPI05", "0");
            jsonObject.put("SPC01", "N");
            jsonObject.put("SPC02", "N");
            jsonObject.put("SPC03", "N");
            jsonObject.put("SPC04", "N");
            jsonObject.put("SPC05", "N");
            jsonObject.put("SPV01", "");
            jsonObject.put("SPV02", "");
            jsonObject.put("SPV03", "");
            jsonObject.put("SPV04", "");
            jsonObject.put("SPV05", "");
            jsonObject.put("SPT01", null);
            jsonObject.put("SPT02", null);
            jsonObject.put("SPT03", null);
            jsonObject.put("SPT04", null);
            jsonObject.put("SPT05", null);
            jsonObject.put("SDT01", null);
            jsonObject.put("SDT02", null);
            jsonObject.put("SDT03", null);
            jsonObject.put("SDT04", null);
            jsonObject.put("SDT05", null);
            jsonObject.put("LastUser", zClose.getLastUser());
            jsonObject.put("LastUpdate", zClose.getLastUpdate());
            jsonObject.put("LockUser", "");
            jsonObject.put("LockUpdate", "0000-00-00 00:00:00");
            jsonObject.put("LockStatus", "0");
            jsonObject.put("RecordStatus", "READY");
            jsonObject.put("RecordUpdate", zClose.getRecordUpdate());
            jsonObject.put("QueueStatus", "READY");
//            jsonObject.put("ID","02536c00-4ce1-11ec-adbe-00090faa0001");
////        jsonObject.put("ID",UpdateTransZReadID);
//            jsonObject.put("RetailID","1");
//            jsonObject.put("TransIDFrom","a7bb4e9c-4b52-11ec-adbe-00090faa0001");
//            jsonObject.put("TransIDTo","f536e2d7-4cdd-11ec-adbe-00090faa0001");
//            jsonObject.put("TransNoFrom","KD0000111");
//            jsonObject.put("TransNoTo","KD0000113");
//            jsonObject.put("TransDate","2021-11-22");
//            jsonObject.put("ZReadNo","KD0000026");
//            jsonObject.put("ZReadDate","2021-11-22 12:43:03");
//            jsonObject.put("ZReadUser","superuser");
//            jsonObject.put("TerminalID", "1");
//            jsonObject.put("PendingSync", "N");
//            jsonObject.put("SPBI01", "0");
//            jsonObject.put("SPBI02", "0");
//            jsonObject.put("SPBI03", "0");
//            jsonObject.put("SPBI04", "0");
//            jsonObject.put("SPBI05", "0");
//            jsonObject.put("SPD01", "0.000000");
//            jsonObject.put("SPD02", "0.000000");
//            jsonObject.put("SPD03", "0.000000");
//            jsonObject.put("SPD04", "0.000000");
//            jsonObject.put("SPD05", "0.000000");
//            jsonObject.put("SPI01", "0");
//            jsonObject.put("SPI02", "0");
//            jsonObject.put("SPI03", "0");
//            jsonObject.put("SPI04", "0");
//            jsonObject.put("SPI05", "0");
//            jsonObject.put("SPC01", "N");
//            jsonObject.put("SPC02", "N");
//            jsonObject.put("SPC03", "N");
//            jsonObject.put("SPC04", "N");
//            jsonObject.put("SPC05", "N");
//            jsonObject.put("SPV01", "");
//            jsonObject.put("SPV02", "");
//            jsonObject.put("SPV03", "");
//            jsonObject.put("SPV04", "");
//            jsonObject.put("SPV05", "");
//            jsonObject.put("SPT01", null);
//            jsonObject.put("SPT02", null);
//            jsonObject.put("SPT03", null);
//            jsonObject.put("SPT04", null);
//            jsonObject.put("SPT05", null);
//            jsonObject.put("SDT01", null);
//            jsonObject.put("SDT02", null);
//            jsonObject.put("SDT03", null);
//            jsonObject.put("SDT04", null);
//            jsonObject.put("SDT05", null);
//            jsonObject.put("LastUser", "superuser");
//            jsonObject.put("LastUpdate", "2021-11-22 12:43:03");
//            jsonObject.put("LockUser", "");
//            jsonObject.put("LockUpdate", "0000-00-00 00:00:00");
//            jsonObject.put("LockStatus", "0");
//            jsonObject.put("RecordStatus", "READY");
//            jsonObject.put("RecordUpdate", "2021-11-22 12:43:03");
//            jsonObject.put("QueueStatus", "READY");


            JSONArray mJsonArray = new JSONArray();
            mJsonArray.put(jsonObject);

            Log.i("jsonObject____","jsonObject____"+jsonObject);

            jsonArrObj.put("tbltranszreadno",mJsonArray);

            Log.i("jsonObject____","jsonOjsonArrObjt____"+jsonArrObj);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("jsonArrObj___","jsonArrObj__dfd__"+jsonArrObj);
        String temp = Query.UpdateTransZReadSOAP(syncCompanyCode, jsonArrObj);


        Query.updateZReadByJsonSendingValue(zClose.getUUID(),jsonArrObj);


        String updateTransZReadResult = Query.StringRequestQueue(context,queue, syncUrl, result, temp,
                "", "", zClose.getUUID(), result);

    }
    @Override
    public void onBackPressed() {
        //ActivityCompat.finishAffinity(SyncActivity.this);
        Intent i = new Intent(context,SettingActivity.class);
        startActivity(i);
        finish();
    }
}

//String todate = to_year+"-"+(to_month+1)+"-"+to_dayOfMonth;

//                                    String sql = "SELECT BillNo,strftime('%Y-%m-%d %H:%M:%S', CloseDateTime / 1000, 'unixepoch')" +
//                                            ", case when Cancel =1 THEn 'VOID' ELSE 'SALES' END AS SalesStatus FROM Bill WHERE strftime('"+Constraints.sqldateformat+"', CloseDateTime / 1000, 'unixepoch') BETWEEN '" + fromdate +"'"+
//                                            " and '" + todate+"' and Cancel = 0 ORDER BY BillNo ASC";

//                                    String sql = "SELECT BillNo,strftime('%Y-%m-%d %H:%M:%S', CloseDateTime / 1000, 'unixepoch')" +
//                                            ", case when Cancel =1 THEN 'VOID' ELSE 'SALES' END AS SalesStatus FROM Bill WHERE strftime('"+Constraints.sqldateformat+"', CloseDateTime / 1000, 'unixepoch') BETWEEN '" + fromdate +"'"+
//                                            " and '" + todate+"' and Cancel = 0 ORDER BY BillNo ASC";
//                                    String sql = "SELECT BillNo,strftime('%Y-%m-%d %H:%M:%S', CloseDateTime / 1000, 'unixepoch')" +
//                                            ", case when Cancel =1 THEN 'VOID' ELSE 'SALES' END AS SalesStatus FROM Bill WHERE strftime('"+Constraints.sqldateformat+"', CloseDateTime / 1000, 'unixepoch')
//                                            BETWEEN '" + fromdate +"'"+
//                                            " and '" + todate+"' ORDER BY BillNo ASC";
//                                    String sql = "SELECT BillNo,strftime('%Y-%m-%d %H:%M:%S', DateTime / 1000, 'unixepoch'), STATUS FROM Sales " +
//                                            "WHERE strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch') BETWEEN '"+fromdate+"' and '"+todate+"' ORDER BY BillNo ASC";
//                                     String sql = "SELECT Sales.BillNo,strftime('%Y-%m-%d %H:%M:%S', Sales.DateTime / 1000, 'unixepoch'), SyncSales.SalesStatus FROM Sales " +
//                                             "left join SyncSales on SyncSales.BillNo = Sales.BillNo " +
//                                             "WHERE strftime('"+Constraints.sqldateformat+"', Sales.DateTime / 1000, 'unixepoch') BETWEEN '"+fromdate+"' and '"+todate+"' ORDER BY Sales.BillNo ASC";


