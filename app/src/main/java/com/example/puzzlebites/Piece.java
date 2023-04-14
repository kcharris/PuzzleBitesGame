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
    boolean canMove = false;
    int speed = 0;
    int x = 0;
    int y = 0;
    String type;

    public Piece(Context context, String type, int x, int y) {
        super(context);
        this.type = type;
        this.x = x;
        this.y = y;
        switch (type) {
            case "bagel":
                this.speed = 1;
                this.canMove = true;
                setMargins(this, getPXFromDP(x * 40), getPXFromDP(y * 40), 0, 0);
                this.setImageResource(R.drawable.bagelbitebegin);
                break;
        };
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(0, 0);
        layoutParams.height = getPXFromDP(40);
        layoutParams.width = getPXFromDP(40);
        layoutParams.topToTop = R.id.GridBoard;
        layoutParams.startToStart = R.id.GridBoard;
        this.setLayoutParams(layoutParams);
    }

    private void setMargins(View view, int left, int top, int right, int bottom) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            p.setMargins(left, top, right, bottom);
            view.requestLayout();
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
        ConstraintLayout.LayoutParams clp = (ConstraintLayout.LayoutParams) this.getLayoutParams();
        int margin1 = clp.topMargin;
        int margin2 = clp.leftMargin;
//        score.addNumOfMove();
        if (clp.topMargin != 0) {
            clp.setMargins( margin2, margin1 - getPXFromDP(speed * 40), 0, 0);
        }
        this.setLayoutParams(clp);

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
        if (vlp.topMargin != 0) {
            setMargins(this, margin2, margin1 + getPXFromDP(speed * 40), 0, 0);
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
        if (vlp.topMargin != 0) {
            setMargins(this, margin2 + getPXFromDP(speed * 40), margin1, 0, 0);
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
        if (vlp.topMargin != 0) {
            setMargins(this, margin2 - getPXFromDP(speed * 40), margin1, 0, 0);
        }
    }
}
