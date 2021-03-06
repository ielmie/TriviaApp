package com.example.triviaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Score extends AppCompatActivity {

    Button buttonToContinue;
    TextView scoreScreen;
    int numberOfCorrectAnswers;
    int[] questionOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        // retrieves the data transmitted to it when it passes from one activity to the other
        receiveInfoFromIntent();

        //Initializes the textview
        scoreScreen = (TextView) findViewById(R.id.scoreScreen);
        addScoreToTextView(numberOfCorrectAnswers,questionOrder, scoreScreen);

        //Creates the continue button
        //And the code for when the button is clicked
        //which returns us to the main menu
        buttonToContinue = (Button) findViewById(R.id.buttonToContinue);
        buttonToContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnToMenu();
            }
        });

    }

    //Method that gets the information from the previous activity
    //and assigns the values to the appropriate variables
    public void receiveInfoFromIntent() {
        Intent intent = getIntent();
        numberOfCorrectAnswers = intent.getIntExtra("numberOfCorrect",0);
        questionOrder = intent.getIntArrayExtra("questionNumbers");

    }

    //Method that takes an integer and a Textview as input
    //And assigns the appropriate score text to said textView
    public void addScoreToTextView(int number, int[] array, TextView tv){
        if(number<((array.length/2)+1)){

            tv.setText("You got "+ Integer.toString(number)+"/"+Integer.toString(array.length)+"."+" Better luck next time!");
        }
        else{
            tv.setText("You got "+ Integer.toString(number)+"/"+Integer.toString(array.length)+"."+" Congratulations!");
        }
    }

    //Method that creates an intent to return to the main menu
    //then starts that activity
    public void returnToMenu(){
        Intent in = new Intent(this, MainActivity.class);
        startActivity(in);
        finish();
    }
}