package edu.msu.liyimin2.project1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class FinalScore extends ActionBarActivity {

    /**
     * Game class
     */
    private Game game = new Game();

    /**
     * Winner text
     */
    private TextView winnerIndicator;

    /**
     * Player 1 score text
     */
    private TextView player1score;

    /**
     * Player 2 score text
     */
    private TextView player2score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_score);
        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        game = (Game)bundle.getSerializable("GAME");

        winnerIndicator = (TextView)findViewById(R.id.winnerText);
        if(game.getPlayer1().getScore() >= game.getPlayer2().getScore()){
            winnerIndicator.setText(getResources().getString(R.string.player_1_wins));
        }
        else if(game.getPlayer1().getScore() == game.getPlayer2().getScore()){
            winnerIndicator.setText(getResources().getString(R.string.tie_game));
        }
        else{
            winnerIndicator.setText(getResources().getString(R.string.player_2_wins));
        }
        player1score = (TextView)findViewById(R.id.player_1_score);
        String player1ScoreString = String.valueOf(game.getPlayer1().getScore());
        player1score.setText(player1ScoreString);
        player2score = (TextView)findViewById(R.id.player_2_score);
        String player2ScoreString = String.valueOf(game.getPlayer2().getScore());
        player2score.setText(player2ScoreString);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_final_score, menu);
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

    public void onMenu(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
