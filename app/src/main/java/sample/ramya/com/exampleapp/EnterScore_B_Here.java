package sample.ramya.com.exampleapp;

import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import sample.ramya.com.exampleapp.customviews.CustomTextview;
import sample.ramya.com.exampleapp.database.Dbhandler;
import sample.ramya.com.exampleapp.database.ModelPlayer;
import sample.ramya.com.exampleapp.database.TeamBModel;

public class EnterScore_B_Here extends AppCompatActivity {
    int playerOne = 0;
    int balls = 0;
    int playerNon_Stricker = 0;
    int balls_Non_Stricker = 0;
    TextView detailscorePlayerOne, scorePlayerOne, ballsCount;
    CustomTextview NAmeofPlayer, NameofPlayer2, txt_score, txt_balls;
    Dbhandler dbhandler;
    int id;
    public static boolean isPressed = false;
    String teamNameChecking;
    String name, nameNonStricker;

    List<TeamBModel> listPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_scores_here);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        scorePlayerOne = (TextView) findViewById(R.id.txt_score_player1);
        ballsCount = (TextView) findViewById(R.id.txt_balls);
        txt_score = (CustomTextview) findViewById(R.id.txt_score_player2);
        txt_balls = (CustomTextview) findViewById(R.id.txt_balls2);

        dbhandler = new Dbhandler(EnterScore_B_Here.this);
        Intent intent = this.getIntent();
        Bundle bundle = this.getIntent().getExtras();

        listPlayers = (List<TeamBModel>) bundle.getSerializable("data");

        for (int i = 0; i < listPlayers.size(); i++) {
            id = listPlayers.get(i).getIdB();
            name = listPlayers.get(0).getPlayer_nameB();
            nameNonStricker = listPlayers.get(1).getPlayer_nameB();
        }

        NAmeofPlayer = (CustomTextview) findViewById(R.id.txt_name_player1);
        NAmeofPlayer.setText(name);
        NameofPlayer2 = (CustomTextview) findViewById(R.id.txt_name_player2);
        NameofPlayer2.setText(nameNonStricker);
        scorePlayerOne.setText(" " + listPlayers.get(0).getPlayer_scoreB());
        playerOne = Integer.parseInt(listPlayers.get(0).getPlayer_scoreB());
        txt_score.setText(" " + listPlayers.get(1).getPlayer_scoreB());
        playerNon_Stricker = Integer.parseInt(listPlayers.get(1).getPlayer_scoreB());

    }

    public void makeSingle(View view) {
        playerOne = playerOne + 1;
        balls = ++balls;
        displayScoreAddition(playerOne, balls);
    }

    public void makeDouble(View view) {
        playerOne = playerOne + 2;
        balls = ++balls;
        displayScoreAddition(playerOne, balls);
    }

    public void makeTriples(View view) {
        playerOne = playerOne + 3;
        balls = ++balls;
        displayScoreAddition(playerOne, balls);
    }

    public void hitFour(View view) {
        playerOne = playerOne + 4;
        balls = ++balls;
        displayScoreAddition(playerOne, balls);
    }

    public void hitSix(View view) {
        playerOne = playerOne + 6;
        balls = ++balls;
        displayScoreAddition(playerOne, balls);
    }

    public void hitWickets(View view) {
        /*displayDetailScore(String.valueOf(playerOne));
        Dbhandler db=new Dbhandler(this);
        displayScoreAddition(playerOne);*/

        //for Updaing other player score.
        SharedPreferences preferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
        String teamnameA = preferences.getString("TEAMA", null);
        String teamnameB = preferences.getString("TEAMB", null);

        if (listPlayers.get(1).getPlayer_teamB().equals(teamnameB)) {
            dbhandler.updateScoreTeamB(new TeamBModel(listPlayers.get(1).getIdB(), "", Integer.toString(playerNon_Stricker), "", 0, 0, ""));
        } else {
            dbhandler.updateScore(new ModelPlayer(listPlayers.get(1).getIdB(), "", Integer.toString(playerNon_Stricker), "", 0, 0, ""));
        }

        callIntent(listPlayers.get(0), Integer.toString(playerOne));
    }

    public void dotBall(View view) {
        balls = ++balls;
        displayScoreAddition(playerOne, balls);
    }

    public void wideBall(View view) {
        Toast.makeText(this, "no Action", Toast.LENGTH_SHORT).show();
    }

    private void callIntent(TeamBModel playerdata, String playerOne) {
        SharedPreferences preferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
        String teamnameA = preferences.getString("TEAMA", null);
        String teamnameB = preferences.getString("TEAMB", null);

//        Intent resultScoreintent = new Intent();
//        resultScoreintent.putExtra("NEW_SCORE", playerOne);
//        resultScoreintent.putExtra("Balls", balls);
        if (playerdata.getPlayer_teamB().equals(teamnameB)) {
            dbhandler.updateScoreTeamB(new TeamBModel(playerdata.getIdB(), "", playerOne, "", 1, 0, ""));
        } else {
            dbhandler.updateScore(new ModelPlayer(playerdata.getIdB(), "", playerOne, "", 1, 0, ""));
        }

//        setResult(Activity.RESULT_OK, resultScoreintent);
        finish();
    }

    private void displayScoreAddition(int playerOne, int balls) {

        ballsCount.setText("( " + balls + " )");
        scorePlayerOne.setText("" + playerOne);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //Write your logic here
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void makeSingletwo(View view) {
        playerNon_Stricker = playerNon_Stricker + 1;
        balls_Non_Stricker = ++balls_Non_Stricker;
        displayScoreAdditiontwo(playerNon_Stricker, balls_Non_Stricker);
    }

    private void displayScoreAdditiontwo(int playerNon_stricker, int balls_non_stricker) {

        txt_score.setText("" + playerNon_stricker);
        txt_balls.setText("" + balls_non_stricker);

    }

    public void makeDoubletwo(View view) {
        playerNon_Stricker = playerNon_Stricker + 2;
        balls_Non_Stricker = ++balls_Non_Stricker;
        displayScoreAdditiontwo(playerNon_Stricker, balls_Non_Stricker);
    }

    public void makeTriplestwo(View view) {
        playerNon_Stricker = playerNon_Stricker + 3;
        balls_Non_Stricker = ++balls_Non_Stricker;
        displayScoreAdditiontwo(playerNon_Stricker, balls_Non_Stricker);
    }

    public void hitFourtwo(View view) {
        playerNon_Stricker = playerNon_Stricker + 4;
        balls_Non_Stricker = ++balls_Non_Stricker;
        displayScoreAdditiontwo(playerNon_Stricker, balls_Non_Stricker);
    }

    public void hitSixtwo(View view) {
        playerNon_Stricker = playerNon_Stricker + 6;
        balls_Non_Stricker = ++balls_Non_Stricker;
        displayScoreAdditiontwo(playerNon_Stricker, balls_Non_Stricker);
    }

    public void dotBallTwo(View view) {
        balls_Non_Stricker = ++balls_Non_Stricker;
        displayScoreAdditiontwo(playerNon_Stricker, balls_Non_Stricker);
    }

    public void wideBallTwo(View view) {
        Toast.makeText(this, "no Action", Toast.LENGTH_SHORT).show();
    }

    public void hitWicketstwo(View view) {

        //for Updaing other player score.
        SharedPreferences preferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
        String teamnameA = preferences.getString("TEAMA", null);
        String teamnameB = preferences.getString("TEAMB", null);

        if (listPlayers.get(0).getPlayer_teamB().equals(teamnameB)) {
            dbhandler.updateScoreTeamB(new TeamBModel(listPlayers.get(0).getIdB(), "", Integer.toString(playerOne), "", 0, 0, ""));
        } else {
            dbhandler.updateScore(new ModelPlayer(listPlayers.get(0).getIdB(), "", Integer.toString(playerOne), "", 0, 0, ""));
        }

        callIntent(listPlayers.get(1), Integer.toString(playerNon_Stricker));

    }
}

