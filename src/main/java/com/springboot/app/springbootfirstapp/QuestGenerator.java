package com.springboot.app.springbootfirstapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class QuestGenerator {

    private static final Map<String, String> tables;
    private static final Map<String, String> nounToTables;

    static {

        Map<String, String> nounsMap = new HashMap<>(); // key: table name, value: noun phrase.
                                                        // sqlite table name to human readable string conversion
        nounsMap.put("all_usa_states", "A State in the USA");
        nounsMap.put("many_english_words", "A Word in the English Language");
        nounsMap.put("all_world_countries", "A Country in the World");
        nounsMap.put("all_elements", "An Element");
        nounsMap.put("many_animals", "An Animal");
        nounsMap.put("many_greek_gods", "A Greek God");
        nounsMap.put("many_roman_gods", "A Roman God");
        nounsMap.put("many_cosmetic_items", "A Cosmetic Item");
        nounsMap.put("many_office_supplies", "An Office Supply");
        nounsMap.put("many_olympic_sports", "An Olympic Sport");
        nounsMap.put("many_nfl_teams", "An NFL Team");
        nounsMap.put("many_marvel", "A Marvel Character");
        nounsMap.put("many_mythical_creatures", "A Mythical Creature");
        nounsMap.put("many_weather_conditions", "A Weather Condition");
        nounsMap.put("many_vegetables", "A Vegetable");
        nounsMap.put("many_professions", "A Profession");
        nounsMap.put("many_pets", "A Pet");
        nounsMap.put("many_landmarks", "A Landmark");
        nounsMap.put("many_luxury_brands", "A Luxury Brand");
        nounsMap.put("many_holidays", "A Holiday");
        nounsMap.put("many_gemstones", "A Gemstone");
        nounsMap.put("many_fruits", "A Fruit");
        nounsMap.put("many_flowers_plants", "A Flower or Plant");
        nounsMap.put("many_empires", "An Empire");
        nounsMap.put("many_youtubers", "A Youtuber");

        tables = Collections.unmodifiableMap(nounsMap);
        nounToTables = nounsMap.entrySet().stream()
                .collect(Collectors.toMap(e -> e.getValue().toLowerCase(), Map.Entry::getKey));

    }

    public static String getRandomTable() throws Exception {

        String sql = "SELECT name FROM sqlite_master WHERE type='table'";

        try (Connection conn = DBUtil.getConnection()) {
            try (Statement stmt = conn.createStatement()) {
                try (ResultSet rs = stmt.executeQuery(sql)) {

                    List<String> tables = new ArrayList<>();
                    while (rs.next()) {
                        tables.add(rs.getString("name"));
                    }

                    int randomIdx = (int) (Math.random() * tables.size());
                    // return tables.get(randomIdx);
                    return "many_english_words"; // TODO: remove this line
                }

            }
        }
    }

    public static String getRandomQuestion() throws Exception {

        String randTable = getRandomTable();
        String randQuestType = QuestionUtil.randQuestType();
        String noun = tables.get(randTable);

        String letter = null;

        if (!randQuestType.equals(QuestionUtil.QUEST_TYPE_ANY)) {

            try (Connection conn = DBUtil.getConnection()) {
                try (Statement stmt = conn.createStatement()) {
                    try (ResultSet rs = stmt.executeQuery("SELECT " + randTable + " FROM " + randTable)) {

                        List<String> words = new ArrayList<>();
                        while (rs.next()) {
                            words.add(rs.getString(1));
                        }

                        int randomIdx = (int) (Math.random() * words.size());
                        String randomWord = words.get(randomIdx);

                        letter = randQuestType.equals(QuestionUtil.QUEST_TYPE_BEGIN) ? randomWord.substring(0, 1)
                                : randomWord.substring(randomWord.length() - 1);
                    }
                }
            }
        }

        return QuestionUtil.buildQuestion(randQuestType, noun, letter);
    }

    public static int checkAnswer(String question, String answer) throws Exception {

        question = question.toLowerCase(Locale.ENGLISH);
        answer = answer.toLowerCase(Locale.ENGLISH);
        answer = answer.trim();



        String[] parts = QuestionUtil.parseQuestion(question); // [0] = quest type ("starts wiht" etc.), [1] = noun, [2] = letter
        String randQuestType = parts[0];
        String noun = parts[1];
        String letter = parts[2];

        String tableName = nounToTables.get(noun);

        if (QuestionUtil.QUEST_TYPE_BEGIN.equals(randQuestType)) { // doesn't start with the correct letter
            if (!answer.startsWith(letter)) {
                return 0;
            }

        } else if (QuestionUtil.QUEST_TYPE_END.equals(randQuestType)) { // doesn't end with the correct letter
            if (!answer.endsWith(letter)) {
                return 0;
            }
        }

        try (Connection conn = DBUtil.getConnection()) {
            
            String query = String.format("SELECT %s FROM %s WHERE LOWER(REPLACE(%s, ' ', '')) = ? OR LOWER(%s) = ?", tableName, tableName, tableName, tableName); // ignore sandwiched spaces
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, answer);
                stmt.setString(2, answer);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {

                        String matchedWord = rs.getString(1); // TODO: handle multiple matches and implement Levenshtein distance
                        // return the length of the matched word without counting spaces
                        return matchedWord.replaceAll("\\s+", "").length();
                    } else {
                        return 0;
                    }

                }
            }
        }
    }

    public static String botAnswer(String question) throws Exception {
        question = question.toLowerCase(Locale.ENGLISH);
        String[] parts = QuestionUtil.parseQuestion(question);
        String randQuestType = parts[0];
        String noun = parts[1];
        String letter = parts[2];
        String tableName = nounToTables.get(noun);

        if (QuestionUtil.QUEST_TYPE_ANY.equals(randQuestType)) {
            // return a random word from the table called tableName
            try (Connection conn = DBUtil.getConnection()) {
                try (Statement stmt = conn.createStatement()) {
                    try (ResultSet rs = stmt.executeQuery("SELECT " + tableName + " FROM " + tableName)) {

                        List<String> words = new ArrayList<>();
                        while (rs.next()) {
                            words.add(rs.getString(1));
                        }

                        int randomIdx = (int) (Math.random() * words.size());
                        String randomWord = words.get(randomIdx);
                        return randomWord;
                    }
                }
            }
        }

        else if (QuestionUtil.QUEST_TYPE_BEGIN.equals(randQuestType)) {
            try (Connection conn = DBUtil.getConnection()) {
                try (Statement stmt = conn.createStatement()) {
                    // does not need to be protected from SQL injection because user input is not
                    // used and the letter is a single character
                    try (ResultSet rs = stmt.executeQuery("SELECT " + tableName + " FROM " + tableName + " WHERE "
                            + tableName + " LIKE '" + letter + "%'")) {
                        List<String> words = new ArrayList<>();
                        while (rs.next()) {
                            words.add(rs.getString(1));
                        }

                        int randomIdx = (int) (Math.random() * words.size());
                        String randomWord = words.get(randomIdx);
                        return randomWord;
                    }
                }
            }
        }

        else if (QuestionUtil.QUEST_TYPE_END.equals(randQuestType)) {
            try (Connection conn = DBUtil.getConnection()) {
                try (Statement stmt = conn.createStatement()) {
                    try (ResultSet rs = stmt.executeQuery("SELECT " + tableName + " FROM " + tableName + " WHERE "
                            + tableName + " LIKE '%" + letter + "'")) {

                        List<String> words = new ArrayList<>();
                        while (rs.next()) {
                            words.add(rs.getString(1));
                        }

                        int randomIdx = (int) (Math.random() * words.size());
                        String randomWord = words.get(randomIdx);
                        return randomWord;
                    }
                }
            }
        }
        return null;
    }

}
