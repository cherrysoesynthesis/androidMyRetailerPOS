package com.dcs.myretailer.app.Activity;

import static com.google.zxing.BarcodeFormat.DATA_MATRIX;

import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.Html;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
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
import com.dcs.myretailer.app.Model.BillListModel;
import com.dcs.myretailer.app.Cashier.MainActivity;
import com.dcs.myretailer.app.Cashier.RecyclerViewAdapter;
import com.dcs.myretailer.app.Adapter.CheckOutAdapter;
import com.dcs.myretailer.app.Checkout.PaymentCashSuccesActivity;
import com.dcs.myretailer.app.Checkout.PaymentTypesCheckoutAdapter;
import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.DeviceHelper;
import com.dcs.myretailer.app.DeviceListAdapter;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.LalamoveAPI;
import com.dcs.myretailer.app.Model.BillPaymentValue;
import com.dcs.myretailer.app.Model.JeripayReceiptData;
import com.dcs.myretailer.app.Model.MercatusReceiptData;
import com.dcs.myretailer.app.Model.OrderDetails;
import com.dcs.myretailer.app.Model.ReceiptData;
import com.dcs.myretailer.app.Printer.IngenicoInitialize;
import com.dcs.myretailer.app.Printer.KitchenPrinter;
import com.dcs.myretailer.app.Printer.PrintingForIMIN;
import com.dcs.myretailer.app.Printer.PrintingForIngenico;
import com.dcs.myretailer.app.Printer.PrintingForPAX;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.ScreenSize.CashLayoutActivityScreenSize;
import com.dcs.myretailer.app.databinding.ActivityCashLayoutBinding;
import com.dcs.myretailer.app.e600.printer.PrinterTester;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.imin.printerlib.IminPrintUtils;
import com.imin.printerlib.util.BluetoothUtil;
import com.pax.dal.IDAL;
import com.pax.neptunelite.api.NeptuneLiteUser;
import com.usdk.apiservice.aidl.printer.UPrinter;

import org.json.JSONObject;
import org.w3c.dom.Document;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class CashLayoutActivity extends AppCompatActivity implements View.OnClickListener {
    public static Double change_due_amt = 0.0;
    private static IDAL dal;
    private static Context appContext;
    private static String str;
    private static String str_imn;
    private static String str_PaymentType;
    private static String strTax;
    private static String strItemDiscountAmount;
    private static String strTotalBillDisount;
    private static String strTotalItemDisount;
    private static String str_pay_amt = "";
    private static String Billtype = "0";
    private static String PaymentName = "0";
    private static Double billDiscountValueSales = 0.0;
    private static String billDiscountTypeNameSales = "";
    private static String DiscountNameSales = "";
    private static String Header,ReceiptNo,ReceiptNoDateTime,TotalItems,Footer,Subtotal,SubTotal,BillNo,RoundAdj,ServiceCharges,Total,PaymentType,PaymentAmount,Change,DateTime,strN,strST,strT,strQty,strName,strPrice,Qty,strNameOrigin,Name,Price = "0";
    static String Remarks = "";
    public static ArrayList<String> IDArr = new ArrayList<String>();
    public static ArrayList<String> sldQtyArr = new ArrayList<String>();
    public static ArrayList<String> sldIDPromoArr = new ArrayList<String>();
    public static ArrayList<String> sldOpenPriceStatusArr = new ArrayList<String>();
    public static ArrayList<String> sldNameArr = new ArrayList<String>();
    public static ArrayList<String> sldRemarksArr = new ArrayList<String>();
    public static ArrayList<Integer> detailsBillPID = new ArrayList<Integer>();
    public static ArrayList<String> sltPriceTotalArr = new ArrayList<String>();
    public static ArrayList<Double> sltItemDisArr = new ArrayList<Double>();
    public static ArrayList<String> PaymentTypeNameArr = new ArrayList<String>();
    public static ArrayList<String > PaymentTypeAmountArr = new ArrayList<String>();
    public static ArrayList<String > PaymentTypeRemarksArr = new ArrayList<String>();
    public static Double payment_amount,Changeamount = 0.0;
    public static String str_info,strreceiptdatetime = "";
    static Bitmap bitmap__ = null;
    public static String HeaderValue,FooterValue,Options,ImageStatus,ImageLogo = "";
    private static Integer paymentID = 1;
    private static String paymentName = "cash";
    public static int sale_id = 0;
    public static int bill_details_id = 0;
    public static Integer totalQty = 0;
    public static Double amount = 0.0;
    public static Double sub_total = 0.0;
    public static Double ItemDiscountAmount = 0.0;
//    Handler mHandler;
    public static CashLayoutActivity context;
    public static Double total_amt = 0.0;
    public static Double CashValue =  0.0;
    public static String CashValuePaymentName =  "";
    public static Integer CashValuePaymentID =  0;
    public static String CashValuePaymentRemarks =  "";
    public static Double BillDiscountValue =  0.0;
    public static String CashID =  "0";
    public static String CashName =  "0";
    public static String str_taxname = "0";
    public static String TotalItemDisount = "";
    public static String TotalBillDisount = "";
    public static Double TotalTaxes = 0.0;
    public static DateFormat dateFormat55 = new SimpleDateFormat("dd MMM yyyy  hh:mm aa");
    public static final List<String> ewalletrecArr = new ArrayList<String>();
    public static final List<String> ewalletVoucherValArr = new ArrayList<String>();
    public static RequestQueue queue_qrcode;
    public static final int MY_SOCKET_TIMEOUT_MS = 30000;
//    public static Boolean chk_pos_qr_code_ = false;
    public static String qrcodestring = "";
    public static Bitmap bitmap_qr_shoptima = null;

    public static ArrayList<String> sldTaxID = new ArrayList<String>();
    public static ArrayList<String> sldCTaxName = new ArrayList<String>();
    public static ArrayList<String> sldCTaxAmount = new ArrayList<String>();
    public static ArrayList<String> sldCTaxType = new ArrayList<String>();
    public static ArrayList<String> sldCTaxRate = new ArrayList<String>();
    public static ArrayList<String> sldDiscountName = new ArrayList<String>();
    public static ArrayList<String> sldDiscountType = new ArrayList<String>();
    public static ArrayList<String> sldDiscountValue = new ArrayList<String>();
    public static  String chk_print_receipt_paper = "0";
    public static  String chk_qr_code_on_receipt = "0";
    public static UPrinter printer;
    ActivityCashLayoutBinding binding = null;
   // private IPrinter test_printer;
    private PrinterTester test_printer;
//    public static String terminalTypeVal = "PAX";

    static Activity CurrentActivity;
    static Resources resourceVal;
//    static PrintSpooler printerSpooler = null;

//    private static IminPrintUtils mIminPrintUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_cash_layout);

        CurrentActivity = this;

        appContext = getApplicationContext();
        dal = getDal(appContext);

        context = CashLayoutActivity.this;

        resourceVal = getResources();

        new CashLayoutActivityScreenSize(binding);

        String terminalTypeVal = Query.GetDeviceData(Constraints.TERMINAL_TYPE);

        if (terminalTypeVal.toUpperCase().equals(Constraints.INGENICO.toUpperCase())) {
            //RegisterForIngenico(context);
            new IngenicoInitialize(this);

            printer = DeviceHelper.me().getPrinter();

        }

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        //setTitle("Cash");
        setTitle("Bill #"+MainActivity.strbillNo);

        myToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_close_white));
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        queue_qrcode = Volley.newRequestQueue(this);


        try {
            chk_print_receipt_paper = Query.GetOptions(23);
        }catch (StringIndexOutOfBoundsException e){
            chk_print_receipt_paper = "0";
        }finally {
            chk_print_receipt_paper = chk_print_receipt_paper;
        }

        chk_qr_code_on_receipt = Query.GetOptions(18);

        Intent intent = getIntent();
        Billtype = intent.getStringExtra("Billtype");
        PaymentName = intent.getStringExtra("PaymentName");
        paymentID = Integer.parseInt(intent.getStringExtra("paymentID"));
        Remarks = intent.getStringExtra("Remarks");


        CashValuePaymentID = paymentID;

        binding.btnAccept.setOnClickListener(this);

        total_amt = CheckOutActivity.amount;
        Double round = CheckOutActivity.staticRound;
        String roundAmt = "0";
        String getRoundActivateStatus = Query.GetRoundActivateInfo(String.valueOf(paymentID));
        //total_amt = 66.63;

        if (getRoundActivateStatus.equals("1")) {
           // total_amt = total_amt + round;
        }
//        if (CashValue> 0.0){
//            total_amt = total_amt - CashValue;
//        }
        CashValue = 0.0;

        String checkingDoublePaymentType = "0";
        if (CheckOutActivity.CashValuePaymentIDArr.contains(CashValuePaymentID)){
//        for (int check = 0; check < CheckOutActivity.CashValuePaymentIDArr.size(); check ++) {
//            if ((CheckOutActivity.CashValuePaymentIDArr.get(check)).equals(CashValuePaymentID)) {
                checkingDoublePaymentType = "1";
//            }else {
//                checkingDoublePaymentType = "0";
//            }
        }
//        Log.i("DFDFD___","checkingDoublePaymentType___"+checkingDoublePaymentType);
        //if (checkingDoublePaymentType.equals("1")) {

        for (int checkingMultiple = 0; checkingMultiple < CheckOutActivity.CashValueArr.size(); checkingMultiple++) {
            if (CheckOutActivity.CashValuePaymentIDArr.contains(CashValuePaymentID)) {
                CashValue += CheckOutActivity.CashValueArr.get(checkingMultiple);
                checkingMultiple = CheckOutActivity.CashValueArr.size();
            } else {
                CashValue += CheckOutActivity.CashValueArr.get(checkingMultiple);
            }
        }
//        if (CashValue> 0.0){
//            total_amt = total_amt - CashValue;
//        }
        if (CashValue> 0.0){
           total_amt = total_amt - CashValue;
        }

        binding.total.setText("$"+String.format("%.2f", total_amt));
        binding.cashCollected.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_CLASS_PHONE );

        binding.cashCollected.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                mHandler.removeCallbacks(m_Runnable);
//                mHandler.removeCallbacksAndMessages(null);
//                context.mHandler = new Handler();
//                m_Runnable.run();
                // TODO Auto-generated method stub
//                if(!flag)
                if (binding.cashCollected.getText().toString().length() > 0) {
//                    change_due_amt = Double.parseDouble(cash_collected.getText().toString()) - Double.parseDouble(total_amt);
//                    change_due.setText("$" + String.format("%.2f", Double.valueOf(String.valueOf(change_due_amt))));
//                    btn_accept.setText("ACCEPT $" + String.format("%.2f", Double.parseDouble(cash_collected.getText().toString())));

                    changeDueAmtAndAcceptAmt(binding.cashCollected.getText().toString());

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

                 if (binding.cashCollected.getText().toString().length() > 0) {

                     changeDueAmtAndAcceptAmt(binding.cashCollected.getText().toString());

               }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }
        });

        getDetailsBillProduct(CheckOutActivity.BillNo);

        binding.cashCollected.setOnClickListener(this);

        binding.btnFixed.setText("$"+String.format("%.2f", Double.valueOf(total_amt)));

        binding.btn10.setOnClickListener(this);
        binding.btn15.setOnClickListener(this);
        binding.btn20.setOnClickListener(this);
        binding.btn25.setOnClickListener(this);
        binding.btn30.setOnClickListener(this);
        binding.btn50.setOnClickListener(this);
        binding.btn100.setOnClickListener(this);
        binding.btn500.setOnClickListener(this);
        binding.btnFixed.setOnClickListener(this);

    }

    private static final int FONT_SMALL = 10;
    private static final int FONT_MEDIUM = 20;
    private static final int FONT_BIG = 28;
    private static final int FONT_BIGEST = 40;

    public static void printReceipt(Context context, int width,String str) {
//        PrinterTester.getInstance().init();
//        PrinterTester.getInstance().printStr(str, null);
//        PrinterTester.getInstance().start();
        PrinterTester test_printer = PrinterTester.getInstance();
        test_printer.init();
//        String tsttt = "";
//        for (int i = 0 ; i < 100 ; i ++){
//            tsttt += "TESTESDT";
//        }
        test_printer.printStr(str,null);
        test_printer.start();
//        PaxGLPage iPaxGlPage = PaxGLPage.getInstance(context);
//        IPage page = iPaxGlPage.createPage();
//
////        IDAL dal = null;
//        try {
////            dal = NeptuneLiteUser.getInstance().getDal(context);
//            //IPrinter printer = dal.getPrinter();
////            IPrinter printer = CashLayoutActivity.getDal(CashLayoutActivity.context).getPrinter();
//            PrinterTester printer = PrinterTester.getInstance();
//            printer.init();
//            printer.setGray(400);
//
//            page.adjustLineSpace(0);
////            page.setTypefaceObj(Typeface.createFromAsset(context.getAssets(), "Fangsong.ttf"));
//            page.setTypefaceObj(Typeface.createFromAsset(context.getAssets(), "Roboto-Regular.ttf"));
//            IPage.ILine.IUnit unit = page.createUnit();
//            unit.setAlign(IPage.EAlign.CENTER);
//            unit.setText("GLiPaxGlPage").setTextStyle(TEXT_STYLE_BOLD);
//            page.addLine().addUnit().addUnit(unit).addUnit(page.createUnit().setText("Test").setAlign(IPage.EAlign.RIGHT));
//
//            page.addLine().addUnit("--------------------------------------", FONT_MEDIUM);
//            page.addLine().addUnit("Merchant Name: " + "Pinelab", FONT_MEDIUM);
//            page.addLine().addUnit("Merchant Adrr: " + "भारत", FONT_MEDIUM);
//
//            page.addLine().addUnit("MERCHANT ID:", FONT_MEDIUM).addUnit("123456789012345", FONT_MEDIUM, IPage.EAlign.RIGHT);
//            page.addLine().addUnit("TERMINAL ID:", FONT_MEDIUM).addUnit("12345678", FONT_MEDIUM, IPage.EAlign.RIGHT);
//            page.addLine().addUnit("--------------------------------------", FONT_MEDIUM);
//            page.addLine().addUnit(" ", FONT_SMALL);
//
//            page.addLine().addUnit("CARD NO./EXP. DATE", FONT_MEDIUM);
//            page.addLine().addUnit("5454545454545454", FONT_MEDIUM).addUnit("**/**", FONT_MEDIUM, IPage.EAlign.RIGHT);
//
//            page.addLine().addUnit(" ", FONT_SMALL);
//
//            page.addLine().addUnit("TRANS TYPE: ", FONT_MEDIUM);
//            page.addLine().addUnit("Sale", FONT_BIG);
//            page.addLine().addUnit(" ", FONT_SMALL);
//
//            page.addLine().addUnit("TRANS NO:", FONT_MEDIUM).addUnit("BATCH NO:", FONT_MEDIUM, IPage.EAlign.RIGHT);
//            page.addLine().addUnit("123456", FONT_MEDIUM).addUnit("000001", FONT_MEDIUM, IPage.EAlign.RIGHT);
//
//            page.addLine().addUnit("APP CODE:", FONT_MEDIUM, IPage.EAlign.LEFT, IPage.ILine.IUnit.TEXT_STYLE_NORMAL, 1)
//                    .addUnit("REF CODE:", FONT_MEDIUM, IPage.EAlign.RIGHT, IPage.ILine.IUnit.TEXT_STYLE_NORMAL, 1);
//            page.addLine().addUnit("987654", FONT_MEDIUM, IPage.EAlign.LEFT, IPage.ILine.IUnit.TEXT_STYLE_NORMAL, 1)
//                    .addUnit("012345678912", FONT_MEDIUM, IPage.EAlign.RIGHT, IPage.ILine.IUnit.TEXT_STYLE_NORMAL);
//            page.addLine().addUnit("DATE/TIME:" + "2016/06/13 12:12:12", FONT_MEDIUM);
//            page.addLine().addUnit(" ", FONT_SMALL);
//
//            page.addLine().addUnit("AMOUNT:", FONT_BIG, IPage.EAlign.LEFT).addUnit("RMB 1.00", FONT_BIGEST, IPage.EAlign.RIGHT, TEXT_STYLE_BOLD);
//            page.addLine().addUnit(" ", FONT_SMALL);
//
//            page.addLine().addUnit("----------CARDHOLDER SIGNATURE--------", FONT_MEDIUM);
//            page.addLine().addUnit(getImageFromAssetsFile(context, "pt.bmp"));
//
//            page.addLine().addUnit("--------------------------------------", FONT_MEDIUM);
////            page.addLine()
////                    .addUnit("I AGREE TO PAY THE ABOVE TOTAL AMOUNT ACCORDING TO THE CARD ISSUER AGREEMENT", FONT_MEDIUM, EAlign.CENTER);
////            page.addLine().addUnit(" ", FONT_SMALL);
//            page.addLine()
//                    .addUnit("मैं इस समझौते को पूरा करने के लिए अभिजात वर्ग के कुल भुगतान को स्वीकार करता हूं", FONT_MEDIUM, IPage.EAlign.CENTER);
//            page.addLine().addUnit(" ", FONT_SMALL);
//
//            page.addLine().addUnit("****MARCHANT COPY****", FONT_MEDIUM, IPage.EAlign.CENTER);
//            page.addLine().addUnit("\n\n\n\n\n", FONT_MEDIUM);
//            Bitmap bitmap = iPaxGlPage.pageToBitmap(page, width);
//
//            printer.printBitmap(bitmap);
////            printer.doubleHeight(true, true);
////            printer.doubleHeight(false, false);
//            printer.printStr("print text directly", null);
//            printer.start();
//        } catch (Exception e) {
//            e.printStackTrace();
//            Log.i("err___","err__printtestt_"+e.getMessage());
//        }

    }

    public static Bitmap getImageFromAssetsFile(Context context, String fileName) {
        Bitmap image = null;
        AssetManager am = context.getResources().getAssets();
        try {
            InputStream is = am.open(fileName);
            image = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;

    }

    @Override
    protected void onResume() {
        context = CashLayoutActivity.this;

        Intent intent = getIntent();
        Billtype = intent.getStringExtra("Billtype");
        PaymentName = intent.getStringExtra("PaymentName");
        paymentID = Integer.parseInt(intent.getStringExtra("paymentID"));
        Remarks = intent.getStringExtra("Remarks");

        super.onResume();
    }

    @Override
    public void onBackPressed() {

        CheckOutActivity.CashValueArr.clear();
        CheckOutActivity.CashValuePaymentNameArr.clear();
        CheckOutActivity.CashValuePaymentIDArr.clear();
        CheckOutActivity.CashValuePaymentRemarksArr.clear();
        CheckOutActivity.CashValue = "0.0";

        CheckOutActivity.CashValuePaymentName = "";
        CheckOutActivity.CashValue = "";
        CashValuePaymentName = "";
        CashValue = 0.0;

        Intent ckActivy = new Intent(getApplicationContext(), CheckOutActivity.class);
        ckActivy.putExtra(Constraints.BillNo, CheckOutActivity.BillNo);
        ckActivy.putExtra(Constraints.Status, "Main");
        ckActivy.putExtra(Constraints.StatusSALES,"");
        startActivity(ckActivy);
        finish();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_accept:

                payment_amount = Double.valueOf(binding.cashCollected.getText().toString());

                Log.i("payment_amount___","payment_amount__"+payment_amount);

                total_amt = Double.valueOf(String.format("%.2f", total_amt));

                Log.i("total_amt__","total_amt__"+total_amt);

                if(Double.valueOf(binding.cashCollected.getText().toString()) >= total_amt) {

                    final ProgressDialog pd = new ProgressDialog(context);

                    new AsyncTaskSync(context,pd,binding.btnAccept).execute();
                }else{
                    new AlertDialog.Builder(this, R.style.AlertDialogStyle)
                            .setMessage(Constraints.NotEnoughAmount)
                            .setCancelable(false)
                            .setPositiveButton(Constraints.OK, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    CashLayoutActivityCallFun(CheckOutActivity.BillNo);

                                }
                            })
                            .setNegativeButton(Constraints.No, null)
                            .show();
                }
                break;
            case R.id.btn_10:
                    calculate(10.00);
                break;
            case R.id.btn_15:
                    calculate(15.00);
                break;
            case R.id.btn_20:
                    calculate(20.00);
                break;
            case R.id.btn_25:
                    calculate(25.00);
                break;
            case R.id.btn_30:
                    calculate(30.00);
                break;
//            case R.id.btn_35:
//                    validate();
//                    calculate(35.00);
//                break;
            case R.id.btn_50:
                    calculate(50.00);
                break;
            case R.id.btn_100:
                    calculate(100.00);
                break;
            case R.id.btn_500:
                    calculate(500.00);
                break;
            case R.id.btn_fixed:
                    calculate(Double.valueOf(String.format("%.2f", Double.valueOf(total_amt))));
                break;
        }
    }

    private static class AsyncTaskSync extends AsyncTask<Object, ImageView, Void> {
        Context mcontext;
        ProgressDialog pd;
        Button btn_accept;

        public AsyncTaskSync(Context context, ProgressDialog pdg, Button btnAccept) {
            mcontext = context;
            pd = pdg;
            btn_accept = btnAccept;
        }

        protected Void doInBackground(Object... params) {


            if (Changeamount < 0.0){
                Changeamount =  0.0;
            }

            Double bill_amt = amount;


            Query.SaveBill("","","","","","OFF");


            Double gross_sales = amount;
            Double total_sales = amount;

            Double billdiscount = CheckOutActivity.discount_amount;

            //Double total_discount = ItemDiscountAmount + billdiscount;
            Double total_discount =  billdiscount;

            Double gross_total =  gross_sales - total_discount;

            String cashier_name = "";

            Double service_charges = CheckOutActivity.service_charges;
            Double total_nett_sales = gross_total + service_charges;

            Double amt_exclusive = CheckOutActivity.amt_exclusive;


            if (amt_exclusive > 0.0){
                total_nett_sales = total_nett_sales + amt_exclusive;
            }

            //TotalPromoValue Calculate
            Double PromoTotalValue = Query.GetPromoTotal(BillNo);
            if (PromoTotalValue != 0.0){
                total_nett_sales = total_nett_sales + PromoTotalValue;
                sub_total = sub_total + PromoTotalValue;
            }

            Double round = CheckOutActivity.staticRound;
            String roundAmt = "0";
            String getRoundActivateStatus = Query.GetRoundActivateInfo(String.valueOf(paymentID));
            if (getRoundActivateStatus.equals("1")) {
                total_nett_sales = total_nett_sales + round;
                roundAmt = String.format("%.2f", round);
            }else {
                roundAmt = "0";
            }


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

        String billNo = CheckOutActivity.BillNo;
         ItemDiscountAmount = Query.GetItemDiscountAmount(billNo);

        String s = "Select ID FROM Sales WHERE BillNo = '" +billNo + "'";
        Cursor cc = DBFunc.Query(s,false);
        if (cc != null) {
            if (cc.getCount() == 0) {
                //SALES
                String uniqueId = UUID.randomUUID().toString();
                String BillID = Query.findBillIDByBillNo(billNo);

                String sql = "INSERT INTO Sales (UUID,BillNo,BillID,TotalQty,SubTotal,Totalamount,Changeamount,PaymentTypeID,PaymentTypeName,PaymentAmount," +
                        "GrossSales,TotalItemDisount,TotalBillDisount,GrossTotal,ServiceCharges,TotalNettSales,TotalTaxes,CashierName,SalesDateTime,BillHour,STATUS," +
                        "RoundAdj,DiscountID,DiscountName,DiscountType,DiscountTypeName,DiscountValue,EwalletStatus,ReceiptRemarks,DateTime) VALUES (";
                sql += "'" + uniqueId + "', ";
                sql += "'" + billNo + "', ";
                sql += "'" + BillID + "', ";
                sql += "'" + DBFunc.PurifyString(String.valueOf(totalQty)) + "', ";
                sql += "'" + String.format("%.2f", Double.valueOf(sub_total)) + "', ";
                sql += "'" + String.format("%.2f", Double.valueOf(total_sales)) + "', ";
                sql += "'" + String.format("%.2f", Double.valueOf(Changeamount)) + "', ";
                sql += "'" + DBFunc.PurifyString(String.valueOf(paymentID)) + "', ";
                sql += "'" + DBFunc.PurifyString(paymentName) + "', ";
                sql += "'" + String.format("%.2f", Double.valueOf(payment_amount)) + "', ";
                sql += "'" + String.format("%.2f", Double.valueOf(gross_sales)) + "', ";
                sql += "'" + String.format("%.2f", Double.valueOf(ItemDiscountAmount)) + "', ";
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

                sql += 0 + ", ";
                sql += "'" + PaymentTypesCheckoutAdapter.payment_remarks + "', ";
                sql += System.currentTimeMillis() + ")";


                DBFunc.ExecQuery(sql, false);

                PaymentTypesCheckoutAdapter.payment_remarks = "";


//
                Integer sale_id = Query.findLatestID("ID","Sales",false);
//
                CashLayoutActivity.SaveSalesItem(BillNo,sub_total,totalQty,sale_id,String.valueOf(Changeamount),
                        paymentName,String.valueOf(payment_amount),context,System.currentTimeMillis());

//
//                Query.SaveBillPaymentIntoDBFun(context,bill_amt,amount,total_discount,amt_exclusive,PromoTotalValue,BillNo,
//                        String.valueOf(paymentID),paymentName,String.valueOf(Changeamount),total_nett_sales,"cash","",sale_id);
//


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

                        sql = "INSERT INTO BillPayment (BillNo,STATUS,PaymentRemarks,EwalletStatus,EwalletIssueBanker,EwalletPaymentType,PaymentID,Name,Amount,PaymentDateTime,QRCode," +
                                "ChangeAmount,SaleSync) VALUES (";
                        //sql += BillNo + ", ";
                        sql += "'"+CheckOutActivity.BillNo+"'" + ", ";
                        sql += "'"+ Constraints.SALES+"'" + ", ";
                        sql += "'"+ CheckOutActivity.CashValuePaymentRemarksArr.get(i) +"'" + ", ";
                        sql +=  0 + ", ";
                        sql +=  "''" + ", ";
                        sql +=  "''" + ", ";
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

                //String getRoundActivateStatus = Query.GetRoundActivateInfo(paymentID);

//                if (getRoundActivateStatus.equals("1")) {
//                    //bill_amt = bill_amt + CheckOutActivity.staticRound;
//                    actual_amt = actual_amt + CheckOutActivity.staticRound;
//                }
                String str_info = "";
                String printrqr = Query.GetOptions(18);
                if (printrqr.equals("1")) {
                    str_info = Query.getStrInfoForQRCodeOnReceipt(BillNo, paymentName, actual_amt, actual_amt);
                } else {
                    str_info = "";
                }

                //Query.SaveBillPayment(context,BillNo,paymentID,paymentName,actual_amt,str_info,Changeamount,strCardOrCash,card_label,sale_id);
                sql = "INSERT INTO BillPayment (BillNo,STATUS,PaymentRemarks,EwalletStatus,EwalletIssueBanker,EwalletPaymentType,PaymentID,Name,Amount,PaymentDateTime,QRCode,ChangeAmount,SaleSync) VALUES (";
                //sql += BillNo + ", ";
                sql += "'"+billNo+"'" + ", ";
                sql += "'"+ Constraints.SALES+"'" + ", ";
                sql += "'"+ Remarks+"'" + ", ";
                 sql +=  0 + ", ";
                sql +=  "''" + ", ";
                sql +=  "''" + ", ";

                sql += paymentID + ", ";
                String searchPaymentName = "SELECT Name From Payment WHERE ID = "+paymentID;
                Cursor csearchPN = DBFunc.Query(searchPaymentName,true);
                if (csearchPN != null){
                    if (csearchPN.getCount() != 0){
                        if (csearchPN.moveToNext()){
                            paymentName = csearchPN.getString(0);
                        }
                    }
                    csearchPN.close();
                }
                sql += "'" + DBFunc.PurifyString(DBFunc.PurifyString(paymentName)) + "', ";
                sql += Query.DoubleAmountFormat(Double.valueOf(actual_amt)) + ", ";
                sql += System.currentTimeMillis() + ", ";
                sql += "'" + str_info + "', ";
                sql += "'" + Query.DoubleAmountFormat(Changeamount) + "', ";
//        sql += "'" + change_due_amt + "', ";
                sql += "'0')";
                Log.i("Sf_SaveBillPayment", sql);
                DBFunc.ExecQuery(sql, false);
            }
        }

            Query.ChkOnlineOrderAndUpdateCloseDtBill(billNo,CheckOutActivity.tbl_name);

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            pd.dismiss();
            btn_accept.setEnabled(true);

            final Integer sale_id = Query.findLatestID("ID","Sales",false);

            String chkKitchenPrinter = Query.GetOptions(28);
            if (chkKitchenPrinter.equals("1")) {

                new KitchenPrinter(context, resourceVal, CheckOutActivity.BillNo);
            }

            if (chk_print_receipt_paper.equals("1")) {


                final SweetAlertDialog pDialog =  new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText(Constraints.Print)
                        .setConfirmText(Constraints.YES)
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();

                                if (sale_id > 0) {

                                    bitmap_qr_shoptima = null;

                                    if (chk_qr_code_on_receipt.equals("1")) {
                                        //bitmap_qr_shoptima = Query.GetPrintQRCodeOnReceipt(context);
                                        //Query.GetPrintQRCodeOnReceipt(context);
                                        CashLayoutActivity.GetPrintQRCodeOnReceipt(context,CheckOutActivity.BillNo);

                                    }else {

                                        bitmap__ = null;
                                        bitmap__ = Query.GetLogPrint();

                                        Query.CheckEmulatorOrNot(context, sale_id, CheckOutActivity.BillNo, Constraints.SALES, Constraints.SALES,bitmap__,bitmap_qr_shoptima);

                                        CheckOutActivity.str_percentage_value = "0";
                                        CheckOutActivity.discount_amount = 0.0;

                                        UpdateFunationfun(CheckOutActivity.BillNo);
                                    }


                                }else {
                                    Toast.makeText(mcontext, Constraints.SALES + " Successfully.", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(mcontext, PaymentCashSuccesActivity.class);
                                    mcontext.startActivity(intent);


                                    UpdateFunationfun(CheckOutActivity.BillNo);
                                }

                            }
                        })
                        .setCancelButton(Constraints.No, new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();

                                Toast.makeText(context, Constraints.SALES + " Successfully.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(context, PaymentCashSuccesActivity.class);
                                context.startActivity(intent);

                                UpdateFunationfun(CheckOutActivity.BillNo);


                            }
                        });
                pDialog.show();
                pDialog.setCancelable(false);

            }else {
                Toast.makeText(context, Constraints.SALES + " Successfully.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, PaymentCashSuccesActivity.class);
                context.startActivity(intent);

                UpdateFunationfun(CheckOutActivity.BillNo);
            }
        }

        @Override
        protected void onPreExecute() {
            pd.show();
            btn_accept.setEnabled(false);
        }
    }

    public static void UpdateFunationfun(String billNo){


//        String BillID = findBillIDByBillNo(billNo);

            String TotalItems = "0";
            String TableNo = "0";
            String QueueNo = "0";
            String TotalAmount = "0";
            String OnlineOrderBill = "0";
            String sqll = "SELECT Sales.BillNo,Sales.STATUS,Sales.TotalQty," +
                    "Sales.TotalNettSales,Sales.SalesDateTime,Sales.ID,DetailsBillProduct.OnlineOrderBill," +
                    "vchQueueNo,intTableNo " +
                    " FROM Sales " +
                    " INNER JOIN DetailsBillProduct on DetailsBillProduct.BillNo = Sales.BillNo " +
                    " WHERE Sales.BillNo = '"+billNo+"' " +
                    " group by Sales.BillNo order by Sales.BillNo DESC ";
            Log.i("__ddsql",sqll);
            Cursor Cursor_DBP = DBFunc.Query(sqll,false);
            if (Cursor_DBP != null){
                if (Cursor_DBP.moveToNext()){
                    TotalItems = Cursor_DBP.getString(2);
                    TotalAmount = Cursor_DBP.getString(3);
                    OnlineOrderBill = Cursor_DBP.getString(6);
                    QueueNo = Cursor_DBP.getString(7);
                    TableNo = Cursor_DBP.getString(8);
                    //STATUS = Cursor_DBP.getString(1);
                    //STATUS = "PENDING";
                }
                Cursor_DBP.close();
            }
            DateFormat dateFormat55 = new SimpleDateFormat("dd MMM yyyy  hh:mm aa");
            String Date = dateFormat55.format(new Date()).toString();

            String BillID = Query.findBillIDByBillNo(billNo);
            BillListModel blist = new BillListModel(BillID,billNo,"S",TotalItems,Date,TableNo,QueueNo,OnlineOrderBill,TotalAmount,0);
//        Log.i("Dfd_____STATUS","STATUS___"+STATUS);
            Query.UpdateBillList(blist,billNo);
            //Query.UpdateStatusBillList(billNo,ENUM.SALES.toUpperCase());
            //Query.UpdateIsClosedBillList(billNo);


            String bidsql = "SELECT SalesDateTime,BillNo " +
                    "FROM Sales WHERE BillNo = '"+BillNo+"'";

            Cursor cdd = DBFunc.Query(bidsql, false);
            if (cdd != null) {
                if (cdd.moveToNext()) {
                    String updatesql = "UPDATE BillList SET Date = '" + cdd.getString(0) + "', " +
                            "IsClosed  = 'Closed'," +
                            "STATUS = '"+ Constraints.SALES.toUpperCase()+"' " +
                            "WHERE BillNo = '" + BillNo + "' ";

                    DBFunc.ExecQuery(updatesql, false);
                }
            }


            CheckOutActivity.billDisID = "0";
            CheckOutActivity.billDisName = "";
            CheckOutActivity.billDisType = "";
            CheckOutActivity.billDisTypeName = "";
            CheckOutActivity.billDisValue = "";

            CheckOutActivity.billDisIDAmt = "";
            CheckOutActivity.billDisNameAmt = "";
            CheckOutActivity.billDisOptionsAmt = "";
            CheckOutActivity.billDisTypeNameAmt = "";
            CheckOutActivity.billDisTypeAmt = "";
            CheckOutActivity.billDisValueAmt = "";

            CheckOutActivity.disID = "";
            CheckOutActivity.disName = "";;
            CheckOutActivity.disOptions = "";
            CheckOutActivity.disTypeName = "";
            CheckOutActivity.disType = "";
            CheckOutActivity.disValue = "";

            CheckOutActivity.discount_amount = 0.0;

            BillDiscountValue =  0.0;

            CheckOutActivity.CashValuePaymentNameArr.clear();
            CheckOutActivity.CashValuePaymentIDArr.clear();
            CheckOutActivity.CashValuePaymentRemarksArr.clear();
            CheckOutActivity.CashValueArr.clear();

            CashLayoutActivity.change_due_amt = 0.0;

    }


    public static void getDetailsBillProduct(String billNo) {
        String sql = " SELECT BillNo,(ProductQty),(ProductPrice),ProductName,(ItemDiscountAmount)," +
                "BillDateTime,ID,TaxID,TaxName,(TaxAmount),DiscountName,DiscountTypeName,DiscountValue,ProductQty FROM DetailsBillProduct " +
                " Where BillNo = '"+ billNo +"' "+
                "   group by ProductID,OpenPriceStatus,Remarks";
//        String sql = " SELECT BillNo,SUM(ProductQty),SUM(ProductPrice),ProductName,SUM(ItemDiscountAmount)," +
//                "BillDateTime,ID,TaxID,TaxName,SUM(TaxAmount),DiscountName,DiscountTypeName,DiscountValue,ProductQty FROM DetailsBillProduct " +
//                " Where BillNo = '"+ billNo +"' "+
//                "   group by ProductID,OpenPriceStatus,Remarks";
        Log.i("_sql__",sql);
        Cursor c = DBFunc.Query(sql, false);
        if (c != null) {
            sub_total = 0.0;
            amount = 0.0;
            totalQty = 0;
            ItemDiscountAmount = 0.0;

            sldTaxID.clear();
            sldCTaxName.clear();
            sldCTaxAmount.clear();
            sldCTaxType.clear();
            sldCTaxRate.clear();
            sldDiscountName.clear();
            sldDiscountType.clear();
            sldDiscountValue.clear();
            while (c.moveToNext()) {
                if (!c.isNull(0)) {
                    if (c.getInt(13) != -1){

                        //sub_total += c.getDouble(2) - c.getDouble(4);
                        sub_total += (c.getDouble(2));
                        //amount += c.getDouble(2);
                        //amount += c.getInt(1) * (c.getDouble(2) - c.getDouble(4));
                        //amount +=  (c.getDouble(2) - c.getDouble(4));
                        amount +=  (c.getDouble(2) - (c.getInt(1) * c.getDouble(4)));
                        totalQty += c.getInt(1);
                        ItemDiscountAmount += c.getDouble(4);
                        Integer taxID = c.getInt(7);
                        sldTaxID.add(String.valueOf(taxID));
                        sldCTaxName.add(c.getString(8));
                        sldCTaxAmount.add(c.getString(9));
                        sldDiscountName.add(c.getString(10));
                        sldDiscountType.add(c.getString(11));
                        sldDiscountValue.add(c.getString(12));

                        String str_tax = "Select Type,Rate from Tax Where ID = " +taxID;

                        Cursor CursortaxObj = DBFunc.Query(str_tax,true);
                        if (CursortaxObj != null){
                            if (CursortaxObj.moveToNext()) {
                                sldCTaxType.add(CursortaxObj.getString(0));
                                sldCTaxRate.add(CursortaxObj.getString(1));
                            }else {
                                sldCTaxType.add("0");
                                sldCTaxRate.add("0");
                            }
                            CursortaxObj.close();
                        }else {
                            sldCTaxType.add("0");
                            sldCTaxRate.add("0");
                        }
                    }
                }
            }
            c.close();
        }
    }


    private void calculate(Double amount) {

        binding.cashCollected.setText(String.format("%.2f", Double.parseDouble(String.valueOf(amount))));
        total_amt = Double.valueOf(String.format("%.2f", Double.parseDouble(String.valueOf(total_amt))));

        if ((amount) >= total_amt){

            changeDueAmtAndAcceptAmt(String.valueOf(amount));

        }else{
            new AlertDialog.Builder(this, R.style.AlertDialogStyle)
                    .setMessage(Constraints.NotEnoughAmount)
                    .setCancelable(false)
                    .setPositiveButton(Constraints.OK, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            CashLayoutActivityCallFun(CheckOutActivity.BillNo);

                        }
                    })
                    .setNegativeButton(Constraints.No, null)
                    .show();
        }
    }

    private void CashLayoutActivityCallFun(String billNo) {

        Intent intent = new Intent(CashLayoutActivity.this, CheckOutActivity.class);
        intent.putExtra("BillNo",billNo);
        intent.putExtra("Status","CashLayoutActivity");
        intent.putExtra("StatusSALES","");
        startActivity(intent);
        finish();

        CashValue = Double.valueOf(binding.cashCollected.getText().toString());
        MultiplePaymentFun(CashValue);
    }

    private void MultiplePaymentFun(Double CashValue) {
        CashValuePaymentName = PaymentName;
        CashValuePaymentID = paymentID;
        CashValuePaymentRemarks = Remarks;

        for (int check = 0; check < CheckOutActivity.CashValuePaymentIDArr.size(); check ++){
            if ((CheckOutActivity.CashValuePaymentIDArr.get(check)).equals(CashValuePaymentID)){

                CheckOutActivity.CashValuePaymentNameArr.remove(check);
                CheckOutActivity.CashValuePaymentIDArr.remove(check);
                CheckOutActivity.CashValuePaymentRemarksArr.remove(check);
                CheckOutActivity.CashValueArr.remove(check);

            }
        }

        if (CashValue > 0.0) {
            CheckOutActivity.CashValuePaymentNameArr.add(CashValuePaymentName);
            CheckOutActivity.CashValuePaymentIDArr.add(CashValuePaymentID);
            CheckOutActivity.CashValuePaymentRemarksArr.add(CashValuePaymentRemarks);
            CheckOutActivity.CashValueArr.add(CashValue);

        }
        if (CheckOutActivity.discount_amount != 0.0) {
            BillDiscountValue =  CheckOutActivity.discount_amount;
        }
        CashID = PaymentTypesCheckoutAdapter.paymentID;
        CashName = PaymentTypesCheckoutAdapter.paymentName;
    }

    private void changeDueAmtAndAcceptAmt(String amount) {
        if (amount !=null && amount.length() > 0) {
            change_due_amt = Double.valueOf(amount) - total_amt;
        }else {
            change_due_amt = 0.0;
        }
        if (change_due_amt > 0.0){
            binding.changeDue.setText("$" + String.format("%.2f", Double.valueOf(String.valueOf(change_due_amt))));
        }else{
            binding.changeDue.setText("$0.00");
        }
        //cash_collected = (EditText) findViewById(R.id.cash_collected);
        //cash_collected.setText(String.format("%.2f", Double.parseDouble(amount)));
        //cash_collected.setText(String.format("%.2f", Double.parseDouble(String.valueOf(amount))));
        if (String.valueOf(amount) != null) {
            binding.btnAccept.setText("ACCEPT $" + String.format("%.2f", Double.valueOf(amount)));
        }
        payment_amount = Double.valueOf(amount);
        Changeamount = Double.valueOf(String.valueOf(change_due_amt));

//        mHandler.removeCallbacks(m_Runnable);
//        mHandler.removeCallbacksAndMessages(null);
    }

    public static void SaveBillPayment(String BillNo, Double sub_total, Integer totalQty, Double amount,
                                       Double ItemDiscountAmount, String paymentID, String paymentName, String Changeamount,
                                       String payment_amount, Context context, String strCardOrCash,
                                       String card_label) {
//        if (change_due_amt < 0.0){
//            change_due_amt = 0.0;
//        }
        if (Double.valueOf(Changeamount) < 0.0){
            Changeamount = "0.0";
        }

//        String sql = "0";
//        if (Billtype.equals("B")) { // B for Bill / OB for Online Bill
//
//            BillTypeFunction(BillNo,CheckOutActivity.tbl_name);
//
//        }
        Double bill_amt = amount;
        if (!strCardOrCash.equals("onlinecash")) {

            Query.SaveBill("","","","","","OFF");

            bill_amt = amount + CheckOutActivity.service_charges;
        }
        Double amt_exclusive = CheckOutActivity.amt_exclusive;

        if (amt_exclusive > 0.0){
            bill_amt = bill_amt + amt_exclusive;
        }

        //Double total_discount = ItemDiscountAmount + CheckOutActivity.discount_amount;
        Double total_discount = CheckOutActivity.discount_amount;


//        Log.i("RERETETE___","TRTR4bill_amtbefore__"+bill_amt);
        bill_amt -= total_discount;


        //TotalPromoValue Calculate
        Double PromoTotalValue = Query.GetPromoTotal(BillNo);
        if (PromoTotalValue != 0.0){
            bill_amt = bill_amt + PromoTotalValue;
        }


        SaveSales(BillNo,sub_total,totalQty,amount,ItemDiscountAmount,Changeamount,
                paymentID,paymentName,payment_amount,context,bill_amt,strCardOrCash,card_label);

        Query.ChkOnlineOrderAndUpdateCloseDtBill(BillNo,CheckOutActivity.tbl_name);

//        Log.i("Billtype___","Billtype__"+Billtype);
//        if (Billtype.equals("B")) { // B for Bill / OB for Online Bill
//
//            BillTypeFunction(BillNo,CheckOutActivity.tbl_name);
//
//        }
    }

    public static void BillTypeFunction(String BillNo,String tblName) {
        String str_tbl = " ";
        if (!tblName.equals("0")){
            str_tbl = " , BalNo = '" + tblName +"'";
        }
        Integer billIDDD = Query.getBillID(BillNo); ;
        // sql = "UPDATE Bill SET CloseDateTime = " + System.currentTimeMillis() + str_tbl + " WHERE BillNo = " + BillNo;
        String sql = "";
        if (billIDDD > 0) {
            sql = "UPDATE Bill SET CloseDateTime = " + System.currentTimeMillis() + str_tbl + " WHERE BillNo = " + billIDDD;
        }else {
            sql = "UPDATE Bill SET CloseDateTime = " + System.currentTimeMillis() + str_tbl + " WHERE BillNo = " + BillNo;
        }

        DBFunc.ExecQuery(sql, false);
    }

    public static void SaveSales(String BillNo, Double sub_total, Integer totalQty, Double amount, Double ItemDiscountAmount,
                                 String Changeamount, String paymentID, String paymentName, String payment_amount,
                                 Context context,Double bill_amt,String strCardOrCash,String card_label) {
        Double gross_sales = amount;
        Double total_sales = amount;

        sub_total = Query.GetSubTotalValueFromDetailsBillProduct(BillNo);

        Double billdiscount = CheckOutActivity.discount_amount;

        //Double total_discount = ItemDiscountAmount + billdiscount;
        Double total_discount =  billdiscount;

        Double gross_total =  gross_sales - total_discount;

        String cashier_name = "";

        Double service_charges = CheckOutActivity.service_charges;
        Double total_nett_sales = gross_total + service_charges;

        Double amt_exclusive = CheckOutActivity.amt_exclusive;


        if (amt_exclusive > 0.0){
            total_nett_sales = total_nett_sales + amt_exclusive;
        }

        //TotalPromoValue Calculate
        Double PromoTotalValue = Query.GetPromoTotal(BillNo);
        if (PromoTotalValue != 0.0){
            total_nett_sales = total_nett_sales + PromoTotalValue;
            sub_total = sub_total + PromoTotalValue;
        }

        Double round = CheckOutActivity.staticRound;
        String roundAmt = "0";
        String getRoundActivateStatus = Query.GetRoundActivateInfo(paymentID);
        if (getRoundActivateStatus.equals("1")) {
            total_nett_sales = total_nett_sales + round;
            roundAmt = String.format("%.2f", round);
        }else {
            roundAmt = "0";
        }


        Query.SaveSales(BillNo,totalQty,sub_total,total_sales,Changeamount,paymentID,paymentName,payment_amount,
                gross_sales,ItemDiscountAmount,billdiscount,service_charges,total_nett_sales,amt_exclusive,cashier_name,
                roundAmt, bill_amt,amount,total_discount,
                PromoTotalValue,BillNo,Changeamount,context,strCardOrCash,card_label);
    }

    public static void SaveSalesItem(String BillNo, Double sub_total, Integer totalQty, Integer sales_ID, String
            Changeamount, String paymentName, String payment_amount, Context context, long timestamp) {

        String sql_category = "Select Count(ProductID),ProductID,ProductName,CategoryID,CategoryName,SUM(ProductPrice) " +
                "From DetailsBillProduct WHERE BillNo = '"+BillNo+"' " +
                " AND Cancel = 'SALES'" +
                "Group By ProductID";

        Cursor c_cateogry = DBFunc.Query(sql_category,false);
        if (c_cateogry != null) {
            while (c_cateogry.moveToNext()){
                String pluid = c_cateogry.getString(1);
                String pluname = c_cateogry.getString(2);
                String categoryid = c_cateogry.getString(3);
                String categoryname = c_cateogry.getString(4);
                Integer cc_qty = c_cateogry.getInt(0);
                String cc_price = c_cateogry.getString(5);

                String sql_chk_plu_existing = "Select PluId from SalesItem WHERE BillNo = '" + BillNo + "' AND PluId = " +
                        pluid;
                Cursor curpluexisting = DBFunc.Query(sql_chk_plu_existing, false);
                if (curpluexisting != null) {
                    if (curpluexisting.getCount() == 0) {
                        //pluid,pluname,categoryid,categoryname
                        String sql = "INSERT INTO SalesItem (SalesID,BillNo,PluId,PluName,CategoryId,CategoryName," +
                                "Qty,Price,DateTime) VALUES (";
                        sql += sales_ID + ", ";
                        sql += "'" + DBFunc.PurifyString(BillNo) + "', ";
                        sql += "'" + DBFunc.PurifyString(pluid) + "', ";
                        sql += "'" + DBFunc.PurifyString(pluname) + "', ";
                        sql += "'" + DBFunc.PurifyString(categoryid) + "', ";
                        sql += "'" + DBFunc.PurifyString(categoryname) + "', ";
                        sql += "'" + DBFunc.PurifyString(String.valueOf(cc_qty)) + "', ";
                        sql += "'" + DBFunc.PurifyString(cc_price) + "', ";
                        sql += timestamp + ")";

                        DBFunc.ExecQuery(sql, false);

                        curpluexisting.close();
                    }
                }
            }
            c_cateogry.close();
        }

//        Cursor config_values_pro_item = null;
//        String url = "";
//        config_values_pro_item = DBFunc.Query("SELECT retails_id,company_code,company_url,option FROM POSSys", true);
//        if (config_values_pro_item != null) {
//            while (config_values_pro_item.moveToNext()) {
//                url = config_values_pro_item.getString(2);
//            }
//        }
//        if (url != null) {
//            SyncActivity.ResyncOrNormal(context,CheckOutActivity.BillNo,"","Normal","");
//        }

//        String chk_qr_code_on_receipt = Query.GetOptions(18);
//
//        bitmap_qr_shoptima = null;
//        if (chk_qr_code_on_receipt.equals("1")) {
//            //bitmap_qr_shoptima = Query.GetPrintQRCodeOnReceipt(context);
//            //Query.GetPrintQRCodeOnReceipt(context);
//            GetPrintQRCodeOnReceipt(context,BillNo);
//
//        }else {
//
//            bitmap__ = null;
//            bitmap__ = Query.GetLogPrint();
//
//            if (!Query.isEmulator()) {
//                Log.i("DFDF__","sales_ID___"+sales_ID+"__"+sale_id);
//                //PrintingReceipt(sale_id, context, ENUM.SALES, bitmap__, null, CheckOutActivity.BillNo);
//                PrintingReceipt(sales_ID, context, ENUM.SALES, bitmap__, null, CheckOutActivity.BillNo);
//            }else {
//                Log.i("paymentsuccess__","one__"+"one");
//                Intent i = new Intent(context, PaymentCashSuccesActivity.class);
//                context.startActivity(i);
//                ((Activity)context).finish();
//            }
//            CheckOutActivity.str_percentage_value = "0";
//            CheckOutActivity.discount_amount = 0.0;
//        }

        //SaveBillReceipt(BillNo,sub_total,totalQty,sales_ID,Changeamount,paymeentName,payment_amount,context);
    }


//    public static void SaveBillReceipt(String BillNo, Double sub_total, Integer totalQty, Integer sales_ID, String change, String paymentName, String paymentAmount, Context context) {
//        getHeaderFooterValues();
//        strheader = HeaderValue;
//        strfooter = FooterValue;
//
//        Date fprintTime = new Date();
//        strreceiptdatetime = String.format("%02d-%02d-%4d ", fprintTime.getDate(), (fprintTime.getMonth() + 1), (fprintTime.getYear() + 1900));
//        strreceiptdatetime += String.format("%02d:%02d.%02d", fprintTime.getHours(), fprintTime.getMinutes(), fprintTime.getSeconds());
//
////        AddNewProductActivity.BitMapToString(bitmap)
//
//        String sql = "INSERT INTO BillReceipt (Header,SalesID,ReceiptNo,ReceiptNoDateTime,Description,Qty,Price,TotalItems," +
//                "Footer,Subtotal,Total,PaymentType,PaymentAmount,Change,ClosedSales,Image,DateTime) VALUES (";
//        sql +=  "'" + strheader + "', ";
//        sql += sales_ID + ", ";
//        sql +=  "'" + BillNo + "', ";
//        sql += "'" + strreceiptdatetime  + "', ";
//        sql += "'" + DBFunc.PurifyString(String.valueOf(CheckOutActivity.sldNameArr)) + "', ";
//        sql += "'" + DBFunc.PurifyString(String.valueOf(CheckOutActivity.sldQtyArr)) + "', ";
//        sql += "'" + DBFunc.PurifyString(String.valueOf(CheckOutActivity.sltPriceTotalArr)) + "', ";
//        sql += "'" + DBFunc.PurifyString(String.valueOf(totalQty)) + "', ";
//        sql += "'" + strfooter + "', ";
//        sql += "'" + DBFunc.PurifyString(String.valueOf(sub_total)) + "', ";
//        sql += "'" + DBFunc.PurifyString(String.valueOf(amount) )+ "', ";
//        sql += "'" + DBFunc.PurifyString(String.valueOf(paymentName) )+ "', ";
//        sql += "'" + DBFunc.PurifyString(String.valueOf(paymentAmount) )+ "', ";
//        sql += "'" + DBFunc.PurifyString(String.valueOf(change) )+ "', ";
//        sql += "'" + DBFunc.PurifyString(String.valueOf(ClosedSales) )+ "', ";
//
//        String str_img = "0";
//        if (ImageStatus != null || ImageStatus.equals("0")){
//            str_img = "0";
//        }else{
//            str_img = ImageStatus;
//        }
//        sql += "'"+str_img+"', ";
//        sql += System.currentTimeMillis() + ")";
//
//        DBFunc.ExecQuery(sql, false);
//
//
//        bill_receipt_id = Query.findLatestID("ID","BillReceipt",false);
//        //saveDetailsBillProduct(bill_details_id);
//        for (int i =0 ; i < CheckOutActivity.sldQtyArr.size(); i++) {
//            sql = "INSERT INTO Details_ReceiptProduct (BillNo,ProductQty,ProductName,ProductID,CategoryID,CategoryName,ProductPrice,BillReceiptID,SalesID,DateTime) VALUES (";
//            sql += "'" + BillNo + "', ";
//            sql += "'" + DBFunc.PurifyString(String.valueOf(CheckOutActivity.sldQtyArr.get(i))) + "', ";
//            sql += "'" + DBFunc.PurifyString(String.valueOf(CheckOutActivity.sldNameArr.get(i))) + "', ";
//            sql += "'" + DBFunc.PurifyString(String.valueOf(CheckOutActivity.sldIDArr.get(i))) + "', ";
//            sql += "'" + DBFunc.PurifyString(String.valueOf(CheckOutActivity.sldCategoryIDArr.get(i))) + "', ";
//            sql += "'" + DBFunc.PurifyString(String.valueOf(CheckOutActivity.sldCategoryNameArr.get(i))) + "', ";
//            sql += "'" + DBFunc.PurifyString(String.valueOf(CheckOutActivity.sltPriceTotalArr.get(i))) + "', ";
//            sql += "'" + DBFunc.PurifyString(String.valueOf(bill_receipt_id)) + "', ";
//            sql += sales_ID + ", ";
//            sql += System.currentTimeMillis() + ")";
//
//            DBFunc.ExecQuery(sql, false);
//
//        }
//        Cursor config_values_pro_item = null;
//        String retail_ID = "";
//        String company_code = "";
//        String url = "";
//        String option = "";
//        config_values_pro_item = DBFunc.Query("SELECT retails_id,company_code,company_url,option FROM POSSys", true);
//        if (config_values_pro_item != null) {
//            while (config_values_pro_item.moveToNext()) {
//                retail_ID = config_values_pro_item.getString(0);
//                company_code = config_values_pro_item.getString(1);
//                url = config_values_pro_item.getString(2);
//                option = config_values_pro_item.getString(3);
//            }
//        }
//        if (url != null) {
//            SyncActivity.ResyncOrNormal(context,CheckOutActivity.BillNo,"","Normal","");
//        }
//
//        String chk_qr_code_on_receipt = Query.GetOptions(18);
//
//        bitmap_qr_shoptima = null;
//        if (chk_qr_code_on_receipt.equals("1")) {
//            //bitmap_qr_shoptima = Query.GetPrintQRCodeOnReceipt(context);
//            //Query.GetPrintQRCodeOnReceipt(context);
//            GetPrintQRCodeOnReceipt(context,BillNo);
//
//        }else {
//
//            bitmap__ = null;
//            bitmap__ = Query.GetLogPrint();
//
//            if (!Query.isEmulator()) {
//                PrintingReceipt(sale_id, context, ENUM.SALES, bitmap__, null, CheckOutActivity.BillNo);
//            }else {
//
//                Log.i("paymentsuccess__","two__"+"two");
//                Intent i = new Intent(context, PaymentCashSuccesActivity.class);
//                context.startActivity(i);
//                ((Activity)context).finish();
//            }
//            CheckOutActivity.str_percentage_value = "0";
//            CheckOutActivity.discount_amount = 0.0;
//        }
//    }

    public static void GetPrintQRCodeOnReceipt(Context context,String BillNo) {
        //Bitmap[] bitmapQRCode = new Bitmap[1];
        //Bitmap bitmapQRCode = null;
        String  status_str_info = Query.GetStatus(BillNo);

        if (status_str_info.toUpperCase().equals(Constraints.SALES.toUpperCase())) {
            String str_info = Query.GetQRCodeFromBillPayment(BillNo);

            //bitmapQRCode = GenerateQRCodeString(context, str_info);
            GenerateQRCodeString(context, str_info,BillNo);
        }
        //Log.i("TAGGG___","status_str_info__d_"+bitmapQRCode);
        //return bitmapQRCode;
    }
    //public static Bitmap bitmapQRCode = null;
    //public static ProgressDialog progressDialog;
    private static void GenerateQRCodeString(final Context context, final String strInfo, final String BillNo) {

        final SweetAlertDialog pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        //final SweetAlertDialog pDialog = new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading ...");
        //pDialog.setCancelable(true);
//        pDialog.show();
        //final Bitmap[] bitmapQRCode = new Bitmap[1];

        queue_qrcode = Volley.newRequestQueue(context);

//        RequestQueue queue = Volley.newRequestQueue(this);
        //String url = "http://" + qr_code_shoptima_url;
        String url = MainActivity.qr_code_shoptima_url;

        //String url = "http://" + "llposmgr.ddns.net:8085/Service.asmx";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //pDialog.dismiss();
                        // response code
                        String xmlString = response;

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
                        qrcodestring = dcsQrCodeString;
                        //qrcodestring = "dcs:ZMiWiadjOZm90Rszvn5+SG7Ep2MsGAfnfx8xoCE1e3rA4mtgaoy0BfjbFEHlqY5dRAZzgiBdN8zO+uJulLTQ3flsQ0wozp2FOyNmX2pCABM1QJIlTooayanA5NhSv3nQboljX7a7KnjoRCF/zOXccA==";

                        //bitmap_qr_shoptima = convertQRCodeImage(context,qrcodestring);
                        //convertQRCodeImage(dcsQrCodeString);
                        //bitmapQRCode[0] = convertQRCodeImage(context, qrcodestring);
                        //bitmapQRCode = convertQRCodeImage(context, qrcodestring);
                        convertQRCodeImage(context, qrcodestring, pDialog,BillNo);
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

                byte[] b = temp.getBytes(Charset.forName("UTF-8"));

                return b;
            }

            @Override
            public String getBodyContentType() {
                return "text/xml;charset=utf-8";
            }
        };
        queue_qrcode.add(stringRequest);
        pDialog.show();
        //return bitmapQRCode;
        //progressDialog = Query.showProgressDialog(context, ENUM.Downaloding);
    }

    //    private void convertQRCodeImage(String dcsQrCodeString, Bitmap qrcode_bitmap, int billID) {
    private static void convertQRCodeImage(Context context, String dcsQrCodeString, SweetAlertDialog pDialog,String BillNo) {
        //pDialog.dismiss();
        Bitmap bitmap_qr_shoptima = null;
        try {
            //this.qrcode_bitmap = TextToImageEncode(dcsQrCodeString);
//            final SweetAlertDialog pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
//            pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
//            pDialog.setTitleText("Loading ...");
//            pDialog.show();
            bitmap_qr_shoptima = TextToImageEncode(context,dcsQrCodeString,pDialog);
            //Log.i("___chk_qrt", "_bitmap_qr_shoptima___" + bitmap_qr_shoptima);
            bitmap__ = null;
            bitmap__ = Query.GetLogPrint();


            Query.CheckEmulatorOrNot(context,sale_id,BillNo, Constraints.SALES, Constraints.SALES,bitmap__,bitmap_qr_shoptima);

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
        pDialog.dismiss();
        return bitmap;
    }


    static String BalanceNo = null;
    final static String Zeroes = "00000000";
    static String balance_title = "Balance";


    private static void PostSales() {
        String retail_ID = "";
        String company_code = "";
        String url = "";
        RequestQueue queue = Volley.newRequestQueue(context);
        Cursor config_values_pro_item = null;
        config_values_pro_item = DBFunc.Query("SELECT retails_id,company_code,company_url FROM POSSys", true);
        if (config_values_pro_item != null) {
            while (config_values_pro_item.moveToNext()) {
                retail_ID = config_values_pro_item.getString(0);
                company_code = config_values_pro_item.getString(1);
                url = config_values_pro_item.getString(2);
            }
            config_values_pro_item.close();
        }

        final String finalCompany_code = company_code;
        final JSONObject jsonObject = new JSONObject();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response code
                        String xmlString = response;

//                            Document xmlparse  = null;
//                            Document parse = APIActivity.XMLParseFunction(xmlString, xmlparse);
//                            for (int i=0;i< parse.getElementsByTagName("PostSalesResponse").getLength();i++) {
//                                PostSalesResponse = (parse.getElementsByTagName("PostSalesResponse").getLength() > 0)
//                                        ? parse.getElementsByTagName("PostSalesResponse").item(i).getTextContent() : " ";
//                            }
//                            //Log.i("getResult",getInventoryResult);
//                            //displayPopupDialogQRScan(getInventoryResult,getInventoryResult);
//
//                            try {
//
//
////                                {"CompanyID":"5","RetailerID":"4","TransNo":"ON000001","TotalDue":"20",
////                                        "TotalGST":"0.00","TotalDisc":"0.00","TransDate":"2020-06-17",
////                                        "CreateTime":"2020-06-17 16:28:22", "TotalQty":"2","CashierID":"",
////                                        "MemberID":"0bb22288-b073-11ea-84cf-00155d01ca02","isNewCust":"Y",
////                                        "SalesPersonID":"","CommID":"","CommPerc":"0","ReceiptOrderStatus":"",
////                                        "vchQueueNo":"0001","MacAddress":"","TerminalID":"1","PendingSync":"N",
////                                        "LastUser":"","LastUpdate":"2020-06-17 16:28:22","LockUser":"N",
////                                        "LockUpdate":"2020-06-17 16:28:22", "LockStatus":"0","RecordStatus":"READY",
////                                        "RecordUpdate":"2020-06-17 16:28:22",
////                                        "QueueStatus":"READY",
////                                        "salesorder_item":[{"RecordNo":"1","LineNo":"1","ItemQty":"2","ItemPrice":"10",
////                                        "ItemTotal":"20.00","ItemGST":"0.00","ItemDiscType":"","ItemDisc1":"0.00","ItemDisc2":"0.00",
////                                        "ItemDisc3":"0.00","ItemID":"4ffb9a83-4efe-11ea-84ac-0894ef44a723","ItemBarcode":"4710227231304", "ItemUOM":"", "ItemGSTInEx":"N","ItemCost":"6.00","ItemActQty":"1.000","ItemUOMID":"", "ItemGroupDisc":"0.00","ItemSKU":"4710227231304","SupplierID":"","SalesPersonID":"","SalesCommTypeID":"","SalesCommPerc":"0.00","ItemCommPerc":"0.00","ItemCommAmt":"0.00","ItemSerialNo":"","DISCID":"","ItemIMEINo":"","ItemBatchNo":"","ItemStatus":"","OpenPriceRemark":"","ItemRemark":"", "ExpireDate":"0000-00-00", "ExpiryDay":"0","RedeemPoint":"0","ParentItemID_ADDON":"","bitAddOnItem":"N", "ParentDetailID_ADDON":"","MemDOBDiscPerc":"0.00", "MemDOBDiscAmount":"0.00","ReceiptOrderStatus":"","TerminalID":"1","RFID":"","PendingSync":"N","LastUser":"","LastUpdate":"2020-06-17 16:28:22","LockUser":"N","LockUpdate":"2020-06-17 16:28:22","LockStatus":"0","RecordStatus":"READY","RecordUpdate":"2020-06-17 16:28:22","QueueStatus":"READY"}]}
//
//                                JSONObject obj = new JSONObject(PostSalesResponse);
//                                String salesorder_item_obj = obj.getString("salesorder_item");
//                                JSONArray mJsonArray = new JSONArray(salesorder_item_obj);
//
//                                JSONObject salesorder_item_Object = null;
//                                //Log.i("mJsonArrayLength:", String.valueOf(mJsonArray.length()));
//                                DBFunc.ExecQuery("DELETE FROM Inventory", true);
//                                for (int i =0; i < mJsonArray.length(); i++){
//                                    salesorder_item_Object = mJsonArray.getJSONObject(i);
//                                    RecordNo = salesorder_item_Object.getString("RecordNo");
//                                    LineNo = salesorder_item_Object.getString("LineNo");
//                                    ItemQty = salesorder_item_Object.getString("ItemQty");
//                                    ItemPrice = salesorder_item_Object.getString("ItemPrice");
//                                    ItemTotal = salesorder_item_Object.getString("ItemTotal");
//                                    ItemGST = salesorder_item_Object.getString("ItemGST");
//                                    ItemDiscType = salesorder_item_Object.getString("ItemDiscType");
//                                    ItemDisc1 = salesorder_item_Object.getString("ItemDisc1");
//                                    ItemDisc2 = salesorder_item_Object.getString("ItemDisc2");
//                                    ItemDisc3 = salesorder_item_Object.getString("ItemDisc3");
//                                    IttemID = salesorder_item_Object.getString("ItemID");
//                                    ItemBarcode = salesorder_item_Object.getString("ItemBarcode");
//                                    ItemUOM = salesorder_item_Object.getString("ItemUOM");
//                                    ItemCost = salesorder_item_Object.getString("ItemCost");
//                                    ItemUOMID = salesorder_item_Object.getString("ItemUOMID");
//                                    ItemSKU = salesorder_item_Object.getString("ItemSKU");
//                                    QueueStatus = salesorder_item_Object.getString("QueueStatus");
//
//                                }
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
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
                        "    <PostSales xmlns=\"http://tempuri.org/\">\n" +
                        "      <companyCode>"+finalCompany_code+"</companyCode>\n" +
                        "      <json>"+ LalamoveAPI.sendPostSales()+"</json>\n" +
                        "      <salestype>"+ Constraints.SALES+"</salestype>\n" +
                        "    </PostSales>\n" +
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
        // }
    }

    public static String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 5, bos);
        byte[] bitmapdata = bos.toByteArray();
//        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG,50, baos);
//        byte [] b=baos.toByteArray();
        String temp=Base64.encodeToString(bitmapdata, Base64.DEFAULT);
        return temp;
    }
//    public static void PrintingReceipt(final int sale_id, final Context mcontext, final String status, final Bitmap bitmap__, final Bitmap bitmap_qr_shoptima, final String billNo) {
////        Log.i("Chkk","chkk_ddd_"+sale_id+"__");
////        String chk_print_receipt_paper = "0";
////        try {
////            chk_print_receipt_paper = Query.GetOptions(23);
////        }catch (StringIndexOutOfBoundsException e){
////            chk_print_receipt_paper = "0";
////        }finally {
////            chk_print_receipt_paper = chk_print_receipt_paper;
////        }
//
////        PrintReceiptFun(mcontext,sale_id,billNo,status);
//
////        Log.i("ReceiptPrintchekk","DFDF_"+chk_print_receipt_paper+"__"+status);
////        if (chk_print_receipt_paper.equals("1")) {
////
////
////            if (status.equals("Reprint")) {
////                PrintReceiptFun(mcontext,sale_id,billNo,status);
////            }if (status.equals("Refund")) {
////                PrintReceiptFun(mcontext,sale_id,billNo,status);
////            } else {
////
////                Log.i("ReceiptPrintchekk","chkk_"+"one");
////                try {
////
////
////
////                    Log.i("ReceiptPrintchekk","chkk_"+"one1");
//////                            new SweetAlertDialog(mcontext, SweetAlertDialog.WARNING_TYPE)
//////                                    .setTitleText(ENUM.Print)
//////                                    .setConfirmText(ENUM.YES)
//////                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//////                                        @Override
//////                                        public void onClick(SweetAlertDialog sDialog) {
//////                                            sDialog.dismissWithAnimation();
//////                                            Integer id = Query.findLatestID("ID","SALES",false);
//////                                            Log.i("Chkk","chkk__"+sale_id+"__"+id);
//////                                            PrintReceiptFun(mcontext,sale_id,billNo,status);
//////                                        }
//////                                    })
//////                                    .setCancelButton(ENUM.No, new SweetAlertDialog.OnSweetClickListener() {
//////                                        @Override
//////                                        public void onClick(SweetAlertDialog sDialog) {
//////                                            sDialog.dismissWithAnimation();
//////
//////                                            Log.i("CheckingPS","DFDFDFD____"+"two");
//////                                            Log.i("paymentsuccess__","four__"+"four");
//////                                            Intent i = new Intent(mcontext, PaymentCashSuccesActivity.class);
//////                                            mcontext.startActivity(i);
//////                                        }
//////                                    })
//////
//////                                    .show();
////                    final SweetAlertDialog pDialog =  new SweetAlertDialog(mcontext, SweetAlertDialog.WARNING_TYPE)
////                            .setTitleText(ENUM.Print)
////                            .setConfirmText(ENUM.YES)
////                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
////                                @Override
////                                public void onClick(SweetAlertDialog sDialog) {
////                                    sDialog.dismissWithAnimation();
////                                    Integer id = Query.findLatestID("ID","SALES",false);
////                                    Log.i("Chkk","chkk__"+sale_id+"__"+id);
////                                    PrintReceiptFun(mcontext,sale_id,billNo,status);
////                                }
////                            })
////                            .setCancelButton(ENUM.No, new SweetAlertDialog.OnSweetClickListener() {
////                                @Override
////                                public void onClick(SweetAlertDialog sDialog) {
////                                    sDialog.dismissWithAnimation();
////
////                                    Log.i("CheckingPS","DFDFDFD____"+"two");
////                                    Log.i("paymentsuccess__","four__"+"four");
////                                    Intent i = new Intent(mcontext, PaymentCashSuccesActivity.class);
////                                    mcontext.startActivity(i);
////                                }
////                            });
////                    pDialog.show();
////                    pDialog.setCancelable(false);
////
////
////                }catch (Exception e) {
////
////                    Log.i("ReceiptPrintchekk","chkk_"+"two");
////                    Log.i("ReceiptPrintchekk","DFDFmesage_"+e.getMessage());
////                }
////            }
////        }else {
////            PrintReceiptFun(mcontext,sale_id,billNo,status);
//////             if (status.toUpperCase().equals("REPRINT")) {
//////                 PrintReceiptFun(mcontext,sale_id,billNo,status);
////////                 Intent intent = new Intent(mcontext, TransactionDetailsActivity.class);
////////                 intent.putExtra("BillNo", billNo);
////////                 mcontext.startActivity(intent);
////////                 // ((Activity)mcontext).finish();
//////            }else if (status.toUpperCase().equals("REFUND")) {
//////                 PrintReceiptFun(mcontext,sale_id,billNo,status);
////////                 Intent intent = new Intent(mcontext, TransactionDetailsActivity.class);
////////                 intent.putExtra("BillNo", billNo);
////////                 mcontext.startActivity(intent);
////////                 // ((Activity)mcontext).finish();
//////            }else if (status.toUpperCase().equals("CANCEL")) {
//////                 PrintReceiptFun(mcontext,sale_id,billNo,status);
////////                 Intent intent = new Intent(mcontext, TransactionDetailsActivity.class);
////////                 intent.putExtra("BillNo", billNo);
////////                 mcontext.startActivity(intent);
//////////                 ((Activity)mcontext).finish();
//////            }else {
//////
//////
//////                 Log.i("FFF___","S2");
//////                 Intent i = new Intent(mcontext, PaymentCashSuccesActivity.class);
//////                 mcontext.startActivity(i);
//////             }
////        }
//
//
////        CheckOutActivity.billDisValue = "";
////        CashLayoutActivity.BillDiscountValue = 0.0;
////        CheckOutActivity.billDisName = "";
////        CheckOutActivity.billDisNameAmt = "";
////        CheckOutActivity.billDisValueAmt = "";
////
////        CheckOutActivity.sldIDArr.clear();
////        CheckOutActivity.sldItemDisArr.clear();
////        sldDiscountName.clear();
////        sldDiscountType.clear();
////        sldDiscountValue.clear();
////
////        RecyclerViewNoImageAdapter.slddisID.clear();
////        RecyclerViewNoImageAdapter.slddisName.clear();
////        RecyclerViewNoImageAdapter.slddisTypeName.clear();
////        RecyclerViewNoImageAdapter.slddisType.clear();
////        RecyclerViewNoImageAdapter.slddisValue.clear();
////
////        RecyclerViewAdapter.slddisID.clear();
////        RecyclerViewAdapter.slddisName.clear();
////        RecyclerViewAdapter.slddisTypeName.clear();
////        RecyclerViewAdapter.slddisType.clear();
////        RecyclerViewAdapter.slddisValue.clear();
////
////        RecyclerViewNoImageAdapter.item_diss_amt = "0";
////        RecyclerViewNoImageAdapter.sltBillDisArr.clear();
////
////        RecyclerViewAdapter.item_diss_amt = "0";
////        RecyclerViewAdapter.sltBillDisArr.clear();
//
////        CheckOutActivity.CashValuePaymentNameArr.clear();
////        CheckOutActivity.CashValuePaymentIDArr.clear();
////        CheckOutActivity.CashValueArr.clear();
////
////        change_due_amt = 0.0;
//    }

    public static void PrintReceiptFun(final Context mcontext, final Integer sale_id, final String billNo, final String status,
                                       final Bitmap bitmap__, final Bitmap bitmap_qr_shoptima) {

//         String chk_print_receipt_paper = "0";
//        try {
//            chk_print_receipt_paper = Query.GetOptions(23);
//        }catch (StringIndexOutOfBoundsException e){
//            chk_print_receipt_paper = "0";
//        }finally {
//            chk_print_receipt_paper = chk_print_receipt_paper;
//        }


        if (sale_id > 0) {

//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//
//                PrintFormatFun(mcontext,sale_id,billNo,status,bitmap__,bitmap_qr_shoptima);
//            }

            if (status.toUpperCase().equals(Constraints.SALES.toUpperCase())){

                Intent i = new Intent(mcontext, PaymentCashSuccesActivity.class);// If Receipt Print Checkbox Untick
                mcontext.startActivity(i);
            }else {
                //PrintFormatFun(sale_id,billNo,status,bitmap__,bitmap_qr_shoptima);

//                Toast.makeText(mcontext, status.toUpperCase() + " Successfully.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mcontext, TransactionDetailsActivity.class);
                intent.putExtra("BillNo", billNo);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mcontext.startActivity(intent);
//                ((Activity)mcontext).finish();

            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                String terminaltype_check = Query.GetDeviceData(Constraints.TERMINAL_TYPE);
                Integer receiptCount = 1;

                if (terminaltype_check.toUpperCase().equals(Constraints.IMIN.toUpperCase())) {
                    receiptCount = MainActivity.receiptCount;
                }//here
                for (int printcount = 0; printcount < receiptCount; printcount++) {
                    PrintFormatFun(mcontext, sale_id, billNo, status, bitmap__, bitmap_qr_shoptima);
                }
            }
        } else {
//            Toast.makeText(mcontext, status.toUpperCase() + " Successfully.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(mcontext, TransactionDetailsActivity.class);
            intent.putExtra("BillNo", billNo);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mcontext.startActivity(intent);
//            ((Activity)mcontext).finish();
        }

//        Query.UpdateBillListForZClose(billNo);

        CashLayoutActivity.CashValue = 0.0;
        CashLayoutActivity.BillDiscountValue = 0.0;
        //CashLayoutActivity.CashID = 0;
        CashLayoutActivity.CashID = "0";
        CashLayoutActivity.CashName = "0";

        CheckOutActivity.billDisValue = "";
        CashLayoutActivity.BillDiscountValue = 0.0;
        CheckOutActivity.billDisName = "";
        CheckOutActivity.billDisNameAmt = "";
        CheckOutActivity.billDisValueAmt = "";

        CheckOutActivity.sldIDArr.clear();
        CheckOutActivity.sldItemDisArr.clear();
        sldDiscountName.clear();
        sldDiscountType.clear();
        sldDiscountValue.clear();

//        RecyclerViewNoImageAdapter.slddisID.clear();
//        RecyclerViewNoImageAdapter.slddisName.clear();
//        RecyclerViewNoImageAdapter.slddisTypeName.clear();
//        RecyclerViewNoImageAdapter.slddisType.clear();
//        RecyclerViewNoImageAdapter.slddisValue.clear();

        RecyclerViewAdapter.slddisID.clear();
        RecyclerViewAdapter.slddisName.clear();
        RecyclerViewAdapter.slddisTypeName.clear();
        RecyclerViewAdapter.slddisType.clear();
        RecyclerViewAdapter.slddisValue.clear();
//
//        RecyclerViewNoImageAdapter.item_diss_amt = "0";
//        RecyclerViewNoImageAdapter.sltBillDisArr.clear();

        RecyclerViewAdapter.item_diss_amt = "0";
        RecyclerViewAdapter.sltBillDisArr.clear();

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void PrintFormatFun(Context mcontext, Integer sale_id, String billNo, String status,
                                      Bitmap bitmap__, Bitmap bitmap_qr_shoptima) {
        Header = "";
        Footer = "";
        getHeaderFooterValues();

        Header += HeaderValue.replace("_&ABCD_EF_G&", "\n");

        Footer += FooterValue.replace("_&ABCD_EF_G&", "\n");

        Tax();

        SalesData(sale_id);

        getBillDetailsAll(billNo);

        IminPrintUtils mIminPrintUtils = null;

        String terminaltype_check = Query.GetDeviceData(Constraints.TERMINAL_TYPE);

        try {

            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                    System.currentTimeMillis(), DBFunc.PurifyString("Chk-CL-ChkmIminPrintUtils-"+mIminPrintUtils));

        } catch (Exception e){
            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                    System.currentTimeMillis(), DBFunc.PurifyString("Err-CL-ChkmIminPrintUtils-"+mIminPrintUtils+"-"+e.getMessage()));
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

            mIminPrintUtils = IminPrintUtils.getInstance(mcontext);
            DeviceListAdapter mAdapter = new DeviceListAdapter(mcontext);
            ;
            List<BluetoothDevice> printerDevices = BluetoothUtil.getPairedDevices();
            mAdapter.clear();

            mAdapter.addAll(printerDevices);
            int bluetoothPosition = 0;
//                int bluetoothPosition = 1;
            BluetoothDevice device = mAdapter.getItem(bluetoothPosition);

            try {

                mIminPrintUtils.initPrinter(IminPrintUtils.PrintConnectType.BLUETOOTH, device);
                DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                        System.currentTimeMillis(), DBFunc.PurifyString("OK-CashLayoutActivity-ReceiptPrint-"
                                + mIminPrintUtils));

            } catch (IOException e) {
                e.printStackTrace();
                DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                        System.currentTimeMillis(), DBFunc.PurifyString("Err-CashLayoutActivity-ReceiptPrint-" + e.getMessage() + "-" + mIminPrintUtils));
            }
            mIminPrintUtils.setTextSize(22);
            mIminPrintUtils.setTextStyle(Typeface.BOLD);
            mIminPrintUtils.setTextLineSpacing(1.0f);

            mIminPrintUtils.setTextLineSpacing(1.0f);
        }

        ReceiptData receiptDataJson = new ReceiptData();
        try {
            receiptDataJson = printingReceiptFormat(sale_id, status, billNo, mIminPrintUtils);
        } catch (Exception e){
            Log.i("terminaltype_check__","terminaltype_check__1_"+e.getMessage());
        }


        if (terminaltype_check.toUpperCase().equals(Constraints.IMIN.toUpperCase())) {

            new PrintingForIMIN(mcontext,ReceiptNo,sale_id, status, billNo,mIminPrintUtils);


        } else if (terminaltype_check.toUpperCase().equals(Constraints.INGENICO.toUpperCase())){

            new PrintingForIngenico(mcontext,printer,str);

        }else if (terminaltype_check.toUpperCase().equals(Constraints.PAX.toUpperCase())) {

            new PrintingForPAX(terminaltype_check,bitmap__,receiptDataJson,bitmap_qr_shoptima,ReceiptNo);

        }else if (terminaltype_check.toUpperCase().equals("Verifone".toUpperCase())) {
//              new PrintingForVerifone();
        }else if (terminaltype_check.toUpperCase().equals(Constraints.PAX_E600M.toUpperCase())) {

             printReceipt(mcontext,384,str);
        }
    }


    private static final int WHITE = 0xFFFFFFFF;
    private static final int BLACK = 0xFF000000;

    public static Bitmap encodeAsBitmap(String contents, BarcodeFormat format, int img_width, int img_height) throws WriterException {
        String contentsToEncode = contents;
        if (contentsToEncode == null) {
            return null;
        }
        Map<EncodeHintType, Object> hints = null;
        String encoding = guessAppropriateEncoding(contentsToEncode);
        if (encoding != null) {
            hints = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
            hints.put(EncodeHintType.CHARACTER_SET, encoding);
        }
        MultiFormatWriter writer = new MultiFormatWriter();
        BitMatrix result;
        try {
            result = writer.encode(contentsToEncode, format, img_width, img_height, hints);
        } catch (IllegalArgumentException iae) {
            // Unsupported format
            return null;
        }
        int width = result.getWidth();
        int height = result.getHeight();
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            int offset = y * width;
            for (int x = 0; x < width; x++) {
                pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

    private static String guessAppropriateEncoding(CharSequence contents) {
        // Very crude at the moment
        for (int i = 0; i < contents.length(); i++) {
            if (contents.charAt(i) > 0xFF) {
                return "UTF-8";
            }
        }
        return null;
    }

    public static final byte[] intToByteArray(int value) {
        return new byte[] {
                (byte)(value >>> 24),
                (byte)(value >>> 16),
                (byte)(value >>> 8),
                (byte)value};
    }


    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }

    public static void SalesData(int sale_id) {
        String sql = "SELECT SUM(TotalItemDisount),SUM(TotalBillDisount)," +
                "SUM(TotalTaxes),SUM(TotalNettSales),SUM(SubTotal),BillNo,SUM(ServiceCharges),SUM(RoundAdj)," +
                "SUM(DiscountValue),DiscountTypeName,DiscountName,SUM(TotalTaxes),SUM(TotalQty),SalesDateTime," +
                "Changeamount FROM Sales " +
                "WHERE ID = " + sale_id + " group by ID order by ID DESC ";
        Log.i("__sql", sql);
        Cursor c = DBFunc.Query(sql, false);
        if (c != null) {

            ClearSaleDataFun();

            while (c.moveToNext()) {
                //if (!c.isNull(0)) {
                    TotalItemDisount = c.getString(0);
                    TotalBillDisount = c.getString(1);
                    TotalTaxes = c.getDouble(2);
                    Total = c.getString(3);
                    SubTotal = c.getString(4);
                    BillNo = c.getString(5);
                    ServiceCharges = c.getString(6);
                    RoundAdj = c.getString(7);
                    billDiscountValueSales = c.getDouble(8);
                    billDiscountTypeNameSales = c.getString(9);
                    DiscountNameSales = c.getString(10);
                    TotalItems = c.getString(12);
                    ReceiptNoDateTime = c.getString(13);
                    ReceiptNo = BillNo;
                    Change = c.getString(14);
                //}
            }
        }
        Log.i("SubTotal___","SubTotal_ddd___"+SubTotal);
    }

    private static void ClearSaleDataFun() {
        TotalItemDisount = "";
        TotalBillDisount = "";
        TotalTaxes = 0.0;
        TotalItems = "0";
        Total = "";
        SubTotal = "";
        BillNo = "";
        ServiceCharges = "";
        RoundAdj = "";
        billDiscountValueSales = 0.0;
        billDiscountTypeNameSales = "";
        DiscountNameSales = "";
        ReceiptNoDateTime = "";
        ReceiptNo = "";
        Change = "";
    }

    public static void getBillDetailsAll(String billNo){
        Cursor c = Query.GetdetailsBillProductByBillNo(billNo);
        if (c != null) {

            ClearArrayFun();

            while (c.moveToNext()) {
                if (!c.isNull(0)) {
                    if (c.getInt(12) != -1){

                        sldQtyArr.add(c.getString(0));
                        //IDArr.add(c.getString(12));
                        IDArr.add(c.getString(10));
                        String Remarks = c.getString(14);
                        detailsBillPID.add(c.getInt(15));
//                        if (Remarks.trim() != "" && Remarks.length() > 0 && !(Remarks.equals("0"))) {
//                            sldNameArr.add(c.getString(1) + "\n" + "(" + Remarks + ")");
//                        } else {
//                            sldNameArr.add(c.getString(1));
//                        }
                        //sltPriceTotalArr.add(c.getString(2));
                        sldNameArr.add(c.getString(1));
//                        if (Remarks != null && Remarks.length() > 8) {

                        String chkicno_on = Query.GetOptions(26);
                        if (chkicno_on.equals("1")) {
                            //HPB
                            if (Remarks != null && Remarks.length() == 9) {
                                sldRemarksArr.add(Constraints.PASSFirstDigits+Remarks.substring(5,9));
                            } else {
                                sldRemarksArr.add(Remarks);
                            }
                        } else {
                            sldRemarksArr.add(Remarks);
                        }
                        sltPriceTotalArr.add(c.getString(11));
                        sltItemDisArr.add(c.getDouble(3));
                        sldDiscountName.add(c.getString(4));
                        sldDiscountType.add(c.getString(5));
                        sldDiscountValue.add(c.getString(6));
                        sldTaxID.add(c.getString(7));
                        sldCTaxName.add(c.getString(8));
                        sldCTaxAmount.add(c.getString(9));
                        sldIDPromoArr.add(c.getString(10));
                        sldOpenPriceStatusArr.add(c.getString(13));

                        try {
                            Cursor CursortaxObj = Query.GetTaxById(Integer.parseInt(c.getString(7)));

                            if (CursortaxObj != null) {
                                if (CursortaxObj.moveToNext()) {
                                    sldCTaxType.add(CursortaxObj.getString(0));
                                    sldCTaxRate.add(CursortaxObj.getString(1));
                                } else {
                                    sldCTaxType.add("0");
                                    sldCTaxRate.add("0");
                                }
                                CursortaxObj.close();
                            } else {
                                sldCTaxType.add("0");
                                sldCTaxRate.add("0");
                            }
                        }catch (NumberFormatException e){
                            sldCTaxType.add("0");
                            sldCTaxRate.add("0");
                        }
                    }
                }
            }
            c.close();
        }   Log.i("___","sldNameArr___"+sldNameArr);
    }

    private static void ClearArrayFun() {
        IDArr.clear();
        sldQtyArr.clear();
        sldNameArr.clear();
        sldRemarksArr.clear();
        sltPriceTotalArr.clear();
        sltItemDisArr.clear();
        sldDiscountName.clear();
        sldDiscountType.clear();
        sldDiscountValue.clear();
        sldTaxID.clear();
        sldCTaxName.clear();
        sldCTaxAmount.clear();
        sldCTaxType.clear();
        sldCTaxRate.clear();
        sldIDPromoArr.clear();
        sldOpenPriceStatusArr.clear();
        detailsBillPID.clear();
    }

//    @RequiresApi(api = Build.VERSION_CODES.N)
//    private static void printingReceiptFormatIMIN(Integer sale_id, String status, String BillNo) {
//        Log.i("printinatIMIN","ff__str_imnd"+str_imn);
//        String terminalTypeVal = Query.GetDeviceData(Constraints.TERMINAL_TYPE);
//        str = "\n"+ Header;
//        String total_line = "";
//        Integer line32spacecount = Query.GetLineSpaceCount(terminalTypeVal,32,0);
//        if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)) {
//            line32spacecount = line32spacecount + 5;
//        }
//        for (int i = 0; i < line32spacecount; i++) {
//            total_line += "-";
//        }
//////
//
//        if (status.toUpperCase().equals(Constraints.VOID.toUpperCase())) {
//            str += "\n" + total_line;
//            str += "\n" + "Cancel Bill";
//        }
//        if (status.toUpperCase().equals(Constraints.CANCEL.toUpperCase())) {
//            str += "\n" + total_line;
//            str += "\n" + "Cancel Bill";
//        }
//        if (status.toUpperCase().equals(Constraints.REFUND.toUpperCase())) {
//            str += "\n" + total_line;
//            str += "\n" + "Refund Bill";
//        }
//        if (status.toUpperCase().equals(Constraints.Reprint.toUpperCase())) {
//            str += "\n" + total_line;
//            str += "\n" + "Reprint Bill";
//
//            String sq = "SELECT STATUS FROM SALES WHERE ID = "+sale_id;
//            Cursor csq = DBFunc.Query(sq,false);
//            if ( csq != null) {
//                if (csq.moveToNext()) {
//                    if (csq.getString(0).toUpperCase().equals(Constraints.VOID.toUpperCase())) {
//                        str += "\n" + total_line;
//                        str += "\n" + "Cancel Bill";
//                    }
//                    if (csq.getString(0).toUpperCase().equals(Constraints.CANCEL.toUpperCase())) {
//                        str += "\n" + total_line;
//                        str += "\n" + "Cancel Bill";
//                    }
//                }
//                csq.close();
//            }
//        }
//        str += "\n" + total_line;
//
//        String tblName = "0";
//        String queName = "0";
//        String orderStatus = "0";
//
//        Cursor C_DetailsBillProduct = Query.SearchBillProductByBillNo(BillNo, Constraints.NoGroupBy);
//
//        if (C_DetailsBillProduct != null) {
//            if (C_DetailsBillProduct.moveToNext()) {
//                tblName = C_DetailsBillProduct.getString(3);
//                queName = C_DetailsBillProduct.getString(4);
//                orderStatus = C_DetailsBillProduct.getString(5);
//            }
//            C_DetailsBillProduct.close();
//        }
//
//        if (!(queName == null ||  queName.equals("0") || queName.isEmpty())) {
//            if (!queName.equals("0 ,")) {
//
//            }
//            //str += "\n" + "QueueNo #" + queName;
//        }
//        if (!(tblName == null || tblName.equals("0") || !tblName.equals("0,") || tblName.isEmpty() )) {
//            Log.i("__tblName","tblName____"+tblName);
//            //str += "\n" + "TableNo #" + tblName;
//        }
//        if (orderStatus != null && orderStatus.toUpperCase().equals("HOLD".toUpperCase())) {
//            //str += "\n" + "Order Status #" + orderStatus;
//        }
//
//        //str += "\n"+ "DATE #" + ReceiptNoDateTime;
//        //str += "\n"+ "RECEIPT NO #" + ReceiptNo;
//        //Delivery
//        String delivery_text = Query.GetSalesDelivery(ReceiptNo);
//        if (delivery_text.length() > 0) {
//            //str += "\n\n"+Html.fromHtml(delivery_text);
//        }
//        str_imn = str;
//        String description_header =  "DESCRIPTION        QTY         PRICE";
//        str +="\n"+"\n"+ description_header;
//
//        total_line = "";
//        for (int i = 0; i < line32spacecount; i++) {
//            total_line += "-";
//        }
//        str += "\n" + total_line;
////        Integer line20spacecount = Query.GetLineSpaceCount(terminalTypeVal,20,0);
////        Integer line21spacecount = Query.GetLineSpaceCount(terminalTypeVal,21,0);
////        Integer line12spacecount = Query.GetLineSpaceCount(terminalTypeVal,12,0);
////        Integer line11spacecount = Query.GetLineSpaceCount(terminalTypeVal,11,0);
////        Integer line25spacecount = Query.GetLineSpaceCount(terminalTypeVal,25,0);
////        Integer line18pacecount = Query.GetLineSpaceCount(terminalTypeVal,18,0);
////        Integer line16spacecount = Query.GetLineSpaceCount(terminalTypeVal,16,0);
////        Integer line5spacecount = Query.GetLineSpaceCount(terminalTypeVal,5,0);
//        Double GrandtotalItemDis = 0.0;
//        for (int qty = 0 ; qty < sldQtyArr.size(); qty ++) {
//            //Qty
//            Qty = sldQtyArr.get(qty);
////            Qty = IDArr.get(qty);
//            Integer line5spacecount = Query.GetLineSpaceCount(terminalTypeVal,5,0);
////            }
//            strQty = validate_space(0,line5spacecount,Qty,"Qty");
//
//            //Name
//            Name = sldNameArr.get(qty);
//
//            //strName = GetFormatForDescription(Name);
//            strNameOrigin = GetFormatForDescription(Name,terminalTypeVal);
//
//            Double realTotAmt = 0.0;
//            String _StrName = "";
//            String strItemDisAmt = "";
//            //Double disamt = Double.valueOf(sltItemDisArr.get(qty));
//            Double disamt = Double.valueOf(Qty) * Double.valueOf(sltItemDisArr.get(qty));
//            //if (disamt > 0.0) {
//
//            if (disamt != 0.0) {
//                String StrName = "";
//                if (sldDiscountType.get(qty).equals("% Percentage")){
//                    if (terminalTypeVal.toUpperCase().equals(Constraints.INGENICO.toUpperCase())){
//                        StrName = sldDiscountName.get(qty) + "(" + sldDiscountValue.get(qty) + "%%" + ") ";
//                    }else {
//                        StrName = sldDiscountName.get(qty) + "(" + sldDiscountValue.get(qty) + "%" + ") ";
//                    }
//                }
//                if (sldDiscountType.get(qty).equals("$ Dollar Value")){
//                    StrName =  sldDiscountName.get(qty) +  "($" + sldDiscountValue.get(qty) + "" + ") ";
//                }
//
//                // String StrName = "***Item Discount***";
//                //_StrName = GetFormatForDesctiption(StrName);
//                //_StrName = StrName;
//                Integer line21spacecount = Query.GetLineSpaceCount(terminalTypeVal,2,01);
//                _StrName = validate_space(0,line21spacecount,StrName,"Qty");;
//                //_StrName = validate_space(0,21,StrName,"Qty");;
//                // _StrName = StrName;
//                //totalItemDis = sltItemDisArr.get(qty) * Double.valueOf(sldQtyArr.get(qty));
//                //ItemDisAmt = "-" + String.format("%.2f", totalItemDis);
//
//                if (terminalTypeVal.toUpperCase().equals(Constraints.INGENICO.toUpperCase())){
//                    Integer line12spacecount = Query.GetLineSpaceCount(terminalTypeVal,12,0);
////                    if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)) {
////                        line12spacecount = line12spacecount + 8;
////                    }
//                    strItemDisAmt = validate_space(0, line12spacecount, "-" + String.format("%.2f", disamt), "Price");
//                }else {
//                    Integer line11spacecount = Query.GetLineSpaceCount(terminalTypeVal,11,0);
////                    if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)) {
////                        line11spacecount = line11spacecount + 8;
////                    }
//                    strItemDisAmt = validate_space(0, line11spacecount, "-" + String.format("%.2f", disamt), "Price");
//                }
//                GrandtotalItemDis += Double.valueOf(disamt);
//
//                //realTotAmt = Double.valueOf(sltItemDisArr.get(qty)) + Double.valueOf(sltPriceTotalArr.get(qty));
//                realTotAmt = Double.valueOf(sltPriceTotalArr.get(qty)) - Double.valueOf(disamt);
//            }else {
//                realTotAmt = Double.valueOf(sltPriceTotalArr.get(qty));
//            }
//
//            //Price
//            //Price = String.format("%.2f", realTotAmt);
////            Price = String.format("%.2f", Double.valueOf(sltPriceTotalArr.get(qty)));
//            Price = String.format("%.2f", Double.valueOf(Qty) * Double.valueOf(sltPriceTotalArr.get(qty)));
//            Integer lineprice9spacecount = Query.GetLineSpaceCount(terminalTypeVal,9,0);
//            strPrice = validate_space(0,lineprice9spacecount,Price,"Price");
//            String nqp = "";
//
//            //nqp = strName + "x"+strQty + strPrice;
//            nqp = strNameOrigin + "x"+strQty + strPrice;
//            str += "\n" + nqp;
//            if (disamt !=  0.0) {
//                str += "\n" + _StrName + strItemDisAmt;
//            }
//            //Show PromoName And Value (MixAndMatch)
//            //GetPromo
//            str += GetShowPromoDetails(terminalTypeVal,qty);
////           str += GetShowPromoDetails(Integer.parseInt(String.valueOf(Qty)));
//
//            //GetPLUModi
//            str += GetShowPLUModiDetails(BillNo,String.valueOf(IDArr.get(qty)),String.valueOf(sldOpenPriceStatusArr.get(qty)),Qty);
//
////           GetShowPLUModiDetails(BillNo,String.valueOf(Qty));
//        }
//
//        //strST = validate_space(0,21,String.format("%.2f", Double.valueOf(SubTotal)),"Price");
//
//
//        //SalesData(sale_id);
//
//        //String StrSubtotal = Query.ShowAmtMinusCorrectly(Double.valueOf(Subtotal));
//        if (sale_id == -5){
//            SubTotal = String.valueOf(CheckOutActivity.sub_total);
//        }
//
//        String StrSubtotal = Query.ShowAmtMinusCorrectly(Double.valueOf(SubTotal));
//        Integer line21spacecount = Query.GetLineSpaceCount(terminalTypeVal,21,0);
////        if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)){
////            line21spacecount = line21spacecount + 10;
////        }
//        strST = validate_space(0,line21spacecount,StrSubtotal,"Price");
//
//        String str_item = "ITEM";
//        if (sale_id == -5){
//            TotalItems = "0";
//        }
//
//        if (TotalItems.length() > 0){
//            str_item = "ITEMS";
//        }
//        str += "\n" + total_line;
//        str += "\n" + "TOTAL  (" + TotalItems +") "+ str_item +".";
//        str += "\n" + total_line;
//        //str += "\n" + "SUB-TOTAL" +" $" + strST;
//        //str += "\n" + "SUB-TOTAL  " + strST;
//        str = "\n" + "SUB-TOTAL  " + strST;
//
//        if (Double.valueOf(TotalBillDisount) > 0.0) {
//            Integer line18pacecount = Query.GetLineSpaceCount(terminalTypeVal,18,0);
////            if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)){
////                line18pacecount = line18pacecount + 12;
////            }
//            strTotalBillDisount = validate_space(0, line18pacecount, "-$" + String.format("%.2f", Double.valueOf(TotalBillDisount)), "Price");
//            //str += "\n" + "BillDiscount $" +  strTotalBillDisount;
//            str += "\n" + "BillDiscount  " +  strTotalBillDisount;
//            if (billDiscountTypeNameSales.equals("% Percentage")) {
//                if (billDiscountValueSales < 0.0){
//                    billDiscountValueSales = (-1) * billDiscountValueSales;
//                    if (terminalTypeVal.toUpperCase().equals(Constraints.INGENICO.toUpperCase())){
//                        str += "\n" + DiscountNameSales + "(-" + billDiscountValueSales + "%%)";
//                    }else {
//                        str += "\n" + DiscountNameSales + "(-" + billDiscountValueSales + "%)";
//                    }
//                }else {
//                    if (terminalTypeVal.toUpperCase().equals(Constraints.INGENICO.toUpperCase())){
//                        str += "\n" + DiscountNameSales + "(" + billDiscountValueSales + "%%)";
//                    }else {
//                        str += "\n" + DiscountNameSales + "(" + billDiscountValueSales + "%)";
//                    }
//                }
//            }else {
//                if (billDiscountValueSales < 0.0){
//                    billDiscountValueSales = (-1) * billDiscountValueSales;
//                    str += "\n" + DiscountNameSales + "(-$" + billDiscountValueSales + ")";
//                }else {
//                    str += "\n" + DiscountNameSales + "($" + billDiscountValueSales + ")";
//                }
//            }
//        }
//
//        String totQtyaa = Query.GetPromotionPriceByBillNo(BillNo,"0");
//
//        Boolean checkSalesRefundReprint = false;
//        if (status.toUpperCase().equals(Constraints.SALES.toUpperCase()) || status.toUpperCase().equals(Constraints.REFUND.toUpperCase()) || status.toUpperCase().equals(Constraints.Reprint.toUpperCase())) {
//            checkSalesRefundReprint = true;
//        }else {
//            checkSalesRefundReprint = false;
//        }
//
//        //if (Double.valueOf(totQtyaa) > 0.0) {
//        if (Double.valueOf(totQtyaa) != 0.0) {
//            String StrtotQtyaa = Query.ShowAmtMinusCorrectly(Double.valueOf(String.valueOf(totQtyaa)));
//            Integer line20spacecount = Query.GetLineSpaceCount(terminalTypeVal,20,0);
//            String strPromotion = validate_space(0,line20spacecount, StrtotQtyaa, "Price");
//            str += "\n" + "Promotion" +  strPromotion;
//        }
//
//        Integer chk_serviceCharges = Query.CheckServiceCharges();
//        if (chk_serviceCharges == 1) {
//            String str_service_charges =  Query.GetServiceChargesNameAndPercentage();
//            if (ServiceCharges != null && ServiceCharges.length() > 0 && Double.valueOf(ServiceCharges) != 0.0) {
//                String str_ServiceChargesValue = Query.ShowAmtMinusCorrectly(Double.valueOf(String.valueOf(ServiceCharges)));
//                //String str_ServiceChargesName = validate_space(0, 20, str_service_charges.toUpperCase() + " $", "Qty");
//                Integer line20spacecount = Query.GetLineSpaceCount(terminalTypeVal,20,0);
//                String str_ServiceChargesName = validate_space(0, line20spacecount, str_service_charges.toUpperCase() + "  ", "Qty");
//                Integer line11spacecount = Query.GetLineSpaceCount(terminalTypeVal,11,0);
////                if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)) {
////                    line11spacecount = line11spacecount + 10;
////                }
//                str += "\n" + str_ServiceChargesName + validate_space(0, line11spacecount, str_ServiceChargesValue, "Price");
//            }
//        }
//        Cursor Cursor_tax = Query.GetTax();
//        if (Cursor_tax != null){
//            if (Cursor_tax.moveToNext()){
//                String taxRate = Cursor_tax.getString(0);
//                Integer taxType = Cursor_tax.getInt(1);
//                String taxName = Cursor_tax.getString(2);
//
//                String percentageSign = "";
//                if (terminalTypeVal.toUpperCase().equals(Constraints.INGENICO.toUpperCase())){
//                    percentageSign = "%%";
//
//                    if (taxName.contains("%")){
//                        Log.i("TAG","taxName_"+taxName);
//                        String first = taxName.split("%")[0];
//                        String second = "";
//                        try {
//
//                            second = taxName.split("%")[1];
//                        }catch (ArrayIndexOutOfBoundsException e){
//                            Log.i("TAG","error_"+e.getMessage());
//                        }
//                        String str = first + percentageSign + second;
//                        taxName = str;
//                    }
//                }else {
//                    percentageSign = "%";
//                }
//
//                if (taxType == 2){
//                    double amt_inclusive = Query.calculateInclusive(Double.valueOf(Total),Integer.parseInt(taxRate));
//                    if (amt_inclusive != 0.0) {
//                        String str_amt_inclusive = Query.ShowAmtMinusCorrectly(Double.valueOf(String.valueOf(amt_inclusive)));
//                        Log.i("TAXNAMEINCLUSIVE_","TAX__"+taxName.toUpperCase());
//                        String str_inclusive = taxName.toUpperCase() + "(" + taxRate + percentageSign +")" + " SGD " + str_amt_inclusive + " Incl";
//                        str += "\n" + str_inclusive;
//                    }
//                }else if (taxType == 3){
//                    if (TotalTaxes != 0.0) {
//                        String str_exclusive = Query.ShowAmtMinusCorrectly(Double.valueOf(String.valueOf(TotalTaxes)));
//                        Log.i("TAXNAMEe_","TAX__"+taxName.toUpperCase());
//                        line21spacecount = Query.GetLineSpaceCount(terminalTypeVal,21,0);
//                        String TaxExcul = validate_space(0, line21spacecount, taxName.toUpperCase() + "(" + taxRate + percentageSign +")" + "  ", "Qty");
//                        Integer line11spacecount = Query.GetLineSpaceCount(terminalTypeVal,11,0);
////                        if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)) {
////                            line11spacecount = line11spacecount + 10;
////                        }
//                        str += "\n" + TaxExcul + validate_space(0, line11spacecount, str_exclusive, "Price");
//                    }
//                }
//            }
//            Cursor_tax.close();
//        }
//
//        if (Double.valueOf(RoundAdj) != 0.0) {
//            String roundStr = Query.ShowAmtMinusCorrectly(Double.valueOf(String.valueOf(RoundAdj)));
//            //String RoundStr = validate_space(0, 21, "Round Adj".toUpperCase() + " $", "Qty");
//            line21spacecount = Query.GetLineSpaceCount(terminalTypeVal,21,0);
//            String RoundStr = validate_space(0, line21spacecount, "Round Adj".toUpperCase() + "  ", "Qty");
//            Integer line11spacecount = Query.GetLineSpaceCount(terminalTypeVal,11,0);
//            str += "\n" + RoundStr + validate_space(0, line11spacecount, roundStr, "Price");
//        }
//        String TotalStr = Query.ShowAmtMinusCorrectly(Double.valueOf(String.valueOf(Total)));
//        Integer line25spacecount = Query.GetLineSpaceCount(terminalTypeVal,25,0);
////        if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)){
////            line25spacecount = line25spacecount + 14;
////        }
//        strT = validate_space(0,line25spacecount,TotalStr,"Price");
//        //str += "\n" + "TOTAL $" + strT;
//        str += "\n" + "TOTAL  " + strT;
//        if (checkSalesRefundReprint) {
//            //Payment
//            str += "\n" + "PAYMENT" + "\n";
//        }
//
//        Cursor c = Query.GetBillPaymentAmountDetails(BillNo);
//        if (c != null) {
//            PaymentTypeNameArr.clear();
//            PaymentTypeAmountArr.clear();
//            while (c.moveToNext()) {
//                if (!c.isNull(0)) {
//
//                    if (c.getString(6) != null && c.getInt(6) == 1){
//
//                        if (c.getString(6) != null && c.getInt(6) != 0 && c.getString(6).length() > 0
//                                && c.getString(5) != null && c.getString(5).length() > 1) {
//
//                            PaymentTypeNameArr.add(c.getString(5));
//                            PaymentTypeNameArr.add("(" + c.getString(0) + ") ");
//                            Double val = c.getDouble(1) + c.getDouble(2);
//                            PaymentTypeAmountArr.add(String.valueOf(val));
//                            PaymentTypeAmountArr.add("");
//                        }else {
//                            PaymentTypeNameArr.add(c.getString(0));
//                            Double val = c.getDouble(1) + c.getDouble(2);
//                            PaymentTypeAmountArr.add(String.valueOf(val));
//                        }
//                    }else {
//                        PaymentTypeNameArr.add(c.getString(0));
//                        Double val = c.getDouble(1) + c.getDouble(2);
//                        PaymentTypeAmountArr.add(String.valueOf(val));
//                    }
//                }
//            }
//            c.close();
//        }
//
//
//
//        for (int i = 0 ; i < PaymentTypeNameArr.size() ; i++) {
//            String payment_amt_ = "0.0";
//            payment_amt_ = PaymentTypeAmountArr.get(i);
//            Integer line16spacecount = Query.GetLineSpaceCount(terminalTypeVal,16,0);
////                if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)){
////                    line16spacecount = line16spacecount + 5;
////                }
//            str_PaymentType = validate_space(0, line16spacecount, PaymentTypeNameArr.get(i).toUpperCase(), "");
//            //str_PaymentType = validate_space(0, 16, PaymentTypeNameArr.get(i).toUpperCase(), "");
//
//            if (checkSalesRefundReprint) {
//                str += str_PaymentType;
//            }
//
//            if (checkSalesRefundReprint) {
////                    if (Double.valueOf(Total) < 0.0) {
////                        payment_amt_ = (-1) * payment_amt_;
////                    }else {
////                        payment_amt_ = payment_amt_;
////                    }
//                payment_amt_ = payment_amt_;
//            }
//            if (payment_amt_ != null && payment_amt_.length() > 0) {
//                String _pay_amt = Query.ShowAmtMinusCorrectly(Double.valueOf(payment_amt_));
//                //String _pay_amt = String.format("%7.2f", payment_amt_).substring(0, 7);
//
//                line16spacecount = Query.GetLineSpaceCount(terminalTypeVal,16,0);
////                    if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)){
////                        line16spacecount = line16spacecount + 5;
////                    }
//                str_pay_amt = validate_space(0, line16spacecount, _pay_amt, "Price");
//                //str_pay_amt = validate_space(0, 16, _pay_amt, "Price");
//                if (checkSalesRefundReprint) {
//                    str += str_pay_amt;
//                    if (terminalTypeVal.equals(Constraints.INGENICO)){
//                        if (i < PaymentTypeNameArr.size() - 1) {
//                            str += "\n";
//                        }
//                    }
//                }
//            }else {
//                str += "";
//            }
//        }
//        Log.i("str___","str____"+str);
//        if (checkSalesRefundReprint) {
////            if (!(Change.equals("0") || Change.isEmpty() || Change == null)) {
//            if (Change != null && !(Change.isEmpty()) && !(Change.equals("0"))) {
//                if (Double.valueOf(Change) != 0.0) {
//                    String strC = "";
//                    //Change = String.valueOf(Double.valueOf(Change));
//                    String StrChange = Query.ShowAmtMinusCorrectly(Double.valueOf(String.valueOf(Change)));
//                    line25spacecount = Query.GetLineSpaceCount(terminalTypeVal,25,0);
//                    strC = validate_space(0, line25spacecount, StrChange, "Price");
//                    str += "\n" + "CHANGE " + strC;
//                }
//            }
//        }
//        str += "\n" + total_line;
//
//        //Mercatus
//        Mercatusfun(terminalTypeVal);
//
//        //Jeripay
//        JeripayFun(terminalTypeVal);
//
//        str += "\n" + total_line;
//        String closed_sales_str = "CLOSED SALES:" + ReceiptNoDateTime;
//        str += "\n" + closed_sales_str;
//
//        str +="\n" + total_line + "\n";
//        str +=  Footer + "\n"+ "\n"+ "\n"+ "\n"+ "\n";
//
//        Log.i("PrintingRECEIPT__","str+"+str);
//
//    }


//    @RequiresApi(api = Build.VERSION_CODES.N)
//    private static void printingReceiptFormatIMIN(Integer sale_id, String status, String BillNo,Context context) {
//
//        mIminPrintUtils = IminPrintUtils.getInstance(context);
////
//        mIminPrintUtils.initPrinter(IminPrintUtils.PrintConnectType.SPI);
//        //mIminPrintUtils.setAlignment(0);
////            mIminPrintUtils.setPageFormat(0);//80mm
//        mIminPrintUtils.setTextSize(22);
//        Log.i("printinatIMIN","ff__str_imn"+str_imn);
//        //printingReceiptFormatIMIN(sale_id, status, billNo);
////            mIminPrintUtils.setTextWidth(576);
////            mIminPrintUtils.setTextLineSpacing(1.5f);
//        //mIminPrintUtils.setAlignment(0);

//        Log.i("DSf___","ff__str_imn"+str_imn);
//        //str = "12345678901234567890123456789012345678901234567890";
//        //mIminPrintUtils.setLeftMargin(10);
//        mIminPrintUtils.setTextLineSpacing(1.0f);
//        //mIminPrintUtils.printText(str);
////            mIminPrintUtils.printColumnsText(new String[]{"1","iMin","iMin"} ,
////                            new int[]{2,1,1} ,
////                            new int[]{0,1,2} ,new int[]{22,22,22} );
//
//        //printColumnsText(String[] colTextArr, int[] colWidthArr, int[] colAlign, int[] size) {
////            mIminPrintUtils.printText("iMin committed to use advanced technologies to help our business partners digitize their business.We are dedicated in becoming a leading provider of smart business equipment " +
////                                            "in ASEAN countries,assisting our partners to connect, create and utilize data effectively.\n");
//
////             mIminPrintUtils.printText(str);
//        Log.i("dfdfdf__","dfdfdfdf"+str);
//       // mIminPrintUtils.printAndFeedPaper(10);
//
//        String terminalTypeVal = Query.GetDeviceData(Constraints.TERMINAL_TYPE);
//        str = "\n"+ Header;
//        String total_line = "";
//        for (int i = 0; i < 32; i++) {
//            total_line += "-";
//        }
//////
//
////        if (status.toUpperCase().equals(Constraints.VOID.toUpperCase())) {
////            str += "\n" + total_line;
////            str += "\n" + "Cancel Bill";
////        }
////        if (status.toUpperCase().equals(Constraints.CANCEL.toUpperCase())) {
////            str += "\n" + total_line;
////            str += "\n" + "Cancel Bill";
////        }
////        if (status.toUpperCase().equals(Constraints.REFUND.toUpperCase())) {
////            str += "\n" + total_line;
////            str += "\n" + "Refund Bill";
////        }
////        if (status.toUpperCase().equals(Constraints.Reprint.toUpperCase())) {
////            str += "\n" + total_line;
////            str += "\n" + "Reprint Bill";
////
////            String sq = "SELECT STATUS FROM SALES WHERE ID = "+sale_id;
////            Cursor csq = DBFunc.Query(sq,false);
////            if ( csq != null) {
////                if (csq.moveToNext()) {
////                    if (csq.getString(0).toUpperCase().equals(Constraints.VOID.toUpperCase())) {
////                        str += "\n" + total_line;
////                        str += "\n" + "Cancel Bill";
////                    }
////                    if (csq.getString(0).toUpperCase().equals(Constraints.CANCEL.toUpperCase())) {
////                        str += "\n" + total_line;
////                        str += "\n" + "Cancel Bill";
////                    }
////                }
////                csq.close();
////            }
////        }
//        str += "\n" + total_line;
//
//        String tblName = "0";
//        String queName = "0";
//        String orderStatus = "0";
//
//        Cursor C_DetailsBillProduct = Query.SearchBillProductByBillNo(BillNo, Constraints.NoGroupBy);
//
//        if (C_DetailsBillProduct != null) {
//            if (C_DetailsBillProduct.moveToNext()) {
//                tblName = C_DetailsBillProduct.getString(3);
//                queName = C_DetailsBillProduct.getString(4);
//                orderStatus = C_DetailsBillProduct.getString(5);
//            }
//            C_DetailsBillProduct.close();
//        }
//
////        if (!(queName == null ||  queName.equals("0") || queName.isEmpty())) {
////            if (!queName.equals("0 ,")) {
////
////            }
////            str += "\n" + "QueueNo #" + queName;
////        }
////        if (!(tblName == null || tblName.equals("0") || !tblName.equals("0,") || tblName.isEmpty() )) {
////            Log.i("__tblName","tblName____"+tblName);
////            str += "\n" + "TableNo #" + tblName;
////        }
////        if (orderStatus != null && orderStatus.toUpperCase().equals("HOLD".toUpperCase())) {
////            str += "\n" + "Order Status #" + orderStatus;
////        }
//
//        //str += "\n"+ "DATE #" + ReceiptNoDateTime;
//        //str += "\n"+ "RECEIPT NO #" + ReceiptNo;
//        //Delivery
//        String delivery_text = Query.GetSalesDelivery(ReceiptNo);
//        if (delivery_text.length() > 0) {
//            str += "\n\n"+Html.fromHtml(delivery_text);
//        }
//
//        String description_header =  "DESCRIPTION      QTY       PRICE";
//        str +="\n"+"\n"+ description_header;
//
//        total_line = "";
//        for (int i = 0; i < 32; i++) {
//            total_line += "-";
//        }
//        str += "\n" + total_line;
//
//        Double GrandtotalItemDis = 0.0;
//        for (int qty = 0 ; qty < sldQtyArr.size(); qty ++) {
//            //Qty
//            Qty = sldQtyArr.get(qty);
////            Qty = IDArr.get(qty);
//
//            Log.i("DFDFD___","sldQtyArr___"+sldQtyArr+"__Qty__"+Qty);
//            Integer count5 = 5;
////            if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)) {
////                count5 = 10;
////            }
//            strQty = validate_space(0,count5,Qty,"Qty");
//
//            //Name
//            Name = sldNameArr.get(qty);
//            Log.i("DF____","Name___"+Name);
//
//            //strName = GetFormatForDescription(Name);
//            strNameOrigin = GetFormatForDescription(Name,terminalTypeVal);
//
//            Double realTotAmt = 0.0;
//            String _StrName = "";
//            String strItemDisAmt = "";
//            //Double disamt = Double.valueOf(sltItemDisArr.get(qty));
//            Double disamt = Double.valueOf(Qty) * Double.valueOf(sltItemDisArr.get(qty));
//            //if (disamt > 0.0) {
//
//            if (disamt != 0.0) {
//                String StrName = "";
//                if (sldDiscountType.get(qty).equals("% Percentage")){
//                    if (terminalTypeVal.toUpperCase().equals(Constraints.INGENICO.toUpperCase())){
//                        StrName = sldDiscountName.get(qty) + "(" + sldDiscountValue.get(qty) + "%%" + ") ";
//                    }else {
//                        StrName = sldDiscountName.get(qty) + "(" + sldDiscountValue.get(qty) + "%" + ") ";
//                    }
//                }
//                if (sldDiscountType.get(qty).equals("$ Dollar Value")){
//                    StrName =  sldDiscountName.get(qty) +  "($" + sldDiscountValue.get(qty) + "" + ") ";
//                }
//                Integer count21 = 21;
//                if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)){
//                    count21 = 31;
//                }
//                _StrName = validate_space(0,count21,StrName,"Qty");;
//
//                Integer count_11 = 11;
//                if (terminalTypeVal.toUpperCase().equals(Constraints.INGENICO.toUpperCase())){
//                    count_11 = 12;
//                }else  if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN.toUpperCase())){
//                    count_11 = 15;
//                }
//                strItemDisAmt = validate_space(0, count_11, "-" + String.format("%.2f", disamt), "Price");
//                GrandtotalItemDis += Double.valueOf(disamt);
//
//                realTotAmt = Double.valueOf(sltPriceTotalArr.get(qty)) - Double.valueOf(disamt);
//            }else {
//                realTotAmt = Double.valueOf(sltPriceTotalArr.get(qty));
//            }
//
//            //Price
//            //Price = String.format("%.2f", realTotAmt);
////            Price = String.format("%.2f", Double.valueOf(sltPriceTotalArr.get(qty)));
//            Price = String.format("%.2f", Double.valueOf(Qty) * Double.valueOf(sltPriceTotalArr.get(qty)));
//            Integer count9 = 9;
//            if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)) {
//                count9 = 14;
//            }
//            strPrice = validate_space(0,count9,Price,"Price");
//            String nqp = "";
//
//            //nqp = strNameOrigin + "x"+strQty + strPrice;
//            // nqp = strNameOrigin + "x"+Qty + Price;
//            nqp = strNameOrigin + "x"+strQty + strPrice;
////            mIminPrintUtils.printColumnsText(new String[]{Name,"x"+Qty,Price} ,
////                            new int[]{2,1,1} ,
////                            new int[]{0,1,2} ,new int[]{22,22,22} );
////            mIminPrintUtils.printColumnsText(new String[]{_StrName,"",strItemDisAmt} ,
////                            new int[]{2,1,1} ,
////                            new int[]{0,1,2} ,new int[]{22,22,22} );
//
//            Query.PrintingValueSetForIMIN(mIminPrintUtils,Name,"x",Price,new int[]{2,1,1},
//                    new int[]{0,1,2},new int[]{22,22,22});
//
//            Query.PrintingValueSetForIMIN(mIminPrintUtils,_StrName,"",strItemDisAmt,new int[]{2,1,1},
//                    new int[]{0,1,2},new int[]{22,22,22});
//
//            str += "\n" + nqp;
//            if (disamt !=  0.0) {
//                str += "\n" + _StrName + strItemDisAmt;
//            }
//            //Show PromoName And Value (MixAndMatch)
//            //GetPromo
//            str += GetShowPromoDetails(terminalTypeVal,qty);
////           str += GetShowPromoDetails(Integer.parseInt(String.valueOf(Qty)));
//
//            //GetPLUModi
//            //str += GetShowPLUModiDetails(BillNo,String.valueOf(IDArr.get(qty)),String.valueOf(sldOpenPriceStatusArr.get(qty)));
//            str += GetShowPLUModiDetails(BillNo,String.valueOf(IDArr.get(qty)),String.valueOf(sldOpenPriceStatusArr.get(qty)),Qty);
//
////           GetShowPLUModiDetails(BillNo,String.valueOf(Qty));
//        }
//
////        //strST = validate_space(0,21,String.format("%.2f", Double.valueOf(SubTotal)),"Price");
////
////
////        //SalesData(sale_id);
////
////        //String StrSubtotal = Query.ShowAmtMinusCorrectly(Double.valueOf(Subtotal));
////        if (sale_id == -5){
////            SubTotal = String.valueOf(CheckOutActivity.sub_total);
////        }
////
////        String StrSubtotal = Query.ShowAmtMinusCorrectly(Double.valueOf(SubTotal));
////        Integer c_count21 = 21;
////         if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)){
////             c_count21 = 42;
////         }
////        strST = validate_space(0,c_count21,StrSubtotal,"Price");
////
////        String str_item = "ITEM";
////        if (sale_id == -5){
////            TotalItems = "0";
////        }
////
////        if (TotalItems.length() > 0){
////            str_item = "ITEMS";
////        }
////        str += "\n" + total_line;
////        str += "\n" + "TOTAL  (" + TotalItems +") "+ str_item +".";
////        str += "\n" + total_line;
////        //str += "\n" + "SUB-TOTAL" +" $" + strST;
////        str += "\n" + "SUB-TOTAL  " + strST;
////        //str = "\n" + "SUB-TOTAL  " + strST;
////
////        if (Double.valueOf(TotalBillDisount) > 0.0) {
////            if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)) {
////                strTotalBillDisount = validate_space(0, 42, "-$" + String.format("%.2f", Double.valueOf(TotalBillDisount)), "Price");
////            }else {
////                //strTotalBillDisount = validate_space(0, 18, "-" + String.format("%.2f", Double.valueOf(TotalBillDisount)), "Price");
////                strTotalBillDisount = validate_space(0, 17, "-$" + String.format("%.2f", Double.valueOf(TotalBillDisount)), "Price");
////            }
////            //str += "\n" + "BillDiscount $" +  strTotalBillDisount;
////            str += "\n" + "BillDiscount  " +  strTotalBillDisount;
////            if (billDiscountTypeNameSales.equals("% Percentage")) {
////                if (billDiscountValueSales < 0.0){
////                    billDiscountValueSales = (-1) * billDiscountValueSales;
////                    if (terminalTypeVal.toUpperCase().equals(Constraints.INGENICO.toUpperCase())){
////                        str += "\n" + DiscountNameSales + "(-" + billDiscountValueSales + "%%)";
////                    }else {
////                        str += "\n" + DiscountNameSales + "(-" + billDiscountValueSales + "%)";
////                    }
////                }else {
////                    if (terminalTypeVal.toUpperCase().equals(Constraints.INGENICO.toUpperCase())){
////                        str += "\n" + DiscountNameSales + "(" + billDiscountValueSales + "%%)";
////                    }else {
////                        str += "\n" + DiscountNameSales + "(" + billDiscountValueSales + "%)";
////                    }
////                }
////            }else {
////                if (billDiscountValueSales < 0.0){
////                    billDiscountValueSales = (-1) * billDiscountValueSales;
////                    str += "\n" + DiscountNameSales + "(-$" + billDiscountValueSales + ")";
////                }else {
////                    str += "\n" + DiscountNameSales + "($" + billDiscountValueSales + ")";
////                }
////            }
////        }
////
////        String totQtyaa = Query.GetPromotionPriceByBillNo(BillNo,"0");
////
////        Boolean checkSalesRefundReprint = false;
////        if (status.toUpperCase().equals(Constraints.SALES.toUpperCase()) || status.toUpperCase().equals(Constraints.REFUND.toUpperCase()) || status.toUpperCase().equals(Constraints.Reprint.toUpperCase())) {
////            checkSalesRefundReprint = true;
////        }else {
////            checkSalesRefundReprint = false;
////        }
////
////        //if (Double.valueOf(totQtyaa) > 0.0) {
////        if (Double.valueOf(totQtyaa) != 0.0) {
////            String StrtotQtyaa = Query.ShowAmtMinusCorrectly(Double.valueOf(String.valueOf(totQtyaa)));
////            String strPromotion = validate_space(0, 20, StrtotQtyaa, "Price");
////            str += "\n" + "Promotion" +  strPromotion;
////        }
////
////        Integer chk_serviceCharges = Query.CheckServiceCharges();
////        if (chk_serviceCharges == 1) {
////            String str_service_charges =  Query.GetServiceChargesNameAndPercentage();
////            if (ServiceCharges != null && ServiceCharges.length() > 0 && Double.valueOf(ServiceCharges) != 0.0) {
////                String str_ServiceChargesValue = Query.ShowAmtMinusCorrectly(Double.valueOf(String.valueOf(ServiceCharges)));
////                //String str_ServiceChargesName = validate_space(0, 20, str_service_charges.toUpperCase() + " $", "Qty");
////                String str_ServiceChargesName = validate_space(0, 20, str_service_charges.toUpperCase() + "  ", "Qty");
////                //str += "\n" + str_ServiceChargesName + validate_space(0, 11, str_ServiceChargesValue, "Price");
////                if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)) {
////                    Integer c_count12 = 12;
////                    if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)){
////                        c_count12 = 38;
////                    }
////                    str += "\n" + str_ServiceChargesName + validate_space(0, c_count12, str_ServiceChargesValue, "Price");
////                }else {
////                    Integer c_count11 = 11;
////                    if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)){
////                        c_count11 = 39;
////                    }
////                    str += "\n" + str_ServiceChargesName + validate_space(0, c_count11, str_ServiceChargesValue, "Price");
////                }
////            }
////        }
////        Cursor Cursor_tax = Query.GetTax();
////        if (Cursor_tax != null){
////            if (Cursor_tax.moveToNext()){
////                String taxRate = Cursor_tax.getString(0);
////                Integer taxType = Cursor_tax.getInt(1);
////                String taxName = Cursor_tax.getString(2);
////
////                String percentageSign = "";
////                if (terminalTypeVal.toUpperCase().equals(Constraints.INGENICO.toUpperCase())){
////                    percentageSign = "%%";
////
////                    if (taxName.contains("%")){
////                        Log.i("TAG","taxName_"+taxName);
////                        String first = taxName.split("%")[0];
////                        String second = "";
////                        try {
////
////                            second = taxName.split("%")[1];
////                        }catch (ArrayIndexOutOfBoundsException e){
////                            Log.i("TAG","error_"+e.getMessage());
////                        }
////                        String str = first + percentageSign + second;
////                        taxName = str;
////                    }
////                }else {
////                    percentageSign = "%";
////                }
////
////                if (taxType == 2){
////                    double amt_inclusive = Query.calculateInclusive(Double.valueOf(Total),Integer.parseInt(taxRate));
////                    if (amt_inclusive != 0.0) {
////                        String str_amt_inclusive = Query.ShowAmtMinusCorrectly(Double.valueOf(String.valueOf(amt_inclusive)));
////                        Log.i("TAXNAMEINCLUSIVE_","TAX__"+taxName.toUpperCase());
////                        String str_inclusive = taxName.toUpperCase() + "(" + taxRate + percentageSign +")" + " SGD " + str_amt_inclusive + " Incl";
////                        str += "\n" + str_inclusive;
////                    }
////                }else if (taxType == 3){
////                    if (TotalTaxes != 0.0) {
////                        String str_exclusive = Query.ShowAmtMinusCorrectly(Double.valueOf(String.valueOf(TotalTaxes)));
////                        Log.i("TAXNAMEe_","TAX__"+taxName.toUpperCase());
////                        String TaxExcul = validate_space(0, 21, taxName.toUpperCase() + "(" + taxRate + percentageSign +")" + "  ", "Qty");
////                        Integer c_count11 = 11;
////                        if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)){
////                            c_count11 = 34;
////                        }
////                        str += "\n" + TaxExcul + validate_space(0, c_count11, str_exclusive, "Price");
////                    }
////                }
////            }
////            Cursor_tax.close();
////        }
////
////        if (Double.valueOf(RoundAdj) != 0.0) {
////            String roundStr = Query.ShowAmtMinusCorrectly(Double.valueOf(String.valueOf(RoundAdj)));
////            //String RoundStr = validate_space(0, 21, "Round Adj".toUpperCase() + " $", "Qty");
////            String RoundStr = validate_space(0, 21, "Round Adj".toUpperCase() + "  ", "Qty");
////             Integer c_count11 = 11;
////             if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)){
////                 c_count11 = 31;
////             }
////            str += "\n" + RoundStr + validate_space(0, c_count11, roundStr, "Price");
////         }
////        String TotalStr = Query.ShowAmtMinusCorrectly(Double.valueOf(String.valueOf(Total)));
////         Integer c_count25 = 25;
////         if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)){
////             c_count25 = 50;
////         }
////        strT = validate_space(0,c_count25,TotalStr,"Price");
////        //str += "\n" + "TOTAL $" + strT;
////        str += "\n" + "TOTAL  " + strT;
////        Log.i("Dfdf___","str__"+str);
////        if (checkSalesRefundReprint) {
////            //Payment
////            str += "\n" + "PAYMENT" + "\n";
////        }
////
////        Cursor c = Query.GetBillPaymentAmountDetails(BillNo);
////        if (c != null) {
////            PaymentTypeNameArr.clear();
////            PaymentTypeAmountArr.clear();
////            while (c.moveToNext()) {
////                if (!c.isNull(0)) {
////                    Log.i("DF__","_____ffffffff"+c.getString(6)+"\n"+
////                            "____ffffffff_"+c.getString(5));
////                    if (c.getString(6) != null && c.getInt(6) == 1){
////                        Log.i("DF__ffffffff","____dffffffff_"+c.getString(6)+"\n"+
////                                "____dffffffff_"+c.getString(5));
////                        if (c.getString(6) != null && c.getInt(6) != 0 && c.getString(6).length() > 0
////                                && c.getString(5) != null && c.getString(5).length() > 1) {
////
////                            Log.i("DF__","____drffffffff_"+c.getString(6)+"\n"+
////                                    "____drffffffff_"+c.getString(5));
////                            PaymentTypeNameArr.add(c.getString(5));
////                            PaymentTypeNameArr.add("(" + c.getString(0) + ") ");
////                            Double val = c.getDouble(1) + c.getDouble(2);
////                            PaymentTypeAmountArr.add(String.valueOf(val));
////                            PaymentTypeAmountArr.add("");
////                        }else {
////                            PaymentTypeNameArr.add(c.getString(0));
////                            Double val = c.getDouble(1) + c.getDouble(2);
////                            PaymentTypeAmountArr.add(String.valueOf(val));
////                        }
////                    }else {
////                        PaymentTypeNameArr.add(c.getString(0));
////                        Double val = c.getDouble(1) + c.getDouble(2);
////                        PaymentTypeAmountArr.add(String.valueOf(val));
////                    }
////                }
////            }
////            c.close();
////        }
////
////
////
////        for (int i = 0 ; i < PaymentTypeNameArr.size() ; i++) {
////                String payment_amt_ = "0.0";
////                payment_amt_ = PaymentTypeAmountArr.get(i);
////                Integer c_count16 = 16;
////                if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)){
////                    c_count16 = 31;
////                }
////
////                str_PaymentType = validate_space(0, c_count16 , PaymentTypeNameArr.get(i).toUpperCase(), "");
////
////                if (checkSalesRefundReprint) {
////                    str += str_PaymentType;
////                }
////
////                if (checkSalesRefundReprint) {
//////                    if (Double.valueOf(Total) < 0.0) {
//////                        payment_amt_ = (-1) * payment_amt_;
//////                    }else {
//////                        payment_amt_ = payment_amt_;
//////                    }
////                    payment_amt_ = payment_amt_;
////                }
////                if (payment_amt_ != null && payment_amt_.length() > 0) {
////                    String _pay_amt = Query.ShowAmtMinusCorrectly(Double.valueOf(payment_amt_));
////                    //String _pay_amt = String.format("%7.2f", payment_amt_).substring(0, 7);
////
////                    c_count16 = 16;
////                    if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)){
////                        c_count16 = 27;
////                    }
////                    str_pay_amt = validate_space(0, c_count16, _pay_amt, "Price");
////                    if (checkSalesRefundReprint) {
////                        str += str_pay_amt;
////                        if (terminalTypeVal.equals(Constraints.INGENICO)){
////                            if (i < PaymentTypeNameArr.size() - 1) {
////                                str += "\n";
////                            }
////                        }
////                    }
////                }else {
////                    str += "";
////                }
////        }
////        Log.i("str___","str____"+str);
////        if (checkSalesRefundReprint) {
//////            if (!(Change.equals("0") || Change.isEmpty() || Change == null)) {
////            if (Change != null && !(Change.isEmpty()) && !(Change.equals("0"))) {
////                if (Double.valueOf(Change) != 0.0) {
////                    String strC = "";
////                    //Change = String.valueOf(Double.valueOf(Change));
////                    String StrChange = Query.ShowAmtMinusCorrectly(Double.valueOf(String.valueOf(Change)));
////                    c_count25 = 25;
////                    if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)){
////                        c_count25 = 47;
////                    }
////                    strC = validate_space(0, c_count25, StrChange, "Price");
////                    str += "\n" + "CHANGE " + strC;
////                }
////            }
////        }
////        str += "\n" + total_line;
//
////        //Mercatus
////       Mercatusfun(terminalTypeVal);
////
////       //Jeripay
////        JeripayFun(terminalTypeVal);
//
//        //str += "\n" + total_line;
//        String closed_sales_str = "CLOSED SALES:" + ReceiptNoDateTime;
//        str += "\n" + closed_sales_str;
//
//        str +="\n" + total_line + "\n";
//        str +=  Footer + "\n"+ "\n"+ "\n"+ "\n"+ "\n";
//
//        Log.i("PrintingRECEIPT__","str+"+str);
//
//    }

     @RequiresApi(api = Build.VERSION_CODES.N)
    public static ReceiptData printingReceiptFormat(Integer sale_id, String status,
                                              String BillNo, IminPrintUtils mIminPrintUtils) {
         ReceiptData jsonObjectPrint = new ReceiptData();

        try {

        String terminalTypeVal = Query.GetDeviceData(Constraints.TERMINAL_TYPE);
        str = "\n"+ Header;

        jsonObjectPrint.setReceiptHeader(Header);

        Query.PrintingValueSetForIMIN(mIminPrintUtils,Header,"","",new int[]{2,0,0},
                new int[]{0,1,2},new int[]{22,22,22});
        String total_line = "";
        for (int i = 0; i < 32; i++) {
            total_line += "-";
        }

        if (status.toUpperCase().equals(Constraints.VOID.toUpperCase())) {
            str += "\n" + total_line;

            str += "\n" + "Cancel Bill";

            jsonObjectPrint.setTotalLine(total_line);
            jsonObjectPrint.setStatusVoidBill("Cancel Bill");

            Query.PrintingValueSetForIMINReport(mIminPrintUtils,Constraints.LINEIMIN, Constraints.HEADER);

            Query.PrintingValueSetForIMIN(mIminPrintUtils,"Cancel Bill","","",new int[]{2,0,0},
                    new int[]{0,1,2},new int[]{22,22,22});

        }

        if (status.toUpperCase().equals(Constraints.CANCEL.toUpperCase())) {
            str += "\n" + total_line;
            str += "\n" + "Cancel Bill";

            jsonObjectPrint.setTotalLine(total_line);
            jsonObjectPrint.setStatusCancelBill("Cancel Bill");

            Query.PrintingValueSetForIMINReport(mIminPrintUtils,Constraints.LINEIMIN, Constraints.HEADER);

            Query.PrintingValueSetForIMIN(mIminPrintUtils,"Cancel Bill","","",new int[]{2,0,0},
                    new int[]{0,1,2},new int[]{22,22,22});
        }


        if (status.toUpperCase().equals(Constraints.REFUND.toUpperCase())) {
            str += "\n" + total_line;
            str += "\n" + "Refund Bill";

            jsonObjectPrint.setTotalLine(total_line);
            jsonObjectPrint.setStatusRefundBill("Refund Bill");

            Query.PrintingValueSetForIMINReport(mIminPrintUtils,Constraints.LINEIMIN, Constraints.HEADER);

            Query.PrintingValueSetForIMIN(mIminPrintUtils,"Refund Bill","","",new int[]{2,0,0},
                    new int[]{0,1,2},new int[]{22,22,22});
        }


        if (status.toUpperCase().equals(Constraints.Reprint.toUpperCase())) {
            str += "\n" + total_line;
            str += "\n" + "Reprint Bill";

            jsonObjectPrint.setTotalLine(total_line);
            jsonObjectPrint.setStatusReprintBill("Reprint Bill");

            Query.PrintingValueSetForIMINReport(mIminPrintUtils,Constraints.LINEIMIN, Constraints.HEADER);
            Query.PrintingValueSetForIMIN(mIminPrintUtils,"Reprint Bill","","",new int[]{2,0,0},
                    new int[]{0,1,2},new int[]{22,22,22});

            String sq = "SELECT STATUS FROM SALES WHERE ID = "+sale_id;
            Cursor csq = DBFunc.Query(sq,false);
            if ( csq != null) {
                if (csq.moveToNext()) {
                    if (csq.getString(0).toUpperCase().equals(Constraints.VOID.toUpperCase())) {
                        str += "\n" + total_line;

                        str += "\n" + "Cancel Bill";

                        jsonObjectPrint.setTotalLine(total_line);
                        jsonObjectPrint.setStatusVoidBill("Cancel Bill");

                        Query.PrintingValueSetForIMINReport(mIminPrintUtils,Constraints.LINEIMIN, Constraints.HEADER);

                        Query.PrintingValueSetForIMIN(mIminPrintUtils,"Cancel Bill","","",new int[]{2,0,0},
                                new int[]{0,1,2},new int[]{22,22,22});

                    }
                    if (csq.getString(0).toUpperCase().equals(Constraints.CANCEL.toUpperCase())) {
                        str += "\n" + total_line;

                        Query.PrintingValueSetForIMINReport(mIminPrintUtils,Constraints.LINEIMIN, Constraints.HEADER);

                        str += "\n" + "Cancel Bill";

                        jsonObjectPrint.setTotalLine(total_line);
                        jsonObjectPrint.setStatusCancelBill("Cancel Bill");

                        Query.PrintingValueSetForIMIN(mIminPrintUtils,"Cancel Bill","","",new int[]{2,0,0},
                                new int[]{0,1,2},new int[]{22,22,22});
                    }
                }
                csq.close();
            }
        }
        str += "\n" + total_line;
        Log.i("Dfdf___","Dfdfd______"+"dsffds_one");
        jsonObjectPrint.setTotalLine(total_line);

        Query.PrintingValueSetForIMINReport(mIminPrintUtils,Constraints.LINEIMIN, Constraints.HEADER);
        String tblName = "0";
        String queName = "0";
        String orderStatus = "0";

        Cursor C_DetailsBillProduct = Query.SearchBillProductByBillNo(BillNo, Constraints.NoGroupBy);

        if (C_DetailsBillProduct != null) {
            if (C_DetailsBillProduct.moveToNext()) {
                tblName = C_DetailsBillProduct.getString(3);
                queName = C_DetailsBillProduct.getString(4);
                orderStatus = C_DetailsBillProduct.getString(5);
            }
            C_DetailsBillProduct.close();
        }

            Log.i("Dfdf___","Dfdfd______"+"dsffds_two");
        if (!(queName == null ||  queName.equals("0") || queName.isEmpty())) {
            if (!(queName.equals("0 ,")) || !(queName.equals("0,")) || !(queName.equals("0, "))|| !(queName.equals(" 0, "))|| !(queName.equals(" 0,"))) {

                if (queName.trim().equals("0,")) {

                    if (queName.length() == 4) {

                        str += "\n" + "QueueNo #" + queName;

                        jsonObjectPrint.setQueueNo("QueueNo #" + queName);

                        Query.PrintingValueSetForIMIN(mIminPrintUtils, "QueueNo #" + queName,"","",new int[]{2,0,0},
                                new int[]{0,1,2},new int[]{22,22,22});
                    }
                } else {

                    str += "\n" + "QueueNo #" + queName;

                    jsonObjectPrint.setQueueNo("QueueNo #" + queName);

                    Query.PrintingValueSetForIMIN(mIminPrintUtils, "QueueNo #" + queName,"","",new int[]{2,0,0},
                            new int[]{0,1,2},new int[]{22,22,22});
                }
            }


//            Query.PrintingValueSetForIMIN(mIminPrintUtils, "QueueNo #" + queName,"","",new int[]{2,0,0},
//                    new int[]{0,1,2},new int[]{22,22,22});
        }
        if (!(tblName == null || tblName.equals("0") || !tblName.equals("0,") || !tblName.equals("0 ,") || tblName.isEmpty() )) {

            str += "\n" + "TableNo #" + tblName;

            jsonObjectPrint.setTableNo("TableNo #" + tblName);

            Query.PrintingValueSetForIMIN(mIminPrintUtils, "TableNo #" + tblName,"","",new int[]{2,0,0},
                    new int[]{0,1,2},new int[]{22,22,22});
        }
        if (orderStatus != null && orderStatus.toUpperCase().equals("HOLD".toUpperCase())) {
            str += "\n" + "Order Status #" + orderStatus;

            jsonObjectPrint.setOrderStatus("Order Status #" + orderStatus);

            Query.PrintingValueSetForIMIN(mIminPrintUtils,  "Order Status #" + orderStatus,"","",new int[]{2,0,0},
                    new int[]{0,1,2},new int[]{22,22,22});
        }

        str += "\n"+ "DATE #" + ReceiptNoDateTime;
        str += "\n"+ "RECEIPT NO #" + ReceiptNo;

        jsonObjectPrint.setReceiptNoDateTime("DATE #" + ReceiptNoDateTime);
        jsonObjectPrint.setReceiptNo("RECEIPT NO #" + ReceiptNo);

         String sql111 = "SELECT card_label,card_number,invoice_number,BillNo FROM BillJeripayDetails " +
                 " WHERE BillNo = '"+BillNo+"' Group By BillNo,card_label";
         Cursor jeripayDetails = DBFunc.Query(sql111,false);
         if (jeripayDetails != null) {
             if (jeripayDetails.moveToNext()) {
                 String invoice_number = jeripayDetails.getString(2);
                 if (invoice_number!= null && invoice_number.length() > 1) {
                     str +=  "\n"+ "Invoice No #" + invoice_number;

                     jsonObjectPrint.setInvoiceNo("Invoice No #" + invoice_number);

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
                 if (invoice_number!= null && invoice_number.length() > 1) {
                     str +=  "\n"+ "Invoice No #" + invoice_number;

                     jsonObjectPrint.setInvoiceNo("Invoice No #" + invoice_number);
                 }
             }
             csql1BillBOC.close();
         }
        Query.PrintingValueSetForIMIN(mIminPrintUtils, "DATE #" + ReceiptNoDateTime,"","",new int[]{2,0,0},
                new int[]{0,1,2},new int[]{22,22,22});

        Query.PrintingValueSetForIMIN(mIminPrintUtils,"RECEIPT NO #" + ReceiptNo,"","",new int[]{2,0,0},
                new int[]{0,1,2},new int[]{22,22,22});
        //Delivery
        String delivery_text = Query.GetSalesDelivery(ReceiptNo);
        if (delivery_text.length() > 0) {
            str += "\n\n"+Html.fromHtml(delivery_text);
            jsonObjectPrint.setDeliveryText((Html.fromHtml(delivery_text)).toString());
        }

        String description_header =  "DESCRIPTION      QTY       PRICE";
        str +="\n"+"\n"+ description_header;

//        jsonObjectPrint.set("DESCRIPTION","DESCRIPTION");
//        jsonObjectPrint.put("QTY","QTY");
//        jsonObjectPrint.put("PRICE","PRICE");

         Query.PrintingValueSetForIMIN(mIminPrintUtils,"","","",new int[]{2,0,0},
                 new int[]{0,1,2},new int[]{22,22,22});
         Query.PrintingValueSetForIMIN(mIminPrintUtils,"DESCRIPTION","QTY","PRICE",new int[]{2,1,1},
                 new int[]{0,1,2},new int[]{22,22,22});

        total_line = "";
        for (int i = 0; i < 32; i++) {
            total_line += "-";
        }
        str += "\n" + total_line;
//         Query.PrintingValueSetForIMIN(CashLayoutActivity.mIminPrintUtils,total_line,"","",new int[]{2,0,0},
//                 new int[]{0,1,2},new int[]{22,22,22});
        Query.PrintingValueSetForIMINReport(mIminPrintUtils,Constraints.LINEIMIN, Constraints.HEADER);


        jsonObjectPrint.setTotalLine(total_line);

        ArrayList<OrderDetails> jsonArray = new ArrayList<OrderDetails>();


        Double GrandtotalItemDis = 0.0;
        for (int qty = 0 ; qty < sldQtyArr.size(); qty ++) {

            OrderDetails orderDetailsObj = new OrderDetails();
            //Qty
            Qty = sldQtyArr.get(qty);
//            Qty = IDArr.get(qty);

            Integer count5 = 5;
//            if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)) {
//                count5 = 10;
//            }
            strQty = validate_space(0,count5,Qty,"Qty");

            //Name
            Name = sldNameArr.get(qty);
            Remarks = sldRemarksArr.get(qty);

            //strName = GetFormatForDescription(Name);
//            strNameOrigin = GetFormatForDescription(Name,terminalTypeVal);

            if (Remarks != null && Remarks.trim().length() > 0) {
                //if (!(Remarks.equals("0"))) {
                if (Remarks != null && Remarks.trim().length() > 0 && !(Remarks.equals(null)) && !(Remarks.equals("null"))){
                    strNameOrigin = Name + "\n" + "(" + Remarks + ")";
                } else {
                        strNameOrigin = Name;
                }
            } else {
                strNameOrigin = Name;
            }

            Double realTotAmt = 0.0;
            String _StrName = "";
            String strItemDisAmt = "";
            //Double disamt = Double.valueOf(sltItemDisArr.get(qty));
            Double disamt = Double.valueOf(Qty) * Double.valueOf(sltItemDisArr.get(qty));
            //if (disamt > 0.0) {

            String StrName = "";
            if (disamt != 0.0) {
                if (sldDiscountType.get(qty).equals("% Percentage")){
                    if (terminalTypeVal.toUpperCase().equals(Constraints.INGENICO.toUpperCase())){
                        StrName = sldDiscountName.get(qty) + "(" + sldDiscountValue.get(qty) + "%%" + ") ";
                    }else {
                        StrName = sldDiscountName.get(qty) + "(" + sldDiscountValue.get(qty) + "%" + ") ";
                    }
                }
                if (sldDiscountType.get(qty).equals("$ Dollar Value")){
                    StrName =  sldDiscountName.get(qty) +  "($" + sldDiscountValue.get(qty) + "" + ") ";
                }
                Integer count21 = 21;
                if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)){
                    count21 = 31;
                }
                _StrName = validate_space(0,count21,StrName,"Qty");;

                Integer count_11 = 11;
                if (terminalTypeVal.toUpperCase().equals(Constraints.INGENICO.toUpperCase())){
                    count_11 = 12;
                }else  if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN.toUpperCase())){
                    count_11 = 15;
                }
                strItemDisAmt = validate_space(0, count_11, "-" + String.format("%.2f", disamt), "Price");
                GrandtotalItemDis += Double.valueOf(disamt);

                realTotAmt = Double.valueOf(sltPriceTotalArr.get(qty)) - Double.valueOf(disamt);
            }else {
                realTotAmt = Double.valueOf(sltPriceTotalArr.get(qty));
            }

            //Price
            //Price = String.format("%.2f", realTotAmt);
//            Price = String.format("%.2f", Double.valueOf(sltPriceTotalArr.get(qty)));
            //Price = String.format("%.2f", Double.valueOf(Qty) * Double.valueOf(sltPriceTotalArr.get(qty)));
            Price = String.format("%.2f",Double.valueOf(sltPriceTotalArr.get(qty)));
            Integer count9 = 9;
            if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)) {
                count9 = 14;
            }
            strPrice = validate_space(0,count9,Price,"Price");
            String nqp = "";

            //nqp = strNameOrigin + "x"+strQty + strPrice;
            // nqp = strNameOrigin + "x"+Qty + Price;
//            nqp = strNameOrigin + "x"+strQty + strPrice;
            String strvalName = "";
            String spe = "";
            Integer count17 = 17;
            if(terminalTypeVal.toUpperCase().equals(Constraints.IMIN)){
                count17 = 22;
            }
            for (int i = 0 ; i < count17 ; i++) {
                spe += " ";
            }
            nqp = strNameOrigin + "\n" + spe + "x"+strQty + strPrice;
            str += "\n" + nqp;

            orderDetailsObj.setOderDetailDisName(strNameOrigin);
            orderDetailsObj.setOderDetailsQty("x"+strQty);
            orderDetailsObj.setOderDetailPrice(strPrice);

            if (disamt !=  0.0) {
                str += "\n" + _StrName + strItemDisAmt;
                orderDetailsObj.setOderDetailDisName(_StrName);
                orderDetailsObj.setOderDetailDisPrice(strItemDisAmt);
            }


//            Query.PrintingValueSetForIMIN(mIminPrintUtils,Name,"x"+Qty,Price,new int[]{2,1,1},
            Query.PrintingValueSetForIMIN(mIminPrintUtils,strNameOrigin,"x"+Qty,Price,new int[]{2,1,1},
                    new int[]{0,1,2},new int[]{22,22,22});
            if (disamt !=  0.0) {
                Query.PrintingValueSetForIMIN(mIminPrintUtils,StrName,"","-" + String.format("%.2f", disamt),new int[]{2,1,1},
                        new int[]{0,1,2},new int[]{22,22,22});
            }
            //Show PromoName And Value (MixAndMatch)
            //GetPromo
            str += GetShowPromoDetails(terminalTypeVal,qty,mIminPrintUtils);
//           str += GetShowPromoDetails(Integer.parseInt(String.valueOf(Qty)));

            //GetPLUModi
            //str += GetShowPLUModiDetails(BillNo,String.valueOf(IDArr.get(qty)),String.valueOf(sldOpenPriceStatusArr.get(qty)));
            str += GetShowPLUModiDetails(terminalTypeVal,mIminPrintUtils,BillNo,String.valueOf(IDArr.get(qty)),
                    String.valueOf(sldOpenPriceStatusArr.get(qty)),String.valueOf(sldRemarksArr.get(qty)),Qty,detailsBillPID.get(qty));

//           GetShowPLUModiDetails(BillNo,String.valueOf(Qty));

            jsonArray.add(orderDetailsObj);
        }

        jsonObjectPrint.setOrderDetails(jsonArray);


        //strST = validate_space(0,21,String.format("%.2f", Double.valueOf(SubTotal)),"Price");


        //SalesData(sale_id);

        //String StrSubtotal = Query.ShowAmtMinusCorrectly(Double.valueOf(Subtotal));
        if (sale_id == -5){
            SubTotal = String.valueOf(CheckOutActivity.sub_total);
        }

        String StrSubtotal = Query.ShowAmtMinusCorrectly(Double.valueOf(SubTotal));
        Integer c_count21 = 21;
        if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)){
            c_count21 = 42;
        }
        strST = validate_space(0,c_count21,StrSubtotal,"Price");

        String str_item = "ITEM";
        if (sale_id == -5){
            TotalItems = "0";
        }

        if (TotalItems.length() > 0){
            str_item = "ITEMS";
        }


        str += "\n" + total_line;
        Query.PrintingValueSetForIMINReport(mIminPrintUtils,Constraints.LINEIMIN, Constraints.HEADER);

        String totalStringValue = "TOTAL  (" + TotalItems +") "+ str_item +".";
        str += "\n" + totalStringValue;

            jsonObjectPrint.setTotalItems("TOTAL  (" + TotalItems +") "+ str_item +".");

            Log.i("Dfdf___","Dfdfd______"+"dsffds_three");
        //str += "\n" + "TOTAL  (" + TotalItems +") "+ str_item +".";

        Query.PrintingValueSetForIMIN(mIminPrintUtils,totalStringValue,"","",new int[]{2,0,0},
                new int[]{0,1,2},new int[]{22,22,22});

        str += "\n" + total_line;

        jsonObjectPrint.setTotalLine(total_line);

        Query.PrintingValueSetForIMINReport(mIminPrintUtils,Constraints.LINEIMIN, Constraints.HEADER);
        //str += "\n" + "SUB-TOTAL" +" $" + strST;
        //str += "\n" + "SUB-TOTAL  " + strST;
        String subtotalStringValue = "SUB-TOTAL  " + strST;
        str += "\n" + subtotalStringValue;

        jsonObjectPrint.setSubTotal(StrSubtotal);

        //str = "\n" + "SUB-TOTAL  " + strST;
        Query.PrintingValueSetForIMIN(mIminPrintUtils,"SUB-TOTAL  ","",StrSubtotal,new int[]{2,0,1},
                new int[]{0,1,2},new int[]{22,22,22});

        if (TotalBillDisount != null && Double.valueOf(TotalBillDisount) > 0.0) {
//            if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)) {
//                strTotalBillDisount = validate_space(0, 42, "-$" + String.format("%.2f", Double.valueOf(TotalBillDisount)), "Price");
//            }else {
            //strTotalBillDisount = validate_space(0, 18, "-" + String.format("%.2f", Double.valueOf(TotalBillDisount)), "Price");
            //strTotalBillDisount = validate_space(0, 17, "-$" + String.format("%.2f", Double.valueOf(TotalBillDisount)), "Price");
            strTotalBillDisount = validate_space(0, 18, "-$" + String.format("%.2f", Double.valueOf(TotalBillDisount)), "Price");
//            }
            //str += "\n" + "BillDiscount $" +  strTotalBillDisount;
            //str += "\n" + "BillDiscount  " +  strTotalBillDisount;
            String billDisStringValue = "BillDiscount  " +  strTotalBillDisount;
            str += "\n" + billDisStringValue;

            jsonObjectPrint.setBillDiscount(strTotalBillDisount);

            Query.PrintingValueSetForIMIN(mIminPrintUtils,"BillDiscount","","-$" + String.format("%.2f", Double.valueOf(TotalBillDisount)),new int[]{2,0,1},
                    new int[]{0,1,2},new int[]{22,22,22});

            if (billDiscountTypeNameSales.equals("% Percentage")) {
                if (billDiscountValueSales < 0.0){
                    billDiscountValueSales = (-1) * billDiscountValueSales;
                    if (terminalTypeVal.toUpperCase().equals(Constraints.INGENICO.toUpperCase())){
                        str += "\n" + DiscountNameSales + "(-" + billDiscountValueSales + "%%)";
                    }else {
                        str += "\n" + DiscountNameSales + "(-" + billDiscountValueSales + "%)";
                        String disNameStringValue = DiscountNameSales + "(-" + billDiscountValueSales + "%)";
                        str += "\n" + disNameStringValue;

                        jsonObjectPrint.setBillDiscountNameValue(disNameStringValue);

                        Query.PrintingValueSetForIMIN(mIminPrintUtils,disNameStringValue,"","",new int[]{2,0,0},
                                new int[]{0,1,2},new int[]{22,22,22});

                    }
                }else {
                    if (terminalTypeVal.toUpperCase().equals(Constraints.INGENICO.toUpperCase())){
                        str += "\n" + DiscountNameSales + "(" + billDiscountValueSales + "%%)";
                    }else {
                        //str += "\n" + DiscountNameSales + "(" + billDiscountValueSales + "%)";
                        String disNameStringValue = DiscountNameSales + "(" + billDiscountValueSales + "%)";
                        str += "\n" + disNameStringValue;

                        jsonObjectPrint.setBillDiscountNameValue(disNameStringValue);

                        Query.PrintingValueSetForIMIN(mIminPrintUtils,disNameStringValue,"","",new int[]{2,0,0},
                                new int[]{0,1,2},new int[]{22,22,22});

                    }
                }
            }else {
                if (billDiscountValueSales < 0.0){
                    billDiscountValueSales = (-1) * billDiscountValueSales;
                    //str += "\n" + DiscountNameSales + "(-$" + billDiscountValueSales + ")";
                    String disNameStringValue = DiscountNameSales + "(-$" + billDiscountValueSales + ")";
                    str += "\n" + disNameStringValue;
                    Query.PrintingValueSetForIMIN(mIminPrintUtils,disNameStringValue,"","",new int[]{2,0,0},
                            new int[]{0,1,2},new int[]{22,22,22});
                }else {
                    //str += "\n" + DiscountNameSales + "($" + billDiscountValueSales + ")";
                    String disNameStringValue = DiscountNameSales + "($" + billDiscountValueSales + ")";
                    str += "\n" + disNameStringValue;
                    Query.PrintingValueSetForIMIN(mIminPrintUtils,disNameStringValue,"","",new int[]{2,0,0},
                            new int[]{0,1,2},new int[]{22,22,22});
                }
            }
        }


        String totQtyaa = Query.GetPromotionPriceByBillNo(BillNo,"0");


        Boolean checkZCloseStatus = false;
        if (status.toUpperCase().equals(Constraints.SALES.toUpperCase()) || status.toUpperCase().equals(Constraints.REFUND.toUpperCase()) || status.toUpperCase().equals(Constraints.Reprint.toUpperCase())) {
            checkZCloseStatus = true;
        }else {
            checkZCloseStatus = false;
        }


        //if (Double.valueOf(totQtyaa) > 0.0) {
        if (Double.valueOf(totQtyaa) != 0.0) {
            String StrtotQtyaa = Query.ShowAmtMinusCorrectly(Double.valueOf(String.valueOf(totQtyaa)));
            String strPromotion = validate_space(0, 20, StrtotQtyaa, "Price");
            str += "\n" + "Promotion" +  strPromotion;

            Query.PrintingValueSetForIMIN(mIminPrintUtils,"Promotion","",strPromotion,
                    new int[]{2,1,1},
                    new int[]{0,1,2},new int[]{22,22,22});
        }

        Integer chk_serviceCharges = Query.CheckServiceCharges();
        if (chk_serviceCharges == 1) {
            String str_service_charges =  Query.GetServiceChargesNameAndPercentage();
            if (ServiceCharges != null && ServiceCharges.length() > 0 && Double.valueOf(ServiceCharges) != 0.0) {
                String str_ServiceChargesValue = Query.ShowAmtMinusCorrectly(Double.valueOf(String.valueOf(ServiceCharges)));
                //String str_ServiceChargesName = validate_space(0, 20, str_service_charges.toUpperCase() + " $", "Qty");
               // String str_ServiceChargesName = validate_space(0, 20, str_service_charges.toUpperCase() + "  ", "Qty");
                String str_ServiceChargesName = validate_space(0, 22, str_service_charges.toUpperCase() + "  ", "Qty");
                //str += "\n" + str_ServiceChargesName + validate_space(0, 11, str_ServiceChargesValue, "Price");
                if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)) {
                    Integer c_count12 = 12;
                    if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)){
                        c_count12 = 38;
                    }
                    str += "\n" + str_ServiceChargesName + validate_space(0, c_count12, str_ServiceChargesValue, "Price");

                    jsonObjectPrint.setServiceChargesName(str_ServiceChargesName);
                    jsonObjectPrint.setServiceChargesName(str_ServiceChargesValue);

                    Query.PrintingValueSetForIMIN(mIminPrintUtils,str_ServiceChargesName,"",str_ServiceChargesValue,
                            new int[]{2,1,1},
                            new int[]{0,1,2},new int[]{22,22,22});

                }else {
                    Integer c_count11 = 11;
                    if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)){
                        c_count11 = 39;
                    }
                    str += "\n" + str_ServiceChargesName + validate_space(0, c_count11, str_ServiceChargesValue, "Price");

                    jsonObjectPrint.setServiceChargesName(str_ServiceChargesName);
                    jsonObjectPrint.setServiceChargesName(str_ServiceChargesValue);

                    Query.PrintingValueSetForIMIN(mIminPrintUtils,str_ServiceChargesName,"",str_ServiceChargesValue,
                            new int[]{2,1,1},
                            new int[]{0,1,2},new int[]{22,22,22});
                }
            }
        }
        Cursor Cursor_tax = Query.GetTax();
        if (Cursor_tax != null){
            if (Cursor_tax.moveToNext()){
                String taxRate = Cursor_tax.getString(0);
                Integer taxType = Cursor_tax.getInt(1);
                String taxName = Cursor_tax.getString(2);

                String percentageSign = "";
                if (terminalTypeVal.toUpperCase().equals(Constraints.INGENICO.toUpperCase())){
                    percentageSign = "%%";

                    if (taxName.contains("%")){
                        String first = taxName.split("%")[0];
                        String second = "";
                        try {

                            second = taxName.split("%")[1];
                        }catch (ArrayIndexOutOfBoundsException e){
                            Log.i("TAG","error_"+e.getMessage());
                        }catch (Exception e){
                            Log.i("TAG","error_"+e.getMessage());
                        }
                        String str = first + percentageSign + second;
                        taxName = str;
                    }
                }else {
                    percentageSign = "%";
                }

                if (taxType == 2){
                    double amt_inclusive = Query.calculateInclusive(Double.valueOf(Total),Integer.parseInt(taxRate));
                    if (amt_inclusive != 0.0) {
                        String str_amt_inclusive = Query.ShowAmtMinusCorrectly(Double.valueOf(String.valueOf(amt_inclusive)));

                        //String str_inclusive = taxName.toUpperCase() + "(" + taxRate + percentageSign +")" + " SGD " + str_amt_inclusive + " Incl";
                        String str_inclusive = taxName.toUpperCase() + "(" + taxRate + percentageSign +")" + " $ " + str_amt_inclusive + " Incl";
                        str += "\n" + str_inclusive;

                        jsonObjectPrint.setTaxInclusive(str_inclusive);

                        Query.PrintingValueSetForIMIN(mIminPrintUtils,str_inclusive,"","",
                                new int[]{2,1,1},
                                new int[]{0,1,2},new int[]{22,22,22});

                    }
                }else if (taxType == 3){
                    if (TotalTaxes != 0.0) {
                        String str_exclusive = Query.ShowAmtMinusCorrectly(Double.valueOf(String.valueOf(TotalTaxes)));

                        //String TaxExcul = validate_space(0, 21, taxName.toUpperCase() + "(" + taxRate + percentageSign +")" + "  ", "Qty");
                        String TaxExcul = validate_space(0, 22, taxName.toUpperCase() + "(" + taxRate + percentageSign +")" + "  ", "Qty");
                        Integer c_count11 = 11;
                        if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)){
                            c_count11 = 34;
                        }
                        str += "\n" + TaxExcul + validate_space(0, c_count11, str_exclusive, "Price");

                        jsonObjectPrint.setTaxExclusiveName(TaxExcul);
                        jsonObjectPrint.setTaxExclusiveValue(str_exclusive);

                        Query.PrintingValueSetForIMIN(mIminPrintUtils,taxName.toUpperCase() + "(" + taxRate + percentageSign +")","",str_exclusive,
                                new int[]{2,1,1},
                                new int[]{0,1,2},new int[]{22,22,22});
                    }
                }
            }
            Cursor_tax.close();
        }
            Log.i("Dfdf___","Dfdfd______"+"dsffds_five");
        if (Double.valueOf(RoundAdj) != 0.0) {
            String roundStr = Query.ShowAmtMinusCorrectly(Double.valueOf(String.valueOf(RoundAdj)));
            //String RoundStr = validate_space(0, 21, "Round Adj".toUpperCase() + " $", "Qty");
            String RoundStr = validate_space(0, 21, "Round Adj".toUpperCase() + "  ", "Qty");
            Integer c_count11 = 11;
            if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)){
                c_count11 = 31;
            }
            str += "\n" + RoundStr + validate_space(0, c_count11, roundStr, "Price");

            jsonObjectPrint.setRoundAdj(roundStr);

            Query.PrintingValueSetForIMIN(mIminPrintUtils,"Round Adj".toUpperCase(),"",roundStr,
                    new int[]{2,1,1},
                    new int[]{0,1,2},new int[]{22,22,22});
        }
        String TotalStr = Query.ShowAmtMinusCorrectly(Double.valueOf(String.valueOf(Total)));
        Integer c_count25 = 25;
        if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)){
            c_count25 = 50;
        }
        strT = validate_space(0,c_count25,TotalStr,"Price");
        //str += "\n" + "TOTAL $" + strT;
        str += "\n" + "TOTAL  " + strT;

            Log.i("Dfdf___","Dfdfd______"+"dsffds_six");
        jsonObjectPrint.setBillTotal(TotalStr);

        Query.PrintingValueSetForIMIN(mIminPrintUtils,"TOTAL  ","",TotalStr,
                new int[]{2,0,1},
                new int[]{0,1,2},new int[]{22,22,22});


        if (checkZCloseStatus) {
            //Payment
            str += "\n" + "PAYMENT" + "\n";


            jsonObjectPrint.setBillPayment("PAYMENT");

            Query.PrintingValueSetForIMIN(mIminPrintUtils,"PAYMENT","","",
                    new int[]{2,1,1},
                    new int[]{0,1,2},new int[]{22,22,22});
        }

        Cursor c = Query.GetBillPaymentAmountDetails(BillNo);
        if (c != null) {
            PaymentTypeNameArr.clear();
            PaymentTypeAmountArr.clear();
            PaymentTypeRemarksArr.clear();
            while (c.moveToNext()) {
                if (!c.isNull(0)) {

                    if (c.getString(6) != null && c.getInt(6) == 1){


                        if (c.getString(5) != null && c.getString(5).length() > 1) {

                            PaymentTypeNameArr.add(c.getString(5));
                            PaymentTypeNameArr.add("(" + c.getString(0) + ") ");
                            Double val = c.getDouble(1) + c.getDouble(2);
                            PaymentTypeAmountArr.add(String.valueOf(val));
                            PaymentTypeAmountArr.add("");
                        }else {
                            PaymentTypeNameArr.add(c.getString(0));
                            Double val = c.getDouble(1) + c.getDouble(2);
                            PaymentTypeAmountArr.add(String.valueOf(val));
                        }
                    }else {
                        PaymentTypeNameArr.add(c.getString(0));
                        Double val = c.getDouble(1) + c.getDouble(2);
                        PaymentTypeAmountArr.add(String.valueOf(val));
                    }
                    PaymentTypeRemarksArr.add(c.getString(7));
                }
            }
            c.close();
        }

            Log.i("Dfdf___","Dfdfd______"+"dsffds_seven");
        ArrayList<BillPaymentValue> paymentValueArr = new ArrayList<BillPaymentValue>();
        for (int i = 0 ; i < PaymentTypeNameArr.size() ; i++) {
            BillPaymentValue paymentValueObj = new BillPaymentValue();
            String payment_amt_ = "0.0";
            payment_amt_ = PaymentTypeAmountArr.get(i);
            Integer c_count16 = 16;
            if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)){
                c_count16 = 31;
            }

            str_PaymentType = validate_space(0, c_count16 , PaymentTypeNameArr.get(i).toUpperCase(), "");
            Log.i("dsfdsf___","dfdfdf__"+PaymentTypeNameArr.get(i).toUpperCase());
            paymentValueObj.setBillPaymentValueDetailsName(PaymentTypeNameArr.get(i).toUpperCase());
            Log.i("dsfdsf___","dfdfdf__"+paymentValueObj.getBillPaymentValueDetailsName());
            if (checkZCloseStatus) {
                str += str_PaymentType;
//                paymentValueObj.setBillPaymentValueDetailsAmount(PaymentTypeNameArr.get(i).toUpperCase());

            }

            if (checkZCloseStatus) {
//                    if (Double.valueOf(Total) < 0.0) {
//                        payment_amt_ = (-1) * payment_amt_;
//                    }else {
//                        payment_amt_ = payment_amt_;
//                    }
                payment_amt_ = payment_amt_;
            }

//            if (payment_amt_ != null && payment_amt_.length() > 0) {
            try {
                String _pay_amt = "";
                if (payment_amt_ != null && payment_amt_.length() > 0) {
                    _pay_amt = Query.ShowAmtMinusCorrectly(Double.valueOf(payment_amt_));
                }
                Query.PrintingValueSetForIMIN(mIminPrintUtils, PaymentTypeNameArr.get(i).toUpperCase(), "", _pay_amt,
                        new int[]{2, 0, 1},
                        new int[]{0, 1, 2}, new int[]{22, 22, 22});

                //String _pay_amt = String.format("%7.2f", payment_amt_).substring(0, 7);

                c_count16 = 16;
                if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)) {
                    c_count16 = 27;
                }
                str_pay_amt = validate_space(0, c_count16, _pay_amt, "Price");
                if (checkZCloseStatus) {
                    str += str_pay_amt;

                    paymentValueObj.setBillPaymentValueDetailsAmount(_pay_amt);
//                    Query.PrintingValueSetForIMIN(mIminPrintUtils,PaymentTypeNameArr.get(i).toUpperCase(),"",_pay_amt,
//                            new int[]{2,1,1},
//                            new int[]{0,1,2},new int[]{22,22,22});
                    if (terminalTypeVal.equals(Constraints.INGENICO)) {
                        if (i < PaymentTypeNameArr.size() - 1) {
                            str += "\n";
                        }
                    }
                }
//            }else {
//                str += "";
//            }
            } catch (Exception e){
                str += "";
            }
            Log.i("Dfdf___","Dfdfd______"+"dsffds_eight");
           //if (PaymentTypeRemarksArr != null && PaymentTypeRemarksArr.size() > 0){
            if (PaymentTypeRemarksArr != null && PaymentTypeRemarksArr.size() > 0 &&
                    !(PaymentTypeRemarksArr.equals(null)) && !(PaymentTypeRemarksArr.equals("null"))){

                    try {

                        if (!PaymentTypeRemarksArr.get(i).equals("") && !(PaymentTypeRemarksArr.get(i).equals("null"))) {
                        str += "\n" + "(" + PaymentTypeRemarksArr.get(i) + ")" + "\n";

                        paymentValueObj.setBillPaymentValueDetailsRemarks("(" + PaymentTypeRemarksArr.get(i) + ")");

                        Query.PrintingValueSetForIMIN(mIminPrintUtils, "("+PaymentTypeRemarksArr.get(i).toUpperCase()+")", "", "",
                                new int[]{2, 0, 0},
                                new int[]{0, 1, 2}, new int[]{22, 22, 22});

                    }
                } catch (Exception e){
                }
            }
            paymentValueArr.add(paymentValueObj);
        }
            Log.i("Dfdf___","Dfdfd______"+"dsffds_nine"+paymentValueArr);

        jsonObjectPrint.setBillPaymentDetailsValue(paymentValueArr);

        if (checkZCloseStatus) {
//            if (!(Change.equals("0") || Change.isEmpty() || Change == null)) {
            if (Change != null && !(Change.isEmpty()) && !(Change.equals("0"))) {
                if (Double.valueOf(Change) != 0.0) {
                    String strC = "";
                    //Change = String.valueOf(Double.valueOf(Change));
                    String StrChange = Query.ShowAmtMinusCorrectly(Double.valueOf(String.valueOf(Change)));
                    c_count25 = 25;
                    if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)){
                        c_count25 = 47;
                    }
                    strC = validate_space(0, c_count25, StrChange, "Price");
                    str += "\n" + "CHANGE " + strC;

                    jsonObjectPrint.setBillChangeAmount(StrChange);

                    Query.PrintingValueSetForIMIN(mIminPrintUtils,"CHANGE","",StrChange,
                            new int[]{2,1,1},
                            new int[]{0,1,2},new int[]{22,22,22});
                }
            }
        }
        str += "\n" + total_line;
        jsonObjectPrint.setTotalLine(total_line);
//        Query.PrintingValueSetForIMINReport(mIminPrintUtils,Constraints.LINEIMIN, Constraints.HEADER);
            Log.i("Dfdf___","Dfdfd______"+"dsffds_nine");
        //Mercatus
        MercatusReceiptData mrd = new MercatusReceiptData();
        mrd = Mercatusfun(mIminPrintUtils,terminalTypeVal);
        jsonObjectPrint.setMercatusInfo(mrd);

        JeripayReceiptData jprd = new JeripayReceiptData();
        //Jeripay
         jprd=JeripayFun(mIminPrintUtils,terminalTypeVal,total_line);

         jsonObjectPrint.setJeripayInfo(jprd);

            Log.i("Dfdf___","Dfdfd______"+"dsffds_ten");
        String closed_sales_str = "CLOSED SALES:" + ReceiptNoDateTime.trim();
        str += "\n" + closed_sales_str;

        jsonObjectPrint.setClosedSales("CLOSED SALES:" + ReceiptNoDateTime.trim());

        try {

            Query.PrintingValueSetForIMIN(mIminPrintUtils,closed_sales_str,"","",
                    new int[]{2,0,0},
                    new int[]{0,1,2},new int[]{22,22,22});
            Query.PrintingValueSetForIMINReport(mIminPrintUtils,Constraints.LINEIMIN, Constraints.HEADER);

            Query.PrintingValueSetForIMIN(mIminPrintUtils,Footer,"","",
                    new int[]{2,0,0},
                    new int[]{0,1,2},new int[]{22,22,22});
            Query.PrintingValueSetForIMINReport(mIminPrintUtils,"\n", Constraints.HEADER);
//        Query.PrintingValueSetForIMINReport(mIminPrintUtils,"\n", Constraints.HEADER);
            Query.PrintingValueSetForIMIN(mIminPrintUtils,"","","",
                    new int[]{2,0,0},
                    new int[]{0,1,2},new int[]{22,22,22});
            Query.PrintingValueSetForIMIN(mIminPrintUtils,"","","",
                    new int[]{2,0,0},
                    new int[]{0,1,2},new int[]{22,22,22});
            IminPrintUtils.getInstance(context).partialCut();
        } catch (Exception e){

        }
        str +="\n" + total_line + "\n";

        str +=  Footer + "\n"+ "\n"+ "\n"+ "\n"+ "\n";
            Log.i("Dfdf___","Dfdfd______"+"dsffds_ten_1");
         //mIminPrintUtils = null;
          jsonObjectPrint.setReceiptFooter(Footer);
            Log.i("Dfdf___","Dfdfd______"+"dsffds_ten_2");
     } catch (Exception e){

    }
        return jsonObjectPrint;
    }
//    @RequiresApi(api = Build.VERSION_CODES.N)
//    private static void printingReceiptFormat(Integer sale_id, String status, String BillNo) {
//        String terminalTypeVal = Query.GetDeviceData(Constraints.TERMINAL_TYPE);
//        str = "\n"+ Header;
//        String total_line = "";
//        Integer line32spacecount = Query.GetLineSpaceCount(terminalTypeVal,32,0);
//        if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)) {
//            line32spacecount = line32spacecount + 5;
//        }
//        for (int i = 0; i < line32spacecount; i++) {
//            total_line += "-";
//        }
//////
//
//        if (status.toUpperCase().equals(Constraints.VOID.toUpperCase())) {
//            str += "\n" + total_line;
//            str += "\n" + "Cancel Bill";
//        }
//        if (status.toUpperCase().equals(Constraints.CANCEL.toUpperCase())) {
//            str += "\n" + total_line;
//            str += "\n" + "Cancel Bill";
//        }
//        if (status.toUpperCase().equals(Constraints.REFUND.toUpperCase())) {
//            str += "\n" + total_line;
//            str += "\n" + "Refund Bill";
//        }
//        if (status.toUpperCase().equals(Constraints.Reprint.toUpperCase())) {
//            str += "\n" + total_line;
//            str += "\n" + "Reprint Bill";
//
//            String sq = "SELECT STATUS FROM SALES WHERE ID = "+sale_id;
//            Cursor csq = DBFunc.Query(sq,false);
//            if ( csq != null) {
//                if (csq.moveToNext()) {
//                    if (csq.getString(0).toUpperCase().equals(Constraints.VOID.toUpperCase())) {
//                        str += "\n" + total_line;
//                        str += "\n" + "Cancel Bill";
//                    }
//                    if (csq.getString(0).toUpperCase().equals(Constraints.CANCEL.toUpperCase())) {
//                        str += "\n" + total_line;
//                        str += "\n" + "Cancel Bill";
//                    }
//                }
//                csq.close();
//            }
//        }
//        str += "\n" + total_line;
//
//        String tblName = "0";
//        String queName = "0";
//        String orderStatus = "0";
//
//        Cursor C_DetailsBillProduct = Query.SearchBillProductByBillNo(BillNo, Constraints.NoGroupBy);
//
//        if (C_DetailsBillProduct != null) {
//            if (C_DetailsBillProduct.moveToNext()) {
//                tblName = C_DetailsBillProduct.getString(3);
//                queName = C_DetailsBillProduct.getString(4);
//                orderStatus = C_DetailsBillProduct.getString(5);
//            }
//            C_DetailsBillProduct.close();
//        }
//
//        if (!(queName == null ||  queName.equals("0") || queName.isEmpty())) {
//            if (!queName.equals("0 ,")) {
//
//            }
//            //str += "\n" + "QueueNo #" + queName;
//        }
//        if (!(tblName == null || tblName.equals("0") || !tblName.equals("0,") || tblName.isEmpty() )) {
//            Log.i("__tblName","tblName____"+tblName);
//            //str += "\n" + "TableNo #" + tblName;
//        }
//        if (orderStatus != null && orderStatus.toUpperCase().equals("HOLD".toUpperCase())) {
//            //str += "\n" + "Order Status #" + orderStatus;
//        }
//
//        //str += "\n"+ "DATE #" + ReceiptNoDateTime;
//        //str += "\n"+ "RECEIPT NO #" + ReceiptNo;
//        //Delivery
//        String delivery_text = Query.GetSalesDelivery(ReceiptNo);
//        if (delivery_text.length() > 0) {
//            //str += "\n\n"+Html.fromHtml(delivery_text);
//        }
//
//        String description_header =  "DESCRIPTION        QTY         PRICE";
//        str +="\n"+"\n"+ description_header;
//
//        total_line = "";
//        for (int i = 0; i < line32spacecount; i++) {
//            total_line += "-";
//        }
//        str += "\n" + total_line;
////        Integer line20spacecount = Query.GetLineSpaceCount(terminalTypeVal,20,0);
////        Integer line21spacecount = Query.GetLineSpaceCount(terminalTypeVal,21,0);
////        Integer line12spacecount = Query.GetLineSpaceCount(terminalTypeVal,12,0);
////        Integer line11spacecount = Query.GetLineSpaceCount(terminalTypeVal,11,0);
////        Integer line25spacecount = Query.GetLineSpaceCount(terminalTypeVal,25,0);
////        Integer line18pacecount = Query.GetLineSpaceCount(terminalTypeVal,18,0);
////        Integer line16spacecount = Query.GetLineSpaceCount(terminalTypeVal,16,0);
////        Integer line5spacecount = Query.GetLineSpaceCount(terminalTypeVal,5,0);
//        Double GrandtotalItemDis = 0.0;
//        for (int qty = 0 ; qty < sldQtyArr.size(); qty ++) {
//            //Qty
//            Qty = sldQtyArr.get(qty);
////            Qty = IDArr.get(qty);
//            Integer line5spacecount = Query.GetLineSpaceCount(terminalTypeVal,5,0);
////            }
//            strQty = validate_space(0,line5spacecount,Qty,"Qty");
//
//            //Name
//            Name = sldNameArr.get(qty);
//
//            //strName = GetFormatForDescription(Name);
//            strNameOrigin = GetFormatForDescription(Name,terminalTypeVal);
//
//            Double realTotAmt = 0.0;
//            String _StrName = "";
//            String strItemDisAmt = "";
//            //Double disamt = Double.valueOf(sltItemDisArr.get(qty));
//            Double disamt = Double.valueOf(Qty) * Double.valueOf(sltItemDisArr.get(qty));
//           //if (disamt > 0.0) {
//
//            if (disamt != 0.0) {
//                String StrName = "";
//                if (sldDiscountType.get(qty).equals("% Percentage")){
//                    if (terminalTypeVal.toUpperCase().equals(Constraints.INGENICO.toUpperCase())){
//                        StrName = sldDiscountName.get(qty) + "(" + sldDiscountValue.get(qty) + "%%" + ") ";
//                    }else {
//                        StrName = sldDiscountName.get(qty) + "(" + sldDiscountValue.get(qty) + "%" + ") ";
//                    }
//                }
//                if (sldDiscountType.get(qty).equals("$ Dollar Value")){
//                    StrName =  sldDiscountName.get(qty) +  "($" + sldDiscountValue.get(qty) + "" + ") ";
//                }
//
//               // String StrName = "***Item Discount***";
//                //_StrName = GetFormatForDesctiption(StrName);
//                //_StrName = StrName;
//                Integer line21spacecount = Query.GetLineSpaceCount(terminalTypeVal,2,01);
//                _StrName = validate_space(0,line21spacecount,StrName,"Qty");;
//                //_StrName = validate_space(0,21,StrName,"Qty");;
//               // _StrName = StrName;
//                //totalItemDis = sltItemDisArr.get(qty) * Double.valueOf(sldQtyArr.get(qty));
//                //ItemDisAmt = "-" + String.format("%.2f", totalItemDis);
//
//                if (terminalTypeVal.toUpperCase().equals(Constraints.INGENICO.toUpperCase())){
//                    Integer line12spacecount = Query.GetLineSpaceCount(terminalTypeVal,12,0);
////                    if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)) {
////                        line12spacecount = line12spacecount + 8;
////                    }
//                    strItemDisAmt = validate_space(0, line12spacecount, "-" + String.format("%.2f", disamt), "Price");
//                }else {
//                    Integer line11spacecount = Query.GetLineSpaceCount(terminalTypeVal,11,0);
////                    if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)) {
////                        line11spacecount = line11spacecount + 8;
////                    }
//                    strItemDisAmt = validate_space(0, line11spacecount, "-" + String.format("%.2f", disamt), "Price");
//                }
//                GrandtotalItemDis += Double.valueOf(disamt);
//
//                //realTotAmt = Double.valueOf(sltItemDisArr.get(qty)) + Double.valueOf(sltPriceTotalArr.get(qty));
//                realTotAmt = Double.valueOf(sltPriceTotalArr.get(qty)) - Double.valueOf(disamt);
//            }else {
//                realTotAmt = Double.valueOf(sltPriceTotalArr.get(qty));
//            }
//
//            //Price
//            //Price = String.format("%.2f", realTotAmt);
////            Price = String.format("%.2f", Double.valueOf(sltPriceTotalArr.get(qty)));
//            Price = String.format("%.2f", Double.valueOf(Qty) * Double.valueOf(sltPriceTotalArr.get(qty)));
//            Integer lineprice9spacecount = Query.GetLineSpaceCount(terminalTypeVal,9,0);
//            strPrice = validate_space(0,lineprice9spacecount,Price,"Price");
//            String nqp = "";
//
//           //nqp = strName + "x"+strQty + strPrice;
//            nqp = strNameOrigin + "x"+strQty + strPrice;
//            str += "\n" + nqp;
//            if (disamt !=  0.0) {
//                str += "\n" + _StrName + strItemDisAmt;
//            }
//            //Show PromoName And Value (MixAndMatch)
//            //GetPromo
//           str += GetShowPromoDetails(terminalTypeVal,qty);
////           str += GetShowPromoDetails(Integer.parseInt(String.valueOf(Qty)));
//
//            //GetPLUModi
//           str += GetShowPLUModiDetails(BillNo,String.valueOf(IDArr.get(qty)),String.valueOf(sldOpenPriceStatusArr.get(qty)),Qty);
//
////           GetShowPLUModiDetails(BillNo,String.valueOf(Qty));
//        }
//
//        //strST = validate_space(0,21,String.format("%.2f", Double.valueOf(SubTotal)),"Price");
//
//
//        //SalesData(sale_id);
//
//        //String StrSubtotal = Query.ShowAmtMinusCorrectly(Double.valueOf(Subtotal));
//        if (sale_id == -5){
//            SubTotal = String.valueOf(CheckOutActivity.sub_total);
//        }
//
//        String StrSubtotal = Query.ShowAmtMinusCorrectly(Double.valueOf(SubTotal));
//        Integer line21spacecount = Query.GetLineSpaceCount(terminalTypeVal,21,0);
////        if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)){
////            line21spacecount = line21spacecount + 10;
////        }
//        strST = validate_space(0,line21spacecount,StrSubtotal,"Price");
//
//        String str_item = "ITEM";
//        if (sale_id == -5){
//            TotalItems = "0";
//        }
//
//        if (TotalItems.length() > 0){
//            str_item = "ITEMS";
//        }
//        str += "\n" + total_line;
//        str += "\n" + "TOTAL  (" + TotalItems +") "+ str_item +".";
//        str += "\n" + total_line;
//        //str += "\n" + "SUB-TOTAL" +" $" + strST;
//        str += "\n" + "SUB-TOTAL  " + strST;
//
//        if (Double.valueOf(TotalBillDisount) > 0.0) {
//            Integer line18pacecount = Query.GetLineSpaceCount(terminalTypeVal,18,0);
////            if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)){
////                line18pacecount = line18pacecount + 12;
////            }
//            strTotalBillDisount = validate_space(0, line18pacecount, "-$" + String.format("%.2f", Double.valueOf(TotalBillDisount)), "Price");
//            //str += "\n" + "BillDiscount $" +  strTotalBillDisount;
//            str += "\n" + "BillDiscount  " +  strTotalBillDisount;
//            if (billDiscountTypeNameSales.equals("% Percentage")) {
//                if (billDiscountValueSales < 0.0){
//                    billDiscountValueSales = (-1) * billDiscountValueSales;
//                    if (terminalTypeVal.toUpperCase().equals(Constraints.INGENICO.toUpperCase())){
//                        str += "\n" + DiscountNameSales + "(-" + billDiscountValueSales + "%%)";
//                    }else {
//                        str += "\n" + DiscountNameSales + "(-" + billDiscountValueSales + "%)";
//                    }
//                }else {
//                    if (terminalTypeVal.toUpperCase().equals(Constraints.INGENICO.toUpperCase())){
//                        str += "\n" + DiscountNameSales + "(" + billDiscountValueSales + "%%)";
//                    }else {
//                        str += "\n" + DiscountNameSales + "(" + billDiscountValueSales + "%)";
//                    }
//                }
//            }else {
//                if (billDiscountValueSales < 0.0){
//                    billDiscountValueSales = (-1) * billDiscountValueSales;
//                    str += "\n" + DiscountNameSales + "(-$" + billDiscountValueSales + ")";
//                }else {
//                    str += "\n" + DiscountNameSales + "($" + billDiscountValueSales + ")";
//                }
//            }
//        }
//
//        String totQtyaa = Query.GetPromotionPriceByBillNo(BillNo,"0");
//
//        Boolean checkSalesRefundReprint = false;
//        if (status.toUpperCase().equals(Constraints.SALES.toUpperCase()) || status.toUpperCase().equals(Constraints.REFUND.toUpperCase()) || status.toUpperCase().equals(Constraints.Reprint.toUpperCase())) {
//            checkSalesRefundReprint = true;
//        }else {
//            checkSalesRefundReprint = false;
//        }
//
//        //if (Double.valueOf(totQtyaa) > 0.0) {
//        if (Double.valueOf(totQtyaa) != 0.0) {
//            String StrtotQtyaa = Query.ShowAmtMinusCorrectly(Double.valueOf(String.valueOf(totQtyaa)));
//            Integer line20spacecount = Query.GetLineSpaceCount(terminalTypeVal,20,0);
//            String strPromotion = validate_space(0,line20spacecount, StrtotQtyaa, "Price");
//            str += "\n" + "Promotion" +  strPromotion;
//        }
//
//        Integer chk_serviceCharges = Query.CheckServiceCharges();
//        if (chk_serviceCharges == 1) {
//            String str_service_charges =  Query.GetServiceChargesNameAndPercentage();
//            if (ServiceCharges != null && ServiceCharges.length() > 0 && Double.valueOf(ServiceCharges) != 0.0) {
//                String str_ServiceChargesValue = Query.ShowAmtMinusCorrectly(Double.valueOf(String.valueOf(ServiceCharges)));
//                //String str_ServiceChargesName = validate_space(0, 20, str_service_charges.toUpperCase() + " $", "Qty");
//                Integer line20spacecount = Query.GetLineSpaceCount(terminalTypeVal,20,0);
//                String str_ServiceChargesName = validate_space(0, line20spacecount, str_service_charges.toUpperCase() + "  ", "Qty");
//                Integer line11spacecount = Query.GetLineSpaceCount(terminalTypeVal,11,0);
////                if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)) {
////                    line11spacecount = line11spacecount + 10;
////                }
//                str += "\n" + str_ServiceChargesName + validate_space(0, line11spacecount, str_ServiceChargesValue, "Price");
//            }
//        }
//        Cursor Cursor_tax = Query.GetTax();
//        if (Cursor_tax != null){
//            if (Cursor_tax.moveToNext()){
//                String taxRate = Cursor_tax.getString(0);
//                Integer taxType = Cursor_tax.getInt(1);
//                String taxName = Cursor_tax.getString(2);
//
//                String percentageSign = "";
//                if (terminalTypeVal.toUpperCase().equals(Constraints.INGENICO.toUpperCase())){
//                    percentageSign = "%%";
//
//                    if (taxName.contains("%")){
//                        Log.i("TAG","taxName_"+taxName);
//                        String first = taxName.split("%")[0];
//                        String second = "";
//                        try {
//
//                            second = taxName.split("%")[1];
//                        }catch (ArrayIndexOutOfBoundsException e){
//                            Log.i("TAG","error_"+e.getMessage());
//                        }
//                        String str = first + percentageSign + second;
//                        taxName = str;
//                    }
//                }else {
//                    percentageSign = "%";
//                }
//
//                if (taxType == 2){
//                    double amt_inclusive = Query.calculateInclusive(Double.valueOf(Total),Integer.parseInt(taxRate));
//                    if (amt_inclusive != 0.0) {
//                        String str_amt_inclusive = Query.ShowAmtMinusCorrectly(Double.valueOf(String.valueOf(amt_inclusive)));
//                        Log.i("TAXNAMEINCLUSIVE_","TAX__"+taxName.toUpperCase());
//                        String str_inclusive = taxName.toUpperCase() + "(" + taxRate + percentageSign +")" + " SGD " + str_amt_inclusive + " Incl";
//                        str += "\n" + str_inclusive;
//                    }
//                }else if (taxType == 3){
//                    if (TotalTaxes != 0.0) {
//                        String str_exclusive = Query.ShowAmtMinusCorrectly(Double.valueOf(String.valueOf(TotalTaxes)));
//                        Log.i("TAXNAMEe_","TAX__"+taxName.toUpperCase());
//                        line21spacecount = Query.GetLineSpaceCount(terminalTypeVal,21,0);
//                        String TaxExcul = validate_space(0, line21spacecount, taxName.toUpperCase() + "(" + taxRate + percentageSign +")" + "  ", "Qty");
//                        Integer line11spacecount = Query.GetLineSpaceCount(terminalTypeVal,11,0);
////                        if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)) {
////                            line11spacecount = line11spacecount + 10;
////                        }
//                        str += "\n" + TaxExcul + validate_space(0, line11spacecount, str_exclusive, "Price");
//                    }
//                }
//            }
//            Cursor_tax.close();
//        }
//
//        if (Double.valueOf(RoundAdj) != 0.0) {
//            String roundStr = Query.ShowAmtMinusCorrectly(Double.valueOf(String.valueOf(RoundAdj)));
//            //String RoundStr = validate_space(0, 21, "Round Adj".toUpperCase() + " $", "Qty");
//            line21spacecount = Query.GetLineSpaceCount(terminalTypeVal,21,0);
//            String RoundStr = validate_space(0, line21spacecount, "Round Adj".toUpperCase() + "  ", "Qty");
//            Integer line11spacecount = Query.GetLineSpaceCount(terminalTypeVal,11,0);
//            str += "\n" + RoundStr + validate_space(0, line11spacecount, roundStr, "Price");
//        }
//        String TotalStr = Query.ShowAmtMinusCorrectly(Double.valueOf(String.valueOf(Total)));
//        Integer line25spacecount = Query.GetLineSpaceCount(terminalTypeVal,25,0);
////        if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)){
////            line25spacecount = line25spacecount + 14;
////        }
//        strT = validate_space(0,line25spacecount,TotalStr,"Price");
//        //str += "\n" + "TOTAL $" + strT;
//        str += "\n" + "TOTAL  " + strT;
//        if (checkSalesRefundReprint) {
//            //Payment
//            str += "\n" + "PAYMENT" + "\n";
//        }
//
//        Cursor c = Query.GetBillPaymentAmountDetails(BillNo);
//        if (c != null) {
//            PaymentTypeNameArr.clear();
//            PaymentTypeAmountArr.clear();
//            while (c.moveToNext()) {
//                if (!c.isNull(0)) {
//
//                    if (c.getString(6) != null && c.getInt(6) == 1){
//
//                        if (c.getString(6) != null && c.getInt(6) != 0 && c.getString(6).length() > 0
//                                && c.getString(5) != null && c.getString(5).length() > 1) {
//
//                            PaymentTypeNameArr.add(c.getString(5));
//                            PaymentTypeNameArr.add("(" + c.getString(0) + ") ");
//                            Double val = c.getDouble(1) + c.getDouble(2);
//                            PaymentTypeAmountArr.add(String.valueOf(val));
//                            PaymentTypeAmountArr.add("");
//                        }else {
//                            PaymentTypeNameArr.add(c.getString(0));
//                            Double val = c.getDouble(1) + c.getDouble(2);
//                            PaymentTypeAmountArr.add(String.valueOf(val));
//                        }
//                    }else {
//                        PaymentTypeNameArr.add(c.getString(0));
//                        Double val = c.getDouble(1) + c.getDouble(2);
//                        PaymentTypeAmountArr.add(String.valueOf(val));
//                    }
//                }
//            }
//            c.close();
//        }
//
//
//
//        for (int i = 0 ; i < PaymentTypeNameArr.size() ; i++) {
//                String payment_amt_ = "0.0";
//                payment_amt_ = PaymentTypeAmountArr.get(i);
//                Integer line16spacecount = Query.GetLineSpaceCount(terminalTypeVal,16,0);
////                if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)){
////                    line16spacecount = line16spacecount + 5;
////                }
//                str_PaymentType = validate_space(0, line16spacecount, PaymentTypeNameArr.get(i).toUpperCase(), "");
//               //str_PaymentType = validate_space(0, 16, PaymentTypeNameArr.get(i).toUpperCase(), "");
//
//                if (checkSalesRefundReprint) {
//                    str += str_PaymentType;
//                }
//
//                if (checkSalesRefundReprint) {
////                    if (Double.valueOf(Total) < 0.0) {
////                        payment_amt_ = (-1) * payment_amt_;
////                    }else {
////                        payment_amt_ = payment_amt_;
////                    }
//                    payment_amt_ = payment_amt_;
//                }
//                if (payment_amt_ != null && payment_amt_.length() > 0) {
//                    String _pay_amt = Query.ShowAmtMinusCorrectly(Double.valueOf(payment_amt_));
//                    //String _pay_amt = String.format("%7.2f", payment_amt_).substring(0, 7);
//
//                    line16spacecount = Query.GetLineSpaceCount(terminalTypeVal,16,0);
////                    if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)){
////                        line16spacecount = line16spacecount + 5;
////                    }
//                    str_pay_amt = validate_space(0, line16spacecount, _pay_amt, "Price");
//                    //str_pay_amt = validate_space(0, 16, _pay_amt, "Price");
//                    if (checkSalesRefundReprint) {
//                        str += str_pay_amt;
//                        if (terminalTypeVal.equals(Constraints.INGENICO)){
//                            if (i < PaymentTypeNameArr.size() - 1) {
//                                str += "\n";
//                            }
//                        }
//                    }
//                }else {
//                    str += "";
//                }
//        }
//        Log.i("str___","str____"+str);
//        if (checkSalesRefundReprint) {
////            if (!(Change.equals("0") || Change.isEmpty() || Change == null)) {
//            if (Change != null && !(Change.isEmpty()) && !(Change.equals("0"))) {
//                if (Double.valueOf(Change) != 0.0) {
//                    String strC = "";
//                    //Change = String.valueOf(Double.valueOf(Change));
//                    String StrChange = Query.ShowAmtMinusCorrectly(Double.valueOf(String.valueOf(Change)));
//                    line25spacecount = Query.GetLineSpaceCount(terminalTypeVal,25,0);
//                    strC = validate_space(0, line25spacecount, StrChange, "Price");
//                    str += "\n" + "CHANGE " + strC;
//                }
//            }
//        }
//        str += "\n" + total_line;
//
//        //Mercatus
//       Mercatusfun(terminalTypeVal);
//
//       //Jeripay
//        JeripayFun(terminalTypeVal);
//
//        str += "\n" + total_line;
//        String closed_sales_str = "CLOSED SALES:" + ReceiptNoDateTime;
//        str += "\n" + closed_sales_str;
//
//        str +="\n" + total_line + "\n";
//        str +=  Footer + "\n"+ "\n"+ "\n"+ "\n"+ "\n";
//
//        Log.i("PrintingRECEIPT__","str+"+str);
//
//    }

    private static JeripayReceiptData JeripayFun(IminPrintUtils mIminPrintUtils, String terminalTypeVal,
                                                 String total_line) {
        JeripayReceiptData jprd = new JeripayReceiptData();
        Integer line21spacecount = Query.GetLineSpaceCount(terminalTypeVal,21,0);
        String IsCardPayment = "0";
        Cursor billJeripay = Query.GetBillJeripay(BillNo);
        if (billJeripay != null){
            if (billJeripay.getCount() > 0){
                if (billJeripay.moveToNext()) {
                    IsCardPayment = billJeripay.getString(4);
                    if (IsCardPayment != null && IsCardPayment.length() > 0) {
                        str += "\n" + "Card Information";

                        IMINPrintinFormatforJeripayMercatus(mIminPrintUtils,"Card Information");
                    }
                }
            }
            while (billJeripay.moveToNext()){
                String sstatus = billJeripay.getString(0);
                String acquirer = billJeripay.getString(1);
                String acquirer_payment_id = billJeripay.getString(2);
                String amount = billJeripay.getString(3);
                IsCardPayment = billJeripay.getString(4);

                String str_status = "Status";
                String str_acquirer = "Acquirer";
                String acquirer_payment_idStr =  "Acquirer PaymentID";
                String acquirer_amt =  "Amount";
                line21spacecount = Query.GetLineSpaceCount(terminalTypeVal,21,0);
                String sstatusStr = validate_space(0, line21spacecount,  str_status + "  ", "Qty");
                String acquirerStr = validate_space(0, line21spacecount,  str_acquirer + "  ", "Qty");

                String amountStr = validate_space(0, line21spacecount,  acquirer_amt+ "  ", "Qty");

                if (IsCardPayment.equals("1")) {

                    str += "\n" + "\n" + sstatusStr;
                    str += "\n" + sstatus ;
                    str += "\n" + "\n" + acquirerStr;
                    str += "\n" + acquirer ;
                    str += "\n" + "\n" + acquirer_payment_idStr;
                    str += "\n" + acquirer_payment_id ;
                    str += "\n" + "\n" + amountStr;
                    str += "\n" + "$" + String.format("%.2f", Double.valueOf(amount));
                    str += "\n" + total_line;

                    jprd.setStatus(sstatus);
                    jprd.setAcquirer(acquirer);
                    jprd.setAcquirer_PaymentID(acquirer_payment_id);
                    jprd.setAmount("$" + String.format("%.2f", Double.valueOf(amount)));


                    if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)) {
                        IMINPrintinFormatforJeripayMercatus(mIminPrintUtils,str_status);
                        IMINPrintinFormatforJeripayMercatus(mIminPrintUtils,sstatus);
                        IMINPrintinFormatforJeripayMercatus(mIminPrintUtils,str_acquirer);
                        IMINPrintinFormatforJeripayMercatus(mIminPrintUtils,acquirer);
                        IMINPrintinFormatforJeripayMercatus(mIminPrintUtils,acquirer_payment_idStr);
                        IMINPrintinFormatforJeripayMercatus(mIminPrintUtils,acquirer_payment_id);
                        IMINPrintinFormatforJeripayMercatus(mIminPrintUtils,acquirer_amt);
                        IMINPrintinFormatforJeripayMercatus(mIminPrintUtils,"$" + String.format("%.2f", Double.valueOf(amount)));
                    }
                }
            }
            billJeripay.close();
        }


        Cursor jeripayDetails = Query.GetJeripayDetails(BillNo);
        if (jeripayDetails != null){
            while (jeripayDetails.moveToNext()){
                String card_label = jeripayDetails.getString(0);
                String card_number = jeripayDetails.getString(1);
                String invoice_number = jeripayDetails.getString(2);
                line21spacecount = Query.GetLineSpaceCount(terminalTypeVal,21,0);
                String card_labelStr = validate_space(0, line21spacecount,  Constraints.CARDLABEL + "  ", "Qty");
                String card_numberStr = validate_space(0, line21spacecount, Constraints.CARDNUMBER + "  ", "Qty");
                String invoice_numberStr = validate_space(0, line21spacecount,  Constraints.INVNUMBER + "  ", "Qty");
                if (IsCardPayment.equals("1")) {
                    str += "\n" + "\n" + card_labelStr;
                    str += "\n" + card_label ;
                    str += "\n" + "\n" + card_numberStr;
                    str += "\n" + card_number ;
                    str += "\n" + "\n" + invoice_numberStr;
                    str += "\n" + invoice_number ;

                    jprd.setCard_Label(card_label);
                    jprd.setCard_Number(card_number);
                    jprd.setInvoice_Number(invoice_number);

                    if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)) {
                        IMINPrintinFormatforJeripayMercatus(mIminPrintUtils, Constraints.CARDLABEL);
                        IMINPrintinFormatforJeripayMercatus(mIminPrintUtils, card_label);
                        IMINPrintinFormatforJeripayMercatus(mIminPrintUtils, Constraints.CARDNUMBER);
                        IMINPrintinFormatforJeripayMercatus(mIminPrintUtils, card_number);
                        IMINPrintinFormatforJeripayMercatus(mIminPrintUtils, Constraints.INVNUMBER);
                        IMINPrintinFormatforJeripayMercatus(mIminPrintUtils, invoice_number);
                    }
                }
            }
            jeripayDetails.close();
        }
        return jprd;
    }

    public static void IMINPrintinFormatforJeripayMercatus(IminPrintUtils mIminPrintUtils, String colText) {
        Query.PrintingValueSetForIMIN(mIminPrintUtils, colText, "", "",
                new int[]{2, 0, 0},
                new int[]{0, 1, 2}, new int[]{22, 22, 22});
    }

    private static MercatusReceiptData Mercatusfun(IminPrintUtils mIminPrintUtils, String terminalTypeVal) {
        MercatusReceiptData mrd = new MercatusReceiptData();
        Integer line21spacecount = Query.GetLineSpaceCount(terminalTypeVal,21,0);
        String memberStatus = "Not Member";
        Cursor cM = Query.GetBillMercatus(BillNo);
        if (cM != null){
            while (cM.moveToNext()){
                String chkStatus = cM.getString(0);
                if (chkStatus.equals("1")){
                    memberStatus = "Member";
                }else {
                    memberStatus = "No Member";
                }

                str += "\n" +memberStatus;

                mrd.setMemberStatus(memberStatus);

                IMINPrintinFormatforJeripayMercatus(mIminPrintUtils,"No Member");
            }
            cM.close();
        }
        Cursor jeripayPayment = Query.GetMercatusPayment(BillNo);
        if (jeripayPayment != null){
            while (jeripayPayment.moveToNext()){
                String payment_type = jeripayPayment.getString(0);
                String payment_amount = jeripayPayment.getString(1);

                String paymentTypelStr = validate_space(0, line21spacecount, Constraints.PaymentType + "  ", "Qty");
                String paymentAmountStr = validate_space(0, line21spacecount,  Constraints.PaymentAmount + "  ", "Qty");

//                str += "\n" + paymentTypelStr + validate_space(0, 11, payment_type, "Price");
//                str += "\n" + paymentAmountStr + validate_space(0, 11, payment_amount, "Price");

                str += "\n" + "\n" + paymentTypelStr;
                str += "\n" + payment_type ;
                str += "\n" + "\n" + paymentAmountStr;
                str += "\n" + payment_amount ;

                mrd.setPayment_type(payment_type);
                mrd.setPayment_amount(payment_amount);

                if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)) {
                    IMINPrintinFormatforJeripayMercatus(mIminPrintUtils, Constraints.PaymentType);
                    IMINPrintinFormatforJeripayMercatus(mIminPrintUtils, payment_type);
                    IMINPrintinFormatforJeripayMercatus(mIminPrintUtils, Constraints.PaymentAmount);
                    IMINPrintinFormatforJeripayMercatus(mIminPrintUtils, payment_amount);
                }
            }
            jeripayPayment.close();
        }
        Cursor cMDetails = Query.GetMercatusDetails(BillNo);
        if (cMDetails != null){
            while (cMDetails.moveToNext()){
                String card_label = cMDetails.getString(0);
                String card_number = cMDetails.getString(1);
                String invoice_number = cMDetails.getString(2);
                String card_labelStr = validate_space(0, line21spacecount, Constraints.CARDLABEL + "  ", "Qty");
                String card_numberStr = validate_space(0, line21spacecount, Constraints.CARDNUMBER + "  ", "Qty");
                String invoice_numberStr = validate_space(0, line21spacecount, Constraints.INVNUMBER + "  ", "Qty");

//                str += "\n" + card_labelStr + validate_space(0, 11, card_label, "Price");
//                str += "\n" + card_numberStr + validate_space(0, 11, card_number, "Price");
//                str += "\n" + invoice_numberStr + validate_space(0, 11, invoice_number, "Price");

                str += "\n" + "\n" + card_labelStr;
                str += "\n" +"\n" + card_label ;
                str += "\n" + "\n" + card_numberStr;
                str += "\n" +"\n" + card_number ;
                str += "\n" + "\n" + invoice_numberStr;
                str += "\n" +"\n" + invoice_number ;

                mrd.setCard_Label(card_label);
                mrd.setCard_Number(card_number);

                if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)) {
                    IMINPrintinFormatforJeripayMercatus(mIminPrintUtils, Constraints.CARDLABEL);
                    IMINPrintinFormatforJeripayMercatus(mIminPrintUtils, card_label);
                    IMINPrintinFormatforJeripayMercatus(mIminPrintUtils, Constraints.CARDNUMBER);
                    IMINPrintinFormatforJeripayMercatus(mIminPrintUtils, card_number);
                    IMINPrintinFormatforJeripayMercatus(mIminPrintUtils, Constraints.INVNUMBER);
                    IMINPrintinFormatforJeripayMercatus(mIminPrintUtils, invoice_number);
                }
            }
            cMDetails.close();
        }

        Cursor cMV = Query.GetMercatusVoucher(BillNo);
        if (cMV != null){
            while (cMV.moveToNext()){
                String voucher_number = cMV.getString(0);
                String voucher_amount = cMV.getString(1);
                String voucherNumberStr = validate_space(0, line21spacecount, Constraints.VoucherNumber + "  ", "Qty");
                String voucher_amountStr = validate_space(0, line21spacecount, Constraints.VoucherAmount + "  ", "Qty");

                str += "\n" +"\n" + voucherNumberStr;
                str += "\n" + voucher_number;
                str += "\n" +"\n" + voucher_amountStr;
                str += "\n" + "$"+ String.format("%.2f", Double.valueOf(voucher_amount));


                mrd.setVoucher_number(voucher_number);
                mrd.setVoucher_amount("$"+ String.format("%.2f", Double.valueOf(voucher_amount)));

                if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)) {
                    IMINPrintinFormatforJeripayMercatus(mIminPrintUtils, Constraints.VoucherNumber);
                    IMINPrintinFormatforJeripayMercatus(mIminPrintUtils, voucher_number);
                    IMINPrintinFormatforJeripayMercatus(mIminPrintUtils, Constraints.VoucherAmount);
                    IMINPrintinFormatforJeripayMercatus(mIminPrintUtils, "$"+ String.format("%.2f", Double.valueOf(voucher_amount)));
                }

            }
            cMV.close();
        }
        Cursor cMLoyal = Query.GetMercatusMallLoyaltyDetails(BillNo);
        if (cMLoyal != null){
            while (cMLoyal.moveToNext()){
//                status,transaction_id,transaction_amount
                String loyal_status = cMLoyal.getString(0);
                String loyal_transaction_id = cMLoyal.getString(1);
                String loyal_transaction_amount = cMLoyal.getString(2);

                String statStr = validate_space(0, line21spacecount, Constraints.MallLoyaltyStatus + "  ", "Qty");
//                String transIdStr = validate_space(0, 21, "Mall Loyalty Transaction Id" + "  ", "Qty");
//                String transAmtStr = validate_space(0, 21, "Mall Loyalty Transaction Amouny" + "  ", "Qty");
                String transIdStr = "Mall Loyalty Transaction Id";
                String transAmtStr = "Mall Loyalty Transaction Amouny";

                str += "\n" +"\n" + statStr;
                str += "\n" + loyal_status;
                str += "\n" +"\n" + transIdStr;
                str += "\n" + loyal_transaction_id;
                str += "\n" +"\n" + transAmtStr;
                str += "\n" + "$"+ String.format("%.2f", Double.valueOf(loyal_transaction_amount));

                if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)) {
                    IMINPrintinFormatforJeripayMercatus(mIminPrintUtils, Constraints.MallLoyaltyStatus);
                    IMINPrintinFormatforJeripayMercatus(mIminPrintUtils, loyal_status);
                    IMINPrintinFormatforJeripayMercatus(mIminPrintUtils, transIdStr);
                    IMINPrintinFormatforJeripayMercatus(mIminPrintUtils, loyal_transaction_id);
                    IMINPrintinFormatforJeripayMercatus(mIminPrintUtils, transAmtStr);
                    IMINPrintinFormatforJeripayMercatus(mIminPrintUtils, "$"+ String.format("%.2f", Double.valueOf(loyal_transaction_amount)));
                }
            }
            cMLoyal.close();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Query.PrintingValueSetForIMINReport(mIminPrintUtils,Constraints.LINEIMIN, Constraints.HEADER);
        }
        return mrd;
    }

//    private static String GetShowPLUModiDetails(String BillNo,String Qty,String openPriceStatus,String Qty) {
    private static String GetShowPLUModiDetails(String terminalType,IminPrintUtils mIminPrintUtils, String BillNo, String ID,
                                                String openPriceStatus,String remarks, String Qty,Integer detailsBillPID) {

//        String text = "";
        String str = "";
//        ArrayList ModiN = CheckOutAdapter.ModiFun(BillNo,Name);
        ArrayList ModiN = CheckOutAdapter.ModiFun(BillNo,ID,openPriceStatus,remarks,Qty,detailsBillPID);
        String text = "";

        if (ModiN.size() > 0){

            for (int modiVar = 0 ; modiVar < ModiN.size() ; modiVar ++) {
                if (terminalType.equals(Constraints.IMIN)) {
                    if (modiVar > 0){
                        text += "<br/>";
                    }
                    text += "<font color='#a9aaad'>(" + ModiN.get(modiVar) + ")</font>";
                } else {
                    text += "<br/><font color='#a9aaad'>(" + ModiN.get(modiVar) + ")</font>";
                }
            }
        }

        if (ModiN.size() > 0){
            str += Html.fromHtml(text);
        }

        Query.PrintingValueSetForIMIN(mIminPrintUtils,str,"","",new int[]{2,0,0},
                new int[]{0,1,2},new int[]{22,22,22});
        return str;
    }

    private static String GetShowPromoDetails(String terminalTypeVal, int qty, IminPrintUtils mIminPrintUtils) {
        Integer line21spacecount = Query.GetLineSpaceCount(terminalTypeVal,21,0);
        Integer line11spacecount = Query.GetLineSpaceCount(terminalTypeVal,11,0);
        String str = "";
        Cursor CursorPromo = Query.GetPromo(BillNo,sldIDPromoArr.get(qty));
        //PLUID,PromoName,PromoValue from PLUPromo
//            String text_promo_name = "0";
//            String text_promo_value = "0";
        if (CursorPromo != null){
            while (CursorPromo.moveToNext()){
                String PromoName =  CursorPromo.getString(1);
                Double PromoValue =  CursorPromo.getDouble(2);
                String promo_StrName = validate_space(0,line21spacecount,PromoName,"Qty");

                String promo_StrValue = validate_space(0,line11spacecount, String.format("%.2f", PromoValue), "Price");
                str += "\n" + promo_StrName + promo_StrValue;

                Query.PrintingValueSetForIMIN(mIminPrintUtils,promo_StrName,"",promo_StrValue,new int[]{2,1,1},
                        new int[]{0,1,2},new int[]{22,22,22});
            }
            CursorPromo.close();
        }
        return str;
    }

    public static void Tax() {
        Cursor plutax = DBFunc.Query("SELECT ID,Name,Acronym,Rate, Type FROM Tax ", true);
        String tax_acronym = "";
        if (plutax != null) {
            //double _tax = 0;
            //total_tax = 0.0;
            str_taxname = "";
            while (plutax.moveToNext()) {
                //if (plutax.getInt(4) == 0) {// not include(add on tax)
                    //_tax = (totalPrice * (plutax.getDouble(3) / 100f));
                    //str_taxname = plutax.getString(1) + "( " + plutax.getInt(3) + " %% )";
                    str_taxname = plutax.getString(1).toLowerCase() + "( " + plutax.getInt(3) + " % )";

                //}
            }
            plutax.close();
        }
    }

//    public static IDAL getDal(){
//        if(dal == null){
//            try {
//                long start = System.currentTimeMillis();
//                dal = NeptuneLiteUser.getInstance().getDal(appContext);
//                Log.i("Test","getdal  cost:"+(System.currentTimeMillis() - start)+" ms");
//                Log.i("Test____getd______",(System.currentTimeMillis() - start)+" ms");
//            } catch (Exception e) {
//                e.printStackTrace();
//                Toast.makeText(appContext, "error occurred,DAL is null.", Toast.LENGTH_LONG).show();
//
//
//            }
//        }
//        return dal;
//    }

    public static IDAL getDal(Context context) {
        if (dal == null) {
            try {
                long start = System.currentTimeMillis();
                dal = NeptuneLiteUser.getInstance().getDal(context);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(context, "error occurred,DAL is null.", Toast.LENGTH_LONG).show();
            }
        }
        return dal;
    }
//    public static IDAL getDal(){
//        if(dal == null){
//            try {
//                long start = System.currentTimeMillis();
//                dal = NeptuneLiteUser.getInstance().getDal(appContext);
//                Log.i("Test","get dal cost:"+(System.currentTimeMillis() - start)+" ms");
//            } catch (Exception e) {
//                e.printStackTrace();
//                Toast.makeText(appContext, "error occurred,DAL is null.", Toast.LENGTH_LONG).show();
//            }
//        }
//        return dal;
//    }

     public static String GetFormatForDescription(String valName,String terminalTypeVal){
        String strvalName = "";
        Integer count17 = 17;
        Integer count32 = 32;
        Integer count50 = 50;
        Integer count68 = 68;
        if(terminalTypeVal.toUpperCase().equals(Constraints.IMIN)){
             count17 = 22;
             count32 = 37;
             count50 = 55;
             count68 = 73;
        }
        Log.i("logg__","df___"+valName.length());
        if (valName.length() <= count17){
            //String name = valName.substring(0, valName.length());
            strvalName += validate_spacee_for_chinese_character(valName.substring(0, valName.length()),0,count17);
        }
        if (valName.length() > count17){
            //strvalName = validate_space(0,16,valName,"");
            //strvalName = validate_space(0,17,valName,"");
            strvalName = validate_spacee_for_chinese_character(valName.substring(0,count17),0,count17);
            if (valName.length() < count32){
                //String name = valName.substring(17, valName.length());
                //Log.i("fdf_name",name);
                //strvalName += validate_spacee_for_chinese_character(name);
                strvalName += validate_spacee_for_chinese_character(valName.substring(count17,valName.length()),0,count17);
            }
        }

        if (valName.length() > count32){
            //strvalName = validate_space(0,17,valName,"");
            strvalName = validate_spacee_for_chinese_character(valName.substring(0,count17),0,count17);
            strvalName += "\n"+validate_spacee_for_chinese_character(valName.substring(count17,count32),0,count17);
            //strvalName += "\n"+validate_space(17,32,valName,"");
            if (valName.length() < count50){
                //String name = valName.substring(32, valName.length());
                strvalName += validate_spacee_for_chinese_character(valName.substring(count32,valName.length()),0,count17);
            }
        }
        if (valName.length() > count50){
//            strvalName = validate_space(0,17,valName,"");
//            strvalName += "\n"+validate_space(17,32,valName,"");
//            strvalName += "\n"+validate_space(32,50,valName,"");
            strvalName = validate_spacee_for_chinese_character(valName.substring(0,count17),0,count17);
            strvalName += "\n"+validate_spacee_for_chinese_character(valName.substring(count17,count32),0,count17);
            strvalName += "\n"+validate_spacee_for_chinese_character(valName.substring(count32,count50),0,count17);
            if (valName.length() < count68){
                //strvalName += "\n" + valName.substring(50, valName.length());
                //String name = valName.substring(51, valName.length());
                //strvalName += validate_spacee_for_chinese_character(name);
                strvalName += validate_spacee_for_chinese_character(valName.substring(count50,valName.length()),0,count17);
            }
        }
        if (valName.length() > count68){
            strvalName = validate_space(0,count17,valName,"");
            strvalName += "\n"+validate_space(count17,count32,valName,"");
            strvalName += "\n"+validate_space(count32,count50,valName,"");
            strvalName += "\n"+validate_space(count50,count68,valName,"");
        }
        return strvalName;
    }
//    public static String GetFormatForDescription(String valName,String terminalTypeVal){
//        String strvalName = "";
//        Integer count17 = 17;
//        Integer count32 = 32;
//        Integer count50 = 50;
//        Integer count68 = 68;
//        if(terminalTypeVal.toUpperCase().equals(Constraints.IMIN)){
//             count17 = 22;
//             count32 = 37;
//             count50 = 55;
//             count68 = 73;
//        }
//        Log.i("logg__","df___"+valName.length());
//        if (valName.length() <= count17){
//            //String name = valName.substring(0, valName.length());
//            strvalName += validate_spacee_for_chinese_character(valName.substring(0, valName.length()),0,count17);
//        }
//        if (valName.length() > count17){
//            //strvalName = validate_space(0,16,valName,"");
//            //strvalName = validate_space(0,17,valName,"");
//            strvalName = validate_spacee_for_chinese_character(valName.substring(0,count17),0,count17);
//            if (valName.length() < count32){
//                //String name = valName.substring(17, valName.length());
//                //Log.i("fdf_name",name);
//                //strvalName += validate_spacee_for_chinese_character(name);
//                strvalName += validate_spacee_for_chinese_character(valName.substring(count17,valName.length()),0,count17);
//            }
//        }
//
//        if (valName.length() > count32){
//            //strvalName = validate_space(0,17,valName,"");
//            strvalName = validate_spacee_for_chinese_character(valName.substring(0,count17),0,count17);
//            strvalName += "\n"+validate_spacee_for_chinese_character(valName.substring(count17,count32),0,count17);
//            //strvalName += "\n"+validate_space(17,32,valName,"");
//            if (valName.length() < count50){
//                //String name = valName.substring(32, valName.length());
//                strvalName += validate_spacee_for_chinese_character(valName.substring(count32,valName.length()),0,count17);
//            }
//        }
//        if (valName.length() > count50){
////            strvalName = validate_space(0,17,valName,"");
////            strvalName += "\n"+validate_space(17,32,valName,"");
////            strvalName += "\n"+validate_space(32,50,valName,"");
//            strvalName = validate_spacee_for_chinese_character(valName.substring(0,count17),0,count17);
//            strvalName += "\n"+validate_spacee_for_chinese_character(valName.substring(count17,count32),0,count17);
//            strvalName += "\n"+validate_spacee_for_chinese_character(valName.substring(count32,count50),0,count17);
//            if (valName.length() < count68){
//                //strvalName += "\n" + valName.substring(50, valName.length());
//                //String name = valName.substring(51, valName.length());
//                //strvalName += validate_spacee_for_chinese_character(name);
//                strvalName += validate_spacee_for_chinese_character(valName.substring(count50,valName.length()),0,count17);
//            }
//        }
//        if (valName.length() > count68){
//            strvalName = validate_space(0,count17,valName,"");
//            strvalName += "\n"+validate_space(count17,count32,valName,"");
//            strvalName += "\n"+validate_space(count32,count50,valName,"");
//            strvalName += "\n"+validate_space(count50,count68,valName,"");
//        }
//        return strvalName;
//    }
//    public static String GetFormatForDescription(String valName,String terminalTypeVal){
//        String strvalName = "";
//        Log.i("CLay","valName.length()_"+valName.length());
//        Integer namelinespacecount = Query.GetLineSpaceCount(terminalTypeVal,21,0);
//        if (valName.length() <= namelinespacecount){
//            //String name = valName.substring(0, valName.length());
//            strvalName += validate_spacee_for_chinese_character(valName.substring(0, valName.length()),0,namelinespacecount);
//        }
//        Integer lines32pacecount = Query.GetLineSpaceCount(terminalTypeVal,32,0);
//        Integer linespacecount50 = Query.GetLineSpaceCount(terminalTypeVal,50,0);
//        Integer linespacecount68 = Query.GetLineSpaceCount(terminalTypeVal,68,0);
//        if (valName.length() > namelinespacecount){
//            //strvalName = validate_space(0,16,valName,"");
//            //strvalName = validate_space(0,17,valName,"");
//            strvalName = validate_spacee_for_chinese_character(valName.substring(0,namelinespacecount),0,namelinespacecount);
//            if (valName.length() < lines32pacecount){
//                //String name = valName.substring(17, valName.length());
//                //Log.i("fdf_name",name);
//                //strvalName += validate_spacee_for_chinese_character(name);
//                strvalName += validate_spacee_for_chinese_character(valName.substring(namelinespacecount,valName.length()),0,namelinespacecount);
//            }
//        }
//        Log.i("Fgf_valName_", String.valueOf(valName.length()));
//        if (valName.length() > lines32pacecount){
//            //strvalName = validate_space(0,17,valName,"");
//            strvalName = validate_spacee_for_chinese_character(valName.substring(0,namelinespacecount),0,namelinespacecount);
//            strvalName += "\n"+validate_spacee_for_chinese_character(valName.substring(namelinespacecount,namelinespacecount),0,namelinespacecount);
//            //strvalName += "\n"+validate_space(17,32,valName,"");
//            if (valName.length() < linespacecount50){
//                //String name = valName.substring(32, valName.length());
//                strvalName += validate_spacee_for_chinese_character(valName.substring(lines32pacecount,valName.length()),0,namelinespacecount);
//            }
//        }
//        if (valName.length() > linespacecount50){
////            strvalName = validate_space(0,17,valName,"");
////            strvalName += "\n"+validate_space(17,32,valName,"");
////            strvalName += "\n"+validate_space(32,50,valName,"");
//            strvalName = validate_spacee_for_chinese_character(valName.substring(0,namelinespacecount),0,namelinespacecount);
//            strvalName += "\n"+validate_spacee_for_chinese_character(valName.substring(namelinespacecount,lines32pacecount),0,namelinespacecount);
//            strvalName += "\n"+validate_spacee_for_chinese_character(valName.substring(lines32pacecount,linespacecount50),0,namelinespacecount);
//            if (valName.length() < linespacecount68){
//                //strvalName += "\n" + valName.substring(50, valName.length());
//                //String name = valName.substring(51, valName.length());
//                //strvalName += validate_spacee_for_chinese_character(name);
//                strvalName += validate_spacee_for_chinese_character(valName.substring(linespacecount50,valName.length()),0,namelinespacecount);
//            }
//        }
//        if (valName.length() > linespacecount68){
//            strvalName = validate_space(0,namelinespacecount,valName,"");
//            strvalName += "\n"+validate_space(namelinespacecount,lines32pacecount,valName,"");
//            strvalName += "\n"+validate_space(lines32pacecount,linespacecount50,valName,"");
//            strvalName += "\n"+validate_space(linespacecount50,linespacecount68,valName,"");
//        }
//        return strvalName;
//    }

    private static String validate_spacee_for_chinese_character(String name,Integer startNum,Integer endNum) {
        String strvalName = "";
        String spe = "";
        Boolean b_chk = isCJK(name);
        if (b_chk == true){
            //for (int i = 0; i < 13 - name.length(); i++) {
            //for (int i = 0; i < 15 - name.length(); i++) {
           //startNum_b818 Lily Bulb 百合__16_0_endNum17
           //          818 Lily Bulb

            if (name.length() == 16){

                endNum = endNum - 2;

                //startNum_b818 Lily Bulb 百合__16_0_endNum17
                //startNum_818 Lily Bulb 百合__16_0_endNum14
                //for (int i = 0; i < 14 - name.length(); i++) {
                strvalName = name.substring(0,14);
                for (int i = startNum; i < endNum - name.substring(14,name.length()).length(); i++) {
                    //for (int i = 0; i < 16 - name.length(); i++) {
//            for (int i = 0; i < 17 - name.length(); i++) {
                    spe += " ";
                }
                strvalName += "\n"+name.substring(14,name.length()) + spe;
            }else {
                endNum = endNum - 3;

                //startNum_b818 Lily Bulb 百合__16_0_endNum17
                //startNum_818 Lily Bulb 百合__16_0_endNum14
                //for (int i = 0; i < 14 - name.length(); i++) {
                //spe = "";
                for (int i = startNum; i < endNum - name.length(); i++) {
                    //for (int i = 0; i < 16 - name.length(); i++) {
//            for (int i = 0; i < 17 - name.length(); i++) {
                    spe += " ";
                }
                strvalName = name + spe;
            }
        }else {
//            //for (int i = 0; i < 17 - name.length(); i++) {
//            spe = "";
//            for (int i = startNum; i < endNum - name.length(); i++) {
////            for (int i = 0; i < 17 - name.length(); i++) {
//                //spe += " ";
//                //spe += "&nbsp;";
//                //spe += "\t";
//                //spe += " ";
//                //spe += Html.fromHtml("&nbsp;");
//                //spe += "u";
//                //spe += " ";
////                Log.i("endNum__","endNum__"+endNum);
////                Log.i("endNum__","name.length()__"+name.length());
//                Log.i("endNum__","name.length()__"+i);
//                Log.i("endNum__","name.length()__"+endNum);
//                Log.i("endNum__","name.length()__"+name.length());
//                if (i == (endNum - name.length()) - 1) {
//                    spe += "-";
//                    Log.i("endNum__","endNum_1_"+spe);
//                }else {
////                    spe += "u";
//                    spe += "u";
//                }
//
//            }
//            strvalName = name + spe;

//            spe = "";
//            for (int i = 0; i < 17 - (name.length()+1); i++) {
////                spe += " ";
////                spe += "u";
//                spe += "z";
//            }

//            strvalName = name + spe +"-";

//            spe = "";
//            for (int i = 0; i < 17 - (name.length()+1); i++) {
//                spe += " ";
//            }
//            if(name.length()< 5){
//                spe += " ";
//            }
//            strvalName = name + spe +"-";

            spe = "";
            for (int i = 0; i < 17 - (name.length()+1); i++) {
                spe += " ";
//                spe += "1";
            }

            strvalName = name + spe +"-";
        }
        strvalName = "\n"+ strvalName;
        Log.i("fdf_nstrvalName",strvalName);
        return strvalName;
    }

//    private static String validate_spacee_for_chinese_character(String name) {
//        Log.i("fdf_named",name);
//        String strvalName = "";
//        String spe = "";
//        Boolean b_chk = isCJK(name);
//        if (b_chk == true){
//            //for (int i = 0; i < 13 - name.length(); i++) {
//            //for (int i = 0; i < 15 - name.length(); i++) {
//            //for (int i = 0; i < 14 - name.length(); i++) {
//            for (int i = 0; i < 16 - name.length(); i++) {
////            for (int i = 0; i < 17 - name.length(); i++) {
//                spe += "C";
//            }
//        }else {
//            for (int i = 0; i < 17 - name.length(); i++) {
////            for (int i = 0; i < 17 - name.length(); i++) {
//                spe += "N";
//            }
//        }
////        isCJK(name);
////        final int length = name.length();
////        for (int offset = 0; offset < length; ) {
////            final int codepoint = Character.codePointAt(name, offset);
////
////            // use codepoint here
////
////            offset += Character.charCount(codepoint);
////        }
//        strvalName += "\n"+ name + spe;
//        Log.i("fdf_nstrvalName",strvalName);
//        return strvalName;
//    }
    public static boolean isCJK(String str){
        int length = str.length();
        for (int i = 0; i < length; i++){
            char ch = str.charAt(i);
            Character.UnicodeBlock block = Character.UnicodeBlock.of(ch);
            if (Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS.equals(block)||
                    Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS.equals(block)||
                    Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A.equals(block)){
                return true;
            }
        }
        return false;
    }
    private static String validate_space(int start,int val,String valName,String status) {
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

    public static void getHeaderFooterValues() {
        Cursor c = DBFunc.Query("SELECT HeaderValue,FooterValue,Options,ImageStatus,DateTime,Logo FROM ReceiptEditor", true);
        if (c != null) {
            if (c.moveToNext()) {
                if (!c.isNull(0)) {
                    HeaderValue = c.getString(0);
                    FooterValue = c.getString(1);
                    Options = String.valueOf(c.getInt(2));
                    ImageStatus = String.valueOf(c.getInt(3));
                    ImageLogo = String.valueOf(c.getInt(5));
                }
            }
            c.close();
        }
    }
    //public void volleySubmitSales(double amount, double cost, final String bill_payment_type,String retail_ID,String company_code,String url, String sale_staus) {
//    private static void volleySubmitSales(Context context,final String bill_payment_type, String retail_ID, String company_code,
//                                          String url, String sale_staus, final String BillNo, Double Changeamount, String PaymentType,
//                                          String PaymentAmount, String total_amt) {
//        //final String receipt_no = _bill_no;
//        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
//        Date date = new Date();
//        Log.i("__dateat(date)",dateFormat.format(date));
//        //final String receipt_no = dateFormat.format(date) + bill_no;
//        final String receipt_no = BillNo;
//
//        double chgamt = 0.0;
//        if (Double.valueOf(Changeamount) < 0.0) {
//            chgamt = (-1) * Double.valueOf(Changeamount);
//        }
//        //double paytotal = Double.valueOf(_tot_a) - chgamt;
//        double paytotal = Double.valueOf(PaymentAmount);
//        RequestQueue queue = Volley.newRequestQueue(context);
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
//            jsonObject.put(DeclarationConf.submitSalesSalesStatus, sale_staus);
//            //jsonObject.put(DeclarationConf.submitSalesMemberID, __volley_submit_sale_memberID);
//            jsonObject.put(DeclarationConf.submitSalesMemberID, "");
//
//            if (sale_staus.equals("VOID")){
//                s_v = (-1);
//            }else{
//                s_v = 1;
//            }
//            //if (Double.valueOf(__volley_tax_rate) > 0.0) {
//            if (Double.valueOf(total_amt) > 0.0) {
//                //jsonObject.put(DeclarationConf.submitSalesSalesTaxTtl, s_v * Double.valueOf(volley_tax_ttl());
//                jsonObject.put(DeclarationConf.submitSalesSalesTaxTtl, s_v * Double.valueOf(total_amt));
//            }else{
//                jsonObject.put(DeclarationConf.submitSalesSalesTaxTtl, 0.0);
//            }
//            //jsonObject.put(DeclarationConf.submitSalesSalesRounding, Double.valueOf(__volley_round));
//            jsonObject.put(DeclarationConf.submitSalesSalesRounding, Double.valueOf(0.0));
//            jsonObject.put("SalesTotalAmount", s_v * Double.valueOf(paytotal));
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        JSONObject sales_payment_details = new JSONObject();
//        JSONArray sales_payement_details_array = new JSONArray();
//        String option_val = "";
//        String option_id = "";
//        try {
//            if (bill_payment_type.length() > 0) {
//                option_val = bill_payment_type.toUpperCase();
//            }else{
//                option_val = PaymentType.toUpperCase();
//            }
//            Cursor config_option = DBFunc.Query("SELECT ID FROM payment WHERE UPPER(Name) = '" + option_val +"'", true);
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
//                sales_payment_details.put("strPayment", PaymentType.toUpperCase());
//            }
//            //sales_payment_details.put("SalesPayTtl", String.format("%.2f", Double.valueOf(_tot_a)));
//            sales_payment_details.put("SalesPayTtl", s_v * Double.valueOf(total_amt));
//
//            sales_payment_details.put("SalesBalTtl", s_v * Double.valueOf(paytotal));
//            //sales_payment_details.put("ChangeAmount", String.format("%.2f", Double.valueOf(chgamt)));
//            if (sale_staus.equals("VOID")){
//                sales_payment_details.put("ChangeAmount", 0.0);
//            }else {
//                sales_payment_details.put("ChangeAmount", Double.valueOf(chgamt));
//            }
//            sales_payement_details_array.put(sales_payment_details);
//            //sales_multiple_payement.put(sales_payment_details);
//            jsonObject.put("SalesPayments",sales_payement_details_array);
//            //jsonObject.put("SalesPayments", sales_multiple_payement);
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
//                        //sales_multiple_payement = new JSONArray();
//                        // response code
//                        String xmlString = response;
//                        //{"PaymentMethods":[{"ID":10,"Name":"CASH","Full":"CASH","Display":"Y"}]}
//                        //updatePayment(finalCompany_code1, finalOption_id,bill_payment_type.toUpperCase(), finalUrl);
//                        Log.i("ddd_error", xmlString);
//                        Document xmlparse = null;
//                        Document parse = APIActivity.XMLParseFunction(xmlString, xmlparse);
//                        for (int i = 0; i < parse.getElementsByTagName("submitSalesResult").getLength(); i++) {
//                            submitSalesResult = (parse.getElementsByTagName("submitSalesResult").getLength() > 0)
//                                    ? parse.getElementsByTagName("submitSalesResult").item(i).getTextContent() : " ";
//                        }
//                        DBFunc.ExecQuery("UPDATE BillPayment SET SaleSync = 1 WHERE BillNo = " + BillNo, false);
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
//                String temp = Query.SubmitSales(finalCompany_code,jsonObject);
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

    public static void cancelBill(String BillNo,Context context,Integer sales_id) {
        String status = Constraints.CANCEL;
        String sql_searchbillno = "SELECT ID FROM SALES WHERE BillNo = '"+BillNo+"'";
        Cursor csql_searchbillno = DBFunc.Query(sql_searchbillno,false);
        if (csql_searchbillno != null) {
            if (csql_searchbillno.moveToNext()){
                sales_id = csql_searchbillno.getInt(0);
            }
        }
        DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                System.currentTimeMillis(), DBFunc.PurifyString("CashLayoutActivity " +"sql_searchbillno-"+BillNo+"-"+sql_searchbillno+"-"+sales_id));

        if (sales_id == 0) {

            Query.UpdateDetailsBillProductStatusByBillNo(BillNo,status,System.currentTimeMillis());

            //status = "CANCEL";
            String chk_jerifood = Query.GetOptions(24);
            if (chk_jerifood.equals("0")) {
                Integer billID = Query.findLatestID("BillNo", "Bill", false);
                String BNo = Query.findBillNoByBillID(billID);
                if (BNo.equals(BillNo)) {
                    Query.CreateNewBillAndDetailsBillProduct();
                }
            }
        }else {


            status = Constraints.VOID;

            //VOID
            Query.UpdateSalesStatusByBillNo(BillNo,status);

            //Query.UpdateBillPayment();

            Query.UpdateDetailsBillProductStatusByBillNo(BillNo,status,System.currentTimeMillis());
        }

        String chk_jerifood = Query.GetOptions(24);
        if (chk_jerifood.equals("0")) {

            Intent intent = new Intent(context, TransactionDetailsActivity.class);
            intent.putExtra("BillNo", BillNo);
            context.startActivity(intent);
            ((Activity) context).finish();
        }


        String Date = Query.GetDateFormart55();
        Query.UpdateStatusBillList(BillNo,status,Date,System.currentTimeMillis()); // Update BillList

        //Update Bill
        String updBill = "UPDATE Bill SET CloseDateTime = "+System.currentTimeMillis() + " WHERE TransNo = '"+BillNo+"' ";
        DBFunc.ExecQuery(updBill,false);

        String updatesql = "UPDATE BillPayment SET STATUS = '"+status+"' WHERE BillNo = '"+BillNo+"' ";
        DBFunc.ExecQuery(updatesql,false);

        DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                System.currentTimeMillis(), DBFunc.PurifyString("Update-CashLayoutActivity " +   updatesql));

        //Cancel OR VOID
        Query.UpdateSalesStatusByBillNo(BillNo,status);

        String updatesalesisql = "UPDATE SalesItem SET STATUS = '"+status+"' WHERE BillNo = '"+BillNo+"' ";
        DBFunc.ExecQuery(updatesalesisql,false);


        Query.UpdatStockAgentByStatus(BillNo);


        if (chk_jerifood.equals("0")) {

            String url = "";
            Cursor CPossys = Query.GetURLAndCodeFromPossys();
            if (CPossys != null) {
                while (CPossys.moveToNext()) {
                    url = CPossys.getString(2);
                }
                CPossys.close();
            }

//                                    Bitmap bitmap__ = null;
//                                    bitmap__ = Query.GetLogPrint();

            if (url != null) {

                if (sales_id > 0) { // if Edit Bill from checkout not sync
                    //SyncActivity.volleySubmitSales(context, billNo,"",amount, "Cancel", ENUM.CANCEL, "Normal");
                    SyncActivity.ResyncOrNormal(context, BillNo, "", "Normal", "");
                }
            }

        }

//        CheckOutActivity.sldIDArr.clear();
//        RecyclerViewAdapter.hashValues.clear();
    }
}
