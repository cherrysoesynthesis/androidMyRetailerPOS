package com.dcs.myretailer.app.Checkout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.dcs.myretailer.app.Allocator;
import com.dcs.myretailer.app.CashLayoutActivity;
import com.dcs.myretailer.app.Cashier.DeclarationConf;
import com.dcs.myretailer.app.Cashier.ProductMainPageFragment;
import com.dcs.myretailer.app.CheckOutActivity;
import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.databinding.ItemPaymentCheckoutItemBookBinding;
import com.pax.dal.entity.EScannerType;
import com.pax.globalpay.opensdk.SaleMsg;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class PaymentTypesCheckoutAdapter extends RecyclerView.Adapter<PaymentTypesCheckoutAdapter.MyViewHolder> {

    private Context mContext ;
    public ArrayList<PaymentTypes> mPaymentType ;
    ArrayList<String> arr = new ArrayList<String>();
    HashMap<String,String> hashValues = new HashMap<String, String>();
    HashMap<String,String> hashValuesCount = new HashMap<String, String>();
    ArrayList selectedItems = new ArrayList();
    private String str ;
    public static String bill_type ;
    private ProductMainPageFragment productMainPageFragment;
    //int[] counter;
    public static Integer totalItems  = 0;
    public static Integer count_totalItems  = 0;
    public static Integer count_item_selected  = 0;
    public static Double totalAmount = 0.0;
    public static String disVal = "";
    public static Integer PaymentIDCheckoutAdapter = 0;
    //public static Integer paymentID = 1;
    public static String  paymentID = "1";
    public static String paymentName = "cash";
    private final DecimalFormat REAL_FORMATTER = new DecimalFormat("0.00");
    String payment_type_name = "";
    String bankName = "";
    public static String status = "1";
    public static Integer cardPaymentID = 0;
    private static final int CAMERA_REQUEST = 1888;
    Double total_item_discount = 0.0;
    Double total_bill_discount = 0.0;
    Double service_changes = 0.0;
    Double taxes_amount = 0.0;
    public static Double CashValue =  0.0;
    // public static Integer CashID =  0;
    public static String CashID =  "0";
    public static String CashName =  "0";
    public static String str_taxname = "0";
    public static String TotalItemDisount = "";
    public static String TotalBillDisount = "";
    public static String TotalTaxes = "";
    static int bill_receipt_id = 0;
    public static Integer totalQty = 0;
    public static Double amount = 0.0;
    public static Double sub_total = 0.0;
    public static Double chkoutactivity_amount = 0.0;
    public static Double chkbilldiscount = 0.0;
    public static Double chkoutactivity_staticRound = 0.0;
    public static Double ItemDiscountAmount = 0.0;
    public static final List<String> ewalletrecArr = new ArrayList<String>();
    public static final List<String> ewalletVoucherValArr = new ArrayList<String>();
    public static final int MY_SOCKET_TIMEOUT_MS = 30000;
    //    public static Boolean chk_pos_qr_code_ = false;
    public static String qrcodestring = "";
    public static Bitmap[] bitmap_qr_shoptima = null;
    public static ArrayList<String> sldTaxID = new ArrayList<String>();
    public static ArrayList<String> sldCTaxName = new ArrayList<String>();
    public static ArrayList<String> sldCTaxAmount = new ArrayList<String>();
    public static ArrayList<String> sldCTaxType = new ArrayList<String>();
    public static ArrayList<String> sldCTaxRate = new ArrayList<String>();
    public static ArrayList<String> sldDiscountName = new ArrayList<String>();
    public static ArrayList<String> sldDiscountType = new ArrayList<String>();
    public static ArrayList<String> sldDiscountValue = new ArrayList<String>();
    int qrval = 0;
    public static String payment_remarks = "";
    ItemPaymentCheckoutItemBookBinding binding = null;
    public PaymentTypesCheckoutAdapter(Context mContext, ArrayList<PaymentTypes> mPaymentType, String str, String bill_type) {
        this.mContext = mContext;
        this.mPaymentType = mPaymentType;
        this.str = str;
        this.bill_type = bill_type;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_payment_checkout_item_book, parent, false);
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_payment_checkout_item_book, parent, false);
        //View view = binding.getRoot();

        //return new MyViewHolder(view);
        return new MyViewHolder(binding);

    }

    @Override
    public int getItemCount() {
        return mPaymentType.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ItemPaymentCheckoutItemBookBinding binding;
//        public MyViewHolder(View itemView) {
//            super(itemView);
//        }

        public MyViewHolder(ItemPaymentCheckoutItemBookBinding binding) {
            super(binding.getRoot());
            this.binding = (ItemPaymentCheckoutItemBookBinding) binding;
        }
    }
    String chk_print_receipt_paper = "0";
    String checkBillNo = "";
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        String terminalTypeVal = Query.GetDeviceData(Constraints.TERMINAL_TYPE);
        if (terminalTypeVal.equals(Constraints.PAX_E600M)) {
            LinearLayout.LayoutParams paramslayCardView = new LinearLayout.LayoutParams(150, 100);
            paramslayCardView.leftMargin = 9;
            binding.cashLayout.setLayoutParams(paramslayCardView);
        } else if (terminalTypeVal.equals(Constraints.IMIN)) {
            String device = Query.GetDeviceData(Constraints.DEVICE);
            if (device.equals("M2-Max")) {
//                LinearLayout.LayoutParams paramslayCardView = new LinearLayout.LayoutParams(160,
//                        110);
                LinearLayout.LayoutParams paramslayCardView = new LinearLayout.LayoutParams(200,
                        120);
                //paramslayCardView.leftMargin = 10;
                binding.cashLayout.setLayoutParams(paramslayCardView);

//                LinearLayout.LayoutParams paramscardviewId = new LinearLayout.LayoutParams(160, 100);
//                paramscardviewId.topMargin = 10;
//                paramscardviewId.leftMargin = 10;
//                binding.cardviewId.setLayoutParams(paramscardviewId);

                LinearLayout.LayoutParams paramscardviewId = new LinearLayout.LayoutParams(160, 100);
                paramscardviewId.topMargin = 10;
                paramscardviewId.leftMargin = 10;
                binding.cardviewId.setLayoutParams(paramscardviewId);
                //private float ourFontsize = 14f;
                binding.paymentTypeNameCheckout.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f);
            }
        }

        if (mPaymentType.get(position).getPaymentName().toUpperCase().equals("CASH")){
            holder.binding.imgView.setImageResource(R.drawable.ic_cash);
        }else if (mPaymentType.get(position).getPaymentName().toUpperCase().equals("VOUCHER")){
            holder.binding.imgView.setImageResource(R.drawable.ic_voucher);
        }else if (mPaymentType.get(position).getPaymentName().toUpperCase().equals("OTHERS")){
            holder.binding.imgView.setImageResource(R.drawable.ic_more);
        }else{
            holder.binding.imgView.setImageResource(R.drawable.ic_credit);
        }
        holder.binding.paymentTypeNameCheckout.setText(mPaymentType.get(position).getPaymentName());


        getDetailsBillProduct(CheckOutActivity.BillNo);

        checkBillNo = Query.getBillNofromDetailsBillProduct(CheckOutActivity.BillNo);//check last bill have data or not

        try {
            chk_print_receipt_paper = Query.GetOptions(23);
        }catch (StringIndexOutOfBoundsException e){
            chk_print_receipt_paper = "0";
        }finally {
            chk_print_receipt_paper = chk_print_receipt_paper;
        }


        OnClickFun(holder,mPaymentType);
    }
    Bitmap bitmap__ = null;
    Integer sale_id = 0;
    private void OnClickFun(final MyViewHolder holder, final ArrayList<PaymentTypes> mPaymentType) {

        holder.itemView.setEnabled(true);

        final String finalChk_print_receipt_paper = chk_print_receipt_paper;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int position = holder.getAdapterPosition();


                String chkRemarks = Query.findfieldNameById("Remarks", "ID",
                        mPaymentType.get(position).getPaymentID() , "Payment", true);


                if (chkRemarks.equals("1")) {

                    CheckOutActivity.binding.layCheckoutOverAll.setAlpha(0.4F);

                    LayoutInflater inflater = (LayoutInflater) mContext
                            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    final View popUpView = inflater.inflate(R.layout.activity_item_payment_remarks_pop_up,
                            (ViewGroup) v.findViewById(R.id.fadePopup));

                    PopupWindow mpopup = new PopupWindow(popUpView, LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT, true); // Creation of popup
                    mpopup.setAnimationStyle(android.R.style.Animation_Dialog);

                    mpopup.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    mpopup.setOutsideTouchable(true);
                    mpopup.setFocusable(true);
                    mpopup.update();
//        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                    mpopup.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
//        popupWindow.showAsDropDown(popupView, 0, 0);
                    mpopup.showAtLocation(popUpView, Gravity.CENTER, 0, 0);

                    LinearLayout lvclose = (LinearLayout) popUpView.findViewById(R.id.linear_close) ;

                    EditText pop_up_window_item_remarks = (EditText) popUpView.findViewById(R.id.pop_up_window_itemremarks) ;
//                    pop_up_window_item_remarks.setText(remarks);


                    Button btn_add_pop_up_window_itemremarks = (Button) popUpView.findViewById(R.id.btn_add_pop_up_window_itemremarks);
                    btn_add_pop_up_window_itemremarks.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            // Toast.makeText(getContext(), "Update Successfully!", Toast.LENGTH_LONG).show();
                            mpopup.dismiss();

                            payment_remarks = pop_up_window_item_remarks.getText().toString();

                            paymentFun(position,finalChk_print_receipt_paper,payment_remarks);
                        }
                    });

                    Button btn_cancel_pop_up_window_itemremarks = (Button) popUpView.findViewById(R.id.btn_cancel_pop_up_window_itemremarks) ;
                    btn_cancel_pop_up_window_itemremarks.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mpopup.dismiss();

                            payment_remarks = pop_up_window_item_remarks.getText().toString();

                            paymentFun(position,finalChk_print_receipt_paper,payment_remarks);
                        }
                    });

                    lvclose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mpopup.dismiss();
                        }
                    });

                    ImageView remarks_clear_itemremarks = (ImageView) popUpView.findViewById(R.id.remarks_clear) ;
                    remarks_clear_itemremarks.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
//                popupWindow.dismiss();
                            //onResume();
//                            CheckOutActivity.binding.layCheckoutOverAll.setAlpha(1);
                            pop_up_window_item_remarks.setText("");
                        }
                    });

                    mpopup.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {

                            //Do Something here
                            CheckOutActivity.binding.layCheckoutOverAll.setAlpha(1);
                        }
                    });

//                    mpopup.setTouchInterceptor(new View.OnTouchListener()
//                    {
//
//                        public boolean onTouch(View v, MotionEvent event)
//                        {
////                Log.i("dsfsdf___","___"+event.getAction());
////                if (event.getAction() == MotionEvent.ACTION_OUTSIDE)
////                {
////                            mpopup.dismiss();
//                            CheckOutActivity.binding.layCheckoutOverAll.setAlpha(1);
//                            return true;
////                }
//
////                return false;
//                        }
//                    });

            } else {
                    paymentFun(position,finalChk_print_receipt_paper,"");
                }
            }
        });
    }

    private void paymentFun(Integer position,String finalChk_print_receipt_paper,String payment_remarks) {
        PaymentIDCheckoutAdapter = Integer.parseInt(mPaymentType.get(position).getPaymentID());
        paymentID = mPaymentType.get(position).getPaymentID();
        paymentName = mPaymentType.get(position).getPaymentName();
        String mpaymentypeStatus = mPaymentType.get(position).getSwitchSTATUS();
        String mpaymentypeLink = mPaymentType.get(position).getLinktoPaymentApp();
        String mpaymentypeShoptima = mPaymentType.get(position).getIntegratetoShoptima();
        String mpaymentypeFullamt = mPaymentType.get(position).getFullAmount();

        DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                System.currentTimeMillis(), DBFunc.PurifyString("ClickedPaymentButton- checkBillNo-" + CheckOutActivity.BillNo +
                        ",STATUS-" + checkBillNo +   ",paymentID-" + paymentID + ",mpaymentypeStatus-" + mpaymentypeStatus+ ",mpaymentypeLink-" + mpaymentypeLink
                        + ",PaymentName-" +paymentName+ ",mpaymentypeShoptima-" +mpaymentypeShoptima+ ",mpaymentypeFullamt-" +mpaymentypeFullamt+
                        ",finalChk_print_receipt_paper-" +finalChk_print_receipt_paper));


        final String chk_qr_code_on_receipt = Query.GetOptions(18);
//                holder.itemView.setEnabled(false);
//                CheckOutActivity.myrv.setEnabled(false);
//                String checkBillNo = Query.getBillNofromDetailsBillProduct(CheckOutActivity.BillNo);//check last bill have data or not
        if (checkBillNo.length() > 0) {  // if have , can't z close , so return false

            chkbilldiscount = CheckOutActivity.discount_amount;
            chkoutactivity_amount = CheckOutActivity.amount;
            chkoutactivity_staticRound = CheckOutActivity.staticRound;

            if (mpaymentypeStatus.equals("1")){


                if (mpaymentypeLink.equals("1")){


                    cardPaymentID = Integer.parseInt(paymentID) ;

                    Intent intent = new Intent(mContext, CheckOutActivity.class);
                    intent.putExtra("BillNo",CheckOutActivity.BillNo);
                    intent.putExtra("Status","CardPayment");
                    intent.putExtra("Ezlink","");
                    intent.putExtra("StatusSALES","");
                    mContext.startActivity(intent);
                    //((Activity)mContext).finish();


                }else if (mpaymentypeShoptima.equals("1")){

                    qrval = DeclarationConf.QR_VOUCHER_REQUEST;
                    ScannerTester.getInstance(EScannerType.REAR).scan(handler,10000);

                }else if (mpaymentypeFullamt.equals("1")){


//                            getDetailsBillProduct(CheckOutActivity.BillNo);

//                                CashLayoutActivity.SaveBillPayment(CheckOutActivity.BillNo, sub_total, totalQty, amount, ItemDiscountAmount,
//                                        PaymentTypesCheckoutAdapter.paymentID, PaymentTypesCheckoutAdapter.paymentName,
//                                        "0", String.valueOf(amount), mContext,"cash","");




                    if (finalChk_print_receipt_paper.equals("1")) {


                        final SweetAlertDialog syncDialog =
                                new SweetAlertDialog(mContext, SweetAlertDialog.WARNING_TYPE)
                                        .setTitleText(Constraints.Print)
                                        .setConfirmText(Constraints.YES)
                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sDialog) {
                                                sDialog.dismissWithAnimation();

                                                SaveBillPaymentFun();

                                                if (sale_id > 0) {


                                                    if (chk_qr_code_on_receipt.equals("1")) {
                                                        //bitmap_qr_shoptima = Query.GetPrintQRCodeOnReceipt(context);
                                                        //Query.GetPrintQRCodeOnReceipt(context);
                                                        CashLayoutActivity.GetPrintQRCodeOnReceipt(mContext,CheckOutActivity.BillNo);

                                                    }else {

                                                        bitmap__ = null;
                                                        bitmap__ = Query.GetLogPrint();

                                                        Query.CheckEmulatorOrNot(mContext, sale_id, CheckOutActivity.BillNo, Constraints.SALES, Constraints.SALES,
                                                                bitmap__,null);

                                                        CheckOutActivity.str_percentage_value = "0";
                                                        CheckOutActivity.discount_amount = 0.0;
                                                    }


                                                }else {
                                                    Toast.makeText(mContext, Constraints.SALES + " Successfully.", Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(mContext, PaymentCashSuccesActivity.class);
                                                    mContext.startActivity(intent);
                                                }


                                                CashLayoutActivity.UpdateFunationfun(CheckOutActivity.BillNo);
                                            }
                                        })
                                        .setCancelButton(Constraints.No, new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sDialog) {
                                                sDialog.dismissWithAnimation();

                                                SaveBillPaymentFun();

                                                CashLayoutActivity.UpdateFunationfun(CheckOutActivity.BillNo);

                                                Toast.makeText(mContext, Constraints.SALES + " Successfully.", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(mContext, PaymentCashSuccesActivity.class);
                                                mContext.startActivity(intent);
                                            }
                                        });
                        syncDialog.show();
                        syncDialog.setCancelable(false);

                    }else {

                        SaveBillPaymentFun();


                        Toast.makeText(mContext, Constraints.SALES + " Successfully.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(mContext, PaymentCashSuccesActivity.class);
                        mContext.startActivity(intent);


                        CashLayoutActivity.UpdateFunationfun(CheckOutActivity.BillNo);
                    }



                }else if (mPaymentType.get(position).getRoundingActivate().equals("1")){

                    chkoutactivity_amount = chkoutactivity_amount + chkoutactivity_staticRound;

                    CashLayoutActivityCallFun(mContext,paymentName,paymentID,payment_remarks);

//                }else if (mPaymentType.get(position).getEzLink().equals("1")){
                }else if (mPaymentType.get(position).getEzLink() != null && mPaymentType.get(position).getEzLink().equals("1")){

                    //chkoutactivity_amount = chkoutactivity_amount + chkoutactivity_staticRound;

                    //CashLayoutActivityCallFun(mContext,paymentName,paymentID);

//                    Intent launchIntent = new Intent();
//                    launchIntent.setClassName(DeclarationConf.PACKAGE_NAME_DBS, DeclarationConf.CLASS_NAME_DBS);
//                    launchIntent.setFlags(0);
//                    //launchIntent.setFlags( Intent.FLAG_ACTIVITY_NO_HISTORY);
//                    Bundle bundleApp = new Bundle();
//
//                    //Ezlink
//                    bundleApp.putString("Request", sendSaleObject(String.format("%.2f", (cardamount * 100)), DeclarationConf.EVENT_EZLINK_SALE));
//                    launchIntent.putExtras(bundleApp);
//                    //new SendData1().execute(launchIntent, 6699, false);
//                    startActivityForResult(launchIntent, 6699);

                    cardPaymentID = Integer.parseInt(paymentID) ;

                    Intent intent = new Intent(mContext, CheckOutActivity.class);
                    intent.putExtra("BillNo",CheckOutActivity.BillNo);
                    intent.putExtra("Status","CardPayment");
                    intent.putExtra("Ezlink","Ezlink");
                    intent.putExtra("StatusSALES","");
                    mContext.startActivity(intent);

                }else {

                    CashLayoutActivityCallFun(mContext,paymentName,paymentID,payment_remarks);
                }
//                        CashLayoutActivity.UpdateFunationfun(CheckOutActivity.BillNo);
            }else {

                //Query.ErrorDialog(mContext,"Payment Type need to activate.");
                Query.SweetAlertWarning(mContext,"Y","Payment Type need to activate.", Constraints.OK,"","");
            }
        }else {

            //Query.ErrorDialog(mContext,"Cannot do payment.");
            Query.SweetAlertWarning(mContext,"Y","Cannot do payment.", Constraints.OK,"","");

        }
    }

//    private void paymentFun(Integer position,String finalChk_print_receipt_paper) {
//        PaymentIDCheckoutAdapter = Integer.parseInt(mPaymentType.get(position).getPaymentID());
//        paymentID = mPaymentType.get(position).getPaymentID();
//        paymentName = mPaymentType.get(position).getPaymentName();
//        String mpaymentypeStatus = mPaymentType.get(position).getSwitchSTATUS();
//        String mpaymentypeLink = mPaymentType.get(position).getLinktoPaymentApp();
//
//        final String chk_qr_code_on_receipt = Query.GetOptions(18);
////                holder.itemView.setEnabled(false);
////                CheckOutActivity.myrv.setEnabled(false);
////                String checkBillNo = Query.getBillNofromDetailsBillProduct(CheckOutActivity.BillNo);//check last bill have data or not
//        if (checkBillNo.length() > 0) {  // if have , can't z close , so return false
//
//            chkbilldiscount = CheckOutActivity.discount_amount;
//            chkoutactivity_amount = CheckOutActivity.amount;
//            chkoutactivity_staticRound = CheckOutActivity.staticRound;
//
//            if (mpaymentypeStatus.equals("1")){
//
//                Log.i("dsfdsf___","___mpaymentypeLink__"+mpaymentypeLink);
//                Log.i("dsfdsf___","_____"+mPaymentType.get(position).getEzLink());
//                if (mpaymentypeLink.equals("1")){
//                    Log.i("dsfdsf___","___mpaymentypeLink_______"+"1");
//
//                    cardPaymentID = Integer.parseInt(paymentID) ;
//
//                    Intent intent = new Intent(mContext, CheckOutActivity.class);
//                    intent.putExtra("BillNo",CheckOutActivity.BillNo);
//                    intent.putExtra("Status","CardPayment");
//                    intent.putExtra("Ezlink","");
//                    intent.putExtra("StatusSALES","");
//                    mContext.startActivity(intent);
//                    //((Activity)mContext).finish();
//
//
//                }else if (mPaymentType.get(position).getIntegratetoShoptima().equals("1")){
//
//                    Log.i("dsfdsf___","___mpaymentypeLink_______"+"2");
//
//                    qrval = DeclarationConf.QR_VOUCHER_REQUEST;
//                    ScannerTester.getInstance(EScannerType.REAR).scan(handler,10000);
//
//                }else if (mPaymentType.get(position).getFullAmount().equals("1")){
//
//                    Log.i("dsfdsf___","___mpaymentypeLink_______"+"3");
////                            getDetailsBillProduct(CheckOutActivity.BillNo);
//
////                                CashLayoutActivity.SaveBillPayment(CheckOutActivity.BillNo, sub_total, totalQty, amount, ItemDiscountAmount,
////                                        PaymentTypesCheckoutAdapter.paymentID, PaymentTypesCheckoutAdapter.paymentName,
////                                        "0", String.valueOf(amount), mContext,"cash","");
//
//
//
//
//                    if (finalChk_print_receipt_paper.equals("1")) {
//
//
//                        final SweetAlertDialog syncDialog =
//                                new SweetAlertDialog(mContext, SweetAlertDialog.WARNING_TYPE)
//                                        .setTitleText(Constraints.Print)
//                                        .setConfirmText(Constraints.YES)
//                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                                            @Override
//                                            public void onClick(SweetAlertDialog sDialog) {
//                                                sDialog.dismissWithAnimation();
//
//                                                SaveBillPaymentFun();
//
//                                                if (sale_id > 0) {
//
//                                                    Log.i("DSFchk_receipt_print_","cchk_qr_code_on_receipt___"+chk_qr_code_on_receipt);
//                                                    if (chk_qr_code_on_receipt.equals("1")) {
//                                                        //bitmap_qr_shoptima = Query.GetPrintQRCodeOnReceipt(context);
//                                                        //Query.GetPrintQRCodeOnReceipt(context);
//                                                        CashLayoutActivity.GetPrintQRCodeOnReceipt(mContext,CheckOutActivity.BillNo);
//
//                                                    }else {
//
//                                                        bitmap__ = null;
//                                                        bitmap__ = Query.GetLogPrint();
//
//                                                        Query.CheckEmulatorOrNot(mContext, sale_id, CheckOutActivity.BillNo, Constraints.SALES, Constraints.SALES,
//                                                                bitmap__,null);
//
//                                                        CheckOutActivity.str_percentage_value = "0";
//                                                        CheckOutActivity.discount_amount = 0.0;
//                                                    }
//
//
//                                                }else {
//                                                    Toast.makeText(mContext, Constraints.SALES + " Successfully.", Toast.LENGTH_SHORT).show();
//                                                    Intent intent = new Intent(mContext, PaymentCashSuccesActivity.class);
//                                                    mContext.startActivity(intent);
//                                                }
//
//
//                                                CashLayoutActivity.UpdateFunationfun(CheckOutActivity.BillNo);
//                                            }
//                                        })
//                                        .setCancelButton(Constraints.No, new SweetAlertDialog.OnSweetClickListener() {
//                                            @Override
//                                            public void onClick(SweetAlertDialog sDialog) {
//                                                sDialog.dismissWithAnimation();
//
//                                                SaveBillPaymentFun();
//
//                                                CashLayoutActivity.UpdateFunationfun(CheckOutActivity.BillNo);
//
//                                                Toast.makeText(mContext, Constraints.SALES + " Successfully.", Toast.LENGTH_SHORT).show();
//                                                Intent intent = new Intent(mContext, PaymentCashSuccesActivity.class);
//                                                mContext.startActivity(intent);
//                                            }
//                                        });
//                        syncDialog.show();
//                        syncDialog.setCancelable(false);
//
//                    }else {
//                        Log.i("RERETETE___","TRTR62___"+CheckOutActivity.CashValueArr);
//                        Log.i("RERETETE___","TRTR62___"+CheckOutActivity.CashValuePaymentIDArr);
//                        Log.i("RERETETE___","TRTR62___"+CheckOutActivity.CashValuePaymentNameArr);
//                        SaveBillPaymentFun();
//
//
//                        Toast.makeText(mContext, Constraints.SALES + " Successfully.", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(mContext, PaymentCashSuccesActivity.class);
//                        mContext.startActivity(intent);
//
//
//                        CashLayoutActivity.UpdateFunationfun(CheckOutActivity.BillNo);
//                    }
//
//
//
//                }else if (mPaymentType.get(position).getRoundingActivate().equals("1")){
//                    Log.i("dsfdsf___","___mpaymentypeLink_______"+"4");
//                    chkoutactivity_amount = chkoutactivity_amount + chkoutactivity_staticRound;
//
//                    CashLayoutActivityCallFun(mContext,paymentName,paymentID);
//
////                }else if (mPaymentType.get(position).getEzLink().equals("1")){
//                }else if (mPaymentType.get(position).getEzLink().equals("1")){
//                    Log.i("dsfdsf___","___mpaymentypeLink_______"+"5");
//                    Log.i("RERETETE___","TRTR62__dddddd_"+mPaymentType.get(position).getEzLink());
//                    //chkoutactivity_amount = chkoutactivity_amount + chkoutactivity_staticRound;
//
//                    //CashLayoutActivityCallFun(mContext,paymentName,paymentID);
//
////                    Intent launchIntent = new Intent();
////                    launchIntent.setClassName(DeclarationConf.PACKAGE_NAME_DBS, DeclarationConf.CLASS_NAME_DBS);
////                    launchIntent.setFlags(0);
////                    //launchIntent.setFlags( Intent.FLAG_ACTIVITY_NO_HISTORY);
////                    Bundle bundleApp = new Bundle();
////
////                    //Ezlink
////                    bundleApp.putString("Request", sendSaleObject(String.format("%.2f", (cardamount * 100)), DeclarationConf.EVENT_EZLINK_SALE));
////                    launchIntent.putExtras(bundleApp);
////                    //new SendData1().execute(launchIntent, 6699, false);
////                    startActivityForResult(launchIntent, 6699);
//
//                    cardPaymentID = Integer.parseInt(paymentID) ;
//
//                    Intent intent = new Intent(mContext, CheckOutActivity.class);
//                    intent.putExtra("BillNo",CheckOutActivity.BillNo);
//                    intent.putExtra("Status","CardPayment");
//                    intent.putExtra("Ezlink","Ezlink");
//                    intent.putExtra("StatusSALES","");
//                    mContext.startActivity(intent);
//
//                }else {
//
//                    Log.i("dsfdsf___","___mpaymentypeLink_______"+"6");
//                    Log.i("RERETETE___","TRTR62__dddddd_"+mPaymentType.get(position).getEzLink());
//                    Log.i("RERETETE___","TRTR62__dddddd_"+Integer.parseInt(mPaymentType.get(position).getEzLink()));
//                    CashLayoutActivityCallFun(mContext,paymentName,paymentID);
//                }
////                        CashLayoutActivity.UpdateFunationfun(CheckOutActivity.BillNo);
//            }else {
//
//                //Query.ErrorDialog(mContext,"Payment Type need to activate.");
//                Query.SweetAlertWarning(mContext,"Y","Payment Type need to activate.", Constraints.OK,"","");
//            }
//        }else {
//            Log.i("Dfd_ff__","chk___"+"seven");
//            //Query.ErrorDialog(mContext,"Cannot do payment.");
//            Query.SweetAlertWarning(mContext,"Y","Cannot do payment.", Constraints.OK,"","");
//
//        }
//    }

    private void SaveBillPaymentFun() {
        getDetailsBillProduct(CheckOutActivity.BillNo);

        CashLayoutActivity.SaveBillPayment(CheckOutActivity.BillNo, sub_total, totalQty, amount, ItemDiscountAmount,
                PaymentTypesCheckoutAdapter.paymentID, PaymentTypesCheckoutAdapter.paymentName,
                "0", String.valueOf(amount), mContext,"cash","");

        sale_id = Query.findLatestID("ID","Sales",false);
    }

    private void CashLayoutActivityCallFun(final Context mContext,final String paymentName,final String paymentID,String payment_remarks) {

        String checkingDoublePaymentType = "0";
        //for (int check = 0; check < CheckOutActivity.CashValuePaymentIDArr.size(); check ++) {
       if (CheckOutActivity.CashValuePaymentIDArr.contains(Integer.parseInt(paymentID))){
//            Log.i("testing___","SFD_d__"+String.valueOf(CheckOutActivity.CashValuePaymentIDArr.get(check)));
//            Log.i("testing___","SFD__dd_"+String.valueOf(paymentID));
//            if (String.valueOf(CheckOutActivity.CashValuePaymentIDArr.get(check)).equals(String.valueOf(paymentID))) {
                checkingDoublePaymentType = "1";
//            }else {
//                checkingDoublePaymentType = "0";
//            }
        }

        if (checkingDoublePaymentType.equals("0")){
            Log.i("payment_remarks___","payment_remarks000__"+payment_remarks);
            Intent i = new Intent(mContext, CashLayoutActivity.class);
            i.putExtra("Billtype",bill_type);
            i.putExtra("PaymentName",paymentName);
            i.putExtra("paymentID",paymentID);
            i.putExtra("Remarks",payment_remarks);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(i);
//            ((Activity)mContext).finish();
        }else {
            new SweetAlertDialog(mContext, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Multiple Payment Type Duplicate. \n Are you sure to continue ?")
                    .setConfirmText(Constraints.YES)
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismissWithAnimation();
                            Log.i("payment_remarks___","payment_remarks001__"+payment_remarks);
                            Intent i = new Intent(mContext, CashLayoutActivity.class);
                            i.putExtra("Billtype",bill_type);
                            i.putExtra("PaymentName",paymentName);
                            i.putExtra("paymentID",paymentID);
                            mContext.startActivity(i);
                            //((Activity)mContext).finish();
                        }
                    })
                    .setCancelButton(Constraints.No, new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismissWithAnimation();
                        }
                    })
                    .show();
        }
    }

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0:
                    //resultTv.setText(getText(R.string.scanner_result) + msg.obj.toString());
                    if (qrval == DeclarationConf.QR_REQUEST) {
                        qrval = 0;
                        Query.VolleyGetToken(mContext,DeclarationConf.QR_REQUEST, msg.obj.toString());

                    }
                    if (qrval == DeclarationConf.QR_VOUCHER_REQUEST) {
                        qrval = 0;

                        Query.VolleyGetToken(mContext,DeclarationConf.QR_VOUCHER_REQUEST, msg.obj.toString());

                    }
                    break;
                default:
                    break;
            }
        }

        ;
    };

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        bankName = Query.GetBankNameFun();

        if (!(data == null)) {
            String responseData;

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
                        Query.ResponseCodeSuccessFun(mContext,CheckOutActivity.BillNo,paymentID,paymentName,"");

                    }
                }
            }
            else if (bankName.toUpperCase().equals(DeclarationConf.HOST_TYPES_DBS.toUpperCase())) {
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
                        Query.ResponseCodeSuccessFun(mContext,CheckOutActivity.BillNo,paymentID,paymentName,"");

                    }
                }
            }
            else if (bankName.toUpperCase().equals(DeclarationConf.HOST_TYPES_BOC.toUpperCase())) {
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
                        Query.ResponseCodeSuccessFun(mContext,CheckOutActivity.BillNo,paymentID,paymentName,"");

                    }
                }
            }
            else if (bankName.toUpperCase().equals(DeclarationConf.HOST_TYPES_JERIPAY.toUpperCase())){

                //                {"uuid":"bfcd4105bde042afb86d7bb1f0fe67c9","status":"SUCCESS","acquirer":"Ocbc","acquirer_id":"OCBC","acquirer_payment_id":"000014","amount":20}
                if (data.hasExtra("transaction")) {
                    responseData = data.getStringExtra("transaction");

                    JSONObject res_data = null;
                    String response_code = "";
                    if (responseData != null) {
                        try {
                            res_data = new JSONObject(responseData);
                            //Log.i("sdsres_data", String.valueOf(res_data));
//                            {"uuid":"87a335fceb4e44cd8feb8e07a7c733f1",
//                                    "status":"SUCCESS",
//                                    "acquirer":"Ocbc",
//                                    "acquirer_id":"OCBC",
//                                    "acquirer_payment_id":"000022",
//                                    "amount":0.44}
                            response_code = res_data.getString("status");
                            //acquirer_id = res_data.getString("acquirer_id");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    if (response_code.toUpperCase().equals("SUCCESS")) {

                        Query.ResponseCodeSuccessFun(mContext,CheckOutActivity.BillNo,paymentID,paymentName,"");

                    }
                } else {
                    // Do something else
                }

            }else if (bankName.toUpperCase().equals(DeclarationConf.HOST_TYPES_GLOBAL_PAYMENT.toUpperCase())){
//                ITransAPI transAPI = TransAPIFactory.createTransAPI(CheckOutActivity.this);
//                SaleMsg.Response response = (SaleMsg.Response) transAPI.onResult(requestCode, resultCode, data);
//                //Log.i("ddd__requestCode", String.valueOf(requestCode));
//                //Log.i("ddd__response.getRspCode()", String.valueOf(response.getRspCode()));
//                //if (requestCode == Activity.RESULT_OK && response != null) {
//                if (requestCode == 100 && response != null) {
//                    if (response.getRspCode() == 0) {
//                        Log.i("__response", String.valueOf(response));
//                        String respMsg = response.getRspMsg();
//                        String merchantName = response.getMerchantName();
//                        String merchantId = response.getMerchantId();
//                        String appId = response.getAppId();
//                        String acuquirerName = response.getAcquirerName();
//                        String issuerName = response.getIssuerName();
//                        long batchNo = response.getBatchNo();
//                        long traceNo = response.getTraceNo();
//                        String amount = response.getAmount();
//                        String appCode = response.getAppCode();
//                        String cardType = response.getCardType();
//                        String refNo = response.getRefNo();
//                        String cardNo = response.getCardNo();
//                        Log.i("cardNo__",cardNo);
//                        String tipAmount = response.getTipAmount();
//                        String app = response.getApp();
//                        String aid = response.getAid();
//                        String tc = response.getTc();
//                        String tvr = response.getTvr();
//                        String tsi = response.getTsi();
//                        String atc = response.getAtc();
//                        String stt = response.getStt();
//                        String enterMode = response.getEnterMode();
//                        // String txnCurrCode = response.getTxnCurrCode();
//                        //String cardholderCode = response.getCardholderCode();
//
//                        String fxRate = response.getFxRate();
//                        String foreignAmt = response.getForeignAmt();
//
//
//                        Log.i("respMsg_",respMsg);
//                        Log.i("merchantName_",merchantName);
//                        Log.i("merchantId_",merchantId);
//                        Log.i("appId_",appId);
//                        Log.i("acuquirerName_",acuquirerName);
//                        Log.i("issuerName_",issuerName);
//                        Log.i("batchNo_", String.valueOf(batchNo));
//                        Log.i("traceNo_", String.valueOf(traceNo));
//                        Log.i("amount_",amount);
//                        Log.i("appCode_",appCode);
//                        Log.i("cardType_",cardType);
//                        Log.i("refNo_",refNo);
//                        Log.i("cardNo_",cardNo);
//                        Log.i("tipAmount_",tipAmount);
//                        Log.i("app_",app);
//                        Log.i("aid_",aid);
//                        Log.i("tc_",tc);
//                        Log.i("tvr_",tvr);
//                        Log.i("tsi_",tsi);
//                        Log.i("atc_",atc);
//                        Log.i("stt_",stt);
//                        Log.i("enterMode_",enterMode);
//                        // Log.i("txnCurrCode_",txnCurrCode);
//                        //Log.i("cardholderCode_",cardholderCode);
//                        //Log.i("fxRate_",fxRate);
//                        //Log.i("foreignAmt_",foreignAmt);
//
//                        //InsertPaymentIntoDB(_paymentID, _payname, _val_amount);
//                    } else {
//                        Log. d ("response", response.getRspMsg());
//                    }
//                } else {
//                    Log. d ("response", "transaction failed");
//                }
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

    private class SendData1 extends AsyncTask<Object, String, Object> {
        @Override
        protected void onPreExecute() {

        }

        //        protected void onPostExecute(Intent result) {
//            Log.i("resultsdsdsd", String.valueOf(result));
//        }
        @SuppressWarnings({"unchecked", "deprecation"})
        @Override
        protected Object doInBackground(Object... params) {
            Intent launchIntent = (Intent) params[0];
            ((Activity) mContext).startActivityForResult(launchIntent, 6699);
            return null;
        }

        //@Override
        protected void onPostExecute(String result) {
        }

    }

    private String sendSaleObject(String amount, String commandCode) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("transaction_amount", getConvertedAmount(amount));
            jsonObject.put("transaction_type", commandCode);
            jsonObject.put("command_identifier", "1");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        //processJsonRequest = jsonObject.toString();
        return jsonObject.toString();
    }

    public void globalPymentSaleRequest(long val_amount,String payment_card_type) {
        SaleMsg.Request request = new SaleMsg.Request();
        request.setAppId("com.pax.globalpay");
        request.setPackageName("com.pax.globalpay");
        request.setAmount(val_amount);
        request.setTipAmount(0);
        request.setPrint(true);
//        ITransAPI transAPI = TransAPIFactory.createTransAPI(CheckOutActivity.this);
//        boolean ret = transAPI.doTrans(request);
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

    private String getConvertedAmount(String amount) {

        double l = Double.parseDouble(amount);
        l = l / 100;
        return REAL_FORMATTER.format(l);

    }

    private void getDetailsBillProduct(String billNo) {
        String sql = " SELECT BillNo,(ProductQty),(ProductPrice),ProductName,(ItemDiscountAmount)," +
                "BillDateTime,ID,TaxID,TaxName,(TaxAmount),DiscountName,DiscountTypeName,DiscountValue FROM DetailsBillProduct " +
                " Where BillNo = '"+ billNo +"' ";
//                "   group by ProductID,OpenPriceStatus";
//        String sql = " SELECT BillNo,SUM(ProductQty),SUM(ProductPrice),ProductName,SUM(ItemDiscountAmount)," +
//                "BillDateTime,ID,TaxID,TaxName,SUM(TaxAmount),DiscountName,DiscountTypeName,DiscountValue FROM DetailsBillProduct " +
//                " Where BillNo = '"+ billNo +"' "+
//                "   group by ProductID,OpenPriceStatus";
        //Log.i("_sql__",sql);
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
                    //Double totalPrice = c.getInt(1) * (c.getDouble(2) - c.getDouble(4));
                    //Double totalPrice = (c.getDouble(2) - c.getDouble(4));
                    Double totalPrice = (c.getDouble(2) - ( c.getInt(1) * c.getDouble(4)));

                    //sub_total += c.getDouble(2) - c.getDouble(4);
                    //sub_total += c.getInt(1) * (c.getInt(2));
                    sub_total += totalPrice;
                    Log.i("Sdf__sub_total","subdddfdf_total_____"+sub_total);
//                    amount += c.getDouble(2);
                    amount += totalPrice;
                    totalQty += c.getInt(1);
                    //ItemDiscountAmount += c.getDouble(4);
                    ItemDiscountAmount += c.getInt(1) * c.getDouble(4);
                    Integer taxID = c.getInt(7);
                    sldTaxID.add(String.valueOf(taxID));
                    sldCTaxName.add(c.getString(8));
                    sldCTaxAmount.add(c.getString(9));
                    sldDiscountName.add(c.getString(10));
                    sldDiscountType.add(c.getString(11));
                    sldDiscountValue.add(c.getString(12));
                    Log.i("taxIDDD", String.valueOf(taxID));
                    String str_tax = "Select Type,Rate from Tax Where ID = " +taxID;
                    Log.i("taxIDDD_str_tax", String.valueOf(str_tax));
                    Cursor CursortaxObj = DBFunc.Query(str_tax,true);
                    if (CursortaxObj != null){
                        if (CursortaxObj.moveToNext()) {
                            sldCTaxType.add(CursortaxObj.getString(0));
                            sldCTaxRate.add(CursortaxObj.getString(1));
                        }
                        CursortaxObj.close();
                    }else {
                        sldCTaxType.add("0");
                        sldCTaxRate.add("0");
                    }
                }
            }
            c.close();
        }
    }
}

