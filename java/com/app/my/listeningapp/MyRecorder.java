package com.app.my.listeningapp;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.text.format.Time;


import java.io.File;

public class MyRecorder {

    private MediaRecorder mediaRecorder;
    private String fileName;

    public MyRecorder(String fileName) {
        Time time = new Time(Time.getCurrentTimezone());
        time.setToNow();
        this.fileName = Environment.getExternalStorageDirectory() + "/" + fileName + time.format("%Y%m%d%H%M%S") +".3gpp";
    }

    public void recordStart() {
        try {
            releaseRecorder();

            File outFile = new File(fileName);
            if (outFile.exists()) {
                outFile.delete();
            }

            mediaRecorder = new MediaRecorder();
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mediaRecorder.setOutputFile(fileName);
            mediaRecorder.prepare();
            mediaRecorder.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void recordStop() {
        if (mediaRecorder != null) {
            mediaRecorder.stop();
        }
    }



    public void releaseRecorder() {
        if (mediaRecorder != null) {
            mediaRecorder.release();
            mediaRecorder = null;
        }
    }
}
