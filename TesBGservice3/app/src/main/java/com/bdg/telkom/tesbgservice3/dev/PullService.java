package com.bdg.telkom.tesbgservice3.dev;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by lacorp on 6/12/2016.
 */
public class PullService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public PullService() {
        super("PullService");
    }

    @Override
    protected void onHandleIntent(Intent workIntent) {
        // Gets data from the incoming Intent
        String dataString = workIntent.getDataString();

        // Do work here, based on the contents of dataString
    }
}
