package com.bdg.telkom.tesbgservice3;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
///*http://fabcirablog.weebly.com/blog/creating-a-never-ending-background-service-in-android*/
public class MainActivity extends AppCompatActivity {

    Intent mServiceIntent;
    private SensorService mSensorService;
    Context ctx;
    public Context getCtx(){
        return ctx;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ctx = this;
        setContentView(R.layout.activity_main);
        Toast.makeText(getApplicationContext(),"Teston start", Toast.LENGTH_SHORT).show();
        mSensorService = new SensorService(getCtx());
        mServiceIntent = new Intent(getCtx(), mSensorService.getClass());
//        createHandler();
        if (!isMyServiceRunning(mSensorService.getClass())){
            startService(mServiceIntent);

        }
    }


    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service: manager.getRunningServices(Integer.MAX_VALUE)){
            if (serviceClass.getName().equals(service.service.getClassName())){
                Log.i("IsmyserviceRunning?",true+"");
                return true;
            }

        }
        Log.i("isMyServiceRunning?",false+"");
        return false;
    }

    @Override
    protected void onDestroy() {
        stopService(mServiceIntent);
        Log.i("MAINACT","onDestroy!");
        super.onDestroy();
    }


}
