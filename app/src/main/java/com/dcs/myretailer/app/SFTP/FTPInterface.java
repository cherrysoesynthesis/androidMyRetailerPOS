package com.dcs.myretailer.app.SFTP;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.dcs.myretailer.app.Activity.RemarkMainActivity;
import com.dcs.myretailer.app.Cashier.MainActivity;
import com.dcs.myretailer.app.Query.Query;

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
import java.text.SimpleDateFormat;
import java.util.Date;

public class FTPInterface {
//    String server = "202.136.18.71";
//    String user = "postest2";
//    String password = "postest2@2022";
    String server = "";
    String user = "";
    String password = "";
    final Handler handler = new Handler();
    public FTPInterface(Context context, File filePath, String fileName,String status){
        Log.i("TESTING__________d","FTPInterface"+filePath
                +"\n_fileName_"+fileName
                +"\n_status_"+status);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                Log.i("SFTP_","sftp_contextdelay_");
                if (filePath != null && fileName != null) {
                    new FTPTask(context, filePath, fileName,status).execute();
                }

            }
        }, 2000);
//        new FTPTask(fileName).execute();
    }

    private class FTPTask extends AsyncTask<Boolean, Void, Boolean> {
        Context context;
        File filePath;
        String fileName;
        String status;
        boolean result = false;
        public FTPTask(Context c,File filep,String fileN,String s) {
           // filePath = fPath;
            context = c;
            filePath = filep;
            fileName = fileN;
            status = s;
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        protected Boolean doInBackground(Boolean... voids) {
            user = Query.findfieldNameByTableName("user","FTPSync", true);
            server = Query.findfieldNameByTableName("ip","FTPSync", true);
            password = Query.findfieldNameByTableName("password","FTPSync", true);

            Log.i("TESTING__________d","doInBackground_"+user
                    +"\n_host_"+server
                    +"\n_password_"+password);

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
                BufferedInputStream buffIn = null;
                Log.i("Sdfsf____","dfdf_status-"+status);
                if (status.equals("send")) {
                    File send = new File("/storage/emulated/0/mall/backup/"+filePath.getName());
                    buffIn = new BufferedInputStream(new FileInputStream(send));
                    result = ftpClient.storeFile(filePath.getName(), buffIn);
                } else {
//                ftpClient.changeWorkingDirectory("/20220520/");

//                ftpClient.(File("file_path"))
                    //String file =  "file1.txt";
//                BufferedOutputStream buffIn = null;
//                boolean result = false;

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

                        Log.i("chk__", "onerrr___" + filePath.toString());
                        // Log.i("chk__","one___"+ ftpClient.storeFile(filePath.getName(), buffIn));
                        result = ftpClient.storeFile(filePath.getName(), buffIn);
                        Log.i("chk__", "one_g__" + ftpClient.getStatus(filePath.getName()));
                    } catch (Exception e) {
                        Log.i("chk__", "ongetMessage__" + e.getMessage());

                    }
                }

                Log.i("Sdfsf____","dfdf_status-"+result);
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
                if (result && !(status.equals("send"))) {
                    Log.i("Saveddd","successfully");

                    //Toast.makeText(context, "File Upload Successfully.", Toast.LENGTH_SHORT).show();

                    //File source = new File("/storage/emulated/0/mall/"+filePath.getName());
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
//                    if (!destination.exists()) {
//                        source.renameTo(destination);
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
//                    }
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

                File autoDeletePath = new File("/storage/emulated/0/mall/backup/"+filePath.getName());
                Log.i("DfautoDeletePath_","autoDeletePath___"+autoDeletePath.toString());

                Log.i("DfautoDeletePath_","lastModified___"+autoDeletePath.lastModified());
//                Date date = dateFormat.parse(longDate.toString());
//                System.out.println("Date : "+date);
//                SimpleDateFormat dateFormatNew = new SimpleDateFormat("dd/MM/yyyy");
//                String formattedDate = dateFormatNew.format(date);
//                System.out.println("Formatted date : "+formattedDate);...

                //long to date object


                Query.AutoDeleteMallBackupFile();




//                //date to long
//                Date d1=new Date();
//                Long l1=d1.getTime();
//                System.out.println(l1);

//                Path file = Paths.get(filePath.getName());
//                Log.i("DfautoDeletePath_","Pathfile___"+file.toString());
//                BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);

                //Log.i("creationTime_" , "attr_____"+attr.creationTime());
                //Log.i("lastModifiedTime_" , "attr_____"+attr.lastModifiedTime());


            } catch (IOException e) {
                e.printStackTrace();
                Log.i("error__","errrr"+e.getMessage());
            }
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
}
