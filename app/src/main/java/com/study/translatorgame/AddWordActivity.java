package com.study.translatorgame;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class AddWordActivity extends AppCompatActivity {
    private EditText editTextWord;
    private EditText editTextTranslate;
    private String word;
    private String translate;
    private SQLiteDatabase database;
    private SQLiteOpenHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editTextWord = findViewById(R.id.editTextWord);
        editTextTranslate = findViewById(R.id.editTextTranslate);

        dbHelper = new DBWordsHelper(this);
        try {
            database = dbHelper.getWritableDatabase();
        } catch (SQLiteException e) {
            Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickSaveWord(View view) {
        word = editTextWord.getText().toString().trim();
        translate = editTextTranslate.getText().toString();
        ArrayList<Word> wordsList = WordsActivity.words;
        boolean isExists = false;
        for (Word w: wordsList) {
            if (w.getName().equals(word)) isExists = true;
        }
        if (isExists) {
            //проверка на существование слова в словаре
            Toast.makeText(getApplication(), "Слово уже существует!", Toast.LENGTH_SHORT).show();
        } else if (!word.isEmpty() && !translate.isEmpty()) {
            //добавление слова в базу
            database.insert(DBWordsContract.WordsEntry.TABLE_NAME, null, getWordValues(word, translate));
            startActivity(new Intent(this, WordsActivity.class));
        } else {
            Toast.makeText(getApplication(), "Данные не заполнены!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private ContentValues getWordValues(String word, String translation) {
        ContentValues wordValues = new ContentValues();
        wordValues.put(DBWordsContract.WordsEntry.COLUMN_WORD, word);
        wordValues.put(DBWordsContract.WordsEntry.COLUMN_TRANSLATION, translation);
        return wordValues;
    }

    /**
     * Метод запускает интент для запуска активити google translate
     * Если приложение не установлено, высвечивается сообщение
     *
     * @param view
     */
    public void onClickSearchTranslate(View view) {
        word = editTextWord.getText().toString().trim();
        if (!word.isEmpty()) {
            try {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, word);
                intent.putExtra("key_text_input", word);
                intent.putExtra("key_text_output", "");
                intent.putExtra("key_language_from", "en");
                intent.putExtra("key_language_to", "ru");
                intent.putExtra("key_suggest_translation", "");
                intent.putExtra("key_from_floating_window", false);
                intent.setComponent(new ComponentName(
                        "com.google.android.apps.translate",
                        "com.google.android.apps.translate.TranslateActivity"));
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(getApplication(), "Sorry, No Google Translation Installed",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplication(), "Данные не заполнены!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        database.close();
    }
}
