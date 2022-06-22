package com.dcs.myretailer.app.SFTP;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.dcs.myretailer.app.Activity.RemarkMainActivity;
import com.dcs.myretailer.app.Query.Query;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.io.File;

public class SFTPInterfaceTestConnection {
    String host = "";
    String user = "";
    String password = "";
    Integer port = 0;
    final Handler handler = new Handler();
    public SFTPInterfaceTestConnection(Context context) {
        Log.i("SFTP_","sftp_context");
        Toast.makeText(RemarkMainActivity.context, "Connecting...", Toast.LENGTH_SHORT).show();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                Log.i("SFTP_","sftp_contextdelay_");
                new SFTPTask(context).execute();
            }
        }, 1000);
    }
    private class SFTPTask extends AsyncTask<Void, Void, Boolean> {
        //String result;
        Context context;

        boolean result = false;
        public SFTPTask(Context c) {
            context = c;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {

            user = Query.findfieldNameByTableName("user","FTPSync", true);
            host = Query.findfieldNameByTableName("ip","FTPSync", true);
            password = Query.findfieldNameByTableName("password","FTPSync", true);
            try {
                port = Integer.parseInt(Query.findfieldNameByTableName("port", "FTPSync", true));
            } catch (Exception e){
                port = 0;
            }
            Log.i("SFTP_","sftp_user_"+user);
            Log.i("SFTP_","sftp_host_"+host);
            Log.i("SFTP_","sftp_password_"+password);
            Log.i("SFTP_","sftp_port_"+port);
            try {
                JSch ssh = new JSch();
                //Session session = ssh.getSession("username", "myip90000.ordomain.com", 22);
                Session session = ssh.getSession(user, host, port);
                // Remember that this is just for testing and we need a quick access, you can add an identity and known_hosts file to prevent
                // Man In the Middle attacks
                java.util.Properties config = new java.util.Properties();
                config.put("StrictHostKeyChecking", "no");
                session.setConfig(config);
                session.setPassword(password);

                try {
                    session.connect();
                } catch (JSchException e) {
                    e.printStackTrace();
                }
                Channel channel = session.openChannel("sftp");
                channel.connect();

                ChannelSftp sftp = (ChannelSftp) channel;


                if (sftp.isConnected()){
                    result = true;
                } else {
                    result = false;
                }


                Log.i("SFTP_","sftp_result_"+result);
                channel.disconnect();
                session.disconnect();
                } catch (JSchException e) {
                    result = false;
                    Log.i("JSchExceptionrr___",e.getMessage().toString());
                    e.printStackTrace();
                } catch (Exception e){
                    result = false;
                    Log.i("EExceptiontion__",e.getMessage().toString());
                    e.printStackTrace();
                }
            return result;
        }
        @Override
        protected void onPostExecute(Boolean aVoid) {

            Log.i("SFTP_","sftp_raVoid_"+aVoid);
            if (aVoid){
                Log.i("savedSuccessfully","savedsuccess");
                Toast.makeText(RemarkMainActivity.context, "Connection Success!.", Toast.LENGTH_SHORT).show();
            } else {
                Log.i("savedSuccessfully","saved");
                //Toast.makeText(RemarkMainActivity.context, "Connection Failed!" + err, Toast.LENGTH_SHORT).show();
                Toast.makeText(RemarkMainActivity.context, "Connection Fail!", Toast.LENGTH_SHORT).show();
            }
            super.onPostExecute(aVoid);
        }

//        @Override
//        protected void onPreExecute() {
//            Toast.makeText(RemarkMainActivity.context, "Connecting...", Toast.LENGTH_SHORT).show();
//            super.onPreExecute();
//        }
    }
}
