package edu.msu.liyimin2.project1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Justin on 2/15/2015.
 */
public class Game implements Serializable {
     //Player 1
    private Player player1 = new Player();
    //Player 2
    private Player player2 = new Player();
    //Active Player
    private int activePlayer = 1;
    //The bird in the has been in the gameview
    private ArrayList<Bird> birds = new ArrayList<Bird>();
    /**
     * This variable is set to a piece we are dragging. If
     * we are not dragging, the variable is null.
     */
    private Bird b = null;


    //The scale for the custome view
    final static float SCALE_IN_VIEW = 0.9f;
    //The size of custome view in pixel
    private int gameViewSize;
    //Left margin in pixels
    private int marginX;
    //Top margin in pixels
    private int marginY;
    //Paint for filling the area the puzzle is in.
    private Paint fillPaint;
    //How much we scale the puzzle pieces
    private float scaleFactor;
    //Most recent relative X touch when dragging
    private float lastRelX;
    //Most recent relative Y touch when dragging
    private float lastRelY;

    public Game(){
    }

    public Game(Context context){
    }

    private synchronized void writeObject(java.io.ObjectOutputStream stream) throws java.
            io.IOException {
        stream.defaultWriteObject();
        stream.writeObject(player1);
        stream.writeObject(player2);
    }

    private synchronized void readObject(java.io.ObjectInputStream stream) throws java.
            io.IOException, ClassNotFoundException {
        stream.defaultReadObject();
        player1 = (Player) stream.readObject();
        player2 = (Player) stream.readObject();
    }

    public Player getPlayer1(){
        return player1;
    }

    public Player getPlayer2(){
        return player2;
    }

    public int getMarginX() {
        return marginX;
    }

    public int getMarginY() {
        return marginY;
    }

    public int getGameViewSize() {
        return gameViewSize;
    }

    public float getScaleFactor() {
        return scaleFactor;
    }

    public Player getActivePlayer(){
      if(activePlayer == 1){
          return player1;
      }
      else{
          return player2;
      }
    }

    public void nextPlayer(){
        if(activePlayer == 1){
            activePlayer = 2;
        }
        else{
            activePlayer = 1;
        }
    }

    public void ArchiveBird(Bird b){
        birds.add(b);
    }

    public void draw(Canvas canvas){
        int wid = canvas.getWidth();
        int hit = canvas.getHeight();

        int minDim = wid < hit ? wid : hit;

        gameViewSize = (int)(minDim * SCALE_IN_VIEW);
        //Compute the margins so we center the puzzle
        marginX = (wid - gameViewSize) / 2;
        marginY = (hit - gameViewSize) / 2;

        scaleFactor = (float)gameViewSize / (float)canvas.getWidth();
        // Draw the outline of the puzzle
        //canvas.drawRect(marginX, marginY, marginX + gameViewSize, marginY +gameViewSize, fillPaint);

        //canvas.save();
        //canvas.translate(marginX, marginY);
        //canvas.scale(scaleFactor, scaleFactor);
        for(Bird b : birds){
            b.draw(canvas, marginX, marginY, gameViewSize, scaleFactor);
        }
    }

    private boolean onTouched(float x, float y) {

        // Check each piece to see if it has been hit
        // We do this in reverse order so we find the pieces in front
        /*for(int p=birds.size()-1; p>=0;  p--) {
            if(birds.get(p).hit(x, y, gameViewSize, scaleFactor)) {
                // We hit a piece!
                b = birds.get(p);
                lastRelX = x;
                lastRelY = y;
                return true;
            }
        }*/
        /*if(getActivePlayer().getBird().hit(x,y,gameViewSize, scaleFactor)){
            b = getActivePlayer().getBird();
            lastRelX = x;
            lastRelY = y;
            return true;
        }

        return false;*/
        if(getActivePlayer().getBird() == null){
            return false;
        }
        else{
            b = getActivePlayer().getBird();
            lastRelX = x;
            lastRelY = y;
            return true;
        }
    }


    /**
     * Handle a touch event from the view.
     * @param view The view that is the source of the touch
     * @param event The motion event describing the touch
     * @return true if the touch is handled.
     */
    public boolean onTouchEvent(View view, MotionEvent event) {

        float relX = (event.getX() - marginX) / gameViewSize;
        float relY = (event.getY() - marginY) / gameViewSize;

        switch(event.getActionMasked()) {

            case MotionEvent.ACTION_DOWN:
                Log.i("onTouchEvent", "ACTION_DOWN");
                return onTouched(relX, relY);


            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                Log.i("onTouchEvent", "ACTION_UP");
                break;

            case MotionEvent.ACTION_MOVE:
                Log.i("onTouchEvent",  "ACTION_MOVE: " + event.getX() + "," + event.getY());
                // If we are dragging, move the piece and force a redraw
                if(b != null) {
                    //getActivePlayer().getBird().move(relX - lastRelX, relY - lastRelY);
                    b.move(relX - lastRelX, relY - lastRelY, marginX, marginY, gameViewSize);
                    lastRelX = relX;
                    lastRelY = relY;
                    view.invalidate();
                    return true;
                }
                break;
        }

        return false;
    }
}
