package com.discaddy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class PlayerProfile extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_profile);

        Intent playerIntent = getIntent();
        String name = playerIntent.getStringExtra("name");
        String course = playerIntent.getStringExtra("course");
        String score = playerIntent.getStringExtra("score");
        String disk = playerIntent.getStringExtra("disk");

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
}
