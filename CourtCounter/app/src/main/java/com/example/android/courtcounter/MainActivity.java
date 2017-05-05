package com.example.android.courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int scoreTeamA = 0;
    int scoreTeamB = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addOneForTeamA(View v){
        scoreTeamA = scoreTeamA + 1;
        displayScoreTeamA(scoreTeamA);
    }
    public void addTwoForTeamA(View v){
        scoreTeamA = scoreTeamA + 2;
        displayScoreTeamA(scoreTeamA);
    }
    public void addThreeForTeamA(View v){
        scoreTeamA = scoreTeamA + 3;
        displayScoreTeamA(scoreTeamA);
    }
    public void displayScoreTeamA(int score){
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }
/*
* This is for team B*/
    public void addOneForTeamB(View v){
        scoreTeamB = scoreTeamB + 1;
        displayScoreTeamB(scoreTeamB);
    }
    public void addTwoForTeamB(View v){
        scoreTeamB = scoreTeamB + 2;
        displayScoreTeamB(scoreTeamB);
    }
    public void addThreeForTeamB(View v){
        scoreTeamB = scoreTeamB + 3;
        displayScoreTeamB(scoreTeamB);
    }
    public void displayScoreTeamB(int score){
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }

    public void resetScore(View v){
        scoreTeamA = 0;
        scoreTeamB = 0;
        displayScoreTeamA(scoreTeamA);
        displayScoreTeamB(scoreTeamB);
    }
}
