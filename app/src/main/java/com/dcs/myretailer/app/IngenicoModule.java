package com.dcs.myretailer.app;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.dcs.myretailer.app.ENUM.Constraints;
import com.usdk.apiservice.aidl.constants.RFDeviceName;
import com.usdk.apiservice.aidl.pinpad.DeviceName;

import dagger.Module;

@Module
public class IngenicoModule {
//    Application mApplication;
//
//    public IngenicoModule(Application application) {
//        mApplication = application;
//    }
//
//    @Provides
//    @Singleton
//    Application providesApplication() {
//        return mApplication;
//    }
    public IngenicoModule(Context context) {
        if (Build.MODEL.startsWith("AECR")) {
//            public static String PINPAD_DEVICE_NAME = DeviceName.IPP;
//            public static String RF_DEVICE_NAME = RFDeviceName.INNER;
//            DemoConfig.PINPAD_DEVICE_NAME = DeviceName.COM_EPP;
//            DemoConfig.RF_DEVICE_NAME = RFDeviceName.EXTERNAL;

            Constraints.PINPAD_DEVICE_NAME = DeviceName.COM_EPP;
            Constraints.RF_DEVICE_NAME = RFDeviceName.EXTERNAL;
        } else {
            Constraints.PINPAD_DEVICE_NAME = DeviceName.IPP;
            Constraints.RF_DEVICE_NAME = RFDeviceName.INNER;
        }
        DeviceHelper.me().init(context);
        DeviceHelper.me().bindService();

//        return true;
    }

////    @Singleton
////    @Provides
//    public UPrinter getModule() {
//        return DeviceHelper.me().getPrinter();
//    }
}
