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
public class SignUp{
    
    public void signUp() {

        System.out.println("=============== SIGN-UP ==========================");
        //================ New Username ==========================
        String createdUsername = createUsername();
        boolean isUsernameCorrect = false;

        while (isUsernameCorrect == false) {
            if (createdUsername.contains("_") && createdUsername.length() <= 5 && !createdUsername.contains(" ")) {
                System.out.println("========================================");
                System.out.println("Username successfully captured.");
                System.out.println("========================================");
                isUsernameCorrect = true;
            } else {
                System.out.println("========================================");
                System.out.println("ALERT!");
                System.out.println("Username is not correctly formatted,");
                System.out.println("please ensure your username contains");
                System.out.println("an underscore and is no more than");
                System.out.println("five characters in length.");
                System.out.println("========================================");
                createdUsername = createUsername();
            }
        }

        // ================== New Phone Number =====================
        /*
        This is a static method (on the right side of the equals sign). So that means
        the method can be accessed by just using dot notation with the class name and the class name's method.
        If the class name's method was not static, you would need the declared object to access it.
         */
        String createdPhoneNumber = createPhoneNumber();
        boolean isCreatedPhoneNumberCorrect = false;

        while (isCreatedPhoneNumberCorrect == false) {
            if (createdPhoneNumber.matches("\\+27\\d{9,10}")) {
                System.out.println("========================================");
                System.out.println("Cell phone number successfully added.");
                System.out.println("========================================");
                isCreatedPhoneNumberCorrect = true;
            } else {
                System.out.println("========================================");
                System.out.println("Cell phone number incorrectly formatted or");
                System.out.println("does not contain international code.");
                System.out.println("========================================");
                createdPhoneNumber = createPhoneNumber();
            }
        }
        //AI code inline reference: (OpenAI, 2025);

        //===================== New Password ============================
        String createdPassword = createNewPassword();
        boolean isCreatedPasswordCorrect = false;

        while (isCreatedPasswordCorrect == false) {
            /*
            These method calls all check to see if the password contains the following parameters respectively:
            -> more than eight characters long.
            -> contains a minimum of one capital letter.
            -> contains a minimum of one number.
            -> contains a special character (using a uniquely created SignUp class method called 'passwordContainsSpecialCharacter()'.
            */
            if (createdPassword.length() > 8 && createdPassword.matches(".*[A-Z].*") && createdPassword.matches(".*\\d.*") && passwordContainsSpecialCharacter(createdPassword)) {
                System.out.println("========================================");
                System.out.println("Password successfully captured.");
                System.out.println("========================================");
                isCreatedPasswordCorrect = true;
            } else {
                System.out.println("========================================");
                System.out.println("Password is NOT correctly formatted;");
                System.out.println("please ensure the password contains at least");
                System.out.println("eight characters, a capital letter, a number, and a special character.");
                System.out.println("========================================");
                //Always remember that if your dot.notation method returns a value, always attach it to a declared variable when calling it again.
                createdPassword = createNewPassword();
            }
        }

    }

    public String createUsername(){
        System.out.println("Enter your new username");
        System.out.println("Username must contain (1) an underscore, and is no more than (2) five characters long.");
        System.out.print(">> ");
        Scanner inputUsername = new Scanner(System.in);
        String username = inputUsername.nextLine();
        return username;
    }

    public String createPhoneNumber(){
        System.out.println("Enter your existing phone number.");
        System.out.println("The phone number must contain:");
        System.out.println("-> An international country code (+27).");
        System.out.println("-> Followed by ten digits (starting with 0).");
        System.out.print(">> ");
        Scanner inputPhoneNumber = new Scanner(System.in);
        String phoneNumber = inputPhoneNumber.nextLine();
        return phoneNumber;
    }
    
    public String createNewPassword(){
        System.out.println("Create a new password.");
        System.out.println("Password must be:");
        System.out.println("-> more than eight characters long.");
        System.out.println("->a minimum of one capital letter.");
        System.out.println("->a minimum of one number.");
        System.out.println("->a minimum of one special character. ([!@#$%^&*()_+] etc.)");
        System.out.print(">> ");
        Scanner inputPassword = new Scanner(System.in);
        String password = inputPassword.nextLine();
        return password;
    }
    
    //This method checks to see if the user's password contains a special character.
    public boolean passwordContainsSpecialCharacter(String inputNewPassword) {
        String password = inputNewPassword;
        int count = 0;

        for (int i = 0;
                i < password.length();
                i++) {
            if (!Character.isLetterOrDigit(password.charAt(i)) && !Character.isWhitespace(password.charAt(i))) {
                count++;
            }
        }

        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    //Reference to AI phone number checker
    /*
    This is the (human-modified) AI generated code for checking
    phone numbers in South Africa.
    Reference: When prompted with: "Create a cellphone number checker - In Java - that
    contains South Africa's international country code [number], followed by the number,
    which is no more than ten digits long." The ChatGPT generated the code used to detect
    South African phone numbers with their country code attached to them - (as seen in the
    inline reference at the top).
    
    OpenAI (2025). ChatGPT (May 13, 2024 version) [Large Language Model]. Available at: https://chatgpt.com . 
    */
}
