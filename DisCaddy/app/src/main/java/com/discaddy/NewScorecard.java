package com.discaddy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class NewScorecard extends Activity {

    private Scorecard scorecard;
    private ArrayList<String> currentPlayers;
    private PlayerDbAdapter mDbHelper;
    private Map<Integer, String> selectedPlayers;

    private final String LOG_TAG = "NewScorecard";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_scorecard);

        this.scorecard = new Scorecard();
        this.currentPlayers = new ArrayList<String>();
        this.selectedPlayers = new HashMap<Integer, String>();
        mDbHelper = new PlayerDbAdapter(this);
        mDbHelper.open();
        fillData();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.new_scorecard, menu);
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




    public void fillData() {
        // Get all of the notes from the database and create the item list
        Cursor c = this.mDbHelper.fetchAllPlayers();
        startManagingCursor(c);

        String[] from = new String[] { PlayerDbAdapter.KEY_Name };
        int[] to = new int[] { R.id.text1 };

        // Now create an array adapter and set it to display using our row
        SimpleCursorAdapter players =
                new SimpleCursorAdapter(this, R.layout.player_row, c, from, to);
        final ListView playerList = (ListView)findViewById(R.id.players_list);
        playerList.setAdapter(players);

        playerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
//                Toast.makeText(getApplicationContext(),
//                        "Click ListItem Number " + position, Toast.LENGTH_SHORT)
//                        .show();

                Cursor playerData = mDbHelper.fetchPlayer(id);
                String name = playerData.getString(1); // Name index

                String putStr = "";

                if (selectedPlayers.containsKey(position)) {
                    if (Integer.parseInt(selectedPlayers.get(position).split("#")[0]) == 1) {
                        putStr = "0#" + selectedPlayers.get(position).split("#")[1];
                        selectedPlayers.put(position, putStr);
                        Toast.makeText(getApplicationContext(),"item " + position + " now unselected", Toast.LENGTH_SHORT).show();
//                        ((TextView)view).setBackgroundColor(Color.argb(0,0,0,0));
                    }
                    else {
                        putStr = "1#" + selectedPlayers.get(position).split("#")[1];
                        selectedPlayers.put(position, putStr);
                        Toast.makeText(getApplicationContext(),"item " + position + " now selected", Toast.LENGTH_SHORT).show();
//                        ((TextView)view).setBackgroundColor(Color.argb(125, 75, 236, 90));
                    }
                }
                else {
                    putStr = "1#" + name;
                    selectedPlayers.put(position, putStr);
                    Toast.makeText(getApplicationContext(),"item " + position + " now selected", Toast.LENGTH_SHORT).show();
//                    ((TextView)view).setBackgroundColor(Color.argb(125, 75, 236, 90));
                }
//                if (view.isSelected()) {
//                    view.setSelected(false);
//                if (view.isPressed()) {
//                    view.setPressed(false);
//                    Toast.makeText(getApplicationContext(),
//                            "item is now unselected", Toast.LENGTH_LONG)
//                            .show();
////                    ((TextView)view).setBackgroundResource(Color.rgb(0,200,0));
//                }
//                else {
////                    view.setSelected(true);
//                    view.setPressed(true);
//                    Toast.makeText(getApplicationContext(),
//                            "item is now selected", Toast.LENGTH_LONG)
//                            .show();
////                    ((TextView)view).setBackgroundResource(Color.rgb(200,0,0));
//                }



                TextView tv = (TextView) view;
                tv.setBackgroundColor(Color.argb(125, 75, 236, 90));

            }
        });


//        playerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position,
//                                    long id) {
//
//                Cursor playerData = mDbHelper.fetchPlayer(id);
//                String name = playerData.getString(1); // Name index
//                String course = playerData.getString(2); // Course index
//                String score = playerData.getString(3); // Score index
//                String disk = playerData.getString(4); // Disk index
//
//                Intent myIntent = new Intent(NewScorecard.this, PlayerProfile.class);
//                myIntent.putExtra("name", name);
//                myIntent.putExtra("course", course);
//                myIntent.putExtra("score", score);
//                myIntent.putExtra("disk", disk);
//                NewScorecard.this.startActivity(myIntent);;
//            }
//        });
    }

    public void addNewPlayer(View view) {
        Intent myIntent = new Intent(NewScorecard.this, NewPlayer.class);
        NewScorecard.this.startActivity(myIntent);
    }

    public void addCurrentPlayer(View view) {
        //get player represented by item clicked (each item should be one player)
        //add to list of currentPlayers
    }

    public void go(View view) {
        //currentPlayers should contain all players
        for (Map.Entry<Integer, String> e : selectedPlayers.entrySet()) {
//            Log.d(LOG_TAG, e.getValue());
            if (Integer.parseInt(e.getValue().split("#")[0]) == 1)
                currentPlayers.add(e.getValue().split("#")[1]);
        }

        String playerString = "";

        for (String s : currentPlayers)
            playerString += s + "#";
        playerString = playerString.substring(0,playerString.length() - 1);

        Intent myIntent = new Intent(NewScorecard.this, Scorecard.class);

        myIntent.putExtra("playerString", playerString);

        NewScorecard.this.startActivity(myIntent);

        //currentPlayers now contains all players that are playing.
        //pass this to new Intent for Scorecard Activity


    }
}
