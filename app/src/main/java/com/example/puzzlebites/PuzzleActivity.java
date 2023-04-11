package com.example.puzzlebites;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class PuzzleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle);

        sStartLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == Activity.RESULT_OK)
                        {

                        }
                    }
                });
    }
    public class pieces{
        boolean canMove;
        int x;
        int y;
        String type;
        public pieces(String typeStrc, boolean canMoveBool) {
            canMove = canMoveBool;
            x = 0;
            y = 0;
            type = "typeSTR";
        }
    }

    private ActivityResultLauncher<Intent> sStartLauncher;

    public void skipPuzzle(View v) {
        Intent puzzle = new Intent(this,scoreScreen.class);
        puzzle.putExtra("puzzle",0);
        setResult(Activity.RESULT_OK, puzzle);
        sStartLauncher.launch(puzzle);
        finish();
    }
    public void returnMain(View v)
    {
        Intent mainIntent = new Intent(this, MainPageActivity.class);
        mainIntent.putExtra("Return", 0);
        setResult(Activity.RESULT_OK, mainIntent);
        finish();
    }
    public int getPXFromDP(double dp){
        DisplayMetrics dm = getResources().getDisplayMetrics();
        return (int)(dp * dm.density);
    }
    public void moveUp(View v){
        ImageView bagelIV = findViewById(R.id.bagelIV);
        ViewGroup.MarginLayoutParams vlp = (ViewGroup.MarginLayoutParams) bagelIV.getLayoutParams();
        int margin1 = vlp.topMargin;
        int margin2 = vlp.leftMargin;

        if(vlp.topMargin != 0){
            setMargins(bagelIV, margin2, margin1- getPXFromDP(40), 0, 0);
        }

    }

    public void moveDown(View v){
        ImageView bagelIV = findViewById(R.id.bagelIV);
        ViewGroup.MarginLayoutParams vlp = (ViewGroup.MarginLayoutParams) bagelIV.getLayoutParams();

        int margin1 = vlp.topMargin;
        int margin2 = vlp.leftMargin;

        if(vlp.topMargin != getPXFromDP(40*8)){
            setMargins(bagelIV, margin2, margin1+ getPXFromDP(40), 0, 0);
        }
    }

    public void moveRight(View v){
        ImageView bagelIV = findViewById(R.id.bagelIV);
        ViewGroup.MarginLayoutParams vlp = (ViewGroup.MarginLayoutParams) bagelIV.getLayoutParams();

        int margin1 = vlp.topMargin;
        int margin2 = vlp.leftMargin;

        if(vlp.leftMargin != getPXFromDP(40*8)){
            setMargins(bagelIV, margin2+ getPXFromDP(40), margin1, 0, 0);
        }
    }

    public void moveLeft(View v){
        ImageView bagelIV = findViewById(R.id.bagelIV);
        ViewGroup.MarginLayoutParams vlp = (ViewGroup.MarginLayoutParams) bagelIV.getLayoutParams();

        int margin1 = vlp.topMargin;
        int margin2 = vlp.leftMargin;

        if(vlp.leftMargin != 0){
            setMargins(bagelIV, margin2- getPXFromDP(40), margin1, 0, 0);
        }
    }

    private void setMargins (View view, int left, int top, int right, int bottom) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            p.setMargins(left, top, right, bottom);
            view.requestLayout();
        }
    }
    /*public void finishPuzzle(View v){
        Intent scoreIntent = new Intent(this, scoreScreen.class);
        startActivity(scoreIntent);
        finish();
    }*/
}