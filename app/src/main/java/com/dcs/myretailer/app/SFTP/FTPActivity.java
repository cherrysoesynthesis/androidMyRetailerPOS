
package com.dcs.myretailer.app.SFTP;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.dcs.myretailer.app.R;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;

public class FTPActivity extends AppCompatActivity {
    String server = "202.136.18.71";
    //String serverRoad = "";
    String user = "postest";
    String password = "postest@2022";
    //connect server and sent txt file
    //generate txt file
    //generate format (2 types)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ftpactivity);



        FTPClient client = new FTPClient();

//        TextView ausgabe = (TextView) findViewById(R.id.ausgabe);


//        try {
////            client.connect("ftp-web.ohost.de");
//            client.connect(InetAddress.getByName(server));
//
//            client.login(user, password);
//            String filename = "file1.txt";
//            FileInputStream fis = null;
//            fis = new FileInputStream(filename);
//            client.storeFile(filename, fis);
//            client.logout();
//            fis.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(InetAddress.getByName(server));
            ftpClient.login(user, password);
//            ftpClient.changeWorkingDirectory(serverRoad);
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            String file =  "file1.txt";
            BufferedInputStream buffIn = null;
            buffIn = new BufferedInputStream(new FileInputStream(file));
            ftpClient.enterLocalPassiveMode();
//            ftpClient.storeFile("test.txt", buffIn);
            ftpClient.storeFile(file, buffIn);
            buffIn.close();
            ftpClient.logout();
            ftpClient.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}