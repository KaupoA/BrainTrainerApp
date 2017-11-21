package com.kast.braintrainerapp;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    TextView rightWrongText;
    TextView timerText;
    TextView scoreText;
    TextView calculationText;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    RelativeLayout gameLayout;

    ArrayList<Integer> answers = new ArrayList<>();
    int locationOfCorrectAnswer;
    int score = 0;
    int questionNumber = 0;

    public void playAgain(View view) { // Play again button appears.

        score = 0;
        questionNumber = 0;

        timerText.setText("30s");
        scoreText.setText("0/0");
        rightWrongText.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);

        // Enable the buttons for a new game.
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);

        generateQuestion();

        CountDownTimer countDownTimer = new CountDownTimer(30100, 1000) { // 30 seconds is 30100, 100 more for the delay, so the timer will work correctly,
            // otherwise it will got to 29 as soon as counter starts.
            @Override
            public void onTick(long millisUntilFinished) {

                timerText.setText(String.valueOf(millisUntilFinished / 1000) + "s");

            }

            @Override
            public void onFinish() {

                // Disable the buttons, so when the timer is finished they're unclickable.
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);

                playAgainButton.setVisibility(View.VISIBLE);
                timerText.setText("0s");
                rightWrongText.setText("You got " + Integer.toString(score) + " out of " + Integer.toString(questionNumber) + ".");

            }
        }.start();

    }

    public void generateQuestion() { // Random question generator.

        Random rand = new Random();

        // Generate 2 random numbers from 0 to 20.
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        calculationText.setText(Integer.toString(a) + " + " + Integer.toString(b)); // Set the calculationText to random numbers.

        locationOfCorrectAnswer = rand.nextInt(4); // Random location from 0 to 3;

        answers.clear(); // Remove the content of the answers array list each time to add new numbers.

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

        // Display random numbers to buttons.
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }

    public void rightAnswer (View view) {

        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {   // If button tag equals locationOfCorrectAnswer then do this.

            score++; // Add 1 to the score
            rightWrongText.setText("Correct!");

        } else {

            rightWrongText.setText("Wrong!");

        }

        questionNumber++; // Add 1 to the questionNumber
        scoreText.setText(Integer.toString(score) + "/" + Integer.toString(questionNumber));
        generateQuestion(); // Generate new random question

    }

    public void start(View view) {

        startButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(RelativeLayout.VISIBLE);

        playAgain(findViewById(R.id.playAgainButton));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button)findViewById(R.id.startButton);
        timerText = (TextView)findViewById(R.id.timerText);
        button0 = (Button)findViewById(R.id.button0);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        rightWrongText = (TextView)findViewById(R.id.rightWrongText);
        scoreText = (TextView)findViewById(R.id.scoreText);
        calculationText = (TextView)findViewById(R.id.calculationText);
        playAgainButton = (Button)findViewById(R.id.playAgainButton);
        gameLayout = (RelativeLayout)findViewById(R.id.gameLayout);

    }
}
