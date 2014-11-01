package com.discaddy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class PlayerEdit extends Activity {
    private PlayerDbAdapter mDbHelper_edit;
    private long player_id;
    private EditText nameView;
    private TextView courseView, diskView, scoreView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_edit);
        //open database
        mDbHelper_edit = new PlayerDbAdapter(this);
        mDbHelper_edit.open();
        //get profile info from intent
        Intent playerIntent = getIntent();
        String name = playerIntent.getStringExtra("name");
        String course = playerIntent.getStringExtra("course");
        String score = playerIntent.getStringExtra("score");
        String disk = playerIntent.getStringExtra("disk");
        //set edit fields
        player_id = playerIntent.getLongExtra("id", 0);
        nameView = (EditText) findViewById(R.id.name_edit_player);
        nameView.setText(name);
        courseView = (TextView) findViewById(R.id.course_edit_player);
        courseView.setText(course);
        scoreView = (TextView) findViewById(R.id.score_edit_player);
        scoreView.setText(score);
        diskView = (TextView) findViewById(R.id.disk_edit_player);
        diskView.setText(disk);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.player_edit, menu);
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

    /*
    *Called upon submit button click
    *Update player's information in database from the edit fields
    *Sends user back to PlayerProfile
    */
    public void updatePlayer(View view){
        String nameview = nameView.getText().toString();
        String courseview = courseView.getText().toString();
        String scoreview = scoreView.getText().toString();
        String diskview = diskView.getText().toString();

        if(!nameview.equals("")) {
            if (mDbHelper_edit.updatePlayer(player_id, nameview, courseview, scoreview, diskview)) {
                Intent myIntent = new Intent(PlayerEdit.this, PlayerProfile.class);
                myIntent.putExtra("name", nameview);
                myIntent.putExtra("score", scoreview);
                myIntent.putExtra("disk", diskview);
                myIntent.putExtra("course", courseview);
                myIntent.putExtra("id", player_id);
                myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NO_HISTORY);
                PlayerEdit.this.startActivity(myIntent);
            } else
                Toast.makeText(this, "Error: Could not update", Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(this, "Error: No player name", Toast.LENGTH_SHORT).show();
    }

}


