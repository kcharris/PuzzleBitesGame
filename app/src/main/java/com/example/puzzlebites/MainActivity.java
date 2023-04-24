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
    private ActivityResultLauncher<Intent> mStartLauncher;
    private SettingRepository settingRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        applySettings();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myLayout = (ConstraintLayout) findViewById(R.id.mainActivity);

        mStartLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == Activity.RESULT_OK)
                        {
                            if(result.getData().hasExtra("fromSettings")){
                                restartHelper();
                            }
                        }
                    }
                });
    }
    public void applySettings(){
        Setting.applySettingToView(this);
    }
    public void restartHelper(){
        this.recreate();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        applySettings();
    }

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
}