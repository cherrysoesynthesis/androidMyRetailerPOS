package com.dcs.myretailer.app.Printer;

import com.dcs.myretailer.app.Activity.CashLayoutActivity;
import com.dcs.myretailer.app.DeviceHelper;

public class IngenicoInitialize {
    public IngenicoInitialize(CashLayoutActivity context){
        DeviceHelper.me().init(context);
        DeviceHelper.me().bindService();
        DeviceHelper.me().register(true);
    }
}
