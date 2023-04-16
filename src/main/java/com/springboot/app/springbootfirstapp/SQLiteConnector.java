package com.springboot.app.springbootfirstapp;

// import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLiteConnector {
    
    public static Connection connect() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:src/main/resources/NounBankSQLite.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static String displayAllTables (){
        String result = "";
        try {
            Connection conn = connect();
            java.sql.Statement stmt = conn.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery("SELECT name FROM sqlite_master WHERE type='table';");
            while (rs.next()) {
                result += rs.getString("name") + "  ";
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public static String getRandomNbTable() {
        try {
            String sql = "SELECT name FROM sqlite_master WHERE type='table'";
            Connection conn = connect();
            java.sql.Statement stmt = conn.createStatement();
            ResultSet rs = ((java.sql.Statement) stmt).executeQuery(sql);
            String[] tables = new String[100]; // store the table names in an array
            int i = 0;
            while (rs.next()) {
                tables[i] = rs.getString("name");
                i++;
            }
            
            // for (int j = 0; j < i; j++) { // print the table names
            //     System.out.println(tables[j]);
            // }
            
            int random = (int) (Math.random() * i); // choose a random table from the array
            String chosenTable = tables[random];
            // System.out.println("Randomly Chosen Table: " + chosenTable); // print the randomly chosen table name
            // conn.close();
            // print chosen table

            return chosenTable;
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
}

        
