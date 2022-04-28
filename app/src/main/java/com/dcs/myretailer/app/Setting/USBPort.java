package com.dcs.myretailer.app.Setting;

import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class USBPort {
    private final UsbDeviceConnection usbconnect;
    private final USBInputStream mInputStream;
    private final USBOutputStream mOutputStream;
    public USBPort(UsbDeviceConnection usbconnect, UsbEndpoint usbin, UsbEndpoint usbout){
        this.usbconnect = usbconnect;
        mInputStream = new USBInputStream(usbconnect, usbin);
        mOutputStream = new USBOutputStream(usbconnect, usbout);
    }

    public void close() throws IOException {
        usbconnect.close();
    }

    public InputStream getInputStream() throws IOException {
        return mInputStream;
    }

    public OutputStream getOutputStream() throws IOException {
        return mOutputStream;
    }

    private class USBOutputStream extends OutputStream{
        UsbDeviceConnection usbconnect;
        UsbEndpoint usbout;
        public USBOutputStream(UsbDeviceConnection usbconnect, UsbEndpoint usbout){
            this.usbconnect = usbconnect;
            this.usbout = usbout;
        }


        @Override
        public void write(byte[] buffer) throws IOException{
            if(usbconnect.bulkTransfer(usbout, buffer, buffer.length, 1000)<0){
                throw new IOException("USB write timeout!");
            }
        }

        @Override
        public void write(byte[] buffer, int offset, int count) throws IOException{
            if(usbconnect.bulkTransfer(usbout, buffer, count, 1000)<0){
                throw new IOException("USB write timeout!");
            }
        }

        @Override
        public void write(int oneByte) throws IOException {
            byte[] buffer = new byte[1];
            buffer[0] = (byte)(oneByte&0xFF);
            if(usbconnect.bulkTransfer(usbout, buffer, 1, 1000)<0){
                throw new IOException("USB write timeout!");
            }
        }

        @Override
        public void close() throws IOException {
        }

    }

    private class USBInputStream extends InputStream{
        UsbDeviceConnection usbconnect;
        UsbEndpoint usbin;
        public USBInputStream(UsbDeviceConnection usbconnect, UsbEndpoint usbin){
            this.usbconnect = usbconnect;
            this.usbin = usbin;
        }

        @Override
        public int read(byte[] buffer, int offset, int count) throws IOException {
            byte[] _buf = new byte[count];
            int len =  usbconnect.bulkTransfer(usbin, _buf, _buf.length, 1000);
            if(len<0){
                throw new IOException("USB read timeout!");
            }
            System.arraycopy(_buf, 0, buffer, offset, len);

            return len;
        }

        @Override
        public int read() throws IOException {
            byte[] buffer = new byte[1];
            if(usbconnect.bulkTransfer(usbin, buffer, 1, 1000)<0){
                throw new IOException("USB read timeout!");
            }

            return buffer[0];
        }

        @Override
        public void close() throws IOException {
        }

    }
}
