package com.discaddy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;


public class CoursesLookUp extends Activity {

    private final static String key = "AIzaSyDwflKsNSeb4_2FyX3w7p-4iXpEuy9eTAE";
    private String parameters;
    private double lat, lng;
    private final static String TAG = "CoursesLookUp";
    public HashMap<String, String> coursesMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_look_up);
        coursesMap = new HashMap<String, String>();
        setParameters();
        new GetPlaces().execute(parameters);
    }


    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_courses_look_up, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/



    public void fillData() {

        final ArrayList<String> courseNames = new ArrayList<String>(coursesMap.keySet());
        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, courseNames);

        ListView listView = (ListView) findViewById(R.id.courses_lookup_list);
        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                String name = (String) parent.getItemAtPosition(position);
                String place_id = coursesMap.get(name);
                String parameter_click = "https://maps.googleapis.com/maps/api/place/details/json?placeid="+place_id+"&key="+key;

                Intent myIntent = new Intent(CoursesLookUp.this, CoursesWebView.class);
                myIntent.putExtra("parameter", parameter_click);
                myIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                CoursesLookUp.this.startActivity(myIntent);
            }
        });
    }

    private void setUserLocation(){
        //update location
        LocationManager locMan = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Location lastLoc = locMan.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        lat = lastLoc.getLatitude();
        lng = lastLoc.getLongitude();
    }

    private void setParameters(){
        setUserLocation();
        parameters = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+lat+","+lng+"&rankby=distance&name=disc%20golf%20course&key="+key;
        Log.v(TAG, parameters);
    }

    public void onContentChanged() {
        super.onContentChanged();
        View empty = findViewById(R.id.empty);
        ListView list = (ListView) findViewById(R.id.courses_lookup_list);
        list.setEmptyView(empty);
    }

    //wraps database access so that it is not done in the GUI thread.
    public class GetPlaces extends AsyncTask<String, Void, String> {

        @Override
        protected  void onPreExecute(){
            TextView none = (TextView) findViewById(R.id.empty);
            none.setText("Loading Nearby Courses...");
        }

        //fetch and parse place data
        @Override
        protected String doInBackground(String... placesURL) {
            StringBuilder placesBuilder = new StringBuilder();
            //fetch places
            for (String placeSearchURL : placesURL) {
                //execute search
                HttpClient placesClient = new DefaultHttpClient();
                try {
                    //try to fetch the data
                    HttpGet placesGet = new HttpGet(placeSearchURL);
                    HttpResponse placesResponse = placesClient.execute(placesGet);
                    StatusLine placeSearchStatus = placesResponse.getStatusLine();
                    if (placeSearchStatus.getStatusCode() == 200) {
                        //we have an OK response
                        HttpEntity placesEntity = placesResponse.getEntity();
                        InputStream placesContent = placesEntity.getContent();
                        InputStreamReader placesInput = new InputStreamReader(placesContent);
                        BufferedReader placesReader = new BufferedReader(placesInput);
                        String lineIn;
                        while ((lineIn = placesReader.readLine()) != null) {
                            placesBuilder.append(lineIn);
                        }

                    } else
                        Log.v(TAG, "Issue somewhere!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return placesBuilder.toString();
        }

        @Override
        protected void onPostExecute(String input) {
            super.onPostExecute(input);
            try {
                JSONObject jsonObject = new JSONObject(input);
                JSONArray resultsArray = jsonObject.getJSONArray("results");
                for(int index = 0; index < resultsArray.length(); index++){
                    JSONObject entry = resultsArray.getJSONObject(index);
                    coursesMap.put(entry.getString("name"), entry.getString("place_id"));
                }
                fillData();
            }catch(JSONException e){
                //usually if their was a connection problem.
                TextView none = (TextView) findViewById(R.id.empty);
                none.setText("No Courses Found");
                Toast.makeText(CoursesLookUp.this, "Problem Connecting", Toast.LENGTH_SHORT).show();
            }
        }
    }
}




