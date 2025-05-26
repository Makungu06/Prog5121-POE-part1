package poe.pkg1.prog;

import javax.swing.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// User class to manage user data and login logic
class User {
    private String username;
    private String password;
    private String cellphone;
    private String firstName;
    private String lastName;

    public User(String username, String password, String cellphone, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.cellphone = cellphone;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String registerUser() {
        if (username.length() < 5 || password.length() < 5) {
            return "Username and password must be at least 5 characters long.";
        }
        return "Registration successful.";
    }

    public String returnLoginStatus(String enteredUsername, String enteredPassword) {
        if (enteredUsername.equals(this.username) && enteredPassword.equals(this.password)) {
            return "Login successful. Welcome, " + firstName + " " + lastName + "!";
        } else {
            return "Login failed. Invalid username or password.";
        }
    }

    public String getUsername() {
        return username;
    }
}

// Message class to represent and hash messages
class Message {
    private static int idCounter = 1;
    private String messageID;
    private String messageContent;
    private String sender;
    private String recipient;
    private String timestamp;
    private String hash;

    public Message(String messageContent, String sender, String recipient) {
        this.messageID = generateMessageID();
        this.messageContent = messageContent;
        this.sender = sender;
        this.recipient = recipient;
        this.timestamp = generateTimestamp();
        this.hash = generateHash(messageContent);
    }

    private String generateMessageID() {
        return String.format("MSG%03d", idCounter++);
    }

    private String generateTimestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    private String generateHash(String content) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(content.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            return "HASH_ERROR";
        }
    }

    @Override
    public String toString() {
        return "\nMessage ID: " + messageID +
               "\nFrom: " + sender +
               "\nTo: " + recipient +
               "\nTime: " + timestamp +
               "\nContent: " + messageContent +
               "\nHash: " + hash;
    }
}

// Main program class
public class POE1PROG {
    public static void main(String[] args) {
        // Registration
        String firstName = JOptionPane.showInputDialog("Enter your first name:");
        String lastName = JOptionPane.showInputDialog("Enter your last name:");
        String username = JOptionPane.showInputDialog("Enter username (min 5 characters):");
        String password = JOptionPane.showInputDialog("Enter password (min 5 characters):");
        String cellphone = JOptionPane.showInputDialog("Enter cellphone number (e.g., +27...):");

        User user = new User(username, password, cellphone, firstName, lastName);
        String regStatus = user.registerUser();
        JOptionPane.showMessageDialog(null, regStatus);

        // Login
        if (regStatus.equals("Registration successful.")) {
            String enteredUsername = JOptionPane.showInputDialog("Enter username to login:");
            String enteredPassword = JOptionPane.showInputDialog("Enter password:");

            String loginMessage = user.returnLoginStatus(enteredUsername, enteredPassword);
            JOptionPane.showMessageDialog(null, loginMessage);

            // Message creation
            if (loginMessage.startsWith("Login successful")) {
                String recipient = JOptionPane.showInputDialog("Enter recipient username:");
                String content = JOptionPane.showInputDialog("Enter your message:");

                Message message = new Message(content, user.getUsername(), recipient);
                JOptionPane.showMessageDialog(null, "=== Message Details ===\n" + message);
            }
        }
    }
}
