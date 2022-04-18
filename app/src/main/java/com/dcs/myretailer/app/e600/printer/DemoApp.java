package com.dcs.myretailer.app.e600.printer;

import android.app.Application;
import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import com.dcs.myretailer.app.DeviceHelper;
import com.pax.dal.IDAL;
import com.pax.neptunelite.api.NeptuneLiteUser;
import com.usdk.apiservice.aidl.printer.AlignMode;
import com.usdk.apiservice.aidl.printer.OnPrintListener;
import com.usdk.apiservice.aidl.printer.UPrinter;

public class DemoApp extends Application implements DeviceHelper.ServiceReadyListener {
    //    java.lang.ClassNotFoundException: Didn't find class "androidx.core.app.CoreComponentFactory" on path: DexPathList[[],nativeLibraryDirectories=[/product/priv-app/Launcher3QuickStep/lib/arm64, /data/resource/lib64, /system/lib64, /product/lib64, /data/resource/lib64, /system/lib64, /product/lib64]]
//    at dalvik.system.BaseDexClassLoader.findClass(BaseDexClassLoader.java:196)
    private static IDAL dal;
    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
        dal = getDal();

        try {
            DeviceHelper.me().init(this);
            DeviceHelper.me().bindService();
            DeviceHelper.me().register(true);
            DeviceHelper.me().setServiceListener(this);
            UPrinter printer = DeviceHelper.me().getPrinter();
            printer.addText(AlignMode.LEFT, "fgdgfdgsfdgf");
            printer.startPrint(new OnPrintListener.Stub() {
                @Override
                public void onFinish() throws RemoteException {
                    Log.i("onFinish | sheetNo = " ,"curSheetNo");

                }

                @Override
                public void onError(int error) throws RemoteException {
                    Log.i("onerror = " ,String.valueOf(error));
                }
            });
        }catch (Exception e){
            Log.i("DeviceHelper",":"+(System.currentTimeMillis())+" ms"+e.getMessage());
        }
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

    @Override
    public void onReady(String version) {
        DeviceHelper.me().register(true);
    }
}
