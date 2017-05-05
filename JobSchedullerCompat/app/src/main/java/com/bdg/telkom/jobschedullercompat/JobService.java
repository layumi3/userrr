package com.bdg.telkom.jobschedullercompat;

import android.content.Intent;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import java.util.LinkedList;

import me.tatarka.support.job.JobInfo;
import me.tatarka.support.job.JobParameters;
import me.tatarka.support.job.JobScheduler;

/**
 * Created by lacorp on 6/9/2016.
 */
public class JobService extends me.tatarka.support.job.JobService {

    private static final String TAG ="SyncService";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"Service created");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"Service destroyed");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Messenger callback = intent.getParcelableExtra("messanger");
        Message m = Message.obtain();
        m.what = MainActivity.MSG_SERVICE_OBJ;
        m.obj=this;
        try {
            callback.send(m);
        }catch (RemoteException e){
            Log.e(TAG,"Error passing service object back to activity.");
        }
        return START_NOT_STICKY;
    }

    @Override
    public boolean onStartJob(JobParameters params) {
        jobParamsMap.add(params);
        if (mActivity != null){
            mActivity.onReceivedStopJob();
        }
        Log.i(TAG,"on stop job: "+params.getJobId());
        return true;
    }
    MainActivity mActivity;
    private final LinkedList<JobParameters> jobParamsMap = new LinkedList<JobParameters>();

    public void setUiCallback(MainActivity activity){
        mActivity = activity;
    }

    /*send job to the job scheduller*/
    public void scheduleJob(JobInfo t){
        Log.d(TAG,"Scheduling Job");
        JobScheduler tm = JobScheduler.getInstance(this);
        tm.schedule(t);
    }

    public boolean callJobFinished(){
        JobParameters params= jobParamsMap.poll();
        if (params == null){
            return false;
        }else {
            Log.i(TAG,"job finished: "+params.getJobId());
            jobFinished(params,false);
            return true;
        }
    }

    public boolean callJobFailed(){
        JobParameters params= jobParamsMap.poll();
        if (params == null){
            return false;
        }else {
            Log.i(TAG,"job finished: "+params.getJobId());
            jobFinished(params,true);
            return true;
        }
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
