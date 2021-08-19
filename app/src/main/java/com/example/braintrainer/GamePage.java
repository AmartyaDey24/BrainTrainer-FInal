package com.example.braintrainer;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class GamePage extends AppCompatActivity {

    TextView timer,score,question,results;
    Button option0,option1,option2,option3,restart;
    ImageButton exit;
    CountDownTimer countDownTimer;

    int IntScore = 0;
    int numOfQuestion = 0;

    int correctAnswer;
    ArrayList<Integer> answers = new ArrayList<Integer>();

    public void playAgain(View view){
        restart.setVisibility(View.INVISIBLE);
        results.setVisibility(View.INVISIBLE);
        newQuestion();
        IntScore = 0;
        numOfQuestion = 0;
        score.setText("0/0");
        countDownTimer.start();

    }

    public void chooseOption(View view){
        if (Integer.toString(correctAnswer).equals(view.getTag().toString())){
            results.setVisibility(View.VISIBLE);
            results.setText("CORRECT!");
            IntScore++;
        } else {
            results.setVisibility(View.VISIBLE);
            results.setText("WRONG!!");
        }
        numOfQuestion++;
        score.setText(Integer.toString(IntScore) + "/" + Integer.toString(numOfQuestion));
        newQuestion();
    }

    public void newQuestion() {

        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        question.setText(Integer.toString(a) + " + " + Integer.toString(b));

        correctAnswer = rand.nextInt(4);

        answers.clear();

        for (int i=0;i<4;i++){
            if (i == correctAnswer) {
                answers.add(a+b);
            }
            else {
                int wrongAnswer = rand.nextInt(41);

                while (wrongAnswer == a+b) {
                    wrongAnswer = rand.nextInt(41);
                }
                answers.add(wrongAnswer);
            }

        }
        option0.setText(Integer.toString(answers.get(0)));
        option1.setText(Integer.toString(answers.get(1)));
        option2.setText(Integer.toString(answers.get(2)));
        option3.setText(Integer.toString(answers.get(3)));
    }

    public void exit(View view){
        Intent intentExit = new Intent(this,MainActivity.class);
        startActivity(intentExit);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page);

        timer = (TextView) findViewById(R.id.timer);
        score = (TextView) findViewById(R.id.score);
        question = (TextView) findViewById(R.id.question);
        results = (TextView) findViewById(R.id.results);

        option0 = (Button) findViewById(R.id.option1);
        option1 = (Button) findViewById(R.id.option2);
        option2 = (Button) findViewById(R.id.option3);
        option3 = (Button) findViewById(R.id.option4);

        restart = (Button) findViewById(R.id.restart);
        exit = (ImageButton) findViewById(R.id.exit);

        newQuestion();

        countDownTimer = new CountDownTimer(31000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf(millisUntilFinished / 1000 + "s"));
                option0.setEnabled(true);
                option1.setEnabled(true);
                option2.setEnabled(true);
                option3.setEnabled(true);
            }

            @Override
            public void onFinish() {
                results.setVisibility(View.VISIBLE);
                results.setText("Times UP!");
                restart.setVisibility(View.VISIBLE);
                restart.setText("Play Again !!");
                option0.setEnabled(false);
                option1.setEnabled(false);
                option2.setEnabled(false);
                option3.setEnabled(false);
            }
        }.start();
    }
}