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
import android.view.View;
import android.widget.TextView;

import com.example.puzzlebites.data.model.Piece;
import com.example.puzzlebites.data.model.Puzzle;
import com.example.puzzlebites.data.model.Puzzles;
import com.example.puzzlebites.data.model.Score;
import com.example.puzzlebites.data.model.Setting;
import com.example.puzzlebites.data.repository.SettingRepository;

import java.util.ArrayList;
import java.util.HashSet;

public class PuzzleActivity extends AppCompatActivity {
    private Score score;
    public Puzzle puzzle;
    Puzzles puzzles = new Puzzles(this);
    private HashSet<String> endLocations;
    private String puzzleNum;
    private ConstraintLayout myLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.puzzleNum = getIntent().getExtras().getString("puzzleNum");
        setContentView(R.layout.activity_puzzle);
        myLayout = (ConstraintLayout) findViewById(R.id.puzzleActivity);
        score = new ViewModelProvider(this).get(Score.class);
        applySettings();
        score.getNumOfMovesString().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                TextView movesTV = findViewById(R.id.puzzleMovesTV);
                movesTV.setText(s);
            }
        });
        sStartLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == Activity.RESULT_OK)
                        {
                            if(result.getData().hasExtra("returned")){
                                setPuzzle(puzzleNum);
                            }
                            else{
                                returnMain();
                            }
                        }
                    }
                });
        setPuzzle(puzzleNum);
    }
    public void applySettings(){
        Setting.applySettingToView(myLayout);
    }
    private ActivityResultLauncher<Intent> sStartLauncher;
    private void setPuzzle(String puzzleNum) {
        if(!(this.puzzle == null)){
            for(Piece p: puzzle.getAllPieces()) {
                myLayout.removeViewInLayout(p);
            }
        }
        puzzle = puzzles.getPuzzle(puzzleNum);
        for (Piece p : puzzle.getAllPieces()) {
            myLayout.addView(p);
        }
        score.reset();
    }
    @Override
    protected void onResume() {
        super.onResume();
        setPuzzle(this.puzzleNum);
    }
    public void returnMain(View v)
    {
        returnMain();
    }
    public void returnMain(){
        Intent mainIntent = new Intent(this, MainPageActivity.class);
        setResult(Activity.RESULT_OK, mainIntent);
        finish();
    }
    public void moveUp(View v) {
        if(puzzle.moveGeneral("up")){
            score.incrementNumOfMove();
        }
        if(puzzle.isWinState()){
            finishPuzzle();
        }
    }

    public void moveDown(View v) {
        if(puzzle.moveGeneral("down")){
            score.incrementNumOfMove();
        }
        if(puzzle.isWinState()){
            finishPuzzle();
        }
    }

    public void moveRight(View v) {
        if(puzzle.moveGeneral("right")){
            score.incrementNumOfMove();
        }
        if(puzzle.isWinState()){
            finishPuzzle();
        }
    }

    public void moveLeft(View v) {
        if(puzzle.moveGeneral("left")){
            score.incrementNumOfMove();
        }
        if(puzzle.isWinState()){
            finishPuzzle();
        }
    }

    public void undoBTN(View v){
        if(puzzle.undoMove()){
            score.decrementNumOfMoves();
        }
    }
    public void finishPuzzle(){
        Intent scoreIntent = new Intent(this, ScoreScreenActivity.class);
        scoreIntent.putExtra("score", score.getNumOfMoves());

        scoreIntent.putExtra("puzzleNum",puzzleNum);
        scoreIntent.putExtra("bronze", puzzle.bronzeThres);
        scoreIntent.putExtra("silver", puzzle.silverThres);
        scoreIntent.putExtra("gold", puzzle.goldThres);
        sStartLauncher.launch(scoreIntent);
    }
    public void resetPuzzle(View v){
        setPuzzle(puzzleNum);
    }

}