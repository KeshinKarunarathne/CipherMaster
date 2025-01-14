package com.fritzdevelopers.ciphermaster;

public class FourSquareCipher extends CommonMethods{

    public static String fourSquare(String message, String firstKey, String secondKey) {
        String[] plainPolybius = polybius("");
        String[] firstKeyPolybius = polybius(removeJ(normalizeText(firstKey)));
        String[] secondKeyPolybius = polybius(removeJ(normalizeText(secondKey)));

        String evenMessage = removeJ(normalizeText(message));

        if (evenMessage.length() % 2 != 0) {
            evenMessage = evenMessage + "X";
        }

        Integer[] rows = new Integer[evenMessage.length()];
        Integer[] columns = new Integer[evenMessage.length()];

        for (int i = 0; i <= evenMessage.length() - 1; i++) {
            for (int j = 0; j <= 4; j++) {
                for (int k = 0; k <= 4; k++) {
                    if (evenMessage.charAt(i) == plainPolybius[j].charAt(k)) {
                        rows[i] = j;
                        columns[i] = k;
                        break;
                    }
                }
            }
        }

        String finalMessage = "";

        for (int i = 0; i <= rows.length - 1; i++) {
            if ((i % 2) == 0) {
               finalMessage = finalMessage + firstKeyPolybius[rows[i]].charAt(columns[i + 1]);
            } else {
               finalMessage = finalMessage + secondKeyPolybius[rows[i]].charAt(columns[i - 1]);
            }
        }

        return convertToOriginal(finalMessage, message);
    }

    public static String fourSquareDecrypt(String encryptedMessage, String firstKey, String secondKey) {
        String[] plainPolybius = polybius("");
        String[] firstKeyPolybius = polybius(removeJ(normalizeText(firstKey)));
        String[] secondKeyPolybius = polybius(removeJ(normalizeText(secondKey)));

        String evenEncryptedMessage = removeJ(normalizeText(encryptedMessage));

        if (evenEncryptedMessage.length() % 2 != 0) {
            evenEncryptedMessage = evenEncryptedMessage + "X";
        }

        Integer[] rows = new Integer[evenEncryptedMessage.length()];
        Integer[] columns = new Integer[evenEncryptedMessage.length()];

        for (int i = 0; i <= evenEncryptedMessage.length()-1; i += 2) {
            for (int j = 0; j <= 4; j++) {
                for (int k = 0; k <= 4; k++) {
                    if (evenEncryptedMessage.charAt(i) == firstKeyPolybius[j].charAt(k)) {
                        rows[i] = j;
                        columns[i+1] = k;
                    }
                    if (evenEncryptedMessage.charAt(i+1) == secondKeyPolybius[j].charAt(k)) {
                        rows[i+1] = j;
                        columns[i] = k;
                    }
                }
            }
        }

        String originalMessage = "";

        for (int i = 0; i <= rows.length-1; i++) {
            originalMessage = originalMessage + plainPolybius[rows[i]].charAt(columns[i]);
        }

        return convertToOriginal(originalMessage, encryptedMessage);
    }
}
