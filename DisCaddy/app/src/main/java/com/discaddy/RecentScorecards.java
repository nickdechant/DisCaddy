package com.discaddy;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class RecentScorecards extends Activity {
    //private ScorecardDbAdapter mDbHelperScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_scorecards);
        //mDbHelperScore = new ScorecardDbAdapter(this);
        //mDbHelperScore.open();
        //fillData();
    }

    public void onContentChanged() {
        super.onContentChanged();
        View empty = findViewById(R.id.empty);
        ListView list = (ListView) findViewById(R.id.score_card_list);
        list.setEmptyView(empty);
    }


/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.recent_scorecards, menu);
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
    }*/

//    private void fillData() {
//        // Get all of the notes from the database and create the item list
//        //Cursor c = mDbHelperScore.fetchAllPlayers();
//        startManagingCursor(c);
//
//        //String[] from = new String[] { ScorecardDbAdapter.keys[1]};
//        int[] to = new int[] { R.id.text1 };
//
//        // Now create an array adapter and set it to display using our row
//        //SimpleCursorAdapter players =
//               // new SimpleCursorAdapter(this, R.layout.list_row, c, from, to);
//        ListView playerList = (ListView)findViewById(R.id.score_card_list);
//        playerList.setAdapter(players);
//        playerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position,
//                                    long id) {
//
//                Cursor playerData = mDbHelperScore.fetchPlayer(id);
//                long player_id = playerData.getLong(0);
//                String name = playerData.getString(1); // Name index
//                String scores = playerData.getString(2); // JSON Object
//
//                Intent myIntent = new Intent(RecentScorecards.this,scorecardViewer.class);
//                myIntent.putExtra("name", name);
//                myIntent.putExtra("scores", scores);
//                myIntent.putExtra("id", player_id);
//               RecentScorecards.this.startActivity(myIntent);;
//            }
//        });
//    }


}
