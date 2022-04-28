package com.dcs.myretailer.app;

import android.net.http.RequestQueue;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LalamoveAPI {
    public static String lalamoveapi_url = "https://sandbox-rest.lalamove.com";
    String quotation_url_post = "https://sandbox-rest.lalamove.com/v2/quotations";
    public static JSONObject quotation_json = new JSONObject();
    String totalFee = "0";
    String totalFeeCurrency = "0";
    String msg = "0";
    //JSONObject contact_obj = new JSONObject();
    public static JSONObject putQuotationJSON(){
        quotation_json = new JSONObject();
        try {
            quotation_json.put("scheduleAt","2018-12-19T14:30:00.00Z");
            quotation_json.put("serviceType","MOTORCYCLE");
            quotation_json.put("stops",getWayPoints());
            quotation_json.put("deliveries",getDeliveryInfo());
            quotation_json.put("requesterContact",getContact());
            quotation_json.put("specialRequests","[\"COD\"]");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return quotation_json;
    }
    public static JSONObject getWayPoints(){

        JSONObject wayPoint_obj = new JSONObject();
        JSONObject en_SG_obj = new JSONObject();
        JSONObject en_SG_obj_details = new JSONObject();
        JSONObject lat_lng_obj = new JSONObject();
        try {
            en_SG_obj_details.put("displayString","444 Phayathai Road, Wang Mai Sub-district, Pathumwan District, Bangkok 10330, Thailand");
            en_SG_obj_details.put("country","SG");

            en_SG_obj.put("en_SG",en_SG_obj_details);

            lat_lng_obj.put("lat","13.740167");
            lat_lng_obj.put("lng","100.535237");

            wayPoint_obj.put( "location",lat_lng_obj);
            wayPoint_obj.put( "addresses",en_SG_obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        {
//            "location": { "lat": "13.740167", "lng": "100.535237" },
//            "addresses": {
//                "th_TH": {
//                    "displayString": "444 ถนน พญาไท แขวง วังใหม่ เขต ปทุมวัน กรุงเทพมหานคร 10330 ประเทศไทย",
//                            "country": "TH"
//                }
//            }
//        }
        return lat_lng_obj;
    }

    public static JSONObject getDeliveryInfo(){
        JSONObject contact_obj = getContact();
        JSONObject deliveryInfo_obj = new JSONObject();
        try {

            deliveryInfo_obj.put( "toStop",1);
            deliveryInfo_obj.put( "toContact",contact_obj);
            deliveryInfo_obj.put( "remarks", "ORDER#94\r\n1. Tshirt จำนวน 1\r\n2. Hoodie จำนวน 1\r\n");
        } catch (JSONException e) {
            e.printStackTrace();
        }

//        {
//            "toStop": 1,
//                "toContact": <Contact>
//                "remarks": "ORDER#94\r\n1. Tshirt จำนวน 1\r\n2. Hoodie จำนวน 1\r\n"
//        }
        return deliveryInfo_obj;
    }
    public static JSONObject getContact(){
        JSONObject contact_obj = new JSONObject();
        try {

            contact_obj.put( "name","Donald Trump");
            contact_obj.put( "phone","8912121212");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return contact_obj;
//        { "name": "Donald Trump", "phone": "8912121212" }
    }
    public void getQuotationJSON(Integer response){
        try {
                quotation_json = new JSONObject("result");
                if (response == 201) {
                   totalFee = quotation_json.get("totalFee").toString();
                   totalFeeCurrency = quotation_json.get("totalFeeCurrency").toString();
                }else{
                    msg = quotation_json.get("message").toString();

                }
            } catch (JSONException e) {
            e.printStackTrace();
        }
//    { "totalFee": "108000", "totalFeeCurrency": "THB" }
    //{ "message": "ERR_DELIVERY_MISMATCH" }
//        { "message": "ERR_TOO_MANY_STOPS" }
//        { "message": "ERR_INVALID_PAYMENT_METHOD" }
//        { "message": "ERR_INVALID_LOCALE" }
//        { "message": "ERR_INVALID_PHONE_NUMBER" }/
//        { "message": "ERR_INVALID_SCHEDULE_TIME" }
    }

    public static JSONObject sendPostSales(){
        JSONObject postSales_obj = new JSONObject();
        JSONArray postSales_obj_SalesOrderItemArr = new JSONArray();
        try {

            postSales_obj.put( "CompanyID","5");
            postSales_obj.put( "RetailerID","4");
            postSales_obj.put( "TransNo","ON000001");
            postSales_obj.put( "TotalDue","20");
            postSales_obj.put( "TotalGST","0.00");
            postSales_obj.put( "TotalDisc","0.00");
            postSales_obj.put( "TransDate","2020-06-17");
            postSales_obj.put( "CreateTime","2020-06-17 16:28:22");
            postSales_obj.put( "TotalQty","2");
            postSales_obj.put( "CashierID","");
            postSales_obj.put( "MemberID","0bb22288-b073-11ea-84cf-00155d01ca02");
            postSales_obj.put( "isNewCust","Y");
            postSales_obj.put( "SalesPersonID","");
            postSales_obj.put( "CommID","");
            postSales_obj.put( "CommPerc","0");
            postSales_obj.put( "ReceiptOrderStatus","");
            postSales_obj.put( "vchQueueNo","0001");
            postSales_obj.put( "MacAddress","");
            postSales_obj.put( "TerminalID","1");
            postSales_obj.put( "PendingSync","N");
            postSales_obj.put( "LastUser","");
            postSales_obj.put( "LastUpdate","2020-06-17 16:28:22");
            postSales_obj.put( "LockUser","N");
            postSales_obj.put( "LockUpdate","2020-06-17 16:28:22");
            postSales_obj.put( "LockStatus","0");
            postSales_obj.put( "RecordStatus","READY");
            postSales_obj.put( "RecordUpdate","2020-06-17 16:28:22");
            postSales_obj.put( "QueueStatus","READY");

            JSONObject salesOrderItem_obj = new JSONObject();
            salesOrderItem_obj.put("RecordNo","1");
            salesOrderItem_obj.put("LineNo","1");
            salesOrderItem_obj.put("ItemQty","2");
            salesOrderItem_obj.put("ItemPrice","10");
            salesOrderItem_obj.put("ItemTotal","20.00");
            salesOrderItem_obj.put("ItemGST","0.00");
            salesOrderItem_obj.put("ItemDiscType","");
            salesOrderItem_obj.put("ItemDisc1","0.00");
            salesOrderItem_obj.put("ItemDisc2","0.00");
            salesOrderItem_obj.put("ItemDisc3","0.00");
            salesOrderItem_obj.put("ItemID","4ffb9a83-4efe-11ea-84ac-0894ef44a723");
            salesOrderItem_obj.put("ItemBarcode","4710227231304");
            salesOrderItem_obj.put("ItemUOM","");
            salesOrderItem_obj.put("ItemGSTInEx","N");
            salesOrderItem_obj.put("ItemCost","6.00");
            salesOrderItem_obj.put("ItemActQty","1.000");
            salesOrderItem_obj.put("ItemUOMID","");
            salesOrderItem_obj.put("ItemGroupDisc","0.00");
            salesOrderItem_obj.put("ItemSKU","4710227231304");
            salesOrderItem_obj.put("SupplierID","");
            salesOrderItem_obj.put("SalesPersonID","");
            salesOrderItem_obj.put("SalesCommTypeID","");
            salesOrderItem_obj.put("SalesCommPerc","0.00");
            salesOrderItem_obj.put("ItemCommPerc","0.00");
            salesOrderItem_obj.put("ItemCommAmt","0.00");
            salesOrderItem_obj.put("ItemSerialNo","");
            salesOrderItem_obj.put("DISCID","");
            salesOrderItem_obj.put("ItemIMEINo","");
            salesOrderItem_obj.put("ItemBatchNo","");
            salesOrderItem_obj.put("ItemStatus","");
            salesOrderItem_obj.put("OpenPriceRemark","");
            salesOrderItem_obj.put("ItemRemark","");
            salesOrderItem_obj.put("ExpireDate","0000-00-00");
            salesOrderItem_obj.put("ExpiryDay","0");
            salesOrderItem_obj.put("RedeemPoint","0");
            salesOrderItem_obj.put("ParentItemID_ADDON","");
            salesOrderItem_obj.put("bitAddOnItem","N");
            salesOrderItem_obj.put("ParentDetailID_ADDON","");
            salesOrderItem_obj.put("MemDOBDiscPerc","0.00");
            salesOrderItem_obj.put("MemDOBDiscAmount","0.00");
            salesOrderItem_obj.put("ReceiptOrderStatus","");
            salesOrderItem_obj.put("TerminalID","1");
            salesOrderItem_obj.put("RFID","");
            salesOrderItem_obj.put("PendingSync","N");
            salesOrderItem_obj.put("LastUser","");
            salesOrderItem_obj.put("LastUpdate","2020-06-17 16:28:22");
            salesOrderItem_obj.put("LockUser","N");
            salesOrderItem_obj.put("LockUpdate","2020-06-17 16:28:22");
            salesOrderItem_obj.put("LockStatus","0");
            salesOrderItem_obj.put("RecordStatus","READY");
            salesOrderItem_obj.put( "RecordUpdate","2020-06-17 16:28:22");
            salesOrderItem_obj.put( "QueueStatus","READY");

            postSales_obj_SalesOrderItemArr.put(salesOrderItem_obj);
            postSales_obj.put( "salesorder_item",postSales_obj_SalesOrderItemArr);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return postSales_obj;
    }

    //String quotation_json = "https://sandbox-rest.lalamove.com/v2/quotations";
//    Authorization: hmac <TOKEN>
//    X-LLM-Country: <YOUR_COUNTRY>
//    X-Request-ID: <NONCE>

//    Authorization: hmac 914c9e52e6414d9494e299708d176a41:1545880607433:5133946c6a0ba25932cc18fa3aa1b5c3dfa2c7f99de0f8599b28c2da88ed9d42
//    X-LLM-Country: TH
//    X-Request-ID: 211b9d85-a2cc-476f-8675-b61ec923cc27

    String place_order = "";
    String get_order_details = "";
    String get_order_driver_information = "";
    String get_order_driver_location = "";
    String cancel_order = "";

    String get_order_details_GET = "https://sandbox-rest.lalamove.com/v2/orders/";
    String driver_details_GET = get_order_details_GET + "/{orderId}/drivers/";
    String driver_location_GET = get_order_details_GET + "{orderId}/drivers/{driverId}/location";
    String cancel_order_PUT = "https://sandbox-rest.lalamove.com/v2/orders/{id}/cancel";

//    private RequestQueue mRequestQueue;
//    private StringRequest mStringRequest;
//    private String url = "http://www.mocky.io/v2/597c41390f0000d002f4dbd1";
//
//
//    private void sendAndRequestResponse() {
//
//        //RequestQueue initialized
//        mRequestQueue = Volley.newRequestQueue(this);
//
//        //String Request initialized
//        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//
//                Toast.makeText(getApplicationContext(),"Response :" + response.toString(), Toast.LENGTH_LONG).show();//display the response on screen
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//                Log.i(TAG,"Error :" + error.toString());
//            }
//        });
//
//        mRequestQueue.add(mStringRequest);
//    }

    //Singapore
    String ST_MOTORCYCLE_KEY = "MOTORCYCLE";
    String ST_MOTORCYCLE_VALUE = "Bike";
    String ST_MOTORCYCLE_ShipmentRestricions = "40 × 25 × 25cm, 8kg";

    String ST_CAR_KEY = "CAR";
    String ST_CAR_VALUE = "CAR";
    String ST_CAR_ShipmentRestricions = "70 × 50 × 50cm, 20kg";

    String ST_MINIVAN_KEY = "MINIVAN";
    String ST_MINIVAN_VALUE = "1.7m Van";
    String ST_MINIVAN_ShipmentRestricions = "160 × 120 × 100cm";

    String ST_VAN_KEY = "VAN";
    String ST_VAN_VALUE = "2.4m Van";
    String ST_VAN_ShipmentRestricions = "230 × 120 × 120cm";

    String ST_TRUCK330_KEY = "TRUCK330";
    String ST_TRUCK330_VALUE = "10ft Lorry";
    String ST_TRUCK330_ShipmentRestricions = "290 × 140 × 170cm";

    String ST_TRUCK550_KEY = "TRUCK550";
    String ST_TRUCK550_VALUE = "14ft Lorry";
    String ST_TRUCK550_ShipmentRestricions = "420 × 170 × 190cm";
}
