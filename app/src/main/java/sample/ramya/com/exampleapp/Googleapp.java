package sample.ramya.com.exampleapp;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.icu.text.NumberFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Googleapp extends AppCompatActivity {

    int numberOfCoffee = 0;
    int footballScoreTeamA = 0;
    int footballScoreTeamB = 0;
    TextView scoreA, scoreB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_googleapp);


    }

    public void verify(int footballScoreTeamA, int footballScoreTeamB) {
        if (footballScoreTeamA >= footballScoreTeamB) {

            scoreA.setTextColor(Color.parseColor("#0e1599"));
            scoreB.setTextColor(Color.parseColor("#7e0202"));

        }
        if (footballScoreTeamA <= footballScoreTeamB) {
            scoreA.setTextColor(Color.parseColor("#7e0202"));
            scoreB.setTextColor(Color.parseColor("#0e1599"));
        } else {
            scoreA.setTextColor(Color.parseColor("#235c13"));
            scoreB.setTextColor(Color.parseColor("#235c13"));
        }

    }

    public void submitOrder(View view) {
        display(numberOfCoffee);

        String priceMessage = "Today's special:" + 23 + "jack";
        displayprice(priceMessage);
    }

    public void incriment(View view) {

        numberOfCoffee = numberOfCoffee + 1;
        display(numberOfCoffee);

    }

    public void decriment(View view) {
        numberOfCoffee = numberOfCoffee - 1;
        display(numberOfCoffee);
    }

    public void pointsThree(View view) {
        footballScoreTeamA = footballScoreTeamA + 3;
        showScoreTeamA(footballScoreTeamA);
        /*verify(footballScoreTeamA,footballScoreTeamB);*/
    }

    public void pointsTwo(View view) {
        footballScoreTeamA = footballScoreTeamA + 2;
        showScoreTeamA(footballScoreTeamA);
        /*verify(footballScoreTeamA,footballScoreTeamB);*/

    }

    public void pointsFree(View view) {
        footballScoreTeamA = footballScoreTeamA + 1;
        showScoreTeamA(footballScoreTeamA);
        /*verify(footballScoreTeamA,footballScoreTeamB);*/
    }

    private void showScoreTeamA(int footballScoreTeamA) {
        scoreA = (TextView) findViewById(R.id.tv_score_team_a);
        scoreA.setText("" + footballScoreTeamA);
        /*verify(footballScoreTeamA,footballScoreTeamB);*/

    }

    public void pointsThreeTeamB(View view) {
        footballScoreTeamB = footballScoreTeamB + 3;
        showScoreTeamB(footballScoreTeamB);
        /*verify(footballScoreTeamA,footballScoreTeamB);*/
    }

    public void pointsTwoTeamB(View view) {
        footballScoreTeamB = footballScoreTeamB + 2;
        showScoreTeamB(footballScoreTeamB);
        /*verify(footballScoreTeamA,footballScoreTeamB);*/
    }

    public void pointsFreeBTeamB(View view) {
        footballScoreTeamB = footballScoreTeamB + 1;
        showScoreTeamB(footballScoreTeamB);
        /*verify(footballScoreTeamA,footballScoreTeamB);*/
    }

    private void showScoreTeamB(int footballScoreTeamB) {
        scoreB = (TextView) findViewById(R.id.tv_score_team_b);
        scoreB.setText(" " + footballScoreTeamB);

    }
    public void reset(View view){
        footballScoreTeamA=0;
        footballScoreTeamB=0;
        scoreA.setText("" + footballScoreTeamA);
        scoreB.setText("" + footballScoreTeamB);

    }


    private void displayprice(String i) {
        TextView priceNumber = (TextView) findViewById(R.id.tv_price_cofee);
//        priceNumber.setText(NumberFormat.getCurrencyInstance().format(i));
        priceNumber.setText(i);
    }

    public void display(int i) {
        TextView QuantityNumber = (TextView) findViewById(R.id.tv_quantity_cofee);
        QuantityNumber.setText("" + i);
    }
}
