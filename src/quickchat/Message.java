/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quickchat;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Pako Tone
 */
public class Message {

    public boolean checkMessageID(String inputMessageID) {
        boolean isMessageIDTenCharacters;
        isMessageIDTenCharacters = false;

        if (inputMessageID.length() == 10) {
            isMessageIDTenCharacters = true;
        }

        return isMessageIDTenCharacters;
    }

    public String checkRecipientCell(String inputRecipientCell) {
        String recipientCell;
        if (inputRecipientCell.matches("\\+27\\d{9,10}") || inputRecipientCell.matches("\\d{9,11}")) {
            recipientCell = inputRecipientCell;
            System.out.println("Cell phone number successfully captured.");
        } else {
            recipientCell = "Incorrect";
            System.out.println("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again");
        }
        return recipientCell;
    }

    public String createMessageHash(String inputMessageId,
            String inputMessage) {
        String twoNumbers = inputMessageId.substring(0, 2);
        System.out.println("Two numbers: " + twoNumbers);

        // Remove leading/trailing spaces and split by one or more spaces
        String[] words = inputMessage.trim().split("\\s+");
        String firstWord = null;
        String lastWord = null;

        if (words.length > 0) {
            firstWord = words[0];
            lastWord = words[words.length - 1];
        } else {
            System.out.println("The sentence is empty or contains only spaces.");
        }

        StringBuilder messageHash = new StringBuilder();
        messageHash.append(twoNumbers);
        messageHash.append(":");
        messageHash.append("0");
        messageHash.append(":");
        messageHash.append(firstWord);
        messageHash.append(lastWord);

        String messageHashString = messageHash.toString();
        return messageHashString;
    }

    public int sentMessage(String messageID,
            String messageHash,
            String recipientNumber,
            String userMessage) {
        String listOfText = "Message ID: " + messageID + "\n" + "Message Hash: " + messageHash + "\n" + "Recipient Number: " + recipientNumber + "\n" + "Message: " + userMessage;

        Object[] options = {"Send Message", "Save Message For Later", "Discard Message"};

        int choice = JOptionPane.showOptionDialog(null, listOfText, "Message Options", JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        return choice;
    }

//    public String printMessages() {
//        return printMessages;
//    }
//
//    public int returnTotalMessages(String inputFilePath) {
//        String filePath = inputFilePath;
//        
//        try {
//            
//        } catch {
//            
//        }
//        
//        return totalMessages;
//    }

    public void storeMessage(int inputOption,
            String inputMessageID,
            String inputMessageHash,
            String inputRecipientNumber,
            String inputReturnedInputMessage) {

        String filePath;

        if (inputOption == 0) {
            //==== Send Message ====
            filePath = "listOfSentMessages.json";

            JSONArray parsedFilePath = new JSONArray();

            // =========================== listOfSentMessages.json =========================================
            //Read existing file
            boolean isFileCreated = false;
            while (isFileCreated == false) {
                try {
                    if (Files.exists(Paths.get(filePath))) {
                        String fileContents = new String(Files.readAllBytes(Paths.get(filePath)));
                        parsedFilePath = new JSONArray(fileContents);
                        isFileCreated = true;
                    } else {
                        try{
                            FileWriter file = new FileWriter(filePath);
                            isFileCreated = true;
                        } catch (IOException error){
                            System.out.print("File failed to be created. Trying again");
                            isFileCreated = false;
                        }
                    }
                } catch (IOException error) {
                    System.out.println("Something went wrong while saving the message: \n" + error.getMessage());
                    isFileCreated = false;
                }
            }

            //Initialise array
            JSONObject sendMessage = new JSONObject();

            sendMessage.put("Message ID", inputMessageID);
            sendMessage.put("Message Hash", inputMessageHash);
            sendMessage.put("Recipient Number", inputRecipientNumber);
            sendMessage.put("Sent Message", inputReturnedInputMessage);
            sendMessage.put("Flag","Sent");

            //Update file
            parsedFilePath.put(sendMessage);

            boolean isFileUpdated = false;
            while (isFileUpdated == false) {
                try (FileWriter file = new FileWriter(filePath)) {
                    file.write(parsedFilePath.toString(4));  // 4 = pretty print indent
                    file.flush();
                    System.out.println("Success: JSON written to file successfully.");
                    isFileUpdated = true;
                } catch (IOException error) {
                    System.out.println("Something went wrong while sending the message: \n" + error.getMessage());
                    isFileUpdated = false;
                }
            }
            // =========================== END OF listOfSentMessages.json =========================================

        } else if (inputOption == 1) {
            //==== Save Message for later ====
            filePath = "listOfSavedMessages.json";
            JSONArray parsedFilePath = new JSONArray();

            // =========================== listOfSavedMessages.json =========================================
            //Read existing file
            try {
                if (Files.exists(Paths.get(filePath))) {
                    String fileContents = new String(Files.readAllBytes(Paths.get(filePath)));
                    parsedFilePath = new JSONArray(fileContents);
                } else {
                    System.out.println("File does not exist. Writing new file to storage.");
                    try {
                        FileWriter file = new FileWriter("listOfSavedMessages.json");
                    } catch (IOException error) {
                        System.out.println("An error occurred.");
                        System.out.println(error.getMessage());
                    }
                }
            } catch (IOException error) {
                System.out.println("Something went wrong while saving the message: \n" + error.getMessage());
            }
            
            //Initialise array
            JSONObject saveMessage = new JSONObject();

            saveMessage.put("Message ID", inputMessageID);
            saveMessage.put("Message Hash", inputMessageHash);
            saveMessage.put("Recipient Number", inputRecipientNumber);
            saveMessage.put("Sent Message", inputReturnedInputMessage);
            saveMessage.put("Flag", "Stored");

            //Update file
            parsedFilePath.put(saveMessage);

            try (FileWriter file = new FileWriter("listOfSavedMessages.json")) {
                file.write(parsedFilePath.toString(4));  // 4 = pretty print indent
                file.flush();
                System.out.println("SUCCESS: JSON written to file successfully.");
            } catch (IOException error) {
                System.out.println("Something went wrong while saving the message: \n" + error.getMessage());
            }
            
            // =========================== END OF listOfSavedMessages.json =========================================

        } else if (inputOption == 2) {
            //==== Discard Message ====
            filePath = "listOfDiscardedMessages.json";
            JSONArray parsedFilePath = new JSONArray();

            // =========================== listOfDiscardedMessages.json =========================================
            //Read existing file
            try {
                if (Files.exists(Paths.get(filePath))) {
                    String fileContents = new String(Files.readAllBytes(Paths.get(filePath)));
                    parsedFilePath = new JSONArray(fileContents);
                } else {
                    System.out.println("File does not exist. Writing new file to storage.");
                    try {
                        FileWriter file = new FileWriter(filePath);
                    } catch (IOException error) {
                        System.out.println("An error occurred.");
                        System.out.println(error.getMessage());
                    }
                }
            } catch (IOException error) {
                System.out.println("Something went wrong while saving the message: \n" + error.getMessage());
            }
            
            //Initialise array
            JSONObject discardMessage = new JSONObject();

            discardMessage.put("Message ID", inputMessageID);
            discardMessage.put("Message Hash", inputMessageHash);
            discardMessage.put("Recipient Number", inputRecipientNumber);
            discardMessage.put("Sent Message", inputReturnedInputMessage);
            discardMessage.put("Flag", "Discarded");

            //Update file
            parsedFilePath.put(discardMessage);

            try (FileWriter file = new FileWriter(filePath)) {
                file.write(parsedFilePath.toString(4));  // 4 = pretty print indent
                file.flush();
                System.out.println("SUCCESS: JSON written to file successfully.");
            } catch (IOException error) {
                System.out.println("Something went wrong while saving the message: \n" + error.getMessage());
            }
            
            // =========================== END OF listOfDiscardedMessages.json =========================================
        }
    }
    
    public void storeMessageAll(int inputOption, String inputMessageID,String inputMessageHash,String inputRecipientNumber,String inputReturnedInputMessage) {
        String filePathTwo = "listOfAllMessages.json";
        
        if (inputOption == 0) {
            //==== Send Message ====

            JSONArray parsedFilePath = new JSONArray();

            // =========================== listOfSentMessages.json =========================================
            //Read existing file
            boolean isFileCreated = false;
            while (isFileCreated == false) {
                try {
                    if (Files.exists(Paths.get(filePathTwo))) {
                        String fileContents = new String(Files.readAllBytes(Paths.get(filePathTwo)));
                        parsedFilePath = new JSONArray(fileContents);
                        isFileCreated = true;
                    } else {
                        try{
                            FileWriter file = new FileWriter(filePathTwo);
                            isFileCreated = true;
                        } catch (IOException error){
                            System.out.print("File failed to be created. Trying again");
                            isFileCreated = false;
                        }
                    }
                } catch (IOException error) {
                    System.out.println("Something went wrong while saving the message: \n" + error.getMessage());
                    isFileCreated = false;
                }
            }

            //Initialise array
            JSONObject sendMessage = new JSONObject();

            sendMessage.put("Message ID", inputMessageID);
            sendMessage.put("Message Hash", inputMessageHash);
            sendMessage.put("Recipient Number", inputRecipientNumber);
            sendMessage.put("Sent Message", inputReturnedInputMessage);
            sendMessage.put("Flag","Sent");

            //Update file
            parsedFilePath.put(sendMessage);

            boolean isFileUpdated = false;
            while (isFileUpdated == false) {
                try (FileWriter file = new FileWriter(filePathTwo)) {
                    file.write(parsedFilePath.toString(4));  // 4 = pretty print indent
                    file.flush();
                    System.out.println("Success: JSON written to file successfully.");
                    isFileUpdated = true;
                } catch (IOException error) {
                    System.out.println("Something went wrong while sending the message: \n" + error.getMessage());
                    isFileUpdated = false;
                }
            }
            // =========================== END OF listOfSentMessages.json =========================================

        } else if (inputOption == 1) {
            //==== Save Message for later ====
            filePathTwo = "listOfAllMessages.json";
            JSONArray parsedFilePath = new JSONArray();

            // =========================== listOfSavedMessages.json =========================================
            //Read existing file
            try {
                if (Files.exists(Paths.get(filePathTwo))) {
                    String fileContents = new String(Files.readAllBytes(Paths.get(filePathTwo)));
                    parsedFilePath = new JSONArray(fileContents);
                } else {
                    System.out.println("File does not exist. Writing new file to storage.");
                    try {
                        FileWriter file = new FileWriter("listOfSavedMessages.json");
                    } catch (IOException error) {
                        System.out.println("An error occurred.");
                        System.out.println(error.getMessage());
                    }
                }
            } catch (IOException error) {
                System.out.println("Something went wrong while saving the message: \n" + error.getMessage());
            }
            
            //Initialise array
            JSONObject saveMessage = new JSONObject();

            saveMessage.put("Message ID", inputMessageID);
            saveMessage.put("Message Hash", inputMessageHash);
            saveMessage.put("Recipient Number", inputRecipientNumber);
            saveMessage.put("Sent Message", inputReturnedInputMessage);
            saveMessage.put("Flag", "Stored");

            //Update file
            parsedFilePath.put(saveMessage);

            try (FileWriter file = new FileWriter("listOfSavedMessages.json")) {
                file.write(parsedFilePath.toString(4));  // 4 = pretty print indent
                file.flush();
                System.out.println("SUCCESS: JSON written to file successfully.");
            } catch (IOException error) {
                System.out.println("Something went wrong while saving the message: \n" + error.getMessage());
            }
            
            // =========================== END OF listOfSavedMessages.json =========================================

        } else if (inputOption == 2) {
            //==== Discard Message ====
            JSONArray parsedFilePath = new JSONArray();

            // =========================== listOfDiscardedMessages.json =========================================
            //Read existing file
            try {
                if (Files.exists(Paths.get(filePathTwo))) {
                    String fileContents = new String(Files.readAllBytes(Paths.get(filePathTwo)));
                    parsedFilePath = new JSONArray(fileContents);
                } else {
                    System.out.println("File does not exist. Writing new file to storage.");
                    try {
                        FileWriter file = new FileWriter(filePathTwo);
                    } catch (IOException error) {
                        System.out.println("An error occurred.");
                        System.out.println(error.getMessage());
                    }
                }
            } catch (IOException error) {
                System.out.println("Something went wrong while saving the message: \n" + error.getMessage());
            }
            
            //Initialise array
            JSONObject discardMessage = new JSONObject();

            discardMessage.put("Message ID", inputMessageID);
            discardMessage.put("Message Hash", inputMessageHash);
            discardMessage.put("Recipient Number", inputRecipientNumber);
            discardMessage.put("Sent Message", inputReturnedInputMessage);
            discardMessage.put("Flag", "Discarded");

            //Update file
            parsedFilePath.put(discardMessage);

            try (FileWriter file = new FileWriter(filePathTwo)) {
                file.write(parsedFilePath.toString(4));  // 4 = pretty print indent
                file.flush();
                System.out.println("SUCCESS: JSON written to file successfully.");
            } catch (IOException error) {
                System.out.println("Something went wrong while saving the message: \n" + error.getMessage());
            }
            
            // =========================== END OF listOfDiscardedMessages.json =========================================
        }
    }
    
}
