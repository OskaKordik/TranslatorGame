package com.study.translatorgame;

import java.util.ArrayList;

public class Word {
    private String name;
    private ArrayList<String> translation;

    public Word(String name, ArrayList<String> translation) {
        this.name = name;
        this.translation = translation;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getTranslation() {
        return translation;
    }

    public boolean isHasTranslation(String s) {
        if (translation == null) return false;
        else return (translation.contains(s));
    }

    @Override
    public String toString() {
        return "Word{" +
                "name='" + name + '\'' +
                ", translation=" + translation +
                '}';
    }
}
