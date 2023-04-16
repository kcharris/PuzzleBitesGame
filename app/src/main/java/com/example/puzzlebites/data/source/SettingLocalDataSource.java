package com.example.puzzlebites.data.source;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.prefs.Preferences;

public class SettingLocalDataSource {
    private SharedPreferences sp;

    public SettingLocalDataSource(Context context){
        sp = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
    }
    public SharedPreferences getSharedPreferences(){
        return sp;
    }
}
