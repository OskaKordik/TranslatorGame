package com.study.translatorgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ArrayList<Word> words = createWords();
//        for (Word w : words) {
//            Log.i("MyInfo", w.toString());
//        }
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
}
