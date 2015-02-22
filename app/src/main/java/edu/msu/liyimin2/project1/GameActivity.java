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
    private int round = 0;
    private int cnt = 0;
    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gameView = (GameView)this.findViewById(R.id.gameView);

        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        game=(Game)bundle.getSerializable("GAME");
        cnt = bundle.getInt("COUNT", 0);
        round = bundle.getInt("ROUND", 0);
        TextView user = (TextView)findViewById(R.id.user);
        if(round == 0) {
            if (cnt == 0) {
                user.setText(game.getPlayer1().getName());
                gameView.setGame(game);
                gameView.setPlayer(game.getPlayer1());
            }
            if (cnt == 1) {
                user.setText(game.getPlayer2().getName());
                gameView.setGame(game);
                gameView.setPlayer(game.getPlayer2());
            }
        }
        if(round == 1){
            if (cnt == 1) {
                user.setText(game.getPlayer1().getName());
                gameView.setGame(game);
                gameView.setPlayer(game.getPlayer1());
            }
            if (cnt == 0) {
                user.setText(game.getPlayer2().getName());
                gameView.setGame(game);
                gameView.setPlayer(game.getPlayer2());
            }
        }
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
        cnt++;
        game.ArchiveBird(gameView.getPlayer().getBird());
        if(round == 0) {
            if (cnt == 1) {
                game.getPlayer1().addPoint();
                Intent intent = new Intent(this, GameActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("GAME", game);
                bundle.putInt("COUNT", cnt);
                bundle.putInt("ROUND", round);
                intent.putExtras(bundle);
                startActivity(intent);
            }

            if (cnt == 2) {
                game.getPlayer2().addPoint();
                Intent intent = new Intent(this, SelectionActivity.class);
                //Intent intent = new Intent(this, SelectionActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("GAME", game);
                cnt = 0;
                bundle.putInt("COUNT", cnt);
                round++;
                bundle.putInt("ROUND", round);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }
        if(round == 1){
            if (cnt == 1) {
                game.getPlayer2().addPoint();
                Intent intent = new Intent(this, GameActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("GAME", game);
                bundle.putInt("COUNT", cnt);
                bundle.putInt("ROUND", round);
                intent.putExtras(bundle);
                startActivity(intent);
            }

            if (cnt == 2) {
                game.getPlayer1().addPoint();
                Intent intent = new Intent(this, SelectionActivity.class);
                //Intent intent = new Intent(this, SelectionActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("GAME", game);
                cnt = 0;
                bundle.putInt("COUNT", cnt);
                round = 0;
                bundle.putInt("ROUND", round);
                intent.putExtras(bundle);
                startActivity(intent);
            }

        }
    }
}
