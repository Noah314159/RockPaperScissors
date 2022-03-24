package com.example.rockpaperscissors;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //rng for opponents symbol choice
    Random rand = new Random();

    //string constants for the possible round outcomes
    private static final String WIN = "WON";
    private static final String LOSE = "LOST";
    private static final String DRAW = "DRAW";

    //constants for which int represents which symbol
    private static final int ROCK = 1;
    private static final int PAPER = 2;
    private static final int SCISSOR = 3;

    //variable tracks which symbol the player has chosen
    private int playerSelection = 0;

    //variables for the opponenet and player scores
    private int playerScore = 0;
    private int opponentScore = 0;

    //variables for played images
    private ImageView opponentSymbol;
    private ImageView playerSymbol;

    //variables for scores and round result
    private TextView opponentScoreText;
    private TextView playerScoreText;
    private TextView roundResultText;

    //variables for all the buttons
    private ImageButton paper_button;
    private ImageButton rock_button;
    private ImageButton scissor_button;
    private Button play_button;
    private Button reset_button;


    //get colors as ints
    private int grey;
    private int lightGrey;
    private int darkGrey;
    private int accent;
    private int blue;
    private int red;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //variables for the buttons the player will select
        paper_button = findViewById(R.id.paper_button);
        rock_button = findViewById(R.id.rock_button);
        scissor_button = findViewById(R.id.scissor_button);
        play_button = findViewById(R.id.play_button);
        reset_button = findViewById(R.id.reset_button);

        //set on click listeners
        paper_button.setOnClickListener(this);
        rock_button.setOnClickListener(this);
        scissor_button.setOnClickListener(this);
        play_button.setOnClickListener(this);
        reset_button.setOnClickListener(this);


        //set color variables
        grey = getResources().getColor(R.color.grey);
        lightGrey = getResources().getColor(R.color.lightGrey);
        darkGrey = getResources().getColor(R.color.darkGrey);
        accent = getResources().getColor(R.color.accent);
        red = getResources().getColor(R.color.red);
        blue = getResources().getColor(R.color.blue);

        //set textView variables
        opponentScoreText = findViewById(R.id.opponentScore_text);
        playerScoreText = findViewById(R.id.playerScore_text);
        roundResultText = findViewById(R.id.result_text);

        //set image variables
        opponentSymbol = findViewById(R.id.opponentSymbol_image);
        playerSymbol = findViewById(R.id.playerSymbol_image);

    }

    @Override
    public void onClick(View view) {
        //id of the button pressed
        int button_id = view.getId();

        if (button_id == R.id.paper_button){
            //if button is already selected, deselect it, else select it
            if (playerSelection == PAPER){
                paper_button.setBackgroundColor(lightGrey);
                rock_button.setBackgroundColor(lightGrey);
                scissor_button.setBackgroundColor(lightGrey);
                playerSelection = 0;
            } else {
                paper_button.setBackgroundColor(grey);
                rock_button.setBackgroundColor(lightGrey);
                scissor_button.setBackgroundColor(lightGrey);
                playerSelection = PAPER;
            }
        } else if (button_id == R.id.scissor_button){
            //if button is already selected, deselect it, else select it
            if (playerSelection == SCISSOR){
                scissor_button.setBackgroundColor(lightGrey);
                paper_button.setBackgroundColor(lightGrey);
                rock_button.setBackgroundColor(lightGrey);
                playerSelection = 0;
            } else {
                scissor_button.setBackgroundColor(grey);
                paper_button.setBackgroundColor(lightGrey);
                rock_button.setBackgroundColor(lightGrey);
                playerSelection = SCISSOR;
            }
        } else if (button_id == R.id.rock_button){
            //if button is already selected, deselect it, else select it
            if (playerSelection == ROCK){
                rock_button.setBackgroundColor(lightGrey);
                scissor_button.setBackgroundColor(lightGrey);
                paper_button.setBackgroundColor(lightGrey);
                playerSelection = 0;
            } else {
                rock_button.setBackgroundColor(grey);
                scissor_button.setBackgroundColor(lightGrey);
                paper_button.setBackgroundColor(lightGrey);
                playerSelection = ROCK;
            }


        } else if (button_id == R.id.play_button){
            //makes sure player has selected a symbol
            if (playerSelection == 0){
                Toast toast = Toast.makeText(this, "Please Choose A Symbol.", Toast.LENGTH_LONG);
                toast.show();
            } else {
                playRound();
            }
        } else if (button_id == R.id.reset_button){
            reset();
        } else {
            Toast toast = Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    /**
     * this is a helper method that plays a round of the game by taking in the players selection as an int,
     * and generates a symbol for the enemy
     * 1 - rock
     * 2 - paper
     * 3 - scissor
     * 0 - unselected
     */
    private void playRound(){

        if (playerSelection == ROCK){
            playerSymbol.setImageResource(R.drawable.rock_icon);
        } else if (playerSelection == PAPER){
            playerSymbol.setImageResource(R.drawable.paper_icon);
        } else if (playerSelection == SCISSOR){
            playerSymbol.setImageResource(R.drawable.scissor_icon);
        }

        int opponentSelection = rand.nextInt(3) + 1;

        if (opponentSelection == ROCK){
            opponentSymbol.setImageResource(R.drawable.rock_icon);

            if (playerSelection == PAPER){
                //win
                playerScore += 1;
                roundResultText.setText(WIN);
                roundResultText.setTextColor(blue);
            } else if (playerSelection == SCISSOR){
                //lose
                opponentScore += 1;
                roundResultText.setText(LOSE);
                roundResultText.setTextColor(red);
            } else {
                //draw
                roundResultText.setText(DRAW);
                roundResultText.setTextColor(accent);
            }
        } else if (opponentSelection == PAPER){
            opponentSymbol.setImageResource(R.drawable.paper_icon);

            if (playerSelection == SCISSOR){
                //win
                playerScore += 1;
                roundResultText.setText(WIN);
                roundResultText.setTextColor(blue);
            } else if (playerSelection == ROCK){
                //lose
                opponentScore += 1;
                roundResultText.setText(LOSE);
                roundResultText.setTextColor(red);
            } else {
                //draw
                roundResultText.setText(DRAW);
                roundResultText.setTextColor(accent);
            }
        } else {
            opponentSymbol.setImageResource(R.drawable.scissor_icon);

            if (playerSelection == ROCK){
                //win
                playerScore += 1;
                roundResultText.setText(WIN);
                roundResultText.setTextColor(blue);
            } else if (playerSelection == PAPER){
                //lose
                opponentScore += 1;
                roundResultText.setText(LOSE);
                roundResultText.setTextColor(red);
            } else {
                //draw
                roundResultText.setText(DRAW);
                roundResultText.setTextColor(accent);
            }
        }

        //sets score textViews to updated values
        opponentScoreText.setText(String.valueOf(opponentScore));
        playerScoreText.setText(String.valueOf(playerScore));
        //resets players symbol choice
        rock_button.setBackgroundColor(lightGrey);
        scissor_button.setBackgroundColor(lightGrey);
        paper_button.setBackgroundColor(lightGrey);
        playerSelection = 0;
    }

    /**
     * helper method that resets the score counters, deselects player symbol choice etc
     */
    private void reset(){
        //deselect the symbol buttons
        rock_button.setBackgroundColor(lightGrey);
        scissor_button.setBackgroundColor(lightGrey);
        paper_button.setBackgroundColor(lightGrey);
        playerSelection = 0;

        //reset scores to 0
        opponentScore = 0;
        playerScore = 0;

        //set score text back to 0
        opponentScoreText.setText(String.valueOf(opponentScore));
        playerScoreText.setText(String.valueOf(playerScore));

        //make symbols invisible
        playerSymbol.setImageResource(android.R.color.transparent);
        opponentSymbol.setImageResource(android.R.color.transparent);

        //reset round result box to empty
        roundResultText.setText("");
        roundResultText.setTextColor(accent);

    }

}