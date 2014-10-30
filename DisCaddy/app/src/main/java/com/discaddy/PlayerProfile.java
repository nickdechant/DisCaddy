package com.discaddy;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class PlayerProfile extends Activity {
    private PlayerDbAdapter mDbHelper_profile;
    private String name;
    private String course;
    private String score;
    private String disk;
    private long player_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_profile);
        mDbHelper_profile = new PlayerDbAdapter(this);
        mDbHelper_profile.open();

        Intent playerIntent = getIntent();
        name = playerIntent.getStringExtra("name");
        course = playerIntent.getStringExtra("course");
        score = playerIntent.getStringExtra("score");
        disk = playerIntent.getStringExtra("disk");
        player_id = playerIntent.getLongExtra("id", 0);
        Log.v("PlayerProfile", "Player_id: "+player_id);

        TextView nameView = (TextView) findViewById(R.id.player_name_field);
        nameView.setText(name);
        TextView courseView = (TextView) findViewById(R.id.player_fav_course_field);
        courseView.setText(course);
        TextView scoreView = (TextView) findViewById(R.id.player_score_field);
        scoreView.setText(score);
        TextView diskView = (TextView) findViewById(R.id.player_fav_disk_field);
        diskView.setText(disk);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.player_profile, menu);
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

    public void sendToEdit(View view) {
        Log.v("sendToEdit", "player_id: "+player_id);
        Intent myIntent = new Intent(PlayerProfile.this, PlayerEdit.class);
        myIntent.putExtra("name", name);
        myIntent.putExtra("score", score);
        myIntent.putExtra("disk", disk);
        myIntent.putExtra("course", course);
        myIntent.putExtra("id", player_id);
        //myIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        PlayerProfile.this.startActivity(myIntent);
    }

    public void sendToDelete(View view){
        mDbHelper_profile.deletePlayer(player_id);
        Intent myIntent = new Intent(PlayerProfile.this, Player.class);
        myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NO_HISTORY);
        PlayerProfile.this.startActivity(myIntent);
    }
}

