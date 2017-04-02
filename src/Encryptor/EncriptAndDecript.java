/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Encryptor;

import javax.crypto.Cipher;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.InvalidKeyException;


public class EncriptAndDecript {

    private static String algorithm = "DESede";
    private static Key key = null;
    private static Cipher cipher = null;
    private static EncriptAndDecript obj = new EncriptAndDecript();

    public EncriptAndDecript() {
        try {
            key = KeyGenerator.getInstance(algorithm).generateKey();
            cipher = Cipher.getInstance(algorithm);
        } catch (Exception e) {
        }
    }

    public static EncriptAndDecript getInstance() {
        return obj;
    }

    public static byte[] encrypt(String input)
            throws InvalidKeyException,
            BadPaddingException,
            IllegalBlockSizeException {
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] inputBytes = input.getBytes();
        return cipher.doFinal(inputBytes);
    }

    public static String getEncryptStringValue(String input) throws InvalidKeyException,
            BadPaddingException,
            IllegalBlockSizeException {
        return new String(encrypt(input));
    }


    public static String decrypt(byte[] encryptionBytes)
            throws InvalidKeyException,
            BadPaddingException,
            IllegalBlockSizeException {
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] recoveredBytes =
                cipher.doFinal(encryptionBytes);
        String recovered =
                new String(recoveredBytes);
        return recovered;
    }
}