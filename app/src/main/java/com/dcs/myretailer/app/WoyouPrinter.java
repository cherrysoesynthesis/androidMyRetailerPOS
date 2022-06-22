//package com.dcs.myretailer.app;
//
//
//import android.content.ComponentName;
//import android.content.Context;
//import android.content.Intent;
//import android.content.ServiceConnection;
//import android.os.IBinder;
//import android.os.RemoteException;
//
//public class WoyouPrinter {
//
//    private static final WoyouPrinter printer = new WoyouPrinter();
//    private IWoyouService woyouService = null;
//    private Context context;
//    private Intent intent;
//    private ServiceConnection connService = new ServiceConnection() {
//
//        @Override
//        public void onServiceDisconnected(ComponentName name) {
//            woyouService = null;
//        }
//        @Override
//        public void onServiceConnected(ComponentName name, IBinder service) {
//            woyouService = IWoyouService.Stub.asInterface(service);
//        }
//    };
//
//    private WoyouPrinter(){}
//
//
//    public static WoyouPrinter getInstance() {
//        return printer;
//    }
//
//    public void initPrinter(Context context){
//        intent=new Intent();
//        intent.setPackage("woyou.aidlservice.jiuiv5");
//        intent.setAction("IWoyouService");
//        this.context = context;
//        this.context.startService(intent);
//        this.context.bindService(intent, connService, Context.BIND_AUTO_CREATE);
//
//    }
//
//    public void disposePrinter(){
//        context.unbindService(connService);
//        context.stopService(intent);
//    }
//
//    public void PrintText(String msg, ICallback callback){
//        if(woyouService != null){
//            try {
//                woyouService.printText(msg, callback);
//
//            } catch (RemoteException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public void PrintRaw(byte[] data, ICallback callback){
//        if(woyouService != null){
//            try{
//                woyouService.sendRAWData(data, callback);
//            }catch(RemoteException e){
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public String GetPrinterSerial(){
//        String serial = "";
//        if(woyouService !=null){
//
//            try{
//                serial = woyouService.getPrinterSerialNo();
//            }catch(RemoteException e){}
//        }
//        return serial;
//    }
//}
//
