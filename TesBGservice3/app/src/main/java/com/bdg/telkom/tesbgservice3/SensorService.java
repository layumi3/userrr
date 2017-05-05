package com.bdg.telkom.tesbgservice3;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by lacorp on 6/12/2016.
 */
public class SensorService extends Service {

    private  int counter=0;

    public SensorService(Context applicationContext){
        super();
//        Toast.makeText(SensorService.this,"Test", LENGTH_SHORT).show();
        Log.i("Here", "here i am");
    }

    public SensorService(){

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        startTimer();
        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("Exit","ondestroy!");
        Intent broadcastIntent = new Intent("RestartSensor");
        sendBroadcast(broadcastIntent);
        stoptimertask();
    }
    private Timer timer;
    private TimerTask timerTask;
    long oldTime=0;

    public void startTimer(){
        timer = new Timer();
        initializeTimerTask();
        timer.schedule(timerTask, 1000, 1000);
    }

    public void initializeTimerTask(){
        timerTask = new TimerTask() {
            @Override
            public void run() {
                Log.i("in timer", "in timer +++"+(counter++));
//                Toast.makeText(SensorService.this,"Teston start"+counter, LENGTH_SHORT).show();

            }
        };
    }

    public void stoptimertask(){
        if (timer != null){
            timer.cancel();
            timer=null;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }
}
