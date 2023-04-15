package com.example.puzzlebites;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.app.Instrumentation;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Shader;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

                        }
                    }
                });

    }

    @Override
    public void onResume()
    {
        Log.d("SPColor", backgroundColor);
        super.onResume();

        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        View view = inflater.inflate(R.layout.activity_main, null);
        setContentView(view);
        if(backgroundColor.equals("Default"))
        {
            view.setBackgroundColor(Color.rgb(255,255,255));
        }
        else
        {
            view.setBackgroundColor(Color.rgb(0,255,255));
        }
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
        Intent settings = new Intent(this, setting.class);
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