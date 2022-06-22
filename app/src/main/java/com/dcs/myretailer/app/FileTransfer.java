package com.dcs.myretailer.app;

import android.util.Log;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPSClient;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.cert.X509Certificate;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class FileTransfer {

    public FTPClient mFTPClient = null;
    private static final String TAG = null;

    public void ftpConnect(String host,String username,String password,int port) {
        mFTPClient = new FTPClient();
//        FTPSClient ftpClient = new FTPSClient("TLS", false);
//        try {
//            TrustManager[] trustManager = new TrustManager[] { new X509TrustManager() {
//                @Override
//                public X509Certificate[] getAcceptedIssuers() {
//                    return null;
//                }
//
//                @Override
//                public void checkClientTrusted(X509Certificate[] certs, String authType) {
//                }
//
//                @Override
//                public void checkServerTrusted(X509Certificate[] certs, String authType) {
//                }
//            } };
//
//            ftpClient.setTrustManager(trustManager[0]);
//            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
//            kmf.init(null, null);
//            KeyManager km = kmf.getKeyManagers()[0];
//            ftpClient.setKeyManager(km);
//            ftpClient.setBufferSize(1024 * 1024);
//            ftpClient.setConnectTimeout(100000);
//            ftpClient.connect(InetAddress.getByName("49.128.60.174"), 21);
//            ftpClient.setSoTimeout(100000);
//
//            if (ftpClient.login("user", "password")) {
//                ftpClient.execPBSZ(0);
//                ftpClient.execPROT("P");
//                ftpClient.changeWorkingDirectory("/");
//                // 250 = directory succesfully changed
////                if (ftpClient.getReplyString().contains("250")) {
////                    ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
////                    ftpClient.enterLocalPassiveMode();
////                    BufferedInputStream buffIn = null;
////
//////                    for (File picture : pictures) {
////                        buffIn = new BufferedInputStream(new FileInputStream(picture.getAbsolutePath()));
////                        boolean result = ftpClient.storeFile(picture.getName(), buffIn);
////                        try {
////                            buffIn.close();
////                        } catch (Exception e) {
////                        }
////                        if (result)
////                            picture.delete();
//////                    }
////                }
//            }
//
//        } catch (SocketException e) {
//            Log.e("APPTAG", e.getStackTrace().toString());
//        } catch (UnknownHostException e) {
//            Log.e("APPTAG", e.getStackTrace().toString());
//        } catch (IOException e) {
//            Log.e("APPTAG", e.getStackTrace().toString());
//        } catch (Exception e) {
//            Log.e("APPTAG", e.getStackTrace().toString());
//        } finally {
//            try {
//                ftpClient.logout();
//            } catch (Exception e2) {
//            }
//            try {
//                ftpClient.disconnect();
//            } catch (Exception e2) {
//            }
//        }

        try {
            // connecting to the host
            Log.i("host_","host__"+host);
            Log.i("port_","port__"+port);
            mFTPClient.connect(host, port);

            Log.i("getReplyCode_","getReplyCode__"+mFTPClient.getReplyCode());
            // now check the reply code, if positive mean connection success
            if (FTPReply.isPositiveCompletion(mFTPClient.getReplyCode())) {
                // login using username & password
                boolean status = mFTPClient.login(username, password);

                Log.i("status_","status__"+status);

                /* Set File Transfer Mode
                 *
                 * To avoid corruption issue you must specified a correct
                 * transfer mode, such as ASCII_FILE_TYPE, BINARY_FILE_TYPE,
                 * EBCDIC_FILE_TYPE .etc. Here, I use BINARY_FILE_TYPE
                 * for transferring text, image, and compressed files.
                 */
                mFTPClient.setFileType(FTP.BINARY_FILE_TYPE);
                mFTPClient.enterLocalPassiveMode();

                //return status;
            }
        } catch(Exception e) {
            Log.i(TAG, "Error: could not connect to host " + host );
            Log.i(TAG, "Error: could not connect to host " + e.getMessage() );
        }

        //return false;
    }
    /*fileupload*/
//    public boolean ftpUpload(String srcFilePath, String desFileName,
//                             String desDirectory)
    public void ftpUpload(String srcFilePath, String desFileName,
                             String desDirectory) {
        FTPClient mFTPClient = new FTPClient();
        Log.i("srcFilePath_","srcFilePath_"+srcFilePath);
        Log.i("desDirectory_","desDirectory_"+desDirectory);
        boolean status = false;
        try {
            //File f=new File("D:/img/abc.jpeg");
            FileInputStream srcFileStream = new FileInputStream(srcFilePath);

// change working directory to the destination directory
            Log.i("desFileName__","desFileName__"+desFileName);
            Log.i("srcFileStream__","srcFileStream__"+srcFileStream);

            //if (ftpChangeDirectory("ftp://49.128.60.174/"+desDirectory)) {
            //if (ftpChangeDirectory("ftp://1111111@49.128.60.174/sumati/")) {
                status = mFTPClient.storeFile(desFileName, srcFileStream);
                //status = mFTPClient.storeFile("/storage/emulated/0/Pictures/Screenshots/Screenshot_20200921-121136.png", srcFileStream);
           // }

            srcFileStream.close();
            //return status;
        } catch (Exception e) {
            Log.i("uploadfailed_", "upload failed"+e.getMessage());
        }
        Log.i("status__","status__"+status);
        //return status;
    }
    public boolean ftpChangeDirectory(String directory_path)
    {
        FTPClient mFTPClient = new FTPClient();
        try {
            mFTPClient.changeWorkingDirectory(directory_path);
        } catch(Exception e) {
            //Log.d(TAG, "Error: could not change directory to " + directory_path);
        }

        return false;
    }

}
