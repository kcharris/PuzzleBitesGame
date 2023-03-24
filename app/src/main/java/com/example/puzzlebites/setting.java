package com.example.puzzlebites;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.FileOutputStream;

public class setting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }

    private String backColor = "default";
    private String filename = "savedData";
    private String fileContent = backColor;
    public void returnHome(View v)
    {
        Intent mainIntent = new Intent(this, MainActivity.class);
        mainIntent.putExtra("Return", 0);
        mainIntent.putExtra("Background", backColor);
        setResult(Activity.RESULT_OK, mainIntent);
        finish();
    }

    public void backgroundChange(View v)
    {
        backColor = "blue";
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
