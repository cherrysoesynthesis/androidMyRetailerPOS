package com.dcs.myretailer.app.SFTP;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.dcs.myretailer.app.Activity.RemarkMainActivity;
import com.dcs.myretailer.app.Query.Query;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

public class SFTPInterface {
//    String host = "202.136.18.71";
//    String user = "postest";
//    String password = "postest@2022";
//    String port = "22";
    String host = "";
    String user = "";
    String password = "";
    Integer port = 0;
    final Handler handler = new Handler();
    public SFTPInterface(Context context, File filePath, String fileName, String status) {
        Log.i("TESTING__________d","SFTPInterface"+filePath
                +"\n_fileName_"+fileName
                +"\n_status_"+status);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                Log.i("SFTP_","sftp_contextdelay_");
                new SFTPTask(context,filePath,fileName,status).execute();

            }
        }, 1000);
    }
    private class SFTPTask extends AsyncTask<Boolean, Void, Boolean> {
        String result;
        Context context;
        File filePath;
        String fileName;
        String status;

        public SFTPTask(Context c,File fp,String fn,String s) {
            context = c;
            filePath = fp;
            fileName = fn;
            status = s;
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        protected Boolean doInBackground(Boolean... voids) {

            Boolean result = sendFile(context,filePath,status);
            return result;
        }
        @Override
        protected void onPostExecute(Boolean aVoid) {
            if (aVoid){
                Log.i("savedSuccessfully","savedsuccess");
                Toast.makeText(RemarkMainActivity.context, "File Uploaded Successfully.", Toast.LENGTH_SHORT).show();
            } else {
                Log.i("savedSuccessfully","saved");
                Toast.makeText(RemarkMainActivity.context, "File Upload Fail.", Toast.LENGTH_SHORT).show();
            }
            super.onPostExecute(aVoid);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private Boolean sendFile(Context context, File filePath, String status) {
        Boolean success = false;
        user = Query.findfieldNameByTableName("user","FTPSync", true);
        host = Query.findfieldNameByTableName("ip","FTPSync", true);
        password = Query.findfieldNameByTableName("password","FTPSync", true);
        try {
            port = Integer.parseInt(Query.findfieldNameByTableName("port", "FTPSync", true));
        } catch (Exception e){
            port = 0;
        }
        Log.i("TESTING__________d","doInBackground_"+user
                +"\n_host_"+host
                +"\n_password_"+password
                +"\n_port_"+port);
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

            session.connect();
            Channel channel = session.openChannel("sftp");
            channel.connect();

            ChannelSftp sftp = (ChannelSftp) channel;

            //sftp.cd(directory);
            // If you need to display the progress of the upload, read how to do it in the end of the article

            // use the put method , if you are using android remember to remove "file://" and use only the relative path
            //sftp.put("/storage/0/myfile.txt", "/var/www/remote/myfile.txt");
            //sftp.put(fileName.getName(), "/var/www/remote/"+fileName.getName());

            Log.i("TESTING__________d","SFTPInterface__"+filePath.toString()
                    +"\n_fileName_"+filePath.getName()
                    +"\n_type_"+status);

            if (status.equals("send")) {
                File sendFileDestination = new File("/storage/emulated/0/mall/backup/"+filePath.getName());
                //sftp.put(String.valueOf(filePath), filePath.getName());
                Log.i("sendFileDestination___","here___"+sendFileDestination.toString());
                sftp.put(String.valueOf(sendFileDestination), filePath.getName());
                success = true;
            } else {
                sftp.put(String.valueOf(filePath), filePath.getName());
                success = true;
            }



            if(success && !(status.equals("send"))){
                // The file has been uploaded succesfully
               // Toast.makeText(context, "File Upload Successfully.", Toast.LENGTH_SHORT).show();

               // File source = new File("/storage/emulated/0/mall/"+fileName.getName());
                File source = new File("/storage/emulated/0/"+filePath.getName());
                File destination = new File("/storage/emulated/0/mall/backup/"+filePath.getName());

                if (destination.exists()) {
                    try {
//                            FileUtils.deleteDirectory(destination);
//                            FileUtils.forceDeleteOnExit(destination);
                        FileUtils.forceDelete(destination);
                        Log.i("DSfExcep_","forceDelete");
                    } catch (Exception e){
                        Log.i("DSfExcep_","error"+e.getMessage());
                    }
                }

////                if (!destination.exists()) {
//                   // source.renameTo(destination);
//                    if (source.renameTo(destination)){
//                        Log.i("ifdsfdsf","BackupSuccessfully");
//                    }else {
//                        Log.i("elsedsfdsf","BackupSuccessfully");
//                    }
                    if (source.renameTo(destination)){
                        try {
                            FileUtils.deleteDirectory(source);
                            Log.i("ifdsfdsf","DeleteSuccessfully");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Log.i("ifdsfdsf","BackupSuccessfully");
                    }else {
                        Log.i("elsedsfdsf","BackupSuccessfully");
                    }
//                }
            }

            Query.AutoDeleteMallBackupFile();
//            File autoDeletePath = new File("/storage/emulated/0/mall/backup/"+filePath.getName());
//
//            Path file = Paths.get(autoDeletePath.getName());
//
//            BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);
//
//            Log.i("creationTime_" , "attr_____"+attr.creationTime());
//            Log.i("lastModifiedTime_" , "attr_____"+attr.lastModifiedTime());

//            try {
//
//                Path file = Paths.get(fileName);
//                BasicFileAttributes attr =
//                        Files.readAttributes(file, BasicFileAttributes.class);
//
//                System.out.println("creationTime: " + attr.creationTime());
//                System.out.println("lastAccessTime: " + attr.lastAccessTime());
//                System.out.println("lastModifiedTime: " + attr.lastModifiedTime());
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

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
        return success;
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
