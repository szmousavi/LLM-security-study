import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

public class asym_encryption_1_Req27 {

    public static PublicKey publicKey;
    public static PrivateKey privateKey;

    public static void main(String[] args) throws Exception {
        generateKeyPair();

        String message = "Hello, World!";

        String encryptedMessage = encrypt(message, publicKey);
        System.out.println("Encrypted message: " + encryptedMessage);

        String decryptedMessage = decrypt(encryptedMessage, privateKey);
        System.out.println("Decrypted message: " + decryptedMessage);
    }

    public static void generateKeyPair() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(1024);
        KeyPair pair = keyGen.generateKeyPair();
        publicKey = pair.getPublic();
        privateKey = pair.getPrivate();
    }

    public static String encrypt(String msg, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] bytes = cipher.doFinal(msg.getBytes());
        return bytes.toString();
    }

    public static String decrypt(String msg, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] bytes = cipher.doFinal(msg.getBytes());
        return new String(bytes);
    }
}