package com.dcs.myretailer.app.Printer;

import android.content.Context;
import android.graphics.Color;
import android.os.RemoteException;
import android.util.Log;

import com.dcs.myretailer.app.Cashier.MainActivity;
import com.dcs.myretailer.app.DeviceHelper;
import com.usdk.apiservice.aidl.printer.ASCScale;
import com.usdk.apiservice.aidl.printer.ASCSize;
import com.usdk.apiservice.aidl.printer.AlignMode;
import com.usdk.apiservice.aidl.printer.OnPrintListener;
import com.usdk.apiservice.aidl.printer.UPrinter;

public class PrintingForIngenico {
    public PrintingForIngenico(Context mcontext,UPrinter printer,String str) {
        //startPrinter(1,mcontext);
        for (int printcount = 0; printcount < MainActivity.receiptCount; printcount++) {
            try {
                printer = DeviceHelper.me().getPrinter();
                startPrinter(1, mcontext, str, printer);
            } catch (RemoteException e) {
                e.printStackTrace();
                Log.i("SDFDSF__", "Df_" + "INGENICO_" + e.getMessage());
            }
        }
    }


    public static void startPrinter(final int curSheetNo, final Context context, String str,
                                    UPrinter printer) throws RemoteException {
//        if (curSheetNo > 1) {
//            return;
//        }
//        outputBlueText(">>> start print | sheetNo = " + curSheetNo);

        // 打印文本 print text
//        printer.addText(AlignMode.CENTER, "Sheet number " + curSheetNo);

        printer.setAscScale(ASCScale.SC1x1);
        printer.setAscSize(ASCSize.DOT24x12);
//        printer.addText(AlignMode.LEFT, "English:Scale1x1,Dot24x12");
        printer.addText(AlignMode.LEFT, str);

//        printer.setHzScale(HZScale.SC1x1);
//        printer.setHzSize(HZSize.DOT24x24);
//        printer.addText(AlignMode.LEFT, "chinese : scaling ratio 1x1, bitmap 24x24");
//
//        printer.setPrintFormat(PrintFormat.FORMAT_MOREDATAPROC, PrintFormat.VALUE_MOREDATAPROC_PRNTOEND);
//        printer.addText(AlignMode.LEFT, "Setting Print format for printing all the contents until the end!");
//        printer.setPrintFormat(PrintFormat.FORMAT_MOREDATAPROC, PrintFormat.VALUE_MOREDATAPROC_PRNONELINE);
//        printer.addText(AlignMode.LEFT, "Setting Print format for printing only one line, more than one line does not print!");
//
//        printer.setPrintFormat(PrintFormat.FORMAT_ZEROSPECSET, PrintFormat.VALUE_ZEROSPECSET_SPECIALZERO);
//        printer.addText(AlignMode.LEFT, "Zero print style: 0 ");
//        printer.setPrintFormat(PrintFormat.FORMAT_ZEROSPECSET, PrintFormat.VALUE_ZEROSPECSET_DEFAULTZERO);
//        printer.addText(AlignMode.LEFT, "Zero print style: 0 ");
//
//        // 打印行混合文本 Print mix text on the same line
//        List<Bundle> textBlockList = new ArrayList<Bundle>();
//        Bundle block1 = new Bundle();
//        block1.putString(PrinterData.TEXT, "Thank ");
//        textBlockList.add(block1);
//        Bundle block2 = new Bundle();
//        block2.putString(PrinterData.TEXT, "you ");
//        block2.putInt(PrinterData.ASC_SCALE, ASCScale.SC2x2);
//        block2.putInt(PrinterData.ASC_SIZE, ASCSize.DOT24x8);
//        textBlockList.add(block2);
//        Bundle block3 = new Bundle();
//        block3.putString(PrinterData.TEXT, "for using");
//        textBlockList.add(block3);
//        printer.addMixText(AlignMode.CENTER, textBlockList);
//
//        List<Bundle> textBlockList2 = new ArrayList<Bundle>();
//        block1.putInt(PrinterData.ALIGN_MODE, AlignMode.LEFT);
//        block2.putInt(PrinterData.ALIGN_MODE, AlignMode.RIGHT);
//        textBlockList2.add(block1);
//        textBlockList2.add(block2);
//        printer.addMixStyleText(textBlockList2);
//
//        printer.addQrCode(AlignMode.CENTER, 240, ECLevel.ECLEVEL_H, "www.landicorp.com");
//        printer.addBarCode(AlignMode.CENTER, 2, 48,  "1234567");
//
////        printer.addText(AlignMode.LEFT, ">>>>>>> addBmpImage ");
////        byte[] image = readAssetsFile(context, "logo.bmp");
////        printer.addBmpImage(0, FactorMode.BMP1X1, image);
//
////        printer.addText(AlignMode.LEFT, ">>>>>>> addBmpPath ");
////        FileUtil.copyFileToSD(getBaseContext(), Environment.getExternalStorageDirectory().getPath(), "barcode.bmp");
////        printer.addBmpPath(0, FactorMode.BMP1X1, Environment.getExternalStorageDirectory() + "/barcode.bmp");
//
//        printer.addText(AlignMode.LEFT, ">>>>>>> gray0, 5, 10");
//        for (int i = 0; i <= 10; i=i+5) {
//            printer.setPrnGray(i);
//            printer.addText(AlignMode.LEFT, i + "gray浓度测试");
//        }
//        printer.setPrnGray(3);
//        printer.feedLine(5);
//
        Log.i("drrrI_","onFinish_"+printer);
        printer.startPrint(new OnPrintListener.Stub() {
            @Override
            public void onFinish() throws RemoteException {
                int orange = Color.rgb(0xEF, 0xB3, 0x36);
//                outputText(orange,"=> onFinish | sheetNo = " + curSheetNo);
                //startPrinter(curSheetNo + 1,context);

                Log.i("drrrI_","onFinish");
            }

            @Override
            public void onError(int error) throws RemoteException {
//                outputRedText("=> onError: " + getErrorDetail(error));
                Log.i("ErrrI_","Err__"+error);
            }
        });
    }
}
