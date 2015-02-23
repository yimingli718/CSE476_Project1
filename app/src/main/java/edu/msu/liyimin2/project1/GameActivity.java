package edu.msu.liyimin2.project1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class GameActivity extends ActionBarActivity {
    private Game game;
    private GameView gameView;
    private boolean moveOn = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gameView = (GameView)this.findViewById(R.id.gameView);

        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        game=(Game)bundle.getSerializable("GAME");

        gameView.setGame(game);

        TextView user = (TextView)findViewById(R.id.user);
        user.setText(game.getActivePlayer().getName());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onConfirm(View view) {
        if(!moveOn){
            moveOn = true;
            //gameView.setPlayer(game.getActivePlayer());
            game.getActivePlayer().addPoint();
            game.ArchiveBird(game.getActivePlayer().getBird());
            game.nextPlayer();
            TextView user = (TextView)findViewById(R.id.user);
            user.setText(game.getActivePlayer().getName());
            gameView.setGame(game);
            gameView.invalidate();
        }
        else{
            moveOn = false;
            game.getActivePlayer().addPoint();
            game.ArchiveBird(game.getActivePlayer().getBird());
            Intent intent = new Intent(this, SelectionActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("GAME", game);
            intent.putExtras(bundle);
            startActivity(intent);
            this.finish();
        }
    }
}
