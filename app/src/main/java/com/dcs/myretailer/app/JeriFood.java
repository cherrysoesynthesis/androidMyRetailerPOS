package com.dcs.myretailer.app;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.dcs.myretailer.app.Activity.CashLayoutActivity;
import com.dcs.myretailer.app.Activity.CheckOutActivity;
import com.dcs.myretailer.app.Activity.TransactionDetailsActivity;
import com.dcs.myretailer.app.Cashier.MainActivity;
import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.e600.printer.PrinterTester;
import com.pax.dal.entity.EFontTypeAscii;
import com.pax.dal.entity.EFontTypeExtCode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class JeriFood {
    String status_for_jerifood = "0";
    public JeriFood(TransactionDetailsActivity context,Intent intent,Date date1,String BillNo) {
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


                    String checkingExistingBillNo = TransactionDetailsActivity.ValidAndGetValue("BillNo", "Bill", "BillNo", BillNo, false);
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

                    checkingExistingBillNo = TransactionDetailsActivity.ValidAndGetValue("BillNo", "BillList", "BillNo", BillNo, false);
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


                            //checkingExistingBillNo = ValidAndGetValue("BillNo", "PLUModi",
//                                    "BillNo", BillNo, false);
                            //if (checkingExistingBillNo.equals("0")) {

                            String productUUID = TransactionDetailsActivity.ValidAndGetValue("UUID", "PLU", "NAME", itemName, true);
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

                                        String modiID = TransactionDetailsActivity.ValidAndGetValue("ID", "Product_Modifier", "Name", metaItemValue, true);

                                        String modiName = TransactionDetailsActivity.ValidAndGetValue("Name", "Product_Modifier", "Name", metaItemValue, true);
                                        String modiPrice = TransactionDetailsActivity.ValidAndGetValue("Price", "Product_Modifier", "Name", metaItemValue, true);

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
                        String productId = TransactionDetailsActivity.ValidAndGetValue("ID", "PLU", "NAME", item_name, true);
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


                            TransactionDetailsActivity.saveBillPayments(payments_name, payments_amount, timestamp);
                        }

                        TransactionDetailsActivity.saveSales(totalItems, payments_amount, payments_name, extraCharges_amount, extraCharges_name, dateFormat3, dateFormat4, timestamp);
                    } else {

                        TransactionDetailsActivity.saveBillPayments("CASH", totalAmount, timestamp);
                        TransactionDetailsActivity.saveSales(totalItems, totalAmount, "CASH", extraCharges_amount, extraCharges_name, dateFormat3, dateFormat4, timestamp);
                    }

                    //}else {
                    if (orderStatus.equals("CANCELLED")){

                        String sales_id_for_reject = TransactionDetailsActivity.ValidAndGetValue("ID", "Sales", "BillNo", BillNo, false);

                        CashLayoutActivity.cancelBill(BillNo, context,Integer.parseInt(sales_id_for_reject));
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
    }
}
