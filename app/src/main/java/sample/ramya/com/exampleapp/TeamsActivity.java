package sample.ramya.com.exampleapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TeamsActivity extends AppCompatActivity {
    EditText teamA, teamB;
    Button submitTeams;
    private Boolean GAMESTATUS = false;
    SharedPreferences preferences;
    String teamAname,teamBname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);
        teamA = (EditText) findViewById(R.id.edt_team_ind);
        teamB = (EditText) findViewById(R.id.edt_team_pak);
        submitTeams = (Button) findViewById(R.id.btn_submit_teams);

        submitTeams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!teamA.getText().toString().equals("") && !teamB.getText().toString().equals("")) {
                    teamAname=teamA.getText().toString();
                    teamBname=teamB.getText().toString();
                    preferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("GAMESTATUS",true);
                    editor.putString("TEAMA",teamAname);
                    editor.putString("TEAMB",teamBname);
                    editor.commit();
                    Log.d("@#!#@",teamAname + "" + teamBname);

                    Intent intent = new Intent(TeamsActivity.this, ScoreDetails.class);
                    intent.putExtra("TEAM1", teamA.getText().toString());
                    intent.putExtra("TEAM2", teamB.getText().toString());
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "enter Team-A and Team-B", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
