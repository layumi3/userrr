package com.bdg.telkom.jobschedullercompat;

import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import me.tatarka.support.job.JobInfo;
import me.tatarka.support.job.JobParameters;
import me.tatarka.support.job.JobScheduler;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    public static final int MSG_UNCOLOUR_START = 0;
    public static final int MSG_UNCOLOUR_STOP = 1;
    public static final int MSG_SERVICE_OBJ = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_act);
        Resources res = getResources();
        defaultColor = res.getColor(R.color.none_received);
        startJobColor = res.getColor(R.color.start_received);
        stopJobColor = res.getColor(R.color.stop_received);

        // Set up UI.
        mShowStartView = (TextView) findViewById(R.id.onstart_textview);
        mShowStopView = (TextView) findViewById(R.id.onstop_textview);
        mParamsTextView = (TextView) findViewById(R.id.task_params);
        mDelayEditText = (EditText) findViewById(R.id.delay_time);
        mDeadlineEditText = (EditText) findViewById(R.id.deadline_time);
        mPeriodEditText = (EditText) findViewById(R.id.period_time);
        mNoConnectivityRadioButton = (RadioButton) findViewById(R.id.checkbox_none);
        mWiFiConnectivityRadioButton = (RadioButton) findViewById(R.id.checkbox_unmetered);
        mAnyConnectivityRadioButton = (RadioButton) findViewById(R.id.checkbox_any);
        mRequiresChargingCheckBox = (CheckBox) findViewById(R.id.checkbox_charging);
        mRequiresIdleCheckbox = (CheckBox) findViewById(R.id.checkbox_idle);
        mPersistedCheckbox = (CheckBox) findViewById(R.id.checkbox_persisted);
        mBackoffDelayEditText = (EditText) findViewById(R.id.backoff_delay_time);
        mBackoffLinearRadioButton = (RadioButton) findViewById(R.id.checkbox_linear);
        mBackoffExponentialRadioButton = (RadioButton) findViewById(R.id.checkbox_exponential);
        mServiceComponent = new ComponentName(this, JobService.class);
        Intent startServiceIntent = new Intent(this, JobService.class);
        startServiceIntent.putExtra("messager ", new Messenger(mHandler));
        startService(startServiceIntent);

    }
    // UI fields.
    int defaultColor;
    int startJobColor;
    int stopJobColor;

    private TextView mShowStartView;
    private TextView mShowStopView;
    private TextView mParamsTextView;
    private EditText mDelayEditText;
    private EditText mDeadlineEditText;
    private EditText mPeriodEditText;
    private EditText mBackoffDelayEditText;
    private RadioButton mNoConnectivityRadioButton;
    private RadioButton mWiFiConnectivityRadioButton;
    private RadioButton mAnyConnectivityRadioButton;
    private CheckBox mRequiresChargingCheckBox;
    private CheckBox mRequiresIdleCheckbox;
    private CheckBox mPersistedCheckbox;
    private RadioButton mBackoffLinearRadioButton;
    private RadioButton mBackoffExponentialRadioButton;
    ComponentName mServiceComponent;
    JobService mJobService;

    private static int kJobId = 0;

    Handler mHandler = new Handler(/*default looper*/){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MSG_UNCOLOUR_START:
                    mShowStartView.setBackgroundColor(defaultColor);
                    break;
                case MSG_UNCOLOUR_STOP:
                    mShowStartView.setBackgroundColor(defaultColor);
                    break;
                case MSG_SERVICE_OBJ:
                    mJobService = (JobService) msg.obj;
                    mJobService.setUiCallback(MainActivity.this);
            }
        }
    };

    private boolean ensureService(){
        if (mJobService == null){
            Toast.makeText(MainActivity.this, "Service null, never got callback?",Toast.LENGTH_SHORT).show();
            return false;

        }
        return true;
    }

    private void scheduleJob(View view){
        if (!ensureService()){
            return;
        }

        JobInfo.Builder builder = new JobInfo.Builder(kJobId++,mServiceComponent);
        String delay = mDelayEditText.getText().toString();
        if (delay != null && !TextUtils.isEmpty(delay)){
            builder.setMinimumLatency(Long.valueOf(delay)*1000);
        }

        String deadline = mDeadlineEditText.getText().toString();
        if (deadline != null && !TextUtils.isEmpty(deadline)){
            builder.setMinimumLatency(Long.valueOf(deadline)*1000);
        }
        String period = mPeriodEditText.getText().toString();
        if (period != null && !TextUtils.isEmpty(period)){
            builder.setMinimumLatency(Long.valueOf(period)*1000);
        }

        boolean requiresUnmetered = mWiFiConnectivityRadioButton.isChecked();
        boolean requiresAnyConnectivity = mAnyConnectivityRadioButton.isChecked();

        if (requiresUnmetered){
            builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED);
        }else if (requiresAnyConnectivity){
            builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
        }

        builder.setRequiresDeviceIdle(mRequiresIdleCheckbox.isChecked());
        builder.setRequiresCharging(mRequiresChargingCheckBox.isChecked());
        builder.setPersisted(mPersistedCheckbox.isChecked());

        String backoffTime =mBackoffDelayEditText.getText().toString();
        if (backoffTime !=null && TextUtils.isEmpty(backoffTime)){
        int backoffPolicy = mBackoffExponentialRadioButton.isChecked()? JobInfo.BACKOFF_POLICY_LINEAR : JobInfo.BACKOFF_POLICY_EXPONENTIAL;
            builder.setBackoffCriteria(Long.valueOf(backoffTime)*1000, backoffPolicy);
        }

        try {
            mJobService.scheduleJob(builder.build());
        }catch (IllegalArgumentException e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }catch (IllegalStateException e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();

        }

    }

    public  void cancelAllJob(View view){
        JobScheduler tm = JobScheduler.getInstance(this);
        List<JobInfo> pendingJobs = tm.getAllPendingJobs();
        for (JobInfo job: pendingJobs){
            Log.d(TAG,"cancelled job : "+job.getId());
        }
        tm.cancelAll();
    }

    public void finishJob(View view){
        if (!ensureService()){
            return;
        }
        mJobService.callJobFinished();
        mParamsTextView.setText("");
    }
    public void failJob(View view){
        if (!ensureService()){
            return;
        }
        mJobService.callJobFailed();
        mParamsTextView.setText("");
    }

    public void onReceivedStartJob(JobParameters params){
        mShowStartView.setBackgroundColor(startJobColor);
        Message m= Message.obtain(mHandler, MSG_UNCOLOUR_START);
        mHandler.sendMessageDelayed(m,1000L);
        mParamsTextView.setText("Executing: "+params.getJobId()+""+params.getExtras());
    }
    public void onReceivedStopJob(){
        mShowStartView.setBackgroundColor(stopJobColor);
        Message m= Message.obtain(mHandler, MSG_UNCOLOUR_STOP);
        mHandler.sendMessageDelayed(m,2000L);
        mParamsTextView.setText("");
    }


}
