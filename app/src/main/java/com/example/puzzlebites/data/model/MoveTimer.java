package com.example.puzzlebites.data.model;

import android.app.Activity;
import android.content.Context;

import java.util.Timer;
import java.util.TimerTask;

public class MoveTimer {
    Timer timer;

    public MoveTimer(Context c, long seconds){
        MoveTimerInterface activity = (MoveTimerInterface) c;
        timer = new Timer();
        timer.schedule(new BTNTask(activity), seconds);
    }
    public interface MoveTimerInterface{
        void reenableButtons();
    }
    class BTNTask extends TimerTask {
        MoveTimerInterface activity;
        public BTNTask(MoveTimerInterface a){
            activity = a;
        }
        @Override
        public void run() {
            ((Activity) activity).runOnUiThread(new Runnable(){
                @Override
                        public void run(){
                    activity.reenableButtons();
                    timer.cancel();
                }
            });
        }
    }
}
