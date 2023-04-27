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
import android.widget.Button;
import android.widget.TextView;

import com.example.puzzlebites.data.model.MoveTimer;
import com.example.puzzlebites.data.model.Piece;
import com.example.puzzlebites.data.model.PieceType;
import com.example.puzzlebites.data.model.Puzzle;
import com.example.puzzlebites.data.model.Puzzles;
import com.example.puzzlebites.data.model.Score;
import com.example.puzzlebites.data.model.Setting;
import com.example.puzzlebites.data.repository.SettingRepository;

import java.util.HashSet;

public class PuzzleActivity extends AppCompatActivity implements MoveTimer.MoveTimerInterface {
    private Score score;
    public Puzzle puzzle;
    Puzzles puzzles = new Puzzles(this);
    private HashSet<String> endLocations;
    private PieceType puzzleEnum;
    private ConstraintLayout myLayout;
    private SettingRepository settingRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        applySettings();
        super.onCreate(savedInstanceState);
        this.puzzleEnum = PieceType.valueOf(getIntent().getExtras().getString("puzzleEnum"));
        setContentView(R.layout.activity_puzzle);
        myLayout = (ConstraintLayout) findViewById(R.id.puzzleActivity);
        score = new ViewModelProvider(this).get(Score.class);
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
                                setPuzzle(puzzleEnum);
                            }
                            else{
                                returnMain();
                            }
                        }
                    }
                });
        setPuzzle(puzzleEnum);
    }
    public void applySettings(){
        Setting.applySettingToView(this);
    }
    private ActivityResultLauncher<Intent> sStartLauncher;
    private void setPuzzle(PieceType puzzleEnum) {
        TextView levelNameTV = findViewById(R.id.puzzleLevelTV);
        levelNameTV.setText(String.valueOf(puzzleEnum));
        if(!(this.puzzle == null)){
            for(Piece p: puzzle.getAllPieces()) {
                myLayout.removeViewInLayout(p);
            }
        }
        puzzle = puzzles.getPuzzle(puzzleEnum);
        for (Piece p : puzzle.getAllPieces()) {
            myLayout.addView(p);
        }
        score.reset();
    }
    public void disableThenReenableButtons(){
        Button upBTN = findViewById(R.id.puzzleUpBTN);
        Button rightBTN = findViewById(R.id.puzzleRightBTN);
        Button downBTN = findViewById(R.id.puzzleDownBTN);
        Button leftBTN = findViewById(R.id.puzzleLeftBTN);

        upBTN.setEnabled(false);
        rightBTN.setEnabled(false);
        downBTN.setEnabled(false);
        leftBTN.setEnabled(false);

        settingRepository = new SettingRepository(this);
        new MoveTimer(this, settingRepository.getSettings().animationMilliseconds);
    }
    public void reenableButtons(){
        Button upBTN = findViewById(R.id.puzzleUpBTN);
        Button rightBTN = findViewById(R.id.puzzleRightBTN);
        Button downBTN = findViewById(R.id.puzzleDownBTN);
        Button leftBTN = findViewById(R.id.puzzleLeftBTN);

        upBTN.setEnabled(true);
        rightBTN.setEnabled(true);
        downBTN.setEnabled(true);
        leftBTN.setEnabled(true);

        if(puzzle.isWinState()){
            finishPuzzle();
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        setPuzzle(this.puzzleEnum);
    }
    public void returnBTN(View v)
    {
        returnMain();
    }
    public void returnMain(){
        Intent mainIntent = new Intent(this, MainPageActivity.class);
        setResult(Activity.RESULT_OK, mainIntent);
        finish();
    }
    public void moveUp(View v) {
        if(puzzle.moveGeneral("up", PuzzleActivity.this)){
            score.incrementNumOfMove();
        }
        disableThenReenableButtons();
    }

    public void moveDown(View v) {
        if(puzzle.moveGeneral("down", PuzzleActivity.this)){
            score.incrementNumOfMove();
        }

        disableThenReenableButtons();
    }

    public void moveRight(View v) {
        if(puzzle.moveGeneral("right", PuzzleActivity.this)){
            score.incrementNumOfMove();
        }

        disableThenReenableButtons();
    }

    public void moveLeft(View v) {
        if(puzzle.moveGeneral("left", PuzzleActivity.this)){
            score.incrementNumOfMove();
        }

        disableThenReenableButtons();
    }

    public void undoBTN(View v){
        if(puzzle.undoMove()){
            score.decrementNumOfMoves();
        }
        disableThenReenableButtons();
    }
    public void finishPuzzle(){
        Intent scoreIntent = new Intent(this, ScoreScreenActivity.class);
        scoreIntent.putExtra("score", score.getNumOfMoves());
        scoreIntent.putExtra("puzzleEnum",puzzleEnum.toString());
        scoreIntent.putExtra("bronze", puzzle.bronzeThres);
        scoreIntent.putExtra("silver", puzzle.silverThres);
        scoreIntent.putExtra("gold", puzzle.goldThres);
        sStartLauncher.launch(scoreIntent);
    }
    public void resetPuzzle(View v){
        setPuzzle(puzzleEnum);
    }

}