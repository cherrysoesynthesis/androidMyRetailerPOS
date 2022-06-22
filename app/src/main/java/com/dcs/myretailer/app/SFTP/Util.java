package com.dcs.myretailer.app.SFTP;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;

import java.io.File;

public class Util {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void scheduleJob(Context context) {
        ComponentName serviceComponent = new ComponentName(context, MyJobService.class);
        //JobInfo.Builder builder = new JobInfo.Builder(0, serviceComponent);
        JobInfo.Builder builder = new JobInfo.Builder(129, serviceComponent);
        builder.setMinimumLatency(30 * 1000); // Wait at least 30s
        builder.setOverrideDeadline(60 * 1000); // Maximum delay 60s

        JobScheduler jobScheduler = (JobScheduler)context.getSystemService(context.JOB_SCHEDULER_SERVICE);
        jobScheduler.schedule(builder.build());

        Log.i("success__","SUCCESS______"+jobScheduler.RESULT_SUCCESS);
        Log.i("success__","FAILURE______"+jobScheduler.RESULT_FAILURE);
        if (jobScheduler.RESULT_SUCCESS == 1) {
           //
        }
    }
}