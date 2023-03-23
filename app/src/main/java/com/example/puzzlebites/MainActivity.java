package com.example.puzzlebites;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStartLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == Activity.RESULT_OK)
                        {

                        }
                    }
                });

        try {
            FileInputStream fin = openFileInput(filename);
            int x;
            String temp = "";
            while ((x = fin.read()) != -1) {
                temp = temp + Character.toString((char) x);
            }
            fin.close();
            Log.d("fileRead", "File Read " + temp);
        }catch(Exception e)
            {
                Log.d("fileRead", "File not read " + e);
            }



    }

    private ActivityResultLauncher<Intent> mStartLauncher;
    private String filename = "myFile";
    private String fileContent = "HelloWorld";

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
    protected void onStop()
    {
        super.onStop();

        try {
            FileOutputStream fos = openFileOutput(filename, Context.MODE_PRIVATE);
            fos.write(fileContent.getBytes());
            fos.close();
            Log.d("fileRead", "file wrote " + fileContent);
        }catch(Exception e)
        {
            Log.d("fileRead", "file not wrote" + e);
            return;
        }

    }



}