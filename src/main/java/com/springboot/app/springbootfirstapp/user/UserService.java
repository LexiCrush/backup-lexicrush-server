package com.springboot.app.springbootfirstapp.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.python.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi.ecCVCDSA;

import com.springboot.app.springbootfirstapp.DBUtil;

public class UserService {

    public static void insertUser(String username, String password) throws Exception {

        try (Connection conn = DBUtil.getAuthConnection()) {

            String query = "INSERT INTO players (username, password) VALUES (?, ?)";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, username);
                stmt.setString(2, password);
                stmt.executeUpdate();
            }
        }
    }

    public static boolean validateUser(String username, String password) throws Exception {

        try (Connection conn = DBUtil.getAuthConnection()) {

            String query = "SELECT password FROM players where username = ?"; // prevent SQl injectioWHERE

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, username);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {

                        String dbPassword = rs.getString(1);

                        System.out.println("Stored dbPassword: " + dbPassword);
                        System.out.println("Provided Password: " + password);
                        return dbPassword.equals(password);
                    } else {

                        System.out.println("No user found");
                        return false;
                    }

                }
            }
        }

    }

    public static String createAccessToken(String username) throws Exception {

        long expiredAt = System.currentTimeMillis() + 1000 * 60 * 60 * 2;
        // user1|1234567890
        return username + "|" + expiredAt;
    }

    public static String parseAccessToken(String accessToken) throws Exception {

        String[] parts = accessToken.split("\\|");

        if (parts.length != 2) {
            return null;
        }

        String username = parts[0];
        long expiredAt = Long.parseLong(parts[1]);

        if (expiredAt < System.currentTimeMillis()) {
            return null;
        }

        return username;
    }

    public static boolean userExists(String username) throws Exception {
        try (Connection conn = DBUtil.getAuthConnection()) {

            String query = "SELECT username FROM players where username = ?"; // prevent SQl injectioWHERE

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, username);
                try (ResultSet rs = stmt.executeQuery()) {
                    return rs.next();
                }
            }
        }
    }

    public static void removeUser(String username) throws Exception {
        try (Connection conn = DBUtil.getAuthConnection()) {
            String query = "DELETE FROM players WHERE username=?";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, username);
                stmt.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
