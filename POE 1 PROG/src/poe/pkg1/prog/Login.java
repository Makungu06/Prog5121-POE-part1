package poe.pkg1.prog;

public class Login 
{
    private final String username;
    private final String password;
    private final String cellphone;
    private final String firstName;
    private final String lastName;
    
    public Login(String username, String password, String cellphone, String firstName, String lastName)
    {
        this.username = username;
        this.password = password;
        this.cellphone = cellphone;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public boolean checkUserName() 
    {
        return username.contains("_") && username.length() <= 5;
        
    }
    
    public boolean checkPassowrdComplexity() 
    {
        return password.matches("^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@$%^&*()_+//_={}:;<>?]).{8,}$");
        
    }
    
    // Cellphone validation 
    public boolean checkCellPhoneNumber()
    {
        return cellphone.matches("^//+//d{1,3}//d{9,10}$");
    }
    
    public String registerUser() {
        if (!checkUserName()) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters";
        } else if (!checkPassowrdComplexity()) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a chsracter";
        } else if (!checkCellPhoneNumber()) {
            return "Cellphone number incorrectly formatted or does not contain international code.";
        }
        return "Registration successful.";
        
    }
    
    public boolean loginUser(String enteredUsername, String enteredPassword) {
        return this.username.equals(enteredUsername) && this.password.equals(enteredPassword);
    }

    public String returnLoginStatus(String enteredUsername, String enteredPassword) {
        if (loginUser(enteredUsername, enteredPassword)) {
            return "Welcome" + firstName + "," + lastName + "it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
            
           
        }
    }
}

            

        
    
    

