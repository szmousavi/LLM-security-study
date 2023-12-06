import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class sym_encryption_1_Req28 {

    static SecretKey secretKey;

    public static void main(String[] args) throws Exception {
        // Create a random symmetric key
        KeyGenerator keygen = KeyGenerator.getInstance("AES");
        keygen.init(128);
        secretKey = keygen.generateKey();
        
        String originalMessage = "Hello, world!";
        System.out.println("Original Message : " + originalMessage);

        String encryptedMessage = encryptMessage(originalMessage);
        System.out.println("Encrypted Message : " + encryptedMessage);

        String decryptedMessage = decryptMessage(encryptedMessage);
        System.out.println("Decrypted Message : " + decryptedMessage);
    }


    public static String encryptMessage(String plainText) throws Exception {
        // Create an AES Cipher
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        // Encrypt the plain text
        byte[] encryptedByte = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));

        // Encode and return the encrypted byte[]
        return Base64.getEncoder().encodeToString(encryptedByte);
    }

    public static String decryptMessage(String encryptedText) throws Exception {
        // Decode the encrypted text
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);

        // Create an AES Cipher
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        // Decrypt the encoded bytes
        byte[] decryptedByte = cipher.doFinal(decodedBytes);
        
        // Return the decrypted String
        return new String(decryptedByte);
    }

}