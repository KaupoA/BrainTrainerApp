package com.kast.braintrainerapp;

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

    Button startButton;
    ArrayList<Integer> answers = new ArrayList<>();
    int locationOfCorrectAnswer;
    TextView timerText;
    TextView calculationText;
    CountDownTimer countDownTimer;

    public void timer (View view) {



    }

    public void start (View view) {

        startButton.setVisibility(View.INVISIBLE);

        final int seconds = 30;
        final String secondString = Integer.toString(seconds);

        countDownTimer = new CountDownTimer(30000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

            }
        }.start();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button)findViewById(R.id.startButton);
        timerText = (TextView)findViewById(R.id.timerText);
        Button button0 = (Button)findViewById(R.id.button0);
        Button button1 = (Button)findViewById(R.id.button1);
        Button button2 = (Button)findViewById(R.id.button2);
        Button button3 = (Button)findViewById(R.id.button3);
        calculationText = (TextView)findViewById(R.id.calculationText);

        Random rand = new Random();

        // Generate 2 random numbers from 0 to 20.
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        calculationText.setText(Integer.toString(a) + " + " + Integer.toString(b)); // Set the calculationText to random numbers.

        locationOfCorrectAnswer = rand.nextInt(4); // Random location from 0 to 3;

        int incorrectAnswer;

        // For loop for 1 correct answer and for 3 wrong answers.
        for (int i=0; i<4; i++) {

            if (i == locationOfCorrectAnswer) {

                answers.add(a + b); // The sum on a + b.

            } else {

                incorrectAnswer = rand.nextInt(41); // Random number from 0 to 40.

                // While loop to loop until incorrectAnswer isn't equal to the correct answer.
                while (incorrectAnswer == a + b) {

                    incorrectAnswer = rand.nextInt(41);

                }

                answers.add(incorrectAnswer);

            }

        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }
}
