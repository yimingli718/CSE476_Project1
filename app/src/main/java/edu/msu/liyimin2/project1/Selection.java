package edu.msu.liyimin2.project1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

/**
 * Created by EMing on 2/19/15.
 */
public class Selection {
    //The scale for the custome view
    final static float SCALE_IN_VIEW = 0.9f;
    //The size of custome view in pixel
    private int birdViewSize;
    //Left margin in pixels
    private int marginX;
    //Top margin in pixels
    private int marginY;
    //How much we scale the puzzle pieces
    private float scaleFactor;

    private int birdSize;

    public ArrayList<Bird> BirdsCollection = new ArrayList<Bird>();

    //Paint for filling the area the puzzle is in.
    private Paint fillPaint;

    private int birdIndex;

    public Selection(Context context){
        fillPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        fillPaint.setColor(0xffcccccc);


        BirdsCollection.add(new Bird(context, R.drawable.donald));
        BirdsCollection.add(new Bird(context, R.drawable.ostrich));
        BirdsCollection.add(new Bird(context, R.drawable.parrot));
        BirdsCollection.add(new Bird(context, R.drawable.seagull));
        BirdsCollection.add(new Bird(context, R.drawable.swallow));
    }

    public void draw(Canvas canvas){
        int wid = canvas.getWidth();
        int hit = canvas.getHeight();

        //Determine the minimum of the two dimensions
        int minDim = wid < hit ? wid : hit;

        birdViewSize = (int)(minDim * SCALE_IN_VIEW);
        //Compute the margins so we center the puzzle
        marginX = (wid - birdViewSize) / 2;
        marginY = (hit - birdViewSize) / 2;
        //
        // Draw the outline of the puzzle
        //

        canvas.drawRect(marginX, marginY, marginX + birdViewSize, marginY +birdViewSize, fillPaint);

        //canvas.save();
        //canvas.translate(marginX, marginY);
        //canvas.scale(scaleFactor, scaleFactor);

        Bird bird = BirdsCollection.get(birdIndex);
        bird.draw(canvas);
    }

    public void setBirdIndex(int index){
        birdIndex = index;
    }

    public Bird getBird(){
        return BirdsCollection.get(birdIndex);
    }
}
