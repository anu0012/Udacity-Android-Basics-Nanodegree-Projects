package com.example.anuragsharma.footballscore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private int score_A=0;
    private int score_B=0;
    private int foul_A=0;
    private int foul_B=0;

    public void addGoalForTeamA(View v){
        score_A++;
        displayScoreA(score_A);
    }

    public void addGoalForTeamB(View v){
        score_B++;
        displayScoreB(score_B);
    }

    public void addFoulForTeamA(View v){
        foul_A++;
        displayFoulA(foul_A);
    }

    public void addFoulForTeamB(View v){
        foul_B++;
        displayFoulB(foul_B);
    }

    public void displayScoreA(int score)
    {
        TextView t=(TextView)findViewById(R.id.team_a_goal);
        t.setText(String.valueOf(score));
    }

    public void displayScoreB(int score)
    {
        TextView t=(TextView)findViewById(R.id.team_b_goal);
        t.setText(String.valueOf(score));
    }

    public void displayFoulA(int foul)
    {
        TextView t=(TextView)findViewById(R.id.team_a_foul);
        t.setText(String.valueOf(foul));
    }

    public void displayFoulB(int foul)
    {
        TextView t=(TextView)findViewById(R.id.team_b_foul);
        t.setText(String.valueOf(foul));
    }

    public void resetScore(View v)
    {
        score_A=0;
        score_B=0;
        foul_A=0;
        foul_B=0;
        displayScoreA(score_A);
        displayScoreB(score_B);
        displayFoulA(foul_A);
        displayFoulB(foul_B);
    }

}
