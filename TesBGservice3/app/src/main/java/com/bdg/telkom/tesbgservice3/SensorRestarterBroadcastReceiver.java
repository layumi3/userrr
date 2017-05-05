package com.bdg.telkom.tesbgservice3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by lacorp on 6/12/2016.
 */
public class SensorRestarterBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(SensorRestarterBroadcastReceiver.class.getSimpleName(),"Service Stop! Oooooppsss");
        context.startService(new Intent(context, SensorService.class));
    }
}
