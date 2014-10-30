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

import org.w3c.dom.Text;


public class PlayerEdit extends Activity {
    private PlayerDbAdapter mDbHelper_edit;
    private String name;
    private String course;
    private String score;
    private String disk;
    private long player_id;
    private EditText nameView;
    private TextView courseView;
    private TextView diskView;
    private TextView scoreView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_edit);
        mDbHelper_edit = new PlayerDbAdapter(this);
        mDbHelper_edit.open();
        Intent playerIntent = getIntent();
        name = playerIntent.getStringExtra("name");
        course = playerIntent.getStringExtra("course");
        score = playerIntent.getStringExtra("score");
        disk = playerIntent.getStringExtra("disk");
        player_id = playerIntent.getLongExtra("id", 0);
        Log.v("PlayerEditOnCreate", "Player_id: "+player_id);

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

    public void updatePlayer(View view){
        Log.v("PlayerEditUpdatePlayer", "Player ID: "+player_id);

       if(mDbHelper_edit.updatePlayer(player_id, nameView.getText().toString(), courseView.getText().toString(),
               scoreView.getText().toString(), diskView.getText().toString())) {
           Intent myIntent = new Intent(PlayerEdit.this, PlayerProfile.class);
           myIntent.putExtra("name", nameView.getText().toString());
           myIntent.putExtra("score", scoreView.getText().toString());
           myIntent.putExtra("disk", diskView.getText().toString());
           myIntent.putExtra("course", courseView.getText().toString());
           myIntent.putExtra("id", player_id);
           myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NO_HISTORY);
           PlayerEdit.this.startActivity(myIntent);
       }else
           Log.v("PlayerEdit", "Did not update");
    }

}


