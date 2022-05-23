//package com.dcs.myretailer.app.SFTP;
//
//import android.annotation.SuppressLint;
//import android.app.job.JobInfo;
//import android.app.job.JobScheduler;
//import android.content.ComponentName;
//import android.content.Context;
//import android.os.Build;
//import android.util.Log;
//
//import androidx.annotation.RequiresApi;
//
//import com.dcs.myretailer.app.ENUM.Constraints;
//import com.dcs.myretailer.app.Query.Query;
//
//import java.io.File;
//import java.util.concurrent.TimeUnit;
//
//public class RunShedular {
//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    public RunShedular(Context context, File filePath, String fileName) {
//        String type = Query.findfieldNameByTableName("type","FTPSync", true);
//        Log.i("first__","first____"+scheduleJob(context)+"___"+ JobScheduler.RESULT_SUCCESS);
//        if(scheduleJob(context) == JobScheduler.RESULT_SUCCESS) {
//            Log.i("second__","second__"+scheduleJob(context)+"___"+JobScheduler.RESULT_SUCCESS);
//            if (type.equals(Constraints.FTP_SELECTED_FTP)) {
//                new FTPInterface(filePath, fileName);
//            } else {
//                new SFTPInterface(filePath, fileName);
//            }
//        }
//    }
//
//
//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    private static Integer scheduleJob(Context context) {
//        // private static void scheduleJob(Context context) {
//
//
//        // The JobService that we want to run
//        final ComponentName name = new ComponentName(context, MyJobService.class);
//
//        // Schedule the job
//        final int result = jobScheduler.schedule(getJobInfo(123, 1, name));
//
//        // If successfully scheduled, log this thing
//        if (result == JobScheduler.RESULT_SUCCESS) {
//            Log.i("TAG", "Scheduled job successfully!");
//        }
//        return result;
//    }
//
//    @SuppressLint("MissingPermission")
//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    private static JobInfo getJobInfo(final int id, final long hour, final ComponentName name) {
//        //final long interval = TimeUnit.HOURS.toMillis(hour); // run every hour
//        final long interval = TimeUnit.SECONDS.toMillis(5); // run every hour
//        final boolean isPersistent = true; // persist through boot
//        final int networkType = JobInfo.NETWORK_TYPE_ANY; // Requires some sort of connectivity
//
//        final JobInfo jobInfo;
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            jobInfo = new JobInfo.Builder(id, name)
//                    .setMinimumLatency(interval)
//                    .setRequiredNetworkType(networkType)
//                    .setPersisted(isPersistent)
//                    .build();
//        } else {
//            jobInfo = new JobInfo.Builder(id, name)
//                    .setPeriodic(interval)
//                    .setRequiredNetworkType(networkType)
//                    .setPersisted(isPersistent)
//                    .build();
//        }
//
//        return jobInfo;
//    }
//
//}
