/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmo_aes;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;



/**
 *
 * @author Mateus Kienen
 */
public class JavaNativeApiTester {

    private static SecretKeySpec secretKey;
    private static byte[] key = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80};
                                 

    public static void setKey(byte[] myKey) {
//        MessageDigest sha = null;
//            
//        key = myKey;
//        key = Arrays.copyOf(key, 16);
//        secretKey = new SecretKeySpec(key, "AES");

    }

    public static String encrypt(String strToEncrypt) {
        try {
            //40,41,42,43,...
            byte[] chave = "40414243444546474849505152535455".getBytes();
            SecretKeySpec secKey = new SecretKeySpec(chave, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public static String decrypt(String strToDecrypt) {
        try {
            byte[] chave = "40414243444546474849505152535455".getBytes();
            SecretKeySpec secKey = new SecretKeySpec(chave, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }

    public static void main(String[] args) {

        String originalString = "DESENVOLVIMENTO!";
        String encryptedString = JavaNativeApiTester.encrypt(originalString);
        String decryptedString = JavaNativeApiTester.decrypt(encryptedString);

        System.out.println("oliginal " + originalString);
        System.out.println("encriptado " + encryptedString);
        System.out.println("decriptado " + decryptedString);
 
    }
}
