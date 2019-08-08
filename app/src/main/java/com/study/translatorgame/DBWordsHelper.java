package com.study.translatorgame;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBWordsHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "words.db";
    private static final int DB_VERSION = 1;


    public DBWordsHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateDatabase(db, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateDatabase(db, oldVersion, newVersion);
    }

    private static void insertWord(SQLiteDatabase db, String word, String translation) {
        ContentValues wordValues = new ContentValues();
        wordValues.put(DBWordsContract.WordsEntry.COLUMN_WORD, word);
        wordValues.put(DBWordsContract.WordsEntry.COLUMN_TRANSLATION, translation);
        db.insert(DBWordsContract.WordsEntry.TABLE_NAME, null, wordValues);
    }

    private void updateDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {
            db.execSQL(DBWordsContract.WordsEntry.COMMAND_CREATE);

            //заполнение DB
//            insertWord(db, "game", "игра");
//            insertWord(db, "study", "изучение, исследование");
        }
        if (newVersion > 1) {
            //код добавления нового столбца
        }
    }
}
