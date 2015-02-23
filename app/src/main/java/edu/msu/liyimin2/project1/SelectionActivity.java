package edu.msu.liyimin2.project1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class SelectionActivity extends ActionBarActivity {
    /**
     * Game class
     */
    private Game game = new Game();

    /**
     * Image View
     */
    private ImageView imgView = null;

    /**
     *
     */
    private int birdIndex = 0;

    /**
     * Bird List
     */
    private ArrayList<Bird> birdList =  new ArrayList<Bird>();

    //private int round = 0;
    //private int cnt = 0;

    private Boolean moveOn = false;
    private TextView playerIndicator;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        game = (Game)bundle.getSerializable("GAME");

        playerIndicator = (TextView)findViewById(R.id.playerIndicator);
        playerIndicator.setText(game.getActivePlayer().getName());

        birdList.add(new Bird(getBaseContext(), R.drawable.bananaquit));
        birdList.add(new Bird(getBaseContext(), R.drawable.ostrich));
        birdList.add(new Bird(getBaseContext(), R.drawable.parrot));
        birdList.add(new Bird(getBaseContext(), R.drawable.seagull));
        birdList.add(new Bird(getBaseContext(), R.drawable.swallow));

        imgView = (ImageView)findViewById(R.id.birdImage);
        imgView.setImageResource(birdList.get(birdIndex).getBirdId());
    }

    @Override
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
    }

    public void onSelect(View view)
    {
        game.getActivePlayer().setBird(new Bird(getBaseContext(), birdList.get(birdIndex).getBirdId()));
        if(!moveOn){
            moveOn = true;
            game.nextPlayer();
            playerIndicator.setText(game.getActivePlayer().getName());
            birdIndex = 0;
            imgView.setImageResource(birdList.get(birdIndex).getBirdId());
            imgView.invalidate();
        }
        else{
            Intent intent = new Intent(this, GameActivity.class);
            moveOn = false;
            game.nextPlayer();
            Bundle bundle = new Bundle();
            bundle.putSerializable("GAME", game);
            intent.putExtras(bundle);
            startActivity(intent);
            this.finish();
        }

    }

    public void onNext(View view) {
        if(birdIndex < birdList.size()-1) {
            birdIndex++;
            imgView.setImageResource(birdList.get(birdIndex).getBirdId());
            imgView.invalidate();
        }
    }

    public void onPrev(View view){
        if(birdIndex != 0) {
            birdIndex--;
            imgView.setImageResource(birdList.get(birdIndex).getBirdId());
            imgView.invalidate();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putSerializable("GAME", game);
        bundle.putInt("INDEX", birdIndex);
    }

    @Override
    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        game = (Game)bundle.getSerializable("GAME");
        birdIndex = bundle.getInt("INDEX");

        imgView.setImageResource(birdList.get(birdIndex).getBirdId());
        imgView.invalidate();
        playerIndicator.setText(game.getActivePlayer().getName());
    }
}