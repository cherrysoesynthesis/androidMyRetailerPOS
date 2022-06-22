package com.dcs.myretailer.app.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dcs.myretailer.app.Cashier.MainActivity;
import com.dcs.myretailer.app.Cashier.ProductMainPageFragment;
import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.google.zxing.Result;

import java.util.ArrayList;
import java.util.Date;

import cn.pedant.SweetAlert.SweetAlertDialog;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{
    private ZXingScannerView scannerView;
    private Button toManualButton;
    private ViewGroup scannerFrame;

    private final int PERMISSION_CAMERA = 1;
    Toolbar myToolbar;
    Integer totalItems = 0;
    ArrayList<String> sldIDArr = new ArrayList<String>();
    ArrayList<Integer> sldQtyArr = new ArrayList<Integer>();
    ArrayList<String> sldNameArr = new ArrayList<String>();
    ArrayList<String> sltPriceTotalArr = new ArrayList<String>();
    ArrayList<String> sltBillDisArr = new ArrayList<String>();
    ArrayList<String> sltCategoryIDArr = new ArrayList<String>();
    ArrayList<String> sltCategoryNameArr = new ArrayList<String>();
    ArrayList<String> vchQueueNo = new ArrayList<String>();
    ArrayList<String> intTableNo = new ArrayList<String>();
    ArrayList<String> slddisID = new ArrayList<String>();
    ArrayList<String> slddisName = new ArrayList<String>();
    ArrayList<String> slddisTypeName = new ArrayList<String>();
    ArrayList<String> slddisType = new ArrayList<String>();
    ArrayList<String> slddisValue = new ArrayList<String>();
    @RequiresApi(api = Build.VERSION_CODES.M)
//    public static String scan_str = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle("QR Code Scanner");
        setSupportActionBar(myToolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        View scannerFrameView = (View) findViewById(R.id.scanner_frame);
        scan(scannerFrameView);

//        Intent intent = getIntent();
//        scan_str = intent.getStringExtra("scan");

//        scannerFrame = (ViewGroup) findViewById(R.id.scanner_frame);
//        toManualButton = (Button) findViewById(R.id.manual_entry);
//
//        scannerView = new ZXingScannerView(this) {
//            @Override
//            protected IViewFinder createViewFinderView(Context context) {
//                return new ViewFinderView(context);
//            }
//        };
//        scannerFrame.addView(scannerView);

//        toManualButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(view.getContext(), ItemDetailsActivity.class));
//            }
//        });

//        me.dm7.barcodescanner.core.ViewFinderView a = (me.dm7.barcodescanner.core.ViewFinderView) findViewById(R.id.viewfinder_view);
//        a.setAlpha();
//        Paint mFinderMaskPaint = new Paint();
//        mFinderMaskPaint.setColor(getResources().getColor(me.dm7.barcodescanner.core.R.color.viewfinder_mask));
//        me.dm7.barcodescanner.core.ViewFinderView canvas = (me.dm7.barcodescanner.core.ViewFinderView) findViewById(R.id.viewfinder_view);
//        int width = canvas.getWidth();
//        int height = canvas.getHeight();
//        Rect framingRect = getFramingRect();
//        RectF rectF = new RectF(framingRect.left, framingRect.top, framingRect.right, framingRect.bottom);
//        final Path path = new Path();
//        path.addRoundRect(rectF, 100f, 100f, Path.Direction.CW)
//        path.setFillType(Path.FillType.INVERSE_WINDING)
//        canvas.drawPath(path, mFinderMaskPaint);
//
//        if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
//            requestPermissions(new String[]{Manifest.permission.CAMERA}, 1);
//        } else {
//            Toast.makeText(this, "test1",Toast.LENGTH_LONG).show();
//            //Intent intent = new Intent("com.google.zxing.jar.client.android.SCANX");
//            Intent intent = new Intent("com.google.zxing.jar.client.android.SCAN");
//            //intent.putExtra("SCAN_FORMATS", "QR_CODE_MODE");
//            intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
//            //startActivityForResult(intent, IntentIntegrator.REQUEST_CODE);
//            startActivityForResult(intent, 0);
//            //finish();
//            //startActivityForResult(intent, 0);
//        }

//        try {
//            Intent intent = new Intent("com.google.zxing.jar.client.android.SCAN");
//            intent.putExtra("SCAN_MODE", "QR_CODE_MODE"); // "PRODUCT_MODE for bar codes
//
//            startActivityForResult(intent, 0);
//
//        } catch (Exception e) {
//
//            Uri marketUri = Uri.parse("market://details?id=com.google.zxing.jar.client.android");
//            Intent marketIntent = new Intent(Intent.ACTION_VIEW,marketUri);
//            startActivity(marketIntent);
//
//        }
    }

    public void scan(View view){
        myToolbar.setTitle("QR Code Scanner");
        scannerView = new ZXingScannerView(getApplicationContext());
        setContentView(scannerView);
        scannerView.setResultHandler(ScanActivity.this);
        scannerView.startCamera();
//        runOnUiThread(new Runnable() {
//
//            @Override
//            public void run() {
//                final Handler handler = new Handler();
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        //add your code here
//
//                        scannerView = new ZXingScannerView(getApplicationContext());
//                        setContentView(scannerView);
//                        scannerView.setResultHandler(ScanActivity.this);
//                        scannerView.startCamera();
//                    }
//                }, 1000);
//
//            }
//        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {
        Log.i("df_result_",result.getText());//'Twas brillig
        //e9b77a81-e761-11ea-8bc6-00155d01ca02
        //Toast.makeText(this, result.getText(), Toast.LENGTH_SHORT).show();
//        scannerView.resumeCameraPreview(this);

        //scannerView.stopCameraPreview();

        //String str1 = "Place QR Code within box to detect. Once scanned, remove from box and scan next item.";
        //ShowDialog(str1,Gravity.TOP,R.style.AlertDialogStyleScannerTop);

//        if (scan_str != null && scan_str.length() > 0) {
//            if (scan_str.equals("search")) {


                if (MainActivity.binding.pager.getCurrentItem() == 3) {
                    SearchOnlineBill(result);
                } else if (MainActivity.binding.pager.getCurrentItem() == 4) {
                    SearchOnlineBill(result);
                } else {
//                    String chkBarcode = Query.GetOptions(16);
//                    if(chkBarcode.equals("1")) {
//                        MainActivity.barCodeScannerDeviceFun(result.toString());
//                    }else {
                        AddToBill(result);
//                    }
//                    MainActivity.barCodeScannerDeviceFun(result.toString());
                }
//            }
////            else if (scan_str.equals("main")){
//
//                MainActivity.barCodeScannerDeviceFun(result.toString());
////            }
//        }

    }

    private void SearchOnlineBill(Result result) {
        String billNo = "";
        String sql = "SELECT TransNo FROM Bill " +
                "WHERE TransID = '"+result.getText().replace(" ","")+"'";
        Log.i("ScanActivity","sql_"+sql);
        Cursor c = DBFunc.Query(sql, false);
        if (c != null){
            if (c.moveToNext()){
                billNo = c.getString(0);

            }
            c.close();
        }
        Log.i("ScanActivity","sbillNo_"+billNo);
        if (billNo != null && billNo.length() > 1) {
            finish();
            Integer sale_id = Query.GetSalesByBillNo(billNo);
            if (sale_id > 0){
                Intent intent = new Intent(getApplicationContext(), TransactionDetailsActivity.class);
                intent.putExtra("BillNo", billNo);
                startActivity(intent);
                finish();
            }else {
                Intent intent = new Intent(getApplicationContext(), CheckOutActivity.class);
                intent.putExtra("BillNo", billNo);
                intent.putExtra("Status", "Bill");
                intent.putExtra("StatusSALES", "HOLD");
                startActivity(intent);
                finish();
            }
//
//            Intent intent = new Intent(mContext, CheckOutActivity.class);
//            intent.putExtra("BillNo",selectedID);
//            intent.putExtra("Status","Bill");
//            intent.putExtra("StatusSALES",status);
        }else {
            ErrorSweetMessageDialog(this,"Fail");
        }
    }

    private void AddToBill(Result result) {
        String sql = "SELECT Name,Price,Code,Image,ID,ProductCategoryID,ProductCategoryName " +
                "FROM PLU WHERE Code = '"+result.getText().replace("'","")+"'";
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
        Log.i("ScanAct","__getResulttt__"+result.getText().replace("'",""));
        Log.i("ScanAct","__gscanCode__"+scanCode);
        if (result.getText().replace("'","").equals(scanCode)){
            String dateFormat3 = CheckOutActivity.dateFormat55.format(new Date()).toString();
//            Query.saveDetailsBillProduct_AssignValue(MainActivity.BillID, MainActivity.strbillNo, "OFF",
//                    scanPLUID, scanPLUame, scanPLUPrice,
//                      "0", String.valueOf(scanPLUID), "0", "0",
//                    ENUM.SALES, "0", "0", "0", "0", "0");

            Integer qty = Query.GetQtyByBillNoAndProductId(MainActivity.strbillNo,scanPLUID);

            Query.saveDetailsBillProduct_AssignValue("scan",MainActivity.BillID, MainActivity.strbillNo, "OFF",
                    qty, scanPLUame, scanPLUPrice,
                      "0", String.valueOf(scanPLUID), "0", "0",
                    Constraints.SALES, "0", "0", "0", "0", "0",
                    0,"0","");

            Query.SaveBillPLU(MainActivity.BillID,MainActivity.strbillNo,
                    String.valueOf(scanPLUID),scanPLUame,String.valueOf(scanPLUID),qty,"","");

//            String str2 = "Item added to bill. You may proceed to scan next item if any.";
//            ShowDialog(str2,Gravity.BOTTOM,R.style.AlertDialogStyleScannerBottomSuccess);
            ProductMainPageFragment.status_on = "1";
            // finish();
            SuccessSweetMessageDialog(this);
        }else {
            String str2 = "Failed. Please try again or check if item have or not. ";
            ErrorSweetMessageDialog(this,str2);
            //String str2 = "Failed. Please try again or check if item has already been added to bill. If error persists, please contact support";
            //ShowDialog(str2,Gravity.BOTTOM,R.style.AlertDialogStyleScannerBottomFail);
        }
        //scannerView.resumeCameraPreview(this);
    }

    private void ErrorSweetMessageDialog(final ScanActivity scanActivity,String msg) {
//        new SweetAlertDialog(scanActivity, SweetAlertDialog.ERROR_TYPE)
//                .setTitleText("Oops...")
//                .setContentText(str2)
//                .show();
//        scannerView.resumeCameraPreview(scanActivity);
        new SweetAlertDialog(scanActivity, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Error!")
                .setContentText(msg)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
//                        sDialog
//                                .setTitleText("Added!")
//                                .setContentText("Item added to bill. You may proceed to scan next item if any.")
//                                .setConfirmText("OK")
//                                .setConfirmClickListener(null)
//                                .changeAlertType(SweetAlertDialog.ERROR_TYPE);
                        scannerView.resumeCameraPreview(scanActivity);
                        sDialog.dismiss();
                        //MainActivity.St = "1";
                    }
                })
                .show();
    }

    private void SuccessSweetMessageDialog(final ScanActivity scanActivity) {
        //ew SweetAlertDialog(scanActivity, SweetAlertDialog.WARNING_TYPE)
        new SweetAlertDialog(scanActivity, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("Success!")
                .setContentText("Item added to bill. You may proceed to scan next item if any.")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
//                        sDialog
//                                .setTitleText("Added!")
//                                .setContentText("Item added to bill. You may proceed to scan next item if any.")
//                                .setConfirmText("OK")
//                                .setConfirmClickListener(null)
//                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                        sDialog.dismiss();
                        scannerView.resumeCameraPreview(scanActivity);
                    }
                })
                .show();
    }

//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if (isVisibleToUser) {
//            scannerView.setResultHandler(this);
//            scannerView.startCamera();
//        }
//        else if(scannerView != null) {
//            scannerView.stopCamera();
//        }
//    }

    private void ShowDialog(String str, int top, int style) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this,style);
        //alert.setTitle("Final Result");
        alert.setMessage(str);
        AlertDialog dialog_card = alert.create();
        // dlgAlert.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // WindowManager.LayoutParams WMLP =
        dialog_card.getWindow().setGravity(top);
        if (top == Gravity.TOP) {
            dialog_card.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }
        dialog_card.show();
    }

    //    public synchronized Rect getFramingRect() {
//        if (framingRect == null) {
//            if (camera == null) {
//                return null;
//            }
//            Point screenResolution = configManager.getScreenResolution();
//            if (screenResolution == null) {
//                // Called early, before init even finished
//                return null;
//            }
//
//            // Code added to enable portrait mode
//            int width = MIN_FRAME_WIDTH;
//            int height = MIN_FRAME_HEIGHT;
//            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
//                int tmp = 7 * screenResolution.x / 8;
//                width = (tmp) < MIN_FRAME_WIDTH ? MIN_FRAME_WIDTH : (tmp);
//
//                tmp = 1 * screenResolution.y / 3;
//                height = (tmp) < MIN_FRAME_WIDTH ? MIN_FRAME_WIDTH : ((tmp) > MAX_FRAME_HEIGHT ? MAX_FRAME_HEIGHT : (tmp));
//                Log.d(TAG, "Customized code for portrait mode in getFramingRect executed (Piyush Merja) ");
//            }else{
//                // Original Code
//                width = findDesiredDimensionInRange(screenResolution.x, MIN_FRAME_WIDTH, MAX_FRAME_WIDTH);
//                height = findDesiredDimensionInRange(screenResolution.y, MIN_FRAME_HEIGHT, MAX_FRAME_HEIGHT);
//            }
//            // End
//
//            int leftOffset = (screenResolution.x - width) / 2;
//            int topOffset = (screenResolution.y - height) / 2;
//            framingRect = new Rect(leftOffset, topOffset, leftOffset + width, topOffset + height);
//            Log.d(TAG, "Calculated framing rect: " + framingRect);
//        }
//        return framingRect;
//    }

    private static int findDesiredDimensionInRange(int resolution, int hardMin, int hardMax) {
        int dim = 5 * resolution / 8; // Target 5/8 of each dimension
        if (dim < hardMin) {
            return hardMin;
        }
        if (dim > hardMax) {
            return hardMax;
        }
        return dim;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("requestCode_", String.valueOf(requestCode));
        if (requestCode == 0) {
            Log.i("requestCode_", String.valueOf(requestCode));
            Log.i("resultCode_", String.valueOf(resultCode));
            Log.i("data_", String.valueOf(data));
            if (resultCode == RESULT_OK) {
                //String contents = data.getStringExtra("SCAN_RESULT");
                String contents = data.getStringExtra("SCAN_FORMATS");
                Log.i("contents_", String.valueOf(contents));
            }
            if(resultCode == RESULT_CANCELED){
                //handle cancel
            }
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        MainPage();
    }

    private void MainPage() {
//        MainActivity.status_setting = "0";
//        MainActivity.St = "1";
            //MainActivity.St = "1";
//            Intent i = new Intent(ScanActivity.this, MainActivity.class);
//            i.putExtra("name", "CheckoutActivity");
//            startActivity(i);
        MainActivity.St = "1";
//        ProductMainPageFragment.St = "1";
//        RecyclerViewAdapter.St = "1";

        //ActivityCompat.finishAffinity(ScanActivity.this);
        Intent i = new Intent(ScanActivity.this,MainActivity.class);
        startActivity(i);
        finish();
    }
}
