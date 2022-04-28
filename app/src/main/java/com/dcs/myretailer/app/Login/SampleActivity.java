//package com.dcs.myretailer.app.Login;
//
//import android.Manifest;
//import android.app.Activity;
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.pm.PackageInfo;
//import android.content.pm.PackageManager;
//import android.database.Cursor;
//import android.graphics.PorterDuff;
//import android.os.AsyncTask;
//import android.os.Build;
//import android.os.Bundle;
//import android.os.Environment;
//import android.os.Handler;
//import android.provider.MediaStore;
//import android.util.Log;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.Window;
//import android.view.WindowManager;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.RequiresApi;
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//import androidx.databinding.DataBindingUtil;
//
//import com.dcs.myretailer.app.Allocator;
//import com.dcs.myretailer.app.Cashier.DeclarationConf;
//import com.dcs.myretailer.app.Cashier.MainActivity;
//import com.dcs.myretailer.app.DaggerIngenicoComponent;
//import com.dcs.myretailer.app.Database.DBFunc;
//import com.dcs.myretailer.app.DialogBox;
//import com.dcs.myretailer.app.ENUM.Constraints;
//import com.dcs.myretailer.app.IngenicoModule;
//import com.dcs.myretailer.app.Model.DeviceData;
//import com.dcs.myretailer.app.Query.Query;
//import com.dcs.myretailer.app.R;
//import com.dcs.myretailer.app.Activity.SettingActivity;
//import com.dcs.myretailer.app.databinding.ActivitySampleBinding;
//import com.imin.printerlib.IminPrintUtils;
//import com.karumi.dexter.Dexter;
//import com.karumi.dexter.MultiplePermissionsReport;
//import com.karumi.dexter.PermissionToken;
//import com.karumi.dexter.listener.DexterError;
//import com.karumi.dexter.listener.PermissionDeniedResponse;
//import com.karumi.dexter.listener.PermissionGrantedResponse;
//import com.karumi.dexter.listener.PermissionRequest;
//import com.karumi.dexter.listener.PermissionRequestErrorListener;
//import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
//import com.karumi.dexter.listener.single.PermissionListener;
//import com.newland.me.ConnUtils;
//import com.newland.me.DeviceManager;
//import com.newland.mtype.ConnectionCloseEvent;
//import com.newland.mtype.ExModuleType;
//import com.newland.mtype.event.DeviceEventListener;
//import com.newland.mtype.module.common.externalGuestDisplay.ExternalGuestDisplay;
//import com.newland.mtypex.nseries3.NS3ConnParams;
//
//import java.io.File;
//import java.nio.charset.Charset;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.SortedMap;
//
//@RequiresApi(api = Build.VERSION_CODES.N)
//public class SampleActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,View.OnClickListener{
//    public static final String TAG = "PinLockView";
//    private static final int MY_CAMERA_REQUEST_CODE = 100;
//    private static final ArrayList<String> typename = new ArrayList<String>();
//    private static final ArrayList<String> typepin = new ArrayList<String>();
//    ArrayList<Integer> pin_arr = new ArrayList<Integer>();
//    ArrayList<Integer> pin_name = new ArrayList<Integer>();
//    ArrayList<Integer> pin_id = new ArrayList<Integer>();
//    static DeviceManager deviceManager = null;
//    public static ActivitySampleBinding binding = null;
//    static Cursor config_values = null;
//    public static String NAME = "0";
//    public static String PIN = "0";
//    static String terminalTypeVal = "0";
//    Handler mHandler;
//    static String check = "0";
//    public static IminPrintUtils mIminPrintUtils;
//    public static Context context = null;
//    public static String loginname = "";
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//
//        binding = DataBindingUtil.setContentView(this,R.layout.activity_sample);
//
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//
//        context = this;
//
//        PermissionRequestFun();
//
//        requestStoragePermission();
//
//        updateSampleFun(this);
//
//        binding.spinner.setOnItemSelectedListener(this);
//        binding.imgLoginName.setOnClickListener(this);
//        binding.btnLogin.setOnClickListener(this);
//
//
//
//
//
//    }
//
////    private void BatteryFun() {
//////        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//////            Intent intent = new Intent();
//////            String packageName = getPackageName();
//////            PowerManager pm = (PowerManager) getSystemService(POWER_SERVICE);
//////            if (!pm.isIgnoringBatteryOptimizations(packageName)) {
//////                intent.setAction(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
//////                intent.setData(Uri.parse("package:" + packageName));
//////                startActivity(intent);
//////            }
//////        }
////    }
//
//    @Override
//    protected void onResume() {
//
//        //Intent intent = new Intent(this, MainActivity.class);
//        //intent.putExtra("name", "SampleActivity");
//        //startActivity(intent);
//        //finish();
//
//        context = this;
//        PinLockView.mPin = "";
//        updateSampleFun(this);
//        super.onResume();
//    }
//
////    @Override
////    protected void onRestart() {
////        Log.i("onreusme","tes___"+"onRestart");
////        context = this;
////        PinLockView.mPin = "";
////        updateSampleFun(this);
////        super.onRestart();
////    }
//
//    private void updateSampleFun(SampleActivity context) {
//        SaveDeviceData(context);
//
//        terminalTypeVal = Query.GetDeviceData(Constraints.TERMINAL_TYPE);
//
//        CustomerDisplayFun(binding);
//
//
//        if (terminalTypeVal.equals(Constraints.INGENICO)) {
//            DaggerIngenicoComponent.builder()
//                    .ingenicoModule(new IngenicoModule(context))
//                    .build();
//        }
//
//        DatabaseBackUpFun(context);
//
//        SaveDataDefault(context);
//
//        Query.SavePermissionGroup(Constraints.ADMIN);
//
//        mIminPrintUtils = IminPrintUtils.getInstance(context);
//        byte[] bytes = new byte[2];
//        bytes[0]= 27;
//        bytes[1]= 64;
//
//        mIminPrintUtils.printerByte(bytes);
//
//        Cursor Cursor_Staff = Query.getStaffData();
//        if (Cursor_Staff != null){
//            typename.clear();
//            typepin.clear();
//            while (Cursor_Staff.moveToNext()){
//                typename.add(Cursor_Staff.getString(0));
//                typepin.add(Cursor_Staff.getString(1));
//            }
//            Cursor_Staff.close();
//        }
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
//                R.layout.spinner_list,typename);
//
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        binding.spinner.setAdapter(adapter);
//        binding.spinner.getBackground().setColorFilter(context.getResources().getColor(R.color.colorBg), PorterDuff.Mode.SRC_ATOP);
//
//
//
//        binding.pinLockView.attachIndicatorDots(binding.indicatorDots);
//        binding.pinLockView.setPinLockListener(mPinLockListener);
//        binding.pinLockView.setPinLength(4);
//        binding.indicatorDots.setIndicatorType(IndicatorDots.IndicatorType.FILL_WITH_ANIMATION);
//
//        binding.profileName.setText(Constraints.DEFAULTNAME);
//        binding.profileName.setTextSize(16);
//
//        binding.pinNo.setText(Constraints.PINNO);
//        binding.pinNo.setTextSize(16);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//            binding.profileName.setLineHeight(24);
//        }
//
////        new AsyncTaskupdateSampleFun(sampleActivity,binding).execute();
//    }
//
//    public static class AsyncTaskupdateSampleFun extends AsyncTask<Object, ImageView, Void> {
//        Context mcontext = null;
//        ActivitySampleBinding mbinding = null;
////        ArrayList<String> typename = new ArrayList<String>();
////        ArrayList<String> typepin = new ArrayList<String>();
//        public AsyncTaskupdateSampleFun(SampleActivity context, ActivitySampleBinding binding) {
//            mcontext = context;
//            mbinding = binding;
//        }
//
//        protected Void doInBackground(Object... params) {
//
//
//
//            SaveDeviceData(mcontext);
//
//            CustomerDisplayFun(mbinding);
//
//            terminalTypeVal = Query.GetDeviceData(Constraints.TERMINAL_TYPE);
//
//
//            if (terminalTypeVal.equals(Constraints.INGENICO)) {
//                DaggerIngenicoComponent.builder()
//                        .ingenicoModule(new IngenicoModule(mcontext))
//                        .build();
//            }
//
//            DatabaseBackUpFun(mcontext);
//
//            SaveDataDefault(mcontext);
//
//            Query.SavePermissionGroup(Constraints.ADMIN);
//
//            mIminPrintUtils = IminPrintUtils.getInstance(mcontext);
//            byte[] bytes = new byte[2];
//            bytes[0]= 27;
//            bytes[1]= 64;
//
//            mIminPrintUtils.printerByte(bytes);
//
//            Cursor Cursor_Staff = Query.getStaffData();
//            if (Cursor_Staff != null){
//                typename.clear();
//                typepin.clear();
//                while (Cursor_Staff.moveToNext()){
//                    typename.add(Cursor_Staff.getString(0));
//                    typepin.add(Cursor_Staff.getString(1));
//                }
//                Cursor_Staff.close();
//            }
//
////        this.mHandler = new Handler();
////        m_Runnable.run();
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void result) {
//
//            ArrayAdapter<String> adapter = new ArrayAdapter<String>(mcontext,
//                    R.layout.spinner_list,typename);
//
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//            mbinding.spinner.setAdapter(adapter);
//            mbinding.spinner.getBackground().setColorFilter(mcontext.getResources().getColor(R.color.colorBg), PorterDuff.Mode.SRC_ATOP);
//
//
//
//            mbinding.pinLockView.attachIndicatorDots(mbinding.indicatorDots);
//            mbinding.pinLockView.setPinLockListener(mPinLockListener);
//            mbinding.pinLockView.setPinLength(4);
//            mbinding.indicatorDots.setIndicatorType(IndicatorDots.IndicatorType.FILL_WITH_ANIMATION);
//
//            mbinding.profileName.setText(Constraints.DEFAULTNAME);
//            mbinding.profileName.setTextSize(16);
//
//            mbinding.pinNo.setText(Constraints.PINNO);
//            mbinding.pinNo.setTextSize(16);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//                mbinding.profileName.setLineHeight(24);
//            }
//        }
//
//        @Override
//        protected void onPreExecute() {
//
////            SaveDeviceData(mcontext);
////
////            CustomerDisplayFun(mbinding);
////
////            terminalTypeVal = Query.GetDeviceData(Constraints.TERMINAL_TYPE);
////            Log.i("Df__","terminalTypeVal___"+terminalTypeVal);
////
////            if (terminalTypeVal.equals(Constraints.INGENICO)) {
////                DaggerIngenicoComponent.builder()
////                        .ingenicoModule(new IngenicoModule(mcontext))
////                        .build();
////            }
//        }
//    }
////
////    private static PinLockListener mPinLockListener = new PinLockListener() {
////        @Override
////        public void onComplete(String pin) {
////            if (PinLockView.mPin.length() > 0){
////                binding.pinNo.setVisibility(View.GONE);
////            }else{
////                binding.pinNo.setVisibility(View.VISIBLE);
////            }
////            Log.i(TAG, "Pin complete: " + pin);
////        }
////
////        @Override
////        public void onEmpty() {
////            Log.i(TAG, "Pin empty");
////        }
////
////        @Override
////        public void onPinChange(int pinLength, String intermediatePin) {
////            Log.i(TAG, "Pin changed, new length " + pinLength + " with intermediate pin " + intermediatePin);
////            if (PinLockView.mPin.length() > 0){
////                binding.pinNo.setVisibility(View.GONE);
////            }else{
////                binding.pinNo.setVisibility(View.VISIBLE);
////            }
////
////        }
////    };
//
//    public void PermissionRequestFun() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//                requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
//            }
//        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                    == PackageManager.PERMISSION_GRANTED) {
//                Log.v(TAG,"Permission is granted");
//
//            } else {
//
//                Log.v(TAG,"Permission is revoked");
//                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
//
//            }
//        }
//        else { //permission is automatically granted on sdk<23 upon installation
//            Log.v(TAG,"Permission is granted");
//        }
//    }
//
//    private static void DatabaseBackUpFun(Context mcontext) {
//        try {
//            PackageInfo pInfo = mcontext.getPackageManager().getPackageInfo(mcontext.getPackageName(), 0);
//            //vercode = pInfo.versionCode;
//            //txt_ver.setText(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 70, pInfo.versionName));
//        } catch (Exception e) {
//        }
//
//        File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/posdata");
//        if (!dir.exists()) {
//            dir.mkdirs();
//        }
//
//        Allocator.DataPath = dir.getAbsolutePath();
//
//        if (!DBFunc.LoadDBFromInternal(mcontext)) {
//            //Toast.makeText(this, "General", Toast.LENGTH_LONG).show();
//            if (DBFunc.CreateResetDB(mcontext, true)) {
//                DBFunc.LoadDBFromInternal(mcontext);
//            } else {
//                DialogBox dlg1 = new DialogBox(mcontext);
//                dlg1.setDialogIconType(DialogBox.IconType.ERROR);
//                //dlg1.setTitle("TEST");
//                //dlg1.setMessage("TESTDSGF");
//                dlg1.setButton1OnClickListener("ERROR", null);
//                dlg1.show();
//            }
//
//        }
//        if (!DBFunc.LoadTransactDBFromInternal(mcontext)) {
//            //Toast.makeText(this, "TESTSDFDSF", Toast.LENGTH_LONG).show();
//            if (DBFunc.CreateResetDB(mcontext, false)) {
//                DBFunc.LoadTransactDBFromInternal(mcontext);
//            } else {
//                DialogBox dlg1 = new DialogBox(mcontext);
//                dlg1.setDialogIconType(DialogBox.IconType.ERROR);
//                dlg1.setTitle("ERROR");
//                dlg1.setMessage("ERROR");
//                dlg1.setButton1OnClickListener("ERROR", null);
//                dlg1.show();
//
//            }
//        }
//    }
//
//    private static void CustomerDisplayFun(ActivitySampleBinding binding) {
//        if (terminalTypeVal.equals(Constraints.PAX_E600M)) {
//
//            LinearLayout.LayoutParams linearparams = new LinearLayout.LayoutParams(520,
//                    ViewGroup.LayoutParams.WRAP_CONTENT);
//            linearparams.topMargin = 20;
//            linearparams.leftMargin = 20;
//
//            binding.layBtnLogin.setLayoutParams(linearparams);
//
//            LinearLayout.LayoutParams linearparamsbtn = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT);
//            binding.btnLogin.setLayoutParams(linearparamsbtn);
//
//
//            LinearLayout.LayoutParams linearparamsLayPinLockView = new LinearLayout.LayoutParams(540,
//                    ViewGroup.LayoutParams.WRAP_CONTENT);
//            linearparamsLayPinLockView.topMargin = 20;
//            linearparamsLayPinLockView.leftMargin = 15;
//            LinearLayout.LayoutParams linearparamsPinLockView = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT);
//            binding.linearlayoutPinlockview.setLayoutParams(linearparamsLayPinLockView);
//            binding.pinLockView.setLayoutParams(linearparamsPinLockView);
//
//
//            LinearLayout.LayoutParams linearOverAllParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT);
//            linearOverAllParams.setMargins(20,0,40,30);
//            binding.layOverAll.setLayoutParams(linearOverAllParams);
//        } else if (terminalTypeVal.equals(Constraints.IMIN)) {
////            LinearLayout.LayoutParams linearparams = new LinearLayout.LayoutParams(520,
////                    ViewGroup.LayoutParams.WRAP_CONTENT);
////            //linearparams.topMargin = 20;
////            //linearparams.leftMargin = 20;
////
////            binding.layBtnLogin.setLayoutParams(linearparams);
////
////            LinearLayout.LayoutParams linearparamsbtn = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
////                    ViewGroup.LayoutParams.WRAP_CONTENT);
////            binding.btnLogin.setLayoutParams(linearparamsbtn);
//
//
//            LinearLayout.LayoutParams linearparamsLayPinLockView = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT);
//            linearparamsLayPinLockView.topMargin = 10;
//            linearparamsLayPinLockView.leftMargin = 20;
//            binding.linearlayoutPinlockview.setLayoutParams(linearparamsLayPinLockView);
//
//            LinearLayout.LayoutParams linearparamsPinLockView = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT);
//            linearparamsPinLockView.topMargin = 20;
//            linearparamsPinLockView.leftMargin = 5;
//            binding.pinLockView.setLayoutParams(linearparamsPinLockView);
//
////            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
////                binding.profileName.setLineHeight(10);
////            }
//
//            LinearLayout.LayoutParams ImgLinearLay = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
//                   200);
//            ImgLinearLay.topMargin = 90;
//            ImgLinearLay.bottomMargin = 50;
//            //linearparamsLayPinLockView.leftMargin = 20;
//            binding.first.setLayoutParams(ImgLinearLay);
//
////            LinearLayout.LayoutParams linearOverAllParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
////                    ViewGroup.LayoutParams.WRAP_CONTENT);
////            linearOverAllParams.setMargins(0,0,0,20);
////            binding.layOverAll.setLayoutParams(linearOverAllParams);
//        }
//    }
//
//    private static void SaveDeviceData(Context mcontext) {
//        String MODEL = android.os.Build.MODEL; // N910
//        String BOARD = android.os.Build.BOARD; // msm8909
//        String BRAND = android.os.Build.BRAND; // qcom
//        String DEVICE = android.os.Build.DEVICE; // msm8909
//        String DISPLAY = android.os.Build.DISPLAY; // NewLand_N910-48f87c9f7a
//        String PRODUCT = android.os.Build.PRODUCT; // msm8909
//        String terminal_type = "";
//        Log.i("MODEL__","MODEL__"+MODEL);
//        Log.i("MODEL__","BOARD__"+BOARD);
//        Log.i("MODEL__","BRAND__"+BRAND);
//        Log.i("MODEL__","DEVICE__"+DEVICE);
//        Log.i("MODEL__","DISPLAY__"+DISPLAY);
//        Log.i("MODEL__","DISPLAY__"+DISPLAY);
//        Log.i("MODEL__","PRODUCT__"+PRODUCT);
//        if (BRAND.equals(Constraints.iMin)){
//            BRAND = Constraints.ALPS;
//        }
//        if ((DISPLAY.split("_")[0]).toUpperCase().equals(Constraints.NewLand)){
//            terminal_type = "NewLand";
//            newLandPrinterInit(mcontext);
//            newlandcustomer_display("0.00");
//        }else if ((DISPLAY.split("_")[0]).equals(Constraints.A930)){
//            terminal_type = "PAX";
//        }else if (MODEL.equals(Constraints.E600M)){
//            terminal_type = Constraints.PAX_E600M;
//        }else if (BRAND.equals(Constraints.LANDI)){
////            DISPLAY_QKQ1.200407.002 release-keys
////            DEVICE_DX8000
////            BRAND_LANDI
////            BOARD_QC_Reference_Phone
////            PRODUCT_DX8000
//            terminal_type = Constraints.INGENICO;
//            //terminal_model = modelVal;
//        }else if (BRAND.equals(Constraints.ALPS)){
////            DISPLAY_QKQ1.200407.002 release-keys
////            DEVICE_DX8000
////            BRAND_LANDI
////            BOARD_QC_Reference_Phone
////            PRODUCT_DX8000
//            terminal_type = Constraints.IMIN;
//            //terminal_model = modelVal;
//        }
//
//       // Query.SaveDeviceData(new DeviceData(0,MODEL,BOARD,BRAND,DEVICE,DISPLAY,PRODUCT,terminal_type.toUpperCase()));
//    }
//
////    public static void initDefaultConfig() {
////        if (Build.MODEL.startsWith("AECR")) {
//////            public static String PINPAD_DEVICE_NAME = DeviceName.IPP;
//////            public static String RF_DEVICE_NAME = RFDeviceName.INNER;
//////            DemoConfig.PINPAD_DEVICE_NAME = DeviceName.COM_EPP;
//////            DemoConfig.RF_DEVICE_NAME = RFDeviceName.EXTERNAL;
////
////            Constraints.PINPAD_DEVICE_NAME = DeviceName.COM_EPP;
////            Constraints.RF_DEVICE_NAME = RFDeviceName.EXTERNAL;
////        } else {
////            Constraints.PINPAD_DEVICE_NAME = DeviceName.IPP;
////            Constraints.RF_DEVICE_NAME = RFDeviceName.INNER;
////        }
////        Log.i("Register_Device","TAG___"+"Registration device");
////    }
//
//    private void requestStoragePermission() {
//        Dexter.withActivity(this)
//                .withPermissions(
//                        Manifest.permission.READ_EXTERNAL_STORAGE,
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                        Manifest.permission.ACCESS_FINE_LOCATION,
//                        Manifest.permission.WRITE_CALENDAR,
//                        Manifest.permission.RECORD_AUDIO,
//                        Manifest.permission.READ_PHONE_STATE,
//                        Manifest.permission.READ_CALENDAR)
//                .withListener(new MultiplePermissionsListener() {
//                    @Override
//                    public void onPermissionsChecked(MultiplePermissionsReport report) {
//                        // check if all permissions are granted
//                        if (report.areAllPermissionsGranted()) {
//                            //Toast.makeText(getApplicationContext(), "All permissions are granted!", Toast.LENGTH_SHORT).show();
//                        }
//
//                        // check for permanent denial of any permission
//                        if (report.isAnyPermissionPermanentlyDenied()) {
//                            // show alert dialog navigating to Settings
//                            //showSettingsDialog();
//                        }
//                    }
//
//                    @Override
//                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
//                        token.continuePermissionRequest();
//                    }
//                }).
//                withErrorListener(new PermissionRequestErrorListener() {
//                    @Override
//                    public void onError(DexterError error) {
//                        Toast.makeText(getApplicationContext(), "Error occurred! ", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .onSameThread()
//                .check();
//    }
//    private void requestCameraPermission() {
//        Dexter.withActivity(this)
//                .withPermission(Manifest.permission.CAMERA)
//                .withListener(new PermissionListener() {
//                    @Override
//                    public void onPermissionGranted(PermissionGrantedResponse response) {
//                        // permission is granted
//                        openCamera();
//                    }
//
//                    @Override
//                    public void onPermissionDenied(PermissionDeniedResponse response) {
//                        // check for permanent denial of permission
//                        if (response.isPermanentlyDenied()) {
//                            //showSettingsDialog();
//                        }
//                    }
//
//                    @Override
//                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
//                        token.continuePermissionRequest();
//                    }
//                }).check();
//    }
//    private void showSettingsDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(SampleActivity.this);
//        builder.setTitle("Need Permissions");
//        builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.");
//        builder.setPositiveButton("GOTO SETTINGS", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.cancel();
////                openSettings();
//            }
//        });
//        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.cancel();
//            }
//        });
//        builder.show();
//
//    }
//
//    private void openCamera() {
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(intent, 100);
//    }
//    private static void newlandcustomer_display(String amt) {
//        ExternalGuestDisplay mExGuestDiaplay = (ExternalGuestDisplay) deviceManager.getDevice().getExModule(ExModuleType.GUEST_DISPLAY);
//
//        boolean bool = mExGuestDiaplay.showDigitalLed(amt);
//
//    }
//
//    private static void SaveDataDefault(Context mcontext) {
//        check = checkExistOrNot("Payment");
//        savePaymentDefault(check);
//
//        check = checkExistOrNot("ReportSettings");
//        saveReportSettingsDefault(check);
//
//        check = checkExistOrNot("TaxType");
//        saveTaxTypeDefault(check);
//
//        check = checkExistOrNot("ReceiptEditor");
//        saveReceiptEditorDefault(check,mcontext);
//
//        check = checkExistOrNot(Constraints.ConfigPaymentHost);
//        saveConfigPaymentHostDefault(check);
//
//        check = checkExistOrNot(Constraints.ConfigPaymentType);
//        saveConfigPaymentTypeDefault(check);
//
//        check = checkExistOrNot("Printer");
//        savePrinterDefault(check);
//
//        check = checkExistOrNot("POSSys");
//        savePOSSysDefault(check);
//    }
//
//    private static void newLandPrinterInit(Context mcontext) {
//        try {
//            deviceManager = ConnUtils.getDeviceManager();
//            deviceManager.init(mcontext, Constraints.K21_DRIVER_NAME, new NS3ConnParams(), new DeviceEventListener<ConnectionCloseEvent>() {
//
//                @Override
//                public void onEvent(ConnectionCloseEvent event, Handler handler) {
//                    if (event.isSuccess()) {
//                        //Log.i("TestingStart","isSuccess");
//                    }
//                    if (event.isFailed()) {
//                        //Log.i("TestingStart","isFailed");
//                    }
//                }
//
//                @Override
//                public Handler getUIHandler() {
//                    return null;
//                }
//            });
//
//            deviceManager.connect();
//            //Log.i("ISACtive","Devicee__"+deviceManager.getDevice().isAlive());
//            deviceManager.getDevice().setBundle(new NS3ConnParams());
//
//        } catch (Exception e1) {
//            e1.printStackTrace();
//            //Log.i("TestingStart","second_two"+e1);
//        }
////        Printer getprinter = (Printer) deviceManager.getDevice().getStandardModule(ModuleType.COMMON_PRINTER);
////        Map<String, Bitmap> map = new HashMap<String, Bitmap>();
////        StringBuffer scriptBuffer = new StringBuffer();
////        scriptBuffer.append("*image l 370*80 path:");
////        scriptBuffer.append("logo\n");
////        scriptBuffer.append("!hz l\n !asc l\n !gray 5\n");
////        scriptBuffer.append("!NLPRNOVER\n");
////
////        Log.i("_getprinter", String.valueOf(getprinter.getStatus()));
////
////        PrinterResult printerResult = getprinter.printByScript(PrintContext.defaultContext(),
////                scriptBuffer.toString(), map, 60, TimeUnit.SECONDS);
//
//        //printer=(com.newland.mtype.module.common.printer.Printer) deviceManager.getDevice().getStandardModule(ModuleType.COMMON_PRINTER);
//        //deviceManager.getDevice().getInstance(context).getPrinter();
//        //printer = (Printer) deviceManager.getDevice().getStandardModule(ModuleType.COMMON_PRINTER);
//        //Log.i("dsfsafd_status", String.valueOf(printer.getStatus()));
//        //printer.init();
//    }
//
//    private static void savePOSSysDefault(String check) {
//        if (check.isEmpty()) {
//            DBFunc.ExecQuery("DELETE FROM POSSys", true);
//            Cursor chk_printer = null;
//            String printer_name = "0";
//            Cursor config_values_printer1 = DBFunc.Query("SELECT id FROM Printer where name = 'receipt_setting'", true);
//            if (config_values_printer1 != null) {
//                while (config_values_printer1.moveToNext()) {
//                    printer_name = config_values_printer1.getString(0);
//
//                }
//            }
//
//            String sql = "INSERT INTO POSSys (name,receipt_printer,retails_id,company_code,company_url,receipt_count," +
//                    "hotkey_col,hotkey_row,bal_mode,bal_title, bill_fontsize, option,maplayout, refer_info1_name, " +
//                    "refer_info2_name, refer_info3_name, roundtype, language) VALUES ('"
//                    + DBFunc.PurifyString("pos")
//                    + "','"
//
//                    + Integer.parseInt(printer_name)
//                    + "','"
//
//                    + "0"
//                    + "','"
//
//                    + ""
//                    + "','"
//
//                    + ""
//                    + "',"
//
//                    + 1
//                    + ","
//
//                    + (3)
//                    + ","
//
//                    + (3)
//                    + ","
//
//                    + (0)
//                    + ",'"
//
//                    + 0
//                    + "',"
//
//                    //+ (8 + 10)
//                    + (14)
//                    + ",'" + DBFunc.PurifyString("00000000000000000000000000") + "'";
//
//            if (0 == 0) {
//                sql += ",NULL";
//            }
//
//            if (0 == 0) {
//                sql += ",NULL";
//            }
//
//            if (0 == 0) {
//                sql += ",NULL";
//            }
//            if (0 == 0) {
//                sql += ",NULL";
//            }
//
//
//            sql += "," + 0+"";
//
//            sql += ", " + 0;
//
//            sql += ")";
//
//            //DBFunc.ExecQuery("DELETE FROM Printer", true);
//
//
//            DBFunc.ExecQuery(sql, true);
//
//            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth, System.currentTimeMillis(), "POSConfig -> Update");
//
//            //printer_Save();
//        }
//    }
//
//    private static void savePrinterDefault(String check) {
//        if (check.isEmpty()) {
//            SortedMap charsets = Charset.availableCharsets();
//            String ch_name = "";
//            for (Iterator e = charsets.keySet().iterator(); e.hasNext(); ) {
//                Charset charset = (Charset) charsets.get(e.next());
//
//                if (Charset.isSupported(charset.name())) {
//                    if (charset.name().equals("US-ASCII")) {
//                        ch_name = charset.name();
//                    }
//                }
//            }
//
//            //spn_charset.setSelection(adapt_charset.getPosition("US-ASCII"));
//            //default Add Printer
//            final String fcharset1 = "" + ch_name;
//            DBFunc.ExecQuery("INSERT INTO Printer (name,dflt,type,lfeed,lwidth,nodouble,param,charset) " +
//                    "VALUES ('" + DBFunc.PurifyString("receipt_setting") + "'," + (0) + "," + 0 + "," + 5 + "," + 40 + "," + (0) + ",'" + DBFunc.PurifyString("") + "','" + DBFunc.PurifyString(fcharset1) + "')", true);
//
//            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth, System.currentTimeMillis(), "Printer -> Add -> Name: " + "receipt_setting");
//
//        }
//    }
//
//    private static void saveConfigPaymentTypeDefault(String check) {
//        if (check.isEmpty()) {
//            DBFunc.ExecQuery("INSERT INTO "+ Constraints.ConfigPaymentType+" (Name) VALUES ('"+ DeclarationConf.CARD_TYPE_OTHERS.toUpperCase()+"' )", true);
//            DBFunc.ExecQuery("INSERT INTO "+ Constraints.ConfigPaymentType+" (Name) VALUES ('"+DeclarationConf.CARD_TYPE_WECHAT_PAY.toUpperCase()+"' )", true);
//            DBFunc.ExecQuery("INSERT INTO "+ Constraints.ConfigPaymentType+" (Name) VALUES ('"+DeclarationConf.CARD_TYPE_GRAB_PAY.toUpperCase()+"' )", true);
//            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth, System.currentTimeMillis(), "ConfPayType -> Add -> PaymentTypeName: " + "others");
//        }
//    }
//
//    private static void saveConfigPaymentHostDefault(String check) {
//        if (check.isEmpty()) {
//            DBFunc.ExecQuery("INSERT INTO "+ Constraints.ConfigPaymentHost+" (Name,Activity,BankName,Activate) VALUES ('" + DBFunc.PurifyString("sg.com.mobileeftpos.paymentapplication") + "','" + DBFunc.PurifyString("sg.com.mobileeftpos.paymentapplication.activities.ECRActivity") + "' , '" + DBFunc.PurifyString("ocbc").toLowerCase().trim() + "', " + "1" + ")", true);
//
//            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth, System.currentTimeMillis(), "ConfPayHost -> Add -> BankName: " + "ocbc");
//        }
//    }
//
//    private static void saveReceiptEditorDefault(String check, Context mcontext) {
//        if (check.isEmpty()) {
//            Query.SaveReceiptEditor(mcontext,null,"Hello , Welcome !","Thank you , come again!",0,0);
//        }
//    }
//
//    private static void saveTaxTypeDefault(String check) {
//        String sqlChk = "SELECT Name FROM TaxType WHERE Name = 'None'";
//        Cursor c = DBFunc.Query(sqlChk,true);
//        if (c != null){
//            if (c.getCount() == 0){
//
////                String truncateTb = "DELETE FROM TaxType WHERE Name = 'Inclusive'";
////                String truncateTb1 = "DELETE FROM TaxType WHERE Name = 'Exclusive'";
////                DBFunc.ExecQuery(truncateTb,true);
////                DBFunc.ExecQuery(truncateTb1,true);
////
////                SaveDataDefault_Table("TaxType","Name","Inclusive");
////                SaveDataDefault_Table("TaxType","Name","Exclusive");
//                SaveDataDefault_Table("TaxType","Name","None");
////
////                DBFunc.ExecQuery("INSERT INTO TaxType (Name,DateTime) VALUES ('None'," +System.currentTimeMillis()+")", true);
////                DBFunc.ExecQuery("INSERT INTO TaxType (Name,DateTime) VALUES ('Inclusive'," +System.currentTimeMillis()+")", true);
////                DBFunc.ExecQuery("INSERT INTO TaxType (Name,DateTime) VALUES ('Exclusive'," +System.currentTimeMillis()+")", true);
//            }
//            c.close();
//        }
//        if (check.isEmpty()) {
//            SaveDataDefault_Table("TaxType","Name","None");
//            SaveDataDefault_Table("TaxType","Name","Inclusive");
//            SaveDataDefault_Table("TaxType","Name","Exclusive");
//////            DBFunc.ExecQuery("INSERT INTO TaxType (Name,DateTime) VALUES ('GST / VAT'," +System.currentTimeMillis()+")", true);
////            DBFunc.ExecQuery("INSERT INTO TaxType (Name,DateTime) VALUES ('None'," +System.currentTimeMillis()+")", true);
////            DBFunc.ExecQuery("INSERT INTO TaxType (Name,DateTime) VALUES ('Inclusive'," +System.currentTimeMillis()+")", true);
////            DBFunc.ExecQuery("INSERT INTO TaxType (Name,DateTime) VALUES ('Exclusive'," +System.currentTimeMillis()+")", true);
//////            DBFunc.ExecQuery("INSERT INTO TaxType (Name,DateTime) VALUES ('CESS'," +System.currentTimeMillis()+")", true);
//        }
//    }
//
//    private static void SaveDataDefault_Table(String tbName, String fieldName, String fieldValue) {
//        String sqlChk = "SELECT "+fieldName+" FROM  "+tbName+" WHERE Name =" + "'"+fieldValue+"'";
//        Cursor c = DBFunc.Query(sqlChk,true);
//        if (c != null) {
//            if (c.getCount() == 0) {
//                DBFunc.ExecQuery("INSERT INTO "+tbName+" ("+fieldName+",DateTime) VALUES ('"+fieldValue+"'," +System.currentTimeMillis()+")", true);
//            }
//        }
//    }
//
//    private static void saveReportSettingsDefault(String check) {
//        if (check.isEmpty()) {
//            Query.SaveReportSettings("1","1","1","1","1","1","1","1","1");
//        }
//    }
//
//    private static String checkExistOrNot(String tblName) {
//        check = "";
//        config_values = DBFunc.Query("SELECT * FROM "+tblName, true);
//        if (config_values != null) {
//            while (config_values.moveToNext()) {
//                check = config_values.getString(0);
//            }
//            config_values.close();
//        }
//        return check;
//    }
//
//    private static void savePaymentDefault(String check) {
//        if (check.isEmpty()) {
//            Query.SavePayment(DeclarationConf.PAYMENT_TYPE_CASH,"1","0","0","0","0.00","0","1","0","0","0");
//            Query.SavePayment(DeclarationConf.PAYMENT_TYPE_MASTER,"0","1","0","0","0.00","0","1","0","0","0");
//            Query.SavePayment(DeclarationConf.PAYMENT_TYPE_VISA,"0","1","0","0","0.00","0","1","0","0","0");
//            Query.SavePayment(DeclarationConf.PAYMENT_TYPE_NET,"1","0","0","0","0.00","0","1","1","0","0");
//        }
//    }
//
//    private void ShowStaff() {
//        String sql = "SELECT id,name,pin,commission,permission_group_id,permission_group_name " +
//                "FROM Staff";
//        Log.i("fjgire__",sql);
//        Cursor c = DBFunc.Query(sql, true);
//        if (c != null) {
//            while (c.moveToNext()) {
//                pin_id.add(c.getInt(0));
//                pin_name.add(c.getInt(1));
//                pin_arr.add(c.getInt(2));
//            }
//            c.close();
//        }
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//        // TODO Auto-generated method stub
//    }
////    //Performing action onItemSelected and onNothing selected
//    @Override
//    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
//        //Toast.makeText(getApplicationContext(), pin_name.get(position), Toast.LENGTH_LONG).show();
//        NAME = typename.get(position);
//        PIN = typepin.get(position);
//    }
//
////    private final Runnable m_Runnable = new Runnable()
////    {
////        public void run()
////
////        {
////            //Toast.makeText(SampleActivity.this,"in runnable" ,Toast.LENGTH_SHORT).show();
//////            Toast.makeText(AddTaxConfigurationActivity.this,"in runnable ddd"+TaxTypeSheetFragment.selected_tax_name ,Toast.LENGTH_SHORT).show();
////            mHandler.postDelayed(m_Runnable, 300);
////            //new LongOperation().execute();
////            RunAuto();
////           // SampleActivity.this.mHandler.postDelayed(m_Runnable,500);
////        }
////
////    };
//
////    private void RunAuto() {
////        //getGeneralSetings();
////        if (PinLockView.mPin.length() > 0){
////            binding.pinNo.setVisibility(View.GONE);
////        }else{
////            binding.pinNo.setVisibility(View.VISIBLE);
////        }
////    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == MY_CAMERA_REQUEST_CODE) {
//            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
//            } else {
//                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
//            }
//        }
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.img_login_name:
//                StaffLoginSheetFragment pcSSheetFragment = new StaffLoginSheetFragment();
//                pcSSheetFragment.show(getSupportFragmentManager(), pcSSheetFragment.getTag());
//                break;
//              case R.id.btn_login:
////                  if (PinLockView.mPin.equals("1111")){
////                      Intent intent = new Intent(this, SettingActivity.class);
//////                      intent.putExtra("name", "SampleActivity");
////                      startActivity(intent);
////                      finish();
////                      PinLockView.mPin = "";
////                  } else {
////                      try {
////
////                          new AsyncTaskCheckLoginAccess(this,Constraints.LOGINSYSTEM,Constraints.Accessable).execute();
////                       }catch (Exception e){
////                            Query.DonothaveUserAccess(this);
////                    }
////                       SyncActivity.volleyCheckPermission(this,Constraints.LOGINSYSTEM,Constraints.Accessable);
//////                       SyncActivity.volleyCheckPermission(this,Constraints.LOGINSYSTEM,Constraints.Accessable);
////                        Log.i("dsfdsfaccess__","dddd___"+Constraints.Accessable);
////                      if (Constraints.Accessable.equals("1")) {
////                        Intent intent = new Intent(context, MainActivity.class);
////                        intent.putExtra("name", "SampleActivity");
////                         startActivity(intent);
////                        finish();
////                        PinLockView.mPin = "";
////                    } else {
////                          SyncActivity.volleyCheckPermission(this,Constraints.LOGINSYSTEM,Constraints.Accessable);
////                          if (Constraints.Accessable.equals("1")) {
////                              Intent intent = new Intent(context, MainActivity.class);
////                              intent.putExtra("name", "SampleActivity");
////                              startActivity(intent);
////                              finish();
////                              PinLockView.mPin = "";
////                          } else {
////                              Query.DonothaveUserAccess(context);
////                          }
////        //                if (recount < count) {
////        //                    recount ++;
////        //
////        //                    new AsyncTaskCheckLoginAccess(context,Constraints.LOGINSYSTEM,Constraints.Accessable).execute();
////        ////                    str_accessable = SyncActivity.volleyCheckPermission(mcontext,str_ordering,str_accessable,syncRetailID,
////        ////                            syncCompanyCode,syncUrl,download_retail_ID,download_company_code,download_url,download_userlogin,download_userpassword);
////        ////                    if (str_accessable.equals("1")) {
////        ////                        Intent intent = new Intent(mcontext, MainActivity.class);
////        ////                        intent.putExtra("name", "SampleActivity");
////        ////                        ((Activity)mcontext).startActivity(intent);
////        ////                        ((Activity)mcontext).finish();
////        ////                        PinLockView.mPin = "";
////        ////                    }
////        //                }else {
//////                            Query.DonothaveUserAccess(context);
////        //                }
////                    }
//                  }
////                  else if (PinLockView.mPin.equals("0001")) {
////
////                      new AsyncTaskCheckLoginAccess(this,Constraints.LOGINSYSTEM,Constraints.Accessable).execute();
////
////
////                  }
////                  else {
////                      //Query.ErrorDialog(this, ENUM.WrongPassword);
////
////                      new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
////                              .setTitleText(Constraints.WrongPassword)
////                              .setConfirmText(Constraints.OK)
////                              .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
////                                  @Override
////                                  public void onClick(SweetAlertDialog sDialog) {
////                                      sDialog.dismissWithAnimation();
////                                  }
////                              })
////                              .show();
////
////                  }
////                  String StatusSuccess = "0";
////                  Cursor Cursor_Staff = Query.getStaff(NAME,PinLockView.mPin,"SEARCH");
////                  if (Cursor_Staff != null){
////                      if (Cursor_Staff.moveToNext()){
////                          StatusSuccess = "1";
////                      }
////                      Cursor_Staff.close();
////                  }
////                  if (StatusSuccess.equals("1")){
////                        Intent intent = new Intent(this, MainActivity.class);
////                        intent.putExtra("name", "SampleActivity");
////                        startActivity(intent);
////                        finish();
////                        PinLockView.mPin = "";
////                  }else {
////                      //Query.ErrorDialog(this, ENUM.WrongPassword);
////
////                      new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
////                              .setTitleText(Constraints.WrongPassword)
////                              .setConfirmText(Constraints.OK)
////                              .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
////                                  @Override
////                                  public void onClick(SweetAlertDialog sDialog) {
////                                      sDialog.dismissWithAnimation();
////                                  }
////                              })
////                              .show();
////
////                  }
//////                if (password.equals("0")){
//////                    if (PinLockView.mPin.length() > 0 && PinLockView.mPin.equals("1111")) {
//////                        Intent intent = new Intent(this, MainActivity.class);
//////                        intent.putExtra("name", "SampleActivity");
//////                        startActivity(intent);
//////                        finish();
//////                        PinLockView.mPin = "";
//////                    }
//////                }else{
//////                    if (PinLockView.mPin.length() > 0 && PinLockView.mPin.equals(password)) {
//////                        Intent intent = new Intent(this, MainActivity.class);
//////                        intent.putExtra("name", "SampleActivity");
//////                        startActivity(intent);
//////                        finish();
//////                        PinLockView.mPin = "";
//////                    }
//////                }
//                 break;
//        }
//    }
//
//    private static class AsyncTaskCheckLoginAccess extends AsyncTask<Object, Void, String> {
//        Context mcontext;
//        String str_ordering = "";
//        String str_accessable = "";
//        String syncRetailID = "";
//        String syncCompanyCode = "";
//        String syncUrl = "";
//        String download_retail_ID = "";
//        String download_company_code = "";
//        String download_url = "";
//        String download_userlogin = "";
//        String download_userpassword = "";
//        public AsyncTaskCheckLoginAccess(Context context,String strOrdering,String strAccessable) {
//            mcontext = context;
//            str_ordering = strOrdering;
//            str_accessable = strAccessable;
//        }
//
//        ProgressDialog pd = null;
//
//        protected String doInBackground(Object... params) {
//
////            str_accessable = SyncActivity.volleyCheckPermission(mcontext,str_ordering,str_accessable,syncRetailID,
////                    syncCompanyCode,syncUrl,download_retail_ID,download_company_code,download_url,download_userlogin,download_userpassword);
//
//            //String accessable = SyncActivity.volleyCheckPermission(mcontext,str_ordering,str_accessable);
//            return "0";
//        }
//
//        Integer count = 2;
//        @Override
//        protected void onPostExecute(String result) {
//            Integer recount = 0;
//
//            Log.i("__","Constraints.Accessable_ee_"+count+"___"+recount+"___"+result);
//            if (result.equals("1")) {
//                Intent intent = new Intent(mcontext, MainActivity.class);
//                intent.putExtra("name", "SampleActivity");
//                ((Activity)mcontext).startActivity(intent);
//                ((Activity)mcontext).finish();
//                PinLockView.mPin = "";
//            } else {
////                if (recount == 0) {
////                if (recount > 2) {
////                    recount ++;
////                    try {
////                        //String accessable = SyncActivity.volleyCheckPermission(mcontext,str_ordering,str_accessable);
////                        Log.i("sfd__str_accessable","str_accessable__"+str_accessable);
//////                        if ("1".equals("1")) {
//////                            Intent intent = new Intent(mcontext, MainActivity.class);
//////                            intent.putExtra("name", "SampleActivity");
//////                            ((Activity)mcontext).startActivity(intent);
//////                            ((Activity)mcontext).finish();
//////                            PinLockView.mPin = "";
//////                        }else {
//////                            Query.DonothaveUserAccess(mcontext);
//////                        }
////                    }catch (Exception e){
//                        Query.DonothaveUserAccess(mcontext);
////                    }
////
////                } else {
////                    Query.DonothaveUserAccess(mcontext);
////                }
////                if (recount < count) {
////                    recount ++;
////
////                    new AsyncTaskCheckLoginAccess(context,Constraints.LOGINSYSTEM,Constraints.Accessable).execute();
//////                    str_accessable = SyncActivity.volleyCheckPermission(mcontext,str_ordering,str_accessable,syncRetailID,
//////                            syncCompanyCode,syncUrl,download_retail_ID,download_company_code,download_url,download_userlogin,download_userpassword);
//////                    if (str_accessable.equals("1")) {
//////                        Intent intent = new Intent(mcontext, MainActivity.class);
//////                        intent.putExtra("name", "SampleActivity");
//////                        ((Activity)mcontext).startActivity(intent);
//////                        ((Activity)mcontext).finish();
//////                        PinLockView.mPin = "";
//////                    }
////                }else {
//
////                }
//            }
//        }
//
//        @Override
//        protected void onPreExecute() {
////            Cursor Cursor_Possys = Query.GetURLAndCodeFromPossys();
////            if (Cursor_Possys != null) {
////                while (Cursor_Possys.moveToNext()) {
////                    syncRetailID = Cursor_Possys.getString(0);
////                    syncCompanyCode = Cursor_Possys.getString(1);
////                    syncUrl = Cursor_Possys.getString(2);
////                    download_retail_ID = Cursor_Possys.getString(3);
////                    download_company_code = Cursor_Possys.getString(4);
////                    download_url = Cursor_Possys.getString(5);
////                    download_userlogin = Cursor_Possys.getString(13);
////                    download_userpassword = Cursor_Possys.getString(14);
////                }
////                Cursor_Possys.close();
////            }
//        }
//    }
//}
