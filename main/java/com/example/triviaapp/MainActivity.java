//Author: Émilie Fortin

package com.example.triviaapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

public class MainActivity extends AppCompatActivity {

   Button quitButton, startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializes the quit Button
        //When clicked it causes the app to close gracefully
        quitButton = (Button) findViewById(R.id.quitButton);
        quitButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                System.exit(0);
            }
        });

        //Initializes the start Button
        //When clicked it causes the app to go to the quiz activity
        startButton = (Button) findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startQuiz();
            }
        });

    }

    //Method that creates an intent to go to the question class
    //Then starts that activity
    public void startQuiz(){
        Intent intent = new Intent(this, Question.class);
        startActivity(intent);
    }
}
