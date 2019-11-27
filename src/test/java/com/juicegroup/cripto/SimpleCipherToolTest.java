package com.juicegroup.cripto;

import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleCipherToolTest {

    @Test
    public void shouldCipher_aaaWithKeyword_abc() {
        String input = "aaa";
        String key = "abc";
        String expectedResult = "bcd";

        Cipher cipher = new SimpleCipherTool(key);
        String output = cipher.cipher(input);

        assertEquals(expectedResult, output);
    }

    @Test
    public void shouldCipher_SampleTaskWithKeyword_abc() {
        String input = "Sample task.";
        String key = "abc";
        String expectedResult = "Scpqnh vdtm.";

        Cipher cipher = new SimpleCipherTool(key);
        String output = cipher.cipher(input);

        assertEquals(expectedResult, output);
    }

    @Test
    public void shouldDecipher_aaaWithKeyword_abc() {

        String input = "bcd";
        String key = "abc";
        String expectedResult = "aaa";

        Cipher cipher = new SimpleCipherTool(key);
        String output = cipher.decipher(input);

        assertEquals(expectedResult, output);
    }

    @Test
    public void shouldDecipher_SampleTaskWithKeyword_abc() {
        String input = "Scpqnh vdtm.";
        String key = "abc";
        String expectedResult = "Sample task.";

        Cipher cipher = new SimpleCipherTool(key);
        String output = cipher.decipher(input);

        assertEquals(expectedResult, output);
    }

    @Test
    public void shouldDecipherUrlWithGivenPassword() {
        String input = "ilpeb://cxcivt.rxn/uodacbrkar/tsieufusozegd";
        String key = "arvoia";
        String expectedResult = "https://github.com/csorbazoli/sampletaskvfc";

        Cipher cipher = new SimpleCipherTool(key);
        String output = cipher.decipher(input);

        assertEquals(expectedResult, output);
    }
}