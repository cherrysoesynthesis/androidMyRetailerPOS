package com.dcs.myretailer.app.Setting;

import it.sauronsoftware.ftp4j.FTPDataTransferListener;

public class MyTransferListener implements FTPDataTransferListener {

    public void started() {
        System.out.println("Trasferimento FTP avviato");

    }

    public void transferred(int length) {
        System.out.println("in trasferimento: "+ String.valueOf(length));

    }

    public void completed() {
        System.out.println("Completato");

    }

    public void aborted() {
        System.out.println("Annullato");

    }

    public void failed() {
        System.out.println("Trasferimento fallito");

    }

}