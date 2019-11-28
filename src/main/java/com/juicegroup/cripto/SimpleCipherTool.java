package com.juicegroup.cripto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimpleCipherTool implements Cipher {

    private static char[] ALPHABET_CHARS = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private static int[] ALPHABET_VALUES = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11,
            12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26};

    private int[] keywordValues;

    private int currentPosition;

    public SimpleCipherTool(String key) {
        this.keywordValues = buildKeywordValues(key);
        this.currentPosition = 0;
    }

    @Override
    public String cipher(String text) {

        StringBuilder builder = new StringBuilder();
        currentPosition = 0;
        for (char character : text.toCharArray()) {

            int index = getPosition(character);
            if (index < 0) {
                builder.append(character);
            } else {

                int textCharValue = ALPHABET_VALUES[index];
                int keywordValue = keywordValues[currentPosition];
                int cipherPosition = textCharValue + keywordValue - 1; //arrays is started from zero
                builder.append(ALPHABET_CHARS[cipherPosition]);
            }
            updateNextPosition();
        }
        return builder.toString();
    }

    @Override
    public String decipher(String cipheredText) {

        StringBuilder builder = new StringBuilder();
        currentPosition = 0;
        for (char character : cipheredText.toCharArray()) {

            int index = getPosition(character);
            if (index < 0) {
                builder.append(character);
            } else {
                int textCharValue = ALPHABET_VALUES[index];
                int keywordValue = keywordValues[currentPosition];
                int alphabetMaxValue = ALPHABET_VALUES[ALPHABET_VALUES.length - 1];

                int decipherPosition = (textCharValue - keywordValue < 0 )
                        ? (textCharValue - keywordValue + alphabetMaxValue)
                        : (textCharValue - keywordValue);
                int idx = decipherPosition - 1; //arrays is started from zero

                int lastAlphabetCharsIndex = ALPHABET_CHARS.length - 1;

                idx = (idx < 0) ? lastAlphabetCharsIndex : idx;

                builder.append(ALPHABET_CHARS[idx]);
            }
            updateNextPosition();
        }
        return builder.toString();
    }

    /**
     * Creates the array with de correspondent values of the given keyword.
     *
     * @param key Keyword.
     * @return Array with values.
     */
    private int[] buildKeywordValues(String key) {
        List<Integer> array = new ArrayList<>();
        for (char c : key.toCharArray()) {
            array.add(ALPHABET_VALUES[getPosition(c)]);
        }
        return array.stream().mapToInt(i -> i).toArray();
    }

    /**
     * Returns the position of the given character in the alphabet char array.
     *
     * @param character Character.
     * @return The position.
     */
    private int getPosition(char character) {
        int index = Arrays.binarySearch(ALPHABET_CHARS, character);
        return (index < 0) ? -1 : index;
    }

    /**
     * Updates the current position of keyword values.
     */
    private void updateNextPosition() {
        this.currentPosition += 1;
        if (currentPosition > keywordValues.length - 1) {
            currentPosition = 0;
        }
    }

}
