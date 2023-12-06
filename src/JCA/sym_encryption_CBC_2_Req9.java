import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

public class sym_encryption_CBC_2_Req9 {
    
    public static byte[] initVector = new byte[16];
    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final String AES = "AES";

    public static void main(String[] args) throws Exception {
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(initVector); //generating random bytes for the vector

        SecretKey secretKey = KeyGenerator.getInstance(AES).generateKey(); //generating secret key
        String cipherText = encrypt("Data to be Encrypted", secretKey, initVector);

        System.out.println("Encrypted Text: " + cipherText);
        String plainText = decrypt(cipherText, secretKey, initVector);

        System.out.println("Decrypted Text: " + plainText);
    }

    public static String encrypt(String dataToBeEncrypted, SecretKey secretKey, byte[] initVector) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(initVector);

        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
        byte[] encryptedData = cipher.doFinal(dataToBeEncrypted.getBytes(StandardCharsets.UTF_8));

        return Base64.getEncoder().encodeToString(encryptedData);
    }

    public static String decrypt(String encryptedData, SecretKey secretKey, byte[] initVector) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(initVector);

        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
        byte[] decryptedData = cipher.doFinal(Base64.getDecoder().decode(encryptedData));

        return new String(decryptedData, StandardCharsets.UTF_8);
    }
}
