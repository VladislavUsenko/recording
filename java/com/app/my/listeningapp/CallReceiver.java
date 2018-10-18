package com.app.my.listeningapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;

import android.util.Log;


public class CallReceiver extends BroadcastReceiver {

    private static final String TAG = "MyTag";

    private static boolean incomingCall = false;
    private static String phoneNumber = "";
    private MyRecorder recorder = null;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.PHONE_STATE")) {
            String phoneState = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            if (phoneState.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                //Трубка не поднята, телефон звонит
                phoneNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
                incomingCall = true;
                Log.d(TAG,"Incoming call number: " + phoneNumber);

            } else if (phoneState.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
                //Телефон находится в режиме звонка (набор номера при исходящем звонке / разговор)
                if (incomingCall) {
                    recorder = new MyRecorder(phoneNumber);
                    recorder.recordStart();
                    Log.d(TAG,"Is a conversation...");
                    incomingCall = false;
                }
            } else if (phoneState.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
                //Телефон находится в ждущем режиме - это событие наступает по окончанию разговора
                //или в ситуации "отказался поднимать трубку и сбросил звонок".
                if (incomingCall) {
                    Log.d(TAG,"Conversation ended");
                    recorder.releaseRecorder();
                    recorder.recordStop();
                    incomingCall = false;
                }
            }
        }
    }
}