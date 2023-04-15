package com.example.puzzlebites;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PuzzleActivity extends AppCompatActivity {
    private scoreModel score;
    public List<Piece> pieces = new ArrayList<Piece>();
    ConstraintLayout myLayout;
    Puzzles puzzles = new Puzzles(this);
    private String puzzle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.puzzle = getIntent().getExtras().getString("puzzle");
        setContentView(R.layout.activity_puzzle);
        score = new ViewModelProvider(this).get(scoreModel.class);
        score.getNumOfMovesString().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                TextView moves = findViewById(R.id.puzzleMovesTV);
                moves.setText(s);
            }
        });
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
        myLayout = (ConstraintLayout) findViewById(R.id.puzzleActivity);
        setPuzzle(puzzle);

    }

    private ActivityResultLauncher<Intent> sStartLauncher;
    private void setPuzzle(String puzzle){
        if(!this.pieces.isEmpty()){
            for(Piece p: pieces) {
                myLayout.removeViewInLayout(p);
            }
            this.pieces = new ArrayList<>();
        }
        this.pieces = puzzles.getPuzzle(puzzle);
        for (Piece p : pieces) {
            myLayout.addView(p);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setPuzzle(this.puzzle);
    }

    public void skipPuzzle(View v) {
        Global.moveCount = 0;
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

    public void moveUp(View v) {
        score.addNumOfMove();
        for (Piece p : pieces) {
            p.moveUp();
        }
        TextView puzzleMoves = findViewById(R.id.puzzleMovesTV);
        Global.moveCount += 1;
        puzzleMoves.setText("Move Count: " + Global.moveCount);
    }

    public void moveDown(View v) {

        score.addNumOfMove();
        for (Piece p : pieces) {
            p.moveDown();
        }
        TextView puzzleMoves = findViewById(R.id.puzzleMovesTV);
        Global.moveCount += 1;
        puzzleMoves.setText("Move Count: " + Global.moveCount);

    }

    public void moveRight(View v) {
        for (Piece p : pieces) {
            p.moveRight();
        }
        TextView puzzleMoves = findViewById(R.id.puzzleMovesTV);
        Global.moveCount += 1;
        puzzleMoves.setText("Move Count: " + Global.moveCount);
    }

    public void moveLeft(View v) {
        for (Piece p : pieces) {
            p.moveLeft();
        }
        TextView puzzleMoves = findViewById(R.id.puzzleMovesTV);
        Global.moveCount += 1;
        puzzleMoves.setText("Move Count: " + Global.moveCount);
    }

    public void undoBTN(View v){
        TextView puzzleMoves = findViewById(R.id.puzzleMovesTV);
        Global.moveCount -= 1;
        puzzleMoves.setText("Move Count: " + Global.moveCount);
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