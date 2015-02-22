package edu.msu.liyimin2.project1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

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

    private int activePlayer = 1;

    //The bird in the has been in the gameview
    private ArrayList<Bird> birds = new ArrayList<Bird>();


    //The scale for the custome view
    final static float SCALE_IN_VIEW = 0.9f;
    //The size of custome view in pixel
    private int birdViewSize;
    //Left margin in pixels
    private int marginX;
    //Top margin in pixels
    private int marginY;
    //Paint for filling the area the puzzle is in.
    private Paint fillPaint;

    public Game(){
    }

    public Game(Context context){
    }

    private synchronized void writeObject(java.io.ObjectOutputStream stream) throws java.
            io.IOException {
        stream.defaultWriteObject();
        stream.writeObject(player1);
        stream.writeObject(player2);
        //stream.write(activePlayer);
    }

    private synchronized void readObject(java.io.ObjectInputStream stream) throws java.
            io.IOException, ClassNotFoundException {
        stream.defaultReadObject();
        player1 = (Player) stream.readObject();
        player2 = (Player) stream.readObject();
        //activePlayer = stream.readInt();
    }

    public Player getPlayer1(){
        return player1;
    }

    public Player getPlayer2(){
        return player2;
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
        /*int wid = canvas.getWidth();
        int hit = canvas.getHeight();

        fillPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        fillPaint.setColor(0xffcccccc);

        //Determine the minimum of the two dimensions
        int minDim = wid < hit ? wid : hit;

        birdViewSize = (int)(minDim * SCALE_IN_VIEW);
        //Compute the margins so we center the puzzle
        marginX = (wid - birdViewSize) / 2;
        marginY = (hit - birdViewSize) / 2;
        // Draw the outline of the puzzle
        canvas.drawRect(marginX, marginY, marginX + birdViewSize, marginY +birdViewSize, fillPaint);*/

        //canvas.save();
        //canvas.translate(marginX, marginY);
        //canvas.scale(scaleFactor, scaleFactor);
        for(Bird b : birds){
            b.draw(canvas);
        }
    }
}
