package edu.msu.liyimin2.project1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable {
     //Player 1
    private Player player1 = new Player();
    //Player 2
    private Player player2 = new Player();
    //Active Player
    private int activePlayer = 1;
    //The bird in the has been in the gameview
    private ArrayList<Bird> birds = new ArrayList<Bird>();


    //Game View
    private transient GameView gameView = null;

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

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

    public void draw(Canvas canvas, int marginX, int marginY, int gameViewSize, float scaleFactor) {
        //draw all of the birds
        for(Bird b : birds){
            b.draw(canvas, marginX, marginY, gameViewSize, scaleFactor);
        }
    }

    public void updateBirds(Context context){
        player1.getBird().update(context);
        player2.getBird().update(context);
        for(Bird b: birds){
            b.update(context);
        }
    }

    public boolean checkCollision(Bird activeBird){
        for(Bird b: birds){
            if(activeBird.collisionTest(b)){
                return true;
            }
        }
        return false;

    }
}
