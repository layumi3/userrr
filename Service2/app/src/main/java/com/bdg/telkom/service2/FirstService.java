package com.bdg.telkom.service2;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by lacorp on 6/15/2016.
 */
public class FirstService extends Service {
    private final String TAG="Serviceee";
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

//    @Override
//    public void onStart(Intent intent, int startId) {
//        super.onStart(intent, startId);
//        Log.d("First","first started");
////        startActivity(new Intent(this, MainActivity.class));
//        this.stopSelf();
//    }

/*http://inchoo.net/dev-talk/android-development/android-simple-service/*/
        @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

//
//            Log.i(TAG, "Service onStartCommand " + startId);
//
//            final int currentId = startId;
//
//            Runnable r = new Runnable() {
//                public void run() {
//
//                    for (int i = 0; i < 3; i++)
//                    {
//                        long endTime = System.currentTimeMillis() + 10*1000;
//
//                        while (System.currentTimeMillis() < endTime) {
//                            synchronized (this) {
//                                try {
//                                    wait(endTime -
//                                            System.currentTimeMillis());
//                                } catch (Exception e) {
//                                }
//                            }
//                        }
//                        Log.i(TAG, "Service running " + currentId);
//                    }
//                    stopSelf();
//
//                }
//            };
//            Intent dialogIntent = new Intent(this, MainActivity.class);
//            dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(dialogIntent);
//
//            Thread t = new Thread(r);
//            t.start();
//



            Intent dialogIntent = new Intent(this, MainActivity.class);
            dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(dialogIntent);
            if (startId==50){
//                Toast.makeText(getApplicationContext(),TAG+"50",Toast.LENGTH_SHORT).show();
                Log.d("First","ifffffffffffff 50");
            }

            Log.d("First","first started");
//        startActivity(new Intent(this, MainActivity.class));
        return START_STICKY;
    }

    

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("First","first destroy");
    }
}
