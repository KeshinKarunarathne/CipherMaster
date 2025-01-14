package com.fritzdevelopers.ciphermaster;

import java.util.Arrays;

public class CommonMethods {

    public static String normalizeText(String literal) {
        literal = literal.toUpperCase();
        String finalLiteral = "";
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i <= literal.length() - 1; i++) {
            for (int j = 0; j <= alphabet.length() - 1; j++) {
                if (literal.charAt(i) == alphabet.charAt(j)) {
                    finalLiteral = finalLiteral + literal.charAt(i);
                }
            }
        }
        return finalLiteral;
    }

    public static String normalizeTextWithSpaces(String literal) {
        literal = literal.toUpperCase();
        String finalLiteral = "";
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i <= literal.length() - 1; i++) {
            for (int j = 0; j <= alphabet.length() - 1; j++) {
                if (literal.charAt(i) == alphabet.charAt(j)) {
                    finalLiteral = finalLiteral + literal.charAt(i);
                    break;
                }
                else if (finalLiteral.length() >= 1 && j == alphabet.length()-1 && finalLiteral.charAt(finalLiteral.length()-1) != ' ') {
                    finalLiteral = finalLiteral + " ";
                }
            }
        }

        if (finalLiteral.charAt(finalLiteral.length()-1) == ' ') {
            finalLiteral = finalLiteral.substring(0, finalLiteral.length()-1);
        }

        return finalLiteral;
    }

    public static int letterToNumber(char letter) {
        int ASCII = letter;
        return ASCII-65;
    }

    public static String numberToLetter(int number) {
        int correctedNumber = (number % 26);
        char letter = (char) (correctedNumber + 65);
        return Character.toString(letter);
    }

    public static int modularInverse(int beforeMod, int afterMod) {
        int count = 0;

        for (int i = 0; i <= afterMod - 1; i++) {
            if (((beforeMod * i) % afterMod) == 1) {
                count = i;
                break;
            } else {
                count++;
            }
        }
        return count;
    }

    public static String unrepeatedKey(String key) {
        String keyUpper = normalizeText(key);

        if (keyUpper == "") {
            return "";
        }
        else {
            for (int j = 0; j <= keyUpper.length() - 2; j++) {
                for (int k = j + 1; k <= keyUpper.length() - 1; k++) {
                    if (keyUpper.charAt(j) == keyUpper.charAt(k)) {
                        if (k == keyUpper.length() - 1) {
                            //System.out.println("1. " + keyUpper.substring(0, keyUpper.length() - 1));
                            return unrepeatedKey(keyUpper.substring(0, keyUpper.length() - 1));
                        } else {
                            //System.out.println("2. " + keyUpper.substring(0, k) + keyUpper.substring(k + 1, keyUpper.length()));
                            return unrepeatedKey(keyUpper.substring(0, k) + keyUpper.substring(k + 1, keyUpper.length()));
                        }
                    } else if (j == keyUpper.length() - 2 && k == keyUpper.length() - 1) {
                        return keyUpper;
                    }
                }
            }
            return null;
        }
    }

    public static String unrepeatedAlphabet(String key) {
        String keyUpper = normalizeText(key);
        String alphabetWithoutJ = "ABCDEFGHIKLMNOPQRSTUVWXYZ";

        for (int i = 0; i <= keyUpper.length() - 1; i++) {
            for (int j = 0; j <= alphabetWithoutJ.length() - 1; j++) {
                if (alphabetWithoutJ.charAt(j) == keyUpper.charAt(i)) {
                    if (j == alphabetWithoutJ.length() - 1) {
                        alphabetWithoutJ = alphabetWithoutJ.substring(0, j);
                    } else {
                        alphabetWithoutJ = alphabetWithoutJ.substring(0, j) + alphabetWithoutJ.substring(j + 1, alphabetWithoutJ.length());
                    }
                }
            }
        }
        return alphabetWithoutJ;
    }

    public static String keyOrder(String key) {
        Integer keyPlaceValues[] = new Integer[normalizeText(key).length()];
        String keyUpper = normalizeText(key);
        String orderedKey = "";

        for (int i = 0; i <= keyUpper.length()-1; i++) {
            keyPlaceValues[i] = (int) keyUpper.charAt(i);
        }

        Arrays.sort(keyPlaceValues, 0, keyPlaceValues.length);

        for (int j = 0; j <= keyPlaceValues.length-1; j++) {
            String letter = Character.toString((char) ((int) keyPlaceValues[j]));
            orderedKey = orderedKey + letter;
        }
        return orderedKey;
    }

    public static String[] polybius(String key) {
        String editedKey = unrepeatedKey(key);
        String editedAlphabet = unrepeatedAlphabet(editedKey);
        String finalEdit = editedKey + editedAlphabet;
        String polybiusSquare[] = new String[5];

        int count = 0;

        for (int i = 0; i <= polybiusSquare.length-1; i++) {
            polybiusSquare[i] = finalEdit.substring(count, count+5);
            count += 5;
        }

        return polybiusSquare;
    }

    public static String shiftAlphabet(int shift) {
        int start;
        if (shift < 0) {
            start = (int) 'Z' + shift + 1;
        } else {
            start = 'A' + shift;
        }
        String result = "";
        char currChar = (char) start;
        for (; currChar <= 'Z'; ++currChar) {
            result = result + currChar;
        }
        if (result.length() < 26) {
            for (currChar = 'A'; result.length() < 26; ++currChar) {
                result = result + currChar;
            }
        }
        return result;
    }

    public static Integer[] matrixVectorMultiplication(Integer[] matrix, Integer[] vector) {
        Integer[] finalVector = new Integer[vector.length];

        for (int i = 0; i <= vector.length - 2; i += 2) {
            finalVector[i] = ((matrix[0] * vector[i]) + (matrix[1] * vector[i + 1])) % 26;
            finalVector[i + 1] = ((matrix[2] * vector[i]) + (matrix[3] * vector[i + 1])) % 26;
        }

        return finalVector;
    }

    public static Integer[] inverseModuloMatrix(int firstKey, int secondKey, int thirdKey, int fourthKey) {
        //The determinant cannot be equal to zero
        //The HCF of the denominator and 26 must always be 1
        int determinant = (firstKey * fourthKey) - (secondKey * thirdKey);
        int moduloInverseOfDeterminant;
        Integer[] inverseMatrix = new Integer[4];

        moduloInverseOfDeterminant = modularInverse(determinant, 26);

        inverseMatrix[0] = fourthKey;
        inverseMatrix[1] = secondKey * -1;
        inverseMatrix[2] = thirdKey * -1;
        inverseMatrix[3] = firstKey;

        //System.out.println(determinant);
        //System.out.println(moduloInverseOfDeterminant);

        for (int i = 0; i <= inverseMatrix.length-1; i++) {
            while (inverseMatrix[i] < 0) {
                inverseMatrix[i] += 26;
            }
            inverseMatrix[i] *= moduloInverseOfDeterminant;
            inverseMatrix[i] = inverseMatrix[i] % 26;
        }

        return inverseMatrix;
    }

    public static String unrepeatedMessage(String message) {

        String finalMessage = normalizeText(message);

        for (int i = 0; i <= finalMessage.length()-2; i += 2) {
            if (finalMessage.charAt(i) == finalMessage.charAt(i+1)) {
                if (i == finalMessage.length()-2) {
                    finalMessage = finalMessage.substring(0, message.length()-1) + "X";
                }
                else {
                    finalMessage = finalMessage.substring(0, i+1) + "X" + finalMessage.substring(i+2, message.length());
                }
            }
        }
        return finalMessage;
    }

    public static Integer[] RowAndColumn(char letter) {

        String[] polybiusSquare = polybius("");
        Integer[] rowAndColumn = new Integer[2];

        for (int i = 0; i <= polybiusSquare.length-1; i++) {
            for (int j = 0; j <= 4; j ++) {
                if (polybiusSquare[i].charAt(j) == letter) {
                    rowAndColumn[0] = i;
                    rowAndColumn[1] = j;
                    break;
                }
            }
        }
        return rowAndColumn;
    }

    public static String characterToBinary(char symbol) {
        int encodedSymbol = symbol;
        String encodedKeyBin = Integer.toBinaryString(encodedSymbol);
        if (encodedKeyBin.length() < 8) {
            String fillerZero = "00000000";
            return fillerZero.substring(0, 8-encodedKeyBin.length()) + encodedKeyBin;
            //return (fillerZero.repeat(8-encodedKeyBin.length()) + encodedKeyBin);
        }
        else {
            return encodedKeyBin;
        }
    }

    public static String convertToOriginal(String outputMessage, String initialMessage) {
        //This method converts the footprint of the final message to that of the initial message given to be encrypted or decrypted
        initialMessage = normalizeTextWithSpaces(initialMessage);
        String formattedFinalMessage = "";
        int count = 0;

        for (int i = 0; i <= initialMessage.length()-1; i++) {
            if (initialMessage.charAt(i) == ' ') {
                formattedFinalMessage  = formattedFinalMessage + ' ';
            }
            else {
                formattedFinalMessage = formattedFinalMessage + outputMessage.charAt(count);
                count++;
            }
        }
        return formattedFinalMessage;
    }

    public static String removeJ(String message) {
        String messageWithoutJ = "";

        for (int i = 0; i <= normalizeText(message).length()-1; i++) {
            if (normalizeText(message).charAt(i) == 'J') {
                messageWithoutJ = messageWithoutJ + "I";
            }
            else {
                messageWithoutJ = messageWithoutJ + normalizeText(message).charAt(i);
            }
        }
        return messageWithoutJ;
    }

    public static boolean messageVerified(String message, String key) {
        String normalisedMessage = normalizeText(message);
        String normalisedKey = unrepeatedKey(normalizeText(key));
        String spaces = "";

        if (normalisedMessage.length() >= normalisedKey.length()) {
            if (normalisedMessage.length() % normalisedKey.length() != 0) {
                //System.out.println("The length of the message is not a multiple of that of the key");
                return false;
            } else {
                int noOfRows = normalisedMessage.length() / normalisedKey.length();
                for (int i = noOfRows; i <= normalizeTextWithSpaces(message).length() - 1; i += noOfRows) {
                    if (normalizeTextWithSpaces(message).charAt(i) == ' ') {
                        i++;
                    }
                    else {
                        //System.out.println("The length of one or more terms in the message is not equal to the number of rows");
                        return false;
                    }
                }
            }
        }
        else {
            //System.out.println("The length of the message is less than that of the key");
            return false;
        }
        return true;
    }

    public static int HCF(int firstNumber, int secondNumber) {

        int hcf = 0;

        for(int i = 1; i <= firstNumber || i <= secondNumber; i++) {
            if( firstNumber % i == 0 && secondNumber % i == 0 ) {
                hcf = i;
            }
        }
        return hcf;
    }

}
