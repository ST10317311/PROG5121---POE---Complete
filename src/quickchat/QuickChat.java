/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package quickchat;

//import org.json.JSONObject;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Pako Tone
 */
public class QuickChat {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Declarations
        
        inputNewMessage();

        SignUp newUser = new SignUp();
        LogIn existingUser = new LogIn();
        boolean isUserLoggedIn = false;

        System.out.println("Welcome to QuickChat Authentication Page!");
        System.out.println("Type '1' for Sign-Up. Type '2' for Log-In. (NO LETTERS OR SPECIAL CHARACTERS)");
        System.out.println(">> ");
        // ============ Authentication Options =====================
        Scanner inputAuthenticationNumber = new Scanner(System.in);
        int authenticationNumber = inputAuthenticationNumber.nextInt();

        boolean isAuthenticationChosen = false;

        while (isAuthenticationChosen == false) {
            if (authenticationNumber == 1) {
                System.out.println("====================================");
                System.out.println("You have selected SIGN-UP.");
                System.out.println("====================================");
                isAuthenticationChosen = true;
                newUser.signUp();
            } else if (authenticationNumber == 2) {
                System.out.println("====================================");
                System.out.println("You have chosen LOG-IN.");
                System.out.println("====================================");
                isAuthenticationChosen = true;
                existingUser.logIn();
                //Once all the methods in this object have been executed. Then 
                //change to 'boolean isUserLoggedIn = existingUser.logIn
                isUserLoggedIn = true;
            } else {
                System.out.println("You have chosen an incorrect value.");
                isAuthenticationChosen = false;
                System.out.println("Type '1' for Sign-Up. Type '2' for Log-In.");
                authenticationNumber = inputAuthenticationNumber.nextInt();
            }
        }

        if (isUserLoggedIn == true) {
            System.out.println("User is successfully logged in");
            chatMenu();
        } else {
            System.out.println("User is not successfully logged in.");
            System.out.println("Please try again.");
            //Run authentication page method
            isUserLoggedIn = existingUser.logIn();
        }
    }

    public static void chatMenu() {

        Scanner inputChatMenuOption = new Scanner(System.in);
        boolean isChatMenuOptionSelected = false;

        while (isChatMenuOptionSelected == false) {
            System.out.println("Welcome to QuickChat <user name> <user surname>.");
            System.out.println("=========== Chat Menu =================");
            System.out.println("Type in the number to open the menu.");
            System.out.println("1. Send Messages.");
            System.out.println("2. Show recently sent messages. (Coming Soon).");
            System.out.println("3. Quit.");
            System.out.println(">> ");

            if (inputChatMenuOption.hasNextInt()) {
                int chatMenuOption = inputChatMenuOption.nextInt();

                switch (chatMenuOption) {
                    case 1 -> {
                        System.out.println("You have selected '1' - ==== Send Messages. ==== .");
                        isChatMenuOptionSelected = true;
                        inputNewMessage();
                    }
                    case 2 -> {
                        System.out.println("You have selected '2' - ==== Show Recently Sent Messages. ==== .");
                        isChatMenuOptionSelected = true;
                    }
                    case 3 -> {
                        System.out.println("You have selected '3' - ==== Quit. ==== .");
                        System.out.println("Thank you for using QuickChat. Goodbye.");
                        isChatMenuOptionSelected = true;
                        System.exit(0);
                    }
                    default ->
                        System.out.println("Invalid option. Please try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                inputChatMenuOption.next(); // Clear invalid input
            }
        }
    }

    public static void inputNewMessage() {
        //Declarations
        int returnedNumberOfMessages;
        boolean isMessageIDTenCharacters;
        boolean doesRecipientNumberMeetRequirements;
        String recipientNumber;
        String returnedRecipientNumber;
        Message newMessage = new Message();

        // ==== Message Quantity Input ====
        System.out.println("How many messages would you like to send? (Type in a number)");
        System.out.print(">> ");
        Scanner inputNumberOfMessages = new Scanner(System.in);
        returnedNumberOfMessages = inputNumberOfMessages.nextInt();

        //Message ID
        Random randomNumber = new Random();
        StringBuilder messageIdString = new StringBuilder();

        for (int index = 0;
                index < 11;
                index++) {
            messageIdString.append(randomNumber.nextInt(10));
        }

        String messageId = messageIdString.toString();

        System.out.println("Message ID: " + messageId);

        isMessageIDTenCharacters = newMessage.checkMessageID(messageId);

        // ==== Receipent Number ====
        System.out.println("Input the receipents number. - Must be no more than ten digits, and include an international code (+27)");
        System.out.print(">> ");
        Scanner inputRecipientNumber = new Scanner(System.in);
        recipientNumber = inputRecipientNumber.nextLine();
        returnedRecipientNumber = newMessage.checkRecipientCell(recipientNumber);

        doesRecipientNumberMeetRequirements = false;
        while (doesRecipientNumberMeetRequirements == false) {
            if ("Incorrect".equals(returnedRecipientNumber)) {
                System.out.println("==============================================================");
                System.out.println("Input the receipents number. - Must be no more than ten digits");
                System.out.print(">> ");
                recipientNumber = inputRecipientNumber.nextLine();
                returnedRecipientNumber = newMessage.checkRecipientCell(recipientNumber);
            } else {
                doesRecipientNumberMeetRequirements = true;
            }
        }

        // ==== New Message =====
        System.out.println("Input your new message");
        System.out.print(">> ");
        Scanner inputNewMessage = new Scanner(System.in);
        String returnedInputMessage = inputNewMessage.nextLine();
        int returnedLengthOfMessage = returnedInputMessage.length();

        boolean isMessageLengthExceeding = true;

        while (isMessageLengthExceeding == true) {
            if (returnedLengthOfMessage <= 250) {
                //The Program may proceed
                isMessageLengthExceeding = false;
                System.out.println("Message successfully captured.");
            } else {
                //The program may mot proceed
                System.out.println("The message exceeds 250 characters by " + returnedLengthOfMessage + ". Please reduce size.");
                System.out.println("Input your new message");
                System.out.print(">> ");
                returnedInputMessage = inputNewMessage.nextLine();
            }
        }

        String messageHash = newMessage.createMessageHash(messageId, returnedInputMessage);

        System.out.println("Message hash" + messageHash);
        System.out.println("!!!! ===== WARNING ===== !!!!");
        System.out.println("WARNING: If dialog box DOES NOT appear after sending a");
        System.out.println("message, MINIMISE Netbeans to reveal it, then return to NetBeans IDE.");
        System.out.println("!!!! ===== END OF WARNING ===== !!!!");
        int choice = newMessage.sentMessage(messageId, messageHash, recipientNumber, returnedInputMessage);

        if (choice == 0) {
            //Send Message
            newMessage.storeMessage(0, messageId, messageHash, recipientNumber, returnedInputMessage);
            newMessage.storeMessageAll(0, messageId, messageHash, recipientNumber, returnedInputMessage);
            //Return to chat menu
            chatMenu();
        } else if (choice == 1) {
            //Save Message for later
            newMessage.storeMessage(1, messageId, messageHash, recipientNumber, returnedInputMessage);
            newMessage.storeMessageAll(1, messageId, messageHash, recipientNumber, returnedInputMessage);
            //Return to chat menu.
            chatMenu();
        } else if (choice == 2) {
            //discard message
            newMessage.storeMessage(2, messageId, messageHash, recipientNumber, returnedInputMessage);
            newMessage.storeMessageAll(2, messageId, messageHash, recipientNumber, returnedInputMessage);
            //Return to chat menu.
            chatMenu();
        }
    }

}
//========== End of Main Method =========

