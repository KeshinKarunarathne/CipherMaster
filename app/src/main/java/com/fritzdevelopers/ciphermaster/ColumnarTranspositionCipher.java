package com.fritzdevelopers.ciphermaster;

public class ColumnarTranspositionCipher extends CommonMethods{

    public static String columnarTrans(String message, String key) {
        String normalisedMessage = normalizeText(message);
        String keyUpper = unrepeatedKey(normalizeText(key));
        String finalMessage = "";
        int noOfRows;

        if ((normalisedMessage.length() % keyUpper.length()) == 0) {
            noOfRows = normalisedMessage.length() / keyUpper.length();
        } else {
            noOfRows = (normalisedMessage.length() / keyUpper.length()) + 1;
        }

        String columnar[] = new String[noOfRows];

        int count = 0;

        for (int i = 0; i <= noOfRows-1; i++) {
            if (i == noOfRows-1) {
                String filler = "xxxxxxxxxxxxxxxxxxxx";
                columnar[i] = normalisedMessage.substring(count, normalisedMessage.length());
                columnar[i] = columnar[i] + filler.substring(0, keyUpper.length()-columnar[i].length());
                //columnar[i] = columnar[i] + filler.repeat(keyUpper.length()-columnar[i].length());
            } else {
                columnar[i] = normalisedMessage.substring(count, count + keyUpper.length());
                count += keyUpper.length();
            }
        }

        String orderedKey = keyOrder(keyUpper);

        for (int i = 0; i <= orderedKey.length() - 1; i++) {
            char keyLetter = orderedKey.charAt(i);
            int column = keyUpper.indexOf(keyLetter);

            for (int j = 0; j <= noOfRows - 1; j++) {
                String letter = Character.toString(columnar[j].charAt(column));
                finalMessage = finalMessage + letter;

                if (j == noOfRows-1) {
                    finalMessage = finalMessage + " ";
                }
            }
        }
        return finalMessage;
    }

    public static String columnarTransDecrypt(String encryptedMessage, String key) {
        String keyUpper = unrepeatedKey(normalizeText(key));
        String normalizedEncryptedMessage = normalizeTextWithSpaces(encryptedMessage);
        //System.out.println(normalizedEncryptedMessage);

        int count = 0;

        while (normalizedEncryptedMessage.charAt(count) != ' ') {
            count++;
        }

        //System.out.println(count);

        int noOfRows = count;
        String[] columnar = new String[noOfRows];

        for (int i = 0; i <= noOfRows - 1; i++) {
            String letterRow = "";
            int counter = i;

            while (counter <= normalizedEncryptedMessage.length() - 1) {
                letterRow = letterRow + normalizedEncryptedMessage.charAt(counter);
                counter += (noOfRows+1);
            }

            //System.out.println(letterRow);
            columnar[i] = letterRow;
        }

        String orderedKey = keyOrder(keyUpper);

        String[] orderedColumnar = new String[noOfRows];

        for (int i = 0; i <= orderedColumnar.length - 1; i++) {
            String letterRow = "";

            for (int j = 0; j <= keyUpper.length() - 1; j++) {
                char letter = keyUpper.charAt(j);
                int letterPlace = orderedKey.indexOf(letter);

                letterRow = letterRow + columnar[i].charAt(letterPlace);
            }

            //System.out.println(letterRow);
            orderedColumnar[i] = letterRow;
        }

        String originalMessage = "";

        for (int i = 0; i <= orderedColumnar.length - 1; i++) {
            originalMessage = originalMessage + orderedColumnar[i];
        }

        return originalMessage;
    }

}