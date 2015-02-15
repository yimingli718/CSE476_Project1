package edu.msu.liyimin2.project1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Justin on 2/15/2015.
 */
public class Game {
    /**
     * Percentage of the display width or height that
     * is occupied by the puzzle.
     */
    final static float SCALE_IN_VIEW = 0.9f;
    /**
     * Paint for filling the area the puzzle is in
     */
    private Paint fillPaint;

    /**
     * Paint for outlining the area the puzzle is in
     */
    private Paint outlinePaint;
    /**
     * Player 1
     */
    public Player player1;

    /**
     * Player 2
     */
    public Player player2;

    public Game(){
        player1 = new Player();
        player2 = new Player();

    }

    public Game(Context context){
        player1 = new Player();
        player2 = new Player();
        fillPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        fillPaint.setColor(0xffcccccc);
    }

    public void draw(Canvas canvas){
        int wid = canvas.getWidth();
        int hit = canvas.getHeight();
        int minDim = wid < hit ? wid : hit;
        int puzzleSize = (int)(minDim * SCALE_IN_VIEW);
        //Computer the margins
        int marginX = (wid - puzzleSize) / 2;
        int marginY = (hit - puzzleSize) / 2;

        canvas.drawRect(marginX, marginY, marginX + puzzleSize, marginY + puzzleSize, fillPaint);
    }
}
