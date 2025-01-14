package com.fritzdevelopers.ciphermaster;

public class XORCipher extends CommonMethods {

    public static String XOR(String message, char key) {
        String encodedKey = characterToBinary(key);
        String finalMessage = "";

        for (int i = 0; i <= message.length() - 1; i++) {
            String messageBin = characterToBinary(message.charAt(i));
            String binarySymbol = "";

            for (int j = 0; j <= 7; j++) {
                if (messageBin.charAt(j) == encodedKey.charAt(j)) {
                    binarySymbol = binarySymbol + "0";
                } else {
                    binarySymbol = binarySymbol + "1";
                }
            }

            int decimalSymbol = Integer.parseInt(binarySymbol, 2);
            String actualSymbol = Character.toString((char) decimalSymbol);
            finalMessage = finalMessage + actualSymbol;
        }

        return finalMessage;
    }

    public static String XORDecrypt(String encryptedMessage, char key) {
        String encodedKey = characterToBinary(key);
        String originalMessage = "";

        for (int i = 0; i <= encryptedMessage.length()-1; i++) {
            String encryptedMessageBin = characterToBinary(encryptedMessage.charAt(i));
            String binarySymbol = "";

            for (int j = 0; j <= 7; j++) {
                if (encryptedMessageBin.charAt(j) == encodedKey.charAt(j)) {
                    binarySymbol = binarySymbol + "0";
                }
                else {
                    binarySymbol = binarySymbol + "1";
                }
            }

            int decimalSymbol = Integer.parseInt(binarySymbol, 2);
            String originalSymbol = Character.toString((char) decimalSymbol);
            originalMessage = originalMessage + originalSymbol;
        }
        return originalMessage;
    }
}
