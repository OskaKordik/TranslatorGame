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

    public String getTranslation() {
        StringBuilder translateString = new StringBuilder();
        for (int i =0; i < translation.size(); i++) {
            translateString.append(translation.get(i));
            if (i < (translation.size() - 1)) translateString.append(",\n");
        }
        return translateString.toString();
    }

    public String getRandomTranslation() {
        return translation.get((int) (Math.random() * translation.size()));
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
