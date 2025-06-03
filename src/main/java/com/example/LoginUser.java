package com.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;
import java.util.Scanner;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class LoginUser {
    // Replace with your Twilio credentials
    public static final String ACCOUNT_SID = "AC0180286ff6fef6a0af7e8c3a43b1ad4f";
    public static final String AUTH_TOKEN = "c4fba72389170ead2b02ea1472a5b95e";
    public static final String FROM_PHONE = "+12707176431"; // Twilio number

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the registered username: ");
        String username = scanner.nextLine();

        System.out.print("Enter the registered password: ");
        String inputPassword = scanner.nextLine();

        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/login_system", "postgres", "nithish1906");
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username = ?");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String hashedPassword = rs.getString("password");
                String phone = rs.getString("phone");

                BCrypt.Result result = BCrypt.verifyer().verify(inputPassword.toCharArray(), hashedPassword);
                if (result.verified) {
                    // Send OTP
                    String otp = String.format("%04d", new Random().nextInt(10000));
                    sendSms(phone, otp);
                    System.out.print("Enter OTP sent to your phone: ");
                    String enteredOtp = scanner.nextLine();

                    if (enteredOtp.equals(otp)) {
                        System.out.println("Login successful!");
                        System.out.println("Successfully logged in as: " + username);
                    } else {
                        System.out.println("Incorrect OTP.");
                    }
                } else {
                    System.out.println("Incorrect password.");
                }
            } else {
                System.out.println("User not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        scanner.close();
    }

    public static void sendSms(String toPhone, String otp) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message.creator(
                new com.twilio.type.PhoneNumber(toPhone),
                new com.twilio.type.PhoneNumber(FROM_PHONE),
                "Your OTP code is: " + otp
        ).create();
    }
}
