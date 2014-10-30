package com.discaddy;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.HashMap;
import java.util.Map;


public class Scorecard extends Activity {

    private Map<String, int[]> scores;
    private ScorecardDbAdapter mDbHelperScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_card);
        this.scores = new HashMap<String, int[]>();
        mDbHelperScore = new ScorecardDbAdapter(this);
        mDbHelperScore.open();
        //fillData(); //Prob going to need this eventually!!!!!!!!!!!
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.score_card, menu);
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

    public int[] getScores(String player) {
        return this.scores.get(player);
    }

    public void incrementScore(String player, int hole) {
        this.scores.get(player)[hole]++;
    }

    public void decrementScore(String player, int hole) {
        this.scores.get(player)[hole]--;
    }

    public void addPlayer(String name) {
        scores.put(name, new int[18]);
    }

}
