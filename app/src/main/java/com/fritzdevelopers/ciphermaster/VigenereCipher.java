package com.fritzdevelopers.ciphermaster;

public class VigenereCipher extends CommonMethods{

    public static String vigenere(String message, String key) {
        String normalisedMessage = normalizeText(message);
        String keyUpper = normalizeText(key);
        String encryptedMessage = "";
        int count = 0;
        int finalNumber;
        String replaceCharacter;

        for (int i = 0; i <= normalisedMessage.length()-1; i++) {
            finalNumber = letterToNumber(keyUpper.charAt(count)) + letterToNumber(normalisedMessage.charAt(i));
            replaceCharacter = numberToLetter(finalNumber);
            encryptedMessage = encryptedMessage + replaceCharacter;
            //System.out.println(letterToNumber(keyUpper.charAt(count)) + " " + letterToNumber(message.charAt(count)) + " = " + finalNumber + " " + replaceCharacter);
            if (count == keyUpper.length()-1) {
                count = 0;
            }
            else { count++; }
        }
        return convertToOriginal(encryptedMessage, message);
    }

    public static String vigenereDecrypt(String encryptedMessage, String key) {
        String normalisedEncryptedMessage = normalizeText(encryptedMessage);
        String keyUpper = normalizeText(key);
        String originalMessage = "";
        int count = 0;
        int initialNumber;
        String originalCharacter;

        for (int i = 0; i <= normalisedEncryptedMessage.length()-1; i++) {
            initialNumber = letterToNumber(normalisedEncryptedMessage.charAt(i)) - letterToNumber(keyUpper.charAt(count));

            if (initialNumber < 0) {
                initialNumber += 26;
            }

            originalCharacter = numberToLetter(initialNumber);
            originalMessage = originalMessage + originalCharacter;

            if(count == keyUpper.length()-1) {
                count = 0;
            }
            else { count++; }
        }
        return convertToOriginal(originalMessage, encryptedMessage);
    }

}
