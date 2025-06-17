/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quickchat;

import java.util.Scanner;

/**
 *
 * @author Pako Tone
 */
public class LogIn {
    
    public boolean logIn(){
        //Declarations
        //boolean usernameExists = <true>
        //boolean userPasswordExists = <true>
        //boolean userPasswordExists = <true>
        //Therefore
        //logIn method should return a boolean value called successfullyLoggedIn = <false>
        System.out.println("=============== LOG-IN ===============");
        
        //=========== Username search ====================
        boolean usernameExists = false;
        boolean doesUsernameExist = checkUsername();
        
        while (usernameExists == false){
            if (doesUsernameExist == true){
                System.out.println("Username exists.");
                System.out.println("====================================");
                usernameExists = true;
            } else {
                System.out.println("Username DOES NOT exist.");
                System.out.println("Please try again.");
                System.out.println("====================================");
                usernameExists = false;
                doesUsernameExist = checkUsername();
            }
        }
        
        //=========== Password Search ================================
        boolean passwordExists = false;
        boolean doesPasswordExist = checkPasswordComplexity();
        
        while (passwordExists == false){
            if (doesPasswordExist == true){
                System.out.println("Password exists.");
                System.out.println("====================================");
                passwordExists = true;
            } else {
                System.out.println("Password DOES NOT exist.");
                System.out.println("Please try again.");
                System.out.println("====================================");
                passwordExists = false;
                doesPasswordExist = checkPasswordComplexity();
            }
        }
        
        //============== Phone Number Search ===========================
        boolean phoneNumberExists = false;
        boolean doesPhoneNumberExist = checkCellPhoneNumber();
        
        while (phoneNumberExists == false){
            if (doesPhoneNumberExist == true){
                System.out.println("Phone Number exists");
                System.out.println("====================================");
                phoneNumberExists = true;
            } else {
                System.out.println("Phpone Number DOES NOT exist.");
                System.out.println("Please try again.");
                System.out.println("====================================");
                phoneNumberExists = false;
                doesPhoneNumberExist = checkCellPhoneNumber();
            }
        }  
        
        boolean userLoggedInSuccessfully = false;
        
        while (userLoggedInSuccessfully == false){
            if (usernameExists && passwordExists && phoneNumberExists){
                userLoggedInSuccessfully = true;
            }
        }
        
        return userLoggedInSuccessfully;
    }
    // ====================== End Of LogIn Class Method ========================

    public boolean checkUsername() {
        boolean usernameExists = false;

        System.out.println("Enter your existing username.");
        System.out.print(">> ");
        Scanner inputExistingUsername = new Scanner(System.in);
        String checkIfUsernameExists = inputExistingUsername.nextLine();

        if (checkIfUsernameExists.contains("_") && checkIfUsernameExists.length() <= 5) {
            usernameExists = true;
        }
        return usernameExists;
    }

    public boolean checkPasswordComplexity() {
        boolean passwordExists = false;

        System.out.println("Enter your existing password.");
        System.out.println("-> Minimum eight characters, contains a number, contains a capital letter, contains special character.");
        System.out.print(">> ");
        Scanner inputExistingPassword = new Scanner(System.in);
        String checkPasswordComplexity = inputExistingPassword.nextLine();

        if (checkPasswordComplexity.length() > 8 &&
                checkPasswordComplexity.matches(".*[A-Z].*") &&
                checkPasswordComplexity.matches(".*\\d.*") &&
                passwordContainsSpecialCharacter(checkPasswordComplexity)) {
            passwordExists = true;
        }
        return passwordExists;
    }

    //This method checks to see if the user's password contains a special character.
    public boolean passwordContainsSpecialCharacter(String inputExistingPassword) {
        String password = inputExistingPassword;
        int count = 0;
        boolean doesPasswordContainSpecialCharacter = false;

        for (int i = 0;
                i < password.length();
                i++) {
            if (!Character.isLetterOrDigit(password.charAt(i)) && !Character.isWhitespace(password.charAt(i))) {
                count++;
            }
        }

        if (count > 0) {
            doesPasswordContainSpecialCharacter = true;
        }
        
        return doesPasswordContainSpecialCharacter;
    }

    public boolean checkCellPhoneNumber() {
        boolean doesCellPhoneNumberExist = false;
        System.out.println("Enter existing phone number.");
        System.out.println("Phone number must contain country code (+27) and ");
        System.out.println(">> ");
        Scanner inputExistingPhoneNumber = new Scanner(System.in);
        String existingPhoneNumber = inputExistingPhoneNumber.nextLine();
        
        if (existingPhoneNumber.matches("\\+27\\d{9,10}")) {
            doesCellPhoneNumberExist = true;
        }
        return doesCellPhoneNumberExist;
    }
    
}
