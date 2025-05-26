package poe.pkg1.prog;
import java.util.Scanner;

public class POE1PROG {

    public static void main(String[] args) {
        Scanner input = new Scanner (System.in);
        System.out.print("Enter your first name: ");
        String firstName = input.nextLine();
        System.out.print("Enter your last name: ");
        String lastName = input.nextLine();
        System.out.print("Enter username: ");
        String username = input.nextLine();
        System.out.print("Enter password: ");
        String password = input.nextLine();
        System.out.print("Enter cellphone number (with international code, e.g., +27...)");
        String cellphone = input.nextLine();
        
        Login  login = new Login (username, password, cellphone, firstName, lastName);
        String regStatus = login.registerUser();
        System.out.println(regStatus);
        
        if (regStatus.equals("Registration successful.")) {
            System.out.println("/n--- Login ---");
            System.out.println("Enter username: ");
            String enteredUsername = input.nextLine();
            System.out.print("Enter password: ");
            String enteredPassword = input.nextLine();
            
            String loginMessage = login.returnLoginStatus(enteredUsername, enteredPassword);
            System.out.println(loginMessage);
            
                     
        }
        
        
        
    
    }
    
}
