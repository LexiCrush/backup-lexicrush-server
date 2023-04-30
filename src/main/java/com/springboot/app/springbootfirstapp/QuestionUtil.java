package com.springboot.app.springbootfirstapp;

import java.util.Arrays;
import java.util.Locale;

public class QuestionUtil {

    public static final String QUEST_TYPE_BEGIN = "That Starts With The Letter";
    public static final String QUEST_TYPE_END= "That Ends With The Letter";
    public static final String QUEST_TYPE_ANY = "Any";

    public static String buildQuestion(String type, String noun, String letter) {
        if (QUEST_TYPE_BEGIN.equalsIgnoreCase(type)) {
            return String.format("Name %s that starts with the letter %s", noun, letter);
        }
        if (QUEST_TYPE_END.equalsIgnoreCase(type)) {
            return String.format("Name %s that ends with the letter %s", noun, letter);
        }
        if (QUEST_TYPE_ANY.equalsIgnoreCase(type)) {
            return String.format("Name %s", noun);
        }

        throw new IllegalArgumentException("Invalid question type.");
    }

    public static String[] parseQuestion(String question) {
        question = question.toLowerCase(Locale.ENGLISH);

        if (question.startsWith("name") && question.contains("that ends with the letter")) {
            return new String[] {QUEST_TYPE_END, 
                
                question.substring(5, question.indexOf("that ends with the letter") - 1), 
                            question.substring(question.length() - 1)};
        }

        if (question.startsWith("name") && question.contains("that starts with the letter")) {
            return new String[] {QUEST_TYPE_BEGIN, 
                question.substring(5, question.indexOf("that starts with the letter") - 1), 
                            question.substring(question.length() - 1)};
        }

        if (question.startsWith("name")) {
            return new String[] {QUEST_TYPE_ANY, question.substring(5), null};
        }

        throw new IllegalArgumentException("Invalid question.");
    }

    public static String randQuestType() {
        int randIndex = (int) (Math.random() * 6);
        System.out.println(randIndex);
        // difficulty weighting
        if (randIndex < 3) { 
            return QUEST_TYPE_ANY;
        }
        if (randIndex == 3 || randIndex == 4) {
            return QUEST_TYPE_BEGIN;
        }
        return QUEST_TYPE_END;
    }
    

    public static void main(String[] args) {
        System.out.println(QuestionUtil.randQuestType());
    }
}
