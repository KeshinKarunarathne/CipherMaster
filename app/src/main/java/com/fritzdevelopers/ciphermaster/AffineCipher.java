package com.fritzdevelopers.ciphermaster;

public class AffineCipher extends CommonMethods{

    public static String affine(String message, int alpha, int beta) {
        String messageNormalised = CommonMethods.normalizeText(message);
        String finalMessage = "";

        for (int i = 0; i <= messageNormalised.length()-1; i++) {
            int letterNumber = CommonMethods.letterToNumber(messageNormalised.charAt(i));
            int replaceNumber = (alpha*letterNumber) + beta;
            String replaceLetter = numberToLetter(replaceNumber);
            finalMessage = finalMessage + replaceLetter;
        }
        return convertToOriginal(finalMessage, message);
    }

    public static String affineDecrypt(String encryptedMessage, int alpha, int beta) {
        int inverseOfAlpha = modularInverse(alpha, 26);
        int initialNumber;
        String encryptedMessageNormalised = normalizeText(encryptedMessage);
        String originalMessage = "";

        for (int i = 0; i <= encryptedMessageNormalised.length()-1; i++) {
            initialNumber = (letterToNumber(encryptedMessageNormalised.charAt(i)) - beta) * inverseOfAlpha;

            while (initialNumber < 0) {
                initialNumber += 26;
            }

            initialNumber = initialNumber % 26;

            originalMessage = originalMessage + numberToLetter(initialNumber);
        }
        return convertToOriginal(originalMessage, encryptedMessage);
    }


}
