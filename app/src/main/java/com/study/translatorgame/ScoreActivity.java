package com.study.translatorgame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {
    TextView textResultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        textResultView = findViewById(R.id.textViewResult);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        int max = preferences.getInt("max", 0);

        int result = getIntent().getIntExtra("result", 0);
        textResultView.setText("Ваш результат: " + result + "\nМаксимальный результат: " + max);
    }

    public void startNewGame(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
}
