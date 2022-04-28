package com.dcs.myretailer.app.Activity;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dcs.myretailer.app.R;
import com.google.zxing.Result;

import cn.pedant.SweetAlert.SweetAlertDialog;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanProductActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{
    private ZXingScannerView scannerView;
    private Button toManualButton;
    private ViewGroup scannerFrame;

    private final int PERMISSION_CAMERA = 1;
    Toolbar myToolbar;
    @RequiresApi(api = Build.VERSION_CODES.M)
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
    }

    public void scan(View view){
        myToolbar.setTitle("QR Code Scanner");
        scannerView = new ZXingScannerView(getApplicationContext());
        setContentView(scannerView);
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {
        Log.i("df__",result.getText());//'Twas brillig
        Toast.makeText(this, result.getText(), Toast.LENGTH_SHORT).show();
        scannerView.resumeCameraPreview(this);
        AddNewProductActivity.scan_qr_code = result.getText().replace("'","");
        AddNewProductActivity.binding.editTextProductCode.setText(result.getText().replace("'",""));
        AddNewProductActivity.St = "1";

//        Intent i = new Intent(ScanProductActivity.this,AddNewProductActivity.class);
//        startActivity(i);
        finish();
//        Intent i = new Intent(ScanProductActivity.this,AddNewProductActivity.class);
//        startActivity(i);
        //SuccessSweetMessageDialog(this);
    }
    private void SuccessSweetMessageDialog(final ScanProductActivity scanProductActivity) {
        new SweetAlertDialog(scanProductActivity)
                .setTitleText("Added!")
                .show();
    }
//19040501
//        745231
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
}
