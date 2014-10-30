package com.discaddy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Scorecard extends Activity {

    private Map<String, int[]> scores;
    private ArrayList<String> playerNames;
    private ScorecardDbAdapter mDbHelperScore;
    private CourseDbAdapter mDbHelperCourse;
    private final String LOG_TAG = "Scorecard";
    public int currentHole;
    public String courseName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_card);
        this.scores = new HashMap<String, int[]>();
        this.playerNames = new ArrayList<String>();
        mDbHelperScore = new ScorecardDbAdapter(this);
        mDbHelperScore.open();
        Intent myIntent = getIntent();
        this.currentHole = 0;
        //create mock course for testing
        String[] parStrings = {"4", "3", "4", "3", "4", "3", "4", "3", "4", "3", "4", "3", "4", "3", "4", "3", "4", "3"};
        mDbHelperCourse = new CourseDbAdapter(this);
        mDbHelperCourse.open();
        if (mDbHelperCourse.createCourse("Zilker", parStrings) != -1)
            Log.d(LOG_TAG, "course created successfully");

        String playerString = myIntent.getStringExtra("playerString");
        String[] players = playerString.split("#");
        for (String player : players) {
            //TODO: parse in par score from current course
            int[] pars = new int[18];
            for (int i=0; i<18; i++)
                pars[i] = Integer.parseInt(parStrings[i]);
            scores.put(player, pars);
        }

        for (Map.Entry<String, int[]> e : scores.entrySet())
            playerNames.add(e.getKey());

        ScorecardCustomAdapter custAdapter = new ScorecardCustomAdapter(this, this, scores, currentHole);
        ListView playerList = (ListView) findViewById(R.id.scorecard_list);
        playerList.setAdapter(custAdapter);

        Button prevBtn = (Button)findViewById(R.id.scorecard_prev_button);
        Button nextBtn = (Button)findViewById(R.id.scorecard_next_button);

        prevBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(currentHole > 0) {
                    currentHole--;
                    updateListView();
                }
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(currentHole < 17) {
                    currentHole++;
                    updateListView();
                }
            }
        });
        TextView holeDisplayNumber = (TextView) findViewById(R.id.scorecard_current_hole);
        holeDisplayNumber.setText("Hole " + currentHole);
        //fillData(); //Prob going to need this eventually!!!!!!!!!!!
    }

    public void updateListView() {
        ScorecardCustomAdapter custAdapter = new ScorecardCustomAdapter(this, this, scores, currentHole);
        ListView playerList = (ListView) findViewById(R.id.scorecard_list);
        playerList.setAdapter(custAdapter);
        TextView holeDisplayNumber = (TextView) findViewById(R.id.scorecard_current_hole);
        holeDisplayNumber.setText("Hole " + currentHole);
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

    public int incrementScore(String player, int hole) {
        return ++this.scores.get(player)[hole];
    }

    public int decrementScore(String player, int hole) {
       return --this.scores.get(player)[hole];
    }

    public void addPlayer(String name) {
        scores.put(name, new int[18]);
    }

}
