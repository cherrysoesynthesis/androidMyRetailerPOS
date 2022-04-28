package com.dcs.myretailer.app.PaymentSendDataFormat;

import com.dcs.myretailer.app.Activity.CheckOutActivity;
import com.dcs.myretailer.app.Cashier.DeclarationConf;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;

public class CheckPaymentApp {

    public static String existOrNotApp(CheckOutActivity context, String packageName){
        String value = "0";
//        String terminalTypeVal = Query.GetDeviceData(Constraints.TERMINAL_TYPE);
        String device = Query.GetDeviceData(Constraints.DEVICE);
        boolean isAppInstalled = false;
        if (device.equals("M2-Max")) {
//            isAppInstalled = Query.appInstalledOrNotM2(context, packageName);
            value = "1";
        } else {

            isAppInstalled = Query.appInstalledOrNot(context, packageName);
            if (isAppInstalled) {
                value = "1";
            } else {
                value = "0";
            }
        }
//        if (isAppInstalled) {
//            value = "1";
//
//        } else {
//            value = "0";
//            // Query.SweetAlertWarningYesOnly(context.getApplicationContext(), Constraints.APP_NOT_INSTALLED,Constraints.OK);
//            // CheckOutActivity.SweetAlertWarningYesOnly(context, Constraints.APP_NOT_INSTALLED,Constraints.OK);
//        }
        return value;
    }
}
