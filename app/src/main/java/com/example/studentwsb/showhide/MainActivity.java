package com.example.studentwsb.showhide;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    int correctResultLocation;
    ArrayList<Integer> answers = new ArrayList<>();
    TextView answerTextView;
    TextView scoreTextView;
    int score = 0;
    int guesses = 0;
    TextView calculationView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView timerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goButton = findViewById(R.id.goButton);
        calculationView = findViewById(R.id.calculationTextView);

        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        answerTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);
        newQuestion();

        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l/1000)+ "s");
            }

            @Override
            public void onFinish() {
            answerTextView.setText("Timeout");
            }
        }.start();

    }


    public void start(View view) {
        goButton.setVisibility(View.INVISIBLE);
    }

    public void newQuestion(){
        Random random = new Random();
        int firstNumber = random.nextInt(20) + 1;
        int secondNumber = random.nextInt(20) + 1;


        calculationView.setText(Integer.toString(firstNumber) + "+" + Integer.toString(secondNumber));

        correctResultLocation = random.nextInt(4);
        answers.clear();

        for (int i = 0; i < 4; i++) {
            if (i == correctResultLocation) {
                answers.add((firstNumber + secondNumber));
            } else {
                int wrongAnswer = random.nextInt(39) + 2;
                while (wrongAnswer == firstNumber + secondNumber) {
                    wrongAnswer = random.nextInt(39) + 2;
                }
                answers.add(wrongAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }


    public void chooseAnswer(View view) {
        if (Integer.toString(correctResultLocation).equals(view.getTag().toString())) {
            answerTextView.setText("Correct");
            score++;

        } else {
            answerTextView.setText("Incorrect");
        }
        guesses++;
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(guesses));
        newQuestion();
    }
}
