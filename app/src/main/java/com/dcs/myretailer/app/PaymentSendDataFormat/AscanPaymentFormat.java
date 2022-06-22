package com.dcs.myretailer.app.PaymentSendDataFormat;

import android.content.Intent;
import android.os.Bundle;

import com.dcs.myretailer.app.Activity.CheckOutActivity;
import com.dcs.myretailer.app.Cashier.DeclarationConf;

public class AscanPaymentFormat {
    public static Intent ezlink(Bundle bundleApp, Intent intent, Double cardamount) {
        bundleApp.putString("Request", CheckOutActivity.sendSaleObject(String.format("%.2f", (cardamount * 100)), DeclarationConf.EVENT_EZLINK_SALE));
        intent.putExtras(bundleApp);
        //new SendData1().execute(launchIntent, 6699, false);
        return intent;
    }
}
