import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class asym_encryption_2_Req16 {

    public static void main(String[] args) throws Exception {
        // Generate key pair
        KeyPair keyPair = buildKeyPair();
        PublicKey pubKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        //Encrypt with public key
        String encrypted = encrypt(pubKey, "This is a secret message");
        System.out.println("Encrypted data:" + encrypted);

        // Decrypt with private key
        String decrypted = decrypt(privateKey, encrypted);
        System.out.println("Decrypted data:" + decrypted);
    }

    public static String encrypt(PublicKey publicKey, String message) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        return Base64.getEncoder().encodeToString(cipher.doFinal(message.getBytes("UTF-8")));
    }

    public static String decrypt(PrivateKey privateKey, String encryptedData) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedData)), "UTF-8");
    }

    public static KeyPair buildKeyPair() throws Exception {
        final int keySize = 2048;
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(keySize);        

        return keyPairGenerator.genKeyPair();
    }
}