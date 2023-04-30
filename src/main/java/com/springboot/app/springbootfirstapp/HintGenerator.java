package com.springboot.app.springbootfirstapp;
import java.util.Locale;


public class HintGenerator {
    public static String makeHint(String question) throws Exception {
        // parseQuestion
        String[] parsedQuestion = QuestionUtil.parseQuestion(question);
        String questType = parsedQuestion[0];
        String noun = parsedQuestion[1];
        String letter = parsedQuestion[2];

        System.out.println("questType: " + questType);
        System.out.println("noun: " + noun);
        System.out.println("letter: " + letter);

        // if the question type is any, provide the first and last letter of the answer

        if (questType.equals(QuestionUtil.QUEST_TYPE_ANY)) {
            String possibleAnswer = QuestGenerator.botAnswer(question);
            possibleAnswer = possibleAnswer.toLowerCase(Locale.ENGLISH);
            String hint = "";
            String[] words = possibleAnswer.split(" ");
            
            for (String word : words) {
                if (word.length() >= 3) {
                    hint += word.charAt(0);
                    for (int i = 0; i < word.length() - 2; i++) {
                        hint += "_";
                    }
                    hint += word.charAt(word.length() - 1);
                    hint += " ";
                } else {
                    hint += word + " ";
                }
            }

            return hint;
        }

        // if the question type is begin, provide the last letter of the answer and one other random letter 

        if (questType.equals(QuestionUtil.QUEST_TYPE_BEGIN)) {
            String possibleAnswer = QuestGenerator.botAnswer(question);
            possibleAnswer = possibleAnswer.toLowerCase(Locale.ENGLISH);
            String hint = "";
            String[] words = possibleAnswer.split(" ");
            
            for (String word : words) {
                if (word.length() >= 3) {
                    hint += word.charAt(0);
                    for (int i = 0; i < word.length() - 2; i++) {
                        hint += "_";
                    }
                    hint += word.charAt(word.length() - 1);
                    hint += " ";
                } else {
                    hint += word + " ";
                }
            }
            return hint;
        }

        // if the question type is end, provide the first letter of the answer and one other random letter

        if (questType.equals(QuestionUtil.QUEST_TYPE_END)) {
            String possibleAnswer = QuestGenerator.botAnswer(question);
            possibleAnswer = possibleAnswer.toLowerCase(Locale.ENGLISH);
            String hint = "";
            String[] words = possibleAnswer.split(" ");
            
            for (String word : words) {
                if (word.length() >= 3) {
                    hint += word.charAt(0);
                    for (int i = 0; i < word.length() - 2; i++) {
                        hint += "_";
                    }
                    hint += word.charAt(word.length() - 1);
                    hint += " ";
                } else {
                    hint += word + " ";
                }
            }
            return hint;
        }
        return null;
    }
    public static void main(String[] args) throws Exception {
        System.out.println(makeHint("Name An Element that ends with the letter n"));
    }
}

