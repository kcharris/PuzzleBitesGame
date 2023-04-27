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
        SharedPreferences.Editor edit = shared.edit();
        edit.putInt("level1Score", setting.level1Score);
        edit.putInt("level2Score", setting.level2Score);
        edit.putInt("level3Score", setting.level3Score);
        edit.putInt("level4Score", setting.level4Score);
        edit.putInt("level5Score", setting.level5Score);

        edit.putInt("level1Star", setting.level1Star);
        edit.putInt("level2Star", setting.level2Star);
        edit.putInt("level3Star", setting.level3Star);
        edit.putInt("level4Star", setting.level4Star);
        edit.putInt("level5Star", setting.level5Star);
        // total score is not a saved property of Setting
        edit.putInt("totalScore", setting.getTotalScore());

        edit.putInt("backgroundColor", setting.style);
        edit.commit();
    }

    public Setting getSettings(){
        final Map<String, ?> settingMap = shared.getAll();
        Setting setting = new Setting();
        try {
            setting.level1Score = (int) settingMap.get("level1Score");
            setting.level2Score = (int) settingMap.get("level2Score");
            setting.level3Score = (int) settingMap.get("level3Score");
            setting.level4Score = (int) settingMap.get("level4Score");
            setting.level5Score = (int) settingMap.get("level5Score");

            setting.level1Star = (int) settingMap.get("level1Star");
            setting.level2Star = (int) settingMap.get("level2Star");
            setting.level3Star = (int) settingMap.get("level3Star");
            setting.level4Star = (int) settingMap.get("level4Star");
            setting.level5Star = (int) settingMap.get("level5Star");

            setting.style = (int) settingMap.get("backgroundColor");
        }
        catch (Exception e){
            setting = new Setting();
        }
        if (setting.style == 0){
            setting = new Setting();
        }
        return setting;
    }
    public void resetScore(Setting setting){
        setting.level1Score = 0;
        setting.level2Score = 0;
        setting.level3Score = 0;
        setting.level4Score = 0;
        setting.level5Score = 0;

        setting.level1Star = 0;
        setting.level2Star = 0;
        setting.level3Star = 0;
        setting.level4Star = 0;
        setting.level5Star = 0;
    }


}
