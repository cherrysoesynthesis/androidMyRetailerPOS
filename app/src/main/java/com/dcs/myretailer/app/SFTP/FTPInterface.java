package com.dcs.myretailer.app.SFTP;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.dcs.myretailer.app.Activity.AddNewProductActivity;
import com.dcs.myretailer.app.Query.Query;

import org.apache.commons.io.FileUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FTPInterface {
//    String server = "202.136.18.71";
//    String user = "postest2";
//    String password = "postest2@2022";
    String server = "";
    String user = "";
    String password = "";
    public FTPInterface(Context context, File filePath, String fileName){
        if (filePath != null && fileName != null) {
            new FTPTask(context, filePath, fileName).execute();
        }
//        new FTPTask(fileName).execute();
    }

    private class FTPTask extends AsyncTask<Void, Void, Void> {
        Context context;
        File filePath;
        String fileName;

        public FTPTask(Context c,File filep,String fileN) {
           // filePath = fPath;
            context = c;
            filePath = filep;
            fileName = fileN;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            user = Query.findfieldNameByTableName("user","FTPSync", true);
            server = Query.findfieldNameByTableName("ip","FTPSync", true);
            password = Query.findfieldNameByTableName("password","FTPSync", true);
            FTPClient ftpClient = new FTPClient();
            try {
                // ftpClient.connect(InetAddress.getByName(server));
                ftpClient.connect(server);
                ftpClient.login(user, password);
                Log.i("chk__","server_"+server);
                Log.i("chk__","user_"+user);
                Log.i("chk__","password_"+password);
//            ftpClient.changeWorkingDirectory(serverRoad);
//                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
//                ftpClient.setFileType(FTP.ASCII_FILE_TYPE);
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
//                ftpClient.changeWorkingDirectory("/20220520/");

//                ftpClient.(File("file_path"))
                //String file =  "file1.txt";
//                BufferedOutputStream buffIn = null;
                boolean result = false;
                BufferedInputStream buffIn = null;
//                String str = "/storage/emulated/0/mall/D674838.004.txt";
               // Log.i("chk__","one___"+str);
                try {
//                    buffIn = new BufferedInputStream(new FileInputStream(filePath.getName()));
                    File file = new File(filePath.toString());
                    //buffIn = new BufferedInputStream(new FileInputStream(file));
                    buffIn = new BufferedInputStream(new FileInputStream(file));
//                    buffIn = new BufferedInputStream(new FileInputStream(filePath));
//                buffIn = new BufferedInputStream(new FileInputStream(filePath));


                    ftpClient.enterLocalPassiveMode();
                    //ftpClient.storeFile(filePath.toString(), buffIn);
//                    ftpClient.storeFile(fileName, buffIn);
//                    ftpClient.storeFile(filePath.getName(), buffIn);
                    ftpClient.storeFile(file.toString(), buffIn);
//                boolean result = ftpClient.storeFile(filePath.toString(), buffIn);
                    // boolean result = ftpClient.storeFile(filePath.getName(), buffIn);
//                    result = ftpClient.storeFile(filePath.getName(), buffIn);
                    ftpClient.storeFile(file.toString(), buffIn);
//                ftpClient.storeFile(filePath.getName(), buffIn);
                    //ftpClient.storeFile(str.toString(), buffIn);

                    Log.i("chk__","onerrr___"+filePath.toString());
                   // Log.i("chk__","one___"+ ftpClient.storeFile(filePath.getName(), buffIn));
                    result = ftpClient.storeFile(filePath.getName(), buffIn);
                    Log.i("chk__","one_g__"+ ftpClient.getStatus(filePath.getName()));
                } catch (Exception e){
                    Log.i("chk__","ongetMessage__"+e.getMessage());

                }
//                FTPClient mFTP = new FTPClient();
//                try {
//                    // Connect to FTP Server
//                    mFTP.connect(server);
//                    //mFTP.login("user", "password");
//                    mFTP.setFileType(FTP.BINARY_FILE_TYPE);
//                    mFTP.enterLocalPassiveMode();
//
//                    // Prepare file to be uploaded to FTP Server
//                    File file = new File(filePath.toString());
//                    FileInputStream ifile = new FileInputStream(file);
//
//                    // Upload file to FTP Server
//                    mFTP.storeFile("filetotranfer",ifile);
//                    mFTP.disconnect();
//                } catch (SocketException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }

//                if ((ftpClient.storeFile(filePath.toString(),buffIn)) == "true") {
                if (result) {
                    Log.i("Saveddd","successfully");

                    //Toast.makeText(context, "File Upload Successfully.", Toast.LENGTH_SHORT).show();

                    //File source = new File("/storage/emulated/0/mall/"+filePath.getName());
                    File source = new File("/storage/emulated/0/"+filePath.getName());
                    File destination = new File("/storage/emulated/0/mall/backup/"+filePath.getName());

                    if (!destination.exists()) {
                        source.renameTo(destination);
                        if (source.renameTo(destination)){
                            try {
//
                                FileUtils.deleteDirectory(source);
                                Log.i("ifdsfdsf","DeleteSuccessfully");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                                Log.i("ifdsfdsf","BackupSuccessfully");
                        }else {
                            Log.i("elsedsfdsf","BackupSuccessfully");
                        }
                    }
                } else {
                   //Log.i("chk__","two___"+ ftpClient.storeFile(filePath.getName(), buffIn));
                    Log.i("Saveddd","failed");
//                    //Toast.makeText(context, "File Upload Failed.", Toast.LENGTH_SHORT).show();
//
//                    File source = new File("/storage/emulated/0/mall/"+filePath.getName());
//                    File destination = new File("/storage/emulated/0/mall/backup/"+filePath.getName());
//
//                    if (!destination.exists()) {
//                        source.renameTo(destination);
//                        if (source.renameTo(destination)){
//                            Log.i("ifdsfdsf","BackupSuccessfully");
//                        }else {
//                            Log.i("elsedsfdsf","BackupSuccessfully");
//                        }
//                    }
                }

//                Log.i("DSf___","storeFilegetStatus__"+ftpClient.getStatus("/"+fileName));
                try {

                    buffIn.close();
                    ftpClient.logout();
                    ftpClient.disconnect();
                } catch (Exception e){

                }
            } catch (IOException e) {
                e.printStackTrace();
                Log.i("error__","errrr"+e.getMessage());
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}
