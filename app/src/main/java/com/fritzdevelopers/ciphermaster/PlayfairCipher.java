package com.fritzdevelopers.ciphermaster;

public class PlayfairCipher extends CommonMethods{

   public static String playfair(String message) {
        String[] alphabetWithoutJ = polybius("");

        String evenMessage = removeJ(normalizeText(message));

        if ((evenMessage.length() % 2) != 0) {
            evenMessage = evenMessage + "X";
        }

        String unrepeatedMessage = unrepeatedMessage(evenMessage);
        String finalMessage = "";

        for (int i = 0; i <= unrepeatedMessage.length()-1; i += 2) {
            //System.out.println(finalMessage);
            //If both characters are in the same row
            if (RowAndColumn(unrepeatedMessage.charAt(i))[0].equals(RowAndColumn(unrepeatedMessage.charAt(i+1))[0])) {
                if (RowAndColumn(unrepeatedMessage.charAt(i))[1] == 4) {
                    finalMessage = finalMessage +  alphabetWithoutJ[RowAndColumn(unrepeatedMessage.charAt(i))[0]].charAt(0);
                    finalMessage = finalMessage + alphabetWithoutJ[RowAndColumn(unrepeatedMessage.charAt(i+1))[0]].charAt(RowAndColumn(unrepeatedMessage.charAt(i+1))[1]+1);
                }
                else if (RowAndColumn(unrepeatedMessage.charAt(i+1))[1] == 4) {
                    finalMessage = finalMessage + alphabetWithoutJ[RowAndColumn(unrepeatedMessage.charAt(i))[0]].charAt(RowAndColumn(unrepeatedMessage.charAt(i))[1]+1);
                    finalMessage = finalMessage + alphabetWithoutJ[RowAndColumn(unrepeatedMessage.charAt(i+1))[0]].charAt(0);
                }
                else {
                    finalMessage = finalMessage + alphabetWithoutJ[RowAndColumn(unrepeatedMessage.charAt(i))[0]].charAt(RowAndColumn(unrepeatedMessage.charAt(i))[1]+1);
                    finalMessage = finalMessage + alphabetWithoutJ[RowAndColumn(unrepeatedMessage.charAt(i+1))[0]].charAt(RowAndColumn(unrepeatedMessage.charAt(i+1))[1]+1);
                }
            }
            //If both characters are in the same column
            else if (RowAndColumn(unrepeatedMessage.charAt(i))[1].equals(RowAndColumn(unrepeatedMessage.charAt(i+1))[1])) {
                if (RowAndColumn(unrepeatedMessage.charAt(i))[0] == 4) {
                    finalMessage = finalMessage + alphabetWithoutJ[0].charAt(RowAndColumn(unrepeatedMessage.charAt(i))[1]);
                    finalMessage = finalMessage + alphabetWithoutJ[RowAndColumn(unrepeatedMessage.charAt(i+1))[0]+1].charAt(RowAndColumn(unrepeatedMessage.charAt(i+1))[1]);
                }
                else if (RowAndColumn(unrepeatedMessage.charAt(i+1))[0] == 4) {
                    finalMessage = finalMessage + alphabetWithoutJ[RowAndColumn(unrepeatedMessage.charAt(i))[0]+1].charAt(RowAndColumn(unrepeatedMessage.charAt(i))[1]);
                    finalMessage = finalMessage + alphabetWithoutJ[0].charAt(RowAndColumn(unrepeatedMessage.charAt(i+1))[1]);
                }
                else {
                    finalMessage = finalMessage + alphabetWithoutJ[RowAndColumn(unrepeatedMessage.charAt(i))[0]+1].charAt(RowAndColumn(unrepeatedMessage.charAt(i))[1]);
                    finalMessage = finalMessage + alphabetWithoutJ[RowAndColumn(unrepeatedMessage.charAt(i+1))[0]+1].charAt(RowAndColumn(unrepeatedMessage.charAt(i+1))[1]);
                }
            }
            //When the two characters form two corners of a box
            else {
                finalMessage = finalMessage + alphabetWithoutJ[RowAndColumn(unrepeatedMessage.charAt(i))[0]].charAt(RowAndColumn(unrepeatedMessage.charAt(i+1))[1]);
                finalMessage = finalMessage + alphabetWithoutJ[RowAndColumn(unrepeatedMessage.charAt(i+1))[0]].charAt(RowAndColumn(unrepeatedMessage.charAt(i))[1]);
            }
        }
        return convertToOriginal(finalMessage, message);
    }

    public static String playfairDecrypt(String encryptedMessage) {
        String[] alphabetWithoutJ = polybius("");

        String evenEncryptedMessage = removeJ(normalizeText(encryptedMessage));

        if ((evenEncryptedMessage.length() % 2) != 0) {
            evenEncryptedMessage = evenEncryptedMessage + "X";
        }

        String originalMessage = "";

        for (int i = 0; i <= evenEncryptedMessage.length()-1; i += 2) {
            //System.out.println(originalMessage);
            //If both characters are in the same row
            if (RowAndColumn(evenEncryptedMessage.charAt(i))[0].equals(RowAndColumn(evenEncryptedMessage.charAt(i + 1))[0])) {
                if (RowAndColumn(evenEncryptedMessage.charAt(i))[1] == 0) {
                    originalMessage = originalMessage + alphabetWithoutJ[RowAndColumn(evenEncryptedMessage.charAt(i))[0]].charAt(4);
                    originalMessage = originalMessage + alphabetWithoutJ[RowAndColumn(evenEncryptedMessage.charAt(i + 1))[0]].charAt(RowAndColumn(evenEncryptedMessage.charAt(i + 1))[1] - 1);
                } else if (RowAndColumn(evenEncryptedMessage.charAt(i + 1))[1] == 0) {
                    originalMessage = originalMessage + alphabetWithoutJ[RowAndColumn(evenEncryptedMessage.charAt(i))[0]].charAt(RowAndColumn(evenEncryptedMessage.charAt(i))[1] - 1);
                    originalMessage = originalMessage + alphabetWithoutJ[RowAndColumn(evenEncryptedMessage.charAt(i + 1))[0]].charAt(4);
                } else {
                    originalMessage = originalMessage + alphabetWithoutJ[RowAndColumn(evenEncryptedMessage.charAt(i))[0]].charAt(RowAndColumn(evenEncryptedMessage.charAt(i))[1] - 1);
                    originalMessage = originalMessage + alphabetWithoutJ[RowAndColumn(evenEncryptedMessage.charAt(i + 1))[0]].charAt(RowAndColumn(evenEncryptedMessage.charAt(i + 1))[1] - 1);
                }
            }
            //If both characters are in the same column
            else if (RowAndColumn(evenEncryptedMessage.charAt(i))[1].equals(RowAndColumn(evenEncryptedMessage.charAt(i + 1))[1])) {
                if (RowAndColumn(evenEncryptedMessage.charAt(i))[0] == 0) {
                    originalMessage = originalMessage + alphabetWithoutJ[4].charAt(RowAndColumn(evenEncryptedMessage.charAt(i))[1]);
                    originalMessage = originalMessage + alphabetWithoutJ[RowAndColumn(evenEncryptedMessage.charAt(i + 1))[0] - 1].charAt(RowAndColumn(evenEncryptedMessage.charAt(i + 1))[1]);
                } else if (RowAndColumn(evenEncryptedMessage.charAt(i + 1))[0] == 0) {
                    originalMessage = originalMessage + alphabetWithoutJ[RowAndColumn(evenEncryptedMessage.charAt(i))[0] - 1].charAt(RowAndColumn(evenEncryptedMessage.charAt(i))[1]);
                    originalMessage = originalMessage + alphabetWithoutJ[4].charAt(RowAndColumn(evenEncryptedMessage.charAt(i + 1))[1]);
                } else {
                    originalMessage = originalMessage + alphabetWithoutJ[RowAndColumn(evenEncryptedMessage.charAt(i))[0] - 1].charAt(RowAndColumn(evenEncryptedMessage.charAt(i))[1]);
                    originalMessage = originalMessage + alphabetWithoutJ[RowAndColumn(evenEncryptedMessage.charAt(i + 1))[0] - 1].charAt(RowAndColumn(evenEncryptedMessage.charAt(i + 1))[1]);
                }
            }
            //When the two characters form two corners of a box
            else {
                originalMessage = originalMessage + alphabetWithoutJ[RowAndColumn(evenEncryptedMessage.charAt(i))[0]].charAt(RowAndColumn(evenEncryptedMessage.charAt(i + 1))[1]);
                originalMessage = originalMessage + alphabetWithoutJ[RowAndColumn(evenEncryptedMessage.charAt(i + 1))[0]].charAt(RowAndColumn(evenEncryptedMessage.charAt(i))[1]);
            }
        }
        return convertToOriginal(originalMessage, encryptedMessage);
    }
}

