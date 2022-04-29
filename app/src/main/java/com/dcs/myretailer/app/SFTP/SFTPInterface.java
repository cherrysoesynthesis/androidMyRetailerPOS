package com.dcs.myretailer.app.SFTP;

import android.os.AsyncTask;
import android.util.Log;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class SFTPInterface {
    String host = "202.136.18.71";
    String user = "postest";
    String password = "postest@2022";
    public SFTPInterface(File filePath, String fileName) {
        new SFTPTask(filePath).execute();
    }
    private class SFTPTask extends AsyncTask<Void, Void, Void> {
        String result;
        File fileName;

        public SFTPTask(File fn) {
            fileName = fn;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            sendFile(fileName);
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

    private void sendFile(File fileName) {
        try {
            JSch ssh = new JSch();
            //Session session = ssh.getSession("username", "myip90000.ordomain.com", 22);
            Session session = ssh.getSession(user, host, 22);
            // Remember that this is just for testing and we need a quick access, you can add an identity and known_hosts file to prevent
            // Man In the Middle attacks
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.setPassword(password);

            session.connect();
            Channel channel = session.openChannel("sftp");
            channel.connect();

            ChannelSftp sftp = (ChannelSftp) channel;

            //sftp.cd(directory);
            // If you need to display the progress of the upload, read how to do it in the end of the article

            // use the put method , if you are using android remember to remove "file://" and use only the relative path
            //sftp.put("/storage/0/myfile.txt", "/var/www/remote/myfile.txt");
            //sftp.put(fileName.getName(), "/var/www/remote/"+fileName.getName());

            Log.i("fileName.getName___",fileName+"DSfs___"+fileName.getName());
            Log.i("fileName.getName___","String_"+String.valueOf(fileName));
            sftp.put(String.valueOf(fileName), fileName.getName());

            Boolean success = true;

            if(success){
                // The file has been uploaded succesfully
            }

            channel.disconnect();
            session.disconnect();
        } catch (JSchException e) {

            Log.i("JSchExceptionrr___",e.getMessage().toString());
            e.printStackTrace();
        } catch (SftpException e) {
            Log.i("SftpException__",e.getMessage().toString());
            e.printStackTrace();
        } catch (Exception e){
            Log.i("EExceptiontion__",e.getMessage().toString());
            e.printStackTrace();
        }
    }
//
//    private ChannelSftp setupJsch() throws JSchException {
//        String username = "";
//        String password = "";
//        String host = "";
//        JSch jsch = new JSch();
//        Session jschSession = jsch.getSession(username, host);
//        java.util.Properties config = new java.util.Properties();
//        config.put("StrictHostKeyChecking", "no");
//        jschSession.setConfig(config);
//        jschSession.setPassword(password);
//        jschSession.connect();
//        return (ChannelSftp) jschSession.openChannel("sftp");
//    }
}
