package com.example.puzzlebites;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class scoreModel extends ViewModel {
    long timeStart;
    long timeEnd;
    float timeFl=0;
    private LiveData<Integer> numOfMoves = new MutableLiveData<>(0);
    private LiveData<String> numOfMovesString = Transformations.map(numOfMoves, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
            String strInput = "Total entries "+ String.valueOf(input);
            return strInput;
        }
    });
    public LiveData<String> getNumOfMovesString() {
        return numOfMovesString;
    }
    public void startClock() {
        long timeStart = System.nanoTime();
    }
    public void endClock() {
        long timeEnd = System.nanoTime();
    }
    public float calcClock() {
        return (float) timeEnd-timeStart;
    }
    public void addNumOfMove() { //this will allow the numOfMove counter to increment for each move
        //and should be called after each move inside the puzzle and MainPageActivities
    }
}
