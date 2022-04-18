package com.dcs.myretailer.app.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.dcs.myretailer.app.Allocator;
import com.dcs.myretailer.app.Cashier.DeclarationConf;
import com.dcs.myretailer.app.Cashier.MainActivity;
import com.dcs.myretailer.app.DaggerIngenicoComponent;
import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.DeviceListAdapter;
import com.dcs.myretailer.app.DialogBox;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.FontAssets.RemarkMainActivityFontAssets;
import com.dcs.myretailer.app.IngenicoModule;
import com.dcs.myretailer.app.Logger;
import com.dcs.myretailer.app.Model.DeviceData;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.Report.SerialChecker;
import com.dcs.myretailer.app.Setting.StrTextConst;
import com.dcs.myretailer.app.databinding.ActivityRemarkMainBinding;
import com.imin.printerlib.IminPrintUtils;
import com.imin.printerlib.util.BluetoothUtil;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.newland.me.ConnUtils;
import com.newland.me.DeviceManager;
import com.newland.mtype.ConnectionCloseEvent;
import com.newland.mtype.ExModuleType;
import com.newland.mtype.event.DeviceEventListener;
import com.newland.mtype.module.common.externalGuestDisplay.ExternalGuestDisplay;
import com.newland.mtypex.nseries3.NS3ConnParams;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;

public class RemarkMainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = "PinLockView";
    private static final int MY_CAMERA_REQUEST_CODE = 100;
    private static final ArrayList<String> typename = new ArrayList<String>();
    private static final ArrayList<String> typepin = new ArrayList<String>();
    ArrayList<Integer> pin_arr = new ArrayList<Integer>();
    ArrayList<Integer> pin_name = new ArrayList<Integer>();
    ArrayList<Integer> pin_id = new ArrayList<Integer>();
    static DeviceManager deviceManager = null;
    public static ActivityRemarkMainBinding binding = null;
    static Cursor config_values = null;
    public static String NAME = "0";
    public static String PIN = "0";
    static String terminalTypeVal = "0";
    Handler mHandler;
    static String check = "0";
    public static IminPrintUtils mIminPrintUtils;
    public static Context context = null;
    public static String loginname = "";
    public static String userid = "";
    public static String userpassword = "";
    public static String licensekeyVal = "";
    PackageInfo pInfo = null;
    static int vercode = 0;
//    public static String loginSuccess = "0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_remark_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_remark_main);

        context = this;

        Intent intent = getIntent();
        if (intent.hasExtra("licensekeyVal")) {
            licensekeyVal = intent.getStringExtra("licenseKey");
        }
        MainActivity.licensekeyVal = licensekeyVal;

        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);

            vercode = pInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        try {
            updateSampleFun(this);

        } catch (Exception e){
            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                    System.currentTimeMillis(), DBFunc.PurifyString("Err-RemarksMainActivity-updateSampleFun"+e.getMessage()));

        }

        try {
            PermissionRequestFun();

        } catch (Exception e){
            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                    System.currentTimeMillis(), DBFunc.PurifyString("Err-RemarksMainActivity-PermissionRequestFun"+e.getMessage()));


        }

        try {
            requestStoragePermission();
        } catch (Exception e){
            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                    System.currentTimeMillis(), DBFunc.PurifyString("Err-RemarksMainActivity-requestStoragePermission"+e.getMessage()));


        }

//         updateSampleFun(this);

        try {
            binding.user.setInputType(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            binding.btnLogin.setOnClickListener(this);
        } catch (Exception e){
            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                    System.currentTimeMillis(), DBFunc.PurifyString("Err-RemarksMainActivity-SOFT_INPUT_STATE_ALWAYS_VISIBLE"+e.getMessage()));


        }
        try {
            screenDisplay();
        } catch (Exception e){
            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                    System.currentTimeMillis(), DBFunc.PurifyString("Err-RemarksMainActivity-screenDisplay"+e.getMessage()));


        }
        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            int vercode = pInfo.versionCode;
            binding.txtVer.setText(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 70, pInfo.versionName));
        } catch (Exception e) {
        }
        binding.txtLicense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dlg = new Dialog(RemarkMainActivity.this);
                dlg.setTitle(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 90));
                dlg.setContentView(R.layout.dlg_license);
                Button btn = (Button) dlg.findViewById(R.id.btn_ok);
                TextView txt = (TextView) dlg.findViewById(R.id.txt_license);
                InputStream is = RemarkMainActivity.this.getResources().openRawResource(R.raw.licenses);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                try {
                    int len = 0;
                    byte[] b = new byte[8192];
                    while ((len = is.read(b)) != -1) {
                        baos.write(b, 0, len);
                    }
                    baos.flush();
                    is.close();
                    byte[] str = baos.toByteArray();
                    txt.setText(new String(str));

                } catch (IOException e) {
                    Logger.WriteLog("ActivityPosMenu", e.toString());
                    Log.i("ActivityPosMenu", e.toString());
                }
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dlg.dismiss();
                    }
                });

                btn.setText(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0));

                dlg.show();
            }

        });



        final int[] code = SerialChecker.Encode(this);
        final String codetxt = SerialChecker.GenHWIDCode(code);
        String GetLicenseKey = Query.GetLicenseKey("MacAddress");
        String GetKeyLicense = Query.GetLicenseKey("KeyLicense");

        new RemarkMainActivityFontAssets(binding);

        if (GetKeyLicense != null && GetKeyLicense.length() > 0) {
            binding.KeyLicense.setText(GetKeyLicense);
        }

//        if (!GetLicenseKey.equals(codetxt)) {
        if (1 == 0) {

            LicenseKeyFun();

        }


    }

    private void LicenseKeyFun() {
        if (!SerialChecker.CheckSerial(this)) {
            //binding.overall.setAlpha(0.4F);
            final DialogBox dlg = new DialogBox(this);
            // dlg.setCancelable(false);
            // dlg.setCanceledOnTouchOutside(false);
            dlg.setTitle(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 31));

            LinearLayout lay = new LinearLayout(this);
            // lay.setLayoutParams(new
            // LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
            lay.setOrientation(LinearLayout.VERTICAL);
            TextView tv = new TextView(this);
            final int[] code = SerialChecker.Encode(this);
            final String codetxt = SerialChecker.GenHWIDCode(code);
            Log.i("codetxt____","codetxt_____"+codetxt);
            tv.setText(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 32, codetxt));
            tv.setTypeface(Typeface.MONOSPACE);
            tv.setTextColor(ContextCompat.getColor(this, R.color.mine_shaft));
            lay.addView(tv);

            // LinearLayout lay_barcode = new LinearLayout(this);

            // lay_barcode.setBackgroundColor(Color.BLACK);

//			final BarcodeReaderPreview barcode_reader = new BarcodeReaderPreview(dlg.getContext());
//			barcode_reader.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
//			// lay_barcode.addView(barcode_reader, new
//			// LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT,1));
//			lay.addView(barcode_reader);

            final EditText et = new EditText(this);
            et.setTypeface(Typeface.MONOSPACE);
            et.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            et.setMaxLines(1);
            et.setTextColor(ContextCompat.getColor(this, R.color.mine_shaft));
            et.setSingleLine(true);
            lay.addView(et);

            // LinearLayout lay1 = new LinearLayout(this);
            // lay1.setLayoutParams(new
            // LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
            // final Button btn0 = new Button(this);
            // btn0.setText(StrTextConst.GetText(TextType.GENERAL, 34));
            // btn0.setLayoutParams(new
            // LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT,
            // 1f));
            dlg.setButton1OnClickListener("Settings", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent btn_dialog = new Intent(RemarkMainActivity.this, SettingActivity.class);
                    startActivity(btn_dialog);
//                    ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
//                    ClipData clip = ClipData.newPlainText("PIXIEPOS_ACTIVATOR", codetxt);
//                    clipboard.setPrimaryClip(clip);
//                    Toast.makeText(v.getContext(), StrTextConst.GetText(StrTextConst.TextType.GENERAL, 33), Toast.LENGTH_SHORT).show();
                }
            });

            // final Button btn1 = new Button(this);
            // btn1.setText(StrTextConst.GetText(TextType.GENERAL, 35));
            // btn1.setLayoutParams(new
            // LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT,
            // 1f));
            dlg.setButton2OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 35), new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    Intent i = new Intent(RemarkMainActivity.this, RemarkMainActivity.class);
//                    i.putExtra("licenseKey",et.getText().toString().toUpperCase());
//                    startActivity(i);
//                    finish();

                    SyncActivity.CheckLicenseKey(RemarkMainActivity.this,et.getText().toString(),codetxt);
////
//                    String GetLicenseKey = Query.GetLicenseKey(et.getText().toString(),"LicenseKeyAct");
//
//                    Log.i("__","GetLicenseKey___"+GetLicenseKey+"___"+et.getText().toString());
//
//                    //if (GetLicenseKey.equals("1")) {
//                    if (GetLicenseKey.equals(et.getText().toString())) {
//                        Intent i = new Intent(RemarkMainActivity.this, RemarkMainActivity.class);
//                        i.putExtra("licenseKey",et.getText().toString().toUpperCase());
//                        startActivity(i);
//                        finish();
//                    }  else {
////                            DialogBox dlg1 = new DialogBox(v.getContext());
////                            dlg1.setDialogIconType(DialogBox.IconType.ERROR);
////                            dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 30));
////                           //dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 37));
////                            dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
////                            dlg1.show();
//                            binding.overall.setAlpha(1);
////                            return;
//
//                        //Logger.WriteLog("ActivityPosMenu",e.toString());
//                        DialogBox dlg1 = new DialogBox(v.getContext());
//                        dlg1.setDialogIconType(DialogBox.IconType.ERROR);
//                        dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 30));
//                       // dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 37));
//                        dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
//                        dlg1.show();
//                        }
////                    Intent i = new Intent(RemarkMainActivity.this, RemarkMainActivity.class);
////                    //i.putExtra("licenseKey",et.getText().toString().toUpperCase());
////                    i.putExtra("licenseKey",et.getText().toString().toUpperCase());
////                    startActivity(i);
////                    finish();
////                    try {
////                        int[] key = SerialChecker.DeGenSerial(et.getText().toString());
////
////                       // if (Arrays.equals(key, code)) {
////                        if ("1".equals("1")) {
////                            File file = new File(v.getContext().getFilesDir(), "info.dat");
////                            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
////                            writer.write(et.getText().toString().toUpperCase());
////                            writer.flush();
////                            writer.close();
////                            DialogBox dlg1 = new DialogBox(v.getContext());
////                            dlg1.setDialogIconType(DialogBox.IconType.INFORMATION);
////                            dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 30));
////                            dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 36));
////                            dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
////                            dlg1.show();
////                            dlg.dismiss();
////
////                            Intent i = new Intent(LicenseKeyActivity.this, RemarkMainActivity.class);
////                            i.putExtra("licenseKey",et.getText().toString().toUpperCase());
////                            startActivity(i);
////                            finish();
////
////
////                            //binding.overall.setAlpha(1);
////                        } else {
////                            DialogBox dlg1 = new DialogBox(v.getContext());
////                            dlg1.setDialogIconType(DialogBox.IconType.ERROR);
////                            dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 30));
////                            dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 37));
////                            dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
////                            dlg1.show();
////                            binding.overall.setAlpha(1);
////                            return;
////                        }
////                    } catch (Exception e) {
////                        //binding.overall.setAlpha(1);
////                        Logger.WriteLog("ActivityPosMenu",e.toString());
////                        DialogBox dlg1 = new DialogBox(v.getContext());
////                        dlg1.setDialogIconType(DialogBox.IconType.ERROR);
////                        dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 30));
////                        dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 37));
////                        dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
////                        dlg1.show();
////                    }
                }
            });
            // Button btn2 = new Button(this);
            // btn2.setText(StrTextConst.GetText(TextType.GENERAL, 3));
            dlg.setButton3OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 3), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.exit(-1);
                }

            });

            // lay1.addView(btn0);
            // lay1.addView(btn1);
            // lay1.addView(btn2);
//			barcode_reader.setOnBarcodeReadSuccessListener(new OnBarcodeReadSuccess() {
//				@Override
//				public void ReadSuccess(String text) {
//					et.setText(text.toUpperCase());
//					dlg.performButton1Click();
//				}
//			});

            // lay.addView(lay1);
            lay.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            dlg.addView(lay);
            // (lay, new
            // LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
            //dlg.setWindowSize(0.7f, 0.7f);
            dlg.setWindowSize(1.7f, 0.7f);
            dlg.show();
        }else {
            // binding.overall.setAlpha(1);
            Intent i = new Intent(RemarkMainActivity.this, RemarkMainActivity.class);
            startActivity(i);
            finish();
        }
    }

    private void screenDisplay() {
        String device = Query.GetDeviceData(Constraints.DEVICE);
        Log.i("sdf__device","device___"+device);
        if (device.equals("M2-Max")) {
            Log.i("sdf__device","device___"+device);
            LinearLayout.LayoutParams linearparamsimage = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            linearparamsimage.setMargins(10,120,10,0);
            binding.profileImage.setLayoutParams(linearparamsimage);

            LinearLayout.LayoutParams linearparams = new LinearLayout.LayoutParams(700, 100);
            linearparams.setMargins(0,130,0,0);
            binding.userLay.setLayoutParams(linearparams);

            LinearLayout.LayoutParams linearparamspassword = new LinearLayout.LayoutParams(700, 100);
            linearparamspassword.setMargins(0,50,0,0);
            binding.passLay.setLayoutParams(linearparamspassword);

            LinearLayout.LayoutParams linearparamsbtnlogin = new LinearLayout.LayoutParams(700, 80);
            linearparamsbtnlogin.setMargins(0,50,0,0);
            binding.btnLogin.setLayoutParams(linearparamsbtnlogin);

        }
    }
    public static final int REQUEST_READ_PHONE_STATE = 8;
    private void PermissionRequestFun() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
            }
        }
        int permissionCheckREAD_PHONE_STATE = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
        if(permissionCheckREAD_PHONE_STATE != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                //Log.v(TAG,"Permission is granted");

            } else {

                //Log.v(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            //Log.v(TAG,"Permission is granted");
        }
    }

    public void requestStoragePermission() {
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.WRITE_CALENDAR,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.READ_CALENDAR)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            //Toast.makeText(getApplicationContext(), "All permissions are granted!", Toast.LENGTH_SHORT).show();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings
                            //showSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(getApplicationContext(), "Error occurred! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }

    @SuppressLint("MissingPermission")
    private void updateSampleFun(RemarkMainActivity context) {

        try {
            SaveDeviceData(context);

        } catch (Exception e){
            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                    System.currentTimeMillis(), DBFunc.PurifyString("Err-RemarksMainActivity-SaveDeviceData"+e.getMessage()));
        }

        try {


            terminalTypeVal = Query.GetDeviceData(Constraints.TERMINAL_TYPE);
        }catch (Exception e){
            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                    System.currentTimeMillis(), DBFunc.PurifyString("Err-RemarksMainActivity-terminalTypeVal"+e.getMessage()));
        }


        try {
            CustomerDisplayFun(binding);
        } catch (Exception e){
            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                    System.currentTimeMillis(), DBFunc.PurifyString("Err-RemarksMainActivity-CustomerDisplayFun"+e.getMessage()));
        }


        if (terminalTypeVal.equals(Constraints.INGENICO)) {
            DaggerIngenicoComponent.builder()
                    .ingenicoModule(new IngenicoModule(context))
                    .build();
        }

        try {
            DatabaseBackUpFun(context);
            try {
                SaveDataDefault(context);
            } catch (Exception e){
                DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                        System.currentTimeMillis(), DBFunc.PurifyString("Err-RemarksMainActivity-SaveDataDefault"+e.getMessage()));
            }
        } catch (Exception e){
            Log.i("DSFD___","SfsfdgetMessage____"+e.getMessage());
            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                    System.currentTimeMillis(), DBFunc.PurifyString("Err-RemarksMainActivity-DatabaseBackUpFun"+e.getMessage()));
        }


        try {
            wait(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            Query.SavePermissionGroup(Constraints.ADMIN);
        } catch (Exception e){
            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                    System.currentTimeMillis(), DBFunc.PurifyString("Err-RemarksMainActivity-SavePermissionGroup"+e.getMessage()));
        }

        try {
            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                    System.currentTimeMillis(), DBFunc.PurifyString("Chk-ChkterminalTypeVal-" + terminalTypeVal));
        } catch (Exception e){
            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                    System.currentTimeMillis(), DBFunc.PurifyString("Err-ChkterminalTypeVal-"+terminalTypeVal));
        }

        try {

            if (terminalTypeVal.equals(Constraints.IMIN)) {

                mIminPrintUtils = IminPrintUtils.getInstance(context);
                byte[] bytes = new byte[2];
                bytes[0] = 27;
                bytes[1] = 64;

                mIminPrintUtils.printerByte(bytes);

                DeviceListAdapter mAdapter = new DeviceListAdapter(context);
                List<BluetoothDevice> printerDevices = BluetoothUtil.getPairedDevices();
                mAdapter.clear();
                Log.i("printerDevices__", "AAAA___" + printerDevices);
                mAdapter.addAll(printerDevices);
                int bluetoothPosition = 0;
//        int bluetoothPosition = 1;
                BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
                adapter.enable();

                BluetoothDevice device = mAdapter.getItem(bluetoothPosition);
                Log.i("printerDevices__", "BBBB___" + device);
                try {

                    Log.i("terminaltype_check__", "terminaltype_checkr___" + Build.VERSION.SDK_INT + "__" + Build.VERSION_CODES.N);
                    mIminPrintUtils.initPrinter(IminPrintUtils.PrintConnectType.BLUETOOTH, device);
//            mIminPrintUtils.initPrinter(IminPrintUtils.PrintConnectType.SPI);
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.i("DSfer__", "Ererrr_" + e.getMessage());
                }
                mIminPrintUtils.setTextSize(22);
                mIminPrintUtils.setTextStyle(Typeface.BOLD);
                mIminPrintUtils.setTextLineSpacing(1.0f);

            }
        } catch (Exception e){
            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                    System.currentTimeMillis(), DBFunc.PurifyString("Err-RemarksMainActivity-"+Constraints.IMIN+"-"+e.getMessage()));
        }
        try {

        DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                System.currentTimeMillis(), DBFunc.PurifyString("Chk-ChkmIminPrintUtils-"+mIminPrintUtils));

        } catch (Exception e){
            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                    System.currentTimeMillis(), DBFunc.PurifyString("Err-ChkmIminPrintUtils-"+mIminPrintUtils+"-"+e.getMessage()));
        }
    }

    private static void DatabaseBackUpFun(Context mcontext) {
        try {
            PackageInfo pInfo = mcontext.getPackageManager().getPackageInfo(mcontext.getPackageName(), 0);
            //vercode = pInfo.versionCode;
            //txt_ver.setText(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 70, pInfo.versionName));
        } catch (Exception e) {
        }

        File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/posdata");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        Allocator.DataPath = dir.getAbsolutePath();
        Log.i("LoadDBFromInternal_","LoadDBFromInternal_____"+
                DBFunc.LoadDBFromInternal(mcontext));

        if (!DBFunc.LoadDBFromInternal(mcontext)) {
            //Toast.makeText(this, "General", Toast.LENGTH_LONG).show();
//            Log.i("LoadDBFromInternal_","DBFromInternal_____"+
//                    DBFunc.LoadDBFromInternal(mcontext));
//            Log.i("LoadDBFromInternal_","CreCreateResetDBl_____"+
//                    DBFunc.CreateResetDB(mcontext, true));
            if (DBFunc.CreateResetDB(mcontext, true)) {

                DBFunc.LoadDBFromInternal(mcontext);
                Log.i("LoadDBFromInternal","CreCreateResetDBl__enddd");
            } else {
                DialogBox dlg1 = new DialogBox(mcontext);
                dlg1.setDialogIconType(DialogBox.IconType.ERROR);
                //dlg1.setTitle("TEST");
                //dlg1.setMessage("TESTDSGF");
                dlg1.setButton1OnClickListener("ERROR", null);
                dlg1.show();
            }

        }
        if (!DBFunc.LoadTransactDBFromInternal(mcontext)) {
            //Toast.makeText(this, "TESTSDFDSF", Toast.LENGTH_LONG).show();
            if (DBFunc.CreateResetDB(mcontext, false)) {
                DBFunc.LoadTransactDBFromInternal(mcontext);
            } else {
                DialogBox dlg1 = new DialogBox(mcontext);
                dlg1.setDialogIconType(DialogBox.IconType.ERROR);
                dlg1.setTitle("ERROR");
                dlg1.setMessage("ERROR");
                dlg1.setButton1OnClickListener("ERROR", null);
                dlg1.show();

            }
        }
    }

    private static void saveConfigPaymentHostDefault(String check) {
        if (check.isEmpty()) {
            DBFunc.ExecQuery("INSERT INTO "+ Constraints.ConfigPaymentHost+" (Name,Activity,BankName,Activate) VALUES ('" + DBFunc.PurifyString("sg.com.mobileeftpos.paymentapplication") + "','" + DBFunc.PurifyString("sg.com.mobileeftpos.paymentapplication.activities.ECRActivity") + "' , '" + DBFunc.PurifyString("ocbc").toLowerCase().trim() + "', " + "1" + ")", true);

            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth, System.currentTimeMillis(), "ConfPayHost -> Add -> BankName: " + "ocbc");
        }
    }

    private static void saveReceiptEditorDefault(String check, Context mcontext) {
        if (check.isEmpty()) {
            Query.SaveReceiptEditor(mcontext,null,"Hello , Welcome !","Thank you , come again!",0,0);
        }
    }

    private static void saveTaxTypeDefault(String check) {
        String sqlChk = "SELECT Name FROM TaxType WHERE Name = 'None'";
        Cursor c = DBFunc.Query(sqlChk,true);
        if (c != null){
            if (c.getCount() == 0){

//                String truncateTb = "DELETE FROM TaxType WHERE Name = 'Inclusive'";
//                String truncateTb1 = "DELETE FROM TaxType WHERE Name = 'Exclusive'";
//                DBFunc.ExecQuery(truncateTb,true);
//                DBFunc.ExecQuery(truncateTb1,true);
//
//                SaveDataDefault_Table("TaxType","Name","Inclusive");
//                SaveDataDefault_Table("TaxType","Name","Exclusive");
                SaveDataDefault_Table("TaxType","Name","None");
//
//                DBFunc.ExecQuery("INSERT INTO TaxType (Name,DateTime) VALUES ('None'," +System.currentTimeMillis()+")", true);
//                DBFunc.ExecQuery("INSERT INTO TaxType (Name,DateTime) VALUES ('Inclusive'," +System.currentTimeMillis()+")", true);
//                DBFunc.ExecQuery("INSERT INTO TaxType (Name,DateTime) VALUES ('Exclusive'," +System.currentTimeMillis()+")", true);
            }
            c.close();
        }
        if (check.isEmpty()) {
            SaveDataDefault_Table("TaxType","Name","None");
            SaveDataDefault_Table("TaxType","Name","Inclusive");
            SaveDataDefault_Table("TaxType","Name","Exclusive");
////            DBFunc.ExecQuery("INSERT INTO TaxType (Name,DateTime) VALUES ('GST / VAT'," +System.currentTimeMillis()+")", true);
//            DBFunc.ExecQuery("INSERT INTO TaxType (Name,DateTime) VALUES ('None'," +System.currentTimeMillis()+")", true);
//            DBFunc.ExecQuery("INSERT INTO TaxType (Name,DateTime) VALUES ('Inclusive'," +System.currentTimeMillis()+")", true);
//            DBFunc.ExecQuery("INSERT INTO TaxType (Name,DateTime) VALUES ('Exclusive'," +System.currentTimeMillis()+")", true);
////            DBFunc.ExecQuery("INSERT INTO TaxType (Name,DateTime) VALUES ('CESS'," +System.currentTimeMillis()+")", true);
        }
    }

    private static void SaveDataDefault_Table(String tbName, String fieldName, String fieldValue) {
        String sqlChk = "SELECT "+fieldName+" FROM  "+tbName+" WHERE Name =" + "'"+fieldValue+"'";
        Cursor c = DBFunc.Query(sqlChk,true);
        if (c != null) {
            if (c.getCount() == 0) {
                DBFunc.ExecQuery("INSERT INTO "+tbName+" ("+fieldName+",DateTime) VALUES ('"+fieldValue+"'," +System.currentTimeMillis()+")", true);
            }
        }
    }

    private static void saveReportSettingsDefault(String check) {
        if (check.isEmpty()) {
            Query.SaveReportSettings("1","1","1","1","1","1","1","1","1");
        }
    }

    private static void savePaymentDefault(String check) {
        if (check.isEmpty()) {
            Query.SavePayment(DeclarationConf.PAYMENT_TYPE_CASH, "1", "0", "0", "0", "0.00", "0", "1", "0", "0", "0");
            Query.SavePayment(DeclarationConf.PAYMENT_TYPE_MASTER, "0", "1", "0", "0", "0.00", "0", "1", "0", "0", "0");
            Query.SavePayment(DeclarationConf.PAYMENT_TYPE_VISA, "0", "1", "0", "0", "0.00", "0", "1", "0", "0", "0");
            Query.SavePayment(DeclarationConf.PAYMENT_TYPE_NET, "1", "0", "0", "0", "0.00", "0", "1", "1", "0", "0");
        }
    }

    private static void SaveDataDefault(Context mcontext) {
        check = checkExistOrNot("Payment");
        savePaymentDefault(check);

        check = checkExistOrNot("ReportSettings");
        saveReportSettingsDefault(check);

        check = checkExistOrNot("TaxType");
        saveTaxTypeDefault(check);

        check = checkExistOrNot("ReceiptEditor");
        saveReceiptEditorDefault(check,mcontext);

        check = checkExistOrNot(Constraints.ConfigPaymentHost);
        saveConfigPaymentHostDefault(check);

        check = checkExistOrNot(Constraints.ConfigPaymentType);
        saveConfigPaymentTypeDefault(check);

        check = checkExistOrNot("Printer");
        savePrinterDefault(check);

        check = checkExistOrNot("POSSys");
        savePOSSysDefault(check);
    }

    private static void saveConfigPaymentTypeDefault(String check) {
        if (check.isEmpty()) {
            DBFunc.ExecQuery("INSERT INTO "+ Constraints.ConfigPaymentType+" (Name) VALUES ('"+ DeclarationConf.CARD_TYPE_OTHERS.toUpperCase()+"' )", true);
            DBFunc.ExecQuery("INSERT INTO "+ Constraints.ConfigPaymentType+" (Name) VALUES ('"+DeclarationConf.CARD_TYPE_WECHAT_PAY.toUpperCase()+"' )", true);
            DBFunc.ExecQuery("INSERT INTO "+ Constraints.ConfigPaymentType+" (Name) VALUES ('"+DeclarationConf.CARD_TYPE_GRAB_PAY.toUpperCase()+"' )", true);
            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth, System.currentTimeMillis(), "ConfPayType -> Add -> PaymentTypeName: " + "others");
        }
    }

    private static void savePrinterDefault(String check) {
        if (check.isEmpty()) {
            SortedMap charsets = Charset.availableCharsets();
            String ch_name = "";
            for (Iterator e = charsets.keySet().iterator(); e.hasNext(); ) {
                Charset charset = (Charset) charsets.get(e.next());

                if (Charset.isSupported(charset.name())) {
                    if (charset.name().equals("US-ASCII")) {
                        ch_name = charset.name();
                    }
                }
            }

            //spn_charset.setSelection(adapt_charset.getPosition("US-ASCII"));
            //default Add Printer
            final String fcharset1 = "" + ch_name;
            DBFunc.ExecQuery("INSERT INTO Printer (name,dflt,type,lfeed,lwidth,nodouble,param,charset) " +
                    "VALUES ('" + DBFunc.PurifyString("receipt_setting") + "'," + (0) + "," + 0 + "," + 5 + "," + 40 + "," + (0) + ",'" + DBFunc.PurifyString("") + "','" + DBFunc.PurifyString(fcharset1) + "')", true);

            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth, System.currentTimeMillis(), "Printer -> Add -> Name: " + "receipt_setting");

        }
    }

    private static void savePOSSysDefault(String check) {
        if (check.isEmpty()) {
            DBFunc.ExecQuery("DELETE FROM POSSys", true);
            Cursor chk_printer = null;
            String printer_name = "0";
            Cursor config_values_printer1 = DBFunc.Query("SELECT id FROM Printer where name = 'receipt_setting'", true);
            if (config_values_printer1 != null) {
                while (config_values_printer1.moveToNext()) {
                    printer_name = config_values_printer1.getString(0);

                }
            }

            String sql = "INSERT INTO POSSys (name,receipt_printer,retails_id,company_code,company_url,receipt_count," +
                    "hotkey_col,hotkey_row,bal_mode,bal_title, bill_fontsize, option,maplayout, refer_info1_name, " +
                    "refer_info2_name, refer_info3_name, roundtype, language) VALUES ('"
                    + DBFunc.PurifyString("pos")
                    + "','"

                    + Integer.parseInt(printer_name)
                    + "','"

                    + "0"
                    + "','"

                    + ""
                    + "','"

                    + ""
                    + "',"

                    + 1
                    + ","

                    + (3)
                    + ","

                    + (3)
                    + ","

                    + (0)
                    + ",'"

                    + 0
                    + "',"

                    //+ (8 + 10)
                    + (14)
                    + ",'" + DBFunc.PurifyString("00000000000000000000000000000") + "'";

            if (0 == 0) {
                sql += ",NULL";
            }

            if (0 == 0) {
                sql += ",NULL";
            }

            if (0 == 0) {
                sql += ",NULL";
            }
            if (0 == 0) {
                sql += ",NULL";
            }


            sql += "," + 0+"";

            sql += ", " + 0;

            sql += ")";

            //DBFunc.ExecQuery("DELETE FROM Printer", true);


            DBFunc.ExecQuery(sql, true);

            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth, System.currentTimeMillis(), "POSConfig -> Update");

            //printer_Save();
        }
    }


    private static String checkExistOrNot(String tblName) {
        check = "";
        config_values = DBFunc.Query("SELECT * FROM "+tblName, true);
        if (config_values != null) {
            while (config_values.moveToNext()) {
                check = config_values.getString(0);
            }
            config_values.close();
        }
        return check;
    }

    private static void CustomerDisplayFun(ActivityRemarkMainBinding binding) {
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
//                    200);
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
    }

    private static void SaveDeviceData(Context mcontext) {
        String MODEL = android.os.Build.MODEL; // N910
        String BOARD = android.os.Build.BOARD; // msm8909
        String BRAND = android.os.Build.BRAND; // qcom
        String DEVICE = android.os.Build.DEVICE; // msm8909
        String DISPLAY = android.os.Build.DISPLAY; // NewLand_N910-48f87c9f7a
        String PRODUCT = android.os.Build.PRODUCT; // msm8909
        String terminal_type = "";
        Log.i("MODEL__","MODEL__"+MODEL);
        Log.i("MODEL__","BOARD__"+BOARD);
        Log.i("MODEL__","BRAND__"+BRAND);
        Log.i("MODEL__","DEVICE__"+DEVICE);
        Log.i("MODEL__","DISPLAY__"+DISPLAY);
        Log.i("MODEL__","DISPLAY__"+DISPLAY);
        Log.i("MODEL__","PRODUCT__"+PRODUCT);

//        String str = "MODEL -"+MODEL+" "+"\n";
//         str += "BOARD -"+BOARD+" "+"\n";
//         str += "BRAND -"+BRAND+" "+"\n";
//         str += "DEVICE -"+DEVICE+" "+"\n";
//         str += "DISPLAY -"+DISPLAY+" "+"\n";
//         str += "PRODUCT -"+PRODUCT+" "+"\n";
//        final SweetAlertDialog pRefundDialog =   new SweetAlertDialog(mcontext, SweetAlertDialog.WARNING_TYPE)
//                //.setTitleText("Cancelled Bill")
//                .setContentText(str)
//                .setConfirmText(Constraints.OK)
//                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                    @Override
//                    public void onClick(SweetAlertDialog sDialog) {
//                        sDialog.dismissWithAnimation();
//                    }
//                }) ;
//        pRefundDialog.show();
//        pRefundDialog.setCancelable(false);

        if (BRAND.equals(Constraints.iMin)){
            BRAND = Constraints.ALPS;
        }
        if ((DISPLAY.split("_")[0]).toUpperCase().equals(Constraints.NewLand)){
            terminal_type = "NewLand";
            newLandPrinterInit(mcontext);
            newlandcustomer_display("0.00");
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
        }else if (BRAND.equals(Constraints.Verifone)){
//            DISPLAY_QKQ1.200407.002 release-keys
//            DEVICE_DX8000
//            BRAND_LANDI
//            BOARD_QC_Reference_Phone
//            PRODUCT_DX8000
            terminal_type = Constraints.Verifone;
            //terminal_model = modelVal;
        }
        Constraints.TERMINAL_TYPE = terminal_type;


        Query.SaveDeviceData(new DeviceData(0,MODEL,BOARD,BRAND,DEVICE,DISPLAY,PRODUCT,terminal_type.toUpperCase(),String.valueOf(vercode),licensekeyVal));
    }

    private static void newlandcustomer_display(String amt) {
        ExternalGuestDisplay mExGuestDiaplay = (ExternalGuestDisplay) deviceManager.getDevice().getExModule(ExModuleType.GUEST_DISPLAY);

        boolean bool = mExGuestDiaplay.showDigitalLed(amt);

    }

    private static void newLandPrinterInit(Context mcontext) {
        try {
            deviceManager = ConnUtils.getDeviceManager();
            deviceManager.init(mcontext, Constraints.K21_DRIVER_NAME, new NS3ConnParams(), new DeviceEventListener<ConnectionCloseEvent>() {

                @Override
                public void onEvent(ConnectionCloseEvent event, Handler handler) {
                    if (event.isSuccess()) {
                        //Log.i("TestingStart","isSuccess");
                    }
                    if (event.isFailed()) {
                        //Log.i("TestingStart","isFailed");
                    }
                }

                @Override
                public Handler getUIHandler() {
                    return null;
                }
            });

            deviceManager.connect();
            //Log.i("ISACtive","Devicee__"+deviceManager.getDevice().isAlive());
            deviceManager.getDevice().setBundle(new NS3ConnParams());

        } catch (Exception e1) {
            e1.printStackTrace();
            //Log.i("TestingStart","second_two"+e1);
        }
//        Printer getprinter = (Printer) deviceManager.getDevice().getStandardModule(ModuleType.COMMON_PRINTER);
//        Map<String, Bitmap> map = new HashMap<String, Bitmap>();
//        StringBuffer scriptBuffer = new StringBuffer();
//        scriptBuffer.append("*image l 370*80 path:");
//        scriptBuffer.append("logo\n");
//        scriptBuffer.append("!hz l\n !asc l\n !gray 5\n");
//        scriptBuffer.append("!NLPRNOVER\n");
//
//        Log.i("_getprinter", String.valueOf(getprinter.getStatus()));
//
//        PrinterResult printerResult = getprinter.printByScript(PrintContext.defaultContext(),
//                scriptBuffer.toString(), map, 60, TimeUnit.SECONDS);

        //printer=(com.newland.mtype.module.common.printer.Printer) deviceManager.getDevice().getStandardModule(ModuleType.COMMON_PRINTER);
        //deviceManager.getDevice().getInstance(context).getPrinter();
        //printer = (Printer) deviceManager.getDevice().getStandardModule(ModuleType.COMMON_PRINTER);
        //Log.i("dsfsafd_status", String.valueOf(printer.getStatus()));
        //printer.init();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.btn_login:
////                if (terminalTypeVal.equals("PAX")) {
//                    TxnReceiptGenerator.printReceipt();
////                }
//                break;
            case R.id.btn_login:
                    userid = binding.user.getText().toString();
                    userpassword = binding.password.getText().toString();
                    if (userid.equals("1111")){
//                        Intent intent = new Intent(this, SettingActivity.class);
////                      intent.putExtra("name", "SampleActivity");
//                        startActivity(intent);
//                        finish();
                        Intent intent = new Intent(this, MainActivity.class);
                        intent.putExtra("name", "SampleActivity");
                        startActivity(intent);
                        finish();
                       // PinLockView.mPin = "";
                    } else {
                        String accessable = Query.SearchUserAccess(Constraints.FL001,userid,userpassword);
                        if (accessable.equals("1")){
//                            loginSuccess = "1";
                            Intent intent = new Intent(this, MainActivity.class);
                            intent.putExtra("name", "SampleActivity");
                            startActivity(intent);
                            finish();
                        }else {
                            Query.DonothaveUserAccess(this);
                        }

                        //try {

//                            new AsyncTaskCheckLoginAccess(this, Constraints.LOGINSYSTEM, Constraints.Accessable) {
//                                @Override
//                                public void onResponseReceived(Object result) {
//                                    Log.i("result___","result_____"+result);
//                                    if (result.equals("1")) {
//                                    Intent intent = new Intent(mcontext, MainActivity.class);
//                                    intent.putExtra("name", "SampleActivity");
//                                    ((Activity)mcontext).startActivity(intent);
//                                    ((Activity)mcontext).finish();
//                                    PinLockView.mPin = "";
//                                } else {
//
//                                        Query.DonothaveUserAccess(mcontext);
//                                    }
//                                }
//                            }.execute();
//
////                        CallServiceTask(this).execute(request, url);
//                        //}
//                        //catch (Exception e){
//                        //    Query.DonothaveUserAccess(this);
//                        //}
                    }
                break;
        }
    }

//    private static abstract class AsyncTaskCheckLoginAccess extends AsyncTask<Object, Void, String> {
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
////        RestClient caller;
//
////        RestClient caller;
//
//        public abstract void onResponseReceived(Object result);
//
//        public AsyncTaskCheckLoginAccess(Context context,String strOrdering,String strAccessable) {
//            mcontext = context;
//            str_ordering = strOrdering;
//            str_accessable = strAccessable;
//        }
//
//        ProgressDialog pd = null;
////        Integer recount_ = 0;
//        protected String doInBackground(Object... params) {
//
////            str_accessable = SyncActivity.volleyCheckPermission(mcontext,str_ordering,str_accessable,syncRetailID,
////                    syncCompanyCode,syncUrl,download_retail_ID,download_company_code,download_url,download_userlogin,download_userpassword);
//
//            SyncActivity.volleyCheckPermission(mcontext,str_ordering,str_accessable);
////            if (recount_ == 0) {
////                recount_ ++;
////                SyncActivity.volleyCheckPermission(mcontext,str_ordering,str_accessable);
////            }
//            return str_accessable;
//        }
//
//        Integer count = 2;
//        @Override
//        protected void onPostExecute(String result) {
//
//            onResponseReceived(result);
////            Integer recount = 0;
//
////            Log.i("__","Constraints.Accessable_ee_"+count+"___"+recount+"___"+result);
////            if (result.equals("1")) {
////                Intent intent = new Intent(mcontext, MainActivity.class);
////                intent.putExtra("name", "SampleActivity");
////                ((Activity)mcontext).startActivity(intent);
////                ((Activity)mcontext).finish();
////                PinLockView.mPin = "";
////            } else {
//////                if (recount == 0) {
////                if (recount > 2) {
////                    recount ++;
////                    try {
////                        SyncActivity.volleyCheckPermission(mcontext,str_ordering,str_accessable);
////                        Log.i("sfd__str_accessable","str_accessable__"+str_accessable);
////                        if (str_accessable.equals("1")) {
////                            Intent intent = new Intent(mcontext, MainActivity.class);
////                            intent.putExtra("name", "SampleActivity");
////                            ((Activity)mcontext).startActivity(intent);
////                            ((Activity)mcontext).finish();
////                            PinLockView.mPin = "";
////                        }else {
////                            Query.DonothaveUserAccess(mcontext);
////                        }
////                    }catch (Exception e){
////                        Query.DonothaveUserAccess(mcontext);
////                    }
////
////                } else {
////                    Query.DonothaveUserAccess(mcontext);
////                }
//////                if (recount < count) {
//////                    recount ++;
//////
//////                    new AsyncTaskCheckLoginAccess(context,Constraints.LOGINSYSTEM,Constraints.Accessable).execute();
////////                    str_accessable = SyncActivity.volleyCheckPermission(mcontext,str_ordering,str_accessable,syncRetailID,
////////                            syncCompanyCode,syncUrl,download_retail_ID,download_company_code,download_url,download_userlogin,download_userpassword);
////////                    if (str_accessable.equals("1")) {
////////                        Intent intent = new Intent(mcontext, MainActivity.class);
////////                        intent.putExtra("name", "SampleActivity");
////////                        ((Activity)mcontext).startActivity(intent);
////////                        ((Activity)mcontext).finish();
////////                        PinLockView.mPin = "";
////////                    }
//////                }else {
////
//////                }
//            }
////        }
//
////        @Override
////        protected void onPreExecute() {
//////            Cursor Cursor_Possys = Query.GetURLAndCodeFromPossys();
//////            if (Cursor_Possys != null) {
//////                while (Cursor_Possys.moveToNext()) {
//////                    syncRetailID = Cursor_Possys.getString(0);
//////                    syncCompanyCode = Cursor_Possys.getString(1);
//////                    syncUrl = Cursor_Possys.getString(2);
//////                    download_retail_ID = Cursor_Possys.getString(3);
//////                    download_company_code = Cursor_Possys.getString(4);
//////                    download_url = Cursor_Possys.getString(5);
//////                    download_userlogin = Cursor_Possys.getString(13);
//////                    download_userpassword = Cursor_Possys.getString(14);
//////                }
//////                Cursor_Possys.close();
//////            }
////        }
//    }

}