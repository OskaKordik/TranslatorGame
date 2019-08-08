package com.study.translatorgame;

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
    public static final ArrayList<Word> wordsFromDB = new ArrayList<>();
    private SQLiteDatabase database;
    private SQLiteOpenHelper dbHelper;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerViewWords = findViewById(R.id.recyclerViewWords);

        dbHelper = new DBWordsHelper(this);

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
            if (wordsFromDB.size() > 4) {
                startActivity(new Intent(this, GameActivity.class));
            } else Toast.makeText(this, "Добавьте более 4 слов!", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void createWords() {
        if (!wordsFromDB.isEmpty()) wordsFromDB.clear();

        cursor = database.query(DBWordsContract.WordsEntry.TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String word = cursor.getString(cursor.getColumnIndex(DBWordsContract.WordsEntry.COLUMN_WORD));
            String translation = cursor.getString(cursor.getColumnIndex(DBWordsContract.WordsEntry.COLUMN_TRANSLATION));

            wordsFromDB.add(new Word(word, new ArrayList<>(Arrays.asList(translation.split("\\s*,\\s*")))));
        }
      }

    public void onClickAddWord(View view) {
        startActivity(new Intent(this, AddWordActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        database.close();
    }
}
