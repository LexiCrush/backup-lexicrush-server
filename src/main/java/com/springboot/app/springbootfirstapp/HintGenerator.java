package com.springboot.app.springbootfirstapp;
import java.util.Locale;

public class HintGenerator {
    public static String makeHint(String question) {
        question = question.toLowerCase(Locale.ENGLISH);
        return "HINT";
    }
}
