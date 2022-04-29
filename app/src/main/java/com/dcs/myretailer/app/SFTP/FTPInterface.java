package com.dcs.myretailer.app.SFTP;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FTPInterface {
    String server = "202.136.18.71";
    String user = "postest2";
    String password = "postest2@2022";
    public FTPInterface(File filePath, String fileName){

        //new FTPTask(fileName).execute();
        new FTPTask(filePath).execute();
    }

    private class FTPTask extends AsyncTask<Void, Void, Void> {
        String result;
        File fileName;

        public FTPTask(File fn) {
            fileName = fn;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            FTPClient ftpClient = new FTPClient();
            try {
                // ftpClient.connect(InetAddress.getByName(server));
                ftpClient.connect(server);
                ftpClient.login(user, password);
//            ftpClient.changeWorkingDirectory(serverRoad);
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                //String file =  "file1.txt";
                BufferedInputStream buffIn = null;
                Log.i("DSf___","dfdf___"+fileName);
                buffIn = new BufferedInputStream(new FileInputStream(fileName));
                ftpClient.enterLocalPassiveMode();
//            ftpClient.storeFile("test.txt", buffIn);
                ftpClient.storeFile(fileName.getName(), buffIn);
                buffIn.close();
                ftpClient.logout();
                ftpClient.disconnect();
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
