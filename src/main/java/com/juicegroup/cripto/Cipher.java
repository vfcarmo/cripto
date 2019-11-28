package com.juicegroup.cripto;

public interface Cipher {

    /**
     * Encrypts the text using the given keyword.
     *
     * @param text Plain text.
     * @return Cipher text.
     */
    String cipher(String text);

    /**
     * Decrypts the cipher text using the given keyword.
     *
     * @param cipheredText Cipher text.
     * @return Plain text.
     */
    String decipher(String cipheredText);
}
