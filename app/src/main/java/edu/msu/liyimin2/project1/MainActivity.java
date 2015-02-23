package edu.msu.liyimin2.project1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        game = new Game();

        EditText player_1_text = (EditText) findViewById(R.id.player_1);
        player_1_text.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                boolean handled = false;
                if (i == EditorInfo.IME_ACTION_NEXT){
                    Toast.makeText(MainActivity.this, "Player 1 name is: "
                            + textView.getText().toString(), Toast.LENGTH_SHORT).show();
                }
                return handled;
            }
        });

        EditText player_2_text = (EditText) findViewById(R.id.player_2);
        player_2_text.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                boolean handled = false;
                if (i == EditorInfo.IME_ACTION_DONE) {
                    //show toast for input
                    Toast.makeText(MainActivity.this, "Player 2 name is: "
                            + textView.getText().toString(), Toast.LENGTH_SHORT).show();

                    //close keyboard
                    InputMethodManager inputManager = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                    handled = true;
                }
                return handled;
            }
        });
    }


    public void onStartGame(View view)
    {
        Intent intent = new Intent(this, SelectionActivity.class);
        Bundle bundle = new Bundle();

        if ((((EditText) findViewById(R.id.player_1)).getText().toString()).length() == 0
                || (((EditText) findViewById(R.id.player_2)).getText().toString()).length() == 0)
        {
            game.getPlayer1().setName(getResources().getString(R.string.player_1_name));
            game.getPlayer2().setName(getResources().getString(R.string.player_2_name));
        }
        else{
            game.getPlayer1().setName(((EditText) findViewById(R.id.player_1)).getText().toString());
            game.getPlayer2().setName(((EditText) findViewById(R.id.player_2)).getText().toString());
        }

        bundle.putSerializable("GAME", game);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void onHelp(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setTitle(R.string.HelpTitle);
        builder.setMessage(R.string.Help);
        // Create the dialog box and show it
        builder.setPositiveButton(android.R.string.ok, null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
