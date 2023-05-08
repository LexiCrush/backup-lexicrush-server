package com.springboot.app.springbootfirstapp.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.springboot.app.springbootfirstapp.DBUtil;

public class UserStatService {

    public static int getScoreByType(String username, String scoretype) throws Exception {

        // valid types
        Boolean validType = (!scoretype.equals("current_score") && !scoretype.equals("high_score")
                && !scoretype.equals("available_hints") && !scoretype.equals("coins")
                && !scoretype.equals("games_played"));

        if (validType) {
            throw new Exception("Invalid score type");
        }

        // query the players table for the score type
        try (Connection conn = DBUtil.getAuthConnection()) {
            String query = "SELECT " + scoretype + " FROM players WHERE username = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, username);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    } else {
                        throw new Exception("User does not exist");
                    }
                }
            }
        }
    }

    public static void updateScoreByType(String username, int score, String scoretype) throws Exception {
        Boolean validType = (!scoretype.equals("current_score") && !scoretype.equals("high_score")
                && !scoretype.equals("available_hints") && !scoretype.equals("coins")
                && !scoretype.equals("games_played"));

        if (validType) {
            throw new Exception("Invalid score type");
        }

        try (Connection conn = DBUtil.getAuthConnection()) {
            String query = "UPDATE players SET " + scoretype + " = ? WHERE username = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, score);
                stmt.setString(2, username);
                stmt.executeUpdate();
            }
        }
    }

    public static int getAvailableHints(String username) throws Exception {
        try (Connection conn = DBUtil.getAuthConnection()) {
            String query = "SELECT available_hints FROM players WHERE username = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, username);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    } else {
                        throw new Exception("User does not exist");
                    }
                }
            }
        }
    }

    public static void updateAvailableHints(String username, int hints) throws Exception {
        try (Connection conn = DBUtil.getAuthConnection()) {
            String query = "UPDATE players SET available_hints = ? WHERE username = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, hints);
                stmt.setString(2, username);
                stmt.executeUpdate();
            }
        }
    }

    public static int getGamesPlayed(String username) throws Exception {
        try (Connection conn = DBUtil.getAuthConnection()) {
            String query = "SELECT games_played FROM players WHERE username = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, username);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    } else {
                        throw new Exception("User does not exist");
                    }
                }
            }
        }
    }

    public static void updateGamesPlayed(String username, int games) throws Exception {
        try (Connection conn = DBUtil.getAuthConnection()) {
            String query = "UPDATE players SET games_played = ? WHERE username = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, games);
                stmt.setString(2, username);
                stmt.executeUpdate();
            }
        }
    }

    public static int getGamesWon(String username) throws Exception {
        try (Connection conn = DBUtil.getAuthConnection()) {
            String query = "SELECT games_won FROM players WHERE username = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, username);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    } else {
                        throw new Exception("User does not exist");
                    }
                }
            }
        }
    }

    public static void updateGamesWon(String username, int games) throws Exception {
        try (Connection conn = DBUtil.getAuthConnection()) {
            String query = "UPDATE players SET games_won = ? WHERE username = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, games);
                stmt.setString(2, username);
                stmt.executeUpdate();
            }
        }
    }

    public static int getGamesLost(String username) throws Exception {
        try (Connection conn = DBUtil.getAuthConnection()) {
            String query = "SELECT games_lost FROM players WHERE username = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, username);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    } else {
                        throw new Exception("User does not exist");
                    }
                }
            }
        }
    }

    public static void updateGamesLost(String username, int games) throws Exception {
        try (Connection conn = DBUtil.getAuthConnection()) {
            String query = "UPDATE players SET games_lost = ? WHERE username = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, games);
                stmt.setString(2, username);
                stmt.executeUpdate();
            }
        }
    }

    public static String[][] getHighScoreLeaderboard() throws Exception {
        try (Connection conn = DBUtil.getAuthConnection()) {
            String query = "SELECT username, high_score FROM players ORDER BY high_score DESC LIMIT 10";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    String[][] leaderboard = new String[10][2];
                    int i = 0;
                    while (rs.next()) {
                        leaderboard[i][0] = rs.getString(1);
                        leaderboard[i][1] = Integer.toString(rs.getInt(2));
                        i++;
                    }
                    return leaderboard;
                }
            }
        }
    }
}