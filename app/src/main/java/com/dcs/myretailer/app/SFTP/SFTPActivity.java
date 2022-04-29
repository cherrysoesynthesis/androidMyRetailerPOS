package com.dcs.myretailer.app.SFTP;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.dcs.myretailer.app.R;
import com.google.android.material.snackbar.Snackbar;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;

public class SFTPActivity extends AppCompatActivity {
//    String user = "xxx";
//    String password = "xxx";
//    String host = "192.168.xxx.xxx";
//    String user = "xxx";
//    String password = "xxx";
//    String host = "192.168.xxx.xxx";
    //String directory = "";
    String host = "202.136.18.71";
    String user = "postest";
    String password = "postest@2022";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sftpactivity);

        sendFile();

//        executeSSHcommand();
    }

    private ChannelSftp setupJsch() throws JSchException {
        String username = "";
        String password = "";
        String host = "";
        JSch jsch = new JSch();
        Session jschSession = jsch.getSession(username, host);
        java.util.Properties config = new java.util.Properties();
        config.put("StrictHostKeyChecking", "no");
        jschSession.setConfig(config);
        jschSession.setPassword(password);
        jschSession.connect();
        return (ChannelSftp) jschSession.openChannel("sftp");
    }

    // pass the local path of the file as String and the name of the file which you want at Sftp Server.
    public boolean uploadSftpFromPath(String localFile, String sftpFile) {
        ChannelSftp channelSftp = null;
        try {
            channelSftp = setupJsch();
        } catch (JSchException e) {
            // throw the exception
        }
        try {
            channelSftp.connect();
        } catch (JSchException e) {
            // throw the exception
        }
        try{
            channelSftp.put(localFile, sftpFile);
            System.out.println("Upload Complete");
        } catch (SftpException e) {
            // throw the exception
        }
        channelSftp.exit();
        return true;
    }
    //pass the InputStream and the name of the file which you want at Server.
    public boolean uploadSftpFromInputStream(InputStream localFile, String sftpFile) {
        ChannelSftp channelSftp = null;
        try {
            channelSftp = setupJsch();
        } catch (JSchException e) {
            // throw the exception
        }
        try {
            channelSftp.connect();
        } catch (JSchException e) {
            // throw the exception
        }
        try{
            channelSftp.put(localFile, sftpFile);
            System.out.println("Upload Complete");
        } catch (SftpException e) {
            // throw the exception
        }
        channelSftp.exit();
        return true;
    }
//pass the String Object and the name of the file which you want at Sftp Server.
    public boolean uploadSftpFromStringObject(String localFile, String sftpFile) {
        return uploadSftpFromInputStream(new ByteArrayInputStream(localFile.getBytes()), sftpFile);
    }
//https://medium.com/@princebatra2315/upload-and-download-a-file-through-sftp-in-java-8bde3b8e4bdb
//    public boolean downloadSftp( String localFilePath, String remoteFile) {
//        ChannelSftp channelSftp = null;
//        try {
//            channelSftp = setupJsch();
//        } catch (JSchException e) {
//            // throw the exception
//        }
//        try {
//            channelSftp.connect();
//        } catch (JSchException e) {
//            // throw the exception
//        }
//        try{
//            channelSftp.get(localFile, remoteFile);
//            System.out.println("Download Complete");
//        } catch (SftpException e) {
//            // throw the exception
//        }
//        channelSftp.exit();
//        return true;
//    }

    private void sendFile() {
        try {
            JSch ssh = new JSch();
            //Session session = ssh.getSession("username", "myip90000.ordomain.com", 22);
            Session session = ssh.getSession(user, host, 22);
            // Remember that this is just for testing and we need a quick access, you can add an identity and known_hosts file to prevent
            // Man In the Middle attacks
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.setPassword("Passw0rd");

            session.connect();
            Channel channel = session.openChannel("sftp");
            channel.connect();

            ChannelSftp sftp = (ChannelSftp) channel;

            //sftp.cd(directory);
            // If you need to display the progress of the upload, read how to do it in the end of the article

            // use the put method , if you are using android remember to remove "file://" and use only the relative path
            sftp.put("/storage/0/myfile.txt", "/var/www/remote/myfile.txt");

            Boolean success = true;

            if(success){
                // The file has been uploaded succesfully
            }

            channel.disconnect();
            session.disconnect();
        } catch (JSchException e) {
            System.out.println(e.getMessage().toString());
            e.printStackTrace();
        } catch (SftpException e) {
            System.out.println(e.getMessage().toString());
            e.printStackTrace();
        }
    }

    public void executeSSHcommand(){

        int port=22;
        try{

            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setTimeout(10000);
            session.connect();
            ChannelExec channel = (ChannelExec)session.openChannel("exec");
            channel.setCommand("your ssh command here");
            channel.connect();
            channel.disconnect();
            // show success in UI with a snackbar alternatively use a toast
            Snackbar.make(this.findViewById(android.R.id.content),
                    "Success!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        catch(JSchException e){
            // show the error in the UI
            Snackbar.make(this.findViewById(android.R.id.content),
                    "Check WIFI or Server! Error : "+e.getMessage(),
                    Snackbar.LENGTH_LONG)
                    .setDuration(20000).setAction("Action", null).show();
        }
    }
}