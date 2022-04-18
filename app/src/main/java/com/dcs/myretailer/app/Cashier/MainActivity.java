package com.dcs.myretailer.app.Cashier;

import static org.apache.commons.net.util.Base64.encodeBase64String;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.SearchManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.InputType;
import android.util.Log;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dcs.myretailer.app.API.APIInterface;
import com.dcs.myretailer.app.Allocator;
import com.dcs.myretailer.app.Activity.CheckOutActivity;
import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.LalamoveAPI;
import com.dcs.myretailer.app.MainActivityViewModel;
import com.dcs.myretailer.app.MainPagePagerAdapter;
import com.dcs.myretailer.app.Model.DeviceData;
import com.dcs.myretailer.app.OnlineOrderClosedBillListMainPageFragment;
import com.dcs.myretailer.app.OnlineOrderListMainPageFragment;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.Activity.RemarkMainActivity;
import com.dcs.myretailer.app.Report.ReportActivity;
import com.dcs.myretailer.app.Activity.ScanActivity;
import com.dcs.myretailer.app.ScreenSize.MainActivityScreenSize;
import com.dcs.myretailer.app.Activity.SettingActivity;
import com.dcs.myretailer.app.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.pax.dal.IDAL;
import com.pax.neptunelite.api.NeptuneLiteUser;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IllegalFormatException;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

//import org.apache.commons.codec.digest.DigestUtils;

//import com.pax.neptunelite.api.NeptuneLiteUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {
    private static MainActivity xcontext;
    APIInterface apiInterface;
    public static int BillID = 0;
    public static String BalNo = "0";
    public static String CashierFloatAmount = "0";
    public static String strbillNo = "0";
    public static String tbl_name = "0";
    public static String inv_table = "0";
    public static String remarksScanVal = "";
    public static String licensekeyVal = "";
//    public static Button btnCheckout;
    Integer view_page_str_search_control = -1;
    private static SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;
    //public static String str_search = "0";
//    public static String category_checking = "0";
    public static String category_status = "0";
    public static String str_tab_fragment_2 = "0";
    public static String str_tab_fragment_3 = "0";
    public static String str_tab_fragment_5 = "0";
    public static String str_tab_fragment_4 = "0";
    public static String str_chk_map_layout = "0";
    public static String balanceMode = "0";
    public static Integer defaultMapID = -1;
    public static Integer receiptCount = 0;
    public static Integer totalItems  = 0;
    public static Double totalAmount = 0.0;
    public static String status_setting = "0";
    public static String homebuttonpress = "0";
//    Handler mHandler;
//    Handler mHandler_online_order;
    public static String name = "";

    public static MainPagePagerAdapter adapter;
//    public static ViewPager viewPager;
    //private String url = "http://www.mocky.io/v2/597c41390f0000d002f4dbd1";
//    private String url = "";
    public static String SECRET_str = "0";
    public static String time_str = "0";
    private static final byte[] salt = { (byte) 0xA4, (byte) 0x0B, (byte) 0xC8,
            (byte) 0x34, (byte) 0xD6, (byte) 0x95, (byte) 0xF3, (byte) 0x13 };
    Mac sha256_HMAC = null;
    String SIGNATURE = "0";
    private static IDAL dal;
    private static Context appContext;
    private static Context appth_is;
    public static String status = "1";
    public static String St = "0";
    public static String tab_2_ = "0";
    public static String CategorySt = "0";
    static BitmapDrawable drawable = null;
    static Bitmap bitmap;
    static RequestQueue queue;
    static RequestQueue queue_qrcode;
    String Str_BalmodeCheck = "0";
    public static String str_quick_product = "0";
    public static String str_quick_add_product = "0";
    public static String str_edit_product_sheetfragment = "0";
    private static final String FRAGMENTS_TAG = "android:support:fragments";
    public static int billFontSize = 14;
    public static int roundtype = 1;
    public static int decimalpoint = 2;
    public static String shoptima_url = "";
    public static String shoptima_user_id = "";
    public static String shoptima_password = "";
    public static String shoptima_machine_id = "";
    public static String shoptima_mall_code = "";
    public static String qr_code_shoptima_url = "";
    public static String qr_code_shoptima_user_id = "";
    public static String qr_code_shoptima_password = "";
    public static String qr_code_shoptima_machine_id = "";
    public static String qr_code_shoptima_mall_code = "";
    public static String qr_code_shoptima_brand = "";
    public static String qr_code_shoptima_outlet = "";
    public static boolean noPrintCondimentQty = false;
    public static boolean lastItemSelected = false;
    // static boolean holdBalanceOnlySendKP = false;
    public static boolean paymentOnlyKP = false;
    public static boolean hideNaviBar = false;
    public static boolean referFunction = false;
    public static boolean referCompulsory = false;
    public static boolean referInfo1print = false;
    public static boolean referInfo2print = false;
    public static boolean referInfo3print = false;
    public static boolean userovertake = false;
    public static boolean noprintunpaid = false;
    public static boolean printerreceipt = false;
    public static boolean cashdrawer = false;
    public static boolean customerdisplay = false;
    public static boolean barcodescanner = false;
    public static String referInfo1Text = "Info 1";
    public static String referInfo2Text = "Info 2";
    public static String referInfo3Text = "Info 3";
    public static Boolean chk_pos_qr_code_ = false;
    public static Integer product_ID = 0;
    public static String product_Name = "0";
    static String syncUrl = "";

    private static final String DATA_STRING_TAG = "com.motorolasolutions.emdk.datawedge.data_string";

    private IntentFilter fltIntentAction ;

    private BroadcastReceiver _newItemsReceiver;

    private int iCount = 0;

    public static ActivityMainBinding binding = null;

    private Disposable disposable;
    MainActivityViewModel model = null;

    AppCompatActivity activity = null;

    //                    FragmentManager manager = ((AppCompatActivity) appContext).getSupportFragmentManager();
    static FragmentManager manager = null;
//    public static void updateMediaButtons() {
//        Log.i("updateMediaButtons__","updateMediaButtons");
//        updateMainFun(appContext);
//    }
//
//    private static void updateMainFun(Context appContext) {
//        CheckBarCodeStatusFun();
//    }
    Intent getIntent = null;
    Bundle bundleName = null;
    static String chkBarcode;
    @SuppressLint("StaticFieldLeak")
//    @RequiresApi(api = Build.VERSION_CODES.N)


//    public static IntentIntegrator qrScan;

//    protected void onCreate(Bundle savedInstanceState) {
//    protected void onCreate(@Nullable Bundle savedInstanceState) {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        if (savedInstanceState != null) {edit_text_barcode
//            // remove saved fragment, will new fragment in mPagerAdapter
//            savedInstanceState.remove(FRAGMENTS_TAG);
//        }
        super.onCreate(savedInstanceState);


        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

//        binding.floatingActionButton.setOnClickListener(this);

//        binding.container.setOnTouchListener(this);
        binding.container.setOnClickListener(this);

        model = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        // = (AppCompatActivity) appContext.getApplicationContext();

        //                    FragmentManager manager = ((AppCompatActivity) appContext).getSupportFragmentManager();
        //manager = activity.getSupportFragmentManager();
        manager = getSupportFragmentManager();

        appContext = getApplicationContext();
        dal = getDal(appContext);

        appth_is = this;
        chkBarcode = Query.GetOptions(16);

        ScreenDisplayFun(binding,appContext);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        appContext = getApplicationContext();
        dal = getDal(appContext);
//
        CheckBarCodeStatusFun();
//
        updateMainFun();
//
        binding.btnCheckout.setOnClickListener(this);

    }


    private void updateMainFun() {
        getIntent = getIntent();
        bundleName = getIntent.getExtras();
        String str_product = "Product";

        if(bundleName!=null) {
            name = (String) bundleName.get("name");
            Log.i("name___","name_"+name);

            if (!(name.equals("TabFragmentPercentage")) && !(name.equals("TabFragmentAmount")) ) {

                if (!(name.equals("CheckoutActivity"))){
                    CreateBillOrPendingBills();
                }
            }

            if (name.equals("BillListMainPageFragment")) {
                str_tab_fragment_3 = "1";
                binding.pager.setCurrentItem(2);
            } else if (name.equals("MapLayoutMainPageFragment")) {

                MapLayoutMainPageFragment mapLayoutMainPageFragment = new MapLayoutMainPageFragment();
                //FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.mainlayout, mapLayoutMainPageFragment, "Test Fragment");
                transaction.commit();

            } else if (name.equals("SampleActivity")){
                    Cursor C_Bill = Query.GetDataFromBill();
                    if (C_Bill != null) {
                        while (C_Bill.moveToNext()) {
                            CashierFloatAmount = C_Bill.getString(0);
                        }
                        C_Bill.close();
                    }
                }
                else if (name.split("____")[0].equals("Category")){
                    category_status = "1";
                }
                else if (name.equals("CheckoutActivity")){
                    St = "1";
                }
//            else if (name.equals("OnlineOrderBill")){
//                PagerAdapter.OnlineOrderBillStatusOn = "1";
//                MainActivity.viewPager.setCurrentItem(3);
//            }
//            else if (name.equals("PrintingActivity")){
//                Log.i("Dfd___","Dfdf___"+"PrintingActivity");
//
//                Log.i("TESTING____","Testing___"+"TESSSSSS3");
//                Query.CreateNewBillAndDetailsBillProduct();
////                //str_tab_fragment_2 = "0";
//            }
                else if (name.equals("CategoryMainPageFragment")){
                    str_tab_fragment_2 = "1";
//                //str_tab_fragment_2 = "0";
                }
//                else if (name.equals("BillListMainPageFragment")){
//                    str_tab_fragment_3 = "1";
//                    binding.pager.setCurrentItem(2);
//                }
                else if (name.equals("TabFragmentPercentage")){
//                RecyclerViewAdapter.item_diss =  TabFragmentPercentage.str_percentage;
//                Double dis_amt = (Double.valueOf(TabFragmentPercentage.str_percentage_value) * Double.valueOf(RecyclerViewAdapter.item_pricee)) / 100d;
//                String chk_hide_img = Query.GetOptions(20);
//                if (chk_hide_img.equals("1")){
//                    if (EditProductSheetFragment.str_percentage_value != null && EditProductSheetFragment.str_percentage_value.length() > 0) {
//                        RecyclerViewNoImageAdapter.item_diss = EditProductSheetFragment.str_percentage;
//                        Double dis_amt = (Double.valueOf(EditProductSheetFragment.str_percentage_value) * Double.valueOf(RecyclerViewNoImageAdapter.item_pricee)) / 100d;
//                        Double new_amt = Double.valueOf(RecyclerViewNoImageAdapter.item_pricee) - dis_amt;
//                        RecyclerViewNoImageAdapter.old_pricee = String.valueOf(new_amt);
//                        RecyclerViewNoImageAdapter.item_diss_amt = String.valueOf(dis_amt);
//                    }
//
//                }else {
                    if (EditProductSheetFragment.str_percentage_value != null && EditProductSheetFragment.str_percentage_value.length() > 0) {
                        RecyclerViewAdapter.item_diss = EditProductSheetFragment.str_percentage;
                        Double dis_amt = (Double.valueOf(EditProductSheetFragment.str_percentage_value) * Double.valueOf(RecyclerViewAdapter.item_pricee)) / 100d;
                        Double new_amt = Double.valueOf(RecyclerViewAdapter.item_pricee) - dis_amt;
                        RecyclerViewAdapter.old_pricee = String.valueOf(new_amt);
                        RecyclerViewAdapter.item_diss_amt = String.valueOf(dis_amt);
                    }
//                }
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                intent.putExtra("name", "");
                    //FragmentManager manager = ((AppCompatActivity) appContext).getSupportFragmentManager();
                    EditProductSheetFragment editProductSheetFragment = new EditProductSheetFragment();
                    editProductSheetFragment.setCancelable(false);

                    editProductSheetFragment.show(manager, editProductSheetFragment.getTag());
                    //name = "";
                }else if (name.equals("TabFragmentAmount")){
                    //RecyclerViewAdapter.item_diss =  EditProductSheetFragment.str_percentage;
                    if (EditProductSheetFragment.str_value != null && EditProductSheetFragment.str_value.length() > 0) {
//                    String chk_hide_img = Query.GetOptions(20);
//                    if (chk_hide_img.equals("1")) {
//                        RecyclerViewNoImageAdapter.item_diss = EditProductSheetFragment.str_value;
//                        Double dis_amt = Double.valueOf(EditProductSheetFragment.str_value);
//                        Double new_amt = Double.valueOf(RecyclerViewNoImageAdapter.item_pricee) - dis_amt;
//                        RecyclerViewNoImageAdapter.old_pricee = String.valueOf(new_amt);
//                        RecyclerViewNoImageAdapter.item_diss_amt = String.valueOf(dis_amt);
//                    } else {
                        RecyclerViewAdapter.item_diss = EditProductSheetFragment.str_value;
                        Double dis_amt = Double.valueOf(EditProductSheetFragment.str_value);
                        Double new_amt = Double.valueOf(RecyclerViewAdapter.item_pricee) - dis_amt;
                        RecyclerViewAdapter.old_pricee = String.valueOf(new_amt);
                        RecyclerViewAdapter.item_diss_amt = String.valueOf(dis_amt);
//                    }
                    }
                    //FragmentManager manager = ((AppCompatActivity) appContext).getSupportFragmentManager();
                    EditProductSheetFragment editProductSheetFragment = new EditProductSheetFragment();
                    editProductSheetFragment.setCancelable(false);

                    editProductSheetFragment.show(manager, editProductSheetFragment.getTag());
                    //name = "";
                }
                else if (name.equals("EditProductSheetFragment")){
                    //PagerAdapter.status_on = "1";
                }else if (name.equals("OnlineOrderListMainPageFragment")){
                    str_tab_fragment_5 = "1";
                }else if (name.equals("RemarkScanActivity")){
                    RecyclerViewAdapter.remarkScanVar = "RemarkScanActivity";
                    RecyclerViewAdapter.remarkScanValue = remarksScanVal;

                }

            if (name.equals("ManageBillFragment")){
                St = "1";
                strbillNo = CheckOutActivity.BillNo;
                showHeader(strbillNo);

            }else{

//                if (!(name.equals("TabFragmentPercentage")) && !(name.equals("TabFragmentAmount")) ) {
//
//                    if (!(name.equals("CheckoutActivity"))){
//                            CreateBillOrPendingBills();
//                    }
//                }

                strbillNo = Query.findBillNoByBillID(BillID);

                showHeader(strbillNo);
            }

        }

        getBillByBillNo();
//
        try {

            BalNo = Query.GetBalNo(strbillNo);
        } catch (Exception e){
            Log.e("BalNo_ex","e_BalNo"+e.getMessage());
        }

        //showHeader();
        showHeader(strbillNo);

        Query.DeleteProductQty(strbillNo);

        Str_BalmodeCheck = ChkBalanceMode();
        if (Integer.parseInt(Str_BalmodeCheck) == 2) {
            //ShowMapLayout(defaultMapID);
            str_tab_fragment_4 = "1";
            str_chk_map_layout = "1";
        }
        tabLayoutFun(binding);
        tabSelectedFun(binding);
//
        binding.navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        binding.navView.getMenu().findItem(R.id.navigation_cashier).setChecked(true);

        ScreenDisplayFun(binding,appContext);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        //MainActivity.binding.container.setAlpha(1);
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //MainActivity.binding.container.setAlpha(1);
        return super.onTouchEvent(event);
    }

    public static final String PHONE_STATE_PERMISSION = Manifest.permission.READ_PHONE_STATE;

    public static boolean checkPermission(String permission, Activity activity) {
        return ContextCompat.checkSelfPermission(activity, permission) ==
                PackageManager.PERMISSION_GRANTED;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        disposable.dispose();
    }

    private Observable<String> gettstObservable() {
        Observable<String> dataObservable =  Observable.just("A", "B", "C", "D");
        return dataObservable;
    }

    private void ScreenDisplayFun(ActivityMainBinding binding, Context appContext) {

         String MODEL = android.os.Build.MODEL; // N910
        String BOARD = android.os.Build.BOARD; // msm8909
        String BRAND = android.os.Build.BRAND; // qcom
        String DEVICE = android.os.Build.DEVICE; // msm8909
        String DISPLAY = android.os.Build.DISPLAY; // NewLand_N910-48f87c9f7a
        String PRODUCT = android.os.Build.PRODUCT; // msm8909
        String terminal_type = "";

        if (BRAND.equals(Constraints.iMin)){
            BRAND = Constraints.ALPS;
        }
        if ((DISPLAY.split("_")[0]).toUpperCase().equals(Constraints.NewLand)){
            terminal_type = "NewLand";

        }else if ((DISPLAY.split("_")[0]).equals(Constraints.A930)){
            terminal_type = "PAX";
        }else if (MODEL.equals(Constraints.E600M)){
            terminal_type = Constraints.PAX_E600M;
        }else if (BRAND.equals(Constraints.LANDI)){
//            DISPLAY_QKQ1.200407.002 release-keys
//            DEVICE_DX8000
//            BRAND_LANDI
//            BOARD_QC_Reference_Phone
//            PRODUCT_DX8000
            terminal_type = Constraints.INGENICO;
            //terminal_model = modelVal;
        }else if (BRAND.equals(Constraints.ALPS) || BRAND.equals(Constraints.iMin)){
//            DISPLAY_QKQ1.200407.002 release-keys
//            DEVICE_DX8000
//            BRAND_LANDI
//            BOARD_QC_Reference_Phone
//            PRODUCT_DX8000
            terminal_type = Constraints.IMIN;
            //terminal_model = modelVal;
        }
        Constraints.TERMINAL_TYPE = terminal_type;
        Log.i("dsfsd___","___"+terminal_type);
        Log.i("dsfsd___","___"+Constraints.TERMINAL_TYPE);

        PackageInfo pInfo = null;
        int vercode = 0;
        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);

            vercode = pInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        Query.SaveDeviceData(new DeviceData(0,MODEL,BOARD,BRAND,DEVICE,DISPLAY,PRODUCT,terminal_type.toUpperCase(),String.valueOf(vercode),licensekeyVal));

        //ScreenSize
        new MainActivityScreenSize(getContext(),binding);
    }

    //Getting the scan results
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //if qrcode has nothing in it
            if (result.getContents() == null) {

            } else {
                Log.i("Df___","__dfdfdf______"+result.getContents());
                //qrCode.setText(result.getContents());

                //JSON_DATA_WEB_CALL();

                //if qr contains data

                // Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
//
    String barcode = "";
    String TAG = "MainActivity";
    Integer ccccount = 0;
    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                System.currentTimeMillis(), DBFunc.PurifyString("Chk_barcode_" +
                        binding.edittextBarcode.getText())+"-chkBarcode-"+chkBarcode);
        if (binding.edittextBarcode.getText().toString().length() > 0) {
            //Log.i("___MMMM","SDDD4___"+str_search);
            if (chkBarcode.equals("1")) {
                binding.edittextBarcode.setVisibility(View.VISIBLE);
                binding.edittextBarcode.requestFocus();
                binding.edittextBarcode.setInputType(InputType.TYPE_NULL);

                barcode = binding.edittextBarcode.getText().toString();


                barCodeScannerDeviceFun(barcode);


            } else {
                DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                        System.currentTimeMillis(), DBFunc.PurifyString("Err-MainActivity-chkBarcodeNeedtoON-" +  chkBarcode));

                binding.edittextBarcode.setVisibility(View.GONE);
            }

        }
        return super.dispatchKeyEvent(e);

    }

    public static void barCodeScannerDeviceFun(String barCode) {
        String sql = "SELECT Name,Price,Code,Image,ID,ProductCategoryID,ProductCategoryName " +
                "FROM PLU WHERE Code = '"+barCode+"'";

        //Toast.makeText(appContext, "qty_"+sql, Toast.LENGTH_SHORT).show();

        Cursor c = DBFunc.Query(sql, true);
        String scanCode = "0";
        String scanPLUame = "0";
        String scanPLUPrice = "0";
        Integer scanPLUID = 0;
        String scanProductCategoryID = "0";
        String scanProductCategoryName = "0";
        if (c != null){
            if (c.moveToNext()){
                scanPLUame = c.getString(0);
                scanPLUPrice = c.getString(1);
                scanCode = c.getString(2);
                scanPLUID = c.getInt(4);
                scanProductCategoryID = c.getString(5);
                scanProductCategoryName = c.getString(6);


            }
            c.close();
        }
        DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                System.currentTimeMillis(), DBFunc.PurifyString("Err-MainActivity-barCodeScannerDeviceFun-barCode-" +
                        barCode+"-"+scanCode));

        if (barCode.equals(scanCode)) {

            Integer qty = Query.GetQtyByBillNoAndProductId(MainActivity.strbillNo,scanPLUID);

            Query.saveDetailsBillProduct_AssignValue("main",MainActivity.BillID, MainActivity.strbillNo, "OFF",
                    qty, scanPLUame, scanPLUPrice, "0", String.valueOf(scanPLUID), "0", "0",
                    Constraints.SALES, "0", "0", "0", "0", "0",
                    0,"0","");

            Query.SaveBillPLU(MainActivity.BillID, MainActivity.strbillNo,
                    String.valueOf(scanPLUID), scanPLUame, String.valueOf(scanPLUID), 1, "", "");


            St = "1";


            binding.edittextBarcode.setText("");

            Intent intent= new Intent(appContext, MainActivity.class);
            appContext.startActivity(intent);

        }else {
        }
            //RecyclerViewAdapter.St = "1";
        //Log.i("DFDF___ccccount__","ccccount___"+ccccount);
    }

    public static void tabSelectedFun(ActivityMainBinding mbinding) {
//        adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
//        viewPager.setAdapter(adapter);

        //tabLayout.setupWithViewPager(viewPager);
        //PagerAdapter.view_pager_position = viewPager.getCurrentItem();
        if (str_tab_fragment_2.equals("1")){
            //            Log.i("str_tab_fragment_2",str_tab_fragment_2);
            mbinding.pager.setCurrentItem(1);
             str_tab_fragment_2 = "0";
        }

        if (str_tab_fragment_3.equals("1")){
            //            Log.i("str_tab_fragment_2",str_tab_fragment_2);
            mbinding.pager.setCurrentItem(2);
            str_tab_fragment_3 = "0";
        }


//        Log.i("DFDF__","DSD"+String.valueOf(ReportActivity.previousReport).length());
//        if (String.valueOf(ReportActivity.previousReport).length() >  4 ) {
//            viewPager.setCurrentItem(3);
//        }
        mbinding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            //        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                try {
//                    viewPager.setCurrentItem(tab.getPosition());
                    if (mbinding.pager.getCurrentItem() > -1) {
                        mbinding.pager.setCurrentItem(tab.getPosition());
                    }
                }catch (IllegalFormatException e){
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

    private void showHeader(String strbillNo) {

        if (strbillNo.equals("0")){
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
            finish();
        }else {

            try {
                if (strbillNo.length() > 0) {
                    if (strbillNo.equals("0")) {
                        Integer billID_str = Query.findLatestID("BillNo", "Bill", false);
                        strbillNo = Query.findBillNoByBillID(billID_str);
                    }
                    if (strbillNo.equals("00000000")) {
                        Integer billID_str = Query.findLatestID("BillNo", "Bill", false);
                        strbillNo = Query.findBillNoByBillID(billID_str);
                        //strbillNo = billNo(billID_str);
                    }
                    BalNo = Query.GetBalNo(strbillNo);
                    if (!tbl_name.equals("0")) {
                        setTitle(Constraints.Bill + " #" + strbillNo);
                        getSupportActionBar().setSubtitle(" ( " + tbl_name + " ) ");
                        inv_table = tbl_name;
                    } else {
                        if (!BalNo.equals("0")) {
                            //setTitle(ENUM.Bill+" #"+strbillNo+"\n ( "+ BalNo +" ) ");
                            setTitle(Constraints.Bill + " #" + strbillNo);
                            getSupportActionBar().setSubtitle(" ( " + BalNo + " ) ");
                            inv_table = BalNo;
                        } else {
                            setTitle(Constraints.Bill + " #" + strbillNo);
                            getSupportActionBar().setSubtitle("");
                            inv_table = BalNo;
                        }
                    }
                }
            }catch (NullPointerException e){

            }
        }
    }

    public static void clearViewPagerAdapter(FragmentManager manager) {
        Class<? extends FragmentManager> aClass = manager.getClass();
        try {
            Field f = aClass.getDeclaredField("mAdded");
            f.setAccessible(true);
            ArrayList<Fragment> list = (ArrayList) f.get(manager);
            if (list != null && !list.isEmpty()) {
                list.clear();
            }

            f = aClass.getDeclaredField("mActive");
            f.setAccessible(true);
            SparseArray<Fragment> array = (SparseArray) f.get(manager);
            if (array != null && array.size() > 0) {
                array.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String ChkBalanceMode() {
        String Str_BalmodeCheck = "0";
        Cursor c = Query.getPossy();
        if (c != null) {
            if (c.moveToNext()) {

                billFontSize = c.getInt(7);

                if (billFontSize < 10) {
                    billFontSize = 10;
                }
                if (billFontSize > 40) {
                    billFontSize = 40;
                }

                Str_BalmodeCheck = c.getString(5);
                defaultMapID = c.getInt(9);
                receiptCount = c.getInt(2);

                if (!c.isNull(13)) {
                    roundtype = c.getInt(13);
                }
                if (!c.isNull(14)) {
                    shoptima_url = c.getString(14);
                }

                if (!c.isNull(15)) {
                    shoptima_user_id = c.getString(15);
                }

                if (!c.isNull(16)) {
                    shoptima_password = c.getString(16);
                }

                if (!c.isNull(17)) {
                    shoptima_machine_id = c.getString(17);
                }

                if (!c.isNull(18)) {
                    shoptima_mall_code = c.getString(18);
                }

                if (!c.isNull(19)) {
                    qr_code_shoptima_url = c.getString(19);
                }

                if (!c.isNull(20)) {
                    qr_code_shoptima_user_id = c.getString(20);
                }

                if (!c.isNull(21)) {
                    qr_code_shoptima_password = c.getString(21);
                }

                if (!c.isNull(22)) {
                    qr_code_shoptima_machine_id = c.getString(22);
                }

                if (!c.isNull(23)) {
                    qr_code_shoptima_mall_code = c.getString(23);
                }

                if (!c.isNull(24)) {
                    qr_code_shoptima_brand = c.getString(24);
                }

                if (!c.isNull(25)) {
                    qr_code_shoptima_outlet = c.getString(25);
                }

                String option = c.getString(8);
                if (option.length() > 0) {
                    if (option.charAt(0) == '1') {//automatic selecting last item
                        lastItemSelected = true;
                    }
                }
                if (option.length() > 1) {
                    if (option.charAt(1) == '1') {//don't print condiment item on receipt
                        noPrintCondimentQty = true;
                    }
                }
                if (option.length() > 2) {
                    if (option.charAt(2) == '1') {//Kitchen Printer will only print if receipt settle/paid
                        paymentOnlyKP = true;
                    }
                }
                if (option.length() > 3) {
                    if (option.charAt(3) == '1') {//automatic hide Android Bottom Navigation Bar(back, home, etc buttons)
                        hideNaviBar = true;
                    }
                }

                if (option.length() > 6) {
                    if (option.charAt(6) == '1') {//enable refer function
                        referFunction = true;
                    }
                }

                if (option.length() > 7) {
                    if (option.charAt(7) == '1') {//refer is compulsory on each receipt before settlement/payment
                        referCompulsory = true;
                    }
                }
                if (option.length() > 8) {
                    if (option.charAt(8) == '1') {//print refer info 1 on receipt
                        referInfo1print = true;
                    }
                }
                if (option.length() > 9) {
                    if (option.charAt(9) == '1') {//print refer info 2 on receipt
                        referInfo2print = true;
                    }
                }
                if (option.length() > 10) {
                    if (option.charAt(10) == '1') {//print refer info 3 on receipt
                        referInfo3print = true;
                    }
                }
                if (option.length() > 11) {
                    if (option.charAt(11) == '1') {//take over user if the receipt is open by another user
                        userovertake = true;
                    }
                }
                if (option.length() > 12) {
                    if (option.charAt(12) == '1') {//don't print unpaid bill
                        noprintunpaid = true;
                    }
                }
                if (option.length() > 13) {
                    if (option.charAt(13) == '1') {//don't print unpaid bill
                        printerreceipt = true;
                    }
                }
                if (option.length() > 14) {
                    if (option.charAt(14) == '1') {//don't print unpaid bill
                        cashdrawer = true;
                    }
                }
                if (option.length() > 15) {
                    if (option.charAt(15) == '1') {//don't print unpaid bill
                        customerdisplay = true;
                    }
                }
                if (option.length() > 16) {
                    if (option.charAt(16) == '1') {//don't print unpaid bill
                        barcodescanner = true;
                    }
                }
                if (option.length() > 18) {
                    if (option.charAt(18) == '1') {
                        chk_pos_qr_code_ = true;
                    }else{
                        chk_pos_qr_code_ = false;
                    }
                }
            }else {
                Str_BalmodeCheck = "0";
                defaultMapID = 0;
            }
            c.close();
        }else {
            Str_BalmodeCheck = "0";
            defaultMapID = 0;
        }
        return Str_BalmodeCheck;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_cashier:
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                    finish();
                    return true;
                case R.id.navigation_report:
                    Intent report_setting = new Intent(getApplicationContext(), ReportActivity.class);
                    startActivity(report_setting);
                    finish();
                    return true;
                case R.id.navigation_setting:
                    status_setting = "1";
                    St = "0";
                    Intent btn_dialog = new Intent(getApplicationContext(), SettingActivity.class);
                    startActivity(btn_dialog);
                    finish();
//                    Intent btn_dialog = new Intent(getApplicationContext(), MajorActivity.class);
//                    startActivity(btn_dialog);
//                    finish();
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.floatingActionButton:
//                Intent iHelloSQLCipherActivity = new Intent(this, HelloSQLCipherActivity.class);
//                startActivity(iHelloSQLCipherActivity);
//                break;
//            case R.id.container:
//
//                Log.i("jererererer__main","onClick");
//                binding.container.setAlpha(1);
//                break;
            case R.id.btn_checkout:
                Query.UpdateNaNItemBillDiscount();
//                Integer totitm = 0;
//                Cursor C_DetailsBillProduct = Query.SearchBillProductByBillNo(strbillNo, Constraints.NoGroupBy);
//                if (C_DetailsBillProduct != null) {
//                    totitm = 0;
//                    while (C_DetailsBillProduct.moveToNext()) {
//                        if (!C_DetailsBillProduct.isNull(0)) {
//                            totitm = C_DetailsBillProduct.getInt(0);
//                        }
//                    }
//                    C_DetailsBillProduct.close();
//                }
//                if (totitm > 0.0) {
                if (totalItems > 0) {

                    CheckOutActivity.CashValuePaymentNameArr.clear();
                    CheckOutActivity.CashValuePaymentIDArr.clear();
                    CheckOutActivity.CashValueArr.clear();
                    Intent ckActivy = new Intent(getApplicationContext(), CheckOutActivity.class);
                    ckActivy.putExtra(Constraints.BillNo, strbillNo);
                    ckActivy.putExtra(Constraints.Status, "Main");
                    ckActivy.putExtra(Constraints.StatusSALES,"");
                    startActivity(ckActivy);
                    finish();
                }else{
//                    new AlertDialog.Builder(this, R.style.AlertDialogStyle)
//                            .setMessage(ENUM.ChooseProduct)
//                            .setCancelable(false)
//                            .setNegativeButton(ENUM.OK, null)
//                            .show();

                        new SweetAlertDialog(MainActivity.this, SweetAlertDialog.WARNING_TYPE)
                            //.setTitleText("Cancelled Bill")
                            .setContentText(Constraints.ChooseProduct)
                            .setConfirmText(Constraints.OK)
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismissWithAnimation();
                                }
                            })
                            .show();
                }
            break;
        }
    }

    public void createHMAC(){
        time_str = String.valueOf(System.currentTimeMillis()) ;

        try {
            String secret = "secret";
            String message = "Message";

            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secret_key);

            //String hash = Base64.encodeBase64String(sha256_HMAC.doFinal(message.getBytes()));
            String hash = encodeBase64String(sha256_HMAC.doFinal(message.getBytes()));
            Log.i("Hash_value",hash);
            //1593488638643
            //qnR8UCqJggD55PohusaBNviGoOJ67HC6Btry4qXLVZc=
            SECRET_str = hash;
        }
        catch (Exception e){
            Log.i("Hash_valueError",e.getMessage());
        }
        String method_GET_str = "GET";
        String method_POST_str = "POST";
        String path_str = LalamoveAPI.lalamoveapi_url;
        String body_str = "";
//        const body = JSON.stringify({...}); // => the whole body for '/v2/quotations'

        String rawSignature = "'"+time_str+"\r\n"+method_POST_str+"\r\n"+path_str+"\r\n\r\n"+body_str+"'";
        // => '1546222219293\r\nPOST\r\n/v2/quotations\r\n\r\n{\n \"scheduleAt\": \"2018-12-31T14:30:00.00Z\",\n \"serviceType\": \"MOTORCYCLE\",\n \"requesterContact\": { \"name\": \"Peter Pan\", \"phone\": \"232\" },\n \"stops\": [\n {\n \"location\": { \"lat\": \"-6.255431000000001\", \"lng\": \"106.60114290000001\" },\n \"addresses\": {\n \"en_ID\": {\n \"displayString\":\n \"Jl. Perum Dasana Indah No.SD 3/ 17-18, RT.3/RW.1, Bojong Nangka, Klp. Dua, Tangerang, Banten 15810, Indonesia\",\n \"country\": \"ID\"\n }\n }\n },\n {\n \"location\": { \"lat\": \"-6.404722800000001\", \"lng\": \"106.81902130000003\" },\n \"addresses\": {\n \"en_ID\": {\n \"displayString\": \"Jl. Kartini, Ruko No. 1E, Depok, Pancoran MAS, Kota Depok, Jawa Barat 16431, Indonesia\",\n \"country\": \"ID\"\n }\n }\n }\n ],\n \"deliveries\": [\n {\n \"toStop\": 1,\n \"toContact\": {\n \"name\": \"mm\",\n \"phone\": \"9999999\"\n }\n }\n ]\n}\n'


        //String SIGNATURE = CryptoJS.HmacSHA256(rawSignature, SECRET_str).toString();
// => '5133946c6a0ba25932cc18fa3aa1b5c3dfa2c7f99de0f8599b28c2da88ed9d42'

        String API_KEY = "914c9e52e6414d9494e299708d176a41";
        //String TOKEN = API_KEY+":"+time_str+":"+SIGNATURE;

        //String Authorization = "hmac "+TOKEN;
        String X_LLM_Country = "SG";
//        String X_Request_ID =  <NONCE>
        //objectJson();
    }

    public void DeliveryInfoJsonDatatoLalava(){
        String url = LalamoveAPI.lalamoveapi_url;
        // Instantiate the RequestQueue.
        //RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest sr = new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("Sdfresponse",response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("SdfE",String.valueOf(error));
                Log.i("SdfE",String.valueOf(error.getMessage()));
            }
        }){
            @Override
            protected Map<String,String> getParams(){
//                {
//                    "toStop": 1,
//                        "toContact": <Contact>
//                        "remarks": "ORDER#94\r\n1. Tshirt จำนวน 1\r\n2. Hoodie จำนวน 1\r\n"
//                }
                Map<String,String> params = new HashMap<String, String>();
                params.put("toStop","userAccount.getUsername()");
                params.put("toContact","userAccount.getPassword()");
                params.put("remarks","userAccount.getPassword()");
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }
        };
        //Log.i("Dsfsdf,", String.valueOf(sr));
        queue.add(sr);
    }
//    public void LalaMoveDelivery(){
//        RequestQueue queue = Volley.newRequestQueue(this);
////        JSONObject parameters = new JSONObject();
////        try {
////            parameters.put("key", "value");
////            parameters.put("key", "value");
////        } catch (Exception e) {
////        }
//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, LalamoveAPI.lalamoveapi_url, LalamoveAPI.getDeliveryInfo(),new Response.Listener<JSONObject>() {
//            //JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, "http://http://49.128.60.174:44343", LalamoveAPI.getDeliveryInfo(),new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                Log.i("onResponse", response.toString());
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e("onErrorResponse", error.toString());
//            }
//        }) {
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String> headers = new HashMap<String, String>();
//                // Basic Authentication
//                String SECRET = "MCwCAQACBQDDym2lAgMBAAECBDHB";
//                String time = String.valueOf(System.currentTimeMillis()) ;
//
//                String method = "POST";
//                String path = LalamoveAPI.lalamoveapi_url;
//                String body = LalamoveAPI.getDeliveryInfo().toString();
//
//                String rawSignature = time + "\r\n"+ method +"\r\n"+ path +"\r\n\r\n"+body+"";
//                try {
//                    sha256_HMAC = Mac.getInstance("HmacSHA256");
//                    //SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
//                    Charset charset = Charset.forName("UTF-8");
//
//                    byte[] SECRETbyte = SECRET.getBytes(charset);
//                    byte[] rawSignaturebyte = rawSignature.getBytes(charset);
//                    SecretKeySpec secret_key = new SecretKeySpec(SECRETbyte, "HmacSHA256");
//                    sha256_HMAC.init(secret_key);
//
//                    //SIGNATURE =  Hex.encodeHexString(sha256_HMAC.doFinal(rawSignaturebyte));
//                    //SIGNATURE =  Hex.encodeHexString(rawSignaturebyte);
//                    String s = new String(Hex.encodeHex(DigestUtils.md5(rawSignaturebyte)));
//                    SIGNATURE =  s;
//                } catch (NoSuchAlgorithmException e) {
//                    e.printStackTrace();
//                    //Log.i("ERRRRRRR_one",e.getMessage());
//                } catch (InvalidKeyException e) {
//                    e.printStackTrace();
//                    //Log.i("ERRRRRRR_two",e.getMessage());
//                }
//                String API_KEY = "914c9e52e6414d9494e299708d176a41";
//                String TOKEN = API_KEY+":"+ time_str +":"+SIGNATURE;
//                //Log.i("ERRRRRRR_three",TOKEN);
//                headers.put("Authorization", "hmac " + TOKEN);
//                headers.put("X-LLM-Country", "SG" );
//                headers.put("X-Request-ID", "211b9d85-a2cc-476f-8675-b61ec923cc27" );
//                headers.put("Content-Type", "application/json" );
//                //Log.i("Sf_header_", String.valueOf(headers));
//                //Log.i("Sf_deliveryInfo_", String.valueOf(LalamoveAPI.getDeliveryInfo()));
//                return headers;
//            }
//        };
//        //Log.i("_requesttt", String.valueOf(request));
//        queue.add(request);
//    }
    public void SendJsonDatatoLalava(){
        //String spreadsheetID = "1";
        //String url = LalamoveAPI.lalamoveapi_url + spreadsheetID + "/exec";
        String url = LalamoveAPI.lalamoveapi_url;
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest sr = new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("name","userAccount.getUsername()");
                params.put("comment","userAccount.getPassword()");
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }
        };
        queue.add(sr);
    }
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if ((keyCode == KeyEvent.KEYCODE_HOME)) {
//            Log.i("DFDAFDAF","KEYCODE_HOME");
//
//            homebuttonpress = "1";
//            //showDialog("'HOME'");
//            return false;
//        }
//        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
//            Log.i("DFDAFDAF","KEYCODE_BACK");
//            //showDialog("'BACK'");
//            return true;
//        }
//        if ((keyCode == KeyEvent.KEYCODE_MENU)) {
//            //System.out.println("KEYCODE_MENU");
//            //showDialog("'MENU'");
//            return true;
//        }
//        return false;
//    }

    void showDialog(String the_key){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("You have pressed the " + the_key + " button. Would you like to exit the app?")
                .setCancelable(true)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        finish();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.setTitle("CoderzHeaven.");
        alert.show();
    }

//    @Override
//    public void onAttachedToWindow() {
//        super.onAttachedToWindow();
//        this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD);
//    }

    public void onUserLeaveHint() { // this only executes when Home is selected.
        // do stuff
        super.onUserLeaveHint();
        Log.i("MyActivity_","HOMEEEEEEEEE");
    }

//    @Override
//    public void onAttachedToWindow() {
//        super.onAttachedToWindow();
//        this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD);
//    }

//    private final Runnable m_Runnable = new Runnable() {
//        public void run() {
//            context.mHandler.removeCallbacks(m_Runnable);
//            context.mHandler.removeCallbacksAndMessages(null);
//
//            context.mHandler.postDelayed(m_Runnable, 500);
//
////            str_search.length() > 0||
//            if (St.equals("1") || str_quick_product.equals("1")|| str_quick_add_product.equals("1")||
//                    str_edit_product_sheetfragment.equals("1")) {
//
//                ProductMainPageFragment.status_on = "1";
//                ProductMainPageFragment.St = "1";
//                RecyclerViewAdapter.St = "1";
//                getBillByBillNo();
//
//                Log.i("DFDF_____","DFDFDF____1"+"one");
//                showHeader(strbillNo);
//                Log.i("DFDF_____","DFDFDF____2"+"one");
//                tabLayoutFun();
//                Log.i("DFDF_____","DFDFDF____3"+"one");
//                tabSelectedFun();
//                Log.i("DFDF_____","DFDFDF____4"+"one");
//
//                Cursor CPossys = Query.GetURLAndCodeFromPossys();
//                if (CPossys != null) {
//                    while (CPossys.moveToNext()) {
//                        syncUrl = CPossys.getString(2);
//                    }
//                    CPossys.close();
//                }
//
////                if (syncUrl.length() > 10) {
////
////                    SyncActivity.passwordResendSalesDialog(MainActivity.this, "", 0, 0, 0,
////                            0, 0, 0, "FailResync");
////                }
//
//                //edittext_barcode.requestFocus();
//
//                St = "0";
//                str_quick_product = "0";
//                str_quick_add_product = "0";
//                str_edit_product_sheetfragment = "0";
//
////                AddTaxConfigurationActivity.mHandler.removeCallbacks(AddTaxConfigurationActivity.m_Runnable);
//                //AddTaxConfigurationActivity.mHandler.removeCallbacksAndMessages(null);
//            }
//        }
//    };

    private static void tabLayoutFun(ActivityMainBinding mbinding) {
       // try {
        mbinding.tabLayout.removeAllTabs();
        mbinding.tabLayout.addTab(mbinding.tabLayout.newTab().setText(Constraints.Products));
        mbinding.tabLayout.addTab(mbinding.tabLayout.newTab().setText(Constraints.Categories));
        mbinding.tabLayout.addTab(mbinding.tabLayout.newTab().setText(Constraints.Bill));

            String OnlineStatus = Query.GetOptions(19);
            if (OnlineStatus.equals("1")) {
                mbinding.tabLayout.addTab(binding.tabLayout.newTab().setText(Constraints.OnlineOrder));
                mbinding.tabLayout.addTab(binding.tabLayout.newTab().setText(Constraints.OnlineOrderBill));

                mbinding.tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
            }else {
//                tabLayout.removeTab(tabLayout.newTab().setText(ENUM.OnlineOrder));
//                tabLayout.removeTab(tabLayout.newTab().setText(ENUM.OnlineOrderBill));
                mbinding.tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
            }
            //tabLayout.addTab(tabLayout.newTab().setText("Layout"));
        //tabLayout.addTab(tabLayout.newTab().setText("Promotion"));
            //tabLayout.setTabTextColors(getResources().getColor(R.color.mine_shaft),getResources().getColor(R.color.mine_shaft));
        mbinding.tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
//            tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        mbinding.tabLayout.setTabTextColors(Color.parseColor("#a9aaad"), Color.parseColor("#000000"));
//            tabLayout.setSelectedTabIndicatorHeight(2);
//            tabLayout.setSelectedTabIndicatorColor(Color.RED);
            //adapter = new MainPagePagerAdapter(getSupportFragmentManager(), mbinding.tabLayout.getTabCount());
            adapter = new MainPagePagerAdapter(manager, mbinding.tabLayout.getTabCount());
            //adapter = new PagerAdapter(getSupportFragmentManager());

        mbinding.pager.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        mbinding.pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mbinding.tabLayout));
            //view_pager_position = viewPager.getCurrentItem();
            //Log.i("DFDF______________", String.valueOf(view_pager_position));
//            adapter.addFragment(new ProductMainPageFragment(), "Product",0);
//            adapter.addFragment(new CategoryMainPageFragment(), "Category", 1);
//            adapter.addFragment(new BillListMainPageFragment(), "Bill", 2);
//            adapter.addFragment(new OnlineOrderListMainPageFragment(), "OnlineOrder", 3);
//            adapter.addFragment(new OnlineOrderClosedBillListMainPageFragment(), "OrderOrderBill", 4);
//            adapter.addFragment(new MapLayoutMainPageFragment(), "Layout", 4);

            //viewPager.setOffscreenPageLimit(3);
            //viewPager.setAdapter(adapter);

//        }catch (IllegalFormatException e){
//            Log.i("EEEE",e.getMessage());
//        }
    }

//    @Override
//    protected void onResume() {
//        appContext = getApplicationContext();
//        updateMainFun();
//        CheckBarCodeStatusFun();
//        super.onResume();
//
//    }

//    @Override
//    protected void onRestart() {
//
//        CheckBarCodeStatusFun();
//        super.onRestart();
//    }

    public static void getBillByBillNo() {
        if (!MainActivity.strbillNo.equals("0")) {
            Cursor C_DetailsBillProduct = Query.SearchBillProductByBillNo(MainActivity.strbillNo, Constraints.GroupBy);
            if (C_DetailsBillProduct != null) {
                totalItems = 0;
                totalAmount = 0.0;
                while (C_DetailsBillProduct.moveToNext()) {
//                if (!C_DetailsBillProduct.isNull(0)) {
//                    if (C_DetailsBillProduct.getInt(2) != -1){
                    totalItems += C_DetailsBillProduct.getInt(0);
//                        totalAmount += C_DetailsBillProduct.getInt(0) *
//                                (C_DetailsBillProduct.getDouble(1) - C_DetailsBillProduct.getDouble(3));
                    //totalAmount += (C_DetailsBillProduct.getDouble(1) - C_DetailsBillProduct.getDouble(3));
                    totalAmount += (C_DetailsBillProduct.getDouble(1) - (C_DetailsBillProduct.getInt(0) * C_DetailsBillProduct.getDouble(3)));
//                    }
//                }
                }
                C_DetailsBillProduct.close();
            }

            String str_product = "Product";
            if (totalItems > 0) {
                str_product = "Products";
            } else {
                str_product = "Product";
            }

            binding.btnCheckout.setText(Html.fromHtml("<b>" + "CHECKOUT&nbsp;&nbsp;&nbsp;&nbsp;" + "</b>" +
                    "<small>" + totalItems + " " + str_product + " " + "</small>" +
                    "<b>" + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$" + String.format("%.2f", totalAmount) + "</b>"));

        }
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            onBackPressed();
//        }
//        return super.onKeyDown(keyCode, event);
//    }
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
//        if (category_checking.equals("0")) {

//            DialogFun(ENUM.Exit);

        final SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText(Constraints.Exit)
                .setConfirmText(Constraints.YES)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        //sDialog.dismissWithAnimation();
                        ActivityCompat.finishAffinity(MainActivity.this);
//                        RemarkMainActivity.loginSuccess = "0";
                        RemarkMainActivity.userid = "";
                        RemarkMainActivity.userpassword = "";
                    }
                })
                .setCancelButton(Constraints.No, new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                    }
                });
        pDialog.show();
        pDialog.setCancelable(false);

//        }

    }
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//    }

    private void DialogFun(String str) {
        try {

            new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText(str)
                    .setConfirmText(Constraints.YES)
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            //sDialog.dismissWithAnimation();
                            ActivityCompat.finishAffinity(MainActivity.this);
                        }
                    })
                    .setCancelButton(Constraints.No, new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismissWithAnimation();
                        }
                    })
                    .show();
        }catch (OutOfMemoryError e) {

        }
    }

//    private void CreateBillOrPendingBills() {
////        Cursor c = DBFunc.Query("SELECT BillNo FROM Bill order by CloseDateTime DESC ", false);
//        //String sql = "SELECT BillList.BillNo,BillList.STATUS FROM Bill " +
//        String sql = "SELECT Bill.BillNo,BillList.STATUS FROM Bill " +
//                "inner join BillList on BillList.BillID = Bill.BillNo " +
//                "where Bill.CloseDateTime IS NULL AND BillList.STATUS = 'PENDING'  order by BillList.BillNo DESC LIMIT 1";
//        Log.i("DFd___","bill_id1__"+"__"+sql);
//        Cursor c = DBFunc.Query(sql, false);
//        if (c != null) {
//            if (c.getCount() == 0) {
//                Query.CreateNewBillAndDetailsBillProduct();
//                c = DBFunc.GetBillNoFromBill();
//
//            } else {
//                c = DBFunc.GetBillNoFromBill();
//                Integer bill_id = 0;
//                if (c != null) {
//                    while (c.moveToNext()) {
//                    //if (c.moveToNext()) {
////                        if (!c.isNull(0)) {
//                            bill_id = c.getInt(0);
//
//                            Log.i("DFd___","bill_id__"+bill_id+"__"+c.getString(1));
////                        }
//                    }
//                    c.close();
//                }
//                if (bill_id > 0) {
//                    Integer lstBillID = Query.findLatestID("BillNo","Bill",false);
//                    String strbillNo = Query.findBillNoByBillID(lstBillID);
//                    Query.SaveDetailsBillProduct(strbillNo,"OFF",-1,"0","0",
//                            0,"0","-1","0","0",
//                            "0","0",ENUM.SALES,lstBillID,"0","","",
//                            "","","","",0.00,"","0");
//                } else {
//                    Query.CreateNewBillAndDetailsBillProduct();
//                }
//                c = DBFunc.GetBillNoFromBill();
//
//            }
//            if (c != null) {
//                if (c.moveToNext()) {
//                    if (!c.isNull(0)) {
//
//                        Log.i("StepFirst2_","BillNo__"+BillID);
//                        BillID = c.getInt(0);
//                    }
//                }
//                c.close();
//            }
//        }
//    }
    private void CreateBillOrPendingBills() {

        String sql = Query.GetSQLForBillPendingQty();

        Log.i("name_mccssss__","name___"+sql);

        Cursor c = DBFunc.Query(sql, false);
        if (c != null) {
            Log.i("sql__SaveBill_","sql__c.getCount()__"+c.getCount());
            if (c.getCount() == 0) {
                Query.CreateNewBillAndDetailsBillProduct();
                c = DBFunc.GetBillNoFromBill();

            } else {

                Log.i("sql__SaveBill_","sql__celse.getCount()__"+c.getCount());
                c = DBFunc.GetBillNoFromBill();
                Integer bill_id = 0;
                if (c != null) {
                    while (c.moveToNext()) {
                        //if (c.moveToNext()) {
                        if (!c.isNull(0)) {
                            bill_id = c.getInt(0);
                        }
                    }
                    c.close();
                }
                Log.i("sql__SaveBill_","sql_bill_id_"+bill_id);
                if (bill_id > 0) {
                    Integer lstBillID = Query.findLatestID("BillNo","Bill",false);
                    String strbillNo = Query.findBillNoByBillID(lstBillID);
                    Query.SaveDetailsBillProduct(strbillNo,"OFF",-1,"0","0",
                            "0","-1","0","0",
                            "0","0", Constraints.SALES,lstBillID,"0","","",
                            "","","","",0.00,"",
                            "0",0,"");
                } else {
                    Query.CreateNewBillAndDetailsBillProduct();
                }
                c = DBFunc.GetBillNoFromBill();

            }
            if (c != null) {
                //while (c.moveToNext()) {
                if (c.moveToNext()) {
                    if (!c.isNull(0)) {
                        BillID = c.getInt(0);
                    }
                }
                c.close();
            }
        }
    }

//    private final class LongOperation extends AsyncTask<Void, Void, String> {
//        Context context;
//        public LongOperation(Context applicationContext) {
//            context = applicationContext;
//        }
//
//        @Override
//        protected String doInBackground(Void... params) {
//            for (int i = 0; i < 5; i++) {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    // We were cancelled; stop sleeping!
//                }
//            }
//            return "Executed";
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            //Room Database
//
//            AppExecutors.getInstance().diskIO().execute(
//                    new Runnable() {
//                        @Override
//                        public void run() {
//                            MasterDB masterDB = MasterDB.getInstance(context);
//
//                            PLUKPrinterModel pm = new PLUKPrinterModel(
//                                    0,1,2
//                            );
//                            masterDB.plukPrinterDao().SavePLUKPrinter(pm);
//
//                            OpenDiscountDatabase openDisDB = OpenDiscountDatabase.getInstance(context);
//                            OpenDiscountModel openModel = new OpenDiscountModel(
//                                    0, UUID.randomUUID().toString(),
//                                    "fifteen",
//                                    "50",
//                                    "Percentage%",
//                                    "12:12:30.33"
//                            );
//                            OpenDiscountModel openModel1 = new OpenDiscountModel(
//                                    0, UUID.randomUUID().toString(),
//                                    "ten",
//                                    "10",
//                                    "Dollar",
//                                    "12:15:30.33"
//                            );
//                            openDisDB.openDiscountDao().SaveOpenDiscount(openModel);
//                            openDisDB.openDiscountDao().SaveOpenDiscount(openModel1);
//
//                            List<OpenDiscountModel> getdismodel = openDisDB.openDiscountDao().GetOpenDiscount();
//                            Log.i("Testinggetdismodel_","getdismodel"+getdismodel.size());
//                            for (int i = 0 ; i < getdismodel.size() ; i++) {
//                                OpenDiscountModel aa = getdismodel.get(i);
//                                Log.i("Testinggetdismodel_","success__"+aa);
//                            }
//                        }
//                    }
//            );
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_actions, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) MainActivity.this.getSystemService(Context.SEARCH_SERVICE);

        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(MainActivity.this.getComponentName()));
            queryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String newText) {
                    binding.edittextBarcode.setVisibility(View.GONE);
                    binding.edittextBarcode.setText("");
                   // str_search = newText;

                        if (binding.pager.getCurrentItem() == 0){
                            ProductMainPageFragment.St = "1";
                            ProductMainPageFragment.str_newText = newText;

                            ProductMainPageFragment.RunAuto();
                        } else if (binding.pager.getCurrentItem() == 1){
//                            CategoryMainPageFragment.St = "1";
                            CategoryMainPageFragment.str_newText = newText;
                            CategoryMainPageFragment.MainPageCategoryRefreshFun();
                        } else if (binding.pager.getCurrentItem() == 2){
                            BillListMainPageFragment.St = "1";
                            BillListMainPageFragment.str_newText = newText;
                            BillListMainPageFragment.updateBillListFun();
                        } else if (binding.pager.getCurrentItem() == 3){
                            //OnlineOrderListMainPageFragment.St = "1";
                            OnlineOrderListMainPageFragment.str_newText = newText;
                        } else if (binding.pager.getCurrentItem() == 4){
                            //OnlineOrderClosedBillListMainPageFragment.St = "1";
                            OnlineOrderClosedBillListMainPageFragment.str_newText = newText;
                        }

                    return true;
                }
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return true;
                }
            };
            searchView.setOnQueryTextListener(queryTextListener);
            searchView.setOnCloseListener(new SearchView.OnCloseListener() {
                @Override
                public boolean onClose() {
//                    Toast t = Toast.makeText(MainActivity.this, "close", Toast.LENGTH_SHORT);
//                    t.show();
                    ClearSearchManagerValue();
                    return false;
                }
            });
            searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean newViewFocus) {
                    if (!newViewFocus) {
                        //Collapse the action item.
                        searchItem.collapseActionView();
                        //Clear the filter/search query.
                        CheckBarCodeStatusFun();
                    }
                }
            });

        }
        return super.onCreateOptionsMenu(menu);
    }

    public static void CheckBarCodeStatusFun(){
        if (chkBarcode.equals("1")){
            binding.edittextBarcode.setVisibility(View.VISIBLE);
            binding.edittextBarcode.requestFocus();
            binding.edittextBarcode.setInputType(InputType.TYPE_NULL);
        }else {
            binding.edittextBarcode.setVisibility(View.GONE);
        }
        //edittext_barcode.setKeyListener(null);
//        edittext_barcode.requestFocus();
//        edittext_barcode.setActivated(true);
//        edittext_barcode.setPressed(false);
//        edittext_barcode.setFocusable(false);

//        edittext_barcode.requestFocus();
//        edittext_barcode.setInputType(InputType.TYPE_NULL);
//        edittext_barcode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//
//                if(edittext_barcode.hasFocus()){
//
//                    //et1.setCursorVisible(true);
//                    edittext_barcode.setActivated(true);
//                    edittext_barcode.setPressed(false);
//                    edittext_barcode.setFocusable(false);
//
//                }
//            }
//        });
    }

    public static void ClearSearchManagerValue(){

        ProductMainPageFragment.str_newText = "";
        BillListMainPageFragment.str_newText = "";
        OnlineOrderListMainPageFragment.str_newText = "";
        OnlineOrderClosedBillListMainPageFragment.str_newText = "";
        searchView.setInputType(0);
        Log.i("Dsfs___","Dfd_dddd__"+searchView.getInputType());
        //str_search = "";

        CheckBarCodeStatusFun();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add) {
            AddSheetFragment addSheetFragment = new AddSheetFragment();
            addSheetFragment.show(getSupportFragmentManager(), addSheetFragment.getTag());
            return true;
        }else if (id == R.id.action_scan) {
            Intent intent_scan = new Intent(getApplicationContext(), ScanActivity.class);
//            intent_scan.putExtra("scan_value","search");
            startActivity(intent_scan);
            finish();
            return true;
        }else if (id == R.id.action_search) {
            return false;

        }
        searchView.setOnQueryTextListener(queryTextListener);
//        if (id == android.R.id.home) {
//            edittext_barcode.requestFocus();
//            //hideSoftKeyboard(this);
//            // finish(), NavUtils.navigateUpFromSameTask(activity) or something like that
//        }
        return super.onOptionsItemSelected(item);
    }

    public static MainActivity getContext() {
        return xcontext;
    }

    public static IDAL getDal(Context context) {
        if (dal == null) {
            try {
                long start = System.currentTimeMillis();
                //dal = NeptuneLiteUser.getInstance().getDal(appContext);
                dal = NeptuneLiteUser.getInstance().getDal(context);
                //Log.i("Test", "get dal cost:" + (System.currentTimeMillis() - start) + " ms");
            } catch (Exception e) {
                e.printStackTrace();
                //Toast.makeText(appContext, "error occurred,DAL is null.", Toast.LENGTH_LONG).show();
               //Toast.makeText(appContext, "error occurred,DAL is null.", Toast.LENGTH_LONG).show();
            }
        }
        return dal;
    }
}
