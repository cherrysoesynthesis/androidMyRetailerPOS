package com.dcs.myretailer.app.Setting;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbConstants;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class USBHelper {

    public static List<UsbDevice> ListUSBDevice(Activity active){
        return ListUSBDevice(active, 0, 0);
    }

    public static List<UsbDevice> ListUSBDevice(Activity active, int PID, int VID){
        UsbManager manager = (UsbManager) active.getSystemService(Context.USB_SERVICE);
        HashMap<String, UsbDevice> deviceList = manager.getDeviceList();
        List<UsbDevice> devices = new ArrayList<UsbDevice>();
        for(Object key:deviceList.keySet()){

            UsbDevice device = deviceList.get(key);
            if(PID==0 && VID == 0){
                devices.add(device);
            }else{
                if(device.getProductId() == PID && device.getVendorId() == VID){
                    devices.add(device);
                }
            }
        }
        return devices;
    }

    public static UsbDevice getUSBDevice(Activity active, String name){
        if(name.isEmpty()){
            return null;
        }
        UsbManager manager = (UsbManager) active.getSystemService(Context.USB_SERVICE);
        HashMap<String, UsbDevice> deviceList = manager.getDeviceList();
        for(Object key:deviceList.keySet()){

            UsbDevice device = deviceList.get(key);
            if(device.getDeviceName().equals(name)){
                return device;
            }
        }
        return null;
    }

    public static boolean isUSBHasPermission(Activity active, UsbDevice device){
        UsbManager manager = (UsbManager) active.getSystemService(Context.USB_SERVICE);
        return manager.hasPermission(device);
    }

    public static void getUSBPermission(Activity active, UsbDevice device){
        if(!isUSBHasPermission(active, device)){
            UsbManager manager = (UsbManager) active.getSystemService(Context.USB_SERVICE);
            PendingIntent pi = PendingIntent.getBroadcast(active, 0, new Intent("com.android.example.USB_PERMISSION"), 0);
            manager.requestPermission(device, pi);
        }
    }

    public static USBPort USBEstablish(Activity active, UsbDevice device){
        if(device==null || active == null){//cannot be null
            return null;
        }

        if(!isUSBHasPermission(active, device)){
            return null;
        }

        UsbEndpoint usbin = null;
        UsbEndpoint usbout = null;
        UsbInterface usbinterface = null;

        int usbclass = 7;
        int usbsubclass = 1;
        int usbprotocol = 2;


        for(int i=0;i<device.getInterfaceCount();i++){
            UsbInterface _inf = device.getInterface(i);
            if(_inf.getInterfaceClass() == usbclass && _inf.getInterfaceSubclass() == usbsubclass && _inf.getInterfaceProtocol() == usbprotocol){
                usbinterface = _inf;
                for(int j=0;j<_inf.getEndpointCount();j++){
                    if(_inf.getEndpoint(j).getDirection() == UsbConstants.USB_DIR_IN){
                        if(usbin==null){
                            usbin = _inf.getEndpoint(j);
                        }
                    }
                    if(_inf.getEndpoint(j).getDirection() == UsbConstants.USB_DIR_OUT){
                        if(usbout==null){
                            usbout = _inf.getEndpoint(j);
                        }
                    }
                }
                break;
            }
        }

        if(usbinterface==null || usbin == null || usbout == null){ //no available USB printer found
            return null;
        }

        UsbManager manager = (UsbManager) active.getSystemService(Context.USB_SERVICE);
        UsbDeviceConnection usbconnect = manager.openDevice(device);
        usbconnect.claimInterface(usbinterface, true);

        return new USBPort(usbconnect, usbin, usbout);
    }



}
