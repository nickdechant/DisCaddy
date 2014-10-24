package com.discaddy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class Splash extends Activity implements View.OnClickListener {

    Button newScorecard;
    Button recentScorecard;
    Button players;
    Button findACourse;
    Button discs;
    Button reportDisc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        newScorecard = (Button)findViewById(R.id.new_scorecard_button);
        recentScorecard = (Button)findViewById(R.id.recent_scorecards_button);
        players = (Button)findViewById(R.id.players_button);
        findACourse = (Button)findViewById(R.id.find_a_course_button);
        discs = (Button)findViewById(R.id.discs_button);
        reportDisc = (Button)findViewById(R.id.report_found_disc_button);

        newScorecard.setOnClickListener(this);
        recentScorecard.setOnClickListener(this);
        players.setOnClickListener(this);
        findACourse.setOnClickListener(this);
        discs.setOnClickListener(this);
        reportDisc.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == newScorecard){
            Intent myIntent = new Intent(Splash.this, NewScorecard.class);
            //myIntent.putExtra("key", value);
            Splash.this.startActivity(myIntent);
        }
        if(v == recentScorecard){
            Intent myIntent = new Intent(Splash.this, RecentScorecards.class);
            //myIntent.putExtra("key", value);
            Splash.this.startActivity(myIntent);
        }
        if(v == players){
            Intent myIntent = new Intent(Splash.this, Players.class);
            //myIntent.putExtra("key", value);
            Splash.this.startActivity(myIntent);
        }
        if(v == findACourse) {
            Intent myIntent = new Intent(Splash.this, RecentScorecards.class);
            //myIntent.putExtra("key", value);
            Splash.this.startActivity(myIntent);
        }
        if(v == discs) {
            Intent myIntent = new Intent(Splash.this, Discs.class);
            //myIntent.putExtra("key", value);
            Splash.this.startActivity(myIntent);
        }
        if(v == reportDisc) {
            Intent myIntent = new Intent(Splash.this, ReportDisc.class);
            //myIntent.putExtra("key", value);
            Splash.this.startActivity(myIntent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.splash, menu);
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
