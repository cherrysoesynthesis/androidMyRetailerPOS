package com.dcs.myretailer.app.Cashier;

import android.content.Intent;
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

import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.ScanActivity;
import com.google.zxing.Result;

import cn.pedant.SweetAlert.SweetAlertDialog;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class RemarkScanActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{
    private ZXingScannerView scannerView;
    private Button toManualButton;
    private ViewGroup scannerFrame;

    private final int PERMISSION_CAMERA = 1;
    Toolbar myToolbar;
    @RequiresApi(api = Build.VERSION_CODES.M)
//    public static String scan_str = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle("Item Remarks Scanner");
        setSupportActionBar(myToolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        View scannerFrameView = (View) findViewById(R.id.scanner_frame);
        scan(scannerFrameView);
    }

    public void scan(View view){
        myToolbar.setTitle("QR Code Scanner");
        scannerView = new ZXingScannerView(getApplicationContext());
        setContentView(scannerView);
        scannerView.setResultHandler(RemarkScanActivity.this);
        scannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {

        Log.i("df_result_",result.getText());//'Twas brillig

        //RecyclerViewAdapter.btn_cancel_pop_up_window_itemremarks.setText(result.getText());

        Intent i = new Intent(RemarkScanActivity.this,MainActivity.class);
        i.putExtra("name", "RemarkScanActivity");
        MainActivity.remarksScanVal = result.getText();
        startActivity(i);
        finish();
//        if (MainActivity.binding.pager.getCurrentItem() == 3) {
//            SearchOnlineBill(result);
//        } else if (MainActivity.binding.pager.getCurrentItem() == 4) {
//            SearchOnlineBill(result);
//        } else {
////                    String chkBarcode = Query.GetOptions(16);
////                    if(chkBarcode.equals("1")) {
////                        MainActivity.barCodeScannerDeviceFun(result.toString());
////                    }else {
//            AddToBill(result);
////                    }
////                    MainActivity.barCodeScannerDeviceFun(result.toString());
//        }
////            }
//////            else if (scan_str.equals("main")){
////
////                MainActivity.barCodeScannerDeviceFun(result.toString());
//////            }
////        }

    }

//    private void SearchOnlineBill(Result result) {
//        String billNo = "";
//        String sql = "SELECT TransNo FROM Bill " +
//                "WHERE TransID = '"+result.getText().replace(" ","")+"'";
//        Log.i("ScanActivity","sql_"+sql);
//        Cursor c = DBFunc.Query(sql, false);
//        if (c != null){
//            if (c.moveToNext()){
//                billNo = c.getString(0);
//
//            }
//            c.close();
//        }
//        Log.i("ScanActivity","sbillNo_"+billNo);
//        if (billNo != null && billNo.length() > 1) {
//            finish();
//            Integer sale_id = Query.GetSalesByBillNo(billNo);
//            if (sale_id > 0){
//                Intent intent = new Intent(getApplicationContext(), TransactionDetailsActivity.class);
//                intent.putExtra("BillNo", billNo);
//                startActivity(intent);
//                finish();
//            }else {
//                Intent intent = new Intent(getApplicationContext(), CheckOutActivity.class);
//                intent.putExtra("BillNo", billNo);
//                intent.putExtra("Status", "Bill");
//                intent.putExtra("StatusSALES", "HOLD");
//                startActivity(intent);
//                finish();
//            }
////
////            Intent intent = new Intent(mContext, CheckOutActivity.class);
////            intent.putExtra("BillNo",selectedID);
////            intent.putExtra("Status","Bill");
////            intent.putExtra("StatusSALES",status);
//        }else {
//            ErrorSweetMessageDialog(this,"Fail");
//        }
//    }



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

        if (requestCode == 0) {

            if (resultCode == RESULT_OK) {
                //String contents = data.getStringExtra("SCAN_RESULT");
                String contents = data.getStringExtra("SCAN_FORMATS");

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
//        super.onBackPressed();
////        MainActivity.status_setting = "0";
////        MainActivity.St = "1";
//        //MainActivity.St = "1";
////            Intent i = new Intent(ScanActivity.this, MainActivity.class);
////            i.putExtra("name", "CheckoutActivity");
////            startActivity(i);
//        MainActivity.St = "1";
////        ProductMainPageFragment.St = "1";
////        RecyclerViewAdapter.St = "1";
//
//        //ActivityCompat.finishAffinity(ScanActivity.this);
//        Intent i = new Intent(ScanActivity.this,MainActivity.class);
//        startActivity(i);
//        finish();
    }
}

