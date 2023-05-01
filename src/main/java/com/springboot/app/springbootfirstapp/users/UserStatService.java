package com.springboot.app.springbootfirstapp.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

// import java.sql.SQLException;
// import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
// import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import com.springboot.app.springbootfirstapp.DBUtil;

public class UserStatService {

    public static int getScoreByType(String username, String scoretype) throws Exception {

        // score type can only be current_score, high_score
        if (!scoretype.equals("current_score") && !scoretype.equals("high_score")) {
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
        // score type can only be current_score, high_score
        if (!scoretype.equals("current_score") && !scoretype.equals("high_score")) {
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

    public static int getAvailibleHints(String username) throws Exception {
        try (Connection conn = DBUtil.getAuthConnection()) {
            String query = "SELECT availible_hints FROM players WHERE username = ?";
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

    public static void updateAvailibleHints(String username, int hints) throws Exception {
        try (Connection conn = DBUtil.getAuthConnection()) {
            String query = "UPDATE players SET availible_hints = ? WHERE username = ?";
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

}