package com.example.puzzlebites;

import static androidx.core.view.ViewGroupKt.setMargins;

import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

//this class needs methods that will determine viable moves or not
//methods can be called per piece to determine what pieces can and cannot move
//move methods (two methods) 1. to check if a move can be made 2. one to actually move it
public class Piece extends androidx.appcompat.widget.AppCompatImageView {
    public boolean canMove = false;
    public boolean isActive = false;
    public int speed = 0;
    public int x = 0;
    public int y = 0;
    public ConstraintLayout.LayoutParams layoutParams;
    String type;

    public Piece(Context context, String type, int x, int y) {
        super(context);
        this.type = type;
        this.x = x;
        this.y = y;
        this.layoutParams = new ConstraintLayout.LayoutParams(40,40);
        this.setLayoutParams(this.layoutParams);
        switch (type) {
            case "bagel":
                this.speed = 1;
                this.canMove = true;
                this.isActive = true;
                this.setImageResource(R.drawable.bagelbitebegin);
                break;
            case "end":
                this.setImageResource(R.drawable.goldstar); // switch to end image for pieces to go to
                this.setBackgroundColor(Color.argb(255, 0, 150, 100));
                break;
            case "cat":
                this.speed = 2;
                this.canMove = true;
                this.setBackgroundColor(Color.argb(255, 100,50,225));
                this.setImageResource(R.drawable.catbegin);
                break;
            case "plate":
                this.speed = 1;
                this.canMove = true;
                this.setBackgroundColor(Color.argb(255, 200,100,0));
                this.setImageResource(R.drawable.platebegin);
                break;
            case "catSwitch":
                this.setImageResource(R.drawable.switchoff);
                this.setBackgroundColor(Color.argb(255, 100,50,225));
                break;
            case "plateSwitch":
                this.setImageResource(R.drawable.switchoff);
                this.setBackgroundColor(Color.argb(255, 200,100,0));
                break;
        }
        setMargins( getPXFromDP(this.x * 40), getPXFromDP(this.y * 40), 0, 0);
        layoutParams.height = getPXFromDP(40);
        layoutParams.width = getPXFromDP(40);
        layoutParams.topToTop = R.id.gridboardIV;
        layoutParams.startToStart = R.id.gridboardIV;
        this.setLayoutParams(layoutParams);
    }

    private void setMargins(int left, int top, int right, int bottom) {
        if (this.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) this.getLayoutParams();
            p.setMargins(left, top, right, bottom);
            this.setLayoutParams(p);
        }
    }

    public int getPXFromDP(double dp) {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        return (int) (dp * dm.density);
    }

    public boolean moveUp() {
            /*Need to create function that determines if player has finished puzzle (by seeing if two IVs
            overlap) and at that point score.endClock() and score.calcClock() will be called.*/
//        if(!score.getHasStarted()) {
//            score.startClock();
//        }
        if(this.isActive) {
            ViewGroup.MarginLayoutParams vlp = (ViewGroup.MarginLayoutParams) this.getLayoutParams();
            int margin1 = vlp.topMargin;
            int margin2 = vlp.leftMargin;
//        score.addNumOfMove();
            Log.d("check", (vlp.topMargin - getPXFromDP(speed * 40)) + " ");
            if (vlp.topMargin - getPXFromDP(speed * 40) >= 0) {
                //this.animate().setDuration(1000).translationYBy(getPXFromDP(-40));
                setMargins(margin2, margin1 - getPXFromDP(speed * 40), 0, 0);
                return true;
            }
        }
        return false;

    }

    public boolean moveDown() {

        /*Need to create function that determines if player has finished puzzle (by seeing if two IVs
            overlap) and at that point score.endClock() and score.calcClock() will be called.*/
//        if(!score.getHasStarted()) {
//            score.startClock();
//        }
        if(this.isActive) {
            ViewGroup.MarginLayoutParams vlp = (ViewGroup.MarginLayoutParams) this.getLayoutParams();
            int margin1 = vlp.topMargin;
            int margin2 = vlp.leftMargin;
//        score.addNumOfMove();
            if (vlp.topMargin + getPXFromDP(speed * 40) <= getPXFromDP(8 * 40)) {
                //this.animate().setDuration(1000).translationYBy(getPXFromDP(40));
                setMargins(margin2, margin1 + getPXFromDP(speed * 40), 0, 0);
                return true;
            }
        }
        return false;
    }

    public boolean moveRight() {

        /*Need to create function that determines if player has finished puzzle (by seeing if two IVs
            overlap) and at that point score.endClock() and score.calcClock() will be called.*/
//        if(!score.getHasStarted()) {
//            score.startClock();
//        }
        if(this.isActive) {
            ViewGroup.MarginLayoutParams vlp = (ViewGroup.MarginLayoutParams) this.getLayoutParams();
            int margin1 = vlp.topMargin;
            int margin2 = vlp.leftMargin;
//        score.addNumOfMove();
            if (vlp.leftMargin + getPXFromDP(speed * 40) <= getPXFromDP(8 * 40)) {
                //this.animate().setDuration(1000).translationXBy(getPXFromDP(40));
                setMargins(margin2 + getPXFromDP(speed * 40), margin1, 0, 0);
                return true;
            }
        }
        return false;
    }

    public boolean moveLeft() {

        /*Need to create function that determines if player has finished puzzle (by seeing if two IVs
            overlap) and at that point score.endClock() and score.calcClock() will be called.*/
//        if(!score.getHasStarted()) {
//            score.startClock();
//        }
        if(this.isActive) {
            ViewGroup.MarginLayoutParams vlp = (ViewGroup.MarginLayoutParams) this.getLayoutParams();
            int margin1 = vlp.topMargin;
            int margin2 = vlp.leftMargin;
//        score.addNumOfMove();
            if (vlp.leftMargin - getPXFromDP(speed * 40) >= 0) {
                //this.animate().setDuration(1000).translationXBy(getPXFromDP(-40));
                setMargins(margin2 - getPXFromDP(speed * 40), margin1, 0, 0);
                return true;
            }
        }
        return false;
    }
}
