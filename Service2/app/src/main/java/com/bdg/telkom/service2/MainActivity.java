package com.bdg.telkom.service2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.format.DateFormat;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    private DateFormat mLastUpdateTime;
    //    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Calendar c = Calendar.getInstance();
        System.out.println("Current time =&gt; "+c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("mm");
        String formattedDate = df.format(c.getTime());

//        MyTimerTask myTask = new MyTimerTask();
//        Timer myTimer = new Timer();

        startService(new Intent(this,FirstService.class));
        if (formattedDate.equals("44")){
            Log.d("First","update 50");
            Toast.makeText(this,"updated: "+formattedDate,Toast.LENGTH_SHORT).show();}
        if (formattedDate.equals("45")){
            Toast.makeText(this,"updatedss: "+formattedDate,Toast.LENGTH_SHORT).show();}
        if (formattedDate.equals("46")){
            Log.d("First","update 555");
            Toast.makeText(this,"updateds: "+formattedDate,Toast.LENGTH_SHORT).show();

//            Toast.makeText(getApplicationContext(),"ss "+kokom,Toast.LENGTH_SHORT).show();
//            String kokom = startService(new Intent(this,FirstService.class)).toString();
//            Log.d("First","update 1");
        }/*else if (formattedDate.equals("26")){
            Toast.makeText(this,"updated: 2"+formattedDate,Toast.LENGTH_SHORT).show();
//            Log.d("First","update 50");

        }*/


//        setContentView(R.layout.activity_main);
    }

//    private Runnable datas = new Runnable() {
//        @Override
//        public void run() {
//            handler.postDelayed(datas,10000);
//
//        }
//    }


}
