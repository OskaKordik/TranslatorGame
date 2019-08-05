package com.study.translatorgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class WordsActivity extends AppCompatActivity {
    RecyclerView recyclerViewWords;
    private ArrayList<Word> words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);
        recyclerViewWords = findViewById(R.id.recyclerViewWords);
        words = createWords();
        WordsAdapter adapter = new WordsAdapter(words);
        recyclerViewWords.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewWords.setAdapter(adapter);
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
