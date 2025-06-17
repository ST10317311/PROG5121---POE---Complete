/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package quickchat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pako Tone
 */
public class MessageTest {

    public MessageTest() {
    }

    //Used to scan an array of messages, making sure they are correctly populated with the correct information.
    @Test
    public void testMessagesAreCorrectlyPopulated() {
        String filePath = "listOfAllMessages.json";

        String fetchedMessage = null;

        try {
            //Step 1 - Reads all the file content and converts it into a Java String.
            String stringFilePath = new String(Files.readAllBytes(Paths.get(filePath)));

            //Step 2 - Converts the Java String into a Java Array.
            JSONArray parsedFilePath = new JSONArray(stringFilePath);

            //Step 3 - The Java array then scans all Java Objects for messages only.
            for (int index = 0;
                    index < parsedFilePath.length();
                    index++) {
                JSONObject messageIndex = parsedFilePath.getJSONObject(index);

                if (messageIndex.has("Sent Message") && !messageIndex.isNull("Sent Message")) {
                    fetchedMessage = messageIndex.getString("Sent Message");
                    System.out.println(fetchedMessage);
                } else {
                    System.out.println(fetchedMessage);
                    System.out.println("Message is missing from message " + index);
                }
            }
        } catch (IOException error) {
            error.getMessage();
        }
    }

    //Used to find the longest message in all messages.
    @Test
    public void testLongestMessage() {
        String filePath = "listOfAllMessages.json";
        JSONArray parsedFilePath = new JSONArray();
        String fetchedMessage = null;

        try {
            //Step 1 - Reads all the file content and converts it into a Java String.
            String stringFilePath = new String(Files.readAllBytes(Paths.get(filePath)));

            //Step 2 - Converts the Java String into a Java Array.
            parsedFilePath = new JSONArray(stringFilePath);

            //Step 3 - these are variables that are used to store a strings length and the longest length of message from the array.
            String temporaryStoredMessage = "";
            int maximumStringLength = 0;

            //For-loop used to look for the longest message, using Step 3's initialised variables.
            for (int index = 0;
                    index < parsedFilePath.length();
                    index++) {
                JSONObject messageIndex = parsedFilePath.getJSONObject(index);
                String message = messageIndex.getString("Sent Message");

                if (message.length() > maximumStringLength) {
                    maximumStringLength = message.length();
                    temporaryStoredMessage = message;
                }
            }

            System.out.println("Longest message: " + temporaryStoredMessage);
        } catch (IOException error) {
            error.getMessage();
        }
    }

    //USed to search for message ID using recipient number.
    @Test
    public void testSearchForMessageID() {
        String filePath = "listOfAllMessages.json";
        JSONArray parsedFilePath = new JSONArray();
        String fetchedMessageID = null;
        String fetchedMessage = null;

        try {
            //Step 1 - Reads all the file content and converts it into a Java String.
            String stringFilePath = new String(Files.readAllBytes(Paths.get(filePath)));

            //Step 2 - Converts the Java String into a Java Array.
            parsedFilePath = new JSONArray(stringFilePath);

            //For-loop used to look for the longest message, using Step 3's initialised variables.
            for (int index = 0;
                    index < parsedFilePath.length();
                    index++) {
                JSONObject messageIndex = parsedFilePath.getJSONObject(index);
                String recipientNumber = messageIndex.getString("Recipient Number");

                if ("0838884567".equals(recipientNumber)) {
                    fetchedMessageID = messageIndex.getString("Message ID");
                    fetchedMessage = messageIndex.getString("Sent Message");
                }
            }

            System.out.println("Message ID: " + fetchedMessageID);
        } catch (IOException error) {
            error.getMessage();
        }
    }

    //Used to find messages using recipient number
    @Test
    public void testMessageSearchWithRecipientNumber() {
        String filePath = "listOfAllMessages.json";
        JSONArray parsedFilePath = new JSONArray();
        String fetchedMessage = null;

        try {
            //Step 1 - Reads all the file content and converts it into a Java String.
            String stringFilePath = new String(Files.readAllBytes(Paths.get(filePath)));

            //Step 2 - Converts the Java String into a Java Array.
            parsedFilePath = new JSONArray(stringFilePath);

            //For-loop used to look for a message regarding a recipient's phone number.
            for (int index = 0;
                    index < parsedFilePath.length();
                    index++) {
                JSONObject messageIndex = parsedFilePath.getJSONObject(index);
                String recipientNumber = messageIndex.getString("Recipient Number");

                if ("+27838884567".equals(recipientNumber)) {
                    fetchedMessage = messageIndex.getString("Sent Message");
                }
            }
            System.out.println("Message: " + fetchedMessage);
        } catch (IOException error) {
            error.getMessage();
        }
    }
    
    // Used to display all messages that are SENT.
    @Test
    public void testDisplayReportOfAllMessagesSent(){
        String filePath = "listOfSentMessages.json";
        JSONArray parsedFilePath = new JSONArray();

        try {
            //Step 1 - Reads all the file content and converts it into a Java String.
            String stringFilePath = new String(Files.readAllBytes(Paths.get(filePath)));

            //Step 2 - Converts the Java String into a Java Array.
            parsedFilePath = new JSONArray(stringFilePath);

            System.out.println("===================== All messages =========================");
            //For-loop used to display all the messages.
            for (int index = 0; index < parsedFilePath.length(); index++) {
                JSONObject messageIndex = parsedFilePath.getJSONObject(index);
                
                String messageHash = messageIndex.getString("Message Hash");
                String recipientNumber = messageIndex.getString("Recipient Number");
                String sentMessage = messageIndex.getString("Sent Message");
                String flag = messageIndex.getString("Flag");
                String messageID = messageIndex.getString("Message ID");
                
                System.out.println("=======================================");
                System.out.println("Message Hash: " + messageHash);
                System.out.println("Recipient Number: " + recipientNumber);
                System.out.println("Sent Message: " + sentMessage);
                System.out.println("Flag: " + flag);
                System.out.println("Message ID: " + messageID);
                System.out.println("=======================================");
            }
        } catch (IOException error) {
            System.out.println("An error occured: " + error.getMessage());
        }
    }
}
