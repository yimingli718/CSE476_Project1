package edu.msu.liyimin2.project1;
import android.graphics.Canvas;

import java.io.Serializable;

public class Player implements Serializable {
    //Player name
    private String name = "player1";
    //Score
    public int score = 0;
    //Bird chosen
    private Bird bird = null;

    public Player(){
    }
    public String getName(){
        return name;
    }
    public void setName(String n){
        name = n;
    }
    public void setBird(Bird b){  bird = b;}
    public Bird getBird() { return bird;}
    public void addPoint() { score++; }

    //public void drawBird(Canvas canvas){
    //    bird.draw(canvas);
    //}
    public void drawBird(Canvas canvas, int marginX, int marginY, int puzzleSize, float scaleFactor) {
        bird.draw(canvas, marginX, marginY, puzzleSize, scaleFactor);
    }

    private synchronized void writeObject(java.io.ObjectOutputStream stream) throws java.
            io.IOException {
        stream.defaultWriteObject();
        stream.writeObject(bird);
    }

    private synchronized void readObject(java.io.ObjectInputStream stream) throws java.
            io.IOException, ClassNotFoundException {
        stream.defaultReadObject();
        bird = (Bird) stream.readObject();
    }
}
