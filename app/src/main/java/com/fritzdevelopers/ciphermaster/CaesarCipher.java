package com.fritzdevelopers.ciphermaster;

public class CaesarCipher extends CommonMethods{

    public static String caesar(String message, int key) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String intermediateMessage = "";
        String newAlphabet = shiftAlphabet(key);
        for (int i = 0; i < normalizeText(message).length(); i++) {
            char letterBefore = normalizeText(message).charAt(i);
            int letterPlace = alphabet.indexOf(letterBefore);
            char letterAfter = newAlphabet.charAt(letterPlace);
            intermediateMessage = intermediateMessage + letterAfter;
        }
        return convertToOriginal(intermediateMessage, message);
    }

    public static String caesarDecrypt(String encryptedMessage, int key) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String originalMessage = "";
        String newAlphabet = shiftAlphabet(key);
        for (int i = 0; i < normalizeText(encryptedMessage).length(); i++) {
            char letterBefore = normalizeText(encryptedMessage).charAt(i);
            int letterPlace = newAlphabet.indexOf(letterBefore);
            char letterAfter = alphabet.charAt(letterPlace);
            originalMessage = originalMessage + letterAfter;
        }
        return convertToOriginal(originalMessage, encryptedMessage);
    }
}
