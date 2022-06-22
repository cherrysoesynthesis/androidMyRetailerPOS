package com.dcs.myretailer.app;

import static com.dcs.myretailer.app.Report.ReportActivity.appContext;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;

import com.dcs.myretailer.app.e600.printer.DemoApp;
import com.pax.commonlib.utils.LogUtils;
import com.pax.dal.IDAL;
import com.pax.dal.IPrinter;
import com.pax.neptunelite.api.NeptuneLiteUser;

public class PrinterUtil {
    private static final String TAG = "PrinterUtil";
    private static IDAL dal;

    private PrinterUtil() {
    }

    public int print(Bitmap bitmap) {
        return printBuiltIn(bitmap);
//        printBitmap(bitmap,true);
    }

    private static class LazyHolder {

        static final PrinterUtil INSTANCE = new PrinterUtil();

        private LazyHolder() {
            throw new IllegalStateException("Utility class");
        }

    }

    public static PrinterUtil getInstance() {
        return LazyHolder.INSTANCE;
    }

    public String getPrintResultStr(int printResult) {
        String printResultStr = "";
        if (printResult == 2) {
            printResultStr = "Printer out of paper ! ";
        } else if (printResult == 8) {
            printResultStr = "Printer overheated ! ";
        }
        return printResultStr;
    }
    public static IDAL getDal(){
        if(dal == null){
            try {
                long start = System.currentTimeMillis();
                dal = NeptuneLiteUser.getInstance().getDal(appContext);
                Log.i("Test","get dal cost:"+(System.currentTimeMillis() - start)+" ms");
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(appContext, "error occurred,DAL is null.", Toast.LENGTH_LONG).show();
            }
        }
        return dal;
    }

    //内置打印机打印方法
    private int printBuiltIn(Bitmap bitmap) {
        int result = -1;
        try {
            //IDAL dal = FinancialApplication.getDal();
            IDAL dal = getDal();
            if (dal == null) {
                return result;
            }
            IPrinter printer = dal.getPrinter();
            printer.init();
            //here you must not use print method with IPinterListener,
            // because it will execute print in another thread,
            // we use a single thread pool to execute all print task
            printer.printBitmap(bitmap);
            printer.setGray(3);
            result = printer.start();
            if (result == 0) {
                printer.cutPaper(0);
            }
            String printResultStr = getPrintResultStr(result);
            if (printResultStr != null && !printResultStr.equals("")) {
                printResultStr = printResultStr + "  result code : " + result;
               // Toast.makeText(FinancialApplication.getApp(), printResultStr, Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public synchronized void printBitmap(Bitmap bitmap, boolean cutPaper) {
//        IPrinter printer = FinancialApplication.getApp().getDal().getPrinter();
        IPrinter printer = DemoApp.getDal().getPrinter();
        IPrinter.IPinterListener listener = new IPrinter.IPinterListener() {
            @Override
            public void onSucc() {
                LogUtils.d(TAG, "=================onSucc===========");
            }

            @Override
            public void onError(int i) {
                LogUtils.d(TAG, "=================onError===========" + i);
            }
        };

        if (cutPaper) {
            printer.print(bitmap, 0, listener);
        } else {
            printer.print(bitmap, listener);
        }

//        while (true) {
//            if ((Device.checkBattery(FinancialApplication.getApp()) != Device.ChargeType.AC &&
//                    Device.checkBattery(FinancialApplication.getApp()) != Device.ChargeType.USB) &&
//                    !Utils.isScreenOrientationPortrait()) {
//                PrintListener.Status status = listener.onConfirm(null, FinancialApplication.getApp().getString(R.string.err_low_voltage));
//                if (status == Status.CANCEL) {
//                    return 0;
//                }
//            } else {
//                break;
//            }
//        }
//
//        int[] result = new int[1];
//        while (true) {
//            ConditionVariable cv = new ConditionVariable();
//            IPrinter printer = FinancialApplication.getApp().getDal().getPrinter();
//            IPrinter.IPinterListener listener = new IPrinter.IPinterListener() {
//                @Override
//                public void onSucc() {
//                    result[0] = 0;
//                    cv.open();
//                }
//
//                @Override
//                public void onError(int i) {
//                    if (i == 1) {
//                        result[0] = 0;
//                    } else if (handlePrintException(i)) {
//                        result[0] = 1;
//                    } else {
//                        result[0] = -1;
//                    }
//                    cv.open();
//                }
//            };
//
//            if (cutPaper) {
//                printer.print(bitmap, 0, listener);
//            } else {
//                printer.print(bitmap, listener);
//            }
//
//            cv.block();
//
//            if (result[0] != 1) {
//                break;
//            }
//        }
//
//        return result[0];
    }


}