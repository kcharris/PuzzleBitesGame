package com.example.puzzlebites.data.model;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

public class Score extends ViewModel {
   /* long timeStart;
    long timeEnd;
    boolean hasStarted =false;*/
    private MutableLiveData<Integer> numOfMoves = new MutableLiveData<>(0);
    private LiveData<String> numOfMovesString = Transformations.map(numOfMoves, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
            return "Move: # "+ input;
        }
    });
    public int getNumOfMoves(){
        return numOfMoves.getValue();
    }
    public LiveData<String> getNumOfMovesString() {
        return numOfMovesString;
    }
    /*public void startClock() {
        long timeStart = System.nanoTime();
        hasStarted =true;
    }*/
   /* public void endClock() {
        long timeEnd = System.nanoTime();
        hasStarted =false;
    }*/
   /* public float calcClock() {
        return (float) timeEnd-timeStart;
    }
    public boolean getHasStarted() {
        return hasStarted;
    }*/
    public void incrementNumOfMove() {
        numOfMoves.setValue(numOfMoves.getValue()+1);
    }
    public void decrementNumOfMoves(){numOfMoves.setValue(numOfMoves.getValue() -1);}
    public void reset(){numOfMoves.setValue(0);}
}
