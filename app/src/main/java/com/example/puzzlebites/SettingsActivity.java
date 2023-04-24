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

import com.example.puzzlebites.data.model.Setting;
import com.example.puzzlebites.data.repository.SettingRepository;

import java.util.ArrayList;
import java.util.Arrays;

public class SettingsActivity extends AppCompatActivity {
    ConstraintLayout myLayout;
    SettingRepository settingRepository;
    Setting setting;
    private ArrayList<Integer> colors = new ArrayList<>(Arrays.asList(Color.BLUE,Color.LTGRAY, Color.CYAN, Color.MAGENTA ));
    private int cycle = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        settingRepository = new SettingRepository(this);
        setting = settingRepository.getSettings();
        myLayout = (ConstraintLayout) findViewById(R.id.activitySetting);
        applySettings();
    }
    public void applySettings(){
        Setting.applySettingToView(myLayout);
    }

    public void returnHome(View v)
    {
        Intent mainIntent = new Intent(this, MainActivity.class);
        saveSharedPreferences();
        setResult(Activity.RESULT_OK, mainIntent);
        finish();
    }

    public void backgroundChange(View v)
    {
        cycle += 1;
        setting.backgroundColor = colors.get(cycle% colors.size());
        myLayout.setBackgroundColor(setting.backgroundColor);
    }

    public void resetScore(View v) {

        //score = 0;
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

    public void saveSharedPreferences()
    {
        settingRepository.saveSettings(setting);
    }

}
