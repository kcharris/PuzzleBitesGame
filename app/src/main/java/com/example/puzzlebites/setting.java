package com.example.puzzlebites;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.ColorSpace;
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
    /*
    Braeden - One of my tasks is to implement a reset button that's on the settings page. I think that
    it would be wise to have a ModelClass that holds the scores for the instance of the activity running at the time
    this would mean that settings should call a reset method from the implied Model
    this would be the equivalent to a Global Variable but this shows understanding of class material
    */
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

    public void resetScore() {
        //model.reset(); this is just an example of my idea, awaiting implementation
        //Luke might want to do this but I can if anyone or Luke would rather
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
