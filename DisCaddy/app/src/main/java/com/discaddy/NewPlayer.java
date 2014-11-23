package com.discaddy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NewPlayer extends Activity {

    private EditText name_field, score_field, course_field, disk_field;
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

    public void FromCard() {
        Intent i = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, 2);
    }

    /*
    *Inserts a new player into the database when the user clicks
    *submit button.
    *Sends user back to last activity on stack
    */
    public void createNewPlayer(View view) {
        String name = name_field.getText().toString();
        String score = score_field.getText().toString();
        String course = course_field.getText().toString();
        String disk = disk_field.getText().toString();

        if(name.equals(""))
            Toast.makeText(this, "No player name", Toast.LENGTH_SHORT).show();
        else{
            mDbHelper_new.createPlayer(name, course, score, disk);
            finish();
        }
    }
}
