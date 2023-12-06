import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class asym_encryption_3_Req23 {
    public static void main(String[] args) throws Exception {
        String originalText = "Hello World!";
        KeyPair keyPair = buildKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // encrypt the message
        byte [] encrypted = encrypt(originalText.getBytes(), privateKey);     
        System.out.println("Encypted message: " + Base64.getEncoder().encodeToString(encrypted));
        
        // decrypt the message
        byte[] decrypted = decrypt(encrypted, publicKey);                                  
        System.out.println("Decryped message: " + new String(decrypted));     
    }

    public static KeyPair buildKeyPair() throws Exception {
        final int keySize = 2048;
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(keySize);      
        return keyPairGenerator.genKeyPair();
    }

    public static byte[] encrypt(byte[] message, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");  
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);  

        return cipher.doFinal(message);
    }

    public static byte[] decrypt(byte[] encrypted, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");  
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        
        return cipher.doFinal(encrypted);
    }
}