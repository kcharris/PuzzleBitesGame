package com.example.puzzlebites;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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


public class MainActivity extends AppCompatActivity {
    private ConstraintLayout myLayout;
    private String scoreStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myLayout = (ConstraintLayout) findViewById(R.id.mainActivity);
        applySettings();
        restoreSharedPreferences();
        mStartLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == Activity.RESULT_OK)
                        {
                            Intent i = result.getData();
                            if(i.hasExtra("Color"))
                            {
                                backgroundColor = i.getExtras().getString("Color");
                            }
                            if(i.hasExtra("score")) {
                                scoreStr = i.getExtras().getString("score"); //I'm stopping here because It's unlikely user would return to settings page everytime after a
                            }                                                     //puzzle is completed in order to save data, need to save data instantly and carry it to trophyPage

                        }
                    }
                });
    }
    public void applySettings(){
        Setting.applySettingToView(myLayout);
    }

    @Override
    public void onResume()
    {
//        Log.d("SPColor", backgroundColor);
        super.onResume();
//
//        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
//        View view = inflater.inflate(R.layout.activity_main, null);
//        setContentView(view);
//        if(backgroundColor.equals("Default"))
//        {
//            view.setBackgroundColor(Color.rgb(255,255,255));
//        }
//        else
//        {
//            view.setBackgroundColor(Color.rgb(0,255,255));
//        }
        applySettings();
    }


    private ActivityResultLauncher<Intent> mStartLauncher;
    private String backgroundColor;
    private int highScore;


    public void startPuzzleSelection(View v)
    {
        Intent lvlSelect = new Intent(this, MainPageActivity.class);
        mStartLauncher.launch(lvlSelect);
    }




    public void settings(View v)
    {
        Intent settings = new Intent(this, SettingsActivity.class);
        mStartLauncher.launch(settings);
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
        backgroundColor = sp.getString("Color", "Default");
        highScore = sp.getInt("HighScore", 0);

    }

    public void saveSharedPreferences()
    {
        SharedPreferences sp = getSharedPreferences("sharedPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("Color", backgroundColor);
        edit.putInt("HighScore", highScore);

        edit.commit();
    }


}