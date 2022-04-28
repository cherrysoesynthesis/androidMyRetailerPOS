//package com.dcs.myretailer.app;
//
//
//import android.app.Activity;
//import android.bluetooth.BluetoothAdapter;
//import android.bluetooth.BluetoothDevice;
//import android.bluetooth.BluetoothSocket;
//import android.hardware.usb.UsbDevice;
//import android.os.RemoteException;
//import android.view.View;
//import android.widget.Toast;
//
//import com.dcs.myretailer.app.Setting.StrTextConst;
//import com.dcs.myretailer.app.Setting.USBHelper;
//import com.dcs.myretailer.app.Setting.USBPort;
//
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.net.InetSocketAddress;
//import java.net.Socket;
//import java.net.UnknownHostException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Set;
//import java.util.UUID;
//
//public class PrintSpooler extends Thread{
//    private Activity active;
//    private WoyouPrinter printer;
//    private ICallback callback;
//    private boolean start = false;
//    private boolean pause = false;
//    private Thread printThread = null;
//    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm.ss");
//
//    List<DocumentJob> documentList = new ArrayList<DocumentJob>();
//    HashMap<Integer,PrinterParam> printers = new HashMap<Integer,PrinterParam>();
//    int totaljob = 0;
//    public PrintSpooler(Activity activity, HashMap<Integer,PrinterParam> printerlist) throws Exception{
//        if(activity==null){
//            throw new Exception("Not null activity is allow!");
//        }
//        this.active = activity;
//
//        printers = printerlist;
//
//        printer = WoyouPrinter.getInstance();
//        printer.initPrinter(active);
//        callback = new ICallback.Stub() {
//            @Override
//            public void onRunResult(boolean isSuccess) throws RemoteException {
//            }
//            @Override
//            public void onReturnString(String result) throws RemoteException {
//            }
//            @Override
//            public void onRaiseException(int code, String msg)
//                    throws RemoteException {
//            }
//        };
//
//        printThread = this;
//    }
//
//    class DocumentJob{
//        int printerID = 0;
//        int jobID = 0;
//        long JobTime = 0;
//        String JobName = "";
//        byte[] JobDoc = new byte[0];
//        public DocumentJob(String name, int printerID, byte[] b){
//            jobID = totaljob + 1;
//            totaljob++;
//            JobTime = System.currentTimeMillis();
//            JobName = name;
//            this.printerID = printerID;
//            JobDoc = b;
//            if(JobName.isEmpty()){
//                JobName = "Doc_"+sdf.format(new Date(JobTime));
//            }
//        }
//    }
//
//
//
//    @Override
//    public void run(){
//        start = true;
//        while(true){
//            try{
//                if(documentList.size()>0){
//                    DocumentJob doc = documentList.get(0);
//                    if(!printers.containsKey(doc.printerID)){
//                        SendErrorMessage(StrTextConst.GetText(StrTextConst.TextType.PRINTSPL, 12, doc.printerID));
//                        sleep(Long.MAX_VALUE);
//                        return;
//                    }
//                    PrinterParam print = printers.get(doc.printerID);
//                    switch(print.Type()){
//                        case 3: //usb port
//
//                            if(print.USBPort().isEmpty()){
//                                SendErrorMessage(StrTextConst.GetText(StrTextConst.TextType.PRINTSPL, 13));
//                                sleep(Long.MAX_VALUE);
//                                return;
//                            }
//
//                            UsbDevice usbdevice = USBHelper.getUSBDevice(active, print.USBPort());
//                            if(usbdevice==null){
//                                SendErrorMessage(StrTextConst.GetText(StrTextConst.TextType.PRINTSPL, 14));
//                                sleep(Long.MAX_VALUE);
//                                break;
//                            }
//                            if(!USBHelper.isUSBHasPermission(active, usbdevice)){
//                                SendErrorMessage(StrTextConst.GetText(StrTextConst.TextType.PRINTSPL, 15, usbdevice.getDeviceName()));
//                                sleep(Long.MAX_VALUE);
//                                return;
//                            }
//                            USBPort usbport = USBHelper.USBEstablish(active, usbdevice);
//                            try {
//                                final OutputStream os = usbport.getOutputStream();
//
//                                Thread _usbprint = new Thread(){
//                                    public void run(){
//                                        ByteArrayInputStream bais = new ByteArrayInputStream(documentList.get(0).JobDoc);
//                                        try{
//                                            byte[] b = new byte[32];
//                                            int len = 0;
//                                            while((len = bais.read(b))!=-1){
//                                                os.write(b,0,len);
//                                            }
//                                            os.flush();
//                                            bais.close();
//                                        }catch(IOException e){
//                                            try {
//                                                bais.close();
//                                            } catch (IOException e1) {}
//                                        }
//
//                                    }
//                                };
//                                _usbprint.start();
//                                _usbprint.join();
//                                usbport.close();
//                            } catch (IOException e2) {
//                                SendErrorMessage(StrTextConst.GetText(StrTextConst.TextType.PRINTSPL, 16));
//                                sleep(Long.MAX_VALUE);
//                            }
//                            break;
//
//                        case 2://bluetooth
//                            BluetoothAdapter bt = BluetoothAdapter.getDefaultAdapter();
//                            if(bt==null){
//                                SendErrorMessage(StrTextConst.GetText(StrTextConst.TextType.PRINTSPL, 17));
//                                sleep(Long.MAX_VALUE);
//                                break;
//                            }
//
//                            if(bt.getState()!=BluetoothAdapter.STATE_ON){
//                                SendErrorMessage(StrTextConst.GetText(StrTextConst.TextType.PRINTSPL, 18));
//                                sleep(Long.MAX_VALUE);
//                                break;
//                            }
//                            bt.cancelDiscovery();
//
//                            Set<BluetoothDevice> paired = bt.getBondedDevices();
//                            if(paired==null){
//                                SendErrorMessage(StrTextConst.GetText(StrTextConst.TextType.PRINTSPL, 19));
//                                sleep(Long.MAX_VALUE);
//                                break;
//                            }
//
//                            if(paired.size()==0 || print.BTMAC().isEmpty()){
//                                SendErrorMessage(StrTextConst.GetText(StrTextConst.TextType.PRINTSPL, 20));
//                                sleep(Long.MAX_VALUE);
//                                break;
//                            }
//
//                            boolean printed = false;
//                            for(BluetoothDevice device:paired){
//                                if(device.getAddress().equalsIgnoreCase(print.BTMAC())){
//                                    if(device.getBondState() == BluetoothDevice.BOND_BONDED){
//                                        try{
//                                            bt.cancelDiscovery();
//                                            BluetoothSocket sock = device.createInsecureRfcommSocketToServiceRecord(UUID.fromString("0000110E-0000-1000-8000-00805F9B34FB"));
//                                            try{
//                                                sock.connect();
//                                                final OutputStream os = sock.getOutputStream();
//                                                Thread _btprint = new Thread(){
//                                                    public void run(){
//                                                        ByteArrayInputStream bais = new ByteArrayInputStream(documentList.get(0).JobDoc);
//                                                        try{
//                                                            byte[] b= new byte[32];
//                                                            int len = 0;
//                                                            while((len = bais.read(b))!=-1){
//                                                                os.write(b,0,len);
//
//                                                            }
//                                                            os.flush();
//                                                            bais.close();
//                                                        }catch(IOException e){
//                                                            try {
//                                                                bais.close();
//                                                            } catch (IOException e1) {}
//                                                        }
//
//                                                    }
//                                                };
//                                                _btprint.start();
//                                                _btprint.join();
//                                                sock.close();
//                                                printed = true;
//                                                break;
//                                            }catch(IOException e){
//                                                try {
//                                                    bt.cancelDiscovery();
//                                                    Method m = device.getClass().getMethod("createRfcommSocket", new Class[] {int.class});
//                                                    sock = (BluetoothSocket)m.invoke(device, 1);
//                                                    sock.connect();
//                                                    final OutputStream os = sock.getOutputStream();
//                                                    Thread _btprint = new Thread(){
//                                                        public void run(){
//                                                            ByteArrayInputStream bais = new ByteArrayInputStream(documentList.get(0).JobDoc);
//                                                            try{
//                                                                byte[] b= new byte[32];
//                                                                int len = 0;
//                                                                while((len = bais.read(b))!=-1){
//                                                                    os.write(b,0,len);
//                                                                    os.flush();
//                                                                }
//                                                                bais.close();
//                                                            }catch(IOException e){
//                                                                try {
//                                                                    bais.close();
//                                                                } catch (IOException e1) {}
//                                                            }
//
//                                                        }
//                                                    };
//                                                    _btprint.start();
//                                                    _btprint.join();
//                                                    sock.close();
//                                                    printed = true;
//                                                    break;
//                                                } catch (IllegalArgumentException e1) {
//                                                    SendErrorMessage(StrTextConst.GetText(StrTextConst.TextType.PRINTSPL, 23));
//                                                    sleep(Long.MAX_VALUE);
//                                                    break;
//                                                } catch (IllegalAccessException e1) {
//                                                    SendErrorMessage(StrTextConst.GetText(StrTextConst.TextType.PRINTSPL, 23));
//                                                    sleep(Long.MAX_VALUE);
//                                                    break;
//                                                } catch (InvocationTargetException e1) {
//                                                    SendErrorMessage(StrTextConst.GetText(StrTextConst.TextType.PRINTSPL, 23));
//                                                    sleep(Long.MAX_VALUE);
//                                                    break;
//                                                } catch (NoSuchMethodException e1) {
//                                                    SendErrorMessage(StrTextConst.GetText(StrTextConst.TextType.PRINTSPL, 23));
//                                                    sleep(Long.MAX_VALUE);
//                                                    break;
//                                                } catch (IOException e1){
//                                                    SendErrorMessage(StrTextConst.GetText(StrTextConst.TextType.PRINTSPL, 21));
//                                                    sleep(Long.MAX_VALUE);
//                                                    break;
//                                                }
//                                            }
//                                        }catch(IOException e){
//                                            SendErrorMessage(StrTextConst.GetText(StrTextConst.TextType.PRINTSPL, 21));
//                                            sleep(Long.MAX_VALUE);
//                                            break;
//                                        }
//                                    }
//                                }
//                            }
//
//                            if(!printed){
//                                SendErrorMessage(StrTextConst.GetText(StrTextConst.TextType.PRINTSPL, 20));
//                                sleep(Long.MAX_VALUE);
//                                break;
//                            }
//
//                            break;
//                        case 1: //LAN Printer
//                            try{
//                                //Socket sock = new Socket(print.LANIP(),print.LANPort());
//                                Socket sock = new Socket();
//                                sock.connect(new InetSocketAddress(print.LANIP(),print.LANPort()), 3000);
//                                sock.setSendBufferSize(1024);
//                                final OutputStream os = sock.getOutputStream();
//                                //new InetSocketAddress(print.LANIP(),print.LANPort())
//                                os.write(new byte[0]);
//                                Thread _netprint = new Thread(){
//                                    public void run(){
//                                        ByteArrayInputStream bais = new ByteArrayInputStream(documentList.get(0).JobDoc);
//                                        try{
//                                            byte[] b = new byte[1024];
//                                            int len = 0;
//                                            while((len = bais.read(b))!=-1){
//                                                os.write(b,0,len);
//                                            }
//                                            os.flush();
//                                            bais.close();
//                                        }catch(IOException e){
//                                            try {
//                                                bais.close();
//                                            } catch (IOException e1) {}
//                                        }
//
//                                    }
//                                };
//                                _netprint.start();
//                                _netprint.join();
//                                //usbport.close();
//
//                                //Socket sock = new Socket(print.LANIP(),print.LANPort());
//                                //OutputStream os = sock.getOutputStream();
//                                //os.write(documentList.get(0).JobDoc);
//                                //os.flush();
//                                sock.close();
//                            }catch(UnknownHostException e){
//                                SendErrorMessage("UnknownHostException:" + e.getMessage());
//                                sleep(Long.MAX_VALUE);
//                            }catch(IOException e){
//                                SendErrorMessage("IOException:" + e.getMessage());
//                                sleep(Long.MAX_VALUE);
//                            }
//                            break;
//                        case 0:
//                            printer.PrintRaw(documentList.get(0).JobDoc, callback);
//                            break;
//                        default:
//                            SendErrorMessage(StrTextConst.GetText(StrTextConst.TextType.PRINTSPL, 22, print.name));
//                            sleep(Long.MAX_VALUE);
//                            return;
//
//                        //break;
//                    }
//                    documentList.remove(0);
//                }
//
//                if(documentList.size()==0){
//                    sleep(500);
//                }
//            }catch(InterruptedException e){
//                if(!start)break;
//            }
//        }
//        start = false;
//    }
//
//    void ShutdownSpooler(){
//        start=false;
//        PurgeAllDocument();
//        this.interrupt();
//    }
//
//    void PurgeAllDocument(){
//        documentList.clear();
//    }
//
//    void SendToSpooler(String jobname, int printerID, byte[] b){
//        if(!start){
//            Toast.makeText(active, StrTextConst.GetText(StrTextConst.TextType.PRINTSPL, 11), Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        documentList.add(new DocumentJob(jobname,printerID,b));
//        if(!pause){
//        }
//    }
//
//    public void setPause(boolean pause){
//        this.pause = pause;
//    }
//
//    public boolean IsPause(){
//        return pause;
//    }
//
//    private void SendErrorMessage(final String msg){
//        active.runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                final DialogBox dlg = new DialogBox(active);
//                dlg.setTitle(StrTextConst.GetText(StrTextConst.TextType.PRINTSPL, 1));
//                dlg.setDialogIconType(DialogBox.IconType.WARNING);
//                dlg.setMessage(StrTextConst.GetText(StrTextConst.TextType.PRINTSPL, 10, documentList.get(0).JobName, documentList.get(0).jobID, totaljob, msg));
//                dlg.setButton1OnClickListener(StrTextConst.GetText(StrTextConst.TextType.PRINTSPL, 100), new View.OnClickListener(){
//                    @Override
//                    public void onClick(View v) {
//
//                        printThread.interrupt();
//                        dlg.dismiss();
//                    }
//                });
//
//                dlg.setButton2OnClickListener(StrTextConst.GetText(StrTextConst.TextType.PRINTSPL, 101), new View.OnClickListener(){
//                    @Override
//                    public void onClick(View v) {
//
//                        documentList.remove(0);
//                        printThread.interrupt();
//                        dlg.dismiss();
//                    }
//                });
//
//                dlg.setButton3OnClickListener(StrTextConst.GetText(StrTextConst.TextType.PRINTSPL, 102), new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        PurgeAllDocument();
//                        printThread.interrupt();
//                        dlg.dismiss();
//                    }
//                });
//                dlg.show();
//
//            }
//        });
//    }
//}
