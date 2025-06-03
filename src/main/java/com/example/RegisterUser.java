package com.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class RegisterUser {

    
    public static void main(String[] args) {

        try {
        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/login_system", "postgres", "nithish1906");
        if (conn != null) {
            System.out.println("Connected to DB!");
            conn.close();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username to be registered: ");
        String username = scanner.nextLine();
        System.out.print("Enter the password to be registed: ");  // same username you will type in login prompt
        String rawPassword = scanner.nextLine();
        System.out.print("Enter the phone number to be registed: ");
        String phone = scanner.nextLine();

        // Hash the password
        String hashedPassword = BCrypt.withDefaults().hashToString(12, rawPassword.toCharArray());

        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/login_system", "postgres", "nithish1906");
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (username, password, phone) VALUES (?, ?, ?)");
            stmt.setString(1, username);
            stmt.setString(2, hashedPassword);
            stmt.setString(3, "+91" + phone);
            stmt.executeUpdate();
            System.out.println("User registered successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
