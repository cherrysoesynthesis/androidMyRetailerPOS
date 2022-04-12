package com.dcs.myretailer.app.KitchenPrinter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.dcs.myretailer.app.Checkout.CheckOutAdapter;
import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.Setting.SettingActivity;
import com.dcs.myretailer.app.databinding.ActivityKitchenPrinterBinding;
import com.epson.eposprint.Builder;
import com.epson.eposprint.EposException;
import com.epson.eposprint.Print;

import java.util.ArrayList;

public class KitchenPrinterActivity extends AppCompatActivity implements View.OnClickListener {
//    private static String openDeviceIP = "192.168.192.168";
    private static String openDeviceName = "192.168.192.168";
    private static int connectionType = Print.DEVTYPE_TCP;
    private static int printerModel = Builder.LANG_EN;
    private static String printerName = null;

//    private EditText editWarnings = null;
//    private Spinner spnNames = null;
//    private Spinner spnModels = null;

    public static ActivityKitchenPrinterBinding binding = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = DataBindingUtil.setContentView(this, R.layout.activity_kitchen_printer);

        int widthSZ = Query.screenSize(getApplicationContext(),"W");
        int heigthSZ = Query.screenSize(getApplicationContext(),"H");


//        LinearLayout.LayoutParams layKitchenPrinter = new LinearLayout.LayoutParams((int) ((widthSZ)),
//                android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
//        layKitchenPrinter.gravity = Gravity.CENTER;
//        binding.layKitchenPrinter.setLayoutParams(layKitchenPrinter);

        LinearLayout.LayoutParams paramsLayIPAddressSelect = new LinearLayout.LayoutParams((int) ((widthSZ)/10 *9),
                android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
        paramsLayIPAddressSelect.leftMargin = (int) ((widthSZ)/10 * 0.5); ;

        binding.LayBtn.setLayoutParams(paramsLayIPAddressSelect);
        binding.layKitchenPrinter.setLayoutParams(paramsLayIPAddressSelect);
//        binding.LayIPAddressSelect.setLayoutParams(paramsLayIPAddressSelect);

        LinearLayout.LayoutParams paramsbuttonDiscovery = new LinearLayout.LayoutParams((int) ((widthSZ)/10 *3),
                android.widget.Toolbar.LayoutParams.WRAP_CONTENT);
        paramsLayIPAddressSelect.topMargin = (int) ((widthSZ)/10 * 0.5);
        paramsLayIPAddressSelect.leftMargin = (int) ((widthSZ)/10 * 0.2);
        binding.buttonDiscovery.setLayoutParams(paramsbuttonDiscovery);

        printerName = getString(R.string.printername_m10);



        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        setTitle("Kitchen Printer Setup");

        myToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_close_white));
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



        KitchenPrinterModel kpm = Query.GetKitchenPrinter();
        openDeviceName = kpm.getOpenDeviceName();
        binding.editTextProductName1.setText(openDeviceName);

        binding.buttonDiscovery.setOnClickListener(this);
        binding.buttonPrint.setOnClickListener(this);
        ArrayAdapter<String> nameAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        nameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nameAdapter.add(getString(R.string.printername_m10));
        nameAdapter.add(getString(R.string.printername_p20));
        nameAdapter.add(getString(R.string.printername_p60));
        nameAdapter.add(getString(R.string.printername_p60ii));
        nameAdapter.add(getString(R.string.printername_p80));
        nameAdapter.add(getString(R.string.printername_t20));
        nameAdapter.add(getString(R.string.printername_t20ii));
        nameAdapter.add(getString(R.string.printername_t70));
        nameAdapter.add(getString(R.string.printername_t70ii));
        nameAdapter.add(getString(R.string.printername_t81ii));
        nameAdapter.add(getString(R.string.printername_t82));
        nameAdapter.add(getString(R.string.printername_t82ii));
        nameAdapter.add(getString(R.string.printername_t83ii));
        nameAdapter.add(getString(R.string.printername_t88v));
        nameAdapter.add(getString(R.string.printername_t90ii));
        nameAdapter.add(getString(R.string.printername_u220));
        nameAdapter.add(getString(R.string.printername_u330));
        binding.spinnerPrinter.setAdapter(nameAdapter);

        // init printer model list
        ArrayAdapter<SpnModelsItem> modelAdapter = new ArrayAdapter<SpnModelsItem>(this, android.R.layout.simple_spinner_item);
        modelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        modelAdapter.add(new SpnModelsItem(getString(R.string.model_ank), Builder.MODEL_ANK));
        modelAdapter.add(new SpnModelsItem(getString(R.string.model_japanese), Builder.MODEL_JAPANESE));
        modelAdapter.add(new SpnModelsItem(getString(R.string.model_chinese), Builder.MODEL_CHINESE));
        modelAdapter.add(new SpnModelsItem(getString(R.string.model_taiwan), Builder.MODEL_TAIWAN));
        modelAdapter.add(new SpnModelsItem(getString(R.string.model_korean), Builder.MODEL_KOREAN));
        modelAdapter.add(new SpnModelsItem(getString(R.string.model_thai), Builder.MODEL_THAI));
        modelAdapter.add(new SpnModelsItem(getString(R.string.model_southasia), Builder.MODEL_SOUTHASIA));
        binding.spinnerModel.setAdapter(modelAdapter);

        // init warning window
        binding.editWarnings.setFocusable(false);

    }

    @Override
    public void onBackPressed() {
        //ActivityCompat.finishAffinity(PaymentListActivity.this);
        Intent i = new Intent(this, SettingActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.button_discovery:

                //openDeviceName = binding.buttonDiscovery.getText().toString();
                //openDeviceName = binding.buttonDiscovery.getText().toString();
//                openDeviceIP = binding.buttonDiscovery.getText().toString();;
                intent = new Intent(this, DiscoverPrinterActivity.class);

                intent.putExtra("devtype", connectionType);
                intent.putExtra("ipaddress", openDeviceName);

                startActivityForResult(intent, 0);
                break;

            case R.id.button_print:
                Result result = new Result();
                Builder builder = createReceiptData(result,getResources());
                runPrintSequence(getApplicationContext(),getResources(),builder,result);
                break;

            default:
                // Do nothing
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


       // Toast.makeText(this, "data_" + data, Toast.LENGTH_SHORT).show();
        if (data != null) {

           // Toast.makeText(this, "data_" + resultCode, Toast.LENGTH_SHORT).show();
            if (resultCode == RESULT_OK) {
                connectionType = data.getIntExtra("devtype", 0);
                openDeviceName = data.getStringExtra("ipaddress");
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);


        outState.putString("openDeviceName", openDeviceName);
        outState.putInt("connectionType", connectionType);
        outState.putInt("language", printerModel);
        outState.putString("printerName", printerName);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        openDeviceName = savedInstanceState.getString("openDeviceName");
        connectionType = savedInstanceState.getInt("connectionType");
        printerModel = savedInstanceState.getInt("language");
        printerName = savedInstanceState.getString("printerName");
    }

    public static void runPrintSequence(Context context, Resources resources, Builder builder, Result result) {


    //private void runPrintSequence() {
//        Result result = new Result();
//        Builder builder = null;

        // Run print sequence of sample receipt
//        binding.editWarnings.setText("");
//        builder = createReceiptData(result,resources);

        if (result.getEposException() == null) {
            print(context,builder, result);
        }

        displayMsg(context,result);

        // Clear objects
        if (builder != null) {
            builder.clearCommandBuffer();
        }

        builder = null;
        result = null;

        return;
    }

//    private Builder createReceiptData(Result result) {
    public static Builder createReceiptData(Result result, Resources resources) {
        Builder builder = null;

        // Top logo data
        Bitmap logoData = BitmapFactory.decodeResource(resources, R.drawable.store);

        // Text buffer
        StringBuilder textData = new StringBuilder();

        // addBarcode API settings
        final int barcodeWidth = 2;
        final int barcodeHeight = 100;

        // Null check
        if (result == null) {
            return null;
        }

        // init result
        result.setPrinterStatus(0);
        result.setBatteryStatus(0);
        result.setEposException(null);
        result.setEpsonIoException(null);

//        // Get printer name and model
//        this.printerName = (String)this.spnNames.getSelectedItem();
//        this.printerModel = ((SpnModelsItem)this.spnModels.getSelectedItem()).getModelConstant();

        try {
            // Create builder
            if (printerName != null && printerModel !=-1 ){

                builder = new Builder(printerName, printerModel);

                // Set alignment to center
                builder.addTextAlign(Builder.ALIGN_CENTER);

//            // Add top logo to command buffer
//            builder.addImage(logoData, 0, 0,
//                    logoData.getWidth(),
//                    logoData.getHeight(),
//                    Builder.COLOR_1,
//                    Builder.MODE_MONO,
//                    Builder.HALFTONE_DITHER,
//                    Builder.PARAM_DEFAULT,
//                    getCompress(connectionType));
//
//            // Add receipt text to command buffer

                // Section 1 : Store information
                builder.addFeedLine(1);

                textData.append("THE STORE 1234 (555) 555-5555\n");
                textData.append("STORE DIRECTOR - John Smith\n");
                textData.append("\n");
                textData.append("7/01/07 16:58 6153 05 0191 134\n");
                textData.append("ST# 21 OP# 001 TE# 01 TR# 747\n");
                textData.append("------------------------------\n");

                builder.addText(textData.toString());
                textData.delete(0, textData.length());

                // Section 2 : Purchased items
                textData.append("2x Chicken Rice \n");
                textData.append("1x Fried Rice \n");
//            textData.append("104 3 CUP BLK TEAPOT    9.99 R\n");
//            textData.append("451 EMERIL GRIDDLE/PAN 17.99 R\n");
//            textData.append("389 CANDYMAKER ASSORT   4.99 R\n");
//            textData.append("740 TRIPOD              8.99 R\n");
//            textData.append("334 BLK LOGO PRNTED ZO  7.99 R\n");
//            textData.append("581 AQUA MICROTERRY SC  6.99 R\n");
//            textData.append("934 30L BLK FF DRESS   16.99 R\n");
//            textData.append("075 LEVITATING DESKTOP  7.99 R\n");
//            textData.append("412 **Blue Overprint P  2.99 R\n");
//            textData.append("762 REPOSE 4PCPM CHOC   5.49 R\n");
//            textData.append("613 WESTGATE BLACK 25  59.99 R\n");
                textData.append("------------------------------\n");

                builder.addText(textData.toString());
                textData.delete(0, textData.length());

//            // Section 3 : Payment information
//            textData.append("SUBTOTAL                160.38\n");
//            textData.append("TAX                      14.43\n");
//
//            builder.addText(textData.toString());
//            textData.delete(0, textData.length());
//
//            builder.addTextDouble(Builder.TRUE, Builder.TRUE);
//            builder.addText("TOTAL    174.81\n");
//            builder.addTextDouble(Builder.FALSE, Builder.FALSE);
//            builder.addFeedLine(1);
//
//            textData.append("CASH                    200.00\n");
//            textData.append("CHANGE                   25.19\n");
//            textData.append("------------------------------\n");
//
//            builder.addText(textData.toString());
//            textData.delete(0, textData.length());
//
//            // Section 4 : Advertisement
//            textData.append("TotalNumber of Items Purchased\n");
//            textData.append("Sign Up and Save !\n");
//            textData.append("With Preferred Saving Card\n");
//
//            builder.addText(textData.toString());
//            textData.delete(0, textData.length());
//
//            builder.addFeedLine(2);
//
//            // Add barcode data to command buffer
//            builder.addBarcode("01209457",
//                    Builder.BARCODE_CODE39,
//                    Builder.HRI_BELOW,
//                    Builder.FONT_A,
//                    barcodeWidth,
//                    barcodeHeight);

                // Add command to cut receipt to command buffer
                builder.addCut(Builder.CUT_FEED);
            }

        }
        catch (EposException e) {
            result.setEposException(e);
        }

        // Discard text buffer
        textData = null;

        return builder;
    }
//    private Builder createReceiptData(Result result) {
    public static Builder createReceiptDataOrder(Context context, Result result, Resources resources, String billNo) {
//      //  openDeviceName = "192.168.18.42";
//        Log.i("openDeviceName_","openDeviceName1_"+openDeviceName);
//        Log.i("connectionType_","connectionType1_"+connectionType);
//        Log.i("printerModel_","printerModel1_"+printerModel);
//        Log.i("printerName_","printerName1_"+printerName);
       // openDeviceName = "Printer Discovery / Select";
//        openDeviceName = "192.168.18.42";
//        connectionType = 0;
//        printerModel = 0;
//        printerName = "TM-m10";


        Log.i("sql____","sql__billNo__"+billNo);
        Builder builder = null;
        try {
            KitchenPrinterModel kpm = Query.GetKitchenPrinter();
            openDeviceName = kpm.getOpenDeviceName();
            connectionType = Integer.parseInt(kpm.getConnectionType());
            printerModel = Integer.parseInt(kpm.getPrinterModel());
            printerName = kpm.getPrinterName();

            // Top logo data
            Bitmap logoData = BitmapFactory.decodeResource(resources, R.drawable.store);

            // Text buffer
            StringBuilder textData = new StringBuilder();

            // addBarcode API settings
            final int barcodeWidth = 2;
            final int barcodeHeight = 100;

            // Null check
            if (result == null) {
                return null;
            }

            // init result
            result.setPrinterStatus(0);
            result.setBatteryStatus(0);
            result.setEposException(null);
            result.setEpsonIoException(null);

//        // Get printer name and model
//        this.printerName = (String)this.spnNames.getSelectedItem();
//        this.printerModel = ((SpnModelsItem)this.spnModels.getSelectedItem()).getModelConstant();

            try {
                // Create builder
                builder = new Builder(printerName, printerModel);

                // Set alignment to center
               // builder.addTextAlign(Builder.ALIGN_CENTER);
                builder.addTextAlign(Builder.ALIGN_LEFT);

//            // Add top logo to command buffer
//            builder.addImage(logoData, 0, 0,
//                    logoData.getWidth(),
//                    logoData.getHeight(),
//                    Builder.COLOR_1,
//                    Builder.MODE_MONO,
//                    Builder.HALFTONE_DITHER,
//                    Builder.PARAM_DEFAULT,
//                    getCompress(connectionType));
//
//            // Add receipt text to command buffer

                // Section 1 : Store information
                //builder.addFeedLine(1);

                Cursor c = null;

                String sql = "SELECT (ProductQty),ProductName," +
                        "Datetime,strftime('" + Constraints.sqldateformat_kitchenprinter_dmy + "', Datetime / 1000, 'unixepoch')," +
                        "ProductID,ID " +
//                    "vchQueueNo,QueueStatus,intTableNo " +
                        " FROM DetailsBillProduct WHERE BillNo = '" + billNo + "' order by ProductID ASC";
                Log.i("SDQL", "sql_" + sql);
                c = DBFunc.Query(sql, false);


//            textData.append("STORE DIRECTOR - John Smith\n");
//            textData.append("\n");
//            textData.append("7/01/07 16:58 6153 05 0191 134\n");
//            textData.append("ST# 21 OP# 001 TE# 01 TR# 747\n");


                // Section 2 : Purchased items
//            textData.append("2x Chicken Rice \n");
//            textData.append("1x Fried Rice \n");
                Integer totalQty = 0;
                if (c != null) {
//                    if (c.moveToNext()) {
//                        String dt = c.getString(3);
////                    String queueno = c.getString(4);
////                    String questatus = c.getString(4);
//                        textData.append("Date : " + dt + "\n\n");
//                        textData.append("===========KITCHEN SLIP============\n\n");
//                        textData.append("BillNo : " + billNo + "\n");
//                        textData.append("*****" + "DINEIN" + "*****\n");
//                        textData.append("---------------------------------------\n");
//                        builder.addText(textData.toString());
//                        textData.delete(0, textData.length());
////                    builder.addText(textData.toString());
//                    }
                    while (c.moveToNext()) {
                        int qty = c.getInt(0);
                       // String dt = c.getString(3);

                        totalQty += qty;
                        String name = c.getString(1);
                        Integer productId = c.getInt(4);
                        Integer billdetailsId = c.getInt(5);

                        String dtsales = Query.GetDtFromSales(billNo);

                        if (c.isFirst()) {
                            textData.append("Date : " + dtsales + "\n\n");
                            textData.append("=============KITCHEN SLIP==============\n\n");
                            textData.append("BillNo : " + billNo + "\n");
                            textData.append("*****" + "DINEIN" + "*****\n");
                            textData.append("------------------------------------------\n");
                            Log.i("SDFSF__","ttextData___"+textData.toString());
//                            textData.append(qty + "x " + name + "\n");
                            builder.addText(textData.toString());
                            textData.delete(0, textData.length());
//                            builder.addText(textData.toString());
//                            textData.delete(0, textData.length());
                        }
//yawmingyi housing - facility quarintee => wed
//                            airport =>
//                        airport mhar lar pot
//                                1 2
                        ArrayList ModiN = CheckOutAdapter.ModiFun(billNo, String.valueOf(productId),
                                "","",String.valueOf(qty),billdetailsId);
                        String text = "";

                        if (ModiN.size() > 0) {
                            for (int modiVar = 0; modiVar < ModiN.size(); modiVar++) {
                                //text += "<br/><font color='#a9aaad'>(" + ModiN.get(modiVar) + ")</font>";
                                text += "<font color='#a9aaad'>(" + ModiN.get(modiVar) + ")</font>";
                            }
                        }

                        //textData.delete(0, textData.length());

                        textData.append(qty + "x " + name + "\n");
                        textData.append(Html.fromHtml(text) + "\n");

                        if (c.isLast()) {

                            Log.i("SDFSF__","ttextData___"+textData.toString());
                            builder.addText(textData.toString());
                            textData.delete(0, textData.length());
                        }
                    }
                    c.close();
                }

                textData.delete(0, textData.length());
                textData.append("------------------------------------------\n");

                Log.i("SDFSF__","totalQty___"+totalQty);
                textData.append("Total Qty:" + totalQty + "\n");
                builder.addText(textData.toString());
                textData.delete(0, textData.length());

                // Add command to cut receipt to command buffer
                builder.addCut(Builder.CUT_FEED);

            } catch (EposException e) {
                result.setEposException(e);
            }

            // Discard text buffer
            textData = null;
            KitchenPrinterActivity.runPrintSequence(context, resources, builder, result);
        } catch (Exception e){
            Log.i("exception____e___","Msg___Err___"+e.getMessage());
        }
        return builder;
    }

    private static void print(Context context, Builder builder, Result result) {
        int printerStatus[] = new int[1];
        int batteryStatus[] = new int[1];
        boolean isBeginTransaction = false;

        // sendData API timeout setting (10000 msec)
        final int sendTimeout = 10000;
        Print printer = null;

        // Null check
        if ((builder == null) || (result == null)) {
            return;
        }

        // init result
        result.setPrinterStatus(0);
        result.setBatteryStatus(0);
        result.setEposException(null);
        result.setEpsonIoException(null);

        printer = new Print(context);

        try {
            // Open
            printer.openPrinter(
                    connectionType,
                    openDeviceName,
                    Print.FALSE,
                    Print.PARAM_DEFAULT,
                    Print.PARAM_DEFAULT);
        }
        catch (EposException e) {
            result.setEposException(e);
            return;
        }

        try {
            // Print data if printer is printable
            printer.getStatus(printerStatus, batteryStatus);
            result.setPrinterStatus(printerStatus[0]);
            result.setBatteryStatus(batteryStatus[0]);

            if (isPrintable(result)) {
                printerStatus[0] = 0;
                batteryStatus[0] = 0;

                printer.beginTransaction();
                isBeginTransaction = true;

                printer.sendData(builder, sendTimeout, printerStatus, batteryStatus);
                result.setPrinterStatus(printerStatus[0]);
                result.setBatteryStatus(batteryStatus[0]);
            }
        }
        catch (EposException e) {
            result.setEposException(e);
        }
        finally {
            if (isBeginTransaction) {
                try {
                    printer.endTransaction();
                }
                catch (EposException e) {
                    // Do nothing
                }
            }
        }

        try {
            printer.closePrinter();
        }
        catch (EposException e) {
            // Do nothing
        }

        return;
    }

    // Display error messages and warning messages
    private static void displayMsg(Context context,Result result) {
        if (result == null) {
            return;
        }

        String errorMsg = MsgMaker.makeErrorMessage(context, result);
        String warningMsg = MsgMaker.makeWarningMessage(context, result);

        if (!errorMsg.isEmpty()) {
            ShowMsg.show(context, errorMsg);
        }

        if (!warningMsg.isEmpty()) {
//            binding.editWarnings.setText(warningMsg);
        }

        return;
    }

    // Determine whether printer is printable
    private static boolean isPrintable(Result result) {
        if (result == null) {
            return false;
        }

        int status = result.getPrinterStatus();
        if ((status & Print.ST_OFF_LINE) == Print.ST_OFF_LINE) {
            return false;
        }

        if ((status & Print.ST_NO_RESPONSE) == Print.ST_NO_RESPONSE) {
            return false;
        }

        return true;
    }

    // Get Compress parameter of addImage API
    private int getCompress(int connection) {
        if (connection == Print.DEVTYPE_BLUETOOTH) {
            return Builder.COMPRESS_DEFLATE;
        }
        else {
            return Builder.COMPRESS_NONE;
        }
    }

}