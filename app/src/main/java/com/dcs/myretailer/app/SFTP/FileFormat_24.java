package com.dcs.myretailer.app.SFTP;

import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.util.Log;
import android.view.View;

import androidx.annotation.RequiresApi;

import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FileFormat_24 {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public FileFormat_24(Context context, String billno, String fromDate, String toDate, String status) {

        FTPSync ftpSync = Query.getFTPSync();
        String ftpuuid = Query.findfieldNameByTableName("uuid","FTPSync", true);
        Log.i("dsf__","ftpuuid =___"+ftpuuid);
        String sales = "";
        if (fromDate != null && fromDate.length() > 0){
            sales = "SELECT strftime('%Y%m%d', DateTime / 1000 + (3600*8), 'unixepoch'),ZReadNo,BillNo,DateTime  " +
                    "FROM SALES " +
                    "where strftime('%Y%m%d', DateTime / 1000 + (3600*8), 'unixepoch')  " +
                    "BETWEEN '"+fromDate+"' AND '"+toDate+"'  GROUP BY  strftime('%Y%m%d', DateTime / 1000 + (3600*8), 'unixepoch') " +
                    " AND STATUS = 'SALES' ";
        } else {
            sales = "SELECT strftime('%Y%m%d', DateTime / 1000 + (3600*8), 'unixepoch')," +
                    "ZReadNo,BillNo,DateTime FROM SALES WHERE BillNo = '" + billno + "'";
        }
        Log.i("sfdsf___","sales_FileFormat_24__"+sales);
        Cursor c = DBFunc.Query(sales,false);
        String salesDateYMD = "";
        String billNo = "";
        String zReadNo = "";
        if (c != null) {
            //if (c.moveToNext()){
            while (c.moveToNext()){
                long dtvalue = c.getLong(3);
                Log.i("sdffd__dtvalue","sales_FileFormat_24__"+dtvalue);
                //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
                //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
                //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
                //Date date = new Date(dtvalue);
                //salesDateYMD = simpleDateFormat.format(date);
                salesDateYMD = c.getString(0);
                zReadNo = c.getString(1);
                billNo = c.getString(2);

                FTPFileFormat ftpFileFormat = Query.getFTPFileFormat(billNo);
                Integer fileGeneratedNum = 0;
                if (ftpFileFormat != null){
                    try {
                        fileGeneratedNum = Integer.parseInt(ftpFileFormat.getFileGenerateCount()) + 1 ;
                    } catch (Exception e){
                        fileGeneratedNum = 0;
                    }
                }

                Log.i("sfdsf___","salesDateYMD_dd__"+salesDateYMD);
                //String salesDateYMD = new SimpleDateFormat(Constraints.dateYMD_FTP).format(new Date());//date need to change
                String machineId = ftpSync.getMachineID(); //  9
                //String batchId = "116"; //  most -> 12
                String batchId = "";
                if (zReadNo != null) {
                    batchId = zReadNo; //  most -> 12
                } else {
                    batchId = "0";
                }
                String dmy = new SimpleDateFormat(Constraints.dateDMY_FTP).format(new Date());//date need to change//   -> 8
                String receiptCount = "0"; //12 (no decimal)
                double nettsbeforeSST = 0.00; //12 (999999999.99)
                double SST = 0.00; //12 (999999999.99)
                double discount = 0.00; //12 (999999999.99)
                double serviceCharges = 0.00; //12 (999999999.99)
                Integer noOfPax = 0; //12 (999999999999)
                double cash = 0.00; //12 (999999999.99)
                double touchAndGo = 0.00; //12 (999999999.99)
                double visa = 0.00; //12 (999999999.99)
                double master = 0.00; //12 (999999999.99)
                double amex = 0.00; //12 (999999999.99)
                double voucher = 0.00; //12 (999999999.99)
                double other = 0.00; //12 (999999999.99)
                String SST_Registered =  "N"; //12 (999999999.99)
                String str = "";
                for (int i = 0 ; i < 24; i ++ ){
                    String hrValue = "";
                    if (i < 10){
                        hrValue = "0"+i;
                    } else {
                        hrValue = String.valueOf(i);
                    }


                    Log.i("sfdsf___","hrValue___"+hrValue+"_____"+i);
                    String salesSQL = "select BillNo,PaymentTypeName,SUM(TotalNettSales),SUM(TotalTaxes), " +
                            "strftime('%Y%m%d %H:%M:%S', DateTime / 1000 + (3600*8), 'unixepoch'),count(*)," +
                            "SUM(IncTax),SUM(ExcluTax), strftime('%Y%m%d %H', DateTime / 1000 + (3600*8), 'unixepoch')," +
                            "SUM(TotalBillDisount) " +
                            "from sales " +
//                            " group by strftime('%Y%m%d %H', DateTime / 1000 + (3600*8), 'unixepoch')" ;
                            "where strftime('%Y%m%d %H', DateTime / 1000 + (3600*8), 'unixepoch') = '"+salesDateYMD+" "+hrValue+"' " +
                            " AND STATUS = 'SALES' ";
                    Log.i("Sdf___","dfd_"+salesSQL);
                    Cursor cSalesSQL = DBFunc.Query(salesSQL,false);
                    if (cSalesSQL != null) {
                        if (cSalesSQL.getCount() != 0){
                             receiptCount = "0"; //12 (no decimal)
                             nettsbeforeSST = 0.00; //12 (999999999.99)
                             SST = 0.00; //12 (999999999.99)
                             discount = 0.00; //12 (999999999.99)
                             serviceCharges = 0.00; //12 (999999999.99)
                             noOfPax = 0; //12 (999999999999)
                             cash = 0.00; //12 (999999999.99)
                             touchAndGo = 0.00; //12 (999999999.99)
                             visa = 0.00; //12 (999999999.99)
                             master = 0.00; //12 (999999999.99)
                             amex = 0.00; //12 (999999999.99)
                             voucher = 0.00; //12 (999999999.99)
                             other = 0.00; //12 (999999999.99)
//                             SST_Registered =  "N"; //12 (999999999.99)
                             SST_Registered =  chkTaxYORN();
                             Log.i("SST_Registered___","chk_tax"+SST_Registered);
                            if (cSalesSQL.moveToNext()){
                                String salesPType = cSalesSQL.getString(1);
                                Double nettafttax = cSalesSQL.getDouble(2);
                                Double tax = cSalesSQL.getDouble(3);
                                Double inctax = 0.0;
                                Double exclutax = 0.0;
                                try {

                                    inctax = Double.valueOf(String.format("%.2f", cSalesSQL.getDouble(6)));
                                } catch (Exception e){
                                    inctax = 0.0;
                                }
                                try {

                                    exclutax = Double.valueOf(String.format("%.2f", cSalesSQL.getDouble(7)));
                                } catch (Exception e){
                                    exclutax = 0.0;
                                }
//                                String status = cSalesSQL.getString(8);
                                if (inctax > 0.0){
                                    //SST_Registered = "Y";
                                    nettsbeforeSST = nettafttax - inctax;
                                    SST = inctax;
                                }else if (exclutax > 0.0){
                                    //SST_Registered = "Y";
                                    nettsbeforeSST = nettafttax - exclutax;
                                    SST = exclutax;
                                }
//                                nettsbeforeSST = nettafttax - tax;

                                String datehourly = cSalesSQL.getString(8);

                                discount = Double.valueOf(String.format("%.2f", cSalesSQL.getDouble(9)));

                                String strBP = "select strftime('%Y%m%d %H', PaymentDateTime / 1000 + (3600*8), 'unixepoch')," +
                                        "SUM(Amount),Name from BillPayment " +
                                        "where strftime('%Y%m%d %H', PaymentDateTime / 1000 + (3600*8), 'unixepoch') = '"+datehourly+"'" +
                                        "AND STATUS = 'SALES' group by Name";
                                Cursor strBPC = DBFunc.Query(strBP,false);
                                if (strBPC != null) {
                                     cash = 0.00; //12 (999999999.99)
                                     touchAndGo = 0.00; //12 (999999999.99)
                                     visa = 0.00; //12 (999999999.99)
                                     master = 0.00; //12 (999999999.99)
                                     amex = 0.00; //12 (999999999.99)
                                     voucher = 0.00; //12 (999999999.99)
                                     other = 0.00; //12 (999999999.99)
                                    while (strBPC.moveToNext()) {
                                        Double bPAmt = strBPC.getDouble(1);
                                        String bPName = strBPC.getString(2);

                                        try {
                                            if (bPName.toUpperCase().equals(Constraints.CASH)) {
                                                cash += Double.valueOf(String.format("%.2f", bPAmt));

                                            } else if (bPName.toUpperCase().equals(Constraints.TOUCH_AND_GO)) {
                                                touchAndGo += Double.valueOf(String.format("%.2f", bPAmt));

                                                Log.i("Sfdfs__touchAndGo +=",bPName+bPAmt+"_"+other);
                                            } else if (bPName.toUpperCase().equals(Constraints.VISA)) {
                                                visa += Double.valueOf(String.format("%.2f", bPAmt));

                                                Log.i("Sfdfs__visa +=",bPName+bPAmt+"_"+other);

                                            } else if (bPName.toUpperCase().equals(Constraints.MASTER)) {
                                                master += Double.valueOf(String.format("%.2f", bPAmt));

                                                Log.i("Sfdfs__mastero +=",bPName+bPAmt+"_"+other);
                                            } else if (bPName.toUpperCase().equals(Constraints.AMEX)) {
                                                amex += Double.valueOf(String.format("%.2f", bPAmt));

                                                Log.i("Sfdfs__tamex +=",bPName+bPAmt+"_"+other);
                                            } else if (bPName.toUpperCase().equals(Constraints.VOUCHER)) {
                                                voucher += Double.valueOf(String.format("%.2f", bPAmt));

                                                Log.i("Sfdfs__voucher +=",bPName+bPAmt+"_"+other);
                                            }
//                                            else if (bPName.toUpperCase().equals(Constraints.OTHER)) {
//                                                other += Double.valueOf(String.format("%.2f", bPAmt));
//                                            }
                                            else {
                                                other += Double.valueOf(String.format("%.2f", bPAmt));;

                                                Log.i("Sfdfs__tothero +=",bPName+bPAmt+"_"+other);
                                            }

                                        } catch (Exception e){
                                            Log.i("Excep__","e__"+e.getMessage());
                                            cash = 0.00; //12 (999999999.99)
                                            touchAndGo = 0.00; //12 (999999999.99)
                                            visa = 0.00; //12 (999999999.99)
                                            master = 0.00; //12 (999999999.99)
                                            amex = 0.00; //12 (999999999.99)
                                            voucher = 0.00; //12 (999999999.99)
                                            other = 0.00; //12 (999999999.99)
                                        }
                                    }
                                    strBPC.close();
                                }
//                                try {
//                                    if (salesPType.toUpperCase().equals(Constraints.CASH)) {
//                                        cash = Double.valueOf(String.format("%.2f", cSalesSQL.getDouble(2)));
//                                    } else if (salesPType.toUpperCase().equals(Constraints.TOUCH_AND_GO)) {
//                                        touchAndGo = Double.valueOf(String.format("%.2f", cSalesSQL.getDouble(2)));
//                                    } else if (salesPType.toUpperCase().equals(Constraints.VISA)) {
//                                        visa = Double.valueOf(String.format("%.2f", cSalesSQL.getDouble(2)));
//                                    } else if (salesPType.toUpperCase().equals(Constraints.MASTER)) {
//                                        master = Double.valueOf(String.format("%.2f", cSalesSQL.getDouble(2)));
//                                    } else if (salesPType.toUpperCase().equals(Constraints.AMEX)) {
//                                        amex = Double.valueOf(String.format("%.2f", cSalesSQL.getDouble(2)));
//                                    } else if (salesPType.toUpperCase().equals(Constraints.VOUCHER)) {
//                                        voucher = Double.valueOf(String.format("%.2f", cSalesSQL.getDouble(2)));
//                                    } else if (salesPType.toUpperCase().equals(Constraints.OTHER)) {
//                                        other = Double.valueOf(String.format("%.2f", cSalesSQL.getDouble(2)));
//                                    } else {
//                                        other = Double.valueOf(String.format("%.2f", cSalesSQL.getDouble(2)));;
//                                    }
//                                } catch (Exception e){
//                                    Log.i("Excep__","e__"+e.getMessage());
//                                    cash = 0.00; //12 (999999999.99)
//                                    touchAndGo = 0.00; //12 (999999999.99)
//                                    visa = 0.00; //12 (999999999.99)
//                                    master = 0.00; //12 (999999999.99)
//                                    amex = 0.00; //12 (999999999.99)
//                                    voucher = 0.00; //12 (999999999.99)
//                                    other = 0.00; //12 (999999999.99)
//                                }

                                receiptCount = cSalesSQL.getString(5);
                            }
                        }else {
                            cash = 0.00; //12 (999999999.99)
                            touchAndGo = 0.00; //12 (999999999.99)
                            visa = 0.00; //12 (999999999.99)
                            master = 0.00; //12 (999999999.99)
                            amex = 0.00; //12 (999999999.99)
                            voucher = 0.00; //12 (999999999.99)
                            other = 0.00; //12 (999999999.99)
                        }

                        cSalesSQL.close();
                    }
                    double taxRCash = getTaxMinusValue(cash);
                    double taxRtouchAndGo= getTaxMinusValue(touchAndGo);
                    double taxRvisa = getTaxMinusValue(visa);
                    double taxRmaster = getTaxMinusValue(master);
                    double taxRamex = getTaxMinusValue(amex);
                    double taxRvoucher = getTaxMinusValue(voucher);
                    double taxRother= getTaxMinusValue(other);

                    try {
                        cash = Double.valueOf(String.format("%.2f", cash - taxRCash));
                    } catch (Exception e) {
                        cash = 0.00; //12 (999999999.99)
                    }
                    try {
                        touchAndGo = Double.valueOf(String.format("%.2f", touchAndGo - taxRtouchAndGo));
                    } catch (Exception e){
                        touchAndGo = 0.00;
                    }
                    try {
                        visa = Double.valueOf(String.format("%.2f", visa - taxRvisa));

                    } catch (Exception e){
                        visa = 0.00;
                    }

                    try {

                        master = Double.valueOf(String.format("%.2f", master - taxRmaster));

                    } catch (Exception e){
                        master = 0.00;
                    }

                    try {
                        amex = Double.valueOf(String.format("%.2f", amex - taxRamex));

                    } catch (Exception e){
                        amex = 0.00;
                    }

                     try {
                         voucher = Double.valueOf(String.format("%.2f", voucher - taxRvoucher));

                     } catch (Exception e){
                         voucher = 0.00;
                     }

                        try {
                            other = Double.valueOf(String.format("%.2f", other - taxRother));

                        } catch (Exception e){
                            other = 0.00;
                        }
                    try {
                        nettsbeforeSST = Double.valueOf(String.format("%.2f", nettsbeforeSST));

                    } catch (Exception e){
                        nettsbeforeSST = 0.00;
                    }
//                    str += machineId+"|"+batchId+"|"+dmy+"|"+hrValue+"|"+receiptCount+"|"+nettsbeforeSST+"|"+SST+"|"+discount+
//                            "|"+serviceCharges+"|"+noOfPax+"|"+cash+"|"+touchAndGo+"|"+visa+"|"+master+"|"+amex+"|"+
//                            voucher+"|"+other+"|"+SST_Registered+"\n";

                    String nettsbeforeSSTStr = String.format("%.2f", nettsbeforeSST);
                    String SSTStr = String.format("%.2f", SST);
                    String discountStr = String.format("%.2f", discount);
                    String serviceChargesStr = String.format("%.2f", serviceCharges);
                    String cashStr = String.format("%.2f", cash);
                    String touchAndGoStr = String.format("%.2f", touchAndGo);
                    String visaStrvisaStr = String.format("%.2f", visa);
                    String masterStr = String.format("%.2f", master);
                    String amexStr = String.format("%.2f", amex);
                    String voucherStr = String.format("%.2f", voucher);
                    String otherStr = String.format("%.2f", other);

                    Log.i("dsfdsf___","batchId_"+batchId);
                    Log.i("dsfdsf___","nettsbeforeSSTStr_"+nettsbeforeSSTStr);
                    Log.i("dsfdsf___","SSTStr_"+SSTStr);
                    Log.i("dsfdsf___","discountStr_"+discountStr);
                    Log.i("dsfdsf___","dserviceChargesStr_"+serviceChargesStr);
                    Log.i("dsfdsf___","cashStr_"+cashStr);
                    Log.i("dsfdsf___","touchAndGoStr_"+touchAndGoStr);
                    Log.i("dsfdsf___","visaStrvisaStr_"+visaStrvisaStr);
                    Log.i("dsfdsf___","masterStr_"+masterStr);
                    Log.i("dsfdsf___","amexStr_"+amexStr);
                    Log.i("dsfdsf___","voucherStr_"+voucherStr);
                    Log.i("dsfdsf___","otherStr_"+otherStr);

                    str += machineId+"|"+batchId+"|"+dmy+"|"+hrValue+"|"+receiptCount+"|"+
                            nettsbeforeSSTStr+"|"+SSTStr+"|"+discountStr+
                            "|"+serviceChargesStr+"|"+noOfPax+"|"+cashStr+"|"+touchAndGoStr+"|"+visaStrvisaStr+"|"+
                            masterStr+"|"+amexStr+"|"+
                            voucherStr+"|"+otherStr+"|"+
                            SST_Registered+"\n";

                }
                String fileName = "H"+machineId+"_"+salesDateYMD+".txt";
                Log.i("dfds____","str___"+str);
                FPTFileCreateActivity.writeGeneratedFile(context,fileName,str,status); //2-	24- Waterway Point

                if (ftpFileFormat == null) {
                    FTPFileFormat createFtpFileFormat = new FTPFileFormat();
                    createFtpFileFormat.setFileFormatTypeNo(Constraints.FILEFORMAT_24);
                    createFtpFileFormat.setFileGenerateCount(fileGeneratedNum.toString());
                    createFtpFileFormat.setFileGenerateCountString("");
                    createFtpFileFormat.setFileName(fileName);
                    createFtpFileFormat.setFTPSyncUUID(ftpuuid);
                    createFtpFileFormat.setZReadNo(zReadNo);
                    createFtpFileFormat.setBillNo(billNo);

                    Query.saveFTPFileFormat(context,createFtpFileFormat);
                }
//        writeGeneratedFile(this,"H18000061_20220426.txt",str); //2-	24- Waterway Point
            }
            c.close();
        }

    }

    public static double getTaxMinusValue(double cash) {
        String inctaxValue = "0";
        String exctaxValue = "0";
        Integer taxRate = 0;
        String str_inc_taxname = "";
        String str_exc_taxname = "";
        Double inctax = 0.0;
        Double amt_inclusive = 0.0;
        Double amt_exclusive = 0.0;
        Cursor Cursor_tax = Query.GetTax();
        if (Cursor_tax != null){
            if (Cursor_tax.moveToNext()){
                taxRate = Cursor_tax.getInt(0);
                Integer taxType = Cursor_tax.getInt(1);
                str_inc_taxname = Cursor_tax.getString(2);
                str_exc_taxname = Cursor_tax.getString(2);

                //1=> None , 2=>Inclusive , 3=>Exclusive
                if (taxType == 2){
                    amt_inclusive = 0.0;
                    amt_inclusive = Query.calculateInclusive(cash,taxRate);
                    amt_exclusive = 0.0;
                }else if (taxType == 3) {
                    amt_exclusive = 0.0;
                    amt_exclusive = Query.calculateExclusive(cash,taxRate);
                    amt_inclusive = 0.0;
                }
            }
            Cursor_tax.close();
        }
        if (amt_inclusive != 0.0){
            inctaxValue = String.format("%.2f", amt_inclusive);
        }
        if (amt_exclusive != 0.0){
            str_exc_taxname = str_exc_taxname.toUpperCase()+ "(" + taxRate+"%)";
            exctaxValue =  String.format("%.2f", Double.valueOf(amt_exclusive));
        }
        try {
            return Double.valueOf(inctaxValue);
        } catch (Exception e){
            return 0.0;
        }
    }

    private String chkTaxYORN() {
        String chkTax = "N";
        Cursor Cursor_tax = Query.GetTax();
        if (Cursor_tax != null){
            if (Cursor_tax.moveToNext()){
                Integer taxType = Cursor_tax.getInt(1);

                //1=> None , 2=>Inclusive , 3=>Exclusive
                if (taxType == 2){
                    chkTax = "Y";
                }else if (taxType == 3) {
                    chkTax = "Y";
                }
            }
            Cursor_tax.close();
        }
        return chkTax;
    }
}
