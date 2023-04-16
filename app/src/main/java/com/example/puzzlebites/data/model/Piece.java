package com.example.puzzlebites.data.model;

import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.puzzlebites.R;

//this class needs methods that will determine viable moves or not
//methods can be called per piece to determine what pieces can and cannot move
//move methods (two methods) 1. to check if a move can be made 2. one to actually move it
public class Piece extends androidx.appcompat.widget.AppCompatImageView {
    public boolean canMove = false;
    public boolean isActive = false;
    public int speed = 0;
    public int nextTopMargin = 0;
    public int nextStartMargin = 0;
    public ConstraintLayout.LayoutParams layoutParams;
    public PieceType type;

    public Piece(Context context, PieceType type, int x, int y){
        super(context);
        this.type = type;
        this.layoutParams = new ConstraintLayout.LayoutParams(40,40);
        this.setLayoutParams(this.layoutParams);

        switch (type) {
            case BAGEL:
                this.speed = 1;
                this.canMove = true;
                this.isActive = true;
                this.setImageResource(R.drawable.bagelbitebegin);
                this.setScaleType(ScaleType.CENTER_CROP);
                break;
            case END:
                this.setImageResource(R.drawable.goldstar); // switch to end image for pieces to go to
                this.setBackgroundColor(Color.argb(255, 0, 150, 100));
                break;
            case CAT:
                this.speed = 2;
                this.canMove = true;
                this.setBackgroundColor(Color.argb(255, 100,50,225));
                this.setImageResource(R.drawable.catbegin);
                break;
            case PLATE:
                this.speed = 1;
                this.canMove = true;
                this.setBackgroundColor(Color.argb(255, 200,100,0));
                this.setImageResource(R.drawable.platebegin);
                break;
            case CAT_SWITCH:
                this.setImageResource(R.drawable.switchoff);
                this.setBackgroundColor(Color.argb(255, 100,50,225));
                break;
            case PLATE_SWITCH:
                this.setImageResource(R.drawable.switchoff);
                this.setBackgroundColor(Color.argb(255, 200,100,0));
                break;
            case LEVEL1:
                this.setImageResource(R.drawable.goldenstar);
                this.setScaleType(ScaleType.CENTER_CROP);
                this.setId(R.id.lvlOne);
                break;
            case LEVEL2:
                this.setImageResource(R.drawable.goldenstar);
                this.setScaleType(ScaleType.CENTER_CROP);
                this.setId(R.id.lvlTwo);
                break;
            case LEVEL3:
                this.setImageResource(R.drawable.goldenstar);
                this.setScaleType(ScaleType.CENTER_CROP);
                this.setId(R.id.lvlThree);
                break;
            case LEVEL4:
                this.setImageResource(R.drawable.goldenstar);
                this.setScaleType(ScaleType.CENTER_CROP);
                this.setId(R.id.lvlFour);
                break;
            case LEVEL5:
                this.setImageResource(R.drawable.goldenstar);
                this.setScaleType(ScaleType.CENTER_CROP);
                this.setId(R.id.lvlFive);
                break;
            case LEVEL_TROPHY:
                this.setImageResource(R.drawable.goldenstar);
                this.setScaleType(ScaleType.CENTER_CROP);
                this.setId(R.id.lvlTrophy);
                break;
        }
        this.nextStartMargin = getPXFromDP(x * 40);
        this.nextTopMargin = getPXFromDP(y * 40);
        setMargins();
        layoutParams.height = getPXFromDP(40);
        layoutParams.width = getPXFromDP(40);
        layoutParams.topToTop = R.id.gridboardIV;
        layoutParams.startToStart = R.id.gridboardIV;
        this.setLayoutParams(layoutParams);
    }

    public void setMargins() {
        if (this.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) this.getLayoutParams();
            p.setMargins(nextStartMargin, nextTopMargin, 0, 0);
            this.setLayoutParams(p);
        }
    }

    public int getPXFromDP(double dp) {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        return (int) (dp * dm.density);
    }

    public boolean moveUp() {
        ViewGroup.MarginLayoutParams vlp = (ViewGroup.MarginLayoutParams) this.getLayoutParams();
        int topMargin = vlp.topMargin;
        int startMargin = vlp.leftMargin;

        if(this.isActive) {
//        score.addNumOfMove();
            if (vlp.topMargin - getPXFromDP(speed * 40) >= 0) {
                //this.animate().setDuration(1000).translationYBy(getPXFromDP(-40));
                //setMargins(startMargin, topMargin - getPXFromDP(speed * 40));
                nextStartMargin = startMargin;
                nextTopMargin = topMargin - getPXFromDP(speed * 40);
                return true;
            }
            else{
                return false;
            }
        }
        else{
            nextTopMargin = topMargin;
            nextStartMargin = startMargin;
        }
        return true;

    }

    public boolean moveDown() {
        ViewGroup.MarginLayoutParams vlp = (ViewGroup.MarginLayoutParams) this.getLayoutParams();
        int topMargin = vlp.topMargin;
        int startMargin = vlp.leftMargin;
        if(this.isActive) {
//        score.addNumOfMove();
            if (vlp.topMargin + getPXFromDP(speed * 40) <= getPXFromDP(8 * 40)) {
                //this.animate().setDuration(1000).translationYBy(getPXFromDP(40));
                //setMargins(startMargin, topMargin + getPXFromDP(speed * 40));
                nextStartMargin = startMargin;
                nextTopMargin = topMargin + getPXFromDP(speed * 40);;
                return true;
            }
            else{
                return false;
            }
        }
        else{
            nextTopMargin = topMargin;
            nextStartMargin = startMargin;
        }
        return true;
    }

    public boolean moveRight() {
        ViewGroup.MarginLayoutParams vlp = (ViewGroup.MarginLayoutParams) this.getLayoutParams();
        int topMargin = vlp.topMargin;
        int startMargin = vlp.leftMargin;
        if(this.isActive) {

//        score.addNumOfMove();
            if (vlp.leftMargin + getPXFromDP(speed * 40) <= getPXFromDP(8 * 40)) {
                //this.animate().setDuration(1000).translationXBy(getPXFromDP(40));
                //setMargins(startMargin + getPXFromDP(speed * 40), topMargin);
                nextStartMargin = startMargin + getPXFromDP(speed * 40);
                nextTopMargin = topMargin;
                return true;
            }
            else{
                return false;
            }
        }
        else{
            nextTopMargin = topMargin;
            nextStartMargin = startMargin;
        }
        return true;
    }

    public boolean moveLeft() {
        ViewGroup.MarginLayoutParams vlp = (ViewGroup.MarginLayoutParams) this.getLayoutParams();
        int topMargin = vlp.topMargin;
        int startMargin = vlp.leftMargin;
        if(this.isActive) {

//        score.addNumOfMove();
            if (vlp.leftMargin - getPXFromDP(speed * 40) >= 0) {
                //this.animate().setDuration(1000).translationXBy(getPXFromDP(-40));
                //setMargins(startMargin - getPXFromDP(speed * 40), topMargin);
                nextStartMargin = startMargin - getPXFromDP(speed * 40);
                nextTopMargin = topMargin;
                return true;
            }
            else{
                return false;
            }
        }
        else{
            nextTopMargin = topMargin;
            nextStartMargin = startMargin;
        }
        return true;
    }

}