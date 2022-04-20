package com.dcs.myretailer.app.Activity;

import static com.dcs.myretailer.app.Activity.TransactionDetailsActivity.resourceVal;
import static com.dcs.myretailer.app.Activity.TransactionDetailsActivity.sldTaxID;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.multidex.MultiDex;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.dcs.myretailer.app.Allocator;
import com.dcs.myretailer.app.Checkout.PaymentCashSuccesActivity;
import com.dcs.myretailer.app.DialogBox;
import com.dcs.myretailer.app.Logger;
import com.dcs.myretailer.app.Model.BillBOC;
import com.dcs.myretailer.app.Cashier.ButtonAdapter;
import com.dcs.myretailer.app.Cashier.DeclarationConf;
import com.dcs.myretailer.app.Cashier.EditProductSheetFragment;
import com.dcs.myretailer.app.Cashier.ItemDiscountActivity;
import com.dcs.myretailer.app.Cashier.MainActivity;
import com.dcs.myretailer.app.Cashier.RecyclerViewAdapter;
import com.dcs.myretailer.app.Adapter.CheckOutAdapter;
import com.dcs.myretailer.app.Checkout.ManageBillFragment;
import com.dcs.myretailer.app.Checkout.PaymentTypes;
import com.dcs.myretailer.app.Checkout.PaymentTypesCheckoutAdapter;
import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Model.BillJeripay;
import com.dcs.myretailer.app.Model.BillJeripayDetails;
import com.dcs.myretailer.app.Model.BillMercatus;
import com.dcs.myretailer.app.Model.BillMercatusDetails;
import com.dcs.myretailer.app.Model.BillMercatusMallLoyaltyDetails;
import com.dcs.myretailer.app.Model.BillMercatusPayment;
import com.dcs.myretailer.app.Model.BillMercatusVouchers;
import com.dcs.myretailer.app.MyRecyclerViewCheckout;
import com.dcs.myretailer.app.PaymentSendDataFormat.AscanPaymentFormat;
import com.dcs.myretailer.app.PaymentSendDataFormat.CheckPaymentApp;
import com.dcs.myretailer.app.PaymentSendDataFormat.JeripayPaymentFormat;
import com.dcs.myretailer.app.Printer.KitchenPrinter;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.Report.MathUtil;
import com.dcs.myretailer.app.ScreenSize.CheckoutActivityScreenSize;
import com.dcs.myretailer.app.Setting.ItemDetail;
import com.dcs.myretailer.app.Setting.StrTextConst;
import com.dcs.myretailer.app.ViewModel;
import com.dcs.myretailer.app.databinding.ActivityCheckOutBinding;
import com.pax.dal.IDAL;
import com.pax.globalpay.opensdk.ITransAPI;
import com.pax.globalpay.opensdk.SaleMsg;
import com.pax.globalpay.opensdk.TransAPIFactory;
import com.pax.neptunelite.api.NeptuneLiteUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import cn.pedant.SweetAlert.SweetAlertDialog;

//import com.dcs.myretailer.app.Cashier.RecyclerViewNoImageAdapter;

public class CheckOutActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {
//    ImageView add_member_icon;
    public static String status = "0";
    private static CheckOutAdapter customAdapter = null;
    String productSelectedID,productPrice = "";
    Integer productTotalCount = 0;
    public static String disID = "0";
    public static String billDisID = "0";
    public static String ItemPriceTotal = "0";
    public static String disName = "";
    public static String disOptions = "";
    public static String disTypeName = "";
    public static String disType = "";
    public static String disValue = "";
    public static String billDisName = "";
    public static String billDisOptions = "";
    public static String billDisTypeName = "";
    public static String billDisType = "";
    public static String billDisValue = "";
    public static String disIDAmt = "0";
    public static String disNameAmt = "";
    public static String disOptionsAmt = "";
    public static String disTypeNameAmt = "";
    public static String disTypeAmt = "";
    public static String disValueAmt = "";
    public static String billDisIDAmt = "0";
    public static String billDisNameAmt = "";
    public static String billDisOptionsAmt = "";
    public static String billDisTypeNameAmt = "";
    public static String billDisTypeAmt = "";
    public static String billDisValueAmt = "";
//    ListView checkOutListView;
    //RecyclerView recyclerViewcheckOut;
    LinearLayoutManager layoutManager;
    MyRecyclerViewCheckout adapter;
    List<ViewModel> model;
    public static String str_percentage,str_percentage_value = "";
    public static String str_value = "";
    public static String strheader,str_info,strreceiptdatetime,strfooter,ClosedSales = "";
    String HeaderValue,FooterValue,Options,ImageStatus = "";
    public static Integer paymentID = 1;
    public static String paymentName = "cash";
    Integer paymentAmount = 0;
    Integer change = 0;
    public static int sale_id = 0;
    public static int bill_details_id = 0;
    public static int bill_receipt_id = 0;
    public static Double amount = 0.0;
    public static Double totalAmount = 0.0;
    public static Double staticRound = 0.0;
    Integer exising_id = 0;
    public static ArrayList<String> IDArr = new ArrayList<String>();
    private static final DecimalFormat REAL_FORMATTER = new DecimalFormat("0.00");
    private static final int CAMERA_REQUEST = 1888;
    String payment_type_name = "";
    String bankName = "";
    String chkBillNo = "";
    static final double EPSILON = 0.000001;
   // DateFormat dateFormat = new SimpleDateFormat("hh.mm aa");
   // DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy hh.mm aa");
    //public static DateFormat dateFormat55 = new SimpleDateFormat("dd MM yyyy , hh:mm aa");
    public static DateFormat dateFormat55 = new SimpleDateFormat("dd MMM yyyy  hh:mm aa");
    public static DateFormat dateFormat555 = new SimpleDateFormat("hh");
    //public static DateFormat dateFormat55 = new SimpleDateFormat("dd MM yyyy  hh:mm aa");
    public static Double sub_total = 0.0;
    public static Double amt_exclusive = 0.0;
    public static String BillNo = "";
    static String Status = "";
    static String Ezlink = "";
    static String StatusSALES = "";
    private static IDAL dal;
    private static Context appContext;
    public static Double round = 0.0;
    static Double total_tax = 0.0;
    static String str_taxname = "";
    String str_bill_no = "";
    public static ArrayList<String> sldQtyArr = new ArrayList<String>();
    public static ArrayList<String> sldNameArr = new ArrayList<String>();
    public static ArrayList<String> sldCategoryIDArr = new ArrayList<String>();
    public static ArrayList<String> sldCategoryNameArr = new ArrayList<String>();
//    public static ArrayList<String> sldTaxID = new ArrayList<String>();
//    public static ArrayList<String> sldCTaxName = new ArrayList<String>();
    public static ArrayList<String> sldCTaxAmount = new ArrayList<String>();
//    public static ArrayList<String> sldCTaxType = new ArrayList<String>();
//    public static ArrayList<String> sldCTaxRate = new ArrayList<String>();
    public static ArrayList<String> sldDiscountName = new ArrayList<String>();
    public static ArrayList<String> sldDiscountType = new ArrayList<String>();
    public static ArrayList<String> sldDiscountValue = new ArrayList<String>();
    public static ArrayList<String> sldDetailsBillID = new ArrayList<String>();
    public static ArrayList<String> sldOpenPriceStatus = new ArrayList<String>();
    public static ArrayList<String> sldRemarks = new ArrayList<String>();
    public static ArrayList<String> sldRemarksNoSubstring = new ArrayList<String>();
    public static ArrayList<Integer> billdetailsPID = new ArrayList<Integer>();
//    public static ArrayList<String> sldTaxType = new ArrayList<String>();
    public static String EditFragmentOpenPrice = "0";
    public static String EditFragmenttotalQty = "0";
    public static String EditFragmentName = "0";
    public static String EditFragmentProductID = "0";
    public static String Remarks = "";
//    public static String ID = "";
    public static String EditFragmentPrice = "0";
    public static String bill_type = "0";
    public static ArrayList<String> sltPriceTotalArr = new ArrayList<String>();
    public static ArrayList<String> sltPriceTotalArrEach = new ArrayList<String>();
    public static ArrayList<String> sldIDArr = new ArrayList<String>();
    public static ArrayList<Integer> sldIDDetailsBill = new ArrayList<Integer>();
    public static ArrayList<String> sldItemDisArr = new ArrayList<String>();
    public final String POPUP_LOGIN_TITLE="Edit Product";
    public final String POPUP_LOGIN_TEXT="Please fill quantity that you want to.";
    public final String EMAIL_HINT="--Email--";
    public final String PASSWORD_HINT="--Password--";
//    ImageView btn_add_discount_checkout;
//    ImageView btn_remove_discount_checkout;
//    public static TextView txt_discount_checkout;
//    public static TextView bill_discount_name;
    public static Double discount_amount = 0.0;
    public static Double item_dis_amt = 0.0;
    public static ArrayList<PaymentTypes> paymentTypesClass = new ArrayList<PaymentTypes>();
//    LinearLayout multiple_payment,roundAdj_layout;
//    TextView text_payment_cash = null;
    public static ArrayList<Integer> ViewsldQtyArr = new ArrayList<Integer>();
    public static Double service_charges = 0.0;
    public static ArrayList<String> ChkOut_sldNameArr = new ArrayList<String>();
    public static ArrayList<Integer> ChkOut_sldQtyArr = new ArrayList<Integer>();
    public static ArrayList<String> ChkOut_sltBillDisArr = new ArrayList<String>();
    public static ArrayList<String> ChkOut_sltPriceTotalArr = new ArrayList<String>();
    public static ArrayList<String> ChkOut_sldIDArr = new ArrayList<String>();
    public static ArrayList<String> ChkOut_intTableNo = new ArrayList<String>();
    public static ArrayList<String> ChkOut_vchQueueNo = new ArrayList<String>();
    public static ArrayList<String> ChkOut_slddisID = new ArrayList<String>();
    public static ArrayList<String> ChkOut_slddisName = new ArrayList<String>();
    public static ArrayList<String> ChkOut_slddisTypeName = new ArrayList<String>();
    public static ArrayList<String> ChkOut_slddisType = new ArrayList<String>();
    public static ArrayList<String> ChkOut_slddisValue = new ArrayList<String>();
    public static SimpleDateFormat sdf;
    //public static Integer billFontSize = 16;
    //public static Integer BillID = 1;
    String balance_title = "AAAA";
//    TextView checkoutTitleBillNo;
    //LinearLayout lay_payment_method;
//    LinearLayout lay_payment_header;
    //LinearLayout payment_type_recyclerview_id_layout_id;

//    public static RecyclerView myrv;
    public static PaymentTypesCheckoutAdapter myAdapter;

    public static String tbl_name = "0";
    public static int tax_Seq = 0;
    String str_inclusive = "0";
    String str_exclusive = "0";
    Double amt_inclusive = 0.0;
    //public static Double amt_tax = 0.0;
    String str_inc_taxname = "0";
    String str_exc_taxname = "0";
    String str_inc_taxrate = "0";
    String str_exc_taxrate = "0";
    Handler mHandler;
    public static String St = "0";
    public static String old_pricee = "0";
//    TextView round_adj_value;
    public static ArrayList<Double> CashValueArr = new ArrayList<Double>();
    public static ArrayList<String> CashValuePaymentNameArr = new ArrayList<String>();
    public static ArrayList<Integer> CashValuePaymentIDArr = new ArrayList<Integer>();
    public static ArrayList<String> CashValuePaymentRemarksArr = new ArrayList<String>();
    public static String  CashValue =  "0.0";
    public static String CashValuePaymentName =  "";
    public static Integer CashValuePaymentID =  0;
    public static String CashValuePaymentRemarks =  "";
//    ScrollView checkOutScrollView;
    public static ActivityCheckOutBinding binding = null;
    public static String error = "0";

    public static void updateMediaButtons() {
        updateCheckoutAdapter(appContext);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MultiDex.install(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_check_out);

        //ScreenSize
        new CheckoutActivityScreenSize(this,binding);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        myToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_close_white));
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainPage();
            }
        });

        setTitle("Checkout Bill");

        sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");

        Intent intent = getIntent();
        BillNo = intent.getStringExtra("BillNo");
        Status = intent.getStringExtra("Status");
        Ezlink = intent.getStringExtra("Ezlink");
        StatusSALES = intent.getStringExtra("StatusSALES");


        //appContext = getApplicationContext();
        appContext = CheckOutActivity.this;
        dal = getDal();

        resourceVal = getResources();

        binding.checkoutOrderSummary.roundAdjLayout.setVisibility(View.GONE);
        binding.checkoutOrderSummary.btnAddDiscountCheckout.setOnClickListener(this);
        binding.checkoutOrderSummary.btnRemoveDiscountCheckout.setOnClickListener(this);
        binding.checkoutOrderSummary.addMemberIcon.setOnClickListener(this);

        PaymentTypesCheckoutAdapterShowFun(appContext,BillNo,resourceVal);

        if (Status.equals("TabFragmentPercentage_CheckoutForItem")) {

            DiscountFunForPercentage(appContext);
        }else if (Status.equals("TabFragmentAmount_CheckoutForItem")) {

            DiscountFunForAmount(appContext);
        }

        if (Double.valueOf(CashLayoutActivity.CashValue) > 0.0) {

            binding.checkoutOrderSummary.multiplePayment.setVisibility(View.VISIBLE);

            if (Status.equals("CashLayoutActivity")) {
                CashValuePaymentName = "";
                CashValuePaymentName = "";
                CashValuePaymentID = 0;
                CashValuePaymentRemarks = "";
                CashValue = "";
                for (int check = 0 ; check < CashValuePaymentIDArr.size(); check ++){
                    if (Double.valueOf(CashValueArr.get(check)) > 0.0) {
                        CashValuePaymentName += "\n" + "\n" + CashValuePaymentNameArr.get(check);
                        CashValue += "\n" + "\n" + "$" + String.format("%.2f", Double.valueOf(CashValueArr.get(check)));
                    }

                }
                binding.checkoutOrderSummary.textPaymentCash.setText(CashValuePaymentName);
                binding.checkoutOrderSummary.textPaymentCashAmount.setText(CashValue);
            }
            if (Status.equals("CardPayment")) {
                CashValuePaymentName = "";
                CashValue = "";
                for (int check = 0 ; check < CashValuePaymentNameArr.size(); check ++){
                    if ( Double.valueOf(CashValueArr.get(check)) > 0.0) {
                        CashValuePaymentName += "\n" + "\n" + CashValuePaymentNameArr.get(check);
                        CashValue += "\n" + "\n" + "$" + String.format("%.2f", Double.valueOf(CashValueArr.get(check)));
                    }
                }
                binding.checkoutOrderSummary.textPaymentCash.setText(CashValuePaymentName);
                binding.checkoutOrderSummary.textPaymentCashAmount.setText(CashValue);
            }
        }

        str_info = "";
        paymentID = 1;
        paymentName = "cash".toUpperCase();

        if (!Status.equals("Bill")) {

            if (sldIDArr.size() > 0) {
//                DeleteBill();
//                SaveBill();
            } else {
                if (!Status.equals("Main")) {

                    ClearDataVariableFun();

                }
            }
        }

        getDetailsBillProductAll(BillNo);

        updateTotalItemsFun();

        String str_tblName = "";
        if (!MainActivity.tbl_name.equals("0")) {
            str_tblName = " ( "+ MainActivity.tbl_name +" ) ";
            tbl_name = MainActivity.tbl_name;
        }
        binding.checkoutInfo.checkoutTitleBillNo.setText("Bill #"+BillNo+str_tblName);

        updateCheckoutAdapter(appContext);

        updateDiscountFun();

        updateSubtotalFun();

        updateTotalAmtRoundAmtDisAmtFun();

        if (Status.equals("PaymentTypesCheckoutAdapter")){

            PaymentTypesCheckoutAdapterFun(round);
        }

        if (Status.equals("CardPayment")){
            CardPaymentFun(round,Ezlink);
        } else {
            //Add Else Statement at V3.3.24
            binding.checkoutPaymentList.paymentTypeRecyclerviewId.setEnabled(true);

            customAdapter = new CheckOutAdapter(getApplicationContext(),BillNo,
                    sldQtyArr, sldNameArr, sltPriceTotalArrEach, sldIDArr , sldItemDisArr,
                    sldDiscountName, sldDiscountType, sldDiscountValue,sldOpenPriceStatus,sldRemarks,billdetailsPID);
//                sldQtyArr, sldcheckOutListViewNameArr, sltPriceTotalArr, sldIDArr , sldItemDisArr, sldDiscountName, sldDiscountType, sldDiscountValue);

//        binding.buttons.checkOutListView
            binding.checkoutInfo.checkOutListView.setAdapter(customAdapter);

            checkOutListOnClickFun(binding.checkoutInfo.checkOutListView);
        }
    }

    public static void updateDiscountFun() {
        item_dis_amt = 0.0;
        for (int i = 0 ; i < sldItemDisArr.size() ; i++){
            item_dis_amt += Double.valueOf(sldItemDisArr.get(i));
        }
        if (item_dis_amt > 0.0) {
            //bill_item_dis_layout.setVisibility(View.VISIBLE);
            binding.checkoutOrderSummary.billItemDisLayout.setVisibility(View.GONE);
            binding.checkoutOrderSummary.txtItemDiscountCheckout.setVisibility(View.VISIBLE);
            binding.checkoutOrderSummary.txtItemDiscountCheckout.setText("$" + String.valueOf(String.format("%.2f", item_dis_amt)));
            //txt_item_discount_checkout.setTextSize(MainActivity.billFontSize);
        }
    }

    public static void updateTotalAmtRoundAmtDisAmtFun() {
        if (CashLayoutActivity.BillDiscountValue > 0.0){
            discount_amount = CashLayoutActivity.BillDiscountValue;
            //Show BillDiscount Name And Amount
            Query.ShowBillDiscountAtCheckoutPage();

        }else {
            discount_amount = 0.0;
            discount_amount = Query.CalculationBillDiscountAmount(Status, sub_total, str_percentage_value, binding.checkoutOrderSummary.txtDiscountCheckout,
                    str_value);
        }
        discount_amount = Double.valueOf(String.format("%.2f", Double.valueOf(discount_amount)));
        amount =  sub_total - discount_amount; // Add Bill Discount into Subtotal
        //Service Charges
        Integer chk_serviceCharges = Query.CheckServiceCharges();
        service_charges = 0.0;
        if (chk_serviceCharges == 1) {
            service_charges = Query.CalculationServiceCharges(binding.checkoutOrderSummary.serviceChargesLayout,
                    binding.checkoutOrderSummary.serviceChargesname, amount, binding.checkoutOrderSummary.serviceChargesValue);
            amount = amount + service_charges;
        }

        //Get Exclusive
        amt_exclusive = Query.CalculationTaxFunction(amount,binding.checkoutOrderSummary.incTaxLayout,
                binding.checkoutOrderSummary.incTaxname,
                binding.checkoutOrderSummary.taxLayout,binding.checkoutOrderSummary.taxname,binding.checkoutOrderSummary.taxValue);

        if (amt_exclusive != 0.0){
            amount = amount + amt_exclusive;
        }

        totalAmount = amount;

        //amount = 66.63;
        //Get Round Adj
        Double ammmt = MathUtil.Truncate(amount,MainActivity.decimalpoint, 0);
        double round = MathUtil.CalculateRound(ammmt, MainActivity.decimalpoint, MainActivity.roundtype);
        round = Double.valueOf(String.format("%.2f", Double.valueOf(round)));

        staticRound = round;
        //        //Get Round Adj
        //        Double ammmt = MathUtil.Truncate(amount,MainActivity.decimalpoint, 0);
        //        double round = MathUtil.CalculateRound(ammmt, MainActivity.decimalpoint, MainActivity.roundtype);
        //        staticRound = round;
        //Add Round into Total Amount

        amount = amount + round;


        binding.checkoutOrderSummary.roundAdjValue.setText("$" + String.format("%.2f", Double.valueOf(round)));
        if ( round >= 0.0) {
            binding.checkoutOrderSummary.roundAdjValue.setText("$" + String.format("%.2f", Double.valueOf(round)));
        }else {
            Double r_round = round * (-1);
            binding.checkoutOrderSummary.roundAdjValue.setText("-"+"$" + String.format("%.2f", Double.valueOf(r_round)));
        }


        //Log.i("TAGG","exclusive_tax_total_"+exclusive_tax_total);
        //amount = amount + exclusive_tax_total;

        //Add Total Amount
        //checkout_total.setText("$"+ String.format("%.2f", Double.valueOf(amount)));
        binding.checkoutOrderSummary.checkoutTotal.setText("$"+ String.format("%.2f", Double.valueOf(totalAmount)));
        //checkout_total.setTextSize(MainActivity.billFontSize);
    }

    public static void updateSubtotalFun() {
        sub_total = 0.0;
//        for (int i = 0 ; i < sltPriceTotalArr.size() ; i++){
//            sub_total += Double.valueOf(sltPriceTotalArr.get(i));
//        }

        //SubTotal
        sub_total = Query.GetSubTotalValueFromDetailsBillProduct(BillNo);

        String totQtyaa = Query.GetPromotionPriceByBillNo(BillNo,"0");

        if (Double.valueOf(totQtyaa) != 0.0) {
            sub_total = sub_total + Double.valueOf(totQtyaa);
        }
        //Get PromoTotalValue from PLUPromo
        Double PromoTotalValue = Query.GetPromoTotal(BillNo);

        if (PromoTotalValue != 0.0){
            sub_total = sub_total + PromoTotalValue;
        }
//        //Item discount Add into Subtotal
//        if (item_dis_amt > 0.0){
//            sub_total = sub_total -item_dis_amt;
//        }
//        //Add Exclusive Tax
//        sub_total = sub_total + exclusive_tax_total;
        // SubTotal
        binding.checkoutOrderSummary.checkoutSubtotal.setText("$"+String.format("%.2f", sub_total)); // SubTotal
    }


    public static void updateTotalItemsFun() {
        Integer totItm = 0;
        for (int i = 0; i < sldQtyArr.size(); i ++){
            totItm += Integer.parseInt(sldQtyArr.get(i));
        }
        if (totItm == 0 || totItm == 1){
            binding.checkoutOrderSummary.totitmHeaderCheckout.setText("Total Item");
        }else {
            binding.checkoutOrderSummary.totitmHeaderCheckout.setText("Total Items");
        }
        binding.checkoutOrderSummary.checkoutTotalItem.setText(String.valueOf(totItm));
    }

    private void CheckoutScrollView() {
        String terminalTypeVal = Query.GetDeviceData(Constraints.TERMINAL_TYPE);
        if (terminalTypeVal.equals(Constraints.INGENICO)) {
//            checkOutScrollView.setMinimumWidth(ENUM.checkOutScrollViewWidthIngenico);
//            checkOutScrollView.setMinimumHeight(ENUM.checkOutScrollViewWidthIngenico);

//            LinearLayout.LayoutParams scrollParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//            ViewGroup.LayoutParams.WRAP_CONTENT);
//            checkOutScrollView.setLayoutParams(scrollParams);
//            LinearLayout.LayoutParams scrollParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT, 2.0f);
//            checkOutScrollView.setLayoutParams(scrollParams);

//            checkOutScrollView.setLayoutParams(new RelativeLayout.LayoutParams(
//                    LayoutParams.FILL_PARENT, 350));
        }
//        ScrollView home_scroll = new ScrollView(this);
//        LinearLayout home_linear = new LinearLayout(this);
//        home_linear.setOrientation(LinearLayout.VERTICAL);
//
//        home_scroll.addView(home_linear, new LinearLayout.LayoutParams(480, 800));
    }

    public static void PaymentTypesCheckoutAdapterShowFun(Context appContext,String BillNo, Resources resourceVal) {

            getPaymentsAll();

            final String cancel_status = Query.getCancelByBillNo(BillNo);

            if (cancel_status.toUpperCase().equals("CANCEL") || cancel_status.toUpperCase().equals("VOID")) {
                //lay_payment_method.setVisibility(View.GONE);
                binding.checkoutPaymentList.layPaymentHeader.setVisibility(View.GONE);
                //payment_type_recyclerview_id_layout_id.setVisibility(View.GONE);
            }else {
                //lay_payment_method.setVisibility(View.VISIBLE);
                binding.checkoutPaymentList.layPaymentHeader.setVisibility(View.VISIBLE);
                //payment_type_recyclerview_id_layout_id.setVisibility(View.VISIBLE);

            }
            myAdapter = new PaymentTypesCheckoutAdapter(appContext,
                    paymentTypesClass, "product", bill_type, resourceVal);

            GridLayoutManager gridLayoutManager = new GridLayoutManager(appContext, 4);
            binding.checkoutPaymentList.paymentTypeRecyclerviewId.setLayoutManager(gridLayoutManager);
            binding.checkoutPaymentList.paymentTypeRecyclerviewId.setAdapter(myAdapter);
            myAdapter.notifyDataSetChanged();
    }

    public  void PaymentTypesCheckoutAdapterFun(double round) {
        //Double card_amt = amount - Double.valueOf(CashLayoutActivity.CashValue);
        Double cartAmt = 0.0;
        for (int i = 0 ; i < CashValueArr.size(); i++) {
            cartAmt += CashValueArr.get(i);
        }
        Double card_amt = amount - cartAmt;

        if (CashValueArr.size() == 0) {
            card_amt = card_amt - PaymentTypesCheckoutAdapter.chkbilldiscount;
        }

        String getRoundActivateStatus = Query.GetRoundActivateInfo(String.valueOf(PaymentTypesCheckoutAdapter.cardPaymentID));

////            if (discount_amount != 0.0){
////                card_amt = card_amt - discount_amount;
////            }
//        if (getRoundActivateStatus.equals("0")) {
//            card_amt = card_amt - round;
//        }

        cardPayment(card_amt, PaymentTypesCheckoutAdapter.PaymentIDCheckoutAdapter,"");
    }

    private void CardPaymentFun(double round,String EzlinkStatus) {

        //Double card_amt = amount - Double.valueOf(CashLayoutActivity.CashValue);

        //Double card_amt = Double.valueOf(checkout_total.getText().toString().replace("$","")) - Double.valueOf(CashLayoutActivity.CashValue);
        //Double card_amt = Double.valueOf(totalAmount) - Double.valueOf(CashLayoutActivity.CashValue);
        Double cartAmt = 0.0;
        for (int i = 0 ; i < CashValueArr.size(); i++) {
            cartAmt += CashValueArr.get(i);
        }
        Double card_amt = Double.valueOf(totalAmount) - cartAmt;

        if (CashValueArr.size() == 0) {
            card_amt = card_amt - PaymentTypesCheckoutAdapter.chkbilldiscount;
        }


        String getRoundActivateStatus = Query.GetRoundActivateInfo(String.valueOf(PaymentTypesCheckoutAdapter.cardPaymentID));
//            discount_amount = 0.0;
//            discount_amount = Query.CalculationBillDiscountAmount(Status, sub_total, str_percentage_value, txt_discount_checkout,
//                    str_value);
        //Log.i("discount_amount", "discount_amountttt___"+String.valueOf(discount_amount));
//            if (discount_amount != 0.0){
//                card_amt = card_amt - discount_amount;
//            }

//        if (getRoundActivateStatus.equals("0")) {
//            card_amt = card_amt - round;
//        }

        cardPayment(card_amt, PaymentTypesCheckoutAdapter.cardPaymentID,EzlinkStatus);
    }

    public static void ClearDataVariableFun() {
        Integer totalItems = 0;
        ArrayList<String> sldIDArr = new ArrayList<String>();
        ArrayList<Integer> sldQtyArr = new ArrayList<Integer>();
        ArrayList<String> sldNameArr = new ArrayList<String>();
        ArrayList<String> sltPriceTotalArr = new ArrayList<String>();
        ArrayList<String> sltBillDisArr = new ArrayList<String>();
        ArrayList<String> sltCategoryIDArr = new ArrayList<String>();
        ArrayList<String> sltCategoryNameArr = new ArrayList<String>();
        Integer countSelectedArr = RecyclerViewAdapter.countSelectedArr;
        //String BillNo = String.valueOf(MainActivity.BillID);
        ArrayList<String> vchQueueNo = RecyclerViewAdapter.vchQueueNo;
        ArrayList<String> intTableNo = RecyclerViewAdapter.intTableNo;
        ArrayList<String> slddisID = new ArrayList<String>();
        ArrayList<String> slddisName = new ArrayList<String>();
        ArrayList<String> slddisTypeName = new ArrayList<String>();
        ArrayList<String> slddisType = new ArrayList<String>();
        ArrayList<String> slddisValue = new ArrayList<String>();

            totalItems = RecyclerViewAdapter.totalItems;
            sldIDArr = RecyclerViewAdapter.sldIDArr;
            sldQtyArr = RecyclerViewAdapter.sldQtyArr;
            sldNameArr = RecyclerViewAdapter.sldNameArr;
            sltPriceTotalArr = RecyclerViewAdapter.sltPriceTotalArr;
            sltBillDisArr = RecyclerViewAdapter.sltBillDisArr;
            sltCategoryIDArr = RecyclerViewAdapter.sltCategoryIDArr;
            sltCategoryNameArr = RecyclerViewAdapter.sltCategoryNameArr;
            countSelectedArr = RecyclerViewAdapter.countSelectedArr;
            //BillID = MainActivity.BillID;
            //BillNo = String.valueOf(MainActivity.BillID);
            //BillNo = String.valueOf(MainActivity.strbillNo);
            vchQueueNo = RecyclerViewAdapter.vchQueueNo;
            intTableNo = RecyclerViewAdapter.intTableNo;

            slddisID = RecyclerViewAdapter.slddisID;
            slddisName = RecyclerViewAdapter.slddisName;
            slddisTypeName = RecyclerViewAdapter.slddisTypeName;
            slddisType = RecyclerViewAdapter.slddisType;
            slddisValue = RecyclerViewAdapter.slddisValue;

            Integer qty = 1;
            String dateFormat3 = dateFormat55.format(new Date()).toString();
            String paymenttype = "Cash";
            String status = Constraints.SALES;
            String Itemstatus = Constraints.SALES;
            ArrayList<Integer> Modifier_ID =  ButtonAdapter.ID;

            Integer BillID = Integer.parseInt(Query.findBillIDByBillNo(BillNo));

////        saveDetailsBillProduct(BillID,BillNo,sldNameArr,sldQtyArr,sltPriceTotalArr,
////                sltBillDisArr,sltCategoryNameArr,
////                    qty,totalItems,sldIDArr,sltCategoryIDArr,countSelectedArr,dateFormat3,status,
////                    sub_total,amount,paymenttype,Modifier_ID,vchQueueNo,intTableNo,Itemstatus,slddisID,slddisName,
////                    slddisTypeName,slddisType,slddisValue);
//
//        saveDetailsBillProduct(BillID,BillNo,sldNameArr,sldQtyArr,sltPriceTotalArr,
//                sltBillDisArr,sltCategoryNameArr,
//                    qty,sldIDArr,sltCategoryIDArr,dateFormat3, Modifier_ID,intTableNo,vchQueueNo,
//                status,Itemstatus,slddisID,slddisName,
//                    slddisTypeName,slddisType,slddisValue);
//        }
        CashValueArr.clear();
        CashValuePaymentNameArr.clear();
        CashValuePaymentIDArr.clear();
        CashValuePaymentRemarksArr.clear();
        CashLayoutActivity.CashValue = 0.0;

        CashValuePaymentName = "";
        CashValue = "";
//        for (int check = 0 ; check < CashValuePaymentNameArr.size(); check ++){
//            if ( Double.valueOf(CashValueArr.get(check)) > 0.0) {
//                CashValuePaymentName += "\n" + "\n" + CashValuePaymentNameArr.get(check);
//                CashValue += "\n" + "\n" + "$" + String.format("%.2f", Double.valueOf(CashValueArr.get(check)));
//            }
//
//        }
//        text_payment_cash.setText(CashValuePaymentName);
//        text_payment_cash_amount.setText(CashValue);
    }

//    private void checkOutListOnClickFun(ListView checkOutListView) {
//        this.binding.checkoutInfo.checkOutListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
//
//                //String chk_hide_img = Query.GetOptions(20);
//                EditProductSheetFragment.ClearExistingValue();
//
//                FragmentManager manager = getSupportFragmentManager();
//                EditProductSheetFragment editProductSheetFragment = new EditProductSheetFragment();
//                //Object o = checkOutListView.getItemAtPosition(position);
////                String sqll =  "SELECT count(ProductQty),ProductName,SUM(ProductPrice),BillNo," +
////                "SUM(ItemDiscountAmount),ProductID,CategoryID,CategoryName,TaxID,TaxName,SUM(TaxAmount)," +
////                        "DiscountName,DiscountTypeName,DiscountValue,ProductPrice,ID  " +
////                        "FROM Details_BillProduct " +
////                        // "Where BillNo = '"+ BillNo +"' group by ProductQty";
////                        "Where BillNo = '"+ BillNo +"' AND ProductID = '"+ sldIDArr.get(position) +"' AND Cancel = 'SALES' group by ProductID,OpenPriceStatus";
////
//                String sqll =  "SELECT ProductQty,ProductName,ProductPrice,BillNo,ItemDiscountAmount,ProductID,CategoryID," +
//                        "CategoryName,TaxID,TaxName,TaxAmount," +
//                        "DiscountName,DiscountTypeName,DiscountValue, " +
//                        "ProductPrice,ID,OpenPriceStatus  " +
//                        "FROM DetailsBillProduct Where BillNo = '"+BillNo+"' AND  " +
//                        "ProductID = '"+sldIDArr.get(position)+"' " +
//                        " AND OpenPriceStatus = '"+sldOpenPriceStatus.get(position)+"' AND Cancel = 'SALES' " +
//                        "group by OpenPriceStatus" ;
//                Log.i("DFD______sqll____",sqll);
//                Cursor c = DBFunc.Query(sqll,false);
//                //int ccount = 0;
//                if (c != null){
//                    while (c.moveToNext()){
//                        //ccount ++;
//                        EditFragmentName = c.getString(1);
//                        EditFragmentPrice = c.getString(14);
////                        EditFragmentQty = ccount;
//                        EditFragmentOpenPrice = c.getString(16);
//                        EditFragmenttotalQty = c.getString(0);
//                    }
//                    c.close();
//                }
//                EditProductSheetFragment.billNo = BillNo;
//                EditProductSheetFragment.productName = EditFragmentName;
//                //EditProductSheetFragment.productPrice = EditFragmentPrice;
//                //EditProductSheetFragment.productPrice = String.valueOf(Double.valueOf(EditFragmentPrice) * Double.valueOf(EditFragmenttotalQty));
//                //EditProductSheetFragment.EditFragmentPrice = String.valueOf(Double.valueOf(EditFragmentPrice) * Double.valueOf(EditFragmenttotalQty));
//                EditProductSheetFragment.EditFragmentPrice = EditFragmentPrice;
//                Log.i("__","EditFragmentPriceee_"+EditProductSheetFragment.EditFragmentPrice);
//                EditProductSheetFragment.productID = sldIDArr.get(position);
//                EditProductSheetFragment.DetailBillID = sldDetailsBillID.get(position);
//                EditProductSheetFragment.FragmentVar = "CheckoutItem";
//                EditProductSheetFragment.EditFragmenttotalQty = EditFragmenttotalQty;
//                EditProductSheetFragment.EditFragmentOpenPrice = EditFragmentOpenPrice;
//                Log.i("__","EditFragmentOpenPrice_"+EditFragmentOpenPrice+"__"+sldOpenPriceStatus.get(position));
//                try {
//
//                    //EditProductCheckoutSheetFragment editProductSheetFragment = new EditProductCheckoutSheetFragment();
//                    editProductSheetFragment.show(manager, editProductSheetFragment.getTag());
//                }catch (Throwable e){
//                    editProductSheetFragment.onStop();
//                }
//
//
//    /* write you handling code like...
//    String st = "sdcard/";
//    File f = new File(st+o.toString());
//    // do whatever u want to do with 'f' File object
//    */
//            }
//        });
//    }

//    private final Runnable m_Runnable = new Runnable(){
//        public void run(){
//            // Toast.makeText(mcontext,"in runnable"+change_due_amt ,Toast.LENGTH_SHORT).show();
////            Toast.makeText(AddTaxConfigurationActivity.this,"in runnable ddd"+TaxTypeSheetFragment.selected_tax_name ,Toast.LENGTH_SHORT).show();
//
//            if (St.equals("1")) {
//                getDetailsBillProductAll();
//                customAdapter = new CheckOutAdapter(getApplicationContext(),BillNo,
//                        sldQtyArr, sldNameArr, sltPriceTotalArrEach, sldIDArr , sldItemDisArr, sldDiscountName, sldDiscountType, sldDiscountValue);
//                checkOutListView.setAdapter(customAdapter);
//                checkOutListOnClickFun(checkOutListView);
//            }
//            mHandler.postDelayed(m_Runnable,300);
//            //mcontext.mHandler.postDelayed(m_Runnable,20000);
//        }
//
//    };

    public void DiscountFunForAmount(Context context) {

        updateAmountDisFun();

        EditBottomFragmentShowFun();
    }

    public static void updateAmountDisFun() {
        EditProductSheetFragment.str_percentage = "";
        EditProductSheetFragment.str_percentage_value = "";
        EditProductSheetFragment.disID = "";
        EditProductSheetFragment.disName = "";
        EditProductSheetFragment.disTypeName = "";
        EditProductSheetFragment.disType = "";
        EditProductSheetFragment.disValue = "";

        Log.i("disTypeNameAmt___","disTypeNameAmt___"+disTypeNameAmt);
        Log.i("disTypeNameAmt___","disTypeAmt___"+disTypeAmt);
        Log.i("disTypeNameAmt___","disValueAmt___"+disValueAmt);
        Log.i("disTypeNameAmt___","str_value___"+str_value);

//        EditProductSheetFragment.disIDAmt = disIDAmt;
//        EditProductSheetFragment.disNameAmt = disNameAmt;
//        EditProductSheetFragment.disOptionsAmt = disOptionsAmt;
//        EditProductSheetFragment.disTypeNameAmt = disTypeNameAmt;
//        EditProductSheetFragment.disTypeAmt = disTypeAmt;
//        EditProductSheetFragment.disValueAmt = disValueAmt;

        //RecyclerViewAdapter.item_diss =  EditProductSheetFragment.str_percentage;

        if (str_value != null && str_value.length() > 0) {
//            String chk_hide_img = Query.GetOptions(20);
//            if (chk_hide_img.equals("1")) {
//                RecyclerViewNoImageAdapter.item_diss = str_value;
//                Double dis_amt = Double.valueOf(str_value);
//                Double new_amt = Double.valueOf(ItemPriceTotal) - dis_amt;
//
//                new_amt = new_amt * Double.valueOf(EditFragmenttotalQty);
//                dis_amt = dis_amt * Double.valueOf(EditFragmenttotalQty);
//                //EditProductSheetFragment.EditFragmentPrice = String.valueOf(Double.valueOf(EditFragmentPrice) * Double.valueOf(EditFragmenttotalQty));
//
//                RecyclerViewNoImageAdapter.old_pricee = String.valueOf(new_amt);
//                RecyclerViewNoImageAdapter.item_diss_amt = String.valueOf(dis_amt);
//            } else {
            RecyclerViewAdapter.item_diss = str_value;
            //str_value
            Double dis_amt = Double.valueOf(str_value);
            Double new_amt = Double.valueOf(ItemPriceTotal) - dis_amt;

            new_amt = new_amt * Double.valueOf(EditFragmenttotalQty);
            dis_amt = dis_amt * Double.valueOf(EditFragmenttotalQty);

            //EditProductSheetFragment.EditFragmentPrice = String.valueOf(Double.valueOf(EditFragmentPrice) * Double.valueOf(EditFragmenttotalQty));


            RecyclerViewAdapter.old_pricee = String.valueOf(new_amt);
            RecyclerViewAdapter.item_diss_amt = String.valueOf(dis_amt);
            Log.i("new_amt__r_","new_amt__"+new_amt);
            Log.i("new_amt_r__","new_amt__"+dis_amt);
            Log.i("new_amt__r_","str_value_"+str_value);
//            }
        }

        EditProductSheetFragment.str_value =  str_value;

        EditProductSheetFragment.disIDAmt = disIDAmt;
        EditProductSheetFragment.disNameAmt = disNameAmt;
        EditProductSheetFragment.disOptionsAmt = disOptionsAmt;
        EditProductSheetFragment.disTypeNameAmt = disTypeNameAmt;
        EditProductSheetFragment.disTypeAmt = disTypeAmt;
        EditProductSheetFragment.disValueAmt = disValueAmt;

        Log.i("new_amt__r_","new_amt1__"+EditProductSheetFragment.str_value);
        Log.i("new_amt__r_","new_amt2__"+EditProductSheetFragment.disIDAmt);
        Log.i("new_amt__r_","new_amt3__"+EditProductSheetFragment.disNameAmt);
        Log.i("new_amt__r_","new_amt4__"+EditProductSheetFragment.disOptionsAmt);
        Log.i("new_amt__r_","new_amt5__"+EditProductSheetFragment.disTypeNameAmt);
        Log.i("new_amt__r_","new_amt6__"+EditProductSheetFragment.disTypeAmt);
        Log.i("new_amt__r_","new_amt7__"+EditProductSheetFragment.disValueAmt);
        Log.i("new_amt__r_","item_diss__"+RecyclerViewAdapter.item_diss);
    }

    public void DiscountFunForPercentage(Context context) {

        updatePercentageDisFun();

        EditBottomFragmentShowFun();
    }

    public static void updatePercentageDisFun() {

        EditProductSheetFragment.str_value =  "";
        EditProductSheetFragment.disIDAmt =  "";
        EditProductSheetFragment.disNameAmt =  "";
        EditProductSheetFragment.disOptionsAmt =  "";
        EditProductSheetFragment.disTypeNameAmt =  "";
        EditProductSheetFragment.disTypeAmt =  "";
        EditProductSheetFragment.disValueAmt =  "";

        EditProductSheetFragment.old_pricee = "0";
        String chk_hide_img = Query.GetOptions(20);
//        if (chk_hide_img.equals("1")){
//            RecyclerViewNoImageAdapter.item_diss =  str_percentage;
//            if (str_percentage_value != null && str_percentage_value.length() > 0) {
//                Double dis_amt = (Double.valueOf(str_percentage_value) * Double.valueOf(ItemPriceTotal)) / 100d;
//                Double new_amt = Double.valueOf(ItemPriceTotal) - dis_amt;
//
//                new_amt = new_amt * Double.valueOf(EditFragmenttotalQty);
//                dis_amt = dis_amt * Double.valueOf(EditFragmenttotalQty);
//                //EditProductSheetFragment.EditFragmentPrice = String.valueOf(Double.valueOf(EditFragmentPrice) * Double.valueOf(EditFragmenttotalQty));
//
//
////                RecyclerViewNoImageAdapter.old_pricee = String.valueOf(new_amt);
//                old_pricee = String.valueOf(new_amt);
////                RecyclerViewNoImageAdapter.item_diss_amt = String.valueOf(dis_amt);
//            }
//        }else {
        if (str_percentage != null && str_percentage_value.length() > 0) {
            RecyclerViewAdapter.item_diss = str_percentage;
            Double dis_amt = (Double.valueOf(str_percentage_value) * Double.valueOf(ItemPriceTotal)) / 100d;
            Double new_amt = Double.valueOf(ItemPriceTotal) - dis_amt;

            new_amt = new_amt * Double.valueOf(EditFragmenttotalQty);
            dis_amt = dis_amt * Double.valueOf(EditFragmenttotalQty);
            //EditProductSheetFragment.EditFragmentPrice = String.valueOf(Double.valueOf(EditFragmentPrice) * Double.valueOf(EditFragmenttotalQty));

            old_pricee = String.valueOf(new_amt);
            RecyclerViewAdapter.old_pricee = String.valueOf(new_amt);
            RecyclerViewAdapter.item_diss_amt = String.valueOf(dis_amt);
        }
//        }

        EditProductSheetFragment.str_percentage = str_percentage;
        EditProductSheetFragment.str_percentage_value = str_percentage_value;

        EditProductSheetFragment.disID = disID;
        EditProductSheetFragment.disName = disName;
        EditProductSheetFragment.disOptions = disOptions;
        EditProductSheetFragment.disTypeName = disTypeName;
        EditProductSheetFragment.disType = disType;
        EditProductSheetFragment.disValue = disValue;

    }

    public void EditBottomFragmentShowFun() {
//        EditProductSheetFragment.str_value =  "";
//        EditProductSheetFragment.disIDAmt =  "";
//        EditProductSheetFragment.disNameAmt =  "";
//        EditProductSheetFragment.disOptionsAmt =  "";
//        EditProductSheetFragment.disTypeNameAmt =  "";
//        EditProductSheetFragment.disTypeAmt =  "";
//        EditProductSheetFragment.disValueAmt =  "";
//
//        EditProductSheetFragment.str_percentage = "";
//        EditProductSheetFragment.str_percentage_value = "";
//        EditProductSheetFragment.disID = "";
//        EditProductSheetFragment.disName = "";
//        EditProductSheetFragment.disTypeName = "";
//        EditProductSheetFragment.disType = "";
//        EditProductSheetFragment.disValue = "";
//
//        str_value = "";
//        str_percentage= "";
//        str_percentage_value= "";

        Log.i("TAGGGG","EditBottomFragmentShowFun__"+EditFragmentPrice);
        Log.i("TAGGGG","EditBottomFragmestr_value__"+EditProductSheetFragment.str_value);
//        FragmentManager manager = ((FragmentActivity)appContext).getSupportFragmentManager();
        FragmentManager manager = getSupportFragmentManager();
        EditProductSheetFragment editProductSheetFragment = new EditProductSheetFragment();

        try {

            Log.i("DFDFDFD___","TRTRTR____"+"_____4");
            editProductSheetFragment.show(manager, editProductSheetFragment.getTag());
            Log.i("TAGGGG","EditBottomFragmestr_value__"+EditProductSheetFragment.str_value);
        }catch (Throwable e){
            editProductSheetFragment.onStop();
        }
    }


    //    private List<ViewModel> createMockList() {
//        List<ViewModel> items = new ArrayList<ViewModel>();
//
//        Log.i("Df___sldQtyArrze_", String.valueOf(sldQtyArr));
////        for (int i = 0; i < sldQtyArr.size(); i++) {
////            items.add(new ViewModel("Item " + (i + 1),listImages.getResourceId(i, -1)));
////            sldIDArr , sldItemDisArr, sldDiscountName, sldDiscountType, sldDiscountValue
//            items.add(new ViewModel(sldQtyArr, sldQtyArr,sldNameArr, sltPriceTotalArrEach,sldIDArr , sldItemDisArr, sldDiscountName, sldDiscountType, sldDiscountValue));
////        }
//        return items;
//    }
    public static void getPaymentsAll() {
        //Cursor c = Query.GetPaymentInfoAll("LIMIT 3");
        Cursor c = Query.GetPaymentInfoAll();
        if(c != null){
            paymentTypesClass.clear();
            while(c.moveToNext()){
                paymentTypesClass.add(new PaymentTypes(c.getString(0),c.getString(1),
                        c.getString(2),c.getString(3),c.getString(4),c.getString(5),
                        c.getString(6),c.getString(7),c.getString(8)));
            }
            //paymentTypesClass.add(new PaymentTypes("0","Others","0","0","0","0","0"));
            c.close();
        }
    }

    private void DeleteBill() {
        DBFunc.ExecQuery("DELETE FROM BillDetails WHERE BillNo = '"+ BillNo + "'", false);
        DBFunc.ExecQuery("DELETE FROM DetailsBillProduct WHERE BillNo = '"+ BillNo + "'", false);
        DBFunc.ExecQuery("DELETE FROM Details_BillProduct WHERE BillNo = '"+ BillNo + "'", false);
        DBFunc.ExecQuery("DELETE FROM BillPLU WHERE BillNo = '"+ BillNo + "'", false);
    }

    public static void getDetailsBillProductAll(String BillNo){

        String sql = " SELECT (ProductQty),ProductName,(ProductPrice),BillNo," +
                "(ItemDiscountAmount),ProductID,CategoryID,CategoryName,TaxID,TaxName,(TaxAmount)," +
                "DiscountName,DiscountTypeName,DiscountValue,ProductPrice,ID,OpenPriceStatus,TaxType,ProductQty,Remarks,ID  " +
                "FROM DetailsBillProduct " +
                "Where BillNo = '"+ BillNo +"' " +
//                " AND  ( Cancel = 'SALES' || Cancel = 'CANCEL' ) " +
                "group by ProductID,OpenPriceStatus,Remarks";
//        String sql = " SELECT SUM(ProductQty),ProductName,SUM(ProductPrice),BillNo," +
//                "SUM(ItemDiscountAmount),ProductID,CategoryID,CategoryName,TaxID,TaxName,SUM(TaxAmount)," +
//                "DiscountName,DiscountTypeName,DiscountValue,ProductPrice,ID,OpenPriceStatus,TaxType,ProductQty,Remarks,ID  " +
//                "FROM DetailsBillProduct " +
//                "Where BillNo = '"+ BillNo +"' " +
////                " AND  ( Cancel = 'SALES' || Cancel = 'CANCEL' ) " +
//                "group by ProductID,OpenPriceStatus,Remarks";
        Log.i("DFDFA_getDAll_","df_dfdf"+sql);
        Cursor c = DBFunc.Query(sql, false);
        if (c != null) {
            sldIDArr.clear();
            sldIDDetailsBill.clear();
            sldQtyArr.clear();
            sldNameArr.clear();
            sltPriceTotalArr.clear();
            sltPriceTotalArrEach.clear();
            sldItemDisArr.clear();
            sldCategoryIDArr.clear();
            sldCategoryNameArr.clear();
            sldTaxID.clear();
            sldCTaxAmount.clear();
            sldDiscountName.clear();
            sldDiscountType.clear();
            sldDiscountValue.clear();
            sldDetailsBillID.clear();
            sldOpenPriceStatus.clear();
            sldRemarks.clear();
            sldRemarksNoSubstring.clear();
            bill_type = "0";
            billdetailsPID.clear();
            while (c.moveToNext()) {
                   // if (c.getInt(18) != -1){

                        String ID = c.getString(5);
                        String Qty = c.getString(0);
                        String Name = c.getString(1);
                        String Remarks = c.getString(19);
                        billdetailsPID.add(c.getInt(20));
                        String chkicno_on = Query.GetOptions(26);
                        if (chkicno_on.equals("1")) {
                            //HPB
                            if (Remarks != null && Remarks.length() == 9) {
                                Remarks = Constraints.PASSFirstDigits + Remarks.substring(5,9);
                            } else {
                                Remarks =  Remarks;
                            }
                        } else {
                            Remarks =  Remarks;
                        }
                        Log.i("Remarks____","Remarks___"+Remarks);
                        if (Remarks != null && Remarks.trim().length() > 0 && !(Remarks.equals(null)) && !(Remarks.equals("null"))){
                            sldRemarksNoSubstring.add(Remarks);
                        } else {
                            sldRemarksNoSubstring.add("");
                        }
                        //String Price = c.getString(2);

                        //Double totalPrice = c.getDouble(0) * c.getDouble(2);
                        Double totalPrice = c.getDouble(2);
                        sldQtyArr.add(Qty);
                        //sldNameArr.add(Name);

                        if (Remarks != null && Remarks.trim() != "" && Remarks.length() > 0 && !(Remarks.equals("0"))) {
                            if (!Remarks.equals("null")) {
                                sldNameArr.add(Name + "\n" + "(" + Remarks + ")");
                            } else {
                                sldNameArr.add(Name);
                            }
                        } else {
                            sldNameArr.add(Name);
                        }
                        Log.i("totalPrice__","totalPrice___"+totalPrice);
                        sltPriceTotalArr.add(String.valueOf(totalPrice));
                        //sltPriceTotalArrEach.add(c.getString(14));
                        //sltPriceTotalArrEach.add(c.getString(2));
                        sltPriceTotalArrEach.add(String.valueOf(totalPrice));

                        //sldItemDisArr.add(c.getString(4));
                        Double diVal = 0.0;
                        try {
                            diVal = c.getDouble(0) * Double.valueOf(c.getString(4));
//                            diVal = Double.valueOf(c.getString(4));
                        } catch (Exception e){
                            diVal = 0.0;
                        }
                        sldItemDisArr.add(String.valueOf(diVal));
                        sldIDArr.add(ID);
                        sldIDDetailsBill.add(c.getInt(15));
                        sldCategoryIDArr.add(c.getString(6));
                        sldCategoryNameArr.add(c.getString(7));
                        Integer taxID = c.getInt(8);
                        sldTaxID.add(String.valueOf(taxID));
                        //sldCTaxName.add(c.getString(9));
                        sldCTaxAmount.add(c.getString(10));
                        sldDiscountName.add(c.getString(11));
                        sldDiscountType.add(c.getString(12));
                        sldDiscountValue.add(c.getString(13));
                        sldDetailsBillID.add(c.getString(15));
                        sldOpenPriceStatus.add(c.getString(16));
                        if (Remarks != null && Remarks.length() > 0 &&
                                !(Remarks.equals(null)) && !(Remarks.equals("null"))){

                            sldRemarks.add(Remarks);
                        }else {

                            sldRemarks.add("");
                         }
                        String taxType = c.getString(17);
                        bill_type ="B";
                   // }
            }
            c.close();
        }
    }

    private static void FunPromotion(String productID, String productName, String productPrice, Integer productQty) {
        String Promo_Promo_DateFrom = "";
        String Promo_Promo_DateTo = "";
        String Promotype_Promo_TypeCode = "";
        String Promo_Promo_Name = "";
        String PLU_Name = "";
        String Promo_Item_PromoID = "";
        String Promo_Item_ItemID = "";
        String Promo_Item_Item_Qty = "";
        String Promo_Item_ItemUOM = "";
        String Promo_Item_ItemPrice = "";
        String Promo_Mixmatch_Promo_Qty = "";
        String Promo_Mixmatch_Promo_Amount = "";
        String Promo_Promo_Type = "";
        Integer totQty = 0;
        Integer totQty1 = 0;
        Integer ot_totQty_3 = 0;
        String fromDAte = "";
        String toDAte = "";
        String Promo_Name = "";
        String PLU_Price = "";
        String now_date = Query.ISO_8601_formatted_date1();
        String sql = "select Promo.Promo_DateFrom,Promo.Promo_DateTo,Promotype.Promo_TypeCode,Promo.Promo_Name,PLU.Name," +
                "       Promo_Item.PromoID,Promo_Item.ItemID,Promo_Item.Item_Qty,Promo_Item.ItemUOM,Promo_Item.ItemPrice," +
                "       Promo_Mixmatch.Promo_Qty,Promo_Mixmatch.Promo_Amount,Promo.Promo_Type,PLU.Price from PLU " +
                "        inner join Promo_Item on Promo_Item.ItemID = PLU.UUID " +
                "        inner join Promo on Promo_Item.PromoID = Promo.PromoID " +
                "        inner join Promo_Mixmatch on Promo_Item.PromoID = Promo_Mixmatch.PromoID " +
                "        inner join Promotype on Promotype.Promo_TypeID = Promo.Promo_Type " +
                "        WHERE PLU.ID = "+productID+"" +
                "        GROUP BY Promo_Item.ItemID";
        Cursor CursorPromotion = DBFunc.Query(sql,true);
        if (CursorPromotion != null){
            if (CursorPromotion.moveToNext()) {
                //if (CursorPromotion.moveToNext()) {
                Promo_Promo_DateFrom = CursorPromotion.getString(0);
                Promo_Promo_DateTo = CursorPromotion.getString(1);
                Promotype_Promo_TypeCode = CursorPromotion.getString(2);
                Promo_Promo_Name = CursorPromotion.getString(3);
                PLU_Name = CursorPromotion.getString(4);
                Promo_Item_PromoID = CursorPromotion.getString(5);
                Promo_Item_ItemID = CursorPromotion.getString(6);
                Promo_Item_Item_Qty = CursorPromotion.getString(7);
                Promo_Item_ItemUOM = CursorPromotion.getString(8);
                Promo_Item_ItemPrice = CursorPromotion.getString(9);
                Promo_Mixmatch_Promo_Qty = CursorPromotion.getString(10);
                Promo_Mixmatch_Promo_Amount = CursorPromotion.getString(11);
                Promo_Promo_Type = CursorPromotion.getString(12);
                PLU_Price = CursorPromotion.getString(13);
                productPrice = PLU_Price;
                Log.i("Dfd_typecode_",Promotype_Promo_TypeCode);
                if (Promotype_Promo_TypeCode.equals("mm")) {
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
                        SimpleDateFormat sdf2 = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
                        fromDAte = sdf2.format(sdf.parse(Promo_Promo_DateFrom));
                        toDAte = sdf2.format(sdf.parse(Promo_Promo_DateTo));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Date date11 = null;
                    Date date12 = null;
                    sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
                    try {
                        date11 = sdf.parse(fromDAte);
                        date12 = sdf.parse(toDAte);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Log.i("Dfd_tLong_",now_date+"__"+Long.parseLong(now_date));
                    Log.i("Dfd_tLong_",date11.getTime()+"__"+Long.parseLong(String.valueOf(date11.getTime())));
                    Log.i("Dfd_tLongd_",productQty+"__"+Promo_Mixmatch_Promo_Qty);
                    if (Long.parseLong(now_date) >= Long.parseLong(String.valueOf(date11.getTime()))) {
                        if (Long.parseLong(String.valueOf(date12.getTime())) >= Long.parseLong(now_date)) {
                            if (Integer.parseInt(String.valueOf(productQty)) >= Integer.parseInt(Promo_Mixmatch_Promo_Qty)) {

                                totQty = 0;
                                totQty += Integer.parseInt(String.valueOf(productQty));

                            } else {

                                totQty += Integer.parseInt(String.valueOf(productQty));

                            }
                            totQty1 += Integer.parseInt(String.valueOf(productQty));
                        }
                    }
                    Log.i("DfdtotQtygd_",totQty+"__"+Promo_Mixmatch_Promo_Qty);
                    if (Double.parseDouble(String.valueOf(totQty)) >= Double.parseDouble(Promo_Mixmatch_Promo_Qty)
                            && Double.parseDouble(Promo_Mixmatch_Promo_Qty) > 0.0) {
                        String volley_ItemID = Promo_Item_ItemID;

                        if (volley_ItemID.length() > 1) {

                            int modulus = totQty % Integer.parseInt(Promo_Mixmatch_Promo_Qty);

                            int realtotqty = totQty / Integer.parseInt(Promo_Mixmatch_Promo_Qty); // 5 / 3 = 1

                            int totQty_ = realtotqty * Integer.parseInt(Promo_Mixmatch_Promo_Qty);  // 1 * 3 = 3

                            Log.i("totQty_", String.valueOf(totQty_));
                            Integer overtot = 0;
                            Integer ot_totQty_ = 0;
                            Double reduceprice = 0.0;
                            Double needqtyamt = 0.0;
                            Double totQtyaa = 0.0;
                            Double price = 0.0;
                            if (modulus == 0) {
                                overtot = 0;
                            } else {
                                overtot = totQty - totQty_;
                                ot_totQty_ = totQty_;
                                reduceprice = overtot * Double.valueOf(productPrice);
                            }
                            //int totamt_ = realtotqty * Integer.parseInt(mixmatproamt);
                            Log.i("realtotqty_", String.valueOf(realtotqty));
                            double totamt_ = Double.parseDouble(String.valueOf(realtotqty)) * Double.parseDouble(Promo_Mixmatch_Promo_Qty);
                            if (overtot > 0) {
                                Log.i("Dfd_ot_totQty_", String.valueOf(ot_totQty_));
                                Log.i("Dfd_oproductPrice_", String.valueOf(productPrice));
                                needqtyamt = ot_totQty_ * Double.valueOf(productPrice); // 3 * 0.8 = 2.4
                            } else {

                                Log.i("Dfd_ot_totQty_e", String.valueOf(totQty_));
                                Log.i("Dfd_oproductPricee_", String.valueOf(productPrice));
                                needqtyamt = totQty_ * Double.valueOf(productPrice); // 3 * 0.8 = 2.4
                            }
                            double promo_amt = Double.valueOf(Promo_Mixmatch_Promo_Qty);
                            double totalsalesamt = needqtyamt;
                            double allitemdis = promo_amt / totalsalesamt;

                            //double eachitmNotDis = Double.valueOf(productName);
                            double eachitmNotDis = Double.valueOf(productPrice);
                            double eachitmtotalamt = eachitmNotDis * allitemdis;
                            double eachitmdiss = eachitmNotDis - eachitmtotalamt;

                            double originalPrice = eachitmNotDis;
                            double dis = eachitmdiss;
                            double ttl = eachitmtotalamt;

                            Log.i("DF_needqtyamt", String.valueOf(needqtyamt));
                            Log.i("DF_totamt_", String.valueOf(totamt_));
                            double tamt = needqtyamt - totamt_;
                            double aa = tamt;
                            totQtyaa += aa;
                            price = Double.valueOf(productPrice);
                            price = price - aa;

                            //promo_amt / totalsalesamt ; // 2/2.4 = 0.8333
                            //0.80 * 0.8333 = 0.67
                            //0.80 (price) - 0.67 (total) = 0.13 (dis)


                            Double dis_amt = Double.valueOf(needqtyamt) - Double.valueOf(Promo_Mixmatch_Promo_Qty); // 2.4  = 2

                            Double val = Double.valueOf(needqtyamt) - Double.valueOf(totamt_);

                            //eachItemPromoValue = val / Integer.parseInt(Promo_Mixmatch_Promo_Qty);


                            String volley_ItemQty = " ";
                            if (overtot > 0) {
                                if (totQty > ot_totQty_) {
                                    volley_ItemQty = String.valueOf(ot_totQty_);
                                    ot_totQty_3 = totQty - ot_totQty_;
                                } else {
                                    volley_ItemQty = String.valueOf(totQty);
                                }
                            } else {
                                if (totQty > totQty_) {
                                    volley_ItemQty = String.valueOf(totQty_);
                                } else {
                                    volley_ItemQty = String.valueOf(totQty);
                                }
                            }
                            String volley_ItemUOMDesc = Promo_Item_ItemUOM;

                            double volley_itemprice = Double.valueOf(productPrice);
                            String volley_ItemPrice = String.valueOf(volley_itemprice);
                            // 2.4 - 2 = 0.4
                            double eachitmdis = Double.valueOf(totamt_) / Double.valueOf(volley_ItemQty);

                            String volley_ItemDisc = String.format("%.2f", dis_amt);

                            String volley_ItemTax = "0";
                            double dou_itm_total = Double.valueOf(volley_ItemPrice) - Double.valueOf(volley_ItemDisc);

                            double eachitmtotamt = Double.valueOf(volley_itemprice) - Double.valueOf(eachitmdis);
                            String volley_ItemTotal = String.format("%.2f", eachitmtotamt);

                            double dneedqtyamt = needqtyamt - totamt_; // 2.4 - 2 = 0.4
                            double eachProAmt = dneedqtyamt / totQty_; // 0.4 / 3 = 0.1333
                            double eachProAmttot = eachProAmt * productQty;
                            double originalpromotot = dneedqtyamt - eachProAmt;
                            //price += price - originalpromotot;

                            //if (Integer.parseInt(String.valueOf(productQty)) >= Integer.parseInt(mixmatproqty)) {
                            Log.i("DFdfeachProAmt", String.valueOf(eachProAmt));
                            if (eachProAmt > 0) {
                                Log.i("DFvolley_ItemID", String.valueOf(volley_ItemID));
                                if (volley_ItemID.length() > 0) {
                                    ItemDetail itmdetail = new ItemDetail(volley_ItemID, Promo_Name, volley_ItemQty, volley_ItemUOMDesc, String.valueOf(originalPrice),
                                            String.valueOf(dis), volley_ItemTax, String.valueOf(ttl), Promo_Item_ItemID, Promo_Item_PromoID, Promo_Promo_Type, Promotype_Promo_TypeCode);

                                    //ItemStringList.add(itmdetail);
                                }

                                Double mul1 = (-1) * Double.valueOf(aa);

//                                String st = report_product_name.getText().toString() + "<br/>  **" + Promo_Name;
//                                report_product_name.setText(Html.fromHtml(st));
//                                String stprice = report_product_price.getText().toString() + "<br/> " + mul1;
//                                report_product_price.setText(Html.fromHtml(stprice));

                                Query.saveBillPromo(CheckOutActivity.BillNo, productID, productID, Promo_Item_ItemID,
                                        Promo_Promo_Type, Promotype_Promo_TypeCode, String.valueOf(mul1));

                                totQty = 0;
                            }
                            //}
                        }
                    }
                }
            }
            CursorPromotion.close();
        }
    }



//    void SaveBillDisc(int discID, int pluindex) {
//        Cursor c = DBFunc.Query("SELECT Name,Value,Option FROM Disc WHERE ID = " + discID, true);
//        if (c == null) {// database error
//            return;
//        }
//        if (!c.moveToNext()) {// no such discount found
//            return;
//        }
//
//        final String name = c.getString(0);
//        double value = c.getDouble(1);
//        if (value < 0)
//            value = 0;
//        final boolean[] options = new boolean[4];// billdisc, amt disc,
//        // positive value, include
//        // tax discount
//        for (int i = 0; i < options.length; i++) {
//            if (i < c.getString(2).length()) {
//                if (c.getString(2).charAt(i) == '1') {
//                    options[i] = true;
//                }
//            }
//        }
//        c.close();
//        String sql = "INSERT INTO BillDisc(DiscID, BillNo,BillPLU_idx,Name,Value,Option) VALUES(" + discID + ", " + BillID + ", ";
//        if (pluindex == -1) {
//            sql += "NULL, ";
//        } else {
//            sql += pluindex + ", ";
//        }
//
//        String option = "";
//        for (boolean b : options) {
//            if (b) {
//                option += "1";
//            } else {
//                option += "0";
//            }
//        }
//        sql += "'" + DBFunc.PurifyString(name) + "', " + value + ", '" + option + "')";
//
//        DBFunc.ExecQuery(sql, false);
//
//        DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth, System.currentTimeMillis(), DBFunc.PurifyString("POS -> Discount -> Bill: " + BillID + ", Name: " + name + ", Value: " + value + ", Option: " + option));
//
//        //UpdateItemList();
//        //CalculateBillData(BillID);
//    }
//    void CalculateBillData(int billID) {
////        , List<Object[]> billItemList, List<Object[]> tax
//        Cursor data= DBFunc.Query("SELECT idx,Name,Amount,Qty,PLUID,CondiPLUID FROM BillPLU WHERE BillNo = " + billID + " AND Cancel = 0 AND CondiPLUID IS NULL ORDER BY idx ASC", false);
//
//        while (data.moveToNext()) {
//            double itemprice_base = data.getInt(3) * data.getDouble(2);
//
//            double itemprice_disc = itemprice_base;
//            // disc option = bill disc, amount disc, positive value, count on tax
//            Cursor disc = DBFunc.Query("SELECT idx, Name, Value, Option FROM BillDisc WHERE BillNo = " + billID + " AND BillPLU_idx = " + data.getInt(0) + " ORDER BY idx ASC LIMIT 1", false);
//            int discidx = -1;
//            String discname = "";
//            double discvalue = 0;
//            double discamt = 0;
//            boolean[] discopt = new boolean[4];
//
//            if (disc != null) {
//                if (disc.moveToNext()) {
//                    discidx = disc.getInt(0);
//                    discname = disc.getString(1);
//                    discvalue = disc.getDouble(2);
//                    if (discvalue < 0)
//                        discvalue = 0;
//                    for (int i = 0; i < discopt.length; i++) {
//                        if (i < disc.getString(3).length()) {
//                            if (disc.getString(3).charAt(i) == '1') {
//                                discopt[i] = true;
//                            }
//                        }
//                    }
//                }
//                disc.close();
//            }
//
//            if (discidx != -1) {
//                if (!discopt[0]) {// is it bill discount?
//                    if (discopt[3]) {// discount before tax
//                        if (discopt[1]) {// amount discount
//                            if (discopt[2]) {// discount is positive(surcharge)
//                                discamt = discvalue;
//                                itemprice_disc += discamt;
//                            } else {// discount
//                                if (itemprice_disc - discvalue > 0) {
//                                    discamt = -discvalue;
//                                    itemprice_disc += discamt;
//                                } else {
//                                    discamt = -itemprice_base;
//                                    itemprice_disc = 0;
//                                }
//                            }
//                        } else {
//                            if (discvalue > 100)
//                                discvalue = 100;
//                            discamt = itemprice_disc * (discvalue / 100d);
//                            if (discopt[2]) {
//                                itemprice_disc += discamt;
//                            } else {
//                                discamt = -discamt;
//                                itemprice_disc += discamt;
//                            }
//                        }
//                    }
//                }
//            }
//
//            double itemprice = itemprice_disc;
//
//            // double itemprice = itemprice_base;
//            Cursor plutax = DBFunc.Query("SELECT TaxID,Name,Acronym,Rate, Type FROM BillPLUTax WHERE BillPLU_idx = " + data.getInt(0) + " ORDER BY Seq ASC", false);
//            String tax_acronym = "";
//            if (plutax != null) {
//                double _tax = 0;
//                while (plutax.moveToNext()) {
//                    if (plutax.getInt(4) == 0) {// not include(add on tax)
//                        _tax = (itemprice * (plutax.getDouble(3) / 100f));
//                        itemprice += _tax;
//                    } else if (plutax.getInt(4) == 1) {// is inclusive (VAT)
//                        _tax = (itemprice - (itemprice / (1f + (plutax.getDouble(3) / 100f))));
//                        itemprice = (itemprice / (1f + (plutax.getDouble(3) / 100f)));
//                    } else if (plutax.getInt(4) == 2) {// calculate on base price (add on tax)
//                        _tax = (itemprice_base * (plutax.getDouble(3) / 100f));
//                        itemprice += _tax;
//                    }
//
//                    if (!plutax.isNull(2) && !plutax.getString(2).isEmpty()) {
//                        tax_acronym = plutax.getString(2);
//                    }
//                }
//                plutax.close();
//            }
//
//            itemprice_disc = itemprice;
//
//            if (discidx != -1) {
//                if (!discopt[0]) {// is it bill discount?
//                    if (!discopt[3]) {// discount AFTER tax
//                        if (discopt[1]) {// amount discount
//                            if (discopt[2]) {// discount is positive(surcharge)
//                                discamt = discvalue;
//                                itemprice_disc += discamt;
//                            } else {// discount
//                                if (itemprice_base - discvalue > 0) {
//                                    discamt = -discvalue;
//                                    itemprice_disc += discamt;
//                                } else {
//                                    discamt = -itemprice_disc;
//                                    itemprice_disc = 0;
//                                }
//                            }
//                        } else {
//                            if (discvalue > 100)
//                                discvalue = 100;
//                            discamt = itemprice_disc * (discvalue / 100d);
//                            if (discopt[2]) {
//                                itemprice_disc += discamt;
//                            } else {
//                                discamt = -discamt;
//                                itemprice_disc += discamt;
//                            }
//                        }
//                    }
//                }
//            }
//
//        }
//    }

    public static String checkBillDetailsByBillNo(String billNo) {
        String bill_No = "";
        String sql = "Select BillNo FROM BillDetails Where BillNo = '"+billNo+"' ";
        Cursor c = DBFunc.Query(sql, false);
        if (c != null) {
            if (c.moveToNext()) {
                bill_No = c.getString(0);
            }
            c.close();
        }
//        if (bill_No.isEmpty()){
//            sql = "Select TransNo FROM OnlineBill_HOLD Where TransNo = '"+billNo+"' ";
//            c = DBFunc.Query(sql, false);
//            if (c != null) {
//                if (c.moveToNext()) {
//                    bill_No = c.getString(0);
//                }
//                c.close();
//            }
//        }
        return bill_No;
    }

    @Override
    public void onBackPressed() {
        MainPage();
        super.onBackPressed();
//        finish();
//        MainPage();
//        MainActivity.St = "1";
//        ProductMainPageFragment.St = "1";
//        RecyclerViewAdapter.St = "1";
    }

    //    @Override
//    public void onBackPressed() {
//        //if (category_checking.equals("0")) {
//            new AlertDialog.Builder(this)
//                    .setMessage("Are you sure you want to exit?")
//                    .setCancelable(false)
//                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            CheckOutActivity.this.finish();
//                        }
//                    })
//                    .setNegativeButton("No", null)
//                    .show();
//       // }
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
//            case R.id.layCheckoutOverAll:
//                binding.layCheckoutOverAll.setAlpha(1);
//                break;
            case R.id.add_member_icon:
//                AddMemberShipFragment addMemberShipFragment = new AddMemberShipFragment();
//                addMemberShipFragment.show(getSupportFragmentManager(), addMemberShipFragment.getTag());
                break;
//            case R.id.payment_other_layout:
////                PaymentOtherFragment paymentOtherFragment = new PaymentOtherFragment();
////                paymentOtherFragment.show(getSupportFragmentManager(), paymentOtherFragment.getTag());
//                break;
//            case R.id.cash_layout:
//                finish();
//                Intent i = new Intent(getApplicationContext(), CashLayoutActivity.class);
//                startActivity(i);
//                break;
//            case R.id.card_layout:
//                Log.i("Sf_card_layout", String.valueOf(amount));
//                cardPayment(Double.parseDouble(String.valueOf(amount)),paymentID) ;
//                break;
            case R.id.btn_add_discount_checkout:
                emptyCashValueAll();
                Intent DiscountActivity = new Intent(getApplicationContext(), ItemDiscountActivity.class);
                DiscountActivity.putExtra("name","Checkout");
                startActivity(DiscountActivity);
                finish();
                break;
            case R.id.btn_remove_discount_checkout:
                    binding.checkoutOrderSummary.txtItemDiscountCheckout.setText("$0.00");
                break;
        }
    }

    private void cardPayment(final Double cardamount, Integer paymentID,String EzlinkStatus) {

        Intent launchIntent = new Intent();

        //Cursor c = DBFunc.Query("SELECT Name,Amount,Option,PaymentTypeID FROM payment WHERE ID = " + paymentID, true);
        Cursor c = DBFunc.Query("SELECT Name,Amount,Option FROM payment WHERE ID = " + paymentID, true);
        //String payment_type_str = "";
        if (c != null) {
            if (c.moveToNext()) {
                //payment_type_str = c.getString(3);
                payment_type_name = c.getString(0);
            }
            if (!(payment_type_name.toUpperCase().equals(DeclarationConf.CARD_TYPE_EZLINK))) {
                payment_type_name = "OTHERS";
            }
            c.close();
        }

        bankName = Query.GetBankNameFun();


        if (EzlinkStatus.equals("Ezlink")) {
            bankName = "";

            String chkValue = CheckPaymentApp.existOrNotApp(CheckOutActivity.this,DeclarationConf.PACKAGE_NAME_ASCAN);
            if (chkValue.equals("1")){
                SendRequestForActivityResultFun(DeclarationConf.CARD_TYPE_EZLINK,cardamount,DeclarationConf.PACKAGE_NAME_ASCAN, DeclarationConf.CLASS_NAME_ASCAN);
//                SendRequestForActivityResultFun(payment_type_name,cardamount,DeclarationConf.PACKAGE_NAME_DBS, DeclarationConf.CLASS_NAME_DBS);

            } else {
                SweetAlertWarningYesOnly(this, Constraints.APP_NOT_INSTALLED,Constraints.OK);
            }
        } else {
            if (bankName.toUpperCase().equals(DeclarationConf.HOST_TYPES_OCBC.toUpperCase())) {
                String chkValue = CheckPaymentApp.existOrNotApp(CheckOutActivity.this,DeclarationConf.PACKAGE_NAME_OCBC);
                if (chkValue.equals("1")) {
                    SendRequestForActivityResultFun(payment_type_name, cardamount, DeclarationConf.PACKAGE_NAME_OCBC, DeclarationConf.CLASS_NAME_OCBC);
                } else {
                    SweetAlertWarningYesOnly(this, Constraints.APP_NOT_INSTALLED,Constraints.OK);
                }
            }
            else if (bankName.toUpperCase().equals(DeclarationConf.HOST_TYPES_DBS.toUpperCase())) {
                 String chkValue = CheckPaymentApp.existOrNotApp(CheckOutActivity.this,DeclarationConf.PACKAGE_NAME_DBS);
                 if (chkValue.equals("1")) {
                    SendRequestForActivityResultFun(payment_type_name,cardamount,DeclarationConf.PACKAGE_NAME_DBS, DeclarationConf.CLASS_NAME_DBS);
                } else {
                     SweetAlertWarningYesOnly(this, Constraints.APP_NOT_INSTALLED,Constraints.OK);
                 }
            }  else if (bankName.toUpperCase().equals(DeclarationConf.HOST_TYPES_BOC.toUpperCase())) {
                String chkValue = CheckPaymentApp.existOrNotApp(CheckOutActivity.this,DeclarationConf.PACKAGE_NAME_BOC);
                if (chkValue.equals("1")) {
                    SendRequestForActivityResultFun(payment_type_name,cardamount,DeclarationConf.PACKAGE_NAME_BOC, DeclarationConf.CLASS_NAME_BOC);
                } else {
                    SweetAlertWarningYesOnly(this, Constraints.APP_NOT_INSTALLED,Constraints.OK);
                }
            } else if (bankName.toUpperCase().equals(DeclarationConf.HOST_TYPES_AMERICAN_EXPRESS.toUpperCase())) {


            } else if (bankName.toUpperCase().equals(DeclarationConf.HOST_TYPES_UPI.toUpperCase())) {


            } else if (bankName.toUpperCase().equals(DeclarationConf.HOST_TYPES_JCB.toUpperCase())) {


            } else if (bankName.toUpperCase().equals(DeclarationConf.HOST_TYPES_IPP.toUpperCase())) {


            } else if (bankName.toUpperCase().equals(DeclarationConf.HOST_TYPES_DINERS.toUpperCase())) {


            } else if (bankName.toUpperCase().equals(DeclarationConf.HOST_TYPES_ASCAN.toUpperCase())) {


            } else if (bankName.toUpperCase().equals(DeclarationConf.HOST_TYPES_JERIPAY.toUpperCase())) {

//                if (1==1) {
//                    String chkStatus = "0";
//                    try {
//                        SendRequestForActivityResultFun(payment_type_name,cardamount,DeclarationConf.PACKAGE_NAME_JERIPAY, DeclarationConf.CLASS_NAME_JERIPAY);
//
//                    } catch (Exception e){
//                        Log.i("EREX___","exception___"+e.getMessage());
//                        chkStatus = "1";
//                    }
//                    Log.i("EREX___","echkStatus___"+chkStatus);
////                    if (chkStatus.equals("1")) {
////                        //Query.SweetAlertWarningYesOnly(CheckOutActivity.this,"Application is not currently installed.","OK");
////                        new AlertDialog.Builder(getApplicationContext(), R.style.AlertDialogStyle)
////                                .setMessage("Application is not currently installed.")
////                                .setCancelable(false)
////                                .setNegativeButton(Constraints.OK, null)
////                                .show();
////                    }
//                } else {
                    Log.i("bankName__","bankName___"+bankName+"__"+DeclarationConf.HOST_TYPES_JERIPAY);
                    String chkValue = CheckPaymentApp.existOrNotApp(CheckOutActivity.this,DeclarationConf.PACKAGE_NAME_JERIPAY);

                    if (chkValue.equals("1")) {
                        SendRequestForActivityResultFun(payment_type_name, cardamount, DeclarationConf.PACKAGE_NAME_JERIPAY, DeclarationConf.CLASS_NAME_JERIPAY);
                    } else {
                        SweetAlertWarningYesOnly(this, Constraints.APP_NOT_INSTALLED,Constraints.OK);
                    }

            }  else if (bankName.toUpperCase().equals(DeclarationConf.HOST_TYPES_MECATUS.toUpperCase())) {
                String chkValue = CheckPaymentApp.existOrNotApp(CheckOutActivity.this,DeclarationConf.PACKAGE_NAME_MERCATUS);
                if (chkValue.equals("1")) {
                    new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText(Constraints.MercatusMember)
                            .setConfirmText(Constraints.YES)
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismissWithAnimation();

                                    str_member = "1";

                                    SendRequestForActivityResultFun(payment_type_name,cardamount,DeclarationConf.PACKAGE_NAME_MERCATUS,
                                            DeclarationConf.CLASS_NAME_MERCATUS);
                                }
                            })
                            .setCancelButton(Constraints.No, new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismissWithAnimation();

                                    str_member = "0";

                                    SendRequestForActivityResultFun(payment_type_name,cardamount,DeclarationConf.PACKAGE_NAME_MERCATUS,
                                            DeclarationConf.CLASS_NAME_MERCATUS);
                                }
                            })
                            .show();

                } else {
                    SweetAlertWarningYesOnly(this, Constraints.APP_NOT_INSTALLED,Constraints.OK);
                }

            } else if (bankName.toUpperCase().equals(DeclarationConf.HOST_TYPES_GLOBAL_PAYMENT.toUpperCase())) {
                String chkValue = CheckPaymentApp.existOrNotApp(CheckOutActivity.this,DeclarationConf.PACKAGE_NAME_GLOBALPAYMENT);
                if (chkValue.equals("1")) {

                    String payment_card_type = "";
                    if (payment_type_name.replace(" ", "").toUpperCase().equals(DeclarationConf.CARD_TYPE_WECHAT_PAY.toUpperCase())) {
                        //WeChatPay
                        payment_card_type = DeclarationConf.CARD_TYPE_WECHAT_PAY;

                    } else if (payment_type_name.replace(" ", "").toUpperCase().equals(DeclarationConf.CARD_TYPE_GRAB_PAY.toUpperCase())) {
                        //CARD_TYPE_GRAB_PAY
                        payment_card_type = DeclarationConf.CARD_TYPE_GRAB_PAY;
                    } else if (payment_type_name.replace(" ", "").toUpperCase().equals(DeclarationConf.CARD_TYPE_PAYNOW.toUpperCase())) {
                        //PayNow
                        payment_card_type = DeclarationConf.CARD_TYPE_PAYNOW;
                    } else if (payment_type_name.replace(" ", "").toUpperCase().equals(DeclarationConf.CARD_TYPE_ALIPAY.toUpperCase())) {
                        //AliPay
                        payment_card_type = DeclarationConf.CARD_TYPE_ALIPAY;
                    } else if (payment_type_name.replace(" ", "").toUpperCase().equals(DeclarationConf.CARD_TYPE_EZLINK.toUpperCase())) {
                        //Ezlink
                        payment_card_type = DeclarationConf.CARD_TYPE_EZLINK;
                    } else if (payment_type_name.replace(" ", "").toUpperCase().equals(DeclarationConf.CARD_TYPE_OTHERS.toUpperCase())) {
                        payment_card_type = DeclarationConf.CARD_TYPE_OTHERS;
                    } else {

                    }

//          VISA, MASTERCARD, JCB, AMEX, UNIONPAY, Alipay, WechatPay
                    //double val_amount_gp = val_amount * 100;
                    double val_amount_gp = cardamount * 100;

                    globalPymentSaleRequest((long) val_amount_gp, payment_card_type);
                } else {
                    SweetAlertWarningYesOnly(this, Constraints.APP_NOT_INSTALLED,Constraints.OK);
                }
            }
        }
    }

    String str_member = "";
    public void SendRequestForActivityResultFun(String payment_type_name, Double cardamount, String requestPackageName, String requestClassName) {
        Integer requstCode = DeclarationConf.EVENT_COMMON_CODE_REQUEST;
        Intent launchIntent = new Intent();
        launchIntent.setClassName(requestPackageName, requestClassName);
        if (requestPackageName.equals(DeclarationConf.PACKAGE_NAME_JERIPAY) || requestPackageName.equals(DeclarationConf.PACKAGE_NAME_MERCATUS)){
            launchIntent.setAction(Intent.ACTION_VIEW);
        }
        launchIntent.setFlags(0);
        //launchIntent.setFlags( Intent.FLAG_ACTIVITY_NO_HISTORY);
        Bundle bundleApp = new Bundle();


        if (payment_type_name.replace(" ", "").toUpperCase().equals(DeclarationConf.CARD_TYPE_WECHAT_PAY.toUpperCase())) {
            //WeChatPay
            bundleApp.putString("Request", sendQRAndBarcodeObject(String.format("%.2f", (cardamount * 100))));
            launchIntent.putExtras(bundleApp);

        } else if (payment_type_name.replace(" ", "").toUpperCase().equals(DeclarationConf.CARD_TYPE_GRAB_PAY.toUpperCase())) {
            //CARD_TYPE_GRAB_PAY

        } else if (payment_type_name.replace(" ", "").toUpperCase().equals(DeclarationConf.CARD_TYPE_PAYNOW.toUpperCase())) {
            //PayNow

        } else if (payment_type_name.replace(" ", "").toUpperCase().equals(DeclarationConf.CARD_TYPE_ALIPAY.toUpperCase())) {
            //AliPay

        } else if (payment_type_name.replace(" ", "").toUpperCase().equals(DeclarationConf.CARD_TYPE_EZLINK.toUpperCase())) {
            //Ezlink
            launchIntent = AscanPaymentFormat.ezlink(bundleApp,launchIntent,cardamount);
            requstCode = DeclarationConf.EVENT_EZLINK_CODE_REQUEST;
            //startActivityForResult(launchIntent, 6699);

        } else if (payment_type_name.replace(" ", "").toUpperCase().equals(DeclarationConf.CARD_TYPE_OTHERS.toUpperCase())) {
            if (requestPackageName.equals(DeclarationConf.PACKAGE_NAME_JERIPAY)){
                launchIntent =  JeripayPaymentFormat.launchIntent(launchIntent,BillNo,cardamount);

            } else if (requestPackageName.equals(DeclarationConf.PACKAGE_NAME_MERCATUS)){
                if ( str_member.equals("1")) { // Member
                    launchIntent = JeripayPaymentFormat.mercatusMember(launchIntent,cardamount);
                } else { // not memeber
                    launchIntent = JeripayPaymentFormat.mercatusNotMember(launchIntent,cardamount);
                }
            }else {
                //Others
                //Credit Card
                //Debit Card
                //Apple Pay
                bundleApp.putString("Request", sendSaleObject(String.format("%.2f", (cardamount * 100)), DeclarationConf.EVENT_SALE));
                launchIntent.putExtras(bundleApp);
            }
            requstCode = DeclarationConf.EVENT_JERIPAY_CODE_REQUEST;
        } else {

        }

        try {

            startActivityForResult(launchIntent,requstCode);
        } catch (Exception e){
            //If M2 and not installed app , then will show this dialog box
            error = "1";
            Log.i("SDfds___","dsf_e__"+e.getMessage());
        }
    }

    public void globalPymentSaleRequest(long val_amount,String payment_card_type) {
        SaleMsg.Request request = new SaleMsg.Request();
        request.setAppId(DeclarationConf.PACKAGE_NAME_GLOBALPAYMENT_APP_ID);
        request.setPackageName(DeclarationConf.PACKAGE_NAME_GLOBALPAYMENT);
        request.setAmount(val_amount);
        request.setTipAmount(0);
        request.setPrint(true);
        ITransAPI transAPI = TransAPIFactory.createTransAPI(CheckOutActivity.this);
        boolean ret = transAPI.doTrans(request);
    }

    private String sendQRAndBarcodeObject(String amount) {
        JSONObject jsonObject = new JSONObject();
        try {
        jsonObject.put("transaction_amount", getConvertedAmount(amount));
        jsonObject.put("entry_mode", "'S'");
        jsonObject.put("transaction_type", "C701");
        jsonObject.put("command_identifier", "1");

        } catch (JSONException e) {
        e.printStackTrace();
        }
        //processJsonRequest = jsonObject.toString();
        return jsonObject.toString();
        }
    private String nonMemberTransaction(String amount) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("'command'", "nonMember");
            jsonObject.put("'amount'", getConvertedAmount(amount));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        //processJsonRequest = jsonObject.toString();
        return jsonObject.toString();
        }

    public static String getConvertedAmount(String amount) {

        double l = Double.parseDouble(amount);
        l = l / 100;
        return REAL_FORMATTER.format(l);

    }
    //Just1
//    public static void SaveBill(Integer BillID,String BillNo,ArrayList<String> sldNameArr,ArrayList<Integer> sldQtyArr
//            ,ArrayList<String> sltPriceTotalArr,ArrayList<String> sltBillDisArr
//            ,ArrayList<String> sltCategoryNameArr,Integer qty,Integer totalItems
//            ,ArrayList<String> sldIDArr,ArrayList<String> sltCategoryIDArr,Integer countSelectedArr
//            ,String dateFormat3,String status,Double sub_total,Double amount,String paymenttype,ArrayList<Integer> Modifier_ID
//             ,ArrayList<String> vchQueueNo,ArrayList<String> intTableNo,String Itemstatus,
//                                ArrayList<String> slddisID ,ArrayList<String> slddisName ,ArrayList<String> slddisTypeName,ArrayList<String> slddisType,ArrayList<String> slddisValue) {
////        String sql = "UPDATE Bill SET CloseDateTime = " + System.currentTimeMillis() + " WHERE BillNo = " + MainActivity.BillID;
////        DBFunc.ExecQuery(sql, false);
//
//
//        SaveBillDetails(BillID,BillNo,sldNameArr,sldQtyArr,sltPriceTotalArr,sltBillDisArr,sltCategoryNameArr,qty,totalItems,sldIDArr
//        ,sltCategoryIDArr,countSelectedArr,dateFormat3,status,sub_total,amount,paymenttype,Modifier_ID,vchQueueNo,intTableNo,Itemstatus,
//                 slddisID ,slddisName ,slddisTypeName,slddisType,slddisValue);
//    }

//    public static void SaveBillDetails(Integer BillID,String BillNo,ArrayList<String> sldNameArr,ArrayList<Integer> sldQtyArr
//            ,ArrayList<String> sltPriceTotalArr,ArrayList<String> sltBillDisArr
//            ,ArrayList<String> sltCategoryNameArr,Integer qty,Integer totalItems
//            ,ArrayList<String> sldIDArr,ArrayList<String> sltCategoryIDArr,Integer countSelectedArr,
//                                       String dateFormat3,String status,Double sub_total,Double amount,String paymenttype,ArrayList<Integer> Modifier_ID
//            ,ArrayList<String> vchQueueNo,ArrayList<String> intTableNo,String Itemstatus,ArrayList<String> slddisID ,ArrayList<String> slddisName ,ArrayList<String> slddisTypeName,ArrayList<String> slddisType,ArrayList<String> slddisValue) {
//
//
//            saveDetailsBillProduct(BillID,BillNo,sldNameArr,sldQtyArr,sltPriceTotalArr,
//                    sltBillDisArr,sltCategoryNameArr,qty,sldIDArr,sltCategoryIDArr,dateFormat3,Modifier_ID,vchQueueNo,intTableNo,status,Itemstatus,
//                    slddisID ,slddisName ,slddisTypeName,slddisType,slddisValue);
//    }

//    public static void saveDetailsBillProduct(Integer BillID,String BillNo,ArrayList<String> sldNameArr,ArrayList<Integer> sldQtyArr
//                                               ,ArrayList<String> sltPriceTotalArr,ArrayList<String> sltBillDisArr
//                                               ,ArrayList<String> sltCategoryNameArr,Integer qty
//                                               ,ArrayList<String> sldIDArr,ArrayList<String> sltCategoryIDArr,
//                                               String dateFormat3,ArrayList<Integer> Modifier_ID
//                                                ,ArrayList<String> vchQueueNo,ArrayList<String> intTableNo,String status,String Itemstatus,
//                                               ArrayList<String> slddisID ,ArrayList<String> slddisName ,ArrayList<String> slddisTypeName,ArrayList<String> slddisType,ArrayList<String> slddisValue) {
public static void saveDetailsBillProduct(Integer BillID,String BillNo,String sldNameArr
                                               ,String sltPriceTotalArr,String sltBillDisArr
                                               ,String sltCategoryNameArr,Integer qty
                                               ,String sldIDArr,String sltCategoryIDArr,
                                               String dateFormat3,ArrayList<Integer> Modifier_ID
                                                ,String vchQueueNo,String intTableNo,String status,String Itemstatus,
                                               String slddisID ,String slddisName ,String slddisTypeName,
                                                String slddisType,String slddisValue,Integer EditProductSheetFragment_ID,
                                          String openPrice,String strRemarks) {

        //for (int i = 0 ; i < sldIDArr.size(); i++) {
            String OnlineOrderBill = Query.GetOnlineOrderBillStatus(BillNo);

            String str_Cancel = Query.ItemStatus(Itemstatus);

    Log.i("Sdfdsf___","dsfsDiscountValue2__"+slddisValue);
            Query.saveDetailsBillProduct_AssignValue("checkout",BillID, BillNo, OnlineOrderBill, qty, sldNameArr, sltPriceTotalArr,
                    sltBillDisArr, sldIDArr, vchQueueNo, intTableNo,
                    str_Cancel, slddisID, slddisName, slddisTypeName, slddisType, slddisValue,EditProductSheetFragment_ID,openPrice,
                    strRemarks);

//            if (!st_sldNameArr.equals("0")) {
//                Query.saveDetailsBillProduct_AssignValue(BillID, BillNo, OnlineOrderBill, st_sldQtyArr, st_sldNameArr, st_sltPriceTotalArr,
//                          st_sltBillDisArr, st_sldIDArr, str_vchQueueNo, str_intTableNo,
//                        str_Cancel, st_slddisID, st_slddisName, st_slddisTypeName, st_slddisType, st_slddisValue);
//            }
        // }
        SaveBillPLU(sldIDArr,sldNameArr,sltPriceTotalArr,BillID,BillNo,qty,Modifier_ID,Itemstatus);
    }

    private static String GetTryCatchVal(String val) {
        String str = "";
        if (val != null) {
            try {
                str = val;
            } catch (IndexOutOfBoundsException e) {
                str = "0";
            }
        } else {
            str = "0";
        }
        return str;
    }

    //    sql += Integer.parseInt(RecyclerViewAdapter.sltPriceTotalArr.get(i)) + ", ";
//    public static void SaveBillPLU(ArrayList<String> sldIDArr,ArrayList<String> sldNameArr,
//                                   ArrayList<String> sltPriceTotalArr,Integer BillID,String BillNo,Integer qty,ArrayList<Integer> Modifier_ID,String Itemstatus) {
public static void SaveBillPLU(String sldIDArr,String sldNameArr,
                                   String sltPriceTotalArr,Integer BillID,String BillNo,Integer qty,
                               ArrayList<Integer> Modifier_ID,String Itemstatus) {

        //for (int i = 0; i < sldIDArr.size(); i ++){
            String str_Cancel = "";
            if (Itemstatus.equals(Constraints.SALES)){
                str_Cancel = Constraints.SALES;
            }else if (Itemstatus.equals(Constraints.CANCEL)){
                str_Cancel = Constraints.CANCEL;
            }else{
                str_Cancel = Constraints.VOID;
            }
            String listString = Query.GetModifier(Modifier_ID);

            Query.SaveBillPLU(BillID,BillNo,sldIDArr,
                    sldNameArr,sltPriceTotalArr,qty,str_Cancel,listString);
//            Query.SaveBillPLU(BillID,BillNo,sldIDArr.get(i),
//                    sldNameArr.get(i),sltPriceTotalArr.get(i),qty,str_Cancel,listString);

            //Integer bill_plu_id = Query.findLatestID("idx","BillPLU",false);
            //calculateTaxForPLU(bill_plu_id,Integer.parseInt(sldIDArr.get(i)));

            ButtonAdapter.ID.clear();

       // }

//        String chk_hide_img = Query.GetOptions(20);
//        if (chk_hide_img.equals("1")){
//            RecyclerViewNoImageAdapter.count = 0;
//            RecyclerViewNoImageAdapter.countSelectedArr = 0;
//            RecyclerViewNoImageAdapter.sldNameArr.clear();
//            RecyclerViewNoImageAdapter.sldQtyArr.clear();
//            RecyclerViewNoImageAdapter.sldIDArr.clear();
//            RecyclerViewNoImageAdapter.sltPriceTotal = 0.0;
//            RecyclerViewNoImageAdapter.str_sltPriceTotal = "0";
//            RecyclerViewNoImageAdapter.sltPriceTotalArr.clear();
//            //RecyclerViewAdapter.chksldQtyArr.clear();
//            RecyclerViewNoImageAdapter.sldCategoryIDArr.clear();
//            RecyclerViewNoImageAdapter.sldCategoryNameArr.clear();
//        }else {
            RecyclerViewAdapter.count = 0;
            RecyclerViewAdapter.countSelectedArr = 0;
            RecyclerViewAdapter.sldNameArr.clear();
            RecyclerViewAdapter.sldQtyArr.clear();
            RecyclerViewAdapter.sldIDArr.clear();
            RecyclerViewAdapter.sltPriceTotal = 0.0;
            RecyclerViewAdapter.str_sltPriceTotal = "0";
            RecyclerViewAdapter.sltPriceTotalArr.clear();
            //RecyclerViewAdapter.chksldQtyArr.clear();
            RecyclerViewAdapter.sldCategoryIDArr.clear();
            RecyclerViewAdapter.sldCategoryNameArr.clear();
            RecyclerViewAdapter.slddisID.clear();
            RecyclerViewAdapter.slddisName.clear();
            RecyclerViewAdapter.slddisTypeName.clear();
            RecyclerViewAdapter.slddisType.clear();
            RecyclerViewAdapter.slddisValue.clear();
//        }
    }
    private void MainPage() {
//        // Construct the Intent you want to end up at
//        Intent detailActivity = new Intent(this, MainActivity.this);
//// Construct the PendingIntent for your Notification
//        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
//// This uses android:parentActivityName and
//// android.support.PARENT_ACTIVITY meta-data by default
//        stackBuilder.addNextIntentWithParentStack(detailActivity);
//        PendingIntent pendingIntent = stackBuilder
//                .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
////        MainActivity.status_setting = "0";
//        MainActivity.St = "1";
        if (StatusSALES.equals("ON")){
//            //ActivityCompat.finishAffinity(CheckOutActivity.this);
            Intent i = new Intent(CheckOutActivity.this, MainActivity.class);
            i.putExtra("name", "OnlineOrderBill");
            startActivity(i);
            finish();
//            MainActivity.updateMediaButtons();
        }else {
//            MainActivity.St = "1";
////        ProductMainPageFragment.St = "1";
////        RecyclerViewAdapter.St = "1";
////            ActivityCompat.finishAffinity(CheckOutActivity.this);
            Intent i = new Intent(CheckOutActivity.this, MainActivity.class);
            i.putExtra("name", "CheckoutActivity");
            startActivity(i);
            finish();
//            CheckOutActivity.updateMediaButtons();
        }
    }


//    private static void calculateTaxForPLU(Integer billpluID,Integer pluID) {
//        Double total_tax = 0.0;
//        Double totalPrice = 0.0;
//        Integer taxID = 0;
//        Cursor CurorPLU = DBFunc.Query("SELECT ProductTaxID,Price FROM PLU WHERE ID = " + pluID, true);
//        if (CurorPLU != null){
//            if (CurorPLU.moveToNext()){
//                taxID = CurorPLU.getInt(0);
//                totalPrice = CurorPLU.getDouble(1);
//                Cursor plutax = DBFunc.Query("SELECT ID,Name,Acronym,Rate,Type,Seq FROM Tax WHERE ID = " + taxID, true);
//                if (plutax != null) {
//                    double _tax = 0;
//                    total_tax = 0.0;
//                    Double gst_calculate_tax = 0.0;
//                    if (plutax.moveToNext()) {
//                        tax_Seq = plutax.getInt(4);
//                        if (plutax.getInt(4) == 1) {// not include(add on tax)
//                            gst_calculate_tax = plutax.getDouble(3);
//                            //inclusive gst : total price (after discount)  - (total price /  (1 + (7/100))
////                            gst_calculate_tax = plutax.getDouble(3) - 0.46;
////
////                            _tax = (totalPrice * (gst_calculate_tax / 100f));
//                            _tax = totalPrice - (totalPrice / (1 + (plutax.getDouble(3)/100)));
//
//                        } else if (plutax.getInt(4) == 2) {// calculate on base price (add on tax)
//                            //gst_calculate_tax = plutax.getDouble(3) - 0.46;
//                            gst_calculate_tax = plutax.getDouble(3);
//                            _tax = (totalPrice * (gst_calculate_tax / 100f));
//                            //exclusive gst : total price (after discount) * (7/100)
//
//                        }
//                        //total_tax += _tax;
//                        //total_tax = _tax;
//                        total_tax = gst_calculate_tax;
//                        Log.i("Df__total_tax", String.valueOf(total_tax));
//                        //str_taxname = plutax.getString(1) + "( " + plutax.getInt(3) + " % )";
//                        String BillPLU_idx = String.valueOf(pluID);
//                        Integer TaxID = taxID;
//                        String Name = plutax.getString(1);
//                        String Acronym = plutax.getString(2);
//                        Double Rate = plutax.getDouble(3);
//                        Integer Type = plutax.getInt(4);
//                        Integer Seq = plutax.getInt(5);
//
//                        Query.BillPLUTax(billpluID,taxID,Name,Acronym,Rate,Type,Seq,total_tax);
//
//                    }
//                    plutax.close();
//                }
//            }
//            CurorPLU.close();
//        }
//        Log.i("__total_tax", String.valueOf(total_tax));
//        //return total_tax;
//    }

//    private static void calculateTax() {
//        Double totalPrice = sub_total;
//        //Cursor plutax = DBFunc.Query("SELECT TaxID,Name,Acronym,Rate, Type FROM Tax WHERE Status = '1' ", true);
//        Cursor plutax = DBFunc.Query("SELECT ID,Name,Acronym,Rate,Type,Seq FROM Tax ", true);
//        String tax_acronym = "";
//        if (plutax != null) {
//            double _tax = 0;
//            total_tax = 0.0;
//            Double gst_calculate_tax = 0.0;
//            while (plutax.moveToNext()) {
//                tax_Seq = plutax.getInt(4);
//                //if (plutax.getInt(4) == 0) {// not include(add on tax)
//                if (plutax.getInt(4) == 1) {// not include(add on tax)
//                    gst_calculate_tax = plutax.getDouble(3) - 0.46;
//                    //_tax = (totalPrice * (plutax.getDouble(3) / 100f));
//                    _tax = (totalPrice * (gst_calculate_tax / 100f));
//                    str_taxname = plutax.getString(1) + "( " + plutax.getInt(3) + " % )";
//                    //totalPrice += _tax;
//                } else if (plutax.getInt(4) == 2) {// is inclusive (VAT)
////                } else if (plutax.getInt(4) == 1) {// is inclusive (VAT)
//                    gst_calculate_tax = plutax.getDouble(3) - 0.46;
//                    //_tax = (totalPrice - (totalPrice / (1f + (plutax.getDouble(3) / 100f))));
//                    _tax = (totalPrice - (totalPrice / (1f + (gst_calculate_tax / 100f))));
//                    str_taxname = plutax.getString(1) + "( " + plutax.getInt(3) + " % )";
//                    //itemprice = (itemprice / (1f + (plutax.getDouble(3) / 100f)));
//                } else if (plutax.getInt(4) == 3) {// calculate on base price (add on tax)
////                } else if (plutax.getInt(4) == 2) {// calculate on base price (add on tax)
//                    // _tax = (itemprice_base * (plutax.getDouble(3) / 100f));
//                    gst_calculate_tax = plutax.getDouble(3) - 0.46;
//                    //_tax = (totalPrice * (plutax.getDouble(3) / 100f));
//                    _tax = (totalPrice * (gst_calculate_tax / 100f));
//                    str_taxname = plutax.getString(1) + "( " + plutax.getInt(3) + " % )";
//                    //itemprice += _tax;
//                }
//
//                if (!plutax.isNull(2) && !plutax.getString(2).isEmpty()) {
//                    tax_acronym = plutax.getString(2);
//                }
//                total_tax += _tax;
//                //Log.i("TAXXX", String.valueOf(_tax));
//                //Log.i("TAXtemprice", String.valueOf(itemprice));
//            }
//            plutax.close();
//           // _tax = (totalPrice * (7 / 100f));
//           // total_tax = _tax;
//        }
//        //total_tax = (totalPrice * (7 / 100f));
//        Log.i("__total_tax", String.valueOf(total_tax));
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.activity_main_actions, menu);
//        return true;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_checkout_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_manage_bill) {

            final String cancel_status = Query.getCancelByBillNo(CheckOutActivity.BillNo);
            if (cancel_status.toUpperCase().equals("CANCEL")) {

                new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Cancelled Bill")
                        //.setContentText("Won't be able to recover this file!")
                        //.setConfirmText("Yes,delete it!")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                //sDialog.dismissWithAnimation();
                                sDialog.dismissWithAnimation();

                            }
                        })
//                        .setCancelButton("Cancel", new SweetAlertDialog.OnSweetClickListener() {
//                            @Override
//                            public void onClick(SweetAlertDialog sDialog) {
//                                sDialog.dismissWithAnimation();
//                            }
//                        })
                        .show();
            }else {
//            if (Status.equals("SALES")) {
//            if (StatusSALES.isEmpty()) {
                ManageBillFragment manageBillFragment = new ManageBillFragment();
                manageBillFragment.show(getSupportFragmentManager(), manageBillFragment.getTag());
                // manageBillFragment.show(FragmentManager, manageBillFragment.getTag());
//            }
//            }
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        AlertDialog.Builder alert = new AlertDialog.Builder(this, R.style.AlertDialogStyle);
//
//        alert.setTitle(POPUP_LOGIN_TITLE);
//        alert.setMessage(POPUP_LOGIN_TEXT);
//
//        // Set an EditText view to get user input
//        final EditText email = new EditText(this);
//        //email.setHint(EMAIL_HINT);
//        email.setText("1");
//        email.setTextColor(getResources().getColor(R.color.mine_shaft));
//        email.setBackgroundColor(getResources().getColor(R.color.allproduct_bg_color_1));
//
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                100
//        );
//        params.setMargins(40, 10, 40,10 );
//        //email.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
//        email.setInputType(InputType.TYPE_CLASS_NUMBER);
//        email.setLayoutParams(params);
//
//        email.setPadding(25,5,5,5);
//        //email.setLayoutParams(new LinearLayout.LayoutParams(400, 50));
//        //email.setLayoutParams(new LinearLayout.LayoutParams(50, 50));
//
////        final TextView password = new TextView(this);
////        //password.setHint(PASSWORD_HINT);
////        password.setText("Product Name");
////        password.setTextColor(getResources().getColor(R.color.mine_shaft));
////        //password.setLayoutParams(new LinearLayout.LayoutParams(50, 50));
//
//        //final TextView price = new TextView(this);
//        //password.setHint(PASSWORD_HINT);
//        //price.setText("10");
//        //price.setTextColor(getResources().getColor(R.color.mine_shaft));
//        //price.setLayoutParams(new LinearLayout.LayoutParams(50, 50));
//
//        LinearLayout layout = new LinearLayout(getApplicationContext());
//
////        ViewGroup.LayoutParams params = layout.getLayoutParams();
//// Changes the height and width to the specified *pixels*
////        params.height = 100;
////        params.width = 300;
////        layout.setLayoutParams(params);
//        layout.setOrientation(LinearLayout.HORIZONTAL);
//        layout.setLayoutParams(new LinearLayout.LayoutParams(300, 300));
//        layout.addView(email);
//        //layout.addView(password);
//        //layout.addView(price);
//        alert.setView(layout);
//
////        MyCustomDialog builder = new MyCustomDialog(getApplicationContext(), "Try Again", "");
////        final AlertDialog dialog = builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
////
////            @Override
////            public void onClick(DialogInterface dialogInterface, int i) {
////
////            }
////
////        }).create();
////
//////2. now setup to change color of the button
////        dialog.setOnShowListener( new DialogInterface.OnShowListener() {
////            @Override
////            public void onShow(DialogInterface arg0) {
////                dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(COLOR_I_WANT);
////            }
////        });
////
////        dialog.show();
////
//        alert.setPositiveButton("Update", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int whichButton) {
//                String sql = "UPDATE Tax SET Name = '"+edit_text_tax_name.getText().toString()+"', Acronym = '"+edit_text_tax_actonym.getText().toString()
//                        +"', Rate = "+edit_text_tax_rate.getText().toString()+", Type = "+tax_type_id+", Seq = "+seq+" WHERE ID = "+taxID;
//                Log.i("__sql",sql);
//                DBFunc.ExecQuery(sql, true);
//                // Do something with value!
//            }
//        });
//        alert.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int whichButton) {
//                // Canceled.
//            }
//        });
//
//        alert.show();
//    }

//    private class SendData1 extends AsyncTask<Object, String, Object> {
//        @Override
//        protected void onPreExecute() {
//
//        }
//
//        //        protected void onPostExecute(Intent result) {
////            Log.i("resultsdsdsd", String.valueOf(result));
////        }
//        @SuppressWarnings({"unchecked", "deprecation"})
//        @Override
//        protected Object doInBackground(Object... params) {
//            Intent launchIntent = (Intent) params[0];
//            CheckOutActivity.this.startActivityForResult(launchIntent, 6699);
//            return null;
//        }
//
//        //@Override
//        protected void onPostExecute(String result) {
//        }
//
//    }


    public static String sendSaleObject(String amount, String commandCode) {
        Log.i("Dg__commandCode","dfdf__"+commandCode);
        Log.i("Dg__commandCode","dfdfamt__"+getConvertedAmount(amount));
        JSONObject jsonObject = new JSONObject();
        try {
            if (commandCode.equals(DeclarationConf.EVENT_EZLINK_SALE)) {
//                jsonObject.put("transaction_amount", getConvertedAmount(amount));
////                jsonObject.put("transaction_type", "C640");
//                jsonObject.put("transaction_type", "C200");
//                jsonObject.put("command_identifier", "1");
//                jsonObject.put("custom_data_3", "EZLINK");
////                jsonObject.put("transaction_amount", getConvertedAmount(amount));
////                jsonObject.put("transaction_type", "C640");
////                jsonObject.put("command_identifier", "1");
////                jsonObject.put("custom_data_3", "CEPAS");


//                jsonObject.put("transaction_amount", getConvertedAmount(amount));
//                jsonObject.put("transaction_type", "C200");
//                jsonObject.put("command_identifier", "1");
//                //jsonObject.put("custom_data_3", "EZLINK");

                jsonObject.put("transaction_amount", getConvertedAmount(amount));
                jsonObject.put("transaction_type", "C610");
                jsonObject.put("command_identifier", getCurrentDate());
            } else {
                jsonObject.put("transaction_amount", getConvertedAmount(amount));
                jsonObject.put("transaction_type", commandCode);
                jsonObject.put("command_identifier", "1");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        //processJsonRequest = jsonObject.toString();
        DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                System.currentTimeMillis(), DBFunc.PurifyString("JSON-Sync-CheckoutActivity-" + jsonObject));
        Log.i("Df___","jsonObject___"+jsonObject);
        return jsonObject.toString();
    }

    public static String getCurrentDate() {
        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateToStr = format.format(today);
        return dateToStr;
    }
//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Log.i("Sdfsafdsf_",RecyclerViewAdapter.sldIDArr.get(position));
//    }
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    super.onActivityResult(requestCode, resultCode, data);
    bankName = Query.GetBankNameFun();

//        CashValueArr.clear();
//        CashValuePaymentNameArr.clear();
//        CashValue = "0.0";
//
//        CashValuePaymentName = "";
//        CashValue = "";
//        CashLayoutActivity.CashValuePaymentName = "";
//        CashLayoutActivity.CashValue = 0.0;
    //"Response":

    DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
            System.currentTimeMillis(), DBFunc.PurifyString("OnActivityResult -requestCode-" + requestCode + " ,resultCode-" +
                    resultCode + ",data-" + data +",Payment-"+ PaymentTypesCheckoutAdapter.paymentName + ",bankName-" + bankName));
    if (!(data == null)) {
        if (data.hasExtra("Response")) {
            String responseDataLog = data.getStringExtra("Response");
            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                    System.currentTimeMillis(), DBFunc.PurifyString("OnActivityResult-Response " + responseDataLog));
        }
    }

    if (!(data == null)) {
        if (data.hasExtra("transaction")) {
            String responseDataLog = data.getStringExtra("transaction");
            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                    System.currentTimeMillis(), DBFunc.PurifyString("OnActivityResult-Response " + responseDataLog));
        }
    }
    Log.i("SDFREsponse___","Sdfd__data"+data);
    if (!(data == null)) {
        String responseData;
        if (requestCode == 9999 || PaymentTypesCheckoutAdapter.paymentName.toUpperCase().equals(DeclarationConf.CARD_TYPE_EZLINK)) {


            if (data.hasExtra("Response")) {
                responseData = data.getStringExtra("Response");
//                    responseData = "{\"command_identifier\":\"20211025095236\"," +
//                            "\"custom_data_2\":\"Transaction Aborted\"," +
//                            "\"response_code\":\"TA\",\"transaction_type\":\"R610\"}";

//                    responseData = responseData = "{\"additional_printing_flag\":\"\",\"approval_code\":\"000001\",\n" +
//                        "                            \"batch_number\":\"000002\",\"card_holder_name\":\"\",\n" +
//                        "                            \"card_label\":\"EZLINK\",\"card_number\":\"XXXXXXXXXXXX6011\",\n" +
//                        "                            \"card_type\":\"E\",\"command_identifier\":\"\",\n" +
//                        "                            \"coupons_vouchers\":\"\",\"custom_data_2\":\"\",\n" +
//                        "                            \"custom_data_3\":\"\",\n" +
//                        "                            \"date_time\":\"20210918190101\",\n" +
//                        "                            \"destination_package_name\":\"\",\n" +
//                        "                            \"ecr_unique_trace_number\":\"\",\n" +
//                        "                            \"employee_id\":\"\",\"emv_data\":\"\",\n" +
//                        "                            \"entry_mode\":\"C\",\"expiry_date\":\"\",\n" +
//                        "                            \"external_device_invoice\":\"\",\n" +
//                        "                            \"host_label\":\"EPAY\",\"host_type\":\"Z\",\n" +
//                        "                            \"invoice_number\":\"000008\",\n" +
//                        "                            \"merchant_id\":\"600054000001639\",\n" +
//                        "                            \"original_trans_type\":\"000495\",\"response_code\":\"00\",\n" +
//                        "                            \"retrieval_reference_number\":\"210918190101\",\n" +
//                        "                            \"source_package_name\":\"\",\"terminal_id\":\"60010009\",\n" +
//                        "                            \"transaction_amount\":\"000000000001\",\n" +
//                        "                            \"transaction_info\":\"\",\"transaction_type\":\"R610\"}" ;

//
//                    Log.i("responseData___", "responseDataezlink___" + responseData);

                JSONObject res_data = null;
                String response_code = "";
                if (responseData != null) {
                    try {
                        res_data = new JSONObject(responseData);
                        response_code = res_data.getString("response_code");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

//                    {"command_identifier":"20211025095236","custom_data_2":"Transaction Aborted","response_code":"TA","transaction_type":"R610"}
                String cardLabel = "";
                if (response_code.equals("00")) {

                    try {
                        JSONObject boc_res_data = new JSONObject(responseData);
//                            {"additional_printing_flag":"","approval_code":"174433","batch_number":"000659",
//                                    "card_holder_name":"TAN SOO KWEI/             ","card_label":"MASTERCARD",
//                                    "card_number":"XXXXXXXXXXXX8595","card_type":"M","command_identifier":"",
//                                    "coupons_vouchers":"","custom_data_2":"","custom_data_3":"",
//                                    "date_time":"20210226174433","destination_package_name":"",
//                                    "ecr_unique_trace_number":"","employee_id":"",
//                                    "emv_data":"0000008000E800A0000000041010  MasterCard                      ",
//                                    "entry_mode":"C","expiry_date":"XXXX","external_device_invoice":"",
//                                    "host_label":"BOC","host_type":"B","invoice_number":"000012",
//                                    "merchant_id":"104767011000016","original_trans_type":"000013","response_code":"00",
//                                    "retrieval_reference_number":"210226174433","source_package_name":"","terminal_id":"76002460",
//                                    "transaction_amount":"000000005990","transaction_info":"","transaction_type":"R200"}
                        //
                        String billNo = BillNo;
                        String uuid = UUID.randomUUID().toString();
                        String STATUS = "SALES";
                        String additional_printing_flag = "";
                        String approval_code = "";
                        String batch_number = "";
                        String card_holder_name = "";
                        String card_label = "";
                        String card_number = "";
                        String card_type = "";
                        String command_identifier = "";
                        String coupons_vouchers = "";
                        String custom_data_ = "";
                        String custom_data_3 = "";
                        String date_time = "";
                        String destination_package_name = "";
                        String ecr_unique_trace_number = "";
                        String employee_id = "";
                        String emv_data = "";
                        String entry_mode = "";
                        String expiry_date = "";
                        String external_device_invoice = "";
                        String host_label = "";
                        String host_type = "";
                        String invoice_number = "";
                        String merchant_id = "";
                        String original_trans_type = "";
                        String retrieval_reference_number = "";
                        String source_package_name = "";
                        String terminal_id = "";
                        String transaction_amount = "";
                        String transaction_info = "";
                        String transaction_type = "";
                        try {
                            additional_printing_flag = boc_res_data.getString("additional_printing_flag");
                        } catch (Exception e){
                            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                                    System.currentTimeMillis(), DBFunc.PurifyString("additional_printing_flag-" + e.getMessage()));
                        }
                        approval_code = catchResponse(boc_res_data.getString("approval_code"));
                        batch_number = catchResponse(boc_res_data.getString("batch_number"));
                        card_holder_name = catchResponse(boc_res_data.getString("card_holder_name"));
                        card_label = catchResponse(boc_res_data.getString("card_label"));
                        card_number = catchResponse(boc_res_data.getString("card_number"));
                        card_type = catchResponse(boc_res_data.getString("card_type"));
                        command_identifier = catchResponse(boc_res_data.getString("command_identifier"));
                        coupons_vouchers = catchResponse(boc_res_data.getString("coupons_vouchers"));
                        custom_data_ = catchResponse(boc_res_data.getString("custom_data_2"));
                        custom_data_3 = catchResponse(boc_res_data.getString("custom_data_3"));
                        date_time = catchResponse(boc_res_data.getString("date_time"));
                        destination_package_name = catchResponse(boc_res_data.getString("destination_package_name"));
                        ecr_unique_trace_number = catchResponse(boc_res_data.getString("ecr_unique_trace_number"));
                        employee_id = catchResponse(boc_res_data.getString("employee_id"));
                        emv_data = catchResponse(boc_res_data.getString("emv_data"));
                        entry_mode = catchResponse(boc_res_data.getString("entry_mode"));
                        expiry_date = catchResponse(boc_res_data.getString("expiry_date"));
                        external_device_invoice = catchResponse(boc_res_data.getString("external_device_invoice"));
                        host_label = catchResponse(boc_res_data.getString("host_label"));
                        host_type = catchResponse(boc_res_data.getString("host_type"));
                        invoice_number = catchResponse(boc_res_data.getString("invoice_number"));
                        merchant_id = catchResponse(boc_res_data.getString("merchant_id"));
                        original_trans_type = catchResponse(boc_res_data.getString("original_trans_type"));
                        retrieval_reference_number = catchResponse(boc_res_data.getString("retrieval_reference_number"));
                        source_package_name = catchResponse(boc_res_data.getString("source_package_name"));
                        terminal_id = catchResponse(boc_res_data.getString("terminal_id"));
                        transaction_amount = catchResponse(boc_res_data.getString("transaction_amount"));
                        transaction_info = catchResponse(boc_res_data.getString("transaction_info"));
                        transaction_type = catchResponse(boc_res_data.getString("transaction_type"));
                        long dateTime = System.currentTimeMillis();

                        Log.i("DFD___", "cardLabel___" + card_label + "__" + card_label);
                        try {
                            //cardLabel = emv_data.split("  ")[1]+"~"+host_label;
                            cardLabel = host_label + "~" + card_label;
                        } catch (ArrayIndexOutOfBoundsException e) {
                            cardLabel = host_label;
                        } catch (Exception e){
                            cardLabel = card_label;
                        }
                        Log.i("DFD___", "cardLabel___" + cardLabel);

                        BillBOC billBOCObj = new BillBOC(0, billNo, uuid, STATUS, additional_printing_flag, approval_code,
                                batch_number, card_holder_name, card_label, card_number, card_type, command_identifier,
                                coupons_vouchers, custom_data_, custom_data_3, date_time, destination_package_name,
                                ecr_unique_trace_number, employee_id, emv_data, entry_mode, expiry_date,
                                external_device_invoice, host_label, host_type, invoice_number, merchant_id,
                                original_trans_type, response_code, retrieval_reference_number, source_package_name
                                , terminal_id, transaction_amount, transaction_info, transaction_type, dateTime);
                        Query.saveBillBOC(billBOCObj);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    status = "1";
                    Query.ResponseCodeSuccessFun(CheckOutActivity.this, BillNo,
                            PaymentTypesCheckoutAdapter.paymentID, PaymentTypesCheckoutAdapter.paymentName, cardLabel);

                } else if (response_code.equals("TA")) {
                    final SweetAlertDialog syncDialog =  new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Response Code from EZLINK is Transaction Aborted. Do you want to continue?")
                            .setConfirmText(Constraints.YES)
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismissWithAnimation();

                                    String billNo = BillNo;
                                    String uuid = UUID.randomUUID().toString();
                                    String STATUS = "SALES";
                                    long dateTime = System.currentTimeMillis();
                                    String cardLabel = PaymentTypesCheckoutAdapter.paymentName.toUpperCase();
                                    BillBOC billBOCObj = new BillBOC(0, billNo, uuid, STATUS, "", "",
                                            "", "", cardLabel, "", "", "",
                                            "", "", "", "", "",
                                            "", "", "", "", "",
                                            "", "", "", "", "",
                                            "", "", "", ""
                                            , "", "", "", "", dateTime);
                                    Query.saveBillBOC(billBOCObj);
                                    status = "1";
                                    Query.ResponseCodeSuccessFun(CheckOutActivity.this, BillNo,
                                            PaymentTypesCheckoutAdapter.paymentID, PaymentTypesCheckoutAdapter.paymentName, cardLabel);

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
                    emptyCashValueAll();
                }
            }else {
                String billNo = BillNo;
                String uuid = UUID.randomUUID().toString();
                String STATUS = "SALES";
                long dateTime = System.currentTimeMillis();
                String cardLabel = PaymentTypesCheckoutAdapter.paymentName.toUpperCase();
                BillBOC billBOCObj = new BillBOC(0, billNo, uuid, STATUS, "", "",
                        "", "", cardLabel, "", "", "",
                        "", "", "", "", "",
                        "", "", "", "", "",
                        "", "", "", "", "",
                        "", "", "", ""
                        , "", "", "", "", dateTime);
                Query.saveBillBOC(billBOCObj);

                status = "1";
                Query.ResponseCodeSuccessFun(CheckOutActivity.this, BillNo,
                        PaymentTypesCheckoutAdapter.paymentID, PaymentTypesCheckoutAdapter.paymentName, cardLabel);
            }
        } else {
            if (bankName.toUpperCase().equals(DeclarationConf.HOST_TYPES_OCBC.toUpperCase())) {
                if (data.hasExtra("Response")) {
                    responseData = data.getStringExtra("Response");

                    JSONObject res_data = null;
                    String response_code = "";
                    if (responseData != null) {
                        try {
                            res_data = new JSONObject(responseData);

                            //{"additional_printing_flag":"\u00001\u0000\u0000\u0000\u0000\u0000104","approval_code":"SALE51","batch_number":"000001","card_label":"VISA","card_number":"4628450041586661","card_type":"V","command_identifier":"1","custom_data_2":"Success","date_time":"0427214621","host_label":"DBS","host_type":"D","invoice_number":"000002","merchant_id":"100088812345678","original_trans_type":"R200","response_code":"00","retrieval_reference_number":"515151515151","terminal_id":"10081004","transaction_type":"R200"}
                            response_code = res_data.getString("response_code");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    if (response_code.equals("00")) {

                        status = "1";
                        Query.ResponseCodeSuccessFun(CheckOutActivity.this, BillNo,
                                PaymentTypesCheckoutAdapter.paymentID, PaymentTypesCheckoutAdapter.paymentName, "");

                    } else {
                        emptyCashValueAll();
                    }
                }
            } else if (bankName.toUpperCase().equals(DeclarationConf.HOST_TYPES_DBS.toUpperCase())) {
                if (data.hasExtra("Response")) {
                    responseData = data.getStringExtra("Response");

                    JSONObject res_data = null;
                    String response_code = "";
                    if (responseData != null) {
                        try {

                            res_data = new JSONObject(responseData);
                            response_code = res_data.getString("response_code");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    if (response_code.equals("00")) {

                        status = "1";
                        Query.ResponseCodeSuccessFun(CheckOutActivity.this, BillNo,
                                PaymentTypesCheckoutAdapter.paymentID, PaymentTypesCheckoutAdapter.paymentName, "");

                    } else {
                        emptyCashValueAll();
                    }
                }
            } else if (bankName.toUpperCase().equals(DeclarationConf.HOST_TYPES_BOC.toUpperCase())) {
                if (data.hasExtra("Response")) {
                    responseData = data.getStringExtra("Response");

                    Log.i("responseData___", "responseData___" + responseData);

                    JSONObject res_data = null;
                    String response_code = "";
                    if (responseData != null) {
                        try {
                            res_data = new JSONObject(responseData);
                            response_code = res_data.getString("response_code");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    Log.i("DFD___", "cardLabell___" + response_code + "__" + response_code);
                    String cardLabel = "";
                    if (response_code.equals("00")) {

                        try {
                            JSONObject boc_res_data = new JSONObject(responseData);
//                            {"additional_printing_flag":"","approval_code":"174433","batch_number":"000659",
//                                    "card_holder_name":"TAN SOO KWEI/             ","card_label":"MASTERCARD",
//                                    "card_number":"XXXXXXXXXXXX8595","card_type":"M","command_identifier":"",
//                                    "coupons_vouchers":"","custom_data_2":"","custom_data_3":"",
//                                    "date_time":"20210226174433","destination_package_name":"",
//                                    "ecr_unique_trace_number":"","employee_id":"",
//                                    "emv_data":"0000008000E800A0000000041010  MasterCard                      ",
//                                    "entry_mode":"C","expiry_date":"XXXX","external_device_invoice":"",
//                                    "host_label":"BOC","host_type":"B","invoice_number":"000012",
//                                    "merchant_id":"104767011000016","original_trans_type":"000013","response_code":"00","retrieval_reference_number":"210226174433","source_package_name":"","terminal_id":"76002460","transaction_amount":"000000005990","transaction_info":"","transaction_type":"R200"}
                            //
                            String billNo = BillNo;
                            String uuid = UUID.randomUUID().toString();
                            String STATUS = "SALES";
                            String additional_printing_flag = boc_res_data.getString("additional_printing_flag");
                            String approval_code = boc_res_data.getString("approval_code");
                            String batch_number = boc_res_data.getString("batch_number");
                            String card_holder_name = boc_res_data.getString("card_holder_name");
                            String card_label = boc_res_data.getString("card_label");
                            String card_number = boc_res_data.getString("card_number");
                            String card_type = boc_res_data.getString("card_type");
                            String command_identifier = boc_res_data.getString("command_identifier");
                            String coupons_vouchers = boc_res_data.getString("coupons_vouchers");
                            String custom_data_ = boc_res_data.getString("custom_data_2");
                            String custom_data_3 = boc_res_data.getString("custom_data_3");
                            String date_time = boc_res_data.getString("date_time");
                            String destination_package_name = boc_res_data.getString("destination_package_name");
                            String ecr_unique_trace_number = boc_res_data.getString("ecr_unique_trace_number");
                            String employee_id = boc_res_data.getString("employee_id");
                            String emv_data = boc_res_data.getString("emv_data");
                            String entry_mode = boc_res_data.getString("entry_mode");
                            String expiry_date = boc_res_data.getString("expiry_date");
                            String external_device_invoice = boc_res_data.getString("external_device_invoice");
                            String host_label = boc_res_data.getString("host_label");
                            String host_type = boc_res_data.getString("host_type");
                            String invoice_number = boc_res_data.getString("invoice_number");
                            String merchant_id = boc_res_data.getString("merchant_id");
                            String original_trans_type = boc_res_data.getString("original_trans_type");
                            String retrieval_reference_number = boc_res_data.getString("retrieval_reference_number");
                            String source_package_name = boc_res_data.getString("source_package_name");
                            String terminal_id = boc_res_data.getString("terminal_id");
                            String transaction_amount = boc_res_data.getString("transaction_amount");
                            String transaction_info = boc_res_data.getString("transaction_info");
                            String transaction_type = boc_res_data.getString("transaction_type");
                            long dateTime = System.currentTimeMillis();

                            Log.i("DFD___", "cardLabel___" + card_label + "__" + card_label);
                            try {
                                //cardLabel = emv_data.split("  ")[1]+"~"+host_label;
                                cardLabel = host_label + "~" + card_label;
                            } catch (ArrayIndexOutOfBoundsException e) {
                                cardLabel = host_label;
                            }
                            Log.i("DFD___", "cardLabel___" + cardLabel);

                            BillBOC billBOCObj = new BillBOC(0, billNo, uuid, STATUS, additional_printing_flag, approval_code,
                                    batch_number, card_holder_name, card_label, card_number, card_type, command_identifier,
                                    coupons_vouchers, custom_data_, custom_data_3, date_time, destination_package_name,
                                    ecr_unique_trace_number, employee_id, emv_data, entry_mode, expiry_date,
                                    external_device_invoice, host_label, host_type, invoice_number, merchant_id,
                                    original_trans_type, response_code, retrieval_reference_number, source_package_name
                                    , terminal_id, transaction_amount, transaction_info, transaction_type, dateTime);
                            Query.saveBillBOC(billBOCObj);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        status = "1";
                        Query.ResponseCodeSuccessFun(CheckOutActivity.this, BillNo,
                                PaymentTypesCheckoutAdapter.paymentID, PaymentTypesCheckoutAdapter.paymentName, cardLabel);

                    } else {
                        emptyCashValueAll();
                    }
                }
            } else if (bankName.toUpperCase().equals(DeclarationConf.HOST_TYPES_JERIPAY.toUpperCase())) {

                //                {"uuid":"bfcd4105bde042afb86d7bb1f0fe67c9","status":"SUCCESS","acquirer":"Ocbc","acquirer_id":"OCBC","acquirer_payment_id":"000014","amount":20}

                if (data.hasExtra("transaction")) {
                    responseData = data.getStringExtra("transaction");
                    JSONObject res_data = null;
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
                    if (responseData != null) {
                        try {

                            res_data = new JSONObject(responseData);


//                            {"uuid":"bd768cf864bf4297b1ce0ba00d89d034","status":"SUCCESS","acquirer":"Ocbc",
//                                    "acquirer_id":"OCBC","acquirer_payment_id":"000004","amount":359,
//                                    "details":{"card_label":"OCBC~VISA","card_number":"XXXXXXXXXXXX6661",
//                                    "merchant_id":"168353110196","terminal_id":"83100186","invoice_number":"000004"}}


//                            // uuid = res_data.getString("uuid");
//                            uuid = "b8cc85ac65f94bbe8ac46cab5a3e7ec2";
//                            //status = res_data.getString("status");
//                            status = "SUCCESS";
//                            //acquirer = res_data.getString("acquirer");
//                            acquirer = "GrabPay";
//                            //acquirer_id = res_data.getString("acquirer_id");
//                            acquirer_id = "GRAB_PAY";
//                            //acquirer_payment_id = res_data.getString("acquirer_payment_id");
//                            acquirer_payment_id = "cdcaad384faa48ac9e084dc4f7b2646a";
//                            //amount = res_data.getString("amount");
//                            amount = "0.1";
//                            //response_detail = res_data.getString("details");
//                            response_detail = "";
//
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
                                Log.i("fgfg", "response_detail__" + response_detail);

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
                                if (acquirer.toUpperCase().equals("GrabPay".toUpperCase()) || acquirer.toUpperCase().equals("Grab".toUpperCase())) {
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

                                Query.ResponseCodeSuccessFun(CheckOutActivity.this, BillNo,
                                        PaymentTypesCheckoutAdapter.paymentID,
                                        PaymentTypesCheckoutAdapter.paymentName, card_label);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        emptyCashValueAll();
                    }

//                    if (response_code.toUpperCase().equals("SUCCESS")) {
//
//                        if (response_detail.length() > 1) {
//                            Boolean jsoncheck = isJSONValid(response_detail);
//                            if (jsoncheck){ // card payment
//
//                            }
//                        }else { // wallet
////                            1.	I1V886-I2VZAV-IFVEA8-I9V1AZ-I6V2AE
////                            2.	YZJXP1-85I2VQ-A9YBJ6-KIA8YP-JZP481
////                            3.	AP8KYZ-IDJXVC-P1AS85-YWI2J7-VQPLA9
//
//                        }
//
//                        Query.ResponseCodeSuccessFun(CheckOutActivity.this,BillNo,PaymentTypesCheckoutAdapter.paymentID,PaymentTypesCheckoutAdapter.paymentName);
//
//                    }
                } else {
                    // Do something else
                    emptyCashValueAll();
                }

            } else if (bankName.toUpperCase().equals(DeclarationConf.HOST_TYPES_MECATUS.toUpperCase())) {

                //                {"uuid":"bfcd4105bde042afb86d7bb1f0fe67c9","status":"SUCCESS","acquirer":"Ocbc","acquirer_id":"OCBC","acquirer_payment_id":"000014","amount":20}


                if (data.hasExtra("transaction")) {
                    responseData = data.getStringExtra("transaction");
                    JSONObject res_data = null;
                    String response_code = "";
                    String response_detail = "";
                    if (responseData != null) {
                        try {

                            res_data = new JSONObject(responseData);
                            Log.i("FD___", "res_data____" + res_data);
                            String transaction_id = res_data.getString("transaction_id");
                            String payment = res_data.getString("payment");
                            String vouchers = res_data.getString("vouchers");
                            String mall_loyalty_details = res_data.getString("mall_loyalty_details");
                            String status = "";
                            String message = "";
                            if (mall_loyalty_details.length() > 5) {
                                JSONObject malloyaldetail = new JSONObject(mall_loyalty_details);
                                status = malloyaldetail.getString("status");
                                if (!status.equals("FAIL")) {
                                    try {
                                        String trans_id = malloyaldetail.getString("transaction_id");
                                        String trans_amt = malloyaldetail.getString("transaction_amount");
//                                Integer ID, String billNo, String status, String transaction_id, String transaction_amount, Integer dt
                                        BillMercatusMallLoyaltyDetails malloldet = new BillMercatusMallLoyaltyDetails(0, BillNo, status, trans_id, trans_amt, 0);
                                        Query.SaveBillMercatusMallLoyaltyDetails(malloldet);
                                    } catch (JSONException e) {

                                    }
                                }
                            }
//                            [{"voucher_number":"MME5000000040GRP","voucher_value":50}]

                            if (vouchers.length() > 5) {
                                JSONArray vouchersJSONArray = new JSONArray(vouchers);
                                Log.i("TAGG__", vouchersJSONArray.length() + "__-JSONArr_PRSales.length()");
                                for (int i = 0; i < vouchersJSONArray.length(); i++) {
                                    JSONObject vouchJSONObj = vouchersJSONArray.getJSONObject(i);
                                    String voucher_number = vouchJSONObj.getString("voucher_number");
                                    String voucher_value = vouchJSONObj.getString("voucher_value");
//                               Integer ID, String billNo, String voucher_number, String voucher_value, Integer dt
                                    BillMercatusVouchers vouchObj = new BillMercatusVouchers(0, BillNo, voucher_number, voucher_value, 0);
                                    Query.SaveBillMercatusVouchers(vouchObj);
                                }
                            }
                            if (payment.length() > 5) {
                                JSONObject paymentJSONObj = new JSONObject(payment);
                                String payment_type = paymentJSONObj.getString("payment_type");
                                String payment_id = paymentJSONObj.getString("payment_id");
                                String amount = paymentJSONObj.getString("amount");
                                String paymentdetails = paymentJSONObj.getString("details");

                                if (paymentdetails.length() > 1) {
                                    JSONObject paymentdetailsJSONObj = new JSONObject(paymentdetails);
                                    String card_label = paymentdetailsJSONObj.getString("card_label");
                                    String card_number = paymentdetailsJSONObj.getString("card_number");
                                    String merchant_id = paymentdetailsJSONObj.getString("merchant_id");
                                    String terminal_id = paymentdetailsJSONObj.getString("terminal_id");
                                    String invoice_number = paymentdetailsJSONObj.getString("invoice_number");
//                                    Integer ID, String billNo, String card_label, String card_number, String merchant_id, String terminal_id, String invoice_number, Integer dt
                                    BillMercatusDetails paymentdetailsObj = new BillMercatusDetails(0, BillNo, card_label, card_number, merchant_id, terminal_id, invoice_number, 0);
                                    Query.SaveBillMercatusDetails(paymentdetailsObj);
                                }

                                Integer billmercatusdetailsID = Query.findLatestID("ID", "BillMercatusMallLoyaltyDetails", false);
//                              Integer ID, String billNo, String payment_type, String payment_id, String amount, String billmercatusdetailsID, Integer dt
                                BillMercatusPayment paymentObj = new BillMercatusPayment(0, BillNo, payment_type, payment_id, amount, String.valueOf(billmercatusdetailsID), 0);
                                Query.SaveBillMercatusPayment(paymentObj);
                            }
                            if (transaction_id.length() > 1) {
                                //Integer ID, String billNo, String transaction_id, String billmercatuspaymentID,
                                // String billmercatusvouchersID, String billmercatusmallloyaltydetailsID, String isMember, String dt, Integer dtTime
                                Integer billmercatuspaymentID = Query.findLatestID("ID", "BillMercatusPayment", false);
                                Integer billmercatusvoucherID = Query.findLatestID("ID", "BillMercatusVouchers", false);
                                Integer billmercatusdetailsID = Query.findLatestID("ID", "BillMercatusMallLoyaltyDetails", false);
                                BillMercatus mercatusObj = new BillMercatus(0, BillNo, transaction_id,
                                        String.valueOf(billmercatuspaymentID),
                                        String.valueOf(billmercatusvoucherID),
                                        String.valueOf(billmercatusdetailsID), str_member, Query.GetDateFormart55(), 0);
                                Query.SaveBillMercatus(mercatusObj);
                            }

//                            if (status.toUpperCase().equals("SUCCESS")) {
//
//                                Query.ResponseCodeSuccessFun(CheckOutActivity.this,BillNo,PaymentTypesCheckoutAdapter.paymentID,PaymentTypesCheckoutAdapter.paymentName);
//
//                            }else if (status.toUpperCase().equals("FAIL")) {
//                                JSONObject malloyaldetail = new JSONObject(mall_loyalty_details);
//                                message = malloyaldetail.getString("message");
//                                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
//                            }
                            if (payment.length() > 5) {
                                Query.ResponseCodeSuccessFun(CheckOutActivity.this, BillNo,
                                        PaymentTypesCheckoutAdapter.paymentID, PaymentTypesCheckoutAdapter.paymentName, "");
                            } else {
                                if (str_member.equals("0")) {
                                    Query.ResponseCodeSuccessFun(CheckOutActivity.this, BillNo,
                                            PaymentTypesCheckoutAdapter.paymentID, PaymentTypesCheckoutAdapter.paymentName, "");

                                }
                            }

//                            {"transaction_id":"1e3e9403561b4e9fb9d57d1b808513e1","payment":{},
//                                "vouchers":[{"voucher_number":"MME5000000040GRP","voucher_value":50}],
//                                "mall_loyalty_details":{"status":"SUCCESS","transaction_id":"Test reference","transaction_amount":899}}

//                            {"transaction_id":"256784553a4c4a3e9f0eadbcf92d9a24","payment":{},"vouchers":[],"mall_loyalty_details":{"status":"FAIL","message":"Error!"}}

//                            {"transaction_id":"a17558efc3404aafa5f452538918f05c","payment":{},"vouchers":[],"mall_loyalty_details":{}}
//                            response_code = res_data.getString("status");
//                            response_detail = res_data.getString("details");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
//                    Log.i("Df___","response_code___"+response_code);
//                    if (response_code.toUpperCase().equals("SUCCESS")) {
//
//                        if (response_detail.length() > 1) {
//                            Boolean jsoncheck = isJSONValid(response_detail);
//                            if (jsoncheck){ // card payment
//
//                            }
//                        }else { // wallet
////                            1.	I1V886-I2VZAV-IFVEA8-I9V1AZ-I6V2AE
////                            2.	YZJXP1-85I2VQ-A9YBJ6-KIA8YP-JZP481
////                            3.	AP8KYZ-IDJXVC-P1AS85-YWI2J7-VQPLA9
//
//                        }
//
//                        //Query.ResponseCodeSuccessFun(CheckOutActivity.this,BillNo,PaymentTypesCheckoutAdapter.paymentID,PaymentTypesCheckoutAdapter.paymentName);
//
//                    }
                } else {
                    // Do something else
                }

            } else if (bankName.toUpperCase().equals(DeclarationConf.HOST_TYPES_GLOBAL_PAYMENT.toUpperCase())) {
                ITransAPI transAPI = TransAPIFactory.createTransAPI(CheckOutActivity.this);
                SaleMsg.Response response = (SaleMsg.Response) transAPI.onResult(requestCode, resultCode, data);
                //Log.i("ddd__requestCode", String.valueOf(requestCode));
                //Log.i("ddd__response.getRspCode()", String.valueOf(response.getRspCode()));
                //if (requestCode == Activity.RESULT_OK && response != null) {
                if (requestCode == 100 && response != null) {
                    if (response.getRspCode() == 0) {

                        String respMsg = response.getRspMsg();
                        String merchantName = response.getMerchantName();
                        String merchantId = response.getMerchantId();
                        String appId = response.getAppId();
                        String acuquirerName = response.getAcquirerName();
                        String issuerName = response.getIssuerName();
                        long batchNo = response.getBatchNo();
                        long traceNo = response.getTraceNo();
                        String amount = response.getAmount();
                        String appCode = response.getAppCode();
                        String cardType = response.getCardType();
                        String refNo = response.getRefNo();
                        String cardNo = response.getCardNo();
                        String tipAmount = response.getTipAmount();
                        String app = response.getApp();
                        String aid = response.getAid();
                        String tc = response.getTc();
                        String tvr = response.getTvr();
                        String tsi = response.getTsi();
                        String atc = response.getAtc();
                        String stt = response.getStt();
                        String enterMode = response.getEnterMode();
                        String fxRate = response.getFxRate();
                        String foreignAmt = response.getForeignAmt();
                    } else {
                        Log.d("response", response.getRspMsg());
                    }
                } else {
                    Log.d("response", "transaction failed");
                }
            }

            if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
                String encoded = null;
                String image;
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                photo.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
                encoded = "data:image/jpeg;base64," + encoded;

                //loadBreadModuleList(encoded);

            }

            //	 try {
            //		 File root = new File(Environment.getExternalStorageDirectory(), "Notes");
            //		 if (!root.exists()) {
            //			 root.mkdirs();
            //		 }
            //		 File gpxfile = new File(root, "aaa");
            //		 FileWriter writer = new FileWriter(gpxfile);
            //		 writer.append(requestCode+"\n"+resultCode+"\n"+data.toString()+"\n"+data.getStringExtra("Response"));
            //		 writer.flush();
            //		 writer.close();
            //		 //Toast.makeText(ActivityPosCashier.this, "Saved", Toast.LENGTH_SHORT).show();
            //	 } catch (IOException e) {
            //		 e.printStackTrace();
            //	 }

            //	 String responseData;
            //	 responseData = data.getStringExtra("Response");
            //	 JSONObject res_data = null;
            //	 String response_code = "";
            //	 if (responseData != null) {
            //		 try {
            //			 res_data = new JSONObject(responseData);
            //			 response_code = res_data.getString("response_code");
            //		 } catch (JSONException e) {
            //			 e.printStackTrace();
            //		 }
            //	 }
            //	 //Toast.makeText(CurrentActivity, "response_code:" + response_code , Toast.LENGTH_LONG).show();
            //
            //	 //if(requestCode==6699 &&  (resultCode == RESULT_OK || resultCode == -1) ) {
            //	 if (response_code.equals("00")) {
            //		 Log.i("test____2", String.valueOf(_val_amount));
            //		 InsertPaymentIntoDB(_paymentID, _payname, _val_amount);
            //	 } else {
            //
            //	 }
            //}
        }
    }else{
        emptyCashValueAll();

        String device = Query.GetDeviceData(Constraints.DEVICE);

        if (device.equals("M2-Max") && error.equals("1")) {
            error = "0";
            SweetAlertWarningYesOnly(this, Constraints.APP_NOT_INSTALLED,Constraints.OK);
        } else {
            CheckOutActivityFun();
        }
            //onPause();
    }



    //        if (!(data == null)) {
    //            String responseData;
    //            responseData = data.getStringExtra("Response");
    //            JSONObject res_data = null;
    //            String response_code = "";
    //            if (responseData != null) {
    //                try {
    //                    res_data = new JSONObject(responseData);
    //                    response_code = res_data.getString("response_code");
    //                } catch (JSONException e) {
    //                    e.printStackTrace();
    //                }
    //            }
    //            Log.i("response_code", response_code);
    //            if (response_code.equals("00")) {
    //                InsertPaymentIntoDB(_paymentID, _payname, _val_amount);
    //            }
    //            if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
    //                String encoded = null;
    //                String image;
    //                Bitmap photo = (Bitmap) data.getExtras().get("data");
    //                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    //                photo.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
    //                byte[] byteArray = byteArrayOutputStream.toByteArray();
    //                encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
    //                encoded = "data:image/jpeg;base64," + encoded;
    //
    //                loadBreadModuleList(encoded);
    //
    //            }
    //
    //            //	 try {
    //            //		 File root = new File(Environment.getExternalStorageDirectory(), "Notes");
    //            //		 if (!root.exists()) {
    //            //			 root.mkdirs();
    //            //		 }
    //            //		 File gpxfile = new File(root, "aaa");
    //            //		 FileWriter writer = new FileWriter(gpxfile);
    //            //		 writer.append(requestCode+"\n"+resultCode+"\n"+data.toString()+"\n"+data.getStringExtra("Response"));
    //            //		 writer.flush();
    //            //		 writer.close();
    //            //		 //Toast.makeText(ActivityPosCashier.this, "Saved", Toast.LENGTH_SHORT).show();
    //            //	 } catch (IOException e) {
    //            //		 e.printStackTrace();
    //            //	 }
    //
    //            //	 String responseData;
    //            //	 responseData = data.getStringExtra("Response");
    //            //	 JSONObject res_data = null;
    //            //	 String response_code = "";
    //            //	 if (responseData != null) {
    //            //		 try {
    //            //			 res_data = new JSONObject(responseData);
    //            //			 response_code = res_data.getString("response_code");
    //            //		 } catch (JSONException e) {
    //            //			 e.printStackTrace();
    //            //		 }
    //            //	 }
    //            //	 //Toast.makeText(CurrentActivity, "response_code:" + response_code , Toast.LENGTH_LONG).show();
    //            //
    //            //	 //if(requestCode==6699 &&  (resultCode == RESULT_OK || resultCode == -1) ) {
    //            //	 if (response_code.equals("00")) {
    //            //		 Log.i("test____2", String.valueOf(_val_amount));
    //            //		 InsertPaymentIntoDB(_paymentID, _payname, _val_amount);
    //            //	 } else {
    //            //
    //            //	 }
    //            //}
    //        }

}
    public static String catchResponse(String responserdatacheck) {
        String data = "";
        try {
            data = responserdatacheck;
        } catch (Exception e){
            data = "";
            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                    System.currentTimeMillis(), DBFunc.PurifyString(responserdatacheck +"-" + e.getMessage()));
        }
        return data;
    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//        super.onActivityResult(requestCode, resultCode, data);
//        bankName = Query.GetBankNameFun();
//
//        Log.i("Dfd___", "datares___" + data);
//
//
////        CashValueArr.clear();
////        CashValuePaymentNameArr.clear();
////        CashValue = "0.0";
////
////        CashValuePaymentName = "";
////        CashValue = "";
////        CashLayoutActivity.CashValuePaymentName = "";
////        CashLayoutActivity.CashValue = 0.0;
//        //"Response":
//
//        if (!(data == null)) {
//            String responseData;
//            if (requestCode == 9999) {
//
//                if (data.hasExtra("Response")) {
//                    responseData = data.getStringExtra("Response");
////                    responseData = responseData = "{\"additional_printing_flag\":\"\",\"approval_code\":\"000001\",\n" +
////                        "                            \"batch_number\":\"000002\",\"card_holder_name\":\"\",\n" +
////                        "                            \"card_label\":\"EZLINK\",\"card_number\":\"XXXXXXXXXXXX6011\",\n" +
////                        "                            \"card_type\":\"E\",\"command_identifier\":\"\",\n" +
////                        "                            \"coupons_vouchers\":\"\",\"custom_data_2\":\"\",\n" +
////                        "                            \"custom_data_3\":\"\",\n" +
////                        "                            \"date_time\":\"20210918190101\",\n" +
////                        "                            \"destination_package_name\":\"\",\n" +
////                        "                            \"ecr_unique_trace_number\":\"\",\n" +
////                        "                            \"employee_id\":\"\",\"emv_data\":\"\",\n" +
////                        "                            \"entry_mode\":\"C\",\"expiry_date\":\"\",\n" +
////                        "                            \"external_device_invoice\":\"\",\n" +
////                        "                            \"host_label\":\"EPAY\",\"host_type\":\"Z\",\n" +
////                        "                            \"invoice_number\":\"000008\",\n" +
////                        "                            \"merchant_id\":\"600054000001639\",\n" +
////                        "                            \"original_trans_type\":\"000495\",\"response_code\":\"00\",\n" +
////                        "                            \"retrieval_reference_number\":\"210918190101\",\n" +
////                        "                            \"source_package_name\":\"\",\"terminal_id\":\"60010009\",\n" +
////                        "                            \"transaction_amount\":\"000000000001\",\n" +
////                        "                            \"transaction_info\":\"\",\"transaction_type\":\"R610\"}" ;
////
////                    Log.i("responseData___", "responseDataezlink___" + responseData);
//
//                    JSONObject res_data = null;
//                    String response_code = "";
//                    if (responseData != null) {
//                        try {
//                            res_data = new JSONObject(responseData);
//                            response_code = res_data.getString("response_code");
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    Log.i("DFD___", "cardLabell___" + response_code + "__" + response_code);
//                    String cardLabel = "";
//                    if (response_code.equals("00")) {
//
//                        try {
//                            JSONObject boc_res_data = new JSONObject(responseData);
////                            {"additional_printing_flag":"","approval_code":"174433","batch_number":"000659",
////                                    "card_holder_name":"TAN SOO KWEI/             ","card_label":"MASTERCARD",
////                                    "card_number":"XXXXXXXXXXXX8595","card_type":"M","command_identifier":"",
////                                    "coupons_vouchers":"","custom_data_2":"","custom_data_3":"",
////                                    "date_time":"20210226174433","destination_package_name":"",
////                                    "ecr_unique_trace_number":"","employee_id":"",
////                                    "emv_data":"0000008000E800A0000000041010  MasterCard                      ",
////                                    "entry_mode":"C","expiry_date":"XXXX","external_device_invoice":"",
////                                    "host_label":"BOC","host_type":"B","invoice_number":"000012",
////                                    "merchant_id":"104767011000016","original_trans_type":"000013","response_code":"00","retrieval_reference_number":"210226174433","source_package_name":"","terminal_id":"76002460","transaction_amount":"000000005990","transaction_info":"","transaction_type":"R200"}
//                            //
//                            String billNo = BillNo;
//                            String uuid = UUID.randomUUID().toString();
//                            String STATUS = "SALES";
//                            String additional_printing_flag = boc_res_data.getString("additional_printing_flag");
//                            String approval_code = boc_res_data.getString("approval_code");
//                            String batch_number = boc_res_data.getString("batch_number");
//                            String card_holder_name = boc_res_data.getString("card_holder_name");
//                            String card_label = boc_res_data.getString("card_label");
//                            String card_number = boc_res_data.getString("card_number");
//                            String card_type = boc_res_data.getString("card_type");
//                            String command_identifier = boc_res_data.getString("command_identifier");
//                            String coupons_vouchers = boc_res_data.getString("coupons_vouchers");
//                            String custom_data_ = boc_res_data.getString("custom_data_2");
//                            String custom_data_3 = boc_res_data.getString("custom_data_3");
//                            String date_time = boc_res_data.getString("date_time");
//                            String destination_package_name = boc_res_data.getString("destination_package_name");
//                            String ecr_unique_trace_number = boc_res_data.getString("ecr_unique_trace_number");
//                            String employee_id = boc_res_data.getString("employee_id");
//                            String emv_data = boc_res_data.getString("emv_data");
//                            String entry_mode = boc_res_data.getString("entry_mode");
//                            String expiry_date = boc_res_data.getString("expiry_date");
//                            String external_device_invoice = boc_res_data.getString("external_device_invoice");
//                            String host_label = boc_res_data.getString("host_label");
//                            String host_type = boc_res_data.getString("host_type");
//                            String invoice_number = boc_res_data.getString("invoice_number");
//                            String merchant_id = boc_res_data.getString("merchant_id");
//                            String original_trans_type = boc_res_data.getString("original_trans_type");
//                            String retrieval_reference_number = boc_res_data.getString("retrieval_reference_number");
//                            String source_package_name = boc_res_data.getString("source_package_name");
//                            String terminal_id = boc_res_data.getString("terminal_id");
//                            String transaction_amount = boc_res_data.getString("transaction_amount");
//                            String transaction_info = boc_res_data.getString("transaction_info");
//                            String transaction_type = boc_res_data.getString("transaction_type");
//                            long dateTime = System.currentTimeMillis();
//
//                            Log.i("DFD___", "cardLabel___" + card_label + "__" + card_label);
//                            try {
//                                //cardLabel = emv_data.split("  ")[1]+"~"+host_label;
//                                cardLabel = host_label + "~" + card_label;
//                            } catch (ArrayIndexOutOfBoundsException e) {
//                                cardLabel = host_label;
//                            }
//                            Log.i("DFD___", "cardLabel___" + cardLabel);
//
//                            BillBOC billBOCObj = new BillBOC(0, billNo, uuid, STATUS, additional_printing_flag, approval_code,
//                                    batch_number, card_holder_name, card_label, card_number, card_type, command_identifier,
//                                    coupons_vouchers, custom_data_, custom_data_3, date_time, destination_package_name,
//                                    ecr_unique_trace_number, employee_id, emv_data, entry_mode, expiry_date,
//                                    external_device_invoice, host_label, host_type, invoice_number, merchant_id,
//                                    original_trans_type, response_code, retrieval_reference_number, source_package_name
//                                    , terminal_id, transaction_amount, transaction_info, transaction_type, dateTime);
//                            Query.saveBillBOC(billBOCObj);
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                        status = "1";
//                        Query.ResponseCodeSuccessFun(CheckOutActivity.this, BillNo,
//                                PaymentTypesCheckoutAdapter.paymentID, PaymentTypesCheckoutAdapter.paymentName, cardLabel);
//
//                    } else {
//                        emptyCashValueAll();
//                    }
//                }
//            } else {
//                if (bankName.toUpperCase().equals(DeclarationConf.HOST_TYPES_OCBC.toUpperCase())) {
//                    if (data.hasExtra("Response")) {
//                        responseData = data.getStringExtra("Response");
//
//                        JSONObject res_data = null;
//                        String response_code = "";
//                        if (responseData != null) {
//                            try {
//                                res_data = new JSONObject(responseData);
//
//                                //{"additional_printing_flag":"\u00001\u0000\u0000\u0000\u0000\u0000104","approval_code":"SALE51","batch_number":"000001","card_label":"VISA","card_number":"4628450041586661","card_type":"V","command_identifier":"1","custom_data_2":"Success","date_time":"0427214621","host_label":"DBS","host_type":"D","invoice_number":"000002","merchant_id":"100088812345678","original_trans_type":"R200","response_code":"00","retrieval_reference_number":"515151515151","terminal_id":"10081004","transaction_type":"R200"}
//                                response_code = res_data.getString("response_code");
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//
//                        if (response_code.equals("00")) {
//
//                            status = "1";
//                            Query.ResponseCodeSuccessFun(CheckOutActivity.this, BillNo,
//                                    PaymentTypesCheckoutAdapter.paymentID, PaymentTypesCheckoutAdapter.paymentName, "");
//
//                        } else {
//                            emptyCashValueAll();
//                        }
//                    }
//                } else if (bankName.toUpperCase().equals(DeclarationConf.HOST_TYPES_DBS.toUpperCase())) {
//                    if (data.hasExtra("Response")) {
//                        responseData = data.getStringExtra("Response");
//
//                        JSONObject res_data = null;
//                        String response_code = "";
//                        if (responseData != null) {
//                            try {
//
//                                res_data = new JSONObject(responseData);
//                                response_code = res_data.getString("response_code");
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//
//                        if (response_code.equals("00")) {
//
//                            status = "1";
//                            Query.ResponseCodeSuccessFun(CheckOutActivity.this, BillNo,
//                                    PaymentTypesCheckoutAdapter.paymentID, PaymentTypesCheckoutAdapter.paymentName, "");
//
//                        } else {
//                            emptyCashValueAll();
//                        }
//                    }
//                } else if (bankName.toUpperCase().equals(DeclarationConf.HOST_TYPES_BOC.toUpperCase())) {
//                    if (data.hasExtra("Response")) {
//                        responseData = data.getStringExtra("Response");
//
//                        Log.i("responseData___", "responseData___" + responseData);
//
//                        JSONObject res_data = null;
//                        String response_code = "";
//                        if (responseData != null) {
//                            try {
//                                res_data = new JSONObject(responseData);
//                                response_code = res_data.getString("response_code");
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                        Log.i("DFD___", "cardLabell___" + response_code + "__" + response_code);
//                        String cardLabel = "";
//                        if (response_code.equals("00")) {
//
//                            try {
//                                JSONObject boc_res_data = new JSONObject(responseData);
////                            {"additional_printing_flag":"","approval_code":"174433","batch_number":"000659",
////                                    "card_holder_name":"TAN SOO KWEI/             ","card_label":"MASTERCARD",
////                                    "card_number":"XXXXXXXXXXXX8595","card_type":"M","command_identifier":"",
////                                    "coupons_vouchers":"","custom_data_2":"","custom_data_3":"",
////                                    "date_time":"20210226174433","destination_package_name":"",
////                                    "ecr_unique_trace_number":"","employee_id":"",
////                                    "emv_data":"0000008000E800A0000000041010  MasterCard                      ",
////                                    "entry_mode":"C","expiry_date":"XXXX","external_device_invoice":"",
////                                    "host_label":"BOC","host_type":"B","invoice_number":"000012",
////                                    "merchant_id":"104767011000016","original_trans_type":"000013","response_code":"00","retrieval_reference_number":"210226174433","source_package_name":"","terminal_id":"76002460","transaction_amount":"000000005990","transaction_info":"","transaction_type":"R200"}
//                                //
//                                String billNo = BillNo;
//                                String uuid = UUID.randomUUID().toString();
//                                String STATUS = "SALES";
//                                String additional_printing_flag = boc_res_data.getString("additional_printing_flag");
//                                String approval_code = boc_res_data.getString("approval_code");
//                                String batch_number = boc_res_data.getString("batch_number");
//                                String card_holder_name = boc_res_data.getString("card_holder_name");
//                                String card_label = boc_res_data.getString("card_label");
//                                String card_number = boc_res_data.getString("card_number");
//                                String card_type = boc_res_data.getString("card_type");
//                                String command_identifier = boc_res_data.getString("command_identifier");
//                                String coupons_vouchers = boc_res_data.getString("coupons_vouchers");
//                                String custom_data_ = boc_res_data.getString("custom_data_2");
//                                String custom_data_3 = boc_res_data.getString("custom_data_3");
//                                String date_time = boc_res_data.getString("date_time");
//                                String destination_package_name = boc_res_data.getString("destination_package_name");
//                                String ecr_unique_trace_number = boc_res_data.getString("ecr_unique_trace_number");
//                                String employee_id = boc_res_data.getString("employee_id");
//                                String emv_data = boc_res_data.getString("emv_data");
//                                String entry_mode = boc_res_data.getString("entry_mode");
//                                String expiry_date = boc_res_data.getString("expiry_date");
//                                String external_device_invoice = boc_res_data.getString("external_device_invoice");
//                                String host_label = boc_res_data.getString("host_label");
//                                String host_type = boc_res_data.getString("host_type");
//                                String invoice_number = boc_res_data.getString("invoice_number");
//                                String merchant_id = boc_res_data.getString("merchant_id");
//                                String original_trans_type = boc_res_data.getString("original_trans_type");
//                                String retrieval_reference_number = boc_res_data.getString("retrieval_reference_number");
//                                String source_package_name = boc_res_data.getString("source_package_name");
//                                String terminal_id = boc_res_data.getString("terminal_id");
//                                String transaction_amount = boc_res_data.getString("transaction_amount");
//                                String transaction_info = boc_res_data.getString("transaction_info");
//                                String transaction_type = boc_res_data.getString("transaction_type");
//                                long dateTime = System.currentTimeMillis();
//
//                                Log.i("DFD___", "cardLabel___" + card_label + "__" + card_label);
//                                try {
//                                    //cardLabel = emv_data.split("  ")[1]+"~"+host_label;
//                                    cardLabel = host_label + "~" + card_label;
//                                } catch (ArrayIndexOutOfBoundsException e) {
//                                    cardLabel = host_label;
//                                }
//                                Log.i("DFD___", "cardLabel___" + cardLabel);
//
//                                BillBOC billBOCObj = new BillBOC(0, billNo, uuid, STATUS, additional_printing_flag, approval_code,
//                                        batch_number, card_holder_name, card_label, card_number, card_type, command_identifier,
//                                        coupons_vouchers, custom_data_, custom_data_3, date_time, destination_package_name,
//                                        ecr_unique_trace_number, employee_id, emv_data, entry_mode, expiry_date,
//                                        external_device_invoice, host_label, host_type, invoice_number, merchant_id,
//                                        original_trans_type, response_code, retrieval_reference_number, source_package_name
//                                        , terminal_id, transaction_amount, transaction_info, transaction_type, dateTime);
//                                Query.saveBillBOC(billBOCObj);
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//
//                            status = "1";
//                            Query.ResponseCodeSuccessFun(CheckOutActivity.this, BillNo,
//                                    PaymentTypesCheckoutAdapter.paymentID, PaymentTypesCheckoutAdapter.paymentName, cardLabel);
//
//                        } else {
//                            emptyCashValueAll();
//                        }
//                    }
//                } else if (bankName.toUpperCase().equals(DeclarationConf.HOST_TYPES_JERIPAY.toUpperCase())) {
//
//                    //                {"uuid":"bfcd4105bde042afb86d7bb1f0fe67c9","status":"SUCCESS","acquirer":"Ocbc","acquirer_id":"OCBC","acquirer_payment_id":"000014","amount":20}
//
//                    if (data.hasExtra("transaction")) {
//                        responseData = data.getStringExtra("transaction");
//                        JSONObject res_data = null;
//                        String uuid = "";
//                        String status = "";
//                        String acquirer = "";
//                        String acquirer_id = "";
//                        String acquirer_payment_id = "";
//                        String amount = "";
//                        String response_detail = "";
//                        String isCardPayment = "";
//                        String merchant_id = "";
//                        String terminal_id = "";
//                        String invoice_number = "";
//                        if (responseData != null) {
//                            try {
//
//                                res_data = new JSONObject(responseData);
//
//
////                            {"uuid":"bd768cf864bf4297b1ce0ba00d89d034","status":"SUCCESS","acquirer":"Ocbc",
////                                    "acquirer_id":"OCBC","acquirer_payment_id":"000004","amount":359,
////                                    "details":{"card_label":"OCBC~VISA","card_number":"XXXXXXXXXXXX6661",
////                                    "merchant_id":"168353110196","terminal_id":"83100186","invoice_number":"000004"}}
//
//
////                            // uuid = res_data.getString("uuid");
////                            uuid = "b8cc85ac65f94bbe8ac46cab5a3e7ec2";
////                            //status = res_data.getString("status");
////                            status = "SUCCESS";
////                            //acquirer = res_data.getString("acquirer");
////                            acquirer = "GrabPay";
////                            //acquirer_id = res_data.getString("acquirer_id");
////                            acquirer_id = "GRAB_PAY";
////                            //acquirer_payment_id = res_data.getString("acquirer_payment_id");
////                            acquirer_payment_id = "cdcaad384faa48ac9e084dc4f7b2646a";
////                            //amount = res_data.getString("amount");
////                            amount = "0.1";
////                            //response_detail = res_data.getString("details");
////                            response_detail = "";
////
//                                uuid = res_data.getString("uuid");
//                                status = res_data.getString("status");
//                                acquirer = res_data.getString("acquirer");
//                                acquirer_id = res_data.getString("acquirer_id");
//                                acquirer_payment_id = res_data.getString("acquirer_payment_id");
//                                amount = res_data.getString("amount");
//                                response_detail = res_data.getString("details");
//                                String card_label = "";
//                                String card_number = "";
//                                if (status.toUpperCase().equals("SUCCESS")) {
//                                    Log.i("fgfg", "response_detail__" + response_detail);
//
////                                _{"uuid":"b8cc85ac65f94bbe8ac46cab5a3e7ec2","status":"SUCCESS","acquirer":"GrabPay",
////                                    "acquirer_id":"GRAB_PAY","acquirer_payment_id":"cdcaad384faa48ac9e084dc4f7b2646a","amount":0.1,"details":""}
//
//                                    if (response_detail != null && response_detail.length() > 3) {
//                                        JSONObject response_detailOBj = new JSONObject(response_detail);
//                                        card_label = response_detailOBj.getString("card_label");
//                                        card_number = response_detailOBj.getString("card_number");
//                                        if (response_detail.length() > 10) {
//                                            merchant_id = response_detailOBj.getString("merchant_id");
//                                            terminal_id = response_detailOBj.getString("terminal_id");
//                                            invoice_number = response_detailOBj.getString("invoice_number");
//                                            isCardPayment = "1";
//                                        } else {
//                                            isCardPayment = "0";
//                                        }
//                                    }
//                                    if (acquirer.toUpperCase().equals("GrabPay".toUpperCase()) || acquirer.toUpperCase().equals("Grab".toUpperCase())) {
//                                        //"OCBC~VISA"
//                                        card_label = acquirer_id + "~" + acquirer;
//                                    }
//
////                                Integer ID, String billNo, String card_label, String card_number, String merchant_id, String terminal_id, String invoice_number, Integer dt
//                                    BillJeripayDetails jeripaydetail = new BillJeripayDetails(0, BillNo, card_label, card_number, merchant_id, terminal_id, invoice_number, 0);
//                                    Query.SaveBillJeripayDetails(jeripaydetail);
//
////                                Integer ID, String billNo, String uuid, String status, String acquirer, String acquirer_id,
////                                String acquirer_payment_id, String amount, String billjeripaydetailsID,
////                                String isCardPayment, String dt, Integer dtTime
//                                    Integer billjeripaydetailsID = Query.findLatestID("ID", "BillJeripayDetails", false);
//                                    BillJeripay bill_jeripay = new BillJeripay(0, BillNo, uuid, status, acquirer, acquirer_id, acquirer_payment_id, amount,
//                                            String.valueOf(billjeripaydetailsID), isCardPayment, Query.GetDateFormart55(), 0);
//                                    Query.SaveBillJeripay(bill_jeripay);
//
//                                    Query.ResponseCodeSuccessFun(CheckOutActivity.this, BillNo,
//                                            PaymentTypesCheckoutAdapter.paymentID,
//                                            PaymentTypesCheckoutAdapter.paymentName, card_label);
//                                }
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        } else {
//                            emptyCashValueAll();
//                        }
//
////                    if (response_code.toUpperCase().equals("SUCCESS")) {
////
////                        if (response_detail.length() > 1) {
////                            Boolean jsoncheck = isJSONValid(response_detail);
////                            if (jsoncheck){ // card payment
////
////                            }
////                        }else { // wallet
//////                            1.	I1V886-I2VZAV-IFVEA8-I9V1AZ-I6V2AE
//////                            2.	YZJXP1-85I2VQ-A9YBJ6-KIA8YP-JZP481
//////                            3.	AP8KYZ-IDJXVC-P1AS85-YWI2J7-VQPLA9
////
////                        }
////
////                        Query.ResponseCodeSuccessFun(CheckOutActivity.this,BillNo,PaymentTypesCheckoutAdapter.paymentID,PaymentTypesCheckoutAdapter.paymentName);
////
////                    }
//                    } else {
//                        // Do something else
//                        emptyCashValueAll();
//                    }
//
//                } else if (bankName.toUpperCase().equals(DeclarationConf.HOST_TYPES_MECATUS.toUpperCase())) {
//
//                    //                {"uuid":"bfcd4105bde042afb86d7bb1f0fe67c9","status":"SUCCESS","acquirer":"Ocbc","acquirer_id":"OCBC","acquirer_payment_id":"000014","amount":20}
//
//
//                    if (data.hasExtra("transaction")) {
//                        responseData = data.getStringExtra("transaction");
//                        JSONObject res_data = null;
//                        String response_code = "";
//                        String response_detail = "";
//                        if (responseData != null) {
//                            try {
//
//                                res_data = new JSONObject(responseData);
//                                Log.i("FD___", "res_data____" + res_data);
//                                String transaction_id = res_data.getString("transaction_id");
//                                String payment = res_data.getString("payment");
//                                String vouchers = res_data.getString("vouchers");
//                                String mall_loyalty_details = res_data.getString("mall_loyalty_details");
//                                String status = "";
//                                String message = "";
//                                if (mall_loyalty_details.length() > 5) {
//                                    JSONObject malloyaldetail = new JSONObject(mall_loyalty_details);
//                                    status = malloyaldetail.getString("status");
//                                    if (!status.equals("FAIL")) {
//                                        try {
//                                            String trans_id = malloyaldetail.getString("transaction_id");
//                                            String trans_amt = malloyaldetail.getString("transaction_amount");
////                                Integer ID, String billNo, String status, String transaction_id, String transaction_amount, Integer dt
//                                            BillMercatusMallLoyaltyDetails malloldet = new BillMercatusMallLoyaltyDetails(0, BillNo, status, trans_id, trans_amt, 0);
//                                            Query.SaveBillMercatusMallLoyaltyDetails(malloldet);
//                                        } catch (JSONException e) {
//
//                                        }
//                                    }
//                                }
////                            [{"voucher_number":"MME5000000040GRP","voucher_value":50}]
//
//                                if (vouchers.length() > 5) {
//                                    JSONArray vouchersJSONArray = new JSONArray(vouchers);
//                                    Log.i("TAGG__", vouchersJSONArray.length() + "__-JSONArr_PRSales.length()");
//                                    for (int i = 0; i < vouchersJSONArray.length(); i++) {
//                                        JSONObject vouchJSONObj = vouchersJSONArray.getJSONObject(i);
//                                        String voucher_number = vouchJSONObj.getString("voucher_number");
//                                        String voucher_value = vouchJSONObj.getString("voucher_value");
////                               Integer ID, String billNo, String voucher_number, String voucher_value, Integer dt
//                                        BillMercatusVouchers vouchObj = new BillMercatusVouchers(0, BillNo, voucher_number, voucher_value, 0);
//                                        Query.SaveBillMercatusVouchers(vouchObj);
//                                    }
//                                }
//                                if (payment.length() > 5) {
//                                    JSONObject paymentJSONObj = new JSONObject(payment);
//                                    String payment_type = paymentJSONObj.getString("payment_type");
//                                    String payment_id = paymentJSONObj.getString("payment_id");
//                                    String amount = paymentJSONObj.getString("amount");
//                                    String paymentdetails = paymentJSONObj.getString("details");
//
//                                    if (paymentdetails.length() > 1) {
//                                        JSONObject paymentdetailsJSONObj = new JSONObject(paymentdetails);
//                                        String card_label = paymentdetailsJSONObj.getString("card_label");
//                                        String card_number = paymentdetailsJSONObj.getString("card_number");
//                                        String merchant_id = paymentdetailsJSONObj.getString("merchant_id");
//                                        String terminal_id = paymentdetailsJSONObj.getString("terminal_id");
//                                        String invoice_number = paymentdetailsJSONObj.getString("invoice_number");
////                                    Integer ID, String billNo, String card_label, String card_number, String merchant_id, String terminal_id, String invoice_number, Integer dt
//                                        BillMercatusDetails paymentdetailsObj = new BillMercatusDetails(0, BillNo, card_label, card_number, merchant_id, terminal_id, invoice_number, 0);
//                                        Query.SaveBillMercatusDetails(paymentdetailsObj);
//                                    }
//
//                                    Integer billmercatusdetailsID = Query.findLatestID("ID", "BillMercatusMallLoyaltyDetails", false);
////                              Integer ID, String billNo, String payment_type, String payment_id, String amount, String billmercatusdetailsID, Integer dt
//                                    BillMercatusPayment paymentObj = new BillMercatusPayment(0, BillNo, payment_type, payment_id, amount, String.valueOf(billmercatusdetailsID), 0);
//                                    Query.SaveBillMercatusPayment(paymentObj);
//                                }
//                                if (transaction_id.length() > 1) {
//                                    //Integer ID, String billNo, String transaction_id, String billmercatuspaymentID,
//                                    // String billmercatusvouchersID, String billmercatusmallloyaltydetailsID, String isMember, String dt, Integer dtTime
//                                    Integer billmercatuspaymentID = Query.findLatestID("ID", "BillMercatusPayment", false);
//                                    Integer billmercatusvoucherID = Query.findLatestID("ID", "BillMercatusVouchers", false);
//                                    Integer billmercatusdetailsID = Query.findLatestID("ID", "BillMercatusMallLoyaltyDetails", false);
//                                    BillMercatus mercatusObj = new BillMercatus(0, BillNo, transaction_id,
//                                            String.valueOf(billmercatuspaymentID),
//                                            String.valueOf(billmercatusvoucherID),
//                                            String.valueOf(billmercatusdetailsID), str_member, Query.GetDateFormart55(), 0);
//                                    Query.SaveBillMercatus(mercatusObj);
//                                }
//
////                            if (status.toUpperCase().equals("SUCCESS")) {
////
////                                Query.ResponseCodeSuccessFun(CheckOutActivity.this,BillNo,PaymentTypesCheckoutAdapter.paymentID,PaymentTypesCheckoutAdapter.paymentName);
////
////                            }else if (status.toUpperCase().equals("FAIL")) {
////                                JSONObject malloyaldetail = new JSONObject(mall_loyalty_details);
////                                message = malloyaldetail.getString("message");
////                                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
////                            }
//                                if (payment.length() > 5) {
//                                    Query.ResponseCodeSuccessFun(CheckOutActivity.this, BillNo,
//                                            PaymentTypesCheckoutAdapter.paymentID, PaymentTypesCheckoutAdapter.paymentName, "");
//                                } else {
//                                    if (str_member.equals("0")) {
//                                        Query.ResponseCodeSuccessFun(CheckOutActivity.this, BillNo,
//                                                PaymentTypesCheckoutAdapter.paymentID, PaymentTypesCheckoutAdapter.paymentName, "");
//
//                                    }
//                                }
//
////                            {"transaction_id":"1e3e9403561b4e9fb9d57d1b808513e1","payment":{},
////                                "vouchers":[{"voucher_number":"MME5000000040GRP","voucher_value":50}],
////                                "mall_loyalty_details":{"status":"SUCCESS","transaction_id":"Test reference","transaction_amount":899}}
//
////                            {"transaction_id":"256784553a4c4a3e9f0eadbcf92d9a24","payment":{},"vouchers":[],"mall_loyalty_details":{"status":"FAIL","message":"Error!"}}
//
////                            {"transaction_id":"a17558efc3404aafa5f452538918f05c","payment":{},"vouchers":[],"mall_loyalty_details":{}}
////                            response_code = res_data.getString("status");
////                            response_detail = res_data.getString("details");
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
////                    Log.i("Df___","response_code___"+response_code);
////                    if (response_code.toUpperCase().equals("SUCCESS")) {
////
////                        if (response_detail.length() > 1) {
////                            Boolean jsoncheck = isJSONValid(response_detail);
////                            if (jsoncheck){ // card payment
////
////                            }
////                        }else { // wallet
//////                            1.	I1V886-I2VZAV-IFVEA8-I9V1AZ-I6V2AE
//////                            2.	YZJXP1-85I2VQ-A9YBJ6-KIA8YP-JZP481
//////                            3.	AP8KYZ-IDJXVC-P1AS85-YWI2J7-VQPLA9
////
////                        }
////
////                        //Query.ResponseCodeSuccessFun(CheckOutActivity.this,BillNo,PaymentTypesCheckoutAdapter.paymentID,PaymentTypesCheckoutAdapter.paymentName);
////
////                    }
//                    } else {
//                        // Do something else
//                    }
//
//                } else if (bankName.toUpperCase().equals(DeclarationConf.HOST_TYPES_GLOBAL_PAYMENT.toUpperCase())) {
//                    ITransAPI transAPI = TransAPIFactory.createTransAPI(CheckOutActivity.this);
//                    SaleMsg.Response response = (SaleMsg.Response) transAPI.onResult(requestCode, resultCode, data);
//                    //Log.i("ddd__requestCode", String.valueOf(requestCode));
//                    //Log.i("ddd__response.getRspCode()", String.valueOf(response.getRspCode()));
//                    //if (requestCode == Activity.RESULT_OK && response != null) {
//                    if (requestCode == 100 && response != null) {
//                        if (response.getRspCode() == 0) {
//
//                            String respMsg = response.getRspMsg();
//                            String merchantName = response.getMerchantName();
//                            String merchantId = response.getMerchantId();
//                            String appId = response.getAppId();
//                            String acuquirerName = response.getAcquirerName();
//                            String issuerName = response.getIssuerName();
//                            long batchNo = response.getBatchNo();
//                            long traceNo = response.getTraceNo();
//                            String amount = response.getAmount();
//                            String appCode = response.getAppCode();
//                            String cardType = response.getCardType();
//                            String refNo = response.getRefNo();
//                            String cardNo = response.getCardNo();
//                            String tipAmount = response.getTipAmount();
//                            String app = response.getApp();
//                            String aid = response.getAid();
//                            String tc = response.getTc();
//                            String tvr = response.getTvr();
//                            String tsi = response.getTsi();
//                            String atc = response.getAtc();
//                            String stt = response.getStt();
//                            String enterMode = response.getEnterMode();
//                            String fxRate = response.getFxRate();
//                            String foreignAmt = response.getForeignAmt();
//                        } else {
//                            Log.d("response", response.getRspMsg());
//                        }
//                    } else {
//                        Log.d("response", "transaction failed");
//                    }
//                }
//
//                if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
//                    String encoded = null;
//                    String image;
//                    Bitmap photo = (Bitmap) data.getExtras().get("data");
//                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//                    photo.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
//                    byte[] byteArray = byteArrayOutputStream.toByteArray();
//                    encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
//                    encoded = "data:image/jpeg;base64," + encoded;
//
//                    //loadBreadModuleList(encoded);
//
//                }
//
//                //	 try {
//                //		 File root = new File(Environment.getExternalStorageDirectory(), "Notes");
//                //		 if (!root.exists()) {
//                //			 root.mkdirs();
//                //		 }
//                //		 File gpxfile = new File(root, "aaa");
//                //		 FileWriter writer = new FileWriter(gpxfile);
//                //		 writer.append(requestCode+"\n"+resultCode+"\n"+data.toString()+"\n"+data.getStringExtra("Response"));
//                //		 writer.flush();
//                //		 writer.close();
//                //		 //Toast.makeText(ActivityPosCashier.this, "Saved", Toast.LENGTH_SHORT).show();
//                //	 } catch (IOException e) {
//                //		 e.printStackTrace();
//                //	 }
//
//                //	 String responseData;
//                //	 responseData = data.getStringExtra("Response");
//                //	 JSONObject res_data = null;
//                //	 String response_code = "";
//                //	 if (responseData != null) {
//                //		 try {
//                //			 res_data = new JSONObject(responseData);
//                //			 response_code = res_data.getString("response_code");
//                //		 } catch (JSONException e) {
//                //			 e.printStackTrace();
//                //		 }
//                //	 }
//                //	 //Toast.makeText(CurrentActivity, "response_code:" + response_code , Toast.LENGTH_LONG).show();
//                //
//                //	 //if(requestCode==6699 &&  (resultCode == RESULT_OK || resultCode == -1) ) {
//                //	 if (response_code.equals("00")) {
//                //		 Log.i("test____2", String.valueOf(_val_amount));
//                //		 InsertPaymentIntoDB(_paymentID, _payname, _val_amount);
//                //	 } else {
//                //
//                //	 }
//                //}
//            }
//        }else{
//                emptyCashValueAll();
//                CheckOutActivityFun();
//            }
//
//
//        //        if (!(data == null)) {
//        //            String responseData;
//        //            responseData = data.getStringExtra("Response");
//        //            JSONObject res_data = null;
//        //            String response_code = "";
//        //            if (responseData != null) {
//        //                try {
//        //                    res_data = new JSONObject(responseData);
//        //                    response_code = res_data.getString("response_code");
//        //                } catch (JSONException e) {
//        //                    e.printStackTrace();
//        //                }
//        //            }
//        //            Log.i("response_code", response_code);
//        //            if (response_code.equals("00")) {
//        //                InsertPaymentIntoDB(_paymentID, _payname, _val_amount);
//        //            }
//        //            if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
//        //                String encoded = null;
//        //                String image;
//        //                Bitmap photo = (Bitmap) data.getExtras().get("data");
//        //                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        //                photo.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
//        //                byte[] byteArray = byteArrayOutputStream.toByteArray();
//        //                encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
//        //                encoded = "data:image/jpeg;base64," + encoded;
//        //
//        //                loadBreadModuleList(encoded);
//        //
//        //            }
//        //
//        //            //	 try {
//        //            //		 File root = new File(Environment.getExternalStorageDirectory(), "Notes");
//        //            //		 if (!root.exists()) {
//        //            //			 root.mkdirs();
//        //            //		 }
//        //            //		 File gpxfile = new File(root, "aaa");
//        //            //		 FileWriter writer = new FileWriter(gpxfile);
//        //            //		 writer.append(requestCode+"\n"+resultCode+"\n"+data.toString()+"\n"+data.getStringExtra("Response"));
//        //            //		 writer.flush();
//        //            //		 writer.close();
//        //            //		 //Toast.makeText(ActivityPosCashier.this, "Saved", Toast.LENGTH_SHORT).show();
//        //            //	 } catch (IOException e) {
//        //            //		 e.printStackTrace();
//        //            //	 }
//        //
//        //            //	 String responseData;
//        //            //	 responseData = data.getStringExtra("Response");
//        //            //	 JSONObject res_data = null;
//        //            //	 String response_code = "";
//        //            //	 if (responseData != null) {
//        //            //		 try {
//        //            //			 res_data = new JSONObject(responseData);
//        //            //			 response_code = res_data.getString("response_code");
//        //            //		 } catch (JSONException e) {
//        //            //			 e.printStackTrace();
//        //            //		 }
//        //            //	 }
//        //            //	 //Toast.makeText(CurrentActivity, "response_code:" + response_code , Toast.LENGTH_LONG).show();
//        //            //
//        //            //	 //if(requestCode==6699 &&  (resultCode == RESULT_OK || resultCode == -1) ) {
//        //            //	 if (response_code.equals("00")) {
//        //            //		 Log.i("test____2", String.valueOf(_val_amount));
//        //            //		 InsertPaymentIntoDB(_paymentID, _payname, _val_amount);
//        //            //	 } else {
//        //            //
//        //            //	 }
//        //            //}
//        //        }
//
//    }

    private void emptyCashValueAll() {
        binding.checkoutOrderSummary.textPaymentCash.setText("");
        binding.checkoutOrderSummary.textPaymentCashAmount.setText("");
        CashValuePaymentNameArr.clear();
        CashValuePaymentIDArr.clear();
        CashValuePaymentRemarksArr.clear();
        CashValueArr.clear();
        CashValue =  "0.0";
        CashValuePaymentName =  "";
        CashValuePaymentID =  0;
    }

    public boolean isJSONValid(String test) {
        try {
            new JSONObject(test);
        } catch (JSONException ex) {
            // edited, to include @Arthur's comment
            // e.g. in case JSONArray is valid as well...
            try {
                new JSONArray(test);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }

    private void CheckOutActivityFun() {
        Intent ckActivy = new Intent(getApplicationContext(), CheckOutActivity.class);
        ckActivy.putExtra(Constraints.BillNo, CheckOutActivity.BillNo);
        ckActivy.putExtra(Constraints.Status, "Main");
        ckActivy.putExtra(Constraints.StatusSALES,"");
        startActivity(ckActivy);
        finish();
    }

    public static IDAL getDal() {
        if (dal == null) {
            try {
                long start = System.currentTimeMillis();
                dal = NeptuneLiteUser.getInstance().getDal(appContext);
                Log.i("Test", "get dal cost:" + (System.currentTimeMillis() - start) + " ms");
            } catch (Exception e) {
                e.printStackTrace();
                //Toast.makeText(appContext, "error occurred,DAL is null.", Toast.LENGTH_LONG).show();
                Toast.makeText(CheckOutActivity.appContext, "error occurred,DAL is null.", Toast.LENGTH_LONG).show();
            }
        }
        return dal;
    }

    //show balance split dialog
    void ShowBalanceSplitDlg() {
//        if (BalanceNo == null) {// if balance not null
//            ShowErrorDialog(StrTextConst.GetText(StrTextConst.GetText(StrTextConst.TextType.POS, 106, balance_title));
//            return;
//        }
//
//        if (BillCancel) {
//            ShowErrorDialog(StrTextConst.GetText(StrTextConst.GetText(StrTextConst.TextType.POS, 140));
//            return;
//        }
//
//        if (BillPaid) {
//            ShowErrorDialog(StrTextConst.GetText(StrTextConst.GetText(StrTextConst.TextType.POS, 141));
//            return;
//        }

        final Integer BillID = 0;
        final List<Object[]> lst_main = new ArrayList<Object[]>();
        final List<List<Object[]>> lst_split = new ArrayList<List<Object[]>>();
        Cursor c = DBFunc.Query("SELECT idx, Name, Qty FROM BillPLU WHERE BillNo = " + BillID + " AND Cancel = 0 AND CondiPLUID IS NULL ORDER BY idx", false);

        int count = 0;

        while (c.moveToNext()) {
            lst_main.add(new Object[]{c.getInt(0), c.getString(1), c.getInt(2), null});

            // get list of condiment item
            Cursor condi = DBFunc.Query("SELECT idx, Name, Qty FROM BillPLU WHERE BillNo = " + BillID + " AND Cancel = 0 AND CondiPLUID = " + c.getInt(0) + " ORDER BY idx", false);
            while (condi.moveToNext()) {
                lst_main.add(new Object[]{condi.getInt(0), condi.getString(1), condi.getInt(2), c.getInt(0)});
            }
            condi.close();

            count++;

        }
        c.close();

        if (count < 2) {
            //ShowErrorDialog(StrTextConst.GetText(StrTextConst.GetText(StrTextConst.TextType.POS, 144));
            Query.ErrorDialog(this, StrTextConst.GetText(StrTextConst.TextType.POS, 144));
            lst_main.clear();
            return;
        }

        final Dialog dlg = new Dialog(this);
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Window window = dlg.getWindow();
            WindowManager.LayoutParams wlp = window.getAttributes();
            wlp.gravity = Gravity.RIGHT;
            window.setAttributes(wlp);
        }
        dlg.setContentView(R.layout.dlg_billsplit);
        dlg.setCancelable(false);
        dlg.setCanceledOnTouchOutside(false);

        final TableLayout tbl_main = (TableLayout) dlg.findViewById(R.id.tbl_main_itemlist);
        tbl_main.setTag(-1);
        final TableLayout tbl_split = (TableLayout) dlg.findViewById(R.id.tbl_split_itemlist);
        tbl_split.setTag(-1);

        final TextView lbl_1 = (TextView) dlg.findViewById(R.id.lbl_1);

        final Button btn_add = (Button) dlg.findViewById(R.id.btn_add);
        final Button btn_remove = (Button) dlg.findViewById(R.id.btn_remove);
        final Button btn_bill_prev = (Button) dlg.findViewById(R.id.btn_bill_prev);
        final Button btn_bill_next = (Button) dlg.findViewById(R.id.btn_bill_next);
        final Button btn_bill_add = (Button) dlg.findViewById(R.id.btn_bill_add);
        final Button btn_bill_del = (Button) dlg.findViewById(R.id.btn_bill_del);
        final Button btn_ok = (Button) dlg.findViewById(R.id.btn_ok);
        final Button btn_cancel = (Button) dlg.findViewById(R.id.btn_cancel);

        lbl_1.setText(StrTextConst.GetText(StrTextConst.TextType.POS, 222));
        btn_bill_add.setText(StrTextConst.GetText(StrTextConst.TextType.POS, 223));
        btn_bill_del.setText(StrTextConst.GetText(StrTextConst.TextType.POS, 224));
        btn_ok.setText(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0));

        final TextView txt_billsplit_no = (TextView) dlg.findViewById(R.id.txt_billsplit_no);
        txt_billsplit_no.setTag(0);
        txt_billsplit_no.setText(StrTextConst.GetText(StrTextConst.TextType.POS, 414));
        btn_bill_next.setEnabled(false);
        btn_bill_prev.setEnabled(false);

        lst_split.add(new ArrayList<Object[]>());

        btn_bill_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int splitpos = (Integer) txt_billsplit_no.getTag();
                if (splitpos < lst_split.size() - 1) {
                    splitpos++;
                    txt_billsplit_no.setTag(splitpos);
                    txt_billsplit_no.setText(StrTextConst.GetText(StrTextConst.TextType.POS, 415, (splitpos + 1), lst_split.size()));
                }

                if (splitpos == lst_split.size() - 1) {
                    btn_bill_next.setEnabled(false);
                }

                if (splitpos > 0) {
                    btn_bill_prev.setEnabled(true);
                } else {
                    btn_bill_prev.setEnabled(false);
                }

                tbl_split.setTag(-1);
                tbl_split.removeAllViews();

                // refresh split table
                for (int i = 0; i < lst_split.get(splitpos).size(); i++) {
                    TableRow tr = new TableRow(dlg.getContext());
                    TextView tv = new TextView(dlg.getContext());
                    tr.setTag(i);
                    String txt = ((Integer) lst_split.get(splitpos).get(i)[2]) + "x " + ((String) lst_split.get(splitpos).get(i)[1]);
                    if (lst_split.get(splitpos).get(i)[3] != null) {
                        tv.setText("  ++" + txt);
                    } else {
                        tv.setText(txt);
                    }

                    tv.setPadding(15, 0, 0, 0);
                    //tv.setTextSize(billFontSize);
                    tv.setTextSize(16);
                    tr.addView(tv);
                    tr.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            for (int j = 0; j < tbl_split.getChildCount(); j++) {
                                ((TableRow) tbl_split.getChildAt(j)).setBackgroundColor(Color.TRANSPARENT);
                            }
                            v.setBackgroundColor(Color.argb(0x80, 0x80, 0xFF, 0x50));
                            tbl_split.setTag((Integer) v.getTag());
                        }
                    });

                    tbl_split.addView(tr);
                }

                if (tbl_split.getChildCount() == 0) {
                    TableRow tr = new TableRow(dlg.getContext());
                    TextView tv = new TextView(dlg.getContext());
                    //tv.setTextSize(billFontSize);
                    tv.setTextSize(16);
                    tv.setText(" ");
                    tr.addView(tv);
                    tbl_split.addView(tr);
                }

            }
        });

        btn_bill_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int splitpos = (Integer) txt_billsplit_no.getTag();
                if (splitpos > 0) {
                    splitpos--;
                    txt_billsplit_no.setTag(splitpos);
                    txt_billsplit_no.setText(StrTextConst.GetText(StrTextConst.TextType.POS, 415, (splitpos + 1), lst_split.size()));
                }

                if (splitpos == 0) {
                    btn_bill_prev.setEnabled(false);
                }

                if (splitpos < lst_split.size() - 1) {
                    btn_bill_next.setEnabled(true);
                } else {
                    btn_bill_next.setEnabled(false);
                }

                tbl_split.setTag(-1);
                tbl_split.removeAllViews();

                // refresh split table
                for (int i = 0; i < lst_split.get(splitpos).size(); i++) {
                    TableRow tr = new TableRow(dlg.getContext());
                    TextView tv = new TextView(dlg.getContext());
                    tr.setTag(i);
                    String txt = ((Integer) lst_split.get(splitpos).get(i)[2]) + "x " + ((String) lst_split.get(splitpos).get(i)[1]);
                    if (lst_split.get(splitpos).get(i)[3] != null) {
                        tv.setText("  ++" + txt);
                    } else {
                        tv.setText(txt);
                    }

                    // tv.setText(((Integer)lst_split.get(splitpos).get(i)[2]) +
                    // "x " + ((String)lst_split.get(splitpos).get(i)[1]));
                    tv.setPadding(15, 0, 0, 0);
                    tv.setTextSize(MainActivity.billFontSize);
                    tr.addView(tv);
                    tr.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            for (int j = 0; j < tbl_split.getChildCount(); j++) {
                                ((TableRow) tbl_split.getChildAt(j)).setBackgroundColor(Color.TRANSPARENT);
                            }
                            v.setBackgroundColor(Color.argb(0x80, 0x80, 0xFF, 0x50));
                            tbl_split.setTag((Integer) v.getTag());
                        }
                    });

                    tbl_split.addView(tr);
                }

                if (tbl_split.getChildCount() == 0) {
                    TableRow tr = new TableRow(dlg.getContext());
                    TextView tv = new TextView(dlg.getContext());
                    tv.setTextSize(MainActivity.billFontSize);
                    tv.setText(" ");
                    tr.addView(tv);
                    tbl_split.addView(tr);
                }
            }
        });

        btn_bill_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogBox dlg1 = new DialogBox(getApplicationContext());
                dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.POS, 0));
                dlg1.setDialogIconType(DialogBox.IconType.QUESTION);
                dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.POS, 416));

                dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 6), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dlg1.dismiss();
                        lst_split.add(new ArrayList<Object[]>());
                        txt_billsplit_no.setTag(lst_split.size() - 1);
                        txt_billsplit_no.setText("AAA");
                        //txt_billsplit_no.setText(StrTextConst.GetText(StrTextConst.GetText(StrTextConst.TextType.POS, 415, (lst_split.size()), lst_split.size()));

                        btn_bill_prev.setEnabled(true);
                        tbl_split.setTag(-1);
                        tbl_split.removeAllViews();

                        if (tbl_split.getChildCount() == 0) {
                            TableRow tr = new TableRow(dlg.getContext());
                            TextView tv = new TextView(dlg.getContext());
                            //tv.setTextSize(billFontSize);
                            tv.setTextSize(16);
                            tv.setText(" ");
                            tr.addView(tv);
                            tbl_split.addView(tr);
                        }

                    }
                });
                dlg1.setButton2OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 7), null);
                dlg1.show();

            }
        });

        btn_bill_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lst_split.size() <= 1) {
                    //ShowErrorDialog(StrTextConst.GetText(StrTextConst.GetText(StrTextConst.TextType.POS, 146));
                    return;
                }

                final DialogBox dlg1 = new DialogBox(getApplicationContext());
                dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.POS, 0));
                dlg1.setDialogIconType(DialogBox.IconType.QUESTION);
               // dlg1.setMessage(StrTextConst.GetText(StrTextConst.GetText(StrTextConst.TextType.POS, 417));
                dlg1.setMessage("DFdsf");

                dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 6), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dlg1.dismiss();

                        int splitpos = (Integer) txt_billsplit_no.getTag();
                        for (Object[] obj : lst_split.get(splitpos)) {
                            lst_main.add(obj);
                        }

                        lst_split.remove(splitpos);
                        if (splitpos >= lst_split.size()) {
                            splitpos = lst_split.size() - 1;
                        }

                        txt_billsplit_no.setTag(splitpos);
                        txt_billsplit_no.setText(StrTextConst.GetText(StrTextConst.TextType.POS, 415, (splitpos + 1), lst_split.size()));
                        if (splitpos > 0) {
                            btn_bill_prev.setEnabled(true);
                        } else {
                            btn_bill_prev.setEnabled(false);
                        }
                        if (splitpos < lst_split.size() - 1) {
                            btn_bill_next.setEnabled(true);
                        } else {
                            btn_bill_next.setEnabled(false);
                        }

                        tbl_main.setTag(-1);
                        tbl_main.removeAllViews();

                        for (int i = 0; i < lst_main.size(); i++) {
                            TableRow tr = new TableRow(dlg.getContext());
                            TextView tv = new TextView(dlg.getContext());
                            tr.setTag(i);

                            String txt = (lst_main.get(i)[2]) + "x " + ((String) lst_main.get(i)[1]);
                            if (lst_main.get(i)[3] != null) {
                                tv.setText("  ++" + txt);
                            } else {
                                tv.setText(txt);
                            }
                            // tv.setText(((Integer)lst_main.get(i)[2])
                            // + "x " +
                            // ((String)lst_main.get(i)[1]));
                            tv.setPadding(15, 0, 0, 0);
                            tv.setTextSize(MainActivity.billFontSize);
                            tr.addView(tv);
                            tr.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    for (int j = 0; j < tbl_main.getChildCount(); j++) {
                                        ((TableRow) tbl_main.getChildAt(j)).setBackgroundColor(Color.TRANSPARENT);
                                    }
                                    v.setBackgroundColor(Color.argb(0x80, 0x80, 0xFF, 0x50));
                                    tbl_main.setTag((Integer) v.getTag());
                                }
                            });

                            tbl_main.addView(tr);

                        }

                        tbl_split.setTag(-1);
                        tbl_split.removeAllViews();

                        // refresh split table
                        for (int i = 0; i < lst_split.get(splitpos).size(); i++) {
                            TableRow tr = new TableRow(dlg.getContext());
                            TextView tv = new TextView(dlg.getContext());
                            tr.setTag(i);
                            String txt = ((Integer) lst_split.get(splitpos).get(i)[2]) + "x " + ((String) lst_split.get(splitpos).get(i)[1]);
                            if (lst_split.get(splitpos).get(i)[3] != null) {
                                tv.setText("  ++" + txt);
                            } else {
                                tv.setText(txt);
                            }
                            // tv.setText(((Integer)lst_split.get(splitpos).get(i)[2])
                            // + "x " +
                            // ((String)lst_split.get(splitpos).get(i)[1]));
                            tv.setPadding(15, 0, 0, 0);
                            tv.setTextSize(MainActivity.billFontSize);
                            tr.addView(tv);
                            tr.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    for (int j = 0; j < tbl_split.getChildCount(); j++) {
                                        ((TableRow) tbl_split.getChildAt(j)).setBackgroundColor(Color.TRANSPARENT);
                                    }
                                    v.setBackgroundColor(Color.argb(0x80, 0x80, 0xFF, 0x50));
                                    tbl_split.setTag((Integer) v.getTag());
                                }
                            });

                            tbl_split.addView(tr);
                        }

                        if (tbl_main.getChildCount() == 0) {
                            TableRow tr = new TableRow(dlg.getContext());
                            TextView tv = new TextView(dlg.getContext());
                            //tv.setTextSize(billFontSize);
                            tv.setTextSize(18);
                            tv.setText(" ");
                            tr.addView(tv);
                            tbl_main.addView(tr);
                        }

                        if (tbl_split.getChildCount() == 0) {
                            TableRow tr = new TableRow(dlg.getContext());
                            TextView tv = new TextView(dlg.getContext());
                            //tv.setTextSize(billFontSize);
                            tv.setTextSize(18);
                            tv.setText(" ");
                            tr.addView(tv);
                            tbl_split.addView(tr);
                        }

                    }
                });
                dlg1.setButton2OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 7), null);
                dlg1.show();

            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // add into bill

                if (lst_main.size() <= 1) {
                    //ShowErrorDialog(StrTextConst.GetText(StrTextConst.GetText(StrTextConst.TextType.POS, 147));
                    Query.ErrorDialog(getApplication(),StrTextConst.GetText(StrTextConst.TextType.POS, 147));
                    return;
                }

                int selected_item_main = (Integer) tbl_main.getTag();

                if (selected_item_main == -1) {
                    //ShowErrorDialog(StrTextConst.GetText(StrTextConst.GetText(StrTextConst.TextType.POS, 117));
                    Query.ErrorDialog(getApplication(),StrTextConst.GetText(StrTextConst.TextType.POS, 117));
                    return;
                }

                int selectedsplitbill = (Integer) txt_billsplit_no.getTag();

                int pluidx = 0;

                if (lst_main.get(selected_item_main)[3] == null) {
                    pluidx = (Integer) lst_main.get(selected_item_main)[0];
                } else {// if this is condiment item then we will change the
                    // selected index to main plu item index
                    pluidx = (Integer) lst_main.get(selected_item_main)[3];
                    boolean exist = false;
                    for (int i = 0; i < lst_main.size(); i++) {
                        if ((Integer) lst_main.get(i)[0] == pluidx) {
                            selected_item_main = i;
                            exist = true;
                            break;
                        }
                    }

                    if (!exist) {
                        DialogBox dlg1 = new DialogBox(getApplicationContext());
                        dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 18));
                        dlg1.setDialogIconType(DialogBox.IconType.ERROR);
                        dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.POS, 149));
                        dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                        dlg1.show();
                        return;
                    }
                }

                lst_split.get(selectedsplitbill).add(lst_main.get(selected_item_main));
                lst_main.remove(selected_item_main);

                // check for any condiment item that belongs to main pluidx
                while (true) {
                    boolean hasCondi = false;
                    for (int i = 0; i < lst_main.size(); i++) {
                        if (lst_main.get(i)[3] != null) {
                            if ((Integer) lst_main.get(i)[3] == pluidx) {
                                lst_split.get(selectedsplitbill).add(lst_main.get(i));
                                lst_main.remove(i);
                                hasCondi = true;
                                break;
                            }
                        }
                    }
                    if (!hasCondi) {
                        break;
                    }
                }

                tbl_main.setTag(-1);
                tbl_main.removeAllViews();

                for (int i = 0; i < lst_main.size(); i++) {
                    TableRow tr = new TableRow(dlg.getContext());
                    TextView tv = new TextView(dlg.getContext());
                    tr.setTag(i);
                    // tv.setText(((Integer)lst_main.get(i)[2]) + "x " +
                    // ((String)lst_main.get(i)[1]));
                    String txt = (lst_main.get(i)[2]) + "x " + ((String) lst_main.get(i)[1]);
                    if (lst_main.get(i)[3] != null) {
                        tv.setText("  ++" + txt);
                    } else {
                        tv.setText(txt);
                    }
                    tv.setPadding(15, 0, 0, 0);
                    //tv.setTextSize(billFontSize);
                    tv.setTextSize(16);
                    tr.addView(tv);
                    tr.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            for (int j = 0; j < tbl_main.getChildCount(); j++) {
                                ((TableRow) tbl_main.getChildAt(j)).setBackgroundColor(Color.TRANSPARENT);
                            }
                            v.setBackgroundColor(Color.argb(0x80, 0x80, 0xFF, 0x50));
                            tbl_main.setTag((Integer) v.getTag());
                        }
                    });

                    tbl_main.addView(tr);

                }

                tbl_split.setTag(-1);
                tbl_split.removeAllViews();

                for (int i = 0; i < lst_split.get(selectedsplitbill).size(); i++) {
                    TableRow tr = new TableRow(dlg.getContext());
                    TextView tv = new TextView(dlg.getContext());
                    tr.setTag(i);
                    // tv.setText(((Integer)lst_split.get(selectedsplitbill).get(i)[2])
                    // + "x " +
                    // ((String)lst_split.get(selectedsplitbill).get(i)[1]));
                    String txt = ((Integer) lst_split.get(selectedsplitbill).get(i)[2]) + "x " + ((String) lst_split.get(selectedsplitbill).get(i)[1]);
                    if (lst_split.get(selectedsplitbill).get(i)[3] != null) {
                        tv.setText("  ++" + txt);
                    } else {
                        tv.setText(txt);
                    }

                    tv.setPadding(15, 0, 0, 0);
                    tv.setTextSize(MainActivity.billFontSize);
                    tr.addView(tv);
                    tr.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            for (int j = 0; j < tbl_split.getChildCount(); j++) {
                                ((TableRow) tbl_split.getChildAt(j)).setBackgroundColor(Color.TRANSPARENT);
                            }
                            v.setBackgroundColor(Color.argb(0x80, 0x80, 0xFF, 0x50));
                            tbl_split.setTag((Integer) v.getTag());
                        }
                    });

                    tbl_split.addView(tr);
                }

                if (tbl_main.getChildCount() == 0) {
                    TableRow tr = new TableRow(dlg.getContext());
                    TextView tv = new TextView(dlg.getContext());
                    tv.setTextSize(MainActivity.billFontSize);
                    tv.setText(" ");
                    tr.addView(tv);
                    tbl_main.addView(tr);
                }

                if (tbl_split.getChildCount() == 0) {
                    TableRow tr = new TableRow(dlg.getContext());
                    TextView tv = new TextView(dlg.getContext());
                    tv.setTextSize(MainActivity.billFontSize);
                    tv.setText(" ");
                    tr.addView(tv);
                    tbl_split.addView(tr);
                }

            }
        });

        btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selected_item_split = (Integer) tbl_split.getTag();
                if (selected_item_split == -1) {
                    //ShowErrorDialog(StrTextConst.GetText(StrTextConst.GetText(StrTextConst.TextType.POS, 117));
                    return;
                }
                int selectedsplitbill = (Integer) txt_billsplit_no.getTag();

                int pluidx = 0;

                if (lst_split.get(selectedsplitbill).get(selected_item_split)[3] == null) {
                    pluidx = (Integer) lst_split.get(selectedsplitbill).get(selected_item_split)[0];
                } else {// if this is condiment item then we will change the
                    // selected index to main plu item index
                    pluidx = (Integer) lst_split.get(selectedsplitbill).get(selected_item_split)[3];
                    boolean exist = false;

                    for (int i = 0; i < lst_split.get(selectedsplitbill).size(); i++) {
                        if ((Integer) lst_split.get(selectedsplitbill).get(i)[0] == pluidx) {
                            selected_item_split = i;
                            exist = true;
                            break;
                        }
                    }

                    if (!exist) {
                        DialogBox dlg1 = new DialogBox(getApplicationContext());
                        dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 18));
                        dlg1.setDialogIconType(DialogBox.IconType.ERROR);
                        dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.POS, 149));
                        dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                        dlg1.show();
                        return;
                    }
                }

                lst_main.add(lst_split.get(selectedsplitbill).get(selected_item_split));
                lst_split.get(selectedsplitbill).remove(selected_item_split);

                while (true) {
                    boolean hasCondi = false;
                    for (int i = 0; i < lst_split.get(selectedsplitbill).size(); i++) {
                        if (lst_split.get(selectedsplitbill).get(i)[3] != null) {
                            if ((Integer) lst_split.get(selectedsplitbill).get(i)[3] == pluidx) {
                                lst_main.add(lst_split.get(selectedsplitbill).get(i));
                                lst_split.get(selectedsplitbill).remove(i);
                                // lst_split.get(selectedsplitbill).add(lst_main.get(i));
                                // lst_main.remove(i);
                                hasCondi = true;
                                break;
                            }
                        }
                    }
                    if (!hasCondi) {
                        break;
                    }
                }

                tbl_main.setTag(-1);
                tbl_main.removeAllViews();

                for (int i = 0; i < lst_main.size(); i++) {
                    TableRow tr = new TableRow(dlg.getContext());
                    TextView tv = new TextView(dlg.getContext());
                    tr.setTag(i);
                    // tv.setText(((Integer)lst_main.get(i)[2]) + "x " +
                    // ((String)lst_main.get(i)[1]));
                    String txt = (lst_main.get(i)[2]) + "x " + ((String) lst_main.get(i)[1]);
                    if (lst_main.get(i)[3] != null) {
                        tv.setText("  ++" + txt);
                    } else {
                        tv.setText(txt);
                    }
                    tv.setPadding(15, 0, 0, 0);
                    tv.setTextSize(MainActivity.billFontSize);
                    tr.addView(tv);
                    tr.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            for (int j = 0; j < tbl_main.getChildCount(); j++) {
                                ((TableRow) tbl_main.getChildAt(j)).setBackgroundColor(Color.TRANSPARENT);
                            }
                            v.setBackgroundColor(Color.argb(0x80, 0x80, 0xFF, 0x50));
                            tbl_main.setTag((Integer) v.getTag());
                        }
                    });

                    tbl_main.addView(tr);

                }

                tbl_split.setTag(-1);
                tbl_split.removeAllViews();

                for (int i = 0; i < lst_split.get(selectedsplitbill).size(); i++) {
                    TableRow tr = new TableRow(dlg.getContext());
                    TextView tv = new TextView(dlg.getContext());
                    tr.setTag(i);
                    // tv.setText(((Integer)lst_split.get(selectedsplitbill).get(i)[2])
                    // + "x " +
                    // ((String)lst_split.get(selectedsplitbill).get(i)[1]));
                    String txt = ((Integer) lst_split.get(selectedsplitbill).get(i)[2]) + "x " + ((String) lst_split.get(selectedsplitbill).get(i)[1]);
                    if (lst_split.get(selectedsplitbill).get(i)[3] != null) {
                        tv.setText("  ++" + txt);
                    } else {
                        tv.setText(txt);
                    }
                    tv.setPadding(15, 0, 0, 0);
                    // tv.setTextSize(billFontSize);
                    tv.setTextSize(16);
                    tr.addView(tv);
                    tr.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            for (int j = 0; j < tbl_split.getChildCount(); j++) {
                                ((TableRow) tbl_split.getChildAt(j)).setBackgroundColor(Color.TRANSPARENT);
                            }
                            v.setBackgroundColor(Color.argb(0x80, 0x80, 0xFF, 0x50));
                            tbl_split.setTag((Integer) v.getTag());
                        }
                    });

                    tbl_split.addView(tr);
                }

                if (tbl_main.getChildCount() == 0) {
                    TableRow tr = new TableRow(dlg.getContext());
                    TextView tv = new TextView(dlg.getContext());
                    tv.setTextSize(MainActivity.billFontSize);
                    tv.setText(" ");
                    tr.addView(tv);
                    tbl_main.addView(tr);
                }

                if (tbl_split.getChildCount() == 0) {
                    TableRow tr = new TableRow(dlg.getContext());
                    TextView tv = new TextView(dlg.getContext());
                    tv.setTextSize(MainActivity.billFontSize);
                    tv.setText(" ");
                    tr.addView(tv);
                    tbl_split.addView(tr);
                }
            }
        });

        btn_ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Toast.makeText(ActivityPosCashier.this, "mcsbtn_test8", Toast.LENGTH_LONG).show();
                boolean splitok = false;
                for (List<Object[]> obj : lst_split) {
                    if (obj.size() > 0) {
                        splitok = true;
                        break;
                    }
                }

                if (!splitok) {
                    //ShowErrorDialog(StrTextConst.GetText(StrTextConst.GetText(StrTextConst.TextType.POS, 150));
                    return;
                }

                final DialogBox dlg1 = new DialogBox(v.getContext());
                dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.POS, 0));
                dlg1.setDialogIconType(DialogBox.IconType.QUESTION);
                dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.POS, 418));
                dlg1.setButton2OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 7), null);
                dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 6), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dlg1.dismiss();

                        // TODO: cashier name
                        // String cashiername = Allocator.cashierName;
                        long time = System.currentTimeMillis();

                        for (List<Object[]> obj : lst_split) {
                            if (obj.size() > 0) {
//                                DBFunc.ExecQuery("INSERT INTO Bill (OpenDateTime, Cashier, CashierID, BalNo) VALUES (" + time + ",'" + DBFunc.PurifyString(Allocator.cashierName) + "', " + Allocator.cashierID + ", " +
//                                        "'" + DBFunc.PurifyString(BalanceNo) + "')", false);
//                                DBFunc.ExecQuery("INSERT INTO Bill (OpenDateTime, Cashier, CashierID, BalNo) VALUES " +
//                                        "(" + time + ",'" + DBFunc.PurifyString(Allocator.cashierName) + "', " + Allocator.cashierID + ", " +
//                                        "'" + DBFunc.PurifyString("AAA") + "')", false);
                                Query.SaveBill("","","","","","OFF");
                                Cursor c = DBFunc.Query("SELECT seq FROM sqlite_sequence WHERE name = 'Bill'", false);
                                if (c.moveToNext()) {
                                    int billid = c.getInt(0);
                                    for (Object[] plu : obj) {
                                        DBFunc.ExecQuery("UPDATE BillPLU SET BillNo = " + billid + " WHERE idx = " + (Integer) plu[0], false);
                                    }

                                    DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth, System.currentTimeMillis(), DBFunc.PurifyString("POS -> Balance Split -> Bill: " + BillID + "/" + billid));

                                } else {
                                    DialogBox dlg1 = new DialogBox(getApplicationContext());
                                    dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 18));
                                    dlg1.setDialogIconType(DialogBox.IconType.ERROR);
                                    dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.POS, 312));
                                    dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                                    dlg1.show();
                                    // Toast.makeText(CurrentActivity,
                                    // "DB Error! Failed to split bill!",
                                    // Toast.LENGTH_SHORT).show();
                                    break;
                                }
                                c.close();
                            }
                        }
                        lst_split.clear();
                        lst_main.clear();
                        dlg.dismiss();
                        // if(!paymentOnlyKP){
                        //CloseBalance(false);
                        // }
                    }
                });
                dlg1.show();
            }

        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final DialogBox dlg1 = new DialogBox(v.getContext());
                dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.POS, 0));
                dlg1.setDialogIconType(DialogBox.IconType.QUESTION);
                dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.POS, 419));
                dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 6), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dlg1.dismiss();
                        lst_main.clear();
                        lst_split.clear();
                        dlg.dismiss();
                    }
                });
                dlg1.setButton2OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 7), null);
                dlg1.show();

            }

        });

        for (int i = 0; i < lst_main.size(); i++) {
            TableRow tr = new TableRow(dlg.getContext());
            TextView tv = new TextView(dlg.getContext());
            tr.setTag(i);
            // tv.setText(((Integer)lst_main.get(i)[2]) + "x " +
            // ((String)lst_main.get(i)[1]));
            String txt = (lst_main.get(i)[2]) + "x " + ((String) lst_main.get(i)[1]);
            if (lst_main.get(i)[3] != null) {
                tv.setText("  ++" + txt);
            } else {
                tv.setText(txt);
            }
            tv.setPadding(15, 0, 0, 0);
            tv.setTextSize(MainActivity.billFontSize);
            tr.addView(tv);
            tr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int j = 0; j < tbl_main.getChildCount(); j++) {
                        ((TableRow) tbl_main.getChildAt(j)).setBackgroundColor(Color.TRANSPARENT);
                    }
                    v.setBackgroundColor(Color.argb(0x80, 0x80, 0xFF, 0x50));
                    tbl_main.setTag((Integer) v.getTag());
                }
            });

            tbl_main.addView(tr);
        }

        if (tbl_main.getChildCount() == 0) {
            TableRow tr = new TableRow(dlg.getContext());
            TextView tv = new TextView(dlg.getContext());
            tv.setTextSize(MainActivity.billFontSize);
            tv.setText(" ");
            tr.addView(tv);
            tbl_main.addView(tr);
        }

        if (tbl_split.getChildCount() == 0) {
            TableRow tr = new TableRow(dlg.getContext());
            TextView tv = new TextView(dlg.getContext());
            tv.setTextSize(MainActivity.billFontSize);
            tv.setText(" ");
            tr.addView(tv);
            tbl_split.addView(tr);
        }

        dlg.show();
        dlg.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }


    //show balance merge dialog for user to choose
    @SuppressWarnings("deprecation")
    void ShowBalanceMergeList() {
        final Integer BillID = 0;

//        if (BalanceNo == null) {// if balance not null
//            ShowErrorDialog(StrTextConst.GetText(StrTextConst.GetText(StrTextConst.TextType.POS, 106, balance_title));
//            return;
//        }
//
//        if (BillCancel) {
//            ShowErrorDialog(StrTextConst.GetText(StrTextConst.GetText(StrTextConst.TextType.POS, 140));
//            return;
//        }
//
//        if (BillPaid) {
//            ShowErrorDialog(StrTextConst.GetText(StrTextConst.GetText(StrTextConst.TextType.POS, 141));
//            return;
//        }
//
         final Dialog dlg = new Dialog(getApplicationContext());
        if (getApplicationContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Window window = dlg.getWindow();

            WindowManager.LayoutParams wlp = window.getAttributes();
            wlp.gravity = Gravity.RIGHT;
            window.setAttributes(wlp);
        }

        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dlg.setContentView(R.layout.dlg_receipt_viewer);

        dlg.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        final LinearLayout laylist = (LinearLayout) dlg.findViewById(R.id.lay_rcptview_list);

        Button btn_close = (Button) dlg.findViewById(R.id.btn_rcpview_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlg.dismiss();
            }
        });

        Button btn_current = (Button) dlg.findViewById(R.id.btn_rcpview_current);

        btn_current.setVisibility(View.GONE);

        Button btn_searchdate = (Button) dlg.findViewById(R.id.btn_rcpview_datetime);
        btn_searchdate.setVisibility(View.GONE);

        TextView tv1 = (TextView) dlg.findViewById(R.id.txt_hint);
        tv1.setVisibility(View.VISIBLE);
        tv1.setText(StrTextConst.GetText(StrTextConst.TextType.POS, 151, balance_title));

        Cursor c = DBFunc.Query("SELECT BillNo, OpenDateTime, CloseDateTime, Cashier, Round, Cancel, BalNo FROM Bill Where BalNo IS NOT NULL AND CloseDateTime IS NULL AND Cancel = 0 AND BillNo != " + BillID, false);
        if (c == null) {
//            DialogBox dlg1 = new DialogBox(CurrentActivity);
//            dlg1.setTitle(StrTextConst.GetText(TextType.GENERAL, 18));
//            dlg1.setDialogIconType(IconType.ERROR);
//            dlg1.setMessage(StrTextConst.GetText(TextType.GENERAL, 19));
//            dlg1.setButton1OnClickListener(StrTextConst.GetText(TextType.GENERAL, 0), null);
//            dlg1.show();
            // Toast.makeText(CurrentActivity,
            // "Failed to connect to DB!",Toast.LENGTH_SHORT).show();
            return;
        }

        while (c.moveToNext()) {
            Button btn = new Button(dlg.getContext());

            Date printTime = new Date(c.getLong(1));
            if (!c.isNull(2)) {
                printTime = new Date(c.getLong(2));
            }

            // String cashier = c.getString(3);

            String billNumber = "111";
//            if ((c.getInt(0) + "").length() < Zeroes.length()) {
//                billNumber += Zeroes.substring(0, Zeroes.length() - (c.getInt(0) + "").length()) + c.getInt(0);
//            } else {
//                billNumber += (c.getInt(0) + "");
//            }

            if (!c.isNull(6)) {
                billNumber += " (" + balance_title + ":" + c.getString(6) + ")";
            }

            String otime = String.format("%02d-%02d-%4d  ", printTime.getDate(), (printTime.getMonth() + 1), (printTime.getYear() + 1900));
            otime += String.format("%02d:%02d.%02d", printTime.getHours(), printTime.getMinutes(), printTime.getSeconds());

            double totalamt = 0;
            double totaltax = 0;
            Cursor data = DBFunc.Query("SELECT idx,Amount,Qty FROM BillPLU WHERE BillNo = " + c.getInt(0) + " AND Cancel = 0 ORDER BY idx ASC", false);
            if (data != null) {
                while (data.moveToNext()) {
                    double itemprice = data.getInt(2) * data.getDouble(1);
                    Cursor plutax = DBFunc.Query("SELECT TaxID, Rate, Type FROM BillPLUTax WHERE BillPLU_idx = " + data.getInt(0) + " ORDER BY Seq ASC", false);
                    if (plutax != null) {
                        double _tax = 0;
                        while (plutax.moveToNext()) {
                            if (plutax.getInt(2) == 0) {// not include(add on
                                // tax)
                                _tax = (itemprice * (plutax.getDouble(1) / 100f));
                                itemprice += _tax;
                            } else if (plutax.getInt(2) == 1) {// is inclusive
                                // (VAT)
                                _tax = (itemprice - (itemprice / (1f + (plutax.getDouble(1) / 100f))));
                                itemprice = (itemprice / (1f + (plutax.getDouble(1) / 100f)));
                            }

                        }
                        totaltax += _tax;
                        plutax.close();
                    }
                    totalamt += itemprice;
                }
                data.close();
            }

            if (c.isNull(2)) {
                if (c.getInt(5) == 1) {
                    btn.setText("AAA");
                } else {
                    //btn.setText(StrTextConst.GetText(StrTextConst.GetText(StrTextConst.TextType.POS, 403, billNumber));

                    btn.setText("AAA"); }
            } else {
                if (c.getInt(5) == 1) {
                    btn.setText("AAA");
                    //btn.setText(StrTextConst.GetText(StrTextConst.GetText(StrTextConst.TextType.POS, 406, billNumber));
                } else {
                    btn.setText("AAA");
                   // btn.setText(StrTextConst.GetText(StrTextConst.GetText(StrTextConst.TextType.POS, 405, billNumber));
                }
            }

            btn.setText(StrTextConst.GetText(StrTextConst.TextType.POS, 407, btn.getText().toString(), otime, String.format("%.2f", totalamt), String.format("%.2f", totaltax)));

            btn.setTag(c.getInt(0));
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // dlg.dismiss();
                    // SelectBill((Integer)v.getTag());
                    final int oldbillid = (Integer) v.getTag();
                    final DialogBox dlg1 = new DialogBox(v.getContext());
                    dlg1.setDialogIconType(DialogBox.IconType.QUESTION);
                    dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.POS, 0));
                    dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.POS, 420, oldbillid, BillID));
                    dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 6), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dlg1.dismiss();
                            Cursor c = DBFunc.Query("SELECT BalNo FROM Bill WHERE BillNo = " + oldbillid, false);
                            String oldbal = "";
                            if (c.moveToNext()) {
                                oldbal = c.getString(0);
                            }
                            c.close();

                            long time = System.currentTimeMillis();

                            DBFunc.ExecQuery("UPDATE Bill SET MergeBill = " + BillID + ", CloseDateTime = " + time + " WHERE BillNo = " + oldbillid, false);

                            //PrintMoveKP(oldbillid, new Date(time), oldbal, BalanceNo);
                            DBFunc.ExecQuery("UPDATE BillPLU SET BillNo = " + BillID + " WHERE BillNo = " + oldbillid, false);

                            String newbill = "" + BillID;

                            // if(!paymentOnlyKP){
                            //CloseBalance(false);
                            // }

                            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth, System.currentTimeMillis(), DBFunc.PurifyString("POS -> Balance Merge -> Bill: " + oldbillid + "->" + newbill));
                            // UpdateItemList();
                            // ShowErrorDialog(balance_title+" merged!");
                            DialogBox dlg2 = new DialogBox(v.getContext());
                            dlg2.setDialogIconType(DialogBox.IconType.INFORMATION);
                            dlg2.setTitle(StrTextConst.GetText(StrTextConst.TextType.POS, 0));
                            dlg2.setMessage(StrTextConst.GetText(StrTextConst.TextType.POS, 152, oldbillid, newbill));
                            dlg2.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
                            dlg2.show();
                            dlg.dismiss();
                        }
                    });

                    dlg1.setButton2OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 7), null);
                    dlg1.show();

                }
            });

            laylist.addView(btn);
        }
        c.close();

        if (laylist.getChildCount() == 0) {
            TextView tv = new TextView(dlg.getContext());
            tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            tv.setGravity(Gravity.CENTER);
           // tv.setText(StrTextConst.GetText(StrTextConst.GetText(StrTextConst.TextType.POS, 153, balance_title));
            laylist.addView(tv);
        }
        dlg.show();
    }
//
//
//    public static void UpdateBill(Context context,Integer billID,String BillNo, String existingBillNo,String Itemstatus) {
//        try {
////            String chk_hide_img = Query.GetOptions(20);
////            if (chk_hide_img.equals("1")) {
////                ChkOut_sldNameArr = RecyclerViewNoImageAdapter.sldNameArr;
////                ChkOut_sldQtyArr = RecyclerViewNoImageAdapter.sldQtyArr;
////                ChkOut_sltBillDisArr = RecyclerViewNoImageAdapter.sltBillDisArr;
////                ChkOut_sltPriceTotalArr = RecyclerViewNoImageAdapter.sltPriceTotalArr;
////                ChkOut_sldIDArr = RecyclerViewNoImageAdapter.sldIDArr;
////                ChkOut_intTableNo = RecyclerViewNoImageAdapter.intTableNo;
////                ChkOut_vchQueueNo = RecyclerViewNoImageAdapter.vchQueueNo;
////                ChkOut_slddisID = RecyclerViewNoImageAdapter.slddisID;
////                ChkOut_slddisName = RecyclerViewNoImageAdapter.slddisName;
////                ChkOut_slddisTypeName = RecyclerViewNoImageAdapter.slddisTypeName;
////                ChkOut_slddisType = RecyclerViewNoImageAdapter.slddisType;
////                ChkOut_slddisValue = RecyclerViewNoImageAdapter.slddisValue;
////            } else {
//                ChkOut_sldNameArr = RecyclerViewAdapter.sldNameArr;
//                ChkOut_sldQtyArr = RecyclerViewAdapter.sldQtyArr;
//                ChkOut_sltBillDisArr = sltBillDisArr;
//                ChkOut_sltPriceTotalArr = RecyclerViewAdapter.sltPriceTotalArr;
//                ChkOut_sldIDArr = RecyclerViewAdapter.sldIDArr;
//                ChkOut_intTableNo = RecyclerViewAdapter.intTableNo;
//                ChkOut_vchQueueNo = RecyclerViewAdapter.vchQueueNo;
//                ChkOut_slddisID = RecyclerViewAdapter.slddisID;
//                ChkOut_slddisName = RecyclerViewAdapter.slddisName;
//                ChkOut_slddisTypeName = RecyclerViewAdapter.slddisTypeName;
//                ChkOut_slddisType = RecyclerViewAdapter.slddisType;
//                ChkOut_slddisValue = RecyclerViewAdapter.slddisValue;
////            }
//            String str_Cancel = Query.ItemStatus(Itemstatus);
//
//            for (int i = 0; i < ChkOut_sldNameArr.size(); i++) {
//                String OnlineOrderBill = Query.GetOnlineOrderBillStatus(BillNo);
//                String str_vchQueueNo = "0";
//                String str_intTableNo = "0";
//
//                if (ChkOut_vchQueueNo.size() > 0) {
//                    try {
//                        str_vchQueueNo = Query.GetValue(ChkOut_vchQueueNo.size(), ChkOut_vchQueueNo.get(i));
//
//                    }catch (IndexOutOfBoundsException e){
//
//                    }
//                }
//
//                if (ChkOut_intTableNo.size() > 0) {
//                    try {
//                        str_intTableNo = Query.GetValue(ChkOut_intTableNo.size(), ChkOut_intTableNo.get(i));
//                    }catch (IndexOutOfBoundsException e){
//                        str_intTableNo = "0";
//                    }
//
//                }
//
//                try {
//                    Query.saveDetailsBillProduct_AssignValue(billID, BillNo, OnlineOrderBill, ChkOut_sldQtyArr.get(i), ChkOut_sldNameArr.get(i), ChkOut_sltPriceTotalArr.get(i),
//                             ChkOut_sltBillDisArr.get(i), ChkOut_sldIDArr.get(i), str_vchQueueNo, str_intTableNo,
//                            str_Cancel, ChkOut_slddisID.get(i), ChkOut_slddisName.get(i), ChkOut_slddisTypeName.get(i), ChkOut_slddisType.get(i), ChkOut_slddisValue.get(i));
//                }catch (IndexOutOfBoundsException e){
//                    Log.i("ERRRIR",e.getMessage());
//                }
//            }
//            for (int i = 0; i < ChkOut_sldIDArr.size(); i++) {
//                ArrayList<Integer> Modifier_ID = ButtonAdapter.ID;
//                String listString = Query.GetModifier(Modifier_ID);
//
//                Integer BillID = Integer.parseInt(Query.findBillIDByBillNo(BillNo));
//                Query.SaveBillPLU(BillID, BillNo, ChkOut_sldIDArr.get(i), ChkOut_sldNameArr.get(i),
//                        ChkOut_sltPriceTotalArr.get(i), 1, str_Cancel, listString);
////                Query.SaveBillPLU(MainActivity.BillID, MainActivity.strbillNo, ChkOut_sldIDArr.get(i), ChkOut_sldNameArr.get(i),
////                        ChkOut_sltPriceTotalArr.get(i), 1, str_Cancel, listString);
//
//                Integer bill_plu_id = Query.findLatestID("idx", "BillPLU", false);
//                //calculateTaxForPLU(bill_plu_id, Integer.parseInt(ChkOut_sldIDArr.get(i)));
//
//                //MainActivity.St = "1";
//            }
//            //EditProductSheetFragment.str_edit_product_sheet_fragment = "0";
//            ButtonAdapter.ID.clear();
//
//            //String sql = "Select TotalItems,totalNettAmount FROM BillDetails Where BillNo = '" + MainActivity.strbillNo + "' ";
//            String sql = " SELECT SUM(ProductQty),SUM(ProductPrice) FROM DetailsBillProduct " +
//                    " Where BillNo = '" + BillNo + "' " +
//                    " group by BillNo";
////            String sql = " SELECT count(ProductQty),SUM(ProductPrice) FROM Details_BillProduct " +
////                    " Where BillNo = '" + MainActivity.strbillNo + "' " +
////                    " group by BillNo";
//            Cursor c = DBFunc.Query(sql, false);
//            if (c != null) {
////                if (chk_hide_img.equals("1")) {
////
////                    RecyclerViewNoImageAdapter.totalItems = 0;
////                    RecyclerViewNoImageAdapter.totalAmount = 0.0;
////                } else {
//
//                    RecyclerViewAdapter.totalItems = 0;
//                    RecyclerViewAdapter.totalAmount = 0.0;
////                }
//                while (c.moveToNext()) {
//                    //if (c.moveToNext()) {
//                    if (!c.isNull(0)) {
//
////                        if (chk_hide_img.equals("1")) {
////                            RecyclerViewNoImageAdapter.totalItems += c.getInt(0);
////                            RecyclerViewNoImageAdapter.totalAmount += Double.valueOf(c.getString(1));
////
////                        } else {
//                            RecyclerViewAdapter.totalItems += c.getInt(0);
//                           // RecyclerViewAdapter.totalAmount += c.getInt(0) * Double.valueOf(c.getString(1));
//                            RecyclerViewAdapter.totalAmount +=  Double.valueOf(c.getString(1));
////                        }
//                    }
//                }
//                c.close();
//            }
//            //MainActivity.St = "1";
////        MainActivity.St = "1";
////        ProductMainPageFragment.St = "1";
////        RecyclerViewAdapter.St = "1";
//        }catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//    @Override
//    public void onResume() {
//
//        updateCheckoutAdapter();
//
//        super.onResume();
////        EditProductSheetFragment.CallCheckAdapterFun(appContext);
//    }


//    @Override
//    public void onResume() {
//        Log.i("chksttatuss_On","onresume");
//        appContext = CheckOutActivity.this;
//        updateCheckoutAdapter(appContext);
////        updateCheckOutActivityFun();
//        super.onResume();
////        items.clear();
////        items = dbHelper.getItems(); // reload the items from database
////        customAdapter.notifyDataSetChanged();
//
//    }

    @Override
    protected void onRestart() {
        appContext = CheckOutActivity.this;
        Log.i("chksttatuss_On","onrestart");
        updateCheckoutAdapter(appContext);
//        updateCheckOutActivityFun();
        super.onRestart();
    }
//
    @Override
    protected void onPause() {
//        Log.i("chksttatuss_On","onPause");
//        appContext = CheckOutActivity.this;
//        updateCheckoutAdapter(appContext);
//        updateCheckOutActivityFun();

        super.onPause();
        Log.i("ChkLifeCycle","onPause");
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("ChkLifeCycle","onResume"+"___"+error);


    }

    @Override
    protected void onStop() {
//        Intent i = new Intent(CheckOutActivity.this, MainActivity.class);
//        startActivity(i);
//        finish();
        super.onStop();
        Log.i("ChkLifeCycle","onStop");
    }

    public static void updateCheckoutAdapter(Context context) {
        sldQtyArr.clear();
        sldNameArr.clear();
        sltPriceTotalArrEach.clear();
        sldIDArr.clear();
        sldItemDisArr.clear();
        sldDiscountName.clear();
        sldDiscountType.clear();
        sldDiscountValue.clear();
        sldOpenPriceStatus.clear();
        sldRemarks.clear();
        sldRemarksNoSubstring.clear();

        getDetailsBillProductAll(BillNo);

        customAdapter = new CheckOutAdapter(context,BillNo,
                sldQtyArr, sldNameArr, sltPriceTotalArrEach, sldIDArr , sldItemDisArr,
                sldDiscountName, sldDiscountType, sldDiscountValue,sldOpenPriceStatus,sldRemarks,billdetailsPID);
//                sldQtyArr, sldcheckOutListViewNameArr, sltPriceTotalArr, sldIDArr , sldItemDisArr, sldDiscountName, sldDiscountType, sldDiscountValue);

//        binding.buttons.checkOutListView
        binding.checkoutInfo.checkOutListView.setAdapter(customAdapter);

        //checkOutListOnClickFun(binding.checkoutInfo.checkOutListView);

        updateDiscountFun();
        updateTotalItemsFun();
        updateSubtotalFun();
        updateTotalAmtRoundAmtDisAmtFun();
        //updateAmountDisFun();
        //updatePercentageDisFun();

        PaymentTypesCheckoutAdapterShowFun(appContext,BillNo,resourceVal);

    }

    String barcode = "";
    EditText pop_up_window_item_remarks;
    private void checkOutListOnClickFun(ListView checkOutListView) {
        this.binding.checkoutInfo.checkOutListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

//                CheckOutActivity.binding.layCheckoutOverAll.setAlpha(1);

                //String chk_hide_img = Query.GetOptions(20);
                EditProductSheetFragment.ClearExistingValue();

//                FragmentManager manager = getSupportFragmentManager();
//                EditProductSheetFragment editProductSheetFragment = new EditProductSheetFragment();
                //Object o = checkOutListView.getItemAtPosition(position);
//                String sqll =  "SELECT count(ProductQty),ProductName,SUM(ProductPrice),BillNo," +
//                "SUM(ItemDiscountAmount),ProductID,CategoryID,CategoryName,TaxID,TaxName,SUM(TaxAmount)," +
//                        "DiscountName,DiscountTypeName,DiscountValue,ProductPrice,ID  " +
//                        "FROM Details_BillProduct " +
//                        // "Where BillNo = '"+ BillNo +"' group by ProductQty";
//                        "Where BillNo = '"+ BillNo +"' AND ProductID = '"+ sldIDArr.get(position) +"' AND Cancel = 'SALES' group by ProductID,OpenPriceStatus";
//

                String productAllowRemarks = TransactionDetailsActivity.ValidAndGetValue("AllowRemarks", "PLU",
                        "ID", sldIDArr.get(position), true);

//                String sqll =  "SELECT ProductQty,ProductName,ProductPrice,BillNo,ItemDiscountAmount,ProductID,CategoryID," +

                GetItemDataCheckout(BillNo,sldIDDetailsBill.get(position));


               // if (Remarks.equals("0")) {
//                try {
//
//                }catch (Exception e){
//
//                }
                if (productAllowRemarks == null || productAllowRemarks.equals("null") || productAllowRemarks.equals("0")) {

                    ShowEditProductFun(position);
                } else {

                    CheckOutActivity.binding.layCheckoutOverAll.setAlpha(0.4F);
//                    CheckOutActivity.binding.layCheckoutOverAll.setOnTouchListener(View::onTouchEvent);
//                    CheckOutActivity.binding.layCheckoutOverAll.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            CheckOutActivity.binding.layCheckoutOverAll.setAlpha(1);
//                        }
//                    });
//                binding.inner.setAlpha(0.4F);
//                binding.inner.setVisibility(View.GONE);


                    LayoutInflater inflater = (LayoutInflater) appContext
                            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                    View popupView = inflater.inflate(R.layout.activity_item_remarks_pop_up, null);


                    final PopupWindow popupWindow = new PopupWindow(popupView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
                    popupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
                    LinearLayout lvclose = (LinearLayout) popupView.findViewById(R.id.linear_close);


                    //EditText pop_up_window_item_remarks = (EditText) popupView.findViewById(R.id.pop_up_window_itemremarks) ;
                    pop_up_window_item_remarks = (EditText) popupView.findViewById(R.id.pop_up_window_itemremarks);
                    pop_up_window_item_remarks.setText(Remarks);
                    pop_up_window_item_remarks.setTextColor(Color.BLACK);
                    pop_up_window_item_remarks.setOnKeyListener(new EditText.OnKeyListener() {
                        public boolean onKey(View v, int keyCode, KeyEvent event) {

                            pop_up_window_item_remarks.setTextColor(Color.BLACK);

                            Remarks = pop_up_window_item_remarks.getText().toString();

                            String barcodescanner = pop_up_window_item_remarks.getText().toString();

                           //barcode = barcodescanner;
                            if (barcodescanner.endsWith("#")) {

                                pop_up_window_item_remarks.setText(barcodescanner.substring(0,9));

                                return false;
                            }
                            return false;
                        }
                    });
//        pop_up_window_item_remarks.setText("testing3");


                    popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    popupWindow.setOutsideTouchable(true);
                    popupWindow.setFocusable(true);
                    popupWindow.update();
//        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                    popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
//        popupWindow.showAsDropDown(popupView, 0, 0);
                    popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);

                    Button btn_add_pop_up_window_itemremarks = (Button) popupView.findViewById(R.id.btn_add_pop_up_window_itemremarks);
                    btn_add_pop_up_window_itemremarks.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            // Toast.makeText(getContext(), "Update Successfully!", Toast.LENGTH_LONG).show();
                            popupWindow.dismiss();
//
//                            updateAndRefreshRemarks(pop_up_window_item_remarks.getText().toString(),
//                                    BillNo,EditFragmentProductID,openPrice,EditFragmentName,"openPrice");


                            String normal_status = "openPrice";
                            if (EditFragmentOpenPrice != null && EditFragmentOpenPrice.equals("0")) {
                                normal_status = "normal";
                            } else if (EditFragmentOpenPrice == null){
                                normal_status = "normal";
                            } else if (EditFragmentOpenPrice == "null"){
                                normal_status = "normal";
                            }
                            Log.i("Sdfds___","normal_status___"+normal_status);
                            updateAndRefreshRemarks(pop_up_window_item_remarks.getText().toString(),
                                    BillNo, EditFragmentProductID, EditFragmentOpenPrice, EditFragmentName,normal_status,String.valueOf(billdetailsPID.get(position)));

                            CheckOutActivity.binding.layCheckoutOverAll.setAlpha(1);

                            //NormalFun(holder,position_val);

                            Remarks = pop_up_window_item_remarks.getText().toString();

                            ShowEditProductFun(position);

                        }
                    });

                    Button btn_cancel_pop_up_window_itemremarks = (Button) popupView.findViewById(R.id.btn_cancel_pop_up_window_itemremarks);
                    btn_cancel_pop_up_window_itemremarks.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            popupWindow.dismiss();
                            //onResume();
                            CheckOutActivity.binding.layCheckoutOverAll.setAlpha(1);
                            ShowEditProductFun(position);
                        }
                    });

                    lvclose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            popupWindow.dismiss();
                            //onResume();
                            CheckOutActivity.binding.layCheckoutOverAll.setAlpha(1);
                        }
                    });

                    ImageView remarks_clear_itemremarks = (ImageView) popupView.findViewById(R.id.remarks_clear);
                    remarks_clear_itemremarks.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
//                popupWindow.dismiss();
                            //onResume();
//                            CheckOutActivity.binding.layCheckoutOverAll.setAlpha(1);
                            pop_up_window_item_remarks.setText("");
                        }
                    });

                    popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {

                            //Do Something here
                            CheckOutActivity.binding.layCheckoutOverAll.setAlpha(1);
                        }
                    });

//                    popupWindow.setTouchInterceptor(new View.OnTouchListener()
//                    {
//
//                        public boolean onTouch(View v, MotionEvent event)
//                        {
////                Log.i("dsfsdf___","___"+event.getAction());
////                if (event.getAction() == MotionEvent.ACTION_OUTSIDE)
////                {
////                            popupWindow.dismiss();
//                            CheckOutActivity.binding.layCheckoutOverAll.setAlpha(1);
//                            return true;
////                }
//
////                return false;
//                        }
//                    });
//                }



    /* write you handling code like...
    String st = "sdcard/";
    File f = new File(st+o.toString());
    // do whatever u want to do with 'f' File object
    */
                }
            }
        });
    }

    private void GetItemDataCheckout(String BillNo,Integer bID) {
        String sqll =  "SELECT SUM(ProductQty),ProductName,ProductPrice,BillNo,ItemDiscountAmount,ProductID,CategoryID," +
                "CategoryName,TaxID,TaxName,TaxAmount," +
                "DiscountName,DiscountTypeName,DiscountValue, " +
                "ProductPrice,ID,OpenPriceStatus,Remarks,ID " +
                "FROM DetailsBillProduct Where BillNo = '"+BillNo+"' AND  " +
                "ID = "+bID+" " ;
//                        "ProductID = '"+sldIDArr.get(position)+"' " +
//                        " AND OpenPriceStatus = '"+sldOpenPriceStatus.get(position)+"' " ;
////                        if (sldRemarks.get(position) != null && sldRemarks.get(position).length() > 0) {
////                            sqll +=   "AND Remarks = '"+sldRemarks.get(position) + "' ";
////                        }
//                        if (sldRemarksNoSubstring.get(position) != null && sldRemarksNoSubstring.get(position).length() > 0) {
//                            sqll +=   "AND Remarks = '"+sldRemarksNoSubstring.get(position) + "' ";
//                        } else {
//                            //sqll +=   "AND Remarks IS NULL";
//                            sqll +=   "AND (Remarks IS NULL OR Remarks = 'null' OR Remarks = '' ) ";
//                        }
//                    sqll += "  AND Cancel = 'SALES' " +
//                        "group by OpenPriceStatus,Remarks" ;

        Log.i("Remarks__","sqllll___"+sqll);
        Cursor c = DBFunc.Query(sqll,false);
        EditFragmentName = "0";
        EditFragmentProductID = "0";
        EditFragmentPrice = "0";
        EditFragmentOpenPrice = "0";
        Remarks = "";
        //ID = "";
        EditFragmenttotalQty = "";
        //int ccount = 0;
        if (c != null){
//                    while (c.moveToNext()){
            if (c.moveToNext()){
                //ccount ++;
                EditFragmentName = c.getString(1);
                EditFragmentProductID = c.getString(5);
                EditFragmentPrice = c.getString(14);
//                        EditFragmentQty = ccount;
                EditFragmentOpenPrice = c.getString(16);
                Remarks = c.getString(17);
                //ID = c.getString(18);
                EditFragmenttotalQty = c.getString(0);
            }
            c.close();
        }
    }

//    String barcode = "";


    private void ShowEditProductFun(int position) {
        FragmentManager manager = getSupportFragmentManager();
        EditProductSheetFragment editProductSheetFragment = new EditProductSheetFragment();

        EditProductSheetFragment.billNo = BillNo;
        Log.i("__","EditFragmentPriceee_"+EditProductSheetFragment.billNo);
        EditProductSheetFragment.productName = EditFragmentName;
        Log.i("__","EditFragmentPriceee_"+EditProductSheetFragment.productName);
        //EditProductSheetFragment.productPrice = EditFragmentPrice;
        //EditProductSheetFragment.productPrice = String.valueOf(Double.valueOf(EditFragmentPrice) * Double.valueOf(EditFragmenttotalQty));
        //EditProductSheetFragment.EditFragmentPrice = String.valueOf(Double.valueOf(EditFragmentPrice) * Double.valueOf(EditFragmenttotalQty));
        EditProductSheetFragment.EditFragmentPrice = EditFragmentPrice;
        Log.i("__","EditFragmentPriceee_1"+EditProductSheetFragment.EditFragmentPrice);
        EditProductSheetFragment.productID = sldIDArr.get(position);
        Log.i("__","EditFragmentPriceee_2"+EditProductSheetFragment.productID);
        EditProductSheetFragment.DetailBillID = sldDetailsBillID.get(position);
        Log.i("__","EditFragmentPriceee_3"+EditProductSheetFragment.DetailBillID);
        EditProductSheetFragment.FragmentVar = "CheckoutItem";
        EditProductSheetFragment.EditFragmenttotalQty = EditFragmenttotalQty;
        Log.i("__","EditFragmentPriceee_4"+EditProductSheetFragment.EditFragmenttotalQty);
//        EditProductSheetFragment.EditFragmentOpenPrice = EditFragmentOpenPrice;
//        EditProductSheetFragment.EditFragmentOpenPrice = sldOpenPriceStatus.get(position);
        EditProductSheetFragment.EditFragmentOpenPrice = EditFragmentOpenPrice;
        //EditProductSheetFragment.ID = ID;
        EditProductSheetFragment.ID = String.valueOf(billdetailsPID.get(position));
        Log.i("__","EditFragmentPriceee_ID"+EditProductSheetFragment.ID);
        Log.i("__","EditFragmentPriceee_IDttt"+sldDetailsBillID.get(position));
        Log.i("__","EditFragmentOpenPriceee_5"+EditProductSheetFragment.EditFragmentOpenPrice);
        EditProductSheetFragment.EditFragmentRemarks = Remarks;
        Log.i("__","EditFragmentOpenPriceee_6"+EditProductSheetFragment.EditFragmentRemarks);
        Log.i("__","EditFragmentOpenPrice_"+EditFragmentOpenPrice+"__"+sldOpenPriceStatus.get(position));
        try {
            Log.i("DFDFDFD___","TRTRTR____"+"_____5");
            //EditProductCheckoutSheetFragment editProductSheetFragment = new EditProductCheckoutSheetFragment();
            editProductSheetFragment.show(manager, editProductSheetFragment.getTag());
        }catch (Throwable e){
            editProductSheetFragment.onStop();
        }
    }

    private void updateAndRefreshRemarks(String strRemarks, String billNo, String popupProductID,
                                         String EditFragmentOpenPrice, String titleVar,String status,String ID) {
        Log.i("Sdfs___","dfdf_"+EditFragmentOpenPrice);
        //MainActivity.binding.container.setAlpha(0.0F);
//        MainActivity.binding.container.setAlpha(1);
//        String updateItemRemarksPLU = "UPDATE PLU SET Remarks = '"+strRemarks+
//                "' WHERE ID = "+ popupProductID;
//        Log.i("update___","update___"+updateItemRemarksPLU);
//        DBFunc.ExecQuery(updateItemRemarksPLU,true);

        String dateFormat3 = CheckOutActivity.dateFormat55.format(new Date()).toString();
        String dateFormat4 = CheckOutActivity.dateFormat555.format(new Date()).toString();

        String chkExisting = TransactionDetailsActivity.ValidAndGetValue("BillNo", "DetailsBillProduct", "BillNo",
                MainActivity.strbillNo, false);
        Log.i("existing___","chkExisting______"+chkExisting);
        Log.i("existing___","chstatus_____"+status);
        if (chkExisting.equals("0")) {
            //NormalFun(holder,position_val);
            //AddNewDetailsBillProduct(holder, position_val);

            Log.i("Dfd___","IDDD_first4__"+ID);
            Query.UpdateDetailsBillProduct("ChkActRemarksExBZero",status,EditFragmentOpenPrice,strRemarks,billNo,popupProductID,"0");

            Query.UpdatePLUModi(appContext,popupProductID,MainActivity.strbillNo,strRemarks,EditFragmentOpenPrice,ID);
        } else {
            if (status.equals("openPrice")) {
                Log.i("Dfd___","IDDD_first2__"+ID);
                Query.UpdateDetailsBillProduct("ChkActRemarksOpenPrice",status,EditFragmentOpenPrice,strRemarks,billNo,popupProductID,"0");

                Query.UpdatePLUModi(appContext,popupProductID,MainActivity.strbillNo,strRemarks,EditFragmentOpenPrice,ID);
            } else {
                String chkExistingRemarks = TransactionDetailsActivity.ValidAndGetValue("Remarks", "DetailsBillProduct", "Remarks",
                        strRemarks, false);
                Log.i("chkExistingRemarks_____", "chkExistingRemarks_____" + chkExistingRemarks);
//                if (chkExistingRemarks.equals("0")) {
                if (chkExistingRemarks != null && chkExistingRemarks.trim().length() > 0 && !(chkExistingRemarks.equals("0")) && !(chkExistingRemarks.trim() == null) && !(chkExistingRemarks.trim() == "null")) {

                    Log.i("Dfd___","IDDD_first3__"+ID);
                    //Query.UpdateDetailsBillProduct("ChkActRemarksNotOpenPrice",status,EditFragmentOpenPrice,strRemarks,billNo,popupProductID,"0");
                    Query.UpdateDetailsBillProduct("ChkActRemarksNotOpenPrice",status,EditFragmentOpenPrice,strRemarks,billNo,popupProductID,ID);

                    //Query.UpdatePLUModi(appContext,popupProductID,MainActivity.strbillNo,strRemarks,EditFragmentOpenPrice);
                } else {

                    if (ID != null) {

                        try {

                            Log.i("Dfd___","IDDD_first1__"+ID);
                            Query.UpdateDetailsBillProduct("ChkActRemarksNotOpenPrice-ID","","",
                                    strRemarks,"","",ID);

                            Query.UpdatePLUModi(appContext,popupProductID,MainActivity.strbillNo,strRemarks,EditFragmentOpenPrice,ID);
                        }catch (Exception e){

                        }
                    }
                }
            }
        }
//        Query.UpdatePLUModi(appContext,popupProductID,MainActivity.strbillNo,strRemarks,EditFragmentOpenPrice);
        //getBillByBillNo();

        //hashValues = Query.GetProductByID(MainActivity.strbillNo);
        //Query.GetProductByID(MainActivity.strbillNo);


        //CheckoutButtonValues(totalItems, totalAmount);

        //MainCheckoutButtonUpdate(holder,position_val);


//        FragmentManager manager = ((AppCompatActivity) mContext).getSupportFragmentManager();
//        EditProductSheetFragment editProductSheetFragment = new EditProductSheetFragment();
//        editProductSheetFragment.setCancelable(false);
//        editProductSheetFragment.show(manager, editProductSheetFragment.getTag());

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        Log.i("SDfds____","ontouch__"+motionEvent.toString());
        CheckOutActivity.binding.layCheckoutOverAll.setAlpha(1);
        return false;
    }

    public void SweetAlertWarningYesOnly(Context context, String header, String description) {
        //Context can;t transfer for payment calling ,so use this(checkoutactivity) funtion
        try {

            new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
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
        } catch (Exception e){
            Log.i("Alertboxexception_","execption_chkddkkk_"+e.getMessage());
        }
    }
}

//        if (!(requestCode == CAMERA_REQUEST || requestCode == 6699)) {
//            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
//            if (result != null) {
//                //if qrcode has nothing in it
//                if (result.getContents() == null) {
//                    Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
//                } else {
//                    //if qr contains data
//                    if (qrval == DeclarationConf.QR_REQUEST) {
//                        qrval = 0;
//                        VolleyGetToken(DeclarationConf.QR_REQUEST, result.getContents());
//
//                    }
//                    if (qrval == DeclarationConf.QR_VOUCHER_REQUEST) {
//                        qrval = 0;
//
//                        VolleyGetToken(DeclarationConf.QR_VOUCHER_REQUEST, result.getContents());
//
//                    }
//
//                }
//            }
//        }