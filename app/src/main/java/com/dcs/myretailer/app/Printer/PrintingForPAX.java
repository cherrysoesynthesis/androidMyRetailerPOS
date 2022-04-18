package com.dcs.myretailer.app.Printer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.dcs.myretailer.app.Activity.CashLayoutActivity;
import com.dcs.myretailer.app.Cashier.MainActivity;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.ReceiptData;
import com.dcs.myretailer.app.TxnReceiptGenerator;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;

public class PrintingForPAX {
    public PrintingForPAX(String terminaltype_check, Bitmap bitmap__,
                          ReceiptData receiptDataJson, Bitmap bitmap_qr_shoptima, String ReceiptNo) {
        // dal = getDal(mcontext);
//                PrinterTester printer_tester = PrinterTester.getInstance();
        //getDal(mcontext);
        try {
            Log.i("terminaltype_check_","terminaltype_check___printer_tester_"+terminaltype_check);
//                    printer_tester.init();
//                    printer_tester.setGray(3);
//                    printer_tester.setGray(5);

//                    printer_tester.setGray(10);
//                    printer_tester.s
        }catch (Exception e){
            Log.i("exception__","error__"+e.getMessage());
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;

        try {
            if (bitmap__ != null || !bitmap__.equals("0") || !bitmap__.equals("null")) {
//                        printer_tester.printBitmap(Bitmap.createScaledBitmap(bitmap__, 360, 180, false));
                receiptDataJson.setScaledBitmap(bitmap__);
            }
        } catch (NullPointerException e) {
            Log.i("NullPointerExceptione_", String.valueOf(e.getMessage()));
        }
//            Log.i("terminaltype_check_","terminaltype_check___str_"+str);
        // printer_tester.printStr(str, null);




        //printer_tester.cutPaper(5);
        //Log.i("chk_pos_qr_code_","chk_pos_qr_code____"+String.valueOf(MainActivity.chk_pos_qr_code_));
        if (MainActivity.chk_pos_qr_code_.equals(true)) {
            if (bitmap_qr_shoptima != null) {
                //printer_tester.printBitmap(bitmap_qr_shoptima);
                receiptDataJson.setShoptima(bitmap_qr_shoptima);
            }
        }
        String chkBarcodeOnReceipt = Query.GetOptions(25);
        if (chkBarcodeOnReceipt.equals("1")) {
            String barcode_data = ReceiptNo;
            Bitmap barCode = GetReceiptNoBarCode(ReceiptNo);


            try {
                if (barCode != null || !barCode.equals("0") || !barCode.equals("null")) {
                    //printer_tester.printBitmap(Bitmap.createScaledBitmap(barCode, 300, 40, false));
                    receiptDataJson.setReceiptNoBarCode(barCode);
                    //printer_tester.printBitmap(barCode);
                    // printer_tester.printStr("\n"+"\n"+"\n", null);
                }
            } catch (NullPointerException e) {
                Log.i("Nuone_barCode", String.valueOf(e.getMessage()));
            }
        }

//                printer_tester.fontSet();

        // printer_tester.fontSet((EFontTypeAscii) EFontTypeAscii.FONT_16_32, (EFontTypeExtCode) EFontTypeExtCode.FONT_16_32);
//                printer_tester.fontSet((EFontTypeAscii) EFontTypeAscii.FONT_8_16, (EFontTypeExtCode) EFontTypeExtCode.FONT_16_16);

        for (int printcount = 0; printcount < MainActivity.receiptCount; printcount++) {
//                    final String status1 = printer_tester.start();
            TxnReceiptGenerator.printReceipt(receiptDataJson);
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
