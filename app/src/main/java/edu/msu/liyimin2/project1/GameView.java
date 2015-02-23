package edu.msu.liyimin2.project1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


/**
 * TODO: document your custom view class.
 */
public class GameView extends View {
    //region Member Variables
    /**
     * The actual game
     */
    private Game game = new Game();
    //The scale for the custom view
    final private static float SCALE_IN_VIEW = 0.9f;
    //The size of custom view in pixel
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

    /**
     * This variable is set to a piece we are dragging. If
     * we are not dragging, the variable is null.
     */
    private Bird b = null;

    //endregion

    //region Constructors

    public GameView(Context context) {
        super(context);
        init(null, 0);
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public GameView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    //endregion

    private void init(AttributeSet attrs, int defStyle)
    {
        game = new Game();
        fillPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        fillPaint.setColor(0xffcccccc);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float relX = (event.getX() - getMarginX()) / getGameViewSize();
        float relY = (event.getY() - getMarginY()) / getGameViewSize();

        switch(event.getActionMasked()) {

            case MotionEvent.ACTION_DOWN:
                //Log.i("onTouchEvent", "ACTION_DOWN");
                return onTouched(relX, relY);


            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:

                //Log.i("onTouchEvent", "ACTION_UP");
                break;

            case MotionEvent.ACTION_MOVE:
                //Log.i("onTouchEvent",  "ACTION_MOVE: " + event.getX() + "," + event.getY());
                // If we are dragging, move the piece and force a redraw
                if(b != null) {
                    b.move(relX - lastRelX, relY - lastRelY, getMarginX(), getMarginY(), getGameViewSize());
                    lastRelX = relX;
                    lastRelY = relY;
                    invalidate();
                    return true;
                }
                break;
        }

        return false;
    }

    public void setGame(Game g){
        game = g;
        //game.setGameView(this);
    }


    @Override
    protected void onDraw(Canvas canvas) {





        super.onDraw(canvas);

        int wid = canvas.getWidth();
        int hit = canvas.getHeight();

        int minDim = wid < hit ? wid : hit;

        gameViewSize = (int)(minDim * SCALE_IN_VIEW);
        //Compute the margins so we center the puzzle
        marginX = (wid - gameViewSize) / 2;
        marginY = (hit - gameViewSize) / 2;


        scaleFactor = (float)gameViewSize / (float)canvas.getWidth();


        canvas.drawRect(marginX, marginY, marginX + gameViewSize, marginY + gameViewSize, fillPaint);
        game.draw(canvas, marginX, marginY, gameViewSize, scaleFactor);
        game.getActivePlayer().drawBird(canvas, getMarginX(), getMarginY(), getGameViewSize(), getScaleFactor());
    }

    private boolean onTouched(float x, float y) {

        if(game.getActivePlayer().getBird() == null){
            return false;
        }
        else{
            b = game.getActivePlayer().getBird();
            lastRelX = x;
            lastRelY = y;
            return true;
        }
    }


    //region Getters

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

    //endregion
}
