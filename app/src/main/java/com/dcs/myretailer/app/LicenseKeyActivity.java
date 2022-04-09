package com.dcs.myretailer.app;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.Report.SerialChecker;
import com.dcs.myretailer.app.Setting.StrTextConst;
import com.dcs.myretailer.app.databinding.ActivityLicenseKeyBinding;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

public class LicenseKeyActivity extends AppCompatActivity {
    ActivityLicenseKeyBinding binding = null;
    private static final int MY_CAMERA_REQUEST_CODE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_license_key);


        try {
            PermissionRequestFun();

        } catch (Exception e){
            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                    System.currentTimeMillis(), DBFunc.PurifyString("Err-RemarksMainActivity-PermissionRequestFun"+e.getMessage()));


        }

        try {
            requestStoragePermission();
        } catch (Exception e){
            Log.i("Err_LicenseKeyFun_","Err3_"+e.getMessage());
            DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                    System.currentTimeMillis(), DBFunc.PurifyString("Err-RemarksMainActivity-requestStoragePermission"+e.getMessage()));


        }

//         try {
//             LicenseKeyFun();
//         }catch (Exception e){
//             Log.i("Err_LicenseKeyFun_","Err_"+e.getMessage());
//             DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
//                     System.currentTimeMillis(),  "Err_LicenseKeyFun_"+"Err_"+e.getMessage());
//
//         }


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
            dlg.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 34), new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("PIXIEPOS_ACTIVATOR", codetxt);
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(v.getContext(), StrTextConst.GetText(StrTextConst.TextType.GENERAL, 33), Toast.LENGTH_SHORT).show();
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
                    Intent i = new Intent(LicenseKeyActivity.this, RemarkMainActivity.class);
                    //i.putExtra("licenseKey",et.getText().toString().toUpperCase());
                    i.putExtra("licenseKey",et.getText().toString().toUpperCase());
                    startActivity(i);
                    finish();
//                    try {
//                        int[] key = SerialChecker.DeGenSerial(et.getText().toString());
//
//                       // if (Arrays.equals(key, code)) {
//                        if ("1".equals("1")) {
//                            File file = new File(v.getContext().getFilesDir(), "info.dat");
//                            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
//                            writer.write(et.getText().toString().toUpperCase());
//                            writer.flush();
//                            writer.close();
//                            DialogBox dlg1 = new DialogBox(v.getContext());
//                            dlg1.setDialogIconType(DialogBox.IconType.INFORMATION);
//                            dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 30));
//                            dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 36));
//                            dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
//                            dlg1.show();
//                            dlg.dismiss();
//
//                            Intent i = new Intent(LicenseKeyActivity.this, RemarkMainActivity.class);
//                            i.putExtra("licenseKey",et.getText().toString().toUpperCase());
//                            startActivity(i);
//                            finish();
//
//
//                            //binding.overall.setAlpha(1);
//                        } else {
//                            DialogBox dlg1 = new DialogBox(v.getContext());
//                            dlg1.setDialogIconType(DialogBox.IconType.ERROR);
//                            dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 30));
//                            dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 37));
//                            dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
//                            dlg1.show();
//                            binding.overall.setAlpha(1);
//                            return;
//                        }
//                    } catch (Exception e) {
//                        //binding.overall.setAlpha(1);
//                        Logger.WriteLog("ActivityPosMenu",e.toString());
//                        DialogBox dlg1 = new DialogBox(v.getContext());
//                        dlg1.setDialogIconType(DialogBox.IconType.ERROR);
//                        dlg1.setTitle(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 30));
//                        dlg1.setMessage(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 37));
//                        dlg1.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.GENERAL, 0), null);
//                        dlg1.show();
//                    }
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
            Intent i = new Intent(LicenseKeyActivity.this, RemarkMainActivity.class);
            startActivity(i);
            finish();
        }
    }

    private void PermissionRequestFun() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
            }
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

    private void requestStoragePermission() {
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
                            try {
                                LicenseKeyFun();
                            }catch (Exception e){
                                Log.i("Err_LicenseKeyFun_","Err_"+e.getMessage());
                                DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                                        System.currentTimeMillis(),  "Err_LicenseKeyFun_"+"Err_"+e.getMessage());

                            }

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
}