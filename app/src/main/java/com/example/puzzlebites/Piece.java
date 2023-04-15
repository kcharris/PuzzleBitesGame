package com.example.puzzlebites;

import static androidx.core.view.ViewGroupKt.setMargins;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

//this class needs methods that will determine viable moves or not
//methods can be called per piece to determine what pieces can and cannot move
//move methods (two methods) 1. to check if a move can be made 2. one to actually move it
public class Piece extends androidx.appcompat.widget.AppCompatImageView {
    public boolean canMove = false;
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
                setMargins( getPXFromDP(this.x * 40), getPXFromDP(this.y * 40), 0, 0);
                this.setImageResource(R.drawable.bagelbitebegin);
                break;
        }
        layoutParams.height = getPXFromDP(40);
        layoutParams.width = getPXFromDP(40);
        layoutParams.topToTop = R.id.gridboardIV;
        layoutParams.startToStart = R.id.gridboardIV;
        this.setTag("bagelIV");
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

    public void moveUp() {
            /*Need to create function that determines if player has finished puzzle (by seeing if two IVs
            overlap) and at that point score.endClock() and score.calcClock() will be called.*/
//        if(!score.getHasStarted()) {
//            score.startClock();
//        }
        ViewGroup.MarginLayoutParams vlp = (ViewGroup.MarginLayoutParams) this.getLayoutParams();
        int margin1 = vlp.topMargin;
        int margin2 = vlp.leftMargin;
//        score.addNumOfMove();
        if (vlp.topMargin != 0) {
            //this.animate().setDuration(1000).translationYBy(getPXFromDP(-40));
            setMargins( margin2, margin1 - getPXFromDP(speed * 40), 0, 0);
        }

    }

    public void moveDown() {

        /*Need to create function that determines if player has finished puzzle (by seeing if two IVs
            overlap) and at that point score.endClock() and score.calcClock() will be called.*/
//        if(!score.getHasStarted()) {
//            score.startClock();
//        }
        ViewGroup.MarginLayoutParams vlp = (ViewGroup.MarginLayoutParams) this.getLayoutParams();
        int margin1 = vlp.topMargin;
        int margin2 = vlp.leftMargin;
//        score.addNumOfMove();
        if (vlp.topMargin != getPXFromDP(8*40)) {
            //this.animate().setDuration(1000).translationYBy(getPXFromDP(40));
            setMargins( margin2, margin1 + getPXFromDP(speed * 40), 0, 0);
        }
    }

    public void moveRight() {

        /*Need to create function that determines if player has finished puzzle (by seeing if two IVs
            overlap) and at that point score.endClock() and score.calcClock() will be called.*/
//        if(!score.getHasStarted()) {
//            score.startClock();
//        }
        ViewGroup.MarginLayoutParams vlp = (ViewGroup.MarginLayoutParams) this.getLayoutParams();
        int margin1 = vlp.topMargin;
        int margin2 = vlp.leftMargin;
//        score.addNumOfMove();
        if (vlp.leftMargin != getPXFromDP(8*40)) {
            //this.animate().setDuration(1000).translationXBy(getPXFromDP(40));
            setMargins( margin2 + getPXFromDP(speed * 40), margin1, 0, 0);
        }
    }

    public void moveLeft() {

        /*Need to create function that determines if player has finished puzzle (by seeing if two IVs
            overlap) and at that point score.endClock() and score.calcClock() will be called.*/
//        if(!score.getHasStarted()) {
//            score.startClock();
//        }
        ViewGroup.MarginLayoutParams vlp = (ViewGroup.MarginLayoutParams) this.getLayoutParams();
        int margin1 = vlp.topMargin;
        int margin2 = vlp.leftMargin;
//        score.addNumOfMove();
        if (vlp.leftMargin != 0) {
            //this.animate().setDuration(1000).translationXBy(getPXFromDP(-40));
            setMargins(margin2 - getPXFromDP(speed * 40), margin1, 0, 0);
        }
    }
}
