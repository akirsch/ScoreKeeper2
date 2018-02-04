package com.example.android.scorekeeper;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.scorekeeper.R;

public class MainActivity extends AppCompatActivity {

    static final String STATE_SCORETEAMA ="scoreTeamA";
    static final String STATE_SCORETEAMB = "scoreTeamB";
    static final String STATE_CORNERTEAMA = "teamACorner";
    static final String STATE_CORNERTEAMB = "teamBCorner";
    static final String STATE_REDCARDTEAMA = "redCardTeamA";
    static final String STATE_REDCARDTEAMB = "redCardTeamB";
    static final String STATE_YELLOWCARDTEAMA = "yellowCardTeamA";
    static final String STATE_YELLOWCARDTEAMB = "yellowCardTeamB";

    int scoreTeamA = 0;
    int scoreTeamB = 0;
    int teamACorners = 0;
    int teamBCorners = 0;
    int redCardTeamA = 0;
    int yellowCardTeamA= 0;
    int redCardTeamB = 0;
    int yellowCardTeamB= 0;

    ProgressBar[] teamAProgressBarArray = new ProgressBar[4];
    ProgressBar teamBGoalsProgressBar;
    ProgressBar teamBRedCardsProgressBar;
    ProgressBar teamBYellowCardsProgressBar;
    ProgressBar teamBCornersProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        teamAProgressBarArray[0] = findViewById(R.id.team_a_goals_progress_bar);
        teamAProgressBarArray[1] = findViewById(R.id.team_a_redCards_progress_bar);
        teamAProgressBarArray[2] = findViewById(R.id.team_a_yellowCards_progress_bar);
        teamAProgressBarArray[3] = findViewById(R.id.team_a_corners_progress_bar);

        teamBGoalsProgressBar  = findViewById(R.id.team_b_goals_progress_bar);
        teamBRedCardsProgressBar = findViewById(R.id.team_b_redCards_progress_bar);
        teamBYellowCardsProgressBar = findViewById(R.id.team_b_yellowCards_progress_bar);
        teamBCornersProgressBar = findViewById(R.id.team_b_corners_progress_bar);

        for (int i = 0; i < teamAProgressBarArray.length; i++) {
            teamAProgressBarArray[i].setRotation(180);
        }

        // Find the Team A Score View
        ImageView teamAScoreButton = findViewById(R.id.team_a_scoreButton);

        // Set a click listener on that View
        teamAScoreButton.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                goalTeamA();
                teamAProgressBarArray[0].incrementProgressBy(1);
            }
        });

        // Find the Team B Score View
        ImageView teamBScoreButton = findViewById(R.id.team_b_scoreButton);

        // Set a click listener on that View
        teamBScoreButton.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                goalTeamB();
                teamBGoalsProgressBar.incrementProgressBy(1);
            }
        });

        ImageView teamARedCardButton = findViewById(R.id.team_a_redCardButton);
        teamARedCardButton.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
               rCardTeamA();
               teamAProgressBarArray[1].incrementProgressBy(1);
            }
        });

        ImageView teamAYellowCardButton = findViewById(R.id.team_a_YellowCardButton);
        teamAYellowCardButton.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                yCardTeamA();
                teamAProgressBarArray[2].incrementProgressBy(1);
            }
        });

        ImageView teamACornersButton = findViewById(R.id.team_a_cornerButton);
        teamACornersButton.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
               cornerTeamA();
               teamAProgressBarArray[3].incrementProgressBy(1);
            }
        });

        ImageView teamBRedCardButton = findViewById(R.id.team_b_redCardButton);
        teamBRedCardButton.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                rCardTeamB();
                teamBRedCardsProgressBar.incrementProgressBy(1);
            }
        });

        ImageView teamBYellowCardButton = findViewById(R.id.team_b_YellowCardButton);
        teamBYellowCardButton.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                yCardTeamB();
                teamBYellowCardsProgressBar.incrementProgressBy(1);
            }
        });

        ImageView teamBCornersButton = findViewById(R.id.team_b_cornerButton);
        teamBCornersButton.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                cornersTeamB();
                teamBCornersProgressBar.incrementProgressBy(1);
            }
        });



        // Find the Reset Icon View
        ImageView resetGame = findViewById(R.id.resetButton);

        // Set a click listener on that View
        resetGame.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                Intent MainActivity = getBaseContext().getPackageManager()
                        .getLaunchIntentForPackage( getBaseContext().getPackageName() );
                MainActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(MainActivity);
            }
        });


    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current score state
        savedInstanceState.putInt(STATE_SCORETEAMA, scoreTeamA);
        savedInstanceState.putInt(STATE_SCORETEAMB, scoreTeamB);
        savedInstanceState.putInt(STATE_CORNERTEAMA, teamACorners);
        savedInstanceState.putInt(STATE_CORNERTEAMB, teamBCorners);
        savedInstanceState.putInt(STATE_REDCARDTEAMA, redCardTeamA);
        savedInstanceState.putInt(STATE_REDCARDTEAMB, redCardTeamB);
        savedInstanceState.putInt(STATE_YELLOWCARDTEAMA , yellowCardTeamA);
        savedInstanceState.putInt(STATE_YELLOWCARDTEAMB, yellowCardTeamB);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);

        // Restore state members from saved instance
        scoreTeamA = savedInstanceState.getInt(STATE_SCORETEAMA);
        scoreTeamB = savedInstanceState.getInt(STATE_SCORETEAMB);
        teamACorners = savedInstanceState.getInt(STATE_CORNERTEAMA);
        teamBCorners = savedInstanceState.getInt(STATE_CORNERTEAMB);
        redCardTeamA = savedInstanceState.getInt(STATE_REDCARDTEAMA);
        redCardTeamB = savedInstanceState.getInt(STATE_REDCARDTEAMB);
        yellowCardTeamA = savedInstanceState.getInt(STATE_YELLOWCARDTEAMA);
        yellowCardTeamB = savedInstanceState.getInt(STATE_YELLOWCARDTEAMB);

        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
        displayCornersForTeamA(teamACorners);
        displayCornersForTeamB(teamBCorners);
        displayRedCardsForTeamA(redCardTeamA);
        displayRedCardsForTeamB(redCardTeamB);
        displayYellowCardsForTeamA(yellowCardTeamA);
        displayYellowCardsForTeamB(yellowCardTeamB);

    }
    /**
     * Displays the given score for Team A.
     */
    public void displayForTeamA(int score) {
        TextView teamAScore = findViewById(R.id.team_a_score);
        teamAScore.setText(String.valueOf(score));
    }

    /**
     * Displays the given fouls for Team A.
     */
    public void displayCornersForTeamA(int fouls) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_cornerDisplay);
        scoreView.setText(String.valueOf(fouls));
    }

    /**
     * Displays the given Yellow Cards for Team A.
     */
    public void displayYellowCardsForTeamA(int yCards) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_yellowCardDisplay);
        scoreView.setText(String.valueOf(yCards));
    }

    /**
     * Displays the given Yellow Cards for Team A.
     */
    public void displayRedCardsForTeamA(int rCards) {
        TextView scoreView = findViewById(R.id.team_a_redCardDisplay);
        scoreView.setText(String.valueOf(rCards));
    }

    /**
     * This method is called when the GOAL button for Team A is clicked.
     */
    public void goalTeamA() {
        scoreTeamA++;
        displayForTeamA(scoreTeamA);
    }

    /**
     * This method is called when the Foals button for Team A is clicked.
     */
    public void cornerTeamA() {
        teamACorners++;
        displayCornersForTeamA(teamACorners);
    }

    /**
     * This method is called when the Yellow Card button for Team A is clicked.
     */
    public void yCardTeamA() {
        yellowCardTeamA++;
        displayYellowCardsForTeamA(yellowCardTeamA);
    }

    /**
     * This method is called when the Red Card button for Team A is clicked.
     */
    public void rCardTeamA() {
        redCardTeamA++;
        displayRedCardsForTeamA(redCardTeamA);
    }

    /**
     * This method is called when the New Game button is clicked.
     */
    public void newGame(View view) {
        scoreTeamA = 0;
        scoreTeamB = 0;
        teamBCorners = 0;
        teamACorners = 0;
        redCardTeamA = 0;
        yellowCardTeamA = 0;
        redCardTeamB = 0;
        yellowCardTeamB = 0;
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
        displayCornersForTeamA(teamACorners);
        displayCornersForTeamB(teamBCorners);
        displayRedCardsForTeamA(redCardTeamA);
        displayYellowCardsForTeamA(yellowCardTeamA);
        displayRedCardsForTeamB(redCardTeamB);
        displayYellowCardsForTeamB(yellowCardTeamB);
    }

    /**
     * Displays the given score for Team B.
     */
    public void displayForTeamB(int score) {
        TextView scoreView = findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Displays the given fould for Team B.
     */
    public void displayCornersForTeamB(int score) {
        TextView scoreView = findViewById(R.id.team_b_cornerDisplay);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Displays the given Yellow Cards for Team B.
     */
    public void displayRedCardsForTeamB(int rCards) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_redCardDisplay);
        scoreView.setText(String.valueOf(rCards));
    }

    /**
     * Displays the given Yellow Cards for Team B.
     */
    public void displayYellowCardsForTeamB(int yCards) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_yellowCardDisplay);
        scoreView.setText(String.valueOf(yCards));
    }

    /**
     * This method is called when the GOAL button for Team B is clicked.
     */
    public void goalTeamB() {
        scoreTeamB++;
        displayForTeamB(scoreTeamB);
    }

    /**
     * This method is called when the Fouls button for Team B is clicked.
     */
    public void cornersTeamB() {
        teamBCorners++;
        displayCornersForTeamB(teamBCorners);
    }

    /**
     * This method is called when the Yellow Card button for Team B is clicked.
     */
    public void yCardTeamB() {
        yellowCardTeamB++;
        displayYellowCardsForTeamB(yellowCardTeamB);
    }

    /**
     * This method is called when the Red Card button for Team B is clicked.
     */
    public void rCardTeamB() {
        redCardTeamB++;
        displayRedCardsForTeamB(redCardTeamB);
    }

}
