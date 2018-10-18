package com.app.my.listeningapp;


import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MyTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "Starting app...");

        PackageManager pm = getPackageManager();
        boolean isTelephonySupported = pm.hasSystemFeature(PackageManager.FEATURE_TELEPHONY);
        boolean isGSMSupported = pm.hasSystemFeature(PackageManager.FEATURE_TELEPHONY_GSM);

        if (isTelephonySupported && isGSMSupported){
            Log.d(TAG, "the phone can receive calls");

        }else Log.d(TAG, "The phone can't receive calls, please change device!");
    }
}
