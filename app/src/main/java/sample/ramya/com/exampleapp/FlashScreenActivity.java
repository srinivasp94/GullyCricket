package sample.ramya.com.exampleapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FlashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_screen);
        Thread t = new Thread() {
            public void run() {

                try {

                    sleep(2000);
                    finish();
                    SharedPreferences preferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
                    boolean hasLoggedIn = preferences.getBoolean("GAMESTATUS", false);
                    String a=preferences.getString("TEAMA",null);
                    String b=preferences.getString("TEAMB",null);
                    /*if (preferences.getBoolean("GAMESTATUS", false)) {
                        Intent cv = new Intent(FlashScreenActivity.this, TeamsActivity.class*//*otherclass*//*);
                        startActivity(cv);
                        finish();
                    }*/
                    if (hasLoggedIn) {
                        Intent cv = new Intent(FlashScreenActivity.this, ScoreDetails.class/*otherclass*/);
                        startActivity(cv);
                        finish();
                    } else {
                        Intent intent = new Intent(FlashScreenActivity.this, TeamsActivity.class);
                        startActivity(intent);
                        finish();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();

    }
}
