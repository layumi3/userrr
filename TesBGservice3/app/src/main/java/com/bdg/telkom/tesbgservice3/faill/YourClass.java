package com.bdg.telkom.tesbgservice3.faill;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by lacorp on 6/12/2016.
 */
public class YourClass extends AppCompatActivity {
    private Button myButton;

    //create an handler
    private final Handler myHandler = new Handler();

    final Runnable updateRunnable = new Runnable() {
        public void run() {
            //call the activity method that updates the UI
            updateUI();
        }
    };


    private void updateUI()
    {
        // ... update the UI
        Toast.makeText(getApplicationContext(),"ssss",Toast.LENGTH_SHORT).show();
    }

    private void doSomeHardWork()
    {
        //.... hard work

        //update the UI using the handler and the runnable
        myHandler.post(updateRunnable);

    }

    private View.OnClickListener buttonListener = new View.OnClickListener() {
        public void onClick(View v) {
            new Thread(new Runnable() {

//                doSomeHardWork();

                @Override
                public void run() {
                    doSomeHardWork();
                }

            }).start();
        }
    };
}
