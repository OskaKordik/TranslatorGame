package com.study.translatorgame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class GameActivity extends AppCompatActivity {
    private TextView textViewQuestion;
    private TextView textViewTimer;
    private TextView textViewScore;
    private TextView textViewOpinion0;
    private TextView textViewOpinion1;
    private TextView textViewOpinion2;
    private TextView textViewOpinion3;
    private ArrayList<TextView> opinions;

    private ArrayList<Word> words;

    private int rightAnswerPosition;
    private int indexQuestion;

    private int countOfRightAnswer;
    private int countOfQuestion;
    private boolean gameOver = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar !=null) actionBar.hide();

        textViewQuestion = findViewById(R.id.textViewQuestion);
        textViewTimer = findViewById(R.id.textViewTimer);
        textViewScore = findViewById(R.id.textViewScore);
        textViewOpinion0 = findViewById(R.id.textViewOpinion0);
        textViewOpinion1 = findViewById(R.id.textViewOpinion1);
        textViewOpinion2 = findViewById(R.id.textViewOpinion2);
        textViewOpinion3 = findViewById(R.id.textViewOpinion3);
        opinions = new ArrayList<>(Arrays.asList(textViewOpinion0, textViewOpinion1, textViewOpinion2, textViewOpinion3));
        words = WordsActivity.words;
        if (!words.isEmpty()) {
            startTimer();
            playGame();
        } else Toast.makeText(this, "Словарь пуст!", Toast.LENGTH_SHORT);
    }

    private void startTimer() {
        CountDownTimer timer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textViewTimer.setText(getTime(millisUntilFinished));
                if (millisUntilFinished < 10000) textViewTimer.setTextColor(getResources().getColor(android.R.color.holo_red_light));
            }

            @Override
            public void onFinish() {
                textViewTimer.setText(getTime(0));
                gameOver = true;

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                int max = preferences.getInt("max", 0);
                if (countOfRightAnswer >= max) preferences.edit().putInt("max", countOfRightAnswer).apply();

                Intent intent = new Intent(GameActivity.this, ScoreActivity.class);
                intent.putExtra("result", countOfRightAnswer);
                startActivity(intent);
            }
        };
        timer.start();
    }

    public void onClickPlay(View view) {
        if (!gameOver) {
            if (rightAnswerPosition == opinions.indexOf((TextView) view)) {
                countOfRightAnswer++;
                textViewQuestion.setTextColor(getResources().getColor(android.R.color.holo_green_light));
//                Toast.makeText(this, "Верно!", Toast.LENGTH_SHORT).show();
            } else {
                textViewQuestion.setTextColor(getResources().getColor(android.R.color.holo_red_light));
//                Toast.makeText(this, "Неверно!", Toast.LENGTH_SHORT).show();
            }
            countOfQuestion++;
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    textViewQuestion.setTextColor(getResources().getColor(android.R.color.white));
                    playGame();
                }
            }, 1000);

        }
    }

    private void playGame() {

        String textTranslation;
        int randomIndex;

        rightAnswerPosition = (int) (Math.random() * opinions.size());
        textViewQuestion.setText(generateQuestion());

        for (int i = 0; i < opinions.size(); i++) {
            if (i == rightAnswerPosition) {
                textTranslation = words.get(indexQuestion).getRandomTranslation();
                opinions.get(i).setText(textTranslation);
            }
            else {
                do {
                    randomIndex = (int) (Math.random() * words.size());
                } while (randomIndex == indexQuestion);
                textTranslation = words.get(randomIndex).getRandomTranslation();
                opinions.get(i).setText(textTranslation);
            }
        }

        textViewScore.setText(countOfRightAnswer + " / " + countOfQuestion);
    }

    private String generateQuestion() {
        indexQuestion = (int) (Math.random() * words.size());
        return words.get(indexQuestion).getName();
    }

    private String getTime(long millis) {
        int seconds = (int) (millis / 1000);
        int minutes = seconds / 60;
        seconds = seconds % 60;
        return String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);
    }
}
