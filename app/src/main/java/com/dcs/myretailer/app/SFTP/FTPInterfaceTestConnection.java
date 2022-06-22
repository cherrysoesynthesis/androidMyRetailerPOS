package com.dcs.myretailer.app.SFTP;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.dcs.myretailer.app.Activity.RemarkMainActivity;
import com.dcs.myretailer.app.Query.Query;

import org.apache.commons.io.FileUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FTPInterfaceTestConnection {
    String server = "";
    String user = "";
    String password = "";
    //String portno = "21";
    final Handler handler = new Handler();
    public FTPInterfaceTestConnection(Context context){
        Toast.makeText(RemarkMainActivity.context, "Connecting...", Toast.LENGTH_SHORT).show();
        user = Query.findfieldNameByTableName("user","FTPSync", true);
        server = Query.findfieldNameByTableName("ip","FTPSync", true);
        password = Query.findfieldNameByTableName("password","FTPSync", true);
        //portno = "21";
        //if (server != null && server != null) {
        Log.i("chk__","ongetMessage__"+user);
        Log.i("chk__","ongetMessage__"+server);
        Log.i("chk__","ongetMessage__"+password);


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                new FTPTask(context).execute();
            }
        }, 2000);

       // }
//        new FTPTask(fileName).execute();
    }

    private class FTPTask extends AsyncTask<Boolean, Void, Boolean> {
        Context context;
        boolean result = false;
        public FTPTask(Context c) {
            // filePath = fPath;
            context = c;
        }

        @Override
        protected Boolean doInBackground(Boolean... voids) {

            Log.i("chk__","ondoInBackgrounde__"+password);
            FTPClient ftpClient = new FTPClient();
            try {
            // ftpClient.connect(InetAddress.getByName(server));
                ftpClient.connect(server);
                result = ftpClient.login(user, password);
                Log.i("chk__","ondoInBackgroresult__"+result);
                ftpClient.disconnect();
            } catch (Exception e){
                Log.i("chk__","ongetMessage__"+e.getMessage());
                result = false;
            }

            return result;
        }
        @Override
        protected void onPostExecute(Boolean aVoid) {
            Log.i("chk__","ononPostExecutee__"+aVoid);
            if (aVoid){
                Log.i("savedSuccessfully","savedsuccess");
                Toast.makeText(RemarkMainActivity.context, "Connection Success!.", Toast.LENGTH_SHORT).show();
            } else {
                Log.i("savedSuccessfully","saved");
                Toast.makeText(RemarkMainActivity.context, "Connection Fail.", Toast.LENGTH_SHORT).show();
            }
            super.onPostExecute(aVoid);
        }

       /* @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }*/
    }
}
