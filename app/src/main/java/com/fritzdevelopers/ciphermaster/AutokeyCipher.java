package com.fritzdevelopers.ciphermaster;

public class AutokeyCipher extends CommonMethods{

    public static String autokey(String message, char keyLetter) {
        String normalisedMessage = normalizeText(message);
        String finalMessage = numberToLetter(letterToNumber(keyLetter) + letterToNumber(normalisedMessage.charAt(0)));

        for (int i = 0; i <= normalisedMessage.length()-2; i++) {
            int finalNumber = letterToNumber(normalisedMessage.charAt(i)) + letterToNumber(normalisedMessage.charAt(i+1));
            finalMessage = finalMessage + numberToLetter(finalNumber);
            //System.out.println(Character.toString(message.charAt(i)) + " " +Character.toString(message.charAt(i+1)));
        }

        return convertToOriginal(finalMessage, message);
    }

    public static String autokeyDecrypt(String encryptedMessage, char keyLetter) {
        String normalisedEncryptedMessage = normalizeText(encryptedMessage);
        String originalMessage = "";
        int initialNumber = letterToNumber(normalisedEncryptedMessage.charAt(0)) - letterToNumber(keyLetter);

        if (initialNumber < 0) {
            initialNumber += 26;
        }
        else {
            initialNumber = initialNumber % 26;
        }

        originalMessage = originalMessage + numberToLetter(initialNumber);

        for (int i = 1; i <= normalisedEncryptedMessage.length()-1; i++) {
            initialNumber = letterToNumber(normalisedEncryptedMessage.charAt(i)) - initialNumber;

            if (initialNumber < 0) {
                initialNumber += 26;
            }
            else {
                initialNumber = initialNumber % 26;
            }

            originalMessage = originalMessage + numberToLetter(initialNumber);
        }
        return convertToOriginal(originalMessage, encryptedMessage);
    }


}
