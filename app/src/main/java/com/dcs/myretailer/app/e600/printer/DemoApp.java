package com.dcs.myretailer.app.e600.printer;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.pax.dal.IDAL;
import com.pax.neptunelite.api.NeptuneLiteUser;

public class DemoApp extends Application {
//    java.lang.ClassNotFoundException: Didn't find class "androidx.core.app.CoreComponentFactory" on path: DexPathList[[],nativeLibraryDirectories=[/product/priv-app/Launcher3QuickStep/lib/arm64, /data/resource/lib64, /system/lib64, /product/lib64, /data/resource/lib64, /system/lib64, /product/lib64]]
//    at dalvik.system.BaseDexClassLoader.findClass(BaseDexClassLoader.java:196)
    private static IDAL dal;
    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
        dal = getDal();
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

}
