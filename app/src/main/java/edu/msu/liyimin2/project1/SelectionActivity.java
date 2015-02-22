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
    private ImageView imgView;

    /**
     *
     */
    private int birdIndex = 0;

    /**
     * Bird List
     */
    private ArrayList<Bird> birdList =  new ArrayList<Bird>();

    private int round = 0;
    private int cnt = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        game = (Game)bundle.getSerializable("GAME");
        cnt = bundle.getInt("COUNT", 0);
        round = bundle.getInt("ROUND", 0);

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


        birdList.add(new Bird(getBaseContext(), R.drawable.donald));
        birdList.add(new Bird(getBaseContext(), R.drawable.robin));
        birdList.add(new Bird(getBaseContext(), R.drawable.parrot));
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
        cnt++;
        if(round == 0){
            if (cnt == 1) {
                Intent intent = new Intent(this, SelectionActivity.class);
                game.getPlayer1().setBird(birdList.get(birdIndex));

                Bundle bundle = new Bundle();
                bundle.putSerializable("GAME", game);
                bundle.putInt("COUNT", cnt);
                bundle.putInt("ROUND", round);
                intent.putExtras(bundle);
                startActivity(intent);
            }
            if (cnt == 2) {
                Intent intent = new Intent(this, GameActivity.class);
                game.getPlayer2().setBird(birdList.get(birdIndex));

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
                game.getPlayer2().setBird(birdList.get(birdIndex));

                Bundle bundle = new Bundle();
                bundle.putSerializable("GAME", game);
                bundle.putInt("COUNT", cnt);
                bundle.putInt("ROUND", round);
                intent.putExtras(bundle);
                startActivity(intent);
            }
            if (cnt == 2) {
                Intent intent = new Intent(this, GameActivity.class);
                game.getPlayer1().setBird(birdList.get(birdIndex));

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

    public void onNext(View view) {
        if(birdIndex != birdList.size()-1) {
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

}