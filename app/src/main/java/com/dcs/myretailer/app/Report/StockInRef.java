package com.dcs.myretailer.app.Report;

public class StockInRef {
     String pluid;
     String pluname;
     String pluprice;
      String transno;
     double qty;
     String dt;

    public StockInRef(String pluid, String pluname, String pluprice, String transno, Double qty, String dt) {
        this.pluid = pluid;
        this.pluname = pluname;
        this.pluprice = pluprice;
        this.transno = transno;
        this.qty = qty;
        this.dt = dt;
    }

}
