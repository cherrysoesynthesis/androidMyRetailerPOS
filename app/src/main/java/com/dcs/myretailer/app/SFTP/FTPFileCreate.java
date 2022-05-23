package com.dcs.myretailer.app.SFTP;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.dcs.myretailer.app.Cashier.DeclarationConf;
import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FTPFileCreate {
    public FTPFileCreate(Context context,String billno,String fromDate,String toDate) {
        String fileFormat = Query.findfieldNameByTableName("fileFormat","FTPSync", true);
        Log.i("Sdfdsf___","SfileFormat___"+fileFormat);
        if (fileFormat != null) {
            if (fileFormat.equals(Constraints.FILEFORMAT_1)) {

                new FileFormat_1(context, billno, fromDate,toDate);

            } else if (fileFormat.equals(Constraints.FILEFORMAT_24)) {

                new FileFormat_24(context, billno, fromDate,toDate);

            }
        }
    }
}
