package com.study.translatorgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private TextView textViewQuestion;
    private TextView textViewTimer;
    private TextView textViewScore;
    private TextView textViewOpinion0;
    private TextView textViewOpinion1;
    private TextView textViewOpinion2;
    private TextView textViewOpinion3;
    private ArrayList<TextView> opinions;

    ArrayList<Word> words;

    private String question;
    private String rightAnswer;
    private int rightAnswerPosition;
    private int indexQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewQuestion = findViewById(R.id.textViewQuestion);
        textViewTimer = findViewById(R.id.textViewTimer);
        textViewScore = findViewById(R.id.textViewScore);
        textViewOpinion0 = findViewById(R.id.textViewOpinion0);
        textViewOpinion1 = findViewById(R.id.textViewOpinion1);
        textViewOpinion2 = findViewById(R.id.textViewOpinion2);
        textViewOpinion3 = findViewById(R.id.textViewOpinion3);
        opinions = new ArrayList<>(Arrays.asList(textViewOpinion0, textViewOpinion1, textViewOpinion2, textViewOpinion3));

        words = createWords();
//        for (Word w : words) {
//            Log.i("MyInfo", w.toString());
//        }
        playGame();
    }

    public ArrayList<Word> createWords() {
        ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("Game", new ArrayList<String>(Arrays.asList("игра"))));
        words.add(new Word("Action", new ArrayList<String>(Arrays.asList("действие", "поступок"))));
        words.add(new Word("Temp", new ArrayList<String>(Arrays.asList("температура", "работать временно"))));
        words.add(new Word("Study", new ArrayList<String>(Arrays.asList("изучение", "исследование"))));
        words.add(new Word("Brain", new ArrayList<String>(Arrays.asList("мозг"))));
        words.add(new Word("Constrain", new ArrayList<String>(Arrays.asList("сдерживать", "ограничивать"))));
        words.add(new Word("Support", new ArrayList<String>(Arrays.asList("поддержка"))));
        words.add(new Word("Provide", new ArrayList<String>(Arrays.asList("предоставлять"))));
        words.add(new Word("Compatibility", new ArrayList<String>(Arrays.asList("совместимость"))));
        words.add(new Word("Convenience", new ArrayList<String>(Arrays.asList("удобство", "комфорт", "выгода"))));
        return words;
    }


    public void onClickPlay(View view) {
        playGame();
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
    }

    private String generateQuestion() {
        indexQuestion = (int) (Math.random() * words.size());
        return question = words.get(indexQuestion).getName();
    }

}
