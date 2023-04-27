package com.example.puzzlebites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.puzzlebites.data.model.Setting;
import com.example.puzzlebites.data.repository.SettingRepository;

import java.util.ArrayList;
import java.util.Arrays;

public class SettingsActivity extends AppCompatActivity {
    ConstraintLayout myLayout;
    SettingRepository settingRepository;
    Setting setting;
    private ArrayList<Integer> styles = new ArrayList<>(Arrays.asList(R.style.Theme_PuzzleBites_theme1,
            R.style.Theme_PuzzleBites_theme2,R.style.Theme_PuzzleBites_theme3,R.style.Theme_PuzzleBites_theme4));
    private int cycle = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        applySettings();
        super.onCreate(savedInstanceState);
        settingRepository = new SettingRepository(this);
        setTheme(settingRepository.getSettings().style);
        setContentView(R.layout.activity_setting);
        setting = settingRepository.getSettings();
        myLayout = (ConstraintLayout) findViewById(R.id.activitySetting);
    }
    public void applySettings(){
        Setting.applySettingToView(this);
    }

    public void returnHome(View v)
    {
        Intent mainIntent = new Intent(this, MainActivity.class);
        saveSharedPreferences();
        mainIntent.putExtra("fromSettings", "fromSettings");
        setResult(Activity.RESULT_OK, mainIntent);
        finish();
    }

    public void changeTheme(View v)
    {
        cycle = styles.indexOf(setting.style);
        cycle += 1;
        if(cycle >= styles.size()){
            cycle = 0;
        }
        setting.style = styles.get(cycle% styles.size());
        saveSharedPreferences();
        this.recreate();
    }

    public void toggleSound(View v)
    {
        boolean sound = setting.soundToggle;
        if(sound)
            setting.soundToggle = false;
        else
            setting.soundToggle = true;
        Log.d("SoundSet", String.valueOf(setting.soundToggle));
    }

    public void resetScore(View v) {
        // add a fragment to allow users the option to select proceed or cancel
        settingRepository.resetScore(setting);
        settingRepository.saveSettings(setting);
    }
//    public void darkModeToggleBTN(View v){
//        //Dark mode is toggled by the settings of the android device itself, or it's settings toward our application.
//        //Similar to turning on energy saver mode.
//    }

    @Override
    public void onStop()
    {
        saveSharedPreferences();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent mainIntent = new Intent(this, MainActivity.class);
        setResult(RESULT_OK, mainIntent);
    }

    public void saveSharedPreferences()
    {
        settingRepository.saveSettings(setting);
    }

}
