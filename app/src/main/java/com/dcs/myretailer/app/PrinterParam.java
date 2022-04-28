package com.dcs.myretailer.app;


public class PrinterParam {
    int id = -1;
    String name = "";
    int lfeed = 1;
    int lwidth = 32;
    boolean nodouble = false;
    int type = -1;
    String lanip = "";
    int port = 9100;
    String usbport = "";
    String btmac = "";
    public PrinterParam(int id, String name,int type, int lfeed, int lwidth, boolean nodouble, String param1, int param2){
        this.id = id;
        this.name = name;
        this.type = type;
        this.lfeed = lfeed;
        this.lwidth = lwidth;
        this.nodouble = nodouble;
        //Log.e("Printer Name", ""+name);
        //Log.e("Printer Type", ""+type);
        //Log.e("Printer", param1);
        if(type==1){
            lanip = param1;
            port = param2;
        }else if(type==2){
            btmac = param1;
        }else if(type==3){
            usbport = param1;
        }
    }

    public String Name(){
        return name;
    }

    public int Type(){
        return type;
    }

    public int Feed(){
        return lfeed;
    }

    public int Width(){
        return lwidth;
    }

    public boolean NoDouble(){
        return nodouble;
    }

    public String LANIP(){
        return lanip;
    }


    public int LANPort(){
        return port;
    }

    public String BTMAC(){
        return btmac;
    }

    public String USBPort(){
        return usbport;
    }

}
