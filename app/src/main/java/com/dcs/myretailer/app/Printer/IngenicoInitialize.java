package com.dcs.myretailer.app.Printer;

import android.util.Log;

import com.dcs.myretailer.app.Activity.CashLayoutActivity;
import com.dcs.myretailer.app.DeviceHelper;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;

public class IngenicoInitialize {
    public IngenicoInitialize(CashLayoutActivity context) {

        DeviceHelper.me().init(context);
        DeviceHelper.me().bindService();
        DeviceHelper.me().register(true);
    }
}
