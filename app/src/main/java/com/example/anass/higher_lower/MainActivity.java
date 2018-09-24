package com.example.anass.higher_lower;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.support.design.widget.Snackbar;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private int currentScore;
    private int highScore;
    private int previousRoll;

    View view;
    ArrayAdapter<String> diceRollsAdapter;
    ImageView diceImage;
    Button lowerButton;
    Button higherButton;
    ListView mListView;
    TextView scoreNumber;
    TextView highScoreNumber;


    private Dice dice = new Dice();
    private ArrayList<String> diceRolls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view   = findViewById(R.id.main_layout_id);
        diceImage   = (ImageView) findViewById(R.id.imageView);
        scoreNumber = (TextView) findViewById(R.id.scoreNumber);
        highScoreNumber = (TextView) findViewById(R.id.highScoreNumber);
        lowerButton = (Button) findViewById(R.id.buttonLower);

        lowerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                previousRoll = dice.getCurrentRoll();
                diceRollAction();
                scoreHandler(previousRoll,dice.getCurrentRoll(),"Lower");

            }
        });

        higherButton = (Button) findViewById(R.id.buttonHigher);
        higherButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                previousRoll = dice.getCurrentRoll();
                diceRollAction();
                scoreHandler(previousRoll,dice.getCurrentRoll(),"Higher");
            }
        });


        diceRollAction();

    }

    public void updateUI(){
        diceRollsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,diceRolls);
        mListView = findViewById(R.id.ScoreList);
        mListView.setAdapter(diceRollsAdapter);
    }


    public int getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    private String getPreviousDiceRoll(){
        return diceRolls.get(diceRolls.size() -1);
    }

    private void diceRollAction(){
        dice.diceRoll();
        diceRolls.add("Throw is: " + dice.getCurrentRoll());
        diceImageSetter(dice.getCurrentRoll());
        updateUI();
    }

    private void diceImageSetter(int number){
        switch (number){
            case 1:
                diceImage.setImageResource(R.drawable.d1);
                break;
            case 2:
                diceImage.setImageResource(R.drawable.d2);
                break;
            case 3:
                diceImage.setImageResource(R.drawable.d3);
                break;
            case 4:
                diceImage.setImageResource(R.drawable.d4);
                break;
            case 5:
                diceImage.setImageResource(R.drawable.d5);
                break;
            case 6:
                diceImage.setImageResource(R.drawable.d6);
                break;
        }
    }


    private void scoreHandler(int previousRoll, int currentRoll, String type){

        if(type == "Higher"){
            if(previousRoll < currentRoll){
                currentScore++;
                showSnackbar(view,"You Win!", Snackbar.LENGTH_SHORT);
            }else if(previousRoll > currentRoll){
                currentScore = 0;
                showSnackbar(view,"You Lose!", Snackbar.LENGTH_SHORT);
            }else{
                showSnackbar(view,"Draw, try again!", Snackbar.LENGTH_SHORT);
            }
        }else if(type == "Lower"){
            if(previousRoll > currentRoll){
                currentScore++;
                showSnackbar(view,"You Win!", Snackbar.LENGTH_SHORT);
            }
            else if(previousRoll < currentRoll){
                currentScore = 0;
                showSnackbar(view,"You Lose!", Snackbar.LENGTH_SHORT);
            }else{
                showSnackbar(view,"Draw, try again!", Snackbar.LENGTH_SHORT);
            }
        }

        if(currentScore > highScore){
            highScore = currentScore;
            highScoreNumber.setText("" + highScore);
        }

        scoreNumber.setText("" + currentScore);
    }

    public void showSnackbar(View view, String message, int duration)
    {
        Snackbar.make(view, message, duration).show();
    }



}
