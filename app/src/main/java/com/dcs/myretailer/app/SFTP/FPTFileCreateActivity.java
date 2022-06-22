package com.dcs.myretailer.app.SFTP;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Model.Result;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class FPTFileCreateActivity extends AppCompatActivity {
//    private final String TAG = "AnotherActivity";
//    public static Intent getStartIntent(Context context) {
//        Intent intent = new Intent(context, FPTFileCreateActivity.class);
//        return intent;
//    }
//https://data-flair.training/blogs/storage-in-android/
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fptfile_create);

        jobScheduler = (JobScheduler) getSystemService(
                Context.JOB_SCHEDULER_SERVICE);
//
//
//        // Job Scheduler
//        final JobScheduler scheduler = (JobScheduler) getApplicationContext().getSystemService(JOB_SCHEDULER_SERVICE);
//        ComponentName componentName = new ComponentName(this, MyJobService.class);
//        @SuppressLint("MissingPermission") JobInfo jobInfo = new JobInfo.Builder(JOB_ID, componentName)
//                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
//                .setPersisted(true)
//                .build();
//        if (scheduler != null) {
//            // Checking if job is already running
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                if (scheduler.getPendingJob(JOB_ID) == jobInfo)
//                    return;
//            }
//            scheduler.schedule(jobInfo);
//        }
//
//        String str = "18000061|116|26042022|00|0|0.00|0.00|0.00|0.00|0|0.00|0.00|0.00|0.00|0.00|0.00|0.00|Y\n" +
//                "18000061|116|26042022|01|0|0.00|0.00|0.00|0.00|0|0.00|0.00|0.00|0.00|0.00|0.00|0.00|Y\n" +
//                "18000061|116|26042022|02|0|0.00|0.00|0.00|0.00|0|0.00|0.00|0.00|0.00|0.00|0.00|0.00|Y\n" +
//                "18000061|116|26042022|03|0|0.00|0.00|0.00|0.00|0|0.00|0.00|0.00|0.00|0.00|0.00|0.00|Y\n" +
//                "18000061|116|26042022|04|0|0.00|0.00|0.00|0.00|0|0.00|0.00|0.00|0.00|0.00|0.00|0.00|Y\n" +
//                "18000061|116|26042022|05|0|0.00|0.00|0.00|0.00|0|0.00|0.00|0.00|0.00|0.00|0.00|0.00|Y\n" +
//                "18000061|116|26042022|06|0|0.00|0.00|0.00|0.00|0|0.00|0.00|0.00|0.00|0.00|0.00|0.00|Y\n" +
//                "18000061|116|26042022|07|0|0.00|0.00|0.00|0.00|0|0.00|0.00|0.00|0.00|0.00|0.00|0.00|Y\n" +
//                "18000061|116|26042022|08|0|0.00|0.00|0.00|0.00|0|0.00|0.00|0.00|0.00|0.00|0.00|0.00|Y\n" +
//                "18000061|116|26042022|09|0|0.00|0.00|0.00|0.00|0|0.00|0.00|0.00|0.00|0.00|0.00|0.00|Y\n" +
//                "18000061|116|26042022|10|1|13.01|0.78|3.71|0.00|1|0.00|0.00|0.00|0.00|0.00|0.00|13.79|Y\n" +
//                "18000061|116|26042022|11|1|16.98|1.02|0.00|0.00|1|18.00|0.00|0.00|0.00|0.00|0.00|0.00|Y\n" +
//                "18000061|116|26042022|12|6|77.01|4.61|9.88|0.00|6|17.00|0.00|17.55|0.00|0.00|0.00|47.07|Y\n" +
//                "18000061|116|26042022|13|8|176.39|10.59|7.52|0.00|8|117.00|0.00|24.00|0.00|0.00|0.00|45.98|Y\n" +
//                "18000061|116|26042022|14|6|55.02|3.30|3.18|0.00|6|9.50|0.00|25.50|0.00|0.00|0.00|23.32|Y\n" +
//                "18000061|116|26042022|15|9|126.97|7.62|14.41|0.00|9|56.00|0.00|25.00|0.00|0.00|0.00|53.59|Y\n" +
//                "18000061|116|26042022|16|9|91.76|5.50|4.74|0.00|9|74.55|0.00|0.00|0.00|0.00|0.00|22.71|Y\n" +
//                "18000061|116|26042022|17|15|311.27|18.69|2.54|0.00|15|147.00|0.00|128.00|0.00|0.00|0.00|54.96|Y\n" +
//                "18000061|116|26042022|18|24|474.61|28.48|19.41|0.00|24|99.50|0.00|278.25|0.00|0.00|0.00|125.34|Y\n" +
//                "18000061|116|26042022|19|23|365.96|21.95|34.59|0.00|23|54.50|0.00|151.00|0.00|0.00|0.00|182.41|Y\n" +
//                "18000061|116|26042022|20|24|403.88|24.23|60.39|0.00|24|135.00|0.00|37.50|0.00|0.00|0.00|255.61|Y\n" +
//                "18000061|116|26042022|21|17|220.41|13.19|8.90|0.00|17|73.50|0.00|127.00|0.00|0.00|0.00|33.10|Y\n" +
//                "18000061|116|26042022|22|0|0.00|0.00|0.00|0.00|0|0.00|0.00|0.00|0.00|0.00|0.00|0.00|Y\n" +
//                "18000061|116|26042022|23|0|0.00|0.00|0.00|0.00|0|0.00|0.00|0.00|0.00|0.00|0.00|0.00|Y\n";
//
//        writeGeneratedFile(this,"H18000061_20220426.txt",str); //2-	24- Waterway Point
//
//        String str1 = "D78000262022032500000000.00";//1-	CapitalLand

        //writeGeneratedFile(this,"D7800026.703.txt.txt",str1);

//        writeToFile("Hello!",this,"config.txt");
    }

    static JobScheduler jobScheduler = null;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void writeGeneratedFile(Context context, String sFileName, String sBody, String status) {
        try {
           // String FolerName = sdf3.format(myCalendar2.getTime()).toString();
            //File direct = new File("/storage/emulated/0/"+"FileFormatby");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd");
            Date now = new Date();
            //String fileName = formatter.format(now) + ".txt";//like 2016_01_12.txt
            String fileName = formatter.format(now);//like 2016_01_12.txt

            //File direct = new File("/storage/emulated/0/"+fileName);
            //File direct = new File("/storage/emulated/0/mall");
            //File direct = new File("/storage/emulated/0/");
            //File direct = new File("/storage/emulated/0/mall/");
            File direct = new File("/storage/emulated/0/");

            if (direct.exists()){
//                Log.i("FolerName" + direct, "exists");
//                try {
//
////                    FileUtils.deleteDirectory(direct);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            } else {
                Log.i("FolerName" + direct, "else");
//                direct.mkdir();
                if (direct.mkdirs()) {
                    Log.i("file_", "created___" + fileName);
                } else {
                    Log.i("file_" , "Error___"+ fileName);
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
            Log.i("root_",  "root____"+ root);
//            File root = new File(Environment.getExternalStorageDirectory(), "Notes");
            if (!root.exists()) {
                root.mkdirs();
            }

            File direct1 = new File("/storage/emulated/0/mall/backup/");

            if (direct1.exists()){
//                Log.i("FolerName" + direct, "exists");
//                try {
//
////                    FileUtils.deleteDirectory(direct);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

//                /storage/emulated/0/D674838.007.txt
            } else {
                Log.i("FolerName" + direct1, "else");
//                direct.mkdir();
                if (direct1.mkdirs()) {
                    Log.i("file_", "created___" + fileName);
                } else {
                    Log.i("file_" , "Error___"+ fileName);
                }
            }
            File root1 = new File(direct1.getAbsolutePath());
            Log.i("root_",  "root____"+ root1);
//            File root = new File(Environment.getExternalStorageDirectory(), "Notes");
            if (!root1.exists()) {
                root1.mkdirs();
            }

            File gpxfile = new File(root, sFileName);
//            File gpxfile1 = new File(root1, sFileName);
            Log.i("root_",  "root1____"+ gpxfile);
            FileWriter writer = new FileWriter(gpxfile);
           //FileWriter writer1 = new FileWriter(gpxfile1);
            writer.append(sBody);
            //writer1.append(sBody);
            Log.i("root_",  "root2____"+ writer);
            writer.flush();
            //writer1.flush();
            writer.close();
            //writer1.close();
            Log.i("root_",  "root3____"+ writer);
//            //Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show();
//            Log.i("first__","first____"+scheduleJob(context)+"___"+JobScheduler.RESULT_SUCCESS);
//            //if(scheduleJob(context) == JobScheduler.RESULT_SUCCESS) {
//                Log.i("second__","second__"+scheduleJob(context)+"___"+JobScheduler.RESULT_SUCCESS);
//                //new FTPInterface(gpxfile, sFileName);
//                //new SFTPInterface(gpxfile, sFileName);
//           // }
            Schedular schedular = new Schedular();
//            schedular.setFileName(gpxfile.toString());
//            schedular.setFilePath(sFileName);
            schedular.setFileName(gpxfile.toString());
            schedular.setFilePath(sFileName);
            Log.i("root_",  "root4____"+ schedular.getFilePath());
            Query.saveSchedular(sFileName,context,schedular);

            Log.i("dsfdsf____","__gpxfile___"+gpxfile);
            Log.i("dsfdsf____","__sFileName__"+sFileName);
            String type = Query.findfieldNameByTableName("type","FTPSync", true);
            if (type.equals(Constraints.FTP_SELECTED_FTP)) {
                new FTPInterface(context,gpxfile, sFileName,status);
            } else {
                new SFTPInterface(context,gpxfile, sFileName,status);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private static Integer scheduleJob(Context context) {
   // private static void scheduleJob(Context context) {


        // The JobService that we want to run
        final ComponentName name = new ComponentName(context, MyJobService.class);

        // Schedule the job
        final int result = jobScheduler.schedule(getJobInfo(123, 1, name));

        // If successfully scheduled, log this thing
        if (result == JobScheduler.RESULT_SUCCESS) {
            Log.i("TAG", "Scheduled job successfully!");
        }
        return result;
    }

    @SuppressLint("MissingPermission")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private static JobInfo getJobInfo(final int id, final long hour, final ComponentName name) {
        //final long interval = TimeUnit.HOURS.toMillis(hour); // run every hour
        final long interval = TimeUnit.SECONDS.toMillis(5); // run every hour
        final boolean isPersistent = true; // persist through boot
        final int networkType = JobInfo.NETWORK_TYPE_ANY; // Requires some sort of connectivity

        final JobInfo jobInfo;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            jobInfo = new JobInfo.Builder(id, name)
                    .setMinimumLatency(interval)
                    .setRequiredNetworkType(networkType)
                    .setPersisted(isPersistent)
                    .build();
        } else {
            jobInfo = new JobInfo.Builder(id, name)
                    .setPeriodic(interval)
                    .setRequiredNetworkType(networkType)
                    .setPersisted(isPersistent)
                    .build();
        }

        return jobInfo;
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