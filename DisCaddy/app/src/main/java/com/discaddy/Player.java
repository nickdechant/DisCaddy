package com.discaddy;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.TextView;
import android.widget.Toast;


public class Player extends Activity {
    private PlayerDbAdapter mDbHelper;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);
        mDbHelper = new PlayerDbAdapter(this);
        mDbHelper.open();
        //mDbHelper.createPlayer("BOB", "RoyG", "100", "Destroyer");
        fillData();
    }

    /*@Override*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.players, menu);
        return true;
    }

    /*
    * @Override
    * Used only when no list items exist
    * Displays no_players string
    */
    public void onContentChanged() {
        super.onContentChanged();
        View empty = findViewById(R.id.empty);
        ListView list = (ListView) findViewById(R.id.players_list);
        list.setEmptyView(empty);
    }

    /*
    @Override
    public boolean onOpwtionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    */

    /*
    *Sends user to NewPlayer class on button click.
    */
    public void createPlayer(View view) {
        Intent myIntent = new Intent(Player.this, NewPlayer.class);
        Player.this.startActivity(myIntent);
    }


    private void fillData() {
        // Get all of the notes from the database and create the item list
        Cursor c = mDbHelper.fetchAllPlayers();
        startManagingCursor(c);

        String[] from = new String[] { PlayerDbAdapter.KEY_Name };
        int[] to = new int[] { R.id.text1 };

        // Now create an array adapter and set it to display using our row
        SimpleCursorAdapter players =
                new SimpleCursorAdapter(this, R.layout.player_row, c, from, to);
        ListView playerList = (ListView)findViewById(R.id.players_list);
        playerList.setAdapter(players);
        playerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                Cursor playerData = mDbHelper.fetchPlayer(id);
                long player_id = playerData.getLong(0);
                Log.v("Player", "Player_id: "+player_id);
                String name = playerData.getString(1); // Name index
                String course = playerData.getString(2); // Course index
                String score = playerData.getString(3); // Score index
                String disk = playerData.getString(4); // Disk index

                Intent myIntent = new Intent(Player.this, PlayerProfile.class);
                myIntent.putExtra("name", name);
                myIntent.putExtra("course", course);
                myIntent.putExtra("score", score);
                myIntent.putExtra("disk", disk);
                myIntent.putExtra("id", player_id);
                Player.this.startActivity(myIntent);;
            }
        });
    }
}

