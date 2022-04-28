package com.dcs.myretailer.app.Printer;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.dcs.myretailer.app.Model.KitchenPrinterActivity;
import com.dcs.myretailer.app.Model.Result;
import com.epson.eposprint.Builder;

public class KitchenPrinter {
    public KitchenPrinter(Context mcontext, Resources resources, String billNo) {
            Log.i("beforeKitChenPrinterFun","During");
            Result result = new Result();
            Log.i("beforeKitChenPrinterFun","During1");
            Builder builder = KitchenPrinterActivity.createReceiptDataOrder(mcontext,result,resources,billNo);
            Log.i("beforeKitChenPrinterFun","During2");

            Log.i("beforeKitChenPrinterFun","During3");
    }
}
