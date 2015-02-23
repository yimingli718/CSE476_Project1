package edu.msu.liyimin2.project1;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


/**
 * TODO: document your custom view class.
 */
public class GameView extends View {
    /**
     * The actual game
     */
    private Game game = new Game();
    //private Player player = new Player();

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

    private void init(AttributeSet attrs, int defStyle) {
        game = new Game(getContext());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //return super.onTouchEvent(event);
        return game.onTouchEvent(this, event);
    }

    public void setGame(Game g){game = g;}

    //public void setPlayer(Player p){player = p;}

    //public Player getPlayer() {return player;}


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        game.draw(canvas);
        game.getActivePlayer().drawBird(canvas, game.getMarginX(), game.getMarginY(), game.getGameViewSize(), game.getScaleFactor());
    }
}
