package com.dcs.myretailer.app;

import static com.google.zxing.BarcodeFormat.DATA_MATRIX;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
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
import com.dcs.myretailer.app.Cashier.MainActivity;
import com.dcs.myretailer.app.Cashier.RecyclerAdapter;
import com.dcs.myretailer.app.Checkout.CheckOutAdapter;
import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.Setting.SyncActivity;
import com.dcs.myretailer.app.databinding.ActivityTransactionDetailsBinding;
import com.dcs.myretailer.app.e600.printer.PrinterTester;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.pax.dal.IDAL;
import com.pax.dal.entity.EFontTypeAscii;
import com.pax.dal.entity.EFontTypeExtCode;
import com.pax.neptunelite.api.NeptuneLiteUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class TransactionDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    private static IDAL dal;
    private static Context appContext;
    public static ArrayList<String> sldQtyArr = new ArrayList<String>();
    public static ArrayList<String> sldNameArr = new ArrayList<String>();
    public static ArrayList<String> sltPriceTotalArr = new ArrayList<String>();
    public static ArrayList<String> sltPriceTotalEachArr = new ArrayList<String>();
    public static ArrayList<String> sldTaxID = new ArrayList<String>();
    public static ArrayList<String> sldCTaxName = new ArrayList<String>();
    public static ArrayList<String> sldCTaxAmount = new ArrayList<String>();
    public static ArrayList<String> sldDiscountName = new ArrayList<String>();
    public static ArrayList<String> sldDiscountType = new ArrayList<String>();
    public static ArrayList<String> sldDiscountValue = new ArrayList<String>();
    public static ArrayList<String> sldOpenPriceStatus = new ArrayList<String>();
    public static ArrayList<String> sldRemarks = new ArrayList<String>();
    public static ArrayList<Integer> billdetailsPID = new ArrayList<Integer>();
    public static ArrayList<String> sldCTaxType = new ArrayList<String>();
    public static ArrayList<String> PaymentTypeNameArr = new ArrayList<String>();
    public static ArrayList<String> PaymentTypeRemarksArr = new ArrayList<String>();
    public static String ProductQty,ProductName,ProductPrice = "";
    public static Integer sales_id = 0;
    public static String BillDateTime = "0";
    public static String PaymentTypeName = "0";
    public static String totalNettAmount = "0";
    public static double RoundingAdj = 0.0;
    public static double DiscountValueSales = 0.0;
    public static String DiscountTypeNameSales = "";
    public static String DiscountNameSales = "";
    public static Integer TotalQtySales = 0;
    public static double ServiceCharges = 0.0;
    public static double TotalTax = 0.0;
    public static Double SubTotal = 0.0;
    public static String TaxesAmount = "0";
    public static String TotalItemDisount = "0";
    public static String TotalBillDisount = "0";
    public static String STATUS = "";
    public static String BillNo = "";
    public static ArrayList<String>sldQtyArrCheckout = new ArrayList<String>();
    public static ArrayList<String>sldNameArrCheckout = new ArrayList<String>();
    public static ArrayList<String>sltPriceTotalArrCheckout = new ArrayList<String>();
    public static ArrayList<String>sltIDArrCheckout = new ArrayList<String>();
    public static ArrayList<String>sldItemDisArr = new ArrayList<String>();
    ActivityTransactionDetailsBinding binding = null;
    String status_for_jerifood = "0";
    public static String checkBillnodate = "";
    public static String dateFormat3 = "";
    static Resources resourceVal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_transaction_details);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        setTitle("Transactions Details");

        appContext = getApplicationContext();
        dal = getDal();

        resourceVal = getResources();

//        Intent intent = getIntent();
//        BillNo = intent.getStringExtra("BillNo");
        //        if (BillNo == null) {
        Intent intent = getIntent();
        BillNo = intent.getStringExtra("BillNo");

        String checktodaydate = "SELECT strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch') " +
                "FROM Sales WHERE BillNo = '"+BillNo+"'";
        Cursor cursorchecktodaydate = DBFunc.Query(checktodaydate,false);

        if (cursorchecktodaydate !=null ) {
            if (cursorchecktodaydate.moveToNext()) {
                checkBillnodate = cursorchecktodaydate.getString(0);
            }
            cursorchecktodaydate.close();
        }
//                %m/%d/%Y

        Date date1 = new Date();
         dateFormat3 = new SimpleDateFormat("MM/dd/yyyy").format(date1).toString();
//        }

        try {

            String data = intent.getStringExtra("data");
            //String data1 = intent.getStringExtra("data");

            //String output = JSON.Stringify(data);
            //Log.i("df___","output____"+output);
//            String data = " {\"orderUuid\":\"bc9149dd18ac4d348c662d60642d2663\",\"orderNo\":\"00001107\",\"total\":\"20.0\",\"createdTime\":\"2021-05-25 11:07:03\",\n" +
//                    "                \"orderReceivedTime\":\"2021-05-25 11:07:00\",\"deliveryDateTime\":\"25 May 11:27 AM\",\n" +
//                    "                \"driverCollectionTime\":\"\",\"deliveryType\":\"TAKEAWAY\",\"paidAmount\":\"20.0\",\"tableNo\":\"\",\n" +
//                    "                \"customerNote\":\" \",\"name\":\"ajmal customer\",\"email\":\"sdsdf@23.com\",\"address\":\"\",\"postcode\":\"\",\n" +
//                    "                \"phone\":\"+65 83381571\",\"payments\":[],\"discounts\":[],\n" +
//                    "            \"extraCharges\":[{\"name\":\"GST (7% inclusive)\",\"amount\":\"1.31\"}],\n" +
//                    "            \"items\":[{\"itemName\":\"Beef burger - Combo \",\"itemQuantity\":1,\"total\":\"20.0\",\"note\":\"( no eggs  )\",\n" +
//                    "                \"itemMeta\":[{\"metaItemId\":\"Choose drink \",\"metaItemValue\":\"Apple Juice * 1 \"},\n" +
//                    "            {\"metaItemId\":\"Choose 2 sides \",\"metaItemValue\":\"Beans * 1 \"}]}]}\"";

//            {"orderUuid":"bc9149dd18ac4d348c662d60642d2663","orderNo":"00001107","total":"20.0","createdTime":"2021-05-25 11:07:03",
//                    "orderReceivedTime":"2021-05-25 11:07:00","deliveryDateTime":"25 May 11:27 AM","driverCollectionTime":"",
//                    "deliveryType":"TAKEAWAY","paidAmount":"20.0","tableNo":"","customerNote":" ","name":"ajmal customer",
//                    "email":"sdsdf@23.com","address":"","postcode":"","phone":"+65 83381571","payments":[],
//                "discounts":[],"extraCharges":[{"name":"GST (7% inclusive)","amount":"1.31"}],
//                "items":[{"itemName":"Beef burger - Combo ","itemQuantity":1,"total":"20.0","note":"( no eggs  )",
//                    "itemMeta":[{"metaItemId":"Choose drink ","metaItemValue":"Apple Juice * 1 "},
//                {"metaItemId":"Choose 2 sides ","metaItemValue":"Beans * 1 "}]}]}"
//            JSONObject data = null;
//            Bundle extras = getIntent().getExtras();
//            Log.i("extras__","__extras"+extras);
//            if (extras != null) {
//                //data = extras.getBundle("data").toString();
//                //data = extras.getString("data");
//
//                try {
//                    data = new JSONObject(extras.getString("data"));
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
////                String str_owner = object.getString("owner");
////                String str_request_id = object.getString("request_id");
//            }


            status_for_jerifood = "1";
            if (data != null) {
                String orderUuid = "";
                String orderNo = "";
                String orderStatus = "";
                String total = "";
                String createdTime = "";
                String orderReceivedTime = "";
                String deliveryDateTime = "";
                String driverCollectionTime = "";
                String deliveryType = "";
                String paidAmount = "";
                String tableNo = "";
                String customerNote = "";
                String name = "";
                String email = "";
                String address = "";
                String postcode = "";
                String phone = "";
                String paymentsArr = "";
                String discountsArr = "";
                String extraChargesArr = "";
                JSONObject payments = null;
                JSONObject discounts = null;
                JSONObject extraCharges = null;
                JSONObject items = null;
                JSONObject itemMeta = null;
                String payments_name = "";
                String payments_amount = "";
                String discounts_name = "";
                String discounts_amount = "";
                String extraCharges_name = "";
                String extraCharges_amount = "";
                String itemsArr = "";
                String itemNamekp = "";
                String itemName = "";
                String itemModifierName = "";
                String itemQuantity = "";
                String itemTotal = "";
                String itemNote = "";
                String itemMetaArr = "";
                String metaItemId = "";
                String metaItemValue = "";
                String metaItemQtyValue = "";
                String nameKitchenPrinter = "";
                String plumodiKitchenPrinter = "";
                String qtyKitchenPrinter = "";
                try {
                    //{"orderUuid":"bc9149dd18ac4d348c662d60642d2663","orderNo":"00001107","total":"20.0","createdTime":"2021-05-25 11:07:03","orderReceivedTime":"2021-05-25 11:07:00","deliveryDateTime":"25 May 11:27 AM","driverCollectionTime":"","deliveryType":"TAKEAWAY","paidAmount":"20.0","tableNo":"","customerNote":" ","name":"ajmal customer","email":"sdsdf@23.com","address":"","postcode":"","phone":"+65 83381571","payments":[],"discounts":[],"extraCharges":[{"name":"GST (7% inclusive)","amount":"1.31"}],"items":[{"itemName":"Beef burger - Combo ","itemQuantity":1,"total":"20.0","note":"( no eggs  )","itemMeta":[{"metaItemId":"Choose drink ","metaItemValue":"Apple Juice * 1 "},{"metaItemId":"Choose 2 sides ","metaItemValue":"Beans * 1 "}]}]}
                    //{"orderUuid":"8f175e01176442a58852b81e36ba8b64","orderNo":"00001106","total":"15.0","createdTime":"2021-05-25 11:05:18","orderReceivedTime":"2021-05-25 10:47:00","deliveryDateTime":"25 May 11:07 AM","driverCollectionTime":"","deliveryType":"TAKEAWAY","paidAmount":"15.0","tableNo":"","customerNote":" ","name":"ajmal customer","email":"sdsdf@23.com","address":"","postcode":"","phone":"+65 83381571","payments":[],"discounts":[],"extraCharges":[{"name":"GST (7% inclusive)","amount":"0.98"}],"items":[{"itemName":"chocolate ice cream edited - Regular ","itemQuantity":1,"total":"15.0","note":"","itemMeta":[]}]}
                    // {"orderUuid":"8f175e01176442a58852b81e36ba8b64","orderNo":"00001106","total":"15.0",
//                            "createdTime":"2021-05-25 11:05:18","orderReceivedTime":"2021-05-25 10:47:00",
//                            "deliveryDateTime":"25 May 11:07 AM","driverCollectionTime":"",
//                            "deliveryType":"TAKEAWAY","paidAmount":"15.0","tableNo":"","customerNote":" ",
//                            "name":"ajmal customer","email":"sdsdf@23.com","address":"","postcode":"",
//                            "phone":"+65 83381571","payments":[],"discounts":[],
//                        "extraCharges":[{"name":"GST (7% inclusive)","amount":"0.98"}],
//                        "items":[{"itemName":"chocolate ice cream edited - Regular ","itemQuantity":1,"total":"15.0","note":"","itemMeta":[]}]}_
//                _{"orderUuid":"8f175e01176442a58852b81e36ba8b64","orderNo":"00001106","total":"15.0","createdTime":"2021-05-25 11:05:18",
//                        "orderReceivedTime":"2021-05-25 10:47:00","deliveryDateTime":"25 May 11:07 AM","driverCollectionTime":"",
//                        "deliveryType":"TAKEAWAY","paidAmount":"15.0","tableNo":"","customerNote":" ","name":"ajmal customer",
//                        "email":"sdsdf@23.com","address":"","postcode":"","phone":"+65 83381571","payments":[],"discounts":[],
//                    "extraCharges":[{"name":"GST (7% inclusive)","amount":"0.98"}],"" +
//                            "items":[{"itemName":"chocolate ice cream edited - Regular ","" +
//                            "itemQuantity":1,"total":"15.0","note":"","itemMeta":[]}]}

                    JSONObject dataJsonObj = new JSONObject(data);

                    orderUuid = dataJsonObj.getString("orderUuid");
                    orderNo = dataJsonObj.getString("orderNo");
                    orderStatus = dataJsonObj.getString("status");
//                    if (orderStatus.equals("PROCESSING")) {
                        total = dataJsonObj.getString("total");
                        createdTime = dataJsonObj.getString("createdTime");
                        orderReceivedTime = dataJsonObj.getString("orderReceivedTime");
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date2 = new Date();
                        try {
                            date2 = format.parse(createdTime);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        long timestamp = date1.getTime();


                        String dateFormat3 = CheckOutActivity.dateFormat55.format(date1).toString();
                        String dateFormat4 = CheckOutActivity.dateFormat555.format(date1).toString();
                        deliveryDateTime = dataJsonObj.getString("deliveryDateTime");
                        driverCollectionTime = dataJsonObj.getString("driverCollectionTime");
                        deliveryType = dataJsonObj.getString("deliveryType");
                        paidAmount = dataJsonObj.getString("paidAmount");
                        tableNo = dataJsonObj.getString("tableNo");
                        customerNote = dataJsonObj.getString("customerNote");
                        name = dataJsonObj.getString("name");
                        email = dataJsonObj.getString("email");
                        address = dataJsonObj.getString("address");
                        postcode = dataJsonObj.getString("postcode");
                        phone = dataJsonObj.getString("phone");

                        BillNo = orderNo;
//                String BillID = obj.getString("billID");
                        String billStatus = "SALES";
                        String totalItems = "0";

                        //
                        JSONObject ittm = new JSONObject();
                        itemsArr = dataJsonObj.getString("items");
                        JSONArray itemsJsonArr1 = new JSONArray(itemsArr);
                        for (int i = 0; i < itemsJsonArr1.length(); i++) {
                            ittm = itemsJsonArr1.getJSONObject(i);
                            try {
                                itemTotal += ittm.getInt("itemQuantity");
                            } catch (NullPointerException e) {
                                itemTotal = "0";
                            }
                        }
                        totalItems = String.valueOf(itemTotal);

                        String totalAmount = total;
                        //


                        String intTableno = tableNo;
                        String vchqueueno = "0";
                        String onlineOrderBill = "OFF";
                        String date = createdTime;
                        String dateTimeStamp = createdTime;
                        String openDateTimeStamp = createdTime;
                        String casherId = "0";
                        String casherName = "";
                        String balNo = "";
                        String receiptOrderStatus = deliveryType;


                        discountsArr = dataJsonObj.getString("discounts");
                        JSONArray discountJsonArr = new JSONArray(discountsArr);
                        for (int i = 0; i < discountJsonArr.length(); i++) {
                            discounts = discountJsonArr.getJSONObject(i);
                            discounts_name = discounts.getString("name");
                            discounts_amount = discounts.getString("amount");
                        }
                        extraChargesArr = dataJsonObj.getString("extraCharges");
                        JSONArray extraChargesJsonArr = new JSONArray(extraChargesArr);
                        for (int i = 0; i < extraChargesJsonArr.length(); i++) {
                            extraCharges = extraChargesJsonArr.getJSONObject(i);
                            extraCharges_name = extraCharges.getString("name");
                            extraCharges_amount = extraCharges.getString("amount");
                        }

                        Log.i("BillNo__", "BillNo__" + BillNo);

                        String checkingExistingBillNo = ValidAndGetValue("BillNo", "Bill", "BillNo", BillNo, false);
                        if (checkingExistingBillNo.equals("0")) {


                            String billSql = "INSERT INTO Bill (OpenDateTime, Cashier, CashierID, BalNo, " +
                                    "VchQueueNo,IntTableNo,ReceiptOrderStatus,TransNo, " +
                                    "OnlineOrderBill) VALUES " +
                                    "(" + timestamp + ",'" + casherName + "', " +
                                    casherId + ", '" + balNo + "' , " +
                                    "'" + vchqueueno + "', '" + intTableno + "','" + receiptOrderStatus + "','" + BillNo + "'," +
                                    "'" + onlineOrderBill + "' )";

                            DBFunc.ExecQuery(billSql, false);
                        }

                        Integer bId = Query.findLatestID("BillNo", "Bill", false);

                        checkingExistingBillNo = ValidAndGetValue("BillNo", "BillList", "BillNo", BillNo, false);
                        if (checkingExistingBillNo.equals("0")) {
                            String sql = "INSERT INTO BillList (BillID,BillNo,STATUS,TotalItems," +
                                    "Date,TableNo,QueueNo,OnlineOrderBill,TotalAmount,DateTime) " +
                                    "VALUES ('" + DBFunc.PurifyString(bId.toString()) + "'," +
                                    "'" + DBFunc.PurifyString(String.valueOf(BillNo)) + "','" +
                                    //DBFunc.PurifyString(blist.getSTATUS()) + "','" +
                                    DBFunc.PurifyString(billStatus) + "','" +
                                    DBFunc.PurifyString(totalItems) + "','" +
                                    DBFunc.PurifyString(deliveryDateTime) + "','" +
                                    DBFunc.PurifyString(intTableno) + "','" +
                                    DBFunc.PurifyString(vchqueueno) + "','" +
                                    DBFunc.PurifyString(onlineOrderBill) + "','" +
                                    DBFunc.PurifyString(totalAmount) + "'," +
                                    "" + timestamp + ")";

                            DBFunc.ExecQuery(sql, false);
                        }

                        itemsArr = dataJsonObj.getString("items");
                        JSONArray itemsJsonArr = new JSONArray(itemsArr);
                        nameKitchenPrinter = "";
                        qtyKitchenPrinter = "";
                        for (int i = 0; i < itemsJsonArr.length(); i++) {
                            items = itemsJsonArr.getJSONObject(i);
                            itemNamekp = items.getString("itemName");
//                        itemName = items.getString("itemName");
                            itemName = items.getString("itemName").split(" - ")[0];
                            try {
                                itemModifierName = items.getString("itemName").split(" - ")[1];
                            } catch (Exception e){
                                itemModifierName = "";
                            }
                            itemQuantity = items.getString("itemQuantity");


                            itemTotal = items.getString("total");
                            itemNote = items.getString("note");

                            itemMetaArr = items.getString("itemMeta");
                            JSONArray itemMetaJsonArr = new JSONArray(itemMetaArr);
                            String plumodi = "";
                            for (int j = 0; j < itemMetaJsonArr.length(); j++) {
                                itemMeta = itemMetaJsonArr.getJSONObject(j);
                                metaItemId = itemMeta.getString("metaItemId");
                                metaItemValue = itemMeta.getString("metaItemValue");
                                String metaItemValueKP = itemMeta.getString("metaItemValue");
                                metaItemValue = itemMeta.getString("metaItemValue").split(" \\* ")[0];
                                metaItemQtyValue = itemMeta.getString("metaItemValue").split(" \\* ")[1];
                                Log.i("SSFDS_", "DSFD__" + metaItemValueKP);
                                Log.i("SSFDS_", "metaItemValue__" + metaItemValue);
                                Log.i("SSFDS_", "metaItemQtyValue__" + metaItemQtyValue);

                                //checkingExistingBillNo = ValidAndGetValue("BillNo", "PLUModi",
//                                    "BillNo", BillNo, false);
                                //if (checkingExistingBillNo.equals("0")) {
                                Log.i("DSQQ__", "Sqq_" + metaItemValue + "__");
                                Log.i("DSQQ__", "Sqq_" + metaItemQtyValue + "__");
                                String productUUID = ValidAndGetValue("UUID", "PLU", "NAME", itemName, true);
                                if (productUUID != null) {
                                    String billid = Query.findBillIDByBillNo(BillNo);
                                    //String sqlPLUModi = "Select ID from PLUModi Where ItemID = '" + productUUID + "'" +
                                    String sqlPLUModi = "Select ID from PLUModi Where PLU_UUID = '" + productUUID + "'" +
                                            " AND BillNo = '" + BillNo + "' AND ModiName = '" + metaItemValue + "' ";

                                    Cursor CursorPLUModi = DBFunc.Query(sqlPLUModi, false);
                                    if (CursorPLUModi != null) {
                                        if (CursorPLUModi.getCount() == 0) {
//                                        String sql = "INSERT INTO PLUModi (ItemID,ItemName,ModiName,BillNo,BillID) VALUES (";
//                                        sql += "'" + BillNo + "', ";
//                                        sql += "'0',";
//                                        sql += "'" + itemName + "',";
//                                        sql += "'" + BillNo + "',";
//                                        sql += "'0')";

                                            String modiID = ValidAndGetValue("ID", "Product_Modifier", "Name", metaItemValue, true);

                                            String modiName = ValidAndGetValue("Name", "Product_Modifier", "Name", metaItemValue, true);
                                            String modiPrice = ValidAndGetValue("Price", "Product_Modifier", "Name", metaItemValue, true);

//                                            String sql = "INSERT INTO PLUModi (PLU_UUID,ItemID,ItemName,ModiName,Qty,OpenPriceStatus,BillNo,BillID) VALUES (";
//                                            sql += "'" + productUUID + "', ";
//                                            sql += "'" + modiID + "', ";
//                                            sql += "'" + itemName + "',";
//                                            sql += "'" + modiName + "',";
//                                            sql += "'" + metaItemQtyValue + "',";
//                                            sql += "'',";
//                                            sql += "'" + BillNo + "',";
//                                            sql += "'" + billid + "')";
//
//                                            Log.i("Sf_UpdateBillPLU", sql);
//                                            DBFunc.ExecQuery(sql, false);

                                           try {
                                               Query.SavePLUModi(productUUID, Integer.parseInt(modiID), itemName, modiName, modiPrice,
                                                       Integer.parseInt(metaItemQtyValue), "", "",BillNo, billid,"-2");
                                           }catch (Exception e){

                                           }
                                        }
                                        CursorPLUModi.close();
                                    }
                                }
                                //}

                                plumodi += "\n " + metaItemValueKP;
                            }
                            nameKitchenPrinter += itemQuantity + "* " + itemNamekp + " " + "\n " + itemNote + " " + plumodi;
                            //qtyKitchenPrinter += itemQuantity;

                            String item_name = itemName;
                            String item_qty = itemQuantity;
                            String item_price = itemTotal;
                            String item_discount_amount = "0";
                            String productId = ValidAndGetValue("ID", "PLU", "NAME", item_name, true);
                            String categoryId = "0";
                            String categoryName = "";
                            String Cancel = "SALES";
                            String taxId = "";
                            String taxtype = "";
                            String taxName = "";
                            String taxAmount = "";
                            String disId = "";
                            String disName = "";
                            String disType = "";
                            String disTypeName = "";
                            String disValue = "";
                            String aftDisAmt = "";
                            String openPriceStatus = "";
                            String beforeOpenPrice = "";

                            String dateTimeStampbd = createdTime;

//                    String[] item_price_ary = "abc".split("");
//
//                    Log.i("item_name__","item_name__"+item_name);
//                    Log.i("item_qty__","item_qty__"+item_qty);
//                    Log.i("item_price__","item_price__"+item_price);

                            //String OnlineOrderBill = "OFF";
                            //String dateFormat3 = CheckOutActivity.dateFormat55.format(new Date()).toString();
                            //String dateFormat3 = CheckOutActivity.dateFormat55.format(date1).toString();
//                    try {
                            //checkingExistingBillNo = ValidAndGetValue("BillNo", "DetailsBillProduct",
                                   //"BillNo", BillNo, false);
                            String q = "SELECT BillNo FROM DetailsBillProduct WHERE BillNo = '"+BillNo+"' " +
                                    "AND ProductID = '"+productId+"' ";

                            Cursor cursor = DBFunc.Query(q,false);
                            String returnVal = "0";
                            if (cursor != null){
                                if (cursor.moveToNext()){
                                    checkingExistingBillNo = cursor.getString(0);
                                }
                            }

                            if (checkingExistingBillNo.equals("0")) {
                                String sql = "INSERT INTO DetailsBillProduct (UUID,BillNo,OnlineOrderBill,ProductQty,ProductName,ProductPrice,BillDetailsID,BillDateTime," +
                                        "ItemDiscountAmount,ProductID,CategoryID,CategoryName,vchQueueNo,intTableNo,Cancel," +
                                        "BillID,TaxID,TaxType,TaxName,TaxAmount,DiscountID,DiscountName,DiscountType,DiscountTypeName," +
                                        "DiscountValue,AfterDiscountAmount,OpenPriceStatus,BeforeOpenPrice,Note,ModifierGroup,BillID,DateTime) VALUES (";
                                sql += "'" + UUID.randomUUID().toString() + "', ";
                                sql += "'" + BillNo + "', ";
                                sql += "'" + onlineOrderBill + "', ";
                                sql += "'" + item_qty + "', ";
                                sql += "'" + item_name + "', ";
                                if (item_price != null) {
                                    sql += "'" + String.format("%.2f", Double.valueOf(item_price)) + "', ";
                                } else {
                                    sql += "'',";
                                }
                                sql += "'" + 0 + "', ";
                                sql += "'" + dateFormat3 + "', ";
                                if (item_discount_amount != null) {
                                    sql += "'" + String.format("%.2f", Double.valueOf(item_discount_amount)) + "', ";
                                } else {
                                    sql += "'',";
                                }
                                sql += "'" + productId + "', ";
                                sql += "'" + categoryId + "', ";
                                sql += "'" + categoryName + "', ";
                                sql += "'" + vchqueueno + "', ";
                                sql += "'" + intTableno + "', ";
                                sql += "'" + Cancel + "', ";
                                sql += "'" + bId + "', ";
                                sql += "'" + taxId + "', ";
                                sql += "'" + taxtype + "', ";
                                sql += "'" + taxName + "', ";

                                if (taxAmount != null) {
                                    try {

                                        sql += "'" + String.format("%.2f", Double.valueOf(taxAmount)) + "', ";
                                    } catch (NumberFormatException e) {
                                        sql += "'',";
                                    }
                                } else {
                                    sql += "'',";
                                }
                                sql += "'" + disId + "', ";
                                sql += "'" + disName + "', ";
                                sql += "'" + disType + "', ";
                                sql += "'" + disTypeName + "', ";
                                if (disValue != null) {
                                    try {

                                        sql += "'" + String.format("%.2f", Double.valueOf(disValue)) + "', ";
                                    } catch (NumberFormatException e) {
                                        sql += "'',";
                                    }
                                } else {
                                    sql += "'',";
                                }
                                if (aftDisAmt != null) {
                                    try {

                                        sql += "'" + String.format("%.2f", Double.valueOf(aftDisAmt)) + "', ";
                                    } catch (NumberFormatException e) {
                                        sql += "'',";
                                    }
                                } else {
                                    sql += "'',";
                                }
                                sql += "'" + openPriceStatus + "', ";
                                ;

                                if (beforeOpenPrice != null) {
                                    try {

                                        sql += "'" + String.format("%.2f", Double.valueOf(beforeOpenPrice)) + "', ";
                                    } catch (NumberFormatException e) {
                                        sql += "'',";
                                    }
                                } else {
                                    sql += "'',";
                                }
                                ;
                                sql += "'" + itemNote + "', ";
                                sql += "'" + itemModifierName + "', ";
                                sql += "'" + bId + "', ";
                                sql += timestamp + ")";

                                DBFunc.ExecQuery(sql, false);
                            }
                        }


                        //BillPayment
                        paymentsArr = dataJsonObj.getString("payments");

                        if (paymentsArr.length() > 2) {
                            JSONArray paymentJsonArr = new JSONArray(paymentsArr);
                            for (int i = 0; i < paymentJsonArr.length(); i++) {
                                payments = paymentJsonArr.getJSONObject(i);
                                payments_name = payments.getString("name");


                                saveBillPayments(payments_name, payments_amount, timestamp);
                            }

                            saveSales(totalItems, payments_amount, payments_name, extraCharges_amount, extraCharges_name, dateFormat3, dateFormat4, timestamp);
                        } else {

                            saveBillPayments("CASH", totalAmount, timestamp);
                            saveSales(totalItems, totalAmount, "CASH", extraCharges_amount, extraCharges_name, dateFormat3, dateFormat4, timestamp);
                        }

                    //}else {
                        if (orderStatus.equals("CANCELLED")){

                            String sales_id_for_reject = ValidAndGetValue("ID", "Sales", "BillNo", BillNo, false);

                             CashLayoutActivity.cancelBill(BillNo,TransactionDetailsActivity.this,Integer.parseInt(sales_id_for_reject));
                            status_for_jerifood = "1";

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (orderStatus.equals("PROCESSING")) {
                    //KitchenPrinterFunction
                    String orderNoKitchenPrinter = BillNo;
//                String nameKitchenPrinter = "";
//                String plumodiKitchenPrinter = "";
//                String qtyKitchenPrinter = "";

                    PrinterTester printer_tester = PrinterTester.getInstance();

                    printer_tester.init();
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inScaled = false;


                    String str = "Order No# " + orderNoKitchenPrinter;
//                str += "\n" +nameKitchenPrinter;
//                str += "\n" +"(" + plumodiKitchenPrinter +")";
//                str += "\n" + qtyKitchenPrinter;
                    //str += "\n" + qtyKitchenPrinter + " * " + nameKitchenPrinter;
                    str += "\n" +nameKitchenPrinter;
                    str += "\n";
                    str += "\n";
                    str += "\n";

                    printer_tester.fontSet((EFontTypeAscii) EFontTypeAscii.FONT_16_32, (EFontTypeExtCode) EFontTypeExtCode.FONT_16_32);
                    printer_tester.printStr(str, null);
                    //printer_tester.cutPaper(1);
                    for (int printcount = 0; printcount < MainActivity.receiptCount; printcount++) {
                        final String status1 = printer_tester.start();
                    }
                }
            }
            status_for_jerifood = "1";
        } catch (NullPointerException e){
            status_for_jerifood = "1";
        } catch (Exception e){
            status_for_jerifood = "1";
        }

        ScreenDisplayFun();

        myToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_close_white));
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



        updateTransactionFun();
    }

    private void updateTransactionFun() {
        getDetailsBillProductAll();
        getBillDetailsAll();

        TransactionDetailsAdapter customAdapter = new TransactionDetailsAdapter(getApplicationContext(),
                sldQtyArr,  sldNameArr,  sltPriceTotalEachArr);
        binding.itemsListView.setAdapter(customAdapter);

        getDetailsBillProductAllByQty(BillNo);


        CheckOutAdapter checkoutAdapter = new CheckOutAdapter(getApplicationContext(),BillNo,
                sldQtyArrCheckout, sldNameArrCheckout, sltPriceTotalArrCheckout, sltIDArrCheckout,sldItemDisArr, sldDiscountName, sldDiscountType,
                sldDiscountValue,sldOpenPriceStatus,sldRemarks,billdetailsPID);

        binding.checkOutListView.setAdapter(checkoutAdapter);

//        String tblName = "0";
//        Cursor C_DetailsBillProduct = Query.SearchBillProductByBillNo(BillNo, Constraints.NoGroupBy);
//        if (C_DetailsBillProduct != null) {
//            if (C_DetailsBillProduct.moveToNext()) {
//                tblName = C_DetailsBillProduct.getString(3);
//            }
//            C_DetailsBillProduct.close();
//        }
//
        binding.transactionBillNo.setText("Bill #"+BillNo);


        Integer sale_id = Query.GetOnlineSalesByBillNo(BillNo);

        if (sale_id > 0){

            binding.laySalessummary.setVisibility(View.VISIBLE);
            binding.laySaleSummaryDetails.setVisibility(View.VISIBLE);
            binding.layTotalAmt.setVisibility(View.VISIBLE);

            binding.LinerarRefundOrCancel.setVisibility(View.GONE);
            binding.LinerarRefundOrCancelTxt.setVisibility(View.GONE);

            String onlineorderbill = Query.GetOnlineOrderBillStatus(BillNo);

            if (onlineorderbill.equals("ON")){
                String collectedOrDeliveryStatus = Query.GetCollectedOrDeliveryStatusByBillNo(BillNo);
                binding.LinerarCollectedOrDeliveryTxt.setVisibility(View.VISIBLE);
                if (collectedOrDeliveryStatus != null && !collectedOrDeliveryStatus.equals("null")){
                    if (collectedOrDeliveryStatus.length() > 0 && collectedOrDeliveryStatus.equals("Collected")){
                        binding.btnCollectedBillTxt.setVisibility(View.VISIBLE);
                        binding.btnCollectedBillTxt.setTextColor(ContextCompat.getColor(TransactionDetailsActivity.this, R.color.dark_blue));
                        binding.btnDeliveryTxt.setVisibility(View.GONE);
                    }else {
                        binding.btnCollectedBillTxt.setVisibility(View.GONE);
                        binding.btnDeliveryTxt.setTextColor(ContextCompat.getColor(TransactionDetailsActivity.this, R.color.dark_blue));
                        binding.btnDeliveryTxt.setVisibility(View.VISIBLE);
                    }
                }
            }else {
                binding.LinerarCollectedOrDeliveryTxt.setVisibility(View.GONE);
                binding.btnCollectedBillTxt.setVisibility(View.GONE);
                binding.btnDeliveryTxt.setVisibility(View.GONE);
            }
        }else {
            SaleIDZeroFun();
        }


        binding.btnCollectedBillTxt.setOnClickListener(this);
        binding.btnDeliveryTxt.setOnClickListener(this);
        binding.imgReprintKitchenprinter.setOnClickListener(this);

        String vchQueueNo = "0";
        String intTableNo = "0";
        String receiptOrderStatus = "0";
        String sql = "SELECT VchQueueNo,IntTableNo,ReceiptOrderStatus " +
                "FROM Bill " +
                "where TransNo = '" + BillNo + "'";
        Log.i("_sql__",sql);
        Cursor cc = DBFunc.Query(sql, false);
        if (cc != null) {
            while (cc.moveToNext()) {
                vchQueueNo = cc.getString(0);
                intTableNo = cc.getString(1);
                receiptOrderStatus = cc.getString(2);
            }
            cc.close();
        }
        if (intTableNo != null && !intTableNo.equals("null") && intTableNo != "0" && intTableNo.length() > 0) {
            binding.transactionTableNo.setVisibility(View.VISIBLE);
            String text = "<font color='#a9aaad'>Table No #</font>"+intTableNo;
            binding.transactionTableNo.setText(Html.fromHtml(text));
            //text += "<br/><font color='#a9aaad'>(" + ModiN.get(modiVar) + ")</font>";
        }
        if (vchQueueNo != null && !vchQueueNo.equals("null") && vchQueueNo != "0" && vchQueueNo.length() > 0) {
            binding.transactionVoucherNo.setVisibility(View.VISIBLE);
            String text = "<font color='#a9aaad'>Queue No #</font>"+vchQueueNo;
            binding.transactionVoucherNo.setText(Html.fromHtml(text));
        }

        String delivery_text = Query.GetSalesDelivery(BillNo);
        if (delivery_text.length() > 0) {
            binding.transactionDeliveryStatus.setVisibility(View.VISIBLE);
            binding.transactionDeliveryStatus.setText(Html.fromHtml(delivery_text));
        }
        if (receiptOrderStatus != null && receiptOrderStatus != "0" && receiptOrderStatus.length() > 0) {
            binding.transactionReceiptOrderStatus.setVisibility(View.VISIBLE);
            String text = "<font color='#a9aaad'>Order Status #</font>"+receiptOrderStatus;
            binding.transactionReceiptOrderStatus.setText(Html.fromHtml(text));
        }
        if (BillDateTime != null && BillDateTime != "0" && BillDateTime.length() > 0) {
            String text = "<font color='#a9aaad'>Date #</font>"+BillDateTime;
            binding.transactionDatetime.setText(Html.fromHtml(text));
        }
        binding.transactionCashier.setText("");
        PaymentTypeName = "";

        for (int i = 0 ; i < PaymentTypeNameArr.size() ; i ++) {
            if (PaymentTypeRemarksArr != null && PaymentTypeRemarksArr.size() > 0) {
                try {
                    if (!(PaymentTypeRemarksArr.get(i).equals("")) && PaymentTypeRemarksArr.get(i).length() > 0 && !(PaymentTypeRemarksArr.get(i).equals(null)) &&
                            !(PaymentTypeRemarksArr.get(i).equals("null"))) {
//                        if (!(PaymentTypeRemarksArr.get(i).equals("EZLINK"))) {
                            PaymentTypeName += PaymentTypeNameArr.get(i) + "(" + PaymentTypeRemarksArr.get(i) + ")";
//                        }
                    } else {
                        PaymentTypeName += PaymentTypeNameArr.get(i);
                    }
                } catch (Exception e){
                    PaymentTypeName += PaymentTypeNameArr.get(i);
                }
            } else {
                PaymentTypeName += PaymentTypeNameArr.get(i);
            }
            if (PaymentTypeNameArr.size() > 1) {
                PaymentTypeName += ",";
            }
        }

        String text = "<font color='#a9aaad'>Payment Mode #</font>" ;
        if (PaymentTypeName != null && PaymentTypeName != "0" && PaymentTypeName.length() > 0) {
            text += PaymentTypeName;
            binding.transactionPaymentMode.setVisibility(View.VISIBLE);
            binding.transactionPaymentMode.setText(Html.fromHtml(text));
        }

        String sql111 = "SELECT card_label,card_number,invoice_number,BillNo FROM BillJeripayDetails " +
                " WHERE BillNo = '"+BillNo+"' Group By BillNo,card_label";
        Cursor jeripayDetails = DBFunc.Query(sql111,false);
        if (jeripayDetails != null) {
            if (jeripayDetails.moveToNext()) {
                String invoice_number = jeripayDetails.getString(2);
                String text111 = "<font color='#a9aaad'>Invoice No #</font>" ;
                text111 += invoice_number;
                if (invoice_number!= null && invoice_number.length() > 1) {
                    binding.jeripayInvoiceNo.setVisibility(View.VISIBLE);
                    binding.jeripayInvoiceNo.setText(Html.fromHtml(text111));
                }
            }
            jeripayDetails.close();
        }

        String sql1BillBOC= "SELECT invoice_number FROM BillBOC " +
                " WHERE BillNo = '"+BillNo+"' Group By BillNo,card_label";
        Cursor csql1BillBOC = DBFunc.Query(sql1BillBOC,false);
        if (csql1BillBOC != null) {
            if (csql1BillBOC.moveToNext()) {
                String invoice_number = csql1BillBOC.getString(0);
                String text111 = "<font color='#a9aaad'>Invoice No #</font>" ;
                text111 += invoice_number;
                if (invoice_number!= null && invoice_number.length() > 1) {
                    binding.jeripayInvoiceNo.setVisibility(View.VISIBLE);
                    binding.jeripayInvoiceNo.setText(Html.fromHtml(text111));
                }
            }
            csql1BillBOC.close();
        }

        String str_sub_total = Query.ShowAmtMinusCorrectly(Double.valueOf(String.valueOf(SubTotal)));
        binding.transactionGrosssales.setText(str_sub_total);

        if (TotalQtySales == 0 || TotalQtySales == 1){
            binding.totalitmHeader.setText("Total Item");
        }else {
            binding.totalitmHeader.setText("Total Items");
        }
        binding.transactionTotalItems.setText(String.valueOf(TotalQtySales));

        if (Double.valueOf(TotalItemDisount) == 0.0) {
            binding.LayTotalItemDis.setVisibility(View.GONE);
        }else {
            binding.LayTotalItemDis.setVisibility(View.VISIBLE);
            String str_total_item_dis = Query.ShowAmtMinusCorrectly(Double.valueOf(String.valueOf(TotalItemDisount)));
            binding.transactionTotalitemdis.setText(str_total_item_dis);
        }

        String disName = "";
        if (DiscountTypeNameSales.equals("% Percentage")) {
            disName = "\n" + DiscountNameSales + " ("+DiscountValueSales+"%)";
        }else {
            disName = "\n" + DiscountNameSales + " ($"+DiscountValueSales+")";
        }

        if (TotalBillDisount != null && TotalBillDisount.length() > 0) {
            if (Double.valueOf(TotalBillDisount) == 0.0){
                binding.LayBillDiscount.setVisibility(View.GONE);
            }else {
                String str_total_bill_dis = Query.ShowAmtMinusCorrectly(Double.valueOf(String.valueOf(TotalBillDisount)));
                binding.LayBillDiscount.setVisibility(View.VISIBLE);
                binding.totalBillDiscount.setText("Total Bill Discount" + disName);
                binding.transactionTotalbilldis.setText(str_total_bill_dis);
            }
        }else {
            binding.LayBillDiscount.setVisibility(View.GONE);
        }
        Integer chk_serviceCharges = Query.CheckServiceCharges();

        if (chk_serviceCharges == 1) {
            String str_service_charges = Query.GetServiceChargesNameAndPercentage();

            if (ServiceCharges == 0.0) {
                binding.serviceChargesLayout.setVisibility(View.GONE);
            }else {

                String st_sv_ch = Query.ShowAmtMinusCorrectly(ServiceCharges);

                binding.serviceChargesLayout.setVisibility(View.VISIBLE);
                binding.serviceChargesname.setText(str_service_charges);
                binding.serviceChargesValue.setVisibility(View.VISIBLE);
                binding.serviceChargesValue.setText(st_sv_ch);
            }
        }else {
            binding.serviceChargesLayout.setVisibility(View.GONE);
        }
        String jerifood_tax = "0";
        try {
           jerifood_tax = Query.GetOptions(24);
        } catch (Exception e){
            jerifood_tax = "0";
        }

        if (jerifood_tax.equals("1")) {
            String taxNameJerifood = ValidAndGetValue("TaxName", "Sales",
                    "BillNo", BillNo, false);
            String taxAmtJerifood = ValidAndGetValue("TotalTaxes", "Sales",
                    "BillNo", BillNo, false);
            binding.layExcTransationTax.setVisibility(View.GONE);
            String str_inclusive_taxJerifood = taxNameJerifood + taxAmtJerifood;
            binding.layIncTransationTax.setVisibility(View.VISIBLE);
            binding.incTaxNamee.setText(str_inclusive_taxJerifood);
        }else {
            Log.i("jerifood_tax","jerifood_tax___"+"else");
            Cursor Cursor_tax = Query.GetTax();
            if (Cursor_tax != null) {
                if (Cursor_tax.moveToNext()) {
                    String taxRate = Cursor_tax.getString(0);
                    String taxType = Cursor_tax.getString(1);
                    Log.i("jerifood_tax","jerifood_tax___"+taxType);
                    String taxName = Cursor_tax.getString(2);
                    Log.i("jerifood_tax","jerifood_taxName___"+taxName);
                    if (taxType.equals("2")) {
                        double amt_inclusive = Query.calculateInclusive(Double.valueOf(totalNettAmount), Integer.parseInt(taxRate));
                        if (amt_inclusive == 0.0) {
                            binding.layExcTransationTax.setVisibility(View.GONE);
                            binding.layIncTransationTax.setVisibility(View.GONE);
                        } else {
                            String str_inclusive_amt = Query.ShowAmtMinusCorrectly(amt_inclusive);
//                        "$" + String.format("%.2f", amt_inclusive);
                            binding.layExcTransationTax.setVisibility(View.GONE);
                            //String str_inclusive = taxName.toUpperCase() + "(" + taxRate + "%)" + " SGD " + str_inclusive_amt + " Incl";
                            String str_inclusive = taxName.toUpperCase() + "(" + taxRate + "%)" + " $ " + str_inclusive_amt + " Incl";
                            binding.layIncTransationTax.setVisibility(View.VISIBLE);
                            binding.incTaxNamee.setText(str_inclusive);
                        }
                    } else {
                        if (TotalTax == 0.0) {

                            binding.layExcTransationTax.setVisibility(View.GONE);
                            binding.layIncTransationTax.setVisibility(View.GONE);
                        } else {
                            String str_exclusive = Query.ShowAmtMinusCorrectly(TotalTax);
                            //String str_exclusive = "$" + String.format("%.2f", Double.valueOf(TotalTax));

                            binding.layIncTransationTax.setVisibility(View.GONE);
                            binding.layExcTransationTax.setVisibility(View.VISIBLE);

//                            binding.TaxNamee.setVisibility(View.VISIBLE);
//                            binding.TaxNamee.setBackgroundColor(ContextCompat.getColor(appContext,
//                                    R.color.light_green));

                            binding.TaxNamee.setText(taxName.toUpperCase() + "(" + taxRate + "%)");

                            //taxname.setTextSize(MainActivity.billFontSize);
                            binding.transactionTaxes.setVisibility(View.VISIBLE);
                            binding.transactionTaxes.setText(str_exclusive);
                        }
                    }
//                    if (taxType.equals("2")) {
//                        double amt_inclusive = Query.calculateInclusive(Double.valueOf(totalNettAmount), Integer.parseInt(taxRate));
//                        if (amt_inclusive == 0.0) {
//                            binding.layExcTransationTax.setVisibility(View.GONE);
//                            binding.layIncTransationTax.setVisibility(View.GONE);
//                        } else if (taxType.equals("3")) {
//                            String str_inclusive_amt = Query.ShowAmtMinusCorrectly(amt_inclusive);
////                        "$" + String.format("%.2f", amt_inclusive);
//                            binding.layExcTransationTax.setVisibility(View.GONE);
//                            String str_inclusive = taxName.toUpperCase() + "(" + taxRate + "%)" + " SGD " + str_inclusive_amt + " Incl";
//                            binding.layIncTransationTax.setVisibility(View.VISIBLE);
//                            binding.incTaxNamee.setText(str_inclusive);
//                        }
//                    } else {
//                        if (TotalTax == 0.0) {
//
//                            binding.layExcTransationTax.setVisibility(View.GONE);
//                            binding.layIncTransationTax.setVisibility(View.GONE);
//                        } else {
//                            String str_exclusive = Query.ShowAmtMinusCorrectly(TotalTax);
//                            //String str_exclusive = "$" + String.format("%.2f", Double.valueOf(TotalTax));
//
//                            binding.layIncTransationTax.setVisibility(View.GONE);
//                            binding.layExcTransationTax.setVisibility(View.VISIBLE);
//                            binding.incTaxNamee.setText(taxName.toUpperCase() + "(" + taxRate + "%)");
//                            //taxname.setTextSize(MainActivity.billFontSize);
//                            binding.transactionTaxes.setVisibility(View.VISIBLE);
//                            binding.transactionTaxes.setText(str_exclusive);
//                        }
//                    }
                }
                Cursor_tax.close();
            }
        }
        if (RoundingAdj == 0){
            binding.LayRoundAdj.setVisibility(View.GONE);
        }else {
            String st_roundAdj = Query.ShowAmtMinusCorrectly(Double.valueOf(String.valueOf(RoundingAdj)));
            binding.LayRoundAdj.setVisibility(View.VISIBLE);
            binding.roundAdjValues.setText(st_roundAdj);
        }

        String st_ttlnettAmt = Query.ShowAmtMinusCorrectly(Double.valueOf(String.valueOf(totalNettAmount)));
        binding.transactionTotalamount.setText(st_ttlnettAmt);

        sales_id = getSalesIDByBillNo(RecyclerAdapter.selectedID);

        binding.imgReprint.setOnClickListener(this);
        binding.imgResyncBillno.setOnClickListener(this);
        binding.imgRefreshBill.setOnClickListener(this);
        binding.btnRefundOrCheckout.setOnClickListener(this);
        binding.btnCancelBill.setOnClickListener(this);
        if (STATUS.toUpperCase().equals(Constraints.SALES.toUpperCase())){

            sql = "SELECT BillNo FROM Sales " +
                    " WHERE ReferenceBillNo = '"+BillNo+"'  " ;
            Log.i("Dfd__","sql_"+sql);
            Cursor c = DBFunc.Query(sql, false);
            if ( c != null){
                if (c.getCount() == 0){
                    binding.LinerarRefundOrCancelTxt.setVisibility(View.GONE);
                    binding.LinerarRefundOrCancel.setVisibility(View.VISIBLE);
                    binding.btnRefundOrCheckout.setVisibility(View.VISIBLE);
                    binding.btnCancelBill.setVisibility(View.VISIBLE);
                }else {
                    binding.LinerarRefundOrCancelTxt.setVisibility(View.VISIBLE);
                    binding.btnRefundOrCheckouttxt.setTextColor(ContextCompat.getColor(TransactionDetailsActivity.this, R.color.color_red));
                    if (c.moveToNext()) {
                        String refund_bill_no = "Refund Bill No #" + c.getString(0);
                        //binding.btnRefundOrCheckouttxt.setlay
                        binding.btnRefundOrCheckouttxt.setText(refund_bill_no);

                        //LinearLayout container = (LinearLayout)findViewById(R.id.container);

                        //Button btn = new Button(this);
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                                300,
                                LinearLayout.LayoutParams.MATCH_PARENT);
//                            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
//                                    250,
//                                    30);
                        binding.btnRefundOrCheckouttxt.setLayoutParams(lp);

                        //container.addView(btn);
                    }
                    binding.btnCancelBillTxt.setVisibility(View.GONE);
                    binding.LinerarRefundOrCancel.setVisibility(View.GONE);
                    binding.btnRefundOrCheckout.setVisibility(View.GONE);
                    binding.btnCancelBill.setVisibility(View.GONE);
                }
                c.close();
            }
        }else {
            SaleIDZeroFun();
        }

        if (STATUS.equals("VOID")){
            binding.btnCancelBill.setText("Cancelled");
        }
//        if (STATUS.equals("Refund")){
//            binding.btnRefundOrCheckout.setText("Refund");
//        }
    }

    @Override
    protected void onResume() {
        appContext = getApplicationContext();
        updateTransactionFun();
        super.onResume();
    }

    public static void saveSales(String totalItems, String payments_amount, String payments_name,
                                 String extraCharges_amount, String extraCharges_name,
                                 String dateFormat3, String dateFormat4, long timestamp) {
        String s = "Select ID FROM Sales WHERE BillNo = '" + BillNo + "'";
        Cursor cc = DBFunc.Query(s, false);
        if (cc != null) {
            if (cc.getCount() == 0) {
                //SALES
                String uniqueId = UUID.randomUUID().toString();
                String BillID = Query.findBillIDByBillNo(BillNo);


                try {
                    String sql = "INSERT INTO Sales (UUID,BillNo,BillID,TotalQty,SubTotal,Totalamount,Changeamount,PaymentTypeID,PaymentTypeName,PaymentAmount," +
                            "GrossSales,TotalItemDisount,TotalBillDisount,GrossTotal,ServiceCharges,TotalNettSales,TaxName,TotalTaxes,CashierName,SalesDateTime,BillHour,STATUS," +
                            "RoundAdj,DiscountID,DiscountName,DiscountType,DiscountTypeName,DiscountValue,EwalletStatus,DateTime) VALUES (";
                    sql += "'" + uniqueId + "', ";
                    sql += "'" + BillNo + "', ";
                    sql += "'" + BillID + "', ";
                    sql += "'" + DBFunc.PurifyString(String.valueOf(totalItems)) + "', ";
                    try {
                        sql += "'" + String.format("%.2f", Double.valueOf(payments_amount)) + "', ";
                    }catch (NumberFormatException e){
                        sql += "'0.00', ";
                    }
                    try {
                        sql += "'" + String.format("%.2f", Double.valueOf(payments_amount)) + "', ";
                    }catch (NumberFormatException e){
                        sql += "'0.00', ";
                    }
                    sql += "'" + String.format("%.2f", Double.valueOf("0")) + "', ";
                    String paymentID = ValidAndGetValue("ID", "Payment", "Name", payments_name, true);
                    sql += "'" + DBFunc.PurifyString(String.valueOf(paymentID)) + "', ";
                    sql += "'" + DBFunc.PurifyString(payments_name) + "', ";
                    try {
                        sql += "'" + String.format("%.2f", Double.valueOf(payments_amount)) + "', ";
                    }catch (NumberFormatException e){
                        sql += "'0.00', ";
                    }
                    try {
                        sql += "'" + String.format("%.2f", Double.valueOf(payments_amount)) + "', ";
                    }catch (NumberFormatException e){
                        sql += "'0.00', ";
                    }
                    sql += "'" + String.format("%.2f", Double.valueOf("0")) + "', ";
                    sql += "'" + String.format("%.2f", Double.valueOf("0")) + "', ";
                    try {
                        sql += "'" + String.format("%.2f", Double.valueOf(payments_amount)) + "', ";
                    }catch (NumberFormatException e){
                        sql += "'0.00', ";
                    }
                    sql += "'" + String.format("%.2f", Double.valueOf("0")) + "', ";
                    try {
                        sql += "'" + String.format("%.2f", Double.valueOf(payments_amount)) + "', ";
                    }catch (NumberFormatException e){
                        sql += "'0.00', ";
                    }
                    sql += "'" +extraCharges_name + "', ";

                    try {
                        sql += "'" + String.format("%.2f", Double.valueOf(extraCharges_amount)) + "', ";
                    }catch (NumberFormatException e){
                        sql += "'0.00', ";
                    }
                    sql += "'" + DBFunc.PurifyString(String.valueOf("")) + "', ";
                    sql += "'" + dateFormat3 + "', ";
                    sql += "'" + dateFormat4 + "', ";
                    sql += "'SALES', ";
                    sql += "'0', ";
                    sql += "'" + "0" + "', ";
                    sql += "'" + "" + "', ";
                    sql += "'" + "" + "', ";
                    sql += "'" + "" + "', ";
                    sql += "'" + "0" + "', ";

                    sql += 0 + ", ";

                    sql += timestamp + ")";

                    DBFunc.ExecQuery(sql, false);
                }catch (Exception e){
                    DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                            System.currentTimeMillis(), DBFunc.PurifyString("saveSales - " +   e.getMessage()));
                }
                try {

                    Integer sale_id = Query.findLatestID("ID", "Sales", false);

                    CashLayoutActivity.SaveSalesItem(BillNo, Double.valueOf(payments_amount),
                            Integer.valueOf(totalItems), sale_id, String.valueOf("0"),
                            payments_name, String.valueOf(payments_amount), appContext,System.currentTimeMillis());
                }catch (Exception e){
                    Log.i("NumberFormatException", "NumberFormatException" + e.getMessage());

                    DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                            System.currentTimeMillis(), DBFunc.PurifyString("saveSales -SaveSalesItem- " +   e.getMessage()));
                }
//
//
//                Query.SaveBillPaymentIntoDBFun(context,bill_amt,amount,total_discount,amt_exclusive,PromoTotalValue,BillNo,
//                        String.valueOf(paymentID),paymentName,String.valueOf(Changeamount),total_nett_sales,"cash","",sale_id);
//
                Double actual_amt = 0.0;
                try {

                    actual_amt = Double.valueOf(payments_amount);
                }catch (NumberFormatException e){
                    actual_amt = 0.0;
                }


                CashLayoutActivity.change_due_amt = 0.0;

                if (com.dcs.myretailer.app.CheckOutActivity.CashValueArr.size() > 0) {
//            Log.i("Sizzze","Sizzz___"+CheckOutActivity.CashValueArr.size());
                    for (int i = 0; i < com.dcs.myretailer.app.CheckOutActivity.CashValueArr.size(); i++) {
                        //bill_amt = amount - CashLayoutActivity.CashValue;
                        //bill_amt = amount - CheckOutActivity.CashValueArr.get(i);
                        actual_amt -= com.dcs.myretailer.app.CheckOutActivity.CashValueArr.get(i);

                        String str_info = "";
                        String printrqr = Query.GetOptions(18);
                        if (printrqr.equals("1")) {

                            str_info = Query.getStrInfoForQRCodeOnReceipt(BillNo, com.dcs.myretailer.app.CheckOutActivity.CashValuePaymentNameArr.get(i),
                                    com.dcs.myretailer.app.CheckOutActivity.CashValueArr.get(i),
                                    com.dcs.myretailer.app.CheckOutActivity.CashValueArr.get(i));
                        } else {
                            str_info = "";
                        }

                        try {
                            String sql = "INSERT INTO BillPayment (BillNo,STATUS,EwalletStatus,EwalletIssueBanker,EwalletPaymentType,PaymentID,Name,Amount,PaymentDateTime,QRCode," +
                                    "ChangeAmount,SaleSync) VALUES (";
                            //sql += BillNo + ", ";
                            sql += "'" + com.dcs.myretailer.app.CheckOutActivity.BillNo + "'" + ", ";
                            sql += "'" + Constraints.SALES + "'" + ", ";
                            sql += 0 + ", ";
                            sql += "''" + ", ";
                            sql += "''" + ", ";
                            sql += com.dcs.myretailer.app.CheckOutActivity.CashValuePaymentIDArr.get(i) + ", ";
                            sql += "'" + com.dcs.myretailer.app.CheckOutActivity.CashValuePaymentNameArr.get(i) + "', ";
                            sql += Query.DoubleAmountFormat(com.dcs.myretailer.app.CheckOutActivity.CashValueArr.get(i)) + ", ";
                            sql += System.currentTimeMillis() + ", ";
                            sql += "'" + str_info + "', ";
                            sql += "'" + Query.DoubleAmountFormat(Double.valueOf("0")) + "', ";
                            //        sql += "'" + change_due_amt + "', ";
                            sql += "'0')";

                            DBFunc.ExecQuery(sql, false);

                            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                                    System.currentTimeMillis(), DBFunc.PurifyString("Saved-Transacty-BillPayment - " +  sql));


                        }catch (Exception e){
                            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                                    System.currentTimeMillis(), DBFunc.PurifyString("Err-TransActivity-BillPayment - " +   e.getMessage()));
                        }
                    }
                } else {
                    // actual_amt = Double.valueOf(payments_amount);
                    try {

                        actual_amt = Double.valueOf(payments_amount);
                    }catch (NumberFormatException e){
                        actual_amt = 0.0;
                    }
                }

            }


        }
    }

    public static void saveBillPayments(String payments_name,String payments_amount,long timestamp) {

        try {
            String sql = "INSERT INTO BillPayment (BillNo,STATUS,EwalletStatus,EwalletIssueBanker,EwalletPaymentType," +
                    "PaymentID,Name,Amount,PaymentDateTime,QRCode,ChangeAmount,SaleSync) VALUES (";
            //sql += BillNo + ", ";
            sql += "'" + BillNo + "'" + ", ";
            sql += "'" + Constraints.SALES + "'" + ", ";
            sql += 0 + ", ";
            sql += "''" + ", ";
            sql += "''" + ", ";

            String paymentID = ValidAndGetValue("ID", "Payment", "Name", payments_name, true);
            sql += paymentID + ", ";

            sql += "'" + DBFunc.PurifyString(DBFunc.PurifyString(payments_name)) + "', ";

            sql += Query.DoubleAmountFormat(Double.valueOf(payments_amount)) + ", ";

            sql += timestamp + ", ";

            sql += "'', ";
            sql += "'0', ";

            //        sql += "'" + change_due_amt + "', ";
            sql += "'0')";

            DBFunc.ExecQuery(sql, false);
        }catch (Exception e){
            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                    System.currentTimeMillis(), DBFunc.PurifyString("TransactionDetailsActivity-saveBillPayments - " +   e.getMessage()));
        }
    }

    public static String ValidAndGetValue(String selectVal,String tblName,String matchName,String matchVal,Boolean flag) {
        //String pluID = "SELECT ID FROM PLU WHERE NAME = '"+matchVal+"' ";

        String returnVal = "0";
        try {
            String query = "SELECT " + selectVal + " FROM " + tblName + " WHERE UPPER(" + matchName + ") = '" + matchVal.toUpperCase() + "' ";

            Cursor cursor = DBFunc.Query(query, flag);
            if (cursor != null) {
                if (cursor.moveToNext()) {
                    returnVal = cursor.getString(0);
                }
            }
        }catch (Exception e){
            returnVal = "0";
        }
        return returnVal;
    }

    private void ScreenDisplayFun() {
        String terminalTypeVal = Query.GetDeviceData(Constraints.TERMINAL_TYPE);


        if (terminalTypeVal.equals(Constraints.PAX_E600M)) {
            ScrollView.LayoutParams params = new ScrollView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    android.widget.Toolbar.LayoutParams.MATCH_PARENT);
            binding.ScrollView01.setLayoutParams(params);

            FrameLayout.LayoutParams params1 = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    android.widget.Toolbar.LayoutParams.MATCH_PARENT);
            binding.transactiondetaillinerarlayout.setLayoutParams(params1);

            LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(750,
                    android.widget.Toolbar.LayoutParams.MATCH_PARENT);
            params2.leftMargin = 30;
            binding.layCheckoutOverAll.setLayoutParams(params2);

            LinearLayout.LayoutParams LayHeaderp = new LinearLayout.LayoutParams(400,
                    android.widget.Toolbar.LayoutParams.MATCH_PARENT);
            binding.LayHeader.setLayoutParams(LayHeaderp);
//
//            LinearLayout.LayoutParams paramslayCardView = new LinearLayout.LayoutParams(580, ViewGroup.LayoutParams.WRAP_CONTENT);
//            //FrameLayout.LayoutParams paramslayCardView = new FrameLayout.LayoutParams(580, ViewGroup.LayoutParams.WRAP_CONTENT);
//            paramslayCardView.leftMargin = 10;
//            binding.checkoutInfo.layCardView.setLayoutParams(paramslayCardView);
////
//            binding.checkoutOrderSummary.layCardView.setLayoutParams(paramslayCardView);
////
//            FrameLayout.LayoutParams paramslaySubCardView = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                    android.widget.Toolbar.LayoutParams.MATCH_PARENT);
//            binding.checkoutOrderSummary.laySubCardView.setLayoutParams(paramslaySubCardView);
////
////            FrameLayout.LayoutParams paramslaySubCardViewDetails = new FrameLayout.LayoutParams(580,
////                    android.widget.Toolbar.LayoutParams.MATCH_PARENT);
//            LinearLayout.LayoutParams paramslaySubCardViewDetails = new LinearLayout.LayoutParams(580,
//                    android.widget.Toolbar.LayoutParams.MATCH_PARENT);
//            paramslaySubCardViewDetails.leftMargin = 10;
//            binding.checkoutOrderSummary.laySubCardViewDetails.setLayoutParams(paramslaySubCardViewDetails);
////
//            LinearLayout.LayoutParams paramslaySubCardViewDetailsTotalAmt = new LinearLayout.LayoutParams(580,80);
//            //paramslaySubCardViewDetailsTotalAmt.leftMargin = 10;
//            binding.checkoutOrderSummary.laySubCardViewDetailsTotalAmt.setLayoutParams(paramslaySubCardViewDetailsTotalAmt);
////            binding.checkoutOrderSummary.tota;..setLayoutParams(paramslaySubCardViewDetailsTotalAmt);
//
//            binding.checkoutPaymentList.layCardView.setLayoutParams(paramslayCardView);
//            binding.checkoutPaymentList.layView.setLayoutParams(paramslayCardView);
        }else if (terminalTypeVal.equals(Constraints.IMIN)){
            String device = Query.GetDeviceData(Constraints.DEVICE);

            if (device.equals("M2-Max")) {
                ScrollView.LayoutParams params = new ScrollView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        android.widget.Toolbar.LayoutParams.MATCH_PARENT);
                binding.ScrollView01.setLayoutParams(params);

                FrameLayout.LayoutParams params1 = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
                binding.transactiondetaillinerarlayout.setLayoutParams(params1);

                FrameLayout.LayoutParams paramtransactionheaderlay = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
                binding.headerlay.setLayoutParams(paramtransactionheaderlay);

                LinearLayout.LayoutParams paramtransactionheaderlay1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
                binding.headerlay1.setLayoutParams(paramtransactionheaderlay1);


                LinearLayout.LayoutParams paramLinearDt = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
                binding.LinearDt.setLayoutParams(paramLinearDt);


                LinearLayout.LayoutParams paramtransactionbillno = new LinearLayout.LayoutParams(500,
                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
                binding.laytransactionbillno.setLayoutParams(paramtransactionbillno);


                LinearLayout.LayoutParams transactiondatetime = new LinearLayout.LayoutParams(470,
                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
                binding.transactionDatetime.setLayoutParams(transactiondatetime);


                LinearLayout.LayoutParams paramscard = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
                binding.cardLay.setLayoutParams(paramscard);
//
                LinearLayout.LayoutParams paramscard1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
                binding.cardView111.setLayoutParams(paramscard1);
//
                LinearLayout.LayoutParams paramscard11 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
                binding.cardView111111.setLayoutParams(paramscard11);
//
                LinearLayout.LayoutParams paramscheckOutListView = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
                binding.checkOutListView.setLayoutParams(paramscheckOutListView);
//
                LinearLayout.LayoutParams paramstransCard = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
                binding.transactionCard.setLayoutParams(paramstransCard);

                FrameLayout.LayoutParams paramscardsale = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
                binding.laySaleSummaryDetails.setLayoutParams(paramscardsale);
//
                LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
                params2.leftMargin = 30;
                params2.rightMargin = 30;
                binding.layCheckoutOverAll.setLayoutParams(params2);

                LinearLayout.LayoutParams paramstamt = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
                paramstamt.leftMargin = 30;
                paramstamt.rightMargin = 30;
                binding.layTotalAmt.setLayoutParams(params2);


//                LinearLayout.LayoutParams paramsimgReprint = new LinearLayout.LayoutParams((int) ((widthSZ)/10 *9),
//                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
//                paramsimgReprint.leftMargin = (int) ((widthSZ)/10 * 0.5); ;
//                binding.imgReprint.setLayoutParams(paramsimgReprint);

//                LinearLayout.LayoutParams LayHeaderp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
//                binding.LayHeader.setLayoutParams(LayHeaderp);
//
//                LinearLayout.LayoutParams paramsLayDescription = new LinearLayout.LayoutParams(350,
//                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
//                paramsLayDescription.leftMargin = 10;
//                binding.LayDescription.setLayoutParams(paramsLayDescription);


                binding.totalitmHeader.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f);
                binding.transactionTotalItems.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f);

                binding.subtotaltransactiondetails.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f);
                binding.transactionGrosssales.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f);

                binding.txttotalitemdis.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f);
                binding.transactionTotalitemdis.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f);

                binding.totalBillDiscount.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f);
                binding.transactionTotalbilldis.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f);

                binding.serviceChargesname.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f);
                binding.serviceChargesValue.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f);

                binding.incTaxNamee.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f);
                binding.TaxNamee.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f);
                binding.transactionTaxes.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f);

                binding.txtRoundAdj.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f);
                binding.roundAdjValues.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f);

                binding.txtTotalAmt.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f);
                binding.transactionTotalamount.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f);

                int widthSZ = Query.screenSize(getApplicationContext(),"W");
                int heigthSZ = Query.screenSize(getApplicationContext(),"H");

                LinearLayout.LayoutParams LayHeader_width = new LinearLayout.LayoutParams((int) ((widthSZ)),
                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
                binding.LayHeader.setLayoutParams(LayHeader_width);
                //binding.LayHeader.setBackgroundColor(getResources().getColor(R.color.light_green));

                LinearLayout.LayoutParams LayDescription_width = new LinearLayout.LayoutParams((int) ((widthSZ/10) * 6.4),
                        android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
                binding.LayDescription.setLayoutParams(LayDescription_width);
               // binding.LayDescription.setBackgroundColor(getResources().getColor(R.color.dark_blue));

//        LinearLayout.LayoutParams billDiscountName_width = new LinearLayout.LayoutParams((int) ((widthSZ/10) * 3.5),
//                android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
//        binding.txtdescription.setLayoutParams(billDiscountName_width);

              //  binding.txtdescription.setBackgroundColor(getResources().getColor(R.color.color_red));
            }
        }
    }
    private void SaleIDZeroFun() {
//        Log.i("DFDFD___","FDFDF___"+STATUS.toUpperCase());
//        if (STATUS.length() == 0){
            String sqlstatus = "Select STATUS from BillList " +
                    "where BillNo = '"+BillNo+"'";
            Cursor ccsqlstatus = DBFunc.Query(sqlstatus,false);
            if (ccsqlstatus != null) {
                if (ccsqlstatus.moveToNext()){
                    STATUS = ccsqlstatus.getString(0);
                }
                ccsqlstatus.close();
            }
//        }
        binding.LinerarRefundOrCancelTxt.setVisibility(View.VISIBLE);
        if (STATUS.toUpperCase().equals(Constraints.VOID.toUpperCase())){
            binding.btnCancelBillTxt.setTextColor(ContextCompat.getColor(TransactionDetailsActivity.this, R.color.color_red));
            binding.btnRefundOrCheckouttxt.setVisibility(View.GONE);
        }
        if (STATUS.toUpperCase().equals(Constraints.CANCEL.toUpperCase())){

            binding.laySalessummary.setVisibility(View.GONE);
            binding.laySaleSummaryDetails.setVisibility(View.GONE);
            binding.layTotalAmt.setVisibility(View.GONE);

            binding.btnCancelBillTxt.setTextColor(ContextCompat.getColor(TransactionDetailsActivity.this, R.color.color_red));
            binding.btnRefundOrCheckouttxt.setVisibility(View.GONE);
        }
        if (STATUS.toUpperCase().equals(Constraints.REFUND.toUpperCase())){
//                binding.btnRefundOrCheckouttxt.setTextColor(ContextCompat.getColor(TransactionDetailsActivity.this, R.color.color_red));
//                binding.btnCancelBillTxt.setVisibility(View.GONE);
            String tsql = "SELECT ReferenceBillNo FROM Sales " +
                    " WHERE BillNo = '"+BillNo+"'  " ;
            Log.i("Dfd__","sql_"+tsql);
            Cursor tc = DBFunc.Query(tsql, false);
            if ( tc != null) {
                if (tc.getCount() == 0) {

                    binding.btnRefundOrCheckouttxt.setTextColor(ContextCompat.getColor(TransactionDetailsActivity.this, R.color.color_red));
                    binding.btnCancelBillTxt.setVisibility(View.GONE);
                }else {
                    binding.btnRefundOrCheckouttxt.setTextColor(ContextCompat.getColor(TransactionDetailsActivity.this, R.color.color_red));
                    binding.btnCancelBillTxt.setVisibility(View.GONE);
                    String refund_bill_no = "REFUND BILL";
                    if (tc.moveToNext()) {
                        refund_bill_no += "\n \n Sales Bill No #" + tc.getString(0);
                        //binding.btnRefundOrCheckouttxt.setlay
//                            binding.btnRefundOrCheckouttxt.setTextColor(ContextCompat.getColor(TransactionDetailsActivity.this, R.color.nasty_green));
                    }
                    binding.btnRefundOrCheckouttxt.setText(refund_bill_no);
                    //Button btn = new Button(this);
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                            300,
                            LinearLayout.LayoutParams.MATCH_PARENT);
//                            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
//                                    250,
//                                    30);
                    binding.btnRefundOrCheckouttxt.setLayoutParams(lp);
                }
                tc.close();
            }
        }
        binding.LinerarRefundOrCancel.setVisibility(View.GONE);
        binding.btnRefundOrCheckout.setVisibility(View.GONE);
        binding.btnCancelBill.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {

        Intent i = new Intent(TransactionDetailsActivity.this,MainActivity.class);
//        i.putExtra("name", "BillListMainPageFragment");
        startActivity(i);
        finish();
////        MainActivity.St = "1";
//
////        MainActivity.updateMediaButtons();
//
//        Log.i("sdf___status_for_jerifood","status_for_jerifood_____s"+status_for_jerifood);
//        if (status_for_jerifood.equals("0")) {
//
//
//            Intent i = new Intent(TransactionDetailsActivity.this, MainActivity.class);
//            i.putExtra("name", "BillListMainPageFragment");
//            startActivity(i);
//            finish();
//        } else {
//            status_for_jerifood = "0";
//            Intent data =  getIntent();
//            String registro = BillNo;
//            Bundle bundle = new Bundle();
////            bundle.putString("retorno", registro);
//            bundle.putString("orderno", registro);
//            data.putExtras(bundle);
//
//            setResult(Activity.RESULT_OK, data);
//            super.onBackPressed(); // this calls finish(); internally.
//        }
//
//
//
////        super.onBackPressed();
////        ProductMainPageFragment.St = "1";
////        RecyclerViewAdapter.St = "1";
//        //ActivityCompat.finishAffinity(TransactionDetailsActivity.this);
////        Intent i = new Intent(TransactionDetailsActivity.this,MainActivity.class);
////        i.putExtra("name", "BillListMainPageFragment");
////        startActivity(i);
////        finish();//add 09112020
////        finish();


//        Intent i = new Intent(TransactionDetailsActivity.this, MainActivity.class);
//        i.putExtra("name", "BillListMainPageFragment");
//        startActivity(i);
//        finish();
//        ReportAdapter adapter = new ReportAdapter
//                (getFragmentManager(), ReportActivity.binding.tabLayoutReport.getTabCount());
//        ReportActivity.binding.pagerReport.setAdapter(adapter);
//        ReportActivity.binding.pagerReport.setCurrentItem(3);


//        MainPagePagerAdapter adapter = new MainPagePagerAdapter(getSupportFragmentManager(), MainActivity.binding.tabLayout.getTabCount());
//        //adapter = new PagerAdapter(getSupportFragmentManager());
//        MainActivity.binding.pager.setAdapter(adapter);
//        MainActivity.binding.pager.setCurrentItem(2);

//        MainActivity.updateBillListFun();
//
    }

    //    public static String convertArrayToStringMethod(String[] strArray) {
//        StringBuilder stringBuilder = new StringBuilder();
//        for (int i = 0; i < strArray.length; i++) {
//            stringBuilder.append(strArray[i]);
//        }
//        return stringBuilder.toString();
//    }


    public static void getDetailsBillProductAllByQty(String billNo){
        //String sql = "SELECT ProductQty,ProductName,ProductPrice,BillNo FROM Details_BillProduct Where BillNo = '"+ BillNo +"' ";
        //String sql = " SELECT count(ProductQty),ProductName,SUM(ProductPrice),ProductID,SUM(ItemDiscountAmount) " +
        String sql = " SELECT (ProductQty),ProductName,ProductPrice,ProductID," +
                "(ItemDiscountAmount),(ProductPrice)," +
                "DiscountName,DiscountTypeName,DiscountValue,ProductQty,OpenPriceStatus,Remarks,ID " +
                "FROM DetailsBillProduct " +
                "Where BillNo = '"+ billNo +"' group by ProductID,OpenPriceStatus,Remarks";
//        String sql = " SELECT SUM(ProductQty),ProductName,ProductPrice,ProductID," +
//                "SUM(ItemDiscountAmount),SUM(ProductPrice)," +
//                "DiscountName,DiscountTypeName,DiscountValue,ProductQty,OpenPriceStatus,Remarks,ID " +
//                "FROM DetailsBillProduct " +
//                "Where BillNo = '"+ billNo +"' group by ProductID,OpenPriceStatus,Remarks";
                //"Where BillNo = '"+ billNo +"' group by ProductQty order by count(ProductQty) ASC";
        Log.i("_sql__",sql);
        Cursor c = DBFunc.Query(sql, false);
        if (c != null) {
            sldQtyArrCheckout.clear();
            sldNameArrCheckout.clear();
            sltPriceTotalArrCheckout.clear();
            sltIDArrCheckout.clear();
            sldItemDisArr.clear();
            sldDiscountName.clear();
            sldDiscountType.clear();
            sldDiscountValue.clear();
            sldOpenPriceStatus.clear();
            sldRemarks.clear();
            billdetailsPID.clear();
            while (c.moveToNext()) {
                if (!c.isNull(0)) {
                    if (c.getInt(9) != -1){

                        sldQtyArrCheckout.add(c.getString(0));
                        Log.i("Dfdfdfarr__","array_dddd__"+c.getString(11));
                        if (c.getString(11) != null && !(c.getString(11).equals("")) && c.getString(11).trim().length() > 0 && !(c.getString(11).equals("0"))) {
                            String chkicno_on = Query.GetOptions(26);
                            if (chkicno_on.equals("1")) {
                                //HPB
                                if (c.getString(11) != null && c.getString(11).length() == 9) {
                                    String remarkstransaction = Constraints.PASSFirstDigits + c.getString(11).substring(5, 9);
                                    Log.i("Dfdfdfarr__", "array__remarkstransaction_" + c.getString(11));
                                    sldNameArrCheckout.add(c.getString(1) + "\n" + "(" + remarkstransaction + ")");
                                } else {
                                    if (c.getString(11) != null && c.getString(11).length() > 0 && !(c.getString(11).equals(null)) && !(c.getString(11).equals("null"))) {
                                        sldNameArrCheckout.add(c.getString(1) + "\n" + "(" + c.getString(11) + ")");
                                    } else {
                                        sldNameArrCheckout.add(c.getString(1));
                                    }
                                }
                            } else {
                                 if (c.getString(11) != null && c.getString(11).length() > 0 && !(c.getString(11).equals(null)) && !(c.getString(11).equals("null"))) {
                                        sldNameArrCheckout.add(c.getString(1) + "\n" + "(" + c.getString(11) + ")");
                                    } else {
                                        sldNameArrCheckout.add(c.getString(1));
                                    }
                            }

//                            if (c.getString(11) != null && c.getString(11).length() > 0 && !(c.getString(11).equals(null)) && !(c.getString(11).equals("null"))) {
//                                sldNameArrCheckout.add(c.getString(1) + "\n" + "(" + c.getString(11) + ")");
//                            } else {
//                                sldNameArrCheckout.add(c.getString(1));
//                            }

                        } else {
                            sldNameArrCheckout.add(c.getString(1));
                        }
                        //sltPriceTotalArrCheckout.add(c.getString(2));
                        //sltPriceTotalArrCheckout.add(c.getString(5));
//                        Double totPrice = c.getDouble(0) * c.getDouble(5);
                        Double totPrice = c.getDouble(5);
                        sltPriceTotalArrCheckout.add(String.valueOf(totPrice));
                        //sltPriceTotalArrCheckout.add(c.getString(5));
                        sltIDArrCheckout.add(c.getString(3));
                        Double diVal = c.getDouble(0) * c.getDouble(4);
//                        Double diVal = c.getDouble(4);
                        sldItemDisArr.add(String.valueOf(diVal));
                        //sldItemDisArr.add(c.getString(4));

                        sldDiscountName.add(c.getString(6));
                        sldDiscountType.add(c.getString(7));
                        sldDiscountValue.add(c.getString(8));
                        sldOpenPriceStatus.add(c.getString(10));
                        String remarks_ = c.getString(11);
                        billdetailsPID.add(c.getInt(12));
                        String chkicno_on = Query.GetOptions(26);
                        if (chkicno_on.equals("1")) {
                            //HPB
                            if (remarks_ != null && remarks_.length() == 9) {
                                sldRemarks.add(Constraints.PASSFirstDigits+remarks_.substring(5,9));
                            } else {
                                sldRemarks.add(remarks_);
                            }
                        } else {
                            sldRemarks.add(remarks_);
                        }
                        if (remarks_ != null && remarks_.length() > 0 &&
                                !(remarks_.equals(null)) && !(remarks_.equals("null"))) {
                            sldRemarks.add(remarks_);
                        } else {
                            sldRemarks.add("");
                        }
                    }
                }
            }
            c.close();
        }
//        Log.i("hfdkjgf_size", String.valueOf(sldQtyArrCheckout.size()));
//        if (sldQtyArrCheckout.size() == 0){
//            sql = " SELECT SUM(ItemQty),ItemName,SUM(ItemPrice),ItemID,SUM(ItemDisc1) " +
//                    "FROM OnlineBill_HOLD_ITEMS " +
//                    "Where TransNo = '"+ billNo +"' group by ItemID";
//            //"Where BillNo = '"+ billNo +"' group by ProductQty order by count(ProductQty) ASC";
//            //Log.i("_sql__",sql);
//            c = DBFunc.Query(sql, false);
//            if (c != null) {
//                sldQtyArrCheckout.clear();
//                sldNameArrCheckout.clear();
//                sltPriceTotalArrCheckout.clear();
//                sltIDArrCheckout.clear();
//                sldItemDisArr.clear();
//                while (c.moveToNext()) {
//                    if (!c.isNull(0)) {
//                        sldQtyArrCheckout.add(c.getString(0));
//                        sldNameArrCheckout.add(c.getString(1));
//                        sltPriceTotalArrCheckout.add(c.getString(2));
//                        sltIDArrCheckout.add(c.getString(3));
//                        sldItemDisArr.add(c.getString(4));
//                    }
//                }
//                c.close();
//            }
//        }
    }

    public static Integer getSalesIDByBillNo(String billNo){
        Integer sales_id = 0;
        String sql = "SELECT ID FROM SALES Where BillNo = '"+ billNo +"' ";
        Log.i("_sql_rrrr_",sql);
        Cursor c = DBFunc.Query(sql, false);
        if (c != null) {
            if (c.moveToNext()) {
                if (!c.isNull(0)) {
                    Log.i("DSF_Integer_", String.valueOf(c.getInt(0)));
                    sales_id = c.getInt(0);
                }
            }
            c.close();
        }
        return sales_id;
    }

    public static void getDetailsBillProductAll(){
        //String sql = "SELECT ProductQty,ProductName,ProductPrice,BillNo FROM Details_BillProduct Where BillNo = '"+ RecyclerAdapter.selectedID +"' ";
//        String sql = " SELECT BillNo,count(ProductQty),SUM(ProductPrice),ProductName,BillDateTime,ID FROM Details_BillProduct " +
//                " Where BillNo = '"+ BillNo +"' "+
//                "   group by ProductName";
        String sql = " SELECT BillNo,SUM(ProductQty),SUM(ProductPrice),ProductName,BillDateTime,ID," +
                "TaxID,TaxName,SUM(TaxAmount)," +
                "DiscountName,DiscountTypeName,DiscountValue,ProductPrice,ProductID " +
                "FROM DetailsBillProduct " +
                " Where BillNo = '"+ BillNo +"' "+
                "   group by ProductID,OpenPriceStatus,Remarks order by ProductID ASC";
        Log.i("_sql__",sql);
        Cursor c = DBFunc.Query(sql, false);
        if (c != null) {
            sldQtyArr.clear();
            sldNameArr.clear();
            sltPriceTotalArr.clear();
            sltPriceTotalEachArr.clear();
            sldTaxID.clear();
            sldCTaxName.clear();
            sldCTaxAmount.clear();
            sldCTaxType.clear();
            sldDiscountName.clear();
            sldDiscountType.clear();
            sldDiscountValue.clear();
            while (c.moveToNext()) {
                if (!c.isNull(0)) {
                    Log.i("DSF_",c.getString(0));
                    Log.i("DSFdd_",c.getString(1));
                    Log.i("DSFrff_",c.getString(2));
                    Log.i("DSFrff_",c.getString(2));
                    sldQtyArr.add(c.getString(1));
                    //sldNameArr.add(c.getString(1));
                    sldNameArr.add(c.getString(3));

                    //Double totalPrice = c.getDouble(0) * c.getDouble(1);
                    Double totalPrice = c.getDouble(2);
                    Log.i("totalPrice__","totalPrice___"+totalPrice);
                    sltPriceTotalArr.add(String.valueOf(totalPrice));
                    sltPriceTotalEachArr.add(String.valueOf(totalPrice));
//                    sltPriceTotalArr.add(c.getString(2));
//                    sltPriceTotalEachArr.add(c.getString(12));
                    Integer taxID = c.getInt(6);
                    sldTaxID.add(String.valueOf(taxID));
                    sldCTaxName.add(c.getString(7));
                    sldCTaxAmount.add(c.getString(8));
                    sldDiscountName.add(c.getString(9));
                    Log.i("TAGGG","_c.getString10__"+c.getString(10));
                    sldDiscountType.add(c.getString(10));
                    sldDiscountValue.add(c.getString(11));
                    Log.i("taxIDDD", String.valueOf(taxID));
                    String str_tax = "Select Type from Tax Where ID = " +taxID;
                    Log.i("taxIDDD_str_tax", String.valueOf(str_tax));
                    Cursor CursortaxObj = DBFunc.Query(str_tax,true);
                    if (CursortaxObj != null){
                        if (CursortaxObj.moveToNext()) {
                            sldCTaxType.add(CursortaxObj.getString(0));
                        }else {
                            sldCTaxType.add("0");
                        }
                        CursortaxObj.close();
                    }else {
                        sldCTaxType.add("0");
                    }
                }
            }
            c.close();
        }
//        if (sldQtyArr.size() == 0){
//            sql = " SELECT TransNo,count(ItemQty),SUM(ItemPrice),ItemName,DateTime,ID " +
//                    "FROM OnlineBill_HOLD_ITEMS " +
//                    "Where TransNo = '"+ BillNo +"' group by ItemQty";
//            //"Where BillNo = '"+ billNo +"' group by ProductQty order by count(ProductQty) ASC";
//            //Log.i("_sql__",sql);
//            c = DBFunc.Query(sql, false);
//            if (c != null) {
//                sldQtyArr.clear();
//                sldNameArr.clear();
//                sltPriceTotalArr.clear();
//                while (c.moveToNext()) {
//                    if (!c.isNull(0)) {
//                        sldQtyArr.add(c.getString(1));
//                        //sldNameArr.add(c.getString(1));
//                        sldNameArr.add(c.getString(3));
//                        sltPriceTotalArr.add(c.getString(2));
//                    }
//                }
//                c.close();
//            }
//        }
    }

    public static void getBillDetailsAll(){
        String sql = "SELECT ID,BillNo,TotalQty,SubTotal," +
                "Totalamount,PaymentTypeID,PaymentAmount,SalesDateTime," +
                "GrossSales,TotalItemDisount,TotalBillDisount,GrossTotal,ServiceCharges,TotalNettSales,TotalTaxes,SalesDateTime,STATUS," +
                "PaymentTypeName,RoundAdj,DiscountValue,DiscountTypeName,DiscountName,TotalQty FROM Sales " +
                "where BillNo = '"+BillNo+"' order by BillNo DESC ";


//        TotalItemDisount,TotalBillDisount,GrossTotal,ServiceCharges,TotalNettSales,TotalTaxes,SalesDateTime,STATUS,PaymentTypeName?
        Cursor c = DBFunc.Query(sql,false);
        if (c != null) {
            if (c.getCount() == 0){
                String bidsql = "SELECT Date " +
                        "FROM BillList Where BillNo = '"+BillNo+"'";
                Log.i("__sqlSales", bidsql);
                Cursor cbl = DBFunc.Query(bidsql,false);
                if (cbl != null){
                    if (cbl.moveToNext()){
                        BillDateTime = cbl.getString(0);
                    }
                    cbl.close();
                }
            }
            while (c.moveToNext()) {
                if (!c.isNull(0)) {
                    BillDateTime = c.getString(7);
                    TaxesAmount = c.getString(14);
                    SubTotal = c.getDouble(3);
                    TotalItemDisount = c.getString(9);
                    TotalBillDisount = c.getString(10);
                    STATUS = c.getString(16);
                    totalNettAmount = c.getString(13);
                    ServiceCharges = c.getDouble(12);
                    TotalTax = c.getDouble(14);
                    RoundingAdj = c.getDouble(18);
                    DiscountValueSales = c.getDouble(19);
                    DiscountTypeNameSales = c.getString(20);
                    DiscountNameSales = c.getString(21);
                    TotalQtySales = c.getInt(22);
                }
            }
            c.close();
        }
        sql = "SELECT Name,EwalletPaymentType,EwalletStatus,PaymentRemarks,Amount FROM BillPayment " +
                "where BillNo = '"+BillNo+"' group by Name order by BillNo DESC  ";
        c = DBFunc.Query(sql,false);
        if (c != null) {
            PaymentTypeNameArr.clear();
            PaymentTypeRemarksArr.clear();
            while (c.moveToNext()) {
                if (!c.isNull(0)) {
                    if (c.getString(2) != null && c.getInt(2) == 1 && c.getString(1) != null && c.getString(1).length() > 1) {
                        PaymentTypeNameArr.add(c.getString(1)+"(" + c.getString(0) + ") "+"$"+"("+String.format("%.2f", c.getDouble(4)) +")");

                    } else {
                        PaymentTypeNameArr.add(c.getString(0)+"$"+"("+String.format("%.2f", c.getDouble(4))+")");
                    }
                    PaymentTypeRemarksArr.add(c.getString(3));
                }
            }
            c.close();
        }
    }
    public static Bitmap bitmap_qr_shoptima = null;
    //public static String accessablevoid = "0";
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_reprint_kitchenprinter:
                String chkKitchenPrinter = Query.GetOptions(27);
                if (chkKitchenPrinter.equals("1")) {

                    Context c = this;
                    final SweetAlertDialog syncDialog =  new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText(Constraints.KitchenPrinterPrintQuestion)
                            .setConfirmText(Constraints.YES)
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismissWithAnimation();

                                    CashLayoutActivity.KitChenPrinterFun(c, resourceVal, BillNo);
                                }
                            })
                            .setCancelButton(Constraints.No, new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismissWithAnimation();
                                }
                            });
                    syncDialog.show();
                    syncDialog.setCancelable(false);

                }
                break;
            case R.id.img_refresh_bill:

                DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                        System.currentTimeMillis(), DBFunc.PurifyString("TransactionDetailsActivity-OnClick " +"Refresh Bill-"+BillNo));

                Query.UpdateBillListForZClose(BillNo);

                final SweetAlertDialog refreshDialog =  new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Refresh Bill Success")
                        .setConfirmText(Constraints.OK)
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();
                            }
                        });
                refreshDialog.show();
                refreshDialog.setCancelable(false);
                break;
            case R.id.img_resync_billno:

                DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                        System.currentTimeMillis(), DBFunc.PurifyString("TransactionDetailsActivity-OnClick " +"Resync Bill-"+BillNo));

                Cursor config_values_pro_item = null;
                String url = "";
                config_values_pro_item = DBFunc.Query("SELECT retails_id,company_code,company_url,option FROM POSSys", true);
                if (config_values_pro_item != null) {
                    while (config_values_pro_item.moveToNext()) {
                        url = config_values_pro_item.getString(2);
                    }
                }
                if (url != null) {
                    final SweetAlertDialog syncDialog =  new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText(Constraints.ResyncBillNoQuestion)
                            .setConfirmText(Constraints.YES)
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismissWithAnimation();


//                                    Cursor config_values_pro_item = null;
//                                    String url = "";
//                                    config_values_pro_item = DBFunc.Query("SELECT retails_id,company_code,company_url,option FROM POSSys", true);
//                                    if (config_values_pro_item != null) {
//                                        while (config_values_pro_item.moveToNext()) {
//                                            url = config_values_pro_item.getString(2);
//                                        }
//                                    }

                                    String syncUrl = "";
                                    Cursor Cursor_Possys = Query.GetURLAndCodeFromPossys();
                                    if (Cursor_Possys != null) {
                                        while (Cursor_Possys.moveToNext()) {
                                            syncUrl = Cursor_Possys.getString(2);
                                        }
                                        Cursor_Possys.close();
                                    }

                                    if (syncUrl != null) {

                                        SyncActivity.ResyncOrNormal(appContext,BillNo,"","Normal","");
                                    }

                                }
                            })
                            .setCancelButton(Constraints.No, new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismissWithAnimation();
                                }
                            });
                    syncDialog.show();
                    syncDialog.setCancelable(false);

                }else {
                    new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Resync Sales")
                            .setConfirmText("URL Empty!")
                            .show();

                }


                break;
            case R.id.img_reprint:

                DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                        System.currentTimeMillis(), DBFunc.PurifyString("TransactionDetailsActivity-OnClick " +"Reprint Bill-"+BillNo));

                //String accessable = SyncActivity.volleyCheckPermission(this, Constraints.REPRINTBILL,Constraints.Accessable);
//                String accessable = Query.SearchUserAccess(Constraints.M0022, RemarkMainActivity.userid,RemarkMainActivity.userpassword);
//                if (accessable.equals("1")){
                //if (Constraints.Accessable.equals("1")) {
                    final SweetAlertDialog pDialog =  new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText(Constraints.ReprintQuestion)
                            .setConfirmText(Constraints.YES)
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismissWithAnimation();

                                    DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                                            System.currentTimeMillis(), DBFunc.PurifyString("TransactionDetailsActivity-OnClick-YES" +"Reprint Bill-"+BillNo));



                                    //CashLayoutActivity.PrintingReceipt(sales_id,getApplicationContext(),"SALES");
                                    //Bitmap[] bitmap_qr_shoptima = Query.GetPrintQRCodeOnReceipt(getApplicationContext());
                                    String chk_qr_code_on_receipt = Query.GetOptions(18);

                                    if (chk_qr_code_on_receipt.equals("1")) {
                                        // bitmap_qr_shoptima = Query.GetPrintQRCodeOnReceipt(getApplicationContext());
                                        //Query.GetPrintQRCodeOnReceipt(getApplicationContext());
                                        //QRCode Reprint
                                        GetPrintQRCodeOnReceipt(getApplicationContext(),BillNo,sales_id);
                                    }else {
                                        //Reprint Normal
                                        sales_id = getSalesIDByBillNo(TransactionDetailsActivity.BillNo);

                                        if (sales_id != null && sales_id > 0) {
                                            Query.CheckEmulatorOrNot(getApplicationContext(), sales_id, BillNo, "Reprint", "Reprint", null, null);
                                        }

//                                    Toast.makeText(getApplicationContext(), "Reprint" + " Successfully.", Toast.LENGTH_SHORT).show();
//                                    Intent intent = new Intent(getApplicationContext(), TransactionDetailsActivity.class);
//                                    intent.putExtra("BillNo", BillNo);
//                                    startActivity(intent);
//                                    finish();
                                    }

                                }
                            })
                            .setCancelButton(Constraints.No, new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismissWithAnimation();

                                    DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                                            System.currentTimeMillis(), DBFunc.PurifyString("TransactionDetailsActivity-OnClick-NO" +"Reprint Bill-"+BillNo));


                                }
                            });
                    pDialog.show();
                    pDialog.setCancelable(false);
//                } else {
//                    Query.DonothaveUserAccess(this);
//                }


                break;
            case R.id.btn_refund_or_checkout:

                DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                        System.currentTimeMillis(), DBFunc.PurifyString("TransactionDetailsActivity-OnClick " +"Refund Bill-"+BillNo));

                if (dateFormat3.equals(checkBillnodate)) {
                    final SweetAlertDialog pRefundDialog =   new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                            //.setTitleText("Cancelled Bill")
                            .setContentText("Can't allow to refund for today's bill.")
                            .setConfirmText(Constraints.OK)
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismissWithAnimation();
                                }
                            }) ;
                    pRefundDialog.show();
                    pRefundDialog.setCancelable(false);
                } else {
//                if (checkBillnodate.equals())
                    //accessable = SyncActivity.volleyCheckPermission(this, Constraints.REFUNDTRANS,Constraints.Accessable);
                    String accessablecancel = "0";
                    if (RemarkMainActivity.userid.equals("1111")){
                        accessablecancel = "1";
                    }else {
                        accessablecancel = Query.SearchUserAccess(Constraints.F0025, RemarkMainActivity.userid, RemarkMainActivity.userpassword);
                    }
                    if (accessablecancel.equals("1")) {

                        if (sales_id > 0) {
                            final SweetAlertDialog pRefundDialog = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                                    //.setTitleText("Cancelled Bill")
                                    .setContentText("Are you sure you want to refund?")
                                    .setConfirmText(Constraints.YES)
                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sDialog) {
                                            RefundSales(BillNo, sales_id);

//                                    Toast.makeText(getApplicationContext(), "Refund" + " Successfully.", Toast.LENGTH_SHORT).show();
//                                    Intent intent = new Intent(getApplicationContext(), TransactionDetailsActivity.class);
//                                    intent.putExtra("BillNo", BillNo);
//                                    startActivity(intent);
//                                    finish();
                                        }
                                    })
                                    .setCancelButton(Constraints.No, new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sDialog) {
                                            sDialog.dismissWithAnimation();
                                        }
                                    });
                            pRefundDialog.show();
                            pRefundDialog.setCancelable(false);
                        }

                    } else {
                        Query.DonothaveUserAccess(this);
                    }
                    if (STATUS.equals("VOID")) {
                        new AlertDialog.Builder(this, R.style.AlertDialogStyle)
                                .setMessage("Bill Already Cancel!")
                                .setCancelable(false)
                                .setNegativeButton(Constraints.OK, null)
                                .show();
                    } else {
                        if (STATUS.equals("Refund")) {
                            new AlertDialog.Builder(this, R.style.AlertDialogStyle)
                                    .setMessage("Already Refund Bill!")
                                    .setCancelable(false)
                                    .setNegativeButton(Constraints.OK, null)
                                    .show();
                        }
                    }
                }
                break;
            case R.id.btn_cancel_bill:

                DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                        System.currentTimeMillis(), DBFunc.PurifyString("TransactionDetailsActivity-OnClick " +"Cancel Bill-"+BillNo));

                if (!(dateFormat3.equals(checkBillnodate))) {
                    final SweetAlertDialog pRefundDialog =   new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                            //.setTitleText("Cancelled Bill")
                            .setContentText("Can't allow to refund for today's bill.")
                            .setConfirmText(Constraints.OK)
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismissWithAnimation();
                                }
                            }) ;
                    pRefundDialog.show();
                    pRefundDialog.setCancelable(false);
                } else {
//                 String accessablevoid = SyncActivity.volleyCheckPermission(this, Constraints.VOIDTRANS,"0");
//                 Log.i("__","Accessable_VOIDTRANS_"+accessablevoid);
                    String accessablecancel = "0";
                    if (RemarkMainActivity.userid.equals("1111")){
                        accessablecancel = "1";
                    }else {
                        accessablecancel = Query.SearchUserAccess(Constraints.F0019, RemarkMainActivity.userid,RemarkMainActivity.userpassword);
                    }
                if (accessablecancel.equals("1")){
                    if (STATUS.equals("VOID")){

                                new AlertDialog.Builder(this, R.style.AlertDialogStyle)
                                        .setMessage("Already Cancel Bill!")
                                        .setCancelable(false)
                                        .setNegativeButton(Constraints.OK, null)
                                        .show();

                        }else {
                            final SweetAlertDialog pRefundDialog =   new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                                    //.setTitleText("Cancelled Bill")
                                    .setContentText("Are you sure you want to cancel?")
                                    .setConfirmText(Constraints.YES)
                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sDialog) {
                                            sDialog.dismissWithAnimation();


                                            CashLayoutActivity.cancelBill(BillNo,TransactionDetailsActivity.this,sales_id);

                                        }
                                    })
                                    .setCancelButton(Constraints.No, new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sDialog) {
                                            sDialog.dismissWithAnimation();
                                        }
                                    });
                            pRefundDialog.show();
                            pRefundDialog.setCancelable(false);
                        }
                    } else {
                        Query.DonothaveUserAccess(this);
                    }
                }
                break;
            case R.id.btn_collected_bill_txt:
                CollectedOrDeliveryFun("Collected",BillNo);
                break;
            case R.id.btn_delivery_txt:
                CollectedOrDeliveryFun("Delivery",BillNo);
                break;
        }
    }

    private void CollectedOrDeliveryFun(String status,String billNo) {
        Query.UpdateCollectedOrDeliveryByBillNo(status,billNo);
        Intent i = new Intent(TransactionDetailsActivity.this,TransactionDetailsActivity.class);
        i.putExtra("BillNo", String.valueOf(billNo));
        startActivity(i);
        finish();
    }

    public static void GetPrintQRCodeOnReceipt(Context context, String BillNo, Integer sales_id) {
        //Bitmap[] bitmapQRCode = new Bitmap[1];
        //Bitmap bitmapQRCode = null;
        String  status_str_info = Query.GetStatus(BillNo);

        if (status_str_info.toUpperCase().equals(Constraints.SALES.toUpperCase())) {
            String str_info = Query.GetQRCodeFromBillPayment(BillNo);

            //bitmapQRCode = GenerateQRCodeString(context, str_info);
            GenerateQRCodeString(context, str_info,BillNo,sales_id);
        }
        //Log.i("TAGGG___","status_str_info__d_"+bitmapQRCode);
        //return bitmapQRCode;
    }
    //public static Bitmap bitmapQRCode = null;
    //public static ProgressDialog progressDialog;
    private static void GenerateQRCodeString(final Context context, final String strInfo, final String BillNo, final Integer sales_id) {

        final SweetAlertDialog pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        //final SweetAlertDialog pDialog = new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading ...");
        //pDialog.setCancelable(true);
//        pDialog.show();
        //final Bitmap[] bitmapQRCode = new Bitmap[1];

        RequestQueue queue_qrcode = Volley.newRequestQueue(context);
        Log.i("First_TESTING","First_TESTING");
//        RequestQueue queue = Volley.newRequestQueue(this);
        //String url = "http://" + qr_code_shoptima_url;
        String url = MainActivity.qr_code_shoptima_url;
        Log.i("qr_code_shopturl_dd",url);
        //String url = "http://" + "llposmgr.ddns.net:8085/Service.asmx";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //pDialog.dismiss();
                        // response code
                        String xmlString = response;
                        Log.i("GenerateQRCodeString_", response);
//                        <?xml version="1.0" encoding="utf-8"?><soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema"><soap:Body><GenerateQRCodeStringResponse xmlns="http://tempuri.org/"><GenerateQRCodeStringResult><xs:schema id="NewDataSet" xmlns="" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:msdata="urn:schemas-microsoft-com:xml-msdata"><xs:element name="NewDataSet" msdata:IsDataSet="true" msdata:MainDataTable="ReturnTable" msdata:UseCurrentLocale="true"><xs:complexType><xs:choice minOccurs="0" maxOccurs="unbounded"><xs:element name="ReturnTable"><xs:complexType><xs:sequence><xs:element name="STATUS" type="xs:string" minOccurs="0" /><xs:element name="ERRORCODE" type="xs:string" minOccurs="0" /><xs:element name="QRCODE" type="xs:string" minOccurs="0" /></xs:sequence></xs:complexType></xs:element></xs:choice></xs:complexType></xs:element></xs:schema><diffgr:diffgram xmlns:msdata="urn:schemas-microsoft-com:xml-msdata" xmlns:diffgr="urn:schemas-microsoft-com:xml-diffgram-v1"><DocumentElement xmlns=""><ReturnTable diffgr:id="ReturnTable1" msdata:rowOrder="0" diffgr:hasChanges="inserted"><STATUS>SUCCESS</STATUS><ERRORCODE>0</ERRORCODE><QRCODE>dcs:ZMiWiadjOZm90Rszvn5+SDCYtZ060vKEq+XRWhfgR3Jd6Lujro5gl1iKxsSKQuoOHUUjpInfZ6HmrecQ5LLuOgRvmPm750E4F1td66GcmRS7I16DCd4SRCEMWexHpdxNP5T89iNNIVFXrq8RP1sbdQ==</QRCODE></ReturnTable></DocumentElement></diffgr:diffgram></GenerateQRCodeStringResult></GenerateQRCodeStringResponse></soap:Body></soap:Envelope>
//
                        Document xmlparse = null;
                        Document parse = APIActivity.XMLParseFunction(xmlString, xmlparse);
                        String status = "";
                        //String qrcodestring = "";
                        String dcsQrCodeString = "";
                        for (int ii = 0; ii < parse.getElementsByTagName("STATUS").getLength(); ii++) {
                            status = (parse.getElementsByTagName("STATUS").getLength() > 0)
                                    ? parse.getElementsByTagName("STATUS").item(ii).getTextContent() : " ";
                        }
                        for (int iii = 0; iii < parse.getElementsByTagName("QRCODE").getLength(); iii++) {
                            dcsQrCodeString = (parse.getElementsByTagName("QRCODE").getLength() > 0)
                                    ? parse.getElementsByTagName("QRCODE").item(iii).getTextContent() : " ";
                        }

                        //qrcodestring = dcsQrCodeString.split(":")[1];
                       String qrcodestring = dcsQrCodeString;
                        //qrcodestring = "dcs:ZMiWiadjOZm90Rszvn5+SG7Ep2MsGAfnfx8xoCE1e3rA4mtgaoy0BfjbFEHlqY5dRAZzgiBdN8zO+uJulLTQ3flsQ0wozp2FOyNmX2pCABM1QJIlTooayanA5NhSv3nQboljX7a7KnjoRCF/zOXccA==";
                        Log.i("qrcodestring_","qrcodestring_"+qrcodestring);
                        //bitmap_qr_shoptima = convertQRCodeImage(context,qrcodestring);
                        //convertQRCodeImage(dcsQrCodeString);
                        //bitmapQRCode[0] = convertQRCodeImage(context, qrcodestring);
                        //bitmapQRCode = convertQRCodeImage(context, qrcodestring);
                        convertQRCodeImage(context, qrcodestring, pDialog,BillNo,sales_id);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("First_TESTING","First_error");
//                        // As of f605da3 the following should work
                NetworkResponse response = error.networkResponse;
                Log.i("errorres:1",error.getMessage());
                Log.i("errorres:", String.valueOf(error));
                if (error instanceof ServerError && response != null) {
                    try {
                        String res = new String(response.data,
                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));

                        Log.i("res:",res);
                        // Now you can use any deserializer to make sense of data
//                                JSONObject obj = new JSONObject(res);
                    } catch (UnsupportedEncodingException e1) {
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
                String encodedURL = null;
                String temp = "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
                        "  <soap12:Body>\n" +
                        "    <GenerateQRCodeString xmlns=\"http://tempuri.org/\">\n" +
                        "      <UserID>"+MainActivity.qr_code_shoptima_user_id+"</UserID>\n" +
                        "      <Password>"+MainActivity.qr_code_shoptima_password+"</Password>\n" +
                        "      <strInfo>"+strInfo+"</strInfo>\n" +
                        "    </GenerateQRCodeString>\n" +
                        "  </soap12:Body>\n" +
                        "</soap12:Envelope>";
                Log.i("APPshp", "request__String: " + temp);
                byte[] b = temp.getBytes(Charset.forName("UTF-8"));

                return b;
            }

            @Override
            public String getBodyContentType() {
                return "text/xml;charset=utf-8";
            }
        };
        queue_qrcode.add(stringRequest);
        //pDialog.show();
        //return bitmapQRCode;
        //progressDialog = Query.showProgressDialog(context, ENUM.Downaloding);
    }

    //    private void convertQRCodeImage(String dcsQrCodeString, Bitmap qrcode_bitmap, int billID) {
    private static void convertQRCodeImage(Context context, String dcsQrCodeString, SweetAlertDialog pDialog, String BillNo, Integer sales_id) {
        //pDialog.dismiss();
        Bitmap bitmap_qr_shoptima = null;
        try {
            //this.qrcode_bitmap = TextToImageEncode(dcsQrCodeString);
//            final SweetAlertDialog pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
//            pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
//            pDialog.setTitleText("Loading ...");
//            pDialog.show();
            bitmap_qr_shoptima = TextToImageEncode(context,dcsQrCodeString,pDialog);
//
            Bitmap bitmap__ = null;
            bitmap__ = Query.GetLogPrint();
//

            Query.CheckEmulatorOrNot(context, sales_id, BillNo, "Reprint","Reprint",bitmap__,bitmap_qr_shoptima);

            CheckOutActivity.str_percentage_value = "0";
            CheckOutActivity.discount_amount = 0.0;
            //bitmap_qr_shoptima = bitmap_qr_shoptima;
            //CashLayoutActivity.bitmap_qr_shoptima = bitmap_qr_shoptima;
            //TransactionDetailsActivity.bitmap_qr_shoptima = bitmap_qr_shoptima;
            //qrcode_bitmap = TextToImageEncode(dcsQrCodeString);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        //return bitmap_qr_shoptima;
    }
    public static Bitmap TextToImageEncode(Context context, String Value, SweetAlertDialog pDialog) throws WriterException {
        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(
                    Value,
                    DATA_MATRIX.QR_CODE,
                    500, 500, null
            );

        } catch (IllegalArgumentException Illegalargumentexception) {
            Log.i("ssd","Illegalargumentexception"+Illegalargumentexception);
            return null;
        }

        int bitMatrixWidth = bitMatrix.getWidth();

        int bitMatrixHeight = bitMatrix.getHeight();

        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];

        for (int y = 0; y < bitMatrixHeight; y++) {
            int offset = y * bitMatrixWidth;

            for (int x = 0; x < bitMatrixWidth; x++) {

                pixels[offset + x] = bitMatrix.get(x, y) ?
                        context.getResources().getColor(R.color.cardview_dark_background):context.getResources().getColor(R.color.cardview_light_background);
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);

        bitmap.setPixels(pixels, 0, 500, 0, 0, bitMatrixWidth, bitMatrixHeight);

        //progressDialog.dismiss();
        //Dialog.dismiss();
        return bitmap;
    }

    private void RefundSales(String BillNo,Integer sales_id) {

        Query.RefundBill(sales_id,"AA",BillNo);
//
//        //Refund
        String bno = BillNo;
        Integer sid = 0;
        String sql_sale_ = "SELECT BillNo,ID From Sales WHERE ReferenceBillNo = '"+BillNo+"'";
        Cursor csql_sale = DBFunc.Query(sql_sale_,false);
        if (csql_sale != null) {
            if (csql_sale.getCount() == 0){
                bno = BillNo;
                sid = 0;
            }
            if (csql_sale.moveToNext()) {
                bno = csql_sale.getString(0);
                sid = csql_sale.getInt(1);
            }
            csql_sale.close();
        }

        Bitmap bitmap__ = Query.GetLogPrint();

        Query.CheckEmulatorOrNot(getApplicationContext(), sid, bno, "Refund","Refund",bitmap__,null);

        Query.UpdatStockAgentByStatus(BillNo);


        SyncActivity.ResyncOrNormal(TransactionDetailsActivity.this,bno,"","Normal","");


    }

    private void cancelBill(Integer sales_id) {
        //CashLayoutActivity.cancelBill(BillNo,appContext,sales_id);



    }

    public static IDAL getDal() {
        if (dal == null) {
            try {
                long start = System.currentTimeMillis();
                dal = NeptuneLiteUser.getInstance().getDal(appContext);
                Log.i("Test", "get dal cost:" + (System.currentTimeMillis() - start) + " ms");
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(appContext, "error occurred,DAL is null.", Toast.LENGTH_LONG).show();
            }
        }
        return dal;
    }

}
