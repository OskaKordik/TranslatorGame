package com.study.translatorgame;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;

public class WordsActivity extends AppCompatActivity {
    private RecyclerView recyclerViewWords;
    public static final ArrayList<Word> words = new ArrayList<>();
    public static final ArrayList<Word> wordsFromDB = new ArrayList<>();
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerViewWords = findViewById(R.id.recyclerViewWords);

        SQLiteOpenHelper dbHelper = new DBWordsHelper(this);

        try {
            database = dbHelper.getWritableDatabase();
            createWords();
        } catch (SQLiteException e) {
            Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT).show();
        }


        WordsAdapter adapter = new WordsAdapter(wordsFromDB);
        recyclerViewWords.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewWords.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_start_game) {
            startActivity(new Intent(this, GameActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void createWords() {
//        if (words.isEmpty()) {
//            words.add(new Word("Game", new ArrayList<String>(Arrays.asList("игра"))));
//            words.add(new Word("Action", new ArrayList<String>(Arrays.asList("действие", "поступок"))));
//            words.add(new Word("Temp", new ArrayList<String>(Arrays.asList("температура", "работать временно"))));
//            words.add(new Word("Study", new ArrayList<String>(Arrays.asList("изучение", "исследование"))));
//            words.add(new Word("Brain", new ArrayList<String>(Arrays.asList("мозг"))));
//            words.add(new Word("Constrain", new ArrayList<String>(Arrays.asList("сдерживать", "ограничивать"))));
//            words.add(new Word("Support", new ArrayList<String>(Arrays.asList("поддержка"))));
//            words.add(new Word("Provide", new ArrayList<String>(Arrays.asList("предоставлять"))));
//            words.add(new Word("Compatibility", new ArrayList<String>(Arrays.asList("совместимость"))));
//            words.add(new Word("Convenience", new ArrayList<String>(Arrays.asList("удобство", "комфорт", "выгода"))));
//        }
//        for (Word word: words) {
//            database.insert(DBWordsContract.WordsEntry.TABLE_NAME, null, getWordValues(word.getName(), word.getTranslation()));
//        }

        Cursor cursor = database.query(DBWordsContract.WordsEntry.TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String word = cursor.getString(cursor.getColumnIndex(DBWordsContract.WordsEntry.COLUMN_WORD));
            String translation = cursor.getString(cursor.getColumnIndex(DBWordsContract.WordsEntry.COLUMN_TRANSLATION));
            wordsFromDB.add(new Word(word, new ArrayList<String>(Arrays.asList(translation.split(", ")))));
        }
        cursor.close();
    }

    private ContentValues getWordValues(String word, String translation) {
        ContentValues wordValues = new ContentValues();
        wordValues.put(DBWordsContract.WordsEntry.COLUMN_WORD, word);
        wordValues.put(DBWordsContract.WordsEntry.COLUMN_TRANSLATION, translation);
        return wordValues;
    }

    public void onClickAddWord(View view) {
        startActivity(new Intent(this, AddWordActivity.class));
    }
}
