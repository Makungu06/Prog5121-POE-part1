
import java.util.Scanner;

public class UserRegistrationAndLogin {

    public static void main(String[] args) {
        // Registration
        try (Scanner input = new Scanner(System.in)) {
            // Registration
            System.out.print("Enter your first name: ");
            String firstName = input.nextLine();
            System.out.print("Enter your last name: ");
            String lastName = input.nextLine();
            System.out.print("Enter username: ");
            String username = input.nextLine();
            System.out.print("Enter password: ");
            String password = input.nextLine();
            System.out.print("Enter cellphone number (with international code, e.g., +27...): ");
            String cellphone = input.nextLine();
            // Creating a Login object and attempting user registration
            Login login = new Login(username, password, cellphone, firstName, lastName);
            String regStatus = login.registerUser();
            System.out.println(regStatus);
            if ("Registration successful.".equals(regStatus)) {
                // Proceed with login if registration is successful
                System.out.println("\n--- Login ---");
                
                // Login Process
                String enteredUsername = getUserInput(input, "Enter username: ");
                String enteredPassword = getUserInput(input, "Enter password: ");
                
                String loginMessage = login.returnLoginStatus(enteredUsername, enteredPassword);
                System.out.println(loginMessage);
            }
            // Closing the scanner resource
        }
    }

    // Helper method to get user input with a prompt
    private static String getUserInput(Scanner input, String prompt) {
        System.out.print(prompt);
        return input.nextLine();
    }
}

class Login {
    private final String username;
    private final String password;
    private final String firstName;
    private final String lastName;
    
    // Constructor
    public Login(String username, String password, String cellphone, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    // Register user method (for demonstration purposes)
    public String registerUser() {
        // In a real application, you would check if the username is available, 
        // store the information securely, and more.
        System.out.println("User registered successfully: " + firstName + " " + lastName);
        return "Registration successful.";
    }
    
    // Check login credentials
    public String returnLoginStatus(String enteredUsername, String enteredPassword) {
        if (this.username.equals(enteredUsername) && this.password.equals(enteredPassword)) {
            return "Login successful!";
        } else {
            return "Invalid username or password.";
        }
    }
}
