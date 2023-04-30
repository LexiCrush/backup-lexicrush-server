package com.springboot.app.springbootfirstapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserService {
    
    public static void insertUser(String username, String password) throws Exception {
        try (Connection conn = DBUtil.getAuthConnection()) {

            String query = "INSERT INTO User (username, password) VALUES (?, ?)";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, username);
                stmt.setString(2, password);
                stmt.executeUpdate();
            }
        }
    }

    public static  boolean validateUser(String username, String password) throws Exception {
       

        try (Connection conn = DBUtil.getAuthConnection()) {

            String query = "SELECT password FROM User where username = ?"; // prevent SQl injectioWHERE 

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, username);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {


                        String dbPassword = rs.getString(1);

                        System.out.println("dbPassword: " + dbPassword);
                        return dbPassword.equals(password);
                    }
                    else {

                        System.out.println("No user found");
                        return false;
                    }
                    
                }
            }
        }
        
    }
}
