package com.example.cis436_proj1;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Player playerOne = new Player(0);
    Player playerTwo = new Player(0);
    String imgDice[] = new String[]{"one","two","three","four","five","six"};
    int roundScore = 0;
    int currentTurn = 1;
    TextView currentPlayer;
    TextView playerOneScore;
    TextView playerTwoScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentPlayer = (TextView) findViewById(R.id.currentTurn);
        playerOneScore = (TextView) findViewById(R.id.playerOneScore);
        playerTwoScore = (TextView) findViewById(R.id.playerTwoScore);
    }
    public int nextTurn(int turn)
    {
        roundScore = 0;
        if (turn == 1)
        {
            turn = 2;
            return turn;
        }
        else{
            turn = 1;
            return turn;
        }
    }
    public void holdScore(View view) {

    if (currentTurn == 1)
    {
        playerOne.setScore(roundScore);
        playerOneScore.setText(""+playerOne.getScore());
        Log.d("SCORE","VALUE :"+ playerOne.getScore());
        currentTurn = this.nextTurn(currentTurn);
        currentPlayer.setText("Player" + currentTurn);
        return;
    }
    else
    {
        playerTwo.setScore(roundScore);
        playerTwoScore.setText(""+playerTwo.getScore());
        currentTurn = this.nextTurn(currentTurn);
        currentPlayer.setText("Player" + currentTurn);
        return;
    }

    }
    public void rollDice(View view){
        ImageView diceOneImg =(ImageView) findViewById(R.id.diceSlotOne);
        ImageView diceTwoImg = (ImageView) findViewById(R.id.diceSlotTwo);
        Button holdBtn = (Button) findViewById(R.id.holdBtn);
        holdBtn.setEnabled(true);

        int firstRoll = new Random().nextInt(7-1)+1;
        int secondRoll = new Random().nextInt(7-1)+1;
        int firstRollID = getResources().getIdentifier(imgDice[firstRoll-1], "drawable", getPackageName());
        int secondRollID = getResources().getIdentifier(imgDice[secondRoll-1], "drawable", getPackageName());
        diceOneImg.setImageResource(firstRollID);
        diceTwoImg.setImageResource(secondRollID);
        if(firstRoll ==1&& secondRoll==1)
        {
            if (currentTurn == 1)
            {
                playerOne.resetScore();
                playerOneScore.setText(""+playerOne.getScore());
            }
            else
            {
                playerTwo.resetScore();
                playerTwoScore.setText(""+playerTwo.getScore());
            }
            currentTurn = this.nextTurn(currentTurn);
            currentPlayer.setText("Player" + currentTurn);
            Log.d("TEMP","Value: DOUBLE 1's");
            return;
        }
        if (firstRoll ==1|| secondRoll==1)
        {
            currentTurn = this.nextTurn(currentTurn);
            currentPlayer.setText("Player" + currentTurn);
            Log.d("TEMP","Value: ONE 1's");
            return;

        }

        if (firstRoll == secondRoll)
        {

            roundScore += secondRoll+firstRoll;

            holdBtn.setEnabled(false);
            return;
        }
        roundScore += firstRoll + secondRoll;
        Log.d("FIRSTROLL","Value: "+firstRoll);
        Log.d("SECONDROLL","Value: "+secondRoll);



    }
}