package com.discaddy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class NewPlayer extends Activity {

    private EditText name_field;
    private EditText score_field;
    private EditText course_field;
    private EditText disk_field;
    private PlayerDbAdapter mDbHelper_new;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_player);
        mDbHelper_new = new PlayerDbAdapter(this);
        mDbHelper_new.open();
        name_field = (EditText) findViewById(R.id.name_new_player);
        score_field = (EditText) findViewById(R.id.score_new_player);
        course_field = (EditText) findViewById(R.id.course_new_player);
        disk_field =  (EditText) findViewById(R.id.disk_new_player);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.players, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /*Inserts a new player into the database when the user clicks
    * submit button.
     */
    public void createNewPlayer(View view) {
        //adds the info from user input into database.
        mDbHelper_new.createPlayer(name_field.getText().toString(), course_field.getText().toString(),
        score_field.getText().toString(), disk_field.getText().toString());
        //sends user back to Player activity with updated list.
        Intent myIntent = new Intent(NewPlayer.this, Player.class);
        myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NO_HISTORY);
        NewPlayer.this.startActivity(myIntent);
    }
}
