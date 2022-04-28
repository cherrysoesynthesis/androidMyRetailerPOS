package com.dcs.myretailer.app.Activity;

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
import android.view.View;
import android.widget.LinearLayout;
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
import com.dcs.myretailer.app.APIActivity;
import com.dcs.myretailer.app.Allocator;
import com.dcs.myretailer.app.CancelBill;
import com.dcs.myretailer.app.Cashier.MainActivity;
import com.dcs.myretailer.app.Cashier.RecyclerAdapter;
import com.dcs.myretailer.app.Adapter.CheckOutAdapter;
import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.JeriFood;
import com.dcs.myretailer.app.Printer.KitchenPrinter;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.RefreshBill;
import com.dcs.myretailer.app.RefundBill;
import com.dcs.myretailer.app.ReprintBill;
import com.dcs.myretailer.app.ReprintKitchenPrinter;
import com.dcs.myretailer.app.ResyncBill;
import com.dcs.myretailer.app.ScreenSize.TransactionDetailsActivityScreenSize;
import com.dcs.myretailer.app.TransactionDetailsAdapter;
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

    public static String checkBillnodate = "";
    public static String dateFormat3 = "";
    static Resources resourceVal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_transaction_details);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        setTitle("Transactions Details");

        appContext = getApplicationContext();
        dal = getDal();

        resourceVal = getResources();

        Intent intent = getIntent();
        BillNo = intent.getStringExtra("BillNo");

        checkBillnodate = Query.getCheckBillnoDate(BillNo);

        Date date1 = new Date();
        dateFormat3 = new SimpleDateFormat("MM/dd/yyyy").format(date1).toString();

        try {
            new JeriFood(this, intent, date1, BillNo);
        } catch (Exception e){

        }

        //ScreenSize
        new TransactionDetailsActivityScreenSize(this,binding);

        myToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_close_white));
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        try {
            updateTransactionFun();
        } catch (Exception e){

        }
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

            Cursor Cursor_tax = Query.GetTax();
            if (Cursor_tax != null) {
                if (Cursor_tax.moveToNext()) {
                    String taxRate = Cursor_tax.getString(0);
                    String taxType = Cursor_tax.getString(1);

                    String taxName = Cursor_tax.getString(2);

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

                if (CheckOutActivity.CashValueArr.size() > 0) {
//            Log.i("Sizzze","Sizzz___"+CheckOutActivity.CashValueArr.size());
                    for (int i = 0; i < CheckOutActivity.CashValueArr.size(); i++) {
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

                        try {
                            String sql = "INSERT INTO BillPayment (BillNo,STATUS,EwalletStatus,EwalletIssueBanker,EwalletPaymentType,PaymentID,Name,Amount,PaymentDateTime,QRCode," +
                                    "ChangeAmount,SaleSync) VALUES (";
                            //sql += BillNo + ", ";
                            sql += "'" + CheckOutActivity.BillNo + "'" + ", ";
                            sql += "'" + Constraints.SALES + "'" + ", ";
                            sql += 0 + ", ";
                            sql += "''" + ", ";
                            sql += "''" + ", ";
                            sql += CheckOutActivity.CashValuePaymentIDArr.get(i) + ", ";
                            sql += "'" + CheckOutActivity.CashValuePaymentNameArr.get(i) + "', ";
                            sql += Query.DoubleAmountFormat(CheckOutActivity.CashValueArr.get(i)) + ", ";
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

                        if (c.getString(11) != null && !(c.getString(11).equals("")) && c.getString(11).trim().length() > 0 && !(c.getString(11).equals("0"))) {
                            String chkicno_on = Query.GetOptions(26);
                            if (chkicno_on.equals("1")) {
                                //HPB
                                if (c.getString(11) != null && c.getString(11).length() == 9) {
                                    String remarkstransaction = Constraints.PASSFirstDigits + c.getString(11).substring(5, 9);

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
                    sldQtyArr.add(c.getString(1));
                    //sldNameArr.add(c.getString(1));
                    sldNameArr.add(c.getString(3));

                    //Double totalPrice = c.getDouble(0) * c.getDouble(1);
                    Double totalPrice = c.getDouble(2);

                    sltPriceTotalArr.add(String.valueOf(totalPrice));
                    sltPriceTotalEachArr.add(String.valueOf(totalPrice));
//                    sltPriceTotalArr.add(c.getString(2));
//                    sltPriceTotalEachArr.add(c.getString(12));
                    Integer taxID = c.getInt(6);
                    sldTaxID.add(String.valueOf(taxID));
                    sldCTaxName.add(c.getString(7));
                    sldCTaxAmount.add(c.getString(8));
                    sldDiscountName.add(c.getString(9));

                    sldDiscountType.add(c.getString(10));
                    sldDiscountValue.add(c.getString(11));

                    String str_tax = "Select Type from Tax Where ID = " +taxID;

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
                    new ReprintKitchenPrinter(this,resourceVal,BillNo);
                break;
            case R.id.img_refresh_bill:
                    new RefreshBill(this,BillNo);
                break;
            case R.id.img_resync_billno:
                    new ResyncBill(this,BillNo);
                break;
            case R.id.img_reprint:
                    new ReprintBill(this,sales_id,BillNo,STATUS,dateFormat3,checkBillnodate);
                break;
            case R.id.btn_refund_or_checkout:
                    new RefundBill(this,sales_id,BillNo,STATUS,dateFormat3,checkBillnodate);
                break;
            case R.id.btn_cancel_bill:
                    new CancelBill(this,sales_id,BillNo,STATUS,dateFormat3,checkBillnodate);
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


    //    private void convertQRCodeImage(String dcsQrCodeString, Bitmap qrcode_bitmap, int billID) {
    public static void convertQRCodeImage(Context context, String dcsQrCodeString, SweetAlertDialog pDialog, String BillNo, Integer sales_id) {
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
