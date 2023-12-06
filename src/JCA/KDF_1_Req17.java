import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class KDF_1_Req17 {
    public static void main(String[] args) {
        String password = "UserPassword";
        String salt = "Salty123"; // It is advised to use unique salt for each user

        System.out.println("Cryptographic Key: " + deriveKey(password, salt));
    }

    private static String deriveKey(String password, String salt) {
        int iterationCount = 65536;
        int keyLength = 128;
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), iterationCount, keyLength);
        SecretKeyFactory skf = null;
        try {
            skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] key = skf.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(key);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Error deriving key", e);
        }
    }
}