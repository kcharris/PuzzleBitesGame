package com.example.puzzlebites;

import androidx.appcompat.app.AppCompatActivity;

public class scoreModel extends AppCompatActivity {
    long timeStart;
    long timeEnd;
    public class score {
        long timeStart;
        long timeEnd;
        float timeFl=0;
        int numOfMoves=0;
        public void startClock() {
            long timeStart = System.nanoTime();
        }
        public void endClock() {
            long timeEnd = System.nanoTime();
        }
        public float calcClock() {
            return (float) timeEnd-timeStart;
        }
    }
    public void addNumOfMove() { //this will allow the numOfMove counter to increment for each move
        //and should be called after each move inside the puzzle and MainPageActivities
    }
}
