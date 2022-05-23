package com.dcs.myretailer.app.SFTP;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MyJobService extends JobService {
    @Override
    public boolean onStartJob(JobParameters params) {
        // Write your code here
        Log.i("MyJobService_","onStartJob___"+params.getJobId());
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.i("MyJobService_","onStopJob___"+params.getJobId());
        return true;
    }

}

//import android.app.job.JobParameters;
//import android.app.job.JobService;
//import android.os.Build;
//import android.os.Handler;
//import android.os.HandlerThread;
//import android.util.Log;
//
//import androidx.annotation.RequiresApi;
//
//import java.util.Timer;
//import java.util.TimerTask;
//
//@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//public class MyJobService extends JobService {
//
//    private static final String TAG = MyJobService.class.getSimpleName();
//
//    @Override
//    public boolean onStartJob(final JobParameters params) {
//
//        HandlerThread handlerThread = new HandlerThread("SomeOtherThread");
//        handlerThread.start();
//
//        Handler handler = new Handler(handlerThread.getLooper());
//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//                Log.e(TAG, "Running!!!!!!!!!!!!!");
//                jobFinished(params, true);
//            }
//        });
//
//        return true;
//    }
//
//    @Override
//    public boolean onStopJob(final JobParameters params) {
//        Log.d(TAG, "onStopJob() was called");
//        return true;
//    }
//}

//public class MyJobService extends JobService {
//    private LongRunningTask mRunningTask;
//    @Override
//    public boolean onStartJob(JobParameters params) {
//        if (mRunningTask == null)
//            mRunningTask = new LongRunningTask();
//        mRunningTask.startTask();
//        return true;
//    }
//    @Override
//    public boolean onStopJob(JobParameters params) {
//        mRunningTask.finishTask();
//        return true;
//    }
//}
//
//class LongRunningTask {
//    private final String TAG = "LongRunningTask";
//    private Timer mTimer;
//    private int count = 0;
//    LongRunningTask() {
//    }
//    void startTask() {
//        if (mTimer == null)
//            mTimer = new Timer();
//
//        mTimer.scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                Log.e(TAG, "Executing long running task " + count++);
//            }
//        }, 0, 1000);
//    }
//    void finishTask() {
//        if (mTimer == null) return;
//        mTimer.cancel();
//    }
//}