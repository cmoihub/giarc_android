package com.practice.g_i_arc.courtcounter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int points_A = 0;
    int points_B = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Increase team A's score by 3
     * @param view
     */
    public void threePointer_A(View view) {
        points_A += 3;
        displayScore(points_A,"A");
    }

    /**
     * Increase team B's score by 3
     * @param view
     */
    public void threePointer_B(View view) {
        points_B += 3;
        displayScore(points_B,"B");
    }

    /**
     * Increase team A's score by 2
     * @param view
     */
    public void twoPointer_A(View view) {
        points_A += 2;
        displayScore(points_A,"A");
    }

    /**
     * Increase team A's score by 2
     * @param view
     */
    public void twoPointer_B(View view) {
        points_B += 2;
        displayScore(points_B,"B");
    }

    /**
     * Increase team A's score by 1
     * @param view
     */
    public void freeThrow_A(View view) {
        points_A++;
        displayScore(points_A,"A");
    }

    /**
     * Increase team B's score by 1
     * @param view
     */
    public void freeThrow_B(View view) {
        points_B++;
        displayScore(points_B,"B");
    }

    /**
     * updates the scoreboard for a given team for the basketball game.
     * @param team
     * @param scores
     */
    public void displayScore(int scores, String team) {
        TextView scoreBoard = null;
        if(team=="A") scoreBoard = (TextView) findViewById(R.id.point_view_A);
        else scoreBoard = (TextView) findViewById(R.id.point_view_B);
        scoreBoard.setText(String.valueOf(scores));
    }

    public void reset(View view){
        points_A=0;
        points_B=0;
        displayScore(points_A,"A");
        displayScore(points_B,"B");
    }
}