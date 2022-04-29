package com.dcs.myretailer.app.SFTP;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.dcs.myretailer.app.R;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FPTFileCreateActivity extends AppCompatActivity {
//https://data-flair.training/blogs/storage-in-android/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fptfile_create);

        String str = "18000061|116|26042022|00|0|0.00|0.00|0.00|0.00|0|0.00|0.00|0.00|0.00|0.00|0.00|0.00|Y\n" +
                "18000061|116|26042022|01|0|0.00|0.00|0.00|0.00|0|0.00|0.00|0.00|0.00|0.00|0.00|0.00|Y\n" +
                "18000061|116|26042022|02|0|0.00|0.00|0.00|0.00|0|0.00|0.00|0.00|0.00|0.00|0.00|0.00|Y\n" +
                "18000061|116|26042022|03|0|0.00|0.00|0.00|0.00|0|0.00|0.00|0.00|0.00|0.00|0.00|0.00|Y\n" +
                "18000061|116|26042022|04|0|0.00|0.00|0.00|0.00|0|0.00|0.00|0.00|0.00|0.00|0.00|0.00|Y\n" +
                "18000061|116|26042022|05|0|0.00|0.00|0.00|0.00|0|0.00|0.00|0.00|0.00|0.00|0.00|0.00|Y\n" +
                "18000061|116|26042022|06|0|0.00|0.00|0.00|0.00|0|0.00|0.00|0.00|0.00|0.00|0.00|0.00|Y\n" +
                "18000061|116|26042022|07|0|0.00|0.00|0.00|0.00|0|0.00|0.00|0.00|0.00|0.00|0.00|0.00|Y\n" +
                "18000061|116|26042022|08|0|0.00|0.00|0.00|0.00|0|0.00|0.00|0.00|0.00|0.00|0.00|0.00|Y\n" +
                "18000061|116|26042022|09|0|0.00|0.00|0.00|0.00|0|0.00|0.00|0.00|0.00|0.00|0.00|0.00|Y\n" +
                "18000061|116|26042022|10|1|13.01|0.78|3.71|0.00|1|0.00|0.00|0.00|0.00|0.00|0.00|13.79|Y\n" +
                "18000061|116|26042022|11|1|16.98|1.02|0.00|0.00|1|18.00|0.00|0.00|0.00|0.00|0.00|0.00|Y\n" +
                "18000061|116|26042022|12|6|77.01|4.61|9.88|0.00|6|17.00|0.00|17.55|0.00|0.00|0.00|47.07|Y\n" +
                "18000061|116|26042022|13|8|176.39|10.59|7.52|0.00|8|117.00|0.00|24.00|0.00|0.00|0.00|45.98|Y\n" +
                "18000061|116|26042022|14|6|55.02|3.30|3.18|0.00|6|9.50|0.00|25.50|0.00|0.00|0.00|23.32|Y\n" +
                "18000061|116|26042022|15|9|126.97|7.62|14.41|0.00|9|56.00|0.00|25.00|0.00|0.00|0.00|53.59|Y\n" +
                "18000061|116|26042022|16|9|91.76|5.50|4.74|0.00|9|74.55|0.00|0.00|0.00|0.00|0.00|22.71|Y\n" +
                "18000061|116|26042022|17|15|311.27|18.69|2.54|0.00|15|147.00|0.00|128.00|0.00|0.00|0.00|54.96|Y\n" +
                "18000061|116|26042022|18|24|474.61|28.48|19.41|0.00|24|99.50|0.00|278.25|0.00|0.00|0.00|125.34|Y\n" +
                "18000061|116|26042022|19|23|365.96|21.95|34.59|0.00|23|54.50|0.00|151.00|0.00|0.00|0.00|182.41|Y\n" +
                "18000061|116|26042022|20|24|403.88|24.23|60.39|0.00|24|135.00|0.00|37.50|0.00|0.00|0.00|255.61|Y\n" +
                "18000061|116|26042022|21|17|220.41|13.19|8.90|0.00|17|73.50|0.00|127.00|0.00|0.00|0.00|33.10|Y\n" +
                "18000061|116|26042022|22|0|0.00|0.00|0.00|0.00|0|0.00|0.00|0.00|0.00|0.00|0.00|0.00|Y\n" +
                "18000061|116|26042022|23|0|0.00|0.00|0.00|0.00|0|0.00|0.00|0.00|0.00|0.00|0.00|0.00|Y\n";

        writeGeneratedFile(this,"H18000061_20220426.txt",str);

//        writeToFile("Hello!",this,"config.txt");
    }


    public void writeGeneratedFile(Context context, String sFileName, String sBody) {
        try {
           // String FolerName = sdf3.format(myCalendar2.getTime()).toString();
            //File direct = new File("/storage/emulated/0/"+"FileFormatby");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd");
            Date now = new Date();
            //String fileName = formatter.format(now) + ".txt";//like 2016_01_12.txt
            String fileName = formatter.format(now);//like 2016_01_12.txt

            File direct = new File("/storage/emulated/0/"+fileName);

            if (direct.exists()){
                Log.i("FolerName" + direct, "exists");
                try {

                    FileUtils.deleteDirectory(direct);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Log.i("FolerName" + direct, "else");
//                direct.mkdir();
                if (direct.mkdirs()) {
                    Log.i("file_" + fileName, "created___");
                } else {
                    Log.i("file_" + fileName, "Error___");
                }
            }

//                File  f = new File("non_existing_dir/someDir");
//                if (direct.mkdir()) {
//                    Log.i("file_" + FolerName, "created");
//                } else {
//                    Log.i("file_" + FolerName, "Error__");

            //}


            //File root = new File(direct.getAbsolutePath() +"/"+"master.db");
            File root = new File(direct.getAbsolutePath());
//            File root = new File(Environment.getExternalStorageDirectory(), "Notes");
            if (!root.exists()) {
                root.mkdirs();
            }
            File gpxfile = new File(root, sFileName);
            FileWriter writer = new FileWriter(gpxfile);
            writer.append(sBody);
            writer.flush();
            writer.close();
            Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show();
            new FTPInterface(gpxfile,sFileName);
            new SFTPInterface(gpxfile,sFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeToFile(String data, Context context,String filename) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                    context.openFileOutput(filename, Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}