package com.bdg.telkom.tesbgservice3;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by lacorp on 6/12/2016.
 */
public class Serv extends Service {

    public static int count=0;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Intent dialogIntent= new Intent(this,MainAct.class);
        Toast.makeText(this,"BP Started",Toast.LENGTH_LONG).show();
//
//
//        for (int i=1;i<200000;i++){
//            count++;
//        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this,"BP destroyed",Toast.LENGTH_LONG).show();
        super.onDestroy();
    }
    public static int showCount(){
        return count;
    }
}
