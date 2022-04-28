package com.dcs.myretailer.app.Report;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.print.PrintAttributes;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;

import com.dcs.myretailer.app.Allocator;
import com.dcs.myretailer.app.Activity.BarChartActivity;
import com.dcs.myretailer.app.Cashier.MainActivity;
import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.DeviceHelper;
import com.dcs.myretailer.app.DeviceListAdapter;
import com.dcs.myretailer.app.DialogBox;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.FileBrowser;
import com.dcs.myretailer.app.Logger;
import com.dcs.myretailer.app.Model.Cancellation;
import com.dcs.myretailer.app.Model.CategorySales;
import com.dcs.myretailer.app.Model.Discount;
import com.dcs.myretailer.app.Model.Payment;
import com.dcs.myretailer.app.Model.ProductSales;
import com.dcs.myretailer.app.Model.ReceiptZCloseData;
import com.dcs.myretailer.app.Model.Refund;
import com.dcs.myretailer.app.Model.TotalSales;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.Activity.RemarkMainActivity;
import com.dcs.myretailer.app.ScreenSize.ReportActivityScreenSize;
import com.dcs.myretailer.app.Activity.SettingActivity;
import com.dcs.myretailer.app.Setting.StrTextConst;
import com.dcs.myretailer.app.PrintReceiptGenerator;
import com.dcs.myretailer.app.databinding.ActivityReportBinding;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.MPPointF;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.imin.printerlib.IminPrintUtils;
import com.imin.printerlib.util.BluetoothUtil;
import com.pax.dal.IDAL;
import com.pax.neptunelite.api.NeptuneLiteUser;
import com.usdk.apiservice.aidl.printer.ASCScale;
import com.usdk.apiservice.aidl.printer.ASCSize;
import com.usdk.apiservice.aidl.printer.AlignMode;
import com.usdk.apiservice.aidl.printer.OnPrintListener;
import com.usdk.apiservice.aidl.printer.UPrinter;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

//import com.dcs.myretailer.app.Printing.PrinterTester;

//import android.support.design.widget.BottomNavigationView;
//import android.support.design.widget.TabLayout;

public class ReportActivity extends AppCompatActivity implements View.OnClickListener, OnChartValueSelectedListener {
    Integer item_count,total_sales_amount_count = 0;
    final Calendar myCalendar = Calendar.getInstance();
    public static Date date = Calendar.getInstance().getTime();
//    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    public static String date_status = "0";
    private static IDAL dal;
    public static ReportActivity appContext;
    ArrayList<Integer> Arr = new ArrayList<Integer>();
//    Handler mHandler;
    Double TotalNettSales = 0.0;
    Integer BillCancelTotalQty = 0;
    Integer TotalQty = 0;
    Double BillCancel = 0.0;
    String BillTax = "0";
    Double TotalBillDisountTax = 0.0;
    Double TotalBillDisountAmountCancel = 0.0;
    Double TotalBillServiceChargesCancel = 0.0;
    Double TotalNettSalesProductRefund = 0.0;
    Integer BillRefundTotalQty = 0;
    Double BillRefund = 0.0;
    Double TotalBillDisountTaxRefund = 0.0;
    Double TotalBillDisountAmountRefund = 0.0;
    Double TotalBillServiceChargesRefund = 0.0;
    Double GrossSales = 0.0;
    Double TotalTaxSales = 0.0;
    Double TotalRoundAdjSales = 0.0;
    Double ServiceChargesSales = 0.0;
    Double TaxTotal = 0.0;
    Double TotalBillDisount = 0.0;
    Integer TotalQtyProduct = 0;
    Double TotalNettSalesProduct = 0.0;
    String GrossSalesProduct = "0";
    SimpleDateFormat sdf3;
    String TaxTotalProduct = "0";
    String TotalBillDisountProduct = "0";
    String BillCancelTotalQtyProduct = "";
    String BillCancelProduct = "";
    String BillTaxProduct = "";
    Double TotalBillDisountTaxProduct = 0.0;
    Double TotalProductServiceCharges = 0.0;
    Double TotalNettSalesProductCancel = 0.0;
    Integer PaymentTypeCount = 0;
    String PaymentTypeAmount = "0";
    String PaymentTypeName = "0";
    Integer DicountCount = 0;
    String DicountAmount = "0";
    String DicountName = "0";
    Integer TotalTaxesCount = 0;
    Double TotalTaxesAmount = 0.0;
    Integer TotalQtyCategory = 0;
    Double TotalNettSalesCategory = 0.0;
    //static Activity CurrentActivity;
//    private SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd");
    Double GrossSalesCategory = 0.0;
    String TaxTotalCategory = "0";
    Double TotalBillDisountCategory = 0.0;
    Integer BillCancelTotalQtyCategory = 0;
    String BillTaxCategory = "";
    Double TotalBillDisountTaxCategory = 0.0;
    Integer TotalQtyCategoryCancel = 0;
    Double TotalNettSalesCategoryCancel = 0.0;
    public static Integer _qty = 0;
    public static Double _amt = 0.0;
    public static String qty_itm = "0";
    public static String start = "0";
    public static String end = "0";
    public static long previousReport = 0;
//    public static String St = "0";
    String str = "";
    String str2 = "";
    String str3 = "";
    String str4 = "";
    String str5 = "";
    String str6 = "";
    String str7 = "";
    final static String Zeroes = "00000000";
    private Dialog dlg;

    private float scale = 4.167f;

    private Typeface boldtext = Typeface.create(Typeface.MONOSPACE, Typeface.BOLD);
    private Typeface normaltext = Typeface.create(Typeface.MONOSPACE, Typeface.NORMAL);

    private Calendar reportgentime = Calendar.getInstance();
    final Calendar myCalendar2 = Calendar.getInstance();
    private String regID = null;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_cashier:
                    //mTextMessage.setText("Cashier");
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                    finish();
                    return true;
                case R.id.navigation_report:
                    Intent report_setting = new Intent(getApplicationContext(),ReportActivity.class);
                    startActivity(report_setting);
                    finish();
                    return true;
                case R.id.navigation_setting:
                    Intent btn_dialog = new Intent(getApplicationContext(), SettingActivity.class);
                    startActivity(btn_dialog);
                    finish();
                    return true;
                case R.id.map_layout:
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("name", "MapLayoutMainPageFragment");
                    startActivity(intent);
                    finish();
                    return true;
            }
            return false;
        }
    };

    protected Typeface tfRegular;
    protected Typeface tfLight;
    public static ActivityReportBinding binding = null;
    public static UPrinter printer;
//    private SimpleDateFormat fmt = null;
    private SimpleDateFormat fmttime = null;
    public static String previous_report_shift_name = "";
    DateFormat formatter = new SimpleDateFormat(Constraints.dateYMD);
//    DateFormat formatter = new SimpleDateFormat("MM/dd/YYYY");
//    DateFormat formatter = null;

    public static void updateMediaButtons() {
        ReportOverallFragment.updateMediaButtons();
        updateReportDateAndDataFun();
    }

    private static void updateReportDateAndDataFun() {

        updateFromToDateFun();

        updateQtyAndAmtFun();

//        ReportXFragment.customAdapter.notifyDataSetChanged();
    }

    public static void updateFromToDateFun() {
        if (start.length() > 0 || end.length() > 0) {
            binding.mText.setText(start);
            binding.mText1.setText(end);
        } else {
            DateFormat formatter = new SimpleDateFormat(Constraints.dateYMD);
            String today = formatter.format(date);
            binding.mText.setText(today);
            binding.mText1.setText(today);
        }
        start = binding.mText.getText().toString();
        end = binding.mText1.getText().toString();
    }


    public static void updateQtyAndAmtFun() {

        new AsyncTaskUpdateQtyAndAmtFun(binding,_qty,_amt,qty_itm).execute(String.valueOf(ReportActivity.previousReport),
                ReportActivity.previous_report_shift_name,start,end);

    }

//    public static void showTotalItemsAndAmt(Double amt,Integer qty,String status) {
//        Log.i("Sdf_____","___"+binding.pagerReport.getCurrentItem()+"_a__"+amt+"_b__"+qty+"_c__"+status);
//        //String name = String.valueOf(binding.tabLayoutReport.getTabAt(binding.pagerReport.getCurrentItem()));
//        String name = String.valueOf(binding.tabLayoutReport.getSelectedTabPosition());
//        Log.i("Sdf_____","__name_=_"+name);
//        //if (binding.pagerReport.getCurrentItem() < 3) {
//        if (name != null && (name.equals("0") || name.equals("1") || name.equals("2"))) {
//            String _qty_task = "";
////            if (status.equals("1")) {
//                if (qty > 0) {
//                    _qty_task = qty + " items";
//                } else {
//                    _qty_task = qty + " item";
//                }
//                try {
//
//                    ReportActivity.binding.LayTotalSales.setVisibility(View.VISIBLE);
//                    ReportActivity.binding.txttotalPriceAmount.setText("$" + String.format("%.2f", Double.valueOf(amt)));
//                    ReportActivity.binding.txttotalQty.setText("Total Sales (" + _qty_task + " sold)");
//                } catch (Exception e) {
//                    ReportActivity.binding.LayTotalSales.setVisibility(View.VISIBLE);
//                    ReportActivity.binding.txttotalPriceAmount.setText("$0.00");
//                    ReportActivity.binding.txttotalQty.setText("Total Sales (" + 0 + " sold)");
//                }
////            } else {
////                ReportActivity.binding.LayTotalSales.setVisibility(View.GONE);
////            }
//        } else {
//                ReportActivity.binding.LayTotalSales.setVisibility(View.GONE);
//            }
//    }

    public static class AsyncTaskUpdateQtyAndAmtFun extends AsyncTask<Object, ImageView, Void> {
        ActivityReportBinding binding_task = null;
        String qty_itm_task = "0";
        Double _amt_task = 0.0;
        Integer _qty_task = 0;
        public AsyncTaskUpdateQtyAndAmtFun(ActivityReportBinding binding,Integer _qty,Double _amt,String qty_itm) {
            binding_task = binding;
            _qty_task = _qty;
            _amt_task = _amt;
            qty_itm_task = qty_itm;
        }

        protected Void doInBackground(Object... params) {
            String sql = "";
            String reportCriteria = (String) params[0];
            String reportShiftName = (String) params[1];
            String start_task = (String) params[2];
            String end_task = (String) params[3];
            if (reportCriteria.length() > 4){
                String billnoall = "";
                String query = " WHERE (STATUS = 'SALES'   OR STATUS = 'REFUND') ";
                String cpSql = "SELECT ID,AllBillNo,ClosingTime FROM ZClosing " +
                        "WHERE  ClosingTime = '"+reportCriteria+"' ";
//                    "WHERE strftime('"+Constraints.sqldateformat+"', ClosingTime / 1000, 'unixepoch') = '"+ReportActivity.previousReport+"' ";

                Cursor cpc = DBFunc.Query(cpSql, false);
                if (cpc != null) {
                    if (cpc.moveToNext()) {
                        billnoall = cpc.getString(1);
                    }
                    cpc.close();
                }

                sql = "SELECT SUM(TotalQty),SUM(TotalNettSales),SUM(GrossTotal),SUM(TotalBillDisount)," +
                        "SUM(TotalTaxes),SUM(RoundAdj)," +
                        "SUM(ServiceCharges),count(ID),SUM(TotalItemDisount)" +
                        " FROM Sales " +
                        query ;
                if (reportShiftName.equals("Now")){
                    sql += "AND IsZ IS NULL " ;
                }
                sql += " AND BillID IN ("+billnoall+") " +
                        " AND strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch') BETWEEN '"+ start_task +"' AND '"+ end_task +"' " +
                        " group by strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch')  order by BillNo DESC ";


            }else {
                //String query = " WHERE STATUS = 'SALES' AND isZ IS NULL ";
                // String query = " WHERE STATUS = 'SALES' ";
                String query = " WHERE (STATUS = 'SALES' OR STATUS = 'REFUND') ";
//                    String query = " WHERE STATUS = 'SALES' ";
                sql = " SELECT SUM(TotalQty),SUM(TotalNettSales) FROM SALES " +
                        query ;
                //String myFormat = "";
//                    try {
//                        myFormat = "YYYY-MM-dd"; //In which you need put here
//                    }catch (IllegalArgumentException e){
//                        myFormat = "yyyy-MM-dd"; //In which you need put here
//                    }

                //String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(Constraints.dateYMD, Locale.US);
                final Calendar myCalendar2 = Calendar.getInstance();
                String todayd = sdf.format(myCalendar2.getTime());
//                    if (todayd.equals(start)) {
                if (reportShiftName.equals("Now")){
                    sql += "AND IsZ IS NULL " ;
                }
                sql += " AND strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch') BETWEEN '"+ start_task +"' AND '"+ end_task +"' ";


            }
            Cursor c = DBFunc.Query(sql, false);
            if (c != null) {
                _qty_task = 0;
                _amt_task = 0.0;
                while (c.moveToNext()) {
//                            if (!c.isNull(0)) {
                    _qty_task += c.getInt(0);
                    _amt_task += c.getDouble(1);
//                            }
                }
                c.close();
            }

//            if (_qty_task > 0) {
//                qty_itm_task = _qty_task + " items";
//            } else {
//                qty_itm_task = _qty_task + " item";
//            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
//            binding_task.txttotalPriceAmount.setText("$" + String.format("%.2f", Double.valueOf(_amt_task)));
//            binding_task.txttotalQty.setText("Total Sales (" + qty_itm_task + " sold)");
            Log.i("DSFDSF____","result_____"+"herere");
            //ReportActivity.showTotalItemsAndAmt(_amt_task,_qty_task,"1");
        }

        @Override
        protected void onPreExecute() {
        }
    }


    @Override
    protected void onResume() {
        appContext = ReportActivity.this;
        updateReportDateAndDataFun();
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText(Constraints.Exit)
                .setConfirmText(Constraints.YES)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        //sDialog.dismissWithAnimation();
                        ActivityCompat.finishAffinity(ReportActivity.this);
                    }
                })
                .setCancelButton("Cancel", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                    }
                })
                .show();

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
//    private static IminPrintUtils mIminPrintUtils;
    public static String terminalTypeVal = "PAX";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_report);

        //appContext = getApplicationContext();
        appContext = ReportActivity.this;
        dal = getDal();

        terminalTypeVal = Query.GetDeviceData(Constraints.TERMINAL_TYPE);
        if (terminalTypeVal.equals(Constraints.INGENICO)) {
            Query.RegisterForIngenico(ReportActivity.this);

            printer = DeviceHelper.me().getPrinter();
        }

        String statusZ = "";
        Intent intent = getIntent();
        if (intent.getStringExtra("statusZ") != null) {
            statusZ = intent.getStringExtra("statusZ");

            Log.i("statusZ___","statusZ__"+statusZ);
            //if (statusZ == null || statusZ.isEmpty()){
            if (statusZ == null || statusZ.equals("1")){
                Log.i("statusZ___","statusZt__"+statusZ);
                //if (statusZ != null && statusZ.length() > 3){



                DialogFunZCloseYst("Previous Z-Closing need to close ", "Z", 3);
            }
        }


        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        setTitle("Reports");

        String searchCloseDateEmpty = "SELECT BillNo,CloseDateTime FROM Bill WHERE CloseDateTime IS NULL";
        Cursor csearchCloseDateEmpty = DBFunc.Query(searchCloseDateEmpty,false);
        if (csearchCloseDateEmpty != null) {
            while (csearchCloseDateEmpty.moveToNext()){
                String billid = csearchCloseDateEmpty.getString(0);
                String billlistdt = "SELECT DateTime FROM BillList WHERE BillID ='"+billid+"'";
                Cursor cbilllistdt = DBFunc.Query(billlistdt,false);
                if (cbilllistdt != null) {
                    if (cbilllistdt.moveToNext()) {
                        long dt = cbilllistdt.getLong(0);

                        //Update Bill
                        String updBill = "UPDATE Bill SET CloseDateTime = "+dt +" WHERE BillNo = "+billid;
                        Log.i("updBill___","updBill___"+updBill);
                        DBFunc.ExecQuery(updBill,false);
                    }
                    cbilllistdt.close();
                }

            }
            csearchCloseDateEmpty.close();
        }


//        DateFormat formatter = new SimpleDateFormat(Constraints.dateYMD);
//        formatter = new SimpleDateFormat("MM/dd/YYYY");
//        String today = formatter.format(date);

        //fmt = new SimpleDateFormat(Constraints.dateYMD);
        //formatter = new SimpleDateFormat(Constraints.dateYMD);
        fmttime = new SimpleDateFormat("HH:mm:ss");
        //CurrentActivity = getApplicationContext();

//        St = "1";
//
        tfRegular = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        tfLight = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");

        binding.txtbuttn.setOnClickListener(this);

        binding.chart1.setOnChartValueSelectedListener(this);

        binding.chart1.setDrawBarShadow(false);
        binding.chart1.setDrawValueAboveBar(true);

        binding.chart1.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        binding.chart1.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        binding.chart1.setPinchZoom(false);

        binding.chart1.setDrawGridBackground(false);
        // chart.setDrawYLabels(false);

        setData(9,21,300);

//        ArrayList<String> mValues = new ArrayList<String>();
//        mValues.add("AAAA");
//        mValues.add("AAAA1");
//        mValues.add("AAAA2");
//        mValues.add("AAAA3");
//        IAxisValueFormatter xAxisFormatter = new DayAxisValueFormatter(mValues);
//
//        XAxis xAxis = chart.getXAxis();
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setTypeface(tfLight);
//        xAxis.setDrawGridLines(false);
//        xAxis.setGranularity(1f); // only intervals of 1 day
//        xAxis.setLabelCount(7);
//        xAxis.setValueFormatter(xAxisFormatter);
//
//        IAxisValueFormatter custom = new MyAxisValueFormatter();
//
//        YAxis leftAxis = chart.getAxisLeft();
//        leftAxis.setTypeface(tfLight);
//        leftAxis.setLabelCount(8, false);
//        leftAxis.setValueFormatter(custom);
//        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
//        leftAxis.setSpaceTop(15f);
//        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
//
//        YAxis rightAxis = chart.getAxisRight();
//        rightAxis.setDrawGridLines(false);
//        rightAxis.setTypeface(tfLight);
//        rightAxis.setLabelCount(8, false);
//        rightAxis.setValueFormatter(custom);
//        rightAxis.setSpaceTop(15f);
//        rightAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
//
//        Legend l = chart.getLegend();
//        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
//        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
//        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
//        l.setDrawInside(false);
//        l.setForm(Legend.LegendForm.SQUARE);
//        l.setFormSize(9f);
//        l.setTextSize(11f);
//        l.setXEntrySpace(4f);
//
//        XYMarkerView mv = new XYMarkerView(this, xAxisFormatter);
//        mv.setChartView(chart); // For bounds control
//        chart.setMarker(mv); // Set the marker to the chart


        updateReportDateAndDataFun();

        binding.LayReportRefresh.setOnClickListener(this);

        formatter = new SimpleDateFormat(Constraints.dateYMD);
        String today = formatter.format(date);

        String sql_dt = "select strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch')" +
                " from Sales " ;
        sql_dt += "where isZ IS NULL ";
        sql_dt += "order by DateTime ASC";

        String isnulldatestr = "";
        Cursor sql_dt_c = DBFunc.Query(sql_dt,false);
        if (sql_dt_c != null) {
            if (sql_dt_c.moveToNext()) {
                isnulldatestr = sql_dt_c.getString(0);

            }
            sql_dt_c.close();
        }

        if (isnulldatestr != null && isnulldatestr.length() > 0) {
            if (isnulldatestr.equals(today)) {
                start = today;
                end = today;
            }else {
                start = isnulldatestr;
                end = today;
            }
        }else {
            start = today;
            end = today;
        }

        try {

            sdf3 = new SimpleDateFormat(Constraints.dateYMD+"-HH-mm-ss");
            //sdf3 = new SimpleDateFormat("YYYY-MM-dd-HH-mm-ss");
        }catch (IllegalArgumentException e){

            sdf3 = new SimpleDateFormat(Constraints.dateYMD+"-HH-mm-ss");
            //sdf3 = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

        }

//        String query = " WHERE (STATUS = 'SALES' OR STATUS = 'REFUND')  AND isZ IS NULL ";
//        String sql = " SELECT SUM(TotalQty),SUM(TotalNettSales) FROM SALES " +
//                    query;
//
//        Cursor c = DBFunc.Query(sql, false);
//        if (c != null) {
//            _qty = 0;
//            _amt = 0.0;
//            while (c.moveToNext()) {
////                if (!c.isNull(0)) {
//                    _qty += c.getInt(0);
//                    _amt += c.getDouble(1);
////                }
//            }
//            c.close();
//        }
//        binding.txttotalPriceAmount.setText("$"+ String.format("%.2f", Double.valueOf(_amt)));
//        String qty_itm = "0";
//        if (_qty > 0){
//            qty_itm = _qty + "items";
//        }else {
//            qty_itm = _qty + "item";
//        }
//        binding.txttotalQty.setText("Total Sales ("+qty_itm+" sold)");
        binding.rPrint.setOnClickListener(this);
        binding.LayReportExport.setOnClickListener(this);

        tabLayoutFun();

        binding.navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        binding.navView.getMenu().findItem(R.id.navigation_report).setChecked(true);

      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            if (terminalTypeVal.equals(Constraints.INGENICO)){
                binding.navView.setLayoutParams(new LinearLayout.LayoutParams(720, 120));
                binding.pagerReport.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 780));
            }else if (terminalTypeVal.equals(Constraints.PAX)){

                LinearLayout.LayoutParams pagerReportLay = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        850);
                binding.pagerReport.setLayoutParams(pagerReportLay);

                binding.navView.setLayoutParams(new LinearLayout.LayoutParams(720, 100));
            }
        }
        formatter = new SimpleDateFormat(Constraints.dateYMD);
        today = formatter.format(date);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

//        menu_icon.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
////                // TODO Auto-generated method stub
////                new DatePickerDialog(ReportActivity.this, date, myCalendar
////                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
////                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
//                ReportDateSheetFragment rdSheetFragment = new ReportDateSheetFragment();
//                rdSheetFragment.show(getSupportFragmentManager(), rdSheetFragment.getTag());
//            }
//        });

//        m_icon.setOnClickListener(new View.OnClickListener() {
        binding.daterange.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
        //                // TODO Auto-generated method stub
        //                new DatePickerDialog(ReportActivity.this, date, myCalendar
        //                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
        //                 myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                PreviousReportFragment.previous_start_d = "";
                PreviousReportFragment.previous_end_d = "";
                ReportDateSheetFragment.currenttabnumber = binding.pagerReport.getCurrentItem();
                ReportDateSheetFragment rdSheetFragment = new ReportDateSheetFragment();
                rdSheetFragment.show(getSupportFragmentManager(), rdSheetFragment.getTag());
            }
        });
        //ScreenSize
        new ReportActivityScreenSize(appContext,binding);
    }

    private void DialogFunZCloseYst(String msg, final String title, final Integer tabNumber) {

        final SweetAlertDialog ystZCloseDialog = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                //.setTitleText("Cancelled Bill")
                .setContentText(msg)
                .setConfirmText(Constraints.YES)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                        PrintX_ZReport(title,tabNumber);
                    }
                });
        ystZCloseDialog.show();
        ystZCloseDialog.setCancelable(false);
    }


    public void tabLayoutFun() {
        binding.tabLayoutReport.removeAllTabs();
        binding.tabLayoutReport.addTab(binding.tabLayoutReport.newTab().setText("Overall"));//0
        binding.tabLayoutReport.addTab(binding.tabLayoutReport.newTab().setText("Product"));//1
        binding.tabLayoutReport.addTab(binding.tabLayoutReport.newTab().setText("Category"));//2
        binding.tabLayoutReport.addTab(binding.tabLayoutReport.newTab().setText(Constraints.ZReport));//3
        binding.tabLayoutReport.addTab(binding.tabLayoutReport.newTab().setText(Constraints.XReport));//4
//        tabLayout.addTab(tabLayout.newTab().setText("Others"));//5
        binding.tabLayoutReport.setTabTextColors(getResources().getColor(R.color.mine_shaft),
                getResources().getColor(R.color.mine_shaft));
        binding.tabLayoutReport.setTabGravity(TabLayout.GRAVITY_FILL);
        binding.tabLayoutReport.setTabTextColors(Color.parseColor("#a9aaad"), Color.parseColor("#000000"));
////        TabLayout tab_layout_report_search = (TabLayout) findViewById(R.id.tab_layout_report_search);
//        //tab_layout_report_search.addTab(tab_layout_report_search.newTab().setIcon(R.drawable.ic_calendar_grey_500));
//        LayoutInflater lay1 = (LayoutInflater) appContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        //View vtab = (View) lay1.inflate(R.layout.tab_list_item, null);
//        View vtab11 = (View) lay1.inflate(R.layout.tab_list_item11, null);
//        View vtab1 = (View) lay1.inflate(R.layout.tab_list_item1, null);
//        View vtab2 = (View) lay1.inflate(R.layout.tab_list_item2, null);
//        View vtab3 = (View) lay1.inflate(R.layout.tab_list_item3, null);
//        View vtab4 = (View) lay1.inflate(R.layout.tab_list_item4, null);

        binding.tabLayoutReportSearch.setTabTextColors(getResources().getColor(R.color.mine_shaft),
                getResources().getColor(R.color.mine_shaft));
        //tab_layout_report_search.setTabGravity(TabLayout.GRAVITY_FILL);
        binding.tabLayoutReportSearch.setTabGravity(TabLayout.GRAVITY_CENTER);


        if (ReportDateSheetFragment.currenttabnumber != -1){
            binding.pagerReport.setCurrentItem(ReportDateSheetFragment.currenttabnumber);
        }
        ReportAdapter adapter = new ReportAdapter
                (getSupportFragmentManager(), binding.tabLayoutReport.getTabCount(),ReportActivity.this);
        binding.pagerReport.setAdapter(adapter);
        //viewPager.addOnPageChangeListener((ViewPager.OnPageChangeListener) new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        binding.pagerReport.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabLayoutReport));

//        adapter = new ReportAdapter
//                (getSupportFragmentManager(), tabLayout.getTabCount());
//        viewPager.setAdapter(adapter);
//        if (ReportDateSheetFragment.currenttabnumber != -1){
//            binding.pagerReport.setCurrentItem(ReportDateSheetFragment.currenttabnumber);
//        }
        binding.tabLayoutReport.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            //        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                try {

//                    viewPager.setCurrentItem(tab.getPosition());



//                     ReportAdapter adapter = new ReportAdapter
//                            (getSupportFragmentManager(), binding.tabLayoutReport.getTabCount(),ReportActivity.this);
//                    binding.pagerReport.setAdapter(adapter);

                if (binding.pagerReport.getCurrentItem() > -1) {
//                    if (viewPager.getCurrentItem() == 0){
//                        St = "1";
//                    }
                    binding.pagerReport.setCurrentItem(tab.getPosition());
                }else {
                    binding.pagerReport.setCurrentItem(ReportDateSheetFragment.currenttabnumber);
                }
                }catch (Exception e){
                    Log.e("IllegalFormatException",e.getMessage());
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        //menu_text.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txtbuttn:
                Intent ddintent = new Intent(ReportActivity.this, BarChartActivity.class);
                startActivity(ddintent);
                break;
            case R.id.Lay_report_refresh:

                DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                        System.currentTimeMillis(), DBFunc.PurifyString("ReportActivity-OnClick " +"Refresh -"+MainActivity.strbillNo));

                Intent report_setting = new Intent(getApplicationContext(), ReportActivity.class);
                startActivity(report_setting);
                finish();
//                St = "1";
                break;
//            case R.id.report_print:
//                date_status = "1";
//
//                Log.i("SDfdf_report_print",date_status);
//                Log.i("SDfdf_reposdst",menu_text.getText().toString());
//                Log.i("SDfdf_reposdst",menu_text1.getText().toString());
//                printSales(menu_text.getText().toString(),menu_text1.getText().toString());
//            break;
            case R.id.r_print:

                DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                        System.currentTimeMillis(), DBFunc.PurifyString("ReportActivity-OnClick " +"Reprint -"+MainActivity.strbillNo));

//                     if (binding.pagerReport.getCurrentItem() == 2) {
                     if (binding.pagerReport.getCurrentItem() ==3) {

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

                    if (checkBillPending == true) {

//                        if (ReportActivity.previous_report_shift_name.toUpperCase().equals("NOW")) {
                            ReprintOrPrintZCloseDialogFun("What you want to do ?", "Z", binding.pagerReport.getCurrentItem());
//                        }else {
//                            new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
//                                    .setTitleText("Please choose Report Type 'Now'.")
//                                    .setConfirmText(Constraints.OK)
//                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                                        @Override
//                                        public void onClick(SweetAlertDialog sDialog) {
//                                            //sDialog.dismissWithAnimation();
//                                            //ActivityCompat.finishAffinity(ReportActivity.this);
//                                            sDialog.dismissWithAnimation();
//                                        }
//                                    })
//                                    .show();
//                        }
                        //DialogFun("Are you sure you want to do Z Closing report ?", "Z");
                    }else {

                        //Query.ErrorDialog(ReportActivity.this,"Pending Bill still open.");
                        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText(Query.stillpendingbillno+" Pending Bill still open.")
                                .setConfirmText(Constraints.YES)
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        //sDialog.dismissWithAnimation();
                                        //ActivityCompat.finishAffinity(ReportActivity.this);
                                        sDialog.dismissWithAnimation();
                                    }
                                })
//                    .setCancelButton("No", new SweetAlertDialog.OnSweetClickListener() {
//                        @Override
//                        public void onClick(SweetAlertDialog sDialog) {
//                            sDialog.dismissWithAnimation();
//                        }
//                    })
                                .show();
                    }
                }else if (binding.pagerReport.getCurrentItem() == 4){
//                }else if (binding.pagerReport.getCurrentItem() == 3){
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

                    if (checkBillPending == true) {
//                        if (!(ReportActivity.previous_report_shift_name == null ||
//                                ReportActivity.previous_report_shift_name.toUpperCase().equals("NOW") ||
//                                ReportActivity.previous_report_shift_name.toUpperCase().equals("ALL") ||
//                                ReportActivity.previous_report_shift_name.length() > 3)) {
                            DialogFun("Are you sure you want to do X Closing report ?", "X",binding.pagerReport.getCurrentItem());
//                        }else {
//                            new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
//                                    .setTitleText("Please choose Report Type.")
//                                    .setConfirmText(Constraints.YES)
//                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                                        @Override
//                                        public void onClick(SweetAlertDialog sDialog) {
//                                            //sDialog.dismissWithAnimation();
//                                            //ActivityCompat.finishAffinity(ReportActivity.this);
//                                            sDialog.dismissWithAnimation();
//                                        }
//                                    })
//                                    .show();
//                        }
                    }else {
                        //Query.ErrorDialog(ReportActivity.this,"Pending Bill still open.");
                        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                                .setTitleText(Query.stillpendingbillno+" Pending Bill still open.")
                                .setConfirmText(Constraints.YES)
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        //sDialog.dismissWithAnimation();
                                        //ActivityCompat.finishAffinity(ReportActivity.this);
                                        sDialog.dismissWithAnimation();
                                    }
                                })
//                    .setCancelButton("No", new SweetAlertDialog.OnSweetClickListener() {
//                        @Override
//                        public void onClick(SweetAlertDialog sDialog) {
//                            sDialog.dismissWithAnimation();
//                        }
//                    })
                                .show();
                    }
                }else {
                    new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("MyRetailer")
                            .setContentText("Please choose ZReport or XReport to print!")
                            .show();
                }



            break;
            case R.id.report_export:
//                date_status = "1";
//                printSales();

                    //download();


                break;
            case R.id.Lay_report_export:
                DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                        System.currentTimeMillis(), DBFunc.PurifyString("ReportActivity-OnClick " +"Export -"+MainActivity.strbillNo));
//                date_status = "1";
//                printSales();

//                    //download();
//                final Calendar calfrom = Calendar.getInstance();
//                final Calendar calto = Calendar.getInstance();
//                calfrom.set(Calendar.HOUR_OF_DAY, 0);
//                calfrom.set(Calendar.MINUTE, 0);
//                calfrom.set(Calendar.SECOND, 0);
//                calfrom.set(Calendar.MILLISECOND, 0);
//
//                calto.set(Calendar.HOUR_OF_DAY, 23);
//                calto.set(Calendar.MINUTE, 59);
//                calto.set(Calendar.SECOND, 59);
//                calto.set(Calendar.MILLISECOND, 999);
//                UserData user = null;
//                ReferData refer = null;
//                GenerateReport(calfrom,calto, 1, user,refer,false);
                Intent intent = new Intent(getApplicationContext(), ActivityGenReport.class);
                startActivity(intent);
                break;
        }
    }


    private void ReprintOrPrintZCloseDialogFun(String msg, final String title, final Integer tabNumber) {
        new SweetAlertDialog(this, SweetAlertDialog.NORMAL_TYPE)
                .setTitleText("MyRetailer")
                .setContentText(msg)
                .setConfirmText("ZClose")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();

                        DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                                System.currentTimeMillis(), DBFunc.PurifyString("ReportActivity-OnClick " +"ZClose -"+MainActivity.strbillNo));
                        //PrintX_ZReport(title);
                        //String accessable = SyncActivity.volleyCheckPermission(appContext, Constraints.Z_Closing,Constraints.Accessable);
                        //String accessable = Query.SearchUserAccess(Constraints.F0031, RemarkMainActivity.userid,RemarkMainActivity.userpassword);
                        //if (accessable.equals("1")){
                        String accessablecancel = "0";
                        if (RemarkMainActivity.userid.equals("1111")){
                            accessablecancel = "1";
                        }else {
                            accessablecancel = Query.SearchUserAccess(Constraints.F0031, RemarkMainActivity.userid,RemarkMainActivity.userpassword);
                        }
                        //if (Constraints.Accessable.equals("1")) {
                        if (accessablecancel.equals("1")) {
                            DialogFun("Are you sure you want to do Z Closing report ?", "Z", tabNumber);
                        } else {
                            Query.DonothaveUserAccess(appContext);
                        }
                    }
                })
                .setCancelButton("Reprint", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();

                        DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                                System.currentTimeMillis(), DBFunc.PurifyString("ReportActivity-OnClick " +"Reprint -"+MainActivity.strbillNo));

                        //SyncActivity.volleyCheckPermission(appContext, Constraints.Print_Z_Report,Constraints.Accessable);
                        String accessablecancel = "0";
                        if (RemarkMainActivity.userid.equals("1111")){
                            accessablecancel = "1";
                        }else {
                            accessablecancel = Query.SearchUserAccess(Constraints.F0096, RemarkMainActivity.userid,RemarkMainActivity.userpassword);
                        }
                       // if (Constraints.Accessable.equals("1")) {
                        if (accessablecancel.equals("1")) {
                            if (String.valueOf(ReportActivity.previousReport).length() > 4){
                                //PrintX_ZReport("X");
                                ReceiptZCloseData rzcd = new ReceiptZCloseData();
                                rzcd = getPrintSales("Z","ReprintZ",appContext);
                                printSales(rzcd, str, str2, str3, str4, str5, str6, str7, "Z","ReprintZ",tabNumber);

                            }else {
                                new SweetAlertDialog(ReportActivity.this, SweetAlertDialog.ERROR_TYPE)
                                        .setTitleText("MyRetailer")
                                        .setContentText("Please choose Previous Report!")
                                        .show();
                            }
                        } else {
                            Query.DonothaveUserAccess(appContext);
                        }
                    }
                })
                .show();
    }

    private void DialogFun(String msg, final String title, final Integer tabNumber) {
        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("MyRetailer")
                .setContentText(msg)
                //.setConfirmText("Yes,delete it!")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                       PrintX_ZReport(title,tabNumber);
                    }
                })
                .setCancelButton("Cancel", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                        //PrintX_ZReport("X");
                    }
                })
                .show();
    }

    private void PrintX_ZReport(String str_status,Integer tabNumber) {

        AutoSaveDBBackup();
        CheckZReportPrint();
        date_status = "1";
        str = "";
        str2 = "";
        str3 = "";
        str4 = "";
        str5 = "";
        str6 = "";
        str7 = "";
        //getPrintSales(m_text.getText().toString(),m_text1.getText().toString());

        ReceiptZCloseData rzcd = new ReceiptZCloseData();
        rzcd = getPrintSales(str_status,"NormalPrintZ",appContext);
       // printSales(str, str2, str3, str4, str5, str6, str7, str_status,"NormalPrintZ",tabNumber);
        printSales(rzcd,str, str3 , str2, str4, str5, str6, str7, str_status,"NormalPrintZ",tabNumber);

    }

    private void AutoSaveDBBackup() {
        File dbpath = getApplicationContext().getDatabasePath("master.db");
        File dbpath_trans = getApplicationContext().getDatabasePath("transact.db");

        if(dbpath.exists() && dbpath.canRead()){

            String FolerName = sdf3.format(myCalendar2.getTime()).toString();
            File direct = new File("/storage/emulated/0/"+FolerName);

            if (direct.exists()){
                Log.i("FolerName" + direct, "exists");
                try {

                    FileUtils.deleteDirectory(direct);
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.i("file_" + FolerName, "FolerNameError_"+e.getMessage());
                }
            } else {
                Log.i("FolerName" + direct, "else");
//                direct.mkdir();
                if (direct.mkdirs()) {
                    Log.i("file_" + FolerName, "created___");
                } else {
                    Log.i("file_" + FolerName, "Error___");
                }
            }

//                File  f = new File("non_existing_dir/someDir");
//                if (direct.mkdir()) {
//                    Log.i("file_" + FolerName, "created");
//                } else {
//                    Log.i("file_" + FolerName, "Error__");

            //}


            File f_master = new File(direct.getAbsolutePath() +"/"+"master.db");
            File f_transaction = new File(direct.getAbsolutePath()+"/"+"transaction.db");

            //File f = new File("/storage/emulated/0/ate1");

            try {
                Log.i("SaveDBToDisk" + f_master.getAbsolutePath(), "__"+dbpath.getAbsolutePath());
                DBFunc.SaveDBToDisk(f_master.getAbsolutePath(), dbpath.getAbsolutePath());
                DBFunc.SaveDBToDisk(f_transaction.getAbsolutePath(), dbpath_trans.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
                Log.i("EEEEEEEEEEE",e.getMessage());
            }
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(f_master)));
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(f_transaction)));

            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth, System.currentTimeMillis(),
                    "Database -> AutoBackup -> Master -> "+f_master.getAbsolutePath());
            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth, System.currentTimeMillis(),
                    "Database -> AutoBackup -> Transaction -> "+f_transaction.getAbsolutePath());

            Log.i("SaveDBToDisk" + "AutoBackup", "__"+f_master.getAbsolutePath());
            Log.i("SaveDBToDisk" + "AutoBackup", "__"+f_transaction.getAbsolutePath());
//            DialogBox dlg = new DialogBox(getApplicationContext());
//            dlg.setDialogIconType(DialogBox.IconType.INFORMATION);
//            dlg.setTitle(StrTextConst.GetText(StrTextConst.TextType.CFGDB, 7));
//            dlg.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGDB, 13,f_master.getName()));
//            dlg.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
//            dlg.show();
        }else{
            Log.i("SaveDBToDisk" ,"ELSE______");
//            DialogBox dlg = new DialogBox(getApplicationContext());
//            dlg.setDialogIconType(DialogBox.IconType.ERROR);
//            dlg.setTitle(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 18));
//            dlg.setMessage(StrTextConst.GetText(StrTextConst.TextType.CFGDB, 12));
//            dlg.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
//            dlg.show();
        }
    }
    void GenerateReport(Calendar calfrom, Calendar calto, int reportType, UserData user, ReferData refer, boolean viewonly){
        if(reportType<0 || reportType >5){
            return;
        }

        dlg = new Dialog(this);
        dlg.setTitle(StrTextConst.GetText(StrTextConst.TextType.RPTGEN, 1));
        dlg.setCancelable(false);
        dlg.setCanceledOnTouchOutside(false);
        LinearLayout lay = new LinearLayout(this);
        dlg.addContentView(lay, new ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ProgressBar pb = new ProgressBar(this);
        pb.setLayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        pb.setIndeterminate(true);
        lay.addView(pb);

        reportgentime = Calendar.getInstance();

        new GenerateSalesSumReport().execute(calfrom,calto,user,refer,viewonly);

//        switch(reportType){
//            case 0:
//                new GenerateSalesSumReport().execute(calfrom,calto,user,refer,viewonly);
//                break;
//            case 1:
//                new GeneratePLUReport().execute(calfrom,calto,user,refer,viewonly,false);
//                break;
//            case 2:
//                new GeneratePLUReport().execute(calfrom,calto,user,refer,viewonly,true);
//                break;
////            case 3:
////                new GenerateDeptReport().execute(calfrom,calto,user,refer,viewonly);
////                break;
//            case 4:
//                new GenerateUserLogReport().execute(calfrom,calto,user,viewonly);
//                break;
//
//        }

    }

    private final RectF onValueSelectedRectF = new RectF();

    @Override
    public void onValueSelected(Entry e, Highlight h) {

        if (e == null)
            return;

        RectF bounds = onValueSelectedRectF;
        binding.chart1.getBarBounds((BarEntry) e, bounds);
        MPPointF position = binding.chart1.getPosition(e, YAxis.AxisDependency.LEFT);

//        Log.i("bounds", bounds.toString());
//        Log.i("position", position.toString());
//
//        Log.i("x-index",
//                "low: " + binding.chart1.getLowestVisibleX() + ", high: "
//                        + binding.chart1.getHighestVisibleX());

        MPPointF.recycleInstance(position);
    }

    private void setData(int first,int count, float range) {

        ArrayList<BarEntry> values = new ArrayList<BarEntry>();
        for (int i = first ; i < count; i++) {
            //float val = (float) (Math.random() * (range + 1));
            float val = (float) (Math.random() * (range));
            values.add(new BarEntry(i, val));
        }

        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        BarDataSet set1 = new BarDataSet(values, "Hourly Sales");
        dataSets.add(set1);

        BarData data = new BarData(dataSets);
        data.setValueTextSize(10f);
        data.setValueTypeface(tfLight);
        data.setBarWidth(0.9f);

        binding.chart1.setData(data);

//        for (int i = (int) start; i < start + count; i++) {
//            float val = (float) (Math.random() * (range + 1));
//
//            if (Math.random() * 100 < 25) {
//                values.add(new BarEntry(i, val, getResources().getDrawable(R.drawable.setting)));
//            } else {
//                values.add(new BarEntry(i, val));
//            }
//        }

//        //BarDataSet set1;
//
//        if (chart.getData() != null &&
//                chart.getData().getDataSetCount() > 0) {
//            //set1 = (BarDataSet) chart.getData().getDataSetByIndex(0);
//            //set1.setValues(values);
//            chart.getData().notifyDataChanged();
//            chart.notifyDataSetChanged();
//
//        } else {
//            //set1 = new BarDataSet(values, "The year 2017");
//
//            //set1.setDrawIcons(false);
////
////            int startColor1 = ContextCompat.getColor(this, android.R.color.holo_orange_light);
////            int startColor2 = ContextCompat.getColor(this, android.R.color.holo_blue_light);
////            int startColor3 = ContextCompat.getColor(this, android.R.color.holo_orange_light);
////            int startColor4 = ContextCompat.getColor(this, android.R.color.holo_green_light);
////            int startColor5 = ContextCompat.getColor(this, android.R.color.holo_red_light);
////            int endColor1 = ContextCompat.getColor(this, android.R.color.holo_blue_dark);
////            int endColor2 = ContextCompat.getColor(this, android.R.color.holo_purple);
////            int endColor3 = ContextCompat.getColor(this, android.R.color.holo_green_dark);
////            int endColor4 = ContextCompat.getColor(this, android.R.color.holo_red_dark);
////            int endColor5 = ContextCompat.getColor(this, android.R.color.holo_orange_dark);
//
////            List<Fill> gradientFills = new ArrayList<Fill>();
////            gradientFills.add(new Fill(startColor1, endColor1));
////            gradientFills.add(new Fill(startColor2, endColor2));
////            gradientFills.add(new Fill(startColor3, endColor3));
////            gradientFills.add(new Fill(startColor4, endColor4));
////            gradientFills.add(new Fill(startColor5, endColor5));
////
////            //set1.setFills(gradientFills);
//
//            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
//            //dataSets.add(set1);
//
//            BarData data = new BarData(dataSets);
//            data.setValueTextSize(10f);
//            data.setValueTypeface(tfLight);
//            data.setBarWidth(0.9f);
//
//            chart.setData(data);
//        }
    }

    @Override
    public void onNothingSelected() {

    }


    private class GenerateSalesSumReport extends AsyncTask<Object, String, Object> {

        @Override
        protected void onPreExecute(){
            if(dlg!=null)dlg.show();
        }

        @SuppressWarnings("unchecked")
        @Override
        protected Object doInBackground(Object... params) {


            Calendar fromdate = (Calendar)params[0];
            Calendar todate = (Calendar)params[1];
            UserData user = null;
            if(params[2]!=null){
                user = (UserData)params[2];
            }
            ReferData refer = null;
            if(params[3]!=null){
                refer = (ReferData)params[3];
                Log.e("ASDAD",refer.getName());

            }
            boolean viewonly = (Boolean)params[4];

            List<Object[]> daysumdata = new ArrayList<Object[]>();
            long from_tick = (fromdate.getTimeInMillis()/86400000L);
            long to_tick = todate.getTimeInMillis()/86400000L;

            Calendar daycount = Calendar.getInstance();
            daycount.set(fromdate.get(Calendar.YEAR), fromdate.get(Calendar.MONTH), fromdate.get(Calendar.DATE), 0,0,0);
            daycount.set(Calendar.MILLISECOND, 0);
            int day =(int) (to_tick - from_tick);
            if(day<=0)day=1;

            int uid = -1;
            int rid = -2;
            if(user!=null){
                uid = user.getID();
            }
            if(refer!=null){
                rid = refer.getID();
            }

            for(int i=0;i<day;i++){
                Calendar dayend = Calendar.getInstance();
                dayend.set(daycount.get(Calendar.YEAR), daycount.get(Calendar.MONTH), daycount.get(Calendar.DATE), 23, 59, 59);
                dayend.set(Calendar.MILLISECOND, 999);
                //Cursor bill = DBFunc.Query("SELECT (SELECT BillNo FROM Bill WHERE CloseDateTime BETWEEN "+daycount.getTimeInMillis()+" AND "+dayend.getTimeInMillis()+" ORDER BY BillNo ASC LIMIT 1) AS 'BillStart', (SELECT BillNo FROM Bill WHERE CloseDateTime BETWEEN "+daycount.getTimeInMillis()+" AND "+dayend.getTimeInMillis()+" ORDER BY BillNo DESC LIMIT 1) AS 'BillEnd'", false);

                Object[] data = null;

                data = CommonReport.CountSalesTotal(daycount.getTimeInMillis(),dayend.getTimeInMillis(), uid, rid);


                daysumdata.add(new Object[]{daycount.getTimeInMillis(),data});


                daycount.add(Calendar.DATE, 1);
            }

            Object doc = null;

            if(viewonly){
                doc = new ArrayList<Bitmap>();
            }else{
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    doc = new PdfDocument();
                }
            }
            int height = 0;
            int width = 0;
            PrintAttributes.MediaSize mediasize = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                mediasize = PrintAttributes.MediaSize.ISO_A4;
                height = mediasize.getWidthMils()/1000 * (int)(72*scale);
                width = mediasize.getHeightMils()/1000 * (int)(72*scale);
            }

            Paint p = new Paint();
            p.setTextSize(8*scale);
            p.setTypeface(normaltext);
            float txtheight = 9*scale;

            float pagefillheight = height-(70*scale);
            //float pagefillwidth = width - 40;
            String[] header = new String[]{
                    "Date         ",
                    " Bill Paid",
                    "  Bill Cancel",
                    "   Qty Sold",
                    "      Amt Nett",
                    "     Amt Gross",
                    "     Tax Total",
                    "      Discount",
                    "      Round",
                    "     Surcharge",
                    "  Qty Cancel",
                    "    Amt Cancel"};

            long totalbill = 0;
            long totalqty = 0;
            long totalbillcancel = 0;
            double totalamtnett = 0;
            double totalamtgross = 0;
            double totaltax = 0;
            double totaldisc = 0;
            double totalround = 0;
            double totalsurcharge = 0;
            long totalqtycancel = 0;
            double totalamtgrosscancel = 0;
            String[] tmp = new String[2+daysumdata.size()];
            for(int i=0;i<tmp.length-2;i++){
                String[] data = new String[header.length];
                Object[] summaryday = daysumdata.get(i);


                data[0] = String.format("%1$-"+(header[0].length())+"s", formatter.format(new Date((Long)summaryday[0])));
                if(summaryday[1]==null){
                    data[1] = String.format("%1$"+header[1].length()+"s", "0");
                    data[2] = String.format("%1$"+header[2].length()+"s", "0");
                    data[3] = String.format("%1$"+header[3].length()+"s", "0");
                    data[4] = String.format("%1$"+header[4].length()+"s", String.format("%,.2f",0d));
                    data[5] = String.format("%1$"+header[5].length()+"s", String.format("%,.2f",0d));
                    data[6] = String.format("%1$"+header[6].length()+"s", String.format("%,.2f",0d));
                    data[7] = String.format("%1$"+header[7].length()+"s", String.format("%,.2f",0d));
                    data[8] = String.format("%1$"+header[8].length()+"s", String.format("%,.2f",0d));
                    data[9] = String.format("%1$"+header[9].length()+"s", String.format("%,.2f",0d));
                    data[10] = String.format("%1$"+header[10].length()+"s", "0");
                    data[11] = String.format("%1$"+header[11].length()+"s", String.format("%,.2f",0d));
                }else{
                    Object[] salesdata = (Object[])summaryday[1];
                    //0        , 1       , 2        , 3       , 4    , 5              , 6      , 7        , 8,             , 9             , 10             , 11            , 12
                    //totalbill, totalqty, totalnett, totaltax, round, totalamtcollect, discamt, surchgamt, totalbillcancel, totalqtycancel, totalcancelnett, totalcancelamt, totaltaxcancel
                    totalbill+=(Integer)salesdata[0];
                    totalqty+=(Integer)salesdata[1];
                    totalamtnett+=(Double)salesdata[2];
                    totaltax+=(Double)salesdata[3];
                    totalround+=(Double)salesdata[4];
                    totalamtgross+=(Double)salesdata[5];
                    totaldisc+=(Double)salesdata[6];
                    totalsurcharge+=(Double)salesdata[7];
                    totalbillcancel+=(Integer)salesdata[8];
                    totalqtycancel+=(Integer)salesdata[9];
                    totalamtgrosscancel+=(Double)salesdata[11];

                    String tmpstr = String.format("%1$"+header[1].length()+"s", ""+(Integer)salesdata[0]);
                    data[1] = tmpstr.substring(0,Math.min(tmpstr.length(), header[1].length()));
                    tmpstr = String.format("%1$"+header[2].length()+"s", ""+(Integer)salesdata[8]);
                    data[2] = tmpstr.substring(0,Math.min(tmpstr.length(), header[2].length()));
                    tmpstr = String.format("%1$"+header[3].length()+"s", ""+(Integer)salesdata[1]);
                    data[3] = tmpstr.substring(0,Math.min(tmpstr.length(), header[3].length()));
                    tmpstr = String.format("%1$"+header[4].length()+"s", String.format("%,.2f",(Double)salesdata[2]));
                    data[4] = tmpstr.substring(0,Math.min(tmpstr.length(), header[4].length()));
                    tmpstr = String.format("%1$"+header[5].length()+"s", String.format("%,.2f",(Double)salesdata[5]));
                    data[5] = tmpstr.substring(0,Math.min(tmpstr.length(), header[5].length()));
                    tmpstr = String.format("%1$"+header[6].length()+"s", String.format("%,.2f",(Double)salesdata[3]));
                    data[6] = tmpstr.substring(0,Math.min(tmpstr.length(), header[6].length()));
                    tmpstr = String.format("%1$"+header[7].length()+"s", String.format("%,.2f",(Double)salesdata[6]));
                    data[7] = tmpstr.substring(0,Math.min(tmpstr.length(), header[7].length()));;
                    tmpstr = String.format("%1$"+header[8].length()+"s", String.format("%,.2f",(Double)salesdata[4]));
                    data[8] = tmpstr.substring(0,Math.min(tmpstr.length(), header[8].length()));;
                    tmpstr = String.format("%1$"+header[9].length()+"s", String.format("%,.2f",(Double)salesdata[7]));
                    data[9] = tmpstr.substring(0,Math.min(tmpstr.length(), header[9].length()));
                    tmpstr = String.format("%1$"+header[10].length()+"s", ""+(Integer)salesdata[9]);
                    data[10] = tmpstr.substring(0,Math.min(tmpstr.length(), header[10].length()));
                    tmpstr = String.format("%1$"+header[11].length()+"s", String.format("%,.2f",(Double)salesdata[11]));
                    data[11] = tmpstr.substring(0,Math.min(tmpstr.length(), header[11].length()));
                }

                tmp[i] = "";
                for(int j=0;j<data.length;j++){
                    tmp[i]+=data[j];
                }
            }

            tmp[tmp.length-2] = "";
            tmp[tmp.length-1] = "";
            String[] data = new String[header.length];
            data[0] = String.format("%1$-"+(header[0].length())+"s", "TOTAL");

            String tmpstr = String.format("%1$"+header[1].length()+"s", ""+totalbill);
            data[1] = tmpstr.substring(0,Math.min(tmpstr.length(), header[1].length()));

            tmpstr = String.format("%1$"+header[2].length()+"s", ""+totalbillcancel);
            data[2] = tmpstr.substring(0,Math.min(tmpstr.length(), header[2].length()));

            tmpstr = String.format("%1$"+header[3].length()+"s", ""+totalqty);
            data[3] = tmpstr.substring(0,Math.min(tmpstr.length(), header[3].length()));

            tmpstr = String.format("%1$"+header[4].length()+"s", ""+String.format("%,.2f",totalamtnett));
            data[4] = tmpstr.substring(0,Math.min(tmpstr.length(), header[4].length()));

            tmpstr = String.format("%1$"+header[5].length()+"s", ""+String.format("%,.2f",totalamtgross));
            data[5] = tmpstr.substring(0,Math.min(tmpstr.length(), header[5].length()));
            tmpstr = String.format("%1$"+header[6].length()+"s", ""+String.format("%,.2f",totaltax));
            data[6] = tmpstr.substring(0,Math.min(tmpstr.length(), header[6].length()));
            tmpstr = String.format("%1$"+header[7].length()+"s", ""+String.format("%,.2f",totaldisc));
            data[7] = tmpstr.substring(0,Math.min(tmpstr.length(), header[7].length()));
            tmpstr = String.format("%1$"+header[8].length()+"s", ""+String.format("%,.2f",totalround));
            data[8] = tmpstr.substring(0,Math.min(tmpstr.length(), header[8].length()));
            tmpstr = String.format("%1$"+header[9].length()+"s", ""+String.format("%,.2f",totalsurcharge));
            data[9] = tmpstr.substring(0,Math.min(tmpstr.length(), header[9].length()));
            tmpstr = String.format("%1$"+header[10].length()+"s", ""+totalqtycancel);
            data[10] = tmpstr.substring(0,Math.min(tmpstr.length(), header[10].length()));
            tmpstr = String.format("%1$"+header[11].length()+"s", ""+String.format("%,.2f",totalamtgrosscancel));
            data[11] = tmpstr.substring(0,Math.min(tmpstr.length(), header[11].length()));
            for(int j=0;j<data.length;j++){
                tmp[tmp.length-1]+=data[j];
            }
            int pagemax = (int)((float)(txtheight*tmp.length) / pagefillheight);
            int itemperpage = (int)(pagefillheight/txtheight);
            if(((int)((float)(txtheight*tmp.length)) % pagefillheight)>0){
                pagemax++;
            }

            for(int i=0;i<pagemax;i++){
                int nextcount = itemperpage;
                if(tmp.length-(i)*itemperpage <itemperpage){
                    nextcount = tmp.length-(i)*itemperpage;
                }
                String[] tmpdata = new String[nextcount];
                System.arraycopy(tmp, i*itemperpage, tmpdata, 0, nextcount);
                if(viewonly){
                    Bitmap b = Bitmap.createBitmap(width,height, Bitmap.Config.RGB_565);
                    Canvas c = new Canvas(b);
                    Paint paint = new Paint();
                    paint.setColor(Color.WHITE);
                    c.drawRect(0, 0, width, height, paint);
                    DrawReportPage(c,fromdate, todate,1+i,pagemax, header, tmpdata,"Sales Summary Report", user,refer);
                    //DrawReportDailySubpage(c,fromdate, todate,1+i,pagemax, header, tmpdata);
                    ((ArrayList<Bitmap>)doc).add(b);
                }else{
                    PdfDocument.PageInfo pi = null;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                        pi = new PdfDocument.PageInfo.Builder(width,height, 1+i).create();
                        PdfDocument.Page page = ((PdfDocument)doc).startPage(pi);
                        DrawReportPage(page.getCanvas(),fromdate, todate,1+i,pagemax, header, tmpdata,"Sales Summary Report", user,refer);
                        //DrawReportDailySubpage(page.getCanvas(),fromdate, todate,1+i,pagemax, header, tmpdata);
                        ((PdfDocument)doc).finishPage(page);
                    }
                }
            }

            return doc;
        }

        @Override
        protected void onProgressUpdate(String... params){}

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected void onPostExecute(Object output){
            ReportResult(output);

        }
    }

    private void DrawReportPage(Canvas c, Calendar fromdate, Calendar todate, int pagenum, int pagemax, String[] header,  String[] item, String title, UserData user, ReferData refer){

        Paint paint = new Paint();
        paint.setStrokeWidth(0);
        paint.setTextSize(10*scale);
        paint.setTypeface(Typeface.DEFAULT_BOLD);

        String tmpstr = MessageFormat.format("Date: {0} \u2192 {1}",formatter.format(fromdate.getTime()), formatter.format(todate.getTime()));
        c.drawText(tmpstr, 25*scale, 50*scale, paint);

        if(user!=null){

            tmpstr = user.getName();
            if(tmpstr.length()>32){
                tmpstr = tmpstr.substring(0, 32)+"...";
            }
            tmpstr = MessageFormat.format("User: {0}", tmpstr);
            c.drawText(tmpstr, 300*scale, 50*scale, paint);
        }

        if(refer!=null){
            if(refer.getID()!=-2){
                tmpstr = refer.getName();
                if(tmpstr.length()>32){
                    tmpstr = tmpstr.substring(0, 32)+"...";
                }
                tmpstr = MessageFormat.format("Refer: {0}", tmpstr);
                c.drawText(tmpstr, 500*scale, 50*scale, paint);
            }
        }

        tmpstr = "Page: "+pagenum+"/"+pagemax;
        c.drawText(tmpstr, c.getWidth()-(20*scale)-paint.measureText(tmpstr), 50*scale, paint);



        paint.setColor(Color.BLACK);
        paint.setTextSize(30*scale);
        tmpstr = title;
        c.drawText(tmpstr, c.getWidth()/2 - paint.measureText(tmpstr)/2,paint.getTextSize(), paint);
        paint.setStrokeWidth(2);


        c.drawLine(10*scale, 35*scale, c.getWidth()-(10*scale), 35*scale, paint);
        paint.setTypeface(boldtext);
        paint.setTextSize(8*scale);
        int widthcount = (int)(10*scale);
        for(int i=0;i<header.length;i++){
            tmpstr = header[i];
            c.drawText(tmpstr, widthcount, 70*scale, paint);
            widthcount += paint.measureText(tmpstr);
        }

        paint.setStrokeWidth(2);

        c.drawLine(10*scale, 57*scale, c.getWidth()-(10*scale), 57*scale, paint);
        c.drawLine(10*scale, 77*scale, c.getWidth()-(10*scale), 77*scale, paint);



        for(int i=0;i<item.length;i++){
            //if(i==item.length-2 && pagenum == pagemax){
            if(item[i].isEmpty()){
                paint.setStrokeWidth(1);
                c.drawLine(10*scale, 87*scale+(i*(paint.getTextSize()+1))-5, c.getWidth()-(10*scale), 87*scale+(i*(paint.getTextSize()+1))-5, paint);
            }
            if((i-1)>=0 && i+1<item.length-1){
                if(item[i-1].isEmpty() && item[i+1].isEmpty()){
                    paint.setTypeface(boldtext);
                }else{
                    paint.setTypeface(normaltext);
                }
            }else{
                paint.setTypeface(normaltext);
            }

            if(i==item.length-1 && pagenum == pagemax){
                paint.setTypeface(boldtext);
            }
            c.drawText(item[i], 10*scale, 88*scale+(i*(paint.getTextSize()+1)), paint);
        }

        paint.setTypeface(Typeface.DEFAULT);

        paint.setStrokeWidth(2);
        c.drawLine(10*scale, c.getHeight()-22*scale, c.getWidth()-10*scale, c.getHeight()-22*scale, paint);
        paint.setStrokeWidth(1);

        paint.setTypeface(Typeface.DEFAULT);

        paint.setTextSize(8*scale);

        tmpstr = Allocator.cashierName;
        if(tmpstr.length()>32){
            tmpstr = Allocator.cashierName.substring(0, 20) + "...";
        }
        //fmt = new SimpleDateFormat(Constraints.dateYMD);
        fmttime = new SimpleDateFormat("HH:mm:ss");
        tmpstr = MessageFormat.format("Report Generated On: {0}  {1}  (By: {2})",
                formatter.format(reportgentime.getTime()), fmttime.format(reportgentime.getTime()), tmpstr);
        //tmpstr = "Report Generated On: "+fmt.format(reportgentime.getTime())+"  "+fmttime.format(reportgentime.getTime());
        c.drawText(tmpstr, 20*scale,c.getHeight()-10*scale, paint);

//        tmpstr = regID;
//        c.drawText(regID, c.getWidth() - (20*scale + paint.measureText(tmpstr)) ,c.getHeight()-10*scale, paint);
//
//        paint.setTextSize(10*scale);
//        tmpstr = "Powered By PIXIE POS";
//        c.drawText(tmpstr, c.getWidth()/2 - paint.measureText(tmpstr)/2,c.getHeight()-10*scale, paint);
    }


//    private class GeneratePLUReport extends AsyncTask<Object, String, Object>{
//
//
//        @Override
//        protected void onPreExecute(){
//            if(dlg!=null)dlg.show();
//        }
//
//        @SuppressWarnings("unchecked")
//        @Override
//        protected Object doInBackground(Object... params) {
//
//            Calendar fromdate = (Calendar)params[0];
//            Calendar todate = (Calendar)params[1];
//            UserData user = null;
//            if(params[2]!=null){
//                user = (UserData)params[2];
//            }
//            ReferData refer = null;
//            if(params[3]!=null){
//                refer = (ReferData)params[3];
//            }
//            boolean viewonly = (Boolean)params[4];
//            boolean groupdept = (Boolean)params[5];
//
//
//
//            List<PLUSalesData> plu = null;
//
//            int uid = -1;
//            int rid = -2;
//            if(user!=null){
//                uid = user.getID();
//            }
//            if(refer!=null){
//                rid = refer.getID();
//            }
//
//
//            plu = CommonReport.CountPLUSalesTotal(fromdate.getTimeInMillis(),todate.getTimeInMillis(), groupdept, uid, rid);
//
//
//            if(plu==null){
//                return null;
//            }
//
//            if(groupdept){//group by department
//                Collections.sort(plu, new Comparator<PLUSalesData>(){
//                    @Override
//                    public int compare(PLUSalesData a, PLUSalesData b) {
//                        if(a.pluid==b.pluid){
//                            return 0;
//                        }
//                        return a.pluid<b.pluid? -1:1;
//                    }
//                });
//                Collections.sort(plu, new Comparator<PLUSalesData>(){
//                    @Override
//                    public int compare(PLUSalesData a, PLUSalesData b) {
//                        if(a.deptid==b.deptid){
//                            return 0;
//                        }
//                        return a.deptid<b.deptid? -1:1;
//                    }
//                });
//
//            }else{
//                Collections.sort(plu, new Comparator<PLUSalesData>(){
//                    @Override
//                    public int compare(PLUSalesData a, PLUSalesData b) {
//                        if(a.pluid==b.pluid){
//                            return 0;
//
//                        }
//                        return a.pluid<b.pluid? -1:1;
//                    }
//                });
//            }
//
//
//
//
//
//
//            Object doc = null;
//
//            if(viewonly){
//                doc = new ArrayList<Bitmap>();
//            }else{
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                    doc = new PdfDocument();
//                }
//            }
//            PrintAttributes.MediaSize mediasize = null;
//            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
//                mediasize = PrintAttributes.MediaSize.ISO_A4;
//            }
//            int height = 0;
//            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
//                height = mediasize.getWidthMils()/1000 * (int)(72*scale);
//            }
//            int width = 0;
//            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
//                width = mediasize.getHeightMils()/1000 * (int)(72*scale);
//            }
//
//            Paint p = new Paint();
//            p.setTextSize(8*scale);
//            p.setTypeface(normaltext);
//            float txtheight = 9*scale;
//
//            float pagefillheight = height-(70*scale);
//            String[] header = new String[]{
//                    "PLU ID    ",
//                    "Name                ",
//                    "       Qty Sold",
//                    "            Amount",
//                    "          Amt Nett",
//                    "               Tax",
//                    "     Item Disc",
//                    "   Item Surchg",
//                    "         Cancel",
//                    "        Amt Cancel"};
//
//
//            List<String> datastr = new ArrayList<String>();
//
//            int deptID = -2;
//            String deptName = "";
//
//            if(groupdept){
//                double qtysold = 0;
//                //int qtysold = 0;
//                double amtsold = 0;
//                double amtnett = 0;
//                double tax = 0;
//                double disc = 0;
//                double srchg = 0;
//                double cancel = 0;
//                double amtcancel = 0;
//
//                double deptqtysold = 0;
//                //int deptqtysold = 0;
//                double deptamtsold = 0;
//                double deptamtnett = 0;
//                double depttax = 0;
//                double deptdisc = 0;
//                double deptsrchg = 0;
//                double deptcancel = 0;
//                double deptamtcancel = 0;
//
//
//                for(PLUSalesData data:plu){
//                    if(data.deptid != deptID || !data.deptname.equalsIgnoreCase(deptName)){
//                        if(deptID!=-2){
//                            datastr.add("");
//                            String tmp = "";
//                            String tmpstr = "";
//                            if(deptID==-1){
//                                tmpstr = String.format("%1$-"+(header[0].length()+header[1].length()-1)+"s ", "Total (No Dept)");
//                            }else{
//                                tmpstr = String.format("%1$-"+(header[0].length()+header[1].length()-1)+"s ", "Total "+deptName);
//                            }
//                            tmp += tmpstr.substring(0,Math.min(tmpstr.length(), (header[0].length()+header[1].length())));
//
//                            tmpstr = String.format("%1$"+header[2].length()+"s", String.format("%,.2f",deptqtysold));
//                            tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[2].length()));
//
//                            tmpstr = String.format("%1$"+header[3].length()+"s", String.format("%,.2f",deptamtsold));
//                            tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[3].length()));
//
//                            tmpstr = String.format("%1$"+header[4].length()+"s", String.format("%,.2f",deptamtnett));
//                            tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[4].length()));
//
//                            tmpstr = String.format("%1$"+header[5].length()+"s", String.format("%,.2f",depttax));
//                            tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[5].length()));
//
//                            tmpstr = String.format("%1$"+header[6].length()+"s", String.format("%,.2f",deptdisc));
//                            tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[6].length()));
//
//                            tmpstr = String.format("%1$"+header[7].length()+"s", String.format("%,.2f",deptsrchg));
//                            tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[7].length()));
//
//                            tmpstr = String.format("%1$"+header[8].length()+"s", String.format("%,.2f",deptcancel));
//                            tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[8].length()));
//
//                            tmpstr = String.format("%1$"+header[9].length()+"s", String.format("%,.2f",deptamtcancel));
//                            tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[9].length()));
//                            datastr.add(tmp);
//
//                        }
//
//                        datastr.add("");
//                        if(data.deptid==-1){
//                            datastr.add("(No Dept)");
//                        }else{
//                            datastr.add(data.deptname+"("+data.deptid+")");
//                        }
//                        datastr.add("");
//                        deptID = data.deptid;
//                        deptName = data.deptname;
//
//
//
//                        deptqtysold = 0;
//                        deptamtsold = 0;
//                        deptamtnett = 0;
//                        depttax = 0;
//                        deptdisc = 0;
//                        deptsrchg = 0;
//                        deptcancel = 0;
//                        deptamtcancel = 0;
//
//                    }
//
//
//
//                    String tmp = "";
//
//                    String tmpstr = String.format("%1$-"+header[0].length()+"s", data.pluid);
//                    tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[0].length()));
//
//                    tmpstr = String.format("%1$-"+(header[1].length()-1)+"s ", data.name);
//                    tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[1].length()));
//
//                    tmpstr = String.format("%1$"+header[2].length()+"s", String.format("%,.2f",data.paidqty));
//                    tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[2].length()));
//
//                    tmpstr = String.format("%1$"+header[3].length()+"s", String.format("%,.2f",data.paidamounttax));
//                    tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[3].length()));
//
//                    tmpstr = String.format("%1$"+header[4].length()+"s", String.format("%,.2f",data.paidamountnotax));
//                    tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[4].length()));
//
//                    tmpstr = String.format("%1$"+header[5].length()+"s", String.format("%,.2f",data.paidtax));
//                    tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[5].length()));
//
//                    tmpstr = String.format("%1$"+header[6].length()+"s", String.format("%,.2f",data.discamount));
//                    tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[6].length()));
//
//                    tmpstr = String.format("%1$"+header[7].length()+"s", String.format("%,.2f",data.surchgamt));
//                    tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[7].length()));
//
//                    tmpstr = String.format("%1$"+header[8].length()+"s", String.format("%,.2f",data.cancelqty));
//                    tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[8].length()));
//
//                    tmpstr = String.format("%1$"+header[9].length()+"s", String.format("%,.2f",data.cancelamtnotax));
//                    tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[9].length()));
//                    datastr.add(tmp);
//
//                    qtysold += data.paidqty;
//                    amtsold += data.paidamounttax;
//                    amtnett += data.paidamountnotax;
//                    tax += data.paidtax;
//                    disc += data.discamount;
//                    srchg += data.surchgamt;
//                    cancel += data.cancelqty;
//                    amtcancel += data.cancelamtnotax;
//
//                    deptqtysold += data.paidqty;
//                    deptamtsold += data.paidamounttax;
//                    deptamtnett += data.paidamountnotax;
//                    depttax += data.paidtax;
//                    deptdisc += data.discamount;
//                    deptsrchg += data.surchgamt;
//                    deptcancel += data.cancelqty;
//                    deptamtcancel += data.cancelamtnotax;
//                }
//
//
//                datastr.add("");
//
//                String tmp = "";
//                String tmpstr = "";
//
//                if(deptID!=-2){
//                    if(deptID==-1){
//                        tmpstr = String.format("%1$-"+(header[0].length()+header[1].length()-1)+"s ", "Total (No Dept)");
//                    }else{
//                        tmpstr = String.format("%1$-"+(header[0].length()+header[1].length()-1)+"s ", "Total "+deptName);
//                    }
//                    tmp += tmpstr.substring(0,Math.min(tmpstr.length(), (header[0].length()+header[1].length())));
//
//                    tmpstr = String.format("%1$"+header[2].length()+"s", String.format("%,.2f",deptqtysold));
//                    tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[2].length()));
//
//                    tmpstr = String.format("%1$"+header[3].length()+"s", String.format("%,.2f",deptamtsold));
//                    tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[3].length()));
//
//                    tmpstr = String.format("%1$"+header[4].length()+"s", String.format("%,.2f",deptamtnett));
//                    tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[4].length()));
//
//                    tmpstr = String.format("%1$"+header[5].length()+"s", String.format("%,.2f",depttax));
//                    tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[5].length()));
//
//                    tmpstr = String.format("%1$"+header[6].length()+"s", String.format("%,.2f",deptdisc));
//                    tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[6].length()));
//
//                    tmpstr = String.format("%1$"+header[7].length()+"s", String.format("%,.2f",deptsrchg));
//                    tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[7].length()));
//
//                    tmpstr = String.format("%1$"+header[8].length()+"s", String.format("%,.2f",deptcancel));
//                    tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[8].length()));
//
//                    tmpstr = String.format("%1$"+header[9].length()+"s", String.format("%,.2f",deptamtcancel));
//                    tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[9].length()));
//                    datastr.add(tmp);
//                    datastr.add("");
//                }
//
//                tmp = "";
//
//                tmpstr = String.format("%1$-"+header[0].length()+"s", "Total");
//                tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[0].length()));
//
//                tmpstr = String.format("%1$-"+header[1].length()+"s", " ");
//                tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[1].length()));
//
//                tmpstr = String.format("%1$"+header[2].length()+"s", String.format("%,.2f",qtysold));
//                //tmpstr = String.format("%1$"+header[2].length()+"s",qtysold);
//                tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[2].length()));
//
//                tmpstr = String.format("%1$"+header[3].length()+"s", String.format("%,.2f",amtsold));
//                tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[3].length()));
//
//                tmpstr = String.format("%1$"+header[4].length()+"s", String.format("%,.2f",amtnett));
//                tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[4].length()));
//
//                tmpstr = String.format("%1$"+header[5].length()+"s", String.format("%,.2f",tax));
//                tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[5].length()));
//
//                tmpstr = String.format("%1$"+header[6].length()+"s", String.format("%,.2f",disc));
//                tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[6].length()));
//
//                tmpstr = String.format("%1$"+header[7].length()+"s", String.format("%,.2f",srchg));
//                tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[7].length()));
//
//                tmpstr = String.format("%1$"+header[8].length()+"s", String.format("%,.2f",cancel));
//                tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[8].length()));
//
//                tmpstr = String.format("%1$"+header[9].length()+"s", String.format("%,.2f",amtcancel));
//                tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[9].length()));
//                datastr.add(tmp);
//
//            }else{
//                double qtysold = 0;
//                //int qtysold = 0;
//                double amtsold = 0;
//                double amtnett = 0;
//                double tax = 0;
//                double disc = 0;
//                double srchg = 0;
//                double cancel = 0;
//                double amtcancel = 0;
//
//                for(PLUSalesData data:plu){
//                    String tmp = "";
//
//                    String tmpstr = String.format("%1$-"+header[0].length()+"s", data.pluid);
//                    tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[0].length()));
//
//                    tmpstr = String.format("%1$-"+(header[1].length()-1)+"s ", data.name);
//                    tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[1].length()));
//
//                    tmpstr = String.format("%1$"+header[2].length()+"s", String.format("%,.2f",data.paidqty));
//                    tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[2].length()));
//
//                    tmpstr = String.format("%1$"+header[3].length()+"s", String.format("%,.2f",data.paidamounttax));
//                    tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[3].length()));
//
//                    tmpstr = String.format("%1$"+header[4].length()+"s", String.format("%,.2f",data.paidamountnotax));
//                    tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[4].length()));
//
//                    tmpstr = String.format("%1$"+header[5].length()+"s", String.format("%,.2f",data.paidtax));
//                    tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[5].length()));
//
//                    tmpstr = String.format("%1$"+header[6].length()+"s", String.format("%,.2f",data.discamount));
//                    tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[6].length()));
//
//                    tmpstr = String.format("%1$"+header[7].length()+"s", String.format("%,.2f",data.surchgamt));
//                    tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[7].length()));
//
//                    tmpstr = String.format("%1$"+header[8].length()+"s", String.format("%,.2f",data.cancelqty));
//                    tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[8].length()));
//
//                    tmpstr = String.format("%1$"+header[9].length()+"s", String.format("%,.2f",data.cancelamtnotax));
//                    tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[9].length()));
//                    datastr.add(tmp);
//
//
//                    qtysold += data.paidqty;
//                    amtsold += data.paidamounttax;
//                    amtnett += data.paidamountnotax;
//                    tax += data.paidtax;
//                    disc += data.discamount;
//                    srchg += data.surchgamt;
//                    cancel += data.cancelqty;
//                    amtcancel += data.cancelamtnotax;
//                }
//
//                datastr.add("");
//
//                String tmp = "";
//
//                String tmpstr = String.format("%1$-"+header[0].length()+"s", "Total");
//                tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[0].length()));
//
//                tmpstr = String.format("%1$-"+header[1].length()+"s", " ");
//                tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[1].length()));
//
//                tmpstr = String.format("%1$"+header[2].length()+"s", String.format("%,.2f",qtysold));
//                //tmpstr = String.format("%1$"+header[2].length()+"s", qtysold);
//                tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[2].length()));
//
//                tmpstr = String.format("%1$"+header[3].length()+"s", String.format("%,.2f",amtsold));
//                tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[3].length()));
//
//                tmpstr = String.format("%1$"+header[4].length()+"s", String.format("%,.2f",amtnett));
//                tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[4].length()));
//
//                tmpstr = String.format("%1$"+header[5].length()+"s", String.format("%,.2f",tax));
//                tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[5].length()));
//
//                tmpstr = String.format("%1$"+header[6].length()+"s", String.format("%,.2f",disc));
//                tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[6].length()));
//
//                tmpstr = String.format("%1$"+header[7].length()+"s", String.format("%,.2f",srchg));
//                tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[7].length()));
//
//                tmpstr = String.format("%1$"+header[8].length()+"s", String.format("%,.2f",cancel));
//                tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[8].length()));
//
//                tmpstr = String.format("%1$"+header[9].length()+"s", String.format("%,.2f",amtcancel));
//                tmp += tmpstr.substring(0,Math.min(tmpstr.length(), header[9].length()));
//                datastr.add(tmp);
//            }
//
//
//            String[] tmp = datastr.toArray(new String[0]);
//
//            int pagemax = (int)((float)(txtheight*tmp.length) / pagefillheight);
//            int itemperpage = (int)(pagefillheight/txtheight);
//            if(((int)((float)(txtheight*tmp.length)) % pagefillheight)>0){
//                pagemax++;
//            }
//
//            for(int i=0;i<pagemax;i++){
//                int nextcount = itemperpage;
//                if(tmp.length-(i)*itemperpage <itemperpage){
//                    nextcount = tmp.length-(i)*itemperpage;
//                }
//                String[] tmpdata = new String[nextcount];
//                System.arraycopy(tmp, i*itemperpage, tmpdata, 0, nextcount);
//                if(viewonly){
//                    Bitmap b = Bitmap.createBitmap(width,height, Bitmap.Config.RGB_565);
//                    Canvas c = new Canvas(b);
//                    Paint paint = new Paint();
//                    paint.setColor(Color.WHITE);
//                    c.drawRect(0, 0, width, height, paint);
//                    if(groupdept){
//                        DrawReportPage(c,fromdate, todate,1+i,pagemax, header, tmpdata,"PLU Group By Dept", user,refer);
//                    }else{
//                        DrawReportPage(c,fromdate, todate,1+i,pagemax, header, tmpdata,"Summary PLU Report", user,refer);
//                    }
//                    ((ArrayList<Bitmap>)doc).add(b);
//                }else{
//                    PdfDocument.PageInfo pi = null;
//                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
//                        pi = new PdfDocument.PageInfo.Builder(width,height, 1+i).create();
//                        PdfDocument.Page page = ((PdfDocument)doc).startPage(pi);
//                        if(groupdept){
//                            DrawReportPage(page.getCanvas(),fromdate, todate,1+i,pagemax, header, tmpdata,"PLU Group By Dept", user,refer);
//                        }else{
//                            DrawReportPage(page.getCanvas(),fromdate, todate,1+i,pagemax, header, tmpdata,"Summary PLU Report", user,refer);
//                        }
//                        ((PdfDocument)doc).finishPage(page);
//                    }
//                }
//            }
//
//            return doc;
//        }
//
//        @Override
//        protected void onProgressUpdate(String... params){}
//
//        @Override
//        protected void onPostExecute(Object output){
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                ReportResult(output);
//            }
//
//        }
//    }

    private class GenerateUserLogReport extends AsyncTask<Object, String, Object>{


        @Override
        protected void onPreExecute(){
            if(dlg!=null)dlg.show();
        }

        @SuppressWarnings("unchecked")
        @Override
        protected Object doInBackground(Object... params) {

            Calendar fromdate = (Calendar)params[0];
            Calendar todate = (Calendar)params[1];
            UserData user = null;
            if(params[2]!=null){
                user = (UserData)params[2];
            }
            boolean viewonly = (Boolean)params[3];


            String sql = "SELECT DISTINCT(user_id) FROM userlog WHERE time BETWEEN "+fromdate.getTimeInMillis()+" AND "+todate.getTimeInMillis();

            if(user!=null){
                sql += " AND user_id = "+user.id+" AND name = '"+DBFunc.PurifyString(user.name)+"'";
            }

            sql += " ORDER BY user_id";


            Cursor c_user = DBFunc.Query(sql, false);
            if(c_user==null){//DB Error
                return null;
            }

            List<Integer> _tmpuserid = new ArrayList<Integer>();

            while(c_user.moveToNext()){
                _tmpuserid.add(c_user.getInt(0));
            }

            c_user.close();

            //Map<Integer,List<String>> data = new HashMap<Integer,List<String>>();
            List<UserLogData> data = new ArrayList<UserLogData>();
            for(int id : _tmpuserid){
                sql = "SELECT DISTINCT(name) FROM userlog WHERE user_id = "+id +" AND time BETWEEN "+fromdate.getTimeInMillis()+" AND "+todate.getTimeInMillis();

                if(user!=null){
                    sql += " AND name = '"+DBFunc.PurifyString(user.name)+"'";
                }

                Cursor _tmp = DBFunc.Query(sql, false);//get list of name





                if(_tmp==null){
                    return null;
                }

                while(_tmp.moveToNext()){
                    data.add(new UserLogData(id,_tmp.getString(0)));
                }

                _tmp.close();
            }

            for(UserLogData l : data){
                Cursor _tmp = DBFunc.Query("SELECT time,info FROM userlog WHERE time BETWEEN "+fromdate.getTimeInMillis()+" AND "+todate.getTimeInMillis()+" AND user_id = "+l.getID()+" AND name = '"+DBFunc.PurifyString(l.getName())+"' ORDER BY time ASC", false);

                if(_tmp==null){
                    return null;
                }

                while(_tmp.moveToNext()){
                    l.getLog().put(new Date(_tmp.getLong(0)), _tmp.getString(1));
                }
                _tmp.close();
            }



            Object doc = null;

            if(viewonly){
                doc = new ArrayList<Bitmap>();
            }else{
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    doc = new PdfDocument();
                }
            }
            PrintAttributes.MediaSize mediasize = null;
            int height = 0;
            int width = 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                mediasize = PrintAttributes.MediaSize.ISO_A4;
                height = mediasize.getWidthMils()/1000 * (int)(72*scale);
                width = mediasize.getHeightMils()/1000 * (int)(72*scale);
            }

            Paint p = new Paint();
            p.setTextSize(8*scale);
            p.setTypeface(normaltext);
            float txtheight = 9*scale;

            float pagefillheight = height-(70*scale);
            String[] header = new String[]{
                    //yyyy/mm/dd hh:mm.ss
                    "Date Time               ",
                    "Information"};



            List<String> datastr = new ArrayList<String>();

            for(UserLogData l : data){
                datastr.add("");
                datastr.add(l.getName());
                datastr.add("");

                Date[] adate = l.getLog().keySet().toArray(new Date[0]);
                Arrays.sort(adate);

                for(Date date : adate){
                    //String tmp = fmt.format(date)+" "+fmttime.format(date);
                    String tmp = String.format("%-20s", formatter.format(date) + "  " + fmttime.format(date));
                    if(tmp.length()>24){
                        tmp = tmp.substring(0, 24);
                    }
                    datastr.add(MessageFormat.format("{0}    {1}", tmp, l.getLog().get(date)));
                }
            }

            String[] tmp = datastr.toArray(new String[0]);

            int pagemax = (int)((float)(txtheight*tmp.length) / pagefillheight);
            int itemperpage = (int)(pagefillheight/txtheight);
            if(((int)((float)(txtheight*tmp.length)) % pagefillheight)>0){
                pagemax++;
            }

            for(int i=0;i<pagemax;i++){
                int nextcount = itemperpage;
                if(tmp.length-(i)*itemperpage <itemperpage){
                    nextcount = tmp.length-(i)*itemperpage;
                }
                String[] tmpdata = new String[nextcount];
                System.arraycopy(tmp, i*itemperpage, tmpdata, 0, nextcount);
                if(viewonly){
                    Bitmap b = Bitmap.createBitmap(width,height, Bitmap.Config.RGB_565);
                    Canvas c = new Canvas(b);
                    Paint paint = new Paint();
                    paint.setColor(Color.WHITE);
                    c.drawRect(0, 0, width, height, paint);

                    DrawReportPage(c,fromdate, todate,1+i,pagemax, header, tmpdata, "User Log", user,null);
                    ((ArrayList<Bitmap>)doc).add(b);
                }else{
                    PdfDocument.PageInfo pi = null;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        pi = new PdfDocument.PageInfo.Builder(width,height, 1+i).create();
                        PdfDocument.Page page = ((PdfDocument)doc).startPage(pi);
                        DrawReportPage(page.getCanvas(),fromdate, todate,1+i,pagemax, header, tmpdata, "User Log", user,null);
                        //DrawReportPLUSubpage(page.getCanvas(),fromdate, todate,1+i,pagemax, header, tmpdata, groupdept);
                        ((PdfDocument)doc).finishPage(page);
                    }
                }
            }

            return doc;
        }

        @Override
        protected void onProgressUpdate(String... params){}

        @TargetApi(Build.VERSION_CODES.KITKAT)
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected void onPostExecute(Object output){
            ReportResult(output);

        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    void ReportResult(Object result){
        if(result == null){
            DialogBox dlg = new DialogBox(this);
            dlg.setTitle(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 18));
            dlg.setDialogIconType(DialogBox.IconType.ERROR);
            dlg.setMessage(StrTextConst.GetText(StrTextConst.TextType.RPTGEN, 26));
            dlg.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
            dlg.show();
            return;
        }

        if(dlg!=null){
            if(dlg.isShowing()){
                dlg.dismiss();
            }
        }
        if(result instanceof PdfDocument){
            final PdfDocument fdoc = (PdfDocument)result;

            FileBrowser fb = new FileBrowser(getApplicationContext());

            fb.SetFileExtension("PDF File|*.pdf");
            fb.setOnFileOKListener(new FileBrowser.OnFileOKListener(){
                @Override
                public void onSelected(View v, File file) {
                    try {
                        FileOutputStream fos = new FileOutputStream(file);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                            fdoc.writeTo(fos);
                        }
                        fos.close();
                        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));

                        DialogBox dlg = new DialogBox(v.getContext());
                        dlg.setTitle(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 12));
                        dlg.setDialogIconType(DialogBox.IconType.INFORMATION);
                        dlg.setMessage(StrTextConst.GetText(StrTextConst.TextType.RPTGEN, 28 ,file.getAbsolutePath()));
                        dlg.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                        dlg.show();
                    } catch (IOException e) {
                        Logger.WriteLog("ActivityGenReport",e.toString());
                        DialogBox dlg = new DialogBox(v.getContext());
                        dlg.setTitle(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 18));
                        dlg.setDialogIconType(DialogBox.IconType.ERROR);
                        dlg.setMessage(StrTextConst.GetText(StrTextConst.TextType.RPTGEN, 27));
                        dlg.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                        dlg.show();
                    }
                }
            });
            fb.ShowSaveDialog();

        }else{
            @SuppressWarnings("unchecked")
            final ArrayList<Bitmap> doclist = (ArrayList<Bitmap>)result;
            final Dialog dlg = new Dialog(getApplicationContext());
            dlg.setCancelable(false);
            dlg.setCanceledOnTouchOutside(false);
            dlg.setContentView(R.layout.dlg_reportviewer);
            dlg.setTitle(StrTextConst.GetText(StrTextConst.TextType.RPTGEN, 0));

            TextView lbl_1 = (TextView)dlg.findViewById(R.id.lbl_1);

            final Button btn_next = (Button)dlg.findViewById(R.id.btn_next);
            final Button btn_prev = (Button)dlg.findViewById(R.id.btn_prev);
            Button btn_zoom_in = (Button)dlg.findViewById(R.id.btn_zoom_in);
            Button btn_zoom_out = (Button)dlg.findViewById(R.id.btn_zoom_out);
            Button btn_zoom_fit = (Button)dlg.findViewById(R.id.btn_zoom_fit);
            Button btn_zoom_exact = (Button)dlg.findViewById(R.id.btn_zoom_exact);
            Button btn_close = (Button)dlg.findViewById(R.id.btn_close);

            LinearLayout lay = (LinearLayout)dlg.findViewById(R.id.lay_canvas);
            final CanvasDrawer cd = new CanvasDrawer(dlg.getContext());
            lay.addView(cd, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

            final TextView txt_page = (TextView)dlg.findViewById(R.id.txt_pgnum);
            txt_page.setTag(0);
            txt_page.setText((((Integer)txt_page.getTag())+1)+"/"+doclist.size());


            btn_zoom_in.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    cd.ZoomIn();
                }
            });

            btn_zoom_out.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    cd.ZoomOut();
                }
            });

            btn_zoom_fit.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    cd.ZoomFit();
                }
            });

            btn_zoom_exact.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    cd.ZoomExact();
                }
            });

            btn_prev.setEnabled(false);
            if(((Integer)txt_page.getTag())>= doclist.size()-1){
                btn_next.setEnabled(false);
            }

            btn_close.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    doclist.clear();
                    dlg.dismiss();
                }
            });




            btn_prev.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    //previous page
                    int count = (Integer)txt_page.getTag();
                    if(count<=0){
                        count = 0;
                        btn_prev.setEnabled(false);
                        txt_page.setTag(count);
                        return;
                    }
                    count--;

                    if(count<=0){
                        btn_prev.setEnabled(false);
                    }
                    txt_page.setTag(count);
                    if(count<doclist.size()-1)btn_next.setEnabled(true);
                    txt_page.setText((count+1)+"/"+doclist.size());
                    cd.DrawBitmap(doclist.get(count));
                }
            });

            btn_next.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    //next page
                    int count = (Integer)txt_page.getTag();
                    if(count>=doclist.size()-1){
                        count = doclist.size()-1;
                        btn_next.setEnabled(false);
                        txt_page.setTag(count);
                        return;
                    }
                    count++;



                    if(count>=doclist.size()-1){
                        btn_next.setEnabled(false);
                    }


                    txt_page.setTag(count);
                    if(count>0)btn_prev.setEnabled(true);
                    txt_page.setText((count+1)+"/"+doclist.size());
                    cd.DrawBitmap(doclist.get(count));

                }
            });


            if(doclist.size()>0){
                cd.DrawBitmap(doclist.get(0));
            }

            lbl_1.setText(StrTextConst.GetText(StrTextConst.TextType.RPTGEN, 10));
            btn_zoom_fit.setText(StrTextConst.GetText(StrTextConst.TextType.RPTGEN, 100));
            btn_zoom_exact.setText(StrTextConst.GetText(StrTextConst.TextType.RPTGEN, 101));
            btn_close.setText(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 13));
            dlg.show();

            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            Window window = dlg.getWindow();
            lp.copyFrom(window.getAttributes());
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(lp);



        }
    }
    private class CanvasDrawer extends View{
        private ScaleGestureDetector sg;
        private GestureDetector gd;
        private Bitmap b = null;
        private float posX=0,posY=0;
        private float scale = 1f;
        private RectF size = new RectF(0,0,1,1);
        private float scalemin = 1f;

        public CanvasDrawer(Context context) {
            super(context);
            sg = new ScaleGestureDetector(context,new ScaleListener());
            gd = new GestureDetector(context,new PanListener());
            //this.setOnClickListener(doubleclicklistener);
        }

        public CanvasDrawer(Context context, AttributeSet attrs){
            super(context,attrs);
            sg = new ScaleGestureDetector(context,new ScaleListener());
            gd = new GestureDetector(context,new PanListener());
            //this.setOnClickListener(doubleclicklistener);

        }



        @Override
        public boolean onTouchEvent(MotionEvent e){
            if(e.getPointerCount()>=2){
                if(sg!=null){
                    sg.onTouchEvent(e);
                }
            }else if(e.getPointerCount() == 1){
                if(gd!=null){
                    gd.onTouchEvent(e);
                }
            }

            return true;
        }

        public void DrawBitmap(Bitmap b){
            this.b = b;
            scale = -1f;
            posX = 0;
            posY = 0;
            if(this.b==null){
                return;
            }
            invalidate();
        }

        @Override
        protected void onDraw(Canvas c){
            if(b==null){
                return;
            }
            if(c.getWidth()<=0 || c.getHeight() <=0){
                return;
            }

            size.set(0, 0, c.getWidth(), c.getHeight());
            if(scale==-1){
                if(b.getWidth()>b.getHeight()){
                    scale = (float)c.getWidth()/(float)b.getWidth();
                }else{
                    scale = (float)c.getHeight()/(float)b.getHeight();
                }
                scalemin = scale;
                if(scalemin<0.1f)scalemin=0.1f;
            }

            c.save();
            c.scale(scale, scale);
            c.translate(posX, posY);
            c.drawBitmap(b, 0, 0, null);

            c.restore();
        }

        private class PanListener extends GestureDetector.SimpleOnGestureListener{
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float disX, float disY){
                if(b!=null){

                    float x = disX/scale;
                    float y = disY/scale;

                    posX-=x;
                    posY-=y;
                    if(posX<-b.getWidth())posX = -b.getWidth();
                    if(b!=null){
                        if(posX>b.getWidth()-size.width()) posX = b.getWidth()-size.width();
                    }else{
                        posX = 0;
                    }

                    if(posY<-b.getHeight())posY = -b.getHeight();
                    if(b!=null){
                        if(posY>b.getHeight()-size.height()) posY = b.getHeight()-size.height();
                    }else{
                        posY = 0;
                    }

                }

                invalidate();

                return true;
            }
        }

        private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
            @Override
            public boolean onScale(ScaleGestureDetector detector) {
                scale *= detector.getScaleFactor();
                scale = Math.max(scalemin/2f, Math.min(scale, 5.0f));
                invalidate();
                return true;
            }
        }

        public void ZoomIn(){
            scale *=2f;
            scale = Math.max(scalemin/2f, Math.min(scale, 5.0f));
            invalidate();
        }

        public void ZoomOut(){
            scale /=2f;
            scale = Math.max(scalemin/2f, Math.min(scale, 5.0f));
            invalidate();
        }

        public void ZoomExact(){
            scale = 1f;
            scale = Math.max(scalemin/2f, Math.min(scale, 5.0f));
            invalidate();
        }

        public void ZoomFit(){
            scale = scalemin;
            scale = Math.max(scalemin/2f, Math.min(scale, 5.0f));
            invalidate();
        }
    }
    private void CheckZReportPrint() {
        String Str_BalmodeCheck = MainActivity.ChkBalanceMode();
        if (Integer.parseInt(Str_BalmodeCheck) == 2) {
            if (Integer.parseInt(Str_BalmodeCheck) > 0) {

                Cursor _tmpcursor = DBFunc.Query("SELECT COUNT(*) FROM Bill WHERE CloseDateTime IS NULL", false);
                if (_tmpcursor == null) {
                    DialogBox dlg1 = new DialogBox(ReportActivity.this);
                    dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 18));
                    dlg1.setDialogIconType(DialogBox.IconType.ERROR);
                    dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 19));
                    dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                    dlg1.show();
                    // Toast.makeText(CurrentActivity,
                    // "Fatal Error! Failed to Connect to Database!",
                    // Toast.LENGTH_SHORT).show();
                    return;
                }

                if (_tmpcursor.moveToNext()) {// get total count of bill haven't
                    // close
                    long count = _tmpcursor.getLong(0);
                    _tmpcursor.close();
                    if (count == 1) {
                        _tmpcursor = DBFunc.Query("SELECT BillNo FROM Bill WHERE CloseDateTime IS NULL", false);
                        if (_tmpcursor == null) {
                            DialogBox dlg1 = new DialogBox(ReportActivity.this);
                            dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 18));
                            dlg1.setDialogIconType(DialogBox.IconType.ERROR);
                            dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 19));
                            dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                            dlg1.show();
                            // Toast.makeText(CurrentActivity,
                            // "Fatal Error! Failed to Connect to Database!",
                            // Toast.LENGTH_SHORT).show();
                            return;
                        }
                        int unclosecount = 0;
                        long billID = 0;
                        while (_tmpcursor.moveToNext()) {
                            billID = _tmpcursor.getLong(0);
                            Cursor _tmpcursor2 = DBFunc.Query("SELECT COUNT(*) FROM BillPLU WHERE BillNo = " + billID, false);
                            _tmpcursor2.moveToNext();
                            if (_tmpcursor2.getLong(0) > 0)
                                unclosecount++;
                            _tmpcursor2.close();
                            // return;
                        }
                        _tmpcursor.close();

                        if (unclosecount > 0) {
                            _tmpcursor = DBFunc.Query("SELECT BillNo FROM Bill WHERE CloseDateTime IS NULL", false);
                            if (_tmpcursor.moveToNext()) {
                                //long billID = _tmpcursor.getLong(0);
                                billID = _tmpcursor.getLong(0);
                                _tmpcursor.close();
                                String _billnum = "";
                                if ((billID + "").length() < Zeroes.length()) {
                                    _billnum = Zeroes.substring(0, Zeroes.length() - (billID + "").length()) + billID;
                                }

                                DialogBox dlg1 = new DialogBox(ReportActivity.this);
                                dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.POS, 0));
                                dlg1.setDialogIconType(DialogBox.IconType.WARNING);
                                dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.POS, 139, _billnum));
                                dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                                dlg1.show();
                                // Toast.makeText(CurrentActivity,
                                // "Cannot do Z report because bill "+_billnum+" haven't settle yet!",
                                // Toast.LENGTH_LONG).show();

                                return;
                            }
                            _tmpcursor.close();
                            DialogBox dlg1 = new DialogBox(ReportActivity.this);
                            dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 18));
                            dlg1.setDialogIconType(DialogBox.IconType.ERROR);
                            dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.POS, 312));
                            dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                            dlg1.show();
                            return;
                        }

                    } else if (count > 1) {
                        DialogBox dlg1 = new DialogBox(ReportActivity.this);
                        dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.POS, 0));
                        dlg1.setDialogIconType(DialogBox.IconType.WARNING);
                        dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.POS, 139, count));
                        dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                        dlg1.show();

                        return;
                    }

                } else {
                    _tmpcursor.close();
                    DialogBox dlg1 = new DialogBox(ReportActivity.this);
                    dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 18));
                    dlg1.setDialogIconType(DialogBox.IconType.ERROR);
                    dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 312));
                    dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                    dlg1.show();
                    return;
                }
                _tmpcursor.close();
            } else {
                Cursor _tmpcursor = DBFunc.Query("SELECT BillNo FROM Bill WHERE CloseDateTime IS NULL ORDER BY BillNo ASC", false);
                if (_tmpcursor == null) {
                    DialogBox dlg1 = new DialogBox(ReportActivity.this);
                    dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 18));
                    dlg1.setDialogIconType(DialogBox.IconType.ERROR);
                    dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 19));
                    dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                    dlg1.show();
                    // Toast.makeText(CurrentActivity,
                    // "Fatal Error! Failed to Connect to Database!",
                    // Toast.LENGTH_SHORT).show();
                    return;
                }

                int unclosecount = 0;
                long billID = 0;
                while (_tmpcursor.moveToNext()) {
                    billID = _tmpcursor.getLong(0);
                    Cursor _tmpcursor2 = DBFunc.Query("SELECT COUNT(*) FROM BillPLU WHERE BillNo = " + billID, false);
                    _tmpcursor2.moveToNext();
                    if (_tmpcursor2.getLong(0) > 0)
                        unclosecount++;
                    _tmpcursor2.close();
                    // return;
                }
                _tmpcursor.close();

                if (unclosecount > 0) {
                    if (unclosecount == 1) {
                        String _billnum = "";
                        if ((billID + "").length() < Zeroes.length()) {
                            _billnum = Zeroes.substring(0, Zeroes.length() - (billID + "").length()) + billID;
                        }
                        DialogBox dlg1 = new DialogBox(ReportActivity.this);
                        dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.POS, 0));
                        dlg1.setDialogIconType(DialogBox.IconType.WARNING);
                        dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.POS, 139, _billnum));
                        dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                        dlg1.show();
                        return;
                    } else {
                        DialogBox dlg1 = new DialogBox(ReportActivity.this);
                        //dlg1.setTitle("PIXIE POS");
                        dlg1.setTitle("MyRetailer");
                        dlg1.setDialogIconType(DialogBox.IconType.WARNING);
                        dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.POS, 139, unclosecount));
                        dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                        dlg1.show();
                        return;
                    }
                }
            }
        }
    }

    private void printSales(ReceiptZCloseData rzcd, String str, String str2, String str3, String str4,
                            String str5, String str6, String str7, String str_status,
                            String ReprintZStatus, Integer tabNumber) {
        String chkZClose = Query.GetOptions(27);
        final String finalStr = str;
        final String finalStr2 = str2;
        final String finalStr3 = str3;
        final String finalStr4 = str4;
        final String finalStr5 = str5;
        final String finalStr6 = str6;
        final String finalStr7 = str7;

        String txtFileZReport = finalStr + finalStr2 +finalStr3+finalStr4+finalStr5+finalStr6+finalStr7;

         String terminaltype_check = Query.GetDeviceData(Constraints.TERMINAL_TYPE);
//        if (terminaltype_check.toUpperCase().equals(Constraints.IMIN.toUpperCase())) {
//            Log.i("Build.VERSION.SDK_INT__","DFDF_"+Build.VERSION.SDK_INT);
//            Log.i("Build.VERSION.SDK_INT__","DFDF_"+Build.VERSION_CODES.N);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                mIminPrintUtils = IminPrintUtils.getInstance(this);
////
//                mIminPrintUtils.initPrinter(IminPrintUtils.PrintConnectType.SPI);
//                mIminPrintUtils.printText(txtFileZReport);
//                mIminPrintUtils.printAndFeedPaper(100);
//            }
//
//        }else
            if (terminaltype_check.toUpperCase().equals(Constraints.INGENICO.toUpperCase())){

                //startPrinter(1,mcontext);
                try {
                    startPrinter(1,appContext,txtFileZReport);
                } catch (RemoteException e) {
                    e.printStackTrace();
                    Log.i("ErrorPrinting__","Error__"+e.getMessage());
                }

         }else if (terminaltype_check.toUpperCase().equals(Constraints.PAX.toUpperCase())) {



//             PrinterTester printer_tester = PrinterTester.getInstance();
//             printer_tester.init();
//             BitmapFactory.Options options = new BitmapFactory.Options();
//             options.inScaled = false;
//
//             printer_tester.printStr(finalStr, null);//Total Sales
//             printer_tester.printStr(finalStr2, null);//Category
//             printer_tester.printStr(finalStr3, null);//Product
//             printer_tester.printStr(finalStr4, null);//Payment
//             printer_tester.printStr(finalStr5, null);//Discount
//             printer_tester.printStr(finalStr6, null);
//             printer_tester.printStr(finalStr7, null);//Cancellation
//             //printer_tester.cutPaper(5);
//             final String status = printer_tester.start();


                PrintReceiptGenerator.printZCloseReceipt(rzcd);
         }else if (terminaltype_check.toUpperCase().equals(Constraints.PAX_E600M.toUpperCase())) {
         }

        date_status = "0";
        Query.XReportClosingPeriod();

        if (!ReprintZStatus.equals("ReprintZ")) { // if Z Closing
            ReportActivity.previous_report_shift_name = "Now";
            PreviousReportFragment.selected_tax_id = 0;
            PreviousReportFragment.selected_tax_name = "";
            ReportDateSheetFragment.currenttabnumber = binding.pagerReport.getCurrentItem();

            //viewPager.setCurrentItem(viewPager.getCurrentItem());
            //binding.pagerReport.setCurrentItem(3);

            if (str_status.equals("Z")) {
                Query.ZClose(ReportActivity.this);
                //binding.pagerReport.setCurrentItem(3);
                Query.UpdateIsZInSyncSales();
            }
//            St = "1";
//            ReportProductFragment.St = "1";
//            ReportCategoryFragment.St = "1";
//            ReportXFragment.St = "1";
//            St = "1";
//            ReportProductFragment.St = "1";
//            ReportCategoryFragment.St = "1";
//            ReportXFragment.St = "1";

            //binding.pagerReport.setCurrentItem(3);
            //binding.pagerReport.setCurrentItem(3);

        }else {
//            Intent i = new Intent(ReportActivity.this,ReportActivity.class);
//            startActivity(i);
//            finish();

            //ReportDateSheetFragment.currenttabnumber = binding.pagerReport.getCurrentItem();
            //binding.pagerReport.setCurrentItem(3);
//            St = "1";
        }

        //binding.pagerReport.setCurrentItem(3);
        //binding.pagerReport.setCurrentItem(3);
        //binding.pagerReport.setCurrentItem(3);


//         Intent i = new Intent(ReportActivity.this,ReportActivity.class);
//         startActivity(i);
        updateQtyAndAmtFun();

        if (chkZClose.equals("1")){

            RemarkMainActivity.userid = "";
            RemarkMainActivity.userpassword = "";
            Intent i = new Intent(this,RemarkMainActivity.class);
            startActivity(i);
            finish();
            //ActivityCompat.finishAffinity(ReportActivity.this);
//                        RemarkMainActivity.loginSuccess = "0";
        } else {
            ReportAdapter adapter = new ReportAdapter
                    (getSupportFragmentManager(), binding.tabLayoutReport.getTabCount(),ReportActivity.this);
            binding.pagerReport.setAdapter(adapter);
            if (binding.pagerReport.getCurrentItem() > 0){
                binding.pagerReport.setCurrentItem(binding.pagerReport.getCurrentItem());
            }else {
                binding.pagerReport.setCurrentItem(3);
            }
        }

//        ReportAdapter adapter = new ReportAdapter
//                (getSupportFragmentManager(), binding.tabLayoutReport.getTabCount(),ReportActivity.this);
//        binding.pagerReport.setAdapter(adapter);
//        if (binding.pagerReport.getCurrentItem() > 0){
//            binding.pagerReport.setCurrentItem(binding.pagerReport.getCurrentItem());
//        }else {
//            binding.pagerReport.setCurrentItem(3);
//        }
//        binding.pagerReport.setCurrentItem(2);

//        ReportXFragment.reportProductHeaderArr.clear();
//        ReportXFragment.reportProductHeaderArr.clear();
//        ReportXFragment.reportProductHeaderArr.clear();
//        ReportXFragment.reportProductHeaderArr.clear();
//        ReportXFragment.reportProductHeaderArr.clear();
//        ReportXFragment.reportProductHeaderArr.clear();
//        ReportXFragment.reportProductHeaderArr.clear();
//
//        ReportXFragment.reportProductNameArr.clear();
//        ReportXFragment.reportProductNameArr.clear();
//        ReportXFragment.reportProductNameArr.clear();
//        ReportXFragment.reportProductNameArr.clear();
//        ReportXFragment.reportProductNameArr.clear();
//        ReportXFragment.reportProductNameArr.clear();
//        ReportXFragment.reportProductNameArr.clear();
//
//        ReportXFragment.reportProductPriceArr.clear();
//        ReportXFragment.reportProductPriceArr.clear();
//        ReportXFragment.reportProductPriceArr.clear();
//        ReportXFragment.reportProductPriceArr.clear();
//        ReportXFragment.reportProductPriceArr.clear();
//        ReportXFragment.reportProductPriceArr.clear();
//        ReportXFragment.reportProductPriceArr.clear();
//
//        ReportXFragment.getPrintSales(ReportActivity.start,ReportActivity.end);
//        ReportXFragment.reportProductHeaderArr.add(ReportXFragment.str_header);
//        ReportXFragment.reportProductHeaderArr.add(ReportXFragment.str_header1);
//        ReportXFragment.reportProductHeaderArr.add(ReportXFragment.str_header2);
//        ReportXFragment.reportProductHeaderArr.add(ReportXFragment.str_header3);
//        ReportXFragment.reportProductHeaderArr.add(ReportXFragment.str_header4);
//        ReportXFragment.reportProductHeaderArr.add(ReportXFragment.str_header5);
//        ReportXFragment.reportProductHeaderArr.add(ReportXFragment.str_header6);
//
//        ReportXFragment.reportProductNameArr.add(ReportXFragment.str_name);
//        ReportXFragment.reportProductNameArr.add(ReportXFragment.str_name1);
//        ReportXFragment.reportProductNameArr.add(ReportXFragment.str_name2);
//        ReportXFragment.reportProductNameArr.add(ReportXFragment.str_name3);
//        ReportXFragment.reportProductNameArr.add(ReportXFragment.str_name4);
//        ReportXFragment.reportProductNameArr.add(ReportXFragment.str_name5);
//        ReportXFragment.reportProductNameArr.add(ReportXFragment.str_name6);
//
//        ReportXFragment.reportProductPriceArr.add(ReportXFragment.str_price);
//        ReportXFragment.reportProductPriceArr.add(ReportXFragment.str_price1);
//        ReportXFragment.reportProductPriceArr.add(ReportXFragment.str_price2);
//        ReportXFragment.reportProductPriceArr.add(ReportXFragment.str_price3);
//        ReportXFragment.reportProductPriceArr.add(ReportXFragment.str_price4);
//        ReportXFragment.reportProductPriceArr.add(ReportXFragment.str_price5);
//        ReportXFragment.reportProductPriceArr.add(ReportXFragment.str_price6);
//
//        ReportXFragment.binding.ReportProductListView.removeAllViewsInLayout();
//        ReportXAdapter customAdapter = new ReportXAdapter(getApplicationContext(),
//                ReportXFragment.reportProductNameArr,
//                ReportXFragment.reportProductPriceArr,
//                ReportXFragment.reportProductHeaderArr);
////            binding.ReportProductListView.removeAllViews(); // java.lang.UnsupportedOperationException: removeAllViews() is not supported in AdapterView
////            binding.ReportProductListView.refreshDrawableState();
//        ReportXFragment.binding.ReportProductListView.setAdapter(customAdapter);
////            customAdapter.notifyDataSetInvalidated();
//        customAdapter.notifyDataSetChanged();
//        //ReportXFragment.updateReportFragmentXFun();
       }

    public static void startPrinter(final int curSheetNo, final Context context,String str) throws RemoteException {
//        if (curSheetNo > 1) {
//            return;
//        }
//        outputBlueText(">>> start print | sheetNo = " + curSheetNo);

        // 打印文本 print text
//        printer.addText(AlignMode.CENTER, "Sheet number " + curSheetNo);

        printer.setAscScale(ASCScale.SC1x1);
        printer.setAscSize(ASCSize.DOT24x12);
//        printer.addText(AlignMode.LEFT, "English:Scale1x1,Dot24x12");
        printer.addText(AlignMode.LEFT, str);

//        printer.setHzScale(HZScale.SC1x1);
//        printer.setHzSize(HZSize.DOT24x24);
//        printer.addText(AlignMode.LEFT, "chinese : scaling ratio 1x1, bitmap 24x24");
//
//        printer.setPrintFormat(PrintFormat.FORMAT_MOREDATAPROC, PrintFormat.VALUE_MOREDATAPROC_PRNTOEND);
//        printer.addText(AlignMode.LEFT, "Setting Print format for printing all the contents until the end!");
//        printer.setPrintFormat(PrintFormat.FORMAT_MOREDATAPROC, PrintFormat.VALUE_MOREDATAPROC_PRNONELINE);
//        printer.addText(AlignMode.LEFT, "Setting Print format for printing only one line, more than one line does not print!");
//
//        printer.setPrintFormat(PrintFormat.FORMAT_ZEROSPECSET, PrintFormat.VALUE_ZEROSPECSET_SPECIALZERO);
//        printer.addText(AlignMode.LEFT, "Zero print style: 0 ");
//        printer.setPrintFormat(PrintFormat.FORMAT_ZEROSPECSET, PrintFormat.VALUE_ZEROSPECSET_DEFAULTZERO);
//        printer.addText(AlignMode.LEFT, "Zero print style: 0 ");
//
//        // 打印行混合文本 Print mix text on the same line
//        List<Bundle> textBlockList = new ArrayList<Bundle>();
//        Bundle block1 = new Bundle();
//        block1.putString(PrinterData.TEXT, "Thank ");
//        textBlockList.add(block1);
//        Bundle block2 = new Bundle();
//        block2.putString(PrinterData.TEXT, "you ");
//        block2.putInt(PrinterData.ASC_SCALE, ASCScale.SC2x2);
//        block2.putInt(PrinterData.ASC_SIZE, ASCSize.DOT24x8);
//        textBlockList.add(block2);
//        Bundle block3 = new Bundle();
//        block3.putString(PrinterData.TEXT, "for using");
//        textBlockList.add(block3);
//        printer.addMixText(AlignMode.CENTER, textBlockList);
//
//        List<Bundle> textBlockList2 = new ArrayList<Bundle>();
//        block1.putInt(PrinterData.ALIGN_MODE, AlignMode.LEFT);
//        block2.putInt(PrinterData.ALIGN_MODE, AlignMode.RIGHT);
//        textBlockList2.add(block1);
//        textBlockList2.add(block2);
//        printer.addMixStyleText(textBlockList2);
//
//        printer.addQrCode(AlignMode.CENTER, 240, ECLevel.ECLEVEL_H, "www.landicorp.com");
//        printer.addBarCode(AlignMode.CENTER, 2, 48,  "1234567");
//
////        printer.addText(AlignMode.LEFT, ">>>>>>> addBmpImage ");
////        byte[] image = readAssetsFile(context, "logo.bmp");
////        printer.addBmpImage(0, FactorMode.BMP1X1, image);
//
////        printer.addText(AlignMode.LEFT, ">>>>>>> addBmpPath ");
////        FileUtil.copyFileToSD(getBaseContext(), Environment.getExternalStorageDirectory().getPath(), "barcode.bmp");
////        printer.addBmpPath(0, FactorMode.BMP1X1, Environment.getExternalStorageDirectory() + "/barcode.bmp");
//
//        printer.addText(AlignMode.LEFT, ">>>>>>> gray0, 5, 10");
//        for (int i = 0; i <= 10; i=i+5) {
//            printer.setPrnGray(i);
//            printer.addText(AlignMode.LEFT, i + "gray浓度测试");
//        }
//        printer.setPrnGray(3);
//        printer.feedLine(5);
//
        printer.startPrint(new OnPrintListener.Stub() {
            @Override
            public void onFinish() throws RemoteException {
                int orange = Color.rgb(0xEF, 0xB3, 0x36);
//                outputText(orange,"=> onFinish | sheetNo = " + curSheetNo);
                //startPrinter(curSheetNo + 1,context);
            }

            @Override
            public void onError(int error) throws RemoteException {
//                outputRedText("=> onError: " + getErrorDetail(error));
            }
        });
    }

    private void download() {
        String URL = "http://www.codeplayon.com/samples/resume.pdf";
        new DownloadTask(ReportActivity.this, URL);
    }

//    private void download() {
////        try {
////            String content = "Separe here integers by semi-colon";
////            File file = new File("AAAAAAAAAAAAAAAAAAAAAAAA" +".csv");
////            // if file doesnt exists, then create it
////            if (!file.exists()) {
////                file.createNewFile();
////            }
////
////            FileWriter fw = new FileWriter(file.getAbsoluteFile());
////            BufferedWriter bw = new BufferedWriter(fw);
////            bw.write(content);
////            bw.close();
////
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////        BufferedReader br = null;
////        try {
////            String sCurrentLine;
////            br = new BufferedReader(new FileReader("AAAAAAAAAAAAAAAAAAAAAAAA"+".csv"));
////            while ((sCurrentLine = br.readLine()) != null) {
////                System.out.println(sCurrentLine);
////            }
////        } catch (IOException e) {
////            e.printStackTrace();
////        } finally {
////            try {
////                if (br != null)br.close();
////            } catch (IOException ex) {
////                ex.printStackTrace();
////            }
////        }
////        try {
////// OPTION 1: if the file is in the sd
////           // File csvfile = new File(Environment.getExternalStorageDirectory() + "/csvfile.csv");
////// END OF OPTION 1
////
////// OPTION 2: pack the file with the app
////            /* "If you want to package the .csv file with the application and have it install on the internal storage when the app installs, create an assets folder in your project src/main folder (e.g., c:\myapp\app\src\main\assets\), and put the .csv file in there, then reference it like this in your activity:" (from the cited answer) */
////            String csvfileString = this.getApplicationInfo().dataDir + File.separatorChar + "csvfile.csv";
////            File csvfile = new File(csvfileString);
////// END OF OPTION 2
////
////            CSVReader reader = new CSVReader(new FileReader("csvfile.getAbsolutePath()"));
////            String[] nextLine;
////            while ((nextLine = reader.readNext()) != null) {
////                // nextLine[] is an array of values from the line
////                System.out.println(nextLine[0] + nextLine[1] + "etc...");
////            }
////        } catch (Exception e) {
////            e.printStackTrace();
////            Toast.makeText(this, "The specified file was not found", Toast.LENGTH_SHORT).show();
////        }
//        File folder = new File(Environment.getExternalStorageDirectory() + "/DebugData");
//
//        String path = folder.getPath();
//        final String filename = folder.toString() + "/" + "Test.csv";
//        if(!folder.mkdirs() || !folder.exists()){
//            Log.i("ffd", path + " failed");
//        } else {
//            Log.i("nfc", path + " succeeded");
//        }
//
//        // show waiting screen
//        CharSequence contentTitle = getString(R.string.app_name);
//        final ProgressDialog progDailog = ProgressDialog.show(
//                ReportActivity.this, contentTitle, "even geduld aub...",
//                true);//please wait
//        final Handler handler = new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//
//
//
//
//            }
//        };
//
//        new Thread() {
//            public void run() {
//                try {
//
//                    FileWriter fw = new FileWriter(filename);
//                    Log.i("Dfsd_", String.valueOf(fw));
//                    String sql = "SELECT * from sales";
//                    Cursor cursor = DBFunc.Query(sql, false);
//                    //Cursor cursor = db.selectAll();
//
//                    fw.append("No");
//                    fw.append(',');
//
//                    fw.append("code");
//                    fw.append(',');
//
//                    fw.append("nr");
//                    fw.append(',');
//
//                    fw.append("Orde");
//                    fw.append(',');
//
//                    fw.append("Da");
//                    fw.append(',');
//
//                    fw.append("Date");
//                    fw.append(',');
//
//                    fw.append("Leverancier");
//                    fw.append(',');
//
//                    fw.append("Baaln");
//                    fw.append(',');
//
//                    fw.append("asd");
//                    fw.append(',');
//
//                    fw.append("Kwaliteit");
//                    fw.append(',');
//
//                    fw.append("asd");
//                    fw.append(',');
//
//                    fw.append('\n');
//
////                        if (cursor.moveToFirst()) {
////                            do {
////                                fw.append(cursor.getString(0));
////                                fw.append(',');
////
////                                fw.append(cursor.getString(1));
////                                fw.append(',');
////
////                                fw.append(cursor.getString(2));
////                                fw.append(',');
////
////                                fw.append(cursor.getString(3));
////                                fw.append(',');
////
////                                fw.append(cursor.getString(4));
////                                fw.append(',');
////
////                                fw.append(cursor.getString(5));
////                                fw.append(',');
////
////                                fw.append(cursor.getString(6));
////                                fw.append(',');
////
////                                fw.append(cursor.getString(7));
////                                fw.append(',');
////
////                                fw.append(cursor.getString(8));
////                                fw.append(',');
////
////                                fw.append(cursor.getString(9));
////                                fw.append(',');
////
////                                fw.append(cursor.getString(10));
////                                fw.append(',');
////
////                                fw.append('\n');
////
////                            } while (cursor.moveToNext());
////                        }
////                        if (cursor != null && !cursor.isClosed()) {
////                            cursor.close();
////                        }
//
//                    // fw.flush();
//                    fw.close();
//
//                } catch (Exception e) {
//                    Log.i("ERERER_",e.toString());
//                    Log.i("ERERER__",e.getMessage().toString());
//                }
//                handler.sendEmptyMessage(0);
//                progDailog.dismiss();
//            }
//        }.start();
//    }


//    public void downloadPDForCSV() throws IOException {
//        {
//
//            File folder = new File(Environment.getExternalStorageDirectory()
//                    + "/Folder");

//            boolean var = false;
//            if (!folder.exists())
//                var = folder.mkdir();
//
//            Log.i("sdfasdfdsfafdsf" , String.valueOf(var));
//
//
//            final String filename = folder.toString() + "/" + "Test.csv";
//            Log.i("DSfd__",filename);



//        }
//
//    }

//    private void getPrintSales(String start, String end) {
//        String sales = "0";
//        String category = "0";
//        String product_sales = "0";
//        String payment = "0";
//        String disocunt = "0";
//        String tax = "0";
//        String cancellation = "0";
//        String refer_info_sales = "0";
//
//        getSalesALLTotalSales(start,end);
//        //getSalesALLTotalOnlineOrderSales(start,end);
//
//        String sql = "SELECT Sales,Category,ProductSales,Payment,Discount,Tax,Cancellation,ReferInfoSales FROM ReportSettings";
//        //Log.i("_sql__",sql);
//        Cursor c = DBFunc.Query(sql, true);
//        if (c != null) {
//            if (c.moveToNext()) {
//                if (!c.isNull(0)) {
//                    Log.i("DSF_Integer_", String.valueOf(c.getInt(0)));
//                    sales = c.getString(0);
//                    category = c.getString(1);
//                    product_sales = c.getString(2);
//                    payment = c.getString(3);
//                    disocunt = c.getString(4);
//                    tax = c.getString(5);
//                    cancellation = c.getString(6);
//                    //refer_info_sales = c.getString(7);
//                }
//            }
//            c.close();
//        }
//        if (sales.equals("1")){
//            str = Query.XZDataReportSalesShow(TotalNettSales,BillCancelTotalQty,TotalQty,TotalNettSales,GrossSales,TaxTotal,TotalBillDisount,TotalNettSales,BillCancel,BillCancelTotalQty,TotalRoundAdjSales,ServiceChargesSales);
//        }
//        if (category.equals("1")){
//            str2 = Query.XZDataReportCategoryShow(TotalQtyCategory,TotalNettSalesCategory,TaxTotalCategory,TotalBillDisountTaxCategory,TotalQtyCategoryCancel,TotalNettSalesCategory);
//        }
//        if (product_sales.equals("1")){
//            str3 = Query.XZDataReportProductShow(TotalQtyProduct,TotalNettSalesProduct,TaxTotalProduct,TotalBillDisountTaxProduct,TotalNettSalesProductCancel,TotalNettSalesProduct,TotalProductServiceCharges);
//        }
//        if (payment.equals("1")){
//             //*Payment Count*
//            str4 = Query.XZDataReportPaymentShow(PaymentTypeCount,PaymentTypeAmount,PaymentTypeName);
//        }
//        if (disocunt.equals("1")){
//            // *Discount / Surcharge*
//            str5 = Query.XZDataReportDiscountShow(DicountName,DicountAmount);
//        }
//        if (tax.equals("1")){
//            //str6 = Query.XZDataReportTaxShow(String.valueOf(TotalTaxesCount),TotalTaxesAmount);
//            str6 = Query.XZDataReportTaxShow("TotalTax",String.valueOf(TotalTaxesAmount));
//        }
//         if (cancellation.equals("1")){
//             str6 += Query.XZDataReportCancellationShow(BillCancel,BillCancelTotalQty,BillTax,TotalBillDisountTax);
//        }
//
//        if (refer_info_sales.equals("1")){
//            str7 = Query.XZDataReportReforeInfoShow();
//        }
//        DateFormat dateFormat2 = new SimpleDateFormat("dd-MM-yyyy hh:mm aa");
//        String dateString2 = dateFormat2.format(new Date()).toString();
//        str7 += "\n" + "====================================";
//        str7 += "\n" + dateString2;
//        str7 += "\n" + "====================================";
//        str7 += "\n" + "\n" + "\n" + "\n" + "\n" ;
//
//
////            }
////        }).start();
//    }
private ReceiptZCloseData getPrintSales(String str_status,String reprintZStatus,Context context) {
    String sales = "0";
    String category = "0";
    String product_sales = "0";
    String payment = "0";
    String disocunt = "0";
    String tax = "0";
    String cancellation = "0";
    String refund = "0";
    IminPrintUtils mIminPrintUtils = null;
    String terminaltype_check = Query.GetDeviceData(Constraints.TERMINAL_TYPE);
    if (terminaltype_check.toUpperCase().equals(Constraints.IMIN.toUpperCase())) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //mIminPrintUtils = SampleActivity.mIminPrintUtils;
//            mIminPrintUtils = RemarkMainActivity.mIminPrintUtils;



//            mIminPrintUtils.printerByte(CashLayoutActivity.intToByteArray(27));
//            mIminPrintUtils.printerByte(CashLayoutActivity.intToByteArray(64));

            mIminPrintUtils = IminPrintUtils.getInstance(this);
//            //mIminPrintUtils.initPrinter(IminPrintUtils.PrintConnectType.SPI);
////            mIminPrintUtils.initPrinter(IminPrintUtils.PrintConnectType.BLUETOOTH);
//            DeviceListAdapter mAdapter = new DeviceListAdapter(context);;
//            int bluetoothPosition = 1;
//            BluetoothDevice device = mAdapter.getItem(bluetoothPosition);
            DeviceListAdapter mAdapter = new DeviceListAdapter(context);;
            List<BluetoothDevice> printerDevices = BluetoothUtil.getPairedDevices();
            mAdapter.clear();
            mAdapter.addAll(printerDevices);
            int bluetoothPosition = 0;
            BluetoothDevice device = mAdapter.getItem(bluetoothPosition);
            try {
                mIminPrintUtils.initPrinter(IminPrintUtils.PrintConnectType.BLUETOOTH, device);
            } catch (IOException e) {
                e.printStackTrace();
                DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                        System.currentTimeMillis(), DBFunc.PurifyString("Err-ReportActivity-NinitPrinter-"
                                + e.getMessage()));
            }
            mIminPrintUtils.setTextSize(22);
            mIminPrintUtils.setTextStyle(Typeface.BOLD);
            mIminPrintUtils.setTextLineSpacing(1.0f);
        }
    }

    try {

        DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                System.currentTimeMillis(), DBFunc.PurifyString("Chk-RA-ChkmIminPrintUtils-"+mIminPrintUtils));

    } catch (Exception e){
        DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                System.currentTimeMillis(), DBFunc.PurifyString("Err-RA-ChkmIminPrintUtils-"+mIminPrintUtils+"-"+e.getMessage()));
    }

    getSalesALLTotalSales();
    //getSalesALLTotalOnlineOrderSales(start,end);

    String sql = "SELECT Sales,Category,ProductSales,Payment,Discount,Tax,Cancellation,ReferInfoSales,Refund FROM ReportSettings";
    //Log.i("_sql__",sql);
    Cursor c = DBFunc.Query(sql, true);
    if (c != null) {
        if (c.moveToNext()) {
            if (!c.isNull(0)) {

                sales = c.getString(0);
                category = c.getString(1);
                product_sales = c.getString(2);
                payment = c.getString(3);
                disocunt = c.getString(4);
                tax = c.getString(5);
                cancellation = c.getString(6);
                refund = c.getString(8);
                //refer_info_sales = c.getString(7);
            }
        }
        c.close();
    }



    ReceiptZCloseData rzcd = new ReceiptZCloseData();
    TotalSales rzcd_ts = new TotalSales();
    ProductSales rzcd_ps = new ProductSales();
    CategorySales rzcd_cats = new CategorySales();
    List<Payment> rzcd_pays = new ArrayList<Payment>();
    Discount rzcd_dis = new Discount();
    Refund rzcd_rfs = new Refund();
    Cancellation rzcd_cans = new Cancellation();

    //TotalSales
    //str = "Z Report";
    str = str_status +" Report";
    if (sales.equals("1")){
        // str = Query.XZDataReportSalesShow(TotalNettSales,BillCancelTotalQty,TotalQty,TotalNettSales,GrossSales,TaxTotal,TotalBillDisount,TotalNettSales,BillCancel,BillCancelTotalQty);

//        Cursor Cursor_tax = Query.GetTax();
//        if (Cursor_tax != null) {
//            if (Cursor_tax.moveToNext()) {
//                String taxRate = Cursor_tax.getString(0);
//                Integer taxType = Cursor_tax.getInt(1);
//                String taxName = Cursor_tax.getString(2);
//
//                //if (taxType == 1){
//                //1=> None , 2=>Inclusive , 3=>Exclusive
//                if (taxType == 2) {
//                    double amt_inclusive = Query.calculateInclusive(Double.valueOf(TotalNettSales), Integer.parseInt(taxRate));
//                    TotalTaxSales = amt_inclusive;
//                }
//            }
//            Cursor_tax.close();
//        }

//        Cursor Cursor_tax = Query.GetTax();
//        if (Cursor_tax != null){
//            if (Cursor_tax.moveToNext()){
//                String taxRate = Cursor_tax.getString(0);
//                String taxType = Cursor_tax.getString(1);
//                String taxName = Cursor_tax.getString(2);
//                if (taxType.equals("2")){
//                    Cursor ctax = Query.XZDataReportSales(0,0,"ZClose");
//                    if (ctax != null) {
//                        TotalTaxSales = 0.0;
//                        Double TotalNettSalestax = 0.0;
//                        while (ctax.moveToNext()) {
//                            TotalNettSalestax = ctax.getDouble(1);
//
//
//                            double amt_inclusive = Query.calculateInclusive(Double.valueOf(TotalNettSalestax), Integer.parseInt(taxRate));
//                            //Log.i("TotalNettSalestax__","TotalNettSalestax__"+TotalNettSalestax+"__"+taxRate+"_"+amt_inclusive);
//                            TotalTaxSales += Double.valueOf(String.format("%.2f", amt_inclusive));
//                            Log.i("TotalNettSalestax__", "TotalNettSalestaxx__" + TotalNettSalestax + "__" + taxRate + "_" + amt_inclusive);
//                        }
//                        ctax.close();
//                    }
//                    //str_price += "\n" +"$"+ String.format("%.2f", Double.valueOf(TotalTaxSales));
//                    // double amt_inclusive = Query.calculateInclusive(Double.valueOf(TotalNettSales),Integer.parseInt(taxRate));
////                        TotalTaxSales = amt_inclusive;
//                } else {
//                    //str_price += "\n" +"$"+ String.format("%.2f", Double.valueOf(TotalTaxSales));
//                }
//            }
//            Cursor_tax.close();
//        }


        if (terminaltype_check.toUpperCase().equals(Constraints.IMIN)){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Query.PrintingValueSetForIMINReport(mIminPrintUtils,Constraints.LINEIMIN, Constraints.HEADER);

                Query.PrintingValueSetForIMINReport(mIminPrintUtils,"*TOTAL SALES*",Constraints.HEADER);
                Query.PrintingValueSetForIMINReport(mIminPrintUtils,Constraints.DOTLINEIMIN, Constraints.HEADER);
                Query.PrintingValueSetForIMINReport(mIminPrintUtils,Constraints.BillPaid,"$"+String.format("%.2f", Double.valueOf(TotalNettSales)));
                Query.PrintingValueSetForIMINReport(mIminPrintUtils,Constraints.BillCancel,"$"+String.format("%.2f", Double.valueOf(BillCancel)));
                Query.PrintingValueSetForIMINReport(mIminPrintUtils,Constraints.BillRefund,"$"+String.format("%.2f", Double.valueOf(BillRefund)));
                Query.PrintingValueSetForIMINReport(mIminPrintUtils,Constraints.QtySold,String.valueOf(TotalQty));
                Query.PrintingValueSetForIMINReport(mIminPrintUtils,Constraints.AMTNett,"$"+String.format("%.2f", Double.valueOf(TotalNettSales)));
                Query.PrintingValueSetForIMINReport(mIminPrintUtils,Constraints.TaxTotal,"$"+String.format("%.2f", Double.valueOf(TotalTaxSales)));
                Query.PrintingValueSetForIMINReport(mIminPrintUtils,Constraints.AMTDiscount,"$"+String.format("%.2f", Double.valueOf(TotalBillDisount)));
                Query.PrintingValueSetForIMINReport(mIminPrintUtils,Constraints.AMTSurcharge,"$"+String.format("%.2f", Double.valueOf(ServiceChargesSales)));
                Query.PrintingValueSetForIMINReport(mIminPrintUtils,Constraints.RoundAdj,"$"+String.format("%.2f", Double.valueOf(TotalRoundAdjSales)));
                Query.PrintingValueSetForIMINReport(mIminPrintUtils,Constraints.QtyCancel,String.valueOf(BillCancelTotalQty));
                Query.PrintingValueSetForIMINReport(mIminPrintUtils,Constraints.QtyRefund,String.valueOf(BillRefundTotalQty));

            }//            Query.PrintingValueSetForIMINReport(mIminPrintUtils,Constraints.AMTCancel,String.format("%.2f", Double.valueOf(BillCancel)));
        } else {
            str += "\n" + Constraints.LINE;
            str += "\n" + "*TOTAL SALES*";
            str += "\n" + Constraints.DOTLINE;

           // str += "\n" + Constraints.BillPaid + spaceCount(Constraints.BillPaid, "$"+String.format("%.2f", Double.valueOf(TotalNettSales)));
            str += "\n" + Constraints.BillPaid + Query.spaceCount(Constraints.BillPaid, String.format("%.2f", Double.valueOf(TotalNettSales)));
            str += "\n" + Constraints.BillCancel + Query.spaceCount(Constraints.BillCancel, String.format("%.2f", Double.valueOf(BillCancel)));
            str += "\n" + Constraints.BillRefund + Query.spaceCount(Constraints.BillRefund,String.format("%.2f", Double.valueOf(BillRefund)));
            str += "\n" + Constraints.QtySold + Query.spaceCount2(Constraints.QtySold, String.valueOf(TotalQty));
            str += "\n" + Constraints.AMTNett + Query.spaceCount(Constraints.AMTNett, String.format("%.2f", Double.valueOf(TotalNettSales)));

            //        str += "\n" +ENUM.AMTGross + Query.spaceCount(ENUM.AMTGross,String.format("%.2f", Double.valueOf(GrossSales)));

//tax cancellation
            //
            str += "\n" + Constraints.TaxTotal + Query.spaceCount(Constraints.TaxTotal, String.format("%.2f", Double.valueOf(TotalTaxSales)));
            str += "\n" + Constraints.AMTDiscount + Query.spaceCount(Constraints.AMTDiscount, String.format("%.2f", Double.valueOf(TotalBillDisount)));
            str += "\n" + Constraints.AMTSurcharge + Query.spaceCount(Constraints.AMTSurcharge, String.format("%.2f", Double.valueOf(ServiceChargesSales)));
            str += "\n" + Constraints.RoundAdj + Query.spaceCount(Constraints.RoundAdj, String.format("%.2f", Double.valueOf(TotalRoundAdjSales)));
//        str +="\n" +ENUM.AMTCollected + Query.spaceCount(ENUM.AMTCollected,String.format("%.2f", Double.valueOf(TotalNettSales)));
            str += "\n" + Constraints.QtyCancel + Query.spaceCount2(Constraints.QtyCancel, String.valueOf(BillCancelTotalQty));
            str += "\n" + Constraints.QtyRefund + Query.spaceCount2(Constraints.QtyRefund,String.valueOf(BillRefundTotalQty));//12
//            str += "\n" + Constraints.AMTCancel + Query.spaceCount(Constraints.AMTCancel, String.format("%.2f", Double.valueOf(BillCancel)));//12

            rzcd_ts.setBillPaid("$"+String.format("%.2f", Double.valueOf(TotalNettSales)));
            rzcd_ts.setBillCancel("$"+String.format("%.2f", Double.valueOf(BillCancel)));
            rzcd_ts.setBillRefund("$"+String.format("%.2f", Double.valueOf(BillRefund)));
            rzcd_ts.setQtySold(String.valueOf(TotalQty));
            rzcd_ts.setAMTNett("$"+String.format("%.2f", Double.valueOf(TotalNettSales)));
            rzcd_ts.setTaxTotal("$"+String.format("%.2f", Double.valueOf(TotalTaxSales)));
            rzcd_ts.setAMTSurcharge("$"+String.format("%.2f", Double.valueOf(ServiceChargesSales)));
            rzcd_ts.setRoundAdj("$"+String.format("%.2f", Double.valueOf(TotalRoundAdjSales)));
            rzcd_ts.setQtyCancel(String.valueOf(BillCancelTotalQty));
            rzcd_ts.setQtyRefund(String.valueOf(BillRefundTotalQty));

            rzcd.setTotalSales(rzcd_ts);
        }
    }
    Integer CategoryCancelItemDiscount_Qty = 0;
    Double CategoryCancelItemDiscount_Amount = 0.0;
    Integer CategoryCancelTotalBillDiscount_Qty = 0;
    Double CategoryCancelTotalBillDiscount_Amount = 0.0;
    ArrayList<Integer> ClosingPeriodBillIDArr = new ArrayList<Integer>();
    Cursor c_cancel_item_discount = Query.XZDataReportCancelItemDiscount(start,end,ClosingPeriodBillIDArr);
    if (c_cancel_item_discount != null) {
        CategoryCancelItemDiscount_Qty = 0;
        CategoryCancelItemDiscount_Amount = 0.0;
        while (c_cancel_item_discount.moveToNext()) {
            //TotalQtyCategoryCancel += c_cancel.getInt(2);
            //TotalNettSalesCategoryCancel = c_cancel.getDouble(13);
            String bnoo = c_cancel_item_discount.getString(3);
            String sqlSearchReferenceBillNo = "SELECT BillNo FROM SALES " +
                    "WHERE ReferenceBillNo = '"+bnoo+"'";
            Cursor cSearchReferenceBillNo = DBFunc.Query(sqlSearchReferenceBillNo,false);
            if (cSearchReferenceBillNo != null) {
                if (cSearchReferenceBillNo.getCount() == 0) {
                    CategoryCancelItemDiscount_Qty += c_cancel_item_discount.getInt(0);
                    //TotalNettSalesCategoryCancel += c_cancel.getDouble(0) * c_cancel.getDouble(1);
                    CategoryCancelItemDiscount_Amount += c_cancel_item_discount.getDouble(1);
                }
                cSearchReferenceBillNo.close();
            }
        }
        c_cancel_item_discount.close();
    }

    //ProductSales
    if (product_sales.equals("1")){
        //str3 = Query.XZDataReportProductShow(TotalQtyProduct,TotalNettSalesProduct,TaxTotalProduct,TotalBillDisountTaxProduct,TotalNettSalesProductCancel,TotalNettSalesProduct);
        if (terminaltype_check.toUpperCase().equals(Constraints.IMIN)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Query.PrintingValueSetForIMINReport(mIminPrintUtils,Constraints.LINEIMIN, Constraints.HEADER);

                Query.PrintingValueSetForIMINReport(mIminPrintUtils, "*Product SALES*", Constraints.HEADER);
                Query.PrintingValueSetForIMINReport(mIminPrintUtils, Constraints.DOTLINEIMIN, Constraints.HEADER);
                Query.PrintingValueSetForIMINReport(mIminPrintUtils,Constraints.QtySold,String.valueOf(TotalQtyProduct));
                //Query.PrintingValueSetForIMINReport(mIminPrintUtils,Constraints.AMTNett,"$"+String.format("%.2f", Double.valueOf(TotalNettSalesProduct)));
                Query.PrintingValueSetForIMINReport(mIminPrintUtils,Constraints.Amount,"$"+String.format("%.2f", Double.valueOf(TotalNettSalesProduct)));

            }          //Query.PrintingValueSetForIMINReport(mIminPrintUtils,Constraints.QtyCancel,String.valueOf(BillCancelTotalQty));
            //Query.PrintingValueSetForIMINReport(mIminPrintUtils,Constraints.AMTCancel,"$"+String.format("%.2f", Double.valueOf(TotalNettSalesProductCancel)));

        }else {
            str3 = "\n" + Constraints.LINE;
            str3 += "\n" + "*Product SALES*";
            str3 += "\n" + Constraints.DOTLINE;

            str3 += "\n" + Constraints.QtySold + Query.spaceCount2(Constraints.QtySold, String.valueOf(TotalQtyProduct));
            //str3 += "\n" + Constraints.AMTNett + Query.spaceCount(Constraints.AMTNett, String.format("%.2f", Double.valueOf(TotalNettSalesProduct)));
            Double ammmt = MathUtil.Truncate(TotalNettSalesProduct,MainActivity.decimalpoint, 0);
//            str3 += "\n" + Constraints.Amount + Query.spaceCount(Constraints.Amount, String.format("%.2f", Double.valueOf(TotalNettSalesProduct)));
            str3 += "\n" + Constraints.Amount + Query.spaceCount(Constraints.Amount, String.format("%.2f", Double.valueOf(ammmt)));
            //str3 += "\n" +ENUM.TaxTotal + Query.spaceCount(ENUM.TaxTotal,String.format("%.2f", Double.valueOf(TaxTotalProduct)));
            //str3 += "\n" +ENUM.AMTDiscount + Query.spaceCount(ENUM.AMTDiscount,String.format("%.2f", Double.valueOf(TotalBillDisountTaxProduct)));
            //str3 += "\n" +ENUM.AMTSurcharge + Query.spaceCount(ENUM.AMTSurcharge,String.format("%.2f", Double.valueOf(TotalProductServiceCharges)));

            //str3 += "\n" + Constraints.QtyCancel + Query.spaceCount(Constraints.QtyCancel, String.valueOf(BillCancelTotalQty));//7
            //str3 += "\n" + Constraints.AMTCancel + Query.spaceCount(Constraints.AMTCancel,String.format("%.2f", Double.valueOf(TotalNettSalesProductCancel)));//7

            rzcd_ps.setQtySold(String.valueOf(TotalQtyProduct));
            rzcd_ps.setAmount("$"+String.format("%.2f", Double.valueOf(ammmt)));

            rzcd.setProductSales(rzcd_ps);
        }
    }

    if (category.equals("1")){
        // str2 = Query.XZDataReportCategoryShow(TotalQtyCategory,TotalNettSalesCategory,TaxTotalCategory,TotalBillDisountTaxCategory,TotalQtyCategoryCancel,TotalNettSalesCategory);
        if (terminaltype_check.toUpperCase().equals(Constraints.IMIN)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Query.PrintingValueSetForIMINReport(mIminPrintUtils,Constraints.LINEIMIN, Constraints.HEADER);
                Query.PrintingValueSetForIMINReport(mIminPrintUtils,"*Category*",Constraints.HEADER);
                Query.PrintingValueSetForIMINReport(mIminPrintUtils,Constraints.DOTLINEIMIN, Constraints.HEADER);
                Query.PrintingValueSetForIMINReport(mIminPrintUtils,Constraints.QtySold,String.valueOf(TotalQtyCategory));
                Query.PrintingValueSetForIMINReport(mIminPrintUtils,Constraints.AMTNett,"$"+String.format("%.2f", Double.valueOf(TotalNettSalesCategory)));

            }
          //            Query.PrintingValueSetForIMINReport(mIminPrintUtils,Constraints.Amount,"$"+String.format("%.2f", Double.valueOf(TotalNettSalesCategory)));
//            Query.PrintingValueSetForIMINReport(mIminPrintUtils,Constraints.QtyCancel,String.valueOf(TotalQtyCategoryCancel));
//            Query.PrintingValueSetForIMINReport(mIminPrintUtils,Constraints.AMTCancel,"$"+String.format("%.2f", Double.valueOf(TotalNettSalesCategoryCancel)));
//            Query.PrintingValueSetForIMINReport(mIminPrintUtils,"Item Discount Qty",String.valueOf(CategoryCancelItemDiscount_Qty));
//            Query.PrintingValueSetForIMINReport(mIminPrintUtils,"Item Discount Amount","$"+String.format("%.2f", Double.valueOf(CategoryCancelItemDiscount_Amount)));
//            Query.PrintingValueSetForIMINReport(mIminPrintUtils,"Bill Discount Qty",String.valueOf(CategoryCancelTotalBillDiscount_Qty));
//            Query.PrintingValueSetForIMINReport(mIminPrintUtils,"Bill Discount Amount","$"+String.format("%.2f", Double.valueOf(CategoryCancelTotalBillDiscount_Amount)));

        }else {

            str2 = "\n" + Constraints.LINE;
            str2 += "\n" + "*Category*";
            str2 += "\n" + Constraints.DOTLINE;

            str2 += "\n" + Constraints.QtySold + Query.spaceCount2(Constraints.QtySold, String.valueOf(TotalQtyCategory));//12
            str2 += "\n" + Constraints.AMTNett + Query.spaceCount(Constraints.AMTNett,String.format("%.2f", Double.valueOf(TotalNettSalesCategory)));//12


            rzcd_cats.setQtySold(String.valueOf(TotalQtyCategory));
            rzcd_cats.setAMTNett("$"+String.format("%.2f", Double.valueOf(TotalNettSalesCategory)));

            rzcd.setCategorySales(rzcd_cats);
        }
    }
    if (payment.equals("1")){
        //str4 = Query.XZDataReportPaymentShow(PaymentTypeCount,PaymentTypeAmount);
        if (terminaltype_check.toUpperCase().equals(Constraints.IMIN)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Query.PrintingValueSetForIMINReport(mIminPrintUtils,Constraints.LINEIMIN, Constraints.HEADER);
                Query.PrintingValueSetForIMINReport(mIminPrintUtils, "*Payment*", Constraints.HEADER);
                Query.PrintingValueSetForIMINReport(mIminPrintUtils, Constraints.DOTLINEIMIN, Constraints.HEADER);
            }
        }else {
            str4 = "\n" +Constraints.LINE;
            str4 += "\n" + "*Payment*";
            str4 += "\n"  + Constraints.DOTLINE;
        }

        Cursor c_payment = Query.XZDataReportPayment(0,0,"XReport");
        if (c_payment != null) {
            PaymentTypeCount = 0;
            PaymentTypeAmount = "";
            PaymentTypeName = "";
            while (c_payment.moveToNext()) {
                Payment psales = new Payment();
                PaymentTypeCount += c_payment.getInt(0);
                PaymentTypeAmount = "$"+ String.format("%.2f", Double.valueOf(c_payment.getDouble(1)));
                PaymentTypeName = c_payment.getString(0) ;
                if (c_payment.getString(5) != null && c_payment.getInt(5) != 0 && c_payment.getInt(5) == 1
                        && c_payment.getString(6) != null && c_payment.getString(6).length() > 1) {

                    PaymentTypeName = c_payment.getString(6);
                    PaymentTypeName += "("+c_payment.getString(0)+")";
                    PaymentTypeAmount = "$"+ String.format("%.2f", Double.valueOf(c_payment.getDouble(1)));
                }else {
                    PaymentTypeName = c_payment.getString(0) ;
                    PaymentTypeAmount = "$"+ String.format("%.2f", Double.valueOf(c_payment.getDouble(1)));
                }

                if (c_payment.getString(8) != null  && c_payment.getString(8) != "null" && c_payment.getString(8).length() > 0) {
                    PaymentTypeName += "("+c_payment.getString(8)+")" ;
                    PaymentTypeAmount += "";
                }

                if (terminaltype_check.toUpperCase().equals(Constraints.IMIN)) {
                    Query.PrintingValueSetForIMINReport(mIminPrintUtils, PaymentTypeName.toUpperCase(), String.valueOf(PaymentTypeAmount));
                } else {
                    str4 += "\n" + PaymentTypeName.toUpperCase() + Query.spaceCount2(PaymentTypeName.toUpperCase(),
                            String.valueOf(PaymentTypeAmount));


                    psales.setPaymentTypeName(PaymentTypeName.toUpperCase());
                    psales.setPaymentTypeAmount(PaymentTypeAmount);

                    rzcd_pays.add(psales);


                }
            }

            rzcd.setPayment(rzcd_pays);
            c_payment.close();
        }
    }
    if (disocunt.equals("1")){
        //str5 = Query.XZDataReportDiscountShow(DicountCount,DicountAmount);
        if (terminaltype_check.toUpperCase().equals(Constraints.IMIN)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Query.PrintingValueSetForIMINReport(mIminPrintUtils,Constraints.LINEIMIN, Constraints.HEADER);
                Query.PrintingValueSetForIMINReport(mIminPrintUtils, "*Discount*", Constraints.HEADER);
                Query.PrintingValueSetForIMINReport(mIminPrintUtils, Constraints.DOTLINEIMIN, Constraints.HEADER);
            }

        } else {
            str5 = "\n" + Constraints.LINE;
            str5 += "\n" + "*Discount*";
            str5 += "\n" + Constraints.DOTLINE;

        }

        Cursor c_discount = Query.XZDataReportDiscount(start, end, Arr);
        if (c_discount != null) {
//            DicountCount = 0;
            DicountName = "";
            DicountAmount = "";
            while (c_discount.moveToNext()) {
                if (c_discount.getString(0) != null) {
                    if (c_discount.getString(0).length() > 0) {
                        DicountAmount = "$" + String.format("%.2f", Double.valueOf(c_discount.getString(0)));
                        //DicountName += "TotalItemDiscount" + " \n";
                    }
                }
            }
            c_discount.close();
        }
//        str5 += "TotalItemDiscount"+DicountAmount;
        if (terminaltype_check.toUpperCase().equals(Constraints.IMIN)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Query.PrintingValueSetForIMINReport(mIminPrintUtils, "Total Item Dis", String.valueOf(DicountAmount));
            }
        } else {
            str5 += "\n" + "Total Item Dis" + Query.spaceCount2("Total Item Dis", String.valueOf(DicountAmount));
            rzcd_dis.setTotalItemDis(String.valueOf(DicountAmount));
        }
        Cursor c_discount_bill = Query.XZDataReportDiscountBill(start, end, Arr);
        if (c_discount_bill != null) {
            while (c_discount_bill.moveToNext()) {
                if (c_discount_bill.getString(0) != null) {
                    if (c_discount_bill.getString(0).length() > 0) {
                        DicountAmount = "$" + String.format("%.2f", Double.valueOf(c_discount_bill.getString(0)));
                        DicountName = "Total Bill Dis" + " \n";
                    }
                }
            }
            c_discount_bill.close();
        }
        //str5 += "TotalBillDisount"+DicountAmount+ " \n";
        if (terminaltype_check.toUpperCase().equals(Constraints.IMIN)) {
            Query.PrintingValueSetForIMINReport(mIminPrintUtils, "Total Bill Dis", String.valueOf(DicountAmount));
        } else {
            str5 += "\n" + "Total Bill Dis" + Query.spaceCount2("Total Bill Dis", String.valueOf(DicountAmount));

            rzcd_dis.setTotalBillDis(String.valueOf(DicountAmount));
        }
        rzcd.setDiscount(rzcd_dis);
    }
    try {
        //*Cancellation
        if (refund.equals("1")) {
            //str6 += Query.XZDataReportCancellationShow(BillCancel,BillCancelTotalQty,BillTax,TotalBillDisountTax);

            if (terminaltype_check.toUpperCase().equals(Constraints.IMIN)) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    Query.PrintingValueSetForIMINReport(mIminPrintUtils,Constraints.LINEIMIN, Constraints.HEADER);

                    Query.PrintingValueSetForIMINReport(mIminPrintUtils, "*Refund*", Constraints.HEADER);
                    Query.PrintingValueSetForIMINReport(mIminPrintUtils, Constraints.DOTLINEIMIN, Constraints.HEADER);
                    Query.PrintingValueSetForIMINReport(mIminPrintUtils,Constraints.BillRefund,"$"+String.format("%.2f", Double.valueOf(BillRefund)));
                    Query.PrintingValueSetForIMINReport(mIminPrintUtils,Constraints.Quantity,String.valueOf(BillRefundTotalQty));
                    Query.PrintingValueSetForIMINReport(mIminPrintUtils,Constraints.AMTNett,"$"+String.format("%.2f", Double.valueOf(BillRefund)));
                    Query.PrintingValueSetForIMINReport(mIminPrintUtils,Constraints.AMTSurcharge,"$"+String.format("%.2f", Double.valueOf(TotalBillServiceChargesRefund)));

                }
            }else {
                str6 = "\n" +Constraints.LINE;
                str6 += "\n" + "*Refund*";
                str6 += "\n"  + Constraints.DOTLINE;
                str6 += Constraints.BillRefund + Query.spaceCount(Constraints.BillRefund, String.format("%.2f", Double.valueOf(BillRefund)));
                str6 += "\n" + Constraints.Quantity + Query.spaceCount2(Constraints.Quantity, String.valueOf(BillRefundTotalQty));
                str6 += "\n" + Constraints.AMTNett + Query.spaceCount(Constraints.AMTNett, String.format("%.2f", Double.valueOf(BillRefund)));
                //str6 += "\n" +ENUM.TaxTotal + Query.spaceCount(ENUM.TaxTotal,String.format("%.2f", Double.valueOf(TotalBillDisountTaxRefund)));
//        str6 += "\n" +ENUM.AMTDiscount + Query.spaceCount(ENUM.AMTDiscount,String.format("%.2f", Double.valueOf(TotalBillDisountAmountRefund)));
                str6 += "\n" + Constraints.AMTSurcharge + Query.spaceCount(Constraints.AMTSurcharge, String.format("%.2f", Double.valueOf(TotalBillServiceChargesRefund)));

                rzcd_rfs.setBillRefund("$"+String.format("%.2f", Double.valueOf(BillRefund)));
                rzcd_rfs.setQuantity(String.valueOf(BillRefundTotalQty));
                rzcd_rfs.setAMTNett("$"+String.format("%.2f", Double.valueOf(BillRefund)));
                rzcd_rfs.setAMTSurcharge("$"+String.format("%.2f", Double.valueOf(TotalBillServiceChargesRefund)));

                rzcd.setRefund(rzcd_rfs);
            }
        }
    } catch (NullPointerException e){

    }
    //*Cancellation
    if (cancellation.equals("1")){
        //str6 += Query.XZDataReportCancellationShow(BillCancel,BillCancelTotalQty,BillTax,TotalBillDisountTax);
        if (terminaltype_check.toUpperCase().equals(Constraints.IMIN)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Query.PrintingValueSetForIMINReport(mIminPrintUtils,Constraints.LINEIMIN, Constraints.HEADER);

                Query.PrintingValueSetForIMINReport(mIminPrintUtils, "*Cancellation*", Constraints.HEADER);
                Query.PrintingValueSetForIMINReport(mIminPrintUtils, Constraints.DOTLINEIMIN, Constraints.HEADER);
//            Query.PrintingValueSetForIMINReport(mIminPrintUtils, "Bill Cancelled", "$"+String.format("%.2f", Double.valueOf(BillCancel)));
                Query.PrintingValueSetForIMINReport(mIminPrintUtils, Constraints.Quantity, String.valueOf(BillCancelTotalQty));
                Query.PrintingValueSetForIMINReport(mIminPrintUtils, Constraints.AMTNett, "$"+String.format("%.2f", Double.valueOf(BillCancel)));
                Query.PrintingValueSetForIMINReport(mIminPrintUtils, Constraints.TaxTotal, "$"+String.format("%.2f", Double.valueOf(TotalBillDisountTax)));
//            Query.PrintingValueSetForIMINReport(mIminPrintUtils, Constraints.AMTDiscount, "$"+String.format("%.2f", Double.valueOf(TotalBillDisountAmountCancel)));
                Query.PrintingValueSetForIMINReport(mIminPrintUtils, Constraints.AMTSurcharge, "$"+String.format("%.2f", Double.valueOf(TotalBillServiceChargesCancel)));

            }
        } else {
            str6 += "\n" + Constraints.LINE;
            str6 += "\n" + "*Cancellation*";
            str6 += "\n" + Constraints.DOTLINE;

//            str6 += "\n" + "Bill Cancelled" + Query.spaceCount("Bill Cancelled", String.format("%.2f", Double.valueOf(BillCancel)));
            str6 += "\n" + Constraints.Quantity + Query.spaceCount2(Constraints.Quantity, String.valueOf(BillCancelTotalQty));
            str6 += "\n" + Constraints.AMTNett + Query.spaceCount(Constraints.AMTNett,String.format("%.2f", Double.valueOf(BillCancel)));
            //str7 += "\n" +ENUM.Amount + Query.spaceCount(ENUM.Amount,String.format("%.2f", Double.valueOf(TotalNettSalesProductCancel)));
            //str7 += "\n" +ENUM.TaxTotal + Query.spaceCount(ENUM.TaxTotal,String.format("%.2f", Double.valueOf(BillTax)));
            str6 += "\n" + Constraints.TaxTotal + Query.spaceCount(Constraints.TaxTotal, String.format("%.2f", Double.valueOf(TotalBillDisountTax)));
//            str6 += "\n" + Constraints.AMTDiscount + Query.spaceCount(Constraints.AMTDiscount, String.format("%.2f", Double.valueOf(TotalBillDisountAmountCancel)));
            str6 += "\n" + Constraints.AMTSurcharge + Query.spaceCount(Constraints.AMTSurcharge,String.format("%.2f", Double.valueOf(TotalBillServiceChargesCancel)));

            rzcd_cans.setQuantity(String.valueOf(BillCancelTotalQty));
            rzcd_cans.setAMTNett("$"+(String.format("%.2f", Double.valueOf(BillCancel))));
            rzcd_cans.setTaxTotal("$"+String.format("%.2f", Double.valueOf(TotalBillDisountTax)));
            rzcd_cans.setAMTSurcharge("$"+(String.format("%.2f", Double.valueOf(TotalBillServiceChargesCancel))));

            rzcd.setCancellation(rzcd_cans);
        }
    }

//    if (refer_info_sales.equals("1")){
//        str7 = Query.XZDataReportReforeInfoShow();
//    }

    DateFormat dateFormat2 = new SimpleDateFormat(Constraints.dateYMD+" hh:mm aa");
    //DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd hh:mm aa");
    DateFormat dateFormat3 = new SimpleDateFormat(Constraints.dateYMD);
    SimpleDateFormat sdf = new SimpleDateFormat(Constraints.dateYMD, Locale.US);
    final Calendar myCalendar2 = Calendar.getInstance();
    String todayd = sdf.format(myCalendar2.getTime());
    if (reprintZStatus.equals("ReprintZ")) {
        if (todayd.equals(ReportActivity.start)) {
            if (String.valueOf(ReportActivity.previousReport).length() > 4){
                SimpleDateFormat sdf1 = new SimpleDateFormat(Constraints.dateYMD);


                long val = ReportActivity.previousReport;
                Date date=new Date(val);
//                SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yy");
                //String dateText = sdf1.format(date);
                String dateText = dateFormat2.format(date);
                System.out.println(dateText);

                showZClosingReportFooter(dateText,"Reprint", mIminPrintUtils,terminaltype_check,rzcd);

            }else {
                String dateString2 = dateFormat2.format(new Date()).toString();
                showZClosingReportFooter(dateString2,"ZClose", mIminPrintUtils,terminaltype_check,rzcd);
            }
        }else {
            if (String.valueOf(ReportActivity.previousReport).length() > 4){
                SimpleDateFormat sdf1 = new SimpleDateFormat(Constraints.dateYMD);


                long val = ReportActivity.previousReport;
                Date date=new Date(val);
//                SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yy");
                //String dateText = sdf1.format(date);
                String dateText = dateFormat2.format(date);

                showZClosingReportFooter(dateText,"Reprint", mIminPrintUtils,terminaltype_check,rzcd);

            }else {
                SimpleDateFormat sdf1 = new SimpleDateFormat(Constraints.dateYMD);
                Date previous_reportdt1 = null;
                Date previous_reportdt2 = null;
                try {
                    previous_reportdt1 = sdf1.parse(ReportActivity.start);
                    previous_reportdt2 = sdf1.parse(ReportActivity.end);

                    String date1 = dateFormat3.format(previous_reportdt1);
                    String date2 = dateFormat3.format(previous_reportdt2);
                    String date = date1 + "    -    " + date2;

                    showZClosingReportFooter(date, "Reprint",mIminPrintUtils,terminaltype_check,rzcd);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }else {
        String dateString2 = dateFormat2.format(new Date()).toString();
        if (binding.pagerReport.getCurrentItem() > 0){
            if(binding.pagerReport.getCurrentItem() == 4){
                showZClosingReportFooter(dateString2, "XClose", mIminPrintUtils, terminaltype_check,rzcd);
            }else {

                showZClosingReportFooter(dateString2, "ZClose", mIminPrintUtils, terminaltype_check,rzcd);
            }
        }else {
            showZClosingReportFooter(dateString2, "ZClose", mIminPrintUtils, terminaltype_check,rzcd);
        }
    }


//            }
//        }).start();
    return rzcd;
}

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void showZClosingReportFooter(String str, String status, IminPrintUtils mIminPrintUtils, String terminaltype_check,
                                          ReceiptZCloseData rzcd) {
        Log.i("DSF__","statudds___"+status);
        rzcd.setZCloseStatus(status);
        rzcd.setZClosedt(str);
        if (status.equals("Reprint")) {
            if (terminaltype_check.toUpperCase().equals(Constraints.IMIN)) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    Query.PrintingValueSetForIMINReport(mIminPrintUtils, Constraints.DOTLINEIMIN, Constraints.HEADER);
                    Query.PrintingValueSetForIMINReport(mIminPrintUtils,  status.toUpperCase(), "");
                }
           } else {
                str7 += "\n" + Constraints.DOTLINE;
                str7 += "\n" + status.toUpperCase();
            }
        }
        if (terminaltype_check.toUpperCase().equals(Constraints.IMIN)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Query.PrintingValueSetForIMINReport(mIminPrintUtils, Constraints.DOTLINEIMIN, Constraints.HEADER);

                Query.PrintingValueSetForIMINReport(mIminPrintUtils, str, "");
                Query.PrintingValueSetForIMINReport(mIminPrintUtils, Constraints.DOTLINEIMIN, Constraints.HEADER);
                Query.PrintingValueSetForIMINReport(mIminPrintUtils,  status.toUpperCase(), "");
                Query.PrintingValueSetForIMINReport(mIminPrintUtils,"\n", Constraints.HEADER);
                Query.PrintingValueSetForIMINReport(mIminPrintUtils,"\n", Constraints.HEADER);
            }
        } else {
            str7 += "\n" + Constraints.DOTLINE;
            str7 += "\n" + str;
            str7 += "\n" + Constraints.DOTLINE;
            str7 += "\n" + status.toUpperCase();
            str7 += "\n" + "\n" + "\n" + "\n" + "\n";
        }
    }

    private void getSalesALLTotalSales() {
//        if (ReportDateSheetFragment.start_date.length() > 0 || ReportDateSheetFragment.end_date.length() > 0) {
//            start = ReportDateSheetFragment.start_date;
//            end = ReportDateSheetFragment.end_date;
//        }

        if (ReportActivity.start.length() > 0 || ReportActivity.end.length() > 0) {
            start = ReportActivity.start;
            end = ReportActivity.end;
        }
        //Total Sales//
        Cursor c = Query.XZDataReportSales(0,0,"XReport");
        ArrayList<Integer> BillIDArr = new ArrayList<Integer>();
        if (c != null) {
            TotalQty = 0;
            TotalNettSales = 0.0;
            GrossSales = 0.0;
            TotalBillDisount = 0.0;
            TotalTaxSales = 0.0;
            TotalRoundAdjSales = 0.0;
            ServiceChargesSales = 0.0;
            while (c.moveToNext()) {
                TotalQty += c.getInt(0);
                TotalNettSales += c.getDouble(1);
                //GrossSales += c.getDouble(8);
                //GrossSales += c.getDouble(2);
                GrossSales += TotalNettSales - c.getDouble(4);
                TotalBillDisount += c.getDouble(3) + c.getDouble(8);
                TotalTaxSales += c.getDouble(4);
                TotalRoundAdjSales += c.getDouble(5);
                ServiceChargesSales += c.getDouble(6);
            }
            c.close();
        }

        Cursor Cursor_tax = Query.GetTax();
        if (Cursor_tax != null){
            if (Cursor_tax.moveToNext()){
                String taxRate = Cursor_tax.getString(0);
                String taxType = Cursor_tax.getString(1);
                String taxName = Cursor_tax.getString(2);
                Log.i("DF___","taxType____"+taxType);
                if (taxType.equals("2")){
                    //Cursor ctax = Query.XZDataReportSales(0,0,"ZClose");
                    Cursor ctax = Query.XZDataReportSales(0,0,"XReport");
                    if (ctax != null) {
                        TotalTaxSales = 0.0;
                        Double TotalNettSalestax = 0.0;
                        while (ctax.moveToNext()) {
                            TotalNettSalestax = ctax.getDouble(1);


                            double amt_inclusive = Query.calculateInclusive(Double.valueOf(TotalNettSalestax), Integer.parseInt(taxRate));
                            //Log.i("TotalNettSalestax__","TotalNettSalestax__"+TotalNettSalestax+"__"+taxRate+"_"+amt_inclusive);
                            TotalTaxSales += Double.valueOf(String.format("%.2f", amt_inclusive));
                            Log.i("TotalNettSalestaxdddd__", "TotalNettSalestaxx__" + TotalNettSalestax + "__" + taxRate + "_" + amt_inclusive);
                        }
                        ctax.close();
                    }
                    //str_price += "\n" +"$"+ String.format("%.2f", Double.valueOf(TotalTaxSales));
                    // double amt_inclusive = Query.calculateInclusive(Double.valueOf(TotalNettSales),Integer.parseInt(taxRate));
//                        TotalTaxSales = amt_inclusive;
                } else {
                    //str_price += "\n" +"$"+ String.format("%.2f", Double.valueOf(TotalTaxSales));
                }
            }
            Cursor_tax.close();
        }
//        Cursor c_payment = Query.XZDataReportPayment(0,0,"XReport");
//        if (c_payment != null) {
//            PaymentTypeCount = 0;
//            PaymentTypeAmount = "";
//            PaymentTypeName = "";
//            while (c_payment.moveToNext()) {
////                PaymentTypeCount += c_payment.getInt(0);
////                //PaymentTypeAmount += c_payment.getString(1) +" \n";
////                PaymentTypeAmount += "$"+ String.format("%.2f", Double.valueOf(c_payment.getString(1))) +" \n";
////                PaymentTypeName += c_payment.getString(3) +" \n";
//
//                //PaymentTypeAmount += "$"+ String.format("%.2f", Double.valueOf(c_payment.getDouble(1) / 2)) +" \n";
//                PaymentTypeAmount += "$"+ String.format("%.2f", Double.valueOf(c_payment.getDouble(1))) +" \n";
//                //PaymentTypeName += c_payment.getString(3) +" \n";
//                PaymentTypeName += c_payment.getString(0) +" \n";
//            }
//            c_payment.close();
//        }

        //Payment Type
        Cursor c_payment = Query.XZDataReportPayment(0,0,"XReport");
        if (c_payment != null) {
            PaymentTypeCount = 0;
            PaymentTypeAmount = "";
            PaymentTypeName = "";
            while (c_payment.moveToNext()) {

                if (c_payment.getDouble(1) > 0.0) {

                    if (c_payment.getString(5) != null && c_payment.getInt(5) != 0 && c_payment.getInt(5) == 1
                            && c_payment.getString(6) != null && c_payment.getString(6).length() > 1) {
                        PaymentTypeName += c_payment.getString(6) + " ("+c_payment.getString(0)+")" + " \n";
                        PaymentTypeAmount += "$" + String.format("%.2f", Double.valueOf(c_payment.getDouble(1))) + " \n";
                    }else {
                        PaymentTypeName += c_payment.getString(0) + " \n";
                        PaymentTypeAmount += "$" + String.format("%.2f", Double.valueOf(c_payment.getDouble(1))) + " \n";
                    }
                    //PaymentTypeName += c_payment.getString(3) +" \n";

                }
            }
            c_payment.close();
        }
        Cursor c_discount = Query.XZDataReportDiscount(start,end,Arr);
        if (c_discount != null) {
//            DicountCount = 0;
            DicountName = "";
            DicountAmount = "";
            while (c_discount.moveToNext()) {
                if (c_discount.getString(0) != null) {
                    if (c_discount.getString(0).length() > 0) {
                        DicountAmount += "$" + String.format("%.2f", Double.valueOf(c_discount.getString(0))) + " \n";
                        DicountName += "TotalItemDiscount" + " \n";
                    }
                }
            }
            c_discount.close();
        }
        Cursor c_discount_bill = Query.XZDataReportDiscountBill(start,end,Arr);
        if (c_discount_bill != null) {
            while (c_discount_bill.moveToNext()) {
                if (c_discount_bill.getString(0) != null) {
                    if (c_discount_bill.getString(0).length() > 0) {
                        DicountAmount += "$" + String.format("%.2f", Double.valueOf(c_discount_bill.getString(0))) + " \n";
                        DicountName += "TotalBillDisount" + " \n";
                    }
                }
            }
            c_discount_bill.close();
        }
        Cursor c_tax = Query.XZDataReportTax(start,end,Arr);
        if (c_tax != null) {
            TotalTaxesCount = 0;
            TotalTaxesAmount = 0.0;
            while (c_tax.moveToNext()) {
                //TotalTaxesCount += c_tax.getInt(0);
                TotalTaxesAmount += c_tax.getDouble(0);
            }
            c_tax.close();
        }
        //ProductSalesQuery
        Cursor c_product = Query.XZDataReportProduct(0,0,"XReport");
        if (c_product != null) {
            TotalQtyProduct = 0;
            TotalNettSalesProduct = 0.0;
            GrossSalesProduct = "0";
            TotalBillDisountProduct = "0";
            BillTaxProduct = "0";
            TotalBillDisountTaxProduct = 0.0;
            TotalProductServiceCharges = 0.0;
            while (c_product.moveToNext()) {
                TotalQtyProduct += c_product.getInt(0);
//                if (c_product.getDouble(1) > 0.0) {
//                    TotalNettSalesProduct += c_product.getDouble(1) - c_product.getDouble(4);
//                } else {
//                    TotalNettSalesProduct += c_product.getDouble(1);
//                }
                TotalNettSalesProduct += c_product.getDouble(1) + c_product.getDouble(4);
                GrossSalesProduct += c_product.getString(2);
                //TaxTotalProduct = c.getString(14);
                TotalBillDisountProduct += c_product.getString(3);
                BillTaxProduct += c_product.getString(4);
                TotalBillDisountTaxProduct += c_product.getDouble(5);
                TotalProductServiceCharges += c_product.getDouble(6);
            }
            c_product.close();
        }

        Integer id = Query.CategoryCheck();
        if (id > 0) {
            Cursor c_category = Query.XZDataReportCategory(start, end, Arr);

            if (c_category != null) {
                TotalQtyCategory = 0;
                TotalNettSalesCategory = 0.0;
                GrossSalesCategory = 0.0;
                TotalBillDisountCategory = 0.0;
                BillCancelTotalQtyCategory = 0;
                TotalBillDisountTaxCategory = 0.0;
                while (c_category.moveToNext()) {
//                TotalQtyCategory += c_category.getInt(0);
//                TotalNettSalesCategory += c_category.getDouble(1);
//                GrossSalesCategory += c_category.getDouble(2);
//                //TaxTotalCategory = c.getString(14);
//                TotalBillDisountCategory += c_category.getDouble(3);
//                BillCancelTotalQtyCategory += c_category.getInt(0);
//                //BillTaxCategory = c.getString(14);
//                TotalBillDisountTaxCategory += c_category.getDouble(3);
                    String bnoo = c_category.getString(3);
                    String sqlSearchReferenceBillNo = "SELECT BillNo FROM SALES " +
                            "WHERE ReferenceBillNo = '" + bnoo + "'";
                    Cursor cSearchReferenceBillNo = DBFunc.Query(sqlSearchReferenceBillNo, false);
                    if (cSearchReferenceBillNo != null) {
                        if (cSearchReferenceBillNo.getCount() == 0) {
                            TotalQtyCategory += c_category.getInt(0);
                            //TotalNettSalesCategory += c_category.getDouble(0) * c_category.getDouble(1);
                            TotalNettSalesCategory += c_category.getDouble(1);
                        }
                        cSearchReferenceBillNo.close();
                    }
                }
                c_category.close();
            }

            Cursor c_cancel = Query.XZDataReportCancel(start, end, Arr);

            if (c_cancel != null) {
                TotalQtyCategoryCancel = 0;
                TotalNettSalesCategoryCancel = 0.0;
                while (c_cancel.moveToNext()) {
                    //TotalQtyCategoryCancel += c_cancel.getInt(2);
                    //TotalNettSalesCategoryCancel = c_cancel.getDouble(13);
                    TotalQtyCategoryCancel += c_cancel.getInt(0);
                    //TotalNettSalesCategoryCancel += c_cancel.getDouble(0) * c_cancel.getDouble(1);
                    TotalNettSalesCategoryCancel += c_cancel.getDouble(1);
                    Log.i("HHHHEEEELLLPO", "hello_" + TotalQtyCategoryCancel + "__" + TotalNettSalesCategoryCancel);
                }
                c_cancel.close();
            }

            Cursor c_productcancel = Query.XZDataReportProductCancel(0, 0, "XReport");

            if (c_productcancel != null) {
                TotalNettSalesProductCancel = 0.0;
                BillCancelTotalQty = 0;
                BillCancel = 0.0;
                TotalBillDisountTax = 0.0;
                TotalBillDisountAmountCancel = 0.0;
                TotalBillServiceChargesCancel = 0.0;
                while (c_productcancel.moveToNext()) {
                    //                String sql = "SELECT count(ID),BillNo,SUM(TotalQty),SUM(SubTotal),SUM(Totalamount)," +
//                        "PaymentTypeID,SUM(PaymentAmount),strftime('%Y-%m-%d %H:%M:%S', DateTime / 1000, 'unixepoch'),
//                        SUM(GrossSales),SUM(TotalItemDisount)," +
//                        "SUM(TotalBillDisount),SUM(GrossTotal),SUM(ServiceCharges),SUM(TotalNettSales),SUM(TotalTaxes)," +
//                        "SalesDateTime FROM Sales " +
                    //TotalNettSalesProductCancel = c_productcancel.getDouble(13);
                    TotalNettSalesProductCancel += c_productcancel.getDouble(13);
                    BillCancelTotalQty += c_productcancel.getInt(2);
                    BillCancel += c_productcancel.getDouble(13);
                    //BillTax = c.getString(14);
                    TotalBillDisountTax += c_productcancel.getDouble(14);
                    TotalBillDisountAmountCancel += c_productcancel.getDouble(13);
                    TotalBillServiceChargesCancel += c_productcancel.getDouble(12);
//                TotalNettSalesProductCancel = c_productcancel.getDouble(13);
//                BillCancelTotalQty += c_productcancel.getInt(2);
//                BillCancel += c_productcancel.getDouble(13);
//                //BillTax = c.getString(14);
//                TotalBillDisountTax += c_productcancel.getDouble(14);
//                TotalBillDisountAmountCancel += c_productcancel.getDouble(13);
//                TotalBillServiceChargesCancel += c_productcancel.getDouble(12);
                }
                c_productcancel.close();
            }
        }

//        Cursor Cursor_tax = Query.GetTax();
//        if (Cursor_tax != null){
//            if (Cursor_tax.moveToNext()){
//                String taxRate = Cursor_tax.getString(0);
//                Integer taxType = Cursor_tax.getInt(1);
//                String taxName = Cursor_tax.getString(2);
//                //1=> None , 2=>Inclusive , 3=>Exclusive
//                if (taxType == 2){
//                    double amt_inclusive = Query.calculateInclusive(Double.valueOf(TotalNettSalesProductCancel),Integer.parseInt(taxRate));
//                    TotalBillDisountTax = amt_inclusive;
//                }
//            }
//            Cursor_tax.close();
//        }

//        Cursor_tax = Query.GetTax();
//        if (Cursor_tax != null){
//            if (Cursor_tax.moveToNext()){
//                String taxRate = Cursor_tax.getString(0);
//                String taxType = Cursor_tax.getString(1);
//                String taxName = Cursor_tax.getString(2);
//                if (taxType.equals("2")){
//                    Cursor ctax = Query.XZDataReportProductCancel(0,0,"XReport");
//                    if (ctax != null) {
//                        TotalTaxSales = 0.0;
//                        Double TotalNettSalestax = 0.0;
//                        while (ctax.moveToNext()) {
//                            TotalNettSalestax = ctax.getDouble(13);
//
//
//                            double amt_inclusive = Query.calculateInclusive(Double.valueOf(TotalNettSalestax), Integer.parseInt(taxRate));
//                            //Log.i("TotalNettSalestax__","TotalNettSalestax__"+TotalNettSalestax+"__"+taxRate+"_"+amt_inclusive);
//                            TotalTaxSales += Double.valueOf(String.format("%.2f", amt_inclusive));
//                            Log.i("TotalNettSalestax__", "TotalNettSalestaxx__" + TotalNettSalestax + "__" + taxRate + "_" + amt_inclusive);
//                        }
//                        ctax.close();
//                    }
//                    //str_price += "\n" +"$"+ String.format("%.2f", Double.valueOf(TotalTaxSales));
//                    // double amt_inclusive = Query.calculateInclusive(Double.valueOf(TotalNettSales),Integer.parseInt(taxRate));
////                        TotalTaxSales = amt_inclusive;
//                } else {
//                    //str_price += "\n" +"$"+ String.format("%.2f", Double.valueOf(TotalTaxSales));
//                }
//            }
//            Cursor_tax.close();
//        }

        Cursor c_productrefund = Query.XZDataReportProductRefund(0,0,"XReport");

        if (c_productrefund != null) {
            TotalNettSalesProductRefund = 0.0;
            BillRefundTotalQty = 0;
            BillRefund = 0.0;
            TotalBillDisountTaxRefund = 0.0;
            TotalBillDisountAmountRefund = 0.0;
            TotalBillServiceChargesRefund = 0.0;
            while (c_productrefund.moveToNext()) {
                TotalNettSalesProductRefund += (-1) * c_productrefund.getDouble(13);
                BillRefundTotalQty += (-1) * c_productrefund.getInt(2);
                BillRefund += (-1) * c_productrefund.getDouble(13);
                TotalBillDisountTaxRefund += (-1) * c_productrefund.getDouble(14);
                TotalBillDisountAmountRefund += (-1) * c_productrefund.getDouble(13);
                TotalBillServiceChargesRefund += (-1) * c_productrefund.getDouble(12);
            }
            c_productrefund.close();
        }
    }



    private void getSalesALLTotalSales(String start, String end) {
        Cursor c = Query.XZDataReportSales(0,0,"ZClose");
        ArrayList<Integer> BillIDArr = new ArrayList<Integer>();
        if (c != null) {
            TotalQty = 0;
            TotalNettSales = 0.0;
            GrossSales = 0.0;
            TotalBillDisount = 0.0;
            TotalTaxSales = 0.0;
            TotalRoundAdjSales = 0.0;
            ServiceChargesSales = 0.0;
            while (c.moveToNext()) {
                TotalQty += c.getInt(0);
                TotalNettSales += c.getDouble(1);
                //GrossSales += c.getDouble(8);
                GrossSales += c.getDouble(2);
                TotalBillDisount += c.getDouble(3);
                TotalTaxSales += c.getDouble(4);
                TotalRoundAdjSales += c.getDouble(5);
                ServiceChargesSales += c.getDouble(6);
            }
            c.close();
        }
//        Cursor c_payment = Query.XZDataReportPayment(start,end,Arr);
//        if (c_payment != null) {
//            PaymentTypeCount = 0;
//            PaymentTypeAmount = "";
//            PaymentTypeName = "0";
//            String str = "";
//            while (c_payment.moveToNext()) {
//                PaymentTypeCount += c_payment.getInt(0);
////                PaymentTypeAmount += c_payment.getDouble(1);
////                PaymentTypeName += c_payment.getString(2);
//                PaymentTypeName += c_payment.getString(3) +" \n";
//                //str += "$"+ String.format("%.2f", Double.valueOf(c_payment.getString(1))) +" \n";
//                str += "$"+ c_payment.getString(1) +" \n";
//                //PaymentTypeAmount = spaceCount(PaymentTypeName,String.valueOf(str));
//                PaymentTypeAmount = String.valueOf(str);
//            }
//            c_payment.close();
//        }
//        Cursor c_payment = Query.XZDataReportPayment(0,0,"ZClose");
//        if (c_payment != null) {
//            PaymentTypeCount = 0;
//            PaymentTypeAmount = "";
//            PaymentTypeName = "";
//            while (c_payment.moveToNext()) {
//                PaymentTypeCount = c_payment.getInt(0);
//               // PaymentTypeName += c_payment.getString(3);
//                //PaymentTypeAmount += "$"+ String.format("%.2f", Double.valueOf(c_payment.getString(1)));
//                PaymentTypeName = "$"+ String.format("%.2f", Double.valueOf(c_payment.getString(1)));
//                PaymentTypeAmount+= c_payment.getString(3) + Query.spaceCount2(c_payment.getString(3),String.valueOf(PaymentTypeName)) + "\n";
//                //PaymentTypeName += c_payment.getString(3) +" \n";
//            }
//            c_payment.close();
//        }
//        //Payment Type
//        Cursor c_payment = Query.XZDataReportPayment(0,0,"ZClose");
//        if (c_payment != null) {
//            PaymentTypeCount = 0;
//            PaymentTypeAmount = "";
//            PaymentTypeName = "";
//            while (c_payment.moveToNext()) {
//                if (c_payment.getDouble(1) > 0.0) {
//                    //PaymentTypeCount += c_payment.getInt(0);
//                    //PaymentTypeAmount += c_payment.getString(1) +" \n";
//                    //PaymentTypeAmount += "$"+ String.format("%.2f", Double.valueOf(c_payment.getDouble(1) / 2)) +" \n";
//                    Log.i("DFDFD___","df____"+c_payment.getString(5));
//                    if (c_payment.getString(5) != null && c_payment.getInt(5) == 1) {
//                        PaymentTypeName += c_payment.getString(6) + " ("+c_payment.getString(0)+")" + " \n";
//                        PaymentTypeAmount += "$" + String.format("%.2f", Double.valueOf(c_payment.getDouble(1))) + " \n";
//                    }else {
//                        PaymentTypeName += c_payment.getString(0) + " \n";
//                        PaymentTypeAmount += "$" + String.format("%.2f", Double.valueOf(c_payment.getDouble(1))) + " \n";
//                    }
//                    //PaymentTypeName += c_payment.getString(3) +" \n";
//
//                }
//            }
//            c_payment.close();
//        }
//        Cursor c_discount = Query.XZDataReportDiscount(start,end,Arr);
////        if (c_discount != null) {
////            DicountCount = 0;
////            DicountAmount = 0.0;
////            while (c_discount.moveToNext()) {
////                DicountCount += c_discount.getInt(0);
////                DicountAmount += c_discount.getDouble(1) + c_discount.getDouble(2);
////
////            }
////            c_discount.close();
////        }
//        if (c_discount != null) {
////            DicountCount = 0;
//            DicountName = "";
//            DicountAmount = "";
//            while (c_discount.moveToNext()) {
//                DicountAmount += "$"+ String.format("%.2f", Double.valueOf(c_discount.getString(0))) +" \n";
//                DicountName += "TotalItemDiscount"+" \n";
//            }
//            c_discount.close();
//        }
////        Cursor c_tax = Query.XZDataReportTax(start,end,Arr);
////        if (c_tax != null) {
////            TotalTaxesCount = 0;
////            TotalTaxesAmount = 0.0;
////            while (c_tax.moveToNext()) {
////                TotalTaxesCount += c_tax.getInt(0);
////                TotalTaxesAmount += c_tax.getDouble(1);
////            }
////            c_tax.close();
////        }
        Cursor c_discount = Query.XZDataReportDiscount(start,end,Arr);
        if (c_discount != null) {
//            DicountCount = 0;
            DicountName = "";
            DicountAmount = "";
            while (c_discount.moveToNext()) {
                if (c_discount.getString(0) != null) {
                    if (c_discount.getString(0).length() > 0) {
                        DicountAmount = "$" + String.format("%.2f", Double.valueOf(c_discount.getString(0)));
                        //DicountName += "TotalItemDiscount" + " \n";
                        DicountName+= "TotalItemDiscount" + Query.spaceCount2("TotalItemDiscount",String.valueOf(DicountAmount))+ " \n";
                    }
                }
            }
            c_discount.close();
        }
        Cursor c_discount_bill = Query.XZDataReportDiscountBill(start,end,Arr);
        if (c_discount_bill != null) {
            while (c_discount_bill.moveToNext()) {
                if (c_discount_bill.getString(0) != null) {
                    if (c_discount_bill.getString(0).length() > 0) {
                        DicountAmount = "$" + String.format("%.2f", Double.valueOf(c_discount_bill.getString(0)));
                        //DicountName += "TotalBillDisount" + " \n";
                        DicountName+= "TotalBillDisount" + Query.spaceCount2("TotalBillDisount",String.valueOf(DicountAmount))+ " \n";

                    }
                }
            }
            c_discount_bill.close();
        }
        Cursor c_tax = Query.XZDataReportTax(start,end,Arr);
        if (c_tax != null) {
            TotalTaxesCount = 0;
            TotalTaxesAmount = 0.0;
            while (c_tax.moveToNext()) {
                //TotalTaxesCount += c_tax.getInt(0);
                TotalTaxesAmount += c_tax.getDouble(0);
            }
            c_tax.close();
        }
        //ProductSalesQuery
        Cursor c_product = Query.XZDataReportProduct(0,0,"ZClose");
        if (c_product != null) {
            TotalQtyProduct = 0;
            TotalNettSalesProduct = 0.0;
            GrossSalesProduct = "0";
            TotalBillDisountProduct = "0";
            BillTaxProduct = "0";
            TotalBillDisountTaxProduct = 0.0;
            TotalProductServiceCharges = 0.0;
            while (c_product.moveToNext()) {
                TotalQtyProduct += c_product.getInt(0);
                TotalNettSalesProduct += c_product.getDouble(1)+c_product.getDouble(4);
                GrossSalesProduct += c_product.getString(2);
                //TaxTotalProduct = c.getString(14);
                TotalBillDisountProduct += c_product.getString(3);
                BillTaxProduct += c_product.getString(4);
                TotalBillDisountTaxProduct += c_product.getDouble(3);
                TotalProductServiceCharges += c_product.getDouble(3);
            }
            c_product.close();
        }

        Cursor c_category = Query.XZDataReportCategory(start,end,Arr);

        if (c_category != null) {
            TotalQtyCategory = 0;
            TotalNettSalesCategory = 0.0;
            GrossSalesCategory = 0.0;
            TotalBillDisountCategory = 0.0;
            BillCancelTotalQtyCategory = 0;
            TotalBillDisountTaxCategory = 0.0;
            while (c_category.moveToNext()) {
//                TotalQtyCategory += c_category.getInt(0);
//                TotalNettSalesCategory += c_category.getDouble(1);
//                GrossSalesCategory += c_category.getDouble(2);
//                //TaxTotalCategory = c.getString(14);
//                TotalBillDisountCategory += c_category.getDouble(3);
//                BillCancelTotalQtyCategory += c_category.getInt(0);
//                //BillTaxCategory = c.getString(14);
//                TotalBillDisountTaxCategory += c_category.getDouble(3);
                String bnoo = c_category.getString(3);
                String sqlSearchReferenceBillNo = "SELECT BillNo FROM SALES " +
                        "WHERE ReferenceBillNo = '"+bnoo+"'";
                Cursor cSearchReferenceBillNo = DBFunc.Query(sqlSearchReferenceBillNo,false);
                if (cSearchReferenceBillNo != null) {
                    if (cSearchReferenceBillNo.getCount() == 0) {
                        TotalQtyCategory += c_category.getInt(0);
                        //TotalNettSalesCategory += c_category.getDouble(0) * c_category.getDouble(1);
                        TotalNettSalesCategory += c_category.getDouble(1);
                    }
                    cSearchReferenceBillNo.close();
                }
            }
            c_category.close();
        }


//
//        Cursor c_cancel = Query.XZDataReportCancel(start,end,Arr);
//        if (c_cancel != null) {
//            TotalQtyCategoryCancel = 0;
//            TotalNettSalesCategoryCancel = 0.0;
//            while (c_cancel.moveToNext()) {
//                //TotalQtyCategoryCancel += c_cancel.getInt(2);
//                //TotalNettSalesCategoryCancel = c_cancel.getDouble(13);
//                TotalQtyCategoryCancel += c_cancel.getInt(0);
//                //TotalNettSalesCategoryCancel += c_cancel.getDouble(0) * c_category.getDouble(1);
//                TotalNettSalesCategoryCancel += c_cancel.getDouble(1);
//            }
//            c_cancel.close();
//        }

//        Cursor c_productcancel = Query.XZDataReportProductCancel(0,0,"ZClose");
//
//        if (c_productcancel != null) {
//            TotalNettSalesProductCancel = 0.0;
//            BillCancelTotalQty = 0;
//            BillCancel = 0.0;
//            TotalBillDisountTax = 0.0;
//            while (c_productcancel.moveToNext()) {
//                TotalNettSalesProductCancel = c_productcancel.getDouble(13);
//                BillCancelTotalQty += c_productcancel.getInt(2);
//                BillCancel += c_productcancel.getDouble(13);
//                //BillTax = c.getString(14);
//                TotalBillDisountTax += c_productcancel.getDouble(9);
//            }
//            c_productcancel.close();
//        }
//        if (ReportDateSheetFragment.start_date.length() > 0 || ReportDateSheetFragment.end_date.length() > 0) {
//            start = ReportDateSheetFragment.start_date;
//            end = ReportDateSheetFragment.end_date;
//        }
//        Cursor c = Query.XZDataReportSales(start,end,Arr);
//        if (c != null) {
//            while (c.moveToNext()) {
//                if (!c.isNull(0)) {
//                    TotalQty += c.getInt(2);
//                    TotalNettSales += c.getDouble(13);
//                    GrossSales += c.getDouble(8);
//                    TotalBillDisount += c.getDouble(9);
//                }
//            }
//            c.close();
//        }
//        Cursor c_payment = Query.XZDataReportPayment(start,end,Arr);
//        if (c_payment != null) {
//            while (c_payment.moveToNext()) {
//                if (!c_payment.isNull(0)) {
//                    PaymentTypeCount += c_payment.getInt(0);
//                    PaymentTypeAmount += c_payment.getDouble(1);
//                }
//            }
//            c_payment.close();
//        }
//        Cursor c_discount = Query.XZDataReportDiscount(start,end,Arr);
//        if (c_discount != null) {
//            while (c_discount.moveToNext()) {
//                if (!c_discount.isNull(0)) {
//                    DicountCount += c_discount.getInt(0);
//                    DicountAmount += c_discount.getDouble(1) + c_discount.getDouble(2);
//                }
//            }
//            c_discount.close();
//        }
//        Cursor c_tax = Query.XZDataReportTax(start,end,Arr);
//        if (c_tax != null) {
//            while (c_tax.moveToNext()) {
//                if (!c_tax.isNull(0)) {
//                    //TotalTaxesCount = c.getString(0);
//                    //TotalTaxesAmount = c.getString(1);
//                }
//            }
//            c_tax.close();
//        }
//
//        Cursor c_product = Query.XZDataReportProduct(start,end,Arr);
//
//        if (c_product != null) {
//            while (c_product.moveToNext()) {
//                if (!c_product.isNull(0)) {
//                    TotalQtyProduct += c_product.getInt(2);
//                    TotalNettSalesProduct = c_product.getDouble(13);
//                    GrossSalesProduct = c_product.getString(8);
//                    //TaxTotalProduct = c.getString(14);
//                    TotalBillDisountProduct = c_product.getString(9);
//                    BillTaxProduct = c_product.getString(14);
//                    TotalBillDisountTaxProduct = c_product.getDouble(9);
//                }
//            }
//            c_product.close();
//        }
//
//        Cursor c_category = Query.XZDataReportCategory(start,end,Arr);
//
//        if (c_category != null) {
//            while (c_category.moveToNext()) {
//                if (!c_category.isNull(0)) {
//                    TotalQtyCategory += c_category.getInt(2);
//                    TotalNettSalesCategory += c_category.getDouble(13);
//                    GrossSalesCategory += c_category.getDouble(8);
//                    //TaxTotalCategory = c.getString(14);
//                    TotalBillDisountCategory += c_category.getDouble(9);
//                    BillCancelTotalQtyCategory += c_category.getInt(2);
//                    //BillTaxCategory = c.getString(14);
//                    TotalBillDisountTaxCategory += c_category.getDouble(9);
//                }
//            }
//            c_category.close();
//        }
//
//        Cursor c_cancel = Query.XZDataReportCancel(start,end,Arr);
//        if (c_cancel != null) {
//            while (c_cancel.moveToNext()) {
//                if (!c_cancel.isNull(0)) {
////                    if (c.getString(2).equals(null)){
////                        TotalQtyCategoryCancel = "0";
////                    }else {
//                         TotalQtyCategoryCancel += c_cancel.getInt(2);
////                    }
//                    TotalNettSalesCategoryCancel = c_cancel.getDouble(13);
//                }
//            }
//            c_cancel.close();
//        }
//
//        Cursor c_productcancel = Query.XZDataReportProductCancel(start,end,Arr);
//
//        if (c_productcancel != null) {
//            while (c.moveToNext()) {
//                if (!c_productcancel.isNull(0)) {
//                    TotalNettSalesProductCancel = c_productcancel.getDouble(13);
//                    BillCancelTotalQty += c_productcancel.getInt(2);
//                    BillCancel += c_productcancel.getDouble(13);
//                    //BillTax = c.getString(14);
//                    TotalBillDisountTax += c_productcancel.getDouble(9);
//                }
//            }
//            c_productcancel.close();
//        }
    }

    private List<ReferData> ListRefers(){
        List<ReferData> data = new ArrayList<ReferData>();
        Cursor c = DBFunc.Query("SELECT DISTINCT(ReferID) FROM Bill ORDER BY ReferID", false);
        if(c==null){//DB Error
            return null;
        }

        List<Integer> _tmpreferid = new ArrayList<Integer>();

        while(c.moveToNext()){
            if(c.isNull(0)){
                _tmpreferid.add(-1);
            }else{
                _tmpreferid.add(c.getInt(0));
            }
        }
        c.close();



        for(int id : _tmpreferid){
            if(id == -1){
                continue;
            }
            Cursor _tmp = DBFunc.Query("SELECT DISTINCT(Refer) FROM Bill WHERE ReferID = "+id, false);//get list of name

            if(_tmp==null){
                return null;
            }

            while(_tmp.moveToNext()){
                data.add(new ReferData(id,_tmp.getString(0)));
            }

            _tmp.close();
        }

        Collections.sort(data, new Comparator<ReferData>(){
            @Override
            public int compare(ReferData lhs, ReferData rhs) {
                return lhs.name.compareTo(rhs.name);
            }
        });

        data.add(0,new ReferData(-1,"(None)"));
        data.add(0,new ReferData(-2,"All"));
        return data;
    }

    private List<UserData> ListUsers(){
        List<UserData> data = new ArrayList<UserData>();


        Cursor c_user = DBFunc.Query("SELECT DISTINCT(user_id) FROM userlog ORDER BY user_id", false);
        if(c_user==null){//DB Error
            return null;
        }

        List<Integer> _tmpuserid = new ArrayList<Integer>();

        while(c_user.moveToNext()){
            _tmpuserid.add(c_user.getInt(0));
        }

        c_user.close();

        //Map<Integer,List<String>> data = new HashMap<Integer,List<String>>();
        for(int id : _tmpuserid){
            if(id == -1 && Allocator.cashierID != -1){
                continue;
            }
            Cursor _tmp = DBFunc.Query("SELECT DISTINCT(name) FROM userlog WHERE user_id = "+id, false);//get list of name

            if(_tmp==null){
                return null;
            }

            while(_tmp.moveToNext()){
                data.add(new UserData(id,_tmp.getString(0)));
            }

            _tmp.close();
        }

        _tmpuserid.clear();


        c_user = DBFunc.Query("SELECT DISTINCT(CashierID) FROM Bill ORDER BY CashierID", false);
        if(c_user==null){//DB Error
            return null;
        }

        while(c_user.moveToNext()){
            _tmpuserid.add(c_user.getInt(0));
        }

        c_user.close();

        for(int id : _tmpuserid){
            Cursor _tmp = DBFunc.Query("SELECT DISTINCT(Cashier) FROM Bill WHERE CashierID = "+id, false);//get list of name

            if(_tmp==null){
                return null;
            }

            while(_tmp.moveToNext()){
                boolean exist = false;

                for(UserData u:data){
                    if(u.id==id && u.getName().equals(_tmp.getString(0))){
                        exist = true;
                        break;
                    }
                }

                if(!exist){
                    data.add(new UserData(id,_tmp.getString(0)));
                }

            }

            _tmp.close();
        }

        _tmpuserid.clear();

        Collections.sort(data, new Comparator<UserData> (){
            @Override
            public int compare(UserData lhs, UserData rhs) {
                return lhs.name.compareTo(rhs.name);
            }
        });


        return data;
    }

    class ReferData{
        private int id = -1;
        private String name = "";
        public ReferData(int id, String name){
            this.id = id;
            this.name = name;
        }

        public int getID(){
            return id;
        }

        public String getName(){
            return name;
        }

        public String toString(){
            return name;
        }
    }

    class UserData{
        private int id = -1;
        private String name = "";
        public UserData(int id, String name){
            this.id = id;
            this.name = name;
        }

        public int getID(){
            return id;
        }

        public String getName(){
            return name;
        }

        public String toString(){
            return name;
        }
    }

    class UserLogData{
        private int id = -1;
        private String name = "";
        private Map<Date, String> log = new HashMap<Date, String>();
        public UserLogData(int id, String name){
            this.id = id;
            this.name = name;
        }

        public int getID(){
            return id;
        }

        public String getName(){
            return name;
        }

        public Map<Date, String> getLog(){
            return log;
        }

    }

}
