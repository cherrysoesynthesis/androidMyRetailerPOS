package com.dcs.myretailer.app.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dcs.myretailer.app.Cashier.RecyclerViewAdapter;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.Report.ReportActivity;

public class RemarksBarcodeActivity extends Activity implements View.OnClickListener {
    EditText editext;
    Button addbtn;
    Intent getIntent;
    Bundle bundleName;
    String billno = "";
    String productID = "";
    String openPriceVar = "";
    String titleVar = "";
    RecyclerViewAdapter.MyViewHolder holder ;
    String status = "";
    Integer position_val = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remarks_barcode);


        getIntent = getIntent();
        bundleName = getIntent.getExtras();

        billno = bundleName.getString("billno");
        productID = bundleName.getString("productID");
        openPriceVar = bundleName.getString("openPriceVar");
        titleVar = bundleName.getString("titleVar");
        holder = bundleName.getParcelable("holder");
        status = bundleName.getString("status");
        position_val = bundleName.getInt("position_val");

        editext = (EditText) findViewById(R.id.editext);
        addbtn = (Button) findViewById(R.id.add);

        addbtn.setOnClickListener(this);


    }

//    @Override
//    public boolean dispatchKeyEvent(KeyEvent e) {
////            Intent intent_scan = new Intent(getApplicationContext(), ScanActivity.class);
//////            intent_scan.putExtra("scan_value","main");
////            startActivity(intent_scan);
////            finish();
////        if (e.getKeyCode() == KeyEvent.KEYCODE_SPACE && e.getAction() == KeyEvent.ACTION_UP) {
//
////        if(e.getAction()==KeyEvent.ACTION_DOWN){
//        //Log.i(TAG,"dispatchKeyEvent: "+e.toString());
//
//        //RecyclerViewAdapter.remarkScanVar = "RemarkBarcodeScan";
//
////        KeyEvent event = new KeyEvent(0, 0, 0, KeyEvent.KEYCODE_DEL, 0, 0,
////                0, 0, KeyEvent.KEYCODE_ENDCALL);
//
////            char pressedKey = (char) e.getUnicodeChar();
////             barcode += pressedKey;
//////             Log.i("barcode____","barcode____"+barcode);
////            String remmmarks = RecyclerViewAdapter.pop_up_window_item_remarks.getText().toString() ;
////             if (remmmarks != null && remmmarks.trim().length() > 12){
////                 Log.i("remmmarks___","remmmarks____"+remmmarks);
////                //String barcodeeefirst =  barcode.substring(6,8);
////                //String barcodeeesecond =  barcode.substring(11,11);
////                String barcodeee =  remmmarks.substring(6,8) + barcode.substring(11,12);
////                 Log.i("barcodeee____","barcodeee____"+barcodeee);
////                 RecyclerViewAdapter.pop_up_window_item_remarks.setText(barcodeee);
////                 Log.i("barcodeee____","barcodeeeeee____"+RecyclerViewAdapter.pop_up_window_item_remarks.getText().toString());
////             }
//        //barcode = edittext_barcode.getText().toString();
////            final Handler handler = new Handler(Looper.getMainLooper());
////            handler.postDelayed(new Runnable() {
////                @Override
////                public void run() {
////                    //Do something after 100ms
////            Log.i("Dfdf___","__barcode__"+barcode+"__"+barcode.length());
////                    final Handler handler = new Handler();
////                    handler.postDelayed(new Runnable() {
////                        @Override
////                        public void run() {
//
//        //if (barcode.length() > 0) {
//
//
////            Log.i("Dfdf___","__e.getKeyCode__"+barcode+"_____"+"_|_"
////                    +e.getCharacters()+"_____"+"_|_"+e.getKeyCode()+"_|_"+KeyEvent.KEYCODE_ENTER);
//////            + e.getCharacters().lastIndexOf(e.getKeyCode())
////            if(e.getKeyCode() == KeyEvent.KEYCODE_ENTER){
////                Log.i("DF__","dfd_getKeyCode_"+e.getKeyCode());
////
////                Log.i("DF__","dfd_KEYCODE_ENTER_"+KeyEvent.KEYCODE_ENTER);
//
//            String barcode = editext.getText().toString();
//
////            editext.setFilters(new InputFilter[] {
////                    new InputFilter() {
////                        public CharSequence filter(CharSequence src, int start,
////                                                   int end, Spanned dst, int dstart, int dend) {
////                            if(src.equals("")){ // for backspace
////                                return src;
////                            }
////                            if(src.toString().matches("[a-zA-Z ]+")){
////                                return src;
////                            }
////                            return editext.getText().toString();
////                        }
////                    }
////            });
//
//            if (barcode != null && barcode.trim().length() > 17){
//                editext.setText("");
//                String aaaa = barcode.substring(0,1);
//                Log.i("aaaa___","aaaa______"+aaaa);
////                if (aaaa.startsWith("G")) {
//                if (aaaa.startsWith("A") || aaaa.startsWith("B") || aaaa.startsWith("C") || aaaa.startsWith("D") || aaaa.startsWith("E") ||
//                        aaaa.startsWith("F") || aaaa.startsWith("G") || aaaa.startsWith("H") || aaaa.startsWith("I") || aaaa.startsWith("J") ||
//                        aaaa.startsWith("K") || aaaa.startsWith("L") || aaaa.startsWith("M") || aaaa.startsWith("N") || aaaa.startsWith("O") || aaaa.startsWith("P") ||
//                        aaaa.startsWith("Q") || aaaa.startsWith("R") || aaaa.startsWith("S") || aaaa.startsWith("T") || aaaa.startsWith("U") ||
//                        aaaa.startsWith("V") || aaaa.startsWith("W") || aaaa.startsWith("X") || aaaa.startsWith("Y") || aaaa.startsWith("Z")) {
//                    Log.i("aaaa___","aaaa______if"+aaaa);
//                    editext.setText(barcode.substring(5, 9));
//                    editext.setTextColor(Color.BLACK);
//                } else {
//                    Log.i("aaaa___","aaaa______else"+aaaa);
//                }
//            }
//
//            Log.i("barcode___","barcode__"+barcode);
//            Log.i("barcode___","barcode__"+editext.getText().toString());
//           // barCodeScannerDeviceFun(barcode);
//
//        return super.dispatchKeyEvent(e);
//    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add:
                Log.i("barcode___","barcode__"+editext.getText().toString().trim());
                Log.i("barcode___","barcode__"+billno);
                Log.i("barcode___","barcode__"+productID);
                Log.i("barcode___","barcode__"+openPriceVar);
                Log.i("barcode___","barcode__"+titleVar);
                Log.i("barcode___","barcode__"+status);
                Log.i("barcode___","barcode__"+RecyclerViewAdapter._position_val);
                Log.i("barcode___","barcode__"+RecyclerViewAdapter._holder);
                RecyclerViewAdapter.updateAndRefreshRemarks(editext.getText().toString().trim(),billno,productID,openPriceVar,titleVar,
                        RecyclerViewAdapter._holder,RecyclerViewAdapter._position_val,status,"1","0");

                Intent report_setting = new Intent(getApplicationContext(), ReportActivity.class);
                startActivity(report_setting);
                finish();
                break;
        }
    }
}