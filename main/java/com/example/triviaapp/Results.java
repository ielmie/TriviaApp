//Author: Émilie Fortin

package com.example.triviaapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Results extends AppCompatActivity {

    Button buttonContinue;
    TextView resultScreen;

    int questionNum;
    int numberOfCorrectAnswers;
    int[] questionOrder;
    boolean resultsCorrect;
    String correct = "CORRECT";
    String inCorrect = "INCORRECT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        // retrieves the data transmitted to it when it passes from one activity to the other
        receiveInfoFromIntent();

        //Initializes the textView and Button and assigns the text to each
        resultScreen = (TextView) findViewById(R.id.resultScreen);
        if(resultsCorrect){
            resultScreen.setText(correct);
            resultScreen.setTextColor(Color.parseColor("#27b21a"));
        }
        if(!resultsCorrect){
            resultScreen.setText(inCorrect);
            resultScreen.setTextColor(Color.parseColor("#a70000"));
        }

        for(int i=0;i<questionOrder.length;i++){
        }

        buttonContinue = (Button) findViewById(R.id.buttonContinue);
        buttonContinue.setText("Continue");

        //The code for when button is clicked.
        //The buttons will change the display back to the question page if there have not been 10 questions left
        // or to the main page if 10 questions have been reached
        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(questionNum>=10){
                    goToScore();
                }
                else{
                    startNextQuestion();
                }
            }
        });


    }

    //Method that gets the information from the previous activity
    //and assigns the values to the appropriate variables
    public void receiveInfoFromIntent() {
        Intent intent = getIntent();
        numberOfCorrectAnswers = intent.getIntExtra("numberOfCorrectAnswers",0);
        questionNum = intent.getIntExtra("numberOfQuestions",0);
        questionOrder = intent.getIntArrayExtra("questionNumbers");
        resultsCorrect = intent.getBooleanExtra("isCorrect", true);
    }

    //Method that creates an intent to go to the score page
    // adds Extras with the information necessary
    //then starts that activity
    public void goToScore(){
        Intent in = new Intent(this, Score.class);
        in.putExtra("numberOfCorrect", numberOfCorrectAnswers);
        startActivity(in);
    }

    //Method that creates an intent to go to the next question activity
    //adds Extras with the information necessary
    //then starts that activity
    public void startNextQuestion(){
        Intent intents = new Intent(this, Question.class);
        intents.putExtra("numberOfQuestions", questionNum);
        intents.putExtra("numberOfCorrectAnswers",numberOfCorrectAnswers);
        intents.putExtra("questionNumbers", questionOrder);
        startActivity(intents);
    }

}