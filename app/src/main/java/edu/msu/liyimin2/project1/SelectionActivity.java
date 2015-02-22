package edu.msu.liyimin2.project1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;


public class SelectionActivity extends ActionBarActivity {
    /**
     * Game class
     */
    private Game game = new Game();
    private int round = 0;
    private int cnt = 0;
    private int birdIndex = 0;
    private SelectionView selectionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        selectionView = (SelectionView)this.findViewById(R.id.selectionView);

        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        game = (Game)bundle.getSerializable("GAME");



        cnt = bundle.getInt("COUNT", 0);
        round = (Integer)bundle.getInt("ROUND", 0);
        birdIndex = bundle.getInt("BIRDINDEX", 0);
        selectionView.setBirdIndex(birdIndex);
        TextView playerIndicator = (TextView)findViewById(R.id.playerIndicator);
        if(round == 0){
            if(cnt == 0){
                playerIndicator.setText(game.getPlayer1().getName());
            }
            if(cnt == 1){
                playerIndicator.setText(game.getPlayer2().getName());
            }
        }
        //switch the order to pick the bird
        if(round == 1){
            if(cnt == 1){
                playerIndicator.setText(game.getPlayer1().getName());
            }
            if(cnt == 0){
                playerIndicator.setText(game.getPlayer2().getName());
            }
        }
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_selection, menu);
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
    }*/

    public void onSelect(View view)
    {
        cnt++;
        if(round == 0){
            if (cnt == 1) {
                Intent intent = new Intent(this, SelectionActivity.class);
                game.getPlayer1().setBird(selectionView.getBird());

                Bundle bundle = new Bundle();
                bundle.putSerializable("GAME", game);
                bundle.putInt("COUNT", cnt);
                bundle.putInt("ROUND", round);
                intent.putExtras(bundle);
                startActivity(intent);
            }
            if (cnt == 2) {
                Intent intent = new Intent(this, GameActivity.class);
                game.getPlayer2().setBird(selectionView.getBird());

                Bundle bundle = new Bundle();
                bundle.putSerializable("GAME", game);
                cnt = 0;
                bundle.putInt("COUNT", cnt);
                bundle.putInt("ROUND", round);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }
        if(round == 1){
            if (cnt == 1) {
                Intent intent = new Intent(this, SelectionActivity.class);
                game.getPlayer2().setBird(selectionView.getBird());

                Bundle bundle = new Bundle();
                bundle.putSerializable("GAME", game);
                bundle.putInt("COUNT", cnt);
                bundle.putInt("ROUND", round);
                intent.putExtras(bundle);
                startActivity(intent);
            }
            if (cnt == 2) {
                Intent intent = new Intent(this, GameActivity.class);
                game.getPlayer1().setBird(selectionView.getBird());

                Bundle bundle = new Bundle();
                bundle.putSerializable("GAME", game);
                cnt = 0;
                bundle.putInt("COUNT", cnt);
                bundle.putInt("ROUND", round);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }
    }

    public void onNext(View view){
        if(birdIndex<=3){
            birdIndex++;
            Intent intent = new Intent(this, SelectionActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("GAME", game);
            bundle.putInt("COUNT", cnt);
            bundle.putInt("ROUND", round);
            bundle.putInt("BIRDINDEX", birdIndex);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else {
            return;
        }
    }

    public void onPrev(View view){
        if(birdIndex >= 1){
            birdIndex--;
            Intent intent = new Intent(this, SelectionActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("GAME", game);
            bundle.putInt("COUNT", cnt);
            bundle.putInt("ROUND", round);
            bundle.putInt("BIRDINDEX", birdIndex);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else{
            return;
        }
    }
}
