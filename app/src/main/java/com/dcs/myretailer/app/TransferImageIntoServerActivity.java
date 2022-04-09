package com.dcs.myretailer.app;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import androidx.loader.content.CursorLoader;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TransferImageIntoServerActivity extends AppCompatActivity {
    protected static final int SELECT_PICTURE = 0;
    public static String var;
    TextView textTargetUri;
    //public FTPClient mFTPClient = null;
    boolean status = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_image_into_server);
        Button buttonLoadImage = (Button)findViewById(R.id.loadimage);
        textTargetUri = (TextView)findViewById(R.id.targeturi);

        buttonLoadImage.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View arg0) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,
                        "Click Picture To Export"), SELECT_PICTURE);

            }});
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){

            Uri targetUri = data.getData();
            FTPUpload(targetUri);
            //FTPUploadImage();
////            String[] projection = {MediaStore.Images.Media.DATA };
////            Cursor cursor = managedQuery(targetUri, projection, null, null, null);
////           // Cursor cursor = getContentResolver().query(targetUri, projection, null, null, null);
//////            Cursor c_images= getApplicationContext().getContentResolver().query(
//////                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//////                    null, null, null,
//////                    "(" + MediaStore.Images.Media._ID + "*(-1))");
////
////            int column_index = cursor
////                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
////            cursor.moveToFirst();
////            //return cursor.getString(column_index);
////            textTargetUri.setText(cursor.getString(column_index));
////            String var=cursor.getString(column_index);
////            // textTargetUri.setText("connected"+var);
////            textTargetUri.setText(var);
////            String img=var.substring(12);
//            //String img= getRealPathFromURI(targetUri);
//            //String img= getRealPathApi11to18(getApplicationContext(),targetUri);
           //String img= getImagePath(getApplicationContext(),targetUri);
////            try {
////                Log.i("FDFDF__","img0_____"+img);
////                img = getRealPathFromUri(getApplicationContext(),targetUri);
////                Log.i("FDFDF__","img1_____"+img);
////            } catch (IOException e) {
////                e.printStackTrace();
////                Log.i("FDFDF__","img2_____"+img);
////            }
//            Log.i("FDFDF__","img_____"+img);
//            //String img= "content://com.android.providers.media.documents/document/image%3A6745";
//
//
//            try{
//                FileTransfer fr =new FileTransfer();
//                /*Toast.makeText(getApplicationContext(),
//                        "ftp connected",
//                        Toast.LENGTH_LONG).show();*/
////                String server = "ftp://49.128.60.174/";
////                int port = 21;
////                String user = "8000713";
////                String pass = "8000713";
//                //String server = "ftp://49.128.60.174/";
//                //String server = "49.128.60.174";
//                String server = "ftp://49.128.60.174/";
////                //int port = 21;
////                int port = 8080 ;
//                String user = "1111111";
//                String pass = "Myret@123";
////                //fr.ftpConnect(server,user,pass,8080);
//                fr.ftpConnect(server,user,pass,21);
//                fr.ftpUpload("/storage/emulated/0/Pictures/Screenshots/",
//                        "Screenshot_20200921-121136.png",
//                        "ftp://1111111@49.128.60.174/sumati/");
//
//                Toast.makeText(getApplicationContext(),
//                        img,
//                        Toast.LENGTH_LONG).show();
//
//                try{
//                    fr.ftpUpload(img,"test.jpg","sumati");
//
//                    Toast.makeText(getApplicationContext(),
//                            "image uploaded to the server",
//                            Toast.LENGTH_LONG).show();
//
//                }
//                catch(Exception e){
//                    Toast.makeText(getApplicationContext(),
//                            "exception for upload",
//                            Toast.LENGTH_LONG).show();
//                }
//            }
//            catch(Exception es){
//                Toast.makeText(getApplicationContext(),
//                        "server",
//                        Toast.LENGTH_LONG).show();
//            }
            // System.out.println("path:"+var);

/* boolean tempStatus = false;
  String desFileName = "" ;
  FileInputStream srcFileStream = null;*/


            //textTargetUri.setText("uploaded");
            //File f = new File(var);
            //textTargetUri.setText("uploaded"+var);
            //desFileName = "myUploadFile.JPEG";
/*  try
  {
  srcFileStream = new FileInputStream(f);
  //textTargetUri.setText("uploaded"+var);

  }
  catch (Exception e)
  {
  e.toString();
  e.printStackTrace();
  }

 // mFTPClient = new FTPClient();
 // mFTPClient.connect("xx.xx.xx.xx");

  SimpleFTPClient a = new SimpleFTPClient ();
  a.setHost("xx.xx.xx.xx");
  //textTargetUri.setText("connected"+var);
 // FTPClient client = new FTPClient();
  try{
 //* client.connect("xx.xx.xx.xx");
  //textTargetUri.setText("connected"+var);
  }
  catch(Exception e){}
  a.setUser("user");
  a.setPassword("pass");
    boolean connected=a.connect();

    if ( connected){

        System.out.println("connected");
      // Upload a file from your local drive, lets say in â€œc:/ftpul/u.txtâ€�
   /* if (a.uploadFile(var))
      {
        textTargetUri.setText("connected"+var);

      System.out.println(a.getLastSuccessMessage ());
      }
      else
          textTargetUri.setText("connected1"+var);
        System.out.println(a.getLastErrorMessage ());
    }*/

            // a.uploadFile(var);

            //textTargetUri.setText("connected"+var);


        }

        Toast.makeText(getApplicationContext(),
                "finally uploaded to the server",
                Toast.LENGTH_LONG).show();
    }

    @SuppressLint("NewApi")
    private static String getRealPathApi19Above(Context context, Uri uri) {
        String filePath = "";
        try {
            final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

            // DocumentProvider
            if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
                // ExternalStorageProvider
                if (isExternalStorageDocument(uri)) {
                    final String docId = DocumentsContract.getDocumentId(uri);
                    final String[] split = docId.split(":");
                    final String type = split[0];

                    if ("primary".equalsIgnoreCase(type)) {
                        return Environment.getExternalStorageDirectory() + "/" + split[1];
                    }

                    // TODO handle non-primary volumes
                }
                // DownloadsProvider
                else if (isDownloadsDocument(uri)) {

                    final String id = DocumentsContract.getDocumentId(uri);
                    final Uri contentUri = ContentUris.withAppendedId(
                            Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                    return getDataColumn(context, contentUri, null, null);
                }
                // MediaProvider
                else if (isMediaDocument(uri)) {
                    final String docId = DocumentsContract.getDocumentId(uri);
                    final String[] split = docId.split(":");
                    final String type = split[0];

                    Uri contentUri = null;
                    if ("image".equals(type)) {
                        contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                    } else if ("video".equals(type)) {
                        contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                    } else if ("audio".equals(type)) {
                        contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                    }

                    final String selection = "_id=?";
                    final String[] selectionArgs = new String[]{
                            split[1]
                    };

                    return getDataColumn(context, contentUri, selection, selectionArgs);
                }
            }
            // MediaStore (and general)
            else if ("content".equalsIgnoreCase(uri.getScheme())) {

                // Return the remote address
                if (isGooglePhotosUri(uri))
                    return uri.getLastPathSegment();

                return getDataColumn(context, uri, null, null);
            }
            // File
            else if ("file".equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            }


      /*  String wholeID = DocumentsContract.getDocumentId(uri);

        // Split at colon, use second item in the array
        String id = wholeID.split(":")[1];

        String[] column = {MediaStore.Images.Media.DATA};

        // where id is equal to
        String sel = MediaStore.Images.Media._ID + "=?";

        Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                column, sel, new String[]{id}, null);

        int columnIndex = cursor.getColumnIndex(column[0]);

        if (cursor.moveToFirst()) {
            filePath = cursor.getString(columnIndex);
        }
        cursor.close();
        */

        } catch (Exception e) {
            filePath = "";
        }
        return filePath;
    }


    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


    @SuppressLint("NewApi")
    private static String getRealPathApi11to18(Context context, Uri contentUri) {
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            String result = null;

            CursorLoader cursorLoader = new CursorLoader(
                    context,
                    contentUri, proj, null, null, null);
            Cursor cursor = cursorLoader.loadInBackground();

            if (cursor != null) {
                int column_index =
                        cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                result = cursor.getString(column_index);
            }
            return result;
        } catch (Exception e) {
            return "";
        }
    }


    public static String getImagePath(Context context, Uri uri) {
        if (Build.VERSION.SDK_INT < 19)
            return getRealPathApi11to18(context, uri);
        else
            return getRealPathApi19Above(context, uri);

    } public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

//    public String getRealPathFromURI(Uri contentUri) {
////        Uri selectedImageUri = contentUri;
////        String selectedImagePath = uriToFilename(selectedImageUri);
////        String res = null;
////        String[] proj = { MediaStore.Images.Media.DATA };
////        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
////        if (cursor !=null) {
////            if (cursor.moveToFirst()) {
////                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
////                res = cursor.getString(column_index);
////                Log.i("res_1","res__"+res);
////            }
////            cursor.close();
////        }else {
////            Log.i("res_2","res__"+res);
////        }
////        Log.i("res_","res__"+res);
//        String[] projection = {MediaStore.Images.Media.DATA};
//        Cursor cursor = getContentResolver().query(contentUri, projection, null, null, null);
//        //int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//        int column_index = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
//        cursor.moveToFirst();
//        Log.i("column_index__","column_index__"+contentUri+"___"+column_index);
//        return cursor.getString(column_index);
//    }
//
//    public static String getRealPathFromUri(Context context, Uri uri) throws IOException {
//
//        String filePath = null;
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            if (DocumentsContract.isDocumentUri(context, uri)) {
//
//                String documentId = DocumentsContract.getDocumentId(uri);
//                if (isMediaDocument(uri)) { // MediaProvider.
//                    String id = documentId.split(":")[1];
//
//                    String selection = MediaStore.Images.Media._ID + "=?";
//                    String[] selectionArgs = {id};
//
//                    filePath = getDataColumnImage(context, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection, selectionArgs);
//                } else if (isDownloadsDocument(uri)) { // DownloadsProvider.
//                    Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(documentId));
//                    filePath = getDataColumnImage(context, contentUri, null, null);
//                }
//            }
//            else if ("content".equalsIgnoreCase(uri.getScheme())) {
//                filePath = getDataColumnImage(context, uri, null, null);
//            }
//            else if ("file".equals(uri.getScheme())) {
//                filePath = uri.getPath();
//            }else
//                return filePath = null;
//        }
//        return filePath;
//    }
//
//
//    private static boolean isMediaDocument(Uri uri) {
//        return "com.android.providers.media.documents".equals(uri.getAuthority());
//    }
//
//    private static boolean isDownloadsDocument(Uri uri) {
//        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
//    }
//
//    private static String getDataColumnImage(Context context, Uri uri, String selection, String[] selectionArgs) {
//
//        String path = null;
//
//        String[] projection = new String[]{
//                MediaStore.Files.FileColumns._ID,
//                MediaStore.Images.Media.DATE_TAKEN,
//                MediaStore.Images.Media.WIDTH,
//                MediaStore.Images.Media.HEIGHT,
//                MediaStore.MediaColumns.TITLE,
//                MediaStore.Images.Media.MIME_TYPE,
//        };
//        Cursor cursor = null;
//        try {
//            cursor = context.getContentResolver().query(
//                    uri,
//                    projection,
//                    selection,
//                    selectionArgs,
//                    null
//            );
//            if (cursor != null && cursor.moveToFirst()) {
//                int idColumn = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns._ID);
//                int titleColumn = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.TITLE);
//
//                Uri photoUri = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, cursor.getString(idColumn));
//                String title = cursor.getString(titleColumn);
//
//                Log.e("TAG", "photoUri: " + photoUri);
//                Log.e("TAG", "title: " + title);
//                Log.e("TAG", "photoUri.getPath: " + photoUri.getPath());
//            }
//        } catch (Exception e) {
//            if (cursor != null) {
//                cursor.close();
//            }
//        }
//        return path;
//
//        // MediaStore.Images.Media.DATA is deprecated, so I want to use the other way as mentioned above to get real path.
//        /*String[] projection = new String[]{MediaStore.Images.Media.DATA};
//            Cursor cursor = null;
//            try {
//                cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
//                if (cursor != null && cursor.moveToFirst()) {
//                    int columnIndex = cursor.getColumnIndexOrThrow(projection[0]);
//                    path = cursor.getString(columnIndex);
//                }
//            } catch (Exception e) {
//                if (cursor != null) {
//                    cursor.close();
//                }
//            }
//            return path;*/
//    }

    public void FTPUploadImage(){
        FTPClient ftp = new FTPClient();
        FTPClientConfig config = new FTPClientConfig();
        //config.setXXX(YYY); // change required options
        // for example config.setServerTimeZoneId("Pacific/Pitcairn")
        ftp.configure(config );
        boolean error = false;
        try {
            int reply;
            String server = "ftp//49.128.60.174";
            ftp.connect(server);
            Log.i("Connected to","Connected to " + server + ".");
            Log.i("Connected to","ftp.getReplyString() " + ftp.getReplyString() + ".");
            //System.out.print(ftp.getReplyString());

            // After connection attempt, you should check the reply code to verify
            // success.
            reply = ftp.getReplyCode();

            if(!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                //System.err.println("FTP server refused connection.");
                Log.i("Connected to","ftp.getReplyString() " + ftp.getReplyString() + ".");
                System.exit(1);
            }
     // ... // transfer files
            ftp.logout();
        } catch(IOException e) {
            error = true;
            e.printStackTrace();
        } finally {
            if(ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch(IOException ioe) {
                    // do nothing
                }
            }
            System.exit(error ? 1 : 0);
        }
    }

    void FTPUpload(Uri uri){
        //String filename = uri.getLastPathSegment();
        File filename = new File(uri.toString());
        FTPClient ftpClient = null;


        //for(long batchID : filereport.keySet()){

           // if(stop)return;
            try{
                ftpClient = new FTPClient();
                ftpClient.setConnectTimeout(5000);
                ftpClient.connect("49.128.60.174",21);

                if(ftpClient.login("1111111", "Myret@12")){
                    //ftpClient.enterLocalActiveMode();

                    //if(ftpactivemode){

                        //ftpClient.enterLocalActiveMode();
                    //}else{
                        ftpClient.enterLocalPassiveMode();
                    //}
                    ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                    //ftppath => /
                    if(ftpClient.changeWorkingDirectory("sumati")){

                        //Log.e("BATCH ID", batchID+">>>>>>>>>>>>>>>"+filereport.get(batchID));
                        boolean success = false;
                        String errcode = "";
                        //File filename = filereport.get(batchID);
                        for(int retry=0;retry<3;retry++){
                            //if(stop)return;
                            try{
                                Log.i("GRIBReport FTP", "Report Uploading > "+filename);

                                if(filename.isDirectory()){

                                    Log.i("GRIBReport FTP", "Create Dir > "+filename.getName());
                                    File[] files = filename.listFiles();
                                    success = true;
                                    for(File f:files){
                                        Log.i("GRIBReport FTP", "Report Uploading > "+f.getAbsolutePath());
                                        InputStream is = new FileInputStream(f);

                                        success = ftpClient.storeFile("/"+filename.getName()+"/"+f.getName(), is);

                                        if(!success){//error happens on FTP
                                            break;
                                        }
                                        //sftpchan.put(f.getAbsolutePath(), f.getName());
                                    }

                                }else{
                                    InputStream is = new FileInputStream(filename);
                                    success = ftpClient.storeFile(filename.getName(), is);
                                    is.close();
                                }

                                if(success){
                                    //DBFunc.ExecQuery("UPDATE batch_info SET status = 1, errmsg = '', time = "+ Calendar.getInstance().getTimeInMillis()+" WHERE id = "+batchID, true);
                                    Log.i("GRIBReport FTP", "Report Uploaded > "+filename);
                                    break;
                                }else{
                                    String errmsg = ftpClient.getReplyString();
                                    errcode = Base64.encodeToString(errmsg.getBytes(), Base64.DEFAULT);;
                                    Log.e("GRIBReport FTP Upload",errmsg);
                                }

                                //if success break;
                                //success = true;

                            }catch(IOException e){

                                String errmsg = e.getMessage()+"\r\n";
                                for(StackTraceElement ste : e.getStackTrace()){
                                    errmsg += ste.toString()+"\r\n";
                                }
                                errcode = Base64.encodeToString(errmsg.getBytes(), Base64.DEFAULT);
                                Log.w("GRIBReport FTP", "Failed Upload > "+filename);
                                Log.e("GRIBReport FTP", e.toString());
                                Log.w("GRIBReport FTP", "Retrying "+(retry+1)+"/3");

                            }
                        }
                        if(!success){
                            //DBFunc.ExecQuery("UPDATE batch_info SET status = -1, errmsg = '"+DBFunc.PurifyString(errcode)+"', time = "+Calendar.getInstance().getTimeInMillis()+" WHERE id = "+batchID, true);
                        }
                    }else{
                        String errmsg = "FTP Path doesn't exist!";

                        String errcode = Base64.encodeToString(errmsg.getBytes(), Base64.DEFAULT);
                        //DBFunc.ExecQuery("UPDATE batch_info SET status = -1, errmsg = '"+DBFunc.PurifyString(errcode)+"', time = "+Calendar.getInstance().getTimeInMillis()+" WHERE id = "+batchID, true);

                    }
                }else{
                    String errmsg = "FTP Username/Password is wrong!";

                    String errcode = Base64.encodeToString(errmsg.getBytes(), Base64.DEFAULT);
                    //DBFunc.ExecQuery("UPDATE batch_info SET status = -1, errmsg = '"+DBFunc.PurifyString(errcode)+"', time = "+Calendar.getInstance().getTimeInMillis()+" WHERE id = "+batchID, true);
                }


                if(ftpClient.isConnected()){
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            }catch(Exception e){

                String errmsg = e.getMessage()+"\r\n";
                for(StackTraceElement ste : e.getStackTrace()){
                    errmsg += ste.toString()+"\r\n";
                }
                //errmsg = errmsg + String.valueOf(f_test);

                String errcode = Base64.encodeToString(errmsg.getBytes(), Base64.DEFAULT);
                //DBFunc.ExecQuery("UPDATE batch_info SET status = -1, errmsg = '"+DBFunc.PurifyString(errcode)+"', time = "+Calendar.getInstance().getTimeInMillis()+" WHERE id = "+batchID, true);
            }
            try{
                if(ftpClient!=null){
                    if(ftpClient.isConnected()){
                        ftpClient.logout();
                        ftpClient.disconnect();
                    }
                }
            }catch(Exception e){}
        //}


    }
}
