package com.example.puzzlebites.data.repository;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.puzzlebites.data.model.Setting;
import com.example.puzzlebites.data.source.SettingLocalDataSource;

import java.util.Map;

public class SettingRepository {
    private SettingLocalDataSource settingDS;
    private SharedPreferences shared;

    public SettingRepository(Context context){
        settingDS = new SettingLocalDataSource(context);
        shared = settingDS.getSharedPreferences();
    }
    public void getSetting(){
        //shared.getAll();
    }
    public void saveSettings(Setting setting){
        shared.edit().putInt("level1Score", setting.level1Score);
        shared.edit().putInt("level2Score", setting.level2Score);
        shared.edit().putInt("level3Score", setting.level3Score);
        shared.edit().putInt("level4Score", setting.level4Score);
        shared.edit().putInt("level5Score", setting.level5Score);
        // total score is not a saved property of Setting
        shared.edit().putInt("totalScore", setting.getTotalScore());

        shared.edit().putInt("backgroundColor", setting.backgroundColor);
        shared.edit().apply();
    }

    public Setting getSettings(){
        final Map<String, ?> settingMap = shared.getAll();
        Setting setting = new Setting();
        if(settingMap.size() > 0){
            setting.level1Score = (int)settingMap.get("level1Score");
            setting.level2Score = (int)settingMap.get("level2Score");
            setting.level3Score = (int)settingMap.get("level3Score");
            setting.level4Score = (int)settingMap.get("level4Score");
            setting.level5Score = (int)settingMap.get("level5Score");

            setting.backgroundColor = (int)settingMap.get("backgroundColor");
        }

        return setting;
    }


}
