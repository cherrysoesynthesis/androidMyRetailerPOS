package com.dcs.myretailer.app.Printer;

import static com.dcs.myretailer.app.Activity.RemarkMainActivity.mIminPrintUtils;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Build;

import com.dcs.myretailer.app.Activity.CashLayoutActivity;
import com.dcs.myretailer.app.Allocator;
import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.DeviceListAdapter;
import com.dcs.myretailer.app.Query.Query;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.imin.printerlib.IminPrintUtils;
import com.imin.printerlib.util.BluetoothUtil;

import java.io.IOException;
import java.util.List;

public class PrintingForIMIN {
    public PrintingForIMIN(Context mcontext, String receiptNo) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

            mIminPrintUtils = IminPrintUtils.getInstance(mcontext);
            DeviceListAdapter mAdapter = new DeviceListAdapter(mcontext);;
            List<BluetoothDevice> printerDevices = BluetoothUtil.getPairedDevices();
            mAdapter.clear();

            mAdapter.addAll(printerDevices);
            int bluetoothPosition = 0;
//                int bluetoothPosition = 1;
            BluetoothDevice device = mAdapter.getItem(bluetoothPosition);

            try {

                mIminPrintUtils.initPrinter(IminPrintUtils.PrintConnectType.BLUETOOTH, device);
                DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                        System.currentTimeMillis(), DBFunc.PurifyString("OK-CashLayoutActivity-ReceiptPrint-"
                                +mIminPrintUtils));

            } catch (IOException e) {
                e.printStackTrace();
                DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                        System.currentTimeMillis(), DBFunc.PurifyString("Err-CashLayoutActivity-ReceiptPrint-" +e.getMessage()+"-"+mIminPrintUtils));
            }
            mIminPrintUtils.setTextSize(22);
            mIminPrintUtils.setTextStyle(Typeface.BOLD);
            mIminPrintUtils.setTextLineSpacing(1.0f);

            mIminPrintUtils.setTextLineSpacing(1.0f);

            String chkBarcodeOnReceipt = Query.GetOptions(25);
            if (chkBarcodeOnReceipt.equals("1")) {
                String barcode_data = receiptNo;
                Bitmap barCode = GetReceiptNoBarCode(receiptNo);



                //assert mIminPrintUtils != null;
                assert mIminPrintUtils != null;
                mIminPrintUtils.printSingleBitmap(barCode);

                if (barCode != null) {
                    for (int i = 0 ; i < 4 ; i ++) {
                        Query.PrintingValueSetForIMIN(mIminPrintUtils, "", "", "",
                                new int[]{2, 0, 0},
                                new int[]{0, 1, 2}, new int[]{22, 22, 22});
                    }
                }
            }
        }
    }

    private static Bitmap GetReceiptNoBarCode(String ReceiptNo) {
        Bitmap barCode = null;
        // barcode image
        //ImageView iv = new ImageView(this);
        try {
            //barCode = encodeAsBitmap(barcode_data, BarcodeFormat.CODE_128, 300, 40);
            //barCode = encodeAsBitmap(ReceiptNo, BarcodeFormat.CODE_128, 460, 80);
            //barCode = encodeAsBitmap(ReceiptNo, BarcodeFormat.CODE_128, 600, 150);
            barCode = CashLayoutActivity.encodeAsBitmap(ReceiptNo, BarcodeFormat.CODE_128, 600, 150);
            // bitmap.getRowBytes();
            //iv.setImageBitmap(barCode);

        } catch (WriterException e) {
            e.printStackTrace();
        }
        return barCode;
    }

}
