package com.study.translatorgame;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class AddWordActivity extends AppCompatActivity {
    private EditText editTextWord;
    private EditText editTextTranslate;
    private String word;
    private String[] translate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setContentView(R.layout.activity_add_word);
        editTextWord = findViewById(R.id.editTextWord);
        editTextTranslate = findViewById(R.id.editTextTranslate);
    }

    public void onClickSaveWord(View view) {
        word = editTextWord.getText().toString().trim();
        translate = editTextTranslate.getText().toString().split(",");
        if (!word.isEmpty() && (translate.length > 0)) {
            //добавление слова в базу
            WordsActivity.words.add(new Word(word, new ArrayList<>(Arrays.asList(translate))));
            startActivity(new Intent(this, WordsActivity.class));
        } else {
            Toast.makeText(getApplication(), "Данные не заполнены!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Метод запускает интент для запуска активити google translate
     * Если приложение не установлено, высвечивается сообщение
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
}
