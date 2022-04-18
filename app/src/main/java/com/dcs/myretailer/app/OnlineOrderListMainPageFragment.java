package com.dcs.myretailer.app;

import android.app.NotificationManager;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dcs.myretailer.app.Activity.CashLayoutActivity;
import com.dcs.myretailer.app.Activity.CheckOutActivity;
import com.dcs.myretailer.app.Model.BillListModel;
import com.dcs.myretailer.app.Cashier.BillData;
import com.dcs.myretailer.app.Cashier.ButtonAdapter;
import com.dcs.myretailer.app.Cashier.ClearBillSheetFragment;
import com.dcs.myretailer.app.Cashier.RecyclerAdapter;
import com.dcs.myretailer.app.Cashier.RecyclerViewAdapter;
import com.dcs.myretailer.app.Cashier.SalesDelievery.SalesDelivery;
import com.dcs.myretailer.app.Cashier.SalesDelievery.SalesDeliveryItem;
import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;
import com.jmedeisis.draglinearlayout.DragLinearLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class OnlineOrderListMainPageFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    //private List<BillDetails> lstBillDetails = new ArrayList<BillDetails>();
    private List<BillListModel> lstBill = new ArrayList<BillListModel>();
    public static String str_newText = "";
    Handler mHandler;
    Handler mHandler_online_order;
    public static String status_on = "0";
    String obj_RECEIPTFLAG = "0";
    String obj_QueueNo = "0";
    String obj_TableNo = "0";
    String obj_SALESID = "0";
    String obj_SALESNO = "0";
    String obj_SALESDATE = "0";
    String obj_CREATETIME = "0";
    String obj_SALESAMOUNT = "0";
    String TransID = "0";
    String CompanyID = "0";
    String RetailerID = "0";
    String TransNo = "0";
    String TotalDue = "0";
    String TotalGST = "0";
    String TotalDisc = "0";
    String TransDate = "0";
    String CreateTime = "0";
    String TotalQty = "0";
    String CashierID = "0";
    String MemberID = "0";
    String isNewCust = "0";
    String SalesPersonID = "0";
    String CommID = "0";
    String CommPerc = "0";
    String vchQueueNo = "0";
    String intTableNo = "0";
    String MacAddress = "0";
    String ZReadNo = "0";
    String SalesID = "0";
    String RetailID = "0";
    String DOID = "0";
    String Bill_ID = "0";
    String SalesNo = "0";
    String SalesTax = "0";
    String SalesTaxVal = "0";
    String SalesDate = "0";
    String CloseRetailID = "0";
    String CloseDate = "0";
    String CloseTime = "0";
    String CloseSalesID = "0";
    String Sales_DetailID = "0";
    String ItemIDD = "0";
    String ItemName_Item_Detail_Payment = "0";
    String SupBarCode = "0";
    String ItemQty_Item_Detail_Payment = "0";
    String ItemUOMDesc = "0";
    String ItemQtyAct = "0";
    String ItemUnitPrice_Item_Detail_Payment = "0";
    String ItemUnitPrice_ItemName_Detail_Payment = "0";
    String ItemTotalDisc_Item_Detail_Payment = "0";
    String ItemTaxTotal_Item_Detail_Payment = "0";
    String ItemUnitCost = "0";
    String ItemAveCost = "0";
    String ItemSSPx = "0";

    String  TransID_Hold = "0";
    String  TransNo_Hold = "0";
    String  TotalDue_Hold = "0";
    String  ReceiptOrderStatus_Hold = "0";
    String  vchQueueNo_Hold = "0";
    String  intTableNo_Hold = "0";

    Double total_price_Item_Hold = 0.0;
    String TransID_Item_Hold = "0";
    String RecordNo_Item_Hold = "0";
    String LineNo_Item_Hold = "0";
    String ItemQty_Item_Hold = "0";
    String ItemPrice_Item_Hold = "0";
    String ItemTotal_Item_Hold = "0";
    String ItemGST_Item_Hold = "0";
    String ItemDiscType_Item_Hold = "0";
    String ItemDisc1_Item_Hold = "0";
    String ItemDisc2_Item_Hold =  "0";
    String ItemDisc3_Item_Hold =  "0";;
    String IttemID_Item_Hold = "0";
    String IttemID_Item_Payment = "0";
    String ItemName_Item_Payment = "0";
    String ItemPrice_Item_Payment = "0";

    //ItemName = retail_sales_arr_Valuesh.getString("ItemName");
    String ItemName_Item_Hold = "0";
    String ItemBarcode_Item_Hold = "0";
    String ItemUOM_Item_Hold = "0";
    String ItemGSTInEx_Item_Hold = "0";
    String ItemCost_Item_Hold = "0";
    String ItemActQty_Item_Hold = "0";
    String ItemUOMID_Item_Hold = "0";
    String ItemGroupDisc_Item_Hold = "0";
    String ItemSKU_Item_Hold = "0";
    String SupplierID_Item_Hold = "0";
    String SalesPersonID_Item_Hold = "0";
    String SalesCommTypeID_Item_Hold = "0";
    String SalesCommPerc_Item_Hold = "0";
    String ItemCommPerc_Item_Hold = "0";
    String ItemCommAmt_Item_Hold = "0";
    String ItemSerialNo_Item_Hold = "0";
    String DISCID_Item_Hold = "0";
    String ItemIMEINo_Item_Hold = "0";
    String ItemBatchNo_Item_Hold = "0";
    String ItemStatus_Item_Hold = "0";
    String OpenPriceRemark_Item_Hold = "0";
    String ItemRemark_Item_Hold = "0";
    String ExpireDate_Item_Hold = "0";
    String ExpiryDay_Item_Hold = "0";
    String RedeemPoint_Item_Hold = "0";
    String ParentItemID_ADDON_Item_Hold ="0";
    String bitAddOnItem_Item_Hold = "0";
    String ParentDetailID_ADDON_Item_Hold = "0";
    String MemDOBDiscPerc_Item_Hold = "0";
    String MemDOBDiscAmount_Item_Hold = "0";
    String  ReceiptOrderStatus_Item_Hold = "0";
    String TerminalID_Item_Hold = "0";
    String RFID_Item_Hold = "0";
    String PendingSync_Item_Hold =  "0";
    String SPBI01_Item_Hold=  "0";
    String SPBI02_Item_Hold =  "0";
    String SPBI03_Item_Hold =  "0";
    String SPBI04_Item_Hold = "0";
    String SPBI05_Item_Hold =  "0";
    String SPD01_Item_Hold =  "0";
    String SPD02_Item_Hold = "0";

    String SalesID_Payment = "";
    String RetailID_Payment = "";
    String DOID_Payment = "";
    String Bill_ID_Payment = "";
    String vchQueueNo_Payment = "";
    String intTableNo_Payment = "";
    String ReceiptOrderStatus_Payment = "";
    String SalesNo_Payment = "";
    String SalesTax_Payment = "";
    String SalesTaxVal_Payment = "";
    String SalesDate_Payment = "";
    String CloseRetailID_Payment = "";
    String CloseDate_Payment = "";
    String CloseTime_Payment = "";
    String CloseSalesID_Payment = "";
    String SalesSubTtl_Payment = "";
    String SalesBalTtl_Payment = "";
    String SalesChangeAmt_Payment = "";
    String SalesPayTtl_Payment = "";
    String SalesRounding_Payment = "";

    String ItemTaxType = "0";
    String SalesPaymentID = "0";
    String PaymentID = "0";
    String PaymentReference = "0";
    String ReceiptRemarks = "0";
    String OthersPayment = "0";
    String OthersPaymentRef = "0";
    String Close_SalesID = "0";
    String Close_TerminalID = "0";
    String CardDisc = "0";
    String SalesPayTtl = "0";
    String SalesBalTtl = "0";
    String SalesDeposit = "0";
    String ChangeAmount = "0";
    String TipsAmount = "0";
    String PaymentStatus = "0";
    String DepositStatus = "0";
    String Close_RetailID = "0";
    String CardAmt = "0";
    String ZReadDate = "0";

    String PostSalesResponse = "";
    String GetDetailsForOrderResult = "";
    String GetSalesForOrderingResponse = "0";
    String UpdateStatusForOnlineOrderResponse = "";
    String RecordNo = "0";
    String LineNo = "0";
    String ItemQty = "0";
   //String ItemPrice = "0";
    String ItemTotal = "0";
    String ItemGST = "0";
    String ItemDiscType = "0";
    String ItemDisc1 = "0";
    String ItemDisc2 = "0";
    String ItemDisc3 = "0";
    String IttemID = "0";
    String ItemBarcode = "0";
    String ItemUOM = "0";
    String ItemGSTInEx = "0";
    String ItemCost = "0";
    String ItemActQty = "0";
    String ItemUOMID = "0";
    String ItemGroupDisc = "0";
    String ItemSKU = "0";
    String SupplierID = "0";
    String SalesCommTypeID = "0";
    String SalesCommPerc = "0";
    String ItemCommPerc = "0";
    String ItemCommAmt = "0";
    String ItemSerialNo = "0";
    String DISCID = "0";
    String ItemIMEINo = "0";
    String ItemBatchNo = "0";
    String ItemStatus = "0";
    String OpenPriceRemark = "0";
    String ItemRemark = "0";
    String ExpireDate = "0";
    String ExpiryDay = "0";
    String RedeemPoint = "0";
    String ParentItemID_ADDON = "";
    String bitAddOnItem = "0";
    String ParentDetailID_ADDON = "0";
    String MemDOBDiscPerc = "0";
    String MemDOBDiscAmount = "0";
    String ReceiptOrderStatus = "0";
    String TerminalID = "0";
    String RFID = "0";
    String PendingSync = "0";
    String SPBI01 = "0";
    String SPBI02 = "0";
    String SPBI03 = "0";
    String SPBI04 = "0";
    String SPBI05 = "0";
    String SPD01 = "0";
    String SPD02 = "0";
    String SPD03 = "0";
    String SPD04 = "0";
    String SPD05 = "0";
    String SPI01 = "0";
    String SPI02 = "0";
    String SPI03 = "0";
    String SPI04 = "0";
    String SPI05 = "0";
    String SPC01 = "0";
    String SPC02 = "0";
    String SPC03 = "0";
    String SPC04 = "0";
    String SPC05 = "0";
    String SPV01 = "0";
    String SPV02 = "0";
    String SPV03 = "0";
    String SPV04 = "0";
    String SPV05 = "0";
    String SPT01 = "0";
    String SPT02 = "0";
    String SPT03 = "0";
    String SPT04 = "0";
    String SPT05 = "0";
    String SDT01 = "0";
    String SDT02 = "0";
    String SDT03 = "0";
    String SDT04 = "0";
    String SDT05 = "0";
    String LastUser = "0";
    String LastUpdate = "0";
    String LockUser = "0";
    String LockUpdate = "0";
    String LockStatus = "0";
    String RecordStatus = "0";
    String RecordUpdate = "0";
    String QueueStatus = "0";
    String downloadRetailID = "";
    String terminal_id = "";
    private String downloadUrl = "";
    String downloadCompanyCode = "";
    BitmapDrawable drawable = null;
    Bitmap bitmap;
    RequestQueue queue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.billlist_mainpage_list, container, false);

        MainPagePagerAdapter.OnlineOrderBillStatusOn = "0";

        getBillAll();

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        //recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

//        adapter = new RecyclerAdapter(getContext(), createList(20));
//        adapter = new RecyclerAdapter(getContext(), createList(20));
        RecyclerViewAdapter.count = 0;

        adapter = new RecyclerAdapter(getContext(), lstBill);
        recyclerView.setAdapter(adapter);

        queue = Volley.newRequestQueue(getContext());

//        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new
                DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
        String optValue = Query.GetOptions(19);

        if (optValue.equals("1")) {
            Cursor CPossys = Query.GetURLAndCodeFromPossys();
            if (CPossys != null) {
                while (CPossys.moveToNext()) {
                    downloadRetailID = CPossys.getString(3);
                    downloadCompanyCode = CPossys.getString(4);
                    downloadUrl = CPossys.getString(5);
                    terminal_id = CPossys.getString(6);
                }
                CPossys.close();
            }
        }
        //queue = Volley.newRequestQueue(getContext());
        Bitmap icon = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.default_no_image);
        drawable = new BitmapDrawable(this.getResources(), icon);
        bitmap = drawable.getBitmap();
        mHandler = new Handler();
        mHandler_online_order = new Handler();
        //m_Runnable.run();
        m_Runnable_online_order.run();
        return view;
    }

    private final Runnable m_Runnable_online_order = new Runnable() {
        public void run() {
            if (getContext() != null) {

                if (downloadUrl != null) {
                    if (!downloadUrl.isEmpty()) {
                        String optValue = Query.GetOptions(19);

                        if (optValue.equals("1")) {
                            GetSalesForOrdering(getContext());
                            getBillAll();
                            RecyclerViewAdapter.count = 0;
                            adapter = new RecyclerAdapter(getContext(), lstBill);
                            recyclerView.setAdapter(adapter);
                        }
                    }

                    mHandler_online_order.postDelayed(m_Runnable_online_order, 5000);
                }
            }
        }

    };

    private void GetSalesForOrdering(final Context context) {
        if (context != null) {
            //RequestQueue queue = Volley.newRequestQueue(context);
            final String finalCompany_code = downloadCompanyCode;
            final String finalRetails_ID = downloadRetailID;
            final JSONObject jsonObject = new JSONObject();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, downloadUrl,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // response code
                            String xmlString = response;
                            Log.i("inv_xmlString", xmlString);
                            Document xmlparse = null;
                            Document parse = APIActivity.XMLParseFunction(xmlString, xmlparse);
                            for (int i = 0; i < parse.getElementsByTagName("GetSalesForOrderingResponse").getLength(); i++) {
                                GetSalesForOrderingResponse = (parse.getElementsByTagName("GetSalesForOrderingResponse").getLength() > 0)
                                        ? parse.getElementsByTagName("GetSalesForOrderingResponse").item(i).getTextContent() : " ";
                            }
                            if (!GetSalesForOrderingResponse.equals("0")) {
                                try {
                                    JSONObject obj = new JSONObject(GetSalesForOrderingResponse);
                                    String RETAIL_SALES = obj.getString("RETAIL_SALES");
                                    JSONArray mJsonArray_RETAIL_SALES = new JSONArray(RETAIL_SALES);
                                    for (int i = 0; i < mJsonArray_RETAIL_SALES.length(); i++) {
                                        JSONObject retail_sales_arr_Values = mJsonArray_RETAIL_SALES.getJSONObject(i);
                                        obj_SALESID = retail_sales_arr_Values.getString("SALESID");
                                        obj_SALESNO = retail_sales_arr_Values.getString("SALESNO");
                                        obj_SALESDATE = retail_sales_arr_Values.getString("SALESDATE");
                                        //obj_CREATETIME = retail_sales_arr_Values.getString("CREATETIME");
                                        obj_SALESAMOUNT = retail_sales_arr_Values.getString("SALESAMOUNT");
                                        obj_RECEIPTFLAG = retail_sales_arr_Values.getString("RECEIPTFLAG");
                                        obj_QueueNo = retail_sales_arr_Values.getString("QueueNo");
                                        obj_TableNo = retail_sales_arr_Values.getString("TableNo");

                                        if (obj_RECEIPTFLAG.length() > 0) {
                                            String sql_billno = "SELECT ID FROM DetailsBillProduct WHERE BillNo = '" + obj_SALESNO + "'";

                                            Cursor Cursor_DetailsBill = DBFunc.Query(sql_billno, false);
                                            if (Cursor_DetailsBill != null) {
                                                if (Cursor_DetailsBill.getCount() == 0) {
                                                    GetDetailsForOrder(obj_RECEIPTFLAG, context);
                                                }
                                                Cursor_DetailsBill.close();
                                            }
                                        }
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // As of f605da3 the following should work
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
                            "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                            "  <soap:Body>\n" +
                            "    <GetSalesForOrdering xmlns=\"http://tempuri.org/\">\n" +
                            "      <companyCode>" + finalCompany_code + "</companyCode>\n" +
                            "      <retailid>" + finalRetails_ID + "</retailid>\n" +
                            "    </GetSalesForOrdering>\n" +
                            "  </soap:Body>\n" +
                            "</soap:Envelope>";
                    //Log.i("APP", "request String: " + temp);
                    byte[] b = temp.getBytes(Charset.forName("UTF-8"));

                    return b;
                }

                @Override
                public String getBodyContentType() {
                    return "text/xml;charset=utf-8";
                }
            };
            Log.i("_stringRsssssequest", String.valueOf(stringRequest));
            queue.add(stringRequest);
        }
    }
     //.setContentTitle("Order Arrived")
    //                .setContentText("No : "+SalesNo)
    private void SoundAlert(Context context,String title,String contentText) {
        //Define Notification Manager
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

//Define sound URI
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_tax)
//                .setContentTitle("Order Arrived")
//                .setContentText("No : "+SalesNo)
                .setContentTitle(title)
                .setContentText(contentText)
                .setSound(soundUri); //This sets the sound to play

        //Display notification
        assert notificationManager != null;
        int m = (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);
        notificationManager.notify(new Random().nextInt() , mBuilder.build());
    }
    private void GetDetailsForOrder(final String obj_RECEIPTFLAG, Context context) {
        if (getContext() != null) {
            //RequestQueue queue = Volley.newRequestQueue(this);
            //RequestQueue queue = Volley.newRequestQueue(getContext());
            final String finalRetail_ID = downloadRetailID;
            String finalSalesStatus = "";
            Log.i("Dfd_obj_RECEIPTFLAG", obj_RECEIPTFLAG);
            if (obj_RECEIPTFLAG.equals("H")) {
                finalSalesStatus = "HOLD";
            } else {
                finalSalesStatus = "PAYMENT";
            }
            final String finalCompany_code = downloadCompanyCode;
            final String finalSaleNo = obj_SALESNO;
            final JSONObject jsonObject = new JSONObject();
            final String finalSalesStatus1 = finalSalesStatus;
            StringRequest stringRequest = new StringRequest(Request.Method.POST, downloadUrl,
                    new Response.Listener<String>() {
                        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                        @Override
                        public void onResponse(String response) {
                            // response code
                            String xmlString = response;
                            //Log.i("inv_xmlString",xmlString);
                            Document xmlparse = null;
                            Document parse = APIActivity.XMLParseFunction(xmlString, xmlparse);
                            for (int i = 0; i < parse.getElementsByTagName("GetDetailsForOrderResult").getLength(); i++) {
                                GetDetailsForOrderResult = (parse.getElementsByTagName("GetDetailsForOrderResult").getLength() > 0)
                                        ? parse.getElementsByTagName("GetDetailsForOrderResult").item(i).getTextContent() : " ";
                            }

                            RETAIL_SALES(GetDetailsForOrderResult, obj_RECEIPTFLAG);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // As of f605da3 the following should work
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
                            "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                            "  <soap:Body>\n" +
                            "    <GetDetailsForOrder xmlns=\"http://tempuri.org/\">\n" +
                            "      <companyCode>" + finalCompany_code + "</companyCode>\n" +
                            "      <salesno>" + finalSaleNo + "</salesno>\n" +
                            "      <retailid>" + finalRetail_ID + "</retailid>\n" +
                            "      <salesstatus>" + finalSalesStatus1 + "</salesstatus>\n" +
                            "    </GetDetailsForOrder>\n" +
                            "  </soap:Body>\n" +
                            "</soap:Envelope>";
                    //Log.i("APP", "request String: " + temp);
                    byte[] b = temp.getBytes(Charset.forName("UTF-8"));

                    return b;
                }

                @Override
                public String getBodyContentType() {
                    return "text/xml;charset=utf-8";
                }
            };
            //Log.i("_stringRsssssequest", String.valueOf(stringRequest));
            queue.add(stringRequest);
        }
    }

    Double total_price = 0.0;
    Integer total_ItemQty = 0;
    ArrayList<Integer> MID = new ArrayList<Integer>();
    ArrayList<String> modVal = new ArrayList<String>();
    ArrayList<String> modParentItemVal = new ArrayList<String>();

    private void RETAIL_SALES(String ResponseObj, String obj_receiptflag) {
        try {
            JSONObject obj = new JSONObject(ResponseObj);

            if (obj_receiptflag.equals("H")) {

               OnlineHoldBillFun(obj);

            } else {

                if (obj_receiptflag.length() == 1) {

                    OnlinePaymentBillFun(obj);
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void OnlinePaymentBillFun(JSONObject obj) {

        String Payment_RetailSales = null;
        try {
            //Log.i("__obj","objddd____"+obj.getString("SALESDELIVERY"));
            Payment_RetailSales = obj.getString("RETAIL_SALES");
            JSONArray JSONArr_PRSales = new JSONArray(Payment_RetailSales);

            for (int i = 0; i < JSONArr_PRSales.length(); i++) {
                JSONObject JSONObj_PRSales = JSONArr_PRSales.getJSONObject(i);
                InsertValueIntoVariable(JSONObj_PRSales);

                SoundAlert(getContext(),"Order Arrived","Receipt No : "+SalesNo_Payment);
            }

            //Save DetailBillProduct // Save PLU
            String Payment_RetailSalesDetail = obj.getString("RETAIL_SALES_DETAIL");

            JSONArray JSONArr_PRSD = new JSONArray(Payment_RetailSalesDetail);


            SaveOnlinePaymentBill(JSONArr_PRSD);

            for (int i = 0; i < JSONArr_PRSales.length(); i++) {
                CreateNewBillForOnlineBill(SalesID_Payment,SalesNo_Payment,vchQueueNo_Payment,intTableNo_Payment,ReceiptOrderStatus_Payment);

                Double GrossSales = Double.valueOf(SalesBalTtl_Payment) - Double.valueOf(SalesTaxVal_Payment);
                Integer sales_qty = Query.GetQtyByBillNo(SalesNo_Payment);

//                Query.SaveSales(SalesNo_Payment,sales_qty,Double.valueOf(SalesSubTtl_Payment),
//                        Double.valueOf(SalesBalTtl_Payment),SalesChangeAmt_Payment,"0","",SalesPayTtl_Payment,
//                        GrossSales,0.0,0.0,0.0,Double.valueOf(SalesBalTtl_Payment),
//                        Double.valueOf(SalesTaxVal_Payment),"", SalesRounding_Payment);
            }
//
//            //Query.SaveBillPayment(SalesNo_Payment,"1","Cash",Double.valueOf(SalesBalTtl_Payment),"",SalesChangeAmt_Payment);
//            Query.SaveBillPayment(SalesNo_Payment,"1","VISA",Double.valueOf(SalesBalTtl_Payment),"",SalesChangeAmt_Payment);

            //Save SalesDelivery
            if (obj.has("SALESDELIVERY")) {
                String Payment_RetailSalesDelivery = obj.getString("SALESDELIVERY");
                JSONArray JSONArr_PRSDelivery = new JSONArray(Payment_RetailSalesDelivery);


                //Save SalesDeliveryItem
                String Payment_RetailSalesItemDelivery = obj.getString("salesdelivery_item");
                JSONArray JSONArr_PRSIDelivery = new JSONArray(Payment_RetailSalesItemDelivery);


                saveSaleDelivery(JSONArr_PRSDelivery,JSONArr_PRSIDelivery);
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void saveSaleDelivery(JSONArray JSONArr_PRSDelivery, JSONArray JSONArr_PRSIDelivery) {
        for (int i = 0; i < JSONArr_PRSDelivery.length(); i++) {
            JSONObject JSONObj_PRSDelivery = null;
            try {
                JSONObj_PRSDelivery = JSONArr_PRSDelivery.getJSONObject(i);
                String PSD_SalesDelivery_ID = JSONObj_PRSDelivery.getString("ID");
                String PSD_Type = JSONObj_PRSDelivery.getString("Type");
                String PSD_CoyCode = JSONObj_PRSDelivery.getString("CoyCode");
                String PSD_CompanyID = JSONObj_PRSDelivery.getString("CompanyID");
                String PSD_CompanyAddr = JSONObj_PRSDelivery.getString("CompanyAddr");
                String PSD_CompanyTel = JSONObj_PRSDelivery.getString("CompanyTel");
                String PSD_CompanyFax = JSONObj_PRSDelivery.getString("CompanyFax");
                String PSD_RecipientName = JSONObj_PRSDelivery.getString("RecipientName");
                String PSD_RecipientAddr = JSONObj_PRSDelivery.getString("RecipientAddr");
                String PSD_RecipientPostCode = JSONObj_PRSDelivery.getString("RecipientPostCode");
                String PSD_RecipientAttn = JSONObj_PRSDelivery.getString("RecipientAttn");
                String PSD_RecipientTel = JSONObj_PRSDelivery.getString("RecipientTel");
                String PSD_RecipientFax = JSONObj_PRSDelivery.getString("RecipientFax");
                String PSD_OrderStatus = JSONObj_PRSDelivery.getString("OrderStatus");
                String PSD_IDRef = JSONObj_PRSDelivery.getString("IDRef");
                String PSD_TrackingNumber = JSONObj_PRSDelivery.getString("TrackingNumber");
                String PSD_Date = JSONObj_PRSDelivery.getString("Date");
                String PSD_SN_Ref = JSONObj_PRSDelivery.getString("SN_Ref");
                String PSD_INVID = JSONObj_PRSDelivery.getString("INVID");
                String PSD_INVRef = JSONObj_PRSDelivery.getString("INVRef");
                String PSD_INVDate = JSONObj_PRSDelivery.getString("INVDate");
                String PSD_GstRate = JSONObj_PRSDelivery.getString("GstRate");
                String PSD_BalSubTotal = JSONObj_PRSDelivery.getString("BalSubTotal");
                String PSD_BalTax = JSONObj_PRSDelivery.getString("BalTax");
                String PSD_TotalDiscount = JSONObj_PRSDelivery.getString("TotalDiscount");
                String PSD_BalTotal = JSONObj_PRSDelivery.getString("BalTotal");
                String PSD_BalPayable = JSONObj_PRSDelivery.getString("BalPayable");
                String PSD_OutStandingBal = JSONObj_PRSDelivery.getString("OutStandingBal");
                String PSD_LocalBalSubTotal = JSONObj_PRSDelivery.getString("LocalBalSubTotal");
                String PSD_LocalTax = JSONObj_PRSDelivery.getString("LocalTax");
                String PSD_LocalTotalDiscount = JSONObj_PRSDelivery.getString("LocalTotalDiscount");
                String PSD_LocalTotal = JSONObj_PRSDelivery.getString("LocalTotal");
                String PSD_LocalBalPayable = JSONObj_PRSDelivery.getString("LocalBalPayable");
                String PSD_LocalOutStandingBal = JSONObj_PRSDelivery.getString("LocalOutStandingBal");
                String PSD_DepositAmount = JSONObj_PRSDelivery.getString("DepositAmount");
                Query.SaveSalesDelivery(new SalesDelivery(PSD_SalesDelivery_ID,PSD_Type,PSD_CoyCode,PSD_CompanyID,PSD_CompanyAddr,
                        PSD_CompanyTel,PSD_CompanyFax,PSD_RecipientName,PSD_RecipientAddr,PSD_RecipientPostCode,PSD_RecipientAttn,
                        PSD_RecipientTel,PSD_RecipientFax,PSD_OrderStatus,PSD_IDRef,PSD_TrackingNumber,PSD_Date,PSD_SN_Ref,PSD_INVID,PSD_INVRef,PSD_INVDate,
                        PSD_GstRate,PSD_BalSubTotal,PSD_BalTax,PSD_TotalDiscount,PSD_BalTotal,PSD_BalPayable,PSD_OutStandingBal,PSD_LocalBalSubTotal,
                        PSD_LocalTax,PSD_LocalTotalDiscount,PSD_LocalTotal,PSD_LocalBalPayable,PSD_LocalOutStandingBal,PSD_DepositAmount));

                String SalesDeliveryID_Sales = Query.SearchSalesDelivery(PSD_SalesDelivery_ID);
                DBFunc.ExecQuery("UPDATE Sales SET SalesDeliveryID = '"+SalesDeliveryID_Sales+"' WHERE BillNo = '"+SalesNo_Payment+"'",false);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < JSONArr_PRSIDelivery.length(); i++) {
            JSONObject JSONObj_PRSIDelivery = null;
            try {
                JSONObj_PRSIDelivery = JSONArr_PRSIDelivery.getJSONObject(i);
                String PSD_SalesDeliveryItem_ID = JSONObj_PRSIDelivery.getString("ID");
                String PSD_KeyCol = JSONObj_PRSIDelivery.getString("KeyCol");
                String PSD_ItemID = JSONObj_PRSIDelivery.getString("ItemID");
                String PSD_SupBarItem = JSONObj_PRSIDelivery.getString("SupBarItem");
                String PSD_SupBarItemID = JSONObj_PRSIDelivery.getString("SupBarItemID");
                String PSD_ItemRemark = JSONObj_PRSIDelivery.getString("ItemRemark");
                String PSD_PriceLevel = JSONObj_PRSIDelivery.getString("PriceLevel");
                String PSD_ActualQty = JSONObj_PRSIDelivery.getString("ActualQty");
                String PSD_ActualSOQty = JSONObj_PRSIDelivery.getString("ActualSOQty");
                String PSD_Currency = JSONObj_PRSIDelivery.getString("Currency");
                String PSD_ExchRate = JSONObj_PRSIDelivery.getString("ExchRate");
                String PSD_GST = JSONObj_PRSIDelivery.getString("GST");
                String PSD_GSTType = JSONObj_PRSIDelivery.getString("GSTType");
                String PSD_GSTRate = JSONObj_PRSIDelivery.getString("GSTRate");
                String PSD_ItemPrice = JSONObj_PRSIDelivery.getString("ItemPrice");
                String PSD_ItemCost = JSONObj_PRSIDelivery.getString("ItemCost");
                String PSD_ItemDiscAmt = JSONObj_PRSIDelivery.getString("ItemDiscAmt");
                String PSD_ItemUnit = JSONObj_PRSIDelivery.getString("ItemUnit");
                String PSD_ItemUnitID = JSONObj_PRSIDelivery.getString("ItemUnitID");
                String PSD_ItemBaseUnit = JSONObj_PRSIDelivery.getString("ItemBaseUnit");
                String PSD_ItemBaseUnitID = JSONObj_PRSIDelivery.getString("ItemBaseUnitID");
                String PSD_ItemSubTotal = JSONObj_PRSIDelivery.getString("ItemSubTotal");
                String PSD_ItemGST = JSONObj_PRSIDelivery.getString("ItemGST");
                String PSD_TotalDisc = JSONObj_PRSIDelivery.getString("TotalDisc");
                String PSD_TTotal = JSONObj_PRSIDelivery.getString("Total");
                String PSD_LocalItemPrice = JSONObj_PRSIDelivery.getString("LocalItemPrice");
                String PSD_LocalItemDiscAmt = JSONObj_PRSIDelivery.getString("LocalItemDiscAmt");
                String PSD_LocalTotalDisc = JSONObj_PRSIDelivery.getString("LocalTotalDisc");
                String PSD_LocalItemSubTotal = JSONObj_PRSIDelivery.getString("LocalItemSubTotal");
                String PSD_LocalItemGST = JSONObj_PRSIDelivery.getString("LocalItemGST");
                String PSD_LocalTotal = JSONObj_PRSIDelivery.getString("LocalTotal");
                String PSD_ItemFOC = JSONObj_PRSIDelivery.getString("ItemFOC");
                String PSD_ExpireDate = JSONObj_PRSIDelivery.getString("ExpireDate");
                Query.SaveSalesDeliveryItem(new SalesDeliveryItem(PSD_SalesDeliveryItem_ID,PSD_KeyCol,PSD_ItemID,PSD_SupBarItem,PSD_SupBarItemID,
                        PSD_ItemRemark,PSD_PriceLevel,PSD_ActualQty,PSD_ActualSOQty,PSD_Currency,
                        PSD_ExchRate, PSD_GST,PSD_GSTType,PSD_GSTRate,PSD_ItemPrice,
                        PSD_ItemCost,PSD_ItemDiscAmt,PSD_ItemUnit,PSD_ItemUnitID,PSD_ItemBaseUnit,
                        PSD_ItemBaseUnitID, PSD_ItemSubTotal,PSD_ItemGST,PSD_TotalDisc,PSD_TTotal,
                        PSD_LocalItemPrice,PSD_LocalItemDiscAmt,PSD_LocalTotalDisc,PSD_LocalItemSubTotal, PSD_LocalItemGST,
                        PSD_LocalTotal,PSD_ItemFOC,PSD_ExpireDate));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void SaveOnlinePaymentBill(JSONArray JSONArr_PRSD) {

        for (int i = 0; i < JSONArr_PRSD.length(); i++) {
            JSONObject JSONObj_PRSD = null;
            try {
                JSONObj_PRSD = JSONArr_PRSD.getJSONObject(i);

                Payment_JSONObjectInsertIntoVarialble(JSONObj_PRSD);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

//    private void Payment_RetailSalesDetailFun(JSONObject JSONObj_PRSD) {
//
//
//        Payment_JSONObjectInsertIntoVarialble(JSONObj_PRSD);
//
//        Query.SaveDetailsBillProduct(billNo,onlineOrderBill,productQty,productName,productPrice,bill_details_id,itemDiscount,productID,
//                c_cate_catID,c_cate_catName,vchQueueNo_Payment,intTableNo_Payment,str_Cancel,billId,taxID,taxName,taxItemAmount,slddisID,
//                slddisName,slddisType,slddisTypeName,DiscountValue,after_dis_amt,openPriceStatus,beforeOpenPrice);
//
//
////        Payment_JSONObjectInsertIntoVarialble(JSONObj_PRSD);
////
////        if (ItemQty.equals("0")) {
////            Query.SaveModifier(ItemName, ItemPrice);
////        } else {
////            SaveIntoPLU_OnlineOrderPLU();
////        }
////        SaveToRecyclerViewParameters();
////
////        if (RecyclerViewAdapter.sldIDArr.size() > 0) {
////            Integer totalItems = RecyclerViewAdapter.totalItems;
////            ArrayList<String> sldIDArr = RecyclerViewAdapter.sldIDArr;
////            ArrayList<Integer> sldQtyArr = RecyclerViewAdapter.sldQtyArr;
////            ArrayList<String> sldNameArr = RecyclerViewAdapter.sldNameArr;
////            ArrayList<String> sltPriceTotalArr = RecyclerViewAdapter.sltPriceTotalArr;
////            ArrayList<String> sltBillDisArr = RecyclerViewAdapter.sltBillDisArr;
////            ArrayList<String> sltCategoryIDArr = RecyclerViewAdapter.sltCategoryIDArr;
////            ArrayList<String> sltCategoryNameArr = RecyclerViewAdapter.sltCategoryNameArr;
////            Integer countSelectedArr = RecyclerViewAdapter.countSelectedArr;
////            //String BillNo = String.valueOf(MainActivity.BillID);
////            String BillNo = TransNo;
////            Integer qty = total_ItemQty;
////            String dateFormat3 = CheckOutActivity.dateFormat55.format(new Date()).toString();
////            String paymenttype = "Cash";
////            String status = "SALES";
////            String Itemstatus = "SALES";
//////                            Double sub_total = 0.0;
//////                            Double amount = 0.0;
////            Double sub_total = total_price;
////            Double amount = total_price;
////            ArrayList<Integer> Modifier_ID = ButtonAdapter.ID;
////
////            ArrayList<String> vchQueueNo = RecyclerViewAdapter.vchQueueNo;
////            ArrayList<String> intTableNo = RecyclerViewAdapter.intTableNo;
////
////            String sql = "SELECT ID FROM Product_Modifier WHERE Name = '" + ItemName + "'";
////
////            Cursor c = DBFunc.Query(sql, true);
////            if (c != null) {
////                MID.clear();
////                while (c.moveToNext()) {
////                    MID.add(c.getInt(0));
////                }
////                c.close();
////            }
////            Cursor Cursor_DetailsBill = DBFunc.Query("SELECT ID FROM Details_BillProduct WHERE BillNo = '" + BillNo + "'", false);
////            if (Cursor_DetailsBill != null) {
////                if (Cursor_DetailsBill.getCount() == 0) {
////                    Integer billIDDD = Query.getBillID(BillNo);
////
////                    CheckOutActivity.SaveBill(billIDDD, BillNo, sldNameArr, sldQtyArr, sltPriceTotalArr, sltBillDisArr, sltCategoryNameArr,
////                            qty, totalItems, sldIDArr, sltCategoryIDArr, countSelectedArr, dateFormat3, status,
////                            sub_total, amount, paymenttype, MID, vchQueueNo, intTableNo, Itemstatus, RecyclerViewAdapter.slddisID,
////                            RecyclerViewAdapter.slddisName, RecyclerViewAdapter.slddisTypeName, RecyclerViewAdapter.slddisType, RecyclerViewAdapter.slddisValue);
////
////                }
////                Cursor_DetailsBill.close();
////            }
////
////        }
//    }

    private void InsertValueIntoVariable(JSONObject retail_sales_arr_Values) {
        try {
            SalesID_Payment = retail_sales_arr_Values.getString("SalesID");
            RetailID_Payment = retail_sales_arr_Values.getString("RetailID");
            DOID_Payment = retail_sales_arr_Values.getString("DOID");
            Bill_ID_Payment = retail_sales_arr_Values.getString("BillID");
            vchQueueNo_Payment = retail_sales_arr_Values.getString("vchQueueNo");
            intTableNo_Payment = retail_sales_arr_Values.getString("TableNumber");
            ReceiptOrderStatus_Payment = retail_sales_arr_Values.getString("ReceiptOrderStatus");
            SalesNo_Payment = retail_sales_arr_Values.getString("SalesNo");
            SalesTax_Payment = retail_sales_arr_Values.getString("SalesTax");
            SalesDate_Payment = retail_sales_arr_Values.getString("SalesDate");
            CloseRetailID_Payment = retail_sales_arr_Values.getString("CloseRetailID");
            CloseDate_Payment = retail_sales_arr_Values.getString("CloseDate");
            CloseTime_Payment = retail_sales_arr_Values.getString("CloseTime");
            CloseSalesID_Payment = retail_sales_arr_Values.getString("CloseSalesID");

            SalesSubTtl_Payment = retail_sales_arr_Values.getString("SalesSubTtl");
            SalesBalTtl_Payment = retail_sales_arr_Values.getString("SalesBalTtl");
            SalesChangeAmt_Payment = retail_sales_arr_Values.getString("SalesChangeAmt");
            SalesPayTtl_Payment = retail_sales_arr_Values.getString("SalesPayTtl");
            SalesRounding_Payment = retail_sales_arr_Values.getString("SalesRounding");
            SalesTaxVal_Payment = retail_sales_arr_Values.getString("SalesTaxVal");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void OnlineHoldBillFun(JSONObject obj) {
        String RETAIL_SALES_HOLDHDR = null;
        try {
            RETAIL_SALES_HOLDHDR = obj.getString("RETAIL_SALES_HOLDHDR");
            JSONArray mJsonArray_HOLDHDR = new JSONArray(RETAIL_SALES_HOLDHDR);
            String RETAIL_SALES_HOLDDTL = obj.getString("RETAIL_SALES_HOLDDTL");
            JSONArray mJsonArray_HOLDDTL = new JSONArray(RETAIL_SALES_HOLDDTL);
            Log.i("_mJsonArray_HOLDHDR__", String.valueOf(mJsonArray_HOLDHDR));
            Log.i("_mJsonArray_HOLDHDR__", String.valueOf(mJsonArray_HOLDHDR.length()));
            for (int j = 0; j < mJsonArray_HOLDHDR.length(); j++) {
                JSONObject retail_sales_arr_Values = mJsonArray_HOLDHDR.getJSONObject(j);

                 JSONObjectInsertIntoVarialble_Hold(retail_sales_arr_Values);
                 //Hold Bill

                 CreateNewBillForOnlineBill(TransID_Hold,TransNo_Hold,vchQueueNo_Hold,intTableNo_Hold,ReceiptOrderStatus_Hold);
//                SaveOnlineHoldBill(mJsonArray_HOLDDTL);
                SoundAlert(getContext(),"Order Arrived","Receipt No : "+TransNo_Hold);
            }
            SaveOnlineHoldBill(mJsonArray_HOLDDTL);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void CreateNewBillForOnlineBill(String transID,String transNo, String vchQueueNo, String intTableNo, String receiptOrderStatus) {
//        Cursor c = DBFunc.Query("SELECT BillNo FROM Bill WHERE TransNo = '"+transNo+"'",false);
//        if (c != null) {
//            if (c.getCount() == 0) {
//                Log.i("dfddddf__","AAddA"+vchQueueNo+"______"+intTableNo+"______"+receiptOrderStatus+"______"+transID+"______"+transNo);
//
//                Query.SaveBill(vchQueueNo,intTableNo,receiptOrderStatus,transID,transNo,"ON");
//            }
//            c.close();
//        }
        Cursor c = DBFunc.Query("SELECT BillNo FROM Bill WHERE TransNo = '"+transNo+"'",false);
        if (c != null) {
            if (c.getCount() == 0) {
                String sql = "INSERT INTO Bill (OpenDateTime, Cashier, CashierID, BalNo, " +
                        "VchQueueNo,IntTableNo,ReceiptOrderStatus,TransID, " +
                        "OnlineOrderBill,TransNo) VALUES " +
                        "(" + System.currentTimeMillis() + ",'" + Allocator.cashierName+ "', " +
                        Allocator.cashierID + ", '0' , " +
                        "'"+ vchQueueNo +"', '"+intTableNo+"','"+receiptOrderStatus+"','"+transID+"',"+
                        "'"+"ON"+"','" + transNo + "')";

                DBFunc.ExecQuery(sql, false);
            }
            c.close();
        }
        Query.updateBillNo("ON");

        Query.SaveBillList("ON");
    }

    private void JSONObjectInsertIntoVarialble_Hold(JSONObject retail_sales_arr_values) {
        try {
            TransID_Hold = retail_sales_arr_values.getString("TransID");
            TransNo_Hold = retail_sales_arr_values.getString("TransNo");
            TotalDue_Hold = retail_sales_arr_values.getString("TotalDue");
            ReceiptOrderStatus_Hold = retail_sales_arr_values.getString("ReceiptOrderStatus");//DINEIN//takeaway
            vchQueueNo_Hold = retail_sales_arr_values.getString("vchQueueNo");
            intTableNo_Hold = retail_sales_arr_values.getString("intTableNo");
        } catch (JSONException e) {
            e.printStackTrace();
        }

//        >{ "RETAIL_SALES_HOLDHDR": [ { "TransID": "22e32ce7-e5fb-11ea-8bc6-00155d01ca02",
//                "CompanyID": "TASTY", "RetailerID": 2, "TransNo": "ABC129_071527533",
//                "TotalDue": 25.70, "TotalGST": 0.00, "TotalDisc": 0.00, "TransDate":
//            "2020-08-24T00:00:00", "CreateTime": "2020-08-24T07:15:27", "TotalQty": 6.0, "CashierID": "",
//                    "MemberID": "", "isNewCust": "Y", "SalesPersonID": "", "CommID": "",
//                    "CommPerc": 0.00, "ReceiptOrderStatus": "TAKEAWAY", "vchQueueNo": "0214",
//                    "intTableNo": 0, "MacAddress": "", "TerminalID": 1, "PendingSync": "N", "SPBI01": 0,
//                    "SPBI02": 0, "SPBI03": 0, "SPBI04": 0, "SPBI05": 0, "SPD01": 0.000000, "SPD02": 0.000000,
//                    "SPD03": 0.000000, "SPD04": 0.000000, "SPD05": 0.000000, "SPI01": 0, "SPI02": 0, "SPI03": 0,
//                    "SPI04": 0, "SPI05": 0, "SPC01": "N", "SPC02": "N", "SPC03": "N", "SPC04": "N", "SPC05": "N",
//                    "SPV01": "", "SPV02": "", "SPV03": "", "SPV04": "", "SPV05": "", "SPT01": null, "SPT02": null,
//                    "SPT03": null, "SPT04": null, "SPT05": null, "SDT01": null, "SDT02": null, "SDT03": null,
//                    "SDT04": null, "SDT05": null, "LastUser": "online", "LastUpdate": "2020-08-24T07:15:27", "LockUser": "N",
//                    "LockUpdate": "2020-08-24T19:15:27", "LockStatus": "0", "RecordStatus": "READY", "RecordUpdate":
//            "2020-08-24T07:15:27", "QueueStatus": "READY" } ],
    }

    private void SaveOnlineHoldBill(JSONArray mJsonArray_HOLDDTL) {
        for (int i = 0; i < mJsonArray_HOLDDTL.length(); i++) {
            JSONObject retail_sales_arr_Valuesh = null;
            try {
                retail_sales_arr_Valuesh = mJsonArray_HOLDDTL.getJSONObject(i);

                //JSONObjectInsertIntoVarialble(retail_sales_arr_Valuesh);
                JSONObjectInsertIntoVarialble_ItemHold(retail_sales_arr_Valuesh);

                //Hold Validate PLU or Modifier and then save
                if (Integer.parseInt(ItemQty_Item_Hold) == 0) { //Modifier Save

                    Query.SaveModifier(ItemName_Item_Hold, ItemPrice_Item_Hold); //SaveModifier

                    modVal.add(ItemName_Item_Hold);

                    modParentItemVal.add(ParentItemID_ADDON_Item_Hold);

                    SavePLUModi_HoldBill();

                } else {
                    PLU_HoldBill(IttemID_Item_Hold,ItemName_Item_Hold,ItemPrice_Item_Hold,vchQueueNo); // Save PLU
                }
                //Save into Details Bill Product
                saveDetailsBillProduct_HoldBill(TransNo_Hold,IttemID_Item_Hold,ItemQty_Item_Hold,ItemPrice_Item_Hold,ItemName_Item_Hold,vchQueueNo_Hold,intTableNo_Hold,TotalDue_Hold); // Save Details Bill
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    private void SavePLUModi_HoldBill() {
        String sqlPLUModi = "Select ID from PLUModi Where ItemID = '" + ParentItemID_ADDON_Item_Hold + "'" +
                " AND BillNo = '" + TransNo_Hold + "'";
        Cursor CursorPLUModi = DBFunc.Query(sqlPLUModi, false);
        if (CursorPLUModi != null) {
            if (CursorPLUModi.getCount() == 0) {
//              String sql = "INSERT INTO PLUModi (ItemID,ItemName,ModiName,BillNo,BillID) VALUES (";
//                sql += "'" + ParentItemID_ADDON_Item_Hold + "', ";
//                sql += "'0',";
//                sql += "'" + ItemName_Item_Hold + "',";
//                sql += "'" + TransNo_Hold + "',";
//                sql += "'0')";
//                Log.i("Sf_UpdateBillPLU", sql);
//                DBFunc.ExecQuery(sql, false);
                String uniqueId  = UUID.randomUUID().toString();
                String billid = Query.findBillIDByBillNo(TransNo_Hold);
                try {
                    Query.SavePLUModi(uniqueId, Integer.parseInt(ParentItemID_ADDON_Item_Hold), "", ItemName_Item_Hold, "", 0,
                            "","", TransNo_Hold, billid,"-2");
                }catch (Exception e){

                }
            }
            CursorPLUModi.close();
        }
    }

    private void JSONObjectInsertIntoVarialbleHold(JSONObject retail_sales_arr_Values) {
        try {
            TransID = retail_sales_arr_Values.getString("TransID");
            CompanyID = retail_sales_arr_Values.getString("CompanyID");
            RetailerID = retail_sales_arr_Values.getString("RetailerID");
            TransNo = retail_sales_arr_Values.getString("TransNo");
            TotalDue = retail_sales_arr_Values.getString("TotalDue");
            TotalGST = retail_sales_arr_Values.getString("TotalGST");
            TotalDisc = retail_sales_arr_Values.getString("TotalDisc");
            TransDate = retail_sales_arr_Values.getString("TransDate");
            CreateTime = retail_sales_arr_Values.getString("CreateTime");
            TotalQty = retail_sales_arr_Values.getString("TotalQty");
            CashierID = retail_sales_arr_Values.getString("CashierID");
            MemberID = retail_sales_arr_Values.getString("MemberID");
            isNewCust = retail_sales_arr_Values.getString("isNewCust");
            SalesPersonID = retail_sales_arr_Values.getString("SalesPersonID");
            CommID = retail_sales_arr_Values.getString("CommPerc");
            CommPerc = retail_sales_arr_Values.getString("CommPerc");
            ReceiptOrderStatus = retail_sales_arr_Values.getString("ReceiptOrderStatus");
            vchQueueNo = retail_sales_arr_Values.getString("vchQueueNo");
            intTableNo = retail_sales_arr_Values.getString("intTableNo");
            MacAddress = retail_sales_arr_Values.getString("MacAddress");
            TerminalID = retail_sales_arr_Values.getString("TerminalID");
            PendingSync = retail_sales_arr_Values.getString("PendingSync");
            SPBI01 = retail_sales_arr_Values.getString("SPBI01");
            SPBI02 = retail_sales_arr_Values.getString("SPBI02");
            SPBI03 = retail_sales_arr_Values.getString("SPBI03");
            SPBI04 = retail_sales_arr_Values.getString("SPBI04");
            SPBI05 = retail_sales_arr_Values.getString("SPBI05");
            SPD01 = retail_sales_arr_Values.getString("SPD01");
            SPD02 = retail_sales_arr_Values.getString("SPD02");
            SPD03 = retail_sales_arr_Values.getString("SPD03");
            SPD04 = retail_sales_arr_Values.getString("SPD04");
            SPD05 = retail_sales_arr_Values.getString("SPD05");
            SPI01 = retail_sales_arr_Values.getString("SPI01");
            SPI02 = retail_sales_arr_Values.getString("SPI02");
            SPI03 = retail_sales_arr_Values.getString("SPI03");
            SPI04 = retail_sales_arr_Values.getString("SPI04");
            SPI05 = retail_sales_arr_Values.getString("SPI05");
            SPC01 = retail_sales_arr_Values.getString("SPC01");
            SPC02 = retail_sales_arr_Values.getString("SPC02");
            SPC03 = retail_sales_arr_Values.getString("SPC03");
            SPC04 = retail_sales_arr_Values.getString("SPC04");
            SPC05 = retail_sales_arr_Values.getString("SPC05");
            SPV01 = retail_sales_arr_Values.getString("SPV01");
            SPV02 = retail_sales_arr_Values.getString("SPV02");
            SPV03 = retail_sales_arr_Values.getString("SPV03");
            SPV04 = retail_sales_arr_Values.getString("SPV04");
            SPV05 = retail_sales_arr_Values.getString("SPV05");
            SPT01 = retail_sales_arr_Values.getString("SPT01");
            SPT02 = retail_sales_arr_Values.getString("SPT02");
            SPT03 = retail_sales_arr_Values.getString("SPT03");
            SPT04 = retail_sales_arr_Values.getString("SPT04");
            SPT05 = retail_sales_arr_Values.getString("SPT05");
            SDT01 = retail_sales_arr_Values.getString("SDT01");
            LastUser = retail_sales_arr_Values.getString("LastUser");
            LastUpdate = retail_sales_arr_Values.getString("LastUpdate");
            LockUser = retail_sales_arr_Values.getString("LockUser");
            LockUpdate = retail_sales_arr_Values.getString("LockUpdate");
            LockStatus = retail_sales_arr_Values.getString("LockStatus");
            RecordStatus = retail_sales_arr_Values.getString("RecordStatus");
            RecordUpdate = retail_sales_arr_Values.getString("RecordUpdate");
            QueueStatus = retail_sales_arr_Values.getString("QueueStatus");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

//    private void JSONObjectInsertIntoVarialble_ItemPayment(JSONObject retail_sales_arr_Valuesh) {
//        try {
//            IttemID_Item_Payment = retail_sales_arr_Valuesh.getString("ItemID");
//            //ItemName_Item_Payment = retail_sales_arr_Valuesh.getString("itemname");
//            ItemName_Item_Payment = retail_sales_arr_Valuesh.getString("ItemName");
//            //ItemPrice_Item_Payment = retail_sales_arr_Valuesh.getString("ItemPrice");
//            ItemPrice_Item_Payment = retail_sales_arr_Valuesh.getString("ItemUnitPrice");
//
//            Log.i("TAGGG__","IttemID_Item_Payment__"+IttemID_Item_Payment);
//            Log.i("TAGGG__","IItemPrice_Item_Payment__"+ItemPrice_Item_Payment);
//            Log.i("TAGGG__","IIvchQueueNo_Payment__"+vchQueueNo_Payment);
//            PLU_HoldBill(IttemID_Item_Payment,ItemName_Item_Payment,ItemPrice_Item_Payment,vchQueueNo_Payment);// Save PLU
//
//            //Save into Details Bill Product
//            // saveDetailsBillProduct_HoldBill(); // Save Details Bill
//            Log.i("TAGGG__","IttemID_Item_Payment__"+IttemID_Item_Payment);
//            Log.i("TAGGG__","ItemQty_Item_Detail_Payment__"+ItemQty_Item_Detail_Payment);
//            Log.i("TAGGG__","ItemPrice_Item_Payment__"+ItemPrice_Item_Payment);
//            Log.i("TAGGG__","IItemName_Item_Payment__"+ItemName_Item_Payment);
//            Log.i("TAGGG__","vchQueueNo_Payment__"+vchQueueNo_Payment);
//            Log.i("TAGGG__","intTableNo_Payment__"+intTableNo_Payment);
//            Log.i("TAGGG__","SalesBalTtl_Payment__"+SalesBalTtl_Payment);
//            saveDetailsBillProduct_HoldBill(SalesNo_Payment,IttemID_Item_Payment,ItemQty_Item_Detail_Payment,ItemPrice_Item_Payment,
//                    ItemName_Item_Payment,vchQueueNo_Payment,intTableNo_Payment,SalesBalTtl_Payment); // Save Details Bill
//
//
////            TransID_Item_Hold = retail_sales_arr_Valuesh.getString("TransID");
////            RecordNo_Item_Hold = retail_sales_arr_Valuesh.getString("RecordNo");
////            LineNo_Item_Hold = retail_sales_arr_Valuesh.getString("LineNo");
////            ItemQty_Item_Hold = retail_sales_arr_Valuesh.getString("ItemQty");
////            ItemTotal_Item_Hold = retail_sales_arr_Valuesh.getString("ItemTotal");
////            ItemGST_Item_Hold = retail_sales_arr_Valuesh.getString("ItemGST");
////            ItemDiscType_Item_Hold = retail_sales_arr_Valuesh.getString("ItemDiscType");
////            ItemDisc1_Item_Hold = retail_sales_arr_Valuesh.getString("ItemDisc1");
////            ItemDisc2_Item_Hold = retail_sales_arr_Valuesh.getString("ItemDisc2");
////            ItemDisc3_Item_Hold = retail_sales_arr_Valuesh.getString("ItemDisc3");
////
////
////            //ItemName = retail_sales_arr_Valuesh.getString("ItemName");
////            ItemBarcode_Item_Hold = retail_sales_arr_Valuesh.getString("ItemBarcode");
////            ItemUOM_Item_Hold = retail_sales_arr_Valuesh.getString("ItemUOM");
////            ItemGSTInEx_Item_Hold = retail_sales_arr_Valuesh.getString("ItemGSTInEx");
////            ItemCost_Item_Hold = retail_sales_arr_Valuesh.getString("ItemCost");
////            ItemActQty_Item_Hold = retail_sales_arr_Valuesh.getString("ItemActQty");
////            ItemUOMID_Item_Hold = retail_sales_arr_Valuesh.getString("ItemUOMID");
////            ItemGroupDisc_Item_Hold = retail_sales_arr_Valuesh.getString("ItemGroupDisc");
////            ItemSKU_Item_Hold = retail_sales_arr_Valuesh.getString("ItemSKU");
////            SupplierID_Item_Hold = retail_sales_arr_Valuesh.getString("SupplierID");
////            SalesPersonID_Item_Hold = retail_sales_arr_Valuesh.getString("SalesPersonID");
////            SalesCommTypeID_Item_Hold = retail_sales_arr_Valuesh.getString("SalesCommTypeID");
////            SalesCommPerc_Item_Hold = retail_sales_arr_Valuesh.getString("SalesCommPerc");
////            ItemCommPerc_Item_Hold = retail_sales_arr_Valuesh.getString("ItemCommPerc");
////            ItemCommAmt_Item_Hold = retail_sales_arr_Valuesh.getString("ItemCommAmt");
////            ItemSerialNo_Item_Hold = retail_sales_arr_Valuesh.getString("ItemSerialNo");
////            DISCID_Item_Hold = retail_sales_arr_Valuesh.getString("DISCID");
////            ItemIMEINo_Item_Hold = retail_sales_arr_Valuesh.getString("ItemIMEINo");
////            ItemBatchNo_Item_Hold = retail_sales_arr_Valuesh.getString("ItemBatchNo");
////            ItemStatus_Item_Hold = retail_sales_arr_Valuesh.getString("ItemStatus");
////            OpenPriceRemark_Item_Hold = retail_sales_arr_Valuesh.getString("OpenPriceRemark");
////            ItemRemark_Item_Hold = retail_sales_arr_Valuesh.getString("ItemRemark");
////            ExpireDate_Item_Hold = retail_sales_arr_Valuesh.getString("ExpireDate");
////            ExpiryDay_Item_Hold = retail_sales_arr_Valuesh.getString("ExpiryDay");
////            RedeemPoint_Item_Hold = retail_sales_arr_Valuesh.getString("RedeemPoint");
////            ParentItemID_ADDON_Item_Hold = retail_sales_arr_Valuesh.getString("ParentItemID_ADDON");
////            bitAddOnItem_Item_Hold = retail_sales_arr_Valuesh.getString("bitAddOnItem");
////            ParentDetailID_ADDON_Item_Hold = retail_sales_arr_Valuesh.getString("ParentDetailID_ADDON");
////            MemDOBDiscPerc_Item_Hold = retail_sales_arr_Valuesh.getString("MemDOBDiscPerc");
////            MemDOBDiscAmount_Item_Hold = retail_sales_arr_Valuesh.getString("MemDOBDiscAmount");
////            ReceiptOrderStatus_Item_Hold = retail_sales_arr_Valuesh.getString("ReceiptOrderStatus");
////            TerminalID_Item_Hold = retail_sales_arr_Valuesh.getString("TerminalID");
////            RFID_Item_Hold = retail_sales_arr_Valuesh.getString("RFID");
////            PendingSync_Item_Hold = retail_sales_arr_Valuesh.getString("PendingSync");
////            SPBI01_Item_Hold= retail_sales_arr_Valuesh.getString("SPBI01");
////            SPBI02_Item_Hold = retail_sales_arr_Valuesh.getString("SPBI02");
////            SPBI03_Item_Hold = retail_sales_arr_Valuesh.getString("SPBI03");
////            SPBI04_Item_Hold = retail_sales_arr_Valuesh.getString("SPBI04");
////            SPBI05_Item_Hold = retail_sales_arr_Valuesh.getString("SPBI05");
////            SPD01_Item_Hold = retail_sales_arr_Valuesh.getString("SPD01");
////            SPD02_Item_Hold = retail_sales_arr_Valuesh.getString("SPD02");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//
//            Log.i("TAGGG___","ERRRR___"+e.getMessage());
//        }
//    }
    private void JSONObjectInsertIntoVarialble_ItemHold(JSONObject retail_sales_arr_Valuesh) {
        try {
            TransID_Item_Hold = retail_sales_arr_Valuesh.getString("TransID");
            RecordNo_Item_Hold = retail_sales_arr_Valuesh.getString("RecordNo");
            LineNo_Item_Hold = retail_sales_arr_Valuesh.getString("LineNo");
            ItemQty_Item_Hold = retail_sales_arr_Valuesh.getString("ItemQty");
            ItemPrice_Item_Hold = retail_sales_arr_Valuesh.getString("ItemPrice");
            ItemTotal_Item_Hold = retail_sales_arr_Valuesh.getString("ItemTotal");
            ItemGST_Item_Hold = retail_sales_arr_Valuesh.getString("ItemGST");
            ItemDiscType_Item_Hold = retail_sales_arr_Valuesh.getString("ItemDiscType");
            ItemDisc1_Item_Hold = retail_sales_arr_Valuesh.getString("ItemDisc1");
            ItemDisc2_Item_Hold = retail_sales_arr_Valuesh.getString("ItemDisc2");
            ItemDisc3_Item_Hold = retail_sales_arr_Valuesh.getString("ItemDisc3");
            IttemID_Item_Hold = retail_sales_arr_Valuesh.getString("ItemID");

            //ItemName = retail_sales_arr_Valuesh.getString("ItemName");
            ItemName_Item_Hold = retail_sales_arr_Valuesh.getString("itemname");
            ItemBarcode_Item_Hold = retail_sales_arr_Valuesh.getString("ItemBarcode");
            ItemUOM_Item_Hold = retail_sales_arr_Valuesh.getString("ItemUOM");
            ItemGSTInEx_Item_Hold = retail_sales_arr_Valuesh.getString("ItemGSTInEx");
            ItemCost_Item_Hold = retail_sales_arr_Valuesh.getString("ItemCost");
            ItemActQty_Item_Hold = retail_sales_arr_Valuesh.getString("ItemActQty");
            ItemUOMID_Item_Hold = retail_sales_arr_Valuesh.getString("ItemUOMID");
            ItemGroupDisc_Item_Hold = retail_sales_arr_Valuesh.getString("ItemGroupDisc");
            ItemSKU_Item_Hold = retail_sales_arr_Valuesh.getString("ItemSKU");
            SupplierID_Item_Hold = retail_sales_arr_Valuesh.getString("SupplierID");
            SalesPersonID_Item_Hold = retail_sales_arr_Valuesh.getString("SalesPersonID");
            SalesCommTypeID_Item_Hold = retail_sales_arr_Valuesh.getString("SalesCommTypeID");
            SalesCommPerc_Item_Hold = retail_sales_arr_Valuesh.getString("SalesCommPerc");
            ItemCommPerc_Item_Hold = retail_sales_arr_Valuesh.getString("ItemCommPerc");
            ItemCommAmt_Item_Hold = retail_sales_arr_Valuesh.getString("ItemCommAmt");
            ItemSerialNo_Item_Hold = retail_sales_arr_Valuesh.getString("ItemSerialNo");
            DISCID_Item_Hold = retail_sales_arr_Valuesh.getString("DISCID");
            ItemIMEINo_Item_Hold = retail_sales_arr_Valuesh.getString("ItemIMEINo");
            ItemBatchNo_Item_Hold = retail_sales_arr_Valuesh.getString("ItemBatchNo");
            ItemStatus_Item_Hold = retail_sales_arr_Valuesh.getString("ItemStatus");
            OpenPriceRemark_Item_Hold = retail_sales_arr_Valuesh.getString("OpenPriceRemark");
            ItemRemark_Item_Hold = retail_sales_arr_Valuesh.getString("ItemRemark");
            ExpireDate_Item_Hold = retail_sales_arr_Valuesh.getString("ExpireDate");
            ExpiryDay_Item_Hold = retail_sales_arr_Valuesh.getString("ExpiryDay");
            RedeemPoint_Item_Hold = retail_sales_arr_Valuesh.getString("RedeemPoint");
            ParentItemID_ADDON_Item_Hold = retail_sales_arr_Valuesh.getString("ParentItemID_ADDON");
            bitAddOnItem_Item_Hold = retail_sales_arr_Valuesh.getString("bitAddOnItem");
            ParentDetailID_ADDON_Item_Hold = retail_sales_arr_Valuesh.getString("ParentDetailID_ADDON");
            MemDOBDiscPerc_Item_Hold = retail_sales_arr_Valuesh.getString("MemDOBDiscPerc");
            MemDOBDiscAmount_Item_Hold = retail_sales_arr_Valuesh.getString("MemDOBDiscAmount");
            ReceiptOrderStatus_Item_Hold = retail_sales_arr_Valuesh.getString("ReceiptOrderStatus");
            TerminalID_Item_Hold = retail_sales_arr_Valuesh.getString("TerminalID");
            RFID_Item_Hold = retail_sales_arr_Valuesh.getString("RFID");
            PendingSync_Item_Hold = retail_sales_arr_Valuesh.getString("PendingSync");
            SPBI01_Item_Hold= retail_sales_arr_Valuesh.getString("SPBI01");
            SPBI02_Item_Hold = retail_sales_arr_Valuesh.getString("SPBI02");
            SPBI03_Item_Hold = retail_sales_arr_Valuesh.getString("SPBI03");
            SPBI04_Item_Hold = retail_sales_arr_Valuesh.getString("SPBI04");
            SPBI05_Item_Hold = retail_sales_arr_Valuesh.getString("SPBI05");
            SPD01_Item_Hold = retail_sales_arr_Valuesh.getString("SPD01");
            SPD02_Item_Hold = retail_sales_arr_Valuesh.getString("SPD02");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void Payment_JSONObjectInsertIntoVarialble(JSONObject retail_sales_arr_Values) {
        try {
            Sales_DetailID = retail_sales_arr_Values.getString("Sales_DetailID");
            SalesID = retail_sales_arr_Values.getString("SalesID");
            RetailID = retail_sales_arr_Values.getString("RetailID");
            ItemIDD = retail_sales_arr_Values.getString("ItemID");
            //ItemName = retail_sales_arr_Values.getString("ItemName");
            //ItemName = retail_sales_arr_Values.getString("itemname");
            SupBarCode = retail_sales_arr_Values.getString("SupBarCode");
            total_ItemQty += Integer.parseInt(ItemQty);
            ItemUOM = retail_sales_arr_Values.getString("ItemUOM");
            ItemUOMDesc = retail_sales_arr_Values.getString("ItemUOMDesc");
            ItemQtyAct = retail_sales_arr_Values.getString("ItemQtyAct");
            ItemUnitCost = retail_sales_arr_Values.getString("ItemUnitCost");
            ItemAveCost = retail_sales_arr_Values.getString("ItemAveCost");
            ItemSSPx = retail_sales_arr_Values.getString("ItemSSPx");

            ItemTaxType = retail_sales_arr_Values.getString("ItemTaxType");
            SupplierID = retail_sales_arr_Values.getString("SupplierID");


            ItemName_Item_Detail_Payment = retail_sales_arr_Values.getString("ItemName");
            ItemQty_Item_Detail_Payment = retail_sales_arr_Values.getString("ItemQty");
            ItemUnitPrice_Item_Detail_Payment = retail_sales_arr_Values.getString("ItemUnitPrice");
            ItemTotalDisc_Item_Detail_Payment = retail_sales_arr_Values.getString("ItemTotalDisc");
            ItemTaxTotal_Item_Detail_Payment = retail_sales_arr_Values.getString("ItemTaxTotal");


            IttemID_Item_Payment = retail_sales_arr_Values.getString("ItemID");
//            //ItemName_Item_Payment = retail_sales_arr_Valuesh.getString("itemname");
//            ItemName_Item_Payment = retail_sales_arr_Valuesh.getString("ItemName");
//            //ItemPrice_Item_Payment = retail_sales_arr_Valuesh.getString("ItemPrice");
//            ItemPrice_Item_Payment = retail_sales_arr_Valuesh.getString("ItemUnitPrice");


            if (ItemName_Item_Detail_Payment == null || ItemName_Item_Detail_Payment.equals("null")){
                ItemName_Item_Detail_Payment = "";
            }
            PLU_HoldBill(IttemID_Item_Payment,ItemName_Item_Detail_Payment,ItemUnitPrice_Item_Detail_Payment,vchQueueNo_Payment);// Save PLU


            String  productIDByUUID = Query.SearchProductIDByUUID(IttemID_Item_Payment);

            for (int i = 0 ; i < Integer.parseInt(ItemQty_Item_Detail_Payment) ; i ++) {
                Query.SaveDetailsBillProduct(SalesNo_Payment, "ON", Integer.parseInt(productIDByUUID), ItemName_Item_Detail_Payment,
                        ItemUnitPrice_Item_Detail_Payment,
                          ItemTotalDisc_Item_Detail_Payment, productIDByUUID,
                        "0", "", vchQueueNo_Payment, intTableNo_Payment, "VISA", 0, "0", "",
                        "0","", "", "", "", Double.valueOf(ItemPrice_Item_Payment),
                        "0", "0",0,"");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

//    private void SaveIntoPLU_OnlineOrderPLU() {
//        String sql = "SELECT ID FROM PLU WHERE UUID = '" + IttemID + "'";
//        Log.i("sql__ddd", sql);
//        Cursor c = DBFunc.Query(sql, true);
//        if (c != null) {
//            if (c.getCount() == 0) {
//                Query.SavePLU(ItemName,IttemID,ItemPrice,vchQueueNo,AddNewProductActivity.BitMapToString(bitmap),"0","0","0",
//                        0,"0",0,"0");
//            }
//            c.close();
//        }
//    }

    private void SaveToRecyclerViewParameters(String UUID,String Qty,String itemPrice,String itemname,String vchQueueNo,String intTableNo) {
        String sql = " SELECT ID FROM PLU Where UUID = '" + UUID + "' ";
        Log.i("TAGG__","UUID__"+sql);
        //String sql = "Select TotalItems,totalNettAmount FROM BillDetails Where BillNo = '"+MainActivity.strbillNo+"' ";
        Cursor c = DBFunc.Query(sql, true);
        if (c != null) {
            if (c.moveToNext()) {

                ClearRecyclerViewParameters();

                Integer pluID = c.getInt(0);
                Log.i("TAGG__","pluID__"+pluID);
                Log.i("TAGG__","Qty__"+Qty);
                total_price_Item_Hold = 0.0;
                for (int qty_i = 0; qty_i < Integer.parseInt(Qty); qty_i++) {
                    total_price_Item_Hold += Double.valueOf(itemPrice);
                    RecyclerViewAdapter.totalItems = 1;
                    RecyclerViewAdapter.countSelectedArr = 1;
                    RecyclerViewAdapter.sldNameArr.add(itemname);
                    RecyclerViewAdapter.sltPriceTotalArr.add(itemPrice);
                    RecyclerViewAdapter.sldQtyArr.add(pluID);
                    RecyclerViewAdapter.sltPriceTotalArr.add(String.valueOf(itemPrice));
                    RecyclerViewAdapter.sltBillDisArr.add("0");
                    RecyclerViewAdapter.sldIDArr.add(String.valueOf(pluID));
                    RecyclerViewAdapter.sltCategoryIDArr.add("0");
                    RecyclerViewAdapter.sltCategoryNameArr.add("0");
                    RecyclerViewAdapter.vchQueueNo.add(vchQueueNo);
                    RecyclerViewAdapter.intTableNo.add(intTableNo);
//                    RecyclerViewAdapter.ReceiptOrderStatus.add("0");
                    RecyclerViewAdapter.slddisID.add("0");
                    RecyclerViewAdapter.slddisName.add("0");
                    RecyclerViewAdapter.slddisTypeName.add("0");
                    RecyclerViewAdapter.slddisType.add("0");
                    RecyclerViewAdapter.slddisValue.add("0");
                }
            }
            c.close();
        }
        Log.i("TAGG__","total_price_Item_Hold_"+total_price_Item_Hold);
    }

    private void ClearRecyclerViewParameters() {
        RecyclerViewAdapter.totalItems = 0;
        RecyclerViewAdapter.countSelectedArr = 0;
        RecyclerViewAdapter.sldNameArr.clear();
        RecyclerViewAdapter.sltPriceTotalArr.clear();
        RecyclerViewAdapter.sldQtyArr.clear();
        RecyclerViewAdapter.sltPriceTotalArr.clear();
        RecyclerViewAdapter.sltBillDisArr.clear();
        RecyclerViewAdapter.sldIDArr.clear();
        RecyclerViewAdapter.sltCategoryIDArr.clear();
        RecyclerViewAdapter.sltCategoryNameArr.clear();
        RecyclerViewAdapter.vchQueueNo.clear();
        RecyclerViewAdapter.intTableNo.clear();
//        RecyclerViewAdapter.ReceiptOrderStatus.clear();
        RecyclerViewAdapter.slddisID.clear();
        RecyclerViewAdapter.slddisName.clear();
        RecyclerViewAdapter.slddisTypeName.clear();
        RecyclerViewAdapter.slddisType.clear();
        RecyclerViewAdapter.slddisValue.clear();
    }

    private void PLU_HoldBill(String UUID,String itemName,String itemPrice,String voucherNo) {
//        AddNewProductActivity.BitMapToString(bitmap)
        String sql = "SELECT ID FROM PLU WHERE UUID = '" + UUID + "'";
        Log.i("sql__ddd", sql);
        Cursor c = DBFunc.Query(sql, true);
        if (c != null) {
            if (c.getCount() == 0) {
                Log.i("_TAGG__","itemName__"+itemName+"__"+UUID);
                Query.SavePLU(itemName,UUID,itemPrice,voucherNo,
                        "","","","","","","0","0","0",
                        "0",0,"0",0,"0","0","Other");
                //pluName, uniqueId, String pluPrice,String pluCode, String str_img, String str_checked_name_quick_edit, String str_checked_price_quick_edit, String str_checked_open_price_edit,
                // Integer category_IDD, String category_Namee, Integer taxID, String taxName

            }
            c.close();
        }
    }

    private void saveDetailsBillProduct_HoldBill(String billNo, String itemID, String itemQty, String itemPrice, String itemName, String vchQueueNo, String intTableNo, String totalAmt) {
        //Query.CreateNewBill("ON", TransNo);

        //Here
        SaveToRecyclerViewParameters(itemID,itemQty,itemPrice,itemName,vchQueueNo,intTableNo);

        Log.i("TESTING", "SaveToRecyclerViewParameters_"+String.valueOf(RecyclerViewAdapter.sldIDArr.size()));
        if (RecyclerViewAdapter.sldIDArr.size() > 0) {
            Integer totalItems = RecyclerViewAdapter.totalItems;
            ArrayList<String> sldIDArr = RecyclerViewAdapter.sldIDArr;
            ArrayList<Integer> sldQtyArr = RecyclerViewAdapter.sldQtyArr;
            ArrayList<String> sldNameArr = RecyclerViewAdapter.sldNameArr;
            ArrayList<String> sltPriceTotalArr = RecyclerViewAdapter.sltPriceTotalArr;
            ArrayList<String> sltBillDisArr = RecyclerViewAdapter.sltBillDisArr;
            ArrayList<String> sltCategoryIDArr = RecyclerViewAdapter.sltCategoryIDArr;
            ArrayList<String> sltCategoryNameArr = RecyclerViewAdapter.sltCategoryNameArr;
            Integer countSelectedArr = RecyclerViewAdapter.countSelectedArr;
            //String BillNo = String.valueOf(MainActivity.BillID);
            String BillNo = billNo;
            //Integer qty = Integer.parseInt(ItemQty_Item_Hold);
            //Integer qty =
            String dateFormat3 = CheckOutActivity.dateFormat55.format(new Date()).toString();
            String paymenttype = "Cash";
            String status = Constraints.SALES.toUpperCase();
            String Itemstatus = Constraints.SALES.toUpperCase();
//            Double sub_total = total_price;
//            Double amount = total_price;
            Double sub_total = total_price_Item_Hold;
            Double amount = Double.valueOf(totalAmt);
            ArrayList<Integer> Modifier_ID = ButtonAdapter.ID;

            RecyclerViewAdapter.vchQueueNo.add(vchQueueNo);
            RecyclerViewAdapter.intTableNo.add(intTableNo);
//            RecyclerViewAdapter.ReceiptOrderStatus.add(ReceiptOrderStatus_Hold);

            //ReceiptOrderStatus_Hold = retail_sales_arr_values.getString("ReceiptOrderStatus");//DINEIN//takeaway

            ArrayList<String> QueueNo = RecyclerViewAdapter.vchQueueNo;
            ArrayList<String> TableNo = RecyclerViewAdapter.intTableNo;

            Integer billIDDD = Query.getBillID(BillNo);
            //Here

//            CheckOutActivity.SaveBill(billIDDD, BillNo, sldNameArr, sldQtyArr, sltPriceTotalArr, sltBillDisArr, sltCategoryNameArr,
//                    1, totalItems, sldIDArr, sltCategoryIDArr, countSelectedArr, dateFormat3, status,
//                    sub_total, amount, paymenttype, MID, QueueNo, TableNo, Itemstatus, RecyclerViewAdapter.slddisID,
//                    RecyclerViewAdapter.slddisName, RecyclerViewAdapter.slddisTypeName, RecyclerViewAdapter.slddisType,
//                    RecyclerViewAdapter.slddisValue);
//
//            CheckOutActivity.saveDetailsBillProduct(billIDDD, strbillNo, mData.get(position).getTitle(),
//                    existingQty+1, mData.get(position).getPrice(),
//                    item_diss_amt, mData.get(position).getProductCategoryName(),
//                    existingQty+1, mData.get(position).getProductID(), mData.get(position).getProductCategoryID(),
//                    dateFormat3,   Modifier_ID,
//                    MainActivity.inv_table, MainActivity.tbl_name,
//                    status,Itemstatus, DiscountID, DiscountName, DiscountTypeName,
//                    DiscountType, DiscountValue); //Just

        }

    }

    private void UpdateStatusForOnlineOrder() {
        if (getContext() != null) {
            //RequestQueue queue = Volley.newRequestQueue(getContext());
            final String finalCompany_code = downloadCompanyCode;
            final String finalRetails_ID = downloadRetailID;
            final String finalterminal_ID = terminal_id;
            final String finalsaleno = SalesNo;
            final JSONObject jsonObject = new JSONObject();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, downloadUrl,
                    new Response.Listener<String>() {
                        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                        @Override
                        public void onResponse(String response) {
                            // response code
                            String xmlString = response;
                            Log.i("inv_xmlString", xmlString);
                            Document xmlparse = null;
                            Document parse = APIActivity.XMLParseFunction(xmlString, xmlparse);
                            for (int i = 0; i < parse.getElementsByTagName("UpdateStatusForOnlineOrderResponse").getLength(); i++) {
                                UpdateStatusForOnlineOrderResponse = (parse.getElementsByTagName("UpdateStatusForOnlineOrderResponse").getLength() > 0)
                                        ? parse.getElementsByTagName("UpdateStatusForOnlineOrderResponse").item(i).getTextContent() : " ";
                            }

                            JSONObject obj = null;
                            try {
                                obj = new JSONObject(UpdateStatusForOnlineOrderResponse);
                                //"Status":"Success"
                                String Status = obj.getString("Status");
                                Log.i("Status__", Status);
                                if (Status.equals("Success")) {
                                    Date datetime = new Date(System.currentTimeMillis());
                                    CashLayoutActivity.PrintKP(SalesNo, SalesID, SalesDate);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // As of f605da3 the following should work
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
                            "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                            "  <soap:Body>\n" +
                            "    <UpdateStatusForOnlineOrder xmlns=\"http://tempuri.org/\">\n" +
                            "      <companyCode>" + finalCompany_code + "</companyCode>\n" +
                            "      <retailid>" + finalRetails_ID + "</retailid>\n" +
                            "      <terminalid>" + finalterminal_ID + "</terminalid>\n" +
                            "      <salesno>" + finalsaleno + "</salesno>\n" +
                            "    </UpdateStatusForOnlineOrder>\n" +
                            "  </soap:Body>\n" +
                            "</soap:Envelope>";
                    //Log.i("APP", "request String: " + temp);
                    byte[] b = temp.getBytes(Charset.forName("UTF-8"));

                    return b;
                }

                @Override
                public String getBodyContentType() {
                    return "text/xml;charset=utf-8";
                }
            };
            Log.i("_stringRsssssequest", String.valueOf(stringRequest));
            queue.add(stringRequest);
        }
    }

//    private void getBillAll() {
//        Cursor c = null;
//        Log.i("sdffd", str_newText);
//        if (str_newText.length() > 0) {
//            String sql = "SELECT BillNo,count(ProductQty)," +
//                    "SUM(ProductPrice),BillDateTime,ID,SUM(ItemDiscountAmount),vchQueueNo,intTableNo " +
//                    "FROM Details_BillProduct Where OnlineOrderBill = 'ON' " +
//                    " AND BillNo LIKE '%" + str_newText.toUpperCase() + "%' OR " +
//                    " vchQueueNo LIKE '%" + str_newText.toUpperCase() + "%' OR " +
//                    " intTableNo LIKE '%" + str_newText.toUpperCase() + "%' " +
//                    "group by BillNo order by BillNo DESC";
//            Log.i("_sql_a_", sql);
//
//            c = DBFunc.Query(sql, false);
//        } else {
//            String sql = "SELECT BillNo,count(ProductQty)," +
//                    "SUM(ProductPrice),BillDateTime,ID,SUM(ItemDiscountAmount),vchQueueNo,intTableNo " +
//                    "FROM Details_BillProduct Where OnlineOrderBill = 'ON' " +
//                    "group by BillNo order by BillNo DESC";
////            ,vchQueueNo,intTableNo
//            //billNo,totalItems,totalAmount,dateTime,ID,STATUS,vchQueueNo,intTableNo
//            Log.i("_sql__", sql);
//            c = DBFunc.Query(sql, false);
//        }
//        if (c != null) {
//            lstBillDetails.clear();
//            while (c.moveToNext()) {
//                String status = "HOLD";
//                //Double double_price = Double.valueOf( c.getString(2));
//                Double exact_price = c.getDouble(2) - c.getDouble(5);
//                Log.i("exact_price_", String.valueOf(exact_price));
//                String vchQueueNo = "0";
//                String intTableNo = "0";
//                String sql = "SELECT VchQueueNo,IntTableNo " +
//                        "FROM Bill " +
//                        "where TransNo = '" + c.getString(0) + "'";
//                Log.i("_sql__", sql);
//                Cursor cc = DBFunc.Query(sql, false);
//                if (cc != null) {
//                    if (cc.moveToNext()) {
//                        vchQueueNo = cc.getString(0);
//                        intTableNo = cc.getString(1);
//                    }
//                    cc.close();
//                }
//
//                Integer sale_id = Query.getSalesIDByBillNo(c.getString(0));
//                if (sale_id == 0) {
//                    Log.i("vchQueueNo__","vchQueueNo__"+vchQueueNo);
//                    Log.i("intTableNo__","intTableNo__"+intTableNo);
//                    lstBillDetails.add(new BillDetails(c.getString(0),
//                            c.getInt(1),
//                            String.valueOf(exact_price), c.getString(3), c.getInt(4), status,
//                            vchQueueNo, intTableNo));
//                }
//
//
//            }
//            // billNo,  totalItems,  totalAmount,  dateTime,  ID
//            c.close();
//        }
//    }
    private void getBillAll() {
        if (str_newText.length() > 0){
    //            String queryStrSales = " WHERE Sales.BillNo LIKE '%"+str_newText+"%' ";
    //            String queryStrBillDetails = " AND BillNo LIKE '%"+str_newText+"%'";
    //            BillListSearchAllData(queryStrSales,queryStrBillDetails);

            String queryStrBill = " AND BillNo LIKE '%" + str_newText.toUpperCase() + "%' OR " +
                    " QueueNo LIKE '%" + str_newText.toUpperCase() + "%' OR " +
                    " TableNo LIKE '%" + str_newText.toUpperCase() + "%' OR" +
                    " STATUS = '"+str_newText+"' " ;
            BillListSearchAllData(queryStrBill);
        }else{
            BillListSearchAllData("");
        }
    }
    private void BillListSearchAllData(String queryStrBill) {
//        Cursor Cursor_S = Query.GetSales(queryStrSales);
//        ArrayList<String> billSalesArrList = Query.GetSalesArr(Cursor_S);
//
//        lstBillDetails = Query.GetBillListData(billSalesArrList,queryStrBillDetails);
//        Log.i("dfd__","lstBillDetails_ff_"+lstBillDetails.size());
//        Cursor Cursor_Sales = Query.GetSales(queryStrSales);
//        lstBillDetails = Query.GetBillListDataSales(lstBillDetails,Cursor_Sales);
//        Log.i("dfd__","lstBillDetails_ffff_"+lstBillDetails.size());

        lstBill = Query.getBillList("ON",queryStrBill);
    }
//    private final Runnable m_Runnable = new Runnable()
//    {
//        public void run()
//
//        {
//
//            mHandler.postDelayed(m_Runnable, 1000);
//            // Toast.makeText(getContext(),"in runnable 3",Toast.LENGTH_SHORT).show();
////            Toast.makeText(AddTaxConfigurationActivity.this,"in runnable ddd"+TaxTypeSheetFragment.selected_tax_name ,Toast.LENGTH_SHORT).show();
//            //mHandler = new Handler();
//            // if (status_on.equals("1")) {
//            if (str_newText.length() > 0){
//                if (getActivity()!=null){
//                    //adapter = new RecyclerAdapter(getContext(), lstBillDetails);
//                    adapter = new RecyclerAdapter(getActivity(), lstBillDetails);
//                    recyclerView.setAdapter(adapter);
//                    getBillAll();
////        recyclerView.setAdapter(adapter);
//                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//                    recyclerView.addItemDecoration(new
//                            DividerItemDecoration(getActivity(),
//                            DividerItemDecoration.VERTICAL));
//                }
//                }
//
//        }
//        // }
//
//
//
//    };
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//
//        // Only if you need to restore open/close state when
//        // the orientation is changed
//        if (adapter != null) {
//            adapter.saveStates(outState);
//        }
//    }
//
//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//
//        // Only if you need to restore open/close state when
//        // the orientation is changed
//        if (adapter != null) {
//            adapter.restoreStates(savedInstanceState);
//        }
//    }


    private List<String> createList(int n) {
        List<String> list = new ArrayList<String>();

        for (int i = 0; i < n; i++) {
            list.add("View " + i);
        }

        return list;
    }

    public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
        private ArrayList<BillData> dataList;

        public ListAdapter(ArrayList<BillData> data) {
            this.dataList = data;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            TextView table_name, table_name1, table_name2, table_name3, table_name4;
            TextView occupied_name, occupied_name1, occupied_name2, occupied_name3, occupied_name4;
            TextView pax_name, pax_name1, pax_name2, pax_name3, pax_name4;
            DragLinearLayout dragLinearLayout;

            public ViewHolder(View itemView) {
                super(itemView);
//                this.table_name = (TextView) itemView.findViewById(R.id.table_name);
//                this.occupied_name = (TextView) itemView.findViewById(R.id.occupied_name);
//                this.pax_name = (TextView) itemView.findViewById(R.id.pax_name);
//                this.table_name1 = (TextView) itemView.findViewById(R.id.table_name_1);
//                this.occupied_name1 = (TextView) itemView.findViewById(R.id.occupied_name_1);
//                this.pax_name1 = (TextView) itemView.findViewById(R.id.pax_name_1);
//                this.table_name2 = (TextView) itemView.findViewById(R.id.table_name_2);
//                this.occupied_name2 = (TextView) itemView.findViewById(R.id.occupied_name_2);
//                this.pax_name2 = (TextView) itemView.findViewById(R.id.pax_name_2);
//                this.table_name3 = (TextView) itemView.findViewById(R.id.table_name_3);
//                this.occupied_name3 = (TextView) itemView.findViewById(R.id.occupied_name_3);
//                this.pax_name3 = (TextView) itemView.findViewById(R.id.pax_name_3);
//                this.table_name4 = (TextView) itemView.findViewById(R.id.table_name_4);
//                this.occupied_name4 = (TextView) itemView.findViewById(R.id.occupied_name_4);
//                this.pax_name4 = (TextView) itemView.findViewById(R.id.pax_name_4);
//
////                itemView.findViewById(R.id.container).setTag("DRAGGABLE TEXTVIEW");
////                itemView.findViewById(R.id.container).setOnLongClickListener(this);
////                itemView.findViewById(R.id.container).setOnDragListener(this);
//
//                itemView.findViewById(R.id.layout_1).setTag("DRAGGABLE TEXTVIEW");
//                itemView.findViewById(R.id.layout_1).setOnLongClickListener(this);
//                itemView.findViewById(R.id.layout_1).setOnDragListener(this);
//
//                DragLinearLayout dragLinearLayout = (DragLinearLayout) itemView.findViewById(R.id.container);
//                // set all children draggable except the first (the header)
//                for(int i = 0; i < dragLinearLayout.getChildCount(); i++) {
//                    View child = dragLinearLayout.getChildAt(i);
//                    dragLinearLayout.setViewDraggable(child, child); // the child is its own drag handle
//                }
            }
//            @Override
//            public boolean onLongClick(View v) {
//                // Create a new ClipData.Item from the ImageView object's tag
//                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
//                // Create a new ClipData using the tag as a label, the plain text MIME type, and
//                // the already-created item. This will create a new ClipDescription object within the
//                // ClipData, and set its MIME type entry to "text/plain"
//                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
//                Log.i("v.getTag()_", String.valueOf(v.getTag()));
//                Log.i("mimeTypes_", Arrays.toString(mimeTypes));
//                Log.i("item_", String.valueOf(item));
//                ClipData data = new ClipData(v.getTag().toString(), mimeTypes, item);
//                // Instantiates the drag shadow builder.
//                View.DragShadowBuilder dragshadow = new View.DragShadowBuilder(v);
//                // Starts the drag
//                v.startDrag(data        // data to be dragged
//                        , dragshadow   // drag shadow builder
//                        , v           // local data about the drag and drop operation
//                        , 0          // flags (not currently used, set to 0)
//                );
//                return true;
//            }
            // This is the method that the system calls when it dispatches a drag event to the listener.
//            @Override
//            public boolean onDrag(View v, DragEvent event) {
//                Log.i("View__",v.toString());
//                // Defines a variable to store the action type for the incoming event
//                int action = event.getAction();
//                // Handles each of the expected events
//                switch (action) {
//
//                    case DragEvent.ACTION_DRAG_STARTED:
//                        // Determines if this View can accept the dragged data
//                        if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
//                            // if you want to apply color when drag started to your view you can uncomment below lines
//                            // to give any color tint to the View to indicate that it can accept data.
//                            // v.getBackground().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);
//                            // Invalidate the view to force a redraw in the new tint
//                            //  v.invalidate();
//                            // returns true to indicate that the View can accept the dragged data.
//                            return true;
//                        }
//                        // Returns false. During the current drag and drop operation, this View will
//                        // not receive events again until ACTION_DRAG_ENDED is sent.
//                        return false;
//
//                    case DragEvent.ACTION_DRAG_ENTERED:
//                        // Applies a GRAY or any color tint to the View. Return true; the return value is ignored.
//                        //v.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
//                        // Invalidate the view to force a redraw in the new tint
//                        v.invalidate();
//                        return true;
//
//                    case DragEvent.ACTION_DRAG_LOCATION:
//                        // Ignore the event
//                        return true;
//
//                    case DragEvent.ACTION_DRAG_EXITED:
//                        // Re-sets the color tint to blue. Returns true; the return value is ignored.
//                        // view.getBackground().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);
//                        //It will clear a color filter .
//                        //v.getBackground().clearColorFilter();
//                        // Invalidate the view to force a redraw in the new tint
//                        v.invalidate();
//                        return true;
//
//                    case DragEvent.ACTION_DROP:
//                        // Gets the item containing the dragged data
//                        ClipData.Item item = event.getClipData().getItemAt(0);
//                        // Gets the text data from the item.
//                        String dragData = item.getText().toString();
//                        // Displays a message containing the dragged data.
//                        Toast.makeText(getContext(), "Dragged data is " + dragData, Toast.LENGTH_SHORT).show();
//                        // Turns off any color tints
//                        //v.getBackground().clearColorFilter();
//                        // Invalidates the view to force a redraw
//                        v.invalidate();
//
//                        View vw = (View) event.getLocalState();
//                        ViewGroup owner = (ViewGroup) vw.getParent();
//                        owner.removeView(vw); //remove the dragged view
//                        //caste the view into LinearLayout as our drag acceptable layout is LinearLayout
//                        LinearLayout container = (LinearLayout) v;
//                        container.addView(vw);//Add the dragged view
//                        vw.setVisibility(View.VISIBLE);//finally set Visibility to VISIBLE
//                        // Returns true. DragEvent.getResult() will return true.
//                        return true;
//
//                    case DragEvent.ACTION_DRAG_ENDED:
//                        // Turns off any color tinting
//                        //v.getBackground().clearColorFilter();
//                        // Invalidates the view to force a redraw
//                        v.invalidate();
//                        // Does a getResult(), and displays what happened.
//                        if (event.getResult())
//                            Toast.makeText(getContext(), "The drop was handled.", Toast.LENGTH_SHORT).show();
//                        else
//                            Toast.makeText(getContext(), "The drop didn't work.", Toast.LENGTH_SHORT).show();
//                        // returns true; the value is ignored.
//                        return true;
//                    // An unknown action type was received.
//                    default:
//                        Log.e("DragDrop Example", "Unknown action type received by OnDragListener.");
//                        break;
//                }
//                return false;
//            }
        }

        @Override
        public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bill_recyclerview_item, parent, false);

            ListAdapter.ViewHolder viewHolder = new ListAdapter.ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(final ListAdapter.ViewHolder holder, final int position) {
            holder.table_name.setText(dataList.get(position).getTableName());
            holder.occupied_name.setText(dataList.get(position).getOccupiedName());
            holder.pax_name.setText(dataList.get(position).getPaxName());
            holder.table_name1.setText(dataList.get(position).getTableName1());
            holder.occupied_name1.setText(dataList.get(position).getOccupiedName2());
            holder.pax_name1.setText(dataList.get(position).getPaxName3());
            holder.table_name2.setText(dataList.get(position).getTableName());
            holder.occupied_name2.setText(dataList.get(position).getOccupiedName());
            holder.pax_name2.setText(dataList.get(position).getPaxName());
            holder.table_name3.setText(dataList.get(position).getTableName1());
            holder.occupied_name3.setText(dataList.get(position).getOccupiedName2());
            holder.pax_name3.setText(dataList.get(position).getPaxName3());
            holder.table_name4.setText(dataList.get(position).getTableName1());
            holder.occupied_name4.setText(dataList.get(position).getOccupiedName2());
            holder.pax_name4.setText(dataList.get(position).getPaxName3());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onClick(View v) {
//                    BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
//                    bottomSheetFragment.show(getActivity().getSupportFragmentManager(), bottomSheetFragment.getTag());

                    ClearBillSheetFragment clearBillSheetFragment = new ClearBillSheetFragment();
                    clearBillSheetFragment.show(getActivity().getSupportFragmentManager(), clearBillSheetFragment.getTag());
                }
            });
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }
    }
}

//        Query.CreateNewBill("ON", TransNo);
//        String sql = " SELECT ID FROM PLU Where UUID = '" + IttemID + "' ";
//
//        //String sql = "Select TotalItems,totalNettAmount FROM BillDetails Where BillNo = '"+MainActivity.strbillNo+"' ";
//        Cursor c = DBFunc.Query(sql, true);
//        if (c != null) {
//            if (c.moveToNext()) {
////            Log.i("dfd__getcount_", String.valueOf(c.getCount()));
////            if (c.getCount() == 0) {
//                for (int qty_i = 0; qty_i < Integer.parseInt(TotalQty); qty_i++) {
//                    Integer pluID = c.getInt(0);
//                    total_price += Double.valueOf(ItemPrice);
//                    RecyclerViewAdapter.totalItems = 1;
//                    RecyclerViewAdapter.countSelectedArr = 1;
//                    RecyclerViewAdapter.sldNameArr.add(ItemName);
//                    RecyclerViewAdapter.sltPriceTotalArr.add(ItemPrice);
//                    // RecyclerViewAdapter.sldQtyArr.add(Integer.parseInt(ItemQty));
//                    RecyclerViewAdapter.sldQtyArr.add(pluID);
//                    total_ItemQty += Integer.parseInt(ItemQty);
//                    RecyclerViewAdapter.sltPriceTotalArr.add(String.valueOf(total_price));
//                    RecyclerViewAdapter.sltBillDisArr.add("0");
//                    RecyclerViewAdapter.sldIDArr.add(c.getString(0));
//                    RecyclerViewAdapter.sltCategoryIDArr.add("0");
//                    RecyclerViewAdapter.sltCategoryNameArr.add("0");
//                    RecyclerViewAdapter.vchQueueNo.add(vchQueueNo);
//                    RecyclerViewAdapter.intTableNo.add(intTableNo);
//                    RecyclerViewAdapter.slddisID.add("0");
//                    RecyclerViewAdapter.slddisName.add("0");
//                    RecyclerViewAdapter.slddisTypeName.add("0");
//                    RecyclerViewAdapter.slddisType.add("0");
//                    RecyclerViewAdapter.slddisValue.add("0");
//
//                }
////                                    RecyclerViewAdapter.vchQueueNo.add(vchQueueNo);
////                                    Log.i("QQQQQQ",vchQueueNo);
////                                    RecyclerViewAdapter.intTableNo.add(intTableNo);
//                // }
//            }
//        }

//}