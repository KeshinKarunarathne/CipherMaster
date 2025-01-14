package com.fritzdevelopers.ciphermaster;

public class BifidCipher extends CommonMethods {

    public static String bifid(String message, String key) {
        String[] filledPolybius = polybius(removeJ(normalizeText(key)));
        String evenMessage = removeJ(normalizeText(message));

        if (evenMessage.length() % 2 != 0) {
            evenMessage = evenMessage + "X";
        }

        Integer[] rows = new Integer[evenMessage.length()];
        Integer[] columns = new Integer[evenMessage.length()];

        for (int i = 0; i <= evenMessage.length() - 1; i++) {
            for (int j = 0; j <= filledPolybius.length - 1; j++) {
                for (int k = 0; k <= 4; k++) {
                    if (filledPolybius[j].charAt(k) == evenMessage.charAt(i)) {
                        rows[i] = j;
                        columns[i] = k;
                        //break;
                    }
                }
            }
        }

        Integer[] finalNumbers = new Integer[evenMessage.length() * 2];

        for (int i = 0; i <= evenMessage.length()-1; i++) {
            finalNumbers[i] = rows[i];
        }
        for (int j = evenMessage.length(); j <= finalNumbers.length - 1; j++) {
            finalNumbers[j] = columns[j - evenMessage.length()];
        }

        String finalMessage = "";

        for (int i = 0; i <= finalNumbers.length-2; i += 2) {
            finalMessage = finalMessage + filledPolybius[finalNumbers[i]].charAt(finalNumbers[i + 1]);
        }

        if ((normalizeText(message).length() % 2) != 0 ) {
            finalMessage = finalMessage.substring(0, finalMessage.length()-1);
        }

        return convertToOriginal(finalMessage, message);
    }

    public static String bifidDecrypt(String encryptedMessage, String key) {
        String[] filledPolybius = polybius(removeJ(normalizeText(key)));

        String evenEncryptedMessage = removeJ(normalizeText(encryptedMessage));

        if (evenEncryptedMessage.length() % 2 != 0) {
            evenEncryptedMessage = evenEncryptedMessage + "X";
        }

        Integer[] rowsAndColumns = new Integer[evenEncryptedMessage.length() * 2];

        int count = 0;

        for (int i = 0; i <= evenEncryptedMessage.length() - 1; i++) {
            for (int j = 0; j <= filledPolybius.length - 1; j++) {
                for (int k = 0; k <= 4; k++) {
                    if (filledPolybius[j].charAt(k) == evenEncryptedMessage.charAt(i)) {
                        rowsAndColumns[count] = j;
                        rowsAndColumns[count + 1] = k;
                        count += 2;
                    }
                }
            }
        }

        String originalMessage = "";

        for (int i = 0; i <= (rowsAndColumns.length / 2) - 1; i++) {
            originalMessage = originalMessage + filledPolybius[rowsAndColumns[i]].charAt(rowsAndColumns[i + (rowsAndColumns.length/2)]);
        }

        if ((normalizeText(encryptedMessage).length() % 2) != 0 ) {
            originalMessage = originalMessage.substring(0, originalMessage.length()-1);
        }

        return convertToOriginal(originalMessage, encryptedMessage) ;
        }
}
