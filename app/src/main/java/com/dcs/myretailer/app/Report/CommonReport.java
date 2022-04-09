package com.dcs.myretailer.app.Report;

import android.database.Cursor;
import android.text.Html;
import android.util.Log;

import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommonReport {

//    public static Object[] CountSalesTotal(long starttime, long endtime, long userid, long referid){
//
//        int totalbill = 0;
//
//        int totalqty = 0;
//        double totalnett = 0;s
//        double totaltax = 0;
//        double round = 0;
//        double totalamt = 0;
//
//        double discamt = 0;
//        double surchgamt = 0;
//
//        int totalbillcancel = 0;
//
//        int totalqtycancel = 0;
//        double totalcancelnett = 0;
//        double totalcancelamt = 0;
//        double totaltaxcancel = 0;
//
//
//        Cursor bill = null;
//        try{
//            String sql = "SELECT BillNo, Round, Cancel FROM Bill WHERE CloseDateTime BETWEEN "+starttime + " AND "+endtime+" " +
//                    "AND CloseDateTime IS NOT NULL";
//            if(userid != -1){
//                sql += " AND CashierID = "+userid;
//            }
//            Log.i("Dfdfdf_idx_sql", String.valueOf(sql));
//            if(referid != -2){
//                if(referid == -1){
//                    sql += " AND ReferID IS NULL";
//                }else{
//                    sql += " AND ReferID = "+referid;
//                }
//            }
//            Log.i("Dfdfdf_idx_sqll", String.valueOf(sql));
//            sql += " ORDER BY BillNo ASC";
//            bill = DBFunc.Query(sql, false);
//            if(bill==null){//DB Error
//                return null;
//            }
//
//            while(bill.moveToNext()){
//                int billID = bill.getInt(0);//get BillNo
//                boolean billcancel = false;
//                if(bill.getInt(2)==1){//bill is canceled
//                    billcancel = true;
//                }else{
//                    billcancel =false;
//                }
//                Log.i("Dfdfdf_billcancel", String.valueOf(billcancel));
//
//                if(!billcancel)round += bill.getDouble(1);
//                double _totalbillamount = 0;
//
//                if(billcancel){
//                    totalbillcancel++;
//                }else{
//                    totalbill++;
//                }
//
//                Cursor data = null;
//                Log.i("Dfdfdf_billID", String.valueOf(billID));
//                try{
//                    data = DBFunc.Query("SELECT idx, PLUID, Name, Amount, Qty, Cancel FROM BillPLU " +
//                            "WHERE BillID = "+billID+" AND Cancel = 'SALES' ORDER BY idx ASC", false);
////                    data = DBFunc.Query("SELECT idx, PLUID, Name, Amount, Qty, Cancel FROM BillPLU " +
////                            "WHERE BillNo = "+billID+" AND Cancel = 0 ORDER BY idx ASC", false);
//
//                    if(data==null){//return null if cannot connect to database and retrieve data
//                        return null;
//                    }
//
//                    while(data.moveToNext()){
//                        int idx = data.getInt(0);
//                        double amount = data.getDouble(3);
//                        double qty = data.getDouble(4);
//
//                        boolean cancel = false;
//                        if(data.getInt(5)==1){
//                            cancel = true;
//                        }
//
//                        if(billcancel){
//                            cancel = true;
//                        }
//
//
//                        double _itemprice_base = (qty*amount);
//                        if(cancel){
//                            totalcancelnett += _itemprice_base;
//                            totalqtycancel+=qty;
//                        }else{
//                            totalnett += _itemprice_base;
//                            totalqty+=qty;
//                        }
//
//
//                        double _itemprice_disc = _itemprice_base;
//
//
//                        int _discID = -1;
//                        double _discrate = 0;
//                        boolean[] _discopt = new boolean[4];
//                        double _discamt = 0;
//
//                        Cursor disc = null;
//                        if(!billcancel){
//                            try{
//                                disc = DBFunc.Query("SELECT idx, DiscID, Name, Value, Option FROM BillDisc " +
//                                        "WHERE BillNo = "+billID+" AND BillPLU_idx = "+idx+ " ORDER BY idx ASC LIMIT 1", false);
//                                if(disc != null){
//                                    if(disc.moveToNext()){
//                                        _discID = disc.getInt(1);
//                                        _discrate = disc.getDouble(3);
//                                        if(_discrate<0)_discrate = 0;
//                                        for(int i=0;i<_discopt.length;i++){
//                                            if(i<disc.getString(4).length()){
//                                                if(disc.getString(4).charAt(i) == '1'){
//                                                    _discopt[i] = true;
//                                                }
//                                            }
//                                        }
//                                    }
//                                    //disc.close();
//                                }else{//DB error!
//                                    return null;
//                                }
//                            }finally{
//                                if(disc!=null)disc.close();
//                            }
//
//                            if(_discID!=-1){
//
//                                if(!_discopt[0]){//is it bill discount?
//                                    if(_discopt[3]){//discount before tax
//                                        if(_discopt[1]){//amount discount
//                                            if(_discopt[2]){//discount is positive(surcharge)
//                                                _discamt = _discrate;
//                                                _itemprice_disc += _discamt;
//                                                surchgamt += _discamt;
//                                            }else{//discount
//                                                if(_itemprice_disc-_discrate>0){
//                                                    _discamt = -_discrate;
//                                                    _itemprice_disc += _discamt;
//                                                }else{
//                                                    _discamt = -_itemprice_base;
//                                                    _itemprice_disc = 0;
//                                                }
//                                                discamt += _discamt;
//
//                                            }
//                                        }else{
//                                            if(_discrate>100)_discrate = 100;
//                                            _discamt = _itemprice_disc * (_discrate/100d);
//                                            if(_discopt[2]){
//                                                _itemprice_disc += _discamt;
//                                                surchgamt += _discamt;
//                                            }else{
//                                                _discamt = -_discamt;
//                                                _itemprice_disc += _discamt;
//                                                discamt += _discamt;
//                                            }
//                                            //existobj.count++;
//                                        }
//                                    }
//
//                                }
//                            }
//                        }
//
//                        double _itempricetax = _itemprice_disc;
//                        //double _itempricetax = _itemprice_base;
//                        double _tax = 0;
//
//                        Cursor plutax = null;
//                        try{
//                            plutax = DBFunc.Query("SELECT TaxID,Name,Acronym,Rate, Type FROM BillPLUTax WHERE BillPLU_idx = " + idx + " ORDER BY Seq ASC", false);
//                            if (plutax != null) {
//                                while (plutax.moveToNext()) {
//                                    if (plutax.getInt(4) == 0) {// not include(add on tax)
//                                        _tax = (_itempricetax * (plutax.getDouble(3) / 100f));
//                                        _itempricetax += _tax;
//                                    } else if (plutax.getInt(4) == 1) {// is inclusive (VAT)
//                                        _tax = (_itempricetax - (_itempricetax / (1f + (plutax.getDouble(3) / 100f))));
//                                        _itempricetax = (_itempricetax / (1f + (plutax.getDouble(3) / 100f)));
//                                    } else if (plutax.getInt(4) == 2) {// calculate on base price(add on tax)
//                                        _tax = (_itemprice_base * (plutax.getDouble(3) / 100f));
//                                        _itempricetax += _tax;
//                                    }
//
//
//                                    if(cancel){
//                                        totaltaxcancel += _tax;
//                                    }else{
//                                        totaltax += _tax;
//                                    }
//
//                                }
//                                //plutax.close();
//                            }else{//failed to connect to database then we just close the previous and report back null data to let previous stack knows bad happening
//                                return null;
//                            }
//                        }finally{
//                            if(plutax!=null)plutax.close();
//                        }
//
//
//
//
//                        //_itemprice_disc = Double.parseDouble(df.format(_itempricetax));
//                        _itemprice_disc = MathUtil.Truncate(_itempricetax,2, 0);
//
//                        if(!billcancel){
//                            if(_discID != -1){
//                                if(!_discopt[0]){//is it bill discount?
//                                    if(!_discopt[3]){//discount AFTER tax
//                                        if(_discopt[1]){//amount discount
//                                            if(_discopt[2]){//discount is positive(surcharge)
//                                                _discamt = _discrate;
//                                                _itemprice_disc += _discamt;
//                                                surchgamt += _discamt;
//                                                //existobj.count++;
//                                            }else{//discount
//                                                if(_itemprice_base-_discrate>0){
//                                                    _discamt = -_discrate;
//                                                    _itemprice_disc += _discamt;
//                                                }else{
//                                                    _discamt = -_itemprice_disc;
//                                                    _itemprice_disc = 0;
//                                                }
//                                                discamt += _discamt;
//                                            }
//                                            //existobj.count++;
//                                        }else{
//                                            if(_discrate>100)_discrate = 100;
//                                            discamt = _itemprice_disc * (_discrate/100d);
//                                            if(_discopt[2]){//surcharge
//                                                _itemprice_disc += _discamt;
//                                                surchgamt += _discamt;
//                                            }else{
//                                                _discamt = -_discamt;
//                                                _itemprice_disc += _discamt;
//                                                discamt += _discamt;
//
//                                            }
//                                            //existobj.count++;
//                                        }
//                                    }
//
//                                }
//                            }
//
//                            //existobj.amount += discamt;
//                            //discamt += _discamt;
//
//
//                        }
//
//                        //_itemprice_disc = Double.parseDouble(df.format(_itemprice_disc));
//                        _itemprice_disc = MathUtil.Truncate(_itemprice_disc,2, 0);
//                        if(cancel){
//                            totalcancelamt += _itemprice_disc;
//                        }else{
//                            _totalbillamount += _itemprice_disc;
//                        }
//
//                    }
//                }catch (IllegalFormatException e){
//                    Log.i("Loooooo",e.getMessage());
//                }finally{
//                    if(data!=null)data.close();
//                }
//
//                if(!billcancel){
//
//                    Cursor disc = null;
//                    int _discID = -1;
//                    double _discrate = 0;
//                    boolean[] _discopt = new boolean[4];
//                    try{
//                        disc = DBFunc.Query("SELECT idx, DiscID, Name, Value, Option FROM BillDisc WHERE BillNo = "+billID+" AND BillPLU_idx IS NULL ORDER BY idx ASC LIMIT 1", false);
//
//                        if(disc!=null){
//
//                            if(disc.moveToNext()){
//                                //int discidx = disc.getInt(0);
//                                _discID = disc.getInt(1);
//                                //String discname = disc.getString(2);
//                                _discrate = disc.getDouble(3);
//                                for(int i=0;i<_discopt.length;i++){
//                                    if(i<disc.getString(4).length()){
//                                        if(disc.getString(4).charAt(i) == '1'){
//                                            _discopt[i] = true;
//                                        }
//                                    }
//                                }
//                            }
//                            //disc.close();
//                        }else{
//                            return null;
//                        }
//
//                    }finally{
//                        if(disc!=null)disc.close();
//                    }
//
//
//                    double _discamt = 0;
//                    double _pricedisc = _totalbillamount;
//
//
//                    if(_discID != -1){
//                        if(_discopt[0]){//is it bill discount?
//                            //if(discopt[3]){//discount before tax
//                            if(_discopt[1]){//amount discount
//                                if(_discopt[2]){//discount is positive(surcharge)
//                                    _discamt = _discrate;
//                                    _pricedisc = _discamt;
//                                    surchgamt += _discamt;
//                                }else{//discount
//                                    if(_pricedisc-_discrate>0){
//                                        _discamt = -_discrate;
//                                        _pricedisc += _discamt;
//                                    }else{
//                                        _discamt = -_pricedisc;
//                                        _pricedisc = 0;
//                                    }
//                                    discamt += _discamt;
//                                }
//                            }else{
//                                if(_discrate>100)_discrate = 100;
//                                _discamt = _pricedisc * (_discrate/100d);
//                                if(_discopt[2]){//surcharge
//                                    _pricedisc -= _discamt;
//                                    surchgamt += _discamt;
//                                }else{
//                                    _discamt = -_discamt;
//                                    _pricedisc += _discamt;
//                                    discamt += _discamt;
//                                }
//
//                            }
//
//                        }
//
//                    }
//
//                    totalamt += MathUtil.Truncate(_pricedisc, 2, 0);
//                }
//
//            }
//        }finally{
//            if(bill!=null)bill.close();
//        }
//
//
//        //totalbill, totalqty, totalnett, totaltax, round, totalamtcollect, discamt, surchgamt, totalbillcancel, totalqtycancel, totalcancelnett, totalcancelamt, totaltaxcancel
//        return new Object[]{totalbill, totalqty, totalnett, totaltax, round, totalamt, discamt, surchgamt, totalbillcancel, totalqtycancel, totalcancelnett, totalcancelamt, totaltaxcancel};
//    }

//    public static Object[] CountSalesTotal(long starttime, long endtime, long userid, long referid){
//
//        int totalbill = 0;
//
//        int totalqty = 0;
//        double totalnett = 0;
//        double totaltax = 0;
//        double round = 0;
//        double totalamt = 0;
//
//        double discamt = 0;
//        double surchgamt = 0;
//
//        int totalbillcancel = 0;
//
//        int totalqtycancel = 0;
//        double totalcancelnett = 0;
//        double totalcancelamt = 0;
//        double totaltaxcancel = 0;
//        ArrayList<Integer> Arr = new ArrayList<Integer>();
//
//        ArrayList<Integer> BillIDArr = new ArrayList<Integer>();
//
//        Double TotalNettSales = 0.0;
//        Integer BillCancelTotalQty = 0;
//        Integer BillCancelPaidCount = 0;
//        Integer TotalQty = 0;
//        Double BillCancel = 0.0;
//        String BillTax = "0";
//        Double TotalBillDisountTax = 0.0;
//        Double GrossSales = 0.0;
//        Double TaxTotal = 0.0;
//        Double TotalBillDisount = 0.0;
//        Double TotalRoundAdjSales = 0.0;
//        Double ServiceChargesSales = 0.0;
//        Double TotalTaxSales = 0.0;
//        Integer TotalQtyProduct = 0;
//        Double TotalNettSalesProduct = 0.0;
//        String GrossSalesProduct = "0";
//        String TaxTotalProduct = "0";
//        String TotalBillDisountProduct = "0";
//        String BillCancelTotalQtyProduct = "";
//        String BillCancelProduct = "";
//        String BillTaxProduct = "";
//        Double TotalBillDisountTaxProduct = 0.0;
//        Double TotalNettSalesProductCancel = 0.0;
//        Integer PaymentTypeCount = 0;
//        String PaymentTypeAmount = "0";
//        String PaymentTypeName = "0";
////    Integer DicountCount = 0;
//        String DicountName = "";
//        String DicountAmount ="";
//        Integer TotalTaxesCount = 0;
//        Integer BillPaidCount = 0;
//        Double TotalTaxesAmount = 0.0;
//        Integer TotalQtyCategory = 0;
//        Double TotalNettSalesCategory = 0.0;
//        Double GrossSalesCategory = 0.0;
//        String TaxTotalCategory = "0";
//        Double TotalBillDisountCategory = 0.0;
//        Integer BillCancelTotalQtyCategory = 0;
//        String BillTaxCategory = "";
//        Double TotalBillDisountTaxCategory = 0.0;
//        Integer TotalQtyCategoryCancel = 0;
//        Double TotalNettSalesCategoryCancel = 0.0;
//        //Total Sales//
//        Cursor c = Query.XZDataReportSales(starttime,endtime,"Report");
//        if (c != null) {
//            TotalQty = 0;
//            BillPaidCount = 0;
//            TotalNettSales = 0.0;
//            GrossSales = 0.0;
//            TotalBillDisount = 0.0;
//            TotalTaxSales = 0.0;
//            TotalRoundAdjSales = 0.0;
//            ServiceChargesSales = 0.0;
//            while (c.moveToNext()) {
//                TotalQty += c.getInt(0);
//                TotalNettSales += c.getDouble(1);
//                //GrossSales += c.getDouble(8);
//                GrossSales += c.getDouble(2);
//                TotalBillDisount += c.getDouble(3) + c.getDouble(8);
//                TotalTaxSales += c.getDouble(4);
//                TotalRoundAdjSales += c.getDouble(5);
//                ServiceChargesSales += c.getDouble(6);
//                BillPaidCount += c.getInt(7);
//            }
//            c.close();
//        }
//
//        Cursor c_productcancel = Query.XZDataReportProductCancel(starttime,endtime,"Report");
//
//        if (c_productcancel != null) {
//            TotalNettSalesProductCancel = 0.0;
//            BillCancelTotalQty = 0;
//            BillCancelPaidCount = 0;
//            BillCancel = 0.0;
//            TotalBillDisountTax = 0.0;
//            while (c_productcancel.moveToNext()) {
//                TotalNettSalesProductCancel = c_productcancel.getDouble(2);
//                BillCancelTotalQty += c_productcancel.getInt(13);
//                BillCancelPaidCount += c_productcancel.getInt(0);
//                BillCancel += c_productcancel.getDouble(13);
//                //BillTax = c.getString(14);
//                TotalBillDisountTax += c_productcancel.getDouble(9);
//            }
//            c_productcancel.close();
//        }
//        double grossTotal = TotalNettSales - TotalTaxSales;
//        return new Object[]{BillPaidCount, TotalQty, TotalNettSales, TotalTaxSales, TotalRoundAdjSales, grossTotal, TotalBillDisount, ServiceChargesSales, 1, BillCancelTotalQty, BillCancel, BillCancel, BillTax};
//    }

//    public static Object[] CountSalesTotal(long starttime, long endtime, long userid, long referid){
//
//        int totalbill = 0;
//
//        int totalqty = 0;
//        double totalnett = 0;
//        double totaltax = 0;
//        double round = 0;
//        double totalamt = 0;
//
//        double discamt = 0;
//        double surchgamt = 0;
//
//        int totalbillcancel = 0;
//
//        int totalqtycancel = 0;
//        double totalcancelnett = 0;
//        double totalcancelamt = 0;
//        double totaltaxcancel = 0;
//        ArrayList<Integer> Arr = new ArrayList<Integer>();
//
//        ArrayList<Integer> BillIDArr = new ArrayList<Integer>();
//
//        Double TotalNettSales = 0.0;
//        Integer BillCancelTotalQty = 0;
//        Integer BillCancelPaidCount = 0;
//        Integer TotalQty = 0;
//        Double BillCancel = 0.0;
//        String BillTax = "0";
//        Double TotalBillDisountTax = 0.0;
//        Double GrossSales = 0.0;
//        Double TaxTotal = 0.0;
//        Double TotalBillDisount = 0.0;
//        Double TotalRoundAdjSales = 0.0;
//        Double ServiceChargesSales = 0.0;
//        Double TotalTaxSales = 0.0;
//        Integer TotalQtyProduct = 0;
//        Double TotalNettSalesProduct = 0.0;
//        String GrossSalesProduct = "0";
//        String TaxTotalProduct = "0";
//        String TotalBillDisountProduct = "0";
//        String BillCancelTotalQtyProduct = "";
//        String BillCancelProduct = "";
//        String BillTaxProduct = "";
//        Double TotalBillDisountTaxProduct = 0.0;
//        Double TotalNettSalesProductCancel = 0.0;
//        Integer PaymentTypeCount = 0;
//        String PaymentTypeAmount = "0";
//        String PaymentTypeName = "0";
////    Integer DicountCount = 0;
//        String DicountName = "";
//        String DicountAmount ="";
//        Integer TotalTaxesCount = 0;
//        Integer BillPaidCount = 0;
//        Double TotalTaxesAmount = 0.0;
//        Integer TotalQtyCategory = 0;
//        Double TotalNettSalesCategory = 0.0;
//        Double GrossSalesCategory = 0.0;
//        String TaxTotalCategory = "0";
//        Double TotalBillDisountCategory = 0.0;
//        Integer BillCancelTotalQtyCategory = 0;
//        String BillTaxCategory = "";
//        Double TotalBillDisountTaxCategory = 0.0;
//        Integer TotalQtyCategoryCancel = 0;
//        Double TotalNettSalesCategoryCancel = 0.0;
//        //Total Sales//
//        ArrayList<String> arr = new ArrayList<String>();
//        ArrayList<String> arrall = new ArrayList<String>();
//        Cursor c = Query.XZDataReportSales(starttime,endtime,"Report");
//        if (c != null) {
//            TotalQty = 0;
//            BillPaidCount = 0;
//            TotalNettSales = 0.0;
//            GrossSales = 0.0;
//            TotalBillDisount = 0.0;
//            TotalTaxSales = 0.0;
//            TotalRoundAdjSales = 0.0;
//            ServiceChargesSales = 0.0;
//            arr.clear();
//            arrall.clear();
//            while (c.moveToNext()) {
////                if (c.getString(9) == null){
////
////                }else {
////                    if (c.getString(9)!= null && c.getString(9).length() > 3) {
////                        if (!arr.contains(c.getString(9))) {
////                            arr.add(c.getString(9));
////                            arrall.add(c.getString(10));
//
//                            TotalQty += c.getInt(0);
//                            TotalNettSales += c.getDouble(1);
//                            //GrossSales += c.getDouble(8);
//                            GrossSales += c.getDouble(2);
//                            TotalBillDisount += c.getDouble(3) + c.getDouble(8);
//                            TotalTaxSales += c.getDouble(4);
//                            TotalRoundAdjSales += c.getDouble(5);
//                            ServiceChargesSales += c.getDouble(6);
//                            BillPaidCount += c.getInt(7);
//
////                        }else {
////
////                            TotalQty -= c.getInt(0);
////                            TotalNettSales -= c.getDouble(1);
////                            //GrossSales += c.getDouble(8);
////                            GrossSales -= c.getDouble(2);
////                            TotalBillDisount -= c.getDouble(3) + c.getDouble(8);
////                            TotalTaxSales -= c.getDouble(4);
////                            TotalRoundAdjSales -= c.getDouble(5);
////                            ServiceChargesSales -= c.getDouble(6);
////                            BillPaidCount -= c.getInt(7);
////                            arrall.add(c.getString(10));
////                        }
////                    }else {
////                        arrall.add(c.getString(10));
////                    }
////                }
////                Log.i("DFDF_____","DFDF__arr___"+arr);
////                Log.i("DFDF_____","DFDF__arrall___"+arrall);
////                if (!arrall.contains(c.getString(9))) {
////                    TotalQty += c.getInt(0);
////                    TotalNettSales += c.getDouble(1);
////                    //GrossSales += c.getDouble(8);
////                    GrossSales += c.getDouble(2);
////                    TotalBillDisount += c.getDouble(3) + c.getDouble(8);
////                    TotalTaxSales += c.getDouble(4);
////                    TotalRoundAdjSales += c.getDouble(5);
////                    ServiceChargesSales += c.getDouble(6);
////                    BillPaidCount += c.getInt(7);
////                }
//            }
//            c.close();
//        }
//        Integer BillCancelCount = 0;
////        Cursor c_productcancel = Query.XZDataReportProductCancel(starttime,endtime,"Report");
////        if (c_productcancel != null) {
////            TotalNettSalesProductCancel = 0.0;
////            BillCancelTotalQty = 0;
////            BillCancel = 0.0;
////            BillCancelCount = 0;
////            TotalBillDisountTax = 0.0;
//////            TotalBillDisountAmountCancel = 0.0;
//////            TotalBillServiceChargesCancel = 0.0;
////            while (c_productcancel.moveToNext()) {
////                TotalNettSalesProductCancel = c_productcancel.getDouble(13);
////                //BillCancelTotalQty += c_productcancel.getInt(1);
////                BillCancelTotalQty += c_productcancel.getInt(2);
////                BillCancelCount += c_productcancel.getInt(0);
////                BillCancel += c_productcancel.getDouble(13);
////                //BillTax = c.getString(14);
////                TotalBillDisountTax += c_productcancel.getDouble(14);
//////                TotalBillDisountAmountCancel += c_productcancel.getDouble(13);
//////                TotalBillServiceChargesCancel += c_productcancel.getDouble(12);
////            }
////            c_productcancel.close();
////        }
////
//        double grossTotal = TotalNettSales - TotalTaxSales;
////        Cursor Cursor_tax = Query.GetTax();
////        if (Cursor_tax != null){
////            if (Cursor_tax.moveToNext()){
////                String taxRate = Cursor_tax.getString(0);
////                String taxType = Cursor_tax.getString(1);
////                String taxName = Cursor_tax.getString(2);
////                if (taxType.equals("1")){
////                    double amt_inclusive = Query.calculateInclusive(Double.valueOf(TotalNettSales),Integer.parseInt(taxRate));
////                    TotalTaxSales = amt_inclusive;
//////                    if (amt_inclusive != 0.0) {
//////                        //String str_amt_inclusive = Query.ShowAmtMinusCorrectly(Double.valueOf(String.valueOf(amt_inclusive)));
//////                        //String str_inclusive = taxName.toUpperCase() + "(" + taxRate + "%)" + " SGD " + str_amt_inclusive + " Incl";
//////                        TotalTaxSales += Double.parseDouble(amt_inclusive);
//////                    }
////                }
//////                else {
//////                    if (TotalTaxes != 0.0) {
//////                        String str_exclusive = Query.ShowAmtMinusCorrectly(Double.valueOf(String.valueOf(TotalTaxes)));
//////                        //String str_exclusive = String.format("%.2f", Double.valueOf(TotalTaxes));
//////                        //String TaxExcul = validate_space(0, 21, taxName.toUpperCase() + "(" + taxRate + "%)" + " $", "Qty");
//////                        String TaxExcul = validate_space(0, 21, taxName.toUpperCase() + "(" + taxRate + "%)" + "  ", "Qty");
//////                        str += "\n" + TaxExcul + validate_space(0, 11, str_exclusive, "Price");
//////                    }
//////                }
////            }
////            Cursor_tax.close();
////        }
////        return new Object[]{BillPaidCount, TotalQty, TotalNettSales, TotalTaxSales, TotalRoundAdjSales, grossTotal, TotalBillDisount, ServiceChargesSales,BillCancelCount, BillCancelTotalQty, BillCancel, BillCancel, BillTax};
//
//        return new Object[]{BillPaidCount, TotalQty, TotalNettSales, TotalTaxSales, TotalRoundAdjSales, grossTotal, TotalBillDisount, ServiceChargesSales,BillCancelCount, BillCancelTotalQty, BillCancelTotalQty, BillCancel, BillTax};
//    }

//    public static Object[] CountDetailsBillTotal(long starttime, long endtime, long userid, long referid){
//        Integer TotalQty = 0;
//        String productName = "";
//        Double productPrice = 0.0;
//        String productRemarks = "";
//        //Total Sales//
//        Cursor c = Query.DetailsBillProductReport(starttime,endtime,"Report");
//        if (c != null) {
//            TotalQty = 0;
////            BillPaidCount = 0;
////            TotalNettSales = 0.0;
////            GrossSales = 0.0;
////            TotalBillDisount = 0.0;
////            TotalTaxSales = 0.0;
////            TotalRoundAdjSales = 0.0;
////            ServiceChargesSales = 0.0;
//            while (c.moveToNext()) {
////                TotalQty += c.getInt(0);
////                productName = c.getString(1);
////                productPrice += c.getDouble(2);
////                productRemarks = c.getString(3);
//
////                SUM(ProductQty),ProductName,SUM(ProductPrice),Remarks,BillNo
//                TotalQty = c.getInt(0);
//                productName = c.getString(1);
//                productPrice = c.getDouble(2);
//                productRemarks = c.getString(3);
//
//
//                Log.i("dfdsf____","____"+TotalQty);
//                Log.i("dfdsf____","____"+productName);
//                Log.i("dfdsf____","____"+productPrice);
//                Log.i("dfdsf____","____"+productRemarks);
//
//                return new Object[]{TotalQty, productName, productRemarks , productPrice};
//            }
//            c.close();
//        } else {
//            return new Object[]{TotalQty, productName, productPrice, productRemarks};
//        }
////        SUM(ProductQty),ProductName,SUM(ProductPrice),BillNo," +
////        "SUM(ItemDiscountAmount),ProductID,CategoryID,CategoryName,TaxID,TaxName,SUM(TaxAmount)," +
////                "DiscountName,DiscountTypeName,DiscountValue,ProductPrice,ID,OpenPriceStatus,TaxType,ProductQty,Remarks
////        return new Object[]{BillPaidCount, TotalQty, TotalNettSales, TotalTaxSales, TotalRoundAdjSales, grossTotal, TotalBillDisount, ServiceChargesSales,BillCancelCount, BillCancelTotalQty, BillCancel, BillCancel, BillTax};
////
////        Log.i("dfdsf____","____"+TotalQty);
////        Log.i("dfdsf____","____"+productName);
////        Log.i("dfdsf____","____"+productPrice);
////        Log.i("dfdsf____","____"+productRemarks);
////
////        return new Object[]{TotalQty, productName, productPrice, productRemarks};
////        return new Object[]{TotalQty, productName, productPrice, productRemarks};
//    }

    public static Object[] CountSalesTotal(long starttime, long endtime, long userid, long referid){

        int totalbill = 0;

        int totalqty = 0;
        double totalnett = 0;
        double totaltax = 0;
        double round = 0;
        double totalamt = 0;

        double discamt = 0;
        double surchgamt = 0;

        int totalbillcancel = 0;

        int totalqtycancel = 0;
        double totalcancelnett = 0;
        double totalcancelamt = 0;
        double totaltaxcancel = 0;
        ArrayList<Integer> Arr = new ArrayList<Integer>();

        ArrayList<Integer> BillIDArr = new ArrayList<Integer>();

        Double TotalNettSales = 0.0;
        Integer BillCancelTotalQty = 0;
        Integer BillCancelPaidCount = 0;
        Integer TotalQty = 0;
        Double BillCancel = 0.0;
        String BillTax = "0";
        Double TotalBillDisountTax = 0.0;
        Double GrossSales = 0.0;
        Double TaxTotal = 0.0;
        Double TotalBillDisount = 0.0;
        Double TotalRoundAdjSales = 0.0;
        Double ServiceChargesSales = 0.0;
        Double TotalTaxSales = 0.0;
        Integer TotalQtyProduct = 0;
        Double TotalNettSalesProduct = 0.0;
        String GrossSalesProduct = "0";
        String TaxTotalProduct = "0";
        String TotalBillDisountProduct = "0";
        String BillCancelTotalQtyProduct = "";
        String BillCancelProduct = "";
        String BillTaxProduct = "";
        Double TotalBillDisountTaxProduct = 0.0;
        Double TotalNettSalesProductCancel = 0.0;
        Integer PaymentTypeCount = 0;
        String PaymentTypeAmount = "0";
        String PaymentTypeName = "0";
//    Integer DicountCount = 0;
        String DicountName = "";
        String DicountAmount ="";
        Integer TotalTaxesCount = 0;
        Integer BillPaidCount = 0;
        Double TotalTaxesAmount = 0.0;
        Integer TotalQtyCategory = 0;
        Double TotalNettSalesCategory = 0.0;
        Double GrossSalesCategory = 0.0;
        String TaxTotalCategory = "0";
        Double TotalBillDisountCategory = 0.0;
        Integer BillCancelTotalQtyCategory = 0;
        String BillTaxCategory = "";
        Double TotalBillDisountTaxCategory = 0.0;
        Integer TotalQtyCategoryCancel = 0;
        Double TotalNettSalesCategoryCancel = 0.0;
        //Total Sales//
        Cursor c = Query.XZDataReportSales(starttime,endtime,"Report");
        if (c != null) {
            TotalQty = 0;
            BillPaidCount = 0;
            TotalNettSales = 0.0;
            GrossSales = 0.0;
            TotalBillDisount = 0.0;
            TotalTaxSales = 0.0;
            TotalRoundAdjSales = 0.0;
            ServiceChargesSales = 0.0;
            while (c.moveToNext()) {
                TotalQty += c.getInt(0);
                TotalNettSales += c.getDouble(1);
                //GrossSales += c.getDouble(8);
                GrossSales += c.getDouble(2);
                TotalBillDisount += c.getDouble(3) + c.getDouble(8);
                TotalTaxSales += c.getDouble(4);
                TotalRoundAdjSales += c.getDouble(5);
                ServiceChargesSales += c.getDouble(6);
                BillPaidCount += c.getInt(7);
            }
            c.close();
        }
        Integer BillCancelCount = 0;
        Cursor c_productcancel = Query.XZDataReportProductCancel(starttime,endtime,"Report");
        if (c_productcancel != null) {
            TotalNettSalesProductCancel = 0.0;
            BillCancelTotalQty = 0;
            BillCancel = 0.0;
            BillCancelCount = 0;
            TotalBillDisountTax = 0.0;
//            TotalBillDisountAmountCancel = 0.0;
//            TotalBillServiceChargesCancel = 0.0;
            while (c_productcancel.moveToNext()) {
                TotalNettSalesProductCancel = c_productcancel.getDouble(13);
                //BillCancelTotalQty += c_productcancel.getInt(1);
                BillCancelTotalQty += c_productcancel.getInt(2);
                BillCancelCount += c_productcancel.getInt(0);
                BillCancel += c_productcancel.getDouble(13);
                //BillTax = c.getString(14);
                TotalBillDisountTax += c_productcancel.getDouble(14);
//                TotalBillDisountAmountCancel += c_productcancel.getDouble(13);
//                TotalBillServiceChargesCancel += c_productcancel.getDouble(12);
            }
            c_productcancel.close();
        }

//        double grossTotal = TotalNettSales - TotalTaxSales;
        Cursor Cursor_tax = Query.GetTax();
        if (Cursor_tax != null){
            if (Cursor_tax.moveToNext()){
                String taxRate = Cursor_tax.getString(0);
                Integer taxType = Cursor_tax.getInt(1);
                String taxName = Cursor_tax.getString(2);
                //1=> None , 2=>Inclusive , 3=>Exclusive
                if (taxType == 2){


                    Cursor ctax = Query.XZDataReportSalesTax(starttime,endtime,"Report");
                    if (ctax != null) {
                        TotalTaxSales = 0.0;
                        Double TotalNettSalestax = 0.0;
                        while (ctax.moveToNext()) {
                            TotalNettSalestax = ctax.getDouble(1);


                            double amt_inclusive = Query.calculateInclusive(Double.valueOf(TotalNettSalestax), Integer.parseInt(taxRate));
                            //Log.i("TotalNettSalestax__","TotalNettSalestax__"+TotalNettSalestax+"__"+taxRate+"_"+amt_inclusive);
                            TotalTaxSales += Double.valueOf(String.format("%.2f", amt_inclusive));
                            Log.i("TotalNettSalestax__", "TotalNettSalestaxx__" + TotalNettSalestax + "__" + taxRate + "_" + amt_inclusive);
                        }
                        ctax.close();
                    }

//                    double amt_inclusive = Query.calculateInclusive(Double.valueOf(TotalNettSales),Integer.parseInt(taxRate));
//                    TotalTaxSales = amt_inclusive;
//                    if (amt_inclusive != 0.0) {
//                        //String str_amt_inclusive = Query.ShowAmtMinusCorrectly(Double.valueOf(String.valueOf(amt_inclusive)));
//                        //String str_inclusive = taxName.toUpperCase() + "(" + taxRate + "%)" + " SGD " + str_amt_inclusive + " Incl";
//                        TotalTaxSales += Double.parseDouble(amt_inclusive);
//                    }
                }
//                else {
//                    if (TotalTaxes != 0.0) {
//                        String str_exclusive = Query.ShowAmtMinusCorrectly(Double.valueOf(String.valueOf(TotalTaxes)));
//                        //String str_exclusive = String.format("%.2f", Double.valueOf(TotalTaxes));
//                        //String TaxExcul = validate_space(0, 21, taxName.toUpperCase() + "(" + taxRate + "%)" + " $", "Qty");
//                        String TaxExcul = validate_space(0, 21, taxName.toUpperCase() + "(" + taxRate + "%)" + "  ", "Qty");
//                        str += "\n" + TaxExcul + validate_space(0, 11, str_exclusive, "Price");
//                    }
//                }
            }
            Cursor_tax.close();
        }
        Log.i("dsf____","TotalTaxSales_sss__"+TotalTaxSales);
        double grossTotal = TotalNettSales - TotalTaxSales;
//        return new Object[]{BillPaidCount, TotalQty, TotalNettSales, TotalTaxSales, TotalRoundAdjSales, grossTotal, TotalBillDisount, ServiceChargesSales,BillCancelCount, BillCancelTotalQty, BillCancel, BillCancel, BillTax};
        return new Object[]{BillPaidCount, TotalQty, TotalNettSales, TotalTaxSales, TotalRoundAdjSales, grossTotal, TotalBillDisount, ServiceChargesSales,BillCancelCount, BillCancelTotalQty, BillCancelTotalQty, BillCancel, BillTax};
    }

    public static Object[] CountSalesPLUTotal(long starttime, long endtime, long userid, long referid){
        //ProductSalesQuery
        Object[] aa = null;
        int pluIDSales = 0;
        int pluQtySales = 0;
        double pluAmountSales = 0.0;
        double pluTaxSales = 0.0;
        double pluItemDiscountSales = 0.0;
        double pluServiceChargesSales = 0.0;
        String pluNameSales = "";
        //ProductSalesQuery
        Cursor c_product = Query.XZDataReportProduct(starttime,endtime,"Report");
        if (c_product != null) {
            pluIDSales = 0;
            pluQtySales = 0;
            pluAmountSales = 0.0;
            pluTaxSales = 0.0;
            pluItemDiscountSales = 0.0;
            pluServiceChargesSales = 0.0;
            pluNameSales = "";
            while (c_product.moveToNext()) {
                pluIDSales = c_product.getInt(0);
                pluNameSales = c_product.getString(1);
                pluQtySales = c_product.getInt(2);
                //TaxTotalProduct = c.getString(14);
                pluAmountSales = c_product.getDouble(3);
                pluTaxSales = c_product.getDouble(4);
                pluItemDiscountSales = c_product.getDouble(5);
                pluServiceChargesSales = c_product.getDouble(6);

               aa = new Object[]{pluIDSales, pluNameSales, pluQtySales, pluAmountSales, pluTaxSales, pluItemDiscountSales, pluServiceChargesSales, pluServiceChargesSales, pluServiceChargesSales, pluServiceChargesSales};

            }
            c_product.close();
        }   else {
            aa = new Object[]{pluIDSales, pluNameSales, pluQtySales, pluAmountSales, pluTaxSales, pluItemDiscountSales, pluServiceChargesSales, pluServiceChargesSales, pluServiceChargesSales, pluServiceChargesSales};

        }
        return aa;

    }
    public static List<PLUSalesData> CountPLUSalesTotal(long starttime, long endtime, boolean groupdept, long userid, long referid){
        List<PLUSalesData> paidpluobj = new ArrayList<PLUSalesData>();
//
//        Cursor bill = null;
//        try{
//        //String sql = "SELECT BillNo, Cancel FROM Bill WHERE CloseDateTime BETWEEN "+starttime+" and "+endtime+" ";
//        String str_report_query = "  strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch') " +
//                "BETWEEN strftime('"+Constraints.sqldateformat+"', "+starttime+" / 1000, 'unixepoch')"+ " " +
//                "AND strftime('"+Constraints.sqldateformat+"', "+endtime+" / 1000, 'unixepoch')";


//        String str_report_query = "  SALES.DateTime " +
//                "BETWEEN "+starttime+" "+ " " +
//                "AND "+endtime+" ";
        String str_report_query = "  SALES.DateTime " +
                "BETWEEN "+starttime+" "+ " " +
                "AND "+endtime+" "+
                //"AND SALES.STATUS = 'SALES'";
                "AND (SALES.STATUS = 'SALES' OR SALES.STATUS = 'REFUND') ";

//            //String sql = "SELECT BillNo, Cancel FROM Bill WHERE CloseDateTime BETWEEN "+starttime+" and "+endtime+" AND CloseDateTime IS NOT NULL";
//            String sql = "SELECT BillPLU.BillNo, Bill.Cancel FROM Bill " +
//                    " inner join BillPLU on  BillPLU.BillID = Bill.BillNo " +
//                    " WHERE "+str_report_query+" AND Bill.CloseDateTime IS NOT NULL Group By Bill.BillNo";
//            //String sql = "SELECT BillNo, Cancel FROM Bill WHERE "+str_report_query+" ";

//            Log.i("___sqlsqlsql1",sql);
//            sql += " ORDER BY Bill.BillNo ASC";
//            bill = DBFunc.Query(sql, false);
//            if(bill==null){
//                return null;
//            }
//            String  billNo = "'0'";
        String  _status_ = "0";
//            while(bill.moveToNext()){
//                billNo += ",'"+bill.getString(0)+"'";//get BillNo
//            }

        Cursor data = null;

//            try{
        //String sql_billplu = "SELECT ProductID, ProductName, ProductPrice,count(ProductQty), " +
        //String sql_billplu = "SELECT DetailsBillProduct.ProductID,DetailsBillProduct.ProductName,DetailsBillProduct.ProductPrice," +
        String sql_billplu = "SELECT DetailsBillProduct.ProductID,DetailsBillProduct.ProductName," +
                "sum(DetailsBillProduct.ProductPrice)," +
                "sum(DetailsBillProduct.ProductQty), " +
                "sum(DetailsBillProduct.ProductQty * DetailsBillProduct.ItemDiscountAmount)," +
                "DetailsBillProduct.Cancel,DetailsBillProduct.ProductQty,DetailsBillProduct.Remarks," +
                "SALES.STATUS " +
                "FROM DetailsBillProduct inner join SALES on SALES.BillNo = DetailsBillProduct.BillNo" +
                " WHERE " +
                str_report_query +
                " Group By strftime('"+Constraints.sqldateformat+"', SALES.DateTime / 1000, 'unixepoch')," +
                "DetailsBillProduct.ProductID,DetailsBillProduct.Cancel," +
                "DetailsBillProduct.OpenPriceStatus,DetailsBillProduct.Remarks ORDER BY DetailsBillProduct.ProductID ASC ";
//                        + " Group By ProductID ORDER BY ProductID ASC";
//                        " BillNo IN ("+billNo+") Group By ProductID ORDER BY ProductID ASC";
//                Cancel = 'SALES' AND
        Log.i("D___sql_billplu",sql_billplu);
        data = DBFunc.Query(sql_billplu, false);
        if(data==null){//return null if cannot connect to database and retrieve data
            return null;
        }


        while(data.moveToNext()){
            PLUSalesData existobj = null;
            int pluid = 0;
            String pluname = "";
            //double amount = 0.0;
            //double qty = 0.0;
            int qty = 0;
            double _discamt = 0.0;
            //int idx = data.getInt(0);
            Log.i("__pluid_","pluid__"+pluid);
            pluid = data.getInt(0);
            if (data.getString(7) != null && data.getString(7).trim().length() > 0){
                //pluname = String.valueOf(Html.fromHtml(data.getString(1) + "<br/>" +"(" + data.getString(7) + ")")) ;
                pluname = String.valueOf(Html.fromHtml(data.getString(1) +"(" + data.getString(7) + ")")) ;
            }else {
                pluname = data.getString(1);
            }
//            String plunamesql = "SELECT Price FROM PLU WHERE ID = "+pluid;
//            Log.i("plunamesql___","plunamesql_"+plunamesql);
//            Cursor cplunamesql = DBFunc.Query(plunamesql,true);
//            if (cplunamesql != null){
//                if  (cplunamesql.moveToNext()){
//                    amount = cplunamesql.getDouble(0);
//                }
//                cplunamesql.close();
//            }
//            Log.i("plunamesql___","plunamesqlamount_"+amount);
           // amount = data.getDouble(2);
            //qty = data.getDouble(3);
            qty = data.getInt(3);
            //amount = data.getDouble(2)/qty;
            //_discamt = data.getDouble(4);
            //_discamt = data.getDouble(4) * data.getDouble(3);
            _discamt = data.getDouble(4);
            //_status_ = data.getString(5);
            _status_ = data.getString(8);

            //double itemprice_after_itemdis_notincbilldis = data.getDouble(2);
            //double itemprice_itemdis = data.getDouble(3) * data.getDouble(4);

            //double nett = itemprice_after_itemdis_notincbilldis + itemprice_itemdis; // let a is nett
            //double nett = itemprice_after_itemdis_notincbilldis; // let a is nett
            //double nett = data.getDouble(2); // let a is nett

            //qty = data.getDouble(3); // let b
            //double itemprice_original = nett / qty; // let c is unitprice

            Log.i("dsf___nett","nett__"+pluname+"-"+data.getDouble(2)+"-"+qty+"-");


           // double _itemprice_base = (data.getDouble(2)) / data.getDouble(3);
            double unit_price = data.getDouble(2);

            if(existobj==null){
               // existobj = new PLUSalesData(pluid,pluname,amount, 0, "");
                existobj = new PLUSalesData(pluid,pluname,unit_price, 0, "");
                paidpluobj.add(existobj);
            }
            //double _itemprice_base = (qty*amount);
           // double _itemprice_base = (amount);
            //double _itemprice_base = (data.getDouble(2) + data.getDouble(4)) / data.getDouble(3);
            //double _itemprice_base = (data.getDouble(2)) / data.getDouble(3);
            //double _itemprice_disc = _itemprice_base;

            double _surchgamt = 0;
            Log.i("dsfsdf____","_status__ggggggg__"+_status_+"unit_price___"+unit_price);
            if(_status_.toUpperCase().equals(Constraints.SALES.toUpperCase()) ||
                    _status_.toUpperCase().equals(Constraints.REFUND.toUpperCase())){

                if (_status_.toUpperCase().equals(Constraints.REFUND.toUpperCase())) {
                    existobj.paidqty = (-1) * qty;
                    existobj.paidamountnotax = (-1) * unit_price;
                    //existobj.paidamountnotax +=   unit_price;
                    Log.i("dsfsdf____","_status__kkkjjjjj__"+existobj.paidamountnotax);
                    //existobj.paidamounttax += _itempricetax;
                    //existobj.paidamounttax += 0;
                    // existobj.nett +=   nett;
                    //existobj.unitprice +=  (-1) * unit_price;
                    //existobj.unitprice +=    unit_price;
                    //existobj.unitprice +=  (-1)  *  unit_price;
                    //existobj.unitprice +=  (-1)  *  5;
                    existobj.unitprice =  (-1)  * unit_price;
                    //existobj.paidtax += _tax;
                    existobj.paidtax = 0;
                    existobj.discamount = (-1) * _discamt;
                    existobj.surchgamt = _surchgamt;

                    existobj.cancelqty = 0;
                    existobj.cancelamtnotax = 0;
                    existobj.cancelamttax = 0;
                    existobj.canceltax = 0;
                }else {
                    existobj.paidqty = qty;
                    existobj.paidamountnotax = unit_price;
                    //existobj.paidamounttax += _itempricetax;
                    //existobj.paidamounttax += 0;
                    //existobj.nett += nett;
                    //existobj.unitprice += unit_price;
                    existobj.unitprice = unit_price;
                    //existobj.paidtax += _tax;
                    existobj.paidtax = 0;
                    existobj.discamount = _discamt;
                    existobj.surchgamt = _surchgamt;

                    existobj.cancelqty = 0;
                    existobj.cancelamtnotax = 0;
                    existobj.cancelamttax = 0;
                    existobj.canceltax = 0;
                }

//                if (_status_.toUpperCase().equals(Constraints.REFUND.toUpperCase())) {
//                    existobj.paidqty += (-1) * qty;
//                    existobj.paidamountnotax += (-1) * unit_price;
//                    //existobj.paidamountnotax +=   unit_price;
//                    Log.i("dsfsdf____","_status__kkkjjjjj__"+existobj.paidamountnotax);
//                    //existobj.paidamounttax += _itempricetax;
//                    //existobj.paidamounttax += 0;
//                   // existobj.nett +=   nett;
//                    //existobj.unitprice +=  (-1) * unit_price;
//                    //existobj.unitprice +=    unit_price;
//                    //existobj.unitprice +=  (-1)  *  unit_price;
//                    //existobj.unitprice +=  (-1)  *  5;
//                    existobj.unitprice =  (-1)  *  5;
//                    //existobj.paidtax += _tax;
//                    existobj.paidtax += 0;
//                    existobj.discamount += (-1) * _discamt;
//                    existobj.surchgamt += _surchgamt;
//
//                    existobj.cancelqty += 0;
//                    existobj.cancelamtnotax += 0;
//                    existobj.cancelamttax += 0;
//                    existobj.canceltax += 0;
//                }else {
//                    existobj.paidqty += qty;
//                    existobj.paidamountnotax += unit_price;
//                    //existobj.paidamounttax += _itempricetax;
//                    //existobj.paidamounttax += 0;
//                    //existobj.nett += nett;
//                    //existobj.unitprice += unit_price;
//                    existobj.unitprice = unit_price;
//                    //existobj.paidtax += _tax;
//                    existobj.paidtax += 0;
//                    existobj.discamount += _discamt;
//                    existobj.surchgamt += _surchgamt;
//
//                    existobj.cancelqty += 0;
//                    existobj.cancelamtnotax += 0;
//                    existobj.cancelamttax += 0;
//                    existobj.canceltax += 0;
//                }
            }else{

                Log.i("TAGG___","_status___"+_status_);
                existobj.paidqty += 0;
                existobj.paidamountnotax += 0;
                //existobj.paidamounttax += _itempricetax;
                //existobj.paidamounttax += 0;
                //existobj.nett += 0;
                existobj.unitprice += 0;
                //existobj.paidtax += _tax;
                existobj.paidtax += 0;
                existobj.discamount += 0;
                existobj.surchgamt += 0;

                existobj.cancelqty += qty;
                existobj.cancelamtnotax += unit_price;
                existobj.cancelamttax += 0;
                existobj.canceltax += 0;
            }

            Log.i("__existobj","existobj___"+existobj);
        }
//            }
//            finally{
//                if(data!=null)data.close();
//            }
//        }
//        finally{
//            if(bill!=null)bill.close();
//        }
        Log.i("__paidpluobj___","paidpluobjjj_____"+paidpluobj);
        return paidpluobj;
    }

//    public static List<PLUSalesData> CountPLUSalesTotal(long starttime, long endtime, boolean groupdept, long userid, long referid){
//        List<PLUSalesData> paidpluobj = new ArrayList<PLUSalesData>();
////
////        Cursor bill = null;
////        try{
////        //String sql = "SELECT BillNo, Cancel FROM Bill WHERE CloseDateTime BETWEEN "+starttime+" and "+endtime+" ";
////        String str_report_query = "  strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch') " +
////                "BETWEEN strftime('"+Constraints.sqldateformat+"', "+starttime+" / 1000, 'unixepoch')"+ " " +
////                "AND strftime('"+Constraints.sqldateformat+"', "+endtime+" / 1000, 'unixepoch')";
//
//
//        String str_report_query = "  SALES.DateTime " +
//                "BETWEEN "+starttime+" "+ " " +
//                "AND "+endtime+" ";
//
////            //String sql = "SELECT BillNo, Cancel FROM Bill WHERE CloseDateTime BETWEEN "+starttime+" and "+endtime+" AND CloseDateTime IS NOT NULL";
////            String sql = "SELECT BillPLU.BillNo, Bill.Cancel FROM Bill " +
////                    " inner join BillPLU on  BillPLU.BillID = Bill.BillNo " +
////                    " WHERE "+str_report_query+" AND Bill.CloseDateTime IS NOT NULL Group By Bill.BillNo";
////            //String sql = "SELECT BillNo, Cancel FROM Bill WHERE "+str_report_query+" ";
//
////            Log.i("___sqlsqlsql1",sql);
////            sql += " ORDER BY Bill.BillNo ASC";
////            bill = DBFunc.Query(sql, false);
////            if(bill==null){
////                return null;
////            }
////            String  billNo = "'0'";
//        String  _status_ = "0";
////            while(bill.moveToNext()){
////                billNo += ",'"+bill.getString(0)+"'";//get BillNo
////            }
//
//        Cursor data = null;
//
////            try{
//        //String sql_billplu = "SELECT ProductID, ProductName, ProductPrice,count(ProductQty), " +
//        String sql_billplu = "SELECT DetailsBillProduct.ProductID,DetailsBillProduct.ProductName,DetailsBillProduct.ProductPrice," +
//                "sum(DetailsBillProduct.ProductQty), " +
//                "DetailsBillProduct.ItemDiscountAmount,DetailsBillProduct.Cancel,DetailsBillProduct.ProductQty,DetailsBillProduct.Remarks," +
//                "SALES.STATUS " +
//                "FROM DetailsBillProduct inner join SALES on SALES.BillNo = DetailsBillProduct.BillNo" +
//                " WHERE " +
//                str_report_query +
//                " Group By strftime('"+Constraints.sqldateformat+"', SALES.DateTime / 1000, 'unixepoch'),DetailsBillProduct.ProductID,DetailsBillProduct.Cancel," +
//                "DetailsBillProduct.OpenPriceStatus,DetailsBillProduct.Remarks ORDER BY DetailsBillProduct.ProductID ASC ";
////                        + " Group By ProductID ORDER BY ProductID ASC";
////                        " BillNo IN ("+billNo+") Group By ProductID ORDER BY ProductID ASC";
////                Cancel = 'SALES' AND
//        Log.i("D___sql_billplu",sql_billplu);
//        data = DBFunc.Query(sql_billplu, false);
//        if(data==null){//return null if cannot connect to database and retrieve data
//            return null;
//        }
//
//
//        while(data.moveToNext()){
//            PLUSalesData existobj = null;
//            int pluid = 0;
//            String pluname = "";
//            double amount = 0.0;
//            double qty = 0.0;
//            double _discamt = 0.0;
//            //int idx = data.getInt(0);
//            Log.i("__pluid_","pluid__"+pluid);
//            pluid = data.getInt(0);
//            if (data.getString(7) != null && data.getString(7).trim().length() > 0){
//                pluname = String.valueOf(Html.fromHtml(data.getString(1) + "<br/>" +"(" + data.getString(7) + ")")) ;
//            }else {
//                pluname = data.getString(1);
//            }
//
//
//            amount = data.getDouble(2);
//            qty = data.getDouble(3);
//            _discamt = data.getDouble(4);
//            _status_ = data.getString(5);
//
//            if(existobj==null){
//                existobj = new PLUSalesData(pluid,pluname,amount, 0, "");
//                paidpluobj.add(existobj);
//            }
//            //double _itemprice_base = (qty*amount);
//            double _itemprice_base = (amount);
//            double _itemprice_disc = _itemprice_base;
//
//            double _surchgamt = 0;
//
//            if(_status_.toUpperCase().equals(Constraints.SALES.toUpperCase())){
//
//                existobj.paidqty += qty;
//                existobj.paidamountnotax += _itemprice_base;
//                //existobj.paidamounttax += _itempricetax;
//                //existobj.paidamounttax += 0;
//                existobj.paidamounttax += _itemprice_base;
//                //existobj.paidtax += _tax;
//                existobj.paidtax += 0;
//                existobj.discamount += _discamt;
//                existobj.surchgamt += _surchgamt;
//
//                existobj.cancelqty += 0;
//                existobj.cancelamtnotax += 0;
//                existobj.cancelamttax += 0;
//                existobj.canceltax += 0;
//            }else{
//
//                Log.i("TAGG___","_status___"+_status_);
//                existobj.paidqty += 0;
//                existobj.paidamountnotax += 0;
//                //existobj.paidamounttax += _itempricetax;
//                //existobj.paidamounttax += 0;
//                existobj.paidamounttax += 0;
//                //existobj.paidtax += _tax;
//                existobj.paidtax += 0;
//                existobj.discamount += 0;
//                existobj.surchgamt += 0;
//
//                existobj.cancelqty += qty;
//                existobj.cancelamtnotax += _itemprice_base;
//                existobj.cancelamttax += 0;
//                existobj.canceltax += 0;
//            }
//
//            Log.i("__existobj","existobj___"+existobj);
//        }
////            }
////            finally{
////                if(data!=null)data.close();
////            }
////        }
////        finally{
////            if(bill!=null)bill.close();
////        }
//        return paidpluobj;
//    }

    public static List<StockInRef> CountStockInData(long starttime, long endtime, long userid, long referid){
        List<StockInRef> paidpluobj = new ArrayList<StockInRef>();

//        String str_report_query = "  DateTime " +
//                "BETWEEN "+starttime+" "+ " " +
//                "AND "+endtime+" ";

        String str_report_query = " ";


        Cursor data = null;

        String sql_billplu = "SELECT PLUID, Qty, TransNo,DateTime " +
                "FROM StockIn " +
//                WHERE
        str_report_query +
                " Group By strftime('"+Constraints.sqldateformat+"', DateTime / 1000, 'unixepoch') ORDER BY PLUID ASC ";
        Log.i("D___sql_billplu",sql_billplu);
        data = DBFunc.Query(sql_billplu, true);
        if(data==null){//return null if cannot connect to database and retrieve data
            return null;
        }


        while(data.moveToNext()){
            StockInRef existobj = null;
            String pluid = "0";
            String pluname = "";
            String transNo = "";
            String dt = "";
            double amount = 0.0;
            double qty = 0.0;
            double _discamt = 0.0;
            //int idx = data.getInt(0);
            pluid = data.getString(0);
            Log.i("__pluid_","pluid__"+pluid);
            qty = data.getDouble(1);
            transNo = data.getString(2);
            dt = data.getString(3);

//            amount = data.getDouble(2);
//            qty = data.getDouble(3);
//            _discamt = data.getDouble(4);
//            _status_ = data.getString(5);

            if(existobj==null){
//                String pluid, String pluname, String pluprice, String transno, String qty, String dt
                existobj = new StockInRef(pluid,pluid,pluid,transNo,qty,dt);
                paidpluobj.add(existobj);

                existobj.pluid += pluid;
                existobj.qty += qty;
                existobj.transno += transNo;
                //existobj.paidamounttax += _itempricetax;
                //existobj.paidamounttax += 0;
                existobj.dt += dt;
            }
//            double _itemprice_base = (qty*amount);
//            double _itemprice_disc = _itemprice_base;

//            double _surchgamt = 0;
//
////            if(_status_.toUpperCase().equals(ENUM.SALES.toUpperCase())){
//
//                existobj.pluid += pluid;
//                existobj.qty += qty;
//                existobj.transno += transNo;
//                //existobj.paidamounttax += _itempricetax;
//                //existobj.paidamounttax += 0;
//                existobj.dt += dt;
//            }else{
//
//                Log.i("TAGG___","_status___"+_status_);
//                existobj.paidqty += 0;
//                existobj.paidamountnotax += 0;
//                //existobj.paidamounttax += _itempricetax;
//                //existobj.paidamounttax += 0;
//                existobj.paidamounttax += 0;
//                //existobj.paidtax += _tax;
//                existobj.paidtax += 0;
//                existobj.discamount += 0;
//                existobj.surchgamt += 0;
//
//                existobj.cancelqty += qty;
//                existobj.cancelamtnotax += _itemprice_base;
//                existobj.cancelamttax += 0;
//                existobj.canceltax += 0;
//            }

            Log.i("__existobj","existobj___"+existobj);
        }
//            }
//            finally{
//                if(data!=null)data.close();
//            }
//        }
//        finally{
//            if(bill!=null)bill.close();
//        }
        return paidpluobj;
    }

//    public static List<PLUSalesData> CountPLUSalesTotal(long starttime, long endtime, boolean groupdept, long userid, long referid){
//        List<PLUSalesData> paidpluobj = new ArrayList<PLUSalesData>();
////
//        Cursor bill = null;
//        try{
//            //String sql = "SELECT BillNo, Cancel FROM Bill WHERE CloseDateTime BETWEEN "+starttime+" and "+endtime+" ";
//            String str_report_query = "  strftime('"+Constraints.sqldateformat+"', Bill.CloseDateTime / 1000, 'unixepoch') BETWEEN strftime('"+Constraints.sqldateformat+"', "+starttime+" / 1000, 'unixepoch')"+ " AND strftime('"+Constraints.sqldateformat+"', "+endtime+" / 1000, 'unixepoch')";
//
//            //String sql = "SELECT BillNo, Cancel FROM Bill WHERE CloseDateTime BETWEEN "+starttime+" and "+endtime+" AND CloseDateTime IS NOT NULL";
//            String sql = "SELECT BillPLU.BillNo, Bill.Cancel FROM Bill " +
//                    " inner join BillPLU on  BillPLU.BillID = Bill.BillNo " +
//                    " WHERE "+str_report_query+" AND Bill.CloseDateTime IS NOT NULL Group By Bill.BillNo";
//            //String sql = "SELECT BillNo, Cancel FROM Bill WHERE "+str_report_query+" ";
//
//            Log.i("___sqlsqlsql1",sql);
//            sql += " ORDER BY Bill.BillNo ASC";
//            bill = DBFunc.Query(sql, false);
//            if(bill==null){
//                return null;
//            }
//            String  billNo = "'0'";
//            while(bill.moveToNext()){
//                billNo += ",'"+bill.getString(0)+"'";//get BillNo
//            }
//
//            Cursor data = null;
//
//            try{
//                String sql_billplu = "SELECT ProductID, ProductName, ProductPrice, count(ProductQty) ," +
//                        "ItemDiscountAmount " +
//                        "FROM Details_BillProduct WHERE " +
//                        " Cancel = 'SALES' AND BillNo IN ("+billNo+") Group By ProductID ORDER BY ProductID ASC";
//                Log.i("D___sql_billplu",sql_billplu);
//                data = DBFunc.Query(sql_billplu, false);
//                if(data==null){//return null if cannot connect to database and retrieve data
//                    return null;
//                }
//
//
//                while(data.moveToNext()){
//                    PLUSalesData existobj = null;
//                    int pluid = 0;
//                    String pluname = "";
//                    double amount = 0.0;
//                    double qty = 0.0;
//                    double _discamt = 0.0;
//                    //int idx = data.getInt(0);
//                    Log.i("__pluid_","pluid__"+pluid);
//                    pluid = data.getInt(0);
//                    pluname = data.getString(1);
//
//                    amount = data.getDouble(2);
//                    qty = data.getDouble(3);
//                    _discamt = data.getDouble(4);
//
//                if(existobj==null){
//                    existobj = new PLUSalesData(pluid,pluname,amount, 0, "");
//                    paidpluobj.add(existobj);
//                }
//                double _itemprice_base = (qty*amount);
//                double _itemprice_disc = _itemprice_base;
//
//                double _surchgamt = 0;
//                existobj.paidqty += qty;
//                existobj.paidamountnotax += _itemprice_base;
//                //existobj.paidamounttax += _itempricetax;
//                existobj.paidamounttax += 0;
//                //existobj.paidtax += _tax;
//                existobj.paidtax += 0;
//                existobj.discamount += _discamt;
//                existobj.surchgamt += _surchgamt;
//
//
//                    Log.i("__existobj","existobj___"+existobj);
//                }
//            }finally{
//                if(data!=null)data.close();
//            }
//        }finally{
//            if(bill!=null)bill.close();
//        }
//
//
//        //Cursor disc = null;
//
////                        if(!billcancel){
////                            try{
////                                disc = DBFunc.Query("SELECT idx, DiscID, Name, Value, Option FROM BillDisc WHERE BillNo = "+billID+" AND BillPLU_idx = "+idx+ " ORDER BY idx ASC LIMIT 1", false);
////                                if(disc != null){
////
////                                    if(disc.moveToNext()){
////                                        _discID = disc.getInt(1);
////                                        _discrate = disc.getDouble(3);
////                                        if(_discrate<0)_discrate = 0;
////                                        for(int i=0;i<_discopt.length;i++){
////                                            if(i<disc.getString(4).length()){
////                                                if(disc.getString(4).charAt(i) == '1'){
////                                                    _discopt[i] = true;
////                                                }
////                                            }
////                                        }
////                                    }
////                                    //disc.close();
////                                }else{//DB error!
////                                    return null;
////                                }
////                            }finally{
////                                if(disc!=null)disc.close();
////                            }
////
////                            if(_discID!=-1){
////                                if(!_discopt[0]){//is it bill discount?
////                                    if(_discopt[3]){//discount before tax
////                                        if(_discopt[1]){//amount discount
////                                            if(_discopt[2]){//discount is positive(surcharge)
////                                                _discamt = _discrate;
////                                                _itemprice_disc += _discamt;
////                                                _surchgamt = _discamt;
////                                            }else{//discount
////                                                if(_itemprice_disc-_discrate>0){
////                                                    _discamt = -_discrate;
////                                                    _itemprice_disc += _discamt;
////                                                }else{
////                                                    _discamt = -_itemprice_base;
////                                                    _itemprice_disc = 0;
////                                                }
////                                                //_discamt += _discamt;
////
////                                            }
////                                        }else{
////                                            if(_discrate>100)_discrate = 100;
////                                            _discamt = _itemprice_disc * (_discrate/100d);
////                                            if(_discopt[2]){
////                                                _itemprice_disc += _discamt;
////                                                _surchgamt = _discamt;
////                                            }else{
////                                                _discamt = -_discamt;
////                                                _itemprice_disc += _discamt;
////                                                //discamt += _discamt;
////                                            }
////                                        }
////                                    }
////                                }
////                            }
////                        }
////
////
////                        double _itempricetax = _itemprice_disc;
////                        double _tax = 0;
////
////                        Cursor plutax = null;
////                        try{
////                            plutax = DBFunc.Query("SELECT TaxID,Name,Acronym,Rate, Type FROM BillPLUTax WHERE BillPLU_idx = " + idx + " ORDER BY Seq ASC", false);
////                            if (plutax != null) {
////                                while (plutax.moveToNext()) {
////                                    if (plutax.getInt(4) == 0) {// not include(add on tax)
////                                        _tax = (_itempricetax * (plutax.getDouble(3) / 100f));
////                                        _itempricetax += _tax;
////                                    } else if (plutax.getInt(4) == 1) {// is inclusive (VAT)
////                                        _tax = (_itempricetax - (_itempricetax / (1f + (plutax.getDouble(3) / 100f))));
////                                        _itempricetax = (_itempricetax / (1f + (plutax.getDouble(3) / 100f)));
////                                    } else if (plutax.getInt(4) == 2) {// calculate on base price(add on tax)
////                                        _tax = (_itemprice_base * (plutax.getDouble(3) / 100f));
////                                        _itempricetax += _tax;
////                                    }
////                                }
////                                //plutax.close();
////                            }else{//failed to connect to database then we just close the previous and report back null data to let previous stack knows bad happening
////                                return null;
////                            }
////                        }finally{
////                            if(plutax!=null)plutax.close();
////                        }
////
////                        _itemprice_disc = MathUtil.Truncate(_itempricetax, 2, 0);
//
////                        if(!billcancel){
////                            if(_discID != -1){
////                                if(!_discopt[0]){//is it bill discount?
////                                    if(!_discopt[3]){//discount AFTER tax
////                                        if(_discopt[1]){//amount discount
////                                            if(_discopt[2]){//discount is positive(surcharge)
////                                                _discamt = _discrate;
////                                                _itemprice_disc += _discamt;
////                                                _surchgamt = _discamt;
////                                            }else{//discount
////                                                if(_itemprice_base-_discrate>0){
////                                                    _discamt = -_discrate;
////                                                    _itemprice_disc += _discamt;
////                                                }else{
////                                                    _discamt = -_itemprice_disc;
////                                                    _itemprice_disc = 0;
////                                                }
////                                                //discamt += _discamt;
////                                            }
////                                        }else{
////                                            if(_discrate>100)_discrate = 100;
////                                            _discamt = _itemprice_disc * (_discrate/100d);
////                                            if(_discopt[2]){//surcharge
////                                                _itemprice_disc += _discamt;
////                                                _surchgamt += _discamt;
////                                            }else{
////                                                _discamt = -_discamt;
////                                                _itemprice_disc += _discamt;
////                                                //discamt += _discamt;
////
////                                            }
////                                        }
////                                    }
////
////                                }
////                            }
////
////                        }
//
////                        _itemprice_disc = MathUtil.Truncate(_itemprice_disc, 2, 0);
//
//
//
////                        for(PLUSalesData obj:paidpluobj){
////                            int _tmpid = obj.pluid;
////                            String _tmpname = obj.name;
////                            double _tmpamount = obj.unitprice;
////                            if(groupdept){
////                                int _tmpdeptID = obj.deptid;
////                                String _tmpdeptName = obj.deptname;
////                                if(_tmpid == pluid && _tmpname.equalsIgnoreCase(_tmpname) && _tmpamount == amount && _tmpdeptID == deptID && _tmpdeptName.equalsIgnoreCase(deptName)){
////                                    existobj = obj;
////                                    break;
////                                }
////                            }else{
////                                if(_tmpid == pluid && _tmpname.equalsIgnoreCase(_tmpname) && _tmpamount == amount){
////                                    existobj = obj;
////                                    break;
////                                }
////                            }
////                        }
//
//
//
//
////                        boolean cancel = false;
////                        if(data.getInt(5)==1){
////                            cancel = true;
////                        }
//
////                        if(billcancel){
////                            cancel = true;
////                        }
////
////                        int deptID = -1;
////                        String deptName = "";
////                        if(!data.isNull(6)){
////                            deptID = data.getInt(6);
////                            if(!data.isNull(7)){
////                                deptName = data.getString(7);
////                            }
////                        }
////
////
//////                        if(cancel){
//////                            existobj.cancelqty += qty;
//////                            existobj.cancelamtnotax += _itemprice_base;
//////                            existobj.cancelamttax += _itempricetax;
//////                            existobj.canceltax += _tax;
//////                        }else{
////                            existobj.paidqty += qty;
////                            existobj.paidamountnotax += _itemprice_base;
////                            existobj.paidamounttax += _itempricetax;
////                            existobj.paidtax += _tax;
////                            existobj.discamount += _discamt;
////                            existobj.surchgamt += _surchgamt;
////                      //  }
////
////
////
////        PLUSalesData existobj = new PLUSalesData(1,"aaaaa",10.00, 0, "0");
////        paidpluobj.add(existobj);
////        //return new Object[]{BillPaidCount, TotalQty, TotalNettSales, TotalTaxSales, TotalRoundAdjSales, grossTotal, TotalBillDisount, ServiceChargesSales, 1, BillCancelTotalQty, BillCancel, BillCancel, BillTax};
//
//        return paidpluobj;
//    }

    public static Object[] CountCancelTotal(long starttime, long endtime, long userid){
        //List<DiscSalesData> discobj = new ArrayList<DiscSalesData>();
        int totalbillcancel = 0;
        int totalcancelpreqty = 0;
        int totalcancelpostqty = 0;
        double totalcancelprenett = 0;
        double totalcancelpostnett = 0;
        double totalcancelpreamt = 0;
        double totalcancelpostamt = 0;
        double totalcancelpretax = 0;
        double totalcancelposttax = 0;


        Cursor bill = null;
        try{
            String sql = "";
            if(userid==-1){
                sql = "SELECT BillNo, Round, Cancel FROM Bill WHERE CloseDateTime BETWEEN "+starttime+ " AND "+endtime+" AND CloseDateTime IS NOT NULL ORDER BY BillNo ASC";
            }else{
                sql = "SELECT BillNo, Round, Cancel FROM Bill WHERE CloseDateTime BETWEEN "+starttime+ " AND "+endtime+" AND CloseDateTime IS NOT NULL AND CashierID = "+userid+" ORDER BY BillNo ASC";
            }


            bill = DBFunc.Query(sql, false);
            if(bill==null){//DB Error
                return null;
            }

            while(bill.moveToNext()){

                int billID = bill.getInt(0);//get BillNo
                boolean billcancel = false;
                if(bill.getInt(2)==1){//bill is canceled
                    billcancel = true;
                }

                double _totalbillamount = 0;
                double _totalbillnettamt = 0;
                double _totalbilltaxamount = 0;
                double _totalbillqty = 0;

                Cursor data = null;

                try{
                    data = DBFunc.Query("SELECT idx, PLUID, Name, Amount, Qty, Cancel FROM BillPLU WHERE BillNo = "+billID+" ORDER BY idx ASC", false);
                    if(data==null){//return null if cannot connect to database and retrieve data
                        return null;
                    }

                    while(data.moveToNext()){
                        int idx = data.getInt(0);
                        double amount = data.getDouble(3);
                        double qty = data.getDouble(4);

                        boolean itemcancel = false;
                        if(data.getInt(5)==1){
                            itemcancel = true;
                        }

                        double _itemprice_base = (qty*amount);
                        double _itemprice_disc = _itemprice_base;

                        int _discID = -1;
                        double _discrate = 0;
                        boolean[] _discopt = new boolean[4];
                        double _discamt = 0;

                        if(!itemcancel){


                            Cursor disc = null;

                            try{
                                disc = DBFunc.Query("SELECT idx, DiscID, Name, Value, Option FROM BillDisc WHERE BillNo = "+billID+" AND BillPLU_idx = "+idx+ " ORDER BY idx ASC LIMIT 1", false);
                                if(disc != null){

                                    if(disc.moveToNext()){
                                        _discID = disc.getInt(1);
                                        _discrate = disc.getDouble(3);
                                        if(_discrate<0)_discrate = 0;
                                        for(int i=0;i<_discopt.length;i++){
                                            if(i<disc.getString(4).length()){
                                                if(disc.getString(4).charAt(i) == '1'){
                                                    _discopt[i] = true;
                                                }
                                            }
                                        }
                                    }
                                    //disc.close();
                                }else{//DB error!
                                    return null;
                                }
                            }finally{
                                if(disc!=null)disc.close();
                            }


                            if(_discID!=-1){
                                if(!_discopt[0]){//is it bill discount?
                                    if(_discopt[3]){//discount before tax
                                        if(_discopt[1]){//amount discount
                                            if(_discopt[2]){//discount is positive(surcharge)
                                                _discamt = _discrate;
                                                _itemprice_disc += _discamt;
                                            }else{//discount
                                                if(_itemprice_disc-_discrate>0){
                                                    _discamt = -_discrate;
                                                    _itemprice_disc += _discamt;
                                                }else{
                                                    _discamt = -_itemprice_base;
                                                    _itemprice_disc = 0;
                                                }
                                            }
                                        }else{
                                            if(_discrate>100)_discrate = 100;
                                            _discamt = _itemprice_disc * (_discrate/100d);
                                            if(_discopt[2]){
                                                _itemprice_disc += _discamt;
                                            }else{
                                                _discamt = -_discamt;
                                                _itemprice_disc += _discamt;
                                            }
                                        }
                                    }

                                }
                            }
                        }

                        double _itempricetax = _itemprice_disc;
                        double _tax = 0;

                        Cursor plutax = null;

                        try{
                            plutax = DBFunc.Query("SELECT TaxID,Name,Acronym,Rate, Type FROM BillPLUTax WHERE BillPLU_idx = " + idx + " ORDER BY Seq ASC", false);
                            if (plutax != null) {
                                while (plutax.moveToNext()) {
                                    if (plutax.getInt(4) == 0) {// not include(add on tax)
                                        _tax = (_itempricetax * (plutax.getDouble(3) / 100f));
                                        _itempricetax += _tax;
                                    } else if (plutax.getInt(4) == 1) {// is inclusive (VAT)
                                        _tax = (_itempricetax - (_itempricetax / (1f + (plutax.getDouble(3) / 100f))));
                                        _itempricetax = (_itempricetax / (1f + (plutax.getDouble(3) / 100f)));
                                    } else if (plutax.getInt(4) == 2) {// calculate on base price(add on tax)
                                        _tax = (_itemprice_base * (plutax.getDouble(3) / 100f));
                                        _itempricetax += _tax;
                                    }
                                }
                                //plutax.close();
                            }else{//failed to connect to database then we just close the previous and report back null data to let previous stack knows bad happening
                                return null;
                            }
                        }finally{
                            if(plutax!=null)plutax.close();
                        }

                        _itemprice_disc = MathUtil.Truncate(_itempricetax,2,0);

                        if(!itemcancel){
                            if(_discID != -1){
                                if(!_discopt[0]){//is it bill discount?
                                    if(!_discopt[3]){//discount AFTER tax
                                        if(_discopt[1]){//amount discount
                                            if(_discopt[2]){//discount is positive(surcharge)
                                                _discamt = _discrate;
                                                _itemprice_disc += _discamt;
                                            }else{//discount
                                                if(_itemprice_base-_discrate>0){
                                                    _itemprice_disc += _discamt;
                                                }else{
                                                    _discamt = -_itemprice_disc;
                                                    _itemprice_disc = 0;
                                                }
                                            }
                                            //existobj.count++;
                                        }else{
                                            if(_discrate>100)_discrate = 100;
                                            if(_discopt[2]){//surcharge
                                                _itemprice_disc += _discamt;
                                            }else{
                                                _discamt = -_discamt;
                                                _itemprice_disc += _discamt;

                                            }
                                        }
                                    }

                                }
                            }
                        }


                        _itemprice_disc = MathUtil.Truncate(_itemprice_disc,2,0);
                        if(itemcancel){
                            totalcancelprenett += _itemprice_base;
                            totalcancelpreamt += _itemprice_disc;
                            totalcancelpretax += _tax;
                            totalcancelpreqty+= qty;
                        }else{
                            _totalbillnettamt += _itemprice_base;
                            _totalbillamount += _itemprice_disc;
                            _totalbilltaxamount += _tax;
                            _totalbillqty += qty;
                        }

                    }
                }finally{
                    if(data!=null)data.close();
                }

                if(billcancel){

                    Cursor disc = null;
                    int _discID = -1;
                    double _discrate = 0;
                    boolean[] _discopt = new boolean[4];

                    try{
                        disc = DBFunc.Query("SELECT idx, DiscID, Name, Value, Option FROM BillDisc WHERE BillNo = "+billID+" AND BillPLU_idx IS NULL ORDER BY idx ASC LIMIT 1", false);


                        if(disc!=null){

                            if(disc.moveToNext()){
                                _discID = disc.getInt(1);
                                _discrate = disc.getDouble(3);
                                for(int i=0;i<_discopt.length;i++){
                                    if(i<disc.getString(4).length()){
                                        if(disc.getString(4).charAt(i) == '1'){
                                            _discopt[i] = true;
                                        }
                                    }
                                }
                            }
                        }
                    }finally{
                        if(disc!=null)disc.close();
                    }

                    double _discamt = 0;
                    double _pricedisc = _totalbillamount;

                    if(_discID != -1){
                        if(_discopt[0]){//is it bill discount?
                            //if(discopt[3]){//discount before tax
                            if(_discopt[1]){//amount discount
                                if(_discopt[2]){//discount is positive(surcharge)
                                    _discamt = _discrate;
                                    _pricedisc = _discamt;
                                }else{//discount
                                    if(_pricedisc-_discrate>0){
                                        _discamt = -_discrate;
                                        _pricedisc += _discamt;
                                    }else{
                                        _discamt = -_pricedisc;
                                        _pricedisc = 0;
                                    }
                                }
                            }else{
                                if(_discrate>100)_discrate = 100;
                                _discamt = _pricedisc * (_discrate/100d);
                                if(_discopt[2]){//surcharge
                                    _pricedisc -= _discamt;
                                }else{
                                    _discamt = -_discamt;
                                    _pricedisc += _discamt;
                                }

                            }

                        }

                    }

                    totalcancelpostamt += MathUtil.Truncate(_pricedisc,2,0);
                    totalcancelpostnett += _totalbillnettamt;
                    totalcancelposttax += _totalbilltaxamount;
                    totalcancelpostqty += _totalbillqty;
                    totalbillcancel++;
                }

            }
        }finally{
            if(bill!=null)bill.close();
        }

        //0              , 1                , 2                 , 3                , 4                , 5                 , 6                  , 7                 , 8
        //totalbillcancel, totalcancelpreqty, totalcancelprenett, totalcancelpreamt, totalcancelpretax, totalcancelpostqty, totalcancelpostnett, totalcancelpostamt, totalcancelposttax
        return new Object[]{totalbillcancel, totalcancelpreqty, totalcancelprenett, totalcancelpreamt, totalcancelpretax, totalcancelpostqty, totalcancelpostnett, totalcancelpostamt, totalcancelposttax};

    }


    public static List<TaxSalesData> CountTaxSalesTotal(long starttime, long endtime, long userid){
        List<TaxSalesData> taxobj = new ArrayList<TaxSalesData>();

        String sql = "";
        if(userid == -1){
            sql = "SELECT BillNo, Cancel FROM Bill WHERE CloseDateTime BETWEEN "+starttime+" and "+endtime+" AND CloseDateTime IS NOT NULL ORDER BY BillNo ASC";
        }else{
            sql = "SELECT BillNo, Cancel FROM Bill WHERE CloseDateTime BETWEEN "+starttime+" and "+endtime+" AND CloseDateTime IS NOT NULL AND CashierID = "+userid+" ORDER BY BillNo ASC";
        }

        Cursor bill = null;

        try{
            bill = DBFunc.Query(sql, false);
            if(bill==null){
                return null;
            }

            while(bill.moveToNext()){
                Cursor data = null;

                try{
                    data = DBFunc.Query("SELECT idx, Amount, Qty, Cancel FROM BillPLU WHERE BillNo = "+bill.getInt(0)+" AND Cancel = 0 ORDER BY idx ASC", false);
                    if(data==null){//return null if cannot connect to database and retrieve data
                        return null;
                    }

                    while(data.moveToNext()){
                        int pluidx = data.getInt(0);
                        double itemprice_base = data.getDouble(1) * data.getDouble(2);
                        double itemprice = itemprice_base;

                        boolean cancel = false;

                        if(data.getInt(3)!=0){
                            cancel = true;
                        }
                        if(bill.getInt(1)==1){
                            cancel = true;
                        }

                        if(!cancel){
                            Cursor disc = null;

                            int discID = -1;
                            double discrate = 0;
                            boolean[] discopt = new boolean[4];
                            double itemprice_disc = itemprice;

                            try{
                                disc = DBFunc.Query("SELECT idx, DiscID, Name, Value, Option FROM BillDisc WHERE BillPLU_idx = "+pluidx+ " ORDER BY idx ASC LIMIT 1", false);



                                if(disc != null){

                                    if(disc.moveToNext()){
                                        discID = disc.getInt(1);
                                        discrate = disc.getDouble(3);
                                        if(discrate<0)discrate = 0;
                                        for(int i=0;i<discopt.length;i++){
                                            if(i<disc.getString(4).length()){
                                                if(disc.getString(4).charAt(i) == '1'){
                                                    discopt[i] = true;
                                                }
                                            }
                                        }
                                    }
                                    //disc.close();
                                }else{//DB error!
                                    return null;
                                }
                            }finally{
                                if(disc!=null)disc.close();
                            }

                            double discamt = 0;

                            if(discID!=-1){

                                if(!discopt[0]){//is it bill discount?
                                    if(discopt[3]){//discount before tax
                                        if(discopt[1]){//amount discount
                                            if(discopt[2]){//discount is positive(surcharge)
                                                discamt = discrate;
                                                itemprice_disc += discamt;
                                            }else{//discount
                                                if(itemprice_disc-discrate>0){
                                                    discamt = -discrate;
                                                    itemprice_disc += discamt;
                                                }else{
                                                    discamt = -itemprice_base;
                                                    itemprice_disc = 0;
                                                }

                                            }
                                        }else{
                                            if(discrate>100)discrate = 100;
                                            discamt = itemprice_disc * (discrate/100d);
                                            if(discopt[2]){
                                                itemprice_disc += discamt;
                                            }else{
                                                discamt = -discamt;
                                                itemprice_disc += discamt;
                                            }
                                        }
                                    }

                                }
                                itemprice = itemprice_disc;
                            }
                        }

                        //double itemprice = itemprice_base;

                        Cursor plutax = null;

                        try{
                            plutax = DBFunc.Query("SELECT TaxID, Name, Rate, Type FROM BillPLUTax WHERE BillPLU_idx = "+pluidx + " ORDER BY Seq ASC", false);

                            if (plutax != null) {
                                while (plutax.moveToNext()) {
                                    TaxSalesData existobj = null;
                                    for(TaxSalesData obj:taxobj){
                                        if(obj.taxID == plutax.getInt(0) && obj.name.equalsIgnoreCase(plutax.getString(1)) && obj.type == plutax.getInt(3) && Double.valueOf(obj.rate).equals(Double.valueOf(plutax.getDouble(2)))){
                                            existobj = obj;
                                            break;
                                        }
                                    }

                                    if(existobj==null){
                                        existobj = new TaxSalesData(plutax.getInt(0), plutax.getString(1), plutax.getDouble(2), plutax.getInt(3));
                                        taxobj.add(existobj);
                                    }


                                    double _tax = 0;
                                    if(existobj.type == 0){//not include (add on tax)
                                        _tax = (itemprice * (existobj.rate / 100f));
                                        itemprice += _tax;
                                    } else if(existobj.type == 1){//is inclusive (VAT)

                                        itemprice = (itemprice / (1f + (existobj.rate / 100f)));
                                        _tax = itemprice - (itemprice / (1f + (existobj.rate / 100f)));
                                    } else if(existobj.type == 2){//calculate on base price(add on tax)
                                        _tax = (itemprice_base * (existobj.rate / 100f));
                                        itemprice += _tax;
                                    }

                                    if(cancel){
                                        existobj.cancelamt += itemprice_base;
                                        existobj.canceltaxed += _tax;
                                        existobj.cancelcount++;
                                    }else{
                                        existobj.paidamt += itemprice_base;
                                        existobj.paidtaxed += _tax;
                                        existobj.paidcount++;
                                    }


                                }
                                //plutax.close();
                            }
                        }finally{
                            if(plutax!=null)plutax.close();
                        }
                    }
                }finally{
                    if(data!=null)data.close();
                }
            }
        }finally{
            if(bill!=null)bill.close();
        }
        return taxobj;
    }

    public static List<PaymentSalesData> CountPaymentSalesTotal(long starttime, long endtime, long userid){


        List<PaymentSalesData> paidobj = new ArrayList<PaymentSalesData>();

        Cursor bill = null;

        try{
            String sql = "";
            if(userid == -1){
                sql = "SELECT BillNo, Cancel FROM Bill WHERE CloseDateTime BETWEEN "+starttime+" and "+endtime+" AND CloseDateTime IS NOT NULL ORDER BY BillNo ASC";
            }else{
                sql = "SELECT BillNo, Cancel FROM Bill WHERE CloseDateTime BETWEEN "+starttime+" and "+endtime+" AND CloseDateTime IS NOT NULL AND CashierID = "+userid+" ORDER BY BillNo ASC";
            }

            bill = DBFunc.Query(sql, false);
            if(bill==null){
                return null;
            }

            while(bill.moveToNext()){

                Cursor data = null;

                try{
                    data = DBFunc.Query("SELECT PaymentID, Name, Amount, Cancel FROM BillPayment WHERE BillNo = "+bill.getInt(0)+" AND Cancel = 0 ORDER BY idx ASC", false);
                    if(data==null){//return null if cannot connect to database and retrieve data
                        return null;
                    }

                    while(data.moveToNext()){
                        PaymentSalesData existobj = null;
                        boolean cancel = false;
                        if(data.getInt(3)==1){
                            cancel=true;
                        }
                        if(bill.getInt(1)==1){
                            cancel=true;
                        }
                        for(PaymentSalesData obj:paidobj){
                            if(obj.paymentid == data.getInt(0) && obj.name.equalsIgnoreCase(data.getString(1))){
                                existobj = obj;
                                break;
                            }
                        }

                        if(existobj==null){
                            existobj = new PaymentSalesData(data.getInt(0), data.getString(1));
                            paidobj.add(existobj);
                        }

                        if(cancel){
                            existobj.cancelcount++;
                            existobj.cancelamount += data.getDouble(2);
                        }else{
                            existobj.paycount++;
                            existobj.payamount += data.getDouble(2);
                        }
                    }
                }finally{
                    if(data!=null)data.close();
                }
            }
        }finally{
            if(bill!=null)bill.close();
        }
        return paidobj;
    }


//    public static List<DeptSalesData> CountDeptSalesTotal(long starttime, long endtime, long userid, long referid){
//        List<DeptSalesData> paiddeptobj = new ArrayList<DeptSalesData>();
//
//        Cursor bill = null;
//
//        try{
//            String sql = "SELECT BillNo, Cancel FROM Bill WHERE CloseDateTime BETWEEN "+starttime+" and "+endtime+" AND CloseDateTime IS NOT NULL";
//            if(userid == -1){
//                sql += " AND CashierID = "+userid;
//            }
//            if(referid != -2){
//                if(referid == -1){
//                    sql += " AND ReferID IS NULL";
//                }else{
//                    sql += " AND ReferID = "+referid;
//                }
//            }
//
//            sql += " ORDER BY BillNo ASC";
//
//            bill = DBFunc.Query(sql, false);
//            if(bill==null){
//                return null;
//            }
//
//            while(bill.moveToNext()){
//                int billID = bill.getInt(0);//get BillNo
//                boolean billcancel = false;
//
//                if(bill.getInt(1)==1){//bill is canceled
//                    billcancel = true;
//                }else{
//                    billcancel =false;
//                }
//
//                Cursor data = null;
//                try{
//                    data = DBFunc.Query("SELECT idx, Amount, Qty, Cancel, DeptID, DeptName FROM BillPLU WHERE BillNo = "+billID+" ORDER BY idx ASC", false);
//                    if(data==null){//return null if cannot connect to database and retrieve data
//                        return null;
//                    }
//
//                    while(data.moveToNext()){
//                        int idx = data.getInt(0);
//                        double amount = data.getDouble(1);
//                        double qty = data.getDouble(2);
//                        int deptID = -1;
//                        String deptName = "";
//
//                        if(!data.isNull(4)){
//                            deptID = data.getInt(4);
//                            if(!data.isNull(5)){
//                                deptName = data.getString(5);
//                            }
//                        }
//
//                        DeptSalesData existobj = null;
//                        for(DeptSalesData obj:paiddeptobj){
//                            if(obj.deptID == deptID && deptName.equalsIgnoreCase(obj.name)){
//                                existobj = obj;
//                                break;
//                            }
//                        }
//
//                        if(existobj==null){
//                            existobj = new DeptSalesData(deptID,deptName);
//                            paiddeptobj.add(existobj);
//                        }
//
//
//                        boolean cancel = false;
//                        if(data.getInt(3)==1){
//                            cancel = true;
//                        }
//                        if(billcancel){
//                            cancel=true;
//                        }
//
//                        double _itemprice_base = (qty*amount);
//                        double _itemprice_disc = _itemprice_base;
//
//                        int _discID = -1;
//                        double _discrate = 0;
//                        boolean[] _discopt = new boolean[4];
//                        double _discamt = 0;
//                        double _surchgamt = 0;
//                        Cursor disc = null;
//
//                        if(!billcancel){
//                            try{
//                                disc = DBFunc.Query("SELECT idx, DiscID, Name, Value, Option FROM BillDisc WHERE BillNo = "+billID+" AND BillPLU_idx = "+idx+ " ORDER BY idx ASC LIMIT 1", false);
//                                if(disc != null){
//                                    if(disc.moveToNext()){
//                                        _discID = disc.getInt(1);
//                                        _discrate = disc.getDouble(3);
//                                        if(_discrate<0)_discrate = 0;
//                                        for(int i=0;i<_discopt.length;i++){
//                                            if(i<disc.getString(4).length()){
//                                                if(disc.getString(4).charAt(i) == '1'){
//                                                    _discopt[i] = true;
//                                                }
//                                            }
//                                        }
//                                    }
//                                    //disc.close();
//                                }else{//DB error!
//                                    return null;
//                                }
//                            }finally{
//                                if(disc!=null)disc.close();
//                            }
//
//                            if(_discID!=-1){
//                                if(!_discopt[0]){//is it bill discount?
//                                    if(_discopt[3]){//discount before tax
//                                        if(_discopt[1]){//amount discount
//                                            if(_discopt[2]){//discount is positive(surcharge)
//                                                _discamt = _discrate;
//                                                _itemprice_disc += _discamt;
//                                                _surchgamt = _discamt;
//                                            }else{//discount
//                                                if(_itemprice_disc-_discrate>0){
//                                                    _discamt = -_discrate;
//                                                    _itemprice_disc += _discamt;
//                                                }else{
//                                                    _discamt = -_itemprice_base;
//                                                    _itemprice_disc = 0;
//                                                }
//                                            }
//                                        }else{
//                                            if(_discrate>100)_discrate = 100;
//                                            _discamt = _itemprice_disc * (_discrate/100d);
//                                            if(_discopt[2]){
//                                                _itemprice_disc += _discamt;
//                                                _surchgamt = _discamt;
//                                            }else{
//                                                _discamt = -_discamt;
//                                                _itemprice_disc += _discamt;
//                                            }
//                                        }
//                                    }
//                                }
//                            }
//                        }
//
//
//                        double _itempricetax = _itemprice_disc;
//                        double _tax = 0;
//
//                        Cursor plutax = null;
//                        try{
//                            plutax = DBFunc.Query("SELECT TaxID,Name,Acronym,Rate, Type FROM BillPLUTax WHERE BillPLU_idx = " + idx + " ORDER BY Seq ASC", false);
//                            if (plutax != null) {
//                                while (plutax.moveToNext()) {
//                                    if (plutax.getInt(4) == 0) {// not include(add on tax)
//                                        _tax = (_itempricetax * (plutax.getDouble(3) / 100f));
//                                        _itempricetax += _tax;
//                                    } else if (plutax.getInt(4) == 1) {// is inclusive (VAT)
//                                        _tax = (_itempricetax - (_itempricetax / (1f + (plutax.getDouble(3) / 100f))));
//                                        _itempricetax = (_itempricetax / (1f + (plutax.getDouble(3) / 100f)));
//                                    } else if (plutax.getInt(4) == 2) {// calculate on base price(add on tax)
//                                        _tax = (_itemprice_base * (plutax.getDouble(3) / 100f));
//                                        _itempricetax += _tax;
//                                    }
//                                }
//                                //plutax.close();
//                            }else{//failed to connect to database then we just close the previous and report back null data to let previous stack knows bad happening
//                                return null;
//                            }
//                        }finally{
//                            if(plutax!=null)plutax.close();
//                        }
//
//                        _itemprice_disc = MathUtil.Truncate(_itempricetax,2,0);
//                        if(!billcancel){
//                            if(_discID != -1){
//                                if(!_discopt[0]){//is it bill discount?
//                                    if(!_discopt[3]){//discount AFTER tax
//                                        if(_discopt[1]){//amount discount
//                                            if(_discopt[2]){//discount is positive(surcharge)
//                                                _discamt = _discrate;
//                                                _itemprice_disc += _discamt;
//                                                _surchgamt = _discamt;
//                                            }else{//discount
//                                                if(_itemprice_base-_discrate>0){
//                                                    _discamt = -_discrate;
//                                                    _itemprice_disc += _discamt;
//                                                }else{
//                                                    _discamt = -_itemprice_disc;
//                                                    _itemprice_disc = 0;
//                                                }
//                                                //discamt += _discamt;
//                                            }
//                                        }else{
//                                            if(_discrate>100)_discrate = 100;
//                                            _discamt = _itemprice_disc * (_discrate/100d);
//                                            if(_discopt[2]){//surcharge
//                                                _itemprice_disc += _discamt;
//                                                _surchgamt += _discamt;
//                                            }else{
//                                                _discamt = -_discamt;
//                                                _itemprice_disc += _discamt;
//                                                //discamt += _discamt;
//
//                                            }
//                                        }
//                                    }
//
//                                }
//                            }
//
//                        }
//
//                        _itemprice_disc = MathUtil.Truncate(_itemprice_disc,2,0);
//
//                        if(cancel){
//                            existobj.deptCancelQty += qty;
//                            existobj.deptCancelSales += _itemprice_base;
//                            existobj.deptCancelTaxSales += _itempricetax;
//                            existobj.deptCancelTax += _tax;
//                        }else{
//                            existobj.deptPaidQty += qty;
//                            existobj.deptPaidSales += _itemprice_base;
//                            existobj.deptTaxSales += _itempricetax;
//                            existobj.deptTax += _tax;
//                            existobj.discamount += _discamt;
//                            existobj.surchgamt += _surchgamt;
//                        }
//                    }
//                }finally{
//                    if(data!=null)data.close();
//                }
//            }
//        }finally{
//            if(bill!=null)bill.close();
//        }
//
//
//
//        return paiddeptobj;
//
//    }

    public static List<DiscSalesData> CountDiscSalesTotal(long starttime, long endtime, long userid){
        List<DiscSalesData> discobj = new ArrayList<DiscSalesData>();

        Cursor bill = null;

        try{
            String sql = "";
            if(userid == -1){
                sql = "SELECT BillNo FROM Bill WHERE CloseDateTime BETWEEN "+starttime + " AND "+endtime+" AND Cancel = 0 AND CloseDateTime IS NOT NULL ORDER BY BillNo ASC";
            }else{
                sql = "SELECT BillNo FROM Bill WHERE CloseDateTime BETWEEN "+starttime + " AND "+endtime+" AND Cancel = 0 AND CloseDateTime IS NOT NULL AND CashierID = "+userid+" ORDER BY BillNo ASC";
            }

            bill = DBFunc.Query(sql, false);
            if(bill==null){//DB Error
                return null;
            }

            while(bill.moveToNext()){
                int billID = bill.getInt(0);//get BillNo
                double totalbillamount = 0;

                Cursor data = null;

                try{
                    data = DBFunc.Query("SELECT idx, PLUID, Name, Amount, Qty, Cancel FROM BillPLU WHERE BillNo = "+billID+" AND Cancel = 0 ORDER BY idx ASC", false);
                    if(data==null){//return null if cannot connect to database and retrieve data
                        return null;
                    }


                    while(data.moveToNext()){
                        int idx = data.getInt(0);
                        //int pluid = data.getInt(1);
                        double amount = data.getDouble(3);
                        double qty = data.getDouble(4);

                        boolean cancel = false;
                        if(data.getInt(5)==1){
                            cancel = true;
                        }

                        if(!cancel){

                            double _itemprice_base = (qty*amount);
                            double _itemprice_disc = _itemprice_base;

                            DiscSalesData existobj = null;

                            Cursor disc = null;
                            try{
                                disc = DBFunc.Query("SELECT idx, DiscID, Name, Value, Option FROM BillDisc WHERE BillNo = "+billID+" AND BillPLU_idx = "+idx+ " ORDER BY idx ASC LIMIT 1", false);
                                if(disc != null){

                                    if(disc.moveToNext()){
                                        int discID = disc.getInt(1);
                                        String discname = disc.getString(2);
                                        double discvalue = disc.getDouble(3);
                                        boolean[] discopt = new boolean[4];
                                        if(discvalue<0)discvalue = 0;
                                        for(int i=0;i<discopt.length;i++){
                                            if(i<disc.getString(4).length()){
                                                if(disc.getString(4).charAt(i) == '1'){
                                                    discopt[i] = true;
                                                }
                                            }
                                        }


                                        for(DiscSalesData obj:discobj){
                                            if(obj.discID == discID && discname.equalsIgnoreCase(obj.name) && Double.compare(obj.rate, discvalue) == 0 && Arrays.equals(obj.option, discopt)){
                                                existobj = obj;
                                                break;
                                            }
                                        }

                                        if(existobj==null){
                                            existobj = new DiscSalesData(discID,discname,discvalue,discopt);
                                            discobj.add(existobj);
                                        }

                                    }
                                    //disc.close();
                                }else{//DB error!
                                    return null;
                                }
                            }finally{
                                if(disc!=null)disc.close();
                            }

                            double discamt = 0;

                            if(existobj!=null){

                                if(!existobj.option[0]){//is it bill discount?
                                    if(existobj.option[3]){//discount before tax
                                        if(existobj.option[1]){//amount discount
                                            if(existobj.option[2]){//discount is positive(surcharge)
                                                discamt = existobj.rate;
                                                _itemprice_disc += discamt;
                                            }else{//discount
                                                if(_itemprice_disc-existobj.rate>0){
                                                    discamt = -existobj.rate;
                                                    _itemprice_disc += discamt;
                                                }else{
                                                    discamt = -_itemprice_base;
                                                    _itemprice_disc = 0;
                                                }

                                            }
                                            existobj.count++;
                                        }else{
                                            if(existobj.rate>100)existobj.rate = 100;
                                            discamt = _itemprice_disc * (existobj.rate/100d);
                                            if(existobj.option[2]){
                                                _itemprice_disc += discamt;
                                            }else{
                                                discamt = -discamt;
                                                _itemprice_disc += discamt;
                                            }
                                            existobj.count++;
                                        }
                                    }

                                }
                            }

                            double _itempricetax = _itemprice_disc;
                            //double _itempricetax = _itemprice_base;
                            double _tax = 0;

                            Cursor plutax = null;
                            try{
                                plutax = DBFunc.Query("SELECT TaxID,Name,Acronym,Rate, Type FROM BillPLUTax WHERE BillPLU_idx = " + idx + " ORDER BY Seq ASC", false);
                                if (plutax != null) {
                                    while (plutax.moveToNext()) {
                                        if (plutax.getInt(4) == 0) {// not include(add on tax)
                                            _tax = (_itempricetax * (plutax.getDouble(3) / 100f));
                                            _itempricetax += _tax;
                                        } else if (plutax.getInt(4) == 1) {// is inclusive (VAT)
                                            _tax = (_itempricetax - (_itempricetax / (1f + (plutax.getDouble(3) / 100f))));
                                            _itempricetax = (_itempricetax / (1f + (plutax.getDouble(3) / 100f)));
                                        } else if (plutax.getInt(4) == 2) {// calculate on base price(add on tax)
                                            _tax = (_itemprice_base * (plutax.getDouble(3) / 100f));
                                            _itempricetax += _tax;
                                        }
                                    }
                                    //plutax.close();
                                }else{//failed to connect to database then we just close the previous and report back null data to let previous stack knows bad happening
                                    //data.close();
                                    return null;
                                }
                            }finally{
                                if(plutax!=null)plutax.close();
                            }


                            _itemprice_disc = _itempricetax;

                            if(existobj!=null){
                                if(!existobj.option[0]){//is it bill discount?
                                    if(!existobj.option[3]){//discount AFTER tax
                                        if(existobj.option[1]){//amount discount
                                            if(existobj.option[2]){//discount is positive(surcharge)
                                                discamt = existobj.rate;
                                                _itemprice_disc += discamt;
                                                existobj.count++;
                                            }else{//discount
                                                if(_itemprice_base-existobj.rate>0){
                                                    discamt = -existobj.rate;
                                                    _itemprice_disc += discamt;
                                                }else{
                                                    discamt = -_itemprice_disc;
                                                    _itemprice_disc = 0;
                                                }
                                            }
                                            existobj.count++;
                                        }else{
                                            if(existobj.rate>100)existobj.rate = 100;
                                            discamt = _itemprice_disc * (existobj.rate/100d);
                                            if(existobj.option[2]){
                                                _itemprice_disc += discamt;
                                            }else{
                                                discamt = -discamt;
                                                _itemprice_disc += discamt;
                                            }
                                            existobj.count++;
                                        }
                                    }

                                }

                                existobj.amount += discamt;


                            }

                            totalbillamount += _itemprice_disc;
                        }


                    }
                }finally{
                    if(data!=null)data.close();
                }


                DiscSalesData existobj = null;
                Cursor disc = null;
                try{
                    disc = DBFunc.Query("SELECT idx, DiscID, Name, Value, Option FROM BillDisc WHERE BillNo = "+billID+" AND BillPLU_idx IS NULL ORDER BY idx ASC LIMIT 1", false);
                    //Toast.makeText(CurrentActivity, "SELECT idx, DiscID, Name, Value, Option FROM BillDisc WHERE BillNo = "+billID+" AND BillPLU_idx IS NULL ORDER BY idx ASC LIMIT 1", Toast.LENGTH_LONG).show();
                    if(disc!=null){

                        if(disc.moveToNext()){
                            //int discidx = disc.getInt(0);
                            int discID = disc.getInt(1);
                            String discname = disc.getString(2);
                            double discvalue = disc.getDouble(3);
                            boolean[] discopt = new boolean[4];

                            for(int i=0;i<discopt.length;i++){
                                if(i<disc.getString(4).length()){
                                    if(disc.getString(4).charAt(i) == '1'){
                                        discopt[i] = true;
                                    }
                                }
                            }


                            for(DiscSalesData obj:discobj){
                                if(obj.discID == discID && discname.equalsIgnoreCase(obj.name) && Double.compare(obj.rate, discvalue) == 0 && Arrays.equals(obj.option, discopt)){
                                    existobj = obj;
                                    break;
                                }
                            }

                            if(existobj==null){
                                existobj = new DiscSalesData(discID,discname,discvalue,discopt);
                                discobj.add(existobj);
                            }
                        }
                        //disc.close();
                    }else{
                        return null;
                    }
                }finally{
                    if(disc!=null)disc.close();
                }


                double discamt = 0;
                double pricedisc = totalbillamount;


                if(existobj!=null){
                    if(existobj.option[0]){//is it bill discount?
                        //if(discopt[3]){//discount before tax
                        if(existobj.option[1]){//amount discount
                            if(existobj.option[2]){//discount is positive(surcharge)
                                discamt = existobj.rate;
                                pricedisc = discamt;
                            }else{//discount
                                if(pricedisc-existobj.rate>0){
                                    discamt = -existobj.rate;
                                    pricedisc += discamt;
                                }else{
                                    discamt = -pricedisc;
                                    pricedisc = 0;
                                }
                            }
                        }else{
                            if(existobj.rate>100)existobj.rate = 100;
                            discamt = pricedisc * (existobj.rate/100d);
                            if(existobj.option[2]){
                                pricedisc -= discamt;
                            }else{
                                discamt = -discamt;
                                pricedisc += discamt;
                            }
                        }

                    }
                    existobj.count++;
                    existobj.amount += discamt;

                }
            }

        }finally{
            if(bill!=null)bill.close();
        }

        return discobj;
    }


    public static List<ReferSalesData> CountReferSalesTotal(long starttime, long endtime, long userid){

        List<ReferSalesData> paidobj = new ArrayList<ReferSalesData>();
        Cursor refersql = null;

        String sql = "";
        try{

            if(userid == -1){
                sql = "SELECT DISTINCT ReferID, Refer FROM Bill WHERE CloseDateTime BETWEEN "+starttime+" and "+endtime+" AND CloseDateTime IS NOT NULL ORDER BY ReferID";
            }else{
                sql = "SELECT DISTINCT ReferID, Refer FROM Bill WHERE CloseDateTime BETWEEN "+starttime+" and "+endtime+" AND CloseDateTime IS NOT NULL AND CashierID = "+userid+" ORDER BY ReferID";
            }

            refersql = DBFunc.Query(sql, false);
            if(refersql==null){
                return null;
            }

            while(refersql.moveToNext()){
                boolean existobj = false;
                int referID = -1;
                String referName = "(none)";

                if(!refersql.isNull(0) && !refersql.isNull(1)){
                    referID = refersql.getInt(0);
                    referName = refersql.getString(1);

                }
                for(ReferSalesData obj:paidobj){

                    int _tmpid = obj.referID;
                    String _tmpname = obj.name;

                    if(_tmpid == referID && _tmpname.equalsIgnoreCase(referName)){
                        existobj = true;
                        break;
                    }
                }

                if(!existobj){
                    paidobj.add(new ReferSalesData(referID,referName));

                }

            }
        }finally{
            if(refersql!=null)refersql.close();
        }

        for(ReferSalesData referobj:paidobj){
            int totalbill = 0;

            double totalqty = 0;
            double totalnett = 0;
            double totaltax = 0;
            double round = 0;
            double totalamt = 0;

            double discamt = 0;
            double surchgamt = 0;

            int totalbillcancel = 0;

            double totalqtycancel = 0;
            double totalcancelnett = 0;
            double totalcancelamt = 0;
            double totalcanceltax = 0;


            Cursor bill = null;

            try{
                sql = "SELECT BillNo, Cancel FROM Bill WHERE CloseDateTime BETWEEN "+starttime+" and "+endtime+" AND CloseDateTime IS NOT NULL";
                if(referobj.referID==-1){
                    sql += " AND ReferID IS NULL";
                }else{
                    sql += " AND ReferID = "+referobj.referID + " AND Refer = '"+DBFunc.PurifyString(referobj.name)+"'";
                }

                if(userid != -1){
                    sql += " AND CashierID = "+userid;
                }

                sql += " ORDER BY BillNo ASC";

                bill = DBFunc.Query(sql, false);

                if(bill==null){
                    //Log.e("ERROR",sql);
                    return null;
                }

                while(bill.moveToNext()){

                    int billID = bill.getInt(0);//get BillNo
                    boolean billcancel = false;
                    if(bill.getInt(1)==1){//bill is canceled
                        billcancel = true;
                    }else{
                        billcancel =false;
                    }

                    if(!billcancel)round += bill.getDouble(1);
                    double _totalbillamount = 0;

                    if(billcancel){
                        totalbillcancel++;
                    }else{
                        totalbill++;
                    }

                    Cursor data = null;

                    try{
                        data = DBFunc.Query("SELECT idx, PLUID, Name, Amount, Qty, Cancel FROM BillPLU WHERE BillNo = "+billID+" AND Cancel = 0 ORDER BY idx ASC", false);
                        if(data==null){//return null if cannot connect to database and retrieve data
                            return null;
                        }


                        while(data.moveToNext()){
                            int idx = data.getInt(0);
                            double amount = data.getDouble(3);
                            double qty = data.getDouble(4);

                            boolean cancel = false;
                            if(data.getInt(5)==1){
                                cancel = true;
                            }

                            if(billcancel){
                                cancel = true;
                            }


                            double _itemprice_base = (qty*amount);
                            if(cancel){
                                totalcancelnett += _itemprice_base;
                                totalqtycancel+=qty;
                            }else{
                                totalnett += _itemprice_base;
                                totalqty+=qty;
                            }


                            double _itemprice_disc = _itemprice_base;


                            int _discID = -1;
                            double _discrate = 0;
                            boolean[] _discopt = new boolean[4];
                            double _discamt = 0;

                            Cursor disc = null;

                            if(!billcancel){
                                try{
                                    disc = DBFunc.Query("SELECT idx, DiscID, Name, Value, Option FROM BillDisc WHERE BillNo = "+billID+" AND BillPLU_idx = "+idx+ " ORDER BY idx ASC LIMIT 1", false);
                                    if(disc != null){

                                        if(disc.moveToNext()){
                                            _discID = disc.getInt(1);
                                            _discrate = disc.getDouble(3);
                                            if(_discrate<0)_discrate = 0;
                                            for(int i=0;i<_discopt.length;i++){
                                                if(i<disc.getString(4).length()){
                                                    if(disc.getString(4).charAt(i) == '1'){
                                                        _discopt[i] = true;
                                                    }
                                                }
                                            }


                                        }
                                        //disc.close();
                                    }else{//DB error!
                                        return null;
                                    }
                                }finally{
                                    if(disc!=null)disc.close();
                                }

                                if(_discID!=-1){

                                    if(!_discopt[0]){//is it bill discount?
                                        if(_discopt[3]){//discount before tax
                                            if(_discopt[1]){//amount discount
                                                if(_discopt[2]){//discount is positive(surcharge)
                                                    _discamt = _discrate;
                                                    _itemprice_disc += _discamt;
                                                    surchgamt += _discamt;
                                                }else{//discount
                                                    if(_itemprice_disc-_discrate>0){
                                                        _discamt = -_discrate;
                                                        _itemprice_disc += _discamt;
                                                    }else{
                                                        _discamt = -_itemprice_base;
                                                        _itemprice_disc = 0;
                                                    }
                                                    discamt += _discamt;

                                                }
                                            }else{
                                                if(_discrate>100)_discrate = 100;
                                                _discamt = _itemprice_disc * (_discrate/100d);
                                                if(_discopt[2]){
                                                    _itemprice_disc += _discamt;
                                                    surchgamt += _discamt;
                                                }else{
                                                    _discamt = -_discamt;
                                                    _itemprice_disc += _discamt;
                                                    discamt += _discamt;
                                                }
                                                //existobj.count++;
                                            }
                                        }

                                    }
                                }
                            }

                            double _itempricetax = _itemprice_disc;
                            //double _itempricetax = _itemprice_base;
                            double _tax = 0;

                            Cursor plutax = null;

                            try{
                                plutax = DBFunc.Query("SELECT TaxID,Name,Acronym,Rate, Type FROM BillPLUTax WHERE BillPLU_idx = " + idx + " ORDER BY Seq ASC", false);
                                if (plutax != null) {
                                    while (plutax.moveToNext()) {
                                        if (plutax.getInt(4) == 0) {// not include(add on tax)
                                            _tax = (_itempricetax * (plutax.getDouble(3) / 100f));
                                            _itempricetax += _tax;
                                        } else if (plutax.getInt(4) == 1) {// is inclusive (VAT)
                                            _tax = (_itempricetax - (_itempricetax / (1f + (plutax.getDouble(3) / 100f))));
                                            _itempricetax = (_itempricetax / (1f + (plutax.getDouble(3) / 100f)));
                                        } else if (plutax.getInt(4) == 2) {// calculate on base price(add on tax)
                                            _tax = (_itemprice_base * (plutax.getDouble(3) / 100f));
                                            _itempricetax += _tax;
                                        }


                                        if(cancel){
                                            totalcanceltax += _tax;
                                        }else{
                                            totaltax += _tax;
                                        }

                                    }
                                    //plutax.close();
                                }else{//failed to connect to database then we just close the previous and report back null data to let previous stack knows bad happening
                                    //data.close();
                                    return null;
                                }
                            }finally{
                                if(plutax!=null)plutax.close();
                            }

                            //_itemprice_disc = Double.parseDouble(df.format(_itempricetax));
                            _itemprice_disc = MathUtil.Truncate(_itempricetax,2,0);

                            if(!billcancel){
                                if(_discID != -1){
                                    if(!_discopt[0]){//is it bill discount?
                                        if(!_discopt[3]){//discount AFTER tax
                                            if(_discopt[1]){//amount discount
                                                if(_discopt[2]){//discount is positive(surcharge)
                                                    _discamt = _discrate;
                                                    _itemprice_disc += _discamt;
                                                    surchgamt += _discamt;
                                                    //existobj.count++;
                                                }else{//discount
                                                    if(_itemprice_base-_discrate>0){
                                                        discamt = -_discrate;
                                                        _itemprice_disc += _discamt;
                                                    }else{
                                                        _discamt = -_itemprice_disc;
                                                        _itemprice_disc = 0;
                                                    }
                                                    discamt += _discamt;
                                                }
                                                //existobj.count++;
                                            }else{
                                                if(_discrate>100)_discrate = 100;
                                                discamt = _itemprice_disc * (_discrate/100d);
                                                if(_discopt[2]){//surcharge
                                                    _itemprice_disc += _discamt;
                                                    surchgamt += _discamt;
                                                }else{
                                                    _discamt = -_discamt;
                                                    _itemprice_disc += _discamt;
                                                    discamt += _discamt;
                                                }
                                            }
                                        }

                                    }
                                }

                            }

                            //_itemprice_disc = Double.parseDouble(df.format(_itemprice_disc));
                            _itemprice_disc = MathUtil.Truncate(_itemprice_disc,2,0);
                            if(cancel){
                                totalcancelamt += _itemprice_disc;
                            }else{
                                _totalbillamount += _itemprice_disc;
                            }
                        }
                    }finally{
                        if(data!=null)data.close();
                    }

                    if(!billcancel){
                        Cursor disc = null;
                        int _discID = -1;
                        double _discrate = 0;
                        boolean[] _discopt = new boolean[4];

                        try{
                            disc = DBFunc.Query("SELECT idx, DiscID, Name, Value, Option FROM BillDisc WHERE BillNo = "+billID+" AND BillPLU_idx IS NULL ORDER BY idx ASC LIMIT 1", false);

                            if(disc!=null){

                                if(disc.moveToNext()){
                                    //int discidx = disc.getInt(0);
                                    _discID = disc.getInt(1);
                                    //String discname = disc.getString(2);
                                    _discrate = disc.getDouble(3);
                                    for(int i=0;i<_discopt.length;i++){
                                        if(i<disc.getString(4).length()){
                                            if(disc.getString(4).charAt(i) == '1'){
                                                _discopt[i] = true;
                                            }
                                        }
                                    }
                                }
                            }else{
                                return null;
                            }
                        }finally{
                            if(disc!=null)disc.close();
                        }

                        double _discamt = 0;
                        double _pricedisc = _totalbillamount;

                        if(_discID != -1){
                            if(_discopt[0]){//is it bill discount?
                                //if(discopt[3]){//discount before tax
                                if(_discopt[1]){//amount discount
                                    if(_discopt[2]){//discount is positive(surcharge)
                                        _discamt = _discrate;
                                        _pricedisc = _discamt;
                                        surchgamt += _discamt;
                                    }else{//discount
                                        if(_pricedisc-_discrate>0){
                                            _discamt = -_discrate;
                                            _pricedisc += _discamt;
                                        }else{
                                            _discamt = -_pricedisc;
                                            _pricedisc = 0;
                                        }
                                        discamt += _discamt;
                                    }
                                }else{
                                    if(_discrate>100)_discrate = 100;
                                    _discamt = _pricedisc * (_discrate/100d);
                                    if(_discopt[2]){//surcharge
                                        _pricedisc -= _discamt;
                                        surchgamt += _discamt;
                                    }else{
                                        _discamt = -_discamt;
                                        _pricedisc += _discamt;
                                        discamt += _discamt;
                                    }
                                }
                            }
                        }

                        totalamt += MathUtil.Truncate(_pricedisc,2,0);
                    }

                }
            }finally{
                if(bill!=null)bill.close();
            }


            referobj.paidbill = totalbill;
            referobj.paidqty = totalqty;
            referobj.paidamountnotax = totalnett;
            referobj.paidamounttax = totalamt;
            referobj.paidtax = totaltax;
            referobj.paidround = round;

            referobj.cancelbill = totalbillcancel;
            referobj.cancelqty = totalqtycancel;
            referobj.cancelamtnotax = totalcancelnett;
            referobj.cancelamttax =  totalcancelamt;
            referobj.canceltax = totalcanceltax;

            referobj.discamount = discamt;
            referobj.surchgamt = surchgamt;


        }

        return paidobj;
    }
}

