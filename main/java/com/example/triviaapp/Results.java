//Author: Émilie Fortin

package com.example.triviaapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
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
    String correct = "correct";
    String inCorrect = "Incorrect";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        // retrieves the data transmitted to it when it passes from one activity to the other
        Intent intent = getIntent();
        numberOfCorrectAnswers = intent.getIntExtra("numberOfCorrectAnswers",0);
        questionNum = intent.getIntExtra("numberOfQuestions",0);
        questionOrder = intent.getIntArrayExtra("questionNumbers");
        resultsCorrect = intent.getBooleanExtra("isCorrect", true);

        //Initializes the textView and Button and assigns the text to each
        resultScreen = (TextView) findViewById(R.id.resultScreen);
        if(resultsCorrect){
            resultScreen.setText(correct);
        }
        if(!resultsCorrect){
            resultScreen.setText(inCorrect);
        }

        for(int i=0;i<questionOrder.length;i++){
            System.out.println(questionOrder[i]);
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
                    returnToMenu();
                }
                else{
                    startNextQuestion();
                }
            }
        });


    }

    public void returnToMenu(){
        Intent in = new Intent(this, MainActivity.class);
        startActivity(in);
    }

    public void startNextQuestion(){
        Intent intents = new Intent(this, Question.class);
        intents.putExtra("numberOfQuestions", questionNum);
        intents.putExtra("numberOfCorrectAnswers",numberOfCorrectAnswers);
        intents.putExtra("questionNumbers", questionOrder);
        startActivity(intents);
    }

}