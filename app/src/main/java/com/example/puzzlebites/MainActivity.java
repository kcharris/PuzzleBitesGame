package com.example.puzzlebites;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityResultLauncher<Intent> trophyPageLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult trophyPageExit) {
                        /*My plan is to use one ActivityResult for the exit button and
                            the back space (returnBTN) button
                          "Return" will be the returnBTN, else will be the exitBTN
                        */
                        int resultTrophy = trophyPageExit.getResultCode();
                        Intent trophyData = trophyPageExit.getData();
                        if(trophyData.getBooleanExtra("Return", false));
                        /*int result = activityResultFence.getResultCode();
                        Intent data = activityResultFence.getData();
                        assert data != null;
                        Global.area = data.getIntExtra("areaStr",0);
                        TextView outputTV = findViewById(R.id.outputTV);
                        float cost = (Global.area/(float)coverage*Global.costPerLiter);
                        litersUsed = Global.area/(float)coverage*(float)3.8;
                        int rounded = round(cost);
                        //find 10 percent below and above
                        int belowInt = (int) ((int) rounded*.90);
                        int aboveInt = (int) ((int) rounded*1.10);
                        outputTV.setText("Using " + String.format("%.2f",litersUsed) + " cans of paint, the cost will be anywhere from $" + belowInt
                                + " to $" + aboveInt);*/
                    }
                }
        );
    }
}