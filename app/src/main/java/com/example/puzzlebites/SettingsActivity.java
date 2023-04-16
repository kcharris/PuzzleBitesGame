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
        myLayout = (ConstraintLayout) findViewById(R.id.activitySetting);
        applySettings();
        //restoreSharedPreferences();
    }
    public void applySettings(){
        Setting.applySettingToView(myLayout);
    }

//    @Override
//    public void onResume()
//    {
//        super.onResume();
//
//        LayoutInflater inflater = LayoutInflater.from(SettingsActivity.this);
//        View view = inflater.inflate(R.layout.activity_setting, null);
//        setContentView(view);
//        if(color.equals("Default"))
//        {
//            view.setBackgroundColor(Color.rgb(255,255,255));
//        }
//        else
//        {
//            view.setBackgroundColor(Color.rgb(0,255,255));
//        }
//    }


    private String color = "Default";
    private int score = 0;
    /*
    Braeden - One of my tasks is to implement a reset button that's on the settings page. I think that
    it would be wise to have a ModelClass that holds the scores for the instance of the activity running at the time
    this would mean that settings should call a reset method from the implied Model
    this would be the equivalent to a Global Variable but this shows understanding of class material
    */
    public void returnHome(View v)
    {
        Intent mainIntent = new Intent(this, MainActivity.class);
        saveSharedPreferences();
        setResult(Activity.RESULT_OK, mainIntent);
        mainIntent.putExtra("Color", color);
        Log.d("SPColor", "Return with color " + color);
        finish();
    }

    public void backgroundChange(View v)
    {
        cycle += 1;
        setting.backgroundColor = colors.get(cycle% colors.size());
        myLayout.setBackgroundColor(setting.backgroundColor);
    }

    public void resetScore(View v) {
        score = 0;
    }

    @Override
    public void onStop()
    {
        saveSharedPreferences();
        super.onStop();
    }

    public void restoreSharedPreferences()
    {
        SharedPreferences sp = getSharedPreferences("sharedPref", Context.MODE_PRIVATE);
        color = sp.getString("Color", "Default");
    }


    public void saveSharedPreferences()
    {
        settingRepository.saveSettings(setting);
//        SharedPreferences sp = getSharedPreferences("sharedPref", Context.MODE_PRIVATE);
//        SharedPreferences.Editor edit = sp.edit();
//        edit.putString("Color", color);
//        edit.putInt("HighScore", score);
//        edit.commit();
    }

}
