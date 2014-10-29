package com.discaddy;

import java.util.*;

/**
 * Created by nick on 10/28/14.
 */
public class Scorecard {

//    private ArrayList<String> players;
    private Map<String, int[]> scores;

    public Scorecard() {
//        this.players = new ArrayList<String>();
        this.scores = new HashMap<String, int[]>();
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
