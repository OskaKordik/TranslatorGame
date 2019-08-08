package com.study.translatorgame;

import android.provider.BaseColumns;

public class DBWordsContract {
    public static final class WordsEntry implements BaseColumns {
        public static final String TABLE_NAME = "words";
        public static final String COLUMN_WORD = "word";
        public static final String COLUMN_TRANSLATION = "translation";

        public static final String COMMAND_CREATE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                                    + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_WORD + " TEXT, "
                                    + COLUMN_TRANSLATION + " TEXT);";
        public static final String COMMAND_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
