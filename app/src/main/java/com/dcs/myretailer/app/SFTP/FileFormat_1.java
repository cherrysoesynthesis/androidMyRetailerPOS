package com.dcs.myretailer.app.SFTP;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;

public class FileFormat_1 {
    public FileFormat_1(Context context, String billno, String fromDate, String toDate, String status) {
        FTPSync ftpSync = Query.getFTPSync();
        String machineId = ftpSync.getMachineID(); //  7 numeric
        //String fileName = "D7800026.703.txt";//1-	CapitalLand
        String ftpuuid = Query.findfieldNameByTableName("uuid","FTPSync", true);
//        String str1 = "D78000262022032500000000.00";//1-	CapitalLand
        Log.i("SDfsd___","dftpuuid__"+ftpuuid);
        String sales = "";
        if (fromDate != null && fromDate.length() > 0){
            sales = "SELECT strftime('%Y%m%d', DateTime / 1000 + (3600*8), 'unixepoch'),ZReadNo,BillNo  " +
                    "FROM SALES where strftime('%Y%m%d', DateTime / 1000 + (3600*8), 'unixepoch')  " +
                    "BETWEEN '"+fromDate+"' AND '"+toDate+"'  GROUP BY  strftime('%Y%m%d', DateTime / 1000 + (3600*8), 'unixepoch') " +
                    " AND STATUS = 'SALES' ";
        } else {
            sales = "SELECT strftime('%Y%m%d', DateTime / 1000 + (3600*8), 'unixepoch'),ZReadNo,BillNo FROM SALES WHERE BillNo = '" + billno + "'";
        }
        Log.i("SDfsd___","dsfdsFormatone__"+sales);
        String salesDateYMD = "";
        String zReadNo = "";
        String billNo = "";
        Cursor c = DBFunc.Query(sales,false);
        if (c != null) {
            //if (c.moveToNext()){
            while (c.moveToNext()) {
                salesDateYMD = c.getString(0);
                zReadNo = c.getString(1);
                billNo = c.getString(2);

                FTPFileFormat ftpFileFormat = Query.getFTPFileFormat(billNo);

                String countStr = "000";
                Integer fileGeneratedNum = 0;

                Log.i("sdfsdf___","Sdf_bfre__"+ftpFileFormat.getFileGenerateCount());
                if (ftpFileFormat != null && ftpFileFormat.getUuid() != null){
                    try {
                        fileGeneratedNum = Integer.parseInt(ftpFileFormat.getFileGenerateCount()) + 1 ;
                    } catch (Exception e){
                        fileGeneratedNum = 0;
                    }
                }
                Log.i("sdfsdf___","Sdf___"+fileGeneratedNum);
                //10 //001
                if (fileGeneratedNum < 10) {
                    countStr = "00"+fileGeneratedNum; // 001 -> 009
                } else if (fileGeneratedNum < 100) { // 10 - 99
                    countStr = "0"+fileGeneratedNum; //010 -> 099
                }else if (fileGeneratedNum < 1000) { // 100 - 990
                    countStr = fileGeneratedNum.toString();
                }



                String fileName = "D"+machineId+"."+countStr+".txt";//1-	CapitalLand

                String salesSQL = "select SUM(TotalNettSales),SUM(IncTax),SUM(ExcluTax) " +
                        "from sales where strftime('%Y%m%d', DateTime / 1000 + (3600*8), 'unixepoch') = '"+salesDateYMD+"' " +
                        " AND STATUS = 'SALES' ";
                Log.i("Sdf___","dfd_"+salesSQL);
                Cursor cSalesSQL = DBFunc.Query(salesSQL,false);

                String salesAmt = "0.00";
                Double saleamt = 0.0;
                Double saleamtveforeSST = 0.0;
                if (cSalesSQL != null) {
                    if (cSalesSQL.getCount() != 0) {
                        if (cSalesSQL.moveToNext()) {
                            saleamt = cSalesSQL.getDouble(0);
                            double taxR = FileFormat_24.getTaxMinusValue(saleamt);

                            try {
                                saleamtveforeSST = Double.valueOf(String.format("%.2f", saleamt - taxR));
                            } catch (Exception e) {
                                saleamtveforeSST = 0.00; //12 (999999999.99)
                            }

                            salesAmt = String.format("%.2f", saleamtveforeSST);//3.00
                            int lengchk = 11 - salesAmt.length(); //11 - 4 = 7
                            String lengchkStr = "";
                            for (int i = 0 ; i < lengchk ; i ++) {
                                lengchkStr += "0";
                            }

                            lengchkStr += salesAmt;
                            Log.i("DSfds___","lengchkStr____"+lengchkStr);
                            //String str1 = "D78000262022032500000000.00";//1-	CapitalLand
                            //String str1 = "D"+machineId+salesDateYMD+"00000000.00";//1-	CapitalLand
                            String str1 = "D"+machineId+salesDateYMD+lengchkStr;//1-	CapitalLand



                            //getFTPFileFormat
                            FPTFileCreateActivity.writeGeneratedFile(context,fileName,str1,status);

                            //if (ftpFileFormat != null && ftpFileFormat.getUuid() == null){
                            if (ftpFileFormat != null){

                                FTPFileFormat createFtpFileFormat = new FTPFileFormat();
                                createFtpFileFormat.setFileFormatTypeNo(Constraints.FILEFORMAT_1);
                                createFtpFileFormat.setFileGenerateCount(fileGeneratedNum.toString());
                                createFtpFileFormat.setFileGenerateCountString(countStr);
                                createFtpFileFormat.setFileName(fileName);
                                createFtpFileFormat.setFTPSyncUUID(ftpuuid);
                                createFtpFileFormat.setZReadNo(zReadNo);
                                createFtpFileFormat.setBillNo(billNo);

                                Query.saveFTPFileFormat(context,createFtpFileFormat);
                            }
                        }
                    }
                }

            }
        }

    }
}
