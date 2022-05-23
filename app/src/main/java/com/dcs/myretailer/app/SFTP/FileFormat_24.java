package com.dcs.myretailer.app.SFTP;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FileFormat_24 {

    public FileFormat_24(Context context, String billno, String fromDate, String toDate) {

        FTPSync ftpSync = Query.getFTPSync();
        String ftpuuid = Query.findfieldNameByTableName("uuid","FTPSync", true);
        Log.i("dsf__","ftpuuid =___"+ftpuuid);
        String sales = "";
        if (fromDate != null && fromDate.length() > 0){
            sales = "SELECT strftime('%Y%m%d', DateTime / 1000 + (3600*8), 'unixepoch'),ZReadNo,BillNo,DateTime  " +
                    "FROM SALES where strftime('%Y%m%d', DateTime / 1000 + (3600*8), 'unixepoch')  " +
                    "BETWEEN '"+fromDate+"' AND '"+toDate+"'  GROUP BY  strftime('%Y%m%d', DateTime / 1000 + (3600*8), 'unixepoch') ";
        } else {
            sales = "SELECT strftime('%Y%m%d', DateTime / 1000 + (3600*8), 'unixepoch'),ZReadNo,BillNo,DateTime FROM SALES WHERE BillNo = '" + billno + "'";
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
                            "strftime('%Y%m%d %H:%M:%S', DateTime / 1000 + (3600*8), 'unixepoch'),count(*),IncTax,ExcluTax " +
                            "from sales " +
//                            " group by strftime('%Y%m%d %H', DateTime / 1000 + (3600*8), 'unixepoch')" ;
                            "where strftime('%Y%m%d %H', DateTime / 1000 + (3600*8), 'unixepoch') = '"+salesDateYMD+" "+hrValue+"'";
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
                             SST_Registered =  "N"; //12 (999999999.99)
                            if (cSalesSQL.moveToNext()){
                                String salesPType = cSalesSQL.getString(1);
                                Double nettafttax = cSalesSQL.getDouble(2);
                                Double tax = cSalesSQL.getDouble(3);
                                Double inctax = cSalesSQL.getDouble(6);
                                Double exclutax = cSalesSQL.getDouble(7);
                                if (inctax > 0.0){
                                    SST_Registered = "Y";
                                    nettsbeforeSST = nettafttax - inctax;
                                    SST = inctax;
                                }else if (exclutax > 0.0){
                                    SST_Registered = "Y";
                                    nettsbeforeSST = nettafttax - exclutax;
                                    SST = exclutax;
                                }
//                                nettsbeforeSST = nettafttax - tax;



                                Log.i("sfdsf___","salesPType___"+salesPType);
                                Log.i("sfdsf___","nettsbeforeSST___"+nettsbeforeSST);
                                Log.i("sfdsf___","inctax___"+inctax);
                                Log.i("sfdsf___","exclutax___"+exclutax);
                                Log.i("sfdsf___","SST___"+SST);
                                try {
                                    if (salesPType.toUpperCase().equals(Constraints.CASH)) {
                                        cash = cSalesSQL.getDouble(2);
                                    } else if (salesPType.toUpperCase().equals(Constraints.TOUCH_AND_GO)) {
                                        touchAndGo = cSalesSQL.getDouble(2);
                                    } else if (salesPType.toUpperCase().equals(Constraints.VISA)) {
                                        visa = cSalesSQL.getDouble(2);
                                    } else if (salesPType.toUpperCase().equals(Constraints.MASTER)) {
                                        master = cSalesSQL.getDouble(2);
                                    } else if (salesPType.toUpperCase().equals(Constraints.AMEX)) {
                                        amex = cSalesSQL.getDouble(2);
                                    } else if (salesPType.toUpperCase().equals(Constraints.VOUCHER)) {
                                        voucher = cSalesSQL.getDouble(2);
                                    } else if (salesPType.toUpperCase().equals(Constraints.OTHER)) {
                                        other = cSalesSQL.getDouble(2);
                                    } else {
                                        other = cSalesSQL.getDouble(2);
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
                    str += machineId+"|"+batchId+"|"+dmy+"|"+hrValue+"|"+receiptCount+"|"+nettsbeforeSST+"|"+SST+"|"+discount+"|"+serviceCharges+"|"+noOfPax+"|"+cash+"|"+touchAndGo+"|"+visa+"|"+master+"|"+amex+"|"+voucher+"|"+other+"|"+SST_Registered+"\n";
                }
                String fileName = "H"+machineId+"_"+salesDateYMD+".txt";
                Log.i("dfds____","str___"+str);
                FPTFileCreateActivity.writeGeneratedFile(context,fileName,str); //2-	24- Waterway Point

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
}
