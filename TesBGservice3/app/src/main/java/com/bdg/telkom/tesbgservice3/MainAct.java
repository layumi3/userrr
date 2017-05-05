package com.bdg.telkom.tesbgservice3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by lacorp on 6/12/2016.
 */
public class MainAct extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_act);
    }

    public void startIt(View view){
        startService(new Intent(getBaseContext(),Serv.class));
    }
    public void stopIt(View view){
        stopService(new Intent(getBaseContext(),Serv.class));
    }
    public void showValue(View view){
        Toast.makeText(MainAct.this,Serv.showCount()+"",Toast.LENGTH_LONG).show();
    }

}
